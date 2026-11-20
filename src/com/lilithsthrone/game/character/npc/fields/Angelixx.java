package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Saellatrix;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Angelixx extends NPC {

	public Angelixx() {
		this(false);
	}
	
	public Angelixx(boolean isImported) {
		super(isImported,
				new NameTriplet("Ангеликс"), "Ловиеннемартуилани",
				"Хотя она и кажется юной, невинной суккубом, Анжеликса — чрезвычайно могущественная и опасная пешка своей матери, старшей лилин Ловьенны.",
				52, Month.MAY, 17,
				30, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx"),
				true);
		
		if(!isImported) {
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.12")) {
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.2") && !this.isDoll()) {
			this.setStartingBody(true);
			this.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx"), true);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
			this.setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.BARREN,
						Perk.FIRING_BLANKS,
						Perk.CLOTHING_ENCHANTER,
						Perk.WEAPON_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.INNOCENT,
					PersonalityTrait.COWARDLY);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ARMPIT_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_LILIN_PAWN);
		}
		
		if(setSpells) {
			this.addSpell(Spell.CLOAK_OF_FLAMES);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3);
			
			this.addSpell(Spell.PROTECTIVE_GUSTS);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3);

			this.addSpell(Spell.STONE_SHELL);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_1);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_2);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_3);
			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);

			this.addSpell(Spell.FLASH);
			this.addSpellUpgrade(SpellUpgrade.FLASH_1);
			this.addSpellUpgrade(SpellUpgrade.FLASH_2);
			this.addSpellUpgrade(SpellUpgrade.FLASH_3);

			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3);
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		
		// Body:
		this.setAgeAppearanceAbsolute(18);
		this.setTailType(TailType.NONE);
		this.setWingType(WingType.DEMON_FEATHERED);
		this.setWingSize(WingSize.ZERO_TINY.getValue());
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.ZERO_TINY.getMedianValue());
		this.setHornRows(1);
		
		// Core:
		this.setHeight(152);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_BLUE_LIGHT));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, CoveringPattern.OMBRE, CoveringModifier.SMOOTH, PresetColour.COVERING_WHITE, false, PresetColour.SKIN_LIGHT, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);

		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_FEATHER, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLONDE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.TWIN_TAILS);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_DIRTY_BLONDE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_PINK_LIGHT));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLUE_DARK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.A.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.FOUR_SLIMY);
		this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		this.setTesticleCount(2); // For if she grows one
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.addTattoo(InventorySlot.VAGINA,
				new Tattoo("innoxia_animal_hoof", PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, false,
						null, null));

		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_BLUE_LIGHT, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
		if(this.isTaur()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband_bow", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_PINK, false), InventorySlot.TAIL, true, this);
		}
		
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_SILVER, false);
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		this.equipClothingFromNowhere(ring, true, this);
		
		if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom_angelixx")) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_chemise", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_thighhigh_socks_striped", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, false), true, this);
			
		} else {
			if(!this.isTaur()) {
				AbstractClothing vstring = Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_WHITE, false);
				vstring.setPattern("irbynx_polka_dots_big");
				vstring.setPatternColours(Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE));
				this.equipClothingFromNowhere(vstring, true, this);
	
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_leg_warmers", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("nerdlinger_street_hitop_canvas_sneakers_hi_top_canvas_sneakers", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);
			}
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
	
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);
	
		}
		
		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return PresetColour.BASE_YELLOW_LIGHT.toWebHexString();
		}
		return "#fafad8";
	}

	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Sleip.class));
		children.add(Main.game.getNpc(Nir.class));
		
		return children;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	// External methods:
	
	private List<String> adjectivesUsed = new ArrayList<>();
	public void initGangMember(NPC gangMember, boolean resetAdjectives) {
		if(resetAdjectives) {
			adjectivesUsed = new ArrayList<>();
		}
		gangMember.setHistory(Occupation.NPC_MUGGER);
		gangMember.equipClothing();
		gangMember.setLocation(Main.game.getPlayerCell(), true);
		gangMember.removePersonalityTrait(PersonalityTrait.MUTE);
		adjectivesUsed.add(Main.game.getCharacterUtils().setGenericName(gangMember, "член банды", adjectivesUsed));
		
		gangMember.unequipAllWeaponsIntoVoid(true);
	}
	
	public void setDefeatedName(NPC gangMember, boolean resetAdjectives) {
		if(resetAdjectives) {
			adjectivesUsed = Util.newArrayListOfValues("knocked-out", "unconscious", "defeated");
			Collections.shuffle(adjectivesUsed);
		}
		String adjective = "defeated";
		if(adjectivesUsed.size()>0) {
			adjective = adjectivesUsed.get(0);
			adjectivesUsed.remove(0);
		}
		
		gangMember.setGenericName(adjective+" "+(gangMember.isFeminine()?gangMember.getSubspecies().getSingularFemaleName(gangMember.getBody()):gangMember.getSubspecies().getSingularMaleName(gangMember.getBody())));
	}
	
	public void setDefeatedNamesPostCombat() {
		List<NPC> enemies = Main.game.getCharactersPresent().stream().filter(npc->npc instanceof ElisAlleywayAttacker && npc.hasFoughtPlayer()).collect(Collectors.toList());
		for(int i=0;i<enemies.size();i++) {
			setDefeatedName(enemies.get(i), i==0);
		}
	}
	
	public void initVictim(NPC victim, NPC gangMember) {
		victim.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, true);
		victim.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		victim.setAnalVirgin(false);
		victim.setFaceVirgin(false);
		victim.setVaginaVirgin(false);
		
		victim.setHistory(Occupation.NPC_CAPTIVE);
		victim.unequipAllClothingIntoVoid(true, true);

		AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false);
		choker.setSticker("top_txt", "worthless");
		choker.setSticker("btm_txt", "whore");
		victim.equipClothingFromNowhere(choker, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.WRIST, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.ANKLE, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, false), true, gangMember);
		
		victim.addFluidStored(SexAreaOrifice.VAGINA, new FluidStored(gangMember, gangMember.getCum(), 250));
		victim.addFluidStored(SexAreaOrifice.MOUTH, new FluidStored(gangMember, gangMember.getCum(), 100));
		victim.addDirtySlot(InventorySlot.ANUS);
		victim.addDirtySlot(InventorySlot.CHEST);
		victim.addDirtySlot(InventorySlot.GROIN);
		victim.addDirtySlot(InventorySlot.LEG);
		victim.addDirtySlot(InventorySlot.HIPS);
		victim.addDirtySlot(InventorySlot.STOMACH);
		victim.addDirtySlot(InventorySlot.TORSO_UNDER);
		victim.addDirtySlot(InventorySlot.EYES);
		victim.addDirtySlot(InventorySlot.MOUTH);
		victim.addDirtySlot(InventorySlot.HEAD);
		
		victim.setLocation(Main.game.getPlayerCell(), true);
		victim.removePersonalityTrait(PersonalityTrait.MUTE);
		victim.setGenericName("captive");
	}

	public void initGangMemberGuards() {
		Main.game.getNpc(Sleip.class).setLocation(this);
		Main.game.getNpc(Nir.class).setLocation(this);
	}
	
	/**
	 * Banishes all gang members from ground floor (pass in 0 as argument) or first floor (pass in 1 as argument)
	 */
	public void banishGangMembers(int floor) {
		String worldId = "innoxia_fields_elis_abandoned_bakery_f0";
		if(floor==1) {
			worldId = "innoxia_fields_elis_abandoned_bakery_f1";
		}
		List<NPC> present = new ArrayList<>();
		Cell[][] cellGrid = Main.game.getWorlds().get(WorldType.getWorldTypeFromId(worldId)).getCellGrid();
		for(int i=0; i<cellGrid.length; i++) {
			for(int j=0; j<cellGrid[i].length; j++) {
				present.addAll(Main.game.getCharactersPresent(cellGrid[i][j]).stream().filter(
						npc->(npc instanceof ElisAlleywayAttacker || npc instanceof GenericSexualPartner)
							&& !npc.isSlave()
							&& !Main.game.getPlayer().getParty().contains(this)
						).collect(Collectors.toList()));
				Cell c = cellGrid[i][j];
				c.resetInventory();
			}
		}
		
		for(NPC npc : present) {
			Main.game.banishNPC(npc);
		}

		if(floor==1) {
			Main.game.getNpc(Sleip.class).returnToHome();
			Main.game.getNpc(Nir.class).returnToHome();
		}
	}
	
	private static List<String> diaryEntries = Util.newArrayListOfValues(
		"Сегодня какая-то кошкодевочка оттолкнула меня с дороги и назвала «глупенькой девчонкой». К счастью, мои мальчики были неподалеку, так что я велела им затащить её в переулок и взять вдвоем. Я приказала им кончить на неё всю, а потом спросила, кто теперь из нас глупенькая.<br/>(01/14)",
		"Сегодня вечером в баре меня снова пытались подцепить. Это был крупный, красавчик зебра-бой, а у меня, знаете ли, слабость ко всем конь-морфам, так что я позволила ему утащить меня в его вонючую каморку и жестко оттрахать прямо на кровати. Его жирный гребаный член так сладко меня растянул, а после того как он залил меня спермой, он заставил меня отсосать. Блядь, у меня уже встает от одних мыслей об этом... Наверное, позову раба, чтобы он пришел и отлупил меня на кровати...<br/>(02/14)",
		"Саэллатрикс хочет, чтобы я увеличила число похищений, но настаивает, чтобы это были одиночки, у которых нет друзей или семьи, которые могли бы заявить об их исчезновении. Моя банда делает всё возможное, неужели она не знает, как это трудно?! При таком раскладе мне придется вернуться к соблазнению людей в подворотнях, а это серьезно отнимет время, которое я провожу со своими мальчиками...<br/>(03/14)",
		"Мои хорошие мальчики, Слейп и Нир, сегодня поймали троих беженцев! В награду я велела им прийти ко мне в комнату в полночь, где я надела их любимое белье, а раб привязал меня к кровати. Блядь, они так жарили меня до самого утра, что сегодня утром я едва могу ходить...<br/>(04/14)",
		"Когда я телепортировала беженцев в лавку Саэллатрикс, одна из них назвала меня тупой маленькой сучкой. Блядь, это меня так взбесило! Пока я телепортировала остальных, я велела своим мальчикам устроить ей двойное проникновение в её грязную киску. К тому времени, как я закончила, щель этой сучки превратилась в зияющее, залитое спермой месиво. Прежде чем телепортировать и её, я пообещала, что это лишь дегустация её новой жизни...<br/>(05/14)",
		"Сегодня я ходила в банк, и какой-то мудак в холле велел мне встать в конец очереди. Я сказала ему, кто он, блядь, такой, и он осознал свою ошибку. Слишком поздно! Я добила этого тупого придурка увольнением, а после того как сварила зелье, превращающее его в здоровую, плодовитую кобылку с огромными сиськами, я отправила за ним Слейпа и Нира с приказом оставить «её» голой, беременной и перепачканной в сперме шлюхой...<br/>(06/14)",
		"Тот кентавр, который вчера использовал меня как чехол для своего члена, в итоге умудрился забеременеть меня тройней... Фу... Одни конь-морфы — это полбеды, но вынашивать демонических кентавров — это реально чертовски утомительно. Позже возьму немного материнского молока и покончу с этим. В любом случае, беременность немного портит мой вид...<br/>(07/14)",
		"Сегодня Мать прислала Саэллатрикс навестить меня. Эта сучка всё твердила, чтобы я увеличила число похищений. Она вела себя настолько властно и прямолинейно, что я не могла не возбудиться, а когда она отрастила себе жирный член, я просто не смогла устоять! Она прижала меня к земле и трахала во всю мощь, пока мои парни просто стояли и смотрели...<br/>(08/14)",
		"Некоторые члены моей банды исчезли... Должно быть, это Enforcer их схватили, или, может, это вражеская банда? Блядь, я явно не создана для бандитских войн! Сегодня ночью я соблазню одного из Enforcer и посмотрю, что смогу разузнать. Может, того здоровяка-красавчика, конь-морфа, которого я видела во время патрулирования?<br/>(09/14)",
		"Сегодня ночью я нашла того самого крупного, красавца-Enforcer, которого видела на патруле. Соблазнить его оказалось сложнее, чем я ожидала, и мне пришлось прибегнуть к телепатии, чтобы раззадорить его. Ну да ладно, в итоге я получила то, что хотела. У него не оказалось никакой информации о том, кто напал на мою банду, зато его массивный, жирный член зашел в мою горячую киску по самые яйца...<br/>(10/14)",
		"Сегодня меня ограбили в переулке! Пара псов-мальчишек потребовала у меня огни, и у одного из них был пистолет Enforcer! Знаю, я могла бы надрать им задницы, но просто не могла пошевелиться... После того как они забрали мои огни, они сказали, что отдадут мне свои узлы взамен. Блядь, у меня аж всё промокло, так что я позволила им трахать меня в позе собаки прямо там, на полу...<br/>(11/14)",
		"Видела сегодня того помощника Миноталлиса на фермерском рынке. Единороги — это прямо по моей части, так что я подкараулила его у киоска с поцелуями и соблазнила. Конечно, пришлось использовать арканное возбуждение *и* телепатию, но в итоге я его взяла. Мы нашли укромное местечко за соседним зданием, где он настоял на том, чтобы начать с минета... точнее, с кунилингуса. Ему реально удалось довести меня до оргазма одним только языком, а потом еще раз дюжину раз, когда его жирный конский член был забит в мою сочащуюся щелку по самые яйца...<br/>(12/14)",
		"Мама позвала меня сегодня обратно в Dominion, и я решила поехать на карете вместо телепортации. Огромного, мускулистого кентавра, который её вез, звали Вронти, и я перепробовала всё, чтобы он остановился и трахнул меня без смазки своим массивным конским членом. Но из него было почти не вытянуть ни слова. Блядь! Кентавр только что оттрахал меня, а я всё это время думала о Вронти...<br/>(13/14)",
		"Сегодня Нир приняла в банду нового члена. Обычно я не особо обращаю внимание на новичков, но этот — просто огромный, тупой осел. Он так меня завел, что я позволила ему затащить себя в ту грязную уборную в нашей штаб-квартире и жестко оттрахать прямо у стены. Его толстый член ощущался именно так хорошо, как я и надеялась, но этот тупой, похотливый идиот в спешке добраться до моей киски разорвал в клочья мои любимые трусики...<br/>(14/14)"
		);

	private static Set<Integer> viewedDiaryIndexes = new HashSet<>();
	private static Colour[] diaryPageColour = new Colour[] {PresetColour.BASE_BLUE_LIGHT, PresetColour.BASE_ORANGE_LIGHT, PresetColour.BASE_RED_LIGHT, PresetColour.BASE_GREEN_LIME, PresetColour.BASE_PINK_LIGHT};
	
	/**
	 * Does not repeat entries until all are seen, then restarts.
	 */
	public String getDiaryEntry() {
		List<Integer> availableIndexes = new ArrayList<>();
		for(int i=0; i<diaryEntries.size(); i++) {
			availableIndexes.add(i);
		}
		for(Integer i : viewedDiaryIndexes) {
			availableIndexes.remove(i);
		}
		if(availableIndexes.isEmpty()) {
			viewedDiaryIndexes = new HashSet<>();
			return getDiaryEntry();
		}
		
		int index = Util.randomItemFrom(availableIndexes);
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_1 && viewedDiaryIndexes.isEmpty()) {
			index = 4; // Make sure that the 'evidence' entry is the first one to be seen when the player likely checks the diary out for the first time
		}
		viewedDiaryIndexes.add(index);
		
		Colour colour = diaryPageColour[index%diaryPageColour.length];
		
		return "<span style='color:"+colour.toWebHexString()+";'>"+diaryEntries.get(index)+"</spn>";
	}
	
	public void initAsSlave() {
		this.unequipAllClothingIntoVoid(true, true);
		
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_GOLD, false);
		for(int i=0; i<33; i++) {
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_DRAIN, 0));
		}
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MINOR_DRAIN, 0));
		
		this.equipClothingFromNowhere(collar, true, Main.game.getNpc(Saellatrix.class));
		

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_RED_DARK, false), true, Main.game.getNpc(Saellatrix.class));
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_BLACK, false), true, Main.game.getNpc(Saellatrix.class));

		this.setAffection(Main.game.getPlayer(), -100);
		this.setAffection(Main.game.getNpc(Saellatrix.class), -100);
		this.setObedience(100);
	}
	
	public void growPenis() {
		if(this.isTaur()) {
			this.setPenisType(PenisType.DEMON_COMMON);
			this.clearPenisModifiers();
			this.addPenisModifier(PenetrationModifier.FLARED);
			this.setPenisSize(8);
			this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
			this.setTesticleSize(1);
			this.setPenisCumStorage(25);
			
		} else {
			this.setPenisType(PenisType.DEMON_COMMON);
			this.clearPenisModifiers();
			this.setPenisSize(4);
			this.setPenisGirth(PenetrationGirth.TWO_NARROW);
			this.setTesticleSize(0);
			this.setPenisCumStorage(5);
		}
	}
	
	public void applyPostTaurChanges() {
		if(this.isTaur()) {
			if(this.hasPenis()) {
				growPenis();
			}
			this.clearAssOrificeModifiers();
			this.addAssOrificeModifier(OrificeModifier.PUFFY);
			this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			
			this.clearVaginaOrificeModifiers();
			this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
			this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);

			this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_WHITE), true);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BLONDE), true);

			this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
			this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
			
		} else {
			boolean hadPenis = this.hasPenis();
			setStartingBody(false);
			if(hadPenis) {
				growPenis();
			}
		}
		this.equipClothing();
	}
	
	public void equipClubbingClothing() {
		this.unequipAllClothingIntoVoid(true, true);

		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_SILVER, false);
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		this.equipClothingFromNowhere(ring, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_virgin_killer_sweater", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		

		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_WHITE, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_WHITE, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_thighhigh_socks", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
	}
	
	public void spawnClubStrangers() {
		try {
			String id = Main.game.addNPC("misc.GenericSexualPartner", "stranger1");
			GameCharacter npc1 = Main.game.getNPCById(id);
			
			npc1.resetBodyToGenderPreferences(false, true);
			npc1.setName(Name.getRandomName(npc1));
			npc1.removePersonalityTraits(PersonalityCategory.SPEECH);
			npc1.setPlayerKnowsName(true);
			if(Main.game.getPlayer().isFeminine()) {
				npc1.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc1.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			}
			Main.game.getCharacterUtils().equipClothingFromOutfitType(npc1, OutfitType.CLUBBING);
			npc1.setAlcoholLevel(0.25f);
			npc1.setLocation(Main.game.getPlayer());
			npc1.setPenisVirgin(false);
			
			
			id = Main.game.addNPC("misc.GenericSexualPartner", "stranger2");
			GameCharacter npc2 = Main.game.getNPCById(id);
			
			npc2.resetBodyToGenderPreferences(false, true);
			npc2.setName(Name.getRandomName(npc2));
			npc2.removePersonalityTraits(PersonalityCategory.SPEECH);
			npc2.setPlayerKnowsName(true);
			if(Main.game.getPlayer().isFeminine()) {
				npc2.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc2.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			}
			Main.game.getCharacterUtils().equipClothingFromOutfitType(npc2, OutfitType.CLUBBING);
			npc2.setAlcoholLevel(0.25f);
			npc2.setLocation(Main.game.getPlayer());
			npc2.setPenisVirgin(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
