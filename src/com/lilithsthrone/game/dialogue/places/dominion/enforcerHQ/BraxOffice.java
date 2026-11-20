package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import java.util.stream.Collectors;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.6.3
 * @author Innoxia
 */
public class BraxOffice {

	public static void setBraxsPostQuestStatus(boolean applyPlayerLocationChange) {
		Main.game.getNpc(Brax.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK, true);
		Main.game.getNpc(Brax.class).setPendingClothingDressing(true);
		Main.game.getNpc(Brax.class).setAffection(Main.game.getPlayer(), -50);
		
		Main.game.getNpc(CandiReceptionist.class).addSlave(Main.game.getNpc(Brax.class));
		Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory().clearNonEquippedInventory(true);
		
		if(applyPlayerLocationChange) {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
		}
	};
	
	public static void givePlayerEnforcerUniform(StringBuilder sb, int outfitFem) {
		if(sb==null) {
			sb = new StringBuilder();
		}
		if(outfitFem==1) {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfpumps", PresetColour.CLOTHING_BLACK, false), false));

			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
		} else if(outfitFem==0) {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_lsldshirt", PresetColour.CLOTHING_BLUE, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, false), false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
		} else {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_lsldshirt", PresetColour.CLOTHING_BLUE, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfpumps", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, false), false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
			hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
		}

		TransformativePotion tfPotion = Main.game.getNpc(Brax.class).generateTransformativePotion(Main.game.getPlayer());
		AbstractItem potion = EnchantingUtils.craftItem(
			Main.game.getItemGen().generateItem(tfPotion.getItemType()),
			tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
		potion.setName("Удивление Brax");
		sb.append(Main.game.getPlayer().addItem(potion, false));
	};
	
	public static final DialogueNode INTERIOR_BRAX = new DialogueNode("Кабинет [brax.namePos]", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Правда", "Сказать [brax.name], кто ты такой, и что пришел разузнать, что случилось с Arthur.", INTERIOR_BRAX_TRUTH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 2) {
				return new Response("Ложь", "Вы замечаете, что на всех плакатах изображены девушки-волки. Возможно, стоит притвориться, что Arthur — покровитель эксклюзивного борделя с волчьей тематикой, которым вы якобы владеете...",
						INTERIOR_BRAX_LIE,
						null, null, Util.newArrayListOfValues(Perk.OBSERVANT), null, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 3) {
				return new Response("Искушение волчицей", "Использовать свое женственное волчье тело, чтобы заставить [brax.name] выдать информацию об Arthur.", INTERIOR_BRAX_GETTING_TEASED,
						null, null, null, Femininity.FEMININE, Util.newArrayListOfValues(Subspecies.WOLF_MORPH, Subspecies.getSubspeciesFromId("innoxia_wolf_subspecies_arctic"))){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_REPEAT = new DialogueNode("Кабинет [brax.namePos]", "-", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_REPEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				return new ResponseCombat("Сразиться", "[brax.name] выглядит так, будто готов устроить вам еще одну взбучку!", Main.game.getNpc(Brax.class));
			}
			return null;
		}
	};

	public static final DialogueNode INTERIOR_BRAX_TRUTH = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_TRUTH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "Если хочешь узнать, что случилось с Arthur, тебе придется сразиться с [brax.name]!", Main.game.getNpc(Brax.class));
					
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTERIOR_BRAX_LIE = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжать блефовать", "Намекнуть, что «Логово Волчицы» — это твой бордель. Если он даст информацию об Arthur, ты сделаешь его VIP-клиентом.", INTERIOR_BRAX_LIE_BLUFFING);
					
			} else if (index == 2) {
				return new Response("Перестать разыгрывать спектакль", "Сказать [brax.name], что он идиот, и ты здесь, чтобы выяснить, что он сделал с Arthur.", INTERIOR_BRAX_LIE_IDIOT_BRAX);
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_IDIOT_BRAX = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_IDIOT_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "[brax.name] выглядит крайне смущенным, и ты уверен, что, обманув его таким образом, ты получил как минимум небольшое преимущество!", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(30);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отпустить его", "Пожелать [brax.name] повеселиться. Судя по твоим указаниям, ему понадобится как минимум пара часов, прежде чем он поймет, что его разыграли.", INTERIOR_BRAX_LIE_BLUFFING_SUCCESS){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				};
					
			} else if (index == 2) {
				return new Response("Остановить [brax.name]", "Сказать [brax.name], что он идиот, и ты собираешься избить его за такую наивную глупость.", INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_SUCCESS = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING_SUCCESS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Женская униформа", "Взять женскую униформу и покинуть штаб Enforcer.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Мужская униформа", "Взять мужскую униформу и покинуть штаб Enforcer.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 0);
						setBraxsPostQuestStatus(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "[brax.name] выглядит крайне смущенным, и вы уверены, что, обманув его таким образом, обеспечили себе огромное преимущество!", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(50);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Читать", "Прочитать листок бумаги, который только что вручил вам [brax.name].", INTERIOR_BRAX_GETTING_TEASED_UH_OH) {
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_UH_OH = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Сбежать", "Оттолкнуть от себя [brax.name] и, быстро оправдавшись, убежать.", INTERIOR_BRAX_GETTING_TEASED_ESCAPE) {
					
				};
					
			} else if (index == 2) {
				return new ResponseSex("Трахаться", "Позволить [brax.name] взять контроль и трахнуть вас.",
						true, false, 
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH_GET_FUCKED"));
					
			} else if (index == 3) {
				return new ResponseSex("Взять контроль", "Взять ситуацию под контроль и превратить [brax.name] в свою маленькую сучку.", Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Brax.class)),
								null,
								null),
						AFTER_DOMINANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH_TAKE_CONTROL"));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_ESCAPE = new DialogueNode("Кабинет [brax.namePos]", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_ESCAPE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Женская униформа", "Вы берете женскую форму и покидаете штаб силовиков.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Мужская униформа", "Вы берете мужскую форму и покидаете штаб силовиков.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 0);
						setBraxsPostQuestStatus(true);
					}
				};
			} 
			else {
				return null;
			}
		};
	};
	
	
	//----------- BRAX COMBAT/SEX -----------
	

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {

		@Override
		public String getDescription() {
			return "Вы победили Brax!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Вы действительно не хотите заниматься сексом с Brax. Покиньте его кабинет и продолжайте свой путь.", AFTER_COMBAT_VICTORY_NO_SEX){
					
				};
				
			} else if (index == 2) {
				return new ResponseSex("Доминировать над Brax",
						"Сломленный, похотливый вид Brax слишком соблазнителен, чтобы устоять, и вы не можете сдержать коварную улыбку, глядя на волкомальца сверху вниз, готовясь сделать его своей сучкой.",
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, CorruptionLevel.TWO_HORNY, null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DOMINANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_DOMINATE"));
				
			} else if (index == 3) {
				return new ResponseSex("Подчиниться Brax",
						"Хотя вы и победили его, ваша покорная натура заставляет вас задуматься о том, чтобы позволить Brax властно трахать вас...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, Femininity.FEMININE, null,
						false, true,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_SUBMIT"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_NO_SEX = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_NO_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Женская форма", "Вы берете женскую форму и снова оказываетесь снаружи, но на этот раз вы теперь знаете, где находится Arthur.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Мужская форма", "Вы берете мужскую форму и снова оказываетесь снаружи, но на этот раз вы теперь знаете, где находится Arthur.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),0);
						setBraxsPostQuestStatus(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Поражение", "", true) {
		
		@Override
		public String getDescription() {
			return "Вы были повержены Brax!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_DEFEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isSpittingDisabled()) {
					return Response.getDisallowedSpittingResponse();
				}
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					return new Response("Сплюнуть",
							"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
								+"</b> фетиш: ты так обожаешь превращения, что даже не можешь заставить себя выплюнуть трансформирующую жидкость!",
							null);
				} else {
					return new Response("Сплюнуть", "Выплюнуть трансформирующую жидкость.", AFTER_DEFEAT_TRANSFORMATION_REFUSED);
				}
				
			} else if (index == 2) {
				return new Response("Проглотить",
						"Сделать как говорит Brax, и проглотить странную жидкость.",
						AFTER_DEFEAT_TRANSFORMATION,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxTransformedPlayer);
						
						TransformativePotion tfPotion = Main.game.getNpc(Brax.class).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(tfPotion.getItemType()),
							tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						potion.setName("Удивление Brax");
						
						Main.game.getNpc(Brax.class).useItem(potion, Main.game.getPlayer(), false);
						
						Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, PresetColour.EYE_YELLOW));
						Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, PresetColour.COVERING_BLACK), true);
						Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, PresetColour.COVERING_WHITE), true);
						
						if(Main.getProperties().forcedFetishPercentage!=0 && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE));
						}
						if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)<CorruptionLevel.TWO_HORNY.getMinimumValue()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, CorruptionLevel.TWO_HORNY.getMinimumValue()));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_TRANSFORMATION_REFUSED = new DialogueNode("Кабинет Brax", "В кабинете Brax после того, как вас заставили выпить зелье.", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Покоренная", "Brax слишком силен, чтобы вы могли ему противостоять...",
						false, false,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null, AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_REFUSED_DOMINATED"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_TRANSFORMATION = new DialogueNode("Кабинет Brax", "В кабинете Brax после того, как вас заставили выпить зелье.", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Повиноваться",
						"Возбуждающая жидкость, которую вас только что заставили выпить, вынуждает вас повиноваться, и вы жадно встаете на четвереньки, чтобы Brax мог трахнуть вас в позе собаки.",
						false, false,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_OBEY"));
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SUBMISSIVE_SEX = new DialogueNode("Brax закончил", "Brax закончил развлекаться с тобой.", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_SUBMISSIVE_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				if (index == 1) {
					return new Response ("Женская форма", "Ты берешь женскую униформу и продолжаешь свой путь.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
						@Override
						public void effects() {
							givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),1);
							setBraxsPostQuestStatus(true);
						}
					};
					
				} else if (index == 2) {
					return new Response("Мужская форма", "Ты берешь мужскую униформу и продолжаешь свой путь.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
						public void effects( ) {
							givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),0);
							setBraxsPostQuestStatus(true);
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("Продолжить", "Встать и продолжить свой путь.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DOMINANT_SEX = new DialogueNode("Brax падает", "Brax падает, и ты возвращаешься в его кабинет.", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DOMINANT_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Женская форма", "Вы берете женскую форму и снова оказываетесь снаружи, но на этот раз вы теперь знаете, где находится Arthur.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Мужская форма", "Ты берешь мужскую униформу и снова оказываешься снаружи, но на этот раз, зная, где находится Arthur.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 0);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
