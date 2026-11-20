package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.fields.ElisAlleywayAttacker;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveCategory;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.EncounterType;
import com.lilithsthrone.game.dialogue.encounters.ExternalEncounterData;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.RandomEnchantment;
import com.lilithsthrone.game.inventory.enchanting.RandomPotionEnchantment;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.AbstractOutfit;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * What a mess...
 * 
 * @since 0.1.0
 * @version 0.4.10.10
 * @author Innoxia
 */
public class DebugDialogue {

	private static String dollID;
	
	private static Femininity filterFemininity = Femininity.ANDROGYNOUS;
	
	private static GameCharacter targetedCharacter;
	
	public static final DialogueNode DEBUG_MENU = new DialogueNode("Мощный инструмент", "Открыть меню отладки.", false) {
//		@Override
//		public void applyPreParsingEffects() {
//			targetedCharacter = null; // Reset this value when opening the debug menu to make sure that the targetedCharacter is not from a previous save file
//		}
		@Override
		public void applyPreParsingEffects() {
			// Just in case this dialogue node is accessed before initialising targetedCharacter, or if targetedCharacter is no longer present:
			if(targetedCharacter==null
					|| (!targetedCharacter.isPlayer() && !Main.game.getCharactersPresent().contains(targetedCharacter))) {
				targetedCharacter = Main.game.getPlayer();
			}
		}
		
		@Override
		public String getContent() {
			return "<p>Как только вы произносите магическое слово, за вашей спиной внезапно раздается негромкий глухой звук. Обернувшись, вы видите на полу маленькое металлическое устройство, отдаленно напоминающее пульт от телевизора из вашего старого мира.</p><p>Вы наклоняетесь и поднимаете его; вертя в руках, вы замечаете на задней стороне маленькую наклейку. Кто-то оставил на ней послание, и вы читаете следующее:</p><p style='text-align:center;'><i>Привет, [pc.name]!<br/>Похоже, тебе известен магический код отладки! Просто предупреждаю: все настройки здесь дико глючные! Если ты заспавнишь какую-нибудь одежду или предметы, имей в виду, что некоторые из них еще официально не добавлены в игру, так что они могут работать не совсем так, как ожидается. Спасибо за игру!<br/><br/>~Инноксия~<br/></i></p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Главное";
				
			} else if(index == 1) {
				return "Статы";
				
			} else if(index == 2) {
				return "Разное";

			} else if(index == 3) {
				return "Просмотр предметов";
				
			} else if(index == 4) {
				return "Личность";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Назад", "", DEBUG_MENU){
					@Override
					public DialogueNode getNextDialogue() {
						if(Main.game.isInSex()) {
							return Main.sex.SEX_DIALOGUE;
						}
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Открыть парсер", "Протестировать парсер.", PARSER);
					
				} else if (index == 2) {
					return new Response("Режим отладки: ", "Разблокирует зачарование и рабство без необходимости предварительного выполнения соответствующих квестов.", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Режим отладки: "+(Main.game.isDebugMode()?"[style.colourGood(ВКЛ)]":"[style.colourDisabled(OFF)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.debugMode, !Main.game.isDebugMode());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if (index == 3) {
					return new Response("Открыть карты: ", "Открывает все клетки карты.", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Открыть карты: "+(Main.game.isMapReveal()?"[style.colourGood(ВКЛ)]":"[style.colourDisabled(OFF)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.mapReveal, !Main.game.isMapReveal());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if (index == 4) {
					return new Response("Открыть тела: ",
							"При включении одежда не скрывает слоты инвентаря, и вы будете знать, как выглядят гениталии всех персонажей, даже не видя их. Это также открывает нагие изображения и изображения в нижнем белье для уникальных NPC.",
							DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Открыть тела: "+(Main.game.isConcealedSlotsReveal()?"[style.colourGood(ВКЛ)]":"[style.colourDisabled(OFF)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.concealedSlotsReveal, !Main.game.isConcealedSlotsReveal());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if (index == 5) {
					if(!Util.newArrayListOfValues(
							PlaceType.DOMINION_BACK_ALLEYS,
							PlaceType.DOMINION_CANAL,
							PlaceType.DOMINION_CANAL_END,
							PlaceType.DOMINION_ALLEYS_CANAL_CROSSING,
							PlaceType.SUBMISSION_TUNNELS,
							PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"),
							PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_abandoned_bakery")
							).contains(Main.game.getPlayer().getLocationPlace().getPlaceType())) {
						return new Response("Заспавнить атакующего", "Вы можете призвать атакующего только на: плитках переулков и каналов Dominion; плитках туннелей Submission; плитках переулков Элис.", null);
					}
					if(!Main.game.getNonCompanionCharactersPresent().isEmpty()) {
						return new Response("Заспавнить атакующего", "Вы можете призвать атакующего только на пустых плитках.", null);
					}
					return new Response("Заспавнить атакующего", "Призвать атакующего на эту плитку.", ATTACKER_SPAWN_MENU);
					
				} else if (index == 6) {
					return new Response("Меню призыва", "Просмотреть меню призыва одежды, оружия и предметов.", SPAWN_MENU);
					
				} else if (index == 7) {
					return new Response("Трансформация", "Преобразовать свое тело.", BodyChanging.BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer(), true);
						}
					};
					
				} else if (index == 8) {
					return new Response("Установить материал тела", "Установить материал вашего тела.", BODY_PART_MATERIAL);
					
				} else if (index == 9) {
					return new Response("Сброс расы", "Просмотреть настройки сброса расы.", BODY_PART_RACE_RESET);
					
				} else if (index == 10) {
					return new Response("Установить спавн", "Просмотреть все наборы одежды/оружия в игре и заспавнить их.", SPAWN_MENU_SET);
					
				} else if (index == 11) {
					return new Response(UtilText.formatAsMoney(100_000, "охват"), "Добавить 100 000 огней.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(100_000);
						}
					};
					
				} else if (index == 12) {
					return new Response("+1000 эссенций", "Добавить 1000 арканных эссенций.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementEssenceCount(1000, false);
						}
					};
					
				} else if (index == 13) {
//					return new Response("Very long action text for testing", "Very long action text for testing.", null);
					return new Response("Встречи", "Просмотреть все встречи, которые могут произойти на этой клетке.", ENCOUNTER_MENU);
					
				} else if (index == 14) {
					return new Response("Разблокировка стикеров: ",
							"Разблокирует доступность всех стикеров для одежды. Это делается путем игнорирования текстов unavailabilityText и availabilityText, так что если вы пытаетесь протестировать их, включение этой функции создаст проблемы!",
							DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Разблокировка стикеров: "+(Main.game.isAllStickersUnlocked()?"[style.colourGood(ВКЛ)]":"[style.colourDisabled(ВЫКЛ)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.allStickersUnlocked, !Main.game.isAllStickersUnlocked());
							Main.getProperties().savePropertiesAsXML();
						}
					};
					
				} else if(index==15) {
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE)) {
						return new Response("Пропустить квесты Dominion", "Вы уже завершили все основные квесты, ведущие к цепочке квестов Submission!", null);
						
					} else {
						return new Response("Пропустить квесты Dominion", "Пропустить все основные квесты до начала цепочки квестов Submission.", DEBUG_MENU){
							@Override
							public void effects() {
								List<Quest> dominionSkipQuests = Util.newArrayListOfValues(
										Quest.MAIN_1_A_LILAYAS_TESTS,
										Quest.MAIN_1_B_DEMON_HOME,
										Quest.MAIN_1_C_WOLFS_DEN,
										Quest.MAIN_1_D_SLAVERY,
										Quest.MAIN_1_E_REPORT_TO_HELENA,
										Quest.MAIN_1_F_SCARLETTS_FATE,
										Quest.MAIN_1_G_SLAVERY,
										Quest.MAIN_1_H_THE_GREAT_ESCAPE,
										Quest.MAIN_1_I_ARTHURS_TALE,
										Quest.MAIN_2_A_INTO_THE_DEPTHS
										);
								for(int i=0; i<dominionSkipQuests.size()-1; i++) {
									Quest q = dominionSkipQuests.get(i);
									if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==q) {
										q.applySkipQuestEffects();
										Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, dominionSkipQuests.get(i+1));
									}
								}
							}
						};
					}
					
				} else if(index==16) {
					if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS)) {
						return new Response("Пропустить квесты Submission", "Вы еще не продвинулись достаточно далеко по основному квесту, чтобы начать пропускать квесты Submission!", null);
						
					} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
						return new Response("Пропустить квесты Submission", "Вы уже завершили все основные квесты, ведущие к цепочке квестов Elis!", null);
						
					} else {
						return new Response("Пропустить квесты Submission", "Пропустить все основные квесты до начала цепочки квестов Элис.", DEBUG_MENU){
							@Override
							public void effects() {
								List<Quest> submissionSkipQuests = Util.newArrayListOfValues(
										Quest.MAIN_2_A_INTO_THE_DEPTHS,
										Quest.MAIN_2_B_SIRENS_CALL,
										Quest.MAIN_2_C_SIRENS_FALL,
										Quest.MAIN_2_D_MEETING_A_LILIN,
										Quest.MAIN_3_ELIS
										);
								for(int i=0; i<submissionSkipQuests.size()-1; i++) {
									Quest q = submissionSkipQuests.get(i);
									if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==q) {
										q.applySkipQuestEffects();
										Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, submissionSkipQuests.get(i+1));
									}
								}
							
							}
						};
					}
					
				} else if(index==25) {
					return new Response("Зачарованная одежда", "Показать список всей случайно зачарованной одежды, которая может выпасть.", RANDOM_ENCHANTMENT_CLOTHING);
					
				} else if(index==26) {
					return new Response("Зачарованные зелья", "Показать список всех случайно зачарованных зелий Йоко, которые могут выпасть.", RANDOM_ENCHANTMENT_POTIONS);
				}
				
				
			} else if(responseTab==1) {
				if (index == 1) {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+";'>+500 xp</span> (себе)", "Дать себе 500 ед. опыта.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementExperience(500, false);
						}
					};
					
				} else if (index == 2) {
					if(!Main.game.getPlayer().hasCompanions()) {
						return new Response("+500 xp (группе)", "У вас нет спутников, так что давать опыт некому...", null);
					}
					return new Response("<span style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+";'>+500 xp</span> (группе)", "Дать каждому члену отряда по 500 ед. опыта.", DEBUG_MENU){
						@Override
						public void effects() {
							for(GameCharacter character : Main.game.getPlayer().getParty()) {
								character.incrementExperience(500, false);
							}
						}
					};
					
				} else if(index==3) {
					return new Response("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>Телосложение</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, 5);
						}
					};
					
				} else if(index==4) {
					return new Response("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+PresetColour.ATTRIBUTE_ARCANE.toWebHexString()+";'>Arcane</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, 5);
						}
					};
					
				} else if(index==5) {
					return new Response("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>Порочность</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, 5);
						}
					};
					
				} else if(index==6) {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>Максимизировать все атрибуты</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_ARCANE, 100);
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, 100);
						}
					};
					
				}  else if(index==7) {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>+1</span> <span style='color:"+PresetColour.PERK.toWebHexString()+";'>Очко навыка</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementPerkPoints(1);
						}
					};
					
				} else if(index==8) {
					return new Response("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>Телосложение</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, -5);
						}
					};
				} else if(index==9) {
					return new Response("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+PresetColour.ATTRIBUTE_ARCANE.toWebHexString()+";'>Arcane</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, -5);
						}
					};
				} else if(index==10) {
					return new Response("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>Порочность</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
						}
					};
					
				} else if (index == 11) {
					return new Response("Сбросить заклинания", "Сбрасывает все ваши заклинания и улучшения, а также удаляет все очки улучшения заклинаний.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().resetSpells();
							Main.game.getPlayer().clearSpellUpgradePoints();
							
						}
					};
					
				} else if (index == 12) {
					return new Response("+10 очков заклинаний", "Добавляет по 10 очков заклинаний в каждую школу магии.", DEBUG_MENU){
						@Override
						public void effects() {
							for(SpellSchool school : SpellSchool.values()) {
								Main.game.getPlayer().incrementSpellUpgradePoints(school, 10);
							}
						}
					};
					
				} else if (index == 13) {
					return new Response("[style.colourGood(Завершено)] Общая энциклопедия", "Разблокирует все записи в вашей общей энциклопедии.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.getProperties().completeSharedEncyclopedia();
							Main.saveProperties();
						}
					};
					
				} else if (index == 14) {
					return new Response("[style.colourBad(Reset)] Общая энциклопедия", "Удалить все записи из вашей общей энциклопедии.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.getProperties().resetRaceDiscovered();
							Main.getProperties().resetAdvancedRaceKnowledge();
							Main.getProperties().resetItemDiscovered();
							Main.getProperties().resetClothingDiscovered();
							Main.getProperties().resetWeaponDiscovered();
								
							Main.saveProperties();
						}
					};
					
				} else if (index == 15) {
					return new Response("Сбросить девственность", "Удаляет всю информацию о потере девственности, а также сбрасывает состояние всех отверстий на «девственное».", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().completeVirginityReset();
						}
					};
					
				}
				
			} else if(responseTab==2) {
				if (index == 1) {
					return new Response("Тест цветов", "Тест текста на читаемость", COLOURS){
						@Override
						public void effects() {
							coloursSB = new StringBuilder("<p>");
							for (Colour c : PresetColour.getAllPresetColours()) {
								coloursSB.append(c.getId() + ": <span style='color:" + c.toWebHexString() + ";'>Тестовый текст для проверки читаемости.</span><br/>");
							}
							coloursSB.append("<br/><br/>");
							for (BaseColour bc : BaseColour.values()) {
								coloursSB.append(bc.toString() + ": <span style='color:" + bc.toWebHexString() + ";'>Тестовый текст для проверки читаемости.</span><br/>");
							}
							coloursSB.append("</p>");
							
						}
					};
					
				} else if (index == 2) {
					return new Response("Отпрыск", "Посмотреть доступных отпрысков", OFFSPRING);
					
				} else if (index == 3) {
					return new Response("[style.boldBad(Месяц -)]", "Уменьшить текущий месяц на 1.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().minus(1));
						}
					};
					
				} else if (index == 4) {
						return new Response("[style.boldGood(Месяц +)]", "Увеличить текущий месяц на 1.", DEBUG_MENU){
							@Override
							public void effects() {
								Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().plus(1));
							}
						};
						
				}
				else if (index == 5) {
					return new Response("Боевые приемы", "Просмотреть список всех боевых приемов, доступных в игре.", COMBAT_MOVES);
					
//					if(!Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BACK_ALLEYS)) {
//						return new Response("Lumi test", "Lumi can only be spawned in alleyway tiles.", null);
//						
//					} else if(!Main.game.getNonCompanionCharactersPresent().isEmpty()) {
//						return new Response("Lumi test", "Lumi can only be spawned into empty tiles!", null);
//						
//					}  else if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
//						return new Response("Lumi test", "Lumi can not be spawned during an arcane storm.", null);
//					}
//					return new ResponseEffectsOnly("Lumi test", "Spawn Lumi to test her dialogue and scenes."){
//						@Override
//						public void effects() {
//							Main.game.setContent(new Response("", "", LumiDialogue.LUMI_APPEARS));
//						}
//					};
					
				} 
				else if (index == 6) {
					return new Response("Месть Brax", "Brax кончает тебе во влагалище!", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().guaranteePregnancyOnNextRoll();
							if(Main.game.getPlayer().hasHymen()) {
								Main.game.getPlayer().setVaginaVirgin(false);
								SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
								Main.game.getPlayer().setVirginityLoss(sexType, Main.game.getNpc(Brax.class),"пока вы развлекаетесь в меню отладки");
							}
							Main.game.getPlayer().ingestFluid(Main.game.getNpc(Brax.class), Main.game.getNpc(Brax.class).getCum(), SexAreaOrifice.VAGINA, 1000);
						}
					};
					
				} else if (index == 7) {
					return new Response("Лицемерие Lilaya", "Lilaya кончает тебе во влагалище!", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().guaranteePregnancyOnNextRoll();
							if(Main.game.getPlayer().hasHymen()) {
								Main.game.getPlayer().setVaginaVirgin(false);
								SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
								Main.game.getPlayer().setVirginityLoss(sexType, Main.game.getNpc(Lilaya.class), "пока вы развлекаетесь в меню отладки");
							}
							Main.game.getPlayer().ingestFluid(Main.game.getNpc(Lilaya.class), Main.game.getNpc(Lilaya.class).getCum(), SexAreaOrifice.VAGINA, 1000);
						}
					};
					
				} else if (index == 8) {
					return new Response("Испытания Lilaya", "Автоматически завершает квест Lilaya на зачарование, позволяя вам зачаровывать предметы прямо сейчас.", DEBUG_MENU){
						@Override
						public void effects() {
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)){ //If the player hasn't completed the enchantment quest
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)){ //But has started it
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE)); //Finish it
								}
								else{ //But hasn't started it
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)); //Start the quest
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE)); //And finish it
								}
							}
						}
					};

				} else if (index == 9) {
					return new Response("+1 штамп Эпоны", "Дает один штамп, который можно получить, играя в рулетку заводчика.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().eponaStamps += 1;
							Main.game.getTextEndStringBuilder().append("Добавлено 1 штамп, теперь у вас "  + Main.game.getDialogueFlags().eponaStamps + " штамп(ов)");
						}
					};

				} else if(index == 10){
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLAVERY)) { //If the player doesn't have the slaver license
						return new Response("Получить лицензию работорговца", "Автоматически завершает квест на получение лицензии работорговца. Если квеста еще нет, он запустится и тут же завершится.", DEBUG_MENU){
							@Override
							public void effects() {
								if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)){
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY));
								}
								List<Quest> slaverSkipQuests = Util.newArrayListOfValues(
										Quest.SIDE_SLAVER_NEED_RECOMMENDATION,
										Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED,
										Quest.SIDE_UTIL_COMPLETE);
								for(int i=0; i<slaverSkipQuests.size()-1; i++) {
									Quest q = slaverSkipQuests.get(i);
									if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY)==q) {
										q.applySkipQuestEffects();
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, slaverSkipQuests.get(i+1)));
									}
								}
							}
						};
						
					} else {
						return new Response("Получить лицензию работорговца", "Вы уже выполнили побочный квест на получение лицензии работорговца.", null);
					}
					
				} else if(index == 11){
					return new Response("Кентавр", "Появляется дикий кентавр! (Пожалуйста, используйте это только на полностью нейтральной клетке, иначе это, скорее всего, сломает игру.)", CENTAUR_SEX){
						@Override
						public void effects(){
							NPC target = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false,  false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s!=Subspecies.CENTAUR);
							try {
								Main.game.addNPC(target, false);
								Main.game.setActiveNPC(target);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
				} else if (index == 12) {
					return new Response("[style.boldMinorBad(День -)]", "Уменьшить текущий день на 1.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.incrementStartingDateDays(-1);
						}
					};
					
				} else if (index == 13) {
						return new Response("[style.boldMinorGood(День +)]", "Увеличить текущий день на 1.", DEBUG_MENU){
							@Override
							public void effects() {
								Main.game.incrementStartingDateDays(1);
							}
						};
						
				} else if (index == 14) {
					return new Response("+1000 очков [style.mule]", "Дает вам максимальное количество очков [style.mule] (можно использовать после получения статуса [style.mule] в Dominion Express).", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(1000));
						}
					};
					
				} else if(index==15) {
					return new Response("Бимбо!", "Превращает всех женских NPC в игре в пышногрудых, неряшливых бимбо.<br/>[style.italicsBad(Внимание! Это действие нельзя отменить!)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_PINK_DEEP;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								if(npc.isFeminine()) {
									npc.addFetish(Fetish.FETISH_BIMBO);
									npc.addPersonalityTrait(PersonalityTrait.SLOVENLY);
									if(npc.getBreastSize().getMeasurement()<CupSize.E.getMeasurement()) {
										npc.setBreastSize(CupSize.E);
									}
								}
							}
						}
					};
					
				} else if(index==16) {
					return new Response("Режим тигра", "Превращает каждого NPC в игре в высшего тигр-морфа.<br/>[style.italicsBad(Внимание! Это нельзя отменить!)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_CAT_MORPH_TIGER;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								Main.game.getCharacterUtils().reassignBody(
										npc,
										npc.getBody(),
										npc.getGender(),
										Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger"),
										RaceStage.GREATER,
										false);
							}
						}
					};
					
				} else if(index==17) {
					return new Response("Режим моф", "Каждый NPC с типом покрытия, поддерживающим модификатор «пушистость», получает модификатор «пушистость».<br/>[style.italicsBad(Внимание! Это нельзя отменить!)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_ROSE;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								for(BodyPartInterface part : npc.getBody().getAllBodyParts()) {
									AbstractBodyCoveringType bct = npc.getCovering(part);
									if(bct!=null
											&& (bct.getNaturalModifiers().contains(CoveringModifier.FLUFFY) || bct.getExtraModifiers().contains(CoveringModifier.FLUFFY))) {
										npc.getCovering(bct).setModifier(CoveringModifier.FLUFFY);
									}
								}
								
							}
						}
					};
					
				} else if(index==18) {
					return new Response("Режим му",
							"У каждого женского NPC размер груди увеличивается на 5, появляется фетиш «"+Fetish.FETISH_LACTATION_SELF.getName(null)+"', объем запаса грудного молока увеличивается на 500 мл, размер задницы увеличивается на 1, а размер бедер — на 1.<br/>[style.italicsBad(Внимание! Это нельзя отменить!)]",
							DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_COW_MORPH;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								if(npc.isFeminine()) {
									npc.addFetish(Fetish.FETISH_LACTATION_SELF);
									npc.incrementBreastMilkStorage(500);
									npc.incrementBreastSize(5);
									npc.incrementAssSize(1);
									npc.incrementHipSize(1);
								}
							}
						}
					};
				} else if(index==19) {
					return new Response("Режим крысы", "Превращает каждого NPC в игре в высшего крыс-морфа.<br/>[style.italicsBad(Внимание! Это нельзя отменить!)]", DEBUG_MENU){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_RAT_MORPH;
						}
						@Override
						public void effects() {
							for(NPC npc : Main.game.getAllNPCs()) {
								Main.game.getCharacterUtils().reassignBody(
										npc,
										npc.getBody(),
										npc.getGender(),
										Subspecies.RAT_MORPH,
										RaceStage.GREATER,
										false);
							}
						}
					};
					
				} else if(index>=20 && index<=26) {
					ArrayList<AbstractPerk> powerPerks = Util.newArrayListOfValues(Perk.POWER_OF_LIRECEA_1,
							Perk.POWER_OF_LOVIENNE_2,
							Perk.POWER_OF_LASIELLE_3,
							Perk.POWER_OF_LYSSIETH_4,
							Perk.POWER_OF_LUNETTE_5,
							Perk.POWER_OF_LYXIAS_6,
							Perk.POWER_OF_LISOPHIA_7);

					ArrayList<AbstractPerk> powerPerksDemon = Util.newArrayListOfValues(Perk.POWER_OF_LIRECEA_1_DEMON,
							Perk.POWER_OF_LOVIENNE_2_DEMON,
							Perk.POWER_OF_LASIELLE_3_DEMON,
							Perk.POWER_OF_LYSSIETH_4_DEMON,
							Perk.POWER_OF_LUNETTE_5_DEMON,
							Perk.POWER_OF_LYXIAS_6_DEMON,
							Perk.POWER_OF_LISOPHIA_7_DEMON);
					
					AbstractPerk perk = powerPerks.get(index-20);
					AbstractPerk perkDemon = powerPerksDemon.get(index-20);
					
					return new Response("Перк Старшей лилин", "Переключить перк.", DEBUG_MENU) {
						@Override
						public String getTitle() {
							return perk.getName(null)+": "+(Main.game.getPlayer().hasPerkAnywhereInTree(perk) || Main.game.getPlayer().hasPerkAnywhereInTree(perkDemon)?"[style.colourGood(ВКЛ)]":"[style.colourDisabled(ВЫКЛ)]");
						}
						
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPerkAnywhereInTree(perk) || Main.game.getPlayer().hasPerkAnywhereInTree(perkDemon)) {
								Main.game.getPlayer().removeSpecialPerk(perk);
								Main.game.getPlayer().removeSpecialPerk(perkDemon);
							} else {
								if(Main.game.getPlayer().getTrueRace()==Race.DEMON) {
									Main.game.getPlayer().addSpecialPerk(perkDemon);
								} else {
									Main.game.getPlayer().addSpecialPerk(perk);
								}
							}
						}
					};
					
				} else if(index==29)  {
					return new Response("Шансы появления", "Список шансов появления в текущей локации.", SPAWN_RATES) {
						@Override
						public void effects() {
							spawnrateSB = new StringBuilder("<table><tr><th>Название расы</th><th>Всего</th><th>Мужчины</th><th>Женщины</th><th>Название расы</th><th>Всего</th><th>Мужчины</th><th>Женщины</th></tr>");
							spawnTotal = 0;
							spawnTotalMasculine = 0;
							spawnTotalFeminine = 0;
							float spawn;
							float spawnMasculine;
							float spawnFeminine;
							for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
								if (Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).containsKey(s)) {
									spawn = (1000 * Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).get(s).getChanceMultiplier());
									spawnTotalMasculine += (spawn * Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s).getValue());
									spawnTotalFeminine += (spawn * Main.getProperties().getSubspeciesFemininePreferencesMap().get(s).getValue());
									spawnTotal = spawnTotalMasculine + spawnTotalFeminine;
								}
							}
							boolean even = false;
							for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
								if (Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).containsKey(s)) {
									spawn = (1000 * Subspecies.getWorldSpecies(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocationPlace().getPlaceType(), false).get(s).getChanceMultiplier());
									spawnMasculine = (spawn * Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s).getValue());
									spawnFeminine = (spawn * Main.getProperties().getSubspeciesFemininePreferencesMap().get(s).getValue());
									if (!even) {
										spawnrateSB.append("<tr>");
									}
									spawnrateSB.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(Util.capitaliseSentence(s.getNamePlural(null))).append("</td>")
												.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(String.format("%.02f", (((spawnMasculine + spawnFeminine) * 100) / spawnTotal))).append("%</td>")
												.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(String.format("%.02f", ((spawnMasculine * 100) / spawnTotalMasculine))).append("%</td>")
												.append("<td style='color:").append(s.getColour(null).toWebHexString()).append(";'>").append(String.format("%.02f", ((spawnFeminine * 100) / spawnTotalFeminine))).append("%</td>");
									if (even) {
										spawnrateSB.append("</tr>");
									}
									even = !even;
								}
							}
							if (!even) {
								spawnrateSB.append("</tr>");
							}
							spawnrateSB.append("</table>");
						}
					};
					
				} else if(index==30) {
					return new Response("Коллаж предметов", "Просмотреть коллаж всех иконок предметов, оружия и одежды, которые сейчас есть в игре.<br/>[style.italicsMinorBad(Загрузка и отображение могут занять много времени!)]", CLOTHING_COLLAGE);
					
				}
				
			} else if(responseTab == 3) {
				if(index==1) {
					String femName = "Нет";
					if(filterFemininity==Femininity.FEMININE) {
						femName = "Женственные";
					} else if(filterFemininity==Femininity.MASCULINE) {
						femName = "Мужественные";
					}
					return new Response("Фильтр: <span style='color:"+(filterFemininity.getColour().toWebHexString())+";'>"+femName+"</span>",
							"Переключить фильтр женственности для одежды.",
							Main.game.getCurrentDialogueNode()==DEBUG_MENU
								?DEBUG_MENU
								:ITEM_VIEWER) {
						@Override
						public void effects() {
							if(filterFemininity==Femininity.ANDROGYNOUS) {
								filterFemininity = Femininity.FEMININE;
							} else if(filterFemininity==Femininity.FEMININE) {
								filterFemininity = Femininity.MASCULINE;
							} else {
								filterFemininity = Femininity.ANDROGYNOUS;
							}
						}
					};

					// The StringBuilder gets too big and throws: java.lang.OutOfMemoryError: Java heap space
					// So replaced with filter above view
//					return new Response("All",
//							"View icons and ids of all the clothing, weapons, and items in the game. You can also spawn these items by clicking on their icons. <i>Warning: Very sluggish and slow to load.</i>",
//							ITEM_VIEWER) {
//						@Override
//						public void effects() {
//							viewItemVariablesReset();
//							viewAll = true;
//						}
//						@Override
//						public Colour getHighlightColour() {
//							return PresetColour.GENERIC_EXCELLENT;
//						}
//					};
					
				} else if(index==2) {
					return new Response("Предметы",
							"Просмотреть иконки и ID всех предметов в игре. Вы также можете спавнить эти предметы, кликая по их иконкам. <i>Внимание: Может работать медленно и долго загружаться.</i>",
							ITEM_VIEWER) {
						@Override
						public void effects() {
							viewItemVariablesReset();
						}
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_BLUE_LIGHT;
						}
					};
					
				} else if(index==3) {
					return new Response("Оружие",
							"Просмотреть иконки и ID всего оружия в игре. Вы также можете призвать эти предметы, нажимая на их иконки. <i>Внимание: процесс может быть медленным и долгим.</i>",
							ITEM_VIEWER) {
						@Override
						public void effects() {
							viewItemVariablesReset();
							itemViewSlot = InventorySlot.WEAPON_MAIN_1;
						}
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_CRIMSON;
						}
					};
					
				} else if(index==4) {
					// The StringBuilder gets too big and throws: java.lang.OutOfMemoryError: Java heap space
					// So replaced with tattoo view
//					return new Response("All clothing",
//							"View icons and ids of all the clothing in the game. You can also spawn these items by clicking on their icons. <i>Warning: Very sluggish and slow to load.</i>",
//							ITEM_VIEWER) {
//						@Override
//						public void effects() {
//							viewItemVariablesReset();
//							viewAllClothing = true;
//						}
//						@Override
//						public Colour getHighlightColour() {
//							return PresetColour.BASE_YELLOW;
//						}
//					};
					return new Response("Татуировки",
							"Просмотреть иконки и ID всех татуировок в игре. <i>Внимание: процесс может быть медленным и долгим.</i>",
							ITEM_VIEWER) {
						@Override
						public void effects() {
							viewItemVariablesReset();
							viewAllTattoos = true;
						}
					};
					
				} else if(index==5) {
					return new Response("Наряды",
							"Войти в генератор нарядов, позволяющий протестировать все игровые костюмы.",
							OUTFIT_VIEWER) {
						@Override
						public void effects() {
							BasicDoll doll = new BasicDoll();
							try {
								dollID = Main.game.addNPC(doll, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
							doll.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.HUMAN, RaceStage.GREATER, true);
							doll.setBodyMaterial(BodyMaterial.SILICONE);
							doll.setTailType(TailType.DEMON_COMMON);
							doll.setWingType(WingType.DEMON_COMMON);
							doll.setHornType(HornType.STRAIGHT);
							doll.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
							doll.setArmRows(3);
							
							doll.setPiercedEar(true);
							doll.setPiercedLip(true);
							doll.setPiercedNavel(true);
							doll.setPiercedNipples(true);
							doll.setPiercedNipplesCrotch(true);
							doll.setPiercedNose(true);
							doll.setPiercedPenis(true);
							doll.setPiercedTongue(true);
							doll.setPiercedVagina(true);
							
							doll.setName("Кукла для переодевания");
							doll.setLocation(Main.game.getPlayer());
							Main.game.setActiveNPC(doll);
						}
						@Override
						public Colour getHighlightColour() {
							return PresetColour.BASE_PINK_SALMON;
						}
					};
					
				} else {
					int indexOffset = 6;
					List<InventorySlot> clothingSlots = new ArrayList<>(Arrays.asList(InventorySlot.values()));
					clothingSlots.remove(InventorySlot.WEAPON_MAIN_1);
					clothingSlots.remove(InventorySlot.WEAPON_MAIN_2);
					clothingSlots.remove(InventorySlot.WEAPON_MAIN_3);
					clothingSlots.remove(InventorySlot.WEAPON_OFFHAND_1);
					clothingSlots.remove(InventorySlot.WEAPON_OFFHAND_2);
					clothingSlots.remove(InventorySlot.WEAPON_OFFHAND_3);
					
					if(index-indexOffset < clothingSlots.size()) {
						InventorySlot is = clothingSlots.get(index-indexOffset);
						return new Response(Util.capitaliseSentence(is.getName()),
								"Просмотреть иконки и ID всей одежды в слоте '"+is.getName()+"'. Вы также можете призвать эти предметы, нажимая на их иконки. <i>Внимание: процесс может быть очень медленным и долгим.</i>",
								ITEM_VIEWER) {
							@Override
							public void effects() {
								viewItemVariablesReset();
								itemViewSlot = is;
							}
						};
						
					}
//					else if(index-indexOffset == clothingSlots.size()) {
//						return new Response("Tattoos",
//								"View icons and ids of all the tattoos in the game. <i>Warning: May be sluggish and slow to load.</i>",
//								ITEM_VIEWER) {
//							@Override
//							public void effects() {
//								viewItemVariablesReset();
//								viewAllTattoos = true;
//							}
//						};
//					}
				}
				
			} else if(responseTab==4) {
				if(index==1) {
					return new ResponseEffectsOnly(UtilText.parse(targetedCharacter, "[npc.Name]"),
							UtilText.parse(targetedCharacter,
									"[npc.NameIsFull] текущую цель, и изменения личности будут применены к [npc.herHim].<br/><i>Нажмите, чтобы переключиться между персонажами, находящимися в этой клетке.</i>")) {
						@Override
						public Colour getHighlightColour() {
							return targetedCharacter.getGender().getColour();
						}
						@Override
						public void effects() {
							boolean preparedForNext = targetedCharacter.isPlayer();
							for(GameCharacter character : Main.game.getCharactersPresent()) {
								if(preparedForNext) {
									targetedCharacter = character;
									preparedForNext = false;
									break;
								}
								if(character.equals(targetedCharacter)) {
									preparedForNext = true;
								}
							}
							if(preparedForNext) {
								targetedCharacter = Main.game.getPlayer();
							}
						}
					};
				}
				
				List<PersonalityTrait> pt = Arrays.asList(PersonalityTrait.values());
				for(int i=2; i<=pt.size()+1;i++) {
					if(i==index) {
						PersonalityTrait perTr = pt.get(index-2);
						boolean hasTrait = targetedCharacter.hasPersonalityTrait(perTr);
						return new Response(
								hasTrait
									?"<b style='color:"+perTr.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(perTr.getName())+"</b>"
									:Util.capitaliseSentence(perTr.getName()),
									(hasTrait
										?"[style.boldGood(В наличии!)] "
										:"[style.colourMinorBad(Нет в наличии!)] ")
									+perTr.getDescription(targetedCharacter, true, true),
								DEBUG_MENU) {
							@Override
							public void effects() {
								if(hasTrait) {
									targetedCharacter.removePersonalityTrait(perTr);
								} else {
									targetedCharacter.addPersonalityTrait(perTr);
								}
							}
						};
					}
				}
				
				if(index==pt.size()+2) {
					return new Response("[style.colourMinorBad(Регенерировать)]",
								UtilText.parse(targetedCharacter, "Регенерировать черты [npc.namePos] личности на основе [npc.her] расы и истории."),
								DEBUG_MENU) {
						@Override
						public void effects() {
							targetedCharacter.clearPersonalityTraits();
							
							// Starting personalities based on race, copied from GameCharacter.additionalBodySetup():
							for(Entry<PersonalityTrait, Float> entry : targetedCharacter.getRace().getRacialBody().getPersonalityTraitChances().entrySet()) {
								double rnd = Math.random();
								if(rnd<=entry.getValue()) {
									targetedCharacter.addPersonalityTrait(entry.getKey());
								}
							}
			
							for(Entry<PersonalityTrait, Float> entry : targetedCharacter.getTrueSubspecies().getPersonalityTraitChances().entrySet()) {
								double rnd = Math.random();
								if(rnd<=entry.getValue()) {
									targetedCharacter.addPersonalityTrait(entry.getKey());
								}
							}
							
							if(targetedCharacter.hasPersonalityTrait(PersonalityTrait.MUTE)) { // If mute, remove all other speech traits
								targetedCharacter.removePersonalityTraits(PersonalityCategory.SPEECH);
								targetedCharacter.addPersonalityTrait(PersonalityTrait.MUTE);
							}
							
							// Roughly copying effects from CharacterUtils' setHistoryAndPersonality():
							if(targetedCharacter.getHistory()==Occupation.NPC_PROSTITUTE) {
								targetedCharacter.removePersonalityTrait(PersonalityTrait.PRUDE);
								targetedCharacter.removePersonalityTrait(PersonalityTrait.INNOCENT);
							}
							if(targetedCharacter.getHistory().isLowlife()) {
								if(Math.random()<0.25f) {
									targetedCharacter.addPersonalityTrait(PersonalityTrait.SLOVENLY);
								}
							}
						}
					};
				}
				

				
			}
			
			return null;
		}
	};
	private static StringBuilder coloursSB;
	public static final DialogueNode COLOURS = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			return coloursSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	private static NPC activeOffspring = null;
	
	public static final DialogueNode OFFSPRING = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for(NPC npc : Main.game.getOffspring()) {
				boolean isBorn = true;
				if(npc.getMother()!=null && npc.getMother().getPregnantLitter()!=null && npc.getMother().getPregnantLitter().getOffspring().contains(npc.getId())) {
					isBorn = false;
				}
				UtilText.nodeContentSB.append((isBorn?"":"(Еще не родился) ")+"<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName(true)+" "+npc.getSurname()+"</span>"
						+ " ("+npc.getSubspecies().getName(npc.getBody())+" | "+npc.getHalfDemonSubspecies().getName(npc.getBody())+")"
						+ " ("+npc.getCovering(npc.getBody().getTorsoType().getBodyCoveringType(npc.getBody())).getPrimaryColour().getName()+")" // Primary covering colour
						+ " M:"+(npc.getMother()!=null?npc.getMother().getName(true):"Удаленный NPC")
						+ " F:"+(npc.getFather()!=null?npc.getFather().getName(true):"Удаленный NPC")+"<br/>");
			}
			for(OffspringSeed os : Main.game.getOffspringNotSpawned(os -> true,true)) {
				if(!os.isBorn()) {
					UtilText.nodeContentSB.append("[style.colourBad(Еще не родился)] ");
				} else {
					UtilText.nodeContentSB.append("Еще не встречались ");
				}
				
				UtilText.nodeContentSB.append("<span style='color:"+os.getFemininity().getColour().toWebHexString()+";'>"+os.getName()+" "+os.getSurname()+"</span>");
				
				UtilText.nodeContentSB.append(" (<i style='color:"+os.getGender().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(os.getGender().getName())+"</i>)");
				
				UtilText.nodeContentSB.append(" ("+os.getSubspecies().getName(os.getBody()));
				if(os.getSubspecies()==Subspecies.HALF_DEMON) {
					UtilText.nodeContentSB.append("/"+os.getHalfDemonSubspecies().getName(os.getBody()));
				}
				UtilText.nodeContentSB.append(")");
				
				Colour primaryCoveringColour = os.getBody().getCovering(os.getBody().getTorsoType().getBodyCoveringType(os.getBody()), true).getPrimaryColour();
				UtilText.nodeContentSB.append(" (<span style='color:"+primaryCoveringColour.toWebHexString()+";'>"+primaryCoveringColour.getName()+"</span>)"); // Primary covering colour
				
				UtilText.nodeContentSB.append(" M:"+(os.getMother()!=null?os.getMother().getName(true):"Удаленный NPC"));
				UtilText.nodeContentSB.append(" F:"+(os.getFather()!=null?os.getFather().getName(true):"Удаленный NPC")+"<br/>");
			}
			if(activeOffspring!=null) {
				for(AbstractFetish f : activeOffspring.getFetishes(true)) {
					UtilText.nodeContentSB.append("<br/>[style.boldSex(Фетиш:)] "+f.getName(activeOffspring));
				}
				UtilText.nodeContentSB.append(
						"<br/>" + activeOffspring.getDescription()
						+"<br/>" + activeOffspring.getBodyDescription());
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else if(index-1 < Main.game.getOffspring().size()) {
				return new Response(Main.game.getOffspring().get(index-1).getName(true), "Открыть страницу персонажа этого отпрыска.", OFFSPRING) {
					@Override
					public void effects() {
						activeOffspring = Main.game.getOffspring().get(index-1);
						for(CoverableArea ca : CoverableArea.values()) {
							activeOffspring.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static List<AbstractClothingType> clothingTotal = new ArrayList<>();
	public static InventorySlot activeSlot = null;
	public static ItemTag itemTag = null;
	public static int spawnCount = 1;
	public static List<AbstractItemType> itemsTotal = new ArrayList<>();
	public static List<AbstractWeaponType> weaponsTotal = new ArrayList<>();
	public static List<AbstractTattooType> tattoosTotal = new ArrayList<>();
	static {
		clothingTotal.addAll(ClothingType.getAllClothing());
		clothingTotal.removeIf((c) -> c.getDefaultItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER) || c.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(clothingTotal, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		weaponsTotal.addAll(WeaponType.getAllWeapons());
		weaponsTotal.removeIf((w) -> w.getItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER) || w.getItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(weaponsTotal, (i1, i2) -> Main.game.getItemGen().generateWeapon(i1).getRarity().compareTo(Main.game.getItemGen().generateWeapon(i2).getRarity()));

		tattoosTotal.addAll(TattooType.getAllTattooTypes());
		Collections.sort(tattoosTotal, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		itemsTotal.addAll(ItemType.getAllItems());
		itemsTotal.removeIf((i) -> i.getItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER) || i.getItemTags().contains(ItemTag.CHEAT_ITEM));
		Collections.sort(itemsTotal, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
	}
	private static StringBuilder inventorySB = new StringBuilder();
	
	public static final DialogueNode SPAWN_MENU = new DialogueNode("Меню призыва", "Открыть меню спавна.", false) {
		@Override
		public String getHeaderContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='container-half-width'>");

			inventorySB.append(
					"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+ (itemTag==ItemTag.CHEAT_ITEM
							?"[style.boldGreenDark(Чит-предметы)]"
							:(activeSlot==null
								?"<b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Заспавнить предмет</b>"
								:(activeSlot.isWeapon()
									? "<b style='color:"+PresetColour.BASE_RED_LIGHT.toWebHexString()+";'>Заспавнить оружие</b> ("+Util.capitaliseSentence(activeSlot==InventorySlot.WEAPON_MAIN_1?"Ближний бой":"Дальний бой")+")"
									: "<b style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+";'>Заспавнить одежду</b> ("+Util.capitaliseSentence(activeSlot.getName())+")")))
					+"</p>");
			
			int count=0;
			inventorySB.append("<div class='inventory-not-equipped'>");
			if(itemTag==ItemTag.CHEAT_ITEM) {
				for(AbstractClothingType c : ClothingType.getAllClothing()) {
					if(c.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+c.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"
									+c.getSVGImage()
								+"</div>"
								+ "<div class='overlay' id='" + c.getId() + "_SPAWN'></div></div>");
					}
				}
				for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
					if(weaponType.getItemTags().contains(ItemTag.CHEAT_ITEM)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
								+"</div>"
								+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div></div>");
					}
				}
				for(AbstractItemType itemType : ItemType.getAllItems()) {
					if(itemType.getItemTags().contains(ItemTag.CHEAT_ITEM)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
								+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div></div>");
					}
				}
				
			} else if(activeSlot == null) {
				for(AbstractItemType itemType : itemsTotal) {
					if((itemTag==null
							&& (!itemType.getItemTags().contains(ItemTag.BOOK)
							&& !itemType.getItemTags().contains(ItemTag.ESSENCE)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_BOOK)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)))
							|| (itemTag!=null
								&& (itemType.getItemTags().contains(itemTag)
										|| (itemTag==ItemTag.SPELL_BOOK && itemType.getItemTags().contains(ItemTag.SPELL_SCROLL))))) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
												+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div></div>");
					}
					count++;
				}
				
			} else if(activeSlot.isWeapon()) {
				for(AbstractWeaponType weaponType : weaponsTotal) {
					if((weaponType.isMelee() && activeSlot==InventorySlot.WEAPON_MAIN_1)
							|| (!weaponType.isMelee() && activeSlot==InventorySlot.WEAPON_OFFHAND_1)) {
						Rarity rarity = Main.game.getItemGen().generateWeapon(weaponType).getRarity();
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+rarity.getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
												+"</div>"
												+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div></div>");
						count++;
					}
				}
				
			} else {
				for(AbstractClothingType clothingType : clothingTotal) {
					if(clothingType.getEquipSlots().contains(activeSlot)) {
						inventorySB.append("<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"
													+clothingType.getSVGImage()
												+"</div>"
												+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div></div>");
						count++;
					}
				}
			}
			
			// Fill space:
			for (int i = count; i <48; i++) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
			inventorySB.append("</div>"
					+ "</div>");
			
			inventorySB.append("<div class='container-half-width'>");
			for(InventorySlot slot : InventorySlot.values()) {
				if(slot!=InventorySlot.WEAPON_MAIN_2
						&& slot!=InventorySlot.WEAPON_MAIN_3
						&& slot!=InventorySlot.WEAPON_OFFHAND_2
						&& slot!=InventorySlot.WEAPON_OFFHAND_3) {
					inventorySB.append("<div class='normal-button' id='"+slot+"_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"
							+ (slot.isWeapon() ? PresetColour.BASE_RED_LIGHT.toWebHexString() : PresetColour.BASE_YELLOW_LIGHT.toWebHexString())+";'>"
							+(slot == InventorySlot.WEAPON_MAIN_1
								?"Ближний бой"
								:(slot == InventorySlot.WEAPON_OFFHAND_1
										?"Дальний бой"
										:Util.capitaliseSentence(slot.getName())))
							+"</div>");
				}
			}
			inventorySB.append("<div class='normal-button' id='ITEM_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Предметы</div>");
			inventorySB.append("<div class='normal-button' id='ESSENCE_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Эссенции</div>");
			inventorySB.append("<div class='normal-button' id='BOOK_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>Книги</div>");
			inventorySB.append("<div class='normal-button' id='SPELL_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Заклинания</div>");
			inventorySB.append("<div class='normal-button' id='HIDDEN_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; opacity:0; cursor:default; color:"+PresetColour.BASE_GREEN_DARK.toWebHexString()+";'>Читы</div>");

			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPAWN_MENU_SET = new DialogueNode("Установить спавн", "", false) {
		@Override
		public String getHeaderContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='container-full-width'>");
			
			List<AbstractSetBonus> bonuses = new ArrayList<>(SetBonus.allSetBonuses);
			bonuses.sort((sb1, sb2) -> sb1.getName().compareTo(sb2.getName()));
			
			for(AbstractSetBonus sb : bonuses) {
				inventorySB.append("<div class='normal-button' id='SET_BONUS_"+SetBonus.getIdFromSetBonus(sb)+"' style='text-align:center;width:23%; margin:1%; padding:2px; font-size:0.9em;'>");
				inventorySB.append("<b style='color:"+sb.getAssociatedStatusEffect().getColour().toWebHexString()+";'>#</b>");
				inventorySB.append(sb.getName());
				inventorySB.append("<b style='color:"+sb.getAssociatedStatusEffect().getColour().toWebHexString()+";'>#</b>");
				inventorySB.append("</div>");
			}
			
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	private static void viewItemVariablesReset() {
		viewAll = false;
		viewAllClothing = false;
		viewAllTattoos = false;
		itemViewSlot = null;
	}
	
	private static boolean viewAll = false;
	private static boolean viewAllClothing = false;
	private static boolean viewAllTattoos = false;
	private static InventorySlot itemViewSlot = null;
	
	public static final DialogueNode ITEM_VIEWER = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			int width = 33;
			if(Main.primaryStage.getWidth()>=1900) {
				width = 25;
			}
			int imgWidth = 15;
			
			if(!viewAllClothing && !viewAllTattoos && (viewAll || itemViewSlot == null)) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
						+ "<h5>Total items: "+itemsTotal.size()+"</h5>");
				for(AbstractItemType itemType : itemsTotal) {
					if((itemTag==null
							&& (!itemType.getItemTags().contains(ItemTag.BOOK)
							&& !itemType.getItemTags().contains(ItemTag.ESSENCE)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_BOOK)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)))
							|| (itemTag!=null
								&& (itemType.getItemTags().contains(itemTag)
										|| (itemTag==ItemTag.SPELL_BOOK && itemType.getItemTags().contains(ItemTag.SPELL_SCROLL))))) {
						sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
													+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
													+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div></div>"
												+ ItemType.getItemToIdMap().get(itemType)
											+ "</div>");
					}
				}
				sb.append("</div>");
			}

			if(viewAll || (itemViewSlot!=null && itemViewSlot.isWeapon())) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
						+ "<h5>Total weapons: "+weaponsTotal.size()+"</h5>");
				for(AbstractWeaponType weaponType : weaponsTotal) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
												+"</div>"
												+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div></div>"
											+ WeaponType.getIdFromWeaponType(weaponType)
										+ "</div>");
				}
				sb.append("</div>");
			}

			if(viewAll || viewAllClothing) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>");
				sb.append("<h5>");
					sb.append("Всего одежды: "+clothingTotal.size());
				sb.append("</h5>");
				for(AbstractClothingType clothingType : clothingTotal) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
											+ "<div class='inventory-icon-content'>"
												+clothingType.getSVGImage()
											+"</div>"
											+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div></div>"
										+ (clothingType.getPhysicalResistance()>0
												?"[style.boldPhysical("+UtilText.getShieldSymbol()+clothingType.getPhysicalResistance()+")] [style.colourGreenLight("+ClothingType.getIdFromClothingType(clothingType)+")]"
												:ClothingType.getIdFromClothingType(clothingType))
									+ "</div>");
				}
				sb.append("</div>");
				
			} else if(viewAllTattoos) {
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
						+ "<h5>Total tattoos: "+tattoosTotal.size()+"</h5>");
				for(AbstractTattooType tattooType : tattoosTotal) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+tattooType.getRarity().getBackgroundColour().toWebHexString()+";'>"
												+ "<div class='inventory-icon-content'>"
													+tattooType.getSVGImage(Main.game.getPlayer())
												+"</div>"
												+ "<div class='overlay' id='" + tattooType.getId() + "_SPAWN'></div></div>"
											+ TattooType.getIdFromTattooType(tattooType)
										+ "</div>");
				}
				sb.append("</div>");
				
			} else if(itemViewSlot!=null && !itemViewSlot.isWeapon()) {
				List<AbstractClothingType> clothingToDisplay = clothingTotal.stream().filter((c) -> c.getEquipSlots().get(0)==itemViewSlot).collect(Collectors.toList());
				int maxItems = clothingToDisplay.size();
				for(AbstractClothingType clothingType : new ArrayList<>(clothingToDisplay)) {
					if(clothingType.getCoreFemininityRestriction()!=null) {
						if(filterFemininity==Femininity.FEMININE && (!clothingType.getCoreFemininityRestriction().isFeminine() && clothingType.getCoreFemininityRestriction()!=Femininity.ANDROGYNOUS)) {
							clothingToDisplay.remove(clothingType);
						} else if(filterFemininity==Femininity.MASCULINE && (clothingType.getCoreFemininityRestriction().isFeminine() && clothingType.getCoreFemininityRestriction()!=Femininity.ANDROGYNOUS)) {
							clothingToDisplay.remove(clothingType);
						}
					}
				}
				
				sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>");
				sb.append("<h5>");
					sb.append("Всего слотов одежды: '"+itemViewSlot.getName()+"слот одежды: "+clothingToDisplay.size()+(filterFemininity!=Femininity.ANDROGYNOUS?"/"+maxItems:""));
					if(filterFemininity!=Femininity.ANDROGYNOUS) {
						sb.append("<br/>");
						sb.append("(<span style='color:"+filterFemininity.getColour().toWebHexString()+";'>"+(filterFemininity.isFeminine()?"Женственные":"Мужественные")+" Фильтр</span>)");
					}
				sb.append("</h5>");
				for(AbstractClothingType clothingType : clothingToDisplay) {
					sb.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:0; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
								+ "<div class='inventory-icon-content'>"
									+clothingType.getSVGImage()
								+"</div>"
								+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div></div>"
							+ ClothingType.getIdFromClothingType(clothingType)
						+ "</div>");
				}
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode OUTFIT_VIEWER = new DialogueNode("Просмотр наряда", "", true) {
		@Override
		public String getContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>Outfits: "+OutfitType.getAllOutfits().size()+"</h5>");
			int i=0;
			for(AbstractOutfit outfit : OutfitType.getAllOutfits()) {
				String id = OutfitType.getIdFromOutfitType(outfit);
				inventorySB.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

					inventorySB.append("<div class='container-full-width' style='position:relative; padding:0; margin:0; width:100%; background-color:#00000000; -webkit-user-select:auto; text-align:left;'>");
							inventorySB.append("<b>"+outfit.getName()+":</b> <i>"+outfit.getDescription()+"</i>");
							inventorySB.append("<br/>");
							inventorySB.append("ID: "+id);
							inventorySB.append("<br/>");
							inventorySB.append("Женственность: <span style='color:"+outfit.getFemininity().getColour().toWebHexString()+";'>"+outfit.getFemininity().toString()+"</span>");
							inventorySB.append("<br/>");
							inventorySB.append("Условие: <span style='font-family:monospace; font-size:0.85em; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; padding:2px;'>"+outfit.getConditional()+"</span>");
							
							inventorySB.append("<br/>");
							inventorySB.append("Конфигурации ног: ");
							if(outfit.getAcceptableLegConfigurations()!=null && !outfit.getAcceptableLegConfigurations().isEmpty()) {
								List<String> lcNames = new ArrayList<>();
								for(LegConfiguration lc : outfit.getAcceptableLegConfigurations()) {
									lcNames.add(lc.toString());
								}
								inventorySB.append(Util.stringsToStringList(lcNames, false));
							} else {
								inventorySB.append("[style.colourMinorGood(ANY)]");
							}
							
							inventorySB.append("<br/>");
							inventorySB.append("Типы нарядов: ");
							if(outfit.getOutfitTypes()!=null && !outfit.getOutfitTypes().isEmpty()) {
								List<String> otNames = new ArrayList<>();
								for(OutfitType ot : outfit.getOutfitTypes()) {
									otNames.add(ot.toString());
								}
								inventorySB.append(Util.stringsToStringList(otNames, false));
							} else {
								inventorySB.append("[style.colourBad(НИЧЕГО!)]");
							}
							inventorySB.append("<div class='normal-button' id='OUTFIT_"+id+"' style='position:absolute; text-align:center; width:18%; margin:1%; right:2px; bottom:2px; padding:2px; font-size:0.9em; color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Применить</div>");
					inventorySB.append("</div>");
					
					
				inventorySB.append("</div>");
				i++;
			}
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Назад", "Вернуться в главное меню отладки.", DEBUG_MENU) {
					@Override
					public void effects() {
						Main.game.banishNPC(dollID);
					}
				};
			}
			return null;
		}
	};
	
	public static void applyOutfitToDoll(AbstractOutfit outfit) {
		try {
			NPC doll = (NPC) Main.game.getNPCById(dollID);
			
			doll.resetInventory(true);
			if(outfit.getAcceptableLegConfigurations()!=null
					&& !outfit.getAcceptableLegConfigurations().isEmpty()
					&& !outfit.getAcceptableLegConfigurations().contains(doll.getLegConfiguration())) {
				doll.setLegConfiguration(outfit.getAcceptableLegConfigurations().get(0), true);
			}
			if(doll.getLegConfiguration()!=LegConfiguration.BIPEDAL && (outfit.getAcceptableLegConfigurations()==null || outfit.getAcceptableLegConfigurations().isEmpty())) {
				doll.setLegConfiguration(LegConfiguration.BIPEDAL, true);
			}
			
			outfit.applyOutfit(doll, EquipClothingSetting.getAllClothingSettings());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static final DialogueNode BODY_PART_MATERIAL = new DialogueNode("Установить материал тела", "Установить материал тела.", false) {

		@Override
		public String getContent() {
			return "<p>Выберите тип материала.</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < BodyMaterial.values().length+1) {
				return new Response(Util.capitaliseSentence(BodyMaterial.values()[index - 1].getName()), "Сделать ваше тело из "+BodyMaterial.values()[index - 1].getName()+".", BODY_PART_MATERIAL){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	public static Map<AbstractEncounter, Float> encounters;
	public static final DialogueNode ENCOUNTER_MENU = new DialogueNode("Доступные встречи", "", false) {
		@Override
		public void applyPreParsingEffects() {
			encounters = Main.game.getPlayerCell().getPlaceType().getPossibleEncountersMap(true);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>Encounters: "+encounters.size()+"</h5>");
			int i=0;
			for(Entry<AbstractEncounter, Float> entry : encounters.entrySet()) {
				AbstractEncounter encounter = entry.getKey();
				float chance = entry.getValue();
				String id = encounter.getId();
				
				sb.append("<div class='container-full-width' style='border:1px solid "+(encounter.isFromExternalFile()?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_MINOR_BAD).toWebHexString()+"; width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

					sb.append("<div class='container-full-width' style='position:relative; padding:0; margin:0; width:100%; background-color:#00000000; -webkit-user-select:auto; text-align:left;'>");
						if(encounter.isFromExternalFile()) {
							sb.append(UtilText.parse("[style.colourGreenLight(MOD)] "));
						} else {
							sb.append(UtilText.parse("[style.colourRedLight(RES)] "));
						}
						sb.append("<b>"+id+": "+chance+"%</b>");
						sb.append("<br/>");

						sb.append("Встречи:");
						if(encounter.isFromExternalFile()) {
							int encounterIndex=0;
							int rowCount=0;
							for(ExternalEncounterData data : encounter.getPossibleEncounters()) {
								float triggerChance = data.getTriggerChance();
								rowCount++;
								sb.append("<div class='container-full-width' style='margin:2px 0; padding:4px; width:100%;position:relative;"
										+ " background:"+(rowCount%2==0?PresetColour.BACKGROUND.getShades()[3]:PresetColour.BACKGROUND_ALT.getShades()[3])+";'>");
									sb.append(data.getName()+": "+(triggerChance==0?"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>":"<span>")+triggerChance+"%</span>");
									sb.append("<br/>");
									sb.append("Условие: <span style='font-family:monospace; font-size:0.85em; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; padding:2px;'>"+data.getTriggerConditional()+"</span>");
									sb.append("<br/>");
									sb.append("Запуск диалога: "+data.getDialogueId());
									sb.append("<div class='normal-button' id='DEBUG_ENCOUNTER_"+id+"_"+encounterIndex+"' style='position:absolute; text-align:center; width:18%; height:auto; margin:1%; top:-8px; right:2px; padding:2px; font-size:0.9em; color:"+(triggerChance==0?PresetColour.GENERIC_BAD:PresetColour.GENERIC_MINOR_GOOD).toWebHexString()+";'>");
										sb.append("Триггер");
									sb.append("</div>");
								sb.append("</div>");
								encounterIndex++;
							}
							
						} else {
							int rowCount = 0;
							for(Entry<EncounterType, Float> typeEntry : encounter.getDialogues().entrySet()) {
								rowCount++;
								EncounterType et = typeEntry.getKey();
								float etChance = typeEntry.getValue();
								sb.append("<div class='container-full-width' style='margin:2px 0; padding:4px; width:100%;position:relative;"
										+ " background:"+(rowCount%2==0?PresetColour.BACKGROUND.getShades()[3]:PresetColour.BACKGROUND_ALT.getShades()[3])+";'>");
									sb.append(et.toString()+": "+etChance+"%");
									sb.append("<div class='normal-button' id='DEBUG_ENCOUNTER_"+id+"_"+et+"' style='position:absolute; text-align:center; width:18%; height:auto; margin:1%; top:-8px; right:2px; padding:2px; font-size:0.9em; color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>");
										sb.append("Триггер");
									sb.append("</div>");
								sb.append("</div>");
							}
						}
						
						
//							sb.append("Conditional: <span style='font-family:monospace; font-size:0.85em; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; padding:2px;'>"+outfit.getConditional()+"</span>");
							
					sb.append("</div>");
					
				sb.append("</div>");
				i++;
			}
			sb.append("</div>");
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	private static NPC attacker;
	private static RaceStage attackerRaceStage;
	private static AbstractSubspecies attackerSubspecies;
	private static AbstractSubspecies attackerHalfDemonSubspecies;
	private static void initAttacker() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION) {
			attacker = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false));
		} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley")
				|| Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_abandoned_bakery")) {
			attacker = new ElisAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false));
		} else {
			attacker = new SubmissionAttacker(Gender.getGenderFromUserPreferences(false, false));
		}
		
		try {
			Main.game.addNPC(attacker, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		attacker.setLocation(Main.game.getPlayer(), true);
		Main.game.setActiveNPC(attacker);
	}
	
	public static final DialogueNode ATTACKER_SPAWN_MENU = new DialogueNode("Создать атакующего", "", false) {
		@Override
		public void applyPreParsingEffects() {
			attacker = null;
			attackerSubspecies = Subspecies.HUMAN;
			attackerHalfDemonSubspecies = Subspecies.HUMAN;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
				sb.append("Выберите расу атакующего.");
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(Частичная)]";

			} else if(index == 1) {
				return "[style.colourTfMinor(Незначительная)]";
				
			} else if(index == 2) {
				return "[style.colourTfLesser(Малая)]";
				
			} else if(index == 3) {
				return "[style.colourTfGreater(Сильная)]";
				
			} else if(index == 4) {
				return "[style.colourHalfDemon(Полудемон)]";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>(Subspecies.getAllSubspecies());
			availableSubspecies.removeIf(s->
					s.getRace()==Race.ELEMENTAL
//					|| s==Subspecies.LILIN
//					|| s==Subspecies.ELDER_LILIN
//					|| s==Subspecies.ANGEL
					);
			
			if (index!=0 && index<availableSubspecies.size()+1) {
				AbstractSubspecies subspecies = availableSubspecies.get(index - 1);
				String name = subspecies.getName(null);
				
				return new Response(
						Util.capitaliseSentence(name),
						"Создать атакующего подтипа: "+name,
						ATTACKER_SPAWN) {
					@Override
					public Colour getHighlightColour() {
						return subspecies.getColour(null);
					}
					@Override
					public void effects() {
						attackerRaceStage = RaceStage.PARTIAL;
						if(responseTab==1) {
							attackerRaceStage = RaceStage.PARTIAL_FULL;
						} else if(responseTab==2) {
							attackerRaceStage = RaceStage.LESSER;
						} else if(responseTab==3) {
							attackerRaceStage = RaceStage.GREATER;
						}
						if(subspecies==Subspecies.HALF_DEMON || responseTab==4) {
							attackerSubspecies = Subspecies.HALF_DEMON;
							attackerHalfDemonSubspecies = responseTab==4?subspecies:Subspecies.HUMAN;
						} else {
							attackerSubspecies = subspecies;
							attackerHalfDemonSubspecies = Subspecies.HUMAN;
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
			}
			return null;
		}
	};
	
	public static final DialogueNode ATTACKER_SPAWN = new DialogueNode("Создать атакующего", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
				sb.append("Выберите пол атакующего.");
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Gender> availableGedners = Arrays.asList(Gender.values());
			
			if (index!=0 && index<availableGedners.size()+1) {
				Gender gender = availableGedners.get(index - 1);
				String name = attackerSubspecies.getName(null);
				
				return new ResponseEffectsOnly(
						Util.capitaliseSentence(gender.getName()),
						"Создать атакующего.<br/>Подтип: "+name
						+"<br/>Gender: "+gender.getName()
						+" ("
							+(gender.getGenderName().isHasPenis()?"[style.italicsGood(Пенис)] ":"[style.italicsBad(Пенис)] ")
							+(gender.getGenderName().isHasVagina()?"[style.italicsGood(Вагина)] ":"[style.italicsBad(Вагина)] ")
							+(gender.getGenderName().isHasBreasts()?"[style.italicsGood(Грудь)]":"[style.italicsBad(Грудь)]")
						+")") {
					@Override
					public Colour getHighlightColour() {
						return gender.getColour();
					}
					@Override
					public void effects() {
						initAttacker();
						
						if(attackerSubspecies==Subspecies.HALF_DEMON) {
							attacker.setSubspeciesOverride(null);
							attacker.setBody(
									Main.game.getCharacterUtils().generateHalfDemonBody(attacker, gender, attackerHalfDemonSubspecies, false),
									false);
						} else {
							attacker.setSubspeciesOverride(null);
							
							if(attackerSubspecies==Subspecies.DEMON) {
								attackerRaceStage = RaceStage.GREATER;
							}
							
							attacker.setBody(gender, attackerSubspecies, attackerRaceStage, true);
							
//							Main.game.getCharacterUtils().reassignBody(
//									attacker,
//									attacker.getBody(),
//									attacker.getGender(),
//									subspecies,
//									stage,
//									false);
						}

						attacker.resetInventory(true);
						attacker.clearNonEquippedInventory(false);
						Main.game.getCharacterUtils().generateItemsInInventory(attacker, true, true, true);
						attacker.equipClothing();
						
						Main.game.setContent(new Response("", "", attacker.getEncounterDialogue()));
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
			}
			return null;
		}
	};
	
	public static final DialogueNode BODY_PART_RACE_RESET = new DialogueNode("Сбросить тело", "Установить расу.", false) {
		@Override
		public void applyPreParsingEffects() {
			// Just in case this dialogue node is accessed before initialising targetedCharacter, or if targetedCharacter is no longer present:
			if(targetedCharacter==null
					|| (!targetedCharacter.isPlayer() && !Main.game.getCharactersPresent().contains(targetedCharacter))) {
				targetedCharacter = Main.game.getPlayer();
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>"
						+ UtilText.parse(targetedCharacter, "Выберите одну из рас, чтобы сбросить тело [npc.namePos] до значений по умолчанию этой расы. (т. е. переделать текущее тело [npc.her] под другую расу.)")
					+ "</p><p>[style.colourTfPartial(Частичная)]: Устанавливает тело как человеческое, но с усиками, глазами, ушами, волосами, рогами, хвостом и крыльями выбранной расы.</br>[style.colourTfMinor(Малая)]: То же самое, что частичная, но также включает задницу, грудь, пенис и вагину.</br>[style.colourTfLesser(Меньшая)]: То же самое, что малая, но также включает руки и ноги.</br>[style.colourTfGreater(Полная)]: Устанавливает все части тела как у выбранной расы.</br></p><p><b>ID:</b><br/>");
			for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
				sb.append("<span style='color:"+sub.getColour(targetedCharacter).toWebHexString()+";'>"+Util.capitaliseSentence(sub.getName(targetedCharacter.getBody()))+"</span>: "+Subspecies.getIdFromSubspecies(sub));
				sb.append("</br>");
			}
			
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(Частичная)]";

			} else if(index == 1) {
				return "[style.colourTfMinor(Незначительная)]";
				
			} else if(index == 2) {
				return "[style.colourTfLesser(Малая)]";
				
			} else if(index == 3) {
				return "[style.colourTfGreater(Сильная)]";
				
			} else if (index == 4) {
				return "Цель";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==4) {
				if (index == 1) {
					if (targetedCharacter == Main.game.getPlayer()) {
						return new Response(Main.game.getPlayer().getName(), "Вы — текущая цель.", null);
					} else {
						return new Response(Main.game.getPlayer().getName(), "Выбрать себя.", BODY_PART_RACE_RESET) {
							@Override
							public void effects() {
								targetedCharacter = Main.game.getPlayer();
							}
						};
					}
					
				} else {
					index-=2;
				}

				if (index >= Main.game.getCharactersPresent().size() || index<0) {
					return null;
				}
				GameCharacter gc = Main.game.getCharactersPresent().get(index);
				if (!gc.isUnique()) {
					if (targetedCharacter == gc) {
						return new Response(gc.getName(), gc.getName()+" — текущая цель.", null);
					} else {
						return new Response(gc.getName(), "Сменить цель на "+gc.getName(), BODY_PART_RACE_RESET) {
							@Override
							public void effects() {
								targetedCharacter = gc;
							}
						};
					}
				}
			
			}
			
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>();
			availableSubspecies.addAll(Subspecies.getAllSubspecies());
			availableSubspecies.removeIf(s->s.getRace()==Race.ELEMENTAL);
			
			if (index!=0 && index<availableSubspecies.size()+1) {
				AbstractSubspecies subspecies = availableSubspecies.get(index - 1);
				String name = subspecies.getName(null);
				
				return new Response(
						Util.capitaliseSentence(name),
						"Сделать ваше тело таким, как у "+UtilText.generateSingularDeterminer(name)+" "+name+".",
						BODY_PART_RACE_RESET){
					@Override
					public void effects() {
						if(subspecies==Subspecies.HALF_DEMON) {
							targetedCharacter.setSubspeciesOverride(null);
							targetedCharacter.setBody(
									Main.game.getCharacterUtils().generateHalfDemonBody(targetedCharacter, targetedCharacter.getGender(), Subspecies.HUMAN, false),
									false);
//							System.out.println("Subspecies override: "+targetedCharacter.getSubspeciesOverride());
							
						} else {
							targetedCharacter.setSubspeciesOverride(null);
							RaceStage stage = responseTab==0
									?RaceStage.PARTIAL
									:(responseTab==1
										?RaceStage.PARTIAL_FULL
										:(responseTab==2
											?RaceStage.LESSER
											:RaceStage.GREATER));
							
							if(subspecies==Subspecies.DEMON) {
								stage = RaceStage.GREATER;
								
								DialogueFlags dialogueFlags = Main.game.getDialogueFlags();
								if(!dialogueFlags.hasFlag("innoxia_child_of_lyssieth")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lunette")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lirecea")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lovienne")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lasielle")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lyxias")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lisophia")
										&& !dialogueFlags.hasFlag("innoxia_child_of_lilith")){
									Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true);
								}
							}
							
							Main.game.getCharacterUtils().reassignBody(
									targetedCharacter,
									targetedCharacter.getBody(),
									targetedCharacter.getGender(),
									subspecies,
									stage,
									false);
						}
						Main.game.getTextEndStringBuilder().append(
								"<p>[style.boldTfGeneric(Трансформация:)] Теперь вы "+UtilText.generateSingularDeterminer(name)+" "+name+"!"
								+ "</p>");
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode CLOTHING_COLLAGE = new DialogueNode("Коллаж одежды", "Коллаж одежды.", false) {
		@Override
		public String getContent() {
			inventorySB.setLength(0);
			
			float width = 100/20f;
			int imgWidth = 100;
			
			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>Total items: "+itemsTotal.size()+"</h5>");
			for(AbstractItemType itemType : itemsTotal) {
				inventorySB.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:1px; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+";'>"
											+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
											+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div></div></div>");
			}
			inventorySB.append("</div>");

			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>Total weapons: "+weaponsTotal.size()+"</h5>");
			for(AbstractWeaponType weaponType : weaponsTotal) {
				inventorySB.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:1px; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+";'>"
											+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage()
											+"</div>"
											+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div></div></div>");
			}
			inventorySB.append("</div>");

			inventorySB.append("<div class='inventory-not-equipped' style='-webkit-user-select:auto;'>"
					+ "<h5>Total clothing: "+clothingTotal.size()+"</h5>");
			for(AbstractClothingType clothingType : clothingTotal) {
				inventorySB.append("<div class='container-full-width' style='width:"+width+"%; white-space: nowrap; word-wrap: break-word; font-size:0.75em; -webkit-user-select:auto; padding:1px; margin:0;'><div class='inventory-item-slot unequipped' style='width:"+imgWidth+"%; box-sizing: border-box; padding:0; margin:0; background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+";'>"
										+ "<div class='inventory-icon-content'>"
											+clothingType.getSVGImageRandomColour(true, false, false)
										+"</div>"
										+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div></div></div>");
			}
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};

	private static String parsedText = "";
	private static String rawText = "";
	private static String xmlFileText = "res/txt/ENTER_PATH.xml";
	public static final DialogueNode PARSER = new DialogueNode("Парсер", "", true) {

		@Override
		public String getHeaderContent() {
			return ("<p>"
					+ "<b>Please</b> at least skim over the help page before viewing the 'commands' pages! (The number of commands could be off-putting if you're not aware of how simple they really are.)"
					+ "</p>"
					

					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
							+ "<p style='display:inline-block; padding:0; margin:0; width:100%;'>XML test (enter full path, including .xml): </p>"
							+ "<br/>"
							+ "<form style='display:inline-block; width:100%; padding:0; margin:0; text-align:center;'><input type='text' id='xmlTest' style='width:50%;' placeholder='res/txt/...' value='"+xmlFileText+"'></form></div></div><p><b>Этот парсер поддерживает html-форматирование.</b></p><p style='padding:0;margin:0;text-align:center;'>Парсить:</p><form style='padding:0;margin:0;text-align:center;'><textarea id='parseInput' name='Text1' style='width:760px;height:200px;'>"+rawText+"</textarea>"
					+ "</form>");
		}
		
		@Override
		public String getContent() {
			return  "<p><b>Результат:</b></p><p>"+parsedText+"</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Парсить!", "Разобрать введенный вами текст."){
					@Override
					public void effects() {
						rawText = (String) Main.mainController.getWebEngine().executeScript("document.getElementById('parseInput').value");
						parsedText = UtilText.parse(rawText);
						if(Main.game.getCurrentDialogueNode()==PARSER) {
							Main.game.setContent(new Response("", "", PARSER));
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Помочь", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Цели", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Команды", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Список команд", "", PARSER_COMMANDS);
				
			} else if (index == 6) {
				return new Response("Загрузить пример 1", "", PARSER){
					@Override
					public void effects() {
						rawText = "Вы видите, как [lilaya.name] сидит за одним из столов в лаборатории [lilaya.herHis], вертя в руках инертный демонстоун. [rose.name] сидит рядом с [lilaya.herPro], с любовью глядя в [lilaya.eyes+] [lilaya.namePos], пока [rose.her] [rose.tail+] плавно покачивается взад-вперед позади [rose.herPro].\n<br/><br/>\nНе в силах сосредоточиться, когда [lilaya.her] рабыня так близко, [lilaya.name] кладет демонстоун на стол, после чего обвивает [lilaya.arms+] [lilaya.her] вокруг [rose.namePos] со спины и притягивает [rose.herPro] к себе на [lilaya.her] колени. [rose.name] издает радостный тихий вскрик, когда [rose.her] господин наконец начинает уделять [rose.herPro] то внимание, которого [rose.she] так жаждала, и когда [lilaya.namePos] рука скользит под [rose.her] платье, [rose.she] начинает стонать от восторга.";
					}
				};
				
			} else if (index == 7) {
				return new Response("Загрузить пример 2", "", PARSER){
					@Override
					public void effects() {
						rawText = "[brax.name] расхаживает взад-вперед по кабинету [brax.herHis], тихо ворча на [brax.himself]: [brax.speech(Хм... Мой новый постер всё еще не приехал...)]";
					}
				};
				
			} else if (index == 11) {
				return new Response("Xml тест",
						"Проанализируйте каждую запись диалога в файле по указанному вами пути, чтобы проверить наличие ошибок. Lilaya, Brax, Rose, Ralph, Nyan и Zaranix передаются в качестве целей парсера. Специальный парсинг форматируется как 'SP1' и выполняется до 10 раз.",
						PARSER){
					@Override
					public void effects() {
						for(int i=1;i<=10;i++) {
							UtilText.addSpecialParsingString("SP"+i, i==1);
						}
						xmlFileText = ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('xmlTest').value")).replaceAll("\u200b", "");
						parsedText = Main.game.runXmlTest(xmlFileText);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_HELP = new DialogueNode("Супер веселый и интересный гид Инноксии по парсингу", "", true) {

		/*
		 * I've seen String concatenation... String concatenation that you've seen.
		 * But you have no right to call me a bad programmer.
		 * You have a right to ridicule my code. You have a right to do that... but you have no right to judge me.
		 * 
		 * It's impossible for words to describe what is necessary to those who do not know what String concatenation means.
		 * String concatenation... String concatenation has a face... and you must make a friend of String concatenation. String concatenation and html parsing are your friends. If they are not, then they are enemies to be feared.
		 */
		@Override
		public String getHeaderContent() {
			return  "<p style='text-align:center;'><i>You put in the input, and it returns a nice output!</i></p>"
					
					+ "<p>"
					+ "<h6>Input:</h6><br/>"
					+"Everything is parsed using square brackets, split into the following pattern:<br/>"
					+"[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i>]<br/>или, для немногих специальных команд, требующих аргументов:<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i><i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(аргументы)</i>]<br/>или, для парсинга как скрипта:<br/>[#<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i>]<br/>или, для парсинга как скрипта, подавляющего вывод:<br/>[##<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i>]<br/><i>(Это значит, что команда будет выполнена, но строковый вывод не отобразится.)</i></p><p>Пример использования в предложении:<br/><br/><i>Пока ты начинаешь читать нудную документацию по парсингу Инноксии, [lilaya.name] подходит сзади и обвивает [lilaya.her] [lilaya.tail+] вокруг твоих [pc.leg]. Наклонившись над твоим плечом, [lilaya.she] стонет: [lilaya.speech(О боже. Это так скучно, [#pc.getName(true)]!)]'</i><br/><br/>парсится в:<br/><br/>"
					+ UtilText.parse("Пока ты начинаешь читать нудную документацию по парсингу Инноксии, [lilaya.name] подходит сзади и обвивает [lilaya.her] [lilaya.tail+] вокруг твоих [pc.leg]. Наклонившись над твоим плечом, [lilaya.she] стонет: [lilaya.speech(О боже. Это так скучно, [#pc.getName(true)]!)]")
					+ "</p>"
					+ "<br/>"
					
					
					+"<h6><b style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>Цель</b> <b>тег:</b></h6><p>Целью команды является имя NPC или 'pc' для главного героя. Теги целей <b>нечувствительны к регистру.</b> (т.е. pc воспринимается так же, как PC, pC или Pc)<br/> Если передано нераспознанное имя, результат будет 'INVALID_TARGET_NAME'.<br/> Текущие доступные теги целей можно посмотреть на странице 'Targets'.<br/>Пример:<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.command]<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>pc</i>.command(аргументы)]<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>npc</i>.command]</p><br/><h6><b style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Команда</b> <b>и</b> <b style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>аргумент</b> <b>теги:</b></h6><p>Теги команд определяют, какой результат будет возвращен. Они бывают двух видов: с аргументами и без них.<br/>Аргументы передаются внутри скобок, следующих за командой, при этом любые вставленные вами пробелы игнорируются.<br/> т.е. [pc.command(аргументы)] — это то же самое, что [pc.command   (аргументы)].<br/>Теги команд <b>чувствительны к регистру только для первой буквы</b>. (т.е. command воспринимается так же, как cOMMAND, cOmMaNd или commanD)<br/>Аргументы специфичны для каждой команды, поэтому вам придется заглянуть в документацию команды, чтобы узнать, какие аргументы она принимает. (Не волнуйтесь, команд с аргументами не так уж много.)<br/>Пример:<br/>[pc.speech<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(Привет, читатель!)</i>] выводит "+UtilText.parsePlayerSpeech("Привет, читатель!")+""
					+ ""
					+ "</p>"
					
					+"<p>"
					+ "<b>Command modifier (a_ an_)</b><br/>"
					+"You may insert 'a_' or 'an_' to automatically generate the appropriate pronoun before an argument. (It's your choice if you prefer a_ or an_, they both work in exactly the same way.)<br/>"
					+"e.g.:<br/>"
					+ "[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>] выводит 'tall'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>] выводит 'высокий'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_height</i>] <b>также</b> выводит 'высокий'<br/><br/>Для некоторых названий частей тела это добавляет немного сложности.<br/>например:<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>] выводит 'крылья'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms</i>] выводит 'пара крыльев'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_arms</i>] <b>также</b> выводит 'пара крыльев'<br/><br/></p><p><b>Модификатор команды (Заглавные буквы)</b><br/>Большинство команд позволяют применять заглавные буквы. Те, что этого не позволяют (например, команды с числовым выводом), всё равно примут команду с заглавной буквы, но само изменение регистра не применится.<br/>Чтобы сделать вывод с заглавной буквы, нужно просто написать <b>первую букву</b> имени команды с заглавной.<br/>например:<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>] выводит 'tall'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>hEIGHT</i>] <b>также</b> выводит 'высокий' (уверен, никто бы так не сделал...)<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Height</i>] выводит 'Высокий'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>] выводит 'высокий'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_height</i>] выводит 'Высокий'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_Height</i>] <b>также</b> выдает 'A tall'<br/><br/><p><b>Модификатор команды (+ D)</b><br/>Большинство команд, возвращающих имя, могут применять дополнительные <b>рандомизированные дескрипторы</b>. (Вы можете проверить, какие команды принимают модификаторы '+' и 'D', на странице 'Команды'.)<br/>Чтобы применить дополнительные дескрипторы к результату, вам нужно просто добавить '+', 'd' или 'D' в конец команды.<br/><b>Это работает в сочетании с модификаторами 'a_ an_' и 'Capitalisation' (Регистр).</b><br/>например:<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>] выводит 'крылья'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms+</i>] выдает 'feathered wings'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsD</i>] <b>также</b> выдает 'feathered wings'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsd</i>] <b>также</b> выдает 'feathered wings'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>ArmsD</i>] выдает 'Feathered wings'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms+</i>] выдает 'a pair of feathered wings'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_arms+</i>] выдает 'A pair of feathered wings'<br/><br/>Некоторые результаты имеют больше рандомизации, чем другие.<br/>например:<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>] выдает 'slit'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>] выдает 'cunt'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>] выдает 'wet pussy'<br/>[npc.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>] выдает 'усеянную щупальцами киску'<br/><br/></p><br/><h6>Итог:</h6><br/><p><b>Верный</b> синтаксис команды:<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i>]<br/>или<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i><i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(аргументы)</i>]<br/><br/><br/><h6>Примеры:</h6><br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>Бракс</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>] откидывается на [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>Бракс</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>его</i>] спинку стула, гадая, что случилось с [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>артур</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>] после [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>Бракс</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>он</i>] передал [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>артур</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>его</i>] в руки Scarlett.<br/>вывод:<br/>'Brax откидывается на спинку своего кресла, гадая, что стало с Arthur после того, как он передал его Scarlett.'<br/><br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>встал</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>] откидывается на [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>встала</i>.<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>она</i>] поднялась со своего стула, вздохнув, когда [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>встал</i>.<i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>он</i>] делает глоток [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>встала</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>название</i>] кофе.<br/>вывод:<br/>"
					+ UtilText.parse("[rose.name] откидывается на спинку [rose.herHuis] кресла, вздыхая, когда [rose.shyeHe] делает глоток [rose.name] кофе.")+"<br/>"
					+ "<b>Note:</b> <i style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Опечатки</i> заставят систему парсинга вернуть неверную строку команды, тогда как <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>неправильные команды</i> (например, ввод .name вместо .herHis) не вызовут ошибки!<br/><br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>лиловая</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>] врывается к [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>инноксия</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>], гневно крича, узнав, что [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>лиловая</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>ее</i>] секс-сцены еще не исправили, [<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>лиловая</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>речь</i><i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(Какого черта ты творишь, Инноксия?! Ты сказала, что мои сцены перепишут еще несколько недель назад!)</i>]<br/>выводы:<br/>'Lilaya в ярости подлетает к Инноксии, крича от возмущения, узнав, что ее секс-сцены до сих пор не исправлены, "
						+UtilText.parseSpeech("Какого черта ты творишь, Инноксия?! Ты сказала, что мои сцены перепишут еще несколько недель назад!", Main.game.getNpc(Lilaya.class))+"'"
					
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Парсер", "", PARSER);
					
			} else if (index == 2) {
				return new Response("Помочь", "", null);
					
			} else if (index == 3) {
				return new Response("Цели", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Команды", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Список команд", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_TARGETS = new DialogueNode("Парсер", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цели</i>, для использования в синтаксисе парсинга:<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i><i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(аргументы)</i>]</p>");
			
			for(AbstractParserTarget character : ParserTarget.getAllParserTargets()) {
				UtilText.nodeContentSB.append("<hr/>"
						+"<p>");
				
				boolean first=true;
				for(String s : character.getTags()) {
					UtilText.nodeContentSB.append((first?"":" | ") +"<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>"+s+"</i>");
					first = false;
				}
				
				UtilText.nodeContentSB.append("<br/>"
						+character.getDescription()
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Парсер", "", PARSER);
				
			} else if (index == 2) {
				return new Response("Помочь", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Цели", "", null);
				
			} else if (index == 4) {
				return new Response("Команды", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Список команд", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_COMMANDS = new DialogueNode("Парсер", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команды</i>, для использования в синтаксисе парсинга:<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i><i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(аргументы)</i>]</p><p><b>Пожалуйста, не пугайтесь количества команд!</b> <i>Подавляющее</i> большинство из них — это автоматически сгенерированные «стандартные» вариации для частей тела, следующие (надеемся, что) интуитивно понятной системе именования.</p>");
			
			int count=1;
			for(ParserCommand command : UtilText.commandsList) {
				UtilText.nodeContentSB.append("<hr/>"
						+ "<p>"
						+ "<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b> <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
				
				if(command.getTags().size()>1) {
					for(int i = 1; i<command.getTags().size(); i++)
						UtilText.nodeContentSB.append(" | <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
				}
				
				UtilText.nodeContentSB.append("</p>");
				count++;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Парсер", "", PARSER);
				
			} else if (index == 2) {
				return new Response("Помочь", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Цели", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Команды", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Список команд", "", null);
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNode PARSER_COMMANDS_NEAT = new DialogueNode("Парсер", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команды</i>, для использования в синтаксисе парсинга:<br/>[<i style='color:"+PresetColour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>цель</i>.<i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>команда</i><i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>(аргументы)</i>]</p><p><b>Пожалуйста, не пугайтесь количества команд!</b> <i>Подавляющее</i> большинство из них — это автоматически сгенерированные «стандартные» вариации для частей тела, следующие (надеемся, что) интуитивно понятной системе именования.</p>");
			
			int count = 1;
			for(BodyPartType bpt : BodyPartType.values()) {
				UtilText.nodeContentSB.append("<details>"
						+ "<summary style='cursor:pointer;'><b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>("
							+String.format("%03d", count)+" - "+String.format("%03d", count+UtilText.commandsMap.get(bpt).size()-1)+")</b> "+Util.capitaliseSentence(bpt.getName())+"</summary>");
				for(ParserCommand command : UtilText.commandsMap.get(bpt)) {
					UtilText.nodeContentSB.append("<p>"
							+ "<hr/>"
							+ "<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b> <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
					
					if(command.getTags().size()>1) {
						for(int i = 1; i<command.getTags().size(); i++)
							UtilText.nodeContentSB.append(" | <i style='color:"+PresetColour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
					}
					
					UtilText.nodeContentSB.append("<br/>"
							+(command.getArguments()==""?"<i style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Без аргументов</i>":"<i style='color:"+PresetColour.CLOTHING_YELLOW.toWebHexString()+";'>"+command.getArguments()+"</i>")+"<br/>"
							+(command.isAllowsCapitalisation()?"<i style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Регистр</i>":"<i style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Регистр</i>")
								+ " | " +(command.isAllowsPronoun()?"<i style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Местоимения</i>":"<i style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Местоимения</i>")
								+"<br/>"
							+command.getDescription()+"<br/>Примеры:<br/>"
							+ command.getExampleBeforeParse("лилая", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[лилая."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"<br/>"
							
							+ command.getExampleBeforeParse("бракс", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[бракс."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"<br/>"
							
							+ command.getExampleBeforeParse("кейт", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[kate."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")
							+"</p>");
					
					count++;
				}

				UtilText.nodeContentSB.append("</details>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Парсер", "", PARSER);
				
			} else if (index == 2) {
				return new Response("Помочь", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Цели", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Команды", "", null);
				
			} else if (index == 5) {
				return new Response("Список команд", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("Назад", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	
	public static final DialogueNode POST_SEX_2KOMA = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				GameCharacter target = Main.sex.getSubmissiveParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_2KOMA", target);
			} else {
				GameCharacter target = Main.sex.getDominantParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_2KOMA_AS_SUB", target);
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("Продолжить", "Теперь, когда ты поставил эту сучку на [npc.her] место, можешь продолжать то, чем занимался...", Main.game.getDefaultDialogue(false));
				} else {
					return new Response("Продолжить", "Теперь, когда тебя поставили на место, как и подобает сучке, можешь продолжать то, чем занимался...", Main.game.getDefaultDialogue(false));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CENTAUR_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			NPC centaur = Main.game.getActiveNPC();
			return UtilText.parseFromXMLFile("misc/misc", "A_WILD_CENTAUR_APPEARS", centaur);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC centaur = Main.game.getActiveNPC();
			
			if(index==1) {
				return new ResponseSex(
						UtilText.parse(centaur, "Доминируй над [npc.herHim]"),
						UtilText.parse(centaur, "Возьми на себя роль доминанта и трахни эту [npc.race]."),
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(centaur),
								Main.game.getPlayer().getCompanions(),
								null),
						DebugDialogue.POST_SEX_CENTAUR,
						"<p>Решив, что хочешь подчинить себе эту [npc.race], которая только что магическим образом возникла перед тобой, ты подходишь к [npc.herHim] и рычишь: [pc.speech(Пора поставить тебя на место!)]</p>");
				
			} else if(index==2) {
				return new ResponseSex(
						UtilText.parse(centaur, "Подчиниться [npc.herHim]"),
						UtilText.parse(centaur, "Возьми на себя роль подчиненной и позволь этой [npc.race] трахать тебя."),
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(centaur),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Main.game.getPlayer().getCompanions()),
						DebugDialogue.POST_SEX_CENTAUR,
						"<p>Решив, что хочешь, чтобы эта [npc.race] подчинила тебя себе, ты подходишь к [npc.herHim] и умоляешь: [pc.speech(Пора тебе поставить меня на место!)]</p>");
				
			} else if(index==0) {
				return new Response("Неважно", UtilText.parse(centaur, "Решить ничего не делать с этим [npc.race], а вместо этого просто продолжить то, чем вы занимались..."), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.banishNPC(centaur);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SEX_CENTAUR = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				GameCharacter target = Main.sex.getSubmissiveParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_CENTAUR", target);
			} else {
				GameCharacter target = Main.sex.getDominantParticipants(false).entrySet().iterator().next().getKey();
				return UtilText.parseFromXMLFile("misc/misc", "POST_SEX_CENTAUR_AS_SUB", target);
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				NPC centaur = Main.game.getActiveNPC();
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("Продолжить", UtilText.parse(centaur, "Теперь, когда вы поместили этот [npc.race] на его [npc.her] место, вы можете продолжить то, чем занимались..."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(centaur);
						}
					};
				} else {
					return new Response("Продолжить", UtilText.parse(centaur, "Теперь, когда этот [npc.race] поставил вас на место, вы можете продолжить то, чем занимались..."), Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(centaur);
						}
					};
				}
			}
			return null;
		}
	};
	
	private static StringBuilder spawnrateSB;
	private static float spawnTotal, spawnTotalMasculine, spawnTotalFeminine;
	public static final DialogueNode SPAWN_RATES = new DialogueNode("", "", false) {
		@Override
		public String getAuthor() { return "AceXp"; }
		@Override
		public String getContent() {
			return spawnrateSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode COMBAT_MOVES = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			for(CombatMoveCategory cat : CombatMoveCategory.values()) {
				sb.append("<h4>"+cat.toString()+":</h4>");
				int i=0;
				List<AbstractCombatMove> categoryMoves = new ArrayList<>(CombatMove.getAllCombatMovesInCategory(cat));
				Collections.sort(categoryMoves, (m1, m2) ->
					m1.getType().compareTo(m2.getType())==0 && cat==CombatMoveCategory.SPELL
						?m1.getAssociatedSpell().getSpellSchool().compareTo(m2.getAssociatedSpell().getSpellSchool())
						:m1.getType().compareTo(m2.getType()));
				
				for(AbstractCombatMove move : categoryMoves) {
					sb.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
						sb.append("<p style='span:0; margin:0; -webkit-user-select:auto;'>");
							sb.append("<b style='color:"+move.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(move.getName(0, Main.game.getPlayer()))+"</b>: "+move.getDescription(0, Main.game.getPlayer()));
							sb.append("<br/>");
							if(cat==CombatMoveCategory.SPELL) {
								sb.append("Тип: <span style='color:"+move.getAssociatedSpell().getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(move.getAssociatedSpell().getSpellSchool().getName())+" Заклинание</span> | ");
							} else {
								sb.append("Тип: <span style='color:"+move.getType().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(move.getType().getName())+"</span> | ");
							}
							sb.append("ID: <span style='font-family:monospace;'>"+move.getIdentifier()+"</span>");
						sb.append("</p>");
					sb.append("</div>");
					i++;
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};


	public static final DialogueNode RANDOM_ENCHANTMENT_CLOTHING = new DialogueNode("Случайные зачарования одежды", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse("<h4>[style.colourMinorGood(Положительные зачарования:)]</h4>"));
			int i=0;
			for(RandomEnchantment randomEnchantment : RandomEnchantment.getAllPositiveClothingEnchantments()) {
				sb.append(getFormattedEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			sb.append(UtilText.parse("<h4>[style.colourMinorBad(Отрицательные чары:)]</h4>"));
			i=0;
			for(RandomEnchantment randomEnchantment : RandomEnchantment.getAllNegativeClothingEnchantments()) {
				sb.append(getFormattedEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	public static List<String> randomEnchantmentClothingIds = Util.newArrayListOfValues("innoxia_groin_panties", "innoxia_torso_tshirt", "WRIST_MENS_WATCH");
	
	private static String getFormattedEnchantmentEntry(boolean oddRow, RandomEnchantment randomEnchantment) {
		StringBuilder sb = new StringBuilder();
		
		String preParsingString = randomEnchantment.getConditionalPreParsingString();
		UtilText.parse(Main.game.getPlayer(), preParsingString); // Parse the conditional to make sure that any variables for name have been set
		preParsingString = preParsingString.replaceAll("(?<!^)\\[#", "<br/>\\[#"); // Format it for display
		
		sb.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(oddRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; text-align:center; background:#00000000;'>");
				sb.append("<b>"+Util.capitaliseSentence(UtilText.parse(randomEnchantment.getName(null)))+"</b>");
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; font-family:monospace; font-size:0.75em;background:#00000000;'>");
				if(randomEnchantment.isMod()) {
					sb.append(UtilText.parse("[style.colourGreenLight(МОД)] "));
				} else {
					sb.append(UtilText.parse("[style.colourBlueLight(РЕЗ)] "));
				}
				sb.append(RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment));
			sb.append("</div>");

			sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
				sb.append("вес_спавна:<br/>");
				sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+randomEnchantment.getSpawnWeighting()+"</span>");
			sb.append("</div>");
			
			if(!preParsingString.isEmpty()) {
				sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
					sb.append("условный_предварительный_парсинг:<br/>");
					sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+preParsingString+"</span>");
				sb.append("</div>");
			}
			
			
			sb.append("<div class='container-full-width' style='margin:0; padding:0; background:#00000000; text-align:center;'>");
				sb.append("<div class='normal-button' id='RANDOM_ENCHANTMENT_"+randomEnchantmentClothingIds.get(0)+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.FEMININE.toWebHexString()+";'>Заспавнить трусики</div>");
				sb.append("<div class='normal-button' id='RANDOM_ENCHANTMENT_"+randomEnchantmentClothingIds.get(1)+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Заспавнить футболку</div>");
				sb.append("<div class='normal-button' id='RANDOM_ENCHANTMENT_"+randomEnchantmentClothingIds.get(2)+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.MASCULINE.toWebHexString()+";'>Заспавнить часы</div>");
			sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}
	
	//TODO
	public static final DialogueNode RANDOM_ENCHANTMENT_POTIONS = new DialogueNode("Случайные зачарования зелий", "", false) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parse("<h4>[style.colourMinorGood(Положительные зачарования:)]</h4>"));
			int i=0;
			for(RandomPotionEnchantment randomEnchantment : RandomPotionEnchantment.getAllPositivePotionEnchantments()) {
				sb.append(getFormattedPotionEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			sb.append(UtilText.parse("<h4>[style.colourMinorBad(Отрицательные чары:)]</h4>"));
			i=0;
			for(RandomPotionEnchantment randomEnchantment : RandomPotionEnchantment.getAllNegativePotionEnchantments()) {
				sb.append(getFormattedPotionEnchantmentEntry(i%2==0, randomEnchantment));
				i++;
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	private static String getFormattedPotionEnchantmentEntry(boolean oddRow, RandomPotionEnchantment randomEnchantment) {
		StringBuilder sb = new StringBuilder();
		
		String preParsingString = randomEnchantment.getConditionalPreParsingString();
		UtilText.parse(Main.game.getPlayer(), preParsingString); // Parse the conditional to make sure that any variables for name have been set
		preParsingString = preParsingString.replaceAll("(?<!^)\\[#", "<br/>\\[#"); // Format it for display
		
		sb.append("<div class='container-full-width' style='width:95%; padding:4px; margin:4px 2.5% 4px 2.5%; background-color:"+(oddRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; text-align:center; background:#00000000;'>");
				sb.append("<b>"+Util.capitaliseSentence(UtilText.parse(randomEnchantment.getName(null)))+"</b>");
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='span:0; margin:0; width:50%; -webkit-user-select:auto; font-family:monospace; font-size:0.75em;background:#00000000;'>");
				if(randomEnchantment.isMod()) {
					sb.append(UtilText.parse("[style.colourGreenLight(МОД)] "));
				} else {
					sb.append(UtilText.parse("[style.colourBlueLight(РЕЗ)] "));
				}
				sb.append(RandomPotionEnchantment.getIdFromRandomEnchantment(randomEnchantment));
			sb.append("</div>");

			sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
				sb.append("вес_спавна:<br/>");
				sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+randomEnchantment.getSpawnWeighting()+"</span>");
			sb.append("</div>");
			
			if(!preParsingString.isEmpty()) {
				sb.append("<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; -webkit-user-select:auto; margin-top:0;'>");
					sb.append("условный_предварительный_парсинг:<br/>");
					sb.append("<span style='font-family:monospace; font-size:0.75em;'>"+preParsingString+"</span>");
				sb.append("</div>");
			}
			
			
			sb.append("<div class='container-full-width' style='margin:0; padding:0; background:#00000000; text-align:center;'>");
				sb.append("<div class='normal-button' id='RANDOM_POTION_ENCHANTMENT_"+RandomPotionEnchantment.getIdFromRandomEnchantment(randomEnchantment)+"' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>Создать зелье</div>");
			sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}
}
