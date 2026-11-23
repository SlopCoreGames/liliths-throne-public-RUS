package com.lilithsthrone.game.dialogue.utils;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishPreference;
import com.lilithsthrone.game.character.gender.AndrogynousIdentification;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.game.settings.DifficultyLevel;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.ArtistWebsite;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.10.10
 * @author Innoxia, Maxis
 */
public class OptionsDialogue {
	
	private static boolean confirmNewGame = false;
	public static boolean startingNewGame = false;
	
	private static boolean alphabeticalFileSort = false;
	
	private static boolean defaultResetConfirmation = false;
	
	public static final DialogueNode MENU = new DialogueNode("Меню", "Меню", true) {
		
		@Override
		public String getLabel() {
			return "";
		}
		
		@Override
		public String getContent(){
			StringBuilder sb = new StringBuilder();
			sb.append("<h1 class='special-text' style='font-size:48px; line-height:52px; text-align:center;'>"+Main.NAME_OF_GAME+"</h1>");
			if(Main.game.isSillyMode()) {
				sb.append("<p class='special-text' style='text-align:center; margin:0 0; padding:0 0;'><i>Или... не могу поверить, что я провалилась в магическое зеркало и попала в мир, где моя тетя — демон?!</i></p>");
			}
			
			sb.append("<h5 class='special-text' style='text-align:center;'>Создано "+Main.AUTHOR+"</h5>");
			
			if (Main.CheckNotUnpacked()) {
				sb.append("<h3 class='special-text' style='text-align:center;'>[style.italicsBad("+Main.NAME_OF_GAME+" в данный момент запущено из временной папки!");
				sb.append("<br/>Пожалуйста, распакуйте .zip файл перед игрой!)]</h3>");
//				return sb.toString();
			}
			
			sb.append("<p>Эта игра представляет собой текстовую эротическую RPG и содержит много откровенного сексуального контента. Перед началом игры вы должны согласиться с дисклеймером!</p>")
					.append("<p>Вы можете посетить мой блог (https://lilithsthrone.blogspot.co.uk), чтобы следить за процессом разработки (используйте кнопку «Блог» ниже, чтобы открыть его в браузере по умолчанию).")
					//.append(" [style.italicsMinorBad(<b>Note:</b> Intrusive age verification is being rolled out on blogspot, so I will likely create a new blog soon.)]</p>")
					.append("<p style='text-align:center'><b>Пожалуйста, используйте мой блог или github, чтобы скачать последнюю официальную версию «Трона Лилит»!</b></p>")
					.append("<p style='text-align:center'><i>Скопируйте содержимое вашей папки 'data', чтобы использовать старые сохранения в этой версии!</i></p>");
			
			sb.append(getJavaVersionInformation());
			
			if(Toolkit.getDefaultToolkit().getScreenSize().getHeight()<800) {
				sb.append("<p style='text-align:center; color:").append(PresetColour.GENERIC_ARCANE.toWebHexString()).append(";'>")
					.append("Если разрешение игры не подходит под ваш экран, нажмите клавиши: 'Windows' + 'Стрелка вверх', чтобы развернуть на весь экран!</p>");
			}
			if(!Main.game.isStarted() && !Main.getProperties().name.isEmpty()) {
				sb.append("<h4 style='text-align:center;'>Последнее сохранение:</h4>");
				sb.append("<div class='container-full-width' style='width:50%;margin:0 25%;'>");
					sb.append("<h5 style='color:").append(Main.getProperties().nameColour).append(";text-align:center;'>").append(Main.getProperties().name).append("</h5>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'><b>Уровень ").append(Main.getProperties().level).append("</b></p>");
					String colourString = Main.getProperties().raceColour;
					if(!colourString.isEmpty()) {
						colourString = "color:"+colourString+";";
					}
					sb.append("<p style='text-align:center;margin:0;padding:0;").append(colourString).append("'><b>").append(Util.capitaliseSentence(Main.getProperties().race)).append("</b></p>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'>").append(UtilText.formatAsMoney(Main.getProperties().money, "b")).append("</p>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'>").append(UtilText.formatAsEssences(Main.getProperties().arcaneEssences, "b", false)).append("</p>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'>Quest: ").append(Util.capitaliseSentence(Main.getProperties().quest)).append("</p>");
				sb.append("</div>");
			}
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				 if(confirmNewGame || !Main.game.isStarted()) {
					return new ResponseEffectsOnly(
							(!Main.game.isStarted()
									?"Новая игра"
									:"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Подтвердить</b>"),
							"Начать новую игру."
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(Не забудьте сначала сохранить игру!)]</b>"
									:"")){
						@Override
						public void effects() {
							if(!startingNewGame) {
								startingNewGame = true;
								
								//Fixes a bug where inventory would stay on screen
								if (Main.game.isStarted()) {
									Main.game.setInCombat(false);
									Main.game.setInSex(false);
								}
								
								Main.mainController.setAttributePanelContent("");
								Main.mainController.setRightPanelContent("");
								Main.mainController.setButtonsLeftContent("");
								Main.mainController.setButtonsRightContent("");
								Main.game.setRenderMap(false);
								Main.startNewGame(CharacterCreation.CHARACTER_CREATION_START);
								confirmNewGame = false;
							}
						}
					};
					
				 } else {
					 return new Response(
							 "Новая игра",
							 "Начать новую игру."
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(Не забудьте сначала сохранить игру!)]"
									:""),
								MENU){
							@Override
							public void effects() {
								confirmNewGame = true;
							}
						};
				 }
				
			} else if (index == 2) {
				return new Response("Сохранить/Загрузить", "Открыть окно сохранения/загрузки игры.", SAVE_LOAD){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 3) {
				return new Response("Экспорт персонажа", "Открыть окно экспорта персонажа.", IMPORT_EXPORT){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 4) {
				return new Response("Предупреждение", "Просмотреть дисклеймер игры.", DISCLAIMER){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
				
			} else if (index == 5) {
				return new ResponseEffectsOnly("Выход", "Завершает текущую игру и закрывает программу.<br/><br/><b>Не забудьте сначала сохранить игру!</b>"){
					@Override
					public void effects() {
						Main.primaryStage.close();
						confirmNewGame=false;
						System.exit(0);
					}
				};
				
			} else if (index == 6) {
				return new Response("Настройки", "Открыть страницу настроек.", OPTIONS){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};

			} else if (index == 7) {
				return new Response("Настройки контента", "Настройте предпочтительные параметры контента.", MISCELLANEOUS){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
			
			} else if (index == 8) {
				return new Response("Список изменений", "Просмотреть список изменений для этой версии.", PATCH_NOTES);
			
			} else if (index == 9) {
				return new Response("Авторы", "Просмотреть экран с титрами игры.", CREDITS);
				
			} else if (index == 11) {
				return new ResponseEffectsOnly("Блог", "Открывает страницу:<br/><br/><i>https://lilithsthrone.blogspot.co.uk/</i><br/><br/><b>во внешнем браузере по умолчанию.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://lilithsthrone.blogspot.co.uk/");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 12) {
				return new ResponseEffectsOnly("Github", "Открывает страницу:<br/><br/><i>https://github.com/Innoxia/liliths-throne-public</i><br/><br/><b>во внешнем браузере по умолчанию.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://github.com/Innoxia/liliths-throne-public");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 13) {
				return new ResponseEffectsOnly("Вики", "Открывает страницу:<br/><br/><i>https://www.lilithsthrone.com/wiki/doku.php</i><br/><br/><b>во внешнем браузере по умолчанию.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://www.lilithsthrone.com/wiki/doku.php");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 0) {
				if(Main.game.isStarted()) {
					return new ResponseEffectsOnly("Продолжить", "Вернуться к тому, чем вы занимались до открытия этого меню."){
						@Override
						public void effects() {
							Main.mainController.openOptions();
							confirmNewGame=false;
							
						}
					};
					
				} else {
					if(Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
						return new ResponseEffectsOnly("Продолжить", "Продолжить игру с вашего последнего сохранения."){
							@Override
							public void effects() {
								Main.loadGame(Main.getProperties().lastSaveLocation);
								confirmNewGame=false;
								
							}
						};
					} else if ( "".equals(Main.getProperties().lastSaveLocation) ) {
						return new Response("Продолжить", "Нет ранее сохраненных игр, чтобы продолжить.", null);
					} else {
						return new Response("Продолжить", "Ранее сохраненная игра (под названием '"+Main.getProperties().lastSaveLocation+"') не найдена в папке 'data/saves'.", null);
					}
				}
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getJavaVersionInformation() {
		StringBuilder sb = new StringBuilder();
		String version = System.getProperty("java.version");
		
		sb.append("<p style='text-align:center;'>");
			sb.append("Ваша версия java: "+System.getProperty("java.version"));
			if (!version.equals("1.8.0_172")) {
				sb.append("<br/>[style.italicsBad(Рекомендуемая версия java — 1.8.0_172!)]");
				sb.append("<br/>[style.italicsMinorBad(Это может привести к сбоям, например, к зависанию подсказок! Пожалуйста, запустите игру с рекомендуемой версией или используйте .exe сборку.)]");
			}
		sb.append("</p>");
//				+" | ");
		
//		String[] version = System.getProperty("java.version").split("\\.");
//		if(version[0]!=null) {
//			if(Integer.valueOf(version[0])<9) {
//				sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least 9.0.1 to work correctly!");
//			} else {
//				sb.append("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//			}
//		}
//		if(version.length>=2) {
//			if(Integer.valueOf(version[1])<8) {
//				sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//				
//			} else {
//				if(version.length==3){
//					String[] versionMinor = version[2].split("_");
//					if(versionMinor.length>=2)
//						if(Integer.valueOf(versionMinor[1])<131) {
//							sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//							
//						} else {
//							sb.append("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//						}
//				} else {
//					sb.append("This game needs at least v1.8.0_131 to work correctly!");
//				}
//			}
//		}
		
		
		return sb.toString();
	}

	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";
	
	public static final DialogueNode SAVE_LOAD = new DialogueNode("Сохранение файлов игры", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(
					"<p style='text-align:center;'><b>Обратите внимание:</b></p><p>1. Для имен файлов сохранений подходят только стандартные символы (буквы и цифры).<br/>2. Файл 'AutoSave' автоматически перезаписывается каждый раз, когда вы переходите между картами.<br/>3. Файл 'QuickSave' автоматически перезаписывается при каждом быстром сохранении (клавиша "+Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKSAVE).getFullName()+").<br/>4. Вы не можете сохраняться во время сцен, ограничивающих передвижение, включая бой и секс.</p><div class='container-full-width' style='padding:0; margin:0;'><div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>Время</div><div class='container-full-width' style='width:calc(50% - 16px); text-align:center; background:transparent;'>Имя</div><div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>Сохранить | Загрузить | Удалить</div></div>");


			int i=0;
			
			if(Main.game.isStarted()) {
				saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
				i++;
			}
			
//			Main.getSavedGames(alphabeticalFileSort).sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSavedGames(alphabeticalFileSort)) {
				saveLoadSB.append(getSaveLoadRow("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Подтверждения: ",
						"Включает/выключает окна подтверждения при нажатии на загрузку, перезапись или удаление сохранения. Если включено, для выполнения любого действия потребуется два клика. Если выключено — только один.",
						SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Подтверждения: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ВКЛ</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>ВЫКЛ</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 2) {
				return new Response("Сортировка: Дата", "Отсортировать все ваши сохранения по дате.", SAVE_LOAD) {
					@Override
					public void effects() {
						alphabeticalFileSort = false;
					}
				};

			} else if (index == 3) {
				return new Response("Сортировка: Имя", "Отсортировать все ваши сохранения по имени.", SAVE_LOAD) {
					@Override
					public void effects() {
						alphabeticalFileSort = true;
					}
				};

			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode IMPORT_EXPORT = new DialogueNode("Экспорт персонажа", "", true) {
	
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
	
			saveLoadSB.append("<p>Здесь вы можете экспортировать своего текущего персонажа или удалить любых персонажей, которых экспортировали ранее. Любого NPC в игре можно экспортировать, открыв экран его информации (либо через список «присутствующих персонажей», либо через «контакты» в телефоне), а затем нажав кнопку «экспортировать персонажа» (в правом нижнем углу интерфейса).</p><p>Экспортированных персонажей можно использовать в качестве игрового персонажа при начале новой игры (выберите «Начать (Импорт)») или в качестве импортируемой рабыни на аукционе в Переулке Рабов.</p><div class='container-full-width' style='padding:0; margin:0;'><div class='container-quarter-width' style='text-align:center;'>Время</div><div class='container-half-width' style='width:calc(55% - 16px); text-align:center; background:transparent;'>Имя</div><div class='container-quarter-width' style='width:calc(20% - 16px); text-align:center; background:transparent;'>Функции</div></div>");
			
			Main.getCharactersForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			int i = 0;
			for(File f : Main.getCharactersForImport()){
				saveLoadSB.append(OptionsDialogue.getImportRow("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Подтверждения: ",
						"Включает/выключает окна подтверждения при нажатии на загрузку, перезапись или удаление сохранения. Если включено, для выполнения любого действия потребуется два клика. Если выключено — только один.",
							IMPORT_EXPORT) {
					@Override
					public String getTitle() {
						return "Подтверждения: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ВКЛ</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>ВЫКЛ</span>");
					}
					
					@Override
					public void effects() {
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};
	
			} else if (index == 2) {
				if(Main.game.isStarted()) {
					return new Response("Экспорт персонажа", "Экспортирует файл вашего персонажа в папку 'data/characters/'.", IMPORT_EXPORT){
						@Override
						public void effects() {
							Main.game.getCharacterUtils().saveCharacterAsXML(Main.game.getPlayer());
							Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Персонаж экспортирован!");
						}
					};
				} else {
					return new Response("Экспорт персонажа", "Сначала нужно начать игру!", null);
				}
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню.", OptionsDialogue.MENU);
	
			} else {
				return null;
			}
		}
	
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getSaveLoadRow(String date, String name, boolean altColour) {
		if(name!=null){
			String baseName = Util.getFileName(name);
			String identifierName = Util.getFileIdentifier(name);

			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ date
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+ baseName
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
									?(name.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled' id='OVERWRITE_" + identifierName + "_DISABLED'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (name.equals(loadConfirmationName)
									?"<div class='square-button saveIcon' id='LOAD_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
									:"<div class='square-button saveIcon' id='LOAD_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
	
	
							+ (name.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "-"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' value='New Save' style='padding:0;margin:0;width:100%;'></form>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
								?"<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
								:"<div class='square-button saveIcon disabled' id='NEW_SAVE_DISABLED' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
						+ "</div>"
					+ "</div>";
				
		}
	}

	private static String getImportRow(String date, String name, boolean altColour) {
		String baseName = Util.getFileName(name);
		String identifierName = Util.getFileIdentifier(name);
		
		return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					+ "<div class='container-quarter-width' style='background:transparent;'>"
						+ date
					+ "</div>"
					+ "<div class='container-half-width' style='width: calc(55% - 16px); background:transparent;'>"
						+ baseName
					+ "</div>"
					+ "<div class='container-quarter-width' style='padding:auto 0; margin:auto 0; width:20%; text-align:center; background:transparent;'>"
					+ (name.equals(deleteConfirmationName)
							?"<div class='square-button big' id='DELETE_CHARACTER_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
							:"<div class='square-button big' id='DELETE_CHARACTER_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
					+ "</div>"
				+ "</div>";
	}
	
	
	public static final DialogueNode OPTIONS = new DialogueNode("Настройки", "Настройки", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p><b>Светлая/Темная тема:</b><br/>Переключает основную тему отображения между светлой и темной. (В разработке!)</p><p><b>Размер шрифта:</b><br/>Циклически меняет базовый размер шрифта игры. Сейчас это влияет только на размер текста в основном диалоге, но в будущем я расширю это на все элементы интерфейса.<br/>Минимальный размер шрифта: "+Game.FONT_SIZE_MINIMUM+". Стандартный размер шрифта: "+Game.FONT_SIZE_NORMAL+". Максимальный размер шрифта: "+Game.FONT_SIZE_HUGE+".<br/>Текущий размер шрифта: "+Main.getProperties().fontSize+".</p><p><b>Плавное появление:</b><br/>Эта опция отвечает за плавное появление основной части текста при каждом отображении новой сцены. Хотя это делает переходы между сценами чуть красивее, по умолчанию она выключена, так как может вызывать раздражающие лаги в окнах инвентаря.</p><p><b>Сложность (Сейчас установлена на "+Main.getProperties().difficultyLevel.getName()+"):</b>");
			
			for(DifficultyLevel dl : DifficultyLevel.values()) {
				UtilText.nodeContentSB.append("<br/>"+(
						Main.getProperties().difficultyLevel==dl
							?"<b style='color:"+dl.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(dl.getName())+"</b> "+dl.getDescription()
							:"<span style='color:"+dl.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(dl.getName())+"</span> [style.colourDisabled("+dl.getDescription()+")]")
						 );
			}

			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Горячие клавиши", "Открыть страницу горячих клавиш, где вы можете настроить все игровые сочетания кнопок.", KEYBINDS);
				
			} else if (index == 2) {

				if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
					return new Response("Темная тема", "Переключить тему на темный вариант.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
						};
				} else {
					return new Response("Светлая тема (в разработке)", "Переключить тему на светлый вариант.<br/><br/><b>Она все еще находится в разработке...</b>.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
					};
				}

			} else if (index == 3) {
				return new Response("Размер шрифта -",
						"Уменьшить размер игрового шрифта. Значение по умолчанию — 18. Текущее значение: "+Main.getProperties().fontSize+".",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize > Game.FONT_SIZE_MINIMUM) {
							Main.getProperties().fontSize--;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 4) {
				return new Response("Размер шрифта +",
						"Увеличить размер игрового шрифта. Значение по умолчанию — 18. Текущее значение: "+Main.getProperties().fontSize+".",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize < Game.FONT_SIZE_HUGE) {
							Main.getProperties().fontSize++;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 5) {
				return new Response("Проявление: " + (Main.getProperties().hasValue(PropertyValue.fadeInText)
						? "[style.boldGood(ВКЛ)]"
						: "[style.boldBad(OFF)]"), "Включить или выключить плавное появление текста. При включении это может вызывать небольшие задержки в окнах инвентаря.", OPTIONS) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.fadeInText, !Main.getProperties().hasValue(PropertyValue.fadeInText));
						Main.saveProperties();
					}
				};
				
			} else if (index == 6) {
				return new Response("Гендерные местоимения", "Настроить все гендерные местоимения и имена.", OPTIONS_PRONOUNS);
				
			} else if (index == 7) {
				return new Response("Единицы измерения", "Выбрать предпочтительные единицы измерения.", UNIT_PREFERENCE);
			} else if (index == 8) {
				return new Response("Сложность: "+Main.getProperties().difficultyLevel.getName(), "Переключать сложность игры.", OPTIONS){
					@Override
					public void effects() {
						switch(Main.getProperties().difficultyLevel) {
							case NORMAL:
								Main.getProperties().difficultyLevel = DifficultyLevel.LEVEL_SCALING;
								break;
							case LEVEL_SCALING:
								Main.getProperties().difficultyLevel = DifficultyLevel.HARD;
								break;
							case HARD:
								Main.getProperties().difficultyLevel = DifficultyLevel.NIGHTMARE;
								break;
							case NIGHTMARE:
								Main.getProperties().difficultyLevel = DifficultyLevel.HELL;
								break;
							case HELL:
								Main.getProperties().difficultyLevel = DifficultyLevel.NORMAL;
								break;
						}
						Main.saveProperties();
						
						for(NPC npc : Main.game.getAllNPCs()) {
							if(!Main.game.isInCombat() || !Main.combat.getAllCombatants(false).contains(npc)) {
								npc.setMana(npc.getAttributeValue(Attribute.MANA_MAXIMUM));
								npc.setHealth(npc.getAttributeValue(Attribute.HEALTH_MAXIMUM));
							}
						}
					}
				};
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode KEYBINDS = new DialogueNode("Настройки", "Настройки", true) {

		@Override
		public String getHeaderContent() {
			return "<p><table align='center'><tr><th>Действие</th><th>Основная клавиша</th><th>Вторичная клавиша</th></tr>"
					+ getKeybindTableRow(KeyboardAction.MENU)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.QUICKSAVE)
					+ getKeybindTableRow(KeyboardAction.QUICKLOAD)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MENU_SELECT)
					+ getKeybindTableRow(KeyboardAction.INVENTORY)
					+ getKeybindTableRow(KeyboardAction.JOURNAL)
					+ getKeybindTableRow(KeyboardAction.MAP)
					+ getKeybindTableRow(KeyboardAction.CHARACTERS)
					+ getKeybindTableRow(KeyboardAction.ZOOM)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_EAST)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_1)
					+ getKeybindTableRow(KeyboardAction.RESPOND_2)
					+ getKeybindTableRow(KeyboardAction.RESPOND_3)
					+ getKeybindTableRow(KeyboardAction.RESPOND_4)
					+ getKeybindTableRow(KeyboardAction.RESPOND_5)
					+ getKeybindTableRow(KeyboardAction.RESPOND_6)
					+ getKeybindTableRow(KeyboardAction.RESPOND_7)
					+ getKeybindTableRow(KeyboardAction.RESPOND_8)
					+ getKeybindTableRow(KeyboardAction.RESPOND_9)
					+ getKeybindTableRow(KeyboardAction.RESPOND_10)
					+ getKeybindTableRow(KeyboardAction.RESPOND_11)
					+ getKeybindTableRow(KeyboardAction.RESPOND_12)
					+ getKeybindTableRow(KeyboardAction.RESPOND_13)
					+ getKeybindTableRow(KeyboardAction.RESPOND_14)
					+ getKeybindTableRow(KeyboardAction.RESPOND_0)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_PAGE)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_PAGE)

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_TAB)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_TAB)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_EAST)
					+ "</table>"
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		ArrayList<Properties> presets;

		private void loadPresets() {
			presets = new ArrayList<>();

			// Load all text files in the folder as properties
			File presetFolder = new File("res/keybinds");
			if (presetFolder.exists() && presetFolder.isDirectory()) {
				for (File f : presetFolder.listFiles((dir, name) -> name.endsWith(".txt"))) {
					try (FileInputStream input = new FileInputStream(f)) {
						Properties preset = new Properties();
						preset.load(input);
						presets.add(preset);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				presetFolder.mkdirs();
			}
		}

		private void loadPreset(Properties preset) {
			// Clear existing mappings
			Main.getProperties().hotkeyMapPrimary.clear();
			Main.getProperties().hotkeyMapSecondary.clear();

			// Create a key mapping for every action contained in the given property
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (preset.containsKey(ka.name())) {
					String[] keys = preset.getProperty(ka.name()).split(" or ");
					Main.getProperties().hotkeyMapPrimary.put(ka, KeyCodeWithModifiers.fromString(keys[0]));
					if (keys.length == 2) Main.getProperties().hotkeyMapSecondary.put(ka, KeyCodeWithModifiers.fromString(keys[1]));
				}
			}
		}

		private void savePreset(int index) {
			// Create new properties containing current key mappings
			Properties preset = new Properties();
			preset.setProperty("NAME", "Пользовательский " + index);
			preset.setProperty("DESCRIPTION", "Применить ранее сохраненные назначения клавиш.");

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapPrimary.entrySet())
				if (e.getValue() != null)
					preset.setProperty(e.getKey().name(), e.getValue().toString());

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapSecondary.entrySet()) {
				if (e.getValue() != null) {
					// Write or append to existing entry
					String primary = preset.getProperty(e.getKey().name());
					primary = primary == null ? e.getValue().toString() : primary + " or " + e.getValue().toString();
					preset.setProperty(e.getKey().name(), primary);
				}
			}

			// Write properties to file
			try (FileOutputStream output = new FileOutputStream("res/keybinds/custom" + index + ".txt")) {
				preset.store(output, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			// Load the presets if uninitialized
			if (presets == null) loadPresets();

			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", OPTIONS);
				
			} else if (index <= presets.size()) {
				Properties preset = presets.get(index - 1);
				return new Response(preset.getProperty("NAME", "Пользовательский " + index), preset.getProperty("DESCRIPTION", "Применить ранее сохраненные назначения клавиш."), KEYBINDS) {
					@Override
					public void effects() {
						loadPreset(preset);
						Main.saveProperties();
					}
				};
				
			} else if (index == 14) {
				return new Response("Сохранить пресет",
						"Сохранить текущие назначения клавиш в файл. Если вы хотите удалить сохраненные пресеты, перейдите в папку 'res/keybinds' и удалите ненужные .txt файлы. (После перезапуска игры они исчезнут из этого списка.)",
						KEYBINDS) {
					@Override
					public void effects() {
						savePreset(presets.size() - 2);
						loadPresets();
					}
				};
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getKeybindTableRow(KeyboardAction action) {
		return "<tr>"
				+ "<td>"
				+ action.getName()
				+ "</td><td style='min-width:160px;'><div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='KB_PRIMARY_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapPrimary.get(action).getFullName())
				+ "</div><div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? " empty" : "")
				+ "' id='KB_PRIMARY_CLEAR_"
				+ action
				+ "'><b>x</b></div></td><td style='min-width:160px;'><div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& !Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='KB_SECONDARY_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapSecondary.get(action).getFullName())
				+ "</div><div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? " empty" : "")
				+ "' id='KB_SECONDARY_CLEAR_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode OPTIONS_PRONOUNS = new DialogueNode("Настройки", "Настройки", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "<h5 style='text-align:center;'>Global gender names:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
							+ "<th>Body Parts</th>"
								+ "<th style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>Мужественные</th><th style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Андрогинные</th><th style='color:"+PresetColour.FEMININE.toWebHexString()+";'>Женственные</th></tr>");
			
			for(GenderNames gn : GenderNames.values()) {
				sb.append(getGenderNameTableRow(gn));
			}
							
			sb.append("</table>"
					+ "</p>"
					
					+ "<p>"
						+ "<h5 style='text-align:center;'>Player-specific pronouns:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
								+ "<th>Pronoun</th>"
								+ "<th style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>Мужественные</th><th style='color:"+PresetColour.FEMININE.toWebHexString()+";'>Женственные</th></tr>"
							+ getPronounTableRow(GenderPronoun.NOUN)
							+ getPronounTableRow(GenderPronoun.YOUNG_NOUN)
							+ getPronounTableRow(GenderPronoun.SECOND_PERSON)
							+ getPronounTableRow(GenderPronoun.THIRD_PERSON)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_BEFORE_NOUN)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_ALONE)
						+ "</table>"
					+ "</p>"
					+ "<h5 style='text-align:center;'><span style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Андрогинные тела</span> (вариант 3)</h5><p><b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>Женский:</b> Считается <b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>женским</b>.<br/><b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Женская одежда:</b> Считается согласно женственности одежды. Если одежда нейтральна, считается <b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>женской</b>.<br/><b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Мужская одежда:</b> Считается согласно женственности одежды. Если одежда нейтральна, считается <b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>мужской</b>.<br/><b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>Мужской:</b> Считается <b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>мужским</b>.<br/></p>");
			
			return sb.toString();	
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Сохранить", "Сохранить все текущие местоимения.") {
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_MASCULINE_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameNeutral.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_ANDROGYNOUS_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameFemale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_FEMININE_" + gn +"').value")).toLowerCase());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp +"').value")).toLowerCase());
							Main.getProperties().genderPronounMale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp +"').value")).toLowerCase());
						}
						Main.saveProperties();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Местоимения сохранены!");
					}
				};
				
			} else if (index == 2) {
				return new Response("<span style='color:"+Main.getProperties().androgynousIdentification.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.getProperties().androgynousIdentification.getName())+"</span>",
						"Циклическое переключение способа, которым игра воспринимает андрогинные тела, описанного выше.", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						switch(Main.getProperties().androgynousIdentification){
							case FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_FEMININE;
								break;
							case CLOTHING_FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_MASCULINE;
								break;
							case CLOTHING_MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.MASCULINE;
								break;
							case MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.FEMININE;
								break;
							default:
								break;
						}
						
						Main.saveProperties();
					}
				};
				
			} else if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(Подтвердить настройки по умолчанию)]", "Сбросить все местоимения к их значениям по умолчанию.", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, gn.getMasculine());
							Main.getProperties().genderNameNeutral.put(gn, gn.getNeutral());
							Main.getProperties().genderNameFemale.put(gn, gn.getFeminine());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, gp.getFeminine());
							Main.getProperties().genderPronounMale.put(gp, gp.getMasculine());
						}
						Main.saveProperties();
						
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", OPTIONS);
				
			} else {
				return null;
			}
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static Response getDefaultResetConfirmationResponse() {
		return new ResponseEffectsOnly("По умолчанию", "Сбросить все местоимения к их значениям по умолчанию.<br/>[style.italicsMinorBad(Требуется повторная активация для подтверждения.)]") {
			@Override
			public void effects() {
				defaultResetConfirmation = true;
				Main.game.updateResponses();
			}
		};
	}
	
	private static String getGenderNameTableRow(GenderNames name) {
		return "<tr>"
					+ "<td>"
						+ (name.isHasPenis()?"[style.colourGood(Пенис)]":"[style.colourDisabled(Пенис)]")
						+ " " + (name.isHasVagina()?"[style.colourGood(Вагина)]":"[style.colourDisabled(Вагина)]")
						+ " " + (name.isHasBreasts()?"[style.colourGood(Грудь)]":"[style.colourDisabled(Грудь)]")
					+ "</td><td style='min-width:160px;'><form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_MASCULINE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameMale.get(name))
						+ "'></form></td><td style='min-width:160px;'><form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_ANDROGYNOUS_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameNeutral.get(name))
						+ "'></form></td><td style='min-width:160px;'><form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_FEMININE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameFemale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
				+ "</tr>";
	}
	
	private static String getPronounTableRow(GenderPronoun pronoun) {
		return "<tr>"
				+ "<td>"
					+ pronoun.getName()
				+ "</td><td style='min-width:160px;'><form style='padding:0;margin:0;text-align:center;'><input type='text' id='masculine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounMale.get(pronoun))+ "'></form></td><td style='min-width:160px;'><form style='padding:0;margin:0;text-align:center;'><input type='text' id='feminine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounFemale.get(pronoun))+ "'></form>"
				+ "</td>"
				+ "</tr>";
	}
	
	
	public static final DialogueNode PATCH_NOTES = new DialogueNode("Список изменений", "Список изменений", true) {
		
		@Override
		public String getContent(){
			return Main.getPatchNotes();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}

		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	public static final DialogueNode DISCLAIMER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent(){
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", MENU);
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	
	public static final DialogueNode GENDER_PREFERENCE = new DialogueNode("Гендерные предпочтения", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details><summary>[style.boldFeminine(Нажмите для подробностей.)]</summary>Эти настройки определят частоту встреч со случайными NPC определенного пола. У некоторых персонажей, например, у случайных суккубов-агрессоров, есть ограничения по полу, но ваши предпочтения будут учитываться везде, где это возможно.<br/><b>Визуальное представление шансов встречи отображается в полосах в нижней части каждого раздела.</b> (Разные оттенки для каждого пола служат исключительно для удобства распознавания в полосах и не несут иной смысловой нагрузки.)<br/>Персонаж считается обладающим грудью, если её размер не меньше AA.</details>");
			
			// Offspring preferences:

			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.ANDROGYNOUS, "Потомство с учетом предпочтений", "Определите, какое потомство будет учитывать ваши гендерные предпочтения."));
				int[] orderOptions = new int[] {3, 0, 2, 1};
				for(int i : orderOptions) {
					UtilText.nodeContentSB.append(
							(Main.getProperties().offspringGenderLevel==i
								?"<div id='OFFSPRING_GENDER_PREF_"+i+"' class='normal-button selected' style='width:48%; margin:1%; text-align:center; float:right; color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>"
									+ com.lilithsthrone.game.Properties.offspringGenderName[i]
									+ "</div>"
								:"<div id='OFFSPRING_GENDER_PREF_"+i+"' class='normal-button' style='width:48%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+com.lilithsthrone.game.Properties.offspringGenderName[i]+")]"
									+ "</div>"));
				}
			UtilText.nodeContentSB.append("</div></div>");
			
			// Gender preferences:
			
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(Подтвердить настройки по умолчанию)]", "Сбросить все гендерные предпочтения к значениям по умолчанию.", GENDER_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetGenderPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getGenderPreferencesPanel(PronounType type) {
		int count = 0;
		Colour colour = PresetColour.MASCULINE;
		switch(type) {
			case FEMININE:
				colour = PresetColour.FEMININE;
				break;
			case MASCULINE:
				colour = PresetColour.MASCULINE;
				break;
			case NEUTRAL:
				colour = PresetColour.ANDROGYNOUS;
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		for(Gender g : Gender.values()) {
			if(g.getType()==type) {
				sb.append(
						"<div style='display:inline-block; margin:4px auto;width:100%;'>"
							+ "<div style='display:inline-block; margin:0 auto;'>"
								+ "<div style='width:140px; float:left;'><b style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"</b></div>");
				
				for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
					sb.append("<div id='"+preference+"_"+g+"' class='preference-button"+(Main.getProperties().genderPreferencesMap.get(g)==preference.getValue()?" selected":"")+"'>"+Util.capitaliseSentence(preference.getName())+"</div>");
				}
								
				sb.append("<p><br/>"
								+ "<span style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"у </span> есть "
										+(g.getGenderName().isHasVagina()?"[style.colourGood(вагина)]":"нет [style.colourBad(вагина)]")+", "
										+(g.getGenderName().isHasPenis()?"[style.colourGood(пенис)]":"нет [style.colourBad(пенис)]")+", и "
										+ (g.getGenderName().isHasBreasts()?"[style.colourGood(грудь)]":"нет [style.colourBad(грудь)]")+"."
								+ "</p>"
							+ "</div>"
						+ "</div>"
						+ "<hr/>");
				count++;
			}
		}
		
		sb.append(
				getGenderRepresentation()
				+"</div>");
		
		return sb.toString();
	}

	private static String getOrientationRepresentation() {
		float total=0;
		for(SexualOrientation o : SexualOrientation.values()) {
			total+=Main.getProperties().orientationPreferencesMap.get(o);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+PresetColour.ANDROGYNOUS.toWebHexString()+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			for(SexualOrientation o : SexualOrientation.values()) {
				sb.append("<div style='width:" + (Main.getProperties().orientationPreferencesMap.get(o)/total) * (100) + "%; height:12px; background:"
						+ o.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>");
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode ORIENTATION_PREFERENCE = new DialogueNode("Настройки ориентации", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details><summary>[style.boldAndrogynous(Нажмите для подробностей.)]</summary>Эти параметры определят частоту встреч со случайными NPC с определенной сексуальной ориентацией. Учтите, что раса и женственность NPC могут влиять на их ориентацию, а у некоторых NPC она предопределена, но ваши предпочтения будут учитываться везде, где это возможно.</br><b>Визуальное представление шансов на встречу отображается в полосках внизу.</b> (Различные оттенки каждой ориентации служат исключительно для удобства распознавания в полосках и не несут иной смысловой нагрузки.)</details><div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.ANDROPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.AMBIPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.GYNEPHILIC));

			UtilText.nodeContentSB.append(getOrientationRepresentation() + "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(Подтвердить настройки по умолчанию)]", "Сбросить все настройки ориентации к значениям по умолчанию.", ORIENTATION_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetOrientationPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode FETISH_PREFERENCE = new DialogueNode("Настройки фетишей", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details><summary>[style.boldFetish(Нажмите для подробностей.)]</summary>Эти параметры определят вероятность наличия у случайных NPC определенных фетишей и предпочтений. У некоторых рас вероятность определенных фетишей выше, но ваши предпочтения будут учитываться везде, где это возможно.<br/> Настройки контента позволяют включать или отключать соответствующие фетиши.</details><div class='container-full-width' style='text-align:center;'>");
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					UtilText.nodeContentSB.append(getFetishPreferencesPanel(fetish));
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(Подтвердить настройки по умолчанию)]", "Сбросить все настройки фетишей к значениям по умолчанию.", FETISH_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetFetishPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getOrientationPreferencesPanel(SexualOrientation orient) {
		Colour colour = orient.getColour();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
					+ "<div style='width:140px; float:left;'><b style='color:"+colour.toWebHexString()+";'>" +Util.capitaliseSentence(orient.getName())+"</b></div>");
		
		for(SexualOrientationPreference preference : SexualOrientationPreference.values()) {
			sb.append("<div id='"+preference+"_"+orient+"' class='preference-button"+(Main.getProperties().orientationPreferencesMap.get(orient)==preference.getValue()?" selected":"")+"'>"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
		}
						
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}

	public static String getInformationDiv(String id, TooltipInformationEventListener information) {
		Game.informationTooltips.put(id, information);
		return "<div class='title-button no-select' id='"+id+"' style='position:relative; float:left; background:transparent; padding:0; margin:0;'>"
					+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
				+"</div>";
	}
	
	private static String getFetishPreferencesPanel(AbstractFetish fetish) {
		StringBuilder sb = new StringBuilder();
		
		Colour highlightColour = FetishPreference.valueOf(Main.getProperties().fetishPreferencesMap.get(fetish)).getColour();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
				+ getInformationDiv(fetish.getId()+"_INFO", new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(fetish.getName(Main.game.getPlayer())), fetish.getDescription(null)))
				+ "<div style='width:150px; float:left;'><b style='color:"+highlightColour.toWebHexString()+";'>"+Util.capitaliseSentence(fetish.getName(null))+"</b></div>");
		
		for(FetishPreference preference : FetishPreference.values()) {
			String disabledMsg=null;
			if(!fetish.isContentEnabled()) {
				if (Fetish.FETISH_SIZE_QUEEN.equals(fetish)) {
					disabledMsg = "Разница в размерах при проникновении";
				} else if (Fetish.FETISH_NON_CON_DOM.equals(fetish) || Fetish.FETISH_NON_CON_SUB.equals(fetish)) {
					disabledMsg = "Недобровольный секс";
				} else if (Fetish.FETISH_INCEST.equals(fetish)) {
					disabledMsg = "Инцест";
				} else if (Fetish.FETISH_LACTATION_SELF.equals(fetish) || Fetish.FETISH_LACTATION_OTHERS.equals(fetish)) {
					disabledMsg = "Лактация";
				} else if (Fetish.FETISH_ANAL_RECEIVING.equals(fetish) || Fetish.FETISH_ANAL_GIVING.equals(fetish)) {
					disabledMsg = "Анальный контент";
				} else if (Fetish.FETISH_FOOT_RECEIVING.equals(fetish) || Fetish.FETISH_FOOT_GIVING.equals(fetish)) {
					disabledMsg = "Фут-контент";
				} else if (Fetish.FETISH_ARMPIT_RECEIVING.equals(fetish) || Fetish.FETISH_ARMPIT_GIVING.equals(fetish)) {
					disabledMsg = "Контент с подмышками";
				} else {
					disabledMsg = "Неуказанный контент";
				}
			}
			if(disabledMsg!=null) {
				// Disabled fetishes to default, the fetish won't be a valid option for the generator anyway
				Main.getProperties().fetishPreferencesMap.put(fetish, fetish.getFetishPreferenceDefault().getValue());
				sb.append("<div style='display:inline-block;'><span class='option-disabled'>Фетиш принудительно отключен из-за "+disabledMsg+" настройка!</span></div>");
				break;
			} else {
				sb.append("<div id='"+preference+"_"+Fetish.getIdFromFetish(fetish)+"' class='preference-button"+(Main.getProperties().fetishPreferencesMap.get(fetish)==preference.getValue()?" selected":"")+"' style='width:70px;'>"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
		}
		
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}
	
	private static String getGenderRepresentation() {
		
		float total=0;
		for(Gender g : Gender.values()) {
			total+=Main.getProperties().genderPreferencesMap.get(g);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+PresetColour.FEMININE.getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			int f=0, m=0, n=0;
			for(Gender g : Gender.values()) {
				switch(g.getType()) {
					case MASCULINE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px; background:"+PresetColour.MASCULINE.getShades(8)[m] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						m++;
						break;
					case NEUTRAL:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px; background:"+PresetColour.ANDROGYNOUS.getShades(8)[n] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						n++;
						break;
					case FEMININE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px; background:"+PresetColour.FEMININE.getShades(8)[f] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						f++;
						break;
					default:
						break;
				}
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode AGE_PREFERENCE = new DialogueNode("Возрастные предпочтения", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details><summary>[style.boldAge(Нажмите для подробностей.)]</summary>Эти настройки определят частоту встреч со случайными NPC определенного возраста, исходя из их женственности. Некоторые NPC, такие как демоны и гарпии, могут казаться моложе, чем они есть на самом деле, но ваши предпочтения будут учитываться везде, где это возможно.<br/><b>Визуальное представление шансов на возраст можно увидеть на полосах в нижней части каждого раздела.</b></details>");
			
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(Подтвердить настройки по умолчанию)]", "Восстановить все возрастные предпочтения до значений по умолчанию.", AGE_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetAgePreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getAgePreferencesPanel(PronounType type) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		int i=AgeCategory.values().length-1;
		for(AgeCategory ageCat : AgeCategory.values()) {
			sb.append(
					"<div style='display:inline-block; margin:4px auto;width:100%;'>"
						+ "<div style='display:inline-block; margin:0 auto;'>"
							+ "<div style='width:140px; float:left;'><b style='color:"+type.getColour().getShades(AgeCategory.values().length)[i]+";'>" +Util.capitaliseSentence(ageCat.getName())+"</b></div>");
			
			for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
				sb.append(
						"<div id='"+type+"_"+preference+"_"+ageCat+"' class='preference-button"+(Main.getProperties().agePreferencesMap.get(type).get(ageCat)==preference.getValue()?" selected":"")+"'>"
								+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
							
			sb.append("</div>"
					+ "</div>"
					+ "<hr/>");
			i--;
		}
		
		sb.append(
				getAgeRepresentation(type)
				+"</div>");
		
		return sb.toString();
	}
	
	private static String getAgeRepresentation(PronounType type) {
		
		float total=0;
		for(AgeCategory ageCat : AgeCategory.values()) {
			total+=Main.getProperties().agePreferencesMap.get(type).get(ageCat);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+type.getColour().getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");

			int i=(AgeCategory.values().length*2)-1;
			for(AgeCategory ageCat : AgeCategory.values()) {
				if(Main.getProperties().agePreferencesMap.get(type).get(ageCat)>0) {
					sb.append("<div style='width:calc(" + (Main.getProperties().agePreferencesMap.get(type).get(ageCat)/total) * (100) + "% - 1px); height:12px; background:"+type.getColour().getShades(AgeCategory.values().length*2)[i] + "; float:left; border-radius: 2;'></div>");
					sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
				}
				i--;
				i--;
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode FURRY_PREFERENCE = new DialogueNode("Предпочтения фурри", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details><summary>[style.boldHuman(Нажмите для подробностей.)]</summary>Эти настройки определяют количество фурри-контента, который вы встретите в игре. <i>На данный момент эти параметры влияют только на случайных NPC, но в процессе разработки могут быть добавлены версии основных персонажей с пониженным уровнем пушистости.</i><br/>[style.italicsGood(Наведите на кнопки, чтобы узнать значение каждого варианта!)]<br/>Обратите внимание, что для некоторых рас, таких как демоны и гарпии, количество доступных вариантов предпочтений фурри ограничено.</details><span style='height:16px;width:800px;float:left;'></span>");
					
			UtilText.nodeContentSB.append("<div class='container-full-width'>");
					
				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_HUMAN.toWebHexString()+"; float:left; width:100%; text-align:center;'>Частота появления людей</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"HUMAN_SPAWN_RATE",
											PresetColour.RACE_HUMAN,
											Main.getProperties().humanSpawnRate+"%",
											Main.getProperties().humanSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");

				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_CENTAUR.toWebHexString()+"; float:left; width:100%; text-align:center;'>Частота появления тавров</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"TAUR_SPAWN_RATE",
											PresetColour.RACE_CENTAUR,
											Main.getProperties().taurSpawnRate+"%",
											Main.getProperties().taurSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");

				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_HALF_DEMON.toWebHexString()+"; float:left; width:100%; text-align:center;'>Частота появления полудемонов</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"HALF_DEMON_SPAWN_RATE",
											PresetColour.RACE_HALF_DEMON,
											Main.getProperties().halfDemonSpawnRate+"%",
											Main.getProperties().halfDemonSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");
			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.RACE_CENTAUR, "Пушистость верхней части тела тавров", "Установите, насколько пушистыми должны быть верхние части тел тавров."));
			UtilText.nodeContentSB.append(
					(Main.getProperties().taurFurryLevel==2
						?"<div id='TAUR_FURRY_LIMIT_"+2+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.MINIMUM.getColour().toWebHexString()+";'>"
							+ FurryPreference.MINIMUM.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+2+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+FurryPreference.MINIMUM.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==1
						?"<div id='TAUR_FURRY_LIMIT_"+1+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.HUMAN.getColour().toWebHexString()+";'>Человек</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+1+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled(Человек)]</div>")
					+(Main.getProperties().taurFurryLevel==0
						?"<div id='TAUR_FURRY_LIMIT_"+0+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>Девственница</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+0+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled(Нетронутый)]</div>")
					
					+(Main.getProperties().taurFurryLevel==5
						?"<div id='TAUR_FURRY_LIMIT_"+5+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.MAXIMUM.getColour().toWebHexString()+";'>"
							+ FurryPreference.MAXIMUM.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+5+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+FurryPreference.MAXIMUM.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==4
						?"<div id='TAUR_FURRY_LIMIT_"+4+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.NORMAL.getColour().toWebHexString()+";'>"
							+ FurryPreference.NORMAL.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+4+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+FurryPreference.NORMAL.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==3
						?"<div id='TAUR_FURRY_LIMIT_"+3+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.REDUCED.getColour().toWebHexString()+";'>"
							+ FurryPreference.REDUCED.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+3+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+FurryPreference.REDUCED.getName()+")]"
							+ "</div>"));
			UtilText.nodeContentSB.append("</div></div>");
			
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'><div style='display:inline-block; margin:4px auto;'><div style='float:left; text-align:right; margin-right:16px;'><b>Установить все предпочтения фурри:</b></div>");
			for(FurryPreference fp : FurryPreference.values()) {
				UtilText.nodeContentSB.append("<div id='ALL_FURRY_"+fp+"' class='normal-button' style='width:80px; margin:0 2px;'>"+fp.getName()+"</div>");
			}
			UtilText.nodeContentSB.append("</div><div style='display:inline-block; margin:4px auto;'><div style='float:left; text-align:right; margin-right:16px;'><b>Установить частоту появления всех видов:</b></div>");
			for(SubspeciesPreference sp : SubspeciesPreference.values()) {
				UtilText.nodeContentSB.append("<div id='ALL_SPAWN_"+sp+"' class='normal-button' style='width:80px; margin:0 2px;'>"+Util.capitaliseSentence(sp.getName())+"</div>");
			}
			UtilText.nodeContentSB.append("</div>"
											+"</div>");
												
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'>"
											+"<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(false)+";'><div class='container-full-width' style='text-align:center; width:calc(60% - 16px);background:transparent; margin:0 0 0 40%; padding:0;'><b style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+"; float:left; width:50%; text-align:center;'>Предпочтения фурри</b><b style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+"; float:left; width:50%; text-align:center;'>Частота появления</b></div></div>");

			int i=0;
			for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
				if(subspecies.isDisplayedInFurryPreferences()) {
					UtilText.nodeContentSB.append(getSubspeciesPreferencesPanel(subspecies, i%2==0));
					i++;
				}
			}
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(Подтвердить настройки по умолчанию)]", "Сбросить все настройки предпочтений фурри и частоты появления до значений по умолчанию.", FURRY_PREFERENCE) {
					@Override
					public void effects() {
						for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							Main.getProperties().setFeminineFurryPreference(subspecies, subspecies.getDefaultFemininePreference());
							Main.getProperties().setMasculineFurryPreference(subspecies, subspecies.getDefaultMasculinePreference());

							Main.getProperties().setFeminineSubspeciesPreference(subspecies, subspecies.getSubspeciesPreferenceDefault());
							Main.getProperties().setMasculineSubspeciesPreference(subspecies, subspecies.getSubspeciesPreferenceDefault());
						}
						Main.getProperties().humanSpawnRate = 5;
						Main.getProperties().taurSpawnRate = 5;
						Main.getProperties().halfDemonSpawnRate = 5;
						Main.saveProperties();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getSpawnRateDiv(String id, Colour colour, String valueDisplay, int value, int minimum, int maximum) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>");
		
			contentSB.append(
					"<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
					+ "</div>"
					+ "<div id='"+id+"_INCREASE' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldMinorGood(+)]")
					+ "</div>"
					+ "<div class='container-full-width' style='text-align:center; width:40%; float:right; margin:0;'>"
						+ "<b>"+valueDisplay+"</b>"
					+ "</div>"
					+ "<div id='"+id+"_DECREASE' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldMinorBad(-)]")
					+ "</div>"
					+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
					+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
				+ "</div>");
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getEntryBackgroundColour(boolean alternative) {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			if(alternative) {
				return "#d9d9d9";
			}
			return "#dddddd";
		} else {
			if(alternative) {
				return "#222222";
			}
			return "#1f1f1f";  
		}
	}
	
	private static String getSubspeciesPreferencesPanel(AbstractSubspecies s, boolean altColour) {
		StringBuilder sb = new StringBuilder();
		String baseStyle = "max-width:30px; width:14%; margin:0 1%; padding:0;";
		String subspeciesId = Subspecies.getIdFromSubspecies(s);
		
		sb.append("<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(altColour)+"; padding:0; margin:0 0 6px 0; border-left:solid 4px "+s.getColour(null).toWebHexString()+";'>");
		
			// Feminine:
			sb.append("<div class='container-full-width' style='text-align:center; width:40%; background:transparent; margin:0; padding:0;'>"
						+"<b style='color:"+PresetColour.FEMININE.toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getSingularFemaleName(null))+"</b>"
					+"</div>");
			
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");

				for(FurryPreference preference : FurryPreference.values()) {
					sb.append("<div id='FEMININE_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isFurryPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(s)==preference && s.isFurryPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+preference.getColour().toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				sb.append("</div>");
				sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					sb.append("<div id='FEMININE_SPAWN_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isSpawnPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesFemininePreferencesMap().get(s)==preference && s.isSpawnPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+PresetColour.FEMININE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				
			sb.append("</div>");
			
			// Masculine:
			sb.append("<div class='container-full-width' style='text-align:center; width:40%; background:transparent; margin:0; padding:0;'>"
					+"<b style='color:"+PresetColour.MASCULINE.toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getSingularMaleName(null))+"</b>"
				+"</div>");
		
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
			
				for(FurryPreference preference : FurryPreference.values()) {
					sb.append("<div id='MASCULINE_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isFurryPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(s)==preference && s.isFurryPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+preference.getColour().toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					sb.append("<div id='MASCULINE_SPAWN_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isSpawnPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s)==preference && s.isSpawnPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+PresetColour.MASCULINE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				
			sb.append("</div>");

			sb.append("<div class='title-button no-select' id='SUBSPECIES_PREFERENCE_INFO_"+subspeciesId+"' style='position:absolute; margin:0; padding:0; left:1%; right:auto; top:auto; bottom:auto;'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
						+"</div>");
		sb.append("</div>");
		
		return sb.toString();
	}

	public static final DialogueNode UNIT_PREFERENCE = new DialogueNode("Единицы измерения", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>Эти настройки определяют единицы измерения, используемые в игре. При использовании автоопределения система попытается подобрать правильные параметры для вашего языка. <br/><b>Если изменить любой параметр вручную, автоопределение отключится.</b></div><span style='height:16px;width:800px;float:left;'></span>");

			UtilText.nodeContentSB.append(getContentPreferenceDiv("AUTO_LOCALE",
					PresetColour.BASE_BLUE_LIGHT,
					"Автоматически",
					"Если включено, используется системный язык. В противном случае применяются следующие настройки.",
					Main.getProperties().hasValue(PropertyValue.autoLocale)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("METRIC_SIZES",
					PresetColour.BASE_BLUE_STEEL,
					"Метрическая система",
					"В игре будут использоваться метры и сантиметры вместо футов и дюймов.",
					Main.getProperties().hasValue(PropertyValue.metricSizes)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("METRIC_FLUIDS",
					PresetColour.BASE_BLUE_STEEL,
					"Метрические жидкости",
					"В игре будут использоваться литры и миллилитры вместо галлонов и унций.",
					Main.getProperties().hasValue(PropertyValue.metricFluids)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("METRIC_WEIGHTS",
					PresetColour.BASE_BLUE_STEEL,
					"Метрическая система веса",
					"В игре будут использоваться килограммы и граммы вместо фунтов и унций.",
					Main.getProperties().hasValue(PropertyValue.metricWeights)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("TWENTYFOUR_HOUR_TIME",
					PresetColour.BASE_LILAC_LIGHT,
					"24-часовой формат времени",
					"Время будет отображаться в 24-часовом формате вместо AM/PM.",
					Main.getProperties().hasValue(PropertyValue.twentyFourHourTime)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("INTERNATIONAL_DATE",
					PresetColour.BASE_LILAC_LIGHT,
					"Международный формат даты",
					"Сокращенная дата будет отображаться как день.месяц.год вместо месяц/день/год.",
					Main.getProperties().hasValue(PropertyValue.internationalDate)));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", OPTIONS);

			} else {
				return null;
			}
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
		    return DialogueNodeType.OPTIONS;
		}
	};
	
	/**
	 * To be followed by two closing div elements.
	 */
	private static String getCustomContentPreferenceDivStart(Colour colour, String title, String description) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		if(enabled) {
			contentSB.append(
					"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
							+ "[style.boldGood(ON)]"
						+ "</div>"
					+ "<div id='"+id+"_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>[style.colourDisabled(ВЫКЛ)]</div>");
		} else {
			contentSB.append(
					"<div id='"+id+"_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>[style.colourDisabled(ВКЛ)]</div><div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>[style.boldBad(ВЫКЛ)]</div>");
		}

		contentSB.append("</div>"
				+ "</div>");
		
		return contentSB.toString();
	}
	
	private static String getBreastsContentPreferenceVariableDiv(
			String id,
			Colour colour,
			String title,
			String description,
			String valueDisplay,
			int value, int minimum, int maximum,
			String valueDisplayUdders,
			int valueUdders, int minimumUdders, int maximumUdders) {
		
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		contentSB.append(
				"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
					+ "Breasts: "
					+ "<div id='"+id+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
					+ "</div>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
						+ "<b>"+valueDisplay+"</b>"
					+ "</div>"
					+ "<div id='"+id+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
					+ "</div>"
				+ "</div>");
		
		contentSB.append(
				"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
					+ "Udders: "
					+ "<div id='"+id+"_UDDERS_ON' class='normal-button"+(valueUdders==maximumUdders?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (valueUdders==maximumUdders?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
					+ "</div>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
						+ "<b>"+valueDisplayUdders+"</b>"
					+ "</div>"
					+ "<div id='"+id+"_UDDERS_OFF' class='normal-button"+(valueUdders==minimumUdders?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (valueUdders==minimumUdders?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
					+ "</div>"
				+ "</div>");
		
		contentSB.append("</div>"
				+"</div>");
		
		return contentSB.toString();
	}

	private static String getSkinColourContentPreferenceVariableDiv(
			String id,
			Colour colour,
			String title,
			String description) {
		
		StringBuilder contentSB = new StringBuilder();
		int minimum = 0;
		int maximum = 10;

		contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>");
			contentSB.append(
					"<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>");
			
			contentSB.append("<div class='container-half-width' style='width:calc(45% - 16px);'>");
			
				for(Entry<Colour, Integer> entry : Main.getProperties().skinColourPreferencesMap.entrySet()) {
					Colour skinColour = entry.getKey();
					int value = entry.getValue();
					contentSB.append(
							"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
								+ "<span style='color:"+skinColour.toWebHexString()+";'>"+Util.capitaliseSentence(skinColour.getName())+":</span> <div id='"+id+"_"+(skinColour).getId()+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
										+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
								+ "</div><div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>[style.colourSize"+value+"("+value+")]</div><div id='"+id+"_"+(skinColour).getId()+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
									+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
								+ "</div>"
							+ "</div>");
				}
			
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceVariableDiv(String id, Colour colour, String title, String description, String valueDisplay, int value, int minimum, int maximum) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		contentSB.append(
				"<div id='"+id+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
				+ "</div>"
				+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
					+ "<b>"+valueDisplay+"</b>"
				+ "</div>"
				+ "<div id='"+id+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
					+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
				+ "</div>");
		
		contentSB.append("</div>"
				+"</div>");
		
		return contentSB.toString();
	}
	
	
	public static final DialogueNode CREDITS = new DialogueNode("Авторы", "", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>Спасибо за игру! "+Main.NAME_OF_GAME+" Надеюсь, она принесет вам столько же удовольствия, сколько и мне процесс её создания! Огромное спасибо всем вам, кто оказывает финансовую поддержку! Благодаря вам я могу уделять больше времени работе над "+Main.NAME_OF_GAME+", и я обещаю, что сделаю эту игру настолько крутой, насколько это возможно!</p><p style='text-align:center;'>"
						+ Main.NAME_OF_GAME+" Создатель:<br/><b style='color:#9b78fa;'>Innoxia</b><br/><br/>Художники, чьи работы можно встретить в игре:<br/>");
			
			for(Artist artist : Artwork.allArtists) {
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append("<b style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</b><br/>");
				}
			}

			UtilText.nodeContentSB.append("<br/>Участники:</br><b style='color:#21bfc5;'>AceXP</b></br><b style='color:#21bfc5;'>DJ Addi</b></br><b style='color:#21bfc5;'>DSG</b></br><b style='color:#21bfc5;'>Irbynx</b></br><b style='color:#21bfc5;'>Maxis010</b></br><b style='color:#21bfc5;'>Nnxx</b></br><b style='color:#21bfc5;'>Norin</b></br><b style='color:#21bfc5;'>NoStepOnSnek</b></br><b style='color:#21bfc5;'>Phlarx</b></br><b style='color:#21bfc5;'>Pimgd</b></br><b style='color:#21bfc5;'>PoyntFury</b></br><b style='color:#21bfc5;'>Rfpnj</b></br><b style='color:#21bfc5;'>Tukaima</b></br>");
			
			UtilText.nodeContentSB.append("<br/>"
						+ "Special thanks to:<br/>"
						+ "<b>Sensei</b>,<br/>"
						+ "<b style='color:#fa0063;'>loveless</b>, <b style='color:#c790b2;'>Blue999</b>, and <b style='color:#ec9538;'>DesuDemona</b><br/>"
						+ "<b style='color:#21bec4;'>Github & wiki contributors</b><br/>"
						+ "<b style='color:#e06e5f;'>Everyone who's financially supported me</b>,<br/>"
						+ "<b>Bug reporters</b>,<br/>"
						+ "and<br/>"
						+ "<b>Everyone for playing Lilith's Throne!</b>"
					+ "</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>Легендарные покровители</h5><p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					if(cs.getName().equals("Anonymous")) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>?</b> ");
					} else {
						for(int i=0; i<cs.getUncommonCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getRareCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#127775</b> ");
						}
						for(int i=0; i<cs.getEpicCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getLegendaryCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>&#127775</b> ");
						}
						for(int i=0; i<cs.getLegendaryCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>&#9679</b> ");
						}
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>Эпические покровители</h5><p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					for(int i=0; i<cs.getUncommonCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
					}
					
					for(int i=0; i<cs.getRareCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
					}
					
					for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#127775</b> ");
					}
					for(int i=0; i<cs.getEpicCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+Subspecies.DEMON.getColour(null).toWebHexString()+";'>Демонические покровители</h5><p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()==0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", MENU);
				
			} else {
				int i=1;
				for(Artist artist : Artwork.allArtists) {
					for(ArtistWebsite website : artist.getWebsites()) {
						if(index==i) {
							return new ResponseEffectsOnly(website.getName(), "Открывает страницу:<br/><br/><i>"+website.getURL()+"</i><br/><br/><b>Во внешнем браузере по умолчанию.</b>"){
								@Override
								public void effects() {
									Util.openLinkInDefaultBrowser(website.getURL());
								}
							};
						}
						i++;
					}
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static Response getContentOptionsResponse(int responseTab, int index) {
		if (index == 1) {
			if (Main.game.getCurrentDialogueNode().equals(MISCELLANEOUS)) {
				return new Response("Разное", "Вы уже просматриваете настройки разного контента!", null);
			}
			return new Response("Разное", "Просмотреть настройки разного контента.", MISCELLANEOUS);
		} else if (index == 2) {
			if (Main.game.getCurrentDialogueNode().equals(GAMEPLAY)) {
				return new Response("Геймплей", "Вы уже просматриваете настройки игрового контента!", null);
			}
			return new Response("Геймплей", "Просмотреть настройки игрового контента.", GAMEPLAY);
		} else if (index == 3) {
			if (Main.game.getCurrentDialogueNode().equals(SEX)) {
				return new Response("Секс и фетиши", "Вы уже просматриваете настройки секса и фетишей!", null);
			}
			return new Response("Секс и фетиши", "Просмотреть настройки секса и фетишей.", SEX);
		} else if (index == 4) {
			if (Main.game.getCurrentDialogueNode().equals(BODIES)) {
				return new Response("Тела", "Вы уже просматриваете настройки тел!", null);
			}
			return new Response("Тела", "Просмотреть настройки тел.", BODIES);
		} else if (index == 5) {
			return new Response("[style.colourMinorBad(Сброс)]",
					"Сбрасывает <b>все предпочтения в категориях 'Разное', 'Геймплей', 'Секс и фетиши' и 'Тела'</b> до значений по умолчанию!<br/>Это <b>не</b> затрагивает предпочтения по полу, ориентации, возрасту, фурри или фетишам.",
					MISCELLANEOUS) {
				@Override
				public void effects() {
					for (PropertyValue pv : PropertyValue.values()) {
						Main.getProperties().setValue(pv, pv.getDefaultValue());
					}
					Main.getProperties().resetContentOptions();
					Main.saveProperties();
				}
			};
		} else if (index == 6) {
			if (Main.game.getCurrentDialogueNode().equals(GENDER_PREFERENCE)) {
				return new Response("Гендерные предпочтения", "Вы уже находитесь в меню предпочтений по полу!", null);
			}
			return new Response("Гендерные предпочтения", "Установите желаемую частоту встреч для каждого пола.", GENDER_PREFERENCE);
		} else if (index == 7) {
			if (Main.game.getCurrentDialogueNode().equals(ORIENTATION_PREFERENCE)) {
				return new Response("Настройки ориентации", "Вы уже находитесь в меню предпочтений по сексуальной ориентации!", null);
			}
			return new Response("Настройки ориентации", "Установите желаемую частоту встреч для каждой сексуальной ориентации.", ORIENTATION_PREFERENCE);
		} else if (index == 8) {
			if (Main.game.getCurrentDialogueNode().equals(AGE_PREFERENCE)) {
				return new Response("Возрастные предпочтения", "Вы уже находитесь в меню предпочтений по возрасту!", null);
			}
			return new Response("Возрастные предпочтения", "Установите желаемую частоту встреч для каждого возраста.", AGE_PREFERENCE);
		} else if (index == 9) {
			if (Main.game.getCurrentDialogueNode().equals(FURRY_PREFERENCE)) {
				return new Response("Предпочтения фурри", "Вы уже находитесь в меню предпочтений по фурри!", null);
			}
			return new Response("Предпочтения фурри", "Установите желаемый уровень фурри для встреч.", FURRY_PREFERENCE);
		} else if (index == 10) {
			if (Main.game.getCurrentDialogueNode().equals(FETISH_PREFERENCE)) {
				return new Response("Настройки фетишей", "Вы уже просматриваете экран предпочтений фетишей!", null);
			}
			return new Response("Настройки фетишей", "Установите желаемую частоту встреч с фетишами.", FETISH_PREFERENCE);
		} else if (index == 0) {
			return new Response("Назад", "Вернуться в главное меню.", MENU);
		}
		return null;
	}
	
	public static final DialogueNode MISCELLANEOUS = new DialogueNode("Настройки контента (Разное)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.GENERIC_GOOD, "Частота автосохранения", "Выберите, как часто игра должна делать автосохранение при переходе с одной карты на другую."));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='AUTOSAVE_FREQUENCY_"+i+"' class='normal-button"+(Main.getProperties().autoSaveFrequency == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().autoSaveFrequency == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.autoSaveLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ARTWORK",
					PresetColour.BASE_BLUE_LIGHT,
					"Арт",
					"Отображает арты в окнах информации о персонажах.",
					Main.getProperties().hasValue(PropertyValue.artwork)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("THUMBNAIL",
					PresetColour.BASE_BLUE_STEEL,
					"Миниатюры",
					"Включает всплывающие подсказки с миниатюрами персонажей.",
					Main.getProperties().hasValue(PropertyValue.thumbnail)));
			
//			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_AQUA, "Preferred Artist", "Which artist's work is used by default."));
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>"
				+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
					+ "<b style='text-align:center; color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>Предпочтительный художник</b><b>:</b> Художник, чьи работы используются по умолчанию.</div><div class='container-half-width' style='width:calc(45% - 16px);'>");
			
			List<Artist> artists = new ArrayList<>(Artwork.allArtists);
			artists.remove(Artwork.customArtist);
			Collections.sort(artists, (e1, e2)->Main.getProperties().getArtistPriority(e2.getFolderName())-Main.getProperties().getArtistPriority(e1.getFolderName()));
			
			for(int i=0; i<artists.size(); i++) {
				Artist artist = artists.get(i);
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append("<div style='width:100%;  margin:1px 0; border-radius:4px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+";'>");
						UtilText.nodeContentSB.append("<div "+(i==0?"":"id='ARTIST_"+artist.getFolderName()+"_UP'")+" class='normal-button"+(i==0?" disabled":"")+"' style='width:10%; margin:0; text-align:center;'>");
							UtilText.nodeContentSB.append("&#8593;");
						UtilText.nodeContentSB.append("</div>");
						UtilText.nodeContentSB.append("<div "+(i==artists.size()-1?"":"id='ARTIST_"+artist.getFolderName()+"_DOWN'")
									+" class='normal-button"+(i==artists.size()-1?" disabled":"")+"' style='width:10%; margin:0; text-align:center; float:right;'>&#8595;</div>");
						UtilText.nodeContentSB.append("<div style='width:80%; margin:0; text-align:center; float:right;'>"
								+"<span style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</span> ("+artist.getArtworkCount()+")"
							+"</div>");
					UtilText.nodeContentSB.append("</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SHARED_ENCYCLOPEDIA",
					PresetColour.GENERIC_EXCELLENT,
					"Общая энциклопедия",
					"Если включено, ваш персонаж будет использовать общую Энциклопедию (статьи в которой открываются в любом прохождении). Если выключено, открытые статьи Энциклопедии будут отображаться только в том случае, если их обнаружил ваш текущий персонаж.",
					Main.getProperties().hasValue(PropertyValue.sharedEncyclopedia)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("WEATHER_INTERRUPTION",
					PresetColour.GENERIC_ARCANE,
					"Прерывания штормом",
					"Если включено, арканные штормы будут прерывать диалог, чтобы сообщить вам об их начале.",
					Main.getProperties().hasValue(PropertyValue.weatherInterruptions)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("DIALOGUE_COPY",
					PresetColour.BASE_BLUE_STEEL,
					"Автоматическое копирование текста",
					"Если включено, текст текущей сцены будет автоматически копироваться в буфер обмена вашей системы каждый раз при загрузке новой сцены. Эта опция позволяет легко вставлять текст игры в программы для чтения текста, избавляя от необходимости каждый раз выделять и копировать текст сцены вручную.",
					Main.getProperties().hasValue(PropertyValue.automaticDialogueCopy)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SILLY",
					PresetColour.GENERIC_GOOD,
					"Глупый режим",
					"Включает дополнительный глупый контент на протяжении всей игры.",
					Main.getProperties().hasValue(PropertyValue.sillyMode)));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode GAMEPLAY = new DialogueNode("Настройки контента (Геймплей)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ENCHANTMENT_LIMITS",
					PresetColour.GENERIC_ARCANE,
					"Нестабильность зачарования",
					"Включить/выключить механику '"+Attribute.ENCHANTMENT_LIMIT.getName()+"', которая ограничивает количество зачарованных предметов, которые вы можете носить. По умолчанию она включена; при её отключении баланс боевой системы игры может быть нарушен.",
					Main.getProperties().hasValue(PropertyValue.enchantmentLimits)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("BAD_END",
					PresetColour.GENERIC_TERRIBLE,
					"Плохие концовки",
					"Включить/выключить возможность наступления 'плохих концовок', которые завершают игру для вашего персонажа при их встрече.<br/>[style.italicsMinorBad(Обратите внимание, что плохие концовки содержат контент с принуждением, поэтому они игнорируют ваши настройки принуждения.)]",
//							+"<br/>[style.italicsTerrible(Please be aware that some bad ends are unaffected by this setting and are always present in the game.)]"
					Main.getProperties().hasValue(PropertyValue.badEndContent)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LEVEL_DRAIN",
					PresetColour.GENERIC_TERRIBLE,
					"Поглощение уровня",
					"Включить/выключить использование перка 'оргазмического поглощения уровня' уникальными NPC (например, в некоторых сценах с Амбер), из-за которого они поглощают ваш уровень за каждый ваш оргазм во время секса с ними.",
					Main.getProperties().hasValue(PropertyValue.levelDrain)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("OPPORTUNISTIC_ATTACKERS",
					PresetColour.BASE_CRIMSON,
					"Атакующие наживы",
					"Повышает вероятность случайных атак, когда у вас высокая Похоть, низкое Здоровье, вы покрыты жидкостями, раздеты или пьяны.",
					Main.game.isOpportunisticAttackersEnabled()));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("OFFSPRING_ENCOUNTERS",
					PresetColour.BASE_INDIGO,
					"Встречи с отпрысками",
					"Позволяет вам случайно встречать своих отпрысков по всему миру.<br/><i>Эта настройка не влияет на Карту отпрысков и на тех отпрысков, которых вы уже встретили.</i>",
					Main.game.isOffspringEncountersEnabled()));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_BLUE_LIGHT, "Женственность одежды", "Устанавливает ограничения значений женственности одежды."));
			for (int i=Main.getProperties().clothingFemininityTitles.length-1; i>=0; i--) {
				if (Main.getProperties().getClothingFemininityLevel() == i) {
					UtilText.nodeContentSB.append("<div id='CLOTHING_FEMININITY_"+i
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+Main.getProperties().clothingFemininityColours[i].toWebHexString()+";'><b>"+Main.getProperties().clothingFemininityTitles[i]+"</b></div>");
				} else {
					UtilText.nodeContentSB.append("<div id='CLOTHING_FEMININITY_"+i
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+Main.getProperties().clothingFemininityTitles[i]+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_PINK, "Пропуск секс-действий", "Если включено, требования к уровню порочности для секс-действий могут игнорироваться."));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='BYPASS_SEX_ACTIONS_"+i+"' class='normal-button"+(Main.getProperties().bypassSexActions == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().bypassSexActions == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.bypassSexActionsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"PREGNANCY_DURATION",
					PresetColour.BASE_PINK_DEEP,
					"Длительность беременности",
					"Устанавливает максимальное время, необходимое для развития беременности от зачатия до родов.",
					Main.getProperties().pregnancyDuration+" нед."+(Main.getProperties().pregnancyDuration == 1?"":"s"),
					Main.getProperties().pregnancyDuration,
					1,
					40));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SPITTING_ENABLED",
					PresetColour.BASE_BLUE,
					"Отторжение зелий ТФ",
					"Если включено, персонаж может выплевывать принудительные зелья ТФ.",
					!Main.game.isSpittingDisabled()));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"FORCED_TF",
					PresetColour.TRANSFORMATION_GENERIC,
					"Принудительная ТФ",
					"Это устанавливает количество NPC с фетишем '"+Fetish.FETISH_TRANSFORMATION_GIVING.getName(null)+"', которые будут принудительно превращать вас после победы в бою.",
					Main.getProperties().forcedTFPercentage+"%",
					Main.getProperties().forcedTFPercentage,
					0,
					100));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_GREEN, "Лимиты рас при принудительной ТФ", "Позволяет установить максимальный предел фурри-рас, в которые NPC может принудительно превратить вас."));
			for (FurryPreference fp : Util.newArrayListOfValues(FurryPreference.REDUCED,
					FurryPreference.MINIMUM,
					FurryPreference.HUMAN,
					FurryPreference.MAXIMUM,
					FurryPreference.NORMAL)) {
				if (Main.getProperties().getForcedTFPreference() == fp) {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_FURRY_LIMIT_"+fp
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+fp.getColour().toWebHexString()+";'>"+fp.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_FURRY_LIMIT_"+fp
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+fp.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_GREEN, "Гендерная склонность при принудительной ТФ", "Это позволяет переопределить вкусы NPC, когда принудительная трансформация изменит ваш гендерный облик."));
			for (ForcedTFTendency ftt : Util.newArrayListOfValues(ForcedTFTendency.NEUTRAL,
					ForcedTFTendency.FEMININE,
					ForcedTFTendency.FEMININE_HEAVY,
					ForcedTFTendency.MASCULINE_HEAVY,
					ForcedTFTendency.MASCULINE)) {
				if (Main.getProperties().getForcedTFTendency() == ftt) {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_TENDENCY_"+ftt
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+ftt.getColour().toWebHexString()+";'>"+ftt.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_TENDENCY_"+ftt
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+ftt.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"FORCED_FETISH",
					PresetColour.FETISH,
					"Принудительные фетиши",
					"Это устанавливает количество NPC с фетишем '"+Fetish.FETISH_KINK_GIVING.getName(null)+"' фетиш, из-за которого они пытаются насильно навязать вам фетиши после победы над вами в бою.",
					Main.getProperties().forcedFetishPercentage+"%",
					Main.getProperties().forcedFetishPercentage,
					0,
					100));
			
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.FETISH, "Склонность к принудительным фетишам",
					"Это позволяет переопределить вкусы NPC и контролировать склонность принудительных фетишей к доминированию или подчинению."));
			for (ForcedFetishTendency fft : Util.newArrayListOfValues(ForcedFetishTendency.NEUTRAL,
					ForcedFetishTendency.BOTTOM,
					ForcedFetishTendency.BOTTOM_HEAVY,
					ForcedFetishTendency.TOP_HEAVY,
					ForcedFetishTendency.TOP)) {
				if (Main.getProperties().getForcedFetishTendency() == fft) {
					UtilText.nodeContentSB.append("<div id='FORCED_FETISH_TENDENCY_"+fft
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+fft.getColour().toWebHexString()+";'>"+fft.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_FETISH_TENDENCY_"+fft
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>[style.colourDisabled("+fft.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("COMPANION",
					PresetColour.BASE_GREEN_LIGHT,
					"Спутники",
					"Включает возможность добавлять рабов или дружественных персонажей в качестве ваших спутников.<br/>[style.boldBad(Внимание:)] Это экспериментальная функция; поддержка спутников была убрана в версии 0.3.9, поэтому за пределами Dominion не будет специальных диалогов или действий, связанных с вашими спутниками.",
					Main.getProperties().hasValue(PropertyValue.companionContent)));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode SEX = new DialogueNode("Настройки контента (Секс и фетиши)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("NON_CON",
					PresetColour.BASE_CRIMSON,
					"Недобровольный секс",
					"Включает темп «сопротивления» в секс-сценах, который содержит более экстремальные описания принуждения, а также упоминания в диалогах и действия, связанные с этим контентом.<br/>[style.italicsMinorBad(Обратите внимание, что плохие концовки включают в себя неконсенсуальный контент независимо от того, включена эта опция или нет.)]",
					Main.getProperties().hasValue(PropertyValue.nonConContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SADISTIC_SEX",
					PresetColour.BASE_RED,
					"Садистский секс",
					"Разблокирует «садистские» действия во время секса, такие как удушение, шлепки и плевки в партнера.",
					Main.getProperties().hasValue(PropertyValue.sadisticSexContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LIPSTICK_MARKING",
					PresetColour.BASE_RED_DARK,
					"Метки помадой",
					"Включает оставление следов помады на частях тела во время поцелуев во время секса.",
					Main.getProperties().hasValue(PropertyValue.lipstickMarkingContent)));
			
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("VOLUNTARY_NTR",
					PresetColour.GENERIC_MINOR_BAD,
					"Добровольный NTR",
					"При включении у вас появится возможность предложить определенным врагам секс с вашими спутницами, чтобы избежать боя.",
					Main.getProperties().hasValue(PropertyValue.voluntaryNTR)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("INVOLUNTARY_NTR",
					PresetColour.GENERIC_BAD,
					"Невольный NTR",
					"При включении враги могут решить заняться сексом только с вашей спутницей после победы над вашей группой в бою. Если выключено, во всех сценах секса после поражения в бою будете участвовать и вы.",
					Main.getProperties().hasValue(PropertyValue.involuntaryNTR)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("INCEST",
					PresetColour.BASE_ROSE,
					"Инцест",
					"Включает сексуальные взаимодействия между родственниками.",
					Main.getProperties().hasValue(PropertyValue.incestContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LACTATION",
					PresetColour.BASE_YELLOW_LIGHT,
					"Лактация",
					"Включает контент с лактацией.",
					Main.getProperties().hasValue(PropertyValue.lactationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SEXUAL_UDDERS",
					PresetColour.BASE_ORANGE_LIGHT,
					"Контент с грудью-промежностью и вымям",
					"Включает сексуальные действия, связанные с грудью-промежностью и вымям, а также позволяет накладывать трансформации груди-промежности и вымени на игрока.",
					Main.getProperties().hasValue(PropertyValue.udderContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("URETHRAL",
					PresetColour.BASE_PINK_DEEP,
					"Уретральный контент",
					"Включает трансформации уретры и проникновения через неё.",
					Main.getProperties().hasValue(PropertyValue.urethralContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("NIPPLE_PEN",
					PresetColour.BASE_PINK_DEEP,
					"Проникновения через соски",
					"Включает трансформации и сексуальные действия, связанные с проникновением через соски.",
					Main.getProperties().hasValue(PropertyValue.nipplePenContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ANAL",
					PresetColour.BASE_ORANGE,
					"Анальный контент",
					"Если отключено, все действия, связанные с анальным сексом, становятся недоступными во время секса.",
					Main.getProperties().hasValue(PropertyValue.analContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("GAPE",
					PresetColour.BASE_PINK_DEEP,
					"Контент с эффектом растяжения (Gape)",
					"Если отключено, описания широко раскрытых отверстий меняются на просто «свободные», а также скрывается любой специальный контент, связанный с эффектом растяжения.",
					Main.getProperties().hasValue(PropertyValue.gapeContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("PENETRATION_LIMITATION",
					PresetColour.BASE_PINK_DEEP,
					"Разница в размерах при проникновении",
					"Если включено, отверстия будут иметь ограниченную глубину, а это значит, что проникающие объекты (пенисы и хвосты) могут оказаться слишком длинными, чтобы войти полностью.",
					Main.getProperties().hasValue(PropertyValue.penetrationLimitations)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("PENETRATION_LIMITATION_DYNAMIC",
					PresetColour.BASE_PINK_DEEP,
					"Эффекты глубины от эластичности",
					"Если включено, то если эластичность отверстия составляет не менее «гибкой», максимальное значение «некомфортной глубины» будет увеличено, причем более высокие значения эластичности увеличивают его еще сильнее. (Примечание: применяется только тогда, когда также включена функция «Разница в размерах при проникновении».)",
					Main.getProperties().hasValue(PropertyValue.elasticityAffectDepth)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FOOT",
					PresetColour.BASE_TAN,
					"Контент для ступней",
					"Если отключено, во время секса будут недоступны все действия, связанные со ступнями.",
					Main.getProperties().hasValue(PropertyValue.footContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ARMPIT",
					PresetColour.BASE_PINK_LIGHT,
					"Контент для подмышек",
					"Если отключено, во время секса будут недоступны все действия, связанные с подмышками.",
					Main.getProperties().hasValue(PropertyValue.armpitContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("MUSK",
					PresetColour.BASE_YELLOW_LIGHT,
					"Контент мускуса",
					"Если отключено, в некоторых сценах количество мускуса будет уменьшено или они будут полностью пропущены, а эффект статуса «отмечен мускусом» будет отключен.",
					Main.getProperties().hasValue(PropertyValue.muskContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FURRY_TAIL_PENETRATION",
					PresetColour.BASE_MAGENTA,
					"Проникновения через пушистые хвосты",
					"Помечает все типы цепких хвостов как подходящие для проникновения, позволяя пушистым цепким хвостам участвовать в актах проникновения во время секса.",
					Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("INFLATION_CONTENT",
					PresetColour.CUM,
					"Раздувание спермой",
					"Включает механику раздувания спермой.",
					Main.getProperties().hasValue(PropertyValue.inflationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("AUTO_SEX_CLOTHING_MANAGEMENT",
					PresetColour.BASE_BLUE_STEEL,
					"Смена одежды после секса",
					"Надетые вещи будут автоматически возвращаться в исходное состояние после завершения сексуальных сцен.",
					Main.getProperties().hasValue(PropertyValue.autoSexClothingManagement)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("AUTO_SEX_CLOTHING_STRIP",
					PresetColour.BASE_PINK_LIGHT,
					"Автоматическое раздевание",
					"Если включено, все персонажи (кроме зрителей), которых вам разрешено раздевать во время секса (включая вас самих), будут начинать процесс обнаженными.",
					Main.getProperties().hasValue(PropertyValue.autoSexStrip)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("RAPE_PLAY_BY_DEFAULT",
					PresetColour.BASE_CRIMSON,
					"Рейп-плей разрешен по умолчанию",
					"Если включено, покорные персонажи с фетишем «невольная игрушка для секса» смогут участвовать в рейп-плее без предварительного получения разрешения.",
					Main.getProperties().hasValue(PropertyValue.rapePlayAtSexStart)));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_PINK, "Полные описания обнажения", "Настройка частоты подробных описаний открытых частей тела во время секса."));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='FULL_EXPOSURE_DESCRIPTIONS_"+i+"' class='normal-button"+(Main.getProperties().bypassSexActions == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().fullExposureDescriptions == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.fullExposureDescriptionsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode BODIES = new DialogueNode("Настройки контента (Тела)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("AGE",
					PresetColour.AGE_TWENTIES,
					"Возраст",
					"Включает описания возраста, который примерно соответствует внешнему виду персонажей.",
					Main.getProperties().hasValue(PropertyValue.ageContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FERAL",
					PresetColour.BASE_TAN,
					"Дикие",
					"Включает дикий контент, содержащий сексуальные и несексуальные взаимодействия с разумными существами, имеющими тела животных.",
					Main.getProperties().hasValue(PropertyValue.feralContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("CUM_REGENERATION",
					PresetColour.CUM,
					"Регенерация спермы",
					"Включает контент, связанный с регенерацией спермы, такой как уменьшение количества при множественных оргазмах за одну сессию и статус-эффект «полные яички».<br>Если отключено, яички всегда будут считаться полными, но без каких-либо негативных эффектов.",
					Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FUTA_BALLS",
					PresetColour.BASE_PINK,
					"Яички футанари",
					"При включении у NPC-футанари могут быть внешние яички. При выключении они всегда остаются внутренними.",
					Main.getProperties().hasValue(PropertyValue.futanariTesticles)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("CLOACA",
					PresetColour.BASE_PINK_LIGHT,
					"Двуногие клоаки",
					"Если включено, определенные двуногие расы (такие как гарпии и аллигатороморфы) будут иметь клоаки. Если выключено, все двуногие с клоаками будут считаться имеющими обычное строение гениталий. Некоторые особые расы, такие как ламии, всегда имеют клоаки и не зависят от этой настройки.",
					Main.getProperties().hasValue(PropertyValue.bipedalCloaca)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("VESTIGIAL_MULTI_BREAST",
					PresetColour.BASE_PURPLE_LIGHT,
					"Рудиментарная многогрудость",
					"Если включено, ряды груди у персонажей, имеющих несколько рядов, будут описываться как рудиментарные (недоразвитые) по размеру. Если выключено, ряды груди будут описываться как имеющие на один размер чашки меньше, чем предыдущий ряд.",
					Main.getProperties().hasValue(PropertyValue.vestigialMultiBreasts)));
			
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.NIPPLES, "Многогрудость", "Выберите, должны ли случайно сгенерированные зверолюди иметь несколько рядов груди."));
			int[] buttonOrder = new int[] {2, 1, 0, 3}; // Order buttons in this manner so that they appear to be a little more logical
			for (int i : buttonOrder) {
				UtilText.nodeContentSB.append("<div id='MULTI_BREAST_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().multiBreasts == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().multiBreasts == i
							?(i == 0
								?"[style.boldTerrible("
								:(i == 1
									?"[style.boldBad("
									:"[style.boldGood("))
							:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.multiBreastsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.NIPPLES_CROTCH, "Грудь в паху и вымя", "Выберите, должны ли случайно сгенерированные тавры и фурри-персонажи иметь вымя или грудь на паху."));
			for (int i = com.lilithsthrone.game.Properties.uddersLabels.length-1; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='UDDER_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().getUddersLevel() == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().getUddersLevel() == i
						?(i == 0?"[style.boldBad(":"[style.boldGood(")
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.uddersLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_BROWN_LIGHT, "Рост волос", "Выберите, как часто волосы игрока будут отрастать на 1 см. NPC сохраняют свою длину волос, так что эта настройка влияет только на вас."));
			int[] hairButtonOrder = new int[] {2, 1, 0, 3}; // Order buttons in this manner so that they appear to be a little more logical
			for (int i : hairButtonOrder) {
				boolean active = Main.getProperties().getHairGrowth() == i;
				UtilText.nodeContentSB.append("<div id='HAIR_GROWTH_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().getHairGrowth() == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(i == 0
								?"[style.bold"+(active?"Bad":"Disabled")+"(Никогда)]"
								:(i == 1
									?"[style.bold"+(active?"Size10":"Disabled")+"(Еженедельно)]"
									:(i == 2
										?"[style.bold"+(active?"Size5":"Disabled")+"(Ежедневно)]"
										:"[style.bold"+(active?"Size0":"Disabled")+"(Ежечасно)]")))
						+"</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_FACIAL",
					PresetColour.BASE_LILAC_LIGHT,
					"Растительность на лице",
					"Включает описания и контент, связанный с растительностью на лице.",
					Main.getProperties().hasValue(PropertyValue.facialHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_PUBIC",
					PresetColour.BASE_LILAC,
					"Лобковые волосы",
					"Включает описания и контент, связанный с лобковой растительностью.",
					Main.getProperties().hasValue(PropertyValue.pubicHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_BODY",
					PresetColour.BASE_PURPLE,
					"Волосы в подмышках",
					"Включает описания и контент, связанный с растительностью в подмышках.",
					Main.getProperties().hasValue(PropertyValue.bodyHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_ASS",
					PresetColour.BASE_PURPLE_DARK,
					"Волосы на заднице",
					"Включает описания и контент, связанный с растительностью на заднице.",
					Main.getProperties().hasValue(PropertyValue.assHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FEMININE_BEARD",
					PresetColour.BASE_BLUE_STEEL,
					"Женские бороды",
					"Позволяет женским персонажам отращивать бороды.",
					Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FURRY_HAIR",
					PresetColour.CLOTHING_DESATURATED_BROWN,
					"Меховые волосы",
					"Включает или выключает появление человекоподобных волос у персонажей с типом головы «Меховой».",
					Main.getProperties().hasValue(PropertyValue.furryHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SCALY_HAIR",
					PresetColour.BASE_GREEN_DARK,
					"Чешуйчатые волосы",
					"Включает или выключает появление человекоподобных волос у персонажей с рептилоидным или амфибийным типом головы.",
					Main.getProperties().hasValue(PropertyValue.scalyHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LIP_LISP",
					PresetColour.BASE_PINK_SALMON,
					"Шепелявость",
					"Включает или выключает шепелявую речь у персонажей с очень большими губами.",
					Main.getProperties().hasValue(PropertyValue.lipLispContent)));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_BREAST_GROWTH",
					PresetColour.BASE_PINK,
					"Средний рост груди при беременности",
					"Устанавливает <b>средний</b> размер чашки, который персонажи получают при каждой беременности. Фактический рост груди будет находиться в пределах "+Util.intToString(Main.getProperties().pregnancyBreastGrowthVariance)+" значений этого параметра.",
					Main.getProperties().pregnancyBreastGrowth == 0
							?"[style.boldDisabled(Выключено)]"
							:Main.getProperties().pregnancyBreastGrowth+" чашка"+(Main.getProperties().pregnancyBreastGrowth != 1?"s":""),
					Main.getProperties().pregnancyBreastGrowth, 0, 10,
					Main.getProperties().pregnancyUdderGrowth == 0
							?"[style.boldDisabled(Выключено)]"
							:Main.getProperties().pregnancyUdderGrowth+" чашка"+(Main.getProperties().pregnancyUdderGrowth != 1?"s":""),
					Main.getProperties().pregnancyUdderGrowth, 0, 10));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_BREAST_GROWTH_LIMIT",
					PresetColour.BASE_PINK_LIGHT,
					"Лимит роста груди при беременности",
					"Устанавливает максимальный размер чашки, до которого будет расти грудь персонажей в результате беременностей.",
					CupSize.getCupSizeFromInt(Main.getProperties().pregnancyBreastGrowthLimit).getCupSizeName()+"размер груди), ",
					Main.getProperties().pregnancyBreastGrowthLimit, 0, 100,
					CupSize.getCupSizeFromInt(Main.getProperties().pregnancyUdderGrowthLimit).getCupSizeName()+"размер груди), ",
					Main.getProperties().pregnancyUdderGrowthLimit, 0, 100));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_LACTATION",
					PresetColour.BASE_YELLOW,
					"Средняя лактация при беременности",
					"Устанавливает <b>средний</b> прирост лактации, который персонажи получают в результате каждой беременности. Фактический прирост лактации будет находиться в пределах "
							+Units.fluid(Main.getProperties().pregnancyLactationIncreaseVariance)+" от этого значения.",
					Main.getProperties().pregnancyLactationIncrease == 0
							?"[style.boldDisabled(Выключено)]"
							:Units.fluid(Main.getProperties().pregnancyLactationIncrease),
					Main.getProperties().pregnancyLactationIncrease, 0, 1000,
					Main.getProperties().pregnancyUdderLactationIncrease == 0
							?"[style.boldDisabled(Выключено)]"
							:Units.fluid(Main.getProperties().pregnancyUdderLactationIncrease),
					Main.getProperties().pregnancyUdderLactationIncrease, 0, 1000));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_LACTATION_LIMIT",
					PresetColour.BASE_YELLOW_LIGHT,
					"Лимит лактации при беременности",
					"Устанавливает максимальный уровень лактации, который персонажи получают от беременностей.",
					Units.fluid(Main.getProperties().pregnancyLactationLimit, Units.ValueType.PRECISE, Units.UnitType.SHORT),
					Main.getProperties().pregnancyLactationLimit, 0, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(),
					Units.fluid(Main.getProperties().pregnancyUdderLactationLimit, Units.ValueType.PRECISE, Units.UnitType.SHORT),
					Main.getProperties().pregnancyUdderLactationLimit, 0, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"BREAST_SIZE_PREFERENCE",
					PresetColour.NIPPLES,
					"Предпочтительный размер чашки",
					"Влияет на размер груди случайно сгенерированных NPC (размер не опустится ниже AA).",
					(Main.getProperties().breastSizePreference>=0?"+":"")+Main.getProperties().breastSizePreference,
					Main.getProperties().breastSizePreference, -20, 20,
					(Main.getProperties().udderSizePreference>=0?"+":"")+Main.getProperties().udderSizePreference,
					Main.getProperties().udderSizePreference, -20, 20));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"PENIS_SIZE_PREFERENCE",
					PresetColour.PENIS,
					"Предпочтения по размеру пениса",
					"Влияет на размер пениса случайно сгенерированных NPC (размер не опустится ниже "+Units.size(8)+").",
					(Main.getProperties().penisSizePreference>=0?"+":"")+Units.size(Main.getProperties().penisSizePreference, Units.ValueType.PRECISE, Units.UnitType.SHORT),
					Main.getProperties().penisSizePreference,
					-20,
					20));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"TRAP_PENIS_SIZE_PREFERENCE",
					PresetColour.BASE_PINK_LIGHT,
					Util.capitaliseSentence(Gender.N_P_TRAP.getName())+" размера пениса",
					"Размер пениса случайно сгенерированны "+Gender.N_P_TRAP.getName()+"х NPC. 100% означает исходный размер. Размер яичек и объем эякулята также будут изменяться пропорционально этой настройке.",
					(100+Main.getProperties().trapPenisSizePreference)+"%",
					Main.getProperties().trapPenisSizePreference,
					-90,
					100));
			
			UtilText.nodeContentSB.append(getSkinColourContentPreferenceVariableDiv(
					"SKIN_COLOUR_PREFERENCE",
					PresetColour.RACE_HUMAN,
					"Предпочтения по цвету кожи",
					"Влияет на вероятность появления человеческого цвета кожи у случайно сгенерированных NPC. Это не затрагивает «Высших» зверолюдей, так как у них нет человеческого кожного покрова."));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
}
