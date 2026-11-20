package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.8
 * @version 0.3.9
 * @author Innoxia
 */
public class MilkingStall {

	public static final SexAction ATTACH_PUMPS = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private List<InventorySlot> getPumpSlots() {
			List<InventorySlot> list = new ArrayList<>();
			GameCharacter milker = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(MilkingRoom.getActualMilkPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.NIPPLE, null)) {
				if(milker.getClothingInSlot(InventorySlot.NIPPLE)==null) {
					list.add(InventorySlot.NIPPLE);
				}
			}
			if(MilkingRoom.getActualCrotchMilkPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK_CROTCH)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.STOMACH, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.STOMACH, null)) {
				if(milker.getClothingInSlot(InventorySlot.STOMACH)==null) {
					list.add(InventorySlot.STOMACH);
				}
			}
			if(MilkingRoom.getActualCumPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_CUM)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.PENIS, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.PENIS, null)) {
				if(milker.getClothingInSlot(InventorySlot.PENIS)==null) {
					list.add(InventorySlot.PENIS);
				}
			}
			if(MilkingRoom.getActualGirlcumPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_GIRLCUM)
					&& (!milker.hasHymen() || milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_TEAR_HYMEN))
					&& milker.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.VAGINA, null)) {
				if(milker.getClothingInSlot(InventorySlot.VAGINA)==null) {
					list.add(InventorySlot.VAGINA);
				}
			}
			
			return list;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.MILK;
		}
		@Override
		public boolean isDisplayedAsUnavailable() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Прикрепить насосы";
		}
		@Override
		public String getActionDescription() {
			List<InventorySlot> pumps = getPumpSlots();
			if(pumps.isEmpty()) {
				//"[npc2.Name] doesn't have any accessible areas for milking pumps to be attached to!"
				return "<i>[style.italicsBad(Требуется:)] Один из следующих слотов должен быть свободен: 'сосок', 'пенис', 'вагина' или 'живот' (для доения вымени). Также необходимы права [npc2.namePos] на доение, позволяющие экипировать насосы в этот слот.</i>";
			}
			return "Прикрепить насосы доильного аппарата к [npc2.namePos] "+Util.inventorySlotsToStringList(pumps)+".";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			List<InventorySlot> pumps = getPumpSlots();
			return !pumps.isEmpty()
					&& Main.sex.getCharacterPerformingAction().isPlayer() // Limit to the player as otherwise it gets incredibly annoying
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (!Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS) || Main.sex.isMasturbation())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LOCKED_IN_STOCKS);
		}

		@Override
		public String getDescription() {
			List<InventorySlot> pumps = getPumpSlots();
			
			if(pumps.size()>1 || pumps.contains(InventorySlot.NIPPLE) || pumps.contains(InventorySlot.STOMACH)) {
				return "Решив подключить [npc2.name] к доильному аппарату, [npc.name] [npc.verb(move)] к одной стороне стойла и [npc.verb(grab)] еще не использованные насосы...";
				
			} else {
				return "Решив подключить [npc2.name] к доильному аппарату, [npc.name] [npc.verb(move)] к одной стороне стойла и [npc.verb(grab)] еще не использованный насос...";
			}
		}

		@Override
		public String applyEffectsString(){
			List<InventorySlot> pumps = getPumpSlots();
			GameCharacter equipper = Main.sex.getCharacterPerformingAction();
			GameCharacter milker = Main.sex.getCharacterTargetedForSexAction(this);
			StringBuilder sb = new StringBuilder();
			
			for(InventorySlot slot : pumps) {
				sb.append("<p style='text-align:center;padding:0;margin:0;'><i>");
					switch(slot) {
						case NIPPLE:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, equipper));
							break;
						case STOMACH:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, equipper));
							break;
						case PENIS:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, equipper));
							break;
						case VAGINA:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, equipper));
							break;
						default:
							break;
					}
				sb.append("</i></p>");
			}
			
			return sb.toString();
		}
	};
	
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
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
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
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
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
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.BENEATH_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
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
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
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
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
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
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
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
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

}
