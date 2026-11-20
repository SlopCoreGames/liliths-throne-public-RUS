package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class StocksSex {

	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "Обойти сзади";
		}

		@Override
		public String getActionDescription() {
			return "Обойти [npc2.name] сзади и приготовиться трахнуть [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] трахнуть [npc2.name], пока [npc2.sheIs] зафиксирован в станке, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF обходит [npc2.herHim] сзади и [npc.verb(start)] прижимая свой [npc.her] пах к [npc2.her] [npc2.ass+]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.moanVerb], [npc.speech(Будь хорошей [npc2.girl] и не дергайся, пока я тебя трахаю!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.BEHIND_STOCKS)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};

	public static final SexAction SWITCH_TO_BENEATH = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "На четвереньках";
		}

		@Override
		public String getActionDescription() {
			return "Опуститься на четвереньки под [npc2.name] и прижать свой [npc.ass+] к паху [npc2.her], готовый к тому, чтобы [npc2.herHim] трахнул тебя.";
		}

		@Override
		public String getDescription() {
			return "Желая, чтобы [npc2.name] оттрахал(а) её, [npc.name] [npc.verb(drop)] на четвереньки и [npc.verb(crawl)] вперед под [npc2.her]. Переминаясь с ноги на ногу, чтобы устроиться поудобнее, [npc.she] [npc.verb(lift)] [npc.her] [npc.hips+] и [npc.verb(push)] [npc.her] [npc.ass+] задом к [npc2.her] паху. С возбужденным [npc.moan], [npc.name] [npc.verb(call)], [npc.speech(Повезло тебе! Я позволю тебе оттрахать меня!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.BENEATH_STOCKS)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}

		@Override
		public String getActionDescription() {
			return "Встать на колени позади [npc2.name] и отсосать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] отсосать [npc2.name], [npc.name] [npc.verb(kneel)] позади [npc2.herHim]. Поднося рот [npc.her] к паху [npc2.her], [npc.she] [npc.moanVerb], [npc.speech(Тебе это понравится!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "Перейти вперед";
		}

		@Override
		public String getActionDescription() {
			return "Решить использовать рот [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] использовать рот [npc2.namePos], [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF задом, прежде чем переместиться перед [npc2.her] [npc2.face]. Поднося пах [npc.her] к рту [npc2.her], [npc.she] [npc.moanVerb], [npc.speech(Тебе это понравится!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};

}
