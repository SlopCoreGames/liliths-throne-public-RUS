package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.9
 * @author Innoxia
 */
public class RatWarrensCaptive extends NPC {

	public RatWarrensCaptive() {
		this(Gender.getGenderFromUserPreferences(Femininity.FEMININE), false);
	}
	
	public RatWarrensCaptive(Gender gender) {
		this(gender, false);
	}
	
	public RatWarrensCaptive(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public RatWarrensCaptive(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5,
				gender, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(false, 10), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM, false);

		if(!isImported) {
			// RACE:
			
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			this.setGenericName("captive");

			Main.game.getCharacterUtils().randomiseBody(this, true);
			setStartingBody(false);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
	
//			Main.game.getCharacterUtils().applyMakeup(this, true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			initPerkTreeAndBackgroundPerks();
			this.removePersonalityTrait(PersonalityTrait.MUTE);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50+Util.random.nextInt(26));
			
			this.setStartingCombatMoves();
			
			initHealthAndManaToMax();
		}
		
//		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50+Util.random.nextInt(26));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.9")) {
			this.removePersonalityTrait(PersonalityTrait.MUTE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9")) {
			setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.3.3")) {
			this.resetPerksMap(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				autoSelectPerks,
				Util.newArrayListOfValues(
						Perk.AHEGAO),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
		if(this.isSlave() && this.getOwner().isPlayer()) {
			this.setHistory(Occupation.NPC_SLAVE);
		}
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING);
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_LACTATION_SELF);

			this.addFetish(Fetish.FETISH_SIZE_QUEEN);
			this.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC); // Just to make player easier to handle
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_CAPTIVE);
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}

		this.setFaceVirgin(false);
		this.setAssVirgin(false);
		this.setVaginaVirgin(false);
		
		// Milking:
		this.setBreastSize(CupSize.FF.getMeasurement()+Util.random.nextInt(10));
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		this.setNippleCapacity(0, true);
		
		this.setMilkFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()));
		this.setBreastMilkStorage(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()+Util.random.nextInt(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()));
		this.setBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()+Util.random.nextInt(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));

		if(this.getCovering(BodyCoveringType.NIPPLES).getPrimaryColour().getDarkerLinkedColours().contains(PresetColour.SKIN_DARK)) {
			this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_DARK), false);
		}
		
		// Anus:
		if(Main.game.isAnalContentEnabled()) {
			this.addAssOrificeModifier(OrificeModifier.PUFFY);
			this.setAssWetness(Wetness.FIVE_SLOPPY);
			this.setAssDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
			this.setAssCapacity(Penis.getGenericDiameter(38, PenetrationGirth.SEVEN_FAT), true);
			this.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue());
			this.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
			if(Main.game.isAssHairEnabled()) {
				this.setPubicHair(BodyHair.FOUR_NATURAL);
			}
			if(this.getCovering(BodyCoveringType.ANUS).getPrimaryColour().getDarkerLinkedColours().contains(PresetColour.SKIN_DARK)) {
				this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_DARK), false);
			}
		}

		// Vagina:
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.setGirlcumFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()));
		this.addGirlcumModifier(FluidModifier.MUSKY);
		this.setVaginaSquirter(true);
		
		this.setVaginaDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
		this.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue());
		this.setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
		this.setVaginaCapacity(Penis.getGenericDiameter(38, PenetrationGirth.SEVEN_FAT), true);
		
		if(Main.game.isPubicHairEnabled()) {
			this.setPubicHair(BodyHair.FOUR_NATURAL);
		}
		if(this.getCovering(BodyCoveringType.VAGINA).getPrimaryColour().getDarkerLinkedColours().contains(PresetColour.SKIN_DARK)) {
			this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_DARK), false);
		}
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		if(this.getHomeWorldLocation()==WorldType.RAT_WARRENS) {
			sb.append("Эта женщина — одна из «доярок» Мёрка, и её отдают на панель любому, кто может себе это позволить. Пока Мёрк или какой-нибудь незнакомец не трахают её, эта «доярка» оправдывает своё прозвище тем, что её грудь доят с помощью машины, работающей на силе Арканы.");
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensMilkersBackground)) {
				sb.append("<br/>Расспросив Мёрка о доярках, вы выяснили, что изначально их похитили и заставили отдавать молоко против их воли. Однако со временем крысопацан полностью сломил их волю к сопротивлению — до такой степени, что теперь они отчаянно умоляют его трахнуть их...");
			}
			
		} else {
			sb.append(UtilText.parse(this,
					"Эта [npc.race] когда-то была одной из «доек» Мёрка, и её отдавали любой шлюхе, способной оплатить [npc.herHim]. Пока её не трахали Мёрк или случайные незнакомцы, эта «дойная корова» зарабатывала себе имя [npc.her] тем, что её [npc.her] груди доили машиной на энергии Арканы.<br/>Клэр сообщила тебе, что [npc.she] изначально похитил Мёрк и заставил отдавать молоко вопреки [npc.her] воле. Однако со временем крысопацан полностью сломил [npc.her] волю к сопротивлению, до такой степени, что [npc.she] так отчаянно желала продолжать дойку и трах, что у Enforcer не осталось иного выбора, кроме как поработить [npc.herHim], чтобы дать [npc.herHim] ту жизнь, которой [npc.she] так жаждала.<br/>Согласившись заботиться о [npc.herHim], Клэр передала право владения этой рабыней тебе. Теперь целиком и полностью зависит от тебя, подвергнешь ли ты [npc.herHim] тому жестокому обращению, которого [npc.she] так жаждет, или же решишь подарить [npc.herHim] совсем иной образ жизни..."));
		}
		return sb.toString();
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.clearNonEquippedInventory(false);
		this.clearTattoosAndScars();
		
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		this.equipClothingFromNowhere(collar, true, this);
		
		applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
	}
	
	
	public void applyMilkingEquipment(boolean equip, List<InventorySlot> slots) {
		if(equip) {
			if(slots.contains(InventorySlot.NIPPLE)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, this);
			}
			if(slots.contains(InventorySlot.VAGINA)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, this);
			}
			
		} else {
			for(AbstractClothing c : new ArrayList<>(this.getClothingCurrentlyEquipped())) {
				if(c.isMilkingEquipment() && slots.contains(c.getSlotEquippedTo())) {
					this.unequipClothingIntoVoid(c, true, this);
				}
			}
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate(int hour) {
		if(this.getHomeWorldLocation()==WorldType.RAT_WARRENS) {
			// If the player is not a captive, and Murk has not been enslaved, then keep rolling for sex effects:
			if(!Main.game.getPlayer().isCaptive() && !Main.game.getNpc(Murk.class).isSlave()) {
				float rnd = (float) Math.random();
				if(rnd<0.005f && Main.game.isAnalContentEnabled()) { // Average fucked once every week
					this.ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.ANUS, 20+Util.random.nextInt(100));
					
				} else if(rnd<0.05f) { // Average fucked once or twice a day
					this.ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.VAGINA, 20+Util.random.nextInt(100));
				}
			}
		}
	}
	
	@Override
	public void endSex() {
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
		return null;
	}

}
