package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class CitadelYoukoSA {
	
	public static final SexAction ORGASM_DENIED = new SexAction(
			SexActionType.ORGASM_DENIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Отказ";
		}

		@Override
		public String getActionDescription() {
			return "Как только ты готовишься достичь пика, [citadelArcanist.name] внезапно удерживает тебя на месте, заставляя успокоиться!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Takahashi.class))
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotGeneric.MISC_WATCHING;
		}

		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(feel)] отчаянный жар, поднимающийся в паху [npc.her], и, когда [npc.she] [npc.verb(prepare)] к оргазму, [npc.she] [npc.verb(cry)], [npc.speech(Да! Я сейчас кончу!)]<br/><br/>Услышав это, [citadelArcanist.name] издает забавный смешок, [citadelArcanist.speechNoEffects(Глупышка! Разве я не говорила, что тебе нельзя кончать?!)]<br/><br/>Прежде чем [npc.name] успевает что-либо сказать, [citadelArcanist.name] использует свою силу Арканы, чтобы телекинезом заставить [npc.herHim] замереть. [npc.Name] [npc.verb(try)], отчаянно притираясь [npc.her] к [citadelArcanist.namePos], но хитрая ёко просто отступает назад и терпеливо ждет, пока волна оргазма у [npc.namePos] не утихнет. Как только становится ясно, что [npc.sheHas] успокоилась, [citadelArcanist.name] отпускает [npc.herHim] и, прижимая свою киску обратно к [npc.her], дразнит: [citadelArcanist.speechNoEffects(Глупенький! Всё это ради моего удовольствия, а не твоего!)]";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};

}
