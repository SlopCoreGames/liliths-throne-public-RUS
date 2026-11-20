package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.9
 * @author Innoxia
 */
public class Lab {
	
	public static boolean isLilayaAngryAtPlayerDemonTF() {
		return Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")
				&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)
				&& Main.game.getNpc(Lilaya.class).getSubspeciesOverride()!=Subspecies.DEMON;
	}
	
	public static Cell addArthurRoom() {
		Vector2i labLocation = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_LAB).getLocation();
		Cell arthurRoomCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(labLocation.increment(1, 0));
		arthurRoomCell.setPlace(new GenericPlace(PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR), true);
		arthurRoomCell.addPlaceUpgrade(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		arthurRoomCell.setTravelledTo(true);
		return arthurRoomCell;
	}
	
	public static final DialogueNode LAB = new DialogueNode("Лаборатория Lilaya", "", false) {
		@Override
		public String getContent() {
			if(Main.game.getNpc(Lilaya.class).getLocationPlaceType()==PlaceType.LILAYA_HOME_LAB) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RISK");
					
					} else if(Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANT");
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
						return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_PREGNANCY_RESOLVED");
					}
				}
			}
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if(index==1) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					if(Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)) {
						return new Response("Войти", "Дверь в лабораторию Lilaya плотно закрыта. Лучше вернуться позже.", null);
						
					} else if((Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {
						return new Response("Войти", "Дверь в лабораторию Lilaya плотно закрыта. Ты не сможешь войти, пока её беременность не разрешится.", null);
					}
				}
				
				if(Main.game.getNpc(Lilaya.class).getLocationPlaceType()!=PlaceType.LILAYA_HOME_LAB) {
					return new Response("Войти", "Дверь в лабораторию Lilaya плотно закрыта, и, учитывая час, она, скорее всего, спит наверху.", null);
				}
				
				return new Response("Войти", "Переступить порог и войти в лабораторию Lilaya.", LAB_ENTRY) {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)
								&& Main.game.getPlayer().getQuest(QuestLine.MAIN) != Quest.MAIN_1_I_ARTHURS_TALE
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)
								&& Main.game.getNpc(Lilaya.class).getAffection(Main.game.getPlayer())>0) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
					return null;
				}
				if(Main.game.getNpc(Lilaya.class).getLocationPlaceType()!=PlaceType.LILAYA_HOME_LAB || Main.game.getNpc(Arthur.class).getLocationPlaceType()==PlaceType.LILAYA_HOME_LAB) {
					return null;
				}
				return DialogueManager.getDialogueFromId("acexp_dominion_lilaya_lab_voyeurism").getResponse(0, 1);
			}
			return null;
		}
	};
	
	private static void setEntryFlags() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roseToldOnYou, false);
		if(Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, false);
			if(Main.game.getNpc(Lilaya.class).isPregnant()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, false);
			}
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaBirthNews, false);
		}
		if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
			Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
	}
	
	private static List<Response> getLabEntryGeneratedResponses() {
		List<Response> generatedResponses = new ArrayList<>();
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaHug)) {
			generatedResponses.add(new Response("Обнять",
					"Ты уже успел неожиданно обнять Lilaya сегодня, и хотя ей это понравилось, она, кажется, настроена серьезно и не хочет повторения...<br/><i>Тебе стоит подождать до завтра, прежде чем обнимать её снова!</i>",
					null));
		} else if(isLilayaAngryAtPlayerDemonTF()) {
			generatedResponses.add(new Response("Обнять",
					"Из-за своей обиды на тебя за то, что ты — полноценный демон, Lilaya решительно не хочет никаких объятий!",
					null));
		} else {
			generatedResponses.add(new Response("Обнять", "[pc.Step] подойти к Lilaya и крепко её обнять.", LAB_LILAYA_HUG));
		}
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("Беременность", "Тебе нужно будет пройти первичные тесты Lilaya, прежде чем она согласится помочь тебе справиться с твоей беременностью.", null));
				
			} else {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_CONSULT_LILAYA) {
					generatedResponses.add(new Response("Беременность", "Поговорить с Lilaya о своей беременности.", LilayaBirthing.LILAYA_ASSISTS_PREGNANCY){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE));
						}
					});
					
				} else {
					generatedResponses.add(new Response("Беременность", "Поговорить с Lilaya о своей беременности.", LilayaBirthing.LILAYA_ASSISTS_PREGNANCY_REPEAT){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
			}
		}

//		if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
//			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
//				generatedResponses.add(new Response("Incubation", "You'll need to complete Lilaya's initial tests before she'll agree to help you deal with the eggs you're currently incubating.", null));
//				
//			} else {
//				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION) == Quest.SIDE_INCUBATION_CONSULT_LILAYA) {
//					generatedResponses.add(new Response("Incubation", "Speak to Lilaya about the eggs you're currently incubating.", LilayaBirthing.LILAYA_ASSISTS_INCUBATION){
//						@Override
//						public void effects() {
//							setEntryFlags();
//							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_INCUBATION_LILAYA_HELP));
//						}
//					});
//					
//				} else {
//					generatedResponses.add(new Response("Incubation", "Speak to Lilaya about the eggs you're currently incubating.", LilayaBirthing.LILAYA_ASSISTS_INCUBATION_REPEAT){
//						@Override
//						public void effects() {
//							setEntryFlags();
//						}
//					});
//				}
//			}
//		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
					generatedResponses.add(new Response("Эссенции и зачарования", "Тебе нужно будет пройти начальные тесты Lilaya, прежде чем ты сможешь расспросить её о той странной энергии, которую ты поглотил.", null));
					
				} else {
					generatedResponses.add(new Response("Эссенции и зачарования", "Расспросить Lilaya о той странной энергии, которую ты поглотил.", LILAYA_EXPLAINS_ESSENCES){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
				
			} else {
				generatedResponses.add(new Response("Извлечь эссенции",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceExtractionKnown)
								?"Спросить Lilaya, можно ли использовать её оборудование для извлечения эссенций."
								:"Спросить Lilaya, есть ли способ извлечь поглощенные эссенции.",
							ESSENCE_EXTRACTION){
					@Override
					public void effects() {
						setEntryFlags();
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_NEED_RECOMMENDATION) {
				if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
					generatedResponses.add(new Response("Работорговец", "Тебе нужно будет пройти начальные тесты Lilaya, прежде чем ты сможешь попросить у неё рекомендательное письмо.", null));
					
				} else {
					generatedResponses.add(new Response("Работорговец", "Попросить у Lilaya рекомендательное письмо, чтобы получить лицензию работорговца.", LILAYA_SLAVER_RECOMMENDATION){
						@Override
						public void effects() {
							setEntryFlags();
						}
					});
				}
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("Жилье", "Сначала тебе нужно будет пройти начальные тесты Lilaya, прежде чем ты сможешь спросить её о приглашении друзей в дом!", null));
				
			} else {
				generatedResponses.add(new Response("Жилье", "Спросить Lilaya о том, чтобы пригласить своего нового друга пожить в одной из многочисленных свободных комнат особняка.", LILAYA_FRIEND_ACCOMMODATION){
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ACCOMMODATION, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_DOLL_STORAGE) && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_STORAGE)) {
			if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS)) {
				generatedResponses.add(new Response("Хранилище кукол", "Тебе нужно будет завершить начальные тесты Lilaya, прежде чем ты сможешь спросить её о хранении секс-кукол в особняке!", null));
				
			} else {
				generatedResponses.add(new Response("Хранилище кукол", "Спросить Lilaya, можно ли использовать одну из свободных комнат особняка для хранения секс-кукол.", LILAYA_DOLL_STORAGE){
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DOLL_STORAGE, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
		}
		
		
		
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaDateTalk)
				&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate)) {
			generatedResponses.add(new Response("Текущая дата", "Спросить Lilaya, почему календарь в твоей комнате спешит на три года.", LILAYA_CURRENT_DATE_TALK) {
				@Override
				public void effects() {
					setEntryFlags();
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaDateTalk, true);
				}
			});
		}
		
		if(Main.game.getPlayer().hasItemType(ItemType.PRESENT) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
			if(isLilayaAngryAtPlayerDemonTF()) {
				generatedResponses.add(new Response("Вручить подарок", "Хотя в твоём инвентаре есть подарок, Lilaya не горит желанием его принимать из-за своей обиды на то, что ты — полноценный демон, в то время как она — нет.", null));
				
			} else {
				generatedResponses.add(new Response("Вручить подарок", "Отдать подарок из инвентаря Lilaya.", LILAYA_PRESENT) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent2)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent3, true);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent1)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent2, true);
							
						} else {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.givenLilayaPresent1, true);
						}
					}
				});
			}
		}
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
			if(isLilayaAngryAtPlayerDemonTF()) {
				generatedResponses.add(new Response("Гейша Lilaya", "Lilaya не заинтересована в том, чтобы выставлять напоказ свое кимоно или заниматься с вами сексом, пока сама не станет полноценным демоном.", null));
				
			} else {
				generatedResponses.add(new Response("Гейша Lilaya", "Спросить Lilaya, не хочет ли она надеть подарки, которые вы ей принесли.", LILAYA_GEISHA) {
					@Override
					public void effects() {
						setEntryFlags();
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA, true);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA, true);
						
						((Lilaya) Main.game.getNpc(Lilaya.class)).applyGeishaChange();
					}
				});
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_DADDY)) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_ACCEPTED) {
				if(!Daddy.isAvailable()) {
					generatedResponses.add(new Response("Встреча с [daddy.name]", Daddy.getAvailabilityText(), null));
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					generatedResponses.add(new Response("Встреча с [daddy.name]",
							"[style.italicsBad(Вы не можете попросить Lilaya пойти с вами на встречу с [daddy.name], пока в вашей группе есть спутники!)]",
							null));
					
				} else {
					generatedResponses.add(new Response("Встреча с [daddy.name]", "Убедить Lilaya пойти с вами и [daddy.name] на ужин.", DaddyDialogue.CONVINCING_LILAYA) {
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));
						}
					});
					
				}
	
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING)) {
				if(!Daddy.isAvailable()) {
					generatedResponses.add(new Response("Навестить [daddy.name]", Daddy.getAvailabilityText(), null));
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					generatedResponses.add(new Response("Навестить [daddy.name]",
							"[style.italicsBad(Вы не можете попросить Lilaya пойти с вами навестить [daddy.name], пока в вашей группе есть спутники!)]",
							null));
					
				} else  {
					generatedResponses.add(new Response("Навестить [daddy.name]", "Спросить Lilaya, не хочет ли она навестить [daddy.name] вместе с вами.", DaddyDialogue.MEETING) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							setEntryFlags();
							((Lilaya) Main.game.getNpc(Lilaya.class)).applyDinnerDateChange();
							
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
							Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
	
							if(Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
							}
							if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant() && !Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementMoney(500));
							}
						}
					});
					
				}
			}
		}
		
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
				&& Main.game.getPlayer().getClothingCurrentlyEquipped().stream().anyMatch(c -> c.isSelfTransformationInhibiting())
				&& Main.game.getPlayer().getClothingCurrentlyEquipped().stream().anyMatch(c -> c.isSealed())) {
			generatedResponses.add(new Response("Запечатанная проблема",
					"Сказать Lilaya, что на вас надеты зачарованные одежды, которые невозможно снять из-за другого заклинания.<br/>[style.italicsMinorGood(Lilaya снимет чары «служения» со всей вашей одежды!)]",
						LAB_JINX_REMOVAL){
				@Override
				public void effects() {
					for(AbstractClothing clothing : new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped())) {
						if(clothing.isSelfTransformationInhibiting()) {
							clothing.removeServitudeEnchantment();
						}
					}
				}
			});
		}
		
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_1) {
			if(Main.game.getCurrentDialogueNode()==LILAYA_ELLE_HELP) {
				generatedResponses.add(new Response("Местоположение Elle", "Вы уже спрашивали Lilaya о том, где может быть Elle!", null));
				
			} else {
				generatedResponses.add(new Response("Местоположение Elle",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestLilayaHelp)
							?"Спросить Lilaya, не напомнит ли она вам, куда телепортируется Elle."
							:"Попросить Lilaya помочь выяснить, куда телепортируется Elle.",
						LILAYA_ELLE_HELP){
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestLilayaHelp)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_ELLE_HELP_REPEAT"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_ELLE_HELP"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.wesQuestLilayaHelp, true);
						}
						setEntryFlags();
					}
				});
			}
		}
		
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.obtainedScientistClothing)) {
			generatedResponses.add(new Response("Наряд учёного",
					"Спросить Lilaya, нет ли у неё лишнего лабораторного халата, который вы могли бы взять.",
					LILAYA_SCIENTIST_OUTFIT){
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_lab_coat", false), false));
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_safety_goggles", false), false));
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.obtainedScientistClothing, true);
					setEntryFlags();
				}
			});
		}
		
		return generatedResponses;
	}
	
	public static final DialogueNode LAB_ENTRY = new DialogueNode("Лаборатория Lilaya", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_I_ARTHURS_TALE) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_PREGNANCY_BASE"));
					
					if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_PREGNANT"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_NOT_PREGNANT"));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR_BASE"));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ARTHUR"));
				
			} else {
				if(Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER) { // Lilaya a full demon:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END"));
					}
					
				} else { // Lilaya is not a full demon:
					if(isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION_PREGNANCY_RESOLVED"));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION_NO_PREGNANCY"));
						}
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_DEMON_REACTION"));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)) {
						if(Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_PREGNANT"));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_NOT_PREGNANT"));
						}
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_PREGNANCY_RESOLVED"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE"));
	
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_NAUGHTY_ROSE"));
						
						if(isLilayaAngryAtPlayerDemonTF()) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU_DEMON"));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END_DEMON"));
							}
							
						} else {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roseToldOnYou)) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_ROSE_TOLD_ON_YOU"));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ENTRY_BASE_END"));
							}
						}
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				if(index == 1) {
					return new Response("Согласиться",
							"Сказать Lilaya, что вы поможете ей убедить Lyssieth превратить её в полноценного демона.<br/>[style.italicsDemon(Это закончится тем, что Lilaya навсегда превратится в демона!)]",
							LAB_DEMON_TF_AGREE){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.RACE_DEMON;
						}
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50));
						}
					};
					
				} else if(index == 2) {
					return new Response("Отказаться",
							"Попытаться убедить Lilaya, что ей лучше остаться полудемоном.<br/>[style.italics(Вы всегда сможете передумать позже и сказать Lilaya, что поможете ей стать полноценным демоном.)]",
							LAB_DEMON_TF_REFUSE){
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon, true);
						}
					};
				}
				return null;
					
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_I_ARTHURS_TALE || Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_J_ARTHURS_ROOM) {
				if(index == 1) {
					return new Response("Согласиться", "Зная, насколько свирепой может стать ваша [lilaya.relation(pc)] в такие моменты, вы понимаете, что особого выбора у вас нет...", LAB_ARTHURS_TALE){
						@Override
						public void effects() {
							setEntryFlags();
						}
					};
				}
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults)
					&& Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				if(index==0) {
					return new Response("Тебя вышвыривают", "Очевидно, что Lilaya не настроена на разговоры, пока не решится вопрос с её беременностью...", LAB_EXIT_THROWN_OUT) {
						@Override
						public void effects() {
							setEntryFlags();
						}
					};
				}
				return null;
				
			} else {
				List<Response> generatedResponses = getLabEntryGeneratedResponses();
				
				// Return responses:
				if(index==0) {
					return new Response("Уйти", "Попрощаться с Lilaya и выйти из её лаборатории.", LAB) {
						@Override
						public void effects() {
							setEntryFlags();
							Main.game.getTextStartStringBuilder().append(
									"<p>Вы говорите Lilaya, что вам пора идти, и, попрощавшись, направляетесь к двери лаборатории, чтобы выйти.</p>");
						}
					};
					
				} else if (index == 1) {
					if(isLilayaAngryAtPlayerDemonTF()) {
						return new Response("Полноценный демон",
								"Сказать Lilaya, что вы поможете ей убедить её мать превратить её в полноценного демона.<br/>[style.italicsDemon(Это закончится тем, что Lilaya навсегда превратится в полноценного демона!)]",
								LAB_DEMON_TF_AGREE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.RACE_DEMON;
							}
							@Override
							public void effects() {
								setEntryFlags();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE_AFTER_REPEAT"));
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50));
							}
						};
						
					} else {
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_A_LILAYAS_TESTS) {
							return new Response("Тесты", "Дать Lilaya понять, что вы пришли, чтобы она могла провести над вами свои тесты.", AUNT_HOME_LABORATORY_TESTING){
								@Override
								public void effects() {
									setEntryFlags();
								}
							};
							
						} else {
							if(Main.game.getNpc(Arthur.class).getLocationPlace().getPlaceType().equals(PlaceType.LILAYA_HOME_LAB)) {
								return new Response("\"Тесты\"", "Lilaya не может проводить никаких «тестов» на тебе, пока Arthur всё еще находится в её лаборатории. Сначала найди ему подходящую комнату.", null);
								
							} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hadSexWithLilaya)) {
								return new Response("\"Тесты\"", "Дать Lilaya понять, что ты пришел, чтобы она могла провести над тобой еще больше своих «тестов».", AUNT_HOME_LABORATORY_TESTING_MORE_SEX){
									@Override
									public void effects() {
										setEntryFlags();
									}
								};
							} else {
								return new Response("Тесты", "Сказать Lilaya, что ты хочешь, чтобы она провела над тобой еще больше своих «тестов».", AUNT_HOME_LABORATORY_TESTING_REPEAT){
									@Override
									public void effects() {
										setEntryFlags();
									}
								};
							}
						}
					}

				} else if(index<10) {
					if(index-2 < generatedResponses.size()) {
						return generatedResponses.get(index-2);
					} else {
						return null;
					}
					
				} else if(index==11 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
					// Teleport
					return new Response("Кабинет Lyssieth", "Попросить Lilaya использовать резонансный камень, чтобы связаться с Lyssieth, и попросить её телепортировать тебя в её кабинет.", LyssiethPalaceDialogue.LYSSIETH_OFFICE_TALK){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
							if(Main.game.getPlayer().hasCompanions()) { //TODO test
								for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
									companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
								}
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_TELEPORT_TO_LYSSIETHS_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_TELEPORT_TO_LYSSIETHS_OFFICE"));
							}
						}
					};
				}
				
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode LILAYA_END_SEX = new DialogueNode("Встать", "Lilaya действительно нужно вернуться к работе.", true) {

		@Override
		public String getDescription() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return "Lilaya выглядит довольно злой, возможно, тебе стоило вытащить...";
			} else {
				return "Lilaya действительно нужно вернуться к работе.";
			}
		}

		@Override
		public String getContent() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX_CREAMPIE");
			} else {
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lilaya.class))==0) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX_NO_ORGASM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_END_SEX");
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
					return new Response("Тебя вышвыривают", "Возможно, лучше оставить Lilaya в покое, чтобы она немного остыла.", Lab.LAB_EXIT_THROWN_OUT){
						@Override
						public void effects() {
							if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
									&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, true);
							}
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return new Response("Продолжить", "Покинуть лабораторию и дать Lilaya продолжить работу.", Lab.LAB_EXIT) {
						@Override
						public void effects() {
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LAB_EXIT = new DialogueNode("Лаборатория Lilaya", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_EXIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LAB_EXIT_THROWN_OUT = new DialogueNode("Лаборатория Lilaya", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_EXIT_THROWN_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LAB_DEMON_TF_AGREE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_AGREE_CORE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Телепортирован(а)", "Lyssieth телепортирует вас троих обратно в свой дворец...", LyssiethPalaceDialogue.LILAYA_DEMON_TF_START){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) { //TODO test
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
							}
						}
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_DEMON_TF_REFUSE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_DEMON_TF_REFUSE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode LILAYA_PRESENT = new DialogueNode("Лаборатория Lilaya", "", true) {
		
		@Override
		public String getContent() {

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent3)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_3");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.givenLilayaPresent2)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_2");
					
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_PRESENT_1");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_GEISHA = new DialogueNode("Спальня Lilaya", "", true) {
		

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Секс", "Начать заниматься сексом с Lilaya.",
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
						null,
						null),
						END_SEX_GEISHA,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SEX_START")){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
					}
				};

			} if (index == 2) {
				return new ResponseSex("Секс в подчиненной позиции", "Начать заниматься подчиненным сексом с Lilaya.",
						Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						END_SEX_GEISHA,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SUBMISSIVE_SEX_START")){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
					}
				};

			} else if (index == 3) {
				return new Response("Уйти",
						"Сказать Lilaya, что она выглядит потрясающе, но ты пока не хочешь заниматься с ней сексом.",
						RoomPlayer.ROOM){
					@Override public void effects() {

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						Main.game.getNpc(Lilaya.class).resetInventory(false);
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
						
						Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_GEISHA_SEX_REFUSED"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode END_SEX_GEISHA = new DialogueNode("Завершено", "Lilaya с удовлетворенным вздохом падает на кровать.", true) {
		
		@Override
		public String getDescription() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return "Lilaya выглядит довольно злой, возможно, тебе стоило вытащить...";
			} else {
				return "Lilaya с удовлетворенным вздохом падает на кровать.";
			}
		}

		@Override
		public String getContent() {
			if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA_CREAMPIE");
				
			} else {
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lilaya.class))==0) {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA_NO_ORGASM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "END_SEX_GEISHA");
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
					return new Response("Тебя вышвыривают", "Возможно, лучше оставить Lilaya в покое, чтобы она немного остыла.", RoomPlayer.ROOM){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
							if((Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.CREAMPIE_VAGINA) || ((Lilaya)Main.game.getNpc(Lilaya.class)).isAmazonsSecretImpregnation())
									&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults, true);
							}
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);

							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
					
				} else {
					return new Response("В свою комнату", "Вернуться в свою комнату.", RoomPlayer.ROOM){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, true);
							Main.game.getNpc(Lilaya.class).washAllOrifices(true);
							
							Main.game.getNpc(Lilaya.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LAB_JINX_REMOVAL = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_JINX_REMOVAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};

	public static final DialogueNode LILAYA_ELLE_HELP = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_SCIENTIST_OUTFIT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_SCIENTIST_OUTFIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LAB_LILAYA_HUG = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 5));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaHug, true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_LILAYA_HUG");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return LAB_ENTRY.getResponse(0, index);
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			if(getJinxedClothingExample() != null) {
				UtilText.addSpecialParsingString(getJinxedClothingExample().getName(), true);
				UtilText.addSpecialParsingString((getJinxedClothingExample().getClothingType().isPlural()?"them":"it"), false);
				UtilText.addSpecialParsingString((getJinxedClothingExample().getClothingType().isPlural()?"these":"this"), false);
			}
			
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Что случилось?", "Спросить у Lilaya, что случилось.", LILAYA_EXPLAINS_ESSENCES_2) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceBottledDiscovered)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essenceOrgasmDiscovered)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.essencePostCombatDiscovered)) {
							Main.game.getPlayer().incrementEssenceCount(1, false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES_2 = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Слушать", "Слушать, как Lilaya показывает, как использовать накопленные эссенции для зачарования предметов.", LILAYA_EXPLAINS_ESSENCES_3);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_EXPLAINS_ESSENCES_3 = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Поблагодарить её", "Поблагодарить Lilaya за то, что она показала, как зачаровывать предметы.", LAB_EXIT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_EXPLAINS_ESSENCES_END")
								+ Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_UTIL_COMPLETE));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ESSENCE_EXTRACTION = new DialogueNode("Лаборатория Lilaya", "-", true, false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ESSENCE_EXTRACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index == 1) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=1) {
						return new Response("Извлечь (1)", "Извлечь одну из ваших арканных эссенций.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								
								Main.game.getTextEndStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Предмет добавлен в инвентарь:</b> <b>" + (ItemType.BOTTLED_ESSENCE_ARCANE).getDisplayName(true) + "</b></p><p>Теперь у вас есть <b>"+count+" "+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b> в вашем инвентаре.</p>");
								Main.game.getPlayer().incrementEssenceCount(-1, false);
							}
						};
						
					} else {
						return new Response("Извлечь (1)", "У вас нет арканных эссенций!", null);
					}
				} else {
					return new Response("Извлечь (1)", "В вашем инвентаре нет свободного места!", null);
				}
				
				
			} else if(index == 2) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=5) {
						return new Response("Извлечь (5)", "Извлечь пять ваших арканных эссенций.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<5; i++) {
									Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Предметы добавлены в инвентарь:</b> <b>5x</b> <b>" + ItemType.BOTTLED_ESSENCE_ARCANE.getDisplayName(true) + "</b></p><p>Теперь у вас есть <b>"+count+" "+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b> в вашем инвентаре.</p>");
								Main.game.getPlayer().incrementEssenceCount(-5, false);
							}
						};
						
					} else {
						return new Response("Извлечь (5)", "У вас недостаточно арканных эссенций!", null);
					}
				} else {
					return new Response("Извлечь (5)", "В вашем инвентаре нет свободного места!", null);
				}
				
			} else if(index == 3) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=25) {
						return new Response("Извлечь (25)", "Извлечь двадцать пять единиц вашей арканы.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<25; i++) {
									Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Предметы добавлены в инвентарь:</b> <b>25x</b> <b>" + ItemType.BOTTLED_ESSENCE_ARCANE.getDisplayName(true) + "</b></p><p>Теперь у вас есть <b>"+count+" "+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b> в вашем инвентаре.</p>");
								Main.game.getPlayer().incrementEssenceCount(-25, false);
							}
						};
						
					} else {
						return new Response("Извлечь (25)", "У вас недостаточно арканных эссенций!", null);
					}
				} else {
					return new Response("Извлечь (25)", "В вашем инвентаре нет свободного места!", null);
				}
				
			} else if(index == 4) {
				if((!Main.game.getPlayer().isInventoryFull() || Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE)))) {
					if(Main.game.getPlayer().getEssenceCount()>=1) {
						return new Response("Извлечь (все)", "Извлечь всю вашу аркану.", ESSENCE_EXTRACTION_BOTTLED) {
							@Override
							public void effects() {
								for(int i =0; i<Main.game.getPlayer().getEssenceCount(); i++) {
									Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false, false);
								}
								int count = Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.BOTTLED_ESSENCE_ARCANE));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Grabbing another vial, you set about repeating the process several times..."
										+ "</p>"
										+ "<p style='text-align:center;'>"
											+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Предметы добавлены в инвентарь:</b> <b>"+Main.game.getPlayer().getEssenceCount()+"x</b> <b>"
												+ ItemType.BOTTLED_ESSENCE_ARCANE.getDisplayName(true) + "</b></p><p>Теперь у вас есть <b>"+count+" "+(count>1?ItemType.BOTTLED_ESSENCE_ARCANE.getNamePlural(true):ItemType.BOTTLED_ESSENCE_ARCANE.getName(true))+"</b> в вашем инвентаре.</p>");
								Main.game.getPlayer().incrementEssenceCount(-Main.game.getPlayer().getEssenceCount(), false);
								
							}
						};
						
					} else {
						return new Response("Извлечь (все)", "У вас нет эссенций!", null);
					}
				} else {
					return new Response("Извлечь (все)", "В вашем инвентаре нет свободного места!", null);
				}
				
			} else if (index == 0) {
				return new Response("Назад", "Хватит извлекать эссенции.", LAB_ENTRY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.essenceExtractionKnown, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ESSENCE_EXTRACTION_BOTTLED = new DialogueNode("Лаборатория Lilaya", "-", true, false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "ESSENCE_EXTRACTION_BOTTLED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ESSENCE_EXTRACTION.getResponse(0, index);
		}
	};
	
	
	public static final DialogueNode LILAYA_CURRENT_DATE_TALK = new DialogueNode("Лаборатория Lilaya", "-", true, false) {
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_CURRENT_DATE_TALK_KNOW_TRUTH");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_CURRENT_DATE_TALK");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поблагодарить её", "Поблагодарить Lilaya за информацию (или её отсутствие) и подумать о том, чтобы спросить её о чём-нибудь другом.", LAB_EXIT);
			} else {
				return null;
			}
		}
	};
	
	
	//----------------------------------

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Возвращение домой", "Спросить Lilaya, нашла ли она способ отправить тебя обратно домой.", AUNT_HOME_LABORATORY_TESTING_ARTHUR){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_A_LILAYAS_TESTS) {
							Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME);
							((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
						}
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_REPEAT = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_REPEAT");
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						!Main.game.getPlayer().isTaur()
							?"Сесть"
							:"Сделать шаг вперёд",
						"Ты прекрасно понимаешь, почему Lilaya так смущается из-за этих «тестов»...",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else if (index == 2) {
				return new Response("Отклонить", "Сказать Lilaya, что ты передумал. Хотя она, вероятно, будет немного разочарована, ты всегда сможешь вернуться позже и принять её предложение, если передумаешь.", LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_LEAVE"));
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_MORE_SEX = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isTaur()) {
					return new ResponseSex("Секс",
							"Начать заниматься сексом с Lilaya.",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMSitting(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.SITTING_IN_LAP))),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_START")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				} else {
					return new ResponseSex("Секс",
							"Начать заниматься сексом с Lilaya.",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
									null,
									null,
									ResponseTag.PREFER_ORAL),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_START_TAUR")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				}


			} else if (index == 2) {
				return new Response("Остановиться", "Сказать Lilaya, что тебе пора идти. Хотя она определенно будет разочарована тем, что ты уходишь так скоро, ты всегда сможешь вернуться позже, если передумаешь.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_MORE_SEX_STOP"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_ARTHUR = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						"«Тесты»",
						"Принять предложение Lilaya провести еще несколько «тестов». Ты не совсем уверен в её истинных намерениях, но полагаешь, что сможешь остановить её, если она начнет выкидывать что-нибудь эдакое.",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA);

			} else if (index == 2) {
				return new Response("Отклонить",
						"Скажите Lilaya, что вы не настроены на подобное. Она, вероятно, немного расстроится, но вы всегда сможете вернуться позже и принять её предложение, если передумаете.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_ARTHUR_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Откройте рот",
						"Позвольте Lilaya просунуть палец вам в рот. В конце концов, может, это всего лишь часть теста?",
						AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX);

			} else if (index == 2) {
				return new Response("Прекратить это",
						"Встаньте и скажите Lilaya, что это заходит слишком далеко. Хотя она, несомненно, будет расстроена таким внезапным концом её ухаживаний, вы уверены, что она попробует снова, если вы передумаете в будущем.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isTaur()) {
					return new ResponseSex("Позволить этому случиться",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"Вы знаете, что всё это может закончиться только одним способом, и тот факт, что Lilaya напоминает вам вашу тетю Лили, делает процесс еще более возбуждающим..."
								:"Вы знаете, что всё это может закончиться только одним способом. Хотя Lilaya напоминает вам вашу [lilaya.relation(pc)] Лили, вы не думаете, что это помешает вам насладиться процессом...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMSitting(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.SITTING_IN_LAP))),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_START")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
					
				} else {
					return new ResponseSex("Позволить этому случиться",
							Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
								?"Вы знаете, что всё это может закончиться только одним способом, и тот факт, что Lilaya напоминает вам вашу тетю Лили, делает процесс еще более возбуждающим..."
								:"Вы знаете, что всё это может закончиться только одним способом. Хотя Lilaya напоминает вам вашу [lilaya.relation(pc)] Лили, вы не думаете, что это помешает вам насладиться процессом...",
							Util.newArrayListOfValues(Fetish.FETISH_INCEST), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Lilaya.class)),
									null,
									null,
									ResponseTag.PREFER_ORAL),
							null,
							null,
							LILAYA_END_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_START_TAUR")){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.hadSexWithLilaya, true);
							Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE, false);
						}
					};
				}

			} else if (index == 2) {
				return new Response("Прекратить это",
						"Встань и скажи Lilaya, что всё это зашло слишком далеко. Хотя она, несомненно, расстроится из-за такого внезапного конца, ты уверен, что если ты передумаешь в будущем, она снова попытается с тобой заигрывать.",
						LAB_EXIT){
					@Override public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "AUNT_HOME_LABORATORY_TESTING_HORNY_LILAYA_WANTS_SEX_DECLINED"));
					}
				};

			} else {
				return null;
			}
		}
	};

	private static AbstractClothing getJinxedClothingExample() {
		for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(c.isSealed()) {
				return c;
			}
		}
		return null;
	}
	
	public static final DialogueNode LILAYA_FRIEND_ACCOMMODATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_FRIEND_ACCOMMODATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Теперь Lilaya разрешает тебе приглашать друзей к себе домой!", LAB_EXIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DOLL_STORAGE = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_DOLL_STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Теперь Lilaya разрешает тебе хранить кукол в её особняке!", LAB_EXIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_SLAVER_RECOMMENDATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_SLAVER_RECOMMENDATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Жилье", "Согласись с наблюдением Lilaya о том, что тебе понадобится место для содержания твоих рабов.", LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION) {
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_NEED_RECOMMENDATION) {
							Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LILAYA_SLAVER_RECOMMENDATION_SLAVE_ACCOMMODATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Теперь, когда у тебя есть рекомендательное письмо от Lilaya, тебе стоит вернуться в Переулок Работорговцев и поговорить с [finch.name].", LAB_EXIT);
			}
			return null;
		}
	};

	public static final DialogueNode LAB_ARTHURS_TALE = new DialogueNode("Лаборатория Lilaya", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lab", "LAB_ARTHURS_TALE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseEffectsOnly("Расчистить кладовую", "Отправляйся в кладовую лаборатории и помоги Rose расчистить её, чтобы Arthur мог использовать её как спальню.") {
					@Override
					public void effects() {
						Cell arthurRoomCell = addArthurRoom();
						
						Main.game.getPlayer().setLocation(arthurRoomCell);
						Main.game.getNpc(Arthur.class).setLocation(arthurRoomCell, true);
						
						Main.game.setContent(new Response("", "", PlaceUpgrade.LILAYA_ARTHUR_ROOM.getInstallationDialogue(arthurRoomCell)));
					}
				};
			}
			return null;
		}
	};
	
}
