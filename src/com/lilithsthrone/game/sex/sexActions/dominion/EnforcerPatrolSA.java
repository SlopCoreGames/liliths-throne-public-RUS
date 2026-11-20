package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.EnforcerAlleywayDialogue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.8.3
 * @version 0.3.8.3
 * @author Innoxia
 */
public class EnforcerPatrolSA {

	public static final SexAction DEMON_TF_REACTION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())
					&& !EnforcerAlleywayDialogue.isDemonRevealed()
					&& Main.game.getPlayer().getRace()==Race.DEMON;
		}
		@Override
		public String getActionTitle() {
			return "Реакция на демона";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getDescription() {
			return "Увидев, как ты самопроизвольно превращаешься в свою демоническую форму, [npc.name] в шоке ахает и восклицает: [npc.speechNoExtraEffects(Ты демон?! Прости, [pc.maam], я... я не знала!)]<br/><br/>Отталкиваясь от [npc.race], ты стонешь: [pc.speech(Давай, не останавливайся! Продолжай обращаться со мной как с грязным маленьким человеком!)]<br/><br/>Следуя указаниям [npc.sheIs], Enforcer продолжает твой «досмотр полостей», [npc.moaning], [npc.speech(Если ты этого хочешь, то пусть будет так...)]";
		}
		@Override
		public void applyEffects() {
			EnforcerAlleywayDialogue.setDemonRevealed(true);
		}
	};
}
