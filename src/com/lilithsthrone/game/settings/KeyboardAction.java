package com.lilithsthrone.game.settings;

import javafx.scene.input.KeyCode;

/**
 * @since 0.1.61
 * @version 0.3.1
 * @author Innoxia
 */
public enum KeyboardAction {

	MENU("Меню", new KeyCodeWithModifiers(KeyCode.ESCAPE), null),

	FULL_SCREEN("Переключить полноэкранный режим", new KeyCodeWithModifiers(KeyCode.F11), null),
	
	MOVE_NORTH("Движение на север", new KeyCodeWithModifiers(KeyCode.W), new KeyCodeWithModifiers(KeyCode.UP)),
	MOVE_WEST("Движение на запад", new KeyCodeWithModifiers(KeyCode.A), new KeyCodeWithModifiers(KeyCode.LEFT)),
	MOVE_SOUTH("Движение на юг", new KeyCodeWithModifiers(KeyCode.S), new KeyCodeWithModifiers(KeyCode.DOWN)),
	MOVE_EAST("Движение на восток", new KeyCodeWithModifiers(KeyCode.D), new KeyCodeWithModifiers(KeyCode.RIGHT)),
	
	MOVE_RESPONSE_CURSOR_NORTH("Курсор ответа вверх", new KeyCodeWithModifiers(KeyCode.W, false, true), new KeyCodeWithModifiers(KeyCode.UP, false, true)),
	MOVE_RESPONSE_CURSOR_WEST("Курсор ответа влево", new KeyCodeWithModifiers(KeyCode.A, false, true), new KeyCodeWithModifiers(KeyCode.LEFT, false, true)),
	MOVE_RESPONSE_CURSOR_SOUTH("Курсор ответа вниз", new KeyCodeWithModifiers(KeyCode.S, false, true), new KeyCodeWithModifiers(KeyCode.DOWN, false, true)),
	MOVE_RESPONSE_CURSOR_EAST("Курсор ответа вправо", new KeyCodeWithModifiers(KeyCode.D, false, true), new KeyCodeWithModifiers(KeyCode.RIGHT, false, true)),

	QUICKSAVE("Быстрое сохранение", new KeyCodeWithModifiers(KeyCode.F5), null),
	QUICKLOAD("Быстрая загрузка", new KeyCodeWithModifiers(KeyCode.F9), null),

	MENU_SELECT("Выбрать", new KeyCodeWithModifiers(KeyCode.SPACE), new KeyCodeWithModifiers(KeyCode.ENTER)),
	INVENTORY("Инвентарь", new KeyCodeWithModifiers(KeyCode.I), new KeyCodeWithModifiers(KeyCode.TAB)),
	JOURNAL("Телефон", new KeyCodeWithModifiers(KeyCode.P), new KeyCodeWithModifiers(KeyCode.J)),
	MAP("Карта", new KeyCodeWithModifiers(KeyCode.M), null),
	CHARACTERS("Персонажи", new KeyCodeWithModifiers(KeyCode.C), null),
	ZOOM("Масштаб", new KeyCodeWithModifiers(KeyCode.Z), null),

	SCROLL_UP("Прокрутка вверх", new KeyCodeWithModifiers(KeyCode.PAGE_UP), null),
	SCROLL_DOWN("Прокрутка вниз", new KeyCodeWithModifiers(KeyCode.PAGE_DOWN), null),

	RESPOND_1("Ответ 1", new KeyCodeWithModifiers(KeyCode.DIGIT1), null),
	RESPOND_2("Ответ 2", new KeyCodeWithModifiers(KeyCode.DIGIT2), null),
	RESPOND_3("Ответ 3", new KeyCodeWithModifiers(KeyCode.DIGIT3), null),
	RESPOND_4("Ответ 4", new KeyCodeWithModifiers(KeyCode.DIGIT4), null),
	RESPOND_5("Ответ 5", new KeyCodeWithModifiers(KeyCode.DIGIT5), null),
	RESPOND_6("Ответ 6", new KeyCodeWithModifiers(KeyCode.DIGIT1, false, true), null),
	RESPOND_7("Ответ 7", new KeyCodeWithModifiers(KeyCode.DIGIT2, false, true), null),
	RESPOND_8("Ответ 8", new KeyCodeWithModifiers(KeyCode.DIGIT3, false, true), null),
	RESPOND_9("Ответ 9", new KeyCodeWithModifiers(KeyCode.DIGIT4, false, true), null),
	RESPOND_10("Ответ 10", new KeyCodeWithModifiers(KeyCode.DIGIT5, false, true), null),
	RESPOND_11("Ответ 11", new KeyCodeWithModifiers(KeyCode.DIGIT1, true, false), null),
	RESPOND_12("Ответ 12", new KeyCodeWithModifiers(KeyCode.DIGIT2, true, false), null),
	RESPOND_13("Ответ 13", new KeyCodeWithModifiers(KeyCode.DIGIT3, true, false), null),
	RESPOND_14("Ответ 14", new KeyCodeWithModifiers(KeyCode.DIGIT4, true, false), null),
	RESPOND_0("Ответ 15", new KeyCodeWithModifiers(KeyCode.DIGIT5, true, false), new KeyCodeWithModifiers(KeyCode.BACK_SPACE)),

	RESPOND_NEXT_TAB("Следующая вкладка ответов", new KeyCodeWithModifiers(KeyCode.E, false, true), null),
	RESPOND_PREVIOUS_TAB("Предыдущая вкладка ответов", new KeyCodeWithModifiers(KeyCode.Q, false, true), null),
	
	RESPOND_NEXT_PAGE("Следующая страница ответов", new KeyCodeWithModifiers(KeyCode.E), null),
	RESPOND_PREVIOUS_PAGE("Предыдущая страница ответов", new KeyCodeWithModifiers(KeyCode.Q), null);

	private String name;
	private KeyCodeWithModifiers primaryDefault, secondaryDefault;

	private KeyboardAction(String name, KeyCodeWithModifiers primaryDefault, KeyCodeWithModifiers secondaryDefault) {
		this.name = name;
		this.primaryDefault = primaryDefault;
		this.secondaryDefault = secondaryDefault;
	}

	public String getName() {
		return name;
	}

	public KeyCodeWithModifiers getPrimaryDefault() {
		return primaryDefault;
	}

	public KeyCodeWithModifiers getSecondaryDefault() {
		return secondaryDefault;
	}
}