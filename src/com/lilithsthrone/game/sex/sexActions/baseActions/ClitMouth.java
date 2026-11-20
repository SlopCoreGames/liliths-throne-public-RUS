package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4
 * @author Innoxia
 */
public class ClitMouth {
	
	public static final SexAction FORCE_SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
			} else {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH));
			}
		}
		@Override
		public SexActionType getActionType(){
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(Main.sex.getCharacterTargetedForSexAction(this))
					|| Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return SexActionType.ONGOING;
			} else {
				return SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED;
			}
		}
		@Override
		public String getActionTitle() {
			return "Внимание к клитору";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] потратить несколько мгновений на поцелуи и ласки твоего клитора.";
		}
		@Override
		public String getDescription() {
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"Сдвигая [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself], чтобы нежно тереть [npc.her] [npc.clit+] о [npc2.namePos] [npc2.lips+].",
							
							"Быстрым движением [npc.her] [npc.hips], [npc.name] [npc.verb(let)] мягкий [npc.moan], пока [npc.she] нежно [npc.verb(press)] [npc.her] [npc.clit+] о [npc2.namePos] [npc2.tongue+].",
							
							"Мягко прижимая [npc.her] [npc.pussy+] к [npc2.namePos] рту, [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.sheIs] тер [npc.her] [npc.clit+] о [npc2.namePos] [npc2.lips+].");

				case SUB_EAGER:
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"Сдвигая [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself], чтобы жадно тереть [npc.her] [npc.clit+] о [npc2.namePos] [npc2.lips+].",
							
							"Быстрым движением [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(press)] [npc.her] [npc.clit+] о [npc2.namePos] [npc2.tongue+].",
							
							"Жадно прижимая [npc.her] [npc.pussy+] к [npc2.namePos] рту, [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.sheIs] тер [npc.her] [npc.clit+] о [npc2.namePos] [npc2.lips+].");
					
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"Сдвигая [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself], чтобы грубо тереть [npc.her] [npc.clit+] о [npc2.namePos] [npc2.lips+].",
							
							"Резким движением [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.clit+] вниз, прижимая к [npc2.namePos] [npc2.tongue+].",
							
							"Агрессивно прижимая [npc.her] [npc.pussy+] к рту [npc2.namePos], [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.sheIs] грубо втирает [npc.her] [npc.clit+] вниз, прижимая к [npc2.namePos] [npc2.lips+].");
					
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"Двигая [npc.her] [npc.hips], [npc.name] [npc.verb(reposition)] [npc.herself], чтобы втирать [npc.her] [npc.clit+] о [npc2.namePos] [npc2.lips+].",
							
							"Резким движением [npc.her] [npc.hips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(press)] [npc.her] [npc.clit+] вниз, прижимая к [npc2.namePos] [npc2.tongue+].",
							
							"Прижимая [npc.her] [npc.pussy+] к рту [npc2.namePos], [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.sheIs] втирает [npc.her] [npc.clit+] вниз, прижимая к [npc2.namePos] [npc2.lips+].");
					
				default:
					break;
			}
			
			return "";
		}
	};
	
	public static final SexAction SUCK_CLIT = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
			} else {
				return Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT));
			}
		}
		@Override
		public SexActionType getActionType(){
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					|| Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return SexActionType.ONGOING;
			} else {
				return SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED;
			}
		}
		@Override
		public String getActionTitle() {
			return "Сосать клитор";
		}
		@Override
		public String getActionDescription() {
			return "Сосать [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно скользя [npc.her] [npc.tongue] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.clit+], прежде чем начать нежно посасывать и целовать её.",

							"Долгим, медленным языком, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] вверх и по всей поверхности [npc2.namePos] [npc2.clit+], прижимая [npc.her] [npc.lips+] к нему, пока [npc.she] нежно [npc.verb(start)] целует и посасывает [npc2.her] [npc2.clit+].",

							"Нежно целуя и облизывая [npc2.namePos] [npc2.pussy+], [npc.name] медленно [npc.verb(make)] [npc.her] в сторону [npc2.her] [npc2.clit+], и серией мягких движков [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] сосредоточенно доставляет удовольствие [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью скользя [npc.her] [npc.tongue] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.clit+], прежде чем начать жадно посасывать и целовать его.",

							"Долгим, влажным языком, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] вверх и по всей поверхности [npc2.namePos] [npc2.clit+], прижимая [npc.her] [npc.lips+] к нему, пока [npc.she] с жадностью [npc.verb(start)] целует и посасывает [npc2.her] [npc2.clit+].",

							"С жадностью целуя и облизывая [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] в сторону [npc2.her] [npc2.clit+], и серией влажных движков [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] сосредоточенно доставляет удовольствие [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо проводя [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.pussy+], [npc.name] с силой [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.clit+], после чего начинает властно сосать и целовать его.",

							"Резким влажным языком [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] вверх и по [npc2.namePos] [npc2.clit+], прижимая [npc.her] [npc.lips+] к нему, пока [npc.she] [npc.verb(start)] с силой целует и сосет [npc2.her] [npc2.clit+].",

							"Грубо целуя и облизывая [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] в сторону [npc2.her] [npc2.clit+], и серией жадных влажных движков [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] сосредоточен на доставлении удовольствия [npc2.her] [npc2.clit+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью скользя [npc.her] [npc.tongue] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.clit+], прежде чем начать жадно посасывать и целовать его.",

							"Долгим, влажным языком, [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] вверх и по всей поверхности [npc2.namePos] [npc2.clit+], прижимая [npc.her] [npc.lips+] к нему, пока [npc.she] с жадностью [npc.verb(start)] целует и посасывает [npc2.her] [npc2.clit+].",

							"С жадностью целуя и облизывая [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her] в сторону [npc2.her] [npc2.clit+], и серией влажных движков [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] сосредоточенно доставляет удовольствие [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc.her] [npc.tongue] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.clit+], после чего начинает сосать и целовать его.",

							"Длинным влажным языком [npc.name] [npc.verb(run)] [npc.her] [npc.tongue] вверх и по [npc2.namePos] [npc2.clit+], прижимая [npc.her] [npc.lips+] к нему, пока [npc.she] [npc.verb(start)] целует и сосет [npc2.her] [npc2.clit+].",

							"Поцелуи и ласки [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(make)] [npc.her], ведущие к [npc2.her] [npc2.clit+], а затем серия влажных движений языком [npc.her] [npc.tongue+], когда [npc.she] [npc.verb(start)] сосредоточена на доставлении удовольствия [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], и, мягко выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)], умоляя [npc.herHim] продолжать.",
	
								" Дрожащий [npc2.moan] срывается с [npc2.namePos] [npc2.lips+], и, мягко прижимая [npc2.her] [npc2.pussy] к [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливался.",
	
								" Мягко выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] нежный [npc2.moan], прежде чем умоляя [npc.herHim] продолжать."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], и, жадно выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)], умоляя [npc.herHim] продолжать.",
	
								" Дрожащий [npc2.moan] вырывается из глубин [npc2.namePos] [npc2.lips+], и, жадно прижимая [npc2.her] [npc2.pussy] к [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливался.",
	
								" С жадностью выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем умоляя [npc.herHim] продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], и, грубо втирая [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.herHim] продолжить.",
	
								" Дрожащий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо втирая [npc2.her] [npc2.pussy] в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(command)] [npc.herHim] не останавливаться.",
	
								" Грубо втирая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем приказывая [npc.herHim] продолжить."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], и, жадно выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)], умоляя [npc.herHim] продолжать.",
	
								" Дрожащий [npc2.moan] вырывается из глубин [npc2.namePos] [npc2.lips+], и, жадно прижимая [npc2.her] [npc2.pussy] к [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливался.",
	
								" С жадностью выгибая [npc2.her] [npc2.hips] навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем умоляя [npc.herHim] продолжить."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], и, выгибая [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(cry)], требуя от [npc.herHim] продолжить.",
	
								" Дрожащий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, прижимая [npc2.her] [npc2.pussy] к [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливался.",
	
								" [npc2.her] выгибается, [npc2.hips] толкаясь против [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем умолять [npc.herHim] продолжить."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.face], издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил [npc2.herHim] в покое.",
	
								" Со слезами, текущими по [npc2.face] [npc2.her], [npc2.name] [npc2.verb(struggle)] против [npc.name], громко [npc2.sobbing], пока [npc2.she] [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.pussy] от нежеланного [npc.tongue] [npc.her].",
	
								" [npc2.Sobbing] вслух, и со слезами на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beg)], чтобы [npc.name] оставил [npc2.herHim] в покое, отчаянно пытаясь оттянуть [npc2.her] [npc2.hips] назад каждый раз, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tongue+] катились по [npc2.her] [npc2.clit+]."));
						break;
					default:
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Ongoing penetrative actions:
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Тянуть за хвостики";
			} else {
				return "Тянуть за косички";
			}
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Схватить [npc2.namePos] хвостиков и потянуть [npc2.her] вперед, на твой [npc.clit+].";
			} else {
				return "Схватить [npc2.namePos] косичек и потянуть [npc2.her] вперед, на твой [npc.clit+].";
			}
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HAIR)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS || Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}
		@Override
		public String getDescription() {
			
			String style = Main.sex.getCharacterTargetedForSexAction(this).getHairStyle().getName(Main.sex.getCharacterTargetedForSexAction(this));
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] "+style+", [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Протянув руку вниз, чтобы мягко, но уверенно обхватить каждый [npc2.namePos] "+style+", [npc.name] медленно [npc.verb(pull)] [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.clit+] по самую головку.",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] "+style+", прежде чем осторожно потянуть [npc2.herHim] вперед, заставляя [npc.her] [npc.clit+] опускаться в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо схватив [npc2.namePos] "+style+", [npc.name] яростно [npc.verb(jerk)] головку [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Протянув руку вниз, чтобы схватить [npc2.namePos] "+style+", [npc.name] безжалостно [npc.verb(yank)] головку [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.clit+] по самую головку.",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] "+style+", прежде чем яростно дернуть [npc2.herHim] вперед, вгоняя [npc.her] [npc.clit+] глубоко в [npc2.her] так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] "+style+", [npc.name] крепко [npc.verb(pull)] [npc2.her] голову вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Потянувшись вниз, чтобы крепко обхватить каждую из [npc2.namePos] "+style+", [npc.name] размеренно [npc.verb(pull)] [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.clit+].",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] "+style+", прежде чем плавно потянуть [npc2.herHim] вперед, вгоняя [npc.her] [npc.clit+] глубоко в [npc2.her] так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение нежно лаская нижнюю часть [npc.namePos] [npc.clit] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы мягко ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] наконец не вытащится из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сужая [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] с [npc.namePos] дерзким маневром лишь на мгновение, прежде чем резко откинуть [npc2.her] голову назад и выразительно напомнить [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, все же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.clit+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность закричать и жадно глотать воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Вибрации приглушенных криков и рыданий [npc2.namePos] лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] твоему мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь бить и упираться в [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.hair(true)] освобождается, а [npc.namePos] [npc.clit+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, подступающими к [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.clit] своими [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] в конце концов не вырвется из [npc2.her] горла."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction EAR_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Потянуть за ухо";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.ears+] и потянуть [npc2.her] вперед, на твои [npc.clit+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HEAD)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.ears+], [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Протянув руку вниз, чтобы мягко, но уверенно обхватить каждую из [npc2.namePos] [npc2.ears+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim] для глубокого заглатывания [npc.her] [npc.clit+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.ears+], прежде чем мягко потянуть [npc2.herHim] вперед, заталкивая [npc.her] [npc.clit+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо схватив [npc2.namePos] [npc2.ears+], [npc.name] яростно [npc.verb(jerk)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Потянувшись вниз, чтобы схватить [npc2.namePos] [npc2.ears+], [npc.name] безжалостно [npc.verb(yank)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.clit+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.ears+], а затем резко дернув [npc2.herHim] вперед, вонзая [npc.her] [npc.clit+] в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.ears+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Потянувшись вниз, чтобы крепко обхватить каждый из [npc2.namePos] [npc2.ears+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.clit+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.ears+], а затем плавно потянув [npc2.herHim] вперед, вонзая [npc.her] [npc.clit+] в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение нежно лаская нижнюю часть [npc.namePos] [npc.clit] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы мягко ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] наконец не вытащится из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сужая [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] с [npc.namePos] дерзким маневром лишь на мгновение, прежде чем резко откинуть [npc2.her] голову назад и выразительно напомнить [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, все же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.clit+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность закричать и жадно глотать воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Вибрации приглушенных криков и рыданий [npc2.namePos] лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] твоему мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь бить и упираться в [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.hair(true)] освобождается, а [npc.namePos] [npc.clit+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, подступающими к [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.clit] своими [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] в конце концов не вырвется из [npc2.her] горла."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.horns+] и потянуть [npc2.her] вперед, к твоим [npc.clit+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Обхватив [npc2.namePos] [npc2.horns+], [npc.name] осторожно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглатывать [npc.her] [npc.clit+] до самого основания.",
							"Протянув руку вниз, чтобы мягко, но уверенно обхватить каждый из [npc2.namePos] [npc2.horns+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.clit+].",
							"Используя [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем осторожно потянуть [npc2.herHim] вперед, втискивая [npc.her] [npc.clit+] глубоко в [npc2.her] горло так, чтобы [npc2.her] [npc2.lips+] прижались к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо схватив [npc2.namePos] [npc2.horns+], [npc.name] яростно [npc.verb(jerk)] [npc2.her] голову вперед, заставляя [npc2.herHim] проглатывать [npc.her] [npc.clit+] до самого основания.",
							"Протянув руку вниз, чтобы схватить [npc2.namePos] [npc2.horns+], [npc.name] безжалостно [npc.verb(yank)] голову [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.clit+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем резко дергает [npc2.herHim] вперед, вгоняя [npc.her] [npc.clit+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.horns+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Потянувшись вниз, чтобы крепко обхватить каждую из [npc2.namePos] [npc2.horns+], [npc.name] размеренно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], совершая глубокий минет [npc.her] [npc.clit+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем плавно потянув [npc2.herHim] вперед, вгоняя [npc.her] [npc.clit+] в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение нежно лаская нижнюю часть [npc.namePos] [npc.clit] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы мягко ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] наконец не вытащится из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сужая [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] с [npc.namePos] дерзким маневром лишь на мгновение, прежде чем резко откинуть [npc2.her] голову назад и выразительно напомнить [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, все же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.clit+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность закричать и жадно глотать воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Вибрации приглушенных криков и рыданий [npc2.namePos] лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] твоему мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь бить и упираться в [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.hair(true)] освобождается, а [npc.namePos] [npc.clit+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, подступающими к [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.clit] своими [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] в конце концов не вырвется из [npc2.her] горла."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.antennae+] и потянуть [npc2.her] вперед, на свои [npc.clit+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.antennae+], [npc.name] нежно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Потянувшись вниз, чтобы мягко, но уверенно обхватить каждую из [npc2.namePos] [npc2.antennae+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.clit+] по самую основу.",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватить [npc2.namePos] [npc2.antennae+], а затем осторожно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.clit+] глубоко в [npc2.her] горло так, чтобы [npc2.her] [npc2.lips+] прижались к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо схватив [npc2.namePos] [npc2.antennae+], [npc.name] неистово [npc.verb(jerk)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] целиком, до самого основания.",
							"Потянувшись вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], [npc.name] безжалостно [npc.verb(yank)] [npc2.her] вперед в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.clit+] по самую основу.",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], а затем резко дернув [npc2.herHim] вперед, заталкивая [npc.her] [npc.clit+] глубоко в [npc2.her] горло так, чтобы [npc2.her] [npc2.lips+] прижались к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Обхватив [npc2.namePos] [npc2.antennae+], [npc.name] решительно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.clit+] до самого основания.",
							"Протянув руку вниз, чтобы крепко сжать каждую из [npc2.namePos] [npc2.antennae+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы глубоко заглотить [npc.her] [npc.clit+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], а затем плавно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.clit+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
									+(Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение нежно лаская нижнюю часть [npc.namePos] [npc.clit] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы мягко ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] наконец не вытащится из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сужая [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] с [npc.namePos] дерзким маневром лишь на мгновение, прежде чем резко откинуть [npc2.her] голову назад и выразительно напомнить [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, все же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.clit+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность закричать и жадно глотать воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Вибрации приглушенных криков и рыданий [npc2.namePos] лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] твоему мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь бить и упираться в [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу, когда [npc2.her] [npc2.hair(true)] освобождается, а [npc.namePos] [npc.clit+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, подступающими к [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.clit] своими [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.clit+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.clit], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.clit+] в конце концов не вырвется из [npc2.her] горла."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction THROAT_MUSCLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль горла";
		}

		@Override
		public String getActionDescription() {
			return "Сжимайте свои внутренние мышцы горла вокруг [npc2.namePos] [npc2.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"Издавая приглушенный [npc.moan], [npc.name] [npc.verb(concentrate)], напрягая дополнительные внутренние мышцы внутри [npc.her] горла вокруг [npc2.namePos] [npc2.clit+].",
						isTargetedCharacterInanimate()
							?null
							:"[npc.Name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы, выстилающие внутреннюю часть [npc.her] горла. Сжимая их вокруг [npc2.clit+] во [npc.her] рту, [npc.name] [npc.verb(cause)] [npc2.name], вызывая непроизвольный крик удовольствия.",
						"[npc.Name] [npc.verb(find)] [npc.her], издавая серию приглушенных [npc.moans], пока [npc.she] [npc.verb(concentrate)], сжимая дополнительные мышцы внутри [npc.her] горла вокруг [npc2.namePos] [npc2.clit+].",
						"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], контролируя дополнительные мышцы глубоко в [npc.her] горле, сжимая их и массируя [npc2.namePos] [npc2.clit+].");
		}
	};
	
	public static final SexAction CLIT_ORAL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Получить оральный секс клитора";
		}

		@Override
		public String getActionDescription() {
			return "Вставь свой [npc.clit+] в рот [npc2.namePos] и заставь [npc2.herHim] сосать его.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить [npc2.namePos] за головку, [npc.name] [npc.verb(line)] [npc.clitTip+] [npc.her] [npc.clit] вверх к [npc2.her] [npc2.lips+], прежде чем мягко толкнуть [npc.her] [npc.hips] вперед и скользнуть [npc.her] [npc.clit+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить [npc2.namePos] за головку, [npc.name] [npc.verb(push)] [npc.clitTip+] [npc.her] [npc.clit] о [npc2.namePos] [npc2.lips+], прежде чем медленно вбивать [npc.her] [npc.hips] в [npc2.her] [npc2.face] и осторожно проталкивать [npc.her] [npc.clit+] в [npc2.her] рот."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить [npc2.namePos] за головку, [npc.name] [npc.verb(line)] [npc.clitTip+] [npc.her] [npc.clit] вверх к [npc2.her] [npc2.lips+], прежде чем жадно толкнуть [npc.her] [npc.hips] вперед и скользнуть [npc.her] [npc.clit+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить [npc2.namePos] голову, [npc.name] [npc.verb(push)] [npc.clitTip+] [npc.her], [npc.clit] прижимая к [npc2.namePos] [npc2.lips+], а затем жадно вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и скользя [npc.her] [npc.clit+] прямо в [npc2.her] рот."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить [npc2.namePos] голову, [npc.name] [npc.verb(line)] [npc.clitTip+] [npc.her], [npc.clit] задирая к [npc2.her] [npc2.lips+], а затем грубо впечатывая [npc.her] [npc.hips] вперед и насильно втискивая [npc.her] [npc.clit+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить [npc2.namePos] голову, [npc.name] [npc.verb(push)] [npc.clitTip+] [npc.her], [npc.clit] прижимая к [npc2.namePos] [npc2.lips+], а затем грубо вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и насильно засовывая [npc.her] [npc.clit+] в [npc2.her] рот."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить [npc2.namePos] голову, [npc.name] [npc.verb(line)] [npc.clitTip+] [npc.her], [npc.clit] задирая к [npc2.her] [npc2.lips+], а затем толкая [npc.her] [npc.hips] вперед и плавно вводя [npc.her] [npc.clit+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить [npc2.namePos] голову, [npc.name] [npc.verb(push)] [npc.clitTip+] [npc.her], [npc.clit] прижимая к [npc2.namePos] [npc2.lips+], а затем вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и скользя [npc.her] [npc.clit+] в [npc2.her] рот."));
						break;
					default:
						break;
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Немного отстранившись, [npc.name] медленно [npc.verb(lower)] [npc.her] [npc.clit+] к [npc2.namePos] рту, а затем осторожно проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+] и тяжело опускается на [npc2.her] [npc2.face+].",

								"Осторожно опуская [npc.her] [npc.clit+] к [npc2.namePos] рту, [npc.name] медленно [npc.verb(allow)] [npc.her] [npc.legs] так, что они поддаются под напором [npc.herHim], пока [npc.she] с силой проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Немного отстранившись, [npc.name] быстро [npc.verb(drop)] [npc.her] [npc.clit+] к [npc2.namePos] рту, а затем жадно проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+] и тяжело опускается на [npc2.her] [npc2.face+].",

								"Быстро опуская [npc.her] [npc.clit+] к [npc2.namePos] рту, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] так, что они поддаются под напором [npc.herHim], пока [npc.she] жадно проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Немного отстранившись, [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.clit+] к [npc2.namePos] рту, а затем с силой проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+] и тяжело опускается на [npc2.her] [npc2.face+].",

								"Грубо втирая [npc.her] [npc.clit+] в [npc2.namePos] рот, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] так, что они поддаются под напором [npc.herHim], пока [npc.she] с силой проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Слегка отстранившись, [npc.name] быстро [npc.verb(drop)] [npc.her] [npc.clit+] вниз к [npc2.namePos] рту, прежде чем протолкнуть [npc.clitTip+] сквозь [npc2.her] [npc2.lips+] и рухнуть на [npc2.her] [npc2.face+].",

								"Быстро опуская [npc.her] [npc.clit+] к [npc2.namePos] рту, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] так, что они подкашиваются под [npc.herHim], пока [npc.she] проталкивает [npc.clitTip+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.clitTip+] [npc.her] [npc.clit] к [npc2.namePos] [npc2.lips+], [npc.name] мягко [npc.verb(push)] [npc.her] [npc.hips] вперед и [npc.verb(slide)] [npc.her] [npc.clit+] в [npc2.her] рот.",

								"Прижимая [npc.clitTip+] [npc.her] [npc.clit] к [npc2.namePos] [npc2.lips+], [npc.name] медленно [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], плавно вводя [npc.her] [npc.clit+] в [npc2.her] рот."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.clitTip+] [npc.her] [npc.clit] к [npc2.namePos] [npc2.lips+], [npc.name] жадно [npc.verb(push)] [npc.her] [npc.hips] вперед и [npc.verb(slide)] [npc.her] [npc.clit+] в [npc2.namePos] рот.",

								"Прижимая [npc.clitTip+] [npc.her] [npc.clit] к [npc2.namePos] [npc2.lips+], [npc.name] жадно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], вводя [npc.her] [npc.clit+] в [npc2.her] рот."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выровняв [npc.clitTip+] [npc.her] [npc.clit] по линии [npc2.namePos] [npc2.lips+], [npc.name] с силой вбивает [npc.her] [npc.hips] вперед и [npc.verb(force)] [npc.her] [npc.clit+] в [npc2.namePos] рот.",

								"Вжимая [npc.clitTip+] [npc.her] [npc.clit] в [npc2.namePos] [npc2.lips+], [npc.name] грубо [npc.verb(slam)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], заставляя [npc.her] [npc.clit+] войти в [npc2.her] рот."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Приставив [npc.clitTip+] [npc.her] [npc.clit] к [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(push)] [npc.her] [npc.hips] вперед и [npc.verb(slide)] [npc.her] [npc.clit+] в [npc2.her] рот.",

								"Прижимая [npc.clitTip+] [npc.her] [npc.clit] к [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], плавно вводя [npc.her] [npc.clit+] в [npc2.her] рот."));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], медленно продвигая [npc2.her] голову вперед, пока [npc2.she] [npc2.verb(start)] нежно посасывает [npc.namePos] [npc.clit+].",
	
								" С мягким, приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] плавно продвигает [npc2.her] голову вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] оральный секс."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], с нетерпением подавая [npc2.her] голову вперед, пока [npc2.she] [npc2.verb(start)] с наслаждением сосет [npc.namePos] [npc.clit+].",
	
								" С нетерпеливым и очень приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] с удовольствием подает [npc2.her] голову вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] минет."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], грубо толкая [npc2.namePos] голову вперед, пока [npc2.she] [npc2.verb(start)] заталкивает [npc.namePos] [npc.clit+] глубоко в [npc2.her] горло.",
	
								" С нетерпеливым и очень приглушенным [npc2.moan], [npc2.name] с силой [npc2.verb(push)] [npc2.her] голову вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] минет."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], подавая [npc2.her] голову вперед, пока [npc2.she] [npc2.verb(start)] сосет [npc.namePos] [npc.clit+].",
	
								" С приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] подает [npc2.her] голову вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] минет."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенным [npc2.sob], захлебываясь и давясь [npc.namePos] [npc.clit+], пока [npc2.she] отчаянно [npc2.verb(try)], чтобы оттянуть [npc2.her] голову от [npc.her] паха.",
	
								" С приглушенным [npc2.sob], [npc2.name] отчаянно [npc2.verb(try)], пытаясь отстраниться от [npc.namePos] [npc.clit+], булькая и задыхаясь, пока [npc2.she] [npc2.verb(squirm)] и [npc2.verb(struggle)] о [npc.herHim]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], издавая очень приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(bob)] [npc2.her] голову вверх и вниз.",
		
							" Очень приглушенный [npc2.moan] доносится из рта [npc2.namePos], и, с нетерпением обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], [npc2.she] с энтузиазмом [npc2.verb(continue)], чтобы одарить [npc.herHim] [npc2.her] оральным вниманием.",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], жадно облизывая и посасывая [npc.her] [npc.clit], пока [npc2.she] [npc2.verb(continue)], издавая приглушенные [npc2.moaning] звуки."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.clit], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(make)] приглушенные звуки протеста.",
		
							" Приглушенный [npc2.sob] вырывается из рта [npc2.namePos], когда [npc2.she] слабо [npc2.verb(try)], чтобы отстраниться, слезы текут по [npc2.her] [npc2.face], пока [npc.namePos] [npc.clit+] продолжает скользить вперед-назад по [npc2.her] [npc2.lips+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, стекающими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], пока [npc2.she] [npc2.verb(make)] приглушенные звуки протеста."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], издавая очень приглушенный [npc2.moan], пока [npc2.she] медленно [npc2.verb(bob)] [npc2.her] вверх и вниз.",
		
							" Очень приглушенный [npc2.moan] доносится из рта [npc2.namePos], и, обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], [npc2.she] [npc2.verb(continue)], чтобы одарить [npc.herHim] [npc2.her] оральным вниманием.",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], облизывая и посасывая [npc.her] [npc.clit], пока [npc2.she] [npc2.verb(continue)] издает приглушенные [npc2.moaning] звуки."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], издавая очень приглушенный [npc2.moan], пока [npc2.she] медленно [npc2.verb(bob)] [npc2.her] вверх и вниз.",
		
							" Очень приглушенный [npc2.moan] доносится из рта [npc2.namePos], и, нежно обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], [npc2.she] с любовью [npc2.verb(continue)], чтобы одарить [npc.herHim] [npc2.her] оральным вниманием.",
		
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], нежно облизывая и посасывая [npc.her] [npc.clit], пока [npc2.she] [npc2.verb(continue)] издает приглушенные [npc2.moaning] звуки."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с силой [npc2.verb(grip)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], издавая очень приглушенный [npc2.moan], пока [npc2.she] грубо [npc2.verb(bob)] [npc2.her] голову вверх и вниз.",
		
							" Из рта [npc2.namePos] доносится очень приглушенный [npc2.moan]; с силой обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.clit+], [npc2.she] грубо [npc2.verb(continue)], чтобы уделить [npc.herHim] [npc2.her] оральное внимание.",
		
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(grip)] [npc2.her] [npc2.lips+] вниз вокруг [npc.namePos] [npc.clit+], с силой облизывая и посасывая [npc.her] [npc.clit], пока [npc2.she] [npc2.verb(continue)] издает приглушенные [npc2.moaning] звуки."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction CLIT_ORAL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Нежное оральное ласкание клитора";
		}

		@Override
		public String getActionDescription() {
			return "Замри и позволь [npc2.name] посасывать твой [npc.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] мягко [npc.verb(slide)] [npc.her] [npc.clit+] мимо [npc2.namePos] [npc2.lips+], издавая тихий [npc.moan], пока [npc.she] уверенно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
						"[npc.Name] медленно [npc.verb(push)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая тихий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] горло.",
						"Мягко толкая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(continue)] получает [npc2.her] оральное внимание."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Получить оральный секс клитора";
		}

		@Override
		public String getActionDescription() {
			return "Втиснуть свой [npc.clit+] в лицо [npc2.namePos], чтобы подтолкнуть [npc2.herHim] к продолжению ласк.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] жадно [npc.verb(thrust)] [npc.her] [npc.clit+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] алчно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
						"[npc.Name] отчаянно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] горло.",
						"Энергично толкая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] счастливо [npc.verb(continue)] получает [npc2.her] оральное внимание."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Фейсфак";
		}

		@Override
		public String getActionDescription() {
			return "Грубо втиснуть свой [npc.clit+] в горло [npc2.namePos] и хорошенько оттрахать [npc2.herHim] прямо в лицо.";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.STANDING)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING)) {

				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getClitorisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("Резким, яростным толчком вперед, [npc.name] [npc.verb(bury)] [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] [npc2.face]"
												+ (isTargetedCharacterInanimate()
														?", ухмыляясь, пока [npc.she] [npc.verb(feel)] шипы, покрывающие [npc.her] ствол, раз за разом скребут по стенкам [npc2.namePos] горла."
														:", отчего из [npc2.her] [npc2.eyes] начинают катиться слезы, пока зазубрины на [npc.namePos] стержне раз за разом скребут по стенкам [npc2.her] горла."));
							break;
						case FLARED:
							descriptions.add("Резким, яростным толчком вперед, [npc.name] [npc.verb(bury)] [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] [npc2.face]"
												+ (isTargetedCharacterInanimate()
														?", ухмыляясь, пока [npc.she] [npc.verb(feel)] [npc.her], широко раздутая головка, с силой входит и выходит из [npc2.namePos]."
														:", отчего из [npc2.her] [npc2.eyes] начинают катиться слезы, пока широкая, раздутая головка [npc.namePos] с силой проталкивается вверх и вниз по [npc2.her] горлу."));
							break;
						case KNOTTED:
							descriptions.add("Резким, яростным толчком вперед, [npc.name] [npc.verb(bury)] [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] [npc2.face]"
												+ (isTargetedCharacterInanimate()
														?", ухмыляясь, пока [npc.she] [npc.verb(slam)] [npc.her] узлом раз за разом о [npc2.namePos] [npc2.lips+]."
														:", отчего из [npc2.her] [npc2.eyes] начинают катиться слезы, пока [npc.namePos] [npc.verb(slam)] [npc.her] узлом раз за разом о [npc2.her] [npc2.lips+]."));
							break;
						default:
							break;
					}
				}
				
				descriptions.add("[npc.Name] [npc.verb(grab)] края [npc2.namePos] головки, и прежде чем [npc2.she] [npc2.verb(know)], что происходит, [npc.sheIs] грубо вколачивает [npc.her] [npc.clit+] туда-сюда в [npc2.her] ротовую дыру.");
				descriptions.add("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.clit+] до самого дна [npc2.namePos] горла. Пока [npc2.she] [npc2.verb(blink)] слезы на глазах, [npc.name] [npc.verb(start)] стремительно дергается, двигая [npc.her] [npc.hips] взад-вперед, удерживая [npc2.namePos] голову обеими руками, пока [npc.she] беспощадно [npc.verb(fuck)] [npc2.her] [npc2.face].");
				descriptions.add("Схватив [npc2.namePos] за голову, [npc.name] грубо [npc2.verb(pull)] [npc2.her] лицо в [npc.her] пах, погружая [npc.her] [npc.clit+] глубоко в [npc2.her] горло, прежде чем начать безжалостно трахать [npc2.her] [npc2.face].");
				descriptions.add("Мощным толчком [npc.name] [npc.verb(hilt)] [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. Сливистый поток слюны "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, LubricationType.PRECUM)?"и предэякулята ":"")
									+"с уголков рта [npc2.her] стекает слюна, [npc.name] выгибается назад, давая [npc2.name] на мгновение судорожно вздохнуть, прежде чем начать агрессивно трахать [npc2.her] [npc2.face].");
				
				return Util.randomItemFrom(descriptions);
				
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getClitorisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("Разводя [npc.her] колени по обе стороны от головы [npc2.namePos], [npc.name] яростно [npc.verb(thrust)] вниз, зарывая [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. На мгновение прижимая основание к [npc2.her] [npc2.lips+], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] лицо, заставляя [npc2.name] издать приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(struggle)], и, извиваясь под [npc.name], [npc2.she] [npc2.verb(feel)], когда [npc2.her] горло раздирается рядом шипов, идущих вдоль краев [npc.her] [npc.clit+].");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("Разводя [npc.her] колени по обе стороны от головы [npc2.namePos], [npc.name] яростно [npc.verb(thrust)] вниз, заталкивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. На мгновение прижимая основание к [npc2.her] [npc2.lips+], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] лицо, заставляя [npc2.name] издавать приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(struggle)], чтобы вдохнуть; и, извиваясь под [npc.name], [npc2.she] [npc2.verb(feel)], когда [npc2.her] горло растягивается широкой, раздутой головкой [npc.her] [npc.clit+].");
							break;
						case KNOTTED:
							descriptions.add("Разводя [npc.her] колени по обе стороны от головы [npc2.namePos], [npc.name] яростно [npc.verb(thrust)] вниз, заталкивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. На мгновение прижимая основание к [npc2.her] [npc2.lips+], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] лицо, заставляя [npc2.name] издавать приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(struggle)], чтобы вдохнуть; и, извиваясь под [npc.name], [npc2.she] [npc2.verb(feel)], пока [npc2.her] [npc2.lips+] раз за разом получает удары толстой узлом у основания [npc.her] [npc.clit+].");
							break;
						case PREHENSILE:
						case RIBBED:
						case SHEATHED:
						case TAPERED:
						case TENTACLED:
						case VEINY:
						case OVIPOSITOR:
							break;
					}
				}
				
				if(Main.sex.getCharacterPerformingAction().hasLegs()) {
					descriptions.add("[npc.Name] [npc.verb(spread)] [npc.her] колени по обе стороны от головы [npc2.namePos], и прежде чем [npc2.she] [npc2.verb(know)] осознает, что происходит, [npc.nameIs] грубо вколачивает [npc.her] [npc.clit+] туда-сюда в [npc2.her] ротовую дыру.");
					
				} else {
					descriptions.add("[npc.Name] [npc.verb(push)] [npc.her] пах прямо на лицо [npc2.namePos], и прежде чем [npc2.she] [npc2.verb(know)] осознает, что происходит, [npc.nameIs] грубо вколачивает [npc.her] [npc.clit+] туда-сюда в [npc2.her] ротовую дыру.");
				}
				

				descriptions.add("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.clit+] до самого дна [npc2.namePos] горла. Пока [npc2.she] [npc2.verb(blink)] рыдает от боли, [npc.name] [npc.verb(start)] стремительно вколачивает [npc.her] [npc.hips] вверх-вниз, издавая всё больше [npc.moans+], пока [npc.she] безжалостно [npc.verb(fuck)] [npc2.her] [npc2.face].");

				descriptions.add("Опустившись на лицо [npc2.namePos], [npc.name] грубо вгоняет [npc.her] [npc.clit+] глубоко в [npc2.her] горло, издавая [npc.a_moan+], прежде чем начать яростно вколачивать [npc.her] [npc.hips] вверх-вниз, пока [npc.she] безжалостно [npc.verb(fuck)] лицо [npc2.her].");

				descriptions.add("Мощным толчком [npc.name] [npc.verb(hilt)] [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло. Пока слизистая струя "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, LubricationType.PRECUM)?"cummy ":"")
									+"Слюна стекает по уголкам рта [npc2.her], [npc.name] приподнимает [npc.herself], позволяя [npc2.name] на мгновение жадно глотнуть воздуха, прежде чем снова опуститься и начать агрессивно трахать [npc2.her] лицом.");

				return Util.randomItemFrom(descriptions);
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] грубо [npc.verb(slam)] [npc.her] [npc.clit+] сквозь [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] неистово [npc.verb(thrust)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"[npc.Name] яростно [npc.verb(slam)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] горло.",
							"Агрессивно толкая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(continue)] [npc2.her] лицо."));
				
				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction CLIT_ORAL_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться оральному ласканию клитора";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.clit+] от рта [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] бедра от [npc2.namePos] лица, но [npc.her] усилия оказываются напрасными, так как [npc2.she] мягко, но уверенно [npc2.verb(hold)] [npc.herHim] на месте, медленно скользя [npc2.her] [npc2.lips+] вверх и вниз по всей длине [npc.her] [npc.clit+], пока [npc2.she] [npc2.verb(continue)], уделяя [npc.herHim] [npc2.her] нежелательное оральное внимание.",
	
							"В [npc.eyes] [npc.namePos] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit+] изо рта [npc2.namePos]. Однако хватка [npc2.Her] оказывается слишком крепкой, и [npc2.name] мягко, но решительно [npc.verb(continue)] сосать [npc.her] [npc.clit+], пока [npc.she] слабо сопротивляется [npc2.herHim].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются напрасными, пока [npc2.name] [npc2.verb(continue)] уделяет [npc.herHim] [npc2.her] нежелательное оральное внимание."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Издавая [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)] попытаться оттянуть бедра [npc.her] от лица [npc2.namePos], но усилия [npc.her] оказываются тщетными, так как [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, угрожающе рыча, пока [npc2.she] с силой [npc2.verb(slide)] [npc2.her] [npc2.lips+] вверх и вниз по всей длине [npc.her] [npc.clit+].",
	
							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit+] изо рта [npc2.namePos]. Однако хватка [npc2.Her] оказывается слишком крепкой, и [npc2.name] [npc2.verb(let)] с угрожающим рычанием, пока [npc2.name] [npc2.verb(continue)] сосать [npc.her] [npc.clit+].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются напрасными, пока [npc2.name] [npc2.verb(continue)] уделяет [npc.herHim] [npc2.her] нежелательное оральное внимание."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] бедра от [npc2.namePos] лица, но [npc.her] усилия оказываются напрасными, так как [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, с жадностью двигая [npc2.her] [npc2.lips+] вверх и вниз по всей длине [npc.her] [npc.clit+], пока [npc2.she] [npc2.verb(continue)], доставляя [npc.herHim] [npc2.her] нежелательное оральное внимание.",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.clit+] изо рта [npc2.namePos]. Однако хватка [npc2.Her] оказывается слишком крепкой, и [npc2.she] с жадностью [npc.verb(continue)], чтобы сосать [npc.her] [npc.clit+], пока [npc.she] слабо сопротивляется [npc2.herHim].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются напрасными, пока [npc2.name] [npc2.verb(continue)] уделяет [npc.herHim] [npc2.her] нежелательное оральное внимание."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] бедра от [npc2.namePos] лица, но [npc.her] усилия оказываются напрасными, так как [npc2.she] [npc2.verb(move)] вперед и [npc2.verb(continue)], доставляя [npc.herHim] [npc2.her] нежелательное оральное внимание.",

						"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.clit+] изо рта [npc2.namePos]. Однако хватка [npc2.Her] оказывается слишком крепкой, и [npc2.she] с жадностью [npc.verb(continue)], чтобы сосать [npc.her] [npc.clit+], пока [npc.she] слабо сопротивляется [npc2.herHim].",

						"[npc.Sobbing+], [npc.name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются напрасными, пока [npc2.name] [npc2.verb(continue)] уделяет [npc.herHim] [npc2.her] нежелательное оральное внимание."));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Получить оральный секс клитора";
		}

		@Override
		public String getActionDescription() {
			return "Втиснуть свой [npc.clit+] в лицо [npc2.namePos], чтобы подтолкнуть [npc2.herHim] к продолжению ласк.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.clit+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
						"[npc.Name] [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(fuck)] [npc2.her] горло.",
						"Выгибаясь, [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(continue)] с удовольствием наслаждается [npc2.her] оральным вниманием."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT);
		}
		
		@Override
		public String getActionTitle() {
			return "Жадно ласкать клитор ртом";
		}

		@Override
		public String getActionDescription() {
			return "Энергично втискивай свой [npc.hips] в лицо [npc2.namePos], заставляя свой [npc.clit+] проваливаться глубоко в [npc2.her] горло.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] жадно [npc.verb(thrust)] [npc.her] [npc.clit+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] алчно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
						"[npc.Name] отчаянно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] горло.",
						"Энергично толкая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] счастливо [npc.verb(continue)] получает [npc2.her] оральное внимание."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_ORAL_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить ласкать клитор ртом";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.clit+] изо рта [npc2.namePos] и прекрати получать [npc2.her] оральное внимание.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				UtilText.nodeContentSB.append("Используя колени [npc.her], чтобы приподнять [npc.herself], [npc.name] [npc.verb(allow)] [npc.her] [npc.clit+], скользя вверх и из [npc2.namePos] рта. Слизистая нить слюны на мгновение связывает [npc2.namePos] [npc2.lips+] с [npc.clitTip+] [npc.her] [npc.clit], прежде чем порваться и упасть на [npc2.her] лицо.");
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо вонзив [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло в последний раз, [npc.name] затем [npc2.verb(pull)] [npc.her] [npc.hips] назад, ухмыляясь, пока [npc2.name] [npc2.verb(splutter)] и [npc2.verb(gasp)], жадно хватая ртом воздух.",

								"С силой вбивая [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], [npc.name] заталкивает [npc.her] [npc.clit+] глубоко в [npc2.her] горло, а затем полностью вытягивает его из [npc2.her] рта."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc.her] [npc.clit+] из [npc2.namePos] рта, [npc.name] [npc.verb(let)] [npc.a_moan+], когда [npc.she] ставит точку в этом оральном удовольствии.",

								"С помощью [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] [npc.hips] назад, полностью вытягивая [npc.her] [npc.clit+] изо рта [npc2.namePos]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], отчаянно пытаясь, пока [npc2.she] [npc2.verb(plead)], чтобы [npc.name] отпустил(а) [npc2.herHim].",
	
								" [npc2.Name] [npc2.verb(feel)], слезы текут по щекам [npc2.her], пока [npc2.she] [npc2.verb(try)], пытаясь вырваться из хватки [npc.namePos], издавая [npc2.a_sob+] и умоляя [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], обнажая желание [npc2.her] продолжать сосать [npc.namePos] [npc.clit+].",
	
								" [npc2.Name] [npc2.moanVerb], когда [npc.name] выходит из [npc2.her] рта, выдавая отчаянное желание [npc2.her] продолжать сосать [npc.her] [npc.clit+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_CLIT_ORAL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Возьми [npc2.namePos] [npc2.clit+] в рот и начни сосать его.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], пока [npc.she] медленно [npc.verb(lower)] [npc.her] голову между [npc2.her] [npc2.legs], страстно целуя [npc2.clitTip+] [npc2.her] [npc2.clit+], прежде чем заглотить [npc2.herHim] в [npc.her] рот.",

								"Мягко опуская [npc.her] голову между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] долгий, медленный лизь по всей длине [npc2.her] [npc2.clit+], прежде чем взять [npc2.clitTip+] в [npc.her] рот."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], пока [npc.she] жадно [npc.verb(drop)] [npc.her] голову между [npc2.her] [npc2.legs], страстно целуя [npc2.clitTip+] [npc2.her] [npc2.clit+], прежде чем жадно заглотить [npc2.herHim] в [npc.her] рот.",

								"Жадно опуская [npc.her] голову между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] долгий, влажный лизь по всей длине [npc2.her] [npc2.clit+], прежде чем жадно взять [npc2.clitTip+] в [npc.her] рот."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], пока [npc.she] быстро [npc.verb(drop)] [npc.her] голову между [npc2.her] [npc2.legs], грубо целуя [npc2.clitTip+] [npc2.her] [npc2.clit+], прежде чем с силой заглотить [npc2.herHim] в [npc.her] рот.",

								"Опустив голову [npc.her] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] грубым, влажным языком по всей длине [npc2.her] [npc2.clit+], прежде чем с силой заглотить [npc2.clitTip+] в [npc.her] рот."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], пока [npc.she] [npc.verb(drop)] [npc.her] голову между [npc2.her] [npc2.legs], целуя [npc2.clitTip+] [npc2.her] [npc2.clit+], прежде чем взять [npc2.herHim] в [npc.her] рот.",

								"Опустив голову [npc.her] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] долгим, влажным языком по всей длине [npc2.her] [npc2.clit+], прежде чем взять [npc2.clitTip+] в [npc.her] рот."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] медленно [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] уделяет [npc2.herHim] [npc.her] оральное внимание.",

								"Окружив [npc.her] [npc.lips+] вокруг [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] уделяет [npc2.herHim] [npc.her] оральное внимание."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] с нетерпением [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] счастливо [npc.verb(start)] оказывает [npc2.herHim] [npc.her] оральное внимание.",

								"Оборачивая [npc.her] [npc.lips+] вокруг [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] с нетерпением [npc.verb(start)] оказывает [npc2.herHim] [npc.her] оральное внимание."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] силой [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] оказывает [npc2.herHim] [npc.her] оральное внимание.",

								"Силой обхватывая [npc.her] [npc.lips+] вокруг [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] оказывает [npc2.herHim] [npc.her] оральное внимание."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] оказывает [npc2.herHim] [npc.her] оральное внимание.",

								"Оборачивая [npc.her] [npc.lips+] вокруг [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] с нетерпением [npc.verb(start)] оказывает [npc2.herHim] [npc.her] оральное внимание."));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], тихо [npc2.moaning], пока [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",
	
								" При мягком толчке [npc2.her] [npc2.hips], [npc2.name] [npc2.verb(let)] нежный [npc2.moan], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.namePos] [npc.lips+], скользящего вверх и вниз по всей длине [npc2.her] [npc2.clit+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.moaning+], пока [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",
	
								" При энергичном толчке [npc2.her] [npc2.hips], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.namePos] [npc.lips+], скользящего вверх и вниз по всей длине [npc2.her] [npc2.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.moaning+], пока [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",
	
								" При грубом [npc.verb(thrust)] [npc2.her] [npc2.hips], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.namePos] [npc.lips+], скользящего вверх и вниз по всей длине [npc2.her] [npc2.clit+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.moaning+], пока [npc.she] [npc.verb(suck)] [npc2.her] [npc2.clit+].",
	
								" [npc2.her] дергается, [npc2.hips] втискивая [npc.namePos] [npc.face], пока [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enjoy)] ощущение того, как [npc.namePos] [npc.lips+] скользит вверх и вниз по всей длине [npc2.her] [npc2.clit+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пытаясь, но безуспешно вытащить [npc2.her] [npc2.clit] изо рта [npc.namePos], пока [npc2.she] [npc2.verb(struggle)] против нежеланного орального внимания.",
	
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(struggle)] против нежеланного орального внимания, [npc2.sobbing] и извиваясь, пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а)."));
						break;
					default:
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_CLIT_ORAL_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT);
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Глубокий минет";
		}

		@Override
		public String getActionDescription() {
			return "Заглоти [npc2.namePos] [npc2.clit+] как можно глубже в горло.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко обхватив [npc.her] [npc.lips+] вокруг [npc2.clit+] во рту [npc.her], [npc.name] [npc.verb(push)] [npc.her] вперед, принимая [npc2.namePos] [npc2.clit+] так глубоко в [npc.her] горло, насколько [npc.she] только может.",

							"С мягким, приглушенным [npc.moan], [npc.name] осторожно [npc.verb(lean)] вперед, раздвигая [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] как можно больше [npc2.namePos] [npc2.clit+] в [npc.her] горло, насколько [npc.she] может.",

							"Медленно продвигая головку [npc.her] вперед, [npc.name] осторожно [npc.verb(part)] [npc.her] [npc.lips+], прежде чем заглотить [npc2.namePos] [npc2.clit+] глубоко в [npc.her] горло."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью обхватив [npc.her] [npc.lips+] вокруг [npc2.clit+] во рту [npc.her], [npc.name] быстро [npc.verb(push)] [npc.her] вперед, жадно заглатывая [npc2.namePos] [npc2.clit+] так глубоко в [npc.her] горло, насколько это только возможно для [npc.she].",

							"С приглушенным, [npc.moan+], [npc.name] жадно [npc.verb(lean)] вперед, раздвигая [npc.her] [npc.lips+], пока [npc.she] отчаянно [npc.verb(take)] как можно больше [npc2.namePos] [npc2.clit+] в [npc.her] горло, насколько хватает [npc.she].",

							"Жадно подавая [npc.her] голову вперед, [npc.name] охотно [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Силой обхватив [npc.her] [npc.lips+] вокруг [npc2.clit+] во рту [npc.her], [npc.name] грубо [npc.verb(slam)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.clit+] проникать так глубоко в глотку [npc.her], насколько это вообще возможно для [npc.she].",

							"С глухим звуком, [npc.moan+], [npc.name] быстро [npc.verb(lean)] вперед, раздвигая [npc.her] [npc.lips+], пока [npc.she] грубо [npc.verb(force)] столько [npc2.namePos] [npc2.clit+] в [npc.her] горло, сколько [npc.she] может.",

							"Агрессивно толкая [npc.her] голову вперед, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Обернув [npc.her] [npc.lips+] вокруг [npc2.clit+] в [npc.her] рту, [npc.name] быстро [npc.verb(push)] [npc.her] вперед, заглатывая [npc2.namePos] [npc2.clit+] так глубоко в [npc.her] горло, насколько это вообще [npc.she] возможно.",

							"С приглушенным, [npc.moan+] звуком, [npc.name] [npc.verb(lean)] вперед, раздвигая [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] как можно больше [npc2.namePos] [npc2.clit+] в [npc.her] горло, насколько хватает [npc.she].",

							"Проталкивая [npc.her] голову вперед, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her] горло."));
					break;
				default:
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				default:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло, издавая приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(receive)] [npc.her] оральное внимание.",
							" Приглушенный [npc2.moan] вырывается из [npc2.namePos] рта, прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло.",
							" От восторга [npc2.Moaning], [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.clit+] так глубоко в [npc.namePos] горло, насколько это возможно.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.clit] от [npc.namePos] рта, [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
							" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заталкивая [npc2.her] [npc2.clit+] глубоко в [npc.her] горло.",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.clit] от рта [npc.namePos].");
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло, издавая мягкий, приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc.her] оральное внимание.",
							" Приглушенный [npc2.moan] доносится из [npc2.namePos] рта, прежде чем [npc2.she] [npc2.verb(start)] медленно скользит [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло.",
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в горло [npc.namePos].");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] неистово [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло, издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc.her] оральное внимание.",
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], прежде чем [npc2.she] резко [npc2.verb(start)], вонзая [npc2.her] [npc2.clit+] глубоко в горло [npc.namePos].",
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit+] так глубоко, как только возможно, в горло [npc.namePos].");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло, издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc.her] ласкает губами.",
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] заталкивает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] горло.");
			}
		}
		return "";
	}
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Нежно сосать [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно обхватив [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] покачивает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] наслаждается оральными ласками.",
					"С мягким, приглушенным [npc.moan], [npc.name] нежно [npc.verb(start)] покачивает [npc.her] головой вверх-вниз, обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] наслаждается оральными ласками.",
					"Медленно покачивая [npc.her] головой вверх-вниз, [npc.name] нежно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] наслаждается оральными ласками."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Жадно сосать [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью обхватив [npc2.namePos] [npc2.clit+] своими [npc.her] [npc.lips+], [npc.name] [npc.verb(start)] стремительно двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] оказывает [npc2.herHim] [npc.her] энтузиазма.",
					"Приглушенно, с [npc.moan+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх-вниз, жадно обволакивая [npc2.namePos] [npc2.clit+] своими [npc.her] [npc.lips+], пока [npc.she] [npc.verb(give)] уделяет [npc2.herHim] [npc.her] внимание.",
					"Стремительно двигая [npc.her] головой вверх-вниз, [npc.name] отчаянно [npc.verb(wrap)] обхватывает [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] проявляет [npc2.herHim] [npc.her] рьянное внимание."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Грубо сосать [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Сильно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.clit+], [npc.name] агрессивно [npc.verb(start)] двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] оказывает [npc2.herHim] [npc.her] оральное внимание.",
					"Приглушенно, с [npc.moan+], [npc.name] неистово [npc.verb(start)] двигает [npc.her] головой вверх-вниз, грубо обволакивая [npc2.namePos] [npc2.clit+] своими [npc.her] [npc.lips+], пока [npc.she] [npc.verb(give)] уделяет [npc2.herHim] [npc.her] внимание.",
					"Грубо покачивая [npc.her] головой вверх-вниз, [npc.name] властно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] орального внимания."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction CLIT_ORAL_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться сосанию клитора";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытолкнуть [npc2.namePos] [npc2.clit+] изо рта.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], корчась и беспокойно извиваясь, пока [npc2.name] удерживает [npc.herHim] на месте, медленно скользя [npc2.her] [npc2.clit+] вперед-назад мимо [npc.her] [npc.lips+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)] потянуть [npc.her] голову назад, из-за чего [npc.her] протесты вырываются булькающими всплесками, пока [npc2.name] [npc2.verb(continue)] мягко проталкивает [npc2.her] [npc2.clit+] вниз по [npc.her] горлу.",
								"[npc.Name] [npc.verb(feel)] слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name], в то время как [npc.sobbing] в смятении наблюдает, как [npc2.name] [npc2.verb(continue)] мягко скользит [npc2.her] [npc2.clit+] внутрь и наружу [npc.her] рта."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], корчась и беспокойно извиваясь, пока [npc2.name] силой удерживает [npc.herHim] на месте, грубо толкая [npc2.her] [npc2.clit+] вперед-назад мимо [npc.her] [npc.lips+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] голову назад; [npc.her] протесты вырываются булькающими всплесками, пока [npc2.name] [npc2.verb(continue)] грубо вгоняет [npc2.her] [npc2.clit+] в [npc.her] горло.",
								"[npc.Name] [npc.verb(feel)], слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name]; [npc.sobbing] в смятении, пока [npc2.name] [npc2.verb(continue)] жадно вгоняет [npc2.her] [npc2.clit+] в [npc.her] рот и обратно."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], извиваясь и беспорядочно борясь от дискомфорта, пока [npc2.name] удерживает [npc.herHim] на месте, жадно двигая [npc2.her] [npc2.clit+] вперед-назад сквозь [npc.her] [npc.lips+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] голову назад; [npc.her] протесты вырываются булькающими всплесками, пока [npc2.name] [npc2.verb(continue)] жадно вгоняет [npc2.her] [npc2.clit+] в [npc.her] горло.",
								"[npc.Name] [npc.verb(feel)], слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name]; [npc.sobbing] в смятении, пока [npc2.name] с жадностью [npc2.verb(continue)], то и дело просовывая [npc2.her] в [npc2.clit+] и вынимая из [npc.her] рта."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], извиваясь и беспорядочно борясь от дискомфорта, пока [npc2.name] удерживает [npc.herHim] на месте, заставляя [npc2.her] [npc2.clit+] вбиваться в [npc.her] горло.",
						"[npc.a_sob+], [npc.name] [npc.verb(try)], оттягивая [npc.her] голову назад, а [npc.her] протестующие звуки вырываются булькающими всплесками, пока [npc2.name] [npc2.verb(keep)] [npc2.her] [npc2.clit+] глубоко в [npc.her] горле.",
						"[npc.Name] [npc.verb(feel)], слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name], в то время как [npc.sobbing] в смятении от того, что [npc2.name] [npc2.verb(keep)] [npc2.her] [npc2.clit+] глубоко в [npc.her] горле."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_CLIT_ORAL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать сосать [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Оборачивая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(start)] быстро двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] оральное внимание.",
					"С приглушенным, [npc.moan+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх-вниз, обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] оральное внимание.",
					"Быстро двигая [npc.her] головой вверх-вниз, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] [npc2.herHim] [npc.her] оральное внимание."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Жадно сосать [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью обхватив [npc2.namePos] [npc2.clit+] своими [npc.her] [npc.lips+], [npc.name] [npc.verb(start)] стремительно двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] оказывает [npc2.herHim] [npc.her] энтузиазма.",
					"Приглушенно, с [npc.moan+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх-вниз, жадно обволакивая [npc2.namePos] [npc2.clit+] своими [npc.her] [npc.lips+], пока [npc.she] [npc.verb(give)] уделяет [npc2.herHim] [npc.her] внимание.",
					"Стремительно двигая [npc.her] головой вверх-вниз, [npc.name] отчаянно [npc.verb(wrap)] обхватывает [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(give)] проявляет [npc2.herHim] [npc.her] рьянное внимание."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_CLIT_ORAL_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Перестать сосать клитор";
		}

		@Override
		public String getActionDescription() {
			return "Вытащить [npc2.namePos] [npc2.clit+] изо рта и перестать его сосать.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"В последний раз грубо заталкивая [npc2.namePos] [npc2.clit+] в [npc.her] глотку, [npc.name] затем [npc.verb(pull)] [npc.her] голову назад, быстро прекращая [npc.her] оральное внимание.",

							"С силой вбивая [npc.her] [npc.face] в [npc2.namePos] пах, [npc.name] [npc.verb(force)] [npc2.her] [npc2.clit+] глубоко в [npc.her] глотку, прежде чем полностью отстраниться и позволить [npc2.herHim] выскользнуть из [npc.her] рта."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вытягивая [npc2.namePos] [npc2.clit+] изо рта [npc.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(put)] конец [npc.her] оральному вниманию.",

							"С [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] голову назад, полностью вытягивая [npc2.namePos] [npc2.clit+] изо рта [npc.her]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], борясь против [npc.name], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] отпустил(а) [npc2.herHim].",
	
								" Слезы текут по щекам [npc2.namePos], пока [npc2.she] пытается вырваться из хватки [npc.namePos], издавая [npc2.a_sob+], прежде чем умолять [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], обнажая [npc2.her] желание, чтобы [npc.name] продолжал(а) сосать [npc2.her] [npc2.clit+].",
	
								" [npc2.Name] [npc2.moansVerb], когда [npc.name] отстраняется от [npc2.her] паха, выдавая [npc2.her] желание снова почувствовать, как [npc.her] [npc.lips+] обволакивает [npc2.her] [npc2.clit+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
