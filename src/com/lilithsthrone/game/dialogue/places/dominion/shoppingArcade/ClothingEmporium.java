package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.4
 * @author Innoxia
 */
public class ClothingEmporium {
	
	public static final String NYAN_HIDING_DAY_ID = "nyanHidingDay";
	
	public static String incrementAffection(GameCharacter character, float increment, float minimumLimit, float maximumLimit) {
		float currentAffection = character.getAffection(Main.game.getPlayer());
		
		
		if(increment>=0) {
			increment = Math.min(increment, Math.max(0, maximumLimit-currentAffection));
		} else {
			increment = Math.max(increment, Math.min(0, minimumLimit-currentAffection));//TODO test
		}
		
		if(increment==0 || (increment>0 && currentAffection>=maximumLimit)) {
			return "<p style='text-align:center'>"
						+ UtilText.parse(character, "[style.italicsDisabled([npc.Name] не получает дополнительной привязанности к вам от этого действия...)]")
						+ "<br/>"
						+ AffectionLevel.getDescription(character, Main.game.getPlayer(), true)
					+ "</p>";
		}
		if(increment<0 && currentAffection<=minimumLimit) {
			return "<p style='text-align:center'>"
						+ UtilText.parse(character, "[style.italicsDisabled([npc.Name] не теряет привязанности к вам от этого действия...)]")
						+ "<br/>"
						+ AffectionLevel.getDescription(character, Main.game.getPlayer(), true)
					+ "</p>";
		}
		
		return character.incrementAffection(Main.game.getPlayer(), increment);
	}
	
	private static void applyRepeatMeetingReactionUpdates() {
		if(getNyan().isVisiblyPregnant()) {
			getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanApologised, true);
		}
	}
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Магазин одежды Nyan (Снаружи)", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXTERIOR");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isHourBetween(9, 20)) {
						return new Response("Войти", "Магазин одежды Nyan сейчас закрыт. Придется зайти позже...", null);
						
					} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.nyanIntroduced)) {
						return new Response("Войти", "Войти в магазин одежды Nyan.", SHOP_CLOTHING_REPEAT);
						
					} else {
						return new Response("Войти", "Войти в магазин одежды Nyan.", SHOP_CLOTHING);
					}
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Кладовая", "Nyan проводит вас в кладовую.", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nyanIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_STOCK_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_STOCK_ROOM");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_REPEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getAuthor() {
			if(getNyan().isPregnant() && !getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return "Дунер";
			} else {
				return "Инноксия";
			}
		}
		@Override
		public String getContent() {
			if(!Main.game.getCharactersPresent().contains(getNyan())) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_REPEAT_NO_NYAN");
				
			} else {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)) {
					if(Main.game.getDialogueFlags().getSavedLong(NYAN_HIDING_DAY_ID)<Main.game.getDayNumber()) {
						return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_NEXT_DAY");
					} else {
						return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING");
					}
				} else if(getNyan().isVisiblyPregnant() && !getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT_PREGNANT");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT");
				}
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)
					|| !Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)
					|| !Main.game.getCharactersPresent().contains(getNyan())) {
				return null;
			}

			if(index==0) {
				return "Торговать";
			} else if(index==1) {
				return "Поговорить";
			} else {
				return null;
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().getSavedLong(NYAN_HIDING_DAY_ID)<Main.game.getDayNumber()) {
						return new Response("Следовать", "Снова пойти за Nyan в её кладовую и послушать, что она скажет.", NYAN_HIDING_END) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHiding, false);
							}
						};
						
					} else {
						return new Response("Уйти", "Поскольку Nyan прячется, а помощника в лавке нет, вам остается только уйти.", EXTERIOR) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_LEAVE"));
							}
						};
					}
				}
				
				return null;
			}
			
			if(responseTab==1) {
				float currentAffection = getNyan().getAffection(Main.game.getPlayer());
				
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanDating)) {
					if(index==1) {
						return new Response("Девушка",
								"Сказать Nyan, что вы передумали и хотите, чтобы она стала вашей девушкой.",
								NYAN_HIDING_END_SHOP) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanDating, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_REPEAT_GIRLFRIEND"));
								Main.game.setResponseTab(1);
							}
						};
					}
					
				} else {
					if(index==1) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo)) {
							return new Response("Поговорить", "Вы уже общались с Nyan сегодня. Вы сможете повторить это действие завтра.", null);
							
						} else {
							return new Response("Поговорить", "Поговорить с Nyan немного, чтобы узнать её получше.", ROMANCE_TALK) {
								@Override
								public void effects() {
									UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_BASE"));
									
									List<String> topics = Util.newArrayListOfValues(
											"NYAN_NOVELS",
											"NYAN_WORK",
											"NYAN_HOBBIES",
											"NYAN_HOME");
									long lowestValue = 1_000_000;
									for(String topic : topics) {
										if(Main.game.getDialogueFlags().getSavedLong(topic)<lowestValue) {
											lowestValue = Main.game.getDialogueFlags().getSavedLong(topic);
										}
									}
									long thanksJava = lowestValue;
									topics.removeIf(s -> Main.game.getDialogueFlags().getSavedLong(s)>thanksJava);
									String topicSelected = Util.randomItemFrom(topics);
									if(Main.game.getDialogueFlags().getSavedLong("NYAN_NOVELS")==-1) { // Make sure that novels is first topic
										topicSelected = "NYAN_NOVELS";
									}
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_"+topicSelected));
									Main.game.getDialogueFlags().incrementSavedLong(topicSelected, 1);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_FINAL"));
									
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTalkedTo, true);
								
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==2) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanComplimented)) {
							return new Response("Сделать комплимент", "Вы уже делали Nyan комплимент сегодня. Вы сможете повторить это действие завтра.", null);
							
						} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo) && currentAffection<50) {
							return new Response("Сделать комплимент",
									"Вы понимаете, что попытка сделать Nyan комплимент, не поговорив с ней предварительно, закончится катастрофой.<br/>[style.italicsMinorBad(Вам нужно сначала поговорить с Nyan, прежде чем делать комплименты!)]",
									null);
							
						} else {
							return new Response("Сделать комплимент", "Сделать комплимент внешности и способностям Nyan.", ROMANCE_COMPLIMENT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanComplimented, true);
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==3) {
						int requiredAffection = 40;
						if(currentAffection<requiredAffection) {
							return new Response(
									"Флиртовать",
									"Вы понимаете, что попытка флиртовать с Nyan закончится катастрофой. Сначала стоит узнать её получше.<br/>[style.italicsMinorBad(Требуется, чтобы привязанность Nyan была не ниже "+requiredAffection+", а сейчас она составляет  "+currentAffection+".)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFlirtedWith)) {
							return new Response("Флиртовать", "Вы уже флиртовали с Nyan сегодня. Повторить можно будет завтра.", null);
							
						} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo) && currentAffection<50) {
							return new Response("Флиртовать",
									"Можно заметить, что попытка флиртовать с Nyan, не поговорив с ней предварительно, закончится катастрофой.<br/>[style.italicsMinorBad(Вам нужно поговорить с Nyan, прежде чем флиртовать с ней!)]",
									null);
							
						} else {
							return new Response("Флиртовать", "Немного пофлиртовать с Nyan.", ROMANCE_FLIRT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 3, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFlirtedWith, true);
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==6) {
						int requiredAffection = 50;
						if(currentAffection<requiredAffection) {
							return new Response(
									"Идти",
									"Вам кажется, что Nyan еще не готова пойти с вами на прогулку...<br/>[style.italicsMinorBad(Требуется привязанность Nyan не менее "+requiredAffection+", а сейчас она составляет  "+currentAffection+".)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWalked)) {
							return new Response("Идти", "Вы уже гуляли с Nyan сегодня. Повторить можно будет завтра.", null);
							
						} else {
							return new Response("Идти", "Спросить Nyan, не хочет ли она прогуляться с вами.", ROMANCE_WALK) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
					}
					
					// Requires first kiss:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFirstKissed)) {
						if(index==4) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHeadPatted)) {
								return new Response("Погладить по голове", "Вы уже гладили Nyan по голове сегодня. Повторить можно будет завтра.", null);
								
							} else {
								return new Response("Погладить по голове", "Погладить Nyan по голове и сказать ей, что она хорошая девочка.", ROMANCE_HEAD_PAT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHeadPatted, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
							
						} else if(index==5) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanKissed)) {
								return new Response("Поцеловать", "Вы уже целовали Nyan сегодня. Вы сможете повторить это завтра.", null);
								
							} else {
								return new Response("Поцеловать", "По тому, как Nyan смотрит на вас, можно понять, что она хочет еще одного поцелуя. Наклонитесь вперед и дайте ей то, чего она желает.", ROMANCE_KISS) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanKissed, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}

						if(index==7) {
							int requiredAffection = 60;
							if(currentAffection<requiredAffection) {
								return new Response(
										"Свидание в ресторане",
										"Учитывая нервозность Nyan, вам нужно быть уверенным, что она справится с перспективой публичного свидания с вами...<br/>[style.italicsMinorBad(Требуется привязанность Nyan не менее "+requiredAffection+", а сейчас она составляет  "+currentAffection+".)]",
										null);
								
							} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateRequested)) {
								if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY) {
									return new Response("Свидание в ресторане",
											"Спросить Nyan, не хочет ли она сходить с вами на свидание в ресторан «Дубовая поляна».<br/>[style.italicsMinorBad(Это действие нельзя совершать в пятницу или субботу!)]",
											null);
									
								} else {
									return new Response("Свидание в ресторане",
											"Спросить Nyan, не хочет ли она сходить с вами на свидание в ресторан «Дубовая поляна».",
											ROMANCE_DATE_REQUESTED) {
										@Override
										public void effects() {
											applyRepeatMeetingReactionUpdates();
										}
									};
								}
							}
						}
					}
					
					// Requires affection of over 60, which can only be achieved after first date (added flag check just to be sure):
					if(currentAffection>60 || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted)) {
						if(index==7) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanMakeOut)) {
								return new Response("Миловаться", "Вы уже миловались с Nyan сегодня. Вы сможете повторить это завтра.", null);
								
							} else {
								return new Response("Миловаться", "Сразу видно, что Nyan отчаянно жаждет страстного физического контакта с тобой. Наклонись вперед и начни пылко целовать её.", ROMANCE_MAKE_OUT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 60, 100));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanMakeOut, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}
					}
					
					if(index==8) {
						if(getNyan().isVisiblyPregnant()) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTummyRubbed)) {
								return new Response("Погладить животик", "Ты уже гладил Nyan по животику сегодня. Ты сможешь повторить это завтра.", null);
								
							} else {
								return new Response("Погладить животик",
										"Погладить беременный животик Nyan и спросить, как она справляется с беременностью.",
										ROMANCE_RUB_TUMMY) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTummyRubbed, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}
					}
					
					if(index==10) {
						int requiredAffection = 40;
						if(currentAffection<requiredAffection) {
							return new Response(
									"Подарок",
									"Подарок может оказаться слишком сильным потрясением для этой нежной кошкодевочки прямо сейчас...<br/>[style.italicsMinorBad(Требуется привязанность Nyan не менее "+requiredAffection+", а сейчас она составляет  "+currentAffection+".)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanGift)) { // This is set in Nyan's getGiftReaction() method
							return new Response("Подарок", "Ты уже дарил Nyan подарок сегодня. Ты сможешь повторить это завтра.", null);
						} else {
							return new Response("Подарок", "Подарить Nyan подарок (открывает экран выбора подарков).", ROMANCE_GIFT) {
								@Override
								public DialogueNode getNextDialogue() {
									return GiftDialogue.getGiftDialogue(getNyan(), ROMANCE_GIFT, 1);
								}
								@Override
								public void effects() {
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
					}
				}
				
				if(index == 0) {
					return new Response("Уйти", "Сказать Nyan, что тебе пора идти.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
							applyRepeatMeetingReactionUpdates();
						}
					};
				}
				
				return null;
				
			} else if(responseTab==0) {
				String descriptionStart = "Спросить её ";
				if(!Main.game.getCharactersPresent().contains(getNyan())) {
					descriptionStart = "Посмотреть, ";
				}
				
				if (index == 1) {
					return new ResponseTrade("Женская одежда", descriptionStart+"какая женская одежда есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("Женское белье", descriptionStart+"какое женское нижнее белье есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleUnderwear()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 3) {
					return new ResponseTrade("Женские аксессуары", descriptionStart+"какие женские аксессуары есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 6) {
					return new ResponseTrade("Мужская одежда", descriptionStart+"какая мужская одежда есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 7) {
					return new ResponseTrade("Мужское нижнее белье", descriptionStart+"какое мужское нижнее белье есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleLingerie()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 8) {
					return new ResponseTrade("Мужские аксессуары", descriptionStart+"какие мужские аксессуары есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 11) {
					return new ResponseTrade("Унисекс одежда", descriptionStart+"какая унисекс-одежда есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 12) {
					return new ResponseTrade("Унисекс белье", descriptionStart+"какое унисекс-нижнее белье есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousLingerie()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 13) {
					return new ResponseTrade("Аксессуары унисекс", descriptionStart+"какие унисекс-аксессуары есть в наличии.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 5) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new ResponseTrade("Зачарованная одежда", descriptionStart+" какая особенная одежда сейчас есть в наличии.", getNyan()){
							@Override
							public void effects() {
								getNyan().clearNonEquippedInventory(false);
		
								for (AbstractClothing c : getNyan().getSpecials()) {
									if(getNyan().isInventoryFull()) {
										break;
									}
									getNyan().addClothing(c, false);
								}
							}
						};
						
					} else if(!Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new Response("Зачарованная одежда",
								"Спросить у Nyan, есть ли у неё в продаже зачарованная одежда.<br/>[style.italicsQuestRomance(Это запустит романтический квест с Nyan!)]",
								SHOP_ENCHANTED_CLOTHING) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.RELATIONSHIP_NYAN_HELP));
							}
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN) {
						return new Response("Доложить", "Сказать Nyan, что ты разобрался с поставщиками.", SHOP_REPORT_BACK) {
							@Override
							public void effects() {
								getNyan().setSellModifier(1.25f);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
								Main.game.getTextEndStringBuilder().append(getNyan().setAffection(Main.game.getPlayer(), 30));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.SIDE_UTIL_COMPLETE));
							}
						};
						
					} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
						return new Response("Доложить", "Вернуться к Nyan после того, как разберешься с поставщиками.", null);
						
					} else {
						return new Response("Предложить помощь", "Сказать Nyan, что ты поможешь ей решить проблему с поставщиками.", SHOP_OFFER_HELP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getNyan().setAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP));
							}
						};
					}
					 
				} else if (index == 0) {
					return new Response("Уйти", "Сказать Nyan, что тебе пора идти.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
						}
					};
				}
				
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SHOP_ENCHANTED_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_ENCHANTED_CLOTHING");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_OFFER_HELP");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_REPORT_BACK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_REPORT_BACK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Уйти", "Выйти из магазина.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT_EMBARRASSED"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHiding, true);
						Main.game.getDialogueFlags().setSavedLong(NYAN_HIDING_DAY_ID, Main.game.getDayNumber());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NYAN_HIDING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Девушка",
						"Сказать Nyan, что хочешь, чтобы она стала твоей девушкой.",
						NYAN_HIDING_END_SHOP) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanDating, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END_GIRLFRIEND"));
						Main.game.setResponseTab(1);
					}
				};
				
			} else if(index==2) {
				return new Response("Отклонить",
						"Сказать Nyan, что в данный момент ты не готов к серьезным отношениям.<br/>[style.italicsMinorGood(Ты сможешь передумать и попросить Nyan стать твоей девушкой в любое время.)]",
							NYAN_HIDING_END_SHOP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END_DECLINE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NYAN_HIDING_END_SHOP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_TALK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 55*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_COMPLIMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_FLIRT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_HEAD_PAT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_RUB_TUMMY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_RUB_TUMMY"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_WALK = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Cell destination = null;
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWalked)) {
				destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL"));
				
			} else {
				Map<AbstractPlaceType, String> places = Util.newHashMapOfValues(
						new Value<>(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, "ASHLEY"),
						new Value<>(PlaceType.SHOPPING_ARCADE_ANTIQUES, "ANTIQUES"),
						new Value<>(PlaceType.SHOPPING_ARCADE_KATES_SHOP, "KATE"),
						new Value<>(PlaceType.SHOPPING_ARCADE_PIXS_GYM, "PIX"),
						new Value<>(PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, "RALPH"),
						new Value<>(PlaceType.SHOPPING_ARCADE_RESTAURANT, "RESTAURANT"),
						new Value<>(PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, "VICKY"));
				AbstractPlaceType place = Util.randomItemFrom(places.keySet());
				destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(place);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_START"));
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_"+places.get(place)));
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_END"));
			}
			
			Cell finalDestination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getClosestCell(destination.getLocation(), PlaceType.SHOPPING_ARCADE_PATH);
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, finalDestination.getLocation(), false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFirstKissed)) {
				if(index==1) {
					return new Response("Продолжить", "Продолжить прогулку с Nyan по Аркаде.", ROMANCE_WALK_INITIAL_CONTINUE);
				}
				
			} else {
				if(index==1) {
					return new Response("Назад", "Проводить Nyan обратно в её лавку.", ROMANCE_WALK_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_NO_EVENT"));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.getPlayer().hasArms()) {
						return new Response("Взяться за руки", "Так как у тебя нет рук, ты не можешь взять Nyan за руку!", null);
						
					} else if(Main.game.getPlayer().isArmMovementHindered()) {
						return new Response("Взяться за руки", "Так как твоё [pc.arm] движение сейчас сковано одеждой, ты не можешь с комфортом держать Nyan за руку!", null);
						
					} else {
						return new Response("Взяться за руки", "Взять Nyan за руку, пока ты провожаешь её обратно в лавку.", ROMANCE_WALK_END) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_HOLD_HANDS"));
								Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
							}
						};
					}
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Поцеловать", "Так как у тебя нет доступа к рту, ты не можешь поцеловать Nyan!", null);
						
					} else {
						return new Response("Поцеловать", "Воспользоваться случаем и поцеловать Nyan на людях.", ROMANCE_WALK_END) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_KISS"));
								Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_INITIAL_CONTINUE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Cell destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_RESTAURANT);
				
			Cell finalDestination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getClosestCell(destination.getLocation(), PlaceType.SHOPPING_ARCADE_PATH);
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, finalDestination.getLocation(), false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_CONTINUE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Назад", "Сопроводить Nyan обратно в её лавку.", ROMANCE_WALK_INITIAL_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_INITIAL_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Поцеловать", "Так как у тебя нет доступа к рту, ты не можешь поцеловать Nyan!", null);
					
				} else {
					return new Response("Поцеловать", "Воспользоваться случаем и поцеловать Nyan.", ROMANCE_WALK_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END_KISS"));
							Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFirstKissed, true);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Не целоваться", "Вы всегда сможете повторить этот опыт в другой раз...", ROMANCE_WALK_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END_NO_KISS"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
			getNyan().setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWalked, true);
			Main.game.setResponseTab(1);
			
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
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_DATE_REQUESTED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanRestaurantDateRequested, true);
			Cell shoppingArcadeCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SHOPPING_ARCADE);
			Cell nyanApartmentCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(new Vector2i(shoppingArcadeCell.getLocation().getX()-1, shoppingArcadeCell.getLocation().getY()));
			nyanApartmentCell.getPlace().setPlaceType(PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_DATE_REQUESTED");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_MAKE_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Прервать долгий поцелуй.", POST_MAKEOUT);
				
			} else if(index==2) {
				return new ResponseSex("Куннилингус", "Предложить возбужденной кошкодевочке отсосать ей прямо здесь, в кладовой.",
						true, true,
						new SMNyanSex(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotAgainstWall.BACK_TO_WALL))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
							}
//							@Override
//							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
//								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> defaultMap = super.exposeAtStartOfSexMapExtendedInformation();
//								
//								defaultMap.get(true).putIfAbsent(getNyan(), new HashMap<>());
//								defaultMap.get(true).get(getNyan()).put(CoverableArea.VAGINA, new ArrayList<>());
//								
//								return defaultMap;
//							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_ORAL,
						UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex("Фингеринг", "Задрать мини-юбку Nyan и начать ласкать её пальцами, не прекращая поцелуев.",
						true, true,
						new SMNyanSex(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotAgainstWall.BACK_TO_WALL))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_ORAL,
						UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			List<Response> receiveOralResponses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("Получить минет", "Поскольку у тебя нет доступа к члену, ты не можешь заставить Nyan сосать твой член!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Получить минет", "Заставить Nyan опуститься на колени и отсосать вам член прямо здесь, в кладовой.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.AGAINST_WALL,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotAgainstWall.BACK_TO_WALL)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)));
										}
										@Override
										public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									POST_ORAL,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_RECEIVE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("Принять куннилингус", "Поскольку у тебя нет доступа к влагалищу, ты не можешь заставить Nyan заняться куннилингусом!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Принять куннилингус", "Заставить Nyan опуститься на колени и отлизать вас прямо здесь, в кладовой.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.AGAINST_WALL,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotAgainstWall.BACK_TO_WALL)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
										}
										@Override
										public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									POST_ORAL,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			if(Main.game.isAnalContentEnabled() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
					receiveOralResponses.add(new Response("Спросить об анальном сексе", "Спросить Nyan, будет ли ей комфортно заниматься анальным сексом с тобой.", ANAL_TALK));
					
				} else {
					receiveOralResponses.add(
							new ResponseSex("Заняться анилингусом",
								"Заставить возбужденную кошкодевочку подставить тебе свою задницу и начать её лизать.",
								true, true,
								new SMNyanSex(
										SexPosition.AGAINST_WALL,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAgainstWall.FACE_TO_WALL))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(
												new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.ANUS)));
									}
									@Override
									public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
										return false;
									}
								},
								null,
								null,
								POST_ORAL,
								UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_PERFORM_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
				}
			}
			if(index>0 && index-4<receiveOralResponses.size()) {
				return receiveOralResponses.get(index-4);
			}
			return null;
		}
	};

	public static final DialogueNode ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_MAKE_OUT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_MAKEOUT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "POST_MAKEOUT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_ORAL = new DialogueNode("Завершено", "На сегодня с Nyan хватит...", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().cleanAllDirtySlots(true);
			getNyan().cleanAllClothing(false, false);
			Main.game.setResponseTab(1);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "POST_ORAL");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_GIFT = new DialogueNode("Дарить подарок", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
}
