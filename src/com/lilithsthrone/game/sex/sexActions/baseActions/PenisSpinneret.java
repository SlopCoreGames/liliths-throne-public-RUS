package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class PenisSpinneret {

	// -- Methods for multiple ongoing characters:
	
	static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingDP) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingDP, SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS));
	}

	private static List<GameCharacter> getCharactersForParsing(GameCharacter characterReceivingDP) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingDP)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	public static GameCharacter getPrimaryDPPerformer(GameCharacter characterReceivingDP) {
		if(Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.SPINNERET).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.SPINNERET).keySet().iterator().next();
	}
	
	private static String getSpinneretArea(GameCharacter spinneretOwner) {
		return spinneretOwner.hasTailSpinneret()?UtilText.parse(spinneretOwner, "[npc.tail]"):"abdomen";
	}
	
	// ---
	
	public static final SexAction TEASE_PENIS_OVER_SPINNERET = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить спинрет";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.cockHead] своего [npc.cock] по [npc2.her] [npc2.spinneret+].";
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
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Не смущаясь того, что [npc3.namePos] [npc3.cock] уже глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] медленно дразнит [npc.cockTip+] собственного [npc.cock+] [npc.her], прижимая его к [npc2.her], и в любой момент грозит начать двойное проникновение в [npc.herHim].",
								"С мягким [npc.moan], [npc.name] нежно [npc.verb(guide)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] спинрета, намекая, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в паутину [npc2.her].",
								"Мягко скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], так как [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] в каком-нибудь двойном проникновении.")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Ничуть не смущаясь тем, что [npc3.cock] [npc3.namePos] уже глубоко в [npc2.spinneret+] [npc2.namePos], [npc.name] [npc.verb(start)] грубо тереться головкой ([npc.cockTip+]) своего [npc.cock+] о [npc2.her] прядильное отверстие, угрожая начать двойное проникновение в любой момент.",
								"С помощью [npc.a_moan+], [npc.name] с силой [npc.verb(rub)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.spinneret+], давая понять, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в паутинное отверстие [npc2.her].",
								"Грубо потирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] для какого-нибудь двойного проникновения.")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Не смущаясь того, что [npc3.namePos] [npc3.cock] уже глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] втирая [npc.cockTip+] собственного [npc.cock+] [npc.her] в паутинное отверстие [npc2.her], угрожая в любой момент начать двойное проникновение в [npc.herHim].",
								"С помощью [npc.a_moan+], [npc.name] [npc.verb(rub)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.spinneret+], давая понять, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в паутинное отверстие [npc2.her].",
								"Потирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] для какого-нибудь двойного проникновения.")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Ничуть не смущаясь тем, что [npc3.cock] [npc3.namePos] уже глубоко в [npc2.spinneret+] [npc2.namePos], [npc.name] [npc.verb(start)] нетерпеливо тереться головкой ([npc.cockTip+]) своего [npc.cock+] о [npc2.her] прядильное отверстие, угрожая начать двойное проникновение в любой момент.",
								"Пока [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(rub)] [npc.cockTip+] [npc.her] [npc.cock] по открытым частям [npc2.namePos] [npc2.spinneret+], намекая, что [npc.she] может присоединиться к [npc3.name] и начать двойное проникновение в паутину [npc2.her].",
								"С жадностью растирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по открытым частям [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(signal)], что [npc.she] может присоединиться к [npc3.name] в каком-нибудь двойном проникновении.")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] медленно дразня [npc.cockTip+] вверх и вниз по паутине [npc2.her], готовая в любой момент войти в [npc2.herHim].",
								"С мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.spinneret+], прежде чем начать нежно водить [npc.cockTip] вверх и вниз по паутине [npc2.her].",
								"Мягко скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] нежный [npc.moan] при мысли о том, что он сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Втирая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.spinneret+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать скользить [npc.cockTip+] вверх и вниз по паутинному отверстию [npc2.her], готовый начать трахать [npc2.herHim] в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] вверх к [npc2.namePos] [npc2.spinneret+], прежде чем начать грубо [npc2.verb(grind)] [npc.cockTip] вверх и вниз по паутинному отверстию [npc2.her].",
								"Грубо втирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] издает [npc.a_moan+] при мысли о том, что он сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] скользя [npc.cockTip+] вверх и вниз по паутинному отверстию [npc2.her], готовый проникнуть в [npc2.herHim] в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] до [npc2.namePos] [npc2.spinneret+], прежде чем начать водить [npc.cockTip] вверх и вниз по окутанному паутиной отверстию [npc2.her].",
								"Водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Направляя [npc.her] [npc.cock+] до [npc2.namePos] [npc2.spinneret+], [npc.name] с нетерпением [npc.verb(start)] водит [npc.cockTip+] вверх и вниз по окутанному паутиной отверстию [npc2.her], готовый проникнуть в [npc2.herHim] в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] до [npc2.namePos] [npc2.spinneret+], прежде чем начать с нетерпением водить [npc.cockTip] вверх и вниз по окутанному паутиной отверстию [npc2.her].",
								"С нетерпением водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.spinneret+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.spinneret+] на [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] мягкую [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.spinneret+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.spinneret+], и мягко [npc2.verb(push)] [npc2.her] [npc2.hips+] в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.spinneret+] стимулируется, и [npc2.she] жадно [npc2.verb(push)] [npc2.her] плетущее паутину отверстие против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] отчаянную [npc2.moan], прежде чем жадно вытолкнуть [npc2.her] [npc2.spinneret+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.spinneret+], и отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.spinneret+] стимулируется, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] плетущее паутину отверстие против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] отчаянную [npc2.moan], прежде чем грубо вытолкнуть [npc2.her] [npc2.spinneret+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.spinneret+], и яростно [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.spinneret+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] паукообразное отверстие против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.moan], прежде чем вытолкнуть [npc2.her] [npc2.spinneret+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.spinneret+], и [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isSpinneretVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет девственность своего [npc2.her] веретена.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], так как [npc2.she] [npc2.verb(realise)], что [npc2.she] может вот-вот потерять девственность своего [npc2.her] веретена.",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере девственности своего [npc2.her] веретена перед [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Я никогда раньше этого не делала!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Я никогда раньше такого не делала!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу лишаться девственности!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.spinneret] от [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.spinneret] от [npc.namePos] [npc.cock], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.SPINNERET);
		}
	};
	
	public static final SexAction FORCE_PENIS_OVER_SPINNERET = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнящий член (спинноретра)";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.cockTip] [npc2.her] [npc2.cock] по твоей [npc.spinneret+].";
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
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Не довольствуясь лишь тем, что [npc3.namePos] [npc3.cock] проникает в [npc.herHim], [npc.name] [npc.verb(take)] берет [npc2.namePos] [npc2.cock+], прежде чем направить его к обнаженной части [npc.her] [npc.spinneret] и нежно дразнить [npc.cockHead] над плетущим паутину отверстием [npc.her].",
								"Желая большего, чем просто [npc3.namePos] [npc3.cock+], засунутый в [npc.her] [npc.spinneret+], [npc.name] нежно [npc.verb(take)] берет [npc2.namePos] [npc2.cock+], прежде чем начать водить [npc2.cockTip] вверх и вниз по обнаженной части плетущего паутину отверстия [npc.her].",
								"Пока [npc3.namePos] [npc3.cock+] всё ещё входил и выходил из [npc.her] [npc.spinneret+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], а затем плавно проводил [npc2.cockTip+] по открытой части паутинящегося отверстия [npc.her].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Не довольствуясь лишь тем, что [npc3.namePos] [npc3.cock] проникает в [npc.herHim], [npc.name] грубо [npc.verb(grab)] берет [npc2.namePos] [npc2.cock+], после чего направляет его к открытой части [npc.her] [npc.spinneret] и с силой трет [npc.cockHead] о паутинящееся отверстие [npc.her].",
								"Желая большего, чем просто [npc3.namePos] [npc3.cock+], засунутый в [npc.her] [npc.spinneret+], [npc.name] грубо [npc.verb(seize)] хватает [npc2.namePos] [npc2.cock+], после чего начинает с силой тереть [npc2.cockTip] вверх и вниз по открытой части паутинящегося отверстия [npc.her].",
								"Пока [npc3.namePos] [npc3.cock+] всё ещё входил и выходил из [npc.her] [npc.spinneret+], [npc.name] с силой [npc.verb(grab)] [npc2.namePos] [npc2.cock+], а затем грубо трет [npc2.cockTip+] по открытой части паутинящегося отверстия [npc.her].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Не довольствуясь лишь тем, что [npc3.namePos] [npc3.cock] проникает в [npc.herHim], [npc.name] [npc.verb(take)] берет [npc2.namePos] [npc2.cock+], после чего направляет его к открытой части [npc.her] [npc.spinneret] и трет [npc.cockHead] о паутинящееся отверстие [npc.her].",
								"Желая не просто засунуть [npc3.namePos] [npc3.cock+] в [npc.her] [npc.spinneret+], [npc.name] [npc.verb(take)] [npc2.namePos] [npc2.cock+], прежде чем начать энергично возить [npc2.cockTip] вверх и вниз по открытой части [npc.her] плетущего паутину отверстия.",
								"Пока [npc3.namePos] [npc3.cock+] всё еще входил и выходил из [npc.her] [npc.spinneret+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем начать тереть [npc2.cockTip+] по открытой части [npc.her] плетущего паутину отверстия.")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что лишь [npc3.namePos] [npc3.cock] проникает в [npc.herHim], [npc.name] с нетерпением [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к открытой части [npc.her] [npc.spinneret] и отчаянно тереть [npc.cockHead] по [npc.her] плетущему паутину отверстию.",
								"Желая не просто засунуть [npc3.namePos] [npc3.cock+] в [npc.her] [npc.spinneret+], [npc.name] отчаянно [npc.verb(seize)] [npc2.namePos] [npc2.cock+], прежде чем начать энергично возить [npc2.cockTip] вверх и вниз по открытой части [npc.her] плетущего паутину отверстия.",
								"Пока [npc3.namePos] [npc3.cock+] всё еще входил и выходил из [npc.her] [npc.spinneret+], [npc.name] с жадностью [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем с удовольствием тереть [npc2.cockTip+] по открытой части [npc.her] плетущего паутину отверстия.")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.spinneret+]. Медленно толкая [npc2.cockTip+] вверх и вниз по [npc.her] плетущему паутину отверстию, [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",
								"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.spinneret+], прежде чем начать плавно водить [npc2.cockTip] вверх и вниз по [npc.her] плетущему паутину отверстию.",
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.spinneret+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] обещанием проникновения."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем резко дернуть его к [npc.her] [npc.spinneret+]. Грубо толкая [npc2.cockTip+] вверх и вниз по [npc.her] плетущему паутину отверстию, [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и резко дергает его к [npc.her] [npc.spinneret+], прежде чем начать грубо [npc.verb(force)] [npc2.cockTip] вверх и вниз по [npc.her] плетущему паутину отверстию.",
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(grind)] [npc2.cockTip+] по [npc.her] [npc.spinneret+], она издает [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.spinneret+]. Проталкивая [npc2.cockTip+] вверх и вниз по [npc.her] похожему на паутину отверстию, [npc.she] [npc.verb(tease)] [npc2.name], обещая, что проникновение случится в любой момент.",
								"С помощью [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.spinneret+], прежде чем начать скользить [npc2.cockTip] вверх и вниз по [npc.her] похожему на паутину отверстию.",
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.spinneret+], она издает [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.spinneret+]. С жадностью проталкивая [npc2.cockTip+] вверх и вниз по [npc.her] похожему на паутину отверстию, [npc.she] [npc.verb(tease)] [npc2.name], обещая, что проникновение случится в любой момент.",
								"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)], дело за [npc.her] [npc.spinneret+], прежде чем он с нетерпением начнет скользить [npc2.cockTip] вверх и вниз по [npc.her] плетущему паутину отверстию.",
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] с жадностью [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.spinneret+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)], нежно потирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко водить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(feel)], чувствуя стимуляцию [npc2.her] [npc2.cock], и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] нежно водит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.spinneret+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], грубо втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой начать тереть [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] от стимуляции, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.her] [npc.spinneret+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом трет [npc2.her] [npc2.her] [npc2.cock] вверх-вниз по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] с жадностью скользит [npc2.her] [npc2.her] [npc2.cock] вверх-вниз по [npc.namePos] [npc.spinneret+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.cock] вверх-вниз по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянную [npc2.moan], прежде чем начать скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] чувствует, как [npc2.her] [npc2.cock] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.spinneret+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] пытается оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.spinneret+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянную [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] паутиной усеянного отверстия.",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)], заставляя [npc.name] отпустить [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET);
		}
	};
	
	public static final SexAction SPINNERET_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Управление паутинным аппаратом";
		}

		@Override
		public String getActionDescription() {
			return "Сжимайте свой снабженный внутренними мышцами паутинный аппарат вокруг [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getSpinneretOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.spinneret] вокруг #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] на [npc.a_moan+] как [npc.she] [npc.verb(focus)] управлении дополнительными мышцами, выстилающими внутренности [npc.her] [npc.spinneret]. Сжимая их вокруг #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)], [npc.name] [npc.verb(cause)] оба, "
								+ (getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.game.getPlayer())?"вы":"их")
								+ " чтобы вырвались непроизвольные крики удовольствия."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], входящие в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.spinneret+] вокруг двух [npc2.cocks(true)], которые сейчас одновременно проникают в [npc.herHim].",
						"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении дополнительными мышцами глубоко внутри [npc.her] [npc.spinneret], сжимая их и массируя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия."));
				
			} else {
				return UtilText.returnStringAtRandom(
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)] на сжатии дополнительных внутренних мышц внутри [npc.her] [npc.spinneret] вокруг [npc2.namePos] [npc2.cock+].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] извлекает из [npc.a_moan+] [npc.she] [npc.verb(focus)] на управлении дополнительными мышцами, выстилающими внутренности [npc.her] [npc.spinneret]. Сжимая и сдавливая их вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], погружаясь в размеренный ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.spinneret+] вокруг [npc2.namePos] [npc2.cock+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении дополнительными мышцами глубоко внутри [npc.her] [npc.spinneret], сжимая их и массируя [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
			}
		}
	};
	
	public static final SexAction PENIS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать секс-плетение";
		}

		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.cock+] в [npc2.namePos] [npc2.spinneret+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] по поверхности [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] плетущее паутину отверстие.",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по поверхности [npc2.namePos] [npc2.spinneret+], и медленным, уверенным давлением [npc.she] осторожно [npc.verb(sink)] его глубоко в [npc2.her] плетущее паутину отверстие."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.cock+] в паутину [npc2.her] отверстия.",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.spinneret+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в паутину [npc2.her] отверстия."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно ударить вперед, вбивая [npc.her] [npc.cock+] глубоко в паутину [npc2.her] отверстия.",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.spinneret+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в паутину [npc2.her] отверстия."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.cock+] в паутину [npc2.her] отверстия.",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.spinneret+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в паутину [npc2.her] отверстия."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, погружая [npc.her] [npc.cock+] в паутину [npc2.her] отверстия.",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.spinneret+], и коротким толчком [npc.she] [npc.verb(sink)] глубоко в паутину [npc2.her] отверстия."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				String spinneretArea = getSpinneretArea(Main.sex.getCharacterTargetedForSexAction(this));
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], после чего плавно отталкиваясь с [npc2.her] "+spinneretArea+" чтобы погрузить его еще глубже в [npc2.her] [npc2.spinneret+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно отталкиваясь с [npc2.her] "+spinneretArea+", погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.spinneret+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего яростно отталкиваясь с [npc2.her] "+spinneretArea+" чтобы вогнать его еще глубже в [npc2.her] [npc2.spinneret+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] неистово дергаясь назад с [npc2.her] "+spinneretArea+", грубо заставляя [npc.name] погружать [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.spinneret+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], а затем жадно дергается назад с [npc2.her] "+spinneretArea+" чтобы погрузить его еще глубже в [npc2.her] [npc2.spinneret+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно дергается назад с [npc2.her] "+spinneretArea+", отчаянно помогая вогнать [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.spinneret+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], а затем дергается назад с [npc2.her] "+spinneretArea+" чтобы погрузить его еще глубже в [npc2.her] [npc2.spinneret+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] дергается назад с [npc2.her] "+spinneretArea+", помогая вогнать [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.spinneret+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] [npc.namePos] вжимается глубоко в [npc2.her] [npc2.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size();
			
			map.put("one other character fucking [npc2.namePos] spinneret", size==1);
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
			return "Двойное проникновение (паутина)";
		}
		
		@Override
		public String getActionDescription() {
			return UtilText.parse(getCharactersForParsing(),
					"Присоединиться к [npc3.name] в трахе [npc2.namePos] [npc2.spinneret+].");
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] из [npc.her] [npc.cock] до самых [npc2.namePos] [npc2.spinneret+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] мягко [npc.verb(push)] вперед, вталкивая [npc.her] [npc.cock+] вместе с [npc3.hers] и присоединяясь к [npc3.herHim] в двойном проникновении в [npc2.name]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her], [npc.cock] подтягивая [npc2.namePos] [npc2.spinneret+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] грубо [npc.verb(thrust)] вперед, заставляя [npc.her] [npc.cock+] войти вместе с [npc3.hers] и присоединяясь к [npc3.herHim] для двойного проникновения в [npc2.name]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her], [npc.cock] подтягивая [npc2.namePos] [npc2.spinneret+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] [npc.verb(thrust)] вперед, втискивая [npc.her] [npc.cock+] вместе с [npc3.hers] и присоединяясь к [npc3.herHim] для двойного проникновения в [npc2.name]."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться к веселью, [npc.name] [npc.verb(bring)] [npc.cockHead+] [npc.her], [npc.cock] подтягивая [npc2.namePos] [npc2.spinneret+], и в следующий раз, когда [npc3.name] [npc3.verb(pull)] назад, [npc.she] отчаянно [npc.verb(thrust)] вперед, с силой вбивая [npc.her] [npc.cock+] вместе с [npc3.hers] и присоединяясь к [npc3.herHim] для двойного проникновения в [npc2.name]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда второй [npc.cock] входит в [npc2.her] [npc2.spinneret+], после чего плавно выталкивая [npc2.her] [npc2.hips] наружу, чтобы глубоко погрузить его рядом с [npc3.namePos] [npc3.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока второй [npc.cock] входит в [npc2.her] [npc2.spinneret+], после чего он грубо выталкивает [npc2.her] [npc2.hips], чтобы глубоко вогнать его рядом с [npc3.namePos] [npc3.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока второй [npc.cock] входит в [npc2.her] [npc2.spinneret+], после чего он резко выталкивает [npc2.her] [npc2.hips], чтобы глубоко вогнать его рядом с [npc3.namePos] [npc3.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда второй [npc.cock] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] ради [npc.name] и того, чтобы [npc3.name] вытащил его.",
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от второго нежелательного проникновения, и слезы снова начинают свободно течь по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(feel)] незваный [npc.namePos] глубоко втискивается в [npc.cock] [npc2.her] [npc2.spinneret+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока второй [npc.cock] входит в [npc2.her] [npc2.spinneret+], после чего он жадно выталкивает [npc2.her] [npc2.hips], чтобы глубоко вогнать его рядом с [npc3.namePos] [npc3.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" Отчаянно пытаясь, но не в силах отстраниться от двух [npc.cocks(истинных)] внутри [npc2.herHim], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] и [npc3.name] вытащили их из [npc2.her] [npc2.spinneret+].",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name] и [npc3.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы оба "
										+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())?"вы":"их")+" вытащить из [npc2.her] [npc2.spinneret+].",
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] в тщетной попытке вытолкнуть [npc.cocks(true)], вбивающиеся в [npc2.her] [npc2.spinneret+], умоляя и рыдая, чтобы [npc.name] и [npc3.name] оставили [npc2.herHim] в покое.")));
					case SUB_NORMAL:
						return (UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], заставляя два [npc.cocks(true)] погрузиться еще глубже в [npc2.her] [npc2.spinneret+].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] и [npc3.name] могли продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя вас обоих "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"тебе продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают твоим [npc.cocks(true)] глубоко погружаться в [npc2.her] [npc2.spinneret+]."
												:"им продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают их [npc.cocks(true)] глубоко погружаться в [npc2.her] [npc2.spinneret+]."))));
					case DOM_GENTLE:
						return (UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] мягко [npc2.verb(start)], заставляя два [npc.cocks(true)] погрузиться еще глубже в [npc2.her] [npc2.spinneret+].",
								" [npc2.A_moan+] вытекает из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь и выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] и [npc3.name] могли продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя вас обоих "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"тебе продолжать трахать [npc2.herHim], пока ритмичные движения [npc2.her] помогают твоим [npc.cocks(true)] глубоко погружаться в [npc2.her] [npc2.spinneret+]."
												:"им продолжать трахать [npc2.herHim], пока ритмичные движения [npc2.her] помогают их [npc.cocks(true)] глубоко погружаться в [npc2.her] [npc2.spinneret+]."))));
					case DOM_ROUGH:
						return (UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(force)] оба [npc.cocks(true)] еще глубже в [npc2.her] [npc2.spinneret+].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо толкая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(order)] [npc.name] и [npc3.name], чтобы продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] наружу, приказывая им "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"тебе, чтобы продолжать трахать [npc2.herHim], пока [npc2.her] резкие движения помогают вогнать твои [npc.cocks(true)] глубоко в [npc2.her] [npc2.spinneret+]."
												:"им, чтобы продолжать трахать [npc2.herHim], пока [npc2.her] резкие движения помогают вогнать их [npc.cocks(true)] глубоко в [npc2.her] [npc2.spinneret+]."))));
					default:
						return (UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] жадно [npc2.verb(force)] оба [npc.cocks(true)] еще глубже в [npc2.her] [npc2.spinneret+].",
								" [npc2.A_moan+] с шумом вырывается из между [npc2.namePos] [npc2.lips+] и, энергично вгоняя [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] и [npc3.name] могли продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] счастливо [npc2.verb(buck)] [npc2.her] [npc2.hips+] наружу, умоляя вас двоих "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"тебе, чтобы продолжать трахать [npc2.herHim], пока [npc2.her] энергичные движения помогают вогнать твои [npc.cocks(true)] глубоко в [npc2.her] [npc2.spinneret+]."
												:"им, чтобы продолжать трахать [npc2.herHim], пока [npc2.her] энергичные движения помогают вогнать их [npc.cocks(true)] глубоко в [npc2.her] [npc2.spinneret+]."))));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] из нее [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащить из [npc2.her] [npc2.spinneret+].",
								" [npc2.A_sob+] с силой вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name]; слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], умоляя [npc.herHim] вытащить его из [npc2.her] [npc2.spinneret+].",
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.spinneret+]."));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], заставляя [npc.namePos] [npc.cock+] глубоко погрузиться в [npc2.her] [npc2.spinneret+].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, резко выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать её, пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.spinneret+]."));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ медленно [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)], нежно умоляя [npc.name] продолжать трахать [npc2.her] [npc2.spinneret+].",
								" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь под [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать её, пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.spinneret+]."));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] чертовски [npc2.herHim].",
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.herHim], пока движения [npc2.her] заставляют [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.spinneret+]."));
					default:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] с жадностью [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.spinneret+].",
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
								" [npc2.Moaning] в восторге, [npc2.name] с жадностью [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, умоляя [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный секс спинреттой";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.cock] в [npc2.namePos] и выводите из [npc2.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] мягко [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.spinneret+], издавая легкий [npc.moan] при каждом толчке [npc.her] [npc.hips], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.spinneret+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",
						"Когда оба [npc.her] и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.spinneret+], [npc.name] нежно [npc.verb(pump)] [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] покачивает [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",
						"Медленно проталкивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.spinneret+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",
						"Скользя [npc.her] [npc.cock+] в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Спиннерет-трах";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай входить и выходить своим [npc.cock+] в [npc2.namePos] [npc2.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] с нетерпением [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.spinneret+], издавая [npc.a_moan+] при каждом энергичном толчке [npc.her] [npc.hips], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.spinneret+], [npc.name] с энтузиазмом [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.spinneret+], [npc.name] счастливо [npc.verb(pump)] [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] с энтузиазмом качает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
						"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"[npc.her] [npc.cock+] глубоко входит в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], жадно перекачивая [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый секс-спиннерет";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.spinneret+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] грубо [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.spinneret+], издавая [npc.a_moan+] при каждом яростном ударе [npc.her] [npc.hips], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.spinneret+], [npc.name] грубо [npc.verb(slam)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.spinneret+], [npc.name] яростно [npc.verb(thrust)] [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] агрессивно [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Жестоко вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.name].",
						"Яростно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
						"С силой вбивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы грубо швырять [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Спиннерет-трах";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать трахать [npc2.namePos] [npc2.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.spinneret+], издавая [npc.a_moan+] при каждом толчке [npc.her] [npc.hips], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.spinneret+], [npc.name] счастливо [npc.verb(pump)] [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] качает [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
						"Втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] вгоняет [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный секс-спиннерет";
		}

		@Override
		public String getActionDescription() {
			return "Жадно качай свой [npc.cock+] туда-сюда в [npc2.namePos] [npc2.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"Пока [npc.her] [npc.cock+] трется о [npc3.nameHers], [npc.name] с нетерпением [npc.verb(thrust)] глубоко внутри [npc2.namePos] [npc2.spinneret+], издавая [npc.a_moan+] при каждом энергичном толчке [npc.her] [npc.hips], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim].",
						"Присоединяясь к [npc3.name] для двойного проникновения в [npc2.namePos] [npc2.spinneret+], [npc.name] с энтузиазмом [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"Когда и [npc.her], и [npc3.namePos] [npc.cocks(true)] глубоко засунуты в [npc2.namePos] [npc2.spinneret+], [npc.name] счастливо [npc.verb(pump)] [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(start)] с энтузиазмом качает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
						"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
						"[npc.her] [npc.cock+] глубоко входит в [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], жадно перекачивая [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться сексу-спиннерету";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.cock] из [npc2.namePos] [npc2.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] его нежно удерживает, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.spinneret+] вместе с [npc3.nameHers].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить обратно внутрь [npc2.her] [npc2.spinneret+] вместе с [npc3.nameHers].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.spinneret+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.spinneret+] вниз на [npc.her] [npc.cock+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] его грубо удерживает, прежде чем агрессивно вдавить его обратно в [npc2.her] [npc2.spinneret+] вместе с [npc3.nameHers].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо затолкнуть обратно в [npc2.her] [npc2.spinneret+] вместе с [npc3.nameHers].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.spinneret+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.spinneret+] вниз на [npc.her] [npc.cock+].")));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] крепко сжимает его, прежде чем с жадностью затолкнуть обратно в [npc2.her] [npc2.spinneret+] вместе с [npc3.nameHers].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с жадностью затолкнуть обратно в [npc2.her] [npc2.spinneret+] вместе с [npc3.nameHers].",
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.spinneret+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.spinneret+] на [npc.her] [npc.cock+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] нежно сжимает его, прежде чем мягко затолкнуть обратно в [npc2.her] [npc2.spinneret+].",
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно затолкнуть обратно внутрь [npc2.her] [npc2.spinneret+].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.spinneret+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.spinneret+] вниз на [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] грубо сжимает его, прежде чем агрессивно затолкнуть обратно в [npc2.her] [npc2.spinneret+].",
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо затолкнуть обратно внутрь [npc2.her] [npc2.spinneret+].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.spinneret+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.spinneret+] вниз на [npc.her] [npc.cock+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает его, прежде чем жадно втиснуть обратно в [npc2.her] [npc2.spinneret+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно втиснуть обратно внутрь [npc2.her] [npc2.spinneret+].",
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.spinneret+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.spinneret+] на [npc.her] [npc.cock+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить спиннерет-трах";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.spinneret+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"Грубо вырывая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.spinneret+], [npc.name] властно [npc.verb(slide)] водит [npc.cockTip+] вверх и вниз по [npc2.her] плетущему паутину отверстию в последний раз, прежде чем отстраниться и оставить [npc3.name] продолжать трахать [npc2.herHim].",
								"Сделав последний глубокий толчок в [npc2.name], [npc.name] затем [npc.verb(yank)] вытаскивает [npc.her] [npc.cock+] из [npc2.her] [npc2.spinneret+], кладя конец грубому двойному проникновению.")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"Выскальзывая [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(rub)] водит [npc.cockTip] вверх и вниз по [npc2.her] плетущему паутину отверстию в последний раз, прежде чем отстраниться и оставить [npc3.name] продолжать трахать [npc2.herHim].",
								"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.spinneret+], завершая двойное проникновение.")));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
									" [npc2.Name] не может не [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+], пока [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.spinneret], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc3.name] [npc3.verb(carry)] толчками входит и выходит из [npc2.herHim].",
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], чтобы бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc3.name] [npc3.verb(continue)] долбить [npc2.her] [npc2.spinneret+].")));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.spinneret+], жаждая еще большего внимания [npc.her].",
									" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos].")));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо выдергивая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.spinneret+], [npc.name] властно [npc.verb(slide)] [npc.cockTip+] вверх и вниз по паутинному отверстию [npc2.her] в последний раз, прежде чем отстраниться.",
								"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.spinneret+], завершая этот жесткий трах."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Скользя [npc.her] [npc.cock] из [npc2.namePos] [npc2.spinneret+], [npc.name] [npc.verb(rub)] [npc.cockTip] вверх и вниз по [npc2.her] паутине отверстия в последний раз, прежде чем отстраниться.",
								"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.spinneret+], ставя точку в этом трахе."));
						break;
				}
				

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.spinneret], и [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющегося [npc.herHim].",
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.spinneret+] прочь от [npc.name]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.spinneret+], жаждая еще большего внимания [npc.her].",
									" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction USING_PENIS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Получить паучий трах";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.cock+] в свою [npc.spinneret+] и трахайся.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			String spinneretArea = getSpinneretArea(Main.sex.getCharacterPerformingAction());
			
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Сжимая [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его до [npc.her] "+spinneretArea+", издавая легкий [npc.moan], прежде чем мягко подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.spinneret+].",
								"Сжимая [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] "+spinneretArea+", прежде медленно вгоняя [npc.her] [npc.hips] назад и издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его вверх до [npc.her] "+spinneretArea+", издавая [npc.a_moan+], прежде чем с силой вогнать [npc.her] [npc.hips] и заставить [npc2.herHim] проникать в [npc.her] [npc.spinneret+].",
								"Сжимая [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] "+spinneretArea+", прежде жадно вгоняя [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его вверх до [npc.her] "+spinneretArea+", издавая [npc.a_moan+], прежде чем дернуться, вгоняя [npc.her] [npc.hips] и заставляя [npc2.herHim] проникать в [npc.her] [npc.spinneret+].",
								"Сжимая [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] "+spinneretArea+", прежде вгоняя [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] жадно [npc.verb(guide)] его вверх до [npc.her] "+spinneretArea+", издавая [npc.a_moan+], прежде чем отчаянно дернуться, вгоняя [npc.her] [npc.hips] и заставляя [npc2.herHim] проникать в [npc.her] [npc.spinneret+].",
								"Сжимая [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] "+spinneretArea+", прежде жадно толкая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] медленно [npc.verb(move)] [npc.her] [npc.hips], пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] "+spinneretArea+", а затем с легким [npc.moan], [npc.she] плавно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.spinneret+].",
								"Толкать [npc.her] "+spinneretArea+" назад, прижимая к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его над паутинным отверстием [npc.her], прежде чем медленно отстраниться, издавая тихий [npc.moan], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] с силой [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] "+spinneretArea+", а затем с [npc.a_moan+], [npc.she] яростно [npc.verb(slam)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.spinneret+].",
								"Грубо толкая [npc.her] "+spinneretArea+" назад, прижимая к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его над паутинным отверстием [npc.her], прежде чем властно отстраниться, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] "+spinneretArea+", а затем с [npc.a_moan+], [npc.she] [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.spinneret+].",
								"Толкать [npc.her] "+spinneretArea+" прижавшись спиной к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его над [npc.her] плетущим паутину отверстием, а затем отстраняясь и издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] жадно [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижимается к [npc.her] "+spinneretArea+", а затем, используя [npc.a_moan+], [npc.she] жадно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.spinneret+].",
								"Отчаянно толкая [npc.her] "+spinneretArea+" спиной к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его над [npc.her] плетущим паутину отверстием, прежде чем жадно отстраниться и издать [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], осторожно толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахать [npc.namePos] [npc.spinneret+].",
	
								" С тихим [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.spinneret+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], с нетерпением толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.spinneret+].",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.spinneret+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вколачивает [npc2.her] [npc2.cock] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.spinneret+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.spinneret+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.spinneret+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.spinneret+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.cock+] из [npc.her] [npc.spinneret+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			
			map.put("one other character fucking [npc.namePos] spinneret", size==1);
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
			return "Получить двойное проникновение (паучье веретено)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getCharactersForParsing(),
				"Заставить [npc2.name] присоединиться к [npc3.name], чтобы они трахали твою [npc.spinneret+].");
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			String spinneretArea = getSpinneretArea(Main.sex.getCharacterPerformingAction());

			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Не довольствуясь тем, что только [npc3.name] трахает [npc.her] [npc.spinneret+], [npc.name] нежно [npc.verb(take)] берет [npc2.namePos] [npc2.cock], прежде чем медленно направить его к [npc.her] паутинному отверстию. Издавая легкий [npc.moan], [npc.she] затем нежно [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.spinneret+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.spinneret], [npc.name] [npc.verb(take)] берет [npc2.namePos] [npc2.cock], прежде чем осторожно приставить его к [npc.her] "+spinneretArea+". Медленно отодвигая [npc.her] [npc.hips] назад, [npc.she] [npc.verb(let)] издает мягкий [npc.moan], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] вталкивается вместе с [npc3.namePos] в [npc.her] [npc.spinneret+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Ему мало просто трахать [npc3.name] [npc.her] [npc.spinneret+], поэтому [npc.name] грубо [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем с силой втиснуть его в [npc.her] плетущее паутину отверстие. Издавая [npc.a_moan+], [npc.she] затем неистово [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойное проникновение в [npc.her] [npc.spinneret+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.spinneret], [npc.name] с силой [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем быстро нацелить его на [npc.her] "+spinneretArea+". Грубо вбивая [npc.her] [npc.hips] назад, [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] вталкиваются вместе с [npc3.namePos] в [npc.her] [npc.spinneret+].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Ему мало просто трахать [npc3.name] [npc.her], [npc.spinneret+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем поднести его к [npc.her] паутине ее отверстия. Выпустив [npc.a_moan+], [npc.she] затем [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.spinneret+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.spinneret], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем нацелить его на [npc.her] "+spinneretArea+". Ритмично выгибая [npc.her] [npc.hips] назад, [npc.she] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] втискивается вместе с [npc3.namePos] в [npc.her] [npc.spinneret+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Недовольный тем, что просто трахает [npc3.name] [npc.her] [npc.spinneret+], [npc.name] нетерпеливо [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем жадно поднести его к паутине [npc.her] отверстия. Издавая [npc.a_moan+], [npc.she] затем отчаянно [npc.verb(buck)] [npc.her] [npc.hips] и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] и устроить двойное проникновение в [npc.her] [npc.spinneret+].",
							"Желая большего, чем просто [npc3.namePos] [npc3.cock] в [npc.her] [npc.spinneret], [npc.name] жадно [npc.verb(grab)] [npc2.namePos] [npc2.cock], прежде чем быстро прицелиться к [npc.her] "+spinneretArea+". Нетерпеливо выгибая [npc.her] [npc.hips] назад, [npc.she] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] втискивается вместе с [npc3.namePos] в [npc.her] [npc.spinneret+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что [npc3.name] просто трахает [npc.her] [npc.spinneret+], [npc.name] медленно [npc.verb(move)] [npc.her] [npc.hips] до тех пор, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] плетущему паутину отверстию, а затем, с легким [npc.moan], [npc.she] нежно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.spinneret+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] грубо [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], стараясь прицелить [npc2.cockHead] к [npc.her] "+spinneretArea+" прежде чем медленно двинуться назад и заставить [npc2.herHim] присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.spinneret+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что [npc3.name] просто трахает [npc.her] [npc.spinneret+], [npc.name] с силой [npc.verb(push)] [npc.her] [npc.hips] назад до тех пор, пока [npc2.namePos] [npc2.cock+] не прижмется к [npc.her] плетущему паутину отверстию, а затем, с [npc.a_moan+], [npc.she] яростно [npc.verb(slam)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] в двойном проникновении в [npc.her] [npc.spinneret+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] грубо [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], стараясь прицелить [npc2.cockHead] к [npc.her] "+spinneretArea+" прежде чем властно откинуться назад, заставляя [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.spinneret+].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что [npc3.name] просто трахает [npc.her] [npc.spinneret+], [npc.name] [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется к паутинному отверстию [npc.her], а затем, с помощью [npc.a_moan+], [npc.she] жадно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.spinneret+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] грубо [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], стараясь прицелить [npc2.cockHead] к [npc.her] "+spinneretArea+" прежде чем откинуться назад, заставляя [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.spinneret+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Не довольствуясь тем, что [npc3.name] просто трахает [npc.her] [npc.spinneret+], [npc.name] с нетерпением [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижмется к паутинному отверстию [npc.her], а затем, с помощью [npc.a_moan+], [npc.she] жадно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.spinneret+].",
								"Желая, чтобы ее трахали не только [npc3.name], [npc.name] отчаянно [npc.verb(push)] назад к [npc2.namePos] [npc2.cock], стараясь при этом совместить [npc2.cockHead] с [npc.her] "+spinneretArea+" прежде чем с нетерпением откинуться назад, заставляя [npc2.herHim] присоединиться к [npc3.name] для двойного проникновения в [npc.her] [npc.spinneret+].")));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], пока [npc2.she] [npc2.verb(join)] [npc3.herHim] в ебучем [npc.namePos] [npc.spinneret+].",
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.spinneret+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в ебучем [npc.herHim].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], грубо подталкивая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], пока [npc2.she] [npc2.verb(join)] [npc3.herHim] в ебучем [npc.namePos] [npc.spinneret+].",
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(join)] [npc3.name] в беспощадном трахе [npc.herHim].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подталкивая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], пока [npc2.she] [npc2.verb(join)] [npc3.herHim] в ебучем [npc.namePos] [npc.spinneret+].",
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.spinneret+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в ебучем [npc.herHim].")));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.cock+] из [npc.her] [npc.spinneret+].",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.spinneret+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед рядом с [npc3.namePos], пока [npc2.she] [npc2.verb(join)] [npc3.herHim] в пылком трахе [npc.namePos] [npc.spinneret+].",
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, погружая его глубоко в [npc.namePos] [npc.spinneret+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в энергичном трахе [npc.herHim].")));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" Не в силах оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.spinneret], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать и [npc2.her], и [npc3.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.spinneret+]."));
					case DOM_GENTLE:
						return UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(join)] [npc3.name] в мягком проникновении в [npc.her] [npc.spinneret+].",
								" Легкий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно вводит [npc2.her] [npc2.cock+] вместе с [npc3.namePos] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(join)] [npc3.name], плавно вводя [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+]."));
					case DOM_ROUGH:
						return UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(join)] [npc3.name] при грубых толчках [npc.her] [npc.spinneret+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] яростно вгоняет [npc2.her] [npc2.cock+] вместе с [npc3.namePos] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(join)] [npc3.name], грубо вбивая [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+]."));
					case SUB_NORMAL:
						return UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(join)] [npc3.name] при грубых толчках [npc.her] [npc.spinneret+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] вместе с [npc3.namePos] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(join)] [npc3.name] в неистовых [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+]."));
					default:
						return UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(join)] [npc3.name] в увлеченном размашистом [npc.her] [npc.spinneret+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с нетерпением вгоняя [npc2.her] [npc2.cock+] вместе с [npc3.namePos] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(join)] [npc3.name] в нетерпеливом вбивании [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+]."));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								" Не в силах оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.spinneret], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.spinneret+]."));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.spinneret+].",
								" Нежный [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно скользя [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+]."));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.spinneret+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] яростно вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в [npc.namePos] [npc.spinneret+]."));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.spinneret+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+]."));
					default:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.spinneret+].",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.spinneret+].",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно оттрахан(а) спиннеретом";
		}

		@Override
		public String getActionDescription() {
			return "Нежно позволь(те), чтобы [npc2.namePos] [npc2.cock+] оттрахал(а) твой [npc.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"С мягким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить два [npc2.cocks(true)], проникающих в [npc.herHim], еще глубже в [npc.her] [npc.spinneret+].",
						"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)], подбрасывая [npc.her] [npc.hips], заставляя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] проникать еще глубже в [npc.her] [npc.spinneret+].",
						"Медленно вращая [npc.her] [npc.hips], между [npc.namePos] [npc.lips+] вырывается мягкий [npc.moan], пока движения [npc.her] заставляют два [npc2.cocks(true)] глубоко войти в [npc.her] [npc.spinneret+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С мягким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
						"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)], подбрасывая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать еще глубже в [npc.her] [npc.spinneret+].",
						"Медленно вращая [npc.her] [npc.hips], мягкая [npc.moan] струится между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнутый паутиной";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свой [npc.spinneret+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"С неистовым выгибанием [npc.her] [npc.hips], [npc.name] [npc.verb(let)] вовне [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить два [npc2.cocks(true)], пронзающих [npc.herHim], всё глубже в [npc.her] [npc.spinneret+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] выгибает [npc.her] [npc.hips], вгоняя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] всё глубже в [npc.her] [npc.spinneret+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] струится между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.spinneret+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С неистовым выгибанием [npc.her] [npc.hips], [npc.name] [npc.verb(let)] вовне [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] выгибает [npc.her] [npc.hips], вгоняя [npc2.namePos] [npc2.cock+] всё глубже в [npc.her] [npc.spinneret+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо оттрахан(а) спинреттой";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать твой [npc.spinneret+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"От яростных ударов [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] оба [npc2.cocks(true)], проникая всё глубже в [npc.her] [npc.spinneret+] через [npc.herHim].",
						"При [npc.a_moan+], [npc.name] грубо [npc.verb(start)], толкая [npc.her] [npc.hips], заставляя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] проникать всё глубже в [npc.her] [npc.spinneret+].",
						"Сильно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют оба [npc2.cocks(true)] глубоко в [npc.her] [npc.spinneret+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"От яростного удара [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
						"При [npc.a_moan+], [npc.name] грубо [npc.verb(start)], толкая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.spinneret+].",
						"Сильно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнутый паутиной";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.spinneret+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"Резким движением [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(force)], пока оба [npc2.cocks(true)] проникают [npc.herHim] всё глубже в [npc.her] [npc.spinneret+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips], заставляя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] проникать всё глубже в [npc.her] [npc.spinneret+].",
						"Вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.spinneret+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Резким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.spinneret+].",
						"Вращающийся [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаемый спиннеретом";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свой [npc.spinneret+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"С неистовым выгибанием [npc.her] [npc.hips], [npc.name] [npc.verb(let)] вовне [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить два [npc2.cocks(true)], пронзающих [npc.herHim], всё глубже в [npc.her] [npc.spinneret+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] выгибает [npc.her] [npc.hips], вгоняя #IF(npc3.isPlayer())[npc3.namePos] и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks+(true)] всё глубже в [npc.her] [npc.spinneret+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] струится между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют два [npc2.cocks(true)] глубоко в [npc.her] [npc.spinneret+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С неистовым выгибанием [npc.her] [npc.hips], [npc.name] [npc.verb(let)] вовне [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+].",
						"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] выгибает [npc.her] [npc.hips], вгоняя [npc2.namePos] [npc2.cock+] всё глубже в [npc.her] [npc.spinneret+].",
						"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляясь траху спиннеретом";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.spinneret+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], чтобы накопить [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить как #IF(npc3.isPlayer())[npc3.namePos], так и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks(true)] из [npc.her] [npc.spinneret+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.spinneret] от нежеланного двойного проникновения, в отчаянной борьбе, пока два [npc2.cocks(true)] продолжают медленно скользить туда-сюда в [npc.her] [npc.spinneret+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает присоединяться к [npc3.namePos], плавно скользя глубоко в [npc.her] [npc.spinneret+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], чтобы накопить [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить как #IF(npc3.isPlayer())[npc3.namePos], так и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks(true)] из [npc.her] [npc.spinneret+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.spinneret] подальше от нежеланного двойного проникновения, в безнадежной борьбе, пока два [npc2.cocks(true)] продолжают грубо вбиваться в [npc.her] [npc.spinneret+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает присоединяться к [npc3.namePos], яростно вонзаясь глубоко в [npc.her] [npc.spinneret+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(PenisSpinneret.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], чтобы накопить [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить как #IF(npc3.isPlayer())[npc3.namePos], так и [npc2.namePos]#ELSE[npc2.namePos] и [npc3.namePos]#ENDIF [npc2.cocks(true)] из [npc.her] [npc.spinneret+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.spinneret] подальше от нежеланного двойного проникновения, в безнадежной борьбе, пока два [npc2.cocks(true)] продолжают неистово качать туда-сюда в [npc.her] [npc.spinneret+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает присоединяться к [npc3.namePos], жадно вонзаясь глубоко в [npc.her] [npc.spinneret+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass+] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.spinneret+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает плавно входить глубоко в [npc.her] [npc.spinneret+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass+] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.spinneret+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает яростно входить глубоко в [npc.her] [npc.spinneret+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+].",
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.ass+] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] неистово молотит туда-сюда в [npc.her] [npc.spinneret+].",
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] продолжает жадно входить глубоко в [npc.her] [npc.spinneret+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить спиннерет-трах";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытянуть [npc2.her] [npc2.cock] из твоей [npc.spinneret+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisSpinneret.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Резко вырывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+], [npc.name] [npc.verb(let)] с угрожающим рыком, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.spinneret+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.cock] из [npc.her] [npc.spinneret+]."));
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
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.spinneret+].",
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.spinneret+]."));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Резко вырывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+], [npc.name] [npc.verb(let)] с угрожающим рыком, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.spinneret+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.spinneret+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.cock] из [npc.her] [npc.spinneret+]."));
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
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.spinneret+].",
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.spinneret+]."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
