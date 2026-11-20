package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.clothing.Sticker;
import com.lilithsthrone.game.inventory.clothing.StickerCategory;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.comparators.ClothingZLayerComparator;

/**
 * @since 0.1.0
 * @version 0.4.10.10
 * @author Innoxia
 */
public class InventoryDialogue {
	
	private static final int IDENTIFICATION_PRICE = 1000;
	private static final int IDENTIFICATION_ESSENCE_PRICE = 15;
	
	private static AbstractItem item;
	private static AbstractClothing clothing;
	private static AbstractWeapon weapon;
	private static InventorySlot weaponSlot;
	private static GameCharacter owner;
	
	private static NPC inventoryNPC;
	private static InventoryInteraction interactionType;

	private static StringBuilder inventorySB = new StringBuilder();

	private static boolean buyback;

	private static int buyBackPrice;
	private static int buyBackIndex;

	public static DamageType damageTypePreview;
	
	public static List<Colour> dyePreviews;
	public static String dyePreviewPattern;
	public static List<Colour> dyePreviewPatternColours;
	
	public static Map<StickerCategory, Sticker> dyePreviewStickers;

	public static Map<String, String> getDyePreviewStickersAsStrings() {
		Map<String, String> stickerIds = new HashMap<>();
		for(Entry<StickerCategory, Sticker> entry : dyePreviewStickers.entrySet()) {
			stickerIds.put(entry.getKey().getId(), entry.getValue().getId());
		}
		return stickerIds;
	}
	
	private static void resetClothingDyeColours() {
		dyePreviews = new ArrayList<>();
		dyePreviews.addAll(clothing.getColours());
		
		dyePreviewPattern = clothing.getPattern();

		dyePreviewPatternColours = new ArrayList<>();
		dyePreviewPatternColours.addAll(clothing.getPatternColours());
		
		dyePreviewStickers = new HashMap<>(clothing.getStickersAsObjects());
	}

	private static void resetWeaponDyeColours() {
		dyePreviews = new ArrayList<>();
		dyePreviews.addAll(weapon.getColours());
		
		damageTypePreview = weapon.getDamageType();
	}
	
	private static String inventoryView() {
		inventorySB = new StringBuilder();
		
		inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(inventoryNPC, buyback));
		
		return inventorySB.toString();
	}

	private static void equipAll(GameCharacter character) {
		List<AbstractClothing> zlayerClothing = new ArrayList<>(character.getAllClothingInInventory().keySet());
		zlayerClothing.removeIf((c) -> c.isEnchantmentKnown() && c.isSealed());
		zlayerClothing.sort(new ClothingZLayerComparator().reversed());
		Set<InventorySlot> slotsTaken = new HashSet<>();

		for(AbstractClothing c : character.getClothingCurrentlyEquipped()) {
			slotsTaken.add(c.getSlotEquippedTo());
		}

		for(AbstractClothing c : zlayerClothing) {
			if(!slotsTaken.contains(c.getClothingType().getEquipSlots().get(0))) {
				Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+character.equipClothingFromInventory(c, true, character, character)+"</p>");
				slotsTaken.add(c.getClothingType().getEquipSlots().get(0));
			}
		}
	}

	private static String unequipAll(GameCharacter character) {
		StringBuilder sb = new StringBuilder();
		
//		for(int i=0; i<character.getArmRows(); i++) {
//			sb.append(character.unequipMainWeapon(i, false, character.isPlayer()));
//			sb.append(character.unequipOffhandWeapon(i, false, character.isPlayer()));
//		}
		
		List<AbstractClothing> zlayerClothing = new ArrayList<>(character.getClothingCurrentlyEquipped());
		zlayerClothing.sort(new ClothingZLayerComparator());
		
		for(AbstractClothing c : zlayerClothing) { 
			if((!Main.game.isInSex()
					|| (!c.getSlotEquippedTo().isJewellery()
							&& !c.isCondom()
							&& (!character.hasPerkAnywhereInTree(Perk.SPECIAL_SHORT_SIGHTED) || !c.getItemTags().contains(ItemTag.PRESCRIPTION_GLASSES))))
					&& !c.isMilkingEquipment()) {
				if (c.isDiscardedOnUnequip(null)) {
					character.unequipClothingIntoVoid(c, true, Main.game.getPlayer());
				} else {
					if(Main.game.isInNewWorld()) {
						character.unequipClothingIntoInventory(c, true, Main.game.getPlayer());
					} else {
						character.unequipClothingOntoFloor(c, true, Main.game.getPlayer());
					}
				}
				sb.append("<p style='text-align:center;'>"+character.getUnequipDescription()+"</p>");
			}
		}
		
		return sb.toString();
	}
	
	private static String getEnchantmentNotDiscoveredText(String item) {
		StringBuilder sb = new StringBuilder();
		sb.append("Вы еще не научились зачаровывать ");
		sb.append(item);
		sb.append(" предметы...");
		sb.append("<br/>");
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			sb.append("[style.italicsArcane(Стоит сходить и поговорить с Lilaya об эссенциях...)]");
		} else {
			sb.append("[style.italicsArcane(Чтобы узнать больше о зачаровании, вам нужно поглотить эссенцию после боя или секса, либо просто купить её...)]");
		}
		return sb.toString();
	}
	
	private static String getClothingBlockingRemovalText(GameCharacter equipTarget, String equipVerb) {
		StringBuilder sb = new StringBuilder();
		AbstractClothing blockingClothing = equipTarget.getBlockingClothing();

		sb.append("Вы не можете ");
		sb.append(equipVerb);
		sb.append("");
		sb.append(clothing.getName());
		sb.append(", так как ");
		
		if(blockingClothing.equals(clothing) && clothing.isSealed()) {
			sb.append((blockingClothing.getClothingType().isPlural()?" их":" это"));
			sb.append(" запечатано!");
			
		} else {
			sb.append(UtilText.parse(equipTarget, "[npc.namePos] "));
			sb.append(blockingClothing.getName());
			sb.append((blockingClothing.getClothingType().isPlural()?" являются":" есть"));
			sb.append(" преграждает вам путь!");
		}
		
		return sb.toString();
	}
	
	private static boolean isWeaponDyeReforgeActionAvailable() {
		return Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
				|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
				|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
				|| Main.game.isDebugMode();
	}
	
	private static boolean isClothingDyeActionAvailable() {
		return Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
				|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
				|| Main.game.isDebugMode();
	}
	
	
	/**
	 * The main DialogueNode. From here, the player can gain access to all parts
	 * of their inventory.
	 */
	public static final DialogueNode INVENTORY_MENU = new DialogueNode("Инвентарь", "Вернуться в меню инвентаря.", true) {
		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Вечерний наряд";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(inventoryNPC!=null && interactionType==InventoryInteraction.TRADING) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.removeTraderDescription)) {
					UtilText.nodeContentSB.append(inventoryNPC.getTraderDescription());
				}
				
			} else if(interactionType==InventoryInteraction.CHARACTER_CREATION) {
				return CharacterCreation.getCheckingClothingDescription();
			}
			
			return UtilText.nodeContentSB.toString();
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("Недоступно", "Вы не можете управлять инвентарем во время плохого финала...", null);
				}
				return null;
			}
			
			if(responseTab==1) {
				if(item!=null) {
					return ITEM_INVENTORY.getResponse(responseTab, index);
					
				} else if(clothing!=null) {
					if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(clothing) || (inventoryNPC!=null && inventoryNPC.getClothingCurrentlyEquipped().contains(clothing))) {
						return CLOTHING_EQUIPPED.getResponse(responseTab, index);
					} else {
						return CLOTHING_INVENTORY.getResponse(responseTab, index);
					}
					
				} else if(weapon!=null) {
					if(Main.game.getPlayer().hasWeaponEquipped(weapon)
							|| (inventoryNPC!=null && inventoryNPC.hasWeaponEquipped(weapon))) {
						return WEAPON_EQUIPPED.getResponse(responseTab, index);
					} else {
						return WEAPON_INVENTORY.getResponse(responseTab, index);
					}
					
				} else {
					return null;
				}
			}

			StringBuilder responseSB = new StringBuilder();
			switch(interactionType) {
				case COMBAT:
					if(index == 1) {
						return new Response("Забрать всё", "Вы не можете делать это во время боя!", null);

					} else if (index == 2) {
						return new Response("Смешать всё", "Вы не можете делать это во время сражения!", null);

					} else if (index == 3) {
						return new Response("Заменить всё", "Вы не можете делать это во время сражения!", null);
						
					} else if (index == 4) {
						return new Response("Снять всё", "Вы не можете делать это во время сражения!", null);

					} else if (index == 5) {
						return new Response("Надеть всё", "Вы не можете делать это во время сражения!", null);

					} else if(index == 6) {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							return new Response("Убрать всё", "Вы не можете делать это во время боя!", null);
						}
						return new Response("Выбросить всё", "Вы не можете делать это во время боя!", null);

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("Ключи", "Сейчас у вас нет ключей, которые используются для разблокировки определенных предметов одежды.", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("Ключи", "Вы уже просматриваете список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", null);
							
						} else {
							return new Response("Ключи", "Посмотреть список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
					
				case FULL_MANAGEMENT:
					if (index == 1) {
						if(inventoryNPC == null ) {
							if((Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 && !Main.game.getPlayerCell().getInventory().isAnyQuestItemPresent())
									|| Main.game.isInCombat()
									|| Main.game.isInSex()) {
								return new Response("Забрать всё", "Собрать всё на земле.", null);

							} else {
								return new Response("Забрать всё", "Собрать всё на земле.", INVENTORY_MENU){
									@Override
									public void effects(){
										for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllItemsInInventory()).entrySet()) {
											Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
										}
										for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory()).entrySet()) {
											Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
										}
										for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
											Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
										}
									}
								};
							}

						} else {
							if(inventoryNPC.getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
								return new Response("Забрать всё", UtilText.parse(inventoryNPC, "Забрать всё из инвентаря [npc.namePos]."), null);

							} else {
								return new Response("Забрать всё", UtilText.parse(inventoryNPC, "Забрать всё из инвентаря [npc.namePos]."), INVENTORY_MENU){
									@Override
									public void effects(){
										for(Entry<AbstractItem, Integer> entry : new HashMap<>(inventoryNPC.getAllItemsInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasItem(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeItem(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
											}
										}
										for(Entry<AbstractClothing, Integer> entry : new HashMap<>(inventoryNPC.getAllClothingInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasClothing(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeClothing(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
											}
										}
										for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(inventoryNPC.getAllWeaponsInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasWeapon(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeWeapon(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
											}
										}
									}
								};
							}
						}

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Смешать всё", "На вас нет одежды, так что нечего сдвигать!", null);

						} else {
							return new Response("Смешать всё", "Сдвинуть как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 3) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Заменить всё", "На вас нет одежды, так что нечего заменять!", null);

						} else {
							return new Response("Заменить всё", "Заменить как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Снять всё", "На вас нет одежды, так что нечего снимать!", null);

						} else {
							return new Response("Снять всё", "Снять как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
								}
							};
						}

					} else if (index == 5) {
						if(Main.game.getPlayer().getAllClothingInInventory().isEmpty()) {
							return new Response("Надеть всё", "У вас нет одежды, так что надеть нечего!", null);

						} else {
							return new Response("Надеть всё", "Надеть как можно больше одежды из вашего инвентаря.", INVENTORY_MENU){
								@Override
								public void effects(){
									equipAll(Main.game.getPlayer());
								}
							};
						}

					} else if(index == 6) {
						if(Main.game.getPlayer().getInventorySlotsTaken()==0) {
							return new Response(
									!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
										?"Убрать всё"
										:"Выбросить всё",
									!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
										?"В вашем инвентаре нечего складывать..."
										:"В вашем инвентаре нечего выбрасывать...",
											null);
						}
						return new Response(
								!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
									?"Убрать всё"
									:"Выбросить всё",
								!Main.game.getPlayer().getLocationPlace().isItemsDisappear()
									?"Сложить всё содержимое инвентаря в это место."
									:"Выбросить всё содержимое инвентаря на землю.",
										INVENTORY_MENU) {
							@Override
							public void effects() {
								for(Entry<AbstractItem, Integer> i : new HashSet<>(Main.game.getPlayer().getAllItemsInInventory().entrySet())) {
									if(i.getKey().getItemType().isAbleToBeDropped()) {
										dropItems(Main.game.getPlayer(), i.getKey(), i.getValue());
									}
								}
								for(Entry<AbstractWeapon, Integer> w : new HashSet<>(Main.game.getPlayer().getAllWeaponsInInventory().entrySet())) {
									if(w.getKey().getWeaponType().isAbleToBeDropped()) {
										dropWeapons(Main.game.getPlayer(), w.getKey(), w.getValue());
									}
								}
								for(Entry<AbstractClothing, Integer> c : new HashSet<>(Main.game.getPlayer().getAllClothingInInventory().entrySet())) {
									if(c.getKey().getClothingType().isAbleToBeDropped()) {
										dropClothing(Main.game.getPlayer(), c.getKey(), c.getValue());
									}
								}
							}
						};

					} else if (index == 7 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC,"Снять всё ([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "На [npc.Name] нет одежды, так что снимать нечего!"),
									null);

						} else {
							return new Response(UtilText.parse(inventoryNPC,"Снять всё ([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "Снять с [npc.namePos] как можно больше одежды."),
									INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : inventoryNPC.getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 8 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC,"Заменить всё ([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "На [npc.Name] нет никакой одежды, так что менять нечего!"),
									null);

						} else {
							return new Response(UtilText.parse(inventoryNPC,"Заменить всё ([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "Снять как можно больше одежды с [npc.namePos]."),
									INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(inventoryNPC.getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 9 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "На [npc.Name] нет никакой одежды, так что снимать нечего!"),
									null);

						} else {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"),
									UtilText.parse(inventoryNPC, "Снять как можно больше одежды с [npc.namePos]."),
									INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(inventoryNPC));
								}
							};
						}

					} else if (index == 10 && !Main.game.isInSex() && !Main.game.isInCombat()) {
						return getQuickTradeResponse();

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("Ключи", "Сейчас у вас нет ключей, которые используются для разблокировки определенных предметов одежды.", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("Ключи", "Вы уже просматриваете список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", null);
							
						} else {
							return new Response("Ключи", "Посмотреть список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
				case CHARACTER_CREATION:
					if (index == 1) {
						if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
								|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
							return new Response("На сцену", "Вам нужно быть одетым так, чтобы тело было прикрыто, а также надеть пару туфель.", null);
							
						} else {
							return new Response("На сцену", "Теперь ты готов выйти на сцену.", CharacterCreation.CHOOSE_BACKGROUND) {
								@Override
								public int getSecondsPassed() {
									return CharacterCreation.TIME_TO_BACKGROUND;
								}
								@Override
								public void effects() {
									CharacterCreation.moveNPCIntoPlayerTile();
								}
							};
						}
						
					} else if(index == 2){
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
							return new Response("Снять всё", "Сейчас на тебе ничего нет, снимать нечего.", null);
						}
						else{
							return new Response("Снять всё", "Снять как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
								}
							};
						}

					} else {
						return null;
					}
					
				case TRADING:
					if (index == 1) {
						if(inventoryNPC != null ||Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
							return new Response("Забрать всё", "Собрать всё на земле.", null);

						} else {
							return new Response("Забрать всё", "Собрать всё на земле.", INVENTORY_MENU){
								@Override
								public void effects(){
									for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllItemsInInventory()).entrySet()) {
										Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
									}
									for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory()).entrySet()) {
										Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
									}
									for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
										Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
									}
								}
							};
						}

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Смешать всё", "На вас нет одежды, так что нечего сдвигать!", null);

						} else {
							return new Response("Смешать всё", "Сдвинуть как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 3) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Заменить всё", "На вас нет одежды, так что нечего заменять!", null);

						} else {
							return new Response("Заменить всё", "Заменить как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Снять всё", "На вас нет одежды, так что нечего снимать!", null);

						} else {
							return new Response("Снять всё", "Снять как можно больше одежды.", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
								}
							};
						}

					} else if (index == 5) {
						if(Main.game.getPlayer().getAllClothingInInventory().isEmpty()) {
							return new Response("Надеть всё", "У вас нет одежды, так что надеть нечего!", null);

						} else {
							return new Response("Надеть всё", "Надеть как можно больше одежды из вашего инвентаря.", INVENTORY_MENU){
								@Override
								public void effects(){
									equipAll(Main.game.getPlayer());
								}
							};
						}

					} else if(index == 6) {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							return new Response("Убрать всё", "Ты не можешь сделать это во время торговли...", null);
						}
						return new Response("Выбросить всё", "Ты не можешь сделать это во время торговли...", null);

					} else if (index == 9 && inventoryNPC!=null) {
						return getBuybackResponse();

					} else if (index == 10 && !Main.game.isInSex() && !Main.game.isInCombat()) {
						return getQuickTradeResponse();

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("Ключи", "Сейчас у вас нет ключей, которые используются для разблокировки определенных предметов одежды.", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("Ключи", "Вы уже просматриваете список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", null);
							
						} else {
							return new Response("Ключи", "Посмотреть список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
				case SEX:
					if(index == 1) {
						return new Response("Забрать всё", "Собрать всё на земле.", null);

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Смешать всё", "На вас нет одежды, так что нечего сдвигать!", null);

						} else {
							return new Response("Смешать всё", "Сдвинуть как можно больше одежды.", Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												responseSB.append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}

									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 3) {
						return new Response("Заменить всё", "Во время секса нельзя менять одежду!", null);

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Снять всё", "На вас нет одежды, так что нечего снимать!", null);

						} else {
							return new Response("Снять всё", "Снять как можно больше одежды.", Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);
									
									responseSB.append(unequipAll(Main.game.getPlayer()));
									
									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 5) {
						return new Response("Надеть всё", "Во время секса нельзя надевать одежду!", null);

					} else if(index == 6) {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							return new Response("Убрать всё", "Ты не можешь сделать это во время секса...", null);
						}
						return new Response("Выбросить всё", "Ты не можешь сделать это во время секса...", null);

					} else if (index == 7 && inventoryNPC != null) {
						if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Пока ты прячешься, ты не можешь сменить [npc.namePos] одежду!"), null);
							
						} else if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), null)) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "В этой секс-сцене нельзя сменить [npc.namePos] одежду!"), null);

						} else if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "На [npc.Name] нет одежды, так что снимать нечего!"), null);

						} else {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Снять с [npc.namePos] как можно больше одежды."), Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									for(AbstractClothing c : inventoryNPC.getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												responseSB.append("<p style='text-align:center;'>"+inventoryNPC.getDisplaceDescription()+"</p>");
											}
										}
									}

									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 8 && inventoryNPC != null) {
						return new Response(UtilText.parse(inventoryNPC, "Заменить всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Вы не можете сменить [npc.namePos] одежду во время секса!"), null);

					} else if (index == 9 && inventoryNPC != null) {
						if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Пока вы прячетесь, вы не можете снять [npc.namePos] одежду!"), null);
							
						} else if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), null)) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Вы не можете снять [npc.namePos] одежду в этой секс-сцене!"), null);

						} else if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "На [npc.Name] нет никакой одежды, так что снимать нечего!"), null);

						} else {
							return new Response(UtilText.parse(inventoryNPC, "Снять всё ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Снять как можно больше одежды с [npc.namePos]."), Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									responseSB.append(unequipAll(inventoryNPC));

									Main.sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
						}

					} else if(index==11) {
						if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
							return new Response("Ключи", "Сейчас у вас нет ключей, которые используются для разблокировки определенных предметов одежды.", null);
							
						} else if(Main.game.getCurrentDialogueNode()==INVENTORY_MENU_KEYS) {
							return new Response("Ключи", "Вы уже просматриваете список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", null);
							
						} else {
							return new Response("Ключи", "Посмотреть список всех имеющихся у вас ключей, используемых для разблокировки определенных предметов одежды.", INVENTORY_MENU_KEYS);
						}
						
					} else {
						return null;
					}
			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode INVENTORY_MENU_KEYS = new DialogueNode("Инвентарь", "Вернуться в меню инвентаря.", true) {
		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Вечерний наряд";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
			UtilText.nodeContentSB.append("[style.boldMinorGood(В наличии ключей:)]");
			if(Main.game.getPlayer().getUnlockKeyMap().isEmpty()) {
				UtilText.nodeContentSB.append("<br/>[style.italicsDisabled(Нет...)]");
				
			} else {
				for(Entry<String, List<InventorySlot>> entry : Main.game.getPlayer().getUnlockKeyMap().entrySet()) {
					try {
						GameCharacter npc = Main.game.getNPCById(entry.getKey());
						List<String> slots = new ArrayList<>();
						for(InventorySlot slot : entry.getValue()) {
							AbstractClothing slotClothing = npc.getClothingInSlot(slot);
							if(slotClothing!=null) {
								slots.add(Util.capitaliseSentence(slotClothing.getName())+" ('"+slot.getName()+"' слот)");
							}
						}
						if(!slots.isEmpty()) {
							UtilText.nodeContentSB.append(UtilText.parse(npc, "<br/><b style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> ("+slots.size()+"): "));
							int i=0;
							for(String s : slots) {
								UtilText.nodeContentSB.append((i>0?", ":"")+s);
								i++;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INVENTORY_MENU.getResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode ITEM_INVENTORY = new DialogueNode("Предмет", "", true) {

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}
		
		@Override
		public String getContent() {
			return getItemDisplayPanel(item,
					item.getSVGString(),
					item.getDisplayName(true),
					item.getDescription(owner)
						+ item.getExtraDescription(owner, owner)
						+ (owner!=null && owner.isPlayer()
								? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
										? "<p>"
											+(inventoryNPC.willBuy(item) && item.getItemType().isAbleToBeSold()
												?inventoryNPC.getName("") + " купит за " + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getBuyModifier())) + "."
												:inventoryNPC.getName("") + " не хочет покупать это.")
											+"</p>"
										: "")
								:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? "<p>"
											+ inventoryNPC.getName("") + " продаст за " + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getSellModifier(item))) + "."
										+ "</p>" 
									: "")));
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("Недоступно", "Вы не можете управлять инвентарем во время плохого финала...", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasItem(item);
					
					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"Выбросить ":"Спрятать";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "Вы не можете выбрасывать предметы во время мастурбации.", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "Вы не можете выбрасывать предметы во время мастурбации.", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(Все)", "Вы не можете выбрасывать предметы во время мастурбации.", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать предметы во время мастурбации.", null);
								
							} else if(index == 6) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вы не можете использовать это, так как сейчас вы [style.colourTerrible(неподвижны)]!", null);
								}
								if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Предметы нельзя использовать во время этой секс-сцены!", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Это нельзя использовать во время секса!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false));
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Во время секса можно использовать только один предмет за раз!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
						
						default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Выбросить (1)", "Вы не можете выбросить " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Выбросить (1)", "Это место заполнено, поэтому вы не можете бросить свой " + item.getName() + " здесь!", null);
									} else {
										return new Response("Выбросить (1)", "Выбросить свой " + item.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 1);
											}
										};
									}
								} else {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Сложить (1)", "Вы не можете сложить " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Сложить (1)", "Эта область заполнена, поэтому вы не можете сложить свои " + item.getName() + " здесь!", null);
									} else {
										return new Response("Сложить (1)", "Сложить " + item.getName() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(owner.getItemCount(item) < 5) {
										return new Response("Выбросить (5)", "У вас нет пяти " + item.getNamePlural() + " чтобы отдать!", null);
										
									} else if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Выбросить (5)", "Вы не можете выбросить " + item.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Выбросить (5)", "Это место заполнено, поэтому вы не можете бросить свой " + item.getNamePlural() + " здесь!", null);
										
									} else {
										return new Response("Выбросить (5)", "Выбросить пять ваших " + item.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 5);
											}
										};
									}
								} else {
									if(owner.getItemCount(item) < 5) {
										return new Response("Сложить (5)", "У вас нет пяти " + item.getNamePlural() + " чтобы отдать!", null);
										
									} else if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Сложить (5)", "Вы не можете сложить " + item.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Сложить (5)", "Эта область заполнена, поэтому вы не можете сложить свои " + item.getNamePlural() + " здесь!", null);
										
									} else {
										return new Response("Сложить (5)", "Сложить пять ваших " + item.getNamePlural() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Выбросить (Все)", "Вы не можете выбросить " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Выбросить (Все)", "Это место заполнено, поэтому вы не можете бросить свой " + item.getNamePlural() + " здесь!", null);
									} else {
										return new Response("Выбросить (Все)", "Выбросить все ваши " + item.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, owner.getItemCount(item));
											}
										};
									}
								} else {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Склад (Все)", "Вы не можете сложить " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Склад (Все)", "Эта область заполнена, поэтому вы не можете сложить свои " + item.getNamePlural() + " здесь!", null);
									} else {
										return new Response("Склад (Все)", "Сложить все ваши " + item.getNamePlural() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, owner.getItemCount(item));
											}
										};
									}
								}
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null || item.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("Зачаровать", "Этот предмет нельзя зачаровать!", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("Зачаровать", "Зачаровать этот предмет.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("предметов"), null);
								}
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Так как этот предмет обладает особыми эффектами, вы можете использовать только один за раз!", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " предметов в вашем инвентаре.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Отдать (1)", "Вы не можете отдавать предметы во время боя!", null);
								
							} else if(index == 2) {
								return new Response("Отдать (5)", "Вы не можете отдавать предметы во время боя!", null);
								
							} else if(index == 3) {
								return new Response("Отдать (Все)", "Вы не можете отдавать предметы во время боя!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать предметы во время боя!", null);
								
							} else if(index == 6) {
								if(Main.game.getPlayer().isStunned()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вы не можете использовать предметы, пока оглушены!", null);
									
								} else if(Main.combat.isCombatantDefeated(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вы не можете использовать предметы, пока повержены!", null);
									
								} else if(Main.game.getPlayer().getRemainingAP()<CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вам нужно как минимум "+CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())+" Очков действия (AP), чтобы совершить это действие!", null);
									
								} else if (!item.isAbleToBeUsedInCombatAllies()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вы не можете использовать это во время боя!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											Main.combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, owner, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Во время боя можно использовать только один предмет за раз!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {//TODO on ally though???
								if(Main.game.getPlayer().isStunned()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Противник)", "Вы не можете использовать предметы, пока оглушены!", null);
									
								} else if(Main.combat.isCombatantDefeated(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Противник)", "Вы не можете использовать предметы, пока повержены!", null);
									
								} else if(Main.game.getPlayer().getRemainingAP()<CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Противник)", "Вам нужно как минимум "+CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())+" Очков действия (AP), чтобы совершить это действие!", null);
									
								} else if (!item.isAbleToBeUsedInCombatEnemies()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Противник)", "Вы не можете использовать это во время боя!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Противник)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Противник)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);

								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Противник)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Main.combat.ENEMY_ATTACK,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Противник)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Main.combat.ENEMY_ATTACK,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Противник)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Main.combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Main.combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Оппонент)", "Во время боя можно использовать только один предмет за раз!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasItem(item);
							
							if(index == 1) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("Отдать (1)", "Вы не можете отдать " + item.getName() + "!", null);
								} else if(inventoryFull) {
									return new Response("Отдать (1)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								return new Response("Отдать (1)", UtilText.parse(inventoryNPC, "Отдать [npc.name] одну " + item.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, 1);
									}
								};
								
							} else if(index == 2) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("Отдать (5)", "Вы не можете отдать " + item.getName() + "!", null);
								} else if(inventoryFull) {
									return new Response("Отдать (5)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								if(Main.game.getPlayer().getItemCount(item) >= 5) {
									return new Response("Отдать (5)", UtilText.parse(inventoryNPC, "Отдать [npc.name] пять ваших " + item.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferItems(Main.game.getPlayer(), inventoryNPC, item, 5);
										}
									};
								} else {
									return new Response("Отдать (5)", "У вас нет пяти " + item.getNamePlural() + " чтобы отдать!", null);
								}
								
							} else if(index == 3) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("Отдать (Все)", "Вы не можете отдать " + item.getName() + "!", null);
								} else if(inventoryFull) {
									return new Response("Отдать (Все)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								return new Response("Отдать (Все)", UtilText.parse(inventoryNPC, "Отдать [npc.name] все ваши " + item.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null || item.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("Зачаровать", "Этот предмет нельзя зачаровать!", null);
									
								} else if(Main.game.isDebugMode()
											|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
										return new Response("Зачаровать", "Зачаровать этот предмет.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(item);
											}
										};
								
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("предметов"), null);
								}
							
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Так как этот предмет обладает особыми эффектами, вы можете использовать только один за раз!", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " предметов в вашем инвентаре.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											Main.game.getPlayer().useItem(item, inventoryNPC, false);
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
									
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								}
							} else if(index == 12) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), "Так как этот предмет обладает особыми эффектами, вы можете использовать только один за раз!", null);
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " предметов в вашем инвентаре.)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " предметов в вашем инвентаре.)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
									
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " предметов в вашем инвентаре.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Отдать (1)", "Вы не можете отдавать предметы, когда занимаетесь сексом!", null);
								
							} else if(index == 2) {
								return new Response("Отдать (5)", "Вы не можете отдавать предметы, когда занимаетесь сексом!", null);
								
							} else if(index == 3) {
								return new Response("Отдать (Все)", "Вы не можете отдавать предметы, когда занимаетесь сексом!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать предметы, когда занимаетесь сексом!", null);
								
							} else if(index == 6) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вы не можете использовать это, так как сейчас вы [style.colourTerrible(неподвижны)]!", null);
								}
								if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Предметы нельзя использовать во время этой секс-сцены!", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Это нельзя использовать во время секса!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(((NPC)Main.sex.getTargetedPartner(Main.game.getPlayer())).getItemUseEffects(item, owner, Main.game.getPlayer(), Main.game.getPlayer()).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Во время секса можно использовать только один предмет за раз!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", "Вы не можете использовать это, так как сейчас вы [style.colourTerrible(неподвижны)]!", null);
								}
								if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", UtilText.parse(inventoryNPC, "Пока вы прячетесь, вы не можете использовать предметы на [npc.name]!"), null);
									
								} else if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", "Предметы нельзя использовать во время этой секс-сцены!", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", "Это нельзя использовать во время секса!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(inventoryNPC.isAsleep()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", UtilText.parse(inventoryNPC, "Вы не можете использовать это, пока [npc.nameIsFull] спит!"), null);
									
								} else if(item.getItemType().isFetishGiving()) {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
												Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Main.sex.SEX_DIALOGUE,
												Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
												Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
									
								} else if(item.getItemType().isTransformative()) {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
												Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Main.sex.SEX_DIALOGUE,
												Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
												Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
												null,
												null,
												null){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Партнер)",
												item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Партнер)", "Во время секса можно использовать только один предмет за раз!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!item.getItemType().isAbleToBeSold()) {
									return new Response("Продать (1)", "Вы не можете продать  " + item.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(item)) {
									int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Продать (1) (" + UtilText.formatAsMoney(sellPrice, "охват") + ")", "Продать  " + item.getName() + " на " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellItems(Main.game.getPlayer(), inventoryNPC, item, 1, sellPrice);
										}
									};
								} else {
									return new Response("Продать (1)", inventoryNPC.getName("") + " не хочет покупать это.", null);
								}
								
							} else if(index == 2) {
								if(Main.game.getPlayer().getItemCount(item) >= 5) {
									if(!item.getItemType().isAbleToBeSold()) {
										return new Response("Продать (5)", "Вы не можете продать  " + item.getName() + "!", null);
										
									} else if (inventoryNPC.willBuy(item)) {
										int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Продать (5) (" + UtilText.formatAsMoney(sellPrice*5, "охват") + ")", "Продать пять ваших  " + item.getNamePlural() + " на " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												sellItems(Main.game.getPlayer(), inventoryNPC, item, 5, sellPrice);
											}
										};
									} else {
										return new Response("Продать (5)", inventoryNPC.getName("") + " не хочет это покупать.", null);
									}
									
								} else {
									return new Response("Продать (5)", "У вас нет пяти " + item.getNamePlural() + " продать!", null);
								}
								
							} else if(index == 3) {
								if(!item.getItemType().isAbleToBeSold()) {
									return new Response("Продать (Все)", "Вы не можете продать  " + item.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(item)) {
									int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Продать (Все) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getItemCount(item), "охват") + ")",
											"Продать  " + item.getName() + " на " + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getItemCount(item)) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item), sellPrice);
										}
									};
								} else {
									return new Response("Продать (Все)", inventoryNPC.getName("") + " не хочет это покупать.", null);
								}
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null || item.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("Зачаровать", "Этот предмет нельзя зачаровать!", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("Зачаровать", "Зачаровать этот предмет.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("предметов"), null);
								}
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsedWhileTrading()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedWhileTradingDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsedWhileTrading()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedWhileTradingDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Так как этот предмет обладает особыми эффектами, вы можете использовать только один за раз!", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " предметов в вашем инвентаре.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не хочет использовать твои предметы."), null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не хочет использовать твои предметы."), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item) && item.getRarity()!=Rarity.QUEST;
					
					switch(interactionType) {
						case SEX:
							if(index == 1) {
								return new Response("Взять (1)", "Ты не можешь подбирать предметы во время мастурбации.", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Ты не можешь подбирать предметы во время мастурбации.", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Ты не можешь подбирать предметы во время мастурбации.", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать предметы во время мастурбации.", null);
								
							} else if(index == 6) {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Вы не можете использовать это, так как сейчас вы [style.colourTerrible(неподвижны)]!", null);
								}
								if(!Main.sex.isItemUseAvailable()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Предметы нельзя использовать во время этой секс-сцены!", null);
									
								} else if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Это нельзя использовать во время секса!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true);
												resetPostAction();
											}
										};
									} else {
										return new Response(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												Main.sex.setUsingItemText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true));
												resetPostAction();
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
												Main.sex.setSexStarted(true);
											}
										};
									}
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Во время секса можно использовать только один предмет за раз!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					
						default:
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Взять (1)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (1)", "Взять один " + item.getName() + " с земли.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpItems(Main.game.getPlayer(), item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Взять (5)", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayerCell().getInventory().getItemCount(item) >= 5) {
									return new Response("Взять (5)", "Взять пять " + item.getNamePlural() + " с земли.", INVENTORY_MENU){
										@Override
										public void effects(){
											pickUpItems(Main.game.getPlayer(), item, 5);
										}
									};
								} else {
									return new Response("Взять (5)", "Их не пять, " + item.getNamePlural() + " на земле!", null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Взять (Все)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (Все)", "Забрать всё, " + item.getNamePlural() + " с земли.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpItems(Main.game.getPlayer(), item, Main.game.getPlayerCell().getInventory().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Нельзя зачаровывать предметы на земле!", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" все (Себя)", "Так как этот предмет обладает особыми эффектами, вы можете использовать только один за раз!", null);
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())
											+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " которые находятся в этой области.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayerCell().getInventory().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Взять (1)", "Нельзя забирать чужие вещи во время боя!", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Нельзя забирать чужие вещи во время боя!", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Нельзя забирать чужие вещи во время боя!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Нельзя зачаровывать чужие предметы, особенно во время боя!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", "Нельзя использовать чужие предметы во время боя!", null);
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" все (Себя)", "Нельзя использовать чужие предметы во время боя!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Противник)", "Нельзя заставить кого-то использовать предмет прямо во время боя!", null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" все (Оппонент)", "Нельзя заставить кого-то использовать предмет прямо во время боя!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item) && item.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Взять (1)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (1)", UtilText.parse(inventoryNPC, "Взять " + item.getName() + " из [npc.name]."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Взять (5)", "Ваш инвентарь уже полон!", null);
								}
								if(inventoryNPC.getItemCount(item) >= 5) {
									return new Response("Взять (5)", UtilText.parse(inventoryNPC, "Взять пять штук [npc.namePos] " + item.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferItems(inventoryNPC, Main.game.getPlayer(), item, 5);
										}
									};
								} else {
									return new Response("Взять (5)", UtilText.parse(inventoryNPC, "У [npc.Name] нет пяти штук " + item.getNamePlural() + "!"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Взять (Все)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (Все)", UtilText.parse(inventoryNPC, "Забрать всё "+Util.intToString(inventoryNPC.getItemCount(item))+" of  [npc.namePos] " + item.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, inventoryNPC.getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Нельзя зачаровывать предметы, принадлежащие кому-то другому!", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false, false, false) + "</p>");
											if (item.isConsumedOnUse()) {
												inventoryNPC.getInventory().removeItem(item);
											}
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " которые находятся в инвентаре [npc.namePos].)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false, false, false) + "</p>");
											}
											if (item.isConsumedOnUse()) {
												inventoryNPC.getInventory().removeItem(item, itemCount);
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											Main.game.getPlayer().useItem(item, inventoryNPC, false);
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											resetPostAction();
										}
									};
								}
								
							} else if(index == 12) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"), "Так как этот предмет обладает особыми эффектами, вы можете использовать только один за раз!", null);
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " которые находятся в инвентаре [npc.namePos].)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " которые находятся в инвентаре [npc.namePos].)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(Повторить для всех " + item.getNamePlural() + " которые находятся в инвентаре [npc.namePos].)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC).getValue());
											}
											resetPostAction();
										}
									};
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Взять (1)", "Нельзя забирать чужие вещи прямо во время секса!", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Нельзя забирать чужие вещи прямо во время секса!", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Нельзя забирать чужие вещи прямо во время секса!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Нельзя зачаровывать чужие вещи, особенно во время секса!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Себя)", "Нельзя использовать вещи партнера во время секса!", null);
								//TODO
//								if (!item.isAbleToBeUsedInSex()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Self)", "This cannot be used during sex!", null);
//									
//								} else if (!item.isAbleToBeUsedFromInventory()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Self)", item.getUnableToBeUsedFromInventoryDescription(), null);
//									
//								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), Main.game.getPlayer())) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Self)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), Main.game.getPlayer()), null);
//									
//								} else {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Self)",
//											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Main.sex.SEX_DIALOGUE){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, Main.game.getPlayer()));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Себя)", "Во время секса можно использовать только один предмет за раз!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Партнер)", "Нельзя использовать вещи партнера во время секса!", null);
								//TODO
//								if (!item.isAbleToBeUsedInSex()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Partner)", "This cannot be used during sex!", null);
//									
//								} else if (!item.isAbleToBeUsedFromInventory()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (Partner)", item.getUnableToBeUsedFromInventoryDescription(), null);
//									
//								} else if (!item.isAbleToBeUsed(Main.game.getPlayer(), inventoryNPC)) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)", item.getUnableToBeUsedDescription(Main.game.getPlayer(), inventoryNPC), null);
//									
//								} else if(item.getItemType().isFetishGiving()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".",
//											Main.sex.SEX_DIALOGUE,
//											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
//											Fetish.v.getAssociatedCorruptionLevel(),
//											null,
//											null,
//											null){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//								} else if(item.getItemType().isTransformative()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".",
//											Main.sex.SEX_DIALOGUE,
//											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
//											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
//											null,
//											null,
//											null){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//									
//								} else {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Main.sex.SEX_DIALOGUE){
//										@Override
//										public void effects(){
//											Main.sex.setUsingItemText(Main.sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Main.sex.setSexStarted(true);
//										}
//									};
//								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" все (Партнер)", "Во время секса можно использовать только один предмет за раз!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item)  && item.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier(item));
								if(inventoryFull) {
									return new Response("Купить (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Купить (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "охват")+")", "Тебе не хватает денег на это!", null);
								}
								return new Response("Купить (1) (" + UtilText.formatAsMoney(sellPrice, "охват") + ")", "Купить  " + item.getName() + " на " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier(item));
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getItemCount(item) < 5)) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", UtilText.parse(inventoryNPC, "У [npc.Name] нет пяти штук "+item.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", "Тебе не хватает денег на это!", null);
								}
								return new Response("Купить (5) (" + UtilText.formatAsMoney(sellPrice*5, "охват") + ")", "Купить  " + item.getName() + " на " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier(item));
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getItemCount(item);
								if(inventoryFull) {
									return new Response("Купить (Все) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									int affordableCount = (int)(Main.game.getPlayer().getMoney() / sellPrice);
									if(affordableCount > 0) {
										return new Response("Купить (Макс " + affordableCount + ") (" + UtilText.formatAsMoney(sellPrice * affordableCount, "охват") + ")",
												"Купить  " + item.getName() + " на " + UtilText.formatAsMoney(sellPrice * affordableCount) + ".", INVENTORY_MENU) {
											@Override
											public void effects() {
												sellItems(inventoryNPC, Main.game.getPlayer(), item, affordableCount, sellPrice);
											}
										};
									} else {
										return new Response("Купить (Все) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "охват")+")", "Тебе не хватает денег на это!", null);
									}
								}
								return new Response("Купить (Все) (" + UtilText.formatAsMoney(sellPrice*count, "охват") + ")",
										"Купить  " + item.getName() + " на " + UtilText.formatAsMoney(sellPrice*count) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, count, sellPrice);
									}
								};
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровать чужой предмет!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (Себя)", UtilText.parse(inventoryNPC, "[npc.Name] не позволит вам использовать предметы [npc.her], не купив их сначала."), null);
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" все (Себя)", UtilText.parse(inventoryNPC, "[npc.Name] не позволит вам использовать предметы [npc.her], не купив их сначала."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " ([npc.HerHim])"),
										UtilText.parse(inventoryNPC, "[npc.Name] не станет использовать предметы, которые пытается продать [npc.sheIs]!"),
										null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+UtilText.parse(inventoryNPC, " все ([npc.HerHim])"),
										UtilText.parse(inventoryNPC, "[npc.Name] не станет использовать предметы, которые пытается продать [npc.sheIs]!"),
										null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode WEAPON_INVENTORY = new DialogueNode("Оружие", "", true) {
		
		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<String> extraDescriptions = weapon.getExtraDescriptions(owner);
			if(!extraDescriptions.isEmpty()) {
				sb.append("<p>");
					for(int i=0 ; i<extraDescriptions.size() ; i++) {
						sb.append(extraDescriptions.get(i));
						if(i<extraDescriptions.size()-1) {
							sb.append("<br/>");
						}
					}
				sb.append("</p>");
			}
			return getItemDisplayPanel(weapon,
					weapon.getSVGString(),
					Util.capitaliseSentence(weapon.getDisplayName(true)),
					weapon.getDescription(owner)
						+ sb.toString()
						+ (owner!=null && owner.isPlayer()
								? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
										? "<p>" 
											+(inventoryNPC.willBuy(weapon)
												?inventoryNPC.getName("") + " купит за " + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getBuyModifier())) + "."
												:inventoryNPC.getName("") + " не хочет покупать это.")
											+"</p>"
										: "")
								:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? "<p>"
											+ inventoryNPC.getName("") + " продаст за " + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getSellModifier(weapon))) + "."
										+ "</p>" 
									: "")));
		}


		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("Недоступно", "Вы не можете управлять инвентарем во время плохого финала...", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
					
					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"Выбросить ":"Спрятать";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "Вы не можете выпустить оружие во время мастурбации.", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "Вы не можете выпустить оружие во время мастурбации.", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(Все)", "Вы не можете выпустить оружие во время мастурбации.", null);
								
							} else if(index == 4) {
								return new Response("Краска/Перековка", "Вы не можете красить или перековывать оружие во время мастурбации.", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете накладывать заклинания на оружие во время мастурбации.", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", "Вы не можете экипировать оружие во время мастурбации.", null);
								
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", "Вы не можете экипировать оружие во время мастурбации.", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
							
						default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Выбросить (1)", "Вы не можете выбросить " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Выбросить (1)", "Это место заполнено, поэтому вы не можете бросить свой " + weapon.getName() + " здесь!", null);
										
									} else {
										return new Response("Выбросить (1)", "Выбросить свой " + weapon.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 1);
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Сложить (1)", "Вы не можете выбросить " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Сложить (1)", "Эта область заполнена, поэтому вы не можете сложить свои " + weapon.getName() + " здесь!", null);
									} else {
										return new Response("Сложить (1)", "Сложить " + weapon.getName() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Выбросить (5)", "Вы не можете выбросить " + weapon.getName() + "!", null);
										
									} else if(owner.getWeaponCount(weapon) < 5) {
										return new Response("Выбросить (5)", "У вас нет пяти " + weapon.getNamePlural() + " чтобы выбить!", null);
										
									} else if(areaFull) {
										return new Response("Выбросить (5)", "Это место заполнено, поэтому вы не можете бросить свой " + weapon.getNamePlural() + " здесь!", null);
										
									} else {
										return new Response("Выбросить (5)", "Выбросить пять ваших " + weapon.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 5);
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Сложить (5)", "Вы не можете выбросить " + weapon.getName() + "!", null);
										
									} else if(owner.getWeaponCount(weapon) < 5) {
										return new Response("Сложить (5)", "У вас нет пяти " + weapon.getNamePlural() + " чтобы выбить!", null);
										
									} else if(areaFull) {
										return new Response("Сложить (5)", "Эта область заполнена, поэтому вы не можете сложить свои " + weapon.getNamePlural() + " здесь!", null);
										
									} else {
										return new Response("Сложить (5)", "Сложить пять ваших " + weapon.getNamePlural() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Выбросить (Все)", "Вы не можете выбросить " + weapon.getName() + "!", null);
									
								} else if(owner.getLocationPlace().isItemsDisappear()) {
									if(areaFull) {
										return new Response("Выбросить (Все)", "Это место заполнено, поэтому вы не можете бросить свой " + weapon.getNamePlural() + " здесь!", null);
									} else {
										return new Response("Выбросить (Все)", "Выбросить все ваши " + weapon.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, owner.getWeaponCount(weapon));
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Склад (Все)", "Вы не можете выбросить " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Склад (Все)", "Эта область заполнена, поэтому вы не можете сложить свои " + weapon.getNamePlural() + " здесь!", null);
									} else {
										return new Response("Склад (Все)", "Сложить все ваши " + weapon.getNamePlural() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, owner.getWeaponCount(weapon));
											}
										};
									}
								}
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краска/Перековка",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свои познания в [style.colourEarth(заклинаниях земли)], чтобы покрасить или перековать этот предмет."
													:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Краска/Перековка", "Ваш инвентарь полон, поэтому вы не можете изменить свойства этого оружия.", null);
									}
								} else {
									return new Response("Краска/Перековка", "Чтобы изменить свойства этого оружия, вам понадобится кисть для красок или молот для перековки.", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null || weapon.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("Зачаровать", "Это оружие нельзя зачаровать!", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("Зачаровать", "Зачаровать это оружие.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("оружие"), null);
								}
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в правую руку (Себе)", "Экипировать " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в правую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Экипировать в левую руку (Себе)",
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}

								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в левую руку (Себе)",
											"Экипировать " + weapon.getName() + "."
												+(weapon.getWeaponType().isTwoHanded()
														?"<br/>[style.italicsGood(Хотя "+(weapon.getWeaponType().isPlural()?"":"")+")]"
														:""),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в левую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Отдать (1)", "Вы не можете передавать оружие кому-то во время боя!", null);
								
							} else if(index == 2) {
								return new Response("Отдать (5)", "Вы не можете передавать оружие кому-то во время боя!", null);
								
							} else if(index == 3) {
								return new Response("Отдать (Все)", "Вы не можете передавать оружие кому-то во время боя!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Вы не можете красить оружие во время боя!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать оружие во время боя!", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", "Вы не можете менять оружие во время боя!", null);
									
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", "Вы не можете менять оружие во время боя!", null);
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Экипировать (Противник)", "Вы не можете заставить противника экипировать оружие!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasWeapon(weapon);
							
							if(index == 1) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Отдать (1)", "Вы не можете отдать " + weapon.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Отдать (1)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								return new Response("Отдать (1)", UtilText.parse(inventoryNPC, "Отдать [npc.name] одну " + weapon.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Отдать (5)", "Вы не можете отдать " + weapon.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Отдать (5)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								if(Main.game.getPlayer().getWeaponCount(weapon) >= 5) {
									return new Response("Отдать (5)", UtilText.parse(inventoryNPC, "Отдать [npc.name] пять ваших " + weapon.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 5);
										}
									};
								} else {
									return new Response("Отдать (5)", "У вас нет пяти " + weapon.getNamePlural() + " чтобы отдать!", null);
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Отдать (Все)", "Вы не можете отдать " + weapon.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Отдать (Все)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								return new Response("Отдать (Все)", UtilText.parse(inventoryNPC, "Отдать [npc.name] все ваши " + weapon.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краска/Перековка",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свои познания в [style.colourEarth(заклинаниях земли)], чтобы покрасить или перековать этот предмет."
													:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Краска/Перековка", "Ваш инвентарь полон, поэтому вы не можете изменить свойства этого оружия.", null);
									}
								} else {
									return new Response("Краска/Перековка", "Чтобы изменить свойства этого оружия, вам понадобится кисть для красок или молот для перековки.", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null || weapon.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("Зачаровать", "Это оружие нельзя зачаровать!", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("Зачаровать", "Зачаровать это оружие.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("оружие"), null);
								}
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в правую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве основного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в правую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Экипировать в левую руку (Себе)",
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}

								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в левую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве дополнительного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в левую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
//								if(!weapon.getWeaponType().isAbleToBeDropped()) {
//									return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.HerHim])"), "You cannot give away the " + weapon.getName() + "!", null);
//								}

								InventorySlot slot = InventorySlot.mainWeaponSlots[inventoryNPC.getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать основное ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Заставить [npc.name] экипировать "+weapon.getName()+" как основное оружие [npc.her]."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать основное ([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							
							} else if(index == 12) {
//								if(!weapon.getWeaponType().isAbleToBeDropped()) {
//									return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.HerHim])"), "You cannot give away the " + weapon.getName() + "!", null);
//								}
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"),
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}
								InventorySlot slot = InventorySlot.offhandWeaponSlots[inventoryNPC.getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Заставить [npc.name] экипировать "+weapon.getName()+" как вспомогательное оружие [npc.her]."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
							
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Отдать (1)", "Вы не можете передавать оружие во время секса!", null);
								
							} else if(index == 2) {
								return new Response("Отдать (5)", "Вы не можете передавать оружие во время секса!", null);
								
							} else if(index == 3) {
								return new Response("Отдать (Все)", "Вы не можете передавать оружие во время секса!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Вы не можете красить оружие во время секса!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать оружие во время секса!", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", "Вы не можете экипировать оружие, пока занимаетесь сексом!", null);
								
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", "Вы не можете экипировать оружие, пока занимаетесь сексом!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Экипировать ([npc.HerHim])"), "Вы не можете экипировать оружие, пока занимаетесь сексом!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!weapon.getWeaponType().isAbleToBeSold()) {
									return new Response("Продать (1)", "Вы не можете продать  " + weapon.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(weapon)) {
									int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Продать (1) (" + UtilText.formatAsMoney(sellPrice, "охват") + ")", "Продать  " + weapon.getName() + " на " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1, sellPrice);
										}
									};
								} else {
									return new Response("Продать (1)", inventoryNPC.getName("") + " не хочет покупать это.", null);
								}
								
							} else if(index == 2) {
								if(Main.game.getPlayer().getWeaponCount(weapon) >= 5) {
									if(!weapon.getWeaponType().isAbleToBeSold()) {
										return new Response("Продать (5)", "Вы не можете продать  " + weapon.getName() + "!", null);
										
									} else if (inventoryNPC.willBuy(weapon)) {
										int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Продать (5) (" + UtilText.formatAsMoney(sellPrice*5, "охват") + ")", "Продать пять ваших  " + weapon.getNamePlural() + " на " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 5, sellPrice);
											}
										};
									} else {
										return new Response("Продать (5)", inventoryNPC.getName("") + " не хочет это покупать.", null);
									}
									
								} else {
									return new Response("Продать (5)", "У вас нет пяти " + weapon.getNamePlural() + " продать!", null);
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeSold()) {
									return new Response("Продать (Все)", "Вы не можете продать  " + weapon.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(weapon)) {
									int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Продать (Все) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getWeaponCount(weapon), "охват") + ")",
											"Продать  " + weapon.getName() + " на " + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getWeaponCount(weapon)) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon), sellPrice);
										}
									};
								} else {
									return new Response("Продать (Все)", inventoryNPC.getName("") + " не хочет это покупать.", null);
								}
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краска/Перековка",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свои познания в [style.colourEarth(заклинаниях земли)], чтобы покрасить или перековать этот предмет."
													:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Краска/Перековка", "Ваш инвентарь полон, поэтому вы не можете изменить свойства этого оружия.", null);
									}
								} else {
									return new Response("Краска/Перековка", "Чтобы изменить свойства этого оружия, вам понадобится кисть для красок или молот для перековки.", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null || weapon.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
									return new Response("Зачаровать", "Это оружие нельзя зачаровать!", null);
									
								} else if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									return new Response("Зачаровать", "Зачаровать это оружие.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("оружие"), null);
								}
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в правую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве основного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в правую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Экипировать в левую руку (Себе)",
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}
								
								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в левую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве дополнительного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в левую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Экипировать основное ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не хочет использовать ваше оружие."), null);
								
							} else if(index == 12) {
								return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не хочет использовать ваше оружие."), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;

					switch(interactionType) {
						case SEX:
							if(index == 1) {
								return new Response("Взять (1)", "Вы не можете подобрать оружие во время мастурбации.", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Вы не можете подобрать оружие во время мастурбации.", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Вы не можете подобрать оружие во время мастурбации.", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Вы не можете красить оружие во время мастурбации.", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете накладывать заклинания на оружие во время мастурбации.", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", "Вы не можете экипировать оружие во время мастурбации.", null);
								
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", "Вы не можете экипировать оружие во время мастурбации.", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
							
						default:
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Взять (1)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (1)", "Взять один " + weapon.getName() + " с земли.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpWeapons(Main.game.getPlayer(), weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Взять (5)", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayerCell().getInventory().getWeaponCount(weapon) >= 5) {
									return new Response("Взять (5)", "Взять пять " + weapon.getNamePlural() + " с земли.", INVENTORY_MENU){
										@Override
										public void effects(){
											pickUpWeapons(Main.game.getPlayer(), weapon, 5);
										}
									};
								} else {
									return new Response("Взять (5)", "Их не пять, " + weapon.getNamePlural() + " на земле!", null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Взять (Все)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (Все)", "Забрать всё, " + weapon.getNamePlural() + " с земли.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpWeapons(Main.game.getPlayer(), weapon, Main.game.getPlayerCell().getInventory().getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayerCell().getInventory().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краска/Перековка",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свои познания в [style.colourEarth(заклинаниях земли)], чтобы покрасить или перековать этот предмет."
													:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Краска/Перековка", "Ваш инвентарь полон, поэтому вы не можете изменить свойства этого оружия.", null);
									}
								} else {
									return new Response("Краска/Перековка", "Чтобы изменить свойства этого оружия, вам понадобится кисть для красок или молот для перековки.", null);
								}
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Нельзя зачаровывать оружие прямо на земле!", null);
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в правую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве основного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipMainWeaponFromFloor(weapon)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в правую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if(index == 7) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(!weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в левую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Экипировать в левую руку (Себе)",
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}
								return new Response("Экипировать в левую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве дополнительного оружия.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ Main.game.getPlayer().equipOffhandWeaponFromFloor(weapon)
											+ "</p>");
										resetPostAction();
									}
								};
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Взять (1)", "Нельзя отобрать оружие у кого-то во время боя!", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Нельзя отобрать оружие у кого-то во время боя!", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Нельзя отобрать оружие у кого-то во время боя!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Нельзя перекрасить чужое оружие во время боя!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Нельзя зачаровывать чужое оружие, особенно во время боя!", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", "Нельзя использовать чужое оружие во время боя!", null);
								
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", "Нельзя использовать чужое оружие во время боя!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Экипировать основное (противник)", "Нельзя заставить кого-то использовать оружие во время боя!", null);
								
							} else if(index == 12) {
								return new Response("Экипировать в левую руку (Противник)", "Нельзя заставить кого-то использовать оружие во время боя!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Взять (1)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (1)", UtilText.parse(inventoryNPC, "Взять один " + weapon.getName() + " из [npc.name]."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Взять (5)", "Ваш инвентарь уже полон!", null);
								}
								if(inventoryNPC.getWeaponCount(weapon) >= 5) {
									return new Response("Взять (5)", UtilText.parse(inventoryNPC, "Взять пять штук [npc.namePos] " + weapon.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 5);
										}
									};
								} else {
									return new Response("Взять (5)", UtilText.parse(inventoryNPC, "У [npc.Name] нет пяти штук " + weapon.getNamePlural() + "!"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Взять (Все)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (Все)", UtilText.parse(inventoryNPC, "Забрать всё "+Util.intToString(inventoryNPC.getWeaponCount(weapon))+" of  [npc.namePos] " + weapon.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, inventoryNPC.getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (isWeaponDyeReforgeActionAvailable()) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = inventoryNPC.getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краска/Перековка",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свои познания в [style.colourEarth(заклинаниях земли)], чтобы покрасить или перековать этот предмет."
													:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Краска/Перековка", UtilText.parse(inventoryNPC, "Инвентарь [npc.NamePos] полон, поэтому вы не можете изменить свойства этого оружия."), null);
									}
								} else {
									return new Response("Краска/Перековка", UtilText.parse(inventoryNPC, "Чтобы изменить свойства оружия [npc.namePos], вам понадобится кисть для красок или молот для перековки."), null);
								}
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Вы не можете зачаровывать чужое оружие!", null);
								
							} else if(index == 6) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[Main.game.getPlayer().getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в правую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве основного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в правую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Экипировать в левую руку (Себе)",
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}
								
								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Экипировать в левую руку (Себе)", "Экипировать " + weapon.getName() + " в качестве дополнительного оружия.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response("Экипировать в левую руку (Себе)", weapon.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								InventorySlot slot = InventorySlot.mainWeaponSlots[inventoryNPC.getMainWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать основное ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Заставьте [npc.name] экипировать " + weapon.getName() + " как основное оружие [npc.her]."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipMainWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать основное ([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else if(index == 12) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"),
											(weapon.getWeaponType().isPlural()
												?"По мере того как " + weapon.getName() + " требуют двух рук для использования, их можно экипировать только в основной слот!"
												:"По мере того как " + weapon.getName() + " — это двуручное оружие, его можно экипировать только в основной слот!"),
											null);
								}
								
								InventorySlot slot = InventorySlot.offhandWeaponSlots[Main.game.getPlayer().getOffhandWeaponIndexToEquipTo(weapon)];
								if(weapon.isCanBeEquipped(inventoryNPC, slot)) {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"), UtilText.parse(inventoryNPC, "Заставьте [npc.name] экипировать " + weapon.getName() + " как вспомогательное оружие [npc.her]."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ inventoryNPC.equipOffhandWeaponFromInventory(weapon, inventoryNPC)
												+ "</p>");
											resetPostAction();
										}
									};
								} else {
									return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"), weapon.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Взять (1)", "Вы не можете забирать чье-то оружие прямо во время секса!", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Вы не можете забирать чье-то оружие прямо во время секса!", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Вы не можете забирать чье-то оружие прямо во время секса!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Ты не можешь красить чье-то оружие прямо во время секса!", null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Ты не можешь зачаровывать чужое оружие, особенно во время секса!", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", "Ты не можешь пользоваться чужим оружием прямо во время секса!", null);
								
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", "Ты не можешь пользоваться чужим оружием прямо во время секса!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Экипировать основное (противник)", "Ты не можешь заставить кого-то использовать оружие прямо во время секса!", null);
								
							} else if(index == 12) {
								return new Response("Экипировать в левую руку (Противник)", "Ты не можешь заставить кого-то использовать оружие прямо во время секса!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier(weapon));
								if(inventoryFull) {
									return new Response("Купить (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Купить (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "охват")+")", "Тебе не хватает денег на это!", null);
								}
								return new Response("Купить (1) (" + UtilText.formatAsMoney(sellPrice, "охват") + ")", "Купить  " + weapon.getName() + " на " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier(weapon));
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getWeaponCount(weapon) < 5)) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", UtilText.parse(inventoryNPC, "У [npc.Name] нет пяти штук "+weapon.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", "Тебе не хватает денег на это!", null);
								}
								return new Response("Купить (5) (" + UtilText.formatAsMoney(sellPrice*5, "охват") + ")", "Купить  " + weapon.getName() + " на " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier(weapon));
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getWeaponCount(weapon);
								if(inventoryFull) {
									return new Response("Купить (Все) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									int affordableCount = (int)(Main.game.getPlayer().getMoney() / sellPrice);
									if(affordableCount > 0) {
										return new Response("Купить (Макс " + affordableCount + ") (" + UtilText.formatAsMoney(sellPrice * affordableCount, "охват") + ")",
												"Купить  " + weapon.getName() + " на " + UtilText.formatAsMoney(sellPrice * affordableCount) + ".", INVENTORY_MENU) {
											@Override
											public void effects() {
												sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, affordableCount, sellPrice);
											}
										};
									} else {
										return new Response("Купить (Все) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "охват")+")", "Тебе не хватает денег на это!", null);
									}
								}
								return new Response("Купить (Все) (" + UtilText.formatAsMoney(sellPrice*count, "охват") + ")",
										"Купить  " + weapon.getName() + " на " + UtilText.formatAsMoney(sellPrice*count) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, count, sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("Краситель", UtilText.parse(inventoryNPC, "[npc.Name] не позволит тебе покрасить оружие, которое [npc.sheIs] пытается продать!"), null);
								
							} else if(index == 5) {
								return new Response("Зачаровать", "Ты не можешь зачаровывать чужое оружие!", null);
								
							} else if(index == 6) {
								return new Response("Экипировать в правую руку (Себе)", UtilText.parse(inventoryNPC, "[npc.Name] не позволит тебе экипировать оружие [npc.her], не купив его сначала."), null);
								
							} else if(index == 7) {
								return new Response("Экипировать в левую руку (Себе)", UtilText.parse(inventoryNPC, "[npc.Name] не позволит тебе экипировать оружие [npc.her], не купив его сначала."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Экипировать основное ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не собирается надевать то оружие, которое пытается продать [npc.sheIs]!"), null);
								
							} else if(index == 12) {
								return new Response(UtilText.parse(inventoryNPC, "Экипировать в левую руку ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не собирается надевать то оружие, которое пытается продать [npc.sheIs]!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode CLOTHING_INVENTORY = new DialogueNode("Одежда", "", true) {

		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Вечерний наряд";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(clothing.getDescription(owner));
			sb.append("<p>");
				for(String s : clothing.getExtraDescriptions(null, null, true)) {
					sb.append(s+"<br/>");
				}
				for(InventorySlot is : clothing.getClothingType().getEquipSlots()) {
					List<String> descriptions = clothing.getExtraDescriptions(null, is, true);
					if(!descriptions.isEmpty()) {
						sb.append("<i>When equipped into the '"+is.getName()+"слот:</i><br/>");
						for(String s : clothing.getExtraDescriptions(null, is, true)) {
							sb.append(s+"<br/>");
						}
					}
				}
			sb.append("</p>");
			sb.append((owner!=null && owner.isPlayer()
							? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
							? "<p>"
								+(inventoryNPC.willBuy(clothing)
									? inventoryNPC.getName("") + " купит за " + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getBuyModifier())) + "."
									: inventoryNPC.getName("") + " не хочет покупать это.")
								+"</p>"
							: "")
					:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
						? "<p>"
								+ inventoryNPC.getName("") + " продаст за " + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getSellModifier(clothing))) + "."
							+ "</p>" 
						: "")));
			
			
			return getItemDisplayPanel(clothing,
						clothing.getSVGString(),
						clothing.getDisplayName(true),
						sb.toString())
					+(interactionType==InventoryInteraction.CHARACTER_CREATION
						?CharacterCreation.getCheckingClothingDescription()
						:"");
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("Недоступно", "Вы не можете управлять инвентарем во время плохого финала...", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);

					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"Выбросить ":"Спрятать ";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "Нельзя снимать одежду во время мастурбации.", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "Нельзя снимать одежду во время мастурбации.", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(Все)", "Нельзя снимать одежду во время мастурбации.", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Нельзя красить одежду во время мастурбации.", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить презерватив во время мастурбации.", null);
									}
									return new Response("Саботаж", "Нельзя саботировать презерватив во время мастурбации.", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать одежду во время мастурбации.", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Вы не можете надеть это, так как сейчас вы [style.colourTerrible(обездвижены)]!", null);
								}
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Так как это особая секс-сцена, вы не можете надевать одежду во время неё!", null);
										}
										if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Этот слот задействован в текущем сексуальном действии, поэтому вы не можете надеть в него одежду!", null);
										}
										if(Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "надеть"), null);
										}
										
									} else {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else {
								return null;
							}
					default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Выбросить (1)", "Вы не можете выбросить " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Выбросить (1)", "Это место заполнено, поэтому вы не можете бросить свой " + clothing.getName() + " здесь!", null);
									} else {
										return new Response("Выбросить (1)", "Выбросить свой " + clothing.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 1);
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Сложить (1)", "Вы не можете выбросить " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Сложить (1)", "Эта область заполнена, поэтому вы не можете сложить свои " + clothing.getName() + " здесь!", null);
									} else {
										return new Response("Сложить (1)", "Сложить " + clothing.getName() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Выбросить (5)", "Вы не можете выбросить " + clothing.getName() + "!", null);
										
									} else if(owner.getClothingCount(clothing) < 5) {
										return new Response("Выбросить (5)", "У вас нет пяти " + clothing.getNamePlural() + " чтобы отдать!", null);
										
									} else if(areaFull) {
										return new Response("Выбросить (5)", "Это место заполнено, поэтому вы не можете бросить свой " + clothing.getNamePlural() + " здесь!", null);
										
									} else {
										return new Response("Выбросить (5)", "Выбросить пять ваших " + clothing.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 5);
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Сложить (5)", "Вы не можете выбросить " + clothing.getName() + "!", null);
										
									} else if(owner.getClothingCount(clothing) < 5) {
										return new Response("Сложить (5)", "У вас нет пяти " + clothing.getNamePlural() + " чтобы отдать!", null);
										
									} else if(areaFull) {
										return new Response("Сложить (5)", "Эта область заполнена, поэтому вы не можете сложить свои " + clothing.getNamePlural() + " здесь!", null);
										
									} else {
										return new Response("Сложить (5)", "Сложить пять ваших " + clothing.getNamePlural() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Выбросить (Все)", "Вы не можете выбросить " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Выбросить (Все)", "Это место заполнено, поэтому вы не можете бросить свой " + clothing.getNamePlural() + " здесь!", null);
									} else {
										return new Response("Выбросить (Все)", "Выбросить все ваши " + clothing.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, owner.getClothingCount(clothing));
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Склад (Все)", "Вы не можете выбросить " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Склад (Все)", "Эта область заполнена, поэтому вы не можете сложить свои " + clothing.getNamePlural() + " здесь!", null);
									} else {
										return new Response("Склад (Все)", "Сложить все ваши " + clothing.getNamePlural() + " в этой области.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, owner.getClothingCount(clothing));
											}
										};
									}
								}
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && clothing.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краситель",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить эту одежду."
													:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Краситель", "Ваш инвентарь полон, поэтому вы не можете покрасить эту одежду.", null);
									}
								} else {
									return new Response("Краситель", "Вам нужно найти кисть для окрашивания, если вы хотите покрасить свою одежду.", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null || clothing.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
										return new Response("Зачаровать", "Эту одежду нельзя зачаровать!", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("Идентифицировать ([style.italicsArcane(Arcane"+IDENTIFICATION_ESSENCE_PRICE+"  Эссенции)])",
													"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканных эссенций, чтобы сделать это самому, или же прийти к торговцу и заплатить "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);
													
													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>Вы направляете силу "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" ваших арканных эссенций в "+clothing.getName()
																	+", и когда предмет начинает испускать слабое фиолетовое свечение, вы понимаете, какое именно зачарование на нём наложено!</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>Опознание "+clothing.getName()+" стоило вам [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Эссенции Arcane)]!</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("Опознать (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Эссенции</i>)",
													"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканные эссенции, чтобы сделать всё самому ([style.italicsBad(которых у вас нет)]), или отправляйтесь к торговцу и платите "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.", null);
										}
									}
									return new Response("Зачаровать", "Зачаровать эту одежду.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("одежда"), null);
								}
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Отдать (1)", "Вы не можете дарить одежду во время боя!", null);
								
							} else if(index == 2) {
								return new Response("Отдать (5)", "Вы не можете дарить одежду во время боя!", null);
								
							} else if(index == 3) {
								return new Response("Отдать (Все)", "Вы не можете дарить одежду во время боя!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Вы не можете красить одежду во время боя!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете чинить презерватив во время боя!", null);
									}
									return new Response("Саботаж", "Вы не можете портить презерватив во время боя!", null);
								}
								return new Response("Зачаровать", "Вы не можете зачаровывать одежду во время боя!", null);
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Вы не можете переодеваться прямо во время боя!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								return new Response("Надеть: "+Util.capitaliseSentence(slot.getName())+" (Противник)", "Вы не можете заставить противника надеть одежду прямо во время боя!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT: case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasClothing(clothing);
							
							if(index == 1) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Отдать (1)", "Вы не можете отдать " + clothing.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Отдать (1)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								return new Response("Отдать (1)", UtilText.parse(inventoryNPC, "Отдать [npc.name] " + clothing.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Отдать (5)", "Вы не можете отдать " + clothing.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Отдать (5)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									return new Response("Отдать (5)", UtilText.parse(inventoryNPC, "Отдать [npc.name] пять ваших " + clothing.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 5);
										}
									};
								} else {
									return new Response("Отдать (5)", "У вас нет пяти " + clothing.getNamePlural() + " чтобы отдать!", null);
								}
								
							} else if(index == 3) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Отдать (Все)", "Вы не можете отдать " + clothing.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Отдать (Все)", UtilText.parse(inventoryNPC, "[npc.NamePos], инвентарь уже полон!"), null);
								}
								return new Response("Отдать (Все)", UtilText.parse(inventoryNPC, "Отдать [npc.name] все ваши " + clothing.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краситель",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить этот предмет."
													:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Краситель", "Ваш инвентарь полон, поэтому вы не можете покрасить эту одежду.", null);
									}
								} else {
									return new Response("Краситель", "Вам нужно найти кисть для окрашивания, если вы хотите покрасить свою одежду.", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null || clothing.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
										return new Response("Зачаровать", "Эту одежду нельзя зачаровать!", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("Идентифицировать ([style.italicsArcane(Arcane"+IDENTIFICATION_ESSENCE_PRICE+"  Эссенции)])",
													"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканных эссенций, чтобы сделать это самому, или же прийти к торговцу и заплатить "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);

													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>Вы направляете силу "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" ваших арканных эссенций в "+clothing.getName()
																	+", и когда предмет начинает испускать слабое фиолетовое свечение, вы понимаете, какое именно зачарование на нём наложено!</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>Опознание "+clothing.getName()+" стоило вам [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Эссенции Arcane)]!</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("Опознать (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Эссенции</i>)",
													"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканные эссенции, чтобы сделать всё самому ([style.italicsBad(которых у вас нет)]), или отправляйтесь к торговцу и платите "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.", null);
										}
									}
									return new Response("Зачаровать", "Зачаровать эту одежду.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("одежда"), null);
								}
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", INVENTORY_MENU) {
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
//								if(!clothing.getClothingType().isAbleToBeDropped()) {
//									return new Response(
//											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
//											"You cannot give away the " + clothing.getName() + "!",
//											null);
//								}
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(inventoryNPC.isAbleToEquip(clothing, slot, true, Main.game.getPlayer()) && clothing.isEnslavementClothing() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
										boolean willEnslave = !inventoryNPC.isSlave() && inventoryNPC.isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense();
										return new Response(
												UtilText.parse(inventoryNPC,
														!willEnslave
															?"[style.colourMinorBad(Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim]))]"
															:"[style.colourArcane(Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim]))]"),
												UtilText.parse(inventoryNPC,
														"Заставить [npc.name] экипировать "+clothing.getName()+"."
														+(!willEnslave
															?"<br/><i>Несмотря на то, что "+clothing.getName()+" "
																	+(clothing.getClothingType().isPlural()?"имеют чары порабощения, они":"имеет чары порабощения, это")
																	+" [style.colourMinorBad(не поработит [npc.name])], так как"
																	+ (Main.game.getPlayer().isHasSlaverLicense()
																			?" [npc.sheIsFull] не подходит для порабощения"
																			:" у вас нет лицензии работорговца")
																	+"!</i>"
															:"<br/><i>Благодаря тому, что "+clothing.getName()+" "
																	+(clothing.getClothingType().isPlural()?"имеют чары порабощения, они":"имеет чары порабощения, это")
																	+" [style.colourArcane(поработит [npc.name])], вы станете [npc.her] новым владельцем!</i>")),
												INVENTORY_MENU){
											@Override
											public DialogueNode getNextDialogue() {
												if(inventoryNPC.getEnslavementDialogue(clothing)!=null) {
													return inventoryNPC.getEnslavementDialogue(clothing);
													
												} else {
													return INVENTORY_MENU;
												}
											}
											@Override
											public void effects() {
												List<NPC> enslavementTargets = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
//												enslavementTargets.removeIf((npc) -> Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
												enslavementTargets.removeIf((npc) -> !Main.combat.getEnemies(Main.game.getPlayer()).contains(npc));
												if(enslavementTargets.size()<=1) {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getDefaultDialogue(false));
												} else {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getSavedDialogueNode());
												}
												if(inventoryNPC.getEnslavementDialogue(clothing)==null) {
													Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
													
												} else {
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
												}
											}
										};
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
												UtilText.parse(inventoryNPC, "Заставить [npc.name] экипировать "+clothing.getName()+"!"),
												INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
									}
									
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
							
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Отдать (1)", "Вы не можете передать одежду во время секса!", null);
								
							} else if(index == 2) {
								return new Response("Отдать (5)", "Вы не можете передать одежду во время секса!", null);
								
							} else if(index == 3) {
								return new Response("Отдать (Все)", "Вы не можете передать одежду во время секса!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Вы не можете покрасить одежду во время секса!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете починить презерватив во время секса!", null);
									}
									return new Response("Саботаж", "Вы не можете испортить презерватив во время секса!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать одежду, когда вы занимаетесь сексом!", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Вы не можете надеть это, так как сейчас вы [style.colourTerrible(обездвижены)]!", null);
								}
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Так как это особая секс-сцена, вы не можете надевать одежду во время неё!", null);
										}
										if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Этот слот задействован в текущем сексуальном действии, поэтому вы не можете надеть в него одежду!", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "надеть"), null);
										}
										
									} else {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
//								if(!clothing.getClothingType().isAbleToBeDropped()) {
//									return new Response(
//											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
//											"You cannot give away the " + clothing.getName() + "!",
//											null);
//								}
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											UtilText.parse(inventoryNPC, "Вы не можете заставить [npc.name] надеть " + clothing.getName() + " так как ты сейчас [style.colourTerrible(парализован)]!"),
											null);
								}
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), inventoryNPC, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													"Так как это особая секс-сцена, вы не можете надевать одежду во время неё!",
													null);
										}
										if(!Main.sex.isClothingEquipAvailable(inventoryNPC, slot, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													"Этот слот задействован в текущем сексуальном действии, поэтому вы не можете надеть в него одежду!",
													null);
										}
										if(inventoryNPC.isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "Заставьте [npc.name] экипировать " + clothing.getName() + "."),
													Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, inventoryNPC.getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response(UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "[npc.Name] не может экипировать " + clothing.getName() + ", так как другая одежда мешает [npc.herHim] это сделать!"), null);
										}
										
									} else {
										return new Response(UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response(UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"), clothing.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("Продать (1)", "Вы не можете продать  " + clothing.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(clothing)) {
									int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Продать (1) (" + UtilText.formatAsMoney(sellPrice, "охват") + ")", "Продать  " + clothing.getName() + " на " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1, sellPrice);
										}
									};
								} else {
									return new Response("Продать (1)", inventoryNPC.getName("") + " не хочет покупать это.", null);
								}
								
							} else if(index == 2) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("Продать (5)", "Вы не можете продать  " + clothing.getName() + "!", null);
									
								} else if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									if (inventoryNPC.willBuy(clothing)) {
										int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Продать (5) (" + UtilText.formatAsMoney(sellPrice*5, "охват") + ")", "Продать пять ваших  " + clothing.getNamePlural() + " на " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, 5, sellPrice);
											}
										};
									} else {
										return new Response("Продать (5)", inventoryNPC.getName("") + " не хочет это покупать.", null);
									}
									
								} else {
									return new Response("Продать (5)", "У вас нет пяти " + clothing.getNamePlural() + " продать!", null);
								}
								
							} else if(index == 3) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("Продать (Все)", "Вы не можете продать  " + clothing.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(clothing)) {
									int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Продать (Все) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getClothingCount(clothing), "охват") + ")",
											"Продать  " + clothing.getName() + " на " + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getClothingCount(clothing)) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing), sellPrice);
										}
									};
								} else {
									return new Response("Продать (Все)", inventoryNPC.getName("") + " не хочет это покупать.", null);
								}
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && clothing.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краситель", 
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить этот предмет."
													:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Краситель", "Ваш инвентарь полон, поэтому вы не можете покрасить эту одежду.", null);
									}
								} else {
									return new Response("Краситель", "Вам нужно найти кисть для окрашивания, если вы хотите покрасить свою одежду.", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null || clothing.getItemTags().contains(ItemTag.UNENCHANTABLE)) {
										return new Response("Зачаровать", "Эту одежду нельзя зачаровать!", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("Идентифицировать ([style.italicsArcane(Arcane"+IDENTIFICATION_ESSENCE_PRICE+"  Эссенции)])",
													"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканных эссенций, чтобы сделать это самому, или же прийти к торговцу и заплатить "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);

													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>Вы направляете силу "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" ваших арканных эссенций в "+clothing.getName()
																	+", и когда предмет начинает испускать слабое фиолетовое свечение, вы понимаете, какое именно зачарование на нём наложено!</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>Опознание "+clothing.getName()+" стоило вам [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Эссенции Arcane)]!</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("Опознать (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Эссенции</i>)",
													"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканные эссенции, чтобы сделать всё самому ([style.italicsBad(которых у вас нет)]), или отправляйтесь к торговцу и платите "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.", null);
										}
									}
									return new Response("Зачаровать", "Зачаровать эту одежду.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
									
								} else {
									return new Response("Зачаровать", getEnchantmentNotDiscoveredText("одежда"), null);
								}

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							}
//							else if (index == 10) {
//								return getQuickTradeResponse();
//
//							}
							else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								return new Response(UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не хочет носить вашу одежду."), null);
								
							} else if (index == 10 && !clothing.isEnchantmentKnown()) {
								if(!inventoryNPC.willBuy(clothing)) {
									return new Response("Опознать", inventoryNPC.getName("") + " не может опознать одежду!", null);
									
								} else if(Main.game.getPlayer().getMoney() < IDENTIFICATION_PRICE){
									// don't format as money because we don't want to highlight non-selectable choices
									return new Response("Опознать (" + UtilText.formatAsMoneyUncoloured(IDENTIFICATION_PRICE, "охват") + ")", "У вас недостаточно денег!", null);
									
								}else {
									return new Response("Опознать (" + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "охват") + ")",
												"Имеет " + clothing.getName() + " опознанный для " + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "охват") + ".", CLOTHING_INVENTORY){
										@Override
										public void effects(){
											Main.game.getPlayer().incrementMoney(-IDENTIFICATION_PRICE);

											String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
											
											clothing = AbstractClothing.enchantmentRemovedClothing;
											
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ UtilText.parse(inventoryNPC,
																"Вы передаете " + UtilText.formatAsMoney(IDENTIFICATION_PRICE) + " [npc.name], который тут же вливает несколько флаконов арканической эссенции в специальное устройство для идентификации, после чего использует его, чтобы раскрыть чары на вашем "+clothing.getName()+".")
													+ "</p>"
													+enchantmentRemovedString);
											
											RenderingEngine.setPage(Main.game.getPlayer(), clothing);
										}
									};
								}
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {

					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
					switch(interactionType) {
						case CHARACTER_CREATION:
							if (index == 1) {
								if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
										|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
									return new Response("На сцену", "Вам нужно быть одетым так, чтобы тело было прикрыто, а также надеть пару туфель.", null);
									
								} else {
									return new Response("На сцену", "Теперь ты готов выйти на сцену.", CharacterCreation.CHOOSE_BACKGROUND) {
										@Override
										public void effects() {
											CharacterCreation.moveNPCIntoPlayerTile();
										}
									};
								}
								
							} else if(index == 4) {
								if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
									return new Response("Снять всё", "Сейчас на тебе ничего нет, снимать нечего.", null);
								}
								else{
									return new Response("Снять всё", "Снять как можно больше одежды.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
										}
									};
								}
								
							} else if(index == 5) {
								return new Response("Изменить цвет", "Изменить цвет этого предмета одежды.", DYE_CLOTHING_CHARACTER_CREATION) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
										}
									};
									
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
							
							} else {
								return null;
							}
					
					case SEX:
						if(index == 1) {
							return new Response("Взять (1)", "Вы не можете подбирать одежду во время мастурбации.", null);
							
						} else if(index == 2) {
							return new Response("Взять (5)", "Вы не можете подбирать одежду во время мастурбации.", null);
							
						} else if(index == 3) {
							return new Response("Взять (Все)", "Вы не можете подбирать одежду во время мастурбации.", null);
							
						} else if(index == 4) {
							return new Response("Краситель", "Нельзя красить одежду во время мастурбации.", null);
							
						} else if(index == 5) {
							if(clothing.isCondom()) {
								if(clothing.getCondomEffect().getPotency().isNegative()) {
									return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете чинить презервативы на земле!", null);
								}
								return new Response("Саботаж", "Нельзя портить презервативы, лежащие на земле!", null);
							}
							if(!clothing.isEnchantmentKnown()) {
								return new Response("Опознать", "Нельзя опознавать одежду во время секса!", null);
							}
							return new Response("Зачаровать", "Нельзя зачаровывать одежду, лежащую на земле!", null);

						} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
							InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Вы не можете надеть это, так как сейчас вы [style.colourTerrible(обездвижены)]!", null);
							}
							if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
								if(clothing.isAbleToBeEquippedDuringSex(slot).getKey()) {
									if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Так как это особая секс-сцена, вы не можете надевать одежду во время неё!", null);
									}
									if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Этот слот задействован в текущем сексуальном действии, поэтому вы не можете надеть в него одежду!", null);
									}
									if (Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
												Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "надеть"), null);
									}
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
								}
							} else {
								return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();

						} else {
							return null;
						}
					
					default:
						if(index == 1) {
							if(inventoryFull) {
								return new Response("Взять (1)", "Ваш инвентарь уже полон!", null);
							}
							return new Response("Взять (1)", "Взять один " + clothing.getName() + " с земли.", INVENTORY_MENU){
								@Override
								public void effects(){
									pickUpClothing(Main.game.getPlayer(), clothing, 1);
								}
							};
							
						} else if(index == 2) {
							if(inventoryFull) {
								return new Response("Взять (5)", "Ваш инвентарь уже полон!", null);
							}
							if(Main.game.getPlayerCell().getInventory().getClothingCount(clothing) >= 5) {
								return new Response("Взять (5)", "Взять пять " + clothing.getNamePlural() + " с земли.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpClothing(Main.game.getPlayer(), clothing, 5);
									}
								};
							} else {
								return new Response("Взять (5)", "Их не пять, " + clothing.getNamePlural() + " на земле!", null);
							}
							
						} else if(index == 3) {
							if(inventoryFull) {
								return new Response("Взять (Все)", "Ваш инвентарь уже полон!", null);
							}
							return new Response("Взять (Все)", "Забрать всё, " + clothing.getNamePlural() + " с земли.", INVENTORY_MENU){
								@Override
								public void effects(){
									pickUpClothing(Main.game.getPlayer(), clothing, Main.game.getPlayerCell().getInventory().getClothingCount(clothing));
								}
							};
							
						} else if (index==4) {
							if (isClothingDyeActionAvailable()) {
								boolean hasFullInventory = Main.game.getPlayerCell().getInventory().isInventoryFull();
								boolean isDyeingStackItem = Main.game.getPlayerCell().getInventory().getAllClothingInInventory().get(clothing) > 1;
								boolean canDye = !(isDyeingStackItem && hasFullInventory);
								if (canDye) {
									return new Response("Краситель", 
											Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить этот предмет."
												:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
											DYE_CLOTHING) {
										@Override
										public void effects() {
											resetClothingDyeColours();
										}
									};
								} else {
									return new Response("Краситель", "Ваш инвентарь полон, поэтому вы не можете покрасить эту одежду.", null);
								}
							} else {
								return new Response("Краситель", "Вам нужно найти кисть для окрашивания, если вы хотите покрасить свою одежду.", null);
							}
							
						} else if(index == 5) {
							if(clothing.isCondom()) {
								if(clothing.getCondomEffect().getPotency().isNegative()) {
									return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете чинить презервативы на земле!", null);
								}
								return new Response("Саботаж", "Нельзя портить презервативы, лежащие на земле!", null);
							}
							if(!clothing.isEnchantmentKnown()) {
								if(Main.game.getPlayer().getEssenceCount() >= IDENTIFICATION_ESSENCE_PRICE) {
									return new Response("Идентифицировать ([style.italicsArcane(Arcane"+IDENTIFICATION_ESSENCE_PRICE+"  Эссенции)])",
											"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканных эссенций, чтобы сделать это самому, или же прийти к торговцу и заплатить "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.",
											CLOTHING_INVENTORY) {
										@Override
										public void effects() {
											Main.game.getPlayer().incrementEssenceCount(-IDENTIFICATION_ESSENCE_PRICE, false);
											
											Main.game.getPlayerCell().getInventory().removeClothing(clothing);
											String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
											Main.game.getPlayerCell().getInventory().addClothing(clothing);
											
//											clothing = AbstractClothing.enchantmentRemovedClothing;
											
											Main.game.getTextEndStringBuilder().append(
													"<p>Вы направляете силу "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" ваших арканных эссенций в "+clothing.getName()
															+", и когда предмет начинает испускать слабое фиолетовое свечение, вы понимаете, какое именно зачарование на нём наложено!</p>"
													+ enchantmentRemovedString
													+ "<p style='text-align:center;'>Опознание "+clothing.getName()+" стоило вам [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Эссенции Arcane)]!</p>");
											RenderingEngine.setPage(Main.game.getPlayer(), clothing);
										}
									};
								} else {
									return new Response("Опознать (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Эссенции</i>)",
											"Чтобы идентифицировать "+clothing.getName()+", вы можете либо потратить "+IDENTIFICATION_ESSENCE_PRICE+" арканные эссенции, чтобы сделать всё самому ([style.italicsBad(которых у вас нет)]), или отправляйтесь к торговцу и платите "+IDENTIFICATION_PRICE+" огнями, чтобы они сделали всё за тебя.", null);
								}
							}
							return new Response("Зачаровать", "Нельзя зачаровывать одежду, лежащую на земле!", null);
							
						} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
							InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
							if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
								return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
									}
								};
							} else {
								return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Взять (1)", "Нельзя снимать с кого-то одежду во время боя!", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Нельзя снимать с кого-то одежду во время боя!", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Нельзя снимать с кого-то одежду во время боя!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Нельзя красить чью-то одежду во время боя!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить чужой презерватив, тем более во время боя!", null);
									}
									return new Response("Саботаж", "Вы не можете саботировать чужой презерватив, тем более во время боя!", null);
								}
								return new Response("Зачаровать", "Вы не можете зачаровать чужую одежду, тем более во время боя!", null);
								
							} else if(index == 6) {
								return new Response("Надеть (на себя)", "Вы не можете надеть чужую одежду во время боя!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Надеть: ([npc.HerHim])"), "Вы не можете заставить кого-то надеть одежду во время боя!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT: case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Взять (1)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (1)", UtilText.parse(inventoryNPC, "Взять " + clothing.getName() + " из [npc.name]."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Взять (5)", "Ваш инвентарь уже полон!", null);
								}
								if(inventoryNPC.getClothingCount(clothing) >= 5) {
									return new Response("Взять (5)", UtilText.parse(inventoryNPC, "Взять пять штук [npc.namePos] " + clothing.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, 5);
										}
									};
								} else {
									return new Response("Взять (5)", UtilText.parse(inventoryNPC, "У [npc.Name] нет пяти штук " + clothing.getNamePlural() + "!"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Взять (Все)", "Ваш инвентарь уже полон!", null);
								}
								return new Response("Взять (Все)", UtilText.parse(inventoryNPC, "Забрать всё "+Util.intToString(inventoryNPC.getClothingCount(clothing))+" of  [npc.namePos] " + clothing.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, inventoryNPC.getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (isClothingDyeActionAvailable()) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull();
									boolean isDyeingStackItem = clothing!=null && inventoryNPC.getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Краситель", 
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить этот предмет."
													:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Краситель", UtilText.parse(inventoryNPC, "Инвентарь [npc.NamePos] полон, поэтому вы не можете покрасить эту вещь."), null);
									}
								} else {
									return new Response("Краситель", UtilText.parse(inventoryNPC, "Вам понадобится другая кисть для красок, если вы хотите покрасить одежду [npc.namePos]."), null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете чинить чужие презервативы!", null);
									}
									return new Response("Саботаж", "Вы не можете портить чужие презервативы!", null);
								}
								return new Response("Зачаровать", "Вы не можете зачаровывать чужую одежду!", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(inventoryNPC.isAbleToEquip(clothing, slot, true, Main.game.getPlayer()) && clothing.isEnslavementClothing() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
										boolean willEnslave = !inventoryNPC.isSlave() && inventoryNPC.isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense();
										return new Response(
												UtilText.parse(inventoryNPC,
														!willEnslave
															?"[style.colourMinorBad(Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim]))]"
															:"[style.colourArcane(Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim]))]"),
												UtilText.parse(inventoryNPC,
														"Заставить [npc.name] экипировать "+clothing.getName()+"."
														+(!willEnslave
															?"<br/><i>Несмотря на то, что "+clothing.getName()+" "
																	+(clothing.getClothingType().isPlural()?"имеют чары порабощения, они":"имеет чары порабощения, это")
																	+" [style.colourMinorBad(не поработит [npc.name])], так как"
																	+ (Main.game.getPlayer().isHasSlaverLicense()
																			?" [npc.sheIsFull] не подходит для порабощения"
																			:" у вас нет лицензии работорговца")
																	+"!</i>"
															:"<br/><i>Благодаря тому, что "+clothing.getName()+" "
																	+(clothing.getClothingType().isPlural()?"имеют чары порабощения, они":"имеет чары порабощения, это")
																	+" [style.colourArcane(поработит [npc.name])], вы станете [npc.her] новым владельцем!</i>")),
												INVENTORY_MENU){
											@Override
											public DialogueNode getNextDialogue() {
												if(inventoryNPC.getEnslavementDialogue(clothing)!=null) {//inventoryNPC.isAbleToBeEnslaved() && !inventoryNPC.isSlave()) {
													return inventoryNPC.getEnslavementDialogue(clothing);
													
												} else {
													return INVENTORY_MENU;
												}
											}
											@Override
											public void effects() {
												List<NPC> enslavementTargets = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
//												enslavementTargets.removeIf((npc) -> Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
												enslavementTargets.removeIf((npc) -> !Main.combat.getEnemies(Main.game.getPlayer()).contains(npc));
												if(enslavementTargets.size()<=1) {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getDefaultDialogue(false));
												} else {
													SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getSavedDialogueNode());
												}
												if(inventoryNPC.getEnslavementDialogue(clothing)==null) {
													Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
													
												} else {
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
												}
											}
										};
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
												UtilText.parse(inventoryNPC, "Заставьте [npc.name] экипировать " + clothing.getName() + "."),
												INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
									}
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Взять (1)", "Вы не можете снимать с кого-то одежду прямо во время секса!", null);
								
							} else if(index == 2) {
								return new Response("Взять (5)", "Вы не можете снимать с кого-то одежду прямо во время секса!", null);
								
							} else if(index == 3) {
								return new Response("Взять (Все)", "Вы не можете снимать с кого-то одежду прямо во время секса!", null);
								
							} else if(index == 4) {
								return new Response("Краситель", "Вы не можете красить чужую одежду прямо во время секса!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете чинить чужой презерватив, тем более во время секса с этим человеком!", null);
									}
									return new Response("Саботаж", "Вы не можете портить чужой презерватив, тем более во время секса с этим человеком!", null);
								}
								return new Response("Зачаровать", "Вы не можете зачаровывать чужую одежду, тем более во время секса с этим человеком!", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) { //TODO ???
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Вы не можете надеть это, так как сейчас вы [style.colourTerrible(обездвижены)]!", null);
								}
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey() && !inventoryNPC.isTrader()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), Main.game.getPlayer(), clothing)) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Так как это особая секс-сцена, вы не можете надевать одежду во время неё!", null);
										}
										if(!Main.sex.isClothingEquipAvailable(Main.game.getPlayer(), slot, clothing)) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Этот слот задействован в текущем сексуальном действии, поэтому вы не можете надеть в него одежду!", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), "Экипировать " + clothing.getName() + ".", Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), getClothingBlockingRemovalText(Main.game.getPlayer(), "надеть"), null);
										}
										
									} else {
										return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.isAbleToBeEquippedDuringSex(slot).getValue(), null);
									}
									
								} else {
									return new Response("Надеть: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											UtilText.parse(inventoryNPC, "Вы не можете заставить [npc.name] надеть " + clothing.getName() + " так как ты сейчас [style.colourTerrible(парализован)]!"),
											null);
								}
								Value<Boolean, String> equipAllowed = inventoryNPC.isInventoryEquipAllowed(clothing, slot);
								if(!equipAllowed.getKey()) {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											UtilText.parse(inventoryNPC, equipAllowed.getValue()),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(clothing.isAbleToBeEquippedDuringSex(slot).getKey() && !inventoryNPC.isTrader()) {
										if(!Main.sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer(), inventoryNPC, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													"Так как это особая секс-сцена, вы не можете надевать одежду во время неё!",
													null);
										}
										if(!Main.sex.isClothingEquipAvailable(inventoryNPC, slot, clothing)) {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"), 
													"Этот слот задействован в текущем сексуальном действии, поэтому вы не можете надеть в него одежду!",
													null);
										}
										if (inventoryNPC.isAbleToEquip(clothing, slot, false, Main.game.getPlayer())) {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "Заставьте [npc.name] экипировать " + clothing.getName() + "."),
													Main.sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
													Main.sex.setEquipClothingText(c, inventoryNPC.getUnequipDescription());
													Main.mainController.openInventory();
													Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Main.sex.setSexStarted(true);
												}
											};
										} else {
											return new Response(
													UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
													UtilText.parse(inventoryNPC, "[npc.Name] не может экипировать " + clothing.getName() + ", так как другая одежда мешает [npc.herHim] это сделать!"),
													null);
										}
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
												clothing.isAbleToBeEquippedDuringSex(slot).getValue(),
												null);
									}
									
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "Надеть: "+Util.capitaliseSentence(slot.getName())+" ([npc.HerHim])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier(clothing));
								if(inventoryFull) {
									return new Response("Купить (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Купить (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "охват")+")", "Тебе не хватает денег на это!", null);
								}
								return new Response("Купить (1) (" + UtilText.formatAsMoney(sellPrice, "охват") + ")", "Купить  " + clothing.getName() + " на " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier(clothing));
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getClothingCount(clothing) < 5)) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", UtilText.parse(inventoryNPC, "У [npc.Name] нет пяти штук "+clothing.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("Купить (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "охват")+")", "Тебе не хватает денег на это!", null);
								}
								return new Response("Купить (5) (" + UtilText.formatAsMoney(sellPrice*5, "охват") + ")", "Купить  " + clothing.getName() + " на " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier(clothing));
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getClothingCount(clothing);
								if(inventoryFull) {
									return new Response("Купить (Все) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "охват")+")", "Ваш инвентарь уже полон!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									int affordableCount = (int)(Main.game.getPlayer().getMoney() / sellPrice);
									if(affordableCount > 0) {
										return new Response("Купить (Макс " + affordableCount + ") (" + UtilText.formatAsMoney(sellPrice * affordableCount, "охват") + ")",
												"Купить  " + clothing.getName() + " на " + UtilText.formatAsMoney(sellPrice * affordableCount) + ".", INVENTORY_MENU) {
											@Override
											public void effects() {
												sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, affordableCount, sellPrice);
											}
										};
									} else {
										return new Response("Купить (Все) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "охват")+")", "Тебе не хватает денег на это!", null);
									}
								}
								return new Response("Купить (Все) (" + UtilText.formatAsMoney(sellPrice*count, "охват") + ")",
										"Купить  " + clothing.getName() + " на " + UtilText.formatAsMoney(sellPrice*count) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, count, sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("Краситель", UtilText.parse(inventoryNPC, "[npc.Name] не позволит тебе перекрасить одежду, которую пытается продать [npc.sheIs]!"), null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Ты не можешь починить чужой презерватив!", null);
									}
									return new Response("Саботаж", "Ты не можешь испортить чужой презерватив!", null);
								}
								return new Response("Зачаровать", "Ты не можешь зачаровать чужую одежду!", null);
								
							} else if(index == 6) {
								return new Response("Надеть (на себя)", UtilText.parse(inventoryNPC, "[npc.Name] не позволит тебе использовать одежду [npc.her], не купив её сначала."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Экипировать ([npc.HerHim])"), UtilText.parse(inventoryNPC, "[npc.Name] не станет носить одежду, которую пытается продать [npc.sheIs]!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
			
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode WEAPON_EQUIPPED = new DialogueNode("Оружие экипировано", "", true) {
		
		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<String> extraDescriptions = weapon.getExtraDescriptions(owner);
			if(!extraDescriptions.isEmpty()) {
				sb.append("<p>");
					for(int i=0 ; i<extraDescriptions.size() ; i++) {
						sb.append(extraDescriptions.get(i));
						if(i<extraDescriptions.size()-1) {
							sb.append("<br/>");
						}
					}
				sb.append("</p>");
			}
			return getItemDisplayPanel(weapon,
					weapon.getSVGEquippedString(owner),
					Util.capitaliseSentence(weapon.getDisplayName(true)),
					weapon.getDescription(owner)
					 	+sb.toString());
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("Недоступно", "Вы не можете управлять инвентарем во время плохого финала...", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								return new Response("Выбросить", "Вы не можете менять оружие во время боя!", null);
								
							} else {
								return new Response("Спрятать", "Вы не можете менять оружие во время боя!", null);
							}
							
						} else if (index==4) {
							return new Response("Краска/Перековка", "Ты не можешь изменять свойства "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" оружия в бою!", null);
							
						} else if(index == 5) {
							return new Response("Зачаровать", "Ты не можешь зачаровать экипированное оружие!", null);
							
						} if(index == 6) {
							return new Response("Снять", "Вы не можете менять оружие во время боя!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING: case CHARACTER_CREATION:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Выбросить", "Вы не можете выбросить " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Выбросить", "Это место заполнено, поэтому вы не можете бросить свой " + weapon.getName() + " здесь!", null);
								} else {
									return new Response("Выбросить", "Выбросить свой " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Спрятать", "Вы не можете выбросить " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Спрятать", "Эта область заполнена, поэтому вы не можете сложить свои " + weapon.getName() + " здесь!", null);
								} else {
									return new Response("Спрятать", "Убрать ваше " + weapon.getName() + " в этой области.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
										}
									};
								}
							}
							
						} else if (index==4) {
							if (isWeaponDyeReforgeActionAvailable()) {
								return new Response("Краска/Перековка", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Используйте ваше мастерство в [style.colourEarth(земляных заклинаниях)], чтобы изменить свойства этого оружия."
											:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
										DYE_EQUIPPED_WEAPON) {
									@Override
									public void effects() {
										resetWeaponDyeColours();
									}
								};
							} else {
								return new Response("Краситель", "Чтобы изменить свойства этого оружия, вам понадобится кисть для красок или молот для перековки.", null);
							}
							
						} else if(index == 5) {
							return new Response("Зачаровать", "Ты не можешь зачаровать экипированное оружие!", null);
							
						} else if(index == 6) {
							return new Response("Снять", "Снять " + weapon.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, false, true))
											+ "</p>");
									resetPostAction();
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Выбросить", "Вы не можете выбросить " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Выбросить", "Это место заполнено, поэтому вы не можете бросить свой " + weapon.getName() + " здесь!", null);
								} else {
									return new Response("Выбросить", "Выбросить свой " + weapon.getName() + ".", Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.sex.setUnequipWeaponText(weapon,
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Спрятать", "Вы не можете выбросить " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Спрятать", "Эта область заполнена, поэтому вы не можете сложить свои " + weapon.getName() + " здесь!", null);
								} else {
									return new Response("Спрятать", "Убрать ваше " + weapon.getName() + " в этой области.", Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.sex.setUnequipWeaponText(weapon,
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								}
							}
							
						} else if (index==4) {
							return new Response("Краска/Перековка", "Ты не можешь изменять свойства "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" оружие в секс!", null);
							
						} else if(index == 5) {
							return new Response("Зачаровать", "Ты не можешь зачаровать экипированное оружие!", null);
							
						} else if(index == 6) {
							return new Response("Снять", "Снять " + weapon.getName() + ".", Main.sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									Main.sex.setUnequipWeaponText(weapon,
											"<p style='text-align:center;'>"
												+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, false, true))
											+ "</p>");
									resetPostAction();
									Main.mainController.openInventory();
									Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Main.sex.setSexStarted(true);
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				switch(interactionType) {
					case COMBAT:
						if(index == 1) {
							return new Response("Выбросить", "Нельзя заставить кого-то выпустить оружие прямо во время боя!", null);
							
						} else if(index == 2) {
							return new Response("Взять", "Нельзя отобрать оружие у кого-то прямо во время боя!", null);
							
						} else if (index==4) {
							return new Response("Краска/Перековка", "Нельзя изменять свойства чужого экипированного оружия прямо во время боя!", null);
							
						} else if(index == 5) {
							return new Response("Зачаровать", "Нельзя зачаровывать чужое экипированное оружие, особенно во время боя!", null);
							
						} else if (index == 6) {
							return new Response("Снять", "Нельзя снять оружие с кого-то прямо во время боя!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT:  case CHARACTER_CREATION:
						boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
						
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Выбросить", UtilText.parse(inventoryNPC, "[npc.Name] не может выпустить [npc.her] " + weapon.getName() + "!"), null);
									
								} else if(areaFull) {
									return new Response("Выбросить", UtilText.parse(inventoryNPC, "Место занято, поэтому [npc.name] не может выпустить [npc.her] " + weapon.getName() + " здесь!"), null);
								} else {
									return new Response("Выбросить", UtilText.parse(inventoryNPC, "Заставьте [npc.name] выпустить [npc.her] " + weapon.getName() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, true, false))
													+ "</p>");
											resetPostAction();
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Выбросить", UtilText.parse(inventoryNPC, "[npc.Name] не может выпустить [npc.her] " + weapon.getName() + "!"), null);
									
								} else if(areaFull) {
									return new Response("Спрятать", UtilText.parse(inventoryNPC, "Место занято, поэтому [npc.name] не может убрать [npc.her] " + weapon.getName() + " здесь!"), null);
								} else {
									return new Response("Спрятать", UtilText.parse(inventoryNPC, "Получите [npc.name], чтобы хранить [npc.her] " + weapon.getName() + " в этой области."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, true, false))
													+ "</p>");
											resetPostAction();
										}
									};
								}
							}
							
						} else if (index == 2) {
							if(inventoryFull) {
								return new Response("Взять", "Ваш инвентарь полон, так что вы не можете взять это!", null);
								
							} else {
								return new Response("Взять",
										UtilText.parse(inventoryNPC, "Взять [npc.namePos] " + weapon.getName() + " и добавить его в свой инвентарь."),
										INVENTORY_MENU){
									@Override
									public void effects(){
										inventoryNPC.unequipWeaponIntoVoid(weaponSlot, weapon, true);
										Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ (Main.game.getPlayer().addWeapon(weapon, false))
												+ "</p>");
										resetPostAction();
									}
								};
							}
							
						} else if (index==4) {
							if (isWeaponDyeReforgeActionAvailable()) {
								return new Response("Краска/Перековка", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Используйте ваше мастерство в [style.colourEarth(земляных заклинаниях)], чтобы изменить свойства этого оружия."
											:"Используйте кисть для окрашивания или молот для перековки, чтобы изменить свойства этого оружия.",
										DYE_EQUIPPED_WEAPON) {
									@Override
									public void effects() {
										resetWeaponDyeColours();
									}
								};
							} else {
								return new Response("Краситель", UtilText.parse(inventoryNPC, "Чтобы изменить свойства оружия [npc.namePos], вам понадобится кисть для красок или молот для перековки."), null);
							}
							
						} else if(index == 5) {
							return new Response("Зачаровать", "Ты не можешь зачаровать экипированное оружие!", null);
							
						} else if(index == 6) {
							if(!weapon.getWeaponType().isAbleToBeDropped()) {
								return new Response("Снять", UtilText.parse(inventoryNPC, "Так как это уникальное оружие, " + weapon.getName() + " нельзя убрать в [npc.namePos] инвентарь."), null);
							}
							return new Response("Снять", UtilText.parse(inventoryNPC, "Получите [npc.name], чтобы снять [npc.her] " + weapon.getName() + "."), INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, false, false))
											+ "</p>");
									resetPostAction();
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if(index == 1) {
							return new Response("Выбросить", "Вы не можете снять чье-то оружие во время секса!", null);
							
						} else if (index == 2) {
							return new Response("Взять", "Вы не можете забрать чье-то оружие во время секса!", null);
							
						} else if (index==4) {
							return new Response("Краска/Перековка", UtilText.parse(inventoryNPC, "Вы не можете изменять свойства [npc.namePos] оружия во время секса!"), null);
						
						} else if(index == 5) {
							return new Response("Зачаровать", "Ты не можешь зачаровать экипированное оружие!", null);
							
						} else if(index == 6) {
							return new Response("Снять", "Вы не можете снять чье-то оружие во время секса!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case TRADING:
						if(index == 1) {
							return new Response("Выбросить", "Нельзя заставить кого-то выпустить оружие!", null);
							
						} else if (index == 2) {
							return new Response("Взять", "Нельзя забрать чужое оружие!", null);
							
						} else if (index==4) {
							return new Response("Краска/Перековка", UtilText.parse(inventoryNPC, "Нельзя изменять свойства оружия [npc.namePos]!"), null);
							
						} else if(index == 5) {
							return new Response("Зачаровать", "Нельзя зачаровывать чужое экипированное оружие!", null);
							
						} else if(index == 6) {
							return new Response("Снять", "Нельзя снимать чужое оружие!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
					}
				
				}
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	public static final DialogueNode CLOTHING_EQUIPPED = new DialogueNode("Одежда надета", "", true) {

		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Вечерний наряд";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Инвентарь (Быстрое управление <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВКЛЮЧЕНО</b>)";
			} else {
				return "Инвентарь";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(clothing.getDescription(owner));
			sb.append("<p>");
				GameCharacter descriptionTarget = owner; //Main.game.isInSex()?owner:Main.game.getPlayer()
				for(String s : clothing.getExtraDescriptions(descriptionTarget, null, true)) {
					sb.append(s+"<br/>");
				}
				for(String s : clothing.getExtraDescriptions(descriptionTarget, clothing.getSlotEquippedTo(), true)) {
					sb.append(s+"<br/>");
				}
			sb.append("</p>");
			sb.append(Main.game.isInSex()||Main.game.isInCombat()?clothing.getDisplacementBlockingDescriptions(owner):"");
			
			return getItemDisplayPanel(clothing,
						clothing.getSVGEquippedString(owner),
						clothing.getDisplayName(true),
						sb.toString())
					+(interactionType==InventoryInteraction.CHARACTER_CREATION
						?CharacterCreation.getCheckingClothingDescription()
						:"");
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}

			if(Main.game.isBadEnd()) {
				if(index==1) {
					return new Response("Недоступно", "Вы не можете управлять инвентарем во время плохого финала...", null);
				}
				return null;
			}
			
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								return new Response("Выбросить", "Вы не можете выбросить " + clothing.getName() + " во время боя!", null);
								
							} else {
								return new Response("Спрятать", "Вы не можете выбросить " + clothing.getName() + " во время боя!", null);
							}
							
						} else if (index==4) {
							return new Response("Краситель", "Нельзя красить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" одежду в бою!", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(true);
									
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить надетые презервативы!", null);
									}
									return new Response("Саботаж", "Нельзя портить надетые презервативы!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать надетую одежду!", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							return new Response("Снять", "Нельзя снять " + clothing.getName() + " во время боя!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()){
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
										+ "" + clothing.getName() + " во время боя!", null);
								
							
						} else {
							
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Выбросить", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Выбросить", "Место закончилось, так что нельзя просто выбросить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " здесь!", null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Выбросить"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?"Снять "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " и отправить это на помойку."
													:"Выбросить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + "."),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Спрятать", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Спрятать", "Это место заполнено, поэтому вы не можете сохранить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " здесь!", null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Спрятать"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?"Снять "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " и отправить это на помойку."
													:"Спрятать "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " в этой области."),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
							}
							
						} else if (index==4) {
							if (isClothingDyeActionAvailable()) {
								return new Response("Краситель", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить этот предмет."
											:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
										DYE_EQUIPPED_CLOTHING) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
							} else {
								return new Response("Краситель", "Чтобы покрасить эту вещь, вам нужна кисть для красок.", null);
							}
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(true);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить надетые презервативы!", null);
									}
									return new Response("Саботаж", "Нельзя портить надетые презервативы!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать надетую одежду!", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if (owner.isAbleToUnequip(clothing, true, Main.game.getPlayer())) {
								return new Response("Снять", "Снять " + clothing.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToInventory(Main.game.getPlayer(), clothing) + "</p>");
									}
								};
								
							} else {
								return new Response("Снять", getClothingBlockingRemovalText(owner, "снять"), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()){
							
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()) + "" + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
														Main.game.getPlayer(),
														clothing.getSlotEquippedTo(),
														" <span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Это прикроет "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										Main.game.getPlayer().isAbleToBeReplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							} else {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
														Main.game.getPlayer(),
														clothing.getSlotEquippedTo(),
														" <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Это обнажит "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							}
							
						} else {
							return null;
						}
						
					case CHARACTER_CREATION:
						if (index == 1) {
							if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
									|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
								return new Response("На сцену", "Вам нужно быть одетым так, чтобы тело было прикрыто, а также надеть пару туфель.", null);
								
							} else {
								return new Response("На сцену", "Теперь ты готов выйти на сцену.", CharacterCreation.CHOOSE_BACKGROUND) {
									@Override
									public void effects() {
										CharacterCreation.moveNPCIntoPlayerTile();
									}
								};
							}
							
						} else if(index == 4){
							if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
								return new Response("Снять всё", "Сейчас на тебе ничего нет, снимать нечего.", null);
							}
							else{
								return new Response("Снять всё", "Снять как можно больше одежды.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(unequipAll(Main.game.getPlayer()));
									}
								};
							}
							
						} else if(index == 5) {
							return new Response("Изменить цвет", "Изменить цвет этого предмета одежды.", DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION) {
								@Override
								public void effects() {
									resetClothingDyeColours();
								}
							};
							
						} else if(index == 6) {
							return new Response("Снять", "Снять " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									unequipClothingToFloor(Main.game.getPlayer(), clothing);
								}
							};
								
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							String unequipTitle = "Выбросить";
							if(clothing.isDiscardedOnUnequip(slotEquippedTo)) {
								unequipTitle = "Выбросить";
							} else if(!Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								unequipTitle = "Спрятать";
							}
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response(unequipTitle, "Нельзя снять " + clothing.getName() + " так как ты сейчас [style.colourTerrible(парализован)]!", null);
							}
							if(!Main.sex.isCanRemoveSelfClothing(Main.game.getPlayer())) {
								return new Response(unequipTitle, "Нельзя снять " + clothing.getName() + " в этой секс-сцене!", null);
							}
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Выбросить", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Выбросить", "Место закончилось, так что нельзя просто выбросить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " здесь!", null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Выбросить"),
												(clothing.isDiscardedOnUnequip(slotEquippedTo)
														?"Снять "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " и отправить это на помойку."
														:"Выбросить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + "."),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												GameCharacter unequipOwner = owner;
												AbstractClothing c = clothing;
												unequipClothingToFloor(Main.game.getPlayer(), clothing);
												Main.sex.setUnequipClothingText(c, unequipOwner.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Выбросить", getClothingBlockingRemovalText(owner, "снять"), null);
									}
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Спрятать", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Спрятать", "Это место заполнено, поэтому вы не можете сохранить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " здесь!", null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Спрятать"),
												(clothing.isDiscardedOnUnequip(slotEquippedTo)
														?"Снять "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " и отправить это на помойку."
														:"Выбросить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + "."),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												GameCharacter unequipOwner = owner;
												AbstractClothing c = clothing;
												unequipClothingToFloor(Main.game.getPlayer(), clothing);
												Main.sex.setUnequipClothingText(c, unequipOwner.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Спрятать", getClothingBlockingRemovalText(owner, "снять"), null);
									}
								}
							}
							
						} else if (index==4) {
							return new Response("Краситель", "Нельзя красить "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" одежду во время секса!", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(true);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить надетые презервативы!", null);
									}
									return new Response("Саботаж", "Нельзя портить надетые презервативы!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать надетую одежду!", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response("Снять", "Нельзя снять " + clothing.getName() + " так как ты сейчас [style.colourTerrible(парализован)]!", null);
							}
							if(!Main.sex.isCanRemoveSelfClothing(Main.game.getPlayer())) {
								return new Response("Снять", "Нельзя снять " + clothing.getName() + " в этой секс-сцене!", null);
							}
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("Снять", "Снять " + clothing.getName() + ".", Main.sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										AbstractClothing c = clothing;
										unequipClothingToInventory(Main.game.getPlayer(), clothing);
										Main.sex.setUnequipClothingText(c, owner.getUnequipDescription());
										Main.mainController.openInventory();
										Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Main.sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("Снять", getClothingBlockingRemovalText(owner, "снять"), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()) {
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										""+ clothing.getName()+ " "
										+(clothing.getClothingType().isPlural()?"имеют":"имеет")+" уже было "
												+ clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "!", null);
								
							} else {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(
											Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
												+"" + clothing.getName() + " так как ты сейчас [style.colourTerrible(парализован)]!",
											null);
								}
								if(!Main.sex.isCanRemoveSelfClothing(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ " "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" " + clothing.getName() + " в этой секс-сцене!", null);
								}
								
								if(owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11), false, false, Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + ". "
													+ clothing.getClothingBlockingDescription(
															clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
															Main.game.getPlayer(),
															clothing.getSlotEquippedTo(),
															" <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Это обнажит "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" ",
															".</span>"),
													Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
											Main.sex.setDisplaceClothingText(clothing, owner.getDisplaceDescription());
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								
								} else {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ "" + clothing.getName() + ", так как "+UtilText.parse(owner, "[npc.namePos] ")
											+owner.getBlockingClothing().getName()+" "+(owner.getBlockingClothing().getClothingType().isPlural()?"являются":"есть")+" мешает!", null);
								}
							}
							
						} else {
							return null;
						}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							return new Response("Выбросить", "Вы не можете заставить противника раздеться прямо во время боя!", null);
							
						} else if (index==4) {
							return new Response("Краситель", "Вы не можете перекрасить надетую на кого-то одежду во время боя!", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(false);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить надетые презервативы!", null);
									}
									return new Response("Саботаж", "Нельзя портить надетые презервативы!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать надетую одежду!", null);
							}
							
						} else if(index == 6) {
							return new Response("Снять", "Вы не можете снять одежду с противника во время боя!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										""+ clothing.getName()+ " "
										+(clothing.getClothingType().isPlural()?"имеют":"имеет")+" уже было "
												+ clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "!", null);
								
							} else {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "" + clothing.getName() + " во время боя!", null);
							}
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case CHARACTER_CREATION:
						boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
						
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Выбросить", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Выбросить", UtilText.parse(inventoryNPC, "Место закончилось, поэтому вы не можете бросить [npc.namePos] " + clothing.getName() + " здесь!"), null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Выбросить"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "Снять [npc.namePos] " + clothing.getName() + " и отправить это на помойку.")
													:UtilText.parse(inventoryNPC, "Бросить [npc.namePos] " + clothing.getName() + ".")),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Спрятать", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Спрятать", UtilText.parse(inventoryNPC, "Место закончилось, поэтому вы не можете сохранить [npc.namePos] " + clothing.getName() + " здесь!"), null);
								} else {
									return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Спрятать"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "Снять [npc.namePos] " + clothing.getName() + " и отправить это на помойку.")
													:UtilText.parse(inventoryNPC, "Сложить [npc.namePos] " + clothing.getName() + " в этой области.")),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
							}
							
						} else if(index==2) {
							if(inventoryFull) {
								return new Response("Взять", "Ваш инвентарь полон, так что вы не можете взять это!", null);
								
							} else if(clothing.isDiscardedOnUnequip(slotEquippedTo)) {
								return new Response("Взять", "Эта вещь автоматически выбрасывается при снятии, так что забрать её не получится!", null);
								
							} else {
								return new Response("Взять",
										UtilText.parse(inventoryNPC, "Взять [npc.namePos] " + clothing.getName() + " и добавить его в свой инвентарь."),
										INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ unequipClothingToUnequippersInventory(Main.game.getPlayer(), clothing)
												+ "</p>");
									}
								};
							}
							
						} else if (index==4) {
							if (isClothingDyeActionAvailable()) {
								return new Response("Краситель", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Используйте свое мастерство в [style.colourEarth(заклинаниях Земли)], чтобы покрасить этот предмет."
											:"Используйте кисть для окрашивания, чтобы покрасить эту одежду.",
										DYE_EQUIPPED_CLOTHING) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
								
							} else {
								return new Response("Краситель", UtilText.parse(inventoryNPC, "Если хочешь покрасить одежду [npc.namePos], тебе понадобится кисть для красок."), null);
							}
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(false);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить надетые презервативы!", null);
									}
									return new Response("Саботаж", "Нельзя портить надетые презервативы!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать надетую одежду!", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if(!clothing.getClothingType().isAbleToBeDropped()) {
								return new Response("Снять", UtilText.parse(inventoryNPC, "Так как это уникальный предмет, " + clothing.getName() + " нельзя убрать в [npc.namePos] инвентарь."), null);
							}
							return new Response("Снять", "Снять " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToInventory(Main.game.getPlayer(), clothing) + "</p>");
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()) + "" + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
														owner,
														clothing.getSlotEquippedTo(),
														" <span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Это прикроет "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										owner.isAbleToBeReplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							} else {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
														owner,
														clothing.getSlotEquippedTo(),
														" <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Это обнажит "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + owner.getDisplaceDescription() + "</p>");
									}
								};
							}
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							String unequipTitle = "Выбросить";
							if(clothing.isDiscardedOnUnequip(slotEquippedTo)) {
								unequipTitle = "Выбросить";
							} else if(!Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								unequipTitle = "Спрятать";
							}
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response(unequipTitle,
									UtilText.parse(inventoryNPC, "Ты не можешь снять одежду [npc.namePos], потому что ты сейчас [style.colourTerrible(обездвижена)]!"),
									null);
							}
							if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
								return new Response(unequipTitle,
										UtilText.parse(inventoryNPC, "Пока вы прячетесь, вы не можете снять [npc.namePos] одежду!"),
										null);
								
							} else if(clothing.isDiscardedOnUnequip(slotEquippedTo) && !Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response("Выбросить", "Нельзя снять " + clothing.getName() + " в этой секс-сцене!", null);
							}

							if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response(unequipTitle, "Нельзя снять " + clothing.getName() + " в этой секс-сцене!", null);
							}
							
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Выбросить", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Выбросить", UtilText.parse(inventoryNPC, "Место закончилось, поэтому вы не можете бросить [npc.namePos] " + clothing.getName() + " здесь!"), null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Выбросить"),
											(clothing.isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "Снять [npc.namePos] " + clothing.getName() + " и отправить это на помойку.")
													:UtilText.parse(inventoryNPC, "Бросить [npc.namePos] " + clothing.getName() + ".")),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
												Main.sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Выбросить", getClothingBlockingRemovalText(owner, "снять"), null);
									}
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Спрятать", "Вы не можете выбросить " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Спрятать", UtilText.parse(inventoryNPC, "Место закончилось, поэтому вы не можете сохранить [npc.namePos] " + clothing.getName() + " здесь!"), null);
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.isDiscardedOnUnequip(slotEquippedTo)?"Выбросить":"Спрятать"),
												(clothing.isDiscardedOnUnequip(slotEquippedTo)
														?UtilText.parse(inventoryNPC, "Снять [npc.namePos] " + clothing.getName() + " и отправить это на помойку.")
														:UtilText.parse(inventoryNPC, "Сложить [npc.namePos] " + clothing.getName() + " в этой области.")),
												Main.sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
												Main.sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
												Main.mainController.openInventory();
												Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Main.sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Спрятать", getClothingBlockingRemovalText(owner, "снять"), null);
									}
								}
							}
							
						} else if (index==4) {
							return new Response("Краситель", UtilText.parse(inventoryNPC, "Ты не можешь красить одежду [npc.namePos] во время секса!"), null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								return getJinxRemovalResponse(false);
								
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Нельзя чинить надетые презервативы!", null);
									}
									return new Response("Саботаж", "Нельзя портить надетые презервативы!", null);
								}
								return new Response("Зачаровать", "Нельзя зачаровывать надетую одежду!", null);
							}
							
						} else if(index == 6 && !clothing.isDiscardedOnUnequip(slotEquippedTo)) {
							if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
								return new Response("Снять",
									UtilText.parse(inventoryNPC, "Ты не можешь снять одежду [npc.namePos], потому что ты сейчас [style.colourTerrible(обездвижена)]!"),
									null);
							}
							if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
								return new Response("Снять", UtilText.parse(inventoryNPC, "Пока вы прячетесь, вы не можете снять [npc.namePos] одежду!"), null);
								
							} else if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response("Снять", "Нельзя снять " + clothing.getName() + " в этой секс-сцене!", null);
							}
							
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("Снять", "Снять " + clothing.getName() + ".", Main.sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										AbstractClothing c = clothing;
										unequipClothingToInventory(Main.game.getPlayer(), clothing);
										Main.sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
										Main.mainController.openInventory();
										Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Main.sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("Снять", getClothingBlockingRemovalText(owner, "снять"), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										""+ clothing.getName()+ " "
										+(clothing.getClothingType().isPlural()?"имеют":"имеет")+" уже было "
												+ clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "!", null);
								
							} else {
								if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										UtilText.parse(inventoryNPC, "Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()
												+ " одежду [npc.namePos], так как ты сейчас [style.colourTerrible(обездвижена)]!"),
										null);
								}
								if(Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											UtilText.parse(inventoryNPC,
													"Так как ты прячешься, ты не можешь "+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + " [npc.namePos] " + clothing.getName() + "!"),
											null);
								}
								if(owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11), false, false, Main.game.getPlayer())){
									if(!Main.sex.isCanRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
										return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
												"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "" + clothing.getName() + " в этой секс-сцене!", null);
									}
									
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + "" + clothing.getName() + ". "
													+ clothing.getClothingBlockingDescription(
															clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
															owner,
															clothing.getSlotEquippedTo(),
															" <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Это обнажит "+(owner.isPlayer()?"твоей":owner.getName("")+"'s")+" ",
															".</span>"),
													Main.sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											owner.isAbleToBeDisplaced(clothing, clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
											Main.sex.setDisplaceClothingText(clothing, owner.getDisplaceDescription());
											Main.mainController.openInventory();
											Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Main.sex.setSexStarted(true);
										}
									};
								
								} else {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ "" + clothing.getName() + ", так как "+UtilText.parse(owner, "[npc.namePos] ")
											+owner.getBlockingClothing().getName()+" "+(owner.getBlockingClothing().getClothingType().isPlural()?"являются":"есть")+" мешает!", null);
								}
							}
							
						} else {
							return null;
						}
						
						case TRADING:
							if (index == 1) {
								return new Response("Выбросить", UtilText.parse(inventoryNPC, "Вы не можете заставить [npc.name] снять [npc.her] одежду!"), null);
								
							} else if (index==4) {
								return new Response("Краситель", UtilText.parse(inventoryNPC, "Вы не можете покрасить одежду [npc.namePos]!"), null);
								
							}  else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Починить (<i>1 Эссенция</i>)", "Вы не можете починить презерватив [npc.namePos]!", null);
									}
									return new Response("Саботаж", "Вы не можете испортить презерватив [npc.namePos]!", null);
								}
								return new Response("Зачаровать", UtilText.parse(inventoryNPC, "Вы не можете зачаровать одежду [npc.namePos]!"), null);
								
							} else if(index == 6) {
								return new Response("Снять", UtilText.parse(inventoryNPC, "Вы не можете снять одежду [npc.namePos]!"), null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if (index > 10 && index - 11 < clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
								if (clothing.getDisplacedList().contains(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											""+ clothing.getName()+ " "
													+(clothing.getClothingType().isPlural()?"имеют":"имеет")+" уже было "
													+ clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescriptionPast() + "!", null);
									
								} else {
									return new Response(Util.capitaliseSentence(clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"Вы не можете "+clothing.getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "" + clothing.getName() + "!", null);
								}
								
							} else {
								return null;
							}
					}
				
				}
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	private static String getClothingDyeUI() {
		InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = clothing.getClothingType().getEquipSlots().get(0);
		}
		
		inventorySB = new StringBuilder(
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>"+clothing.getDisplayName(true)+"</b></h3><p>Выберите нужные цвета с помощью цветных кнопок ниже. После того как воспользуетесь предпросмотром, чтобы увидеть, как будет выглядеть новая одежда, нажмите кнопку «Покрасить» внизу экрана, чтобы применить изменения.</p></div><br/><div class='container-full-width'><div class='inventoryImage'><div class='inventoryImage-content'>"
							+ clothing.getClothingType().getSVGImage(slotEquippedTo,
									dyePreviews,
									dyePreviewPattern,
									dyePreviewPatternColours,
									getDyePreviewStickersAsStrings())
						+ "</div>"
					+ "</div>");
		
		inventorySB.append("<h3 style='text-align:center;'><b>Покраска и просмотр</b></h3>");
		
		if(!clothing.getClothingType().getStickers().isEmpty()) {
			StringBuilder stickerSB = new StringBuilder();
			boolean stickerFound = false;
			List<StickerCategory> orderedCategories = new ArrayList<>(clothing.getClothingType().getStickers().keySet());
			Collections.sort(orderedCategories, (s1, s2)->s1.getPriority()-s2.getPriority());
			
			for(StickerCategory cat : orderedCategories) {
				stickerSB.append("<div class='container-quarter-width' style='width:calc(75% - 16px); margin:0 8px; padding:0;'>");
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding-top:6px; width:20%;'>");
						stickerSB.append(Util.capitaliseSentence(cat.getName())+":"); // Category name
					stickerSB.append("</div>");
					
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding:0; width:80%;'>");
						List<Sticker> orderedStickers = new ArrayList<>(clothing.getClothingType().getStickers().get(cat));
						Collections.sort(orderedStickers, (s1, s2)->s1.getPriority()-s2.getPriority());
						for(Sticker sticker : orderedStickers) {
							String requirements = UtilText.parse(sticker.getUnavailabilityText()).trim();
							if(requirements.isEmpty() || sticker.isShowDisabledButton()) {
								boolean specialSticker = !sticker.getAvailabilityText().isEmpty() || !sticker.getTagsApplied().isEmpty() || !sticker.getTagsRemoved().isEmpty();
								stickerFound = true;
								String id = "ITEM_STICKER_"+cat.getId()+sticker.getId();
								if(!requirements.isEmpty()) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button disabled'><b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
									
								} else if(dyePreviewStickers.get(cat)==sticker) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button active'><b style='color:" + sticker.getColourSelected().toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
								} else {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button'><span style='color:"+sticker.getColourDisabled().toWebHexString()+";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</span>"
											+ "</div>");
								}
							}
						}
					stickerSB.append("</div>");
				stickerSB.append("</div>");
				
				if(stickerFound) {
					stickerFound = false;
					inventorySB.append(stickerSB.toString());
					stickerSB = new StringBuilder();
				}
			}
		}
		
		for(int i=0; i<clothing.getClothingType().getColourReplacements().size(); i++) {
			ColourReplacement cr = clothing.getClothingType().getColourReplacement(i);
			if(!cr.getAllColours().isEmpty()) {
				inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
							+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" Цвет"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(нельзя изменить)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
//					if(!c.isDesaturated()) {
						inventorySB.append("<div class='normal-button"+(dyePreviews.size()>i && dyePreviews.get(i)==c?" выбранный":"")+"' id='DYE_CLOTHING_"+i+"_"+c.getId()+"' style='width:auto; margin-right:4px; border-width:1px;"
												+(cr.getDefaultColours().contains(c)
													?"цвет-границы:"+PresetColour.TEXT_GREY.toWebHexString()+";"
													:"")
												+(dyePreviews.size()>i && dyePreviews.get(i)==c
													?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
													:"")+"'><div class='phone-item-colour' style='"
											+ (c.isMetallic()
													?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:"background-color:" + c.toWebHexString() + ";")
											+ "'></div>"
							+ "</div>");
//					}
				}
				inventorySB.append("</div>");
			}
		}
		
		if(clothing.getClothingType().isPatternAvailable()){
			inventorySB.append(
					"<br/><div class='container-full-width'>Узор:<br/>");
	 
			for (Pattern pattern : Pattern.getAllPatterns()) {
				if (dyePreviewPattern.equals(pattern.getId())) {
					inventorySB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</b>"
							+ "</div>");
				} else {
					inventorySB.append(
							"<div id='ITEM_PATTERN_"+pattern.getId()+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</span>"
							+ "</div>");
				}
			}
			inventorySB.append("</div>");

			for(int i=0; i<clothing.getClothingType().getPatternColourReplacements().size(); i++) {
				ColourReplacement cr = clothing.getClothingType().getPatternColourReplacement(i);
				if(!cr.getAllColours().isEmpty() && Pattern.getPattern(dyePreviewPattern).isRecolourAvailable(cr)) {
					inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>Узор "+Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" Цвет:<br/>");
					
					for (Colour c : cr.getAllColours()) {
						inventorySB.append("<div class='normal-button"+(dyePreviewPatternColours.size()>i && dyePreviewPatternColours.get(i)==c?" выбранный":"")+"' id='DYE_CLOTHING_PATTERN_"+i+"_"+c.getId()+"' style='width:auto; margin-right:4px;"+(dyePreviewPatternColours.size()>i && dyePreviewPatternColours.get(i)==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'><div class='phone-item-colour' style='"
											+ (c.isMetallic()
													?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:"background-color:" + c.toWebHexString() + ";")
											+ "'></div>"
							+ "</div>");
					}
					inventorySB.append("</div>");
				}
			}
		}
		inventorySB.append("</div>");
		
		return inventorySB.toString();
	}
	
	private static String getWeaponDyeUI() {
		inventorySB = new StringBuilder(
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>"+weapon.getDisplayName(true)+"</b></h3><p>Выберите нужные цвета с помощью цветных кнопок ниже. После того как вы воспользуетесь предпросмотром, чтобы увидеть, как будет выглядеть новое оружие, нажмите кнопку «Покрасить» в нижней части экрана, чтобы применить изменения.</p></div><br/><div class='container-full-width'><div class='container-full-width' style='text-align:center; width:calc(25% - 16px); float:right;'><div class='inventoryImage' style='width:100%;'>"
							+ (weapon.getWeaponType().isEquippedSVGImageDifferent()
								?"Снято"
								:"")
							+ "<div class='inventoryImage-content'>"
								+ weapon.getWeaponType().getSVGImage(damageTypePreview, dyePreviews)
							+ "</div>"
						+ "</div>"
						+(weapon.getWeaponType().isEquippedSVGImageDifferent()
							?"<div class='inventoryImage' style='width:100%;'>Экипировано<div class='inventoryImage-content'>"
										+ weapon.getWeaponType().getSVGEquippedImage(Main.game.getPlayer(), damageTypePreview, dyePreviews)
									+ "</div>"
								+ "</div>"
							:"")
					+ "</div><h3 style='text-align:center;'><b>Краска и просмотр</b></h3>");
		
		
		inventorySB.append("<div class='container-quarter-width' style='text-align:center;width:calc(75% - 16px);'><b>Тип урона:</b><br/>");
		for(DamageType dt : weapon.getWeaponType().getAvailableDamageTypes()) {
			inventorySB.append("<div class='normal-button"+(damageTypePreview==dt?" выбранный":"")+"' id='ТИП_УРОНА_"+dt.toString()+"'style='width:20%; margin:0 2.5%; color:"+(damageTypePreview==dt?dt.getColour().toWebHexString():dt.getColour().getShades(8)[0])+";'>"
						+ Util.capitaliseSentence(dt.getName())
					+ "</div>");
		}
		inventorySB.append("</div>");

		boolean colourOptions = false;

		for(int i=0; i<weapon.getWeaponType().getColourReplacements(false).size(); i++) {
			colourOptions = true;
			ColourReplacement cr = weapon.getWeaponType().getColourReplacement(false, i);
			if(!cr.getAllColours().isEmpty()) {
				inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
						+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" Цвет"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(нельзя изменить)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
					inventorySB.append("<div class='normal-button"+(dyePreviews.size()>i && dyePreviews.get(i)==c?" выбранный":"")+"' id='DYE_WEAPON_"+i+"_"+c.getId()+"' style='width:auto; margin-right:4px; border-width:1px;"
											+(cr.getDefaultColours().contains(c)
												?"цвет-границы:"+PresetColour.TEXT_GREY.toWebHexString()+";"
												:"")
											+(dyePreviews.size()>i && dyePreviews.get(i)==c
												?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
												:"")
										+"'><div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				inventorySB.append("</div>");
			}
		}
		
		if(!colourOptions) {
			inventorySB.append("<div class='container-half-width' style='text-align:center;'>[style.colourDisabled(Доступных красителей нет...)]</div>");
		}

		inventorySB.append("</div>");
		
		return inventorySB.toString();
	}
	
	public static final DialogueNode DYE_CLOTHING = new DialogueNode("Красить одежду", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в предыдущее меню.", INVENTORY_MENU);

			} else if (index == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("Краситель",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Краситель",
						"Покрасить " + clothing.getName() + " в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы сможете перекрасить предмет в любой другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите изменить цвет снова, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p><p><b>The " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "были" : "был") + " окрашены</b>!</p><p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству владения [style.boldEarth(заклинаниями земли)], вы можете окрашивать " + clothing.getName() + " без использования кисти!</p>");
						}
						
						if(owner!=null) {
							owner.removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							owner.addClothing(dyedClothing, false);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedClothing.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							Main.game.getPlayerCell().getInventory().addClothing(dyedClothing);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedClothing.getDisplayName(true)), false);
						}
						
					}
				};

			} else if (index == 6) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("Окрасить всё (стак)",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + clothing.getName() + "!",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getClothingCount(clothing);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getClothingCount(clothing);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Окрасить всё (стак)",
							"У вас есть всего лишь одна "+clothing.getName()+", поэтому вам следует покрасить её, используя отдельное действие...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Окрасить всё (стак)",
								"Вам не хватает кистей для окрашивания, чтобы покрасить все " + clothing.getNamePlural() + "! У вас есть "+dyeBrushCount+" кистей, но в этой стопке находится "+stackCount+" "+clothing.getNamePlural()+" штук...",
								null); 
					}
				}
				
				return new Response("Окрасить всё (стак)",
						"Покрасить все " + clothing.getNamePlural() + " находящиеся в этой стопке предметов одежды ("+stackCount+" всего) в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях Земли)], вы сможете перекрасить их в другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить их цвет, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p><p><b>The "+clothing.getName()+(clothing.getClothingType().isPlural()?"имеют":"имеет")+" были окрашены</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+clothing.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны окрашивать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + clothing.getNamePlural() + " без использования ни одной кисти для окрашивания!</p>");
						}
						
						if(owner!=null) {
							owner.removeClothing(clothing, finalCount);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							owner.addClothing(dyedClothing, finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedClothing.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeClothing(clothing, finalCount);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColours(dyePreviews);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColours(dyePreviewPatternColours);
							dyedClothing.setStickersAsObjects(dyePreviewStickers);
							Main.game.getPlayerCell().getInventory().addClothing(dyedClothing, finalCount);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedClothing.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 11) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("Покрасить все",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + clothing.getName() + "!",
							null); 
				}
				
				List<AbstractClothing> clothingMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractClothing, Integer> entry : owner.getAllClothingInInventory().entrySet()) {
						if(entry.getKey().getClothingType().equals(clothing.getClothingType())) {
							clothingMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
						if(entry.getKey().getClothingType().equals(clothing.getClothingType())) {
							clothingMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Покрасить все",
							"У вас есть всего лишь одна "+clothing.getName()+", поэтому вам следует покрасить её, используя отдельное действие...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Покрасить все",
								"Вам не хватает кистей для окрашивания, чтобы покрасить все " + clothing.getNamePlural() + "! У вас есть "+dyeBrushCount+" кистей, но в этой стопке находится "+stackCount+" "+clothing.getNamePlural()+" всего...",
								null); 
					}
				}
				
				return new Response("Покрасить все",
						"Покрасить все " + clothing.getNamePlural() + " находящиеся в этой стопке предметов одежды ("+stackCount+" всего) в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях Земли)], вы сможете перекрасить их в другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить их цвет, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p><p><b>The "+clothing.getName()+(clothing.getClothingType().isPlural()?"имеют":"имеет")+" были окрашены</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+clothing.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны окрашивать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + clothing.getNamePlural() + " без использования ни одной кисти для окрашивания!</p>");
						}
						
						if(owner!=null) {
							for(AbstractClothing c : clothingMatches) {
								int clothingCount = owner.getAllClothingInInventory().get(c);
								owner.removeClothing(c, clothingCount);
								AbstractClothing dyedClothing = new AbstractClothing(c) {};
								dyedClothing.setColours(dyePreviews);
								dyedClothing.setPattern(dyePreviewPattern);
								dyedClothing.setPatternColours(dyePreviewPatternColours);
								dyedClothing.setStickersAsObjects(dyePreviewStickers);
								owner.addClothing(dyedClothing, clothingCount, false, false);
								Main.game.addEvent(new EventLogEntry("Окрашено", dyedClothing.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractClothing c : clothingMatches) {
								int clothingCount = Main.game.getPlayerCell().getInventory().getAllClothingInInventory().get(c);
								Main.game.getPlayerCell().getInventory().removeClothing(c, clothingCount);
								AbstractClothing dyedClothing = new AbstractClothing(c) {};
								dyedClothing.setColours(dyePreviews);
								dyedClothing.setPattern(dyePreviewPattern);
								dyedClothing.setPatternColours(dyePreviewPatternColours);
								dyedClothing.setStickersAsObjects(dyePreviewStickers);
								Main.game.getPlayerCell().getInventory().addClothing(dyedClothing, clothingCount);
								Main.game.addEvent(new EventLogEntry("Окрашено", dyedClothing.getDisplayName(true)), false);
							}
						}
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_CLOTHING = new DialogueNode("Красить одежду", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в предыдущее меню.", INVENTORY_MENU);

			} else if (index == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("Краситель",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Краситель",
								"Покрасить " + clothing.getName() + " в выбранные вами цвета."
										+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы сможете перекрасить предмет в любой другой цвет в любое время."
												:" Это действие необратимо, и если вы захотите изменить цвет снова, вам понадобится новая кисть для окрашивания."),
								INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(clothing, dyePreviews.get(0))
									+ "</p><p><b>Тот " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "были" : "был") + " окрашены</b>!</p><p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству владения [style.boldEarth(заклинаниями земли)], вы можете окрашивать " + clothing.getName() + " без использования кисти!</p>");
						}
						
						clothing.setColours(dyePreviews);
						clothing.setPattern(dyePreviewPattern);
						clothing.setPatternColours(dyePreviewPatternColours);
						clothing.setStickersAsObjects(dyePreviewStickers);
						
						Main.game.addEvent(new EventLogEntry("Окрашено", clothing.getDisplayName(true)), false);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	
	public static final DialogueNode DYE_CLOTHING_CHARACTER_CREATION = new DialogueNode("Выбрать цвет", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в предыдущее меню.", CLOTHING_INVENTORY);

			} else if (index == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("Краситель",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Краситель",
						"Изменить цвет " + clothing.getName() + " на выбранные вами цвета.",
						INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().removeClothing(clothing);
						AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
						dyedClothing.setColours(dyePreviews);
						dyedClothing.setPattern(dyePreviewPattern);
						dyedClothing.setPatternColours(dyePreviewPatternColours);
						dyedClothing.setStickersAsObjects(dyePreviewStickers);
						clothing = dyedClothing;
						Main.game.getPlayerCell().getInventory().addClothing(dyedClothing);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION = new DialogueNode("Выбрать цвет", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в предыдущее меню.", CLOTHING_EQUIPPED);

			} else if (index  == 1) {
				if(dyePreviews.equals(clothing.getColours())
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternColours.equals(clothing.getPatternColours())
						&& dyePreviewStickers.equals(clothing.getStickersAsObjects())) {
					return new Response("Краситель",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Краситель",
						"Изменить цвет " + clothing.getName() + " на выбранные вами цвета.",
						CLOTHING_EQUIPPED){
					@Override
					public void effects(){
						clothing.setColours(dyePreviews);
						clothing.setPattern(dyePreviewPattern);
						clothing.setPatternColours(dyePreviewPatternColours);
						clothing.setStickersAsObjects(dyePreviewStickers);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_WEAPON = new DialogueNode("Покрасить оружие", "", true) {

		@Override
		public String getContent() {
			return getWeaponDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в предыдущее меню.", INVENTORY_MENU);

			} else if (index == 1) {
				if (!Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("Краситель",
							"У вас нет кисти для красок, поэтому вы не можете изменить цвета " + weapon.getName() + "...",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Краситель",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Краситель",
						"Покрасить " + weapon.getName() + " в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы сможете перекрасить предмет в любой другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите изменить цвет снова, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p><p><b>Того " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "были" : "был") + " окрашены</b>!</p><p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству владения [style.boldEarth(заклинаниями земли)], вы можете окрашивать " + weapon.getName() + " без использования кисти!</p>");
						}
						 
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							owner.addWeapon(dyedWeapon, false);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedWeapon.getDisplayName(true)), false);
							weapon = dyedWeapon;

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedWeapon.getDisplayName(true)), false);
							weapon = dyedWeapon;
						}
					}
				};

			} else if (index == 2) {
				if (!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("Перековка",
							"У вас нет молота для перековки, поэтому вы не можете изменить тип урона у " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Перековка",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Перековка",
						"Перековать " + weapon.getName() + " в выбранный вами тип урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях Земли)], вы сможете перековать его в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить тип урона, вам понадобится еще один молот для перековки."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "были" : "был") + " перековано</b>!</p><p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "Перековка </b> " + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "долбить" : "долбит") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы можете перековать " + weapon.getName() + " без использования молота для перековки!</p>");
						}
						 
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), false);
							Main.game.addEvent(new EventLogEntry("Перековано", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							Main.game.addEvent(new EventLogEntry("Перековано", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 3) {
				if ((!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || !Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH))
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("Покрасить и перековать",
							"У вас нет одновременно кисти для покраски и молота для перековки, поэтому вы не можете покрасить и перековать " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Покрасить и перековать",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Покрасить и перековать",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Покрасить и перековать",
						"Покрасить и перековать  " + weapon.getName() + " в выбранные вами цвета и тип урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в использовании [style.boldEarth(Заклинаний земли)], вы можете красить и перековывать его в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить цвета и тип урона, вам понадобятся новая кисть для окрашивания и новый молот для перековки."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "были" : "был") + " покрашено и перековано</b>!</p><p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
										+"<br/>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "Перековка </b> " + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "долбить" : "долбит") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в использовании [style.boldEarth(Заклинаний земли)], вы можете покрасить и перековать  " + weapon.getName() + " без использования кисти для окрашивания или молота для перековки!</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), false);
							Main.game.addEvent(new EventLogEntry("Покрашено и перековано", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							Main.game.addEvent(new EventLogEntry("Покрашено и перековано", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 6) {
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Окрасить всё (стак)",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Окрасить всё (стак)",
							"У вас есть всего лишь одна "+weapon.getName()+", поэтому вам следует покрасить её, используя отдельное действие...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Окрасить всё (стак)",
								"Вам не хватает кистей для окрашивания, чтобы покрасить все " + weapon.getNamePlural() + "! У вас есть "+dyeBrushCount+" кистей, но в этой стопке находится "+stackCount+" "+weapon.getNamePlural()+" штук...",
								null); 
					}
				}
				
				return new Response("Окрасить всё (стак)",
						"Покрасить все " + weapon.getNamePlural() + " которые находятся в этой стопке оружия ("+stackCount+" всего) в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях Земли)], вы сможете перекрасить их в другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить их цвет, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p><p><b>Того "+weapon.getName()+(weapon.getWeaponType().isPlural()?"имеют":"имеет")+" были окрашены</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны окрашивать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + weapon.getNamePlural() + " без использования ни одной кисти для окрашивания!</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							owner.addWeapon(dyedWeapon, finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							dyedWeapon.setColours(dyePreviews);
							Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon, finalCount);
							Main.game.addEvent(new EventLogEntry("Окрашено", dyedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 7) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Перековать всё (стопкой)",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Перековать всё (стопкой)",
							"У вас есть всего лишь одна "+weapon.getName()+", так что тебе стоит перековывать их по отдельности...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(reforgeHammerCount<stackCount) {
						return new Response("Перековать всё (стопкой)",
								"У тебя недостаточно молотов для перековки, чтобы перековать все " + weapon.getNamePlural() + "! У вас есть "+reforgeHammerCount+" молоты, но есть "+stackCount+" "+weapon.getNamePlural()+" штук...",
								null); 
					}
				}
				
				return new Response("Перековать всё (стопкой)",
						"Перековать всё " + weapon.getNamePlural() + " которые находятся в этой стопке оружия ("+stackCount+" всего) в выбранный тобой тип урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря твоему мастерству в [style.boldEarth(заклинаниях земли)], ты можешь перековывать их в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить тип урона, вам понадобится еще один молот для перековки."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того "+weapon.getName()+(weapon.getWeaponType().isPlural()?"имеют":"имеет")+" было перековано</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> молот для перековки" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны перековывать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + weapon.getNamePlural() + " не используя ни единого молота для перековки!</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("Перековано", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount);
							Main.game.addEvent(new EventLogEntry("Перековано", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 8) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Покрасить и перековать всё (стопкой)",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Покрасить и перековать всё (стопкой)",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Покрасить и перековать всё (стопкой)",
							"У вас есть всего лишь одна "+weapon.getName()+", так что вам стоит покрасить и перековать его, используя отдельное действие...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(dyeBrushCount<stackCount || reforgeHammerCount<stackCount) {
						return new Response("Покрасить и перековать всё (стопкой)",
								"У вас недостаточно кистей для окрашивания или молотов для перековки, чтобы покрасить и перековать все "+stackCount+" "+weapon.getNamePlural()+" штук...",
								null); 
					}
				}
				
				return new Response("Покрасить и перековать всё (стопкой)",
						"Покрасить и перековать всё " + weapon.getNamePlural() + " которые находятся в этой стопке оружия ("+stackCount+" ) целиком в выбранных вами цветах и типах урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы можете делать это в любое время."
										:" Это действие необратимо, и если вы захотите повторить его снова, вам понадобятся еще одна кисть для окрашивания и молот для перековки."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того "+weapon.getName()+(weapon.getWeaponType().isPlural()?"имеют":"имеет")+" были окрашены и перекованы</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");

							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> молот для перековки" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны окрашивать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + weapon.getNamePlural() + " без использования ни одной кисти для окрашивания или молота для перековки!</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount, false, false);
							Main.game.addEvent(new EventLogEntry("Покрашено и перековано", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setColours(dyePreviews);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), finalCount);
							Main.game.addEvent(new EventLogEntry("Покрашено и перековано", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 11) {
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Покрасить все",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Покрасить все",
							"У вас есть всего лишь одна "+weapon.getName()+", поэтому вам следует покрасить её, используя отдельное действие...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Покрасить все",
								"Вам не хватает кистей для окрашивания, чтобы покрасить все " + weapon.getNamePlural() + "! У вас есть "+dyeBrushCount+" кистей, но в этой стопке находится "+stackCount+" "+weapon.getNamePlural()+" всего...",
								null); 
					}
				}
				
				return new Response("Покрасить все",
						"Покрасить все " + weapon.getNamePlural() + " которые находятся в этой стопке оружия ("+stackCount+" всего) в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях Земли)], вы сможете перекрасить их в другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить их цвет, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p><p><b>Того "+weapon.getName()+(weapon.getWeaponType().isPlural()?"имеют":"имеет")+" были окрашены</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны окрашивать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + weapon.getNamePlural() + " без использования ни одной кисти для окрашивания!</p>");
						}
						

						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(w);
								dyedWeapon.setColours(dyePreviews);
								owner.addWeapon(dyedWeapon, weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry("Окрашено", dyedWeapon.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon dyedWeapon = Main.game.getItemGen().generateWeapon(w);
								dyedWeapon.setColours(dyePreviews);
								Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon, weaponCount);
								Main.game.addEvent(new EventLogEntry("Окрашено", dyedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else if (index == 12) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Перековать всё",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Перековать всё",
							"У вас есть всего лишь одна "+weapon.getName()+", так что тебе стоит перековывать их по отдельности...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(reforgeHammerCount<stackCount) {
						return new Response("Перековать всё",
								"У тебя недостаточно молотов для перековки, чтобы перековать все " + weapon.getNamePlural() + "! У вас есть "+reforgeHammerCount+" молоты, но есть "+stackCount+" "+weapon.getNamePlural()+" всего...",
								null); 
					}
				}
				
				return new Response("Перековать всё",
						"Перековать всё " + weapon.getNamePlural() + " которые находятся в этой стопке оружия ("+stackCount+" всего) в выбранный тобой тип урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря твоему мастерству в [style.boldEarth(заклинаниях земли)], ты можешь перековывать их в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить тип урона, вам понадобится еще один молот для перековки."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того "+weapon.getName()+(weapon.getWeaponType().isPlural()?"имеют":"имеет")+" было перековано</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> молот для перековки" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны перековывать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + weapon.getNamePlural() + " не используя ни единого молота для перековки!</p>");
						}

						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry("Перековано", modifiedWeapon.getDisplayName(true)), false);
							}

						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount);
								Main.game.addEvent(new EventLogEntry("Перековано", modifiedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else if (index == 13) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Покрасить и перековать всё",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Покрасить и перековать всё",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Покрасить и перековать всё",
							"У вас есть всего лишь одна "+weapon.getName()+", так что вам стоит покрасить и перековать его, используя отдельное действие...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH));
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER));
					
					if(dyeBrushCount<stackCount || reforgeHammerCount<stackCount) {
						return new Response("Покрасить и перековать всё",
								"У вас недостаточно кистей для окрашивания или молотов для перековки, чтобы покрасить и перековать все "+stackCount+" "+weapon.getNamePlural()+" штук...",
								null); 
					}
				}
				
				return new Response("Покрасить и перековать всё",
						"Покрасить и перековать всё " + weapon.getNamePlural() + " которые находятся в этой стопке оружия ("+stackCount+" ) целиком в выбранных вами цветах и типах урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы можете делать это в любое время."
										:" Это действие необратимо, и если вы захотите повторить его снова, вам понадобятся еще одна кисть для окрашивания и молот для перековки."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того "+weapon.getName()+(weapon.getWeaponType().isPlural()?"имеют":"имеет")+" были окрашены и перекованы</b>!</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>Затем вы повторяете это для остальных "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");

							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> молот для перековки" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы способны окрашивать и перековывать "
												+ (finalCount==2
													?"оба"
													:"все "+Util.intToString(finalCount))
												+" из " + weapon.getNamePlural() + " без использования ни одной кисти для окрашивания или молота для перековки!</p>");
						}
						
						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								modifiedWeapon.setColours(dyePreviews);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								owner.addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry("Покрашено и перековано", modifiedWeapon.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								modifiedWeapon.setColours(dyePreviews);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon(modifiedWeapon), weaponCount);
								Main.game.addEvent(new EventLogEntry("Покрашено и перековано", modifiedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_WEAPON = new DialogueNode("Покрасить оружие", "", true) {

		@Override
		public String getContent() {
			return getWeaponDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в предыдущее меню.", INVENTORY_MENU);

			} else if (index == 1) {
				if (!Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("Краситель",
							"У вас нет кисти для красок, поэтому вы не можете изменить цвета " + weapon.getName() + "...",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Краситель",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Краситель",
						"Покрасить " + weapon.getName() + " в выбранные вами цвета."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы сможете перекрасить предмет в любой другой цвет в любое время."
										:" Это действие необратимо, и если вы захотите изменить цвет снова, вам понадобится новая кисть для окрашивания."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p><p><b>Того " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "были" : "был") + " окрашены</b>!</p><p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству владения [style.boldEarth(заклинаниями земли)], вы можете окрашивать " + weapon.getName() + " без использования кисти!</p>");
						}
						
						

						owner.unequipWeaponIntoVoid(weaponSlot, weapon, true);
						AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
						modifiedWeapon.setColours(dyePreviews);
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							
						} else {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
						}

//						weapon.setPrimaryColour(dyePreviewPrimary);
//						weapon.setSecondaryColour(dyePreviewSecondary);
						Main.game.addEvent(new EventLogEntry("Окрашено", weapon.getDisplayName(true)), false);
					}
				};

			} else if (index == 2) {
				if (!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("Перековка",
							"У вас нет молота для перековки, поэтому вы не можете изменить тип урона у " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Перековка",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Перековка",
						"Перековать " + weapon.getName() + " в выбранный вами тип урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в [style.boldEarth(заклинаниях Земли)], вы сможете перековать его в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить тип урона, вам понадобится еще один молот для перековки."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "были" : "был") + " перековано</b>!</p><p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "Перековка </b> " + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "долбить" : "долбит") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в [style.boldEarth(заклинаниях земли)], вы можете перековать " + weapon.getName() + " без использования молота для перековки!</p>");
						}

						owner.unequipWeaponIntoVoid(weaponSlot, weapon, true);
						AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
						modifiedWeapon.setDamageType(damageTypePreview);
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							
						} else {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
						}
						
						Main.game.addEvent(new EventLogEntry("Перековано", weapon.getDisplayName(true)), false);
					}
				};

			} else if (index == 3) {
				if ((!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || !Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH))
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
						&& !Main.game.isDebugMode()) {
					return new Response("Покрасить и перековать",
							"У вас нет одновременно кисти для покраски и молота для перековки, поэтому вы не можете покрасить и перековать " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Покрасить и перековать",
							"Вам нужно выбрать другой тип урона, прежде чем вы сможете перековать " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviews.equals(weapon.getColours())) {
					return new Response("Покрасить и перековать",
							"Сначала нужно выбрать другие цвета, прежде чем вы сможете покрасить " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Покрасить и перековать",
						"Покрасить и перековать  " + weapon.getName() + " в выбранные вами цвета и тип урона."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" Это действие необратимо, но благодаря вашему мастерству в использовании [style.boldEarth(Заклинаний земли)], вы можете красить и перековывать его в любое время."
										:" Это действие необратимо, и если вы захотите снова изменить цвета и тип урона, вам понадобятся новая кисть для окрашивания и новый молот для перековки."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH) && !Main.game.isDebugMode()) {
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ getDyeBrushEffects(weapon, dyePreviews.get(0))
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p><p><b>Того " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "были" : "был") + " перековано</b>!</p><p>"
										+ (isClothingDyeActionAvailable()
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH))
														+ "</b> кистей для окрашивания" + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "шт.") + " слева!"
												:"У вас осталось <b>0</b> кистей для окрашивания!")
										+"<br/>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"У вас осталось <b>" + Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER))
														+ "Перековка </b> " + (Main.game.getPlayer().getAllItemsInInventory().get(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "долбить" : "долбит") + " слева!"
												:"У вас осталось <b>0</b> молотов для перековки!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>Благодаря вашему мастерству в использовании [style.boldEarth(Заклинаний земли)], вы можете покрасить и перековать  " + weapon.getName() + " без использования кисти для окрашивания или молота для перековки!</p>");
						}

						owner.unequipWeaponIntoVoid(weaponSlot, weapon, true);
						AbstractWeapon modifiedWeapon = Main.game.getItemGen().generateWeapon(weapon);
						modifiedWeapon.setColours(dyePreviews);
						modifiedWeapon.setDamageType(damageTypePreview);
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
							
						} else {
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(modifiedWeapon));
						}
						
//						weapon.setDamageType(damageTypePreview);
//						weapon.setPrimaryColour(dyePreviewPrimary);
//						weapon.setSecondaryColour(dyePreviewSecondary);
						Main.game.addEvent(new EventLogEntry("Покрашено и перековано", weapon.getDisplayName(true)), false);
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	
	
	// Utility methods:
	
	private static String getDyeBrushEffects(AbstractClothing clothing, Colour colour) {
		return "<p>Когда вы берете в руки Кисть-краситель, вы замечаете, что фиолетовое свечение вокруг ее кончика становится все сильнее. Чем ближе вы подносите ее к вашему " + clothing.getName() + ", тем ярче становится свечение, пока внезапно в вашем сознании не начинают мелькать образы различных цветов. Как только ворсинки касаются поверхности " + clothing.getName() + ", Кисть-краситель мгновенно испаряется! Вы видите, что аркана-зачарование окрасило " + clothing.getName() + " " + colour.getName() + "."
				+ "</p>";
	}
	
	private static String getDyeBrushEffects(AbstractWeapon weapon, Colour colour) {
		return "<p>Когда вы берете в руки Кисть-краситель, вы замечаете, что фиолетовое свечение вокруг ее кончика становится все сильнее. Чем ближе вы подносите ее к вашему " + weapon.getName() + ", тем ярче становится свечение, пока внезапно в вашем сознании не начинают мелькать образы различных цветов. Как только ворсинки касаются поверхности " + weapon.getName() + ", Кисть-краситель мгновенно испаряется! Вы видите, что аркана-зачарование окрасило " + weapon.getName() + " " + colour.getName() + "."
				+ "</p>";
	}
	
	private static String getReforgeHammerEffects(AbstractWeapon weapon, DamageType damageType) {
		return "<p>Когда вы берете в руки молот для перековки, вы замечаете, как металлическая голова начинает излучать глубокое фиолетовое свечение. Чем ближе вы подносите его к вашему " + weapon.getName() + ", тем ярче становится это свечение, пока внезапно в вашем сознании не начинают мелькать образы различных типов урона. Как только вы касаетесь металлической головы " + weapon.getName() + ", молот для перековки мгновенно испаряется! Вы видите, что аркана-зачарование перековало " + weapon.getName() + " так что теперь оно наносит " + damageType.getName() + " урона.</p>";
	}
	
	private static String getItemDisplayPanel(AbstractCoreItem item, String SVGString, String title, String description) {
		return "<div class='inventoryImage'>" // style='width: calc(50% - 4px);'
					+ "<div class='inventoryImage-content'>"
						+ SVGString
					+ "</div>"
				+ "</div>"
				+ "<h5 style='margin-bottom:0; padding-bottom:0;'><b>"+title+"</b></h5>"
				+ "<p style='margin-top:0; padding-top:0;'>"
					+ UtilText.parse(item, description)
				+ "</p>";
	}
	
	private static String getGeneralResponseTabTitle(int index) {
		if(index==0) {
			return "Обзор";
		} else if(index==1) {
			return "Выбранный предмет";
		} else {
			return null;
		}
	}
	
	private static Response getCloseInventoryResponse() {
		if(interactionType == InventoryInteraction.CHARACTER_CREATION) {
			return new Response("Назад", "Вернуться к созерцанию своего отражения в зеркале.", CharacterCreation.CHOOSE_ADVANCED_APPEARANCE){
				@Override
				public int getSecondsPassed() {
					return -CharacterCreation.TIME_TO_CLOTHING;
				}
				@Override
				public void effects(){
					item = null;
					clothing = null;
					weapon = null;
				}
			};
			
		} else {
			return new ResponseEffectsOnly("Закрыть инвентарь", "Закрыть меню Инвентаря."){
				@Override
				public void effects(){
					item = null;
					clothing = null;
					weapon = null;
					Main.mainController.openInventory();
				}
			};
		}
	}
	
	private static Response getBuybackResponse() {
		if (buyback) {
			return new Response("Обычная торговля", "Вернуться к обычному меню торговли.", INVENTORY_MENU){
				@Override
				public void effects(){
					buyback = !buyback;
				}
			};
		} else {
			if(Main.game.getPlayer().getBuybackStack()==null || Main.game.getPlayer().getBuybackStack().isEmpty()) {
				return new Response("Обратный выкуп", "Вы еще не продали ни одного предмета, поэтому нечего выкупать...", null);
			} else {
				return new Response("Обратный выкуп", "Переключиться на меню обратного выкупа.", INVENTORY_MENU){
					@Override
					public void effects(){
						buyback = !buyback;
					}
				};
			}
		}
	}
	
	private static Response getQuickTradeResponse() {
		//TODO move this into options
		// Also, instead of being a response, this needs to be a button within the inventory UI
		return null;
		
//		if (Main.game.getDialogueFlags().quickTrade) {
//			return new Response("Quick-Manage: <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>",
//					"Quick-Manage is turned <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>!<br/>"
//							+ "That means you can buy and sell items with a single click when trading, and pick-up and drop items with a single click when in normal inventory mode.", INVENTORY_MENU){
//				
//				@Override
//				public DialogueNodeOld getNextDialogue() {
//					return Main.game.getCurrentDialogueNode();
//				}
//				
//				@Override
//				public void effects(){
//					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
//				}
//			};
//			
//		} else {
//			return new Response("Quick-Manage: <b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>OFF</b>",
//					"Quick-Manage is turned <b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>OFF</b>.<br/>"
//							+ "That means when you click on an item, you get a detailed view of the item before deciding whether to buy/sell or pick-up/drop it.", INVENTORY_MENU){
//
//				@Override
//				public DialogueNodeOld getNextDialogue() {
//					return Main.game.getCurrentDialogueNode();
//				}
//				
//				@Override
//				public void effects(){
//					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
//				}
//			};
//		}
	}
	
	private static Response getJinxRemovalResponse(boolean selfUnseal) {
		boolean ownsKey = Main.game.getPlayer().getUnlockKeyMap().containsKey(owner.getId()) && Main.game.getPlayer().getUnlockKeyMap().get(owner.getId()).contains(clothing.getSlotEquippedTo());
		int removalCost = clothing.getJinxRemovalCost(Main.game.getPlayer(), selfUnseal);
		
		if(clothing.getEffects().stream().filter(ie->ie.getSecondaryModifier()==TFModifier.CLOTHING_SEALING).findFirst().get().getPotency()==TFPotency.SPECIAL) {
			return new Response("Распечатать ([style.colourTerrible(Невозможно)])",
					""+clothing.getName()+" имеет особое запечатывающее заклинание и не может быть распечатано обычными способами!",
					null);
		}
		
		if(interactionType==InventoryInteraction.COMBAT) {
			return new Response("Распечатать"+(ownsKey?"(Использовать клавишу)":"(<i>"+removalCost+" Эссенции</i>)"),
					"В бою одежду распечатать нельзя!",
					null);
		}

		if(interactionType==InventoryInteraction.SEX) {
			if(Main.sex.isCharacterImmobilised(Main.game.getPlayer())) {
				return new Response("Распечатать"+(ownsKey?"(Использовать клавишу)":"(<i>"+removalCost+" Эссенции</i>)"),
						UtilText.parse(owner, "Вы не можете распечатать [npc.namePos] одежду, так как сейчас вы [style.colourTerrible(неподвижны)]!"),
						null);
			}
			if(!selfUnseal && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return new Response("Распечатать"+(ownsKey?"(Использовать клавишу)":"(<i>"+removalCost+" Эссенции</i>)"),
						UtilText.parse(owner, "Пока вы прячетесь, вы не можете распечатать [npc.namePos] одежду!"),
						null);
			}
			if(!Main.sex.getInitialSexManager().isAbleToRemoveClothingSeals(Main.game.getPlayer())) {
				return new Response("Распечатать"+(ownsKey?"(Использовать клавишу)":"(<i>"+removalCost+" Эссенции</i>)"),
						"В этой секс-сцене вы не можете распечатать одежду!",
						null);
			}
		}
		
		if(!ownsKey) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
				return new Response("Распечатать", "Вы не знаете, как распечатывать одежду! Возможно, вам стоит навестить Lilaya и расспросить её об этом...", null);
			}
			if(Main.game.getPlayer().getClothingCurrentlyEquipped().stream().anyMatch(c -> c.isSelfTransformationInhibiting())) {
				return new Response("Распечатать",
						"Хотя обычно вы можете распечатывать одежду, сейчас это невозможно из-за чар на одной или нескольких вещах из вашего снаряжения!<br/>[style.italicsArcane(Навестите Lilaya, чтобы снять печати с одежды!)]",
						null);
			}
			if(Main.game.getPlayer().getTattoos().values().stream().anyMatch(c -> c.isSelfTransformationInhibiting())) {
				return new Response("Распечатать",
						"Хотя обычно вы можете распечатывать одежду, сейчас это невозможно из-за чар на одной или нескольких ваших татуировках!<br/>[style.italicsArcane(Навестите Kate, чтобы снять татуировку!)]",
						null);
			}
		}
		
		if(ownsKey || Main.game.getPlayer().getEssenceCount()>=removalCost) {
			return new Response("Распечатать "+(ownsKey?"([style.italicsGood(Использовать ключ)])":"([style.italicsArcane("+removalCost+"  Эссенции)])"),
						ownsKey
							?"Так как у вас есть ключ, отпирающий этот предмет одежды, вы можете снять его, не тратя ни капли арканной эссенции!"
							:("Потратить "+removalCost+" арканной эссенции на распечатывание этого предмета одежды."
								+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_BONDAGE_VICTIM) && selfUnseal
									?"<br/>[style.italicsMinorBad(You have to pay 5 times the standard unseal cost due to your '"+Fetish.FETISH_BONDAGE_VICTIM.getName(Main.game.getPlayer())+"' фетиш!)]"
									:"")),
						interactionType==InventoryInteraction.SEX
							?Main.sex.SEX_DIALOGUE
							:INVENTORY_MENU) {
				@Override
				public void effects() {
					String s = "";
					if(ownsKey) {
						if(!Main.game.isInSex()) {
							Main.game.getPlayer().removeFromUnlockKeyMap(owner.getId(), clothing.getSlotEquippedTo());
						}
						s = "<p>Используя имеющийся у вас ключ, вы отпираете "+clothing.getName()+"!"
							+ "</p>";
						
					} else {
						Main.game.getPlayer().incrementEssenceCount(-removalCost, false);
						s = UtilText.parse(owner,
								"<p>Вы направляете силу своих арканных эссенций в [npc.namePos] "+clothing.getName()+", и яркая фиолетовая вспышка знаменует то, что печать снята!</p><p style='text-align:center;'>Снятие печати стоило вам [style.boldBad(\", "+removalCost+")] [style.boldArcane(Эссенции Arcane)]!</p>");
					}
					
					// Have to remove and then re-add the clothing as setting the sealed status affects the clothing's hashCode
					List<DisplacementType> clothingDisplacementTypes = new ArrayList<>();
					if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
						if(Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).get(clothing)!=null) {
							clothingDisplacementTypes.addAll(Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).get(clothing));
						}
						Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).remove(clothing);
					}
					clothing.setSealed(false);
					if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
						Main.sex.getClothingPreSexMap().get(owner).get(clothing.getSlotEquippedTo()).put(clothing, clothingDisplacementTypes);
					}
					
					if(interactionType==InventoryInteraction.SEX) {
						Main.sex.setUnequipClothingText(clothing, s);
						Main.mainController.openInventory();
						Main.sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
						Main.sex.setSexStarted(true);
						
					} else {
						Main.game.getTextEndStringBuilder().append(s);
					}
				}
			};
			
		} else {
			return new Response("Снять печать (<i>"+removalCost+" Эссенции</i>)",
					"Вам нужно как минимум "+removalCost+" арканных эссенций, чтобы снять печать с этой одежды!"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_BONDAGE_VICTIM)
									?"<br/>[style.italicsMinorBad(This cost is)] [style.italicsBad(5 times)] [style.italicsMinorBad(more than normal due to your '"+Fetish.FETISH_BONDAGE_VICTIM.getName(Main.game.getPlayer())+"' фетиш!)]"
									:""),
					null);
		}
	}
	
	
	
	// Items:
	
	private static void transferItems(GameCharacter from, GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			from.removeItem(item, count);
			to.addItem(item, count, false, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void dropItems(GameCharacter from, AbstractItem item, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasItem(item)) {
			from.dropItem(item, count, from.isPlayer());
		}
		resetPostAction();
	}
	
	private static void pickUpItems(GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			to.addItem(item, count, true, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void sellItems(GameCharacter from, GameCharacter to, AbstractItem item, int count, int itemPrice) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, false);
		
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addItem(item, count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(item, itemPrice, count));
				} else {
					to.addItem(item, count, false, true);
				}
				from.removeItem(item, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(new EventLogEntry("Продана",
								count+"x <span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+(count==1?item.getName():item.getNamePlural())+"</span> на "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				//((NPC) from).handleSellingEffects(item, count, itemPrice); // Delete: This was replaced by applyItemTransactionEffects
				Main.game.addEvent(new EventLogEntry("Куплено",
								count+"x <span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+(count==1?item.getName():item.getNamePlural())+"</span> на "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(!from.isPlayer()) {
				((NPC)from).applyItemTransactionEffects(item, count, itemPrice, true);
				
			} else {
				((NPC)to).applyItemTransactionEffects(item, count, itemPrice, false);
			}
		}
		
		resetPostAction();
	}
	
	
	// Weapons:
	
	private static void transferWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {
			from.removeWeapon(weapon, count);
			to.addWeapon(weapon, count, false, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void dropWeapons(GameCharacter from, AbstractWeapon weapon, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasWeapon(weapon)) {
			from.dropWeapon(weapon, count, from.isPlayer());
		}
		resetPostAction();
	}
	
	private static void pickUpWeapons(GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {
			to.addWeapon(weapon, count, true, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void sellWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count, int itemPrice) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, false);
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {

			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon(weapon), count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(weapon, itemPrice, count));
				} else {
					to.addWeapon(Main.game.getItemGen().generateWeapon(weapon), count, false, true);
				}
				from.removeWeapon(weapon, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(new EventLogEntry("Продана",
								count+"x <span style='color:"+weapon.getRarity().getColour().toWebHexString()+";'>"+(count==1?weapon.getName():weapon.getNamePlural())+"</span> на "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				//((NPC) from).handleSellingEffects(weapon, count, itemPrice); // Delete: This was replaced by applyItemTransactionEffects
				Main.game.addEvent(new EventLogEntry("Куплено",
								count+"x <span style='color:"+weapon.getRarity().getColour().toWebHexString()+";'>"+(count==1?weapon.getName():weapon.getNamePlural())+"</span> на "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(!from.isPlayer()) {
				((NPC)from).applyItemTransactionEffects(weapon, count, itemPrice, true);
				
			} else {
				((NPC)to).applyItemTransactionEffects(weapon, count, itemPrice, false);
			}
		}
		
		
		resetPostAction();
	}
	
	
	// Clothing:
	
	private static void transferClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			from.removeClothing(clothing, count);
			to.addClothing(clothing, count, false, to.isPlayer());
			owner = to;
		}
		resetPostAction();
	}
	
	
	private static void dropClothing(GameCharacter from, AbstractClothing clothing, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasClothing(clothing)) {
			from.dropClothing(clothing, count, from.isPlayer());
			
			if(from.getClothingCount(clothing) == 0) {
				owner = null;
			}
		}
		resetPostAction();
	}
	
	private static void pickUpClothing(GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			to.addClothing(clothing, count, true, to.isPlayer());
			
			owner = to;
		}
		resetPostAction();
	}
	
	private static void sellClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count, int itemPrice) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, false);
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addClothing(new AbstractClothing(clothing) {}, count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice, count));
				} else {
					to.addClothing(new AbstractClothing(clothing) {}, count, false, true);
				}
				from.removeClothing(clothing, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(new EventLogEntry("Продана",
								count+"x <span style='color:"+clothing.getRarity().getColour().toWebHexString()+";'>"+(count==1?clothing.getName():clothing.getNamePlural())+"</span> на "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				//((NPC) from).handleSellingEffects(clothing, count, itemPrice); // Delete: This was replaced by applyItemTransactionEffects
				Main.game.addEvent(new EventLogEntry("Куплено",
								count+"x <span style='color:"+clothing.getRarity().getColour().toWebHexString()+";'>"+(count==1?clothing.getName():clothing.getNamePlural())+"</span> на "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(!from.isPlayer()) {
				((NPC)from).applyItemTransactionEffects(clothing, count, itemPrice, true);
				
			} else {
				((NPC)to).applyItemTransactionEffects(clothing, count, itemPrice, false);
			}
		}
		
		resetPostAction();
	}
	
	private static String unequipClothingToFloor(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingOntoFloor(clothing, true, unequipper);
		}
		owner = null;
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String unequipClothingToUnequippersInventory(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingIntoUnequippersInventory(clothing, true, unequipper);
		}
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String unequipClothingToInventory(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingIntoInventory(clothing, true, unequipper);
		}
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String equipClothingFromInventory(GameCharacter to, InventorySlot slot, GameCharacter equipper, AbstractClothing clothing) {
		String equipDescription = to.equipClothingFromInventory(clothing, slot, true, equipper, owner);
		owner = to;
		resetPostAction();
		return equipDescription;
	}
	
	private static String equipClothingFromGround(GameCharacter to, InventorySlot slot, GameCharacter equipper, AbstractClothing clothing) {
		owner = to;
		resetPostAction();
		return to.equipClothingFromGround(clothing, slot, true, equipper);
	}
	
	private static Response getCondomSabotageResponse(AbstractClothing clothing) {
		if(clothing.getCondomEffect().getPotency().isNegative()) {
			if(Main.game.getPlayer().getEssenceCount() >= 1) {
				return new Response("Починить ([style.italicsArcane(1 Эссенция)])",
						"Потратить 1 арканную эссенцию, чтобы починить презерватив.", CLOTHING_INVENTORY) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementEssenceCount(-1, false);
						Main.game.getTextEndStringBuilder().append(
								"<p>Вы направляете силу арканной эссенции в презерватив, и после слабого фиолетового свечения он становится как новый!</p><p style='text-align:center;'>Починка презерватива стоила вам [style.boldBad(1)] [style.boldArcane(Арканной Эссенции)]!</p>");
						AbstractClothing c = (AbstractClothing) EnchantmentDialogue.craftAndApplyFullInventoryEffects(clothing, clothing.getClothingType().getEffects());

						Main.game.getPlayer().removeClothing(c);
						c.setName(c.getClothingType().getName());
						setClothing(c);
						Main.game.getPlayer().addClothing(c, false);
						
						RenderingEngine.setPage(Main.game.getPlayer(), c);
					}
				};
			} else {
				return new Response("Починить (<i>1 Эссенция</i>)", "Для починки презерватива вам нужно как минимум 1 арканная эссенция!", null);
			}
			
		} else {
			return new Response("Саботаж", "Сделав небольшой надрез на кончике этого презерватива, вы гарантируете, что он порвется в момент оргазма!", CLOTHING_INVENTORY) {
				@Override
				public void effects(){
					EnchantmentDialogue.setOutputName(clothing.getClothingType().getName());
					AbstractClothing c = (AbstractClothing) EnchantmentDialogue.craftAndApplyFullInventoryEffects(clothing,
							Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_CONDOM, TFModifier.ARCANE_BOOST, TFPotency.MAJOR_DRAIN, 0)),
							false);

					Main.game.getPlayer().removeClothing(c);
					setClothing(c);
					Main.game.getPlayer().addClothing(c, false);

					RenderingEngine.setPage(Main.game.getPlayer(), c);
					Main.game.getTextEndStringBuilder().append(
							"<p>Сделав крошечный, почти невидимый надрез на кончике презерватива, вы гарантируете, что он лопнет, когда наполнится спермой...</p><p style='text-align:center;'>[style.italicsBad(Теперь презерватив гарантированно порвется во время оргазма)]!</p>");
				}
			};
		}
	}
	
	private static void resetPostAction() {
		Main.game.setResponseTab(0);
		resetItems();
	}
	
	public static void resetItems() {
		item = null;
		clothing = null;
		weapon = null;
	}

	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		resetItems();
		InventoryDialogue.item = item;
		if(Main.getProperties().addItemDiscovered(item.getItemType())) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(item.getItemType().getName(false), item.getRarity().getColour()), true);
		}
	}

	public static AbstractWeapon getWeapon() {
		return weapon;
	}

	public static void setWeapon(InventorySlot slot, AbstractWeapon weapon) {
		resetItems();
		InventoryDialogue.weaponSlot = slot;
		InventoryDialogue.weapon = weapon;
		if (Main.getProperties().addWeaponDiscovered(weapon.getWeaponType())) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(weapon.getWeaponType().getName(), weapon.getWeaponType().getRarity().getColour()), true);
		}
	}

	public static AbstractClothing getClothing() {
		return clothing;
	}

	public static void setClothing(AbstractClothing clothing) {
		resetItems();
		InventoryDialogue.clothing = clothing;
		if(Main.getProperties().addClothingDiscovered(clothing.getClothingType())) {
			Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(clothing.getClothingType().getName(), clothing.getClothingType().getRarity().getColour()), true);
		}
	}

	public static boolean isBuyback() {
		return buyback;
	}

	public static void setBuyback(boolean buyback) {
		InventoryDialogue.buyback = buyback;
	}

	public static int getBuyBackPrice() {
		return buyBackPrice;
	}

	public static void setBuyBackPrice(int buyBackPrice) {
		InventoryDialogue.buyBackPrice = buyBackPrice;
	}

	public static int getBuyBackIndex() {
		return buyBackIndex;
	}

	public static void setBuyBackIndex(int buyBackIndex) {
		InventoryDialogue.buyBackIndex = buyBackIndex;
	}

	public static GameCharacter getOwner() {
		return owner;
	}

	public static void setOwner(GameCharacter owner) {
		InventoryDialogue.owner = owner;
	}

	public static NPC getInventoryNPC() {
		return inventoryNPC;
	}

	public static void setInventoryNPC(NPC inventoryNPC) {
		InventoryDialogue.inventoryNPC = inventoryNPC;
	}

	public static InventoryInteraction getNPCInventoryInteraction() {
		return interactionType;
	}

	public static void setNPCInventoryInteraction(InventoryInteraction npcInventoryInteraction) {
		interactionType = npcInventoryInteraction;
	}

}