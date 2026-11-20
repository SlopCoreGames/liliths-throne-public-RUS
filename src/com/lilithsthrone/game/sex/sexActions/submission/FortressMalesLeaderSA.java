package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressMalesLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0
					&& Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBreedingTarget() {
		return Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			GameCharacter otherTarget = null;
			if(getBreedingTarget()==null) {
				if(Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
					otherTarget = Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO);
				} else {
					otherTarget = Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN);
				}
				
			} else {
				otherTarget = Main.sex.getSexPositionSlot(getBreedingTarget())==SexSlotLyingDown.LYING_DOWN
						?Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO)
						:Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN);
			}
			
			if(!otherTarget.hasVagina() || !otherTarget.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return null;
			}
			return otherTarget;
			
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Раунд второй";
		}

		@Override
		public String getActionDescription() {
			return "Скажи [npc2.name], что ты еще не закончил!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] [npc2.hips+], [npc.name] мощно толкается вперед, погружая [npc.her] [npc.cock+] глубоко в [npc2.her] набитую спермой киску. Издав глубокий стон, когда [npc.she] чувствует, как [npc2.namePos] [npc2.labia+] бьется у самого основания [npc.her] члена, [npc.she] рычит: [npc.speechNoEffects(Сейчас мы устроим тебе двойную заправку! Хочу быть уверенным, что точно тебя оплодотворю!)]</br>Удерживая [npc.her] [npc.cock+] в рукояти [npc2.namePos] [npc2.pussy], [npc.name] достает маленький флакон с голубоватой жидкостью. Вытащив пробку [npc.her] зубами, [npc.she] быстро выпивает зелье, после чего отбрасывает пустой флакон в сторону.</br>Откинув [npc.her] [npc.hips] назад, [npc.name] снова начинает ритмично трахать [npc2.name], и каждый [npc.her] толчок сопровождается похотливым хлюпаньем, пока сперма, наполняющая [npc2.namePos] киску, вытекает вокруг [npc.her] толстого члена.</br>[npc2.Name] вскоре [npc2.verb(realise)], для чего предназначалось зелье, которое только что выпил [npc.name], так как каждый раз, когда яички [npc.race] хлопают по [npc2.her] [npc2.assSkin], они становятся заметно тяжелее и тяжелее. Чтобы подтвердить подозрения [npc2.her], [npc.name] хрипит: [npc.speech(Блядь, мои яйца снова такие тяжелые! Твою утробу сейчас зальет моим семенем!)]";
		}

		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING_SWITCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Второй раунд (смена)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Переключиться на трах [npc.name].");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Схватив [npc2.namePos] [npc2.hips+], [npc.name] отстраняется и вытягивает [npc.her] [npc.cock+] из [npc2.her] набитой спермой киски."
					+(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" Сдвинувшись в сторону, [npc.she] быстро срывает одежду, мешающую [npc3.namePos] [npc3.pussy], а затем начинает водить головкой [npc.her] члена вверх и вниз между [npc3.her] [npc3.labia+]."
							:" Сдвинувшись в сторону, [npc.she] быстро приставляет [npc.her] [npc.cock+] к [npc3.namePos] [npc3.pussy], а затем начинает водить головкой вверх и вниз между [npc3.her] [npc3.labia+].")
					+" Внезапно толкнув [npc.her] [npc.hips] вперед, [npc.she] глубоко погружает [npc.her] [npc.cock+] в [npc3.namePos] [npc2.pussy+], издавая утробный рык, когда [npc.she] проникает в [npc3.herHim], [npc.speechNoEffects(Не думай, что я про тебя забыл!)]</br></br>Держа [npc.her] [npc.cock+] рукоятью в [npc3.namePos] [npc3.pussy], [npc.name] затем достает маленький флакон, наполненный светло-голубой жидкостью. Вытащив пробку [npc.her] зубами, [npc.she] быстро осушает зелье, после чего отбрасывает пустой флакон в сторону.</br></br>Оттягивая [npc.her] [npc.hips] назад, [npc.name] начинает ритмично трахать [npc3.name], и каждый из [npc.her] толчков легко входит и выходит благодаря сперме и сокам из [npc2.namePos] киски, смазывающим [npc.her] толстый член.</br>[npc3.Name] вскоре [npc3.verb(realise)], для чего предназначалось зелье, которое только что выпил [npc.name], так как каждый раз, когда яички [npc.race] хлопают по [npc3.her] [npc3.assSkin], они ощущаются заметно все тяжелее и тяжелее. Чтобы подтвердить подозрения [npc3.her], [npc.name] хрипит: [npc.speech(Блядь, мои яйца снова такие тяжелые! Твою утробу сейчас зальет моим семенем!)]");
		}
		
		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
			GameCharacter otherTarget = getOtherTarget();
			
			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Раунд второй";
		}

		@Override
		public String getActionDescription() {
			return "Скажи [npc2.name], что ты еще не закончил!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] [npc2.hips+], [npc.name] выравнивает [npc.her] [npc.cock+] по отношению к [npc2.her] [npc2.pussy+], после чего резко подается вперед, погружая [npc.her] [npc.cock+] глубоко в набитую спермой киску [npc2.her]. Издавая глубокий стон, когда [npc.she] чувствует, как [npc2.namePos] [npc2.labia+] бьется у основания [npc.her] члена, [npc.she] рычит: [npc.speechNoEffects(Сейчас мы устроим тебе двойную заправку! Хочу быть уверенным, что точно тебя оплодотворю!)]</br>Держа [npc.her] [npc.cock+] за рукоять в [npc2.namePos] [npc2.pussy], [npc.name] достает маленький флакон, наполненный светло-голубой жидкостью. Вытащив пробку [npc.her] зубами, [npc.she] быстро осушает зелье, после чего отбрасывает пустой флакон в сторону.</br>Откинув [npc.her] [npc.hips] назад, [npc.name] снова начинает ритмично трахать [npc2.name], и каждый из [npc.her] толчков сопровождается похотливым хлюпаньем, пока сперма, наполняющая [npc2.namePos] киску, вытекает вокруг [npc.her] толстого члена.</br>[npc2.Name] вскоре [npc2.verb(realise)], для чего предназначалось зелье, которое только что выпила [npc.name], так как каждый раз, когда яички [npc.race] хлопают по [npc2.her] [npc2.assSkin], они становятся заметно тяжелее и тяжелее. Чтобы подтвердить [npc2.her] подозрения, [npc.name] кряхтит: [npc.speech(Блядь, мои яйца снова такие тяжелые! Твою утробу сейчас зальет моим семенем!)]";
		}

		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START_SWITCH = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Второй раунд (смена)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Переключиться на трах [npc.name].");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
							|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" Отступив в сторону, [npc.she] быстро срывает одежду, мешающую [npc3.namePos] [npc3.pussy], а затем начинает водить головкой [npc.her] cock вверх и вниз между [npc3.her] [npc3.labia+]."
							:" Сдвинувшись в сторону, [npc.she] быстро пристраивает [npc.her] [npc.cock+] к [npc3.namePos] [npc3.pussy], а затем начинает водить головкой вверх и вниз между [npc3.her] [npc3.labia+].")
					+" Внезапно толкнув [npc.her] [npc.hips] вперед, [npc.she] глубоко погружает [npc.her] [npc.cock+] в [npc3.namePos] [npc2.pussy+], издавая низкий рык, когда [npc.she] проникает в [npc3.herHim], [npc.speechNoEffects(Не думай, что я о тебе забыл!)]</br></br>Держа [npc.her] [npc.cock+] в [npc3.namePos] [npc3.pussy], [npc.name] затем достает маленький флакон, наполненный светло-голубой жидкостью. Вытащив пробку [npc.her] зубами, [npc.she] быстро выпивает зелье, после чего отбрасывает пустой флакон в сторону.</br></br>Откинув [npc.her] [npc.hips] назад, [npc.name] начинает ритмично трахать [npc3.name], при этом каждый из [npc.her] толчков легко входит и выходит из [npc3.her] [npc3.pussy+] благодаря сперме и сокам из [npc2.namePos] киски, смазывающим [npc.her] толстый член.</br>[npc3.Name] вскоре [npc3.verb(realise)], для чего предназначалось зелье, которое только что выпил [npc.name], так как каждый раз, когда яички [npc.race] хлопают по [npc3.her] [npc3.assSkin], они ощущаются заметно всё тяжелее и тяжелее. Чтобы подтвердить подозрения [npc3.her], [npc.name] хрипит: [npc.speech(Блядь, мои яйца снова такие тяжелые! Твою утробу сейчас зальет моим семенем!)]");
		}
		
		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
			GameCharacter otherTarget = getOtherTarget();

			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};


	
}
