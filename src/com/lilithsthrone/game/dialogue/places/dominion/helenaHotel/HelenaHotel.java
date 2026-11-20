package com.lilithsthrone.game.dialogue.places.dominion.helenaHotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class HelenaHotel {
	
//	- Post-Helena's romance quest
//		Scarlett is present during extended work hours, but Helena is only there during normal work hours (she works late to 21:00 on Fridays)
//		If evening, and not an arcane storm:
//			Can go on date to an upmarket restaurant beneath Harpy Nests (turns out Helena owns it, and it's connected to her nest)
//			Scarlett accompanies, but has to wait out of sight
//				She says you don't have the opportunity to impress her by buying the meal; everything here is free for her
//				Affection increases instead by acting firmly but kindly to her
//				You can also lose affection by belittling her or implying that other harpies are more attractive (or other such obviously bad things)
//			Use actions to increase (or decrease) affection. Can increase by about 10 per date.
//				If have a rose, can give to her.
//				She only likes white roses. Offering any other colour decreases affection.
//			Can then ask out on date every Friday between 17:00 and 21:00
//			Once affection >=75, you can talk to Helena about her sex life. She is very flustered and hesitantly admits that she's never had sex. (Believing herself to be too good for anyone)
//			You can take her up to her penthouse apartment and have sex with her.
//				She suggests having Scarlett demonstrate what she wants on one of her maids. (If accept, the limits of what Helena wants are displayed to the player)
//					First time, she will only accept kissing/groping.
//						She gains oral fetishes afterwards
//					Second time, she comes out in lingerie, and allows you to play with her naked breasts (dominantly lets the player fuck her breasts if player has cock).
//						Gains self-breasts fetish
//					Third time, she lets you perform oral on her
//				After oral, she says that this is as far she wants to go - wants to keep her virginity
//					Can convince for giving you oral
//					Can convince for anal (hard if she is still virgin; if virginity taken, she is pretty slutty)
//						She gains anal receiving fetish afterwards (becoming lusty maiden)
//					Can ask Scarlett to set up romantic date in her apartment for taking her virginity
//						Can either let her dom at her own pace, or dom her
//							Gives her dom or sub fetish
//					After virginity taken
//						Wears black lingerie
//						Wears different dress - comments that it's easier to lift up for you
//						Unlocks sex in store, makes her a lot lewder
//						Can convince her to let Scarlett have sex with her as well
//							If convinced, can sometimes find store closed. Entering will lead to scene where Scarlett is fucking Helena (with strapon if female)
//							She says it would be nice for Scarlett to be bigger; other harpies prefer small feminine penises, but she wants to get fucked by a big cock (player has option to increase Scarlett's penis size)
//						Can be convinced to perform or receive under-table oral during date
	

	private static HelenaConversationTopic talkTopic = HelenaConversationTopic.SLAVES;
	private static boolean firstKissScene = false;
	
	private static SexManagerInterface getScarlettOralSexManager() {
		return new SexManagerDefault(
				false,
				SexPosition.SITTING,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotSitting.SITTING)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.ONGOING_ONLY;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return Util.newHashMapOfValues(
						new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)),
						new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					if(Main.game.getNpc(Scarlett.class).hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE; // Doesn't want to cover player in cum before Helena's date
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				return Main.sex.isSatisfiedFromOrgasms(partner, true);
			}
		};
	}
	
	private static String incrementHelenaAffection(float affection) {
		float currentAffection = Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer());
		if(affection<0) {
			if(currentAffection+affection < AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue()) {
				return ""; // Don't drop affection below 'like' level.
			}
		}
		if(currentAffection>=100) {
			return "";
		}
		return Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), affection);
	}

	public static SexManagerInterface getHelenaSexManager(boolean helenaDom,
			AbstractSexPosition position,
			SexSlot helenaSlot,
			SexSlot playerSlot,
			SexType helenaPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return getHelenaSexManager(helenaDom, false, !helenaDom, position, helenaSlot, null, playerSlot, helenaPreference, null, null, null, exposeAtStartOfSexMap);
	}
	
	public static SexManagerInterface getHelenaSexManager(boolean helenaDom,
			boolean scarlettDom,
			boolean playerDom,
			AbstractSexPosition position,
			SexSlot helenaSlot,
			SexSlot scarlettSlot,
			SexSlot playerSlot,
			SexType helenaToPlayerPreference,
			SexType helenaToScarlettPreference,
			SexType scarlettToPlayerPreference,
			SexType scarlettToHelenaPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(
						(helenaDom
							?new Value<>(Main.game.getNpc(Helena.class), helenaSlot)
							:null),
						(scarlettDom && scarlettSlot!=null
								?new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)
								:null),
						(playerDom
								?new Value<>(Main.game.getPlayer(), playerSlot)
								:null)),
				Util.newHashMapOfValues(
						(!helenaDom
							?new Value<>(Main.game.getNpc(Helena.class), helenaSlot)
							:null),
						(!scarlettDom && scarlettSlot!=null
								?new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)
								:null),
						(!playerDom
								?new Value<>(Main.game.getPlayer(), playerSlot)
								:null))) {
			@Override
			public boolean isPlayerAbleToStopSex() {
				return helenaToPlayerPreference==null;
			}
			@Override
			public boolean isAbleToSkipSexScene() {
				return true;
			}
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return clothingToEquip.isCondom(); // Do not allow toy use
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return helenaToPlayerPreference==null;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
				return character.equals(Main.game.getNpc(Helena.class));
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(character.equals(Main.game.getNpc(Helena.class))) {
					if(targetedCharacter.isPlayer() && helenaToPlayerPreference!=null) {
						return helenaToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Scarlett.class)) && helenaToScarlettPreference!=null) {
						return helenaToScarlettPreference;
					}
				} else if(character.equals(Main.game.getNpc(Scarlett.class))) {
					if(targetedCharacter.isPlayer() && scarlettToPlayerPreference!=null) {
						return scarlettToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Helena.class)) && scarlettToHelenaPreference!=null) {
						return scarlettToHelenaPreference;
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(character.equals(Main.game.getNpc(Helena.class))) {
					if(targetedCharacter.isPlayer() && helenaToPlayerPreference!=null) {
						return helenaToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Scarlett.class)) && helenaToScarlettPreference!=null) {
						return helenaToScarlettPreference;
					}
				} else if(character.equals(Main.game.getNpc(Scarlett.class))) {
					if(targetedCharacter.isPlayer() && scarlettToPlayerPreference!=null) {
						return scarlettToPlayerPreference;
					} else if(targetedCharacter.equals(Main.game.getNpc(Helena.class)) && scarlettToHelenaPreference!=null) {
						return scarlettToHelenaPreference;
					}
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, List<SexType>> getSexTypesBannedMap() {
				Map<GameCharacter, List<SexType>> bannedMap = new HashMap<>();
				bannedMap.put(Main.game.getNpc(Helena.class), new ArrayList<>());
				bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS));
				bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS));
				if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE));
				}
				if(Main.game.getNpc(Helena.class).isAssVirgin()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TAIL));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE));
				}
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
				}
				return bannedMap;
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(helenaToPlayerPreference!=null
						&& helenaToPlayerPreference.getPerformingSexArea()==SexAreaOrifice.ANUS
						&& helenaToPlayerPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				if(helenaToScarlettPreference!=null
						&& helenaToScarlettPreference.getPerformingSexArea()==SexAreaOrifice.ANUS
						&& helenaToScarlettPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Scarlett.class), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				if(Main.game.getNpc(Helena.class).isVaginaVirgin()
						&& helenaToPlayerPreference!=null
						&& helenaToPlayerPreference.getPerformingSexArea()==SexAreaOrifice.VAGINA
						&& helenaToPlayerPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.VAGINA, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.VAGINA).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.VAGINA).put(Main.game.getNpc(Helena.class), Util.newHashSetOfValues(LubricationType.GIRLCUM));
					return map;
				}
				return super.getStartingWetAreas();
			}
		};
	}

	
	
	// Generic dialogue:
	
	public static final DialogueNode HOTEL_TRAVEL_TO_NEST = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotel", "HOTEL_TRAVEL_TO_NEST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.HARPY_NESTS_HELENAS_NEST.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HOTEL_TRAVEL_TO_DOMINION = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotel", "HOTEL_TRAVEL_TO_DOMINION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	
	// Helena dates:
	
	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeAllCompanions(true);
			// Reset date flags:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetupEatenOut, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Идти", "Пойти обратно к апартаментам Helena вместе с ней.", DATE_TRAVEL) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WALK"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WALK_REPEAT"));
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Лететь", "Полететь обратно к апартаментам Helena вместе с ней.", DATE_TRAVEL) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_FLY"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_FLY_REPEAT"));
							}
						}
					};
				}
				return new Response("Лететь", "Вы не можете летать...", null);
				
			} else if(index==3) {
				if(Main.game.getPlayer().isTaur()) {
					return new Response("Предложить подвезти", "Предложить Helena прокатиться у вас на спине и отнести её обратно к апартаментам.", DATE_TRAVEL) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_RIDE"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_RIDE_REPEAT"));
							}
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
						}
					};
				}
				return new Response("Предложить подвезти", "Вы не тавр, поэтому не можете предложить Helena прокатиться у вас на спине...", null);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DATE_TRAVEL = new DialogueNode("Отель «Золотое перо»", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Set place 2 tiles left of harpy nests as Helena's hotel:
			if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
				Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HARPY_NESTS_ENTRANCE).getLocation();
				vec.setX(vec.getX()-2);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_HELENA_HOTEL);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_HELENA_HOTEL.getName());
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setTravelledTo(true);
			}
			
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL");
		}
		@Override
		public Response getResponse(int responseTab, int index) { // Scarlett interactions:
			if(index==1) {
				return new Response("Светская беседа", "Поболтать с Scarlett, пока ждешь возвращения Helena.", DATE_RESTAURANT_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_SMALL_TALK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Ждать", "Просто ждать возвращения Helena.", DATE_RESTAURANT_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WAIT"));
					}
				};
				
			} else if(index==3
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateVirginityTalk)) {// Romantic setup for taking virginity or repeat of scene:
				if(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET))<3) {
					return new Response("Романтическая обстановка", "Для этого в вашем инвентаре должно быть как минимум [style.boldMinorBad(три "+ItemType.GIFT_ROSE_BOUQUET.getNamePlural(false)+")] предмета!", null);
				}
				if((!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
						 && (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))) {
					return new Response("Романтическая обстановка", "Для этого действия вам [style.colourBad(требуется либо пенис, либо вагина)], а также [style.colourBad(возможность получить доступ к своим гениталиям)]!", null);
				}
				return new Response("Романтическая обстановка", "Попросить Scarlett подготовить романтическую обстановку в квартире Helena к вашему возвращению со свидания.", DATE_RESTAURANT_ROMANTIC_SETUP);
				
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_ROMANTIC_SETUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_AGREED");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_DECLINED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				if(index==1) {
					return new Response("Поблагодарить её", "Поблагодарить Scarlett за то, что она согласилась на вашу просьбу.", DATE_RESTAURANT_START) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_THANKS"));
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Отказаться", "Отказаться от условий Scarlett.", DATE_RESTAURANT_START) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_REFUSED"));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().getMoney()<1000) {
						return new Response("Заплатить ей ("+UtilText.formatAsMoneyUncoloured(1000, "охват")+")", "У вас недостаточно денег, чтобы заплатить Scarlett...", null);
					}
					return new Response("Заплатить ей ("+UtilText.formatAsMoney(1000, "охват")+")", "Заплатить Scarlett одну тысячу огней в обмен на то, что она устроит романтическую обстановку в квартире Helena.", DATE_RESTAURANT_START) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_PAID"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-1000));
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getNpc(Scarlett.class).hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Сосать член", "В данный момент вы не можете пользоваться ртом, поэтому не можете предложить Scarlett отсосать её член...", null);
						}
						return new ResponseSex(
								"Сосать член",
								"Сделать так, как предлагает Scarlett, и быстро отсосать ей член в обмен на то, что она устроит романтическую обстановку в квартире Helena.",
								true,
								false,
								getScarlettOralSexManager(),
								null,
								null,
								DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL,
								UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						};
						
					} else {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Куннилингус", "В данный момент вы не можете пользоваться ртом, поэтому не можете предложить Scarlett отсосать её киску...", null);
						}
						return new ResponseSex(
								"Куннилингус",
								"Сделать так, как предлагает Scarlett, и быстро отсосать ей киску в обмен на то, что она устроит романтическую обстановку в квартире Helena.",
								true,
								false,
								getScarlettOralSexManager(),
								null,
								null,
								DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL,
								UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_START_CUNNILINGUS")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					}
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL = new DialogueNode("Завершено", "Теперь, когда она получила оргазм, с Scarlett достаточно.", true) {
		@Override
		public void applyPreParsingEffects() { //TODO test
			Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.MOUTH, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.EYES, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.HAIR, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.HEAD, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.NECK, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Дождаться прихода Helena.", DATE_RESTAURANT_START) {
					@Override
					public int getSecondsPassed() {
						return 3*60;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			((Helena)Main.game.getNpc(Helena.class)).applyDressForDate();
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вино", "Сказать Helena и официантке-гарпии, что сегодня вечером вы тоже будете пить вино.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WINE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.1f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			} else if(index==2) {
				return new Response("Вода", "Попросить у гарпии воды.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WATER"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
				
			} else if(index==3) {
				return new Response("Пиво", "Попросить у гарпии пива.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_BEER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.05f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
				
			} else if(index==4) {
				return new Response("Виски", "Попросить у гарпии стакан виски.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WHISKEY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.4f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_TALKING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			talkTopic = HelenaConversationTopic.getRandomTopic();
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAlcoholLevel(0.1f));
		}
		@Override
		public int getSecondsPassed() {
			return 40*60;
		}
		@Override
		public String getContent() {
			// One of several talk topics:
			if(Main.game.getDialogueFlags().hasHelenaConversationTopic(talkTopic)) {
				return (UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_TALKING_"+talkTopic+"_REPEAT"));
			} else {
				return (UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_TALKING_"+talkTopic));
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			switch(talkTopic) {
				case SLAVES:
					if(index==1) {
						return new Response("Естественно", "Сказать Helena, что вы считаете вполне естественным, когда некоторые люди являются рабами.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_NATURAL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Необходимость", "Сказать Helena, что считаете рабство прискорбной необходимостью в таком обществе, как в Dominion.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_NECESSITY"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Неверно", "Сказать Helena, что рабство аморально и что однажды его обязательно отменят.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_WRONG"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case HARPY_NESTS:
					if(index==1) {
						return new Response("Королева", "Сказать Helena, что над всеми Гнездами Гарпий должна править одна королева, и что эту роль стоит занять ей.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_HELENA_TOP"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Баланс", "Сказать Helena, что Гнезда Гарпий, похоже, обрели некий стабильный баланс, поэтому менять ничего не стоит.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_BALANCE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Демонтаж", "Сказать Helena, что социальную структуру Гнезд Гарпий нужно разрушить и полностью перестроить.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_BAD"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case RACES:
					if(index==1) {
						return new Response("Гарпии", "Сказать Helena, что гарпии, несомненно, самые прекрасные из всех рас, и что она — самая прекрасная из всех.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_HARPIES"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Другое", "Сказать Helena, что ты предпочитаешь какую-то другую расу, а не гарпий.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_OTHER"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Личность", "Сказать Helena, что тебе плевать на расу, и что твоё влечение к кому-то больше зависит от личности, чем от внешности.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_PERSONALITY"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case HARPIES:
					if(index==1) {
						return new Response("Восхитительна", "Сказать Helena, что Scarlett — самая восхитительная спутница.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_CONTROL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Грубая", "Сказать Helena, что Scarlett очень грубая, но, по крайней мере, она ведет себя прилично в её присутствии.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_RUDE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Сучка", "Сказать Helena, что Scarlett — настоящая сука.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_BITCH"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case BUSINESS:
					if(index==1) {
						return new Response("Лесть", "Сказать Helena, что только человек с её исключительными способностями смог так развернуть это дело.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_FLATTER"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Присвоить заслуги", "Сказать Helena, что ты заслуживаешь как минимум части заслуг за то, как хорошо всё обернулось.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_CREDIT"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Удивлённая", "Сказать Helena, что вы удивлены успехом дела; вы и представить не могли, что идея Helena сработает.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_SURPRISE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
			}
			return null;
		}
	};
	
	// You can bring up a topic to discuss over pudding
	public static final DialogueNode DATE_RESTAURANT_PLAYER_TOPIC = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().addHelenaConversationTopic(talkTopic);
		}
		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Helena", "Поговорить с Helena и спросить, какие у неё планы на выходные.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_HELENA"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
					}
				};
				
			} else if(index==2) {
				return new Response("Arcane", "Перевести тему разговора на Arcane.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_ARCANE"));
					}
				};
				
			} else if(index==3) {
				return new Response("Политика", "Поговорить с Helena о Lilith и её правлении в Dominion.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_POLITICS"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
					}
				};
				
			} else if(index==4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
				if(Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer())<70) {
					return new Response("Секс-жизнь",
							"Вы чувствуете, что Helena ещё не готова открыто обсуждать с вами свою сексуальную жизнь...<br/>[style.italicsMinorBad(Требуется Привязанность Helena к вам не менее 70. Сейчас она составляет "+Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer())+".)]",
							null);
				}
				return new Response("Секс-жизнь", "Поговорить с Helena о её сексуальной жизни.", DATE_RESTAURANT_END_SEX_TALK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_SEX_LIFE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateSexLifeTalk, true);
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
					}
				};
				
			} else if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGift)) {
					return new Response("Подарок", "Вы уже дарили Helena подарок...", null);
					
				} else {
					return new Response("Подарок",
							"Подарить Helena подарок (открывает экран выбора подарков).<br/>[style.italicsMinorGood(После того как вы подарите ей подарок, вы сможете вернуться к этой сцене и расспросить её об одной из других тем.)]",
							DATE_RESTAURANT_PLAYER_TOPIC) {
						@Override
						public DialogueNode getNextDialogue() {
							return GiftDialogue.getGiftDialogue(Main.game.getNpc(Helena.class), DATE_RESTAURANT_GIFT, 0);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_GIFT = new DialogueNode("Дарить подарок", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_RESTAURANT_PLAYER_TOPIC.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().applyFoodConsumed(30);
			Main.game.getPlayer().applyDrinkConsumed(30);
			Main.game.getNpc(Helena.class).applyFoodConsumed(30);
			Main.game.getNpc(Helena.class).applyDrinkConsumed(30);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сопроводить", "Проводить Helena обратно в её пентхаус.", DATE_RESTAURANT_END_HOME) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_ENTRANCE);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
					return new Response("Уйти", "Поскольку Scarlett уже устроила для вас романтическую обстановку в квартире Helena, вам совсем не хочется её оставлять!", null);
				}
				return new Response("Уйти", "Попрощаться с Helena и сказать, что надеетесь скоро увидеться снова.", DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateFirstDateComplete, true);
						Main.game.getNpc(Helena.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_GOODBYE"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_END_SEX_TALK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_RESTAURANT_END.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END_HOME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_ROMANCE");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
				if(index==1) {
					return new Response("Спальня",
							Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
								?"Helena ведет вас по коридору в свою спальню."
								:"Вести за собой Helena по коридору в её спальню.",
							DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_ROMANTIC_SCENE_BEDROOM"));
						}
					};
				}
				
			} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
				if(index==1) {
					return new Response("Спальня", "Helena не принимает отказов, увлекая вас за собой к своей спальне...", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_HELENA_DOM"));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Прощай", "Попрощаться с Helena и вернуться в Dominion.", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_GOODBYE"));
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Прощальный поцелуй", "Поцеловать Helena в щеку и попрощаться.", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_GOODBYE_KISS"));
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						}
					};
					
				} else if(index==3) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
						return new Response("Внутри",
								"Вы думаете, что Helena пригласила бы вас внутрь, если бы вы смогли заставить её открыто поговорить о её сексуальной жизни...<br/>[style.italicsMinorBad(В следующий раз, когда вы пойдете на свидание с Helena, если её привязанность к вам будет 70 или выше, вам стоит спросить её о сексуальной жизни, чтобы открыть это действие.)]",
								null);
					}
					return new Response("Внутри", "Принять приглашение Helena войти внутрь.", DATE_APARTMENT_START) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_LOUNGE);
							Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
						}
					};
					
				} else if(index==4 && ((Helena)Main.game.getNpc(Helena.class)).isSlutty()) {
					return new Response("Спальня", "Тащить Helena прямиком в спальню, чтобы дать ей тот трах, которого она так явно жаждет.", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_BEDROOM"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			firstKissScene = false;
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START"));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateApartmentSeen, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(index==0) {
				if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_COFFEE) {
					return new Response("Уйти", "Теперь, когда вы выпили кофе, пора уходить...", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START_LEAVE"));
						}
					};
					
				} else {
					return new Response("Уйти", "Тебе стоит хотя бы выпить немного кофе перед уходом...", null);
				}
			}
			
			if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_KISS) {
				responses.add(new Response("Кофе", "Helena вряд ли захочет прерывать веселье ради кофе.", null));
				
			} else if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_COFFEE) {
				responses.add(new Response("Кофе", "Ты и так пьешь кофе с Helena...", null));
				
			} else {
				responses.add(new Response("Кофе", "Ну, ты ведь именно за этим сюда и пришел, верно?", DATE_APARTMENT_COFFEE));
			}
			
			if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
				responses.add(new Response("Первый поцелуй",
						"Судя по тому, как она к тебе относится, ты чувствуешь, что у тебя есть шанс подарить Helena её первый поцелуй...",
						DATE_APARTMENT_KISS) {
					@Override
					public void effects() {
						firstKissScene = true;
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE, true));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
					}
				});
				
			} else {
				if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
					responses.add(new Response("Заняться милованием", "Хотя ей определенно хочется гораздо большего, чем просто поцелуй, Helena будет вполне счастлива провести время, страстно целуясь с тобой на диване.", DATE_APARTMENT_KISS));
					
				} else {
					responses.add(new Response("Заняться милованием", "Теперь, когда Helena познала удовольствие от поцелуев, вы понимаете, что она будет не прочь провести время, нежно целуясь с вами на диване.", DATE_APARTMENT_KISS));
				}
			}
			
			if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
				responses.add(new Response("Спальня", "Helena еще не совсем к этому готова. Возможно, если вы заберете ее первый поцелуй, в следующий раз, когда вы будете здесь, она согласится переместиться в спальню.", null));
				
			} else {
				responses.add(new Response("Спальня",
						((Helena) Main.game.getNpc(Helena.class)).isSlutty()
							?"Дать Helena то, чего она так отчаянно желает, и предложить отправиться в ее спальню."
							:"Спросить Helena, не хочет ли она пойти в спальню вместе с вами.",
						DATE_APARTMENT_BEDROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START_BEDROOM"));
					}
				});
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_LEAVE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateFirstDateComplete, true);
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			Main.game.getNpc(Helena.class).equipClothing();
		}
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
				return new Response("Продолжить", "Продолжить прогулку по улицам Dominion.", PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_COFFEE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_COFFEE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_APARTMENT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DATE_APARTMENT_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(firstKissScene) {
				return 30*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			if(firstKissScene) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_FIRST");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(firstKissScene) {
				if(index==1) {
					return new Response("Уйти",
							"Совершенно ошеломленная произошедшим, Helena убежала, и ей нужно время, чтобы прийти в себя. Возможно, в следующий раз, когда вы поведете ее на свидание, она будет готова зайти еще дальше...",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_FIRST_LEAVE"));
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("Уйти", "Сказать Helena, что вам пора идти.", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_LEAVE"));
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
						}
					};
					
				} else if(index==1) {
					return new Response("Спальня", "Отправиться в спальню вместе с Helena.", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_BEDROOM"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			((Helena)Main.game.getNpc(Helena.class)).applyLingerie();
		}
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
			List<Response> responses = new ArrayList<>();
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetupEatenOut)
					&& Main.game.getNpc(Helena.class).isVaginaVirgin()) { // If romantic scene and Helena is still a virgin, you are led into giving oral:
				responses.add(new ResponseSex(
						"Куннилингус",
						"Повалить Helena на кровать, опуститься головой между ее ног и начать ласкать ее губами.",
						true,
						true,
						getHelenaSexManager(false,
								SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX,
						UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ROMANCE_PERFORM_CUNNILINGUS")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetupEatenOut, true);
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				});
				
			} else { // This is not a romantic scene, so give all options:
				responses.add(new ResponseSex(
						"Ласкать пальцами",
						"Ласкать киску Helena пальцами, одновременно посасывая ее грудь.",
						true,
						true,
						getHelenaSexManager(false,
								Main.game.getPlayer().isTaur()
									?SexPosition.STANDING
									:SexPosition.SITTING,
								Main.game.getPlayer().isTaur()
									?SexSlotStanding.STANDING_SUBMISSIVE
									:SexSlotSitting.SITTING_IN_LAP,
								Main.game.getPlayer().isTaur()
									?SexSlotStanding.STANDING_DOMINANT
									:SexSlotSitting.SITTING,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.NIPPLES, CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_SEX,
						(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE))==0
							?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_FINGERING_FIRST_TIME")
							:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_FINGERING_EXPERIENCED"))) {
					@Override
					public void effects() {
						if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
						}
						if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE));
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueNipple.SUCKLE_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), FingerVagina.FINGERING_START, false, true));
					}
				});
				
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
						responses.add(new Response("Принять пайзури", "Helena еще не готова зайти так далеко с тобой...", null));
						
					} else if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("Принять пайзури", "Так как ты таур, Helena не может принять удобную позу, чтобы сделать тебе титджоб...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Принять пайзури",
								"Заставить Helena сжать грудь и сделать тебе титджоб.",
								true,
								true,
								getHelenaSexManager(false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.NIPPLES)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PAIZURI_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PAIZURI_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_BREASTS_SELF)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_BREASTS_SELF));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisBreasts.FUCKING_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
					responses.add(new Response("Куннилингус", "Helena еще не готова зайти так далеко с тобой...", null));
					
				} else {
					responses.add(new ResponseSex(
							"Куннилингус",
							"Повалить Helena на кровать, опуститься головой между ее ног и начать ласкать ее губами.",
							true,
							true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE))==0
								?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PERFORM_CUNNILINGUS_FIRST_TIME")
								:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_PERFORM_CUNNILINGUS_EXPERIENCED"))) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}
	
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Принять куннилингус", "Так как у тебя нет доступа к влагалищу, Helena не может сделать тебе куннилингус...", null));
						
					} else if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("Принять куннилингус", "Helena еще не готова зайти так далеко с тобой...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Принять куннилингус",
								"Заставить Helena опуститься на колени и отсосать тебе.",
								true,
								true,
								getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.LYING_DOWN,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL_BEHIND
											:SexSlotLyingDown.MISSIONARY_ORAL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_CUNNILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						});
					}
				}
	
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Получить минет", "Так как ты не можешь получить доступ к своему члену, Helena не может отсосать тебе...", null));
						
					} else if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("Получить минет", "Helena еще не готова зайти так далеко с тобой...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Получить минет",
								"Заставить Helena опуститься на колени и сосать твой член.",
								true,
								true,
								getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.SITTING,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL
											:SexSlotSitting.PERFORMING_ORAL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotSitting.SITTING,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_RECEIVE_BLOWJOB_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
							}
						});
					}
				}

				boolean sixtyNinePenisFocus = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)
						|| !Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
					responses.add(new Response("Шестьдесят девять", "Helena еще не готова заняться этим с тобой...", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Шестьдесят девять", "Так как ты тавр, ты не можешь принять удобную позу для секса в стиле «шестьдесят девять» с Helena...", null));
					
				} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					responses.add(new ResponseSex(
							"Шестьдесят девять",
							"Предложить Helena сесть тебе на лицо и наклониться, чтобы вы могли отсасывать друг другу...",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.SIXTY_NINE, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, sixtyNinePenisFocus?SexAreaPenetration.PENIS:SexAreaOrifice.VAGINA),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, sixtyNinePenisFocus?CoverableArea.PENIS:CoverableArea.VAGINA)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", sixtyNinePenisFocus?"DATE_APARTMENT_BEDROOM_SIXTY_NINE_PENIS_HELENA_DOM":"DATE_APARTMENT_BEDROOM_SIXTY_NINE_HELENA_DOM")) {
						@Override
						public void effects() {
							if(sixtyNinePenisFocus && !Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), sixtyNinePenisFocus?PenisMouth.GIVING_BLOWJOB_START:TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					});
					
				} else {
					responses.add(new ResponseSex(
							"Шестьдесят девять",
							"Предложить самому сесть Helena на лицо и наклониться, чтобы вы могли отсасывать друг другу...",
							true, true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.SIXTY_NINE,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, sixtyNinePenisFocus?SexAreaPenetration.PENIS:SexAreaOrifice.VAGINA),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, sixtyNinePenisFocus?CoverableArea.PENIS:CoverableArea.VAGINA)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", sixtyNinePenisFocus?"DATE_APARTMENT_BEDROOM_SIXTY_NINE_PENIS_HELENA_SUB":"DATE_APARTMENT_BEDROOM_SIXTY_NINE_HELENA_SUB")) {
						@Override
						public void effects() {
							if(sixtyNinePenisFocus && !Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), sixtyNinePenisFocus?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
					responses.add(new Response("Скрайсинг", "Helena еще не готова заняться этим с тобой...", null));
					
				} else if(!Main.game.getPlayer().hasVagina()) {
					responses.add(new Response("Скрайсинг", "У тебя нет влагалища, поэтому ты не можешь заняться скрайсингом с Helena...", null));

				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					responses.add(new Response("Скрайсинг", "Так как у тебя нет доступа к влагалищу, ты не можешь заняться скрайсингом с Helena...", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Скрайсинг", "Поскольку ты таур, ты не можешь принять подходящую позу для скрайсинга с Helena...", null));
					
				} else {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						responses.add(new ResponseSex(
								"Скрайсинг",
								"Предложить вам обоим заняться скрайсингом...",
								true, true,
								getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT))==0
										?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_DOM_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_DOM_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true));
							}
						});
						
					} else {
						responses.add(new ResponseSex(
								"Скрайсинг",
								"Предложить вам обоим заняться скрайсингом...",
								true, true,
								getHelenaSexManager(false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.SCISSORING,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_SCISSORING_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), ClitClit.TRIBBING_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).isVaginaVirgin()) {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Наездница", "Так как у тебя нет пениса, Helena не сможет оседлать тебя...", null));

						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Наездница", "Так как у тебя нет доступа к пенису, Helena не сможет оседлать тебя...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Наездница",
									"Позволь Helena властно оседлать твой член.",
									true, true,
									getHelenaSexManager(true,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_COWGIRL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							});
						}
						
					} else { // SUBMISSIVE:
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Миссионерская", "Так как у тебя нет пениса, ты не сможешь властно трахать Helena в миссионерской позиции...", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Миссионерская", "Так как у тебя нет доступа к пенису, ты не сможешь трахать Helena...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Миссионерская",
									"Властно трахай Helena в миссионерской позиции.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_MISSIONARY")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
						
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Догги-стайл", "Так как у тебя нет пениса, ты не можешь доминантно трахать Helena в позе догги-стайл...", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Догги-стайл", "Так как у тебя нет доступа к пенису, ты не сможешь трахать Helena...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Догги-стайл",
									"Доминантно трахать Helena в позе догги-стайл.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_DOGGY")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
						
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Прессинг при спаривании", "Так как у тебя нет пениса, ты не можешь доминантно осеменять Helena...", null));
							
						} else if(Main.game.getNpc(Helena.class).isVisiblyPregnant()) {
							responses.add(new Response("Прессинг при спаривании", "Поскольку у Helena уже раздутый, беременный живот, она вряд ли захочет, чтобы ты прижал её в этой позе...", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Прессинг при спаривании", "Так как у тебя нет доступа к пенису, ты не сможешь трахать Helena...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Прессинг при спаривании",
									"Повали Helena на спину и немедленно начни её осеменять.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MATING_PRESS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_MATING_PRESS")) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE));
									}
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
					
				} else {
					if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("Девственность", "Helena вряд ли захочет обсуждать потерю девственности, пока ты не отсосешь ей...", null));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateVirginityTalk)) {
						responses.add(new Response(
								"Девственность",
								"Helena уже сказала тебе, что не готова лишиться девственности.<br/>[style.italicsMinorGood(Возможно, тебе удастся договориться со Scarlett, когда в следующий раз пойдешь с Helena на свидание...)]",
								null));
						
					} else {
						responses.add(new Response("Девственность", "Спросить у Helena, готова ли она отдать тебе девственность.", DATE_APARTMENT_BEDROOM) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_VIRGINITY_TALK"));
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateVirginityTalk, true);
							}
						});
					}
				}
				
				
				// Anal:
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("Заняться анилингусом", "Helena не захочет анилингуса, пока ты не отсосешь ей клитор.", null));
						
					} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						responses.add(new ResponseSex(
								"Анилингус в позе «фейс-ситинг»",
								"Заставить Helena сесть тебе на лицо, чтобы заняться анилингусом.",
								true, true,
								getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_DOM_FIRST_TIME") // She makes it clear that she will never return the favour
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_DOM_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							}
						});
						
					} else {
						responses.add(new ResponseSex(
								"Заняться анилингусом",
								"Заставить Helena подставить тебе задницу, чтобы заняться анилингусом.",
								true, true,
								getHelenaSexManager(false,
										SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
							}
						});
					}
					
					if(Main.game.getNpc(Helena.class).isAssVirgin()) {
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Лишить анальной девственности", "Так как у тебя нет пениса, ты не можешь лишить Helena анальной девственности...", null));
							
						} else if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
							responses.add(new Response("Лишить анальной девственности",
									"Helena совсем не горит желанием лишиться своей анальной девственности. Возможно, если ты сначала подготовишь её с помощью анилингуса, её можно будет переубедить...",
									null));
							
						} else {
							responses.add(new ResponseSex(
									"Лишить анальной девственности",
									"Предложить Helena, что ты мог бы лишить её анальной девственности...",
									true, true,
									getHelenaSexManager(false,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexPosition.LYING_DOWN
												:SexPosition.ALL_FOURS,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexSlotLyingDown.COWGIRL
												:SexSlotAllFours.ALL_FOURS,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexSlotLyingDown.LYING_DOWN
												:SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_ANAL_VIRGINITY")) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
									}
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ANAL_RECEIVING));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								}
							});
						}
						
					} else {
						if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("Наездница (анально)", "Так как у тебя нет пениса, ты не можешь заставить Helena оседлать твой член анально...", null));
							} else {
								responses.add(new ResponseSex(
										"Наездница (анально)",
										"Позволить Helena доминантно оседлать твой член, используя свою задницу.",
										true, true,
										getHelenaSexManager(false,
												SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_COWGIRL_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
									}
								});
							}
							
						} else {
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("Догги-стайл (анально)", "Так как у тебя нет пениса, ты не можешь доминантно трахать анус Helena в позе догги-стайл...", null));
							} else {
								responses.add(new ResponseSex(
										"Догги-стайл (анально)",
										"Доминантно трахать анус Helena в позе догги-стайл.",
										true, true,
										getHelenaSexManager(false,
												SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_DOGGY_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								});
							}
						}
					}
				}
				
				if(Main.game.isFootContentEnabled() && Main.game.getPlayer().hasPenis()) {
					if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("Когтистый кунилингус", "Поскольку ты таур, тебе трудно принять удобную позу, чтобы Helena могла отработать тебе когтями...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Когтистый кунилингус",
								"Попросить Helena использовать свои когти, чтобы отдрочить тебе.",
								true, true,
								getHelenaSexManager(true,
										SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_TALONJOB")) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_FOOT_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisFoot.FOOT_JOB_SINGLE_GIVING_START, false, true));
							}
						});
					}
				}
				
				// TODO Extras:
				// Scarlett threesome
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX = new DialogueNode("Завершено", "Похоже, с Helena хватит, и она отстраняется...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти",
						Main.game.getPlayer().hasPenis()
							?"Решить не лишать Helena девственности сегодня ночью, и после того, как вы полежите рядом, сказать ей, что тебе пора уходить."
							:"Решить не делать сегодня с Helena ничего больше, и после того, как вы полежите рядом, сказать ей, что тебе пора уходить.",
						DATE_APARTMENT_LEAVE) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_LEAVE"));
					}
				};
				
			} else if(index==1) {
				return new Response("Принять душ и поспать",
						"Решить не лишать Helena девственности, а вместо этого отправиться с ней в душ, после чего лечь спать в её постель вместе с ней.<br/>[style.italicsGood(Это очистит <b> максимум "+Units.fluid(500)+"</b> жидкостей из всех отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
						DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				if(index==2) {
					return new ResponseSex(
							"Лишить девственности (как саб)",
							"Позволить Helena потерять девственность в её собственном темпе, позволив ей оседлать твой член.<br/>[style.italicsSub(Это навсегда даст Helena фетиш доминанта!)]",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_HELENA_DOM")) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_DOMINANT));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
							}
							Main.game.getNpc(Helena.class).removePersonalityTrait(PersonalityTrait.INNOCENT);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
						}
					};
					
				} else if(index==3) {
					return new ResponseSex(
							"Лишить девственности (как доминант)",
							(Main.game.getPlayer().isTaur()
									?"Насадить на Helena и лишить её девственности в позе догги-стайл."
									:"Лишить Helena девственности в миссионерской позе.")
								+ "<br/>[style.italicsSub(Это навсегда привьет Helena фетиш подчинения!)]",
							true, true,
							getHelenaSexManager(false,
									Main.game.getPlayer().isTaur()
										?SexPosition.ALL_FOURS
										:SexPosition.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.ALL_FOURS
										:SexSlotLyingDown.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.BEHIND
										:SexSlotLyingDown.MISSIONARY,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_HELENA_SUB")) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_SUBMISSIVE));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
							}
							Main.game.getNpc(Helena.class).removePersonalityTrait(PersonalityTrait.INNOCENT);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
					
				} else if(index==4) {
					return new ResponseSex(
							"Сломать её",
							(Main.game.getPlayer().isTaur()
									?"Насадить на Helena и садистски лишить её девственности в позе догги-стайл."
									:"Садистски лишить Helena девственности в миссионерской позе.")
								+ "<br/>[style.italicsSub(Это навсегда привьет Helena фетиши подчинения и мазохизма!)]",
							Util.newArrayListOfValues(Fetish.FETISH_SADIST),
							null,
							CorruptionLevel.FIVE_CORRUPT,
							null,
							null,
							null,
							true, true,
							getHelenaSexManager(false,
									Main.game.getPlayer().isTaur()
										?SexPosition.ALL_FOURS
										:SexPosition.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.ALL_FOURS
										:SexSlotLyingDown.LYING_DOWN,
									Main.game.getPlayer().isTaur()
										?SexSlotAllFours.BEHIND
										:SexSlotLyingDown.MISSIONARY,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_HELENA_SUB_SADIST")) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_SUBMISSIVE));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_MASOCHIST));
							Main.game.getNpc(Helena.class).removePersonalityTrait(PersonalityTrait.INNOCENT);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				return DATE_APARTMENT_BEDROOM.getResponse(responseTab, index-1);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX = new DialogueNode("Завершено", "Похоже, с Helena хватит, и она отстраняется...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_FROM_NEST");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) { // Helena & the player have entered her bedroom directly from the nest, and so should return there:
				if(index==1) {
					return new Response("Уйти",
							"Сделать так, как просит Helena, и выйти из её квартиры.",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_LEAVE_NEST"));
							
							Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
							Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
							Main.game.getNpc(Helena.class).equipClothing();
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaBedroomFromNest, false);
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("Уйти",
							"Сказать Helena, что тебе пора уходить...",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_LEAVE"));
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
						}
					};
					
				} else if(index==1) {
					return new Response("Принять душ и поспать",
							"Присоединиться к Helena в душе, прежде чем лечь спать в её постель вместе с ней.<br/>[style.italicsGood(Очистить <b> максимум "+Units.fluid(500)+"</b> жидкостей из всех отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
							DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().cleanAllClothing(false, false);
			Main.game.getPlayer().cleanAllDirtySlots(true);
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			((Helena)Main.game.getNpc(Helena.class)).applyDressForMorning();
		}
		@Override
		public int getSecondsPassed() {
			return 10 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Проснуться",
						"Следующее утро ты просыпаешься в постели Helena...",
						DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_WAKE_UP);
			} 
			return null;
		}
	};
	

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_WAKE_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(9*60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_WAKE_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти",
						"Сказать Helena, что тебе пора уходить...",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_LEAVE"));
					}
				};
				
			} else if(index==1) {
				return new Response("Завтрак",
						"Принять предложение Helena позавтракать вместе перед уходом.",
						DATE_APARTMENT_BREAKFAST) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_BREAKFAST"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			} else if(index==2 && ((Helena)Main.game.getNpc(Helena.class)).isSlutty()) {
				if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					return new ResponseSex(
							"Утренний секс",
							"Позволить Helena взять инициативу в свои руки и заняться с ней сексом.",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
									null,
									Util.newHashMapOfValues()),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_COWGIRL")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueMouth.KISS_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Утренний секс",
							"Взять инициативу в свои руки и заняться сексом с Helena.",
							true, true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
									null,
									Util.newHashMapOfValues()),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING,
							UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_MISSIONARY")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(!Main.game.getPlayer().isTaur()) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueMouth.KISS_START, false, true));
							}
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING = new DialogueNode("Завершено", "Похоже, с Helena хватит, и она отстраняется...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().cleanAllClothing(false, false);
			Main.game.getPlayer().cleanAllDirtySlots(true);
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти",
						"Сказать Helena, что тебе пора уходить...",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_LEAVE"));
					}
				};
				
			} else if(index==1) {
				return new Response("Завтрак",
						"Принять предложение Helena позавтракать вместе перед уходом.",
						DATE_APARTMENT_BREAKFAST) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_BREAKFAST"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BREAKFAST = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_DINING_ROOM);
			Main.game.getNpc(Helena.class).setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_DINING_ROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Позавтракать",
						"Насладиться завтраком с Helena, прежде чем отправиться обратно в Dominion.",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BREAKFAST_LEAVE"));
					}
				};
			}
			return null;
		}
	};
	
	
}
