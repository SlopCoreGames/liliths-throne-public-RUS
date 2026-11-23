package com.lilithsthrone.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantSortingMethod;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class OccupantController {
	public static void initRoomManagerListeners() {
		for (Cell c : OccupantManagementDialogue.getImportantCells()) {
			if (MainController.document.getElementById(c.getId()+"_PRESENT_DISABLED") != null) {
				MainController.addTooltipListeners(c.getId()+"_PRESENT_DISABLED", new TooltipInformationEventListener().setInformation("Управление комнатой", "Вы не можете управлять этой комнатой!"));
			} else if (MainController.document.getElementById(c.getId()+"_DISABLED") != null) {
				MainController.addTooltipListeners(c.getId()+"_DISABLED", new TooltipInformationEventListener().setInformation("Управление комнатой", "Вы не можете управлять этой комнатой!"));
			} else if (MainController.document.getElementById(c.getId()+"_PRESENT") != null) {
				MainController.addEventListener(MainController.document, c.getId()+"_PRESENT", "click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = c;
						}
						
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.ROOM_UPGRADES;
						}
					});
				}, false);
				MainController.addTooltipListeners(c.getId()+"_PRESENT", new TooltipInformationEventListener().setInformation("Управление комнатой", "Открыть экран управления этой комнатой."));
			} else if (MainController.document.getElementById(c.getId()) != null) {
				MainController.addEventListener(MainController.document, c.getId(), "click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = c;
						}
						
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.ROOM_UPGRADES;
						}
					});
				}, false);
				MainController.addTooltipListeners(c.getId(), new TooltipInformationEventListener().setInformation("Управление комнатой", "Открыть экран управления этой комнатой."));
			}
		}
	}
	
	public static void initRoomUpgradesListeners() {
		String id;
		for (AbstractPlaceUpgrade placeUpgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
			id = "ROOM_MOD_INFO_"+PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade);
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("", (OccupantManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
								?placeUpgrade.getDescriptionAfterPurchase()
								:placeUpgrade.getDescriptionForPurchase())));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					Main.game.setContent(
							new Response("",
									"",
									placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect) == null
											?Main.game.getCurrentDialogueNode()
											:placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect)) {
								@Override
								public void effects() {
									OccupantManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
								}
							});
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Купить модификацию",
								"Стоимость: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Купить модификацию",
								"Стоимость: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect.removePlaceUpgrade(placeUpgrade);
							Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
						}
					});
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Убрать модификацию",
								"Стоимость: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Убрать модификацию",
								(!placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getKey()
										?placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getValue()
										:"Стоимость: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade))));
			}
		}
		
		id = "rename_room_button";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					if (!unsuitableName) {
						Main.game.setContent(new Response("Переименовать комнату", "Переименуйте эту комнату, введя новое название в текстовое поле.", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								OccupantManagementDialogue.cellToInspect.getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
							}
						});
					}
				}
			}, false);
		}
	}
	
	static void fluidHandler(MilkingRoom room, FluidStored fluid) {
		String idModifier = "MILK";
		if (fluid.isCum()) {
			idModifier = "CUM";
		} else if (fluid.isGirlCum()) {
			idModifier = "GIRLCUM";
		}
		GameCharacter fluidOwner;
		try {
			fluidOwner = fluid.getFluidCharacter();
		} catch (Exception e1) {
			fluidOwner = null;
		}
		
		String fluidName = fluid.getFluid().getName(fluidOwner);
		
		Map<CoverableArea, SexAreaOrifice> areas = Util.newHashMapOfValues(
				new Util.Value<>(CoverableArea.MOUTH, SexAreaOrifice.MOUTH),
				new Util.Value<>(CoverableArea.VAGINA, SexAreaOrifice.VAGINA),
				new Util.Value<>(CoverableArea.ANUS, SexAreaOrifice.ANUS));
		
		for (Entry<CoverableArea, SexAreaOrifice> area : areas.entrySet()) {
			String id = idModifier+"_"+area.getKey()+"_"+fluid.hashCode();
			if (MainController.document.getElementById(id) != null) {
				float milkAmount = Math.min(fluid.getMillilitres(), MilkingRoom.INGESTION_AMOUNT);
				boolean canIngest = room.isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount);
				
				String fluidOwnerName = fluidOwner == null
						?""
						:(fluidOwner.equals(MilkingRoom.getTargetedCharacter())
						?UtilText.parse(fluidOwner, "собственный [npc.her]")
						:UtilText.parse(fluidOwner, "[npc.namePos]"));
				
				if (canIngest) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.getTextEndStringBuilder().append("<p>");
						if (MilkingRoom.getTargetedCharacter().isPlayer()) {
							switch (area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Схватив один из свободных шлангов, подсоединенных к чану с "+fluidOwnerName+" "+fluidName+", вы быстро снимаете всасывающее устройство с конца, а затем навинчиваете воронку. Направив конец шланга к своей [pc.ass+], вы без промедления вставляете воронку в свою [pc.asshole+]. Переключив рычаг на боку чана из режима «всасывание» в режим «нагнетание», вы нажимаете кнопку пуска и издаете восторженный [pc.moan], чувствуя, как "+fluidName+" нагнетается в вашу [pc.asshole]."));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Схватив один из свободных шлангов, подсоединенных к чану с "+fluidOwnerName+" "+fluidName+", ты быстро снимаешь всасывающее устройство с наконечника и навинчиваешь одну из трубок-насадок. Поднеся трубку ко рту, ты не теряя времени просовываешь её между своих [pc.lips+]. Переключая тумблер на боку чана с режима «всасывание» на «насос», ты нажимаешь кнопку пуска, издавая восторженный [pc.moan], пока жадно глотаешь "+fluidName+"."));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Схватив один из свободных шлангов, подсоединенных к чану с "+fluidOwnerName+" "+fluidName+", ты быстро снимаешь всасывающее устройство с наконечника и навинчиваешь одну из воронкообразных насадок. Направляя конец трубки #IF(pc.hasLegs())между своих [pc.legs+]#ELSEк своему паху#ENDIF, ты не теряя времени просовываешь воронку в свои [pc.pussy+]. Переключая тумблер на боку чана с режима «всасывание» на «насос», ты нажимаешь кнопку пуска, издавая восторженный [pc.moan], когда чувствуешь, как "+fluidName+" в тебя закачивают"
													+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?" прямо в твои [pc.pussy]."
													:" прямо в твою ждущую матку.")));
									break;
								default:
									break;
							}
						} else {
							switch (area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Желая наполнить [npc.namePos] [npc.ass+] "+fluidOwnerName+" "+fluidName+", ты приказываешь [npc.herHim]"
													+(!MilkingRoom.getTargetedCharacter().isTaur() && MilkingRoom.getTargetedCharacter().getGenitalArrangement() == GenitalArrangement.NORMAL
													?" наклониться перед тобой."
													:" опуститься на колени и подставить [npc.herself] тебе.")
													+" Как только [npc.her] [npc.asshole+] предстает перед вами во всей красе, вы хватаете один из свободных шлангов, подсоединенных к выбранному вами резервуару с жидкостью, быстро снимаете всасывающее устройство на конце и навинчиваете воронку-насадку.</p><p>Подводя конец шланга к [npc.namePos] [npc.ass+], вы не теряя времени впихиваете воронку в [npc.her] [npc.asshole+], довольно улыбаясь, когда [npc.she] издает [npc.a_moan+]. Переключив тумблер на боку резервуара из режима «всасывание» в режим «насос», вы нажимаете кнопку пуска, заставляя [npc.name] издать еще более восторженные [npc.moans], когда [npc.she] чувствует... "+fluidName+" втекающее в [npc.her] [npc.asshole]."));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Желая дать [npc.namePos] попробовать "+fluidOwnerName+" "+fluidName+", вы приказываете [npc.herHim] опуститься перед вами на колени. Как только [npc.she] подчиняется, вы хватаете одну из свободных трубок, подсоединенных к выбранному вами резервуару с жидкостью, быстро снимаете наконечник-присоску и навинчиваете одну из соломинок-насадок.</p>Поднеся соломинку к рту [npc.namePos], вы не теряя времени просовываете ее между [npc.her] [npc.lips+] и велите [npc.herHim] подготовить [npc.herself] к вкусной трапезе. Переключая тумблер на боку резервуара с режима «всасывание» на «нагнетание», вы нажимаете кнопку пуска, извлекая из [pc.moan] восторженный звук, пока наблюдаете, как [npc.name] жадно глотает "+fluidName+"."));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Желая наполнить [npc.namePos] [npc.pussy+] до краев "+fluidOwnerName+" "+fluidName+", ты приказываешь [npc.herHim]"
													+(!MilkingRoom.getTargetedCharacter().isTaur()
													&& MilkingRoom.getTargetedCharacter().hasLegs()
													&& MilkingRoom.getTargetedCharacter().getGenitalArrangement() == GenitalArrangement.NORMAL
													?" садится на ближайший стул и широко разводит [npc.her] [npc.legs]."
													:" опуститься на колени и подставить [npc.herself] тебе.")
													+" Как только [npc.she] подчиняется, и [npc.her] [npc.pussy+] оказывается полностью на виду, ты хватаешь одну из свободных трубок, подключенных к выбранному тобой чану с жидкостью, быстро снимаешь всасывающее устройство на конце и навинчиваешь одну из воронкообразных насадок.</p><p>Подводя конец трубки к [npc.namePos] [npc.labia+], ты не теряя времени просовываешь воронку в [npc.her] [npc.pussy+], довольно улыбаясь про себя, когда [npc.she] издает [npc.a_moan+]. Переключив рычаг на боку чана из режима «всасывание» в режим «насос», ты нажимаешь кнопку пуска, заставляя [npc.name] издавать еще больше восторженных [npc.moans], пока [npc.she] чувствует "+fluidName+" в тебя закачивают"
													+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?" в [npc.her] [npc.pussy]."
													:" прямо в ожидающее чрево [npc.her].")));
									break;
								default:
									break;
							}
						}
						
						String ingestion;
//						try {
//							GameCharacter c = fluid.getFluidCharacter();
//							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(c, fluid.getBody(), fluid.getFluid(), area.getValue(), milkAmount);
//						} catch (Exception e1) {
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(fluid, area.getValue(), milkAmount);
//						}
						if (!ingestion.isEmpty()) {
							Main.game.getTextEndStringBuilder().append("</p>"
									+"<p>"
									+ingestion);
						}
						Main.game.getTextEndStringBuilder().append("</p>");
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
										+"<i style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>"+Units.fluid(milkAmount)+" из "+fluidOwnerName+" "+fluidName+" было поглощено!</i></p>");
						
						room.incrementFluidStored(fluid, -milkAmount);
						
						Main.game.setContent(new Response("", "", LilayaMilkingRoomDialogue.INGEST));
						
					}, false);
				}
				String verb = "Выпить";
				String description;
				
				if (MilkingRoom.getTargetedCharacter().isPlayer()) {
					description = "Выпить "+Units.fluid(milkAmount)+" из "+fluidName+".";
					if (area.getKey() != CoverableArea.MOUTH) {
						verb = "Насос";
						description = "Насос "+Units.fluid(milkAmount)+" из "+fluidName+" в твою "+area.getKey().getName()+".";
					}
					
				} else {
					description = UtilText.parse(MilkingRoom.getTargetedCharacter(), "Заставить [npc.name] выпить ")+Units.fluid(milkAmount)+" из "+fluidName+".";
					if (area.getKey() != CoverableArea.MOUTH) {
						verb = "Насос";
						description = "Насос "+Units.fluid(milkAmount)+" из "+fluidName+" into "+UtilText.parse(MilkingRoom.getTargetedCharacter(), "[npc.namePos] ")+area.getKey().getName()+".";
					}
				}
				
				TooltipInformationEventListener el;
				if (canIngest) {
					el = new TooltipInformationEventListener().setInformation(verb+" ("+Units.fluid(milkAmount)+")",
							description);
				} else {
					el = new TooltipInformationEventListener().setInformation(verb+" ("+Units.fluid(milkAmount)+")",
							room.getAreaIngestionBlockedDescription(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount));
				}
				MainController.addTooltipListeners(id, el);
			}
		}
		
		String id = idModifier+"_SELL_"+fluid.hashCode();
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				int income = Math.max(1, (int) (fluid.getFluid().getValuePerMl()*fluid.getMillilitres()));
				Main.game.getPlayer().incrementMoney(income);
				room.getFluidsStored().remove(fluid);
				Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>Вы продали "+fluidName+" на "+(UtilText.formatAsMoney(income, "охват"))+"!</p>");
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id,
					new TooltipInformationEventListener().setInformation("Продать", "Продать все "+fluidName+" for "+(Math.max(1, (int) (fluid.getFluid().getValuePerMl()*fluid.getMillilitres())))+" огни."));
		}
	}
	
	public static void initOverviewListeners() {
		for (int i = 6; i>=0; i--) {
			String id = "SLAVE_DAY_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i; // Lambda requirement
				MainController.addEventListener(MainController.document, id, "click", e->{
					OccupantManagementDialogue.setDayNumber(Main.game.getDayNumber()-finalI);
					Main.game.setContent(new Response("Переименовать", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSlaveJobListeners() {
		String id;
		
		// Copy & paste settings:
		// Schedule:
		id = "copySlaveJobSchedule";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				CompanionManagement.copyJobSchedule();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Копировать график работ",
					UtilText.parse(CompanionManagement.characterSelected(),
							"Копировать график работ [npc.namePos], что позволит вам вставить точно такой же график другим рабыням в их собственных окнах управления работами.")));
		}
		id = "pasteSlaveJobSchedule";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				if(CompanionManagement.isJobSchedulePasteAvailable()) {
					boolean fullyPasted = CompanionManagement.pasteJobSchedule();
					if(fullyPasted) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					} else {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), PresetColour.GENERIC_BAD, "Некоторые задачи не удалось вставить!");
					}
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Вставить график работ",
					UtilText.parse(CompanionManagement.characterSelected(),
							CompanionManagement.isJobSchedulePasteAvailable()
								?"Вставить текущий скопированный график работ, благодаря чему у [npc.name] будет точно такой же график, как у того, который вы скопировали."
								:"Вы еще не копировали график работ, поэтому не можете вставить его, чтобы заменить график [npc.namePos]...")));
		}
		// Job settings:
		id = "copySlaveJobSettings";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				CompanionManagement.copyJobSettings();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Копировать настройки работы",
					UtilText.parse(CompanionManagement.characterSelected(),
							"Копирует настройки работы [npc.namePos], что позволит вам вставить точно такие же настройки другим рабыням в их собственных окнах управления работой.")));
		}
		id = "pasteSlaveJobSettings";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				if(CompanionManagement.isJobSettingsPasteAvailable()) {
					CompanionManagement.pasteJobSettings();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Вставить настройки работы",
					UtilText.parse(CompanionManagement.characterSelected(),
							CompanionManagement.isJobSettingsPasteAvailable()
								?"Вставляет скопированные настройки работы, благодаря чему у [npc.name] будут точно такие же настройки, как у тех, кого вы скопировали."
								:"Вы еще не копировали набор настроек работы, поэтому не можете вставить их, чтобы заменить настройки [npc.namePos]...")));
		}
		
		// Job hours:
		for (int i = 0; i<24; i++) {
			id = i+"_WORK";
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				MainController.addEventListener(MainController.document, id, "click", e->{
					SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
					if (Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(finalI) == job) {
						Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(finalI, SlaveJob.IDLE);
					} else {
						Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(finalI, job);
					}
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
			} else {
				id = i+"_WORK_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"[style.colourBad(Недоступный временной слот)]",
							Main.game.getDialogueFlags().getSlaveryManagerJobSelected().getAvailabilityText(i, Main.game.getDialogueFlags().getManagementCompanion())));
				}
			}
		}
		for (SlaveJobHours preset : SlaveJobHours.values()) {
			id = preset+"_TIME";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					if (preset == SlaveJobHours.NONE) {
						for (int hour = 0; hour<24; hour++) {
							SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
							if (Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(hour) == job) {
								Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(hour, SlaveJob.IDLE);
							}
						}
					} else {
						for (int hour = preset.getStartHour(); hour<preset.getStartHour()+preset.getLength(); hour++) {
							int appliedHour = hour%24;
							SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
							if (job.isAvailable(appliedHour, Main.game.getDialogueFlags().getManagementCompanion())) {
								Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(appliedHour, job);
							}
						}
					}
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Установить предустановленные рабочие часы", preset.getDescription()));
			}
			
			id = preset+"_TIME_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"[style.colourBad(Установить предустановленные рабочие часы)]",
						"Вы не можете назначить эти часы. Либо работа недоступна в это время, либо это слишком большая нагрузка."));
			}
		}
		
		// Jobs:
		for (SlaveJob job : SlaveJob.values()) {
			id = "SLAVE_JOB_INFO_"+job;
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion()));
			}
			
			id = job+"_ASSIGN";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					Main.game.getDialogueFlags().setSlaveryManagerJobSelected(job);
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion()));
			}
			
			for (SlaveJobSetting setting : job.getMutualSettings()) {
				id = job+setting.toString()+"_ADD";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> <b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
							setting.getDescription()
									+" [style.italicsMinorGood(Нажмите, чтобы применить это разрешение.)]"));
				}
				
				id = job.toString()+setting+"_REMOVE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> <b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
							setting.getDescription()
									+" [style.italicsMinorBad(Нажмите, чтобы отозвать это разрешение.)]"));
				}
			}
			
			for (Map.Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
				for (SlaveJobSetting setting : entry.getValue()) {
					id = setting+"_TOGGLE_ADD";
					if (MainController.document.getElementById(id) != null) {
						MainController.addEventListener(MainController.document, id, "click", e->{
							for (SlaveJobSetting settingRem : entry.getValue()) {
								Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, settingRem);
							}
							Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
								"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> <b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
								setting.getDescription()
										+" [style.italicsMinorGood(Нажмите, чтобы применить это разрешение.)]"));
					}
					
					id = setting+"_DISABLED";
					if (MainController.document.getElementById(id) != null) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
								"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> <b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
								setting.getDescription()
										+" [style.italicsMinorBad(В этой категории нельзя отзывать разрешения. Выберите другую.)]"));
					}
				}
			}
		}
	}
	
	public static void initSlavePermissionsListeners() {
		// Copy & paste settings:
		// Permissions:
		String id = "copyPermissions";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				CompanionManagement.copyPermissions();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Копировать разрешения",
					UtilText.parse(CompanionManagement.characterSelected(),
							"Копирует разрешения [npc.namePos], после чего вы сможете вставить точно такие же разрешения другим рабыням в их собственных окнах управления разрешениями.")));
		}
		id = "pastePermissions";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				if(CompanionManagement.isPermissionsPasteAvailable()) {
					CompanionManagement.pastePermissions();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Вставить разрешения",
					UtilText.parse(CompanionManagement.characterSelected(),
							CompanionManagement.isPermissionsPasteAvailable()
								?"Вставить скопированные права доступа, благодаря чему [npc.name] получит точно такие же права, какие вы скопировали."
								:"Вы еще не копировали набор прав доступа, поэтому не можете вставить их, чтобы заменить [npc.namePos]...")));
		}
		
		// Permissions:
		for (SlavePermission permission : SlavePermission.values()) {
			for (SlavePermissionSetting setting : permission.getSettings()) {
				id = setting+"_ADD";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().addSlavePermissionSetting(permission, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorGood(Нажмите, чтобы применить это разрешение.)]"
									+(permission.isMutuallyExclusiveSettings()
									?" [style.italicsMinorBad(В этой категории может быть активным только одно право одновременно.)]"
									:""),
							91 + (setting.getAdditionalDescriptionLines()*16)));
				}
				
				id = setting+"_REMOVE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().removeSlavePermissionSetting(permission, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorBad(Нажмите, чтобы отозвать это разрешение.)]",
							91 + (setting.getAdditionalDescriptionLines()*16)));
				}
				
				id = setting+"_REMOVE_ME";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorBad(В этой категории нельзя отзывать разрешения. Выберите другую.)]",
							91 + (setting.getAdditionalDescriptionLines()*16)));
				}
			}
		}
	}
	
	public static void initOccupantListListeners() {
		String id;
		// Sorting
		for (OccupantSortingMethod osm : OccupantSortingMethod.values()) {
			id = "SORT_SLAVES_BY_"+osm;
			if (MainController.document.getElementById(id) != null) {
				String friendlyName = Util.capitaliseSentence(osm.getName());
				MainController.addEventListener(MainController.document, id, "click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.setSlavesSortedBy(osm);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						osm==OccupantSortingMethod.NONE
							?"Без сортировки"
							:"Сортировать по "+friendlyName,
						osm.getSortingDescription(),
						18*2));
			}
		}
		
		// Sort ascending
		id = "SORT_SLAVES_ASC";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						OccupantManagementDialogue.setSlavesAreInReverseOrder(false);
					}
				});
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"По возрастанию",
					""));
		}
		
		// Sort Descending
		id = "SORT_SLAVES_DESC";
		if (MainController.document.getElementById(id) != null) {
			MainController.addEventListener(MainController.document, id, "click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						OccupantManagementDialogue.setSlavesAreInReverseOrder(true);
					}
				});
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"По убыванию",
					""));
		}
		
		for (String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			id = slaveId;
			NPC slave;
			
			try {
				slave = (NPC) Main.game.getNPCById(slaveId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, getSlavesOwned", slaveId);
				continue;
			}
			
			if (slave != null) { // slave shouldn't be null...
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Осмотреть рабыню",
							UtilText.parse(slave, "Осмотреть [npc.name]."),
							18));
				}
				
				id = slaveId+"_JOB";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(slave)) {
							@Override
							public boolean isIgnoreContentScroll() {
								return true;
							}
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление работой рабыни",
							UtilText.parse(slave, "Установить работу и часы работы [npc.namePos]."),
							18));
				}
				
				id = slaveId+"_PERMISSIONS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(slave)) {
							@Override
							public boolean isIgnoreContentScroll() {
								return true;
							}
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление правами рабыни",
							UtilText.parse(slave, "Управление правами [npc.namePos]."),
							18));
				}
				
				id = slaveId+"_INVENTORY";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
						Main.mainController.openInventory(slave, InventoryInteraction.FULL_MANAGEMENT);
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление инвентарем рабыни",
							UtilText.parse(slave, "Управление инвентарем [npc.namePos]."),
							18));
				}
				
				id = slaveId+"_TRANSFER";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								slave.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
								if(!slave.isAtWork()
										|| slave.getLocationPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)
										|| slave.getWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
									slave.returnToHome();
								}
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить рабыню сюда",
							UtilText.parse(slave, "Переместить [npc.name] в ваше текущее местоположение."),
							18));
				}
				
				id = slaveId+"_TRANSFER_DISABLED_FULL";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить рабыню сюда",
							UtilText.parse(slave, "Вы не можете переместить [npc.name] в это место, так как здесь нет места для [npc.herHim]."),
							18*2));
				}
				id = slaveId+"_TRANSFER_DISABLED_INAPPPROPRIATE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить рабыню сюда",
							UtilText.parse(slave,
									"Вы не можете переместить [npc.name] в это место, так как это неподходящая комната для [npc.herHim]..."
									+(slave.isDoll()
										?"<br/><i>Куклы могут использовать в качестве своего домашнего тайла только шкафы для кукол...</i>"
										:"<br/><i>Рабы могут использовать в качестве своего домашнего тайла только рабские комнаты...</i>")
									),
							18*3));
				}
				id = slaveId+"_TRANSFER_DISABLED_ALREADY_HERE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить рабыню сюда",
							UtilText.parse(slave, "Вы не можете переместить [npc.name] в это место, так как [npc.sheIs] уже считает эту комнату своим [npc.her] домом!"),
							18*2));
				}
				
				id = slaveId+"_SELL";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						if (slave.isAbleToBeSold()) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney((long) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
									Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
									slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
								}
							});
						}
					}, false);
					
					if(slave.isAbleToBeSold()) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Продать раба",
								UtilText.parse(slave,
										"Стоимость [npc.Name] составляет "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)
											+"<br/>Однако, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" купит [npc.herHim] за "
											+UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", PresetColour.GENERIC_ARCANE)+".")));
					} else {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Продать раба",
								UtilText.parse(slave,"[npc.Name] нельзя продать!"),
								18));
					}
				}
				
				id = slaveId+"_SELL_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Продать раба",
							UtilText.parse(slave,
									slave.isAbleToBeSold()
											?"Вы не можете продать [npc.name], так как здесь некому продать [npc.herHim]."
											:"[npc.Name] нельзя продать!"),
							slave.isAbleToBeSold()
								?18*2
								:18));
				}
				
				id = slaveId+"_COSMETICS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								BodyChanging.setTarget(slave);
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Отправить Кейт",
							UtilText.parse(slave, "Отправить [npc.name] в салон красоты Кейт «Секреты суккуба», чтобы изменить внешность [npc.her]."),
							18*2));
				}
				
				id = slaveId+"_COSMETICS_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Отправить рабыню к Кейт",
							UtilText.parse(slave, "Вы еще не встречали Кейт!"),
							18));
				}
			}
		}
		
		for (String occupantId : Main.game.getPlayer().getFriendlyOccupants()) {
			id = occupantId;
			NPC occupant;
			
			try {
				occupant = (NPC) Main.game.getNPCById(occupantId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, getFriendlyOccupants", occupantId);
				continue;
			}
			
			if (occupant != null) { // It shouldn't equal null...
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(occupant)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Осмотреть персонажа",
							UtilText.parse(occupant, "Осмотреть [npc.name]."),
							18));
				}
				
				id = occupantId+"_JOB";
				if (MainController.document.getElementById(id) != null) {
					if(occupant.hasJob()) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление работой",
								UtilText.parse(occupant, "У [npc.name] уже есть постоянная работа, поэтому его нельзя назначить на работу в особняке..."),
								18*2));
						
					} else {
						MainController.addEventListener(MainController.document, id, "click", e -> {
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(occupant)) {
								@Override
								public void effects() {
									CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
									Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								}
							});
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление работой",
								UtilText.parse(occupant, "Дать [npc.name] какую-нибудь временную работу."),
								18));
					}
				}
				
				id = occupantId+"_PERMISSIONS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление правами",
							"Вы не можете управлять правами свободного обитателя.",
							18*2));
				}
				
				id = occupantId+"_INVENTORY";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
//						Main.game.getDialogueFlags().setManagementCompanion(occupant);
						CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
						Main.mainController.openInventory(occupant, InventoryInteraction.FULL_MANAGEMENT);
						
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление инвентарем",
							UtilText.parse(occupant, "Управление инвентарем [npc.namePos]."),
							18));
				}
				
				id = occupantId+"_TRANSFER";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								occupant.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
								occupant.returnToHome();
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить сюда",
							UtilText.parse(occupant, "Переместить [npc.name] в ваше текущее местоположение."),
							18));
				}
				
				id = occupantId+"_TRANSFER_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить сюда",
							UtilText.parse(occupant, "Вы не можете переместить [npc.name] в это место, так как здесь нет места для [npc.herHim]."),
							18*2));
				}
				
				id = occupantId+"_COSMETICS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(occupant)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								BodyChanging.setTarget(occupant);
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Отправить Кейт",
							UtilText.parse(occupant, "Отправить [npc.name] в салон красоты Кейт «Секреты суккуба», чтобы изменить внешность [npc.her]."),
							18*2));
				}
				
				id = occupantId+"_COSMETICS_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Отправить Кейт",
							"Вы еще не встречали Кейт!",
							18));
				}
			}
		}
	}
	
	public static void initSlaveTraderListeners() {
		for (String slaveId : Main.game.getDialogueFlags().getSlaveTrader().getSlavesOwned()) {
			String id = slaveId+"_TRADER";
			NPC slave;
			try {
				slave = (NPC) Main.game.getNPCById(slaveId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, ТОРГОВЕЦ.", slaveId);
				continue;
			}
			
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
							Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Осмотреть рабыню",
						UtilText.parse(slave, "Внимательно осмотреть [npc.name].")));
			}
			
			id = slaveId+"_TRADER_JOB";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление работой рабыни",
						UtilText.parse(slave, "Вы не можете управлять работой [npc.namePos], так как [npc.herHim] вам не принадлежит!")));
			}
			
			id = slaveId+"_TRADER_PERMISSIONS";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление правами рабыни",
						UtilText.parse(slave, "Вы не можете управлять правами [npc.namePos], так как [npc.herHim] вам не принадлежит!")));
			}
			
			id = slaveId+"_TRADER_INVENTORY";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Управление инвентарем рабыни",
						UtilText.parse(slave, "Вы не можете управлять инвентарем [npc.namePos], так как не владеете [npc.herHim]!")));
			}
			
			id = slaveId+"_TRADER_TRANSFER";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Переместить рабыню сюда",
						UtilText.parse(slave, "Вы не можете переместить [npc.name] в это место, так как не владеете [npc.herHim], а также потому, что [npc.sheIs] уже здесь!")));
			}
			
			id = slaveId+"_BUY";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-(int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)));
							Main.game.getPlayer().addSlave(slave);
							slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Купить рабыню",
						UtilText.parse(slave, "Стоимость [npc.Name] составляет "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)+"<br/>Однако, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" продаст [npc.herHim] за "
								+UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)), "b", PresetColour.GENERIC_ARCANE)+".")));
			}
			
			id = slaveId+"_BUY_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Купить рабыню",
						UtilText.parse(slave, "Вы не можете купить [npc.name], так как у вас недостаточно денег!")));
			}
			
			id = slaveId+"_TRADER_COSMETICS";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Отправить рабыню к Кейт",
						UtilText.parse(slave, "Вы не можете отправить Кейт рабыню, которой не владеете!")));
			}
		}
	}
}
