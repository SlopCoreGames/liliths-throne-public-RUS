package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.68
 * @version 0.1.8
 * @author Innoxia
 */
public class SABraxSubCowgirl {

	// TODO: This class isn't being used anymore, but I need the facesitting actions for reference for when I add that position.
	
	// Player's actions:

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "Скажи Brax, что теперь он твоя сучка.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Ты вскрикиваешь, подпрыгивая вверх-вниз на [npc.penis+] Brax, ",
						"Сквозь стоны ты выкрикиваешь Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Аах!~ Да! Обожаю твой член!",

							"Бля! ~Аах!~ Твой член такой огромный!",

							"~Ммм!~ Обожаю скакать на таких мелких бетах, как ты!",

							"Хороший мальчик! ~Аах!~ Позволь своей альфе оседлать тебя, как маленькую сучку!")));
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Ты вскрикиваешь, подпрыгивая вверх-вниз на [npc.penis+] Brax, ",
						"Сквозь стоны ты выкрикиваешь Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Аах!~ Да! Обожаю твой член!",

							"Бля! ~Аах!~ Твой член такой огромный!",

							"~Ммм!~ Обожаю скакать на таких мелких бетах, как ты!",

							"Хороший мальчик! ~Аах!~ Позволь своей альфе оседлать тебя, как маленькую сучку!")));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Глядя сверху вниз на лицо Brax между своих ног, ты покровительственно обращаешься к нему, ",
						"С ухмылкой ты покровительственно обращаешься к Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Аах!~ Да, хороший маленький бета!",

							"~Ммм!~ Продолжай! Да, вот так!",

							"Хороший мальчик, Brax! Продолжай лизать! Не останавливайся!",

							"~Аах!~ Да! Делай так же!")));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Глядя сверху вниз на лицо Brax между своих ног, ты покровительственно обращаешься к нему, ",
						"С ухмылкой ты покровительственно обращаешься к Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Аах!~ Да, хороший маленький бета!",

							"~Ммм!~ Продолжай! Да, вот так!",

							"Хороший мальчик, Brax! Продолжай лизать! Не останавливайся!",

							"~Аах!~ Да! Делай так же!")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Смотря на покорную фигуру Brax под собой, ты властно произносишь: ",
						"С ухмылкой ты покровительственно обращаешься к Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"Ты ведь будешь хорошим маленьким бетой, правда, Brax?",

							"Вот так, лежи смирно, как послушный маленький бета!",

							"Какой же ты послушный бета!",

							"Хочешь, чтобы я оседлала твой маленький бета-член?")));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			}
		}
	};
	

	public static final SexAction PLAYER_COWGIRL_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Поцеловать";
		}

		@Override
		public String getActionDescription() {
			return "Наклониться и поцеловать Brax.";
		}

		@Override
		public String getDescription() {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
				switch(Util.random.nextInt(5)){
					case 0:
						return "Ты опускаешься на [npc.penis+] Brax, радостно взвизгивая от ощущения, когда он входит глубоко в твою [pc.asshole+]. Дойдя до самого основания его члена, ты наклоняешься, обхватываешь голову Brax обеими руками и притягиваешь его к себе для страстного поцелуя.";
					case 1:
						return "С похотливым стоном ты сползаешь на [npc.penis+] Brax, подаваясь вперед к его груди и вдыхая его мужественный запах, прежде чем прижаться к его губам.";
					case 2:
						return "Ты на мгновение выпускаешь [npc.penis+] Brax из своей задницы, наклоняешься и вовлекаешь его в отчаянный поцелуй. После нескольких мгновений языкового совокупления вы выпрямляетесь, и когда ты снова опускаешься на массивный член Brax, ты видишь тонкую нить слюны, которая на миг соединяет ваши губы, прежде чем порваться и упасть на его волосатую грудь.";
					case 3:
						return "Когда ты наклоняешься вперед, Brax приподнимается на локтях, жадно отвечая на твой поцелуй. Ваши стоны заглушаются в тесноте чужих ртов, пока [npc.penis+] Brax глубоко погружается в твою [pc.asshole+].";
					default:
						return "Наклонившись, ты издаешь отчаянный стон, насаживаясь на [npc.penis+] Brax, а затем прижимаешься своими губами к его губам, жадно целуя этого мужественного волка-мальчика.";
				}

			} else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
				switch(Util.random.nextInt(5)){
					case 0:
						return "Ты опускаешься на [npc.penis+] Brax, издавая радостный визг, когда чувствуешь его глубоко внутри своей "+Main.game.getPlayer().getVaginaName(true)+". Дойдя до самого основания его члена, ты наклоняешься, обхватываешь голову Brax обеими руками и тянешь его к себе для страстного поцелуя.";
					case 1:
						return "С похотливым стоном ты сползаешь на [npc.penis+] Brax, подаваясь вперед к его груди и вдыхая его мужественный запах, прежде чем прижаться к его губам.";
					case 2:
						return "Ты на мгновение позволяешь [npc.penis+] Brax выскользнуть из своей киски, наклоняешься и вовлекаешь его в отчаянный поцелуй. После нескольких мгновений языкового совокупления в ртах вы выпрямляетесь, и когда ты снова опускаешься на массивный член Brax, ты видишь тонкую нить слюны, которая на миг соединяет ваши губы, прежде чем порваться и упасть на его покрытую шерстью грудь.";
					case 3:
						return "Когда ты наклоняешься вперед, Brax приподнимается на локтях, жадно отвечая на твой поцелуй. Ваши стоны заглушаются в тесноте ртов, пока [npc.penis+] Brax глубоко входит в твою "
								+Main.game.getPlayer().getVaginaName(true)+".";
					default:
						return "Наклонившись, ты издаешь отчаянный стон, насаживаясь на [npc.penis+] Brax, а затем прижимаешься своими губами к его губам, жадно целуя этого мужественного волка-мальчика.";
				}

			} else {
				switch(Util.random.nextInt(5)){
					case 0:
						return "Ты наклоняешься, обхватываешь голову Brax обеими руками и тянешь его к себе для страстного поцелуя. Ты издаешь приглушенный смешок в рот волка-мальчика, чувствуя, как его "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
								+" член упирается тебе в поясницу.";
					case 1:
						return "С ухмылкой ты наклоняешься к мощной груди Brax, вдыхая его мужественный запах, прежде чем прижаться к его губам.";
					case 2:
						return "Ты чувствуешь, как [npc.penis+] Brax бесполезно упирается тебе в поясницу, пока ты вовлекаешь его в отчаянный поцелуй. После нескольких мгновений языкового траха друг другу в рты, ты выпрямляешься, замечая тонкую нить слюны, которая на миг соединяет ваши губы, прежде чем порваться и упасть на его волосатую грудь.";
					case 3:
						return "Когда ты наклоняешься вперед, Brax опирается на локти, жадно встречая твои губы своими. Ваши стоны заглушаются в тесноте сплетенных ртов, пока Brax... "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
								+" бесплодно упирается тебе в поясницу.";
					default:
						return "Наклонившись, ты на мгновение замираешь, любуясь мужественным телом Brax, издаешь тихий стон, а затем прижимаешься к его губам и начинаешь жадно его целовать.";
				}
			}
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PUSSY = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return CorruptionLevel.THREE_DIRTY;
			else
				return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return "Чистая киска";
			else
				return "Фейссит (киска)";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return "Подвинуться вперед и сесть Brax на лицо, заставляя его язык вылизать сперму из твоей "+Main.game.getPlayer().getVaginaName(true)+".";
			else
				return "Подвинуться вперед и сесть Brax на лицо, заставляя его язык работать над твоей "+Main.game.getPlayer().getVaginaName(true)+".";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()
					&& !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& Main.game.getPlayer().getVaginaType() != VaginaType.NONE;
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
				UtilText.nodeContentSB.setLength(0);
				
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
					UtilText.nodeContentSB.append("Решив, что с языка Brax в твоей [pc.asshole+] хватит, ты приподнимаешься и отползаешь назад, пока твоя "
								+Main.game.getPlayer().getVaginaName(true)+" расположена прямо над его лицом.");
					
				} else {
					UtilText.nodeContentSB.append("Вы решаете задействовать язык Brax и подаетесь вперед, пока ваша промежность не окажется прямо над его лицом.");
				}
				
				UtilText.nodeContentSB.append(" Вы чувствуете, как густая порция склизкой спермы вытекает из вашей "+Main.game.getPlayer().getVaginaName(true)+", и тихо хихикаете, размазывая липкий кремпи по волчьей морде Brax. Вы удерживаете извивающегося волка-мальчика за голову, прижимая свою наполненную спермой киску к его рту, не оставляя ему выбора, кроме как начать вылизывать вас. Спустя мгновение Brax перестает сопротивляться, понимая тщетность усилий, и вы удовлетворенно вздыхаете, когда он начинает слизывать сперму из вашей залитой "+Main.game.getPlayer().getVaginaName(false)+".");
				
				return UtilText.nodeContentSB.toString();
						
			} else {
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
					return "Решив, что с языка Brax в твоей [pc.asshole+] хватит, ты приподнимаешься и отползаешь назад, пока твоя "
								+Main.game.getPlayer().getVaginaName(true)+" расположена над его волчьей мордой. Опускаясь ниже, вы издаете довольный стон, чувствуя, как плоский язык Brax жадно начинает лизать ваши чувствительные складки, и когда ваша "
								+Main.game.getPlayer().getVaginaName(true)+" опускается ему на рот, Brax с энтузиазмом принимается вас вылизывать.";
					
				} else {
					return "Вы решаете задействовать язык Brax и подаетесь вперед, пока ваша промежность не окажется прямо над его волчьей мордой. Опускаясь ниже, вы издаете довольный стон, чувствуя, как плоский язык Brax жадно начинает лизать ваши чувствительные складки, и когда ваша "
								+Main.game.getPlayer().getVaginaName(true)+" опускается ему на рот, Brax с энтузиазмом принимается вас вылизывать.";
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_CUM_STUD);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
				}
				
			} else {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_ASS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return CorruptionLevel.FOUR_LUSTFUL;
			else
				return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return "Чистая задница";
			else
				return "Фейссит (на задницу)";
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return "Подвинуться вперед и сесть Brax на лицо, заставляя его вылизывать сперму из твоей [pc.asshole+].";
			else
				return "Подвинуться вперед и сесть Brax на лицо, заставляя его язык поработать над твоей [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
				UtilText.nodeContentSB.setLength(0);
				
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
					UtilText.nodeContentSB.append("Решив, что с языка Brax в твоей "+Main.game.getPlayer().getVaginaName(true)+" , по тебе уже хватит, ты приподнимаешься и двигаешься вперед, пока твоя [pc.asshole+] не окажется прямо над его лицом.");
					
				} else {
					UtilText.nodeContentSB.append("Ты решаешь пустить язык Brax в дело и двигаешься вперед, пока твоя задница не окажется над его лицом.");
				}
				
				UtilText.nodeContentSB.append(" Ты чувствуешь, как из твоей [pc.asshole+] вытекает густая капля склизкой спермы, и хихикаешь, размазывая липкий кремпи по волчьей морде Brax. Ты удерживаешь голову извивающегося волка-мальчика, прижимая свой наполненный спермой анус к его рту, не оставляя ему выбора, кроме как начать вылизывать тебя. Спустя мгновение Brax перестает дергаться, понимая, что сопротивление бесполезно, и ты удовлетворенно вздыхаешь, когда он начинает слизывать сперму из твоей залитой семенем [pc.asshole].");
				
				return UtilText.nodeContentSB.toString();
						
			} else {
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
					return "Решив, что с языка Brax в твоей "+Main.game.getPlayer().getVaginaName(true)+" , тебе уже хватит, ты приподнимаешься и двигаешься вперед, пока твоя [pc.asshole+] не окажется над его волчьей мордой. Опускаясь вниз, ты издаешь довольный стон, чувствуя, как плоский язык Brax послушно начинает лизать твою [pc.asshole+].";
					
				} else {
					return "Вы решаете задействовать язык Brax и сдвигаетесь вперед, пока ваш [pc.asshole+] не окажется прямо над его волчьей мордой. Опускаясь ниже, вы издаете довольный стон, чувствуя, как плоский язык Brax послушно начинает вылизывать ваш [pc.asshole+].";
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_CUM_STUD);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
				}
				
			} else {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_ANAL_GIVING);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PASSIVE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Пассивное";
		}

		@Override
		public String getActionDescription() {
			return "Сидеть смирно и позволить Brax делать всю работу.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Вы замираете над ртом Brax, издавая тихие стоны, пока его волчий язык жадно облизывает вашу "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"Вы рассеянно гладите Brax по макушке, счастливо стоная, пока он ласкает вас языком.",
					
					"Игриво поглаживая пушистые волчьи уши Brax, вы издаете удовлетворенный стон, когда он глубоко засовывает язык в вашу "+Main.game.getPlayer().getVaginaName(true)+".",
						
					"Устроившись над лицом Brax, вы издаете серию похотливых стонов, пока его широкий волчий язык жадно прорывается в вашу "+Main.game.getPlayer().getVaginaName(true)+".");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PASSIVE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Пассивное";
		}

		@Override
		public String getActionDescription() {
			return "Сидеть смирно и позволить Brax делать всю работу.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Вы замираете над ртом Brax, издавая тихие стоны, пока его волчий язык послушно облизывает ваш [pc.asshole+].",
					
					"Вы рассеянно гладите Brax по макушке, счастливо стоная, пока он вылизывает ваш [pc.asshole+].",
					
					"Игриво поглаживая пушистые волчьи уши Brax, вы издаете удовлетворенный стон, когда он глубоко засовывает язык в ваш [pc.asshole+].",
						
					"Удерживая себя над лицом Brax, ты издаешь серию похотливых стонов, пока его широкий, волчий язык послушно проталкивается в твою [pc.asshole+].");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_GRIND = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Тереться";
		}

		@Override
		public String getActionDescription() {
			return "Грубо тереться о волчью морду Brax.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				return UtilText.returnStringAtRandom(
						"Желая поднять градус страсти, ты наваливаешься на лицо Brax, полностью затыкая его рот своей "+Main.game.getPlayer().getVaginaName(true)+". Когда он начинает скулить и извиваться, ты с силой вжимаешься в его волчью морду, издавая отчаянный визг, пока его язык насильно проникает глубоко в твою "+Main.game.getPlayer().getVaginaName(true)+".",
						
						"Позволяя ногам подкоситься, ты тяжело опускаешься на лицо Brax, с силой втирая свою "+Main.game.getPlayer().getVaginaName(true)+" в его рот, пока он извивается и скулит под тобой.",
						
						"Схватившись за пушистые волчьи уши Brax, ты наваливаешься на его лицо, издавая восторженный визг, когда его язык насильно входит глубоко в твою "+Main.game.getPlayer().getVaginaName(true)+".",
							
						"Чуть шире разведя ноги, ты опускаешься на волчью морду Brax, издавая отчаянный стон и с силой втирая свою "+Main.game.getPlayer().getVaginaName(true)+" в его жаждущий язык.");
			} else {
				return UtilText.returnStringAtRandom(
						"Желая поднять градус страсти, ты наваливаешься на лицо Brax, полностью затыкая его рот своей "+Main.game.getPlayer().getAssName(true)+". Когда он начинает скулить и извиваться, ты с силой вжимаешься своей киской в его волчью морду, издавая отчаянный визг, когда его язык проникает глубоко в твою [pc.asshole+].",
						
						"Позволив ногам подогнуться, ты тяжело опускаешься на лицо Brax, с силой втирая свою [pc.asshole+] в его рот, пока он извивается и скулит под тобой.",
						
						"Потянувшись вниз, чтобы схватить Brax за пушистые волчьи уши, ты опускаешься ему на лицо, издавая восторженный визг, когда его язык проникает глубоко в твою [pc.asshole+].",
							
						"Чуть шире разведя ноги, ты садишься на волчью морду Brax, издавая отчаянный стон и с силой втирая свою [pc.asshole+] в его жадный язык.");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_GRIND_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Тереться";
		}

		@Override
		public String getActionDescription() {
			return "Грубо тереться о волчью морду Brax.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Желая поднять градус страсти, ты наваливаешься на лицо Brax, полностью затыкая его рот своей "+Main.game.getPlayer().getAssName(true)+". Когда он начинает скулить и извиваться, ты с силой вжимаешься своей киской в его волчью морду, издавая отчаянный визг, когда его язык проникает глубоко в твою [pc.asshole+].",
					
					"Позволив ногам подогнуться, ты тяжело опускаешься на лицо Brax, с силой втирая свою [pc.asshole+] в его рот, пока он извивается и скулит под тобой.",
					
					"Потянувшись вниз, чтобы схватить Brax за пушистые волчьи уши, ты опускаешься ему на лицо, издавая восторженный визг, когда его язык проникает глубоко в твою [pc.asshole+].",
						
					"Чуть шире разведя ноги, ты садишься на волчью морду Brax, издавая отчаянный стон и с силой втирая свою [pc.asshole+] в его жадный язык.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};

	public static final SexAction PLAYER_COWGIRL_PENETRATE_PUSSY = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Проникнуть в киску";
		}

		@Override
		public String getActionDescription() {
			return "Ввести [npc.penis+] Brax в свою "+Main.game.getPlayer().getVaginaName(true)+".";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE);
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty())
				UtilText.nodeContentSB.append("Ты приподнимаешься, позволяя острому собачьему члену Brax выскользнуть из твоей [pc.asshole+]. ");

			UtilText.nodeContentSB.append("Потянувшись вниз и обхватив [npc.penis+] под собой, ты ерзаешь, пока он не выровняется по твоему "+Main.game.getPlayer().getVaginaName(true)+". Не теряя ни секунды, ты быстро опускаешься, чувствуя, как сужающийся конец [npc.penis+] Brax легко входит в тебя. С отчаянным воплем ты садишься всё ниже, заглатывая всё больше и больше расширяющегося ствола в свою "+Main.game.getPlayer().getVaginaName(true)
					+", пока, наконец, в дрожи восторга, ты не чувствуешь, как раздутая узла у основания упирается в твои половые губы.");

			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_PENETRATE_ASS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Проникнуть в задницу";
		}

		@Override
		public String getActionDescription() {
			return "Ввести [npc.penis+] Brax в свою [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty())
				UtilText.nodeContentSB.append("Ты приподнимаешься, позволяя острому собачьему члену Brax выскользнуть из твоей "+Main.game.getPlayer().getVaginaName(true)+". ");

			UtilText.nodeContentSB.append("Потянувшись вниз и обхватив [npc.penis+] под собой, ты ерзаешь, пока он не выровняется по твоей [pc.asshole+]. Не теряя ни секунды, ты быстро опускаешься, чувствуя, как сужающийся конец [npc.penis+] Brax легко входит в тебя. С отчаянным воплем ты садишься всё ниже, заглатывая всё больше и больше расширяющегося ствола в свою [pc.ass], пока, наконец, в дрожи восторга, ты не чувствуешь, как раздутая узла у основания упирается в твою [pc.asshole+].");

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Прекратить проникновение";
		}

		@Override
		public String getActionDescription() {
			return "Вернуться в положение сидя на животе у Brax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "Вы приподнимаетесь, и, под разочарованный скулёж Brax, позволяете его [npc.penis+] выскользнуть из вашей "+Main.game.getPlayer().getVaginaName(true)+". Быстрым движением вы снова усаживаетесь на подтянутый, покрытый мехом живот волка-мальчика.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_RIDING_ANAL = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Прекратить проникновение";
		}

		@Override
		public String getActionDescription() {
			return "Вернуться в положение сидя на животе у Brax.";
		}

		@Override
		public String getDescription() {
			return "Вы приподнимаетесь, и, под разочарованный скулёж Brax, позволяете его [npc.penis+] выскользнуть из вашей [pc.asshole+]. Быстрым движением вы снова усаживаетесь на подтянутый, покрытый мехом живот волка-мальчика.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_TONGUE_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Прекратить проникновение";
		}

		@Override
		public String getActionDescription() {
			return "Вернуться в положение сидя на животе у Brax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "Вы приподнимаетесь, и, под разочарованный скулёж Brax, позволяете его языку выскользнуть из вашей "+Main.game.getPlayer().getVaginaName(true)+". Быстрым движением вы снова усаживаетесь на подтянутый, покрытый мехом живот волка-мальчика.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_TONGUE_RIDING_ANAL = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Прекратить проникновение";
		}

		@Override
		public String getActionDescription() {
			return "Вернуться в положение сидя на животе у Brax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "Вы приподнимаетесь, и, под слегка облегченный вздох Brax, позволяете его языку выскользнуть из вашей [pc.asshole+]. Быстрым движением вы снова усаживаетесь на подтянутый, покрытый мехом живот волка-мальчика.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_BRAXS_HANDS_ON_TITS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Brax на груди";
		}

		@Override
		public String getActionDescription() {
			return "Направьте лапы-руки Brax к своей груди, поощряя его ласкать ваши сиськи.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
				return UtilText.returnStringAtRandom(
						"Потянувшись вниз, вы берете лапы-руки Brax и ведете их к своей груди, издавая похотливый стон, когда он с жадностью начинает ощупывать и сжимать мягкую плоть ваших "+Main.game.getPlayer().getBreastName(true)+".",
						
						"Направляя лапы-руки Brax к своей обнаженной груди, вы издаете похотливый стон, чувствуя, как жесткие подушечки на нижней стороне его пальцев впиваются и сжимают ваши "+Main.game.getPlayer().getNippleName(true)+".",
						
						"Выпячивая грудь навстречу Brax, вы ведете его руки к своим обнаженным сиськам, издавая короткий вскрик, когда он начинает жадно щипать и сжимать ваши "+Main.game.getPlayer().getNippleName(true)+".",
							
						"Направляя руки Brax к вашей обнаженной "+Main.game.getPlayer().getBreastName(true)+", вы издаете короткий вскрик, когда он начинает жадно ощупывать вашу грудь, грубо вжимая жесткие подушечки своих ладоней в вашу мягкую плоть.");
			} else {
				return UtilText.returnStringAtRandom(
						"Потянувшись вниз, вы берете лапы-руки Brax и ведете их к своей груди, издавая похотливый стон, когда он с жадностью начинает ощупывать и сжимать ваши "+Main.game.getPlayer().getBreastName(true)
								+ " сквозь ваши "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Направляя лапы-руки Brax к своей груди, вы издаете похотливый стон, чувствуя, как его пальцы пытаются впиться и сжать ваши "+Main.game.getPlayer().getNippleName(true)+" сквозь ваши "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Вы выпячиваете грудь навстречу Brax, направляя его руки к своим соскам, и издаете короткий вскрик, когда он начинает жадно ощупывать вашу грудь через ваши "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
							
						"Направляя руки Brax к вашим "+Main.game.getPlayer().getBreastName(true)+", вы издаете тихий крик, когда он начинает жадно ласкать вашу грудь, грубо вдавливая жесткие подушечки своих ладоней в вашу мягкую плоть через ваши "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_SLOW = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Медленная езда";
		}

		@Override
		public String getActionDescription() {
			return "Медленно скользите вверх и вниз по твердому члену Brax.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Используя ноги, вы медленно поднимаетесь и опускаетесь, проталкивая [npc.penis+] Brax в вашу "+Main.game.getPlayer().getVaginaName(true)+" пока вы оба стонете от удовольствия.",
					
					"Наклонившись вперед, вы опираетесь руками, чтобы перенести часть веса, пока медленно двигаетесь вверх и вниз на [npc.penis+] Brax. Когда вы склоняете лицо к покорному волку, до вас доносится его мускусный мужской запах, и вы прикусываете губу, жадно вдыхая этот дурманящий аромат.",
					
					"Опустив руки на колени, вы помогаете себе ногами, скользя вверх и вниз на [npc.penis+] Brax, и издаете высокие стоны, насаживаясь на ухмыляющегося волка.",
						
					"Вы медленно приподнимаетесь, а затем снова опускаетесь, насаживаясь на свою "+Main.game.getPlayer().getVaginaName(true)+" на [npc.penis+] Brax, издавая похотливый стон.");
			
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_SLOW_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Медленная езда";
		}

		@Override
		public String getActionDescription() {
			return "Медленно скользите вверх и вниз по твердому члену Brax.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Используя ноги, ты медленно двигаешься вверх и вниз, то и дело вводя [npc.penis+] Brax в свою [pc.asshole+], пока вы оба стонете и кряхтите от удовольствия.",
					
					"Наклонившись вперед, вы опираетесь руками, чтобы перенести часть веса, пока медленно двигаетесь вверх и вниз на [npc.penis+] Brax. Когда вы склоняете лицо к покорному волку, до вас доносится его мускусный мужской запах, и вы прикусываете губу, жадно вдыхая этот дурманящий аромат.",
					
					"Опустив руки на колени, вы помогаете себе ногами, скользя вверх и вниз на [npc.penis+] Brax, и издаете высокие стоны, насаживаясь на ухмыляющегося волка.",
						
					"Ты медленно приподнимаешься, а затем снова опускаешься, насаживаясь своим [pc.asshole+] на [npc.penis+] Brax и издавая похотливый стон.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FAST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Быстрая скачка";
		}

		@Override
		public String getActionDescription() {
			return "Быстро подпрыгивай вверх-вниз на [npc.penis+] Brax.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Используя ноги, ты начинаешь стремительно подпрыгивать вверх и вниз, с силой вбивая [npc.penis+] Brax в свою "+Main.game.getPlayer().getVaginaName(true)
						+" со свистом и стонами от удовольствия.",
					
					"Наклонившись вперед, ты опираешься на волосатую мужскую грудь Brax, чтобы перенести на нее основной вес, а затем начинаешь быстро и ритмично двигать бедрами вверх-вниз на его [npc.penis+]. Когда твое лицо оказывается совсем близко к его телу, ты чувствуешь аромат его мужественного мускуса и закусываешь губу, жадно вдыхая этот дурманящий запах.",
					
					"Упершись руками в пол позади себя для опоры, ты начинаешь быстро подпрыгивать вверх-вниз на "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+" Brax, издавая дрожащие высокие стоны, то и дело насаживаясь на ухмыляющегося волка-мальчика.",
						
					"Усмехаясь Brax, ты с энтузиазмом начинаешь двигаться вверх и вниз, раз за разом вонзаясь в неё своим "+Main.game.getPlayer().getVaginaName(true)+" on Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+" издавая при этом серию высоких стонов.");
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FAST_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Быстрая скачка";
		}

		@Override
		public String getActionDescription() {
			return "Быстро подпрыгивай вверх-вниз на [npc.penis+] Brax.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Используя ноги, ты начинаешь быстро подпрыгивать вверх-вниз, с силой вбивая [npc.penis+] Brax то в свою [pc.asshole+], то обратно, при этом визжа и стоня от удовольствия.",
					
					"Наклонившись вперед, ты опираешься на волосатую мужскую грудь Brax, чтобы перенести на нее основной вес, а затем начинаешь быстро и ритмично двигать бедрами вверх-вниз на его [npc.penis+]. Когда твое лицо оказывается совсем близко к его телу, ты чувствуешь аромат его мужественного мускуса и закусываешь губу, жадно вдыхая этот дурманящий запах.",
					
					"Упершись руками в пол позади себя для опоры, ты начинаешь быстро подпрыгивать вверх-вниз на "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+" Brax, издавая дрожащие высокие стоны, то и дело насаживаясь на ухмыляющегося волка-мальчика.",
						
					"Усмехаясь, глядя на Brax сверху вниз, ты с энтузиазмом начинаешь двигаться вверх и вниз, раз за разом вонзая свой [pc.asshole+] в Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+" издавая при этом серию отчаянных стонов.");
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_CUM_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Поцелуй с кончой";
		}

		@Override
		public String getActionDescription() {
			return "Показать Brax, насколько вкусная у него сперма...";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.braxCumOnChest;
		}

		@Override
		public String getDescription() {
			return "Видя сперму Brax, разбрызганную по его волосатому животу и груди, вы внезапно ощущаете непреодолимое желание дать ему попробовать его же самого на вкус. Наклонившись, вы начинаете слизывать все липкое семя, вдыхая мощный мужской мускус волка-мальчика и не спеша стараясь не упустить ни единой капли.<br/><br/>Когда ваш рот наполняется вкусным соленым кремом, вы скользите вверх по груди Brax, пока не оказываетесь лицом к лицу с его довольно обеспокоенным взглядом. Прижавшись губами к его губам, он пытается отвернуться, но, не принимая отказа, вы тянетесь вверх и обхватываете его волчью морду, сжимая щеки, пока не заставляете его рот слегка приоткрыться.<br/><br/>Увидев шанс, вы просовываете язык между его губ и начинаете использовать это отверстие, чтобы передать ему полный рот вкусного крема. Brax начинает ерзать под вами, но его нежелание разделить с вами любовь к сперме лишь укрепляет вашу решимость, и вскоре вам удается протолкнуть значительное количество его собственного семени ему в рот.<br/><br/>Оставив немного для себя, вы прерываете поцелуй, быстро сжимая руку на морде Brax, чтобы он не растратил свою драгоценную джизз впустую. Больше не в силах сопротивляться, вы демонстративно проглатываете небольшое количество вкусной спермы, оставшейся у вас во рту, а затем велите Brax сделать то же самое.<br/><br/>Вы держите морду Brax закрытой, пока не увидите, как он неохотно подражает вашему поведению, и, когда вы отпускаете его, он послушно открывает рот, показывая, что проглотил всё до последней капли. В восторге от выступления волка-мальчика, вы наклоняетесь и делитесь с ним страстным, пропитанным спермой поцелуем.";
		}

		@Override
		public void applyEffects() {
			if (SexFlags.braxCumOnChest)
				SexFlags.braxCumOnChest = false;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FEED_CUM = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Накормить Brax";
		}

		@Override
		public String getActionDescription() {
			return "Используй пальцы, чтобы скормить Brax его собственную сперму.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.braxCumOnChest;
		}

		@Override
		public String getDescription() {
			return "Видя сперму Brax, разбрызганную по его волосатому животу и груди, вы внезапно ощущаете непреодолимое желание дать ему попробовать его же самого на вкус. Наклонившись, вы начинаете собирать все липкое семя пальцами, вдыхая мощный мужской мускус волка-подростка и не спеша стараясь не упустить ни единой капли.<br/><br/>Когда ваша ладонь наполняется вкусным соленым кремом, вы склоняетесь над грудью Brax, чтобы заглянуть в его довольно обеспокоенные глаза. Когда вы подносите свои испачканные спермой пальцы к его рту, Brax пытается отвернуться, но, не принимая отказов, вы свободной рукой хватаете его волчью морду, сжимая щеки, пока не заставляете его рот слегка приоткрыться.<br/><br/>Увидев шанс, вы просовываете свои липкие пальцы за его губы и начинаете использовать это отверстие, чтобы влить в него пригоршню вкусного крема. Brax начинает ерзать под вами, но его нежелание разделить вашу любовь к сперме лишь укрепляет вашу решимость, и вскоре вам удается протолкнуть значительное количество его собственного семени ему в рот.<br/><br/>Довольная тем, что его рот полон, вы убираете пальцы, быстро обхватывая другой рукой морду Brax, чтобы он не растерял свою драгоценную джизз. Хихикая над расстроенным волком, вы приказываете ему всё проглотить.<br/><br/>Вы держите морду Brax закрытой, пока не увидите, что он неохотно подчиняется вашей команде, и, когда вы отпускаете его, он послушно открывает рот, показывая, что всё проглотил. В восторге от выступления волка, вы протягиваете свою испачканную спермой руку, и он, смирившись с тем, что у него нет особого выбора, принимается вылизывать вашу ладонь дочиста.";

		}

		@Override
		public void applyEffects() {
			if (SexFlags.braxCumOnChest)
				SexFlags.braxCumOnChest = false;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			}
		}
	};

	// Partner's actions:

	public static final SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brax вскрикивает, пока ты скачешь вверх-вниз на его [npc.penis+], ",
						"Сквозь стоны Brax выкрикивает тебе, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Аах!~ Да! Продолжай скакать на мне!",

							"Блядь! ~Аах!~ Я твой маленький бета!",

							"~Аах!~ Я твоя маленькая игрушка!",

							"Я твой послушный маленький бета! ~Аах!~ Используй меня!"), Main.sex.getCharacterPerformingAction()));
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brax вскрикивает, пока ты скачешь вверх-вниз на его [npc.penis+], ",
						"Сквозь стоны Brax выкрикивает тебе, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Аах!~ Да! Продолжай скакать на мне!",

							"Блядь! ~Аах!~ Я твой маленький бета!",

							"~Аах!~ Я твоя маленькая игрушка!",

							"Я твой послушный маленький бета! ~Аах!~ Используй меня!"), Main.sex.getCharacterPerformingAction()));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Подняв взгляд из-за твоих ног, Brax бормочет прямо в твою киску, ",
						"Brax бормочет, глядя на тебя снизу вверх, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Мрф!~ Любимый... ~Мрф!~ Киска!",

							"~Ммм!~ Я... ~Мрф!~ Бета!",

							"~Мрф!~ Ты... ~Мрф!~ Альфа!",

							"~Мрф!~ Вкус... ~Ммм!~ Офигенный!"), Main.sex.getCharacterPerformingAction()));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brax бормочет в твою задницу, ",
						"Brax бормочет, глядя на тебя снизу вверх, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Мрф!~ Еще... ~Мрф!~ Сколько можно...",

							"~Ммм!~ Я... ~Мрф!~ Бета!",

							"~Мрф!~ Ты... ~Мрф!~ Альфа!",

							"~Мрф!~ Надеюсь... ~Ммм!~ Счастлив!"), Main.sex.getCharacterPerformingAction()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Глядя на тебя снизу вверх, Brax бормочет: ",
						"Brax бормочет, глядя на тебя снизу вверх, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"Я буду хорошим маленьким бетой...",

							"Я буду делать всё, что ты скажешь...",

							"Я... я бы хотел, чтобы ты оседлала меня... пожалуйста...",

							"Ты мой альфа..."), Main.sex.getCharacterPerformingAction()));
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_CUNNILINGUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax замирает, издавая тихие стоны, когда он жадно вылизывает твою "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"Когда ты садишься Brax на лицо, он почти не шевелится, издавая тихие скулящие звуки, когда он жадно слизывает твою "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"Brax счастливо стонет под тобой, кажется, он вполне доволен тем, что сосет тебя, словно покорная сучка.",
						
					"Пока Brax счастливо вылизывает твою "+Main.game.getPlayer().getVaginaName(true)+", он остается неподвижным, глядя на тебя снизу вверх своими огромными покорными глазами.");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_ANILINGUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax замирает, издавая тихие стоны, пока послушно вылизывает твою [pc.asshole+].",
					
					"Пока ты сидишь лицом на лице Brax, он почти не шевелится, издавая тихие скулящие звуки, усердно слизывая твою [pc.asshole+].",
					
					"Brax стонет под тобой, кажется, он вполне доволен тем, что делает тебе анилингус, словно покорная сучка.",
						
					"Пока Brax вылизывает твою [pc.asshole+], он остается неподвижным, время от времени издавая низкие покорные скулящие звуки.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_RIDING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax замирает, издавая удовлетворенные тихие стоны, пока ты вонзаешь свою "+Main.game.getPlayer().getVaginaName(true)+" в его [npc.penis+].",
					
					"Пока ты продолжаешь скакать на [npc.penis+] Brax, он почти не шевелится, издавая тихие скулящие звуки каждый раз, когда его узел ударяется о твою "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"Brax стонет под тобой, кажется, он вполне доволен тем, что просто лежит неподвижно и позволяет тебе скакать на его [npc.penis+].",
						
					"Пока ты продолжаешь вонзать свою "+Main.game.getPlayer().getVaginaName(true)+" находясь на [npc.penis+] Brax, он остается неподвижным, глядя на тебя своими большими покорными глазами.");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax замирает, издавая довольные тихие стоны, пока ты вонзаешь свой [pc.asshole+] в его [npc.penis+].",
					
					"Пока ты продолжаешь скакать на [npc.penis+] Brax, он сидит почти неподвижно, издавая тихие скулящие звуки каждый раз, когда его узел ударяется о твою [pc.asshole+].",
					
					"Brax стонет под тобой, кажется, он вполне доволен тем, что просто лежит неподвижно и позволяет тебе скакать на его [npc.penis+].",
						
					"Пока ты продолжаешь вонзать свой [pc.asshole+] в [npc.penis+] Brax, он остается неподвижным, глядя на тебя своими большими покорными глазами.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
		
	};
	
	public static final SexAction PARTNER_COWGIRL_MASTURBATE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, null)),
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
		public String getDescription() {
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)
					|| Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				return UtilText.returnStringAtRandom(
						"Пока ты продолжаешь ласкать его языком, Brax тянется вниз, чтобы схватить свой [npc.penis+], издавая серию жалких всхлипов, когда начинает дрочить сам себе.",

						"Все еще зарывшись языком между твоих ног, Brax берется за свой [npc.penis+] и с низким стоном начинает мастурбировать под тобой.",
						
						"Поскольку его [npc.penis+] остается без внимания, Brax тянется рукой между ног и начинает покорно дрочить.",
							
						"Пока ты продолжаешь сидеть у него на лице, Brax тянется к своему паху и, проведя мгновение, сжимая и поглаживая свой толстый узел, начинает покорно ласкать свой [npc.penis+].");
			
			} else {
				return UtilText.returnStringAtRandom(
						"Сидя у него на животе, ты чувствуешь, как [npc.penis+] Brax упирается в твою поясницу, и с умоляющим скулящим звуком он обхватывает себя руками и начинает покорно дрочить.",

						"Уровень возбуждения Brax дает о себе знать, когда ты чувствуешь, как его [npc.penis+] упирается тебе в поясницу. С забавной ухмылкой ты смотришь вниз на покорного волка, который, обхватив себя руками, начинает мастурбировать за твоей спиной.",
						
						"Поскольку его [npc.penis+] не получает никакого внимания, Brax просовывает руку между ног и начинает покорно мастурбировать.",
							
						"С жалким скулением Brax тянется к своему паху и, потратив мгновение на то, чтобы сжать и погладить свой толстый узел, начинает покорно ласкать свой [npc.penis+].");
			}
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_BOUNCE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax мягко подает бедрами в такт твоим прыжкам, помогая насадить твой "+Main.game.getPlayer().getVaginaName(true)+" в его [npc.penis+].",
					
					"Пока ты продолжаешь скакать на нем, Brax начинает двигать бедрами вверх, помогая наполнить твой "+Main.game.getPlayer().getVaginaName(true)+" его [npc.penis+].",
					
					"С похотливым стоном Brax начинает подбрасывать бедра навстречу тебе, помогая тебе оседлать его [npc.penis+].",
						
					"Пока ты продолжаешь вонзать свою "+Main.game.getPlayer().getVaginaName(true)+" находясь на [npc.penis+] Brax, он начинает мягко подавать бедрами вверх в такт твоим прыжкам.");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_BOUNCE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
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
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax мягко подает бедрами в такт твоим прыжкам, помогая насадить твой [pc.asshole+] на его [npc.penis+].",
					
					"Пока ты продолжаешь скакать на нем, Brax начинает двигать бедрами вверх, помогая наполнить твой [pc.asshole+] его [npc.penis+].",
					
					"С похотливым стоном Brax начинает подбрасывать бедра навстречу тебе, помогая тебе оседлать его [npc.penis+].",
						
					"Пока ты продолжаешь вбивать свой [pc.asshole+] в [npc.penis+] Brax, он начинает плавно подбрасывать бедра в такт твоим толчкам.");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_GROPE_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
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
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
				return UtilText.returnStringAtRandom(
						"Заметив, что Brax во все глаза пялится на твою грудь, ты игриво хихикаешь и направляешь его руки к своим соскам, где он тут же принимается жадно ощупывать и сжимать твою мягкую плоть, "+Main.game.getPlayer().getBreastName(true)+".",
						
						"Brax многозначительно тянет свои лапы к твоей обнаженной груди, и, решив не мешать ему, ты издаешь похотливый стон, когда он начинает щипать и сжимать твою "+Main.game.getPlayer().getNippleName(true)+".",
						
						"Brax тянется к твоей груди, и, позволяя ему немного развлечься, ты даешь ему возможность с азартом щипать и сжимать твою "+Main.game.getPlayer().getNippleName(true)+", отчего ты издаешь похотливый стон.",
							
						"Позволяя Brax дотянуться до твоей обнаженной "+Main.game.getPlayer().getBreastName(true)+", вы издаете короткий вскрик, когда он начинает жадно ощупывать вашу грудь, грубо вжимая жесткие подушечки своих ладоней в вашу мягкую плоть.");
			} else {
				return UtilText.returnStringAtRandom(
						"Заметив, что Brax во все глаза пялится на твою грудь, ты игриво хихикаешь и направляешь его руки к своим соскам, издавая похотливый стон, когда он с азартом принимается ощупывать и сжимать твою "+Main.game.getPlayer().getBreastName(true)
								+ " сквозь ваши "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Brax многозначительно тянет свои лапы к твоей обнаженной груди, и, решив не мешать ему, ты издаешь похотливый стон, чувствуя, как его пальцы пытаются щипать и сжимать твою "+Main.game.getPlayer().getNippleName(true)+" сквозь ваши "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Brax тянется к твоей груди, и, позволяя ему немного развлечься, ты даешь ему возможность с азартом ощупывать твою грудь через твою "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
							
						"Позволяя Brax дотянуться до твоей обнаженной "+Main.game.getPlayer().getBreastName(true)+", ты издаешь короткий вскрик, когда он с жадностью начинает ощупывать твою грудь, грубо прижимая свои лапы-руки к твоей "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
		}
	};
	
	// Player's orgasms:

	public static final SexAction PLAYER_COWGIRL_ORGASM_NO_PEN = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
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
			return "Оргазм";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что приближаешься к кульминации.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Чувствуя, что достигаешь пика, ты издаешь отчаянный стон и готовишься к оргазму. ");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
				
				if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
					UtilText.nodeContentSB.append(" Потянувшись вниз, ты хватаешь свой "+Main.game.getPlayer().getPenisName(true)+", после чего начинаешь неистово мастурбировать, плавно покачиваясь взад-вперед на пушистом животе Brax. Издавая отчаянный стон, ты чувствуешь, как твой член дергается под натиском нахлынувшего оргазма,");
					
				} else {
					UtilText.nodeContentSB.append(" Плавно покачиваясь на пушистом животе Brax, ты тянешься вниз и начинаешь тереть свой "+Main.game.getPlayer().getPenisName(true)+" сквозь ваши "
							+ Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+". Издавая отчаянный стон, ты чувствуешь, как твой член дергается под натиском нахлынувшего оргазма,");
				}

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", но тебе не удается выдавить даже капли спермы, что несколько притупляет удовольствие от кульминации.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", и тонкая струйка спермы вырывается"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", и немного спермы брызжет"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", и твоя сперма брызжет"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", и твоя сперма брызжет"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", и твоя огромная порция спермы выливается"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", и твоя огромная порция спермы выливается"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", и твоя огромная порция спермы вытекает"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" прямо на мускулистую грудь Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					default:
						break;
				}
				
				// Vagina:
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
					UtilText.nodeContentSB.append("<br/><br/>Когда твои яички окончательно опустошаются на животе Brax, ты чувствуешь, как во второй раз в паху нарастает волна неистового жара. Ухватившись за грудь Brax, чтобы удержаться, ты чувствуешь слабость в коленях и сжимаешь бедра, пока твоя горячая щель содрогается и дрожит. Очередной разрывающий мозг оргазм накрывает тебя, и ты стонешь и визжишь от восторга, пока твоя женская киска дарит тебе волну неистового наслаждения.");
			
			} else if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append(" Мягко покачиваясь взад-вперед на пушистом животе Brax, ты чувствуешь, как в паху нарастает неистовый жар. Ухватившившись за грудь волчонка, чтобы удержаться, ты чувствуешь слабость в коленях и сжимаешь бедра, пока твоя горячая щель содрогается и дрожит. Разрывающий мозг оргазм накрывает тебя, и ты стонешь и визжишь от восторга, пока твоя женская киска дарит тебе волну неистового наслаждения.");
				
			}else {
				UtilText.nodeContentSB.append(" Мягко покачиваясь взад-вперед на пушистом животе Brax, ты чувствуешь, как в твоей бесполой лоно нарастает неистовый жар. Ухватившившись за грудь волчонка, чтобы удержаться, ты чувствуешь слабость в коленях и сжимаешь бедра, пока твоя кукольная промежность посылает разряды удовольствия по всему телу. Разрывающий мозг оргазм накрывает тебя, и ты стонешь и визжишь от восторга, отчаянно лаская гладкую и чувствительную зону между ног.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};

	public static final SexAction PLAYER_COWGIRL_ORGASM_FACESITTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
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
			return "Оргазм";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что приближаешься к кульминации.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE))
					|| (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE))
				UtilText.nodeContentSB.append("Чувствуя, как язык Brax слизывает влагу с твоего [pc.asshole+], ты понимаешь, что близишься к пику, и с отчаянным стоном готовишься к оргазму. ");
			else
				UtilText.nodeContentSB.append("Чувствуя, как язык Brax слизывает влагу с твоего "+Main.game.getPlayer().getVaginaName(true)+", ты понимаешь, что близишься к пику, и с отчаянным стоном готовишься к оргазму. ");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
				
				if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
					UtilText.nodeContentSB.append(" Потянувшись вниз, ты хватаешь свой "+Main.game.getPlayer().getPenisName(true)+", яростно мастурбируя и притираясь к волчьей морде Brax. Издав отчаянный стон, ты чувствуешь, как твои "+Main.game.getPlayer().getPenisName(true)+" пульсируют под натиском нахлынувшего оргазма");
					
				} else {
					UtilText.nodeContentSB.append(" Притираясь к волчьей морде Brax, ты тянешься вниз и начинаешь теребить свои "+Main.game.getPlayer().getPenisName(true)+" сквозь ваши "
							+ Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+". Издав отчаянный стон, ты чувствуешь, как твои "+Main.game.getPlayer().getPenisName(true)+" пульсируют под натиском нахлынувшего оргазма");
				}

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", но тебе не удается выдавить даже капли спермы, что несколько притупляет удовольствие от кульминации.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", и тонкая струйка спермы вырывается"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", и немного спермы брызжет"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", и твоя сперма брызжет"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", и твоя сперма брызжет"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", и твоя огромная порция спермы выливается"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", и твоя огромная порция спермы выливается"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", и твоя огромная порция спермы вытекает"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" вытекает на пол прямо над головой Brax."
									:" позорно вытекает тебе в "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					default:
						break;
				}
				
				// Vagina:
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
					UtilText.nodeContentSB.append("<br/><br/>Когда твои яички окончательно опустошаются, ты чувствуешь, как во второй раз в паху нарастает волна неистового жара. Ноги подкашиваются, и, полностью рухнув на лицо Brax, ты плотно сжимаешь бедра, пока твоя горячая щель содрогается и трепещет. Очередной разрывающий мозг оргазм накрывает тебя, и ты стонешь и визжишь от восторга, пока твоя женская киска дарит тебе волну невероятного наслаждения.");
			
			} else if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append( "Издавая восторженные визги, ты чувствуешь, как в паху нарастает неистовый жар. Ноги подкашиваются, и, полностью рухнув на лицо Brax, ты плотно сжимаешь бедра, пока твоя горячая щель содрогается и трепещет. Разрывающий мозг оргазм накрывает тебя, и ты стонешь и визжишь от восторга, пока твоя женская киска дарит тебе волну невероятного наслаждения.");
				
			}else {
				UtilText.nodeContentSB.append(" Мягко покачиваясь взад-вперед на пушистом животе Brax, ты чувствуешь, как в твоей бесполой лоно нарастает неистовый жар. Ухватившившись за грудь волчонка, чтобы удержаться, ты чувствуешь слабость в коленях и сжимаешь бедра, пока твоя кукольная промежность посылает разряды удовольствия по всему телу. Разрывающий мозг оргазм накрывает тебя, и ты стонешь и визжишь от восторга, отчаянно лаская гладкую и чувствительную зону между ног.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_ORGASM_RIDING_COCK = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
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
			return "Оргазм";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что приближаешься к кульминации.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS))
					|| (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS))
				UtilText.nodeContentSB.append("Чувствуя приближение кульминации, ты ускоряешь темп, прыгая вверх-вниз на [npc.penis+] Brax. Издавая отчаянный стон, ты теряешь рассудок от ощущений в своих [pc.asshole+] и готовишься к оргазму. ");
			else
				UtilText.nodeContentSB.append("Чувствуя приближение кульминации, ты ускоряешь темп, прыгая вверх-вниз на [npc.penis+] Brax. Издавая отчаянный стон, ты теряешь рассудок от ощущений в своих "+Main.game.getPlayer().getVaginaName(true)+", и готовишься к оргазму. ");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
					UtilText.nodeContentSB.append(
							"Твой [pc.cock+] внезапно начинает неистово пульсировать, и ты понимаешь, что вот-вот кончишь. Схватив свой твердый член одной рукой, ты направляешь его к лицу Brax, яростно мастурбируя в предвкушении оргазма.");

				UtilText.nodeContentSB.append(" Издавая отчаянный стон, ты чувствуешь, как твой член дергается под напором нахлынувшего оргазма. ");

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("К сожалению, тебе не удается выжать даже каплю спермы, что несколько притупляет удовольствие от кульминации.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("Тонкая струйка спермы брызжет на грудь Brax, и ты удовлетворенно стонешь, извергая свое липкое семя.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("Небольшое количество спермы брызжет на грудь Brax, и ты удовлетворенно стонешь, извергая свое липкое семя.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append("Твоя сперма вылетает струей под пульсацию члена, и ты удовлетворенно стонешь, забрызгивая все грудь Brax своим липким семенем.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append("Твоя сперма вылетает струей под пульсацию члена, и ты удовлетворенно стонешь, забрызгивая все грудь Brax своим липким семенем. Несколько капель долетают даже до его лица, и он вздрагивает, когда твоя соленая сперма приземляется вокруг его рта.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append("Твоя огромная порция вырывается наружу под пульсацию члена, и ты удовлетворенно стонешь, заливая липкое семя всё грудь Brax. Брызги спермы долетают до его лица, и он вздрагивает, когда соленая жидкость оседает вокруг его рта.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append("Твоя огромная порция вырывается наружу под пульсацию члена, и ты удовлетворенно стонешь, забрызгивая липким семенем грудь Brax. Несколько густых струй спермы долетают до его лица, и он вздрагивает, когда соленая жидкость оседает вокруг его рта.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append("Твоя колоссальная порция вырывается наружу под пульсацию члена, и ты удовлетворенно стонешь, забрызгивая липким семенем грудь Brax. Несколько густых струй спермы долетают до его лица, и он вздрагивает, когда твоя соленая сперма полностью покрывает его торс и лицо.");
					break;
				default:
					break;
				}

				UtilText.nodeContentSB.append("<br/><br/>");
			}

			// Vagina:
			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
					UtilText.nodeContentSB.append("Пока ты продолжаешь насаживаться своей задницей на узловатый собачий член Brax, ты чувствуешь, как в киске нарастает нестерпимый жар. Ухватившись за грудь Brax, чтобы удержаться, ты чувствуешь слабость в коленях и сжимаешь бедра, пока твоя горячая щель содрогается и дрожит. Ошеломляющий оргазм накрывает тебя, и ты стонешь и визжишь от восторга, когда твоя женская киска дарит тебе волну невероятного наслаждения.");
				} else {
					UtilText.nodeContentSB.append("Пока ты продолжаешь насаживаться своей киской на узловатый собачий член Brax, ты чувствуешь, как в паху нарастает нестерпимый жар. Ухватившись за грудь Brax, чтобы удержаться, ты чувствуешь слабость в коленях и сжимаешь бедра, пока твоя горячая щель содрогается и дрожит, сжимаясь вокруг пульсирующего члена, глубоко засевшего в твоей щели. Ошеломляющий оргазм накрывает тебя, и ты стонешь и визжишь от восторга, когда твоя женская киска дарит тебе волну невероятного наслаждения.");
				}
			}

			// Mound:
			if (Main.game.getPlayer().getPenisType() == PenisType.NONE && Main.game.getPlayer().getVaginaType() == VaginaType.NONE) {
				if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
					UtilText.nodeContentSB.append("Пока ты продолжаешь насаживаться своей задницей на узловатый собачий член Brax, ты чувствуешь, как в твоей бесполой промежности нарастает нестерпимый жар. Ухватившись за грудь Brax, чтобы удержаться, ты чувствуешь слабость в коленях и плотно сжимаешь бедра, пока твоя кукольная промежность посылает разряды удовольствия вверх по всему телу. Ошеломляющий оргазм накрывает тебя волной, и ты стонешь и визжишь от восторга, отчаянно лаская гладкую и чувствительную зону между ног.");
				} else {
					UtilText.nodeContentSB.append("Пока ты скользишь вперед-назад по животу Brax, ты чувствуешь, как в твоей бесполой промежности нарастает нестерпимый жар. Ухватившись за грудь Brax, чтобы удержаться, ты чувствуешь слабость в коленях и плотно сжимаешь бедра, пока твоя кукольная промежность посылает разряды удовольствия вверх по всему телу. Ошеломляющий оргазм пронзает тебя, и ты стонешь и визжишь от восторга, отчаянно лаская гладкую и чувствительную зону между ног.");
				}
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
//			Main.sex.removePenetration(PenetrationType.PENIS, OrificeType.VAGINA); TODO
//			Main.sex.removePenetration(PenetrationType.PENIS, OrificeType.ANUS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
		}
	};

	// Partner's orgasms:

	public static final SexAction PARTNER_COWGIRL_ORGASM = new SexAction(
			SexActionType.ORGASM,
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
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
				return "Стоны Brax становятся всё громче, и ты понимаешь, что он вот-вот кончит. Чувствуя, как толстый член в твоей заднице бешено пульсирует, ты внезапно охвачена отчаянной потребностью наполнить свой зад его липким семенем. В последний раз приподнявшись, ты с силой опускаешься на его разбухший член, вбивая жирную узелковую шишку у основания его члена глубоко в свой анус. Ты издаешь испуганный крик, когда чувствуешь, как узел мгновенно раздувается, намертво запирая дергающийся собачий член Brax глубоко в твоей заднице.<br/>Brax издает дикий стон, и ты извиваешься от восторга, чувствуя, как его член извергает мощную порцию семени прямо в твою шлюшью задницу. Спустя несколько мгновений ты чувствуешь, как узел спадает, и с влажным хлопком он выскальзывает, освобождая тебя.";
				
			} else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
				return "Стоны Brax становятся всё громче, и ты понимаешь, что он вот-вот кончит. Чувствуя, как толстый член в твоей киске бешено пульсирует, ты внезапно охвачена отчаянной потребностью "+(Main.game.getPlayer().isVisiblyPregnant()?"be":"наполнить твою матку")+" его липким семенем. В последний раз приподнявшись, ты с силой опускаешься на его разбухший член, вбивая жирную узелковую шишку у основания его члена глубоко в твою жадную щель. Ты издаешь испуганный крик, когда чувствуешь, как узел мгновенно раздувается, намертво запирая дергающийся собачий член Brax глубоко в твоей влажной щели.<br/>Brax издает дикий стон, и ты извиваешься от восторга, чувствуя, как его член извергает мощную порцию семени прямо в твою "+(Main.game.getPlayer().isVisiblyPregnant()?"ждущую киску":"ждущую матку")+". Спустя несколько мгновений ты чувствуешь, как узел сдувается, и с влажным хлопком он выскальзывает, снова освобождая тебя.";
				
			} else {
				return "Стоны Brax становятся всё громче, и ты понимаешь, что он вот-вот кончит. Оглянувшись на его [npc.penis+], ты внезапно ощущаешь отчаянную потребность начать ласкать его. Подвинувшись назад, чтобы сесть ему на бедра, ты тянешься вниз и обхватываешь его массивный член обеими руками. Проводя ладонями вверх и вниз по всей длине, ты надавливаешь на толстый узел у основания, а затем поднимаешься выше, чтобы потереть сужающуюся головку. Ты издаешь короткий вскрик, чувствуя, как узел раздувается, и направляешь член прямо к лицу Brax, ощущая его первые подергивания.<br/>Brax издает дикий стон, а ты довольно ерзаешь, видя, как его член извергает мощную порцию семени прямо на грудь Brax. Спустя несколько мгновений оргазм Brax заканчивается, и когда узел сдувается, он издает удовлетворенный стон.";
			}
		}

		@Override
		public void applyEffects() {
//			if(Main.sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS)==null && Main.sex.getPenetrationTypeInOrifice(OrificeType.VAGINA)==null)
//				SexFlags.braxCumOnChest = true; TODO
		}
		

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Main.sex.getTargetedPartner(cumProvider))) {
				if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.VAGINA).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.ANUS).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.MOUTH).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.NIPPLE).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.NIPPLE);
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty())
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty())
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			else
				return null;
		}
	};
	
	
}
