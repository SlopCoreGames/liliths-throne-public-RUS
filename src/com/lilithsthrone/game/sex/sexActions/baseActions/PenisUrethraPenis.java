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
 * @since 0.1.82
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisUrethraPenis {
	
	public static final SexAction PENILE_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Погрузите свой [npc.cock+] в [npc2.namePos] [npc2.penisUrethra+] и начните трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.penisUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.penisUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем с силой рвануться вперед, вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.penisUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.penisUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, вонзая [npc.her] [npc.cock+] в [npc2.her] [npc2.penisUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] о [npc2.cockHead+] [npc2.namePos] [npc2.penis+], и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], а затем плавно выталкивая [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.penisUrethra+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)], плавно выгибая [npc2.her] [npc2.hips], погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.penisUrethra+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.cock+] входит в [npc2.herHim], после чего яростно выталкивая [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.penisUrethra+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно дергается, вбивая [npc2.her] [npc2.hips] и грубо заставляя [npc.name] погружаться [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.penisUrethra+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.cock+] входит в [npc2.herHim], после чего с нетерпением выталкивая [npc2.her] [npc2.hips], чтобы погрузить его еще глубже в [npc2.her] [npc2.penisUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно дергает [npc2.her] [npc2.hips], отчаянно помогая [npc.namePos] [npc.cock+] погружаться еще глубже в [npc2.her] [npc2.penisUrethra+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.cock+] входит в [npc2.herHim], после чего дергаясь, чтобы погрузить [npc2.her] [npc2.hips] еще глубже в [npc2.her] [npc2.penisUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.her] [npc2.hips], помогая [npc.namePos] [npc.cock+] погружаться еще глубже в [npc2.her] [npc2.penisUrethra+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, пока слезы текут по [npc2.her] [npc2.face], а [npc.namePos] незваный [npc.cock] глубоко вжимается в [npc2.her] [npc2.penisUrethra+]."));
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
							" [npc2.Name] в ответ жадно [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], заставляя [npc.namePos] [npc.cock+] глубоко погружаться в [npc2.her] [npc2.penisUrethra+].",
		
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, умоляя [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают загнать [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] беспомощно [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил [npc2.her] [npc2.penisUrethra+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], а [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил [npc2.her] [npc2.penisUrethra+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил [npc2.her] [npc2.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], заставляя [npc.namePos] [npc.cock+] глубоко погрузиться в [npc2.her] [npc2.penisUrethra+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, резко выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)] нежно умоляя [npc.name] продолжать трахать [npc2.her] [npc2.penisUrethra+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь под [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] чертовски [npc2.herHim].",
	
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] прямо в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.herHim], пока [npc2.her] движения заставляют [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.penisUrethra+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction PENILE_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводи и выводи свой [npc.cock] в [npc2.namePos] [npc2.penisUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] раскачивает [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.penisUrethra+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",

					"Скользя [npc.her] [npc.cock+] в [npc2.namePos] [npc2.penisUrethra+], [npc.name] слегка [npc.verb(let)] [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай входить и выходить своим [npc.cock+] в [npc2.namePos] [npc2.penisUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.penisUrethra+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] грубо вколачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.name].",

					"Яростно вонзая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] грубо вбивая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",

					"С силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы грубо вколачивать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать трахать [npc2.namePos] [npc2.penisUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] покачивая [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"Вталкивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вонзая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Нетерпеливо вгоняй свой [npc.cock+] в [npc2.namePos] [npc2.penisUrethra+] и вынимай его.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться уретральному сексу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.cock] из [npc2.namePos] [npc2.penisUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.penisUrethra], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] нежно удерживает его, прежде чем мягко вдавить обратно в [npc2.her] [npc2.penisUrethra+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить обратно в [npc2.her] [npc2.penisUrethra+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.penisUrethra+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.penisUrethra+] вниз на [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.penisUrethra], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] грубо хватает его, прежде чем агрессивно вдавить обратно в [npc2.her] [npc2.penisUrethra+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с силой затолкнуть обратно в [npc2.her] [npc2.penisUrethra+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.penisUrethra+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.penisUrethra+] вниз на [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.penisUrethra], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] крепко сжимает его, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.penisUrethra+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.penisUrethra+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], вырывая [npc.her] [npc.cock] из [npc2.namePos] [npc2.penisUrethra+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.penisUrethra+] на [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENILE_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить уретральный трах";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.penisUrethra+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вырывая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.penisUrethra+], [npc.name] властно [npc.verb(slide)] [npc.cockTip+] вверх и вниз по [npc2.cockHead+] [npc2.her] [npc2.penis+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.penisUrethra+], кладя конец грубому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.cock] из [npc2.namePos] [npc2.penisUrethra+], [npc.name] [npc.verb(rub)] [npc.cockTip] вверх и вниз по [npc2.cockHead+] [npc2.her] [npc2.penis+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.penisUrethra+], кладя конец траху."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] из [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.penisUrethra], а [npc2.she] [npc2.verb(continue)] плачет и протестует, пока [npc2.she] [npc2.verb(carry)] слабо борясь против [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.penisUrethra+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.penisUrethra+], жаждая еще большего внимания [npc.her].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Уретра члена трахается";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.cock+] в свою [npc.penisUrethra+] и получай удовольствие.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его до [npc.cockHead+] [npc.her] [npc.penis+], издавая легкий [npc.moan], прежде чем мягко подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.penisUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.penisUrethra+], прежде чем медленно оттолкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] с жадностью [npc.verb(guide)] его до самого [npc.cockHead+] [npc.her] [npc.penis+], издавая [npc.a_moan+], прежде чем отчаянно выгнуться, [npc.her] [npc.hips] и заставить [npc2.herHim] войти в [npc.her] [npc.penisUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.penisUrethra+], прежде чем с жадностью толкнуть [npc.her] [npc.hips] назад и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его к [npc.cockHead+] [npc.her] [npc.penis+], издавая [npc.a_moan+], прежде чем яростно впечатать [npc.her] [npc.hips] и заставить [npc2.herHim] войти в [npc.her] [npc.penisUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.penisUrethra+], прежде чем с жадностью впечатать [npc.her] [npc.hips] назад и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его до самого [npc.cockHead+] [npc.her] [npc.penis+], издавая [npc.a_moan+], прежде чем выгнуться, [npc.her] [npc.hips] и заставить [npc2.herHim] войти в [npc.her] [npc.penisUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.penisUrethra+], затем толкнув [npc.her] [npc.hips] назад и издав [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], осторожно толкая [npc2.her] [npc2.cock] вперед, а [npc2.she] [npc2.verb(start)] с энтузиазмом трахая [npc.namePos] [npc.penisUrethra+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.penisUrethra+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед, а [npc2.she] [npc2.verb(start)] с энтузиазмом трахая [npc.namePos] [npc.penisUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.penisUrethra+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вгоняет [npc2.her] [npc2.cock] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.penisUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.penisUrethra+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.penisUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.penisUrethra+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.cock+] из [npc.her] [npc.penisUrethra+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.penisUrethra+]."));
						break;
					default:
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.penisUrethra+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.penisUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.penisUrethra], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.penisUrethra+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.penisUrethra+].",
		
							" Тихий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно проталкивает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.penisUrethra+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] неистово вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.penisUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.penisUrethra+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.penisUrethra+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный трах уретры";
		}

		@Override
		public String getActionDescription() {
			return "Позвольте [npc2.namePos] [npc2.cock+] нежно оттрахать ваш [npc.penisUrethra+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"При мягком толчке [npc.her] [npc.hips], [npc.name] [npc.verb(let)] нежную [npc.moan], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+].",

					"С мягким [npc.moan], [npc.name] плавно [npc.verb(start)], толкая [npc.her] [npc.hips] и заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.penisUrethra+].",

					"Медленно вращая [npc.her] [npc.hips], мягкая [npc.moan] вытекает из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретра трахается";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свой [npc.penisUrethra+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"При нетерпеливом толчке [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)], толкая [npc.her] [npc.hips] и заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.penisUrethra+].",

					"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Уретра грубо трахается";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свой [npc.penisUrethra+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С яростным ударом [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)], толкая [npc.her] [npc.hips] и заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.penisUrethra+].",

					"Мощно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретра трахается";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.penisUrethra+] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)], толкая [npc.her] [npc.hips] и заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.penisUrethra+].",

					"Вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаемая уретра";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свой [npc.penisUrethra+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"При нетерпеливом толчке [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)], толкая [npc.her] [npc.hips] и заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.penisUrethra+].",

					"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.penisUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляющаяся уретра";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть твой [npc.penisUrethra+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы, которые [npc.verb(start)] скапливаются в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.penisUrethra+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.penisUrethra] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно входит и выходит из [npc.her] [npc.penisUrethra+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы, которые [npc.verb(start)] скапливаются в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.penisUrethra+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.penisUrethra] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.penisUrethra+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в мучениях, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.penisUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы, которые [npc.verb(start)] скапливаются в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.penisUrethra+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.penisUrethra] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается то туда, то сюда в [npc.her] [npc.penisUrethra+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в мучениях, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_PENILE_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить трах через уретру";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.cock] из твоей [npc.penisUrethra+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.cock] из [npc.her] [npc.penisUrethra+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.penisUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.penisUrethra+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent] перед тем, как [npc2.her] [npc2.cock] выскользнет из [npc.her] [npc.penisUrethra+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] с облегченным вздохом, который вскоре сменяется [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] выплескивается из [npc2.a_moan+] как [npc.name] [npc.verb(stop)] [npc2.herHim] из ебаного [npc.her] [npc.penisUrethra+].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.penisUrethra+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
