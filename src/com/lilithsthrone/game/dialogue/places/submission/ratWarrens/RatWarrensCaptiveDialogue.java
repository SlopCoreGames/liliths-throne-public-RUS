package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Dialogue for when the player is taken captive by the rats in the Rat Warrens.
 * 
 * @since 0.3.5.5
 * @version 0.3.9
 * @author Innoxia
 */
public class RatWarrensCaptiveDialogue {
	
	private static boolean playerGrewVagina;
	public static int murkOrgasmsRequired = 1;
	
	private static List<GameCharacter> getCharacters(boolean includeMilkers) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> npc.isUnique() || (!includeMilkers && (npc instanceof RatWarrensCaptive)));
		Collections.sort(guards, (a, b)->a.getLevel()-b.getLevel());
		return guards;
	}
	
	private static List<GameCharacter> getMilkers() {
		List<GameCharacter> milkers = new ArrayList<>();
		for(GameCharacter milker : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM))) {
			if(milker instanceof RatWarrensCaptive) {
				milkers.add(milker);
			}
		}
		return milkers;
	}
	
	private static void spawnRat(boolean vaginaNeeded, boolean penisNeeded) {
		List<String> adjectives = new ArrayList<>();
		try {
			String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
			NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(vaginaNeeded, penisNeeded));
			Main.game.addNPC(rat, false);
			rat.setLevel(4+Util.random.nextInt(5));
			rat.setLocation(Main.game.getPlayer(), true);
			adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), adjectives));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void banishRats() {
		for(GameCharacter rat : getCharacters(false)) {
			Main.game.banishNPC((NPC) rat);
		}
	}
	
	private static GameCharacter getMurk() {
		return Main.game.getNpc(Murk.class);
	}

	public static String equipCollar(GameCharacter character, GameCharacter equipper, Colour collarColour) {
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", collarColour, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		return character.equipClothingFromNowhere(collar, true, equipper);
	}
	
	private static ResponseSex getPlayerOwnerEscapeSexResponse(boolean lyingDown, DialogueNode node, String nodePathHandjob, String nodePathOral, String nodePathSex) {
		AbstractSexPosition position;
		Value<SexSlot, SexType> murkSexInfo;
		SexSlot playerSlot;
		String sexIntroTextPath;
		String responseTitle;
		String responseDescription;
		int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
		
		if(lyingDown) {
			if(stage==0) {
				responseTitle = "Ручная стимуляция";
				responseDescription = "Сделай, как говорит Мёрк, и отсоси ему рукой...";
				sexIntroTextPath = nodePathHandjob;
				position = SexPosition.LYING_DOWN;
				murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER));
				playerSlot = SexSlotLyingDown.BESIDE;
				
			} else if(stage>=4) {
				sexIntroTextPath = nodePathSex;
				responseTitle = "Скакать на члене";
				responseDescription = "Сделай, как говорит твой Господин, и оседлай его член...";
				if(Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs()) {
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.COWGIRL_REVERSE;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.COWGIRL_REVERSE;
					}
					
				} else {
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.COWGIRL;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.COWGIRL;
					}
				}
				
			} else {
				responseTitle = "Поцеловать член";
				responseDescription = "Сделай, как говорит Мёрк, и поцелуй его член...";
				sexIntroTextPath = nodePathOral;
				position = SexPosition.LYING_DOWN;
				murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
				playerSlot = SexSlotLyingDown.MISSIONARY_ORAL;
			}
			
		} else {
			if(stage==0) {
				responseTitle = "Встать на колени";
				responseDescription = "Сделай, как говорит Мёрк, и опустись перед ним на колени, чтобы отсосать ему рукой...";
				sexIntroTextPath = nodePathHandjob;
				position = SexPosition.STANDING;
				murkSexInfo = new Value<>(SexSlotStanding.STANDING_DOMINANT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER));
				playerSlot = SexSlotStanding.PERFORMING_ORAL;
				
			} else if(stage>=4) {
				sexIntroTextPath = nodePathSex;
				if(Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs()) {
					responseTitle = "Трахнутый";
					responseDescription = "Сделай, как говорит твой Господин, и подставься под него, чтобы он хорошенько тебя оттрахал...";
					position = SexPosition.ALL_FOURS;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotAllFours.HUMPING, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotAllFours.ALL_FOURS;
						
					} else {
						murkSexInfo = new Value<>(SexSlotAllFours.HUMPING, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotAllFours.ALL_FOURS;
					}
					
				} else {
					responseTitle = "Лечь на спину";
					responseDescription = "Сделай, как говорит твой Господин, и ляг перед ним, чтобы он хорошенько тебя оттрахал...";
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.LYING_DOWN;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.LYING_DOWN;
					}
				}
				
			} else {
				responseTitle = "Спуститься на колени";
				responseDescription = "Сделай, как говорит Мёрк, и упади перед ним на колени, чтобы отсосать его член...";
				sexIntroTextPath = nodePathOral;
				position = SexPosition.ALL_FOURS;
				murkSexInfo = new Value<>(SexSlotAllFours.IN_FRONT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
				playerSlot = SexSlotAllFours.ALL_FOURS;
			}
		}
		
		return new ResponseSex(
				responseTitle,
				responseDescription,
				true,
				false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(new Value<>(getMurk(), murkSexInfo.getKey())),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
					@Override
					public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
						return false;
					}
					@Override
					public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
						if(!character.isPlayer()) {
							if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH || murkSexInfo.getValue().getTargetedSexArea()==SexAreaPenetration.FINGER) {
								return OrgasmBehaviour.PULL_OUT;
							}
							return OrgasmBehaviour.CREAMPIE;
						}
						return super.getCharacterOrgasmBehaviour(character);
					}
					@Override
					public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
						if(!character.isPlayer()) {
							return OrgasmCumTarget.FACE;
						}
						return null;
					}
					@Override
					public SexControl getSexControl(GameCharacter character) {
						if(character.isPlayer()) {
							return SexControl.NONE;
						}
						return super.getSexControl(character);
					}
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer()) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
						return !equippingCharacter.isPlayer();
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (murkSexInfo.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || murkSexInfo.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return murkSexInfo.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return murkSexInfo.getValue();
					}
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", sexIntroTextPath, getCharacters(false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));

				} else if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				} else {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
					
				}
			}
		};
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms) {
		return getPlayerMurkMilkingStallSM(murkSlot, murkSexPreference, murkOrgasms, null);
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms, SexPace startingSexPace) {
		return getBasicSexManager(SexPosition.MILKING_STALL,
				Util.newHashMapOfValues(new Value<>(getMurk(), murkSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
				murkSexPreference, murkOrgasms, startingSexPace, false);
	}
	
	private static SexManagerDefault getBasicSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			SexType murkSexPreference,
			int murkOrgasms,
			SexPace startingSexPace,
			boolean spitOnAsshole) {
		if(murkOrgasms>0) {
			murkOrgasmsRequired = murkOrgasms;
		}
		return new SexManagerDefault(position, dominants, submissives) {
			@Override
			public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(murkOrgasms>0 && partner.equals(getMurk())) {
					return Main.sex.getNumberOfOrgasms(partner)>=murkOrgasms;
				}
				return super.isPartnerWantingToStopSex(partner);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.NONE;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
				for(GameCharacter dom : dominants.keySet()) {
					map.put(dom, Util.newArrayListOfValues(CoverableArea.PENIS));
				}
				return map;
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return false;
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(spitOnAsshole) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getPlayer(), new HashMap<>());
					map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, Util.newHashMapOfValues(new Value<>(getMurk(), Util.newHashSetOfValues(LubricationType.SALIVA))));
					return map;
				}
				return super.getStartingWetAreas();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return murkSexPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return super.getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer() && startingSexPace!=null) {
					return startingSexPace;
				}
				return super.getStartingSexPaceModifier(character);
			}
		};
	}
	
	private static void applyWakingEffects() {
		getMurk().setLocation(Main.game.getPlayer(), false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveWashed, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
		for(GameCharacter milker : getMilkers()) {
			((RatWarrensCaptive)milker).applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
		}
	}
	
	private static void applyPlayerMilkingPumps(boolean equip, List<InventorySlot> slots) {
		GameCharacter player = Main.game.getPlayer();
		if(equip) {
			// It really doesn't make any narrative sense for the game's lactation content setting to limit the entire purpose of Murk's milkers. Instead of preventing milking, the lactation content setting just limits descriptions of it.
			if(slots.contains(InventorySlot.NIPPLE) && player.hasBreasts()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, player);
			}
			if(slots.contains(InventorySlot.STOMACH) && player.hasBreastsCrotch()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, player);
			}
			if(slots.contains(InventorySlot.PENIS) && player.hasPenis()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, player);
			}
			if(slots.contains(InventorySlot.VAGINA) && player.hasVagina()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, player);
			}
			
		} else {
			for(AbstractClothing c : new ArrayList<>(player.getClothingCurrentlyEquipped())) {
				if(c.isMilkingEquipment() && slots.contains(c.getSlotEquippedTo())) {
					player.unequipClothingIntoVoid(c, true, player);
				}
			}
		}
	}
	
	public static void restoreInventories() {
		int essences = Main.game.getPlayer().getEssenceCount();
		Main.game.getPlayer().setInventory(Main.game.getSavedInventories().get(Main.game.getPlayer().getId()));
		Main.game.getPlayer().setEssenceCount(essences);
	}
	
	public static boolean isTransformationFinished() {
		CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
		return playerTf==null;
	}
	
	private static DialogueNode getSleepNode() {
		if(!isTransformationFinished()) {
			int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
			if(stage==0) {
				return CAPTIVE_DAY_1_WAKE;
			}
			if(stage==1) {
				return CAPTIVE_DAY_2_WAKE;
			}
			if(stage==3) {
				return CAPTIVE_DAY_3_WAKE;
			}
		}
		return BAD_END;
	}
	
	private static boolean isMasculineTransform() {
		return Main.getProperties().getForcedTFTendency().isMasculine()
				&& Main.game.isAnalContentEnabled();
	}

	private static String getObedienceResponseDescription(float increment) {
		String descriptor = "";
		if(increment<-10) {
			descriptor = "[style.italicsMinorGood(значительно меньше)]";
		} else if(increment<5) {
			descriptor = "[style.italicsBad(less)]";
		} else if(increment<0) {
			descriptor = "[style.italicsMinorBad(чуть менее)]";
		} else if(increment<=5) {
			descriptor = "[style.italicsMinorGood(slightly more)]";
		} else if(increment<=10) {
			descriptor = "[style.italicsGood(more)]";
		} else {
			descriptor = "[style.italicsExcellent(значительно больше)]";
		}
		return "<br/><i>Вы станете "+descriptor+" [style.boldObedience(покорными)]!</i>";
	}
	
	private static String incrementPlayerObedience(int increment) {
		Main.game.getPlayer().incrementObedience(increment);
		
		int obedience = (int) Units.round(Main.game.getPlayer().getObedienceValue(), 0);
		if(obedience<0) {
			Main.game.getPlayer().setObedience(0);
			obedience = 0;
		}
		ObedienceLevel level = Main.game.getPlayer().getObedience();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
			if(increment!=0) {
				sb.append("Вы "+(increment>0?"[style.boldGrow(приобретено)]":"[style.boldShrink(потеряно)]")+" <b>"+Math.abs(increment)+"</b> [style.boldObedience(покорность)]!<br/>");
			}
			sb.append("Вы — <span style='color:"+level.getColour().toWebHexString()+";'>"+level.getName()+"</span>"
					+ " (<span style='color:"+level.getColour().toWebHexString()+";'>"+obedience+"</span>/100), ");
			switch(level) {
				case ZERO_FREE_WILLED:
				case POSITIVE_ONE_AGREEABLE:
				case POSITIVE_TWO_OBEDIENT:
					sb.append("и [style.colourMinorGood(можете отказываться или подчиняться любым приказам Мёрка)]!");
					sb.append("<br/><i>При "+ObedienceLevel.POSITIVE_THREE_DISCIPLINED.getMinimumValue()+"/100 вы не сможете отказываться от приказов Мёрка!</i>");
					break;
				case POSITIVE_THREE_DISCIPLINED:
				case POSITIVE_FOUR_DUTIFUL:
					sb.append("и [style.colourMinorBad(не можете заставить себя отказать Мёрку)]!");
					sb.append("<br/><i>При "+ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMinimumValue()+"/100 вы сделаете абсолютно всё, чтобы угодить Мёрку!</i>");
					break;
				case POSITIVE_FIVE_SUBSERVIENT:
					if(obedience>=100) {
						sb.append("и [style.colourBadEnd(смирились со своей судьбой одной из доярок Мёрка)]!");
					} else {
						sb.append("и [style.colourBad(сделаете абсолютно всё, чтобы угодить Мёрку)]!");
						sb.append("<br/><i>При 100/100 вы с радостью примете свою судьбу одной из доярок Мёрка!</i>");
					}
					break;
				default:
					break;
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static boolean isPlayerObeyingOrders(boolean extremeSubCheck) {
		return Main.game.getPlayer().getObedience().isGreaterThan(extremeSubCheck?ObedienceLevel.POSITIVE_FOUR_DUTIFUL:ObedienceLevel.POSITIVE_TWO_OBEDIENT);
	}
	
	
	// --------- START OF DAY 0 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_0 = new DialogueNode("", "", true) {
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
				return new Response("Затаиться",
						"Сиди тихо и надейся, что Мёрк оставит тебя в покое."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_QUIET"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
				
			} else if(index==2) {
				return new Response("Поблагодарить его",
						"Сказать Мёрку «спасибо»."
								+getObedienceResponseDescription(10),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_THANK_HIM"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
				
			} else if(index==3) {
				return new Response("Оскорбить его",
						"Сказать Мёрку, чтобы он отъебался."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_INSULT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_0_THANKS = new DialogueNode("", "", true, true) {
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
			if(Main.game.getPlayer().isPregnant()) {
				if(index==1) {
					return new Response("Ждать", "Ждать возвращения Мёрка...", CAPTIVE_GIVE_BIRTH) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							boolean eggs = !Main.game.getPlayer().getIncubatingLitters().isEmpty();
							if(eggs) {
								for(SexAreaOrifice orifice : new ArrayList<>(Main.game.getPlayer().getIncubatingLitters().keySet())) {
									Main.game.getPlayer().endIncubationPregnancy(orifice, true);
								}
							}
							Main.game.getPlayer().setMana(0);
							
							if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
								Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
								Main.game.getPlayer().incrementVaginaCapacity(
										(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
										false);
							}
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
							}
							if(eggs && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION);
							}
						}
					};
				}
				
			} else if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
				if(index==1) {
					return new Response("Ждать", "Ждать возвращения Мёрка...", CAPTIVE_LAY_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							for(SexAreaOrifice orifice : new ArrayList<>(Main.game.getPlayer().getIncubatingLitters().keySet())) {
								Main.game.getPlayer().endIncubationPregnancy(orifice, true);
							}
							Main.game.getPlayer().setMana(0);
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Спать",
							"Совершенно измотанная, ты чувствуешь, как проваливаешься в сон...",
							CAPTIVE_NIGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_SLEEP_START"));
						}
					};
				}
			}
			return null;
		}
	};
	
	// --------- END OF DAY 0 --------- //

	
	// --------- START OF DAY 1 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_1_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			if(getMilkers().get(3).isPregnant()) {
				getMilkers().get(3).endPregnancy(true); // End milker 4's pregnancy as she's the one to lie on top of the player at the end of day 2 (which seems wrong if she's pregnant)
			}
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_PREP"));
			if(isMasculineTransform()) {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_MASCULINE"));
			} else {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMasculineTransform()) {
				if(index==1) {
					return new Response("Отстраниться",
							"Отстраниться от члена [murk.namePos], что заставит его захотеть превратить тебя в [style.colourMasculineStrong(мужественного)] доярку спермы.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.MASCULINE_PLUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMasculine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_MASCULINE"));
						}
					};
					
				} else if(index==2) {
					return new Response("Податься назад",
							"Приподнять бедра и плотнее прижаться задницей к члену [murk.namePos], что заставит его захотеть превратить тебя в [style.colourAndrogynous(андрогинную)] сисси-доярку спермы.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.ANDROGYNOUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveSissy, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_SISSY"));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Отстраниться",
							"Отстраниться от руки Мёрка, давая понять, что ты предпочёл бы превратиться в [style.colourFeminineStrong(женскую)] доилку.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE_PLUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFeminine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FEMALE"));
						}
					};
					
				} else if(index==2) {
					return new Response("Теребиться на руке",
							"Теребиться на руке Мёрка, давая понять, что ты предпочёл бы превратиться в [style.colourFeminine(фута)] доилку.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFuta, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FUTA"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_TF_CHOICE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().getTattoos().isEmpty()) {
				Main.game.getPlayer().clearTattoos();
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END_CLEAR_TATTOOS"));
			}
			Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END"));
		}
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
				return new Response("Ждать", "У тебя нет иного выбора, кроме как ждать в колодках...", CAPTIVE_DAY_1_MORNING) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(1);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(11*60 + (Util.random.nextInt(45)))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поздороваться",
						"Поздороваться с членом Мёрка в надежде, что тогда он уйдет и прекратит свои издевательства."
							+getObedienceResponseDescription(5),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_ENDURE", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getPlayer().addMuskMarkerCharacter(getMurk());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_MUSK_APPLIED", getMilkers()));
					}
				};
				
			} else if(index==2) {
				return new Response("Сделать комплимент",
						"Поздороваться с членом Мёрка и сделать комплимент его размеру и мощному мускусному запаху."
							+getObedienceResponseDescription(10),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_COMPLIMENT", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getPlayer().addMuskMarkerCharacter(getMurk());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_MUSK_APPLIED", getMilkers()));
					}
				};
				
			} else if(index==3) {
				return new Response("Сопротивляться",
						"Отказаться играть в игру Мёрка и попытаться отстраниться от его члена."
							+getObedienceResponseDescription(-10),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_RESIST", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
						Main.game.getPlayer().addMuskMarkerCharacter(getMurk());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_MUSK_APPLIED", getMilkers()));
					}
				};
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать",
						"Тебе остается только ждать возвращения Мёрка...",
						CAPTIVE_DAY_1_LUNCH);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Позавтракать",
						"Сделать так, как говорит Мёрк, и съесть похлебку..."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_EAT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться",
						"Отказаться есть эту отвратительную жижу..."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_REFUSE"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			Main.game.getPlayer().applyFoodConsumed(15);
		}
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
				return new Response("Ждать", "У тебя нет другого выбора, кроме как ждать в колодках...", CAPTIVE_DAY_1_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON"));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Раскрой рот пошире",
						"Сделать как говорит Мёрк, и проглотить трансформирующее зелье..."
								+getObedienceResponseDescription(10),
						CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON_SWALLOW"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Сопротивляться",
						"Держать рот на замке и попытаться не выпить трансформирующее зелье!"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON_RESIST"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Восстановиться", "Трансформации подошли к концу, и теперь ты можешь начать приходить в себя...", CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Раскрой рот пошире",
						"Ты не можешь удержаться от желания отсосать пульсирующий член Мёрка, но это еще не значит, что ты должна признаться, что влюбилась в него..."
								+getObedienceResponseDescription(5),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkCaptiveBlowjob, true);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Полюбить член",
						"Подыграть Мёрку, сказав его члену, что ты в него влюбилась и жаждешь разделить с ним свой первый поцелуй."
								+getObedienceResponseDescription(15),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkCaptiveBlowjob, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_END = new DialogueNode("Завершено", "Теперь, когда он набил твой живот своим горячим мускусным семенем, Мёрку хватит...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Скованная цепями", "Ты снова оказываешься в цепях...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 1 (Max. obedience = 60) --------- //
	
	
	
	
	// --------- START OF DAY 2 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_2_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Повиноваться",
						"Зайди в свою доильную стойку, чтобы Мёрк мог закрепить твои оковы..."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_OBEY"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Сопротивляться", "Ты слишком послушна, чтобы даже помыслить о неповиновении приказу Мёрка!", null);
				}
				return new Response("Сопротивляться",
						"Попытаться сопротивляться, когда тебя запирают в доильной стойке..."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_RESIST"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
				return new Response("Ждать", "У тебя нет иного выбора, кроме как ждать в колодках...", CAPTIVE_DAY_2_MORNING) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(2);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_MORNING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(10*60 + Util.random.nextInt(30))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						"Поцеловать",
						"Сделать так, как говорит Мёрк, и поцеловать его мускулистый, покрытый спермой член."
							+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response(
						"Жадно целовать",
						"Говорить Мёрку, что ты влюблена в его член, пока целуешь его."
							+getObedienceResponseDescription(10),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS_EAGER"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==3) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Отказаться", "Ты настолько послушна, что не можешь заставить себя ослушаться Мёрка...", null);
				}
				return new Response(
						"Отказаться",
						"Отказаться целовать вонючий член Мёрка."
							+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS_REFUSE"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_POTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Жадно умолять",
						"Ты отчаянно жаждешь трансформации, поэтому с энтузиазмом умоляешь дать тебе испробовать зелье Мёрка..."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Неохотно умолять", "Ты слишком послушна, чтобы делать что-то другое, кроме как усердно выпрашивать зелье трансформации!", null);
				}
				return new Response("Неохотно умолять",
						"Ты отчаянно жаждешь трансформации, но это не значит, что стоит проявлять слишком много восторга...",
						CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_POTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isPlayerObeyingOrders(true)) {
					return new Response("Сосать член", "К этому моменту ты настолько послушна, что не можешь думать ни о чем, кроме как полностью подчиниться члену Мёрка!", null);
				}
				return new ResponseSex(
						"Сосать член",
						"Хотя ты и не хочешь признавать, что влюбилась в член Мёрка, ты всё равно жаждешь его вкуса..."
							+getObedienceResponseDescription(5),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_2_AFTER_BLOWJOB,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Подчиниться члену",
						"Сказать Мёрку, что ты безумно влюблена в его вкусный член и хочешь, чтобы он доминировал в твоем горле!"
							+getObedienceResponseDescription(10),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_2_AFTER_BLOWJOB,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION_BLOWJOB_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_BLOWJOB = new DialogueNode("Завершено", "Мёрку уже достаточно трахать твое горло...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "У тебя нет другого выбора, кроме как ждать в колодках...", CAPTIVE_DAY_2_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Позавтракать",
						"Сделать так, как говорит Мёрк, и съесть похлебку..."
							+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Отказаться", "Ты слишком послушна, чтобы даже помыслить о том, чтобы отказаться есть жижу!", null);
				}
				return new Response("Отказаться",
						"Отказаться есть эту отвратительную жижу..."
							+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_REFUSE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_2_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			Main.game.getPlayer().applyFoodConsumed(15);
		}
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
				return new Response("Ждать", "У тебя нет другого выбора, кроме как ждать в колодках...", CAPTIVE_DAY_2_AFTERNOON) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(0);
						if(Main.game.isAnalContentEnabled()) {
							milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						} else {
							milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON", getMilkers()));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
			playerGrewVagina = false;
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(17*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Жадно умолять",
						"Ты отчаянно жаждешь трансформации, поэтому с энтузиазмом умоляешь дать тебе испробовать зелье Мёрка..."
								+getObedienceResponseDescription(5),
								CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						boolean vagina = Main.game.getPlayer().hasVagina();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
						if(!vagina && Main.game.getPlayer().hasVagina()) {
							playerGrewVagina = true;
						}
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Неохотно умолять", "Ты слишком послушна, чтобы делать что-то другое, кроме как усердно выпрашивать зелье трансформации!", null);
				}
				return new Response("Неохотно умолять",
						"Ты отчаянно жаждешь трансформации, но это не значит, что стоит проявлять слишком много восторга...",
						CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						boolean vagina = Main.game.getPlayer().hasVagina();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
						if(!vagina && Main.game.getPlayer().hasVagina()) {
							playerGrewVagina = true;
						}
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ощупывание", "Мерк некоторое время ощупывает твое новое тело...", CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(playerGrewVagina), true);
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Тренировка", "Мерк переходит к финальной стадии твоего обучения покорности...", CAPTIVE_DAY_2_EVENING_TRAINING_START);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING_START = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60 + Util.random.nextInt(30))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Умолять",
						"Отчаянно умолять Мерка трахнуть тебя вместо доильного аппарата."
								+getObedienceResponseDescription(10),
								CAPTIVE_DAY_2_EVENING_TRAINING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Молчать", "Ты слишком покорна, чтобы игнорировать Мерка, и понимаешь, что не можешь думать ни о чем, кроме как умолять его трахнуть тебя!", null);
				}
				return new Response("Молчать",
						"Несмотря на непреодолимую похоть, ты отказываешься умолять Мерка трахнуть тебя."
								+getObedienceResponseDescription(-10),
								CAPTIVE_DAY_2_EVENING_TRAINING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START_REFUSE"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-5));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed()+(60*30));
			GameCharacter milker = getMilkers().get(3);
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подчиниться",
						"Согласиться с Мерком, что ты — покорная шлюха-доярка, которую нужно трахать его жирным членом."
							+getObedienceResponseDescription(10),
						CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Сопротивляться", "Ты слишком покорна, чтобы ослушаться приказов Мерка!", null);
				}
				return new Response("Сопротивляться",
						"Сделать всё возможное, чтобы подавить желание подчиниться Мерку, и просто молчать."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_REFUSE"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			GameCharacter milker = getMilkers().get(3);
			getMurk().fillCumToMaxStorage();
			if(Main.game.isAnalContentEnabled()) {
				milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			} else {
				milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			}
			if(Main.game.getPlayer().hasPenis()) {
				Main.game.getPlayer().addDirtySlot(InventorySlot.PENIS);
			}
			if(Main.game.getPlayer().hasVagina()) {
				Main.game.getPlayer().addDirtySlot(InventorySlot.VAGINA);
			}
			Main.game.getPlayer().addDirtySlot(InventorySlot.ANUS);
			Main.game.getPlayer().addDirtySlot(InventorySlot.LEG);
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Скованная цепями", "Ты снова оказываешься в цепях...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 2 (max obedience = 100) --------- //
	
	
	
	
	// --------- START OF DAY 3 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_3_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Повиноваться",
						"Зайди в свою доильную стойку, чтобы Мёрк мог закрепить твои оковы..."
							+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_OBEY"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Сопротивляться", "Ты слишком послушна, чтобы даже помыслить о неповиновении приказу Мёрка!", null);
				}
				return new Response("Сопротивляться",
						"Попытаться сопротивляться, когда тебя запирают в доильной стойке..."
								+ getObedienceResponseDescription(-10),
						CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_RESIST"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
				return new Response("Ждать", "Ждать, пока Мерк вернется с твоим последним трансформирующим зельем...", CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Жадно целовать",
						"Вы отчаянно жаждете трансформации, поэтому с вожделением целуете член Мёрка и признаетесь ему, что влюбились в него..."
								+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Неохотно поцеловать", "Ты слишком послушна, чтобы делать что-то другое, кроме как усердно выпрашивать зелье трансформации!", null);
				}
				return new Response("Неохотно поцеловать",
						"Ты отчаянно жаждешь трансформации, но это не значит, что стоит проявлять слишком много восторга...",
						CAPTIVE_DAY_3_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_POTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Доен", "Мёрк принимается подключать вас к доильному аппарату...", CAPTIVE_DAY_3_MILKING);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_MILKING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.STOMACH, InventorySlot.PENIS)); // No vagina as Murk is going to fuck it first
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasVagina()) {
				if(index==1) {
					return new Response("Выпить",
							"Вы настолько возбуждены, что не можете думать ни о чем, кроме как заглотить преобразующую жидкость..."
								+ getObedienceResponseDescription(10),
							CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_DRINK"));
							Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
							Map<String, String> effects = CaptiveTransformation.FEMININE_PUSSY_FINAL.getEffects(Main.game.getPlayer());
							for(Entry<String, String> entry : effects.entrySet()) {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
										+ "</p>"
										+ entry.getValue());
							}
						}
					};
				}
				
			} else {
				return CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF.getResponse(responseTab, index);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Влюблена",
						"Признаться Мёрку, что вы влюбились в его член, и умолять его трахнуть вас!"
							+ getObedienceResponseDescription(25),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.HUMPING,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_FIRST_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_FIRST_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
					}
					@Override
					public void postSexInitEffects() {
						Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_FIRST_SEX = new DialogueNode("Завершено", "Мёрк закончил дарить вам ваш первый настоящий опыт с его жирным, мускусным членом...", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().hasVagina()) {
				Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
				applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_FIRST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Доен", "Мёрк оставляет вас на попечение доильного аппарата и вальяжно уходит проверить остальных доярок...", CAPTIVE_DAY_3_AFTER_SEX_MILKED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SEX_MILKED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SEX_MILKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Полностью подчиниться",
						Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
							?"Сказать Мёрку, что вы его покорная сучка-доярка и вам нужно, чтобы он вас сломал!"
							:"Скажи Мёрку, что ты грязная сучка-доярка и обожаешь его член!"
								+ getObedienceResponseDescription(25),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getMurk(), SexSlotMilkingStall.HUMPING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								2,
								null,
								true),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SEX_MILKED_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
						applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
					}
					@Override
					public void postSexInitEffects() {
						if(Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()) {
							Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SECOND_SEX = new DialogueNode("Завершено", "Мёрк закончил своё «второе свидание» со своим жирным мускусным членом...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SECOND_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Время обеда", "Мёрк оставляет тебя доиться, пока сам идет готовить обед...", CAPTIVE_DAY_3_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(Math.max(Main.game.getHourOfDay()+1, 13)*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Позавтракать",
						"Сделать так, как говорит Мёрк, и съесть похлебку..."
								+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) { // Should be impossible...
				return new Response("Отказаться", "Ты слишком послушна, чтобы даже помыслить о том, чтобы отказаться есть жижу!", null);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			GameCharacter milker = Util.randomItemFrom(getMilkers());
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
			getMurk().returnToHome();
			Main.game.getPlayer().applyFoodConsumed(15);
		}
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
				return new Response("Доен", "Наслаждайся ощущением дойки, ожидая возвращения Мёрка...", CAPTIVE_DAY_3_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			spawnRat(false, true);
			getCharacters(false).get(0).setGenericName("gang-member");
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Умолять о члене",
						UtilText.parse(getCharacters(false), "Умолять о [npc.namePos] крысином члене, чтобы [npc.she] начал тебя трахать.")
								+ getObedienceResponseDescription(10),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SEX", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(
								getCharacters(false).get(0), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharacters(false), "[npc.Name] кажется, что [npc.sheHas] выжал из тебя всё на [npc.her] денег, и он заканчивает секс...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX", getCharacters(false)));
			banishRats();
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
				return new Response("Доен", "Наслаждайся ощущением дойки, ожидая, пока Мёрк вернется снова...", CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			spawnRat(false, true);
			getCharacters(false).get(0).setGenericName("gang-member");
			getCharacters(false).get(0).addFetish(Fetish.FETISH_SADIST);
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(19*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Снова оттрахана",
						UtilText.parse(getCharacters(false), "[npc.Name], кажется, вовсе не собирается тратить время на прелюдию и просто начинает трахать тебя...")
								+ getObedienceResponseDescription(10),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX_START", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getCharacters(false).get(0), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharacters(false), "[npc.Name] [npc.has] натрахался тобой досыта и решает закончить секс...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX", getCharacters(false)));
			banishRats();
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
				return new Response("Доен", "Наслаждайся ощущением дойки, ожидая, пока Мёрк вернется снова...", CAPTIVE_DAY_3_AFTERNOON_END);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA, InventorySlot.NIPPLE, InventorySlot.STOMACH, InventorySlot.PENIS));
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(Math.max(22, Main.game.getHourOfDay()+1)*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подчиниться", "Опустись на колени перед Мёрком и умоляй его оттрахать тебя.", CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT);
				
			} else if(index==2) {
				return new Response("Сбежать", "Мысль о попытке побега даже не посещает твой послушный разум доярки...", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				boolean allFours = Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs();
				return new ResponseSex(
						allFours
							?"Предстать перед ней"
							:"Развести ноги",
						(allFours
							?"Представь свою киску своему Господину и скажи ему, что его член — твоя единственная истинная любовь!"
							:"Разведи ноги перед своим Господином и скажи ему, что его член — твоя единственная истинная любовь!")
							+ getObedienceResponseDescription(25),
						true,
						false,
						getBasicSexManager(
								allFours
									?SexPosition.ALL_FOURS
									:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getMurk(),
										allFours
											?SexSlotAllFours.HUMPING
											:(Main.game.getPlayer().isVisiblyPregnant()
												?SexSlotLyingDown.MISSIONARY
												:SexSlotLyingDown.MATING_PRESS))),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(),
										allFours
											?SexSlotAllFours.ALL_FOURS
											:SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true),
								allFours
									?null
									:new InitialSexActionInformation(getMurk(),
											Main.game.getPlayer(),
											TongueMouth.KISS_START,
											false,
											true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkMaster, true);
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
					}
					@Override
					public void postSexInitEffects() {
						Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
						if(Main.game.getPlayer().hasPenis()) {
							Main.game.getPlayer().applyOrgasmCumEffect();
							if(!allFours) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.CHEST);
							}
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_END = new DialogueNode("Завершено", "Мёрк вдоволь развлекся, набивая твою утробу своим спермотой...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Скованная цепями", "Ты снова оказываешься в цепях...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 3 --------- //
	
	
	
	
	// --------- NIGHT --------- //
	
	
	public static final DialogueNode CAPTIVE_NIGHT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			for(GameCharacter milker : getMilkers()) {
				((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
			}
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())==0) {
				return Main.game.getMinutesUntilTimeInMinutes(01*60)*60; // First night
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())==0
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "DAY_0_SLEEP"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_WARNING"));
			sb.append(incrementPlayerObedience(0));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
				if(stage>=4) {
					if(Main.game.isBadEndsEnabled()) {
						return new Response("Спать",
								"Заснуть...<br/>[style.boldBadEnd(ПЛОХОЙ ФИНАЛ:)] Не сумев сбежать третий раз подряд, ты окончательно капитулируешь перед Мёрком и примешь свою новую жизнь в качестве одной из его доярок!",
								CAPTIVE_NIGHT_SLEEP) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_BAD_END;
							}
						};
						
					} else {
						return new Response("Спать", "Заснуть...", CAPTIVE_NIGHT_SLEEP_RESCUED);
					}
				}
				return new Response("Спать", "Заснуть...", CAPTIVE_NIGHT_SLEEP);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveWashed)) {
					return new Response("Умыться", "Ты уже умывалась этим вечером...", null);
				}
				return new Response("Умыться", "Использовать мелководный ручей, чтобы омыть тело.", CAPTIVE_NIGHT) {
					@Override
					public int getSecondsPassed() {
						return 10*60;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveWashed, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
						StringBuilder sb = new StringBuilder();
						GameCharacter player = Main.game.getPlayer();
						
						player.setHealth(player.getAttributeValue(Attribute.HEALTH_MAXIMUM));
						player.setMana(player.getAttributeValue(Attribute.MANA_MAXIMUM));
						
						sb.append(player.washAllOrifices(false));
						player.calculateStatusEffects(0);
						player.cleanAllDirtySlots(true);
						player.cleanAllClothing(false, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_WASH", getCharacters(false)));
						Main.game.getTextStartStringBuilder().append(sb.toString());
					}
				};
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut)) {
					return new Response("Окликнуть", "Мерк не обратит на тебя внимания, если ты снова закричишь. Придется ждать до завтрашнего вечера...", null);
				}
				return new Response("Окликнуть", "Окликнуть Мерка...", CAPTIVE_CALL_OUT);
				
			} else if(index==4) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Сломать замок", "Ты слишком послушна, чтобы даже помышлять о порче имущества Мерка!", null);
				}
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)>=PhysiqueLevel.THREE_POWERFUL.getMinimumValue()) {
					return new Response("Сломать замок", "Использовать грубую физическую силу, чтобы вырваться из оков...", CAPTIVE_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Сломать замок", "Тебе не хватает сил, чтобы взломать замок...<br/>[style.italicsMinorBad(Требуется как минимум "+PhysiqueLevel.THREE_POWERFUL.getMinimumValue()+" "+Attribute.MAJOR_PHYSIQUE.getName()+"...)]", null);
				
			} else if(index==5) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Взломать замок (Заклинание)", "Ты слишком послушна, чтобы даже помышлять о порче имущества Мерка!", null);
				}
				if(Main.game.getPlayer().hasSpell(Spell.FIREBALL)
						|| Main.game.getPlayer().hasSpell(Spell.ICE_SHARD)
						|| Main.game.getPlayer().hasSpell(Spell.SLAM)) {
					return new Response("Взломать замок (Заклинание)",
							"Потратить время на концентрацию своей арканы, пытаясь преодолеть чары рабского ошейника и сотворить заклинание, чтобы разбить замок на колодках.",
							CAPTIVE_BROKEN_FREE) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK_SPELL", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Сломать замок",
						"Ты не знаешь заклинания, подходящего для взлома замка...<br/>[style.italicsMinorBad(Требуется знание одного из следующих заклинаний: "+Spell.FIREBALL.getName()+"; "+Spell.ICE_SHARD.getName()+"; "+Spell.SLAM.getName()+".)]",
						null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_NIGHT_SLEEP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getHourOfDay()>0) {
				return Main.game.getMinutesUntilTimeInMinutes(Main.game.getHourOfDay()+1*60)*60;
			}
			return Main.game.getMinutesUntilTimeInMinutes(01*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_SLEEP", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Проснуться", "Ты просыпаешься следующим утром...", getSleepNode());
			}
			return null;
		}
	};
	
	
	
	// --------- MISCELLANEOUS DIALOGUES --------- //
	
	
	public static final DialogueNode BAD_END = new DialogueNode("[style.boldBadEnd(Плохая концовка: Дойка Мёрка)]", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.setBadEnd("Дойка Мёрка");
			
			Main.game.getPlayer().setName(new NameTriplet("Похотливая дойка"));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "BAD_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Конец...", "[style.italicsBadEnd(С таким финалом твоего путешествия нить пророчества обрывается. Загрузи сохраненную игру, чтобы восстановить плетение судьбы, или продолжай жить в обреченном мире, который ты создал.)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH"));
			
			if(Main.game.getPlayer().isVaginaEggLayer()) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END_EGGS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isVaginaEggLayer()) {
					return new Response("Защищать яйца!", "Защити свои яйца от этих крыс!", CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						}
					};
				} else {
					return new Response("Отдых", "Ты проводишь некоторое время, приходя в себя после пережитого...", CAPTIVE_GIVE_BIRTH_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 24*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отдых", "Ты проводишь некоторое время, приходя в себя после пережитого...", CAPTIVE_GIVE_BIRTH_FINISHED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH_FINISHED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getNpc(Shadow.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_INITIAL_FINISHED", getCharacters(false)));
			sb.append(CAPTIVE_NIGHT.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_NIGHT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_LAY_EGGS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_LAY_EGGS"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отдых", "Ты проводишь некоторое время, приходя в себя после пережитого...", CAPTIVE_LAY_EGGS_FINISHED) {
					@Override
					public void effects() {
						Main.game.getNpc(Silence.class).returnToHome();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_LAY_EGGS_FINISHED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getNpc(Shadow.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_LAY_EGGS_INITIAL_FINISHED", getCharacters(false)));
			sb.append(CAPTIVE_NIGHT.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_NIGHT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Атаковать",
						"Теперь, когда ты свободна от цепей, ты наконец-то можешь напасть на Мерка!",
						(NPC) getMurk(),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[murk.speech(Ты за это заплатишь!)] — кричит Мерк, готовясь к бою."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Подчиниться",
						"Сделай то, что говорит Мерк, и подчинись ему..."
								+getObedienceResponseDescription(10),
						CAPTIVE_BROKEN_FREE_SUBMIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_BROKEN_FREE_AFTER_SEX, "CAPTIVE_BROKEN_FREE_SUBMIT_HANDJOB", "CAPTIVE_BROKEN_FREE_SUBMIT_ORAL", "CAPTIVE_BROKEN_FREE_SUBMIT_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public String getDescription() {
			return "Мерк на время закончил с тобой...";
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Спать", "Совершенно измотанная сексуальным обслуживанием Мерка, ты вскоре засыпаешь...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Спать",
						"Совершенно измотанная сексуальным обслуживанием Мерка, ты вскоре засыпаешь...",
						getSleepNode());
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_CALL_OUT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Притвориться, что подавилась", "Ты слишком послушна, чтобы даже помыслить о лжи Мёрку!", null);
				}
				return new Response("Притвориться, что подавилась", "Притвориться, что ты подавилась, чтобы обманом заставить [murk.name] снять с тебя ошейник...", CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_CHOKE", getCharacters(false)));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasTraitActivated(Perk.CONVINCING_REQUESTS) && !isPlayerObeyingOrders(false)) {
					return new Response("Соблазнить",
							UtilText.parse(getMurk(),
									"Ты недостаточно хороша в соблазнении, чтобы попытаться обманом заставить [npc.name] снять твой ошейник...<br/>[style.italicsMinorBad(Требуется активный черта '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"(черта должна быть активна.)]"),
							null);
				}
				return new Response("Соблазнить",
						isPlayerObeyingOrders(false)
							?"Сказать Мёрку, что ты так жаждешь секса, что даже не можешь спать..."
							:"Сказать Мёрку, что ты жаждешь секса, пытаясь обманом заставить его снять твой ошейник...<br/>[style.italicsMinorGood(Разблокировано при наличии активной черты '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' trait activated.)]",
						CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_SEDUCE", getCharacters(false)));
						getMurk().returnToHome();
						Main.game.getPlayer().setLocation(getMurk(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_CALL_OUT_RELEASED = new DialogueNode("", "", true) {
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("Сразиться", "Ты слишком послушна, чтобы даже задуматься о сопротивлении Мёрку!", null);
				}
				return new ResponseCombat("Сразиться",
						UtilText.parse(getMurk(), "Теперь, когда ты свободна от ошейника, ты наконец-то можешь напасть на [npc.name]!"),
						null,
						(NPC) getMurk(),
						Util.newArrayListOfValues(getMurk()),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[npc.speech(Т-ты за это заплатишь!)] [npc.name] в панике кричит, размахивая своей битой."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_RELEASED_AFTER_SEX, "CAPTIVE_CALL_OUT_RELEASED_HANDJOB", "CAPTIVE_CALL_OUT_RELEASED_ORAL", "CAPTIVE_CALL_OUT_RELEASED_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX = new DialogueNode("Завершено", "Мурк с тобой закончил...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Заперта", "Позволь Мурку снова запереть тебя в колодки...", CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP);
				
			} else if(index==2) {
				return new Response("Составить компанию", "Предложить Мурку переспать, что даст тебе шанс незаметно ускользнуть и сбежать...", CAPTIVE_RELEASED_OFFER_COMPANY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Спать", "Заснуть...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Спать", "Заснуть...", getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ускользнуть", "Воспользоваться этой возможностью, чтобы незаметно ускользнуть и попытаться сбежать...", CAPTIVE_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_SLIP_AWAY", getCharacters(false)));
						restoreInventories();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Остаться", "Остаться с Мурком до тех пор, пока тебя не запрут обратно в стойло для дойки...", CAPTIVE_RELEASED_OFFER_COMPANY_STAY);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY_STAY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_STAY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(true,
						CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX,
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_HANDJOB",
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_ORAL",
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Спать", "Заснуть...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Спать", "Заснуть...", getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPE_FIGHT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_VICTORY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Туннели", "Направиться обратно через туннели и выбраться из Крысиных нор.", CAPTIVE_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_VICTORY_ESCAPING", getCharacters(false)));
						restoreInventories();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPE_FIGHT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_DEFEAT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_AFTER_DEFEAT_SEX, "DEFEAT_SEX_HANDJOB", "DEFEAT_SEX_ORAL", "DEFEAT_SEX_START");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_AFTER_DEFEAT_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getMurk(), "[npc.NameHasFull] на данный момент с тобой закончил...");
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_AFTER_DEFEAT_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Спать", "Совершенно измотанная битвой и последующим сексом, ты вскоре погружаешься в сон...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Спать",
						"Совершенно измотанная битвой и последующим сексом, ты вскоре погружаешься в сон...",
						getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_NIGHT_SLEEP_RESCUED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 3*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_SLEEP_RESCUED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_ESCAPING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сбежать", "Следуйте за Shadow и Silence, пока они выводят вас из Крысиных нор.", ESCAPING) {
					@Override
					public void effects() {
						RatWarrensCaptiveDialogue.restoreInventories();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setObedience(0);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ESCAPING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "ESCAPING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Присоединяйтесь к Shadow и Silence, чтобы последовать за констеблем Adams к ближайшему посту Enforcer.", RatWarrensDialogue.POST_CAPTIVITY_SWORD_RAID) {
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
