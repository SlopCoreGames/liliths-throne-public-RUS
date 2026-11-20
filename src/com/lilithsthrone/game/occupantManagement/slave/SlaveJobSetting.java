package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.87
 * @version 0.4.9.1
 * @author Innoxia
 */
public enum SlaveJobSetting {
	
	// Sex as a sub settings:
	SEX_ORAL(PresetColour.GENERIC_SEX, "Оральный секс", "Орал П", "Позволить этой рабыне делать оральный секс другим."),
	SEX_VAGINAL(PresetColour.GENERIC_SEX, "Вагинальный секс", "Ваг. Р", "Позволить этой рабыне принимать вагинальный секс."),
	SEX_ANAL(PresetColour.GENERIC_SEX, "Анальный секс", "Анал Р", "Позволить этой рабыне принимать анальный секс.") {
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	SEX_NIPPLES(PresetColour.GENERIC_SEX, "Стимуляция сосков", "Соски (Р)", "Разрешить этой рабыне принимать проникающий секс сосками.") {
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	// Sex as a dom settings:
	SEX_ORAL_DOM(PresetColour.GENERIC_SEX, "Принимать оральный секс", "Оральный (Р)", "Разрешить этой рабыне принимать оральный секс от других."),
	SEX_VAGINAL_DOM(PresetColour.GENERIC_SEX, "Выполнять вагинальный секс", "Вагинальный (П)", "Разрешить этой рабыне заниматься вагинальным сексом с другими."),
	SEX_ANAL_DOM(PresetColour.GENERIC_SEX, "Выполнять анальный секс", "Анальный (П)", "Разрешить этой рабыне заниматься анальным сексом с другими.") {
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	SEX_NIPPLES_DOM(PresetColour.GENERIC_SEX, "Соски P", "Соски P", "Разрешить этой рабыне заниматься проникновением в соски других.") {
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	
	MILKING_NO_PREFERENCE(PresetColour.BASE_GREY, "Без предпочтений", "NP", "Назначить эту рабыню работать в любой доступной доильной комнате."),
	MILKING_INDUSTRIAL(PresetColour.GENERIC_MINOR_BAD, "Промышленная дойка", "IN", "Назначить эту рабыню работать в доильной комнате с промышленными доильными аппаратами."),
	MILKING_REGULAR(PresetColour.BASE_YELLOW_LIGHT, "Обычная дойка", "RG", "Назначить эту рабыню работать в доильной комнате с обычными доильными аппаратами."),
	MILKING_ARTISAN(PresetColour.GENERIC_MINOR_GOOD, "Ремесленное доение", "AR", "Назначить эту рабыню работать в доильном зале вместе с ремесленницами."),
	MILKING_MILK(PresetColour.BASE_YELLOW_LIGHT, "Сбор молока", "CM", "Разрешить сбор молока у этой рабыни."),
	MILKING_MILK_CROTCH(PresetColour.BASE_YELLOW_LIGHT, "Доение вымени", "CU", "Разрешить доение вымени этой рабыни."),
	MILKING_CUM(PresetColour.BASE_BLUE_LIGHT, "Сбор спермы", "CC", "Разрешить сбор спермы этой рабыни."),
	MILKING_GIRLCUM(PresetColour.BASE_PINK_LIGHT, "Сбор девичьей спермы", "CG", "Разрешить сбор девичьей спермы этой рабыни."),
	MILKING_TEAR_HYMEN(PresetColour.BASE_PINK_DEEP, "Разорвать девственную плеву", "TH", "Если у этой рабыни еще цела девственная плева, разрешить вставлять «насос для киски» (что приведет к её разрыву). Если эта опция отключена, рабыни с целой плевой не будут принимать «насосы», и их девичья сперма не будет собираться."),
	
	MILKING_MILK_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Автопродажа молока", "SM", "Установить автоматическую продажу молока этой рабыни вместо его хранения."),
	MILKING_MILK_CROTCH_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Автопродажа вымени", "SCM", "Установить автоматическую продажу молока из промежности этой рабыни вместо его хранения."),
	MILKING_CUM_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Автопродажа спермы", "SC", "Установить автоматическую продажу спермы этой рабыни вместо её хранения."),
	MILKING_GIRLCUM_AUTO_SELL(PresetColour.CURRENCY_GOLD, "Автопродажа девичьей спермы", "SG", "Установить автоматическую продажу девичьей спермы этой рабыни вместо её хранения."),
	
	
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE(PresetColour.FEMININE, "Женская ТФ", "ТФ (Ж)", "Позволить этой рабыне подвергаться женским трансформациям."),
	TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE(PresetColour.MASCULINE, "Мужская ТФ", "ТФ (М)", "Позволить этой рабыне подвергаться мужским трансформациям."),

	DOLL_STATUE_ARTISTIC(PresetColour.BASE_TAN, "Художественная", "DPAR", "Приказать этой кукле принимать художественную позу, когда она изображает статую."),
	DOLL_STATUE_ATTENTION(PresetColour.BASE_TAN, "Смирно", "DPAT", "Приказать этой кукле стоять смирно, когда она изображает статую."),
	DOLL_STATUE_STANDING_SPLIT(PresetColour.BASE_TAN, "Складка стоя", "DPSS", "Приказать этой кукле выполнять складку стоя, когда она изображает статую."),
	DOLL_STATUE_MISSIONARY(PresetColour.BASE_TAN, "Миссионерская", "DPMI", "Приказать этой кукле лежать в миссионерской позе, когда она изображает статую."),
	DOLL_STATUE_ALL_FOURS(PresetColour.BASE_TAN, "На четвереньках", "DPAF", "Приказать этой кукле встать на четвереньки, когда она изображает статую."),
	DOLL_STATUE_SQUATTING(PresetColour.BASE_TAN, "На корточках", "DPSQ", "Приказать этой кукле сесть на корточки с разведенными ногами и руками за головой, когда она изображает статую."),
	DOLL_STATUE_BRIDGE(PresetColour.BASE_TAN, "Мостик", "DPBR", "Приказать этой кукле выполнять акробатический мостик, когда она изображает статую."),

	SECURITY_ENTRANCE_PRIORITY(PresetColour.BASE_GOLD, "Приоритет у входа", "EP", "Эту рабыню будут выбирать чаще других, чтобы она стояла у входа. (Если это право есть у нескольких рабынь, там останется та, что придет первой.)"),
	SECURITY_ANSWER_DOOR(PresetColour.BASE_GREEN_LIGHT, "Открывать дверь", "AD", "Если эта рабыня находится у входа, она будет открывать дверь вместо Роуз."),
	
	BEDROOM_GREETING(PresetColour.GENERIC_MINOR_GOOD, "Приветствие", "BG", "Приказать этой рабыне приветствовать вас каждый раз, когда вы входите в комнату."),
	BEDROOM_CLEAN(PresetColour.BASE_BLUE_LIGHT, "Уборка", "BC", "Велеть этой рабыне поддерживать чистоту в вашей комнате."),
	BEDROOM_WAKE_UP(PresetColour.BASE_YELLOW_LIGHT, "Пробуждение", "BK", "Позволить этой рабыне служить вашим будильником, чтобы она будила вас от глубокого сна в назначенное время."),
	BEDROOM_HELP_WASH(PresetColour.BASE_BLUE, "Мытье", "BW", "Эта рабыня будет помогать вам в ванной, когда вы моетесь. (Это переопределит настройки «Чистоты» рабыни, так как её будут мыть вместе с вами.)"),
	
	BEDROOM_SLEEP_FLOOR(PresetColour.GENERIC_MINOR_BAD, "Спать на полу", "BSF", "Приказать этой рабыне спать на полу в вашей комнате."),
	BEDROOM_SLEEP_ON_BED(PresetColour.BASE_PURPLE_LIGHT, "Спать на кровати", "BSO", "Разрешить этой рабыне спать на вашей кровати, но не под одеялом."),
	BEDROOM_SLEEP_IN_BED(PresetColour.BASE_PINK, "Спать в кровати", "BSI", "Разрешить этой рабыне спать в вашей кровати рядом с вами, под одеялом."),
	
	
	SPA_SHOWERING(PresetColour.BASE_BLUE, "Принятие душа", "SSH", "Эта рабыня будет помогать вам в душевой комнате, когда вы моетесь. (Это переопределит настройки «Чистоты» рабыни, так как её будут мыть вместе с вами.)"),
	SPA_BATHING(PresetColour.BASE_AQUA, "Купание", "SBA", "Эта рабыня будет сопровождать вас в спа, когда вы принимаете ванну. (Это переопределит настройки «Чистоты» рабыни, так как её будут мыть вместе с вами.)"),
	SPA_STRIP_TO_BATHE(PresetColour.BASE_PERIWINKLE, "Купаться нагишом", "SBN", "Эта рабыня будет полностью раздеваться, когда присоединяется к вам для купания в спа."),
	SPA_MASSAGE(PresetColour.BASE_BROWN, "Массаж", "SMA", "Эта рабыня будет доступна для массажа."),
	SPA_SAUNA(PresetColour.BASE_ROSE, "Сауна", "SSA", "Эта рабыня будет сопровождать вас в сауне (после того, как вы построите это расширение)."),
	SPA_POOL(PresetColour.BASE_BLUE_LIGHT, "Плавание", "SPA", "Эта рабыня будет плавать вместе с вами, когда вы пользуетесь бассейном (после того, как вы построите это расширение)."),

//	SPA_RECEPTION(PresetColour.BASE_BROWN, "Receptionist", "SRA", "Assign this slave to work at the spa's reception."),
//	SPA_ASSISTANT(PresetColour.BASE_GREEN_LIME, "Assistant", "SPA", "Assign this slave to work as one of the assistants within the spa proper."),
	;
	
	private Colour colour;
	private String name;
	private String tag;
	private String description;
	
	private SlaveJobSetting(Colour colour, String name, String tag, String description) {
		this.colour = colour;
		this.name = name;
		this.tag = tag;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
	
	public String getTag() {
		return tag;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDailyEffectsDescription() {
		return null;
	}
	
	public String applyDailyEffects() {
		return null;
	}
	
	/**
	 * @return true if this setting is displayed to the player. It will only be false if an associated content setting (e.g. anal, nipple pen) is turned off.
	 */
	public boolean isAvailable() {
		return true;
	}
	
}
