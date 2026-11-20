package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
 * @since 0.1.82
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PenisAnus {

	// -- Methods for multiple ongoing characters:
	
	private static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingDP) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingDP, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
	}

	private static List<GameCharacter> getMultipleOngoingCharactersForParsing(GameCharacter characterReceivingDP) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingDP)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	public static GameCharacter getPrimaryDPPerformer(GameCharacter characterReceivingDP) {
		if(Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.ANUS).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.ANUS).keySet().iterator().next();
	}
	
	// ---
	
	public static final SexAction TEASE_PENIS_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(!getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
				return Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this)));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Ласкать анус";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.cockHead] своего [npc.cock] по [npc2.her] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Не смущаясь того, что [npc3.namePos] [npc3.cock] уже глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] медленно дразнит [npc.cockTip+] собственного [npc.cock+] [npc.her], прижимая их к [npc2.her] [npc2.assCloaca+] и угрожая в любой момент начать двойное проникновение в [npc.herHim].",
								"С мягким [npc.moan], [npc.name] нежно [npc.verb(guide)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.assCloaca+], намекая, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в [npc2.her] [npc2.asshole+].",
								"Мягко скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] в каком-нибудь двойном проникновении."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] roughly grinding the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"С [npc.a_moan+], [npc.name] с силой [npc.verb(rub)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.assCloaca+], намекая на то, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в [npc2.her] [npc2.asshole+].",
								"Грубо водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] для какого-нибудь двойного проникновения."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] grinding the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"С [npc.a_moan+], [npc.name] [npc.verb(rub)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.assCloaca+], намекая на то, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в [npc2.her] [npc2.asshole+].",
								"Водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] для какого-нибудь двойного проникновения."));
						break;
					default:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] eagerly grinding the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"Пока [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(rub)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.assCloaca+], намекая, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в [npc2.her] [npc2.asshole+].",
								"С жадностью потирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] в каком-нибудь двойном проникновении."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] медленно дразня [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.assCloaca+], готовая в любой момент проникнуть в [npc2.herHim].",
								"С мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.asshole+], прежде чем начать нежно водить [npc.cockTip] вверх и вниз по [npc2.her] [npc2.assCloaca+].",
								"Мягко скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Втирая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(pull)] немного назад, прежде чем начать скользить [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.assCloaca+], готовый начать трахать [npc2.herHim] в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] вверх к [npc2.namePos] [npc2.asshole+], прежде чем начать грубо [npc2.verb(grind)] [npc.cockTip] вверх и вниз по [npc2.her] [npc2.assCloaca+].",
								"Грубо втирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] скользя [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.assCloaca+], готовый проникнуть в [npc2.herHim] в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] до [npc2.namePos] [npc2.asshole+], прежде чем начать скользить [npc.cockTip] вверх и вниз по [npc2.her] [npc2.assCloaca+].",
								"Скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Направляя [npc.her] [npc.cock+] до [npc2.namePos] [npc2.asshole+], [npc.name] с нетерпением [npc.verb(start)] скользит [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.assCloaca+], готовая проникнуть в [npc2.herHim] в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] до [npc2.namePos] [npc2.asshole+], прежде чем начать с нетерпением скользить [npc.cockTip] вверх и вниз по [npc2.her] [npc2.assCloaca+].",
								"С нетерпением скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
				}
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируются, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.asshole+] на [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.asshole+] наружу, прижимая к [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.asshole+], и нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.asshole+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] наружу, прижимая к [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно вытолкнуть [npc2.her] [npc2.asshole+] наружу, прижимая к [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.asshole+], и отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips+] наружу в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.asshole+], и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca+] наружу, прижимая к [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо вытолкнуть [npc2.her] [npc2.asshole+] наружу, прижимая к [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.asshole+], и яростно [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируются, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] на [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.moan], прежде чем резко вытолкнуть [npc2.her] [npc2.asshole+] на [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.asshole+], и [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], когда [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот может потерять свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере своей анальной девственности [npc2.her] перед [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Я еще никогда не занималась анальным!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! У меня никогда не было анального секса!)]",
										" [npc2.Name] [npc2.sobVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою анальную девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.cock], [npc2.speech(Нет! Не надо! Пожалуйста, прочь от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.name] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
							}
						}
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_PENIS_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(!getOngoingCharacters(Main.sex.getCharacterPerformingAction()).isEmpty()) {
				return Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this)));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Дразнить членом (анально)";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.cockTip] [npc2.her] [npc2.cock] по твоей [npc.asshole+].";
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
			if(!getOngoingCharacters(Main.sex.getCharacterPerformingAction()).isEmpty()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что лишь [npc3.namePos] [npc3.cock] проникает в [npc.herHim], [npc.name] [npc.verb(take)] хватает [npc2.namePos] [npc2.cock+], прежде чем направить его к обнаженной части [npc.her] [npc.asshole] и нежно поводить [npc.cockHead] по [npc.her] [npc.assCloaca+].",
								"Желая большего, чем просто [npc3.namePos] [npc3.cock+], засунутый в [npc.her] [npc.asshole+], [npc.name] нежно [npc.verb(take)] берет за [npc2.namePos] [npc2.cock+], прежде чем начать водить [npc2.cockTip] вверх и вниз по открытой части [npc.her] [npc.assCloaca+].",
								"Пока [npc3.namePos] [npc3.cock+] все еще входит и выходит из [npc.her] [npc.asshole+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем мягко провести [npc2.cockTip+] по открытой части [npc.her] [npc.assCloaca+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Не довольствуясь лишь тем, что [npc3.namePos] [npc3.cock] проникает в [npc.herHim], [npc.name] грубо [npc.verb(grab)] хватает за [npc2.namePos] [npc2.cock+], прежде чем направить его к открытой части [npc.her] [npc.asshole] и с силой тереть [npc.cockHead] о [npc.her] [npc.assCloaca+].",
								"Желая большего, чем просто [npc3.namePos] [npc3.cock+], засунутый в [npc.her] [npc.asshole+], [npc.name] грубо [npc.verb(seize)] хватает за [npc2.namePos] [npc2.cock+], прежде чем начать с силой водить [npc2.cockTip] вверх и вниз по открытой части [npc.her] [npc.assCloaca+].",
								"Пока [npc3.namePos] [npc3.cock+] все еще входит и выходит из [npc.her] [npc.asshole+], [npc.name] с силой [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем грубо тереть [npc2.cockTip+] по открытой части [npc.her] [npc.assCloaca+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Ей мало того, что [npc3.namePos] [npc3.cock] проникает в [npc.herHim]; [npc.name] [npc.verb(take)] [npc2.namePos] [npc2.cock+], прежде чем направить его к обнаженной части [npc.her] [npc.asshole] и тереть [npc.cockHead] о [npc.her] [npc.assCloaca+].",
								"Ей хочется большего, чем просто [npc3.namePos] [npc3.cock+], засунутый в [npc.her] [npc.asshole+]; [npc.name] [npc.verb(take)] [npc2.namePos] [npc2.cock+], прежде чем начать энергично водить [npc2.cockTip] вверх и вниз по обнаженной части [npc.her] [npc.assCloaca+].",
								"Пока [npc3.namePos] [npc3.cock+] все еще входил и выходил из [npc.her] [npc.asshole+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем тереть [npc2.cockTip+] по обнаженной части [npc.her] [npc.assCloaca+]."));
						break;
					default:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
								"Ей мало того, что [npc3.namePos] [npc3.cock] проникает в [npc.herHim]; [npc.name] жадно [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к обнаженной части [npc.her] [npc.asshole] и отчаянно тереть [npc.cockHead] о [npc.her] [npc.assCloaca+].",
								"Ей хочется большего, чем просто [npc3.namePos] [npc3.cock+], засунутый в [npc.her] [npc.asshole+]; [npc.name] отчаянно [npc.verb(seize)] [npc2.namePos] [npc2.cock+], прежде чем начать энергично водить [npc2.cockTip] вверх и вниз по обнаженной части [npc.her] [npc.assCloaca+].",
								"Пока [npc3.namePos] [npc3.cock+] всё ещё входил и выходил из [npc.her] [npc.asshole+], [npc.name] с нетерпением [npc.verb(grab)] [npc2.namePos] [npc2.cock+], а затем счастливо терла [npc2.cockTip+] об открытую часть [npc.her] [npc.assCloaca+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.asshole+]. Медленно двигая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",
								"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.asshole+], после чего начинает нежно водить [npc2.cockTip] вверх и вниз по [npc.her] [npc.assCloaca+].",
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] осторожно [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.asshole+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении скорого проникновения."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем резко дернуть его к [npc.her] [npc.asshole+]. Грубо толкая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",
								"Схватив [npc.a_moan+] [npc.name], [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и резко дернув его вверх до [npc.her] [npc.asshole+], он начинает грубо [npc.verb(force)] [npc2.cockTip] вверх и вниз по [npc.her] [npc.assCloaca+].",
								"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(grind)] [npc2.cockTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.asshole+]. Проталкивая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name], предвещая проникновение в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] наступает очередь [npc.her] [npc.asshole+], прежде чем начать водить [npc2.cockTip] вверх и вниз по [npc.her] [npc.assCloaca+].",
								"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.asshole+]. С жадностью двигая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.asshole+], прежде чем начать с жадностью скользить [npc2.cockTip] вверх и вниз по [npc.her] [npc.assCloaca+].",
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] с нетерпением [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении скорого проникновения."));
						break;
				}
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] срывается с [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко скользить [npc2.her] [npc2.cock] вперед и назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] от стимуляции, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко скользит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], грубо втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой начать водить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, что [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируется, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)], с энтузиазмом втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом потирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно начать скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, что [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)], жадно скользя [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], потирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем начать скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)], чувствуя, как стимулируются [npc2.her] [npc2.cock], и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)], скользя [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.assCloaca+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)], заставляя [npc.name] отпустить [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction ASSHOLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Контроль ануса";
		}

		@Override
		public String getActionDescription() {
			return "Стисните мышцами ануса [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return UtilText.returnStringAtRandom(
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)] на сжимании дополнительных внутренних мышц внутри [npc.her] [npc.asshole] вокруг #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(focus)] на контроле дополнительных мышц, выстилающих внутреннюю часть [npc.her] [npc.asshole]. Сжимая и сдавливая их вокруг #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)], [npc.name] [npc.verb(cause)] обоих, "
								+ (getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.game.getPlayer())?"вы":"их")
								+ " чтобы вырвались непроизвольные крики удовольствия."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжимании дополнительных мышц внутри [npc.her] [npc.asshole+] вокруг двух [npc2.cocks(true)], которые в данный момент совершают двойное проникновение в [npc.herHim].",
						"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.asshole], сжимая их и массируя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)] на сжимании дополнительных внутренних мышц внутри [npc.her] [npc.asshole] вокруг [npc2.namePos] [npc2.cock+].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] извлекает из [npc.a_moan+] [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы внутри [npc.her] [npc.asshole]. Сжимая и обхватывая ими [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], входя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.asshole+] вокруг [npc2.namePos] [npc2.cock+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.asshole], сжимая их и массируя [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
			}
		}
	};
	
	public static final SexAction PENIS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.cock+] в [npc2.namePos] [npc2.asshole+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			String assTargeting = "между ягодицами [npc2.namePos]";
			if(Main.sex.getCharacterTargetedForSexAction(this).getGenitalArrangement()!=GenitalArrangement.NORMAL) {
				assTargeting = "поверх [npc2.namePos] [npc2.assCloaca+]";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+", и медленным, уверенным давлением [npc.she] осторожно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С азартом дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.cock+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+", и решительным толчком [npc.she] с жадностью [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.cockTip+] [npc.her] [npc.cock] в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно врезаться вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+", и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С азартом дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.cock+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+", и решительным толчком [npc.she] с жадностью [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Лаская [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем мощно толкнуться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+", и коротким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], а затем плавно вытаскивает [npc2.her] [npc2.hips], чтобы погрузить его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] плавно выгибая [npc2.her] [npc2.hips], погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], а затем резко выдергивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно выгибая [npc2.her] [npc2.hips], грубо заставляя [npc.name] погружать [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], а затем жадно выгибая [npc2.her] [npc2.hips], чтобы погрузить его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.hips], отчаянно помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, когда [npc2.a_moan+] входит в [npc.cock+], после чего [npc2.herHim] делает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] толкая [npc2.her] [npc2.hips], помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, пока слезы текут по [npc2.her] [npc2.face], а незваный [npc.namePos] [npc.cock] глубоко вжимается в [npc2.her] [npc2.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	

	public static final SexAction PENIS_FUCKING_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size();
			
			map.put("one other character fucking [npc2.namePos] asshole", size==1);
			map.put("[npc.namePos] penis to be exposed", Main.sex.getCharacterPerformingAction().isPenetrationTypeExposed(SexAreaPenetration.PENIS));
			map.put("[npc.namePos] penis to be free", SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()==1
					&& !getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Двойное проникновение (анальное)";
		}
		
		@Override
		public String getActionDescription() {
			return "Присоединиться к [npc3.name], чтобы трахать [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her], [npc.cock] подбираясь к [npc2.namePos] [npc2.asshole+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] мягко [npc.verb(push)] вперед, втискивая [npc.her] [npc.cock+] рядом с [npc3.hers] и присоединяясь к [npc3.herHim], чтобы устроить [npc2.name] двойное проникновение.");
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her], [npc.cock] подбираясь к [npc2.namePos] [npc2.asshole+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] грубо [npc.verb(thrust)] вперед, вбивая [npc.her] [npc.cock+] рядом с [npc3.hers] и присоединяясь к [npc3.herHim], чтобы устроить [npc2.name] двойное проникновение.");
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her], [npc.cock] подбираясь к [npc2.namePos] [npc2.asshole+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] [npc.verb(thrust)] вперед, втискивая [npc.her] [npc.cock+] рядом с [npc3.hers] и присоединяясь к [npc3.herHim], чтобы устроить [npc2.name] двойное проникновение.");
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her] [npc.cock] до [npc2.namePos] [npc2.asshole+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] отчаянно [npc.verb(thrust)] вперед, вбивая [npc.her] [npc.cock+] рядом с [npc3.hers] и присоединяясь к [npc3.herHim] для двойного проникновения в [npc2.name].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда второй [npc.cock] входит в [npc2.her] [npc2.asshole+], после чего плавно выталкивая [npc2.her] [npc2.hips], чтобы глубоко вогнать его рядом с [npc3.namePos] [npc3.cock+].");
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда второй [npc.cock] входит в [npc2.her] [npc2.asshole+], после чего грубо вбивая [npc2.her] [npc2.hips], чтобы глубоко вогнать его рядом с [npc3.namePos] [npc3.cock+].");
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда второй [npc.cock] входит в [npc2.her] [npc2.asshole+], после чего резко выталкивая [npc2.her] [npc2.hips], чтобы глубоко вогнать его рядом с [npc3.namePos] [npc3.cock+].");
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда второй [npc.cock] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] ради [npc.name] и того, чтобы [npc3.name] вытащил его.",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь отстраниться от второго нежелательного проникновения, и слезы снова начинают свободно катиться по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(feel)] [npc.namePos] незваный [npc.cock] глубоко в [npc2.her] [npc2.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда второй [npc.cock] входит в [npc2.her] [npc2.asshole+], после чего жадно выталкивает [npc2.her] [npc2.hips], чтобы глубоко погрузить его рядом с [npc3.namePos] [npc3.cock+].");
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								" Отчаянно пытаясь, но не в силах отстраниться от двух [npc.cocks(true)], находящихся внутри [npc2.herHim], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] и [npc3.name] вытащили их из [npc2.her] [npc2.asshole+].",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name] и [npc3.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы оба "
										+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())?"вы":"их")+" вытащили их из [npc2.her] [npc2.asshole+].",
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] в тщетной попытке вытолкнуть [npc.cocks(true)], вонзающиеся в [npc2.her] [npc2.asshole+], умоляя и плача, чтобы [npc.name] и [npc3.name] оставили [npc2.herHim] в покое.");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)] заставляет два [npc.cocks(true)] погружаться еще глубже в [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] и [npc3.name] могли продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя вас обоих "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить ваши [npc.cocks(true)] глубоко в [npc2.her] [npc2.asshole+]."
												:"продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить их [npc.cocks(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(start)] мягко заставляет два [npc.cocks(true)] погружаться еще глубже в [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] вытекает из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь и выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] и [npc3.name] могли продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя вас обоих "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"ты продолжаешь трахать [npc2.herHim], пока [npc2.her] ритмичные движения помогают твоим [npc.cocks(true)] погружаться всё глубже в [npc2.her] [npc2.asshole+]."
												:"они продолжают трахать [npc2.herHim], пока [npc2.her] ритмичные движения помогают их [npc.cocks(true)] погружаться всё глубже в [npc2.her] [npc2.asshole+]."));
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(force)] оба [npc.cocks(true)] ещё глубже в [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо толкая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(order)] [npc.name] и [npc3.name], чтобы продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] наружу, приказывая им "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"продолжить трахать [npc2.herHim], пока [npc2.her] резкие движения помогают твоим [npc.cocks(true)] погружаться всё глубже в [npc2.her] [npc2.asshole+]."
												:"продолжить трахать [npc2.herHim], пока [npc2.her] резкие движения помогают их [npc.cocks(true)] погружаться всё глубже в [npc2.her] [npc2.asshole+]."));
					default:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] жадно [npc2.verb(force)] оба [npc.cocks(true)] ещё глубже в [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] с шумом вырывается из между [npc2.namePos] [npc2.lips+] и, энергично вгоняя [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] и [npc3.name] могли продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] счастливо [npc2.verb(buck)] [npc2.her] [npc2.hips+] наружу, умоляя вас двоих "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"продолжать трахать [npc2.herHim], пока энергичные движения [npc2.her] помогают вогнать ваши [npc.cocks(true)] глубоко в [npc2.her] [npc2.asshole+]."
												:"продолжать трахать [npc2.herHim], пока энергичные движения [npc2.her] помогают вогнать их [npc.cocks(true)] глубоко в [npc2.her] [npc2.asshole+]."));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] наружу [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] [npc2.asshole+].",
								" [npc2.A_sob+] с шумом вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+].",
								" [npc2.Sobbing] в смятении, и со слезами, бегущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+]."));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)] позволяет [npc.namePos] [npc.cock+] глубоко погружаться в [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, резко выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать [npc2.her], пока движения [npc.her] помогают погрузить [npc.cock+] глубоко в [npc2.her] [npc2.asshole+]."));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.asshole+].",
								" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь под [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать [npc2.her], пока движения [npc.her] помогают погрузить [npc.cock+] глубоко в [npc2.her] [npc2.asshole+]."));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] чертовски [npc2.herHim].",
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.herHim], пока движения [npc2.her] заставляют [npc.her] [npc.cock+] глубоко проникать в [npc2.her] [npc2.asshole+]."));
					default:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, жалко умоляя [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.asshole+]."));
				}
			}
		}
		return "";
	}
	
	public static final SexAction PENIS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Нежный анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.cock] в [npc2.namePos] [npc2.asshole+] и выводите его обратно.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] нежно [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.asshole+], издавая легкий [npc.moan] при каждом толчке [npc.her] [npc.hips], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяя [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.asshole+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.asshole+], [npc.name] осторожно [npc.verb(pump)] [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] покачивает [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",
						"Медленно проталкивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.asshole+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",
						"Втискивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Анал";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай входить своим [npc.cock+] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] жадно [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.asshole+], издавая [npc.a_moan+] при каждом энергичном толчке [npc.her] [npc.hips], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.asshole+], [npc.name] счастливо [npc.verb(pump)] [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)], ритмично двигая [npc.her] [npc.hips] вперед-назад и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
						"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc.verb(thrust)], толкая [npc.her] [npc.hips] вперед и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Глубоко вгоняя [npc.her] [npc.cock+] в [npc2.namePos], [npc2.asshole+], [npc.name] [npc.verb(let)], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Грубый анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] грубо [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.asshole+], издавая [npc.a_moan+] при каждом яростном ударе [npc.her] [npc.hips], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.asshole+], [npc.name] грубо [npc.verb(slam)], толкая [npc.her] [npc.hips] вперед и издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко забиты в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] взад-вперед, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] агрессивно [npc.verb(fuck)] [npc2.herHim]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Жестоко вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] брутально [npc.verb(fuck)] [npc2.name].",
						"Яростно вонзая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] грубо вбивает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
						"С силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] взад-вперед, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Анал";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.asshole+], издавая [npc.a_moan+] при каждом толчке [npc.her] [npc.hips], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name], который одновременно проникает в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.asshole+], [npc.name] счастливо [npc.verb(pump)] [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] раскачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
						"Втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] толчками подает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Жадный анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Жадно входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] жадно [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.asshole+], издавая [npc.a_moan+] при каждом энергичном толчке [npc.her] [npc.hips], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.asshole+], [npc.name] счастливо [npc.verb(pump)] [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)], ритмично двигая [npc.her] [npc.hips] вперед-назад и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
						"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc.verb(thrust)], толкая [npc.her] [npc.hips] вперед и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Глубоко вгоняя [npc.her] [npc.cock+] в [npc2.namePos], [npc2.asshole+], [npc.name] [npc.verb(let)], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться анальному сексу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.cock] из [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] его нежно удерживает, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.asshole+] вместе с [npc3.nameHers].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить обратно внутрь [npc2.her] [npc2.asshole+] вместе с [npc3.nameHers].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.asshole+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.asshole+] на [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] его грубо хватает, прежде чем агрессивно вдавить его обратно в [npc2.her] [npc2.asshole+] вместе с [npc3.nameHers].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо втиснуть обратно в [npc2.her] [npc2.asshole+] вместе с [npc3.nameHers].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.asshole+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.cock+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] крепко сжимает его, прежде чем с жадностью втиснуть обратно в [npc2.her] [npc2.asshole+] вместе с [npc3.nameHers].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с жадностью втиснуть обратно в [npc2.her] [npc2.asshole+] вместе с [npc3.nameHers].",
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.asshole+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.cock+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole], но усилия [npc.her] оказываются тщетными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] нежно сжимает его, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.asshole+].",
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить его обратно внутрь [npc2.her] [npc2.asshole+].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.asshole+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.asshole+] на [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole], но усилия [npc.her] оказываются тщетными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] грубо сжимает его, прежде чем агрессивно вдавить его обратно в [npc2.her] [npc2.asshole+].",
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо вдавить его обратно внутрь [npc2.her] [npc2.asshole+].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.asshole+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.cock+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает его, прежде чем жадно втиснуть обратно в [npc2.her] [npc2.asshole+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно втиснуть обратно внутрь [npc2.her] [npc2.asshole+].",
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.asshole+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Прекратить анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.asshole+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо выдергивая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.asshole+], [npc.name] властно [npc.verb(slide)] водит [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.assCloaca+] в последний раз, прежде чем отстраниться и оставить [npc3.name] продолжать трахать [npc2.herHim].",
								"Сделав последний глубокий толчок в [npc2.name], [npc.name] затем [npc.verb(yank)] вытаскивает [npc.her] [npc.cock+] из [npc2.her] [npc2.asshole+], кладя конец грубому двойному проникновению."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(rub)] водит [npc.cockTip] вверх и вниз по [npc2.her] [npc2.assCloaca+] в последний раз, прежде чем отстраниться и оставить [npc3.name] продолжать трахать [npc2.herHim].",
								"В последний раз глубоко толкнув [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.asshole+], завершая двойное проникновение."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] не может не [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+], пока [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.asshole], и [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc3.name] [npc3.verb(carry)] в движениях туда-сюда в [npc2.herHim].",
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], пытаясь сопротивляться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc3.name] [npc3.verb(continue)] долбить [npc2.her] [npc2.asshole+]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.asshole+], жаждая еще большего внимания [npc.her].",
									" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо выдернув [npc.her] [npc.cock+] из [npc2.namePos] [npc2.asshole+], [npc.name] властно [npc.verb(slide)] [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.assCloaca+] в последний раз, прежде чем отстраниться.",
								"В последний раз глубоко толкнув внутрь [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.asshole+], завершая этот жесткий трах."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Вытаскивая [npc.her] [npc.cock] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(rub)] [npc.cockTip] вверх и вниз по [npc2.her] [npc2.assCloaca+] в последний раз, прежде чем отстраниться.",
								"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.asshole+], завершая этот трах."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.asshole], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющегося [npc.herHim].",
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.asshole+] прочь от [npc.name]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.asshole+], жаждая еще большего внимания [npc.her].",
									" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	//TODO DP
	
	public static final SexAction USING_PENIS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Принять анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Впусти [npc2.namePos] [npc2.cock+] в свою [npc.asshole+] и получай трах.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			String assTargeting = "между ягодицами [npc.her]";
			if(Main.sex.getCharacterPerformingAction().getGenitalArrangement()!=GenitalArrangement.NORMAL) {
				assTargeting = "к [npc.her] [npc.assCloaca+]";
			}
			
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его вверх "+assTargeting+", издавая легкий [npc.moan], прежде чем мягко подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.asshole+], а затем медленно вталкивая [npc.her] [npc.hips] назад и издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его вверх, "+assTargeting+"издавая [npc.a_moan+], прежде чем с силой обрушить [npc.her] [npc.hips] и заставить [npc2.herHim] войти в [npc.her] [npc.asshole+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] жадно [npc.verb(line)] его до [npc.her] [npc.asshole+], а затем с азартом вбивая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его вверх, "+assTargeting+"издавая [npc.a_moan+], прежде чем дернуться, толкая [npc.her] [npc.hips] и заставляя [npc2.herHim] войти в [npc.her] [npc.asshole+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.asshole+], а затем вталкивая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] с нетерпением [npc.verb(guide)] его вверх, "+assTargeting+"издавая [npc.a_moan+], прежде чем отчаянно дернуться, толкая [npc.her] [npc.hips] и заставляя [npc2.herHim] войти в [npc.her] [npc.asshole+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.asshole+], он жадно вгоняет [npc.her] [npc.hips] обратно, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] в [npc2.her] [npc2.cock+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] медленно [npc.verb(move)] [npc.her] [npc.hips], пока [npc2.namePos] [npc2.cock+] не прижмется "+assTargeting+", а затем, слегка [npc.moan], [npc.she] плавно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.asshole+].",
								"Вжимая [npc.her] [npc.asshole+] в [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его поверх [npc.her] [npc.assCloaca+], после чего он медленно отстраняется, издавая тихий [npc.moan], пока [npc.she] [npc.verb(force)] [npc2.herHim] проникает в [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] с силой [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется "+assTargeting+", а затем, с [npc.a_moan+], [npc.she] яростно [npc.verb(slam)] назад и [npc.verb(force)] [npc2.herHim], чтобы ворваться в [npc.her] [npc.asshole+].",
								"Грубо прижимая [npc.her] [npc.asshole+] к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его поверх [npc.her] [npc.assCloaca+], после чего он властно отстраняется, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim] проникает в [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется вверх, "+assTargeting+", а затем с помощью [npc.a_moan+], [npc.she] [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы пронзить [npc.her] [npc.asshole+].",
								"Втискивая [npc.her] [npc.asshole+] назад против [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его вверх через [npc.her] [npc.assCloaca+], прежде чем отстраниться и издать [npc.a_moan+], когда [npc.she] [npc.verb(force)] [npc2.herHim], чтобы пронзить [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] жадно [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется вверх, "+assTargeting+", а затем с помощью [npc.a_moan+], [npc.she] жадно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы пронзить [npc.her] [npc.asshole+].",
								"Отчаянно втискивая [npc.her] [npc.asshole+] назад против [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его вверх через [npc.her] [npc.assCloaca+], прежде чем жадно отстраниться и издать [npc.a_moan+], когда [npc.she] [npc.verb(force)] [npc2.herHim], чтобы пронзить [npc.herHim]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], когда [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахать [npc.namePos] [npc.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед, в то время как [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.namePos] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] энергично [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.cock] вперед и [npc2.verb(start)] безжалостно трахать [npc.her] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] безжалостно [npc2.verb(start)] трахает [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.cock+] из [npc.her] [npc.asshole+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] на [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction USING_PENIS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			
			map.put("one other character fucking [npc.namePos] asshole", size==1);
			map.put("[npc2.namePos] penis to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(SexAreaPenetration.PENIS));
			map.put("[npc2.namePos] penis to be free", SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()==1
					&& !getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Получить двойное проникновение (анальное)";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] присоединиться к [npc3.name], чтобы они трахали твою [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			String assTargeting = "между ягодицами [npc.her]";
			if(Main.sex.getCharacterPerformingAction().getGenitalArrangement()!=GenitalArrangement.NORMAL) {
				assTargeting = "к [npc.her] [npc.assCloaca+]";
			}

			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не довольствуясь тем, что только [npc3.name] трахает [npc.her] [npc.asshole+], [npc.name] нежно [npc.verb(take)] берет [npc2.namePos] [npc2.cock], прежде чем медленно направить его к [npc.her] [npc.assCloaca+]. Издав легкий [npc.moan], [npc.she] затем нежно [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.asshole+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.asshole], [npc.name] [npc.verb(take)] берет [npc2.namePos] [npc2.cock], прежде чем осторожно прицелиться "+assTargeting+". Медленно толкая [npc.her] [npc.hips] назад, [npc.she] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] втискиваются вместе с [npc3.namePos] в [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не довольствуясь лишь тем, что [npc3.name] трахает [npc.her] [npc.asshole+], [npc.name] грубо [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем с силой задвинуть его к [npc.her] [npc.assCloaca+]. Издав [npc.a_moan+], [npc.she] затем неистово [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.asshole+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.asshole], [npc.name] с силой [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем быстро прицелиться, "+assTargeting+". Грубо вбивая [npc.her] [npc.hips] назад, [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] втискиваются вместе с [npc3.namePos] в [npc.her] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не довольствуясь лишь тем, что [npc3.name] трахает [npc.her] [npc.asshole+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем переместить его к [npc.her] [npc.assCloaca+]. Издав [npc.a_moan+], [npc.she] затем [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.asshole+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.asshole], [npc.name] жадно [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем прицелиться "+assTargeting+"[npc.her] [npc.hips] дергается назад, [npc.she] [npc.verb(let)] выталкивает [npc.a_moan+] как [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+], втискиваясь вместе с [npc3.namePos] в [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не довольствуясь лишь чертовым [npc3.name] [npc.her] [npc.asshole+], [npc.name] нетерпеливо [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем жадно поднести его к [npc.her] [npc.assCloaca+]. Издавая [npc.a_moan+], [npc.she] затем отчаянно [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойное проникновение в [npc.her] [npc.asshole+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.asshole], [npc.name] жадно [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем быстро прицелиться "+assTargeting+"Нетерпеливо выгибая [npc.her] [npc.hips], [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] втискиваются вместе с [npc3.namePos] в [npc.her] [npc.asshole+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что [npc3.name] просто трахает [npc.her] [npc.asshole+], [npc.name] медленно [npc.verb(move)] [npc.her] [npc.hips] до тех пор, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] [npc.assCloaca+], а затем, с легким [npc.moan], [npc.she] плавно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойной проход в [npc.her] [npc.asshole+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] грубо [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], тщательно выравнивая [npc2.cockHead], "+assTargeting+" прежде чем медленно откинуться назад и заставить [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что [npc3.name] просто трахает [npc.her] [npc.asshole+], [npc.name] с силой [npc.verb(push)] [npc.her] [npc.hips] назад до тех пор, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] [npc.assCloaca+], а затем, с [npc.a_moan+], [npc.she] яростно [npc.verb(slam)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойной проход в [npc.her] [npc.asshole+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] грубо [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], тщательно выравнивая [npc2.cockHead], "+assTargeting+" прежде чем властно откинуться назад и заставить [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Не довольствуясь лишь тем, что [npc3.name] трахает [npc.her] [npc.asshole+], [npc.name] [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] [npc.assCloaca+], а затем, вместе с [npc.a_moan+], [npc.she] жадно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойной проход в [npc.her] [npc.asshole+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] грубо [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], тщательно выравнивая [npc2.cockHead], "+assTargeting+" прежде чем отстраниться назад и заставить [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Не довольствуясь лишь тем, что [npc3.name] трахает [npc.her] [npc.asshole+], [npc.name] с нетерпением [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] [npc.assCloaca+], а затем, вместе с [npc.a_moan+], [npc.she] жадно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойной проход в [npc.her] [npc.asshole+].",
								"Желая, чтобы её трахали не только [npc3.name], [npc.name] отчаянно [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], стараясь при этом выровнять [npc2.cockHead], "+assTargeting+" прежде чем с нетерпением отстраниться назад и заставить [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.asshole+]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], когда [npc2.she] [npc2.verb(join)] [npc3.herHim] в процессе траха ```[npc.namePos] [npc.asshole+]```.",
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в процессе траха [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], грубо толкая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], когда [npc2.she] [npc2.verb(join)] [npc3.herHim] в процессе траха [npc.namePos] [npc.asshole+].",
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(join)] [npc3.name] в беспощадном трахе [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], когда [npc2.she] [npc2.verb(join)] [npc3.herHim] в процессе траха [npc.namePos] [npc.asshole+].",
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в процессе траха [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.cock+] из [npc.her] [npc.asshole+].",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] на [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], пока [npc2.she] [npc2.verb(join)] [npc3.herHim] в восторженном трахе [npc.namePos] [npc.asshole+].",
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, погружая его глубоко в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в энергичном трахе [npc.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								" Не в силах оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.asshole], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать и [npc2.her], и [npc3.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.asshole+].");
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(join)] [npc3.name] в мягком проникновении в [npc.her] [npc.asshole+].",
								" Тихий [npc2.moan] срывается с [npc2.namePos] губ, прежде чем [npc2.she] [npc2.verb(start)] медленно вводит [npc2.her] [npc2.cock+], проникая [npc3.namePos] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] от восторга, пока [npc2.name] [npc2.verb(join)] [npc3.name], плавно вводя [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(join)] [npc3.name] при грубых толчках [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается из [npc2.namePos] рта, прежде чем [npc2.she] [npc2.verb(start)] яростно вгоняет [npc2.her] [npc2.cock+], проникая [npc3.namePos] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] от восторга, пока [npc2.name] [npc2.verb(join)] [npc3.name], грубо вбивая [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(join)] [npc3.name] при грубых толчках [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается из [npc2.namePos] рта, прежде чем [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+], проникая [npc3.namePos] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(join)] [npc3.name] в мощных [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].");
					default:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в увлеченном ритме [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с нетерпением вгоняя [npc2.her] [npc2.cock+] вместе с [npc3.namePos] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(join)] [npc3.name] в нетерпеливом движении [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].");
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								" Не в силах оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.asshole], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы втиснуть [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.asshole+]."));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" Легкий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно скользя [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+]."));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] яростно вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в [npc.namePos] [npc.asshole+]."));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+]."));
					default:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.asshole+]."));
				}
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_PENIS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Мягко принять анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Пусть [npc2.namePos] [npc2.cock+] нежно трахает твой [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], заставляя два [npc2.cocks(true)] проникать [npc.herHim] всё глубже в [npc.her] [npc.asshole+].",
						"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] подбрасывая [npc.her] [npc.hips], заставляя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] погружаться всё глубже в [npc.her] [npc.asshole+].",
						"Медленно вращая [npc.her] [npc.hips], мягкий [npc.moan] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.asshole+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], заставляя [npc2.namePos] [npc2.cock+] погружаться глубоко в [npc.her] [npc.asshole+].",
						"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] подбрасывая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.asshole+].",
						"Медленно вращая [npc.her] [npc.hips], мягкая [npc.moan] соскальзывает между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Принять анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать твой [npc.asshole+] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С неистовым толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить два [npc2.cocks(true)], пронзающих [npc.herHim], еще глубже в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips], вгоняя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] всё глубже в [npc.her] [npc.asshole+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] соскальзывает между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.asshole+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С неистовым толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips], вгоняя [npc2.namePos] [npc2.cock+] всё глубже в [npc.her] [npc.asshole+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] с силой вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Грубо принять анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать твой [npc.asshole+] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С яростными ударами [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] оба [npc2.cocks(true)], проникающих [npc.herHim] всё глубже в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)], толкая [npc.her] [npc.hips], заставляя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",
						"Сильно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют оба [npc2.cocks(true)] глубоко в [npc.her] [npc.asshole+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С яростным ударом [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)], толкая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.asshole+].",
						"Яростно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_PENIS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Принять анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.asshole+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Резким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из-за [npc.a_moan+], пока [npc.she] [npc.verb(force)] два [npc2.cocks(true)], проникающих [npc.herHim] всё глубже в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(start)], толкая [npc.her] [npc.hips], заставляя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",
						"Вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.asshole+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Резким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из-за [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(start)], толкая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.asshole+].",
						"Вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "С жадностью принять в анус";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать твой [npc.asshole+] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С неистовым толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить два [npc2.cocks(true)], пронзающих [npc.herHim], еще глубже в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips], вгоняя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] всё глубже в [npc.her] [npc.asshole+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] соскальзывает между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.asshole+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С неистовым толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips], вгоняя [npc2.namePos] [npc2.cock+] всё глубже в [npc.her] [npc.asshole+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] с силой вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return PenisAnus.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
			} else {
				return super.getCharactersForParsing();
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться анальному проникновению";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.asshole+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], и в [npc.her] [npc.eyes] всё надувается, а не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить как #IF(npc3.isPlayer())[npc3.namePos], так и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks(true)] из [npc.her] [npc.asshole+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.asshole] от нежеланного двойного проникновения, в отчаянии борясь, пока два [npc2.cocks(true)] продолжают медленно входить и выходить из [npc.her] [npc.asshole+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает вместе с [npc3.namePos] плавно скользить глубоко в [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], и в [npc.her] [npc.eyes] всё надувается, а не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить как #IF(npc3.isPlayer())[npc3.namePos], так и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks(true)] из [npc.her] [npc.asshole+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.asshole] подальше от нежеланного двойного проникновения, в безнадежной борьбе, пока два [npc2.cocks(true)] продолжают грубо вбиваться в [npc.her] [npc.asshole+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает вместе с [npc3.namePos] яростно вколачиваться глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], и в [npc.her] [npc.eyes] всё надувается, а не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить как #IF(npc3.isPlayer())[npc3.namePos], так и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks(true)] из [npc.her] [npc.asshole+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.asshole] подальше от нежеланного двойного проникновения, в безнадежной борьбе, пока два [npc2.cocks(true)] продолжают неистово молотить в [npc.her] [npc.asshole+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает вместе с [npc3.namePos] жадно вбиваться глубоко в [npc.her] [npc.asshole+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass+] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно входит и выходит из [npc.her] [npc.asshole+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает плавно входить глубоко в [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass+] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается в [npc.her] [npc.asshole+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает яростно входить глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass+] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.asshole+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает жадно входить глубоко в [npc.her] [npc.asshole+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить анальный секс";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.cock] из твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Резко вырывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытащить [npc2.her] [npc2.cock] из [npc.her] [npc.asshole+]."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] с облегченным вздохом, который вскоре сменяется [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.asshole+].",
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.asshole+]."));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Резко вырывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытащить [npc2.her] [npc2.cock] из [npc.her] [npc.asshole+]."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] с облегченным вздохом, который вскоре сменяется [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.asshole+].",
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.asshole+]."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
