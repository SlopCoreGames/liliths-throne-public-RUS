package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * All SexSlots that are used in the OVER_DESK position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotDesk {

	public static final SexSlot OVER_DESK_ON_BACK = new SexSlot(
			"Лежа на спине",
			"лежа на спине",
			"[npc.Name] [npc.verb(collapse)] на спину на [pc.desk], издав [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false,
			SexSlotTag.OVER_DESK_BACK);
	public static final SexSlot OVER_DESK_ON_BACK_TWO = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "лежа на спине (2-й)";
		}
	};
	public static final SexSlot OVER_DESK_ON_BACK_THREE = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "лежа на спине (3-й)";
		}
	};
	public static final SexSlot OVER_DESK_ON_BACK_FOUR = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "лежа на спине (4-й)";
		}
	};

	
	public static final SexSlot OVER_DESK_ON_FRONT = new SexSlot(
			"Наклонившись",
			"наклонившись",
			"[npc.Name] [npc.verb(collapse)] на [pc.desk], издав [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false,
			SexSlotTag.OVER_DESK_FRONT);
	public static final SexSlot OVER_DESK_ON_FRONT_TWO = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "наклонившись (2-й)";
		}
	};
	public static final SexSlot OVER_DESK_ON_FRONT_THREE = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "наклонившись (3-й)";
		}
	};
	public static final SexSlot OVER_DESK_ON_FRONT_FOUR = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "наклонившись (4-й)";
		}
	};

	
	public static final SexSlot BETWEEN_LEGS = new SexSlot(
			"Между ног",
			"между ног",
			null,
			true,
			SexSlotTag.OVER_DESK_BETWEEN_LEGS);
	public static final SexSlot BETWEEN_LEGS_TWO = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "между ног (2-й)";
		}
	};
	public static final SexSlot BETWEEN_LEGS_THREE = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "между ног (3-й)";
		}
	};
	public static final SexSlot BETWEEN_LEGS_FOUR = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "между ног (4-й)";
		}
	};

	public static final SexSlot HUMPING = new SexSlot(
			"Трах",
			"humping",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK); }
			if(partner!=null) { return "Трах "+UtilText.parse(partner, "[npc.name]"); }
			return "Трах";
		}
	};
	public static final SexSlot HUMPING_TWO = new SexSlot(
			"Трах",
			"трах (2-й)",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT_TWO);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK_TWO); }
			if(partner!=null) { return "Трах "+UtilText.parse(partner, "[npc.name]"); }
			return "Трах";
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(
			"Трах",
			"трах (3-й)",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT_THREE);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK_THREE); }
			if(partner!=null) { return "Трах "+UtilText.parse(partner, "[npc.name]"); }
			return "Трах";
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(
			"Трах",
			"трахается (на четвереньках)",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(OVER_DESK_ON_FRONT_FOUR);
			if(partner==null) { partner = getCharacterInSlot(OVER_DESK_ON_BACK_FOUR); }
			if(partner!=null) { return "Трах "+UtilText.parse(partner, "[npc.name]"); }
			return "Трах";
		}
	};
	
	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Оральный секс",
			"оральный секс",
			"[npc.Name] [npc.verb(lower)] [npc.her] голову в [npc2.namePos] пах, издав [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Оральный секс",
			"оральный секс (2-й)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Оральный секс",
			"оральный секс (3-й)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Оральный секс",
			"оральный секс (4-й)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	

	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Получает оральный секс",
			"получение орального секса",
			"[npc.Name] [npc.verb(press)] [npc.herself] вплотную к [npc2.name], издав [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			true);
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "принимает оральный секс (2-й)";
		}
	};
	public static final SexSlot RECEIVING_ORAL_THREE =  new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "принимает оральный секс (3-й)";
		}
	};
	public static final SexSlot RECEIVING_ORAL_FOUR =  new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "принимает оральный секс (4-й)";
		}
	};
}
