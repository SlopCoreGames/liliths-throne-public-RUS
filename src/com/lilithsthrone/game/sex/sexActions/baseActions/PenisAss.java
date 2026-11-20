package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.90
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisAss {
	
	public static final SexAction TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Дразнящая игра «горячим хот-догом»";
		}

		@Override
		public String getActionDescription() {
			return "Просунь [npc.cockHead] своего [npc.cock] между ягодицами [npc2.namePos].";
		}
		
		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко сжимая [npc2.namePos] [npc2.assSize] ягодицы вместе, [npc.name] [npc.verb(start)] медленно скользит [npc.cockHead] [npc.her] [npc.cock+] вверх и вниз в образовавшейся щели.",

							"Нежно обхватив [npc2.namePos] [npc2.ass+], [npc.name] медленно [npc.verb(push)] сжимает [npc2.her] ягодицы, прежде чем начать дразнить [npc.her] [npc.cock+] в этой щели.",

							"Мягко сжимая каждую из [npc2.namePos] ягодиц, [npc.name] медленно [npc.verb(push)] их вместе, а затем дразня [npc.cockHead] [npc.her] [npc.cock+], зажатый между образовавшейся щелью."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо прижимая [npc2.namePos] [npc2.assSize] ягодицы друг к другу, [npc.name] [npc.verb(start)] с силой скользит [npc.cockHead] [npc.her] [npc.cock+] вверх и вниз в образовавшейся щели.",

							"Крепко схватив [npc2.namePos] [npc2.ass+], [npc.name] с силой [npc.verb(push)] сжимает [npc2.her] ягодицы, после чего начинает дразнить [npc.her] [npc.cock+] в этой щели.",

							"С силой сжав каждую из [npc2.namePos] ягодиц, [npc.name] грубо [npc.verb(push)] их вместе, а затем втирая [npc.cockHead] [npc.her] [npc.cock+] в образовавшуюся между ними щель."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Алчно сжимая [npc2.namePos] [npc2.assSize] ягодицы вместе, [npc.name] с жадностью [npc.verb(start)] скользит [npc.cockHead] [npc.her] [npc.cock+] вверх и вниз в образовавшейся щели.",

							"Крепко обхватив [npc2.namePos] [npc2.ass+], [npc.name] с энтузиазмом [npc.verb(push)] сжимает [npc2.her] ягодицы, после чего начинает дразнить [npc.her] [npc.cock+] в этой щели.",

							"Жадно сжав каждую из [npc2.namePos] ягодиц, [npc.name] с нетерпением [npc.verb(push)] их вместе, а затем с энтузиазмом дразнит [npc.cockHead] [npc.her] [npc.cock+], проталкивая их в образовавшуюся щель."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сжимая [npc2.namePos] [npc2.assSize] ягодицы вместе, [npc.name] с жадностью [npc.verb(start)] скользит [npc.cockHead] [npc.her] [npc.cock+] вверх и вниз в образовавшейся щели.",

							"Крепко обхватив [npc2.namePos] [npc2.ass+], [npc.name] с энтузиазмом [npc.verb(push)] сжимает [npc2.her] ягодицы, после чего начинает дразнить [npc.her] [npc.cock+] в этой щели.",

							"Сжав каждую из [npc2.namePos] ягодиц, [npc.name] [npc.verb(push)] их вместе, а затем с энтузиазмом дразнит [npc.cockHead] [npc.her] [npc.cock+], проталкивая их в образовавшуюся щель."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc.verb(anticipate)] [npc.namePos] [npc.cock+] врывается в [npc2.her] [npc2.ass+].",
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc.verb(prepare)] чувствовать, как [npc.namePos] [npc.cock+] втискивается в [npc2.her] [npc2.ass+].",
									" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(anticipate)] [npc.namePos] [npc.cock+] глубоко вбивается в [npc2.her] [npc2.ass+]."));
							break;
						case DOM_ROUGH:
						case SUB_NORMAL:
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] жадно [npc.verb(anticipate)] [npc.namePos] [npc.cock+] втискивается в [npc2.her] [npc2.ass+].",
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc2.she] жадно [npc.verb(prepare)] чувствовать, как [npc.namePos] [npc.cock+] втискивается в [npc2.her] [npc2.ass+].",
									" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(anticipate)] [npc.namePos] [npc.cock+] глубоко вбивается в [npc2.her] [npc2.ass+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc.verb(dread)] ощущение того, как [npc.namePos] [npc.cock+] вбивается в [npc2.her] [npc2.ass+].",
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] [npc.verb(anticipate)] [npc.namePos] [npc.cock+] втискивается в [npc2.her] [npc2.ass+].",
									" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(anticipate)] [npc.namePos] [npc.cock+] глубоко вбивается в [npc2.her] [npc2.ass+]."));
							break;
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], [npc2.speech(Да, используй мою задницу!)]",
									" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], прежде чем взмолиться: [npc2.speech(Давай же, используй мою задницу!)]",
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Да! Трахни мою задницу!)]"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], [npc2.speech(Давай! Используй мою задницу! Постарайся получше!)]",
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем прорычать: [npc2.speech(Вот так, используй мою задницу, сучка!)]",
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(demand)], [npc2.speech(Трахни мою задницу! Прямо сейчас!)]"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], [npc2.speech(Да! Используй мою задницу! Я должна доставить удовольствие твоему члену!)]",
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем взмолиться: [npc2.speech(Давай же! Пожалуйста! Ну используй же мою задницу!)]",
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Да! Трахни мою задницу! Мне нужен твой член!)]"));
							break;
						case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из-за [npc2.namePos] [npc2.lips+], [npc2.speech(Нет! Не надо! Пожалуйста! Уйди от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] выскальзывает из-за [npc2.namePos] [npc2.lips+], [npc2.speech(Вот так, используй мою задницу!)]",
									" [npc2.Name] [npc2.verb(let)] [npc2.moan], прежде чем взмолиться: [npc2.speech(Пожалуйста! Используй мою задницу!)]",
									" [npc2.Name] [npc2.moanVerb] вслух, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Давай же, используй мою задницу уже!)]"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнящая игра «горячим хот-догом»";
		}

		@Override
		public String getActionDescription() {
			return "Просунь [npc2.cockHead] [npc2.namePos] [npc2.cock] между ягодицами.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean canReachPenis = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.PENIS)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.PENIS).contains(SexAreaPenetration.FINGER)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			if(!canReachPenis) { // No available finger-penis actions, so can't reach penis
				return false;
			}
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Слегка сдвинув [npc.her] [npc.hips], [npc.name] [npc.verb(reach)] назад и [npc.verb(press)] [npc.her] ягодицы вместе, медленно протаскивай [npc2.cockHead] [npc2.namePos] [npc2.cock+] вверх и вниз в образовавшейся щели.",

							"Мягко обхватив [npc.her] [npc.ass], [npc.name] медленно [npc.verb(push)] [npc.her] ягодицы вместе, прежде чем начать дразнить [npc2.namePos] [npc2.cock+] в щели.",

							"Мягко сжимая каждую из [npc.her] ягодиц, [npc.name] медленно [npc.verb(push)] их вместе, прежде чем дразнить [npc2.cockHead] [npc2.her] [npc2.cock+] в образовавшейся складке."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Слегка сдвинув [npc.her] [npc.hips], [npc.name] [npc.verb(reach)] назад и грубо [npc.verb(press)] [npc.her] ягодицы вместе, прежде чем с силой протаскивать [npc2.cockHead] [npc2.namePos] [npc2.cock+] вверх и вниз в образовавшейся щели.",

							"Крепко обхватив [npc.her] [npc.ass], [npc.name] грубо [npc.verb(push)] [npc.her] ягодицы вместе, прежде чем начать с силой дразнить [npc2.namePos] [npc2.cock+] в щели.",

							"Крепко сжав каждую из [npc.her] ягодиц, [npc.name] с силой [npc.verb(push)] их вместе, а затем грубо лаская [npc2.cockHead] [npc2.namePos] [npc2.cock+] в образовавшейся щели."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Слегка сдвинув [npc.her] [npc.hips], [npc.name] [npc.verb(reach)] назад и с нетерпением [npc.verb(press)] [npc.her] ягодицы вместе, а затем с удовольствием скользя [npc2.cockHead] [npc2.namePos] [npc2.cock+] вверх и вниз в образовавшейся межъягодичной щели.",

							"Крепко ухватив [npc.her] [npc.ass], [npc.name] медленно [npc.verb(push)] [npc.her] ягодицы вместе, после чего начиная с нетерпением ласкать [npc2.namePos] [npc2.cock+] в щели.",

							"Крепко сжав каждую из [npc.her] ягодиц, [npc.name] с нетерпением [npc.verb(push)] их вместе, а затем лаская [npc2.cockHead] [npc2.namePos] [npc2.cock+] в образовавшейся щели."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Слегка сдвинув [npc.her] [npc.hips], [npc.name] [npc.verb(reach)] назад и с нетерпением [npc.verb(press)] [npc.her] ягодицы вместе, а затем с удовольствием скользя [npc2.cockHead] [npc2.namePos] [npc2.cock+] вверх и вниз в образовавшейся межъягодичной щели.",

							"Крепко ухватив [npc.her] [npc.ass], [npc.name] медленно [npc.verb(push)] [npc.her] ягодицы вместе, после чего начиная с нетерпением ласкать [npc2.namePos] [npc2.cock+] в щели.",

							"Крепко сжав каждую из [npc.her] ягодиц, [npc.name] с нетерпением [npc.verb(push)] их вместе, а затем лаская [npc2.cockHead] [npc2.namePos] [npc2.cock+] в образовавшейся щели."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Мягкий [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(anticipate)] ощущение погружения [npc2.her] [npc2.cock+] в [npc.namePos] [npc.ass+].",
									" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], пока [npc2.she] [npc2.verb(prepare)] с силой вбивать [npc2.her] [npc2.cock+] в [npc.namePos] [npc.ass+].",
									" [npc2.Name] нежно [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(anticipate)] ощущение трахания [npc.namePos] [npc.ass+]."));
							break;
						case DOM_ROUGH:
						case SUB_EAGER:
						case DOM_NORMAL:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] с предвкушением [npc2.verb(anticipate)] ощущение погружения [npc2.her] [npc2.cock+] в [npc.namePos] [npc.ass+].",
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] с предвкушением [npc2.verb(prepare)] с силой вбивать [npc2.her] [npc2.cock+] в [npc.namePos] [npc.ass+].",
									" [npc2.Name] возбужденно [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(anticipate)] ощущение трахания [npc.namePos] [npc.ass+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] вырывается из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc.verb(dread)] возможность того, что придется трахать [npc.namePos] [npc.ass+].",
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc.verb(anticipate)] [npc2.her] [npc2.cock+], втискиваемого в [npc.namePos] [npc.ass+].",
									" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(anticipate)] [npc2.her] [npc2.cock+], вынужденную к [npc.namePos] [npc.ass+]."));
							break;
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Мягкий [npc2.moan] доносится из глубин [npc2.her] [npc2.lips+], [npc2.speech(Пожалуйста! Дай мне оттрахать твою задницу!)]",
									" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], прежде чем взмолиться к [npc.name], [npc2.speech(Да, я хочу оттрахать твою задницу!)]",
									" [npc2.Name] тихо [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Да! Дай мне трахнуть тебя! Пожалуйста!)]"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.her] [npc2.lips+], [npc2.speech(О, грязная шлюха! Ты хочешь, чтобы я оттрахал твою задницу?!)]",
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем прорычать на [npc.name], [npc2.speech(Ты хочешь, чтобы я отъебал твою задницу, грязная шлюха?!)]",
									" [npc2.Name] [npc2.moansVerb] в восторге, а затем, оскалившись, [npc2.speech(Хочешь, чтобы тебе отъебали задницу, сучка?!)]"));
							break;
						case SUB_EAGER:
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.her] [npc2.lips+], [npc2.speech(Да! Я хочу оттрахать твою задницу!)]",
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем обратиться к [npc.name], [npc2.speech(Да! Твоей заднице не помешало бы хорошенько протрахаться!)]",
									" [npc2.Name] [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(call)] к [npc.name], [npc2.speech(Мне нужно оттрахать твою задницу!)]"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] плавно выскальзывает из между [npc2.her] [npc2.lips+], [npc2.speech(Да, трахни мою задницу!)]",
									" [npc2.Name] [npc2.verb(let)] из между [npc2.moan], прежде чем выкрикнуть: [npc2.speech(Пожалуйста! Я хочу, чтобы ты трахнул мою задницу!)]",
									" [npc2.Name] [npc2.moansVerb] вслух, пока [npc2.she] говорит с [npc.herHim], [npc2.speech(Давай же, [npc.verb(let)] трахни меня уже!)]"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] резко выскакивает из между [npc2.her] [npc2.lips+], [npc2.speech(Нет! Не надо! Пожалуйста! Уйди от меня!)]",
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
									" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	
	public static final SexAction ASS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Начать «горячий хот-дог»";
		}

		@Override
		public String getActionDescription() {
			return "Проскользнуть своим [npc.cock+] между ягодицами [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно сжимая [npc2.namePos] [npc2.assSize], [npc.name] [npc.verb(press)] [npc.cockHead+] из [npc.her] [npc.cock+] против [npc2.her] [npc2.ass], прежде чем мягко толкнуть [npc.her] [npc.hips] вперед и начать трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] из [npc.her] [npc.cock] против [npc2.namePos] [npc2.ass+], и с медленным, steady давлением, [npc.she] мягко [npc.verb(press)] [npc2.her] ягодицы вместе, прежде чем начать трахать образовавшуюся щель."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Жадно сжимая [npc2.namePos] [npc2.assSize], [npc.name] [npc.verb(press)] [npc.cockHead+] из [npc.her] [npc.cock+] против [npc2.her] [npc2.ass], прежде чем алчно толкнуть [npc.her] [npc.hips] вперед и начать трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her], [npc.cock] прижатые к [npc2.namePos] [npc2.ass+], после чего он жадно сжимает [npc2.her] ягодицы и начинает трахать образовавшуюся щель."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо сжимая [npc2.namePos] [npc2.assSize] ягодицы, [npc.name] с силой [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] к [npc2.her] [npc2.ass], а затем яростно толкая [npc.her] [npc.hips] вперед и начиная трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her], [npc.cock] прижатые к [npc2.namePos] [npc2.ass+], после чего он грубо сдавливает [npc2.her] ягодицы и начинает агрессивно трахать образовавшуюся щель."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сжимая [npc2.namePos] [npc2.assSize] ягодицы, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] к [npc2.her] [npc2.ass], а затем толкая [npc.her] [npc.hips] вперед и начиная трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her], [npc.cock] прижатые к [npc2.namePos] [npc2.ass+], после чего он прижимает [npc2.her] ягодицы друг к другу и начинает трахать образовавшуюся щель."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] издает мягкий [npc2.moan], пока [npc.name] [npc.verb(start)], используя [npc2.her] задницу, осторожно толкая [npc2.her] [npc2.hips+] назад к [npc.herHim], пока [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.cock+] еще глубже между [npc2.her] ягодицами.",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно толкая [npc2.her] [npc2.ass] в пах [npc.namePos], вонзая [npc.her] [npc.cock+] еще глубже между [npc2.her] ягодицами."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] издает [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] задницу, жадно толкая [npc2.her] [npc2.hips+] назад к [npc.herHim], пока [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.cock+] еще глубже между [npc2.her] ягодицами.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.ass] в пах [npc.namePos], отчаянно помогая вогнать [npc.her] [npc.cock+] еще глубже между [npc2.her] ягодицами."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] издает [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] задницу, яростно толкая [npc2.her] [npc2.hips+] назад к [npc.herHim], пока [npc2.she] грубо [npc2.verb(force)] [npc.cock+] еще глубже между [npc2.her] ягодицами.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно вбивает [npc2.her] [npc2.ass] в пах [npc.namePos], грубо заставляя [npc.herHim] погружаться [npc.her] [npc.cock+] еще глубже между ягодицами [npc2.her]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] издает [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] задницу, жадно толкая [npc2.her] [npc2.hips+] назад к [npc.herHim], пока [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.cock+] еще глубже между [npc2.her] ягодицами.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.ass] в пах [npc.namePos], отчаянно помогая вогнать [npc.her] [npc.cock+] еще глубже между [npc2.her] ягодицами."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя задницу [npc2.her], при этом [npc2.her] [npc2.hips+] бьется о [npc.herHim], пока [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.cock+] еще глубже между ягодицами [npc2.her].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] вбивает [npc2.her] [npc2.ass] в [npc.namePos] пах, помогая [npc.her] [npc.cock+] погрузиться еще глубже между [npc2.her] ягодиц."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя задницу [npc2.her], и, пока по [npc2.her] [npc2.face] текут слезы, [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил это.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] втискивается глубоко между ягодицами [npc2.her]."));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)], жадно подбрасывая [npc2.her] [npc2.hips] вверх-вниз в ответ, издает [npc2.a_moan+], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+], скользя туда-сюда между ягодицами [npc2.her].",
	
						" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+], и, жадно вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] вверх и вниз между ягодицами [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips+] взад-вперед, умоляя [npc.name] продолжать горячее сосание [npc2.herHim], пока движения [npc2.her] помогают [npc.cock+] скользить вверх и вниз между [npc2.her] ягодицами."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил(а) из [npc2.her] [npc2.ass+].",
	
						" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил(а) из [npc2.her] [npc2.ass+].",
	
						" [npc2.Sobbing] в смятении, слезы текут по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил(а) из [npc2.her] [npc2.ass+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)] подпрыгивает, [npc2.her] [npc2.hips] вверх и вниз в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] скользит туда-сюда между [npc2.her] ягодицами.",
	
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] вверх и вниз между ягодицами [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips+] вперед-назад, умоляя о [npc.name], чтобы [npc2.herHim] продолжал развлекаться, пока [npc2.her] движения помогают [npc.cock+] скользить вверх и вниз между [npc2.her] ягодицами."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)] мягко подпрыгивает, [npc2.her] [npc2.hips] вверх и вниз в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] скользит туда-сюда между [npc2.her] ягодицами.",
	
						" Легкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, медленно вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] вверх и вниз между ягодицами [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips+] взад-вперед, умоляя [npc.name] продолжать этот «хотдог» с [npc2.herHim], пока [npc2.her] движения помогают [npc.cock+] скользить вверх и вниз между [npc2.her] ягодицами."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] в ответ яростно [npc2.verb(start)] [npc2.her] [npc2.hips] вверх и вниз, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+], вбиваясь туда-сюда между ягодицами [npc2.her].",
	
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] вверх и вниз между ягодицами [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] в восторге, [npc2.name] властно [npc2.verb(buck)] [npc2.her] [npc2.hips+] взад-вперед, приказывая [npc.name] продолжать этот «хотдог» с [npc2.herHim], пока [npc2.her] движения заставляют [npc.her] [npc.cock+] двигаться вверх и вниз между [npc2.her] ягодицами."));
				break;
		}
		return "";
	}
	
	public static final SexAction ASS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный «хотдог»";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахать ягодицы [npc2.namePos].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно скользя [npc.her] [npc.cock+] между ягодицами [npc2.namePos], [npc.name] steadily [npc.verb(start)] [npc.her] [npc.hips] взад-вперед, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(hotdog)] [npc2.name].",

					"Мягко проскальзывая [npc.her] [npc.cock+] между ягодицами [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(start)] плавно толкая [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(hotdog)] [npc2.herHim].",

					"Мягко сводя ягодицы [npc2.namePos] [npc2.ass+] вместе, [npc.name] [npc.verb(let)] слегка [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Обычный горячий хот-дог";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать трахать ягодицы [npc2.namePos].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью проталкивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos], [npc.name] [npc.verb(start)] энергично дергая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(hotdog)] [npc2.name].",

					"Отчаянно проталкивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(hotdog)] [npc2.herHim].",

					"Алчно сжимая ягодицы [npc2.ass+] [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] отчаянно вгоняет [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый «хотдог»";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать анус [npc2.namePos].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо втискивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos], [npc.name] властно [npc.verb(start)], толкая [npc.her] [npc.hips] туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(hotdog)] [npc2.name].",

					"С силой втискивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos] [npc2.ass+], [npc.name] яростно [npc.verb(start)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(hotdog)] [npc2.herHim].",

					"Властно сжимая ягодицы [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с силой вбивает [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Обычный горячий хот-дог";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать трахать ягодицы [npc2.namePos].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Втискивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos], [npc.name] [npc.verb(start)], толкая [npc.her] [npc.hips] туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] алчно [npc.verb(hotdog)] [npc2.name].",

					"Втискивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(start)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] счастливо [npc.verb(hotdog)] [npc2.herHim].",

					"Сжимая ягодицы [npc2.namePos] [npc2.ass+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы ритмично двигать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадное «хот-доггирование»";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать ягодицы [npc2.namePos].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью проталкивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos], [npc.name] [npc.verb(start)] энергично дергая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(hotdog)] [npc2.name].",

					"Отчаянно проталкивая [npc.her] [npc.cock+] между ягодицами [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(hotdog)] [npc2.herHim].",

					"Алчно сжимая ягодицы [npc2.namePos] [npc2.ass+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы отчаянно двигать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться «хот-доггированию»";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.cock] от [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь (и не сумев) оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя протесты [npc.her], [npc2.she] медленно [npc2.verb(thrust)] [npc2.her] [npc2.ass] назад, при этом [npc.herHim] и [npc2.verb(continue)] мягко втискивая [npc.her] [npc.cock+] между ягодицами [npc2.her].",

							"[npc.A_sob+] с силой вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.name] решительно [npc2.verb(hold)] [npc.herHim] на месте, мягко выталкивая [npc2.her] [npc2.ass] против [npc.her] паха, пока [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] между ягодицами [npc2.her].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.herHim] отпустил [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] протесты [npc.her], медленно растирая [npc2.herself] о [npc.herHim], пока [npc2.she] [npc2.verb(sink)] [npc.her] [npc.cock+] глубоко между ягодицами [npc2.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], как, игнорируя протесты [npc.her], [npc2.she] яростно [npc2.verb(thrust)] [npc2.her] [npc2.ass] назад против [npc.herHim] и [npc2.verb(continue)] грубо втискивая [npc.her] [npc.cock+] между ягодицами [npc2.her].",

							"[npc.A_sob+] с силой вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] властно удерживает [npc.herHim] на месте, яростно вжимая [npc2.her] [npc2.ass] в [npc.her] пах, пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.cock+] между ягодицами [npc2.her].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]. [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] ass cheeks."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_sob+], так как, игнорируя протесты [npc.her], [npc2.she] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.ass] обратно к [npc.herHim] и стремительно [npc2.verb(continue)] вдавливая [npc.her] [npc.cock+] между ягодицами [npc2.her].",

							"[npc.A_sob+] с силой вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] крепко удерживает [npc.herHim] на месте, жадно вжимая [npc2.her] [npc2.ass] в пах [npc.her], пока [npc2.she] отчаянно [npc2.verb(force)] [npc.her] [npc.cock+] между ягодицами [npc2.her].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]. [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] happily [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] ass cheeks."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Хватит играть в хот-дог";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из между ягодиц [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.cock+] из между ягодиц [npc2.namePos], [npc2.ass+], [npc.name] властно [npc.verb(slide)] [npc.cockHead] [npc.her] [npc.cock] вверх и вниз по [npc2.her] [npc2.ass+] в последний раз, прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз глубоко вонзившись между ягодицами [npc2.namePos] [npc2.ass+], [npc.name] затем [npc2.verb(pull)] назад, прекращая эту игру в хот-дог."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc.her] [npc.cock+] из между ягодиц [npc2.namePos] [npc2.ass+], [npc.name] в последний раз шлепает [npc.cockHead] [npc.her] [npc.cock] о [npc2.her] [npc2.ass], прежде чем вытянуть [npc.her] [npc.hips] назад.",

							"В последний раз глубоко протискиваясь между ягодиц [npc2.namePos] [npc2.ass+], [npc.name] затем [npc2.verb(pull)] назад, завершая это соревнование на выносливость."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может не издать [npc2.sob+], когда [npc.name] отстраняется, а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставила [npc.herHim] в покое.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться против [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(beg)] остаться в одиночестве."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] назад, жаждая еще большего [npc.her] внимания.",
	
								" [npc2.A_moan+] выскальзывает из-под [npc2.namePos] [npc2.lips+], выдавая [npc2.her] похоть к [npc.namePos] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction USING_COCK_AGAINST_ASS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Устроить «хот-дог»";
		}

		@Override
		public String getActionDescription() {
			return "Засунуть [npc2.namePos] [npc2.cock+] между ягодиц твоей [npc.ass+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С нежным [npc.moan], [npc.name] медленно [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по [npc.her] [npc.ass+], прежде чем мягко сжать [npc.her] ягодицы и втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Прицеливая [npc.her] [npc.ass+] к [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(push)] сжимая [npc.her] ягодицы, мягко [npc.moaning], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по [npc.her] [npc.ass+], прежде чем отчаянно сжать [npc.her] ягодицы и втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Прицеливая [npc.her] [npc.ass+] к [npc2.namePos] [npc2.cock+], [npc.name] жадно [npc.verb(push)] сжимая [npc.her] ягодицы, [npc.moaning+], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] грубо [npc.verb(force)] [npc2.namePos] [npc2.cock+] поверх [npc.her] [npc.ass+], прежде чем жадно сжать [npc.her] ягодицы и с силой втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Выравнивая [npc.her] [npc.ass+], чтобы [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(push)] [npc.her] ягодицы, пока [npc.moaning+] [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] поверх [npc.her] [npc.ass+], прежде чем отчаянно сжать [npc.her] ягодицы и втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Прицеливая [npc.her] [npc.ass+] к [npc2.namePos] [npc2.cock+], [npc.name] жадно [npc.verb(push)] сжимая [npc.her] ягодицы, [npc.moaning+], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], плавно подбрасывая [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] трахать [npc.her] ягодицы.",
	
								" С мягким [npc2.moan], [npc2.name] плавно [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(sink)] [npc2.her] [npc2.cock+] между ягодицами [npc.namePos] [npc.ass+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], с нетерпением подбрасывая [npc2.her] [npc2.hips] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.her] ягодицы.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(sink)] [npc2.her] [npc2.cock+] между ягодицами [npc.namePos] [npc.ass+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], и, желая напомнить [npc.name], кто здесь главный, грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] вперед, после чего начинает беспощадно трахать [npc.her] ягодицы.",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] безжалостно [npc2.verb(start)] [npc.her] ягодицы."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], толкая [npc2.her] [npc2.hips] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] [npc.her] ягодицы.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(sink)] [npc2.her] [npc2.cock+] между ягодицами [npc.namePos] [npc.ass+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] между ягодицами [npc.her] [npc.ass+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы отстраниться.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко между [npc.her] ягодицами."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между ягодицами [npc.namePos] [npc.ass+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(hotdog)] [npc.herHim].",
	
						" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] глубоко вгоняет [npc2.her] [npc2.cock+] между [npc.assSize] ягодицами [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда между ягодицами [npc.namePos] [npc.ass+]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.ass], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
	
						" [npc2.A_sob+] вырывается из-за [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.cock+] двигаться туда-сюда между ягодицами [npc.her] [npc.ass+].",
	
						" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.ass+]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между ягодицами [npc.namePos] [npc.ass+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(hotdog)] [npc.herHim].",
	
						" Тихий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] нежно вгоняет [npc2.her] [npc2.cock+] между [npc.assSize] ягодицами [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] туда-сюда между ягодицами [npc.namePos] [npc.ass+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между ягодицами [npc.namePos] [npc.ass+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(hotdog)] [npc.herHim].",
	
						" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо вбивает [npc2.her] [npc2.cock+] глубоко между [npc.assSize] ягодицами [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] туда-сюда между ягодицами [npc.namePos] [npc.ass+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между ягодицами [npc.namePos] [npc.ass+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(hotdog)] [npc.herHim].",
	
						" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] глубоко вгоняет [npc2.her] [npc2.cock+] между [npc.assSize] ягодицами [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда между ягодицами [npc.namePos] [npc.ass+]."));
				break;
		}
		return "";
	}
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно зажав между ягодиц";
		}

		@Override
		public String getActionDescription() {
			return "Нежно использовать [npc2.namePos] [npc2.cock+], чтобы трахать твои ягодицы.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно подталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] мягкий [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко между ягодицами [npc.her] [npc.ass+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] заталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] двигаться вперед-назад между [npc.her] ягодицами.",

					"Медленно вгоняя [npc.her] [npc.hips] назад, мягкий [npc.moan] вытекает из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.cock+] двигаться вверх-вниз между ягодицами [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Хотдог";
		}

		@Override
		public String getActionDescription() {
			return "Использовать [npc2.namePos] [npc2.cock+], чтобы трахать свои ягодицы.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью заталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между ягодицами [npc.her] [npc.ass+].",

					"С [npc.a_moan+], [npc.name] жадно [npc.verb(start)] заталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] двигаться вперед-назад между [npc.her] ягодицами.",

					"Отчаянно вгоняя [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(drive)] [npc2.namePos] [npc2.cock+] вверх-вниз между ягодицами [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый хотдог";
		}

		@Override
		public String getActionDescription() {
			return "Грубо заставлять [npc2.namePos] [npc2.cock+] двигаться вверх-вниз между твоими ягодицами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между ягодицами [npc.her] [npc.ass+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] двигаться взад-вперед между ягодицами [npc.her].",

					"Грубо толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.she] властно [npc.verb(drive)] [npc2.namePos] [npc2.cock+] вверх и вниз между ягодицами [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Хотдог";
		}

		@Override
		public String getActionDescription() {
			return "Выпячивай свой [npc.ass+] навстречу [npc2.name], чтобы заставить [npc2.her] [npc2.cock] проскользнуть между твоими ягодицами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между ягодицами [npc.her] [npc.ass+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] вталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] двигаться взад-вперед между ягодицами [npc.her].",

					"Толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.she] [npc.verb(drive)] [npc2.namePos] [npc2.cock+] вверх и вниз между ягодицами [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С энтузиазмом подставить (хотдог)";
		}

		@Override
		public String getActionDescription() {
			return "С энтузиазмом выпячивай свой [npc.ass+] навстречу [npc2.name], пока [npc2.her] [npc2.cock] вклинивается между твоими ягодицами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью заталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между ягодицами [npc.her] [npc.ass+].",

					"С [npc.a_moan+], [npc.name] жадно [npc.verb(start)] заталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] двигаться вперед-назад между [npc.her] ягодицами.",

					"Отчаянно вгоняя [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(drive)] [npc2.namePos] [npc2.cock+] вверх-вниз между ягодицами [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_AGAINST_ASS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться (хотдог)";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.ass+] от [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] нежно [npc2.verb(continue)] трахает [npc.her] ягодицы.",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] медленно [npc2.verb(continue)] входит и выходит между [npc.her] ягодиц.",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.ass] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] нежно [npc2.verb(continue)] глубоко скользит между [npc.her] ягодиц."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"У[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] жадно [npc2.verb(continue)] трахает [npc.her] ягодицы.",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] жадно [npc2.verb(continue)] входит и выходит между [npc.her] ягодиц.",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.ass] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] жадно скользит глубоко между [npc.her] ягодицами."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают наполнять [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] грубо [npc2.verb(continue)] в [npc.her] ягодицы.",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass] от [npc2.namePos] [npc2.cock], отчаянно борясь, пока [npc2.she] грубо [npc2.verb(continue)] входит и выходит между [npc.her] ягодицами.",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.ass] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] грубо [npc2.verb(continue)] глубоко между [npc.her] ягодицами."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить «горячую сосиску»";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытянуть [npc2.her] [npc2.cock] из твоих ягодиц.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.cock] из между [npc.her] ягодиц, [npc.name] [npc.verb(let)] угрожающе рычит, заставляя [npc.she] [npc.verb(command)] [npc2.herHim] остановиться.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем резко вырвать [npc2.her] [npc2.cock] из между [npc.her] ягодиц."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.cock] из между [npc.her] ягодиц, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.herHim], чтобы остановиться.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно выскользнуть [npc2.her] [npc2.cock] из между [npc.her] ягодиц."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] облегченно вздыхает, но вскоре этот вздох переходит в [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)] понимает, что [npc.name] [npc.has] еще не закончил с [npc2.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.her] межъягодичную щель."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
