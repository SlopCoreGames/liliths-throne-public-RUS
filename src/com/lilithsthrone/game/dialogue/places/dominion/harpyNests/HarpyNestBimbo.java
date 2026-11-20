package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
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
public class HarpyNestBimbo {

	public static final DialogueNode HARPY_NEST_BIMBO_TALK = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[pc.speech(Я здесь, чтобы поговорить с тобой о недавних беспорядках в этих краях. Энфорсерам всё труднее поддерживать порядок, и было бы очень кстати, если бы ты заставила свою стаю немного успокоиться,)] — пытаешься ты объяснить, но [bimboHarpy.name] лишь закатывает глаза и в ответ раздраженно цокает языком.</p><p>[bimboHarpy.speechNoEffects(Типа, это вообще-то не моя проблема!)] — говорит она, пренебрежительно махнув рукой, прежде чем продолжить: [bimboHarpy.speechNoEffects(Как насчет того, чтобы, типа, отвалить со своим глупым нытьем! У меня, типа, прям так и чешутся руки позвать [bimboHarpyCompanion.name], чтобы она преподала тебе урок прямо сейчас!)]</p><p>Похоже, [bimboHarpy.name] не собирается прислушиваться к доводам разума. Тебе придется либо придумать другой способ убедить гарпий успокоиться, либо усмирить их силой.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Бимбо-королева", "Ну, ты, типа, пытался поговорить и всё такое, но эта сучка тебя не слушает! Тебе определенно стоит убедить гнездо, что ты должна стать их королевой!", HARPY_NEST_BIMBO_QUEEN,
						Util.newArrayListOfValues(Fetish.FETISH_BIMBO), null, null, Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimboPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_BIMBO_LOLLIPOP), false, true));
						
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
						HARPY_NEST_BIMBO_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Уйти", "Скажи [bimboHarpy.name], что вернешься позже.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что сейчас не лучшее время для этого, вы разворачиваетесь и направляетесь вниз по лестнице. Уходя, вы слышите, как [bimboHarpy.name] ворчит на своих гарпий: [bimboHarpy.speechNoEffects(Фу! Ну и что это вообще было?!)]</p><p>Не обращая внимания на её слова, вы продолжаете спускаться по ступеням и переходите через главную платформу, быстро оказываясь на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_UGLY = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>Раздражённый тем, как эта глупая бимбо с вами обращается, вы решаете проучить её её же методами. [pc.speech(Может, хватит всех оскорблять, и просто наденешь мешок на своё уродливое лицо?)]</p><p>[bimboHarpy.NamePos] заливается ярко-красным цветом и с яростным криком взывает к своей спутнице: [bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]! Тащи [pc.herHim]! Тащи [pc.herHim]! Никто не смеет так со мной разговаривать!)]</p><p>Выглядя почти такой же разъярённой, как [bimboHarpy.name], [bimboHarpyCompanion.name] бросается вперёд, стремясь угодить своей матриарху и начиная яростную атаку.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[bimboHarpyCompanion.Name] спешит исполнить волю своей матриархи!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyBimboCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_QUEEN = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>Насытившись гонором этой сучки, ты делаешь шаг вперед, глядя на [bimboHarpyCompanion.name] во время своей речи: [pc.speechNoEffects(Типа, тебе реально приходится слушать это весь день? Ты, типа, сейчас начнешь делать то, что я скажу, поняла?! А теперь, типа, иди и преклони колени перед своей новой королевой!)]</p><p>Сочетание твоих прекрасных черт и мощной арканной ауры, похоже, оказывает сильное влияние на этих гарпий. То, что ты говоришь в той же манере, что и их матриарх, оказывается достаточным, чтобы начать склонять последовательниц [bimboHarpy.namePos] на твою сторону. Прежде чем она успевает опомниться, [bimboHarpyCompanion.name] бросается вперед, чтобы исполнить твой приказ.</p><p>[bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name]?! Типа, какого черта ты творишь? А ну вернись сюда!)] — кричит [bimboHarpy.name].</p><p>[bimboHarpyCompanion.Name] замирает между вами двумя, в замешательстве переводя взгляд с одной на другую. Ты замечаешь, что остальные члены стаи не спешат защищать свою матриарха, а лишь наблюдают, ожидая, что решит делать [bimboHarpyCompanion.name].</p><p>[pc.speechNoEffects([bimboHarpyCompanion.Name]!)] — выкрикиваешь ты. [pc.speechNoEffects(Иди сюда и преклони предо мной колени, типа, <i>прямо сейчас</i>!)]</p><p>Не совсем понимая, как реагировать, [bimboHarpy.name] с недоверием наблюдает, как её спутница бросается вперед и послушно опускается на колени перед тобой. Остальные гарпии быстро следуют её примеру, признавая в тебе более могущественную и, что критически важно, более привлекательную самку, чем их нынешняя матриарх.</p><p>Когда осознание произошедшего начинает доходить до неё, [bimboHarpy.name] быстро бежит вперед, оттесняя [bimboHarpyCompanion.name], и занимает её место, опускаясь перед тобой на колени. Подвинувшись ближе, она заглядывает тебе в глаза. [bimboHarpy.speechNoEffects(Типа, <i>я</i> самая красивая здесь! Н-ну, кроме тебя, конечно! Позволь мне стать твоей личной питомицей, пожалуйста!)]</p><p>Усмехаясь своей новой гарпии-бимбо, ты говоришь ей то, что она хочет услышать: [pc.speechNoEffects(Хорошая питомица! Ты, типа, такая супер-умная! Я знала, что ты поймешь свое новое место!)]</p><p>[bimboHarpy.Name] подползает ближе, издавая покорные тонкие звуки мяуканья и глядя на тебя снизу вверх, пока ты продолжаешь: [pc.speechNoEffects(Короче, типа, у меня нет времени и всего такого, чтобы рулить этим гнездом, так что ты будешь, типа, заниматься всей этой фигней. Тебе просто нужно помнить, кто тут на самом деле главная! Твое первое задание — типа, заставить всех этих гарпий успокоиться и всё такое! Если я услышу, что они капризничали, тебя, типа, понизит в звании с моей питомицы, поняла?!)]</p><p>[bimboHarpy.speechNoEffects(Да, госпожа!)] — восклицает [bimboHarpy.name]. [bimboHarpy.speechNoEffects(Я возьму их под контроль! Оо! Оо! Еще! Госпожа! У меня есть для тебя особенная леденцовая палочка!)]</p><p>Доставая розово-белый вихревой леденец, [bimboHarpy.name] протягивает его тебе. [bimboHarpy.speechNoEffects(Это, типа, то, что я даю всем новым членам стаи! Это, типа, превратит тебя в одну из нас!)] Ты берешь леденец в знак признания преданности [bimboHarpy.namePos], но не уверен, решишься ли ты его использовать...</p><p>Благодаря твоей личности бимбо и отличной внешности тебе удалось покорить гнездо [bimboHarpy.namePos] без боя! Глядя на матриарха-бимбо, ты задумываешься, не стоит ли тебе публично доказать всем этим гарпиям, кто здесь на самом деле заправляет...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Секс", "Заняться доминантным сексом с [bimboHarpy.name] на глазах у всей её стаи.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyBimbo.class)),
						null,
						null), HARPY_NEST_BIMBO_AFTER_SEX, "<p>Жаждая поставить [harpyBimbo.name] на место перед её ближайшим окружением, вы наклоняетесь и хватаете её за крылья. Подняв её на ноги, вы делаете шаг вперед и глубоко целуете её в [harpyBimbo.lips+], вызывая череду восторженных смешков у окружающих гарпий-бимбо.</p><p>[harpyBimbo.Name] отвечает на ваш властный жест, обвивая крыльями вашу спину и с упоением прижимаясь к вам всем телом, страстно отвечая на поцелуй...</p>");
						
			} else if (index == 0) {
				return new Response("Уйти", "Скажи [bimboHarpy.name], что вернешься позже.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что ваша работа здесь окончена, вы разворачиваетесь и направляетесь вниз по лестнице. Уходя, вы слышите, как [bimboHarpy.name] кричит на гарпий: [bimboHarpy.speechNoEffects(Так, тихо! <i>[pc.SheIs]</i> теперь здесь новый лидер, ясно?!)]</p><p>Услышав это, вы ухмыляетесь и продолжаете спускаться по ступеням через главную платформу, вскоре оказавшись на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>Насытившись дерзостью [bimboHarpy.namePos], вы выдвигаете свое последнее требование: [pc.speech(Либо ты успокоишь свое гнездо, либо я заставлю тебя это сделать!)]</p><p>Лицо [bimboHarpy.NamePos] заливается пунцовой краской, и она выкрикивает своей спутнице: [bimboHarpy.speechNoEffects([bimboHarpyCompanion.Name], преподай урок этому [pc.race]! Типа, <i>никто</i> не смеет так со мной разговаривать!)]</p><p>С криком [bimboHarpyCompanion.name] бросается вперед, стремясь угодить своей матриарху и начиная яростную атаку.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[bimboHarpyCompanion.Name] спешит исполнить волю своей матриархи!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyBimboCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT_BEAT_GF = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[bimboHarpy.Name] издает яростный вопль, когда [bimboHarpyCompanion.name] падает на пол, поверженная. Вы замечаете, что многие окружающие гарпии нервно переглядываются, а некоторые начинают перебираться на вашу сторону платформы. Похоже, они пытаются подстраховаться и готовятся поддержать вас, если вам удастся доказать свою силу.</p><p>У вас не так много времени на раздумья о переменчивой натуре этих гарпий, как [bimboHarpy.Name] внезапно бросается вперед с криком: [bimboHarpy.speech(Ты за это заплатишь!)]</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[bimboHarpy.Name] выглядит разъяренной, когда бросается на вас в атаку!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyBimbo.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_FIGHT_BEAT_BIMBO = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[bimboHarpy.NamePos] внутренний круг глупых гарпий, которые еще мгновение назад ликовали и кричали, мгновенно замолкает, видя, как их матриарх оседает на пол. Сделав шаг вперед, вы смотрите на ее жалкую фигуру и слышите, как глупая гарпия издает эротичный стон, поднимаясь на колени: [bimboHarpy.speechNoEffects(Аах! Типа, ты такой мощный и всё такое! К-кто ты?!)]</p><p>[pc.speech(Твой новый лидер,)] — отвечаете вы. [pc.speech(Мне плевать, как вы обычно выбираете вожака стаи; теперь здесь главный я!)]</p><p>Ваша арканная аура явно оказывает сильное воздействие на [bimboHarpy.name]: она издает еще один похотливый стон, отвечая: [bimboHarpy.speechNoEffects(Д-да, "+(Main.game.getPlayer().isFeminine()?"mistress":"master")+"! Я б-буду, типа, очень послушной для тебя!)]</p><p>Видя, как их матриарх покоряется вам, остальные гарпии падают на колени, склоняясь перед вами, пока вы отдаете приказы: [pc.speech(Теперь все успокоятся, ясно?! Никаких больше раздоров и никаких нападений на путешественников в гнездах гарпий!)]</p><p>Ваши слова, кажется, доходят до них, встречая хор восторженного согласия. Подбираясь к вам всё ближе, [bimboHarpy.name] протягивает розово-белую леденцовую спираль. [bimboHarpy.speechNoEffects(\"}}"+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Если ты, типа, хочешь выглядеть как мы, просто лизни это! Обещаю, что я всё проконтролирую! Мы ведь все будем послушными, правда, девчонки?!)]</p><p>Когда глупые гарпии радостно выкрикивают согласие, ты наклоняешься и берешь леденец в знак признания [bimboHarpy.namePos], но не уверен, решишься ли ты его использовать...</p><p>Благодаря твоей победе над [bimboHarpy.name] и силе твоей арканой ауры, тебе удалось покорить гнездо [bimboHarpy.namePos]! Глядя на глупую матриаршую, ты задаешься вопросом: стоит ли тебе публично доказать всем этим гарпиям, кто здесь на самом деле главный...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Секс", "Заняться доминантным сексом с [bimboHarpy.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyBimbo.class)),
						null,
						null), HARPY_NEST_BIMBO_AFTER_SEX, "<p>Жаждая поставить [harpyBimbo.name] на место перед её ближайшим окружением, вы наклоняетесь и хватаете её за крылья. Подняв её на ноги, вы делаете шаг вперед и глубоко целуете её в [harpyBimbo.lips+], вызывая череду восторженных смешков у окружающих гарпий-бимбо.</p><p>[harpyBimbo.Name] отвечает на ваш властный жест, обвивая крыльями вашу спину и с упоением прижимаясь к вам всем телом, страстно отвечая на поцелуй...</p>");
						
			} else if (index == 0) {
				return new Response("Уйти", "Скажи [bimboHarpy.name], что вернешься позже.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что ваша работа здесь окончена, вы разворачиваетесь и направляетесь вниз по лестнице. Уходя, вы слышите, как [bimboHarpy.name] кричит на гарпий: [bimboHarpy.speechNoEffects(Так, тихо! <i>[pc.SheIs]</i> теперь здесь новый лидер, ясно?!)]</p><p>Услышав это, вы ухмыляетесь и продолжаете спускаться по ступеням через главную платформу, вскоре оказавшись на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_BIMBO_AFTER_SEX = new DialogueNode("Гнездо гарпий", ".", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyBimbo.NamePos]";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyBimbo.class)) >= Main.game.getNpc(HarpyBimbo.class).getOrgasmsBeforeSatisfied()) {
				return "<p>Когда ты отстраняешься от [bimboHarpy.name], она оседает на пол, совершенно измотанная своим оргазмом"+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyBimbo.class)) > 1?"s":"")+". Окружающие гарпии, наблюдавшие за всем этим, преклоняют колени в знак покорности, пока ты заканчиваешь с их матриаршей.</p>";
			} else {
				return "<p>Когда ты отстраняешься от [bimboHarpy.name], она оседает на пол, издавая отчаянный скулеж, когда понимает, что ты с ней закончил. Ее пернатые руки метнулись между ног, и она начинает неистово мастурбировать, пытаясь завершить то, что ты начал. Окружающие гарпии, наблюдавшие за всем этим, преклоняют колени в знак покорности, пока ты заканчиваешь с их матриаршей.</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Уйти", "Насладившись весельем, ты решаешь уйти.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Показав [bimboHarpy.name] ей её место, вы спускаетесь по лестнице и, пройдя обратно через нижнюю платформу, быстро оказываетесь на окраине гнезда [bimboHarpy.namePos].</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
