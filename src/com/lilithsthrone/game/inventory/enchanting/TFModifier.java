package com.lilithsthrone.game.inventory.enchanting;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.7
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum TFModifier {
	
	NONE("Пусто",
		"Нет модификатора",
		"nullification",
		"modifier_circle",
		PresetColour.TEXT_GREY,
		Rarity.COMMON),
	
	// Pos/Neg
	
	ARCANE_BOOST("арканный усилитель",
			"Применяет более мощный эффект по отношению к основной эссенции.",
			"boosted",
			"modifier_circle_arcane",
			PresetColour.GENERIC_ARCANE,
			Rarity.COMMON),

	REMOVAL("removal",
			"Применяет эффект, при котором часть тела основного модификатора удаляется.",
			"vanishing",
			"modifier_circle_removal",
			PresetColour.GENERIC_TERRIBLE,
			Rarity.UNCOMMON),
	
	// Misc:
	
	ORIENTATION_GYNEPHILIC("gynephilia",
			"Применяет эффект, изменяющий ориентацию на гинефилию.",
			"gynephilia",
			"modifier_circle_orientation_gynephilic",
			PresetColour.FEMININE_PLUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_AMBIPHILIC("ambiphilia",
			"Применяет эффект, изменяющий ориентацию на амбифилию.",
			"ambiphilia",
			"modifier_circle_orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_ANDROPHILIC("androphilia",
			"Применяет эффект, изменяющий ориентацию на андрофилию.",
			"androphilia",
			"modifier_circle_orientation_androphilic",
			PresetColour.MASCULINE_PLUS,
			Rarity.LEGENDARY),
	
	PERSONALITY_TRAIT_SPEECH_LISP("lisp",
			"Применяет эффект, заставляющий человека шепелявить.",
			"lisp",
			"modifier_circle_speech",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.LEGENDARY),

	PERSONALITY_TRAIT_SPEECH_STUTTER("stutter",
			"Применяет эффект, заставляющий человека заикаться.",
			"stutter",
			"modifier_circle_speech",
			PresetColour.BASE_PINK_SALMON,
			Rarity.LEGENDARY),

	PERSONALITY_TRAIT_SPEECH_SLOVENLY("небрежная речь",
			"Применяет эффект, связанный с небрежной манерой речи человека.",
			"slovenly",
			"modifier_circle_speech",
			PresetColour.BASE_BROWN,
			Rarity.LEGENDARY),
	
	// Attributes:

	HEALTH_MAXIMUM(AttributeCategory.STRENGTH,
			Attribute.HEALTH_MAXIMUM,
			"Применяет эффект, связанный с основным атрибутом «Максимальное Здоровье».",
			"modifier_circle_strength",//"modifier_circle_health",
			Rarity.EPIC),

	MANA_MAXIMUM(AttributeCategory.INTELLIGENCE,
			Attribute.MANA_MAXIMUM,
			"Применяет эффект, связанный с основным атрибутом «Максимальная Аура».",
			"modifier_circle_mana",
			Rarity.EPIC),
	
	STRENGTH(AttributeCategory.STRENGTH,
			Attribute.MAJOR_PHYSIQUE,
			"Применяет эффект, связанный с основным атрибутом «Телосложение».",
			"modifier_circle_strength",
			Rarity.LEGENDARY),
	
	INTELLIGENCE(AttributeCategory.INTELLIGENCE,
			Attribute.MAJOR_ARCANE,
			"Применяет эффект, связанный с основным атрибутом «Arcane».",
			"modifier_circle_intelligence",
			Rarity.LEGENDARY),
	
	CORRUPTION(AttributeCategory.CORRUPTION,
			Attribute.MAJOR_CORRUPTION,
			"Применяет эффект, связанный с основным атрибутом «Порочность».",
			"modifier_circle_corruption",
			Rarity.LEGENDARY),

	/** This TFModifier is a special case, as it is not added to the available clothing TF lists.
	 * It is simply defined so that modded clothing can add this as a secondary TFModifier (to the primary TFModifier 'CLOTHING_MAJOR_ATTRIBUTE') to increase enchantment capacity of the wearer. */
	ENCHANTMENT_LIMIT(AttributeCategory.CORRUPTION,
			Attribute.ENCHANTMENT_LIMIT,
			"Применяет эффект, связанный со вторичным атрибутом «Нестабильность зачарования».",
			"modifier_circle_corruption",
			Rarity.LEGENDARY),
	
	FERTILITY(AttributeCategory.CORRUPTION,
			Attribute.FERTILITY,
			"Применяет эффект, связанный со вторичным атрибутом «Плодовитость».",
			"modifier_circle_fertility",
			Rarity.RARE),

	VIRILITY(AttributeCategory.CORRUPTION,
			Attribute.VIRILITY,
			"Применяет эффект, связанный со вторичным атрибутом «Мужественность».",
			"modifier_circle_virility",
			Rarity.RARE),
	
	SPELL_COST_MODIFIER(AttributeCategory.INTELLIGENCE,
			Attribute.SPELL_COST_MODIFIER,
			"Применяет эффект, связанный с вторичным атрибутом '"+Attribute.SPELL_COST_MODIFIER.getName()+"'.",
			"modifier_circle_spell_efficiency",
			Rarity.RARE),
	
	
	CRITICAL_DAMAGE(AttributeCategory.STRENGTH,
			Attribute.CRITICAL_DAMAGE,
			"Применяет эффект, связанный с вторичным атрибутом 'Критический урон'.",
			"modifier_circle_critical_damage",
			Rarity.RARE),
	
	
	DAMAGE_LUST(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_LUST,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон соблазнения'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_SPELLS(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_SPELLS,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон заклинания'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_UNARMED(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_UNARMED,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон без оружия'.",
			"modifier_circle_damage_unarmed",
			Rarity.RARE),

	DAMAGE_MELEE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_MELEE_WEAPON,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон холодным оружием'.",
			"modifier_circle_damage_melee",
			Rarity.RARE),

	DAMAGE_RANGED_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_RANGED_WEAPON,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон дистанционным оружием'.",
			"modifier_circle_damage_ranged",
			Rarity.RARE),
	
	DAMAGE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"Применяет эффект, связанный с вторичным атрибутом 'Физический урон'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_FIRE,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон огнем'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_ICE,
			"Применяет эффект, связанный с вторичным атрибутом 'Урон льдом'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_POISON,
			"Применяет эффект, связанный с вторичным атрибутом «Урон ядом».",
			"modifier_circle_damage",
			Rarity.RARE),
	
	/**
	 * Utility value for initialising a weapon to have attribute bonuses related to its damage type.
	 */
	DAMAGE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"Применяет эффект урона, связанный с типом урона оружия.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	
	RESISTANCE_LUST(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_LUST,
			"Применяет эффект, связанный с вторичным атрибутом «Щит соблазна».",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"Применяет эффект, связанный с вторичным атрибутом «Физический щит».",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_FIRE,
			"Применяет эффект, связанный с вторичным атрибутом «Огненный щит».",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_ICE,
			"Применяет эффект, связанный с вторичным атрибутом «Ледяной щит».",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_POISON,
			"Применяет эффект, связанный с вторичным атрибутом «Ядовитый щит».",
			"modifier_circle_resistance",
			Rarity.RARE),

	/**
	 * Utility value for initialising a weapon to have attribute bonuses related to its damage type.
	 */
	RESISTANCE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"Применяет эффект щита, связанный с типом урона оружия.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	
	// Clothing parts:
	
	CLOTHING_MAJOR_ATTRIBUTE("основной атрибут",
			"Применяет модификатор к основному атрибуту.",
			"attribute",
			"modifier_circle_attribute_major",
			PresetColour.GENERIC_ATTRIBUTE,
			Rarity.LEGENDARY),
	
	CLOTHING_ATTRIBUTE("attribute",
			"Применяет модификатор к атрибуту.",
			"attribute",
			"modifier_circle_attribute",
			PresetColour.GENERIC_ATTRIBUTE,
			Rarity.UNCOMMON),

	CLOTHING_SPECIAL("особые эффекты",
			"Применяет особый эффект.",
			"special",
			"modifier_circle_special",
			PresetColour.BASE_TEAL,
			Rarity.LEGENDARY),

	CLOTHING_SEXUAL("сексуальные эффекты",
			"Применяет сексуальный эффект.",
			"sexual",
			"modifier_circle_sexual",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.EPIC),
	
	CLOTHING_ENSLAVEMENT("enslavement",
			"Эта одежда порабощает того, кто её носит.",
			"enslavement",
			"modifier_circle_enslavement",
			PresetColour.BASE_PURPLE,
			Rarity.LEGENDARY),
	
	CLOTHING_SEALING("sealing",
			"Эта одежда плотно прирастает к телу владельца.",
			"sealing",
			"modifier_circle_sealing",
			PresetColour.SEALED,
			Rarity.LEGENDARY),
	
	//CLOTHING_ANTI_SELF_TRANSFORMATION
	CLOTHING_SERVITUDE("servitude",
			"Владелец не может самостоятельно трансформироваться или снять эту одежду.",
			"servitude",
			"modifier_circle_servitude",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.LEGENDARY),

	CLOTHING_CONDOM("прочность презерватива",
			"Повышает базовую прочность презерватива.",
			"прочность презерватива",
			"modifier_circle_resistance",
			PresetColour.BASE_GREEN,
			Rarity.COMMON),

	CLOTHING_CREAMPIE_RETENTION("удержание спермы",
			"Предотвращает постепенное впитывание спермы.",
			"удержание спермы",
			"modifier_circle_creampie_retention",
			PresetColour.CUM,
			Rarity.EPIC),

	CLOTHING_VIBRATION("vibration",
			"Заставляет этот предмет одежды вибрировать при ношении.",
			"vibration",
			"modifier_circle_vibration",
			PresetColour.BASE_PINK,
			Rarity.EPIC),

	CLOTHING_ORGASM_PREVENTION("предотвращение оргазма",
			"Делает носителя неспособным достичь оргазма.",
			"предотвращение оргазма",
			"modifier_circle_orgasm_prevention",
			PresetColour.BASE_CRIMSON,
			Rarity.EPIC),
	
	// Racial parts:

	TF_MATERIAL_FLESH("flesh",
			"Превращает материал тела человека в плоть.",
			"flesh",
			"modifier_circle_tf_material_flesh",
			PresetColour.BASE_CRIMSON,
			Rarity.LEGENDARY),
	
	TF_ANTENNA("antennae",
			"Применяет трансформирующий эффект к вашим усикам.",
			"antennae",
			"modifier_circle_tf_antenna",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_ARMS("arms",
			"Применяет трансформирующий эффект к вашим рукам.",
			"arms",
			"modifier_circle_tf_arm",
			PresetColour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_ASS("ass",
			"Оказывает трансформирующее воздействие на твою задницу.",
			"asses",
			"modifier_circle_tf_ass",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS("breasts",
			"Оказывает трансформирующее воздействие на твою грудь.",
			"breasts",
			"modifier_circle_tf_breast",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS_CROTCH("crotch-boobs",
			"Оказывает трансформирующее воздействие на твои молочные железы/вымени.",
			"crotch-boobs",
			"modifier_circle_tf_breast_crotch",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CORE("core",
			"Оказывает трансформирующее воздействие на твое тело.",
			"core",
			"modifier_circle_tf_core",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.RARE),
	
	TF_EARS("ears",
			"Оказывает трансформирующее воздействие на твои уши.",
			"ears",
			"modifier_circle_tf_ear",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_EYES("eyes",
			"Оказывает трансформирующее воздействие на твои глаза.",
			"eyes",
			"modifier_circle_tf_eye",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_FACE("face",
			"Оказывает трансформирующее воздействие на твое лицо.",
			"faces",
			"modifier_circle_tf_face",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_HAIR("hair",
			"Оказывает трансформирующее воздействие на твои волосы.",
			"hair",
			"modifier_circle_tf_hair",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_HORNS("horns",
			"Оказывает трансформирующее воздействие на твои рога.",
			"horns",
			"modifier_circle_tf_horn",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_LEGS("legs",
			"Оказывает трансформирующее воздействие на твои ноги.",
			"legs",
			"modifier_circle_tf_leg",
			PresetColour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_PENIS("penis",
			"Оказывает трансформирующее воздействие на ваш пенис.",
			"cocks",
			"modifier_circle_tf_penis",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),

	TF_PENIS_URETHRA("мужская уретра",
			"Оказывает трансформирующее воздействие на уретру пениса.",
			"уретрах члена",
			"modifier_circle_tf_penis",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.EPIC),
	
	TF_SKIN("torso",
			"Оказывает трансформирующее воздействие на ваш торс.",
			"torso",
			"modifier_circle_tf_skin",
			PresetColour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_SPINNERET("spinneret",
			"Оказывает трансформирующее воздействие на ваше паучье веретено.",
			"spinnerets",
			"modifier_circle_tf_spinneret",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_TAIL("tail",
			"Оказывает трансформирующее воздействие на ваш хвост.",
			"tails",
			"modifier_circle_tf_tail",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),

	TF_TENTACLE("tentacle",
			"Оказывает трансформирующее воздействие на ваши щупальца.",
			"tentacles",
			"modifier_circle_tf_tentacle",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_VAGINA("vagina",
			"Оказывает трансформирующее воздействие на вашу вагину.",
			"pussies",
			"modifier_circle_tf_vagina",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),

	TF_VAGINA_URETHRA("женская уретра",
			"Оказывает трансформирующее воздействие на уретру вагины.",
			"уретрах киски",
			"modifier_circle_tf_vagina",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.EPIC),
	
	TF_WINGS("wings",
			"Придает вашим крыльям трансформирующий эффект.",
			"wings",
			"modifier_circle_tf_wing",
			PresetColour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_MILK("milk",
			"Придает вашему молоку трансформирующий эффект.",
			"milk",
			"modifier_circle_tf_milk",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_MILK_CROTCH("udder-milk",
			"Придает вашему выменному молоку трансформирующий эффект.",
			"udder-milk",
			"modifier_circle_tf_milk_crotch",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CUM("cum",
			"Придает вашей сперме трансформирующий эффект.",
			"cum",
			"modifier_circle_tf_cum",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_GIRLCUM("girlcum",
			"Придает вашей девичьей сперме трансформирующий эффект.",
			"girlcum",
			"modifier_circle_tf_girlcum",
			PresetColour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	
	// Body part modifiers:
	
	TF_TYPE_1("трансформирующий I",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_1",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_2("трансформирующий II",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_2",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_3("трансформирующий III",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_3",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_4("трансформирующее IV",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_4",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_5("трансформирующее V",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_5",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),

	TF_TYPE_6("трансформирующее VI",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_6",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_7("трансформирующее VII",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_7",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_8("трансформирующее VIII",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_8",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_9("трансформирующее IX",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_9",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_10("трансформирующее X",
			"Превращает соответствующую часть тела в часть тела другой расы.",
			"transformative",
			"modifier_circle_tf_10",
			PresetColour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_MOD_FEMININITY("femininity",
			"Применяет эффект, изменяющий женственность пользователя.",
			"feminine",
			"modifier_circle_femininity",
			PresetColour.ANDROGYNOUS,
			Rarity.COMMON),
	
	TF_MOD_COUNT("count",
			"Применяет эффект, связанный с добавлением или удалением лишних частей тела.",
			"counting",
			"modifier_circle_count",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_COUNT_SECONDARY("вторичный счет",
			"Применяет эффект, связанный с добавлением или удалением лишних частей тела.",
			"counting",
			"modifier_circle_count_secondary",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_BODY_HAIR("hairiness",
			"Применяет эффект, связанный с добавлением или удалением волос на теле.",
			"hairy",
			"modifier_circle_bodyHair",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_FOOT_STRUCTURE_PLANTIGRADE("стопоходящие ступни",
			"Применяет эффект, придающий пользователю стопоходящую структуру стоп.",
			"стопоходящие ступни",
			"modifier_circle_tf_footStructure_plantigrade",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.LEGENDARY),
	
	TF_MOD_FOOT_STRUCTURE_DIGITIGRADE("пальцеходящие ступни",
			"Применяет эффект, придающий пользователю пальцеходящую структуру стоп.",
			"пальцеходящие ступни",
			"modifier_circle_tf_footStructure_digitigrade",
			PresetColour.BASE_BROWN_DARK,
			Rarity.LEGENDARY),
	
	TF_MOD_FOOT_STRUCTURE_UNGULIGRADE("копытоходящие ступни",
			"Применяет эффект, придающий пользователю копытоходящую структуру стоп.",
			"копытоходящие ступни",
			"modifier_circle_tf_footStructure_unguligrade",
			PresetColour.BASE_TAN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_BIPEDAL("двуногое тело",
			"Применяет эффект, наделяющий пользователя двуногим телом.",
			"двуногое тело",
			"modifier_circle_tf_legConfig_bipedal",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAUR("тело тавра",
			"Применяет эффект, наделяющий пользователя телом тавра.",
			"тело тавра",
			"modifier_circle_tf_legConfig_taur",
			PresetColour.BASE_TAN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAIL_LONG("тело с длинным хвостом",
			"Применяет эффект, наделяющий пользователя телом с длинным хвостом.",
			"тело с длинным хвостом",
			"modifier_circle_tf_legConfig_tail_long",
			PresetColour.BASE_GREEN,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_TAIL("тело с хвостом",
			"Применяет эффект, наделяющий пользователя телом с хвостом.",
			"тело с хвостом",
			"modifier_circle_tf_legConfig_tail",
			PresetColour.BASE_AQUA,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_ARACHNID("тело паукообразного",
			"Применяет эффект, наделяющий пользователя телом паукообразного.",
			"тело паукообразного",
			"modifier_circle_tf_legConfig_arachnid",
			PresetColour.BASE_BLACK,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_CEPHALOPOD("тело головоногого",
			"Применяет эффект, наделяющий пользователя телом головоногого.",
			"тело головоногого",
			"modifier_circle_tf_legConfig_cephalopod",
			PresetColour.BASE_RED,
			Rarity.LEGENDARY),

	TF_MOD_LEG_CONFIG_AVIAN("птичье тело",
			"Применяет эффект, наделяющий пользователя птичьим телом.",
			"птичье тело",
			"modifier_circle_tf_legConfig_avian",
			PresetColour.BASE_YELLOW,
			Rarity.LEGENDARY),
	
	TF_MOD_LEG_CONFIG_WINGED_BIPED("крылатое двуногое тело",
			"Применяет эффект, дарующий пользователю крылатое двуногое тело.",
			"крылатое двуногое тело",
			"modifier_circle_tf_legConfig_avian",
			PresetColour.BASE_YELLOW,
			Rarity.LEGENDARY),
	
	TF_MOD_INTERNAL("internal",
			"Применяет эффект, связанный с превращением части тела во внутренний орган.",
			"internal",
			"modifier_circle_internal",
			PresetColour.BASE_BLUE_STEEL,
			Rarity.COMMON),
	
	TF_MOD_SIZE("size",
			"Применяет эффект, связанный с изменением размера определенной части тела.",
			"resizing",
			"modifier_circle_size",
			PresetColour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_SIZE_SECONDARY("вторичный размер",
			"Применяет эффект, связанный с изменением размера другой части тела.",
			"resizing",
			"modifier_circle_size_secondary",
			PresetColour.BASE_LILAC_LIGHT,
			Rarity.COMMON),

	TF_MOD_SIZE_TERTIARY("третичный размер",
			"Применяет эффект, связанный с изменением размера еще одной части тела.",
			"resizing",
			"modifier_circle_size_tertiary",
			PresetColour.BASE_ROSE,
			Rarity.COMMON),
	

	TF_MOD_REGENERATION("regeneration",
			"Применяет эффект, связанный со скоростью регенерации жидкостей.",
			"refilling",
			"modifier_circle_regeneration",
			PresetColour.BASE_GREEN_LIGHT,
			Rarity.COMMON),
	
	// Orifices:
	
	TF_MOD_CAPACITY("capacity",
			"Применяет эффект, изменяющий вместимость отверстия.",
			"capacious",
			"modifier_circle_capacity",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),

	TF_MOD_CAPACITY_2("вместимость II",
			"Применяет эффект, изменяющий вместимость отверстия.",
			"capacious",
			"modifier_circle_capacity",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_WETNESS("wetness",
			"Применяет эффект, изменяющий влажность отверстия или выработку жидкости частью тела.",
			"wet",
			"modifier_circle_wetness",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_DEPTH("depth",
			"Применяет эффект, изменяющий глубину отверстия.",
			"deep",
			"modifier_circle_orifice_deep",
			PresetColour.BASE_GREY,
			Rarity.COMMON),
	
	TF_MOD_DEPTH_2("глубина II",
			"Применяет эффект, изменяющий глубину отверстия.",
			"deep",
			"modifier_circle_orifice_deep",
			PresetColour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY("elasticity",
			"Применяет эффект, изменяющий эластичность отверстия.",
			"elastic",
			"modifier_circle_elasticity",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY_2("эластичность II",
			"Применяет эффект, изменяющий эластичность отверстия.",
			"elastic",
			"modifier_circle_elasticity",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY("plasticity",
			"Применяет эффект, изменяющий пластичность отверстия.",
			"plastic",
			"modifier_circle_plasticity",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY_2("пластичность II",
			"Применяет эффект, изменяющий пластичность отверстия.",
			"plastic",
			"modifier_circle_plasticity",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	// modifiers:
	
	TF_MOD_ORIFICE_PUFFY("puffiness",
			"Применяет эффект, делающий отверстие припухлым.",
			"puffy",
			"modifier_circle_orifice_puffy",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED("ribbing",
			"Применяет эффект, создающий внутри отверстия ребристость.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED("tentacled",
			"Применяет эффект, создающий внутри отверстия щупальца.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED("muscled",
			"Применяет эффект, создающий внутри отверстия мускулистость.",
			"muscled",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_PUFFY_2("припухлость II",
			"Применяет эффект, делающий отверстие припухлым.",
			"puffy",
			"modifier_circle_orifice_puffy",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED_2("ребристость II",
			"Применяет эффект, создающий внутри отверстия ребристость.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED_2("щупальцевидные II",
			"Применяет эффект, создающий внутри отверстия щупальца.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED_2("мускулистые II",
			"Применяет эффект, создающий внутри отверстия мускулистость.",
			"muscled",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	// eye shapes:
	
	TF_MOD_EYE_IRIS_CIRCLE("круглые радужки",
			"Применяет эффект, придающий радужке форму обычного круга.",
			"круглые радужки",
			"modifier_circle_eye_iris_normal",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HORIZONTAL("горизонтальные радужки",
			"Применяет эффект, придающий радужке более горизонтальную форму.",
			"горизонтальные радужки",
			"modifier_circle_eye_iris_horizontal",
			PresetColour.BASE_LILAC_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_VERTICAL("вертикальные радужки",
			"Применяет эффект, придающий радужке более вертикальную форму.",
			"вертикальные радужки",
			"modifier_circle_eye_iris_vertical",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HEART("радужка в форме сердечек",
			"Применяет эффект, придающий радужке форму сердечек.",
			"радужка в форме сердечек",
			"modifier_circle_eye_iris_heart",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_STAR("радужка в форме звезд",
			"Применяет эффект, придающий радужке форму звезд.",
			"радужка в форме звезд",
			"modifier_circle_eye_iris_star",
			PresetColour.BASE_YELLOW,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_CIRCLE("круглые зрачки",
			"Применяет эффект, делающий зрачки обычной круглой формы.",
			"круглые зрачки",
			"modifier_circle_eye_iris_normal",
			PresetColour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HORIZONTAL("горизонтальные зрачки",
			"Применяет эффект, придающий зрачкам более горизонтальную форму.",
			"горизонтальные зрачки",
			"modifier_circle_eye_iris_horizontal",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_VERTICAL("горизонтальные зрачки",
			"Применяет эффект, придающий зрачкам более вертикальную форму.",
			"вертикальные зрачки",
			"modifier_circle_eye_iris_vertical",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HEART("зрачки в форме сердец",
			"Применяет эффект, придающий зрачкам форму сердец.",
			"зрачки в форме сердец",
			"modifier_circle_eye_iris_heart",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_STAR("зрачки в форме звезд",
			"Применяет эффект, придающий зрачкам форму звезд.",
			"зрачки в форме звезд",
			"modifier_circle_eye_iris_star",
			PresetColour.BASE_BLUE,
			Rarity.COMMON),
	
	// breast shapes:
	
	TF_MOD_BREAST_SHAPE_UDDERS("udders",
			"Применяет эффект, превращающий лобковую грудь в вымя.",
			"udders",
			"modifier_circle_breastShape_udders",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_ROUND("круглые груди",
			"Применяет эффект, придающий груди круглую форму.",
			"round",
			"modifier_circle_breastShape_round",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_NARROW("узкие груди",
			"Применяет эффект, придающий груди узкую форму.",
			"narrow",
			"modifier_circle_breastShape_narrow",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_WIDE("широкие груди",
			"Применяет эффект, придающий груди широкую форму.",
			"wide",
			"modifier_circle_breastShape_wide",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_POINTY("острые груди",
			"Применяет эффект, придающий груди заостренную форму.",
			"pointy",
			"modifier_circle_breastShape_pointy",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_PERKY("упругие груди",
			"Применяет эффект, придающий груди подтянутую форму.",
			"perky",
			"modifier_circle_breastShape_perky",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_SIDESET("расставленная в стороны грудь",
			"Применяет эффект, придающий груди форму, при которой соски направлены в стороны.",
			"side-set",
			"modifier_circle_breastShape_sideset",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	// nipple shapes:
	
	TF_MOD_NIPPLE_NORMAL("обычные соски",
			"Применяет эффект, делающий соски обычными.",
			"обычные соски",
			"modifier_circle_nipple_normal",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),

	TF_MOD_NIPPLE_INVERTED("втянутые соски",
			"Применяет эффект, делающий соски втянутыми.",
			"втянутые соски",
			"modifier_circle_nipple_normal",
			PresetColour.BASE_RED_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_VAGINA("соски-киски",
			"Применяет эффект, придающий соскам вид кисок.",
			"соски-киски",
			"modifier_circle_nipple_vagina",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_LIPS("lipples",
			"Применяет эффект, придающий соскам вид пары губ.",
			"lipples",
			"modifier_circle_nipple_lips",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	// areolae shapes:
	
	TF_MOD_AREOLAE_CIRCLE("круглые ареолы",
			"Применяет эффект, делающий ареолы обычной круглой формы.",
			"круглые ареолы",
			"modifier_circle_areolae_normal",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_HEART("ареолы в форме сердечек",
			"Применяет эффект, придающий ареолам форму сердечек.",
			"ареолы в форме сердечек",
			"modifier_circle_areolae_heart",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_STAR("ареолы в форме звезд",
			"Применяет эффект, придающий ареолам форму звезд.",
			"ареолы в форме звезд",
			"modifier_circle_areolae_star",
			PresetColour.BASE_YELLOW,
			Rarity.COMMON),
	
	
	// tongue modifiers:
	
	TF_MOD_TONGUE_RIBBED("ribbing",
			"Применяет эффект, добавляющий языку ребристость.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_TENTACLED("tentacled",
			"Применяет эффект, добавляющий языку щупальца.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_BIFURCATED("bifurcated",
			"Применяет эффект, делающий язык раздвоенным.",
			"bifurcated",
			"modifier_circle_tongue_bifurcated",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_WIDE("wide",
			"Применяет эффект, делающий язык широким.",
			"wide",
			"modifier_circle_tongue_wide",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_FLAT("flat",
			"Применяет эффект, делающий язык плоским.",
			"flat",
			"modifier_circle_tongue_flat",
			PresetColour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_STRONG("strong",
			"Применяет эффект, делающий язык сильным.",
			"strong",
			"modifier_circle_orifice_muscled",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON),
	
	
	// penis & clit modifiers:
	
	TF_MOD_PENIS_SHEATHED("sheathing",
			"Применяет эффект, создающий ножны на части тела.",
			"sheathed",
			"modifier_circle_penis_sheathed",
			PresetColour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_PENIS_RIBBED("ribbing",
			"Применяет эффект, делающий часть тела ребристой.",
			"ribbed",
			"modifier_circle_penis_ribbed",
			PresetColour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TENTACLED("tentacled",
			"Применяет эффект, покрывающий часть тела щупальцами.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_KNOTTED("knotting",
			"Применяет эффект, делающий часть тела узловатой.",
			"knotted",
			"modifier_circle_penis_knotted",
			PresetColour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TAPERED("tapering",
			"Применяет эффект, сужающий часть тела к концу.",
			"tapered",
			"modifier_circle_penis_tapered",
			PresetColour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_PENIS_FLARED("flaring",
			"Применяет эффект, расширяющий часть тела к концу.",
			"flared",
			"modifier_circle_penis_flared",
			PresetColour.BASE_BROWN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BLUNT("blunting",
			"Применяет эффект, делающий часть тела тупой.",
			"blunt",
			"modifier_circle_penis_blunt",
			PresetColour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BARBED("barbing",
			"Применяет эффект, делающий часть тела зазубренной.",
			"barbed",
			"modifier_circle_penis_barbed",
			PresetColour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_PENIS_VEINY("veins",
			"Применяет эффект, делающий часть тела венозной.",
			"veiny",
			"modifier_circle_penis_veiny",
			PresetColour.BASE_PINK_SALMON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_PREHENSILE("prehensile",
			"Применяет эффект, делающий часть тела хватательной.",
			"prehensile",
			"modifier_circle_penis_prehensile",
			PresetColour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PENIS_OVIPOSITOR("ovipositor",
			"Применяет эффект, превращающий часть тела в яйцеклад.",
			"oviposition",
			"modifier_circle_penis_ovipositor",
			PresetColour.BASE_WHITE,
			Rarity.COMMON),

	
	TF_MOD_CUM_EXPULSION("извержение спермы",
			"Применяет эффект, изменяющий количество спермы, выбрасываемой при каждом оргазме.",
			"cumming",
			"modifier_circle_squirter",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	
	// Vagina:
	
	TF_MOD_VAGINA_SQUIRTER("squirter",
			"Применяет эффект, превращающий кого-то в сквиртера.",
			"squirting",
			"modifier_circle_squirter",
			PresetColour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_VAGINA_EGG_LAYER("egg-layer",
			"Применяет эффект, позволяющий кому-то откладывать яйца.",
			"egg-laying",
			"modifier_circle_vagina_eggLayer",
			PresetColour.EGG,
			Rarity.COMMON),

	TF_MOD_HYMEN("hymen",
			"Применяет эффект, связанный с ростом или удалением девственной плевы.",
			"hymen",
			"modifier_circle_tf_vagina",
			PresetColour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	
	// fluid modifiers:
	
	TF_MOD_FLUID_MUSKY("musky",
			"Применяет эффект, связанный с изменением жидкости.",
			"musk",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BROWN,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.MUSKY.getColour();
		}
	},
	
	TF_MOD_FLUID_VISCOUS("viscous",
			"Применяет эффект, связанный с изменением жидкости.",
			"viscous",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_GREY,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.VISCOUS.getColour();
		}
	},
	
	TF_MOD_FLUID_STICKY("sticky",
			"Применяет эффект, связанный с изменением жидкости.",
			"sticky",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_YELLOW_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.STICKY.getColour();
		}
	},
	
	TF_MOD_FLUID_SLIMY("slimy",
			"Применяет эффект, связанный с изменением жидкости.",
			"slimy",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BLUE_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.SLIMY.getColour();
		}
	},
	
	TF_MOD_FLUID_BUBBLING("bubbling",
			"Применяет эффект, связанный с изменением жидкости.",
			"bubbling",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_AQUA,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.BUBBLING.getColour();
		}
	},
	
	TF_MOD_FLUID_ALCOHOLIC("крепкоалкогольный",
			"Применяет эффект, связанный с изменением жидкости.",
			"alcoholic",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_ORANGE,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ALCOHOLIC.getColour();
		}
	},
	
	TF_MOD_FLUID_ALCOHOLIC_WEAK("alcoholic",
			"Применяет эффект, связанный с изменением жидкости.",
			"alcoholic",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_ORANGE_LIGHT,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ALCOHOLIC_WEAK.getColour();
		}
	},
	
	TF_MOD_FLUID_ADDICTIVE("addictive",
			"Применяет эффект, связанный с изменением жидкости.",
			"addictive",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_PINK_DEEP,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.ADDICTIVE.getColour();
		}
	},
	
	TF_MOD_FLUID_HALLUCINOGENIC("psychoactive",
			"Применяет эффект, связанный с изменением жидкости.",
			"psychoactive",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_MAGENTA,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.HALLUCINOGENIC.getColour();
		}
	},

	TF_MOD_FLUID_MINERAL_OIL("минеральное масло",
			"Применяет эффект, связанный с изменением жидкости.",
			"минеральное масло",
			"modifier_circle_fluid_modifier",
			PresetColour.BASE_BLACK,
			Rarity.COMMON) {
		@Override
		public Colour getColour() {
			return FluidModifier.MINERAL_OIL.getColour();
		}
	},
	
	
	// Fluid flavours:
	
	TF_MOD_FLAVOUR_CUM(FluidFlavour.CUM, "flavours/cum"),
	
	TF_MOD_FLAVOUR_MILK(FluidFlavour.MILK, "flavours/cum"),
	
	TF_MOD_FLAVOUR_GIRLCUM(FluidFlavour.GIRL_CUM, "flavours/cum"),

	TF_MOD_FLAVOUR_FLAVOURLESS(FluidFlavour.FLAVOURLESS, "flavours/flavourless"),
	
	TF_MOD_FLAVOUR_BUBBLEGUM(FluidFlavour.BUBBLEGUM, "flavours/bubblegum"),
	
	TF_MOD_FLAVOUR_BEER(FluidFlavour.BEER, "flavours/beer"),
	
	TF_MOD_FLAVOUR_VANILLA(FluidFlavour.VANILLA, "flavours/vanilla"),
	
	TF_MOD_FLAVOUR_STRAWBERRY(FluidFlavour.STRAWBERRY, "flavours/strawberry"),
	
	TF_MOD_FLAVOUR_CHOCOLATE(FluidFlavour.CHOCOLATE, "flavours/chocolate"),
	
	TF_MOD_FLAVOUR_PINEAPPLE(FluidFlavour.PINEAPPLE, "flavours/pineapple"),
	
	TF_MOD_FLAVOUR_HONEY(FluidFlavour.HONEY, "flavours/honey"),
	
	TF_MOD_FLAVOUR_MINT(FluidFlavour.MINT, "flavours/mint"),

	TF_MOD_FLAVOUR_CHERRY(FluidFlavour.CHERRY, "flavours/cherry"),

	TF_MOD_FLAVOUR_COFFEE(FluidFlavour.COFFEE, "flavours/coffee"),

	TF_MOD_FLAVOUR_TEA(FluidFlavour.TEA, "flavours/tea"),

	TF_MOD_FLAVOUR_MAPLE(FluidFlavour.MAPLE, "flavours/maple"),

	TF_MOD_FLAVOUR_CINNAMON(FluidFlavour.CINNAMON, "flavours/cinnamon"),

	TF_MOD_FLAVOUR_LEMON(FluidFlavour.LEMON, "flavours/lemon"),

	TF_MOD_FLAVOUR_ORANGE(FluidFlavour.ORANGE, "flavours/orange"),
	
	TF_MOD_FLAVOUR_GRAPE(FluidFlavour.GRAPE, "flavours/grape"),
	
	TF_MOD_FLAVOUR_MELON(FluidFlavour.MELON, "flavours/melon"),
	
	TF_MOD_FLAVOUR_COCONUT(FluidFlavour.COCONUT, "flavours/coconut"),
	
	TF_MOD_FLAVOUR_BLUEBERRY(FluidFlavour.BLUEBERRY, "flavours/blueberry"),
	
	TF_MOD_FLAVOUR_BANANA(FluidFlavour.BANANA, "flavours/banana"),
	
	
	// Fetishes:
	
	TF_MOD_FETISH_BODY_PART("фетиши на части тела",
			"Применяет эффект, связанный с фетишем на часть тела.",
			"fetish",
			"modifier_circle_desires",
			PresetColour.BASE_PINK,
			Rarity.COMMON),

	TF_MOD_FETISH_BEHAVIOUR("поведенческие фетиши",
			"Применяет эффект, связанный с поведенческим фетишем.",
			"fetish",
			"modifier_circle_desires",
			PresetColour.BASE_PURPLE,
			Rarity.COMMON),
	
	
	TF_MOD_FETISH_ANAL_GIVING(Fetish.FETISH_ANAL_GIVING),
	TF_MOD_FETISH_ANAL_RECEIVING(Fetish.FETISH_ANAL_RECEIVING),
	TF_MOD_FETISH_VAGINAL_GIVING(Fetish.FETISH_VAGINAL_GIVING),
	TF_MOD_FETISH_VAGINAL_RECEIVING(Fetish.FETISH_VAGINAL_RECEIVING),
	TF_MOD_FETISH_PENIS_GIVING(Fetish.FETISH_PENIS_GIVING),
	TF_MOD_FETISH_PENIS_RECEIVING(Fetish.FETISH_PENIS_RECEIVING),
	TF_MOD_FETISH_BREASTS_OTHERS(Fetish.FETISH_BREASTS_OTHERS),
	TF_MOD_FETISH_BREASTS_SELF(Fetish.FETISH_BREASTS_SELF),
	TF_MOD_FETISH_ORAL_RECEIVING(Fetish.FETISH_ORAL_RECEIVING),
	TF_MOD_FETISH_ORAL_GIVING(Fetish.FETISH_ORAL_GIVING),
	TF_MOD_FETISH_LEG_LOVER(Fetish.FETISH_LEG_LOVER),
	TF_MOD_FETISH_STRUTTER(Fetish.FETISH_STRUTTER),
	TF_MOD_FETISH_FOOT_GIVING(Fetish.FETISH_FOOT_GIVING),
	TF_MOD_FETISH_FOOT_RECEIVING(Fetish.FETISH_FOOT_RECEIVING),
	TF_MOD_FETISH_ARMPIT_GIVING(Fetish.FETISH_ARMPIT_GIVING),
	TF_MOD_FETISH_ARMPIT_RECEIVING(Fetish.FETISH_ARMPIT_RECEIVING),
	TF_MOD_FETISH_LACTATION_OTHERS(Fetish.FETISH_LACTATION_OTHERS),
	TF_MOD_FETISH_LACTATION_SELF(Fetish.FETISH_LACTATION_SELF),
	
	TF_MOD_FETISH_DOMINANT(Fetish.FETISH_DOMINANT),
	TF_MOD_FETISH_SUBMISSIVE(Fetish.FETISH_SUBMISSIVE),
	TF_MOD_FETISH_BONDAGE_VICTIM(Fetish.FETISH_BONDAGE_VICTIM),
	TF_MOD_FETISH_BONDAGE_APPLIER(Fetish.FETISH_BONDAGE_APPLIER),
	TF_MOD_FETISH_CROSS_DRESSER(Fetish.FETISH_CROSS_DRESSER),
	TF_MOD_FETISH_CUM_ADDICT(Fetish.FETISH_CUM_ADDICT),
	TF_MOD_FETISH_CUM_STUD(Fetish.FETISH_CUM_STUD),
	TF_MOD_FETISH_DEFLOWERING(Fetish.FETISH_DEFLOWERING),
	TF_MOD_FETISH_DENIAL(Fetish.FETISH_DENIAL),
	TF_MOD_FETISH_DENIAL_SELF(Fetish.FETISH_DENIAL_SELF),
	TF_MOD_FETISH_EXHIBITIONIST(Fetish.FETISH_EXHIBITIONIST),
	TF_MOD_FETISH_VOYEURIST(Fetish.FETISH_VOYEURIST),
	TF_MOD_FETISH_IMPREGNATION(Fetish.FETISH_IMPREGNATION),
	TF_MOD_FETISH_INCEST(Fetish.FETISH_INCEST),
	TF_MOD_FETISH_MASOCHIST(Fetish.FETISH_MASOCHIST),
	TF_MOD_FETISH_MASTURBATION(Fetish.FETISH_MASTURBATION),
	TF_MOD_FETISH_NON_CON_DOM(Fetish.FETISH_NON_CON_DOM),
	TF_MOD_FETISH_NON_CON_SUB(Fetish.FETISH_NON_CON_SUB),
	TF_MOD_FETISH_PREGNANCY(Fetish.FETISH_PREGNANCY),
	TF_MOD_FETISH_PURE_VIRGIN(Fetish.FETISH_PURE_VIRGIN),
	TF_MOD_FETISH_SADIST(Fetish.FETISH_SADIST),
	TF_MOD_FETISH_TRANSFORMATION_GIVING(Fetish.FETISH_TRANSFORMATION_GIVING),
	TF_MOD_FETISH_TRANSFORMATION_RECEIVING(Fetish.FETISH_TRANSFORMATION_RECEIVING),
	TF_MOD_FETISH_BIMBO(Fetish.FETISH_BIMBO),
	TF_MOD_FETISH_KINK_GIVING(Fetish.FETISH_KINK_GIVING),
	TF_MOD_FETISH_KINK_RECEIVING(Fetish.FETISH_KINK_RECEIVING),
	TF_MOD_FETISH_SIZE_QUEEN(Fetish.FETISH_SIZE_QUEEN),
	;

	private static List<TFModifier> TFModStrengthList = new ArrayList<>();
	private static List<TFModifier> TFModIntelligenceList = new ArrayList<>();
	private static List<TFModifier> TFModCorruptionList = new ArrayList<>();
	private static List<TFModifier> TFModSexualList = new ArrayList<>();
	private static List<TFModifier> TFAttributeList = new ArrayList<>();
	private static List<TFModifier> TFRacialBodyPartsList = new ArrayList<>();
	private static List<TFModifier> TFBodyPartFetishList = new ArrayList<>();
	private static List<TFModifier> TFBehaviouralFetishList = new ArrayList<>();

	private static List<TFModifier> clothingPrimaryList = new ArrayList<>();
	private static List<TFModifier> clothingAttributeList = new ArrayList<>();
	private static List<TFModifier> clothingMajorAttributeList = new ArrayList<>();
	private static List<TFModifier> clothingCreampieRetentionList = new ArrayList<>();
	
	private static List<TFModifier> tattooPrimaryList = new ArrayList<>();

	private static List<TFModifier> weaponPrimaryList = new ArrayList<>();
	private static List<TFModifier> weaponAttributeList = new ArrayList<>();
	private static List<TFModifier> weaponMajorAttributeList = new ArrayList<>();

	private static List<TFModifier> dollPrimaryList = new ArrayList<>();
	private static List<TFModifier> dollSecondaryList = new ArrayList<>();
	
	
	static {

		TFModStrengthList.add(NONE);
		TFModIntelligenceList.add(NONE);
		TFModCorruptionList.add(NONE);
		TFModSexualList.add(NONE);
		
		for(TFModifier tfMod : TFModifier.values()) {
			if(tfMod.getAttributeCategory()!=null && tfMod!=TFModifier.DAMAGE_WEAPON && tfMod!=TFModifier.RESISTANCE_WEAPON) {
				switch(tfMod.getAttributeCategory()) {
					case CORRUPTION:
						TFModCorruptionList.add(tfMod);
						break;
					case INTELLIGENCE:
						TFModIntelligenceList.add(tfMod);
						break;
					case STRENGTH:
						TFModStrengthList.add(tfMod);
						break;
				}
			}
		}
		
		TFModSexualList.add(FERTILITY);
		TFModSexualList.add(VIRILITY);
		TFModSexualList.add(DAMAGE_LUST);
		TFModSexualList.add(RESISTANCE_LUST);
		
		// Body parts:
		
		TFRacialBodyPartsList.add(NONE);
		TFRacialBodyPartsList.add(TF_FACE);
		TFRacialBodyPartsList.add(TF_SKIN);
		TFRacialBodyPartsList.add(TF_CORE);
		
		TFRacialBodyPartsList.add(TF_ARMS);
		TFRacialBodyPartsList.add(TF_LEGS);
		
		TFRacialBodyPartsList.add(TF_EARS);
		TFRacialBodyPartsList.add(TF_EYES);
		TFRacialBodyPartsList.add(TF_HAIR);
		TFRacialBodyPartsList.add(TF_ANTENNA);
		TFRacialBodyPartsList.add(TF_HORNS);
		TFRacialBodyPartsList.add(TF_TAIL);
		TFRacialBodyPartsList.add(TF_TENTACLE);
		TFRacialBodyPartsList.add(TF_WINGS);
		
		TFRacialBodyPartsList.add(TF_ASS);
		TFRacialBodyPartsList.add(TF_BREASTS);
		TFRacialBodyPartsList.add(TF_BREASTS_CROTCH);
		TFRacialBodyPartsList.add(TF_PENIS);
		TFRacialBodyPartsList.add(TF_VAGINA);
		
		TFRacialBodyPartsList.add(TF_MILK);
		TFRacialBodyPartsList.add(TF_MILK_CROTCH);
		TFRacialBodyPartsList.add(TF_CUM);
		TFRacialBodyPartsList.add(TF_GIRLCUM);
		
//		TFAttributeList.add(NONE);
		TFAttributeList.add(ARCANE_BOOST);
		
		
		clothingCreampieRetentionList.add(TF_FACE);
		clothingCreampieRetentionList.add(TF_ASS);
		clothingCreampieRetentionList.add(TF_VAGINA);
		clothingCreampieRetentionList.add(TF_VAGINA_URETHRA);
		clothingCreampieRetentionList.add(TF_BREASTS);
		clothingCreampieRetentionList.add(TF_BREASTS_CROTCH);
		clothingCreampieRetentionList.add(TF_PENIS_URETHRA);
		clothingCreampieRetentionList.add(TF_SPINNERET);

		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_SELF);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_PENIS_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_PENIS_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LEG_LOVER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_STRUTTER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_FOOT_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_FOOT_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ARMPIT_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ARMPIT_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_SELF);

		TFBehaviouralFetishList.add(TF_MOD_FETISH_DOMINANT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SUBMISSIVE);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BONDAGE_APPLIER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BONDAGE_VICTIM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_STUD);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_ADDICT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DEFLOWERING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PURE_VIRGIN);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_IMPREGNATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PREGNANCY);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SADIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASOCHIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_DOM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_SUB);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL_SELF);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_VOYEURIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_EXHIBITIONIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BIMBO);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CROSS_DRESSER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASTURBATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_INCEST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SIZE_QUEEN);

		clothingPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_SPECIAL);
		clothingPrimaryList.add(TFModifier.CLOTHING_CREAMPIE_RETENTION);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		clothingPrimaryList.add(TF_FACE);
		clothingPrimaryList.add(TF_CORE);
		clothingPrimaryList.add(TF_ARMS);
		clothingPrimaryList.add(TF_HAIR);
		clothingPrimaryList.add(TF_ASS);
		clothingPrimaryList.add(TF_BREASTS);
		clothingPrimaryList.add(TF_BREASTS_CROTCH);
		clothingPrimaryList.add(TF_PENIS);
		clothingPrimaryList.add(TF_VAGINA);

		clothingMajorAttributeList.add(TFModifier.HEALTH_MAXIMUM);
		clothingMajorAttributeList.add(TFModifier.MANA_MAXIMUM);
		clothingMajorAttributeList.add(TFModifier.STRENGTH);
		clothingMajorAttributeList.add(TFModifier.INTELLIGENCE);
		clothingMajorAttributeList.add(TFModifier.CORRUPTION);
		
		clothingAttributeList.add(TFModifier.FERTILITY);
		clothingAttributeList.add(TFModifier.VIRILITY);
		clothingAttributeList.add(TFModifier.DAMAGE_UNARMED);
		clothingAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		clothingAttributeList.add(TFModifier.DAMAGE_LUST);
		clothingAttributeList.add(TFModifier.DAMAGE_FIRE);
		clothingAttributeList.add(TFModifier.DAMAGE_ICE);
		clothingAttributeList.add(TFModifier.DAMAGE_POISON);
		clothingAttributeList.add(TFModifier.DAMAGE_SPELLS);
		clothingAttributeList.add(TFModifier.RESISTANCE_FIRE);
		clothingAttributeList.add(TFModifier.RESISTANCE_ICE);
		clothingAttributeList.add(TFModifier.RESISTANCE_LUST);
		clothingAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		clothingAttributeList.add(TFModifier.RESISTANCE_POISON);
		clothingAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		clothingAttributeList.add(TFModifier.CRITICAL_DAMAGE);
		

		tattooPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		tattooPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		tattooPrimaryList.add(TFModifier.CLOTHING_CREAMPIE_RETENTION);
		tattooPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		tattooPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		tattooPrimaryList.add(TF_FACE);
		tattooPrimaryList.add(TF_CORE);
		tattooPrimaryList.add(TF_HAIR);
		tattooPrimaryList.add(TF_ASS);
		tattooPrimaryList.add(TF_BREASTS);
		tattooPrimaryList.add(TF_BREASTS_CROTCH);
		tattooPrimaryList.add(TF_PENIS);
		tattooPrimaryList.add(TF_VAGINA);
		

		weaponPrimaryList.add(TFModifier.CLOTHING_MAJOR_ATTRIBUTE);
		weaponPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		
//		weaponAttributeList.add(TFModifier.RESISTANCE_WEAPON);
//		weaponAttributeList.add(TFModifier.DAMAGE_WEAPON);

		weaponMajorAttributeList.add(TFModifier.HEALTH_MAXIMUM);
		weaponMajorAttributeList.add(TFModifier.MANA_MAXIMUM);
		weaponMajorAttributeList.add(TFModifier.STRENGTH);
		weaponMajorAttributeList.add(TFModifier.INTELLIGENCE);
		weaponMajorAttributeList.add(TFModifier.CORRUPTION);
		
		weaponAttributeList.add(TFModifier.FERTILITY);
		weaponAttributeList.add(TFModifier.VIRILITY);
		weaponAttributeList.add(TFModifier.DAMAGE_UNARMED);
		weaponAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		weaponAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		weaponAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		weaponAttributeList.add(TFModifier.DAMAGE_LUST);
		weaponAttributeList.add(TFModifier.DAMAGE_FIRE);
		weaponAttributeList.add(TFModifier.DAMAGE_ICE);
		weaponAttributeList.add(TFModifier.DAMAGE_POISON);
		weaponAttributeList.add(TFModifier.DAMAGE_SPELLS);
		weaponAttributeList.add(TFModifier.RESISTANCE_FIRE);
		weaponAttributeList.add(TFModifier.RESISTANCE_ICE);
		weaponAttributeList.add(TFModifier.RESISTANCE_LUST);
		weaponAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		weaponAttributeList.add(TFModifier.RESISTANCE_POISON);
		weaponAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		weaponAttributeList.add(TFModifier.CRITICAL_DAMAGE);
	}
	
	
	private enum AttributeCategory {
		STRENGTH,
		INTELLIGENCE,
		CORRUPTION;
	}
	
	private AttributeCategory attributeCategory;
	private AbstractAttribute associatedAttribute;
	
	private String name;
	private String description;
	private String descriptor;
	private String path;
	private String SVGString;
	
	private Colour colour;
	private Rarity rarity;
	private AbstractFetish fetish;
	
	private TFModifier(AttributeCategory attributeCategory, AbstractAttribute associatedAttribute, String description, String SVGString, Rarity rarity) {
		this.attributeCategory=attributeCategory;
		this.associatedAttribute=associatedAttribute;
		this.name = associatedAttribute.getName();
		this.description = description;
		this.descriptor = associatedAttribute.getPositiveEnchantment();
		this.colour = associatedAttribute.getColour();
		this.rarity=rarity;
		
		this.path = SVGString;
		this.SVGString = null;
	}
	
	private TFModifier(String name, String description, String descriptor, String SVGString, Colour colour, Rarity rarity) {
		this.name = name;
		this.description = description;
		this.descriptor = descriptor;
		this.rarity=rarity;
		
		if (colour == null) {
			this.colour = PresetColour.CLOTHING_BLACK;
		} else {
			this.colour = colour;
		}
		
		this.path = SVGString;
		this.SVGString = null;
	}
	
	private TFModifier(AbstractFetish f) {
		this.name = f.getName(null);
		this.description = "Применяет эффект, связанный с "+name+" фетишем. ("+Util.capitaliseSentence(f.getShortDescriptor(null))+".)";
		this.descriptor = name;
		this.rarity = Rarity.EPIC;
		this.colour = PresetColour.FETISH;
		this.fetish = f;
		this.SVGString = f.getSVGString(null);
	}

	private TFModifier(FluidFlavour flavour, String pathName) {
		this(flavour, pathName, flavour.getColour());
	}
	
	private TFModifier(FluidFlavour flavour, String pathName, Colour colour) {
		this.name = flavour.getName()+(flavour==FluidFlavour.FLAVOURLESS?"":"-flavour");
		this.description = "Применяет эффект, связанный с изменением вкуса жидкости.";
		this.descriptor = name;
		this.rarity = Rarity.COMMON;
		this.colour = colour;
		this.path = pathName;
		this.SVGString = null;
	}
	
	public int getValue() {
		switch(getRarity()) {
			case JINXED:
				return 1;
			case COMMON:
				return 1;
			case UNCOMMON:
				return 2;
			case RARE:
				return 4;
			case EPIC:
				return 8;
			case LEGENDARY:
				return 12;
			case QUEST:
				return 1;
		}
		return 1;
	}
	
	public AttributeCategory getAttributeCategory() {
		return attributeCategory;
	}

	public AbstractAttribute getAssociatedAttribute() {
		return associatedAttribute;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getSVGString() {
		if(SVGString==null) {
			// Set this item's file image:
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/" + path + ".svg");
				if(is==null) {
					System.err.println("Error! TFModifier icon file does not exist (Trying to read from '"+path+"')! (Code 2)");
				}
				String s = Util.inputStreamToString(is);

				is.close();
				
				if(path.contains("flavour")) {
					String SVGStringBackground = "";
					is = Subspecies.class.getClassLoader().getResourceAsStream("com/lilithsthrone/res/crafting/flavours/background.svg");
					if(is==null) {
						System.err.println("Error! Subspecies background icon file does not exist (Trying to read from 'flavours/background')!");
					}
					SVGStringBackground = "<div style='width:80%;height:80%;position:absolute;left:10%;bottom:10%;'>"+SvgUtil.colourReplacement(this.toString()+"_B", this.getColour(), Util.inputStreamToString(is))+"</div>";
					
					s = SVGStringBackground + "<div style='width:50%;height:50%;position:absolute;left:25%;bottom:25%;'>" + SvgUtil.colourReplacement(this.toString(), this.getColour(), s)+"</div>";
					
				} else {
					s = SvgUtil.colourReplacement(this.toString(), this.getColour(), s);
				}
				
				this.SVGString = s;

				is.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return SVGString;
	}

	public Colour getColour() {
		return colour;
	}
	
	public boolean isSoloDescriptor() {
		return false;
	}
	
	public Rarity getRarity() {
		return rarity;
	}

	public static List<TFModifier> getTFModStrengthList() {
		return TFModStrengthList;
	}

	public static List<TFModifier> getTFModIntelligenceList() {
		return TFModIntelligenceList;
	}

	public static List<TFModifier> getTFModCorruptionList() {
		return TFModCorruptionList;
	}
	
	public static List<TFModifier> getTFModSexualList() {
		return TFModSexualList;
	}

	public static List<TFModifier> getTFRacialBodyPartsList() {
		List<TFModifier> returnList = new ArrayList<>(TFRacialBodyPartsList);
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
			returnList.remove(TFModifier.TF_MILK_CROTCH);
		}
		return returnList;
	}

	public static List<TFModifier> getTFAttributeList() {
		return TFAttributeList;
	}

	public static List<TFModifier> getTFBodyPartFetishList() {
		List<TFModifier> returnList = new ArrayList<>(TFBodyPartFetishList);
		returnList.removeIf(modifier->!modifier.fetish.isContentEnabled());
		return returnList;
	}
	
	public static List<TFModifier> getTFBehaviouralFetishList() {
		List<TFModifier> returnList = new ArrayList<>(TFBehaviouralFetishList);
		returnList.removeIf(modifier->!modifier.fetish.isContentEnabled());
		return returnList;
	}

	public AbstractFetish getFetish() {
		return fetish;
	}

	public static List<TFModifier> getClothingAttributeList() {
		return clothingAttributeList;
	}

	public static List<TFModifier> getClothingMajorAttributeList() {
		return clothingMajorAttributeList;
	}

	public static List<TFModifier> getClothingCreampieRetentionList() {
		List<TFModifier> returnList = new ArrayList<>(clothingCreampieRetentionList);
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
		}
		if(!Main.game.isNipplePenEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS);
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
		}
		if(!Main.game.isUrethraEnabled()) {
			returnList.remove(TFModifier.TF_PENIS_URETHRA);
			returnList.remove(TFModifier.TF_VAGINA_URETHRA);
		}
		return returnList;
	}
	
	public static List<TFModifier> getClothingPrimaryList() {
		List<TFModifier> returnList = new ArrayList<>(clothingPrimaryList);
		if(!Main.game.isBodyHairEnabled()) {
			returnList.remove(TF_ARMS);
		}
		if(!Main.game.isUdderContentEnabled()) {
			returnList.remove(TFModifier.TF_BREASTS_CROTCH);
			returnList.remove(TFModifier.TF_MILK_CROTCH);
		}
		return returnList;
	}

	public static List<TFModifier> getTattooPrimaryList() {
		return tattooPrimaryList;
	}

	public static List<TFModifier> getWeaponPrimaryList() {
		return weaponPrimaryList;
	}

	public static List<TFModifier> getWeaponMajorAttributeList() {
		return weaponMajorAttributeList;
	}
	
	public static List<TFModifier> getWeaponAttributeList() {
		return weaponAttributeList;
	}

	public static List<TFModifier> getDollPrimaryList() {
		return dollPrimaryList;
	}

	public static List<TFModifier> getDollSecondaryList() {
		return dollSecondaryList;
	}
}
