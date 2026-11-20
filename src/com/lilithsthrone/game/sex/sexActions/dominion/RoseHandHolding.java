package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class RoseHandHolding {
	
	public static final SexAction PLAYER_HAND_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Массаж рук";
		}
		@Override
		public String getActionDescription() {
			return "Массировать руки Роуз.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Взяв руку Роуз в одну свою ладонь, вы осторожно прижимаете [pc.fingers+] другой руки к тыльной стороне её ладони, растирая и массируя её нежную кожу.",
					"Вы берете Роуз за руку и медленными, размеренными движениями начинаете делать ей нежный массаж.",
					"Сжимая руку Роуз в своей, вы начинаете слегка надавливать, наслаждаясь ощущением её идеальной, безупречной кожи во время ласкового массажа.",
					"С мягкой, нежной заботой вы начинаете массировать руки Роуз, издавая тихие [pc.moans], когда чувствуете мягкость её ангельской кожи.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_INTERLOCKING_FINGERS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Переплести пальцы";
		}
		@Override
		public String getActionDescription() {
			return "Переплетите свои пальцы с пальцами Роуз.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Взяв руку Роуз в свою, вы переплетаете свои пальцы с её, и с легким нажимом похотливо сжимаете её ладонь.",
					"Одним уверенным движением вы захватываете руку Роуз, просовывая свои пальцы между её пальцами и полностью заполняя манящие промежутки между ними.",
					"Издав решительный [pc.moan], ты просовываешь свои пальцы между пальцами Роуз, сжимая её ладонь и показывая ей, насколько ты развратен.",
					"С похотливым [pc.arm] ты обхватываешь идеальную руку Роуз своей, быстро переплетая свои пальцы с её, изо всех сил стараясь сдержать отчаянный вздох.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_NAIL_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Почесывание ногтями";
		}
		@Override
		public String getActionDescription() {
			return "Мягко погладь ногти Роуз.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Ногти Роуз накрашены нежно-розовым цветом, и ты не можешь устоять перед желанием провести кончиками пальцев по их безупречной поверхности.",
					"С невероятным развратом ты проводишь каждым своим пальцем по соответствующему ногтю на руке Роуз, довольно вздыхая и наслаждаясь её идеальным маникюром.",
					"Роуз не лгала, когда говорила, что тщательно ухаживает за руками, и, проводя кончиками пальцев по её безупречному маникюру, ты гадаешь, сколько же времени у неё ушло на то, чтобы они стали такими красивыми.",
					"Нежно-розовый цвет, которым Роуз накрасила ногти, идеально дополняет бледный тон её кожи, и когда ты проводишь пальцами вверх и вниз по их гладкой поверхности, ты поражаешься тому, насколько они совершенны.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction FINGER_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Поглаживание пальцев";
		}
		@Override
		public String getActionDescription() {
			return "Мягко погладь пальцы Роуз.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Сжимая в руке один из тонких женских пальцев Роуз, вы медленно ведете кончиками пальцев вверх и вниз по его длине, поражаясь тому, насколько мягкая у нее идеальная, безупречная кожа.",
					"Решительным движением руки вы обхватываете пальцы Роуз, проводя своими пальцами вверх и вниз по их длине и не в силах сдержать экстатический [pc.moan].",
					"Скользя своими пальцами по пальцам Роуз, вы издаете тихий скулящий стон от возбуждения, начиная ласкать ее мягкую, ангельскую кожу.",
					"Проводя кончиками пальцев вверх и вниз по каждому из тонких женских пальцев Роуз, вы пытаетесь осознать, насколько же идеально сформированы ее руки.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_LICK_PALM = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Лизать ладони";
		}
		@Override
		public String getActionDescription() {
			return "Лизать ладони Роуз.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Поднеся одну из рук Роуз к своему рту, вы прижимаете свой [pc.lips+] к нежной коже ее ладони, а затем ведете своим [pc.tongue] по ее мягкой коже.",
					"Поднеся руку Роуз к губам, вы проводите долгим, медленным языком по ее мягкой ладони, издавая похотливый [pc.moan], когда ощущаете слабый вкус соленого пота, смешанного с ее женским ароматом.",
					"Взяв руку Роуз, вы подносите ее к своему рту, а затем мягко слизываете влагу с ее нежной ладони. Легкий вкус пота и женских духов бьет вам в [pc.tongue], и вы не можете сдержать дрожащий [pc.moan].",
					"С легким [pc.moan] вы подносите ангельскую руку Роуз к своему [pc.lips+], а затем нежно и с любовью слизываете влагу с ее мягкой ладони.");
		}
		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_START_SUCKING = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Сосать пальцы";
		}
		@Override
		public String getActionDescription() {
			return "Начать сосать пальцы Роуз.";
		}
		@Override
		public String getDescription() {
			return "Проявляя невероятную степень похоти, обычно присущую лишь самым скандальным онлайн-изданиям, вы решительно переходите на новый уровень. Сжав в своей руке ангельскую женскую ладонь Роуз, вы медленно подносите её к своему рту. Предчувствуя ваши намерения и в нетерпении ожидая результата этого безумного поступка, Роуз прислоняется к стене, чтобы не упасть, стоная и тяжело дыша, пока её пальцы всё ближе подбираются к вашей необъятной пасти.<br/><br/>Когда ваше горячее дыхание касается её чувствительных кончиков пальцев, вы на мгновение задумываетесь, не зашли ли вы слишком далеко, но легкого пути назад уже нет, и вы решительно проталкиваете пальцы Роуз сквозь губы себе в рот. Тем самым вы окончательно причисляете себя к рядам самых развратных сексуальных девиантов, понимая, что уже никогда не будете прежним, и начинаете жадно сосать пальцы Роуз.";
		}
	};
	
	public static final SexAction PLAYER_GENTLE_SUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Нежно сосать";
		}
		@Override
		public String getActionDescription() {
			return "Нежно сосать пальцы Роуз.";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Вы нежно посасываете и целуете женские пальчики Роуз, отчаянно проводя языком по её тонким пальцам и наслаждаясь их мягким вкусом, в котором смешались пот и женский парфюм.",
					"Нежно посасывая идеальные пальцы Роуз, вы по очереди обводите языком каждый из её пальчиков, издавая [pc.moans+], пока доставляете оральное удовольствие её руке.",
					"Решив устроить Роуз незабываемое времяпрепровождение, ты нежно посасываешь и целуешь каждый её женственный пальчик, наслаждаясь ощущением столь порочного удовольствия.",
					"Облепив [pc.lips+] вокруг ангельских пальцев Роуз, ты нежно посасываешь и целуешь её безупречные пальчики, издавая тихие [pc.moaning] звуки, когда ощущаешь на языке едва уловимый вкус её пота.");
		}
	};
	
	public static final SexAction PLAYER_INTENSE_SUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Интенсивно сосать";
		}
		@Override
		public String getActionDescription() {
			return "Интенсивно посасывать пальцы Роуз.";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Прежде чем ты успеваешь опомниться, ты полностью растворяешься в порочном удовольствии от посасывания пальцев Роуз и с похотливым криком отчаянно ускоряешь темп своего интенсивного сосания.",
					"Интенсивным сосательным движением ты сжимаешь свой [pc.lips+] вокруг пальцев Роуз, после чего начинаешь неистово целовать и лизать её нежные пальчики.",
					"Ты решаешь ускорить темп своего похотливого сосания пальцев и с отчаянным криком жадно обводишь языком каждый её пальчик по очереди.",
					"Пальцы Роуз мягкие и теплые у тебя во рту, и, больше не в силах сдерживаться, ты неистово начинаешь посасывать и целовать её безупречные пальчики.");
		}
	};
	
	public static final SexAction PLAYER_STOP_SUCKING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Перестать сосать";
		}
		@Override
		public String getActionDescription() {
			return "Перестать посасывать пальцы Роуз.";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Не в силах больше справляться с тем, насколько похотливым стало твое посасывание пальцев, ты вытягиваешь из рта мягкие, женственные пальчики Роуз. Одинокая нить вязкой слюны медленно тянется из твоего рта, на мгновение соединяя твои губы с кончиками пальцев Роуз, прежде чем порваться и упасть на пол под вами.");
		}
	};
	
	// Rose:
	
	public static final SexAction PARTNER_MOAN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Стоны";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Роуз издает отчаянный крик, ",
					"С невероятно похотливым стоном Роуз вскрикивает, ",
					"Отчаянно стоная, Роуз сцепляет свои [rose.eyes+] с твоими, ",
					"С отчаянным, дрожащим стоном Роуз вскрикивает, "));
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[rose.speech(~Аах!~ Да! Не останавливайся!)]",
					"[rose.speech(Да! Да! ~Аах!~ Возьми меня!)]",
					"[rose.speech(О да! ~Аах!~ Продолжай!)]",
					"[rose.speech(О! ~Аах!~ Продолжай! Да!)]",
					"[rose.speech(~Аах!~ Да, да, да!!!)]",
					"[rose.speech(~Аах!~ О да! Продолжай! ~Аах!~)]"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_PANTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Тяжелое дыхание";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Кажется, Роуз не может делать ничего, кроме как тяжело дышать и [rose.moan], пока ты продолжаешь ласкать её ладони.",
					"Твоё мастерство в удержании её рук оказывается слишком сильным для Роуз, которая не может ничего делать, кроме как прерывисто дышать от дикого возбуждения.",
					"Глаза Роуз на мгновение закатываются, и, когда её язык вываливается изо рта, она издает долгий, отчаянный стон.",
					"С отчаянным [rose.moan] Роуз начинает тяжело дышать и вздыхать, и ты видишь, как её [rose.tongue+] вываливается изо рта, пока она пытается справиться с тем количеством удовольствия, которое ты ей даришь.");
		}
	};
	
	public static final SexAction PARTNER_TABLE_BRACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Опереться";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Роуз частично оседает на стол рядом с собой, отчаянно опираясь на него, когда её колени начинают подкашиваться.",
					"С легким [rose.moan] Роуз прислоняется к стене рядом, стараясь удержать равновесие и справиться с интенсивным возбуждением.",
					"Роза прислоняется спиной к стене рядом с собой, явно не в силах стоять самостоятельно, и издает дрожащий [rose.moan].",
					"Опираясь на стоящий рядом стол, Роза использует мебель как опору; она явно с трудом удерживает равновесие из-за всепоглощающего наслаждения, которое дарят ей твои мастерские навыки игры пальцами.");
		}
	};
	
	public static final SexAction PARTNER_SLIDE_FINGERS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Скользить пальцами";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"С похотливым [rose.moan] Роза начинает вводить и выводить пальцы в твоем рту, заставляя тебя испытывать такой уровень разврата, который ты не мог вообразить даже в самых смелых мечтах.",
					"Роза начинает медленно вводить и выводить пальцы в твоем рту, и ты издаешь отчаянный скулящий звук, чувствуя, как ее мягкая кожа трется о твой [pc.lips+].",
					"Вводя и выводя пальцы в твоем рту, Роза слегка закручивает их вокруг твоего языка, после чего издает отчаянный [rose.moan].",
					"В беспрецедентном проявлении вопиющей сексуальной девиации Роза начинает медленно вводить и выводить пальцы в твоем рту, позволяя тебе чувствовать, как ее невероятно мягкая кожа скользит туда-сюда по твоему [pc.lips+].");
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())>=Main.sex.getCharacterPerformingAction().getOrgasmsBeforeSatisfied()
					&& Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=Main.game.getPlayer().getOrgasmsBeforeSatisfied();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "С удовлетворенным вздохом Роза высвобождается из твоих объятий и, нежно глядя тебе в глаза, извиняется: [rose.speech(Думаю, я больше не выдержу! Мне нужно немного отдохнуть, но нам определенно нужно повторить это как-нибудь еще!)]";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "Перестать смотреть";
			}
			return Main.sex.isMasturbation()
					?"Прекратить мастурбацию"
					:"Прекратить секс";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "Отступить и перестать наблюдать за разворачивающейся перед тобой сценой секса.<br/>Это все равно [style.boldSex(применит все соответствующие эффекты)] к остальным участникам, как если бы сцена секса произошла полностью.";
			}
			return Main.sex.isMasturbation()
					?"Закончить сеанс мастурбации."
					:"Перестать заниматься сексом с [npc2.name].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isPlayerAbleToStopSex()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getDescription() {
			return "Насладившись сполна, ты [pc.step] назад и прекращаешь секс...";
		}
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation() || Main.sex.isSpectator(Main.game.getPlayer())
					?SexParticipantType.SELF
					:SexParticipantType.NORMAL;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
		@Override
		public String applyEndEffects(){
			return "";
		}
	};
}
