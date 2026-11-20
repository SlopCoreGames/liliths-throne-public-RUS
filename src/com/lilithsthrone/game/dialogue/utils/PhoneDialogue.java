package com.lilithsthrone.game.dialogue.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaBirthing;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia, tukaima
 */
public class PhoneDialogue {
	
	private static class offspringTableLineSubject {
		boolean female;
		boolean is_feral;
		String child_name;
		String race_color;
		String species_name;
		String mother;
		String father;
		String incubator;
		List<String> relationships;

		offspringTableLineSubject(NPC npc) {
			this.female = npc.isFeminine();
			this.is_feral = npc.isFeral();
			this.child_name = npc.getName(true);
			this.race_color = npc.getRace().getColour().toWebHexString();
			this.species_name = this.female
					? Util.capitaliseSentence(npc.getSubspecies().getSingularFemaleName(npc.getBody()))
					: Util.capitaliseSentence(npc.getSubspecies().getSingularMaleName(npc.getBody()));
			this.mother = (npc.getMother() == null ? "???" : (npc.getMother().isPlayer() ? "[style.colourExcellent(Вы)]" : Util.capitaliseSentence(npc.getMother().getName(true))));
			if(npc.getMother()==null && !npc.getMotherName().equals("???")) {
				mother = npc.getMotherName();
			}

			this.father = (npc.getFather() == null ? "???" : (npc.getFather().isPlayer() ? "[style.colourExcellent(Вы)]" : Util.capitaliseSentence(npc.getFather().getName(true))));
			if(npc.getFather()==null && !npc.getFatherName().equals("???")) {
				father = npc.getFatherName();
			}

			this.incubator = (npc.getIncubator() == null ? "[style.colourDisabled(n/a)]" : (npc.getIncubator().isPlayer() ? "[style.colourExcellent(Вы)]" : Util.capitaliseSentence(npc.getIncubator().getName(true))));
			if(npc.getIncubator()==null && !npc.getIncubatorName().equals("???")) {
				incubator = npc.getIncubatorName();
			}

			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(npc, Relationship.Parent);
			this.relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(npc.getIncubator()!=null && npc.getIncubator().isPlayer()) {
				int insertIndex = 0;
				this.relationships.add(insertIndex, "Инкубатор-мать");

				if(npc.getMother()!=null && npc.getMother().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "mother");
				}
				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "father");
				}

			} else if(npc.getMother()!=null && npc.getMother().isPlayer()) {
				this.relationships.add(0, "Мать");

				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else {
				this.relationships.add(0, "Отец");
			}
		}

		offspringTableLineSubject(OffspringSeed os) {
			this.female = os.isFeminine();
			this.is_feral = os.isFeral();
			this.child_name = "Неизвестно";
			this.race_color = os.getRace().getColour().toWebHexString();
			this.species_name = this.female
					? Util.capitaliseSentence(os.getSubspecies().getSingularFemaleName(os.getBody()))
					: Util.capitaliseSentence(os.getSubspecies().getSingularMaleName(os.getBody()));
			this.mother = (os.getMother() == null ? "???" : (os.getMother().isPlayer() ? "[style.colourExcellent(Вы)]" : Util.capitaliseSentence(os.getMother().getName(true))));
			if(os.getMother()==null && !os.getMotherName().equals("???")) {
				mother = os.getMotherName();
			}

			this.father = (os.getFather() == null ? "???" : (os.getFather().isPlayer() ? "[style.colourExcellent(Вы)]" : Util.capitaliseSentence(os.getFather().getName(true))));
			if(os.getFather()==null && !os.getFatherName().equals("???")) {
				father = os.getFatherName();
			}

			this.incubator = (os.getIncubator() == null ? "[style.colourDisabled(n/a)]" : (os.getIncubator().isPlayer() ? "[style.colourExcellent(Вы)]" : Util.capitaliseSentence(os.getIncubator().getName(true))));
			if(os.getIncubator()==null && !os.getIncubatorName().equals("???")) {
				incubator = os.getIncubatorName();
			}
			this.relationships = new ArrayList<>();
//			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(os, Relationship.Parent);
//			this.relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(os.getIncubator()!=null && os.getIncubator().isPlayer()) {
				int insertIndex = 0;
				this.relationships.add(insertIndex, "Инкубатор-мать");
				
				if(os.getMother()!=null && os.getMother().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "mother");
				}
				if(os.getFather()!=null && os.getFather().isPlayer()) {
					insertIndex++;
					this.relationships.add(insertIndex, "father");
				}

			} else if(os.getMother()!=null && os.getMother().isPlayer()) {
				this.relationships.add(0, "Мать");

				if(os.getFather()!=null && os.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else {
				this.relationships.add(0, "Отец");
			}

		}
	}

	private static List<GameCharacter> charactersEncountered;
	private static StringBuilder journalSB;
	private static SexAreaOrifice layingEggsArea;
	private static Set<String> incubationOffspringBirthed;
	
	private static void applyEggLayingEffects() {
		incubationOffspringBirthed = new HashSet<>();
		incubationOffspringBirthed.addAll(Main.game.getPlayer().getIncubationLitter(layingEggsArea).getOffspring());
		Main.game.getPlayer().endIncubationPregnancy(layingEggsArea, true);
		switch(layingEggsArea) {
			case ANUS:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementAssStretchedCapacity(15);
					Main.game.getPlayer().incrementAssCapacity(
							(Main.game.getPlayer().getAssStretchedCapacity()-Main.game.getPlayer().getAssRawCapacityValue())*Main.game.getPlayer().getAssPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case NIPPLE:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementNippleStretchedCapacity(15);
					Main.game.getPlayer().incrementNippleCapacity(
							(Main.game.getPlayer().getNippleStretchedCapacity()-Main.game.getPlayer().getNippleRawCapacityValue())*Main.game.getPlayer().getNipplePlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case NIPPLE_CROTCH:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementNippleCrotchStretchedCapacity(15);
					Main.game.getPlayer().incrementNippleCrotchCapacity(
							(Main.game.getPlayer().getNippleCrotchStretchedCapacity()-Main.game.getPlayer().getNippleCrotchRawCapacityValue())*Main.game.getPlayer().getNippleCrotchPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case VAGINA:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
					Main.game.getPlayer().incrementVaginaCapacity(
							(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			default:
				break;
		}
		Main.game.getPlayer().setMana(0);
	}
	
	public static final DialogueNode MENU = new DialogueNode("Главный экран телефона", "Телефон", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
//			if(Main.game.isInGlobalMap()) {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullWorldMap());
//			} else {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullMap(Main.game.getPlayer().getWorldLocation(), true));
//			}
			
			UtilText.nodeContentSB.append("<p>Вы достаете телефон и вводите код разблокировки.</p>");
			
			if(Main.game.isInNewWorld()) {
				UtilText.nodeContentSB.append(
						"<p>Используя свою мощную ауру, вы сумели найти способ направлять аркану на зарядку аккумулятора телефона, хотя, учитывая, что он единственный в этом мире, толку от звонков мало. Вместо этого вы используете его как способ хранения информации о вещах, которые обнаружили в этом странном новом мире.</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
				if(index==0) {
					return "Телефон";
				} else if(index==1) {
					return "[style.colourYellowLight(Яйца)]";
				}
			}
			return super.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response(
							(Main.game.getPlayer().isMainQuestUpdated() || Main.game.getPlayer().isSideQuestUpdated() || Main.game.getPlayer().isRelationshipQuestUpdated())
								?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Задания</span>"
								:"Задания",
							"Откройте планировщик, чтобы просмотреть текущие задания.", PLANNER_MAIN){
						@Override
						public void effects() {
							Main.game.getPlayer().setMainQuestUpdated(false);
						}
					};
					
				} else if (index == 2) {
					return new Response(
							Main.getProperties().hasValue(PropertyValue.levelUpHightlight)
								? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Древо навыков</span>"
								:"Древо навыков",
							"Просмотреть страницу персонажа.", CHARACTER_PERK_TREE);
					
				} else if (index == 3) {
					return new Response("Заклинания", "Просмотреть страницу заклинаний.", SpellManagement.CHARACTER_SPELLS_EARTH) {
						@Override
						public void effects() {
							SpellManagement.setSpellOwner(Main.game.getPlayer(), MENU);
						}
					};
					
				} else if (index == 4) {
					return new Response("Фетиши", "Просмотреть страницу фетишей.", CHARACTER_FETISHES);
					
				} else if (index == 5) {
					return new Response("Статы", "Подробно изучить свои характеристики.", CHARACTER_STATS);
					
				} else if (index == 6) {
					return new Response("Селфи", "Сделайте селфи, чтобы получше рассмотреть себя.", CHARACTER_APPEARANCE);
					
				} else if (index == 7) {
					if(Main.game.getPlayer().getCharactersEncountered().isEmpty()) {
						return new Response("Контакты", "Вы еще ни с кем не познакомились!", null);
					} else {
						return new Response("Контакты", "Хотя в этом мире нет телефонов и вы не можете никому позвонить, вы все же ведете список всех людей, с которыми пересекались.", CONTACTS) {
							@Override
							public void effects() {
								Main.game.getPlayer().sortCharactersEncountered();
								charactersEncountered = Main.game.getPlayer().getCharactersEncounteredAsGameCharacters(false);
							}
						};
					}
					
				} else if (index == 8) {
					return new Response(
							(Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))
								? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Энциклопедия</span>"
								:"Энциклопедия",
							"Посмотрите на все предметы и расы, которые вы открыли к этому моменту.", ENCYCLOPEDIA){
						@Override
						public void effects() {
							resetContentForRaces();
						}
					};
					
				} else if (index == 9) {
					if(Main.game.getPlayer().isAbleToSelfTransform()) {
						return new Response("Трансформация",
								"Преобразовать свое тело.",
								BodyChanging.BODY_CHANGING_CORE) {
							@Override
							public void effects() {
								BodyChanging.setTarget(Main.game.getPlayer());
							}
						};
					} else {
						return new Response("Трансформация", Main.game.getPlayer().getUnableToTransformDescription(), null);
					}
					
				} else if (index == 10) {
					return new Response("Карты", "Просмотрите карты всех мест, которые вы посетили.", MAP) {
						@Override
						public void effects() {
							worldTypeMap = Main.game.getPlayer().getWorldLocation();
						}
					};
					
				} else if (index == 11) {
					if(Main.game.isSavedDialogueNeutral()) {
						return new Response("Боевые приемы", "Настройте основные боевые приемы, которые вы готовы использовать в бою.", CombatMovesSetup.COMBAT_MOVES_CORE) {
							@Override
							public void effects() {
								CombatMovesSetup.setTarget(Main.game.getPlayer(), PhoneDialogue.MENU);
							}
						};
					} else {
						return new Response("Боевые приемы", "Вы слишком заняты, чтобы менять свои основные боевые приемы.", null);
					}
					
				} else if (index == 12) {
					if(!Main.game.isSavedDialogueNeutral()) {
							return new Response("Мастурбировать", "Сейчас ты слишком занят, чтобы мастурбировать. (Можно совершить только в нейтральной сцене.)", null);
							
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("Мастурбировать", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("Мастурбировать",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" фетиш)], тебе некомфортно мастурбировать там, где тебя могут увидеть!",
								null);
						
					} else {
						return new ResponseSex("Мастурбировать",
								"Решить сделать перерыв в текущих делах, чтобы заняться мастурбацией.",
								true,
								true,
								new SMMasturbation(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMasturbation.STANDING))),
								Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)
									?Main.game.getPlayer().getParty()
									:null,
								null,
								AFTER_MASTURBATION,
								UtilText.parseFromXMLFile("misc/misc", "MASTURBATION"));
					}
					
				} else if (index == 13){
					String title = Main.game.isSillyMode()?"Отложить":"Поглазеть по сторонам";
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response(title, "Бездельничать, чтобы скоротать время, можно только в нейтральной сцене диалога!", null);
					}
					if(Main.game.getPlayerCell().getPlace().isDangerous()) {
						return new Response(title, "Бездельничать, чтобы скоротать время, можно только в безопасной зоне!", null);
					}
					if(Main.game.getPlayer().getLocationPlace().getPlaceType().isLoiteringEnabledOverride()) {
						if(!Main.game.getPlayer().getLocationPlace().getPlaceType().isLoiteringEnabled()) {
							return new Response(title, "Это не самое подходящее место для того, чтобы слоняться без дела!", null);
						}
					} else {
						if(!Main.game.getPlayerCell().getType().isLoiteringEnabled()) {
							return new Response(title, "Это не самое подходящее место для того, чтобы слоняться без дела!", null);
						}
					}
					return new Response(title, "Задуматься о том, чтобы побродить по этой местности в течение неопределенного времени.", LOITER_SELECTION);
					
				} else if (index == 14){
					if(!Main.game.getPlayer().isElementalSummoned()) {
						if(Main.game.getPlayer().hasDiscoveredElemental()) {
							return new Response("[el.Name]",
									"Ты еще не призвал [el.name], поэтому не можешь говорить с [el.herHim]!<br/>[style.italicsMinorGood(Ты можешь призвать своего элементаля из экрана «Заклинания»!)]",
									null);
						}
						return new Response("Элементаль",
								"Вы еще не призвали своего элементаля, поэтому не можете с ним поговорить...<br/>[style.italicsMinorGood(Вы можете призвать своего элементаля, если выучите заклинание призыва элементаля и примените его в окне «Заклинания»!)]",
								null);
					}
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("[el.Name]",
								Main.game.isInSex()
									?"Вы не можете разговаривать с [el.name] во время секса!"
									:(Main.game.isInCombat()
										?"Вы не можете разговаривать с [el.name] во время боя!"
										:"Вы не можете разговаривать с [el.name] в этой сцене!"),
								null);
					}
					return new Response("[el.Name]",
							"Провести время в разговоре с [el.name].",
							ElementalDialogue.ELEMENTAL_START);
					
				}
				
			} else if(responseTab==1) {
				Set<SexAreaOrifice> incubationAreas = Main.game.getPlayer().getIncubatingLitters().keySet();
				List<Response> responses = new ArrayList<>();
				
				if(incubationAreas.contains(SexAreaOrifice.VAGINA)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Отложить яйца (матка)", "Сейчас вы слишком заняты, чтобы откладывать яйца. (Можно выполнить только в нейтральной сцене.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Отложить яйца (матка)", "Поблизости нет подходящего места, чтобы отложить яйца!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Отложить яйца (матка)", "Вы не можете добраться до своей вагины, поэтому пока не способны отложить яйца, созревшие в вашем чреве!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Отложить яйца (матка)",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], вам некомфортно откладывать яйца в месте, где вас могут увидеть люди!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
						responses.add(new Response("Отложить яйца (матка)", "Вам нужно подождать, пока яйца в вашем чреве закончат рост, прежде чем вы сможете их отложить.", null));
						
					} else {
						responses.add(new Response("Отложить яйца (матка)", "Найдите поблизости подходящее место, чтобы отложить яйца, которые вы вынашиваете в своем чреве.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.VAGINA;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.ANUS)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Отложить яйца (желудок)", "Сейчас вы слишком заняты, чтобы откладывать яйца. (Можно выполнить только в нейтральной сцене.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Отложить яйца (желудок)", "Поблизости нет подходящего места, чтобы отложить яйца!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						responses.add(new Response("Отложить яйца (желудок)", "Вы не можете добраться до своего ануса, поэтому пока не способны отложить яйца, созревшие в вашем желудке!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Отложить яйца (желудок)",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], вам некомфортно откладывать яйца в месте, где вас могут увидеть люди!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
						responses.add(new Response("Отложить яйца (желудок)", "Тебе нужно подождать, пока яйца в твоем животе окончательно вырастут, прежде чем ты сможешь их отложить.", null));
						
					} else {
						responses.add(new Response("Отложить яйца (желудок)", "Найди подходящее место поблизости, чтобы отложить яйца, которые ты вынашиваешь в животе.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.ANUS;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.NIPPLE)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Отложить яйца (грудь)", "Сейчас вы слишком заняты, чтобы откладывать яйца. (Можно выполнить только в нейтральной сцене.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Отложить яйца (грудь)", "Поблизости нет подходящего места, чтобы отложить яйца!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
						responses.add(new Response("Отложить яйца (грудь)", "Ты не можешь добраться до своих сосков, поэтому сейчас не в состоянии отложить яйца, созревшие в твоей груди!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Отложить яйца (грудь)",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], вам некомфортно откладывать яйца в месте, где вас могут увидеть люди!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
						responses.add(new Response("Отложить яйца (грудь)", "Тебе нужно подождать, пока яйца в твоей груди окончательно вырастут, прежде чем ты сможешь их отложить.", null));
						
					} else {
						responses.add(new Response("Отложить яйца (грудь)", "Найди поблизости подходящее место, чтобы отложить яйца, которые ты вынашивала в своей груди.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.NIPPLE;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH)) {
					String udderName = Main.game.getPlayer().getBreastCrotchShape()==BreastShape.UDDERS?"udders":"crotch-boobs";
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Отложить яйца ("+udderName+")", "Сейчас вы слишком заняты, чтобы откладывать яйца. (Можно выполнить только в нейтральной сцене.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Отложить яйца ("+udderName+")", "Поблизости нет подходящего места, чтобы отложить яйца!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
						responses.add(new Response("Отложить яйца ("+udderName+")", "Ты не можешь получить доступ к своим [pc.crotchNipples], поэтому сейчас не можешь отложить яйца, созревшие в твоих [pc.crotchBoobs]!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Отложить яйца ("+udderName+")",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], вам некомфортно откладывать яйца в месте, где вас могут увидеть люди!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
						responses.add(new Response("Отложить яйца ("+udderName+")", "Тебе нужно подождать, пока яйца в твоих [pc.crotchBoobs] закончат расти, прежде чем ты сможешь их отложить.", null));
						
					} else {
						responses.add(new Response("Отложить яйца ("+udderName+")", "Найди поблизости подходящее место, чтобы отложить яйца, которые ты вынашивала в своих [pc.crotchBoobs].", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.NIPPLE_CROTCH;
								applyEggLayingEffects();
							}
						});
					}
				
				}
				if(incubationAreas.contains(SexAreaOrifice.SPINNERET)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Отложить яйца (паутинным аппаратом)", "Сейчас вы слишком заняты, чтобы откладывать яйца. (Можно выполнить только в нейтральной сцене.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Отложить яйца (паутинным аппаратом)", "Поблизости нет подходящего места, чтобы отложить яйца!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Отложить яйца (паутинным аппаратом)",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], вам некомфортно откладывать яйца в месте, где вас могут увидеть люди!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
						responses.add(new Response("Отложить яйца (паутинным аппаратом)", "Вам нужно подождать, пока яйца в вашем паутинном аппарате закончат созревать, прежде чем вы сможете их отложить.", null));
						
					} else {
						responses.add(new Response("Отложить яйца (паутинным аппаратом)", "Найдите подходящее место поблизости, чтобы отложить яйца, которые вы вынашивали в своем паутинном аппарате.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.SPINNERET;
								applyEggLayingEffects();
							}
						});
					}
				}
				
				if(index>0 && index-1<responses.size()) {
					return responses.get(index-1);
				}
				
				// Removed due to description handling being too messy
//				if(incubationAreas.size()>1) {
//					Set<SexAreaOrifice> readyToLay = new HashSet<>();
//					if(incubationAreas.contains(SexAreaOrifice.VAGINA) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
//						readyToLay.add(SexAreaOrifice.VAGINA);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.ANUS) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
//						readyToLay.add(SexAreaOrifice.ANUS);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE_CROTCH);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.SPINNERET) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
//						readyToLay.add(SexAreaOrifice.SPINNERET);
//					}
//					if(readyToLay.size()<=1) {
//						return new Response("Lay eggs (all)", "You need to wait until the eggs in at least two of your orifices have finished maturing before you're able to lay them all at once.", null);
//					} else {
//						return new Response("Lay eggs (all)", "Find a suitable place nearby in which to lay all of the matured eggs which you've been incubating in your orifices.", INCUBATION_EGG_LAYING) {
//							@Override
//							public void effects() {
//								layingEggsAreas = new HashSet<>(readyToLay);
//								applyEggLayingEffects();
//							}
//						};
//					}
//				}
			}
			
			if(index == 0) {
				return new ResponseEffectsOnly("Назад", "Убрать телефон."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
				
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode INCUBATION_EGG_LAYING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.addSpecialParsingString(Util.intToString(incubationOffspringBirthed.size()), true);
			UtilText.addSpecialParsingString(incubationOffspringBirthed.size()==1?"egg":"eggs", false);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()));
			
			if(incubationOffspringBirthed.size()==1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()+"_SINGLE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()+"_MULTIPLE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Отдых", "У вас не осталось энергии, а веки, кажется, становятся всё тяжелее...", INCUBATION_EGG_LAYING_FINISHED){
					@Override
					public void effects() {
						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_UTIL_COMPLETE));
						}
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode INCUBATION_EGG_LAYING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.addSpecialParsingString(Util.intToString(incubationOffspringBirthed.size()), true);

			if(incubationOffspringBirthed.size()==1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_SINGLE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_MULTIPLE"));
			}
			
			UtilText.nodeContentSB.append("<p style='text-align:center;'>");
				for(String id : incubationOffspringBirthed) {
					try {
						OffspringSeed offspring = Main.game.getOffspringSeedById(id);
						String descriptor = LilayaBirthing.getOffspringDescriptor(offspring);
						UtilText.nodeContentSB.append("<br/>"
								+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor
								+ " <i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGenderName()+"</i>"
								+ " <i style='color:"+offspring.getSubspecies().getColour(null).toWebHexString()+";'>"+offspring.getSubspecies().getName(offspring.getBody())+"</i>");
					} catch(Exception ex) {
					}
				}
			UtilText.nodeContentSB.append("</p>");
			
			if(incubationOffspringBirthed.size()==1) {
				try {
					OffspringSeed offspring = Main.game.getOffspringSeedById(incubationOffspringBirthed.iterator().next());
					UtilText.addSpecialParsingString(offspring.getSubspecies().getName(offspring.getBody()), true);
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_END_SINGLE"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_END_MULTIPLE"));
			}
	
			return UtilText.nodeContentSB.toString();
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Успешно отложив яйца, теперь вы вольны продолжить свой путь.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_MASTURBATION = new DialogueNode("Завершено", "На сегодня с мастурбацией покончено.", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/misc", "AFTER_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь...", Main.game.getDefaultDialogue(false));

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode PLANNER_MAIN = new DialogueNode("Планировщик", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			// Main Quests:
			QuestLine questLine = QuestLine.MAIN;
			List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
			int index = questList.size()-1;
			Quest q = questList.get(index);
			
			if (Main.game.getPlayer().isQuestCompleted(questLine)) {
				journalSB.append(
						"<details open>"
						+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>Завершено - " + questLine.getName()
						+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, true));
				
			} else{
				journalSB.append(
						"<details open>"
							+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
								+ questLine.getName()
							+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, false));
			}
			
			index--;
				
			while(index>=0) {
				q = questList.get(index);
				journalSB.append(getQuestBoxDiv(q, true));
				index--;
			}

			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные квесты", "Посмотреть прогресс по основным квестам.", null);
				
			} else if (index == 2) {
				return new Response(
						(Main.game.getPlayer().isSideQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Побочные квесты</span>"
							:"Побочные квесты"),
						"Посмотреть свои побочные квесты.",
						PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
				
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Романтические квесты</span>"
							:"Романтические квесты"),
						"Посмотреть ваши романтические квесты.",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_SIDE = new DialogueNode("Планировщик", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean sideQuestsFound = false;
			
			// Side Quests:
			List<QuestLine> sideQuests = new ArrayList<>(Main.game.getPlayer().getQuests().keySet());
			sideQuests.sort((q1, q2)->
				Main.game.getPlayer().isQuestCompleted(q1)
					?(Main.game.getPlayer().isQuestCompleted(q2)
						?0
						:1)
					:(Main.game.getPlayer().isQuestCompleted(q2)
						?-1
						:0));
			for (QuestLine questLine : sideQuests) {
				if(questLine.getType()==QuestType.SIDE) {
					sideQuestsFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.getShades()[1] + ";'>Ошибка - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>Завершено - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!sideQuestsFound) {
				journalSB.append("<div class='subTitle'>У вас нет никаких побочных квестов!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные квесты", "Посмотреть прогресс по основным квестам.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response("Побочные квесты", "Посмотреть свои побочные квесты.", null);
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Романтические квесты</span>"
							:"Романтические квесты"),
							"Посмотреть ваши романтические квесты.",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_RELATIONSHIP = new DialogueNode("Планировщик", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean relationshipQuestFound = false;
			
			// Romance Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.RELATIONSHIP) {
					relationshipQuestFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.getShades()[1] + ";'>Ошибка - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>Завершено - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!relationshipQuestFound) {
				journalSB.append("<div class='subTitle'>У вас нет никаких романтических квестов!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные квесты", "Посмотреть прогресс по основным квестам.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Побочные квесты</span>"
						:"Побочные квесты"), "Посмотреть свои побочные квесты.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
			} else if (index == 3) {
				return new Response("Романтические квесты", "Посмотреть ваши романтические квесты.", null);
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getQuestBoxDivFailed(Quest q) {
		return "<div class='quest-box'>"
				+ getLevelAndExperienceHTML(q, true)
				+ "<h6 style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";text-align:center;'><b>Ошибка - "+ q.getName() + "</b>"
				+ "</h6>"
				+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
					+ q.getCompletedDescription()
				+ "</p>" 
			+ "</div>";
	}
	
	private static String getQuestBoxDiv(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
//			return "<div class='quest-box'>"
//					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
//							+ "<b>Completed - "+ q.getName() + "</b>"
//					+ "</h6>"
//				+ "</div>";
		}
		
		if(completed) {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'><b>Завершено - "+ q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center; margin-top:0;'>"
						+ q.getCompletedDescription()
					+ "</p>" 
				+ "</div>";
			
		} else {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().toWebHexString()+ "; text-align:center; margin-top:0;'><b>" + q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='text-align:center;'>"
						+ q.getDescription()
					+ "</p>"
				+ "</div>";
		}
	}
	
	private static String getLevelAndExperienceHTML(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
		}
		
		if (!completed) {
			if(q.getLevel() <= Main.game.getPlayer().getLevel() - 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Уровень " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " опыта</b>";
				
			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_BAD.toWebHexString() + ";'>Уровень " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " опыта</b>";
				
			} else {
				return "<b class='quest-extra level'>Уровень " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " опыта</b>";
			}
			
		} else {
			return "<b class='quest-extra level' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Уровень " + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " опыта</b>";
		}
	}
	

	public static final DialogueNode CHARACTER_APPEARANCE = new DialogueNode("Селфи", "Сделать селфи", true) {

		@Override
		public String getContent() {
//			return Main.game.getPlayer().getBodyDescription();
			return Main.game.getPlayer().getCharacterInformationScreen(true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getAttributeDisplayValue(AbstractAttribute att) {
		String valueForDisplay = Units.number(Main.game.getPlayer().getAttributeValue(att));
		
		if(att.isInfiniteAtUpperLimit() && Main.game.getPlayer().getAttributeValue(att)>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		
		return valueForDisplay;
	}
	
	public static final DialogueNode CHARACTER_STATS = new DialogueNode("Характеристики персонажа", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			AbstractStatusEffect physiqueSE = PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect();
			AbstractStatusEffect arcaneSE = IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect();
			AbstractStatusEffect corruptionSE = CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect();
					
			UtilText.nodeContentSB.append(
				"<div class='container-full-width'>"
					+ statAttributeHeader()
					
					+ "<p style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'><b>Основные атрибуты</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE,
							"'<b style='color:"+physiqueSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(physiqueSE.getName(Main.game.getPlayer()))+"</b> получен эффект")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE,
							"'<b style='color:"+arcaneSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arcaneSE.getName(Main.game.getPlayer()))+"</b> получен эффект")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION,
							"'<b style='color:"+corruptionSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(corruptionSE.getName(Main.game.getPlayer()))+"</b> получен эффект")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.HEALTH_MAXIMUM,
							"Когда значение падает до 0, вы проигрываете бой")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MANA_MAXIMUM,
							"Используется как ресурс для сотворения заклинаний")
//				+"</div>"
//				
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'><b>Разное. Атрибуты</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"Увеличивает шанс беременности")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"Увеличивает шанс оплодотворения")

					+ "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'><b style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>Расчет беременности:</b><br/><i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"
//				+"</div>"
//					
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>Боевые атрибуты</b></p>"
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"Наносит <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE))+"%</b> обычного урона",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.ENERGY_SHIELDING,
							"<b>"+getAttributeDisplayValue(Attribute.ENERGY_SHIELDING)+"Блок Здоровья/ход: </b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"Снижает стоимость заклинания на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER))+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"Увеличивает урон заклинания на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"Увеличивает урон без оружия на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED))+"%</b>",
							true)
					

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"Увеличивает урон холодного оружия на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"Увеличивает урон дистанционного оружия на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON))+"%</b>",
							true)
					
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"Увеличивает физический урон на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_PHYSICAL)+"Физический блок/ход: </b>",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"Увеличивает урон огнем на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_FIRE)+"Блок огня/ход: </b>",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"Увеличивает урон льдом на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_ICE)+"</b> Блока льда/ход",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"Увеличивает урон ядом на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_POISON)+"</b> Блока яда/ход",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"Увеличивает урон Похотью на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_LUST)+"</b> Блока Похоти/ход",
							true)

//				+"</div>"
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>Расовые атрибуты урона</b></p>");
			
			List<AbstractRace> raceListSorted = new ArrayList<>(Attribute.racialAttributes.keySet());
			raceListSorted.sort((r1, r2) -> r1.getName(true).compareTo(r2.getName(true)));
			
			for(AbstractRace race : raceListSorted) {
				AbstractAttribute attribute = Attribute.racialAttributes.get(race);
				int damageModifier = (int) Main.game.getPlayer().getAttributeValue(attribute);
				if(race==Race.DEMON) {
					// DEMON is split in IMP, DEMON, LILIN, and ELDER_LILIN damage
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_IMP,
									"Увеличивает урон по бесам на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_IMP))+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), attribute,
									"Увеличивает урон против "+race.getNamePlural(true)+" на <b>"+Units.number(damageModifier)+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LILIN,
									"Увеличивает урон по лилин на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LILIN))+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ELDER_LILIN,
									"Увеличивает урон по старшим лилин на <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ELDER_LILIN))+"%</b>",
									true));
				} else {
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), attribute,
									"Увеличивает урон против "+race.getNamePlural(true)+" на <b>"+Units.number(damageModifier)+"%</b>",
									true));
				}
			}
			
//			List<AbstractAttribute> encounteredAttributes = new ArrayList<>();
//			for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
//				AbstractAttribute damageModifier = subspecies.getDamageMultiplier();
//				if(!encounteredAttributes.contains(damageModifier)) {
//					UtilText.nodeContentSB.append(
//							getAttributeBox(Main.game.getPlayer(), damageModifier,
//									"Increases damage vs "+subspecies.getNamePlural(null)+" by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(damageModifier))+"%</b>",
//									true));
//					encounteredAttributes.add(damageModifier);
//				}
//			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные характеристики", "Внимательно изучите свои основные характеристики.", null);
			
			} else if (index == 2) {
				return new Response("Характеристики тела", "Внимательно изучите показатели вашего тела.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Сексуальные характеристики", "Внимательно изучите ваши сексуальные характеристики.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Характеристики беременности", "Внимательно изучите показатели вашей беременности.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static String getBodyStatsPanel(GameCharacter character) {
		boolean knowsNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		boolean knowsCrotchNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
		boolean knowsPenis = character.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer());
		boolean knowsVagina = character.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer());
		boolean knowsAnus = character.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer());
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='margin-bottom:0;'>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Атрибуты тела")
				+ statHeader()
				+ statRow(PresetColour.ANDROGYNOUS, "Женственность",
						PresetColour.TEXT, String.valueOf(character.getFemininityValue()),
						character.getFemininity().getColour(), Util.capitaliseSentence(character.getFemininity().getName(false)))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Рост",
						PresetColour.TEXT, Units.size(character.getHeightValue()),
						character.getHeight().getColour(), Util.capitaliseSentence(character.getHeight().getDescriptor()))
				+ statRow(PresetColour.MUSCLE_THREE, "Рельеф мышц",
						PresetColour.TEXT, String.valueOf(character.getMuscleValue()),
						character.getMuscle().getColour(), Util.capitaliseSentence(character.getMuscle().getName(false)))
				+ statRow(PresetColour.BODY_SIZE_THREE, "Размер тела",
						PresetColour.TEXT, String.valueOf(character.getBodySizeValue()),
						character.getBodySize().getColour(), Util.capitaliseSentence(character.getBodySize().getName(false)))
				+ statRow(character.getBodyShape().toWebHexStringColour(), "Форма тела",
						PresetColour.TEXT,
						"<b style='color:"+character.getMuscle().getColour().toWebHexString()+";'>"+character.getMuscleValue()+"</b>"
								+ " <b>|</b> <b style='color:"+character.getBodySize().getColour().toWebHexString()+";'>"+character.getBodySizeValue()+"</b>",
						character.getBodyShape().toWebHexStringColour(), Util.capitaliseSentence(character.getBodyShape().getName(false))));
		
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GREATER, "Параметры головы и горла")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина волос",
						PresetColour.TEXT, Units.size(character.getHairRawLengthValue()),
						character.getHairLength().getColour(), Util.capitaliseSentence(character.getHairLength().getDescriptor()))
				+ (character.hasHorns()
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина рогов",
							PresetColour.TEXT, Units.size(character.getHornLengthValue()),
							character.getHornLength().getColour(), Util.capitaliseSentence(character.getHornLength().getDescriptor()))
						:statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина рогов",
								PresetColour.TEXT, Units.size(0),
								PresetColour.TEXT_GREY, "N/A"))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина языка",
						PresetColour.TEXT, Units.size(character.getTongueLengthValue()),
						PresetColour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(character.getTongueLength().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Влажность горла",
						PresetColour.TEXT, String.valueOf(character.getFaceWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Вместимость горла",
						PresetColour.TEXT, Units.size(character.getFaceRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Глубина горла",
						PresetColour.TEXT, String.valueOf(character.getFaceDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceDepth().getDescriptor()) + " ("+(Math.round(character.getFaceDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Комфортная глубина горла",
						PresetColour.GENERIC_MINOR_GOOD, Units.size(character.getFaceMaximumPenetrationDepthComfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Некомфортная глубина горла",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"Без ограничений":Units.size(character.getFaceMaximumPenetrationDepthUncomfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Эластичность горла",
						PresetColour.TEXT, String.valueOf(character.getFaceElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Пластичность горла",
						PresetColour.TEXT, String.valueOf(character.getFacePlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFacePlasticity().getDescriptor())));
				
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasNipples()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Параметры груди")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Размер чашки",
							PresetColour.TEXT, String.valueOf(character.getBreastRawSizeValue()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Количество",
							PresetColour.TEXT, String.valueOf(character.getBreastRows()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(Util.capitaliseSentence(Util.intToString(character.getBreastRows()))+" пара"+(character.getBreastRows()==1?"":"s")))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Запас молока",
							PresetColour.TEXT, !knowsNipples?"Неизвестно":Units.fluid(character.getBreastRawMilkStorageValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Неизвестно":Util.capitaliseSentence(character.getBreastMilkStorage().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Регенерация молока на одну грудь",
							PresetColour.TEXT, !knowsNipples?"Неизвестно":Units.fluid(character.getLactationRegenerationPerSecond(false)*60)+"/мин.",
							PresetColour.GENERIC_SEX, !knowsNipples?"Неизвестно":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Общая регенерация молока",
							PresetColour.TEXT, !knowsNipples?"Неизвестно":Units.fluid(character.getLactationRegenerationPerSecond(true)*60)+"/мин.",
							PresetColour.GENERIC_SEX, !knowsNipples?"Неизвестно":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Емкость",
							PresetColour.TEXT, !knowsNipples?"Неизвестно":Units.size(character.getNippleRawCapacityValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Неизвестно":Util.capitaliseSentence(character.getNippleCapacity().getDescriptor()))
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Глубина сосков",
							PresetColour.TEXT, String.valueOf(character.getNippleDepth().getValue()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleDepth().getDescriptor()) + " ("+(Math.round(character.getNippleDepth().getDepthPercentage()*100))+"%)")
						:"")
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Комфортная глубина сосков",
							PresetColour.GENERIC_MINOR_GOOD, (!knowsNipples?"Неизвестно":Units.size(character.getNippleMaximumPenetrationDepthComfortable())),
							PresetColour.TEXT, "N/A")
						:"")
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Некомфортная глубина сосков",
							PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"Без ограничений":(!knowsNipples?"Неизвестно":Units.size(character.getNippleMaximumPenetrationDepthUncomfortable())),
							PresetColour.TEXT, "N/A")
						:"")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Эластичность",
							PresetColour.TEXT, !knowsNipples?"Неизвестно":String.valueOf(character.getNippleElasticity().getValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Неизвестно":Util.capitaliseSentence(character.getNippleElasticity().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Пластичность",
							PresetColour.TEXT, !knowsNipples?"Неизвестно":String.valueOf(character.getNipplePlasticity().getValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Неизвестно":Util.capitaliseSentence(character.getNipplePlasticity().getDescriptor())));
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Параметры груди")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] нет груди...")));
		}
		
		if(character.hasBreastsCrotch()) {
			sb.append("<span style='height:16px;width:100%;float:left;'></span>");
			rowCount = 0;
			
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, (character.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски"))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Размер",
								PresetColour.TEXT, !character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples?"Неизвестно":String.valueOf(character.getBreastCrotchRawSizeValue()),
								PresetColour.GENERIC_SEX,
								!character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples
									?"Неизвестно"
									:(character.getBreastCrotchShape()==BreastShape.UDDERS
										?Util.capitaliseSentence(character.getBreastCrotchSize().getDescriptor())
										:Util.capitaliseSentence(character.getBreastCrotchSize().getCupSizeName())))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Количество",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":String.valueOf(character.getBreastCrotchRows()),
								PresetColour.GENERIC_SEX,
								!knowsCrotchNipples
									?"Неизвестно"
									:(character.getBreastCrotchRows()==0
										?"Одиночное вымя"
										:Util.capitaliseSentence(Util.intToString(character.getBreastCrotchRows()))+" пара"+(character.getBreastCrotchRows()==1?"":"s")))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Запас молока",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":Units.fluid(character.getBreastCrotchRawMilkStorageValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Неизвестно":Util.capitaliseSentence(character.getBreastCrotchMilkStorage().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Регенерация молока за грудь-яйцо",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":Units.fluid(character.getCrotchLactationRegenerationPerSecond(false)*60)+"/мин.",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Неизвестно":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Общая регенерация молока",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":Units.fluid(character.getCrotchLactationRegenerationPerSecond(true)*60)+"/мин.",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Неизвестно":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Емкость",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":String.valueOf(character.getNippleCrotchRawCapacityValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Неизвестно":Util.capitaliseSentence(character.getNippleCrotchCapacity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Глубина сосков",
								PresetColour.TEXT, String.valueOf(character.getNippleCrotchDepth().getValue()),
								PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleCrotchDepth().getDescriptor()) + " ("+(Math.round(character.getNippleCrotchDepth().getDepthPercentage()*100))+"%)")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "Комфортная глубина сосков",
								PresetColour.GENERIC_MINOR_GOOD, (!knowsCrotchNipples?"Неизвестно":Units.size(character.getNippleCrotchMaximumPenetrationDepthComfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "Некомфортная глубина сосков",
								PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"Без ограничений":(!knowsCrotchNipples?"Неизвестно":Units.size(character.getNippleCrotchMaximumPenetrationDepthUncomfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Эластичность",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":String.valueOf(character.getNippleCrotchElasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Неизвестно":Util.capitaliseSentence(character.getNippleCrotchElasticity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Пластичность",
								PresetColour.TEXT, !knowsCrotchNipples?"Неизвестно":String.valueOf(character.getNippleCrotchPlasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Неизвестно":Util.capitaliseSentence(character.getNippleCrotchPlasticity().getDescriptor())));
		}

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTail()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Характеристики хвоста")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина | Используется для проникновения",
							PresetColour.TEXT, Units.size(character.getTailLength(false))+" | "+Units.size(character.getTailLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Характеристики хвоста")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина",
							PresetColour.TEXT, Units.size(character.getTailLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"Диаметр на участках",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTailDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTailDiameter(character.getTailLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(Основание)]<br/>[style.colourSize3(33%)]<br/>[style.colourSize2(66%)]<br/>[style.colourSize1(Кончик)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"Обхват на участках",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTailCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTailCircumference(character.getTailLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(Основание)]<br/>[style.colourSize3(33%)]<br/>[style.colourSize2(66%)]<br/>[style.colourSize1(Кончик)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Характеристики хвоста")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] хвоста нет...")));
		}

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTentacle()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Характеристики щупальца")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина | Используется для проникновения",
							PresetColour.TEXT, Units.size(character.getTentacleLength(false))+" | "+Units.size(character.getTentacleLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Характеристики щупальца")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина",
							PresetColour.TEXT, Units.size(character.getTentacleLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"Диаметр на участках",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTentacleDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(Основание)]<br/>[style.colourSize3(33%)]<br/>[style.colourSize2(66%)]<br/>[style.colourSize1(Кончик)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"Обхват на участках",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTentacleCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(Основание)]<br/>[style.colourSize3(33%)]<br/>[style.colourSize2(66%)]<br/>[style.colourSize1(Кончик)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Характеристики щупальца")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] без щупалец...")));
		}
		
		sb.append( "<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsPenis) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики пениса")
					+ statRow(PresetColour.TEXT_GREY, "Неизвестно!"));
			
		} else if(character.hasPenis()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики пениса")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Длина",
						PresetColour.TEXT, (Units.size(character.getPenisRawSizeValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Диаметр | Обхват",
						PresetColour.TEXT, (Units.size(character.getPenisDiameter())+" | "+Units.size(character.getPenisCircumference())),
						PresetColour.TEXT_GREY, "N/A")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Размер яичек",
						PresetColour.TEXT, (String.valueOf(character.getTesticleSize().getValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getTesticleSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Запас спермы",
						PresetColour.TEXT, (Units.fluid(character.getPenisRawCumStorageValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisCumStorage().getDescriptor())))
				+ (Main.getProperties().hasValue(PropertyValue.cumRegenerationContent) ? statRow(PresetColour.TRANSFORMATION_GENERIC, "Регенерация спермы",
						PresetColour.TEXT, Units.fluid(character.getCumRegenerationPerSecond()*60)+"/мин.",
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumProductionRegeneration().getName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Извержение спермы (% oф накопленной спермы)",
						PresetColour.TEXT, String.valueOf(character.getPenisRawCumExpulsionValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumExpulsion().getDescriptor())) : ""));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики пениса")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] без пениса...")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsVagina) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики влагалища")
					+ statRow(PresetColour.TEXT_GREY, "Неизвестно!"));
			
		} else if(character.hasVagina()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики влагалища")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Размер клитора",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawClitorisSizeValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaClitorisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Смачивание",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Емкость",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawCapacityValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaCapacity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Глубина",
						PresetColour.TEXT, String.valueOf(character.getVaginaDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getVaginaDepth().getDescriptor()) + " ("+(Math.round(character.getVaginaDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Комфортный предел глубины",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getVaginaMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Некомфортный предел глубины",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"Без ограничений":(Units.size(character.getVaginaMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Эластичность",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaElasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Пластичность",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaPlasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaPlasticity().getDescriptor()))));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики влагалища")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] без влагалища...")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;

		if(!knowsAnus) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики ануса")
					+ statRow(PresetColour.TEXT_GREY, "Неизвестно!"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Характеристики ануса")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Смачивание",
						PresetColour.TEXT, String.valueOf(character.getAssWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Емкость",
						PresetColour.TEXT, Units.size(character.getAssRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Глубина",
						PresetColour.TEXT, String.valueOf(character.getAssDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssDepth().getDescriptor()) + " ("+(Math.round(character.getAssDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Комфортный предел глубины ануса",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getAssMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Предел комфортной глубины ануса",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"Без ограничений":(Units.size(character.getAssMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Эластичность",
						PresetColour.TEXT, String.valueOf(character.getAssElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Пластичность",
						PresetColour.TEXT, String.valueOf(character.getAssPlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssPlasticity().getDescriptor())));
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode CHARACTER_STATS_BODY = new DialogueNode("Характеристики тела", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Механика отверстий</summary>[style.boldSex(Вместимость:)] Вместимость отверстия определяет максимальный диаметр пенисов и хвостов, которые могут быть комфортно введены. <b>Более высокие значения вместимости означают, что отверстие может принимать более толстые объекты без чрезмерного растяжения.</b><br/>- Диаметр пениса рассчитывается на основе комбинации значений его «длины» и «обхвата», при этом дополнительные модификаторы (такие как «расширяющийся» или «сужающийся») дополнительно изменяют итоговое значение.<br/>- Диаметр хвоста рассчитывается на основе значения его «обхвата», а также роста персонажа.<br/>- Значения диаметра вместимости варьируются от 0 [units.sizes] (чрезвычайно узкое) до [units.sizes("+Math.round(Capacity.SEVEN_GAPING.getMaximumValue(false))+")] (широко разинув рот).<br/><br/>");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				UtilText.nodeContentSB.append(
					"[style.boldSex(Глубина:)] Глубина отверстия определяет максимальную длину пенисов и хвостов, которые могут быть в него вставлены — как с комфортом, так и с дискомфортом. <b>Чем выше значение глубины, тем более длинные объекты могут быть введены до того, как персонаж почувствует дискомфорт.</b><br/>- Комфортная и некомфортная глубина отверстия основывается на росте персонажа, а затем корректируется значением «глубины» самого отверстия.<br/>- Сексуальные партнеры, не использующие «грубый» темп секса, чьи пенисы или хвосты слишком длинны для полного введения, остановятся на пределе комфортной глубины.<br/>- Сексуальные партнеры, использующие «грубый» темп секса, будут проталкиваться за пределы комфортной глубины, вызывая у персонажа дискомфорт.<br/>- «Королевы размера» воспринимают «некомфортную» часть глубины отверстия как комфортную.<br/>- Slimes и элементали всегда имеют максимальное значение глубины для каждого своего отверстия.<br/>- Значения глубины варьируются от 0 ("+OrificeDepth.ZERO_EXTREMELY_SHALLOW.getDescriptor()+") до 7 (\"}"+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+")."
						
						+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[style.boldSex(Эластичность:)] Эластичность отверстия определяет, насколько быстро оно растягивается, а также влияет на то, будет ли распознано проникновение слишком крупного объекта. Если пенис партнера превышает пропускную способность вашего отверстия, то во время секса оно будет растягиваться каждый ход, при этом <b>чем выше значения эластичности, тем быстрее оно растягивается</b>. <b>Высокие значения эластичности также означают повышенную толерантность к объектам, которые обычно слишком велики для отверстия, позволяя вставлять более крупные предметы до того, как начнется растяжение</b>.<br/>Значения эластичности варьируются от 0 (чрезвычайно устойчивое к растяжению) до 7 (растягивается мгновенно).<br/><br/>[style.boldSex(Пластичность:)] Пластичность отверстия определяет, как быстро оно восстанавливается после растяжения. Если ваше отверстие растянулось во время секса, то <b>чем выше значения пластичности, тем медленнее оно будет восстанавливаться, а очень высокие значения означают, что оно никогда не вернет свою первоначальную узость</b>.<br/>Значения пластичности варьируются от 0 (мгновенно возвращается к исходному размеру после секса) до 7 (после секса не восстанавливает ни капли своего первоначального размера).</details>"
					
			+ getBodyStatsPanel(Main.game.getPlayer()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные характеристики", "Внимательно изучите свои основные характеристики.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Характеристики тела", "Внимательно изучите показатели вашего тела.", null);
			
			} else if (index == 3) {
				return new Response("Сексуальные характеристики", "Внимательно изучите ваши сексуальные характеристики.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Характеристики беременности", "Внимательно изучите показатели вашей беременности.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_SEX = new DialogueNode("Статистика секса", "", true) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center; width:100%; padding:0; margin:4px 0;'>Вы достигли оргазма [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")] раз"+(Main.game.getPlayer().getDaysOrgasmCount()==1?"":"s")
							+" сегодня, общее количество ваших оргазмов составляет [style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")].<br/>Твой рекорд по количеству оргазмов за один день сейчас составляет [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")].<br/>Всего у вас было секса: [style.boldSex("+Main.game.getPlayer().getUniqueSexPartnerCount()+")] "+(Main.game.getPlayer().getUniqueSexPartnerCount()==1?"человек":"разные люди")+"."
					+ "</div>");
					
			sb.append(sexStatHeader());
			
			boolean oddRow = false;
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Фингеринг",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
							-1,
							oddRow));
			
			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Анальный фингерринг",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
									-1,
									oddRow)
							:""));
			
			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Минет",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							oddRow));

			oddRow = !oddRow;
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Куннилингус",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
							-1,
							oddRow));

			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Анилингус",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
									-1,
									oddRow)
							:""));
			
			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}

			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Интеркруральный секс",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
									oddRow));

			oddRow = !oddRow;
					
			sb.append((Main.game.isFootContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Футбол (Footjobs)",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			if(Main.game.isFootContentEnabled()) {
				oddRow = !oddRow;
			}

			sb.append((Main.game.isArmpitContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Подмышечный секс",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			if(Main.game.isArmpitContentEnabled()) {
				oddRow = !oddRow;
			}
					
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Вагинальный секс",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							oddRow));

			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Анальный секс",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Проникновение в сосок",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isNipplePenEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Проникновение в сосок промежности",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isNipplePenEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Проникновение в уретру пениса",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isUrethraEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Проникновение в уретру влагалища",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные характеристики", "Внимательно изучите свои основные характеристики.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Характеристики тела", "Внимательно изучите показатели вашего тела.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Сексуальные характеристики", "Внимательно изучите ваши сексуальные характеристики.", null);
			
			} else if (index == 4) {
				return new Response("Характеристики беременности", "Внимательно изучите показатели вашей беременности.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_PREGNANCY = new DialogueNode("Статистика беременности", "", true) {

		private void OffspringHeaderDisplay(StringBuilder output, String word_one, String word_two, String color, int count) {
			output.append("<div class='extraAttribute-quarter'>");
			output.append(word_one);
			output.append("<br/>");
			output.append("<b style='color:").append(color).append(";'>");
			output.append(word_two);
			output.append("</b>");
			output.append("<br/>");
			output.append(count);
			output.append("</div>");
		}

		private void offspringTableLine(StringBuilder output, offspringTableLineSubject subject, boolean evenRow, boolean includeIncubationColumn) {
			String color = subject.female ? PresetColour.FEMININE.toWebHexString() : PresetColour.MASCULINE.toWebHexString();
			String feralString = "<span style='color:" + RaceStage.FERAL.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(RaceStage.FERAL.getName()) + "</span> ";
			
			String innerEntryStyle = "background:transparent; margin:0; padding:0; width:"+(includeIncubationColumn?"15":"20")+"%;";
			String innerEntryStyle2 = "background:transparent; margin:0; padding:0; width:15%;";
			String innerEntryStyleWide = "background:transparent; margin:0; padding:0; width:25%;";
			
			output.append("<div class='container-full-width' style='margin:0; width:100%; background:"+(evenRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
					output.append("<span style='color:").append(color).append(";'>");
						output.append(subject.child_name);
					output.append("</span>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle2+"'>");
					output.append(subject.is_feral ? feralString : "");
					output.append("<span style='color:").append(subject.race_color).append(";'>");
						output.append(subject.is_feral ? subject.species_name.toLowerCase() : subject.species_name);
					output.append("</span>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
//					output.append("<b>");
						output.append(subject.mother);
//					output.append("</b>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
//					output.append("<b>");
						output.append(subject.father);
//					output.append("</b>");
				output.append("</div>");
	
				if(includeIncubationColumn) {
					output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
						output.append(subject.incubator);
					output.append("</div>");
				}
				
				output.append("<div class='container-full-width' style='"+innerEntryStyleWide+"'>");
//					output.append("<b>");
						output.append(Util.stringsToStringList(subject.relationships, false));
//						output.append(
//								isGreyedOut
//									?"[style.colourDisabled("+Util.stringsToStringList(relationships, false)+")]"
//									:Util.stringsToStringList(relationships, false));
//					output.append("</b>");
				output.append("</div>");
			
			output.append("</div>");
		}

		@Override
		public String getContent() {
			int sonsBirthed=0;
			int daughtersBirthed=0;
			int sonsFathered=0;
			int daughtersFathered=0;
			int offspringIncubatedCount=0;
			
			List<String> implantedLittersEncountered = new ArrayList<>();
			
			// Birthed with player as the mother:
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()){
				sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			// Birthed with player as the father:
			for (Litter litter : Main.game.getPlayer().getLittersFathered()){
				sonsFathered+=(litter.isSelfImpregnation()?0:litter.getSonsFromMother()+litter.getSonsFromFather());
				daughtersFathered+=(litter.isSelfImpregnation()?0:litter.getDaughtersFromMother()+litter.getDaughtersFromFather());
			}
			// Egg-incubated offspring who have been birthed:
			for (Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				implantedLittersEncountered.add(litter.getId());
				for (String id : litter.getOffspring()) {
					if (id.contains("NPCOffspring")) {
						//NPCOffspring is always born
						offspringIncubatedCount += 1;
						sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
						daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
					} else {
						try {
							OffspringSeed o = Main.game.getOffspringSeedById(id);
							//OffspringSeed may be born or unborn
							if (o.isBorn()) {
								offspringIncubatedCount += 1;
								sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
								daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			// Egg-implanted offspring who have been birthed:
			for (Litter litter : Main.game.getPlayer().getLittersImplanted()) {
				if(!implantedLittersEncountered.contains(litter.getId())) {
					for (String id : litter.getOffspring()) {
						if (id.contains("NPCOffspring")) {
							//NPCOffspring is always born
							offspringIncubatedCount += 1;
							sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
							daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
						} else {
							try {
								OffspringSeed o = Main.game.getOffspringSeedById(id);
								//OffspringSeed may be born or unborn
								if (o.isBorn()) {
									offspringIncubatedCount += 1;
									sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
									daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}

			UtilText.nodeContentSB.setLength(0);

			OffspringHeaderDisplay(UtilText.nodeContentSB, "Материнство", "Сыновья", PresetColour.MASCULINE.toWebHexString(), sonsBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Материнство", "Дочери", PresetColour.FEMININE.toWebHexString(), daughtersBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Отцовство", "Сыновья", PresetColour.MASCULINE.toWebHexString(), sonsFathered);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Отцовство", "Дочери", PresetColour.FEMININE.toWebHexString(), daughtersFathered);

			int childrenMet = Main.game.getOffspring().size();
			int totalChildren = (sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered+offspringIncubatedCount);
			int percentageMet = totalChildren == 0 ? 100 : (100 * childrenMet / totalChildren);
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Total offspring: "+ totalChildren+" (Встреченные дети: "+ percentageMet +"%)</div>"
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ pregnancyDetails()

					+ "<span style='height:16px;width:100%;float:left;'></span>");
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Список отпрысков</div><div class='container-full-width' style='text-align:center;'><div class='container-full-width'style='float:left; margin:0; width:100%; font-weight:bold;'><div class='container-full-width' style='float:left; margin:0; width:20%;'>Имя</div><div class='container-full-width' style='float:left; margin:0; width:15%;'>Раса</div><div class='container-full-width' style='float:left; margin:0; width:20%;'>Мать</div><div class='container-full-width' style='float:left; margin:0; width:20%;'>Отец</div><div class='container-full-width' style='float:left; margin:0; width:25%;'>Ваши отношения</div></div>");
			
			int rowCount = 0;
			List<NPC> offspringMet= new ArrayList<>(Main.game.getOffspring());
			offspringMet.removeIf(npc -> npc.getIncubator()!=null && npc.getIncubator().isPlayer()); // Only non-egg incubated offspring
			List<OffspringSeed> offspringUnknown = new ArrayList<>(Main.game.getOffspringNotSpawned(os->true));
			if(offspringMet.isEmpty() && offspringUnknown.isEmpty()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='margin:0; padding:0; width:100%;float:left;'>[style.italicsDisabled(Нет отпрысков...)]</div>");
			} else {
				offspringMet.sort(Comparator.comparing(GameCharacter::getBirthday));
				for(NPC npc : offspringMet) {
					offspringTableLineSubject subject = new offspringTableLineSubject(npc);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount % 2 == 0, false);
					rowCount++;
				}

				offspringUnknown.sort(Comparator.comparing(OffspringSeed::getConceptionDate));
				for(OffspringSeed os : offspringUnknown) {
					offspringTableLineSubject subject = new offspringTableLineSubject(os);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, false);
					rowCount++;
				}
			}

			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<span style='height:16px;width:100%;float:left;'></span>");
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Список выведенного потомства</div><div class='container-full-width' style='text-align:center;'><div class='container-full-width'style='float:left; margin:0; width:100%; font-weight:bold;'><div class='container-full-width' style='float:left; margin:0; width:15%;'>Имя</div><div class='container-full-width' style='float:left; margin:0; width:15%;'>Раса</div><div class='container-full-width' style='float:left; margin:0; width:15%;'>Мать</div><div class='container-full-width' style='float:left; margin:0; width:15%;'>Отец</div><div class='container-full-width' style='float:left; margin:0; width:15%;'>Инкубировано</div><div class='container-full-width' style='float:left; margin:0; width:25%;'>Ваши отношения</div></div>");
			
			rowCount = 0;
			List<NPC> offspringIncubated = new ArrayList<>(Main.game.getOffspring());
			offspringIncubated.removeIf(npc -> npc.getIncubator()==null || !npc.getIncubator().isPlayer()); // Only egg incubated offspring
			offspringIncubated.removeAll(offspringMet);
			List<OffspringSeed> offspringIncubatedUnknown = new ArrayList<>(Main.game.getOffspringNotSpawned(os->true,true));
			offspringIncubatedUnknown.removeIf(os -> os.getIncubator()==null || !os.getIncubator().isPlayer()); // Only egg incubated offspring
			if(offspringIncubated.isEmpty() && offspringIncubatedUnknown.isEmpty()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='float:left; margin:0; width:100%;'>[style.italicsDisabled(Нет выведенного потомства...)]</div>");
			} else {
				offspringIncubated.sort(Comparator.comparing(GameCharacter::getBirthday));
				for(NPC npc : offspringIncubated) {
					offspringTableLineSubject subject = new offspringTableLineSubject(npc);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, true);
					rowCount++;
				}
				offspringIncubatedUnknown.sort(Comparator.comparing(OffspringSeed::getConceptionDate));
				for(OffspringSeed os : offspringIncubatedUnknown) {
					offspringTableLineSubject subject = new offspringTableLineSubject(os);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, true);
					rowCount++;
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Основные характеристики", "Внимательно изучите свои основные характеристики.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Характеристики тела", "Внимательно изучите показатели вашего тела.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Сексуальные характеристики", "Внимательно изучите ваши сексуальные характеристики.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Характеристики беременности", "Внимательно изучите показатели вашей беременности.", null);
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String sexStatHeader() {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; font-weight:bold; text-align:center;'><div class='container-full-width' style='width:33.3%; padding:0; margin:0;'>Тип</div><div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>Выполнено</div><div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>Кончи в<br/>Дано</div><div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>Получено</div><div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>Кончи в<br/>Получено</div></div>";
	}
	
	private static String sexStatRow(Colour colour, String name, int given, int loadsGiven, int received, int loadsReceived, boolean light) {
		return 
//				"<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; text-align:center;'>"
				"<div class='container-full-width inner' style='text-align:center; margin-bottom:0;"+(light?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"><div style='float:left; width:33.3%; padding:0; margin:0;'><span style='color:" + colour.toWebHexString() + ";'>" + name + "</span>"
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ given
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven)
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ received
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsReceived < 0 ? "<span class='option-disabled'>-</span>" : loadsReceived) 
					+ "</div>"
				+ "</div>";
	}
	
	private static String pregnancyRow(String topLeft, String bottomLeft, String topRight, String bottomRight) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append("<div class='container-full-width' style='text-align:center; margin-top:0; margin-bottom:4px;'>");
			contentSB.append("<div class='container-full-width' style='float:left; margin:0; width:25%; background:transparent;'>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(topLeft);
				contentSB.append("</div>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent; white-space:nowrap;'>");
					contentSB.append(bottomLeft);
				contentSB.append("</div>");
			contentSB.append("</div>");
	
			contentSB.append("<div class='container-full-width' style='float:left; margin:0; width:75%; background:transparent;'>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(topRight);
				contentSB.append("</div>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(bottomRight);
				contentSB.append("</div>");
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String pregnancyDetails() {
		StringBuilder contentSB = new StringBuilder();

		// Mothered children:
		
		boolean noPregnancies=true;

		contentSB.append("<div class='subTitle'>Материнство и вынашивание потомства</div>");
		
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)){
			
			StringBuilder possibleFathersSB = new StringBuilder();
			int potentialFatherCount = 0;
			for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsMother())){
				if(pp.getFather()!=null) {
					if(potentialFatherCount>0) {
						possibleFathersSB.append("<br/>");
					}
					possibleFathersSB.append(UtilText.parse(pp.getFather(),
							"[npc.Name(A)] ("
								+ (!pp.getFather().getRaceStage().getName().isEmpty()
										?"<span style='color:"+pp.getFather().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getFather().getRaceStage().getName())+"</span> "
										:"")
								+ "<span style='color:"+pp.getFather().getRace().getColour().toWebHexString()+";'>"
								+ (pp.getFather().getGender().isFeminine()
										?Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularFemaleName(pp.getFather().getBody()))
										:Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularMaleName(pp.getFather().getBody())))
								+ "</span>) Probability: "));
					
					if (pp.getProbability() <= 0) {
						possibleFathersSB.append("[style.italicsTerrible(Нет)]");
					} else if(pp.getProbability()<=0.15f) {
						possibleFathersSB.append("[style.italicsBad(Низкий)]");
					} else if(pp.getProbability()<=0.3f) {
						possibleFathersSB.append("[style.italicsMinorGood(Средний)]");
					} else if(pp.getProbability()<1) {
						possibleFathersSB.append("[style.italicsGood(Высокий)]");
					} else {
						possibleFathersSB.append("[style.italicsExcellent(Максимальный)]");
					}
					
					possibleFathersSB.append("</b>");
					potentialFatherCount++;
				}
			}
			
			String stage = "";
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)) {
				stage = StatusEffect.PREGNANT_0.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)) {
				stage = StatusEffect.PREGNANT_1.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)) {
				stage = StatusEffect.PREGNANT_2.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				stage = StatusEffect.PREGNANT_3.getName(Main.game.getPlayer());
			}
			
			contentSB.append(pregnancyRow("[style.boldBad(Беременность)]", "[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]", "<b>Возможные отцы:</b>", possibleFathersSB.toString()));
			
			noPregnancies=false;
		}
		
		Map<SexAreaOrifice, List<AbstractStatusEffect>> incubationEffectMap = Util.newHashMapOfValues(
				new Value<>(SexAreaOrifice.VAGINA, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_WOMB_1, StatusEffect.INCUBATING_EGGS_WOMB_2, StatusEffect.INCUBATING_EGGS_WOMB_3)),
				new Value<>(SexAreaOrifice.ANUS, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_STOMACH_1, StatusEffect.INCUBATING_EGGS_STOMACH_2, StatusEffect.INCUBATING_EGGS_STOMACH_3)),
				new Value<>(SexAreaOrifice.NIPPLE, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_1, StatusEffect.INCUBATING_EGGS_NIPPLES_2, StatusEffect.INCUBATING_EGGS_NIPPLES_3)),
				new Value<>(SexAreaOrifice.NIPPLE_CROTCH, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)),
				new Value<>(SexAreaOrifice.SPINNERET, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_SPINNERET_1, StatusEffect.INCUBATING_EGGS_SPINNERET_2, StatusEffect.INCUBATING_EGGS_SPINNERET_3)));
		
		for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> incubationEntry : incubationEffectMap.entrySet()) {
			if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(0))
					|| Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(1))
					|| Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(2))) {
				String stage = "";
				if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(0))) {
					stage = incubationEntry.getValue().get(0).getName(Main.game.getPlayer());
				} else if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(1))) {
					stage = incubationEntry.getValue().get(1).getName(Main.game.getPlayer());
				} else if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(2))) {
					stage = incubationEntry.getValue().get(2).getName(Main.game.getPlayer());
				}
				Litter litter = Main.game.getPlayer().getIncubationLitter(incubationEntry.getKey());
				contentSB.append(pregnancyRow("[style.boldBad(В процессе)] [style.boldPurple(Инкубация)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"Имплантер: [style.colourExcellent(Вы)]"
								:UtilText.parse(litter.getMother(), "Имплантер: <span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Имплантер: [style.colourDisabled(Неизвестно)]"),
						"В имплантировано в "
							+(incubationEntry.getKey()==SexAreaOrifice.VAGINA
								?"матку"
								:(incubationEntry.getKey()==SexAreaOrifice.ANUS || incubationEntry.getKey()==SexAreaOrifice.MOUTH
									?"живот"
									:incubationEntry.getKey().getName(Main.game.getPlayer(), true)))
							+": "
							+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG),
						"[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]"));
				noPregnancies=false;
			}
		}
		
		
		// Birthed:
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				String unknownName = "[style.colourDisabled(Неизвестно)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getFatherName().equals("???")) {
							unknownName = "<span style='color:"+c.getFatherFemininity().getColour().toWebHexString()+";'>"+c.getFatherName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getFatherName().equals("???")) {
							unknownName = "<span style='color:"+o.getFatherFemininity().getColour().toWebHexString()+";'>"+o.getFatherName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				contentSB.append(pregnancyRow("[style.boldGood(Беременность разрешилась)]",
						(litter.getFather()!=null
							?(litter.getFather().isPlayer()
								?"Отец: [style.colourExcellent(Вы)]"
								:UtilText.parse(litter.getFather(), "Отец: <span style='color:"+litter.getFather().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Отец: "+unknownName),
						"Зачатие: "
							+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+" | Рождено: "
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Родилось: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		if(!Main.game.getPlayer().getLittersIncubated().isEmpty()) {
			for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				contentSB.append(pregnancyRow("[style.boldGood(Разрешено)] [style.boldPurple(Инкубация)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"Имплантер: [style.colourExcellent(Вы)]"
								:UtilText.parse(litter.getMother(), "Имплантер: <span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Имплантер: [style.colourDisabled(Неизвестно)]"),
						"Внедрено: "
								+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
								+" | Рождено: "
								+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Родилось: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() +
					(Main.game.getPlayer().getLittersImplanted().isEmpty()
					?";'>Вы еще ни разу не были беременны...</span>"
					:";'>Вы еще ни разу не рожали...</span>")
					+ "</div>");
		}
		
		
		// Fathered:
		noPregnancies=true;
		
		contentSB.append("<span style='height:16px;width:100%;float:left;'></span>");
		
		contentSB.append("<div class='subTitle'>Отец и имплантация потомства</div>");
		
		for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsFather())) {
			if(pp.getMother()!=null) {
				String impregnationChance = "";
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					impregnationChance = "Вероятность зачатия: ";
					if (pp.getProbability() <= 0) {
						impregnationChance += "[style.italicsTerrible(Нет)]";
					} else if(pp.getProbability()<=0.15f) {
						impregnationChance += "[style.italicsBad(Низкий)]";
					} else if(pp.getProbability()<=0.3f) {
						impregnationChance += "[style.italicsMinorGood(Средний)]";
					} else if(pp.getProbability()<1) {
						impregnationChance += "[style.italicsGood(Высокий)]";
					} else {
						impregnationChance += "[style.italicsExcellent(Максимальный)]";
					}
				}
				
				String stage = "";
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					stage = StatusEffect.PREGNANT_0.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_1)) {
					stage = StatusEffect.PREGNANT_1.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_2)) {
					stage = StatusEffect.PREGNANT_2.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_3)) {
					stage = StatusEffect.PREGNANT_3.getName(pp.getMother());
				}
				
				String motherName = UtilText.parse(pp.getMother(), "[npc.Name(A)]")
						+" ("
						+ (!pp.getMother().getRaceStage().getName().isEmpty()
								?"<span style='color:"+pp.getMother().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getMother().getRaceStage().getName())+"</span> "
								:"")
						+ "<span style='color:"+pp.getMother().getRace().getColour().toWebHexString()+";'>"
						+ (pp.getMother().getGender().isFeminine()
								?Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularFemaleName(pp.getMother().getBody()))
								:Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularMaleName(pp.getMother().getBody())))
						+ "</span>)";
				
				contentSB.append(pregnancyRow("[style.boldBad(Беременность)]", "[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]", motherName, impregnationChance));
				
			}
			noPregnancies=false;
		}
		List<Litter> incubatorOngoingLitters = new ArrayList<>(Main.game.getPlayer().getLittersImplanted());
		incubatorOngoingLitters.removeIf(npc -> npc.getIncubator()==null || npc.getIncubator().getIncubatingLitters().isEmpty());
		Set<GameCharacter> incubatorCharacters = new HashSet<>();
		for(Litter litter : incubatorOngoingLitters) {
			incubatorCharacters.add(litter.getIncubator());
		}
		for(GameCharacter incubator : incubatorCharacters) {
			for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> incubationEntry : incubationEffectMap.entrySet()) {
				Litter litter = incubator.getIncubatingLitters().get(incubationEntry.getKey());
				if(litter!=null) {
					if(incubator.hasStatusEffect(incubationEntry.getValue().get(0))
							|| incubator.hasStatusEffect(incubationEntry.getValue().get(1))
							|| incubator.hasStatusEffect(incubationEntry.getValue().get(2))) {
						String stage = "";
						if(incubator.hasStatusEffect(incubationEntry.getValue().get(0))) {
							stage = incubationEntry.getValue().get(0).getName(incubator);
						} else if(incubator.hasStatusEffect(incubationEntry.getValue().get(1))) {
							stage = incubationEntry.getValue().get(1).getName(incubator);
						} else if(incubator.hasStatusEffect(incubationEntry.getValue().get(2))) {
							stage = incubationEntry.getValue().get(2).getName(incubator);
						}
						contentSB.append(pregnancyRow("[style.boldBad(В процессе)] [style.boldPurple(Инкубация)]",
								UtilText.parse(incubator, "Инкубатор: <span style='color:"+incubator.getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"),
								"В имплантировано в "
									+(incubationEntry.getKey()==SexAreaOrifice.VAGINA
										?"матку"
										:(incubationEntry.getKey()==SexAreaOrifice.ANUS || incubationEntry.getKey()==SexAreaOrifice.MOUTH
											?"живот"
											:incubationEntry.getKey().getName(incubator, true)))
									+": "
									+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG),
								"[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]"));
						noPregnancies=false;
					}
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersFathered().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersFathered()) {
				String unknownName = "[style.colourDisabled(Неизвестно)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getMotherName().equals("???")) {
							unknownName = "<span style='color:"+c.getMotherFemininity().getColour().toWebHexString()+";'>"+c.getMotherName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getMotherName().equals("???")) {
							unknownName = "<span style='color:"+o.getMotherFemininity().getColour().toWebHexString()+";'>"+o.getMotherName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				
				contentSB.append(pregnancyRow("[style.boldGood(Беременность разрешилась)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"Мать: [style.colourExcellent(Ты)]"
								:UtilText.parse(litter.getMother(), "Мать: <span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Мать: "+unknownName),
						"Зачатие: "
							+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+" | Рождено: "
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Родилось: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		if(!Main.game.getPlayer().getLittersImplanted().isEmpty()) {
			List<Litter> incubatorCompletedLitters = new ArrayList<>(Main.game.getPlayer().getLittersImplanted());
			incubatorCompletedLitters.removeAll(incubatorOngoingLitters);
			for (Litter litter : incubatorCompletedLitters) {
				String unknownName = "[style.colourDisabled(Неизвестно)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getIncubatorName().equals("???")) {
							unknownName = "<span style='color:"+c.getIncubatorFemininity().getColour().toWebHexString()+";'>"+c.getIncubatorName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getIncubatorName().equals("???")) {
							unknownName = "<span style='color:"+o.getIncubatorFemininity().getColour().toWebHexString()+";'>"+o.getIncubatorName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				
				contentSB.append(pregnancyRow("[style.boldGood(Разрешено)] [style.boldPurple(Инкубация)]",
						(litter.getIncubator()!=null
							?(litter.getIncubator().isPlayer()
								?"Инкубатор: [style.colourExcellent(Ты)]"
								:UtilText.parse(litter.getIncubator(), "Инкубатор: <span style='color:"+litter.getIncubator().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Инкубатор: "+unknownName),
						"Внедрено: "
							+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
							+" | Рождено: "
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Родилось: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Ты еще никого не обрюхатил!</span></div>");
		}
		

		return contentSB.toString();
	}
	
	private static String statHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'><div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>Характеристика</div><div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>Значение</div><div style='float:left; width:30%; font-weight:bold; margin:0; padding:0;'>Описание</div></div>";
	}

	private static String statRowHeader(Colour colour, String text) {
		return "<h6 style='color:"+colour.toWebHexString()+"; text-align:center; margin-bottom:0; padding-bottom:0;'>"+text+"</h6>";
	}
	
	private static int rowCount = 0;
	
	private static String statRow(String colourLeft, String left, Colour colourCentre, String centre, String colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"><div style='color:"+colourLeft+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ left
				+ "</div>"
				+ "<div style='color:"+colourCentre.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ centre
				+ "</div>"
				+ "<div style='color:"+colourRight+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ right
				+ "</div>"
			+ "</div>";
	}
	
	private static String statRow(Colour colourLeft, String left, Colour colourCentre, String centre, Colour colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"><div style='color:"+colourLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+(centre.equalsIgnoreCase("unknown") || centre.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourCentre).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ centre
					+ "</div>"
					+ "<div style='color:"+(right.equalsIgnoreCase("unknown") || right.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourRight).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String statRow(Colour colour, String text) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"><div style='color:"+colour.toWebHexString()+"; width:100%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
						+ text
					+ "</div>"
				+ "</div>";
	}

	private static String statAttributeHeader() {
		return "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'><b><div class='container-full-width' style='background:transparent;margin:0;padding:0;width:26%;'>Атрибут</div><div class='container-full-width' style='background:transparent;margin:0;padding:0;width:16%;'>Основной | Бонус</div><div class='container-full-width' style='background:transparent;margin:0;padding:0;width:20%;'>Значение/Макс.</div><div class='container-full-width' style='background:transparent;margin:0;padding:0;width:36%;'>Описание</div></b></div>";
	}
	
	private static String getAttributeBox(GameCharacter owner, AbstractAttribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}
	
	private static String getAttributeBox(GameCharacter owner, AbstractAttribute att, String effect, boolean half) {
		float value = owner.getAttributeValue(att);

		String valueForDisplay;
		if(((int)value)==value) {
			valueForDisplay = String.valueOf(((int)value));
		} else {
			valueForDisplay = String.valueOf(value);
		}
		if(att.isInfiniteAtUpperLimit() && value>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		if(att.isPercentage()){
			valueForDisplay = (value>=0?"+":"")+valueForDisplay+"%";
		}
		
		float bonusAttributeValue = owner.getBonusAttributeValue(att) + (att==Attribute.RESISTANCE_PHYSICAL?owner.getPhysicalResistanceAttributeFromClothingAndWeapons():0);
		
		return "<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'><div class='container-full-width' style='background:transparent;margin:0;padding:0;width:30%;'><b style='color:" + att.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(att.getName())+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:right;'>"
						+(owner.getBaseAttributeValue(att) > 0 
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (owner.getBaseAttributeValue(att) < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBaseAttributeValue(att), 0, 1)
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:left;'>"
						+" | "	
						+ (bonusAttributeValue > 0
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (bonusAttributeValue < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(bonusAttributeValue, 0, 1)
						+"</b>"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:right;'>"
						+ "<b style='color:"+att.getColour().toWebHexString()+";'>"
							+ valueForDisplay
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:left;'>"
						+ "/"
						+ (value>=att.getUpperLimit() || (att==Attribute.MAJOR_CORRUPTION && value==0)
								?"[style.boldGood("+att.getUpperLimit()+")]"
								:"[style.boldDisabled("+att.getUpperLimit()+")]")
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:40%;'>"
						+ "[style.italicsDisabled("+effect.replaceAll("<br/>", " ")+")]"
					+ "</div>"
					
				+ "</div>";
	}

	private static String getContactEntry(GameCharacter contact) {
		boolean isOffspring = contact.getMotherId().equals(Main.game.getPlayer().getId()) || contact.getFatherId().equals(Main.game.getPlayer().getId());
		
		return UtilText.parse(contact, "<b style='color:"+contact.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"+(isOffspring
				?(contact.isFeminine()
						?", твоя [style.colourFeminine(дочь)]"
						:", твой [style.colourMasculine(сын)]")
				:"")
				+". [npc.She] — это "
				+ (contact.getRaceStage()==RaceStage.HUMAN || contact.isRaceConcealed()
					?"[npc.a_race(true)]"
					:"[npc.a_raceStage(true)] [npc.race(true)]")
				+", который сейчас находится здесь: "
				+ (contact.getWorldLocation()==WorldType.EMPTY
					?"[style.italicsDisabled(Неизвестно!)]"
					:"<i>"+contact.getWorldLocation().getName()+", "+contact.getLocationPlace().getPlaceType().getName()+"</i>."));
	}
	
	public static final DialogueNode CONTACTS = new DialogueNode("Контакты", "Посмотреть контакты.", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for (int i = 0; i < charactersEncountered.size(); i++) {
				GameCharacter npc = charactersEncountered.get(i);
				
				UtilText.nodeContentSB.append("<p>"
												+ getContactEntry(npc)
											+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else if (index <= charactersEncountered.size()) {
				GameCharacter npc = charactersEncountered.get(index-1);
				boolean isOffspring = npc.getMotherId().equals(Main.game.getPlayer().getId()) || npc.getFatherId().equals(Main.game.getPlayer().getId());
				return new Response(
						UtilText.parse(npc, isOffspring
								?(npc.isFeminine()?"[style.colourFeminine([npc.Name])]":"[style.colourMasculine([npc.Name])]")
								:"[npc.Name]"),
						getContactEntry(npc),
						CONTACTS_CHARACTER){
					@Override
					public void effects() {
						CharactersPresentDialogue.resetContent(npc);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CONTACTS_CHARACTER = new DialogueNode("Контакты", "Посмотреть контакты.", true) {

		@Override
		public String getLabel() {
			return CharactersPresentDialogue.characterViewed.getName(true);
		}

		@Override
		public String getContent() {
			return CharactersPresentDialogue.menuContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				try {
					GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return new Response(Util.capitaliseSentence(npc.getName(true)),
							UtilText.parse(npc, "Внимательно посмотри на то, как выглядит [npc.name]."),
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);
							
						}
					};
				} catch (Exception e) {
					Util.logGetNpcByIdError("КОНТАКТЫ_ПЕРСОНАЖА.getResponse()", Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return null;
				}
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String getSubspeciesDiscoveredIndication() {
		return Main.getProperties().getSubspeciesDiscoveredCount()+"/"+Subspecies.getAllSubspecies().size();
	}

	private static String getWeaponsDiscoveredIndication() {
		int size = weaponsDiscoveredList.size();
		return Math.min(size, Main.getProperties().getWeaponsDiscoveredCount())+"/"+size;
	}
	
	private static String getClothingDiscoveredIndication() {
		int size = clothingDiscoveredList.size();
		return Math.min(size, Main.getProperties().getClothingDiscoveredCount())+"/"+size;
	}
	
	private static String getItemsDiscoveredIndication() {
		int size = itemsDiscoveredList.size();
		return Math.min(size, Main.getProperties().getItemsDiscoveredCount())+"/"+size;
	}
	
	private static int encyclopediaItemIndex = 0;
	
	public static final DialogueNode ENCYCLOPEDIA = new DialogueNode("Энциклопедия", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>Вы собрали сведения обо всех расах, оружии, одежде и предметах, которые встретили в своих странствиях.</p>");
			
			sb.append("<p style='text-align:center;'>");
				sb.append("Вы обнаружили:");
				sb.append(Main.getProperties().getSubspeciesDiscoveredCount()==Subspecies.getAllSubspecies().size()
								?"<br/>[style.colourGood(Subspecies: "+getSubspeciesDiscoveredIndication()+")]"
								:"<br/>Subspecies: "+getSubspeciesDiscoveredIndication());

				int size = weaponsDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getWeaponsDiscoveredCount())==weaponsDiscoveredList.size()
								?"<br/>[style.colourGood(Weapons: "+getWeaponsDiscoveredIndication()+")]"
								:"<br/>Weapons: "+getWeaponsDiscoveredIndication());
				
				size = clothingDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getClothingDiscoveredCount())==clothingDiscoveredList.size()
								?"<br/>[style.colourGood(Clothing: "+getClothingDiscoveredIndication()+")]"
								:"<br/>Clothing: "+getClothingDiscoveredIndication());
				
				size = itemsDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getItemsDiscoveredCount())==itemsDiscoveredList.size()
								?"<br/>[style.colourGood(Items: "+getItemsDiscoveredIndication()+")]"
								:"<br/>Items: "+getItemsDiscoveredIndication());
			sb.append("</p>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Расы</span>":"Расы",
						"Взгляните на все различные расы, которые вы встретили в своих странствиях.", RACES){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
					}
				};

			} else if (index == 2) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Оружие</span>":"Оружие",
						"Взгляните на все различные виды оружия, которые вы встретили в своих странствиях.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
					}
				};

			} else if (index == 3) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newClothingDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Одежда</span>":"Одежда",
						"Взгляните на всю различную одежду, которую вы встретили в своих странствиях.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						clothingSlotKey = clothingSlotCategories.keySet().iterator().next();
					}
				};

			} else if (index == 4) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newItemDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Предметы</span>":"Предметы",
						"Посмотрите на все различные предметы, которые вы встретили в своих путешествиях.", ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 0;
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
					}
				};

			} else if (index == 5) {
				if(!Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newRaceDiscovered)) {
					return new Response("Очистить уведомления", "Очищает уведомления энциклопедии.<br/><i>На данный момент у вас нет уведомлений в энциклопедии для очистки...</i>", null);
					
				} else {
					return new ResponseEffectsOnly("Очистить уведомления",
							"Очищает уведомления энциклопедии."){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<AbstractClothingType> clothingDiscoveredList = new ArrayList<>();
	private static List<AbstractWeaponType> weaponsDiscoveredList = new ArrayList<>();
	
	private static Map<String, List<InventorySlot>> clothingSlotCategories;
	private static String clothingSlotKey;
	
	/**
	 * @return A list of all clothing which is available to the player in a normal game. i.e. A list of all clothing excluding silly mode or cheat items.
	 */
	public static List<AbstractClothingType> getClothingDiscoveredList() {
		return clothingDiscoveredList;
	}

	/**
	 * @return A list of all weapons which are available to the player in a normal game. i.e. A list of all weapons excluding silly mode or cheat items.
	 */
	public static List<AbstractWeaponType> getWeaponsDiscoveredList() {
		return weaponsDiscoveredList;
	}
	
	static {
		itemsDiscoveredList.addAll(ItemType.getAllItems());
		itemsDiscoveredList.removeIf((it) -> it.getItemTags().contains(ItemTag.CHEAT_ITEM) || it.getItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(itemsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		weaponsDiscoveredList.addAll(WeaponType.getAllWeapons());
		weaponsDiscoveredList.removeIf((wt) -> wt.getItemTags().contains(ItemTag.CHEAT_ITEM) || wt.getItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(weaponsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.removeIf((ct) -> ct.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM) || ct.getDefaultItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(clothingDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingSlotCategories = new LinkedHashMap<>();
		
		clothingSlotCategories.put("Head",
				Util.newArrayListOfValues(
				InventorySlot.HEAD,
				InventorySlot.EYES,
				InventorySlot.HAIR,
				InventorySlot.MOUTH,
				InventorySlot.NECK));
		
		clothingSlotCategories.put("Torso",
				Util.newArrayListOfValues(
				InventorySlot.TORSO_OVER,
				InventorySlot.TORSO_UNDER));
		
		clothingSlotCategories.put("Chest",
				Util.newArrayListOfValues(
				InventorySlot.CHEST,
				InventorySlot.NIPPLE));
		
		clothingSlotCategories.put("Arms",
				Util.newArrayListOfValues(
				InventorySlot.HAND,
				InventorySlot.WRIST,
				InventorySlot.FINGER));
		
		clothingSlotCategories.put("Waist",
				Util.newArrayListOfValues(
				InventorySlot.STOMACH,
				InventorySlot.HIPS));
		
		clothingSlotCategories.put("Groin",
				Util.newArrayListOfValues(
				InventorySlot.GROIN,
				InventorySlot.PENIS,
				InventorySlot.VAGINA,
				InventorySlot.ANUS));
		
		clothingSlotCategories.put("Legs",
				Util.newArrayListOfValues(
				InventorySlot.LEG,
				InventorySlot.SOCK));
		
		clothingSlotCategories.put("Feet",
				Util.newArrayListOfValues(
				InventorySlot.FOOT,
				InventorySlot.ANKLE));
		
		clothingSlotCategories.put("Extra",
				Util.newArrayListOfValues(
				InventorySlot.HORNS,
				InventorySlot.WINGS,
				InventorySlot.TAIL));
		
		clothingSlotCategories.put("Piercings",
				Util.newArrayListOfValues(
				InventorySlot.PIERCING_EAR,
				InventorySlot.PIERCING_LIP,
				InventorySlot.PIERCING_NOSE,
				InventorySlot.PIERCING_TONGUE,
				InventorySlot.PIERCING_NIPPLE,
				InventorySlot.PIERCING_STOMACH,
				InventorySlot.PIERCING_PENIS,
				InventorySlot.PIERCING_VAGINA));
	}
	
	public static final DialogueNode WEAPON_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Открытое оружие ("+getWeaponsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbMelee = new StringBuilder();
			StringBuilder sbRanged = new StringBuilder();
			StringBuilder sbDamageTypes = new StringBuilder();

			int meleeCount = 0;
			int meleeKnownCount = 0;
			int rangedCount = 0;
			int rangedKnownCount = 0;
			
			for(AbstractWeaponType weaponType : weaponsDiscoveredList) {
				boolean discovered = Main.getProperties().isWeaponDiscovered(weaponType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+"; width:78%; margin:1%; padding:0; '><div class='inventory-icon-content'>"+(discovered?weaponType.getSVGImage():"")+"</div><div class='overlay"+(discovered?"' id='"+weaponType.getId()+"'":" отключенная-темная")+" style='cursor:default;'></div></div>";
				sbDamageTypes.setLength(0);
				if(discovered) {
					float width = weaponType.getAvailableDamageTypes().size()>4?(72/weaponType.getAvailableDamageTypes().size()):18;
					for(DamageType dt : weaponType.getAvailableDamageTypes()) {
						sbDamageTypes.append("<div class='square-button' "+(discovered?"id='"+(weaponType.getId()+"_"+dt.toString())+"'":"")
												+ " style='cursor:default; width:"+width+"%; margin:1%; padding:0; background-color:"+dt.getMultiplierAttribute().getColour().toWebHexString()+";'>"
											+ "</div>");
					}
				}
				if(weaponType.isMelee()) {
					meleeCount++;
					if(discovered) {
						meleeKnownCount++;
					}
					sbMelee.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbMelee.append(entry);
						sbMelee.append(sbDamageTypes.toString());
					sbMelee.append("</div>");
					
				} else {
					rangedCount++;
					if(discovered) {
						rangedKnownCount++;
					}
					sbRanged.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbRanged.append(entry);
						sbRanged.append(sbDamageTypes.toString());
					sbRanged.append("</div>");
				}
			}
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldBlue(Холодное оружие ("+meleeKnownCount+"/"+meleeCount+"))]");
				sb.append("</p>");
				sb.append(sbMelee.toString());
			sb.append("</div>");
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldYellow(Дальнобойное оружие ("+rangedKnownCount+"/"+rangedCount+"))]");
				sb.append("</p>");
				sb.append(sbRanged.toString());
			sb.append("</div>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться к энциклопедии.", ENCYCLOPEDIA);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CLOTHING_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Открытая одежда ("+getClothingDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<InventorySlot> slots = clothingSlotCategories.get(clothingSlotKey);
			Map<InventorySlot, StringBuilder> sbMap = new LinkedHashMap<>();
			Map<InventorySlot, Value<Integer, Integer>> discoveredMap = new HashMap<>();
			for(InventorySlot slot : slots) {
				sbMap.put(slot, new StringBuilder());
				discoveredMap.put(slot, new Value<>(0, 0));
			}
			
			for(AbstractClothingType clothingType : clothingDiscoveredList) {
				if(Collections.disjoint(clothingType.getEquipSlots(), slots)) {
					continue;
				}
				boolean discovered = Main.getProperties().isClothingDiscovered(clothingType);
				
				for(InventorySlot slot : clothingType.getEquipSlots()) {
					if(slots.contains(slot)) {
						String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'><div class='inventory-icon-content'>"+(discovered?clothingType.getSVGImageRandomColour(slot, true, false, false):"")+"</div><div class='overlay"+(discovered?"' id='"+clothingType.getId()+"_"+slot.toString()+"'":" отключенная-темная")+" style='cursor:default;'></div></div>";
						sbMap.get(slot).append(entry);
						discoveredMap.put(slot, new Value<>(discoveredMap.get(slot).getKey()+(discovered?1:0), discoveredMap.get(slot).getValue()+1));
					}
				}
			}

			for(InventorySlot slot : slots) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldYellowLight("+Util.capitaliseSentence(slot.getName())+" ("+discoveredMap.get(slot).getKey()+"/"+discoveredMap.get(slot).getValue()+"))]");
					sb.append("</p>");
					sb.append(sbMap.get(slot).toString());
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться к энциклопедии.", ENCYCLOPEDIA);
			
			} else {
				List<Response> responses = new ArrayList<>();
				for(Entry<String, List<InventorySlot>> entry : clothingSlotCategories.entrySet()) {
					if(clothingSlotKey==entry.getKey()) {
						responses.add(new Response(entry.getKey(), "Вы уже просматриваете эту категорию!", null));
						
					} else {
						responses.add(new Response(entry.getKey(), "Посмотреть всю одежду, подходящую для следующих слотов:<br/><i>"+Util.capitaliseSentence(Util.inventorySlotsToStringList(entry.getValue()))+".</i>", CLOTHING_CATALOGUE) {
							@Override
							public void effects() {
								clothingSlotKey = entry.getKey();
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode ITEM_CATALOGUE = new DialogueNode("", "Посмотреть открытые предметы", true) {
		@Override
		public String getLabel() {
			return "Открытые предметы ("+getItemsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbItems = new StringBuilder();
			StringBuilder sbBooks = new StringBuilder();
			StringBuilder sbEssences = new StringBuilder();
			StringBuilder sbSpells = new StringBuilder();
			
			int itemCount = 0;
			int itemKnownCount = 0;
			int bookCount = 0;
			int bookKnownCount = 0;
			int essenceCount = 0;
			int essenceKnownCount = 0;
			int spellCount = 0;
			int spellKnownCount = 0;
			
			for(AbstractItemType itemType : itemsDiscoveredList) {
				boolean discovered = Main.getProperties().isItemDiscovered(itemType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'><div class='inventory-icon-content'>"+(discovered?itemType.getSVGString():"")+"</div><div class='overlay"+(discovered?"' id='"+itemType.getId()+"'":" отключенная-темная")+" style='cursor:default;'></div></div>";
				
				if(itemType.getItemTags().contains(ItemTag.BOOK)) {
					sbBooks.append(entry);
					bookCount++;
					if(discovered) {
						bookKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.ESSENCE)) {
					sbEssences.append(entry);
					essenceCount++;
					if(discovered) {
						essenceKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.SPELL_BOOK) || itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)) {
					sbSpells.append(entry);
					spellCount++;
					if(discovered) {
						spellKnownCount++;
					}
					
				} else {
					sbItems.append(entry);
					itemCount++;
					if(discovered) {
						itemKnownCount++;
					}
				}
			}
			
			if(encyclopediaItemIndex==0) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldBlueLight(Предметы ("+itemKnownCount+"/"+itemCount+"))]");
					sb.append("</p>");
					sb.append(sbItems.toString());
				sb.append("</div>");
			}

			if(encyclopediaItemIndex==1) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldOrange(Книги ("+bookKnownCount+"/"+bookCount+"))]");
					sb.append("</p>");
					sb.append(sbBooks.toString());
				sb.append("</div>");
				
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldArcane(Эссенции ("+essenceKnownCount+"/"+essenceCount+"))]");
					sb.append("</p>");
					sb.append(sbEssences.toString());
				sb.append("</div>");
			}

			if(encyclopediaItemIndex==2) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldSpells(Заклинания ("+spellKnownCount+"/"+spellCount+"))]");
					sb.append("</p>");
					sb.append(sbSpells.toString());
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Предметы",
						encyclopediaItemIndex==0
							?"Вы уже просматриваете все найденные предметы..."
							:"Просмотреть все найденные предметы.",
						encyclopediaItemIndex==0
							?null
							:ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 0;
					}
				};
				
			} else if(index==2) {
				return new Response("Книги и эссенции",
						encyclopediaItemIndex==1
							?"Вы уже просматриваете все найденные расовые книги и эссенции..."
							:"Просмотреть все найденные расовые книги и эссенции.",
						encyclopediaItemIndex==1
							?null
							:ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 1;
					}
				};
				
			} else if(index==3) {
				return new Response("Книги заклинаний",
						encyclopediaItemIndex==2
							?"Вы уже просматриваете все найденные вами книги заклинаний..."
							:"Просмотреть все найденные вами книги заклинаний.",
						encyclopediaItemIndex==2
							?null
							:ITEM_CATALOGUE){
					@Override
					public void effects() {
						encyclopediaItemIndex = 2;
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к энциклопедии.", ENCYCLOPEDIA);
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractRace> racesDiscovered = new ArrayList<>();
	private static List<AbstractSubspecies> subspeciesDiscovered = new ArrayList<>();
	private static AbstractRace raceSelected;
	private static AbstractSubspecies subspeciesSelected;
	private static Body bodyForSubspeciesSelected;
	private static StringBuilder subspeciesSB = new StringBuilder();
	
	public static void resetContentForRaces() {
		
		subspeciesDiscovered.clear();
		
		for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			if(Main.getProperties().isRaceDiscovered(subspecies)) {
				AbstractRace race = subspecies.getRace();
				if(!racesDiscovered.contains(race)) {
					racesDiscovered.add(race);
				}
				subspeciesDiscovered.add(subspecies);
			}
		}
		
		racesDiscovered.sort((a, b) -> a.getName(false).compareTo(b.getName(false)));
		subspeciesDiscovered.sort((a, b) -> a.getName(null).compareTo(b.getName(null)));
		
	}

	public static final DialogueNode RACES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Открытые расы ("+getSubspeciesDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			// Race (X/Y), book icon if advanced knowledge found
			
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>В своих странствиях вы встретили следующие расы:<br/>Количество открытых подвидов для каждой расы указано в скобках.<br/>Если вы разблокировали расширенные знания о расе, рядом будет отображаться её иконка.</p>");
			List<AbstractRace> sortedRaces = new ArrayList<>();
			sortedRaces.addAll(Race.getAllRaces());
			sortedRaces.remove(Race.NONE);
			sortedRaces.sort((r1, r2) -> r1.getName(false).compareTo(r2.getName(false)));
			int unknownRaces=0;
			for(AbstractRace race : sortedRaces) {
				int discoveredSubspecies = 0;
				int totalSubspecies = 0;
				boolean fullKnowledge = true;
				for(AbstractSubspecies subspecies : Subspecies.getSubspeciesOfRace(race)) {
					if(subspeciesDiscovered.contains(subspecies)) {
						discoveredSubspecies++;
					}
					if(!Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspecies)) {
						fullKnowledge = false;
					}
					totalSubspecies++;
				}
				if(discoveredSubspecies==0) {
					unknownRaces++;
					continue;
				}
				String icon = 
						"<div class='inventory-item-slot' style='width:10%; margin:0; "+(!fullKnowledge?"opacity:0.25;":"")+" pointer-events:none;'>"
							+(fullKnowledge
								?AbstractSubspecies.getMainSubspeciesOfRace(race).getSVGString(null)
								:"")//"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown()+"</div>")
						+"</div>";
				
				String discoveredInfo =
						"<div style='float:left; width:20%; margin:0; text-align:right;'>"
							+(discoveredSubspecies==0
								?"[style.colourDisabled((?/?))]"
								:(discoveredSubspecies==totalSubspecies
									?"[style.colourGood("
									:"")
										+"("+discoveredSubspecies+"/"+totalSubspecies+")"
								+ (discoveredSubspecies==totalSubspecies?")]":""))
						+ "</div>";
				//position:relative; width:"+(Util.random.nextInt(23)+10)+"%; padding:2px; margin:0.5%; float:left;filter:blur(1px) grayscale(0.8); font-size:12px; transform: rotate("+(-20+Util.random.nextInt(41))+"deg);'>
				UtilText.nodeContentSB.append("<div class='container-full-width' style='position:relative; width:32%; padding:2px; margin:0.5%; float:left;'>");
					UtilText.nodeContentSB.append("<div class='overlay' id='ENCYCLOPEDIA_RACE_"+Race.getIdFromRace(race)+"'></div>");
					UtilText.nodeContentSB.append(icon);
					UtilText.nodeContentSB.append("<div style='float:left; text-align:center; width:70%; margin:0;'>");
							if(discoveredSubspecies==0) {
								UtilText.nodeContentSB.append("[style.colourDisabled(???)]");
							} else if(racesDiscovered.contains(race)) {
								UtilText.nodeContentSB.append("<span style='color:"+race.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(race.getName(false)) + "</span>");
							} else {
								UtilText.nodeContentSB.append("[style.colourDisabled(" + Util.capitaliseSentence(race.getName(false))+")]");
							}
						UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append(discoveredInfo);
				UtilText.nodeContentSB.append("</div>");
			}
			for(int i=0; i<unknownRaces;i++) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='position:relative; width:32%; padding:2px; margin:0.5%; float:left;'>");
					UtilText.nodeContentSB.append("<div class='inventory-item-slot' style='width:10%; margin:0; opacity:0.2;'></div>");
					UtilText.nodeContentSB.append("<div style='float:left; text-align:center; width:70%; margin:0;'>");
								UtilText.nodeContentSB.append("[style.colourDisabled(???)]");
						UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='float:left; width:20%; margin:0; text-align:right;'>[style.colourDisabled((?/?))]</div>");
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться к энциклопедии.", ENCYCLOPEDIA);
			
			} else if (index <= racesDiscovered.size()) {
				return new Response(Util.capitaliseSentence(racesDiscovered.get(index - 1).getName(false)),
						"Посмотреть все подвиды расы: '" + racesDiscovered.get(index - 1).getName(false) + "'",
						SUBSPECIES){
					@Override
					public void effects() {
						applyRaceSelection(racesDiscovered.get(index - 1));
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static void applyRaceSelection(AbstractRace race) {
		raceSelected = race;
		subspeciesSelected = AbstractSubspecies.getMainSubspeciesOfRace(raceSelected);
		if(!subspeciesDiscovered.contains(subspeciesSelected)) {
			for(AbstractSubspecies sub : subspeciesDiscovered) {
				if(sub.getRace()==raceSelected) {
					subspeciesSelected = sub;
					break;
				}
			}
		}
		bodyForSubspeciesSelected = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
	}
	
	private static List<String> getSubspeciesModifiersAsStringList(AbstractSubspecies subspecies) {
		LinkedHashMap<AbstractAttribute, Float> attMods;

		attMods = new LinkedHashMap<>(subspecies.getStatusEffectAttributeModifiers(null));
		
		ArrayList<String> fullModList = new ArrayList<>(getSubspeciesAttributeModifiersToStringList(attMods));
		fullModList.addAll(subspecies.getExtraEffects(null));
		
		if(subspecies.isFeralConfigurationAvailable(null)) {
			fullModList.add("<br/><b>Дополнительные бонусы в [style.boldFeral(дикой форме)]:</b>");
			
			for(String s : subspecies.getFeralEffects()) {
				fullModList.add(s);
			}
		}
		
		return fullModList;
	}

	private static List<String> getSubspeciesAttributeModifiersToStringList(Map<AbstractAttribute, Float> attributeMap) {
		List<String> attributeModifiersList = new ArrayList<>();
		if (attributeMap != null) {
			for (Entry<AbstractAttribute, Float> e : attributeMap.entrySet()) {
				attributeModifiersList.add(e.getKey().getFormattedValue(e.getValue()));
			}
		}
		return attributeModifiersList;
	}
	
	public static final DialogueNode SUBSPECIES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(subspeciesSelected.getName(null));
		}
		@Override
		public String getContent() {
			subspeciesSB.setLength(0);
			
			Body femaleBody = Main.game.getCharacterUtils().generateBody(null, Gender.F_V_B_FEMALE, subspeciesSelected, RaceStage.GREATER);
			Body maleBody = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
			
			subspeciesSB.append(
				"<div class='container-full-width' style='width:40%; float:right;'>"
					+ "<p style='width:100%; text-align:center;'>"
						+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b><br/>Средние показатели</p><table align='center'><tr><td></td><td>[style.colourFeminine("+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+")]</td>"
							+ "<td>[style.colourMasculine("+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+")]</td></tr><tr><td>Рост</td><td>"+Units.size(femaleBody.getHeightValue())+"</td>"
							+ "<td>"+Units.size(maleBody.getHeightValue())+"</td></tr><tr><td>Женственность</td><td>"+femaleBody.getFemininity()+"</td>"
							+ "<td>"+maleBody.getFemininity()+"</td></tr><tr><td>Размер груди</td><td>"+(femaleBody.getBreast().getRawSizeValue()==0
										?"Плоская"
										:femaleBody.getBreast().getSize().getCupSizeName()+"-го размера")+"</td>"
							+ "<td>"+(maleBody.getBreast().getRawSizeValue()==0
										?"Плоская"
										:maleBody.getBreast().getSize().getCupSizeName()+"-го размера")+"</td></tr><tr><td>Длина члена</td><td>-</td><td>"+Units.size(maleBody.getPenis().getRawLengthValue())+"</td></tr><tr><td>Диаметр члена</td><td>-</td><td>"+Units.size(maleBody.getPenis().getRawGirthValue())+"</td></tr><tr><td>Вместимость влагалища</td><td>"+Util.capitaliseSentence(femaleBody.getVagina().getOrificeVagina().getCapacity().getDescriptor())+"</td><td>-</td></tr><tr><td>Размер помета</td><td>"+subspeciesSelected.getRace().getNumberOfOffspringLow()+"-"+subspeciesSelected.getRace().getNumberOfOffspringHigh()+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
					+ "</table>"
					+ "<hr/>"
					+ "<p style='width:100%; text-align:center;'>"
						+ "Nocturnality: "+Util.capitaliseSentence(subspeciesSelected.getNocturnality().getName())
						+ "<br/>"
						+ "Aquatic: "+(subspeciesSelected.isAquatic()?"Да":"Нет")
						+ "<br/>"
						+ "Leg configuration: "+Util.capitaliseSentence(bodyForSubspeciesSelected.getLegConfiguration().getName())
						+ "<br/>"
						+ "Short stature: "+(subspeciesSelected.isShortStature()?"Да":"Нет")
					+"</p>"
				+ "</div>");
					
			subspeciesSB.append("<p>"
					+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b>"
					+ (AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==subspeciesSelected
							?" ([style.colourMinorGood(Подвид)] <span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>)"
							:" (Подвид <span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>)")
					+ "<br/>"
					+ "Masculine: <span style='color:"+Femininity.valueOf(maleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+"</span>"
					+ "<br/>"
					+ "Feminine: <span style='color:"+Femininity.valueOf(femaleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+"</span>");

			subspeciesSB.append("<br/><br/><b>Бонусы расы:</b>");
			if(Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)) {
				for (String s : getSubspeciesModifiersAsStringList(subspeciesSelected)) {
					subspeciesSB.append("<br/>");
					subspeciesSB.append(s);
				}
			} else {
				subspeciesSB.append("<br/>");
				subspeciesSB.append("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>");
					subspeciesSB.append("Бонусы расы можно найти в книгах!");
				subspeciesSB.append("</span>");
			}
			
			subspeciesSB.append("<br/><br/>"
					+ "<i>"+subspeciesSelected.getDescription(null)+"</i>"
				+ "</p>");
					
			subspeciesSB.append("<h6>"+Util.capitaliseSentence(raceSelected.getName(false))+" Лор</h6>"
					+subspeciesSelected.getBasicDescription(null)
					+ (Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)
						?subspeciesSelected.getAdvancedDescription(null)
						:"<p style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Дополнительную информацию можно найти в книгах!</p>"));
			
			return subspeciesSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> raceSubspecies = Subspecies.getSubspeciesOfRace(raceSelected);
			
			if (index == 0) {
				return new Response("Назад", "Вернуться к экрану выбора расы.", RACES);
			
			} else if (index <= raceSubspecies.size()) {
				AbstractSubspecies indexSubspecies = raceSubspecies.get(index - 1);
				if(!subspeciesDiscovered.contains(indexSubspecies)) {
					return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
							"Вы еще не открыли этот подвид!",
							null);
				}
				return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
						"Взглянуть поближе на то, " + indexSubspecies.getNamePlural(null) + " как они выглядят."
						+ (AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies
							?"<br/>Это [style.colourMinorGood(основная)] "+raceSelected.getName(false)+" подгруппа."
							:""),
						SUBSPECIES){
					@Override
					public Colour getHighlightColour() {
						if(AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies) {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						subspeciesSelected = indexSubspecies;
						bodyForSubspeciesSelected = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_PERK_TREE = new DialogueNode("Древо навыков", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details><summary>[style.boldPerk(Информация о Перках и Чертах)]</summary>[style.colourPerk(Перки)] (круглые иконки) дают постоянные бонусы к вашим характеристикам.<br/>[style.colourPerk(Черты)] (квадратные иконки) обеспечивают уникальные эффекты для вашего персонажа. В отличие от перков, <b>черты не будут действовать на вашего персонажа, пока вы не поместите их в панель «Активные черты»</b>.<br/>Для разблокировки перков требуются очки перков. Вы получаете одно очко перка при каждом повышении уровня, и дополнительные два очка перка каждые пять уровней.<br/><br/>В дополнение к перкам, которые можно купить за очки перков, существуют также несколько специальных скрытых перков, открываемых через особые события.</details>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(Main.game.getPlayer(), true));
			UtilText.nodeContentSB.append("</div>");
			
			if(!Main.game.getPlayer().isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>[style.italicsBad(Пожалуйста, учтите, что это древо перков находится в разработке. Это не финальная версия, а лишь проверка концепции!)]</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isDoll()) {
					return new Response("Сброс", "Вы не можете сбросить свои перки и черты, пока вы кукла!", null);
				}
				return new Response("Сброс", "Сбросить все перки и черты, вернув все потраченные очки. (Это временная мера, так как древо перков всё еще находится в разработке.)", CHARACTER_PERK_TREE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetPerksMap(false, false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
//	private static boolean confirmReset = false;
	public static final DialogueNode CHARACTER_FETISHES = new DialogueNode("Желания и Фетиши", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<details><summary>[style.boldFetish(Информация о фетишах)]</summary>Вы можете [style.colourArcane(бесплатно)] выбрать свое [style.colourLust(желание)] для каждого фетиша или же решить взять соответствующий [style.colourFetish(фетиш)] за [style.colourArcane(арканные эссенции)].<br/><br/>Выбор желания повлияет на бонусный прирост похоти во время секса, в то время как получение фетиша навсегда закрепит ваше желание как «любовь» и даст особые бонусы. Фетиши можно снять только с помощью зачарованных зелий.<br/><br/>Ваше текущее выбранное желание имеет "+PresetColour.FETISH.getName()+" рамку, но ваше истинное желание (обозначенное цветной иконкой желания) может быть изменено зачатой одеждой или другими предметами.<br/><br/>Вы получаете опыт для каждого фетиша, совершая соответствующие действия во время секса. Опыт начисляется независимо от того, есть ли у вас соответствующий фетиш или нет. Фетиши более высокого уровня заставляют и вас, и вашего партнера получать больше возбуждения от связанных сексуальных действий, а также увеличивают бонусы самого фетиша.<br/><br/>Наконец, производные фетиши нельзя разблокировать напрямую — они применяются автоматически, когда вы соответствуете их требованиям.</details>");
			
			// Normal fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Фетиши</h6></div>");
			ArrayList<AbstractFetish> derivedFetishList = new ArrayList<>();
			ArrayList<AbstractFetish> pairedFetishList = new ArrayList<>();
			ArrayList<AbstractFetish> soloFetishList = new ArrayList<>();
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(fetish.isContentEnabled()) {
					if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
						derivedFetishList.add(fetish);
					} else if(fetish.getOpposite()!=null) {
						pairedFetishList.add(fetish);
					} else {
						soloFetishList.add(fetish);
					}
				}
			}
			while (pairedFetishList.size() > 0) {
				AbstractFetish fetish = pairedFetishList.remove(0);
				if(fetish!=null) {
					pairedFetishList.remove(fetish.getOpposite());
					if (fetish.isTopFetish()) {
						journalSB.append(getFetishEntry(Main.game.getPlayer(), fetish, fetish.getOpposite()));
					} else {
						journalSB.append(getFetishEntry(Main.game.getPlayer(), fetish.getOpposite(), fetish));
					}
				}
			}
			while (soloFetishList.size() > 0) {
				AbstractFetish fetish = soloFetishList.remove(0);
				AbstractFetish fetish2 = null;
				if(soloFetishList.size() > 0) {
					fetish2 = soloFetishList.remove(0);
				}
				journalSB.append(getFetishEntry(Main.game.getPlayer(), fetish, fetish2));
			}
			
			// Derived fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Производные фетиши</h6></div>");
			journalSB.append("<div class='fetish-container'>");
			
			for(AbstractFetish fetish : derivedFetishList) {
				journalSB.append(
						"<div id='FETISH_" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
						? " owned' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
						: (fetish.isAvailable(Main.game.getPlayer())
								? " unlocked' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
								: " locked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
						+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
						+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
								? ""
								: (fetish.isAvailable(Main.game.getPlayer())
										? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); border-radius:5px;'></div>"
										: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.7); border-radius:5px;'></div>"))
						+ "</div>");
			}
			
			// Free Fetishes:
			
			journalSB.append("</div>");
			
			
			return journalSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
	public static String getFetishEntry(GameCharacter targetedCharacter, AbstractFetish othersFetish, AbstractFetish selfFetish) {
		return "<div class='container-full-width' style='background:transparent; margin:2px 0; width:100%;'>"
				+getIndividualFetishEntry(targetedCharacter, othersFetish)
				+(selfFetish == null?"":getIndividualFetishEntry(targetedCharacter, selfFetish))
				+"</div>";
	}
	
	private static String getIndividualFetishEntry(GameCharacter targetedCharacter, AbstractFetish fetish) {
		FetishLevel level = FetishLevel.getFetishLevelFromValue(targetedCharacter.getFetishExperience(fetish));
		float experiencePercentage = ((targetedCharacter.getFetishExperience(fetish)) / (float)(level.getMaximumExperience()))*100;
		
		return "<div class='container-half-width' style='margin:0 8px;'>"
					+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
						+ (targetedCharacter.hasFetish(fetish)
								?"[style.colourPink("+Util.capitaliseSentence(fetish.getName(targetedCharacter))+" "+level.getNumeral()+")]"
								:Util.capitaliseSentence(fetish.getName(targetedCharacter))+" "+level.getNumeral())
						+"<div class='container-full-width' style='margin:2px 0; padding:0; width:100%;'></div>" // Spacer
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.ZERO_HATE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.ONE_DISLIKE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.TWO_NEUTRAL)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.THREE_LIKE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.FOUR_LOVE)
					+ "</div><div class='container-full-width' style='margin:0 8px; width: calc(22% - 16px);'><div id='FETISH_" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon full" + (targetedCharacter.hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.toWebHexString() + ";'>"
							: (fetish.isAvailable(targetedCharacter)
									? " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY_DARK.toWebHexString() + ";'>"))
										+ "<div class='fetish-icon-content'>"+fetish.getSVGString(targetedCharacter)+"</div>"
										+ "<div style='width:40%;height:40%;position:absolute;top:0;right:4px;'>"+level.getSVGImageOverlay()+"</div>"
										+ (targetedCharacter.hasFetish(fetish) // Overlay to create disabled effect:
											? ""
											: (fetish.isAvailable(targetedCharacter)
													? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); border-radius:5px;'></div>"
													: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.7); border-radius:5px;'></div>"))
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width' style='margin:0; padding:0; width:100%;'>"
						+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
							+"<div class='container-full-width' style='margin:4px 0; padding:2px; width:100%; background:#222;'>"
								+ "<div class='container-full-width' style='margin:0; padding:2px; width:" + experiencePercentage + "%; background:"+level.getColour().toWebHexString()+";'></div></div></div><div class='container-full-width' style='text-align:center; margin:0 8px; width: calc(22% - 16px);'><span style='color:"+level.getColour().toWebHexString()+";'>"+targetedCharacter.getFetishExperience(fetish)+" опыт</span></div></div></div>";
	}
	
	private static String getFetishDesireEntry(GameCharacter targetedCharacter, AbstractFetish fetish, FetishDesire desire) {
		boolean disabled = desire!=FetishDesire.FOUR_LOVE && targetedCharacter.hasFetish(fetish);
		
		return "<div class='square-button"+(disabled?" disabled":"")+"' id='"+Fetish.getIdFromFetish(fetish)+"_"+desire+"' style='"+(targetedCharacter.getBaseFetishDesire(fetish)==desire
								?"border:2px solid "+PresetColour.FETISH.getShades()[1]+";"
								:"")+"width:10%; margin:0 5%; float:left; cursor:pointer;'><div class='square-button-content'>"+(targetedCharacter.getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (targetedCharacter.hasFetish(fetish) && targetedCharacter.getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); border-radius:5px;'></div>"
					:targetedCharacter.getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.6); border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
	
	public static AbstractWorldType worldTypeMap = WorldType.DOMINION;

	private static void setMapResponseTabToCurrentMap() {
		AbstractWorldType world = Main.game.getPlayer().getWorldLocation();
		if(world.getWorldRegion()==WorldRegion.SUBMISSION) {
			Main.game.setResponseTab(1);
		} else if(world.getWorldRegion()==WorldRegion.FIELD_CITY) {
			Main.game.setResponseTab(2);
		} else {
			Main.game.setResponseTab(0);
		}
	}
	
	public static final DialogueNode MAP = new DialogueNode("Карты", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getCurrentDialogueNode()!=MAP) {
				setMapResponseTabToCurrentMap();
			}
		}
		@Override
		public String getLabel() {
			return "Карта: "+Util.capitaliseSentence(worldTypeMap.getName());
		}
		@Override
		public String getContent() {
			if(worldTypeMap==WorldType.WORLD_MAP) {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, false);
			} else {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, true);
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Dominion";
			} else if(index==1) {
				return "Покорность";
			} else if(index==2) {
				return "Поля Фолой";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int i=2;
			List<AbstractWorldType> worldTypes = new ArrayList<>(Main.getProperties().hasValue(PropertyValue.mapReveal)?WorldType.getAllWorldTypes():Main.game.getPlayer().getWorldsVisited());
			
			worldTypes.sort((w1, w2) -> (w1.getName().compareTo(w2.getName())));
			
			worldTypes.sort((w1, w2) -> w1.getMajorAreaIndex()-w2.getMajorAreaIndex());
			
			for(AbstractWorldType world : worldTypes) {
				boolean correctRegion = false;
				if(world.getWorldRegion()==WorldRegion.SUBMISSION) {
					correctRegion = responseTab==1;
				} else if(world.getWorldRegion()==WorldRegion.FIELD_CITY || world.getWorldRegion()==WorldRegion.FIELDS || world.getWorldRegion()==WorldRegion.YOUKO_FOREST) {
					correctRegion = responseTab==2;
				} else {
					correctRegion = responseTab==0;
				}
				
				if(correctRegion
						&& world != WorldType.WORLD_MAP
						&& (world != WorldType.EMPTY || Main.game.isDebugMode())
						&& world != WorldType.MUSEUM
						&& world != WorldType.MUSEUM_LOST) {
					if(index==i) {
						boolean playerPresent = Main.game.getPlayer().getWorldLocation()==world;
						String responseTitle = (world.isMajorArea()?"<b>":"")+Util.capitaliseSentence(world.getName())+(world.isMajorArea()?"</b>":"");
//						String responseTitle = Util.capitaliseSentence(world.getName());
						
						if(worldTypeMap==world) {
							return new Response(responseTitle, "Вы уже просматриваете карту "+world.getName()+"."+(playerPresent?"<br/>[style.colourGood(Вы сейчас находитесь в этой области!)]":""), null);
							
						} else if(Main.game.getPlayer().getWorldsVisited().contains(world) || Main.getProperties().hasValue(PropertyValue.mapReveal)) { 
							return new Response(responseTitle, "Посмотреть карту "+world.getName()+"."+(playerPresent?"<br/>[style.colourGood(Вы сейчас находитесь в этой области!)]":""), MAP) {
								@Override
								public Colour getHighlightColour() {
									if(playerPresent) {
										return PresetColour.GENERIC_GOOD;
									}
									if(world==WorldType.EMPTY) {
										return PresetColour.GENERIC_BAD;
									}
									return super.getHighlightColour();
								}
								@Override
								public void effects() {
									Pathing.initPathingVariables();
									worldTypeMap = world;
								}
							};
							
						} else {
							return new Response("???", "Вы еще не открыли эту область.", null);
						}
					}
					i++;
				}
			}
			if (index == 1) {
				boolean playerPresent = Main.game.getPlayer().getWorldLocation()==WorldType.WORLD_MAP;
				if(worldTypeMap==WorldType.WORLD_MAP) {
					return new Response("Карта мира", "Вы уже смотрите на карту мира."+(playerPresent?"<br/>[style.colourGood(Вы сейчас находитесь в этой области!)]":""), null);
					
				} else if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new Response("Карта мира", "Взглянуть на карту мира."+(playerPresent?"<br/>[style.colourGood(Вы сейчас находитесь в этой области!)]":""), MAP) {
						@Override
						public Colour getHighlightColour() {
							if(playerPresent) {
								return PresetColour.GENERIC_GOOD;
							}
							return super.getHighlightColour();
						}
						@Override
						public void effects() {
							Pathing.initPathingVariables();
							worldTypeMap = WorldType.WORLD_MAP;
						}
					};
				} else {
					return new Response("Карта мира", "Вы еще не открыли карту мира!", null);
				}
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню телефона.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode LOITER_SELECTION = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return "<p>Вы раздумываете о том, сколько времени хотели бы провести, слоняясь по этой местности...</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Решить не задерживаться здесь.", MENU);
			}
			
			if(index == 1) {
				return new ResponseEffectsOnly("5 минут", "Послоняться здесь следующие пять минут.") {
					@Override
					public void effects() {
						loiter(5);
					}
				};
				
			} else if(index == 2) {
				return new ResponseEffectsOnly("15 минут", "Послоняться здесь следующие пятнадцать минут.") {
					@Override
					public void effects() {
						loiter(15);
					}
				};
				
			} else if(index == 3) {
				return new ResponseEffectsOnly("30 минут", "Послоняться здесь следующие тридцать минут.") {
					@Override
					public void effects() {
						loiter(30);
					}
				};
				
			} else if(index == 4) {
				return new ResponseEffectsOnly("1 час", "Послоняйся здесь в течение следующего часа.") {
					@Override
					public void effects() {
						loiter(60);
					}
				};
				
			} else if(index == 5) {
				return new ResponseEffectsOnly("2 часа", "Послоняйся здесь в течение следующих двух часов.") {
					@Override
					public void effects() {
						loiter(60*2);
					}
				};
				
			} else if(index == 6) {
				return new ResponseEffectsOnly("4 часа", "Послоняйся здесь в течение следующих четырех часов.") {
					@Override
					public void effects() {
						loiter(60*4);
					}
				};
				
			} else if(index == 7) {
				return new ResponseEffectsOnly("6 часов", "Послоняйся здесь в течение следующих шести часов.") {
					@Override
					public void effects() {
						loiter(60*6);
					}
				};
				
			} else if(index == 8) {
				return new ResponseEffectsOnly("8 часов", "Послоняйся здесь в течение следующих восьми часов.") {
					@Override
					public void effects() {
						loiter(60*8);
					}
				};
				
			} else if(index == 9) {
				return new ResponseEffectsOnly("12 часов", "Скитаться в этой области следующие двенадцать часов.") {
					@Override
					public void effects() {
						loiter(60*12);
					}
				};
				
			} else if(index == 10) {
				return new ResponseEffectsOnly("24 часа", "Скитаться в этой области следующие двадцать четыре часа.") {
					@Override
					public void effects() {
						loiter(60*24);
					}
				};
				
			} else if(index==11) {
				int timeUntilChange = Main.game.getMinutesUntilNextSunrise() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always loitering to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new ResponseEffectsOnly("Следующий рассвет",
						"Скитаться в этой области в течение " + (timeUntilChange >= 60 ?timeUntilChange / 60 + " часов " : " ")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " минут" : "")
							+ " до пяти минут после рассвета ("+Units.time(sunriseSunset[0].plusMinutes(5))+")."){
					@Override
					public void effects() {
						loiter(timeUntilChange);
					}
				};
				
			} else if(index==12) {
				int timeUntilChange = Main.game.getMinutesUntilNextSunset() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always loitering to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new ResponseEffectsOnly("Следующий закат",
						"Скитаться в этой области в течение " + (timeUntilChange >= 60 ?timeUntilChange / 60 + " часов " : " ")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " минут" : "")
							+ " до пяти минут после рассвета ("+Units.time(sunriseSunset[1].plusMinutes(5))+")."){
					@Override
					public void effects() {
						loiter(timeUntilChange);
					}
				};
				
			}
			
			return null;
		}

		private void loiter(int minutes) {
			String period = "";
			int hours = minutes / 60;
			int partialMinutes = minutes % 60;
			if (hours != 0) {
				period += (hours==1?"":Util.intToString(hours)) + " hour"+(hours==1?" ":"s ");
			}
			if (partialMinutes != 0) {
				period += Util.intToString(partialMinutes) + " minutes ";
			}
			if (minutes == 0) {
				period = "period ";
			}
			Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'><i>Вы проводите следующие ").append(period).append("бездельничая и не делая ничего особенного...</i>").append("</p>");
			Main.game.getPlayer().setActive(false);
			Main.game.endTurn(60*minutes);
			Main.game.endTurnTimeTakenAddition = Main.game.endTurnTimeTaken;
			Main.game.getPlayer().setActive(true);
			Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
			Main.game.endTurnTimeTakenAddition = Main.game.endTurnTimeTaken;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
