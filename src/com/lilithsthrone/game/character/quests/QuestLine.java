package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.1
 * @version 0.4
 * @author Innoxia
 */
public enum QuestLine {

	// Main quests:

	MAIN("Трон Лилит", "Вы завершили все основные квесты в этой версии!",
			QuestType.MAIN,
			QuestTree.mainQuestTree),

	// Side quests:

//	SIDE_ITEM_DISCOVERY("Item Discovery", "You have found all the different items that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_ITEMS),
//
//	SIDE_RACE_DISCOVERY("Race Discovery", "You have found all the different races that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_RACES),

	SIDE_ENCHANTMENT_DISCOVERY("Эссенции и Зачарования", "Теперь вы знаете, как использовать эссенции для создания и модификации зачарованных предметов!",
			QuestType.SIDE,
			QuestTree.enchantmentTree),

	SIDE_FIRST_TIME_PREGNANCY("Беременная", "С помощью Лилайи вы смогли завершить свою первую беременность. Возможно, первую из многих...",
			QuestType.SIDE,
			QuestTree.pregnancyTree),

	SIDE_FIRST_TIME_INCUBATION("Закиданная яйцами", "Вы успешно инкубировали, отложили и вылупили яйца, которые были посажены внутри вас!",
			QuestType.SIDE,
			QuestTree.incubationTree),

	SIDE_SLAVERY("Работорговец", "Благодаря рекомендательному письму Лилайи, вам удалось получить желанную лицензию на рабство!",
			QuestType.SIDE,
			QuestTree.slaveryTree),

	SIDE_ACCOMMODATION("Ночлег и еда", "Лилая с радостью дала вам разрешение использовать свободные комнаты для размещения ваших друзей и семьи, при условии, что вы оплатите расходы, которые они повлекут...",
			QuestType.SIDE,
			QuestTree.accommodationTree),

	SIDE_DOLL_STORAGE("Хранилище кукол", "Лилая сказала вам, что вы можете использовать любую свободную комнату для хранения любых кукол, которые вы покупаете...",
			QuestType.SIDE,
			QuestTree.dollStorageTree),

	SIDE_HYPNO_WATCH("Эксперимент Arthur", "Вы помогли Артуру завершить его исследования по созданию гипно-часов, которые теперь находятся в вашем распоряжении!",
			QuestType.SIDE,
			QuestTree.hypnoWatchTree),

	SIDE_ARCANE_LIGHTNING("Arcane молния", "Артур смог извлечь секреты арканической молнии из глобуса, который вы ему дали, что позволило вам выучить два невероятно мощных заклинания.",
			QuestType.SIDE,
			QuestTree.arcaneLightningTree),
	
	SIDE_HARPY_PACIFICATION("Разгневанные гарпии", "Вам удалось успокоить всех трех матерей гарпий, в результате чего гарпийские гнезда стали безопасными для путешествий!",
			QuestType.SIDE,
			QuestTree.angryHarpyTree),

	SIDE_SLIME_QUEEN("Slime королева", "Вы справились с Королевой Слайма!",
			QuestType.SIDE,
			QuestTree.slimeQueenTree),

	SIDE_TELEPORTATION("Проблемы с телепортацией", "После того, как вы научились телепортироваться, вам удалось сбежать из склада Энфорсера.",
			QuestType.SIDE,
			QuestTree.teleportingTree),

	SIDE_DADDY("Любопытный инкуб", "Вы справились с демоном, [daddy.name], который проявлял интерес к Лилае.",
			QuestType.SIDE,
			QuestTree.daddyTree),

	SIDE_BUYING_BRAX("Приручение волка", "После того, как она заставила вас выполнить для нее ряд скучных задач, Кэнди наконец продала вам [brax.name].",
			QuestType.SIDE,
			QuestTree.buyingBraxTree),

	SIDE_VENGAR("Тирания Венгара", "Вы справились с Венгаром и убедились, что Аксель больше не должен беспокоиться о нем.",
			QuestType.SIDE,
			QuestTree.vengarTree),


	SIDE_WES("Бунтующий Энфорсер", "Вам удалось успешно справиться с Квартирмейстером Энфорсера.",
			QuestType.SIDE,
			QuestTree.wesTree),
        
	SIDE_REBEL_BASE("Разграбление могил", "Вам удалось сбежать из заброшенного убежища повстанцев.",
		QuestType.SIDE,
		QuestTree.rebelBaseTree),

	SIDE_REBEL_BASE_FIREBOMBS("Острые тефтели", "Вы получили стабильный запас Арканальных зажигательных бомб. По обычной цене, конечно.",
		QuestType.SIDE,
		QuestTree.rebelBaseFirebombTree),
	
	SIDE_EISEK_STALL("Исправьте 'Эра Верхний", "Вы помогли Эйсеку отремонтировать его лавку.",
		    QuestType.SIDE,
		    QuestTree.eisekStallTree),
	
	SIDE_EISEK_MOB("Стадное чувство", "Вы убедили банду, которая преследовала Эйсека, оставить его в покое.",
		    QuestType.SIDE,
		    QuestTree.eisekMobTree),
	
	SIDE_EISEK_SILLYMODE("Любители драконов", "Вы столкнулись с странной группой людей, одержимых драконами, и очистили их подземелье.",
		    QuestType.SIDE,
		    QuestTree.eisekSillyModeTree),

	SIDE_OGLIX_BEER_BARRELS("Пивная сучка", "Вы обеспечили Огликс больше бочек, что позволило вам отправить четырех преступников из близлежащих переулков к ней, чтобы они стали новыми пивными сучками!",
			QuestType.SIDE,
			QuestTree.beerBarrelTree),

	SIDE_LUNEXIS_ESCAPE("Служение Лунексису", "Вы повиновались приказам вашей госпожи и обеспечили ей возможность сбежать, тем самым запечатав свою судьбу стать одной из ее личных кок-сук...",
			QuestType.SIDE,
			QuestTree.lunexisEscapeTree),

	SIDE_DOLL_FACTORY("Работа с куклами", "Вы раскрыли правду о том, как создаются премиальные секс-куклы для продажи в 'Lovienne's Luxury'...",
			QuestType.SIDE,
			QuestTree.dollFactoryTree),
	
	// Romance quests:
	
	RELATIONSHIP_NYAN_HELP("Проблемы с поставщиками", "Вы помогли Нян решить проблему, которую она имела с поставщиками.",
			QuestType.RELATIONSHIP,
			QuestTree.nyanTree),

	ROMANCE_HELENA("Помощник Ее Величества", "Вы успешно выполнили все задания, которые Хелена дала вам, и в качестве награды, вы можете заказывать у нее на заказ рабов и встречаться с ней каждую пятницу вечером.",
			QuestType.RELATIONSHIP,
			QuestTree.helenaTree),

	ROMANCE_NATALYA("[style.Mule] Обучение", "Завершив обучение у Мадам Натальи, вы теперь квалифицированный [style.mule] и ожидается, что вы будете заниматься сексом с кентаврами Dominion Express.",
			QuestType.RELATIONSHIP,
			QuestTree.natalyaTree),

	ROMANCE_MONICA("Доилка Моники", "Вы успешно вернули персонализированный Moo Milker Моники, и в результате она очень благодарна вам.",
			QuestType.RELATIONSHIP,
			QuestTree.monicaTree),
	;

	private String name, completedDescription;
	private QuestType type;
	private TreeNode<Quest> questTree;

	private QuestLine(String name, String completedDescription, QuestType type, TreeNode<Quest> questTree) {
		this.name = name;
		this.completedDescription = completedDescription;
		this.type = type;
		this.questTree = questTree;
	}

	public String getName() {
		return name;
	}

	public String getCompletedDescription() {
		return completedDescription;
	}

	public QuestType getType() {
		return type;
	}

	public TreeNode<Quest> getQuestTree() {
		return questTree;
	}

}
