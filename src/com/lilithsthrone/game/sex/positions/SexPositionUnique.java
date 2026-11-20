package com.lilithsthrone.game.sex.positions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.dominion.CultistSexActions;
import com.lilithsthrone.game.sex.sexActions.dominion.PetMounting;
import com.lilithsthrone.game.sex.sexActions.dominion.PetOral;
import com.lilithsthrone.game.sex.sexActions.dominion.PixShower;
import com.lilithsthrone.game.sex.sexActions.dominion.RalphOral;
import com.lilithsthrone.game.sex.sexActions.dominion.RoseHandHolding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * 
 * @since 0.1.97
 * @version 0.3.4
 * @author Innoxia
 */
public class SexPositionUnique {

	public static final AbstractSexPosition PET_MOUNTING = new AbstractSexPosition("Оседлан",
			2,
			true,
			null, Util.newArrayListOfValues(PetMounting.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS), Main.sex.getCharacterInPosition(SexSlotUnique.PET_MOUNTING_HUMPING),
					"[npc.NameIs] стоит на четвереньках, а [npc.has] оседлала [npc2.name], который отчаянно жаждет проникнуть в [npc.herHim] и начать вовсю трахать её.");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(SexSlotUnique.PET_MOUNTING_HUMPING, SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their tails or tentacles to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.PET_MOUNTING_HUMPING) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition PET_ORAL = new AbstractSexPosition("Оральный секс в положении лежа",
			2,
			true,
			null, Util.newArrayListOfValues(PetOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotUnique.PET_ORAL_ON_ALL_FOURS), Main.sex.getCharacterInPosition(SexSlotUnique.PET_ORAL_COCKED_LEG),
					"[npc.NameIs] стоит на четвереньках, а [npc2.namePos] [npc2.leg] нависает над [npc.her], так что лицо [npc.her] находится всего в [unit.sizes] от [npc2.namePos] [npc2.cock+].");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.PET_ORAL_ON_ALL_FOURS, SexSlotUnique.PET_ORAL_COCKED_LEG));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition UNDER_DESK_RALPH = new AbstractSexPosition("Под столом",
			2,
			false,
			null,
			Util.newArrayListOfValues(RalphOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "Вы стоите на коленях под прилавком Ральфа, и ваше лицо находится всего в [unit.sizes] от его паха.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOralRalph.getSexActionInteractions(SexSlotUnique.RALPH_SUB, SexSlotUnique.RALPH_DOM));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character taking oral can use their arms to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.RALPH_SUB
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.RALPH_DOM) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};

	public static final AbstractSexPosition OVER_DESK_RALPH = new AbstractSexPosition("На столе",
			2,
			false,
			null,
			Util.newArrayListOfValues(RalphOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "Вы склонились над прилавком Ральфа, а конь-мальчик стоит прямо у вас за спиной.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOralRalph.getSexActionInteractions(SexSlotUnique.RALPH_SUB_SEX, SexSlotUnique.RALPH_DOM_SEX));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their legs to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.RALPH_SUB_SEX
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.RALPH_DOM_SEX) {
					return Util.newHashMapOfValues(
							new Value<>(Leg.class, genericGroinForceCreampieAreas));
				}
			return null;
		}
	};
	
	public static final AbstractSexPosition SHOWER_TIME_PIX = new AbstractSexPosition("Секс в душе",
			2,
			false,
			null, Util.newArrayListOfValues(PixShower.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "Вы стоите, прижавшись лицом к одной из стенок душевой, а позади вас Пикс голодно рычит вам прямо в ухо.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.standingBehindCharacterFacingWall.getSexActionInteractions(SexSlotUnique.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX, SexSlotUnique.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition HANDS_ROSE = new AbstractSexPosition("Держание за руки",
			2,
			false,
			null, Util.newArrayListOfValues(RoseHandHolding.class, GenericOrgasms.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "Вы и кошкодевочка-горничная Роуз стоите лицом к лицу, готовые ласкать друг друга руками.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.handHolding.getSexActionInteractions(SexSlotUnique.HAND_SEX_DOM_ROSE, SexSlotUnique.HAND_SEX_SUB_ROSE));
			interactions.add(StandardSexActionInteractions.handHolding.getSexActionInteractions(SexSlotUnique.HAND_SEX_SUB_ROSE, SexSlotUnique.HAND_SEX_DOM_ROSE));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition KNEELING_ORAL_CULTIST = new AbstractSexPosition("На коленях",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "Вы стоите на коленях у ног [npc.name], а ваш [pc.face+] завис всего в [unit.sizes] от паха [npc.her].";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST, SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character taking oral can use their arms to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition MISSIONARY_ALTAR_CULTIST = new AbstractSexPosition("Миссионерская на алтаре",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			// I spent several hours trying to figure out where UtilText.parse input NPCs were set.
			// I gave up and decided to do it in here so we *KNOW* which [npc] tags are doing what.
			// There is precedent for doing this in SexPosition.java.
			
			List<GameCharacter> characters = new ArrayList<>();
			boolean isKneeling = false;
			// 0 - Lying on altar
			// 1 - Standing OR kneeling, as determined by `kneeling`
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			if (Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS));
			} else if(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS));
				isKneeling = true;
			} else {
				return "MISSIONARY_ALTAR_CULTIST: Неверное положение персонажей!";
			}

			// boolean playerIsOnTheAltar = characters.get(0).isPlayer();

			StringBuilder desc = new StringBuilder();
			desc.append("[npc.NameIs] лежит на спине на алтаре часовни, а ");
			if (isKneeling) {
				desc.append("[npc2.nameIs] стоит на коленях между [npc.hisHer] [npc.legs], готовая к оральному удовольствию с [npc.himHer] в миссионерской позе.");
			} else {
				desc.append("[npc2.nameIs] стоит между [npc.hisHer] [npc.legs], готовая к веселью с [npc.himHer] в миссионерской позе.");
			}
			return UtilText.parse(characters.get(0), characters.get(1), desc.toString());
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their legs, tails, or tentacles to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				
			// The character on top can use their body weight to force a creampie:
			} else if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition MISSIONARY_ALTAR_SEALED_CULTIST = new AbstractSexPosition("Миссионерская на алтаре",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {//TODO
			List<GameCharacter> characters = new ArrayList<>();
			boolean isKneeling = false;
			// 0 - Lying on altar
			// 1 - Standing OR kneeling, as determined by `kneeling`
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			if (Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS));
			} else if(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS));
				isKneeling = true;
			} else {
				return "MISSIONARY_ALTAR_SEALED_CULTIST: Неверное положение персонажей!";
			}

			// boolean playerIsOnTheAltar = characters.get(0).isPlayer();

			StringBuilder desc = new StringBuilder();
			desc.append("[npc.NameIs] лежит на спине на алтаре часовни, а ");
			if (isKneeling) {
				desc.append("[npc2.nameIs] стоит на коленях между [npc.hisHer] [npc.legs], готовая к оральному удовольствию с [npc.himHer] в миссионерской позе.");
			} else {
				desc.append("[npc2.nameIs] стоит между [npc.hisHer] [npc.legs], готовая к веселью с [npc.himHer] в миссионерской позе.");
			}
			return UtilText.parse(characters.get(0), characters.get(1), desc.toString());
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if(Main.sex.getSexPositionSlot(performer) == SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
				if((action.getActionType()==SexActionType.ONGOING
						|| action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED)) {
					return true;
				}
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character on top can use their body weight to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	

}
