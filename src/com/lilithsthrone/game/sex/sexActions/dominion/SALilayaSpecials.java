package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.7
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SALilayaSpecials {
	
	private static boolean isAmazonsSecretActive(GameCharacter targetedCharacter) {
		GameCharacter performingCharacter = Main.sex.getCharacterPerformingAction();
		if(targetedCharacter.hasStatusEffect("innoxia_amazons_secret")) {
			Set<GameCharacter> charactersContactingVagina = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaOrifice.VAGINA, SexAreaOrifice.VAGINA));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT));

			return !charactersContactingVagina.isEmpty();
		}
		
		return false;
	}
	
	// Demand pull out
	public static final SexAction PARTNER_DEMAND_PULL_OUT = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Напоминание об отстранении";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return "Проверка презерватива";
			}
			return "Напоминание о прерванном акте";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
						|| (Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
								&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()))
					&& Main.sex.getCharacterTargetedForSexAction(this).getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !Main.sex.getCharactersRequestingPullout().keySet().contains(Main.sex.getCharacterPerformingAction())
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "Издавая отчаянные стоны и похотливые крики, [npc.name] [npc.verb(try)], чтобы оттолкнуть [npc2.namePos] [npc2.hips] от [npc.her] [npc.pussy], давая понять, что [npc.she] не хочет, чтобы [npc2.herHim] кончил в этой позе из-за эффекта от выпитой бутылки «Секрета Амазонки».";
					
				} else {
					return "Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом удается выдавить из себя фразу, когда [npc.she] кричит [npc2.name]: [npc.speech(Отстраняйся, пока ты не кончил! Я знаю, что ты выпил бутылку «Секрета Амазонки», и я <b>не</b> забеременею!)]";
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "Издавая отчаянные стоны и похотливые крики, [npc.name] [npc.verb(try)], чтобы оттолкнуть [npc2.namePos] [npc2.hips] от [npc.her] [npc.pussy], давая понять, что [npc.she] не хочет, чтобы [npc2.herHim] кончил внутрь [npc.herHim].";
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						return "Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом удается выдавить из себя фразу, когда [npc.she] кричит [npc2.name]: [npc.speech(Презерватив надет правильно, да? Я <b>не</b> забеременею!)]";
					}
					return "Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом удается выдавить из себя фразу, когда [npc.she] кричит [npc2.name]: [npc.speech(Просто не забудь вовремя вытащить! Я <b>не</b> забеременею!)]";
				}
			}
				
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& !isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Приготовиться";
		}

		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Приготовьтесь к нему.";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					return "[npc.Name] издает мягкий [npc.moan] ободрения, пока [npc.she] готовится к тому, чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
				case DOM_NORMAL:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к тому, чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
				case DOM_ROUGH:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к тому, чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
				case SUB_EAGER:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к тому, чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
				case SUB_NORMAL:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к тому, чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
				case SUB_RESISTING:
					return "[npc.Name] издает [npc.a_moan+], пока [npc.she] отчаянно пытается отстраниться от [npc2.name] перед тем, как [npc2.she] [npc2.verb(orgasm)].";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Требовать отстраниться";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return "Успокоить насчет презерватива";
			}
			return "Требовать кончить внутрь";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
					|| (Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
							&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()))
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "Издавая отчаянные стоны и похотливые крики, [npc.name] [npc.verb(try)], чтобы оттолкнуть [npc2.namePos] [npc2.hips] от [npc.her] [npc.pussy], давая понять, что [npc.she] не хочет, чтобы [npc2.herHim] кончил в этой позе из-за эффекта от выпитой бутылки «Секрета Амазонки».";
					
				} else {
					return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом умудряется выдавить из себя фразу, когда [npc.she] вскрикивает, обращаясь к [npc2.name], [npc.speech(Эй, подожди! Если ты будешь кончать вот так, я же могу забеременеть!)]";
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "Издавая отчаянные стоны и похотливые крики, [npc.name] [npc.verb(try)], чтобы оттолкнуть [npc2.namePos] [npc2.hips] от [npc.her] [npc.pussy], давая понять, что [npc.she] не хочет, чтобы [npc2.herHim] кончил внутрь [npc.herHim].";
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом умудряется выдавить фразу, когда [npc.she] кричит [npc2.name], [npc.speech(Надеюсь, этот презерватив не порвется! Я <b>не</b> хочу забеременеть!)]";
					}
					return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом умудряется выдавить фразу, когда [npc.she] кричит [npc2.name], [npc.speech(Кончай в себя! Я <b>не</b> хочу забеременеть!)]";
				}
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	// Furious stop sex
	/**
	 * This should no longer ever be seen, as it was replaced by a catch for the player's orgasm override. I left it here just in case...
	 */
	public static final SexAction PARTNER_FURIOUS_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this)) || !Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return "Забеременела?!";
			}
			return "Кончил внутрь?!";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this)) || !Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return "[npc.Name] чувствует глубокое, пронзительное тепло, разливающееся по её [lilaya.pussy+] и поднимающееся к самой матке. Яростно отталкивая [npc2.name] от себя, она гневно кричит: [npc.speechNoEffects(Какого хера?! Я же говорила тебе кончать до!)]";
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaCondomBroke)) {
				return "[npc.Name] чувствует, как [npc2.namePos] [npc2.cum] вытекает из её [lilaya.pussy+], и, в панике отталкивая [npc2.herHim], она в отчаянии вскрикивает: [npc.speechNoEffects(Презерватив порвался! Нет! Блять! Я же могу от этого забеременеть!)]";
			}
			return "[npc.Name] чувствует, как [npc2.namePos] [npc2.cum] вытекает из её [lilaya.pussy+], и, яростно отталкивая [npc2.herHim] от себя, она гневно кричит: [npc.speechNoEffects(Какого хера?! Я же говорила тебе кончать до!)]";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
