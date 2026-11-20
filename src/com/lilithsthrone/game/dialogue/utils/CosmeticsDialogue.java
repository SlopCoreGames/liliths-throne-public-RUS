package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Holds generic dialogue nodes associated with application of cosmetics, for use in external dialogue files.
 * This is effectively just a generic copy of SuccubisSecrets.java
 * 
 * @since 0.4.2.5
 * @version 0.4.2.5
 * @author Innoxia
 */
public class CosmeticsDialogue {

	public static InventorySlot invSlotTattooToRemove = null;
	
	private static NPC beautician;
	private static DialogueNode returnToNode;
	
	public static void initDialogue(NPC beautician, GameCharacter target, DialogueNode returnToNode) {
		CosmeticsDialogue.beautician = beautician;
		CosmeticsDialogue.returnToNode = returnToNode;
		BodyChanging.setTarget(target);
	}
	
	private static Response getMainResponse(int index) {
		if(index == 1){
			return new ResponseTrade("Торговать", UtilText.parse(beautician, "Спросить у [npc.name], какие косметические средства или украшения продает [npc.she]."), beautician);
			
		} else if (index == 2) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_COSMETICS) {
				return new Response("Макияж", "Вы уже осматриваете доступную косметику...", null);
				
			} else if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				return new Response("Макияж", UtilText.parse(beautician, "Поскольку ваше тело состоит из "+Main.game.getPlayer().getBodyMaterial().getName()+", [npc.name] не может нанести макияж!"), null);
				
			} else {
				return new Response("Макияж",
						UtilText.parse(beautician, "[npc.Name] может использовать множество различных оттенков и стилей помады, лака для ногтей и других видов макияжа."),
						BEAUTICIAN_COSMETICS);
			}
			
		} else if (index == 3) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_HAIR) {
				return new Response("Волосы", "Вы уже осматриваете доступные прически...", null);
				
			} else {
				return new Response("Волосы",
					UtilText.parse(beautician, "[npc.Name] может окрашивать, укладывать, стричь и даже удлинять ваши [pc.hair]."),
					BEAUTICIAN_HAIR);
			}

		} else if (index == 4) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_PIERCINGS) {
				return new Response("Пирсинг", "Вы уже осматриваете доступные виды пирсинга...", null);
				
			} else {
				return new Response("Пирсинг",
						UtilText.parse(beautician, "[npc.Name] может сделать проколы во многих частях вашего тела."),
						BEAUTICIAN_PIERCINGS);
			}

		}  else if (index == 5) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_EYES) {
				return new Response("Глаза", "Вы уже осматриваете доступные изменения глаз...", null);
				
			} else {
				return new Response("Глаза",
						UtilText.parse(beautician, "[npc.Name] может изменить цвет ваших глаз, хотя это довольно сильно истощает [npc.her] ауру, и поэтому стоит очень дорого."),
						BEAUTICIAN_EYES);
			}

		} else if (index == 6) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_SKIN_COLOUR) {
				return new Response("Покрытия", "Вы уже просматриваете доступные изменения для вашего облика...", null);
				
			} else {
				return new Response("Покрытия",
						UtilText.parse(beautician, "[npc.Name] способна менять цвет любых типов кожного покрова, таких как кожа, мех и перья. Это весьма требовательно к [npc.her] ауре, и поэтому стоит очень дорого."),
						BEAUTICIAN_SKIN_COLOUR){
					@Override
					public void effects() {
						SuccubisSecrets.initCoveringsMap(Main.game.getPlayer());
					}
				};
			}
			
		} else if (index == 7) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_OTHER) {
				return new Response("Разное", "Вы уже просматриваете доступные прочие услуги...", null);
				
			} else {
				return new Response("Разное", UtilText.parse(beautician, "[npc.Name] предлагает и другие прочие услуги, такие как отбеливание ануса."), BEAUTICIAN_OTHER);
			}
			
		} else if (index == 8) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_TATTOOS) {
				return new Response("Татуировки", "Вы уже просматриваете доступные татуировки...", null);
				
			} else {
				return new Response("Татуировки",
						UtilText.parse(beautician, "[npc.Name] способна как наносить, так и удалять татуировки. Она даже может наносить татуировки с арканным зачарованием, но, похоже, они стоят очень дорого..."),
						BEAUTICIAN_TATTOOS);
			}

		} else if (index == 0) {
			return new Response("Назад", "На данный момент вы достаточно изменили свою внешность.", returnToNode) {
				@Override
				public void effects() {
					Main.game.setResponseTab(0);
				}
			};
			
		} else {
			return null;
		}
	}
	
	private static String getMoneyRemainingString() {
		return "<h6 style='text-align:center;'>Сейчас у вас есть [style.moneyFormat([pc.money], span)]</h6>";
	}

	public static final DialogueNode BEAUTICIAN_START = new DialogueNode("Косметика", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	public static final DialogueNode BEAUTICIAN_COSMETICS = new DialogueNode("Косметика", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые руж) используются для придания щекам цвета, чтобы придать лицу более юный вид и подчеркнуть скулы.", true, true)
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Помада", "Помада используется для придания губам цвета, текстуры и защиты.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Подводка наносится по контуру глаз, чтобы подчеркнуть их форму или выделить отдельные черты.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются для того, чтобы сделать глаза ярче или привлекательнее.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окрашивания и защиты ногтей на твоих [pc.hands].", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окрашивания и защиты ногтей на твоих [pc.feet].", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_HAIR = new DialogueNode("Волосы", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
				CharacterModificationUtils.getKatesDivHairLengths(true, "Длина волос", "Длина волос определяет доступные прически. Чем длиннее волосы, тем больше вариантов доступно.")

				+CharacterModificationUtils.getKatesDivHairStyles(true, "Прическа", "Доступность причесок зависит от длины твоих волос.")
				
				+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getHairType().getRace(), Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering()).getType(),
						"Цвет [pc.Hair]", "Все изменения цвета волос постоянны, так что если вы захотите сменить цвет снова, вам придется снова навестить Кейт.", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_SKIN_COLOUR = new DialogueNode("Покрытия", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = Main.game.getPlayer();
				
				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(target, bct, entry.getValue().getValue());
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
						true,
						true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_EYES = new DialogueNode("Глаза", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());

			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Main.game.getPlayer().getEyeType().getRace(), Main.game.getPlayer().getEyeCovering(),
							"Радужка", "Радужка — это цветная часть глаза, отвечающая за контроль диаметра и размера зрачка.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"Зрачки", "Зрачок — это отверстие в центре радужки, через которое свет попадает на сетчатку.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"Склеры", "Склера — это (обычно белая) часть глаза, окружающая радужку.", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_PIERCINGS = new DialogueNode("Пирсинг", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPiercings(false));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_OTHER = new DialogueNode("Разное", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivAnalBleaching()
					
					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair(true, "Растительность на лице", "Волосы на лице." 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : " Женские персонажи не могут отращивать растительность на лице."))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair(true, "Лобковые волосы", "Волосы в области гениталий; расположены на половых органах и вокруг паха.")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair(true, "Волосы под мышками", "Волосы на ваших подмышках.")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair(true, "Волосы на заднице", "Волосы вокруг вашего ануса.")
							:"")
					);
			
			for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, bct, "Волосы на теле", "Волосы на теле.", true, true));
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_TATTOOS = new DialogueNode("Татуировки", "-", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoos());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("Подтверждения: ",
						"Включить подтверждение удаления татуировок. Если включено, для удаления татуировки потребуется два клика. Если выключено — всего один.",
							BEAUTICIAN_TATTOOS) {
					@Override
					public String getTitle() {
						return "Подтверждения: "+(Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)
									?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ВКЛ</span>"
									:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>ВЫКЛ</span>");
					}
					
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.tattooRemovalConfirmations, !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations));
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_TATTOOS_ADD = new DialogueNode("Татуировки", "-", true) {
		@Override
		public String getLabel() {
			return "Татуировки: "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName());
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoosAdd());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("Применить ("+UtilText.formatAsMoneyUncoloured(value, "охват")+")", "У вас недостаточно денег, чтобы сделать татуировку!", null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Применить ("+UtilText.formatAsMoneyUncoloured(value, "охват")+")", "Чтобы сделать татуировку, нужно выбрать тип тату, добавить надпись или счетчик!", null);
					
				} else {
					return new Response("Применить ("+UtilText.formatAsMoney(value, "охват")+")", UtilText.parse(beautician, "Скажите [npc.name], что вы хотите, чтобы [npc.herHim] нанесла вам эту татуировку."), BEAUTICIAN_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							Main.game.getPlayer().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("Сохранить/Загрузить", "Сохранять/загружать пресеты татуировок.", TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						initTattooSaveLoadDialogue(BEAUTICIAN_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("Назад", "Решить не делать эту татуировку и вернуться к основному экрану выбора.", BEAUTICIAN_TATTOOS);
			}
			
			return null;
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	// Save/Load screen for tattoos:

	private static DialogueNode returnToNodeFromTattooSaveLoad;
	private static Map<String, Tattoo> loadedTattoosMap;
	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";

	public static void initTattooSaveLoadDialogue(DialogueNode returnToNodeFromTattooSaveLoad) {
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
		CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
		CosmeticsDialogue.returnToNodeFromTattooSaveLoad = returnToNodeFromTattooSaveLoad;
	}
	
	public static DialogueNode getReturnToNodeFromTattooSaveLoad() {
		return returnToNodeFromTattooSaveLoad;
	}

	public static void initSaveLoadMenu() {
		loadedTattoosMap = new TreeMap<>();
		
		for(File f : getSavedTattoos()) {
			try {
				String name = Util.getFileIdentifier(f);
				Tattoo loadedTattoo = loadTattoo(name);
				loadedTattoosMap.put(name, loadedTattoo);
			} catch(Exception ex) {
			}
		}
	}
	
	public static Map<String, Tattoo> getLoadedTattoosMap() {
		return loadedTattoosMap;
	}

	public static final DialogueNode TATTOO_SAVE_LOAD = new DialogueNode("Сохранить файлы татуировок", "", true) {
		@Override
		public void applyPreParsingEffects() {
			initSaveLoadMenu();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0 0 8px 0;'>Для имен файлов сохранений подходят только стандартные символы (буквы и цифры).<br/>Наведите курсор на иконку каждой татуировки, чтобы увидеть подробную информацию о том, что будет сохранено или загружено."
							+(!Main.game.isInNewWorld()
								?"<br/>Если имя выделено [style.colourBad(красным)], значит, вы не можете загрузить эту татуировку из-за несовместимости с выбранной областью тела или из-за того, что татуировка обладает спецэффектами, которые вам пока недоступны!"
								:"<br/>Если имя выделено [style.colourBad(красным)], значит, вы не можете загрузить эту татуировку из-за несовместимости с выбранной областью тела.")
					+ "</div><div class='container-full-width' style='padding:0; margin:0;'><div class='container-full-width' style='width:calc(75% - 16px); text-align:center; background:transparent;'>Название</div><div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>Сохранить | Загрузить | Удалить</div></div>");

			int i=0;
			
			saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
			i++;
			
			for(Entry<String, Tattoo> entry : loadedTattoosMap.entrySet()){
				saveLoadSB.append(getSaveLoadRow(entry.getKey(), entry.getValue(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Подтверждения: ",
						"Включить подтверждение действий при нажатии на кнопки загрузки, перезаписи или удаления сохраненной татуировки. Если включено, для выполнения действия потребуется два клика. Если выключено — только один.",
						TATTOO_SAVE_LOAD) {
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

			} else if (index == 0) {
				return new Response("Назад", "Назад к меню татуировок.", returnToNodeFromTattooSaveLoad);
			}
			
			return null;
		}
	};
	
	public static List<File> getSavedTattoos() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/tattoos");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getSaveLoadRow(String baseName, Tattoo loadedTattoo, boolean altColour) {
		if(loadedTattoo!=null){
			String fileName = (baseName+".xml");
			
			boolean suitableSlot = loadedTattoo.getType().getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot);
			boolean specialEffectsLimitation = !Main.game.isInNewWorld()
					&& (loadedTattoo.getCounter()!=null || loadedTattoo.isGlowing() || (loadedTattoo.getWriting()!=null && loadedTattoo.getWriting().isGlow()));
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+" position:relative;'><div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'><div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'><div class='inventoryImage' style='width:100%;'><div class='inventoryImage-content'>"
										+ loadedTattoo.getSVGString()
									+ "</div><div class='overlay no-pointer' id='LOADED_TATTOO_" + baseName + "'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+ "<h6 style='margin:0; padding:2px;'>"+(!suitableSlot || specialEffectsLimitation?"[style.boldBad("+loadedTattoo.getName()+")]":loadedTattoo.getName())+"</h6><p style='margin:0; padding:2px;'>[style.colourDisabled(data/tattoos/)]"+baseName+"[style.colourDisabled(.xml)]</p></div></div><div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.game.isStarted() && !Main.game.isInCombat() && !Main.game.isInSex()
									?(fileName.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (suitableSlot && !specialEffectsLimitation
									? (fileName.equals(loadConfirmationName)
										?"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadDisabled()+"</div></div>")
	
	
							+ (fileName.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else { //TODO hmmmmmmm (also enchantment _CURRENT)
			String svgString = CharacterModificationUtils.tattoo.getSVGString();
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
					
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ svgString
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_TATTOO_CURRENT'></div>"//TODO
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' placeholder='Enter File Name' style='padding:0;margin:0;width:100%;'></form>"
							+"</div>"
							
						+ "</div>"
					
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
						+ "</div>"
					+ "</div>";
				
		}
	}

	public static void saveTattoo(String name, boolean allowOverwrite, DialogueNode dialogueNode) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/tattoos");
		dir.mkdir();

		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(name+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Такое имя уже существует!");
							return;
						}
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();
			
			Element tattooCoreElement = doc.createElement("tattooSave");
			
			doc.appendChild(tattooCoreElement);
			
			Element tattooElement = CharacterModificationUtils.tattoo.saveAsXML(tattooCoreElement, doc);
			
			// Do not save item effects:
			if(tattooElement.getElementsByTagName("effects").item(0)!=null) {
				tattooElement.removeChild(tattooElement.getElementsByTagName("effects").item(0));
			}
			
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/tattoos/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		if(dialogueNode!=null) {
			Main.game.setContent(new Response("", "", dialogueNode));
		}
		Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Татуировка сохранена!");
	}

	public static Tattoo loadTattoo(String name) {
		if (isLoadTattooAvailable(name)) {
			File file = new File("data/tattoos/"+name+".xml");

			if (file.exists()) {
				try {
					Document doc = Main.getDocBuilder().parse(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					Element rootElement = (Element) doc.getElementsByTagName("tattooSave").item(0);
					
					return Tattoo.loadFromXML((Element) rootElement.getElementsByTagName("tattoo").item(0), doc);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadTattooAvailable(String name) {
		File file = new File("data/tattoos/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static void deleteTattoo(String name) {
		File file = new File("data/tattoos/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Файл не найден...");
		}
	}
	
}
