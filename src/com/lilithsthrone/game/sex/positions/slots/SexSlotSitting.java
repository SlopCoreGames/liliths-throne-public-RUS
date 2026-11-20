package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the SITTING position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotSitting {

	public static final SexSlot SITTING = new SexSlot(
			"Сидя",
			"сидит",
			"С коротким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING);
	public static final SexSlot SITTING_TWO = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "сидит (2-й)";
		}
	};
	public static final SexSlot SITTING_THREE = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "сидит (3-й)";
		}
	};
	public static final SexSlot SITTING_FOUR = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "сидит (4-й)";
		}
	};
	
	
	public static final SexSlot SITTING_IN_LAP = new SexSlot(
			"Сидит на коленях",
			"сидит на коленях",
			"Дрожащими [npc.legs], [npc.name] [npc.verb(sink)] вниз на [npc2.namePos] колени, и с [npc.a_moan+], [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING_IN_LAP);
	public static final SexSlot SITTING_IN_LAP_TWO = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "сидит на коленях (2-й)";
		}
	};
	public static final SexSlot SITTING_IN_LAP_THREE = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "сидит на коленях (3-й)";
		}
	};
	public static final SexSlot SITTING_IN_LAP_FOUR = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "сидя на коленях (4-я)";
		}
	};
	
	
	public static final SexSlot SITTING_BETWEEN_LEGS = new SexSlot(
			"Между ног",
			"между ног",
			"Наклоняясь к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_TWO = new SexSlot(
			"Между ног",
			"между ног (2-й)",
			"Наклоняясь к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_TWO);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_THREE = new SexSlot(
			"Между ног",
			"между ног (3-й)",
			"Наклоняясь к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_THREE);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_FOUR = new SexSlot(
			"Между ног",
			"между ног (4-й)",
			"Наклоняясь к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING_BETWEEN_LEGS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_FOUR);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Оральный секс",
			"оральный секс",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false,SexSlotTag.SITTING_PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Оральный секс",
			"оральный секс (2-й)",
			null,
			false,
			SexSlotTag.SITTING_PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Оральный секс",
			"оральный секс (3-й)",
			null,
			false,
			SexSlotTag.SITTING_PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Оральный секс",
			"оральный секс (4-й)",
			null,
			false,
			SexSlotTag.SITTING_PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL = new SexSlot(
			"подставив для орального секса",
			"подставив для орального секса",
			"С дрожащими [npc.legs], [npc.name] [npc.verb(push)] [npc.her] паха обратно к [npc2.namePos] лицу, и с [npc.a_moan+], [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			false,
			SexSlotTag.SITTING_TAUR_PRESENTING_ORAL);
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_TWO = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "подставив для орального секса (2-я)";
		}
	};
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_THREE = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "подставив для орального секса (3-я)";
		}
	};
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_FOUR = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "предлагает себя для орального (4-я)";
		}
	};
	
}
