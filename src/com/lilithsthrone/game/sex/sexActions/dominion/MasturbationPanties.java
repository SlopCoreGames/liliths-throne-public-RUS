package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class MasturbationPanties {
	
	public static final SexAction PLAYER_STROKE_VAGINA_PANTIES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES;
		}
		
		@Override
		public String getActionTitle() {
			return "Тереть член трусиками";
		}

		@Override
		public String getActionDescription() {
			return "Тереть трусики Lilaya о свою киску.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Ты прижимаешь мягкую ткань трусиков Lilaya к своим [pc.legs+], издавая глубокий вздох и водя ими вверх-вниз по своей обнаженной [pc.pussy].",
					"Прижимая трусики Lilaya к своим [pc.pussy+], ты закрываешь [pc.eyes] и представляешь, как киска твоей демонической [lilaya.relation(pc)] трется о мягкую ткань.",
					"Скользя трусиками Lilaya по своим [pc.pussy+], ты издаешь [pc.a_moan+], представляя, как твоя демоническая [lilaya.relation(pc)] носит их, работая в своей лаборатории.",
					"Ты жадно натягиваешь трусики Lilaya на свои нетерпеливые [pc.pussy], стоная и вздыхая, когда осторожно прижимаешь ткань к своим внешним половым губам.");
		}
		
		@Override
		public String applyEffectsString() {
			if(!LilayasRoom.lilayasPanties.isDirty() && Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return "<p style='text-align:center'>[style.italicsDirty(Трусики Lilaya быстро пачкаются, пока ты трешь их о свою мокрую киску.)]</p>";
				
			} else {
				return "";
			}
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_INCEST);
			} else {
				return null;
			}
		}
		
	};
	
	public static final SexAction PLAYER_STROKE_PENIS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES;
		}
		
		@Override
		public String getActionTitle() {
			return "Тереть член трусиками";
		}

		@Override
		public String getActionDescription() {
			return "Тереть трусики Lilaya о свой [pc.cock].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Обернув трусики Lilaya вокруг своего [pc.cock+], ты начинаешь совершать движения вверх-вниз по всей длине, [pc.moaning+], представляя, как киска твоей демонической [lilaya.relation(pc)] трется о мягкую ткань.",
					"Вы накрываете [pc.cockHead+] своего [pc.cock+] трусиками Lilaya, издавая [pc.a_moan+], когда начинаете ласкать себя через ткань.",
					"Обхватив свой [pc.cock+], вы оборачиваете трусики Lilaya вокруг члена, после чего закрываете [pc.eyes] и воображаете киску своей демонической [lilaya.relation(pc)], мастурбируя.",
					"Водя трусиками Lilaya вверх и вниз по своему [pc.cock+], вы издаете [pc.a_moan+], представляя, как ваша демоническая [lilaya.relation(pc)] носит их, усердно работая в своей лаборатории.");
		}

		@Override
		public String applyEffectsString() {
			if(!LilayasRoom.lilayasPanties.isDirty() && Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaPenetration.PENIS)) {
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return "<p style='text-align:center'>[style.italicsDirty(Трусики Lilaya быстро пачкаются, пока вы трете их о свой член.)]</p>";
				
			} else {
				return "";
			}
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_INCEST);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_STROKE_MOUND = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES
					&& !Main.game.getPlayer().hasPenis()
					&& !Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "Трение трусиками по лобку";
		}

		@Override
		public String getActionDescription() {
			return "Потрите трусики Lilaya о свой безполовой лобок.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Просунув руку между ног, вы трете трусики Lilaya о свой кукольный лобок, стоная и вздыхая и представляя, как киска вашей демонической [lilaya.relation(pc)] прижимается к мягкой ткани.",
					"Вы трете трусики Lilaya о чувствительный кукольный лобок между ног, вздыхая и поскуливая, представляя, как ваша демоническая [lilaya.relation(pc)] носит их, усердно работая в своей лаборатории.",
					"Прижимая трусики Lilaya к своему чувствительному безполовому лобку, вы закрываете [pc.eyes] и воображаете киску своей демонической [lilaya.relation(pc)], мастурбируя.",
					"Вы жадно натягиваете трусики Lilaya на свой кукольный лобок, стоная и вздыхая, когда осторожно прижимаете ткань к своему чувствительному [pc.skin].");
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_SNIFF_PANTIES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES;
		}

		@Override
		public String getActionTitle() {
			return "Нюхать трусики";
		}

		@Override
		public String getActionDescription() {
			return "Нюхать использованные трусики Lilaya.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Поднеся трусики Lilaya к своему [pc.face], вы прижимаете мягкую ткань к носу и глубоко вдыхаете, наслаждаясь слегка мускусным запахом нижнего белья вашей демонической [lilaya.relation(pc)].",
					"Вы прижимаете трусики Lilaya к лицу, вдыхая дурманящий, слегка мускусный аромат использованного белья вашей демонической [lilaya.relation(pc)].",
					"Прижимая трусики Lilaya к носу, вы закрываете свои [pc.eyes] и представляете киску вашей демонической [lilaya.relation(pc)], вдыхая её мускусный, смешанный с духами запах.",
					"Вы с жадностью прижимаете трусики Lilaya к носу, стоная и вздыхая, вдыхая мускусный, пропитанный духами аромат ткани.");
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(
						Fetish.FETISH_INCEST,
						Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_MASTURBATION_ORGASM_IN_PANTIES = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				return "Кончить в трусики";
			}
			return "Сосредоточиться на трусиках";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				return "Вы достигли пика и больше не можете сдерживать оргазм. Кончите прямо в трусики Lilaya.";
			}
			return "Вы достигли пика и больше не можете сдерживать оргазм. Сосредоточьтесь на трусиках Lilaya и нюхайте их во время оргазма.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES
					&& ((Main.game.getPlayer().hasPenisIgnoreDildo()
						&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
						&& SexAreaPenetration.PENIS.isFree(Main.game.getPlayer())
						&& !Main.game.getPlayer().isWearingCondom())
					|| (Main.game.getPlayer().hasVagina()
						&& SexAreaOrifice.VAGINA.isFree(Main.game.getPlayer())
						&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)));
		}

		@Override
		public String getDescription() {
			return GenericOrgasms.getGenericOrgasmDescription(this, Main.game.getPlayer(), OrgasmCumTarget.LILAYA_PANTIES);
		}
		
		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			}
		}
	};
}
