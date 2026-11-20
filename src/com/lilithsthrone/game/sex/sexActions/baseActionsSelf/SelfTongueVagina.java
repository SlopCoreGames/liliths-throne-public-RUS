package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
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
 * @since 0.1.79
 * @version 0.4.8.5
 * @author Innoxia
 */
public class SelfTongueVagina {

	public static final SexAction SELF_CUNNILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isPenetrationNonSelfOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS) // cannot autocunnilingus if have a penis and using it
					&& (Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
							|| Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "Начать автокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Просунь свой [npc.tongue] в [npc.pussy+] и начни ласкать себя языком.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Демонстрируя впечатляющую гибкость, [npc.name] [npc.verb(bend)] вниз и ",
					"Показывая, насколько гибким является [npc.sheIsFull], [npc.name] [npc.verb(double)] наклон и ",
					"Испытывая гибкость [npc.her] на прочность, [npc.name] [npc.verb(bend)] вниз и "));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] об [npc.pussy] самой [npc.her]. Осыпая [npc.her] [npc.labia+] нежными поцелуями, [npc.she] медленно, но уверенно, [npc.verb(slide)] [npc.her] [npc.tongue+] в [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] серию нежных поцелуев в [npc.her] [npc.labia+]. Затем [npc.She] [npc.verb(give)] [npc.her] [npc.pussy+] долгим влажным мазком, прежде чем осторожно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"жадно [npc.verb(press)] [npc.her] [npc.lips+] об [npc.pussy] самой [npc.her]. Осыпая [npc.her] [npc.labia+] страстными поцелуями, [npc.she] отчаянно [npc.verb(slide)] [npc.her] [npc.tongue+] в [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] серию страстных поцелуев в [npc.her] [npc.labia+]. Затем [npc.She] [npc.verb(give)] [npc.her] [npc.pussy+] голодным мазком, прежде чем жадно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"грубо [npc.verb(grind)] [npc.her] [npc.lips+] об [npc.pussy] самой [npc.her]. Осыпая [npc.her] [npc.labia+] напористыми поцелуями, [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.tongue+] в [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] серия властных поцелуев в [npc.her] [npc.labia+]. Затем [npc.She] [npc.verb(give)] [npc.her] [npc.pussy+] грубым языком, прежде чем жадно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"жадно [npc.verb(press)] [npc.her] [npc.lips+] об [npc.pussy] самой [npc.her]. Осыпая [npc.her] [npc.labia+] страстными поцелуями, [npc.she] отчаянно [npc.verb(slide)] [npc.her] [npc.tongue+] в [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] серию страстных поцелуев в [npc.her] [npc.labia+]. Затем [npc.She] [npc.verb(give)] [npc.her] [npc.pussy+] голодным мазком, прежде чем жадно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(press)] [npc.her] [npc.lips+] против собственной [npc.her] [npc.pussy]. Осыпая поцелуями [npc.her] [npc.labia+], [npc.she] [npc.verb(slide)] [npc.her] [npc.tongue+] в [npc.her] [npc.pussy+].",

							"[npc.verb(plant)] серия поцелуев в [npc.her] [npc.labia+]. Затем [npc.She] [npc.verb(give)] [npc.her] [npc.pussy+] влажным языком, прежде чем протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction SELF_CUNNILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный аутокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Нежно лижи свою [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко вгоняя [npc.her] [npc.tongue+] как можно глубже в [npc.her] [npc.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против [npc.her] [npc.labia+] и [npc.verb(let)] приглушенный [npc.moan].",
					"Вынимая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], чтобы нежно поцеловать и облизать [npc.her] [npc.labia+], прежде чем снова податься вперед и медленно скользнуть [npc.her] [npc.tongue] в [npc.her] [npc.pussy+].",
					"Вытягивая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], медленно целуя и прижимаясь к [npc.her] [npc.labia+], прежде чем снова мягко вонзить [npc.her] [npc.tongue] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Аутокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать вгонять свой [npc.tongue] в свою [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью вонзая [npc.her] [npc.tongue+] как можно глубже в [npc.her] [npc.pussy+], при этом [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] о [npc.her] [npc.labia+] и [npc.verb(let)] приглушенный [npc.moan].",
					"Вынимая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], чтобы жадно целовать и лизать [npc.her] [npc.labia+], а затем снова податься вперед и алчно втиснуть [npc.her] [npc.tongue] в [npc.her] [npc.pussy+].",
					"Вытягивая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc.her] [npc.labia+], после чего снова с энтузиазмом вонзая [npc.her] [npc.tongue] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый автокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Грубо вонзить язык в свою [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вонзая [npc.her] [npc.tongue+] как можно глубже в [npc.her] [npc.pussy+], при этом [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] о [npc.her] [npc.labia+] и [npc.verb(let)] приглушенный [npc.moan].",
					"Вынимая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], чтобы грубо целовать и лизать [npc.her] [npc.labia+], а затем снова податься вперед и яростно вонзить [npc.her] [npc.tongue] в [npc.her] [npc.pussy+].",
					"Вытягивая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] с силой целуя и облизывая [npc.her] [npc.labia+], а затем снова грубо вонзая [npc.her] [npc.tongue] глубоко в [npc.her] [npc.pussy+]."));
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Аутокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать вгонять свой [npc.tongue] в собственную [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вгоняя [npc.her] [npc.tongue+] как можно глубже в [npc.her] [npc.pussy+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] в [npc.her] [npc.labia+] и [npc.verb(let)] приглушенный [npc.moan].",
					"Вынимая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] чтобы поцеловать и облизать [npc.her] [npc.labia+], а затем снова подаваясь вперед и скользя [npc.her] [npc.tongue] в [npc.her] [npc.pussy+].",
					"Вытягивая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] целуя и прижимаясь к [npc.her] [npc.labia+], а затем снова грубо вонзая [npc.her] [npc.tongue] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадный аутокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Энергично вгоняйте свой [npc.tongue] в собственную [npc.pussy+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью вонзая [npc.her] [npc.tongue+] как можно глубже в [npc.her] [npc.pussy+], при этом [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] о [npc.her] [npc.labia+] и [npc.verb(let)] приглушенный [npc.moan].",
					"Вынимая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], чтобы жадно целовать и лизать [npc.her] [npc.labia+], а затем снова податься вперед и алчно втиснуть [npc.her] [npc.tongue] в [npc.her] [npc.pussy+].",
					"Вытягивая [npc.her] [npc.tongue+] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc.her] [npc.labia+], после чего снова с энтузиазмом вонзая [npc.her] [npc.tongue] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_CUNNILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Прекратить аутокуннилингус";
		}
		@Override
		public String getActionDescription() {
			return "Вытащите свой [npc.tongue] из [npc.pussy+] и прекратите аутокуннилингус.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним грубым лизком [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc.her] [npc.pussy+].",
	
							"Одарив [npc.her] [npc.labia+] последним грубым поцелуем, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] подальше от [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним движением языка [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc.her] [npc.pussy+].",
	
							"Одарив [npc.her] [npc.labia+] последним влажным поцелуем, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] подальше от [npc.her] [npc.pussy+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
