package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class HarpyNestHelena {
	
	private static float randomChance = 0f;
	
	private static SexManagerInterface getScarlettSexManager(AbstractSexPosition position,
			SexSlot scarlettSlot,
			SexSlot playerSlot,
			SexType scarlettPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap,
			String publicSexStartDescription) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override 
			public String getPublicSexStartingDescription() {
				return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
							+ publicSexStartDescription
						+ "</p>";
			}
			@Override
			public String getRandomPublicSexDescription() {
				return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
							+ UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
								UtilText.returnStringAtRandom(
									"Последовательницы Scarlett ухмыляются и время от времени отпускают комментарии по поводу твоих успехов.",
									"Одна из гарпий начинает непристойно нашептывать что-то подруге, наблюдая за вашим сексом.",
									"Несколько гарпий комментируют твои старания.",
									"Ухмыляясь и хихикая друг с другом, группа гарпий наблюдает за тем, как ты продолжаешь трахать [npc.name].",
									"Ты бросаешь взгляд в сторону и видишь, как одна из гарпий ласкает себя, наблюдая за тем, как ты и Scarlett жадно трахаетесь."))
						+"</p>";
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(partner.getWorldLocation()==WorldType.HARPY_NEST) { // If this is a scene in the nest, Scarlett stops after cumming.
					return super.isPartnerWantingToStopSex(partner);
				}
				return Main.sex.isSatisfiedFromOrgasms(partner, true) && (Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true) || Main.sex.getNumberOfOrgasms(partner)>=3);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.ONGOING_ONLY;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return character.getWorldLocation()!=WorldType.HARPY_NEST;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return character.getWorldLocation()!=WorldType.HARPY_NEST;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isCharacterStartNaked(GameCharacter character) {
				return !character.isPlayer() && character.getWorldLocation()==WorldType.HELENAS_APARTMENT;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer() && scarlettPreference!=null) {
					return scarlettPreference;
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
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}
	
	private static void applyScarlettFuckedEffects() {
		Main.game.getNpc(Scarlett.class).returnToHome();
		Main.game.getNpc(Scarlett.class).equipClothing();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettGoneHome, true);
		if(Main.game.getNpc(Scarlett.class).hasVagina()) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer() || Math.random()<0.8f) { // If Scarlett likes the player, she won't let anyone else get her pregnant. Also 80% chance for her to force her followers to pull out or use a condom.
				Main.game.getNpc(Scarlett.class).calculateGenericSexEffects(
						true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED, GenericSexFlag.PREVENT_CREAMPIE);
			} else {
				Main.game.getNpc(Scarlett.class).calculateGenericSexEffects(
						true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
			}
		}
	}
	
	public static final DialogueNode HELENAS_NEST_EXTERIOR = new DialogueNode("Гнездо Helena", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_STORM");
			} else if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_SLEEPING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
					return new Response("Helena",
							"Стая Helena укрылась в зданиях под её гнездом. Тебе придется вернуться после того, как пройдет аркана-буря.",
							null);
					
				} else if(!Main.game.isExtendedWorkTime()) {
					return new Response("Встретиться с Helena",
							Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)
								?"И Helena, и ее стая спят в зданиях под ее гнездом. Если вы хотите поговорить с Helena, вам стоит отправиться в ее лавку в Переулке Рабов."
								:"И Helena, и ее стая спят в зданиях под ее гнездом. Вам придется вернуться днем, если вы хотите поговорить с ней.",
							null);
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
						return new Response("Helena", "Подойдите к высокой платформе, чтобы встретиться с Helena.", HELENAS_NEST_MAIN_QUEST);
						
					} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA) && Main.game.getCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						return new Response("Helena", "Подойдите к высокой платформе, чтобы встретиться с Helena.", HELENAS_NEST);
						
					}  else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)) {
						return new Response("Helena", "Helena улетела в Переулок Рабов! Вам придется разыскать ее там.", null);
						
					} else {
						return new Response("Helena", "У вас нет причин разговаривать с Helena.", null);
					}
				}
				
			} else if(index==2) {
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
						return new Response("Scarlett",
								"Поскольку над головой сейчас бушует аркана-шторм, Scarlett и остальные обитатели гнезда укрылись в зданиях под её гнездом. Тебе придётся вернуться, когда шторм утихнет, если ты хочешь поговорить с ней.",
								null);
						
					} else if(!Main.game.isExtendedWorkTime()) {
						return new Response("Scarlett",
								"И Scarlett, и остальная стая Helena спят в зданиях под её гнездом. Тебе придётся вернуться днём, если ты хочешь поговорить с ней.",
								null);
						
					} else {
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
							return new Response("Scarlett", "Направляйся туда, где Scarlett окружена толпой арпий, и поздоровайся.", HELENAS_NEST_MEETING_SCARLETT);
						}
						return new Response("Scarlett", "Направляйся туда, где сидит Scarlett, и поздоровайся.", HELENAS_NEST_MEETING_SCARLETT);
					}
				}
				
			} else if(index==5 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateFirstDateComplete)) {
				return new Response("Dominion", "Воспользуйся лифтом, чтобы спуститься через «Золотое перо» и выйти в Dominion.", HelenaHotel.HOTEL_TRAVEL_TO_DOMINION) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST = new DialogueNode("Гнездо Helena", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Горе Scarlett", "Рассказать Helena о том, что Scarlett не справилась со своим работорговым бизнесом.", HELENAS_NEST_MAIN_QUEST_SCARLETT);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_SCARLETT = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Без наказания", "Не забирать наказание Scarlett себе.", HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==2) {
				return new Response("Принять наказание", "Предложить забрать наказание Scarlett себе.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.punishedByHelena);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Покинуть гнездо Helena.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Полететь за ней", "Взлететь и полететь вслед за Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Полететь за ней", "Ты не можешь летать, так что тебе придется добираться до Переулка Рабов пешком.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Терпеть", "Постараться не шуметь и сносить наказание.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==2) {
				return new Response("Барахтаться", "Начать извиваться и вскрикивать от дискомфорта.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==3) {
				return new Response("Умолять о большем", "Умолять о наказании.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Покинуть гнездо Helena.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Полететь за ней", "Взлететь и полететь вслед за Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Полететь за ней", "Ты не можешь летать, так что тебе придется добираться до Переулка Рабов пешком.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Покинуть гнездо Helena.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Полететь за ней", "Взлететь и полететь вслед за Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Полететь за ней", "Ты не можешь летать, так что тебе придется добираться до Переулка Рабов пешком.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Покинуть гнездо Helena.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
						
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Полететь за ней", "Взлететь и полететь вслед за Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("Полететь за ней", "Так как твоя спутница не может летать, вам придется добираться до Переулка Рабов пешком...", null);
					} else {
						return new Response("Полететь за ней", "Так как ты не можешь летать, тебе придется добираться до Переулка Рабов пешком...", null);
					}
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT = new DialogueNode("Гнездо Helena", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Лавка Scarlett", "Вы прибыли в лавку Scarlett.", PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	// Meeting with Helena after completing her romance quest:
	
	public static final DialogueNode HELENAS_NEST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Попрощаться с Helena и покинуть её гнездо.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
						}
					}
				};
				
			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_NEST_TALK) {
					return new Response("Поговорить", "Вы уже разговариваете с Helena...", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaNestTalkedTo)) {
					return new Response("Поговорить", "Вы уже провели некоторое время, болтая с Helena в её гнезде сегодня...", null);
				}
				return new Response("Поговорить", "Спросить у Helena, как у неё дела.", HELENAS_NEST_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaNestTalkedTo, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 2));
					}
				};
				
			} else if(index==2 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaNestFucked)) {
					return new Response("Квартира", "Вы уже занимались сексом с Helena в её гнезде сегодня, и у неё нет времени на повторение...", null);
				}
				return new Response("Квартира", "Принять предложение Helena «показать вам её квартиру».<br/>[style.italicsSex(Это приведет к сексу с ней...)]", HELENAS_NEST_APARTMENT_BEDROOM) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaNestFucked, true);
						if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_NEST.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_NEST_APARTMENT_BEDROOM = new DialogueNode("Спальня Helena", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaBedroomFromNest, true);
			((Helena)Main.game.getNpc(Helena.class)).applyLingerie();
		}
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_APARTMENT_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HelenaHotel.DATE_APARTMENT_BEDROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_HELENA_QUEST_COMPLETE"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT"));
			}
			Main.game.getNpc(Scarlett.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			if(index==0) {
				return new Response("Уйти",
						((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()
							?"Сказать Scarlett, что вы всего лишь хотели заглянуть поздороваться и что вам пора уходить."
							:"Не желая терпеть её ужасное отношение, ты говоришь Scarlett, что собираешься уходить.",
						HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK_POST_QUEST"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK"));
						}
					}
				};
			}
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					responses.add(new Response("Слуга",
							"Поскольку Scarlett не испытывает к тебе влечения, она не хочет, чтобы ты прислуживал ей...",
							null));
					
				} else if(Main.game.getDayMinutes()>19*60) {
					responses.add(new Response("Слуга",
							"День уже слишком поздний, чтобы начинать работать слугой у Scarlett. Тебе стоит попробовать в другой раз до [units.time(19)]...",
							null));
					
				} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) // Performing oral
						|| (Main.game.getNpc(Scarlett.class).hasVagina() && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) // Fucking Scarlett
						|| (Main.game.getNpc(Scarlett.class).hasVagina() && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) // Scissoring
						|| (Main.game.isAnalContentEnabled() && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) // Fucking Scarlett's ass
						|| (Main.game.getNpc(Scarlett.class).hasPenis() && Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) // Getting anally fucked
						|| (Main.game.getNpc(Scarlett.class).hasPenis() && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) // Getting fucked
						) {
					responses.add(new Response("Слуга",
							((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()
								?"Сказать Scarlett, что ты хотел бы отплатить ей за доброту, поработав её слугой в течение дня."
								:"Поработать слугой у Scarlett в течение дня, чтобы получить шанс трахнуть её.",
							HELENAS_NEST_SCARLETTS_SERVANT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getPlayer().removeAllCompanions(true);
						}
					});
					
				} else {
					responses.add(new Response("Слуга",
							"Чтобы работать слугой у Scarlett, тебе нужно иметь возможность дотянуться до своего рта или гениталий...",
							null));
				}
				
				if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.scarlettRelaxed)) {
						responses.add(new Response("Расслабиться",
								"Scarlett уже провела с тобой время сегодня. Если ты хочешь провести с ней больше времени, тебе придется вернуться завтра.",
								null));
					} else {
						responses.add(new Response("Расслабиться",
								"Принять приглашение Scarlett присесть и немного отдохнуть вместе с ней.",
								HELENAS_NEST_SCARLETT_RELAX) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettRelaxed, true);
							}
						});
					}
				}
				
			} else {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) { // Scarlett has a penis (this should always be the case, as Scarlett only loses her vagina after Helena's romance quest is complete):
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
							responses.add(new Response("Предложить задницу", "Scarlett не испытывает к тебе влечения, поэтому она не хочет заниматься с тобой сексом.", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							responses.add(new Response("Предложить задницу", "Scarlett интересует только то, чтобы оттрахать твой зад, но так как у нее нет к нему доступа, она не хочет заниматься с тобой сексом.", null));
							
						} else {
							responses.add(new ResponseSex("Предложить задницу",
									"Сказать Scarlett, что если она этого хочет, то она, конечно, может оттрахать твой зад.",
									true, false,
									new SMAllFours(
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.equals(Main.game.getNpc(Scarlett.class))) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
											}
											return character.getMainSexPreference(targetedCharacter);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.equals(Main.game.getNpc(Scarlett.class));
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
											map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
											return map;
										}
									},
									null,
									null,
									AFTER_SCARLETT_SEX,
									UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX")) {
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
								}
							});
						}
						
					} else { // If anal content is off, Scarlett will fuck the player's pussy or receive oral:
						if(Main.game.getPlayer().hasVagina()) {
							if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
								responses.add(new Response("Предложить киску", "Scarlett не испытывает к тебе влечения, поэтому она не хочет заниматься с тобой сексом.", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								responses.add(new Response("Предложить киску", "Scarlett интересует только то, чтобы оттрахать твою киску, но так как у нее нет к ней доступа, она не хочет заниматься с тобой сексом.", null));
								
							} else {
								responses.add(new ResponseSex("Предложить киску",
										"Сказать Scarlett, что если она этого хочет, то она, конечно, может оттрахать твою киску.",
										true, false,
										new SMAllFours(
												Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
											@Override
											public boolean isPublicSex() {
												return false;
											}
											@Override
											public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
												return getMainSexPreference(character, targetedCharacter);
											}
											@Override
											public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
												if(character.equals(Main.game.getNpc(Scarlett.class))) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
												return character.getMainSexPreference(targetedCharacter);
											}
											@Override
											public boolean isCharacterStartNaked(GameCharacter character) {
												return character.equals(Main.game.getNpc(Scarlett.class));
											}
											@Override
											public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
												Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
												map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
												return map;
											}
										},
										null,
										null,
										AFTER_SCARLETT_SEX,
										UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX_VAGINA")) {
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(
												new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
									}
								});
							}
							
						} else {
							if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
								responses.add(new Response("Предложить оральный секс", "Scarlett не испытывает к тебе влечения, поэтому она не хочет заниматься с тобой сексом.", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								responses.add(new Response("Предложить оральный секс", "Scarlett интересует только минет, но так как у нее нет доступа к твоему рту, она не хочет заниматься с тобой сексом.", null));
								
							} else {
								responses.add(new ResponseSex("Предложить оральный секс",
										"Скажи Scarlett, что если она этого хочет, то ты, конечно же, отсосешь ей член.",
										true, false,
										new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotStanding.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
											@Override
											public boolean isPublicSex() {
												return false;
											}
											@Override
											public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
												return getMainSexPreference(character, targetedCharacter);
											}
											@Override
											public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
												if(character.equals(Main.game.getNpc(Scarlett.class))) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												}
												return character.getMainSexPreference(targetedCharacter);
											}
											@Override
											public boolean isCharacterStartNaked(GameCharacter character) {
												return character.equals(Main.game.getNpc(Scarlett.class));
											}
											@Override
											public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
												Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
												map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
												return map;
											}
										},
										null,
										null,
										AFTER_SCARLETT_SEX,
										UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX_ORAL")) {
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(
												new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
									}
								});
							}
						}
					}
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				responses.add(new Response("Helena", "Скажи Scarlett, что Helena просит её вернуться в её лавку в Переулке Рабов.", HELENAS_NEST_MEETING_SCARLETT_TO_SHOP) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
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
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT_TO_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отступить", "Теперь, когда твоя работа здесь закончена, остается только отступить и приготовиться к возвращению в лавку Helena в Переулке Рабов...", HELENAS_NEST_EXTERIOR);
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Полететь за ней", "Взлететь и полететь за Scarlett.", ScarlettsShop.ROMANCE_SHOP_CORE) {
						@Override
						public void effects() {
							// Move them both here to make sure they haven't gone due to time ticking over into night time when player arrives:
							Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
							Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP_FLY_AFTER"));
						}
					};
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("Полететь за ней", "Так как твоя спутница не умеет летать, ты не можешь полететь за Scarlett...", null);
					} else {
						return new Response("Полететь за ней", "Так как ты не умеешь летать, ты не можешь полететь за Scarlett...", null);
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SEX = new DialogueNode("Завершено", "На данный момент Scarlett сыта по горло...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0
					|| Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH))>0) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX_ORAL");
				
			} else if(Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX_VAGINA");
			}
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Сделай как говорит Scarlett, и покинь гнездо...", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETT_RELAX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response("Квартира", "Поскольку Scarlett не испытывает к тебе влечения, она не пригласит тебя провести время в её комнате...", null);
				}
				return new Response("Квартира",
						"Принять приглашение Scarlett провести с ней немного времени в её комнате.",
						HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeAllCompanions(true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX_APARTMENT"));
					}
				};
				
			} else if(index==2) {
				return new Response(
						!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())
							?"Уйти"
							:"Отклонить",
						!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())
							?"Попрощаться со Scarlett и выйти из гнезда Helena."
							:"Сказать Scarlett, что сегодня у тебя есть другие важные дела, после чего попрощаться и выйти из гнезда Helena.",
						HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX_LEAVE"));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			responses.add(new Response("Массаж спины", "Решить помассировать спину Scarlett...", HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
				}
			});
			
			responses.add(new Response("Уход за крыльями", "Решить привести в порядок крылья Scarlett...", HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
				}
			});
			
			if(Main.game.isFootContentEnabled()) {
				responses.add(new Response("Когти", "Решить помассировать птичьи лапки Scarlett...", HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				});
			}

			if(index!=0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE"));
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingBlockingCoverableAreaAccess(CoverableArea.BACK, false), true, Main.game.getNpc(Scarlett.class));
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сдерживаться", "Решить сдержаться и подождать награды от Scarlett позже.", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_HOLD_BACK"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Минет", "Поскольку вы не можете дотянуться ртом, вы не в силах отсосать у Scarlett...", null);
					}
					return new ResponseSex(
							"Минет",
							"Возьмите свою награду прямо сейчас и отсосите член Scarlett, пока остальные гарпии продолжают баловать её...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Последовательницы Scarlett-гарпии продолжают баловать её, пока вы принимаетесь сосать её член..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Куннилингус", "Поскольку вы не можете дотянуться ртом, вы не в силах отсосать у Scarlett...", null);
					}
					return new ResponseSex(
							"Куннилингус",
							"Возьмите свою награду прямо сейчас и отсосите киску Scarlett, пока остальные гарпии продолжают баловать её...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Последовательницы Scarlett-гарпии продолжают баловать её, пока вы принимаетесь лизать её киску..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сдерживаться", "Решить сдержаться и подождать награды от Scarlett позже.", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_HOLD_BACK"));
					}
				};
				
			} else if(index==2)  {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"Ручная стимуляция",
							"Возьмите свою награду прямо сейчас и отдрочите на член Scarlett, пока остальные гарпии продолжают баловать её...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS))),
									"Последовательницы Scarlett-гарпии продолжают баловать её, пока вы принимаетесь дрочить на её член..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_HANDJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), FingerPenis.COCK_MASTURBATING_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Ласкать пальцами",
							"Возьмите свою награду прямо сейчас и потыкайте пальцами в киску Scarlett, пока остальные гарпии продолжают баловать её...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))),
									"Последовательницы Scarlett-гарпии продолжают баловать её, пока вы принимаетесь играть пальцами в её киске..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_FINGERING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), FingerVagina.FINGERING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сдерживаться", "Решить сдержаться и подождать награды от Scarlett позже.", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_HOLD_BACK"));
						Main.game.getNpc(Scarlett.class).setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Минет", "Поскольку вы не можете дотянуться ртом, вы не в силах отсосать у Scarlett...", null);
					}
					return new ResponseSex(
							"Минет",
							"Возьмите свою награду прямо сейчас и отсосите член Scarlett, пока остальные гарпии продолжают баловать её...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Последовательницы Scarlett-гарпии продолжают баловать её, пока вы принимаетесь сосать её член..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Куннилингус", "Поскольку вы не можете дотянуться ртом, вы не в силах отсосать у Scarlett...", null);
					}
					return new ResponseSex(
							"Куннилингус",
							"Возьмите свою награду прямо сейчас и отсосите киску Scarlett, пока остальные гарпии продолжают баловать её...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Последовательницы Scarlett-гарпии продолжают баловать её, пока вы принимаетесь лизать её киску..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().hasPenis()) {
					return new Response("Когтистый кунилингус", "Так как у тебя нет пениса, ты не можешь получить когтистый кунилингус от Scarlett...", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Когтистый кунилингус", "Так как ты не можешь дотянуться до своего пениса, ты не можешь получить когтистый кунилингус от Scarlett...", null);
				}
				return new ResponseSex(
						"Когтистый кунилингус",
						"Забрать награду прямо сейчас и позволить Scarlett использовать свои когти, чтобы оттрахать тебя, пока остальные гарпии продолжают баловать её...",
						true,
						false,
						getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
								"Последовательницы Scarlett продолжают баловать её, пока она дрочит тебя своими птичьими когтями..."),
						null,
						null,
						AFTER_SCARLETT_SERVANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_TALONJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SERVANT_SEX = new DialogueNode("Завершено", "На данный момент Scarlett сыта по горло...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Продолжить путь к входу в гнездо Helena.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						applyScarlettFuckedEffects();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			randomChance = (float) Math.random();
			Main.game.getNpc(Scarlett.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60) * 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Выйти из этого и сказать Scarlett, что ты собираешься уходить...", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE);
				
			} else if(index==1) {
				return new Response("Целовать ноги",
						"Поцеловать ступни Scarlett.<br/>[style.italicsExcellent(Если ты это сделаешь, она определенно выберет тебя своим партнером!)]",
						HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_KISS_FEET"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==2) {
				return new Response("Склониться",
						"Поклониться перед Scarlett.<br/>[style.italicsGood(Если ты это сделаешь, она, скорее всего, выберет тебя своим партнером, но это не гарантировано.)]",
						randomChance<0.75f
							?HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX
							:HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						if(randomChance<0.75f) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_BOW_DOWN"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_BOW_DOWN_NOT_CHOSEN"));
						}
					}
				};
				
			} else if(index==3) {
				return new Response("Лесть",
						"Сделать всё возможное, чтобы льстить Scarlett и впечатлить её.<br/>[style.italicsMinorGood(Если ты это сделаешь, вероятность того, что она выберет тебя своим партнером, будет примерно пятьдесят на пятьдесят.)]",
						randomChance<0.5f
							?HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX
							:HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						if(randomChance<0.5f) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_FLATTER"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_FLATTER_NOT_CHOSEN"));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Выйти из гнезда Helena.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						applyScarlettFuckedEffects();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyScarlettFuckedEffects();
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
				return new Response("Уйти", "Покинуть гнездо и надеяться, что Scarlett выберет тебя в следующий раз, когда ты решишь стать её слугой...", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).returnToHome();
			Main.game.getPlayer().setLocation(Main.game.getNpc(Scarlett.class), false);
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
			
			responses.add(new ResponseSex(
					"Без предпочтений",
					"Сказать Scarlett, что тебе всё равно, как именно она будет тебя трахать, и что она может решить это по ходу дела...",
					true,
					false,
					getScarlettSexManager(SexPosition.STANDING, SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_SUBMISSIVE,
							null,
							Util.newHashMapOfValues(),
							""),
					null,
					null,
					AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
					UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NO_PREFERENCE")) {
			});
			
			if(Main.game.getNpc(Scarlett.class).hasPenis()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Минет", "Так как ты не можешь дотянуться до своего рта, ты не можешь сосать член Scarlett...", null));
					
				} else {
					responses.add(new ResponseSex(
							"Минет",
							"Сказать Scarlett, что хочешь сосать её член.",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									""),
							null,
							null,
							AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					});
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						responses.add(new Response("Анал", "Так как ты не можешь добраться до своей задницы, ты не можешь попросить Scarlett оттрахать тебя в анус...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Анал",
								"Попросить Scarlett оттрахать твой зад.",
								true,
								false,
								getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_ANAL")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
						});
					}
				}
				
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Трахнутый", "Так как ты не можешь получить доступ к своей киске, ты не можешь попросить Scarlett оттрахать тебя...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Трахнутый",
								"Попросить Scarlett оттрахать твою киску.",
								true,
								false,
								getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_VAGINAL")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
				}
				
			} else { // Scarlett has a pussy:
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Куннилингус", "Так как ты не можешь дотянуться до рта, ты не можешь сделать Scarlett кунилингус...", null));
					
				} else {
					responses.add(new ResponseSex(
							"Куннилингус",
							"Сказать Scarlett, что ты хочешь отсосать ей.",
							true,
							false,
							getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									""),
							null,
							null,
							AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}

				if(Main.game.getPlayer().hasVagina()) {
					if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("Сквиз", "Так как ты таур, ты не можешь принять удобную позу для миссионерского секса ножницами с Scarlett...", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Сквиз", "Так как ты не можешь получить доступ к своей киске, ты не можешь попросить заняться с Scarlett сексом в позе ножниц...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Сквиз",
								"Спросить Scarlett, можно ли тебе заняться с ней сексом в позе ножниц.",
								true,
								false,
								getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SCISSORING")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true));
							}
						});
					}
				}
				
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Трахать её", "Так как ты не можешь получить доступ к своему члену, ты не можешь попросить оттрахать киску Scarlett...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Трахать её",
								"Сказать Scarlett, что ты хочешь оттрахать её киску.",
								true,
								false,
								getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_FUCK_HER")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
					
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Анал", "Поскольку вы не можете добраться до своего члена, вы не можете попросить оттрахать задницу Scarlett...", null));
							
						} else {
							responses.add(new ResponseSex(
									Main.game.getNpc(Scarlett.class).isAssVirgin()
										?"Лишить анальной девственности"
										:"Анал",
									Main.game.getNpc(Scarlett.class).isAssVirgin()
										?"Спросить Scarlett, можно ли лишить её анальной девственности и оттрахать её задницу."
										:"Спросить Scarlett, можно ли оттрахать её задницу.",
									true,
									false,
									getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
											""),
									null,
									null,
									AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
									UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_FUCK_HER_ASS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisAnus.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX = new DialogueNode("Завершено", "На данный момент Scarlett сыта по горло...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Сказать Scarlett, что вам пора уходить, и отправиться на лифте обратно к гнезду Helena...", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
				
			} else if(index==1) {
				return new Response("Переночевать", "Согласиться провести ночь со Scarlett.", AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(60*8) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить путь к выходу из гнезда Helena...", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};
	
}
