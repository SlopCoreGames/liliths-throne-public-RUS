package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.90
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisThighs {
	
	public static final SexAction THIGH_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Начать межбедное проникновение";
		}

		@Override
		public String getActionDescription() {
			return "Просунуть свой [npc.cock+] между бедрами [npc2.namePos] и начать трахать их.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно сжимая [npc2.legs+] [npc2.namePos] вместе, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] против [npc2.namePos] бедер, а затем медленно толкая [npc.her] [npc.hips] вперед и начиная трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her] [npc.cock], прижатый к [npc2.namePos] [npc2.legs+], и с медленным, размеренным нажимом, [npc.she] нежно [npc.verb(press)] [npc2.her], прежде чем начать трахать образовавшуюся щель."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью сжимая [npc2.legs+] [npc2.namePos] вместе, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] против [npc2.namePos] бедер, а затем алчно толкая [npc.her] [npc.hips] вперед и начиная трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her] [npc.cock] против [npc2.namePos] [npc2.legs+], после чего с нетерпением прижимая [npc2.her] бедра друг к другу и начиная трахать образовавшуюся щель."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо сжимая [npc2.legs+] [npc2.namePos] вместе, [npc.name] с силой [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] против [npc2.namePos] бедер, а затем яростно толкая [npc.her] [npc.hips] вперед и начиная трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her] [npc.cock], прижимая их к [npc2.namePos] [npc2.legs+], после чего он грубо сжимает [npc2.her] бедра и начинает агрессивно трахать образовавшуюся щель."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc2.namePos] [npc2.legs+] друг к другу, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] к [npc2.namePos] бедрам, после чего он толкает [npc.her] [npc.hips] вперед и начинает трахать образовавшуюся щель.",

							"[npc.Name] [npc.verb(position)] [npc.cockHead+] [npc.her] [npc.cock], прижимая их к [npc2.namePos] [npc2.legs+], после чего он сжимает [npc2.her] бедра и начинает трахать образовавшуюся щель."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.legs], мягко подаваясь назад навстречу [npc.herHim], в то время как [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.cock+] еще глубже между [npc2.her] бедрами.",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] плавно подается назад, прижимаясь к промежности [npc.namePos], вонзая [npc.her] [npc.cock+] еще глубже между [npc2.her] бедрами."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.legs], жадно подаваясь назад навстречу [npc.herHim], в то время как [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.cock+] еще глубже между [npc2.her] бедрами.",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] с нетерпением подается назад навстречу паху [npc.namePos], отчаянно помогая [npc.her] [npc.cock+] погрузиться еще глубже между [npc2.her] бедер."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.legs], яростно толкаясь навстречу [npc.herHim], пока [npc2.she] грубо [npc2.verb(force)] [npc.cock+] еще глубже между [npc2.her] бедер.",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно подается назад навстречу паху [npc.namePos], грубо заставляя [npc.herHim] [npc.her] [npc.cock+] погрузиться еще глубже между [npc2.her] бедер."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.legs], жадно подаваясь назад навстречу [npc.herHim], в то время как [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.cock+] еще глубже между [npc2.her] бедрами.",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] с нетерпением подается назад навстречу паху [npc.namePos], отчаянно помогая [npc.her] [npc.cock+] погрузиться еще глубже между [npc2.her] бедер."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.legs], подается назад навстречу [npc.herHim], пока [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.cock+] еще глубже между [npc2.her] бедер.",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] подается назад навстречу паху [npc.namePos], помогая [npc.her] [npc.cock+] погрузиться еще глубже между [npc2.her] бедер."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.legs], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратился.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], пытаясь отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] глубоко втискивается между [npc2.her] бедрами."));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)], жадно толкая [npc2.her] [npc2.legs+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+], скользя туда-сюда между [npc2.her] бедрами.",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+], входя и выходя между [npc2.her] [npc2.legs+].",
		
							" В восторге от [npc2.Moaning], [npc2.name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips+] вперед-назад, умоляя о [npc.name], чтобы он продолжал трахать [npc2.her] [npc2.legs], пока движения [npc2.her] помогают [npc.her] [npc.cock+] скользить вверх и вниз между [npc2.her] бедрами."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы струятся по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] бедер.",
		
							" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name]; слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] бедер.",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] бедер."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] в ответ выгибает [npc2.her] [npc2.legs+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+], скользя туда-сюда между [npc2.her] бедер.",
		
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] туда-сюда между [npc2.her] [npc2.legs+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(buck)] двигает [npc2.her] [npc2.hips+] вперед-назад, умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.legs], пока движения [npc2.her] помогают [npc.her] [npc.cock+] скользить вверх и вниз между [npc2.her] бедер."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] мягко выгибает [npc2.her] [npc2.legs+] в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+], скользя туда-сюда между [npc2.her] бедер.",
		
							" Легкий [npc2.moan] струится из меж [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] туда-сюда между [npc2.her] [npc2.legs+], медленно вращая [npc2.her] [npc2.hips].",
		
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips+] туда-сюда, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.legs], пока [npc2.her] движения помогают [npc.her] [npc.cock+] скользить вверх-вниз между [npc2.her] бедрами."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ яростно [npc2.verb(start)] [npc2.her] [npc2.legs+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+], чтобы качать туда-сюда между [npc2.her] бедрами.",
		
							" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], и, грубо вращая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] туда-сюда между [npc2.her] [npc2.legs+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] властно [npc2.verb(buck)] [npc2.her] [npc2.hips+] туда-сюда, приказывая [npc.name] продолжать трахать [npc2.her] [npc2.legs], пока [npc2.her] движения помогают [npc.her] [npc.cock+] скользить вверх-вниз между [npc2.her] бедрами."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction THIGH_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный интеркруральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахать между [npc2.namePos] бедрами.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко проскальзывая [npc.her] [npc.cock+] между [npc2.namePos] бедрами, [npc.name] [npc.verb(start)] размеренно толкая [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Мягко проскальзывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] нежно толкая [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] мягко [npc.verb(fuck)] [npc2.her] бедра.",

					"Мягко сжимая [npc2.namePos] [npc2.legs+] вместе, [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)] плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] бедра."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Обычный межбедренный секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] бедра.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью проталкивая [npc.her] [npc.cock+] между [npc2.namePos] бедрами, [npc.name] [npc.verb(start)] энергично толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Отчаянно проталкивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] лихорадочно толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] бедра.",

					"Алчно сжимая [npc2.namePos] [npc2.legs+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] отчаянно вгоняет [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(fuck)] [npc2.her] бедра."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый межбедренный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать [npc2.namePos] бедра.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо втискивая [npc.her] [npc.cock+] между [npc2.namePos] бедер, [npc.name] властно [npc.verb(start)], толкая [npc.her] [npc.hips] туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Силой втискивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.legs+], [npc.name] яростно [npc.verb(start)], толкая [npc.her] [npc.hips] вперед и издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] бедра.",

					"Властно сжимая [npc2.namePos] [npc2.legs+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с силой вбивает [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] бедра."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Обычный межбедренный секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] бедра.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Втискивая [npc.her] [npc.cock+] между [npc2.namePos] бедер, [npc.name] [npc.verb(start)], толкая [npc.her] [npc.hips] туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] алчно [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Втискивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] бедрами.",

					"Сжимая бедра [npc2.namePos] [npc2.legs+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы отчаянно двигать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(fuck)] [npc2.her] бедрами."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный межбедренный секс";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать [npc2.namePos] бедрами.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью проталкивая [npc.her] [npc.cock+] между [npc2.namePos] бедрами, [npc.name] [npc.verb(start)] энергично толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.legs].",

					"Отчаянно проталкивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.legs+], [npc.name] [npc.verb(start)] лихорадочно толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] бедра.",

					"Алчно сжимая [npc2.namePos] [npc2.legs+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы отчаянно двигать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(fuck)] [npc2.her] бедрами."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться межбедренному сексу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свои [npc.cock] от [npc2.namePos] [npc2.legs+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не сумев оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя [npc.her] протесты, [npc2.she] медленно [npc2.verb(thrust)] [npc2.her] [npc2.legs] назад против [npc.herHim] и нежно [npc2.verb(continue)] вдавливая [npc.her] [npc.cock+] между [npc2.her] бедрами.",

							"[npc.A_sob+] с силой вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя [npc.her] протесты, [npc2.name] решительно [npc2.verb(hold)] [npc.herHim] на месте, мягко прижимая [npc2.her] [npc2.legs] к [npc.her] паху, пока [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] между [npc2.her] бёдер.",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.herHim] отпустил [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] [npc.her] протесты, медленно растирая [npc2.herself] о [npc.herHim], пока [npc2.she] [npc2.verb(sink)] [npc.her] [npc.cock+] глубоко между [npc2.her] бёдер."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], так как, игнорируя [npc.her] протесты, [npc2.she] яростно [npc2.verb(thrust)] [npc2.her] [npc2.legs] обратно к [npc.herHim] и [npc2.verb(continue)] грубо втискивает [npc.her] [npc.cock+] между [npc2.her] бёдер.",

							"[npc.A_sob+] с силой вырывается из-за [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] властно удерживает [npc.herHim] на месте, яростно вжимая [npc2.her] [npc2.legs] в пах [npc.her], пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] бедрами.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]. [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] thighs."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттащить [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], в то время как, игнорируя протесты [npc.her], [npc2.she] с жадностью [npc2.verb(thrust)] [npc2.her] [npc2.legs] обратно к [npc.herHim] и стремительно [npc2.verb(continue)] вдавливает [npc.her] [npc.cock+] между [npc2.her] бедрами.",

							"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, совершенно игнорируя протесты [npc.her], [npc2.she] крепко удерживает [npc.herHim] на месте, жадно вдавливая [npc2.her] [npc2.legs] в пах [npc.her], пока [npc2.she] отчаянно [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] бедрами.",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.name] отпустил [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] протесты [npc.her], жадно втирая [npc2.herself] в [npc.herHim], пока [npc2.she] счастливо [npc2.verb(force)] [npc.her] [npc.cock+] глубоко между [npc2.her] бедрами."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction THIGH_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить межбедерный секс";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из между [npc2.namePos] бедер.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.cock+] из между [npc2.namePos] [npc2.legs+], [npc.name] властно [npc.verb(slide)] [npc.cockHead] [npc.her] [npc.cock] вверх и вниз по [npc2.her] бедрам в последний раз, прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз глубоко вонзившись между [npc2.namePos] [npc2.legs+], [npc.name] затем [npc2.verb(pull)] назад, завершая межбедерный секс."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Когда [npc.her] [npc.cock+] выскальзывает из между [npc2.namePos] [npc2.legs+], [npc.name] в последний раз шлепает [npc.cockHead] [npc.her] [npc.cock] по [npc2.her] бедрам, прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз глубоко втиснувшись между [npc2.namePos] [npc2.legs+], [npc.name] затем [npc2.verb(pull)] назад, завершая межбедрынный секс."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может не издать [npc2.sob+], когда [npc.name] отстраняется, а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставила [npc.herHim] в покое.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться против [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(beg)] остаться в одиночестве."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] назад, жаждая еще большего [npc.her] внимания.",
	
								" [npc2.A_moan+] выскальзывает из-под [npc2.namePos] [npc2.lips+], выдавая [npc2.her] похоть к [npc.namePos] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction USING_COCK_BETWEEN_THIGHS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Межбедринный секс";
		}

		@Override
		public String getActionDescription() {
			return "Засунуть [npc2.namePos] [npc2.cock+] между своих бедер.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С нежным [npc.moan], [npc.name] медленно [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по [npc.her] [npc.legs+], прежде чем мягко сжать [npc.her] бедра и втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Прицеливая [npc.her] [npc.legs+] к [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(push)] [npc.her] бедра вместе, мягко [npc.moaning], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по [npc.her] [npc.legs+], прежде чем отчаянно сжать [npc.her] бедра и втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Прицеливая [npc.her] [npc.legs+] к [npc2.namePos] [npc2.cock+], [npc.name] жадно [npc.verb(push)] [npc.her] бедра вместе, [npc.moaning+], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] грубо [npc.verb(force)] [npc2.namePos] [npc2.cock+] поверх [npc.her] [npc.legs+], прежде чем жадно сжать [npc.her] бедра и с силой втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Выравнивая [npc.her] [npc.legs+], чтобы [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(push)] [npc.her] бедра, пока [npc.moaning+] [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] поверх [npc.her] [npc.legs+], прежде чем отчаянно сжать [npc.her] бедра и втиснуть [npc2.her] [npc2.cock] в образовавшуюся ложбинку.",

							"Прицеливая [npc.her] [npc.legs+] к [npc2.namePos] [npc2.cock+], [npc.name] жадно [npc.verb(push)] [npc.her] бедра вместе, [npc.moaning+], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] в образовавшуюся ложбинку."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], плавно толкая [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] трахать [npc.her] бедра.",
	
								" С мягким [npc2.moan], [npc2.name] плавно [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(sink)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], с нетерпением толкая [npc2.her] [npc2.hips] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] трахать [npc.her] бедра.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(sink)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], и, желая напомнить [npc.name], кто здесь главный, грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] вперед, после чего начинает безжалостно трахать [npc.her] бедра.",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] беспощадно [npc2.verb(start)] [npc.her] бедра."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], толкая [npc2.her] [npc2.hips] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] [npc.her] бедра.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед, и [npc2.verb(sink)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] между [npc.her] [npc.legs+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы отстраниться.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко между [npc.her] бедер."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.legs+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(fuck)] [npc.her] бедра.",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] глубоко вгоняет [npc2.her] [npc2.cock+] между [npc.namePos] бедрами.",
									
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.legs], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы втиснуть [npc2.her] [npc2.cock+] между [npc.her] [npc.legs+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.legs+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(fuck)] [npc.her] бедра.",
		
							" Тихий [npc2.moan] срывается с губ [npc2.namePos], прежде чем [npc2.she] нежно [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] между [npc.namePos] бедрами.",
									
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] неистово [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.legs+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(fuck)] [npc.her] бедрами.",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] грубо [npc2.verb(start)] вбивает [npc2.her] [npc2.cock+] глубоко между [npc.namePos] бедрами.",
									
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.legs+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(fuck)] [npc.her] бедрами.",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] глубоко вгоняет [npc2.her] [npc2.cock+] между [npc.namePos] бедрами.",
									
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.legs+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Мягко принять межбедный секс";
		}

		@Override
		public String getActionDescription() {
			return "Мягко использовать [npc2.namePos] [npc2.cock+], чтобы трахать твои бедра.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко выгибая [npc.her] [npc.hips], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.cock+] глубоко между [npc.her] [npc.legs+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] отодвигает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] входить и выходить между [npc.her] бедрами.",

					"Медленно толкая [npc.her] [npc.hips] назад, мягкий [npc.moan] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Принимать межбедный секс";
		}

		@Override
		public String getActionDescription() {
			return "Использовать [npc2.namePos] [npc2.cock+], чтобы трахать свои бёдра.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно выгибая [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между [npc.her] [npc.legs+].",

					"С помощью [npc.a_moan+], [npc.name] жадно [npc.verb(start)] вдавливает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] входить и выходить между [npc.her] бёдер.",

					"Отчаянно толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(drive)] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо принимать межбедный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо двигать [npc2.namePos] [npc2.cock+] вверх и вниз между своими бёдрами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно толкая [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между [npc.her] [npc.legs+].",

					"С помощью [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] входить и выходить между [npc.her] бёдер.",

					"Грубо толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] властно [npc.verb(drive)] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Принимать межбедный секс";
		}

		@Override
		public String getActionDescription() {
			return "Выдвинь свой [npc.legs+] навстречу [npc2.name], чтобы зажать [npc2.her] [npc2.cock] между бедрами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Дергаясь [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между [npc.her] [npc.legs+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(start)] вдавливает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] входить и выходить между [npc.her] бедрами.",

					"Толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] [npc.verb(drive)] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_BETWEEN_THIGHS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно принимать межбедренный секс";
		}

		@Override
		public String getActionDescription() {
			return "Жадно выпячивай свой [npc.legs+] навстречу [npc2.name], пока [npc2.her] [npc2.cock] вклинивается между твоими бедрами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно выгибая [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко между [npc.her] [npc.legs+].",

					"С помощью [npc.a_moan+], [npc.name] жадно [npc.verb(start)] вдавливает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.cock+] входить и выходить между [npc.her] бёдер.",

					"Отчаянно толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(drive)] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_BETWEEN_THIGHS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться межбедренному сексу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.legs+] от [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают наворачиваться на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] нежно трахает [npc.her] бедра.",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.legs] от [npc2.namePos] [npc2.cock]; она в отчаянии борется, пока [npc2.she] [npc2.verb(continue)] медленно скользит туда-сюда между [npc.her] бедрами.",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.legs] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] плавно скользит глубоко между [npc.her] бедрами."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] начинают наполнять [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] с жадностью [npc2.verb(continue)] [npc.her] бедра.",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.legs] от [npc2.namePos] [npc2.cock]; она в отчаянии борется, пока [npc2.she] [npc2.verb(continue)] жадно скользит туда-сюда между [npc.her] бедрами.",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.legs] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] жадно скользит глубоко между [npc.her] бедрами."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают наполнять [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] грубо [npc2.verb(continue)] в [npc.her] бедра.",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.legs] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] грубо [npc2.verb(continue)] между [npc.her] бедер.",

							"Отчаянно пытаясь оттащить [npc.her] [npc.legs] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] грубо [npc2.verb(continue)] глубоко между [npc.her] бедер."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить межбедерный секс";
		}

		@Override
		public String getActionDescription() {
			return "Заставьте [npc2.name] вытащить [npc2.her] [npc2.cock] из ваших бедер.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.cock] из между [npc.her] бедер, [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы остановиться.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из между [npc.her] бедер."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.cock] из между [npc.her] бедер, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.herHim], чтобы остановиться.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем выскользнуть [npc2.her] [npc2.cock] из-под [npc.her] бедер."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] облегченно вздыхает, но вскоре этот вздох переходит в [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)] понимает, что [npc.name] [npc.has] еще не закончил с [npc2.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.her] бедра."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
