package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMCultistKneeling;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.88
 * @version 0.3.7.9
 * @author Innoxia
 */
public class CultistDialogue {

	private static NPC getCultist() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("Появляется Ведьма!", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Придумай оправдание и поскорее уйди от этой назойливой культистки.", ENCOUNTER_START){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START_LEAVE", getCultist()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if(index==2) {
				return new Response("Часовня", "Сделай, как просит культистка, и последуй за ней в ближайший переулок. Что может пойти не так?", ENCOUNTER_CHAPEL) {
					@Override
					public void effects() {
						// Pull up dress:
						getCultist().displaceClothingForAccess(CoverableArea.PENIS, null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL = new DialogueNode("Часовня Ведьмы", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться", "У тебя не остается иного выбора, кроме как сразиться!", getCultist(), Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), "Ты говоришь суккубу, что она тебе не интересна, и, как ты и ожидал, она бросается в атаку!"),
						new Value<>(getCultist(), "[npc.Name] наводит наготове свою метлу и кричит: [npc.speech(Как <i>смеешь</i> ты отказываться от моего дара! Я вручу его тебе силой!)]")));
				
			} else if(index==2) {
				return new ResponseSex("Принять", "Пасть на колени и приготовиться к оральному обслуживанию.",
						true, true,
						new SMCultistKneeling(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_ORAL_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_ORAL_SEX", getCultist())) {
					@Override
					public void effects() {
						// Remove seals so that player can get access to mouth:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							}
						}
					}
				};
				
			} else if(index == 3) {
				if(Main.game.getPlayer().hasVagina()) {
					return new ResponseSex("Предложить киску", "Вместо этого предложить [npc.name] свою киску.", Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
							null, Fetish.FETISH_PREGNANCY.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMAltarMissionary(
									Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							null,
							null,
							ENCOUNTER_CHAPEL_POST_VAGINAL_SEX,
							UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_VAGINAL_SEX", getCultist())) {
						@Override
						public void effects() {
							((Cultist)getCultist()).setRequestedAnal(false);
							
							// Remove seals so that player can get access to vagina:
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								while (clothing != null) {
									clothing.setSealed(false);
									System.out.println(clothing.getName());
									clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								}
							}
						}
					};
					
				} else {
					return new Response("Предложить киску", "Чтобы поднести [npc.name] в дар, тебе понадобится вагина...", null);
				}
				
			} else if(index==4) {
				return new ResponseSex("Предложить задницу", "Вместо этого предложить [npc.name] свою задницу.", Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING),
						null, Fetish.FETISH_ANAL_RECEIVING.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_ANAL_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_ANAL_SEX", getCultist())) {
					@Override
					public void effects() {
						((Cultist)getCultist()).setRequestedAnal(true);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_REPEAT = new DialogueNode("Часовня Ведьмы", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Pull up dress:
			getCultist().displaceClothingForAccess(CoverableArea.PENIS, null);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_REPEAT", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENCOUNTER_CHAPEL.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_LEAVING = new DialogueNode("Часовня Ведьмы", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_LEAVING", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Покинуть часовню и выйти обратно на улицы Dominion.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			
			} else if(index==10) {
				return new Response(
						"Удалить персонажа",
						"Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]",
						ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getCultist());
					}
				};
			
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_VICTORY = new DialogueNode("Часовня Ведьмы", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Развернуться и направиться к двери.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Colour colour = PresetColour.CLOTHING_BLACK;
						if(getCultist().getClothingInSlot(InventorySlot.TORSO_UNDER)!=null && getCultist().getClothingInSlot(InventorySlot.TORSO_UNDER).getColour(0)==PresetColour.CLOTHING_WHITE) {
							 colour = PresetColour.CLOTHING_WHITE;
						}
						
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			
			} else if(index==2) {
				return new ResponseSex("Секс", "Решить не использовать [npc.namePos] метлу, чтобы пригвоздить [npc.herHim] к месту, а вместо этого просто заняться обычным доминантным сексом с [npc.herHim]...",
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_SEX", getCultist())) {
				};
			
			} else if(index == 3) {
				return new ResponseSex("Печать ведьмы", "Использовать её метлу, чтобы наложить на неё Ведьминское Заклятие.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
								map.put(ImmobilisationType.WITCH_SEAL, Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newHashSetOfValues(getCultist()))));
								return map;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_BROOMSTICK_SEAL_SEX", getCultist())) {
				};
				
			} else if (index == 4) {
				return new Response("Полные трансформации",
						"Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getCultist());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_LOSS = new DialogueNode("Часовня Ведьмы", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Игрушка ведьмы", "Ты полностью обездвижен и ничего не можешь сделать, пока ведьма готовится использовать тебя в качестве своей игрушки.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
								map.put(ImmobilisationType.WITCH_SEAL, Util.newHashMapOfValues(new Value<>(getCultist(), Util.newHashSetOfValues(Main.game.getPlayer()))));
								return map;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS_SEX", getCultist())) {
					@Override
					public void effects() {
						((Cultist)getCultist()).setRequestedAnal(false);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							}
						}
						// Remove seals so that player can get access to anus:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ORAL_SEX = new DialogueNode("После секса", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] хватило орального секса от тебя...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ORAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Развернуться и направиться к двери.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_VAGINAL_SEX = new DialogueNode("После секса", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] хватило траханья твоей киски...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_VAGINAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Развернуться и направиться к двери.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ANAL_SEX = new DialogueNode("После секса", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] хватило траханья твоей задницы...");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ANAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить свой путь.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX = new DialogueNode("После секса", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] хватило траханья тебя...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить свой путь.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEX = new DialogueNode("После секса", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "Тебе хватило траханья [npc.name]...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Развернуться и направиться к двери.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX = new DialogueNode("После секса", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Развернуться и направиться к двери.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
