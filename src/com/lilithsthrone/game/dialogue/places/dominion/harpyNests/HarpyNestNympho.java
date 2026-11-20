package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.8
 * @version 0.4.3.2
 * @author Innoxia
 */
public class HarpyNestNympho {

	public static final DialogueNode HARPY_NEST_NYMPHO_TALK = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[pc.speech(Чего я хочу,)] — говоришь ты, — [pc.speech(хочу, чтобы ты взяла свое гнездо под контроль. Исполнители с трудом поддерживают порядок, а виной тому — члены твоего гнезда!)]</p><p>[harpyNympho.speech(Ну, это не <i>моя</i> вина, если некоторым моим игрушкам хочется немного развлечься где-то еще!)] [harpyNympho.name] отвечает. [harpyNympho.speech(И вообще, ты тут как бы мешаешь, так что разворачивайся и иди приставай к кому-нибудь другому! [harpyNymphoCompanion.Name]! Почему бы тебе не показать этой грубой [pc.race] выход!)]</p><p>Когда [harpyNymphoCompanion.name] делает шаг вперед, ты понимаешь, что [harpyNympho.name] тебя слушать не собирается. Тебе придется либо придумать другой способ убедить этих гарпий успокоиться, либо усмирить их силой.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Королева нимф", "Тебе становится её жалко — эта матриарх раз за разом трахается только с одними и теми же самцами. Покажи ей, как ведет себя настоящая нимфоманка!", HARPY_NEST_NYMPHO_QUEEN,
						null, null, Util.newArrayListOfValues(Perk.NYMPHOMANIAC), Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nymphoPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_NYMPHO_LOLLIPOP), false, true));
						
						if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
						}
					}
				};
					
			} else if (index == 2) {
				return new Response("Усмирить силой",
						"Если ты хочешь, чтобы эти гарпии расслабились, похоже, тебе придется сделать это силой..."
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						HARPY_NEST_NYMPHO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Уйти", "Сказать [harpyNympho.name], что ты вернешься позже.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что сейчас не лучшее время для открытого противостояния, ты разворачиваешься и уходишь. Уходя, ты слышишь, как [harpyNympho.name] жалуется [harpyNymphoCompanion.name]: [harpyNympho.speech(Ну, это была пустая трата времени! Пошли, [harpyNymphoCompanion.name], на этот раз я хочу, чтобы ты использовал мой рот!)]</p><p>Игнорируя её слова, ты продолжаешь путь через платформу и вскоре снова оказываешься на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_UGLY = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>Осознав, что тебе придется заставить [harpyNympho.name] взять свое гнездо под контроль, ты решаешь спровоцировать ее на конфронтацию: [pc.speech(Ну, если ты не собираешься успокаивать свое гнездо, можешь хотя бы согласиться возвести вокруг него стены? Не дело каждый раз видеть такую уродливую гарпию, как ты, когда я прохожу мимо.)]</p><p>[harpyNympho.Name] издает яростный крик и, хлопая крыльями, вопит своей спутнице: [harpyNympho.speech([harpyNymphoCompanion.Name]! Хватайте [pc.herHim]! Хватайте [pc.herHim]! Этот ублюдок! [pc.She] сказал, что я уродливая!)]</p><p>Выглядя почти такой же разъяренной, как [harpyNympho.name], [harpyNymphoCompanion.name] бросается вперед, стремясь угодить своей матриарху и начиная яростную атаку.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[harpyNymphoCompanion.Name] спешит исполнить волю своей матриарха!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyNymphoCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_QUEEN = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() { // Mock her for only having one partner at once, she gets flustered, you describe multiple harpies fucking her, she breaks and falls to her knees admitting that you're better than she is
			return "<p>[pc.speech(Так что, ты трахаешь только одну из своих маленьких игрушек за раз?)] — спрашиваешь ты насмешливым тоном. [pc.speech(Потому что ты ведь понимаешь, что могла бы делать гораздо больше, верно?)]</p><p>На мгновение [harpyNympho.name] кажется, что она застигнута врасплох, и она запинается, отвечая тебе: [harpyNympho.speech(Н-ну, то есть, мне нравится, чтобы каждая гарпия чувствовала себя особенной!)]</p><p>Ропот разговоров, окружавший тебя до этого момента, начинает затихать, и ты понимаешь, что толпы самцов-гарпий вокруг внимательно прислушиваются. С того самого момента, как ты ступила в гнездо [harpyNympho.namePos], ты чувствовала на своей прекрасной фигуре взгляды её стаи, а теперь, громко критикуя сексуальные навыки их матриарха, ты завладела их полным вниманием.</p><p>Приняв самую соблазнительную позу, какую только смогла изобразить, ты продолжаешь: [pc.speech(Знаешь, ты припрятала всех этих великолепных гарпий здесь для личного пользования, но готова трахать их только по одной?! Это как-то несправедливо, вы все так не считаете?)]</p><p>Когда ты задаешь этот последний вопрос, обращаясь к толпе, вся стая отвечает дружным одобрительным криком. Твои прекрасные черты и мощная аркана-аура явно производят на этих гарпий сильное впечатление, что в сочетании с такими открытыми разговорами о сексе заставило их перейти на твою сторону.</p><p>[harpyNympho.speech(Но, ч-что же мне тогда делать?)] — нервно спрашивает [harpyNympho.name]. [harpyNympho.speech(Я и не осознавала, что поступаю несправедливо...)]</p><p>[pc.speech(О, есть много вещей, которые ты могла бы делать,)] — говоришь ты, подходя к ней ближе. [pc.speech(Например, позволить использовать твою киску, задницу и рот одновременно. Или позволить своей стае устроить групповой секс с твоей шлюшливой щелочкой.)]</p><p>С этими словами ты наклоняешься и сжимаешь обнаженную киску [harpyNympho.namePos]. Она издает короткий писк и, прижимаясь к тебе, стонет: [harpyNympho.speech(Да... я сделаю это... А что еще?)]</p><p>[pc.speech(Думаю, тебе уже очевидно, что нужно делать,)] — стонешь ты ей на ухо. [pc.speech(Встань на колени и признай, что здесь главная — я.)]</p><p>Понимая, что её стая заворожена твоими словами, [harpyNympho.name] падает перед тобой на колени. Глядя на тебя широко раскрытыми, полными похоти глазами, она стонет: [harpyNympho.speech("+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", пожалуйста, научи меня! Я успокою стаю для тебя! Пожалуйста, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", скажи, что мне делать!)]</p><p>Вы невольно издаете короткий смешок, видя, как матриарх подчиняется вам. [pc.speech(Хорошая девочка! Отныне ты будешь делать именно то, что я скажу, поняла?!)]</p><p>[harpyNympho.Name] подбирается ближе, продолжая издавать тихие стоны в ответ, [harpyNympho.speech(Да, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Я буду делать всё, что ты скажешь! Пожалуйста, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", у меня есть кое-что для тебя!)]</p><p>Достав леденец в форме члена, [harpyNympho.name] протягивает его вам. [harpyNympho.speech(, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", это сделает тебя похожей на меня! Е-если ты хочешь быть похожей на меня, конечно...)] Вы берете леденец, признавая покорность [harpyNympho.namePos], но не уверены, решитесь ли вы его использовать...</p><p>[pc.speech(Хорошая девочка!)] — говорите вы. [pc.speech(А теперь ты успокоишь свое гнездо, верно?)]</p><p>[harpyNympho.speech(Да, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Я смогу снять весь их стресс, если позволю им использовать меня всех разом!)]</p><p>Благодаря вашей собственной одержимости сексом, вашей мощной арканной ауре и вашей привлекательности, вам удалось покорить гнездо [harpyNympho.namePos] без единой битвы! Глядя на всё еще стонущую матриарха, вы задумываетесь, не стоит ли устроить ей индивидуальное занятие...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Секс", "Заняться доминирующим сексом с [harpyNympho.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyNympho.class)),
						null,
						null), HARPY_NEST_NYMPHO_AFTER_SEX, "<p>Стремясь утолить [harpyNympho.namePos] жажду секса, вы тянетесь вниз и хватаете её за крылья. Подняв её на ноги, вы делаете шаг вперед и глубоко целуете её в [harpyNympho.lips+].</p><p>[harpyNympho.Name] отвечает на ваш властный жест возбужденным визгом и, с энтузиазмом обхватив вашими крыльями вашу спину, страстно начинает отвечать на ваш поцелуй...</p>");
						
			} else if (index == 0) {
				return new Response("Уйти", "Решить уйти.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что ваша работа здесь окончена, вы разворачиваетесь и отправляетесь обратно тем же путем. Уходя, вы слышите, как [harpyNympho.name] подзывает своих гарпий: [harpyNympho.speech(Вы слышали нашего нового лидера! Давайте попробуем сразу троих!)]</p><p>Услышав это, вы ухмыляетесь и продолжаете путь через главную платформу, вскоре снова оказавшись на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>Понимая, что [harpyNympho.name] не желает слушать доводы разума, вы выдвигаете свое последнее требование: [pc.speech(Либо ты прямо сейчас успокоишь свое гнездо, либо я заставлю тебя это сделать!)]</p><p>[harpyNympho.Name] издает гневный крик и кричит своей спутнице: [harpyNympho.speech([harpyNymphoCompanion.Name], вышвырни уже этого [pc.race]! <i>Никто</i> не смеет так со мной разговаривать и оставаться безнаказанным!)]</p><p>[harpyNymphoCompanion.name] тут же бросается вперед, стремясь угодить своей матриарху и начиная яростную атаку.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[harpyNymphoCompanion.Name] спешит исполнить волю своей матриарха!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyNymphoCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT_BEAT_BF = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[harpyNympho.Name] издает гневный вопль, когда [harpyNymphoCompanion.name] падает на пол, поверженная. Вы замечаете, что многие окружающие гарпии нервно переглядываются, а некоторые начинают перебираться на вашу сторону платформы. Похоже, они готовятся поддержать вас, если вам удастся одолеть их матриарха.</p><p>У вас не так много времени, чтобы размышлять о переменчивой натуре этих гарпий, как [harpyNympho.Name] внезапно бросается вперед, выкрикивая: [harpyNympho.speech(Ты за это заплатишь!)]</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[harpyNympho.Name] выглядит разъяренной, когда обрушивает на вас свою атаку!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyNympho.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_FIGHT_BEAT_NYMPHO = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[harpyNympho.NamePos], еще мгновение назад ликовавшая и выкрикивавшая слова поддержки своей матриарху, полностью замолкает, видя, как та тяжело оседает на пол. Шагая вперед, вы возвышаетесь над тяжело дышащей массой розовых перьев и слышите эротичный стон, вырывающийся из уст [harpyNympho.namePos], когда она приподнимается на колени. [harpyNympho.speech(Т-ты такой... могущественный...)]</p><p>[pc.speech(Именно так,)] отвечаете вы, [pc.speech(Мне плевать, как вы здесь, в Гнездах, обычно выбираете вожака стаи; теперь здесь главный я!)]</p><p>Ваша аркана-аура явно оказывает сильное воздействие на [harpyNympho.name], и вы слышите ее очередной похотливый стон, когда она отвечает: [harpyNympho.speech(Д-да,  "+(Main.game.getPlayer().isFeminine()?"mistress":"master")+"! Я сделаю всё, как ты просишь!)]</p><p>Видя, как их матриарх покоряется тебе, остальные гарпии падают на колени, склоняясь перед тобой, пока ты отдаешь приказы, [pc.speech(А теперь все успокоятся, ясно?! Никаких больше раздоров и никаких нападений на путешественников в гнездах гарпий!)]</p><p>Хором раздается восторженное согласие толпы, и ты ловишь себя на ухмылке, глядя сверху вниз на побежденную матриарха у своих ног. Подойдя ближе, [harpyNympho.name] протягивает розовый леденец в форме члена. [harpyNympho.speechNoEffects("+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Пожалуйста, возьми один из моих особенных леденцов! С ним ты станешь похожа на меня! Е-если, конечно, ты хочешь быть похожей на меня...)] Ты берешь леденец в знак признания покорности [harpyNympho.namePos], но не уверен, решишься ли ты его съесть...</p><p>[pc.speech(Хорошая девочка!)] — говоришь ты. [pc.speech(А теперь ты успокоишь свое гнездо, верно?)]</p><p>[harpyNympho.speech(Да, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Я буду держать их в узде!)]</p><p>Благодаря твоей победе над [harpyNympho.name] и силе твоей арканной ауры, тебе удалось усмирить это некогда беспокойное гнездо! Глядя на матриарха, ты задумываешься, не стоит ли тебе публично доказать всем этим гарпиям, кто здесь главный...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Секс", "Заняться доминирующим сексом с [harpyNympho.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyNympho.class)),
						null,
						null), HARPY_NEST_NYMPHO_AFTER_SEX, "<p>Желая показать этому гнезду, кто здесь главная, вы наклоняетесь и хватаете [harpyNympho.namePos] за крылья. Подняв её на ноги, вы делаете шаг вперед и глубоко целуете её в [harpyNympho.lips+].</p><p>[harpyNympho.Name] отвечает на ваш властный жест возбужденным визгом и, с энтузиазмом обхватив вашими крыльями вашу спину, страстно начинает отвечать на поцелуй...</p>");
							
				} else if (index == 0) {
					return new Response("Уйти", "Сказать [harpyNympho.name], что ты вернешься позже.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что ваша работа здесь окончена, вы разворачиваетесь и направляетесь назад тем же путем. Уходя, вы слышите, как [harpyNympho.name] подзывает своих гарпий: [harpyNympho.speech(Теперь она наша новая предводительница! Проявите уважение!)]</p><p>Услышав это, вы ухмыляетесь и продолжаете путь через главную платформу, вскоре снова оказавшись на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_NYMPHO_AFTER_SEX = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyNympho.NamePos]";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyNympho.class)) >= Main.game.getNpc(HarpyNympho.class).getOrgasmsBeforeSatisfied()) {
				return "<p>Когда вы отстраняетесь от [harpyNympho.name], она оседает на пол, совершенно измотанная своим оргазмом."+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyNympho.class)) > 1?"s":"")+". Окружающие гарпии, наблюдавшие за всем этим, преклоняют колени в знак покорности, пока ты заканчиваешь с их матриаршей.</p>";
			} else {
				return "<p>Когда вы отстраняетесь от [harpyNympho.name], она оседает на пол, издавая отчаянный скулеж, осознав, что вы с ней закончили. Её пернатые руки ныряют между ног, и она начинает неистово мастурбировать, стремясь завершить то, что начали вы. Окружающие гарпии, наблюдавшие за всем этим, преклоняют колени в знак подчинения, пока вы заканчиваете с их матриархом.</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Уйти", "Насладившись весельем, ты решаешь уйти.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Поставив [harpyNympho.name] на место, вы возвращаетесь назад по платформе и вскоре снова оказываетесь на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
