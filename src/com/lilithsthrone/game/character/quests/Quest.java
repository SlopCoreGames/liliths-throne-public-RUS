package com.lilithsthrone.game.character.quests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.6.3
 * @author Innoxia
 */
public enum Quest {
	
	
	// Main quests:

	MAIN_PROLOGUE(QuestType.MAIN, 1, 5) {
		@Override
		public String getName() {
			return "Выжить до вечера";
		}
		@Override
		public String getDescription() {
			return "Вы обещали своей тете Лили, что посетите открытие новой экспозиции в ее музее. Вам нужно пережить предстоящий вечер.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ваш вечер в музее оказался гораздо более насыщенным, чем вы могли бы себе представить."
					+ "Таинственный демон по имени Лилит обманом затянул вас в магический портал и переместил в параллельную вселенную."
					+ "Проснувшись на незнакомой улице, вы были спасены от опасности полудемонкой 'Лилайей'."
					+ "Она, похоже, является версией вашей тети Лили в этой вселенной, и в обмен на согласие помочь ей с ее экспериментами, она разрешила вам остаться у нее дома.";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Испытания Lilaya";
		}
		@Override
		public String getDescription() {
			return "Вы можете найти Лилайю в ее лаборатории в любое время, где она будет готова продолжить свои тесты на вас. Может быть, она сможет найти способ отправить вас домой?";
		}
		@Override
		public String getCompletedDescription() {
			return "Лилая провела на вас еще несколько тестов, но она не может продвинуться в своих исследованиях без помощи своего старого коллеги, Артура.";
		}
		@Override
		public void applySkipQuestEffects() {
			((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
		}
	},

	MAIN_1_B_DEMON_HOME(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Поиск Артура; Дом демона";
		}
		@Override
		public String getDescription() {
			return "Лилая сообщила вам, что ее старый коллега, Артур, мог бы знать больше о типе магии, использованной в портале."
					+ " Однако, она, кажется, испытывает к нему сильную неприязнь, и вы оказались в ситуации, когда вам нужно заставить его извиниться перед Лилайей, прежде чем она позволит ему" + " прийти и работать с ней."
					+ " Артур живет в многоквартирном доме под названием 'Sawlty Towers', в районе города, известном как 'Дом демонов', поэтому вы можете найти его там.";
		}
		@Override
		public String getCompletedDescription() {
			return " Когда вы прибыли в дом Артура, вы обнаружили, что его арестовали Энфорсеры Доминиона по подозрению в заговоре против Лилит." + " После ареста его доставили в штаб-квартиру Энфорсеров.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},

	MAIN_1_C_WOLFS_DEN(QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return " Поиск Артура; Волчья логово";
		}
		@Override
		public String getDescription() {
			return " Артур был арестован Энфорсерами Доминиона и доставлен в штаб-квартиру Энфорсеров."
					+ " Похоже, вам придется расспросить там и найти способ спасти Артура.";
		}
		@Override
		public String getCompletedDescription() {
			return " Вы были вынуждены столкнуться с Инспектором Энфорсеров, Браксом."
					+ " К счастью, вам удалось справиться с ним, но затем вы узнали, что Артура продали в рабство!";
		}
		@Override
		public void applySkipQuestEffects() {
			BraxOffice.setBraxsPostQuestStatus(false);
			BraxOffice.givePlayerEnforcerUniform(null,-1);
		}
	},

	MAIN_1_D_SLAVERY(QuestType.MAIN, 3, 10) {
		@Override
		public String getName() {
			return "Поиск Артура; Продажа в рабство";
		}
		@Override
		public String getDescription() {
			return "После победы над Браксом, ты узнал, что Артур был продан в рабство торговцу по имени Скарлетт."
					+ "Тебе придется отправиться в Улицу работорговцев, найти Скарлетт и найти способ освободить Артура.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты нашел гарпию Скарлетт на Улице работорговцев, которая оказалась одной из самых грубых людей, которых ты когда-либо встречал.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},
	
	MAIN_1_E_REPORT_TO_HELENA(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "Поиск Артура; Найди Хелену";
		}
		@Override
		public String getDescription() {
			return "После того, как ты нашел Скарлетт на Улице работорговцев, ты обнаружил, что она больше не владеет Артуром."
					+ "Прежде чем она расскажет тебе что-либо, она хочет, чтобы ты отправился в Гнезда гарпий и сообщил ее матриарху, Хелене, что ее бизнес - полный провал";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты сообщил о проблемах Скарлетт ее матриарху, Хелене."
					+ "Она не показала много сочувствия к Скарлетт и быстро улетела, чтобы поговорить с ней лично.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
		}
	},
	
	MAIN_1_F_SCARLETTS_FATE(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "Поиск Артура; Судьба Скарлетт";
		}
		@Override
		public String getDescription() {
			return "Вам нужно вернуться в лавку Скарлетт, чтобы узнать, что случилось с ней. Надеюсь, Хелена не слишком строго с ней обошлась, и она будет готова рассказать вам, что случилось с Артуром...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись в лавку Скарлетт, только чтобы обнаружить, что Хелена поработила её!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
			Main.game.getNpc(Scarlett.class).resetInventory(true);
			AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
			collar.setSealed(true);
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
		}
	},
	
	MAIN_1_G_SLAVERY(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "Поиск Артура; Рабство";
		}
		@Override
		public String getDescription() {
			return "Хелена готова продать вам Скарлетт, что, похоже, единственный способ получить информацию, которую вы ищете."
					+ "Вам нужно иметь лицензию на рабство, чтобы купить Скарлетт.";
		}
		@Override
		public String getCompletedDescription() {
			return "Хелена продала вам Скарлетт, что позволило вам приказать Скарлетт рассказать вам, что случилось с Артуром.";
		}
		@Override
		public void applySkipQuestEffects() {
			AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
			if (ballgag != null) {
				ballgag.setSealed(false);
				Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
			}
			
			// Complete slavery side quest:
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
				Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY);
			}
			List<Quest> slaverSkipQuests = Util.newArrayListOfValues(
					Quest.SIDE_SLAVER_NEED_RECOMMENDATION,
					Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED,
					Quest.SIDE_UTIL_COMPLETE);
			for(int i=0; i<slaverSkipQuests.size()-1; i++) {
				Quest q = slaverSkipQuests.get(i);
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY)==q) {
					q.applySkipQuestEffects();
					Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, slaverSkipQuests.get(i+1));
				}
			}
			
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
			
			((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
		}
	},
	
	MAIN_1_H_THE_GREAT_ESCAPE(QuestType.MAIN, 10, 200) {
		@Override
		public String getName() {
			return "Поиск Артура; Великое бегство";
		}
		@Override
		public String getDescription() {
			return "Оказывается, Артур был продан крайне опасному демону по имени Зараник, который живёт в Доме Демонов."
					+ "Вам нужно отправиться в Дом Демонов и спасти Артура!";
		}
		@Override
		public String getCompletedDescription() {
			return "После победы над Зараником вы спасли Артура и привели его обратно в дом Лилайи.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
			Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
		}
	},
	
	MAIN_1_I_ARTHURS_TALE(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "Поиск Артура; Заключение";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы освободили Артура из рук Зараникса, вам следует вернуться домой к Лилайе и узнать от него всю историю о том, что произошло.";
		}

		@Override
		public String getCompletedDescription() {
			return "Артур рассказал, как он увлекался запретным искусством заклинаний телепортации."
					+ "Через одного из своих агентов Зараникс узнал об этом и не без труда заставил Артура поработать на себя за измену."
					+ "Теперь, когда вы его освободили, он хочет отплатить за это, узнав, как отправить вас домой.";
		}
		@Override
		public void applySkipQuestEffects() {
			Cell arthurRoomCell = Lab.addArthurRoom();
			Main.game.getNpc(Arthur.class).setLocation(arthurRoomCell, true);
		}
	},
	
	// This quest is no longer used, but is left here for old version support
	MAIN_1_J_ARTHURS_ROOM(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "Поиск Артура; Его собственная комната";
		}
		@Override
		public String getDescription() {
			return "Лилая действительно не хочет, чтобы Артур был в ее лаборатории, и поручила вам помочь Роуз найти подходящую комнату для него.<br/>"
					+ "<i>Зайдите в одну из пустых комнат в доме Лилайи, и через окно управления комнатами обновите ее до 'Комната Артура'.</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли подходящую комнату для Артура, и с помощью Роуз переместили значительное количество арканических приборов в его новую лабораторию-спальню.";
		}
	},
	
	
	MAIN_2_A_INTO_THE_DEPTHS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "На пути к Submission";
		}
		@Override
		public String getDescription() {
			return "Артур смог объяснить механизм, с помощью которого вы были переброшены в этот новый мир, но он, похоже, утаил некоторые детали."
					+ " Он сказал, что расскажет все, когда узнает наверняка, что происходит, но для этого ему нужно поговорить с одним из семи старших лилин."
					+ " После долгих споров Лилая согласилась убедить свою мать помочь, но вам придется быть тем, кто доставит сообщение.<br/>"
					+ "<i>Спуститесь в подземный город Покорности и попросите аудиенции у матери Лилайи, Лиссиет.</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "Следуя совету Артура, вы спустились в Покорность и обнаружили местонахождение дворца Лиссиет.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false);
		}
	},
	
	MAIN_2_B_SIRENS_CALL(QuestType.MAIN, 25, 300) {
		@Override
		public String getName() {
			return "Призыв Сирены";
		}
		@Override
		public String getDescription() {
			return "Охранники у ворот дворца Лиссиет сказали вам, что она в данный момент не принимает посетителей."
					+ "Единственный способ получить аудиенцию с ней - это позаботиться о ее проблемной дочери; 'Темной Сирене'."
					+ "Она в настоящее время живет в каменной крепости в одном из центральных туннелей здесь, в Покорности, откуда она посылает банды бесов терроризировать невинных граждан.</br>"
					+ "Если вам удастся поработить ее, будь то в бою или обманом, вы заслужите аудиенцию с Лиссиет.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось поработить проблемную дочь Лиссиет, и так вы заслужили аудиенцию с ней!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			ImpCitadelDialogue.clearFortress(false);
			// Set tunnels to be cleared manually, as they haven't been cleared when skipping quests:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
					character.returnToHome();
				}
			}
		}
	},
	
	MAIN_2_C_SIRENS_FALL(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Падение Сирены";
		}

		@Override
		public String getDescription() {
			return "Вернись в дворец Лиссиет и сообщи стражам, что ты поработила 'Темную Сирену'."
					+ "Это должно быть достаточно, чтобы предоставить тебе аудиенцию с Лиссиет.";
		}

		@Override
		public String getCompletedDescription() {
			return "За порабощение 'Темной Сирены' стражники у ворот дворца Лиссиет дали тебе разрешение войти и искать аудиенцию с ней.";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			}
			if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
				Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING, false), false);
			}
		}
	},
	
	MAIN_2_D_MEETING_A_LILIN(QuestType.MAIN, 1, 100) {
		@Override
		public String getName() {
			return "Встреча с лилин";
		}

		@Override
		public String getDescription() {
			return "Отправляйся в тронный зал Лиссиет и постарайся наконец получить ответы о том, почему ты здесь и как ты можешь вернуться в свой старый мир.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лиссиет раскрыла, что этот мир на самом деле твой собственный, и что Лилит превратила его в другую реальность, когда она была освобождена из зеркала.";
		}
		@Override
		public void applySkipQuestEffects() {
			((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
			AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
			Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25);
			Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
			if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
				Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0);
			}
		}
	},
	
	MAIN_3_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Путь в Элис";
		}

		@Override
		public String getDescription() {
			return "Лиссиет сказала тебе, что для того, чтобы победить старшую лилин пегатур, Лунетт, тебе нужно заручиться помощью Миноталлис; лилин, которая правит городом Элис."
					+ "Мераксис также упомянул о привлечении помощи юко, но это можно отложить до тех пор, пока ты не приедешь в сам Элис.";
		}
		@Override
		public String getCompletedDescription() {
			return "Лиссиет сказала тебе, что для того, чтобы победить старшую лилин пегатур, Лунетт, тебе нужно заручиться помощью Миноталлис; лилин, которая правит городом Элис."
					+ "С этой целью ты впервые покинула Доминион...";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_B_MEETING_MERAXIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "К Красной Драконье";
		}

		@Override
		public String getDescription() {
			return "Когда ты покидала Dominion, к тебе подошла Мераксис, которая сказала, что ты должна встретиться с ней в таверне 'Красная Драконья' в Элисе, которая, по-видимому, находится недалеко от восточных ворот города."
					+ "Мераксис также сказала, что она организует встречу с Миноталлис, когда ты приедешь, и что она уже нашла для тебя жилье.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты встретилась с Мераксис в таверне 'Красная Драконья', где она смогла найти для тебя жилье в виде арендованной комнаты на первом этаже таверны.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_C_MEETING_MINOTALLYS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Встреча с Миноталисом";
		}

		@Override
		public String getDescription() {
			return "Мераксис организовала для тебя встречу с Миноталлис по поводу угрозы Лунетт для города Элис."
					+ "Скажи Мераксис, что ты готова к встрече в любое время между [units.time(9)]-[units.time(18)].";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты и Мераксис отправились в ратушу Элис, чтобы встретиться с Миноталлис, где ты также встретилась с ее личным помощником, Арион."
					+ "Миноталлис находится в состоянии отрицания текущего положения дел на Полях Фолои и заявила, что она будет рассматривать возможность принятия мер только в том случае, если город Темискюра каким-то образом будет под угрозой.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_D_TO_THEMISCYRA(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "В Фемискиру";
		}

		@Override
		public String getDescription() {
			return "Ты согласилась отправиться в Темискюру с Мераксис и выяснить, угрожает ли городу армия Лунетт."
					+ "Когда ты будешь готова, тебе нужно встретиться с Мераксис и позволить ей провести тебя туда.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты отправилась в Темискиру с Мераксисом, но, прибыв в город, ты обнаружила, что его разрушает армия Лунетт!";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"))==null) {
				Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(11, 32).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"));
			}
		}
	},
	
	MAIN_3_E_THEMISCYRA_ATTACK(QuestType.MAIN, 1, 250) {
		@Override
		public String getName() {
			return "Сохранить Королеву";
		}
		@Override
		public String getDescription() {
			return "Отделившись от Мераксиса, ты объединилась с амазонкой-коу-гёрл по имени Аурокарис."
					+ "Тебе нужно путешествовать по Темискире и найти Мераксиса и Урсу, королеву амазонок, которые должны быть в дворце.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вместе с Аурокарис ты путешествовала по Темискире и нашла Мераксиса и Урсу на площади перед дворцом."
					+ "После встречи с Лунексис, лидером армии Лунетт, Мераксис телепортировал вас пятерых обратно в ратушу Элис, где Миноталлис наконец убедилась, что Лунетт является угрозой для Элис.";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"))==null) {
				Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(10, 20).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"));
			}
			Main.game.getNpc(Ursa.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Aurokaris.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Lunexis.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
		}
	},
	
	MAIN_3_F_PREPARING_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Некоторая помощь от SWORD";
		}
		@Override
		public String getDescription() {
			return "Поскольку Лунетт планирует атаковать Элис в ближайшее время, ты сказала Миноталлис, что поможешь привести в порядок оборону города."
					+ "Тебе нужно отправиться на станцию SWORD в Элис и попросить группу SWORD-энфорсеров о помощи.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты отправилась на станцию SWORD в Элис и попросила группу SWORD-энфорсеров помочь привести в порядок оборону города.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_G_SWORD_SCAPEGOAT(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Козел отпущения SWORD";
		}
		@Override
		public String getDescription() {
			return "SWORD-принудители сказали вам, что для того, чтобы они помогли вам, вам сначала нужно помочь им."
					+ "Вы должны присоединиться к операции по остановке дочери старшего лилина, чтобы вы могли вытащить их из беды, если их узнают."
					+ "Вы должны встретиться с ними на станции принудителей в вторник вечером, после [units.time(17)], чтобы начать операцию.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встретились с SWORD-принудителями в вторник днем, чтобы вы могли присоединиться к операции по остановке дочери старшего лилина.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_H_SWORD_MISSION(QuestType.MAIN, 25, 250) {
		@Override
		public String getName() {
			return "Остановите Суккубу";
		}
		@Override
		public String getDescription() {
			return "Вы должны остаться с SWORD-принудителями, пока они очищают штаб-квартиру суккубы."
					+ "Вам не нужно сражаться, но принудители могли бы оценить, если бы вы оказали им некоторую помощь.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы работали с SWORD-принудителями, чтобы очистить штаб-квартиру суккубы."
					+ "Хотя ей удалось сбежать, вы положили решительный конец ее незаконной деятельности по порабощению.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_I_ELIS_DEFENCE_PLAN(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Отчет о Minotallys";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда SWORD-прислужники работают над подготовкой к защите города, вам нужно вернуться в мэрию, чтобы сообщить об этом Миноталлису.";
		}
		@Override
		public String getCompletedDescription() {
			return "После встречи с Миноталлисом, Урсой, Хаммером и Призраком, а также с Мераксисом в мэрии, был разработан план защиты Элиса от Лунетт.";
		}
	},
	
	MAIN_3_J_FINDING_TAKAHASHI(QuestType.MAIN, 30, 25) {
		@Override
		public String getName() {
			return "Поиски Такахаши";
		}
		@Override
		public String getDescription() {
			return "Мераксис убеждена, что она может заручиться поддержкой юко с помощью своей подруги, Такахаси."
					+ "Наиболее вероятное место, где можно найти Такахаси, - это старая укрывка Мераксиса в Шинринских Высотах."
					+ "Когда вы будете готовы, вам нужно поговорить с Мераксисом и отправиться в Шинринские Высоты, чтобы найти её подругу.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили, что тайное убежище Мераксиса было занято семихвостой юко, которая захватила Такахаси и использовала её в качестве слуги."
					+ "После того как её обнаружили, семихвостая юко сбежала, позволив Мераксису и Такахаси вернуть свою старую укрывку.";
		}
	},
	
	MAIN_3_K_WEAPONS_CACHE(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "Получение пушек";
		}
		@Override
		public String getDescription() {
			return "[style.italicsBad(Это всё, что касается основного квеста на данный момент! Он будет обновлен в ближайшее время!)]"
					+"<br/>"
					+"Пушки, которые Мераксис украла у Лиссита, находятся в руках девятихвостой юко."
					+ " Такахаси знает, где живет этот лилин, и поэтому, когда вы будете готовы, вы трое можете отправиться, чтобы вернуть эти оружие.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_L_YOUKO_LILIN(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "Лилин";
		}
		@Override
		public String getDescription() {
			return "Теперь оружие в безопасности, защита Элиса нуждается только в арканах, которые могут отразить заклинания, которые будут использовать дочери Лунетты."
					+ "Чтобы заставить лилин помочь с этим, вам нужно найти двух лилин и убедить их помочь вам.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_M_DEFENCE_OF_ELIS(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "С помощью лилин и запасом аркановых мускетов в ваших руках, все, что осталось сделать, это начать наступление Лунетты на город.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_N_DEFEAT_LUNETTE(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "Наступление на Элис началось! Благодаря вашим действиям и действиям лилин и мушкетеров, дочери Лунетты были отбиты, но с самим лилин еще нужно разобраться."
					+ "Вам, Мераксису и Миноталысу, предстоит победить ее кентавровую форму.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_O_DEFEAT_LUNETTE2(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "Вы сумели победить Лунетту, но когда ее кентавровское тело упало на землю, она превратилась в свою истинную, демоническую форму."
					+ "Вам нужно задержать ее, пока не приедет Лиссит и не подчинит ее сестру.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	//"[style.italicsBad(This is as far as the main quest goes for now! It will be updated soon!)]"
	//+"<br/>"

	// Side Quests:

	SIDE_UTIL_COMPLETE(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Квест выполнен!";
		}

		@Override
		public String getDescription() {
			return "Квест выполнен!";
		}

		@Override
		public String getCompletedDescription() {
			return "Квест выполнен!";
		}
	},
	
	SIDE_DISCOVER_ALL_ITEMS(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Перфекционист";
		}

		@Override
		public String getDescription() {
			return "В этом новом мире есть много любопытных предметов. Вы задаетесь вопросом, сможете ли вы найти их все...";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты обнаружил все предметы, которые можно найти!";
		}
	},

	SIDE_DISCOVER_ALL_RACES(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Перфекционист";
		}

		@Override
		public String getDescription() {
			return "В этом мире, кажется, существует множество новых странных рас. Ты задаешься вопросом, сможешь ли ты обнаружить их всех...";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты обнаружил все расы, которые можно найти!";
		}
	},
	
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Спроси Лилайу о помощи";
		}

		@Override
		public String getDescription() {
			return "Недавно ты почувствовал странную силу, входящую в твое тело, и хотя она, казалось, не имела очевидного эффекта, тебе, вероятно, стоит проверить это."
					+ "Лилая наверняка знает больше, так что, возможно, тебе стоит пойти и поговорить с ней об этом.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилая сообщила тебе, что ты можешь собирать 'эссенции' из арканической ауры других людей."
					+ "Она показалась немного обеспокоенной тем, что ты можешь делать это, так как, по-видимому, обычно только лилин могут собирать эссенции таким образом...";
		}
	},

	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Лилая знает лучше";
		}

		@Override
		public String getDescription() {
			return "Не может быть... Ты беременна? Ты <b>беременна</b>! Конечно, Лилая знает, что делать?!";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилая смогла успокоить вас и заверить, что беременность в этом мире не такая уж и большая проблема, как дома.";
		}
	},
	
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(QuestType.SIDE, 1, 20) {
		@Override
		public String getName() {
			return "Лилая акушерка";
		}

		@Override
		public String getDescription() {
			return "Лилая сказала, что она сможет помочь вам родить, когда вы будете готовы. Вам нужно подождать, пока ваш живот не перестанет расти, а затем вы можете пойти к Лилайе, чтобы родить.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилая помогла вам родить. Она сказала, что если вы снова забеременеете, она всегда сможет помочь.";
		}
	},
	
	// When getting eggs implanted in you for the first time:
	
	SIDE_INCUBATION_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Живой инкубатор";
		}
		@Override
		public String getDescription() {
			return "Нет никаких сомнений; у вас в теле имплантировано несколько яиц! Вам нечего делать, кроме как ждать, пока они созреют, а затем отложить их...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы ждали, пока яйца, имплантированные в ваше тело, созреют, а затем успешно отложили и высидели их!";
		}
	},
	
//	SIDE_INCUBATION_LILAYA_HELP(QuestType.SIDE, 1, 20) {
//		@Override
//		public String getName() {
//			return "Egg-laying assistance";
//		}
//		@Override
//		public String getDescription() {
//			return "Lilaya said that she'd be able to help you lay your eggs whenever you're ready. You're going to need to wait until they're ready to be hatched, then you can go and see Lilaya to lay them.";
//		}
//		@Override
//		public String getCompletedDescription() {
//			return "Lilaya helped you to lay your eggs. She said that if ever you get implanted with eggs again, she can always help out.";
//		}
//	},
	
	// Getting a slaver license:
	
	SIDE_SLAVER_NEED_RECOMMENDATION(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Рекомендательное письмо";
		}

		@Override
		public String getDescription() {
			return "После того, как вы спросили, как получить лицензию на рабство в здании Администрации рабства, вы узнали, что вам сначала нужно получить рекомендательное письмо. Лилая должна быть в состоянии помочь с этим.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилая дала вам рекомендательное письмо, и что еще более важно, она также предложила вам разместить ваших рабов в своем особняке.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
		}
	},
	
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Преподнести письмо";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы получили рекомендательное письмо от Лилайи, вам следует вернуться в здание Администрации рабства в переулке Рабства и представить его [finch.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы представили рекомендательное письмо [finch.name], и после оплаты пошлины вы получили лицензию на работорговлю!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false));
		}
	},
	
	// Accommodation:
	
	SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Лилая, хозяйка";
		}

		@Override
		public String getDescription() {
			return "Особняк Лилайи полон пустых комнат, которые можно использовать в качестве гостевого жилья. Вы должны спросить ее, можно ли использовать их для размещения ваших друзей и семьи.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилая разрешила вам использовать пустые комнаты для размещения ваших друзей и семьи, при условии, что вы оплатите все расходы.";
		}
	},

	// Doll storage:
	
	SIDE_DOLL_STORAGE_ASK_FOR_SPACE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Куда деваются куклы?";
		}
		@Override
		public String getDescription() {
			return "Хотя особняк Лилайи полон пустых комнат, в которых можно хранить куклы, вам лучше спросить у нее разрешения, прежде чем приносить их домой...";
		}
		@Override
		public String getCompletedDescription() {
			return "Лилая разрешила вам использовать пустые комнаты для хранения любых кукол, которые вы покупаете.";
		}
	},
	
	// Other:
	
	SIDE_HYPNO_WATCH_VICKY(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Закажите в Аркана";
		}

		@Override
		public String getDescription() {
			return "Артур сообщил вам, что он получил от Зараникса указание найти арканический метод изменения сексуальной ориентации человека."
					+ "Хотя он заверил вас, что у него нет намерения когда-либо использовать такой предмет сам, Артур выразил заинтересованность в завершении своих исследований,"
						+ " и попросил тебя принести специальный заказ из магазина 'Аркана' в Торговом центре.";
		}

		@Override
		public String getCompletedDescription() {
			return " Ты забрал посылку из Аркана и принес её обратно Артуру.";
		}
	},
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Подопытный";
		}

		@Override
		public String getDescription() {
			return " После того, как Лилая выполнила инструкции Артура по зачарованию часов, она спросила, можно ли испытать их на тебе...";
//					+ " You could either offer yourself, or, if you own any slaves, offer one of those to Arthur instead.";
		}

		@Override
		public String getCompletedDescription() {
			return " Гипно-часы, похоже, сработали, хотя Лилая остановила испытание, прежде чем оно произвело постоянный эффект."
					+ " Она предупредила, что это будет иметь сильное коррупционное воздействие на разум того, на кого оно направлено, и разчаровала его на всякий случай, прежде чем передать тебе.";
		}
	},
	
	
	LIGHTNING_SPELL_1_PAYMENT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return " Цена Силы";
		}
		@Override
		public String getDescription() {
			return " После того, как ты показал Артуру шар с молнией, который ты нашёл на складе Энуфа, тебе сказали, что можно будет узнать секреты заклинаний молнии внутри него."
					+ " Хотя полученные заклинания будут более мощными, чем те, на которые способен шар,"
						+ " Артур объяснил тебе, что такая экстракция потребует не только огромного количества арканой эссенции, но и навсегда удалит врождённые заклинания шара."
					+ "<br/>Когда ты будешь готов и способен, дай Артуру шар с молнией и позволь ему извлечь 500 арканых эссенций из твоей ауры.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты передала Артуру арканный шар молнии, который нашла в складе Энуфорсера, и также позволила ему извлечь 500 арканных эссенций из твоей Ауры."
					+ "Взамен тебе обещали, что скоро ты получишь мощное арканное заклинание молнии.";
		}
	},
	
	LIGHTNING_SPELL_2_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Извлечение молнии";
		}
		@Override
		public String getDescription() {
			return "Артур сообщил тебе, что извлечение секретов арканной молнии из шара займет некоторое время."
					+ "Тебе придется вернуться к нему через две недели, чтобы узнать, каковы результаты его исследований...";
		}
		@Override
		public String getCompletedDescription() {
			return "Артур с восторгом сообщил тебе, что он не только смог извлечь секреты двух арканных заклинаний молнии из шара,"
					+ "но и смог перенести его оставшуюся силу в меньший кристалл, который он вставил в кольцо для тебя.";
		}
	},
	
	
	
	// Angry Harpies:
	
	HARPY_PACIFICATION_ONE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Гнезда в хаосе";
		}

		@Override
		public String getDescription() {
			return "Энуфорсер сообщил тебе, что гнезда гарпий в настоящее время крайне опасны."
					+ "После дальнейшего расспроса ты узнал, что за человека, который сможет успокоить трех главных матерей, полагается значительная награда.";
		}

		@Override
		public String getCompletedDescription() {
			return "Тебе удалось взять под контроль одно из гнезд гарпий!";
		}
	},
	HARPY_PACIFICATION_TWO(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Один меньше, двое осталось";
		}

		@Override
		public String getDescription() {
			return "У тебя получилось подчинить одну из матерей, но еще двое остались!";
		}

		@Override
		public String getCompletedDescription() {
			return "У тебя получилось подчинить две из гнезд гарпий!";
		}
	},
	HARPY_PACIFICATION_THREE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Одна мать осталась";
		}

		@Override
		public String getDescription() {
			return "У тебя получилось подчинить двух матерей, но еще одна остается!";
		}

		@Override
		public String getCompletedDescription() {
			return "У тебя получилось подчинить все три крупных гнезда гарпий!";
		}
	},
	HARPY_PACIFICATION_REWARD(QuestType.SIDE, 6, 50) {
		@Override
		public String getName() {
			return "Гарпия "+(Main.game.getPlayer().isFeminine()?"queen":"king");
		}

		@Override
		public String getDescription() {
			return "Вернись к посту Наблюдателя, чтобы сообщить о своем успехе.";
		}

		@Override
		public String getCompletedDescription() {
			return "После того, как ты проинформировала Наблюдателей о том, что ты успокоила все три крупных гнезда гарпий, они возобновили свои регулярные патрули, в результате чего гнезда гарпий стали безопасными для путешествий!";
		}
	},
	
	
	
	// Slime Queen:
	
	SLIME_QUEEN_ONE(QuestType.SIDE, 10, 25) {
		@Override
		public String getName() {
			return "Проблемные слаймы";
		}

		@Override
		public String getDescription() {
			return "Когда ты впервые прибыла в Покорность, Наблюдатель по имени Клэр сообщила тебе о текущей ситуации в туннелях."
					+ "По-видимому, все больше и больше слаймов атакуют невинных путешественников и превращают их в больше слаймов."
					+ "Если вы сможете предоставить какую-либо информацию о том, откуда берутся эти агрессивные слаймы, вы сможете заработать вознаграждение в размере пяти тысяч кредитов / огней (Flames)."
					+ "<br/>"
					+ "<p style='text-align:center;'><i>Вам нужно победить слайма в туннелях <b>Submission</b>, чтобы узнать больше.</i></p>";
		}

		@Override
		public String getCompletedDescription() {
			return "Один из слаймов, с которыми вы столкнулись в туннелях, сказал вам, что они получили приказ превратить людей от определенной 'Королевы Слаймов'.";
		}
	},
	
	SLIME_QUEEN_TWO(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Доложить назад";
		}

		@Override
		public String getDescription() {
			return "Вы должны сообщить об этом в один из постов @@H0@@Submission@@H1@@ с этой информацией о 'Королеве Слаймов'.";
		}

		@Override
		public String getCompletedDescription() {
			return "Энфорсер, которому вы сообщили, сказал вам, что они слышали слухи о Королеве Слаймов раньше, но никаких следов такой вещи никогда не было найдено в Submission."
					+ "Они предложили вам заглянуть вниз в Бат-Пещеры и предложили вам еще двадцать тысяч кредитов / огней (Flames), если вы сможете найти эту Королеву и положить конец ее заговорам.";
		}
	},
	
	SLIME_QUEEN_THREE(QuestType.SIDE, 15, 25) {
		@Override
		public String getName() {
			return "Найти Королеву Слаймов";
		}

		@Override
		public String getDescription() {
			return "Спуститесь вниз в Бат-Пещеры и найдите эту Королеву Слаймов.";
		}

		@Override
		public String getCompletedDescription() {
			return "В середине озера Слаймов вы обнаружили логово Королевы Слаймов!";
		}
	},
	
	SLIME_QUEEN_FOUR(QuestType.SIDE, 20, 50) {
		@Override
		public String getName() {
			return "Спросите Королеву";
		}

		@Override
		public String getDescription() {
			return "Поднимись по башне и найди Королеву Слайма.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты нашла Королеву Слайма на вершине башни.";
		}
	},
	
	SLIME_QUEEN_FIVE_SUBMIT(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Помоги Королеве";
		}

		@Override
		public String getDescription() {
			return "Ты решила помочь Королеве Слайма с ее планами по превращению населения Субмишн в слаймов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты решила помочь Королеве Слайма с ее планами и согласилась обмануть Инструкторов, чтобы они поверили, что она больше не представляет угрозы!";
		}
	},
	
	SLIME_QUEEN_SIX_SUBMIT(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Финальный отчет";
		}

		@Override
		public String getDescription() {
			return "Отчитайся перед Клэр и скажи ей, что Королева Слайма больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты сказала Клэр, что Королева Слайма больше не представляет угрозы, и получила вознаграждение в двадцать тысяч кредитов."
				+ "С твоей Королевой теперь в безопасности от расследования Инструкторов, это только вопрос времени, когда все Субмишн станет раем для Слаймов!";
		}
	},
	
	SLIME_QUEEN_FIVE_CONVINCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Убеди Королеву";
		}

		@Override
		public String getDescription() {
			return "Ты решила убедить Королеву Слайма отказаться от своих планов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты убедил Кэтрин отказаться от своего плана превратить всех в Субмиссии в слайм.";
		}
	},
	
	SLIME_QUEEN_SIX_CONVINCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Финальный отчет";
		}

		@Override
		public String getDescription() {
			return "Отчитайся перед Клэр и скажи ей, что Королева Слайм больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты сказал Клэр, что Королева Слайм больше не представляет угрозы, и получил двадцать тысяч кредитов.";
		}
	},
	
	SLIME_QUEEN_FIVE_FORCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Принуди Королеву";
		}

		@Override
		public String getDescription() {
			return "Принуди Королеву Слайм отказаться от своих планов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты принудил Кэтрин отказаться от своего плана превратить всех в Субмиссии в слайм.";
		}
	},
	
	SLIME_QUEEN_SIX_FORCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Финальный отчет";
		}

		@Override
		public String getDescription() {
			return "Отчитайся перед Клэр и скажи ей, что Королева Слайм больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты сказал Клэр, что Королева Слайм больше не представляет угрозы, и получил двадцать тысяч кредитов.";
		}
	},
	
	
	// Teleporting:
	
	TELEPORTING_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Сбегай из склада";
		}

		@Override
		public String getDescription() {
			return "Попав случайно в складское помещение подразделения 'SWORD' Enforcer, тебе и Клэр теперь нужно избежать обнаружения и благополучно сбежать.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты и Клэр сумели сбежать из склада SWORD.";
		}
	},

	TELEPORTING_CAUGHT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Выдерживай кандалы";
		}

		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Пораженная Энфорсерами внутри склада SWORD, ты была приговорена к тому, чтобы быть запертой в публичных кандалах на аллее работорговцев. Выдержи эту пытку, пока Клэр не придет тебя спасти...";
			} else {
				return "Пораженная Энфорсерами внутри склада SWORD, ты была заперта в камерах в штаб-квартире Энфорсеров. Тебе придется ждать, пока Клэр не придет тебя спасти...";
			}
		}

		@Override
		public String getCompletedDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Пораженная Энфорсерами внутри склада SWORD, ты была вынуждена выдержать, пока тебя заперли в публичных кандалах на аллее работорговцев на несколько часов, прежде чем Клэр пришла тебя освободить.";
			} else {
				return "Пораженная Энфорсерами внутри склада SWORD, ты была вынуждена выдержать, пока тебя заперли в камерах в штаб-квартире Энфорсеров на несколько часов, прежде чем Клэр пришла тебя освободить.";
			}
		}
	},
	
	
	// Daddy:
	
	DADDY_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встреча с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Роуз попросила тебя навестить [daddy.name], чтобы убедить [daddy.herHim] оставить Лилайу в покое."
					+ " ([daddy.He] доступен только в квартире [daddy.his] в Доме Демона между "+Units.time(LocalTime.of(18, 00))+" and "+Units.time(LocalTime.of(21, 00))+".)";
		}

		@Override
		public String getCompletedDescription() {
			return "По просьбе Роуз ты встретилась с [daddy.name] в квартире [daddy.her] в Доме Демона.";
		}
	},
	
	DADDY_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Ужин с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "[daddy.Name] настаивает на том, чтобы объяснить мотивы [daddy.her] за ужином. Вам придется либо принять предложение [daddy.her], либо грубо отказать [daddy.herHim] и настаивать, чтобы [daddy.she] оставил Лилайю в покое.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы дали [daddy.name] свой ответ в ответ на просьбу [daddy.her] пригласить вас на ужин.";
		}
	},
	
	DADDY_REFUSED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] отказал";
		}

		@Override
		public String getDescription() {
			return "Вы сказали [daddy.name], что совсем не заинтересованы в том, чтобы выйти на ужин с [daddy.herHim], и что [daddy.sheIs] никогда не должен беспокоить Лилайю.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали [daddy.name], что совсем не заинтересованы в том, чтобы выйти на ужин с [daddy.herHim], и что [daddy.sheIs] никогда не должен беспокоить Лилайю.";
		}
	},
	
	DADDY_REFUSED_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] отказал";
		}

		@Override
		public String getDescription() {
			return "Вы сказали [daddy.name], что не заинтересованы в том, чтобы убедить Лилайю встретиться с [daddy.herHim], и что [daddy.she] никогда не должен беспокоить вашего [lilaya.relation(pc)].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали [daddy.name], что не заинтересованы в том, чтобы убедить Лилайю встретиться с [daddy.herHim], и что [daddy.she] никогда не должен беспокоить вашего [lilaya.relation(pc)].";
		}
	},
	
	DADDY_ACCEPTED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Убеждение Lilaya";
		}

		@Override
		public String getDescription() {
			return "Вы согласились убедить Лилайю встретиться с [daddy.name] на ужин, а затем помочь убедить ее попросить Лиссит встретиться с [daddy.herHim].";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты убедила Лилайу встретиться с [daddy.name] на ужин, при условии, что ты пойдешь с ней.";
		}
	},
	
	DADDY_LILAYA_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Свадьба Лилайу с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Лилая согласилась пойти на ужин с [daddy.name], так что теперь тебе нужно просто сопровождать ее и следить за тем, чтобы вечер прошел гладко.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты пошел с Лилайу на встречу с [daddy.name] на ужин, и хотя у нее были плохие новости для [daddy.herHim] относительно романтических предпочтений Лилайу, она, похоже, действительно понравилась [daddy.herHim]...";
		}
	},
	
	
	// Buying Brax:
	
	BUYING_BRAX_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Коллекция духов";
		}

		@Override
		public String getDescription() {
			return "Канди сказала, что она рассмотрит возможность продажи [brax.name] тебе, но прежде чем она даст тебе окончательный ответ, она хочет, чтобы ты пошел и принес ей заказ духов из магазина 'Секреты Суккуби' в торговом центре.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы оба заплатили за и забрали заказ духов Канди из 'Секреты Суккуби'.";
		}
	},
	
	BUYING_BRAX_DELIVER_PERFUME(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Доставка духов";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда ты забрал бутылки духов, тебе нужно доставить их Канди обратно в штаб-квартиру Энуфорсера.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты достал бутылки духов Канди и передал их ей.";
		}
	},
	
	BUYING_BRAX_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Контрабандные леденцы";
		}

		@Override
		public String getDescription() {
			return "Канди сказала, что она готова продать тебе Бракса, но ей нужно подумать, сколько стоит [brax.sheIs]."
					+ "Она сказала, что у нее будет цена для тебя после того, как ты принесешь коробку контрабандных леденцов из контрольно-пропускного пункта Энуфорсера Гарпийских Гнезд.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты забрал конфеты из контрольно-пропускного пункта Гарпии.";
		}
	},
	
	BUYING_BRAX_DELIVER_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конфеты для Канди";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда у тебя есть коробка с контрабандными конфетами, тебе нужно вернуть их Канди обратно в штаб-квартиру Энфорсера.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты передал коробку с контрабандными конфетами Канди, которая, казалось, не обратила внимания на предупреждения, напечатанные на ней.";
		}
	},
	
	BUYING_BRAX_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Вес волка в помаде";
		}

		@Override
		public String getDescription() {
			return "Канди сказала, что Бракс слишком ценный, чтобы его продавать за mere flames, но она была бы рада дать тебе [brax.herHim] в обмен на что-то столь же ценное; коробку ограниченной серии помад, маркированных как 'Сто поцелуев'."
					+ "Похоже, Канди обнаружила местонахождение одной из немногих оставшихся для продажи коробок, которая находится в магазине в торговом центре под названием 'Перекусы Ральфа'.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты забрал коробку 'Сто поцелуев' у Ральфа.";
		}
	},
	
	BUYING_BRAX_DELIVER_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "У двери волка";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда ты получил коробку 'Сто поцелуев', все, что осталось сделать, это доставить ее Канди в обмен на право владения [brax.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты передал коробку с 'Сто Кис' Кэнди и, наконец, получил свою награду; право владения [brax.name].";
		}
	},

	
	// Vengar:
	
	VENGAR_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Крысиные Норы";
		}
		@Override
		public String getDescription() {
			return "Ты согласился помочь Акселю справиться с Венгаром, который, по-видимому, является лидером самой большой и опасной банды Субмишн. Ты мог отправиться прямо в его убежище, 'Крысиные Норы', или сначала обратиться за помощью к Клэр.";
		}
		@Override
		public String getCompletedDescription() {
			return "Используя пароль, который тебе дал Аксель, ты смог проникнуть в убежище Венгара; Крысиные Норы.";
		}
	},
	
	VENGAR_ONE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встреча с Венгаром";
		}
		@Override
		public String getDescription() {
			return "Чтобы найти Венгара, тебе нужно найти главный зал и быть там между часами "+Units.time(LocalDateTime.of(1, 1, 1, 6, 0))+" and "+Units.time(LocalDateTime.of(1, 1, 1, 22, 0))+".";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты нашел Венгара, сидящего на троне в главном зале, и, подойдя к нему, тебе дали выбор: либо присоединиться к его банде, либо быть атакованным его телохранительницами-крысами.";
		}
	},
	
	VENGAR_TWO_CONFLICT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конфликт";
		}
		@Override
		public String getDescription() {
			return "Выбрав вызов Венгару, тебе теперь нужно победить его в бою, чтобы утвердить свое доминирование над его бандой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты смог победить Венгара, но прежде чем ты смог предпринять какие-либо дальнейшие действия, SWORD начали свой рейд на Крысиные Норы.";
		}
	},
	
	VENGAR_TWO_COOPERATION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Подчинение Акселя";
		}
		@Override
		public String getDescription() {
			return "Венгар раскрыл, что он уже давно хочет сосредоточиться на своем законном бизнесе по производству рома, но он не может просто оставить Акселя без потери уважения со стороны своей банды."
					+ "В обмен на прекращение вымогательства, ты согласился убедить Акселя прийти и показать свое подчинение Венгару.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты смог убедить Акселя отправиться в Крысиные Норы и показать свое подчинение Венгару. Сопровождая его, ты смог повлиять на то, что случилось с мальчишкой-аллигатором.";
		}
	},
	
	VENGAR_TWO_ENFORCERS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Рейд";
		}
		@Override
		public String getDescription() {
			return "Подтвердив, что Венгар находится в Крысиных Норах, вы активировали резонансный камень, подав сигнал ждущим карателям SWORD начать рейд.";
		}
		@Override
		public String getCompletedDescription() {
			return "Каратели SWORD успешно провели рейд в Крысиных Норах и смогли схватить Венгара.";
		}
	},
	
	VENGAR_THREE_COOPERATION_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конец Венгара";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда [axel.name] проявил(а) [axel.her] покорность перед Венгаром, остается только вернуться в Игорный Дом...";
		}
		@Override
		public String getCompletedDescription() {
			return "После того как [axel.name] проявил(а) [axel.her] покорность перед Венгаром, группа карателей SWORD явилась, чтобы нагрянуть в Крысиные Норы и арестовать крысопарня!";
		}
	},

	VENGAR_THREE_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Вернуться к Акселю";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда с Венгаром покончено, вам нужно вернуться к Акселю и сообщить ему о случившемся.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись к Акселю и рассказали ему, как всё будет обстоять отныне.";
		}
	},
	
	VENGAR_OPTIONAL_CLAIRE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Помощь Клэр";
		}
		@Override
		public String getDescription() {
			return "Решив, что лучше всего будет сообщить Клэр о ситуации, вы спросили её, может ли что-то сделать карательный отряд, чтобы помочь. Судя по всему, команда SWORD уже готова к рейду в Крысиные Норы, но им нужно знать, что Венгар внутри, прежде чем начинать штурм. Клэр дала тебе камень резонанса, чтобы активировать его, если ты хочешь, чтобы они поддержали тебя, когда окажешься внутри.";
		}
		@Override
		public String getCompletedDescription() {
			return " Решив, что лучше всего будет сообщить Клэр о ситуации, ты спросила, есть ли что-то, что Энкары могут сделать, чтобы помочь."
					+ " Оказывается, команда SWORD уже готова к рейду в Лагерь Крыс, но им нужно знать, что Венгар внутри, прежде чем начать штурм."
					+ " Клэр дала тебе камень резонанса, чтобы активировать его, если ты хочешь, чтобы они поддержали тебя, когда окажешься внутри.";
		}
	},

	// Wes:

	WES_FAIL(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Упущенная возможность";
		}
		@Override
		public String getDescription() {
			return " После того, как ты сказала Уэсли, что не собираешься помогать ему с его расследованием, мальчик-лиса исчез, и ты можешь быть уверен, что он никогда больше не будет пытаться обратиться за твоей помощью...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	WES_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Нужна помощь";
		}
		@Override
		public String getDescription() {
			return " Путешествуя по Доминиону, к тебе подошел таинственный подставной Энкар SWORD, который попросил твою помощь."
					+ " Он хотел бы встретиться с тобой за пределами антикварного магазина Торгового Аркада между [units.time(13)]-[units.time(14)].";
		}
		@Override
		public String getCompletedDescription() {
			return " Ты согласилась помочь Уэсли расследовать его старшего офицера.";
		}
	},

	WES_1(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "В поисках Элль";
		}
		@Override
		public String getDescription() {
			return " Уэс рассказал тебе, что его старший офицер, [elle.name] (или 'Эль' для краткости), берет с собой очки ночного видения, когда уходит на подозрительно долгие обеды по средам, и всегда возвращается с мокрыми ботинками."
					+ " Тебе нужно найти ее и записать все улики на аркане записывающее устройство, которое Уэс дал тебе..."
					+ "<br/>Она делает [style.colourOrange(необычно долгие обеденные перерывы)]."
					+ "<br/>Она делает это только в [style.colourOrange(среду)]."
					+ "<br/>Она берет с собой набор [style.colourOrange(очков ночного видения)]."
					+ "<br/>После этого ее [style.colourOrange(обувь часто мокрая)] и иногда на ней есть следы какого-то [style.colourOrange(светящегося остатка)]."
					+ "<br/>[style.italicsMinorGood(Для подсказки о том, куда идти, спросите Лилайю об этом.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Догадавшись, что Элл делает свой тёмный бизнес в Бат-Пещерах, вам удалось собрать доказательства её продажи оружия опасной преступной группировке.";
		}
	},

	WES_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Использование доказательств";
		}
		@Override
		public String getDescription() {
			return "С вашим арканическим записывающим устройством, содержащим компрометирующие доказательства коррупции Элл, у вас есть два возможных варианта того, что делать с ними."
					+ "Вы могли бы сдать их анонимно либо Клэр, либо Кэнди, или, если бы вы хотели предать Веса и перейти на сторону Элл,"
						+ "вы могли бы подождать снаружи штаба Энуфа между [units.time(16)]-[units.time(18)] и раскрыть всё [elle.race], когда она уходит с работы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты использовал доказательства, которые ты собрал, чтобы положить конец всему этому делу с Весом и Элль.";
		}
	},

	WES_3_WES(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Поддержал Вес";
		}
		@Override
		public String getDescription() {
			return "Ты решил сделать то, что попросил Вес, и сдать компрометирующие доказательства в качестве анонимного сообщения Энфорсерам."
					+ "Тебе следует подождать как минимум неделю, пока все не уладится, а затем попросить у Веса встречу в штаб-квартире Энфорсеров между [units.time(9)]-[units.time(17)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты поддержал Вес, и после того, как анонимно сдал компрометирующие доказательства, ты встретился с лисичкой в штаб-квартире Энфорсеров, чтобы узнать, что он был повышен по службе и занял место Элль."
					+ "С его новой властью, он предоставил тебе доступ к зоне закупок штаб-квартиры Энфорсеров в качестве награды за твою помощь."
					+ "Теперь ты также можешь свободно посещать Вес в штаб-квартире Энфорсеров между [units.time(9)]-[units.time(17)].";
		}
	},

	WES_3_ELLE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Поддержал Элль";
		}
		@Override
		public String getDescription() {
			return "Ты решил предать Вес и рассказать все Элль."
					+ "Благодарная за твою неожиданную поддержку, [elle.race] пообещала вознаградить тебя, если ты вернешься в штаб-квартиру Энфорсеров после того, как пройдет как минимум неделя...";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты встал на сторону Элли, и после возвращения, чтобы встретиться с ней в штабе Насильщика, ты обнаружил, что Уэс был порабощен."
					+ "Благодарная за твою поддержку, [elle.race] предоставила тебе доступ к зоне реквизиций штаба Насильщика."
					+ "Теперь ты также можешь свободно посещать Элли (и Уэса) в штабе Насильщика в часы [units.time(9)]-[units.time(17)].";
		}
	},

	
	// Rebel Base for HLF Quest
	
	REBEL_BASE_HANDLE_REFUSED(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Нажми на рычаг!";
		}
		@Override
		public String getDescription() {
			return "Ты нашел странную ручку в пещере летучих мышей. Кто знает, для чего она и что она делает.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты потянул за ручку, несмотря на твои опасения.";
		}
	},

	REBEL_BASE_PASSWORD_PART_ONE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Потяни за ручку, получишь загадку";
		}
		@Override
		public String getDescription() {
			return "Странная ручка попросила пароль, которого у тебя нет. Возможно, немного поиска в окрестностях раскроет некоторые подсказки.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты нашел половину страницы журнала, в которой говорилось, что пароль состоит из двух слов. Ты смог расшифровать одно слово, другое было порвано.";
		}
	},

	REBEL_BASE_PASSWORD_PART_TWO(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Заполни пропуск";
		}
		@Override
		public String getDescription() {
			return "Другая половина пароля должна быть на другой половине страницы журнала. Возможно, она все еще находится рядом.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли обе половины пароля, вместе они составляют фразу 'RUAT CAELUM'";
		}
	},

	REBEL_BASE_PASSWORD_COMPLETE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Как скажешь";
		}
		@Override
		public String getDescription() {
			return "С полным паролем вы теперь можете снова попробовать пройти проверку ручки.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили, что ручка была на самом деле прикреплена к двери, которая вела в скрытую пещеру, отходящую от Батовых Пещер.";
		}
	},

	REBEL_BASE_EXPLORATION(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Спуск в пещеры";
		}
		@Override
		public String getDescription() {
			return "Неизвестно, для чего предназначена скрытая пещера или куда она ведет. Возможно, вы сможете найти некоторые ответы внутри.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы узнали, что скрытая пещера была укрытием для давно исчезнувшей повстанческой группы. Судя по всему, они не победили.";
		}
	},

	REBEL_BASE_ESCAPE(QuestType.SIDE,
			15,
			100) {
		@Override
		public String getName() {
			return "Сойти с рук";
		}
		@Override
		public String getDescription() {
			return "Время бежать, желательно до того, как эта пещера обрушится на себя.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы сумели выбраться целым и невредимым. Что бы ни было в этой пещере, это теперь навсегда погребено под землей.";
		}
	},

	REBEL_BASE_FAILED(QuestType.SIDE,
			15,
			0) {
		@Override
		public String getName() {
			return "Мало храбрости";
		}
		@Override
		public String getDescription() {
			return "Вам удалось выбраться из пещеры, но все секреты, которые она хранила, теперь навсегда погребены.";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	REBEL_BASE_FIREBOMBS_START(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Разминирование";
		}
		@Override
		public String getDescription() {
			return "Фейерверки, которые вы получили в пещере, могут пригодиться в бою. Вам нужно найти кого-то, кто может либо сделать, либо приобрести больше.";
		}
		@Override
		public String getCompletedDescription() {
			return "Рокси согласилась разобраться с получением большего количества фейерверков для вас.";
		}
	},

	REBEL_BASE_FIREBOMBS_FINISH(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Огонь из крыс";
		}
		@Override
		public String getDescription() {
			return "Рокси понадобится два дня, чтобы получить новую партию фейерверков. Вам следует вернуться к ней тогда.";
		}
		@Override
		public String getCompletedDescription() {
			return "Каким-то образом Рокси не обманула вас, и вы получили партию фейерверков.";
		}
	},

	REBEL_BASE_FIREBOMBS_FAILED(QuestType.SIDE,
			1,
			0) {
		@Override
		public String getName() {
			return "Неохота Рокси";
		}
		@Override
		public String getDescription() {
			return "Без примера, который можно было бы показать Рокси, она либо не смогла понять, либо не захотела заморачиваться с попыткой повторить фейерверки, которые вы нашли...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	//Eisek Quests
	
	EISEK_STALL_QUEST_STAGE_ONE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Сбор материалов";
		}
		@Override
		public String getDescription() {
			return "Вы узнали, что Эйсеку нужно для ремонта его лавки, а также что он хотел бы на новом вывеске. Теперь вам просто нужно собрать некоторые материалы у торговцев по всему городу для приятного сюрприза. Может быть, есть такой, который торгует тканью?";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы заказали новую вывеску и несколько кусков ткани у Моники.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Нужен тент здесь";
		}
		@Override
		public String getDescription() {
			return "Пока вы ждете, пока Моника закончит ваш заказ, вам нужно найти деревянные шесты для тента. Возможно, местная кузница могла бы как-то изменить рукоятку копья.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы разместили заказ у Имсу и Хейла на несколько модифицированных рукояток для топоров.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Все складывается";
		}
		@Override
		public String getDescription() {
			return "Вам следует вернуться к Хейлу через день и к Монике через три дня, чтобы узнать, готов ли ваш заказ.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы собрали все материалы.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_FOUR(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Собираем все вместе";
		}
		@Override
		public String getDescription() {
			return "У вас есть все необходимое, чтобы улучшить лавку Эйсека. Расскажите ему об этом в следующий раз, когда увидите его.";
		}
		@Override
		public String getCompletedDescription() {
			return "Насколько вы могли судить, Эйсек был в восторге от того, что вы сделали для него, и его лавка выглядит лучше, чем когда-либо.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_ONE(QuestType.SIDE,
			10,
			25) {
		@Override
		public String getName() {
			return "Один против многих";
		}
		@Override
		public String getDescription() {
			return "Эйсек объяснил, почему его преследует толпа, но он не знает многого о них. Если вы хотите убедиться, что они не вернутся, вам придется найти их и противостоять им."
					+ "<br/>Поскольку они, похоже, были местными, возможно, поиск по городу сработает.";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря немногому везению и тому, что банда разместила большой красочный плакат, вы нашли и вошли в их место встречи.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO(QuestType.SIDE,
			10,
			100) {
		@Override
		public String getName() {
			return "Оставьте Дракона в покое!";
		}
		@Override
		public String getDescription() {
			return "Вы нашли, где банда встречалась. Пора разобраться с ними!";
		}
		@Override
		public String getCompletedDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate"))) {
			    return "Вы решили попытаться убедить банду оставить Эйсека в покое, используя ваше внушительное телосложение.";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate_arcane"))) {
			    return "Вы решили попытаться убедить банду оставить Эйсека в покое, используя свои арканы.";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_persuade"))) {
			    if(!Main.game.isSillyModeEnabled()) {
			    	return "Вы убедили банду оставить Эйсека в покое, произнеся трогательную речь.";
			    } else {
			    	return "Вы разрушили аргументы банды ФАКТАМИ и ЛОГИКОЙ.";
			    }
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_seduce"))) {
			    return "Вы убедили банду оставить Эйсека в покое, используя свое мастерство магии похоти, чтобы разжечь оргию.";
			} else {
			    return "Вы не смогли убедить банду оставить Эйсека в покое.";
			}
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "Вышвырнутый";
		}
		@Override
		public String getDescription() {
			return "Ты не смогла убедить толпу оставить Эйзека в покое. Тебе следует вернуться к нему с плохими новостями, теперь, когда ты не смогла справиться с толпой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты не смогла убедить толпу оставить Эйзека в покое.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_THREE_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "Плохие новости";
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String getCompletedDescription() {
			return "Хотя он пытался скрыть это, Эйзек был расстроен, что толпа все еще где-то там, замышляя против него.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_THREE(QuestType.SIDE,
			10,
			250) {
		@Override
		public String getName() {
			return "Хорошие новости";
		}
		@Override
		public String getDescription() {
			return "Тебе следует вернуться к Эйзеку с хорошими новостями, теперь, когда ты справилась с толпой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Хотя он пытался скрыть это, Эйзек был очень рад, что толпа теперь оставит его в покое. Ты даже получила редкие Драконьи фрукты.";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_ONE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Странная толпа";
		}
		@Override
		public String getDescription() {
			return "Ты столкнулась с другой толпой, которая была странно одержима Эйзеком. В итоге это не привело ни к чему, но ты решила посмотреть, что это за странные люди.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты вошла в какое-то подземелье, где они собрались.";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Темнейшее подземелье";
		}
		@Override
		public String getDescription() {
			return "Ты последовала за обитателями подземелья обратно в их подземелье и решила заглянуть внутрь. К сожалению, они не очень оценили твое вторжение и заблокировали путь, по которому ты пришла.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты почти у выхода, осталось только одно препятствие...";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Подземелье зачищено";
		}
		@Override
		public String getDescription() {
			return "Одолев лидера этой странной группы, все, что осталось, это уйти.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты успешно сбежала из подземелья и показала кучке nerds, кто тут главный.";
		}
	},
	
	// Fetching beer barrels for Oglix:
	
	OGLIX_BEER_BARRELS_1(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Бочки Хейрона";
		}
		@Override
		public String getDescription() {
			return "Согласившись помочь Огликс расширить её коллекцию пивных сучек, ты была поручена отправиться в таверну 'Меч Кентавра' и попросить у хозяина, Хирона, любые лишние бочки, которые у него могут быть."
					+ "Если он откажется помочь, Голикса поручила тебе сказать кентавру, что 'Голикса говорит, будь хорошим коньком'.";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря специальной фразе 'Голикса говорит, будь хорошим коньком', тебе удалось убедить Хирона отправить четыре лишние бочки в таверну Огликс.";
		}
	},
	
	OGLIX_BEER_BARRELS_2(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Отчёт о Хорошем Коньке";
		}
		@Override
		public String getDescription() {
			return "Обеспечив помощь Хирона, тебе теперь нужно вернуться к Огликс и сообщить ей, что Хирон решил быть 'хорошим коньком' для Голиксы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты вернулась к Огликс и сообщила ей о своём успехе."
					+ "Теперь, обеспечив четыре дополнительные бочки, в которых можно запереть новых пивных сучек, Огликс попросила тебя найти перспективных кандидатов из криминальной среды близлежащих переулков."
					+ "Кроме того, она сказала тебе подглядывать за таверной с тыльной стороны между [units.time(6)]-[units.time(7)], если ты хотела бы узнать, что значит фраза 'Голикса говорит, будь хорошим коньком'...";
		}
	},

	
	// Helping Lunexis to escape:
	
	LUNEXIS_ESCAPE(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Освободить Лунексиса";
		}
		@Override
		public String getDescription() {
			return "Сдавшись Лунексис и пообещав быть её послушным коньком, ты была приказана твоей новой Mistress помочь ей сбежать из заточения."
					+ "Желая отомстить тому, кто телепортировал её в Элис, кентаврыня придумала план, согласно которому ты должен убедить Мераксиса телепортировать вас троих обратно в Темискьюру."
					+ "Там твоя Mistress вознаградит тебя, оставив тебя одной из её личных рабынь.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты убедил Мераксиса телепортировать себя, вместе с тобой и Лунексис, обратно в Темискьюру."
					+ "Там твоя ложь была раскрыта, и хотя она пыталась бороться, Мераксис вскоре была подчинена и использована твоей Mistress, чтобы вернуть лояльность её армии кентавров.";
		}
	},

	LUNEXIS_ESCAPE_FAILED(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Предательство Лунексиса";
		}
		@Override
		public String getDescription() {
			return "Решив раскрыть всё Мераксис, ты предал Лунексис, и тебе было запрещено иметь дальнейший контакт с демонической кентаврой.";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	
	// Doll factory quests:
	
	DOLL_FACTORY_1(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Исследуй Люксы Ловиенн";
		}
		@Override
		public String getDescription() {
			return "Дневник Анджеликс показал, что похищенных беженцев телепортировали в магазин 'Люксы Ловиенн' в Доминионе."
					+ "Если ты хочешь узнать, что случилось с жертвами Анджеликс, то тебе нужно исследовать этот магазин...";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты отправился в магазин 'Люксы Ловиенн' и попытался узнать, были ли похищенные Анджеликс беженцы доставлены туда."
					+ "Хотя ты и не смог ничего узнать, репортер по имени Фиаметта подошла к тебе, когда ты уходил, и предложила свою помощь.";
		}
	},
	
	DOLL_FACTORY_2(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Взлом и проникновение";
		}
		@Override
		public String getDescription() {
			return "Фиаметта знает способ проникнуть в заднюю часть Lovienne's Luxuries, где, по ее мнению, содержатся похищенные беженцы и используются в качестве рабского труда."
					+ "Не имея другого способа проникнуть и добраться до сути того, что происходит там, вы согласились с ее планом и сказали, что встретитесь с ней возле магазина между [units.time(1)]-[units.time(4)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встретились с Фиаметтой возле Lovienne's Luxuries и смогли проникнуть в заднюю часть помещений, не сразив систему сигнализации.";
		}
	},
	
	DOLL_FACTORY_3(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Докопаться до истины";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда вам удалось проникнуть в заднюю часть Lovienne's Luxuries, вам нужно исследовать и выяснить, где находятся похищенные беженцы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили большой лифт в задней части Lovienne's Luxuries, и, спустившись на него, вы обнаружили большую установку, спрятанную глубоко под Dominion.";
		}
	},
	
	DOLL_FACTORY_4(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Сбор улик";
		}
		@Override
		public String getDescription() {
			return "Вам и Фиаметте нужно найти доказательства того, что произошло с беженцами."
					+ "Журналы, схемы машин или другие подобные документы наверняка находятся где-то здесь...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось собрать веские доказательства причастности Саеллатрикс и Анджеликкс к похищению и незаконному рабству, а также получить тревожные знания о том, как создаются куклы Ловиенн.";
		}
	},
	
	DOLL_FACTORY_5(QuestType.SIDE, 30, 250) {
		@Override
		public String getName() {
			return "Время уходить";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда Фиа получила достаточно доказательств, чтобы опубликовать статью, вам обоим нужно сбежать с фабрики...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы с Фией смогли сбежать с нижней фабрики.";
		}
	},
	
	DOLL_FACTORY_5_DOLLIFIED(QuestType.SIDE, 30, 250) {
		@Override
		public String getName() {
			return "Окуклена!";
		}
		@Override
		public String getDescription() {
			return "Задержанная, Ангеликс превратила вас в куклу! Вам срочно нужно найти Фию, чтобы отменить эту тревожную трансформацию."
					+ "<br/>[style.italics(Вам нужно найти и исследовать четыре)] [style.italicsExcellent(желтый)] [style.italics(плитки внутри фабрики, чтобы найти Фию.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "После того, как вас превратили в куклу, вы смогли найти Фию и отменить процесс, после чего вы оба сбежали с фабрики.";
		}
	},
	
	DOLL_FACTORY_6(QuestType.SIDE, 30, 500) {
		@Override
		public String getName() {
			return "Послание Фьяметты";
		}
		@Override
		public String getDescription() {
			return "Фиаметта обещала написать статью обо всем, что вы оба видели в Lovienne's Luxuries."
					+ "Она обещала отправить вам сообщение, как только оно будет готово к публикации."
					+ "<br/>[style.italicsMinorGood(После того, как прошло несколько дней, зайдите в вестибюль особняка Лилайи в дневное время.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Вместо получения сообщения от Фиаметты, вы встретили Саэллатрикс."
					+ "Используя свои интимные отношения с Лилайей, она поставила тебя в тупик и потребовала, чтобы ты подписал документ, в котором говорилось, что Фиамметта лжёт.";
		}
	},
	
	//TODO
	
	DOLL_FACTORY_7A(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Честность превыше всего";
		}
		@Override
		public String getDescription() {
			return "Ты отказался от сделки с Саэллатрикс и вместо этого поклялся говорить правду и поддерживать всё, что Фиамметта написала в своей статье."
					+ "Прежде чем быстро покинуть особняк, Саэллатрикс упомянула, что её магазин будет закрыт на неделю или две, пока она ждёт, пока общественное возмущение утихнет."
					+ "<br/>[style.italicsMinorGood(Тебе следует вернуться в Lovienne's Luxuries, когда он снова откроется, чтобы узнать, что стало с куклами...)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты отказался от сделки с Саэллатрикс и вместо этого поклялся говорить правду и поддерживать всё, что Фиамметта написала в своей статье."
					+ "Вернувшись в Lovienne's Luxuries после того, как он был закрыт на неделю, ты обнаружил, что Анджеликс была вынуждена взять на себя всю вину за незаконное порабощение,"
						+ "и что Саэллатрикс теперь имеет право превращать только худших преступников в куклы."
					+ "<br/>"
					+ "Более того, не желая иметь тебя в качестве врага, Саэллатрикс была заинтересована в том, чтобы ты оставался её клиентом, и даже позволила тебе решить, что делать с Анджеликс...";
		}
	},
	
	DOLL_FACTORY_7B(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Это просто хороший бизнес";
		}
		@Override
		public String getDescription() {
			return "Ты предал доверие Фиамметты и подписал документ, в котором утверждается, что репортер лжёт."
					+ "Саэллатрикс была рада твоему решению и попросила тебя вернуться в её магазин, чтобы поговорить больше о твоих особых наградах..."
					+ "<br/>[style.italicsMinorGood(Тебе следует вернуться в Lovienne's Luxuries, когда она снова откроется через пару дней...)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты предал доверие Фиамметты и подписал документ, в котором утверждается, что репортер лжёт."
					+ "Взамен Саэллатрикс предложила тебе превратить любого из твоих рабов в куклы в обмен на небольшую плату, или же заплатит тебе, если ты позволишь ей оставить их, чтобы продать в её магазине.";
		}
	},
	
	
	// Romance quests:

	RELATIONSHIP_NYAN_1_STOCK_ISSUES(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Помощь Nyan";
		}
		@Override
		public String getDescription() {
			return "Нян объяснила, что она не может продавать никакую волшебную одежду из-за того, что её оптовый поставщик внезапно перестал с ней общаться."
					+ "По-видимому, этот поставщик раньше был в хороших отношениях с Нян, и его необычное поведение заставило нервную кошачью девушку заподозрить, что с ним случилось что-то ужасное.<br/>"
					+ "Возможно, ты мог бы предложить Нян свою помощь, выяснив, что случилось с этим поставщиком?";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты предложил Нян свою помощь в выяснении, что случилось с её оптовым поставщиком волшебной одежды.";
		}
	},
	
	RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Спасение Kay";
		}
		@Override
		public String getDescription() {
			return "Нян рассказала тебе, что её поставщик, [kay.nameFull], ведёт свой бизнес в складском районе Доминиона."
					+ "Вам нужно отправиться на северо-запад города, найти склад Кея и выяснить, почему он внезапно прервал все контакты с Нян.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы не только нашли склад Кея, но и смогли выяснить, что его бизнес был фактически захвачен парой грубых охотников за головами.";
		}
	},
	
	RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "Охотники за головами";
		}
		@Override
		public String getDescription() {
			return "Вольфганг и Карл, пара охотников за головами, нанятых Кеем для защиты его склада, перешли на сторону своих работодателей и фактически захватили полный контроль над его бизнесом."
					+ "Так или иначе, вам придется убедить этих доберманов оставить Кея в покое...";
		}
		@Override
		public String getCompletedDescription() {
			return "Управившись с Вольфгангом и Карлом и отправив их обратно в логово охотников за головами в Славер-Аллее, вы спасли бизнес Кея и обеспечили Нян доступ к запасам волшебной одежды."
					+ "Выразив вам свою вечную благодарность, Кей сказал, что вы можете посетить его в любое время.";
		}
	},
	
	RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Награда Nyan";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда вы спасли бизнес Кея, вам следует вернуться к Нян и сообщить ей о произошедшем.";
		}
		@Override
		public String getCompletedDescription() {
			return "Очень счастливая Нян заплатила вам обещанную награду и также предложила вам пожизненную скидку 25% в ее магазине."
					+ "Она также намекнула, что она одинока, в неуклюжей попытке попытаться соблазнить вас...";
		}
	},
	
	
	
	ROMANCE_HELENA_FAILED(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Разгневанная Матриарх";
		}

		@Override
		public String getDescription() {
			return "После того, как ты отказался продать Скарлетт Хелене, гордая матка гарпий отказалась от своих планов по организации бизнеса по продаже рабов и улетела обратно в свое гнездо."
					+ "Учитывая, как она безжалостно оскорбила тебя перед уходом, можно быть уверенным, что она никогда больше не захочет тебя видеть...";
		}

		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_HELENA_1_OFFER_HELP(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Предложить помощь";
		}

		@Override
		public String getDescription() {
			return "Спросив Хелену о ее бизнесе, ты обнаружил, что она едва справляется с управлением заведением."
					+ "Выразив желание внести некоторые улучшения в ее магазин, Хелена рассказала, что у нее просто нет времени и желания заниматься этим сама."
					+ "Может быть, ты мог бы предложить ей свою помощь?";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты предложил Хелене свою помощь в улучшении ее магазина.";
		}
	},

	ROMANCE_HELENA_2_PURCHASE_PAINT(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Купить краску";
		}

		@Override
		public String getDescription() {
			return "Хелена рассказала, что первое, что она хочет сделать, — это покрасить весь фасад своего магазина. Не предоставив тебе денег на расходы, гарпия ожидает, что ты отправишься в 'Магазин DIY Аргуса' и купишь банку золотой краски 'Purple-star'. После того как ты купишь ее, тебе нужно вернуться к Хелене.<br/><i>('Магазин DIY Аргуса' находится довольно далеко к югу от Переулка Рабов, рядом с каналом.)</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты купил нужную краску в 'Магазине DIY Аргуса' и вернулся к Хелене.";
		}
	},

	ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор экстерьера (1/3)";
		}

		@Override
		public String getDescription() {
			return "Купив золотую краску, вы вернулись к Helena, но она тут же потребовала, чтобы вы как можно скорее приступили к перекраске фасада её лавки...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы содрали всю старую краску с передней части лавки Helena.";
		}
	},

	ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор экстерьера (2/3)";
		}

		@Override
		public String getDescription() {
			return "Вам нужно вернуться в лавку Helena в часы работы, чтобы узнать, каким будет ваше следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы перекрасили весь фасад лавки Helena и дополнительно получили поставку мебели от суккуба по имени Natalya.";
		}
	},

	ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор экстерьера (3/3)";
		}

		@Override
		public String getDescription() {
			return "Вам нужно вернуться в лавку Helena в часы работы, чтобы узнать, каким будет ваше следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Под присмотром матриарха гарпий вы вывели золотыми буквами над дверью её лавки надпись «Бутик Helena».";
		}
	},

	ROMANCE_HELENA_4_SCARLETTS_RETURN(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Возвращение Scarlett";
		}

		@Override
		public String getDescription() {
			boolean slave = Main.game.getNpc(Scarlett.class).isSlave() || Main.game.getNpc(Scarlett.class).getHomeWorldLocation()==WorldType.EMPTY;
			boolean playerOwner = Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer();
			return "Helena раскрыла вам свой план: она хочет провести ребрендинг своего магазина рабов, превратив его в место, где клиенты смогут заказывать рабов на заказ. Человеком, которого она хочет нанять для обучения таких рабов, является её старый учитель этикета, который, судя по всему, приходится сестрой самой Scarlett."
					+ (slave
						?"Условие, которое она поставила Хелене, заключается в том, что она должна освободить свою непослушную сестру от рабства, пообещать никогда больше не порабощать её, а затем продолжать её использовать..."
							+ "<br/>"
							+(playerOwner
								?"Тебе придётся привести Скарлетт к Хелене и продать её обратно..."
								:"Тебе придётся найти Скарлетт и купить её у того, кто стал её новым владельцем. По словам Хелены, она, по-видимому, была куплена владельцем антикварного магазина где-то в Торговом Аркаде.")
						:"Условие, которое она поставила Хелене, заключается в том, что она должна продолжать использовать свою непослушную сестру и пообещать никогда больше не порабощать её..."
							+ "<br/>"
							+ "Тебе придётся подняться на гнездо Хелены, найти Скарлетт, а затем сказать ей вернуться к Хелене...");
		}
		
		@Override
		public String getCompletedDescription() {
			return "По желанию старого этикет-тренера Хелены, Скарлетт отныне будет работать личным помощником матриарха гарпий.";
		}
	},

	ROMANCE_HELENA_5_SCARLETT_TRAINER(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Помощница гарпии";
		}

		@Override
		public String getDescription() {
			return "Две гарпии ушли рано, чтобы отправиться в гости к сестре Скарлетт. Тебе нужно вернуться в магазин Хелены во время её работы, чтобы узнать, какова будет твоя следующая задача...";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты вернулся в магазин Хелены, чтобы обнаружить, что всё готово и готово к тому, чтобы матриарх гарпий начала принимать клиентов. Однако, прежде чем это произойдёт, тебе нужно помочь ей с ещё парой вещей...";
		}
	},

	ROMANCE_HELENA_6_ADVERTISING(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Реклама";
		}

		@Override
		public String getDescription() {
			return "Получив полдюжины зачарованных плакатов, демонстрирующих красоту Хелены, твоя задача - повесить их у входа в Улицу Рабовладельцев, чтобы помочь рекламировать её магазин.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты повесил плакаты, рекламирующие 'Бутик Хелены', у входа в Улицу Рабовладельцев.";
		}
	},

	ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Подготовка к грандиозному открытию";
		}

		@Override
		public String getDescription() {
			return "После того, как вы повесили плакаты, появилась Скарлетт и привела вас обратно в магазин Хелены."
					+ "Ваша новая задача - подготовить все к завтрашнему грандиозному открытию, что означает работать всю ночь...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы и Скарлетт закончили подготовку к грандиозному открытию магазина.";
		}
	},

	ROMANCE_HELENA_8_FINISH(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Приготовление напитков";
		}

		@Override
		public String getDescription() {
			return "Не желая, чтобы Скарлетт создавала какие-либо проблемы во время грандиозного открытия, Хелена поручила вам оставаться в задней комнате и готовить напитки для гостей.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы и Скарлетт оставались в задней комнате, готовя напитки, пока грандиозное открытие не закончилось."
					+ "Наконец, выразив свою признательность за ваши усилия, Хелена сказала, что она готова отпустить вас на свидание в качестве награды...";
		}
	},
	
	

	ROMANCE_NATALYA_FAILED_INTERVIEW(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Интервью провалено";
		}
		@Override
		public String getDescription() {
			return "Отказавшись сделать то, что попросила Наталия во время интервью, вас выгнали из Dominion Express и сказали никогда не возвращаться...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	ROMANCE_NATALYA_FAILED_CONTRACT(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Контракт отклонен";
		}
		@Override
		public String getDescription() {
			return "Отказавшись подписать контракт, который Наталия предложила вам, вас выгнали из Dominion Express и сказали никогда не возвращаться...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_NATALYA_1_INTERVIEW_START(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Прошедшее интервью";
		}
		@Override
		public String getDescription() {
			return "Наталия, конюха в компании Dominion Express, предложила вам возможность пройти собеседование на должность '[style.mule]'.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы приняли предложение Натальи на собеседование на должность '[style.mule]' в Dominion Express.";
		}
	},

	ROMANCE_NATALYA_2_CONTRACT_SIGNED(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "[style.Mule] Натальи";
		}
		@Override
		public String getDescription() {
			return "Приняв предложение на собеседование на должность '[style.mule]', вам теперь нужно успешно пройти его и подписать контракт.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы успешно прошли собеседование Натальи, и после того, как вы подписали контракт, вам сказали, что теперь вам нужно превратиться в [style.a_shemale] таура.";
		}
	},
	
	ROMANCE_NATALYA_3_TRAINING_1(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "[style.Mule] Обучение";
		}
		@Override
		public String getDescription() {
			return "Наталия сказала вам, что первая часть вашего обучения будет заключаться в повторении вашего устного выступления на одном из рабов-цетав.";
		}
		@Override
		public String getCompletedDescription() {
			return "После того, как вы превратились в [style.a_shemale] таура, вы начали свое [style.mule] обучение, сосая член одного из более непослушных рабов-цетав Dominion Express.";
		}
	},

	ROMANCE_NATALYA_4_TRAINING_2(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Дополнительное обучение";
		}
		@Override
		public String getDescription() {
			return "Наталия снова сказала вам вернуться на следующий день, чтобы продолжить обучение, которое будет заключаться в том, чтобы научиться любить давать римжобы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вторая стадия вашего обучения заключалась в том, чтобы надеть яркую помаду и делать анальный секс с госпожой Натальей.";
		}
	},

	ROMANCE_NATALYA_5_TRAINING_3(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Финальное обучение";
		}
		@Override
		public String getDescription() {
			return "Наталия поручила вам вернуться на следующий день, чтобы закончить обучение, которое будет заключаться в том, чтобы дать римжоб рабу-цетаву, и быть mounted и анально ебаной как Натальей, так и рабом, чью жопу вы обслуживаете.";
		}
		@Override
		public String getCompletedDescription() {
			return "После того, как ты сделала анал с рабыней-кентавром, а затем была ею насилуема, Наталия заявила, что твое обучение по программе [style.mule] завершено.";
		}
	},

	ROMANCE_MONICA_1_TO_THE_FARM(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "На ферму";
		}
		@Override
		public String getDescription() {
			return "После того, как ты предложила помочь вернуть свой персонализированный молокоотсос, Моника сказала, что его можно найти на ее старом рабочем месте; ферме, расположенной на северо-восток от Элиса, под названием 'Молочная ферма Эвелин'."
					+ "Тебе придется пойти на эту ферму и спросить о персонализированном молокоотсосе Моники...";
		}
		@Override
		public String getCompletedDescription() {
			return "Найдя ферму, которая является старым рабочим местом Моники, ты спросила о персонализированном молокоотсосе коровницы, и тебе предоставили встречу с владельцем фермы...";
		}
	},

	ROMANCE_MONICA_2_UNREASONABLE_DEMAND(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Необоснованное требование";
		}
		@Override
		public String getDescription() {
			return "Тебе удалось получить встречу с владельцем фермы, Эвелин, которая принимает форму высокомерной, жадной суккубы."
					+ "Хотя она признала, что молокоотсос бесполезен, она требует, чтобы ты либо дала ей огромную сумму Кредитов / Огни (Flames), либо подписала подозрительный контракт, в обмен на него...";
		}
		@Override
		public String getCompletedDescription() {
			return "Тебе удалось получить персонализированный молокоотсос Моники от Эвелин.";
		}
	},

	ROMANCE_MONICA_3_THE_JOURNEY_HOME(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Путешествие домой";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда у вас есть персонализированный молокоотсос Моники, всё, что осталось сделать, это вернуть его законной владелице.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернули Монике её персонализированный молокоотсос, к её удивлению и радости.";
		}
	},
	;

	private int level, experienceReward;
	private QuestType questType;

	private Quest(QuestType questType, int level, int experienceReward) {
		this.questType = questType;

		this.level = level;
		this.experienceReward = experienceReward;
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getCompletedDescription();
	
	public void applySkipQuestEffects() {	
	}
	
	public int getLevel() {
		return level;
	}

	public QuestType getQuestType() {
		return questType;
	}

	public int getExperienceReward() {
		return experienceReward;
	}
	
	public static Quest getQuestFromId(String quest) {
		if(quest.equalsIgnoreCase("MAIN_3_A_FINDING_THE_YOUKO")) {
			return Quest.MAIN_3_ELIS;
		}
		if(quest.equalsIgnoreCase("MAIN_3_D_TO_THEMISCRYA")) {
			return Quest.MAIN_3_D_TO_THEMISCYRA;
		}
		if(quest.equalsIgnoreCase("MAIN_3_I_ARION_REPORT")) {
			return Quest.MAIN_3_I_ELIS_DEFENCE_PLAN;
		}
		
		return Quest.valueOf(quest);
	}

}
