package com.lilithsthrone.game.dialogue.companions;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.2.10
 * @version 0.3.5.1
 * @author Innoxia
 */
public class OccupantDialogue {

	private static NPC occupant;
	private static GameCharacter characterForSex;
	private static NPC characterForSexSecondary;
	private static List<NPC> charactersPresent;
	private static boolean isApartment;
	private static boolean confirmKickOut;
	private static boolean initFromCharactersPresent;

	private static int sleepTimeInMinutes = 240;
	
	public static void initDialogue(NPC targetedOccupant, boolean isApartment, boolean initFromCharactersPresent) {
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
			Main.game.saveDialogueNode();
		}
		
		if(isApartment) {
			CompanionManagement.initManagement(OCCUPANT_APARTMENT, 2, targetedOccupant);
		} else if(targetedOccupant.isAtWork() || targetedOccupant.isAtHome()) {
			CompanionManagement.initManagement(OCCUPANT_START, 2, targetedOccupant);
		}
		
		occupant = targetedOccupant;
		characterForSex = targetedOccupant;

		characterForSexSecondary = null;
		charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
		charactersPresent.removeIf((npc) -> !Main.game.getPlayer().getCompanions().contains(npc) && (!npc.isSlave() || !npc.getOwner().isPlayer()) && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
		if(charactersPresent.size()>1) {
			if(charactersPresent.contains(Main.game.getPlayer().getMainCompanion()) && !occupant().equals(Main.game.getPlayer().getMainCompanion())) {
				characterForSexSecondary = (NPC) Main.game.getPlayer().getMainCompanion();
				
			} else {
				characterForSexSecondary = charactersPresent.stream().filter((npc) -> !npc.equals(occupant())).findFirst().get();
			}
		}
		
		OccupantDialogue.isApartment = isApartment;
		Main.game.getDialogueFlags().setFlag("innoxia_friendly_occupant_apartment", isApartment);
		
		
		confirmKickOut = false;
		
		OccupantDialogue.initFromCharactersPresent = initFromCharactersPresent;
	}
	
	
	private static void exitDialogue() {
		Main.game.getDialogueFlags().setManagementCompanion(null);
	}
	
	private static DialogueNode getAfterSexDialogue() {
		if(initFromCharactersPresent) {
			return CharactersPresentDialogue.AFTER_SEX;
		} else if(isApartment) {
			return APARTMENT_AFTER_SEX;
		} else {
			return AFTER_SEX;
		}
	}
	
	private static NPC occupant() {
//		return Main.game.getActiveNPC();
		return occupant;
	}
	
	private static boolean hasJob() {
		return occupant().hasJob();
	}
	
	private static void applyReactionReset() {
		if(occupant().isVisiblyPregnant()){
			occupant().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(occupant(), true);
		}
		occupant().removeFlag(NPCFlagValue.occupantHasNewJob);
		confirmKickOut = false;
	}

	public static String getTextFilePath() {
		if(occupant().isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/occupant";
		} else {
			return "misc/friendlyOccupantDialogue";
		}
	}

	private static String getThreesomeTextFilePath() {
		if(characterForSex.isRelatedTo(Main.game.getPlayer()) || (characterForSexSecondary!=null && characterForSexSecondary.isRelatedTo(Main.game.getPlayer()))) {
			return "characters/offspring/occupant";
		} else {
			return "misc/friendlyOccupantDialogue";
		}
	}
	
	private static boolean isCompanionSexPublic() {
		return !isApartment
				&& Main.game.getPlayer().getLocationPlace().isPopulated()
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS);
	}

	private static String getOccupantStartCoreContent() {
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START", occupant()));
		} else {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_WORKING", occupant()));
		}
		
		if(occupant().isVisiblyPregnant()) {
			if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_PREGNANCY_REVEAL", occupant()));
			} else {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_STILL_PREGNANT", occupant()));
			}
		}
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_PLAYER_PREGNANCY", occupant()));
			} else {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
			}
		}
		
		if(occupant().hasFlag(NPCFlagValue.occupantHasNewJob)) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_FINISH_WITH_NEW_JOB", occupant()));
			
		} else {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_START_FINISH", occupant()));
		}
		
		sb.append(occupant().getPlayerRelationStatusDescription());
		
		return sb.toString();
	}
	
	public static final DialogueNode OCCUPANT_START_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_START = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(occupant().isAsleep()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_SLEEPING", occupant()));
				
			} else {
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_ENTRY", occupant()));
				}
				UtilText.nodeContentSB.append(getOccupantStartCoreContent());
			}
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Взаимодействие";
			} else if(index == 1) {
				return UtilText.parse("[style.colourSex(Sex)]");
			} else if(index == 2) {
				return UtilText.parse("[style.colourCompanion(Управление)]");
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(occupant().isAsleep()) {
				if(responseTab == 1) {
					if(index == 1) {
						if(!occupant().hasTrait(Perk.HEAVY_SLEEPER, true)) {
							return new Response("Секс во сне", UtilText.parse(characterForSex, "[npc.Name] спит не так уж крепко, так что вы не можете заняться сексом во сне с [npc.herHim]..."), null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Секс во сне", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, и вы не можете заставить [npc.herHim] переспать с вами..."), null);
							
						} else if(characterForSex.getSexConsensualCount(Main.game.getPlayer())==0) {
							return new Response("Секс во сне", UtilText.parse(characterForSex, "Вам нужно было заняться сексом с [npc.name] хотя бы раз, прежде чем трахать [npc.herHim] во время [npc.her] сна..."), null);
							
						} else {
							return new ResponseSex("Секс во сне",
									UtilText.parse(characterForSex,
											isApartment
												?"Поскольку [npc.sheIs] крепко спит, вы могли бы прокрасться в квартиру [npc.namePos] и нежно трахать [npc.herHim], не разбудив [npc.herHim]..."
												:"Поскольку [npc.sheIs] крепко спит, вы могли бы прокрасться в комнату [npc.namePos] и нежно трахать [npc.herHim], не разбудив [npc.herHim]..."), 
									true, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											null,
											null,
											ResponseTag.PREFER_MISSIONARY) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexPace getStartingSexPaceModifier(GameCharacter character) {
											if(character.isPlayer()) {
												return SexPace.DOM_GENTLE;
											}
											return super.getStartingSexPaceModifier(character);
										}
										@Override
										public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
											Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
											map.put(ImmobilisationType.SLEEP, new HashMap<>());
											map.get(ImmobilisationType.SLEEP).put(Main.game.getPlayer(), Util.newHashSetOfValues(characterForSex));
											return map;
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					}
					
				} else {
					if(index == 1) {
						return new Response(UtilText.parse(occupant(), "Разбудить [npc.herHim]"),
								UtilText.parse(occupant(),
										"Разбудить [npc.name], чтобы вы могли взаимодействовать с [npc.herHim].<br/>[style.italicsMinorBad([npc.She] будет немного раздражена тем, что вы разбудили [npc.herHim]...)]"),
								OCCUPANT_START_NO_CONTENT) {
							@Override
							public void effects() {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_ENTRY", occupant()));
								
								occupant().wakeUp();
								
								Main.game.appendToTextEndStringBuilder(UtilText.parse(occupant(), "<p style='text-align:center;'>[style.italicsMinorBad([npc.Name] не очень-то нравится, когда её будят...)]</p>"));
								Main.game.appendToTextEndStringBuilder(occupant().incrementAffection(Main.game.getPlayer(), -1));
								Main.game.appendToTextEndStringBuilder(getOccupantStartCoreContent());
							}
						};
					}
				}
				if (index == 0) {
					return new Response("Уйти",
							UtilText.parse(occupant(), "Уйти и дать [npc.name] немного поспать."),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				return null;

			} else {
				if(responseTab == 0) {
					if (index == 1) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLife)) {
							return new Response("Жизнь", UtilText.parse(occupant(), "Спросить [npc.name] о прошлой жизни [npc.her]."), OCCUPANT_TALK_LIFE) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("Жизнь", UtilText.parse(occupant(), "Вы уже говорили с [npc.name] о прошлой жизни [npc.her] сегодня."), null);
						}
						
					} else if (index == 2) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
							return new Response(hasJob()
										?"Работа"
										:(occupant().getDesiredJobs().isEmpty()
											?"Безработица"
											:"Поиск работы"),
									UtilText.parse(occupant(),
											hasJob()
											?"Спросить [npc.name] о работе [npc.her]."
											:(occupant().getDesiredJobs().isEmpty()
												?"Спросить [npc.name], счастлива ли [npc.she] оставаться безработной."
												:"Спросить [npc.name], как продвигаются [npc.her] поиски работы.")),
									OCCUPANT_TALK_JOB) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkJob);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
							
						} else {
							return new Response("Работа", UtilText.parse(occupant(),
									hasJob()?"Вы уже спрашивали [npc.name] о [npc.her] работе сегодня.":"Вы уже спрашивали [npc.name] сегодня, как продвигаются [npc.her] поиски работы."), null);
						}
						
					} else if (index == 3) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLilaya)) {
							return new Response("Lilaya", UtilText.parse(occupant(), "Спросить [npc.name] о взаимодействии [npc.her] с Lilaya и Rose."), OCCUPANT_TALK_LILAYA) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLilaya);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("Lilaya", UtilText.parse(occupant(), "Вы уже обсуждали с [npc.name] взаимодействие [npc.her] с Lilaya и Rose сегодня."), null);
						}
						
					} else if (index == 4) {
						if(Main.game.getPlayer().getSlavesOwned().size()==0) {
							return new Response("Рабы", "У вас нет рабов, так что обсуждать это с [npc.name] не о чем.", null);
							
						} else if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkSlaves)) {
							return new Response("Рабы", UtilText.parse(occupant(), "Спросить [npc.name] о [npc.her] взаимодействиях с вашими рабынями."), OCCUPANT_TALK_SLAVES) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkSlaves);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("Рабы", UtilText.parse(occupant(), "Вы уже говорили с [npc.name] о [npc.her] взаимодействиях с вашими рабынями сегодня."), null);
						}
						
					} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
						if(!Main.game.getPlayer().hasCompanion(occupant())) {
							if(!occupant().isCompanionAvailable(Main.game.getPlayer())) {
								return new Response("Взять в отряд",
										UtilText.parse(occupant(), "[npc.Name] нельзя добавить в ваш отряд!"),
										null);
									
							} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
								return new Response("Взять в отряд",
										UtilText.parse(occupant(), "Спросить [npc.name], не хочет ли [npc.she] ненадолго составить вам компанию."),
										OCCUPANT_START){
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getPlayer().addCompanion(occupant());
									}
								};
							} else {
								return new Response("Взять в отряд",
										"Вы уже достигли лимита участников в отряде!",
										null);
							}
							
						} else {
							return new Response("Убрать из отряда",
									UtilText.parse(occupant(), "Приказать [npc.name] покинуть ваш отряд."),
									OCCUPANT_START){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().removeCompanion(occupant());
								}
							};
						}
						
					} else if (index == 6) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantHugged)) {
							if(!occupant().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
								return new Response("Обнять",
										UtilText.parse(occupant(),
												"[npc.Name] недостаточно симпатизирует вам, чтобы обнять вас.<br/>[style.italicsMinorBad(Требуется, чтобы привязанность [npc.name] к вам была не ниже '"+AffectionLevel.POSITIVE_THREE_CARING.getName()+"' towards you.)]"),
										null);
							}
							
							return new Response("Обнять", UtilText.parse(occupant(), "Крепко обнять [npc.name]."), OCCUPANT_HUG) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantHugged);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("Обнять", UtilText.parse(occupant(), "Вы уже обнимали [npc.name] сегодня."), null);
						}
						
					} else if (index == 7) {
						if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantPet)) {
							if(!occupant().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_THREE_CARING)) {
								return new Response("Поглаживания",
										UtilText.parse(occupant(),
												"[npc.Name] недостаточно симпатизирует вам, чтобы позволить гладить [npc.herHim].<br/>[style.italicsMinorBad(Требуется, чтобы привязанность [npc.name] к вам была не ниже '"+AffectionLevel.POSITIVE_FOUR_LOVE.getName()+"' towards you.)]"),
										null);
							}
							return new Response("Поглаживания", UtilText.parse(occupant(), "Ласково погладить [npc.name]."), OCCUPANT_PETTINGS) {
								@Override
								public void effects() {
									applyReactionReset();
									occupant().NPCFlagValues.add(NPCFlagValue.occupantPet);
									Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("Поглаживания", UtilText.parse(occupant(), "Сегодня ты уже успел немного погладить [npc.name]."), null);
						}
						
					} else if(index==8 && Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)!=null) {
						if(!LilayaSpa.isGuestAbleToEquipSwimwear(occupant())) {
							return new Response("Пригласить в спа",
									UtilText.parse(occupant(), "Так как [npc.name] не может надеть купальник, [npc.she] не может пойти в спа...<br/><i>Разблокировка одежды [npc.her] решила бы эту проблему...</i>"),
									null);
						}
						return new Response("Пригласить в спа",
								UtilText.parse(occupant(),
										"Спросить [npc.name], не хочет ли [npc.she] составить тебе компанию в спа, которое построили здесь, в особняке Lilaya.<br/>[style.italicsBlueLight(Ты останешься в той одежде, в которой сейчас находишься, так что если хочешь надеть подходящий купальник — самое время переодеться!)]"),
								LilayaSpa.SPA_GUEST_INVITE) {
							@Override
							public void effects() {
								applyReactionReset();
								LilayaSpa.initGuestAtSpa(occupant());
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
						
					} else if (index == 10) {
						if(hasJob()) {
							return new Response("Выселить",
									UtilText.parse(occupant(),
											"Сказать [npc.name], что ты считаешь идею [npc.her] о переезде полезной для [npc.herHim].<br/>[style.italics(В следующей сцене у тебя будет возможность сохранить или удалить этого персонажа.)]"),
									OCCUPANT_MOVE_OUT) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getDialogueFlags().setManagementCompanion(null);
								}
							};
							
						} else {
							if(confirmKickOut) {
								return new Response("Подтвердить удаление",
										UtilText.parse(occupant(),
												"Сказать [npc.name], что ты хочешь, чтобы [npc.herHim] ушла.<br/>[style.italicsBad(Навсегда удаляет этого персонажа из игры.)]"),
										OCCUPANT_KICK_OUT) {
									@Override
									public Colour getHighlightColour() {
										return PresetColour.GENERIC_NPC_REMOVAL;
									}
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getDialogueFlags().setManagementCompanion(null);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_KICK_OUT", occupant()));
										Main.game.getPlayer().removeFriendlyOccupant(occupant());
										Main.game.banishNPC(occupant());
									}
								};
								
							} else {
								return new ResponseEffectsOnly("Вышвырнуть",
										UtilText.parse(occupant(),
												"Скажите [npc.name], что хотите, чтобы [npc.herHim] ушел.<br/>[style.italicsMinorBad(После выбора этого действия вам нужно будет кликнуть еще раз, чтобы подтвердить, что вы хотите навсегда удалить этого персонажа из игры.)]")) {
									@Override
									public void effects() {
										confirmKickOut = true;
									}
								};
							}
						}
						
					} else if (index == 11) {
						return new Response(
								hasJob()
									?"Предложить смену работы"
									:"Предложить работу",
								UtilText.parse(occupant(),
										hasJob()
											?"Скажите [npc.name], что нынешняя работа [npc.her] в качестве "+UtilText.addDeterminer(occupant.getHistory().getName(occupant()))+" не подходит для [npc.herHim], и что [npc.she] стоит поискать новую работу."
											:"Скажите [npc.name], какую именно работу, по вашему мнению, стоит искать [npc.she]."),
								OCCUPANT_JOB_SUGGESTION) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
						
					} else if(index==12 && occupant().hasJob()) {
						return new Response("[style.colourBad(Уволиться)]",
								UtilText.parse(occupant(), "Скажите [npc.name], чтобы [npc.her] уволился с работы [npc.a_job] и оставался безработным, пока вы не предложите что-то другое для [npc.herHim]."),
								OCCUPANT_JOB_QUIT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_QUIT", occupant()));
								occupant().setHistory(Occupation.NPC_UNEMPLOYED);
								occupant().clearDesiredJobs();
							}
						};
						
					} else if (index == 0) {
						return new Response("Уйти", UtilText.parse(occupant(), "Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.setResponseTab(0);
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
					}
					
					return null;
				
				} else if(responseTab == 1) {
					if (index == 1) {
						if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Секс", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения..."), null);
							
						} else {
							return new ResponseSex("Секс",
									UtilText.parse(characterForSex, "Заняться сексом с [npc.name]."), 
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											Main.game.getPlayer().getCompanions(),
											null) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
								}
							};
						}
						
					} else if (index == 2) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Спитройст (спереди)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Спитройст (спереди)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Спитройст (спереди)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Спитройст (спереди)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Спитройст (спереди)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает влечения к [npc2.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
	
						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] не испытывает влечения к [npc.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
							
						} else {
							return new ResponseSex(
									"Спитройст (спереди)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Подвинься перед [npc.name] так, чтобы ты мог использовать рот [npc.her], пока [npc2.name] берет [npc.her] сзади."),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSexSecondary, Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_FRONT_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 3) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Вертел (сзади)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Вертел (сзади)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Вертел (сзади)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Вертел (сзади)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Вертел (сзади)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает влечения к [npc2.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
	
						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] не испытывает влечения к [npc.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
							
						} else {
							return new ResponseSex(
									"Вертел (сзади)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Переместись за спину [npc.name], чтобы иметь доступ к заднице [npc.her], пока [npc2.name] занята ртом [npc.her]."),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
											Util.newArrayListOfValues(characterForSex),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_BEHIND_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 4) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Бок о бок (как доминант)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Бок о бок (как доминант)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Бок о бок (как доминант)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Бок о бок (как доминант)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Бок о бок (как доминант)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else {
							return new ResponseSex("Бок о бок (как доминант)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Прижми [npc.name] и [npc2.name] к полу на четвереньки, а затем встань на колени позади [npc.name], готовый трахнуть их обеих бок о бок."),
									null, null, null, null, null, null,
									true, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 6) {
						if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Субмиссивный секс",
									UtilText.parse(characterForSex, "[npc.Name] не слишком горит желанием заниматься с тобой сексом, так что тебе придется взять на себя роль доминанта..."),
									null);
							
						} else {
							return new ResponseSex("Субмиссивный секс",
									UtilText.parse(characterForSex, "Заниматься подчиненным сексом с [npc.name]."), 
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Main.game.getPlayer().getCompanions()) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						}
						
					} else if (index == 7) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Шпиготка (спереди)", "Тебе понадобится третий человек, чтобы устроить шпиготку...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Шпиготка (спереди)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Шпиготка (спереди)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Шпиготка (спереди)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Шпиготка (спереди)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else {
							return new ResponseSex(
									"Шпиготка (спереди)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Встань на четвереньки лицом к [npc.name], чтобы [npc.she] мог использовать твой рот, пока [npc2.name] берет тебя сзади."),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSexSecondary, characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 8) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Вертел (сзади)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Шпиготка (сзади)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Шпиготка (сзади)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Шпиготка (сзади)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Шпиготка (сзади)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else {
							return new ResponseSex(
									"Шпиготка (сзади)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Встань на четвереньки и подставь задницу [npc.name], чтобы [npc.she] трахал тебя, пока [npc2.name] использует твой рот."),
									null, null, null, null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSexSecondary, characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if (index == 9) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Рядом (как саб)", UtilText.parse(characterForSex, "Тебе понадобится третий человек, чтобы тебя трахали вместе с кем-то из них или с [npc.name]..."), null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Рядом (как саб)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Рядом (как саб)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Рядом (как саб)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Рядом (как саб)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает влечения к [npc2.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
	
						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] не испытывает влечения к [npc.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
							
						} else {
							return new ResponseSex("Рядом (как саб)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Встань на четвереньки рядом с [npc2.name], чтобы [npc.name] мог опуститься на колени позади вас обоих, готовый трахать вас обоих бок о бок."),
									null, null, null, null, null, null,
									true, false,
									new SMGeneric(
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
											null,
											null,
											ResponseTag.PREFER_DOGGY) {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_AS_SUB_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					
					} else if(index==11) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSex, "Цель: <b style='color:"+characterForSex.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"Сменить целевого персонажа для группового секса.") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSex)) {
												if(i==charactersPresent.size()-1) {
													characterForSex = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get(1);
													}
												} else {
													characterForSex = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
													break;
												}
											}
										}
									}
									Main.game.updateResponses();
								}
							};
							
						} else {
							return new Response(
									UtilText.parse(characterForSex, "Цель: <b>[npc.Name]</b>"),
									"Сменить целевого персонажа для группового секса.<br/>[style.italicsBad(Чтобы разблокировать это действие, с вами должен быть спутник!)]",
									null); 
						}
						
					} else if(index==12) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSexSecondary, "Второй: <b style='color:"+characterForSexSecondary.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"Сменить второго целевого персонажа для группового секса.") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSexSecondary)) {
												if(i==charactersPresent.size()-1) {
													characterForSexSecondary = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get(1);
													}
												} else {
													characterForSexSecondary = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
												}
												break;
											}
										}
									}
									Main.game.updateResponses();
								}
							};
	
						} else {
							return new Response(
									UtilText.parse(characterForSex, "Второй: <b>[npc.Name]</b>"),
									"Сменить второго целевого персонажа для группового секса.<br/>[style.italicsBad(Чтобы разблокировать это действие, с вами должен быть спутник!)]",
									null);
						}
	
					} else if (index == 0) {
						return new Response("Уйти", UtilText.parse(occupant(), "Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.setResponseTab(0);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVING", occupant()));
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
							}
						};
						
					} else  {
						return null;
					}
					
					
				} else if(responseTab == 2) {
					return CompanionManagement.getManagementResponses(index);
					
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_LIFE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			//TODO talk about life, family, friends, stories
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_JOB = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(hasJob()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_JOB", occupant()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_JOB_HUNTING", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_LILAYA = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_LILAYA", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_TALK_SLAVES = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			String id = Util.randomItemFrom(Main.game.getPlayer().getSlavesOwned());
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_SLAVES", occupant(), slave));

			} catch (Exception e) {
				Util.logGetNpcByIdError("OCCUPANT_TALK_SLAVES.getContent()", id);
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_TALK_SLAVES_NULL_SLAVE", occupant()));
			}
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OCCUPANT_HUG = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Обнять [npc.Name]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_HUG", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OCCUPANT_PETTINGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(), "Погладить [npc.Name]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_PETTINGS", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OCCUPANT_JOB_SUGGESTION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(hasJob()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION_CHANGE", occupant()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION", occupant()));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_JOB_SUGGESTION_MECHANICS", occupant()));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Occupation> availableOccuaptions = new ArrayList<>();
			for(Occupation occ : Occupation.values()) {
				if(!occ.isAvailableToPlayer()
						&& occ.isAvailable(occupant())
						&& occ!=Occupation.NPC_UNEMPLOYED
						&& !occ.isLowlife()) {
					availableOccuaptions.add(occ);
				}
			}
			
			if(index==0) {
				return new Response("Назад",
						UtilText.parse(occupant(), "Решить не говорить [npc.name], какую работу, по вашему мнению, стоит выбрать [npc.she]."),
						isApartment
							?OCCUPANT_APARTMENT
							:OCCUPANT_START);
				
			} else if(index==1) {
				return new Response("[style.colourGood(Выбрать все)]",
						UtilText.parse(occupant(), "Выбрать все типы работ, которые [npc.name] должна искать."),
						OCCUPANT_JOB_SUGGESTION) {
					@Override
					public void effects() {
						for(Occupation occ : availableOccuaptions) {
							occupant().addDesiredJob(occ);
						}
					}
				};
				
			} else if(index==2) {
				return new Response("[style.colourBad(Не выбирать ничего)]",
						occupant().hasJob()
							?UtilText.parse(occupant(), "Не выбирать типы работ для [npc.name], из-за чего [npc.herHim] останется работать [npc.a_job].")
							:UtilText.parse(occupant(), "Не выбирать типы работ для [npc.name], из-за чего [npc.herHim] останется без работы."),
							OCCUPANT_JOB_SUGGESTION) {
					@Override
					public void effects() {
						occupant().clearDesiredJobs();
					}
				};
				
			} else if(index-3<availableOccuaptions.size()) {
				Occupation job = availableOccuaptions.get(index-3);
				String jobName = job.getName(occupant());
				if(occupant().getHistory().equals(job)) {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "[npc.Name] уже работает "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+"."),
							null);
					
				} else if(occupant().getDesiredJobs().contains(job)) {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "Сказать [npc.name], чтобы перестал искать работу в качестве "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+"."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						@Override
						public void effects() {
							occupant().removeDesiredJob(job);
						}
					};
					
				} else {
					return new Response(Util.capitaliseSentence(jobName),
							UtilText.parse(occupant(), "Сказать [npc.name], что [npc.she] стоит поискать работу в качестве "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+"."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							occupant().addDesiredJob(job);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OCCUPANT_JOB_QUIT = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(occupant(),"Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_START.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Закончить", "", true) {
		
		@Override
		public String getDescription(){
			return "Рухнуть на диван [npc.namePos] и перевести дух.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", occupant(), characterForSexSecondary);
				
			} else if(Main.sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти",
						UtilText.parse(occupant(),
								occupant().isAsleep()
									?"Оставить [npc.name] спать и выйти из [npc.her] "+(isApartment?"квартиры":"комната")+"."
									:"Дать [npc.name] немного времени на отдых."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "LEAVE_AFTER_SEX", occupant()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_KICK_OUT = new DialogueNode("Выселение", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_MOVE_OUT = new DialogueNode("Переезд", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT", occupant());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Навестить квартиру", "Сказать [npc.name], что ты бы с удовольствием посмотрел на новую квартиру [npc.her], и последовать за [npc.herHim] туда.<br/>[style.italicsGood(Сохраняет этого персонажа, перемещая его на случайную клетку улицы или бульвара в Dominion.)]",
						OCCUPANT_MOVE_OUT_APARTMENT) {
					@Override
					public void effects() {
						occupant().setRandomUnoccupiedLocation(WorldType.DOMINION, true, PlaceType.DOMINION_STREET, PlaceType.DOMINION_STREET_HARPY_NESTS, PlaceType.DOMINION_BOULEVARD);
						occupant().setHomeLocation();
						OccupantDialogue.isApartment = true;
						Main.game.getDialogueFlags().setManagementCompanion(occupant());
						Main.game.getPlayer().setLocation(occupant().getWorldLocation(), occupant().getLocation(), false);
					}
				};
				
			} else if(index==10) {
				if(confirmKickOut) {
					return new Response("Подтвердить удаление", "Сказать [npc.name], что [npc.she] пора жить своей [npc.her] жизнью.<br/>[style.italicsBad(Навсегда удаляет этого персонажа из игры.)]",
							OCCUPANT_KICK_OUT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT_REMOVE_CHARACTER", occupant()));
							Main.game.getPlayer().removeFriendlyOccupant(occupant());
							Main.game.banishNPC(occupant());
							confirmKickOut = false;
						}
					};
					
				} else {
					return new ResponseEffectsOnly("Удалить персонажа", "Сказать [npc.name], что [npc.she] пора жить своей [npc.her] жизнью.<br/>[style.italicsMinorBad(После выбора этого действия вам нужно будет кликнуть еще раз, чтобы подтвердить, что вы хотите навсегда удалить этого персонажа из игры.)]") {
						@Override
						public void effects() {
							confirmKickOut = true;
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_MOVE_OUT_APARTMENT = new DialogueNode("Переезд", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "квартира [npc.NamePos]");
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_MOVE_OUT_APARTMENT", occupant());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};

	private static String getOccupantApartmentCoreContent() {
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().getCompanions().contains(occupant())) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_AS_COMPANION", occupant()));
			
		} else if(occupant().isAtHome()) {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START", occupant()));
			
			if(occupant().isAsleep()) {
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEPING", occupant()));
				
			} else {
				if(occupant().isVisiblyPregnant() && occupant().isCharacterPossiblyFather(Main.game.getPlayer().getId())) {
					if(!occupant().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_PREGNANCY_REVEAL", occupant()));
					} else {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_STILL_PREGNANT", occupant()));
					}
				}
				
				if(Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(occupant().getId())) {
					if(!Main.game.getPlayer().isCharacterReactedToPregnancy(occupant())) {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_PLAYER_PREGNANCY", occupant()));
					} else {
						sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_CONTINUED_PLAYER_PREGNANCY", occupant()));
					}
				}
				
				sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_START_FINISH", occupant()));
				
				sb.append(occupant().getPlayerRelationStatusDescription());
			}
			
		} else {
			sb.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_NOT_AT_HOME", occupant()));
		}
		
		return sb.toString();
	}

	public static final DialogueNode OCCUPANT_APARTMENT_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "Квартира [npc.NamePos]");
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT = new DialogueNode("Переезд", "", true) {//TODO
		@Override
		public String getLabel() {
			return UtilText.parse(occupant(), "Квартира [npc.NamePos]");
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getOccupantApartmentCoreContent());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(occupant().isAtHome()) {
				if(index == 0) {
					return "Взаимодействие";
				} else if(index == 1) {
					return UtilText.parse("[style.colourSex(Секс)]");
				} else if(index == 2) {
					return UtilText.parse("[style.colourCompanion(Управление)]");
				}
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if(index==1) {
					return new Response("Уйти",
							UtilText.parse(occupant(), "Так как [npc.name] сейчас нет дома, остается только отправиться обратно в Dominion."),
							Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			
			if(occupant().isAsleep()) {
				if(index == 1) {
					return new Response(UtilText.parse(occupant(), "Разбудить [npc.herHim]"),
							UtilText.parse(occupant(),
									"Разбудить [npc.name], чтобы вы могли взаимодействовать с [npc.herHim].<br/>[style.italicsMinorBad([npc.She] будет немного раздражена тем, что вы разбудили [npc.herHim]...)]"),
							OCCUPANT_APARTMENT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_ENTRY", occupant()));
							
							occupant().wakeUp();
							
							Main.game.appendToTextEndStringBuilder(UtilText.parse(occupant(), "<p style='text-align:center;'>[style.italicsMinorBad([npc.Name] не очень-то нравится, когда её будят...)]</p>"));
							Main.game.appendToTextEndStringBuilder(occupant().incrementAffection(Main.game.getPlayer(), -1));
							Main.game.appendToTextEndStringBuilder(getOccupantApartmentCoreContent());
						}
					};
					
				} else if (index == 0) {
					return new Response("Уйти",
							UtilText.parse(occupant(), "Уйти и дать [npc.name] немного поспать."),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				return null;
			}
			
			if(responseTab == 0) {
				if (index == 1) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkLife)) {
						return new Response("Жизнь", UtilText.parse(occupant(), "Спросить [npc.name] о прошлой жизни [npc.her]."), OCCUPANT_APARTMENT_TALK_LIFE) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkLife);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Жизнь", UtilText.parse(occupant(), "Вы уже говорили с [npc.name] о прошлой жизни [npc.her] сегодня."), null);
					}
					
				} else if (index == 2) {
					if(!occupant().NPCFlagValues.contains(NPCFlagValue.occupantTalkJob)) {
						return new Response("Работа",
								UtilText.parse(occupant(), "Спросить [npc.name] о работе [npc.her]."),
								OCCUPANT_APARTMENT_TALK_JOB) {
							@Override
							public void effects() {
								applyReactionReset();
								occupant().NPCFlagValues.add(NPCFlagValue.occupantTalkJob);
								Main.game.getTextEndStringBuilder().append(occupant().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
						
					} else {
						return new Response("Работа", UtilText.parse(occupant(), "Вы уже спрашивали [npc.name] о [npc.her] работе сегодня."), null);
					}
					
				}
				else if (index == 3) {
					return new Response("Отдых",
							UtilText.parse(occupant(), "Спросить у [npc.name], можно ли перекантоваться на диване у [npc.her] четыре часа.")
							+ " А также восполнение вашего "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
							OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							sleepTimeInMinutes = 240;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};

				} else if (index == 4) {
					int timeUntilChange = Main.game.getMinutesUntilNextMorningOrEvening() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always sleeping to sunset/sunrise
					LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
					return new Response("Отдыхать до " + (Main.game.isDayTime() ? "Заката" : "Рассвета"),
							UtilText.parse(occupant(), "Спросить [npc.name], можно ли перекантоваться на диване у [npc.her] на ")
								+ (timeUntilChange >= 60 ?timeUntilChange / 60 + " часов " : " ")
								+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " минут" : "")
								+ (Main.game.isDayTime()
										? " до пяти минут после заката ("+Units.time(sunriseSunset[1].plusMinutes(5))+")."
										: " до пяти минут после рассвета ("+Units.time(sunriseSunset[0].plusMinutes(5))+").")
								+ " А также восполнение вашего "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
								OCCUPANT_APARTMENT_SLEEP_OVER){
						@Override
						public void effects() {
							sleepTimeInMinutes = timeUntilChange;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};

				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
					if(!Main.game.getPlayer().hasCompanion(occupant())) {
						
						if(!occupant().isCompanionAvailable(Main.game.getPlayer())) {
							return new Response("Взять в отряд",
									UtilText.parse(occupant(), "[npc.Name] нельзя добавить в ваш отряд!"),
									null);
								
						} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
							return new Response("Взять в отряд",
									UtilText.parse(occupant(), "Спросить [npc.name], не хочет ли [npc.she] ненадолго составить вам компанию."),
									OCCUPANT_APARTMENT){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().addCompanion(occupant());
								}
							};
							
						} else {
							return new Response("Взять в отряд",
									"Вы уже достигли лимита участников в отряде!",
									null);
						}
						
					} else {
						return new Response("Убрать из отряда",
								"Приказать [npc.name] покинуть ваш отряд.",
								OCCUPANT_APARTMENT){
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getPlayer().removeCompanion(occupant());
							}
						};
					}
					
				} else if (index == 6) {
					return new Response(
							hasJob()
								?"Предложить смену работы"
								:"Предложить работу",
							UtilText.parse(occupant(),
									hasJob()
										?"Скажите [npc.name], что нынешняя работа [npc.her] в качестве "+UtilText.addDeterminer(occupant.getHistory().getName(occupant()))+" не подходит для [npc.herHim], и что [npc.she] стоит поискать новую работу."
										:"Скажите [npc.name], какую именно работу, по вашему мнению, стоит искать [npc.she]."),
							OCCUPANT_JOB_SUGGESTION) {
						@Override
						public void effects() {
							applyReactionReset();
						}
					};

				} else if (index == 8) {
					return new Response("Установить будильник", "Установи будильник на телефоне, чтобы проснуться в определенное время.", RoomPlayer.ROOM_SET_ALARM) {
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
						}
					};

				} else if (index == 9) {
					long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
					if(alarmTime >= 0) {
						String alarmTimeStr = Main.game.getDisplayTime(LocalTime.ofSecondOfDay(alarmTime*60));
						int timeUntilAlarm = Main.game.getMinutesUntilTimeInMinutes((int)alarmTime-1)+1; // -1+1 is so we get 1440 instead of 0
						return new Response("Отдыхать до будильника (" + alarmTimeStr + ")",
								"Спросить [npc.name], можно ли перекантоваться на диване у [npc.her] на " + (timeUntilAlarm >= 60 ? timeUntilAlarm / 60 + " ч, " : "")
										+ (timeUntilAlarm % 60 != 0 ? timeUntilAlarm % 60 + " мин, " : "")
										+ " пока не прозвенит будильник. А также восстановит ваши " + Attribute.HEALTH_MAXIMUM.getName() + " и " + Attribute.MANA_MAXIMUM.getName() + ", вы также получите статус «Выспавшийся».",
								OCCUPANT_APARTMENT_SLEEP_OVER) {
							@Override
							public void effects() {
								sleepTimeInMinutes = timeUntilAlarm;
								RoomPlayer.applySleep(sleepTimeInMinutes);
							}
						};
					} else {
						return new Response("Отдыхать до будильника (не установлен)", "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Будильник не установлен!</span>", null);
					}
					
				} else if (index == 10) {
					if(confirmKickOut) {
						return new Response("Подтвердить удаление", UtilText.parse(occupant(), "Сказать [npc.name], что пора двигаться дальше и что вы больше никогда не увидите [npc.herHim].<br/>[style.italicsBad(Это навсегда удалит [npc.name] из игры!)]"),
								OCCUPANT_APARTMENT_REMOVE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
							}
							@Override
							public void effects() {
								applyReactionReset();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_REMOVE", occupant()));
								Main.game.getPlayer().removeFriendlyOccupant(occupant());
								Main.game.banishNPC(occupant());
								confirmKickOut = false;
							}
						};
						
					} else {
						return new ResponseEffectsOnly("Удалить персонажа", UtilText.parse(occupant(), "Сказать [npc.name], что пора двигаться дальше и что вы больше никогда не увидите [npc.herHim].<br/>[style.italicsMinorBad(После выбора этого действия вам нужно будет кликнуть еще раз, чтобы подтвердить, что вы хотите навсегда удалить этого персонажа из игры.)]")) {
							@Override
							public void effects() {
								confirmKickOut = true;
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Уйти", UtilText.parse(occupant(), "Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							applyReactionReset();
							exitDialogue();
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				if (index == 0) {
					return new Response("Уйти", UtilText.parse(occupant(), "Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVING", occupant()));
							applyReactionReset();
							exitDialogue();
						}
					};
					
				} else {
					return OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab == 2) {
				return CompanionManagement.getManagementResponses(index);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_TALK_LIFE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_TALK_LIFE", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_TALK_JOB = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_TALK_JOB", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_SLEEP_OVER = new DialogueNode("", "", true) {

//		@Override
//		public int getSecondsPassed() {
//			return sleepTimeInMinutes*60;
//		}
		
		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER", occupant()));
			
			UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Проснуться", "Спустя некоторое время вы просыпаетесь...", OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return OCCUPANT_APARTMENT.getLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(!occupant().isAtHome()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP_ALONE", occupant()));
			
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OCCUPANT_APARTMENT_SLEEP_OVER_WAKE_UP", occupant()));
				UtilText.nodeContentSB.append(occupant().getPlayerRelationStatusDescription());
			}
			
			
			return UtilText.parse(occupant(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return OCCUPANT_APARTMENT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!occupant().isAtHome()) {
				if (index == 1) {
					return new Response("Снаружи", "Вы снова оказываетесь на улицах Dominion.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							exitDialogue();
						}
					};
				}
				return null;
			}
			
			return OCCUPANT_APARTMENT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OCCUPANT_APARTMENT_REMOVE = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			exitDialogue();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode APARTMENT_AFTER_SEX = new DialogueNode("Закончить", "", true) {
		
		@Override
		public String getDescription(){
			return "Рухнуть на диван [npc.namePos] и перевести дух.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_THREESOME", occupant(), Main.game.getPlayer().getCompanions().get(0));

			} else if(Main.sex.getNumberOfOrgasms(occupant()) >= occupant().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX", occupant());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_AFTER_SEX_NO_ORGASM", occupant());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", 
						UtilText.parse(occupant(),
								occupant().isAsleep()
									?"Оставить [npc.name] спать и выйти из [npc.her] "+(isApartment?"квартиры":"комната")+"."
									:"Дать [npc.name] немного времени на отдых."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "APARTMENT_LEAVE_AFTER_SEX", occupant()));
						exitDialogue();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
