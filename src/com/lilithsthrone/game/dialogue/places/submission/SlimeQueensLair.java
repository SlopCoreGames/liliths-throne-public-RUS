package com.lilithsthrone.game.dialogue.places.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.2
 * @author Innoxia
 */
public class SlimeQueensLair {
	
	private static void resetTower() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsDefeated, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, false);
	}
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Прихожая", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ENTRANCE"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsIntroduced)) {
				UtilText.nodeContentSB.append(
						"<p>[style.italicsBad(Ты понимаешь, что если здесь завяжется драка, сбежать будет практически невозможно!)]</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Толкнуть переднюю дверь башни и выйти обратно.", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "LEAVE_TOWER"));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode GUARD_POST = new DialogueNode("Пост стражи", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed);
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_AREA"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_DEFEATED"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_PACIFIED"));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsIntroduced)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_REPEAT_BLUFFED"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_REPEAT"));
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsBluffed)) {
				if(index==1) {
					return new Response("Поговорить", UtilText.parse(Main.game.getNpc(SlimeGuardFire.class), Main.game.getNpc(SlimeGuardIce.class), "Спросить [npc1.name] и [npc2.name] о том, как они стали стражами Королевы Слаймов."), GUARD_POST_TALK);
						
				} else if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE) && index==2){
					return new ResponseCombat("Сразиться",
							"Решить бросить притворство и напасть на [slimeFire.name] и [slimeIce.name]!",
							Main.game.getNpc(SlimeGuardFire.class),
							Util.newArrayListOfValues(Main.game.getNpc(SlimeGuardFire.class), Main.game.getNpc(SlimeGuardIce.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Поверить не могу, что вы купились на мою историю!)] — смеешься ты, готовясь к бою со слайм-близнецами. [pc.speech(Я научу вас не быть такими доверчивыми!)]"),
									new Value<>(Main.game.getNpc(SlimeGuardFire.class), "[slimeFire.speech(Сестренка и я сейчас оторвемся по полной!)] — усмехается [slimeFire.name], сжимая в руке свой зачарованный огнем меч."),
									new Value<>(Main.game.getNpc(SlimeGuardIce.class), "[slimeIce.speech(Вы еще пожалеете, что обманули нас!)] — выкрикивает [slimeIce.name], вставая плечом к плечу с сестрой, пока та готовится к бою.")));
					
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)?index==2:index==3) {
					return new ResponseSex("Бок о бок",
							"Прижать [slimeFire.name] и [slimeIce.name] на четвереньки, бок о бок, и приготовиться трахнуть их.",
							null, null, null, null, null, null,
							true, false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.ALL_FOURS),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.ALL_FOURS_TWO))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SIDE_BY_SIDE"));
				
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)?index==3:index==4) {
					return new ResponseSex("Стать шредром",
							"Позволь [slimeFire.name] и [slimeIce.name] устроить тебе шпажку.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, true,
							new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED"));
				} else {
					return null;
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated)) {
				if(index==1) {
					return new ResponseSex("Бок о бок",
							"Прижать [slimeFire.name] и [slimeIce.name] на четвереньки, бок о бок, и приготовиться трахнуть их.",
							null, null, null, null, null, null,
							true, false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.ALL_FOURS),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.ALL_FOURS_TWO))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SIDE_BY_SIDE"));
				
				} else if(index==2) {
					return new ResponseSex("Стать шредром",
							"Позволь [slimeFire.name] и [slimeIce.name] устроить тебе шпажку.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, true,
							new SMAllFours(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.IN_FRONT),
											new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
							},
							null,
							null, AFTER_SLIME_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED"));
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseCombat("Сразиться",
							"Проложи себе путь сквозь этих слаймов!",
							Main.game.getNpc(SlimeGuardFire.class),
							Util.newArrayListOfValues(Main.game.getNpc(SlimeGuardFire.class), Main.game.getNpc(SlimeGuardIce.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Раз уж ты хочешь драки, ты её получишь!)] — выкрикиваешь ты, готовясь сразиться с братом и сестрой слаймов."),
									new Value<>(Main.game.getNpc(SlimeGuardFire.class), "[slimeFire.speech(Сестренка и я сейчас оторвемся по полной!)] — усмехается [slimeFire.name], сжимая в руке свой зачарованный огнем меч."),
									new Value<>(Main.game.getNpc(SlimeGuardIce.class), "[slimeIce.speech(Ты пожалеешь об этом!)] — выкрикивает [slimeIce.name], вставая плечом к плечу со своим братом и готовясь к бою."))){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
						}
					};
					
				} else if(index==2) {
					if(Main.game.getNpc(SlimeGuardFire.class).getFoughtPlayerCount()>0) {
						return new Response("Слайм-блеф", "Уже немного поздно пытаться обмануть этих двоих, раз уж ты уже успел с ними подраться...", null);
						
					} else if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
						return new Response("Слайм-блеф", "Притворись одним из новых подданных Королевы Слаймов.", GUARD_POST_SLIME_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else {
						return new Response("Слайм-блеф", "Чтобы сделать это, нужно быть слаймом!", null);
					}
					
				} else if(index==3) {
					if(Main.game.getNpc(SlimeGuardFire.class).getFoughtPlayerCount()>0) {
						return new Response("Блеф слуги", "Уже немного поздно пытаться обмануть этих двоих, раз уж ты уже успел с ними подраться...", null);
						
					} else if(Main.game.getPlayer().getHistory()==Occupation.BUTLER) {
						return new Response("Блеф дворецкого", "Использовать знания, полученные во время работы дворецким, чтобы обмануть этих слаймов.", GUARD_POST_BUTLER_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else if(Main.game.getPlayer().getHistory()==Occupation.MAID) {
						return new Response("Блеф горничной", "Использовать знания, полученные во время работы горничной, чтобы обмануть этих слаймов.", GUARD_POST_MAID_BLUFF) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsBluffed, true);
							}
						};
						
					} else {
						return new Response("Блеф слуги", "У вас недостаточно знаний, чтобы притвориться слугой! (Требуется история 'Горничной' или 'Дворецкого')", null);
					}
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode GUARD_POST_SLIME_BLUFF = new DialogueNode("Пост стражи", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SLIME_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode GUARD_POST_BUTLER_BLUFF = new DialogueNode("Пост стражи", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_BUTLER_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode GUARD_POST_MAID_BLUFF = new DialogueNode("Пост стражи", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_MAID_BLUFF")
					+UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BLUFF_SUCCESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode GUARD_POST_TALK = new DialogueNode("Пост стражи", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_GUARD_SEX_AS_DOM = new DialogueNode("Завершено", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_GUARD_SEX_AS_SUB = new DialogueNode("Завершено", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_AS_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_GUARDS_COMBAT_PLAYER_VICTORY = new DialogueNode("Победа", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "", SLIME_GUARDS_COMBAT_PLAYER_VICTORY_CONTINUE);
			} else if(index==2 || index==3) {
				return GUARD_POST.getResponse(responseTab, index-1);
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_GUARDS_COMBAT_PLAYER_VICTORY_CONTINUE = new DialogueNode("Победа", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_VICTORY_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_GUARDS_COMBAT_PLAYER_DEFEAT = new DialogueNode("Поражение", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_GUARDS_COMBAT_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Спитройст",
						"[slimeFire.name] и [slimeIce.name] подходят, чтобы немного развлечься с тобой...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SlimeGuardIce.class), SexSlotAllFours.IN_FRONT),
										new Value<>(Main.game.getNpc(SlimeGuardFire.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						null, AFTER_SLIME_GUARD_SEX_DEFEATED, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "GUARD_POST_SEX_SPITROASTED_UPON_DEFEAT"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_GUARD_SEX_DEFEATED = new DialogueNode("Завершено", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вышвырнули", "[slimeFire.name] вышвыривает тебя через парадную дверь.", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_GUARD_SEX_DEFEATED_KICKED_OUT"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	
	
	public static final DialogueNode STAIRCASE_UP = new DialogueNode("Винтовая лестница", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STAIRCASE_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Наверх", "Подняться по винтовой лестнице на первый этаж."){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN = new DialogueNode("Винтовая лестница", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STAIRCASE_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Вниз", "Спуститься по винтовой лестнице на первый этаж."){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_STAIRS_UP);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Коридор", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ROOM = new DialogueNode("Спальня", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode STORAGE_VATS = new DialogueNode("Дистиллерия", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "STORAGE_VATS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ROYAL_GUARD_POST = new DialogueNode("Пост королевской стражи", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted));
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_DEFEATED");
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_PACIFIED");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated)) {
				boolean defeatReacted = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted);
				if(index==(!defeatReacted?2:1)) {
					return new ResponseSex("Секс",
							UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "Притянуть [slimeRoyalGuard.name] к своим [slimeRoyalGuard.his] ногам и приготовиться трахать [slimeRoyalGuard.him]."),
							null, null, null, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
							null,
							null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_DOM")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, true);
						}
					};
				
				} else if(index==(!defeatReacted?3:2)) {
					return new ResponseSex("Подчиниться",
							UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "Позволить [slimeRoyalGuard.name] трахать тебя."),
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, true);
						}
					};
					
				} else if(!defeatReacted && index==1) {
					return new Response("Продолжить",
							"Продолжить свой путь по башне.",
							CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeatReacted, true);
						}
					};
					
				} else {
					return null;
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				if(index==1) {
					return new Response("Поговорить", "Спросить у [slimeRoyalGuard.name], как [slimeRoyalGuard.he] оказалась на страже Королевы Слаймов.", ROYAL_GUARD_POST_TALK);
					
				} else if(index==2) {
					return new ResponseCombat("Спарринг",
							"Принять предложение [slimeRoyalGuard.namePos] о спарринге с условием, что победитель сможет делать с телом проигравшего всё, что пожелает.",
							Main.game.getNpc(SlimeRoyalGuard.class),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Ладно, я сойдусь с тобой в спарринге,)] — говорите вы, готовясь к бою, [pc.speech(но помни свои слова о том, что твоё тело будет моим, когда ты проиграешь!)]"),
									new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(Ха!)] — гремит [slimeRoyalGuard.name]. [slimeRoyalGuard.speech(Я с нетерпением жду возможности забрать свой приз!)]")));
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseCombat("Сразиться",
							"Защищайтесь от [slimeRoyalGuard.name].",
							Main.game.getNpc(SlimeRoyalGuard.class),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Я сражался и побеждал врагов и посильнее тебя!)] — выкрикиваете вы, готовясь к бою."),
									new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(Никто не сильнее меня!)] — в ответ ревет [slimeRoyalGuard.name]. [slimeRoyalGuard.speech(Готовься к поражению!)]")));
					
				} else if(index==2) {
					if(Main.game.getNpc(SlimeRoyalGuard.class).getFoughtPlayerCount()>0) {
						return new Response("Сделать комплимент", "Уже сразившись с [slimeRoyalGuard.name] однажды, он теперь слишком насторожен, чтобы попасться на любые ваши уловки...", null);
						
					} else {
						return new Response("Сделать комплимент", "Сделайте комплимент изящной работе ног [slimeRoyalGuard.namePos], пытаясь заставить [slimeRoyalGuard.him] совершить ошибку.", ROYAL_GUARD_POST_ADMIRE) {
							@Override
							public void effects() {
								Main.game.getNpc(SlimeRoyalGuard.class).setHealthPercentage(0.8f);
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_TALK = new DialogueNode("Пост королевской стражи", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE = new DialogueNode("Пост королевской стражи", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться",
						"Защищайтесь от [slimeRoyalGuard.name].",
						Main.game.getNpc(SlimeRoyalGuard.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(Очень хорошо, я принимаю вызов,)] — говорите вы, отступая и готовясь к схватке."),
								new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(Отлично! А теперь защищайся!)] — гремит [slimeRoyalGuard.name], прежде чем броситься в атаку.")));
				
			} else if(index==2) {
				if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.FEROCIOUS_WARRIOR) || Main.game.getPlayer().getHistory()==Occupation.SOLDIER) {
					return new Response("Инструктировать", "Расскажите [slimeRoyalGuard.name], что [slimeRoyalGuard.he] делает не так, пытаясь смутить [slimeRoyalGuard.him] и тем самым измотать [slimeRoyalGuard.himself].", ROYAL_GUARD_POST_ADMIRE_INSTRUCT) {
						@Override
						public void effects() {
							Main.game.getNpc(SlimeRoyalGuard.class).setHealthPercentage(0.4f);
						}
					};
					
				} else {
					return new Response("Инструктировать",
							"Вы недостаточно разбираетесь в боевых техниках, чтобы дать [slimeRoyalGuard.name] какой-либо совет. (Требуется "+Perk.FEROCIOUS_WARRIOR.getName(Main.game.getPlayer())+" перк или предыстория «Солдат».)",
							null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.NYMPHOMANIAC) || Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST)>=50) {
					return new Response("Соблазнить", "Соблазни [slimeRoyalGuard.name] и подстрекай [slimeRoyalGuard.him] красоваться перед тобой, чтобы заставить [slimeRoyalGuard.him] изрядно вымотаться в [slimeRoyalGuard.himself].", ROYAL_GUARD_POST_ADMIRE_SEDUCE) {
						@Override
						public void effects() {
							Main.game.getNpc(SlimeRoyalGuard.class).setLustNoText(80);
						}
					};
					
				} else {
					return new Response("Соблазнить", "Вы недостаточно искусны в соблазнении, чтобы попытаться это сделать. (Требуется активная черта «Нимфоманка» или более 50 единиц урона Похотью.)", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE_INSTRUCT = new DialogueNode("Пост королевской стражи", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_INSTRUCT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться",
						"Теперь, когда вы вымотали [slimeRoyalGuard.name], победить [slimeRoyalGuard.he] будет проще!",
						Main.game.getNpc(SlimeRoyalGuard.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(Конечно, я принимаю вызов,)] — отвечаете вы, готовясь к бою."),
								new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(Готовься... Готовься... защищаться!)] — тяжело дыша, пытается перевести дух [slimeRoyalGuard.name].")));
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) > Main.game.getNpc(SlimeRoyalGuard.class).getAttributeValue(Attribute.MAJOR_PHYSIQUE)) {
					return new Response("Подавить", "Теперь, когда ты измотал [slimeRoyalGuard.name], подавить [slimeRoyalGuard.him] будет проще простого.", ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
						}
					};
					
				} else {
					return new Response("Подавить", "Хотя ты и измотал [slimeRoyalGuard.name], тебе всё ещё не хватает сил, чтобы подавить [slimeRoyalGuard.him]. (Требуется телосложение выше [slimeRoyalGuard.his].)", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER = new DialogueNode("Пост королевской стражи", "", false, true) {

		@Override
		public boolean isTravelDisabled() {
			return ROYAL_GUARD_POST.isTravelDisabled();
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_INSTRUCT_OVERPOWER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNode ROYAL_GUARD_POST_ADMIRE_SEDUCE = new DialogueNode("Пост королевской стражи", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "ROYAL_GUARD_POST_ADMIRE_SEDUCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться",
						"Теперь, когда вы вымотали [slimeRoyalGuard.name], победить [slimeRoyalGuard.he] будет проще!",
						Main.game.getNpc(SlimeRoyalGuard.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(Ладно, я сражусь с тобой...)] — вздыхаешь ты, готовясь к бою."),
								new Value<>(Main.game.getNpc(SlimeRoyalGuard.class), "[slimeRoyalGuard.speech(Э-отлично!)] — отвечает [slimeRoyalGuard.name], уже смущенный и возбужденный твоими поддразниваниями.")));
				
			} else if(index==2) {
				return new ResponseSex("Субмиссивный секс",
						UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "Довести [slimeRoyalGuard.name] до предела и изнурить его сексом."),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair",
						"ROYAL_GUARD_SEX_SEDUCTION")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
					}
				};
					
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM = new DialogueNode("Завершено", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB = new DialogueNode("Завершено", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION = new DialogueNode("Завершено", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return ROYAL_GUARD_POST.isTravelDisabled();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_SEDUCTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY = new DialogueNode("Победа", "", false) {
		
		@Override
		public boolean isTravelDisabled() {
			return ROYAL_GUARD_POST.isTravelDisabled();
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT = new DialogueNode("Поражение", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Секс",
						"[slimeRoyalGuard.Name] собирается повеселиться с тобой...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT_SEX"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_SPARRING_PLAYER_VICTORY = new DialogueNode("Победа", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_SPARRING_PLAYER_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Секс",
						UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "Притянуть [slimeRoyalGuard.name] к своим [slimeRoyalGuard.his] ногам и приготовиться трахать [slimeRoyalGuard.him]."),
						null, null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
						null,
						null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_DOM"));
			
			} else if(index==2) {
				return new ResponseSex("Подчиниться",
						UtilText.parse(Main.game.getNpc(SlimeRoyalGuard.class), "Позволить [slimeRoyalGuard.name] трахать тебя."),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), AFTER_SLIME_ROYAL_GUARD_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_BEGIN_SEX_AS_SUB"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_ROYAL_GUARD_SPARRING_PLAYER_DEFEAT = new DialogueNode("Поражение", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_SPARRING_PLAYER_DEFEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Секс",
						"[slimeRoyalGuard.Name] собирается повеселиться с тобой...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SlimeRoyalGuard.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT_SEX"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED = new DialogueNode("Завершено", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вышвырнут", "[slimeRoyalGuard.name] вышвыривает тебя за парадную дверь.", BatCaverns.SLIME_LAKE_ISLAND){
					@Override
					public void effects() {
						resetTower();
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_DEFEATED_KICKED_OUT"));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED = new DialogueNode("Завершено", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_ROYAL_GUARD_SEX_SPARRING_DEFEATED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROYAL_GUARD_POST.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BED_CHAMBER = new DialogueNode("Спальня", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
//		@Override
//		public boolean isTravelDisabled() {
//			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE);
//		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BED_CHAMBER_PACIFIED");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "BED_CHAMBER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
				return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Убедить", "Убедить [slimeQueen.name] перестать подстрекать других слаймов превращать людей в туннелях Submission.", SLIME_QUEEN_CONVINCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenConvinced, true);
							
							AbstractClothing crown = Main.game.getNpc(SlimeQueen.class).getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getNpc(SlimeQueen.class).unequipClothingIntoVoid(crown, true, Main.game.getNpc(SlimeQueen.class));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_head_slime_queens_tiara", false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_CONVINCE));
						}
					};
					
				} else if(index==2) {
					return new Response("Принудить", "Если она действительно хочет грубого обращения, то именно это [slimeQueen.namePos] и получит. Прижать её к кровати и заставить отказаться от своих планов.", SLIME_QUEEN_FORCE,
							Util.newArrayListOfValues(Fetish.FETISH_SADIST), Fetish.FETISH_SADIST.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenForced, true);

							AbstractClothing crown = Main.game.getNpc(SlimeQueen.class).getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getNpc(SlimeQueen.class).unequipClothingIntoVoid(crown, true, Main.game.getNpc(SlimeQueen.class));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_head_slime_queens_tiara", false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_FORCE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_FORCE));
						}
					};
					
				} else if(index==3) {
					return new Response("Помочь", "Вместо того чтобы останавливать её, предложи Catherine свою помощь. <b>Это значительно увеличит количество встреч со слаймами в Submission.</b>", SLIME_QUEEN_SUBMIT,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeQueenHelped, true);

							AbstractClothing crown = Main.game.getNpc(SlimeQueen.class).getClothingInSlot(InventorySlot.HEAD);
							if(crown!=null) {
								Main.game.getNpc(SlimeQueen.class).unequipClothingIntoVoid(crown, true, Main.game.getNpc(SlimeQueen.class));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_head_slime_queens_tiara", false), true));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_SUBMIT));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_SIX_SUBMIT));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_CONVINCE = new DialogueNode("Спальня", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_CONVINCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Проигнорировать провокационные стоны [slimeQueen.namePos] и уйти.", SLIME_QUEEN_LEAVE);
				
			} else if(index==2) {
				return new ResponseSex("'Изнасиловать'",
						UtilText.parse(Main.game.getNpc(SlimeQueen.class), "Подыграть фантазиям [slimeQueen.namePos] и взять её силой."),
						null, null, null, null, null, null,
						true, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SlimeQueen.class), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public boolean isRapePlayBannedAtStart(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						AFTER_SLIME_QUEEN_SEX, UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_SEX_START"));
				
			} else if(index==3) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					return new Response("Слайм-ванна",
							"Принять ванну с [slimeQueen.Name].<br/>[style.italicsExcellent(Это вымоет <b>все</b> жидкости из всех твоих отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
							SLIME_QUEEN_BATH);
					
				} else {
					return new Response("Слайм-ванна",
							"Принять ванну с [slimeQueen.name].<br/>[style.boldTerrible(Внимание:)] [style.boldSlime(Это превратит тебя в слайма!)]<br/>[style.italicsExcellent(Это вымоет <b>все</b> жидкости из всех твоих отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
							SLIME_QUEEN_BATH_TRANSFORM) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.SLIME));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_TRANSFORM_END"));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode SLIME_QUEEN_FORCE = new DialogueNode("Спальня", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_FORCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SLIME_QUEEN_SUBMIT = new DialogueNode("Спальня", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_SUBMIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_CONVINCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_BATH = new DialogueNode("Спальня", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_BATH, 240+30));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Проигнорировать провокационные стоны [slimeQueen.namePos] и уйти.", SLIME_QUEEN_LEAVE);
				
			} else if(index==2) {
				return new ResponseSex("'Изнасиловать'",
						UtilText.parse(Main.game.getNpc(SlimeQueen.class), "Подыграть фантазиям [slimeQueen.namePos] и взять её силой."),
						null, null, null, null, null, null,
						true, false,
						new SMBath(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SlimeQueen.class), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isRapePlayBannedAtStart(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						AFTER_SLIME_QUEEN_SEX_BATH,
						UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_SEX_START"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_BATH_TRANSFORM = new DialogueNode("Спальня", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_BATH_TRANSFORM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLIME_QUEEN_BATH.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_LEAVE = new DialogueNode("Спальня", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_CORRIDOR, false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SLIME_QUEEN_TALK = new DialogueNode("Спальня", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "SLIME_QUEEN_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode AFTER_SLIME_QUEEN_SEX_BATH = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return "Насладившись, пора положить этому конец...";
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(SlimeQueen.class))>=Main.game.getNpc(SlimeQueen.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_BATH");
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_BATH_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SLIME_QUEEN_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return "Насладившись, пора положить этому конец...";
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(SlimeQueen.class))>=Main.game.getNpc(SlimeQueen.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/slimeQueensLair", "AFTER_SLIME_QUEEN_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return BED_CHAMBER.getResponse(responseTab, index);
		}
	};
}
