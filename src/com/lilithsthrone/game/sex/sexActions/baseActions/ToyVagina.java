package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
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
 * TODO
 * 
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class ToyVagina {
	
	public static AbstractClothing getToyInVagina(GameCharacter character) {
		return character.getClothingInSlot(InventorySlot.VAGINA);
	}
	
	public static boolean isToyInVagina(GameCharacter character) {
		return getToyInVagina(character)!=null;
	}
	
//	private static boolean isToyVibrating(GameCharacter character) {
//		if(!isToyInVagina(character)) {
//			return false;
//		}
//		return getToyInVagina(character).isVibrator();
//	}
	
	public static final SexAction TEASE_TOY_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return isToyInVagina(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "Игрушечная дразнилка (киска)";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.cockHead] своего [npc.cock] по [npc2.her] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.pussy+], [npc.name] медленно [npc.verb(start)], дразня [npc.cockTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовая в любой момент проникнуть в [npc2.herHim].",
							"С мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.pussy+], прежде чем начать осторожно водить [npc.cockTip] вверх и вниз между [npc2.her] [npc2.labia+].",
							"Мягко скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Втирая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] немного назад, прежде чем начать скользить [npc.cockTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый начать трахать [npc2.herHim] в любой момент.",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] вверх к [npc2.namePos] [npc2.pussy+], прежде чем начать грубо [npc2.verb(grind)] [npc.cockTip] вверх и вниз между [npc2.her] [npc2.labia+].",
							"Грубо втирая [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] скользя [npc.cockTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый проникнуть в [npc2.herHim] в любой момент.",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] вверх к [npc2.namePos] [npc2.pussy+], прежде чем начать скользить [npc.cockTip] вверх и вниз между [npc2.her] [npc2.labia+].",
							"Водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.pussy+], [npc.name] с нетерпением [npc.verb(start)] водит [npc.cockTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовая пронзить [npc2.herHim] в любой момент.",
							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.pussy+], прежде чем начать жадно водить [npc.cockTip] вверх и вниз между [npc2.her] [npc2.labia+].",
							"Нетерпеливо водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.pussy+] стимулируется, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко вытолкнуть [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.pussy+], и в ответ нежно [npc2.verb(push)] [npc2.her] [npc2.hips+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.pussy+] стимулируется, и [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.labia+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно толкнуть [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.pussy+], и в ответ отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] с силой вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] чувствует, как [npc2.her] [npc2.pussy+] стимулируются, и [npc2.she] мощно [npc2.verb(thrust)] [npc2.her] [npc2.labia+] прямо в [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо вонзить [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] от восторга, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.pussy+], и в ответ яростно [npc2.verb(buck)] [npc2.her] [npc2.hips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] с силой вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] чувствует, как [npc2.her] [npc2.pussy+] стимулируются, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.labia+] прямо в [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] [npc2.moan], прежде чем вонзить [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] от восторга, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.pussy+], и в ответ [npc2.verb(buck)] [npc2.her] [npc2.hips+]."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] девственность.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] может вот-вот потерять [npc2.her] девственность.",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] девственности перед [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Не надо! Пожалуйста! Я... я девственница! Вы не можете этого сделать!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянно [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Я ведь еще девственница!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу лишаться девственности!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, когда [npc2.she] [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.cock], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
							}
						}
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FORCE_TOY_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить членом";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.cockTip] [npc2.her] [npc2.cock] по твоей [npc.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.pussy+]. Медленно толкая [npc2.cockTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",
							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его до [npc.her] [npc.pussy+], после чего начинает нежно водить [npc2.cockTip] вверх и вниз между [npc.her] [npc.labia+].",
							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.pussy+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем резко дернуть его до [npc.her] [npc.pussy+]. Грубо толкая [npc2.cockTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name], предвещая проникновение в любой момент.",
							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и резко дергает его до [npc.her] [npc.pussy+], после чего начинает грубо [npc.verb(force)] [npc2.cockTip] вверх и вниз между [npc.her] [npc.labia+].",
							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(grind)] [npc2.cockTip+] по [npc.her] [npc.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.pussy+]. Погоня за [npc2.cockTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",
							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.pussy+], прежде чем начать скользить [npc2.cockTip] вверх и вниз между [npc.her] [npc.labia+].",
							"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(slide)] [npc2.cockTip+] поверх [npc.her] [npc.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.pussy+]. Жадно толкая [npc2.cockTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",
							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.pussy+], прежде чем начать жадно скользить [npc2.cockTip] вверх и вниз между [npc.her] [npc.labia+].",
							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] с нетерпением [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] сочитcя между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем нежно скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко скользит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] брызжет между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой втирать [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулирует, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом водит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно водить [npc2.her] [npc2.cock] туда-сюда по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] стимулирует [npc2.her] [npc2.cock], и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно водит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] водит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем водить [npc2.her] [npc2.cock] туда-сюда по [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] от стимуляции, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.pussy+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)], заставляя [npc.name] отпустить [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль киски";
		}

		@Override
		public String getActionDescription() {
			return "Сжимай свою мышцустую киску вокруг [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getVaginaOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)] на сжатии дополнительных внутренних мышц внутри [npc.her] [npc.pussy] вокруг [npc2.namePos] [npc2.cock+].",
					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] извлекает [npc.a_moan+], пока [npc.she] [npc.verb(focus)] на управлении дополнительными мышцами, выстилающими внутренности [npc.her] [npc.pussy]. Сжимая и обхватывая их вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim], вырывается непроизвольный крик удовольствия."
						:""),
					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя на размеренный ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.pussy+] вокруг [npc2.namePos] [npc2.cock+].",
					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.pussy], сжимая их и массируя [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
	
	public static final SexAction TOY_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнуть [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.cock+] в [npc2.namePos] [npc2.pussy+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и медленным, уверенным давлением [npc.she] осторожно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], после чего плавно выдергивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.pussy+].",
	
								" С тихим [npc2.moan], [npc2.name] [npc2.verb(start)] мягко выгибается, [npc2.her] [npc2.hips], погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] издает [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего резко выдергивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно вбивает [npc2.her] [npc2.hips], грубо заставляя [npc.name] погружаться [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего он жадно выгибает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.pussy+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно выгибает [npc2.her] [npc2.hips], отчаянно помогая вогнать [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего он выгибает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.pussy+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] выгибает [npc2.her] [npc2.hips], помогая вогнать [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" При [npc2.a_sob+], [npc2.name] [npc2.verb(try)] тщетно пытается отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока незваный [npc.namePos] [npc.cock] глубоко вжимается в [npc2.her] [npc2.pussy+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил(а) их из [npc2.her] [npc2.pussy+].",
							" [npc2.A_sob+] с силой вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name]; слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], умоляя [npc.herHim] вытащить его из [npc2.her] [npc2.pussy+].",
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.pussy+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], заставляя [npc.namePos] [npc.cock+] глубоко погрузиться в [npc2.her] [npc2.pussy+].",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, резко выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать её, пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ медленно [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)], мягко умоляя [npc.name] продолжать трахать [npc2.her] [npc2.pussy+].",
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь под [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать её, пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] чертовски [npc2.herHim].",
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать трахать [npc2.herHim].",
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.herHim], пока [npc2.her] движения заставляют [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+]."));
				default:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] с жадностью [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+].",
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
							" [npc2.Moaning] в восторге, [npc2.name] с жадностью [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, умоляя [npc.name] продолжать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+]."));
			}
		}
		return "";
	}
	
	public static final SexAction TOY_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный трах";
		}

		@Override
		public String getActionDescription() {
			return "Плавно вводи свой [npc.cock] в [npc2.namePos] и выводи его из [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] покачивает [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",
					"Медленно втискивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.pussy+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",
					"Скользя [npc.her] [npc.cock+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнуть [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать входить своим [npc.cock+] в [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
					"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы с азартом качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый трах";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.pussy+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] грубо швыряя [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.name].",
					"Яростно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] грубо [npc.verb(start)] швыряет [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",
					"Сильно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы с силой вколачивать [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));
		
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнуть [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] качая [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
					"Втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный трах";
		}

		@Override
		public String getActionDescription() {
			return "Жадно качай своим [npc.cock+] туда-сюда в [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",
					"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",
					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы с азартом качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляйся траху";
		}

		@Override
		public String getActionDescription() {
			return "Попытайся вытащить свой [npc.cock] из [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] мягко сжимает его, прежде чем нежно вогнать его обратно в [npc2.her] [npc2.pussy+].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем мягко вогнать обратно внутрь [npc2.her] [npc2.pussy+].",
							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.pussy+] вниз на [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] его крепко, прежде чем агрессивно втиснуть его обратно в [npc2.her] [npc2.pussy+].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо втиснуть обратно в [npc2.her] [npc2.pussy+].",
							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.pussy+] вниз на [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] его крепко, прежде чем с нетерпением втиснуть его обратно в [npc2.her] [npc2.pussy+].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с нетерпением втиснуть обратно в [npc2.her] [npc2.pussy+].",
							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.pussy+] на [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TOY_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Хватит трахаться";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.pussy+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдернув [npc.her] [npc.cock+] из [npc2.namePos] [npc2.pussy+], [npc.name] властно [npc.verb(slide)] [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",
							"В последний раз [npc.name] [npc.verb(yank)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.pussy+], глубоко вклинившись в [npc2.name], тем самым завершая этот грубый трах."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] [npc.cockTip] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",
							"В последний раз глубоко вжимаясь в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.pussy+], завершая трах."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+], пока [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.pussy], а [npc2.she] [npc2.verb(continue)], рыдая и протестуя, пока [npc2.she] [npc2.verb(carry)], слабо сопротивляясь [npc.herHim].",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.pussy+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.pussy+], жаждая еще большего внимания со стороны [npc.her].",
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction USING_TOY_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Трахаться";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.cock+] в свою [npc.pussy+] и получи трах.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его до [npc.her] [npc.labia+], издав легкий [npc.moan], прежде чем мягко подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.pussy+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.pussy+], прежде чем медленно оттолкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его вверх до [npc.her] [npc.labia+], издавая [npc.a_moan+], а затем с силой вбивает [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] [npc.pussy+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.pussy+], а затем жадно вбивает [npc.her] [npc.hips] обратно, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его вверх до [npc.her] [npc.labia+], издавая [npc.a_moan+], а затем резко вскидывая [npc.her] [npc.hips] и заставляя [npc2.herHim] проникать в [npc.her] [npc.pussy+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.pussy+], а затем толкая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] с жадностью [npc.verb(guide)] его вверх до [npc.her] [npc.labia+], издавая [npc.a_moan+], а затем отчаянно вскидывая [npc.her] [npc.hips] и заставляя [npc2.herHim] проникать в [npc.her] [npc.pussy+].",
								"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.pussy+], он жадно вгоняет [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] медленно [npc.verb(move)] [npc.her] [npc.hips], пока [npc2.namePos] [npc2.cock+] не прижимается к [npc.her] [npc.labia+], а затем с легким [npc.moan], [npc.she] плавно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.pussy+].",
								"Прижимая [npc.her] [npc.pussy+] к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] её между [npc.her] [npc.labia+], после чего медленно отступая назад и издавая тихий [npc.moan], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] с силой [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не прижимается к [npc.her] [npc.labia+], а затем с [npc.a_moan+], [npc.she] яростно [npc.verb(slam)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.pussy+].",
								"Грубо прижимая [npc.her] [npc.pussy+] к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] её между [npc.her] [npc.labia+], после чего властно отступая назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не будет прижат до [npc.her] [npc.labia+], а затем с [npc.a_moan+], [npc.she] [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.pussy+].",
								"Прижимая [npc.her] [npc.pussy+] к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] её между [npc.her] [npc.labia+], после чего отступая назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] жадно [npc.verb(push)] [npc.her] [npc.hips] назад, пока [npc2.namePos] [npc2.cock+] не будет прижат до [npc.her] [npc.labia+], а затем с [npc.a_moan+], [npc.she] алчно [npc.verb(buck)] назад и [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.her] [npc.pussy+].",
								"Отчаянно прижимая [npc.her] [npc.pussy+] к [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] её между [npc.her] [npc.labia+], после чего жадно отступая назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)], чтобы трахать [npc.namePos] [npc.pussy+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.pussy+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.namePos] [npc.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.pussy+], пока [npc2.she] энергично [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.cock] вперед и [npc2.verb(start)] беспощадно трахает [npc.her] [npc.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] беспощадно [npc2.verb(start)] трахает [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.pussy+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.cock+] из [npc.her] [npc.pussy+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] на [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.pussy], [npc2.name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться на свободу.",
							" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.pussy+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" Легкий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно скользит [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] яростно вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] от восторга, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в [npc.namePos] [npc.pussy+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+]."));
				default:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.pussy+].",
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в [npc.namePos] [npc.pussy+]."));
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TOY_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно трахаемый";
		}

		@Override
		public String getActionDescription() {
			return "Нежно позволь [npc2.namePos] [npc2.cock+] трахать твой [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу мягкая [npc.moan], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] подбрасывая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.pussy+].",
					"Медленно вращая [npc.her] [npc.hips], мягкая [npc.moan] вытекает из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TOY_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнутый";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свой [npc.pussy+] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С нетерпеливым толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] подбрасывает [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.pussy+].",
					"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TOY_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо трахнуть";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахнуть твой [npc.pussy+] о [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С яростным ударом [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
					"При [npc.a_moan+], [npc.name] грубо [npc.verb(start)] подбрасывая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.pussy+].",
					"Сильно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вбивают [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TOY_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахнутый";
		}

		@Override
		public String getActionDescription() {
			return "Трахни твой [npc.pussy+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу, [npc.a_moan+], чтобы [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
					"С [npc.a_moan+], [npc.name] [npc.verb(start)] дергается, вбивая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] всё глубже в [npc.her] [npc.pussy+].",
					"При вращении [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TOY_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно оттраханная";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свой [npc.pussy+] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С нетерпеливым толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] подбрасывает [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.pussy+].",
					"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться траху";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.pussy+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.pussy+].",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.pussy] подальше от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.pussy+].",
							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.pussy+].",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.pussy] подальше от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.pussy+].",
							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] неистово врывается глубоко в [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] наполниться в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.pussy+].",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.pussy] подальше от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] неистово молотит туда-сюда в [npc.her] [npc.pussy+].",
							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Перестать трахаться";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.cock] из твоей [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.cock] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно вытягивая [npc2.namePos] [npc2.cock] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.cock] из [npc.her] [npc.pussy+]."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы прекратить трахать [npc.her] [npc.pussy+].",
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.pussy+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
