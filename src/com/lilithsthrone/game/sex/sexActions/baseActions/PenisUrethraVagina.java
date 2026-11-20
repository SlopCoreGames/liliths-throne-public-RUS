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
public class PenisUrethraVagina {
	
	public static final SexAction VAGINAL_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Вагинальный уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.cock+] в [npc2.namePos] [npc2.vaginaUrethra+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно вонзиться вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и мощным толчком, [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.vaginaUrethra+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] между [npc2.namePos] [npc2.labia+], и легким толчком, [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], после чего он плавно выталкивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.vaginaUrethra+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] плавно выгибает [npc2.her] [npc2.hips], погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего он яростно выталкивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.vaginaUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно вбивает [npc2.her] [npc2.hips], грубо заставляя [npc.name] погружаться [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего он с жадностью вбивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.vaginaUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] с жадностью вбивает [npc2.her] [npc2.hips], отчаянно помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего он вбивает [npc2.her] [npc2.hips], чтобы вогнать его еще глубже в [npc2.her] [npc2.vaginaUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] вбивает [npc2.her] [npc2.hips], помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.vaginaUrethra+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)] тщетно пытается отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока незваный [npc.namePos] [npc.cock] глубоко вжимается в [npc2.her] [npc2.vaginaUrethra+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.cock+] глубоко погрузиться в [npc2.her] [npc2.vaginaUrethra+].",
		
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+], жадно умоляя [npc.name] продолжать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] слабо [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил [npc2.her] из [npc2.vaginaUrethra+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил [npc2.her] [npc2.vaginaUrethra+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)] погружает [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.vaginaUrethra+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, резко выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, умоляя о [npc.name] и прося продолжать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.vaginaUrethra+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибаясь под [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя о [npc.name] и прося продолжать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] чертовски [npc2.herHim].",
	
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.herHim], пока [npc2.her] движения заставляют [npc.her] [npc.cock+] глубоко войти в [npc2.her] [npc2.vaginaUrethra+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction VAGINAL_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Плавно вводи свой [npc.cock] в [npc2.namePos] и выводи из [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] покачивая [npc.her] [npc.hips] взад-вперед, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",

					"Скользя [npc.her] [npc.cock+] в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать входить своим [npc.cock+] в [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] с энтузиазмом [npc.verb(start)] покачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом проталкивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Мощно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо вгоняй свой [npc.cock+] в [npc2.namePos] [npc2.vaginaUrethra+] туда-сюда.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] грубо швыряя [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.name].",

					"Яростно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] грубо вбивая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",

					"Силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо вколачивает [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] раскачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(start)] совершает толчки [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный уретральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Жадно входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] с энтузиазмом [npc.verb(start)] покачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом проталкивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Мощно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться уретральному сексу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.cock] из [npc2.namePos] [npc2.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] нежное объятие, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.vaginaUrethra+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)] попыталась оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно затолкнуть обратно в [npc2.her] [npc2.vaginaUrethra+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)] попыталась оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] мягко [npc2.verb(force)] [npc2.her] [npc2.vaginaUrethra+] вниз на [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] пытается вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] грубо хватает его, прежде чем агрессивно втиснуть обратно в [npc2.her] [npc2.vaginaUrethra+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)] попыталась оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо затолкнуть обратно в [npc2.her] [npc2.vaginaUrethra+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.vaginaUrethra+] вниз на [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает его, прежде чем жадно втиснуть обратно в [npc2.her] [npc2.vaginaUrethra+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно втиснуть обратно внутрь [npc2.her] [npc2.vaginaUrethra+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.vaginaUrethra+] вниз на [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction VAGINAL_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить уретральный трах";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.vaginaUrethra+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдернув [npc.her] [npc.cock+] из [npc2.namePos] [npc2.pussy+], [npc.name] властно [npc.verb(slide)] [npc.cockTip+] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.vaginaUrethra+], завершая этот жесткий трах."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.cock] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] [npc.cockTip] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.vaginaUrethra+], завершая трах."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] из [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.vaginaUrethra], и [npc2.she] [npc2.verb(continue)] плачет и протестует, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющегося [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.vaginaUrethra+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.vaginaUrethra+], жаждая еще большего внимания [npc.her].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Вагинальная уретра трахается";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.cock+] в свою [npc.vaginaUrethra+] и получай удовольствие от секса.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его до [npc.her] [npc.labia+], издавая легкий [npc.moan], прежде чем нежно подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vaginaUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.vaginaUrethra+], прежде чем медленно оттолкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] жадно [npc.verb(guide)] его до [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем отчаянно подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vaginaUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.vaginaUrethra+], прежде чем жадно толкнуть [npc.her] [npc.hips] назад и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его вверх к [npc.her] [npc.labia+], издавая [npc.a_moan+], а затем с силой вбивает [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] [npc.vaginaUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его вверх к [npc.her] [npc.vaginaUrethra+], он с жадностью вбивает [npc.her] [npc.hips] обратно, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его вверх к [npc.her] [npc.labia+], издавая [npc.a_moan+], после чего он резко вбивает [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] [npc.vaginaUrethra+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его вверх к [npc.her] [npc.vaginaUrethra+], он с силой вталкивает [npc.her] [npc.hips] обратно, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.cock] вперед, чтобы [npc2.she] [npc2.verb(start)] трахать [npc.namePos] [npc.vaginaUrethra+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.vaginaUrethra+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] наружу, пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед, в то время как [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.namePos] [npc.vaginaUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.vaginaUrethra+], пока [npc2.she] энергично [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] наружу, пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.cock] вперед и [npc2.verb(start)] безжалостно трахать [npc.her] [npc.vaginaUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] безжалостно [npc2.verb(start)] трахает [npc.namePos] [npc.vaginaUrethra+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] наружу, пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед, в то время как [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.vaginaUrethra+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.vaginaUrethra+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.cock+] из [npc.her] [npc.vaginaUrethra+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] на [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.vaginaUrethra+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.pussy], [npc2.name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться на свободу.",
		
							" [npc2.A_sob+] с силой вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы вогнать [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" Легкий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)], скользя [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)], вонзая [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.vaginaUrethra+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.vaginaUrethra+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный трах уретры";
		}

		@Override
		public String getActionDescription() {
			return "Позвольте, чтобы ваш [npc.vaginaUrethra+] нежно оттрахал(а) [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"При мягком толчке [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу тихий [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)], выгибая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.vaginaUrethra+].",

					"Медленно вращая [npc.her] [npc.hips], мягкий [npc.moan] вытекает из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретра трахается";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахайте свой [npc.vaginaUrethra+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"При жадном выгибании [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] вбивает [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.vaginaUrethra+].",

					"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Уретра грубо трахается";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свой [npc.vaginaUrethra+] о [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С яростным ударом [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивает [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.vaginaUrethra+].",

					"Сильно вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Уретра трахается";
		}

		@Override
		public String getActionDescription() {
			return "Трахни свой [npc.vaginaUrethra+] о [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] [npc.hips], [npc.name] [npc.verb(let)] из [npc.a_moan+], заставляя [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] дергается, вбивая [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.vaginaUrethra+].",

					"Вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаемая уретра";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахайте свой [npc.vaginaUrethra+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"При жадном выгибании [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] вбивает [npc.her] [npc.hips], заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.vaginaUrethra+].",

					"Энергично вращая [npc.her] [npc.hips], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.vaginaUrethra+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляющаяся уретра";
		}

		@Override
		public String getActionDescription() {
			return "Попытайся оттянуть свой [npc.vaginaUrethra+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливается смазка, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.vaginaUrethra+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно входит и выходит из [npc.her] [npc.vaginaUrethra+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] нежно [npc2.verb(continue)] скользит глубоко в [npc.her] [npc.vaginaUrethra+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливается смазка, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.vaginaUrethra+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] неистово входит и выходит из [npc.her] [npc.vaginaUrethra+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.vaginaUrethra+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливается смазка, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.vaginaUrethra+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вколачивается в [npc.her] [npc.vaginaUrethra+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.vaginaUrethra+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_VAGINAL_URETHRA_SEX_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить трах через уретру";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.cock] из твоей [npc.vaginaUrethra+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.cock] из [npc.her] [npc.vaginaUrethra+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent] перед тем, как резко выдернуть [npc2.her] [npc2.cock] из [npc.her] [npc.vaginaUrethra+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc2.namePos] [npc2.cock] из [npc.her] [npc.vaginaUrethra+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent] перед тем, как плавно выскользнуть [npc2.her] [npc2.cock] из [npc.her] [npc.vaginaUrethra+]."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] от траханья [npc.her] [npc.vaginaUrethra+].",
	
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.vaginaUrethra+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
