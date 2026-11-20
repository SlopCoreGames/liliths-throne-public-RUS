package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
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
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLilayaDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Lyssieth extends NPC {

	public Lyssieth() {
		this(false);
	}
	
	public Lyssieth(boolean isImported) {
		super(isImported,
				new NameTriplet("Lyssieth"), "Lilithmartuilani",
				"Одна из семи старших лилин, Lyssieth — одно из самых могущественных существ в мироздании.",
				7734, Month.OCTOBER, 13,
				1000,
				Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE,
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(true);
			//TODO spells
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5")) {
			this.setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(1000);
			this.setHistory(Occupation.NPC_ELDER_LILIN);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setStartingBody(false);
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.0.10")) {
			this.setStartingBody(false);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_INCEST);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_ELDER_LILIN);
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		
		this.setPiercedEar(true);
		AbstractClothing earrings = null;
		for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceAbsolute(45);
//		this.setTailType(TailType.DEMON_COMMON);
//		this.setWingType(WingType.DEMON_COMMON);
//		this.setHornType(HornType.CURLED);
		this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);

		// Core:
		this.setHeight(184);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, true, PresetColour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.clearAssOrificeModifier();
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisType(PenisType.NONE);
		this.clearPenisModifiers();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.clearVaginaOrificeModifiers();
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_half_rim_glasses", PresetColour.CLOTHING_BROWN_VERY_DARK, PresetColour.CLOTHING_BRASS, PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_plunge_blouse", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_asymmetrical_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_strappy_sandals", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_RED_DARK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);
	}

	@Override
	public Colour getSpeechGlowColour() {
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return PresetColour.BASE_PINK_LIGHT;
		}
		return null;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#71009E";
		}
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return "#FF99F8";
		}
		return "#E194FF";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public int getLevel() {
		return 1000;
	}
	
//	@Override
//	public void turnUpdate() {
//		if(!Main.game.getCharactersPresent().contains(this)) {
//			this.setStartingBody(false);
//		}
//	}
	
	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Lilaya.class));
		children.add(Main.game.getNpc(DarkSiren.class));
		if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
			children.add(Main.game.getPlayer());
		}
		
		return children;
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getTorsoType().getRace()==Race.HUMAN) {
			return "LyssiethЧеловек";
			
		} else {
			return "Lyssiethдемон";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SALyssiethSpecials.class);
	}

	private void setPlayerToPartialDemon() {
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);
		Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, false, PresetColour.EYE_YELLOW, false));
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		
		Main.game.getPlayer().setTailType(TailType.DEMON_COMMON);
		Main.game.getPlayer().setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		Main.game.getPlayer().setHornType(HornType.SWEPT_BACK);
		Main.game.getPlayer().setHornLength(HornLength.ONE_SMALL.getMedianValue());
		Main.game.getPlayer().setMinimumHornsPerRow(2);
		Main.game.getPlayer().getLegConfiguration().setWingsToDemon(Main.game.getPlayer());
		Main.game.getPlayer().setEarType(EarType.DEMON_COMMON);
		Main.game.getPlayer().setEyeType(EyeType.DEMON_COMMON);
		Main.game.getPlayer().setHairType(HairType.DEMON);
	}
	
	private void setPlayerToFullDemon() {
		Main.game.getPlayer().setTorsoType(TorsoType.DEMON_COMMON);
		Main.game.getPlayer().setFaceType(FaceType.DEMON_COMMON);
		Main.game.getPlayer().setSubspeciesOverride(Subspecies.DEMON);
		Main.game.getPlayer().setArousal(100, true);
		if(Main.game.getPlayer().hasPenis()) {
			Main.game.getPlayer().fillCumToMaxStorage();
		}
		
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		
		// Change perk to demon version:
		Main.game.getPlayer().handleDemonicTransformationPerkEffects();
	}

	public void setDaughterToFullDemon(Class<? extends NPC> daughterClass) {
		setDaughterToFullDemon(daughterClass, true);
	}
	
	public void setDaughterToFullDemon(Class<? extends NPC> daughterClass, boolean includeArousalChanges) {
		setDaughterDemonicBodyParts(Main.game.getNpc(daughterClass));
		
		if(includeArousalChanges) {
			Main.game.getNpc(Lilaya.class).setArousal(100);
			Main.game.getPlayer().setArousal(100, true);
			if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(Main.game.getNpc(DarkSiren.class))) {
				Main.game.getNpc(DarkSiren.class).setArousal(100);
			}
		}
		
		Main.game.getNpc(daughterClass).loadImages(true);
	}
	
	public static void setDaughterDemonicBodyParts(GameCharacter daughter) {
		daughter.setAssType(AssType.DEMON_COMMON);
		daughter.setBreastType(BreastType.DEMON_COMMON);
		daughter.setArmType(ArmType.DEMON_COMMON);
		daughter.getLegConfiguration().setLegsToDemon(daughter);
		daughter.setTorsoType(TorsoType.DEMON_COMMON);
		daughter.setFaceType(FaceType.DEMON_COMMON);
		daughter.setSubspeciesOverride(Subspecies.DEMON);

		daughter.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		daughter.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
	}
	
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(SexActionInterface sexAction, OrgasmCumTarget target, boolean applyExtraEffects, String description) {
		if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) { // Vision scene:
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			// Gaining Lyssieth's power:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "SEX_ORGASM_GAINING_POWER"));
			}
			
			return new SexActionOrgasmOverride(true) {
				@Override
				public String getDescription() {
					return sb.toString();
				}
				@Override
				public void applyEffects() {
					if(applyExtraEffects) {
						Main.game.getPlayer().setArousal(50);
					}
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
				}
			};
			
		} else if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) { // Demon TF scene:
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}
			
			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==0) {
				// Stage 1) Player is sucking Lyssieth's cock:
				if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_BLOWJOB"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(20);
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.MASCULINE.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE.getMaximumFemininity());
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.ANDROGYNOUS.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMaximumFemininity());
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
					
				// Stage 1) Player is eating Lyssieth out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.TONGUE)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_CUNNILINGUS"));
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(20);
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.MASCULINE.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE.getMaximumFemininity());
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.ANDROGYNOUS.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMaximumFemininity());
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
						
				// Stage 1) Lyssieth is sucking player's cock:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_BLOWJOB_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								Main.game.getPlayer().setHornType(HornType.CURVED);
								Main.game.getPlayer().setMinimumHornsPerRow(2);
								if(!Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(-20);
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
	
				// Stage 1) Lyssieth is eating the player out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
				}
				
			} else if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1) {
				// Stage 2) Lyssieth is fucking the player:
				if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_PUSSY_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};

				// Stage 2) Lyssieth is fucking the player's ass:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
					
				// Stage 2) The player is fucking Lyssieth:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_FUCKING_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
						@Override
						public void applyEndEffects() {
							if(applyExtraEffects) {
								Main.sex.setCreampieLockedBy(Main.game.getPlayer(), new Value<>(Main.game.getNpc(Lyssieth.class), Leg.class));
							}
						}
					};

				// Stage 2) Scissoring:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.CLIT, Main.game.getPlayer()).contains(SexAreaPenetration.CLIT)) {
					if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SCISSORING) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_SCISSOR_PC_BOTTOM"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_SCISSOR_PC_TOP"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
				}
				
			} else {
				// Stage 3) The player is fucking/breeding Lyssieth:
				if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_BREEDING_LYSSIETH"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_FUCKING_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
						@Override
						public void applyEndEffects() {
							if(applyExtraEffects) {
								if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
									Main.sex.setCreampieLockedBy(Main.game.getPlayer(), new Value<>(Main.game.getNpc(Lyssieth.class), Leg.class));
								} else {
									Main.sex.setCreampieLockedBy(Main.game.getPlayer(), new Value<>(Main.game.getNpc(Lyssieth.class), Tail.class));
								}
							}
						}
					};

				// Stage 3) Lyssieth is fucking the player:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_PUSSY_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};

				// Stage 3) Lyssieth is fucking the player's ass:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_ASS_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
					
				// Stage 3) oral:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SIXTY_NINE) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_BLOWJOB_LYSSIETH_TOP"));
					} else if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.LYING_DOWN) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_BLOWJOB_LYSSIETH_BOTTOM"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_BLOWJOB_FROM_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
	
				// Stage 3) Lyssieth is eating the player out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SIXTY_NINE) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_CUNNILINGUS_LYSSIETH_TOP"));
					} else if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.LYING_DOWN) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_SIXTY_NINE_CUNNILINGUS_LYSSIETH_BOTTOM"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
				}
			}
			
		} else if(Main.sex.getSexManager() instanceof SMLilayaDemonTF) { // TF Lilaya or Meraxis into full demons
			StringBuilder sb = new StringBuilder();
			if(description!=null) {
				sb.append(description);
			} else {
				sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			}

			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			// Lyssieth is fucking Lilaya's pussy:
			if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.VAGINA) && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "LILAYA_DEMON_TF_VAGINA"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(Lilaya.class);
						}
					}
				};
				
			// Lyssieth is fucking Lilaya's ass:
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.ANUS) && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "LILAYA_DEMON_TF_ANUS"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(Lilaya.class);
						}
					}
				};
			
			// Lyssieth is fucking Meraxis's ass:
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(DarkSiren.class)).contains(SexAreaOrifice.VAGINA) && Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "MERAXIS_DEMON_TF_VAGINA"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(DarkSiren.class);
						}
					}
				};
				
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(DarkSiren.class)).contains(SexAreaOrifice.ANUS) && Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "MERAXIS_DEMON_TF_ANUS"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(DarkSiren.class);
						}
					}
				};
			}
		}

		return super.getSexActionOrgasmOverride(sexAction, target, applyExtraEffects, description); // Normal scene
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) {
			if(Main.sex.getNumberOfOrgasms(this)==0) { // Only need to override the start, as preferences are set in the class SALyssiethSpecials after this.
				if(Main.sex.getSexPositionSlot(this)==SexSlotStanding.STANDING_DOMINANT) {
					return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
				} else {
					if(target.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
					}
				}
				
			} else if((Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.SIXTY_NINE || Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.SIXTY_NINE)
					&& !this.hasPenis()) {
				if(Main.sex.getOngoingCharactersUsingAreas(this, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE).contains(target)) {
					if(target.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
					}
				} else {
					return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
				}
			}
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) {
			return getForeplayPreference(target);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public void endSex() {
//		this.setPenisType(PenisType.NONE);
	}

	@Override
	public int getOrgasmsBeforeSatisfied() {
		return 3;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character.isPlayer() && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
			return Util.newHashSetOfValues(Relationship.Parent);
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}
	
	public void growCock(AbstractPenisType type) {
		this.setPenisType(type);
		this.setPenisVirgin(false);
		if(type.getRace()==Race.HUMAN) {
			this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
			this.setPenisSize(18);
			this.setTesticleSize(TesticleSize.THREE_LARGE);
			this.setPenisCumStorage(500);
		} else {
			this.setPenisGirth(PenetrationGirth.FIVE_THICK);
			this.setPenisSize(30);
			this.setTesticleSize(TesticleSize.FOUR_HUGE);
			this.setPenisCumStorage(2500);
		}
		this.fillCumToMaxStorage();
	}
	
	public void setLilinBody() {
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);

		this.setPiercedEar(true);
		AbstractClothing earrings = null;
		for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceAbsolute(45);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.FOUR_HUGE.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());
		this.setLegType(LegType.DEMON_COMMON);

		// Core:
		this.setHeight(196);
		this.setFemininity(100);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, true, PresetColour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
//			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.clearAssOrificeModifier();
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addAssOrificeModifier(OrificeModifier.RIBBED);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.growCock(PenisType.DEMON_COMMON);
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.RIBBED);
		this.addPenisModifier(PenetrationModifier.PREHENSILE);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.clearVaginaOrificeModifiers();
		this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addVaginaOrificeModifier(OrificeModifier.RIBBED);
		
		// Feet:
		// Foot shape
	}
	
	public static boolean isPlayersMommy() {
		return Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
				&& (Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth") || (Main.game.isInSex() && Main.sex.getSexManager() instanceof SMLyssiethDemonTF));
	}
	
	@Override
	public String getDirtyTalkVaginaPenetrated(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkVaginaPenetrated(target, isPlayerDom);
		}
		String returnedLine = "";
		
		if(getVaginaType()!=VaginaType.NONE) {
			if(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.VAGINA) != null) {
				switch(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.VAGINA)) {
					case FINGER:
						returnedLine = UtilText.returnStringAtRandom(
								"Да! Хороший [npc2.girl]! Продолжай ласкать мамочку пальцами!",
								"Так держать! Киска мамочки обожает твое внимание!",
								"О да! Мамочке так нравится, когда ей перебирают пальцами!");
						break;
					case PENIS:
						returnedLine = UtilText.returnStringAtRandom(
								"Да! Трахни меня! Всади мамочке свой член! Не останавливайся!",
								"Не останавливайся! Сильнее! Продолжай трахать мамочку! Да, да, да!",
								"О да! Трахни меня! Мамочке так нравится твой [npc2.cock]!");
						break;
					case TAIL:
						returnedLine = UtilText.returnStringAtRandom(
								"Да! Трахни меня! Дай мамочке свой хвост! Не останавливайся!",
								"Не останавливайся! Сильнее! Продолжай трахать мамочку! Да, да, да!",
								"О да! Трахни меня! Мамочка обожает твой [npc2.tail]!");
						break;
					case TONGUE:
						returnedLine = UtilText.returnStringAtRandom(
								"Да, мамочке нравится твой [npc2.tongue]! Не останавливайся!",
								"Не останавливайся! Засунь этот язык поглубже в мамочкину киску! Да, да, да!",
								"О да! Попробуй мамочкину киску на вкус! Глубже! Хороший [npc2.girl]!");
						break;
					default:
						returnedLine =  UtilText.returnStringAtRandom(
								"Блядь!",
								"Да!",
								"О да!");
						break;
				}
			}
		}
		
		if(returnedLine.isEmpty()) {
			return null;
		}

		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getDirtyTalkMouthPenetrated(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkMouthPenetrated(target, isPlayerDom);
		}
		String returnedLine = "";

		if(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.MOUTH) != null) {
			switch(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.MOUTH)) {
				case FINGER:
					returnedLine = UtilText.returnStringAtRandom(
							"Мамочке нравится вкус твоего [npc2.fingers]! Не останавливайся!",
							"Продолжай! Мамочке так нравится сосать твой [npc2.fingers]!",
							"О да! Мамочке так нравится вкус твоего [npc2.fingers]!");
					break;
				case PENIS:
					returnedLine = UtilText.returnStringAtRandom(
							"О да! Мамочке так нравится твой [npc2.cock]! Трахни ей в рот, как хороший [npc2.girl]!",
							"Не останавливайся! Сильнее! Трахни мамочке в глотку! Да, да, да!",
							"О да! Мамочке так нравится твой [npc2.cock]! Ты такой вкусный!");
					break;
				case TAIL:
					returnedLine = UtilText.returnStringAtRandom(
							"О да! Мамочке так нравится твой [npc2.tail]! Трахни ей в рот, как хороший [npc2.girl]!",
							"Не останавливайся! Сильнее! Трахни мамочке в глотку! Да, да, да!",
							"О да! Мамочке так нравится твой [npc2.tail]! Ты такой вкусный!");
					break;
				case TONGUE:
					returnedLine = UtilText.returnStringAtRandom(
							"Хороший [npc2.girl]! Покажи мамочке, как сильно ты её любишь!",
							"Да! Хороший [npc2.girl]!",
							"Молодец, [npc2.girl]! Мамочке так нравится твоя [npc2.lips]!");
					break;
				default:
					returnedLine = UtilText.returnStringAtRandom(
							"Блядь!",
							"Да!",
							"О да!");
					break;
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkPenisPenetrating(target, isPlayerDom);
		}
		
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target)) {
				switch(orifice) {
					case ANUS:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке так нравится твоя задница! Принимай её член, как послушная [npc2.girl]!",
								"О да, пусть мамочка оттрахает твою милую маленькую попку! Да, да, да!",
								"О да! Хорошая [npc2.girl]! Мамочке так нравится твоя задница!"));
						break;
					case ARMPITS:
					case ASS:
						break;
					case BREAST_CROTCH:
						break;
					case BREAST:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке так нравится твоя [npc2.breasts]! Обслуживай её член, как послушная [npc2.girl]!",
								"О да, пусть мамочка оттрахает твои сиськи! Да, да, да!",
								"О да! Хорошая [npc2.girl]! Мамочке так нравятся твои сиськи!"));
						break;
					case MOUTH:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Соси мамочкин член, как хорошая [npc2.girl]! Вот так!",
								"О да! Облепи этими губками мамочкин член! Не останавливайся!",
								"Соси мамочкин член! Да! Хорошая [npc2.girl]!"));
						break;
					case NIPPLE:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Принимай мамочкино хуй! Хорошая [npc2.girl]!",
								"Мамочке так нравится трахать твои сиськи! Принимай мой хуй, хорошая [npc2.girl]!",
								"О да! Принимай мамочкино хуй! Твои сиськи так приятно трахать!"));
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Принимай мамочкино хуй! Твоя киска принадлежит мне!",
								"Хорошая [npc2.girl]! Принимай мамочкино хуй глубоко в свою маленькую киску!",
								"О да! Позволь мамочке наполнить твою киску своим хуем! Хорошая [npc2.girl]!"));
						break;
					case SPINNERET:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Принимай мамочкино хуй! Твой паучий аппарат принадлежит мне!",
								"Хорошая [npc2.girl]! Принимай мамочкино хуй глубоко в свой маленький паучий аппарат!",
								"О да! Позволь мамочке наполнить твой паучий аппарат своим хуем! Хорошая [npc2.girl]!"));
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		
		String returnedLine = Util.randomItemFrom(availableLines);
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getDirtyTalkTonguePenetrating(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkTonguePenetrating(target, isPlayerDom);
		}
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target)) {
				switch(orifice) {
					case ANUS:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке нравится твоя жопка! Чувствуй, как мой [npc1.tongue] входит глубоко!",
								"О да! Пусть мамочка лизнет твою попку! Да, да, да!",
								"О да! Обожаю лизать зад! Дай мамочке [npc1.tongue] хорошенько и поглубже!"));
						break;
					case ARMPITS:
					case ASS:
						break;
					case BREAST_CROTCH:
						break;
					case BREAST:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке так нравятся твои сиськи! Дай ей пососать твои соски!",
								"О да! Пусть мамочка пососет твои соски! Да, да, да!",
								"О да! Мамочка обожает твои сиськи!"));
						break;
					case MOUTH:
						availableLines.add(UtilText.returnStringAtRandom(
								"О да! Твои губки такие вкусные! Мамочке нужно еще!",
								"Мамочка обожает целовать тебя! Да, да, да!",
								"О да! Твои губки такие вкусные!"));
						break;
					case NIPPLE:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке так нравятся твои сиськи! Дай ей пососать твои соски!",
								"О да! Пусть мамочка пососет твои соски! Да, да, да!",
								"О да! Мамочке так нравятся твои соски! Позволь ей [npc1.tongue] как следует глубоко!"));
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке так нравится обслуживать твою киску! Хороший [npc2.girl]!",
								"О да! Позволь мамочке отсосать тебе! Да, да, да!",
								"О да! Мамочке так нравится вкус твоей киски! Почувствуй, как её [npc1.tongue] входит глубоко-глубоко!"));
						break;
					case SPINNERET:
						availableLines.add(UtilText.returnStringAtRandom(
								"Да! Мамочке так нравится обслуживать твой паутинный аппарат! Хороший [npc2.girl]!",
								"О да! Позволь мамочке облизать твой паутинный аппарат! Да, да, да!",
								"О да! Мамочке так нравится вкус твоего паутинного аппарата! Почувствуй, как её [npc1.tongue] входит глубоко-глубоко!"));
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		
		String returnedLine = Util.randomItemFrom(availableLines);
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}

	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer() || penetrating != SexAreaPenetration.PENIS || (penetrated != SexAreaOrifice.VAGINA && penetrated != SexAreaOrifice.ANUS)) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(penetrated == SexAreaOrifice.VAGINA) {
			if(Main.game.getPlayer().hasHymen()) {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("Когда [npc.name] [npc.her] [npc.cock+] в твою [pc.pussy+], ты не можешь сдержать отчаянного, дрожащего вопля. Будучи столь очарованной идеей оставаться чистой девой, ты едва можешь поверить, что позволяешь старшей лилин забрать её у тебя, но пока [npc.namePos] [npc.cock+] забирает твою драгоценную девственность, у тебя нет времени на раздумья о сделанном выборе. Единственное, что занимает твои мысли — это мучительная боль от того, что твоя плева разрывается [npc.cockGirth] демоническим членом, втиснутым в твою теперь уже оскверненную киску.");
					} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
						sb.append("Когда [npc.name] [npc.her] [npc.cock+] в твою [pc.pussy+], чтобы разорвать твою плеву и забрать девственность, ты невольно издаешь похотливый, мазохистский крик. Мучительная боль от того, что твою плеву разрывает [npc.namePos] [npc.cockGirth] демонический член, полностью овладевает тобой, и ты не можешь не визжать и стонать в восхитительном тумане всепоглощающего экстаза.");
					} else {
						sb.append("Когда [npc.name] вонзает [npc.her] [npc.cock+] в твою [pc.pussy+], чтобы забрать твою девственность, ты невольно издаешь отчаянный, дрожащий вопль. Мучительная боль от того, что твоя девственная плева разрывается [npc.namePos] [npc.cockGirth] демоническим членом, полностью овладевает тобой, и ты ерзаешь от дискомфорта, пытаясь вытерпеть этот болезненный опыт.");
					}
				sb.append("</p>");
				
			} else {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("Когда [npc.name] вонзает [npc.her] [npc.cock+] в твою [pc.pussy+], ты невольно издаешь отчаянный, дрожащий вопль. Будучи столь очарованной идеей чистоты, ты едва можешь поверить, что позволяешь старшей лилин забрать её у тебя, но пока [npc.namePos] [npc.cock+] забирает твою драгоценную девственность, у тебя нет времени на раздумья о своем выборе. Единственное, о чем ты можешь думать, — это то, что тебя овладевает [npc.cockGirth] демонический член, втиснутый в твою теперь уже оскверненную киску.");
					} else {
						sb.append("Когда [npc.namePos] [npc.cock+] вонзает в твою [pc.pussy+], чтобы забрать твою девственность, ты невольно издаешь отчаянно похотливый [pc.moan]. [npc.NamePos] [npc.cockGirth] демонический член дарит тебе столь интенсивное наслаждение, какого ты никогда прежде не чувствовала, и ты не можешь перестать кричать и [pc.moan] в восхитительном тумане всепоглощающего экстаза.");
					}
				sb.append("</p>");
			}
			
			if(Main.sex.getInitialSexManager() instanceof SMLyssiethDemonTF) {
				sb.append("<p>Услышав твой оглушительный вопль, [npc.name] перестает двигаться вперед, и, пока ее горячий, пульсирующий член все еще забит в твою дрожащую киску, [npc.she] дразнит тебя, [npc.speechNoExtraEffects(Ты очень везучая [pc.girl], [pc.name]... ~Ммм!~ Знаешь, не каждому выпадает такая честь — лишиться девственности с древним лилин, особенно во время превращения в демона... ~Ооо!~ А теперь умоляй о моем члене, как послушная маленькая суккуб-шлюшка, и тогда я отдам тебе всё остальное.)]</p>");
			
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("Хотя ты не можешь сдержать слезы, осознавая, что потеряла свою драгоценную, чистую девственность, ты также безумно возбуждена.");
					} else {
						sb.append("Хотя ты невольно морщишься от резкой боли, пронзающей твою набитую членом киску, ты также безумно возбуждена.");
					}
					sb.append(" Повинуясь словам [npc.name], ты отчаянно тяжело дышишь: [pc.speechNoExtraEffects(Пожалуйста, [npc.name]... ~Ааах!~ Дай мне свой член и преврати меня в суккуба!)]");
				sb.append("</p>");
				
			} else {
				sb.append("<p>Услышав твой оглушительный вопль, [npc.name] перестает двигаться вперед, и, пока ее горячий, пульсирующий член все еще забит в твою дрожащую киску, [npc.she] дразнит тебя, [npc.speechNoExtraEffects(Ты очень везучая [pc.girl], [pc.name]... ~Ммм!~ Знаешь, не каждому выпадает такая честь — лишиться девственности с древним лилин... А теперь умоляй о моем члене, и тогда я отдам тебе всё остальное.)]</p>");
				
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("Хотя ты не можешь сдержать слезы, осознавая, что потеряла свою драгоценную, чистую девственность, ты также безумно возбуждена.");
					} else {
						sb.append("Хотя ты невольно морщишься от резкой боли, пронзающей твою набитую членом киску, ты также безумно возбуждена.");
					}
					sb.append(" Выполняя приказы [npc.name], вы отчаянно тяжело дышите, [pc.speechNoExtraEffects(Пожалуйста, [npc.name]... ~Аах!~ Дай мне свой член!)]");
				sb.append("</p>");
			}
			
			sb.append("<p>[npc.speechNoExtraEffects(Хорошая [pc.girl],)] [npc.name] отвечает, и вы пытаетесь подавить свои всхлипы, пока [npc.she] продолжает с того места, где [npc.she] остановился, вонзая оставшуюся часть своего огромного демонического члена [npc.her] в вашу киску. Пока [npc.she] наполняет вашу только что раскрывшуюся девственность своим горячим, пульсирующим [npc.her] стволом, [npc.name] тихо стонет: [npc.speechNoExtraEffects(Вот так... ~Ммм!~ Принимай мой член поглубже... Скоро тебе станет хорошо...)]</p><p>И действительно, после того как [npc.nameHas] вытащил свой [npc.her] член из вашей киски, боль начинает утихать, и вы ловите себя на том, что издаете похотливый стон, сосредоточившись на ощущении того, как [npc.her] [npc.cockHead+] проталкивается в вашу размякшую киску, когда [npc.she] снова начинает мягко двигаться вперед...</p>");
			
		} else if(penetrated == SexAreaOrifice.ANUS) {
			sb.append("<p>");
				sb.append("Пока [npc.name] медленно втискивает [npc.her] [npc.cock+] в вашу [pc.asshole+], чтобы забрать вашу девственность, вы не можете не издать отчаянный, дрожащий вопль. Неприятное ощущение от того, что огромный демонический член [npc.namePos] [npc.cockGirth] силой проникает внутрь вас, заставляет вас ерзать от дискомфорта.");
			sb.append("</p>");
			

			if(Main.sex.getInitialSexManager() instanceof SMLyssiethDemonTF) {
				sb.append("<p>Услышав твой оглушительный вопль, [npc.name] перестает двигаться вперед, и, пока ее горячий, пульсирующий член все еще забит в твою анус, [npc.she] дразнит тебя: [npc.speechNoExtraEffects(~Ммм!~ Знаешь, что бы там ни говорили, все суккубы — те еще похотливые зады... И ты не станешь исключением... ~Ооо!~ А теперь умоляй о моем члене, как хорошая маленькая анал-любительница, и тогда я отдам тебе всё остальное.)]</p>");
			
				sb.append("<p>");
					sb.append("Понимая, что слова [npc.name] вызывают у тебя дикое возбуждение, ты изо всех сил стараешься терпеть дискомфорт и отчаянно стонешь: [pc.speechNoExtraEffects(Пожалуйста, [npc.name]... ~Ааах!~ Я буду хорошей суккубшей-задышкой! Дай мне свой член!)]");
				sb.append("</p>");
				
			} else {
				sb.append("<p>Услышав твой оглушительный вопль, [npc.name] перестает двигаться вперед, и, пока ее горячий, пульсирующий член все еще забит в твою анус, [npc.she] дразнит тебя: [npc.speechNoExtraEffects(~Ммм!~ Приятно знать, что мой член — первый, кто оказался в твоей заднице... Я прямо чувствую, что ты станешь похотливой маленькой задышкой... ~Ооо!~ А теперь умоляй о моем члене, как хорошая маленькая анал-любительница, и тогда я отдам тебе всё остальное.)]</p>");

				sb.append("<p>");
					sb.append("Понимая, что слова [npc.name] вызывают у тебя дикое возбуждение, ты изо всех сил стараешься терпеть дискомфорт и отчаянно стонешь: [pc.speechNoExtraEffects(Пожалуйста, [npc.name]... ~Ааах!~ Дай мне свой член!)]");
				sb.append("</p>");
			}

			sb.append("<p>[npc.speechNoExtraEffects(Хороший [pc.girl],)] [npc.name] отзывается, и ты пытаешься подавить всхлипы, пока [npc.she] продолжает там, где [npc.she] остановилась, и вгоняет оставшуюся часть [npc.her] огромного демонического члена в твой анус. Пока [npc.she] наполняет тебя [npc.her] горячим, пульсирующим стволом, [npc.name] тихо стонет: [npc.speechNoExtraEffects(Вот так... ~Ммм!~ Принимай мой член поглубже... Скоро тебе станет очень хорошо...)]</p><p>Как и ожидалось, после того как [npc.nameHas] вытащил [npc.her] член из твоего ануса, дискомфорт начинает утихать, и ты невольно издаешь похотливый стон, сосредоточившись на ощущении того, как [npc.her] [npc.cockHead+] втискивается в твою размятую задницу, пока [npc.she] снова плавно толкается вперед...</p>");
		}
		
		return UtilText.parse(this,  sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Сломленная девственница</b></p><p>Когда [npc.name] снова начинает набивать твою киску [npc.her] горячим демоническим членом, внезапное осознание произошедшего бьет тебя, словно кувалда.</p><p style='text-align:center;'>[pc.thought(Я... я лишилась девственности?!<br/>У <b> старшей лилин</b>?!)]</p><p>Ты не знаешь, что хуже: потеря девственности, которой так дорожила, или тот факт, что тебе это на самом деле нравится. Пока твои [pc.labia+] похотливо раздвинуты вокруг горячего, толстого [npc.cock], входящего и выходящего из тебя, ты начинаешь убеждать себя, что только на это ты и годна.</p><p style='text-align:center;'>[pc.thought(Если я больше не девственница, значит, я шлюха...<br/>Просто шлюха, которую трахают и наполняют спермой...<br/>Интересно, все ли члены такие же классные, как [npc.namePos]...)]</p><p>Ты смутно осознаешь, что [npc.name] шепчет тебе, как хороша твоя киска, пока [npc.she] начинает сосредотачивать [npc.her] внимание на том, чтобы заполнить ее своим пульсирующим демоническим стволом. С отчаянным стоном,"
					+ (Main.game.getPlayer().hasLegs()
						?" ты раздвигаешь ноги и"
						:" you")
					+ " смиряешься с тем фактом, что теперь ты не более чем <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>сломанная девственница</b>...</p>";
	}
}