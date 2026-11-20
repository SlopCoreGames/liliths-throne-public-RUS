package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
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
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class Dale extends NPC {

	public Dale() {
		this(false);
	}
	
	public Dale(boolean isImported) {
		super(isImported, new NameTriplet("Дейл"), "Лейн",
				"Дейл — администратор в молочной лавке Эвеликс.",
				28, Month.JANUARY, 2,
				15,
				Gender.M_P_MALE, Subspecies.HORSE_MORPH_DONKEY, RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.8")) {
			this.setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.3")) {
			this.setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.HEAVY_SLEEPER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
		
		if(!Main.game.getDialogueFlags().hasFlag("innoxia_dale_teddy_bear_given")) {
			this.removeTrait(Perk.HEAVY_SLEEPER);
		}
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.KIND);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			
//			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_RECEPTIONIST);
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
		// Core:
		this.setHeight(178);
		this.setFemininity(25);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false), false);
		body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));
		this.setHairLength(10);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(26);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(120);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(this.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory")) { // Sleeping
			//this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
			
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_IRON, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hips_leather_belt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
		}
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	private boolean needsMoving = false;
	@Override
	public void hourlyUpdate(int hour) {
		// Sleeps between 01:00-05:00
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(((hour>=1 && hour<5) && this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory"))
					 || (!(hour>=1 && hour<5) && this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"))) {
				needsMoving = true;
			}
		}
	}
	
	@Override
	public void turnUpdate() {
		// Sleeps between 01:00-05:00
		if(needsMoving && !Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(1, 5)) {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_reception_sex", false);
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory"), true);
				this.equipClothing();
				Main.game.updateResponses();
				
			} else {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_dorm_sex", false);
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"), false);
				this.equipClothing();
			}
		}
	}
	
	@Override
	public void endSex() {
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(true, false);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	@Override
	public boolean isAffectedBySleepingStatusEffect() {
		return true;
	}
	
	@Override
	public boolean isSleepingAtHour(int hour) {
		return this.isAtHome(); // Always sleeping when on home tile
	}
	
	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public SexPace getSexPaceDomPreference() {
		return SexPace.DOM_GENTLE;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character) {
		return SexPace.SUB_EAGER;
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
						sb.append("Когда [npc.name] медленно вталкивает [npc.her] [npc.cock+] в твою [pc.pussy+], ты невольно издаешь отчаянный, дрожащий вопль. Будучи настолько очарованной идеей оставаться невинной девой, ты и сама не понимаешь, что нашло на тебя, раз ты решила отдать ему свою девственность в таком месте, но пока [npc.namePos] [npc.cock+] забирает твою драгоценную невинность, у тебя нет времени на раздумья о сделанном выборе. Единственное, о чем ты можешь думать, — это мучительная боль от того, что твоя девственная плева разрывается этим [npc.cockGirth] ослиным членом, втиснутым в твою теперь уже оскверненную киску.");
					} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
						sb.append("Когда [npc.name] медленно вталкивает [npc.her] [npc.cock+] в твою [pc.pussy+], разрывая твою плеву и забирая девственность, ты невольно издаешь похотливый, мазохистский крик. Мучительная боль от того, что твою плеву разрывает [npc.namePos] [npc.cockGirth] ослиный член, полностью овладевает тобой, и ты не можешь удержаться от визгов и стонов в восхитительном тумане всепоглощающего экстаза.");
					} else {
						sb.append("Когда [npc.name] медленно вталкивает [npc.her] [npc.cock+] в твою [pc.pussy+], чтобы забрать твою девственность, ты невольно издаешь отчаянный, дрожащий вопль. Мучительная боль от того, что твою плеву разрывает [npc.namePos] [npc.cockGirth] ослиный член, полностью овладевает тобой, и ты ерзаешь от дискомфорта, пытаясь вытерпеть этот болезненный опыт.");
					}
				sb.append("</p>");
				
			} else {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("Когда [npc.name] медленно втискивает [npc.her] [npc.cock+] в твою [pc.pussy+], ты невольно издаешь отчаянный, дрожащий вопль. Будучи столь очарованной идеей оставаться невинной девой, ты и сама не понимаешь, что нашло на тебя, когда ты согласилась на это, но пока [npc.namePos] [npc.cock+] забирает твою драгоценную девственность, у тебя нет времени на раздумья о сделанном выборе. Единственное, о чем ты можешь думать, — это то, что тебя разминает [npc.cockGirth] ослиный член, вошедший в твою теперь уже оскверненную киску.");
					} else {
						sb.append("Пока [npc.namePos] медленно вгоняет [npc.cock+] в твою [pc.pussy+], чтобы забрать твою девственность, ты невольно издаешь отчаянно похотливый [pc.moan]. [npc.NamePos] [npc.cockGirth] ослиный член дарит тебе столь интенсивное наслаждение, какого ты никогда прежде не чувствовала, и ты не можешь перестать кричать и [pc.moan] в восхитительном тумане всепоглощающего экстаза.");
					}
				sb.append("</p>");
			}
			
			sb.append("<p>Услышав твой оглушительный вопль, [npc.name] мгновенно прекращает толчки и обеспокоенным тоном [npc.she] спрашивает: [npc.speechNoExtraEffects(Ты в порядке, [pc.name]? Мы можем остановиться, если для тебя это слишком...)]</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("Хотя ты не можешь сдержать слезы, осознавая, что потеряла свою драгоценную, чистую девственность, тебя утешает тот факт, что [npc.name] кажется искренне обеспокоенным тобой.");
				} else {
					sb.append("Хотя ты невольно морщишься от резкой боли, пронзающей твою набитую членом киску, тебя утешает тот факт, что [npc.name], кажется, искренне за тебя беспокоится.");
				}
				sb.append(" Сделав небольшую паузу, чтобы перевести дух, ты понимаешь, что пути назад нет, и, тяжело дыша, произносишь: [pc.speechNoExtraEffects(Я в порядке... Можешь продолжать...)]");
			sb.append("</p>");
			
			sb.append("<p>[npc.speechNoExtraEffects(Хорошо, тогда я буду очень осторожен,)] — отвечает [npc.name], и ты пытаешься подавить свои всхлипы, пока [npc.she] возобновляет то, на чем [npc.she] остановился, медленно втискивая свой огромный ослиный член [npc.her] в твою киску. Пока [npc.she] наполняет твою едва растерзанную девственность своим пульсирующим стволом [npc.her], [npc.name] подбадривает тебя мягким хрипом: [npc.speechNoExtraEffects(Для первого раза ты держишься просто отлично... Скоро ты войдешь во вкус...)]</p><p>И действительно, после того как [npc.nameHas] вытащил свой член [npc.her] из твоей киски, боль начинает утихать, и ты ловишь себя на том, что издаешь похотливый стон, сосредоточившись на ощущении того, как плоская, расширенная головка [npc.her] втискивается в твою размякшую киску, когда [npc.she] снова плавно толкается вперед...</p>");
			
		} else if(penetrated == SexAreaOrifice.ANUS) {
			sb.append("<p>");
				sb.append("Когда [npc.name] медленно втискивает [npc.her] [npc.cock+] в твою [pc.asshole+], чтобы забрать твою девственность, ты невольно издаешь отчаянный, дрожащий вопль. Неприятное ощущение от того, что [npc.namePos] [npc.cockGirth] ослиный член медленно пробивается внутрь тебя, заставляет тебя беспокойно ерзать.");
			sb.append("</p>");
			
			sb.append("<p>Услышав твой страдальческий крик, [npc.name] мгновенно прекращает толчки и обеспокоенным тоном [npc.she] спрашивает: [npc.speechNoExtraEffects(Ты в порядке, [pc.name]? Мы можем остановиться, если для тебя это слишком...)]</p>");
			
			sb.append("<p>");
				sb.append("Хотя ты невольно морщишься от резкой боли, пронзающей твою набитую членом задницу, тебя утешает тот факт, что [npc.name] кажется искренне обеспокоенным тобой.");
				sb.append(" Сделав небольшую паузу, чтобы перевести дух, ты понимаешь, что пути назад нет, и, тяжело дыша, произносишь: [pc.speechNoExtraEffects(Я в порядке... Можешь продолжать...)]");
			sb.append("</p>");
			
			sb.append("<p>[npc.speechNoExtraEffects(Ok, I'll take it real slow,)] [npc.name] responds, and you try to suppress your whimpers as [npc.she] resumes where [npc.she] left off and slowly pushes [npc.her] huge donkey-cock into your back door. As [npc.she] fills your ass with [npc.her] throbbing shaft, [npc.name] reassures you with a soft grunt, [npc.speechNoExtraEffects(You're doing really well for your first time... You'll get into the swing of it in a moment...)]</p><p>Sure enough, after [npc.nameHas] pulled [npc.her] cock back out of your asshole, the pain starts to fade away, and you find yourself letting out a lewd moan as you concentrate on the feeling of [npc.her] flat, flared head pushing its way into your broken-in butt as [npc.she] gently thrusts forwards once again...</p>");
		}
		
		return UtilText.parse(this,  sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Сломленная девственница</b></p><p>Когда [npc.name] снова начинает засовывать в твою киску горячий ослиный член [npc.her], внезапное осознание случившегося бьет тебя словно кувалдой.</p><p style='text-align:center;'>[pc.thought(Я... я лишилась девственности?!<br/>Вот так... Вот так, как <b>это</b>?!)]</p><p>Ты не знаешь, что хуже: потеря девственности, которой так дорожила, или тот факт, что тебе это на самом деле нравится. Пока твои [pc.labia+] похотливо раздвинуты вокруг горячего, толстого [npc.cock], который медленно входит и выходит из тебя, ты начинаешь убеждать себя, что только на это ты и годна.</p><p style='text-align:center;'>[pc.thought(Если я больше не девственница, значит, я шлюха...<br/>Просто шлюха, которую трахают и набивают спермой...<br/>Интересно, все члены такие же классные, как [npc.namePos]...)]</p><p>Ты смутно осознаешь, как подбадривающие комментарии [npc.namePos] затихают, когда [npc.she] начинает сосредотачивать [npc.her] внимание на траххе тебя. С отчаянным стоном,"
					+ (Main.game.getPlayer().hasLegs()
						?" ты раздвигаешь ноги и"
						:" you")
					+ " смиряешься с тем фактом, что теперь ты не более чем <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>сломанная девственница</b>...</p>";
	}
}
