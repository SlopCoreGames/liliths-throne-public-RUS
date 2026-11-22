package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * All SexSlots that are used in the ALL_FOURS position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotAllFours {

	public static final SexSlot ALL_FOURS = new SexSlot(
			"На четвереньках",
			"на четвереньках",
			"С дрожащими [npc.legs], [npc.name] [npc.do] [npc.her], стараясь унять [npc.herself], и с [npc.a_moan+], [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			false,
			SexSlotTag.ALL_FOURS);
	public static final SexSlot ALL_FOURS_TWO = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "на четвереньках (2-я)";
		}
	};
	public static final SexSlot ALL_FOURS_THREE = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "на четвереньках (3-я)";
		}
	};
	public static final SexSlot ALL_FOURS_FOUR = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "на четвереньках (4-я)";
		}
	};
	

	public static final SexSlot BEHIND = new SexSlot(
			"Верхом/Трах",
			"behind",
			"[npc.Name] [npc.verb(press)] [npc.her] весом на теле [npc2.namePos], издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"Верхом"
						:standing?"Стоя сзади":"На коленях сзади")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name] [npc.verb(press)] [npc.her] весом придавливает [npc2.namePos] тело, издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации. Поддавшись [npc.her] диким инстинктам, [npc.she] крепко [npc.verb(bite)] на [npc2.namePos] шею, чтобы не дать [npc2.herHim] вырваться, пока [npc.she] [npc.verb(orgasm)].";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_TWO = new SexSlot(
			"Верхом/Трах",
			"сзади (2-й)",
			"[npc.Name] [npc.verb(press)] [npc.her] весом на теле [npc2.namePos], издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"Верхом"
						:standing?"Стоя сзади":"На коленях сзади")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name] [npc.verb(press)] [npc.her] весом придавливает [npc2.namePos] тело, издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации. Поддавшись [npc.her] диким инстинктам, [npc.she] крепко [npc.verb(bite)] на [npc2.namePos] шею, чтобы не дать [npc2.herHim] вырваться, пока [npc.she] [npc.verb(orgasm)].";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_THREE = new SexSlot(
			"Верхом/Трах",
			"сзади (3-й)",
			"[npc.Name] [npc.verb(press)] [npc.her] весом на теле [npc2.namePos], издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"Верхом"
						:standing?"Стоя сзади":"На коленях сзади")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name] [npc.verb(press)] [npc.her] весом придавливает [npc2.namePos] тело, издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации. Поддавшись [npc.her] диким инстинктам, [npc.she] крепко [npc.verb(bite)] на [npc2.namePos] шею, чтобы не дать [npc2.herHim] вырваться, пока [npc.she] [npc.verb(orgasm)].";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_FOUR = new SexSlot(
			"Верхом/Трах",
			"сзади (4-й)",
			"[npc.Name] [npc.verb(press)] [npc.her] весом на теле [npc2.namePos], издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"Верхом"
						:standing?"Стоя сзади":"На коленях сзади")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(prepare)], чтобы достичь [npc.her] кульминации.";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name] [npc.verb(press)] [npc.her] весом придавливает [npc2.namePos] тело, издавая [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации. Поддавшись [npc.her] диким инстинктам, [npc.she] крепко [npc.verb(bite)] на [npc2.namePos] шею, чтобы не дать [npc2.herHim] вырваться, пока [npc.she] [npc.verb(orgasm)].";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	
	public static final SexSlot HUMPING = new SexSlot(
			"Трах",
			"humping",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Трах "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot HUMPING_TWO = new SexSlot(
			"Трах",
			"трах (2-й)",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Трах "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(
			"Трах",
			"трах (3-й)",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Трах "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(
			"Трах",
			"трахается (на четвереньках)",
			"[npc.Name] неистово [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.name], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] к [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Трах "+UtilText.parse(partner, "[npc.name]");
		}
	};
	
	
	public static final SexSlot BEHIND_ORAL = new SexSlot(
			"Оральный секс",
			"оральный секс",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Делает минет "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot BEHIND_ORAL_TWO = new SexSlot(
			"Оральный секс",
			"оральный секс (2-й)",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Делает минет "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot BEHIND_ORAL_THREE = new SexSlot(
			"Оральный секс",
			"оральный секс (3-й)",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Делает минет "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot BEHIND_ORAL_FOUR = new SexSlot(
			"Оральный секс",
			"оральный секс (4-й)",
			"[npc.Name] [npc.verb(reach)] вверх и [npc.verb(place)] [npc.hand] на одном из [npc2.namePos] [npc2.legs], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] достигает [npc.her] кульминации.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Делает минет "+UtilText.parse(partner, "[npc.name]");
		}
	};

	public static final SexSlot USING_FEET = new SexSlot(
			"используя ноги",
			"использование ног",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Использование "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_TWO = new SexSlot(
			"используя ноги",
			"используя ноги (2-й вариант)",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Использование "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_THREE = new SexSlot(
			"используя ноги",
			"ногами (3-я)",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Использование "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_FOUR = new SexSlot(
			"используя ноги",
			"ногами (4-я)",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Использование "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};

	
	public static final SexSlot IN_FRONT = new SexSlot(
			"Стоя/на коленях",
			"спереди",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return (this.isStanding(target)?"Стоя":"На коленях")+(target.isTaur()?" над ":" перед ")+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};
	public static final SexSlot IN_FRONT_TWO = new SexSlot(
			"Стоя/на коленях",
			"спереди (2-я)",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"Стоя":"На коленях")+(target.isTaur()?" над ":" перед ")+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};
	public static final SexSlot IN_FRONT_THREE = new SexSlot(
			"Стоя/на коленях",
			"спереди (3-я)",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"Стоя":"На коленях")+(target.isTaur()?" над ":" перед ")+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};
	public static final SexSlot IN_FRONT_FOUR = new SexSlot(
			"Стоя/на коленях",
			"спереди (4-я)",
			"[npc.Name] [npc.verb(shuffle)] вперед, чтобы стать ближе к [npc2.name], после чего издает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], достигая [npc.her] оргазма.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"Стоя":"На коленях")+(target.isTaur()?" над ":" перед ")+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};


	public static final SexSlot IN_FRONT_ANAL = new SexSlot(
			"Стоя/на коленях",
			"спереди (анальный)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] назад в [npc2.namePos] [npc2.face], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] стремится к [npc.her] кульминации.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Подставить задницу для "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_TWO = new SexSlot(
			"Стоя/на коленях",
			"спереди (2-й анальный)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] назад в [npc2.namePos] [npc2.face], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] стремится к [npc.her] кульминации.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "Подставить задницу для "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_THREE = new SexSlot(
			"Стоя/на коленях",
			"спереди (3-й анальный)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] назад в [npc2.namePos] [npc2.face], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] стремится к [npc.her] кульминации.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "Подставить задницу для "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_FOUR = new SexSlot(
			"Стоя/на коленях",
			"спереди (4-й анальный)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] назад в [npc2.namePos] [npc2.face], прежде чем издать [npc.a_moan+], пока [npc.she] [npc.verb(prepare)] стремится к [npc.her] кульминации.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "Подставить задницу для "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
}
