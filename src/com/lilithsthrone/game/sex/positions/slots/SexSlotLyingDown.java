package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the LYING_DOWN position.
 * 
 * @since 0.3.4
 * @version 0.4.11.2
 * @author Innoxia
 */
public class SexSlotLyingDown {

	public static final SexSlot LYING_DOWN = new SexSlot(
			"Лежа на спине",
			"лежа на спине",
			"С резким [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] к кульминации.",
			false,
			SexSlotTag.LYING_DOWN);
	public static final SexSlot LYING_DOWN_TWO = new SexSlot(LYING_DOWN) {
		@Override
		public String getDescription() {
			return "лежа на спине (2-й)";
		}
	};
	public static final SexSlot LYING_DOWN_THREE = new SexSlot(LYING_DOWN) {
		@Override
		public String getDescription() {
			return "лежа на спине (3-й)";
		}
	};
	public static final SexSlot LYING_DOWN_FOUR = new SexSlot(LYING_DOWN) {
		@Override
		public String getDescription() {
			return "лежа на спине (4-й)";
		}
	};

	public static final SexSlot LYING_DOWN_FRONT = new SexSlot(
			"Лежа на животе",
			"лежа на животе",
			"Продолжая лежать на [npc.her] животе, [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] к кульминации.",
			false,
			SexSlotTag.LYING_DOWN_ON_FRONT);
	public static final SexSlot LYING_DOWN_FRONT_TWO = new SexSlot(LYING_DOWN_FRONT) {
		@Override
		public String getDescription() {
			return "лежа на животе (2-й)";
		}
	};
	public static final SexSlot LYING_DOWN_FRONT_THREE = new SexSlot(LYING_DOWN_FRONT) {
		@Override
		public String getDescription() {
			return "лежа на животе (3-й)";
		}
	};
	public static final SexSlot LYING_DOWN_FRONT_FOUR = new SexSlot(LYING_DOWN_FRONT) {
		@Override
		public String getDescription() {
			return "лежа на животе (4-й)";
		}
	};
	
	
	public static final SexSlot COWGIRL = new SexSlot(
			"Наездница",
			"cowgirl",
			"Дрожащими [npc.legs], [npc.name] [npc.verb(sink)] в [npc2.namePos] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.COWGIRL);
	public static final SexSlot COWGIRL_TWO = new SexSlot(COWGIRL) {
		@Override
		public String getDescription() {
			return "наездница (2-я)";
		}
	};
	public static final SexSlot COWGIRL_THREE = new SexSlot(COWGIRL) {
		@Override
		public String getDescription() {
			return "наездница (3-я)";
		}
	};
	public static final SexSlot COWGIRL_FOUR = new SexSlot(COWGIRL) {
		@Override
		public String getDescription() {
			return "наездница (4-я)";
		}
	};

	
	
	public static final SexSlot COWGIRL_REVERSE = new SexSlot(
			"Обратная наездница",
			"обратная наездница",
			"Дрожащими [npc.legs], [npc.name] [npc.verb(sink)] в [npc2.namePos] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.COWGIRL_REVERSE);
	public static final SexSlot COWGIRL_REVERSE_TWO = new SexSlot(COWGIRL_REVERSE) {
		@Override
		public String getDescription() {
			return "обратная наездница (2-я)";
		}
	};
	public static final SexSlot COWGIRL_REVERSE_THREE = new SexSlot(COWGIRL_REVERSE) {
		@Override
		public String getDescription() {
			return "обратная наездница (3-я)";
		}
	};
	public static final SexSlot COWGIRL_REVERSE_FOUR = new SexSlot(COWGIRL_REVERSE) {
		@Override
		public String getDescription() {
			return "обратная наездница (4-я)";
		}
	};

	
	/** Sitting looking down into the person's eyes. */
	public static final SexSlot FACE_SITTING = new SexSlot(
			"Лицом к лицу: фейсситтинг",
			"face-sitting",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING);

	public static final SexSlot FACE_SITTING_TWO = new SexSlot(
			"Лицом к лицу: фейсситтинг",
			"фейсситтинг (2-й)",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING);

	public static final SexSlot FACE_SITTING_THREE = new SexSlot(
			"Лицом к лицу: фейсситтинг",
			"фейсситтинг (3-й)",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING);

	public static final SexSlot FACE_SITTING_FOUR = new SexSlot(
			"Лицом к лицу: фейсситтинг",
			"фейсситтинг (4-й)",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING);

	/** Sitting looking down at the person's lower body. */
	public static final SexSlot FACE_SITTING_REVERSE = new SexSlot(
			"Обратное фейс-ситтинг",
			"обратное фейс-ситтинг",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	public static final SexSlot FACE_SITTING_REVERSE_TWO = new SexSlot(
			"Обратное фейс-ситтинг",
			"обратное фейс-ситтинг (2-е)",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	public static final SexSlot FACE_SITTING_REVERSE_THREE = new SexSlot(
			"Обратное фейс-ситтинг",
			"обратное фейс-ситтинг (3-е)",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	public static final SexSlot FACE_SITTING_REVERSE_FOUR = new SexSlot(
			"Обратное фейс-ситтинг",
			"обратное фейс-ситтинг (4-е)",
			"Дрожа от [npc.legs], [npc.name] [npc.verb(sink)] на [npc2.namePos] лицо, издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминация.",
			false,
			SexSlotTag.FACE_SITTING_REVERSE);

	/** Oral slots for if the targeted character is lying down on their front. */
	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Спереди",
			"спереди",
			"[npc.Name] [npc.verb(push)] [npc.her] пах в [npc2.namePos] лицо и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] достигает кульминации.",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(
			"Спереди",
			"спереди (2-я)",
			"[npc.Name] [npc.verb(push)] [npc.her] пах в [npc2.namePos] лицо и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] достигает кульминации.",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	public static final SexSlot RECEIVING_ORAL_THREE = new SexSlot(
			"Спереди",
			"спереди (3-я)",
			"[npc.Name] [npc.verb(push)] [npc.her] пах в [npc2.namePos] лицо и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] достигает кульминации.",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	public static final SexSlot RECEIVING_ORAL_FOUR = new SexSlot(
			"Спереди",
			"спереди (4-я)",
			"[npc.Name] [npc.verb(push)] [npc.her] пах в [npc2.namePos] лицо и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] достигает кульминации.",
			false,
			SexSlotTag.RECEIVING_ORAL_FROM_PRONE_PARTNER);
	
	
	public static final SexSlot LAP_PILLOW = new SexSlot(
			"Подушка из колен",
			"подушка из колен",
			"Смотря вниз на [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.",
			false,
			SexSlotTag.LAP_PILLOW);

	public static final SexSlot LAP_PILLOW_TWO = new SexSlot(
			"Подушка из колен",
			"подушка из колен (2-я)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.",
			false,
			SexSlotTag.LAP_PILLOW);

	public static final SexSlot LAP_PILLOW_THREE = new SexSlot(
			"Подушка из колен",
			"подушка из колен (3-я)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.",
			false,
			SexSlotTag.LAP_PILLOW);

	public static final SexSlot LAP_PILLOW_FOUR = new SexSlot(
			"Подушка из колен",
			"Подушка из колен (4-я)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] кульминации.",
			false,
			SexSlotTag.LAP_PILLOW);
	
	
	

	public static final SexSlot SIXTY_NINE = new SexSlot(
			"Шестьдесят девять",
			"sixty-nine",
			"Дрожащими [npc.legs], [npc.name] [npc.verb(allow)] [npc.her] на лицо [npc2.namePos], издавая [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] к кульминации.",
			false,
			SexSlotTag.SIXTY_NINE);
	public static final SexSlot SIXTY_NINE_TWO = new SexSlot(SIXTY_NINE) {
		@Override
		public String getDescription() {
			return "Миссионерский 69 (2-й)";
		}
	};
	public static final SexSlot SIXTY_NINE_THREE = new SexSlot(SIXTY_NINE) {
		@Override
		public String getDescription() {
			return "Миссионерский 69 (3-й)";
		}
	};
	public static final SexSlot SIXTY_NINE_FOUR = new SexSlot(SIXTY_NINE) {
		@Override
		public String getDescription() {
			return "Миссионерский 69 (4-й)";
		}
	};
	
	
	
	public static final SexSlot MISSIONARY = new SexSlot(
			"Миссионерская",
			"между ног",
			"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] к кульминации, и, наклонившись сверху на [npc2.name], [npc.she] похотливо [npc.verb(gaze)] в [npc2.her] [npc2.eyes+].",
			false,
			SexSlotTag.MISSIONARY) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT);
			if(partner==null) {
				return super.getName(target);
			}
			return "Поза «костью» (лежа на животе)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter) || !Main.game.isInSex()) {
				return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
			}
			if(Main.sex.getSexPositionSlot(targetedCharacter).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
				return "[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] к кульминации, и, наклонившись сверху на [npc2.name], [npc.she] [npc.verb(gaze)] в затылок [npc2.her].";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot MISSIONARY_TWO = new SexSlot(MISSIONARY) {
		@Override
		public String getDescription() {
			return "между ног (2-й)";
		}
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT_TWO);
			if(partner==null) {
				return super.getName(target);
			}
			return "Поза «костью» (лежа на животе)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return MISSIONARY.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot MISSIONARY_THREE = new SexSlot(MISSIONARY) {
		@Override
		public String getDescription() {
			return "между ног (3-й)";
		}
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT_THREE);
			if(partner==null) {
				return super.getName(target);
			}
			return "Поза «костью» (лежа на животе)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return MISSIONARY.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot MISSIONARY_FOUR = new SexSlot(MISSIONARY) {
		@Override
		public String getDescription() {
			return "между ног (4-й)";
		}
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(LYING_DOWN_FRONT_FOUR);
			if(partner==null) {
				return super.getName(target);
			}
			return "Поза «костью» (лежа на животе)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return MISSIONARY.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	
	public static final SexSlot MISSIONARY_ORAL = new SexSlot(
			"Миссионерский оральный секс",
			"лицо между ног",
			"[npc.Name] [npc.verb(let)] наружу [npc.a_moan+] как [npc.she] [npc.verb(reach)] [npc.her] кульминации, и, прижимая [npc.her] [npc.face] к [npc2.namePos] паху, [npc.she] [npc.verb(prepare)] чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.MISSIONARY_ORAL);
	public static final SexSlot MISSIONARY_ORAL_TWO = new SexSlot(MISSIONARY_ORAL) {
		@Override
		public String getDescription() {
			return "лицо между ног (2-й)";
		}
	};
	public static final SexSlot MISSIONARY_ORAL_THREE = new SexSlot(MISSIONARY_ORAL) {
		@Override
		public String getDescription() {
			return "лицо между ног (3-й)";
		}
	};
	public static final SexSlot MISSIONARY_ORAL_FOUR = new SexSlot(MISSIONARY_ORAL) {
		@Override
		public String getDescription() {
			return "лицо между ног (4-й)";
		}
	};

	
	
	public static final SexSlot MATING_PRESS = new SexSlot(
			"Прессинг при спаривании",
			"коленно-локтевой секс (mating press)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes] и придавливая [npc.her] весом сверху на [npc2.herHim], [npc.name] [npc1.verb(let)] наружу [npc1.a_moan+] как [npc1.she] [npc1.verb(prepare)] чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.MATING_PRESS);

	public static final SexSlot MATING_PRESS_TWO = new SexSlot(
			"Прессинг при спаривании",
			"коленно-локтевой секс (2-й)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes] и придавливая [npc.her] весом сверху на [npc2.herHim], [npc.name] [npc1.verb(let)] наружу [npc1.a_moan+] как [npc1.she] [npc1.verb(prepare)] чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.MATING_PRESS);

	public static final SexSlot MATING_PRESS_THREE = new SexSlot(
			"Прессинг при спаривании",
			"спаривание (3-я)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes] и придавливая [npc.her] весом сверху на [npc2.herHim], [npc.name] [npc1.verb(let)] наружу [npc1.a_moan+] как [npc1.she] [npc1.verb(prepare)] чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.MATING_PRESS);

	public static final SexSlot MATING_PRESS_FOUR = new SexSlot(
			"Прессинг при спаривании",
			"спаривание (4-я)",
			"Смотря вниз на [npc2.namePos] [npc2.eyes] и придавливая [npc.her] весом сверху на [npc2.herHim], [npc.name] [npc1.verb(let)] наружу [npc1.a_moan+] как [npc1.she] [npc1.verb(prepare)] чтобы достичь [npc1.her] кульминации.",
			false,
			SexSlotTag.MATING_PRESS);
	
	

	public static final SexSlot SCISSORING = new SexSlot(
			"Скрайсинг",
			"scissoring",
			"Отчаянно толкая [npc.her] [npc.hips] вперед, чтобы прижать [npc.her] пах к [npc2.nameHers], [npc.name] [npc1.verb(let)] [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)] на пути к [npc1.her] кульминации.",
			false,
			SexSlotTag.SCISSORING);

	public static final SexSlot SCISSORING_TWO = new SexSlot(
			"Скрайсинг",
			"ножницы (2-я)",
			"Отчаянно толкая [npc.her] [npc.hips] вперед, чтобы прижать [npc.her] пах к [npc2.nameHers], [npc.name] [npc1.verb(let)] [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)] на пути к [npc1.her] кульминации.",
			false,
			SexSlotTag.SCISSORING);

	public static final SexSlot SCISSORING_THREE = new SexSlot(
			"Скрайсинг",
			"ножницы (3-я)",
			"Отчаянно толкая [npc.her] [npc.hips] вперед, чтобы прижать [npc.her] пах к [npc2.nameHers], [npc.name] [npc1.verb(let)] [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)] на пути к [npc1.her] кульминации.",
			false,
			SexSlotTag.SCISSORING);

	public static final SexSlot SCISSORING_FOUR = new SexSlot(
			"Скрайсинг",
			"ножницы (4-я)",
			"Отчаянно толкая [npc.her] [npc.hips] вперед, чтобы прижать [npc.her] пах к [npc2.nameHers], [npc.name] [npc1.verb(let)] [npc1.a_moan+], пока [npc1.she] [npc1.verb(prepare)] на пути к [npc1.her] кульминации.",
			false,
			SexSlotTag.SCISSORING);
	
	

	public static final SexSlot BESIDE = new SexSlot(
			"Рядом",
			"beside",
			"С резким [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(reach)] [npc.her] к кульминации.",
			false); // SexSlotTag.LYING_DOWN Removed in v0.4.11.2
	public static final SexSlot BESIDE_TWO = new SexSlot(BESIDE) {
		@Override
		public String getDescription() {
			return "рядом (2-й)";
		}
	};
	public static final SexSlot BESIDE_THREE = new SexSlot(BESIDE) {
		@Override
		public String getDescription() {
			return "рядом (3-й)";
		}
	};
	public static final SexSlot BESIDE_FOUR = new SexSlot(BESIDE) {
		@Override
		public String getDescription() {
			return "рядом (4-й)";
		}
	};
}
