package com.lilithsthrone.game.dialogue.companions;

/**
 * @since 0.4.4.1
 * @version 0.4.9.1
 * @author Anonymous-BCFED
 */
public enum OccupantSortingMethod {
    NONE("none", "Сбросить порядок сортировки, установив значения по умолчанию."),
    
    NAME("name", "Сортировать рабынь по алфавиту (по имени)."),

    FEMININITY("femininity", "Сортировать рабынь по уровню женственности."),
    
    RACE("раса", "Сортировать рабынь по алфавиту (по названию расы)."),
    
    ROOM("комната", "Сортировать рабынь по алфавиту (по названию комнаты)."),
    
    VALUE("value", "Сортировать рабынь по их денежной стоимости.");
    
	
	private String name;
	private String sortingDescription;

	private OccupantSortingMethod(String name, String sortingDescription) {
		this.name = name;
		this.sortingDescription = sortingDescription;
	}

	public String getName() {
		return name;
	}

	public String getSortingDescription() {
		return sortingDescription;
	}
}