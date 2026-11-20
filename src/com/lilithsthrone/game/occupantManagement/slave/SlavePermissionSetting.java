package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.87
 * @version 0.3.9.2
 * @author Innoxia
 */
public enum SlavePermissionSetting {

	// General/Misc.:
	
	GENERAL_SILENCE(false, "Сайленс", "Запретить этой рабыне говорить. [style.italics(Пока это разрешение активно, рабыня будет считаться немой.)]"),
	GENERAL_CRAWLING(false, "Ползание", "Запретить этой рабыне ходить, заставляя её передвигаться на четвереньках."),
	GENERAL_HOUSE_FREEDOM(false, "Свобода в доме", "Даровать этой рабыне свободу перемещаться по дому Lilaya в свободное время."),
	GENERAL_OUTSIDE_FREEDOM(false, "Свобода на улице", "Даровать этой рабыне свободу выходить из дома Lilaya в свободное время."),
	
	
	// Behaviour:
	
	BEHAVIOUR_WHOLESOME(false, "Благообразность", "Заставить эту рабыню вести себя с вами любяще и благообразно. [style.italics(Это сработает только в том случае, если вы ей нравитесь или она послушна.)]"),
	BEHAVIOUR_PROFESSIONAL(false, "Профессионализм", "Заставьте эту рабыню вести себя профессионально при общении с вами. [style.italics(Это сработает только в том случае, если она к вам расположена или послушна.)]"),
	BEHAVIOUR_STANDARD(true, "Стандартный", "Не давайте этой рабыне никаких указаний относительно того, как она должна вести себя рядом с вами."),
	BEHAVIOUR_SEDUCTIVE(false, "Соблазнительный", "Заставьте эту рабыню вести себя изысканно и соблазнительно при общении с вами. [style.italics(Это сработает только в том случае, если она к вам расположена или послушна.)]"),
	BEHAVIOUR_SLUTTY(false, "Шаловливый", "Заставьте эту рабыню вести себя вульгарно и распутно при общении с вами. [style.italics(Это сработает только в том случае, если она к вам расположена или послушна.)]"),

	
	// Sex:
	
	SEX_LUBE_PILL(false, "Лубрикант-таблетки", "Давайте этой рабыне таблетки-лубриканты, благодаря чему всё её тело будет покрыто смазкой во время секс-сцен.") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_lubrication"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.LUBE_PILL);
		}
	},
	SEX_MASTURBATE(false, "Мастурбация", "Разрешить этой рабыне мастурбировать."),
	SEX_INITIATE_SLAVES(false, "Начать секс", "") {
		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Разрешить этой рабыне проявлять инициативу в сексе с любой другой рабыней, у которой включено разрешение «Секс-игрушка». Она будет проявлять инициативу только с теми рабынями, к которым испытывает влечение, а если у неё нет разрешения «Изнасилователь», то она будет проявлять инициативу только с теми, кто испытывает влечение к ней в ответ. Она будет заниматься сексом только с теми рабынями, которых сможет найти: либо работая с ними в одном месте, либо имея разрешение «Домашняя свобода», либо если обе обладают разрешением «Свобода на улице», либо и то, и другое вместе.";
			}
			return "Разрешить этой рабыне проявлять инициативу в сексе с любой другой рабыней, у которой включено разрешение «Секс-игрушка». Она будет проявлять инициативу только с теми рабынями, к которым испытывает влечение и которые испытывают влечение к ней в ответ. Она будет заниматься сексом только с теми рабынями, которых сможет найти: либо работая с ними в одном месте, либо имея разрешение «Домашняя свобода», либо если обе обладают разрешением «Свобода на улице», либо и то, и другое вместе.";
		}
		@Override
		public int getAdditionalDescriptionLines() {
			return 6;
		}
	},
	SEX_INITIATE_PLAYER(false, "Использовать тебя", "") {
		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Разрешить этой рабыне использовать тебя для сексуальной разрядки. Это позволит ей предлагать тебе секс в любое время, а если у неё есть разрешение «Изнасилователь», то ты не сможешь отвергнуть её притязания. Ей также понадобятся «Домашняя свобода» и/или «Свобода на улице», чтобы иметь возможность найти тебя внутри особняка или на улочках Dominion.";
			}
			return "Позволяет этой рабыне использовать вас для сексуальной разрядки. Это позволит ей предлагать вам секс в любое время. Также ей потребуются «Домашняя свобода» и/или «Уличная свобода», чтобы иметь возможность найти вас внутри особняка или в переулках Dominion.";
		}
		@Override
		public int getAdditionalDescriptionLines() {
			return 3;
		}
	},
	SEX_RAPIST(false, "Изнасиловательница",
			"Позволяет этой рабыне игнорировать сексуальные предпочтения других, включая вас. Если у этой рабыни есть негативное влечение к фетишу '"+Fetish.FETISH_NON_CON_DOM.getName(null)+"' или если ей нравится партнер, но у партнера нет положительного влечения к фетишу '"+Fetish.FETISH_NON_CON_SUB.getName(null)+"', то даже с этим разрешением она не станет насиловать своего партнера.") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return Main.game.isNonConEnabled();
		}
		@Override
		public int getAdditionalDescriptionLines() {
			return 3;
		}
	},
	SEX_RECEIVE_SLAVES(false, "Секс-игрушка", "Позволяет любой из ваших рабынь с включенным разрешением «Инициировать секс» использовать эту рабыню для сексуальной разрядки."),
	SEX_SAVE_VIRGINITY(true, "Сохранить девственность", "Не позволяет другим рабыням лишать эту рабыню девственности во время секса."),
	SEX_IMPREGNATED(false, "Сука-разводчица", "Разрешить этому рабыне забеременеть во время сексуальных актов с любым другим рабом, у которого включено разрешение «Самец-раб».") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return !character.isDoll();
		}	
	},
	SEX_IMPREGNATE(false, "Самец-раб", "Разрешить этой рабыне оплодотворять любого другого раба, у которого включено разрешение «Самка для разведения», во время сексуальных актов.") {
		@Override
		public boolean isAvailableForCharacter(GameCharacter character) {
			return !character.isDoll();
		}	
	},

	
	// Pills:

	PILLS_NO_PILLS(true, "Без таблеток", "Не давать этой рабыне никаких таблеток, изменяющих фертильность, что обеспечит естественный шанс на наступление беременности."),
	
	PILLS_PROMISCUITY_PILLS(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_sterility").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Держать эту рабыню на [#ITEM_innoxia_pills_sterility.getNamePlural(false)], что значительно снижает как её фертильность, так и вирильность.");
		}
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		}
	},
	
	PILLS_VIXENS_VIRILITY(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Держать эту рабыню на [#ITEM_innoxia_pills_fertility.getNamePlural(false)], что значительно повышает как её фертильность, так и вирильность.");
		}
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.VIXENS_VIRILITY);
		}
	},

	PILLS_BROODMOTHER(false, "", "") {
		@Override
		public String getName() {
			return Util.capitaliseSentence(ItemType.getItemTypeFromId("innoxia_pills_broodmother").getName(false));
		}
		@Override
		public String getDescription() {
			return UtilText.parse("Держать эту рабыню на [#ITEM_innoxia_pills_broodmother.getNamePlural(false)], что колоссально повышает как её фертильность, так и вирильность, а также [style.colourExcellent(удваивает)] количество зачатых ею потомков.");
		}
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_broodmother"), character, false);
		}
		@Override
		public void applyEffectsOnRemoval(GameCharacter character) {
			character.removeStatusEffect(StatusEffect.BROODMOTHER_PILL);
		}
	},
	
	
	// Pregnancy:

	PREGNANCY_MOTHERS_MILK(false, "Материнское молоко", "Приказать этой рабыне регулярно употреблять материнское молоко во время беременности, благодаря чему она завершит вынашивание всего через несколько часов после того, как живот станет заметным."),
	PREGNANCY_ALLOW_BIRTHING(true, "Разрешить роды", "Разрешить этой рабыне ходить в родильную комнату Lilaya, чтобы рожать, когда ей это понадобится."),
	PREGNANCY_ALLOW_EGG_LAYING(true, "Разрешить откладывание яиц", "Разрешить этой рабыне ходить в родильную комнату Lilaya, чтобы откладывать любые имплантированные в неё яйца, когда ей это понадобится."),
	
	
	// Diet/Body size:
	
	FOOD_DIET_EXTREME(false,
			Util.capitaliseSentence(BodySize.ZERO_SKINNY.getName(false)),
			"Сильно ограничить количество еды для этой рабыни, что приведет к значительным ежедневным изменениям её размера тела, в конечном итоге превращая её в <b style='color:"+BodySize.ZERO_SKINNY.getColour().toWebHexString()+";'>"+BodySize.ZERO_SKINNY.getName(false)+"</b>."),
	
	FOOD_DIET(false,
			Util.capitaliseSentence(BodySize.ONE_SLENDER.getName(false)),
			"Ограничить количество еды для этой рабыни, что приведет к ежедневным изменениям её размера тела, в конечном итоге превращая её в <b style='color:"+BodySize.ONE_SLENDER.getColour().toWebHexString()+";'>"+BodySize.ONE_SLENDER.getName(false)+"</b>."),
	
	FOOD_NORMAL(true,
			Util.capitaliseSentence(BodySize.TWO_AVERAGE.getName(false)),
			"Давать этой рабыне достаточно еды, что приведет к ежедневным изменениям её размера тела, в конечном итоге превращая её в <b style='color:"+BodySize.TWO_AVERAGE.getColour().toWebHexString()+";'>"+BodySize.TWO_AVERAGE.getName(false)+"</b>."),
	
	FOOD_PLUS(false,
			Util.capitaliseSentence(BodySize.THREE_LARGE.getName(false)),
			"Давать этой рабыне дополнительный прием пищи каждый день, что приведет к ежедневным изменениям её размера тела, в конечном итоге превращая её в <b style='color:"+BodySize.THREE_LARGE.getColour().toWebHexString()+";'>"+BodySize.THREE_LARGE.getName(false)+"</b>."),
	
	FOOD_LAVISH(false,
			Util.capitaliseSentence(BodySize.FOUR_HUGE.getName(false)),
			"Обеспечить эту рабыню изобилием еды, что приведет к значительным ежедневным изменениям её размера тела, в конечном итоге превращая её в <b style='color:"+BodySize.FOUR_HUGE.getColour().toWebHexString()+";'>"+BodySize.FOUR_HUGE.getName(false)+"</b>."),

	
	// Exercise/Muscle:
	
	EXERCISE_FORBIDDEN(false,
			Util.capitaliseSentence(Muscle.ZERO_SOFT.getName(false)),
			"Запретить этой рабыне любые физические нагрузки, что приведет к сильному уменьшению её мышц каждый день, в конечном итоге превращая её в <b style='color:"+Muscle.ZERO_SOFT.getColour().toWebHexString()+";'>"+Muscle.ZERO_SOFT.getName(false)+"</b>."),
	
	EXERCISE_REST(false,
			Util.capitaliseSentence(Muscle.ONE_LIGHTLY_MUSCLED.getName(false)),
			"Не давать этой рабыне никаких физических упражнений, что приведет к ежедневным изменениям её мышечной массы, в конечном итоге превращая её в <b style='color:"+Muscle.ONE_LIGHTLY_MUSCLED.getColour().toWebHexString()+";'>"+Muscle.ONE_LIGHTLY_MUSCLED.getName(false)+"</b>."),
	
	EXERCISE_NORMAL(true,
			Util.capitaliseSentence(Muscle.TWO_TONED.getName(false)),
			"Назначьте этой рабыне умеренное количество упражнений, что будет приводить к ежедневному изменению мышечной массы, со временем делая её <b style='color:"+Muscle.TWO_TONED.getColour().toWebHexString()+";'>"+Muscle.TWO_TONED.getName(false)+"</b>."),
	
	EXERCISE_TRAINING(false,
			Util.capitaliseSentence(Muscle.THREE_MUSCULAR.getName(false)),
			"Дайте этой рабыне режим тренировок, что будет приводить к ежедневному изменению мышечной массы, со временем делая её <b style='color:"+Muscle.THREE_MUSCULAR.getColour().toWebHexString()+";'>"+Muscle.THREE_MUSCULAR.getName(false)+"</b>."),
	
	EXERCISE_BODY_BUILDING(false,
			Util.capitaliseSentence(Muscle.FOUR_RIPPED.getName(false)),
			"Дайте этой рабыне режим изнурительных упражнений, что будет приводить к значительному росту мышц каждый день, со временем делая её <b style='color:"+Muscle.FOUR_RIPPED.getColour().toWebHexString()+";'>"+Muscle.FOUR_RIPPED.getName(false)+"</b>."),
	
	
	// Cleanliness:
	
	CLEANLINESS_WASH_CLOTHES(true, "Стирать одежду", "Прикажите этой рабыне следить за тем, чтобы её одежда всегда была чистой и выстиранной."),
	CLEANLINESS_WASH_BODY(true, "Мыть тело", "Прикажите этой рабыне следить за чистотой своего тела, чтобы её отверстия всегда были свободны от спермы."),
	CLEANLINESS_WASH_THOROUGH(false, "Устранять запахи", "Когда эта рабыня будет мыться, она также будет смывать любые мускусные запахи, которыми она может быть помечена."),
	
	
	// Sleeping:
	SLEEPING_DEFAULT(true, "Спать когда угодно", "Приказывать этой рабыне спать, когда ей вздумается: для дневных рас это будет ночью, а для ночных — днем.") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.recalculateSleepHours();
		}
	},
	SLEEPING_NIGHT(false, "Спать ночью", "Приказывать этой рабыне спать ночью. Это не окажет на неё ни положительного, ни отрицательного эффекта.") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.recalculateSleepHours();
		}
	},
	SLEEPING_DAY(false, "Спать днем", "Приказывать этой рабыне спать днем. Это не окажет на неё ни положительного, ни отрицательного эффекта.") {
		@Override
		public void applyEffectsOnAddition(GameCharacter character) {
			character.recalculateSleepHours();
		}
	},
	
	;
	
	private String name;
	private String description;
	private boolean defaultValue;
	
	private SlavePermissionSetting(boolean defaultValue, String name, String description) {
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getAdditionalDescriptionLines() {
		return 0;
	}
	
	public boolean isDefaultValue() {
		return defaultValue;
	}
	
	public boolean isAvailableForCharacter(GameCharacter character) {
		return true;
	}
	
	public void applyEffectsOnAddition(GameCharacter character) {
	}

	public void applyEffectsOnRemoval(GameCharacter character) {
	}
}
