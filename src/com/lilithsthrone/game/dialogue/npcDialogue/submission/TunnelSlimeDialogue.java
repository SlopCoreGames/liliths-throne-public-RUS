package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.5
 * @version 0.3.4
 * @author Innoxia
 */
public class TunnelSlimeDialogue {
	
	private static NPC getSlime() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ATTACK = new DialogueNode("Нападение!", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_INTRO"));
			
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					
				}
				// Slime transformation descriptions are appended in SubmissionAttacker getEncounterDialogue() method.
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					if (index == 1) {
						return new ResponseCombat("Сразиться",
								"Ты не собираешься мириться с тем, что [npc.name] превращает людей в слаймов. Проучи [npc.herHim]!", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), "[pc.speech(Под моим присмотром ты не будешь рыскать по этим туннелям!)] — кричишь ты, бросаясь в атаку!"),
										new Value<>(getSlime(), "[npc.speech(Предатель! Ты за это заплатишь!)] — вопит [npc.Name], пока [npc.she] готовится защищать [npc.herself].")));
						
					} else if (index == 2) {
						return new Response("Уйти", "Продолжить свое путешествие.", ATTACK) {
							@Override
							public void effects(){
								if(getSlime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_LEAVE"));
								}
							}
							@Override
							public DialogueNode getNextDialogue(){
								return Main.game.getDefaultDialogue(false);
							}
						};
						
					} else if (index == 3) {
						if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Доминирующий секс", "[npc.Name] к тебе не влечет!", null);
							
						} else if(getSlime().hasFetish(Fetish.FETISH_DOMINANT) && !getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							return new Response("Доминирующий секс", "[npc.Name] не желает отдавать тебе роль доминанта.", null);
							
						} else {
							return new ResponseSex("Доминирующий секс", "Возьмите на себя доминирующую роль и займитесь сексом с [npc.name].",
									null, null, null,
									null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(getSlime()),
									null,
									null), AFTER_SLIME_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
						}
						
					} else if (index == 4) {
						if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Секс в подчиненной позиции", "[npc.Name] к тебе не влечет!", null);
							
						} else if(getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlime().hasFetish(Fetish.FETISH_DOMINANT)) {
							return new Response("Секс в подчиненной позиции", "[npc.Name] не желает отдавать тебе роль сабмиссива.", null);
							
						} else {
							return new ResponseSex("Секс в подчиненной позиции", "Принять роль подчиненного и заняться сексом с [npc.name].",
									null, null, null,
									null, null, null,
									true, true,
									new SMGeneric(
											Util.newArrayListOfValues(getSlime()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null), AFTER_SLIME_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
						}
						
					} else {
						return null;
					}
					
				} else {
					if (index == 1) {
						return new ResponseCombat("Сразиться",
								"Побори за себя и сразись с [npc.name]!", Main.game.getActiveNPC(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), ""),
										new Value<>(getSlime(), "")));
						
					} else if (index == 2) {
						return new Response("Предложить деньги", "[npc.Name] не интересуют твои деньги! Всё, чего хочет [npc.she], — это превратить тебя в слайма!", null);
						
					} else if (index == 3) {
						return new Response("Подчиниться", "Позвольте [npc.name] превратить вас в слайма!", TRANSFORMED) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getSlime().useItem(Main.game.getItemGen().generateItem("RACE_INGREDIENT_SLIME"),
										Main.game.getPlayer(), false, true));
								if(getSlime().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED_SLIME_OFFER_SEX"));
								} else {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED_SLIME_NO_SEX"));
								}
							}
						};
						
					} else {
						return null;
					}
				}
				
			} else {
				if (index == 1) {
					return new ResponseCombat("Сразиться",
							"Побори за себя и сразись с [npc.name]!", Main.game.getActiveNPC(),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), ""),
									new Value<>(getSlime(), "")));
				
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand2()) {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "охват")+")",
								"У тебя недостаточно денег, чтобы откупиться от [npc.name]. Тебе придется либо сразиться с [npc.herHim], либо предложить [npc.herHim] свое тело!", null);
					} else {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand2(), "охват")+")",
								"Предложить заплатить [npc.name] "+Util.intToString(Main.game.getDialogueFlags().getMuggerDemand2())+" Огни, чтобы он оставил тебя в покое.", OFFER_MONEY) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-250);
							}
						};
					}
					
				} else if (index == 3) {
					if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Предложить свое тело", "Предложите свое тело [npc.name], чтобы избежать жестокого столкновения.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getSlime()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null) {
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
										}
										return super.getSexControl(character);
									}
								},
								AFTER_SLIME_SEX_AS_SUB,
								UtilText.parseFromXMLFile("places/submission/tunnelSlime", "OFFER_BODY"));
					} else {
						return new Response("Предложить свое тело", "Ты понимаешь, что [npc.name] совсем не заинтересован в сексе с тобой. Тебе придется либо предложить [npc.herHim] немного денег, либо приготовиться к бою!", null);
					}
					
				} else {
					return null;
				}
				
			}
			
		}
	};
	
	public static final DialogueNode ATTACK_REPEAT = new DialogueNode("Нападение!", "", true) {
		
		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT_TRANSFORMER");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ATTACK_PREGNANCY_REVEAL = new DialogueNode("Нападение!", "", true) {
		
		@Override
		public String getContent() {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER_PLAYER_SLIME"));
					if(getSlime().isAttractedTo(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_OFFER_SEX"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMER_PLAYER_SLIME_NO_SEX"));
					}
					return UtilText.nodeContentSB.toString();
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL_TRANSFORMER");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_PREGNANCY_REVEAL");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ATTACK.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode AFTER_SLIME_SEX_AS_DOM = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь насладился, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SLIME_SEX_AS_DOM){
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
	
	public static final DialogueNode AFTER_SLIME_SEX_AS_SUB = new DialogueNode("Использовано", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "[npc.Name] пока достаточно повеселилась с вами.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_SUB");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_SUB_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SLIME_SEX_AS_SUB){
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

	public static final DialogueNode TRANSFORMED = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "TRANSFORMED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Продолжить свое путешествие.", TRANSFORMED) {
					@Override
					public void effects(){
						if(getSlime().isAttractedTo(Main.game.getPlayer())) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_PLAYER_SLIME_TURN_DOWN_SEX"));
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 2) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Доминирующий секс", "[npc.Name] к тебе не влечет!", null);
					
				} else if(getSlime().hasFetish(Fetish.FETISH_DOMINANT) && !getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
					return new Response("Доминирующий секс", "[npc.Name] не желает отдавать тебе роль доминанта.", null);
					
				} else {
					return new ResponseSex("Доминирующий секс", "Возьмите на себя доминирующую роль и займитесь сексом с [npc.name].",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null), AFTER_SLIME_SEX_AS_DOM, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_DOM"));
				}
				
			} else if (index == 3) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Секс в подчиненной позиции", "[npc.Name] к тебе не влечет!", null);
					
				} else if(getSlime().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlime().hasFetish(Fetish.FETISH_DOMINANT)) {
					return new Response("Секс в подчиненной позиции", "[npc.Name] не желает отдавать тебе роль сабмиссива.", null);
					
				} else {
					return new ResponseSex("Секс в подчиненной позиции", "Принять роль подчиненного и заняться сексом с [npc.name].",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_SEX_AS_SUB, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "SLIME_SEX_AS_SUB"));
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RESIST_TRANSFORMED = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED"));
			
			if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape() && Main.game.isNonConEnabled()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED_RAPE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "RESIST_TRANSFORMED_NO_RAPE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape() && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("Секс",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>Вы безропотно отдаетесь [npc.name], откинувшись назад и не оказывая никакого сопротивления, пока [npc.she] языком трахает ваше горло. Спустя мгновение [npc.she] выпрямляется, оставляя мокрую лужу слизи по всему вашему [pc.lips]. [npc.speech(Хорошая сучка! Ты уже знаешь свое место! А теперь будь послушным [pc.girl] и постарайся получить от этого удовольствие...)]</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>Вы с жадностью отдаетесь [npc.name], закидывая голову, чтобы помочь [npc.herHim] языком трахать ваше горло. Спустя мгновение [npc.she] выпрямляется, оставляя мокрую лужу слизи по всему вашему [pc.lips]. [npc.speech(Хорошая сучка! Ты уже знаешь свое место! А теперь будь послушным [pc.girl] и постарайся получить от этого удовольствие...)]</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Сопротивляться сексу",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMLyingDown(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null, AFTER_SLIME_SEX_AS_SUB, "<p>Вы пытаетесь отвернуться, но [npc.name] просто обхватывает голову с обеих сторон, удерживая вас неподвижно, пока [npc.she] языком трахает ваше горло. Спустя мгновение [npc.she] выпрямляется, оставляя мокрую лужу слизи по всему вашему [pc.lips]. [npc.speech(Тупая сучка! Сопротивляйся сколько влезет! Я не остановлюсь, пока не наемся!)]</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Продолжить", "Продолжить путь.", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	

	public static final DialogueNode OFFER_MONEY = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "OFFER_MONEY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", OFFER_MONEY){
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
	
	// Standard combat:
	

	public static final DialogueNode AFTER_COMBAT_PLAYER_VICTORY = new DialogueNode("Победа", "", true) {

		@Override
		public String getDescription() {
			return "Вы победили [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_ENEMY_WANTS_SEX");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean noSex = getSlime().isPostCombatNoSex();
			boolean wantsSex = getSlime().isPostCombatWantsSex();
			boolean rapePlay = getSlime().isPostCombatRapePlay();
		
			if (index == 1) {
				return new Response("Продолжить",
						"Продолжить путь..."
							+ (getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)
									?UtilText.parse(getSlime(), "<br/>[style.italicsBad([npc.Name] будет навсегда удален из игры.)]")
									:""),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						if(getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						if(getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getSlime());
						}
					}
				};
				
			} else if (index == 2) {
				if(noSex) {
					return new Response("Секс", "[npc.Name] не заинтересована в сексе с вами!", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"Игры в изнасилование":"Секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", rapePlay?"AFTER_COMBAT_VICTORY_RAPE":"AFTER_COMBAT_VICTORY_SEX", getSlime()));
					
				} else {
					return new ResponseSex(
							"Изнасиловать [npc.herHim]",
							"[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
							null,
							null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE", getSlime()));
				}
				
			} else if (index == 3) {
				if(noSex) {
					return new Response("Нежный секс", "[npc.Name] не заинтересована в сексе с вами!", null);
					
				} else if(wantsSex){
					return new ResponseSex(rapePlay?"Игры в изнасилование (нежные)":"Нежный секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_GENTLE":"AFTER_COMBAT_VICTORY_SEX_GENTLE", getSlime()));
					
				} else {
					return new ResponseSex("Изнасиловать [npc.herHim] (нежно)", "[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getSlime()));
				}
				
			} else if (index == 4) {
				if(noSex) {
					return new Response("Грубый секс", "[npc.Name] не заинтересована в сексе с вами!", null);
					
				} else if(wantsSex){
					return new ResponseSex(rapePlay?"Игры в изнасилование (грубо)":"Грубый секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_ROUGH":"AFTER_COMBAT_VICTORY_SEX_ROUGH", getSlime()));
					
				} else {
					return new ResponseSex("Изнасиловать [npc.herHim] (грубо)", "[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getSlime()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getSlime()));
				}
				
			} else if (index == 5) {
				if(!getSlime().isAttractedTo(Main.game.getPlayer()) || getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("Подчиниться",
							"Вы не можете подчиниться [npc.name], так как [npc.sheHasFull] не заинтересована в сексе с вами!",
							null);
				} else {
					return new ResponseSex("Подчиниться",
							"Ты не совсем понимаешь, что делать... Возможно, лучше позволить [npc.name] решить, что будет дальше?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getSlime()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SLIME_SEX_AS_SUB,
							UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getSlime()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getSlime(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			}
			//TODO
//			else if (index == 7) {
//				if(slime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
//					return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
//					
//				} else {
//					return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
//						@Override
//						public void effects() {
//							slime().setPlayerKnowsName(true);
//							Main.game.getTextEndStringBuilder().append(slime().setAffection(Main.game.getPlayer(), 10));
//						}
//					};
//				}
//				
//			} 
			else if (index == 8 && getSlime().isAbleToSelfTransform()) {
				return new Response("Трансформировать [npc.herHim]",
						"Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getSlime());
					}
				};
				
			} else if (index == 10 && !getSlime().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Удалить персонажа",
						UtilText.parse(getSlime(), "Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_VICTORY_BANISH_NPC", getSlime()));
						Main.game.banishNPC(getSlime());
					}
				};
				
			} else {
				return null;
			}
			
		}
	};

	public static final DialogueNode AFTER_COMBAT_PLAYER_DEFEAT = new DialogueNode("Поражение", "", true) {
		
		@Override
		public String getDescription() {
			return "Вы были повержены [npc.name]!";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT"));
			
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_TRANSFORMER"));
				
			} else {
				if(getSlime().isAttractedTo(Main.game.getPlayer()) && getSlime().isWillingToRape() && Main.game.isNonConEnabled()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_RAPE"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_COMBAT_DEFEAT_NO_RAPE"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getSlime().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Сопротивляться");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сопротивляться",
								"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b>фетиш: вы так обожаете превращения, что просто не можете заставить себя сопротивляться!",
								null);
					} else {
						return new Response("Сопротивляться", "Выбить странную жидкость из рук [npc.namePos].", RESIST_TRANSFORMED);
					}
					
				} else if (index == 2) {
					return new Response("Подчиниться", "Позвольте [npc.name] превратить вас в слайма!", TRANSFORMED) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getSlime().useItem(Main.game.getItemGen().generateItem("RACE_INGREDIENT_SLIME"),
									Main.game.getPlayer(), false, true));
						}
					};
					
				} else {
					return null;
				}
				
			} else if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape() && Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("Секс",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SLIME_SEX_AS_SUB, "<p>Вы безропотно отдаетесь [npc.name], откинувшись назад и не оказывая никакого сопротивления, пока [npc.she] языком трахает ваше горло. Спустя мгновение [npc.she] выпрямляется, оставляя мокрую лужу слизи по всему вашему [pc.lips]. [npc.speech(Хорошая сучка! Ты уже знаешь свое место! А теперь будь послушным [pc.girl] и постарайся получить от этого удовольствие...)]</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER), AFTER_SLIME_SEX_AS_SUB, "<p>Вы с жадностью отдаетесь [npc.name], закидывая голову, чтобы помочь [npc.herHim] языком трахать ваше горло. Спустя мгновение [npc.she] выпрямляется, оставляя мокрую лужу слизи по всему вашему [pc.lips]. [npc.speech(Хорошая сучка! Ты уже знаешь свое место! А теперь будь послушным [pc.girl] и постарайся получить от этого удовольствие...)]</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Сопротивляться сексу",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getActiveNPC()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING), AFTER_SLIME_SEX_AS_SUB, "<p>Вы пытаетесь отвернуться, но [npc.name] просто обхватывает голову с обеих сторон, удерживая вас неподвижно, пока [npc.she] языком трахает ваше горло. Спустя мгновение [npc.she] выпрямляется, оставляя мокрую лужу слизи по всему вашему [pc.lips]. [npc.speech(Тупая сучка! Сопротивляйся сколько влезет! Я не остановлюсь, пока не наемся!)]</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Продолжить", "Продолжить путь.", AFTER_COMBAT_PLAYER_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь насладился, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getSlime()) >= getSlime().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM");
			} else {
				return UtilText.parseFromXMLFile("places/submission/tunnelSlime", "AFTER_SLIME_SEX_AS_DOM_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Ничто не мешает вам полакомиться одеждой и вещами [npc.namePos]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Удалить персонажа",
						UtilText.parse(getSlime(), "Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
						AFTER_COMBAT_PLAYER_VICTORY){
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
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
