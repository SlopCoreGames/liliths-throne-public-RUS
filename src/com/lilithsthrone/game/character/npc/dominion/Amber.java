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
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.SadisticActions;
import com.lilithsthrone.game.sex.sexActions.dominion.AmberSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Amber extends NPC {

	public Amber() {
		this(false);
	}
	
	public Amber(boolean isImported) {
		super(isImported, new NameTriplet("Эмбер"), "Лиресеамарту",
				"Старшая из служанок Зараникса, Амбер явно возмущена тем, что вы разгуливаете по дому её господина без всякого присмотра.",
				117, Month.OCTOBER, 17,
				15,
				null, null, null,
				new CharacterInventory(false, 10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(28);
		}
		if(this.getBodyMaterial()!=BodyMaterial.FLESH) {
			this.setBodyMaterial(BodyMaterial.FLESH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.8")) {
			this.setLevel(15);
			this.equipClothing();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.9")) {
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, PresetColour.EYE_AMBER, true, PresetColour.EYE_AMBER, true));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1")) {
			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.11")) {
			this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(117);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.ORGASMIC_LEVEL_DRAIN,
						Perk.UNARMED_TRAINING),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 2)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE);
		}
		
		if(setFetishes) {
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			this.addFetish(Fetish.FETISH_FOOT_GIVING);
			
			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_MAID);
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		
		// Body:
		// Add full body reset as this method is called after leaving Zaranix's house:
		this.setAgeAppearanceAbsolute(28);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setTailType(TailType.DEMON_HAIR_TIP);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.SWEPT_BACK);

		// Core:
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, PresetColour.EYE_AMBER, true, PresetColour.EYE_AMBER, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_EBONY, false, PresetColour.COVERING_AMBER, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_EBONY, false, PresetColour.COVERING_AMBER, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), false);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_BLACK), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), true);
		this.setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, CoveringPattern.NONE, PresetColour.COVERING_AMBER, true, PresetColour.COVERING_AMBER, true), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_AMBER));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_AMBER));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.G.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(25);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(550);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_epic", DamageType.FIRE));
		this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_epic", DamageType.FIRE));
		
		// Tattoos
		// Scars

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_open_cup_bra", PresetColour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLACK, false), true, this);

	}

	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(!playerKnowsName) {
			return "Пламенная служанка";
			
		} else {
			return "Эмбер";
		}
	}
	
	@Override
	public String getDescription() {
		if(!playerKnowsName) {
			return "Эта пламенная служанка явно возмущена тем, что вы разгуливаете по дому её господина без всякого присмотра.";
			
		} else {
			return "Старшая из служанок Зараникса, Амбер — одна из самых эффектных суккубов, которых вы когда-либо видели. Её янтарные волосы и глаза, благодаря которым она и получила своё имя, светятся ярким сиянием, резко контрастируя с её иссиня-чёрной кожей.<br/>Амбер беспощадно садистична и обожает утверждать своё превосходство над подчиненными.";
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#FFB38A";
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
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		return "<p>"
					+ UtilText.parse(target,
							UtilText.returnStringAtRandom(
							"Глаза Амбер горят неистовой яростью, когда она наносит мощный удар ногой прямо в [npc.namePos] бок!",
							"С яростным криком Амбер бьёт [npc.name] прямо в грудь!",
							"Выплевывая проклятия, Амбер яростно пинает [npc.namePos] по голеням!",
							"Волосы Амбер, пылающие тем же огненным гневом, что и её глаза, развеваются в воздухе, когда она резко разворачивается и наносит крепкий удар кулаком по [npc.namePos] [npc.arm]!"))
				+ "</p>";
	}
			
	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Издав дикий крик, Эмбер выбрасывает руку вперед, произнося заклинание!",
						"Выплевывая проклятия, Эмбер впивается в тебя своим пылающим взглядом, прежде чем сотворить заклинание!",
						"С яростным проклятием Эмбер делает шаг вперед и накладывает заклинание!") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			if (victory) {
				return new Response("", "", ZaranixHomeGroundFloorRepeat.COMBAT_VICTORY);
			} else {
				return new Response("", "", ZaranixHomeGroundFloorRepeat.COMBAT_LOSS);
			}
			
		} else {
			if (victory) {
				return new Response("", "", AFTER_COMBAT_VICTORY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, true);
					}
				};
			} else {
				return new Response("", "", AFTER_COMBAT_DEFEAT);
			}
			
		}
	}
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {

		@Override
		public String getContent() {
			return "<p>Эмбер пошатывается, упираясь в стену и выплевывая последнее ненавистное проклятие, [amber.speech(Ах ты..., "+(Main.game.getPlayer().isFeminine()?"сучка...,":"ублюдок...")+")]</p><p>Совершенно очевидно, что она больше не в силах продолжать бой, и спустя мгновение действие вашей мощной арканной ауры дает о себе знать. Яростная гримаса Амбер сменяется исключительно похотливым стоном, и, впервые отведя от вас взгляд своих светящихся глаз, она смотрит вниз на свою промежность и просовывает руку под платье.</p><p>[amber.speech(~Ааах!~ Блядь...~)] — стонет она, и по движениям её руки становится предельно ясно, что она ласкает себя пальцами.</p><p>Несмотря на шум вашей битвы, никакой другой горничной, чей голос вы слышали при входе в дом, поблизости не видно. Получив шанс немного развлечься с этой сердитой, а теперь уже очень возбужденной горничной, вы раздумываете: стоит ли воспользоваться моментом или продолжить путь, чтобы найти Зараникс.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить исследование дома Зараникс.", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false)) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getPlayerCell().getDialogue(false);
					}
				};
				
			} if(index==2) {
				return new ResponseSex("Использовать Эмбер",
						"Повеселиться с этой огненной горничной.",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
						null,
						null),
						AFTER_SEX_VICTORY,
						"<p>Похоже, ни одна из других служанок в доме не спешит ей на помощь, поэтому ты решаешь воспользоваться случаем и немного развлечься с Эмбер. Шагнув к ней, пока она прислоняется к стене, ты протягиваешь руку, берешь ее за предплечье и вытягиваешь ее ладонь из-под платья. Лишенная возможности довести себя до оргазма самостоятельно, Эмбер жалобно смотрит тебе в глаза, и вместо ярости ты видишь в них пылающую похоть.</p><p>Она отталкивается от стены, обвивает руками твою спину и отчаянно прижимается своей [amber.lips+] к твоей. Ты отвечаешь на жест, и после нескольких мгновений, проведенных в переплетении языков, ты отстраняешься с ухмылкой...</p>");
				
			} else if(index==3) {
				return new ResponseSex("Подчиниться",
						"Огненный темперамент Эмбер чертовски тебя возбуждает. Ты не решаешься взять на себя доминирующую роль, но ты <i>действительно</i> хочешь заняться с ней сексом. Возможно, если ты подчинишься, она сама захочет тебя оттрахать?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY,
						"<p>Несмотря на её нынешнее поражение, вы чувствуете невероятное возбуждение от доминантного, огненного темперамента Эмбер. Не желая брать на себя роль ведущей, но испытывая глубокое желание заняться сексом с этой теперь уже чертовски возбужденной суккубом, вы подходите к ней, пока она прислоняется к стене, и вздыхаете: [pc.speech('Мисс Эмбер', верно? Эм... Если вы чувствуете легкое возбуждение, может, вы могли бы использовать меня? То есть, я...)]</p><p>Несмотря на то, что вы — чужак в доме её господина, Эмбер смотрит на вас с неистовой, пылающей страстью в глазах. Высунув руку из-под платья, она отталкивается от стены и прерывает вашу фразу, хватая вас за голову и втягивая в отчаянный поцелуй.</p><p>Вы отвечаете на жест, но лишь на несколько мгновений ваши языки сплетаются во рту, прежде чем Эмбер отстраняется с стоном: [amber.speech(Хорошая сучка! Блядь... Как же меня штырит! Мне <i>нужна</i> ты!)]</p>");
				
			} else if (index == 4) {
				return new Response("Трансформации",
						"Заставить Эмбер использовать [amber.her] демонические силы, чтобы трансформировать [amber.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Amber.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Продолжить", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Amber.class)) >= Main.game.getNpc(Amber.class).getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(
						"<p>Эмбер издает глубокий, удовлетворенный вздох и, совершенно обессилев, оседает на пол. Несмотря на усталость, вы замечаете, как одна ее рука скользит между ног, и, явно все еще охваченная похотью, она начинает мастурбировать прямо перед вами. Очевидно, что в таком состоянии она не представляет большой угрозы, поэтому вы возвращаете внимание к текущей задаче: найти Зараникса и спасти Arthur.</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>Эмбер издает отчаянный стон и, опустившись на пол, прижимает обе руки к паху. Не получив удовлетворения, она явно все еще охвачена неистовой похотью и начинает неистово мастурбировать прямо там, на полу. Очевидно, что в таком состоянии она не представляет большой угрозы, поэтому вы возвращаете внимание к текущей задаче: найти Зараникса и спасти Arthur.</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>Понимая, что нужно присматривать и за другими горничными, вы готовитесь отправиться дальше вглубь дома...</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить исследование дома Зараникс.", Main.game.getPlayerCell().getDialogue(false)) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getPlayerCell().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Повержена", "", true) {

		@Override
		public String getContent() {
			return "<p>Рыжеволосая служанка оказывается слишком сильной для тебя, и, не в силах продолжать бой, ты отступаешь, врезаясь спиной в ближайшую стену. Увидев твое поражение, Амбер разражается издевательским смехом; она делает шаг к тебе и рычит: [amber.speech(Тупая сучья тварь! Теперь ты <i>действительно</i> заплатишь!)]</p><p>Подняв взгляд, ты видишь пылающие янтарные глаза разгневанной суккуба, впившиеся в твои. Прежде чем ты успеваешь что-то предпринять или сказать, она протягивает руку и крепко хватает тебя за шею. Когда ее хватка усиливается, ты издаешь сдавленный кашель, что лишь вызывает у нее новый насмешливый смех: [amber.speech(Ха-ха-ха! Жалкое зрелище!)]</p><p>После этого она внезапно швыряет тебя на пол, и ты вскрикиваешь, падая на четвереньки. Амбер обходит тебя сзади, и когда ты пытаешься уползти, она отвешивает тебе звонкую, резкую пощечину по заднице: [amber.speech(Глупая сучка! Ты меня совсем раззадорила! Пора преподать тебе урок!)]</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Использовано", "Амбер начинает трахать тебя.",
						false, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						"<p>Амбер крепко хватает тебя за бедра, а затем грубо приподнимает твою задницу повыше. Резкий удар ее ладони по твоей правой щеке заставляет тебя вскрикнуть, на что служанка отвечает агрессивным рычанием: [amber.speech(Пищи сколько влезет, сучка, <i>теперь ты моя!</i>)]</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Использовано", "", true) {

		@Override
		public String getContent() {
			return "<p>Вы валитесь на пол, совершенно измотанные грубым обращением Амбер. С последней презрительной усмешкой Амбер встает, [amber.speech(На это вы, сучки, только и способны! А теперь вали нахер из моего дома!)]</p><p>Схватив "+(Main.game.getPlayer().getHairRawLengthValue()>4?"горсть ваших [pc.hair(true)]":"за затылок,")+"Амбер безжалостно тащит вас к входной двери. Распахнув её, она бросает вам напоследок очередное ненавистное замечание, после чего буквально вышвыривает вас на улицу и с грохотом захлопывает дверь за вашей спиной.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Тебя вышвыривают", "Амбер вышвыривает вас на улицу.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public int getLootMoney() {
		return 5000;
	}
	
	// Sex:

	@Override
	public boolean isLevelDrainAvailableToUse() {
		AbstractClothing neckClothing = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
		return Main.game.isLevelDrainContentEnabled()
				&& neckClothing!=null
				&& neckClothing.getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar");
	}

	@Override
	public boolean isWantingToLevelDrain(GameCharacter target) {
		return target.isPlayer();
	}

	@Override
	public String getLevelDrainDescription(GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(UtilText.returnStringAtRandom(
				"Издав насмешливый смешок, Амбер грубо хватает вас и рычит: ",
				"Сияющие глаза Амбер широко распахиваются, и с жестоким смехом она явит: ",
				"Издав жестокий, насмешливый смех, Амбер жадно поглощает вашу энергию и издевательски произносит: "
				));
		
		sb.append(UtilText.returnStringAtRandom(
				"[npc.speech(Я выкачаю из тебя всю силу! Когда я закончу, ты превратишься в мою никчемную зверушку!)]",
				"[npc.speech(Жалкая сучка! Вся твоя сила будет моей!)]",
				"[npc.speech(Какая же ты жалкая сучка, раз позволяешь так высасывать свою силу!)]"));
		
		return UtilText.parse(this, target, sb.toString());
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(AmberSpecials.class);
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.ALL_FOURS) {
			if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
			} else {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS);
			}
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.ALL_FOURS) {
			if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			} else {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			}
		}

		return super.getMainSexPreference(target);
	}
	
	@Override
	public GameCharacter getPreferredSexTarget() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			return Main.game.getPlayer();
		}
		return super.getPreferredSexTarget();
	}
	
	@Override
	public List<SexActionInterface> getLimitedSexClasses() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			List<SexActionInterface> actionsAvailable = new ArrayList<>();
			
			actionsAvailable.add(FingerMouth.PARTNER_ASSIST_BLOWJOB);
			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(SadisticActions.class));
			actionsAvailable.add(PartnerTalk.PARTNER_DIRTY_TALK);
			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(GenericOrgasms.class));

//			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(GenericActions.class));
//			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(GenericTalk.class));
//			actionsAvailable.addAll(this.getSexActionInterfacesFromClass(PartnerTalk.class));
			
			return actionsAvailable;
		}
		return super.getLimitedSexClasses();
	}
	
	@Override
	public int getOrgasmsBeforeSatisfied() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) {
			return 0;
		}
		return super.getOrgasmsBeforeSatisfied();
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) {
			return slot==SexSlotSitting.PERFORMING_ORAL_TWO;
		}
		return slot==SexSlotAllFours.BEHIND;
	}
	
	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
	
	@Override
	public String getRoughTalk() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			if(Main.game.getNpc(Zaranix.class).getArousal()>=95) {
				return "[npc.speech(Готовься принимать сперму своего [zaranix.master], никчемная шлюха!)]";
			}
			return UtilText.returnStringAtRandom(
					"[npc.speech(Вот так, никчемная сучка, покажи своему [zaranix.master], что ты всего лишь [zaranix.his] похотливая маленькая игрушка для траха!)]",
					"[npc.speech(Ха! Обожаю смотреть, как жалких, покорных шлюх трахают в рот!)]",
					"[npc.speech(Ты всего лишь никчемная игрушка для траха своего [zaranix.master], поняла?! Твой рот принадлежит [zaranix.him]!)]",
					"[npc.speech(Вот так! Тебе ведь нравится принимать член [zaranix.his], а, никчемная шлюха?!)]");
		}
		return super.getRoughTalk();
	}
	
	@Override
	public String getDirtyTalk() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Zaranix.class))) { // Assisting the player to suck Zaranix's cock:
			if(Main.game.getNpc(Zaranix.class).getArousal()>=95) {
				return "[npc.speech(Готовься принимать сперму своего [zaranix.master], никчемная шлюха!)]";
			}
			return UtilText.returnStringAtRandom(
					"[npc.speech(Давай же, сучка, ты можешь принять член [zaranix.his] и поглубже!)]",
					"[npc.speech(Вот так, шлюха, старайся изо всех сил, чтобы доставить удовольствие члену своего [zaranix.master]!)]",
					"[npc.speech(Глубже, сучка! Принимай член [zaranix.his] еще глубже!)]",
					"[npc.speech(Давай же, никчемная шлюха, шевели не только задницей, но и языком!)]");
		}
		return super.getDirtyTalk();
	}
}
