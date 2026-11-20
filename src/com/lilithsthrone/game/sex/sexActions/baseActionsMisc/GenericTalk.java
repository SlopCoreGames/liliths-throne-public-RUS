package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.3
 * @version 0.4.2.1
 * @author Innoxia
 */
public class GenericTalk {

	public static final SexAction STOP_RAPE_PLAY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive()
							&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST)))
					&& !Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING
					&& !Main.sex.isSexPaceForced(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).getLustLevel().isResistingFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			return "Прекратить игру в изнасилование";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Поскольку твой рот забит, ты не можешь сказать [npc2.name], чтобы тот перестал притворяться сопротивляющимся, но ты все еще можешь издать неодобрительный звук, чтобы дать [npc2.her] понять, что ты хочешь, чтобы [npc2.herHim] прекратил это.";
			}
			return "Сказать [npc2.name], чтобы тот перестал притворяться сопротивляющимся.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("Хотя рот [npc.namePos] забит, [npc.sheIs] все же может издать неодобрительный звук, давая понять [npc2.name], что [npc2.she] пора перестать притворяться, будто он сопротивляется.");
				
			} else {
				sb.append("Не довольный поведением [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim] самым недвусмысленным образом, чтобы тот перестал притворяться сопротивляющимся.");
			}

			sb.append(" Хотя [npc2.she] [npc2.verb(let)] разочарованный [npc2.moan], [npc2.name] [npc2.verb(decide)] согласиться на просьбу [npc.namePos] и [npc2.verb(put)] игру в изнасилование.");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction ALLOW_RAPE_PLAY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive()
						|| Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.game.isNonConEnabled()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					&& !Main.sex.isSexPaceForced(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.isCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_NON_CON_SUB);
		}
		@Override
		public String getActionTitle() {
			return "Продолжить игру в изнасилование";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Хотя твой рот забит, ты все еще можешь издать многозначительный звук, чтобы дать [npc2.her] понять, что [npc2.she] может притворяться сопротивляющимся, если [npc2.she] того захочет.";
			}
			return "Сказать [npc2.name], что [npc.she] может притворяться сопротивляющимся, если [npc2.she] захочет.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("Хотя рот [npc.namePos] забит, [npc.sheIs] все еще может издать многозначительный звук, сигнализируя [npc2.name], что [npc2.she] может притворяться сопротивляющимся, если [npc2.she] [npc2.verb(want)].");
				
			} else {
				sb.append("Желая добавить немного извращенности, [npc.name] [npc.verb(tell)] [npc2.name], что [npc2.she] может притворяться сопротивляющейся, если [npc2.she] [npc2.verb(want)].");
			}

			sb.append(" Издавая возбужденный [npc2.moan], [npc2.name] [npc2.verb(take)] мгновение, чтобы решить, стоит ли и дальше притворяться, что она сопротивляется...");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction ROUGH_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DOMINANT) || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Угрожающее рычание";
			}
			return "Унизительные слова";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Поскольку твой рот забит, ты не можешь сказать [npc2.name], что [npc2.sheIs] — твоя маленькая секс-игрушка, но ты все еще можешь издать агрессивное рычание, чтобы дать [npc2.her] понять, что ты настроен серьезно.";
			}
			return "Сказать [npc2.name], что [npc2.sheIs] — твоя маленькая секс-игрушка.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"Поскольку рот [npc.namePos] забит и [npc.sheIs] не может говорить, но все же желая дать [npc2.name] звуковое напоминание о том, что [npc2.sheIs] — [npc.her] сучка, [npc.she] [npc.verb(let)] особенно агрессивное рычание.",
						"Из-за того, что рот [npc.her] сейчас забит и [npc.nameIsFull] не может говорить, она вместо этого [npc.verb(decide)] издать глубокое, угрожающее рычание, чтобы дать [npc2.name] понять, что [npc2.sheIs] все еще [npc.her] никчемная сучка.",
						"Несмотря на то, что [npc.her] сейчас не может говорить, [npc.name] [npc.verb(let)] угрожающее рычание, давая [npc2.name] понять, что [npc2.sheIs] будет обращаться с ней как с жалкой подстилкой.",
						"Хотя рот [npc.her] забит, из-за чего [npc.herHim] не может говорить, это не мешает [npc.nameIsFull] издать самый угрожающий рык, на который только способна [npc.she], давая [npc2.name] понять, что с [npc2.sheIs] будут обращаться как с покорной сучкой."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isPositive()) {
					sb.append(UtilText.returnStringAtRandom(
							"Со злой ухмылкой, [npc.name] [npc.verb(snarl)] на [npc2.name], ",
							"Голос [npc.NamePos] так и сочится садистским восторгом, пока [npc.she] [npc.verb(snarl)], "));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"Ухмыляясь [npc.herself], [npc.name] [npc.verb(snarl)] на [npc2.name], ",
							"[npc.Name] принимает самый властный голос [npc.her], когда [npc.she] [npc.verb(snarl)], "));
				}
				
				sb.append(Main.sex.getRoughTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SUBMISSIVE_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SUBMISSIVE);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Покорный скулеж";
			}
			return "Покорные речи";
		}
			
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getLastUsedPlayerAction()==ROUGH_TALK && Math.random()<0.5f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Поскольку твой рот забит, ты не можешь сказать [npc2.name], что ты — [npc2.her] покорная шлюха, но всё же можешь издать жалобный скулеж, чтобы дать [npc2.her] понять, что ты всё еще [npc2.her] верная сучка.";
			}
			return "Сказать [npc2.name], что ты — [npc2.her] покорная шлюха и хочешь, чтобы тебя трахали как никчемную сучку.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"Так как рот [npc.namePos] забит и [npc.sheIs] не может говорить, но всё же желая подать [npc2.name] звуковой сигнал о том, что [npc.sheIs] наслаждается [npc.herself], [npc.she] [npc.verb(let)] особенно жалкий скулеж.",
						"Из-за того, что рот [npc.her] сейчас забит, [npc.nameIsFull] не может говорить, и вместо этого [npc.verb(decide)] невероятно покорный, похотливый стон, давая [npc2.name] понять, что [npc.sheIs] наслаждается [npc.herself].",
						"Не смущаясь текущей неспособности [npc.her] говорить, [npc.name] [npc.verb(let)] жалкий, похотливый стон, давая [npc2.name] понять, что [npc.sheIs] рада оставаться [npc2.her] покорной сучкой.",
						"Хотя рот [npc.her] забит, из-за чего [npc.herHim] не может говорить, [npc.nameIsFull] не упускает возможности издать самый жалкий стон, на который только способна [npc.she], давая [npc2.name] понять, что [npc.sheIs] наслаждается тем, что она [npc2.her] покорная сучка."));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] и [npc.verb(try)], стараясь выглядеть как можно более покорной, пока [npc.she] [npc.verb(cry)], ",
						"[npc.Name] [npc.verb(put)] на [npc.her] самом невинном выражении лица, прежде чем [npc.moaning], "));
	
				sb.append(Main.sex.getSubmissiveTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction LOVING_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Любящий вздох";
			}
			return "Любовное признание";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Поскольку ваш рот забит, вы не можете сказать [npc2.name], что любите [npc2.herHim], но все же можете издать нежный гудящий звук, чтобы дать [npc2.her] понять ваши чувства.";
			}
			return "Нежно сказать [npc2.name], что вы любите [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"Так как рот [npc.namePos] забит и [npc.sheIs] не может говорить, но все же желая напомнить [npc2.name] о [npc.her] чувствах, [npc.she] [npc.verb(let)] особенно нежный вздох.",
						"Поскольку рот [npc.her] сейчас чем-то забит, [npc.nameIsFull] не может говорить, и вместо этого [npc.verb(decide)] тихий, мечтательный вздох, чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(love)] [npc2.herHim].",
						"Не смущаясь нынешней неспособности [npc.her] говорить, [npc.name] [npc.verb(let)] нежный, тоскующий вздох, давая понять [npc2.name], что [npc.she] [npc.verb(love)] [npc2.herHim]."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"С любящей улыбкой на лице [npc.her],",
						"Любовно глядя на [npc2.name],"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.name] тихо",
						" [npc.name] нежно"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.verb(sigh)], ",
						" [npc.verb(whisper)], "));
				
				sb.append(Main.sex.getLovingTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction LOVING_REPLY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isLovingAction() {
			return true;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getLastUsedSexAction(Main.sex.getCharacterTargetedForSexAction(this))==LOVING_TALK;
		}
		@Override
		public String getActionTitle() {
			return "Любящий ответ";
		}
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().isPlayer() && Math.random()<0.8f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Так как ваш рот занят, вы не можете сказать [npc2.name], что тоже любите [npc2.herHim], но все же можете издать нежный вздох, чтобы дать [npc2.her] понять ваши чувства.";
			}
			return "Сказать [npc2.name], что вы тоже любите [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"Так как рот [npc.namePos] забит и [npc.sheIs] не может говорить, но, все же желая сказать [npc2.name], что [npc2.her] чувства взаимны, [npc.she] [npc.verb(let)] глубокий, полный любви стон.",
						"Поскольку рот [npc.her] сейчас заблокирован, [npc.nameIsFull] не может говорить, и вместо этого [npc.verb(decide)] нежный стон, давая [npc2.name] понять, что [npc.she] тоже [npc.verb(love)] [npc2.herHim].",
						"Несмотря на то, что [npc.her] сейчас не может говорить, [npc.name] [npc.verb(let)] нежный стон, давая [npc2.name] понять, что [npc.she] тоже [npc.verb(love)] [npc2.herHim]."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"Счастливо улыбаясь [npc2.name],",
						"Издавая счастливый стон,"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.name] с любовью",
						" [npc.name] страстно"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.verb(reply)], ",
						" [npc.verb(respond)], "));
				
				sb.append(Main.sex.getLovingResponseTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction ASKING_FOR_ROUGH_SEX = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Выглядеть маленькой";
			}
			return "Просить грубого секса";
		}

		private boolean isAcceptingRequest() {
			return Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())
//					|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()
					|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isPositive()
					|| Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS);
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Поскольку ваш рот забит, вы не можете напрямую попросить [npc2.name] обращаться с вами [npc2.herHim] грубее. Вместо этого вы можете попытаться выглядеть как можно более маленьким и жалким, чтобы соблазнить [npc2.herHim] на это.<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Требуется, чтобы [npc2.name] любил(а) '"+Fetish.FETISH_DOMINANT.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' фетиш, такой как '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' фетиш, или чтобы у тебя был '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' черта активирована.)]";
			}
			return "Попросить [npc2.name] относиться к тебе как к [npc2.her] никчемной шлюхе.<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Требуется, чтобы [npc2.name] любил(а) '"+Fetish.FETISH_DOMINANT.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' фетиш, такой как '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' фетиш, или чтобы у тебя был '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' черта активирована.)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.DOM_ROUGH
					&& (Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_MASOCHIST) || Main.sex.getCharacterPerformingAction().isPlayer());
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.her] mouth is currently blocked, [npc.name] can't make a direct request, so instead, [npc.she] [npc.verb(let)] out a pathetic whine and [npc.verb(try)] to make [npc.herself] look as small as possible in an attempt to entice [npc2.name] to start treating [npc.herHim] roughly.",
						"Стараясь выглядеть перед [npc.herself] как можно более жалкой и ничтожной, [npc.name] [npc.verb(let)] издает тонкий скулящий звук, пока [npc.she] [npc.verb(attempt)], чтобы заставить [npc2.name] обращаться с [npc.her] похуже.",
						"В отчаянии желая, чтобы с ней обращались как с никчемной сучкой, [npc.name] [npc.verb(try)], стараясь казаться [npc.herself] как можно меньше, и одновременно издавая жалкий, похотливый скулеж.",
						"Не получается говорить, так как рот [npc.her] забит; [npc.name] [npc.do] — лучшее, что [npc.she] может придумать, пытаясь заставить [npc2.name] обращаться с [npc.her] грубо, и [npc.verb(make)] [npc.herself] выглядит как можно более жалко и беспомощно, при этом издавая тонкий скулящий звук."));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"Приняв самый умоляющий вид, на который только был способен [npc.she], [npc.name] [npc.verb(beg)], чтобы [npc2.name] обращалась с [npc.herHim] грубо, ",
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] и [npc.verb(try)], стараясь выглядеть как можно невиннее, пока [npc.she] [npc.verb(beg)], "));
				
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(Ну же! Я твоя никчемная маленькая шлюшка! Обращайся со мной как с ней!)]",
						"[npc.speech(Трахни меня, как свою никчемную шлюшку! Пожалуйста!)]",
						"[npc.speech(Обращайся со мной как со своей грязной маленькой шлюшкой! Обожаю, когда грубо!)]",
						"[npc.speech(Ты же знаешь, что хочешь сломать меня! Трахни меня хорошенько, жестко! Ну же!)]"));
			}
			
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(isAcceptingRequest()) {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Усмехаясь от садистского восторга после твоей просьбы, [npc2.name] издает угрожающее рычание, когда [npc2.she] отвечает, ",
								"Издавая в ответ на твою просьбу глубокое рычание, [npc2.name] утвердительно отвечает, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(О, так вот чего ты хочешь, маленькая сучка?! Ладно, тогда я буду обращаться с тобой как с никчемной шлюхой, которой ты и являешься!)]",
							"[npc2.speech(Так вот чего ты на самом деле хочешь, да?! В таком случае, готовься к тому, что я буду обращаться с тобой как со своей никчемной шлюхой-игрушкой!)]",
							"[npc2.speech(Грязная маленькая шлюшка! Что ж, я с огромным удовольствием оттрахаю тебя до беспамятства!)]"));
					
				} else {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Нахмурив [npc2.her] брови, когда ты спрашиваешь об этом, [npc2.name] вздыхает, прежде чем [npc2.she] ответить, ",
								"Издавая разочарованный вздох в ответ на твою просьбу, [npc2.name] отвечает отрицательно, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(Прости, но я сейчас не в настроении для такого... Давай просто продолжим как есть, ладно?)]",
							"[npc2.speech(Мне не особо нравится подобное... Давай оставим всё как есть.)]",
							"[npc2.speech(Прости... Я не из тех, кто занимается подобным...)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Main.sex.setSexPace(Main.sex.getCharacterTargetedForSexAction(this), SexPace.DOM_ROUGH);
			}
		}
	};
	
	public static final SexAction ASKING_FOR_GENTLE_SEX = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Выглядеть обеспокоенно";
			}
			return "Возразить против грубости";
		}
		
		private boolean isAcceptingRequest() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_SADIST)
					&& (Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())
						|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()
						|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Поскольку твой рот занят, ты не можешь напрямую попросить [npc2.name], чтобы [npc2.herHim] обращалась с тобой нежно. Вместо этого ты можешь изобразить крайнюю обеспокоенность, пытаясь убедить [npc2.herHim] замедлиться.<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Требуется, чтобы [npc.name] любил(а) фетиш '"+Fetish.FETISH_SUBMISSIVE.getName(Main.sex.getCharacterTargetedForSexAction(this))+"', не любил(а) фетиш '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' фетиш, или чтобы у тебя был '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' черта активирована.)]";
			}
			return "Попросить [npc2.name] быть нежнее с тобой.<br/>"
					+ (isAcceptingRequest()
							?"[style.italicsGood("
							:"[style.italicsBad(")
					+"Требуется, чтобы [npc.name] любил(а) фетиш '"+Fetish.FETISH_SUBMISSIVE.getName(Main.sex.getCharacterTargetedForSexAction(this))+"', не любил(а) фетиш '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' фетиш, или чтобы у тебя был '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' черта активирована.)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.DOM_ROUGH
					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_MASOCHIST).isNegative() || Main.sex.getCharacterPerformingAction().isPlayer());
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"Поскольку рот [npc.her] сейчас занят, [npc.name] не может обратиться с прямой просьбой, поэтому вместо этого [npc.she] [npc.verb(raise)] [npc.her] брови и [npc.verb(let)] обеспокоенный стон, пытаясь убедить [npc2.name] замедлиться и быть нежнее с [npc.herHim].",
						"Приняв обеспокоенный вид, [npc.name] [npc.verb(let)] тревожный стон, пока [npc.she] [npc.verb(attempt)], чтобы передать [npc2.name], что [npc.she] [npc.verb(want)] более нежного обращения.",
						"Желая более нежного обращения, [npc.name] [npc.verb(raise)] [npc.her] брови, одновременно издавая беспокойный стон.",
						"Не имея возможности говорить из-за того, что рот [npc.her] занят, [npc.name] [npc.do] лучшее, что может придумать [npc.she], чтобы заставить [npc2.name] относиться к [npc.her] нежнее, и [npc.verb(put)] с тревожным видом, также издавая обеспокоенный стон."));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"Приняв самый умоляющий вид, на который только был способен [npc.she], [npc.name] [npc.verb(ask)] [npc2.name], чтобы тот успокоился, ",
						"[npc.Name] [npc.verb(raise)] [npc.her] брови и [npc.verb(put)] с обеспокоенным видом, пока [npc.she] [npc.verb(ask)], "));
				
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(Пожалуйста, можно не так грубо? Я бы хотела, чтобы это было чуть более нежно...)]",
						"[npc.speech(Помедленнее, пожалуйста! Неужели ты не можешь быть чуть нежнее?)]",
						"[npc.speech(Можешь относиться ко мне немного мягче? Для меня это слишком...)]",
						"[npc.speech(Пожалуйста, можешь замедлиться? Я бы хотела, чтобы ты был чуть нежнее...)]"));
			}

			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(isAcceptingRequest()) {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Счастливо улыбаясь на твою просьбу, [npc2.name] издает успокаивающий вздох, когда [npc2.she] отвечает, ",
								"Издавая тихий [npc.moan] в ответ на твою просьбу, [npc2.name] утвердительно отвечает, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(О, так вот чего ты хочешь, ммм? Я не против двигаться чуть медленнее...)]",
							"[npc2.speech(Меня это устраивает... Давай не будем спешить, ладно?)]",
							"[npc2.speech(Звучит как отличная идея... С этого момента я буду нежнее с тобой...)]"));
					
				} else {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"[npc2.her] нахмуривает брови, выслушав твой вопрос, и, издав цокающий звук, [npc2.name] отвечает, что [npc2.she], ",
								"издав неодобрительный цок в ответ на твою просьбу, [npc2.name] отвечает отрицательно, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(Ага, на эту нежную херню я не подписывалась. Тебе просто придется привыкнуть к тому, как это будет!)]",
							"[npc2.speech(Меня не особо прет вся эта нежная любовь, блядь. Тебе придется смириться с тем, как нравится мне.)]",
							"[npc2.speech(Ага, этого не будет. И не проси больше этой нежной любовной херни!)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Main.sex.setSexPace(Main.sex.getCharacterTargetedForSexAction(this), SexPace.DOM_GENTLE);
			}
		}
	};
	
}
