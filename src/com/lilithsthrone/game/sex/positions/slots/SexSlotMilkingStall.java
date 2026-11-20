package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the MILKING_STALL position.
 * 
 * @since 0.3.4
 * @version 0.3.9
 * @author Innoxia
 */
public class SexSlotMilkingStall {

	public static final SexSlot LOCKED_IN_MILKING_STALL = new SexSlot(
			"Заперта в стойле",
			"заперта в стойле",
			"Невозможно пошевелиться, [npc1.name] [npc1.verb(wriggle)] вокруг в стойле и [npc1.verb(let)] наружу [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)], чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.LOCKED_IN_STOCKS);
	public static final SexSlot LOCKED_IN_MILKING_STALL_TWO = new SexSlot(LOCKED_IN_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "заперта в стойле (2-я)";
		}
	};
	public static final SexSlot LOCKED_IN_MILKING_STALL_THREE = new SexSlot(LOCKED_IN_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "заперта в стойле (3-я)";
		}
	};
	public static final SexSlot LOCKED_IN_MILKING_STALL_FOUR = new SexSlot(LOCKED_IN_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "заперта в стойле (4-я)";
		}
	};

	
	public static final SexSlot BEHIND_MILKING_STALL = new SexSlot(
			"Позади стойла",
			"позади стойла",
			"[npc1.Name] [npc1.verb(buck)] [npc.her] бедра вперед, вжимаясь в [npc2.namePos] пах и издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)], чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.BEHIND_STOCKS);
	public static final SexSlot BEHIND_MILKING_STALL_TWO = new SexSlot(BEHIND_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "позади стойла (2-я)";
		}
	};
	public static final SexSlot BEHIND_MILKING_STALL_THREE = new SexSlot(BEHIND_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "за стойкой (3-й)";
		}
	};
	public static final SexSlot BEHIND_MILKING_STALL_FOUR = new SexSlot(BEHIND_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "за стойкой (4-й)";
		}
	};
	
	
	public static final SexSlot HUMPING = new SexSlot(
			"Трах",
			"humping",
			"[npc1.Name] [npc1.verb(buck)] [npc.her] бедра вперед, вжимаясь в [npc2.namePos] пах и издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)], чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.HUMPING_STOCKS);
	public static final SexSlot HUMPING_TWO = new SexSlot(HUMPING) {
		@Override
		public String getDescription() {
			return "трах (2-й)";
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(HUMPING) {
		@Override
		public String getDescription() {
			return "трах (3-й)";
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(HUMPING) {
		@Override
		public String getDescription() {
			return "трахается (на четвереньках)";
		}
	};
	
	
	public static final SexSlot BENEATH_MILKING_STALL = new SexSlot(
			"На четвереньках",
			"на четвереньках",
			"[npc1.Name] [npc1.verb(buck)] [npc.her] бедра назад, вжимаясь в [npc2.namePos] пах и издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)], чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.ALL_FOURS);
	public static final SexSlot BENEATH_MILKING_STALL_TWO = new SexSlot(BENEATH_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "на четвереньках (2-я)";
		}
	};
	public static final SexSlot BENEATH_MILKING_STALL_THREE = new SexSlot(BENEATH_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "на четвереньках (3-я)";
		}
	};
	public static final SexSlot BENEATH_MILKING_STALL_FOUR = new SexSlot(BENEATH_MILKING_STALL) {
		@Override
		public String getDescription() {
			return "на четвереньках (4-я)";
		}
	};

	
	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Оральный секс",
			"оральный секс",
			"[npc1.Name] [npc1.verb(reach)] вверх и [npc1.verb(place)] [npc1.hand] на одном из [npc2.namePos] [npc2.legs], издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)], чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
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
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
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
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
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
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	

	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Получает оральный секс",
			"получение орального секса",
			"[npc1.Name] [npc1.verb(reach)] вниз и [npc1.verb(place)] [npc1.hand] на [npc2.namePos] голове, издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)], чтобы достичь [npc1.her] кульминации.",
			true,
			SexSlotTag.RECEIVING_ORAL_STOCKS);
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
