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
public class PenisFoot {

	// Foot tease
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Взять [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Заставьте [npc2.name] начать тереть [npc2.her] [npc2.foot] о вашу [npc.cock].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(bringing)] [npc2.namePos] подносит твердое копыто к [npc.her] [npc.cock+], аккуратно [npc.verb(press)] подошвой по [npc.her] гениталиям, после чего [npc.name] резко подает [npc.her] [npc.hips] вперед, начиная получать hoofjob.",
	
								"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her], прижимая [npc.cock] к твердому [npc2.namePos] копыту, прежде чем осторожно прижать [npc2.her] подошву к [npc.her] стволу и начать получать hoofjob."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], и, когда слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратилось.",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока нежеланное [npc.cock] скользит по [npc2.her] [npc2.foot]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], прежде чем [npc2.eagerly] начнет скользить ею вверх и вниз по [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly], скользящим [npc2.her] [npc2.foot] вверх и вниз по [npc.namePos] [npc.cock+]."));
							break;
					}
				}
					
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(curling)] [npc2.namePos] обхватывает [npc.her] [npc.cock+], словно птичья лапа, [npc.name] [npc.verb(make)], убедившись, что [npc2.her] острые когти направлены в сторону от [npc.her] гениталий, после чего [npc.her] [npc.hips] подается вперед, начиная принимать [npc2.a_footjob].",
	
								"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her], [npc.cock] прижимая их к [npc2.namePos] птичьей лапе, после чего [npc2.her] когти обхватывают [npc.her] [npc.cock+], и она начинает принимать [npc2.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], и, когда слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратилось.",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока нежеланное [npc.cock] скользит по [npc2.her] [npc2.foot]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], прежде чем [npc2.eagerly] начнет скользить ею вверх и вниз по [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly], скользящим [npc2.her] [npc2.foot] вверх и вниз по [npc.namePos] [npc.cock+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(guiding)] [npc2.namePos] направляет [npc2.foot+] к [npc.her] паху, [npc.name] [npc.verb(press)] прижимая [npc.cockHead+] [npc.her] [npc.cock+] к нему, после чего [npc.eagerly] заставляет [npc2.herHim] тереться [npc2.her] [npc2.foot] вдоль всей длины [npc.her].",

						"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her], [npc.cock] прижимая их к [npc2.namePos] [npc2.foot+], после чего [npc.eagerly] втирает [npc.her] [npc.cock+] в нее."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], и, когда слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратилось.",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока нежеланное [npc.cock] скользит по [npc2.her] [npc2.foot]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], прежде чем [npc2.eagerly] начнет скользить ею вверх и вниз по [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly], скользящим [npc2.her] [npc2.foot] вверх и вниз по [npc.namePos] [npc.cock+]."));
							break;
					}
				}
					
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(guiding)] [npc2.namePos] [npc2.foot+] к [npc.her] промежности, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+], прижатого к [npc2.her] подошве, прежде чем [npc.eagerly] позволит [npc2.herHim] тереть [npc2.her] [npc2.foot] вдоль всей длины [npc.her].",

						"[npc.Name] [npc.verb(rub)] [npc.cockHead+] [npc.her] [npc.cock], прижатый к [npc2.namePos] [npc2.foot+], прежде чем [npc.eagerly] начал(а) [npc.her] [npc.cock+] об [npc2.her] подошву."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], и, когда слезы текут по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратилось.",
		
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока нежеланное [npc.cock] скользит по [npc2.her] [npc2.foot]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.foot], прежде чем [npc2.eagerly] начнет скользить ею вверх и вниз по [npc.her] [npc.cock+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] [npc2.eagerly], скользящим [npc2.her] [npc2.foot] вверх и вниз по [npc.namePos] [npc.cock+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(start)] скользит [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim] с энтузиазмом [npc2.footjob].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] с жадностью [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] с энтузиазмом [npc2.footjob]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах оттянуть [npc2.her] [npc2.leg] от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] перестал(а) использовать [npc2.her] [npc2.foot].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] отпустил(а) [npc2.her] [npc2.foot].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] ушла от [npc2.her] [npc2.foot]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] скользит [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim] [npc2.a_footjob].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] [npc2.a_footjob]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim] как любящий [npc2.footjob].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] нежный [npc2.footjob]."));
					break;
				case DOM_ROUGH:
					if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)], грубо водя [npc2.her] [npc2.foot] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, совершенно не заботясь о том, что использование [npc2.her] твердого копыта столь яростным образом крайне неудобно для [npc.herHim].",
			
								" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], пока [npc2.she] с силой [npc2.verb(rub)] [npc2.her] твердое копыто вверх и вниз по [npc.namePos] [npc.cock+], ни капли не заботясь о том, насколько неприятно принимать такой грубый [npc2.footjob].",
			
								" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her] твердым копытом вверх и вниз по [npc.namePos] [npc.cock+], насмешливо хохоча над [npc.herHim], пока [npc2.she] [npc2.verb(administer)] этот неудобный, напористый [npc2.footjob]."));
						
					} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)], грубо водя [npc2.her] [npc2.foot] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, совершенно не заботясь о том, что [npc2.her] острые когти то и дело опасно подходят к тому, чтобы полоснуть по [npc.herHim].",
			
								" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], пока [npc2.she] с силой [npc2.verb(rub)] [npc2.her] птичью лапу вверх и вниз по [npc.namePos] [npc.cock+], ни капли не заботясь о том, насколько близко [npc2.her] острые когти подходят к тому, чтобы полоснуть по [npc.herHim].",
			
								" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her] скребла птичьей лапой вверх и вниз по [npc.namePos] [npc.cock+], насмешливо хохоча над [npc.herHim], пока [npc2.she] [npc2.verb(draw)] опасно близко к тому, чтобы полоснуть [npc.herHim] своими [npc2.her] острыми когтями."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ грубо [npc2.verb(start)] двигала [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(give)] [npc.herHim] мощным [npc2.footjob].",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] с силой [npc2.verb(slide)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+].",
			
								" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her] [npc2.foot+] вверх и вниз по [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(deliver)] мощным [npc2.footjob]."));
					}
					
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Нежный [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Мягко принять [npc2.a_footjob] от [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко двигая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot], [npc.name] steadily [npc.verb(start)] толкая [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(receive)] [npc2.a_footjob].",

					"[npc.her] нежно потирает [npc.cock+] вверх и вниз о [npc2.namePos] [npc2.foot+], при этом [npc.name] мягко [npc.verb(start)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(receive)] [npc2.a_footjob].",

					"[npc2.foot+] мягко прижимает [npc.her] [npc.cock+] к [npc2.namePos], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Обычный [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить получать [npc2.a_footjob] от [npc2.name].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot], [npc.name] [npc.verb(start)] энергично дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Отчаянно толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(start)] неистово вгоняя [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Алчно прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] отчаянно качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Грубый [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Грубо принимать [npc2.a_footjob] от [npc2.name].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot], [npc.name] [npc.verb(start)] властно погонял [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(receive)] [npc2.a_footjob].",

					"С силой толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(start)] яростно вбивает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(receive)] [npc2.a_footjob].",

					"Властно прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с силой шлепает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Обычный [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить получать [npc2.a_footjob] от [npc2.name].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.her] [npc.cock+] ходит вверх-вниз по [npc2.namePos] [npc2.foot], [npc.name] [npc.verb(start)] дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"[npc.her] [npc.cock+] ходит вверх-вниз по [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(start)] вгоняет [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] счастливо [npc.verb(receive)] [npc2.a_footjob].",

					"Прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], когда [npc.she] [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Нетерпеливый [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Жадно принимать [npc2.a_footjob] от [npc2.name].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot], [npc.name] [npc.verb(start)] энергично дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Отчаянно толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(start)] неистово вгоняя [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] [npc2.a_footjob].",

					"Алчно прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.foot+], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(start)], отчаянно толкая [npc.her] [npc.hips] взад-вперед и жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(receive)] [npc2.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.cock] от [npc2.namePos] [npc2.foot+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], при этом, игнорируя протесты [npc.her], [npc2.she] медленно, но уверенно [npc2.verb(push)] [npc2.her] [npc2.foot] вниз, прижимая к [npc.her] [npc.cock+].",

							"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.name] уверенно [npc2.verb(hold)] [npc.herHim] на месте, мягко вдавливая [npc2.her] [npc2.foot] вниз к [npc.her] [npc.cock+].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.herHim] отпустил(а) [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] протесты [npc.her], медленно втирая [npc2.her] [npc2.foot] вниз к [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], при этом, игнорируя протесты [npc.her], [npc2.she] яростно [npc2.verb(grind)] [npc2.her] [npc2.foot] вниз, прижимая к [npc.her] [npc.cock+].",

							"[npc.A_sob+] вырывается из-под [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] властно удерживает [npc.herHim] на месте, с силой вдавливая [npc2.her] [npc2.foot] вниз на [npc.her] [npc.cock+].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.name] отпустила [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] протесты [npc.her], грубо втирая [npc2.her] [npc2.foot] вниз на [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но не в силах оттащить [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_sob+], в то время как, игнорируя протесты [npc.her], [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.foot] вниз на [npc.her] [npc.cock+].",

							"[npc.A_sob+] вырывается из-под [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] крепко удерживает [npc.herHim] на месте, с жадностью вдавливая [npc2.her] [npc2.foot] вниз на [npc.her] [npc.cock+].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.name] отпустила [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] полностью [npc2.verb(ignore)], пока [npc.her] протестует, жадно втирая [npc2.her] [npc2.foot] вниз против [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Прекратить [npc2.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Оттяни свой [npc.cock+] от [npc2.namePos] [npc2.foot].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.cock+] от [npc2.namePos] [npc2.foot+], [npc.name] властно [npc.verb(slide)] [npc.cockHead] [npc.her] [npc.cock] вверх и вниз по [npc2.her] [npc2.toes+] в последний раз, прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз толкнувшись вверх против [npc2.namePos] [npc2.foot+], [npc.name] затем [npc2.verb(pull)] назад, кладя конец [npc2.footjob]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc.her] [npc.cock+] прочь от [npc2.namePos] [npc2.foot+], [npc.name] в последний раз шлепает [npc.cockHead] [npc.her] [npc.cock] о [npc2.her] [npc2.toes+], прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз толкнувшись вверх против [npc2.namePos] [npc2.foot+], [npc.name] затем [npc2.verb(pull)] назад, кладя конец [npc2.footjob]."));
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
	
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.foot] к [npc2.namePos] [npc2.cock+] и начни давать [npc2.herHim] [npc.a_footjob].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С помощью [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(press)] [npc.her] твердым копытом по [npc2.namePos] [npc2.cock+], прежде чем осторожно [npc.eagerly] растирать его вверх и вниз по всей длине [npc2.her].",

						"Прижимая [npc.her] твердое копыто к [npc2.namePos] паху, [npc.name] [npc.eagerly] [npc.verb(press)] его вниз по [npc2.her] [npc2.cock+], [npc.moaning+] пока [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С помощью [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], [npc2.eagerly] подбрасывая [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly], втираясь в [npc.her] [npc.foot].",
	
									" С помощью [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С помощью [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(press)] [npc.her] птичьей когтем по [npc2.namePos] [npc2.cock+], а затем осторожно обхватывает [npc.her] когтями [npc2.her] [npc2.cock], начиная доставлять [npc2.herHim] [npc.a_footjob].",

						"Прижимая [npc.her] птичью когтем к [npc2.namePos] паху, [npc.name] [npc.eagerly] [npc.verb(grasp)] [npc2.her] [npc2.cock+], [npc.moaning+], пока [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С помощью [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], [npc2.eagerly] подбрасывая [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly], втираясь в [npc.her] [npc.foot].",
	
									" С помощью [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+]."));
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С помощью [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(press)] [npc.her] [npc.foot+] вниз по [npc2.namePos] [npc2.cock+], после чего [npc.eagerly] растирает её вверх и вниз по всей длине [npc2.her] стержня.",

						"Прижимая [npc.her] [npc.foot+] к [npc2.namePos] паху, [npc.name] [npc.eagerly] [npc.verb(press)] её по [npc2.her] [npc2.cock+], [npc.moaning+], пока [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С помощью [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], [npc2.eagerly] подбрасывая [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly], втираясь в [npc.her] [npc.foot].",
	
									" С помощью [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+]."));
							break;
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С помощью [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(press)] [npc.her] [npc.foot+] вниз по [npc2.namePos] [npc2.cock+], после чего [npc.eagerly] растирает подошву [npc.her] вверх и вниз по всей длине [npc2.her] стержня.",

						"Прижимая [npc.her] [npc.foot+] к [npc2.namePos] паху, [npc.name] [npc.eagerly] [npc.verb(press)] подошвой [npc.her] по [npc2.her] [npc2.cock+], [npc.moaning+], пока [npc.she] [npc.verb(start)], чтобы доставить [npc2.herHim] [npc.a_footjob]."));

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock+], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырваться.",
	
									" С помощью [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc.her] [npc.foot] против [npc2.her] [npc2.cock]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], [npc2.eagerly] подбрасывая [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly], втираясь в [npc.her] [npc.foot].",
	
									" С помощью [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+]."));
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
							" [npc2.Name] жадно [npc2.verb(grind)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с нетерпением вгоняет [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot].",
									
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.foot], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы силой втиснуть [npc2.her] [npc2.cock+] против [npc.her] [npc.foot+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.foot+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot].",
									
							" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(press)] [npc2.her] [npc2.cock+] вплотную к [npc.namePos] [npc.foot+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc.a_footjob].",
		
							" Тихий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] мягко вгоняет [npc2.her] [npc2.cock+] в [npc.namePos] [npc.foot].",
									
							" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] вплотную к [npc.namePos] [npc.foot+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.foot+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(receive)] [npc.a_footjob].",
		
							" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.cock+] в [npc.namePos] [npc.foot].",
									
							" [npc2.Moaning] от восторга, [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] [npc2.cock+] вплотную к [npc.namePos] [npc.foot+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Нежно дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Мягко прижми свой [npc.foot] к [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Плавно скользя [npc.her] [npc.foot] вверх и вниз по [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] скользит [npc.her] [npc.foot+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"Медленно прижимая [npc.her] [npc.foot+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.foot] к [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью скользя [npc.her] [npc.foot] вверх и вниз по [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользит [npc.her] [npc.foot+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"С жадностью прижимая [npc.her] [npc.foot+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Грубо дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Грубо втирай свой [npc.foot] в [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо скользя [npc.her] [npc.foot] вверх и вниз по [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",
					"С [npc.a_moan+], [npc.name] с силой [npc.verb(start)] скользит [npc.her] [npc.foot+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"Грубо прижимая [npc.her] [npc.foot+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.foot] к [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Скользя [npc.her] [npc.foot] вверх и вниз по [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользит [npc.her] [npc.foot+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"Прижимая [npc.her] [npc.foot+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "С жадностью дать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Нетерпеливо прижми свой [npc.foot] к [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью скользя [npc.her] [npc.foot] вверх и вниз по [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользит [npc.her] [npc.foot+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"С жадностью прижимая [npc.her] [npc.foot+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.a_footjob]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться, отдавая [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Попытайся отстранить свой [npc.foot+] от [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наворачиваться на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] нежно толкая [npc.her] [npc.foot].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.foot] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] [npc2.verb(continue)] медленно толкая [npc.her] [npc.foot].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.foot] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] нежно скользит по [npc.her] [npc.foot]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] начинают наполнять [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] с жадностью [npc2.verb(continue)] [npc.her] [npc.foot].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.foot] от [npc2.namePos] [npc2.cock], отчаянно борясь, пока [npc2.she] жадно [npc2.verb(continue)], трахая [npc.her] [npc.foot].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.foot] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] жадно скользит по [npc.her] [npc.foot]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают наполнять [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] грубо трахает [npc.her] [npc.foot].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.foot] от [npc2.namePos] [npc2.cock], отчаянно борясь, пока [npc2.she] грубо [npc2.verb(continue)], трахая [npc.her] [npc.foot].",

							"Отчаянно пытаясь оттащить [npc.her] [npc.foot] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)], грубо притираясь к [npc.her] [npc.foot]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FOOT_JOB_SINGLE_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isDoubleFootJob(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Перестать давать [npc.footjob]";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] оттащить [npc2.her] [npc2.cock] вопреки твоему [npc.foot].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Забирая [npc.her] [npc.foot] от [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы оставить [npc.her] [npc.feet] в покое.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем быстро оттащить [npc.her] [npc.foot] от [npc2.her] [npc2.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Забирая [npc.her] [npc.foot] от [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.herHim], чтобы оставить [npc.her] [npc.feet] в покое.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем оттащить [npc.her] [npc.foot] от [npc2.her] [npc2.cock+]."));
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
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.her] [npc.foot]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	

}
