package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.1
 * @version 0.4
 * @author Innoxia
 */
public enum QuestLine {

	// Main quests:

	MAIN("Трон Лилит", "В этой версии вы прошли весь основной сюжет!",
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

	SIDE_ENCHANTMENT_DISCOVERY("Эссенции и зачарования", "Теперь вы знаете, как использовать эссенции для создания и модификации зачарованных предметов!",
			QuestType.SIDE,
			QuestTree.enchantmentTree),

	SIDE_FIRST_TIME_PREGNANCY("Залетевшая", "С помощью Lilaya вам удалось пережить свою первую беременность. Возможно, первую из многих...",
			QuestType.SIDE,
			QuestTree.pregnancyTree),

	SIDE_FIRST_TIME_INCUBATION("Оклеенная яйцами", "Вы успешно выносили, отложили и вылупили яйца, которые были в вас посажены!",
			QuestType.SIDE,
			QuestTree.incubationTree),

	SIDE_SLAVERY("Работорговец", "Благодаря рекомендательному письму от Lilaya, вам удалось получить заветную лицензию работорговца!",
			QuestType.SIDE,
			QuestTree.slaveryTree),

	SIDE_ACCOMMODATION("Жилье и питание", "Лилая с радостью разрешила вам использовать свободные комнаты для размещения ваших друзей и семьи, при условии, что вы будете оплачивать все их расходы...",
			QuestType.SIDE,
			QuestTree.accommodationTree),

	SIDE_DOLL_STORAGE("Хранилище кукол", "Лилая сказала, что вы можете использовать любую свободную комнату для хранения купленных вами кукол...",
			QuestType.SIDE,
			QuestTree.dollStorageTree),

	SIDE_HYPNO_WATCH("Эксперимент Артура", "Вы помогли Артуру завершить его исследование гипно-часов, меняющих ориентацию, которые теперь находятся в вашем распоряжении!",
			QuestType.SIDE,
			QuestTree.hypnoWatchTree),

	SIDE_ARCANE_LIGHTNING("Аркана-молния", "Артуру удалось извлечь секреты аркана-молнии из сферы, которую вы ему дали, что позволило вам выучить два невероятно мощных заклинания.",
			QuestType.SIDE,
			QuestTree.arcaneLightningTree),
	
	SIDE_HARPY_PACIFICATION("Разгневанные гарпии", "Вам удалось успокоить всех трех матрон гарпий, благодаря чему путешествие через Гнезда гарпий стало безопасным!",
			QuestType.SIDE,
			QuestTree.angryHarpyTree),

	SIDE_SLIME_QUEEN("Королева Slime", "Вы расправились с Королевой Slime!",
			QuestType.SIDE,
			QuestTree.slimeQueenTree),

	SIDE_TELEPORTATION("Проблемы с телепортацией", "Освоив телепортацию, вам удалось сбежать со склада Enforcer.",
			QuestType.SIDE,
			QuestTree.teleportingTree),

	SIDE_DADDY("Любопытный инкуб", "Вы разобрались с демоном [daddy.name], который проявлял интерес к Lilaya.",
			QuestType.SIDE,
			QuestTree.daddyTree),

	SIDE_BUYING_BRAX("Приобретение волка", "После того как она заставила вас выполнить ряд утомительных поручений, Candi наконец продала вам [brax.name].",
			QuestType.SIDE,
			QuestTree.buyingBraxTree),

	SIDE_VENGAR("Тирания Венгара", "Вы разделались с Венгаром, позаботившись о том, чтобы Axel больше о нем не беспокоился.",
			QuestType.SIDE,
			QuestTree.vengarTree),


	SIDE_WES("Разбойник-Enforcer", "Вам удалось успешно разобраться с интендантом Enforcer.",
			QuestType.SIDE,
			QuestTree.wesTree),
        
	SIDE_REBEL_BASE("Разграбление могил", "Вам удалось сбежать из заброшенного убежища повстанцев.",
		QuestType.SIDE,
		QuestTree.rebelBaseTree),

	SIDE_REBEL_BASE_FIREBOMBS("Острые тефтели", "Теперь у вас есть постоянный запас Arcane огненных бомб. Разумеется, по обычной завышенной цене.",
		QuestType.SIDE,
		QuestTree.rebelBaseFirebombTree),
	
	SIDE_EISEK_STALL("Привести в порядок", "Вы помогли Эйзеку привести его лавку в порядок.",
		    QuestType.SIDE,
		    QuestTree.eisekStallTree),
	
	SIDE_EISEK_MOB("Стадное чувство", "Вы убедили толпу, преследовавшую Эйзека, оставить его в покое.",
		    QuestType.SIDE,
		    QuestTree.eisekMobTree),
	
	SIDE_EISEK_SILLYMODE("Любители драконов", "Вы наткнулись на странную группу помешанных на драконах чудиков и зачистили их подземелье.",
		    QuestType.SIDE,
		    QuestTree.eisekSillyModeTree),

	SIDE_OGLIX_BEER_BARRELS("Пивной разврат", "Вы раздобыли больше бочек для Огликса, что позволило отправить к ней четверых преступников из ближайших переулков, чтобы те стали новыми пивными сучками!",
			QuestType.SIDE,
			QuestTree.beerBarrelTree),

	SIDE_LUNEXIS_ESCAPE("Служение Лунексис", "Вы исполнили приказ своей Госпожи и обеспечили ей побег, тем самым предрешив свою судьбу стать одной из её личных кожаных чехлов для члена...",
			QuestType.SIDE,
			QuestTree.lunexisEscapeTree),

	SIDE_DOLL_FACTORY("Дело о куклах", "Вы раскрыли правду о том, как на самом деле создаются элитные секс-куклы, продающиеся в 'Lovienne's Luxury'...",
			QuestType.SIDE,
			QuestTree.dollFactoryTree),
	
	// Romance quests:
	
	RELATIONSHIP_NYAN_HELP("Проблемы с поставщиками", "Вы помогли Nyan решить проблемы, возникшие у неё с поставщиками.",
			QuestType.RELATIONSHIP,
			QuestTree.nyanTree),

	ROMANCE_HELENA("Помощник Ее Высочества", "Вы успешно выполнили все задания, которые давала вам Helena, и в награду вы можете как заказывать у нее индивидуальных рабов, так и приглашать ее на свидание каждый вечер пятницы.",
			QuestType.RELATIONSHIP,
			QuestTree.helenaTree),

	ROMANCE_NATALYA("Обучение [style.Mule]", "Завершив обучение у госпожи Натальи, теперь вы квалифицированный [style.mule] и должны оказывать сексуальные услуги кентаврам-рабам Dominion Express.",
			QuestType.RELATIONSHIP,
			QuestTree.natalyaTree),

	ROMANCE_MONICA("Доильщик Моники", "Вы успешно вернули персональный доильный аппарат Моники, и в результате она очень вам благодарна.",
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
