package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfNoPen {
	
	public static final SexAction STROKE_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS);
		}

		@Override
		public String getActionTitle() {
			return "Ласкать себя";
		}

		@Override
		public String getActionDescription() {
			return "Получить немного удовольствия от ласк самой себя.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"Просунув руку между [npc.her] ног, [npc.name] [npc.verb(tease)] [npc.her] пальцами по входу в [npc.her] [npc.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(stimulate)] [npc.her] внешние складки.",
						"[npc.Name] [npc.verb(probe)] [npc.her] пальцы между [npc.her] ног, [npc.moaning] нежно, пока [npc.she] [npc.verb(tease)] [npc.her] пальцы над входом в [npc.her], манящую [npc.pussy].",
						"Скользя кончиками [npc.her] пальцев по забытым [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] негромкий [npc.moan], пока [npc.she] [npc.verb(stimulate)] [npc.her] внешние половые губы.",
						"[npc.Name] с нетерпением [npc.verb(slide)] [npc.her] пальцами по [npc.her] жаждущей [npc.pussy], [npc.moaning+], пока [npc.she] нежно [npc.verb(press)] по [npc.her] внешним половым губам.");
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(run)] [npc.her] рукой вниз, к паху [npc.her], нажимая на [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" сильно на [npc.her] жаждущую [npc.pussy], пока [npc.she] [npc.verb(let)] тихий всхлип.",
						"[npc.Name] [npc.verb(push)] [npc.her] пальцами между [npc.her] ног, [npc.moaning+], пока [npc.she] [npc.verb(rub)] [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" по губам [npc.her] киски.",
						"[npc.Name] [npc.verb(slide)] [npc.her] кончиками пальцев по [npc.her] "+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+", издавая [npc.a_moan+], пока [npc.she] [npc.verb(press)] вниз и [npc.verb(try)], чтобы стимулировать [npc.her] [npc.pussy+] сквозь [npc.her] одежду.",
						"Прижимая ладонью [npc.her] к пространству между [npc.her] ног, [npc.name] [npc.verb(squeeze)] [npc.her] бедра вместе, пока [npc.she] [npc.verb(press)] [npc.her] "
								+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName() +" плотно к ее заброшенной [npc.pussy] [npc.her].");
			}
		}
		
		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
			}
		}
		
	};
	
	public static final SexAction STROKE_MOUND = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().hasPenis()
					&& !Main.sex.getCharacterPerformingAction().hasVagina()
					&& Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS);
		}
		
		@Override
		public String getActionTitle() {
			return "Ласкать лоно";
		}

		@Override
		public String getActionDescription() {
			return "Получить удовольствие, касаясь своего бесполого лона.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Протянув руку между [npc.her] [npc.legs], [npc.name] [npc.verb(run)] [npc.her] кончиками пальцев по кукольному бугорку [npc.her], [npc.moaning+], пока [npc.she] [npc.verb(tease)] чувствительную зону.",
						"[npc.Name] [npc.verb(tease)] пальцами [npc.her] по чувствительному кукольному лону между [npc.her] ног, [npc.moaning+], пока [npc.she] [npc.verb(stimulate)] [npc.herself].",
						"Исследуя ее пальцами, [npc.name] [npc.verb(reach)] вниз и [npc.verb(start)], чтобы щипать и растирать нежную бесполую промежность [npc.her].",
						"Несмотря на отсутствие гениталий, промежность [npc.namePos] остается крайне чувствительной эрогенной зоной, и [npc.she] с жадностью [npc.verb(start)] растирает и прижимает к ней свои ненасытные пальцы.");
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(run)] [npc.her] [npc.hand] над [npc.her] пахом, прижимая [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
							+" плотно к кукольному бугорку [npc.her], пока [npc.she] [npc.verb(let)] тихий вздох.",
						"[npc.Name] [npc.verb(push)] [npc.her] пальцами между [npc.her] ног, [npc.moaning+], пока [npc.she] [npc.verb(rub)] [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" к бесполому паху [npc.her].",
						"[npc.Name] [npc.verb(slide)] [npc.her] кончиками пальцев по [npc.her] "+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+", прежде чем надавить и попытаться стимулировать кукольный бугорок [npc.her] сквозь [npc.her] одежду.",
						"Нажимая ладонью [npc.her] между [npc.her] [npc.legs] [npc.hand], [npc.name] [npc.verb(squeeze)] [npc.her] бедра, пока [npc.she] [npc.verb(press)] [npc.her] "
								+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" плотно к бесполому бугорку [npc.her].");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return null;
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			}
		}
	};
	
}
