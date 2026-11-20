package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.CultistDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.88
 * @version 0.3.4
 * @author Innoxia
 */
public class Cultist extends NPC {

	private boolean requestedAnal = false;
//	private boolean sealedSex = false;

	public Cultist() {
		this(false);
	}
	
	public Cultist(boolean isImported) {
		super(isImported, null, null,
				"",
				Util.random.nextInt(30)+30, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				15,
				Gender.F_P_V_B_FUTANARI,
				Subspecies.DEMON,
				RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.DOMINION,
				PlaceType.DOMINION_BACK_ALLEYS,
				false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// BODY RANDOMISATION:
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
			Main.game.getCharacterUtils().addFetishes(this);
			if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
				this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
			}
			if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ZERO_HATE) {
				this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.TWO_NEUTRAL);
			}
			
			Main.game.getCharacterUtils().randomiseBody(this, true);

			this.setHistory(Occupation.NPC_CULTIST);
			
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(true);
			setDescription("Как высокопоставленному члену «Культа Lilith», этому демону очевидно демонстрировать невероятную мощь любому встречному. Впрочем, ты — не «любой», и когда ты приближаешься к ней, ты почти физически ощущаешь силу её арканной ауры, соприкасающейся с твоей...");
			
			// Set random inventory & weapons:
			resetInventory(true);
			inventory.setMoney(100);
			
			// CLOTHING:
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			initHealthAndManaToMax();
			
			setStartingCombatMoves();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(15);
			this.setHistory(Occupation.NPC_CULTIST);
			this.resetPerksMap(true);
		}
		setStartingCombatMoves();
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.LUSTPYRE,
						Perk.FETISH_SEEDER,
						Perk.ARCANE_COMBATANT),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 3),
						new Value<>(PerkCategory.ARCANE, 5)));
	}
	
	@Override
	public void setStartingCombatMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipMove("tease");
		this.equipMove("avert");
		this.equipMove("block");
		this.equipAllKnownMoves();
		this.equipAllSpellMoves();
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
		}
		
		if(setFetishes) {
		}
		
		if(setOrientation) {
		}

		if(setHistory) {
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		List<Colour> colours = new ArrayList<>();
		colours.add(PresetColour.CLOTHING_ORANGE);
		colours.add(PresetColour.CLOTHING_BLACK);
		colours.add(PresetColour.CLOTHING_PURPLE);
		colours.add(PresetColour.CLOTHING_PURPLE_LIGHT);
		Colour underwearColour = colours.get(Util.random.nextInt(colours.size()));

		colours.clear();
		colours.add(PresetColour.CLOTHING_WHITE);
		colours.add(PresetColour.CLOTHING_BLACK);
		Colour witchColour = colours.get(Util.random.nextInt(colours.size()));
		
		
		List<AbstractClothingType> clothingChoices = new ArrayList<>();
		
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_panties"));
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_thong"));
		equipClothingFromNowhere(Main.game.getItemGen().generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), underwearColour, false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_chest_lacy_plunge_bra"));
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_chest_plunge_bra"));
		equipClothingFromNowhere(Main.game.getItemGen().generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), underwearColour, false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_sock_thighhigh_socks"));
		equipClothingFromNowhere(Main.game.getItemGen().generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), witchColour, false), true, this);

		equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", witchColour, false), true, this);
		if(Math.random()<0.5) {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", witchColour, PresetColour.CLOTHING_GOLD, witchColour, false), true, this);
		} else {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", witchColour, PresetColour.CLOTHING_GOLD, witchColour, false), true, this);
		}
		if(Math.random()>0.5f) {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", witchColour, false), true, this);
		} else {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", witchColour, false), true, this);
		}
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
		}
		
		// Makeup:
		colours = Util.newArrayListOfValues(
				PresetColour.COVERING_NONE,
				PresetColour.COVERING_ORANGE,
				PresetColour.COVERING_PURPLE,
				PresetColour.COVERING_BLACK);
		
		Colour colourForCoordination = colours.get(Util.random.nextInt(colours.size()));
		Colour colourForNails = colours.get(Util.random.nextInt(colours.size()));
		
		setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, colourForCoordination));
		setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, colourForCoordination));
		setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, colourForCoordination));
		
		setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, colourForNails));
		setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, colourForNails));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	public boolean isRequestedAnal() {
		return requestedAnal;
	}

	public void setRequestedAnal(boolean requestedAnal) {
		this.requestedAnal = requestedAnal;
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return CultistDialogue.ENCOUNTER_START;
	}

	// Combat:
	
	@Override
	public String getLostVirginityDescriptor() {
		return "в её часовне";
	}

	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.getItemType().equals(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					Main.game.getPlayer().useItem(item, target, false);
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"Протягивая «[#ITEM_innoxia_pills_sterility.getName(false)]» к [npc2.name], ты велел [npc2.herHim] проглотить её, чтобы не беспокоиться о случайных беременностях. [npc2.She] сердито фыркает, но, поскольку [npc2.sheIs] не в том положении, чтобы отказываться, [npc2.she] неохотно подчиняется твоей просьбе, [npc2.speech(Это оскорбление самой Lilith...)]")
							+ "</p>");
				} else {
					itemOwner.removeItemByType(ItemType.getItemTypeFromId("innoxia_pills_sterility"));
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"Протягивая «[#ITEM_innoxia_pills_sterility.getName(false)]» к [npc2.name], ты просишь [npc2.herHim] проглотить её, чтобы не беспокоиться о случайных беременностях. С сердитым фырканьем [npc2.she] выбивает пилюлю из твоей руки, [npc2.speech(Как ты смеешь! Lilith требует, чтобы семя её последователей оставалось сильным!)]")
							+ "</p>");
				}
					
			} else if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				Main.game.getPlayer().useItem(item, target, false);
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"Протягивая "+item.getName(false, false)+" к [npc2.name], ты велишь [npc2.herHim] проглотить её. [npc2.She] издает восторженный крик и с жадностью проглатывает маленькую "+item.getColour(0).getName()+" пилюлю, [npc2.speech(Спасибо! Быть максимально фертильной — один из лучших способов поклонения Lilith!)]")
							+ "</p>");
					
				} else {
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"Протягивая "+item.getName(false, false)+" к [npc2.name], ты просишь [npc2.herHim] проглотить её. [npc2.She] издает восторженный крик и с жадностью проглатывает маленькую "+item.getColour(0).getName()+" таблетка, [npc2.speech(Хорошая игрушка! Быть максимально плодовитой — один из лучших способов поклоняться Lilith!)]")
							+ "</p>");
				}
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
	public String getSpellDescription() {
		return "<p>"
				+UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] закручивает свою метлу в гипнотическом танце, а затем резко направляет её на тебя, произнося заклинание!",
							"[npc.Name] зажимает метлу между ног и, толкая бёдра вперёд, издаёт невероятно похотливый стон, произнося заклинание!",
							"[npc.Name] пять раз резко выбрасывает метлу в воздух, после чего издаёт отчаянный стон и произносит заклинание!"))
			+"</p>";
	}
	
	public String getSeductionDescription(Attack attackType, boolean isHit) {
		return "<p>"
				+UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] бросает на тебя томный взгляд, и когда её глаза встречаются с твоими, ты слышишь в своей голове эхом раздающийся крайне похотливый стон: [npc.thought(~Ааах!~ Ты делаешь меня такой мокрой!)]",
							"[npc.Name] впивается в тебя своими большими, невинными глазами, и пока она дует губки, ты слышишь в глубине своего разума эхом отдающийся стон: [npc.thought(~Ммм!~ Трахни меня! ~Ааа!~ Моя киска мокрая и готова к тебе!)]",
							"[npc.Name] невинно дует губки, а затем посылает тебе влажный воздушный поцелуй. Когда она выпрямляется, ты чувствуешь, как призрачная пара влажных губ прижимается к твоей щеке."))
				+"</p>";
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", CultistDialogue.ENCOUNTER_CHAPEL_COMBAT_VICTORY);
		} else {
			return new Response ("", "", CultistDialogue.ENCOUNTER_CHAPEL_COMBAT_LOSS);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		return true;
	}

	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(Main.sex.isInForeplay(this)) {
			return slot==SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS || slot==SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS;
		} else {
			return slot==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS || slot==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS;
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS || Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS) {
			if(requestedAnal || !target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
			} else if(target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
			}
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS || Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS) {
			if(requestedAnal || !target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			} else if(target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			}
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex() && !target.isAsleep()) {
			if((Main.sex.isDom(Main.game.getPlayer()) || Main.sex.isSubHasEqualControl()) && !target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return UtilText.parse(target,
						"<p>Протягивая презерватив [npc.name], ты заставляешь [npc.herHim] взять его и надеть. Быстро вырывая его из маленькой фольгированной упаковки, [npc.she] натягивает его на [npc.her] [npc.cock+], пока [npc.she] скулит тебе: [npc.speech(Это оскорбление для Lilith...)]</p>");
			} else if (!target.isPlayer()){
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom(InventorySlot.PENIS)) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name] [npc.verb(direct)] [npc.her] паутинку на [npc2.namePos] [npc2.cock], но, почуяв, что [npc.sheIs] собирается сделать, [npc2.name] [npc2.verb(slap)] её в сторону и [npc2.verb(growl)], [npc2.speech(Не думаю! Ты заберешь моё семя, и тебе это чертовски понравится!)]");
				}
				return UtilText.parse(target,
						"<p>Вы достаете презерватив и пытаетесь протянуть его [npc.name], но она лишь смеется вам в лицо, когда [npc.she] хватает маленький фольгированный пакетик и разрывает его пополам, [npc.speech(Не думаю! Ты заберешь моё семя, и тебе это чертовски понравится!)]</p>");
			}
		}
		return null;
	}
	
	//TODO UNique virginity loss/dirty talk needed. Was previously using the same as DominionSuccubusAttacker, which didn't fit the situation.
	
}
