package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.8
 * @version 0.3.8.8
 * @author Innoxia
 */
public class StormStreetAttackerDialogue {
	
	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static NPC getMugger() {
		return Main.game.getActiveNPC();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	public static final DialogueNode STORM_ATTACK = new DialogueNode("Нападение!", "", true) {
		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK", getMugger());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "Защититесь от нежелательных притязаний [npc.name]!", getMugger());
				
			} else if (index == 2) {
				return new Response("Предложить деньги",
						"Из-за бушующего шторма Арканы [npc.name] не интересуют ваши деньги, она хочет только секса! Вам придется либо сразиться с [npc.herHim], либо отдать [npc.herHim] то, чего хочет [npc.she]!",
						null);
				
			} else if (index == 3) {
				return new ResponseSex("Предложить свое тело", "Предложите свое тело [npc.name], чтобы избежать жестокого столкновения.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
						null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getMugger()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Main.game.getPlayer().getCompanions()) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
								}
								return super.getSexControl(character);
							}
						},
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_BODY", getMugger()));
					
			} else if (index == 4 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
					return new Response("Предложить секс втроем",
							UtilText.parse(getMugger(), "Сразу видно, что [npc.name] совсем не горит желанием трахаться с тобой, так что секс втроем ей вряд ли интересен..."),
							null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response("Предложить секс втроем",
							UtilText.parse(getMugger(), "Вы понимаете, что [npc.name] совсем не заинтересована в сексе с [com.name], так что группового секса не получится..."),
							null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Предложить секс втроем",
							UtilText.parse(getMugger(), "Можно заметить, что [com.name] совсем не заинтересована в сексе с [npc.name], а заставить [com.herHim] ты не можешь..."),
							null);
					
				} else {
					return new ResponseSex("Предложить секс втроем",
							UtilText.parse(getMugger(), "Предложите [npc.name] возможность заняться сексом и с вами, и с [com.name], чтобы избежать жестокого столкновения."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_THREESOME", getMugger()));
				}
				
			} else if (index == 5 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();
				
				if(!getMugger().isAttractedTo(companion)) {
					return new Response("Предложить [com.name]",
							UtilText.parse(getMugger(), "Вы понимаете, что [npc.name] совсем не заинтересована в сексе с [com.name]..."),
							null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Предложить [com.name]",
							UtilText.parse(getMugger(), "Можно заметить, что [com.name] совсем не заинтересована в сексе с [npc.name], а заставить [com.herHim] ты не можешь..."),
							null);
					
				} else {
					return new ResponseSex("Предложить [com.name]",
							UtilText.parse(getMugger(), "Скажите [npc.name], что [npc.she] может использовать тело [com.namePos], чтобы избежать жестокого столкновения."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_COMPANION", getMugger())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {
		@Override
		public String getDescription() {
			return "Вы победили [npc.name]!";
		}
		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getMugger());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getMugger());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Оставьте [npc.name] позади и продолжайте свой путь...<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Секс",
						"Ну, [npc.she] <i>сам</i> напросился!",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX", getMugger()));
				
			} else if (index == 3) {
				return new ResponseSex("Нежный секс",
						"Ну, [npc.she] <i>сам</i> напросился!",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null,
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getMugger()));
				
			} else if (index == 4) {
				return new ResponseSex("Грубый секс",
						"Ну, [npc.she] <i>сам</i> напросился!",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null,
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getMugger()));
				
			} else if (index == 5) {
				return new ResponseSex("Подчиниться",
						"Ты не совсем понимаешь, что делать... Возможно, лучше позволить [npc.name] решить, что будет дальше?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getMugger()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion())),
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getMugger()));
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 8 && getMugger().isAbleToSelfTransform()) {
				return new Response("Трансформировать [npc.herHim]",
						"Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getMugger());
					}
				};
				
			} else if (index == 9 && getMugger().isAbleToSelfTransform()) {
				return new Response("Быстрые трансформации",
						"Так как [npc.she] может превращать [npc.herself], у тебя в голове рождается несколько идей...(Ты вернешься к этим вариантам, когда закончишь превращать [npc.herHim].)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getMugger(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedTo(getMugger())) {
					return new Response("Трио", UtilText.parse(getMugger(), "[com.Name] не испытывает влечения к [npc.name], так что она не захочет заниматься сексом с [npc.herHim]!"), null);
					
				} else {
					return new ResponseSex("Трио",
							UtilText.parse(getMugger(), companion, "Занимайтесь доминирующим сексом с [npc.name], и тогда [npc2.name] присоединится к веселью."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_THREESOME", getMugger()));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getMugger().isAttractedTo(companion)) {
					return new Response("Отдать [com.name]", UtilText.parse(getMugger(), "[npc.Name] не испытывает влечения к [com.name], так что она не захочет заняться сексом с [npc.herHim]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger())) {
					return new Response("Отдать [com.name]", UtilText.parse(getMugger(), "[com.Name] не испытывает влечения к [npc.name], так что она не захочет заниматься сексом с [npc.herHim]!"), null);
					
				} else {
					return new ResponseSex("Отдать [com.name]",
							UtilText.parse(getMugger(), "Сказать [com.name], что [com.she] может немного развлечься с [npc.name], пока ты наблюдаешь."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger()));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getMugger())) {
					return new Response("Предложить [com.name]", UtilText.parse(getMugger(), "[com.Name] совсем не хочет трахаться с [npc.name]!"), null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response("Предложить [com.name]", UtilText.parse(getMugger(), "[npc.Name] не проявляет интереса к сексу с [com.name]!"), null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Предложить [com.name]",
							UtilText.parse(getMugger(), "Можно заметить, что [com.name] совсем не заинтересована в сексе с [npc.name], а заставить [com.herHim] ты не можешь..."),
							null);
					
				} else {
					return new ResponseSex("Предложить [com.name]",
							UtilText.parse(getMugger(), "Сказать [npc.name], что [npc.she] может воспользоваться [com.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Поражение", "", true) {
		@Override
		public String getDescription() {
			return "Вы были повержены [npc.name]!";
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getMugger()));
			
			if(isCompanionDialogue()) {
				if(getMugger().isWillingToRape()) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "RAPE_BOTH", getMugger()));
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "OFFER_SEX_BOTH", getMugger()));
				}
				
			} else {
				if(getMugger().isWillingToRape()) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "RAPE_PLAYER", getMugger()));
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "OFFER_SEX", getMugger()));
				}
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getMugger()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getMugger().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getMugger().isWillingToRape());
				
				if (index == 1) {
					return new ResponseSex("Секс",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] навязывает [npc.herself] тебе"+(companionSex?" и [com.name]":"")+"..."
										:"Сказать [npc.name], что ты"+(companionSex?" и [com.name]":"")+" хочет заняться сексом с [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] навязывает [npc.herself] тебе"+(companionSex?" и [com.name]":"")+"..."
										:"Сказать [npc.name], что ты"+(companionSex?" и [com.name]":"")+" с радостью займется сексом с [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Сопротивляться сексу",
							UtilText.parse(getMugger(), "[npc.Name] навязывает [npc.herself] тебе"+(companionSex?" и [com.name]":"")+"..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getMugger()));
					
				} else if (index == 4 && !getMugger().isWillingToRape()) {
					return new Response("Отказаться",
							UtilText.parse(getMugger(), "Отказаться от секса с [npc.name] и продолжить свой путь."),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "DEFEATED_REFUSE_THREESOME", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
				
			} else {
				if (index == 1) {
					return new ResponseSex("Секс",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] навязывает вам [npc.herself]..."
										:"Сказать [npc.name], что вы хотите заняться сексом с [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name] навязывает вам [npc.herself]..."
										:"Сказать [npc.name], что вы с радостью займетесь сексом с [npc.herHim]."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Сопротивляться сексу",
							UtilText.parse(getMugger(), "[npc.Name] навязывает вам [npc.herself]..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_RESIST", getMugger()));
					
				} else if (index == 4 && !getMugger().isWillingToRape()) {
					return new Response("Отказаться",
							UtilText.parse(getMugger(), "Отказаться от секса с [npc.name] и продолжить свой путь."),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "DEFEATED_REFUSE_SEX", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь на楽しみлся, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getMugger()) >= getMugger().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_VICTORY", getMugger());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getMugger());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Оставить [npc.name] позади и продолжить свой путь.<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Ничто не мешает вам полакомиться одеждой и вещами [npc.namePos]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Свалиться", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "Вы совершенно измотаны доминирующим напором [npc.namePos] и нуждаетесь в отдыхе, чтобы прийти в себя.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_DEFEAT", getMugger());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Продолжить свой путь.<br/>[style.italicsBad(Это навсегда удалит [npc.name] из игры!)]",
						Main.game.getDefaultDialogue(false)) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
			}
			return null;
		}
	};
}
