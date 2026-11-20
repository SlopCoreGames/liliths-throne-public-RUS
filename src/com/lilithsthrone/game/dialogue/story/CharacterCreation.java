package com.lilithsthrone.game.dialogue.story;

import java.io.File;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CharacterCreation {

	public static final int TIME_TO_NAME = 120;
	public static final int TIME_TO_APPEARANCE = 60;
	public static final int TIME_TO_CLOTHING = 30;
	public static final int TIME_TO_BACKGROUND = 150;
	public static final int TIME_TO_JOB = 150;
	public static final int TIME_TO_SEX_EXPERIENCE = 150;
	public static final int TIME_TO_FINAL_CHECK = 150;

	public static SpellSchool getStartingTomeSpellSchool() {
		if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 1) {
			return SpellSchool.EARTH;
		} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
			return SpellSchool.AIR;
		} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 3) {
			return SpellSchool.WATER;
		}
		return SpellSchool.FIRE;
	}
	
	public static SpellSchool getStartingDemonstoneSpellSchool() {
		if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
			return SpellSchool.EARTH;
		} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 3) {
			return SpellSchool.AIR;
		} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 0) {
			return SpellSchool.WATER;
		}
		return SpellSchool.FIRE;
	}

	public static final DialogueNode CHARACTER_CREATION_START = new DialogueNode("Отказ от ответственности", "", true) {

		@Override
		public String getContent() {
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Согласен", "Вы согласны с тем, что вы достигли совершеннолетия для просмотра порнографических материалов, и даете согласие на то, чтобы вас подвергли воздействию графического содержания.", ALPHA_MESSAGE);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ALPHA_MESSAGE = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Версия " + Main.VERSION_NUMBER + " | <b style='color:" + PresetColour.BASE_YELLOW_LIGHT.toWebHexString() + ";'>"+Main.VERSION_DESCRIPTION+"</b>";
		}
		
		@Override
		public String getContent() {
			return Main.getPatchNotes();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Начать", "Приступить к созданию персонажа.", CHOOSE_APPEARANCE){
					@Override
					public void effects() {
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
						Main.game.getPlayer().calculateStatusEffects(0);
						Main.game.getPlayer().setHealthPercentage(1);
						Main.game.getPlayer().setManaPercentage(1);
						getDressed();
						resetBodyAppearance();
						
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("Неизвестно", "Неизвестно", "Неизвестно"));
						Main.game.getPlayer().setSurname("");
						BodyChanging.setTarget(Main.game.getPlayer());
					}
				};
				
			} else if (index == 2) {
				return new Response("Начать (Импорт)", "Импортировать персонажа из предыдущей версии для использования при запуске игры.", IMPORT_CHOOSE) {
					@Override
					public void effects() {
						Main.game.getPlayerCell().resetInventory();
					}
				};
			}
			return null;
		};
	};

	public static void resetBodyAppearance() {
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		Main.game.getNpc(Lilaya.class).setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_BROWN), true);
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN), true);
		Main.game.getPlayer().setBreastShape(BreastShape.ROUND);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue());
		
		Main.game.getPlayer().setFacialHair(BodyHair.ZERO_NONE);
		resetFemininityAppearance();
		
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}
	
	public static void resetFemininityAppearance() {
		switch(Main.game.getPlayer().getFemininity()) {
			case MASCULINE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPenisSize(PenisLength.TWO_AVERAGE.getMedianValue()+3);
				break;
			case MASCULINE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPenisSize(PenisLength.TWO_AVERAGE);
				break;
			case ANDROGYNOUS:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				if(Main.game.getPlayer().hasPenis()) {
					Main.game.getPlayer().setPenisSize(PenisLength.ONE_TINY);
				}
				if(Main.game.getPlayer().hasVagina()) {
					Main.game.getPlayer().setBreastSize(CupSize.A);
				}
				break;
			case FEMININE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.THREE_TRIMMED);
				Main.game.getPlayer().setBreastSize(CupSize.C);
				break;
			case FEMININE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setPubicHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setBreastSize(CupSize.DD);
				break;
		}
	}
	
	public static void setGenderFemale() {
		Femininity fem = Femininity.FEMININE;
		switch(BodyChanging.getTarget().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case MASCULINE:
				fem = Femininity.FEMININE;
				break;
			case MASCULINE_STRONG:
				fem = Femininity.FEMININE_STRONG;
				break;
			default:
				break;
		}
		BodyChanging.getTarget().setBody(Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, false);
		BodyChanging.getTarget().setFemininity(fem.getMedianFemininity());
		
		if(BodyChanging.getTarget().isPlayer()) {
			getDressed();
			CharacterCreation.resetBodyAppearance();
		}
	}
	
	public static void setGenderMale() {
		Femininity fem = Femininity.MASCULINE;
		switch(BodyChanging.getTarget().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case FEMININE:
				fem = Femininity.MASCULINE;
				break;
			case FEMININE_STRONG:
				fem = Femininity.MASCULINE_STRONG;
				break;
			default:
				break;
		}
		BodyChanging.getTarget().setBody(Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, false);
		BodyChanging.getTarget().setFemininity(fem.getMedianFemininity());

		if(BodyChanging.getTarget().isPlayer()) {
			getDressed();
			CharacterCreation.resetBodyAppearance();
		}
	}
	
	private static void equipPiercings() {
		Colour colour1 = PresetColour.CLOTHING_BLACK_STEEL;
		Colour colour2 = PresetColour.CLOTHING_STEEL;

		if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE_STRONG) {
			colour1 = PresetColour.CLOTHING_PLATINUM;
			colour2 = PresetColour.CLOTHING_GOLD;
		} else if(Main.game.getPlayer().isFeminine()) {
			colour1 = PresetColour.CLOTHING_SILVER;
			colour2 = PresetColour.CLOTHING_SILVER;
		}
		
		Map<InventorySlot, AbstractClothing> pendingPiercings = new HashMap<>();
		
		// Ear piercings:
		if(Main.game.getPlayer().isPiercedEar()) {
			if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE_STRONG) {
				pendingPiercings.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_chain_dangle", colour1, false));
			} else if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE) {
				pendingPiercings.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", colour1, false));
			} else {
				pendingPiercings.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", colour1, false));
			}
		}
		
		// Lip piercings:
		if(Main.game.getPlayer().isPiercedLip()) {
			pendingPiercings.put(InventorySlot.PIERCING_LIP, Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", colour1, false));
		}
		
		// Navel piercings:
		if(Main.game.getPlayer().isPiercedNavel()) {
			if(Main.game.getPlayer().isFeminine()) {
				pendingPiercings.put(InventorySlot.PIERCING_STOMACH, Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", colour2, false));
			} else {
				pendingPiercings.put(InventorySlot.PIERCING_STOMACH, Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", colour2, false));
			}
		}

		// Nipples piercings:
		if(Main.game.getPlayer().isPiercedNipple()) {
			pendingPiercings.put(InventorySlot.PIERCING_NIPPLE, Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", colour2, false));
		}

		// Nose piercings:
		if(Main.game.getPlayer().isPiercedNose()) {
			if(Main.game.getPlayer().isFeminine()) {
				pendingPiercings.put(InventorySlot.PIERCING_NOSE, Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", colour1, false));
			} else {
				pendingPiercings.put(InventorySlot.PIERCING_NOSE, Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", colour1, false));
			}
		}

		// Penis piercings:
		if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isPiercedPenis()) {
			pendingPiercings.put(InventorySlot.PIERCING_PENIS, Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", colour2, false));
		}

		// Tongue piercings:
		if(Main.game.getPlayer().isPiercedTongue()) {
			pendingPiercings.put(InventorySlot.PIERCING_TONGUE, Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", colour1, false));
		}

		// Vagina piercings:
		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isPiercedVagina()) {
			pendingPiercings.put(InventorySlot.PIERCING_VAGINA, Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", colour2, false));
		}
		
		for(InventorySlot slot : InventorySlot.getPiercingSlots()) {
			AbstractClothing clothingCurrentlyInSlot = Main.game.getPlayer().getClothingInSlot(slot);
			
			if(pendingPiercings.get(slot)!=null){
				if(clothingCurrentlyInSlot==null || clothingCurrentlyInSlot.getClothingType()!=pendingPiercings.get(slot).getClothingType()) {
					Main.game.getPlayer().equipClothingFromNowhere(pendingPiercings.get(slot), slot, true, Main.game.getPlayer());
				}
				
			} else if(clothingCurrentlyInSlot!=null){
				Main.game.getPlayer().unequipClothingIntoVoid(slot, true, Main.game.getPlayer());
			}
		}
	}
	
	public static void getDressed() {
		getDressed(Main.game.getPlayer(), true);
	}
	
	public static void getDressed(GameCharacter character, boolean spawnClothingOnFloor) {
		character.resetInventory(false);
		Main.game.getPlayerCell().resetInventory();
		
		equipPiercings();
		
		switch(character.getFemininity()) {
			case MASCULINE_STRONG:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_RED, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_suit_jacket", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_GOLD, false), true, character);
				
				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case MASCULINE:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_SILVER, false), true, character);

				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case ANDROGYNOUS:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, character);
				if(character.getBreastRawSizeValue()!=0) {
					character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false), true, character);
				} else {
					Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false));
				}
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_RED, false), true, character);
				
				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case FEMININE:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_skater_dress", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_trainer_socks", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_PINK_LIGHT, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER, false), true, character);

				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case FEMININE_STRONG:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_slip_dress", PresetColour.CLOTHING_RED_BURGUNDY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED_BURGUNDY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_GOLD, false), true, character);

				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
			default:
				break;
		}
		
		if(character.isPlayer()
				&& ((character.getName(false).equals("James") || character.getName(false).equals("Jane") || character.getName(false).equals("Tracy")) && character.getSurname().equals("Bond"))) {
			character.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_western_kkp_western_kkp")));
		}
	}
	
	private static void generateClothingOnFloor(String clothingType, Colour colour) {
		generateClothingOnFloor(ClothingType.getClothingTypeFromId(clothingType), colour, null, null);
	}
	
	private static void generateClothingOnFloor(AbstractClothingType clothingType, Colour colour) {
		generateClothingOnFloor(clothingType, colour, null, null);
	}
	
	private static void generateClothingOnFloor(AbstractClothingType clothingType, Colour colour, Colour colour2, Colour colour3) {
		for(AbstractClothing clothing : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(clothing.getClothingType()==clothingType) {
				return;
			}
		}
		Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing(clothingType, colour, colour2, colour3, false));
	}
	
	private static void spawnClothingInArea() {
		switch(Main.game.getPlayer().getFemininity()) {
			case MASCULINE:
			case MASCULINE_STRONG:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_foot_trainers"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLUE_GREY, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_work_boots", PresetColour.CLOTHING_TAN);
				generateClothingOnFloor("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_sock_socks", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_eye_aviators", PresetColour.CLOTHING_BLACK_STEEL);
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);
				generateClothingOnFloor("innoxia_hand_gloves", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_head_cap", PresetColour.CLOTHING_BLUE);
				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torsoOver_ribbed_jumper", PresetColour.CLOTHING_GREY);
				generateClothingOnFloor("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_neck_tie", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_torsoOver_suit_jacket", PresetColour.CLOTHING_BLACK);
				break;
				
			case ANDROGYNOUS:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_foot_trainers"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_PURPLE_DARK, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_heels", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_groin_thong", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_RED);

				generateClothingOnFloor("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_yoga_pants", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_GREY);

				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_RED);
				
				generateClothingOnFloor("innoxia_head_cap", PresetColour.CLOTHING_BLUE);
				
				generateClothingOnFloor("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_blouse", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_cami_straps", PresetColour.CLOTHING_GREEN);
				
				generateClothingOnFloor("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_finger_ring", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_wrist_bangle", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_ankle_anklet", PresetColour.CLOTHING_SILVER);
				
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);
				break;
				
			case FEMININE:
			case FEMININE_STRONG:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_PURPLE_DARK);
				generateClothingOnFloor("innoxia_torsoOver_womens_winter_coat", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_groin_panties", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_groin_thong", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_groin_vstring", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_sock_thighhigh_socks", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_eye_aviators", PresetColour.CLOTHING_ROSE_GOLD);
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);

				generateClothingOnFloor("innoxia_foot_ankle_boots", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_TAN);
				generateClothingOnFloor("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_foot_heels", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_hand_elbow_length_gloves", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_head_headband", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_head_headband_bow"), PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK);

				generateClothingOnFloor("innoxia_leg_hotpants", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_leg_mini_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_skirt", PresetColour.CLOTHING_PINK);
				generateClothingOnFloor("innoxia_leg_yoga_pants", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_GREY);
				
				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_RED);
				
				generateClothingOnFloor("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_torso_feminine_short_sleeve_shirt"), PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_blouse", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_cami_straps", PresetColour.CLOTHING_GREEN);
				generateClothingOnFloor("innoxia_torso_long_sleeve_dress", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torso_short_croptop", PresetColour.CLOTHING_PINK);
				generateClothingOnFloor("innoxia_torso_virgin_killer_sweater", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_slip_dress", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_torso_skater_dress", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_wrist_bangle", PresetColour.CLOTHING_GOLD);
				generateClothingOnFloor("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD);
				break;
		}
	}
	
	public static final DialogueNode CHOOSE_APPEARANCE = new DialogueNode("Вечерняя прогулка", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "К тому времени, когда такси наконец подъезжает к Британскому музею, вы уже опаздываете почти на пять минут."
						+ "Целью вашего визита в Лондон является посещение открытия новой выставки вашей тети Лили."
							+ " и, когда вы спешите заплатить водителю и выходите из машины, вы надеетесь, что она еще не начала свою речь."
					+ "</p>"
					+ "<p>"
						+ "Уличные фонари зажигаются, освещая ваше окружение тусклым оранжевым светом."
						+ "Вам нужно всего лишь несколько минут, чтобы оказаться у входа в музей, где, к вашему разочарованию, вы видите, что образовалась небольшая очередь."
						+ "Не имея другого выбора, кроме как встать в очередь и ждать своей очереди, вы бросаете взгляд на большие стеклянные окна современного фасада здания, чтобы увидеть свое размытое отражение в стекле..."
					+ "</p>"
					+ "<br/>"
					
					+ CharacterModificationUtils.getStartDateDiv()
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getGenderChoiceDiv()
						
						+ CharacterModificationUtils.getFemininityChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "Вы будете называться <span style='color:"+Main.game.getPlayer().getGender().getColour().toWebHexString()+";'>"
								+UtilText.generateSingularDeterminer(Main.game.getPlayer().getGender().getName())+ " " + Main.game.getPlayer().getGender().getName()+"</span>.<br/>"
							+ "<i>Вы можете изменить все имена полов в меню настроек.</i>"
						+ "</div>"

						+ CharacterModificationUtils.getBirthdayChoiceDiv()
						
						+ CharacterModificationUtils.getOrientationChoiceDiv()
						
						+ CharacterModificationUtils.getPersonalityChoiceDiv(false)
						
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Ждите своей очереди и надейтесь, что мероприятие еще не началось.", CHOOSE_NAME) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_NAME;
					}
					@Override
					public void effects() {
//						getDressed();
					}
				};
			}
//			else if (index == 0) {
//				return new Response("Back", "Return to the main menu.", OptionsDialogue.MENU);
//			}
			return null;
		}
	};
	
	public static final DialogueNode CHOOSE_NAME = new DialogueNode("Ночной выход в свет", "", true) {

		boolean unsuitableName = false, unsuitableSurname = false;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[npcMale.speech("+(Main.game.getPlayer().isFeminine()?"Мисс":"Сэр")+",)] швейцар окликает вас — очевидно, он уже закончил с остальными в очереди, [npcMale.speech(у вас есть приглашение?)]</p><p>Вы отворачиваетесь от стекла и делаете шаг вперед с улыбкой. [pc.speech(Да, оно прямо здесь... э-э... погодите...)]</p><p>Залезая в свою "+(Main.game.getPlayer().isFeminine()?"purse":"pocket")+", вы чувствуете, как сердце начинает бешено колотиться, обнаружив, что приглашения там нет. [pc.speech(Нет, нет, нет! Должно быть, я оставила его в такси!)]</p><p>[npcMale.speech(Ну, не волнуйтесь,)] отвечает мужчина, [npcMale.speech(если вы назовете меня по имени, я смогу проверить, есть ли вы в списке.)]</p><p>вздохнув с облегчением, вы называете мужчине свое имя...</p><br/><div class='container-full-width' style='text-align:center;'><div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'><i>Ваше имя может быть установлено как три значения: ваше мужское имя, андрогинное имя и женское имя. Ваше имя будет автоматически меняться в зависимости от уровня вашей женственности.</i><br/><p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Имя: </p></form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameMasculineInput' style=' color:"+PresetColour.MASCULINE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getMasculine())+ "'></form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameAndrogynousInput' style=' color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getAndrogynous())+ "'></form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameFeminineInput' style=' color:"+PresetColour.FEMININE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getFeminine())+ "'><br/><p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Фамилия: </p><form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form></div><br/><i>Длина вашего имени должна составлять от 2 до 32 символов. Нельзя использовать квадратные скобки или точки. (Фамилию можно оставить пустой.)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Недопустимое имя.</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Недопустимая фамилия.</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Продолжить", "Используйте это имя и перейдите к следующему этапу создания персонажа."){
					@Override
					public int getSecondsPassed() {
						if (unsuitableName || unsuitableSurname)  {
							return super.getSecondsPassed();
						}
						return TIME_TO_APPEARANCE;
					}
					@Override
					public void effects() {
						List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
						List<String> namesList = new ArrayList<>();
						for(String s : fieldsList) {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
										|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+")) {
									unsuitableName = true;
								} else {
									unsuitableName = false;
									namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							}
						}
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+"))) {
								unsuitableSurname = true;
							} else {
								unsuitableSurname = false;
							}
						}
						if (unsuitableName || unsuitableSurname)  {
							Main.game.setContent(new Response("" ,"", CHOOSE_NAME));
							
						} else {
							Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());

							Main.game.getPlayerCell().resetInventory();
							Main.game.getPlayer().moveToAdjacentMatchingCellType(false, PlaceType.MUSEUM_LOBBY);
							Main.game.setContent(new Response("" ,"", CHOOSE_ADVANCED_APPEARANCE));
							getDressed();
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Случайный", "Сгенерируйте случайное имя на основе вашего пола.", CHOOSE_NAME){
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 16
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+")))
								unsuitableSurname = true;
							else {
								unsuitableSurname = false;
							}
						}
						if(!unsuitableSurname) {
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
						}

						Main.game.getPlayer().setName(Name.getRandomTriplet(Subspecies.HUMAN));
					}
				};
				
			} else if (index == 3) {
				return new Response("Случайная фамилия", "Сгенерируйте случайную фамилию.", CHOOSE_NAME){
					@Override
					public void effects() {
						List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
						List<String> namesList = new ArrayList<>();
						for(String s : fieldsList) {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 16
										|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+"))
									unsuitableName = true;
								else {
									unsuitableName = false;
									namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							}
						}
						if(!unsuitableName) {
							Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
						}
						
						Main.game.getPlayer().setSurname(Name.getSurname(Main.game.getPlayer()));
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернитесь к выбору пола.", CHOOSE_APPEARANCE) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_NAME;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE = new DialogueNode("В музее", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[pc.speech(Это "+(Main.game.getPlayer().getSurname().length()!=0?"[pc.surname], [pc.name] [pc.surname]":"[pc.name]")+",)]"
						+ " ты говоришь, нетерпеливо глядя на лист с именами, который держит мужчина, когда он перебирает его."
					+ "</p>"
					+ "<p>"
						+ "Наконец, ты видишь, как его палец останавливается на твоем имени, и с улыбкой он отходит в сторону и машет рукой, приглашая тебя вперед."
						+ " [npcMale.speech(Желаю вам хорошего вечера, "+(Main.game.getPlayer().getSurname().length()!=0
								?(Main.game.getPlayer().isFeminine()?"Мисс":"Мистер")+" [pc.surname]"
								:(Main.game.getPlayer().isFeminine()?"Мисс":"Сэр"))+".)]"
					+ "</p>"
					+ "<p>"
						+ "Благодарив его, ты спешишь через вход, и через несколько мгновений оказываешься в огромном центральном вестибюле музея."
						+ "На балконах верхнего этажа висят большие баннеры; их крупный шрифт гордо заявляет, что это 'Выставка Аккадской империи: Вечер открытия'."
						+ "На дальнем конце большого зала ты видишь толпы людей, окружающих большую сцену, и ты вздыхаешь с облегчением, заметив, что она в данный момент пуста."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Ух... Я все-таки успел...)]"
					+ "</p>"
					+ "<p>"
						+ "Поскольку речь Лили о начале кажется такой же запоздалой, как и твоя, ты решаешь подойти к ближайшему зеркалу, чтобы убедиться, что ты выглядишь презентабельно..."
					+ "</p>"
					+ "<br/>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Внешность</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>"
					+ "<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Вы можете изменить свою внешность, войдя в каждое из подменю ниже.</i>"
					+ "</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Ваша одежда немного в беспорядке после того, как вы сюда побежали. Приведите себя в порядок, прежде чем перейти к главному этапу.",
						InventoryDialogue.INVENTORY_MENU) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_CLOTHING;
					}
					@Override
					public void effects() {
						equipPiercings();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
					}
				};
				
			} else if (index == 2) {
				return new Response("Ядро", "Войдите в меню кастомизации для всех основных аспектов вашего тела.", CHOOSE_ADVANCED_APPEARANCE_CORE);
				
			} else if (index == 3) {
				return new Response("Лицо", "Войдите в меню кастомизации для аспектов, связанных с вашим лицом.", CHOOSE_ADVANCED_APPEARANCE_FACE);
				
			} else if (index == 4) {
				return new Response("Волосы", "Введите меню настройки для вашей прически.", CHOOSE_ADVANCED_APPEARANCE_HAIR);
				
			} else if (index == 5) {
				return new Response("Грудь", "Введите меню настройки для вашей груди.", CHOOSE_ADVANCED_APPEARANCE_BREASTS);
				
			} else if (index == 6) {
				return new Response("Ягодицы & Бёдра", "Введите меню настройки для аспектов, связанных с вашей ягодицей, бёдрами и анусом.", CHOOSE_ADVANCED_APPEARANCE_ASS);
				
			} else if (index == 7) {
				return new Response((Main.game.getPlayer().hasPenis()?"Пенис":"Киска"), "Введите меню настройки для аспектов, связанных с вашей "+(Main.game.getPlayer().hasPenis()?"пенис":"вагина")+".", CHOOSE_ADVANCED_APPEARANCE_GENITALS);
				
			}  else if (index == 8) {
				return new Response("Макс", "Введите меню настройки для макияжа.", CHOOSE_ADVANCED_APPEARANCE_COSMETICS);
				
			} else if (index == 9) {
				return new Response("Пирсинг", "Введите меню настройки для пирсинга.", CHOOSE_ADVANCED_APPEARANCE_PIERCINGS);
				
			} else if (index == 10) {
				return new Response("Татуировки", "Введите меню настройки для татуировок.", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);
				
			} else if (index == 11) {
				return new Response("Дополнительные волосы", "Введите меню настройки для волос на лице, лобковой области и теле.", CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR);
				
			} else if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь в меню предпочтений контента.", CHOOSE_NAME) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_APPEARANCE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_CORE = new DialogueNode("Внешний вид тела", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти опции могут быть изменены позже в игре.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getHeightChoiceDiv(true)
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.HUMAN, BodyCoveringType.HUMAN, "Цвет кожи", "Цвет кожи, покрывающей ваше тело.", true, false, false)
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getBodySizeChoiceDiv()
						
						+ CharacterModificationUtils.getMuscleChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "Ваши значения мышечной массы и размера тела приводят к тому, что ваша внешность будет:<br/>"
							+ "<b style='color:"+Main.game.getPlayer().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false))+"</b>"
						+ "</div>"
					
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_FACE = new DialogueNode("Внешность лица", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти параметры могут быть изменены позже в игре.</i>"
					+ "</div>"

					+ CharacterModificationUtils.getLipSizeDiv()
					
					+ CharacterModificationUtils.getLipPuffynessDiv()

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Main.game.getPlayer().getEyeType().getRace(), BodyCoveringType.EYE_HUMAN, "Цвет радужки", "Цвет радужной оболочки ваших глаз.", true, false, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_HAIR = new DialogueNode("Внешность волос", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти настройки можно изменить позже в игре.</i>"
					+ "</div>"

					+ CharacterModificationUtils.getKatesDivHairLengths(false, "Длина волос", "Выберите длину ваших волос.")
					
					+ CharacterModificationUtils.getKatesDivHairStyles(false, "Прическа", "Выберите прическу. Некоторые прически недоступны при короткой длине волос.")

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Main.game.getPlayer().getHairType().getRace(), BodyCoveringType.HAIR_HUMAN, "Цвет волос", "Цвет ваших волос.", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_BREASTS = new DialogueNode("Внешность груди", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти настройки можно изменить позже в игре.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getBreastSizeDiv()
					
					+ CharacterModificationUtils.getBreastShapeDiv()
					
					+ CharacterModificationUtils.getNippleSizeDiv()
					
					+ CharacterModificationUtils.getAreolaeSizeDiv()
					
					+ CharacterModificationUtils.getNipplePuffynessDiv()
					
					+ CharacterModificationUtils.getSelfTransformLactationDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свои настройки и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_ASS = new DialogueNode("Внешность асса", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти настройки можно изменить позже в игре.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getAssSizeDiv()
					
					+ CharacterModificationUtils.getHipSizeDiv()
					
					+ CharacterModificationUtils.getBleachedAnusDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свои настройки и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_GENITALS = new DialogueNode("Внешность гениталий", "", true) {
		
		@Override
		public String getLabel() {
			if(Main.game.getPlayer().hasPenis()) {
				return "Внешний вид пениса";
			} else {
				return "Внешний вид влагалища";
			}
		}
		
		@Override
		public String getHeaderContent() {
			if(Main.game.getPlayer().hasPenis()) {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>Все эти настройки можно изменить позже в игре.</i>"
						+ "</div>"
							
							+ CharacterModificationUtils.getPenisSizeDiv()
							
							+ CharacterModificationUtils.getTesticleSizeDiv()
							
							+ CharacterModificationUtils.getSelfTransformCumProductionDiv();
				
			} else {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>Все эти настройки можно изменить позже в игре.</i>"
						+ "</div>"
	
							+ CharacterModificationUtils.getVaginaCapacityDiv()
							
							+ CharacterModificationUtils.getLabiaSizeDiv()
							
							+ CharacterModificationUtils.getClitorisSizeDiv();
				
			}
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свои настройки и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_PIERCINGS = new DialogueNode("Пирсинг", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти настройки можно изменить позже в игре.</i>"
					+ "</div>"
						
					+CharacterModificationUtils.getKatesDivPiercings(true);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_TATTOOS = new DialogueNode("Татуировки", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Позже в игре вы можете получить зачарованные и светящиеся татуировки. Однако, на данный момент, ваш выбор татуировок ограничен более обычными вариантами.</i>"
					+ "</div>"
					+CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD = new DialogueNode("Секреты Суккуби", "-", true) {

		@Override
		public String getLabel() {
			return "Добавить татуировку: "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName());
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Применить", "Вам нужно выбрать тип татуировки, добавить текст или добавить счетчик, чтобы сделать татуировку!", null);
					
				} else {
					return new Response("Применить", 
							UtilText.parse(BodyChanging.getTarget(), "Добавить эту татуировку."), CHOOSE_ADVANCED_APPEARANCE_TATTOOS) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("Сохранить/Загрузить", "Сохранить/Загрузить настройки татуировок.", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("Назад", "Решить не делать эту татуировку и вернуться к главному экрану выбора.", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_COSMETICS = new DialogueNode("Косметика", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Все эти настройки можно изменить позже в игре.</i>"
					+ "</div>"
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые руж) используются для придания цвета щекам, чтобы придать более молодой вид, и для подчеркивания скул.", true, false)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Помада", "Помада используется для придания цвета, текстуры и защиты губ.", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Тушь для глаз наносится вокруг контуров глаз, чтобы помочь определить форму или подчеркнуть различные черты.", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются, чтобы сделать глаза носителя более заметными или привлекательными.", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окраски и защиты ногтей на ваших [pc.hands].", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окраски и защиты ногтей на ваших [pc.feet].", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свои выборы и вернитесь в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR = new DialogueNode("Волосы на теле", "", true) {
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
												+ "<i>Все эти параметры могут быть изменены позже в игре.</i>"
											+ "</div>");
			
			if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false, Race.NONE, Main.game.getPlayer().getBodyHairCoveringType(), "Волосы на теле", "Это волосы, которые покрывают все области, кроме головы.", false, false, false));
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы на теле", "Это волосы, которые покрывают все области, кроме головы.", "Все дополнительные опции для волос тела отключены. Вы не увидите никаких дополнительных волос."));
				
				return UtilText.nodeContentSB.toString();
			}
			
			if(Main.game.isFacialHairEnabled()) {
				if (Main.game.isFemaleFacialHairEnabled()) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Растительность на лице", "Волосы на теле, которые находятся на лице."));
				} else {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Растительность на лице", "Волосы на теле, которые находятся на лице. Женские персонажи не могут отращивать усы."));
				}
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Растительность на лице", "Волосы на теле, которые находятся на лице. Женские персонажи не могут отращивать усы.", "Усы в настоящее время отключены в настройках. Вы не увидите никаких усов, пока они отключены."));
			}
			
			if(Main.game.isPubicHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Лобковые волосы", "Волосы на теле, которые находятся в области гениталий; расположены на и вокруг ваших половых органов и промежности."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Лобковые волосы", "Волосы на теле, которые находятся в области гениталий; расположены на и вокруг ваших половых органов и промежности.", "Лобковые волосы в настоящее время отключены в настройках. Вы не увидите никаких лобковых волос, пока они отключены."));
			}
			
			if(Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getKatesDivUnderarmHair(false, "Волосы под мышками", "Волосы на теле, которые находятся в подмышечных впадинах."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы под мышками", "Волосся, найденное в твоих подмышках.", "Волосся под мышками в настоящее время отключено в настройках. Ты не увидишь никакого содержимого с волосами под мышками, пока оно отключено."));
			}
			
			if(Main.game.isAssHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "Волосы на заднице", "Волосся на теле вокруг твоего анала."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы на заднице", "Волосся на теле вокруг твоего анала.", "Волосся на анусе в настоящее время отключено в настройках. Ты не увидишь никакого содержимого с волосами на анусе, пока оно отключено."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтверди свои выборы и вернись в меню предпочтений контента.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static String getCheckingClothingDescription() {
		StringBuilder sb = new StringBuilder();

		File dir = new File("res/");
		if(!dir.exists()) {
			sb.append("<p style='text-align:center;'>"
						+ "[style.italicsBad(Игра не может прочитать папку 'res', и как следствие, важные предметы одежды будут отсутствовать! Пожалуйста, ознакомься с разделом 'ПРОПАВШИЕ ПАПКИ' в README.txt перед продолжением!)]"
					+ "</p>");
		}
		
		sb.append("<div class='container-full-width' style='background:transparent;'>"
					+ "<p>"
						+ "На главной сцене, похоже, нет никаких признаков активности, поэтому, имея немного больше времени, вы решаете немного подправить свою одежду."
						+ "В конце концов, это большой вечер для Лили, и вы хотите, чтобы она увидела, что вы приложили некоторые усилия к своему внешнему виду."
					+ "</p>"
					+ "<p>"
						+ "Поворачиваясь то в одну, то в другую сторону, чтобы лучше рассмотреть себя в зеркале, вы начинаете замечать, как "+(Main.game.getPlayer().isFeminine()?"горячая":"красивый")+"вы выглядите сегодня вечером..."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Почему я чувствую себя такой возбужденной?)]"
					+ "</p>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Выберите, что вы решили надеть в музей.</i><br/>"
						+ "<i>Вам нужно будет надеть какую-то обувь, а также одежду, которая скрывает ваши гениталии и грудь, прежде чем вы сможете продолжить.</i>"
					+ "</div>"
				+ "</div>");
		
		return sb.toString();
	}
	
	public static void moveNPCIntoPlayerTile() {
		if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasVagina())) {
			Main.game.getNpc(PrologueMale.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			
		} else {
			Main.game.getNpc(PrologueFemale.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			Main.game.getNpc(PrologueFemale.class).addStatusEffect(StatusEffect.PROMISCUITY_PILL_PROLOGUE, 60*60*24*3); // 3 days
		}
	}
	
	public static void moveNPCOutOfPlayerTile() {
		Main.game.getNpc(PrologueMale.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
		Main.game.getNpc(PrologueFemale.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	public static boolean femalePrologueNPC() {
		return Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasPenis());
	}
	
	public static final DialogueNode CHOOSE_BACKGROUND = new DialogueNode("В музее", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "Удовлетворенная своей внешностью, ты отворачиваешься от зеркала и начинаешь идти к главной сцене."
						+ "С каждым шагом, который ты делаешь, ты необъяснимо начинаешь испытывать все большее и большее возбуждение, и к тому времени, когда ты едва прошла половину пути к толпе посетителей,"
							+(Main.game.getPlayer().hasPenis()
									?"ты с трудом сдерживаешь себя от того, чтобы не получить эрекцию."
									:"ты чувствуешь, как твоя пизда становится мокрой от возбуждения.")
					+ "</p>"
					+ "<p>"
						+ "Зайдя за ближайшую колонну, ты потряхиваешь головой, пытаясь избавиться от грязных мыслей, которые начинают проникать в твой разум."
						+ "Когда ты прислоняешься к холодной стене и делаешь глубокий вдох, внезапно прерывает твои мысли голос,");
			
			if(!femalePrologueNPC()) {
				UtilText.nodeContentSB.append("[prologueMale.speech(Отдыхаешь от толпы тоже?)]</p><p>Поворачиваясь, ты видишь высокого, красивого мужчину, который, вероятно, всего на пару лет старше тебя, и он улыбается тебе самой очаровательной улыбкой, которую ты когда-либо видела.Прежде чем ты успеваешь осознать, что делаешь, твои глаза бегают по всем [unit.size] его мужественного, мускулистого тела, и ты только с большим трудом можешь сдержать себя от того, чтобы не выпустить из себя отчаянный маленький стон.</p><p>[pc.thought(Сосредоточься, [pc.name], сосредоточься!)] ты думаешь, пытаясь вести себя как можно более непринужденно, когда улыбаешься незнакомцу перед тобой.</p><p>[pc.speech(На самом деле,) ты говоришь, [pc.speech(я только что приехал. Я думал, что опоздаю, но похоже, что ничего еще не началось.)]</p><p>[prologueMale.speech(Ах, ты, должно быть, только что пропустил объявление,) отвечает он, [prologueMale.speech(вступление откладывается на полчаса.)] Я пытался засидеться в той толпе там, но я не историк, и большая часть разговора довольно скучна...)]</p><p>[pc.speech(Haha,)] ты смеешься, отчаянно пытаясь не представить, как он выглядит голым, [pc.speech(Я знаю <i>точно</i>, что ты имеешь в виду. Моя тетя - дама, которая произносит вступительную речь, и каждый раз, когда я встречаю ее друзей из музея, я не могу следить за их разговорами. Ну, кроме Артура. Он ближе к нашему возрасту, и с ним очень легко общаться.)]</p><p>[prologueMale.speech(Ха! Ты знаешь Артура? Я здесь по его приглашению. Он и я давние друзья,) жизнерадостно отвечает мужчина, и его улыбка заставляет твое сердце учащенно биться.[prologueMale.speech(Я [prologueMale.name], кстати, рад познакомиться с тобой "+(Main.game.getPlayer().isFeminine()?"Госпожа ...?":"Господин ...?")+")]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Точно,)] ты отвечаешь, пожимаешь ему протянутую руку, пытаясь не думать о том, насколько силен и доминирующе его хват.) [pc.speech(Я [pc.Name].)]"
						+ "</p>"
						+ "<p>"
							+ "Вы и [prologueMale.name] продолжаете разговаривать друг с другом, пока ждете начала презентации."
							+ "Вскоре разговор переходит на тему работы, и вы узнаете, что он пилот авиакомпании, базирующийся в аэропорту на окраине города."
							+ "Затем разговор переходит на то, чем вы занимаетесь, и вы разговариваете об этом некоторое время..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("[prologueFemale.speech(Делаете перерыв от толпы тоже?)]</p><p>Поворачиваясь, вы видите красивую женщину, которая выглядит примерно в твоем возрасте, и она улыбается тебе так, как ты еще никогда не видел.Прежде чем ты успеваешь понять, что делаешь, твои глаза бегают по всему телу [unit.size], и ты только что успеваешь остановиться, чтобы не вырвать из себя вожделенное стон.</p><p>[pc.thought(Фокусируйся [pc.name], фокусируйся!)] ты думаешь, пытаясь вести себя как можно более непринужденно, когда улыбаешься незнакомке перед тобой.</p><p>[pc.speech(На самом деле,) ты говоришь, [pc.speech(Я только что приехал. Я думал, что опоздаю, но похоже, что ничего еще не началось.)] </p><p>[prologueFemale.speech(Ах, ты, должно быть, только что пропустил объявление,) она отвечает, [prologueFemale.speech(вступительная речь откладывается на полчаса.Я пытался постоять в той толпе там, но я не историк, и большая часть разговора довольно скучна...)]</p><p>[pc.speech(Haha,)] ты смеёшься, отчаянно пытаясь не представить, как она выглядит голой, [pc.speech(Я знаю <i>точно</i>, что ты имеешь в виду. Моя тетя - это та самая женщина, которая произносит вступительную речь, и каждый раз, когда я встречаю её друзей из музея, я не могу понять их разговоры. Ну, кроме Артура. Он ближе к нашему возрасту, и с ним очень легко общаться и весело разговаривать.)]</p><p>[prologueFemale.speech(О! Ты знаешь Артура? Я здесь по его приглашению, на самом деле. Он и я давно знакомы,)] женщина жизнерадостно отвечает, и её улыбка заставляет твое сердце биться быстрее. [prologueFemale.speech(Я [prologueFemale.name], кстати, рада познакомиться с тобой "+(Main.game.getPlayer().isFeminine()?"Госпожа ...?":"Господин ...?")+")]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Точно,)] ты отвечаешь, пожимаешь ей протянутую руку, пытаясь не думать о том, насколько нежная и мягкая её кожа. [pc.speech(Я [pc.Name].)]"
						+ "</p>"
						+ "<p>"
							+ "Ты и [prologueFemale.name] продолжаете разговаривать, пока ждете начала презентации."
							+ " Вскоре разговор переходит на работу, и ты узнаёшь, что она учится на врача и учится здесь, в городском университете."
							+ " Затем разговор переходит на то, чем ты занимаешься, и ты говоришь об этом немного..."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Назад", "Вернуться к выбору одежды.") {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_BACKGROUND;
					}
					@Override
					public void effects() {
						moveNPCOutOfPlayerTile();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
					}
				};
				
			} else if (index == 1) {
				return new Response("Выбрать профессию", "Перейти к экрану выбора профессии.", BACKGROUND_SELECTION_MENU) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_JOB;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACKGROUND_SELECTION_MENU = new DialogueNode("В музее", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("<div class='container-full-width'>"
									+ "<h6 style='text-align:center'>Выбор профессии</h6>"
									+ "<p style='text-align:center'>Нажмите на иконку рядом с профессией, которую вы хотите, а затем выберите 'Продолжить'.</p>"
								+ "</div>");

			UtilText.nodeContentSB.append("<div class='container-full-width'>");
			for(Occupation history : Occupation.getAvailableHistories(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width'>"
							+"<div class='container-full-width' style='margin:0;padding:0;'>"
								+ "<h6 style='color:"+history.getAssociatedPerk().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(history.getName(Main.game.getPlayer()))+"</h6></div><div class='container-full-width' style='margin:0 8px; width: calc(10% - 16px);'><div id='OCCUPATION_" + history + "' class='fetish-icon full"
									+ (Main.game.getPlayer().getHistory()==history
										? " owned' style='border:2px solid " + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
										: " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>")
									+ "<div class='fetish-icon-content'>"+history.getAssociatedPerk().getSVGString(Main.game.getPlayer())+"</div>"
								+ "</div>"
							+ "</div>"
							+"<div class='container-full-width' style='margin:0 8px; width: calc(90% - 16px);'>"
								+ "<p>"
									+ history.getDescription(Main.game.getPlayer())
								+ "</p>"
							+ "</div>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", CHOOSE_BACKGROUND) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_JOB;
					}
				};
				
			} else if (index == 1) {
				if(Main.game.getPlayer().getHistory().getAssociatedPerk()==null) {
					return new Response("Продолжить", "Вам нужно выбрать профессию, прежде чем продолжить!", null);
				} else {
					return new Response("Продолжить", femalePrologueNPC()?"Расскажите [prologueFemale.name], чем вы занимаетесь.":"Расскажите [prologueMale.name], чем вы занимаетесь.", CHOOSE_SEX_EXPERIENCE) {
						@Override
						public int getSecondsPassed() {
							return TIME_TO_SEX_EXPERIENCE;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().getVirginityLossMap().replaceAll((k, v) ->
								(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC
									|| (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && !Main.game.getPlayer().isFeminine()))
									?new SimpleEntry<>("", "your girlfriend")
									:new SimpleEntry<>("", "your boyfriend"));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode CHOOSE_SEX_EXPERIENCE = new DialogueNode("Начать", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
			switch(Main.game.getPlayer().getHistory()) {
				case ATHLETE:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я профессиональный спортсмен,)]"
							+ " you explain,"
							+ " [pc.speech(и я провожу большую часть времени, тренируясь и участвуя в соревнованиях.)]");
					break;
				case BUTLER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю горничной в очень влиятельной семье здесь в городе,)]"
							+ " you explain,"
							+ " [pc.speech(но я взяла сегодня вечером выходной, чтобы я могла присутствовать на презентации Лили.)]");
					break;
				case CHEF:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я - шеф-повар в ресторане, который находится всего в нескольких шагах отсюда,)]"
							+ " you explain,"
							+ " [pc.speech(но я взяла сегодня вечером выходной, чтобы я могла присутствовать на презентации Лили.)]");
					break;
				case CONSTRUCTION_WORKER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я - строитель,)]"
							+ " you explain,"
							+ " [pc.speech(и в настоящее время я управляю крупным проектом на окраине города.)]");
					break;
				case MAID:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю горничной в очень влиятельной семье здесь в городе,)]"
							+ " you explain,"
							+ " [pc.speech(но я взяла сегодня вечером выходной, чтобы я могла присутствовать на презентации Лили.)]");
					break;
				case MUSICIAN:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я - член городского оркестра,)]"
							+ " you explain,"
							+ " [pc.speech(и я также занимаюсь частным музыкальным обучением.)]");
					break;
				case OFFICE_WORKER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю в одном из офисов в центре города,)]"
							+ " you explain,"
							+ " [pc.speech(в основном занимаюсь административной работой и бумажной волокитой.)]");
					break;
				case SOLDIER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я в армии,)]"
							+ " you explain,"
							+ " [pc.speech(я в отпуске до конца недели, а потом обратно в казармы.)]");
					break;
				case STUDENT:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я студент городского университета,)]"
							+ " you explain,"
							+ " [pc.speech(хотя я еще не совсем решил, что выбрать в качестве своей специальности.)]");
					break;
				case TEACHER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я учитель в местной средней школе,)]"
							+ " you explain,"
							+ " [pc.speech(но, поскольку это промежуточная оценка, я могу расслабиться на этой неделе.)]");
					break;
				case UNEMPLOYED:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я в настоящее время безработный,)]"
							+ " you explain,"
							+ " [pc.speech(я на самом деле думал о том, чтобы подать заявку на работу здесь, в музее.)]");
					break;
				case WRITER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я профессиональный автор,)]" // I write erotic novels...
							+ " you explain,"
							+ " [pc.speech(и в настоящее время жду ответа от моего издателя о моем последнем романе.)]");
					break;
				case ARISTOCRAT:
					UtilText.nodeContentSB.append(
							"[pc.speech(Мне не нужно беспокоиться о работе,)]"
							+ " you explain,"
							+ " [pc.speech(мое семейное поместье обеспечивает все необходимые мне доходы, поэтому я провожу время в путешествиях и наслаждаюсь жизнью.)]");
					break;
				case TOURIST:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я здесь в отпуске,)]"
							+ " you explain,"
							+ " [pc.speech(и пока я здесь, в Великобритании, я не хочу думать о работе.)]");
					break;
				default:
					break;
			}
			UtilText.nodeContentSB.append("</p>");
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Пока вы двое продолжаете разговаривать, сначала о работе, а затем о более общих темах, вы начинаете чувствовать себя все более возбужденным."
							+ " Более того, вы начинаете замечать, что [prologueFemale.namePos] щеки начинают краснеть, и она продолжает жадно смотреть на ваше тело, когда думает, что вы не смотрите на нее."
						+ "</p>"
						+ "<p>"
							+ "В качестве последнего доказательства того, что она так же возбуждена, как и вы, она начинает открыто говорить о своей сексуальной жизни."
							+ "Для начала, вы немного удивлены ее открытостью, но чем больше она говорит, тем комфортнее вы чувствуете себя, разговаривая с этой незнакомой вам женщиной о сексе."
						+ "</p>"
						+ "<p>"
							+ "И так, после разговора с [prologueFemale.name], который длился не более десяти минут, вы рассказываете ей каждую мелочь о своих сексуальных переживаниях..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "По мере того, как вы продолжаете разговаривать, сначала о работе, а затем о более общих темах, вы начинаете чувствовать себя все более и более возбужденным."
							+ "Более того, вы начинаете замечать, что щеки [prologueMale.namePos] начинают краснеть, и он продолжает жадно смотреть на ваше тело, когда думает, что вы не смотрите."
						+ "</p>"
						+ "<p>"
							+ "В качестве последнего доказательства того, что он так же возбужден, как и вы, он начинает открыто говорить о своей сексуальной жизни."
							+ "Для начала, вы немного удивлены его открытостью, но чем больше он говорит, тем комфортнее вы чувствуете себя, разговаривая с этим незнакомым вам мужчиной о сексе."
						+ "</p>"
						+ "<p>"
							+ "И так, после разговора с [prologueMale.name], который длился не более десяти минут, вы рассказываете ему каждую мелочь о своих сексуальных переживаниях..."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='text-align:center;'>"
							+ "<i>Более сексуальный опыт приведет к увеличению порочности. (Вы можете увидеть свою порочность, а также другие атрибуты, в панели персонажа слева на экране.)"
							+ "<br/>"
							+ "Выбор '<span style='color:"+FetishDesire.FOUR_LOVE.getColour().toWebHexString()+";'>"+FetishDesire.FOUR_LOVE.getName()+"</span>'"
								+ " для любого фетиша приведет к тому, что ваш персонаж начнет игру с этим фетишем, в то время как остальные четыре желания просто определяют отношение вашего персонажа к этому фетишу.</i>"
						+ "</div>"
						+CharacterModificationUtils.getSexualExperienceDiv()
						+CharacterModificationUtils.getFetishChoiceDiv());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Как только вы будете довольны своим сексуальным опытом, переходите к последней части создания персонажа.", FINAL_CHECK) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_FINAL_CHECK;
					}
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenis()) {
							for(SexAreaOrifice ot : SexAreaOrifice.values()) {
								SexType st = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot);
								Main.game.getPlayer().resetVirginityLoss(st);
								st = new SexType(SexParticipantType.SELF, SexAreaPenetration.PENIS, ot);
								Main.game.getPlayer().resetVirginityLoss(st);
							}
							Main.game.getPlayer().setPenisVirgin(true);
							
						}
						if(!Main.game.getPlayer().hasVagina()) {
							for(SexAreaPenetration pt : SexAreaPenetration.values()) {
								SexType st = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt);
								Main.game.getPlayer().resetVirginityLoss(st);
								st = new SexType(SexParticipantType.SELF, SexAreaOrifice.VAGINA, pt);
								Main.game.getPlayer().resetVirginityLoss(st);
							}
							Main.game.getPlayer().setVaginaVirgin(true);
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к выбору фона.", BACKGROUND_SELECTION_MENU) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_SEX_EXPERIENCE;
					}
				};
				
			} else {
				return null;
			}
		}
	};

	private static void applyGameStart() {
		CharacterModificationUtils.resetImpossibeSexExperience();
		
		Main.getProperties().addRaceDiscovered(Subspecies.HUMAN);
		Main.game.getPlayer().setGenderIdentity(Main.game.getPlayer().getGender());
		
		Main.game.getNpc(Lilaya.class).setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);

		Main.game.getNpc(Lilaya.class).setBirthday(LocalDateTime.of(Main.game.getPlayer().getBirthday().getYear()-22+18, Main.game.getNpc(Lilaya.class).getBirthMonth(), Main.game.getNpc(Lilaya.class).getDayOfBirth(), 12, 0));
		
		Main.game.clearTextStartStringBuilder();
		Main.game.clearTextEndStringBuilder();

		Main.game.setWeatherInSeconds(Weather.MAGIC_STORM, 5*60*60);
		
		Main.game.getPlayerCell().resetInventory();

		Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_clothing_keys"), false);
	}

	private static void applySkipPrologueStart(boolean imported) {
		Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Lilaya.class));
		Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Rose.class));
		
		Main.getProperties().addRaceDiscovered(Main.game.getNpc(Lilaya.class).getSubspecies());
		Main.getProperties().addRaceDiscovered(Main.game.getNpc(Rose.class).getSubspecies());
		
		Main.game.applyStartingDateChange();
		if(!imported) {
			Main.game.getPlayer().setAgeAppearanceDifference(-Game.TIME_SKIP_YEARS);
		}

		Main.game.getPlayer().addSpecialPerk(Perk.SPECIAL_PLAYER);
		
		moveNPCOutOfPlayerTile();
	}
	
	public static final DialogueNode FINAL_CHECK = new DialogueNode("Начать", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Как только вы будете довольны своей внешностью, нажмите кнопку 'Начать игру', чтобы начать!<br/>"
					+ "[style.colourGood(Это конец создания персонажа, поэтому продолжайте только после того, как вы будете довольны своими выборами!)]</i>"
				+ "</div>"
				+ "<br/>"
				+ "<div class='container-full-width'>"
					+ "<h5 style='text-align:center;'>Финальный внешний вид</h5>"
					+ Main.game.getPlayer().getBodyDescription()
				+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Начать игру", "Используйте этого персонажа и начните игру с самого начала, пытаясь найти Артура в музее.", PrologueDialogue.INTRO){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Пропустить пролог", "Начните игру и пропустите пролог.<br/><br/><i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Не рекомендуется для первого прохождения!</i>"){
					@Override
					public int getSecondsPassed() {
						return 60*60;
					}
					@Override
					public void effects() {
						
						Main.game.setRenderMap(true);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));
						
						Main.game.getPlayer().incrementMoney(5000);

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						Spell startingSpell = Spell.FIREBALL;
						switch(getStartingTomeSpellSchool()) {
							case AIR:
								startingSpell = Spell.POISON_VAPOURS;
								break;
							case EARTH:
								startingSpell = Spell.SLAM;
								break;
							case FIRE:
							case ARCANE:
								startingSpell = Spell.FIREBALL;
								break;
							case WATER:
								startingSpell = Spell.ICE_SHARD;
								break;
						}
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(startingSpell));
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						applyGameStart();
						applySkipPrologueStart(false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);

						Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
						Main.game.getPlayer().setLustNoText(Main.game.getPlayer().getRestingLust());
						
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к выбору фона.", CHOOSE_SEX_EXPERIENCE){
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_FINAL_CHECK;
					}
					@Override
					public void effects() {
						// Remove attribute gain sentences in the start game screen:
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	private static StringBuilder importSB;
	public static final DialogueNode IMPORT_CHOOSE = new DialogueNode("Импорт", "", true) {
		
		@Override
		public String getContent(){
			importSB = new StringBuilder();

			importSB.append("<p style='text-align:center;'>"
					+ "Эти персонажи загружаются из папки 'data/characters'."
					+ "Если вы хотите импортировать персонажа из предыдущей версии, выполните следующие шаги:<br/><br/>"
					+ "<b>1.</b> Откройте старую версию игры и экспортируйте своего старого персонажа (меню -> настройки -> экспорт).<br/>"
					+ "<b>2.</b> Скопируйте экспортированный файл .xml (в папке <i>data/characters</i> старой версии).<br/>"
					+ "<b>3.</b> Вставьте его в папку <i>data/characters</i> этой версии.<br/>"
					+ "<b>4.</b> Нажмите 'Обновить', и ваш старый файл персонажа должен появиться в списке ниже!<br/><br/>"
//					+ "(If it doesn't work, please let me know as a comment on my blog, and I'll get it fixed ASAP!)"
					+ "</p>"
					+ "<p>"
					+ "<table align='center'>"
					+ "<tr>"
					+ "<th></th>"
					+ "<th>Name</th>"
					+ "<th></th>"
					+ "</tr>");
			
			int i=1;
			for(File f : Main.getCharactersForImport()){
				importSB.append(getImportRow(i, f.getName()));
				i++;
			}

			importSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");

			return importSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Обновить", "Обновите эту страницу.", IMPORT_CHOOSE);
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню.", OptionsDialogue.MENU);
				
			} else {
				return null;
			}
		}
	};
	private static String getImportRow(int i, String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td>"
					+ i+"."
				+ "</td>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td><td><div class='saveLoadButton' id='IMPORT_CHARACTER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Загрузить</div></td></tr>";
	}

	private static boolean resetImportedCharacter = false;

	public static final DialogueNode START_GAME_WITH_IMPORT = new DialogueNode("Начать игру", "", true) {
		
		@Override
		public String getLabel() {
			return "Импортированный персонаж";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "<b>TODO:</b> Я включу возможность пройти полное создание персонажа с импортированными персонажами в какой-то момент!"
					+ "</p>"
					+ "<br/>"
					+"<details>"
						+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>Импорт Лог</summary>"
						+ Main.game.getCharacterUtils().getCharacterImportLog()
					+ "</details>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Внешность</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Начать", "Используйте этого персонажа и начните игру с самого начала.", INTRO_2_FROM_IMPORT){
					@Override
					public void effects() {
						if(resetImportedCharacter){
							resetPlayerCharacter();
						}
						Main.game.getPlayer().resetAllQuests();
						Main.game.getPlayer().getCharactersEncountered().clear();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Пропустить пролог", "Начните игру и пропустите пролог.<br/><br/><i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Не рекомендуется для первого прохождения!</i>"){
					@Override
					public void effects() {
						Main.game.setRenderMap(true);
						if(resetImportedCharacter){
							resetPlayerCharacter();
						}
						Main.game.getPlayer().incrementMoney(5000);

						Main.game.getPlayer().resetAllQuests();
						Main.game.getPlayer().getCharactersEncountered().clear();
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.FIREBALL));
						if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 1) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.SLAM));
						} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.POISON_VAPOURS));
						} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 3) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
						}
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						applyGameStart();
						applySkipPrologueStart(true);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else if (index == 5) {
				return new ResponseEffectsOnly(resetImportedCharacter
						?"Сбросить персонажа: <span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>ВКЛ</span>"
						:"Сбросить персонажа: <span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>ВЫКЛ</span>",
						"Сбрасывает опыт и Кредиты / Огни (Flames) до 0 и очищает весь ваш Инвентарь, за исключением экипированной одежды и оружия. " +
								"Заклинания и перки к заклинаниям также удаляются."){
					@Override
					public void effects(){
						resetImportedCharacter = !resetImportedCharacter;
					}
				};


			}
			// Throws error when going back and then resuming
//			else if (index == 0) {
//				return new Response("Back", "Return to new game screen.", OptionsDialogue.MENU);
//			}
			else {
				return null;
			}
		}
	};

	private static void resetPlayerCharacter(){
		PlayerCharacter player = Main.game.getPlayer();
		player.clearNonEquippedInventory(true);
		player.setEssenceCount(0);
		player.incrementExperience(player.getExperienceNeededForNextLevel(player.getLevel()), false);
		player.setLevel(1);
		player.resetSpells();
		player.resetPerksMap(false);
	}

	public static final DialogueNode INTRO_2_FROM_IMPORT = new DialogueNode("В Музее", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_2");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return PrologueDialogue.INTRO_2.getResponse(responseTab, index);
		}
	};
	
}
