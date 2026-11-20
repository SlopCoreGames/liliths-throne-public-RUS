package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class RoomArthur {

	private static int getDaysRemainingUntilArcaneLightningUnlocked() {
		return (int) (14 - (Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong("arthur_globe_day_start")));
	}
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION = new DialogueNode("Комната Arthur", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Найти Lyssieth", "Если ты когда-нибудь захочешь узнать, что происходит, похоже, тебе придется согласиться помочь.", ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH = new DialogueNode("Комната Arthur", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Позволить Arthur продолжить свои эксперименты.", ROOM_ARTHUR);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR");
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
			if(index == 1) {
				return new Response("Lyssieth", "Спросить Arthur о матери Lilaya, Lyssieth.", ROOM_ARTHUR_LYSSIETH);
				
			} else if(index == 2) {
				return new Response("Lilaya", "Спросить Arthur о его прошлых отношениях с Lilaya.", ROOM_ARTHUR_LILAYA);
				
			}
			List<Response> additionalResponses = new ArrayList<>();
			
			// Hypno-watch quest:
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HYPNO_WATCH)) {
					additionalResponses.add(
							new Response("Эксперименты", "Спросить Arthur о том, какие именно эксперименты он проводит сейчас.", ROOM_ARTHUR_HYPNO_WATCH_START) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HYPNO_WATCH));
								}
								@Override
								public Colour getHighlightColour() {
									return PresetColour.QUEST_SIDE;
								}
							});
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_VICKY) {
					if(!Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE))) {
						additionalResponses.add(new Response("Доставить посылку", "Сначала тебе нужно забрать посылку в Arcane Arts!", null));
						
					} else {
						additionalResponses.add(
								new Response("Доставить посылку", "Отдать посылку, которую ты забрал в Arcane Arts.", ROOM_ARTHUR_HYPNO_WATCH_DELIVERY) {
									@Override
									public void effects() {
										Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT));
										Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									}
								});
					}
				}
			}
			
			// Arcane lightning quest:
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ARCANE_LIGHTNING)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ARCANE_LIGHTNING) && Main.game.getPlayer().hasWeaponType(WeaponType.getWeaponTypeFromId("innoxia_lightningGlobe_lightning_globe"), true)) {
					additionalResponses.add(
							new Response("Громовой шар",
									"Показать Arthur грозовой шар, найденный на складе Enforcer, и спросить, есть ли способ раскрыть тайны его силы.",
									ROOM_ARTHUR_ARCANE_LIGHTNING_START) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ARCANE_LIGHTNING));
								}
								@Override
								public Colour getHighlightColour() {
									return PresetColour.QUEST_SIDE;
								}
							});
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_ARCANE_LIGHTNING) == Quest.LIGHTNING_SPELL_1_PAYMENT) {
					if(Main.game.getPlayer().getEssenceCount()<500) {
						additionalResponses.add(new Response("Отдать шар", "Чтобы сделать это, в твоей ауре должно быть поглощено не менее 500 эссенций!", null));
						
					} else {
						additionalResponses.add(new Response("Отдать шар",
								"Отдать Arthur arcane грозовой шар и позволить ему извлечь эссенции из твоей ауры, чтобы раскрыть его тайны.<br/>[style.italicsMinorBad(Ты потеряешь 500 эссенций!)]",
								ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT));
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_ARCANE_LIGHTNING) == Quest.LIGHTNING_SPELL_2_WAITING) {
					if(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong("arthur_globe_day_start") < 14) {
						additionalResponses.add(new Response("Arcane молния",
								"У Arthur еще не было достаточно времени, чтобы раскрыть тайны arcane грозового шара. Вернуться"
									+ (getDaysRemainingUntilArcaneLightningUnlocked()==1
										?" завтра "
										:" через "+Util.intToString(getDaysRemainingUntilArcaneLightningUnlocked())+" дней")
									+" что ему удалось разузнать.",
								null));
						
					} else {
						additionalResponses.add(new Response("Arcane молния",
								"Прошло уже две недели с тех пор, как вы отдали Arthur свой аркана-шар с молниями; стоит спросить его, какие секреты ему удалось из него вытянуть.",
								ROOM_ARTHUR_ARCANE_LIGHTNING_END));
					}
				}
			}
			
			for(int i=0 ; i<additionalResponses.size(); i++) {
				if(index==i+3) {
					return additionalResponses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LYSSIETH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_LYSSIETH");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 1) {
					return new Response("Lyssieth", "Вы уже спрашиваете Arthur о Lyssieth.", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LILAYA = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_LILAYA");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 2) {
					return new Response("Lilaya", "Вы уже спрашиваете Arthur о Lilaya.", null);
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_START = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_START");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_DELIVERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_DELIVERY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Согласиться", "Вы доверяете Lilaya достаточно, чтобы согласиться на её просьбу.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF);
				
			} else if(index == 2) {
				return new Response("Отказаться", "Вы ни за что не согласитесь стать подопытной для Lilaya и Arthur! Возможно, вместо вас можно было бы выставить добровольцем Rose...", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Проснуться", "Вы внезапно выходите из транса.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*15;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Позволить Arthur продолжить свои другие эксперименты.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Разбудить Rose", "Помочь Lilaya разбудить Rose.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Позволить Arthur продолжить свои другие эксперименты.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	
	// Lightning globe:

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_START = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getEssenceCount()<500) {
					return new Response("Отдать шар", "Чтобы сделать это, в твоей ауре должно быть поглощено не менее 500 эссенций!", null);
					
				} else {
					return new Response("Отдать шар",
							"Отдать Arthur arcane грозовой шар и позволить ему извлечь эссенции из твоей ауры, чтобы раскрыть его тайны.<br/>[style.italicsMinorBad(Ты потеряешь 500 эссенций!)]",
							ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT);
				}
				
			} else if(index==2) {
				return new Response("Позже", "Сказать Arthur, что вы подумаете об этом и, возможно, вернетесь в другой раз, чтобы позволить ему поэкспериментировать с глобусом.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_START_LEAVE"));
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeWeaponTypeIntoVoid(WeaponType.getWeaponTypeFromId("innoxia_lightningGlobe_lightning_globe"), true, true, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(-500, false));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ARCANE_LIGHTNING, Quest.LIGHTNING_SPELL_2_WAITING));
			Main.game.getDialogueFlags().setSavedLong("arthur_globe_day_start", Main.game.getDayNumber());
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "[pc.Step] из комнаты Arthur и дать ему спокойно заниматься экспериментами.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_PAYMENT_LEAVE"));
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_END = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Изучить заклинания", "Прочитать бумаги и выучить два заклинания, которые удалось создать Arthur.", ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL);
			}
			return null;
		}
	};

	public static final DialogueNode ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_lightningGlobe_lightning_globe_ring"), 1, false, true));
			Main.game.getPlayer().addSpell(Spell.ARCANE_CHAIN_LIGHTNING);
			Main.game.getPlayer().addSpell(Spell.ARCANE_LIGHTNING_SUPERBOLT);
			Main.game.getTextEndStringBuilder().append(
					"<p style='text-align:center;'>"
						+ "You have learned the following spells:"
						+ "<br/><b style='color:"+PresetColour.SPELL_SCHOOL_ARCANE.toWebHexString()+";'>"+Spell.ARCANE_CHAIN_LIGHTNING.getName()+"</b>"
						+ "<br/><b style='color:"+PresetColour.SPELL_SCHOOL_ARCANE.toWebHexString()+";'>"+Spell.ARCANE_LIGHTNING_SUPERBOLT.getName()+"</b>"
					+ "</p>");
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_ARCANE_LIGHTNING, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Позволить Arthur продолжить свои другие эксперименты.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_ARCANE_LIGHTNING_END_FINAL_LEAVE"));
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
}
