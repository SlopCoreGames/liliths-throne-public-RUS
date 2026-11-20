package com.lilithsthrone.game.inventory.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectTimer;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.84
 * @version 0.4.0
 * @author Innoxia
 */
public class ItemType {
	
	private static String getGenericUseDescription(GameCharacter user, GameCharacter target, String playerUseSelf, String playerUsePartner, String partnerUseSelf, String partnerUsePlayer) {
		if (user!=null && user.isPlayer()) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<p>"+playerUseSelf+"</p>";
					
				} else {
					return UtilText.parse(target, "<p>"+playerUsePartner+"</p>");
				}
			} else {
				return "";
			}
			
		} else {
			if(target!=null) {
				if(target.isPlayer()) {
					return UtilText.parse(user, "<p>"+partnerUsePlayer+"</p>");
					
				} else {
					return UtilText.parse(user, "<p>"+partnerUseSelf+"</p>");
				}
			} else {
				return "";
			}
		}
	}
	
	public static AbstractItemType FETISH_UNREFINED = new AbstractItemType(500,
			"бутылочка",
			false,
			"Поцелуй суккуба",
			"Поцелуи суккуба",
			"Стеклянная бутылочка в форме сердца, наполненная вихрящейся светящейся розовой жидкостью. На бутылочке изысканным почерком выгравировано «Поцелуй суккуба», а подпись ниже — «Безотказное любовное зелье» — ясно дает понять, на что способна эта жидкость.",
			"fetishDrink",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_PINK,
					PresetColour.CLOTHING_PINK_LIGHT,
					PresetColour.CLOTHING_WHITE,
					PresetColour.CLOTHING_PINK_DARK),
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MYSTERY_KINK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.MISC_TF_ITEM,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {
//		@Override
//		public boolean isFetishGiving() {
//			return true;
//		}
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.FETISH_ENHANCEMENT;
		}
		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return FETISH_REFINED;
		}
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы выдергиваете пробку из бутылочки «Поцелуя суккуба», подносите её к своим [pc.lips] и залпом выпиваете содержащуюся внутри сладкую розовую жидкость.",
					"Вы выдергиваете пробку из бутылочки «Поцелуя суккуба», подносите её к [npc.namePos] [npc.lips] и заставляете [npc.herHim] осушить сосуд, выпив всю сладкую розовую жидкость внутри.",
					"[npc.Name] достает бутылочку «Поцелуя суккуба» и, быстро выдернув пробку, [npc.she] тут же осушает её целиком.",
					"[npc.Name] достает бутылочку «Поцелуя суккуба» и, быстро выдернув пробку, [npc.she] подносит её к вашим губам, после чего запрокидывает вашу голову и заставляет быстро проглотить всю сладкую розовую жидкость, содержащуюся внутри.");
		}
	};
	
	public static AbstractItemType FETISH_REFINED = new AbstractItemType(750,
			"флакон",
			false,
			"Фетиш-дар",
			"Фетиш-дары",
			"Флакон с бурлящей розовой жидкостью, полученной путем перегонки «Поцелуя суккуба». Его мощное зачарование сильно отличается от эффектов исходного состава и позволяет добавлять или удалять определенные фетиши.",
			"fetishDrinkRefined",
			PresetColour.FETISH,
			null,
			null,
			Rarity.EPIC,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK)) {
		@Override
		public boolean isFetishGiving() {
			return true;
		}
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы вытаскиваете пробку из стеклянного флакона «Дара фетиша», подносите его к губам и жадно выпиваете содержащуюся внутри приторно-сладкую жидкость.",
					"Вы вытаскиваете пробку из стеклянного флакона «Дара фетиша», подносите его к губам [npc.namePos] и заставляете [npc.herHim] выпить содержимое.",
					"[npc.Name] достает стеклянный флакон «Дара фетиша» и, быстро вытащив пробку, [npc.she] залпом осушает всю бутылочку.",
					"[npc.Name] pulls out a glass vial of 'Fetish Endowment', and, after quickly pulling out the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the sickly sweet liquid that's contained within.");
		}
	};
	
	public static AbstractItemType ADDICTION_REMOVAL = new AbstractItemType(750,
			"бутылочка",
			false,
			"Нектар ангела",
			"Нектары ангела",
			"Изящный хрустальный флакон, наполненный прохладной голубой жидкостью. На одной из сторон выгравированы слова «Нектар ангела», хотя вы не уверены, имеет ли эта жидкость к ним хоть какое-то отношение...",
			"addictionRemoval",
			PresetColour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ADDICTION_REMOVAL)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK_QUALITY)) {
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ADDICTION_REMOVAL_REFINEMENT;
		}
		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ADDICTION_REMOVAL_REFINED;
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы вытаскиваете хрустальную пробку из бутылки «Нектара ангела», подносите её к губам и жадно выпиваете содержащуюся внутри безвкусную жидкость.",
					"Вы вынимаете хрустальную пробку из бутылки «Нектара ангела», подносите её к губам [npc.namePos] и заставляете [npc.herHim] осушить содержимое.",
					"[npc.Name] достает бутылку «Нектара ангела» и, быстро вытащив хрустальную пробку, [npc.she] залпом выпивает всё содержимое.",
					"[npc.Name] достает бутылочку «Ангельского нектара» и, быстро вытащив хрустальную пробку, [npc.she] подносит её к твоим губам, а затем запрокидывает твою голову, заставляя жадно проглотить безвкусную жидкость, содержащуюся внутри.");
		}
	};
	
	public static AbstractItemType ADDICTION_REMOVAL_REFINED = new AbstractItemType(1500,
			"изящный флакон",
			false,
			"Ангельская чистота",
			"Ангельские чистоты",
			"Флакон с прохладной светло-голубой жидкостью, которая испускает слабое, ровное свечение. Будучи очищенной формой «Нектара ангела», эта жидкость утратила способность избавлять от зависимостей, но зато способна навсегда снизить порочность того, кто её выпьет...",
			"addictionRemovalRefined",
			PresetColour.RACE_HUMAN,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK_QUALITY)) {
		@Override
		public String getUseName() {
			return "выпить";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы вынимаете пробку из стеклянного флакона «Ангельской чистоты», подносите его к губам и жадно выпиваете прохладную, освежающую жидкость.",
					"Вы вынимаете пробку из стеклянного флакона «Ангельской чистоты», подносите его к губам [npc.namePos] и заставляете [npc.herHim] осушить содержимое.",
					"[npc.Name] достает стеклянный флакон «Ангельской чистоты» и, быстро вытащив пробку, [npc.she] залпом выпивает всё содержимое.",
					"[npc.Name] pulls out a glass vial of 'Angel's Purity', and, after quickly pulling out the stopper, [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the cool, refreshing liquid which is contained within.");
		}
	};
	
	public static AbstractItemType MUSHROOM = new AbstractItemType(500,
			"группа",
			true,
			"Светящийся гриб",
			"Светящиеся грибы",
			"Биолюминесцентные грибы, подобные этим, часто встречаются в Пещерах Летучих Мышей. Слаймы, обитающие в этих пещерах, особенно любят поедать такие грибы, что и заставляет их тела светиться.",
			"mushrooms",
			PresetColour.BASE_BLUE_LIGHT,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MUSHROOMS)),
			Util.newArrayListOfValues(
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.CONTRABAND_LIGHT)) {
		@Override
		public boolean isTransformative() {
			return true;
		}
		@Override
		public String getUseName() {
			return "есть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Ты отправляешь маленькую группу светящихся грибов в рот, и, пока жуешь и глотаешь их, обнаруживаешь, что у них освежающий мятный вкус.",
					"Ты отправляешь маленькую группу светящихся грибов в рот [npc.namePos], после чего следишь за тем, чтобы [npc.she] их разжевал и проглотил.",
					"[npc.Name] отправляет маленькую группу светящихся грибов в рот [npc.her], после чего разжевывает и проглатывает их.",
					"[npc.Name] отправляет маленькую группу светящихся грибов тебе в рот, и пока [npc.she] заставляет тебя их разжевать и проглотить, ты обнаруживаешь, что у них освежающий мятный вкус.");
		}
	};
	
	private static String getEssenceAbsorptionText(Colour essenceColour, GameCharacter user, GameCharacter target) {
			if (user!=null && user.isPlayer()) {
				if(target!=null) {
					if(target.isPlayer()) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceBottledDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceBottledDiscovered);

							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								return "<p>Выдернув пробку из горлышка маленького флакона, вы ахаете от неожиданности: вихрь света мгновенно вырывается из своей стеклянной темницы. Не давая вам опомниться, эссенция устремляется прямо к вам, и с короткой "+essenceColour.getName()+" вспышкой ударяется вам в грудь и исчезает из виду. Вы думаете, что было бы лучше пойти и спросить Lilaya о том, что только что произошло...</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"");
							} else {
								return "<p>Выдернув пробку из горлышка маленького флакона, вы ахаете от неожиданности: вихрь света мгновенно вырывается из своей стеклянной темницы. Не давая вам опомниться, эссенция устремляется прямо к вам, и с короткой "+essenceColour.getName()+" вспышкой ударяется вам в грудь и исчезает из виду. Вы внезапно вспоминаете, что Lilaya говорила вам о поглощении эссенций, и с облегчением вздыхаете...</p>";
							}
						}

						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							return "<p>Выдернув пробку из горлышка маленького флакона, вы выпускаете арканную эссенцию из ее стеклянной темницы. Притянутая вашей мощной арканной аурой, эссенция мгновенно устремляется к вам, и с короткой "+essenceColour.getName()+" вспышкой исчезает из виду. Вы думаете, что было бы лучше пойти и спросить Lilaya о том, что только что произошло...</p>";
						} else {
							return "<p>Выдернув пробку из горлышка маленького флакона, вы выпускаете арканную эссенцию из ее стеклянной темницы. Притянутая вашей мощной арканной аурой, эссенция мгновенно устремляется к вам, и с короткой "+essenceColour.getName()+" вспышки, она исчезает из виду. Вы чувствуете едва уловимое изменение в своей ауре, дающее понять, что эссенция успешно поглощена.</p>";
						}
						
					} else {
						return UtilText.parse(target,
								"<p>Вытащив пробку из горлышка маленького флакона, вы выпускаете арканную эссенцию из её стеклянной темницы. Притянутая мощной арканной аурой [npc.namePos], эссенция мгновенно устремляется к [npc.herHim], и с небольшой "
										+essenceColour.getName()+" вспышкой, она исчезает из виду, поглощенная аурой [npc.her].</p>");
					}
				} else {
					return "";
				}
				
			} else {
				if(target!=null) {
					if(target.isPlayer()) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essenceBottledDiscovered)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.essenceBottledDiscovered);

							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								return UtilText.parse(user,
										"<p>Вытащив пробку из горлышка маленького флакона, [npc.name] выпускает арканную эссенцию из её стеклянной темницы. Не давая вам времени среагировать, эссенция мгновенно устремляется прямо к вам, и с небольшой "+essenceColour.getName()+" вспышкой ударяется вам в грудь и исчезает из виду. Вы думаете, что было бы лучше пойти и спросить Lilaya о том, что только что произошло...</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):""));
							} else {
								return UtilText.parse(user,
										"<p>Вытащив пробку из горлышка маленького флакона, [npc.name] выпускает арканную эссенцию из её стеклянной темницы. Не давая вам времени среагировать, эссенция мгновенно устремляется прямо к вам, и с небольшой "+essenceColour.getName()+" вспышкой ударяется вам в грудь и исчезает из виду. Вы внезапно вспоминаете, что Lilaya говорила вам о поглощении эссенций, и с облегчением вздыхаете...</p>");
							}
						}

						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							return UtilText.parse(user,
									"<p>Вытащив пробку из горлышка маленького флакона, [npc.name] выпускает арканную эссенцию из её стеклянной темницы. Притянутая вашей мощной арканной аурой, эссенция мгновенно устремляется к вам, и с небольшой "+essenceColour.getName()+" вспышкой исчезает из виду. Вы думаете, что было бы лучше пойти и спросить Lilaya о том, что только что произошло...</p>");
						} else {
							return UtilText.parse(user,
									"<p>Вытащив пробку из горлышка маленького флакона, [npc.name] выпускает арканную эссенцию из её стеклянной темницы. Притянутая вашей мощной арканной аурой, эссенция мгновенно устремляется к вам, и с небольшой "+essenceColour.getName()+" вспышки, она исчезает из виду. Вы чувствуете едва уловимое изменение в своей ауре, дающее понять, что эссенция успешно поглощена.</p>");
						}
						
					} else {
						return UtilText.parse(user, 
								"<p>Выдернув пробку из горлышка маленького флакона, [npc.name] высвобождает арканную эссенцию из ее стеклянной темницы. Притянутая мощной арканной аурой [npc.her], эссенция тут же устремляется к [npc.name], и с легким... "
										+essenceColour.getName()+" вспышкой, она исчезает из виду, поглощенная аурой [npc.her].</p>");
					}
				} else {
					return "";
				}
			}
		
		
	}
	
	public static AbstractItemType BOTTLED_ESSENCE_ARCANE = new AbstractItemType(
			40,
			null,
			false,
			"Arcane эссенция во флаконе",
			"Arcane эссенции во флаконах",
			"маленьким стеклянным флаконом, плотно закупоренным пробкой. Внутри, завороженное, "+PresetColour.GENERIC_ARCANE.getName()+" сияние Arcane эссенции мерцает и кружится в гипнотическом циклическом танце.",
			null,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BOTTLED_ESSENCE_ARCANE)),
			Util.newArrayListOfValues(ItemTag.ESSENCE)) {
		@Override
		public String getUseName() {
			return "поглотить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getEssenceAbsorptionText(PresetColour.GENERIC_ARCANE, user, target);
		}
		@Override
		public String getSVGString() {
			return getEssenceSvg(Subspecies.LILIN);
		}
	};
	

	// Specials:
	
	public static AbstractItemType HARPY_MATRIARCH_BIMBO_LOLLIPOP = new AbstractItemType(1250,
			null,
			false,
			"леденец [bimboHarpy.namePos]",
			"леденцы [bimboHarpy.namePos]",
			"Вихреобразный леденец, который вы получили от матки гарпий [bimboHarpy.name]. Хотя на вид он кажется самым обычным, вы почти уверены, что его поедание приведет к мощной трансформации...",
			"bimboLollipop",
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.BIMBO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE,
					ItemTag.FOOD)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}

		@Override
		public String getUseName() {
			return "сосать";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Поднеся леденец к своим [pc.lips+], ты высовываешь [pc.tongue] и даешь ему долгий, влажный лизь. Насыщенный сладкий вкус наполняет твой рот, совсем не похожий на всё, что ты пробовала раньше. Прежде чем ты успеваешь опомниться, ты уже прижимаешь свои [pc.lips] к этой восхитительной сладости, издавая тихие скулящие звуки и не в силах перестать сосать и облизывать её...",
					"Поднеся леденец к [npc.namePos] [npc.lips+], ты улыбаешься, когда [npc.she] вылетает [npc.her] [npc.tongue], чтобы дать ему долгий, влажный лизь. Пьянящий вкус быстро ошеломляет чувства [npc.her], и [npc.she] жадно прижимает [npc.her] [npc.lips] к этой восхитительной сладости, издавая тихие скулящие звуки, пока [npc.she] понимает, что [npc.herself] не может перестать сосать и облизывать её...",
					"[npc.Name] достает витой леденец и, быстро сорвав обертку, начинает его лизать и сосать...",
					"[npc.Name] достает витой леденец и, быстро сорвав обертку, с силой прижимает его к твоим [pc.lips] и засовывает тебе в рот. Насыщенный сладкий вкус обрушивается на твой язык, совсем не похожий на всё, что ты пробовала раньше. Прежде чем ты успеваешь опомниться, ты уже прижимаешь свои [pc.lips] к этой восхитительной сладости, издавая тихие скулящие звуки и не в силах перестать сосать и облизывать её...");

		}
	};
	
	public static AbstractItemType HARPY_MATRIARCH_NYMPHO_LOLLIPOP = new AbstractItemType(1250,
			null,
			false,
			"Леденец [nymphoHarpy.namePos]",
			"Леденцы [nymphoHarpy.namePos]",
			"Леденец в форме члена, который вы получили от матки гарпий [nymphoHarpy.name]. Хотя он выглядит как обычная конфета, вы почти уверены, что его поедание приведет к мощной трансформации...",
			"nymphoLollipop",
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.NYMPHO_LOLLIPOP)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE,
					ItemTag.FOOD)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}

		@Override
		public String getUseName() {
			return "сосать";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Поднеся леденец к своим [pc.lips+], вы высовываете [pc.tongue] и даете ему долгий, влажный лизь. Интенсивный сладкий вкус наполняет ваш рот, совершенно не похожий ни на что из того, что вы пробовали раньше. Прежде чем вы успеваете опомниться, вы уже запихиваете эту восхитительную конфету в форме члена в рот, издавая похотливые стоны и не в силах перестать сосать и облизывать её...",
					"Поднеся леденец к [npc.namePos] [npc.lips+], вы улыбаетесь, когда [npc.she] высовывает [npc.her] [npc.tongue], чтобы дать ему долгий, влажный лизь. Дурманящий вкус быстро ошеломляет чувства [npc.her], и [npc.she] жадно обхватывает [npc.her] [npc.lips] вокруг восхитительной конфеты в форме члена, издавая похотливые стоны, пока [npc.she] понимает, что [npc.herself] не может перестать сосать и облизывать её...",
					"[npc.Name] достает леденец в форме члена и, быстро сорвав обертку, начинает его лизать и сосать...",
					"[npc.Name] достает леденец в форме члена и, быстро сорвав обертку, с силой прижимает его к твоему [pc.lips] и запихивает тебе в рот. Твой рот наполняет интенсивный сладкий вкус, совершенно не похожий ни на что из того, что ты пробовал раньше. Прежде чем ты успеваешь опомниться, ты уже вовсю сосешь этот восхитительный сладкий член, издавая похотливые стоны и не в силах перестать облизывать и посасывать его...");
		}
	};
	
	public static AbstractItemType HARPY_MATRIARCH_DOMINANT_PERFUME = new AbstractItemType(1250,
			null,
			false,
			"духи [dominantHarpy.namePos]",
			"духи [dominantHarpy.namePos]",
			"Флакон духов, полученный от матриарха гарпий [dominantHarpy.name]. Хотя на вид это обычные духи, ты почти уверен, что их использование приведет к мощной трансформации...",
			"dominantPerfume",
			PresetColour.RARITY_LEGENDARY,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DOMINANT_PERFUME)),
			Util.newArrayListOfValues(
					ItemTag.RACIAL_TF_ITEM,
					ItemTag.NOT_FOR_SALE)) {

		
		@Override
		public boolean isTransformative() {
			return true;
		}
		
		@Override
		public String getUseName() {
			return "распылить";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Поднеся флакон к шее, ты слегка нажимаешь на дозатор. Хотя вырывается лишь небольшая струйка, всё содержимое флакона мгновенно испаряется, оставляя в твоих руках пустую емкость. Пока ты удивленно смотришь на нее, резкий женственный аромат окутывает тебя, подавляя чувства, и ты невольно издаешь отчаянный стон, когда мощное чарующее действие духов начинает проявлять свою истинную природу...",
					"Поднеся флакон духов к шее [npc.namePos], вы слегка сжимаете его. Хотя вылетает лишь небольшое количество жидкости, всё содержимое флакона мгновенно испаряется, оставляя в ваших руках пустую склянку. Пока вы удивленно смотрите на неё, сильный женственный аромат поднимается вверх, одурманивая чувства [npc.namePos], и [npc.she] обнаруживает, что [npc.herself] издает отчаянный стон, когда природа мощного чародейства духов начинает проявлять себя...",
					"[npc.Name] достает флакон духов и, быстро откупорив его, брызгает немного на шею [npc.her]...",
					"[npc.Name] достает флакон духов и, быстро откупорив его, брызгает немного на вашу шею. Хотя вылетает лишь небольшое количество жидкости, всё содержимое флакона мгновенно испаряется, оставляя [npc.name] с пустой склянкой в руках. Пока вы удивленно смотрите на неё, сильный женственный аромат поднимается вверх, одурманивая ваши чувства, и вы обнаруживаете, что издаете отчаянный стон, когда природа мощного чародейства духов начинает проявлять себя...");
			
		}
	};
	
	
	// Crafting outputs:
	
	public static AbstractItemType POTION = new AbstractItemType(500,
			"",
			false,
			"зелье",
			"зелья",
			"Созданные путем насыщения арканными эссенциями расходных предметов, такие зелья могут содержать огромное количество восстанавливающих эффектов или улучшений характеристик. Поскольку создание зелий доступно лишь тем, кто обладает высоким уровнем мастерства в аркане, например, таким демонам, они довольно редки и стоят очень дорого.",
			"refined_potion_container",
			PresetColour.CLOTHING_PINK,
			null,
			null,
			Rarity.RARE,
			null,
			null) {
		@Override
		public boolean isTransformative() {
			return false;
		}
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Сначала вы вынимаете пробку из бутылочки, а затем подносите зелье к своим жаждущим губам. Из горлышка поднимается сладкий аромат, и, проглотив восхитительную жидкость, вы чувствуете, как странное покалывание разливается по всему телу — это начинают действовать эффекты зелья...",
					"Сначала вы вынимаете пробку из бутылочки, а затем подносите зелье к ожидающим губам [npc.namePos], заставляя [npc.herHim] выпить всё до капли.",
					"[npc.Name] достает какое-то зелье и, быстро вытащив пробку, [npc.she] тут же жадно выпивает всё содержимое.",
					"[npc.Name] подносит зелье к вашим губам, после чего запрокидывает вашу голову и заставляет быстро проглотить содержимое. Вы чувствуете, как странное покалывание разливается по всему телу — это начинают действовать эффекты зелья...");
		}
	};
	
	public static AbstractItemType ELIXIR = new AbstractItemType(750,
			"",
			false,
			"эликсир",
			"эликсиры",
			"Созданные путем насыщения арканными эссенциями расходных предметов, подобные эликсиры могут обладать огромным количеством преобразующих эффектов. Поскольку создание эликсиров доступно лишь обладателям высокого уровня мастерства в аркане, таким как демоны, они встречаются довольно редко и стоят очень дорого.",
			"refined_elixir_container",
			PresetColour.CLOTHING_PINK,
			null,
			null,
			Rarity.EPIC,
			null,
			null) {
		@Override
		public boolean isTransformative() {
			return true;
		}
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Сначала вы вынимаете пробку из бутылочки, а затем подносите эликсир к своим жаждущим губам. Из горлышка поднимается сладкий аромат, и, проглотив восхитительную жидкость, вы чувствуете странное покалывание, разливающееся по всему телу, — это начинают действовать эффекты эликсира...",
					"Сначала вы вынимаете пробку из бутылочки, а затем подносите эликсир к ожидающим губам [npc.namePos], прежде чем заставить [npc.herHim] выпить всё до капли.",
					"[npc.Name] достает какой-то эликсир и, быстро вытащив пробку, [npc.she] тут же жадно выпивает содержимое.",
					"[npc.Name] подносит эликсир к вашим губам, затем запрокидывает вашу голову и заставляет быстро проглотить содержимое. Вы чувствуете странное покалывание, разливающееся по всему телу, — это начинают действовать эффекты эликсира...");
		}
	};
	
	
	// Non-TF:

	public static AbstractItemType DYE_BRUSH = new AbstractItemType(150,
			"",
			false,
			"кисть для красок",
			"кисти для красок",
			"Маленькая, на вид самая обыкновенная кисточка, вроде тех, что используют для тонкой детализации на холстах или моделях. При ближайшем рассмотрении вы замечаете слабое фиолетовое свечение, исходящее от кончика кисти, что раскрывает её истинную природу — это зачарованная арканой кисть для красок.",
			"dyeBrush",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DYE_BRUSH)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN)) {
		@Override
		public String getUseName() {
			return "использовать";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>Когда вы берете в руки Кисть-краситель, вы замечаете, как фиолетовое свечение вокруг кончика становится всё сильнее. Чем ближе вы подносите её к одежде, тем ярче становится сияние, пока внезапно в вашем сознании не начинают мелькать образы различных цветов. Как только ворсинки касаются ткани, Кисть-краситель мгновенно испаряется! Вы видите, что аркана-зачарование окрасило вашу одежду в желаемый цвет.</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};
	
	public static AbstractItemType REFORGE_HAMMER = new AbstractItemType(150,
			"",
			false,
			"молот перековки",
			"молоты перековки",
			"Маленький молот с тяжелым металлическим бойком и деревянной рукоятью. Он наделен уникальным аркана-зачарованием, которое не только сделало его легким как перышко, но и даровало способность мгновенно перековывать любое оружие.",
			"reforge_hammer",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.REFORGE_HAMMER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN)) {
		@Override
		public String getUseName() {
			return "использовать";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>Когда вы берете в руки молот перековки, вы видите, как металлический боек начинает излучать глубокое фиолетовое свечение. Чем ближе вы подносите его к оружию, тем ярче становится это сияние, пока внезапно в вашем сознании не начинают мелькать образы различных типов урона. Как только вы касаетесь бойком выбранного оружия, молот перековки мгновенно испаряется! Вы видите, что аркана-зачарование перековало оружие в желаемый тип урона.</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};
	
	public static AbstractItemType CONDOM_USED_WEBBING = new AbstractItemType(1,
			"",
			false,
			"использованная паутина-презерватив",
			"использованные паутины-презервативы",
			"Использованный, похожий на презерватив чехол из паутины, завязанный сверху и наполненный чьей-то спермой. В то время как большинство людей просто выбросили бы это, обладатели особенно грязного ума могут найти ему применение...",
			"condomUsedWebbing",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.USED_CONDOM_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		@Override
		public String getUseName() {
			return CONDOM_USED.getUseName();
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return CONDOM_USED.getUseDescription(user, target);
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return CONDOM_USED.getUnableToBeUsedDescription(user, target);
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}
//		@Override
//		public boolean isAbleToBeUsedWhileTrading() {
//			return false;
//		}
	};

	public static AbstractItemType CONDOM_USED = new AbstractItemType(1,
			"",
			false,
			"использованный презерватив",
			"использованные презервативы",
			"Использованный презерватив, завязанный узлом и наполненный чьей-то спермой. Большинство людей просто выбросили бы его, но те, у кого особенно грязные мысли, могут найти ему применение...",
			"condomUsed",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.USED_CONDOM_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		@Override
		public String getUseName() {
			return "открыть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			if(user==target) {
				return UtilText.parse(user, target, "Развязывая узел на использованном презервативе, [npc.name] [npc.verb(wonder)], как лучше всего использовать содержимое.");
			} else {
				return UtilText.parse(user, target, "Развязывая узел на использованном презервативе, [npc.name] [npc.verb(wonder)], как лучше всего использовать содержимое на [npc2.name].");
			}
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return "Вы не можете придумать, как это использовать. Возможно, его лучше просто выбросить...<br/>(Вам нужно иметь как минимум <b style='color:"+CorruptionLevel.THREE_DIRTY.getColour().toWebHexString()+";'>"+CorruptionLevel.THREE_DIRTY.getName()+"</b> уровня порочности, чтобы знать, как этим пользоваться!)";
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}
//		@Override
//		public boolean isAbleToBeUsedWhileTrading() {
//			return false;
//		}
	};
	
	public static AbstractItemType ORIENTATION_HYPNO_WATCH = new AbstractItemType(50000,
			"",
			false,
			"Гипно-часы",
			"Гипно-часы",
			"Уникальный, невероятно мощный арканный инструмент. При зачаровании эти Гипно-часы обретают способность менять сексуальную ориентацию человека ценой повышения его Порочности...",
			"hypnoClockBase",
			PresetColour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.ORIENTATION_CHANGE)),
			null) {

		
		@Override
		public boolean isFetishGiving() {
			return true;
		}
		
		@Override
		public String getDeterminer() {
			return UtilText.generateSingularDeterminer(this.getName(false));
		}
		
		@Override
		public int getEnchantmentLimit() {
			return 1;
		}
		
		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.ORIENTATION_CHANGE;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return ORIENTATION_HYPNO_WATCH;
		}
		
		@Override
		public String getUseName() {
			return "гипнотизировать";
		}
		
		@Override
		public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
			if(user.equals(target)) {
				return "Использовать " + getName(false) + " чтобы гипнотизировать себя.";
			} else {
				return UtilText.parse(target, "Использовать " + getName(false) + " чтобы гипнотизировать [npc.name].");
			}
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Сжимая в руке изящную цепочку, вы начинаете медленно раскачивать Гипно-часы взад-вперед, приковав взгляд к кружащемуся циферблату и позволяя арканической силе предмета просачиваться в ваш разум...",
					"Сжимая в руке изящную цепочку, вы начинаете медленно раскачивать Гипно-часы взад-вперед перед лицом [npc.namePos], и, как вы и надеялись, [npc.she] устремляет [npc.her] взгляд на кружащийся циферблат, позволяя арканической силе предмета просачиваться в [npc.her] разум...",
					"Сжимая в руке изящную цепочку, [npc.name] начинает медленно раскачивать Гипно-часы взад-вперед, приковав [npc.her] взгляд к кружащемуся циферблату, пока [npc.she] позволяет арканической силе предмета просачиваться в [npc.her] разум...",
					"Сжимая в руке изящную цепочку, [npc.name] начинает медленно раскачивать Гипно-часы взад-вперед перед вашим лицом, и вы обнаруживаете, что не можете делать ничего, кроме как пристально смотреть на кружащийся циферблат, пока арканическая сила предмета просачивается в ваш разум...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
//	public static AbstractItemType VIXENS_VIRILITY = new AbstractItemType(20,
//			"a",
//			false,
//			"breeder pill",
//			"breeder pills",
//			"A small, purple pill, individually packaged in a foil and plastic wrapper."
//				+ " While the text printed on the foil identifies this pill as an 'Orally-Administered Reproduction Enhancer', it's colloquially known as a 'breeder pill', and temporarily boosts both fertility and virility when ingested.",
//			"pill",
//			PresetColour.CLOTHING_PINK,
//			null,
//			null,
//			Rarity.COMMON,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.VIXENS_VIRILITY)),
//			Util.newArrayListOfValues(
//					ItemTag.DOMINION_ALLEYWAY_SPAWN,
//					ItemTag.SUBMISSION_TUNNEL_SPAWN,
//					ItemTag.BAT_CAVERNS_SPAWN,
//					ItemTag.ATTRIBUTE_TF_ITEM,
//					ItemTag.SOLD_BY_RALPH)) {
//		@Override
//		public String getUseName() {
//			return "swallow";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return getGenericUseDescription(user, target,
//					"Popping the little purple pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
//					"Popping the little purple pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
//					"[npc.Name] pops a breeder pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
//					"[npc.Name] pops a breeder pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
//		}
//	};
//	
//	public static AbstractItemType PROMISCUITY_PILL = new AbstractItemType(20,
//			"a",
//			false,
//			"sterility pill",
//			"sterility pills",
//			"A small, blue pill, individually packaged in a foil and plastic wrapper."
//				+ " While the text printed on the foil identifies this pill as an 'Orally-Administered Reproduction Inhibitor',"
//					+ " it's colloquially known as either a 'sterility pill' or 'slut pill', and temporarily reduces both fertility and virility when ingested.",
//			"pill",
//			PresetColour.CLOTHING_BLUE,
//			null,
//			null,
//			Rarity.COMMON,
//			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PROMISCUITY_PILL)),
//			Util.newArrayListOfValues(
//					ItemTag.DOMINION_ALLEYWAY_SPAWN,
//					ItemTag.SUBMISSION_TUNNEL_SPAWN,
//					ItemTag.BAT_CAVERNS_SPAWN,
//					ItemTag.ATTRIBUTE_TF_ITEM,
//					ItemTag.SOLD_BY_RALPH)) {
//		@Override
//		public String getUseName() {
//			return "swallow";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return getGenericUseDescription(user, target,
//					"Popping the little blue pill out of its foil wrapper, you quickly put it in your mouth and swallow it down.",
//					"Popping the little blue pill out of its foil wrapper, you bring it up to [npc.namePos] [npc.lips], before forcing it into [npc.her] mouth and making sure that [npc.she] swallows it down.",
//					"[npc.Name] pops a sterility pill out of its little foil wrapper, before quickly placing it in [npc.her] mouth and swallowing it down.",
//					"[npc.Name] pops a sterility pill out of its little foil wrapper, before bringing it up to your [pc.lips], forcing it into your mouth, and making sure that you swallow it down.");
//		}
//	};
	
	public static AbstractItemType MOO_MILKER_EMPTY = new AbstractItemType(50,
			"",
			false,
			"Доильщик коров",
			"Коровьи доилки",
			"Ручной молокоотсос в коровьем стиле, способный вместить до "+Units.fluid(1000)+" жидкости в прикрепленном пластиковом стакане.",
			"breastPump",
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOO_MILKER)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.BAT_CAVERNS_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		
		@Override
		public String getUseName() {
			return "молоко";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Поднеся Коровью доилку к груди, вы прикладываете присоску к своему [pc.nipple(true)], после чего начинаете качать рычаг вверх-вниз. Ваша [pc.milk+] начинает брызгать в прикрепленный стакан, и вы не можете удержаться от глубокого удовлетворенного вздоха, наслаждаясь восхитительным ощущением самодоения.",
					"Поднеся Коровью доилку к груди [npc.namePos], вы прикладываете присоску к [npc.her] [npc.nipple(true)], после чего начинаете качать рычаг вверх-вниз. [npc.Her] [npc.milk+] начинает брызгать в прикрепленный стакан, и [npc.she] не может удержаться от глубокого удовлетворенного вздоха, наслаждаясь восхитительным ощущением, когда ее доят.",
					"Поднеся Коровью доилку к груди [npc.her], [npc.name] прикладывает присоску к [npc.her] [npc.nipple(true)], после чего начинает качать рычаг вверх-вниз. [npc.Her] [npc.milk+] начинает брызгать в прикрепленный стакан, и [npc.she] не может удержаться от глубокого удовлетворенного вздоха, наслаждаясь восхитительным ощущением, когда она доит [npc.herself].",
					"Поднеся доильный аппарат «Му-Му» к груди, [npc.name] присасывает вакуумную чашку к твоему [pc.nipple(true)], а затем начинает ритмично качать рычаг вверх и вниз. Твоё [pc.milk+] начинает брызгать прямо в прикрепленный стакан, и ты невольно издаешь глубокий, удовлетворенный вздох от восхитительного ощущения дойки.");
		}

		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true) && target.getBreastRawMilkStorageValue()>=5;
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			if(target.isPlayer()) {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return "Чтобы использовать это, у тебя должен быть доступ к соскам!";
				} else {
					return "Тебе нужно иметь как минимум "+Units.fluid(5)+" молока, запасенного в груди, чтобы использовать это!";
				}
				
			} else {
				if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return UtilText.parse(target, "Чтобы использовать это, у тебя должен быть доступ к [npc.namePos] соскам!");
				} else {
					return UtilText.parse(target, "[npc.Name] должно иметь как минимум "+Units.fluid(5)+" молока, запасенного в [npc.her] груди, чтобы использовать это!");
				}
			}
		}
	};
	
	public static AbstractItemType MOO_MILKER_FULL = new AbstractItemType(150,
			"",
			false,
			"Полный доильный аппарат",
			"Полные доильные аппараты",
			"Ручной молокоотсос в коровьем стиле. Прикрепленный пластиковый стакан наполнен молоком, и, открутив сверху насосный механизм, ты можешь в любой момент получить доступ к жидкости.",
			"breastPumpFilled",
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.FILLED_MOO_MILKER_DRINK)),
			Util.newArrayListOfValues(
					ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
		

		@Override
		public String getUseName() {
			return "выпить";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы откручиваете верхнюю часть молокоотсоса и, поднеся его к губам, жадно выпиваете всё содержимое.",
					"Вы откручиваете верхнюю часть молокоотсоса и, поднеся его к [npc.namePos] [npc.lips], заставляете [npc.herHim] жадно выпить всё содержимое.",
					"Открутив верхнюю часть молокоотсоса, [npc.name] подносит его к [npc.her] [npc.lips], после чего проглатывает всё содержимое.",
					"Открутив верхнюю часть молокоотсоса, [npc.name] подносит его к вашему [pc.lips], заставляя вас жадно выпить всё содержимое.");
		}
	};
	
	public static AbstractItemType PREGNANCY_TEST = new AbstractItemType(100,
			"",
			false,
			"Arcane тест на беременность",
			"Arcane тесты на беременность",
			"Маленький пластиковый стержень длиной не более "+Units.size(15)+", в середину которого встроен цифровой дисплей. В прилагаемой инструкции сказано: «Проведите тестом по животу цели, чтобы узнать, кто отец!»",
			"pregnancy_test",
			PresetColour.CLOTHING_WHITE,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PREGNANCY_TEST)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {


		@Override
		public String getUseName() {
			return "использовать";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы проводите тестом на беременность по своему животу, ожидая звукового сигнала, прежде чем поднести его к глазам, чтобы взглянуть на показания.",
					"Вы проводите тестом на беременность по животу [npc.namePos], ожидая звукового сигнала, прежде чем поднести его к глазам, чтобы взглянуть на показания.",
					"[npc.Name] swipes the pregnancy tester over [npc.her] stomach, waiting until [npc.she] hears it beep before bringing it up to take a look at the readout.",
					"[npc.Name] проводит тестом на беременность по твоему животу, ожидая, пока [npc.she] услышит писк, прежде чем поднести его поближе, чтобы взглянуть на результат.");
		}
	};
	
	public static AbstractItemType MOTHERS_MILK = new AbstractItemType(100,
			"бутылочка",
			false,
			"Материнское молоко",
			"Материнские молока",
			"Бутылочка, наполненная густым, сливочным молоком. Сбоку красуется наклейка, гласящая, что этот напиток способен стремительно ускорять беременность и инкубацию яиц.",
			"mothers_milk",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MOTHERS_MILK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {


		@Override
		public String getUseName() {
			return "выпить";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Поднеся бутылочку к своему [pc.lips], ты берешь сосок в рот и жадно начинаешь сосать сливочную жидкость внутри.",
					"Поднеся бутылочку к [npc.namePos] [npc.lips], вы вставляете похожий на сосок носик ей в [npc.her] рот, заставляя [npc.herHim] жадно сосать внутри находящуюся сливочную жидкость.",
					"[npc.Name] достает бутылочку «Материнского молока» и, взяв сосок в [npc.her] рот, [npc.she] жадно начинает сосать сливочную жидкость внутри.",
					"[npc.Name] достает бутылочку «Материнского молока» и, проталкивая сосок тебе в рот, [npc.she] заставляет тебя жадно сосать сливочную жидкость внутри.");
		}
	};
	
	public static AbstractItemType REJUVENATION_POTION = new AbstractItemType(1_000,
			"бутылочка",
			false,
			"зелье омоложения",
			"зелья омоложения",
			"Декоративная стеклянная бутылочка, наполненная светло-голубой жидкостью и запечатанная изысканной золото-стеклянной пробкой. На информационной наклейке на дне бутылки гласит: '<i>Зелье омоложения; гарантированно восстанавливает изношенные отверстия и восполняет все ваши жидкости!</i>'",
			"rejuvenation_potion",
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_BLUE_GREY,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.REJUVENATION_POTION)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH,
					ItemTag.DRINK)) {
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Поднеся бутылочку к своему [pc.lips], ты берешь сосок в рот и жадно начинаешь сосать сливочную жидкость внутри.",
					"Поднеся бутылочку к [npc.namePos] [npc.lips], вы вставляете похожий на сосок носик ей в [npc.her] рот, заставляя [npc.herHim] жадно сосать внутри находящуюся сливочную жидкость.",
					"[npc.Name] достает бутылочку «Материнского молока» и, взяв сосок в [npc.her] рот, [npc.she] жадно начинает сосать сливочную жидкость внутри.",
					"[npc.Name] достает бутылочку «Материнского молока» и, проталкивая сосок тебе в рот, [npc.she] заставляет тебя жадно сосать сливочную жидкость внутри.");
		}
	};
	
	public static AbstractItemType CIGARETTE_PACK = new AbstractItemType(350,
			"пачка",
			true,
			"Сигарета Starr",
			"Сигареты Starr",
			"Нераспечатанная фиолетово-белая картонная пачка, содержащая двадцать сигарет 'Starr'. Согласно информации, напечатанной на обороте пачки, эти сигареты не только 'обогащены добавками, укрепляющими ауру', но и 'гарантированно сделают вас крутым'.",
			"cigaretteBox",
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_GOLD,
			null,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CIGARETTE_PACK)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public String getUseName() {
			return "открыть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Открыв верх пачки, вы получаете доступ к двадцати сигаретам, хранящимся внутри.",
					"Открыв верх пачки, вы получаете доступ к двадцати сигаретам, хранящимся внутри, а затем отдаете их [npc.name].",
					"Открыв верх пачки, [npc.name] получает доступ к двадцати сигаретам, хранящимся внутри.",
					"Открыв верх пачки, [npc.name] получает доступ к двадцати сигаретам, хранящимся внутри, а затем отдает их вам.");
		}
	};
	
	public static AbstractItemType CIGARETTE = new AbstractItemType(20,
			"",
			false,
			"Сигарета Starr",
			"Сигареты Starr",
			"Скрученный бумажный цилиндр с губчатым фильтром, наполненный смесью сушеной растительности и добавок, усиливающих ауру. Он зачарован очень слабым огненным заклинанием, чтобы при попадании в рот самовозгораться.",
			"cigarette",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			Rarity.COMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CIGARETTE)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN,
					ItemTag.SUBMISSION_TUNNEL_SPAWN,
					ItemTag.SOLD_BY_RALPH)) {

		@Override
		public String getUseName() {
			return "дым";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы подносите сигарету к своим [pc.lips] и кладете кончик в рот, после чего огненное зачарование самовоспламеняет другой конец. Сделав вдох, вы втягиваете дым, образующийся от горящей растительности, а затем выдыхаете его, создавая перед лицом маленькое белое облачко.",
					"Вы подносите сигарету к [npc.namePos] [npc.lips] и кладете кончик в [npc.her] рот, после чего огненное зачарование самовоспламеняет другой конец. Сделав вдох, [npc.she] втягивает дым, образующийся от горящей растительности, а затем выдыхает его, создавая перед [npc.her] лицом маленькое белое облачко.",
					"[npc.NamePos] подносит сигарету к [npc.her] [npc.lips] и кладет кончик в [npc.her] рот, после чего огненное зачарование самовоспламеняет другой конец. Сделав вдох, [npc.she] втягивает дым, образующийся от горящей растительности, а затем выдыхает его, создавая перед [npc.her] лицом маленькое белое облачко.",
					"[npc.NamePos] подносит сигарету к вашим [pc.lips] и вкладывает её вам в рот, после чего огненное зачарование самопроизвольно поджигает другой конец. Сделав вдох, вы втягиваете дым от горящей растительной массы, а затем выдыхаете его, создавая перед лицом маленькое белое облачко.");
		}
	};

	public static AbstractItemType MAKEUP_SET = new AbstractItemType(5000,
			"",
			false,
			"набор арканной косметики",
			"набор арканной косметики",
			"Очень востребованный компактный набор косметики. Содержащаяся в нём косметика зачарована так, что позволяет пользователю менять цвет по своему желанию. Что ещё более впечатляюще, сколько бы её ни использовали, она никогда не заканчивается.",
			"makeupSet",
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_BLACK,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.MAKEUP_SET)),
			Util.newArrayListOfValues(
					ItemTag.SOLD_BY_RALPH,
					ItemTag.SOLD_BY_KATE)) {
		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public String getUseName() {
			return "использовать";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "";
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return super.isAbleToBeUsed(user, target) && target.isAbleToWearMakeup();
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			if(!target.isAbleToWearMakeup()) {
				return UtilText.parse(target, "<i>Так как тело [npc.namePos] состоит из "+BodyChanging.getTarget().getBodyMaterial().getName()+", [npc.sheIsFull] [style.colourBad(невозможно использовать любую косметику)]!</i>");
			}
			return "Этот предмет нельзя использовать таким образом!";
		}
	};

	public static AbstractItemType DOLL_CONSOLE = new AbstractItemType(120000,
			"",
			false,
			"D.E.C.K.",
			"D.E.C.K.s",
			"<i>«Набор для экспресс-кастомизации „Кукла“»</i> — это единственный способ трансформировать автономную секс-куклу. Подключив кабель к заднему шейному порту куклы, пользователь может изменить широкий спектр физических характеристик подключённого изделия.",
			"dollConsole",
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_PURPLE,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DOLL_CONSOLE)),
			Util.newArrayListOfValues()) {
		@Override
		public boolean isAbleToBeSold() {
			return true;
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public String getUseName() {
			return "использовать";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "";
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return super.isAbleToBeUsed(user, target) && target.isDoll();
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			if(!target.isDoll()) {
				return UtilText.parse(target, "<i>Так как [npc.nameIsFull] не кукла, D.E.C.K. бесполезен!</i>");
			}
			return "Этот предмет нельзя использовать таким образом!";
		}
	};
	
	public static AbstractItemType PRESENT = new AbstractItemType(250,
			"",
			false,
			"Рождественский подарок",
			"Рождественский подарок",
			"Завернутый подарок, продаваемый одним из надзирателей-оленеморфов в Dominion. В нем содержится случайный предмет из их лавки; его также можно преподнести в дар своему потомству, рабам или Lilaya.",
			"present",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.RARE,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.PRESENT)),
			null) {

		
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
			return !(target.isInventoryFull() && Main.game.getPlayerCell().getInventory().isInventoryFull());
		}

		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return "В вашем инвентаре или на земле нет места для того предмета, что лежит внутри!";
		}
		
		@Override
		public String getUseName() {
			return "открыть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы развязываете ленту и снимаете упаковочную бумагу, а затем открываете коробку, чтобы узнать, что же там внутри...",
					"Вы заставляете [npc.name] развязать ленту и снять упаковочную бумагу, а затем заставляете [npc.herHim] открыть коробку, чтобы узнать, что же там внутри...",
					"[npc.Name] достает подарок, затем принимается развязывать ленту и снимать упаковочную бумагу, после чего открывает коробку, чтобы узнать, что же там внутри...",
					"[npc.Name] достает подарок, а затем заставляет вас развязать ленту и снять упаковочную бумагу, после чего заставляет вас открыть коробку, чтобы узнать, что же там внутри...");
		}
	};
	
	public static AbstractItemType GIFT_ROSE_BOUQUET = new AbstractItemType(
			500,
			null,
			false,
			"Букет роз",
			"Букеты роз",
			"Букет, наполненный разноцветными розами; он приятно пахнет даже издалека. [Ashley.speech(На случай, если ты настолько безнадежен, что даже не знаешь любимого цвета своего адресата, здесь собраны все естественные цвета.)]",
			//				+ " If their favourite happens to be blue, tough luck; maybe you should try getting acquainted with another species of flower instead of going with what's safe.)] ",
			"giftRoseBouquet",
			PresetColour.BASE_RED,
			PresetColour.BASE_ORANGE,
			PresetColour.BASE_YELLOW,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "Букет, наполненный разноцветными розами; он приятно пахнет даже издалека. [Ashley.speech(На случай, если ты настолько безнадежен, что даже не знаешь любимого цвета своего адресата, здесь собраны все естественные цвета.)]";
			} else {
				return "Букет, наполненный разноцветными розами; он приятно пахнет даже издалека.";
			}
		}
		
		@Override
		public String getUseName() {
			return "нюхать";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы вдыхаете тонкий аромат, исходящий от букета роз.",
					"Вы получаете [npc.name], чтобы вдыхать тонкий аромат, исходящий от букета роз.",
					"[npc.Name] заставляет вас вдыхать тонкий аромат, исходящий от букета роз.",
					"[npc.Name] подбивает вас вдыхать тонкий аромат, исходящий от букета роз.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType GIFT_CHOCOLATES = new AbstractItemType(
			300,
			"коробка",
			true,
			"Шоколад",
			"Шоколад",
			"Коробка, наполненная разнообразными шоколадными конфетами. [Ashley.speech(Обычный, но вкусный. Ну да, конечно, притворись, будто покупаешь это не для себя самой.)]",
			"giftChocolates",
			PresetColour.BASE_TAN,
			PresetColour.BASE_BROWN_DARK,
			PresetColour.BASE_YELLOW,
			Rarity.UNCOMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_CHOCOLATES)),
			Util.newArrayListOfValues(
					ItemTag.GIFT,
					ItemTag.FOOD_QUALITY)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "Коробка, наполненная разнообразными шоколадными конфетами. [Ashley.speech(Обычный, но вкусный. Ну да, конечно, притворись, будто покупаешь это не для себя самой.)]";
			} else {
				return "Коробка, наполненная разнообразными шоколадными конфетами.";
			}
		}
		
		@Override
		public String getUseName() {
			return "есть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы снимаете крышку с коробки шоколадных конфет и начинаете лакомиться ими.",
					"Вы снимаете крышку с коробки шоколадных конфет и начинаете скармливать содержимое [npc.name].",
					"[npc.Name] снимает крышку с коробки шоколадных конфет и начинает поедать содержимое.",
					"[npc.Name] снимает крышку с коробки шоколадных конфет и начинает кормить вас конфетами.");
		}
	};
	
	public static AbstractItemType GIFT_PERFUME = new AbstractItemType(
			300,
			"бутылочка",
			false,
			"Розовые духи",
			"Розовые духи",
			"Маленький флакон духов. [Ashley.speech(Обычный аромат, который нравится большинству. Делает вас привлекательнее для всех, ведь никто не любит вонючек!)]",
			"giftPerfume",
			PresetColour.BASE_ROSE,
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			Rarity.UNCOMMON,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.GIFT_PERFUME)),
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "Маленький флакон духов. [Ashley.speech(Обычный аромат, который нравится большинству. Делает вас привлекательнее для всех, ведь никто не любит вонючек!)]";
			} else {
				return "Маленький флакон духов.";
			}
		}
		
		@Override
		public String getUseName() {
			return "распылить";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы глубоко вдыхаете аромат роз, распыляя немного «Розовых духов» на шею.",
					"Вы распыляете немного «Розовых духов» на шею [npc.namePos].",
					"[npc.Name] достает флакон «Розовых духов» и, поднеся его к [npc.her], быстро распыляет немного на [npc.her] [npc.skin] [npc.she].",
					"[npc.Name] достает флакон «Розовых духов» и, поднеся его к вашей шее, [npc.she] быстро распыляет немного на вашу [pc.skin].");
		}
	};
	
	public static AbstractItemType GIFT_TEDDY_BEAR = new AbstractItemType(
			600,
			null,
			false,
			"Плюшевый мишка",
			"Плюшевые мишки",
			"Милый коричневый плюшевый мишка с надписью «Обними меня!», вышитой на маленьком сердечке, которое он держит в лапах. [Ashley.speech(Предупреждение: это неодушевленный предмет; он на самом деле не жаждет вашей привязанности и не может защитить вас от монстров, прячущихся под кроватью!)]",
			"giftTeddyBear",
			PresetColour.BASE_TAN,
			null,
			null,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {


		@Override
		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP)) {
				return "Милый коричневый плюшевый мишка с надписью «Обними меня!», вышитой на маленьком сердечке, которое он держит в лапах. [Ashley.speech(Предупреждение: это неодушевленный предмет; он на самом деле не жаждет вашей привязанности и не может защитить вас от монстров, прячущихся под кроватью!)]";
			} else {
				return "Милый коричневый плюшевый мишка с надписью «Обними меня!», вышитой на маленьком сердечке, которое он держит в лапах.";
			}
		}
		
		@Override
		public String getUseName() {
			return "обнять";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы обнимаете плюшевого мишку. Он такой мягкий и пушистый...",
					"Вы заставляете [npc.name] обнять плюшевого мишку. [npc.She] отмечает, какой он мягкий и пушистый...",
					"[npc.Name] обнимает плюшевого мишку. [npc.She] отмечает, какой он мягкий и пушистый...",
					"[npc.Name] заставляет вас обнять плюшевого мишку. Он такой мягкий и пушистый...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	// Why did I make this?
	public static AbstractItemType EGGPLANT = new AbstractItemType(
			25,
			null,
			false,
			"Баклажан",
			"Баклажаны",
			"Нежное тропическое многолетнее растение, которое в умеренном климате часто выращивают как однолетник. А еще, если прищуриться, он немного похож на член.",
			"eggplant",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.EGGPLANT)),
			Util.newArrayListOfValues(ItemTag.FOOD)) {

		@Override
		public AbstractItemEffectType getEnchantmentEffect() {
			return ItemEffectType.EGGPLANT_POTION;
		}

		@Override
		public AbstractItemType getEnchantmentItemType(List<ItemEffect> effects) {
			return EGGPLANT_POTION;
		}
		
		@Override
		public String getUseName() {
			return "есть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы едите баклажан. Горький вкус разочарования охватывает вас.",
					"Вы заставляете [npc.name] съесть баклажан. Горький вкус разочарования охватывает вас обоих.",
					"[npc.Name] достает баклажан и принимается его есть. Горький вкус разочарования охватывает вас обоих.",
					"[npc.Name] достает баклажан, а затем заставляет вас его съесть. Горький вкус разочарования охватывает вас обоих.");
		}
	};
	
	public static AbstractItemType EGGPLANT_POTION = new AbstractItemType(
			250,
			null,
			false,
			"Баклажановый эликсир",
			"Баклажановые эликсиры",
			"Зелье, приготовленное из горькой мякоти баклажана. Как и сам плод, из которого оно сделано, бутылочка с этим зельем, если прищуриться, чем-то напоминает пенис.",
			"eggplant_potion",
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			Rarity.LEGENDARY,
			null,
			Util.newArrayListOfValues(ItemTag.DRINK)) {
		@Override
		public boolean isTransformative() {
			return true;
		}
		@Override
		public String getUseName() {
			return "выпить";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Вы выпиваете баклажановый эликсир. Насыщенный, сложный вкус оказывается на удивление восхитительным.",
					"Вы заставляете [npc.name] выпить баклажановый эликсир. Насыщенный, сложный вкус оказывается на удивление восхитительным.",
					"[npc.Name] готовит баклажановый эликсир, а затем выпивает его. Насыщенный, сложный вкус оказывается на удивление восхитительным.",
					"[npc.Name] готовит баклажановый эликсир, а затем заставляет вас его выпить. Насыщенный, сложный вкус оказывается на удивление восхитительным.");
		}
	};

	public static AbstractItemType ARTHURS_PACKAGE = new AbstractItemType(0,
			"",
			false,
			"Посылка Arthur",
			"Посылки Arthur",
			"Сверток, который вы забрали в Arcane Arts. Его нужно доставить Артуру.",
			"arthursPackage",
			PresetColour.ANDROGYNOUS,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "осмотреть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"Сверток довольно маленький, его размер примерно "+Units.size(20)+" по каждой стороне. Он сделан из коричневого картона и заклеен упаковочным скотчем.",
					"Сверток довольно маленький, его размер примерно "+Units.size(20)+" по каждой стороне. Он сделан из коричневого картона и заклеен упаковочным скотчем.",
					"Сверток довольно маленький, его размер примерно "+Units.size(20)+" по каждой стороне. Он сделан из коричневого картона и заклеен упаковочным скотчем.",
					"Сверток довольно маленький, его размер примерно "+Units.size(20)+" по каждой стороне. Он сделан из коричневого картона и заклеен упаковочным скотчем.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};


	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY = new AbstractItemType(0,
			"",
			false,
			"Ключ Фирсии",
			"Ключи Фирсии",
			"Аркана-ключ, полученный от Фирсии, предводительницы крепости бесов в Submission. При использовании вместе с ключами, полученными из двух других крепостей, он откроет путь в центральную цитадель.",
			"impArcaneKey",
			PresetColour.CLOTHING_SILVER,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "осмотреть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "Вы чувствуете слабую аркана-пульсацию, исходящую от ключа, когда переворачиваете его в своей [npc.hands].");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY_2 = new AbstractItemType(0,
			"",
			false,
			"Ключ Йортракса",
			"Ключи Йортракса",
			"Аркана-ключ, полученный от Йортракса, предводителя крепости бесов в Submission. При использовании вместе с ключами, полученными из двух других крепостей, он откроет путь в центральную цитадель.",
			"impArcaneKey2",
			PresetColour.CLOTHING_STEEL,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "осмотреть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "Вы чувствуете слабую аркана-пульсацию, исходящую от ключа, когда переворачиваете его в своей [npc.hands].");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	public static AbstractItemType IMP_FORTRESS_ARCANE_KEY_3 = new AbstractItemType(0,
			"",
			false,
			"Ключ Хиорлисса",
			"Ключи Хиорлисса",
			"Аркана-ключ, полученный от Хьорлисс, предводительницы крепости бесов в Submission. В сочетании с ключами из двух других крепостей он откроет путь в центральную цитадель.",
			"impArcaneKey3",
			PresetColour.CLOTHING_GOLD,
			PresetColour.GENERIC_ARCANE,
			null,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "осмотреть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "Вы чувствуете слабую аркана-пульсацию, исходящую от ключа, когда переворачиваете его в своей [npc.hands].");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType LYSSIETHS_RING = new AbstractItemType(0,
			"",
			false,
			"Зачарованное перстень-печать Lyssieth",
			"Зачарованные перстни-печати Lyssieth",
			"Прекрасно выполненное из розового золота и инкрустированное драгоценными камнями кольцо, на которое наложено заклятие порабощения любого, кто его наденет. Если вам удастся обманом заставить «Темную Сирену» надеть его, вы сможете получить аудиенцию у Lyssieth без боя.",
			"lyssiethsRing",
			PresetColour.CLOTHING_ROSE_GOLD,
			PresetColour.CLOTHING_RED_DARK,
			PresetColour.CLOTHING_ROSE_GOLD,
			Rarity.QUEST,
			null,
			null) {


		@Override
		public String getUseName() {
			return "осмотреть";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(user, "Кольцо кажется теплым на ощупь, что выдает мощное чары. Возможно, когда всё это закончится, Lyssieth позволит оставить его вам в награду...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	
	public static AbstractItemType OFFSPRING_MAP = new AbstractItemType(50_000,
			"",
			false,
			"карта аркана-отпрысков",
			"карты аркана-отпрысков",
			"Зачарованная арканой карта, полученная в ратуше Dominion, позволяющая отслеживать примерное местоположение любого из ваших ближайших отпрысков.",
			"offspring_map",
			PresetColour.BASE_BROWN,
			null,
			null,
			Rarity.QUEST,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.OFFSPRING_MAP)),
			null) {
		@Override
		public String getUseName() {
			return "использовать";
		}
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsedInCombatAllies() {
			return false;
		}
		@Override
		public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
//			System.out.println("###");
//			System.out.println(target.isPlayer());
//			System.out.println(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size()==0);
//			System.out.println(Util.newArrayListOfValues(
//					Encounter.DOMINION_ALLEY,
//					Encounter.DOMINION_CANAL,
//					Encounter.HARPY_NEST_WALKWAYS,
//					Encounter.SUBMISSION_TUNNELS,
//					Encounter.BAT_CAVERN,
//					Encounter.getEncounterFromId("innoxia_elis_alleyway")
//				).contains(target.getLocationPlace().getPlaceType().getCoreEncounterType()));
//			System.out.println(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"));
			
			return target.isPlayer()
					&& Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).size()==0
					&& ((Util.newArrayListOfValues(
							Encounter.DOMINION_ALLEY,
							Encounter.DOMINION_CANAL,
							Encounter.HARPY_NEST_WALKWAYS,
							Encounter.SUBMISSION_TUNNELS,
							Encounter.BAT_CAVERN,
							Encounter.getEncounterFromId("innoxia_elis_alleyway")
						).contains(target.getLocationPlace().getPlaceType().getCoreEncounterType()))
						|| Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley"));
		}
		@Override
		public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
			return "Чтобы воспользоваться картой, вы должны находиться на свободной клетке одного из следующих типов: переулки Dominion; каналы Dominion; тропы в гнездах гарпий; туннели Submission; пещеры летучих мышей; переулки Elis.";
		}
		@Override
		public String getUseTooltipDescription(GameCharacter user, GameCharacter target) {
			return "Посмотрите на карту, чтобы узнать, нет ли в этой области кого-то из вашего потомства.";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Вы изучаете карту...";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType CANDI_PERFUMES = new AbstractItemType(500,
			"",
			true,
			"Духи Кэнди",
			"Духи Кэнди",
			"Пара флаконов духов, которые вы забрали у Кейт в «Секретах Суккубов». Их нужно доставить Кэнди в штаб-квартиру Enforcer.",
			"candiPerfumes",
			PresetColour.BASE_ROSE,
			PresetColour.BASE_PURPLE_LIGHT,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "В этих флаконах нет ничего особенного. Лучше всего будет доставить их Кэнди как можно скорее.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType CANDI_CONTRABAND = new AbstractItemType(1000,
			"",
			false,
			"Коробка контрабандных леденцов",
			"Коробки контрабандных леденцов",
			"Коробка, полная контрабандных леденцов, конфискованных Enforcer в гнездах гарпий и очень желанных для Кэнди.",
			"contrabandBox",
			PresetColour.BASE_PINK_DEEP,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "На этой коробке начертано несколько больших красных крестов, ясно дающих понять, что содержимое чем-то опасно. Enforcer, передавший вам эту коробку, сказал, что леденцы внутри — это своего рода постоянный афродизиак, и коробку следует немедленно убрать в надежное хранилище.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType CANDI_HUNDRED_KISSES = new AbstractItemType(50000,
			"",
			false,
			"'Сто поцелуев'",
			"'Сто поцелуев'",
			"Лимитированный набор, содержащий сто помад разных оттенков, выпущенный одной из самых эксклюзивных и элитных косметических компаний во всем Dominion.",
			"candiHundredKisses",
			PresetColour.BASE_PINK_DEEP,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Даже беглый взгляд на эту стильную коробочку дает понять, что в ней не может быть ничего, кроме лучших помад, которые только можно купить за деньги.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType RESONANCE_STONE = new AbstractItemType(0,
			"",
			false,
			"Резонансный камень Клэр",
			"Резонансные камни Клэр",
			"Маленькая отполированная сфера с единственной канавкой, проходящей по всей её окружности. Обычно гражданам запрещено владеть такими, но Клэр отдала этот камень вам, чтобы вы могли подать сигнал Enforcer из SWORD, дислоцированным неподалеку от Крысиных Варренов.",
			"resonanceStone",
			PresetColour.CLOTHING_PURPLE_VERY_DARK,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Резонансный камень сделан из какого-то высокополированного камня; если скрутить его две половины в противоположных направлениях, он активируется и посылает сигнал всем остальным связанным с ним камням. Клэр сказала, что этот камень заставит Enforcer из SWORD начать рейд на Крысиные Варрены, и его следует использовать только после того, как вы подтвердите присутствие Венгара.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType PAINT_CAN_PREMIUM = new AbstractItemType(1_500,
			"банка",
			false,
			"золотая краска «Пурпурная звезда'",
			"золотая краска «Пурпурная звезда'",
			"Банка золотой краски с логотипом премиум-класса «Purple-star», которую вы купили в «Магазине товаров для дома Аргуса». Будем надеяться, Helena оценит её стоимость...",
			"paint_can",
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_PURPLE_DARK,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Если только они не истинные ценители красок, никто и не поверит, сколько вы отдали за эту банку премиальной краски «Purple-star»...";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
			return Util.newArrayListOfValues(PAINT_CAN);
		}
	};

	public static AbstractItemType PAINT_CAN = new AbstractItemType(250,
			"банка",
			false,
			"Золотая краска «Bronze-star»",
			"Золотая краска «Bronze-star»",
			"Банка золотой краски с логотипом стандартного класса «Bronze-star», которую вы купили в «Магазине товаров для дома Аргуса». Надеемся, Helena не будет разочарована...",
			"paint_can",
			PresetColour.CLOTHING_GOLD,
			PresetColour.CLOTHING_BRONZE,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Хотя это и не та премиальная краска «Purple-star», которую заказывала Helena, вы уверены, что этой вполне хватит, чтобы справиться с задачей...";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		@Override
		public List<AbstractCoreType> getAdditionalDiscoveryTypes() {
			return Util.newArrayListOfValues(PAINT_CAN_PREMIUM);
		}
	};

	public static AbstractItemType ROLLED_UP_POSTERS = new AbstractItemType(0,
			"полдюжины",
			true,
			"свернутый зачарованный плакат",
			"свернутые зачарованные плакаты",
			"Полдюжины свернутых плакатов, которые вручила вам Helena с приказом расклеить их на стенах у входа в Переулок Рабов. На каждом из них — зачарованное движущееся изображение прекрасной гарпии, принимающей соблазнительные позы в крошечном бикини.",
			"rolled_up_posters",
			PresetColour.CLOTHING_DESATURATED_BROWN,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Хотя эти плакаты должны рекламировать «Бутик Елены», на них ни словом не упомянуто о лавке гарпии. Вместо этого на каждом из них красуется зачарованное движущееся изображение Елены, принимающей соблазнительную позу в крошечном бикини. Единственный текст, который можно разобрать, находится в самом низу каждого плаката и гласит «Елена», написанное изящным курсивом.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

//	public static AbstractItemType BUSINESS_CARDS = new AbstractItemType(0,
//			"a",
//			false,
//			"pack of business cards",
//			"packs of business cards",
//			"A pack of Helena's business cards, given to you by Scarlett along with the instructions to hand them out at the auction block in Slaver Alley."
//					+ " The pack itself has a fancy heart-shaped cut-out in the middle of its protective sleeve.",
//			"business_card_box_1",
//			PresetColour.CLOTHING_GOLD,
//			null,
//			null,
//			Rarity.QUEST,
//			null,
//			null,
//			null) {
//		@Override
//		public String getUseName() {
//			return "inspect";
//		}
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "Carefully taking them out of their protective packaging, you see that Helena's business cards are very well designed, and clearly display both her name and the address of her store in Slaver Alley.";
//		}
//		@Override
//		public boolean isConsumedOnUse() {
//			return false;
//		}
//	};

	public static AbstractItemType NATALYA_BUSINESS_CARD = new AbstractItemType(0,
			"",
			false,
			"Визитная карточка Натальи",
			"Визитные карточки Натальи",
			"Визитная карточка, полученная от Натальи — доминантной суккутавры, занимающей должность «Стайной хозяйки» в курьерской компании «Dominion Express». Адрес, напечатанный на карточке, ведет вас в складской район Dominion.",
			"natalya_business_card",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "На визитной карточке Натальи четко указаны её имя и титул «Стайная хозяйка». Адрес компании, в которой она работает, «Dominion Express», также ясно указывает на то, что она находится в складском районе Dominion.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType NATALYA_BUSINESS_CARD_STAMPED = new AbstractItemType(0,
			"",
			false,
			"Визитная карточка Натальи (с печатью)",
			"Визитные карточки Натальи (с печатями)",
			"Визитная карточка, полученная от Натальи — доминантной суккутавры, занимающей должность «Стайной хозяйки» в курьерской компании «Dominion Express». Адрес, напечатанный на карточке, ведет вас в складской район Dominion.",
			"natalya_business_card_stamped",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "На визитной карточке Натальи четко прописаны её имя и титул «Хозяйка конюшен». Адрес компании, в которой она работает, «Dominion Express», также ясно указывает на то, что она находится в складском районе Dominion. На эту конкретную карточку лично Наталья поставила штамп, что дает вам мгновенный доступ на склад Dominion Express.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};

	public static AbstractItemType SLAVER_LICENSE = new AbstractItemType(5000,
			"",
			false,
			"Лицензия работорговца",
			"Лицензии работорговцев",
			"Официальный документ, подтверждающий ваше законное право владеть, покупать, продавать и даже захватывать рабов. Хотя лицензии работорговцев крайне трудно получить, они представляют ценность только для своего законного владельца...",
			"slaver_license",
			PresetColour.CLOTHING_WHITE,
			null,
			null,
			Rarity.QUEST,
			null,
			null) {
		@Override
		public String getUseName() {
			return "осмотреть";
		}
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "Эта лицензия работорговца представляет собой единственный лист плотной высококачественной бумаги. В верхней части четко напечатано ваше имя, а внизу стоит подпись Финча.";
		}
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	// Standard non-racial transformatives:
	
//	MASOCHISTS_HEAVEN("a bottle of", "it", "Masochist's Heaven",
//			"A clear plastic bottle of Masochist's Heaven. A girl, lying back in the missionary position, is prominently featured on the label, screaming in delight as a huge cock painfully stretches out her tight, dry pussy.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity, elasticity, and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Masochist's Heaven</i>. The drink is quite bland, but a slight"
//						+ " citrus aftertaste lingers in your mouth as you swallow the last few drops. As you lower the empty bottle, your mouth and throat suddenly feel incredibly"
//						+ " dry, as though you haven't drunk anything for hours. Before you can think about getting another drink, the feeling quickly fades away, spreading a dry warmth throughout your entire body.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Masochist's Heaven</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.MASOCHISTS_HEAVEN.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	GOING_BIG("a bottle of", "it", "Going Big",
//			"A clear plastic bottle of a drink branded as 'Going Big'. A girl, presenting herself doggy-style, is prominently featured on the label, crying out in delight as a gigantic cock easily stretches out her tight, wet pussy.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Decreases orifice capacity. Increases elasticity and wetness.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Going Big</i>."
//						+ " Despite the fact that the liquid is clear, it has a very strong taste of apples, and after only a moment, you're licking the last few drops from your lips.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Going Big</i>, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.GOING_BIG.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	WET_KISS("a bottle of", "it", "Wet Kiss",
//			"A clear plastic bottle of the branded drink <i>Wet Kiss</i>, filled with a rose-coloured liquid. The label on the front is devoid"
//					+ " of any images, and instead simply displays the name <i>Wet Kiss</i>, along with some incomprehensible technical details of the drink's manufacturing process.",
//			"potion", PresetColour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases orifice wetness and capacity.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Wet Kiss</i>. The drink is quite bland, but a slight"
//						+ " aftertaste of cranberries lingers in your mouth as you swallow the last few drops. Within seconds, you feel a slimy wetness squirming in your stomach,"
//						+ " but before you have any time to worry, it quickly dissipates throughout your body.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Wet Kiss</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.WET_KISS.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	
//	
//
//	BUBBLE_MILK("a bottle of", "it", "bubble-milk",
//			"A clear plastic bottle of bubble-milk. Despite its name, the milk doesn't physically bubble, but instead refers to the feeling"
//					+ " you get after drinking it. A busty greater cow-girl is prominently featured on the label, smiling as she milks her gigantic udder-tits into a metal bucket.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 25, Rarity.RARE, "Increases breast size and lactation.") {
//		
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of bubble-milk. It tastes just like regular milk, but as you"
//						+ " swallow the last few drops, a funny bubbling sensation starts to spread throughout your torso before settling in your chest.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of bubble-milk, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.BUBBLE_MILK.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	BUBBLE_CREAM("a bottle of", "it", "bubble-cream",
//			"A clear plastic bottle of bubble-cream. Just like bubble-milk, the cream doesn't physically bubble, but instead refers"
//					+ " to the feeling you get after drinking it. A greater cow-girl with three pairs of gigantic breasts is prominently featured on the label, smiling as she" + " milks her gigantic udder-tits into a metal bucket.",
//			"potion", PresetColour.CLOTHING_WHITE, true, 100, Rarity.EPIC, "Increases breast size, count, and lactation.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of bubble-cream. Its rich taste is exactly like that of regular"
//						+ " cream, but as you swallow the last few drops, a strong bubbling sensation starts to spread throughout your torso before settling in your chest.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of bubble-cream, unscrews the cap, and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.BUBBLE_CREAM.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	THROBBING_GLOW("a bottle of", "it", "Throbbing Glow",
//			"A clear plastic bottle of the energy drink <i>Throbbing Glow</i>, filled with a bright blue liquid. A"
//					+ " well-endowed greater horse-boy is prominently featured on the label, stroking his gigantic member with one hand, while bringing a bottle of <i>Throbbing Glow</i>" + " to his lips with the other.",
//			"potion", PresetColour.CLOTHING_BLUE_LIGHT, true, 25, Rarity.RARE, "Increases penis and testicle size. Increases cum production.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Throbbing Glow</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a throbbing, deep-seated heat take root in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Throbbing Glow</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.THROBBING_GLOW.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	FLOWERS_WARMTH("a bottle of", "it", "Flower's Warmth",
//			"A clear plastic bottle of the energy drink <i>Flower's Warmth</i>, filled with a pale pink liquid. A"
//					+ " greater cat-girl is featured prominently on the label, leaning back in a chair as another greater cat-girl laps hungrily at her exposed pussy.",
//			"potion", PresetColour.CLOTHING_PINK_LIGHT, true, 25, Rarity.RARE, "Increases the body's feminine characteristics.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the plastic cap and gulp down the bottle of <i>Flower's Warmth</i>. It tastes a little sour, sort of like"
//						+ " a cheap, sugary energy drink. As the last few drops slide down your throat, you feel a deep-seated heat start to spread through in your groin.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Flower's Warmth</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.FLOWERS_WARMTH.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
//	
//
//	SCARLET_WHISPER("a bottle of", "it", "Scarlet Whisper",
//			"A delicate glass bottle of <i>Scarlet Whisper</i>, filled with a bright pink liquid. The label on the front displays"
//					+ " the name <i>Scarlet Whisper</i> in a delicate, feminine font. The rest of the label is covered in simple images of pale pink flowers and looping linework.",
//			"potion", PresetColour.CLOTHING_PINK, true, 25, Rarity.RARE, "Increases all feminine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the metal cap and gulp down the bottle of <i>Scarlet Whisper</i>. The liquid has a delicate, sweet flavour,"
//						+ " which reminds you of strawberries and cream. As you finish the bottle, a wave of dizziness washes over you, filling your mind with a soft pink haze. Shaking"
//						+ " your head, the feeling somehow seems to sink down into your body, leaving you tingling all over.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Scarlet Whisper</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.SCARLET_WHISPER.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//	FLAMING_THUNDER("a bottle of", "it", "Flaming Thunder",
//			"A thick glass bottle of <i>Flaming Thunder</i>, filled with a deep blue liquid. The label on the front displays"
//					+ " the name <i>Flaming Thunder</i> in a bold, striking font. The rest of the label is covered in simple images of lightning and bold linework.",
//			"potion", PresetColour.CLOTHING_BLUE, true, 25, Rarity.RARE, "Increases all masculine aspects.") {
//		@Override
//		protected String extraEffects(GameCharacter user, GameCharacter target) {
//			if (user!=null && user.isPlayer() && target.isPlayer())
//				effectStringBuilder = new StringBuilder("<p>You unscrew the metal cap and gulp down the bottle of <i>Flaming Thunder</i>. The liquid has a strong flavour, and despite"
//						+ " its blue colouring, tastes very similar to lemonade. As you finish the bottle, a wave of dizziness washes over you, filling your mind with a strange blue haze."
//						+ " Shaking your head, the feeling somehow seems to sink down into your body, leaving you tingling all over.</p>");
//			else if (user != Main.game.getPlayer() && target != Main.game.getPlayer())
//				effectStringBuilder = new StringBuilder("<p>" + target.getName("The") + " pulls out a bottle of <i>Flaming Thunder</i>, unscrews the cap," + " and gulps it all down.</p>");
//
//			effectStringBuilder.append("<p>" + TransformationEffect.FLAMING_THUNDER.applyEffect(target) + "</p>");
//
//			return effectStringBuilder.toString();
//		}
//
//		@Override
//		public String getUseDescription(GameCharacter user, GameCharacter target) {
//			return "drink";
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInCombat() {
//			return false;
//		}
//
//		@Override
//		public boolean isAbleToBeUsedInSex() {
//			return false;
//		}
//	};
//
	

	public static int getMooMilkerMaxMilk() {
		return 1000;
	}
	
	private static List<AbstractItemType> dominionAlleywayItems = new ArrayList<>();
	private static List<AbstractItemType> submissionTunnelItems = new ArrayList<>();
	private static List<AbstractItemType> batCavernItems = new ArrayList<>();
	private static List<AbstractItemType> elisAlleywayItems = new ArrayList<>();
	
	private static List<AbstractItemType> essences = new ArrayList<>();
	private static List<AbstractItemType> allItems = new ArrayList<>();
	private static List<AbstractItemType> moddedItems = new ArrayList<>();
	private static Map<AbstractSubspecies, String> subspeciesBookId = new HashMap<>();
	
	/**
	 * If you're looking for spell books, their id is:<br/>
	 * SPELL_BOOK_"+spell.toString()<br/>
	 * If you're looking for spell scrolls, their id is:<br/>
	 * "SPELL_SCROLL_"+spellSchool.toString()
	 */
	private static Map<AbstractItemType, String> itemToIdMap = new HashMap<>();

	/**
	 * If you're looking for spell books, their id is:<br/>
	 * SPELL_BOOK_"+spell.toString()<br/>
	 * If you're looking for spell scrolls, their id is:<br/>
	 * "SPELL_SCROLL_"+spellSchool.toString()
	 */
	private static Map<String, AbstractItemType> idToItemMap = new HashMap<>();
	

	public static AbstractItemType getItemTypeFromId(String id) {
		
		if(id.equalsIgnoreCase("PROMISCUITY_PILL")) {
			id = "innoxia_pills_sterility";
			
		} else if(id.equalsIgnoreCase("VIXENS_VIRILITY")) {
			id = "innoxia_pills_fertility";
		}
		
		// Attribute-related liquids were moved out into external res folder in v0.4:
		if(id.equalsIgnoreCase("STR_INGREDIENT_EQUINE_CIDER")) {
			id = "innoxia_race_horse_equine_cider";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_BUBBLE_MILK")) {
			id = "innoxia_race_cow_bubble_milk";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_WOLF_WHISKEY")) {
			id = "innoxia_race_wolf_wolf_whiskey";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_SWAMP_WATER")) {
			id = "innoxia_race_alligator_swamp_water";
		} else if(id.equalsIgnoreCase("STR_INGREDIENT_BLACK_RATS_RUM")) {
			id = "innoxia_race_rat_black_rats_rum";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_FELINE_FANCY")) {
			id = "innoxia_race_cat_felines_fancy";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_GRAPE_JUICE")) {
			id = "innoxia_race_fox_vulpines_vineyard";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_VANILLA_WATER")) {
			id = "innoxia_race_human_vanilla_water";
		} else if(id.equalsIgnoreCase("INT_INGREDIENT_FRUIT_BAT_SQUASH")) {
			id = "innoxia_race_bat_fruit_bats_juice_box";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_CANINE_CRUSH")) {
			id = "innoxia_race_dog_canine_crush";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_SQUIRREL_JAVA")) {
			id = "innoxia_race_squirrel_squirrel_java";
		} else if(id.equalsIgnoreCase("FIT_INGREDIENT_EGG_NOG")) {
			id = "innoxia_race_reindeer_rudolphs_egg_nog";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_HARPY_PERFUME")) {
			id = "innoxia_race_harpy_harpy_perfume";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_SLIME_QUENCHER")) {
			id = "innoxia_race_slime_slime_quencher";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_BUNNY_JUICE")) {
			id = "innoxia_race_rabbit_bunny_juice";
		} else if(id.equalsIgnoreCase("SEX_INGREDIENT_MINCE_PIE")) {
			id = "innoxia_race_none_mince_pie";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_ANGELS_TEARS")) {
			id = "innoxia_race_angel_angels_tears";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_LILITHS_GIFT")) {
			id = "innoxia_race_demon_liliths_gift";
		} else if(id.equalsIgnoreCase("COR_INGREDIENT_IMPISH_BREW")) {
			id = "innoxia_race_imp_impish_brew";
		} else if(id.equalsIgnoreCase("DEBUG_YOUKO_POTION")) {
			id = "innoxia_cheat_inno_chans_gift";
		} else if(id.equalsIgnoreCase("FEMININE_BURGER")) {
			id = "innoxia_cheat_unlikely_whammer";
		}
		
		// Racial-transformative consumables were also moved into external res folder in v0.4:
		
		if(id.equalsIgnoreCase("DEBUG_DEMON_POTION")) {
			id = "innoxia_race_demon_innoxias_gift";
		} else if(id.equalsIgnoreCase("RACE_ANGELS_TEARS") || id.equalsIgnoreCase("RACE_INGREDIENT_HUMAN")) {
			id = "innoxia_race_human_bread_roll";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_CAT_MORPH")) {
			id = "innoxia_race_cat_kittys_reward";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_DOG_MORPH")) {
			id = "innoxia_race_dog_canine_crunch";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_COW_MORPH")) {
			id = "innoxia_race_cow_bubble_cream";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_SQUIRREL_MORPH")) {
			id = "innoxia_race_squirrel_round_nuts";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_BAT_MORPH")) {
			id = "innoxia_race_bat_fruit_bats_salad";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_RAT_MORPH")) {
			id = "innoxia_race_rat_brown_rats_burger";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_RABBIT_MORPH")) {
			id = "innoxia_race_rabbit_bunny_carrot_cake";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_FOX_MORPH")) {
			id = "innoxia_race_fox_chicken_pot_pie";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_HORSE_MORPH")) {
			id = "innoxia_race_horse_sugar_carrot_cube";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_REINDEER_MORPH")) {
			id = "innoxia_race_reindeer_sugar_cookie";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_ALLIGATOR_MORPH")) {
			id = "innoxia_race_alligator_gators_gumbo";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_WOLF_MORPH")) {
			id = "innoxia_race_wolf_meat_and_marrow";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_HARPY")) {
			id = "innoxia_race_harpy_bubblegum_lollipop";
		} else if(id.equalsIgnoreCase("RACE_INGREDIENT_SLIME")) {
			id = "innoxia_race_slime_biojuice_canister";
		}
		
		id = Util.getClosestStringMatch(id, idToItemMap.keySet());
		return idToItemMap.get(id);
	}
	
	public static String getIdFromItemType(AbstractItemType itemType) {
		return itemToIdMap.get(itemType);
	}
	
	public static AbstractItemType getSpellBookType(Spell s) {
		return idToItemMap.get("SPELL_BOOK_"+s);
	}
	
	public static AbstractItemType getSpellScrollType(SpellSchool school) {
		return idToItemMap.get("SPELL_SCROLL_"+school);
	}
	
	public static AbstractItemType getLoreBook(AbstractSubspecies subspecies) {
		return idToItemMap.get(subspeciesBookId.get(subspecies));
	}
	
	static{
		
		// Modded item types:

		moddedItems = new ArrayList<>();
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/items/items");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractItemType ct = new AbstractItemType(innerEntry.getValue(), entry.getKey(), true) {};
					moddedItems.add(ct);
					itemToIdMap.put(ct, id);
					idToItemMap.put(id, ct);
				} catch(Exception ex) {
					System.err.println("Loading modded item failed at 'ItemType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		allItems.addAll(moddedItems);
		
		// External res item types:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/items");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey();
					AbstractItemType ct = new AbstractItemType(innerEntry.getValue(), entry.getKey(), false) {};
					allItems.add(ct);
					itemToIdMap.put(ct, id);
					idToItemMap.put(id, ct);
//					System.out.println("IT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading item failed at 'ItemType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		for(AbstractItemType it : allItems) {
			it.getSVGString(); // Initialise all SVGStrings so that initialisation methods do not conflict with one another in other places in the code.
		}
		
		Field[] fields = ItemType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemType item;
				try {
					item = ((AbstractItemType) f.get(null));
					
					itemToIdMap.put(item, f.getName());
					idToItemMap.put(f.getName(), item);
					
					allItems.add(item);
					
					if(item.getItemTags().contains(ItemTag.ESSENCE)) {
						essences.add(item);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(Spell s : Spell.values()) {
			if(!s.isSpellBook()) {
				continue;
			}
			
			List<String> effectsString = Util.newArrayListOfValues(
					"[style.boldExcellent(Навсегда)] получает заклинание '<b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>'.");
			
			if(s == Spell.ELEMENTAL_EARTH) {
				effectsString.add("Добавляет "+Subspecies.ELEMENTAL_EARTH.getName(null)+" запись в энциклопедии.");
				
			} else if(s == Spell.ELEMENTAL_WATER) {
				effectsString.add("Добавляет "+Subspecies.ELEMENTAL_WATER.getName(null)+" запись в энциклопедии.");
				
			} else if(s == Spell.ELEMENTAL_AIR) {
				effectsString.add("Добавляет "+Subspecies.ELEMENTAL_AIR.getName(null)+" запись в энциклопедии.");
				
			} else if(s == Spell.ELEMENTAL_FIRE) {
				effectsString.add("Добавляет "+Subspecies.ELEMENTAL_FIRE.getName(null)+" запись в энциклопедии.");
				
			} else if(s == Spell.ELEMENTAL_ARCANE) {
				effectsString.add("Добавляет "+Subspecies.ELEMENTAL_ARCANE.getName(null)+" запись в энциклопедии.");
			}
//			effectsString.add("[style.boldExcellent(+5 "+Attribute.DAMAGE_ELEMENTAL.getName()+")]");
			
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(effectsString, s.getSpellSchool().getColour()) {
				
				@Override
				public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
					boolean hasSpell = target.hasSpell(s);
					target.addSpell(s);
					
					String raceKnowledgeGained = "";
					if(target.isPlayer()) {
						if(s == Spell.ELEMENTAL_EARTH) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_EARTH, null, true);
							
						} else if(s == Spell.ELEMENTAL_WATER) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_WATER, null, true);
							
						} else if(s == Spell.ELEMENTAL_AIR) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_AIR, null, true);
							
						} else if(s == Spell.ELEMENTAL_FIRE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_FIRE, null, true);
							
						} else if(s == Spell.ELEMENTAL_ARCANE) {
							raceKnowledgeGained = getBookEffect(target, Subspecies.ELEMENTAL_ARCANE, null, true);
						}
					}
					
					if(hasSpell) {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
									+"</p>"
									+ "<p>"
										+"Reading through the spell book again, you quickly discover that you've already learned all there is to know about the spell '"+s.getName()+"'. Помимо нескольких детально прорисованных диаграмм с изображением демона, творящего это заклинание, на страницах тома нет ничего, что могло бы удержать ваше внимание, и вы ловите себя на том, что закрываете его всего через пару минут, так и не узнав ничего нового...</p>"
									+raceKnowledgeGained;
						} else {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
									+"</p>"
									+ "<p>"
										+ UtilText.parse(target,
												"Снова перелистывая книгу заклинаний, [npc.name] быстро обнаруживает, что [npc.sheIs] уже знает о заклинании ' всё, что только можно..."+s.getName()+"'. Помимо нескольких детально прорисованных диаграмм с изображением демона, творящего это заклинание, на страницах тома нет ничего, что могло бы удержать внимание [npc.her], и [npc.she] закрывает его всего через пару минут, так и не узнав ничего нового...")
									+ "</p>";
						}
						
					} else {
						if(target.isPlayer()) {
							return "<p style='text-align:center'>"
										+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i></p><p>Пока вы читаете книгу заклинаний, вы обнаруживаете, что большинство страниц посвящено тому, как помочь читателю развить свою арканную ауру до уровня, необходимого для изучения этого заклинания. Поскольку ваша аура уже чрезвычайно мощная, эти пассажи для вас бесполезны, и вы быстро перелистываете к последним главам, где подробно описывается, как именно нужно сфокусировать свою ауру для сотворения заклинания '<i>"+s.getName()+"</i>'."
										+ " It doesn't take you long to get the general idea of what to do, and after completing the book's practice exercises, you feel confident that you'll be able to cast this spell whenever you'd like."
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ "You learn the spell <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>!<br/><i>Исполнив свое предназначение, книга заклинаний исчезает во вспышке пурпурного света!</i><br/>[style.italicsExcellent(Книга заклинаний добавлена в библиотеку Lilaya!)]</p>"
									+raceKnowledgeGained;
							
						} else {
							return "<p style='text-align:center'>"
									+"<i><b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+":</b> "+s.getDescription(target)+"</i>"
								+"</p>"
								+ "<p>"
									+ UtilText.parse(target,
											"Пока [npc.name] листает книгу заклинаний, [npc.she] обнаруживает, что большинство страниц посвящено тому, как читателю нарастить свою арканную ауру до уровня, необходимого для изучения этого заклинания. Поскольку аура [npc.her] уже достаточно мощная для этого, данные пассажи бесполезны для [npc.herHim], и [npc.she] быстро перелистывает до последних глав, где подробно описывается, как сфокусировать ауру [npc.her] для сотворения заклинания '<i>"+s.getName()+"</i>'. [npc.herHim] не требуется много времени, чтобы уловить общую суть, и после выполнения практических упражнений из книги, [npc.she] чувствует уверенность в том, что [npc.she] сможет сотворить это заклинание, когда бы [npc.she] ни пожелала.")
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ UtilText.parse(target, "[npc.Name] изучает заклинание <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+s.getName()+"</b>!")
									+ "<br/><i>Исполнив свое предназначение, книга заклинаний исчезает во вспышке пурпурного света!</i></p>";
						}
					}
				}
			};
			
			ItemEffectType.addAbstractItemEffectToIds("EFFECT_SPELL_"+s, effectType);
			
			int value = 2500;
			switch(s) {
				// Tier 1:
				case ARCANE_AROUSAL:
				case ICE_SHARD:
				case POISON_VAPOURS:
				case FIREBALL:
				case SLAM:
					break;
					
				// Tier 2:
				case ARCANE_CLOUD:
				case FLASH:
				case RAIN_CLOUD:
				case TELEKENETIC_SHOWER:
				case TELEPATHIC_COMMUNICATION:
				case VACUUM:
					value = 5000;
					break;

				// Tier 3:
				case STONE_SHELL:
				case SOOTHING_WATERS:
				case PROTECTIVE_GUSTS:
				case CLOAK_OF_FLAMES:
				case CLEANSE:
				case STEAL:
					value = 10000;
					break;
					
				// Tier 4:
				case ELEMENTAL_AIR:
				case ELEMENTAL_ARCANE:
				case ELEMENTAL_EARTH:
				case ELEMENTAL_FIRE:
				case ELEMENTAL_WATER:
					value = 25000;
					break;
					
				// Tier 5:
				case LILITHS_COMMAND:
				case TELEPORT:
					value = 1000000;
					break;
					
				case WITCH_CHARM:
				case WITCH_SEAL:
				case DARK_SIREN_SIRENS_CALL:
				case LIGHTNING_SPHERE_DISCHARGE:
				case LIGHTNING_SPHERE_OVERCHARGE:
				case ARCANE_CHAIN_LIGHTNING:
				case ARCANE_LIGHTNING_SUPERBOLT:
					break;
			}
			
			AbstractItemType spellBook = new AbstractItemType(value,
					null,
					false,
					"Книга заклинаний: "+s.getName(),
					"Книги заклинаний: "+s.getName(),
					"Арканный том, содержащий подробные инструкции о том, как сотворить заклинание '"+s.getName()+"'. Чтение этого тома навсегда откроет способность сотворять это заклинание.",
					"spell_book",
					s.getSpellSchool().getColour(),
					null,
					null,
					Rarity.LEGENDARY,
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_BOOK)) {
		
				@Override
				public String getSVGString() {
					return super.getSVGString()
							+"<div style='width:60%;height:60%;position:absolute;left:0;top:0;'>"
								+ s.getSVGString()
							+ "</div>";
				}
				@Override
				public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !(target.isElemental());
				}
				@Override
				public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
					if(target.isPlayer()) {
						return "Вы уже умеете сотворять это заклинание!";
						
					} else if(target.isElemental()) {
						return UtilText.parse(target, "[npc.Name], как и все элементали, не могут учить заклинания по книгам. Вместо этого [npc.she] нужно сосредоточиться на улучшении [npc.her] понимания арканы, чтобы изучать новые заклинания. (Элементали получают новые заклинания через дерево перков.)");
						
					} else {
						return UtilText.parse(target, "У [npc.Name] недостаточно навыка арканы, чтобы выучить это заклинание! (Требуется аркана не менее "+IntelligenceLevel.ONE_AVERAGE.getMinimumValue()+".)");
					}
				}
				@Override
				public String getUseName() {
					return "прочитать";
				}
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"Вы открываете книгу заклинаний и читаете её содержимое...",
							"Вы открываете книгу заклинаний, и [npc.name] начинает читать её содержимое...",
							"[npc.Name] достает книгу заклинаний, которую [npc.she] затем начинает читать...",
							"[npc.Name] достает книгу заклинаний, которую [npc.she] затем заставляет вас читать...");
				}
				@Override
				public boolean isAbleToBeUsedInSex() {
					return false;
				}
				@Override
				public boolean isAbleToBeUsedInCombatAllies() {
					return false;
				}
			};
			
			itemToIdMap.put(spellBook, "SPELL_BOOK_"+s);
			idToItemMap.put("SPELL_BOOK_"+s, spellBook);
			
			allItems.add(spellBook);
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			
			AbstractItemEffectType effectType = new AbstractItemEffectType(Util.newArrayListOfValues(
							"[style.boldExcellent(+1)] к <span style='color:"+school.getColour().toWebHexString()+";'>"+school.getName()+" очков улучшения </span>."),
							school.getColour()) {
						
						@Override
						public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
							target.incrementSpellUpgradePoints(school, 1);
							return "<p style='text-align:center;'>"
										+ (target.isPlayer()?"Вы получаете":UtilText.parse(target, "[npc.Name] получает"))+" очко улучшения для школы заклинаний <b style='color:"+school.getColour().toWebHexString()+";'>"+school.getName()+"</b>!<br/><i>Исполнив свое предназначение, свиток исчезает во вспышке пурпурного света!</i></p>";
						}
					};

			ItemEffectType.addAbstractItemEffectToIds("EFFECT_SCROLL_SCHOOL_"+school, effectType);
			
			AbstractItemType scroll = new AbstractItemType(1000,
					null,
					false,
					"Свиток "+Util.capitaliseSentence(school.getName()),
					"Свитки "+Util.capitaliseSentence(school.getName()),
					"Арканный свиток, который при прочтении наделяет читателя силой школы '"+Util.capitaliseSentence(school.getName())+"'.",
					"spell_scroll",
					school.getColour(),
					null,
					null,
					Rarity.EPIC,
					Util.newArrayListOfValues(new ItemEffect(effectType)),
					Util.newArrayListOfValues(ItemTag.SPELL_SCROLL)) {
				@Override
				public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
					return (target.isPlayer() || target.getAttributeValue(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.ONE_AVERAGE.getMinimumValue())
							&& !target.isElemental();
				}
				@Override
				public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
					if(target.isElemental()) {
						return "Элементали не могут использовать свитки, вместо этого им нужно улучшать свои заклинания через перки!";
					}
					return UtilText.parse(target, "У [npc.Name] недостаточно навыка арканы, чтобы поглотить силу этого свитка! (Требуется аркана не менее "+IntelligenceLevel.ONE_AVERAGE.getMinimumValue()+".)");
				}
				@Override
				public String getUseName() {
					return "прочитать";
				}
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"Разворачивая свиток, вы читаете его содержимое...",
							"Разворачивая свиток, вы заставляете [npc.name] прочитать его содержимое...",
							"[npc.Name] создает свиток, который [npc.she] затем начинает читать...",
							"[npc.Name] создает свиток, который затем [npc.she] заставляет тебя прочитать...");
				}
				@Override
				public boolean isAbleToBeUsedInSex() {
					return false;
				}
				@Override
				public boolean isAbleToBeUsedInCombatAllies() {
					return false;
				}
			};
			
			itemToIdMap.put(scroll, "SPELL_SCROLL_"+school);
			idToItemMap.put("SPELL_SCROLL_"+school, scroll);
			
			allItems.add(scroll);
		}
		
		// Race books:
		
		Map<String, List<AbstractSubspecies>> subspeciesLoreMap = new HashMap<>();
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		// Add effects from here, as Subspecies and ItemEffectType are dependent on one another to be initialised.
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			subspeciesLoreMap.putIfAbsent(sub.getAdvancedDescriptionId(), new ArrayList<>());
			subspeciesLoreMap.get(sub.getAdvancedDescriptionId()).add(sub);
		}
		
		for(Entry<String, List<AbstractSubspecies>> entry : subspeciesLoreMap.entrySet()) {
			AbstractSubspecies mainSubspecies = entry.getValue().contains(AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
											?AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
											:entry.getValue().get(0);
			
			AbstractItemType loreBook = new AbstractItemType(250,
							null,
							false,
							mainSubspecies.getBookName(),
							mainSubspecies.getBookNamePlural(),
							"Книга, содержащая глубокие познания о "+mainSubspecies.getNamePlural(null)+".",
							"race_book",
							mainSubspecies.getColour(null),
							PresetColour.CLOTHING_GOLD,
							mainSubspecies.getColour(null),
							Rarity.RARE,
							null,
							Util.newArrayListOfValues(ItemTag.BOOK)) {
				@Override
				public String getDescription() {
					return super.getDescription()
							+(mainSubspecies.getBookAuthor().isEmpty()?"":" Автор книги — "+mainSubspecies.getBookAuthor()+"'.");
				}
				@Override
				public List<ItemEffect> getEffects() {
					AbstractSubspecies mainSubspecies = entry.getValue().contains(AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace()))
							?AbstractSubspecies.getMainSubspeciesOfRace(entry.getValue().get(0).getRace())
							:entry.getValue().get(0);
					String id = "BOOK_READ_"+Subspecies.getIdFromSubspecies(mainSubspecies);
					
					if(!ItemEffectType.idToItemEffectTypeMap.containsKey(id)) {
						AbstractItemEffectType bookType = generateBookEffect(mainSubspecies, entry.getValue());
						ItemEffectType.allEffectTypes.add(bookType);
						ItemEffectType.itemEffectTypeToIdMap.put(bookType, id);
						ItemEffectType.idToItemEffectTypeMap.put(id, bookType);
					}
					
					return Util.newArrayListOfValues(new ItemEffect(ItemEffectType.getBookEffectFromSubspecies(mainSubspecies)));
				}
				@Override
				public String getSVGString() {
					int offset = 6;
					float left = (float) (30 + offset*Math.cos(Math.toRadians(60)));
					left = Math.round(left*100);
					left /=100;
					return super.getSVGString()
							+"<div style='width:40%;height:40%;position:absolute;left:"+left+"%;top:"+(30-offset)+"%; opacity:0.75; -webkit-transform: rotate(30deg);'>"
								+ mainSubspecies.getBookSVGString()
							+ "</div>";
				}
				@Override
				public boolean isConsumedOnUse() {
					return false;
				}
				@Override
				public String getUseName() {
					return "прочитать";
				}
				@Override
				public String getUseDescription(GameCharacter user, GameCharacter target) {
					return getGenericUseDescription(user, target,
							"Открыв книгу, ты читаешь её содержимое...",
							"Открыв книгу, ты заставляешь [npc.name] прочитать её содержимое...",
							"[npc.Name] создает книгу под названием '"+getName(false)+"', которую затем [npc.she] начинает читать...",
							"[npc.Name] создает книгу под названием '"+getName(false)+"', что затем [npc.she] заставляет тебя прочитать...");
				}
			};
			
			String id = "BOOK_"+Subspecies.getIdFromSubspecies(mainSubspecies);
					
			itemToIdMap.put(loreBook, id);
			idToItemMap.put(id, loreBook);
			
			for(AbstractSubspecies subspecies : entry.getValue()) {
				subspeciesBookId.put(subspecies, id);
			}
			
			allItems.add(loreBook);
			
			
			// Essences
			if(mainSubspecies!=Subspecies.CENTAUR) { // a CENTAUR essence is identical to a HORSE_MORPH essence

				int override = mainSubspecies.getSubspeciesOverridePriority();
				String raceName = (override>0?mainSubspecies.getFeralName(null):mainSubspecies.getRace().getName(true));
				String raceNamePlural = (override>0?mainSubspecies.getFeralNamePlural(null):mainSubspecies.getRace().getNamePlural(true));

				AbstractStatusEffect statusEffect = new AbstractStatusEffect(80,
						(mainSubspecies.getRace()==Race.ANGEL
							?"ангельский"
							:(mainSubspecies.getRace()==Race.DEMON && override <= 2
								?"бесовский"
								:(mainSubspecies.getRace()==Race.DEMON && override >= 5 && override <= 20
									?"демонический"
									:(mainSubspecies==Subspecies.LILIN
										?"лилин"
										:(mainSubspecies==Subspecies.ELDER_LILIN
											?"старшая лилин"
											:raceName.toLowerCase())))))
							+ " интуиция",
						null,
						mainSubspecies.getColour(null),
						true,
						Util.newHashMapOfValues(new Util.Value<>(Attribute.MAJOR_PHYSIQUE, 2f),
								new Util.Value<>(
									mainSubspecies.getRace()==Race.DEMON && override <= 2
										?Attribute.DAMAGE_IMP
										:(mainSubspecies==Subspecies.LILIN
											?Attribute.DAMAGE_LILIN
											:mainSubspecies==Subspecies.ELDER_LILIN
												?Attribute.DAMAGE_ELDER_LILIN
												:Attribute.getRacialDamageAttribute(mainSubspecies.getRace())),
									25f)),
						null) {
					@Override
					public String getDescription(GameCharacter target) {
						if(target == null) {
							return "";
						}
						return UtilText.parse(target, "Поглотив особым образом зачарованную аркану-эссенцию, [npc.nameIsFull] способен точно предсказать, как "
								+ raceNamePlural.toLowerCase() +" будет вести себя.");
					}
					@Override
					public String getSVGString(GameCharacter owner) {
						return getEssenceEffectSvg(mainSubspecies);
					}
				};

				String effect_id = "COMBAT_BONUS_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase();

				StatusEffect.statusEffectToIdMap.put(statusEffect, effect_id);
				StatusEffect.idToStatusEffectMap.put(effect_id, statusEffect);
				StatusEffect.allStatusEffects.add(statusEffect);

				AbstractItemEffectType effectType = new AbstractItemEffectType(Util.newArrayListOfValues(
						"[style.boldGood(+1)] [style.boldArcane(Arcane эссенция)]"),
						mainSubspecies.getColour(null)) {
					@Override
					public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
						return Util.newHashMapOfValues(new Value<>(statusEffect, 60*4*60));
					}
//					@Override
//					public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
//						List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
//						list.add("Applies <i style='color:"+statusEffect.getColour().toWebHexString()+";'>'"+Util.capitaliseSentence(statusEffect.getName(target))+"'</i>:");
//						for(Entry<AbstractAttribute, Float> entry : statusEffect.getAttributeModifiers(target).entrySet()) {
//							list.add("<i>"+entry.getKey().getFormattedValue(entry.getValue())+"</i>");
//						}
//						return list;
//					}
					@Override
					public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
						if(target.isDoll()) {
							return UtilText.parse(target,
										"<p style='text-align:center;'>[style.colourDisabled(Как [npc.sheIsFull] секс-кукла, [npc.nameIsFull] не способна поглощать эссенции...)]</p>");
						}
						return target.incrementEssenceCount(1, false)
								+ UtilText.parse(target,
										"<p style='text-align:center;'>"
											+"[npc.She] [npc.is] also temporarily far more effective at fighting "
											+ "<b style='color:"+mainSubspecies.getColour(null).toWebHexString()+";'>" + raceNamePlural +"</b>!"
										+ "</p>");
					}
				};

				ItemEffectType.addAbstractItemEffectToIds("BOTTLED_ESSENCE_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase(), effectType);

				AbstractItemType essence = new AbstractItemType(
						Math.min(Math.max((mainSubspecies.getBaseSlaveValue(null) / 250), 40), 10000), // i.e. 48 flames for cat-morphs, minimum 40 flames, 10000 maximum
						null,
						false,
						"Разлитая " + Util.capitaliseSentence(raceName) + " Эссенция",
						"Разлитые " + Util.capitaliseSentence(raceName) + " Эссенции",
						"маленьким стеклянным флаконом, плотно закупоренным пробкой. Внутри, завороженное, "+mainSubspecies.getColour(null).getName().toLowerCase() + " сияние арканной эссенции, пропитанной энергией "+UtilText.generateSingularDeterminer(raceName) + " " + raceName
								+ ", мерцает и кружится в завораживающем циклическом узоре.",
						null,
						mainSubspecies.getColour(null),
						null,
						null,
						Rarity.EPIC,
						Util.newArrayListOfValues(new ItemEffect(effectType)),
						(((mainSubspecies.getRace()==Race.DEMON && mainSubspecies.getSubspeciesOverridePriority()>5) || mainSubspecies.getRace()==Race.ANGEL) // Demon+ (and Angels) are contraband
								?Util.newArrayListOfValues(ItemTag.ESSENCE, ItemTag.CONTRABAND_HEAVY)
								:Util.newArrayListOfValues(ItemTag.ESSENCE))) {
						
					@Override
					public String getUseName() {
						return "поглотить";
					}
					@Override
					public String getUseDescription(GameCharacter user, GameCharacter target) {
						return getEssenceAbsorptionText(mainSubspecies.getColour(null), user, target);
					}
					@Override
					public String getSVGString() {
						return getEssenceSvg(mainSubspecies);
					}

				};

				String essence_id = "BOTTLED_ESSENCE_"+Subspecies.getIdFromSubspecies(mainSubspecies).toUpperCase();

				itemToIdMap.put(essence, essence_id);
				idToItemMap.put(essence_id, essence);

				allItems.add(essence);
				essences.add(essence);
			}

		}
		
		// Add items to spawn lists:
		for(AbstractItemType item : allItems) {
			if(item.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				dominionAlleywayItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				submissionTunnelItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				batCavernItems.add(item);
			}
			if(item.getItemTags().contains(ItemTag.ELIS_ALLEYWAY_SPAWN) || item.getItemTags().contains(ItemTag.ALL_AREAS_SPAWN)) {
				elisAlleywayItems.add(item);
			}
		}
	}
	
	private static AbstractItemEffectType generateBookEffect(AbstractSubspecies mainSubspecies, List<AbstractSubspecies> additionalUnlockSubspecies) {
		return new AbstractItemEffectType(Util.newArrayListOfValues(
				"Добавляет "+mainSubspecies.getName(null)+" запись в энциклопедии",
				"Раскрывает атрибуты расовых статус-эффектов",
				"[style.colourExcellent(Дарует уникальную черту:)]",
				Perk.getSubspeciesRelatedPerk(mainSubspecies).getName(null)
				//"[style.boldExcellent(+10)] <b style='color:"+mainSubspecies.getColour(null).toWebHexString()+";'>"+mainSubspecies.getDamageMultiplier().getName()+"</b>"
				),
				mainSubspecies.getColour(null)) {
			@Override
			public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
				return getBookEffect(target, mainSubspecies, additionalUnlockSubspecies, true);
			}
		};
	}
	
	private static Map<AbstractSubspecies, String> essenceMap = new HashMap<>();
	
	private static String getEssenceSvg(AbstractSubspecies subspecies) {
		if(essenceMap.containsKey(subspecies)) {
			return essenceMap.get(subspecies);
		}
		String background = "";
		String bottle = "";
		Colour colour = subspecies.getColour(null);
		try {
			InputStream is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBackground.svg");
			String s = Util.inputStreamToString(is);
			
			background = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();
			
			is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBottle.svg");
			s = Util.inputStreamToString(is);
			bottle = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String subspeciesIcon = subspecies.getSVGStringNoBackground();
		subspeciesIcon = subspeciesIcon.replaceAll("fill=\"#(.*?)\"", "fill=\""+colour.getShades()[1]+"\"");
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[1], colour.getShades()[0]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[2], colour.getShades()[1]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[3], colour.getShades()[2]);
//		subspeciesIcon = subspeciesIcon.replaceAll(colour.getShades()[4], colour.getShades()[3]);
		subspeciesIcon = subspeciesIcon.replaceAll("stroke=\"#(.*?)\"", "stroke=\""+colour.getShades()[1]+"\"");
		
		String finalImage = "<div style='width:80%;height:80%;position:absolute;left:10%;top:10%;'>"
								+ background
							+ "</div>"
							+"<div style='width:70%;height:70%;position:absolute;left:15%;top:20%;'>"
								+ subspeciesIcon
							+ "</div>"
							+ "<div style='width:60%;height:60%;position:absolute;left:20%;top:25%;'>"
								+ bottle
							+ "</div>";
		
		essenceMap.put(subspecies, finalImage);
		
		return finalImage;
	}

	private static String getEssenceEffectSvg(AbstractSubspecies subspecies) {
		String background = "";
		Colour colour = subspecies.getColour(null);
		try {
			InputStream is = ItemType.class.getResourceAsStream("/com/lilithsthrone/res/items/essenceBackground.svg");
			String s = Util.inputStreamToString(is);

			background = SvgUtil.colourReplacement(subspecies.getName(null), colour, s);
			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String subspeciesIcon = subspecies.getSVGStringNoBackground();
		subspeciesIcon = subspeciesIcon.replaceAll("fill=\"#(.*?)\"", "fill=\""+colour.getShades()[1]+"\"");
		subspeciesIcon = subspeciesIcon.replaceAll("stroke=\"#(.*?)\"", "stroke=\""+colour.getShades()[1]+"\"");

		String finalImage = "<div style='width:80%;height:80%;position:absolute;left:10%;top:10%;'>"
				+ background
				+ "</div>"
				+"<div style='width:70%;height:70%;position:absolute;left:15%;top:20%;'>"
				+ subspeciesIcon
				+ "</div>";

		return finalImage;
	}

	public static List<AbstractItemType> getDominionAlleywayItems() {
		return dominionAlleywayItems;
	}
	
	public static List<AbstractItemType> getSubmissionTunnelItems() {
		return submissionTunnelItems;
	}
	
	public static List<AbstractItemType> getBatCavernItems() {
		return batCavernItems;
	}
	
	public static List<AbstractItemType> getElisAlleywayItems() {
		return elisAlleywayItems;
	}
	
	public static List<AbstractItemType> getEssences() {
		return essences;
	}
	
	public static List<AbstractItemType> getAllItems() {
		return allItems;
	}
	
	public static Map<AbstractItemType, String> getItemToIdMap() {
		return itemToIdMap;
	}
	
	public static Map<String, AbstractItemType> getIdToItemMap() {
		return idToItemMap;
	}

}
