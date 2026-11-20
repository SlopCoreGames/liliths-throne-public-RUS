package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
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
public class HarpyNestDominant {

	public static final DialogueNode HARPY_NEST_DOMINANT_TALK = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[pc.speech(I just want to talk to you about the recent unrest in the Harpy Nests. The Enforcers are having a hard time keeping the peace, and it appears as though some of your flock are to blame,)] you try to explain, but [dominantHarpy.name] stomps her clawed foot on the ground, before cutting you off with an angry grunt.</p><p>[dominantHarpy.speech(What the fuck?! How <i>dare</i> you come here and talk to me like that!)] she shouts, flapping her wings in irritation as she continues, [dominantHarpy.speech(Unless you want to become [dominantHarpyCompanion.namePos] next fuck-toy, you'd better fuck off right now! And be thankful that I'm in a good mood!)]</p><p>It doesn't look like [dominantHarpy.name] is going to listen to you. You'll either have to think of another way to convince these harpies to calm down, or make them calm down by using force.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Узурпировать трон", "Твое терпение лопнуло. Пора показать этой сучке, кто здесь на самом деле главный!", HARPY_NEST_DOMINANT_QUEEN,
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, null, Femininity.FEMININE_STRONG, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantPacified);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_DOMINANT_PERFUME), false, true));
						
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
						HARPY_NEST_DOMINANT_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else if (index == 0) {
				return new Response("Уйти", "Решить покинуть гнездо [harpyDominant.namePos].", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что сейчас не лучшее время для противостояния [harpyDominant.name], вы разворачиваетесь и поспешно уходите от подиума. Уходя, вы слышите, как [harpyDominant.name] рычит на [harpyDominantCompanion.name], [harpyDominant.speechNoEffects(Что это, блять, сейчас было?! Да плевать! [harpyDominantCompanion.Name], можешь продолжать играть со своей секс-игрушкой...)]</p><p>Игнорируя её слова, вы продолжаете путь через платформу и вскоре снова оказываетесь на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_UGLY = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>В голове внезапно всплывает едкий ответ, и прежде чем вы успеваете опомниться, произносите его вслух: [pc.speech(Всё, чего я хочу — это больше никогда не видеть твою уродливую рожу!)]</p><p>[harpyDominant.Name] издает яростный крик и, хлопая крыльями, кричит своей спутнице: [harpyDominant.speech([harpyDominantCompanion.Name]! Разнеси [pc.herHim] в щепки! Вбей [pc.herHim] в подчинение! Эта гребаная сука! Хватай [pc.herHim]!)]</p><p>Выглядя почти такой же разъяренной, как [harpyDominant.name], [harpyDominantCompanion.name] бросается вперед, стремясь угодить своей матриарху и начиная яростную атаку.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[harpyDominantCompanion.Name] спешит исполнить волю своей матриарха!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyDominantCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_QUEEN = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[pc.speech(Если ты еще хоть раз <i>раз</i> так со мной заговоришь, я заставлю твою маленькую питомицу использовать тебя в качестве своей следующей игрушки! И, кстати о тебе,)] — кричишь ты, глядя на [harpyDominantCompanion.Name], — [pc.speech(тебе лучше иметь чертовски <i>чертовски</i> вескую причину, почему ты до сих пор не преклонила колени перед своей новой королевой!)]</p><p>С того самого момента, как ты ступил в гнездо [harpyDominant.namePos], стало ясно, что твои прекрасные черты лица и мощная аркана аура производят сильное впечатление на ее стаю. Теперь, когда ты заговорила в той же властной манере, что и их матриарх, ты слышишь нарастающий вокруг ропот беспокойства. Не желая гневить тебя, [dominantHarpyCompanion.name] внезапно бросается вперед, а затем падает перед тобой на колени.</p><p>[harpyDominant.speech([harpyDominantCompanion.Name]! Наглая сучка! А ну вернись сюда, <i>сию же минуту</i>!)] — визжит [harpyDominant.name].</p><p>[harpyDominantCompanion.Name] подходит ближе к тебе, издавая тихий скулящий звук и покорно склоняя голову. [harpyDominantCompanion.speech(М-"+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", я сделаю всё, что пожелаешь!)]</p><p>Не совсем понимая, как реагировать, [harpyDominant.name] с недоверием наблюдает за тем, как остальные гарпии следуют примеру [harpyDominantCompanion.namePos]. Словно единое целое, они слетаются к тебе, склоняя головы в признании того, что ты — более могущественный и, что крайне важно, более привлекательный лидер, чем их нынешняя матриарх.</p><p>Видя, как её стая ускользает, [harpyDominant.name] внезапно спрыгивает со своего пьедестала. Гневно шагая к тебе, она хватает [harpyDominantCompanion.namePos] за ворот платья и резким рывком оттягивает её от тебя. И как раз в тот момент, когда ты собираешься приказать своим новым последовательницам защитить тебя, [harpyDominant.name] бросается вперед, падая перед тобой на колени.</p><p>[harpyDominant.speech("+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"!)] [harpyDominant.name] кричит. [harpyDominant.speech(Я прослежу, чтобы твое стадо вело себя прилично! Пожалуйста, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", позволь мне быть полезной тебе!)]</p><p>Вы не можете сдержать насмешливого смешка, глядя, как матриарх подчиняется вам. Выбирая между созданием новой стаи или подчинением вашей власти, [harpyDominant.name] явно выбрала второе, и вы вознаграждаете её Покорность, говоря ей то, что она хочет услышать, [pc.speech(Хорошая девочка! Отныне ты будешь делать именно то, что я скажу, понятно?!)]</p><p>[harpyDominant.Name] подбирается ближе, издавая покорное тонкое мяуканье в ответ, [harpyDominant.speech(Да, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Буду исполнять все твои приказы! Буду твоей послушной девочкой! Пожалуйста, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", прими эти особые духи как знак моего смирения перед тобой!)]</p><p>Доставая флакон темно-красных духов, [harpyDominant.name] протягивает их тебе. [harpyDominant.speech(Если хочешь, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", они превратят тебя в одну из нас!)] Ты принимаешь духи в знак признания [harpyDominant.namePos] покорности, но не уверен, решишься ли ты ими воспользоваться...</p><p>[pc.speech(Хорошая девочка!)] — говоришь ты. [pc.speech(А теперь ты должна успокоить это гнездо! Если я услышу хоть <i>один</i> случай непослушания этих гарпий, у тебя будут серьезные неприятности!)]</p><p>[harpyDominant.speech(Да, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Я проучу их! Больше не беспокойся о них!)]</p><p>Благодаря твоему властному характеру и привлекательной внешности, тебе удалось подчинить гнездо [harpyDominant.namePos] без единой схватки! Глядя на теперь уже покорную матриарха, ты задумываешься, не стоит ли тебе публично доказать всем этим гарпиям, кто здесь главный...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Секс", "Заняться доминирующим сексом с [harpyDominant.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyDominant.class)),
						null,
						null), HARPY_NEST_DOMINANT_AFTER_SEX, "<p>Стремясь поставить [harpyDominant.name] на место на глазах у всей твоей новой стаи, ты наклоняешься и хватаешь её за крылья. Подняв её на ноги, ты делаешь шаг вперед и глубоко целуешь её в [harpyDominant.lips+].</p><p>[harpyDominant.Name] отвечает на твой властный жест покорным тихим скулением и, обхватив тебя крыльями со спины, начинает страстно отвечать на поцелуй...</p>");
						
			} else if (index == 0) {
				return new Response("Уйти", "Решить уйти.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Поняв, что твоя работа здесь окончена, ты разворачиваешься и начинаешь возвращаться тем же путем, которым пришел. Уходя, ты слышишь, как [harpyDominant.name] орет на одну из гарпий: [harpyDominant.speech(<i>[pc.SheIs]</i> теперь здесь новая предводительница, ты, никчемная сучка! [harpyDominantCompanion.Name]! Похоже, у тебя появилась новая игрушка для траха!)]</p><p>Услышав это, ты ухмыляешься и продолжаешь путь через главную платформу, вскоре снова оказавшись на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>Не имея другого выбора, ты смотришь на [harpyDominant.name], выдвигая свое последнее требование: [pc.speech(Либо ты прямо сейчас успокоишь свое гнездо, либо я заставлю тебя это сделать!)]</p><p>[harpyDominant.Name] издает яростный крик и выкрикивает своей спутнице: [harpyDominant.speechNoEffects([harpyDominantCompanion.Name], проучи эту дерзкую [pc.race]! <i>Никто</i> не смеет так со мной разговаривать и оставаться безнаказанным!)]</p><p>С криком [harpyDominantCompanion.name] бросается вперед, стремясь угодить своей матриарху и начиная яростную атаку.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[harpyDominantCompanion.Name] спешит исполнить волю своей матриарха!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyDominantCompanion.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT_BEAT_PET = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[harpyDominant.Name] издает яростный крик, когда [harpyDominantCompanion.name] падает на пол, поверженная. Вы замечаете, что многие окружающие гарпии нервно переглядываются, а некоторые начинают перебираться на вашу сторону платформы. Похоже, они пытаются подстраховаться и готовятся поддержать вас, если вам удастся доказать свою силу.</p><p>У вас не так много времени, чтобы размышлять о непостоянстве этих гарпий, как [harpyDominant.Name] внезапно спрыгивает со своего пьедестала с криком: [harpyDominant.speech(Ты, сука! Ты за это заплатишь!)]</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться",
						"[harpyDominant.Name] выглядит разъяренной, когда бросается на вас в атаку!"
						+ (Main.game.isBadEndsEnabled() && Main.game.getPlayer().isAbleToHaveRaceTransformed()
								?"<br/>[style.boldBadEnd(ПЛОХОЙ КОНЕЦ:)] Если ты проиграешь этот бой, гарпии больше никогда тебя не отпустят!"
								:""),
						Main.game.getNpc(HarpyDominant.class));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_FIGHT_BEAT_DOMINANT = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "<p>[harpyDominant.NamePos] внутренний круг гарпий молча наблюдает за тем, как их матриарх оседает на пол. Сделав шаг вперед, вы смотрите сверху вниз на ее жалкую фигуру и слышите тихий стон, вырывающийся из ее губ, когда она приподнимается на колени. [harpyDominant.speechNoEffects(Т-ты такая сильная... Кто ты?!)]</p><p>[pc.speech(Твоя новая "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+",)] — отвечаете вы, поворачиваясь к окружающим гарпиям. [pc.speech(У кого-нибудь из вас есть с этим проблемы?!)]</p><p>Ваша арканная аура явно оказывает сильное воздействие на [harpyDominant.name]: она издает очередной похотливый стон, окликая вас. [harpyDominant.speech(М-"+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Мои девочки будут послушными, не волнуйся!)]</p><p>Видя, как их матриарх подчиняется тебе, остальные гарпии склоняют головы, когда ты отдаешь приказы, [pc.speech(А теперь все успокоятся, ясно?! Никаких больше раздоров и никаких нападений на путешественников в пределах Гнезд Гарпий!)]</p><p>Ты невольно ухмыляешься, когда гарпии дружно выкрикивают согласие. Теперь, когда стая находится под твоим полным контролем, [harpyDominant.Name] подбирается ближе, издавая покорный тонкий писк. [harpyDominant.speech(М-"+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Я буду делать всё, что прикажешь! Я буду твоей послушной девочкой! Пожалуйста, прими эти особые духи как знак моего смирения перед тобой!)]</p><p>Достав флакон темно-красных духов, [harpyDominant.name] протягивает его тебе. [harpyDominant.speech(Если хочешь, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+", они превратят тебя в одну из нас!)] Ты принимаешь духи в знак признания покорности [harpyDominant.namePos], но не уверен, решишься ли ты ими воспользоваться...</p><p>[pc.speech(Хорошая девочка!)] — говоришь ты. [pc.speech(А теперь ты заставишь всех остальных в этом гнезде успокоиться! Если я услышу хоть <i>один</i> отчет о том, что эти гарпии проказничают, у тебя будут серьезные неприятности!)]</p><p>[harpyDominant.speech(Да, "+(Main.game.getPlayer().isFeminine()?"Госпожа":"Хозяин")+"! Я их проучу! Больше о них не беспокойся!)]</p><p>Благодаря твоей победе над [harpyDominant.name] и силе твоей арканной ауры, тебе удалось покорить гнездо [harpyDominant.namePos]! Глядя сверху вниз на теперь уже покорную матриарха, ты задаешься вопросом: стоит ли тебе публично доказать всем этим гарпиям, кто здесь главный...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				return new ResponseSex("Секс", "Заняться доминирующим сексом с [harpyDominant.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(HarpyDominant.class)),
						null,
						null), HARPY_NEST_DOMINANT_AFTER_SEX, "<p>Стремясь поставить [harpyDominant.name] на место на глазах у всей твоей новой стаи, ты наклоняешься и хватаешь её за крылья. Подняв её на ноги, ты делаешь шаг вперед и глубоко целуешь её в [harpyDominant.lips+].</p><p>[harpyDominant.Name] отвечает на твой властный жест покорным тихим скулением и, обхватив тебя крыльями со спины, начинает страстно отвечать на поцелуй...</p>");
						
			} else if (index == 0) {
				return new Response("Уйти", "Скажи [harpyDominant.name], что вернешься позже.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Поняв, что твоя работа здесь окончена, ты разворачиваешься и начинаешь возвращаться тем же путем, которым пришел. Уходя, ты слышишь, как [harpyDominant.name] орет на одну из гарпий: [harpyDominant.speech(<i>[pc.SheIs]</i> теперь здесь новая предводительница, ты, никчемная сучка! [harpyDominantCompanion.Name]! Похоже, у тебя появилась новая игрушка для траха!)]</p><p>Услышав это, ты ухмыляешься и продолжаешь путь через главную платформу, вскоре снова оказавшись на окраине гнезда.</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_NEST_DOMINANT_AFTER_SEX = new DialogueNode("Гнездо гарпий", "", true) {

		@Override
		public String getLabel() {
			return "Гнездо [harpyDominant.NamePos]";
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyDominant.class)) >= Main.game.getNpc(HarpyDominant.class).getOrgasmsBeforeSatisfied()) {
				return "<p>Когда ты отстраняешься от [harpyDominant.name], она оседает на пол, совершенно измотанная своим оргазмом"+(Main.sex.getNumberOfOrgasms(Main.game.getNpc(HarpyDominant.class)) > 1?"s":"")+". Окружающие гарпии, наблюдавшие за всем этим, преклоняют колени в знак покорности, пока ты заканчиваешь с их матриаршей.</p>";
			} else {
				return "<p>Когда ты отстраняешься от [harpyDominant.name], она оседает на пол, издавая отчаянный скулеж, осознав, что ты с ней закончил. Ее пернатые руки метнулись между ног, и она начинает неистово мастурбировать, стремясь завершить то, что ты начал. Окружающие гарпии, наблюдавшие за всем этим, преклоняют колени в знак подчинения, когда ты заканчиваешь с их матриархом.</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Уйти", "Насладившись весельем, ты решаешь уйти.", DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>Показав [harpyDominant.name] ее место, ты отправляешься в путь по платформе, и не успеваешь оглянуться, как снова оказываешься на окраине гнезда...</p>");
					}
				};
					
			} else {
				return null;
			}
		}
	};
}
