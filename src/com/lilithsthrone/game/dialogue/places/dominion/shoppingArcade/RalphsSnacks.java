package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMRalphDiscount;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.82
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RalphsSnacks {
	
	private static void resetDiscountCheck() {
		// if 3 days have passed, reset discount:
		if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)) >= (60*24*3)){
			Main.game.getDialogueFlags().ralphDiscount=0;
		}
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Закуски от Ralph (Снаружи)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Войти", "«Закуски от Ralph» сейчас закрыты, так что, если вы хотите заглянуть внутрь, вам придется вернуться в часы работы.", null);
					}
					return new Response("Войти", "Войти в «Закуски от Ralph».", INTERIOR){
						@Override
						public void effects() {
							resetDiscountCheck();
						}
					};
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode INTERIOR = new DialogueNode("Закуски от Ralph", "-", true) {

		@Override
		public String getContent() {
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ralphIntroduced)) {
				return "<p>Вы толкаете дверь и входите внутрь; при вашем появлении звонит маленький колокольчик. [ralph.speech(Эй, привет! Если понадобится помощь — только спроси!)] — кричит вам из-за прилавка конько-мальчик.</p><p>Вы благодарите конько-мальчика, который, как вы полагаете, и есть Ralph, и начинаете осматривать его лавку. Большинство товаров не представляют собой ничего особенного — это обычная еда и напитки, которые можно встретить почти везде. Однако отличительной чертой этого магазина является специальный стеллаж с напитками и едой, пропитанными магией Арканы. Цены не указаны, вместо них на маленькой табличке написано: «Пожалуйста, обращайтесь к Ralph за помощью в выборе этих товаров».</p>";
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append("<p>Вы снова направляетесь к единственному месту в торговой галерее, где продают еду навынос. Снаружи это выглядит как старомодная кондитерская с большими стеклянными витринами, за которыми выставлено всевозможной экзотической еды и напитков. Над входом золотыми курсивными буквами выведено «Закуски от Ralph», и когда вы толкаете дверь и входите, звонит маленький колокольчик, возвещая о вашем прибытии.</p><p>"
						+ (Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0
								?"Когда вы входите в лавку, Ralph подмигивает вам и окликает из-за очень знакомой прилавка, [ralph.speech(А, ну надо же, неужели мой любимый постоянный клиент! Если понадобится помощь, ты знаешь, к кому обращаться!)]"
								:"Знакомый конь-мальчик кричит вам из-за прилавка: [ralph.speech(Снова привет! Если что-то понадобится, просто спроси!)]")
						+ "</p><p>Вы благодарите Ralph и начинаете осматривать его лавку. Большинство товаров не представляют собой ничего особенного — это обычная еда и напитки, которые можно найти где угодно. Однако отличительной чертой этого магазина является специальная витрина с пропитанными арканой расходниками. Цены не указаны, вместо них на маленькой табличке написано: «Пожалуйста, обращайтесь к Ralph за помощью с этими товарами».</p>");
					
				if(((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive()){
					UtilText.nodeContentSB.append("<p>"
									+ "<b>Ralph is giving you a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>скидка!</b></p>");
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Торговать с Ralph", "Подойти и расспросить Ralph об особых товарах на витрине.", Main.game.getNpc(Ralph.class)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						resetDiscountCheck();
					}
				};
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Скидка", "Спросить Ralph, можно ли получить скидку.", INTERIOR_ASK_FOR_DISCOUNT){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
							resetDiscountCheck();
						}
					};
					
				} else {
					return new Response("Скидка", "Вы сможете получить скидку у Ralph только в том случае, если он получит доступ к вашему рту!", null);
					
				}

			} else if (index == 3
					&& Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)
					&& Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LIPSTICK
					&& !Main.game.getPlayer().hasItemType(ItemType.CANDI_HUNDRED_KISSES)) {
					return new Response("Помада Candi",
							!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ralphAskedAboutHundredKisses)
								?"Спросить у Ralph, есть ли у него коробка конфет «Сотня поцелуев» и готов ли он её продать."
								:"Спросить у Ralph, продаёт ли он всё ещё коробку «Сотня поцелуев».",
							CANDI_LIPSTICK) {
						@Override
						public void effects() {
							resetDiscountCheck();
						}
					};

			} else if (index == 0) {
				return new Response("Уйти", "Покинуть лавку Ralph.", EXTERIOR){
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						resetDiscountCheck();
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_ASK_FOR_DISCOUNT = new DialogueNode("Закуски от Ralph", "-", true, true) {
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0){
				return "<p>Бросив взгляд на другую сторону лавки, вы видите, как Ralph весело машет вам из-за прилавка. Перед глазами вспыхивают образы его массивного лошадиного члена, и вы невольно издаете тихий стон, вспоминая, как он скользил глубоко в вашем горле... Как его широкая, раздутая головка прорывается сквозь ваши губы... И вкус его соленой предэякулята, когда слюна течет из вашего рта...</p><p>Знакомый голос внезапно вырывает вас из мечтаний: [ralph.speech(Эй, ты в порядке?)]</p><p>Вы были настолько поглощены своей фантазией, что не заметили, как Ralph подошел к вам, и, застигнутая врасплох, вы выпалили утвердительный ответ. Повернувшись к мускулистому коню-мальчику, вы замечаете, что его взгляд начал скользить вверх и вниз по вашему телу, и у вас возникает чувство, что он точно знает, о чем вы только что грезили. Посмотрев вниз, вы видите отчетливый бугорок, наливающимся между его ног, но прежде чем вы успеваете среагировать на растущую эрекцию Ralph, он делает шаг вперед, прижимая вас спиной к стене.</p><p>[ralph.speech(Хочешь еще глоточек?)] — лукаво спрашивает он, хитро ухмыляясь и наклоняясь чуть ближе. Почувствовав его горячее дыхание на своем лице, конь-мальчик продолжает: [ralph.speech(Позволь мне кончить тебе в это прелестное маленькое горлышко, и в течение нескольких дней я сделаю тебе скидку двадцать пять процентов на всё, что есть у меня в наличии.)]</p><p>Когда Ralph делает шаг вперед и прижимается своим горячим телом к вашему, вы изо всех сил пытаетесь выкинуть из головы образ его массивного, пульсирующего черного лошадиного члена, и когда палата в его брюках касается вашей ноги, вы выпаливаете свой ответ.</p>";
				
			}else{
				return "<p>У вас возникает чувство, что цены на многие из этих товаров завышены сверх всякой разумной наценки. Бросив взгляд на другую сторону лавки, вы видите, как Ralph весело машет вам рукой из-за прилавка. Воодушевленный его обезоруживающей улыбкой и дружелюбным лицом, вы решаете спросить его, можно ли получить скидку на некоторые из этих трансформирующих предметов, и направляетесь к нему.</p><p>Заметив ваше приближение, Ralph окликает вас: [ralph.speech(Привет! Могу я чем-нибудь вам помочь?)]</p><p>[pc.speech(Привет, я как раз хотел(а) узнать цены на некоторые из этих товаров,)] отвечаете вы.</p><p>Ralph бодро ведет вас к специальному стеллажу, и когда он называет вам цены, ваши подозрения подтверждаются. В то время как некоторые товары стоят разумных денег, более экзотические продукты питания обходятся довольно дорого. Глядя на дружелюбного лавочника, вы спрашиваете его, нет ли возможности немного снизить цены.</p><p>[ralph.speech(Хм,)] говорит он, поглядывая на вас, [ralph.speech(Полагаю, мы могли бы о чем-то договориться...)]</p><p>Вы замечаете, что его улыбка внезапно утратила дружелюбный вид, и пока его глаза блуждают по вашему телу, у вас возникает ощущение, что этот жеребец-мальчик затеял какие-то грязные мысли. Посмотрев вниз, вы видите отчетливый бугорок, образующийся между его ног, но прежде чем вы успеваете среагировать на растущую эрекцию Ralph, он делает шаг вперед, прижимая вас к стене.</p><p>[ralph.speech(Хочется попробовать?)] — лукаво спрашивает он, хитро ухмыляясь и наклоняясь к вам чуть ближе. Почувствовав его горячее дыхание на своем лице, жеребец-мальчик продолжает: [ralph.speech(Позволь мне кончить тебе в это прелестное маленькое горлышко, и в течение нескольких дней я сделаю тебе скидку двадцать пять процентов на всё, что есть у меня в наличии.)]</p><p>Когда Ralph делает шаг вперед и прижимается своим горячим телом к вашему, вы изо всех сил пытаетесь выкинуть из головы образ его массивного, пульсирующего черного конского члена, и когда бугорок в его штанах касается вашей ноги, вы выпаливаете свой ответ.</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Согласиться", "Согласиться на то, что говорит Ralph, и отсосать его член.", Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING),
						null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, true,
						new SMRalphDiscount(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB))),
						null,
						null,
						AFTER_SEX,
						"<p>"
							+ UtilText.parsePlayerSpeech("«Ладно, я сделаю это»,") + " — говорите вы, глядя на Ralph и видя, как его улыбка становится еще шире.</p><p>Он наклоняется ближе, и вы почти ожидаете, что он попытается вас поцеловать, но вместо этого он просто хватает вас за " + Main.game.getPlayer().getArmNameSingular() + " и начинает тащить обратно к своему столу. Пока он идет, он начинает инструктировать вас по поводу того, что сейчас произойдет.</p><p>"
							+ UtilText.parseSpeech("«Ты будешь стоять на коленях под моим столом вот здесь, и я не рассчитываю, что мне придется делать хоть какую-то работу, понятно?»", Main.game.getNpc(Ralph.class))
							+ " — спрашивает он, и когда вы отвечаете утвердительно, он продолжает: "
							+ UtilText.parseSpeech("«Это приличное заведение, так что если кто-то зайдет, ты должна молчать! За каждого покупателя, который тебя услышит, я вычту пять процентов из нашей сделки».", Main.game.getNpc(Ralph.class))
						+ "</p><p>К этому времени Ralph уже завел вас за прилавок, и вы видите, что под столешницей есть пустое пространство,"
							+(Main.game.getPlayer().isTaur()
								?". Даже учитывая размеры тела вашей нижней [pc.legRace], там как раз достаточно места, чтобы вы могли нырнуть внутрь,"
								:", достаточно просторного, чтобы вы могли там вполне комфортно стоять на коленях».")
							+ " Массивная передняя часть стола скрывает вас от остального магазина, и вы понимаете: если вести себя тихо, покупатели даже не заметят, что здесь происходит. Ralph кладет руки вам на плечи, и, чувствуя, что отступать уже поздно, вы позволяете ему опустить вас на колени. Подвинувшись назад, вы устраиваетесь под столом, и Ralph делает шаг вперед, поднося огромный бугор в своих брюках прямо к вашему лицу.</p><p>"
							+ UtilText.parseSpeech("И не забудь уделить внимание и моим яйцам,", Main.game.getNpc(Ralph.class)) + " ты слышишь его приказ.</p><p>Как раз в тот момент, когда ты собираешься ему ответить, раздается звон маленького колокольчика над входной дверью лавки, возвещающий о приходе покупателя. Ты слышишь, как Ralph выкрикивает дружелюбное приветствие, но при этом он подается бедрами вперед, давая понять, что хочет, чтобы ты поскорее приступила к делу. Места для маневров у тебя немного, и ты понимаешь, что для получения скидки тебе придется ограничиться одним лишь ртом.</p><p>Пока покупатель уходит в другую часть магазина, Ralph наклоняется и расстегивает брюки. Резким движением он стягивает их вместе с боксерами, и они сбиваются у него на лодыжках. Твои глаза расширяются, когда ты видишь, как гигантская, стремительно твердеющая лошадиная херь Ральфа поднимается, упираясь тебе в подбородок. Его огромные черные яйца свободно свисают у основания звериного ствола, и ты сглатываешь, предвкушая то, что сейчас произойдет...</p><p><b>Здесь</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>возле прилавка.</b> <b>Ты получишь</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>25%</b> <b>скидку.</b></p>"){
					@Override
					public void effects() {
						Main.game.getNpc(Ralph.class).setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getNpc(Ralph.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						SexFlags.customerAtCounter = false;
						SexFlags.customerTurnAppearance = 0;
					}
				};
				
			} else if (index == 2) {
				return new Response("Отказаться", "Скажи ему, что не готова на это.", INTERIOR_REFUSE_DISCOUNT_CONDITIONS);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_REFUSE_DISCOUNT_CONDITIONS = new DialogueNode("Закуски от Ralph", "-", true) {

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0){
				return "<p>[pc.speech(Нет, спасибо,)] — заставляешь ты себя сказать, отступая в сторону и удаляясь от похотливого лавочника.</p><p>[ralph.speech(Ну, если вдруг приспичит, я всегда готов утолить твою жажду!)] — смеется он, разворачиваясь и направляясь обратно к своему прилавку, [ralph.speech(Если понадобится что-то еще, только скажи.)]</p><p>Ты возвращаешься к осмотру товаров, но образы массивного черного конского члена Ralph никак не хотят покидать твою голову.</p>";
				
			}else{
				return "<p>[pc.speech(Нет, спасибо,)] — говоришь ты, отступая в сторону и удаляясь от похотливого лавочника.</p><p>[ralph.speech(Ну, мы оба ничего не теряем, если попробуем!)] — смеется он, разворачиваясь и направляясь обратно к своему прилавку. [ralph.speech(Если понадобится что-то еще, только скажи.)]</p><p>Ты возвращаешься к осмотру товаров, но тебе трудно избавиться от образа массивного бугра у Ralph в голове.</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Покупки", "Вернуться к осмотру товаров в лавке Ralph.", true) {
		
		@Override
		public String getContent() {
			return "<p>Ralph возвращается к работе, а ты идешь обратно к отделу трансформирующих расходников, раздумывая, стоит ли купить что-нибудь со своей скидкой. Когда он убеждается, что за вами никто не наблюдает, Ralph пожирает твое тело похотливым взглядом, и ты почти уверена, что смогла бы уговорить его дать тебе очередную «скидку» в любое время, когда пожелаешь.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить осмотр товаров в лавке Ralph.", RalphsSnacks.INTERIOR);
			} else {
				return null;
			}
		}
	};
	
	private static int getLipstickPrice() {
		int price = 50000;
		
		price *= (100-Main.game.getDialogueFlags().ralphDiscount)/100f;
		
		return price;
	}
	
	public static final DialogueNode CANDI_LIPSTICK = new DialogueNode("Закуски от Ralph", "-", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().ralphDiscount), true);
			UtilText.addSpecialParsingString(Util.intToString(getLipstickPrice()), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<getLipstickPrice()) {
					return new Response("Заплатить "+UtilText.formatAsMoneyUncoloured(getLipstickPrice(), "охват"), "Недостаточно средств, чтобы оплатить "+UtilText.formatAsMoney(getLipstickPrice(), "охват")+" в обмен на коробку «Ста к поцелуям»!", null);
				}
				return new Response("Заплатить "+UtilText.formatAsMoney(getLipstickPrice(), "охват"),
						"Заплатить Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "охват")+" в обмен на коробку «Ста к поцелуям».",
						CANDI_LIPSTICK_PURCHASE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getLipstickPrice()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_HUNDRED_KISSES), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LIPSTICK));
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
					}
				};

			} else if (index == 2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ralphDailyBred)) {
					return new Response("Согласиться на осеменение", "Ральф слишком занят своим магазином, чтобы продолжать попытки зачать от тебя ребенка. Возможно, тебе стоит прийти завтра и попробовать снова.", null);
					
				} else if(!Main.game.getPlayer().hasVagina()) {
					return new Response("Согласиться на осеменение", "Так как у тебя нет влагалища, остается только заплатить Ральфу "+UtilText.formatAsMoney(getLipstickPrice(), "охват")+" за коробку «Ста к поцелуям».", null);
					
				} else if(Main.game.getPlayer().isPregnant()) {
					return new Response("Согласиться на осеменение", "Так как ты уже беременна, остается только заплатить Ральфу "+UtilText.formatAsMoney(getLipstickPrice(), "охват")+" за коробку «Ста к поцелуям».", null);
					
				} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("Согласиться на осеменение", "Поскольку твоя утроба полна яиц, у тебя нет другого выбора, кроме как заплатить Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "охват")+" за коробку «Ста к поцелуям».", null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Согласиться на осеменение", "Поскольку у тебя нет доступа к своей вагине, у тебя нет другого выбора, кроме как заплатить Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "охват")+" за коробку «Ста к поцелуям».", null);
				}
				return new ResponseSex("Согласиться на осеменение",
						"Сказать Ralph, что ты позволишь ему осеменить тебя в обмен на коробку «Ста к поцелуям».",
						Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.OVER_DESK,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotDesk.BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Ralph.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(character.isPlayer()) {
									return super.getCharacterOrgasmBehaviour(character);
								}
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_START_BREEDING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Ralph.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getNpc(Ralph.class), false);
						Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ralphDailyBred, true);
					}
				};

			} else if (index == 0) {
				return new Response("Отклонить", "Сказать Ralph, что ты об этом подумаешь...", BACK_TO_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_PURCHASE_DECLINE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_INTERIOR"));
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CANDI_LIPSTICK_PURCHASE = new DialogueNode("Закуски от Ralph", "-", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().ralphDiscount), true);
			UtilText.addSpecialParsingString(Util.intToString(getLipstickPrice()), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_PURCHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_BREEDING = new DialogueNode("Завершено", "Теперь, когда твоя утроба наполнена его мощной спермой, Ralph получает свое.", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false), true);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant() && Objects.equals(Main.game.getPlayer().getPregnantLitter().getFather(), Main.game.getNpc(Ralph.class))) {
					return new Response("Успех",
							"Теперь ты не только гордая обладательница лимитированной коробки «Ста к поцелуям», но и стала матерью детей Ralph "
									+(Main.game.getPlayer().getPregnantLitter().getTotalLitterCount()==1?"ребенок":"дети")+"!",
									BACK_TO_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_SUCCESS"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_HUNDRED_KISSES), false));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LIPSTICK));
						}
					};
					
				} else {
					return new Response("Неудача...",
							"Ralph не удалось забеременеть тобой, и поэтому он не желает отдавать коробку «Ста к поцелуям»...",
							BACK_TO_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_FAILURE"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode BACK_TO_INTERIOR = new DialogueNode("Закуски от Ralph", "-", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
}
