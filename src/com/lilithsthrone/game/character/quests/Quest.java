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
			return "Пережить вечер";
		}
		@Override
		public String getDescription() {
			return "Вы обещали своей тете Лили, что посетите открытие новой экспозиции в ее музее. Вам нужно пережить скуку предстоящего вечера.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ваш вечер в музее оказался куда более насыщенным, чем хотелось бы. Таинственный демон по имени Lilith обманом затянула вас в магический портал, ведущий в параллельную вселенную. Проснувшись посреди незнакомой улицы, вы были спасены из ужасной ситуации полудемоном по имени Lilaya. Похоже, она — местная версия вашей тети Лили, и в обмен на согласие помочь ей с экспериментами она позволяет вам жить в ее доме.";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Испытания Lilaya";
		}
		@Override
		public String getDescription() {
			return "Вы можете найти Lilaya в ее лаборатории в любое время — она будет готова продолжить опыты над вами. Возможно, ей удастся найти способ отправить вас домой?";
		}
		@Override
		public String getCompletedDescription() {
			return "Lilaya провела над вами еще несколько тестов, но она не может продвинуться в своих исследованиях без помощи своего старого коллеги, Arthur.";
		}
		@Override
		public void applySkipQuestEffects() {
			((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
		}
	},

	MAIN_1_B_DEMON_HOME(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Поиски Arthur; Обитель демонов";
		}
		@Override
		public String getDescription() {
			return "Lilaya сообщила вам, что ее старый коллега, Arthur, должен лучше знать о типе магии, использованной в портале. Однако она, кажется, питает к нему сильную неприязнь, и теперь ваша задача — заставить его извиниться перед Lilaya, прежде чем она позволит ему прийти и работать с ней. Arthur живет в многоквартирном доме под названием «Sawlty Towers» в городском районе, известном как «Обитель демонов», так что вы можете найти его там.";
		}
		@Override
		public String getCompletedDescription() {
			return "Когда вы прибыли к дому Arthur, то обнаружили, что Enforcers из Dominion арестовали его по подозрению в заговоре против Lilith. После ареста его доставили в штаб-квартиру Enforcer.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},

	MAIN_1_C_WOLFS_DEN(QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return "Поиски Arthur; Волчье логово";
		}
		@Override
		public String getDescription() {
			return "Arthur был арестован Enforcers из Dominion и доставлен в штаб-квартиру Enforcer. Похоже, вам придется разузнать всё на месте и найти способ спасти Arthur.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам пришлось столкнуться с инспектором Enforcer по имени Brax. К счастью, вам удалось справиться с ним, но затем вы узнали, что Arthur был продан в рабство!";
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
			return "Поиски Arthur; Продан в рабство";
		}
		@Override
		public String getDescription() {
			return "Победив Brax, вы узнали, что Arthur был продан в рабство торговке по имени Scarlett. Вам нужно будет отправиться в Переулок работорговцев, найти Scarlett и придумать, как освободить Arthur.";
		}
		@Override
		public String getCompletedDescription() {
			return "В Переулке работорговцев вы нашли гарпию Scarlett, которая оказалась одной из самых грубых личностей, что вам когда-либо встречались.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},
	
	MAIN_1_E_REPORT_TO_HELENA(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "Поиски Arthur; Найти Helena";
		}
		@Override
		public String getDescription() {
			return "Найдя Scarlett в Переулке работорговцев, вы обнаружили, что Arthur ей больше не принадлежит. Прежде чем она расскажет вам что-либо об этом, она хочет, чтобы вы отправились в Гнезда гарпий и сообщили её матриарху, Helena, что её дела — полный провал.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы сообщили о проблемах Scarlett её матриарху, Helena. Она не выказала особого сочувствия к Scarlett и быстро улетела, чтобы поговорить с ней лично.";
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
			return "Поиски Arthur; Судьба Scarlett";
		}
		@Override
		public String getDescription() {
			return "Вам нужно вернуться в лавку Scarlett, чтобы узнать, что с ней стало. Будем надеяться, Helena не слишком жестоко с ней обходилась, и теперь она согласится рассказать вам, что случилось с Arthur...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись в лавку Scarlett, но обнаружили, что Helena превратила её в рабыню!";
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
			return "Поиски Arthur; Рабство";
		}
		@Override
		public String getDescription() {
			return "Helena готова продать вам Scarlett, и, похоже, это единственный способ получить нужную информацию. Чтобы купить Scarlett, вам понадобится лицензия работорговца.";
		}
		@Override
		public String getCompletedDescription() {
			return "Helena продала вам Scarlett, что позволило вам приказать ей рассказать, что случилось с Arthur.";
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
			return "Поиски Arthur; Великий побег";
		}
		@Override
		public String getDescription() {
			return "Оказывается, Arthur был продан чрезвычайно опасному демону по имени Zaranix, который живет в Обители демонов. Вам нужно отправиться в Обитель демонов и спасти Arthur!";
		}
		@Override
		public String getCompletedDescription() {
			return "Победив Zaranix, вы спасли Arthur и привели его домой к Lilaya.";
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
			return "Поиски Arthur; Финал";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы спасли Arthur из когтей Zaranix, вам следует вернуться в дом Lilaya и выслушать его подробный рассказ о случившемся.";
		}

		@Override
		public String getCompletedDescription() {
			return "Arthur объяснил, что он баловался запретным искусством заклинаний телепортации. Через одного из своих агентов Zaranix узнал об этом и без труда поработил Arthur за измену. Теперь, когда вы его спасли, он горит желанием отплатить вам тем же, разузнав, как отправить вас обратно домой.";
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
			return "Поиски Arthur; Его собственная комната";
		}
		@Override
		public String getDescription() {
			return "Lilaya очень не хочет видеть Arthur в своей лаборатории, поэтому поручила вам помочь Rose найти для него подходящую комнату.<br/><i>Зайдите в одну из пустых комнат в доме Lilaya и через окно управления комнатами улучшите её до «Комнаты Arthur».</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли подходящую комнату для Arthur и с помощью Rose перевезли значительное количество арканальных инструментов в его новую спальню-лабораторию.";
		}
	},
	
	
	MAIN_2_A_INTO_THE_DEPTHS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "В Submission";
		}
		@Override
		public String getDescription() {
			return "Артур смог объяснить механизм, с помощью которого вы попали в этот новый мир, но, казалось, он придерживал некоторые детали. Он сказал, что объяснит всё подробно, как только будет точно знать, что происходит, но для этого ему нужно будет поговорить с одной из семи старейших лилин. После долгих споров Lilaya согласилась убедить свою мать помочь, но именно вам придется доставить это послание.<br/><i>Спуститесь в нижний город Submission и попробуйте добиться аудиенции у матери Lilaya, Lyssieth.</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "Следуя совету Артура, вы отправились в Submission и обнаружили местонахождение дворца Lyssieth.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false);
		}
	},
	
	MAIN_2_B_SIRENS_CALL(QuestType.MAIN, 25, 300) {
		@Override
		public String getName() {
			return "Зов Сирены";
		}
		@Override
		public String getDescription() {
			return "Стражники у ворот во дворец Lyssieth сказали вам, что в данный момент она не принимает посетителей. Единственный способ добиться аудиенции — это разобраться с её проблемной дочерью, «Темной Сиреной». Сейчас она живет в каменной крепости в одном из центральных туннелей здесь, в Submission, откуда рассылает банды бесов, чтобы терроризировать мирных граждан.</br>Если вам удастся поработить её — силой или хитростью, — вы заслужите аудиенцию у Lyssieth.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось поработить проблемную дочь Lyssieth, и тем самым вы заслужили аудиенцию у неё!";
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
			return "Вернись во дворец Lyssieth и доложи страже, что поработил «Темную Сирену». Этого должно быть достаточно, чтобы тебя допустили на аудиенцию к Lyssieth.";
		}

		@Override
		public String getCompletedDescription() {
			return "За порабощение «Темной Сирены» стражники у ворот во дворец Lyssieth разрешили тебе войти и попытаться добиться аудиенции с ней.";
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
			return "Отправляйся в тронный зал Lyssieth и попытайся наконец получить ответы на вопросы о том, почему ты здесь и как тебе вернуться в свой прежний мир.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lyssieth открыла, что этот мир на самом деле и есть твой собственный, а Lilith превратила его в иную реальность, когда была освобождена из зеркала.";
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
			return "Lyssieth сказала, что для победы над старшей лилин-пегатавром, Lunette, тебе понадобится помощь Minotallys — лилин, правящей городом Элис. Meraxis также упомянул о том, что стоит привлечь на свою сторону ёко, но это может подождать до твоего прибытия в сам Элис.";
		}
		@Override
		public String getCompletedDescription() {
			return "Lyssieth сказала, что для победы над старшей лилин-пегатавром, Lunette, тебе понадобится помощь Minotallys — лилин, правящей городом Элис. С этой целью ты впервые покинул Dominion...";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_B_MEETING_MERAXIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "К Красному Дракону";
		}

		@Override
		public String getDescription() {
			return "Когда вы покидали Dominion, к вам подошла Мераксис и велела встретиться с ней в таверне «Красный дракон» в Элисе, которая, судя по всему, находится недалеко от восточных ворот города. Мераксис также сказала, что по прибытии она организует встречу с Миноталлисом и уже позаботилась о вашем жилье.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы встретились с Мераксис в таверне «Красный дракон», где ей удалось найти вам жилье — арендованную комнату на первом этаже таверны.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_C_MEETING_MINOTALLYS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Встреча с Миноталлисом";
		}

		@Override
		public String getDescription() {
			return "Мераксис договорилась о вашей встрече с Миноталлисом по поводу угрозы, которую представляет Лунетт для города Элис. Скажите Мераксис, что вы готовы к встрече в любое время между [units.time(9)] и [units.time(18)].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы и Мераксис отправились в ратушу Элиса, чтобы встретиться с Миноталлис, где также познакомились с её личным помощником Арионом. Миноталлис пребывает в состоянии отрицания текущего положения дел на полях Фолой и заявила, что станет предпринимать какие-либо действия только в том случае, если под угрозой окажется город Фемискира.";
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
			return "Вы согласились отправиться в Фемискиру вместе с Мераксис, чтобы выяснить, угрожает ли городу армия Лунетт. Когда будете готовы, вам нужно встретиться с Мераксис, чтобы она проводила вас туда.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы отправились в Фемискиру вместе с Мераксис, но, добравшись до города, обнаружили, что его уничтожает армия Лунетт!";
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
			return "Спасти королеву";
		}
		@Override
		public String getDescription() {
			return "Разлучившись с Мераксисом, вы объединились с амазонкой-коровьей девушкой по имени Аврокарис. Вам нужно пройти через Фемискиру и найти как Мераксиса, так и Урсу, королеву амазонок, которая должна находиться во дворце.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вместе с Аврокарис вы прошли через Фемискиру и нашли и Мераксиса, и Урсу на площади перед дворцом. После встречи с Лунексис, предводительницей армии Лунетт, Мераксис телепортировала вас пятерых обратно в ратушу Элиса, где Миноталлиса наконец убедили в том, что Лунетт представляет угрозу для Элиса.";
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
			return "Небольшая помощь от SWORD";
		}
		@Override
		public String getDescription() {
			return "Поскольку Лунетт планирует напасть на Элис в ближайшем будущем, вы пообещали Миноталлису помочь привести городскую оборону в порядок. Вам нужно отправиться на станцию Enforcer в Элисе и попросить помощи у группы оперативников SWORD.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отправились на станцию Enforcer в Элисе и попросили группу оперативников SWORD помочь с приведением городской обороны в порядок.";
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
			return "Оперативники SWORD сказали вам, что для того, чтобы они помогли вам, вы сначала должны помочь им. Вам предстоит отправиться вместе с ними на операцию по поимке дочери старшей лилин, чтобы вы могли вытащить их из передряги, если их узнают. Чтобы начать операцию, вам нужно встретиться с ними на станции Enforcer во вторник вечером, после [units.time(17)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Во вторник днем вы встретились с Enforcer из SWORD, чтобы отправиться на операцию по поимке дочери старшей лилин.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_H_SWORD_MISSION(QuestType.MAIN, 25, 250) {
		@Override
		public String getName() {
			return "Остановить суккуба";
		}
		@Override
		public String getDescription() {
			return "Вам нужно держаться рядом с Enforcer, пока они зачищают штаб-квартиру суккуба. Сражаться не обязательно, но Enforcer будут не против, если вы окажете им некоторую помощь.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы работали вместе с Enforcer, зачищая штаб-квартиру суккуба. Хотя ей и удалось сбежать, вы нанесли решительный удар по её незаконной деятельности по порабощению.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_I_ELIS_DEFENCE_PLAN(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Доложить Миноталису";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда Enforcer заняты подготовкой обороны города, вам нужно вернуться в ратушу, чтобы доложить об этом Миноталису.";
		}
		@Override
		public String getCompletedDescription() {
			return "После встречи в ратуше с Миноталисом, Урсой, Хаммером, Гостом и Мераксис был составлен план обороны Элиса от Лунетт.";
		}
	},
	
	MAIN_3_J_FINDING_TAKAHASHI(QuestType.MAIN, 30, 25) {
		@Override
		public String getName() {
			return "Поиск Такахаши";
		}
		@Override
		public String getDescription() {
			return "Мераксис убеждена, что сможет привлечь на свою сторону ёко с помощью своей подруги-ёко, Такахаши. Скорее всего, Такахаши можно найти в старом убежище Мераксис на высокогорье Шинрин. Когда будете готовы, вам нужно будет поговорить с Мераксис и отправиться на высокогорье Шинрин, чтобы найти её подругу.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили, что секретное убежище Мераксис было занято семихвостой ёко, которая захватила Такахаши и использовала её в качестве служанки. Обнаружив вас, семихвостая ёко сбежала, позволив Мераксис и Такахаши вернуть свое старое убежище.";
		}
	},
	
	MAIN_3_K_WEAPONS_CACHE(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "Заполучить пушки";
		}
		@Override
		public String getDescription() {
			return "[style.italicsBad(На данный момент это всё, что известно о основном квесте! Он будет дополнен в ближайшее время!)]<br/>Пушки, которые Мераксис украл у Lyssieth, находятся во владении девятихвостой ёко. Такахаши знает, где живет эта ёко, так что, когда вы будете готовы, вы втроем сможете отправиться в путь, чтобы вернуть это оружие.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_L_YOUKO_LILIN(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "Ёко-лилин";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда оружие в безопасности, для защиты Элиса не хватает лишь пользователей Арканы, способных отразить заклинания, которые будут использовать дочери Лунетт. Чтобы заставить ёко помочь, вам нужно разыскать двух ёко-лилин и убедить их оказать вам содействие.";
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
			return "С помощью ёко и запасом арканных мушкетов в ваших руках остается только спровоцировать штурм города дочерями Лунетт.";
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
			return "Штурм Элиса начался! Благодаря вашим действиям, а также усилиям ёко и ополчения с мушкетами, дочери Лунетт были отбиты, но со старшей лилин всё еще нужно разобраться. Вам, Мераксису и Миноталису предстоит победить её в форме кентавра.";
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
			return "Вам удалось победить Лунетт, но когда её тело кентавра рухнуло на землю, она превратилась в свою истинную демоническую форму. Вам нужно задержать её до тех пор, пока Lyssieth не прибудет и не усмирит свою сестру.";
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
			return "В этом новом мире полно любопытных предметов. Интересно, удастся ли тебе найти их все...";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты нашел все существующие предметы!";
		}
	},

	SIDE_DISCOVER_ALL_RACES(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Перфекционист";
		}

		@Override
		public String getDescription() {
			return "В этом мире, кажется, обитает множество странных новых рас. Интересно, удастся ли тебе открыть их все...";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты открыл все существующие расы!";
		}
	},
	
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Попросить Lilaya о помощи";
		}

		@Override
		public String getDescription() {
			return "Недавно ты почувствовал, как в твое тело проникла странная сила, и хотя очевидных последствий пока нет, тебе стоит провериться. Lilaya наверняка знает больше, так что, возможно, тебе стоит пойти и поговорить с ней об этом.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya сообщила тебе, что ты способен собирать «эссенции» из чужой арканной ауры. Она выглядела немного обеспокоенной твоей способностью делать это, так как, судя по всему, обычно собирать эссенции подобным образом могут только лилин...";
		}
	},

	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya знает лучше всех";
		}

		@Override
		public String getDescription() {
			return "Не может быть... Ты беременна? Ты <b>беременна</b>! Наверняка Lilaya знает, что делать?!";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya сумела успокоить тебя и заверила, что беременность в этом мире — не такая уж большая проблема, как у тебя дома.";
		}
	},
	
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(QuestType.SIDE, 1, 20) {
		@Override
		public String getName() {
			return "Lilaya повитуха";
		}

		@Override
		public String getDescription() {
			return "Lilaya сказала, что сможет помочь тебе с родами, когда ты будешь готова. Тебе нужно подождать, пока твой живот окончательно вырастет, а затем можно будет идти к Lilaya, чтобы родить.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya помогла тебе родить. Она сказала, что если ты когда-нибудь снова забеременеешь, она всегда сможет помочь.";
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
			return "В этом нет никаких сомнений: в твое тело вложили кладку яиц! Тебе остается только ждать, пока они созреют, а затем отложить их...";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты дождалась, пока имплантированные в твое тело яйца созреют, а затем успешно отложила и вылупила их!";
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
			return "Узнав в здании Администрации Рабства, как получить лицензию работорговца, ты обнаружила, что сначала тебе понадобится рекомендательное письмо. Lilaya должна помочь с этим.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya дала тебе рекомендательное письмо, и более того, она предложила разрешить тебе поселить своих рабов в её особняке.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
		}
	},
	
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Предъявить письмо";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда ты получил рекомендательное письмо от Lilaya, тебе следует вернуться в здание Администрации Рабства в Переулке Работорговцев и предъявить его [finch.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты предъявил рекомендательное письмо [finch.name], и после оплаты сбора ты получил лицензию работорговца!";
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
			return "Хозяйка Lilaya";
		}

		@Override
		public String getDescription() {
			return "Особняк Lilaya полон пустых комнат, которые можно использовать для размещения гостей. Тебе стоит спросить её, можешь ли ты использовать их, чтобы поселить там своих друзей и семью.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya разрешила тебе использовать пустые комнаты для размещения твоих друзей и семьи при условии, что ты будешь оплачивать все сопутствующие расходы.";
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
			return "Хотя особняк Lilaya полон пустых комнат, в которых можно хранить кукол, тебе лучше сначала спросить её разрешения, прежде чем приносить их домой...";
		}
		@Override
		public String getCompletedDescription() {
			return "Lilaya разрешила тебе использовать пустые комнаты для хранения любых купленных тобой кукол.";
		}
	},
	
	// Other:
	
	SIDE_HYPNO_WATCH_VICKY(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Заказ в Arcane Arts";
		}

		@Override
		public String getDescription() {
			return "Артур сообщил вам, что Зараникс поручил ему найти арканический способ изменения сексуальной ориентации человека. Хотя он заверил вас, что сам не намерен когда-либо использовать подобный предмет, Артур выразил интерес к завершению своего исследования и попросил вас забрать специальный заказ в магазине 'Arcane Arts' в Торговой галерее.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы забрали посылку в Arcane Arts и принесли её Артуру.";
		}
	},
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Испытуемый";
		}

		@Override
		public String getDescription() {
			return "После того как Lilaya последовала инструкциям Артура и зачаровала часы, она спросила, можно ли испытать их на вас...";
//					+ " You could either offer yourself, or, if you own any slaves, offer one of those to Arthur instead.";
		}

		@Override
		public String getCompletedDescription() {
			return "Гипно-часы, похоже, сработали, хотя Lilaya остановила тест до того, как эффект стал необратимым. Она предупредила, что они окажут сильное развращающее воздействие на разум любого, на кого нацелены, и для верности развеяла чары, прежде чем передать их вам.";
		}
	},
	
	
	LIGHTNING_SPELL_1_PAYMENT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Цена власти";
		}
		@Override
		public String getDescription() {
			return "После того как вы показали Arthur арканный шар молний, найденный на складе Enforcer, вам сказали, что станет возможным постичь секреты заключенных в нем заклинаний молнии. Хотя полученные заклинания будут мощнее тех, на что способен шар, Arthur объяснил, что такая экстракция потребует не только огромного количества арканных эссенций, но и навсегда лишит шар его врожденных заклинаний.<br/>Когда вы будете готовы и сможете это сделать, отдайте Arthur арканный шар молний и позвольте ему извлечь 500 арканных эссенций из вашей ауры.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отдали Arthur арканный шар молний, найденный на складе Enforcer, и позволили ему извлечь 500 арканных эссенций из вашей ауры. Взамен вам пообещали, что вскоре вы обретете мощное арканное заклинание молнии.";
		}
	},
	
	LIGHTNING_SPELL_2_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Экстракция молнии";
		}
		@Override
		public String getDescription() {
			return "Arthur сообщил вам, что извлечение секретов арканной молнии из шара займет некоторое время. Вам придется вернуться к нему через две недели, чтобы узнать результаты его исследований...";
		}
		@Override
		public String getCompletedDescription() {
			return "Arthur с восторгом сообщил вам, что ему удалось не только извлечь из шара секреты двух арканных заклинаний молнии, но и перенести оставшуюся мощь в кристалл поменьше, который он уже успел вставить в кольцо специально для вас.";
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
			return "Enforcer сообщил вам, что гнезда гарпий сейчас чрезвычайно опасны. В ходе расспросов вы выяснили, что того, кто сумеет утихомирить трех главных матриархов, ждет щедрая награда.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам удалось взять под контроль одно из гнезд гарпий!";
		}
	},
	HARPY_PACIFICATION_TWO(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Одно готово, осталось два";
		}

		@Override
		public String getDescription() {
			return "Вам удалось подчинить одну из матриархов, но впереди еще две!";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам удалось взять под контроль два гнезда гарпий!";
		}
	},
	HARPY_PACIFICATION_THREE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Осталась одна матриарх";
		}

		@Override
		public String getDescription() {
			return "Вам удалось подчинить двух матриархов, но впереди еще одна!";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам удалось взять под контроль все три главных гнезда гарпий!";
		}
	},
	HARPY_PACIFICATION_REWARD(QuestType.SIDE, 6, 50) {
		@Override
		public String getName() {
			return "Гарпия "+(Main.game.getPlayer().isFeminine()?"queen":"king");
		}

		@Override
		public String getDescription() {
			return "Вернитесь к посту Enforcer, чтобы доложить об успехе.";
		}

		@Override
		public String getCompletedDescription() {
			return "После того как вы сообщили Enforcers, что усмирили все три главных гнезда гарпий, они возобновили свое регулярное патрулирование, благодаря чему по гнездам гарпий теперь можно безопасно путешествовать!";
		}
	},
	
	
	
	// Slime Queen:
	
	SLIME_QUEEN_ONE(QuestType.SIDE, 10, 25) {
		@Override
		public String getName() {
			return "Проблемные Slime";
		}

		@Override
		public String getDescription() {
			return "Когда вы только прибыли в Submission, Enforcer по имени Claire сообщила вам о текущей ситуации в туннелях. Судя по всему, становится всё больше Slime, которые нападают на невинных путешественников и превращают их в новых Slime. Если вы сможете предоставить какую-либо информацию о том, откуда берутся эти агрессивные Slime, вы сможете заработать награду в пять тысяч огней.<br/><p style='text-align:center;'><i>Чтобы узнать больше, вам нужно будет победить слайма в <b>туннелях Submission</b>.</i></p>";
		}

		@Override
		public String getCompletedDescription() {
			return "Один из Slime, встреченных вами в туннелях, сказал, что некая «Королева Slime» отдала им приказ превращать людей.";
		}
	},
	
	SLIME_QUEEN_TWO(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Доложить";
		}

		@Override
		public String getDescription() {
			return "Вам следует отправиться к одному из аванпостов Enforcer в Submission и сообщить им информацию о «Королеве Slime».";
		}

		@Override
		public String getCompletedDescription() {
			return "Enforcer, которому вы доложили, сказал, что и раньше слышал слухи о Королеве Slime, но в Submission никогда не находили никаких следов подобного. Вам предложили заглянуть в Пещеры Летучих Мышей и пообещали еще двадцать тысяч огней, если вы сможете найти эту Королеву и положить конец её интригам.";
		}
	},
	
	SLIME_QUEEN_THREE(QuestType.SIDE, 15, 25) {
		@Override
		public String getName() {
			return "Поиск Королевы Slime";
		}

		@Override
		public String getDescription() {
			return "Спуститесь в Пещеры Летучих Мышей и разыщите легендарную Королеву Slime.";
		}

		@Override
		public String getCompletedDescription() {
			return "Посреди Озера Слаймов вы обнаружили логово Королевы Слаймов!";
		}
	},
	
	SLIME_QUEEN_FOUR(QuestType.SIDE, 20, 50) {
		@Override
		public String getName() {
			return "Противостоять Королеве";
		}

		@Override
		public String getDescription() {
			return "Подняться на башню и найти Королеву Слаймов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы нашли Королеву Слаймов на вершине башни.";
		}
	},
	
	SLIME_QUEEN_FIVE_SUBMIT(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Помочь Королеве";
		}

		@Override
		public String getDescription() {
			return "Вы решили помочь Королеве Слаймов в её планах по превращению населения Submission в слаймов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы решили помочь Королеве Слаймов в её планах и согласились обмануть Enforcers, заставив их поверить, что она больше не представляет угрозы!";
		}
	},
	
	SLIME_QUEEN_SIX_SUBMIT(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Финальный отчет";
		}

		@Override
		public String getDescription() {
			return "Вернуться к Клэр и сообщить ей, что Королева Слаймов больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали Клэр, что Королева Слаймов больше не представляет угрозы, и получили награду в двадцать тысяч огней. Теперь, когда ваша Королева в безопасности от расследования Enforcers, лишь вопрос времени, когда всё Submission превратится в слаймовый рай!";
		}
	},
	
	SLIME_QUEEN_FIVE_CONVINCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Убедить Королеву";
		}

		@Override
		public String getDescription() {
			return "Вы решаете убедить Королеву слаймов отказаться от своих планов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы убедили Кэтрин отказаться от плана превратить всех жителей Submission в слаймов.";
		}
	},
	
	SLIME_QUEEN_SIX_CONVINCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Финальный отчет";
		}

		@Override
		public String getDescription() {
			return "Вернуться к Клэр и сообщить ей, что Королева Слаймов больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сообщили Клэр, что Королева слаймов больше не представляет угрозы, и получили награду в двадцать тысяч огней.";
		}
	},
	
	SLIME_QUEEN_FIVE_FORCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Принудить Королеву";
		}

		@Override
		public String getDescription() {
			return "Принудить Королеву слаймов отказаться от своих планов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы заставили Кэтрин отказаться от плана превратить всех жителей Submission в слаймов.";
		}
	},
	
	SLIME_QUEEN_SIX_FORCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Финальный отчет";
		}

		@Override
		public String getDescription() {
			return "Вернуться к Клэр и сообщить ей, что Королева Слаймов больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сообщили Клэр, что Королева слаймов больше не представляет угрозы, и получили награду в двадцать тысяч огней.";
		}
	},
	
	
	// Teleporting:
	
	TELEPORTING_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Побег со склада";
		}

		@Override
		public String getDescription() {
			return "Случайно телепортировавшись на склад подразделения Enforcer 'SWORD', вы с Клэр должны теперь избежать обнаружения и успешно сбежать.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам с Клэр удалось сбежать со склада SWORD.";
		}
	},

	TELEPORTING_CAUGHT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Вытерпеть колодки";
		}

		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Потерпев поражение от Enforcer на складе SWORD, вы были приговорены к заточению в общественных колодках на Рабской аллее. Переживите это испытание, пока Клэр не придет на помощь...";
			} else {
				return "Потерпев поражение от Enforcer на складе SWORD, вы оказались заперты в камерах в штаб-квартире Enforcer. Вам придется ждать, пока Клэр не придет на помощь...";
			}
		}

		@Override
		public String getCompletedDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Потерпев поражение от Enforcer на складе SWORD, вам пришлось несколько часов провести в общественных колодках на Рабской аллее, прежде чем появилась Клэр и освободила вас.";
			} else {
				return "Потерпев поражение от Enforcer на складе SWORD, вам пришлось пару часов провести в камерах в штаб-квартире Enforcer, прежде чем появилась Клэр и освободила вас.";
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
			return "Роуз попросила вас навестить [daddy.name], чтобы убедить [daddy.herHim] оставить Lilaya в покое. ([daddy.He] доступен только в квартире [daddy.his] в Демоническом доме между "+Units.time(LocalTime.of(18, 00))+" and "+Units.time(LocalTime.of(21, 00))+".)";
		}

		@Override
		public String getCompletedDescription() {
			return "По просьбе Роуз вы встретились с [daddy.name] в квартире [daddy.her] в Демоническом доме.";
		}
	},
	
	DADDY_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Ужин с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "[daddy.Name] настойчиво хочет объяснить свои мотивы [daddy.her] за ужином. Вам придется либо принять предложение [daddy.her], либо прямо отказать [daddy.herHim] и настоять на том, чтобы [daddy.she] оставила Lilaya в покое.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы дали [daddy.name] свой ответ на [daddy.her] просьбу пригласить вас на ужин.";
		}
	},
	
	DADDY_REFUSED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] отказалась";
		}

		@Override
		public String getDescription() {
			return "Вы сказали [daddy.name], что вам совершенно не интересно идти на ужин с [daddy.herHim], и что [daddy.sheIs] больше никогда не должна беспокоить Lilaya.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали [daddy.name], что вам совершенно не интересно идти на ужин с [daddy.herHim], и что [daddy.sheIs] больше никогда не должна беспокоить Lilaya.";
		}
	},
	
	DADDY_REFUSED_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] отказалась";
		}

		@Override
		public String getDescription() {
			return "Вы сказали [daddy.name], что вам не интересно убеждать Lilaya встретиться с [daddy.herHim], и что [daddy.she] больше никогда не должна беспокоить вашу [lilaya.relation(pc)].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали [daddy.name], что вам не интересно убеждать Lilaya встретиться с [daddy.herHim], и что [daddy.she] больше никогда не должна беспокоить вашу [lilaya.relation(pc)].";
		}
	},
	
	DADDY_ACCEPTED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Убедить Lilaya";
		}

		@Override
		public String getDescription() {
			return "Вы согласились убедить Lilaya встретиться с [daddy.name] за ужином, а затем помочь склонить её к тому, чтобы она попросила Lyssieth встретиться с [daddy.herHim].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы убедили Lilaya поужинать с [daddy.name], при условии, что вы пойдете вместе с ней. ";
		}
	},
	
	DADDY_LILAYA_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Свидание Lilaya с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Lilaya согласилась пойти на ужин с [daddy.name], так что теперь вам нужно просто сопровождать её и следить за тем, чтобы вечер прошел гладко.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы отправились с Lilaya на ужин к [daddy.name], и хотя у неё были плохие новости для [daddy.herHim] касательно романтических предпочтений Lyssieth, кажется, [daddy.herHim] ей вполне приглянулся...";
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
			return "Кэнди сказала, что подумает о продаже [brax.name] вам, но прежде чем дать окончательный ответ, она хочет, чтобы вы сходили и забрали её заказ духов в лавке «Секреты Суккуба» в торговой галерее.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы оба оплатили и забрали заказ Кэнди из лавки «Секреты Суккуба».";
		}
	},
	
	BUYING_BRAX_DELIVER_PERFUME(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Доставка духов";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы забрали флаконы с духами, вам нужно доставить их Кэнди в штаб-квартиру Enforcer.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы доставили Кэнди флаконы с духами.";
		}
	},
	
	BUYING_BRAX_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Контрабандные леденцы";
		}

		@Override
		public String getDescription() {
			return "Кэнди сказала, что готова продать тебе Brax, но ей нужно подумать, сколько [brax.sheIs] он стоит. Она пообещала назвать цену после того, как ты добудешь коробку контрабандных леденцов на контрольно-пропускном пункте Enforcer в Гнездах Гарпий.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы забрали леденцы на контрольно-пропускном пункте Enforcer в Гнездах Гарпий.";
		}
	},
	
	BUYING_BRAX_DELIVER_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Леденцы для Кэнди";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда коробка контрабандных леденцов у тебя, тебе нужно вернуть её Кэнди в штаб-квартиру Enforcer.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы доставили коробку контрабандных леденцов Кэнди, которая, казалось, совсем не обратила внимания на штампы с предупреждениями, которыми та была усыпана.";
		}
	},
	
	BUYING_BRAX_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Вес волка в помаде";
		}

		@Override
		public String getDescription() {
			return "Кэнди сказала, что Brax слишком драгоценен, чтобы продавать его за жалкие огни, но она была бы готова отдать [brax.herHim] в обмен на нечто столь же ценное — коробку лимитированной серии помад под брендом «Сотня поцелуев». Судя по всему, Кэнди разузнала, где находится одна из немногих оставшихся в продаже коробок — она лежит в магазине в торговой аркаде под названием «Закуски Ральфа».";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы забрали коробку «Сотни поцелуев» у Ralph.";
		}
	},
	
	BUYING_BRAX_DELIVER_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "У порога волка";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда коробка «Ста поцелуев» у тебя в руках, осталось только доставить её Кэнди в обмен на право владения [brax.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты вручил Кэнди коробку «Ста поцелуев» и наконец получил свой приз — право владения [brax.name].";
		}
	},

	
	// Vengar:
	
	VENGAR_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Крысиные норы";
		}
		@Override
		public String getDescription() {
			return "Ты согласился помочь Акселю разобраться с Венгаром, который, судя по всему, возглавляет самую крупную и опасную банду в Submission. Ты можешь отправиться прямиком в его логово, «Крысиные норы», или сначала пойти и попросить помощи у Клэр.";
		}
		@Override
		public String getCompletedDescription() {
			return "Используя пароль, данный тебе Акселем, ты смог проникнуть в логово Венгара — Крысиные норы.";
		}
	},
	
	VENGAR_ONE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встреча с Венгаром";
		}
		@Override
		public String getDescription() {
			return "Чтобы найти Венгара, тебе нужно отыскать главный зал и прийти туда в промежутке между "+Units.time(LocalDateTime.of(1, 1, 1, 6, 0))+" and "+Units.time(LocalDateTime.of(1, 1, 1, 22, 0))+".";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты нашел Венгара, восседающего на троне в главном зале. После того как ты подошел к нему, тебе представился выбор: либо вступить в его банду, либо сразиться с его телохранительницами — крысиными девчонками.";
		}
	},
	
	VENGAR_TWO_CONFLICT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конфликт";
		}
		@Override
		public String getDescription() {
			return "Выбрав путь открытого противостояния с Венгаром, теперь тебе нужно одолеть его в бою, чтобы утвердить свое господство над его бандой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось победить Венгара, но прежде чем вы успели предпринять какие-либо дальнейшие действия, SWORD начали свой рейд на Крысиные Норы.";
		}
	},
	
	VENGAR_TWO_COOPERATION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Покорность Акселя";
		}
		@Override
		public String getDescription() {
			return "Венгар признался, что уже некоторое время хочет сосредоточиться на своем законном бизнесе по производству рома, но он не может просто оставить Акселя, не потеряв уважения своей банды. В обмен на прекращение вымогательства вы согласились убедить Акселя прийти и продемонстрировать свою покорность Венгару.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось убедить Акселя отправиться в Крысиные Норы и продемонстрировать свою покорность Венгару. Сопровождая его, вы смогли повлиять на то, что случилось с мальчишкой-аллигатором.";
		}
	},
	
	VENGAR_TWO_ENFORCERS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Рейд";
		}
		@Override
		public String getDescription() {
			return "Подтвердив, что Венгар находится в Крысиных Норах, вы активировали резонансный камень, подав сигнал ждущим Enforcer из SWORD начать рейд.";
		}
		@Override
		public String getCompletedDescription() {
			return "Enforcer из SWORD успешно провели рейд на Крысиные Норы и смогли схватить Венгара.";
		}
	},
	
	VENGAR_THREE_COOPERATION_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конец Венгара";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда [axel.name] продемонстрировал(а) [axel.her] покорность Венгару, остается только вернуться в Игорный Дом...";
		}
		@Override
		public String getCompletedDescription() {
			return "После того как [axel.name] продемонстрировал(а) [axel.her] покорность Венгару, группа Enforcer из SWORD явилась, чтобы совершить рейд на Крысиные Норы и арестовать мальчишку-крысу!";
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
			return "Вы вернулись к Акселю и рассказали ему, каким будет положение дел в дальнейшем.";
		}
	},
	
	VENGAR_OPTIONAL_CLAIRE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Помощь Клэр";
		}
		@Override
		public String getDescription() {
			return "Решив, что лучше всего будет сообщить о ситуации Клэр, вы спросили её, может ли что-то сделать Enforcer, чтобы помочь. Судя по всему, отряд SWORD уже готов нагрянуть в Крысиные Норы, но перед началом штурма им нужно убедиться, что Венгар внутри. Клэр дала вам резонансный камень, который можно активировать, если вы захотите, чтобы они пришли вам на помощь, когда вы окажетесь внутри.";
		}
		@Override
		public String getCompletedDescription() {
			return "Решив, что лучше всего будет сообщить о ситуации Клэр, вы спросили её, может ли что-то сделать Enforcer, чтобы помочь. Судя по всему, отряд SWORD уже готов нагрянуть в Крысиные Норы, но перед началом штурма им нужно убедиться, что Венгар внутри. Клэр дала вам резонансный камень, который можно активировать, если вы захотите, чтобы они пришли вам на помощь, когда вы окажетесь внутри.";
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
			return "После того как вы сказали Уэсли, что не намерены помогать ему в расследовании, лисомальчик исчез, и вы можете быть уверены, что он больше никогда не попытается просить вашей помощи...";
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
			return "Во время путешествия по Dominion к вам подошел таинственный внедренный Enforcer из SWORD и попросил о помощи. Он хочет, чтобы вы встретили его возле антикварной лавки в Торговых рядах между [units.time(13)] и [units.time(14)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы согласились помочь Уэсли расследовать дела его вышестоящего офицера.";
		}
	},

	WES_1(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Поиск Элль";
		}
		@Override
		public String getDescription() {
			return "Уэс рассказал вам, что его начальник, [elle.name] (или просто «Элль»), берет с собой очки ночного видения, когда уходит на подозрительно долгие обеды по средам, и всегда возвращается с мокрой обувью. Вам нужно найти её и записать любые улики на аркана-устройство записи, которое дал вам Уэс...<br/>Она устраивает [style.colourOrange(необычно долгие обеденные перерывы)].<br/>Она делает это только по [style.colourOrange(средам)].<br/>Она берет с собой [style.colourOrange(очки ночного видения)].<br/>После этого её [style.colourOrange(обувь часто мокрая)], а иногда на ней можно заметить следы какого-то [style.colourOrange(светящегося налета)].<br/>[style.italicsMinorGood(Чтобы получить подсказку, куда идти, спросите об этом Lilaya.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Вычислив, что Элль ведет свои теневые дела в Пещерах Летучих Мышей, вам удалось собрать доказательства того, что она продает оружие опасной преступной группировке.";
		}
	},

	WES_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Использование улик";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда ваше арканное записывающее устройство содержит неопровержимые улики порочности Элль, у вас есть два варианта действий. Вы могли бы передать их в виде анонимного донесения Клэр или Кэнди, либо, если вы захотите предать Уэса и встать на сторону Элль, вы могли бы подкараулить [elle.race] у штаб-квартиры Enforcer между [units.time(16)] и [units.time(18)], чтобы раскрыть ей всю правду, когда она будет уходить с работы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы использовали собранные улики, чтобы положить конец всей этой истории с Уэсом и Элль.";
		}
	},

	WES_3_WES(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встать на сторону Уэса";
		}
		@Override
		public String getDescription() {
			return "Вы решили поступить так, как просил Уэс, и передали изобличающие улики в виде анонимного донесения Enforcers. Вам стоит подождать как минимум одну неделю, пока всё уляжется, а затем попросить о встрече с Уэсом в штаб-квартире Enforcer в период между [units.time(9)] и [units.time(17)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встали на сторону Уэса и, анонимно передав улики, встретились с лисомальчиком в штаб-квартире Enforcer, чтобы узнать, что его повысили на должность Элль. Благодаря своей новой власти он предоставил вам доступ в зону снабжения штаб-квартиры Enforcer в награду за помощь. Теперь вы также можете свободно навещать Уэса в штаб-квартире Enforcer в часы между [units.time(9)] и [units.time(17)].";
		}
	},

	WES_3_ELLE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встать на сторону Элль";
		}
		@Override
		public String getDescription() {
			return "Вы решили предать Веса и рассказать всё Элль. Благодарная за вашу неожиданную поддержку, [elle.race] пообещала наградить вас, если вы вернетесь в штаб Enforcer как минимум через неделю...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встали на сторону Элль, и после возвращения на встречу с ней в штаб Enforcer вы обнаружили, что Вес был порабощен. Благодарная за вашу поддержку, [elle.race] предоставила вам доступ к зоне снабжения штаба Enforcer. Теперь вы также можете свободно навещать Элль (и Веса) в штабе Enforcer в часы с [units.time(9)] по [units.time(17)].";
		}
	},

	
	// Rebel Base for HLF Quest
	
	REBEL_BASE_HANDLE_REFUSED(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Дерни за рычаг!";
		}
		@Override
		public String getDescription() {
			return "Вы нашли странную рукоятку в Пещерах Летучих Мышей. Кто знает, для чего она нужна и что делает.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вопреки здравому смыслу, вы потянули за рукоятку.";
		}
	},

	REBEL_BASE_PASSWORD_PART_ONE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Потянуть за рукоятку, получить тайну";
		}
		@Override
		public String getDescription() {
			return "Странная рукоятка запросила какой-то пароль, которого у вас нет. Возможно, небольшие поиски поблизости помогут найти зацепки.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли половину страницы из Журнала, в которой упоминалось, что пароль состоит из двух слов. Вам удалось расшифровать только одно слово, второе было оторвано.";
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
			return "Вторая половина пароля должна быть на другой половине страницы Журнала. Возможно, её всё еще можно найти неподалеку.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли обе половины пароля, вместе они образуют фразу «RUAT CAELUM»";
		}
	},

	REBEL_BASE_PASSWORD_COMPLETE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Откройся, о чудо!";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда пароль собран, вы можете еще раз попытаться разгадать загадку ручки.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили, что ручка на самом деле была прикреплена к двери, ведущей в скрытую пещеру, ответвляющуюся от Летучих пещер.";
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
			return "Неизвестно, для чего нужна эта скрытая пещера и куда она ведет. Возможно, внутри вы найдете ответы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы выяснили, что скрытая пещера служила убежищем для давно исчезнувшей группы повстанцев. Судя по всему, они не победили.";
		}
	},

	REBEL_BASE_ESCAPE(QuestType.SIDE,
			15,
			100) {
		@Override
		public String getName() {
			return "Уйти незамеченным";
		}
		@Override
		public String getDescription() {
			return "Пора выбираться, желательно до того, как эта пещера обрушится.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось выбраться целым и невредимым. Все остальное, что было в той пещере, теперь погребено навсегда.";
		}
	},

	REBEL_BASE_FAILED(QuestType.SIDE,
			15,
			0) {
		@Override
		public String getName() {
			return "Дрожащие коленки";
		}
		@Override
		public String getDescription() {
			return "Тебе удалось выбраться оттуда целым и невредимым, но какие бы секреты ни хранила та пещера, теперь они погребены навечно.";
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
			return "Разбор бомб";
		}
		@Override
		public String getDescription() {
			return "Огненные бомбы, которые ты добыл в таинственной пещере, могут пригодиться в бою. Тебе нужно найти кого-нибудь, кто сможет изготовить или раздобыть еще.";
		}
		@Override
		public String getCompletedDescription() {
			return "Рокси согласилась разузнать, как достать тебе еще огненных бомб.";
		}
	},

	REBEL_BASE_FIREBOMBS_FINISH(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Огонь из крысиных нор";
		}
		@Override
		public String getDescription() {
			return "Рокси понадобится два дня, чтобы наладить поставку новых огненных бомб. Тебе стоит вернуться к ней после этого срока.";
		}
		@Override
		public String getCompletedDescription() {
			return "Каким-то образом Рокси тебя не обманула, и ты заполучил запас огненных бомб.";
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
			return "Не имея перед глазами образца, Рокси либо не смогла понять, как их делать, либо просто не захотела утруждать себя попытками воссоздать те огненные бомбы, что ты нашел...";
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
			return "Вы узнали, что именно нужно Эйзеку, чтобы привести в порядок свою лавку, а также что он хотел бы видеть на новой вывеске. Теперь вам осталось лишь собрать кое-какие материалы у торговцев в городе, чтобы устроить ему приятный сюрприз. Может, найдется кто-то, торгующий тканями?";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы заказали у Моники новую вывеску и несколько рулонов ткани.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Нужен навес";
		}
		@Override
		public String getDescription() {
			return "Пока вы ждете, когда Моника подготовит ваш заказ, вам нужно найти деревянные шесты для навеса. Возможно, местный кузнец сможет как-то модифицировать древко от какого-нибудь древкового оружия?";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы заказали у Имсу и Хейла модифицированные топорища.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Все налаживается";
		}
		@Override
		public String getDescription() {
			return "Вам стоит заглянуть к Хейлу через день, а к Монике — через три дня, чтобы проверить, готов ли ваш заказ.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы собрали все необходимые материалы.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_FOUR(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Сборка всего воедино";
		}
		@Override
		public String getDescription() {
			return "У вас есть всё необходимое, чтобы улучшить лавку Эйзека. Расскажите ему об этом при следующей встрече.";
		}
		@Override
		public String getCompletedDescription() {
			return "Насколько вы могли судить, Эйзек был вне себя от радости из-за того, что вы для него сделали, а его лавка теперь выглядит лучше, чем когда-либо.";
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
			return "Эйзек объяснил, почему за ним охотится толпа, но он мало что о них знает. Если вы хотите убедиться, что они не вернутся, вам придется найти их и дать им отпор.<br/>Поскольку они, похоже, состоят из местных жителей, возможно, поиски по городу помогут сдвинуть дело с мертвой точки.";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря капле удачи и яркому красочному плакату, который выставила толпа, вы нашли и вошли в их место сбора.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO(QuestType.SIDE,
			10,
			100) {
		@Override
		public String getName() {
			return "Оставьте дракона в покое!";
		}
		@Override
		public String getDescription() {
			return "Вы нашли место, где собирается толпа. Пора с ними разобраться!";
		}
		@Override
		public String getCompletedDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate"))) {
			    return "Вы решили попытаться убедить толпу оставить Эйзека в покое, используя свое внушительное телосложение.";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate_arcane"))) {
			    return "Вы решили попытаться убедить толпу оставить Эйзека в покое, используя свое мастерство арканы.";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_persuade"))) {
			    if(!Main.game.isSillyModeEnabled()) {
			    	return "Вы убедили толпу оставить Эйзека в покое с помощью проникновенной речи.";
			    } else {
			    	return "Вы разгромили аргументы толпы с помощью ФАКТОВ и ЛОГИКИ.";
			    }
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_seduce"))) {
			    return "Вы убедили толпу оставить Эйзека в покое, использовав свое мастерство магии похоти, чтобы устроить оргию.";
			} else {
			    return "Вам не удалось убедить толпу оставить Эйзека в покое.";
			}
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "Выброшено";
		}
		@Override
		public String getDescription() {
			return "Вам не удалось убедить толпу оставить Эйзека в покое. Теперь, когда вы не справились с толпой, вам стоит вернуться к нему с плохими новостями.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам не удалось убедить толпу оставить Эйзека в покое.";
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
			return "Хотя он и пытался это скрыть, Эйзеку казалось, что он расстроен тем, что толпа все еще где-то там замышляет против него заговор.";
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
			return "Теперь, когда вы разобрались с толпой, вам стоит вернуться к Эйзеку с хорошими новостями.";
		}
		@Override
		public String getCompletedDescription() {
			return "Хотя он и пытался это скрыть, Эйсек выглядел довольно счастливым от того, что толпа теперь оставит его в покое. К тому же ты раздобыл немного редкого драконьего фрукта.";
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
			return "Ты наткнулся на иную разновидность мобов, которые были странным образом одержимы Эйсеком. В конечном счете это ни к чему не привело, но ты решил разузнать, что это за странные люди такие.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты вошел в какой-то подвал, где они собрались.";
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
			return "Ты проследил за обитателями подвала до их логова и решил заглянуть внутрь. К сожалению, они не слишком оценили твое вторжение и перекрыли путь, которым ты пришел.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты почти у выхода, осталось преодолеть лишь одно препятствие...";
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
			return "Победив лидера этой странной группы, тебе остается только выбраться отсюда.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты успешно сбежал из подземелья, показав кучке задротов, кто здесь настоящий босс.";
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
			return "Согласившись помочь Огликс расширить её выбор пивных сук, вы получили задание отправиться в таверну «Меч кентавра» и попросить владельца, Хейрона, выделить любые лишние бочки, которые у него могут быть. Если он откажется помогать, Огликс велела сказать кентавру: «Огликс говорит, будь послушной лошадкой».";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря особой фразе «Огликс говорит, будь послушной лошадкой», вам удалось убедить Хейрона отправить четыре запасные бочки в таверну Огликс.";
		}
	},
	
	OGLIX_BEER_BARRELS_2(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Отчёт послушной лошадки";
		}
		@Override
		public String getDescription() {
			return "Получив помощь от Хейрона, теперь вам нужно вернуться к Огликс и сообщить ей, что Хейрон решил стать «послушной лошадкой» для Огликс.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись к Огликс и сообщили ей об успехе. Теперь, когда у неё есть четыре дополнительные бочки, в которых можно запирать новых пивных сук, Огликс попросила вас поставлять многообещающих кандидаток из числа преступного населения близлежащих переулков. Кроме того, она велела вам прокрасться к задней части её таверны между [units.time(6)] и [units.time(7)], если вы хотите узнать, что на самом деле означает фраза «Огликс говорит, будь послушной лошадкой»...";
		}
	},

	
	// Helping Lunexis to escape:
	
	LUNEXIS_ESCAPE(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Свободная Лунексис";
		}
		@Override
		public String getDescription() {
			return "Сдавшись под власть Лунексис и поклявшись быть её послушным членом-футляром, вы получили приказ от новой Госпожи помочь ей сбежать из плена. Желая отомстить тому, кто телепортировал её на Элис, кентаврисса разработала план: вы должны убедить Мераксис телепортировать вас троих обратно в Фемисциру. Там ваша Госпожа вознаградит вас, сделав одним из своих личных рабов-членников...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы убедили Мераксис телепортировать её саму, а также вас и Лунексис, обратно в Фемисциру. Оказавшись на месте, вы раскрыли свой обман, и хотя Мераксис пыталась сопротивляться, вскоре она была покорена и использована вашей Госпожой, чтобы вернуть колеблющуюся верность её армии кентаврисс.";
		}
	},

	LUNEXIS_ESCAPE_FAILED(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Лунексис предана";
		}
		@Override
		public String getDescription() {
			return "Решив открыть Мераксис всю правду, вы предали Лунексис, после чего вам было запрещено любое дальнейшее общение с этой демонической кентавриссой.";
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
			return "Исследовать «Роскошь Ловьенн\"";
		}
		@Override
		public String getDescription() {
			return "Дневник Анжеликса показал, что похищенных беженцев телепортировали в магазин «Роскошь Ловьенн» в Dominion. Чтобы узнать, что случилось с жертвами Анжеликса, вам нужно будет обыскать этот магазин...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отправились в магазин «Роскошь Ловьенн» и попытались выяснить, привозят ли туда беженцев, похищенных Анжеликсом. Хотя вам не удалось ничего обнаружить, когда вы уходили, к вам подошла репортер по имени Фиамметта и предложила свою помощь.";
		}
	},
	
	DOLL_FACTORY_2(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Взлом и проникновение";
		}
		@Override
		public String getDescription() {
			return "Фиамметта знает путь к заднему входу в «Роскошь Ловьенны» — там, как она считает, держат похищенных беженцев, используя их как рабскую силу. Поскольку другого способа проникнуть внутрь и докопаться до сути происходящего нет, вы согласились на её план и пообещали встретиться у магазина между [units.time(1)] и [units.time(4)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встретились с Фиамметтой у «Роскоши Ловьенны» и сумели пробраться через задний вход, не подняв тревогу.";
		}
	},
	
	DOLL_FACTORY_3(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Докопаться до сути";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда вам удалось проникнуть в заднюю часть «Роскоши Ловьенны», вам нужно провести расследование и выяснить, где находятся похищенные беженцы.";
		}
		@Override
		public String getCompletedDescription() {
			return "В задней части «Роскоши Ловьенны» вы обнаружили большой лифт, который после спуска привел вас в огромный комплекс, скрытый глубоко под Dominion.";
		}
	},
	
	DOLL_FACTORY_4(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Сбор улик";
		}
		@Override
		public String getDescription() {
			return "Вам и Фиамметте нужно найти доказательства того, что случилось с беженцами. Где-то здесь наверняка запрятаны гроссбухи, чертежи машин или другие подобные документы...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось собрать неопровержимые улики причастности Саэллатрикс и Ангеликса к похищениям и незаконному рабству, а также вы узнали пугающую правду о том, как создаются куклы Ловьенны.";
		}
	},
	
	DOLL_FACTORY_5(QuestType.SIDE, 30, 250) {
		@Override
		public String getName() {
			return "Пора уходить";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда у Фии накопилось достаточно доказательств для публикации статьи, вам двоим нужно сбежать с фабрики...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам с Фией удалось сбежать с нижнего яруса фабрики.";
		}
	},
	
	DOLL_FACTORY_5_DOLLIFIED(QuestType.SIDE, 30, 250) {
		@Override
		public String getName() {
			return "Окуклена!";
		}
		@Override
		public String getDescription() {
			return "Попавшись в руки к Angelixx, вы превратились в куклу! Вам нужно во что бы то ни стало найти Фию, чтобы отменить это пугающее превращение.<br/>[style.italics(Вам нужно найти и исследовать четыре)] [style.italicsExcellent(желтых)] [style.italics(плитки на фабрике, чтобы отыскать Фию.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "После того как Angelixx превратила вас в куклу, вам удалось найти Фию и обратить процесс вспять, после чего вы вдвоем сбежали с фабрики.";
		}
	},
	
	DOLL_FACTORY_6(QuestType.SIDE, 30, 500) {
		@Override
		public String getName() {
			return "Послание Фьяметты";
		}
		@Override
		public String getDescription() {
			return "Фьяметта пообещала написать статью обо всем, чему вы стали свидетелями в «Роскоши Ловьенны». Она обещала прислать вам весточку, как только статья будет готова к публикации.<br/>[style.italicsMinorGood(Спустя несколько дней загляните в вестибюль особняка Lilaya в светлое время суток.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Вместо послания от Фьяметты вы столкнулись с Саэллатрикс. Пользуясь своими близкими отношениями с Lilaya, она поставила вас в неловкое положение, потребовав подписать документ, подтверждающий, что Фьяметта лжет.";
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
			return "Вы отказались заключать сделку с Саэллатрикс, вместо этого поклявшись говорить правду и подтвердить всё, что Фиамметта написала в своей статье. Перед тем как стремительно покинуть особняк, Саэллатрикс упомянула, что её лавка будет закрыта неделю или две, пока не утихнет общественное негодование.<br/>[style.italicsMinorGood(Вам стоит вернуться в «Роскошь Ловьенны», когда она снова откроется, чтобы узнать, что стало с куклами...)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отказались заключать сделку с Саэллатрикс, вместо этого поклявшись говорить правду и подтвердить всё, что Фиамметта написала в своей статье. Вернувшись в «Роскошь Ловьенны» спустя неделю закрытия, вы обнаружили, что на Анжеликс возложили всю вину за незаконное порабощение, а Саэллатрикс теперь разрешено превращать в кукол лишь самых отъявленных преступников.<br/>Более того, не желая иметь вас в качестве врага, Саэллатрикс стремилась сохранить вас как клиента и даже позволила вам самим решить судьбу Анжеликс...";
		}
	},
	
	DOLL_FACTORY_7B(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Просто выгодный бизнес";
		}
		@Override
		public String getDescription() {
			return "Вы предали доверие Фиамметты и подписали документ, объявляющий репортершу лгуньей. Саэллатрикс была в восторге от вашего решения и попросила вас вернуться в её лавку, чтобы подробнее обсудить ваши особые награды...<br/>[style.italicsMinorGood(Вам стоит вернуться в «Роскошь Ловьенны», когда она снова откроется через пару дней...)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы предали доверие Фиамметты и подписали документ, заявляющий, что репортер лжет. Взамен Саэллатрикс предложила превращать любую из ваших рабынь в кукол за небольшую плату, либо же она сама заплатит вам, если вы позволите ей оставить их для продажи в своей лавке.";
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
			return "Nyan объяснила, что не может продавать зачарованную одежду, так как её оптовый поставщик внезапно перестал выходить на связь. Судя по всему, раньше у этого поставщика были хорошие отношения с Nyan, и его нехарактерное поведение заставило нервную кошкодевочку заподозрить, что с ним случилось что-то ужасное.<br/>Возможно, вы могли бы предложить Nyan свою помощь, выяснив, что произошло с этим поставщиком?";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы предложили Nyan свою помощь в поисках причин, по которым её оптовый поставщик зачарованной одежды внезапно перестал выходить на связь.";
		}
	},
	
	RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Спасение Kay";
		}
		@Override
		public String getDescription() {
			return "Nyan рассказала вам, что её поставщик, [kay.nameFull], ведет дела в складском районе Dominion. Вам нужно будет отправиться на северо-запад города, найти склад Kay и выяснить, почему он внезапно оборвал все контакты с Nyan.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы не только нашли склад Kay, но и обнаружили, что его дело фактически захвачено парой наглых охотников за головами.";
		}
	},
	
	RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "Охотники за головами-задиры";
		}
		@Override
		public String getDescription() {
			return "Вольфганг и Карл, пара охотников за головами, нанятых Кей для охраны его склада, взбунтовались против нанимателя и фактически захватили контроль над всем его бизнесом. Так или иначе, тебе придется убедить этих доберманов-задир оставить Кея в покое...";
		}
		@Override
		public String getCompletedDescription() {
			return "Разобравшись с Вольфгангом и Карлом и отправив их трусливо шнырять обратно в «Ложу охотников за головами» в Переулке Рабов, вы оба спасли бизнес Кея и обеспечили Nyan возможность снова иметь доступ к запасам зачарованной одежды. Выражая бесконечную благодарность, Кей сказал, что ты всегда можешь заглянуть к нему в гости.";
		}
	},
	
	RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Награда Nyan";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда ты спас бизнес Кея, тебе стоит вернуться к Nyan и сообщить ей о случившемся.";
		}
		@Override
		public String getCompletedDescription() {
			return "Очень счастливая Nyan выплатила тебе обещанную награду, а также предложила пожизненную скидку 25% d в своем магазине. Она также неосторожно обмолвилась, что она не замужем, в неуклюжей попытке зафлиртовать с тобой...";
		}
	},
	
	
	
	ROMANCE_HELENA_FAILED(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Разъяренная матриарх";
		}

		@Override
		public String getDescription() {
			return "После того как ты отказался продать Scarlett для Helena, высокомерная матриарх-гарпия забросила свои планы по ведению работоргового бизнеса и в гневе умчалась обратно в свое гнездо. Судя по тому, как безжалостно она оскорбила тебя перед уходом, можно быть уверенной, что она больше никогда не захочет тебя видеть...";
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
			return "Расспросив Helena о делах, вы обнаружили, что она едва сводит концы с концами, пытаясь удержать лавку на плаву. Выразив желание как-то улучшить свое заведение, Helena призналась, что у нее просто нет ни времени, ни желания заниматься этим самой. Возможно, вы могли бы предложить ей свою помощь?";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы предложили Helena помочь с обустройством ее лавки.";
		}
	},

	ROMANCE_HELENA_2_PURCHASE_PAINT(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Купить краску";
		}

		@Override
		public String getDescription() {
			return "Helena призналась, что первым делом она хочет перекрасить весь фасад своей лавки. Не выделив вам ни гроша на расходы, гарпия ожидает, что вы отправитесь в «Магазин товаров для дома Аргуса» и купите банку золотой краски марки «Пурпурная звезда». Как только купите её, вам нужно будет вернуться к Helena.<br/><i>('Магазин товаров для дома Аргуса' находится довольно далеко к югу от Переулка Рабов, рядом с каналом.)</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы купили необходимую краску в «Магазине товаров для дома Аргуса» и вернулись к Helena.";
		}
	},

	ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор фасада (1/3)";
		}

		@Override
		public String getDescription() {
			return "Купив золотую краску, вы вернулись к Helena, но она тут же потребовала, чтобы вы как можно скорее приступили к покраске фасада её лавки...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы содрали всю старую краску с фасада лавки Helena.";
		}
	},

	ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор фасада (2/3)";
		}

		@Override
		public String getDescription() {
			return "Вам нужно вернуться в лавку Helena в часы работы, чтобы узнать, каким будет ваше следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы перекрасили весь фасад магазина Helena, а также получили поставку мебели от суккуба по имени Наталья.";
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
			return "Под присмотром матриарха гарпий вы вывели золотыми буквами над дверью её магазина надпись «Бутик Helena».";
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
			return "Helena раскрыла вам свой план: она хочет провести ребрендинг своей лавки рабов, превратив её в место, где клиенты смогут заказывать рабов на заказ. Человеком, которого она планирует обучить искусству обращения с такими рабами, является её старый учитель этикета, который, судя по всему, является не кем иным, как сестрой Scarlett."
					+ (slave
						?" Условие, которое она поставила перед Helena, заключается в том, чтобы та освободила свою строптивую сестру из рабства, пообещала больше никогда не порабощать её и затем оставила её на службе...<br/>"
							+(playerOwner
								?"Вам придется привести Scarlett к Helena и продать её ей обратно..."
								:"Вам придется найти Scarlett и выкупить её у её нового владельца. По словам Helena, её купил владелец антикварной лавки где-то в Торговых рядах.")
						:" Условие, которое она поставила перед Helena, заключается в том, чтобы та держала свою строптивую сестру на службе и пообещала больше никогда не порабощать её...<br/> Вам нужно будет подняться в гнездо Helena, найти Scarlett, а затем велеть ей вернуться к Helena...");
		}
		
		@Override
		public String getCompletedDescription() {
			return "Согласно пожеланиям старого учителя этикета Helena, отныне Scarlett будет работать личной помощницей матриарха гарпий.";
		}
	},

	ROMANCE_HELENA_5_SCARLETT_TRAINER(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Помощница гарпий";
		}

		@Override
		public String getDescription() {
			return "Две гарпии ушли пораньше, чтобы навестить сестру Scarlett. Тебе нужно вернуться в лавку Helena в часы работы, чтобы узнать, каким будет твое следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты вернулся в лавку Helena и обнаружил, что всё подготовлено и готово к тому, чтобы матрона гарпий начала принимать покупателей. Однако перед этим тебе нужно помочь ей еще с парой вещей...";
		}
	},

	ROMANCE_HELENA_6_ADVERTISING(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Реклама";
		}

		@Override
		public String getDescription() {
			return "Получив полдюжины зачарованных плакатов, воспевающих красоту Helena, ты должен развесить их у входа в Переулок Рабов, чтобы помочь с рекламой её лавки.";
		}

		@Override
		public String getCompletedDescription() {
			return "Ты развесил плакаты с рекламой «Бутика Helena» у входа в Переулок Рабов.";
		}
	},

	ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Подготовка к торжественному открытию";
		}

		@Override
		public String getDescription() {
			return "После того как ты развесил плакаты, появилась Scarlett и повела тебя обратно в лавку Helena. Твоя новая задача — подготовить всё к завтрашнему торжественному открытию, а это значит, что придется работать всю ночь...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы с Scarlett закончили подготовку к торжественному открытию лавки.";
		}
	},

	ROMANCE_HELENA_8_FINISH(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Приготовление напитков";
		}

		@Override
		public String getDescription() {
			return "Чтобы Scarlett не натворила дел во время торжественного открытия, Helena поручила вам двоим оставаться в подсобке и готовить напитки для гостей.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы с Scarlett провели всё торжественное открытие в подсобке, разливая напитки. Наконец, проявив признательность за ваши старания, Helena сказала, что в награду она готова позволить тебе пригласить её на свидание...";
		}
	},
	
	

	ROMANCE_NATALYA_FAILED_INTERVIEW(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Собеседование провалено";
		}
		@Override
		public String getDescription() {
			return "Отказываясь выполнять просьбы Natalya во время собеседования, ты был выброшен из Dominion Express с приказом никогда не возвращаться...";
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
			return "Отказываясь подписать контракт, предложенный тебе Natalya, ты был выброшен из Dominion Express с приказом никогда не возвращаться...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_NATALYA_1_INTERVIEW_START(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Пройдено собеседование";
		}
		@Override
		public String getDescription() {
			return "Natalya, управляющая конюшнями в компании Dominion Express, предложила тебе пройти собеседование на должность '[style.mule]'.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ты принял предложение Natalya пройти собеседование на должность '[style.mule]' в Dominion Express.";
		}
	},

	ROMANCE_NATALYA_2_CONTRACT_SIGNED(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "[style.Mule] Натальи";
		}
		@Override
		public String getDescription() {
			return "Приняв предложение о собеседовании на должность '[style.mule]', теперь вам нужно успешно пройти его и подписать контракт.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы успешно прошли собеседование у Натальи, и после подписания контракта вам сказали, что теперь вам нужно превратиться в тавра [style.a_shemale].";
		}
	},
	
	ROMANCE_NATALYA_3_TRAINING_1(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Обучение [style.Mule]";
		}
		@Override
		public String getDescription() {
			return "Наталья сказала вам, что первая часть вашего обучения будет включать в себя повторение вашего орального секса на одном из рабов-кентавров.";
		}
		@Override
		public String getCompletedDescription() {
			return "Превратившись в тавра [style.a_shemale], вы начали свое обучение [style.mule], сосая член одного из самых строптивых рабов-кентавров Dominion Express.";
		}
	},

	ROMANCE_NATALYA_4_TRAINING_2(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Дальнейшее обучение";
		}
		@Override
		public String getDescription() {
			return "Наталья снова велела вам вернуться на следующий день, чтобы продолжить обучение, которое будет включать в себя умение наслаждаться римджобом.";
		}
		@Override
		public String getCompletedDescription() {
			return "Второй этап вашего обучения заключался в том, чтобы накрасить губы яркой помадой и сделать анилингус госпоже Наталье.";
		}
	},

	ROMANCE_NATALYA_5_TRAINING_3(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Финальное обучение";
		}
		@Override
		public String getDescription() {
			return "Наталья велела вам вернуться на следующий день, чтобы завершить обучение: вам нужно будет сделать римджоб рабу-кентавру, а затем позволить и Наталье, и этому рабу оседлать вас и оттрахать в задницу.";
		}
		@Override
		public String getCompletedDescription() {
			return "После того как вы отлизали анус рабыне-кентавру, а затем она оседлала вас и оттрахала в зад, Наталья объявила, что ваша тренировка [style.mule] завершена.";
		}
	},

	ROMANCE_MONICA_1_TO_THE_FARM(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "На ферму";
		}
		@Override
		public String getDescription() {
			return "Когда вы предложили помочь найти её персональный молокоотсос, Моника сказала, что его можно найти на её старом месте работы — ферме под названием «Молочная Эвеликс», расположенной к северо-востоку от Элиса. Вам нужно отправиться на эту ферму и спросить молокоотсос Моники...";
		}
		@Override
		public String getCompletedDescription() {
			return "Найдя ферму, где раньше работала Моника, вы спросили про её персональный молокоотсос и получили возможность встретиться с владелицей фермы...";
		}
	},

	ROMANCE_MONICA_2_UNREASONABLE_DEMAND(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Неразумное требование";
		}
		@Override
		public String getDescription() {
			return "Вам удалось встретиться с владелицей фермы, Эвеликс, которая предстала перед вами в образе высокомерной и жадной суккуба. Признавая, что молокоотсос почти ничего не стоит, она требует, чтобы вы либо отдали ей огромную сумму Огней, либо подписали подозрительный контракт в обмен на него...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось получить персональный молокоотсос Моники у Эвеликс.";
		}
	},

	ROMANCE_MONICA_3_THE_JOURNEY_HOME(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Путь домой";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда персональный молокоотсос Моники у вас в руках, осталось только вернуть его законной владелице.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернули Монике её персональный молокоотсос, чем вызвали у неё крайнее удивление и восторг.";
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
