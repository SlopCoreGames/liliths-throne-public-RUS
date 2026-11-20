package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the AGAINST_WALL position.
 * 
 * @since 0.3.4
 * @version 0.4
 * @author Innoxia
 */
public class SexSlotAgainstWall {
	
	public static final SexSlot FACE_TO_WALL = new SexSlot(
			"Лицом к [pc.wall]",
			"лицом к [pc.wall]",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] у [pc.wall] перед [npc1.herHim], издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)] к [npc1.her] кульминации.",
			true,
			SexSlotTag.FACE_TO_WALL);
	public static final SexSlot FACE_TO_WALL_TWO = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "лицом к [pc.wall] (2-й)";
		}
	};
	public static final SexSlot FACE_TO_WALL_THREE = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "лицом к [pc.wall] (3-й)";
		}
	};
	public static final SexSlot FACE_TO_WALL_FOUR = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "лицом к [pc.wall] (4-й)";
		}
	};

	public static final SexSlot BACK_TO_WALL = new SexSlot(
			"Спиной к [pc.wall]",
			"спиной к [pc.wall]",
			"Откинувшись назад, [npc.name] [npc.verb(brace)] [npc.herself] у [pc.wall], пока [npc.she] [npc.verb(feel)] [npc.herself], достигая [npc.her] кульминации.",
			true,
			SexSlotTag.BACK_TO_WALL);
	public static final SexSlot BACK_TO_WALL_TWO = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "спиной к [pc.wall] (2-й)";
		}
	};
	public static final SexSlot BACK_TO_WALL_THREE = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "назад к [pc.wall] (3-я)";
		}
	};
	public static final SexSlot BACK_TO_WALL_FOUR = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "назад к [pc.wall] (4-я)";
		}
	};

	public static final SexSlot STANDING_WALL = new SexSlot(
			"Стоя",
			"standing",
			"Осознав, что [npc1.sheIs] вот-вот достигнет [npc1.her] кульминации, [npc1.name] #IF(npc1.isPlayer())[npc1.step]#ELSE[npc1.steps]#ENDIF подается вперед, издавая [npc1.a_moan+], пока [npc1.she] [npc1.verb(press)] [npc2.name] на [pc.wall].",
			true);
	
	public static final SexSlot STANDING_WALL_TWO = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "стоя (2-я)";
		}
	};
	public static final SexSlot STANDING_WALL_THREE = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "стоя (3-я)";
		}
	};
	public static final SexSlot STANDING_WALL_FOUR = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "стоя (4-я)";
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL = new SexSlot(
			"Оральный секс",
			"оральный секс",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_TWO = new SexSlot(
			"Оральный секс",
			"оральный секс (2-й)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_THREE = new SexSlot(
			"Оральный секс",
			"оральный секс (3-й)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_FOUR = new SexSlot(
			"Оральный секс",
			"оральный секс (4-й)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
