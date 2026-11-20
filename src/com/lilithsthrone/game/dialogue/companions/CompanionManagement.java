package com.lilithsthrone.game.dialogue.companions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectTimer;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.1
 * @version 0.4.10.8
 * @author Innoxia
 */
public class CompanionManagement {

	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	private static DialogueNode coreNode;
	private static int defaultResponseTab;
	
	private static SlaveJob[] savedJobSchedule = null;
	private static Map<SlaveJob, Set<SlaveJobSetting>> savedJobSettings = null;
	private static Map<SlavePermission, Set<SlavePermissionSetting>> savedPermissions = null;
	
	// Saved job schedule:
	
	public static boolean isJobSchedulePasteAvailable() {
		return savedJobSchedule!=null;
	}
	
	public static void copyJobSchedule() {
		savedJobSchedule = new SlaveJob[24];
		for(int i=0; i<savedJobSchedule.length; i++) {
			savedJobSchedule[i] = characterSelected().getSlaveJob(i);
		}
	}

	/**
	 * @return true if all jobs were successfully pasted, false if some were unavailable.
	 */
	public static boolean pasteJobSchedule() {
		boolean fullSuccess = true;
		characterSelected().setSlaveJob24Hours(SlaveJob.IDLE);
		
		for(int i=0; i<savedJobSchedule.length; i++) {
			SlaveJob jobAtHour = savedJobSchedule[i];
			float stamina = characterSelected().getDailySlaveJobStamina();
			
			if(jobAtHour.isAvailable(i, characterSelected())
					&& !jobAtHour.isHidden(characterSelected())
					&& (characterSelected().isSlave()
							// If not a slave, don't work while wanting to sleep or if stamina is depleted
							|| (stamina-jobAtHour.getHourlyStaminaDrain(characterSelected())+characterSelected().getSlaveJob(i).getHourlyStaminaDrain(characterSelected())>=0f
								&& !characterSelected().isSleepingAtHour(i)
								&& jobAtHour.hasFlag(SlaveJobFlag.GUEST_CAN_WORK)))) {
				characterSelected().setSlaveJob(i, jobAtHour);
			} else {
				fullSuccess = false;
			}
		}
		
		return fullSuccess;
	}
	
	// Saved job settings:
	
	public static boolean isJobSettingsPasteAvailable() {
		return savedJobSettings!=null;
	}
	
	public static void copyJobSettings() {
		savedJobSettings = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			savedJobSettings.putIfAbsent(job, new HashSet<>());
			for(SlaveJobSetting jobSetting : characterSelected().getSlaveJobSettings(job)) {
				savedJobSettings.get(job).add(jobSetting);
			}
		}
	}
	
	public static void pasteJobSettings() {
		for(SlaveJob job : SlaveJob.values()) {
			characterSelected().clearSlaveJobSettings(job);
			for(SlaveJobSetting jobSetting : savedJobSettings.get(job)) {
				characterSelected().addSlaveJobSettings(job, jobSetting);
			}
		}
	}

	// Saved permissions:

	public static boolean isPermissionsPasteAvailable() {
		return savedPermissions!=null;
	}
	
	public static void copyPermissions() {
		savedPermissions = new HashMap<>();
		for(Entry<SlavePermission, Set<SlavePermissionSetting>> entry : characterSelected().getSlavePermissionSettings().entrySet()) {
			savedPermissions.put(entry.getKey(), new HashSet<>());
			for(SlavePermissionSetting setting : entry.getValue()) {
				savedPermissions.get(entry.getKey()).add(setting);
			}
		}
	}
	
	public static void pastePermissions() {
		characterSelected().replaceSlavePermissionSettings(savedPermissions);
	}
	
	public static DialogueNode getCoreNode() {
		return coreNode;
	}

	public static int getDefaultResponseTab() {
		return defaultResponseTab;
	}

	public static void initManagement(DialogueNode coreNode, int defaultResponseTab, NPC targetedCharacter) {
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
			Main.game.saveDialogueNode();
		}
		CompanionManagement.coreNode = coreNode;
		CompanionManagement.defaultResponseTab = defaultResponseTab;
		Main.game.getDialogueFlags().setManagementCompanion(targetedCharacter);
		
		// If job is not available for guests, change it to IDLE:
		if(targetedCharacter!=null
				&& !targetedCharacter.isSlave()
				&& !Main.game.getDialogueFlags().getSlaveryManagerJobSelected().hasFlag(SlaveJobFlag.GUEST_CAN_WORK)) {
			Main.game.getDialogueFlags().setSlaveryManagerJobSelected(SlaveJob.IDLE);
		}
	}

	public static DialogueNode getSlaveryManagementInspectSlaveDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_INSPECT;
	}
	
	public static DialogueNode getSlaveryManagementSlaveJobsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_JOBS;
	}
	
	public static DialogueNode getSlaveryManagementSlavePermissionsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_PERMISSIONS;
	}
	
	public static DialogueNode getSlaveryManagementSlaveCosmeticsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_COSMETICS_MAKEUP;
	}
	
	public static NPC characterSelected() {
		return Main.game.getDialogueFlags().getManagementCompanion();
	}
	
	private static String getSlaveInformationHeader(NPC character) {
		StringBuilder headerSB = new StringBuilder();
		AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer()));
		ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
		float affectionChange = character.getDailyAffectionChange();
		float obedienceChange = character.getDailyObedienceChange();
		
		headerSB.append(
				"<div class='container-full-width' style='margin-top:0; text-align:center; border-radius: 5px 0 0 5px;'>"
					// Extra core information:
					+"<div class='container-full-width inner' style='margin-bottom:0;'>"
						+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Location"
						+ "</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Привязанность</b></div><div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'><b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Покорность</b></div><div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'><b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Доход</b></div><div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>Value</div></div><div class='container-full-width inner' style='margin-top:0; border-radius: 0 5px 5px; 0'><div style='width:30%; float:left; margin:0; padding:0;'><b style='color:"+character.getLocationPlace().getColour().toWebHexString()+";'>"+character.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+character.getWorldLocation().getColour().toWebHexString()+";'>"+character.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+character.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(SlaveJob.getFinalDailyIncomeAfterModifiers(character))+"/день</div><div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(character.getValueAsSlave(true))
						+"</div>"
					+ "</div>");

		
		// Job:
		headerSB.append("<div class='container-half-width' style='width:50%; margin:0;'>[style.boldExcellent(Настройки работы:)]");
		List<String> noPermissions = new ArrayList<>();
		for(SlaveJob job : SlaveJob.values()) {
			if(character.hasSlaveJobAssigned(job)) {
				List<String> permissions = new ArrayList<>();
				if(!character.getSlaveJobSettings(job).isEmpty()) {
					headerSB.append("<br/><b>"+Util.capitaliseSentence(job.getName(character))+":</b> ");
					for(SlaveJobSetting setting : character.getSlaveJobSettings(job)) {
						permissions.add("<span style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
					}
					headerSB.append(Util.stringsToStringList(permissions, false)+".");
				} else {
					noPermissions.add(job.getName(character));
				}
			}
		}
		if(!noPermissions.isEmpty()) {
			headerSB.append("<br/><b>"+Util.stringsToStringList(noPermissions, true)+":</b> [style.colourDisabled(n/a)]");
		}
		headerSB.append("</div>");
		
		
		// Permissions:
		if(characterSelected().isSlave()) {
			headerSB.append("<div class='container-half-width' style='width:50%; margin:0;'>[style.boldArcane(Общие разрешения:)]<br/>");
			int permissionCount=0;
			for(SlavePermission permission : SlavePermission.values()) {
				if(permission.isAvailableForCharacter(character)) {
					for(SlavePermissionSetting setting : permission.getSettings()) {
						if(setting.isAvailableForCharacter(character)) {
							if(character.getSlavePermissionSettings().get(permission).contains(setting)) {
								headerSB.append((permissionCount==0?"":", ")+"<span style='color:"+permission.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
								permissionCount++;
							}
						}
					}
				}
			}
			headerSB.append(".</div>");
		}
		
		
		headerSB.append("</div>");
		
		return headerSB.toString();
	}
	
	private static String getBusyWarning() {
		return "<br/>[style.italicsMinorBad(Можно использовать только в стандартном диалоге локации.)]";
	}
	
	public static Response getManagementResponses(int index) {
		if(coreNode==CharactersPresentDialogue.MENU) { // Companion in the player's party:
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if (index == 0) {
				return new ResponseEffectsOnly("Назад", "Перестать осматривать присутствующих персонажей и вернуться к основной игре."){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
						Main.game.restoreSavedContent(false);
						MainController.updateUI();
					}
				};

			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("Осмотреть", UtilText.parse(characterSelected(), "Вы уже внимательно изучаете [npc.name]!"), null);
				}
				return new Response("Осмотреть", UtilText.parse(characterSelected(), "Рассмотреть [npc.name] поближе."), SLAVE_MANAGEMENT_INSPECT);

			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("Работа", UtilText.parse(characterSelected(), "Вы уже устанавливаете работу и часы работы для [npc.namePos]!"), null);
				}
				if(!characterSelected().hasJob()) {
					return new Response("Работа", "Установить работу и часы работы этого персонажа.", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("Работа", "Вы не можете управлять работой трудоустроенного жителя. Эта опция доступна только для рабов и безработных жителей.", null);

			} else if(index==3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("Разрешения", UtilText.parse(characterSelected(), "Вы уже устанавливаете разрешения для [npc.namePos]!"), null);
				}
				if(characterSelected().isSlave()) {
					return new Response("Разрешения", "Установить работу и часы работы этого раба.", SLAVE_MANAGEMENT_PERMISSIONS);
				}
				return new Response("Разрешения", "Вы не можете управлять разрешениями свободного спутника. Эта опция доступна только для рабов.", null);
				
			} else if(index==4) {
				if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Инвентарь", "Вы сейчас заняты чем-то важным!"+getBusyWarning(), null);
					
				} else {
					return new ResponseEffectsOnly("Инвентарь", UtilText.parse(characterSelected(), "Управление инвентарем [npc.namePos].")) {
						@Override
						public void effects() {
							Main.mainController.openInventory((NPC) characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				}
						
			} else if(index==5) {
				return new Response("Отправить Кейт", UtilText.parse(characterSelected(), "Вы не можете отправить спутников отряда к Кейт! Сначала вам нужно отправить [npc.name] домой..."), null);
				
			} else if (index == 6) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(Сбросить перки)]",
							UtilText.parse(characterSelected(), "Сбросить все перки и черты [npc.namePos], вернув все потраченные очки. (Это временная мера, так как древо перков всё еще находится в разработке.)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("Перки", UtilText.parse(characterSelected(), "Распределить очки перков [npc.namePos]."), SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected().isDoll() && (Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE) || characterSelected().hasItemType(ItemType.DOLL_CONSOLE))) {
					return new ResponseEffectsOnly("Трансформации", UtilText.parse(characterSelected(), "Используйте свой D.E.C.K., чтобы настроить внешность [npc.namePos].")) {
						@Override
						public void effects() {
							ItemEffectType.DOLL_CONSOLE.itemEffectOverride(TFModifier.NONE, TFModifier.NONE, TFPotency.BOOST, 0, Main.game.getPlayer(), characterSelected(), new ItemEffectTimer());
						}
					};
					
				} else if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("Трансформации", characterSelected().getUnableToTransformDescription(), null);
					
				} else if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Трансформации", "Вы сейчас заняты чем-то важным!"+getBusyWarning(), null);
					
				} else {
					return new Response("Трансформации",
							UtilText.parse(characterSelected(), "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected());
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("Установить имена", UtilText.parse(characterSelected(), "Вы управляете именем [npc.namePos]!"), null);
				}
				 if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Установить имена", "Вы сейчас заняты чем-то важным!"+getBusyWarning(), null);
				}
				return new Response("Установить имена",
						characterSelected().isSlave()
							?UtilText.parse(characterSelected(), "Изменить имя [npc.namePos] или попросить [npc.herHim] называть вас другим именем.")
							:UtilText.parse(characterSelected(), "Попросить [npc.name] называть вас другим именем."),
						OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10) {
				if(Main.game.getPlayer().isCaptive()) {
					return new Response(
							characterSelected().isElemental()
								?"Рассеять"
								:"Отправить домой",
							"Так как вы находитесь в плену, вы не можете отпустить своих спутников!",
							null);
					
				} else if(!Main.game.isSavedDialogueNeutral()) {
					return new Response(
							characterSelected().isElemental()
								?"Рассеять"
								:"Отправить домой",
							"Вы сейчас заняты чем-то важным!"+getBusyWarning(),
							null);
					
				} else {
					if(charactersPresent.size()==1 || (charactersPresent.size()==2 && characterSelected().isElementalSummoned() && !Main.game.getPlayer().isElementalSummoned())) {
						return new ResponseEffectsOnly(
								characterSelected().isElemental()
									?"Рассеять"
									:"Отправить домой",
									UtilText.parse(characterSelected(), 
										characterSelected().isElemental()
											?"Рассеять физическую форму [npc.namePos] и вернуть [npc.herHim] в вашу аркану ауру."
											:"Сказать [npc.name], чтобы она шла домой.")) {
							@Override
							public void effects() {
								if(characterSelected().isElementalSummoned()) {
									characterSelected().removeCompanion(characterSelected().getElemental());
									characterSelected().getElemental().returnToHome();
								}
								Main.game.getPlayer().removeCompanion(characterSelected());
								characterSelected().returnToHome();
//								Main.mainController.openCharactersPresent();
								Main.game.restoreSavedContent(false);
							}
						};
						
					} else {
						return new Response(
								characterSelected().isElemental()
									?"Рассеять"
									:"Отправить домой",
									UtilText.parse(characterSelected(), 
										characterSelected().isElemental()
											?"Рассеять физическую форму [npc.namePos] и вернуть [npc.herHim] в вашу аркану ауру."
											:"Сказать [npc.name], чтобы она шла домой."),
								coreNode){
							@Override
							public void effects() {
								if(characterSelected().isElementalSummoned()) {
									characterSelected().removeCompanion(characterSelected().getElemental());
									characterSelected().getElemental().returnToHome();
								}
								Main.game.getPlayer().removeCompanion(characterSelected());
								characterSelected().returnToHome();
								
								Main.game.setResponseTab(0);
								CharactersPresentDialogue.resetContent(Main.game.getCharactersPresent().get(0));
							}
						};
					}
				}
				
			} else if(index==11) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("Боевые приемы", UtilText.parse(characterSelected(), "Настроить приемы, которые [npc.name] может использовать в бою."), CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(characterSelected(), coreNode);
						}
					};
				} else {
					return new Response("Боевые приемы", "Вы сейчас заняты чем-то важным!"+getBusyWarning(), null);
				}
				
			} else if(index==12) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("Заклинания", UtilText.parse(characterSelected(), "Управление заклинаниями [npc.namePos]."), SpellManagement.CHARACTER_SPELLS_EARTH) {
						@Override
						public void effects() {
							SpellManagement.setSpellOwner(characterSelected(), coreNode);
						}
					};
					
				} else {
					return new Response("Заклинания", "Вы сейчас заняты чем-то важным!"+getBusyWarning(), null);
				}
				
			} else if(index==13) {
				if(!characterSelected().isElementalSummoned()) {
					return new Response("Снятие стихийного существа", UtilText.parse(characterSelected(), "У [npc.Name] нет призванного стихийного существа..."), null);
					
				} else {
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("Снятие стихийного существа", "Вы сейчас заняты чем-то важным!"+getBusyWarning(), null);
						
					} else {
						return new Response("Снятие стихийного существа", UtilText.parse(characterSelected(), "Приказать [npc.name] развеять стихийную [npc.her]."), coreNode){
							@Override
							public void effects() {
								characterSelected().getElemental().returnToHome();
								characterSelected().setElementalSummoned(false);
							}
						};
					}
				}
			}
			
		} else if(characterSelected()!=null && characterSelected().isSlave()) { // Slave not currently in the player's party:
			if (index == 1) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("Осмотреть", UtilText.parse(characterSelected(), "Вы уже внимательно изучаете [npc.name]!"), null);
				}
				return new Response("Осмотреть", UtilText.parse(characterSelected(), "Рассмотреть [npc.name] повнимательнее!"), SLAVE_MANAGEMENT_INSPECT);
				
			} else if (index == 2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("Работа", UtilText.parse(characterSelected(), "Вы уже устанавливаете работу и часы работы для [npc.namePos]!"), null);
				}
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Работа", "Ты не можешь распоряжаться работой рабыни, которая тебе не принадлежит!", null);
				}
				return new Response("Работа", "Установить работу и часы работы этого раба.", SLAVE_MANAGEMENT_JOBS);
				
			} else if (index == 3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("Разрешения", UtilText.parse(characterSelected(), "Вы уже устанавливаете разрешения для [npc.namePos]!"), null);
				}
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Разрешения", "Ты не можешь управлять правами рабыни, которая тебе не принадлежит!", null);
				}
				return new Response("Разрешения", "Установить права этой рабыни.", SLAVE_MANAGEMENT_PERMISSIONS);
				
			} else if (index == 4) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Инвентарь", "Ты не можешь управлять инвентарем рабыни, которая тебе не принадлежит!", null);
				}
				
				if(characterSelected().getOwner().isPlayer()) {
					return new ResponseEffectsOnly("Инвентарь", UtilText.parse(characterSelected(), "Управление инвентарем [npc.namePos].")){
						@Override
						public void effects() {
							Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				} else {
					return new Response("Инвентарь", UtilText.parse(characterSelected(), "Ты не можешь управлять инвентарем [npc.namePos], так как [npc.herHim] тебе не принадлежит!"), null);
				}
				
			} else if(index == 5) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Отправить Кейт", "Ты не можешь отправлять Кейт рабынь, которые тебе не принадлежат!", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					return new Response("Отправить Кейт",
							UtilText.parse(characterSelected(), "Отправить [npc.name] в салон красоты Кейт «Секреты суккуба», чтобы изменить внешность [npc.her]."),
							SLAVE_MANAGEMENT_COSMETICS_MAKEUP) {
								@Override
								public void effects() {
									BodyChanging.setTarget(characterSelected());
								}
							};
				} else {
					return new Response("Отправить Кейт", "Вы еще не встречали Кейт!", null);
				}
				
			} else if (index == 6) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Перки", "Ты не можешь управлять перками рабынь, которые тебе не принадлежат!", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(Сбросить перки)]",
							UtilText.parse(characterSelected(), "Сбросить все перки и черты [npc.namePos], вернув все потраченные очки. (Это временная мера, так как древо перков всё еще находится в разработке.)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("Перки", "Потратить очки навыков вашей рабыни.", SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected().isDoll() && (Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE) || characterSelected().hasItemType(ItemType.DOLL_CONSOLE))) {
					return new ResponseEffectsOnly("Трансформации", UtilText.parse(characterSelected(), "Используйте свой D.E.C.K., чтобы настроить внешность [npc.namePos].")) {
						@Override
						public void effects() {
							ItemEffectType.DOLL_CONSOLE.itemEffectOverride(TFModifier.NONE, TFModifier.NONE, TFPotency.BOOST, 0, Main.game.getPlayer(), characterSelected(), new ItemEffectTimer());
						}
					};
					
				} else if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("Трансформации", characterSelected().getUnableToTransformDescription(), null);
					
				} else {
					return new Response("Трансформации",
							UtilText.parse(characterSelected(), "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected(), coreNode, defaultResponseTab);
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("Установить имена", UtilText.parse(characterSelected(), "Вы управляете именем [npc.namePos]!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Установить имена", "Вы еще никого не выбрали...", null);
				}
				return new Response("Установить имена", UtilText.parse(characterSelected(), "Изменить имя [npc.namePos] или попросить [npc.herHim] называть вас другим именем."), OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
				return new Response("Отправить домой", UtilText.parse(characterSelected(), "[npc.Name] нет в вашем отряде, поэтому вы не можете отправить [npc.herHim] домой..."), null);
				
			} else if(index==11) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Боевые приемы", "Вы не можете управлять боевыми приемами рабов, которыми не владеете!", null);
				}
				return new Response("Боевые приемы", UtilText.parse(characterSelected(), "Настроить приемы, которые [npc.name] может использовать в бою."), CombatMovesSetup.COMBAT_MOVES_CORE) {
					@Override
					public void effects() {
						CombatMovesSetup.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==12) {
				return new Response("Заклинания", UtilText.parse(characterSelected(), "Управление заклинаниями [npc.namePos]."), SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setSpellOwner(characterSelected(), coreNode);
					}
				};
				
			} else if(index==13) {
				if(!characterSelected().isElementalSummoned()) {
					return new Response("Снятие стихийного существа", UtilText.parse(characterSelected(), "У [npc.Name] нет призванного стихийного существа..."), null);
					
				} else {
					return new Response("Снятие стихийного существа", UtilText.parse(characterSelected(), "Приказать [npc.name] развеять стихийную [npc.her]."), coreNode) {
						@Override
						public void effects() {
							characterSelected().removeCompanion(characterSelected().getElemental());
							characterSelected().getElemental().returnToHome();
						}
					};
				}
				
			} else if(index==14) {
				if(characterSelected().isDoll()) {
					return new Response("Освободить",
							UtilText.parse(characterSelected(), "Кукол нельзя просто так освободить..."),
							null);
					
				} else if(!Main.game.getPlayer().hasItemType("innoxia_slavery_freedom_certification")) {
					return new Response("Освободить",
							UtilText.parse(characterSelected(),
									"У вас нет Сертификата Свободы, поэтому вы не можете освободить [npc.name]...<br/><i>Сертификаты Свободы можно купить у Финча в здании Администрации рабов в Переулке Рабов.</i>"),
							null);
					
				} else {
					if(characterSelected() instanceof Scarlett) {
						return new Response("Освободить",
								UtilText.parse(characterSelected(),
										"Заполните Сертификат Свободы и освободите [npc.name]!<br/>Если вы сделаете это, [npc.she] несомненно тут же сбежит и вернется в гнездо Helena..."),
								SET_SLAVE_FREE_SCARLETT);
					}
					if(characterSelected() instanceof Brax) {
						return new Response("Освободить",
								UtilText.parse(characterSelected(),
										"Вы не можете освободить [npc.name]...<br/><i>(Это будет добавлено позже, когда я добавлю больше контента для Brax!)</i>"),
								null);
					}
					
					String unavailableGuestDescription = "";
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						unavailableGuestDescription = "[style.italicsMinorBad(Так как у вас нет разрешения от Lilaya принимать гостей, вы не сможете пригласить [npc.name] пожить здесь, в особняке, после освобождения [npc.herHim]!)]";
						
					} else if(!characterSelected().isAffectionHighEnoughToInviteHome()) {
						unavailableGuestDescription = "[style.italicsBad([npc.Name] любит вас недостаточно сильно, чтобы рассматривать возможность жизни с вами после обретения свободы!)]";
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						unavailableGuestDescription = "[style.italicsMinorBad(Так как у вас нет свободной гостевой комнаты, в которую могла бы заселиться [npc.name], вы не сможете пригласить [npc.herHim] пожить здесь, в особняке, после освобождения [npc.herHim]!)]";
					}
					String thanksjava = unavailableGuestDescription;
					
					return new Response("Освободить",
							UtilText.parse(characterSelected(),
									"Заполните Сертификат Свободы и освободите [npc.name]!<br/>"
									+ (thanksjava.isEmpty()
										?"[style.italicsMinorGood([npc.Name] любит вас достаточно сильно, чтобы принять предложение поселиться здесь, так что после освобождения [npc.herHim] вы сможете предложить [npc.herHim] занять одну из свободных гостевых комнат!)]"
										:thanksjava+"<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] (и всё содержимое [npc.her] инвентаря) из игры!)]")),
							SET_SLAVE_FREE) {
						@Override
						public Colour getHighlightColour() {
							if(thanksjava.isEmpty()) {
								return PresetColour.GENERIC_MINOR_GOOD;
							}
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
					};
				}
				
			} else if(index == 0) {
				if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
					return new Response("Назад", "Вернуться к списку обитателей.", coreNode) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							coreNode = null;
						}
					};
				}
				return new Response("Уйти",
						characterSelected()==null
							?"Вернуться к предыдущему диалогу."
							:UtilText.parse(characterSelected(),
								characterSelected().isDollStatue()
									?"Оставить [npc.name] застывшей на месте и продолжить свой путь."
									:(characterSelected().isAsleep()
										?"Оставить [npc.name] поспать..."
										:"Сказать [npc.name], что вы догоните [npc.herHim] в другой раз.")),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
		
		} else { // Friendly occupant or null character not currently in the player's party:
			if (index == 1) {
				if(characterSelected() == null) {
					return new Response("Осмотреть", "Вы еще никого не выбрали...", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("Осмотреть", UtilText.parse(characterSelected(), "Вы уже внимательно изучаете [npc.name]!"), null);
				}
				return new Response("Осмотреть", UtilText.parse(characterSelected(), "Рассмотреть [npc.name] поближе."), SLAVE_MANAGEMENT_INSPECT);
				
			} else if (index == 2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("Работа", UtilText.parse(characterSelected(), "Вы уже устанавливаете работу и часы работы для [npc.namePos]!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Работа", "Вы еще никого не выбрали...", null);
				}
				if(!characterSelected().hasJob()) {
					return new Response("Работа", "Установить временную работу и часы работы для этого обитателя.", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("Работа", "У этого обитателя уже есть постоянная работа.", null);
			} else if (index == 3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("Разрешения", UtilText.parse(characterSelected(), "Вы уже устанавливаете разрешения для [npc.namePos]!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Разрешения", "Вы еще никого не выбрали...", null);
				}
				return new Response("Разрешения", "Вы не можете управлять правами вольнодумного обитателя. Этот вариант доступен только для рабов.", null);
				
			} else if (index == 4) {
				if(characterSelected() == null) {
					return new Response("Инвентарь", "Вы еще никого не выбрали...", null);
				}
				return new ResponseEffectsOnly("Инвентарь", UtilText.parse(characterSelected(), "Управление инвентарем [npc.namePos].")){
					@Override
					public void effects() {
						Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if(index == 5) {
				if(characterSelected() == null) {
					return new Response("Отправить Кейт", "Вы еще никого не выбрали...", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					return new Response("Отправить Кейт",
							UtilText.parse(characterSelected(), "Отправить [npc.name] в салон красоты Кейт «Секреты суккуба», чтобы изменить внешность [npc.her]."),
							SLAVE_MANAGEMENT_COSMETICS_MAKEUP) {
								@Override
								public void effects() {
									BodyChanging.setTarget(characterSelected());
								}
							};
				} else {
					return new Response("Отправить Кейт", "Вы еще не встречали Кейт!", null);
				}
				
			} else if (index == 6) {
				if(characterSelected() == null) {
					return new Response("Перки", "Вы еще никого не выбрали...", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(Сбросить перки)]",
							UtilText.parse(characterSelected(), "Сбросить все перки и черты [npc.namePos], вернув все потраченные очки. (Это временная мера, так как древо перков всё еще находится в разработке.)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("Перки", UtilText.parse(characterSelected(), "Распределить очки перков [npc.namePos]."), SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected() == null) {
					return new Response("Трансформации", "Вы еще никого не выбрали...", null);
				}
				if(characterSelected().isDoll() && (Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE) || characterSelected().hasItemType(ItemType.DOLL_CONSOLE))) {
					return new ResponseEffectsOnly("Трансформации", UtilText.parse(characterSelected(), "Используйте свой D.E.C.K., чтобы настроить внешность [npc.namePos].")) {
						@Override
						public void effects() {
							ItemEffectType.DOLL_CONSOLE.itemEffectOverride(TFModifier.NONE, TFModifier.NONE, TFPotency.BOOST, 0, Main.game.getPlayer(), characterSelected(), new ItemEffectTimer());
						}
					};
					
				} else if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("Трансформации", characterSelected().getUnableToTransformDescription(), null);
					
				} else {
					return new Response("Трансформации",
							UtilText.parse(characterSelected(), "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected(), coreNode, defaultResponseTab);
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("Установить имена", UtilText.parse(characterSelected(), "Вы управляете именем [npc.namePos]!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Установить имена", "Вы еще никого не выбрали...", null);
				}
				return new Response("Установить имена", UtilText.parse(characterSelected(), "Попросить [npc.name] называть вас другим именем."), OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
				if(characterSelected() == null) {
					return new Response("Отправить домой", "Вы еще никого не выбрали...", null);
				}
				return new Response("Отправить домой", UtilText.parse(characterSelected(), "[npc.Name] нет в вашем отряде, поэтому вы не можете отправить [npc.herHim] домой..."), null);
				
			} else if(index==11) {
				if(characterSelected() == null) {
					return new Response("Боевые приемы", "Вы еще никого не выбрали...", null);
				}
				return new Response("Боевые приемы", UtilText.parse(characterSelected(), "Настроить приемы, которые [npc.name] может использовать в бою."), CombatMovesSetup.COMBAT_MOVES_CORE) {
					@Override
					public void effects() {
						CombatMovesSetup.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==12) {
				if(characterSelected() == null) {
					return new Response("Заклинания", "Вы еще никого не выбрали...", null);
				}
				return new Response("Заклинания", UtilText.parse(characterSelected(), "Управление заклинаниями [npc.namePos]."), SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setSpellOwner(characterSelected(), coreNode);
					}
				};
				
			} else if(index==13) {
				if(characterSelected() == null) {
					return new Response("Снятие стихийного существа", "Вы еще никого не выбрали...", null);
				}
				if(!characterSelected().isElementalSummoned()) {
					return new Response("Снятие стихийного существа", UtilText.parse(characterSelected(), "У [npc.Name] нет призванного стихийного существа..."), null);
					
				} else {
					return new Response("Снятие стихийного существа", UtilText.parse(characterSelected(), "Приказать [npc.name] развеять стихийную [npc.her]."), coreNode) {
						@Override
						public void effects() {
							characterSelected().removeCompanion(characterSelected().getElemental());
							characterSelected().getElemental().returnToHome();
						}
					};
				}
				
			} else if(index == 0) {
				if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
					return new Response("Назад", "Вернуться к списку обитателей.", coreNode) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							coreNode = null;
						}
					};
				}
				return new Response("Уйти",
							characterSelected()==null
								?"Вернуться к предыдущему диалогу."
								:UtilText.parse(characterSelected(),
										characterSelected().isDollStatue()
											?"Оставить [npc.name] застывшей на месте и продолжить свой путь."
											:(characterSelected().isAsleep()
												?"Оставить [npc.name] поспать..."
												:"Сказать [npc.name], что вы догоните [npc.herHim] в другой раз.")),
							Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
		}
		
		return null;
	}
	
	/**
	 * <b>Use getSlaveryManagementDetailedDialogue(NPC slave) to initialise this!!!</b>
	 */
	public static final DialogueNode SLAVE_MANAGEMENT_INSPECT = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "Осмотр [npc.Name]");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			if(character.isSlave() && character.getOwner().isPlayer()) {
				UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			}
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "<h6 style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Осмотр</h6><div class='container-full-width inner'>"
								+character.getCharacterInformationScreen(false)
							+"</div>"
					+"</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_JOBS = new DialogueNode("Управление рабами", ".", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().getSlaveryManagerJobSelected().isHidden(characterSelected())) {
				Main.game.getDialogueFlags().setSlaveryManagerJobSelected(SlaveJob.IDLE);
			}
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos] Работа");
		}
		@Override
		public String getContent() {
			NPC character = characterSelected();
			ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Job hours
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append("<div class='container-full-width inner' style='text-align:center;padding-left:2px;padding-right:2px;'>");

			UtilText.nodeContentSB.append("<div class='title-button "+(!isJobSchedulePasteAvailable()?"no-select":"")+"' id='pasteSlaveJobSchedule' style='position:absolute; left:auto; right:8px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999; "+(!isJobSchedulePasteAvailable()?"opacity:0.5;":"")+"'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getPasteIcon()
								+ "</div>");
			UtilText.nodeContentSB.append("<div class='title-button' id='copySlaveJobSchedule' style='position:absolute; left:auto; right:48px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999;'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()
								+ "</div>");
			
			UtilText.nodeContentSB.append("<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>График работ</h6>");
			UtilText.nodeContentSB.append("<div style='width:100%;margin-top:8px;'><b>Доступные работы</b></div>");
			
			
			for(SlaveJob job : SlaveJob.values()) {
				if(!job.isHidden(character) && (character.isSlave() || job.hasFlag(SlaveJobFlag.GUEST_CAN_WORK))) {
					UtilText.nodeContentSB.append(
							"<div class='normal-button' id='"+job+"_ASSIGN' style='width:calc(16.6% - 2px); margin:1px;color:"
									+job.getColour().toWebHexString()+";"+(Main.game.getDialogueFlags().getSlaveryManagerJobSelected()==job?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
									+Util.capitaliseSentence(job.getName(character))
									+"</div>");
				}
			}
			
			UtilText.nodeContentSB.append("<div style='width:100%;margin-top:8px;'><b>Временные интервалы</b></div>");
			float stamina = character.getDailySlaveJobStamina();
			SlaveJob jobSelected = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
			for(int i=0 ; i< 24; i++) {
				Colour colour = character.getSlaveJob(i).getColour();
				Colour borderColour = colour;
				Colour backgroundColour = colour;
				String background = "background:"+backgroundColour.getShades()[0]+";";
//				if(character.isSleepingAtHour(i)) {
////					colour = PresetColour.BASE_PURPLE_DARK;
////					background = "background:"+PresetColour.BASE_PURPLE.getShades()[0]+";";
////					String c1 = backgroundColour.getShades()[0];
////					String c2 = PresetColour.BASE_PURPLE_LIGHT.getShades()[0];
////					background = "background: repeating-linear-gradient(135deg, "+c1+", "+c1+" 10px, "+c2+" 10px, "+c2+" 20px);";
//				}
				if((!jobSelected.isAvailable(i, character)
							|| (!character.isSlave() && stamina-jobSelected.getHourlyStaminaDrain(character)+character.getSlaveJob(i).getHourlyStaminaDrain(character)<0f)
							|| (!character.isSlave() && character.isSleepingAtHour(i)))
						&& !jobSelected.equals(SlaveJob.IDLE)) { // Always allow idle job
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour disabled' style='"+background+"border-color:"+borderColour.toWebHexString()+";color:"+colour.getShades()[4]+";' id='"+i+"_WORK_DISABLED'>");
				} else {
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour' style='"+background+"border-color:"+borderColour.toWebHexString()+";color:"+colour.getShades()[4]+";' id='"+i+"_WORK'>");
				}
				UtilText.nodeContentSB.append(String.format("%02d", i)+":00");
				if(character.isSleepingAtHour(i)) { // Sleeping indication via 'zzZ'
					String stroke = "text-shadow:1px 1px 0 #000,-1px -1px 0 #000, 1px -1px 0 #000,-1px 1px 0 #000,1px 1px 0 #000;";
					UtilText.nodeContentSB.append("<b class='hotkey-icon' style='color:"+PresetColour.SLEEP.toWebHexString()+";"+stroke+"'><span style='font-size:0.8em;'>z</span>zZ</b>");
				}
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append(
								"<div style='width:100%;margin-top:8px;'><i>[style.colourStamina(Текущая дневная выносливость:)] "+(stamina>=0?"[style.colourGood(":"[style.colourBad(")+stamina+")]/"+character.getBaseStaminaForSlaveJobs()+"</i>"
								+ "</div>");
								for(SlaveJobHours preset : SlaveJobHours.values()) {
									boolean jobDisabled = false;
									boolean resetI = false;
									boolean nonSlaveSleeping = false;
									for (int i = preset.getStartHour(); i<preset.getStartHour()+preset.getLength(); i++) {
										if (i>23) {
											i = i-24; // Wrap around to 0
											resetI = true;
										}
										if (!jobSelected.isAvailable(i, character)) {
											jobDisabled = true;
											break;
										}
										if(!nonSlaveSleeping) {
											nonSlaveSleeping = !character.isSlave() && character.isSleepingAtHour(i);
										}
										if (resetI) {
											i = i+24; // Reset i to maintain the loop
										}
									}
									if ((!character.isSlave() && jobSelected.getHourlyStaminaDrain(character)*preset.getLength()>stamina) || nonSlaveSleeping || jobDisabled) {
										UtilText.nodeContentSB.append("<div class='normal-button disabled' id='"+preset+"_TIME_DISABLED' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
									} else {
										UtilText.nodeContentSB.append("<div class='normal-button' id='"+preset+"_TIME' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
									}
								}
			UtilText.nodeContentSB.append(
							"</div>"
//						+ "</div>"
					+ "</div>");
			
			
			// Jobs:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>");

			UtilText.nodeContentSB.append("<div class='title-button "+(!isJobSettingsPasteAvailable()?"no-select":"")+"' id='pasteSlaveJobSettings' style='position:absolute; left:auto; right:8px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999; "+(!isJobSettingsPasteAvailable()?"opacity:0.5;":"")+"'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getPasteIcon()
								+ "</div>");
			UtilText.nodeContentSB.append("<div class='title-button' id='copySlaveJobSettings' style='position:absolute; left:auto; right:48px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999;'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()
								+ "</div>");
			
			UtilText.nodeContentSB.append("<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Настройки работы и доп. информация</h6><div class='container-full-width' style='margin-bottom:0;'><div style='width:20%; float:left; font-weight:bold; margin:0 0 0 4%; padding:0;'>Работа</div><div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'><b>Рабочие</b></div><div style='float:left; width:14%; font-weight:bold; margin:0; padding:0;'><b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Привязанность</b></div><div style='float:left; width:14%; font-weight:bold; margin:0; padding:0;'><b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Покорность</b></div><div style='float:left; width:39%; font-weight:bold; margin:0; padding:0;'><b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Доход</b> (+<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Бонус к покорности</b>)</div></div>");
			
			for(SlaveJob job : SlaveJob.values()) {
				if(job.isHidden(character) || (!character.isSlave() && !job.hasFlag(SlaveJobFlag.GUEST_CAN_WORK))) {
					continue;
				}
				float affectionChange = job.getAffectionGain(character);
				float obedienceChange = job.getObedienceGain(character);
				int income = job.getFinalHourlyIncomeAfterModifiers(character);
				boolean isCurrentJob = character.hasSlaveJobAssigned(job);
				
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' "+(isCurrentJob?"style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"")+"><div style='width:4%; float:left; margin:0; padding:0;'><div class='title-button no-select' id='SLAVE_JOB_INFO_"+job+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
							+ "</div>"
							+"<div style='width:20%; float:left; margin:0; padding:0;'>"
								+ (isCurrentJob
									? "<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(character))+"</b>"
									: "[style.colourDisabled("+Util.capitaliseSentence(job.getName(character))+")]")
							+ "</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
								+ Main.game.getOccupancyUtil().getTotalCharactersWorkingJob(job)+"/"+(job.getSlaveLimit()<0?"&#8734;":job.getSlaveLimit())
							+"</div>"
							+ "<div style='float:left; width:14%; margin:0; padding:0;'>"
								+ (affectionChange>0
										?"<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>"
										:(affectionChange<0
												?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/час</div><div style='float:left; width:14%; margin:0; padding:0;'>"
								+ (obedienceChange>0
										?"<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>"
										:(obedienceChange<0
												?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/час</div><div style='float:left; width:39%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(job.getIncome())
								+ " + ("
								+ (job.getObedienceIncomeModifier()>0
										?"[style.colourObedience("+job.getObedienceIncomeModifier()+")]"
										:"[style.colourDisabled("+job.getObedienceIncomeModifier()+")]")
										+ "*<span style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+"</span>)"
								+ " = "
								+(income>0
									?UtilText.formatAsMoney(income, "b")
									:UtilText.formatAsMoney(income, "b", PresetColour.GENERIC_BAD))
								+"/час</div>"
							);
				
				// Job Settings:
				for(SlaveJobSetting setting : job.getMutualSettings()) {
					if(!setting.isAvailable()) {
						continue;
					}
					boolean settingActive = character.hasSlaveJobSetting(job, setting);
					
					String id = settingActive
							?job.toString()+setting.toString()+"_REMOVE"
							:job.toString()+setting.toString()+"_ADD";
			
					UtilText.nodeContentSB.append(
							"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='width:23%; margin:1%; text-align:center;"
										+(settingActive?"border-color:"+job.getColour().toWebHexString()+";":"border-color:"+job.getColour().getShades()[0]+";")+"'>"
								+ (settingActive
										?"<span style='color:"+job.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
										:"[style.colourDisabled("+setting.getName()+")]")
							+ "</div>");
				}
				// More Job Settings:
				for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner' style='"+(!isCurrentJob?"background:#1B1B1B;":"")+"'><div style='width:100%; float:left; margin:0; padding:0;"+(isCurrentJob?"":"color:#777;")+"'>"
													+ "<b>"+Util.capitaliseSentence(entry.getKey())+"</b>"
													+ "</div>");
					
					for(SlaveJobSetting setting : entry.getValue()) {
						if(!setting.isAvailable()) {
							continue;
						}
						boolean settingActive = character.hasSlaveJobSetting(job, setting);
						
						String id = settingActive
								?setting.toString()+"_DISABLED"
								:setting.toString()+"_TOGGLE_ADD";
				
						UtilText.nodeContentSB.append(
								"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='width:23%; margin:1%; text-align:center;"
											+(settingActive?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
									+ (settingActive
											?"<span style='color:"+job.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
											:"[style.colourDisabled("+setting.getName()+")]")
								+ "</div>");
					}
					UtilText.nodeContentSB.append("</div>");
				}
				
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append("</div>");

//			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");
//			UtilText.nodeContentSB.append(  "<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Additional Book-Keeping Information</h6>");
//			UtilText.nodeContentSB.append(  "<p>Optional extra information you may add for this slave for book-keeping purposes.</p>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(    "<h7>Category</h7>");
//			UtilText.nodeContentSB.append(    "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(      "<input id='SET_SLAVE_CATEGORY' type='text' value='"+UtilText.parseForHTMLDisplay(character.getSlaveCategory())+"'/>");
//			UtilText.nodeContentSB.append(    "</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(    "<h7>Notes</h7>");
//			UtilText.nodeContentSB.append(    "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(      "<form style='padding:0;margin:0;text-align:center;'>");
//			UtilText.nodeContentSB.append(        "<textarea id='SET_SLAVE_NOTES' style='width:760px;height:200px;'>"+character.getSlaveNotes()+"</textarea>");
//			UtilText.nodeContentSB.append(      "</form>");
//			UtilText.nodeContentSB.append(    "</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_PERMISSIONS = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Права доступа");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Permissions:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");

			UtilText.nodeContentSB.append("<div class='title-button "+(!isPermissionsPasteAvailable()?"no-select":"")+"' id='pastePermissions' style='position:absolute; left:auto; right:8px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999; "+(!isPermissionsPasteAvailable()?"opacity:0.5;":"")+"'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getPasteIcon()
								+ "</div>");
			UtilText.nodeContentSB.append("<div class='title-button' id='copyPermissions' style='position:absolute; left:auto; right:48px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999;'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()
								+ "</div>");
			
			UtilText.nodeContentSB.append("<h6 style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Права</h6>");
			
			for(SlavePermission permission : SlavePermission.values()) {
				if(permission.isAvailableForCharacter(character)) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner' style='box-sizing:border-box; position:relative; width:98%; margin:4px 1%; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>");
					
					for(SlavePermissionSetting setting : permission.getSettings()) {
						if(setting.isAvailableForCharacter(character)) {
							boolean settingActive = character.getSlavePermissionSettings().get(permission).contains(setting);
							
							String id = (permission.isMutuallyExclusiveSettings()
											?(settingActive?setting+"_REMOVE_ME":setting+"_ADD")
											:(settingActive?setting+"_REMOVE":setting+"_ADD"));
							
							String style = "width:23%; margin:1%;";
							if(permission.getSettings().size()==5) {
								style = "width:18%; margin:1%;"; // These settings can fit 5 on a line
							}
							
							UtilText.nodeContentSB.append(
									"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='"+style+"text-align:center;"
												+(settingActive
													?"border-color:"+permission.getColour().toWebHexString()+";"
													:(permission.isMutuallyExclusiveSettings()
															?""
															:"border-color:"+permission.getColour().getShades()[0]+";"))+"'>"
										+ (settingActive
												?"<span style='color:"+permission.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
												:"[style.colourDisabled("+setting.getName()+")]")
									+ "</div>");
						}
					}
					UtilText.nodeContentSB.append("</div>");
				}
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	private static Response getCosmeticsResponse(int responseTab, int index) {
		if (index == 1) {
			if(!BodyChanging.getTarget().isAbleToWearMakeup()) {
				return new Response("Макияж", UtilText.parse(BodyChanging.getTarget(), "Так как тело [npc.namePos] состоит из "+Main.game.getPlayer().getBodyMaterial().getName()+", Кейт не может нанести макияж!"), null);
				
			} else {
				return new Response("Макияж",
						"Кейт предлагает широкий спектр различных косметических услуг, и нескольким страницам брошюры посвящены изображения, демонстрирующие различные стили и цвета помады, лака для ногтей и других видов макияжа.",
						SLAVE_MANAGEMENT_COSMETICS_MAKEUP);
			}
			
		} else if (index == 2) {
			return new Response("Волосы",
					"Разворот брошюры целиком посвящен всем различным видам красок, стилям и длинам волос, с которыми Кейт может работать.",
					SLAVE_MANAGEMENT_COSMETICS_HAIR);

		} else if (index == 3) {
			return new Response("Пирсинг",
					"Кейт предлагает широкий выбор различных видов пирсинга.",
					SLAVE_MANAGEMENT_COSMETICS_PIERCINGS);

		}  else if (index == 4) {
			if(BodyChanging.getTarget().isDoll()) {
				return new Response("Глаза", "Кейт не может перекрасить аркановый силикон, поэтому эта опция недоступна...", null);
			}
			return new Response("Глаза",
					"В начале брошюры есть специальная страница, рекламирующая способность Кейт менять цвет глаз. Как и изменение цвета кожи, это требует немало её ауры, а потому стоит очень дорого.", SLAVE_MANAGEMENT_COSMETICS_EYES);

		} else if (index == 5) {
			if(BodyChanging.getTarget().isDoll()) {
				return new Response("Покрытия", "Кейт не может перекрасить аркановый силикон, поэтому эта опция недоступна...", null);
			}
			return new Response("Покрытия",
					"В середине брошюры есть специальная страница, рекламирующая уникальный дар Кейт — использование арканы для изменения цвета кожи или меха. Судя по всему, это сильно истощает её ауру, поэтому услуга стоит очень дорого.",
					SLAVE_MANAGEMENT_COSMETICS_COVERINGS){
				@Override
				public void effects() {
					SuccubisSecrets.initCoveringsMap(BodyChanging.getTarget());
				}
			};

		} else if (index == 6) {
			return new Response("Другое", "Кейт может предложить и другие разнообразные услуги, например, отбеливание ануса.", SLAVE_MANAGEMENT_COSMETICS_OTHER);

		} else if (index == 7) {
			return new Response("Татуировки", "Большую часть брошюры занимают рисунки и фотографии, демонстрирующие немалые художественные таланты Кейт. Она даже способна наносить татуировки, зачарованные Арканой, но, похоже, они стоят очень дорого...", SLAVE_MANAGEMENT_TATTOOS);

		} else if (index == 0) {
			if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
				return new Response("Назад", "Вернуться к списку обитателей.", coreNode) {
					@Override
					public void effects() {
						Main.game.setResponseTab(defaultResponseTab);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
			return new Response("Назад", "Вернуться к экрану управления рабынями.", coreNode) {
				@Override
				public void effects() {
					Main.game.setResponseTab(defaultResponseTab);
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_MAKEUP = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Макияж");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>У вас сейчас есть "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые руж) используются для придания щекам цвета, чтобы придать лицу более юный вид и подчеркнуть скулы.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Помада", "Помада используется для придания губам цвета, текстуры и защиты.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Подводка наносится по контуру глаз, чтобы подчеркнуть их форму или выделить отдельные черты.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются для того, чтобы сделать глаза ярче или привлекательнее.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окрашивания и защиты ногтей на [npc.namePos] [npc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окрашивания и защиты ногтей на [npc.namePos] [npc.feet].", true, true)
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Макияж",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже меняете [npc.namePos] макияж!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_HAIR = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Волосы");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>У вас сейчас есть "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")
					+ "</h6>"
					
					+ (BodyChanging.getTarget().isDoll()
							?""
							:CharacterModificationUtils.getKatesDivHairLengths(true, "Длина волос", "Длина волос определяет, какие прически [npc.namePos] смогут себе позволить. Чем длиннее [npc.her] [npc.hair(true)], тем больше стилей будет доступно."))

					+CharacterModificationUtils.getKatesDivHairStyles(true, "Прическа", "Доступность причесок определяется длиной [npc.namePos] [npc.hair(true)].")
					
					+(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME && !BodyChanging.getTarget().isDoll()
						?CharacterModificationUtils.getKatesDivCoveringsNew(
								true, BodyChanging.getTarget().getHairType().getRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(),
								UtilText.parse(BodyChanging.getTarget(), "Цвет [npc.Hair]"),
								"Все изменения цвета волос постоянны, так что если вы захотите сменить цвет снова, вам придется снова навестить Кейт.", true, true)
						:"")
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("Волосы",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже меняете волосы [npc.namePos]!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_PIERCINGS = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Пирсинг");
		}
		
		@Override
		public String getHeaderContent() {
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>У вас сейчас есть "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")
					+ "</h6>"
					
				+CharacterModificationUtils.getKatesDivPiercings(false));
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3) {
				return new Response("Пирсинг",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже меняете пирсинг [npc.namePos]!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_EYES = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Глаза");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>У вас сейчас есть "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")
					+ "</h6>"

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyChanging.getTarget().getEyeType().getRace(), BodyChanging.getTarget().getEyeCovering(),
							"Радужка", "Радужка — это цветная часть глаза, отвечающая за контроль диаметра и размера зрачка.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"Зрачки", "Зрачок — это отверстие в центре радужки, через которое свет попадает на сетчатку.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"Склера", "Склера — это (обычно белая) часть глаза, окружающая радужку.", true, true));
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4) {
				return new Response("Глаза",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже меняете [npc.namePos] глаз!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_COVERINGS = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Покровы");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>У вас сейчас есть "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")
								+ "</h6>");
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = BodyChanging.getTarget();

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
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5) {
				return new Response("Покрытия",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже меняете [npc.namePos] покровов!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_OTHER = new DialogueNode("Управление рабами", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] — Прочее");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>У вас сейчас есть "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivAnalBleaching());
			
			if(!BodyChanging.getTarget().isDoll()) {
				UtilText.nodeContentSB.append((Main.game.isFacialHairEnabled()
								? CharacterModificationUtils.getKatesDivFacialHair(true, "Растительность на лице", "Волосы на лице [npc.namePos]." 
										+ (Main.game.isFemaleFacialHairEnabled() ? "" : " Женские персонажи не могут отращивать растительность на лице."))
								:"")
						
						+(Main.game.isPubicHairEnabled()
								?CharacterModificationUtils.getKatesDivPubicHair(true, "Лобковые волосы", "Волосы в области гениталий; расположены на [npc.namePos] половых органах и вокруг них.")
								:"")
						
						+(Main.game.isBodyHairEnabled()
								?CharacterModificationUtils.getKatesDivUnderarmHair(true, "Волосы под мышками", "Волосы в подмышках [npc.namePos].")
								:"")
						
						+(Main.game.isAssHairEnabled()
								?CharacterModificationUtils.getKatesDivAssHair(true, "Волосы на заднице", "Волосы вокруг ануса [npc.namePos].")
								:"")
						);
			
				for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
					if((Main.game.isFacialHairEnabled() && BodyChanging.getTarget().getFacialHairType().getType()==bct)
							|| (Main.game.isBodyHairEnabled() && BodyChanging.getTarget().getUnderarmHairType().getType()==bct)
							|| (Main.game.isAssHairEnabled() && BodyChanging.getTarget().getAssHairType().getType()==bct)
							|| (Main.game.isPubicHairEnabled() && BodyChanging.getTarget().getPubicHairType().getType()==bct)) {
						UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
								true, Race.NONE, bct, "Волосы на теле", "Волосы на теле.", true, true));
					}
				}
			}
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Другое",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже меняете [npc.namePos] других черт!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_TATTOOS = new DialogueNode("Секреты Суккуба", "-", true) {

		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7) {
				return new Response("Татуировки",
						UtilText.parse(BodyChanging.getTarget(), "Вы уже управляете [npc.namePos] татуировками!"),
						null);
				
			} else if(index==11) {
				return new Response("Подтверждения: ",
						"Включить подтверждение удаления татуировок. Если включено, для удаления татуировки потребуется два клика. Если выключено — всего один.",
							SLAVE_MANAGEMENT_TATTOOS) {
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
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_TATTOOS_ADD = new DialogueNode("Секреты Суккуба", "-", true) {

		@Override
		public String getLabel() {
			return "Секреты суккубов — "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getName()) +" Татуировка";
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("Применить ("+UtilText.formatAsMoneyUncoloured(value, "охват")+")",
							UtilText.parse(BodyChanging.getTarget(), "У вас недостаточно денег, чтобы сделать татуировку [npc.name]!"),  null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Применить ("+UtilText.formatAsMoneyUncoloured(value, "охват")+")", "Чтобы сделать татуировку, нужно выбрать тип тату, добавить надпись или счетчик!", null);
					
				} else {
					return new Response("Применить ("+UtilText.formatAsMoney(value, "охват")+")", 
							UtilText.parse(BodyChanging.getTarget(), "Заплатите Кейт, чтобы сделать [npc.name] эту татуировку!"), SLAVE_MANAGEMENT_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("Сохранить/Загрузить", "Сохранять/загружать пресеты татуировок.", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(SLAVE_MANAGEMENT_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("Назад", "Решить не делать эту татуировку и вернуться к основному экрану выбора.", SLAVE_MANAGEMENT_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_PERKS = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "Перки [npc.NamePos]");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(),
					"<details><summary>[style.boldPerk(Информация о Перках и Чертах)]</summary>[style.colourPerk(Перки)] (круглые иконки) дают постоянные бонусы к атрибутам [npc.namePos].<br/>[style.colourPerk(Черты)] (квадратные иконки) дают уникальные эффекты для [npc.name]. В отличие от перков, <b>черты не будут влиять на [npc.name], пока их не вставят в панель [npc.her] «Активные черты»</b>.<br/>Для разблокировки перков требуются очки перков. [npc.Name] получает одно очко перка каждый раз, когда [npc.she] повышает уровень, и получает дополнительные два очка перка каждые пять уровней.<br/><br/>В дополнение к перкам, которые можно купить за очки перков, существуют также несколько специальных скрытых перков, которые открываются через особые события.</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterSelected(), true));
			
			UtilText.nodeContentSB.append("</div>");
			
			if(!characterSelected().isElemental() && !characterSelected().isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'><i>Пожалуйста, учтите, что это древо перков находится в процессе разработки. Это не финальная версия, а лишь проверка концепции!</i></div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode OCCUPANT_CHOOSE_NAME = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(characterSelected().isSlave()) {

				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
					"<p>В данный момент [npc.nameIsFull] называет тебя «[npc.pcName]», и ты гадаешь, стоит ли попросить [npc.herHim] называть тебя как-то иначе или использовать другой титул. Будучи [npc.sheIs], ты также могла бы изменить [npc.her] имя на любое другое, какое пожелаешь...</p>"));
				
				UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px 16px;'><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Имя</div><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Фамилия</div><div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "Как тебя называет [npc.she]")
						+ "</div>"
						
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+characterSelected().getId()+"_RENAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button' id='"+characterSelected().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div> <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>Все рабыни</div>");
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p>В данный момент [npc.nameIsFull] называет тебя '[npc.pcName]', и ты гадаешь, стоит ли заставить [npc.herHim] называть тебя как-то иначе. Поскольку [npc.sheIs] не твоя рабыня, ты не можешь заставить [npc.herHim] изменить [npc.her] имя.</p>"));
				

				UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px 16px;'><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Имя</div><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Фамилия</div><div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "Как тебя называет [npc.she]")
						+ "</div>"
						
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div> <div class='normal-button disabled' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>Все рабыни</div>");
			}
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p style='text-align:center; margin-top:4px;'><i>Если сказать [npc.name] называть вас «Мамой» или «Папой», «Мамочкой» или «Папочкой», «Госпожой» или «Господином», либо «Мадам» или «Сэр», то [npc.she] автоматически переключится на соответствующее парное имя в зависимости от пола вашего персонажа.</i></p></div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
	};
	
	private static boolean isFreedSlaveAvailableAsGuest() {
		return characterSelected().isAffectionHighEnoughToInviteHome()
				&& Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)
				&& OccupancyUtil.isFreeRoomAvailableForOccupant();
	}

	public static final DialogueNode SET_SLAVE_FREE_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeSlave(characterSelected());
			characterSelected().getPetNameMap().remove(Main.game.getPlayer().getId());// Reset pet name
			Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
			((Scarlett)Main.game.getNpc(Scarlett.class)).resetName();
			((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();

			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_SCARLETT", characterSelected()));
			Main.game.getDialogueFlags().setManagementCompanion(null);
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
			if (index == 1) {
				return new Response("Продолжить",
						"Поскольку Scarlett убежала, вам остается только продолжать свой день...",
						Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	private static boolean freedSlaveDeleted;
	
	public static final DialogueNode SET_SLAVE_FREE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_slavery_freedom_certification"));
			Main.game.getPlayer().removeSlave(characterSelected());
			characterSelected().setEnslavementDialogue(SlaveDialogue.FREEDOM_DIALOG, false);
			if(!isFreedSlaveAvailableAsGuest()) {
				freedSlaveDeleted = true;
				if(!characterSelected().isAffectionHighEnoughToInviteHome()) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_DISLIKE", characterSelected()));
					
				} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION) || !OccupancyUtil.isFreeRoomAvailableForOccupant()) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_NO_GUEST", characterSelected()));
					
				}
				Main.game.banishNPC(characterSelected());
				Main.game.getDialogueFlags().setManagementCompanion(null);
				
			} else {
				freedSlaveDeleted = false;
				characterSelected().getPetNameMap().remove(Main.game.getPlayer().getId());// Reset pet name
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GUEST_CHOICE", characterSelected()));
				Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
			}
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
			if(freedSlaveDeleted) {
				if(index == 1) {
					return new Response("Продолжить",
							"Теперь, когда твоя рабыня освободилась и навсегда покинула твою жизнь, тебе остается лишь продолжать реализацию остальных планов на этот день...",
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
						}
					};
				}
			} else {
				if(index == 1) {
					return new Response("Предложить комнату",
							UtilText.parse(characterSelected(), "Спросить [npc.name], хочет ли [npc.she] остаться в особняке в качестве гостя."),
							SET_SLAVE_FREE_GUEST_ROOM) {
						@Override
						public void effects() {
							Cell c = OccupancyUtil.getFreeRoomForOccupant();
							characterSelected().setLocation(c.getType(), c.getLocation(), true);
							Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
							Main.game.getPlayer().addFriendlyOccupant(characterSelected());
							Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
						}
					};
					
				} else if(index == 2) {
					return new Response("Прощай",
							UtilText.parse(characterSelected(), "Попрощаться с [npc.name]...<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
							SET_SLAVE_FREE_END_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GOODBYE", characterSelected()));
							Main.game.banishNPC(characterSelected());
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
					
				} else if(index == 3) {
					return new Response("Вышвырнуть",
							UtilText.parse(characterSelected(), "Позвать Роуз, чтобы она бесцеремонно выставила [npc.name] из особняка...<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
							SET_SLAVE_FREE_END_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_THROWN_OUT", characterSelected()));
							Main.game.banishNPC(characterSelected());
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode SET_SLAVE_FREE_END_NO_CONTENT = new DialogueNode("", "", false) {
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SET_SLAVE_FREE_GUEST_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GUEST_ROOM", characterSelected());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", UtilText.parse(characterSelected(), "Оставив [npc.name] обустраиваться в новой комнате [npc.her], ты продолжаешь следовать своим планам на день..."), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
					}
				};
			}
			return null;
		}
	};
	
	
}
