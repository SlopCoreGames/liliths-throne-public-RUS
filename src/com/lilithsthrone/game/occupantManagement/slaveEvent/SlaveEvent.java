package com.lilithsthrone.game.occupantManagement.slaveEvent;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.2.2
 * @version 0.4.10.7
 * @author Innoxia
 */
public enum SlaveEvent {
	
	WASHED_BODY(SlaveEventType.MISCELLANEOUS, "Чистое тело", "[npc.Name] умылся и очистил тело [npc.her].") {
		public void applyEffects(GameCharacter character) {
			character.washAllOrifices(true);
			character.cleanAllDirtySlots(true);
			character.calculateStatusEffects(0);
		}
	},
	
	WASHED_CLOTHES(SlaveEventType.MISCELLANEOUS, "Чистая одежда", "[npc.Name] постирал одежду [npc.her].") {
		public void applyEffects(GameCharacter character) {
			character.cleanAllDirtySlots(false); //TODO should they be cleaning slots?
			character.cleanAllClothing(true, false);
			character.calculateStatusEffects(0);
		}
	},

	MIDDAY_UPDATE(SlaveEventType.MISCELLANEOUS, "Полуденные новости", ""),
	
	DAILY_UPDATE(SlaveEventType.MISCELLANEOUS, "Ежедневные новости", ""),

	SLAVE_SEX(SlaveEventType.SEX, "[style.boldSex(Секс)]", "[npc.Name] переспал с другой рабыней..."),
	
	SLAVE_BONDING(SlaveEventType.BONDING, "[style.boldAffection(Привязанность)]", "[npc.Name] провел время, укрепляя связь с другой рабыней..."),
	
	GAVE_BIRTH(SlaveEventType.SEX, "[style.boldExcellent(Родила)]", "Lilaya помогла [npc.name] родить."),

	GAVE_BIRTH_INCUBATION(SlaveEventType.SEX, "[style.boldExcellent(Отложила яйца)]", "Lilaya помогла [npc.name] отложить яйца, которые инкубировала [npc.she]."),
	
	
	// Jobs:

//	JOB_CUM_MILKED("Cum Milked", "[npc.NamePos] [npc.cum+] was milked."),
	JOB_MILK_MILKED(SlaveEventType.JOB, "Доен", ""),
//	JOB_MILK_CROTCH_MILKED("Udders Milked", "[npc.Name] was milked of [npc.her] [npc.crotchMilk+]."),
//	JOB_GIRLCUM_MILKED("Girlcum Milked", "[npc.NamePos] [npc.girlcum+] was milked."),
	
	//TODO
//	JOB_CLEANING("Cleaning Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_COOKING("Cooking Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_LAB_ASSISTANT("Lilaya Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_LIBRARIAN("Librarian Fun", "<i>Placeholder event.</i>"),
//
//	JOB_OFFICE("Office Fun", "<i>Placeholder event.</i>"),
	
	JOB_TEST_SUBJECT(SlaveEventType.JOB, "Подопытный", "[npc.Name] подверглась трансформирующим экспериментам Lilaya..."),
	
	JOB_PUBLIC_STOCKS(SlaveEventType.JOB, "Закована в колодки", "[npc.Name] была закована в общественные колодки в Переулке Рабов..."),
	
	JOB_PROSTITUTE(SlaveEventType.JOB, "Проститутка", "[npc.Name] работала проституткой в Поцелуе Ангела."),

//	JOB_IDLE("Resting", "<i>Placeholder event.</i>"),
	;
	
	private SlaveEventType type;
	private String name;
	private String description;
	
	private SlaveEvent(SlaveEventType type, String name, String description) {
		this.type = type;
		this.name = name;
		this.description = description;
	}

	public SlaveEventType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	// For use in overriding.
	public void applyEffects(GameCharacter character) {
	}
}
