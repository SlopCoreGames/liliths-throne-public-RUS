package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
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
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia
 */
public class Ralph extends NPC {
	
	public static final String RALPH_DISCOUNT_TIMER_ID = "ralph_discount_timer";
	
	public Ralph() {
		this(false);
	}
	
	public Ralph(boolean isImported) {
		super(isImported, new NameTriplet("Ralph"), "Армстронг",
				"Ralph — владелец лавки «Закуски от Ralph». В его осанке чувствуется уверенность, а сам он всегда ведет себя крайне профессионально.",
				34, Month.MAY, 17,
				10, Gender.M_P_MALE, Subspecies.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(false, 10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, true);
		
		if(!isImported) {
			dailyUpdate();
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 35);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.5")) {
			this.setPenisSize(30);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.1")) {
			this.setPenisCumStorage(250);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.11.1")) { // For some reason my test save didn't have Ralph as having these fetishes, so I made sure to re-add them in
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		
		if(setFetishes) {
			this.clearFetishDesires();
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_STORE_OWNER);
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
		this.setHeight(195);
		this.setFemininity(5);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		// Leave as default:
//		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		// Leave as default:
//		this.setNippleVirgin(true);
//		this.setBreastSize(CupSize.FLAT.getMeasurement());
//		this.setBreastShape(BreastShape.ROUND);
//		this.setNippleSize(NippleSize.ZERO_TINY);
//		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		// Leave as default:
//		this.setAssSize(AssSize.TWO_SMALL);
//		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
//		this.setAssWetness(Wetness.ZERO_DRY);
//		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
//		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(30);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(250);
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

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_GOLD, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	/**
	 * Discount is active for three days after earning it.
	 */
	public boolean isDiscountActive(){
		if(Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID) == -1 || Main.game.getDialogueFlags().ralphDiscount <= 0) {
			return false;
		} else {
			return (Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID)) < (60*24*3);
		}
	}

	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
		this.addItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), 25, false, false);
		this.addItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), 10, false, false);
		
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(6+Util.random.nextInt(12)), false, false);
			}
		}

		for(AbstractWeaponType weapon : WeaponType.getAllWeapons()) {
			if(weapon.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!weapon.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addWeapon(Main.game.getItemGen().generateWeapon(weapon), 1+Util.random.nextInt(5), false, false);
			}
		}
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				if(clothing.isDefaultSlotCondom()) {
					Colour condomColour = clothing.getColourReplacement(0).getRandomOfDefaultColours();
					Colour condomColourSec = PresetColour.CLOTHING_BLACK;
					Colour condomColourTer = PresetColour.CLOTHING_BLACK;
					
					if(clothing.getColourReplacement(1)!=null) {
						condomColourSec = clothing.getColourReplacement(1).getRandomOfDefaultColours();
					}
					if(clothing.getColourReplacement(2)!=null) {
						condomColourTer = clothing.getColourReplacement(2).getRandomOfDefaultColours();
					}
					for (int i = 0; i < (3+(Util.random.nextInt(4)))*(clothing.getRarity()==Rarity.COMMON?3:(clothing.getRarity()==Rarity.UNCOMMON?2:1)); i++) {
						this.addClothing(Main.game.getItemGen().generateClothing(clothing, condomColour, condomColourSec, condomColourTer, false), false);
					}
					
				} else {
					this.addClothing(Main.game.getItemGen().generateClothing(clothing, false), false);
				}
			}
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
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
	public String getTraderDescription() {
		if(Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID)>0){
			StringBuilder descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>Вы оглядываетесь на прилавок и видите, что Ralph улыбается вам в ответ. Почувствовав, что вам может понадобиться помощь, он бодро направляется к вам.</p><p>Хотя воспоминание о том, как вы покорно ласкали огромный конский член Ralph, всё еще свежо в памяти обоих, он относится к вам с величайшим уважением и вежливо перечисляет цены на выставленные на продажу трансформирующие расходники."
						+ (this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
								?" Хотя большинство из них, кажется, продаются с разумной наценкой, маленькие синие «"+ItemType.getItemTypeFromId("innoxia_pills_sterility").getNamePlural(false)+"», похоже, стоят неоправданно дорого, и Ralph снова отмечает, что на их продажу наложен огромный налог.</p><p>"
						:"")
						+ " Он напоминает вам, что также заинтересован в покупке любых трансформирующих расходников, которые вы готовы продать. Когда вы поворачиваетесь обратно к товарам, Ralph говорит, что обновляет свой ассортимент каждый день.</p>");
			
			if(isDiscountActive()){
				descriptionSB.append("<p>"
						+ "<b>Ralph is giving you a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>скидка!</b></p>");
			}else{
				descriptionSB.append("<p>Прежде чем он развернется, чтобы вернуться к прилавку, он наклоняется и шепчет вам на ухо: "
						+ UtilText.parseSpeech("Если захочешь снова получить небольшую скидку, просто позови меня.", Main.game.getNpc(Ralph.class))
							+ "</p>");
			}
			
			return descriptionSB.toString();
			
		} else {
			return "<p>Вы переводите взгляд на прилавок и видите, что Ralph улыбается вам в ответ. Почувствовав, что вам может понадобиться помощь, он бодро направляется к вам.</p><p>После короткого приветствия вы расспрашиваете его о выставленных на продажу трансформирующих расходниках. Он вежливо сообщает, что все они продаются, и быстро перечисляет их цены."
						+ (this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
							?" Хотя большинство из них, кажется, продаются с разумной наценкой, маленькие синие «"+ItemType.getItemTypeFromId("innoxia_pills_sterility").getNamePlural(false)+"' кажутся неоправданно дорогими, и после вашего вопроса о высокой стоимости Ralph объясняет, что на их продажу наложен огромный налог.</p><p>"
							:"")
						+ " Почувствовав, что вы можете быть не обычным покупателем, он говорит, что также заинтересован в покупке любых трансформирующих расходников, которые вы захотите продать. Когда вы снова поворачиваетесь к товарам, Ralph сообщает, что обновляет свой запас каждый день.</p>";
		}
		
	}

	@Override
	public boolean isTrader() {
		return true;
	}
	
	@Override
	public float getSellModifier(AbstractCoreItem item) {
		float base = 1.5f;
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType()==ItemType.getItemTypeFromId("innoxia_pills_sterility")) {
				base*=10;
			}
		}
		return Math.max(getBuyModifier(), (base * ((100-Main.game.getDialogueFlags().ralphDiscount)/100f)) * (Main.game.getPlayer().hasTrait(Perk.JOB_STUDENT, true)?0.75f:1));
	}
	
	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY)) {
			return false;
		}
		if(item instanceof AbstractItem) {
			return true;
		}
		if(item instanceof AbstractClothing) {
			AbstractClothingType type = ((AbstractClothing)item).getClothingType();
			return type.isDefaultSlotCondom();
		}
		
		return false;
	}

	@Override
	public void endSex() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.getDisplacedList().clear();
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex() && !target.isAsleep()) {
			if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK && target.equals(this)) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name] [npc.verb(direct)] [npc.her] прядилка у [npc2.namePos] [npc2.cock], но, предчувствуя, что [npc.sheIs] собирается сделать, он отмахивается от нее и ворчит: [npc2.speech(Не думаю! Ты согласилась, чтобы я тебя оплодотворил, и именно это я и сделаю!)]");
				}
				return UtilText.parse(target,
						"<p>Вы достаете презерватив и протягиваете его Ralph, но, увидев, что это, он хватает его и разрывает пополам, после чего пренебрежительно ворчит: [npc.speech(Не думаю! Ты согласилась, чтобы я тебя оплодотворил, и именно это я и сделаю!)]</p>");
			}
			
			if(!target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>Вы достаете презерватив из своего инвентаря и, издав приглушенный вопросительный звук, протягиваете его Ralph. Он смотрит на вас сверху вниз, затем кивает и отстраняется, вынимая свой огромный член из вашего рта. У вас появляется мгновение, чтобы перевести дух, пока Ralph разрывает маленькую фольгированную упаковку и натягивает презерватив на всю длину своего массивного ствола. Снова шагнув вперед, он вталкивает свой обтянутый резиной член обратно вам в глотку, и вы издаете приглушенный вздох облегчения, радуясь тому, что он сделал так, как вы просили.</p>";
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>Пока Ralph продолжает с силой вколачивать свой огромный член в вашу киску, вы шарите в инвентаре и достаете презерватив. Подавляя стоны, вы поворачиваетесь к нему и, протянув руку, просите надеть его. Он разочарованно вздыхает, но не возражает открыто, вынимая член из вашей киски; это дает вам секунду передохнуть, пока он разрывает маленькую фольгированную упаковку и натягивает презерватив на всю длину своего массивного ствола. Как только презерватив надежно зафиксирован, он пристраивается и толкается вперед, глубоко погружая свой обтянутый резиной член в вашу киску.</p>";
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>Пока Ralph продолжает с силой вколачивать свой огромный член в твою задницу, ты шаришь в своем инвентаре и достаешь презерватив. Сдерживая стоны, ты оборачиваешься и, протянув ему упаковку, просишь надеть его. Он разочарованно вздыхает, но не возражает, вынимая свой член из твоего заднего прохода; это дает тебе мгновение передохнуть, пока он разрывает маленькую фольгированную упаковку и натягивает презерватив на всю длину своего массивного ствола. Как только презерватив плотно садится на место, он пристраивается и толкается вперед, глубоко погружая свой обтянутый резиной член в твой ждущий анус.</p>";
				} else {
					return "<p>Достав презерватив из инвентаря, ты наклоняешься вперед, смотришь на Ralph и, протягивая ему маленькую фольгированную упаковку, просишь надеть его. Со вздохом он забирает презерватив у тебя и, вскрыв упаковку, быстро натягивает его на всю длину своего массивного ствола. Ты благодаришь его за то, что он послушался, на что он отвечает, что рад уважить твою просьбу.</p>";
				}
			}
		}
		return null;
	}
	
	
	@Override
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		if(Math.random()>0.3) {
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.VAGINA) {
				return UtilText.returnStringAtRandom(
						"Ralph продолжает вжимать тебя в столешницу, трахая твою [pc.pussy+].",
						"Ты чувствуешь крепкую хватку Ralph на своих бедрах, пока его [ralph.cock+] долбит твою [pc.pussy+].",
						"[ralph.cock+] Ralph продолжает с силой входить и выходить из твоей ненасытной [pc.pussy].",
						"Губы твоей киски обволакивают [ralph.cock+] Ralph, пока он трахает тебя на столешнице.");
			}
			
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.MOUTH) {
				return UtilText.returnStringAtRandom(
						"Слизистая слюна стекает по твоему подбородку, пока ты продолжаешь сосать [ralph.cock+] Ralph.",
						"Ты смотришь вниз на [ralph.cock+], который то входит, то выходит из твоего рта.",
						"[ralph.cock+] Ralph проталкивается сквозь твои губы, пока ты стоишь перед ним на коленях.",
						"Ты ритмично двигаешь головой вверх-вниз, продолжая обслуживать [ralph.cock+] Ralph.");
			}
		}

		return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
	}
	
	// Vagina:
	
	@Override
	public String getStretchingDescription(boolean initialPenetration, GameCharacter partner, SexAreaPenetration penetrationType, SexAreaOrifice orifice, boolean pastTense) {
		switch(orifice) {
			case MOUTH:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
				} else {
					return UtilText.returnStringAtRandom(
							"Ты чувствуешь, как слезы текут по твоему лицу, когда ты пытаешься проглотить [ralph.cock+].",
							"Ты недостаточно умело занимаешься минетом, чтобы с комфортом справляться с [ralph.cock+] Ralph.",
							"Ты корчишься и задыхаешься, изо всех сил пытаясь проглотить [ralph.cock+].",
							"Ты изо всех сил пытаешься проглотить [ralph.cock+] Ralph.");
				}
			case VAGINA:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
				} else {
					return UtilText.returnStringAtRandom(
							"Ты неудобно ерзаешь на столешнице, пока [ralph.cock+] Ralph с трудом входит в твою [pc.pussyCapacity] [pc.pussy].",
							"[ralph.cock+] Ralph болезненно растягивает тебя, когда с силой входит и выходит из твоей [pc.pussyCapacity] [pc.pussy].",
							"[ralph.cock+] Ralph слишком велик для твоего [pc.pussyCapacity] [pc.pussy], и ты издаешь неуютный стон, когда он растягивает тебя.",
							"Твой [pc.pussyCapacity] [pc.pussy] с трудом справляется с невероятным размером [ralph.cock+] Ralph.");
				}
			default:
				return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
		}
	}
	
	@Override
	public String getStretchingFinishedDescription(SexAreaOrifice orifice) {
		switch(orifice) {
			case MOUTH:
				return "В следующий раз, когда Ralph мягко толкнется вперед, ты чувствуешь, как твоя глотка инстинктивно раскрывается, чтобы принять его раздутый лошадиный член, и с приглушенным стоном восторга ты понимаешь, что теперь можешь с комфортом сосать массивный черный конский член Ralph!";
			case VAGINA:
				return "В следующий раз, когда Ralph с силой ворвется вперед, ты чувствуешь, как твоя киска легко растягивается, чтобы принять его раздутый лошадиный член, и с восторженным визгом ты понимаешь, что теперь твой [pc.pussy+] может с комфортом вместить массивный черный конский член Ralph!";
			default:
				return super.getStretchingFinishedDescription(orifice);
		}
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				itemOwner.useItem(item, target, false);
				
				if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
							"<p>Ты достаешь "+item.getName(false, false)+" из своего инвентаря и, издав приглушенный вопросительный звук, протягиваешь это Ralph. Он смотрит на тебя сверху вниз, тихо посмеиваясь и пожимая плечами, когда видит, что ты пытаешься ему дать. Быстро выщелкнув таблетку из пластикового контейнера, он проглатывает ее, и ты издаешь радостный, хоть и несколько приглушенный смешок, понимая, что его сперма только что стала гораздо мощнее.</p>");
				
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
							"<p>Пока Ralph продолжает вколачивать свой огромный член в твою киску, ты шаришь в своем инвентаре и извлекаешь "+item.getName(false, false)+". Сдерживая стоны, ты оборачиваешься и протягиваешь ему руку, прося проглотить это. Он издает короткий смешок, видя, что ты ему предлагаешь, и, быстро выщелкнув таблетку из пластикового контейнера и проглотив ее, на мгновение прерывается, чтобы сказать пару слов, прежде чем продолжить трахать тебя, "
								+ (Main.game.getNpc(Ralph.class).isWearingCondom()
									?" [ralph.speech(Ты ведь понимаешь, что на мне презерватив, да?)]"
									:(Main.game.getPlayer().isVisiblyPregnant()
											?" [ralph.speech(Ох, ты ведь уже беременна, но ладно, почему бы и нет?)]"
											:" [ralph.speech(Надеешься забеременеть, а? Что ж, я буду только рад помочь!)]"))
							+ "</p>");
					
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
								"<p>Пока Ralph продолжает вколачивать свой огромный член в твою задницу, ты шаришь в своем инвентаре и достаешь "+item.getName(false, false)+". Сдерживая стоны, ты оборачиваешься и протягиваешь ему руку, прося проглотить это. Он издает короткий смешок, видя, что ты ему предлагаешь, и, быстро выщелкнув таблетку из пластикового контейнера и проглотив ее, на мгновение прерывается, чтобы сказать пару слов, прежде чем продолжить трахать тебя, [ralph.speech(Ты ведь знаешь, что от этого нельзя забеременеть, верно?)]</p>");
					
				} else {
					return new Value<>(true,
							"<p>Доставая "+item.getName(false, false)+" из своего инвентаря, ты наклоняешься вперед, глядя на Ralph, и просишь его проглотить это, поднося предмет к его лицу. Он смотрит на тебя сверху вниз, тихо посмеиваясь и пожимая плечами, когда понимает, что именно ты пытаешься ему дать. Быстро выщелкнув таблетку из пластикового контейнера, он проглатывает ее, и ты радостно хихикаешь, зная, что его сперма только что стала куда мощнее.</p>");
				}
				
			} else {
				if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>Вы достаете "+item.getItemType().getDeterminer()+" "+item.getName()+" из своего инвентаря и, издав приглушенный вопросительный звук, протягиваете это Ralph. Он смотрит на вас сверху вниз, затем издает пренебрежительное хмыканье и слегка подается вперед, проталкивая свой член еще глубже в ваше горло, пока вы убираете "+item.getName()+" обратно в инвентарь.</p>");
					
				} else if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>Пока Ralph продолжает вколачивать свой огромный член в твою киску, ты шаришь в своем инвентаре и извлекаешь "+item.getItemType().getDeterminer()+" "+item.getName()+". Сдерживая стоны, вы спрашиваете его, не хочет ли он "+item.getItemType().getUseName()+" , но он лишь пренебрежительно хмыкает и продолжает трахать вас. Вы снова начинаете вздыхать и вскрикивать от удовольствия, убирая "+item.getName()+" обратно в инвентарь.</p>");
					
				} else if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>Пока Ralph продолжает вколачивать свой огромный член в твою задницу, ты шаришь в своем инвентаре и достаешь "+item.getItemType().getDeterminer()+" "+item.getName()+". Сдерживая стоны, вы спрашиваете его, не хочет ли он "+item.getItemType().getUseName()+" , но он лишь пренебрежительно хмыкает и продолжает трахать вас. Вы снова начинаете вздыхать и вскрикивать от удовольствия, убирая "+item.getName()+" обратно в инвентарь.</p>");
					
				} else {
					return new Value<>(false,
							"<p>Вы спрашиваете Ralph, не хочет ли он использовать "+item.getItemType().getDeterminer()+" "+item.getName()+" что у тебя есть, но он лишь пренебрежительно хмыкает и говорит, что не заинтересован. Со вздохом ты кладешь "+item.getName()+" обратно в инвентарь.</p>");
				}
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
}
