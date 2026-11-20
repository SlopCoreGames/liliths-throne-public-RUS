package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.3.2
 * @author Innoxia
 */
public class TongueVagina {

	// -- Methods for multiple ongoing characters:
	
	static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingCunnilingus) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingCunnilingus, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
	}

	private static List<GameCharacter> getCharactersForParsing(GameCharacter characterReceivingCunnilingus) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingCunnilingus)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	private static String getOngoingNames(GameCharacter characterReceivingCunnilingus, GameCharacter... charactersToExclude) {
		List<String> names = new ArrayList<>();
		List<GameCharacter> exclusions = Arrays.asList(charactersToExclude);
		for(GameCharacter c : getOngoingCharacters(characterReceivingCunnilingus)) {
			if(!exclusions.contains(c)) {
				names.add(UtilText.parse(c, "[npc.name]"));
			}
		}
		return Util.stringsToStringList(names, false);
	}
	
	public static GameCharacter getPrimaryCunnilingusPerformer(GameCharacter characterReceivingCunnilingus) {
		if(Main.sex.getOngoingActionsMap(characterReceivingCunnilingus).get(SexAreaOrifice.VAGINA).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingCunnilingus).get(SexAreaOrifice.VAGINA).keySet().iterator().next();
	}
	
	// ---
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
				return "Фута-удовольствие";
			} else {
				return "Герма-удовольствие";
			}
		}
		@Override
		public String getActionDescription() {
			return "Удовольствие как для [npc2.namePos] [npc2.cock+], так и для [npc2.pussy+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
					&& Main.sex.isPenetrationTypeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] нежным поцелуем, после чего отстраняется и начинает медленно сосать и целовать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(deliver)] долгим, медленным облизыванием по всей длине [npc2.her] [npc2.pussy+], после чего отстраняется и начинает целовать и сосать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Нежно целуя [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] назад, поднося [npc.her] [npc.lips+] к [npc2.cockHead] [npc2.her] [npc2.cock+], прежде чем взять [npc2.herHim] в [npc.her] рот."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] влажным поцелуем, после чего отстраняется и начинает жадно сосать и целовать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(deliver)] долгим, влажным облизыванием по всей длине [npc2.her] [npc2.pussy+], после чего отстраняется и начинает жадно целовать и сосать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Осыпая [npc2.namePos] [npc2.pussy+] влажными поцелуями, [npc.name] [npc.verb(pull)] назад, поднося [npc.her] [npc.lips+] к самому [npc2.cockHead] [npc2.her] [npc2.cock+], а затем с жадностью заглатывая [npc2.herHim] в [npc.her] рот."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] грубым поцелуем, прежде чем отстраниться и начать властно сосать и целовать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(deliver)] долгим, грубым языком вверх по всей длине [npc2.her] [npc2.pussy+], после чего отстраняется и начинает властно целовать и сосать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Осыпая [npc2.namePos] [npc2.pussy+] грубыми поцелуями, [npc.name] [npc.verb(pull)] назад, поднося [npc.her] [npc.lips+] к самому [npc2.cockHead] [npc2.her] [npc2.cock+], а затем властно заглатывая [npc2.herHim] в [npc.her] рот."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] влажным поцелуем, после чего отстраняется и начинает жадно сосать и целовать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(deliver)] долгим, влажным облизыванием по всей длине [npc2.her] [npc2.pussy+], после чего отстраняется и начинает жадно целовать и сосать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Осыпая [npc2.namePos] [npc2.pussy+] влажными поцелуями, [npc.name] [npc.verb(pull)] назад, поднося [npc.her] [npc.lips+] к самому [npc2.cockHead] [npc2.her] [npc2.cock+], а затем с жадностью заглатывая [npc2.herHim] в [npc.her] рот."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+], прежде чем отстраниться и начать сосать и целовать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(deliver)] длинный лизь по всей длине [npc2.her] [npc2.pussy+], прежде чем отстраниться и начать целовать и сосать [npc2.cockHead] [npc2.her] [npc2.cock+].",

							"Даря поцелуй [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(pull)] назад, поднося [npc.her] [npc.lips+] к [npc2.cockHead] [npc2.her] [npc2.cock+], прежде чем взять [npc2.herHim] в [npc.her] рот."));
					break;
				default:
					break;
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], осторожно проталкивая [npc2.her] [npc2.cock] в [npc.namePos] рот, прежде чем [npc.she] [npc.verb(decide)] вновь переключить внимание [npc.her] на [npc2.her] [npc2.pussy+].",
	
								" Мягко втискивая [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем [npc.she] [npc.verb(decide)] снова спуститься, чтобы сосредоточиться на [npc2.her] [npc2.pussy+].",
	
								" Softly [npc2.moaning], [npc2.name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments, before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back down to [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], жадно заталкивая [npc2.her] [npc2.cock] в [npc.namePos] рот, прежде чем [npc.she] [npc.verb(decide)], чтобы вновь переключить [npc.her] внимание на [npc2.her] [npc2.pussy+].",
	
								" Жадно втискивая [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем [npc.she] [npc.verb(decide)], чтобы снова сосредоточиться на [npc2.her] [npc2.pussy+].",
	
								" [npc2.Moaning+], [npc2.name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)], чтобы вернуть [npc.her] оральное внимание к [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], грубо вбивая [npc2.her] [npc2.cock] в [npc.namePos] рот, прежде чем [npc.she] [npc.verb(decide)], чтобы вновь переключить [npc.her] внимание на [npc2.her] [npc2.pussy+].",
	
								" Грубо вбивая [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем [npc.she] [npc.verb(decide)], чтобы снова сосредоточиться на [npc2.her] [npc2.pussy+].",
	
								" [npc2.Moaning+], [npc2.name] грубо [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)], чтобы вернуть [npc.her] оральное внимание к [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], жадно заталкивая [npc2.her] [npc2.cock] в [npc.namePos] рот, прежде чем [npc.she] [npc.verb(decide)], чтобы вновь переключить [npc.her] внимание на [npc2.her] [npc2.pussy+].",
	
								" Жадно втискивая [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем [npc.she] [npc.verb(decide)], чтобы снова сосредоточиться на [npc2.her] [npc2.pussy+].",
	
								" [npc2.Moaning+], [npc2.name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)], чтобы вернуть [npc.her] оральное внимание к [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], заталкивая [npc2.her] [npc2.cock] в [npc.namePos] рот, прежде чем [npc.she] [npc.verb(decide)], чтобы вновь сместить внимание [npc.her] на [npc2.her] [npc2.pussy+].",
	
								" Вгоняя [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем [npc.she] [npc.verb(decide)], чтобы снова сосредоточиться на [npc2.her] [npc2.pussy+].",
	
								" [npc2.Moaning+], [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)], чтобы вновь переключить оральное внимание [npc.her] на [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Sobbing], извиваясь от дискомфорта, [npc2.name] отчаянно [npc2.verb(try)], пытаясь отстраниться от [npc.name] и умоляя оставить её в покое, пока [npc.she] [npc.verb(shift)] внимание [npc.her] снова на [npc2.her] [npc2.pussy+].",
	
								" При [npc2.a_sob+], [npc2.name] [npc2.verb(try)], пытаясь оттолкнуть [npc.name] и извиваясь от дискомфорта, пока [npc.she] [npc.verb(move)], вновь заставляя [npc.her] сосредоточиться на [npc2.her] [npc2.pussy+].",
	
								" Со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc.name] [npc.verb(move)], вновь сосредотачиваясь на [npc2.her] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Проведя несколько мгновений, посасывая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(shift)] [npc.her] внимание снова на [npc2.her] [npc2.pussy+].",
						" Насладившись вкусом [npc2.namePos] [npc2.cock+] в течение нескольких мгновений, [npc.name] [npc.verb(move)], вновь сосредоточив [npc.her] внимание на [npc2.her] [npc2.pussy+]."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Тянуть за хвостики";
			} else {
				return "Тянуть за косички";
			}
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Схватить по одному из [npc2.namePos] хвостику в каждой [npc.hand] и потянуть [npc2.her] [npc2.face] вперед, прямо в твои [npc.pussy+].";
			} else {
				return "Схватить по одной из [npc2.namePos] косичек в каждой [npc.hand] и потянуть [npc2.her] [npc2.face] вперед, прямо в твои [npc.pussy+].";
			}
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HAIR)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS || Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}
		@Override
		public String getDescription() {
			
			String style = Main.sex.getCharacterTargetedForSexAction(this).getHairStyle().getName(Main.sex.getCharacterTargetedForSexAction(this));
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив одну из [npc2.namePos] "+style+" в каждой [npc.hand], [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] вдавливать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протягивая руку вниз, чтобы нежно, но крепко обхватить каждый из [npc2.namePos] "+style+", [npc.name] медленно [npc.verb(pull)] [npc2.her] в область паха [npc2.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом на [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] "+style+", прежде чем мягко потянуть [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо схватив [npc2.namePos] "+style+" в каждой [npc.hand], [npc.name] резко [npc.verb(jerk)] [npc2.her] вперед, заставляя [npc2.herHim] вдавливать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Потянувшись вниз, чтобы схватить [npc2.namePos] "+style+" сжимая в кулаках, [npc.name] безжалостно [npc.verb(yank)] [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом с [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] "+style+", прежде чем яростно дернуть [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив одну из [npc2.namePos] "+style+" в каждой [npc.hand], [npc.name] уверенно [npc.verb(pull)] [npc2.her] голову вперед, заставляя [npc2.herHim] вталкивать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Потянувшись вниз, чтобы крепко обхватить каждый из [npc2.namePos] "+style+", [npc.name] размеренно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом с [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] "+style+", прежде чем плавно потянуть [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прижимая [npc2.her] [npc2.moans+] к [npc.namePos] паху, [npc2.name] нежно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)], чтобы глотнуть воздуха, пока [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.hair(true)].",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы слизывать и вылизывать [npc.namePos] [npc.pussy+], послушно поедая [npc.herHim], пока [npc.she] в конце концов не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.hair(true)]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Громоподобные вибрации [npc2.namePos], переходящие в угрожающее рычание, хоть и приносят дополнительное удовольствие, тем не менее заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и позволить [npc2.herHim] отстраниться от [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, она наконец может лишь рыдать и жадно хватать ртом воздух, пока [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.hair(true)].",
								" Вибрации [npc2.namePos], заглушающие стоны и рыдания, лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но, спустя несколько секунд ударов и толчков в [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.hair(true)] освобождается."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прижимая [npc2.her] [npc2.moans+] к [npc.namePos] паху, [npc2.name] жадно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)] глотать воздух, когда [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.hair(true)].",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы слизывать и вылизывать [npc.namePos] [npc.pussy+], послушно поедая [npc.herHim], пока [npc.she] в конце концов не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.hair(true)]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction EAR_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Потянуть за ухо";
		}
		@Override
		public String getActionDescription() {
			return "Схватив по одной [npc2.ears+] [npc2.namePos] в каждой [npc.hand], [npc2.face] тянет [npc2.her] вперед, в твои [npc.pussy+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
							&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HEAD)
							&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex());
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Ухватившись за одну из [npc2.namePos] [npc2.ears+] в каждой [npc.hand], [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] втискивать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руку вниз, чтобы мягко, но уверенно обхватить каждую из [npc2.namePos] [npc2.ears+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в пах [npc2.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом на [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватить [npc2.namePos] [npc2.ears+], прежде чем мягко потянуть [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо сжав [npc2.namePos] [npc2.ears+] в каждой [npc.hand], [npc.name] яростно [npc.verb(jerk)] голову [npc2.her] вперед, заставляя [npc2.herHim] втискивать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руки вниз и сжав [npc2.namePos] [npc2.ears+] в каждом кулаке, [npc.name] безжалостно [npc.verb(yank)] [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом у [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.ears+], после чего яростно дергает [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив по одной [npc2.namePos] [npc2.ears+] в каждой [npc.hand], [npc.name] уверенно [npc.verb(pull)] [npc2.her] голову вперед, заставляя [npc2.herHim] вжимать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руки вниз, чтобы крепко сжать каждую из [npc2.namePos] [npc2.ears+], [npc.name] размеренно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом у [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] [npc2.ears+], после чего плавно потянув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прижимая [npc2.her] [npc2.moans+] к [npc.namePos] паху, [npc2.name] нежно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)], жадно хватая ртом воздух, пока [npc.name] на мгновение не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.ears].",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы слизывать и облизывать [npc.namePos] [npc.pussy+], послушно поедая [npc.herHim], пока [npc.she] в конце концов не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.ears]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносящие дополнительное удовольствие, тем не менее заставляют [npc.name] поспешно разжать [npc2.her] [npc2.ears] и позволить [npc2.herHim] отстраниться от [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сжимая [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец закричать и судорожно хватать ртом воздух, когда [npc.name] на мгновение не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.ears].",
								" Вибрации [npc2.namePos], заглушающие крики и рыдания, лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но, спустя несколько секунд попыток пробиться сквозь [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.ears] освобождается."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Заглушая [npc2.her] [npc2.moans+] в [npc.namePos] паху, [npc2.name] жадно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)] глотка воздуха, пока [npc.name] на мгновение не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.ears].",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы вылизывать и слизывать [npc.namePos] [npc.pussy+], послушно поедая [npc.herHim], пока [npc.she] в конце концов не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.ears]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.horns]";
		}
		@Override
		public String getActionDescription() {
			return "Схвати [npc2.namePos] [npc2.horns+] в каждой [npc.hand] и потяни [npc2.her] [npc2.face] вперед, в свои [npc.pussy+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
							&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
							&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Ухватившись за одну из [npc2.namePos] [npc2.horns+] в каждой [npc.hand], [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] втиснуть [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руку вниз, чтобы нежно, но крепко обхватить каждую из [npc2.namePos] [npc2.horns+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом на [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] [npc2.horns+], а затем осторожно потянув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо схватив [npc2.namePos] [npc2.horns+] каждой [npc.hand], [npc.name] яростно [npc.verb(jerk)] [npc2.her] голову вперед, заставляя [npc2.herHim] втиснуть [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руку вниз, чтобы сжать [npc2.namePos] [npc2.horns+] в каждом кулаке, [npc.name] безжалостно [npc.verb(yank)] [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом на [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем резко дернув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Обхватив одну из [npc2.namePos] [npc2.horns+] в каждой [npc.hand], [npc.name] уверенно [npc.verb(pull)] [npc2.her] голову вперед, заставляя [npc2.herHim] втиснуть [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руку вниз, чтобы крепко обхватить каждую из [npc2.namePos] [npc2.horns+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом для [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем плавно потянув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прижимая [npc2.her] [npc2.moans+] к [npc.namePos] паху, [npc2.name] нежно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)], жадно глотая воздух, пока [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.horns].",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы лизать и слизывать [npc.namePos] [npc.pussy+], послушно вылизывая [npc.herHim], пока [npc.she] в конце концов не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.horns]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хотя и приносящие дополнительное удовольствие, все же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.horns] и позволить [npc2.herHim] отстраниться от [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сжимая [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец обрести возможность рыдать и жадно хватать ртом воздух, когда [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.horns].",
								" Вибрации [npc2.namePos] приглушенных криков и рыданий лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но после нескольких секунд попыток пробиться сквозь [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.horns] ослабевает."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Втискивая [npc2.her] [npc2.moans+] в [npc.namePos] пах, [npc2.name] жадно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)], ловя воздух, пока [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.horns].",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы слизывать и вылизывать [npc.namePos] [npc.pussy+], послушно поедая [npc.herHim], пока [npc.she] наконец не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.horns]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.antennae]";
		}
		@Override
		public String getActionDescription() {
			return "Захвати по одному [npc2.namePos] [npc2.antennae+] в каждой [npc.hand] и потяни [npc2.her] [npc2.face] вперед, прямо в свою [npc.pussy+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
							&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
							&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив по одной [npc2.namePos] [npc2.antennae+] в каждой [npc.hand], [npc.name] плавно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] втискивать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руку вниз, чтобы крепко, но нежно обхватить каждую [npc2.namePos] [npc2.antennae+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в пах [npc2.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом на [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватить [npc2.namePos] [npc2.antennae+], а затем мягко потянув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо сжав по одной [npc2.namePos] [npc2.antennae+] в каждой [npc.hand], [npc.name] яростно [npc.verb(jerk)] голову [npc2.her] вперед, заставляя [npc2.herHim] вбивать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Протянув руку вниз и сжав [npc2.namePos] [npc2.antennae+] в каждом кулаке, [npc.name] безжалостно [npc.verb(yank)] голову [npc2.her] в пах [npc2.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом на [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], а затем резко дернув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив по одной [npc2.namePos] [npc2.antennae+] в каждой [npc.hand], [npc.name] решительно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] втискивать [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy].",
							"Потянувшись вниз, чтобы крепко обхватить каждую из [npc2.namePos] [npc2.antennae+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в пах [npc2.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заняться куннилингусом у [npc.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], а затем плавно потянув [npc2.herHim] вперед, втирая [npc.her] [npc.labia+] в [npc2.her] [npc2.mouth]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Заглушая [npc2.her] [npc2.moans+] в паху [npc.namePos], [npc2.name] нежно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)] глотка воздуха, пока [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.antennae].",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы слизывать и лизать [npc.namePos] [npc.pussy+], послушно вылизывая [npc.herHim], пока [npc.she] в конце концов не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.antennae]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации [npc2.namePos], угрожающе рычащие и приносящие дополнительное удовольствие, тем не менее заставляют [npc.name] поспешно разжать [npc2.her] [npc2.antennae], позволяя [npc2.herHim] отстраниться от [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сжав [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец зарыдать и жадно хватать ртом воздух, когда [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.antennae].",
								" Вибрации приглушенных криков и рыданий [npc2.namePos] лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но после нескольких секунд ударов и толчков в [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.antennae] освобождается."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прижимая [npc2.her] [npc2.moans+] к паху [npc.namePos], [npc2.name] жадно [npc.verb(lick)] и [npc.verb(kiss)] [npc.her] [npc.pussy+], прежде чем в конце концов отстраниться и [npc2.verb(gasp)] глотать воздух, когда [npc.name] на мгновение [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.antennae].",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], слизывая и облизывая [npc.namePos] [npc.pussy+], послушно поедая [npc.herHim], пока [npc.she] наконец не [npc.verb(release)] [npc.her] хватку на [npc2.her] [npc2.antennae]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Начать куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Просунуть свой [npc.tongue] в [npc2.namePos] [npc2.pussy+] и начать куннилингус.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] [npc2.labia+], прежде чем медленно, но уверенно просунуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Осыпая [npc2.namePos] [npc2.labia+] серией мягких поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] долгим влажным языком, прежде чем осторожно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] [npc2.labia+], прежде чем отчаянно просунуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Осыпая [npc2.namePos] [npc2.labia+] серией страстных поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] голодным языком, прежде чем жадно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.her] [npc.lips+] в [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] серию властных поцелуев в [npc2.her] [npc2.labia+], прежде чем жадно просунуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Осыпая [npc2.namePos] [npc2.labia+] серией властных поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] грубым языком, прежде чем жадно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] [npc2.labia+], прежде чем отчаянно просунуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Осыпая [npc2.namePos] [npc2.labia+] серией страстных поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] голодным языком, прежде чем жадно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(plant)] серию поцелуев в [npc2.her] [npc2.labia+], прежде чем скользнуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Осыпая [npc2.namePos] [npc2.labia+] серией поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.pussy+] влажным языком, прежде чем протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ мягкий [npc2.moan], нежно выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливался.",
	
								" Нежно выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжать."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, жадно толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], умоляя [npc.herHim] не останавливаться.",
	
								" Отчаянно толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное ласкание, [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, грубо толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(order)], требуя от [npc.herHim] продолжать.",
	
								" Грубо толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face] в жадном ответе на [npc.her] оральное ласкание, [npc2.name] громко [npc2.moanVerb], приказывая [npc.herHim] продолжать."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, жадно толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], умоляя [npc.herHim] не останавливаться.",
	
								" Отчаянно толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное ласкание, [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжить."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, толкая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], умоляя [npc.herHim] не останавливаться.",
	
								" [npc2.her] [npc2.hips] выгибается навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, [npc2.name] [npc2.moanVerb] вслух, умоляя [npc.herHim] Продолжить."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], пытаясь вырваться из-под нежелательного орального внимания [npc.namePos], [npc2.sobbing] и извиваясь, пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
	
								" [npc2.A_sob+] вырывается изо рта [npc2.namePos] и, борясь с [npc.name], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] забрала [npc.her] [npc.tongue] от [npc2.her] киски."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction CUNNILINGUS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return TongueVagina.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		private String getOngoingNames() {
			return TongueVagina.getOngoingNames(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			int size = getOngoingCharacters().size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();
			
			map.put("one character performing cunnilingus", size==1);
			map.put("only ongoing vagina-actions are oral", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).size());
			map.put("[npc.namePos] mouth to be exposed", Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "Присоединиться к куннилингусу";
		}
		@Override
		public String getActionDescription() {
			return "Помочь "+getOngoingNames()+" в оральном обслуживании [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" в оральном обслуживании [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(move)] вперед, рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и нежно поцеловать [npc2.her] [npc2.labia+] в бок."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" в оральном обслуживании [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(elbow)] [npc.her] в сторону, рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и грубо поцеловать [npc2.her] [npc2.labia+] в бок."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" в оральном обслуживании [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(move)] вперед, рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и быстро поцеловать [npc2.her] [npc2.labia+] в бок."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" орально обслуживая [npc2.namePos] [npc2.pussy+], [npc.name] с энтузиазмом [npc.verb(move)] подается вперед, "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+"прежде чем наклониться и одарить бок [npc2.her] [npc2.labia+] страстным, влажным поцелуем."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] в ответ нежный [npc2.moan], и после того как, "+getOngoingNames()+" удалось ненадолго немного отстраниться, [npc2.she] [npc2.verb(allow)] [npc.name], чтобы занять лучшее место, вылизывая и целуя [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] в ответ довольный [npc2.moan], и после того как, грубо приказав, "+getOngoingNames()+" немного отодвинуться, [npc2.she] [npc2.verb(force)] [npc.name], чтобы занять лучшее место, вылизывая и целуя [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan], и после того как, "+getOngoingNames()+" удалось ненадолго немного отстраниться, [npc2.she] [npc2.verb(allow)] [npc.name], чтобы занять лучшее место, вылизывая и целуя [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] встревоженный [npc2.moan] в ответ, который становится только громче и неистовее, когда [npc.name] [npc.verb(push)] "+getOngoingNames()+" в сторону, чтобы занять лучшее место, вылизывая и целуя [npc2.her] [npc2.pussy+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], и после того как "+getOngoingNames()+" временно немного отступить, [npc2.she] счастливо [npc2.verb(encourage)] [npc.name], чтобы занять ведущую позицию в облизывании и поцелуях [npc2.her] [npc2.pussy+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+] и, жадно выгибая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог продолжать обслуживать [npc2.her] [npc2.pussy].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(grind)] [npc2.her] [npc2.labia+] вниз на [npc.namePos] [npc.face], прежде чем настойчиво умолять [npc.herHim] вогнать [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев отстранить [npc2.her] [npc2.hips] от нежеланного [npc.namePos] [npc.tongue], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.she] [npc.verb(continue)] лизать и целовать [npc2.her] [npc2.pussy+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] [npc2.verb(try)], тщетно пытаясь отстраниться от [npc.namePos] [npc.tongue], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)], чтобы орально обслуживать [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.lips+].",
		
							" Тихий [npc2.moan] доносится из уст [npc2.namePos] [npc2.lips+], и, мягко подталкивая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал обслуживать [npc2.her] [npc2.pussy].",
		
							" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.labia+] вниз на [npc.namePos] [npc.face], прежде чем жадно умолять [npc.herHim] вогнать [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] вырывается из уст [npc2.namePos] [npc2.lips+], и, грубо впечатывая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать обслуживать [npc2.her] [npc2.pussy].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.labia+] вниз на [npc.namePos] [npc.face], а затем агрессивно приказывает [npc.herHim] вгонять [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(buck)] [npc2.her] [npc2.hips] назад, издавая [npc2.a_moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan] выскальзывает из между [npc2.namePos] [npc2.lips+], и, выгибая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог продолжать обслуживать [npc2.her] [npc2.pussy].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.labia+] вниз на [npc.namePos] [npc.face], а затем умоляет [npc.herHim] вгонять [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.pussy+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Нежно лизать [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+TongueVagina.getOngoingNames(target, performer)+", [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] на открытую часть [npc2.namePos] [npc2.labia+], прежде чем начать нежно целовать и лизать [npc2.her] [npc2.pussy+].",
						"С мягким [npc.moan], [npc.name] осторожно [npc.verb(lean)] рядом "+TongueVagina.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободной части [npc2.namePos] [npc2.labia+], пока [npc.she] [npc.verb(kiss)] и [npc.verb(nuzzle)] внутрь [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+TongueVagina.getOngoingNames(target, performer)
							+", [npc.name] нежно [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.labia+], которую сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко вонзая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+], прижимаясь к [npc2.her] [npc2.labia+] и издавая приглушенный [npc.moan] [npc.verb(let)].",
						"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], чтобы нежно поцеловать и облизать [npc2.namePos] [npc2.labia+], прежде чем снова податься вперед и медленно скользнуть [npc.her] [npc.tongue] в [npc2.her] [npc2.pussy+].",
						"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], медленно целуя и прижимаясь к [npc2.namePos] [npc2.labia+], прежде чем наклониться вперед и мягко вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать вбивать свой [npc.tongue] в [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+TongueVagina.getOngoingNames(target, performer)+", [npc.name] жадно [npc.verb(press)] [npc.her] [npc.lips+] об открытую часть [npc2.namePos] [npc2.labia+], прежде чем начать с нетерпением целовать и лизать [npc2.her] [npc2.pussy+].",
						"Вместе с [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(lean)] внутрь вместе с "+TongueVagina.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к открытой части [npc2.namePos] [npc2.labia+], пока [npc.she] жадно [npc.verb(kiss)] и [npc.verb(nuzzle)] внутрь, прижимаясь к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+TongueVagina.getOngoingNames(target, performer)
							+", [npc.name] жадно [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.labia+], которую только может найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С энтузиазмом вонзая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+], прижимаясь к [npc2.her] [npc2.labia+] и издавая приглушенный [npc.moan] [npc.verb(let)].",
						"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], чтобы жадно целовать и лизать [npc2.namePos] [npc2.labia+], прежде чем снова податься вперед и алчно засунуть [npc.her] [npc.tongue] в [npc2.her] [npc2.pussy+].",
						"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc2.namePos] [npc2.labia+], прежде чем наклониться вперед и с энтузиазмом вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Грубо вонзи свой язык в [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+TongueVagina.getOngoingNames(target, performer)+", [npc.name] грубо [npc.verb(press)] [npc.her] [npc.lips+] об открытую часть [npc2.namePos] [npc2.labia+], прежде чем начать яростно целовать и лизать [npc2.her] [npc2.pussy+].",
						"С помощью [npc.a_moan+], [npc.name] с силой [npc.verb(push)] внутрь вместе с "+TongueVagina.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободному участку [npc2.namePos] [npc2.labia+], пока [npc.she] грубо [npc.verb(kiss)] и [npc.verb(nuzzle)] в [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+TongueVagina.getOngoingNames(target, performer)
							+", [npc.name] грубо [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos], которую сможет найти [npc2.labia+] [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо втискивая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.labia+] и [npc.verb(let)] приглушенный [npc.moan].",
						"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], чтобы грубо поцеловать и облизать [npc2.namePos] [npc2.labia+], прежде чем податься вперед и с силой вонзить [npc.her] [npc.tongue] в [npc2.her] [npc2.pussy+] еще раз.",
						"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], страстно целуя и облизывая [npc2.namePos] [npc2.labia+], прежде чем наклониться вперед и грубо вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться куннилингусу";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tongue] из [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)], мягко прижимая [npc2.her] [npc2.pussy+] к [npc.her] [npc.lips+] и удерживая [npc.herHim] на месте, пока [npc2.she] яростно [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"Из-за [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.face] обратно в [npc2.her] [npc2.pussy+], нежно растирая [npc2.herself] о [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и из-за [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь отстранить [npc.her] рот от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя протесты [npc.her], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.pussy+] о [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.face] назад, но [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.pussy+] вниз к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] неистово [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"[npc.a_sob+], [npc.name] [npc.verb(try)] отстраниться от [npc2.name], но [npc2.she] яростно [npc2.verb(force)] [npc.her] [npc.face] прямо обратно в [npc2.her] [npc2.pussy+], грубо втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В глазах [npc.namePos], [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)] отстранить [npc.her] рот от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] [npc2.pussy+] о [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)], жадно втискивая [npc2.her] [npc2.pussy+] вниз к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"[npc.a_sob+], [npc.name] [npc.verb(try)] отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.face] прямо обратно в [npc2.her] [npc2.pussy+], жадно втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь отстранить [npc.her] рот от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.pussy+] против [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать вбивать свой [npc.tongue] в [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+TongueVagina.getOngoingNames(target, performer)+", [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против открытого участка [npc2.namePos] [npc2.labia+], прежде чем начать целовать и лизать [npc2.her] [npc2.pussy+].",
						"С [npc.a_moan+], [npc.name] [npc.verb(lean)] вместе с "+TongueVagina.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободной части [npc2.namePos] [npc2.labia+], пока [npc.she] [npc.verb(kiss)] и [npc.verb(nuzzle)] внутрь [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+TongueVagina.getOngoingNames(target, performer)
							+", [npc.name] [npc.verb(kiss)] и [npc.verb(lick)] любой открытый участок [npc2.namePos] [npc2.labia+], который сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Вгоняя [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+], прижатые к [npc2.her] [npc2.labia+], и [npc.verb(let)] приглушенный [npc.moan].",
						"Отстраняя [npc.her] [npc.tongue+] от [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], чтобы поцеловать и лизать [npc2.namePos] [npc2.labia+], прежде чем снова податься вперед и скользнуть [npc.her] [npc.tongue] в [npc2.her] [npc2.pussy+].",
						"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], целуя и прижимаясь к [npc2.namePos] [npc2.labia+], прежде чем наклониться вперед и вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадный куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "С жадностью втирай свой [npc.tongue] в [npc2.namePos] [npc2.pussy+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(target);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+TongueVagina.getOngoingNames(target, performer)+", [npc.name] жадно [npc.verb(press)] [npc.her] [npc.lips+] об открытую часть [npc2.namePos] [npc2.labia+], прежде чем начать с нетерпением целовать и лизать [npc2.her] [npc2.pussy+].",
						"Вместе с [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(lean)] внутрь вместе с "+TongueVagina.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к открытой части [npc2.namePos] [npc2.labia+], пока [npc.she] жадно [npc.verb(kiss)] и [npc.verb(nuzzle)] внутрь, прижимаясь к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+TongueVagina.getOngoingNames(target, performer)
							+", [npc.name] жадно [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.labia+], которую только может найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С энтузиазмом вонзая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+], прижимаясь к [npc2.her] [npc2.labia+] и издавая приглушенный [npc.moan] [npc.verb(let)].",
						"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], чтобы жадно целовать и лизать [npc2.namePos] [npc2.labia+], прежде чем снова податься вперед и алчно засунуть [npc.her] [npc.tongue] в [npc2.her] [npc2.pussy+].",
						"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc2.namePos] [npc2.labia+], прежде чем наклониться вперед и с энтузиазмом вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Вытащить свой [npc.tongue] из [npc2.namePos] [npc2.pussy+] и прекратить куннилингус.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним грубым языкомком, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.namePos] [npc2.pussy+].",
	
							"Дав [npc2.namePos] [npc2.labia+] последний грубый поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.her] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним лизком, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.namePos] [npc2.pussy+].",
	
							"Дав [npc2.namePos] [npc2.labia+] последний влажный поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.her] [npc2.pussy+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)], пытаясь сопротивляться [npc.name], [npc2.sobbing] и ерзая от дискомфорта, когда [npc2.she] понимает, что [npc.name] еще не совсем закончил с [npc2.herHim].",
		
								" Осознав, что [npc.sheHasFull] еще не совсем закончил с [npc2.herHim], [npc2.name] [npc2.verb(continue)] изо всех сил борется и [npc2.sobbing], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], ожидая, когда [npc.name] отпустит [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] уводит [npc.her] [npc.tongue+] от [npc2.her] [npc2.pussy+].",
								
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], пока [npc.name] [npc.verb(stop)] доставляет удовольствие [npc2.her] [npc2.pussy+], выдавая [npc2.her] жажду еще большего [npc.namePos] внимания."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Принять куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать лизать [npc.namePos] [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(start)] медленно втирая [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] вжималась в [npc.her] [npc.labia+], [npc.Name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(start)] мягко прижимает [npc.her] [npc.pussy+] к [npc2.her] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово втирает [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] вжималась в [npc.her] [npc.labia+], [npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно прижимает [npc.her] [npc.pussy+] к [npc2.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо впечатывая [npc.her] [npc.labia+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с силой втирает [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулась в [npc.her] [npc.labia+], [npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо втирает [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово втирает [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] вжималась в [npc.her] [npc.labia+], [npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно прижимает [npc.her] [npc.pussy+] к [npc2.her] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] втирает [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулась в [npc.her] [npc.labia+], [npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] прижимает [npc.her] [npc.pussy+] к [npc2.her] [npc2.lips+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] нежно лижет и целует [npc.her] [npc.labia+].",
	
								" Мягко вытягивая [npc2.her] [npc2.tongue], чтобы сделать долгий, медленный лизь по [npc.labia+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.pussy+] чередой нежных поцелуев."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] с наслаждением лижет и целует [npc.her] [npc.labia+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести долгий влажный языком по [npc.labia+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.pussy+] страстными поцелуями."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с силой [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] грубо лижет и целует [npc.her] [npc.labia+].",
	
								" Жадно выталкивая [npc2.her] [npc2.tongue], чтобы провести грубый влажный языком по [npc.labia+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.pussy+] напористыми поцелуями."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] с наслаждением лижет и целует [npc.her] [npc.labia+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести долгий влажный языком по [npc.labia+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.pussy+] страстными поцелуями."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] облизывает и целует [npc.her] [npc.labia+].",
	
								" Высунув [npc2.her] [npc2.tongue], чтобы провести долгий влажный языком по [npc.labia+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает поцелуями [npc.namePos] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь вырваться, [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.pussy+] на [npc2.her] [npc2.lips+].",
	
								" [npc2.Sobbing] и беспорядочно дергаясь от дискомфорта, протесты [npc2.namePos] оказываются напрасными, так как [npc.name] [npc.verb(press)] [npc.her] [npc.labia+] на [npc2.her] [npc2.face]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return TongueVagina.getCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}
		
		private String getOngoingNames() {
			return TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			int size = TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();
			
			map.put("one character performing cunnilingus", size==1);
			map.put("only ongoing vagina-actions are oral", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).size());
			map.put("[npc2.namePos] mouth to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc2.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "Присоединиться к куннилингусу";
		}
		
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] присоединиться "+getOngoingNames()+" в ласках твоей киски.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" облизывая [npc.herHim], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" отстраниться и ненадолго отойти в сторону, прежде чем мягко потянуть лицо [npc2.namePos] вперед и заставить [npc2.herHim] прижать [npc2.her] [npc2.lips+] к [npc.her] [npc.labiaSize], покрытым слюной [npc.labia]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" облизывая [npc.herHim], [npc.name] [npc.verb(push)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
									+" назад и [npc.verb(order)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" ненадолго отойти в сторону, прежде чем грубо потянуть лицо [npc2.namePos] вперед и заставить [npc2.herHim] прижать [npc2.her] [npc2.lips+] к [npc.her] [npc.labiaSize], покрытым слюной [npc.labia]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" облизывая [npc.herHim], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" слегка отстраниться, прежде чем снова притянуть лицо [npc2.namePos] к себе, заставляя [npc2.herHim] прижимать [npc2.her] [npc2.lips+] к [npc.her] [npc.labiaSize], покрытым слюной [npc.labia]."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" облизывая [npc.herHim], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" слегка отстраниться, прежде чем жадно притянуть лицо [npc2.namePos] к себе, заставляя [npc2.herHim] прижимать [npc2.her] [npc2.lips+] к [npc.her] [npc.labiaSize], покрытым слюной [npc.labia]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" нежно вылизывая и целуя [npc.namePos] [npc.pussy+].",
								" С мягким, приглушенным [npc2.moan], [npc2.name] [npc2.verb(join)] "+getOngoingNames()+" плавно скользя [npc2.her] [npc2.tongue] вверх и вниз по [npc.namePos] [npc.pussy]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" и [npc2.verb(start)] грубо вылизывая и целуя [npc.namePos] [npc.pussy+].",
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(join)] "+getOngoingNames()+" грубо скользя [npc2.her] [npc2.tongue] вверх и вниз по [npc.namePos] [npc.pussy]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" и [npc2.verb(start)] облизывает и целует [npc.namePos] [npc.pussy+].",
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(join)] "+getOngoingNames()+" в скользящем движении [npc2.her] [npc2.tongue] вверх и вниз по [npc.namePos] [npc.pussy]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.sob], пока [npc2.she] лихорадочно [npc2.verb(try)], пытаясь оттянуть [npc2.her] голову от [npc.namePos] паха.",
								" С приглушенным [npc2.sob], [npc2.name] лихорадочно [npc2.verb(try)], пытаясь отстраниться от [npc.namePos] [npc.pussy+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" и [npc2.verb(start)] с энтузиазмом облизывает и целует [npc.namePos] [npc.pussy+].",
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(join)] "+getOngoingNames()+" в скользящем движении [npc2.her] [npc2.tongue] вверх и вниз по [npc.namePos] [npc.pussy]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], и, с нетерпением прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+].",
		
							" От восторга [npc2.Moaning], [npc2.name] жадно [npc2.verb(grind)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia], прежде чем с энтузиазмом вогнать [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттащить [npc2.her] [npc2.face] от [npc.namePos] [npc.labia], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.she] [npc.verb(continue)], заставляя [npc.her] [npc.labia+] прижаться к [npc2.her] [npc2.face+].",
		
							" В смятении [npc2.Sobbing], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от [npc.namePos] [npc.labia], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)], прижимая [npc.her] [npc.pussy+] к [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+], издавая мягкий, приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], и, мягко прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)], медленно проталкивая [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+].",
		
							" В восторге [npc2.Moaning], [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.lips+] к [npc.namePos] [npc.labia], прежде чем мягко проскользнуть [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], и, грубо втирая [npc2.her] [npc2.lips+] в [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] яростно вонзает [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+].",
		
							" Восторженно [npc2.Moaning], [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia], прежде чем агрессивно вонзить [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], и, прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.labia+], [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.pussy+].",
		
							" Восторженно [npc2.Moaning], [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.labia], прежде чем вбивать [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.pussy+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		@Override
		public String getActionTitle() {
			return "Мягко принимать куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Мягко прижать свой [npc.labia+] поверх [npc2.namePos] [npc2.lips], чтобы вжать [npc2.her] [npc2.tongue+] в свои [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" слегка отстранившись, [npc.name] мягко [npc.verb(pull)] [npc2.namePos] вперед, пока [npc2.her] [npc2.lips+] не прижмутся к [npc.her] [npc.labia+], после чего тихо [npc.moan] и начинает размеренно втирать [npc.her] [npc.pussy+] в [npc2.her] [npc2.face].",
							"Мягко наставляя "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] вести, [npc.name] медленно [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], тихо [npc.moan], пока [npc.she] [npc.verb(get)] [npc2.herHim], поедая [npc.herHim].",
							"После того как сказала "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], [npc.name] мягко [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], тихо [npc.moan], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы ласкать [npc.her] [npc.pussy+] языком."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко прижимая [npc.her] [npc.labia+] поверх [npc2.namePos] [npc2.face+], [npc.name] тихо [npc.verb(let)] мягкий [npc.moan], пока [npc.she] уверенно [npc.verb(plant)] [npc.her] [npc.pussy+] поверх [npc2.her] [npc2.lips+].",
						"С тихим [npc.moan], [npc.name] [npc.verb(press)] [npc.her] [npc.labia+] против [npc2.namePos] [npc2.face+], после чего мягко растирает [npc.her] [npc.pussy+] о [npc2.her] [npc2.lips+].",
						"Издавая тихий [npc.moan], [npc.name] нежно [npc.verb(grind)] [npc.her] [npc.pussy+] вниз, прижимаясь к [npc2.namePos] [npc2.lips]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		@Override
		public String getActionTitle() {
			return "Принять куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Жадно прижми свой [npc.labia+] к лицу [npc2.namePos], чтобы заставить [npc2.her] [npc2.tongue+] в твою [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" слегка отодвинуться, [npc.name] с нетерпением [npc.verb(pull)] голову [npc2.namePos] вперед, пока [npc2.her] [npc2.lips+] не прижмутся к [npc.her] [npc.labia+], после чего, издав [npc.a_moan+], она начинает неистово втирать [npc.her] [npc.pussy+] в [npc2.her] [npc2.face].",
							"Твердо инструктируя "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] вести, [npc.name] с жадностью [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы вылизать [npc.herHim].",
							"После того как сказала "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], [npc.name] с жадностью [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы сделать оральный сервис [npc.her] [npc.pussy+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Жадно прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] решительно [npc.verb(plant)] [npc.her] [npc.pussy+] вниз на [npc2.her] [npc2.lips+].",
						"С [npc.a_moan+], [npc.name] с нетерпением [npc.verb(press)] [npc.her] [npc.labia+] против [npc2.namePos] [npc2.face+], прежде чем жадно втирать [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",
						"Издавая [npc.a_moan+], [npc.name] с нетерпением [npc.verb(grind)] [npc.her] [npc.pussy+] вниз против [npc2.namePos] [npc2.lips]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
		@Override
		public String getActionTitle() {
			return "Грубо принимать куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Грубо втирать свой [npc.pussy+] в [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Приказывая "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" немного отодвинуться, [npc.name] грубо [npc.verb(pull)] [npc2.namePos] вперед, пока [npc2.her] [npc2.lips+] не прижмутся к [npc.her] [npc.labia+], после чего издавая [npc.a_moan+] и с силой втирая [npc.her] [npc.pussy+] в [npc2.her] [npc2.face].",
							"Твердо инструктируя "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" дать [npc2.name] взять инициативу, [npc.name] грубо [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(make)] [npc2.herHim], чтобы вылизать [npc.herHim].",
							"После того как сказала "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], [npc.name] грубо [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы оказать оральную услугу [npc.her] [npc.pussy+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] неистово [npc.verb(slam)] [npc.her] [npc.pussy+] поверх [npc2.her] [npc2.lips+].",
						"С [npc.a_moan+], [npc.name] грубо [npc.verb(slam)] [npc.her] [npc.labia+] против [npc2.namePos] [npc2.face+], прежде чем с силой прижать [npc.her] [npc.pussy+] к [npc2.her] [npc2.lips+].",
						"Издавая [npc.a_moan+], [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.pussy+] вниз против [npc2.namePos] [npc2.lips]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться куннилингусу";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.pussy+] от [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.labia] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tongue] глубоко в [npc.her] [npc.pussy+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] мягкий поцелуй в [npc.her] [npc.labia+], прежде чем осторожно скользнуть [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy+].",

							"С [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.labia+] от [npc2.namePos] [npc2.lips+], но [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] мягко втискивает [npc2.her] [npc2.tongue] в [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.labia] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] грубо [npc2.verb(slide)] [npc2.her] [npc2.tongue] глубоко в [npc.her] [npc.pussy+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.pussy+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] влажный поцелуй в [npc.her] [npc.labia+], прежде чем грубо вонзить [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy+].",

							"С [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)] оттащить [npc.her] [npc.labia+] от [npc2.namePos] [npc2.lips+], но [npc2.she] крепко удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] грубо вгоняя [npc2.her] [npc2.tongue] в [npc.her] [npc.pussy+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.labia] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue] глубоко в [npc.her] [npc.pussy+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] страстный поцелуй в [npc.her] [npc.labia+], прежде чем жадно задвинуть [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.pussy+].",

							"С [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.labia+] от [npc2.namePos] [npc2.lips+], но [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] с нетерпением вгоняя [npc2.her] [npc2.tongue] в [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Принять куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Прижать свой [npc.pussy+] к [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" немного отодвинуться, [npc.name] [npc.verb(pull)] [npc2.namePos] вперед, пока [npc2.her] [npc2.lips+] не прижмутся к [npc.her] [npc.labia+], после чего, издав [npc.a_moan+], [npc.her] [npc.pussy+] в [npc2.her] [npc2.face].",
							"Просьба "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] взять инициативу на себя, [npc.name] [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы вылизать [npc.herHim].",
							"После просьбы "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" чтобы освободить место для [npc2.name], [npc.name] [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы орально обслужить [npc.her] [npc.pussy+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Прижимая [npc.her] [npc.labia+] вниз поверх [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] уверенно [npc.verb(plant)] [npc.her] [npc.pussy+] на [npc2.her] [npc2.lips+].",
						"С помощью [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.labia+] против [npc2.namePos] [npc2.face+], прежде чем втирать [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.pussy+] вниз против [npc2.namePos] [npc2.lips]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно принимать куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Жадно прижимать свой [npc.pussy+] к [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = TongueVagina.getPrimaryCunnilingusPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(TongueVagina.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" слегка отодвинуться, [npc.name] с нетерпением [npc.verb(pull)] голову [npc2.namePos] вперед, пока [npc2.her] [npc2.lips+] не прижмутся к [npc.her] [npc.labia+], после чего, издав [npc.a_moan+], она начинает неистово втирать [npc.her] [npc.pussy+] в [npc2.her] [npc2.face].",
							"Твердо инструктируя "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] вести, [npc.name] с жадностью [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы вылизать [npc.herHim].",
							"После того как сказала "+TongueVagina.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], [npc.name] с жадностью [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы сделать оральный сервис [npc.her] [npc.pussy+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Жадно прижимая [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] решительно [npc.verb(plant)] [npc.her] [npc.pussy+] вниз на [npc2.her] [npc2.lips+].",
						"С [npc.a_moan+], [npc.name] с нетерпением [npc.verb(press)] [npc.her] [npc.labia+] против [npc2.namePos] [npc2.face+], прежде чем жадно втирать [npc.her] [npc.pussy+] в [npc2.her] [npc2.lips+].",
						"Издавая [npc.a_moan+], [npc.name] с нетерпением [npc.verb(grind)] [npc.her] [npc.pussy+] вниз против [npc2.namePos] [npc2.lips]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить принимать куннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tongue+] из твоей [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо отрывая голову [npc2.namePos] от [npc.her] [npc.pussy+], [npc.name] [npc.verb(order)] [npc2.herHim], чтобы прекратить куннилингус для [npc.herHim].",

							"Грубо втирая [npc.her] [npc.pussy+] в [npc2.namePos] [npc2.face] в последний раз, затем [npc.name] [npc.verb(pull)] [npc.her] [npc.hips] прочь, завершая куннилингус."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.namePos] убирает голову от [npc.her] [npc.pussy+], [npc.name] [npc.verb(tell)] [npc2.herHim], чтобы прекратить куннилингус для [npc.herHim].",

							"В последний раз [npc.her] [npc.pussy+] в [npc2.namePos] [npc2.face], [npc.name] затем [npc.verb(pull)] [npc.her] [npc.hips], завершая текущий куннилингус."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Пока слезы катились по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], так как [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc2.verb(continue)], сопротивляясь [npc.name] и умоляя [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание подарить [npc.namePos] еще больше [npc.pussy+] [npc2.her] орального внимания.",
	
								" Все еще жаждая добавки, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(move)] от [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
