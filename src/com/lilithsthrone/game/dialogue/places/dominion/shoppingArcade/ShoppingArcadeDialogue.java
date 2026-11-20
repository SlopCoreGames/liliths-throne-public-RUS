package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ShoppingArcadeDialogue {
	
	public static GameCharacter getGloryHoleCharacter() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters.get(0);
	}
	
	public static String getCoreResponseTab(int index) {
		if(index==0) {
			return "Действия";
		} else if(index==1) {
			return "Быстрое перемещение";
		}
		return null;
	}
	
	public static Response getFastTravelResponses(int responseTab, int index) {
		if(responseTab==1) {
			if (index == 1) {
				return new Response("Вход", "Быстрое перемещение к главному входу в Торговую аркаду.", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 2) {
				return new Response("Закуски от Ralph", "Быстрое перемещение к закускам Ralph.", PlaceType.SHOPPING_ARCADE_RALPHS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 3) {
				return new Response("Магазин одежды Nyan", "Быстрое перемещение в магазин одежды Nyan.", PlaceType.SHOPPING_ARCADE_NYANS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 4) {
				return new Response("Искусства Arcane", "Быстрое перемещение в Arcane Arts.", PlaceType.SHOPPING_ARCADE_VICKYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 5) {
				return new Response("Секреты Суккуба", "Быстрое перемещение в Succubi's Secrets.", PlaceType.SHOPPING_ARCADE_KATES_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 6) {
				return new Response("Игровая площадка Пикса", "Быстрое перемещение в спортзал «Игровая площадка Pix».", PlaceType.SHOPPING_ARCADE_PIXS_GYM.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_PIXS_GYM, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 7) {
				return new Response("Любимец снов", "Быстрое перемещение в Dream Lover.", PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
				
			} else if (index == 8) {
				return new Response("Дубрава", "Быстрое перемещение в ресторан «Дубрава».", PlaceType.SHOPPING_ARCADE_RESTAURANT.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT, false);
						Main.game.setResponseTab(0);
					}
				};
			}
		}
		return null;
	}
	
	// Dialogue noes:
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Торговая аркада", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Войти", "Вы проходите через вход и оказываетесь в торговой галерее.", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("Вход в галерею", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ENTRY");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Выход", "Покинуть торговую галерею.", PlaceType.DOMINION_SHOPPING_ARCADE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_SHOPPING_ARCADE, false);
						}
					};
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode ARCADE = new DialogueNode("Торговая аркада", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE"));
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START
					&& Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE_WES_REMINDER"));
			}
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START) {
						if(Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestMet)) {
								return new Response("Встретиться с Enforcer", "Вы уже встречались с ним сегодня, поэтому сможете увидеться снова не раньше завтрашнего дня...", null);
								
							} else if(Main.game.getHourOfDay()!=13) {
								return new Response("Встретиться с Enforcer", "Таинственный Enforcer велел вам встретиться с ним между [units.time(13)] и [units.time(14)], так что вам придется вернуться в это время...", null);
								
							} else {
								return new Response("Встретиться с Enforcer", "Побродить неподалеку в ожидании, пока таинственный Enforcer свяжется с вами...", WesQuest.WES_QUEST_SHOPPING_ARCADE_MEETING);
							}
						}
					}
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode GENERIC_SHOP = new DialogueNode("Лавка", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "GENERIC_SHOP");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode RESTAURANT = new DialogueNode("Дубрава", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "RESTAURANT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES = new DialogueNode("Антикварная лавка", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Войти", "Лавка антиквариата сейчас закрыта; если захотите заглянуть внутрь, придется вернуться в другое время.", null);
					}
					return new Response("Войти", "Войти в лавку антиквариата и осмотреться.", ANTIQUES_INTERIOR);
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR = new DialogueNode("Антикварная лавка", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR"));
			
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				if(index==1) {
					return new Response("Scarlett", "Подойти к сотруднику магазина и расспросить его о Scarlett.", ANTIQUES_INTERIOR_SCARLETT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_START"));
							if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || (Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_TRAP && Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_B_SHEMALE)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_TRANSFORMED"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_END"));
							
							((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), false), true, Main.game.getNpc(Scarlett.class));
						}
					};
				}
			}
			if(index==0) {
				return new Response("Выход", "Вернуться в Торговую аркаду.", ANTIQUES);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT = new DialogueNode("", "", true) {
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
				return new Response("Объяснить", "Объяснить, что, хоть вы и не из той конторы, о которой твердит бельчонок, вы <i>пришли</i>, чтобы забрать Scarlett у него.", ANTIQUES_INTERIOR_SCARLETT_EXPLAIN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_EXPLAIN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_EXPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10_000) {
					return new Response("Заплатить ("+UtilText.formatAsMoney(10_000, "охват")+")", "У вас не хватит денег, чтобы отдать бельчонку десять тысяч огней, которые он просит!", null);
					
				} else {
					return new Response("Заплатить ("+UtilText.formatAsMoney(10_000, "охват")+")", "Отдать бельчонку десять тысяч огней, которые он просит.", ANTIQUES_INTERIOR_SCARLETT_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_PURCHASED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Торговаться", "Поторговаться с бельчонком, пытаясь сбить цену, которую он запрашивает за Scarlett.", ANTIQUES_INTERIOR_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_HAGGLE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK).setSealed(false);
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK), true, Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY()));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
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
				return new Response("Продолжить",
						"Продолжайте свой путь к выходу в Торговую галерею.",
						ARCADE);
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Туалет", "Воспользоваться туалетом.", TOILETS_USE);
				
			} else if(index==2) {
				List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
				return new Response("Умыться",
						"Используйте раковины, чтобы умыться.<br/>[style.italicsGood(Это очистит вашу "+Util.inventorySlotsToParsedStringList(washSlots, Main.game.getPlayer())+", а также любую одежду в этих слотах.)]<br/>[style.italicsMinorBad(Это <b>не</b> очищает спутников.)]",
						TOILETS_WASH) {
					@Override
					public void effects() {
						for(InventorySlot slot : washSlots) {
							Main.game.getPlayer().removeDirtySlot(slot, true);
							AbstractClothing c = Main.game.getPlayer().getClothingInSlot(slot);
							if(c!=null) {
								c.setDirty(Main.game.getPlayer(), false);
							}
						}
					}
				};
				
			} else if(index==3) {
				boolean penisAvailable = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
				boolean vaginaAvailable = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
				
				if((penisAvailable && !Main.game.getPlayer().isTaur()) || vaginaAvailable) {
					return new Response("Глори-хоул (использовать)",
							"В одной из туалетных кабинок есть «дыра славы». Зайдите в кабинку и ждите, пока кто-нибудь с другой стороны обслужит вас.",
							TOILETS_GLORY_HOLE_DOM) {
						@Override
						public void effects() {
							Main.game.spawnSubGloryHoleNPC("stranger");
						}
					};
					
				} else if(penisAvailable && Main.game.getPlayer().isTaur()) {
					return new Response("Глори-хоул (использовать)",
							"Из-за особенностей телосложения твоей [pc.legRace], ты не можешь принять удобную позу, чтобы воспользоваться глори-хоулом...",
							null);
					
				} else {
					return new Response("Глори-хоул (использовать)",
							"У вас нет доступа к своим гениталиям, поэтому вы не можете получить обслуживание в «дыре славы».",
							null);
				}
				
			} else if(index==4) {
				if((Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true))
						|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
						|| (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
					return new Response("Глори-хоул (обслуживание)",
							"В одной из туалетных кабинок есть «дыра славы». Зайдите в кабинку и ждите, чтобы обслужить кого-то с другой стороны.",
							TOILETS_GLORY_HOLE_SUB) {
						@Override
						public void effects() {
							Main.game.spawnDomGloryHoleNPC("stranger");
							getGloryHoleCharacter().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						}
					};
				
				} else {
					return new Response("Глори-хоул (обслуживание)",
							"У вас нет доступа к вашему рту, гениталиям или анусу, поэтому вы не можете обслужить незнакомцев в «дырах славы».",
							null);
				}
			}
			return null;
		}
	};

	public static final DialogueNode TOILETS_USE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_USE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode TOILETS_WASH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_WASH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM = new DialogueNode("Туалеты", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.isExtendedWorkTime()) {
				return 20*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Если подумать, тебе не особо хочется, чтобы какой-то незнакомец развлекался с твоими интимными местами...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_LEAVE", getGloryHoleCharacter()));
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
				
			} else if(index==1) {
				return new ResponseSex("Начать",
						UtilText.parse(getGloryHoleCharacter(), "Сделайте так, как говорит [npc.name], и подойдите к «дыре славы»."),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(getGloryHoleCharacter(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						TOILETS_GLORY_HOLE_DOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM_POST_SEX = new DialogueNode("Туалеты", "Незнакомец быстро выходит из своей кабинки и направляется обратно в магазин, оставляя вас делать то же самое...", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Выйти из кабинки.", TOILETS) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB = new DialogueNode("Туалеты", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.isExtendedWorkTime()) {
				return 20*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Если подумать, тебе не особо хочется сосать член какому-то случайному незнакомцу...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_LEAVE", getGloryHoleCharacter()));
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
				
			} else if(index==1) {
				return new ResponseSex("Начать",
						UtilText.parse(getGloryHoleCharacter(), "Сделай так, как говорит [npc.name], и приготовься обслужить [npc.her] член."),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(getGloryHoleCharacter(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						TOILETS_GLORY_HOLE_SUB_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB_POST_SEX = new DialogueNode("Туалеты", "Незнакомец быстро выходит из своей кабинки и направляется обратно в магазин, оставляя вас делать то же самое...", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Выйти из кабинки.", TOILETS) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
			}
			return null;
		}
	};
}
