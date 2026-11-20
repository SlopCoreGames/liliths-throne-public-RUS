package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMEatenOut;
import com.lilithsthrone.game.sex.managers.submission.SMEatingOut;
import com.lilithsthrone.game.sex.managers.submission.SMShadowSilence;
import com.lilithsthrone.game.sex.managers.submission.SMVengarDominantSex;
import com.lilithsthrone.game.sex.managers.universal.SMAgainstWall;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.managers.universal.SMOverDesk;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class VengarCaptiveDialogue {

	private static Map<VengarCompanionBehaviour, Float> dailyCompanionBehaviours = new HashMap<>();
	private static VengarCompanionBehaviour currentCompanionBehaviour = VengarCompanionBehaviour.SERVING;
	
	public static void applyDailyReset() {
		dailyCompanionBehaviours = new HashMap<>();
		for(VengarCompanionBehaviour cb : VengarCompanionBehaviour.values()) {
			dailyCompanionBehaviours.put(cb, cb.getChanceOfOccurance());
		}
	}
	
	private static GameCharacter getMainCompanion() {
		if(Main.game.getPlayer().hasCompanions()) {
			return Main.game.getPlayer().getMainCompanion();
		}
		return null;
	}
	
	private static boolean isCompanionDialogue() {
		return getMainCompanion()!=null;
	}

	public static GameCharacter generateRat(Gender gender) {
		try {
			NPC rat = new RatGangMember(gender);
			Main.game.addNPC(rat, false);
			rat.setLevel(4+Util.random.nextInt(5));
			rat.setLocation(Main.game.getPlayer(), true);
			String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
			Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), null);
			return rat;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void banishGenericRats() {
		for(GameCharacter guard : getCharactersPresent(false)) {
			Main.game.banishNPC((NPC) guard);
		}
	}
	
	private static List<GameCharacter> getCharactersPresent(boolean includeCompanion) {
		List<GameCharacter> characters = new ArrayList<>();
		characters.addAll(Main.game.getCharactersPresent());
		characters.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof RatGangMember));
		Collections.sort(characters, (a, b)->a.hasPenis()?(b.hasPenis()?0:-1):(b.hasPenis()?1:0));
		if(Main.game.getPlayer().hasCompanions() && includeCompanion) {
			characters.add(0, Main.game.getPlayer().getMainCompanion());
		}
		return characters;
	}
	
	private static String applyFetishTransformation(GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		boolean tfApplied = false;
		
		if(!target.hasFetish(Fetish.FETISH_SUBMISSIVE) || !target.getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_SUBMISSIVE"));
			sb.append(target.addFetish(Fetish.FETISH_SUBMISSIVE, true));
			sb.append(target.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE, true));
			tfApplied = true;
		}

		if(!target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_TRANSFORMATION"));
			sb.append(target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			tfApplied = true;
		}

		if(!target.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive() || !target.getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_BEHAVIOUR_EXTRAS"));
			sb.append(target.setFetishDesire(Fetish.FETISH_EXHIBITIONIST, FetishDesire.THREE_LIKE, true));
			sb.append(target.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE, true));
			tfApplied = true;
		}

		if(!target.hasFetish(Fetish.FETISH_VAGINAL_RECEIVING) 
				|| !target.hasFetish(Fetish.FETISH_ORAL_GIVING) 
				|| (Main.game.isAnalContentEnabled() && !target.hasFetish(Fetish.FETISH_ANAL_RECEIVING))) {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_HOLES"));
			sb.append(target.addFetish(Fetish.FETISH_VAGINAL_RECEIVING, true));
			sb.append(target.addFetish(Fetish.FETISH_ORAL_GIVING, true));
			sb.append(target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			tfApplied = true;
		}

		if(!target.hasFetish(Fetish.FETISH_PENIS_RECEIVING) 
				|| !target.hasFetish(Fetish.FETISH_VAGINAL_GIVING) 
				|| (Main.game.isAnalContentEnabled() && !target.hasFetish(Fetish.FETISH_ANAL_GIVING))) {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_SERVICING_HOLES"));
			sb.append(target.addFetish(Fetish.FETISH_PENIS_RECEIVING, true));
			sb.append(target.addFetish(Fetish.FETISH_VAGINAL_GIVING, true));
			sb.append(target.addFetish(Fetish.FETISH_ANAL_GIVING, true));
			tfApplied = true;
		}
		
		if(!tfApplied) {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_FINISHED_NO_EFFECT"));
		} else {
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "APPLY_FETISH_FINISHED"));
		}
		
		return sb.toString();
	}
	
	private static String applyTransformation(GameCharacter target) { //TODO returning empty string
		StringBuilder sb = new StringBuilder();
		
		if(target.isAbleToHaveRaceTransformed()) {
			boolean genitalsSet = false;
			switch(Main.getProperties().getForcedTFPreference()) {
				case NORMAL:
				case MAXIMUM:
					// face, skin
					target.setTorsoType(TorsoType.RAT_MORPH);
					target.setFaceType(FaceType.RAT_MORPH);
				//$FALL-THROUGH$
				case REDUCED:
					// arms, legs, genitals
					target.setArmType(ArmType.RAT_MORPH);
					target.setLegType(LegType.RAT_MORPH);
					genitalsSet = true;
					target.setVaginaType(VaginaType.RAT_MORPH);
					target.setAssType(AssType.RAT_MORPH);
					target.setPenisType(PenisType.NONE);
					target.setBreastType(BreastType.RAT_MORPH);
					target.setBreastCrotchType(BreastType.RAT_MORPH);
				//$FALL-THROUGH$
				case MINIMUM:
					// everything else
					target.setAntennaType(AntennaType.NONE);
					target.setEarType(EarType.RAT_MORPH);
					target.setEyeType(EyeType.RAT_MORPH);
					target.setHairType(HairType.RAT_MORPH);
					target.setHornType(HornType.NONE);
					target.setTailType(TailType.RAT_MORPH);
					target.setTentacleType(TentacleType.NONE);
					target.setWingType(WingType.NONE);
				//$FALL-THROUGH$
				case HUMAN:
					if(!genitalsSet) {
						target.setVaginaType(VaginaType.HUMAN);
						target.setPenisType(PenisType.NONE);
					}
					break;
			}
		}

		// Core:
		target.setFemininity(Math.max(target.getFemininityValue(), 85));
		target.setHeight(168);
		target.setMuscle(Muscle.TWO_TONED.getMedianValue());
		target.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Face:
		target.setLipSize(Math.max(LipSize.THREE_PLUMP.getValue(), target.getLipSizeValue()));
		
		// Chest:
		target.setBreastSize(Math.max(CupSize.DD.getMeasurement(), target.getBreastSize().getMeasurement()));
		target.setNippleSize(Math.max(NippleSize.THREE_LARGE.getValue(), target.getNippleSize().getValue()));
		target.setAreolaeSize(Math.max(AreolaeSize.THREE_LARGE.getValue(), target.getAreolaeSize().getValue()));
		target.removeMilkModifier(FluidModifier.ADDICTIVE);
		
		// Ass:
		target.setAssSize(Math.max(AssSize.FOUR_LARGE.getValue(), target.getAssSize().getValue()));
		target.setHipSize(Math.max(HipSize.FOUR_WOMANLY.getValue(), target.getHipSize().getValue()));
		
		// Penis:
		// No penis
		
		// Vagina:
		target.setVaginaLabiaSize(Math.max(LabiaSize.THREE_LARGE.getValue(), target.getVaginaRawLabiaSizeValue()));
		target.setVaginaSquirter(true);
		target.setVaginaWetness(Math.max(Wetness.FIVE_SLOPPY.getValue(), target.getVaginaWetness().getValue()));
		target.setVaginaElasticity(Math.max(OrificeElasticity.THREE_FLEXIBLE.getValue(), target.getVaginaElasticity().getValue()));
		target.setVaginaPlasticity(Math.max(OrificePlasticity.FIVE_YIELDING.getValue(), target.getVaginaPlasticity().getValue()));
		target.removeGirlcumModifier(FluidModifier.ADDICTIVE);
		
		return sb.toString();
	}
	
	private static void applyTattoo(GameCharacter target, String text) {
		Tattoo tattoo = new Tattoo("innoxia_misc_none", PresetColour.CLOTHING_GREY, null, null, false, new TattooWriting(text, PresetColour.BASE_PINK, false), null);
		tattoo.setName("'"+text+"' татуировка");
		target.addTattoo(InventorySlot.GROIN, tattoo);
	}
	
	public static final DialogueNode FINAL_COMBAT_DEFEAT_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "FINAL_COMBAT_DEFEAT_STRIPPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						"Проглотить",
						"Тень зажимает тебе нос и держит рот закрытым, так что тебе остается только проглатывать жидкость, наполняющую твой рот...",
						FINAL_COMBAT_DEFEAT_FETISH_APPLICATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "FINAL_COMBAT_DEFEAT_FETISH_APPLICATION"));
						Main.game.getTextStartStringBuilder().append(applyFetishTransformation(Main.game.getPlayer()));
						if(isCompanionDialogue()) {
							applyFetishTransformation(getMainCompanion());
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "FINAL_COMBAT_DEFEAT_FETISH_APPLICATION_END"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode FINAL_COMBAT_DEFEAT_FETISH_APPLICATION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				List<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
				CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
				return new Response(
						"Повиноваться",
						"Из-за зелья, которое тебя только что заставили выпить, ты теперь так отчаянно жаждешь подчиниться и преобразиться, что не можешь сделать ничего, кроме того, что прикажет Тень...",
						START_VENGAR_PUBLIC_FUCK,
						applicableFetishes,
						applicableCorruptionLevel,
						null,
						null,
						null) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().isAbleToHaveRaceTransformed()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "TF_SELF_TF"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "TF_SWALLOW"));
						}
						Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer()));
						
						if(isCompanionDialogue()) {
							applyTransformation(getMainCompanion());
							if(!getMainCompanion().isAbleToHaveRaceTransformed()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "TF_COMPANION_SELF_TF"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "TF_COMPANION_SWALLOW"));
							}
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode START_VENGAR_PUBLIC_FUCK = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLust(50);
			if(isCompanionDialogue()) {
				getMainCompanion().setLust(50);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_BOTH");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_SOLO");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if(index==1) {
					return new ResponseSex(
							"Предстать перед ней",
							"Начать вилять задом и подставить задницу Венгару, предлагая ему трахнуть тебя на глазах у всех в зале.",
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND),
											new Value<>(Main.game.getNpc(Shadow.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(Silence.class), SexSlotAllFours.IN_FRONT_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
							null,
							null,
							START_VENGAR_PUBLIC_FUCK_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex("Трахнут первым",
							"Прижми свой [pc.hips] к Венгару, давая ему понять, что хочешь, чтобы он трахнул тебя первым. В результате Венгар будет трахать тебя, пока ты будешь ласкать языком Тень, а [npc.name] будет ласкать Сайленс.",
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND),
											new Value<>(Main.game.getNpc(Shadow.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(Silence.class), SexSlotAllFours.IN_FRONT_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS_TWO))),
							null,
							null,
							START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_FIRST", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), TongueVagina.CUNNILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getNpc(Silence.class), TongueVagina.CUNNILINGUS_START, false, true));
								
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Трахнут вторым",
							UtilText.parse(getMainCompanion(), 
								"Слегка отползи от Венгара, давая ему понять, что хочешь, чтобы он первым трахнул [npc.name]. В результате ты будешь ласкать языком Сайленс, пока [npc.name] будут использовать и Венгар, и Тень."),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND),
											new Value<>(Main.game.getNpc(Shadow.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(Silence.class), SexSlotAllFours.IN_FRONT_TWO)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS_TWO))),
							null,
							null,
							START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_SECOND", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();

							list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getNpc(Shadow.class), TongueVagina.CUNNILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
							
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Silence.class), TongueVagina.CUNNILINGUS_START, false, true));
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE_PLAYER_NEXT", getCharactersPresent(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE_COMPANION_NEXT", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> previousWetAreas = new HashMap<>(Main.sex.getAllWetAreas()); // Starting lube from saliva
			
			if(index==1) {
				if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
					return new ResponseSex("Трахнутый",
							UtilText.parse(getMainCompanion(), "Поползай вокруг и начни слизывать сперму из киски [npc.namePos], готовясь к тому, что следующим тебя будет трахать Венгар."),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
											new Value<>(getMainCompanion(), SexSlotAllFours.IN_FRONT))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									return previousWetAreas;
								}
							},
							null,
							null,
							START_VENGAR_PUBLIC_FUCK_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE_PLAYER_START", getCharactersPresent(true))) {
						@Override
						public void effects() {
							Main.game.getNpc(Shadow.class).calculateGenericSexEffects(
									true, true, null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.PREVENT_CREAMPIE);
							Main.game.getNpc(Silence.class).calculateGenericSexEffects(
									true, true, null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.PREVENT_CREAMPIE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
							if(!list.isEmpty()) {
								return list;
							}
							return super.getInitialSexActions();
						}
					};
					
				} else {
					return new ResponseSex(
							"Оральная чистка",
							UtilText.parse(getMainCompanion(), "Заставьте [npc.name] начать слизывать сперму из вашей киски, пока Венгар трахает [npc.herHim]."),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.IN_FRONT))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									return previousWetAreas;
								}
							},
							null,
							null,
							START_VENGAR_PUBLIC_FUCK_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_DOUBLE_COMPANION_START", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
							list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							if(!list.isEmpty()) {
								return list;
							}
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode START_VENGAR_PUBLIC_FUCK_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать",
						"Ждать возвращения Сайленс.",
						START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED);
			}
			return null;
		}
	};
	
	public static final DialogueNode START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<String> tattooNames = Util.newArrayListOfValues("Общественная спермобак", "Общественная залупа", "Свободная трахалка", "Свободное использование");
			
			if(index==1) {
				return new Response("Затаиться",
						"Молчать и позволить Венгару выбрать за тебя...<br/><i>Это выберет одну из четырех других опций случайным образом.</i>",
						START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED_END) {
					@Override
					public void effects() {
						String name = tattooNames.get(Util.random.nextInt(tattooNames.size()));
						applyTattoo(Main.game.getPlayer(), name);
						UtilText.addSpecialParsingString(name, true);
						
						if(isCompanionDialogue()) {
							List<String> companionTattooNames = new ArrayList<>(tattooNames);
							companionTattooNames.remove(name);
							String companionTattooName = companionTattooNames.get(Util.random.nextInt(companionTattooNames.size()));
							applyTattoo(getMainCompanion(), companionTattooName);
							UtilText.addSpecialParsingString(companionTattooName, false);
						}
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED_STAY_QUIET", getCharactersPresent(true)));
					}
				};
				
			} else if(index>0 && index-2<tattooNames.size()) {
				String name = tattooNames.get(index-2);
				return new Response(name,
						"Сказать Венгару, что ты хочешь, чтобы на твоей татуировке было написано '"+name+"'...",
						START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED_END) {
					@Override
					public void effects() {
						applyTattoo(Main.game.getPlayer(), name);
						UtilText.addSpecialParsingString(name, true);
						
						if(isCompanionDialogue()) {
							List<String> companionTattooNames = new ArrayList<>(tattooNames);
							companionTattooNames.remove(name);
							String companionTattooName = companionTattooNames.get(Util.random.nextInt(companionTattooNames.size()));
							applyTattoo(getMainCompanion(), companionTattooName);
							UtilText.addSpecialParsingString(companionTattooName, false);
						}
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED_CHOOSE_TATTOO", getCharactersPresent(true)));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode START_VENGAR_PUBLIC_FUCK_AFTER_SEX_TATTOOED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Теперь ты вольна бродить между залом и спальней Венгара.",
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						applyDailyReset(); // So that Vengar, Shadow, and Silence are willing to fuck on the first day
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Слоняться по залу в ожидании, не попросят ли тебя о чем-нибудь.", Main.game.getDefaultDialogue(true, true)) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==2) {
				return new Response("Служить", "Начать разносить еду и напитки крысам, собравшимся в зале.", VENGARS_HALL_CHOOSE_SERVE);
				
			} else if(index==3) {
				return new Response("Флиртовать (крыс-парень)", "Подойти к одному из крыс-парней в зале и начать с ним флиртовать.", VENGARS_HALL_CHOOSE_FLIRT) {
					@Override
					public void effects() {
						generateRat(Gender.getGenderFromUserPreferences(Femininity.MASCULINE));
					}
				};
				
			} else if(index==4) {
				return new Response("Флиртовать (крыса-девушка)", "Подойти к одной из крыс-девушек в зале и начать с ней флиртовать.", VENGARS_HALL_CHOOSE_FLIRT) {
					@Override
					public void effects() {
						generateRat(Gender.getGenderFromUserPreferences(Femininity.FEMININE));
					}
				};
				
			} else if(index==5) {
				return new Response("Флиртовать (Группой)", "Подойти к группе крыс в коридоре и начать с ними флиртовать.", VENGARS_HALL_CHOOSE_FLIRT) {
					@Override
					public void effects() {
						generateRat(Gender.getGenderFromUserPreferences(false, true));
						generateRat(Gender.getGenderFromUserPreferences(false, true));
						generateRat(Gender.getGenderFromUserPreferences(false, false));
						generateRat(Gender.getGenderFromUserPreferences(false, false));
						generateRat(Gender.getGenderFromUserPreferences(false, false));
					}
				};
				
			} else if(index==6) {
				return new Response("Венгар", "Решить какое-то время держаться поближе к Венгару.", VENGARS_HALL_CHOOSE_VENGAR);
				
			} else if(index==7) {
				return new Response("Тень", "Решить какое-то время держаться поближе к Шэдоу.", VENGARS_HALL_CHOOSE_SHADOW);
				
			} else if(index==8) {
				return new Response("Сайленс", "Решить какое-то время держаться поближе к Сайленс.", VENGARS_HALL_CHOOSE_SILENCE);
				
			}  else if(index==9 && isCompanionDialogue()) {
				return new Response(UtilText.parse(getMainCompanion(), "[npc.Name]"), UtilText.parse(getMainCompanion(), "Решить разыскать своего спутника, [npc.name], и посмотреть, чем занят(а) [npc.she]."), VENGARS_HALL_CHOOSE_COMPANION) {
					@Override
					public void effects() {
						currentCompanionBehaviour = Util.getRandomObjectFromWeightedFloatMap(dailyCompanionBehaviours);
						if(currentCompanionBehaviour.isDailyLimited()) {
							dailyCompanionBehaviours.remove(currentCompanionBehaviour);
						}
						switch(currentCompanionBehaviour) {
							case FLIRTING:
							case FUCKING:
								Gender gender = Gender.getGenderFromUserPreferences(false, false);
								// Need penis or vagina:
								if(!gender.getGenderName().isHasPenis() && !gender.getGenderName().isHasVagina()) {
									if(gender.isFeminine()) {
										gender = Gender.getGenderFromUserPreferences(true, false);
									} else {
										gender = Gender.getGenderFromUserPreferences(false, true);
									}
								}
								generateRat(gender); 
								break;
							case GROUP_SEX:
								generateRat(Gender.getGenderFromUserPreferences(false, true));
								generateRat(Gender.getGenderFromUserPreferences(false, true));
								generateRat(Gender.getGenderFromUserPreferences(true, false));
								generateRat(Gender.getGenderFromUserPreferences(true, false));
								break;
							case SERVING:
								break;
							case SHADOW:
								break;
							case SILENCE:
								break;
							case VENGAR:
								break;
						}
					}
				};
				
			} 
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_SERVE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SERVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return VENGARS_HALL_SERVE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_FLIRT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_FLIRT", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return VENGARS_HALL_GROPED.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_VENGAR = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_VENGAR_FUCK");
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_VENGAR_KNEEL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied)) {
				if(index==1) {
					return new Response("Повиноваться", "Упасть на колени перед Венгаром и вытащить его член.", VENGARS_HALL_CHOOSE_VENGAR_HANDJOB) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setLust(Math.max(Main.game.getPlayer().getLust(), 50));
							Main.game.getNpc(Vengar.class).setLust(Math.max(Main.game.getPlayer().getLust(), 50));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Встать на колени", "Исполнить приказ Венгара и покорно опуститься на колени рядом с ним.", VENGARS_HALL_CHOOSE_VENGAR_KNEEL);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_VENGAR_HANDJOB = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_VENGAR_HANDJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Сесть",
						"Исполнить приказ Венгара и сесть к нему на колени.",
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.SITTING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))),
						null,
						null,
						VENGARS_HALL_VENGAR_FUCK_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_VENGAR_SITTING_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_VENGAR_FUCK_AFTER_SEX = new DialogueNode("Завершено", "Венгар на время закончил с тобой.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_VENGAR_FUCK_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отпустить",
						"Исполнить приказ Венгара и вернуться вниз, в зал.",
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_VENGAR_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_VENGAR_KNEEL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подставка для ног", "Исполнить приказ Венгара и встать перед ним на четвереньки, чтобы он мог использовать тебя как подставку для ног.", VENGARS_HALL_CHOOSE_VENGAR_FOOTREST);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_VENGAR_FOOTREST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_VENGAR_FOOTREST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Исполнить приказ Венгара и вернуться вниз, в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_SHADOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveShadowSatisfied)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SHADOW_DISMISSED");
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SHADOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveShadowSatisfied)) {
				if(index==1) {
					return new Response("Поднять зад", "У тебя почти нет выбора, кроме как подчиниться приказу Шэдоу...", VENGARS_HALL_CHOOSE_SHADOW_SPANKING) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setLust(Math.max(Main.game.getPlayer().getLust(), 50));
							Main.game.getNpc(Shadow.class).setLust(Math.max(Main.game.getPlayer().getLust(), 50));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Продолжить", "Исполнить приказ Шэдоу и оставить её в покое, отправившись обратно в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
				}
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_SHADOW_SPANKING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SHADOW_SPANKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Отсосали",
						"Шэдоу начинает отчаянно ласкать тебя языком.",
						true,
						false,
						new SMEatenOut(
								SexPosition.OVER_DESK,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Shadow.class), SexSlotDesk.PERFORMING_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
						null,
						null,
						AFTER_SHADOW_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SHADOW_SPANKING_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SHADOW_ORAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_SHADOW_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Исполнить приказ Шэдоу и вернуться вниз, в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_SILENCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveSilenceSatisfied)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SILENCE_DISMISSED");
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SILENCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveSilenceSatisfied)) {
				if(index==1) {
					return new ResponseSex(
							"Куннилингус",
							"Встань на колени перед Сайленс и начни отсасывать ей...",
							true,
							false,
							new SMEatingOut(
									SexPosition.AGAINST_WALL,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Silence.class), SexSlotAgainstWall.BACK_TO_WALL)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.PERFORMING_ORAL_WALL))),
							null,
							null,
							AFTER_SILENCE_ORAL,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_SILENCE_START_SEX")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Silence.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					};
				
				} else if(index==2) {
					return new Response("Уйти", "Решить не делать минет Сайленс и вместо этого вернуться в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
					
				}
				
			} else {
				if(index==1) {
					return new Response("Продолжить", "Поскольку Сайленс, кажется, не желает никак реагировать на твое присутствие, остается только вернуться в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SILENCE_ORAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_SILENCE_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Похоже, Сайленс сейчас не настроена на что-то еще с тобой, так что у тебя почти нет выбора, кроме как вернуться в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START", getCharactersPresent(true)));
			
			switch(currentCompanionBehaviour) {
				case FLIRTING:
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_FLIRTING", getCharactersPresent(true)));
					break;
				case FUCKING:
					if(getCharactersPresent(true).get(1).hasPenis()) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_FUCKING_PENIS", getCharactersPresent(true)));
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_FUCKING_VAGINA", getCharactersPresent(true)));
					}
					break;
				case GROUP_SEX:
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_GROUP_SEX", getCharactersPresent(true)));
					break;
				case SERVING:
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_SERVING", getCharactersPresent(true)));
					break;
				case SHADOW:
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_SHADOW", getCharactersPresent(true)));
					break;
				case SILENCE:
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_SILENCE", getCharactersPresent(true)));
					break;
				case VENGAR:
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_START_VENGAR", getCharactersPresent(true)));
					break;
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", UtilText.parse(getMainCompanion(), "Оставить [npc.name] одну и вернуться в зал."), Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			switch(currentCompanionBehaviour) {
				case FLIRTING:
					if(index==2) {
						return new Response("Присоединиться",
								UtilText.parse(getCharactersPresent(true), "Присоединиться к [npc.name] в флирте с [npc2.name]."),
								VENGARS_HALL_CHOOSE_COMPANION_FLIRTING);
					}
					break;
				case FUCKING:
					if(index==2) {
						return new Response("Подойти",
								UtilText.parse(getCharactersPresent(true), "Подойти к [npc.name] и поближе рассмотреть [npc.herHim], пока [npc.sheIs] трахает [npc2.name]."),
								VENGARS_HALL_CHOOSE_COMPANION_FUCKING); //TODO need companion to get creampied if NPC has penis
					}
					break;
				case GROUP_SEX:
					if(index==2) {
						return new Response("Подойти ближе",
								UtilText.parse(getCharactersPresent(true), "Подойди к [npc.name] и поближе рассмотри [npc.herHim], пока [npc.sheIs] трахает группа крыс."),
								VENGARS_HALL_CHOOSE_COMPANION_GROUP_SEX);
					}
					break;
				case SERVING:
					if(index==2) {
						return new Response("Помочь",
								UtilText.parse(getMainCompanion(), "Помочь [npc.name] разносить еду и напитки по столам в зале."),
								VENGARS_HALL_CHOOSE_COMPANION_SERVING);
					}
					break;
				case SHADOW:
					if(index==2) {
						return new Response("Возразить",
								UtilText.parse(getMainCompanion(), "Возразить против сурового обращения Шэдоу с [npc.name]."),
								VENGARS_HALL_CHOOSE_COMPANION_SHADOW);
					}
					break;
				case SILENCE:
					if(index==2) {
						return new Response("Смотреть",
								UtilText.parse(getMainCompanion(), "Присоединиться к [npc.name] и наблюдать, как Сайленс властно оседлала крысопарня."),
								VENGARS_HALL_CHOOSE_COMPANION_SILENCE) {
							@Override
							public void effects() {
								//TODO Test Silence gains creampie
								Main.game.getNpc(Silence.class).calculateGenericSexEffects(
										true, true, null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
								Main.game.getNpc(Silence.class).ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.VAGINA, 400);
							}
						};
					}
					break;
				case VENGAR:
					if(index==2) {
						return new Response("Задержаться",
								UtilText.parse(getMainCompanion(), "Потереться поблизости от Венгара, чтобы проверить, не собираются ли скоро отпустить [npc.name]."),
								VENGARS_HALL_CHOOSE_COMPANION_VENGAR);
					}
					break;
			}

			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_FLIRTING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FLIRTING", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ощупывание",
						UtilText.parse(getCharactersPresent(true), "Просто позволить [npc2.name] лапать тебя и [npc.name], не давая [npc2.herHim] зайти слишком далеко."),
						VENGARS_HALL_CHOOSE_COMPANION_FLIRTING_FINISH);
				
			} else if(index==2 && (getCharactersPresent(true).get(1).hasPenis() || getCharactersPresent(true).get(1).hasVagina())) {
				return new ResponseSex("Зайти дальше",
						UtilText.parse(getCharactersPresent(true), "Заставить [npc.name] заняться минет [npc2.name] вместе с тобой."),
						true,
						false,
						new SMSitting(
								Util.newHashMapOfValues(
										new Value<>(getCharactersPresent(true).get(1), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL),
										new Value<>(getCharactersPresent(true).get(0), SexSlotSitting.PERFORMING_ORAL_TWO))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								if(getCharactersPresent(true).get(1).hasPenis()) {
									map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.PENIS));
								} else {
									map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.VAGINA));
								}
								return map;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(getCharactersPresent(true).get(1))) {
									if(character.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
										
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getForeplayPreference(character, targetedCharacter);
							}
						},
						null,
						null,
						AFTER_COMPANION_FLIRTING_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FLIRTING_ORAL", getCharactersPresent(true)));
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMPANION_FLIRTING_ORAL = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharactersPresent(true), "[npc2.Name] закончила с тобой и [npc.name]."); 
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_COMPANION_FLIRTING_ORAL", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Насладившись [npc2.her], [npc2.name] оставляет вас с [npc.name], чтобы вы могли продолжить свои дела в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_FLIRTING_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FLIRTING_FINISH", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Насладившись [npc2.her], [npc2.name] оставляет вас с [npc.name], чтобы вы могли продолжить свои дела в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())){
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_FUCKING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(getCharactersPresent(true).get(1).hasPenis()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FUCKING_PENIS", getCharactersPresent(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FUCKING_VAGINA", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getCharactersPresent(true).get(1).hasPenis()) {
				if(index==1) {
					return new Response("Уйти",
							UtilText.parse(getCharactersPresent(true), "Решить не связываться с [npc.name] и [npc2.name]..."),
							Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
					
				} else if(index==2) {
					return new ResponseSex("Происходит",
							UtilText.parse(getCharactersPresent(true), "Дождаться, пока [npc2.sheIs] закончит, а затем занять место [npc.namePos] и получить оттраханной."),
							true,
							false,
							new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(true).get(1), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(true).get(0), SexSlotLyingDown.FACE_SITTING_REVERSE),
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.PENIS));
									map.put(getCharactersPresent(true).get(0), Util.newArrayListOfValues(CoverableArea.VAGINA));
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(true).get(1))) {
										if(targetedCharacter.isPlayer()) {
											if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
											}
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										}
										
									} else if(character.equals(getMainCompanion())) {
										if(targetedCharacter.isPlayer()) {
											if(getCharactersPresent(true).get(1).hasFetish(Fetish.FETISH_ANAL_GIVING)) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											}
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
										
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return character.getForeplayPreference(targetedCharacter);
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							AFTER_COMPANION_FUCKING,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FUCKING_GET_FUCKED", getCharactersPresent(true))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
							
							if(getCharactersPresent(true).get(1).hasFetish(Fetish.FETISH_ANAL_GIVING)) {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
								initialActions.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							} else {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								initialActions.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
							
							return initialActions;
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Уйти",
							UtilText.parse(getCharactersPresent(true), "Решить не связываться с [npc.name] и [npc2.name]..."),
							Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
					
				} else if(index==2) {
					return new ResponseSex("Происходит",
							UtilText.parse(getCharactersPresent(true), "Занять место [npc.namePos], вылизывая [npc2.name]."),
							true,
							false,
							new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(true).get(1), SexSlotLyingDown.FACE_SITTING_REVERSE)),
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(true).get(0), SexSlotLyingDown.MISSIONARY_ORAL),
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.VAGINA));
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(true).get(1))) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
										
									} else if(character.equals(getMainCompanion())) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
										}
										
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return character.getForeplayPreference(targetedCharacter);
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							AFTER_COMPANION_FUCKING,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FUCKING_PERFORM_CUNNILINGUS", getCharactersPresent(true))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
							
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							initialActions.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							
							return initialActions;
						}
					};
					
				} else if(index==3 && Main.game.isAnalContentEnabled()) {
					return new ResponseSex("Анилингус",
							UtilText.parse(getCharactersPresent(true), "Опуститься позади [npc2.name] и начать анилингус [npc2.herHim], пока [npc.name] продолжает обслуживать киску [npc2.her]."),
							true,
							false,
							new SMStanding(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(true).get(1), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(true).get(0), SexSlotStanding.PERFORMING_ORAL),
											new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.VAGINA));
									map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.ANUS));
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(true).get(1))) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										
									} else if(character.equals(getMainCompanion())) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
										
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return character.getForeplayPreference(targetedCharacter);
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							AFTER_COMPANION_FUCKING,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_FUCKING_PERFORM_ANILINGUS", getCharactersPresent(true))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();

							initialActions.add(new InitialSexActionInformation(getMainCompanion(), getCharactersPresent(true).get(1), TongueVagina.CUNNILINGUS_START, false, false));
							initialActions.add(new InitialSexActionInformation(Main.game.getPlayer(), getCharactersPresent(true).get(1), TongueAnus.ANILINGUS_START, false, true));
							
							return initialActions;
						}
					};
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMPANION_FUCKING = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharactersPresent(true), "[npc2.Name] закончила с тобой и [npc.name]."); 
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_COMPANION_FUCKING", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Насладившись [npc2.her], [npc2.name] оставляет вас с [npc.name], чтобы вы могли продолжить свои дела в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_GROUP_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_GROUP_SEX", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						UtilText.parse(getCharactersPresent(true), "Решить не связываться с [npc.name] и группой крыс..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
				
			} else if(index==2) {
				return new ResponseSex("Присоединиться",
						UtilText.parse(getCharactersPresent(true), "Присоединяйся и получи свое вместе с [npc.name]."),
						true,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(getCharactersPresent(true).get(1), SexSlotLyingDown.MISSIONARY),
										new Value<>(getCharactersPresent(true).get(2), SexSlotLyingDown.MISSIONARY_TWO),
										new Value<>(getCharactersPresent(true).get(3), SexSlotLyingDown.FACE_SITTING_REVERSE),
										new Value<>(getCharactersPresent(true).get(4), SexSlotLyingDown.FACE_SITTING_REVERSE_TWO)),
								Util.newHashMapOfValues(
										new Value<>(getCharactersPresent(true).get(0), SexSlotLyingDown.LYING_DOWN),
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								map.put(getCharactersPresent(true).get(1), Util.newArrayListOfValues(CoverableArea.PENIS));
								map.put(getCharactersPresent(true).get(2), Util.newArrayListOfValues(CoverableArea.PENIS));
								map.put(getCharactersPresent(true).get(3), Util.newArrayListOfValues(CoverableArea.VAGINA));
								map.put(getCharactersPresent(true).get(4), Util.newArrayListOfValues(CoverableArea.VAGINA));
								
								map.put(getCharactersPresent(true).get(0), Util.newArrayListOfValues(CoverableArea.VAGINA));
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
								return map;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(getCharactersPresent(true).get(1))) {
									if(targetedCharacter.equals(getCharactersPresent(true).get(0))) {
										if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
										}
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									}
									if(targetedCharacter.equals(getCharactersPresent(true).get(3)) && character.isAttractedTo(getCharactersPresent(true).get(3))) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
								if(character.equals(getCharactersPresent(true).get(2))) {
									if(targetedCharacter.isPlayer()) {
										if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
										}
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									}
									if(targetedCharacter.equals(getCharactersPresent(true).get(4)) && character.isAttractedTo(getCharactersPresent(true).get(4))) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
								if(character.equals(getCharactersPresent(true).get(3))) {
									if(targetedCharacter.equals(getCharactersPresent(true).get(0))) {
										if(character.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
									if(targetedCharacter.equals(getCharactersPresent(true).get(1)) && character.isAttractedTo(getCharactersPresent(true).get(1))) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
								if(character.equals(getCharactersPresent(true).get(4))) {
									if(targetedCharacter.isPlayer()) {
										if(character.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
									if(targetedCharacter.equals(getCharactersPresent(true).get(2)) && character.isAttractedTo(getCharactersPresent(true).get(2))) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer() && !character.equals(getMainCompanion())) {
									return character.getForeplayPreference(targetedCharacter);
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
						},
						null,
						null,
						AFTER_COMPANION_GROUP_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_GROUP_SEX_JOIN", getCharactersPresent(true))){
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> initialActions = new ArrayList<>();
						
						if(getCharactersPresent(true).get(1).hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, true, true));
						} else {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, true, true));
						}
						if(getCharactersPresent(true).get(3).hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(3), getMainCompanion(), TongueAnus.RECEIVING_ANILINGUS_START, true, true));
						} else {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(3), getMainCompanion(), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
						}

						if(getCharactersPresent(true).get(2).hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(2), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
						} else {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(2), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
						}
						if(getCharactersPresent(true).get(4).hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(4), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, true, true));
						} else {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(true).get(4), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
						}
						
						return initialActions;
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_COMPANION_GROUP_SEX = new DialogueNode("Завершено", "С крысами по вам двоим закончено...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_COMPANION_GROUP_SEX", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Насладившись, крысы оставляют тебя и [npc.name] заниматься другими делами в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_SERVING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			int seconds = 3*60*60;
			int progression = seconds + Main.game.getDaySeconds();
			if(progression > 22*60*60) { // Do not pass 22:10:
				seconds = seconds - (progression - 22*60*60);
				seconds += 10*60;
			}
			return seconds; 
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SERVING", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Завершено",
						UtilText.parse(getMainCompanion(), "После долгих часов раздачи еды и выпивки крысам в зале, ты и [npc.name] наконец-то свободны."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_SHADOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SHADOW", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отшлепан(а)",
						UtilText.parse(getMainCompanion(), "Шэдоу начинает беспощадно шлепать тебя и [npc.name]..."),
						VENGARS_HALL_CHOOSE_COMPANION_SHADOW_SPANKED);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_SHADOW_SPANKED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SHADOW_SPANKED", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Отсосали",
						"Теперь твоя очередь — доминантная крыса-девочка будет ласкать тебя языком.",
						true,
						false,
						new SMEatenOut(
								SexPosition.OVER_DESK,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Shadow.class), SexSlotDesk.PERFORMING_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT),
										new Value<>(getMainCompanion(), SexSlotDesk.OVER_DESK_ON_FRONT_TWO))),
						null,
						null,
						AFTER_SHADOW_COMPANION_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SHADOW_SPANKED_START_SEX", getCharactersPresent(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						list.add(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
						list.add(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), getMainCompanion(), FingerVagina.FINGERING_START, false, true));
						return list;
					}
				};
			}
			return null;
		}
	};
	

	public static final DialogueNode AFTER_SHADOW_COMPANION_ORAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_SHADOW_COMPANION_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Исполнить приказ Шэдоу и вернуться вниз, в зал.", Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_SILENCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SILENCE", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Очистить её",
						"Опустись на колени перед Сайленс и начни слизывать сперму из её только что заправленной спермой киски.",
						true,
						false,
						new SMEatingOut(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Silence.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						AFTER_SILENCE_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SILENCE_PLAYER_CLEANS", getCharactersPresent(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Silence.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Смотреть",
						UtilText.parse(getMainCompanion(), "Смотреть, как [npc.name] слизывает сперму из только что заправленной спермой киски Сайленс..."),
						true,
						false,
						new SMEatingOut(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Silence.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(
										new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL))),
						null,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						AFTER_SILENCE_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_SILENCE_COMPANION_CLEANS", getCharactersPresent(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Silence.class), getMainCompanion(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new Response("Уйти",
						UtilText.parse(getCharactersPresent(true), "Оставить [npc.name] слизывать сперму из только что заправленной спермой киски Сайленс и заняться другими делами в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						Main.game.getNpc(Silence.class).clearFluidsStored(SexAreaOrifice.VAGINA);
						getMainCompanion().ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.MOUTH, 300);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_VENGAR = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подставка для ног",
						UtilText.parse(getMainCompanion(), "У тебя почти нет выбора, кроме как присоединиться к [npc.name] и стать подставкой для ног Венгара..."),
						VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Предстать перед ней",
						UtilText.parse(getMainCompanion(), "Делай всё, что велит Венгар, и постарайся преподнести себя ему лучше, чем [npc.name]."),
						VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_PRESENT) {
					@Override
					public void effects() {
						generateRat(Gender.getGenderFromUserPreferences(false, true));
					}
				};
				
			} else if(index==2) {
				return new Response("Сдерживаться",
						UtilText.parse(getMainCompanion(), "Не слишком стараться перед Венгаром, чтобы [npc.name] точно выбрали для траха..."),
						VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_HOLD_BACK) {
					@Override
					public void effects() {
						generateRat(Gender.getGenderFromUserPreferences(false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_PRESENT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_PRESENT", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Куколка для члена Венгара",
						UtilText.parse(getMainCompanion(), "Сказать Венгару, что ты его покорная куколка для члена."),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND),
										new Value<>(getCharactersPresent(true).get(1), SexSlotAllFours.BEHIND_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										new Value<>(getCharactersPresent(true).get(0), SexSlotAllFours.ALL_FOURS_TWO))),
						null,
						null,
						VENGARS_HALL_CHOOSE_COMPANION_VENGAR_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_PRESENT_START", getCharactersPresent(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						list.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_HOLD_BACK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_HOLD_BACK", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Публичная спермоприемница",
						UtilText.parse(getCharactersPresent(true).get(1), "Сделать так, как говорит [npc.name], и признать, что ты — публичная спермоприемница для любого крыса, который захочет тебя использовать."),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND),
										new Value<>(getCharactersPresent(true).get(1), SexSlotAllFours.BEHIND_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										new Value<>(getCharactersPresent(true).get(0), SexSlotAllFours.ALL_FOURS_TWO))),
						null,
						null,
						VENGARS_HALL_CHOOSE_COMPANION_VENGAR_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR_FOOTREST_HOLD_BACK_START", getCharactersPresent(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
						list.add(new InitialSexActionInformation(getCharactersPresent(true).get(1), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_CHOOSE_COMPANION_VENGAR_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharactersPresent(true), "С Венгаром и [npc2.Name] с вами и [npc.name] покончено."); 
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_CHOOSE_COMPANION_VENGAR_AFTER_SEX", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Насладившись, Венгар приказывает тебе и [npc.name] заняться другими обязанностями в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_DELIVERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_DELIVERY_PLAYER", getCharactersPresent(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_DELIVERY_COMPANION", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				if(index==1) {
					return new Response("Спальня",
							"Последовать за Тенью и Тишиной в спальню.",
							VENGARS_HALL_DELIVERY_BIRTHING) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
							Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
							Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
						}
					};
				}
					
			} else {
				if(index==1) {
					return new Response("Продолжить",
							UtilText.parse(getMainCompanion(), "Судя по тому, как нежно Безмолвная обращается с [npc.name], ты можешь спокойно продолжать свои обязанности в зале, не беспокоясь о [npc.herHim]."),
							Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
						@Override
						public void effects() {
							getMainCompanion().endPregnancy(true);
							getMainCompanion().setMana(0);
							if(getMainCompanion().getBodyMaterial()!=BodyMaterial.SLIME) {
								getMainCompanion().incrementVaginaStretchedCapacity(15);
								getMainCompanion().incrementVaginaCapacity(
										(getMainCompanion().getVaginaStretchedCapacity()-getMainCompanion().getVaginaRawCapacityValue())*getMainCompanion().getVaginaPlasticity().getCapacityIncreaseModifier(),
										false);
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	
	public static final DialogueNode VENGARS_HALL_DELIVERY_BIRTHING = new DialogueNode("", "", true) {//TODO append offspring.
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isVaginaEggLayer()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "VENGARS_HALL_DELIVERY_BIRTHING_EGGS");
			} else {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "VENGARS_HALL_DELIVERY_BIRTHING");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isVaginaEggLayer()) {
					return new Response("Защищать яйца!", "Ты проводишь некоторое время, приходя в себя после пережитого...", VENGARS_HALL_DELIVERY_BIRTHING_EGG_PROTECTION) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						}
					};
				} else {
					return new Response("Отдых", "Ты проводишь некоторое время, приходя в себя после пережитого...", VENGARS_HALL_DELIVERY_BIRTHING_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_DELIVERY_BIRTHING_EGG_PROTECTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 12*60*60; //TODO next morning
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "VENGARS_HALL_DELIVERY_BIRTHING_EGG_PROTECTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Пришло время вернуться к своим обязанностям в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_DELIVERY_BIRTHING_FINISHED = new DialogueNode("", "", true) { 
		@Override
		public int getSecondsPassed() {
			return 12*60*60; //TODO next morning
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "VENGARS_HALL_DELIVERY_BIRTHING_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(true), "Пришло время вернуться к своим обязанностям в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Околачиваться в спальнях в попытке скоротать время, чтобы не работать в зале...", Main.game.getDefaultDialogue(true, true)) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Извилистый проход", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вернуться назад", "У тебя почти нет выбора, кроме как поступить по приказу стражника и вернуться в зал...", VENGARS_HALL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};
	
	
	
	// ENCOUNTERS: //
	
	// When in hall:
	//  If night, always taken to bedroom and fucked
	// 	Rats get you to serve drinks
	//  Rats grope you
	//  A rat fucks you
	// 	Rats fuck you
	// 	Rat gets you to perform oral under table

	// When in bedroom:
	//  Shadow or Silence make you clean the room (with options to clean them as well)
	// 	Shadow & Silence dominate you
	//  Shadow forbids you from sulking in room (if already cleaned)
	

	public static final DialogueNode VENGARS_HALL_NIGHT_TIME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			if(Main.game.getPlayer().hasCompanions()) {
				Main.game.getPlayer().getMainCompanion().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			}
			Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_NIGHT_TIME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Тень", "Тень ведет тебя в личные спальни...", VENGARS_HALL_NIGHT_TIME_ESCORTED) {
					@Override
					public void effects() {
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_NIGHT_TIME_ESCORTED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_NIGHT_TIME_ESCORTED_SEX", getCharactersPresent(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_NIGHT_TIME_ESCORTED", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return VENGARS_BEDROOM_NIGHT_TIME.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGARS_HALL_SERVE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_SERVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить служение",
						"Похоже, тебе еще долго не позволят оставить разнос еды и напитков...",
						VENGARS_HALL_SERVING);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_SERVING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			int seconds = 3*60*60;
			int progression = seconds + Main.game.getDaySeconds();
			if(progression > 22*60*60) { // Do not pass 22:10:
				seconds = seconds - (progression - 22*60*60);
				seconds += 10*60;
			}
			return seconds; 
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_SERVING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Завершено",
						"После долгих часов разноса еды и напитков крысам в зале, ты наконец-то заканчиваешь.",
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_GROPED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(false, false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROPED", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getCharactersPresent(false).size()>1) {
				if(index==1) {
					return new Response("Ощупывание",
							"Просто позволь крысам лапать тебя, не давая им зайти слишком далеко.",
							VENGARS_HALL_GROPED_FINISH);
					
				} else if(index==2) {
					return new ResponseSex("Трахнутый",
							"Ляг на стол спиной и соблазни крыс трахнуть тебя.",
							true,
							false,
							new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(false).get(0), SexSlotLyingDown.LYING_DOWN),
											new Value<>(getCharactersPresent(false).get(1), Main.game.isAnalContentEnabled()?SexSlotLyingDown.MISSIONARY:SexSlotLyingDown.FACE_SITTING),
											new Value<>(getCharactersPresent(false).get(2), SexSlotLyingDown.BESIDE),
											new Value<>(getCharactersPresent(false).get(3), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.COWGIRL))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.PENIS));
									if(Main.game.isAnalContentEnabled()) {
										map.put(getCharactersPresent(false).get(1), Util.newArrayListOfValues(CoverableArea.PENIS));
									} else {
										map.put(getCharactersPresent(false).get(1), Util.newArrayListOfValues(CoverableArea.VAGINA));
										map.get(getCharactersPresent(false).get(0)).add(CoverableArea.MOUTH);
									}
									map.put(getCharactersPresent(false).get(2), Util.newArrayListOfValues(CoverableArea.VAGINA));
									map.put(getCharactersPresent(false).get(3), Util.newArrayListOfValues(CoverableArea.VAGINA));
									
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
									if(Main.game.isAnalContentEnabled()) {
										map.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
									}
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(false).get(0))) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										}
										if(targetedCharacter.equals(getCharactersPresent(false).get(1)) && !Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
										}
									}
									if(character.equals(getCharactersPresent(false).get(1))) {
										if(targetedCharacter.isPlayer()) {
											if(Main.game.isAnalContentEnabled()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
											}
										}
										if(targetedCharacter.equals(getCharactersPresent(false).get(0)) && !Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return character.getForeplayPreference(targetedCharacter);
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							VENGARS_HALL_AFTER_GROUP_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROPED_GROUP_START_SEX", getCharactersPresent(false))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
							
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
							
							if(Main.game.isAnalContentEnabled()) {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(1), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
							} else {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), getCharactersPresent(false).get(1), TongueVagina.CUNNILINGUS_START, true, true));
							}
							
							return initialActions;
						}
					};
				}
				
			} else { //Solo rat:
				if(index==1) {
					return new Response("Ощупывание",
							UtilText.parse(getCharactersPresent(false), "Просто позволь [npc.name] лапать тебя, не давая [npc.herHim] зайти слишком далеко."),
							VENGARS_HALL_GROPED_FINISH);
					
				} else if(index==2 && (getCharactersPresent(false).get(0).hasPenis() || getCharactersPresent(false).get(0).hasVagina())) {
					return new ResponseSex("Сделать минет",
							UtilText.parse(getCharactersPresent(false), "Сделай [npc.name] минет."),
							true,
							false,
							new SMSitting(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(false).get(0), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									if(getCharactersPresent(false).get(0).hasPenis()) {
										map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.PENIS));
									} else {
										map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.VAGINA));
									}
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(false).get(0))) {
										if(character.hasPenis()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									return getForeplayPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							AFTER_GROPED_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROPED_START_ORAL", getCharactersPresent(false))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
	
							if(getCharactersPresent(false).get(0).hasPenis()) {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							} else {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
							
							return initialActions;
						}
					};
					
				} else if(index==3 && getCharactersPresent(false).get(0).hasPenis()) {
					return new ResponseSex("Трахнутый",
							UtilText.parse(getCharactersPresent(false), "Ляг на стол спиной и соблазни [npc.name] трахнуть тебя."),
							true,
							false,
							new SMOverDesk(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(false).get(0), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_BACK))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.PENIS));
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(false).get(0))) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									return getForeplayPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							AFTER_GROPED_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROPED_START_SEX", getCharactersPresent(false))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							return initialActions;
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GROPED_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharactersPresent(false), "[npc.Name] закончил с тобой."); 
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getPosition()==SexPosition.SITTING) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_GROPED_ORAL", getCharactersPresent(false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "AFTER_GROPED_SEX", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(false), "Насладившись [npc.her], [npc.name] оставляет тебя, чтобы заняться другими делами в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())) {
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_GROPED_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			if(getCharactersPresent(false).size()>1) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROPED_GROUP_FINISH", getCharactersPresent(false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROPED_FINISH", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						getCharactersPresent(false).size()>1
							?"Насладившись, крысы оставляют тебя, чтобы ты мог заняться другими делами в зале..."
							:UtilText.parse(getCharactersPresent(false), "Насладившись [npc.her], [npc.name] оставляет тебя, чтобы заняться другими делами в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())){
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_VENGAR_FUCK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_VENGAR_FUCK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return VENGARS_HALL_CHOOSE_VENGAR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGARS_HALL_RAT_FUCK = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(false, false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(getCharactersPresent(false).get(0).hasPenis()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_RAT_FUCK_PENIS", getCharactersPresent(false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_RAT_FUCK_VAGINA", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getCharactersPresent(false).get(0).hasPenis()) {
				if(index==1) {
					return new ResponseSex("Трахнутый",
							UtilText.parse(getCharactersPresent(false), "Ты чувствуешь, что тебя толкнули на стол, и [npc.name] вот-вот трахнет тебя..."),
							true,
							false,
							new SMOverDesk(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(false).get(0), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.PENIS));
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(false).get(0))) {
										if(targetedCharacter.isPlayer()) {
											if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
											}
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										}
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return character.getForeplayPreference(targetedCharacter);
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							VENGARS_HALL_RAT_FUCK_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_RAT_FUCK_FUCKED", getCharactersPresent(false))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
							
							if(getCharactersPresent(false).get(0).hasFetish(Fetish.FETISH_ANAL_GIVING)) {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							} else {
								initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							}
							
							return initialActions;
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex("Куннилингус",
							UtilText.parse(getCharactersPresent(false), "Делай так, как говорит [npc.name], и начинай отсасывать [npc.herHim]."),
							true,
							false,
							new SMAgainstWall(
									Util.newHashMapOfValues(
											new Value<>(getCharactersPresent(false).get(0), SexSlotAgainstWall.BACK_TO_WALL)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.VAGINA));
									return map;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.equals(getCharactersPresent(false).get(0))) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return character.getForeplayPreference(targetedCharacter);
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
							},
							null,
							null,
							VENGARS_HALL_RAT_FUCK_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_RAT_FUCK_CUNNILINGUS", getCharactersPresent(false))){
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> initialActions = new ArrayList<>();
							
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							
							return initialActions;
						}
					};
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_RAT_FUCK_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_RAT_FUCK_AFTER_SEX", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						UtilText.parse(getCharactersPresent(false), "Насладившись [npc.her], [npc.name] оставляет тебя, чтобы заняться другими делами в зале..."),
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())){
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_GROUP_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(false, true));
			if(Main.game.isAnalContentEnabled()) {
				VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(false, true));
			} else {
				VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(true, false));
			}
			VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(false, false));
			VengarCaptiveDialogue.generateRat(Gender.getGenderFromUserPreferences(false, false));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROUP_SEX", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Использовано",
						"Крысы начинают ощупывать и ласкать тебя, увлекая в нужное положение...",
						true,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(getCharactersPresent(false).get(0), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getCharactersPresent(false).get(1), Main.game.isAnalContentEnabled()?SexSlotLyingDown.MISSIONARY:SexSlotLyingDown.FACE_SITTING),
										new Value<>(getCharactersPresent(false).get(2), SexSlotLyingDown.BESIDE),
										new Value<>(getCharactersPresent(false).get(3), SexSlotLyingDown.BESIDE_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.COWGIRL))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								map.put(getCharactersPresent(false).get(0), Util.newArrayListOfValues(CoverableArea.PENIS));
								if(Main.game.isAnalContentEnabled()) {
									map.put(getCharactersPresent(false).get(1), Util.newArrayListOfValues(CoverableArea.PENIS));
								} else {
									map.put(getCharactersPresent(false).get(1), Util.newArrayListOfValues(CoverableArea.VAGINA));
									map.get(getCharactersPresent(false).get(0)).add(CoverableArea.MOUTH);
								}
								map.put(getCharactersPresent(false).get(2), Util.newArrayListOfValues(CoverableArea.VAGINA));
								map.put(getCharactersPresent(false).get(3), Util.newArrayListOfValues(CoverableArea.VAGINA));
								
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
								if(Main.game.isAnalContentEnabled()) {
									map.get(Main.game.getPlayer()).add(CoverableArea.ANUS);
								}
								return map;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.equals(getCharactersPresent(false).get(0))) {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									}
									if(targetedCharacter.equals(getCharactersPresent(false).get(1)) && !Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									}
								}
								if(character.equals(getCharactersPresent(false).get(1))) {
									if(targetedCharacter.isPlayer()) {
										if(Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
										}
									}
									if(targetedCharacter.equals(getCharactersPresent(false).get(0)) && !Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return character.getForeplayPreference(targetedCharacter);
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
						},
						null,
						null,
						VENGARS_HALL_AFTER_GROUP_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_GROUP_SEX_START", getCharactersPresent(false))){
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> initialActions = new ArrayList<>();
						
						initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
						
						if(Main.game.isAnalContentEnabled()) {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(1), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
						} else {
							initialActions.add(new InitialSexActionInformation(getCharactersPresent(false).get(0), getCharactersPresent(false).get(1), TongueVagina.CUNNILINGUS_START, true, true));
						}
						
						return initialActions;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_AFTER_GROUP_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_HALL_AFTER_GROUP_SEX", getCharactersPresent(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Насладившись, крысы оставляют тебя, чтобы ты мог заняться другими делами в зале...",
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime())){
					@Override
					public void effects() {
						banishGenericRats();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_CLEAN = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Чисто", "Сделай так, как приказала Тень, и принимайся за уборку в спальне.", VENGARS_BEDROOM_CLEAN_FINISH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarCaptiveRoomCleaned, true);
						if(Math.random()<0.5f) {
							Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_BEDROOM_CLEAN_FINISH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Shadow.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveShadowSatisfied)) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_SHADOW_ORAL");
				}
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_SHADOW");
				
			} else {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveSilenceSatisfied)) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_SILENCE_ORAL");
				}
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_SILENCE");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Shadow.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveShadowSatisfied)) {
					if(index==1) {
						return new ResponseSex(
								"Поднять зад",
								"Подними задницу повыше, чтобы Тень могла начать отсасывать тебе...",
								true,
								false,
								new SMEatingOut(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Shadow.class), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
								null,
								null,
								VENGARS_BEDROOM_AFTER_RAT_GIRL_ORAL,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_SHADOW_ORAL_START")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					
					} else if(index==2) {
						return new Response("Уйти",
								"Попытаться встать и вместо этого вернуться в зал.<br/><i>Хотя Безмолвие может это и стерпеть, ты думаешь, что Тень вряд ли так легко тебя отпустит...</i>",
								VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW);
					}
					return null;
				}
				
			} else {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveSilenceSatisfied)) {
					if(index==1) {
						return new ResponseSex(
								"Куннилингус",
								"Встань на колени перед Сайленс и начни отсасывать ей...",
								true,
								false,
								new SMEatingOut(
										SexPosition.STANDING,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Silence.class), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
								null,
								null,
								VENGARS_BEDROOM_AFTER_RAT_GIRL_ORAL,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_SILENCE_ORAL_START")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Silence.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					
					} else if(index==2) {
						return new Response("Уйти",
								"Решить не делать минет Сайленс и вместо этого вернуться в зал.",
								VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SILENCE) {
							@Override
							public void effects() {
								Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							}
						};
					}
					return null;
				}
			}
			
			if(index==1) {
				return new Response("Главный зал",
						Main.game.getCharactersPresent().contains(Main.game.getNpc(Shadow.class))
							?"Сделай так, как говорит Тень, и возвращайся в главный зал."
							:"Сделай так, как хочет Безмолвие, и возвращайся в главный зал.",
						PlaceType.RAT_WARRENS_VENGARS_HALL.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SILENCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SILENCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Подумать о том, что делать в главном зале дальше.",
						Main.game.getDefaultDialogue(!Main.game.isExtendedWorkTime()));
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отшлепан(а)",
						"Шэдоу начинает беспощадно шлепать тебя по заднице...",
						VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW_FACE_SITTING);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW_FACE_SITTING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW_FACE_SITTING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Повиноваться",
						"У тебя почти нет выбора, кроме как подчиниться приказам доминантной крысодевочки...",
						true,
						false,
						new SMEatingOut(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Shadow.class), SexSlotLyingDown.FACE_SITTING)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						VENGARS_BEDROOM_AFTER_RAT_GIRL_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_CLEAN_FINISH_IGNORE_SHADOW_FACE_SITTING_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_AFTER_RAT_GIRL_ORAL = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			if(Main.sex.getAllParticipants().contains(Main.game.getNpc(Shadow.class))) {
				return "Шэдоу на время хватит твоего орального внимания.";
			}
			return "Сайленс на время хватит твоего орального внимания.";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(Main.game.getNpc(Shadow.class))) {
				if(Main.sex.getPosition()==SexPosition.ALL_FOURS) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_AFTER_SHADOW_PERFORMING_ORAL");
				}
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_AFTER_SHADOW_RECEIVING_ORAL");
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_AFTER_SILENCE_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Главный зал",
						"Вернуться в главный зал.",
						PlaceType.RAT_WARRENS_VENGARS_HALL.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_SHADOW_SILENCE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			if(Main.game.getPlayer().hasCompanions()) {
				Main.game.getPlayer().getMainCompanion().setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_SHADOW_SILENCE", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				if(index==1) {
					return new ResponseSex(
							"Тень",
							"Выразить желание, чтобы доминантная крысодевочка отсосала тебе...",
							true,
							false,
							new SMShadowSilence(
									SexPosition.AGAINST_WALL,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Silence.class), SexSlotAgainstWall.BACK_TO_WALL),
											new Value<>(Main.game.getNpc(Shadow.class), SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.BACK_TO_WALL_TWO),
											new Value<>(getMainCompanion(), SexSlotAgainstWall.PERFORMING_ORAL_WALL))),
							null,
							null,
							VENGARS_BEDROOM_SHADOW_SILENCE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_SHADOW_SILENCE_FOURSOME_SHADOW_START", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getNpc(Silence.class), getMainCompanion(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							if(!list.isEmpty()) {
								return list;
							}
							return super.getInitialSexActions();
						}
					};
					
				} else if(index==2) {
					return new ResponseSex(
							"Сайленс",
							"Выразить желание отсосать у белошерстной крысодевочки...",
							true,
							false,
							new SMShadowSilence(
									SexPosition.AGAINST_WALL,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Silence.class), SexSlotAgainstWall.BACK_TO_WALL),
											new Value<>(Main.game.getNpc(Shadow.class), SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.PERFORMING_ORAL_WALL),
											new Value<>(getMainCompanion(), SexSlotAgainstWall.BACK_TO_WALL_TWO))),
							null,
							null,
							VENGARS_BEDROOM_SHADOW_SILENCE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_SHADOW_SILENCE_FOURSOME_SILENCE_START", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getNpc(Silence.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							if(!list.isEmpty()) {
								return list;
							}
							return super.getInitialSexActions();
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"Повиноваться",
							"У тебя почти нет выбора, кроме как подчиниться приказам доминантной крысодевочки...",
							true,
							false,
							new SMEatingOut(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Silence.class), SexSlotLyingDown.FACE_SITTING),
											new Value<>(Main.game.getNpc(Shadow.class), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							Util.newArrayListOfValues(getMainCompanion()),
							VENGARS_BEDROOM_SHADOW_SILENCE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_SHADOW_SILENCE_SOLO_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							list.add(new InitialSexActionInformation(Main.game.getNpc(Shadow.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getNpc(Silence.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_SHADOW_SILENCE_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_SHADOW_SILENCE_AFTER_SEX", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Главный зал",
						"Сделай так, как говорит Тень, и возвращайся в главный зал.",
						PlaceType.RAT_WARRENS_VENGARS_HALL.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						if(isCompanionDialogue()) {
							getMainCompanion().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						}
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_BARRED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_BARRED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Главный зал",
						"Сделай так, как говорит Тень, и возвращайся в главный зал.",
						PlaceType.RAT_WARRENS_VENGARS_HALL.getDialogue(true)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};

	
	// Night time dialogues:
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
			if(Main.game.getPlayer().hasCompanions()) {
				Main.game.getPlayer().getMainCompanion().setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_SEX", getCharactersPresent(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied)) {
				if(index==1) {
					return new ResponseSex(
							"Предстать перед ней",
							"Встать на четвереньки и подставить задницу Венгару, предлагая себя для ебли.",
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
							null,
							Util.newArrayListOfValues(getMainCompanion()),
							VENGARS_BEDROOM_NIGHT_TIME_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_SEX_START", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
					
				} else if(index==2 && isCompanionDialogue()) {
					return new ResponseSex(
							UtilText.parse(getMainCompanion(), "[npc.Name]"),
							UtilText.parse(getMainCompanion(), "Пусть [npc.name] будет той, кого будет ебать Венгар."),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), getMainCompanion().isVisiblyPregnant()?SexSlotLyingDown.MISSIONARY:SexSlotLyingDown.MATING_PRESS)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN))),
							null,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							VENGARS_BEDROOM_NIGHT_TIME_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_SEX_COMPANION_START", getCharactersPresent(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				return null;
				
			} else {
				return VENGARS_BEDROOM_NIGHT_TIME_AFTER_SEX.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME_AFTER_SEX = new DialogueNode("Завершено", "Венгар окончательно сломлен...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_AFTER_SEX", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Спать", "Прижаться к Венгару и уснуть вместе с ним.", VENGARS_BEDROOM_NIGHT_TIME_SLEEP) {
					@Override
					public void effects() {
						if(isCompanionDialogue()) {
							getMainCompanion().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						}
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
				
			} else if(index==2) {
				return new Response("Притвориться", "Держаться подальше от Венгара и притвориться спящей, готовой ускользнуть, как только убедишься, что он уснул.", VENGARS_BEDROOM_NIGHT_TIME_PRETEND);
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME_SLEEP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60; //TODO To next morning
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_SLEEP", getCharactersPresent(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Главный зал",
						"Сделай так, как говорит Тень, и возвращайся в главный зал.",
						PlaceType.RAT_WARRENS_VENGARS_HALL.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME_PRETEND = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_PRETEND");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сбежать",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Тихо выбраться из постели и разбудить [npc.name] от [npc.her] сна, чтобы вместе спланировать побег.")
							:"Тихо выбраться из постели и спланировать побег.",
						VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE);
				
			} else if(index==2) {
				return new Response("Спать", "Решить не пытаться сбежать, а вместо этого прижаться к Венгару и уснуть.", VENGARS_BEDROOM_NIGHT_TIME_SLEEP) {
					@Override
					public void effects() {
						if(isCompanionDialogue()) {
							getMainCompanion().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						}
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Отсосать ей",
						"Взять инициативу в свои руки и опуститься на колени перед Сайленс, чтобы начать отсасывать ей.<br/>[style.italicsMinorGood(Это запустит необратимый путь к побегу, который положит конец твоему плену!)]",
						true,
						false,
						new SMEatingOut(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Silence.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_AFTER_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_SILENCE_ORAL_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Silence.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new Response("Протолкнуться мимо",
						(isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Протиснуться мимо Сайленс и забрать из сундука свои вещи и вещи [npc.namePos].")
							:"Протиснуться мимо Сайленс и забрать свои вещи из сундука.")
								+"<br/>[style.italicsMinorGood(Это запустит необратимый путь побега, который положит конец твоему плену!)]",
							VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_PUSH_PAST);
				
			} else if(index==3) {
				return new Response("Сдаться", "Оставить попытки побега на сегодня, а вместо этого вернуться в постель и лечь спать.", VENGARS_BEDROOM_NIGHT_TIME_SLEEP) {
					@Override
					public void effects() {
						if(isCompanionDialogue()) {
							getMainCompanion().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						}
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_AFTER_ORAL = new DialogueNode("Завершено", "Сайленс издает тихий вздох и отступает, довольная твоим выступлением.", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_AFTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Следуйте за Shadow и Silence, пока они выводят вас из Крысиных нор.", ESCAPING) {
					@Override
					public void effects() {
						RatWarrensCaptiveDialogue.restoreInventories();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_PUSH_PAST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_PUSH_PAST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return VENGARS_BEDROOM_NIGHT_TIME_PRETEND_ESCAPE_AFTER_ORAL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ESCAPING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/vengarCaptive", "ESCAPING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сбежать", "Последовать за Шэдоу через туннели.", RatWarrensDialogue.POST_CAPTIVITY_SWORD_RAID) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Silence.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
					}
				};
			}
			return null;
		}
	};
}
