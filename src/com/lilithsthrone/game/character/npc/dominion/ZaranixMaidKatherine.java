package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
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
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ZaranixMaidKatherine extends NPC {

	public ZaranixMaidKatherine() {
		this(false);
	}
	
	public ZaranixMaidKatherine(boolean isImported) {
		super(isImported, new NameTriplet("Кэтрин"), "Ласиэллемарту",
				"Одна из сестер-близнецов суккубов-горничных Зараникса, Кэтрин поручена своим господином следить за чистотой первого этажа.",
				26, Month.SEPTEMBER, 20,
				10,
				null, null, null,
				new CharacterInventory(false, 10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_MAID, true);

		this.setPlayerKnowsName(true);
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setDescription("Одна из сестер-близнецов суккубов-горничных Зараникса, Кэтрин поручена своим господином следить за чистотой первого этажа.");
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 5),
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
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
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
		this.setAgeAppearanceAbsolute(18);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.CURLED);

		// Core:
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_PURPLE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_IVORY), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_WHITE), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.HIME_CUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE));
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
		this.setBreastSize(CupSize.F.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(17);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(100);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
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

		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_cleaning_feather_duster"));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLACK, false), true, this);

	}
	
	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(!playerKnowsName) {
			return "Горничная Зараникса";
			
		} else {
			return "Кэтрин";
		}
	}

	@Override
	public void hourlyUpdate(int hour) {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			this.moveToAdjacentMatchingCellType(true, PlaceType.ZARANIX_GF_CORRIDOR, PlaceType.ZARANIX_GF_STAIRS, PlaceType.ZARANIX_GF_MAID);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#E48AFF";
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
		return true;
	}

	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character instanceof ZaranixMaidKatherine) {
			Set<Relationship> result = new LinkedHashSet<>();
			result.add(Relationship.SiblingTwin);
			return result;
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}

	// Combat:

	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		return "<p>"
				+ UtilText.parse(target,
						UtilText.returnStringAtRandom(
						"Издав отчаянный крик, Кэтрин размахивает своей маленькой метелкой из перьев перед [npc.name], проводя перьями по лицу [npc.her]!",
						"С коротким вскриком Кэтрин щекочет торс [npc.namePos] своей метелкой из перьев!",
						"Кэтрин издает тонкий возглас, проводя метелкой из перьев по торсу [npc.namePos]!"))
			+ "</p>";
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Кэтрин прижимает метелку к груди и вскрикивает, произнося заклинание!",
						"Кэтрин взмывает вверх с метелкой из перьев, произнося заклинание!") 
			+ "</p>";
	}

	@Override
	public String getSeductionDescription(GameCharacter target) {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Кэтрин, стоная, слегка приподнимает подол своего платья горничной, [katherine.speech(Если ты победишь меня, ничто не помешает тебе взять меня прямо здесь!)]",
						"С похотливым стоном Кэтрин просовывает руку под платье, простоная [katherine.speech(~Ах!~ Ты такой могущественный! Я уже вся промокла!)]",
						"Прикусив губу, Кэтрин смотрит тебе прямо в глаза, и в твоей голове эхом отдается похотливый стон: [katherine.speech(Надеюсь, ты побьешь меня! Не щади меня!)]",
						"Издавая невероятно сладострастный стон, Кэтрин проводит ладонями вверх по телу, чтобы обхватить свои [katherine.cupSize]-чашечные груди, [katherine.speech(~Ммм!~ Если я проиграю, ничто не будет мешать тебе трахать меня!)]",
						"Кэтрин картинно пошатывается, прежде чем отчаянно прижать ладони между ног, [katherine.speech(~Аах!~ Я прямо представляю, какие грязные вещи ты сделаешь со мной, если я упаду здесь!)]",
						"Грудь Кэтрин [npc.breasts+] начинает тяжело вздыматься, пока она часто дышит, [katherine.speech(О нет! Я уже чувствую слабость! Т-ты точно воспользуешься мной, если я проиграю!)]") 
			+ "</p>";
	}
	
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKatherineSubdued, true);
				}
			};
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {

		@Override
		public String getContent() {
			return "<p>Кэтрин отшатывается, хватаясь за край стоящего рядом шкафа, и сползает по стене. Кажется, её не слишком расстраивает то, что она не смогла защитить дом своего господина: она смотрит на тебя и стонет: [katherine.speech(~Ах!~ О нет! Я совершенно беззащитна! ~Ммм!~ Теперь ничто не помешает тебе взять то, что ты хочешь!)]</p><p>Совершенно очевидно, что она жаждет секса с тобой; она продолжает стонать и тяжело дышать от желания, прислонившись спиной к стене. Не в силах прождать и двух секунд, чтобы услышать твой ответ, она просовывает руки под платье и начинает бесстыдно мастурбировать прямо у тебя на глазах.</p><p>В своём нынешнем состоянии, пресыщенном Похотью, Кэтрин вряд ли будет представлять серьезную угрозу, так что ты можешь либо сделать то, чего она так явно хочет — заняться с ней сексом, — либо просто проигнорировать её и идти дальше своим путем.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить исследование дома Зараникс.", PlaceType.ZARANIX_GF_MAID.getDialogue(false));
				
			} if(index==2) {
				return new ResponseSex("Использовать Кэтрин", "Немного развлечься с похотливой горничной.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
						null,
						null),
						AFTER_SEX_VICTORY, "<p>Похоже, другие горничные в доме вас не прервут, поэтому вы решаете воспользоваться случаем и немного развлечься с Кэтрин. Подойдя к ней, пока она прислонена к стене, вы протягиваете руку и берете ее за предплечье, а затем убираете ее ладонь от паха. Лишенная возможности довести себя до оргазма, похотливая горничная смотрит вам в глаза, и вы видите в них отчаянную, пылающую похоть.</p><p>Она отталкивается от стены, обвивает руками вашу спину и отчаянно прижимается своей [katherine.lips+] к вашей. Вы отвечаете тем же, и после нескольких мгновений, проведенных в переплетении языков, вы отстраняетесь с ухмылкой...</p>");
				
			} else if(index==3) {
				return new ResponseSex("Подчиниться",
						"Вы не можете заставить себя взять на себя доминирующую роль, но вы <i>действительно</i> хотите заняться сексом с Кэтрин. Возможно, если вы подчинитесь, она сама захочет вас оттрахать?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY, "<p>Не желая брать на себя доминирующую роль, но испытывая глубокое желание заняться сексом с похотливой суккубом, вы подходите к месту, где прислонилась к стене обессиленная Кэтрин, и вздыхаете: [pc.speech(Кэтрин... Эм... Если ты чувствуешь небольшое возбуждение, может быть, ты могла бы использовать меня? То есть, я...)]</p><p>Несмотря на то, что вы — чужак в доме её госпожи, Кэтрин смотрит на вас с неистовой, пылающей страстью в глазах. Высунув руку из-под платья, она тут же прерывает вашу фразу, хватает вас за голову и втягивает в отчаянный поцелуй.</p><p>Вы отвечаете на жест, но лишь на несколько мгновений сплетаете языки, прежде чем Кэтрин отстраняется с стоном: [katherine.speech(О да! Блядь... Как же меня штырит! Ты мне <i>нужен</i>!)]</p>");
				
			} else if (index == 4) {
				return new Response("Трансформации",
						"Заставить Кэтрин использовать [katherine.her] демонические силы, чтобы превратить [katherine.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKatherine.class));
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
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKatherine.class)) >= Main.game.getNpc(ZaranixMaidKatherine.class).getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(
						"<p>С удовлетворенным вздохом Кэтрин откидывается на стену: [katherine.speech(~Ах!~ Это было хорошо...)]</p><p>Несмотря на то, что вы только что довели её до оргазма, её руки скользят между ног, и с исключительно похотливым стоном она начинает ласкать себя пальцами. По её действиям совершенно ясно, что она всё ещё ошеломлена возбуждающей силой вашей ауры, так что можно со спокойной совестью оставить её и продолжить изучение дома Зараникса.</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>С отчаянным стоном Кэтрин прислоняется спиной к стене, [katherine.speech(~Ах!~~ Этого мне было мало!~)]</p><p>Ее руки мгновенно скользят между ног, и с исключительно похотливым стоном она начинает ласкать себя пальцами. По ее действиям совершенно ясно, что она все еще ошеломлена возбуждающей силой вашей <p>, так что можно спокойно оставить ее и продолжить исследование дома Зараникса.</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>Понимая, что нужно присматривать и за другими горничными, вы готовитесь отправиться дальше вглубь дома...</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить исследование дома Зараникс.", PlaceType.ZARANIX_GF_MAID.getDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Повержена", "", true) {

		@Override
		public String getContent() {
			return "<p>Кэтрин оказывается слишком сильной, чтобы вы могли с ней совладать, и, издав побежденный крик, вы шатаетесь по коридору и прислоняетесь к стене, чтобы не упасть. Подняв взгляд, вы видите, как служанка с кожей цвета слоновой кости дует губы, подходя к вам ближе: [katherine.speech(Оу... А я надеялась, что ты будешь сопротивляться чуть дольше... Совсем не весело!)]</p><p>Вы бессильны перед ней, когда она прижимается вплотную, обхватывает вас руками за спину и притягивает к своей [katherine.breasts+], хихикая: [katherine.speech(Похоже, мне придется развлекаться как-нибудь иначе...)]</p><p>С этими словами она внезапно прижимает свою [katherine.lips+] к вашей, вовлекая вас в страстный поцелуй, пока ее демоний хвост обвивается вокруг вас, потирая вашу пах...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Использовано", "Кэтрин использует вас.",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						AFTER_SEX_DEFEAT,
						"");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Использовано", "", true) {

		@Override
		public String getContent() {
			return "<p>Совершенно обессиленный, ты валишься на пол, тяжело дыша. Возвышаясь над тобой, Кэтрин произносит с крайне покровительственным тоном: [katherine.speech(Оууу! Не слишком ли весело тебе было? В таком случае, думаю, пришло время показать тебе выход!)]</p><p>Слишком уставший, чтобы сопротивляться, ты остаешься совершенно беспомощным, пока Кэтрин тащит тебя по коридору к главному входу. Открыв дверь, она бесцеремонно выталкивает тебя на улицу, вздыхая: [katherine.speech(Полагаю, ты выбрал не тот дом для попытки ограбления, а?)]</p><p>С этими словами она захлопывает дверь, оставляя тебя приходить в себя и продолжать свой путь...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Тебя вышвыривают", "Кэтрин вышвыривает тебя на улицу.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
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

}