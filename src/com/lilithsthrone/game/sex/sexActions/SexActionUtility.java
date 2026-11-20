package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public class SexActionUtility {

	// GENERIC:
	
	public static final SexAction PLAYER_NONE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Ничего не делать";
		}

		@Override
		public String getActionDescription() {
			return "Не двигаться.";
		}
		
		@Override
		public String getDescription() {
			if(Main.sex.isMasturbation()) {
				return "Вы замираете, не предпринимая никаких движений...";
			}
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING) {
				List<GameCharacter> characters = new ArrayList<>(Main.sex.getAllParticipants());
				characters.remove(Main.sex.getCharacterPerformingAction());
				if(characters.size()>=2) {
					return UtilText.parse(characters,
							UtilText.returnStringAtRandom(
							"Вы остаетесь на месте, наблюдая, как [npc.name] и [npc2.name] трахаются прямо перед вами.",
							"Почти не шевелясь, вы наблюдаете, как [npc.name] и [npc2.name] развлекаются перед вами.",
							"Вы продолжаете наблюдать за [npc.name] и [npc2.name], сами при этом ничего не делая."));
				}
			}
			
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"Вы остаетесь на месте, мягко прижимаясь к [npc.name], но не предпринимая никаких попыток по отношению к [npc.herHim].",
							"Почти не двигаясь, вы прижимаетесь к [npc.name], ожидая, когда [npc.herHim] сделает следующий шаг.",
							"Вы мягко прижимаетесь к [npc.name], вполне довольный тем, что [npc.herHim] сделает следующий ход.");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"Вы остаетесь на месте, прижимаясь к [npc.name], но не предпринимая никаких попыток по отношению к [npc.herHim].",
							"Почти не двигаясь, вы прижимаетесь к [npc.name], ожидая, когда [npc.herHim] сделает следующий шаг.",
							"Вы прижимаетесь к [npc.name], вполне довольный тем, что [npc.herHim] сделает следующий шаг.");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"Вы остаетесь на месте, вжимаясь в [npc.name], но не предпринимая никаких попыток по отношению к [npc.herHim].",
							"Почти не двигаясь, вы втираетесь в [npc.name], ожидая, когда [npc.herHim] сделает следующий ход.",
							"Вы втираетесь в [npc.name], довольный тем, что [npc.herHim] возьмет инициативу на себя.");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							"Вы остаетесь на месте, прижимаясь к [npc.name], но не предпринимая никаких попыток по отношению к [npc.herHim].",
							"Почти не двигаясь, вы прижимаетесь к [npc.name], ожидая, когда [npc.herHim] сделает следующий шаг.",
							"Вы прижимаетесь к [npc.name], вполне довольный тем, что [npc.herHim] сделает следующий шаг.");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"Вы остаетесь на месте, прижимаясь к [npc.name], но не предпринимая никаких попыток по отношению к [npc.herHim].",
							"Почти не двигаясь, вы прижимаетесь к [npc.name], ожидая, когда [npc.herHim] сделает следующий шаг.",
							"Вы прижимаетесь к [npc.name], вполне довольный тем, что [npc.herHim] сделает следующий шаг.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							"Вы продолжаете сопротивляться [npc.name], отказываясь делать какие-либо движения навстречу [npc.herHim].",
							"Борясь с [pc.sobbing], вы пытаетесь вырваться из хватки [npc.namePos], с ужасом ожидая, каким будет следующий шаг [npc.her].",
							"Вы пытаетесь оттолкнуть [npc.name] от себя и [pc.sobbing], беспорядочно ерзая в тревоге и отказываясь подчиниться.");
			}

			return "Вы остаетесь на месте, вполне довольный тем, чтобы просто подождать и посмотреть, что [npc.name] предпримет дальше.";
		}
	};
	
	public static final SexAction PLAYER_CALM_DOWN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.NEGATIVE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Успокоиться";
		}

		@Override
		public String getActionDescription() {
			return "Постарайтесь успокоиться, это снизит ваше возбуждение.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Вы на мгновение отвлекаетесь от [npc.name], и в процессе успокаиваетесь.",
							"Сжав свои [pc.eyes], вы делаете глубокий вдох, успокаиваясь и снижая свое возбуждение.",
							"Сделав глубокий вдох, вы стараетесь немного успокоиться.");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Вы на мгновение отвлекаетесь от [npc.name], и в процессе успокаиваетесь.",
							"Сжав свои [pc.eyes], вы делаете глубокий вдох, успокаиваясь и снижая свое возбуждение.",
							"Сделав глубокий вдох, вы стараетесь немного успокоиться.");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Вы на мгновение отвлекаетесь от [npc.name], и в процессе успокаиваетесь.",
							"Сжав свои [pc.eyes], вы делаете глубокий вдох, успокаиваясь и снижая свое возбуждение.",
							"Сделав глубокий вдох, вы стараетесь немного успокоиться.");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Вы на мгновение отвлекаетесь от [npc.name], и в процессе успокаиваетесь.",
							"Сжав свои [pc.eyes], вы делаете глубокий вдох, успокаиваясь и снижая свое возбуждение.",
							"Сделав глубокий вдох, вы стараетесь немного успокоиться.");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Вы на мгновение отвлекаетесь от [npc.name], и в процессе успокаиваетесь.",
							"Сжав свои [pc.eyes], вы делаете глубокий вдох, успокаиваясь и снижая свое возбуждение.",
							"Сделав глубокий вдох, вы стараетесь немного успокоиться.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Все еще слабо сопротивляясь [npc.name], ты пытаешься немного успокоиться, напоминая себе, что все это скоро закончится.",
							"Сжимая [pc.eyes], ты пытаешься сделать глубокий вдох, делая вид, что всего этого не происходит, и стараясь успокоиться.",
							Main.sex.isMasturbation()?"":"Сделав глубокий вдох, ты пытаешься немного успокоиться, прежде чем продолжить борьбу с [npc.name].");
				default:
					return Main.sex.isMasturbation()?"":"Ты пытаешься сосредоточиться на чем-то другом, кроме [npc.race], с которой сейчас занимаешься сексом. Благодаря этому тебе удается немного успокоиться, снижая свое возбуждение.";
			}
		}
	};
	
	public static final SexAction PARTNER_NONE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Ничего не делать";
		}

		@Override
		public String getActionDescription() {
			return "Решить ничего не делать.";
		}

		@Override
		public String getDescription() {
			return "[npc.Name] не предпринимает никаких движений.";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_SKIP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public String getDescription() {
			return "[npc.Name] издает [npc.a_moan+].";
		}
	};

	public static final SexAction PLAYER_USE_ITEM = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Использовать предмет";
		}
		@Override
		public String getActionDescription() {
			return "Посмотреть, какие предметы можно использовать.";
		}
		@Override
		public String getDescription() {
			return Main.sex.getUsingItemText();
		}
	};
	
	public static final SexAction PARTNER_SELF_EQUIP_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private Value<AbstractClothing, String> getSexClothingBeingUsed() {
			return getSexClothingBeingUsed(Main.sex.getCharacterPerformingAction());
		}
		private Value<AbstractClothing, String> getSexClothingBeingUsed(GameCharacter performer) {
			return ((NPC)performer).getSexClothingToSelfEquip(Main.sex.getClothingSelfEquipInformation().getValue().getKey(), false);
		}
		@Override
		public boolean isQuickSexRequirementsMet(GameCharacter performer) {
			return !performer.isPlayer()
					&& Main.sex.isCanRemoveSelfClothing(performer);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexClothingBeingUsed()!=null
					&& Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(getSexClothingBeingUsed()!=null) {
				return "Надеть "+getSexClothingBeingUsed().getKey().getName();
			}
			return "Надеть одежду";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexClothingBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString() {
			return "<p>"
						+ Main.sex.getCharacterPerformingAction().equipClothingFromInventory(getSexClothingBeingUsed().getKey(), true, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction())
					+ "</p>";
		}
	};

	public static final SexAction PARTNER_EQUIP_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private Value<AbstractClothing, String> getSexClothingBeingUsed() {
			return getSexClothingBeingUsed(Main.sex.getCharacterPerformingAction());
		}
		private Value<AbstractClothing, String> getSexClothingBeingUsed(GameCharacter performer) {
			return ((NPC) performer).getSexClothingToEquip(Main.sex.getClothingEquipInformation().getValue().getKey(), false);
		}
		@Override
		public boolean isQuickSexRequirementsMet(GameCharacter performer) {
			return !performer.isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(performer, null);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexClothingBeingUsed()!=null
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), getSexClothingBeingUsed().getKey());
		}
		@Override
		public String getActionTitle() {
			if(getSexClothingBeingUsed()!=null) {
				return "Надеть "+getSexClothingBeingUsed().getKey().getName();
			}
			return "Надеть одежду";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexClothingBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString() {
			return "<p>"
						+ Main.sex.getClothingEquipInformation().getValue().getKey().equipClothingFromInventory(getSexClothingBeingUsed().getKey(), true, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction())
					+ "</p>";
		}
	};
	
	public static final SexAction PARTNER_USE_ITEM = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private Value<AbstractItem, String> getSexItemBeingUsed() {
			return ((NPC) Main.sex.getCharacterPerformingAction()).getSexItemToUse(Main.sex.getItemUseInformation().getValue().getKey());
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexItemBeingUsed()!=null;
		}
		@Override
		public String getActionTitle() {
			if(getSexItemBeingUsed()!=null) {
				return "Использовать "+getSexItemBeingUsed().getKey().getName();
			}
			return "Использовать предмет";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexItemBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString(){
			GameCharacter target = Main.sex.getItemUseInformation().getValue().getKey();
			
			if(target.equals(Main.sex.getCharacterPerformingAction())) { // If self-use, their use description forms part of the getSexItemBeingUsed() description.
				Value<AbstractItem, String> itemBeingUsed = getSexItemBeingUsed();
				Main.sex.addItemUseDenial(Main.sex.getCharacterPerformingAction(), target, itemBeingUsed.getKey().getItemType()); // Don't use the same item more than once in a scene
				return Main.sex.getCharacterPerformingAction().useItem(itemBeingUsed.getKey(), target, false, true); // Append only effects
			}
			
			// If using on NPC, the target is responsible for accepting or not:
			if(!target.isPlayer()) {
				Value<Boolean, String> result = ((NPC)target).getItemUseEffects(getSexItemBeingUsed().getKey(), Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction(), target);
				
				if(!result.getKey()) { // Make sure that this character is tracked as having refused this item (so that it can be checked and not offered again in the NPC.getSexItemToUse() method).
					Main.sex.addItemUseDenial(Main.sex.getCharacterPerformingAction(), target, getSexItemBeingUsed().getKey().getItemType());
				}
				
				return result.getValue();
			}
			
			if(Main.sex.isForcingItemUse(Main.sex.getCharacterPerformingAction(), target)) { // If forced to use item, the use description forms part of the getSexItemBeingUsed() description.
				Main.sex.getCharacterPerformingAction().useItem(getSexItemBeingUsed().getKey(), target, false, true); // Append only effects
			}
			// If using on player, and not forced, the player handles refusing or not in their own SexAction, so return nothing.
			return "";
		}
	};
	
	public static final SexAction PLAYER_ACCEPT_ITEM_FROM_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getItemUseInformation()!=null;
		}
		@Override
		public String getActionTitle() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "Принять "+item.getName(false);
		}
		@Override
		public String getActionDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return Util.capitaliseSentence(item.getUseName())+""+item.getName(false)+" "+UtilText.parse(Main.sex.getItemUseInformation().getKey(), " [npc.name] предлагает вам.");
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			AbstractItem item = Main.sex.getItemUseInformation().getValue().getValue();
			return Main.sex.getItemUseInformation().getKey().useItem(item, Main.game.getPlayer(), false, false); // Append full use + effects
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
	};

	public static final SexAction PLAYER_REFUSE_ITEM_FROM_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getItemUseInformation()!=null;
		}
		@Override
		public String getActionTitle() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "Отказаться "+item.getName(false);
		}
		@Override
		public String getActionDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "Отказаться от "+item.getUseName()+""+item.getName(false)+" "+UtilText.parse(Main.sex.getItemUseInformation().getKey(), " [npc.name] предлагает вам.");
		}
		@Override
		public String getDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return UtilText.parse(Main.sex.getItemUseInformation().getKey(),
					"Вы отказываетесь взять "+item.getName(false)+" у [npc.name]. Издав разочарованный скулеж, [npc.she] убирает "+(item.isPlural()?"их":"это")+" обратно в инвентарь [npc.her]...");
		}
		@Override
		public void applyEffects() {
			// Make sure that this character is tracked as having refused this item (so that it can be checked and not offered again in the NPC.getSexItemToUse() method):
			Main.sex.addItemUseDenial(Main.sex.getItemUseInformation().getKey(), Main.game.getPlayer(), Main.sex.getItemUseInformation().getValue().getValue().getItemType());
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
	};
	
	public static final SexAction CLOTHING_REMOVAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Управление одеждой";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return Main.sex.getUnequipClothingText();
		}
	};
	
	public static final SexAction CLOTHING_DYE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Управление одеждой";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return Main.sex.getDyeClothingText();
		}
	};
	
	public static final SexAction POSITION_SELECTION = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Новая поза";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			try {
				return PositioningMenu.positioningSB.toString();
			} catch(Exception ex) {
				System.err.println("POSITION_SELECTION: positioningSB does not exist!");
				return "";
			}
		}
		
		@Override
		public void applyEffects() {
			PositioningMenu.setNewSexManager();
			Main.sex.setSexStarted(true);
		}
	};
}
