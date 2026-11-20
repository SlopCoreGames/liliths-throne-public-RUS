package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.10
 * @version 0.4
 * @author Innoxia
 */
public class PenisFeet {

	// Foot tease
	
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Взять [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Просунь свой [npc.cock+] между [npc2.namePos] [npc2.feet] и начни трахать их.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(bringing)] [npc2.namePos] твердые копыта к бокам [npc.her] [npc.cock+], [npc.name] осторожно [npc.verb(press)] подошвы к [npc.her] гениталиям, прежде чем толкнуть [npc.her] [npc.hips] вперед и начать получать hoofjob.",
	
								"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her], прижимая [npc.cock] к твердым копытам [npc2.namePos], прежде чем осторожно сжать [npc2.her] подошвы вместе и начать получать копытный секс."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а).",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] скользит между [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], после чего [npc2.eagerly] скользят вверх и вниз вокруг [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly], скользящие [npc2.her] [npc2.feet] вверх и вниз по [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(curling)] [npc2.namePos] обвивает [npc.her] [npc.cock+], точно [npc.name] [npc.verb(make)], что [npc2.her] острые когти направлены в сторону от [npc.her], после чего [npc.her] [npc.hips] резко подается вперед, начиная принимать [npc2.footjob].",
	
								"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her], [npc.cock] прижатые к [npc2.namePos] подошвам, после чего [npc2.her] обвивает птичьими лапами [npc.her] [npc.cock+] и начинает принимать [npc2.footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а).",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] скользит между [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], после чего [npc2.eagerly] скользят вверх и вниз вокруг [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly], скользящие [npc2.her] [npc2.feet] вверх и вниз по [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(pushing)] [npc2.namePos] сжимает [npc2.feet+] вместе, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] к ним, после чего [npc.eagerly] резко вгоняет [npc.her] [npc.hips] вперед, начиная трахать образовавшуюся щель.",
										
								"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her], [npc.cock] прижатые к [npc2.namePos] [npc2.feet+], после чего [npc.eagerly] прижимает их друг к другу и начинает трахать образовавшуюся щель."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а).",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] скользит между [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], после чего [npc2.eagerly] скользят вверх и вниз вокруг [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] скользит [npc2.her] [npc2.feet] вверх и вниз вокруг [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(pushing)] [npc2.namePos] сжимает [npc2.feet+] вместе, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] к [npc2.namePos] подошвам, после чего [npc.eagerly] резко подает [npc.her] [npc.hips] вперед, начиная трахать образовавшуюся щель.",
	
								"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her] [npc.cock], прижимая их к [npc2.namePos] [npc2.feet+], после чего [npc.eagerly] прижимает [npc2.her] подошвы друг к другу и начинает трахать образовавшуюся щель."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а).",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] скользит между [npc2.her] [npc2.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.feet], после чего [npc2.eagerly] скользят вверх и вниз вокруг [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly] скользит [npc2.her] [npc2.feet] вверх и вниз вокруг [npc.namePos] [npc.cock+]."));
							break;
					}
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
							" [npc2.Name] с нетерпением [npc2.verb(start)], скользя [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] восторженный [npc2.footjob] [npc.herHim].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] с жадностью [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] восторженный [npc2.footjob]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] перестал использовать [npc2.her] [npc2.feet].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] отпустил [npc2.her] [npc2.feet].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] по [npc.name], умоляя и плача, чтобы [npc.herHim] убрался от [npc2.her] [npc2.feet]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] скользит [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim] [npc2.a_footjob].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] [npc2.a_footjob]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim], словно любящий [npc2.footjob].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] нежный [npc2.footjob]."));
					break;
				case DOM_ROUGH:
					if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)], грубо водя [npc2.her] [npc2.feet] вверх и вниз вокруг [npc.namePos] [npc.cock+] в ответ, совершенно не заботясь о том, что использование [npc2.her] твердых копыт столь яростным образом крайне неудобно для [npc.herHim].",
			
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], пока [npc2.she] с силой [npc2.verb(rub)] [npc2.her] твердыми копытами вверх и вниз по [npc.namePos] [npc.cock+], ни капли не заботясь о том, насколько неприятно принимать такое грубое [npc2.footjob].",
			
								" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her] твердыми копытами вверх и вниз вокруг [npc.namePos] [npc.cock+], насмешливо хохоча над [npc.herHim], пока [npc2.she] [npc2.verb(administer)] это неудобное, напористое [npc2.footjob]."));
						
					} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)], грубо водя [npc2.her] [npc2.feet] вверх и вниз вокруг [npc.namePos] [npc.cock+] в ответ, не обращая внимания на то, что [npc2.her] острые когти то и дело опасно подбираются к тому, чтобы полоснуть [npc.herHim].",
			
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], пока [npc2.she] с силой [npc2.verb(rub)] [npc2.her] птичьими когтями вверх и вниз по [npc.namePos] [npc.cock+], ни капли не заботясь о том, насколько близко [npc2.her] острые когти подходят к тому, чтобы располосовать [npc.herHim].",
			
								" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her], двигая птичьими лапами вверх и вниз вокруг [npc.namePos] [npc.cock+], насмешливо хохоча над [npc.herHim], пока [npc2.she] [npc2.verb(draw)] опасно близко к тому, чтобы полоснуть [npc.herHim] своими [npc2.her] острыми когтями."));
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ грубо [npc2.verb(start)], двигая [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim] мощным [npc2.footjob].",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] с силой [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+].",
			
							" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her] [npc2.feet+] вверх и вниз вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] мощный [npc2.footjob]."));
					}
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Нежный [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахать [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Плавно скользя [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet], [npc.name] steadily [npc.verb(start)] толкая [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(receive)] [npc2.a_footjob].",

					"Мягко просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(start)] плавно толкая [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(receive)] [npc2.a_footjob].",

					"Мягко сжимая [npc2.namePos] [npc2.feet+] вместе, [npc.name] [npc.verb(let)] слегка [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Обычный [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать трахать [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(start)] энергично вбивая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Отчаянно просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Жадно сжимая [npc2.namePos] [npc2.feet+] вместе, [npc.name] [npc.verb(let)] выдыхает [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы отчаянно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Грубый [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо втискивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(start)] доминантно качая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(receive)] [npc2.a_footjob].",

					"Сильно втискивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(start)] яростно вгоняя [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(receive)] [npc2.a_footjob].",

					"Доминантно сжимая [npc2.namePos] [npc2.feet+] вместе, [npc.name] [npc.verb(let)] выплескивая [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы с силой швырять [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Обычный [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать трахать [npc2.namePos] [npc2.feet].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Втискивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(start)] дергая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Втискивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(start)] вгоняя [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] счастливо [npc.verb(receive)] [npc2.a_footjob].",

					"Сжимая [npc2.namePos] [npc2.feet+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы отчаянно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Нетерпеливый [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Нетерпеливо трахать [npc2.namePos] [npc2.feet].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(start)] энергично вбивая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Отчаянно просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Алчно сжимая [npc2.namePos] [npc2.feet+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы отчаянно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.cock] от [npc2.namePos] [npc2.feet+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя [npc.her] протесты, [npc2.she] медленно [npc2.verb(thrust)] [npc2.her] [npc2.feet] обратно к [npc.herHim] и [npc2.verb(continue)] мягко заставляя [npc.herHim] принять [npc2.a_footjob].",

							"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя [npc.her] протесты, [npc2.name] крепко [npc2.verb(hold)] [npc.herHim] на месте, мягко прижимая [npc2.her] [npc2.feet] к паху [npc.her], пока [npc2.she] [npc2.verb(force)] [npc.herHim], чтобы принять [npc2.a_footjob].",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to let go of [npc.her] [npc.cock]. [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests, slowly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.herHim] to receive [npc2.a_footjob]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттащить [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя протесты [npc.her], когда [npc2.she] яростно [npc2.verb(thrust)] [npc2.her], прижимая [npc2.feet] назад к [npc.herHim] и грубо [npc2.verb(continue)] заставляя [npc.herHim] принять [npc2.a_footjob].",

							"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] властно удерживает [npc.herHim] на месте, яростно вжимая [npc2.her] [npc2.feet] в пах [npc.her], пока [npc2.she] грубо [npc2.verb(force)] [npc.herHim], чтобы принять [npc2.a_footjob].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], умоляя [npc2.name] отпустить [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)], несмотря на протесты [npc.her], грубо втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(force)] [npc.herHim], чтобы принять [npc2.a_footjob]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттащить [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя протесты [npc.her], пока [npc2.she] жадно [npc2.verb(thrust)] [npc2.her] [npc2.feet] обратно к [npc.herHim] и [npc2.verb(continue)] стремительно заставляя [npc.herHim] принять [npc2.a_footjob].",

							"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] крепко удерживает [npc.herHim] на месте, жадно прижимая [npc2.her] [npc2.feet] к паху [npc.her], пока [npc2.she] отчаянно [npc2.verb(force)] [npc.herHim], чтобы принять [npc2.a_footjob].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.name] отпустила [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] полностью [npc2.verb(ignore)] протесты [npc.her], жадно втирая [npc2.herself] в [npc.herHim], пока [npc2.she] счастливо [npc2.verb(force)] [npc.herHim], чтобы получить [npc2.a_footjob]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Прекратить [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Вытащить свой [npc.cock+] из между [npc2.namePos] [npc2.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.cock+] из между [npc2.namePos] [npc2.feet+], [npc.name] властно [npc.verb(slide)] [npc.cockHead] [npc.her] [npc.cock] вверх и вниз по [npc2.her] [npc2.toes+] в последний раз, прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз глубоко вонзившись между [npc2.namePos] [npc2.feet+], [npc.name] затем [npc2.verb(pull)] назад, кладя конец [npc2.footjob]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.cock+] из между [npc2.namePos] [npc2.feet+], [npc.name] в последний раз шлепает [npc.cockHead] [npc.her] [npc.cock] по [npc2.her] [npc2.toes+], прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз глубоко толкнувшись между [npc2.namePos] [npc2.feet+], [npc.name] затем [npc2.verb(pull)] назад, кладя конец [npc2.footjob]."));
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
	
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Втиснуть [npc2.namePos] [npc2.cock+] между твоих [npc.feet] и начать давать [npc2.herHim] [npc.a_footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] поверх [npc.her] твердых копыт, прежде чем осторожно прижать их к бокам [npc2.her] [npc2.cock] и начать давать [npc2.herHim] [npc.a_footjob].",

						"Потирая твердые копыта [npc.her] о [npc2.namePos] [npc2.cock+], [npc.name] осторожно [npc.verb(press)] их друг о друга, [npc.moaning+] пока [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] между [npc.her] [npc.feet+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] о [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко между [npc.her] [npc.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], [npc2.eagerly] брыкается, [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly] трахающий [npc.her] [npc.feet].",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(slide)] [npc2.her] [npc2.cock+] вперед между [npc.namePos] [npc.feet+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по подошвам [npc.her] птичьих когтей, прежде чем осторожно обхватить ими [npc2.her] [npc2.cock] и начать доставлять [npc2.herHim] [npc.a_footjob].",

						"Потирая [npc.her] птичьи когти о [npc2.namePos] [npc2.cock+], [npc.name] осторожно [npc.verb(wrap)] их вокруг [npc2.her] ствола, [npc.moaning+] так как [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] между [npc.her] [npc.feet+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] о [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко между [npc.her] [npc.feet]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], [npc2.eagerly] брыкается, [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly] трахающий [npc.her] [npc.feet].",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(slide)] [npc2.her] [npc2.cock+] вперед между [npc.namePos] [npc.feet+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по [npc.her] [npc.feet+], прежде чем [npc.eagerly] обхватить ими [npc2.her] [npc2.cock] и начать доставлять [npc2.herHim] [npc.a_footjob].",

						"Потирая [npc.her] [npc.feet+] о [npc2.namePos] [npc2.cock+], [npc.name] [npc.eagerly] [npc.verb(press)] их вместе, [npc.moaning+] так как [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] между [npc.her] [npc.feet+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] о [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко между [npc.her] [npc.feet]"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], [npc2.eagerly] брыкается, [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly] трахающий [npc.her] [npc.feet].",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(slide)] [npc2.her] [npc2.cock+] вперед между [npc.namePos] [npc.feet+]"));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] по подошвам [npc.her] [npc.feet+], прежде чем [npc.eagerly] прижмет их друг к другу вокруг [npc2.her] [npc2.cock] и начнет давать [npc2.herHim] [npc.a_footjob].",

						"Потирая [npc.her] [npc.feet+] о [npc2.namePos] [npc2.cock+], [npc.name] [npc.eagerly] [npc.verb(press)] их вместе, [npc.moaning+] так как [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] между [npc.her] [npc.feet+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] о [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко между [npc.her] [npc.feet]"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], [npc2.eagerly] брыкается, [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly] трахающий [npc.her] [npc.feet].",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(slide)] [npc2.her] [npc2.cock+] вперед между [npc.namePos] [npc.feet+]"));
							break;
					}
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)], жадно втискивая [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.feet], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы втиснуть [npc2.her] [npc2.cock+] между [npc.her] [npc.feet+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.feet+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.feet+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с силой вгоняет [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc.a_footjob].",
		
							" Тихий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] мягко вгоняет [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.feet+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо вбивает [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet].",
									
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.feet+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Нежно дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Нежно использовать [npc2.namePos] [npc2.cock+], чтобы трахать твою [npc.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко скользя [npc.her] [npc.feet] вверх и вниз вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] скользя [npc.her] [npc.feet+] вверх и вниз вокруг [npc2.namePos] [npc2.cock+].",

					"Медленно прижимая [npc.her] [npc.feet+] друг к другу вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Использовать [npc2.namePos] [npc2.cock+], чтобы трахать твою [npc.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно скользя [npc.her] [npc.feet] вверх и вниз вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользя [npc.her] [npc.feet+] вверх и вниз вокруг [npc2.namePos] [npc2.cock+].",

					"Жадно прижимая [npc.her] [npc.feet+] друг к другу вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Грубо дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Грубо двигать [npc2.namePos] [npc2.cock+] вверх и вниз между твоими [npc.feet].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо скользя [npc.her] [npc.feet+] вверх и вниз вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",
					"С помощью [npc.a_moan+], [npc.name] [npc.verb(start)], с силой скользя [npc.her] [npc.feet+] вверх и вниз вокруг [npc2.namePos] [npc2.cock+].",
					"Грубо прижимая [npc.her] [npc.feet+] друг к другу вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Сдави свои [npc.feet+] вокруг [npc2.namePos] [npc2.cock] и дай [npc2.herHim] [npc.a_footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Скользя [npc.her] [npc.feet] вверх и вниз вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользя [npc.her] [npc.feet+] вверх и вниз вокруг [npc2.namePos] [npc2.cock+].",

					"Прижимая [npc.her] [npc.feet+] друг к другу вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "С жадностью дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Сдави свои [npc.feet+] вокруг [npc2.namePos] [npc2.cock] и дай [npc2.herHim] жадное [npc.footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно скользя [npc.her] [npc.feet] вверх и вниз вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользя [npc.her] [npc.feet+] вверх и вниз вокруг [npc2.namePos] [npc2.cock+].",

					"Жадно прижимая [npc.her] [npc.feet+] друг к другу вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться, отдавая [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.feet+] от [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] нежно трахает [npc.her] [npc.feet].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)] пытается оттянуть [npc.her] [npc.feet] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] [npc2.verb(continue)] медленно входит и выходит между [npc.her] [npc.feet].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.feet] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] нежно скользит между [npc.her] [npc.feet]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] жадно трахает [npc.her] [npc.feet].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.feet] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] [npc2.verb(continue)] жадно скользит туда-сюда между [npc.her] [npc.feet].",

							"Отчаянно пытаясь оттащить [npc.her] [npc.feet] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] жадно скользит между [npc.her] [npc.feet]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают наполнять [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] грубо [npc2.verb(continue)] [npc.her] [npc.feet].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.feet] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] [npc2.verb(continue)] грубо вбивается туда-сюда между [npc.her] [npc.feet].",

							"Отчаянно пытаясь оттащить [npc.her] [npc.feet] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается между [npc.her] [npc.feet]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_DOUBLE_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Перестать давать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.cock] из твоих [npc.feet].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.cock] из между [npc.her] [npc.feet], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы остановиться.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из между [npc.her] [npc.feet]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.cock] из между [npc.her] [npc.feet], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.herHim], чтобы остановиться.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.cock] из между [npc.her] [npc.feet]."));
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
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.her] [npc.feet]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	

}
