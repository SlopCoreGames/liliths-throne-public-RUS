package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
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
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class PenisBreastsCrotch {
	
	private static String getPaizuriTitle(GameCharacter character) {
		if(character.isBreastCrotchFuckablePaizuri()) {
			if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "удер-пайзури";
			} else {
				return "пайзури между грудью и лобком";
			}
		} else {
			if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "удер-найзури";
			} else {
				return "найзури между лобком и грудью";
			}
		}
	}
	
	public static final SexAction FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch();
		}
		
		@Override
		public String getActionTitle() {
			return "Начать "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Просунь свой [npc.cock+] между [npc2.namePos] [npc2.crotchBoobs+] и начни трахать их.";
			} else {
				return "Начни тереться своим [npc.cock+] о живот [npc2.namePos].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы обхватить [npc2.namePos] [npc2.crotchBoobs+], [npc.name] осторожно [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, а затем подавшись вперед и начиная трахать [npc2.her] [npc2.crotchBoobs]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы жадно [npc.verb(sink)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] с нетерпением [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, а затем подавшись вперед и начиная с энтузиазмом трахать [npc2.her] [npc2.crotchBoobs]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы грубо [npc.verb(sink)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] с силой [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, а затем резко подавшись вперед и начиная стремительно трахать [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы обхватить [npc2.namePos] [npc2.crotchBoobs+], затем [npc.name] [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, а затем подавшись вперед и начиная трахать [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы жадно [npc.verb(sink)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] с нетерпением [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, а затем подавшись вперед и начиная с энтузиазмом трахать [npc2.her] [npc2.crotchBoobs]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ радостный маленький [npc2.moan], потянувшись вверх, чтобы помочь прижать [npc2.her] [npc2.crotchBoobs] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], желая продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], быстро потянувшись вверх, чтобы помочь прижать [npc2.her] [npc2.crotchBoobs] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], желая продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], потянувшись вверх, чтобы с силой прижать [npc2.her] [npc2.crotchBoobs] друг к другу, пока [npc2.she] властно [npc2.verb(order)] [npc.herHim], желая продолжать."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], быстро потянувшись вверх, чтобы помочь прижать [npc2.her] [npc2.crotchBoobs] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], желая продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ короткий [npc2.moan], прежде чем потянуться вверх, чтобы помочь прижать [npc2.her] [npc2.crotchBoobs] друг к другу, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], желая продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], потянувшись вверх, чтобы слабо попытаться оттолкнуть [npc.herHim] от [npc2.her], пока [npc2.crotchBoobs] [npc2.verb(beg)] [npc2.she], чтобы [npc.herHim] остановился."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы обхватить [npc2.namePos] [npc2.crotchBoobs+], [npc.name] осторожно [npc.verb(try)], сжимая их вместе, чтобы выровнять [npc.her] [npc.cock] по той небольшой ложбинке, что [npc2.she] [npc2.has], а затем скользнув вперед и начиная с нажимом тереться о [npc2.her] низ живота."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы сжать и ощупать [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her], стараясь сжать их вместе, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, что [npc2.she] [npc2.has], прежде чем скользнуть вперед и с жадностью начать тереться о [npc2.her] низ живота."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы грубо сжать и ощупать [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her], стараясь сжать их вместе, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, что [npc2.she] [npc2.has], прежде чем скользнуть вперед и с силой начать тереться о [npc2.her] низ живота."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы сжать и ощупать [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her], стараясь сжать их вместе, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, что [npc2.she] [npc2.has], прежде чем скользнуть вперед и начать тереться о [npc2.her] низ живота."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы сжать и ощупать [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.do] [npc.her], стараясь сжать их вместе, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, что [npc2.she] [npc2.has], прежде чем скользнуть вперед и с жадностью начать тереться о [npc2.her] низ живота."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ радостный маленький [npc2.moan], потянувшись вверх, чтобы помочь сжать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы трахать [npc2.her] груди."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], быстро потянувшись вверх, чтобы помочь сжать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], потянувшись вверх, чтобы силой попытаться [npc.verb(press)] [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, пока [npc2.she] доминантно [npc2.verb(order)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], быстро потянувшись вверх, чтобы помочь сжать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ маленький [npc2.moan], прежде чем потянуться вверх, чтобы помочь сжать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ на [npc2.a_moan+], слабенько пытаясь оттолкнуть [npc.herHim] от [npc2.her], [npc2.crotchBoobSize] [npc2.crotchBoobs], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратила."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к торсу [npc2.namePos], [npc.name] перехватывает [npc.herself], чтобы совместить [npc.her] [npc.cock] над [npc2.her] нижней частью живота, после чего скользит вперед и начинает с нажимом тереться о тело [npc2.her]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к торсу [npc2.namePos], [npc.name] перехватывает [npc.herself], чтобы совместить [npc.her] [npc.cock] над [npc2.her] нижней частью живота, после чего скользит вперед и начинает жадно тереться о тело [npc2.her]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы грубо [npc.verb(press)] [npc.her] [npc.hands] по [npc2.namePos] торсу, [npc.name] переставляет [npc.herself] так, чтобы [npc.her] [npc.cock] находился прямо над [npc2.her] нижней частью живота, а затем подается вперед, начиная с силой тереться о [npc2.her] тело."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к торсу [npc2.namePos], [npc.name] перехватывает [npc.herself], чтобы совместить [npc.her] [npc.cock] над [npc2.her] нижней частью живота, после чего скользит вперед и начинает тереться о тело [npc2.her]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к торсу [npc2.namePos], [npc.name] перехватывает [npc.herself], чтобы совместить [npc.her] [npc.cock] над [npc2.her] нижней частью живота, после чего скользит вперед и начинает жадно тереться о тело [npc2.her]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] радостный маленький [npc2.moan] в ответ, выпячивая [npc2.her] живот, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, быстро выпячивая [npc2.her] живот, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, властно выпячивая [npc2.her] живот, пока [npc2.she] доминантно [npc2.verb(order)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, выпячивая [npc2.her] живот, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] маленький [npc2.moan] в ответ, выпячивая [npc2.her] живот, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, при этом она слабо пытается дотянуться и [npc2.verb(push)] [npc.name] подальше от [npc2.herHim], пока [npc2.she] [npc2.verb(beg)], чтобы её оставили в покое."));
							break;
						default:
							break;
					}
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterTargetedForSexAction(action).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] вместе в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать трахать [npc2.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, с нетерпением прижимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.her] ложбинкой.",
			
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] вокруг [npc.namePos] [npc.cock+], прежде чем умоляя [npc.herHim] продолжить трахать [npc2.her] мягкие полушария."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.her] [npc2.crotchBoobs] в покое.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил(а) [npc2.her] [npc2.crotchBoobs] в покое.",
			
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрался/убралась от [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] вместе, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)], чтобы [npc.name] продолжал(а) трахать [npc2.her] декольте.",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, сжимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.her] грудей.",
			
								" Восторженно [npc2.Moaning], [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] вокруг [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжать трахать [npc2.her] мягкие полусферы."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" В ответ [npc2.Name] нежно [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] вместе, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.name] продолжать трахать [npc2.her] ложбинку.",
			
								" Мягкий [npc2.moan] доносится из ложбинки между [npc2.namePos] [npc2.lips+], и, осторожно сжимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.her] грудей.",
			
								" Восторженно [npc2.Moaning], [npc2.name] нежно [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] вокруг [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжать трахать [npc2.her] мягкие полусферы."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" В ответ [npc2.Name] грубо [npc2.verb(press)] [npc2.her] [npc2.crotchBoobs+] вместе, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.name] продолжать трахать [npc2.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, с силой сжимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать вколачивать [npc.her] [npc.cock+] вверх и вниз между [npc2.her] декольте.",
			
								" [npc2.Moaning] в восторге, [npc2.name] властно [npc2.verb(wrap)] [npc2.her] [npc2.crotchBoobs+] вокруг [npc.namePos] [npc.cock+], прежде чем приказать [npc.herHim] продолжать трахать [npc2.her] мягкие полусферы."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(action).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(attempt)], чтобы в ответ сжать крошечные [npc2.her] [npc2.crotchBoobs] вместе, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name] продолжать трахать ту малую ложбинку, что [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, с энтузиазмом пытаясь сжать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз между крошечным декольте [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] о края [npc.namePos] [npc.cock+], прежде чем умоляя [npc.herHim] продолжать трахать крошечное декольте [npc2.her]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.her] [npc2.crotchBoobs] в покое.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил(а) [npc2.her] [npc2.crotchBoobs] в покое.",
			
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрался/убралась от [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)], чтобы в ответ сжать [npc2.her] [npc2.crotchBoobs+] вместе, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая трахать то немногое декольте, что [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+] и, сжимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз между крошежным декольте [npc2.her].",
			
								" В восторге от [npc2.Moaning], [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] о края [npc.namePos] [npc.cock+], прежде чем умолять о [npc.herHim], чтобы продолжать трахать крошечное декольте [npc2.her]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)], чтобы осторожно сжать [npc2.her] [npc2.crotchBoobs+] вместе в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая трахать то немногое декольте, что [npc2.she] [npc2.has].",
			
								" Мягкий [npc2.moan] вырывается из меж [npc2.namePos] [npc2.lips+], и, осторожно сжимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз в крошечной ложбинке [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.crotchBoobs+] о края [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжить трахать [npc2.her] крошечную ложбинку."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] грубо прижать [npc2.verb(press)] [npc2.her] [npc2.crotchBoobs+] друг к другу в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.name] продолжать трахать ту малую ложбинку, что [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, с силой прижимая [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs] друг к другу, [npc2.she] [npc2.verb(order)] [npc.name] продолжать вколачивать [npc.her] [npc.cock+] вверх и вниз между [npc2.her] крошечной ложбинкой.",
			
								" [npc2.Moaning] в восторге, [npc2.name] доминантно [npc2.verb(press)] [npc2.her] [npc2.crotchBoobs+] о края [npc.namePos] [npc.cock+], прежде чем приказать [npc.herHim] продолжить трахать [npc2.her] крошечную ложбинку."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] плоский живот в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name] продолжать вжиматься в [npc2.her] торс.",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+] и, с нетерпением выпячивая [npc2.her] плоский живот, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
			
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.does] [npc2.her] изо всех сил, пытаясь [npc2.verb(wrap)] [npc2.her] плоский живот вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и умолять [npc.herHim] продолжать вжиматься в [npc2.her] торс."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.her] [npc2.crotchBoobs] в покое.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил(а) [npc2.her] [npc2.crotchBoobs] в покое.",
			
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрался/убралась от [npc2.her] [npc2.crotchBoobs]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ [npc2.verb(push)] [npc2.her] плоский живот, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая вжиматься в [npc2.her] торс.",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+] и, выпячивая [npc2.her] плоский живот, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
	
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.does] [npc2.her] изо всех сил, пытаясь [npc2.verb(wrap)] [npc2.her] плоский живот вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и умолять [npc.herHim] продолжать вжиматься в [npc2.her] торс."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ мягко [npc2.verb(push)] [npc2.her] плоский живот, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая вжиматься в [npc2.her] торс.",
			
								" Мягкий [npc2.moan] вырывается из-под [npc2.namePos] [npc2.lips+], и, осторожно выталкивая [npc2.her] плоский живот, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
	
								" От восторга [npc2.Moaning], [npc2.name] [npc2.does] [npc2.her] лучше попытаться осторожно [npc2.verb(wrap)] [npc2.her] плоский живот вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и умолять [npc.herHim] просто продолжать вжиматься в [npc2.her] торс."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ грубо [npc2.verb(thrust)] [npc2.her] плоский живот, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.name], продолжая вжиматься в [npc2.her] торс.",
			
								" [npc2.A_moan+] срывается из-под [npc2.namePos] [npc2.lips+], и, с силой выталкивая [npc2.her] плоский живот, [npc2.she] [npc2.verb(order)] [npc.name], продолжая мощно вбиваться [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
	
								" От восторга [npc2.Moaning], [npc2.name] [npc2.does] [npc2.her] лучше попытаться грубо [npc2.verb(wrap)] [npc2.her] плоский живот вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и приказать [npc.herHim] просто продолжать вжиматься в [npc2.her] торс."));
						break;
				}
			}
		}
		return "";
	}
	

	public static final SexAction FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Нежно трахать [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Нежно тереться своим [npc.cock+] о плоский живот [npc2.namePos].";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко проскальзывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] размеренно двигает [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Мягко втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] плавно толкает [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Мягко сжимая [npc2.namePos] [npc2.crotchBoobs+] вместе, [npc.name] [npc.verb(let)] издает легкий [npc.moan], пока [npc.she] [npc.verb(start)] плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.crotchBoobs] [npc2.namePos], [npc.name] [npc.verb(start)] размеренно толкая [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] крошечную ложбинку.",

						"Мягко прижимая [npc.her] [npc.cock+] к крошечной ложбинке между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] плавно толкая [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(grind)] вверх и вниз по [npc2.her] нижней части живота.",

						"Мягко пытаясь сжать [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc.name] [npc.verb(let)] издает легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] нижней части живота."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко скользя [npc.her] [npc.cock+] по плоскому [npc2.namePos] низу живота, [npc.name] [npc.verb(start)] размеренно двигает [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(grind)] по [npc2.her] торсу.",

						"Мягко прижимая [npc.her] [npc.cock+] к плоскому [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] плавно толкая [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(grind)] вверх и вниз по [npc2.her] нижней части живота.",

						"Мягко ощупывая плоский низ живота [npc2.namePos], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)] плавно качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Продолжить трахать [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Продолжить тереться о живот [npc2.namePos].";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] энергично вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Жадно сжимая [npc2.namePos] [npc2.crotchBoobs+] вместе, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с жадностью [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] неистово дрыгает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] крошечную ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в едва заметную ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] энергично толкает [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] низ живота.",

						"С жадностью пытаясь сжать [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc.name] [npc.verb(let)] вырывает [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с нетерпением [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] низ живота."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью скользя [npc.her] [npc.cock+] по плоскому низу живота [npc2.namePos], [npc.name] [npc.verb(start)] неистово дрыгает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(grind)] о [npc2.her] торс.",

						"Отчаянно прижимая [npc.her] [npc.cock+] к плоскому [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] энергично вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх и вниз по нижней части живота [npc2.her].",

						"Алчно ощупывая плоский низ живота [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(start)], чтобы неистово качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] жадно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Грубо трахать [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Грубо тереться своим [npc.cock+] о плоский живот [npc2.namePos].";
			}
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо вбивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] яростно дергая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(fuck)] ложбинку [npc2.her].",

						"Яростно втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] грубо вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] властно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Алчно сжимая [npc2.namePos] [npc2.crotchBoobs+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] властно качает [npc.her] [npc.hips] вперед-назад, жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо вбивая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] неистово дергая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] миниатюрную ложбинку.",

						"Яростно втискивая [npc.her] [npc.cock+] в узкую ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] грубо вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] властно [npc.verb(grind)] вверх-вниз по [npc2.her] низ живота.",

						"Алчно пытаясь сжать [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] властно качает [npc.her] [npc.hips] вперед-назад, жадно вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] низ живота."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо прижимая [npc.her] [npc.cock+] к плоскому низу живота [npc2.namePos], [npc.name] [npc.verb(start)] неистово дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] властно [npc.verb(grind)] по торсу [npc2.her].",

						"Яростно вдавливая [npc.her] [npc.cock+] в плоскую [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] грубо вгоняет [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] доминантно [npc.verb(grind)] вверх-вниз по низу живота [npc2.her].",

						"Алчно ощупывая плоский низ живота [npc2.namePos], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] властно качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.cock+] по торсу [npc2.her]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Продолжить трахать [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Продолжить тереться о плоский живот [npc2.namePos].";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Проскальзывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] [npc.verb(fuck)] в ложбинке [npc2.her].",

						"Втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Сжимая [npc2.namePos] и [npc2.crotchBoobs+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her]."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sliding [npc.her] [npc.cock+] between [npc2.namePos] tiny [npc2.crotchBoobs], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] [npc.verb(fuck)] [npc2.her] diminutive cleavage.",

						"Втискивая [npc.her] [npc.cock+] в узкую ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] низ живота.",

						"Пытаясь прижать [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] друг к другу, [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] нижней части живота."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Скользя [npc.her] [npc.cock+] по [npc2.namePos] плоскому низу живота, [npc.name] [npc.verb(start)] дергается, двигая [npc.her] [npc.hips] вперед-назад и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] [npc.verb(grind)] о [npc2.her] торс.",

						"Прижимая [npc.her] [npc.cock+] к плоскому [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] толчками посылает [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] низу живота.",

						"Сжимая [npc2.namePos] плоский низ живота, [npc.name] [npc.verb(let)] выталкивает [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жаждущий "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Жадно трахать [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Жадно тереться о плоский живот [npc2.namePos].";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] энергично вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

						"Жадно сжимая [npc2.namePos] [npc2.crotchBoobs+] вместе, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с жадностью [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] неистово дрыгает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] крошечную ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в едва заметную ложбинку между [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] энергично толкает [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] низ живота.",

						"С жадностью пытаясь сжать [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs] вместе, [npc.name] [npc.verb(let)] вырывает [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с нетерпением [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] низ живота."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью скользя [npc.her] [npc.cock+] по плоскому низу живота [npc2.namePos], [npc.name] [npc.verb(start)] неистово дрыгает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(grind)] о [npc2.her] торс.",

						"Отчаянно прижимая [npc.her] [npc.cock+] к плоскому [npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] энергично вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх и вниз по нижней части живота [npc2.her].",

						"Алчно ощупывая плоский низ живота [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(start)], чтобы неистово качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] жадно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться, отдавая "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Пытаться оттянуть свой [npc.cock] от [npc2.namePos] [npc2.crotchBoobs+].";
			} else {
				return "Пытаться оттянуть свой [npc.cock] от живота [npc2.namePos].";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытащить [npc.her] [npc.cock+] из ложбинки [npc2.namePos], но [npc2.she] решительно [npc2.verb(hold)] [npc.herHim] на месте, сжимая [npc2.her] [npc2.crotchBoobs+] вместе и мягко напоминая [npc.herHim], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] отчаянно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.crotchBoobs+], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, тихо [npc2.moaning], пока [npc2.she] [npc2.verb(ignore)] [npc.her] отчаянные протесты.",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] из ложбинки [npc2.namePos], но хватка [npc2.her] слишком сильна, и [npc2.she] тихо [npc2.verb(continue)] [npc2.moaning], пока [npc2.she] решительно [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] из ложбинки [npc2.namePos], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, сдавливая [npc2.her] [npc2.crotchBoobs+] и рыча, что [npc2.she] будет использовать [npc.herHim] так, как [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.crotchBoobs+], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, [npc.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] из ложбинки [npc2.namePos], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытащить [npc.her] [npc.cock+] из ложбинки [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, прижимая [npc2.her] [npc2.crotchBoobs+] друг к другу и при этом [npc2.moaning], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.crotchBoobs+], но [npc2.she] решительно [npc2.verb(hold)] [npc.herHim] на месте, [npc2.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] вырваться из ложбинки [npc2.namePos], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] с нетерпением [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.crotchBoobs+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытянуть [npc.her] [npc.cock+] из той узкой ложбинки, что [npc2.name] [npc2.has], но [npc2.she] решительно [npc2.verb(hold)] [npc.herHim] на месте, пытаясь прижать [npc2.her] [npc2.crotchBoobs+] друг к другу и мягко напоминая [npc.herHim], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] отчаянно [npc.verb(try)], пытаясь вырваться из [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, тихо [npc2.moaning], пока [npc2.she] [npc2.verb(ignore)] [npc.her] отчаянные протесты.",

								"На [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)], пытаясь выбраться из той крошечной ложбинки, что [npc2.name] могут предложить, но хватка [npc2.her] слишком сильна, и [npc2.she] тихо [npc2.verb(continue)] [npc2.moaning], пока [npc2.she] решительно [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] из той небольшой ложбинки, которую [npc2.name] [npc2.has], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, пытаясь прижать [npc2.her] [npc2.crotchBoobs+] друг к другу и рыча, что [npc2.she] будет использовать [npc.herHim] так, как [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, [npc.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"В глазах [npc.namePos] [npc.eyes] начинают скапливаться слезы, когда [npc.she] [npc.verb(try)], пытаясь вырваться из той крошечной ложбинки, что есть у [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] из той скудной ложбинки, которой [npc2.name] [npc2.has], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, пытаясь сдавить [npc2.her] [npc2.crotchBoobs+] вместе, пока [npc2.moaning], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.crotchBoobSize] [npc2.crotchBoobs], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, [npc.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"В глазах [npc.namePos] [npc.eyes] начинают скапливаться слезы, когда [npc.she] [npc.verb(try)], пытаясь вырваться из той крошечной ложбинки, что есть у [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] с жадностью [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.crotchBoobs+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] оттянуть [npc.her] [npc.cock+] от плоского низа живота [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, втираясь в [npc.herHim], пока [npc2.she] нежно [npc2.moanVerb], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от низа живота [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, тихо [npc2.moaning], пока [npc2.she] [npc2.verb(ignore)] отчаянные протесты [npc.her].",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)], пытаясь отстраниться от [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] тихо [npc2.verb(continue)] [npc2.moaning], пока [npc2.she] решительно [npc2.verb(force)] [npc.her] [npc.cock+] против [npc2.her] нижней части живота."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] оттянуть [npc.her] [npc.cock+] от низа живота [npc2.namePos], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, с силой втираясь в [npc.herHim], пока [npc2.she] [npc2.verb(growl)], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от низа живота [npc2.namePos], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, [npc2.moaning+], пока [npc2.she] [npc2.verb(ignore)] тщетные протесты [npc.her].",

								"На [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] от [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] грубо [npc2.verb(force)] [npc.namePos] [npc.cock+] против нижней части живота [npc2.her]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock+] от нижней части живота [npc2.namePos], но [npc2.name] крепко [npc2.verb(hold)] [npc.herHim] на месте, втираясь в [npc.herHim], пока [npc2.she] [npc2.moanVerb], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от нижней части живота [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, [npc2.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] от [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] с жадностью [npc2.verb(force)] [npc.her] [npc.cock+] против нижней части живота [npc2.her]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Перестать получать "+getPaizuriTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				return "Оттяни свой [npc.cock+] от [npc2.namePos] [npc2.crotchBoobs+] и перестань трахать их.";
			} else {
				return "Оттяни свой [npc.cock+] от нижней части живота [npc2.namePos] и перестань тереться о [npc2.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо оттолкнув [npc2.name], [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из ложбинки [npc2.her] и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватило трахать [npc2.her] [npc2.crotchBoobs+].",

								"Грубо выдергивая [npc.her] [npc.cock+] из ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], показывая, что [npc.sheHas] хватило трахать [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из ложбинки [npc2.namePos] и [npc.verb(tell)] [npc2.name], что [npc.sheHas] хватило трахать [npc2.her] [npc2.crotchBoobs+].",

								"Выдергивая [npc.her] [npc.cock+] из ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.name], показывая, что [npc.sheHas] хватило трахать [npc2.her] [npc2.crotchBoobs+]."));
						break;
				}
			} else if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо оттолкнув [npc2.name], [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из крошечной ложбинки [npc2.her] и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватило трахать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs].",

								"Грубо выдергивая [npc.her] [npc.cock+] из крошечной ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], показывая, что [npc.sheHas] хватило трахать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.namePos] крошечной ложбинки, и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватит трахать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs].",

								"Вытягивая [npc.her] [npc.cock+] из [npc2.namePos] крошечной ложбинки, [npc.name] [npc.verb(tell)] [npc2.name], что [npc.sheHas] хватит трахать [npc2.her] [npc2.crotchBoobSize] [npc2.crotchBoobs]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо отталкивая [npc2.name], [npc.name] убирает [npc.her] [npc.cock+] от [npc2.her] низа живота, и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватит тереться о [npc2.herHim].",

								"Грубо оттягивая [npc.her] [npc.cock+] от [npc2.namePos] низа живота, [npc.name] [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватит тереться о [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] убирает [npc.her] [npc.cock+] от [npc2.namePos] низа живота, и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватит тереться о [npc2.herHim].",

								"Оттягивая [npc.her] [npc.cock+] от [npc2.namePos] низа живота, [npc.name] [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватит тереться о [npc2.herHim]."));
						break;
				}
			}
			

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)] борется с [npc.herHim], [npc2.moaning+], пока [npc2.she] [npc2.verb(beg)] [npc.herHim], чтобы оставить [npc2.herHim] в покое.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.herHim] оставить [npc2.herHim] в покое, слезы наворачиваются на [npc2.her] [npc2.eyes], пока [npc2.she] слабо [npc2.verb(try)] оттолкнуть [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], предавая желание [npc2.her], чтобы [npc.herHim] продолжал.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(beg)], чтобы [npc.herHim] продолжала использовать [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}
		
		@Override
		public String getActionTitle() {
			return "Выполнить "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Использовать [npc2.namePos] [npc2.cock+], чтобы трахать твои [npc.crotchBoobs+].";
			} else {
				return "Использовать [npc2.namePos] [npc2.cock+], чтобы тереться о твой низ живота.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] вместе, [npc.verb(start)] устраивая [npc2.herHim] титфак."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] вместе, [npc.verb(start)] устраивая [npc2.herHim] страстный титфак."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(pull)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] вместе, [npc.verb(start)] устраивая [npc2.herHim] мощный титфак."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] вместе, [npc.verb(start)] устраивая [npc2.herHim] титфак."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.crotchBoobs+] вместе, [npc.verb(start)] устраивая [npc2.herHim] страстный титфак."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ маленький счастливый [npc2.moan], помогая сжимать [npc.namePos] [npc.crotchBoobs] вместе, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], с нетерпением помогая сжимать [npc.namePos] [npc.crotchBoobs] вместе, пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], грубо сжимая [npc.namePos] [npc.crotchBoobs] вместе, пока [npc2.she] [npc2.verb(demand)], что [npc.she] продолжать."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], с нетерпением помогая сжимать [npc.namePos] [npc.crotchBoobs] вместе, пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan], помогая сжимать [npc.namePos] [npc.crotchBoobs] вместе, пока [npc2.she] робко [npc2.verb(ask)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], слабо пытаясь оттолкнуть [npc.name], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] остановился."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] ее до того малого декольте, что имело [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her], стараясь прижать [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] друг к другу, чтобы устроить [npc2.herHim] титфак."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к той немногой ложбинке, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her] старается прижать [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] друг к другу, чтобы устроить [npc2.herHim] страстный титфак."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к той немногой ложбинке, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her] старается прижать [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] друг к другу, чтобы устроить [npc2.herHim] мощный титфак."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к той немногой ложбинке, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her] старается прижать [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] друг к другу, чтобы устроить [npc2.herHim] титфак."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к той немногой ложбинке, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her] старается прижать [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] друг к другу, чтобы устроить [npc2.herHim] страстный титфак."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ маленьким счастливым [npc2.moan], мягко вжимаясь в низ живота [npc.namePos], пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], отчаянно вжимаясь в низ живота [npc.namePos], пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], грубо вжимаясь в низ живота [npc.namePos], пока [npc2.she] [npc2.verb(demand)], что [npc.herHim] продолжать."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], жадно вжимаясь в низ живота [npc.namePos], пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan], вжимаясь в низ живота [npc.namePos], пока [npc2.she] робко [npc2.verb(ask)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, слабо пытаясь оттолкнуть [npc.name], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратилось."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к плоскому низу живота [npc.her] и, скользя вперед, [npc.she] энергично [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к плоскому низу живота [npc.her] и, скользя вперед, [npc.she] с энтузиазмом [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его до [npc.her] плоского низа живота, и, скользя вперед, [npc.she] с силой [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его до [npc.her] плоского низа живота, и, скользя вперед, [npc.she] [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к плоскому низу живота [npc.her] и, скользя вперед, [npc.she] с энтузиазмом [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ маленьким счастливым [npc2.moan], мягко вжимаясь в низ живота [npc.namePos], пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], отчаянно вжимаясь в низ живота [npc.namePos], пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], грубо вжимаясь в низ живота [npc.namePos], пока [npc2.she] [npc2.verb(demand)], что [npc.herHim] продолжать."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], жадно вжимаясь в низ живота [npc.namePos], пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan], вжимаясь в низ живота [npc.namePos], пока [npc2.she] робко [npc2.verb(ask)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, слабо пытаясь оттолкнуть [npc.name], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратилось."));
							break;
						default:
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.crotchBoobs+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда между [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.crotchBoobs+], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], чтобы освободиться.",
			
								" [npc2.A_sob+] резко вырывается из ложбинки [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] двигаться [npc2.cock+] вперед-назад между [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.crotchBoobs+], издавая тихий [npc2.moan], пока [npc2.she] грубо [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" Тихий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] нежно [npc2.verb(start)], вгоняя [npc2.her] [npc2.cock+] между [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] вперед-назад между [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.crotchBoobs+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] грубо [npc2.verb(start)], вбивая [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] вперед-назад между [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.crotchBoobs+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.crotchBoobs+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] вперед-назад между [npc.namePos] [npc.crotchBoobs+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту крошечную ложбинку, что могли предложить [npc.name] [npc.has], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает вгонять [npc2.her] [npc2.cock+] между [npc.namePos] [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке, образовавшейся между [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.crotchBoobs+], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], чтобы освободиться.",
			
								" [npc2.A_sob+] резко вырывается из ложбинки [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] двигаться [npc2.cock+] вперед-назад между [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту малую ложбинку, что могли предложить [npc.name] [npc.has], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" Мягкий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] нежно втискивает [npc2.her] [npc2.cock+] между [npc.namePos] [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] в восторге, пока [npc2.name] плавно [npc2.verb(thrust)] водит [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке между [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] вбивает [npc2.her] [npc2.cock+] в ту скудную ложбинку, что могут предложить [npc.name] [npc.has], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.A_moan+] срывается с губ [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с силой впечатывает [npc2.her] [npc2.cock+] между [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] в восторге, пока [npc2.name] грубо [npc2.verb(slam)] водит [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке между [npc.namePos] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту скудную ложбинку, что могут предложить [npc.name] [npc.has], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.crotchBoobs+].",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] втискивает [npc2.her] [npc2.cock+] между [npc.crotchBoobSize] [npc.crotchBoobs].",
										
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке между [npc.namePos] [npc.crotchBoobs+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(grind)] по [npc.her] торсу.",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает вгонять [npc2.her] вверх-вниз по [npc.namePos] плоскому животу [npc2.cock+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(grind)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] плоского живота, [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
			
								" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.cock+] двигаться туда-сюда по [npc.her] плоскому животу.",
			
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.cock] от [npc.namePos] плоского живота."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(grind)] о [npc.her] торс.",
			
								" Тихий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] начинает нежно [npc2.verb(start)] двигать [npc2.her] [npc2.cock+] вверх и вниз по [npc.namePos] плоскому животу.",
										
								" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(grind)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(grind)] о [npc.her] торс.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] начинает грубо [npc2.verb(start)] вбивать [npc2.her] [npc2.cock+] вверх и вниз по [npc.namePos] плоскому животу.",
										
								" [npc2.Moaning] от восторга, [npc2.name] грубо [npc2.verb(grind)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(grind)] о [npc.her] торс.",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает вгонять [npc2.her] вверх-вниз по [npc.namePos] плоскому животу [npc2.cock+].",
										
								" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.cock+] по [npc.namePos] плоскому животу."));
						break;
				}
			}
		}
		return "";
	}
	
	public static final SexAction PERFORMING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Нежно ласкать [npc2.namePos] [npc2.cock+] своим [npc.crotchBoobs+].";
			} else {
				return "Нежно ласкайте [npc2.namePos] [npc2.cock+] своим животом.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Протягивая руки вверх, чтобы сжать [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] мягко [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, тихо [npc.moaning], пока [npc.she] [npc.verb(use)] [npc.her] ложбинку.",

						"Нежно обхватив [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(lift)] их вверх и вниз, издавая тихий [npc.moan], пока [npc.she] с любовью [npc.verb(give)] [npc2.herHim] титфак.",

						"Издавая тихий [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, окутывая [npc2.namePos] [npc2.cock+] в [npc.her] мягких холмиках, пока [npc.she] с любовью [npc.verb(give)] [npc2.herHim] титфак."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руки вверх, чтобы прижать [npc.her] [npc.crotchBoobs+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] мягко [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, тихо [npc.moaning], пока [npc.she] [npc.verb(try)] [npc.her] использует всё, что дает небольшая ложбинка [npc.she] [npc.has].",

							"Нежно прижимая [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] к бокам [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(lift)] их вверх и вниз, издавая тихий [npc.moan], пока [npc.she] с любовью [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Letting out a soft [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] together, trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(raise)] и [npc.verb(lower)] [npc.namePos] торс, мягко [npc.moaning], пока [npc.she] [npc.verb(thrust)] извлекает [npc.her] плоский низ живота и [npc.verb(grind)] против [npc2.herHim].",

							"Мягко обхватывая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] торса вверх и вниз, втирая [npc.her] плоский низ живота в [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя [npc2.herHim] титфак.",

							"Издавая тихий [npc.moan], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], прежде чем выпятить [npc.her] плоский низ живота и устроить [npc2.herHim] имитацию титфака."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Выполнить "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Ублажай [npc2.namePos] [npc2.cock+] своим [npc.crotchBoobs+].";
			} else {
				return "Ублажай [npc2.namePos] [npc2.cock+] своим плоским животом.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С радостью протягивая руки, чтобы прижать [npc.her] [npc.crotchBoobs+] вместе вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] декольте, чтобы устроить [npc2.herHim] жадный титфак.",

						"С жадностью обхватив [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] в пылком титфаке.",

						"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, окутывая [npc2.namePos] [npc2.cock+] в [npc.her] мягких холмах, пока [npc.she] [npc.verb(give)] [npc2.herHim] в нетерпеливом титфаке."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв руки, чтобы с радостью прижать [npc.her] [npc.crotchBoobs+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], стараясь максимально использовать то небольшое декольте, что [npc.she] [npc.has], пока [npc.she] [npc.verb(try)] [npc.her].",

							"С жадностью прижимая [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] к бокам [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] с энтузиазмом [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Letting out [npc.a_moan+], [npc.name] happily [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] together, trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы с радостью обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.namePos] торс, [npc.moaning+], пока [npc.she] [npc.verb(thrust)] [npc.her] плоский низ живота, отчаянно притираясь к [npc2.herHim].",

							"С жадностью обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] [npc.her] торс вверх и вниз, притирая [npc.her] плоский низ живота к [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], прежде чем выпятить [npc.her] плоский низ живота и с азартом устроить [npc2.herHim] имитацию титфака."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо исполнить "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Грубо доставить удовольствие [npc2.namePos] [npc2.cock+] твоим [npc.crotchBoobs+].";
			} else {
				return "Грубо доставить удовольствие [npc2.namePos] [npc2.cock+] своим плоским животом.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Протянув руки вверх, чтобы грубо сжать [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] стремительно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] декольте, чтобы устроить [npc2.herHim] доминантный титфак.",

						"Властно обхватив [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(bounce)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] в мощном титфаке.",

						"Издавая [npc.a_moan+], [npc.name] с силой [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, окутывая [npc2.namePos] [npc2.cock+] в [npc.her] мягкие холмики, пока [npc.she] [npc.verb(give)] [npc2.herHim] в доминантном титфаке."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы грубо прижать [npc.her] [npc.crotchBoobs+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] стремительно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], стараясь [npc.she] [npc.verb(try)] [npc.her] максимально использовать ту малую ложбинку, что есть у [npc.she] [npc.has].",

							"Властно прижимая [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] к бокам [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(attempt)], чтобы устроить [npc2.herHim] мощный титфак.",

							"Издавая [npc.a_moan+], [npc.name] с силой [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, стараясь [npc.her] изо всех сил доставить удовольствие [npc2.namePos] [npc2.cock+] с помощью той крошечной ложбинки, что [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы грубо обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] неистово [npc.verb(raise)] и [npc.verb(lower)] [npc.namePos] торс, [npc.moaning+], пока [npc.she] [npc.verb(thrust)] [npc.her] плоский низ живота, чтобы доминантно тереться об [npc2.herHim].",

							"Доминантно обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(lift)] [npc.her] торс вверх и вниз, втирая [npc.her] плоский низ живота в [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак для [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] с силой [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], прежде чем выпятить [npc.her] плоский низ живота и грубо имитировать титфак для [npc2.herHim]."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Выполнить "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Ублажай [npc2.namePos] [npc2.cock+] своим [npc.crotchBoobs+].";
			} else {
				return "Ублажай [npc2.namePos] [npc2.cock+] своим плоским животом.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Протянув руки вверх, чтобы сжать [npc.her] [npc.crotchBoobs+] вместе вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] декольте, чтобы устроить [npc2.herHim] титфак.",

						"Обхватив [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] титфак.",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, [npc2.cock+] окутывает [npc2.namePos] мягкими [npc.her] холмиками, пока [npc.she] [npc.verb(give)] [npc2.herHim] титфак."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С радостью потянувшись вверх, чтобы прижать [npc.her] [npc.crotchBoobs+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, [npc.moaning+], стараясь [npc.she] [npc.verb(try)] [npc.her] использовать ту малую ложбинку, что [npc.she] [npc.has].",

							"Прижимая [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] к бокам [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Letting out [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs+] together, trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С радостью потянувшись вверх, чтобы обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] и [npc.verb(lower)] [npc.namePos] торса, [npc.moaning+], стараясь [npc.she] [npc.verb(thrust)] выпятить [npc.her] плоский низ живота, чтобы тереться о [npc2.herHim].",

							"Обернув [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] торс вверх и вниз, втирая [npc.her] плоский низ живота в [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак для [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], после чего выпячивая [npc.her] плоский низ живота и имитируя титфак для [npc2.herHim]."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С энтузиазмом исполнить "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "С энтузиазмом доставить [npc2.namePos] [npc2.cock+] удовольствие своим [npc.crotchBoobs+].";
			} else {
				return "С энтузиазмом доставить [npc2.namePos] [npc2.cock+] удовольствие своим плоским животом.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С радостью протягивая руки, чтобы прижать [npc.her] [npc.crotchBoobs+] вместе вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] декольте, чтобы устроить [npc2.herHim] жадный титфак.",

						"С жадностью обхватив [npc.her] [npc.crotchBoobs+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] в пылком титфаке.",

						"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, окутывая [npc2.namePos] [npc2.cock+] в [npc.her] мягких холмах, пока [npc.she] [npc.verb(give)] [npc2.herHim] в нетерпеливом титфаке."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв руки, чтобы с радостью прижать [npc.her] [npc.crotchBoobs+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], стараясь максимально использовать то небольшое декольте, что [npc.she] [npc.has], пока [npc.she] [npc.verb(try)] [npc.her].",

							"С жадностью прижимая [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] к бокам [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] с энтузиазмом [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.crotchBoobs+] вместе, стараясь изо всех [npc.her] сил доставить удовольствие [npc2.namePos] [npc2.cock+] тем крошечным количеством декольте, что [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы с радостью обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.namePos] торс, [npc.moaning+], пока [npc.she] [npc.verb(thrust)] [npc.her] плоский низ живота, отчаянно притираясь к [npc2.herHim].",

							"С жадностью обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] [npc.her] торс вверх и вниз, притирая [npc.her] плоский низ живота к [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], прежде чем выпятить [npc.her] плоский низ живота и с азартом устроить [npc2.herHim] имитацию титфака."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться исполнению "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Попытаться оттянуть свой [npc.crotchBoobs+] от [npc2.namePos] [npc2.cock+].";
			} else {
				return "Попытаться отстранить свой плоский живот от [npc2.namePos] [npc2.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] [npc.moan+], пока [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.crotchBoobs+] от [npc2.namePos] [npc2.cock+], прежде чем умолять [npc2.herHim] оставить [npc.herHim] в покое.",

						"При [npc.a_moan+], [npc.name] слабо [npc.verb(try)], пытаясь отстраниться от [npc2.name] и всхлипывая от горя, пока [npc2.her] [npc2.cock+] продолжает вжиматься между [npc.her] [npc.crotchBoobs+].",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim]; слезы текут по щекам [npc.her], пока [npc2.she] [npc2.verb(continue)] втискивает [npc2.her] [npc2.cock+] в ложбинку [npc.her]."));
				
			} else if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] из [npc.moan+], пока [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs] от [npc2.namePos] [npc2.cock+], а затем умоляя [npc.herHim] оставить [npc2.herHim] в покое.",

						"При [npc.a_moan+], [npc.name] слабо [npc.verb(try)], пытаясь отстраниться от [npc2.name] и всхлипывая от горя, пока [npc2.her] [npc2.cock+] продолжают вжиматься между [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs+].",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim]; слезы текут по щекам [npc.her], пока [npc2.she] [npc2.verb(continue)] втискивает [npc2.her] [npc2.cock+] в маленькую ложбинку [npc.her]."));
						
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] из [npc.moan+], пока [npc.she] [npc.verb(try)], пытаясь оттянуть плоский низ живота [npc.her] от [npc2.namePos] [npc2.cock+], а затем умоляя [npc.herHim] оставить [npc2.herHim] в покое.",

						"При [npc.a_moan+], [npc.name] слабо [npc.verb(try)], пытаясь отстраниться от [npc2.name] и всхлипывая от горя, пока [npc2.her] [npc2.cock+] продолжает вжиматься в плоский низ живота [npc.her].",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim]; слезы текут по щекам [npc.her], пока [npc2.she] [npc2.verb(continue)] вгоняет [npc2.her] [npc2.cock+] в торс [npc.her]."));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить выполнение "+getPaizuriTitle(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastCrotchFuckablePaizuri()) {
				return "Отодвинь [npc2.namePos] [npc2.cock] от своего [npc.crotchBoobs+].";
			} else {
				return "Отодвинь [npc2.namePos] [npc2.cock] от своего плоского живота.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchSize().getMeasurement()>=CupSize.AA.getMeasurement()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] грубо [npc.verb(push)] [npc2.name] от [npc.herHim] и угрожающим тоном [npc.verb(order)] [npc2.herHim], чтобы тот перестал трахать [npc.her] [npc.crotchBoobs+].",

								"С угрожающим рычанием, [npc.name] грубо [npc.verb(push)] [npc2.name] прочь, и [npc.verb(order)] [npc2.herHim], чтобы прекратить трахать [npc.her] [npc.crotchBoobs+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] от [npc.herHim], и [npc.verb(tell)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.crotchBoobs+].",

								"С последним [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] прочь, и [npc.verb(tell)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.crotchBoobs+]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] грубо [npc.verb(push)] [npc2.name] от [npc.herHim] и угрожающим тоном [npc.verb(order)] [npc2.herHim], чтобы прекратить тереться о низ живота [npc.her].",

								"С угрожающим рычанием, [npc.name] грубо [npc.verb(push)] [npc2.name] прочь, и [npc.verb(order)] [npc2.herHim], чтобы перестать тереться о низ живота [npc.her]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] от [npc.herHim], и [npc.verb(tell)] [npc2.herHim], чтобы перестать тереться о низ живота [npc.her].",

								"С последним [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] прочь, и [npc.verb(tell)] [npc2.herHim], чтобы перестать тереться о низ живота [npc.her]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)], борясь с [npc.herHim], [npc2.moaning+], пока [npc2.she] [npc2.verb(beg)] [npc.name], чтобы оставить [npc2.herHim] в покое.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.name], чтобы оставить [npc2.herHim] в покое; в [npc2.her] [npc2.eyes] наворачиваются слезы, пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может не [npc2.verb(help)], лишь издавая [npc2.a_moan+], выдающий [npc2.her] желание получить еще больше внимания от [npc.namePos].",
	
								" С [npc2.a_moan+], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал использовать [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
