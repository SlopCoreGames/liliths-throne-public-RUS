package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the STANDING position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotStanding {

	public static final SexSlot STANDING_DOMINANT = new SexSlot(
			"Стоя",
			"standing",
			"[npc.Name] [npc.verb(lean)] глубоко в [npc2.namePos] [npc2.breasts] и [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			true,
			SexSlotTag.STANDING) {
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(targetedCharacter);
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.";
			}
			if(targetedSlot.hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return "С коротким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] пахом в [npc2.namePos] [npc2.face], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.";
			}
			if(targetedSlot.hasTag(SexSlotTag.PERFORMING_ORAL_BEHIND)) {
				return "С коротким толчком [npc.her] [npc.hips] назад, [npc.name] [npc.verb(push)] [npc.her] задницей назад в [npc2.namePos] [npc2.face], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.";
			}
			if(targetedSlot.hasTag(SexSlotTag.STANDING_BEHIND)) {
				return "Откинувшись назад на [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.";
			}
			if(targetedCharacter.isTaur()) {
				return "[npc.Name] [npc.verb(reach)] вокруг и [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.namePos] спины, притягивая [npc2.herHim] ближе и издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.";
			}
			return "[npc.Name] [npc.verb(reach)] вокруг и [npc.verb(grab)] [npc2.namePos] [npc2.ass+], притягивая [npc2.herHim] ближе и издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.";
		}
	};
	
	public static final SexSlot STANDING_DOMINANT_TWO = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "стоя (2-я)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot STANDING_DOMINANT_THREE = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "стоя (3-я)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot STANDING_DOMINANT_FOUR = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "стоя (4-я)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"Стоя",
			"стоит (спереди)",
			"[npc.Name] [npc.verb(lean)] глубоко в [npc2.namePos] [npc2.breasts] и [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			true);

	public static final SexSlot STANDING_SUBMISSIVE_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "стоит (2-я спереди)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "стоит (3-я спереди)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "стоя (4-й спереди)";
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE_BEHIND = new SexSlot(
			"Стоя",
			"стоя (сзади)",
			"[npc.Name] [npc.verb(pull)] [npc2.name] прижавшись спиной к [npc.her] [npc.breasts] и [npc.verb(let)] вытаскивая [npc.a_moan+] как [npc.she] [npc.verb(prepare)] чтобы достичь [npc.her] оргазма.",
			true,
			SexSlotTag.STANDING_BEHIND);

	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "стоя (2-й сзади)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "стоя (3-й сзади)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "стоя (4-й сзади)";
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Оральный секс (спереди)",
			"оральный секс (спереди)",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Оральный секс (спереди)",
			"оральный секс (2-й спереди)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Оральный секс (спереди)",
			"оральный секс (3-я позиция спереди)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Оральный секс (спереди)",
			"оральный секс (4-я позиция спереди)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND = new SexSlot(
			"оральный секс (сзади)",
			"оральный секс (сзади)",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_TWO = new SexSlot(
			"оральный секс (сзади)",
			"оральный секс (2-я позиция сзади)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_THREE = new SexSlot(
			"оральный секс (сзади)",
			"оральный секс (3-я позиция сзади)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_FOUR = new SexSlot(
			"оральный секс (сзади)",
			"оральный секс (4-я позиция сзади)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
