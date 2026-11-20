package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.4
 * @author Innoxia
 */
public class Masturbation {
	
	public static final SexAction SWITCH_TO_STANDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.STANDING;
		}
		@Override
		public String getActionTitle() {
			return "Стоя";
		}
		@Override
		public String getActionDescription() {
			return "Решить подойти к своей [npc.feet] и продолжить мастурбировать стоя во весь рост.";
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Решив, что будет лучше продолжить мастурбировать стоя, [npc.name] [npc.verb(push)] [npc.herself] на [npc.her] [npc.feet], прежде чем опустить [npc.her] [npc.hands] между [npc.her] [npc.legs] и приготовиться продолжить с того места, где [npc.she] остановилась...";
			} else {
				return "Решив, что будет лучше продолжить мастурбировать стоя, [npc.name] [npc.verb(push)] [npc.herself] на [npc.her] [npc.feet], прежде чем оглянуться через [npc.her] плечо на дикое тело [npc.her] [npc.legRace] и издать разочарованный скулящий звук...";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.STANDING))
						:null){
			});
		}
	};
	
	public static final SexAction SWITCH_TO_SITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.SITTING;
		}
		@Override
		public String getActionTitle() {
			return "Сидя";
		}
		@Override
		public String getActionDescription() {
			return "Решить сесть на ближайшую поверхность и продолжить мастурбировать в таком положении.";
		}
		@Override
		public String getDescription() {
			return "Решив, что будет лучше продолжить мастурбировать сидя, [npc.name] [npc.verb(find)] подходящую поверхность поблизости, прежде чем сесть и подвинуть [npc.her] [npc.hands] между [npc.her] [npc.legs]...";
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.SITTING))
						:null){
			});
		}
	};

	public static final SexAction SWITCH_TO_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return SexPosition.MASTURBATION.isSlotUnlocked(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING, Main.sex.getAllOccupiedSlots(true)).getKey()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING_PANTIES
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotMasturbation.KNEELING;
		}
		@Override
		public String getActionTitle() {
			return "На коленях";
		}
		@Override
		public String getActionDescription() {
			return "Решить опуститься на колени и продолжить мастурбировать в таком положении.";
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Решив, что будет лучше продолжить мастурбировать, стоя на коленях на земле, [npc.name] [npc.verb(drop)] в такое положение, прежде чем скользнуть [npc.her] [npc.hands] между [npc.her] [npc.legs] и приготовиться продолжить с того места, где [npc.she] остановилась...";
			} else {
				return "Решив, что будет лучше продолжить мастурбацию, стоя на коленях на земле, [npc.name] [npc.verb(drop)] в такую позу, прежде чем оглянуться через [npc.her] плечо на дикое тело [npc.her] [npc.legRace] и издать разочарованный скулящий звук...";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.MASTURBATION,
					Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING))
						:null,
					!Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						?Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMasturbation.KNEELING))
						:null){
			});
		}
	};
	
}
