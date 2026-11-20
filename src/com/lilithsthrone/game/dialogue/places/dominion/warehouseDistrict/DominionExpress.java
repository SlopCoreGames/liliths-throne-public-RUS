package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionExpressCentaur;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public class DominionExpress {
	
	private static SexAreaInterface sleepSexAreaWanted = null;
	private static int slavePointsReward = 1;
	private static GameCharacter activeSlave;

	private static List<MuleReward> muleRewards;
	static {
		muleRewards = new ArrayList<>();
		// Basic mule: Add horse-morph penis/ass, feminine++, boobs++:
		muleRewards.add(
				new MuleReward("Базовый [style.Mule]", "Избавься от своей киски и обзаведись конь-морф членом и задницей, а также огромной грудью и женственной фигурой!", 5) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BASIC_FILLY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().setVaginaType(VaginaType.NONE));
						sb.append(Main.game.getPlayer().setAssType(AssType.HORSE_MORPH));
						sb.append(Main.game.getPlayer().setPenisType(PenisType.EQUINE));
						if(Main.game.getPlayer().getFemininityValue()<75) {
							sb.append(Main.game.getPlayer().setFemininity(75));
						}
						if(Main.game.getPlayer().getBreastSize().getMeasurement()<CupSize.D.getMeasurement()) {
							sb.append(Main.game.getPlayer().setBreastSize(CupSize.D));
							if(Main.game.getPlayer().getNippleSize().getValue()<NippleSize.TWO_BIG.getValue()) {
								Main.game.getPlayer().setNippleSize(NippleSize.TWO_BIG);
							}
							if(Main.game.getPlayer().getAreolaeSize().getValue()<AreolaeSize.TWO_BIG.getValue()) {
								Main.game.getPlayer().setAreolaeSize(AreolaeSize.TWO_BIG);
							}
						}
						return sb.toString();
					}
				});
		// Taur: Makes lower body taur:
		muleRewards.add(
				new MuleReward("Тав-тастично", "Стань кентавром и наконец-то получи то самое дикое лошадиное тело, о котором всегда мечтал!", 5) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						if(Main.game.getPlayer().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
							return new Value<>(false, "Ты уже тавр, так что эта награда ничего не даст!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_TAUR"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
							if(Main.game.getPlayer().getLegType()!=LegType.DEMON_HORSE_HOOFED) {
								sb.append(Main.game.getPlayer().setLegType(LegType.DEMON_HORSE_HOOFED));
							}
							sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.QUADRUPEDAL, true));
						} else {
							if(Main.game.getPlayer().getLegType()!=LegType.HORSE_MORPH) {
								sb.append(Main.game.getPlayer().setLegType(LegType.HORSE_MORPH));
							}
							sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.QUADRUPEDAL, true));
						}
						return sb.toString();
					}
				});
		// Ass-pussy: Lubrication, puffy, depth, muscles.
		muleRewards.add(
				new MuleReward("Задница-киска", "Преврати свою задницу в идеальное сексуальное отверстие!", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_ASS_PUSSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementAssWetness(3));
						sb.append(Main.game.getPlayer().incrementAssDepth(3));
						sb.append(Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
						return sb.toString();
					}
				});
		// Throat-pussy: Bigger lips, wetter throat, depth, muscles.
		muleRewards.add(
				new MuleReward("Глотательная щель", "После такого сосать конский член уже никогда не будет прежним!", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_THROAT_PUSSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementFaceWetness(3));
						sb.append(Main.game.getPlayer().incrementFaceDepth(3));
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
						return sb.toString();
					}
				});
		// Ass-licker: Bigger lips, wetter tongue, longer tongue.
		muleRewards.add(
				new MuleReward("Задницелиз", "Большие пухлые губы и длинный сильный язык — это именно то, что нужно, чтобы подарить всем этим лошадиным задницам любовь, которую они заслуживают!", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_ASS_LICKER"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementFaceWetness(3));
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().incrementTongueLength(10));
						sb.append(Main.game.getPlayer().addTongueModifier(TongueModifier.STRONG));
						return sb.toString();
					}
				});
		// Hung: Penis size+, more cum, bigger balls.
		muleRewards.add(
				new MuleReward("Размер как у жеребца", "Тебе понравится это ощущение: когда тебя жестко трахают, а между ног болтается твой огромный толстый член!", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							return new Value<>(false, "Чтобы получить эту награду, у тебя должен быть пенис!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_HUNG"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementPenisSize(10));
						if(Main.game.getPlayer().getPenisGirth().getValue()<PenetrationGirth.FIVE_THICK.getValue()) {
							sb.append(Main.game.getPlayer().incrementPenisGirth(1));
						}
						if(Main.game.getPlayer().getTesticleSize().getValue()<TesticleSize.FIVE_MASSIVE.getValue()) {
							sb.append(Main.game.getPlayer().incrementTesticleSize(1));
						}
						sb.append(Main.game.getPlayer().incrementPenisCumStorage(50));
						return sb.toString();
					}
				});
		// Sissy mule: Penis size-, smaller balls.
		muleRewards.add(
				new MuleReward("Сисси-[style.mule]", "Покажи своим партнерам, какая ты покорная маленькая сисси-[style.mule], зажав между ног свой жалко-крошечный член!", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							return new Value<>(false, "Чтобы получить эту награду, у тебя должен быть пенис!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_SISSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						if(Main.game.getPlayer().getPenisRawSizeValue()>PenisLength.ONE_TINY.getMedianValue()) {
							if(Main.game.getPlayer().getPenisRawSizeValue()>PenisLength.ONE_TINY.getMedianValue()+10) {
								sb.append(Main.game.getPlayer().incrementPenisSize(-10));
							} else {
								sb.append(Main.game.getPlayer().setPenisSize(PenisLength.ONE_TINY.getMedianValue()));
							}
						}
						if(Main.game.getPlayer().getPenisGirth().getValue()>PenetrationGirth.THREE_AVERAGE.getValue()) {
							sb.append(Main.game.getPlayer().incrementPenisGirth(-1));
						}
						if(Main.game.getPlayer().getTesticleSize().getValue()>TesticleSize.ONE_TINY.getValue()) {
							sb.append(Main.game.getPlayer().incrementTesticleSize(-1));
						}
						return sb.toString();
					}
				});
		// Top-heavy: Breast size+, puffy nipples, nipple size+
		muleRewards.add(
				new MuleReward("Тяжелая грудь", "Увеличьте размер своей груди, чтобы этим рабыням-кентаврам было на что пялиться (и что сжимать)!", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_TOP_HEAVY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementBreastSize(3));
						sb.append(Main.game.getPlayer().incrementNippleSize(1));
						sb.append(Main.game.getPlayer().incrementAreolaeSize(1));
						sb.append(Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY));
						return sb.toString();
					}
				});
		// Subby slut: submissive, oral giving, anal giving, penis receiving, anal receiving
		muleRewards.add(
				new MuleReward("Подчиняющаяся шлюшка", "Позволь нам напомнить тебе, какая ты грязная маленькая [style.mule] шлюшка!", 50) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_SUBBY"));
						sb.append(Main.game.getPlayer().removeFetish(Fetish.FETISH_DOMINANT, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_PENIS_RECEIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						return sb.toString();
					}
				});
		// Bimbo: Add bimbo fetish, big lips, bleach-blonde hair, breast size+
		muleRewards.add(
				new MuleReward("Бимбо-дива", "Думать о чем-то, кроме подчинения конскому члену и конской заднице — это же, типа, дико бесит, да?", 50) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "Чтобы получить эту награду, тебе нужно иметь доступ к своему рту!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BIMBO"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_MENTAL_END"));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_BIMBO, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE, true));
						
						if(Main.game.getPlayer().getFemininityValue()<95) {
							sb.append(Main.game.getPlayer().setFemininity(95));
						}
						
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						
						sb.append(Main.game.getPlayer().incrementBreastSize(6));
						sb.append(Main.game.getPlayer().incrementNippleSize(1));
						sb.append(Main.game.getPlayer().incrementAreolaeSize(1));
						sb.append(Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY));

						sb.append(Main.game.getPlayer().setHairCovering(
								new Covering(Main.game.getPlayer().getHairType().getBodyCoveringType(Main.game.getPlayer()), CoveringPattern.HIGHLIGHTS, PresetColour.COVERING_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), false));
						
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BIMBO_END"));
						Main.game.getPlayer().addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
						
						return sb.toString();
					}
				});
		
		muleRewards.add(
				new MuleReward("Серебряный ошейник", "Получите это блестящее улучшение! С ним вам будет разрешено обслуживать госпожу Наталью раз в день!", 100) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(getPlayerCollar().getColour(0)!=PresetColour.CLOTHING_BRONZE) {
							return new Value<>(false, "Вы уже улучшили свой ошейник до серебряного!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), true);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_SILVER_COLLAR"));
						getPlayerCollar().setColour(0, PresetColour.CLOTHING_SILVER);
						return sb.toString();
					}
				});
		
		muleRewards.add(
				new MuleReward("Золотой ошейник", "Госпожа Наталья обещала позволить любому золоту [style.mule] доминировать над ней, но будем честны: тебе это явно не по карману!", 1000) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(getPlayerCollar().getColour(0)==PresetColour.CLOTHING_GOLD) {
							return new Value<>(false, "Вы уже улучшили свой ошейник до золотого!");
						}
						if(getPlayerCollar().getColour(0)!=PresetColour.CLOTHING_SILVER) {
							return new Value<>(false, "Вам нужно улучшить ошейник до серебряного, прежде чем переходить к золотому!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), true);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_GOLD_COLLAR"));
						getPlayerCollar().setColour(0, PresetColour.CLOTHING_GOLD);
						return sb.toString();
					}
				});
	}
	
	
	private static AbstractClothing getPlayerCollar() {
		return Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
	}
	
	private static boolean wearingMuleCollar() {
		return getPlayerCollar()!=null && getPlayerCollar().getClothingType().getId().equals("innoxia_neck_filly_choker");
	}
	
	private static AbstractClothing generateCollar() {
		return Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_filly_choker"), false);
	}
	
	private static boolean isPlayerBodyCorrect() {
		return Main.game.getPlayer().isFeminine()
				&& Main.game.getPlayer().getBreastSize().getMeasurement()>=CupSize.D.getMeasurement()
				&& Main.game.getPlayer().hasPenis()
				&& !Main.game.getPlayer().hasVagina();
	}
	
	private static String applyTransformation() {
		StringBuilder sb =  new StringBuilder();
		
		TransformativePotion potion = Main.game.getNpc(Natalya.class).generateTransformativePotion(Main.game.getPlayer());

		if(Main.game.getPlayer().isAbleToHaveRaceTransformed()) {
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.COVERING_BLACK), false);
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.COVERING_BLACK), false);
		}
		
		for(PossibleItemEffect pe : potion.getEffects()) {
			sb.append(pe.getEffect().applyEffect(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), 1));
		}
		
		return sb.toString();
	}
	
	public static GameCharacter spawnSlave(boolean feminine) {
		return spawnSlave(feminine, null);
	}
	
	public static GameCharacter spawnSlave(boolean feminine, Colour collarColour) {
		slavePointsReward = 2+Util.random.nextInt(4); // Slaves give the player 2-5 points to service them
		
		NPC npc;
		if(collarColour==null) {
			collarColour = PresetColour.CLOTHING_GOLD;
			double rnd = Math.random();
			if(rnd<0.8f) {
				collarColour = PresetColour.CLOTHING_BRONZE;
			} else if(rnd<0.95f) {
				collarColour = PresetColour.CLOTHING_SILVER;
			}
		}
		if(feminine) {
			npc = new DominionExpressCentaur(Gender.F_P_B_SHEMALE, collarColour);
		} else {
			npc = new DominionExpressCentaur(Gender.M_P_MALE, collarColour);
		}
		npc.setLocation(Main.game.getPlayer(), false);
		
		npc.setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed()-(25*60*60));
		npc.removeCumModifier(FluidModifier.ADDICTIVE);
		npc.removeCumModifier(FluidModifier.HALLUCINOGENIC);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.getNpc(Natalya.class).addSlave(npc);
		
		return npc;
	}
	
	public static void applySadistSlave(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_SADIST);
		
		slave.removePersonalityTraits(PersonalityCategory.SPEECH);
		
		slave.setName(new NameTriplet("Гром"));
		slave.setPlayerKnowsName(true);

		slave.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		slave.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		slave.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		slave.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		
		slave.setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_HAZEL), false);
		slave.setSkinCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_HAZEL), false);
		
		if(slave.isFeminine()) {
			slave.setHairLength(HairLength.FOUR_MID_BACK);
			slave.setHairStyle(HairStyle.BRAIDED);
		} else {
			slave.setHairLength(HairLength.ONE_VERY_SHORT);
			slave.setHairStyle(HairStyle.NONE);
		}
		
		slave.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.COVERING_BLACK), false);
		slave.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.COVERING_BLACK), false);
		
		slave.setHeight(235);
		
		Main.game.getDialogueFlags().setSadistNatalyaSlave(slave.getId());
	}
	
	private static List<GameCharacter> getSavedSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION_EXPRESS).getCell(PlaceType.DOMINION_EXPRESS_STABLES)));
		list.removeIf((npc)->!(npc instanceof DominionExpressCentaur));
		return list;
	}
	
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->!(npc instanceof DominionExpressCentaur));
		return list;
	}

	private static GameCharacter getSadistSlave() {
		try {
			return Main.game.getNPCById(Main.game.getDialogueFlags().getSadistNatalyaSlave());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void banishSlave(GameCharacter slave, boolean delete) {
		if(delete) {
			Main.game.banishNPC((NPC) slave);
			
		} else {
			slave.setHomeLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_STABLES);
			slave.returnToHome();
		}
		activeSlave = null;
	}
	
	public static final DialogueNode INITIAL_ENTRANCE = new DialogueNode("", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
				return ENTRANCE.getResponse(responseTab, index);
				
			} else {
				if(index==0) {
					return new Response("Выход", "Скажите регистратору, что совершили ошибку, и вернитесь обратно в складской район.", Warehouses.WAREHOUSE_DISTRICT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
						}
					};
					
				} else if(index==1) {
					return new Response("Показать карту", "Покажите регистратору карту, которую вы получили от Натальи.", INITIAL_ENTRANCE_CARD_SHOWN);
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode INITIAL_ENTRANCE_CARD_SHOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE_CARD_SHOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Выход", "Вернуться обратно в складской район.", Warehouses.WAREHOUSE_DISTRICT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(wearingMuleCollar()) {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Привлечь внимание", "Так как вы не можете пользоваться ртом, вы не способны обслуживать кентавров-рабынь, так что нет смысла пытаться привлечь их внимание...", null);
						
					} else {
						return new ResponseEffectsOnly(
								"Искать партнера",
								"С твоим напоказ выставленным [style.mule] ошейником, ты понимаешь, что без труда сможешь привлечь внимание проходящего мимо раба-кентавра..."){
								@Override
								public int getSecondsPassed() {
									return 5*60;
								}
								@Override
								public void effects() {
									DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode STORAGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode FILLY_STATION = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Машина", "Подойди к машине и попробуй взаимодействовать с ней...", FILLY_STATION_MACHINE);
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==FILLY_STATION_POSTER) {
					return new Response("Плакат", "Ты уже читаешь информационный плакат...", null);
				}
				return new Response("Плакат", "Прочитать информационный плакат, приклеенный к стене рядом с аркана-машиной.", FILLY_STATION_POSTER);
			}
			return null;
		}
	};

	public static final DialogueNode FILLY_STATION_POSTER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_POSTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return FILLY_STATION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode FILLY_STATION_MACHINE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE"));
			
			if(!wearingMuleCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE_REJECTED"));
				
			} else {
				UtilText.addSpecialParsingString(String.valueOf(Main.game.getDialogueFlags().getNatalyaPoints()), true);
				Colour collarColour = getPlayerCollar().getColour(0);
				UtilText.addSpecialParsingString("<span style='color:"+collarColour.toWebHexString()+";'>"+Util.capitaliseSentence(collarColour.getName())+"</span>", false);
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE_ACCEPTED"));
				
				sb.append("<div class='container-full-width'>");
					for(MuleReward fr : muleRewards) {
						int points = Main.game.getDialogueFlags().getNatalyaPoints();
						Value<Boolean, String> available = fr.isAvailable();
						sb.append("<div class='container-full-width inner' style='margin-bottom:4px;'>");
							sb.append((available.getKey()?"[style.boldPink(":"[style.boldBad(")+fr.getName()+":)] ");
							sb.append((points<fr.getCost()?"[style.colourBad(":"[style.colourPinkLight(")+fr.getCost()+")] [style.colourPinkLight(Очки [style.Mule])]<br/>");
							sb.append(fr.getDescription());
							if(!available.getKey()) {
								sb.append("<br/>[style.italicsBad("+available.getValue()+")]");
							}
						sb.append("</div>");
					}
				sb.append("</div>");
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Отступить", "Отойди от машины...", FILLY_STATION);
			}
			if(wearingMuleCollar()) {
				List<Response> responses = new ArrayList<>();
				for(MuleReward fr : muleRewards) {
					int points = Main.game.getDialogueFlags().getNatalyaPoints();
					Value<Boolean, String> available = fr.isAvailable();
					if(!available.getKey()) {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsBad("+available.getValue()+")]", null));
						
					} else if(points<fr.getCost()) {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsBad(У вас "+(points==0?"нет,":"у вас есть "+points)+" всего [style.mule] очков, так что вы не можете позволить себе стоимость "+fr.getCost()+"!)]", null));
						
					} else {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsPinkLight(У вас есть "+points+" [style.mule] очков, и это будет стоить вам "+fr.getCost()+"!)]", FILLY_STATION_REWARD) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(fr.applyEffect());
								Main.game.getTextStartStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-fr.getCost()));
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
			}
			return null;
		}
	};

	public static final DialogueNode FILLY_STATION_REWARD = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить взаимодействие со станцией наград [style.mule].", FILLY_STATION_MACHINE);
			}
			return null;
		}
	};
	
	
	public static final DialogueNode STABLES = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES"));
			if(!wearingMuleCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_NO_COLLAR"));
			} else if(!isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_WRONG_BODY"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTER"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					if(!wearingMuleCollar()) {
						return new Response("Войти", "Вы не сможете увидеть госпожу Наталью, не надев свой ошейник [style.mule]!", null);
					} else if(!isPlayerBodyCorrect()) {
						return new Response("Войти", "Вы не сможете увидеть госпожу Наталью, не будучи пышногрудой [style.shemale]!", null);
					}
					return new Response("Войти", "Войдите в конюшни и поищите рабынь-кентавров, чтобы обслужить их...", STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTERED"));
						}
					};
					
				} else {
					return new Response("Войти", "Так как вы не раб и не обладаете нужной [style.mule], вы не можете войти в конюшни...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLES_INTERIOR = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Конюшни";
			} else if(index==1) {
				return "Избранные";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Выход", "Выйти из конюшен и вернуться в основной складской район...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_EXIT"));
					}
				};
				
			}
			if(responseTab==0) {
				if(index==1) {
					if(getSavedSlaves().size()>=10) {
						return new Response("Кентавр", "У вас уже максимально возможное количество избранных рабов (10)...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Кентавр", "Вы не можете дотянуться до своего рта, а значит, не можете обслужить рабов...", null);
					}
					return new Response("Кентавр", "Искать кентавра для обслуживания...", STABLE_SEX) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.MASCULINE;
						}
						@Override
						public void effects() {
							activeSlave = spawnSlave(false);
							activeSlave.setPlayerKnowsName(true);
							UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", activeSlave));
						}
					};
					
				} else if(index==2) {
					if(getSavedSlaves().size()>=10) {
						return new Response("Кентавридка", "У вас уже максимально возможное количество избранных рабов (10)...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Кентавридка", "Вы не можете дотянуться до своего рта, а значит, не можете обслужить рабов...", null);
					}
					return new Response("Кентавридка", "Искать кентавриху для обслуживания...", STABLE_SEX) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE;
						}
						@Override
						public void effects() {
							activeSlave = spawnSlave(true);
							activeSlave.setPlayerKnowsName(true);
							UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", activeSlave));
						}
					};
					
				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==STABLE_SHOWER) {
						return new Response("Душ", "Вы только что приняли душ!", null);
					}
					return new Response("Душ",
								"Используйте душевые в конюшне, чтобы очиститься.<br/>[style.italicsGood(Очищает <b> максимум "+Units.fluid(500)+"</b> жидкостей из всех отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
								STABLE_SHOWER);
				}
				
			} else if(responseTab==1) {
				List<Response> responses = new ArrayList<>();
				for(GameCharacter slave : getSavedSlaves()) {
					if(!getSlaves().contains(slave)) {
						responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "[npc.Name] сейчас на работе, поэтому она недоступна для сексуального обслуживания..."), null));
						
					} else {
						responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Найти стойло [npc.namePos] и предложить [npc.herHim] снова заняться сексом..."), STABLE_SEX) {
							@Override
							public void effects() {
								activeSlave = slave;
								UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
								if(activeSlave.equals(getSadistSlave())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER_SADIST", activeSlave));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER_REPEAT", activeSlave));
								}
							}
						});
					}
				}
				for(int i=0; i<responses.size(); i++) {
					if(index-1==i) {
						return responses.get(i);
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLE_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Сосать член",
						UtilText.parse(activeSlave, "Опуститься на колени и сосать член [npc.namePos]."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(activeSlave, new HashMap<>());
								map.get(activeSlave).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(activeSlave).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), activeSlave, PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Анилингус",
						UtilText.parse(activeSlave, "Опуститься на колени позади [npc.name] и сделать [npc.herHim] римjob."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), activeSlave, TongueAnus.ANILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Оседлан", UtilText.parse(activeSlave, "Вы не можете расслабить анус, поэтому [npc.name] не может на вас сесть..."), null);
				}
				return new ResponseSex(
						"Оседлан",
						UtilText.parse(activeSlave, "Представить свой [pc.asshole+] для [npc.name] и позволить [npc.herHim] оседлать вас."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(activeSlave, new HashMap<>());
								map.get(activeSlave).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(activeSlave).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_MOUNTED")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX = new DialogueNode("Завершено", "Рабыня закончила с вами...", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(activeSlave, "[npc.Name] закончил с тобой...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(slavePointsReward));
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX", activeSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти ([style.colourMinorGood(Любимое)])",
						UtilText.parse(activeSlave, "Вы решаете запомнить стойло [npc.namePos], чтобы иметь возможность навестить [npc.herHim] снова, когда вернетесь в конюшни.<br/>[style.colourMinorGood(Вы сможете снова встретить [npc.name]!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, false);
					}
				};
				
			} else if(index==2) {
				if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
					return new Response("Уйти (Забыть)", UtilText.parse(activeSlave, "Вы не можете забыть [npc.name]..."), null);
				}
				return new Response("Уйти ([style.colourMinorBad(Забыть)])",
						UtilText.parse(activeSlave, "Вы решаете больше никогда не посещать [npc.name], выходя из стойла [npc.her] и направляясь обратно в конюшни.<br/>[style.colourMinorBad(Вы больше не сможете встретить [npc.name]!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, true);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getHourOfDay()>4 && Main.game.getHourOfDay()<22) {
					return new Response("Спать", UtilText.parse(activeSlave, "Переспать с рабыней можно только между [style.time(22)] и [style.time(4)]."), null);
					
				} else {
					return new Response("Спать", UtilText.parse(activeSlave, "Принять предложение [npc.namePos] переспать с [npc.herHim] на ночь."), AFTER_STABLE_SEX_SLEEP);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_STABLE_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			List<SexAreaInterface> list = Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				list.remove(SexAreaOrifice.MOUTH);
				list.remove(SexAreaPenetration.TONGUE);
			}
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				list.remove(SexAreaOrifice.ANUS);
			}
			if(list.isEmpty() || Math.random()<0.1f) {
				sleepSexAreaWanted = null;
			} else {
				sleepSexAreaWanted = Util.randomItemFrom(list);
			}
			if(sleepSexAreaWanted==null) {
				Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
			}
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(6*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP", activeSlave));
			if(sleepSexAreaWanted==null) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_NO_SEX", activeSlave));
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_BLOWJOB", activeSlave));
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_ANILINGUS", activeSlave));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_MOUNTED", activeSlave));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(sleepSexAreaWanted==null) {
				if(index==1) {
					return new Response("Уйти ([style.colourMinorGood(Любимое)])",
							UtilText.parse(activeSlave, "Вы решаете запомнить стойло [npc.namePos], чтобы иметь возможность навестить [npc.herHim] снова, когда вернетесь в конюшни.<br/>[style.colourMinorGood(Вы сможете снова встретить [npc.name]!)]"),
							STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", activeSlave));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
							banishSlave(activeSlave, false);
						}
					};
					
				} else if(index==2) {
					if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
						return new Response("Уйти (Забыть)", UtilText.parse(activeSlave, "Вы не можете забыть [npc.name]..."), null);
					}
					return new Response("Уйти ([style.colourMinorBad(Забыть)])",
							UtilText.parse(activeSlave, "Вы решаете больше никогда не посещать [npc.name], выходя из стойла [npc.her] и направляясь обратно в конюшни.<br/>[style.colourMinorBad(Вы больше не сможете встретить [npc.name]!)]"),
							STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", activeSlave));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
							banishSlave(activeSlave, true);
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				if(index==1) {
					return new ResponseSex(
							"Сосать член",
							UtilText.parse(activeSlave, "У тебя почти нет выбора, кроме как сосать жирный, дикий коньский член [npc.namePos]."),
							true,
							false,
							new SMDominionExpress(SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_BLOWJOB", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				if(index==1) {
					return new ResponseSex(
							"Анилингус",
							UtilText.parse(activeSlave, "У тебя почти нет выбора, кроме как сделать [npc.name] римjob."),
							true,
							false,
							new SMDominionExpress(SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotLyingDown.FACE_SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_ANILINGUS", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"Трахнутый",
							UtilText.parse(activeSlave, "У тебя почти нет выбора, кроме как позволить [npc.name] трахать твой анус."),
							true,
							false,
							new SMDominionExpress(SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_MOUNTED", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX = new DialogueNode("Завершено", "Рабыня закончила с вами...", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(activeSlave, "[npc.Name] закончил с тобой...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getForeplayPreference(activeSlave, Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_BLOWJOB", activeSlave));
				
			} else if(Main.sex.getForeplayPreference(activeSlave, Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_ANILINGUS", activeSlave));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_MOUNTED", activeSlave));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_FINISH", activeSlave));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти ([style.colourMinorGood(Любимое)])",
						UtilText.parse(activeSlave, "Вы решаете запомнить стойло [npc.namePos], чтобы иметь возможность навестить [npc.herHim] снова, когда вернетесь в конюшни.<br/>[style.colourMinorGood(Вы сможете снова встретить [npc.name]!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, false);
					}
				};
				
			} else if(index==2) {
				if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
					return new Response("Уйти (Забыть)", UtilText.parse(activeSlave, "Вы не можете забыть [npc.name]..."), null);
				}
				return new Response("Уйти ([style.colourMinorBad(Забыть)])",
						UtilText.parse(activeSlave, "Вы решаете больше никогда не посещать [npc.name], выходя из стойла [npc.her] и направляясь обратно в конюшни.<br/>[style.colourMinorBad(Вы больше не сможете встретить [npc.name]!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STABLE_SHOWER = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(false));
			Main.game.getPlayer().calculateStatusEffects(0);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(false, true));
			Main.game.getPlayer().cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 10 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SHOWER");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return STABLES_INTERIOR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STABLES_INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isAnalContentEnabled() && !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					return new Response("Войти",
							"У тебя возникает чувство, что ты не хочешь иметь ничего общего с Натальей...<br/>[style.italicsMinorBad(Сцены с Натальей включают анальный секс, и они будут отключены до тех пор, пока в настройках не будет включен параметр «Анальный контент».)]",
							null);
				}
				
				return new Response("Войти",
						"Постучать в дверь кабинета Натальи и войти внутрь."
						+ (!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
							?"<br/>[style.italicsQuestRomance(Это запустит романтический квест Натальи!)]"
							:""),
						OFFICE_STABLE_ENTRY) {
					@Override
					public Colour getHighlightColour() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
							return PresetColour.QUEST_RELATIONSHIP;
						}
						return super.getHighlightColour();
					}
				};
			}
			return null;
		}
	};
	
	private static boolean isOfficeEntryDenied() {
		return (Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && (!isPlayerBodyCorrect() || !wearingMuleCollar()))
				|| Main.game.getPlayer().isPregnant()
				|| Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)
				|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaBusy);
	}
	
	public static final DialogueNode OFFICE_STABLE_ENTRY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!isOfficeEntryDenied()) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_NATALYA));
				}
				if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
					Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD);
					Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD);
					Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD_STAMPED), false);
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(isOfficeEntryDenied()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_DENIED");
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY"));

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW"));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_REPEAT"));
				if(!isPlayerBodyCorrect()) { // If entering after interview has started, that means the player passed & signed the contact, and as such requires Natalya's preferred body:
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WRONG_BODY"));
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_2"));
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_3", getSadistSlave()));
					
				} else { // Qualified mule:
					if(getPlayerCollar().getColour(0)!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_UPGRADED"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WITH_COLLAR"));
					}
				}
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isOfficeEntryDenied()) {
				if(index==1) {
					return new Response("Уйти", "Поскольку в твоем нынешнем состоянии тебе не позволяют видеть Наталью, остается только одно — уйти...", OFFICE_STABLE_EXIT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_DENIED_ENTRY"));
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				if(index==1) {
					return new Response("Интервью", "Принять предложение Натальи об интервью.", OFFICE_STABLE_INTERVIEW_1) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewOffered, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED));
						}
					};
					
				} else if(index==2) {
					return new Response("Отказаться", "Отказаться от интервью с Натальей.<br/>[style.italicsTerrible(Это провалит романтический квест Натальи!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_TERRIBLE;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
							Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
							Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_0"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
						}
					};
				}
				
			} else {
				if(!isPlayerBodyCorrect()) {
					if(index==1) {
						return new Response("Выпить зелье", "Выпей зелье и превратись в [style.a_shemale].", OFFICE_STABLE_ENTRY_TRANSFORMED){
							@Override
							public Colour getHighlightColour() {
								return PresetColour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								String transformationText = applyTransformation();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START"));
								Main.game.getTextStartStringBuilder().append(transformationText);
								
								if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-25));
								}
							}
						};
					}
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
						if(index==1) {
							return new Response("Встать на колени", "Сделай, как велит госпожа Наталья, и [pc.step] к ней, чтобы опуститься на колени рядом...", OFFICE_STABLE_TRAINING_2);
						}
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
						if(index==1) {
							return new Response("Встать на колени", "Сделай, как велит госпожа Наталья, и [pc.step] к ней, чтобы опуститься на колени рядом...", OFFICE_STABLE_TRAINING_3);
						}
							
					} else { // Qualified mule:
						AbstractClothing collar = getPlayerCollar();
						
						if(index==0) {
							return new Response("Уйти", "Скажи Наталье, что вернешься к своим обязанностям, и уходи...", OFFICE_STABLE_EXIT_NO_CONTENT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_FILLY_LEAVE"));
									Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
								}
							};
							
						// Silver:
						} else if(index==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("Сосать член", "Ты уже обслуживал Наталью сегодня, поэтому на тебя у неё больше нет времени...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Сосать член",
										"Твой ранг [style.mule] слишком низок для этого!<br/>[style.italics(Требуется ранг не ниже «серебряного», а у тебя всего «"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return new Response("Сосать член", "Так как ты не можешь дотянуться до своего рта, ты не можешь сосать член госпожи Натальи!", null);
								
							} else {
								return new Response("Сосать член", "Скажи Наталье, что хотел бы воспользоваться преимуществом своего [style.mule] ранга и отсосать ей член.", OFFICE_STABLE_FILLY_GIVE_BLOWJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==2) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("Сделать римджоб", "Ты уже обслуживал Наталью сегодня, поэтому на тебя у неё больше нет времени...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Сделать римджоб",
										"Твой ранг [style.mule] слишком низок для этого!<br/>[style.italics(Требуется ранг не ниже «серебряного», а у тебя всего «"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return new Response("Сделать римджоб", "Так как вы не можете дотянуться до своего рта, вы не можете сделать госпоже Наталье римджоб!", null);
								
							} else {
								return new Response("Сделать римджоб", "Сказать Наталье, что вы хотели бы воспользоваться преимуществом своего [style.mule] ранга и отсосать ей анус.", OFFICE_STABLE_FILLY_GIVE_RIMJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==3) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("Сесть сверху", "Ты уже обслуживал Наталью сегодня, поэтому на тебя у неё больше нет времени...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Сесть сверху",
										"Твой ранг [style.mule] слишком низок для этого!<br/>[style.italics(Требуется ранг не ниже «серебряного», а у тебя всего «"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
								return new Response("Сесть сверху", "Так как вы не можете открыть доступ к своему анусу, Наталья не может сесть на вас!", null);
								
							} else {
								return new Response("Сесть сверху", "Скажи Наталье, что хочешь воспользоваться преимуществом своего ранга [style.mule] и чтобы она оседлала тебя.", OFFICE_STABLE_FILLY_GET_MOUNTED) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						// Gold:
						} else if(index==6) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("Получить минет", "Ты уже доминировал над госпожой Натальей сегодня, поэтому она не позволит тебе повторить это до завтра...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Получить минет",
										"У тебя недостаточно высокий ранг [style.mule] для этого!<br/>[style.italics(Требуется ранг не ниже «золотого», а у тебя всего «"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return new Response("Получить минет", "Так как ты не можешь получить доступ к своему члену, тебе не удается заставить госпожу Наталью сосать твой член!", null);
								
							} else {
								return new Response("Получить минет", "Прикажи госпоже Наталье опуститься на колени и сосать твой член.", OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==7) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("Получить римджоб", "Ты уже доминировал над госпожой Натальей сегодня, поэтому она не позволит тебе повторить это до завтра...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Получить римджоб",
										"У тебя недостаточно высокий ранг [style.mule] для этого!<br/>[style.italics(Требуется ранг не ниже «золотого», а у тебя всего «"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
								return new Response("Получить римджоб", "Так как у тебя нет доступа к своей заднице, ты не можешь заставить госпожу Наталью сделать тебе римджоб!", null);
								
							} else {
								return new Response("Получить римджоб", "Приказать госпоже Наталье опуститься на колени и отсосать твою задницу.", OFFICE_STABLE_FILLY_RECEIVE_RIMJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==8) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("Оседлать её", "Ты уже доминировал над госпожой Натальей сегодня, поэтому она не позволит тебе повторить это до завтра...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Оседлать её",
										"У тебя недостаточно высокий ранг [style.mule] для этого!<br/>[style.italics(Требуется ранг не ниже «золотого», а у тебя всего «"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return new Response("Оседлать её", "Так как у тебя нет доступа к члену, ты не можешь оседлать Наталью!", null);
								
							} else {
								return new Response("Оседлать её", "Приказать госпоже Наталье подставить тебе свою задницу, чтобы ты мог оседлать её и начать анальный трах.", OFFICE_STABLE_FILLY_MOUNT_HER) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
						}
						
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_ENTRY_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START_TRAINING_2_END");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START_TRAINING_3_END");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Раздеться и встать на колени", "Сделать так, как велит Наталья: раздеться догола и опуститься перед ней на колени.", OFFICE_STABLE_INTERVIEW_2) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться", "Отказаться преклонить колени перед Натальей.<br/>[style.italicsTerrible(Это провалит романтический квест Натальи!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_1"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_2 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAnyEquippedClothingSealed()) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_STRIP_SEALED"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_STRIP"));
			}
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_END"));
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поднять юбку", "Сделать так, как велит Наталья, и поднять её юбку...", OFFICE_STABLE_INTERVIEW_3) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться", "Отказаться поднимать юбку Натальи.<br/>[style.italicsTerrible(Это провалит романтический квест Натальи!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_2"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_3 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_3");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Неохотный поцелуй",
						"Нерешительно поцеловать анус Натальи, а затем приняться сосать её член.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_RELUCTANT_KISS")
						+ UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Страстный поцелуй",
						"С жадностью ласкать языком анус Натальи, а затем приняться сосать её член.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_EAGER_KISS")
						+ UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3) {
				return new Response("Отказаться", "Отказаться целовать анус Натальи.<br/>[style.italicsTerrible(Это провалит романтический квест Натальи!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_3"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_FULL_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_AFTER_SEX = new DialogueNode("Завершено", "Наталья довольна твоими стараниями.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Чистый член", "Почистить член Натальи, пока она готовит ваш контракт.", OFFICE_STABLE_CONTRACT_OFFERED) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).replaceAllClothing();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_OFFERED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED"));
			if(isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_NO_TF"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_TF"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подписать", "Сделать так, как говорит Наталья, и подписать контракт.", OFFICE_STABLE_CONTRACT_SIGNED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_3_TRAINING_1));
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться", "Отказаться подписывать контракт.<br/>[style.italicsTerrible(Это провалит романтический квест Натальи!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_CONTRACT);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_REFUSED"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_SIGNED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isPlayerBodyCorrect()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_NO_TF");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isPlayerBodyCorrect()) {
				return OFFICE_STABLE_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Выпить зелье", "Выпить зелье и превратиться в одну из [style.shemale] [style.mules] госпожи Натальи.", OFFICE_STABLE_TRANSFORMED){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							String transformationText = applyTransformation();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_TRANSFORMED"));
							Main.game.getTextStartStringBuilder().append(transformationText);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRANSFORMED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Кентавр", "Сказать Наталье, что вы бы предпочли обслуживать кентавра.", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
					@Override
					public void effects() {
						applySadistSlave(spawnSlave(false, PresetColour.CLOTHING_BRONZE));
					}
				};
				
			} else if(index==2) {
				return new Response("Кентавридка", "Сказать Наталье, что вы бы предпочли обслуживать кентавру.", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
					@Override
					public void effects() {
						applySadistSlave(spawnSlave(true, PresetColour.CLOTHING_BRONZE));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Неохотный поцелуй", UtilText.parse(getSadistSlave(), "Нерешительно поцеловать анус [npc.namePos]."), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_RELUCTANT_KISS", getSadistSlave()));
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.TWO_NEUTRAL, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Страстный поцелуй", UtilText.parse(getSadistSlave(), "Жадно поцеловать анус [npc.namePos]."), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_EAGER_KISS", getSadistSlave()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE, true));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_1_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Лизать член",
						UtilText.parse(getSadistSlave(), "Исполнить приказ госпожи Натальи и опуститься на колени, чтобы облизать дикий лошадиный член [npc.namePos]."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_1_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_SEX_SUCK_COCK")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), getSadistSlave(), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE, true));
						}
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE, true));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlave(getSadistSlave(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_4_TRAINING_2));
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getSadistSlave(), "[npc.Name] закончил с тобой и вытаскивает свой [npc.her] член из твоего измученного горла.");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Повиноваться", UtilText.parse(getSadistSlave(), "Скажи госпоже Наталье, что ты похотливая [style.mule], которая обожает лошадиные задницы и лошадиные члены, прежде чем последовать за [npc.name] из кабинета..."), OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2"));
			if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_LIFT_SKIRT"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToWearMakeup()) {
				if(index==1) {
					return new Response("Матовый темно-синий", "Выбери матовую темно-синюю помаду.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_DARK_BLUE"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.MATTE, PresetColour.COVERING_BLUE_DARK, false, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
					
				} else if(index==2) {
					return new Response("Сверкающий золотой", "Выбери сверкающую золотую помаду.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_SPARKLY_GOLD"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.SPARKLY, PresetColour.COVERING_GOLD, false, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
					
				} else if(index==3) {
					return new Response("Сияющий розовый", "Выбери сияющую розовую помаду.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_GLOWING_PINK"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.GLOSSY, PresetColour.COVERING_PINK, true, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
				}
				return null;
				
			} else {
				return OFFICE_STABLE_TRAINING_2_MAKEUP.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_LIFT_SKIRT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Неуверенные поцелуи", "Нерешительно начни целовать анус Натальи.", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_RELUCTANT_KISSES", getSadistSlave()));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						}
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ORAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Жадные поцелуи", "С жадностью начать целовать анус Натальи.", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_EAGER_KISSES", getSadistSlave()));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						}
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ORAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Жадный анилингус",
						"Начать ласкать анус Натальи языком.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_2_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_AFTER_SEX = new DialogueNode("Завершено", "Госпожа Наталья довольна твоими стараниями...", true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_5_TRAINING_3));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "У Натальи сегодня больше нет на тебя времени, так что тебе придется вернуться завтра, чтобы продолжить обучение.", OFFICE_STABLE_EXIT_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(UtilText.parse(getSadistSlave(), "[npc.Name]"),
						UtilText.parse(getSadistSlave(), "Госпожа Наталья спешит привести [npc.name], чтобы ты мог заняться анилингусом с [npc.herHim], пока она трахает твой зад."),
						OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY) {
					@Override
					public void effects() {
						getSadistSlave().setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Неохотно умолять", "Нерешительно сказать Наталье, что ты хочешь, чтобы она тебя оттрахала.", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY", getSadistSlave()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY_END", getSadistSlave()));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Жадно умолять", "Жадно умолять госпожу Наталью начать трахать тебя.", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_EAGERLY_OBEY", getSadistSlave()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY_END", getSadistSlave()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						}
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_FUCKED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Податься назад",
						UtilText.parse(getSadistSlave(), "Отталкиваться назад и насаживаться на член Натальи, одновременно занимаясь анилингусом с [npc.name]..."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND),
										new Value<>(getSadistSlave(), SexSlotAllFours.IN_FRONT_ANAL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
										new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.PRECUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_FUCKED_START", getSadistSlave())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getSadistSlave(), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX = new DialogueNode("Завершено", "Госпожа Наталья получает свое удовольствие и вынимает свой член из твоей изрядно оттраханной задницы...", true) {
		@Override
		public void applyPreParsingEffects() {
			getSadistSlave().fillCumToMaxStorage();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Раунд второй",
						UtilText.parse(getSadistSlave(), "Подставить свою задницу [npc.name], чтобы [npc.she] могла оседлать тебя и трахать в анус..."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								
								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA, LubricationType.PRECUM, LubricationType.CUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_ROUND_TWO", getSadistSlave())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getSadistSlave(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlave(getSadistSlave(), false);
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getSadistSlave(), "Внеся свой вклад в раздолбанное и забитое спермой состояние твоего ануса, [npc.name] отстраняется и вынимает член [npc.her] из твоей задницы.");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(index==1) {
					return new Response("Чокер [style.Mule]", "Позволить Наталье застегнуть твой новый чокер на шее...", OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(generateCollar(), true, Main.game.getNpc(Natalya.class)));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(10));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString("", true);
			if(Main.game.getPlayer().getHoldingClothing().values().stream().anyMatch(c->c.getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar"))) {
				UtilText.addSpecialParsingString(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_AMBER_SPECIAL"), true);
			}
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Сделать то, что говорит Наталья, и выйти из её кабинета...", OFFICE_STABLE_TRAINING_3_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_LEAVE"));
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						if(Main.game.getPlayer().getHoldingClothing().containsKey(InventorySlot.NECK)) {
							Main.game.getPlayer().addClothing(Main.game.getPlayer().getHoldingClothing().get(InventorySlot.NECK), false);
						}
						Main.game.getPlayer().equipAllClothingFromHoldingInventory(Util.newArrayListOfValues(InventorySlot.NECK));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_EXIT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GIVE_BLOWJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Поцеловать анус",
						"Поцеловать анус Натальи, а затем приняться сосать её член.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GIVE_RIMJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_RIMJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Анилингус",
						"Поцелуй анус Натальи, а затем начни ласкать его языком, занимаясь анилингусом.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_RIMJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GET_MOUNTED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GET_MOUNTED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Поцеловать анус",
						"Поцелуй анус Натальи, а затем подставься, чтобы она оседлала тебя.",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GET_MOUNTED_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Доминировать над ней",
						"Доминировать над госпожой Натальей, заталкивая свой член ей в глотку.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_FILLY_RECEIVE_RIMJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_RIMJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Доминировать над ней",
						"Доминировать над госпожой Натальей, толкая таз назад и накрывая её лицо своим [pc.asshole+].",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_RIMJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_MOUNT_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Доминировать над ней",
						"Доминировать над госпожой Натальей, вгоняя свой [pc.cock+] в её [natalya.asshole+].",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB = new DialogueNode("Завершено", "Госпожа Наталья удовлетворена, и на этом ваше веселье подходит к концу...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_RIMJOB");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_MOUNTED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM = new DialogueNode("Завершено", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_RIMJOB");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_MOUNTED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
}
