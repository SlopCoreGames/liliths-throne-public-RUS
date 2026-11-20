package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class HomeImprovements {

	public static GameCharacter getGloryHoleCharacter() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters.get(0);
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Строительный гипермаркет Аргуса", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "OUTSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isExtendedWorkTime()) {
					return new Response("Войти", "Войти на склад, на котором висит табличка, что он открыт для публики.", PlaceType.HOME_IMPROVEMENTS_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE, false);
						}
					};
				} else {
					return new Response("Войти", "Склад явно закрыт в данный момент. Придется вернуться позже, если хочешь попасть внутрь...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Выход", "Выйти со склада и вернуться в Dominion.", PlaceType.DOMINION_HOME_IMPROVEMENT.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HOME_IMPROVEMENT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR_PAINT_OPTIONS"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SHELVING_PREMIUM = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PREMIUM"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PREMIUM_PRICE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<ItemType.PAINT_CAN_PREMIUM.getValue()) {
						return new Response("Купить ("+UtilText.formatAsMoneyUncoloured(ItemType.PAINT_CAN_PREMIUM.getValue(), "охват")+")",
								"Хотя эта банка краски [style.colourGood(именно та, которую просила Helena)], тебе [style.colourbad(не хватает денег, чтобы купить её)]!",
								null);
					}
					return new Response("Купить ("+UtilText.formatAsMoney(ItemType.PAINT_CAN_PREMIUM.getValue(), "охват")+")",
							"Купить банку "+ItemType.PAINT_CAN_PREMIUM.getName(false)+", которая [style.colourGood(именно та, которую просила Helena)].",
							PAINT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE);
							UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.PAINT_CAN_PREMIUM.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.PAINT_CAN_PREMIUM), false, true));
							((Helena)Main.game.getNpc(Helena.class)).sellOffRemainingSlaves();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SHELVING_STANDARD = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_STANDARD"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), true);
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_STANDARD_PRICE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<ItemType.PAINT_CAN.getValue()) {
						return new Response("Купить ("+UtilText.formatAsMoneyUncoloured(ItemType.PAINT_CAN.getValue(), "охват")+")",
								"Эта банка краски [style.colourBad(совсем не та, которую просила Helena)], и даже если бы ты захотел её купить, тебе [style.colourbad(не хватит денег)]!",
								null);
					}
					return new Response("Купить ("+UtilText.formatAsMoney(ItemType.PAINT_CAN.getValue(), "охват")+")",
							"Купить банку "+ItemType.PAINT_CAN.getName(false)+", которая [style.colourBad(совсем не та, которую просила Helena)]!",
							PAINT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE);
							UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.PAINT_CAN.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.PAINT_CAN), false, true));
							((Helena)Main.game.getNpc(Helena.class)).sellOffRemainingSlaves();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PAINT_PURCHASED = new DialogueNode("", ".", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().getRace()==Race.DEMON) {
				return 5*60;
			} else {
				return 15*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "PAINT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						"Теперь, когда ты получил то, за чем пришел, можно и честь знать.",
						PlaceType.DOMINION_HOME_IMPROVEMENT.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HOME_IMPROVEMENT);
					}
				};
				
			} else if(index==2) {
				return new Response("Продолжить покупки",
						"Вы всегда можете остаться и осмотреться подольше, прежде чем уйти.",
						ENTRANCE);
			}
			return null;
		}
	};
	
	public static final DialogueNode BUILDING_SUPPLIES = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "BUILDING_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS");
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_USE");
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_WASH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM = new DialogueNode("Туалеты", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Если подумать, тебе не особо хочется, чтобы какой-то незнакомец развлекался с твоими интимными местами...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_LEAVE", getGloryHoleCharacter()));
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
						UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_START", getGloryHoleCharacter()));
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_POST_SEX", getGloryHoleCharacter());
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
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Если подумать, тебе не особо хочется сосать член какому-то случайному незнакомцу...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_LEAVE", getGloryHoleCharacter()));
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
						UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_START", getGloryHoleCharacter()));
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
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_POST_SEX", getGloryHoleCharacter());
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
