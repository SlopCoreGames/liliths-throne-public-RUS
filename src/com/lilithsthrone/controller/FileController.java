package com.lilithsthrone.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDressingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.LoadedEnchantment;
import com.lilithsthrone.game.inventory.outfit.Outfit;
import com.lilithsthrone.game.inventory.outfit.OutfitSource;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

import javafx.stage.FileChooser;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class FileController {
	
	private static File lastOpened = null;
	
	public static void initArtworkListeners() {
		GameCharacter character = Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
				?Main.game.getPlayer()
				:(Main.game.getCurrentDialogueNode().equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)
					?Main.game.getDialogueFlags().getManagementCompanion()
					:CharactersPresentDialogue.characterViewed);
		
		String id = "ARTWORK_ADD";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				// Create file chooser for .jpg and .png images in the most recently used directory
				FileChooser chooser = new FileChooser();
				chooser.setTitle("Добавить изображения");
				chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.png", "*.gif"));
				if (lastOpened != null) {
					chooser.setInitialDirectory(lastOpened);
				}
				
				List<File> files = chooser.showOpenMultipleDialog(Main.primaryStage);
				if (files != null && !files.isEmpty()) {
					lastOpened = files.get(0).getParentFile();
					
					character.importImages(files);
					
					if (!character.isPlayer()) {
						CharactersPresentDialogue.resetContent(character);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"Добавить свои арты",
					"Просмотрите свои изображения и добавьте их персонажу. Пожалуйста, учтите, что размер анимированных GIF-файлов ограничен <b>максимумом в 10 МБ</b>, а если файл весит более 1 МБ, это <b>может</b> вызвать [style.italicsBad(значительные лаги)] в зависимости от вашей системы.<br/>Пользовательские изображения для текущего сохранения находятся в папке: <b>'data/images/"+Main.game.getId()+"'</b><br/>ID этого персонажа: <b>'"+character.getId()+"'</b>",
					130));
		}
		
		if (character.hasArtwork()) {
			try {
				Artwork artwork = character.getCurrentArtwork();
				
				id = "ARTWORK_INFO";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						if (!artwork.getArtist().getWebsites().isEmpty()) {
							Util.openLinkInDefaultBrowser(artwork.getArtist().getWebsites().get(0).getURL());
						}
					}, false);
					
					String description;
					if (artwork.getArtist().getName().equals("Custom")) {
						description = "Вы добавили это сами.";
					} else if (artwork.getArtist().getWebsites().isEmpty()) {
						description = "У этого художника нет привязанных сайтов!";
					} else {
						description = "Нажмите, чтобы открыть <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getWebsites().get(0).getName()+"</b>"
								+" ("+artwork.getArtist().getWebsites().get(0).getURL()+") <b>во внешнем браузере</b>!";
					}
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"Автор артов: <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getName()+"</b>",
							description));
				}
				
				id = "ARTWORK_PREVIOUS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						if (artwork.getTotalArtworkCount()>1) {
							artwork.incrementIndex(-1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_NEXT";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						if (artwork.getTotalArtworkCount()>1) {
							artwork.incrementIndex(1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_ARTIST_PREVIOUS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						if (character.getArtworkList().size()>1) {
							character.incrementArtworkIndex(-1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_ARTIST_NEXT";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						if (character.getArtworkList().size()>1) {
							character.incrementArtworkIndex(1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_DELETE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						artwork.getCurrentImage().delete();
						character.updateImages();
						if (!character.isPlayer())
							CharactersPresentDialogue.resetContent(character);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"Удалить пользовательский арт",
							"Удаляет текущее изображение этого персонажа.<br/>[style.italicsBad(Обратите внимание, что это удалит изображение из папки игры!)]"));
				}
			} catch (Exception ex) {
				System.err.println("MainController Artwork handling error.");
			}
		}
	}
	
	public static void initSaveLoadListeners() {
		String id;
		for (File f : Main.getSavedGames(false)) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
						OptionsDialogue.overwriteConfirmationName = "";
						Main.saveGame(fileName, true, false);
					} else {
						OptionsDialogue.overwriteConfirmationName = f.getName();
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно сохранения/загрузки игры.", OptionsDialogue.SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Перезаписать", ""));
			} else {
				id = "OVERWRITE_"+fileIdentifier+"_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Перезаписать (Отключено)",
							(!Main.game.isStarted()
									?"Чтобы перезаписать сохранение, нужно сначала начать игру!"
									:"Вы не можете перезаписать файлы сохранений, пока не находитесь в стандартной сцене локации!"));
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
						OptionsDialogue.loadConfirmationName = "";
						Main.loadGame(fileName);
					} else {
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.loadConfirmationName = f.getName();
						OptionsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно сохранения/загрузки игры.", OptionsDialogue.SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Загрузить", ""));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
						OptionsDialogue.deleteConfirmationName = "";
						Main.deleteGame(fileName);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					} else {
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно сохранения/загрузки игры.", OptionsDialogue.SAVE_LOAD));
					}
					
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Удалить", ""));
			}
		}
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, false);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Сохранить", ""));
		} else {
			id = "NEW_SAVE_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Сохранить (неактивно)",
						(!Main.game.isStarted()
								?"Чтобы сохранить игру, нужно сначала начать прохождение!"
								:"Вы не можете сохранить игру, пока не находитесь в стандартной сцене локации!")));
			}
		}
	}
	
	public static void initImportExportListeners() {
		for (File f : Main.getCharactersForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			String id = "DELETE_CHARACTER_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
						OptionsDialogue.deleteConfirmationName = "";
						Main.deleteExportedCharacter(fileName);
					} else {
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("Импорт/Экспорт", "Открыть окно импорта/экспорта.", OptionsDialogue.IMPORT_EXPORT));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Удалить", ""));
			}
		}
		if (MainController.document.getElementById("NEW_SAVE") != null) {
			MainController.addEventListener(MainController.document, "NEW_SAVE", "click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, false);
			}, false);
		}
	}
	
	public static void initPlayerImportListeners() {
		for (File f : Main.getCharactersForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			if (MainController.document.getElementById("IMPORT_CHARACTER_"+fileIdentifier) != null) {
				MainController.addEventListener(MainController.document, "IMPORT_CHARACTER_"+fileIdentifier, "click", e->{
					Main.importCharacter(f);
				}, false);
			}
		}
	}
	
	public static void initSlaveImportListeners() {
		for (File f : Main.getSlavesForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			if (MainController.document.getElementById("IMPORT_SLAVE_"+fileIdentifier) != null) {
				MainController.addEventListener(MainController.document, "IMPORT_SLAVE_"+fileIdentifier, "click", e->{
					try {
						Game.importCharacterAsSlave(fileName);
						MainController.updateUI();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Персонаж импортирован!");
					} catch (Exception ex) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "Ошибка импорта!");
					}
				}, false);
			}
		}
	}
	
	public static void initAuctionListeners() {
		for (NPC npc : Main.game.getCharactersPresent()) {
			String id = npc.getId()+"_BID";
			if (MainController.document.getElementById(id) != null) {
				if (Main.game.getPlayer().isHasSlaverLicense()) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						SlaverAlleyDialogue.setupBidding(npc);
						Main.game.setContent(new Response("", "", SlaverAlleyDialogue.AUCTION_BIDDING));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							UtilText.parse(npc, "Сделать ставку за [npc.name]"),
							UtilText.parse(npc, "Начать торги за [npc.name]. Есть шанс, что цена превысит [npc.her], так что сначала убедитесь, что у вас достаточно денег!")));
				} else {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							UtilText.parse(npc, "Сделать ставку за [npc.name]"),
							UtilText.parse(npc, "У вас нет лицензии работорговца, поэтому вы не можете делать ставки на рабов!")));
				}
			}
		}
	}
	
	public static void initLodgerImportListeners() {
		for (File f : Main.getSlavesForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			if (MainController.document.getElementById("IMPORT_LODGER_"+fileIdentifier) != null) {
				MainController.addEventListener(MainController.document, "IMPORT_LODGER_"+fileIdentifier, "click", e->{
					try {
						Game.importCharacterAsLodger(fileName);
						MainController.updateUI();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Персонаж импортирован!");
					} catch (Exception ex) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "Ошибка импорта!");
					}
				}, false);
			}
		}
	}
	
	public static void initLodgerWaitingListeners() {
		for (NPC npc : Main.game.getCharactersPresent()) {
			String id = npc.getId()+"_LODGER";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					CityHall.setupLodger(npc);
					Main.game.setContent(new Response("", "", CityHall.CITY_HALL_APPROACH_LODGER));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						UtilText.parse(npc, "Найти [npc.name]"),
						UtilText.parse(npc, "Осмотреться в зоне ожидания и попытаться найти [npc.name]...")));
			}
		}
	}
	
	public static void initClubberImportListeners() {
		for (File f : Main.getSlavesForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			if (MainController.document.getElementById("IMPORT_CLUBBER_"+fileIdentifier) != null) {
				MainController.addEventListener(MainController.document, "IMPORT_CLUBBER_"+fileIdentifier, "click", e->{
					try {
						Game.importCharacterAsClubber(fileName);
						MainController.updateUI();
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Персонаж импортирован!");
					} catch (Exception ex) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "Ошибка импорта!");
					}
				}, false);
			}
		}
	}
	
	public static void initEnchantmentSaveLoadListeners() {
		String id;
		for (File f : EnchantmentDialogue.getSavedEnchants()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.overwriteConfirmationName.equals(f.getName())) {
						EnchantmentDialogue.overwriteConfirmationName = "";
						EnchantmentDialogue.saveEnchant(fileName, true, EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD);
					} else {
						EnchantmentDialogue.overwriteConfirmationName = f.getName();
						EnchantmentDialogue.loadConfirmationName = "";
						EnchantmentDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно чар сохранения/загрузки.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Перезаписать", ""));
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.loadConfirmationName.equals(f.getName())) {
						EnchantmentDialogue.loadConfirmationName = "";
						LoadedEnchantment lEnch = EnchantmentDialogue.loadEnchant(fileName);
						
						EnchantmentDialogue.resetNonTattooEnchantmentVariables();
						AbstractCoreItem abstractItem = lEnch.getSuitableItem();
						EnchantmentDialogue.initModifiers(abstractItem);
						EnchantmentDialogue.getEffects().clear();
						for (ItemEffect ie : lEnch.getEffects()) {
							EnchantmentDialogue.addEffect(ie);
						}
						EnchantmentDialogue.setOutputName(lEnch.getName());
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно чар сохранения/загрузки.", EnchantmentDialogue.ENCHANTMENT_MENU));
					} else {
						EnchantmentDialogue.overwriteConfirmationName = "";
						EnchantmentDialogue.loadConfirmationName = f.getName();
						EnchantmentDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно чар сохранения/загрузки.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Загрузить", ""));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.deleteConfirmationName.equals(f.getName())) {
						EnchantmentDialogue.deleteConfirmationName = "";
						EnchantmentDialogue.deleteEnchant(fileName);
						EnchantmentDialogue.initSaveLoadMenu();
						Main.game.setContent(new Response("Сохранить/Загрузить", ".", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					} else {
						EnchantmentDialogue.overwriteConfirmationName = "";
						EnchantmentDialogue.loadConfirmationName = "";
						EnchantmentDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("Сохранить/Загрузить", ".", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Удалить", ""));
			}
		}
		
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				EnchantmentDialogue.saveEnchant(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Сохранить", ""));
		}
		for (Map.Entry<String, LoadedEnchantment> entry : EnchantmentDialogue.getLoadedEnchantmentsMap().entrySet()) {
			id = "LOADED_ENCHANTMENT_"+entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLoadedEnchantment(entry.getValue()));
			}
		}
		id = "LOADED_ENCHANTMENT_CURRENT";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLoadedEnchantment(EnchantmentDialogue.getCurrentEnchantmentAsLoadedEnchantment()));
		}
	}
	
	public static void initTattooSaveLoadListeners() {
		String id;
		for (File f : CosmeticsDialogue.getSavedTattoos()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || CosmeticsDialogue.overwriteConfirmationName.equals(f.getName())) {
						CosmeticsDialogue.overwriteConfirmationName = "";
						CosmeticsDialogue.saveTattoo(fileName, true, CosmeticsDialogue.TATTOO_SAVE_LOAD);
					} else {
						CosmeticsDialogue.overwriteConfirmationName = f.getName();
						CosmeticsDialogue.loadConfirmationName = "";
						CosmeticsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно татуировок сохранения/загрузки.", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Перезаписать", ""));
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || CosmeticsDialogue.loadConfirmationName.equals(f.getName())) {
						CosmeticsDialogue.loadConfirmationName = "";
						Tattoo loadedTattoo = CosmeticsDialogue.loadTattoo(fileName);
						CharacterModificationUtils.tattoo = loadedTattoo;
						
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно татуировок сохранения/загрузки.", CosmeticsDialogue.getReturnToNodeFromTattooSaveLoad()));
					} else {
						CosmeticsDialogue.overwriteConfirmationName = "";
						CosmeticsDialogue.loadConfirmationName = f.getName();
						CosmeticsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно татуировок сохранения/загрузки.", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Загрузить", ""));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || CosmeticsDialogue.deleteConfirmationName.equals(f.getName())) {
						CosmeticsDialogue.deleteConfirmationName = "";
						CosmeticsDialogue.deleteTattoo(fileName);
						CosmeticsDialogue.initSaveLoadMenu();
						Main.game.setContent(new Response("Сохранить/Загрузить", ".", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					} else {
						CosmeticsDialogue.overwriteConfirmationName = "";
						CosmeticsDialogue.loadConfirmationName = "";
						CosmeticsDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("Сохранить/Загрузить", ".", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Удалить", ""));
			}
		}
		
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				CosmeticsDialogue.saveTattoo(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, CosmeticsDialogue.TATTOO_SAVE_LOAD);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Сохранить", ""));
		}
		for (Entry<String, Tattoo> entry : CosmeticsDialogue.getLoadedTattoosMap().entrySet()) {
			id = "LOADED_TATTOO_"+entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setTattoo(CharacterModificationUtils.tattooInventorySlot, entry.getValue(), BodyChanging.getTarget(), BodyChanging.getTarget()));
			}
		}
		id = "LOADED_TATTOO_CURRENT";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo, BodyChanging.getTarget(), BodyChanging.getTarget()));
		}
	}
	
	public static void initBodySaveLoadListeners() {
		String id;
		for (File f : BodyChanging.getSavedBodies()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.overwriteConfirmationName.equals(f.getName())) {
						BodyChanging.overwriteConfirmationName = "";
						BodyChanging.saveBody(fileName, true);
					} else {
						BodyChanging.overwriteConfirmationName = f.getName();
						BodyChanging.loadConfirmationName = "";
						BodyChanging.deleteConfirmationName = "";
						Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно трансформаций сохранения/загрузки.", BodyChanging.BODY_CHANGING_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Перезаписать", ""));
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					Body loadedBody = BodyChanging.loadBody(fileName);
					if(BodyChanging.isPresetTransformationAvailable(loadedBody)) {
						if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.loadConfirmationName.equals(f.getName())) {
							BodyChanging.loadConfirmationName = "";
							BodyChanging.applyLoadedBody(loadedBody);
							Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно трансформаций сохранения/загрузки.", BodyChanging.BODY_CHANGING_CORE));
						} else {
							BodyChanging.overwriteConfirmationName = "";
							BodyChanging.loadConfirmationName = f.getName();
							BodyChanging.deleteConfirmationName = "";
							Main.game.setContent(new Response("Сохранить/Загрузить", "Открыть окно трансформаций сохранения/загрузки.", BodyChanging.BODY_CHANGING_SAVE_LOAD));
						}
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Загрузить",
						BodyChanging.isPresetTransformationAvailable(BodyChanging.loadBody(fileName))
								?""
								:BodyChanging.getPresetTransformationUnavailabilityText(BodyChanging.loadBody(fileName))));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.deleteConfirmationName.equals(f.getName())) {
						BodyChanging.deleteConfirmationName = "";
						BodyChanging.deleteBody(fileName);
						BodyChanging.initSaveLoadMenu();
						Main.game.setContent(new Response("Сохранить/Загрузить", ".", BodyChanging.BODY_CHANGING_SAVE_LOAD));
					} else {
						BodyChanging.overwriteConfirmationName = "";
						BodyChanging.loadConfirmationName = "";
						BodyChanging.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("Сохранить/Загрузить", ".", BodyChanging.BODY_CHANGING_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Удалить", ""));
			}
		}
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				BodyChanging.saveBody(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Сохранить", ""));
		}
		for (Map.Entry<String, Util.Value<String, Body>> entry : BodyChanging.getPresetTransformationsMap().entrySet()) {
			id = "LOADED_BODY_"+entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLoadedBody(entry.getValue().getValue(), BodyChanging.getTarget()));
			}
		}
	}
	
	public static void initOutfitListeners() {
		String id;
		for (File f : LilayaDressingRoomDialogue.getSavedOutfits()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			
			id = "LOADED_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					String name = Util.getFileIdentifier(f);
					Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
					LilayaDressingRoomDialogue.setActiveOutfit(loadedOutfit);
					Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
					
				}, false);
				String name = Util.getFileIdentifier(f);
				Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
				int availabeOutfits = LilayaDressingRoomDialogue.getOutfitAvailabilityFromTile(loadedOutfit);
				int essenceCost = loadedOutfit.getEssenceCost();
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(loadedOutfit.getName()),
						"Всего предметов одежды в этом наряде: "+loadedOutfit.getClothing().size()
							+ "<br/>Total weapons in this outfit: "+loadedOutfit.getWeapons().size()
							+ "<br/>Available from items stored in area: "+availabeOutfits//Math.max(0, availabeOutfits)
							+ "<br/>Cost to purchase full outfit: "
								+UtilText.formatAsMoney(loadedOutfit.getCost(), "b")
								+", "
								+(essenceCost==0
									?UtilText.formatAsEssencesUncoloured(essenceCost, "b", false)
									:UtilText.formatAsEssences(essenceCost, "b", false))
							+"</div>",
						72));
			}
			
			id = "WEAR_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					
					String name = Util.getFileIdentifier(f);
					Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
					Main.game.getPlayer().loadOutfit(loadedOutfit, OutfitSource.CELL, OutfitSource.NOWHERE);
					Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFITS));
					
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"Надеть",
						"Ваш персонаж наденет всю одежду и оружие, указанные в этом наряде. Любая одежда и оружие, которые на вас надеты сейчас, будут отправлены в гардероб, если они не входят в этот наряд."));
			}
			
			id = "EDIT_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					
					String name = Util.getFileIdentifier(f);
					Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
					LilayaDressingRoomDialogue.setActiveOutfit(loadedOutfit);
					Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
					
				}, false);
//				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("View", ""));
			}
			
			id = "DELETE_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || LilayaDressingRoomDialogue.deleteConfirmationName.equals(f.getName())) {
						LilayaDressingRoomDialogue.deleteConfirmationName = "";
						LilayaDressingRoomDialogue.deleteOutfit(fileName);
						Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFITS));
						
					} else {
						LilayaDressingRoomDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFITS));
					}
				}, false);
//				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Delete", ""));
			}
		}
	}
}
