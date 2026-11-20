package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.8
 * @version 0.3.4
 * @author Innoxia
 */
public class Missionary {

	public static final SexAction SPREAD_LEGS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Развести ноги";
		}
		@Override
		public String getActionDescription() {
			return "Раздвинь свои [npc.legs] широко для [npc2.name].";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN))
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.MISSIONARY))
					&& !Main.sex.isMasturbation()
					&& Main.sex.getCharacterPerformingAction().hasLegs()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterTargetedForSexAction(this)).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterTargetedForSexAction(this)).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 
			
			if(vaginalSex) {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] и [npc.verb(let)] [npc.moan+], прежде чем [npc.spreadingHerLegs], чтобы помочь [npc2.namePos] [npc2.cock+] погрузиться глубже в [npc.her] [npc.pussy+].",
						"[npc.Name] [npc.spreadsHerLegs] для [npc2.name], помогая [npc2.herHim] вгонять [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+].",
						"Тянув [npc.her] [npc.feet] назад и в стороны, [npc.name] [npc.verb(help)] [npc2.name], чтобы вгонять [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+] за счет [npc.spreadingHerLegs].",
						"[npc.Name] [npc.spreadsHerLegs], глядя на [npc2.name] и кусая [npc.her] [npc.lip], пока [npc2.she] [npc2.verb(continue)] трахает [npc.her] [npc.pussy+]."));
			}
			if(analSex) {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
						UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] и [npc.verb(let)] [npc.moan+], прежде чем поднять [npc.her] [npc.ass+] и [npc.spreadingHerLegs], чтобы помочь [npc2.namePos] [npc2.cock+] погрузиться глубже в [npc.her] [npc.asshole+].",
						"[npc.Name] [npc.spreadsHerLegs] и [npc.verb(raise)] [npc.her] [npc.ass+] для [npc2.name], помогая [npc2.herHim] вгонять [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+].",
						"Тянув [npc.her] [npc.feet] назад и в стороны, [npc.name] [npc.verb(help)] [npc2.name], чтобы вгонять [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+] за счет поднятия [npc.her] [npc.ass] и [npc.spreadingHerLegs].",
						"[npc.Name] [npc.spreadsHerLegs] и [npc.verb(raise)] [npc.her] [npc.ass+], глядя на [npc2.name] и кусая [npc.her] [npc.lip], пока [npc2.she] [npc2.verb(continue)] трахает [npc.her] [npc.asshole+]."));
			}
			
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
					UtilText.returnStringAtRandom(
					"Глядя на [npc2.name], [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] и [npc.verb(let)] из [npc.moan+], прежде чем [npc.spreadingHerLegs] и покорно подставить [npc.herself], готовую к проникновению.",
					"[npc.Name] [npc.spreadsHerLegs] для [npc2.name], подставляя [npc.herself] в предвкушении проникновения.",
					"Растягивая [npc.her] [npc.feet] назад и в стороны, [npc.name] [npc.spreadsHerLegs], соблазняя [npc2.name] проникнуть в [npc.herHim].",
					"[npc.Name] [npc.spreadsHerLegs], глядя на [npc2.name] и кусая [npc.her] [npc.lip], пока [npc.she] [npc.verb(entice)] [npc2.herHim], чтобы проникнуть в [npc.herHim]."));
		}
	};
	
}
