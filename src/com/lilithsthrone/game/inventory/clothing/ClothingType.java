package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.84
 * @version 0.4.10.11
 * @author Innoxia
 */
public class ClothingType {
	
//	public static AbstractClothingType TORSO_SLEEVELESS_TURTLENECK = new AbstractClothingType(350,
//			"a",
//			false,
//			"sleeveless turtleneck",
//			"sleeveless turtlenecks",
//			"A feminine sleeveless sweater, with a high turtleneck.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_sleeveless_turtleneck",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							null,
//							null, null
//							),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null, null, null, null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
//	};
	
//	public static AbstractClothingType TORSO_KEYHOLE_CROPTOP = new AbstractClothingType(120,
//			"a",
//			false,
//			"keyhole crop top",
//			"keyhole crop tops",
//			"A small, sleeveless crop top with a stylish cutout that reveals some cleavage.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_keyhole_croptop",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							null,
//							null, null),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
//		
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the crop top.",
//					"You guide [npc.namePos] [npc.arms] through the crop top's sleeves as you pull it down over [npc.her] head.",
//					null,
//					"[npc.Name] pulls on the crop top.",
//					"[npc.Name] guides your [pc.arms] through the crop top's sleeves as [npc.she] pulls it down over your head.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your crop top.",
//					"You slide [npc.namePos] crop top up and over [npc.her] head.",
//					null,
//					"[npc.Name] pulls off [npc.her] crop top.",
//					"[npc.Name] slides your crop top up and over your head.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType TORSO_SHORT_CROPTOP = new AbstractClothingType(100,
//			"a",
//			false,
//			"short croptop",
//			"short croptops",
//			"A small, sleeveless croptop that leaves its wearer's stomach completely exposed.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_short_croptop",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							null,
//							null,
//							null),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES),
//							Util.newArrayListOfValues(ClothingAccess.CHEST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the crop top.",
//					"You guide [npc.namePos] [npc.arms] through the crop top's sleeves as you pull it down over [npc.her] head.",
//					null,
//					"[npc.Name] pulls on the crop top.",
//					"[npc.Name] guides your [pc.arms] through the crop top's sleeves as [npc.she] pulls it down over your head.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your crop top.",
//					"You slide [npc.namePos] crop top up and over [npc.her] head.",
//					null,
//					"[npc.Name] pulls off [npc.her] crop top.",
//					"[npc.Name] slides your crop top up and over your head.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType TORSO_FISHNET_TOP = new AbstractClothingType(100,
//			"a",
//			false,
//			"fishnet top",
//			"fishnet tops",
//			"A small fishnet top that leaves its wearer's stomach completely exposed, while not doing much to conceal anything else, either.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_fishnet_top",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							null,
//							null, null),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							null,
//							Util.newArrayListOfValues(ClothingAccess.CHEST), 
//							null)),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(
//					ItemTag.TRANSPARENT,
//					ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the fishnet top.",
//					"You guide [npc.namePos] [npc.arms] through the fishnet top's sleeves as you pull it down over [npc.her] head.",
//					null,
//					"[npc.Name] pulls on the fishnet top.",
//					"[npc.Name] guides your [pc.arms] through the fishnet top's sleeves as [npc.she] pulls it down over your head.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your fishnet top.",
//					"You slide [npc.namePos] fishnet top up and over [npc.her] head.",
//					null,
//					"[npc.Name] pulls off [npc.her] fishnet top.",
//					"[npc.Name] slides your fishnet top up and over your head.",
//					null, null, null);
//		}
//	};
	

//	public static AbstractClothingType TORSO_CAMITOP_STRAPS = new AbstractClothingType(200,
//			"a",
//			false,
//			"cami top",
//			"cami tops",
//			"A short cami top with straps that loop over the wearer's shoulders. It's short enough that the wearer's stomach is left on display.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_cami_straps",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							null,
//							null, null),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(ClothingAccess.CHEST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
//	};

//	public static AbstractClothingType TORSO_SKATER_DRESS = new AbstractClothingType(250,
//			"a",
//			false,
//			"skater dress",
//			"skater dresses",
//			"A sleeveless skater dress, held up by a pair of thin straps that loop over the wearer's shoulders.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_skater_dress",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(DisplacementType.PULLS_DOWN,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES),
//							Util.newArrayListOfValues(ClothingAccess.CHEST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the skater dress, tidying the skirt down before moving the straps into a comfortable position on your shoulders.",
//					"You pull the skater dress over [npc.namePos] head and down around [npc.her] torso, tidying the skirt before moving the straps to sit comfortably on [npc.her] shoulders.",
//					null,
//					"[npc.Name] pulls on the skater dress, tidying the skirt down before moving the straps into a comfortable position on [npc.her] shoulders.",
//					"[npc.Name] pulls the skater dress over your head and down around your torso, tidying the skirt before moving the straps to sit comfortably on your shoulders.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull your skater dress up over your head and take it off.",
//					"You pull [npc.namePos] skater dress up over [npc.her] head and take it off.",
//					null,
//					"[npc.Name] pulls [npc.her] skater dress up over [npc.her] head and takes it off.",
//					"[npc.Name] pulls your skater dress up over your head and takes it off.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if(dt==DisplacementType.PULLS_UP) {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull up the skirt of your skater dress.",
//						"You pull up the skirt of [npc.namePos] skater dress.",
//						null,
//						"[npc.Name] pulls up the skirt of [npc.her] skater dress.",
//						"[npc.Name] pulls up the skirt of your skater dress.",
//						null, null, null);
//			} else {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You shrug off the shoulder straps of your skater dress, before tugging it down to reveal your chest.",
//						"You slide the straps of [npc.namePos] skater dress down off [npc.her] shoulders, before tugging it down to reveal [npc.her] chest.",
//						null,
//						"[npc.Name] shrugs off the shoulder straps of [npc.her] skater dress, before tugging it down to reveal [npc.her] chest.",
//						"[npc.Name] slides the straps of your skater dress down off your shoulders, before tugging it down to reveal your chest.",
//						null, null, null);
//			}
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if(dt==DisplacementType.PULLS_UP) {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull your skater dress back down into its proper position.",
//						"You pull [npc.namePos] skater dress back down into its proper position.",
//						null,
//						"[npc.Name] pulls [npc.her] skater dress back down into its proper position.",
//						"[npc.Name] your skater dress back down into its proper position.",
//						null, null, null);
//			} else {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull up the top of your skater dress, before placing the straps over your shoulders.",
//						"You pull up the top of [npc.namePos] skater dress, before placing the straps over [npc.her] shoulders.",
//						null,
//						"[npc.Name] pulls up the top of [npc.her] skater dress, before placing the straps over [npc.her] shoulders.",
//						"[npc.Name] pulls up the top of your skater dress, before placing the straps over your shoulders.",
//						null, null, null);
//			}
//		}
//	};
	
//	public static AbstractClothingType TORSO_CORSET_DRESS = new AbstractClothingType(1250,
//			"a",
//			false,
//			"corset dress",
//			"corset dresses",
//			"An overbust corset, which is tied up and tightened by a series of strings running up the front."
//					+ " A long skirt is attached to the bottom rim, turning it into a dress.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_corset_dress",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			Util.newArrayListOfValues(
//					InventorySlot.STOMACH,
//					InventorySlot.CHEST),
//			ColourListPresets.JUST_BLACK,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_BLACK,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_BLACK,
//			ColourListPresets.ALL,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//		
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You slip into the corset dress, before tightening the strings on the front.",
//					"You guide [npc.name] into the corset dress, before tightening the strings on the front.",
//					null,
//					"[npc.Name] slips into the corset dress, before tightening the strings on the front.",
//					"[npc.Name] guides you into the corset dress, before tightening the strings on the front.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You untie the corset dress's strings and take it off.",
//					"You untie the strings on the front of [npc.namePos] corset dress, before taking it off.",
//					null,
//					"[npc.Name] unties [npc.her] corset dress's strings and takes it off.",
//					"[npc.Name] unties the strings on the front of your corset dress, before taking it off.",
//					null, null, null);
//		}
//	};

//	public static AbstractClothingType TORSO_VIRGIN_KILLER_SWEATER = new AbstractClothingType(100,
//			"a",
//			false,
//			"'Virgin-killer' sweater",
//			"'Virgin-killer' sweaters",
//			"A long, dress-like sweater, with a large portion cut out of the back.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_virgin_killer_sweater",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
//	};

//	public static AbstractClothingType TORSO_SLIP_DRESS = new AbstractClothingType(800,
//			"a",
//			false,
//			"slip dress",
//			"slip dresses",
//			"A long, silky, sleeveless dress.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_slip_dress",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(DisplacementType.PULLS_DOWN,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES),
//							Util.newArrayListOfValues(ClothingAccess.CHEST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You step into the slip dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
//					"You guide the slip dress up around [npc.namePos] [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
//					null,
//					"[npc.Name] steps into the slip dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
//					"[npc.Name] guides the slip dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You unzip your slip dress and wriggle out of it as it drops to your feet.",
//					"You unzip [npc.namePos] slip dress and pull it down off [npc.her] body and past [npc.her] feet.",
//					null,
//					"[npc.Name] unzips [npc.her] slip dress and wriggles out of it as it drops to [npc.her] feet.",
//					"[npc.Name] unzips your slip dress and pulls it down your body and past your feet.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if(dt==DisplacementType.PULLS_UP) {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull up the lower half of your slip dress.",
//						"You pull up the lower half of [npc.namePos] slip dress.",
//						null,
//						"[npc.Name] pulls up the lower half of [npc.her] slip dress.",
//						"[npc.Name] pulls up the lower half of your slip dress.",
//						null, null, null);
//			} else {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You shrug off the shoulder straps of your slip dress, before tugging it down to reveal your chest.",
//						"You slide the straps of [npc.namePos] slip dress down off [npc.her] shoulders, before tugging it down to reveal [npc.her] chest.",
//						null,
//						"[npc.Name] shrugs off the shoulder straps of [npc.her] slip dress, before tugging it down to reveal [npc.her] chest.",
//						"[npc.Name] slides the straps of your slip dress down off your shoulders, before tugging it down to reveal your chest.",
//						null, null, null);
//			}
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if(dt==DisplacementType.PULLS_UP) {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull your slip dress back down into its proper position.",
//						"You pull [npc.namePos] slip dress back down into its proper position.",
//						null,
//						"[npc.Name] pulls [npc.her] slip dress back down into its proper position.",
//						"[npc.Name] your slip dress back down into its proper position.",
//						null, null, null);
//			} else {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull up the top of your slip dress, before placing the straps over your shoulders.",
//						"You pull up the top of [npc.namePos] slip dress, before placing the straps over [npc.her] shoulders.",
//						null,
//						"[npc.Name] pulls up the top of [npc.her] slip dress, before placing the straps over [npc.her] shoulders.",
//						"[npc.Name] pulls up the top of your slip dress, before placing the straps over your shoulders.",
//						null, null, null);
//			}
//		}
//	};

//	public static AbstractClothingType TORSO_PLUNGE_DRESS = new AbstractClothingType(600,
//			"a",
//			false,
//			"plunge dress",
//			"plunge dresses",
//			"An elegant dress with a plunging v-neckline, perfect for showing off its wearer's cleavage.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_plunge_dress",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
//							Util.newArrayListOfValues(
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(DisplacementType.PULLS_DOWN,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES),
//							Util.newArrayListOfValues(ClothingAccess.CHEST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO_STOMACH_VISIBLE.getPresetInventorySlotList()),
//					new BlockedParts(DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//		
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You step into the plunge dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
//					"You guide the plunge dress up around [npc.namePos] [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
//					null,
//					"[npc.Name] steps into the plunge dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
//					"[npc.Name] guides the plunge dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You unzip your plunge dress and wriggle out of it as it drops to your feet.",
//					"You unzip [npc.namePos] plunge dress and pull it down off [npc.her] body and past [npc.her] feet.",
//					null,
//					"[npc.Name] unzips [npc.her] plunge dress and wriggles out of it as it drops to [npc.her] feet.",
//					"[npc.Name] unzips your plunge dress and pulls it down your body and past your feet.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if(dt==DisplacementType.PULLS_UP) {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull up the lower half of your plunge dress.",
//						"You pull up the lower half of [npc.namePos] plunge dress.",
//						null,
//						"[npc.Name] pulls up the lower half of [npc.her] plunge dress.",
//						"[npc.Name] pulls up the lower half of your plunge dress.",
//						null, null, null);
//			} else {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You shrug off the shoulder straps of your plunge dress, before tugging it down to reveal your chest.",
//						"You slide the straps of [npc.namePos] plunge dress down off [npc.her] shoulders, before tugging it down to reveal [npc.her] chest.",
//						null,
//						"[npc.Name] shrugs off the shoulder straps of [npc.her] plunge dress, before tugging it down to reveal [npc.her] chest.",
//						"[npc.Name] slides the straps of your plunge dress down off your shoulders, before tugging it down to reveal your chest.",
//						null, null, null);
//			}
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if(dt==DisplacementType.PULLS_UP) {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull your plunge dress back down into its proper position.",
//						"You pull [npc.namePos] plunge dress back down into its proper position.",
//						null,
//						"[npc.Name] pulls [npc.her] plunge dress back down into its proper position.",
//						"[npc.Name] your plunge dress back down into its proper position.",
//						null, null, null);
//			} else {
//				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//						"You pull up the top of your plunge dress, before placing the straps over your shoulders.",
//						"You pull up the top of [npc.namePos] plunge dress, before placing the straps over [npc.her] shoulders.",
//						null,
//						"[npc.Name] pulls up the top of [npc.her] plunge dress, before placing the straps over [npc.her] shoulders.",
//						"[npc.Name] pulls up the top of your plunge dress, before placing the straps over your shoulders.",
//						null, null, null);
//			}
//		}
//	};
//	
//	public static AbstractClothingType TORSO_LONG_SLEEVE_DRESS = new AbstractClothingType(400,
//			"a",
//			false,
//			"long-sleeved dress",
//			"long-sleeved dresses",
//			"A long-sleeved bodycon dress with a high neck.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_long_sleeve_dress",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK,
//									CoverableArea.ARMPITS),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			null,
//			null,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You step into the long-sleeved dress and pull it up around your torso. Once in place, you reach back and zip yourself up.",
//					"You guide the long-sleeved dress up around [npc.namePos] [npc.legs], before pulling it up around [npc.her] torso. Once it's in place, you zip [npc.herHim] up at the back.",
//					null,
//					"[npc.Name] steps into the long-sleeved dress, before pulling it up around [npc.her] torso. Once in place, [npc.she] reaches back to zip [npc.herself] up.",
//					"[npc.Name] guides the long-sleeved dress up around your [pc.legs], before pulling it up around your torso. One it's in place, [npc.she] zips you up at the back.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You unzip your long-sleeved dress and wriggle out of it as it drops to your feet.",
//					"You unzip [npc.namePos] long-sleeved dress and pull it down off [npc.her] body and past [npc.her] feet.",
//					null,
//					"[npc.Name] unzips [npc.her] long-sleeved dress and wriggles out of it as it drops to [npc.her] feet.",
//					"[npc.Name] unzips your long-sleeved dress and pulls it down your body and past your feet.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull up the lower half of your long-sleeved dress.",
//					"You pull up the lower half of [npc.namePos] long-sleeved dress.",
//					null,
//					"[npc.Name] pulls up the lower half of [npc.her] long-sleeved dress.",
//					"[npc.Name] pulls up the lower half of your long-sleeved dress.",
//					null, null, null);
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull your long-sleeved dress back down into its proper position.",
//					"You pull [npc.namePos] long-sleeved dress back down into its proper position.",
//					null,
//					"[npc.Name] pulls [npc.her] long-sleeved dress back down into its proper position.",
//					"[npc.Name] your long-sleeved dress back down into its proper position.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType TORSO_BODYCONZIP_DRESS = new AbstractClothingType(350,
//			"a",
//			false,
//			"frontal-zip dress",
//			"frontal-zip dresses",
//			"A tight fitting bodycon dress with a zip that runs the entire way up the front.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.COMMON,
//			null,
//			"clothing/torso_bodyconzip_dress",
//			null,
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
//							Util.newArrayListOfValues(CoverableArea.BACK),
//							null, null),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS),
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.UNZIPS,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.PENIS,
//									CoverableArea.STOMACH,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST,
//									ClothingAccess.GROIN),
//							PresetConcealmentLists.CONCEALED_DRESS_FRONT_FULL.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.ALL,
//			null,
//			ColourListPresets.JUST_STEEL,
//			ColourListPresets.ALL_METAL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You put on the dress and zip yourself up.",
//					"You guide [npc.name] into the frontal-zip dress and zip [npc.herHim] up.",
//					null,
//					"[npc.Name] puts on the frontal-zip dress and zips [npc.herself] up.",
//					"[npc.Name] guides you into the frontal-zip dress and zips you up.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You fully unzip your frontal-zip dress and shrug it off.",
//					"You fully unzip [npc.namePos] frontal-zip dress and pull it off.",
//					null,
//					"[npc.Name] fully unzips [npc.her] frontal-zip dress and shrugs it off.",
//					"[npc.Name] fully unzips your frontal-zip dress and pulls it off.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			switch(dt) {
//				case PULLS_UP:
//					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//							"You pull up the bottom of your frontal-zip dress.",
//							"You pull up the bottom of [npc.namePos] frontal-zip dress.",
//							null,
//							"[npc.Name] pulls up the bottom of [npc.her] frontal-zip dress.",
//							"[npc.Name] pulls up the bottom of your frontal-zip dress.",
//							null, null, null);
//				case UNZIPS:
//					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//							"You fully unzip the front of your dress.",
//							"You fully unzip the front of [npc.namePos] dress.",
//							null,
//							"[npc.Name] fully unzips the front of [npc.her] dress.",
//							"[npc.Name] fully unzips the front of your dress.",
//							null, null, null);
//				default:
//					return super.displaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
//			}
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			switch(dt) {
//				case PULLS_UP:
//					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//							"You pull the bottom of your frontal-zip dress back down.",
//							"You pull the bottom of [npc.namePos] frontal-zip dress back down.",
//							null,
//							"[npc.Name] pulls the bottom of [npc.her] frontal-zip dress back down.",
//							"[npc.Name] pulls the bottom of your frontal-zip dress back down.",
//							null, null, null);
//				case UNZIPS:
//					return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//							"You zip up the front of your dress.",
//							"You zip up the front of [npc.namePos] dress.",
//							null,
//							"[npc.Name] zips up the front of [npc.her] dress.",
//							"[npc.Name] zips up the front of your dress.",
//							null, null, null);
//				default:
//					return super.displaceText(clothingOwner, clothingRemover, slotToEquipInto, dt, rough);
//			}
//		}
//	};

	// WRIST

	public static AbstractClothingType WRIST_WOMENS_WATCH = new AbstractClothingType(1000,
			"",
			false,
			"женские часы",
			"женские часы",
			"Женственные часы. Как и любые другие часы, их основная задача — показывать время.",
			0,
			Femininity.FEMININE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_womens_watch",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL,
			null,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "Вы надеваете часы на запястье и застегиваете ремешок.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] надевает " + clothing.getName(true) + " на запястье [npc.her] и застегивает ремешок.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "Вы расстегиваете ремешок часов и снимаете их.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] расстегивает ремешок часов [npc.her] и снимает их.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] расстегивает ваш ремешок часов и снимает их.";
				else
					return UtilText.parse(clothingOwner, "Вы расстегиваете ремешок на часах [npc.namePos] и снимаете их.");
			}
		}
	};
	
	public static AbstractClothingType WRIST_MENS_WATCH = new AbstractClothingType(1200,
			"",
			false,
			"мужские часы",
			"мужские часы",
			"Часы мужского стиля. Как и любые другие подобные часы, их основная задача — отсчитывать время.",
			0,
			Femininity.MASCULINE,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_mens_watch",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL_METAL,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_WITH_METALS,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "Вы надеваете часы на запястье и застегиваете ремешок.";
			else
				return UtilText.parse(clothingOwner,
						"[npc.Name] надевает " + clothing.getName(true) + " на запястье [npc.her] и застегивает ремешок.");
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
				return "Вы расстегиваете ремешок часов и снимаете их.";
			else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
				return UtilText.parse(clothingOwner, "[npc.Name] расстегивает ремешок часов [npc.her] и снимает их.");
			else {
				if (clothingOwner.isPlayer())
					return "[npc.Name] расстегивает ваш ремешок часов и снимает их.";
				else
					return UtilText.parse(clothingOwner, "Вы расстегиваете ремешок на часах [npc.namePos] и снимаете их.");
			}
		}
	};
	
	public static AbstractClothingType WRIST_SUIT_CUFFS = new AbstractClothingType(100,
			"пара",
			true,
			"манжеты костюма",
			"манжеты костюма",
			"Пара костюмных манжет, которые надеваются на запястья.",
			0,
			null,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_suit_cuffs",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.JUST_WHITE, ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK, ColourListPresets.ALL,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы застегиваете манжеты костюма на своих запястьях.",
					"Вы застегиваете манжеты костюма на запястьях [npc.namePos].",
					null,
					"[npc.Name] застегивает манжеты костюма на запястьях [npc.her].",
					"[npc.Name] застегивает манжеты костюма на ваших запястьях.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы расстегиваете манжеты своего костюма и снимаете их.",
					"Вы расстегиваете манжеты [npc.namePos] и снимаете их.",
					null,
					"[npc.Name] расстегивает манжеты [npc.her] и снимает их.",
					"[npc.Name] расстегивает ваши манжеты и снимает их.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType WRIST_WRISTBANDS = new AbstractClothingType(100,
			"пара",
			true,
			"напульсник",
			"напульсники",
			"Пара напульсников, предназначенных для ношения на запястьях, чтобы впитывать пот владельца.",
			0,
			null,
			InventorySlot.WRIST,
			Rarity.COMMON,
			null,
			"clothing/wrist_sweatbands",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null,
							Util.newArrayListOfValues(ClothingAccess.WRISTS),
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы застегиваете напульсники на своих запястьях.",
					"Вы застегиваете напульсники на запястьях [npc.namePos].",
					null,
					"[npc.Name] застегивает напульсники на запястьях [npc.her].",
					"[npc.Name] застегивает на твоих запястьях напульсники.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты расстегиваешь напульсники и снимаешь их.",
					"Ты расстегиваешь [npc.namePos] напульсники и снимаешь их.",
					null,
					"[npc.Name] расстегивает [npc.her] напульсники и снимает их.",
					"[npc.Name] расстегивает твои напульсники и снимает их.",
					null, null, null);
		}
	};

	// BELT
	
	public static AbstractClothingType HIPS_CONDOMS = new AbstractClothingType(20,
			"",
			false,
			"пояс для презервативов",
			"пояса для презервативов",
			"Полоска прочной эластичной ткани, плотно облегающая бедра. Особой пользы от нее нет, но к ней всегда можно привязать использованные презервативы...",
			0,
			null,
			InventorySlot.HIPS,
			Rarity.COMMON,
			null,
			"clothing/belt_used_condoms",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null)),
			null,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "Ты накидываешь пояс на ноги, а затем подтягиваешь его к талии.";
			} else {
				return UtilText.parse(clothingOwner, "[npc.Name] накидывает пояс на ноги, а затем подтягивает его к [npc.her] талии.");
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if (clothingOwner.isPlayer() && clothingRemover.isPlayer()) {
				return "Вы стягиваете пояс и сбрасываете его со своих [pc.feet].";
				
			} else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer()) {
				return UtilText.parse(clothingOwner, "[npc.Name] стягивает [npc.her] пояс, прежде чем сбросить его со своих [npc.her] [npc.feet].");
				
			} else {
				if (clothingOwner.isPlayer()) {
					return UtilText.parse(clothingRemover, "[npc.Name] стягивает ваш пояс и соскальзывает им с ваших [pc.feet].");
				} else {
					return UtilText.parse(clothingOwner, "Вы стягиваете [npc.namePos] пояс и соскальзываете им с [npc.her] [npc.feet].");
				}
			}
		}
	};
	
	public static AbstractClothingType HIPS_SUSPENDER_BELT = new AbstractClothingType(150,
			"",
			false,
			"пояс для чулок",
			"пояса для чулок",
			"Круглая лента из мягкого эластичного нейлона, к которой прикреплены шесть лямок; каждая из них предназначена для того, чтобы зацепиться за пару чулок и удержать их.",
			0,
			Femininity.FEMININE,
			InventorySlot.HIPS,
			Rarity.COMMON,
			null,
			"clothing/hips_suspender_belt",
			null,
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							null,
							null,
							null,
							null)),
			null,
			ColourListPresets.LINGERIE, ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL, ColourListPresets.ALL_METAL,
			null,
			null, 
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы надеваете пояс для чулок, а затем подтягиваете его к талии.",
					"Вы заставляете [npc.name] надеть пояс для чулок, а затем подтягиваете его к [npc.her] талии.",
					null,
					"[npc.Name] надевает пояс для чулок, а затем подтягивает его к [npc.her] талии.",
					"[npc.Name] заставляет тебя наступить в пояс для подвязок, а затем натягивает его тебе на талию.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты стягиваешь пояс для подвязок и снимаешь его.",
					"Ты стягиваешь пояс для подвязок [npc.namePos] и снимаешь его.",
					null,
					"[npc.Name] стягивает пояс для подвязок [npc.her] и снимает его.",
					"[npc.Name] стягивает твой пояс для подвязок и снимает его.",
					null, null, null);
		}
	};
	

	// CLOTHING SETS:

	// MAID:
//	public static AbstractClothingType MAID_HEADPIECE = new AbstractClothingType(600,
//			"a",
//			false,
//			"Maid's headpiece",
//			"Maid's headpieces",
//			"A heavily stylised Maid's headpiece, it consists of a coloured headband with a decorative white lace attached on top.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.HEAD,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidHeadband",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.HEAD),
//							null,
//							null,
//							null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You slide the headpiece into place.",
//					"You slide the headpiece onto [npc.namePos] head.",
//					null,
//					"[npc.Name] slides the headpiece into place.",
//					"[npc.Name] slides the headpiece onto your head.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You take off your headpiece.",
//					"You pull off [npc.namePos] headpiece.",
//					null,
//					"[npc.Name] takes [npc.her] headpiece off.",
//					"[npc.Name] pulls your headpiece off.",
//					null, null, null);
//		}
//
//	};
	
//	public static AbstractClothingType MAID_DRESS = new AbstractClothingType(2500,
//			"a",
//			false,
//			"Maid's dress",
//			"Maid's dresses",
//			"A heavily stylised Maid's dress, it consists of a coloured one-piece dress with decorative white lace trimmings."
//					+ " A small white apron is attached to the front, and is similarly trimmed in white lace.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.TORSO_UNDER,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidDress",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK,
//									CoverableArea.ARMPITS),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.ANUS,
//									CoverableArea.PENIS,
//									CoverableArea.VAGINA),
//							Util.newArrayListOfValues(
//									ClothingAccess.GROIN,
//									ClothingAccess.ANUS), 
//							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You step into the dress and pull it up over your torso, zipping yourself up at the back before making sure the trimmings and apron are neatly arranged.",
//					"You get [npc.name] to step into the dress, before pulling it up over [npc.her] torso. Zipping up at the back, you then make sure that the trimmings and apron are neatly arranged.",
//					null,
//					"[npc.Name] steps into the dress and pulls it up over [npc.her] torso, zipping [npc.herself] up at the back before making sure the trimmings and apron are neatly arranged.",
//					"[npc.Name] gets you to step into the dress, before pulling it up over your torso. Zipping you up at the back, [npc.she] then makes sure that the trimmings and apron are neatly arranged.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You reach back and unzip your Maid's dress, pulling your arms out before sliding it down your body and stepping out.",
//					"You unzip the back of [npc.namePos] Maid's dress, before pulling [npc.her] [npc.arms] free to allow the dress to fall to the floor.",
//					null,
//					"[npc.Name] reaches back and unzips [npc.her] Maid's dress, pulling [npc.her] arms out before sliding it down [npc.her] body and stepping out.",
//					"[npc.Name] unzips the back of your Maid's dress, before pulling your arms free to allow the dress to fall to the floor.",
//					null, null, null);
//		}
//
//		@Override
//		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if (dt == DisplacementType.PULLS_DOWN) {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You unzip the back of your dress and pull down the top half.";
//				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
//					return UtilText.parse(clothingOwner, "[npc.Name] unzips the back of [npc.her] dress and pulls the top half down");
//				else {
//					if (clothingOwner.isPlayer())
//						return "[npc.Name] unzips the back of your dress and pulls the top half down.";
//					else
//						return UtilText.parse(clothingOwner, "You unzip the back of [npc.namePos] dress and pull the top half down.");
//				}
//			} else {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You pull up your dress's skirt.";
//				else if (!clothingOwner.isPlayer() && !clothingRemover.isPlayer())
//					return UtilText.parse(clothingOwner, "[npc.Name] pulls up [npc.her] dress's skirt.");
//				else {
//					if (clothingOwner.isPlayer())
//						return "[npc.Name] pulls up your dress's skirt.";
//					else
//						return UtilText.parse(clothingOwner, "You pull up the skirt of [npc.namePos] dress.");
//				}
//			}
//		}
//
//		@Override
//		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
//			if (dt == DisplacementType.PULLS_DOWN) {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You pull your dress back up into the correct position and zip yourself up.";
//				else
//					return UtilText.parse(clothingOwner,
//							"[npc.Name] pulls [npc.her] dress up into the correct position and zips [npc.herself] up.");
//			} else {
//				if (clothingOwner.isPlayer() && clothingRemover.isPlayer())
//					return "You pull your dress's skirt back down.";
//				else
//					return UtilText.parse(clothingOwner, "[npc.Name] pulls [npc.her] dress's skirt back down.");
//			}
//		}
//	};
//	public static AbstractClothingType MAID_STOCKINGS = new AbstractClothingType(450,
//			"a pair of",
//			true,
//			"Maid's stockings",
//			"Maid's stockings",
//			"A pair of cotton Maid's stockings, with a coloured bow near the top.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.SOCK,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidStockings",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.FEET,
//									ClothingAccess.CALVES),
//							null, null, null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the stockings.",
//					"You pull the stockings onto [npc.namePos] [npc.feet].",
//					null,
//					"[npc.Name] pulls on the stockings.",
//					"[npc.Name] pulls the stockings onto your [pc.feet].",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your stockings.",
//					"You pull off [npc.namePos] stockings.",
//					null,
//					"[npc.Name] pulls off [npc.her] stockings.",
//					"[npc.Name] pulls off your stockings.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType MAID_HEELS = new AbstractClothingType(800,
//			"a pair of",
//			true,
//			"Maid's high heels",
//			"Maid's high heels",
//			"A pair of Maid's high heels, they are made of coloured leather with a small amount of white lace decoration.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.FOOT,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidHeels",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.FEET),
//							Util.newArrayListOfValues(CoverableArea.FEET),
//							Util.newArrayListOfValues(ClothingAccess.FEET),
//							null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_STEEL,
//			ColourListPresets.ALL_METAL,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//		
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You slip on the heels and buckle up the straps.",
//					"You pull the heels onto [npc.namePos] [npc.feet] and buckle up the straps.",
//					null,
//					"[npc.Name] pulls on the heels and buckles up the straps.",
//					"[npc.Name] pulls the heels onto your [pc.feet] and buckles up the straps.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You unbuckle your heels and slip them off.",
//					"You unbuckle [npc.namePos] heels and pull them off.",
//					null,
//					"[npc.Name] unbuckles [npc.her] heels and slips them off.",
//					"[npc.Name] unbuckles your heels and pulls them off.",
//					null, null, null);
//		}
//	};
	
//	public static AbstractClothingType MAID_SLEEVES = new AbstractClothingType(350,
//			"a pair of",
//			true,
//			"Maid's sleeves",
//			"Maid's sleeves",
//			"A pair of Maid's sleeves that end just past the elbow. They are made of soft coloured fabric with white lace trimmings.",
//			0,
//			Femininity.FEMININE,
//			InventorySlot.HAND,
//			Rarity.EPIC,
//			SetBonus.getSetBonusFromId("innoxia_maid"),
//			"clothing/maidGloves",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(ClothingAccess.WRISTS),
//							null, null, null)),
//			null,
//			ColourListPresets.MAID,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			null,
//			null,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the sleeves.",
//					"You pull the sleeves onto [npc.namePos] [npc.arms].",
//					null,
//					"[npc.Name] pulls on the sleeves.",
//					"[npc.Name] pulls the sleeves onto your [pc.arms].",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull off your sleeves.",
//					"You pull off [npc.namePos] sleeves.",
//					null,
//					"[npc.Name] pulls [npc.her] sleeves off.",
//					"[npc.Name] pulls your sleeves off.",
//					null, null, null);
//		}
//	};

	// Enforcer:
	
	public static AbstractClothingType ENFORCER_SHIRT = new AbstractClothingType(1500,
			"",
			false,
			"карнавальная рубашка Enforcer",
			"карнавальные рубашки Enforcer",
			"Карнавальная версия рубашки Enforcer, дополненная имитацией бронежилета.",
			1f,
			null,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_slutty_enforcer"),
			"clothing/enforcerShirt",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
							null,
							null),
					new BlockedParts(
							DisplacementType.UNBUTTONS,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLUE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_PINK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.FITS_ARM_WINGS)){
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты натягиваешь рубашку и застегиваешь её на пуговицы.",
					"Ты надеваешь рубашку на [npc.name] и застегиваешь её на пуговицы.",
					null,
					"[npc.Name] натягивает рубашку и застегивает её на пуговицы.",
					"[npc.Name] надевает на тебя рубашку и застегивает её.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты расстегиваешь рубашку и снимаешь её.",
					"Ты расстегиваешь рубашку [npc.namePos] и снимаешь её.",
					null,
					"[npc.Name] расстегивает рубашку [npc.her] и снимает её.",
					"[npc.Name] расстегивает твою рубашку и снимает её.",
					null, null, null);
		}
	};

	public static AbstractClothingType ENFORCER_SHORTS = new AbstractClothingType(600,
			"пара",
			true,
			"праздничные шорты Enforcer",
			"праздничные шорты Enforcer",
			"Пара праздничных шорт Enforcer, дополненная поясной сумкой.",
			0,
			null,
			InventorySlot.LEG,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_slutty_enforcer"),
			"clothing/enforcerShorts",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
									DisplacementType.REMOVE_OR_EQUIP,
									Util.newArrayListOfValues(ClothingAccess.LEGS_UP_TO_GROIN),
									null,
									Util.newArrayListOfValues(ClothingAccess.LEGS_UP_TO_GROIN),
									null),
					new BlockedParts(
									DisplacementType.PULLS_DOWN,
									null,
									Util.newArrayListOfValues(
											CoverableArea.ANUS,
											CoverableArea.PENIS,
											CoverableArea.VAGINA),
									Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
									PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList()),
					new BlockedParts(
									DisplacementType.UNZIPS,
									null,
									Util.newArrayListOfValues(CoverableArea.PENIS),
									Util.newArrayListOfValues(ClothingAccess.GROIN),
									PresetConcealmentLists.CONCEALED_UNZIPS_GROIN.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLUE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_PINK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты шагаешь в шорты, а затем натягиваешь их до талии.",
					"Вы натягиваете шорты высоко вверх, от [npc.namePos] [npc.legs] до [npc.her] талии.",
					null,
					"[npc.Name] шагает в шорты, а затем натягивает их до [npc.her] талии.",
					"[npc.Name] натягивает шорты высоко вверх, от ваших [pc.legs] до самой талии.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы стягиваете шорты вниз и сбрасываете их с [pc.feet].",
					"Вы стягиваете шорты [npc.namePos] и соскальзываете ими с [npc.her] [npc.feet].",
					null,
					"[npc.Name] стягивает шорты [npc.her] и сбрасывает их с [npc.her] [npc.feet].",
					"[npc.Name] стягивает ваши шорты и снимает их с ваших [pc.feet].",
					null, null, null);
		}
	};
	
	public static AbstractClothingType ENFORCER_MINI_SKIRT = new AbstractClothingType(600,
			"",
			false,
			"праздничная мини-юбка Enforcer",
			"праздничные мини-юбки Enforcer",
			"Праздничная юбка Enforcer с поясным ремнем, настолько короткая, что она едва доходит до середины бедра.",
			0,
			Femininity.FEMININE,
			InventorySlot.LEG,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_slutty_enforcer"),
			"clothing/enforcer_miniskirt",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLUE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_PINK),
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_STEEL,
			ColourListPresets.ALL_METAL,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы шагаете в мини-юбку, а затем натягиваете её до самой талии.",
					"Вы натягиваете мини-юбку [npc.namePos] [npc.legs], чтобы она зафиксировалась на [npc.her] талии.",
					null,
					"[npc.Name] шагает в мини-юбку, а затем натягивает её до [npc.her] талии.",
					"[npc.Name] натягивает мини-юбку по вашим [pc.legs], чтобы она зафиксировалась на вашей талии.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы стягиваете мини-юбку и скидываете её с [pc.feet].",
					"Вы стягиваете мини-юбку [npc.namePos] и снимаете её с [npc.her] [npc.feet].",
					null,
					"[npc.Name] стягивает мини-юбку [npc.her], прежде чем скинуть её с [npc.her] [npc.feet].",
					"[npc.Name] стягивает вашу мини-юбку и снимает её с вашей [pc.feet].",
					null, null, null);
		}
	};

	
	public static AbstractClothingType MILK_MAID_TORSO_DRESS = new AbstractClothingType(1500,
			"",
			false,
			"Платье молочницы",
			"Платья молочницы",
			"Платье молочной девушки; состоит из рубашки, юбки и корсета. Рубашка сделана из легкого, удобного материала, который становится очень прозрачным при намокании. Корсет, как и юбка, выполнен из мягкой ткани, а его шнуровку можно затянуть потуже, чтобы подчеркнуть фигуру и слегка сжать грудь.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_milk_maid"),
			"clothing/milk_maid_dress",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.STOMACH,
									CoverableArea.BACK),
							Util.newArrayListOfValues(ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_DOWN,
							null,
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES),
							Util.newArrayListOfValues(ClothingAccess.CHEST),
							Util.newArrayListOfValues(
									InventorySlot.CHEST,
									InventorySlot.NIPPLE,
									InventorySlot.PIERCING_NIPPLE))),
			null,
			ColourListPresets.MILK_MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){
		@Override
		public String getAuthorDescription() {
			return "На бирке, пришитой к внутренней стороне платья, написано: «Сделано Blue999»";
		}
	};
	
	public static AbstractClothingType MILK_MAID_HEADBAND = new AbstractClothingType(400,
			"",
			false,
			"Ободок молочной девушки",
			"Ободки молочных девушек",
			"Рюшевый ободок с множеством декоративных кружев сверху.",
			0,
			Femininity.FEMININE,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_milk_maid"),
			"clothing/milk_maid_headband",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null, null, null)),
			null,
			ColourListPresets.MILK_MAID,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
			@Override
			public String getAuthorDescription() {
				return "На бирке, пришитой к внутренней стороне ободка, написано: «Сделано Blue999»";
			}
			
			@Override
			public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"Вы натягиваете ободок.",
						"Вы надеваете ободок на голову [npc.namePos].",
						null,
						"[npc.Name] надевает ободок на голову [npc.her].",
						"[npc.Name] надевает ободок вам на голову.",
						null, null, null);
			}

			@Override
			public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
				return getEquipDescriptions(clothingOwner, clothingRemover, rough,
						"Вы снимаете повязку.",
						"Вы снимаете [npc.namePos] повязку.",
						null,
						"[npc.Name] снимает с [npc.her] повязку.",
						"[npc.Name] снимает вашу повязку.",
						null, null, null);
			}
	};
	
	public static AbstractClothingType MILK_MAID_KERCHIEF = new AbstractClothingType(300,
			"",
			false,
			"платок",
			"платки",
			"Треугольный кусок ткани, покрывающий макушку.",
			0,
			null,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_milk_maid"),
			"clothing/milk_maid_kerchief",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null, null, null)),
			null,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы надеваете платок на голову, прежде чем завязать завязки под подбородком.",
					"Вы надеваете платок на голову [npc.namePos], прежде чем завязать завязки под [npc.her] подбородком.",
					null,
					"[npc.Name] надевает платок на голову [npc.her], прежде чем завязать завязки под [npc.her] подбородком.",
					"[npc.Name] надевает тебе на голову платок, а затем завязывает завязки под подбородком.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты снимаешь платок.",
					"Ты снимаешь платок с [npc.namePos].",
					null,
					"[npc.Name] снимает [npc.her] платок.",
					"[npc.Name] снимает твой платок.",
					null, null, null);
		}
	};
	
//	public static AbstractClothingType MEGA_MILK = new AbstractClothingType(400,
//			"a",
//			false,
//			"Mega Milk T-shirt",
//			"Mega Milk T-shirts",
//			"A T-shirt with the words 'Mega Milk' written on the front.",
//			0,
//			null,
//			InventorySlot.TORSO_UNDER,
//			Rarity.UNCOMMON,
//			null,
//			"clothing/torso_tshirt_megamilk",
//			Util.newArrayListOfValues(
//					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0)),
//			Util.newArrayListOfValues(
//					new BlockedParts(
//							DisplacementType.REMOVE_OR_EQUIP,
//							Util.newArrayListOfValues(
//									ClothingAccess.ARMS_UP_TO_SHOULDER,
//									ClothingAccess.HEAD),
//							null,
//							null, null),
//					new BlockedParts(
//							DisplacementType.PULLS_UP,
//							null,
//							Util.newArrayListOfValues(
//									CoverableArea.BREASTS,
//									CoverableArea.NIPPLES,
//									CoverableArea.STOMACH,
//									CoverableArea.BACK,
//									CoverableArea.ARMPITS),
//							Util.newArrayListOfValues(
//									ClothingAccess.CHEST,
//									ClothingAccess.WAIST),
//							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList())),
//			null,
//			Util.newArrayListOfValues(
//							PresetColour.CLOTHING_BLUE,
//							PresetColour.CLOTHING_BLACK),
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_WHITE,
//			ColourListPresets.ALL,
//			ColourListPresets.JUST_BLACK,
//			ColourListPresets.ALL,
//			Util.newArrayListOfValues(ItemTag.SOLD_BY_NYAN)){
//
//		@Override
//		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You pull on the t-shirt.",
//					"You pull the t-shirt onto [npc.name].",
//					null,
//					"[npc.Name] pulls on the t-shirt.",
//					"[npc.Name] pulls the t-shirt down over your head.",
//					null, null, null);
//		}
//
//		@Override
//		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
//			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
//					"You take off your t-shirt.",
//					"You take off [npc.namePos] t-shirt.",
//					null,
//					"[npc.Name] takes [npc.her] t-shirt off.",
//					"[npc.Name] takes your t-shirt off.",
//					null, null, null);
//		}
//	};
	
	public static AbstractClothingType JOLNIR_HAT = new AbstractClothingType(400,
			"",
			false,
			"Шляпа Йёльнура",
			"Шляпы Йёльнура",
			"Шляпа, сделанная в том же стиле, что и у Йольского персонажа.",
			0,
			null,
			InventorySlot.HEAD,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_head_hat",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.HEAD),
							null, null, null)),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Ты надеваешь шляпу Йёльнура.",
					"Ты надеваешь шляпу Йёльнура на голову [npc.namePos].",
					null,
					"[npc.Name] надевает шляпу Йольнира.",
					"[npc.Name] надевает шляпу Йольнира вам на голову.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы снимаете шляпу Йольнира.",
					"Вы снимаете с [npc.namePos] шляпу Йольнира.",
					null,
					"[npc.Name] снимает шляпу Йольнира.",
					"[npc.Name] снимает вашу шляпу Йольнира.",
					null, null, null);
		}

	};
	
	public static AbstractClothingType JOLNIR_COAT = new AbstractClothingType(750,
			"",
			false,
			"Пальто Йольнира",
			"Пальто Йольнира",
			"Пальто, похожее на то, что носит Йольская фигура.",
			0,
			null,
			InventorySlot.TORSO_OVER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_torso_over_coat",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0), 
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
							Util.newArrayListOfValues(ClothingAccess.ARMS_UP_TO_SHOULDER),
							PresetConcealmentLists.CONCEALED_FULL_TORSO.getPresetInventorySlotList())),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){
		
		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы натягиваете пальто.",
					"Вы [npc.herHim] помогаете [npc.namePos] [npc.arms] просунуть в рукава пальто, когда надеваете его.",
					null,
					"[npc.Name] натягивает пальто.",
					"Вы помогаете [npc.Name] просунуть [pc.arms] в рукава пальто, пока [npc.she] надевает его на вас.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы снимаете пальто.",
					"Вы стягиваете пальто с [npc.namePos].",
					null,
					"[npc.Name] снимает [npc.her] пальто.",
					"[npc.Name] стягивает с вас пальто.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType JOLNIR_DRESS = new AbstractClothingType(900,
			"",
			false,
			"Платье Йёльнир",
			"Платья Йёльнир",
			"Платье, выполненное в том же стиле, что и одежда Йольского персонажа.",
			0,
			Femininity.FEMININE,
			InventorySlot.TORSO_UNDER,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_torso_dress",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0), 
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(
									ClothingAccess.ARMS_UP_TO_SHOULDER,
									ClothingAccess.HEAD),
							Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.STOMACH,
									CoverableArea.BACK,
									CoverableArea.ARMPITS),
							Util.newArrayListOfValues(
									ClothingAccess.CHEST,
									ClothingAccess.WAIST),
							PresetConcealmentLists.CONCEALED_PARTIAL_TORSO.getPresetInventorySlotList()),
					new BlockedParts(
							DisplacementType.PULLS_UP,
							null,
							Util.newArrayListOfValues(
									CoverableArea.ANUS,
									CoverableArea.PENIS,
									CoverableArea.VAGINA),
							Util.newArrayListOfValues(
									ClothingAccess.GROIN,
									ClothingAccess.ANUS),
							PresetConcealmentLists.CONCEALED_GROIN.getPresetInventorySlotList())),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT, ItemTag.DRESS, ItemTag.FITS_ARM_WINGS)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы натягиваете платье Йёльнир, расправляя юбку, а затем поправляете лямки, чтобы они удобно легли на ваши плечи.",
					"Вы натягиваете платье Йёльнир через голову [npc.namePos] и вниз по [npc.her] туловищу, расправляя юбку и поправляя лямки, чтобы они удобно легли на [npc.her] плечи.",
					null,
					"[npc.Name] натягивает платье Йёльнир, расправляя юбку, а затем поправляет лямки, чтобы они удобно легли на [npc.her] плечи.",
					"[npc.Name] натягивает платье Йёльнир вам через голову и вниз по вашему туловищу, расправляя юбку и поправляя лямки, чтобы они удобно легли на ваши плечи.",
					null, null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы стягиваете платье Йёльнир через голову, снимая его.",
					"Вы стягиваете платье Йёльнир через голову [npc.her], снимая его с [npc.namePos].",
					null,
					"[npc.Name] стягивает платье Йёльнир через голову [npc.her], снимая его с [npc.her].",
					"[npc.Name] стягивает ваше платье Йёльнир через голову, снимая его с вас.",
					null, null, null);
		}

		@Override
		public String displaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы приподнимаете подол своего платья Йёльнир.",
					"Вы приподнимаете подол платья Йёльнир на [npc.namePos].",
					null,
					"[npc.Name] приподнимает подол платья [npc.her] Йёльнир.",
					"[npc.Name] приподнимает подол твоего платья Йёльнир.",
					null, null, null);
		}

		@Override
		public String replaceText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, DisplacementType dt, boolean rough) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы поправляете свое платье Йёльнир, спуская его на место.",
					"Вы натягиваете платье [npc.namePos] Йёльнир обратно на место.",
					null,
					"[npc.Name] натягивает [npc.her] платье Йёльнир обратно на место.",
					"[npc.Name] ваше платье Йёльнир на место.",
					null, null, null);
		}
	};
	
	public static AbstractClothingType JOLNIR_BOOTS = new AbstractClothingType(500,
			"пара",
			true,
			"Сапог Йёльнир",
			"Сапоги Йёльнир",
			"Пара сапог, подобных тем, что носят персонажи Йоля.",
			0,
			Femininity.MASCULINE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_foot_boots",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы просовываете свои [pc.feet] в сапоги.",
					"Вы проталкиваете [npc.namePos] [npc.feet] в сапоги.",
					"Вы силой натягиваете сапоги на [npc.namePos] [npc.feet].",
					"[npc.Name] проталкивает [npc.her] [npc.feet] в сапоги.",
					"[npc.Name] проталкивает ваши [pc.feet] в сапоги.",
					"[npc.Name] силой натягивает сапоги на ваши [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы снимаете свои сапоги.",
					"Вы снимаете сапоги с [npc.namePos].",
					"Вы хватаете [npc.namePos] [npc.feet] и стягиваете [npc.her] сапоги.",
					"[npc.Name] снимает [npc.her] сапоги.",
					"[npc.Name] снимает ваши сапоги.",
					"[npc.Name] хватает тебя за [pc.feet] и стягивает сапоги.", null, null);
		}
	};
	
	public static AbstractClothingType JOLNIR_BOOTS_FEMININE = new AbstractClothingType(750,
			"пара",
			true,
			"Сапог на каблуке Йёльнира",
			"Сапоги на каблуке Йёльнира",
			"Пара сапог в том же стиле, что и у Йольского персонажа.",
			0,
			Femininity.FEMININE,
			InventorySlot.FOOT,
			Rarity.EPIC,
			SetBonus.getSetBonusFromId("innoxia_jolnir"),
			"clothing/jolnir_foot_boots_feminine",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(
							DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FEET),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(ClothingAccess.FEET),
							null)),
			null,
			ColourListPresets.JUST_RED,
			ColourListPresets.ALL,
			ColourListPresets.JUST_BLACK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_WHITE,
			ColourListPresets.ALL,
			Util.newArrayListOfValues(ItemTag.REINDEER_GIFT)){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы просовываете свои [pc.feet] в сапоги.",
					"Вы проталкиваете [npc.namePos] [npc.feet] в сапоги.",
					"Вы силой натягиваете сапоги на [npc.namePos] [npc.feet].",
					"[npc.Name] проталкивает [npc.her] [npc.feet] в сапоги.",
					"[npc.Name] проталкивает ваши [pc.feet] в сапоги.",
					"[npc.Name] силой натягивает сапоги на ваши [pc.feet].", null, null);
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			return getEquipDescriptions(clothingOwner, clothingRemover, rough,
					"Вы снимаете свои сапоги.",
					"Вы снимаете сапоги с [npc.namePos].",
					"Вы хватаете [npc.namePos] [npc.feet] и стягиваете [npc.her] сапоги.",
					"[npc.Name] снимает [npc.her] сапоги.",
					"[npc.Name] снимает ваши сапоги.",
					"[npc.Name] хватает тебя за [pc.feet] и стягивает сапоги.", null, null);
		}
	};

	public static AbstractClothingType FINGER_LYSSIETHS_RING = new AbstractClothingType(50000,
			"",
			false,
			"Перстень-печать Lyssieth",
			"Перстни-печати Lyssieth",
			"Прекрасно исполненный и инкрустированный драгоценными камнями, перстень-печать Lyssieth внушает уважение и преданность любому члену её дома. Как и подобает символу власти лилин, этот предмет обладает огромной, хоть и развращающей силой.",
			0,
			Femininity.ANDROGYNOUS,
			InventorySlot.FINGER,
			Rarity.QUEST,
			null,
			"items/lyssiethsRing",
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_SPELLS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					new BlockedParts(DisplacementType.REMOVE_OR_EQUIP,
							Util.newArrayListOfValues(ClothingAccess.FINGERS),
							null,
							null, null)),
			null,
			ColourListPresets.JUST_ROSE_GOLD,
			ColourListPresets.ALL_METAL,
			ColourListPresets.JUST_RED_DARK,
			ColourListPresets.ALL,
			ColourListPresets.JUST_ROSE_GOLD,
			ColourListPresets.ALL_METAL,
			null){

		@Override
		public String equipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if(rough) {
				return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] грубо [npc.verb(force)] перстень-печать на палец [npc2.namePos].");
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] [npc.verb(slide)] перстень-печать на палец [npc.her].");
				} else {
					return UtilText.parse(clothingOwner, clothingRemover, "[npc.Name] [npc.verb(slide)] наложить перстень на [npc2.namePos] палец.");
				}
			}
		}

		@Override
		public String unequipText(GameCharacter clothingOwner, GameCharacter clothingRemover, InventorySlot slotToEquipInto, boolean rough, AbstractClothing clothing, boolean applyEffects) {
			if(rough) {
				return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] грубо [npc.verb(yank)] снять перстень с [npc2.namePos] пальца.");
			} else {
				if(clothingOwner.equals(clothingRemover)) {
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] [npc.verb(slide)] снять перстень с [npc.her] пальца.");
				} else {
					return UtilText.parse(clothingRemover, clothingOwner, "[npc.Name] [npc.verb(slide)] стянуть перстень с [npc2.namePos] пальца.");
				}
			}
		}
	};
	
	
	private static List<AbstractClothingType> allClothing;
	private static List<AbstractClothingType> moddedClothingList;
	private static Map<AbstractSetBonus, List<AbstractClothingType>> setClothing;
	
	private static List<InventorySlot> coreClothingSlots;
	private static List<InventorySlot> lingerieSlots;
	
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMap;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapFemale;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapMale;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapAndrogynous;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapFemaleIncludingAndrogynous;
	private static Map<InventorySlot, List<AbstractClothingType>> commonClothingMapMaleIncludingAndrogynous;
	
	private static Map<Occupation, ArrayList<AbstractClothingType>> suitableFeminineClothing = new HashMap<>();
	
	private static Map<AbstractClothingType, String> clothingToIdMap = new HashMap<>();
	private static Map<String, AbstractClothingType> idToClothingMap = new HashMap<>();
	
	private static Map<String, String> oldIdConversionMap = new HashMap<>();
	
	public static AbstractClothingType getClothingTypeFromId(String id) {
		return getClothingTypeFromId(id, null);
	}
	
	public static AbstractClothingType getClothingTypeFromId(String id, String slotHint) {
//		System.out.print("ID: "+id);
		
		if(oldIdConversionMap.containsKey(id)) {
			id = oldIdConversionMap.get(id);
		}
		
		Map<String, AbstractClothingType> choiceMap = idToClothingMap;
		if (slotHint!=null && !slotHint.isEmpty()) {
			try {
				InventorySlot slot = InventorySlot.valueOf(slotHint);
				
				// slotHint is present and valid, so filter the clothing map by items that can be equipped to that slot:
				choiceMap = idToClothingMap.entrySet().parallelStream()
						.filter(e -> e.getValue().getEquipSlots().contains(slot))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			} catch (Exception ex) {
				String validSlots = InventorySlot.getClothingSlots().stream()
						.map(InventorySlot::toString).collect(Collectors.joining(", "));
				System.err.println("Warning: getClothingTypeFromId() invalid slot hint: "
						+ slotHint + ". Valid slots are: " + validSlots);
			}
		}
		
		id = Util.getClosestStringMatch(id, choiceMap.keySet());
		
//		System.out.println("  set to: "+id);
		
		return idToClothingMap.get(id);
	}
	
	public static String getIdFromClothingType(AbstractClothingType clothingType) {
		return clothingToIdMap.get(clothingType);
	}

	public static Map<Occupation, ArrayList<AbstractClothingType>> getSuitableFeminineClothing() {
		return suitableFeminineClothing;
	}

	static {
		// Clothing set items:
		oldIdConversionMap.put("NECK_SNOWFLAKE_NECKLACE", "innoxia_elemental_snowflake_necklace");
		oldIdConversionMap.put("PIERCING_EAR_SNOW_FLAKES", "innoxia_elemental_piercing_ear_snowflakes");
		oldIdConversionMap.put("PIERCING_NOSE_SNOWFLAKE_STUD", "innoxia_elemental_piercing_nose_snowflake");
		
		oldIdConversionMap.put("NECK_SUN_NECKLACE", "innoxia_elemental_sun_necklace");
		oldIdConversionMap.put("PIERCING_EAR_SUN", "innoxia_elemental_piercing_ear_sun");
		oldIdConversionMap.put("PIERCING_NOSE_SUN_STUD", "innoxia_elemental_piercing_nose_sun");

		oldIdConversionMap.put("CATTLE_NECK_COWBELL_COLLAR", "innoxia_cattle_cowbell_collar");
		oldIdConversionMap.put("CATTLE_PIERCING_EAR_TAGS", "innoxia_cattle_piercing_ear_tag");
		oldIdConversionMap.put("CATTLE_PIERCING_NOSE_BOVINE_RING", "innoxia_cattle_piercing_nose_ring");

		oldIdConversionMap.put("SOCK_RAINBOW_STOCKINGS", "innoxia_rainbow_stockings");
		oldIdConversionMap.put("HAND_RAINBOW_FINGERLESS_GLOVES", "innoxia_rainbow_gloves");

		oldIdConversionMap.put("BDSM_CHASTITY_CAGE", "innoxia_bdsm_chastity_cage");
		oldIdConversionMap.put("BDSM_PENIS_STRAPON", "innoxia_bdsm_penis_strapon");
		oldIdConversionMap.put("innoxia_bdsmBracelets_wrist_bracelets", "innoxia_bdsm_wrist_bracelets");
		oldIdConversionMap.put("BDSM_BALLGAG", "innoxia_bdsm_ballgag");
		oldIdConversionMap.put("BDSM_RINGGAG", "innoxia_bdsm_ringgag");
		oldIdConversionMap.put("BDSM_SPREADER_BAR", "innoxia_bdsm_spreaderbar");
		oldIdConversionMap.put("BDSM_CHOKER", "innoxia_bdsm_choker");
		oldIdConversionMap.put("BDSM_WRIST_RESTRAINTS", "innoxia_bdsm_wrist_restraints");
		oldIdConversionMap.put("BDSM_CHASTITY_BELT", "innoxia_bdsm_chastity_belt");
		oldIdConversionMap.put("BDSM_CHASTITY_BELT_FULL", "innoxia_bdsm_chastity_belt_full");
		oldIdConversionMap.put("BDSM_KARADA", "innoxia_bdsm_karada");
		
		oldIdConversionMap.put("WITCH_HAT", "innoxia_witch_witch_hat");
		oldIdConversionMap.put("WITCH_DRESS", "innoxia_witch_witch_dress");
		oldIdConversionMap.put("WITCH_BOOTS", "innoxia_witch_witch_boots");
		oldIdConversionMap.put("WITCH_BOOTS_THIGH_HIGH", "innoxia_witch_witch_boots_thigh_high");

		oldIdConversionMap.put("EYES_SAFETY_GOGGLES", "innoxia_scientist_safety_goggles");
		oldIdConversionMap.put("SCIENTIST_EYES_SAFETY_GOGGLES", "innoxia_scientist_safety_goggles");
		oldIdConversionMap.put("SCIENTIST_TORSO_OVER_LAB_COAT", "innoxia_scientist_lab_coat");

		oldIdConversionMap.put("AMBERS_BITCH_CHOKER", "innoxia_neck_ambers_bitch_collar");

		oldIdConversionMap.put("KIMONO_HAIR_KANZASHI", "innoxia_japanese_kanzashi");
		oldIdConversionMap.put("KIMONO_DRESS", "innoxia_japanese_kimono");
		oldIdConversionMap.put("KIMONO_GETA", "innoxia_japanese_geta");
		oldIdConversionMap.put("KIMONO_MENS_KIMONO", "innoxia_japanese_mens_kimono");
		oldIdConversionMap.put("KIMONO_HAORI", "innoxia_japanese_haori");
		oldIdConversionMap.put("KIMONO_MENS_GETA", "innoxia_japanese_mens_geta");
		
		oldIdConversionMap.put("MAID_HEADPIECE", "innoxia_maid_headpiece");
		oldIdConversionMap.put("MAID_DRESS", "innoxia_maid_dress");
		oldIdConversionMap.put("MAID_STOCKINGS", "innoxia_maid_stockings");
		oldIdConversionMap.put("MAID_HEELS", "innoxia_maid_heels");
		oldIdConversionMap.put("MAID_SLEEVES", "innoxia_maid_sleeves");
		
		// Standard items:
		oldIdConversionMap.put("kobolds_belt_leather_belt", "innoxia_hips_leather_belt");
		oldIdConversionMap.put("PENIS_CONDOM", "innoxia_penis_condom");
		
		oldIdConversionMap.put("ANKLE_BRACELET", "innoxia_ankle_anklet");
		oldIdConversionMap.put("ANKLE_SHIN_GUARDS", "innoxia_ankle_shin_guards");
		
		oldIdConversionMap.put("PIERCING_EAR_RING", "innoxia_piercing_ear_ring");
		oldIdConversionMap.put("PIERCING_EAR_BASIC_RING", "innoxia_piercing_ear_ring");
		oldIdConversionMap.put("PIERCING_EAR_HOOPS", "innoxia_piercing_ear_hoops");
		oldIdConversionMap.put("PIERCING_NOSE_BASIC_RING", "innoxia_piercing_nose_ring");
		oldIdConversionMap.put("PIERCING_LIP_RINGS", "innoxia_piercing_lip_double_ring");
		oldIdConversionMap.put("PIERCING_TONGUE_BAR", "innoxia_piercing_basic_barbell");
		oldIdConversionMap.put("PIERCING_NIPPLE_BARS", "innoxia_piercing_basic_barbell_pair");
		oldIdConversionMap.put("PIERCING_NAVEL_GEM", "innoxia_piercing_gemstone_barbell");
		oldIdConversionMap.put("PIERCING_VAGINA_BARBELL_RING", "innoxia_piercing_ringed_barbell");
		oldIdConversionMap.put("PIERCING_PENIS_RING", "innoxia_piercing_penis_ring");

		oldIdConversionMap.put("EYES_GLASSES", "innoxia_eye_glasses");
		oldIdConversionMap.put("EYES_AVIATORS", "innoxia_eye_aviators");
		oldIdConversionMap.put("EYES_PATCH", "innoxia_eye_patch");

		oldIdConversionMap.put("MOUTH_BANDANA", "innoxia_mouth_bandana");
		
		oldIdConversionMap.put("HEAD_CHEATERS_CIRCLET", "innoxia_head_circlet");
		oldIdConversionMap.put("HEAD_CIRCLET", "innoxia_head_circlet");
		oldIdConversionMap.put("HEAD_TIARA", "innoxia_head_tiara");
		oldIdConversionMap.put("HEAD_CAP", "innoxia_head_cap");
		oldIdConversionMap.put("HEAD_HEADBAND", "innoxia_head_headband");
		oldIdConversionMap.put("HEAD_HEADBAND_BOW", "innoxia_head_headband_bow");
		oldIdConversionMap.put("HEAD_SWEATBAND", "innoxia_head_sweatband");
		oldIdConversionMap.put("HEAD_COWBOY_HAT", "innoxia_head_cowboy_hat");
		oldIdConversionMap.put("HEAD_ANTLER_HEADBAND", "innoxia_head_antler_headband");
		oldIdConversionMap.put("HEAD_SLIME_QUEENS_TIARA", "innoxia_head_slime_queens_tiara");
		
		oldIdConversionMap.put("HAND_ELBOWLENGTH_GLOVES", "innoxia_hand_elbow_length_gloves");
		oldIdConversionMap.put("HAND_GLOVES", "innoxia_hand_gloves");
		oldIdConversionMap.put("HAND_FINGERLESS_GLOVES", "innoxia_hand_fingerless_gloves");
		oldIdConversionMap.put("HAND_WRAPS", "innoxia_hand_wraps");
		oldIdConversionMap.put("HAND_FISHNET_GLOVES", "innoxia_hand_fishnet_gloves");

		oldIdConversionMap.put("FINGER_RING", "innoxia_finger_ring");

		oldIdConversionMap.put("NECK_ANKH_NECKLACE", "innoxia_neck_ankh_necklace");
		oldIdConversionMap.put("NECK_BELL_COLLAR", "innoxia_neck_bell_collar");
		oldIdConversionMap.put("NECK_COLLAR_BOWTIE", "innoxia_neck_collar_bowtie");
		oldIdConversionMap.put("NECK_HEART_NECKLACE", "innoxia_neck_heart_necklace");
		oldIdConversionMap.put("NECK_SCARF", "innoxia_neck_scarf");
		oldIdConversionMap.put("NECK_TIE", "innoxia_neck_tie");
		oldIdConversionMap.put("NECK_BREEDER_COLLAR", "innoxia_neck_breeder_collar");
		oldIdConversionMap.put("NECK_SLAVE_COLLAR", "innoxia_bdsm_metal_collar");

		oldIdConversionMap.put("CHEST_LACY_PLUNGE_BRA", "innoxia_chest_lacy_plunge_bra");
		oldIdConversionMap.put("CHEST_BIKINI", "innoxia_chest_bikini");
		oldIdConversionMap.put("CHEST_CHEMISE", "innoxia_chest_chemise");
		oldIdConversionMap.put("CHEST_CROPTOP_BRA", "innoxia_chest_croptop_bra");
		oldIdConversionMap.put("CHEST_FULLCUP_BRA", "innoxia_chest_fullcup_bra");
		oldIdConversionMap.put("CHEST_PLUNGE_BRA", "innoxia_chest_plunge_bra");
		oldIdConversionMap.put("CHEST_NURSING_BRA", "innoxia_chest_nursing_bra");
		oldIdConversionMap.put("CHEST_OPEN_CUP_BRA", "innoxia_chest_open_cup_bra");
		oldIdConversionMap.put("CHEST_SARASHI", "innoxia_chest_sarashi");
		oldIdConversionMap.put("CHEST_SPORTS_BRA", "innoxia_chest_sports_bra");
		oldIdConversionMap.put("CHEST_STRIPED_BRA", "innoxia_chest_striped_bra");
		oldIdConversionMap.put("CHEST_SWIMSUIT", "innoxia_chest_swimsuit");
		oldIdConversionMap.put("CHEST_TUBE_TOP", "innoxia_chest_tube_top");

//		oldIdConversionMap.put("NIPPLE_TAPE_CROSSES", "innoxia_nipple_tape_crosses");
		oldIdConversionMap.put("NIPPLE_TAPE_CROSSES", "innoxia_tape_roll");
		oldIdConversionMap.put("innoxia_nipple_tape_crosses", "innoxia_tape_roll");
		
		oldIdConversionMap.put("LEG_SKIRT", "innoxia_leg_skirt");
		oldIdConversionMap.put("LEG_PENCIL_SKIRT", "innoxia_leg_pencil_skirt");
		oldIdConversionMap.put("LEG_MINI_SKIRT", "innoxia_leg_mini_skirt");
		oldIdConversionMap.put("LEG_MICRO_SKIRT_PLEATED", "innoxia_leg_micro_skirt_pleated");
		oldIdConversionMap.put("LEG_MICRO_SKIRT_BELTED", "innoxia_leg_micro_skirt_belted");
		oldIdConversionMap.put("LEG_SHORTS", "innoxia_leg_shorts");
		oldIdConversionMap.put("LEG_BIKE_SHORTS", "innoxia_leg_bike_shorts");
		oldIdConversionMap.put("LEG_SPORT_SHORTS", "innoxia_leg_sport_shorts");
		oldIdConversionMap.put("LEG_HOTPANTS", "innoxia_leg_hotpants");
		oldIdConversionMap.put("LEG_TIGHT_TROUSERS", "innoxia_leg_tight_jeans");
		oldIdConversionMap.put("LEG_JEANS", "innoxia_leg_jeans");
		oldIdConversionMap.put("LEG_TROUSERS", "innoxia_leg_trousers");
		oldIdConversionMap.put("LEG_CARGO_TROUSERS", "innoxia_leg_cargo_trousers");
		oldIdConversionMap.put("LEG_YOGA_PANTS", "innoxia_leg_yoga_pants");
		oldIdConversionMap.put("LEG_ASSLESS_CHAPS", "innoxia_leg_assless_chaps");
		oldIdConversionMap.put("LEG_CROTCHLESS_CHAPS", "innoxia_leg_crotchless_chaps");

		oldIdConversionMap.put("GROIN_LACY_PANTIES", "innoxia_groin_lacy_panties");
		oldIdConversionMap.put("GROIN_PANTIES", "innoxia_groin_panties");
		oldIdConversionMap.put("GROIN_SHIMAPAN", "innoxia_groin_shimapan");
		oldIdConversionMap.put("GROIN_VSTRING", "innoxia_groin_vstring");
		oldIdConversionMap.put("GROIN_THONG", "innoxia_groin_thong");
		oldIdConversionMap.put("GROIN_BIKINI", "innoxia_groin_bikini");
		oldIdConversionMap.put("GROIN_BOYSHORTS", "innoxia_groin_boyshorts");
		oldIdConversionMap.put("GROIN_BRIEFS", "innoxia_groin_briefs");
		oldIdConversionMap.put("GROIN_BOXERS", "innoxia_groin_boxers");
		oldIdConversionMap.put("GROIN_JOCKSTRAP", "innoxia_groin_jockstrap");
		oldIdConversionMap.put("GROIN_BACKLESS_PANTIES", "innoxia_groin_backless_panties");
		oldIdConversionMap.put("GROIN_CROTCHLESS_PANTIES", "innoxia_groin_crotchless_panties");
		oldIdConversionMap.put("GROIN_CROTCHLESS_THONG", "innoxia_groin_crotchless_thong");
		oldIdConversionMap.put("GROIN_CROTCHLESS_BRIEFS", "innoxia_groin_crotchless_briefs");
		
		oldIdConversionMap.put("FOOT_ANKLE_BOOTS", "innoxia_foot_ankle_boots");
		oldIdConversionMap.put("FOOT_CHELSEA_BOOTS", "innoxia_foot_chelsea_boots");
		oldIdConversionMap.put("FOOT_HEELS", "innoxia_foot_heels");
		oldIdConversionMap.put("FOOT_LOW_TOP_SKATER_SHOES", "innoxia_foot_low_top_skater_shoes");
		oldIdConversionMap.put("FOOT_MENS_SMART_SHOES", "innoxia_foot_mens_smart_shoes");
		oldIdConversionMap.put("FOOT_PLATFORM_BOOTS", "innoxia_foot_platform_boots");
		oldIdConversionMap.put("FOOT_STILETTO_HEELS", "innoxia_foot_stiletto_heels");
		oldIdConversionMap.put("FOOT_THIGH_HIGH_BOOTS", "innoxia_foot_thigh_high_boots");
		oldIdConversionMap.put("FOOT_TRAINERS", "innoxia_foot_trainers");
		oldIdConversionMap.put("FOOT_WORK_BOOTS", "innoxia_foot_work_boots");

		oldIdConversionMap.put("TORSO_TSHIRT", "innoxia_torso_tshirt");
		oldIdConversionMap.put("TORSO_OXFORD_SHIRT", "innoxia_torso_long_sleeved_shirt");
		oldIdConversionMap.put("TORSO_SHORT_SLEEVE_SHIRT", "innoxia_torso_short_sleeved_shirt");
		oldIdConversionMap.put("TORSO_BLOUSE", "innoxia_torso_blouse");
		oldIdConversionMap.put("TORSO_SLEEVELESS_TURTLENECK", "innoxia_torso_sleeveless_turtleneck");
		oldIdConversionMap.put("TORSO_KEYHOLE_CROPTOP", "innoxia_torso_keyhole_croptop");
		oldIdConversionMap.put("TORSO_SHORT_CROPTOP", "innoxia_torso_short_croptop");
		oldIdConversionMap.put("TORSO_FISHNET_TOP", "innoxia_torso_fishnet_top");
		oldIdConversionMap.put("TORSO_CAMITOP_STRAPS", "innoxia_torso_cami_straps");
		oldIdConversionMap.put("TORSO_SKATER_DRESS", "innoxia_torso_skater_dress");
		oldIdConversionMap.put("TORSO_CORSET_DRESS", "innoxia_torso_corset_dress");
		oldIdConversionMap.put("TORSO_VIRGIN_KILLER_SWEATER", "innoxia_torso_virgin_killer_sweater");
		oldIdConversionMap.put("TORSO_SLIP_DRESS", "innoxia_torso_slip_dress");
		oldIdConversionMap.put("TORSO_PLUNGE_DRESS", "innoxia_torso_plunge_dress");
		oldIdConversionMap.put("TORSO_LONG_SLEEVE_DRESS", "innoxia_torso_long_sleeve_dress");
		oldIdConversionMap.put("TORSO_BODYCONZIP_DRESS", "innoxia_torso_bodyconzip_dress");

		oldIdConversionMap.put("MEGA_MILK", "innoxia_torso_tshirt_megamilk");
		
		oldIdConversionMap.put("STOMACH_LOWBACK_BODY", "innoxia_stomach_lowback_body");
		oldIdConversionMap.put("STOMACH_UNDERBUST_CORSET", "innoxia_stomach_underbust_corset");
		oldIdConversionMap.put("STOMACH_OVERBUST_CORSET", "innoxia_stomach_overbust_corset");
		oldIdConversionMap.put("STOMACH_SARASHI", "innoxia_stomach_sarashi");
		
		oldIdConversionMap.put("TORSO_OVER_HOODIE", "innoxia_torsoOver_hoodie");
		oldIdConversionMap.put("TORSO_OVER_OPEN_CARDIGAN", "innoxia_torsoOver_open_front_cardigan");
		oldIdConversionMap.put("TORSO_OVER_BLAZER", "innoxia_torsoOver_blazer");
		oldIdConversionMap.put("TORSO_OVER_COAT_DRESS", "innoxia_torsoOver_dress_coat");
		oldIdConversionMap.put("TORSO_OVER_CLOAK", "innoxia_torsoOver_hooded_cloak");
		oldIdConversionMap.put("TORSO_RIBBED_SWEATER", "innoxia_torsoOver_ribbed_jumper");
		oldIdConversionMap.put("TORSO_OVER_CHRISTMAS_SWEATER", "innoxia_torsoOver_christmas_jumper");
		oldIdConversionMap.put("TORSO_KEYHOLE_SWEATER", "innoxia_torsoOver_keyhole_jumper");
		oldIdConversionMap.put("TORSO_OVER_SUIT_JACKET", "innoxia_torsoOver_suit_jacket");
		oldIdConversionMap.put("TORSO_OVER_WOMENS_LEATHER_JACKET", "innoxia_torsoOver_womens_leather_jacket");
		
		oldIdConversionMap.put("SOCK_SOCKS", "innoxia_sock_socks");
		oldIdConversionMap.put("SOCK_TRAINER_SOCKS", "innoxia_sock_trainer_socks");
		oldIdConversionMap.put("SOCK_KNEEHIGH_SOCKS", "innoxia_sock_kneehigh_socks");
		oldIdConversionMap.put("SOCK_STOCKINGS", "innoxia_sock_stockings");
		oldIdConversionMap.put("SOCK_THIGHHIGH_SOCKS", "innoxia_sock_thighhigh_socks");
		oldIdConversionMap.put("SOCK_THIGHHIGH_SOCKS_STRIPED", "innoxia_sock_thighhigh_socks_striped");
		oldIdConversionMap.put("SOCK_TIGHTS", "innoxia_sock_pantyhose");
		oldIdConversionMap.put("SOCK_FISHNET_STOCKINGS", "innoxia_sock_fishnets");
		oldIdConversionMap.put("SOCK_TOELESS_STRIPED_STOCKINGS", "innoxia_sock_toeless_striped_stockings");

		oldIdConversionMap.put("WRIST_BANGLE", "innoxia_wrist_bangle");
		
		oldIdConversionMap.put("innoxia_insertableVibrator_insertable_vibrator", "innoxia_vagina_insertable_dildo");

		oldIdConversionMap.put("dsg_eep_servequipset_enfdjacket_pc", "dsg_eep_servequipset_enfdjacket");
		
		
		commonClothingMap = new EnumMap<>(InventorySlot.class);
		commonClothingMapFemale = new EnumMap<>(InventorySlot.class);
		commonClothingMapMale = new EnumMap<>(InventorySlot.class);
		commonClothingMapAndrogynous = new EnumMap<>(InventorySlot.class);
		commonClothingMapFemaleIncludingAndrogynous = new EnumMap<>(InventorySlot.class);
		commonClothingMapMaleIncludingAndrogynous = new EnumMap<>(InventorySlot.class);
		
		for(InventorySlot slot : InventorySlot.values()) {
			commonClothingMap.put(slot, new ArrayList<>());
			commonClothingMapFemale.put(slot, new ArrayList<>());
			commonClothingMapMale.put(slot, new ArrayList<>());
			commonClothingMapAndrogynous.put(slot, new ArrayList<>());
			commonClothingMapFemaleIncludingAndrogynous.put(slot, new ArrayList<>());
			commonClothingMapMaleIncludingAndrogynous.put(slot, new ArrayList<>());
		}
		
		coreClothingSlots = Util.newArrayListOfValues(InventorySlot.TORSO_UNDER, InventorySlot.LEG);
		lingerieSlots = Util.newArrayListOfValues(InventorySlot.CHEST, InventorySlot.GROIN, InventorySlot.STOMACH, InventorySlot.SOCK);

		
		allClothing = new ArrayList<>();
		moddedClothingList = new ArrayList<>();
		

		// Modded clothing types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/clothing");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try{
					String id = innerEntry.getKey();
					AbstractClothingType ct = new AbstractClothingType(innerEntry.getValue(), entry.getKey()) {};
					moddedClothingList.add(ct);
					clothingToIdMap.put(ct, id);
					idToClothingMap.put(id, ct);

					if(ct.getRarity()==Rarity.COMMON && !ct.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)) {
						commonClothingMap.get(ct.getEquipSlots().get(0)).add(ct);
						
						if (ct.getFemininityRestriction() == Femininity.FEMININE) {
							commonClothingMapFemale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
							commonClothingMapAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
							commonClothingMapMale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
						}
					}
					
				} catch(XMLLoadException ex){ // we want to catch any errors here; we shouldn't want to load any mods that are invalid as that may cause severe bugs
					System.err.println("Loading modded clothing failed at 'ClothingType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					System.err.println(ex);
				}
			}
		}
		
		allClothing.addAll(moddedClothingList);
		
		
		// External res clothing types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/clothing");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractClothingType ct = new AbstractClothingType(innerEntry.getValue(), entry.getKey()) {};
					allClothing.add(ct);
					clothingToIdMap.put(ct, id);
					idToClothingMap.put(id, ct);

					if(ct.getRarity()==Rarity.COMMON && !ct.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)) {
						commonClothingMap.get(ct.getEquipSlots().get(0)).add(ct);
						
						if (ct.getFemininityRestriction() == Femininity.FEMININE) {
							commonClothingMapFemale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
							commonClothingMapAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
							commonClothingMapMale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
						}
					}
					
				} catch(Exception ex) {
					System.err.println("Loading clothing failed at 'ClothingType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		
		// Add in hard-coded clothing:
		
		Field[] fields = ClothingType.class.getFields();
		
		for(Field f : fields) {
			if (AbstractClothingType.class.isAssignableFrom(f.getType())) {
				AbstractClothingType ct;
				try {
					ct = ((AbstractClothingType) f.get(null));

					// I feel like this is stupid :thinking:
					clothingToIdMap.put(ct, f.getName());
					idToClothingMap.put(f.getName(), ct);
					
					allClothing.add(ct);
					
					if(ct.isDefaultSlotCondom()) {
						continue;
					}
					
					if(ct.getRarity()==Rarity.COMMON && !ct.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)) {
						commonClothingMap.get(ct.getEquipSlots().get(0)).add(ct);
						
						if (ct.getFemininityRestriction() == Femininity.FEMININE) {
							commonClothingMapFemale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.ANDROGYNOUS || ct.getFemininityRestriction() == null) {
							commonClothingMapAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapFemaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
							
						} else if (ct.getFemininityRestriction() == Femininity.MASCULINE) {
							commonClothingMapMale.get(ct.getEquipSlots().get(0)).add(ct);
							commonClothingMapMaleIncludingAndrogynous.get(ct.getEquipSlots().get(0)).add(ct);
						}
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		setClothing = new HashMap<>();
		for(AbstractClothingType ct : allClothing) {
			if(ct.getClothingSet()!=null) {
				setClothing.putIfAbsent(ct.getClothingSet(), new ArrayList<>());
				setClothing.get(ct.getClothingSet()).add(ct);
			}
		}
		
//  	System.out.println(allClothing.size());
		
		//TODO shouldn't this be handled in outfit files?
		suitableFeminineClothing.put(Occupation.NPC_PROSTITUTE,
				Util.newArrayListOfValues(
						ClothingType.getClothingTypeFromId("innoxia_ankle_anklet"),
						ClothingType.getClothingTypeFromId("innoxia_chest_lacy_plunge_bra"),
						
						ClothingType.getClothingTypeFromId("innoxia_chest_open_cup_bra"),
						ClothingType.getClothingTypeFromId("innoxia_chest_plunge_bra"),
						ClothingType.getClothingTypeFromId("innoxia_eye_aviators"),
						ClothingType.getClothingTypeFromId("innoxia_finger_ring"),
						ClothingType.getClothingTypeFromId("innoxia_foot_chelsea_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_ankle_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_heels"),
						ClothingType.getClothingTypeFromId("innoxia_foot_thigh_high_boots"),
						ClothingType.getClothingTypeFromId("innoxia_foot_stiletto_heels"),
						ClothingType.getClothingTypeFromId("innoxia_groin_backless_panties"),
						ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_panties"),
						ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_thong"),
						ClothingType.getClothingTypeFromId("innoxia_groin_lacy_panties"),
						ClothingType.getClothingTypeFromId("innoxia_groin_thong"),
						ClothingType.getClothingTypeFromId("innoxia_groin_vstring"),
						ClothingType.getClothingTypeFromId("innoxia_hand_elbow_length_gloves"),
						ClothingType.getClothingTypeFromId("innoxia_head_headband"),
						ClothingType.getClothingTypeFromId("innoxia_head_headband_bow"),
						ClothingType.getClothingTypeFromId("innoxia_leg_crotchless_chaps"),
						ClothingType.getClothingTypeFromId("innoxia_leg_micro_skirt_belted"),
						ClothingType.getClothingTypeFromId("innoxia_leg_micro_skirt_pleated"),
						ClothingType.getClothingTypeFromId("innoxia_leg_mini_skirt"),
						ClothingType.getClothingTypeFromId("innoxia_leg_skirt"),
						ClothingType.getClothingTypeFromId("innoxia_neck_heart_necklace"),
						ClothingType.getClothingTypeFromId("innoxia_neck_ankh_necklace"),
						ClothingType.getClothingTypeFromId("innoxia_tape_roll"),
						ClothingType.getClothingTypeFromId("innoxia_hand_fishnet_gloves"),
						ClothingType.getClothingTypeFromId("innoxia_sock_fishnets"),
						ClothingType.getClothingTypeFromId("innoxia_sock_pantyhose"),
						ClothingType.getClothingTypeFromId("innoxia_stomach_overbust_corset"),
						ClothingType.getClothingTypeFromId("innoxia_stomach_underbust_corset"),
						ClothingType.getClothingTypeFromId("innoxia_torso_fishnet_top"),
						ClothingType.getClothingTypeFromId("innoxia_torso_keyhole_croptop"),
						ClothingType.getClothingTypeFromId("innoxia_torso_short_croptop"),
						ClothingType.getClothingTypeFromId("innoxia_wrist_bangle"),
						ClothingType.WRIST_WOMENS_WATCH,
						
						ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_lip_double_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_gemstone_barbell"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_basic_barbell_pair"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_nose_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_penis_ring"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_basic_barbell"),
						ClothingType.getClothingTypeFromId("innoxia_piercing_ringed_barbell")));
	}
	
	public static List<AbstractClothingType> getAllClothing() {
		return new ArrayList<>(allClothing);
	}
	
	public static List<AbstractClothingType> getAllClothingInSet(AbstractSetBonus setBonus) {
		return new ArrayList<>(setClothing.get(setBonus));
	}

	public static List<AbstractClothingType> getModdedClothingList() {
		return new ArrayList<>(moddedClothingList);
	}

	public static List<InventorySlot> getCoreClothingSlots() {
		return new ArrayList<>(coreClothingSlots);
	}

	public static List<InventorySlot> getLingerieSlots() {
		return new ArrayList<>(lingerieSlots);
	}
	
	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMap() {
		return new HashMap<>(commonClothingMap);
	}

	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapFemale() {
		return new HashMap<>(commonClothingMapFemale);
	}

	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapMale() {
		return new HashMap<>(commonClothingMapMale);
	}

	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapAndrogynous() {
		return new HashMap<>(commonClothingMapAndrogynous);
	}
	
	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapFemaleIncludingAndrogynous() {
		return new HashMap<>(commonClothingMapFemaleIncludingAndrogynous);
	}
	
	public static Map<InventorySlot, List<AbstractClothingType>> getCommonClothingMapMaleIncludingAndrogynous() {
		return new HashMap<>(commonClothingMapMaleIncludingAndrogynous);
	}

}
