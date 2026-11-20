package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.5.5
 * @author Innoxia
 */
public class PixShower {
	
	// Player:

	public static final SexAction PLAYER_KISS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Поцеловать";
		}

		@Override
		public String getActionDescription() {
			return "Поверни голову назад и поцелуй Пикс.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH) && Main.sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Ты поворачиваешь голову в сторону, пытаясь поцеловать Пикс. На мгновение тебе удается прижать свои [pc.lips] к её губам, но, тихонько хихикнув, она отстраняется, оставляя тебя целовать пустоту.",
					"Оглядываясь на Пикс, ты издаешь отчаянный стон и подаешься назад, чтобы поцеловать её, но она ускользает в самый последний момент, оставляя тебя раскрасневшейся и разочарованной, когда твои [pc.lips] натыкаются лишь на пустоту.",
					"Прильнув к Пикс, ты поворачиваешь голову, чтобы поцеловать её. Заметив твои намерения, она издает смешок и отстраняется, не давая твоим [pc.lips] даже приблизиться к её губам.",
					"Повернув голову назад, чтобы посмотреть на Пикс, ты пытаешься её поцеловать, но, увидев, что ты задумала, она уворачивается с хохотом, заставляя тебя чувствовать себя невероятно глупо.");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_FEEL_BREASTS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сжимать соски";
		}

		@Override
		public String getActionDescription() {
			return "Протяни руку вверх и ущипни свои [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Чувствуя жгучее разочарование, ты тянешься к груди в надежде уделить внимание своим [pc.nipples+]. Однако Пикс замечает это и сердито отшвыривает твои руки от груди, заставляя тебя издать отчаянный стон.",
							
					"Ты пытаешься дотянуться до своих [pc.nipples+], чтобы поиграть с ними, но из-за сильной усталости твои движения крайне вялые, и Пикс быстро убирает твои руки от груди, прежде чем ты успеваешь до них добраться.",
					
					"Издавая отчаянный скулеж, ты пытаешься дотянуться до своих [pc.breasts], но у Пикс другие планы: она решительно отрывает твои руки от груди.",
					
					"В отчаянной попытке поиграть со своими [pc.nipples+], твои [pc.hands] не успевают дойти даже до середины пути, как Пикс властно оттягивает твои [pc.arms] вниз, к бокам.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_TRY_TO_MASTURBATE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Мастурбировать";
		}

		@Override
		public String getActionDescription() {
			return "Просунуть руку между ног и начать мастурбировать.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Покраснев от досады, ты пытаешься спрятать свои [pc.hands] между [pc.legs], но в твоем нынешнем состоянии Пикс оказывается куда быстрее: она мгновенно заводит твои руки за спину, не давая тебе ласкать себя.",
							
					"Ты пытаешься просунуть руку между ног, чтобы заняться мастурбацией, но Пикс быстро хватает тебя за запястья и уводит руки за спину.",
					
					"С отчаянным скулежом ты пытаешься опустить руки между ног. Заметив твои намерения, Пикс издает короткий смешок, грубо хватает тебя за запястья, отрывая руки от паха, и угрожающе рычит тебе в самое ухо.",
					
					"В отчаянной попытке достать до себя, твои руки не успевают дойти даже до середины паха, как Пикс уже оттягивает их в сторону, хихикая тебе в ухо.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "Грязные разговоры с Пикс.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Стоит тебе открыть рот, чтобы что-то сказать, как Пикс прижимает один из своих [pix.hands+] к твоим губам, хихикая тебе в ухо и веля молчать.",
							
					"Ты пытаешься что-то сказать Пикс, но слова мгновенно глохнут, когда она резко вскидывает руку, закрывая тебе рот. Смеясь тебе через плечо, она велит тебе помалкивать и быть хорошим [pc.girl].",
					
					"Стоило тебе приоткрыть рот, как ты даже не успел вымолвить ни слова — Пикс прижала одну из своих [pix.hands+] к твоему [pc.lips+].",
					
					"Когда ты пытаешься что-то возразить Пикс, она быстро зажимает [pix.a_hand+] твой рот, смеясь тебе в ухо и не давая возможности заговорить.");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
		}
	};
	
	public static final SexAction PLAYER_BREAK_FREE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Барахтаться";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вырваться из хватки Пикс.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Насладившись «весельем» Пикс сполна, ты отчаянно пытаешься выскользнуть из её объятий. К сожалению, ты всё ещё совершенно измотан её тренировкой, и она с лёгкостью вжимает тебя обратно в стенку душевой, игриво рыча тебе в ухо, пока твоя попытка побега резко не обрывается.",
							
					"Собрав остатки своей энергии, ты отчаянно пытаешься оттолкнуть Пикс. Издав короткий смешок, она с лёгкостью одолевает тебя, прижимая к стенке душевой и мягко рыча тебе в ухо.",
					
					"Извиваясь то так, то эдак, ты отчаянно пытаешься оттолкнуть Пикс. Смеясь над твоей довольно слабой попыткой, она с лёгкостью вжимает тебя обратно в стенку душевой, мягко рыча тебе через плечо, пока ты тяжело дышишь от напряжения.",
					
					"Оттолкнувшись от стены перед собой, ты пытаешься сбросить Пикс со спины. Но в твоем состоянии тебе удается лишь заставить ее слегка пошатнуться, и она, угрожающе зарычав, тут же с силой прижимает тебя обратно к стене.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction PLAYER_SUBMIT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Подчиниться";
		}

		@Override
		public String getActionDescription() {
			return "Позволить Пикс делать с тобой всё, что ей вздумается.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Понимая, что ты не в состоянии сопротивляться «забавам» Пикс, ты мягко откидываешься на доминантную собачонку, позволяя ей использовать тебя так, как она пожелает.",
							
					"Ты решаешь просто позволить Пикс использовать тебя как угодно, и мягко прижимаешься к ней спиной, на что она отвечает одобрительным рычанием.",
					
					"Прислонившись к стене душевой кабины, ты полностью сдаешься на милость Пикс, позволяя ей творить с тобой всё, что ей вздумается.",
					
					"Осознав, что Пикс полностью контролирует ситуацию, ты прислоняешься к стене душевой, решив позволить ей делать с тобой всё, что она захочет.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction FINGERED_GRIND_DOWN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Тереться ниже";
		}

		@Override
		public String getActionDescription() {
			return "Тереться своим [npc.pussy] о руку Пикс.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Прижимая таз к [pix.hand+] Пикс, доминантная собачонка издает восторженный [pix.moan], глубоко вонзая свои [pix.fingers+] в твой [pc.pussy+], пока ты ахаешь и содрогаешься под струями душа.",
					
					"Опираясь на Пикс, ты начинаешь тереться своим [pc.pussy+] о её [pix.hand+], радостно взвизгивая, когда она в ответ глубоко вонзает свои [pix.fingers+] в твой [pc.pussy].",
					
					"Мягко опуская бедра на руку Пикс, ты пытаешься побудить её проталкиваться чуть глубже, и, к твоему огромному удовольствию, чувствуешь, как она отвечает, поджимая свои [pix.fingers+] и с жадностью лаская стенки твоей вагины.",
					
					"Прижимая твою [pc.pussy] к руке Пикс, она отвечает предвкушающим рычанием и поджимает свои [pix.fingers] внутри тебя. Когда она начинает увлеченно двигаться манящими движениями, ты откидываешься назад, на доминантную собачонку, стоная и содрогаясь под потоками горячей воды из душа.");
		}
	};
	
	// Partner on player:
	
	public static final SexAction PARTNER_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Поцеловать";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Пикс тихо хихикает, наклоняясь к твоему плечу; она игриво прикусывает и покусывает твое ухо, продолжая крепко прижимать тебя к стенке душа.",
					
					"С игривым смехом Пикс грубо прижимает тебя к стене, а затем наклоняется к твоему плечу, чтобы начать кусать и покусывать твое ухо.",
					
					"Пока горячая вода продолжает лить на вас обеих, Пикс прижимается своей [pix.face+] к твоей шее и с легким [pix.moan] начинает игриво вылизывать всё твоё плечо своим [pix.tongue+].",
					
					"Наклонившись к твоему плечу, Пикс начинает игриво вылизывать и слизывать капли с твоей шеи и ушей своим [pix.tongue+].");
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
				
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Прижимая тебя к стене, Пикс тихо рычит тебе в ухо, ",
					"Крепко прижимаясь к твоей спине, Пикс наклоняется к твоему плечу и рычит, "));
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.speech(Отличный [pc.girl]! Надеюсь, ты наслаждаешься наградой так же сильно, как и я!)]",
					"[npc.speech(Оу, ты, кажется, изрядно вымотался! Забавно, как всё само собой сложилось, а?)]",
					"[npc.speech(Знаешь, ты справляешься куда лучше, чем некоторые парни, которых я награждала раньше!)]",
					"[npc.speech(Разве это не супер весело?! Ну, по крайней мере, для меня — точно!)]"));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_GROPE_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.game.getPlayer().hasBreasts();
		}
		
		@Override
		public String getActionTitle() {
			return "Тискать грудь";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB = new StringBuilder();
			
			UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
					"Пикс подносит [pix.a_hand+] к твоей груди, и когда она сжимает одну из твоих [pc.breasts+], ты слышишь её тихий, нежный смешок.",
					
					"Пока струи воды из всё ещё работающего душа стекают по твоему обнажённому телу, Пикс подносит [pix.a_hand+] к твоей груди и с восторженным рычанием начинает грубо ощупывать и сжимать твои [pc.breastRows] [pc.breasts].",
					
					"Твои [pc.breasts+], намокшие под струями душа, внезапно становятся центром внимания Пикс. Подняв одну из своих [pix.hands+] к твоей груди, она с жадностью начинает сжимать и мять твои мясные холмики, тихо рыча тебе в ухо, пока ты пытаешься сдержать [pc.a_moan+].",
					
					"Ты издаёшь [pc.a_moan+], когда Пикс обхватывает тебя сзади и начинает по очереди ощупывать и сжимать каждую из твоих [pc.breasts+]."));
			
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" Тонкая струйка [pc.milk] вытекает ей на кончики пальцев, когда она сжимает твою [pc.nipples+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" Небольшая порция [pc.milk] выплескивается ей на кончики пальцев, когда она сжимает твою [pc.nipples+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" Тонкая струйка [pc.milk] стекает по её пальцам, пока она сжимает твой [pc.nipples+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Твоя [pc.milk] начинает вытекать на её [pix.fingers+], смешиваясь с бегущей водой из душа и стекая по твоему [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" Твоя [pc.milk] начинает сочиться на её [pix.fingers+], смешиваясь с бегущей водой из душа и стекая по твоему [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" Твоя [pc.milk] начинает литься на её [pix.fingers+], смешиваясь с бегущей водой из душа и стекая по твоему [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" Твоя [pc.milk] начинает хлестать густым потоком на её [pix.fingers+], смешиваясь с бегущей водой из душа и стекая по твоему [pc.breasts+].");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.game.getPlayer().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction PARTNER_MASTURBATE_PLAYER_COCK = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(null, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isPenetrationTypeFree(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER);
		}
		
		@Override
		public String getActionTitle() {
			return "Ласкать член игрока";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(),
					UtilText.returnStringAtRandom(
					"Просунув руку между твоих ног, Пикс внезапно хватает твой [pc.cock+], и когда она начинает водить рукой вверх и вниз по его длине, ты невольно издаешь [pc.a_moan+] и откидываешься на неё.",
					
					"Одной своей [pix.hands+] Пикс пробирается между твоих [pc.legs+] и, обхватив твой [pc.cock+], начинает быстро дрочить тебе под струями воды в душе.",
					
					"Ты не можешь удержаться от [pc.a_moan+] и откидываешься на Пикс, когда она просовывает руку, чтобы схватить твой [pc.cock+], и начинает долгими, размеренными движениями дрочить тебе.",
					
					"Ты издаешь серию [pc.moans+], когда Пикс обхватывает твою [pc.cock+]. Тихо рыча тебе на ухо, она с нетерпением начинает ласкать тебя, отчего твой [pc.moans] неуклонно растет в объеме и интенсивности."));
		}
	};
	
	public static final SexAction FINGERING_PLAYER_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Начать пальцевать";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(),
					"Ты чувствуешь, как Пикс тяжело прижимается к твоей спине, и, угрожающе рыча тебе в ухо, она тянется вперед с [pix.a_hand+], чтобы начать поглаживать и исследовать твои внешние половые губы. С помощью [pc.a_moan+] ты откидываешься на нее, и пока звук падающей воды эхом разносится по стенам вокруг вас, она внезапно вгоняет свою [pix.fingers+], одним резким движением проникая в твою [pc.pussy+].");
		}
	};
	
	public static final SexAction FINGERING_PLAYER_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Нежное проникновение пальцами";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Пикс вгоняет свою [pix.fingers+] в твою [pc.pussy+], подгибая их и нежно поглаживая манящими движениями, пока ты тихо стонешь и тяжело дышишь, прижавшись к стене.",
					
					"Вгоняя свою [pix.fingers+] в твою [pc.pussy+], Пикс начинает плавно двигаться вперед-назад, вызывая серию мягких стонов из твоей [pc.lips], пока ты откидываешься на нее, ища опору.",
					
					"Ты издаешь [pc.a_moan+], когда Пикс мягко вталкивает свою [pix.fingers+] глубоко в твою [pc.pussy+]. Когда ты чувствуешь, как ее ладонь надавливает на твои внешние половые губы, она подгибает свои [pix.fingers], лаская стенки твоей вагины и заставляя тебя издать еще один [pc.moan+].",
					
					"Ты издаешь [pc.a_moan+] и откидываешься на Пикс, ища у нее опору, пока она сосредоточенно начинает перебирать твои [pc.pussy+]. Под эхо падающей воды Пикс подгибает свои [pix.fingers+] внутри тебя и с нетерпением начинает ласкать стенки твоей вагины.");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction FINGERING_PLAYER_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Грубые пальцы";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Пикс с силой вгоняет свои [pix.fingers+] в твою [pc.pussy+], подгибая их и грубо совершая манящие движения, пока ты издаешь серию [pc.moans+].",
					
					"Грубо втискивая свои [pix.fingers+] в твою [pc.pussy+], Пикс начинает яростно двигаться вперед-назад, вызывая серию [pc.moans+] из твоей [pc.lips], пока ты бессильно откидываешься на нее, ища опору.",
					
					"Ты издаешь [pc.a_moan+], когда Пикс грубо вгоняет свои [pix.fingers+] глубоко в твою [pc.pussy+]. Когда ты чувствуешь, как ее ладонь с силой прижимается к твоим внешним половым губам, она подгибает свои [pix.fingers], отчаянно лаская стенки твоей вагины и заставляя тебя издать еще один [pc.moan+].",
					
					"Ты издаешь [pc.a_moan+] и откидываешься на Пикс, ища у нее опору, пока она с силой вбивает свои [pix.fingers] глубоко в твою [pc.pussy+]. Под эхо падающей воды Пикс грубо подгибает свои [pix.fingers+] внутри тебя и начинает стремительно ласкать стенки твоей вагины.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction FINGERING_PLAYER_CLIT_PLAY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Игры с клитором";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Погружая пальцы в твою [pc.pussy+], Пикс подносит большой палец к твоему клитору. Когда она надавливает на него и начинает тереть твою чувствительную маленькую пуговку, ты издаешь [pc.a_moan+] и откидываешься на нее, ища опору.",
					
					"Поднимая большой палец к твоему нежному клитору, Пикс надавливает на него и начинает растирать твою маленькую пуговку. С отчаянным криком ты откидываешься на неё, и твой [pc.moans+] почти заглушается шумом льющейся воды, пока она продолжает ласкать твой женский бугорок.",
					
					"Ты ахаешь и выгибаешься навстречу руке Пикс, чувствуя, как её большой палец давит на твой клитор. Откинувшись на доминантную собачонку, ты вжимаешься в её ладонь, издавая [pc.moans+], пока она продолжает играть с твоей чувствительной пуговкой.",
					
					"Ты взвизгиваешь и откидываешься на Пикс, когда она прижимает большой палец к твоему чувствительному маленькому клитору. Пока она продолжает вводить и выводить пальцы в твою [pc.pussy+], ты чувствуешь, как она сосредотачивает все усилия на растирании твоей чувствительной пуговки, и через мгновение ты уже издаешь серию [pc.moans+].");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PROMISE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !SexFlags.pixDemandedPromise
					&& Main.game.getPlayer().getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		
		@Override
		public String getActionTitle() {
			return "Потребовать обещание";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			return "Пикс внезапно прижимает тебя к стене и, наклонившись к твоему плечу, начинает тихо рычать тебе в ухо, [npc.speechNoEffects(Знаешь, у меня есть для тебя небольшое предложение, так что послушай меня, ладно? В общем, вот что будет: когда ты почувствуешь, что вот-вот кончишь, ты пообещаешь мне, что вернешься на новые тренировки в будущем, идет? И я имею в виду <i>настоящее</i> обещание!)]<br/><br/>Ты не в том положении, чтобы перебивать, поэтому, пока Пикс продолжает прижимать тебя к стене, ты слушаешь её «сделку», [npc.speechNoEffects(Знаешь, я могу заниматься этим весь день, так что когда будешь готов кончить для меня, ты пообещаешь, что вернешься тренироваться, ясно?! И если не пообещаешь, я не дам тебе кончить! Надеюсь, ты меня внимательно слушаешь, потому что я, типа, совершенно серьезно!)]<br/><br/>Ты пытаешься ответить, но Пикс быстро прижимает одну из своих [pix.hands+] к твоему рту, напоминая, что тебе позволено говорить только тогда, когда ты вот-вот испытаешь оргазм.";
		}

		@Override
		public void applyEffects() {
			SexFlags.pixDemandedPromise = true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
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
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "С удовлетворенным вздохом Пикс наконец отпускает тебя, и ты обнаруживаешь, что бессильно падаешь ей в объятия, совершенно измотанный.";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};


	// Player:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Приготовиться";
		}

		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Приготовьтесь к нему.";
		}
		
		@Override
		public String getDescription() {
			return "Ты не можешь пошевелиться, пока Пикс прижимается к твоей спине, вжимая тебя в стенку душевой и готовясь к оргазму.";
		}
	};
	
	public static final SexAction PLAYER_ORGASM_PROMISE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Пообещать";
		}

		@Override
		public String getActionDescription() {
			return "Пообещай Пикс, что вернешься снова.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Ты больше не можешь выносить дразнилки Пикс, и когда в паху начинает нарастать отчаянная жажда, ты [pc.a_moan+] и делаешь то, о чем она просит: [pc.speech(Ладно! Обещаю, что вернусь и потренируюсь снова! Пожалуйста, просто дай мне кончить!)].<br/><br/>В ответ Пикс издает восторженный крик, и прежде чем ты успеваешь что-то понять, она запускает обе руки тебе между ног и мягко рычит тебе в ухо: [pix.speech(Хороший [pc.girl]! Знаешь, я думаю, ты заслужил небольшую награду за то, что был таким послушным малышом!)]");
			
			UtilText.nodeContentSB.append("<br/><br/>");
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()){
				
				UtilText.nodeContentSB.append("Ты чувствуешь, как одна из [pix.hands+] Пикс обхватывает твой [pc.cock+], и пока она начинает яростно дрочить тебя, она продолжает твердить о том, каким хорошим [pc.girl] ты был.");
				
				// Describe cum amount:
				UtilText.nodeContentSB.append(" Ты не выдерживаешь и нескольких секунд под ее умелыми руками, и когда ты чувствуешь, как твой [pc.balls+] напрягается");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", ты [pc.a_moan+], осознавая, что не можешь выдавить из себя ни единой капли спермы.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как тонкая струйка [pc.cum+] брызжет");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", ты чувствуешь, как небольшое количество [pc.cum+] брызжет");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum+] брызжет");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum+] брызжет струями");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum+] извергается");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum+] льется");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum+] льется");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE){
					UtilText.nodeContentSB.append(" разлетается по стене прямо перед тобой. ");
				}
				
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("Когда Пикс прижимает свои [pix.breastRows] [pix.breasts+] к твоей спине, ты чувствуешь, как отчаянный, дрожащий жар поднимается из твоих [pc.pussy+]. С легким хихиканьем Пикс внезапно вонзает два своих [pix.fingers+] глубоко в твою [pc.pussy+], и ты вскрикиваешь от экстаза, пока она другой рукой надавливает и трет твой чувствительный маленький клитор. С финальным, оглушительным криком на тебя обрушивается оргазм, и ты чувствуешь, как подкашиваются ноги, и бессильно валишься на Пикс.");
			}
			
			// MOUND ORGASM:
			if (!Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("С оглушительным криком и дрожащими ногами, внезапная волна чистого экстаза проносится сквозь тебя. Мышцы внутри твоего бесполого лона начинают спазмировать и сокращаться, и ты издаешь отчаянный крик, когда Пикс тянется вниз и начинает тереть и щекотать твою невероятно чувствительную промежность. С финальным, оглушительным криком на тебя обрушивается оргазм, и ты чувствуешь, как подкашиваются ноги, и бессильно валишься на Пикс.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_ORGASM_REFUSE = new SexAction(
			SexActionType.ORGASM_DENIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Отказаться";
		}

		@Override
		public String getActionDescription() {
			return "Старайся не шуметь в надежде, что Пикс не заметит, что ты вот-вот достигнешь пика.";
		}

		@Override
		public String getDescription() {
			return "Ты чувствуешь, как в паху нарастает отчаянный жар, но, не желая обещать Пикс, что вернешься к ней снова, ты решаешь промолчать и кончить без ее помощи. По мере того как оргазм все сильнее накрывает тебя, ты внезапно ловишь себя на том, что издаешь тихий [pc.moan].<br/><br/> Преданная твоей неспособностью сохранять молчание, Пикс понимает, что происходит, и с сердитым рычанием хватает твои [pc.arms], прижимая их к спине, пока сама впечатывает тебя в стену, [pix.speech(Что я сказала?! Ты <i>не</i> кончишь, пока не пообещаешь вернуться! Знаешь, я могу так весь день!)]<br/><br/> Она держит тебя так, прижатым к стене, несколько минут, и как бы ты ни старался, тебе никак не удается вырваться. Ты даже отчаянно пытаешься тереться пахом о стенку душевой, но все бесполезно, и ты чувствуешь, как пик удовольствия медленно уходит, оставляя тебя невероятно разочарованным и возбужденным. Только когда становится ясно, что ты успокоился, Пикс наконец отпускает тебя и, скользя руками по твоему телу, готовится снова довести тебя до самого края...";
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};
	
	// Pix orgasm:
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Приготовиться";
		}

		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Приготовьтесь к нему.";
		}
		
		@Override
		public String getDescription() {
			return "Пикс внезапно прижимает тебя к стене и, наклонившись к твоему плечу, рычит прямо в ухо: [npc.speech(О-о, уже готов кончить? Пообещай, что вернешься на тренировку, иначе ты больше никогда не кончишь!)]";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_PIX_REWARD = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Оргазм";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Вы слышите отчаянные тонкие поскуливания Пикс и понимаете, что она получает удовольствие от того, что не дает вам кончить. Почувствовав шанс вырваться, вы пытаетесь оттолкнуть её, но это лишь усиливает её стоны, и с оглушительным криком она с силой вжимает вас в стену, втирая свою обнаженную [pix.pussy] в ваши [pc.ass] в момент, когда её накрывает оргазм.<br/><br/>Спустя несколько мгновений она наконец приходит в себя и, выпрямившись, довольно вздыхает вам в ухо: [pix.speech(Бля... Как же хорошо было!)]";
		}

		@Override
		public void applyEffects() {
		}
	};
	


}
