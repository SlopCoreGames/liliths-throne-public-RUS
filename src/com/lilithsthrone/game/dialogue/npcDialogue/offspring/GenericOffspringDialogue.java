package com.lilithsthrone.game.dialogue.npcDialogue.offspring;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8?
 * @version 0.3.1
 * @author Innoxia
 */
public class GenericOffspringDialogue {
	
	private static NPCOffspring offspring() {
		return (NPCOffspring) Main.game.getActiveNPC();
	}
	
	private static String getOffspringLabel() {
		if(offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
			return UtilText.parse(offspring(), "Разговор с [npc.Name]");
		} else {
			return "Знакомое лицо";
		}
	}
	
	private static String getTextFilePath() {
		return offspring().getWorldLocation().getOffspringTextFilePath(offspring());
	}
	

	private static String getStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(offspring().getPlayerRelationStatusDescription());
		
		// Time limitation:
		sb.append("<p style='text-align:center;'><i>");
		int tokens = Main.game.getDialogueFlags().offspringDialogueTokens;
		if(tokens>=1) {
			sb.append("У [npc.Name] есть время только на [style.italicsMinorBad(, "+Util.intToString(tokens)+" одну вещь"+(tokens>1?"s":"")+")] чтобы обсудить.");
		} else {
			sb.append("[npc.Name] [style.italicsBad(у него почти не осталось времени)] и ему нужно возвращаться к работе.");
		}
		sb.append("</i></p>");
		
		return UtilText.parse(offspring(), sb.toString());
	}
	
	public static final DialogueNode OFFSPRING_ENCOUNTER = new DialogueNode("", "Вы встречаете кого-то, чье лицо кажется вам очень знакомым...", true) {
		@Override
		public void applyPreParsingEffects(){
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).isEmpty()) {
				Main.game.initOffspringEncounter(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlaceType());
			}
			List<GameCharacter> offspringList = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			offspringList.removeIf(c->!c.isRelatedTo(Main.game.getPlayer()));
			Main.game.setActiveNPC((NPC) offspringList.get(0));
			
			if(Main.game.getPlayer().getWorldLocation()==WorldType.BAT_CAVERNS) { // If offspring is in the bat caverns, they are a mushroom hunter
				if(offspring().getItemCount(ItemType.MUSHROOM)<5) {
					offspring().addItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), 5+Util.random.nextInt(10), false, false);
					offspring().setOccupation(Occupation.NPC_MUSHROOM_FORAGER);
				}
			}
		}
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(offspring().getAffection(Main.game.getPlayer()) < AffectionLevel.NEGATIVE_TWO_DISLIKE.getMaximumValue()) {
				if (index == 1) {
					return new Response("Извиниться", UtilText.parse(offspring(), "Извиниться перед [npc.name]."), OFFSPRING_ENCOUNTER_APOLOGY) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) && !offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else {
								if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
								if(offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
							}
							setOffspringFlags();
						}
					};
					
				} else if (index == 8) {
					return new Response("Секс", UtilText.parse(offspring(), "Не может быть, чтобы [npc.she] захотел(а) заняться с тобой сексом, когда [npc.sheIs] в таком гневе."), null) {
						@Override
						public void effects() {
							setOffspringFlags();
						}
					};
					
				} else if (index == 10) {
					return new Response("Атаковать", UtilText.parse(offspring(), "Как [npc.name] смеет так с тобой разговаривать! Пора показать [npc.herHim], где её [npc.her] место!"), OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, false);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0) {
					return new Response("Уйти", UtilText.parse(offspring(), "Сказать [npc.name], что ты вернешься в другой раз."), OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNode getNextDialogue() {
								setOffspringFlags();
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								offspring().setProtectedFromArcaneStorm(false);
							}
						};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Приветствие", UtilText.parse(offspring(), "Поздороваться с [npc.name]."), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_GREETING", offspring()));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					return new Response("Обнять", UtilText.parse(offspring(), "Обнять [npc.name]."), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_HUG", offspring()));
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 3) {
					return new Response("Поцеловать", UtilText.parse(offspring(), "Обнять и поцеловать [npc.name]."), OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_KISS", offspring()));
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 4 && Main.game.isIncestEnabled()) {
					return new Response("Страстный поцелуй", UtilText.parse(offspring(), "Страстно поцеловать [npc.name] в губы, попутно лаская [npc.herHim]."), OFFSPRING_ENCOUNTER_TALKING,
							Util.newArrayListOfValues(Fetish.FETISH_INCEST),
							CorruptionLevel.FOUR_LUSTFUL,
							null,
							null,
							null) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_PASSIONATE_KISS", offspring()));
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								
							} else if(
									((Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.GYNEPHILIC)
									|| (!Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.ANDROPHILIC)
									|| (offspring().getSexualOrientation()==SexualOrientation.AMBIPHILIC))
									|| offspring().hasFetish(Fetish.FETISH_INCEST)) {
								//Incest fetish and not attracted to player, or attracted to player and no incest fetish:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
								
							} else {
								//Not attracted to player, and no incest fetish:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -20));
							}
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} if (index == 5) {
					return new Response("Отругать [npc.herHim].",
							UtilText.parse(offspring(), "Спросить [npc.name], что именно [npc.she] думает о том, чем занимается [npc.sheIs]!"
									+(offspring().getHistory()==Occupation.NPC_PROSTITUTE
											?" (Это выразит неодобрение по поводу того, что [npc.herHim] — проститутка.)"
											:" (Это выразит неодобрение по поводу того, что [npc.herHim] — грабительница.)")),
							OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SCOLDING", offspring()));
							// If masochist fetish, +5, if not, -5:
							if(offspring().hasFetish(Fetish.FETISH_MASOCHIST)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5)); 
							} else {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
							}
							setOffspringFlags();

							if(offspring().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 10) {
					return new Response("Атаковать", UtilText.parse(offspring(), "Как [npc.name] смеет так с тобой разговаривать! Пора показать [npc.herHim], где её [npc.her] место!"), OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, false);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0 && offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
					return new Response("Уйти", UtilText.parse(offspring(), "Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."), OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_LEAVE", offspring()));
								setOffspringFlags();
								offspring().setProtectedFromArcaneStorm(false);
							}	
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	private static void setOffspringFlags() {
		if(offspring().isVisiblyPregnant()) {
			offspring().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(offspring(), true);
		}
		offspring().setFlag(NPCFlagValue.flagOffspringIntroduced, true);
		Main.game.getDialogueFlags().offspringDialogueTokens = 2;
	}
	
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_APOLOGY = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_APOLOGY", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Дать [npc.name] время подумать и отправиться дальше своим путем.", OFFSPRING_ENCOUNTER_APOLOGY) {
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_FIGHT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_FIGHT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"Вы обнаруживаете, что сражаетесь со своим [npc.daughter]!",
						offspring());
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_TALKING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			offspring().setProtectedFromArcaneStorm(true);
		}
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING", offspring()));

			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().offspringDialogueTokens<=0) {
				if (index == 1) {
					return new Response("Пора идти", UtilText.parse(offspring(), "[npc.Name] начал(а) поглядывать на настенные часы, явно давая понять, что тебе пора уходить."), OFFSPRING_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_OUT_OF_TIME", offspring()));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							offspring().setProtectedFromArcaneStorm(false);
						}
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Прошлое", UtilText.parse(offspring(), "Спросить [npc.name] о происхождении [npc.her] и о том, чем [npc.she] зарабатывает на жизнь."), OFFSPRING_ENCOUNTER_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 2) {
					return new Response("Светская беседа", UtilText.parse(offspring(), "Поболтать с [npc.name] о том о сем."), OFFSPRING_ENCOUNTER_SMALL_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 3) {
					return new Response("Подбодрить", UtilText.parse(offspring(), "Подбодрить [npc.name], чтобы [npc.her] выложился(ась) на полную."), OFFSPRING_ENCOUNTER_ENCOURAGE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 4) {
					return new Response("Отругать", UtilText.parse(offspring(), "Отругать [npc.name] и велеть [npc.herHim] лучше [npc.herself]."), OFFSPRING_ENCOUNTER_SCOLD) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !offspring().isAffectionHighEnoughToInviteHome()) {
						return new Response("Предложить комнату",
								UtilText.parse(offspring(), "Вам кажется, что было бы лучше провести больше времени, узнавая [npc.name], прежде чем приглашать [npc.herHim] обратно в особняк Lilaya...<br/>[style.italics(Требуется, чтобы [npc.name] имела хотя бы "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+" немного привязанности к вам.)]"),
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("Предложить комнату",
								UtilText.parse(offspring(), "Вам нужно получить разрешение Lilaya, прежде чем приглашать [npc.name] обратно в её особняк..."),
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("Предложить комнату",
								UtilText.parse(offspring(), "У тебя нет подходящей комнаты, чтобы [npc.name] могла въехать. Сначала улучши одну из пустых комнат в доме Lilaya до «Гостевой комнаты»."),
								null);
						
					} else {
						return new Response("Предложить комнату", UtilText.parse(offspring(), "Спросить у [npc.name], хочет ли [npc.she] комнату в особняке Lilaya."), OFFSPRING_OFFER_ROOM) {
							@Override
							public void effects() {
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								if(offspring().getAffection(Main.game.getPlayer())<0) {
									Main.game.getTextEndStringBuilder().append(offspring().setAffection(Main.game.getPlayer(), 25));
								} else {
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								}
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								offspring().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							}
						};
					}

				} else if (index == 6) {
					return new Response("Ласковое имя", UtilText.parse(offspring(), "Попросить [npc.name] называть тебя другим именем."), OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				}  else if (index == 7) {
					if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
						return new Response("Вручить подарок", UtilText.parse(offspring(), "Вручить [npc.name] подарок, который у тебя с собой."), OFFSPRING_PRESENT) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 15));
								
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								Main.game.getDialogueFlags().offspringDialogueTokens--;
							}
						};
					} else {
						return null;
					}
					
				} else if (index == 8 && Main.game.isIncestEnabled()) {
					return new Response("Секс", UtilText.parse(offspring(), "Сказать [npc.name], что ты хочешь переспать с [npc.herHim]."), OFFSPRING_ENCOUNTER_SEX,
							Util.newArrayListOfValues(Fetish.FETISH_INCEST),
							CorruptionLevel.FIVE_CORRUPT,
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else if(offspring().getHistory()!=Occupation.NPC_PROSTITUTE){
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							}
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}	
					};
					
				} else if (index == 10) {
					return new Response("Атаковать", UtilText.parse(offspring(), "Пришло время показать [npc.herHim] [npc.her] истинное место в этой семье!"), OFFSPRING_ENCOUNTER_APARTMENT_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, true);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
						}
					};
					
				} else if (index == 11) {
					if(!offspring().isAllowingPlayerToManageInventory()) {
						return new Response("Инвентарь", UtilText.parse(offspring(), "[npc.Name] не настолько тебя любит, чтобы позволить тебе выбирать, что [npc.she] носит или что [npc.she] ест и пьет."), null);
					} else {
						return new ResponseEffectsOnly("Инвентарь", UtilText.parse(offspring(), "Управление инвентарем [npc.namePos].")) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_INVENTORY", offspring()));
								Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Уйти", UtilText.parse(offspring(), "Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."), OFFSPRING_ENCOUNTER) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_TALKING_LEAVE", offspring()));
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								offspring().setProtectedFromArcaneStorm(false);
							}
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_BACKGROUND = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() { // TODO use offspring().flagBackgroundProgress
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_BACKGROUND", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Прошлое", "Вы только что поговорили о происхождении [npc.namePos].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SMALL_TALK = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SMALL_TALK", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Светская беседа", "Вы только что перекинулись парой слов с [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_ENCOURAGE = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_ENCOURAGE", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Подбодрить", "Вы только что подбодрили [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SCOLD = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SCOLD", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Отругать", "Вы только что отругали [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_OFFER_ROOM = new DialogueNode("Предложить комнату", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_OFFER_ROOM", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Показать комнату", "Перенести [npc.name] в новую комнату [npc.her].", OFFSPRING_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						offspring().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(offspring());
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_OFFER_ROOM_BACK_HOME = new DialogueNode("Новая комната", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_OFFER_ROOM_BACK_HOME", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Дайте [npc.name] немного времени, чтобы освоиться в [npc.her] новой комнате. Вы можете вернуться в любое время, чтобы поговорить с [npc.herHim].", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_CHOOSE_NAME = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You decide to ask [npc.name] to call you by a different name."
						+ " At the moment, [npc.sheIs] calling you '[npc.pcName]'."
					+ "</p>"
					
					// TODO align this properly
					
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display: inline-block; padding:0 auto; margin:0 auto;vertical-align:middle;width:100%;'>"
							+ "<p style='float:left; padding:0; margin:0; height:32px; line-height:32px;'>[npc.Name] will call you: </p>"
							+ "<form style='float:left; padding:auto 0 auto 0;'><input type='text' id='offspringPetNameInput' value='"+ UtilText.parseForHTMLDisplay(offspring().getPetName(Main.game.getPlayer()))+ "'></form> <div class='SM-button' id='"+offspring().getId()+"_PET_NAME' style='float:left; width:auto; height:28px;'>Переименовать</div></div><p><i>Имена «Мама»/«Папа» и «Мамочка»/«Папочка» являются особенными и будут автоматически меняться в зависимости от пола вашего персонажа.</i></p></div><p id='hiddenFieldName' style='display:none;'></p>");
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Ласковое имя", "Ты уже просишь [npc.name] называть тебя другим именем.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_PRESENT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_PRESENT", offspring()));
			
			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Вручить подарок", "Ты уже даришь [npc.name] подарок.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_SEX = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_SEX", offspring()));

//			UtilText.nodeContentSB.append(getStatus());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Инцестуозный секс",
							"Пора показать своей [npc.daughter], на что способен [npc.her] [pc.mother]!",
							true, true,
							new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(offspring()),
								null,
								null),
							AFTER_SEX_CONSENSUAL,
							"");
					
				} else if (index == 2) {
					return new ResponseSex("Субмиссивный секс",
							"Пора позволить твоей [npc.daughter] показать тебе, на что способна [npc.she]!",
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_CONSENSUAL,
							"");
					
				} else {
					return null;
				}
				
			} else if(offspring().getHistory()==Occupation.NPC_PROSTITUTE){
				if (index == 8) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("Инцестуозный секс ("+UtilText.formatAsMoney(100, "охват")+")",
								"Заплати своей [npc.daughter] 100 огней, чтобы получить желаемое!",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(offspring()),
										null,
										null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_CONSENSUAL,
								"");
					} else {
						return new Response("Заплатить "+UtilText.formatAsMoneyUncoloured(100, "охват"), "У тебя недостаточно денег...", null);
					}
					
				} else if (index == 9) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("Сабмиссивный секс ("+UtilText.formatAsMoney(100, "охват")+")",
								"Заплати своей [npc.daughter] 100 огней, чтобы получить желаемое!",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_CONSENSUAL,
								"");
					} else {
						return new Response("Заплатить "+UtilText.formatAsMoneyUncoloured(100, "охват"), "У тебя недостаточно денег...", null);
					}
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
				
				
			} else {
				if (index == 8 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
					return new Response("Секс", "Вы только что предложили [npc.name] заняться сексом...", null);
					
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
			}
			
		}
	};
	
	public static final DialogueNode OFFSPRING_ENCOUNTER_APARTMENT_FIGHT = new DialogueNode("", "", true) {

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "OFFSPRING_ENCOUNTER_APARTMENT_FIGHT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "Вы обнаруживаете, что сражаетесь со своим [npc.daughter]!", offspring());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {

		@Override
		public String getDescription() {
			return "Вы победили [npc.name]!";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if (index == 1) {
				return new Response("Извиниться", "Может, вы зашли слишком далеко... Возможно, стоит извиниться?", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_APOLOGISE", offspring()));
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
						offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else if (index == 2 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, Fetish.FETISH_INCEST.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX", offspring()));
					
				} else {
					return new ResponseSex(
							"Изнасиловать [npc.herHim]", "[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 3 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Нежный секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX_GENTLE", offspring()));
					
				} else {
					return new ResponseSex("Изнасиловать [npc.herHim] (нежно)",
							"[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE_GENTLE", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 4 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Грубый секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SEX_ROUGH", offspring()));
					
				} else {
					return new ResponseSex("Изнасиловать [npc.herHim] (грубо)",
							"[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(offspring()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_RAPE_ROUGH", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 5 && Main.game.isIncestEnabled()) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Подчиниться",
							"Вы не совсем понимаете, что делать дальше...<br/>Возможно, будет лучше позволить [npc.name] самой решить, что делать дальше?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_SUBMIT", offspring())) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
				} else {
					return new Response("Подчиниться",
							"Ты не можешь поддаться [npc.herHim], ведь [npc.sheHasFull] не заинтересована в сексе с тобой!",
							null);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Удалить персонажа",
						UtilText.parse(offspring(), "Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
						AFTER_COMBAT_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_REMOVE_CHARACTER", offspring()));
						
						Main.game.banishNPC(offspring());
					}
				};
				
			} else if (index == 0) {
				return new Response("Уйти", "Теперь, когда вы преподали [npc.name] урок, можно отправляться дальше...", AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_VICTORY_LEAVE", offspring()));
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Поражение", "", true) {

		@Override
		public String getDescription() {
			return "Вы были повержены [npc.name]!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT", offspring());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isIncestEnabled()) {
				if (index == 1) {
					return new ResponseSex("Секс",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							"[npc.Name] навязывает вам [npc.herself]...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FIVE_CORRUPT, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Сопротивляться сексу",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(offspring()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", offspring())) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Продолжить", "Вы продолжаете свой путь...", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue(){
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
							offspring().setProtectedFromArcaneStorm(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_CONSENSUAL = new DialogueNode("После секса", "", true) {
		
		@Override
		public String getDescription(){
			return "Вы утолили свою похоть к своей [npc.daughter]... Пока что...";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_CONSENSUAL", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SEX_CONSENSUAL){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь насладился, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SEX_VICTORY){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY_LEAVING", offspring()));
						offspring().setProtectedFromArcaneStorm(false);
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Ничто не мешает вам полакомиться одеждой и вещами [npc.namePos]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BACK_ALLEYS)) {
				return new Response(
						"Удалить персонажа",
						UtilText.parse(offspring(), "Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
						AFTER_COMBAT_VICTORY){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_VICTORY_REMOVE_CHARACTER", offspring()));
						
						Main.game.banishNPC(offspring());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Свалиться", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "Вы совершенно измотаны доминирующим напором [npc.namePos] и нуждаетесь в отдыхе, чтобы прийти в себя.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_DEFEAT", offspring());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						offspring().setProtectedFromArcaneStorm(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
