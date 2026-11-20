package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueManager;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.FetishPotion;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Weather;

/**
 * @since 0.2.11
 * @version 0.3.7.8
 * @author Innoxia
 */
public class HarpyAttackerDialogue {
	
	private static boolean transformationsApplied = false;

	private static boolean isStorm() {
		return getHarpy().isVulnerableToArcaneStorm()
				&& Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
	}
	
	private static boolean isWantsToFight() {
		return getHarpy().isAffectionAggressionTrigger(Main.game.getPlayer());
	}

	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static NPC getHarpy() {
		return Main.game.getActiveNPC();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		List<GameCharacter> allCharacters = new ArrayList<>();
		allCharacters.add(getHarpy());
		allCharacters.addAll(Main.game.getPlayer().getCompanions());
		Collections.sort(allCharacters, (c1, c2) -> c1.isElemental()?(c2.isElemental()?0:1):(c2.isElemental()?-1:0));
		return allCharacters;
	}
	
	private static String getFileLocation() {
		return isStorm()?"harpyAttackStorm":"harpyAttack";
	}
	
	private static void applyPregnancyReactions() {
		if(getHarpy().isVisiblyPregnant()){
			getHarpy().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getHarpy(), true);
		}
		if(isCompanionDialogue() && getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getHarpy(), true);
		}
	}
	
	private static String getStatus() {
		return AffectionLevel.getAttitudeDescription(getHarpy(), Main.game.getPlayer(), true);
	}
	
	public static final DialogueNode HARPY_ATTACK = new DialogueNode("Нападение!", "Из теней выскакивает фигура!", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!isWantsToFight()) { // Catch for if the player has somehow raised this npc's affection without using the talk option. Should be impossible but someone managed it somehow (maybe with mods)
				getHarpy().setPlayerKnowsName(true);
			}
			getHarpy().generatePostCombatPotions();
			transformationsApplied = false;
			Main.game.getDialogueFlags().setFlag("innoxia_alleyway_transformations_applied", false);

			if(getHarpy().getPlayerSurrenderCount()>=4) { 
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) { // Even if immune, only give fuck option as others dno't make sense to trigger during a storm
					Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 4);
				} else {
					Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", Util.random.nextInt(6)+1);
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==1 && Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand3()) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 2);
					}
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==6
							&& ((Main.game.getPlayer().getTattooInSlot(InventorySlot.GROIN)!=null || !Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
										&& (!Main.game.isAnalContentEnabled() || Main.game.getPlayer().getTattooInSlot(InventorySlot.TORSO_UNDER)!=null || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 4);
					}
					if(Main.game.getDialogueFlags().getSavedLong("randomResponseIndex")==4 && (!getHarpy().isAttractedTo(Main.game.getPlayer()) || getHarpy().hasStatusEffect(StatusEffect.RECOVERING_AURA))) {
						Main.game.getDialogueFlags().setSavedLong("randomResponseIndex", 5);
					}
				}
			}
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(getHarpy().hasEncounteredBefore()) {
				if(isWantsToFight()) {
					if(getHarpy().getPlayerSurrenderCount()>=4) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_SUBMITTED", getAllCharacters()));
						
					} else if(getHarpy().getPlayerSurrenderCount()==3) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_DEMAND_SUBMIT", getAllCharacters()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_REPEAT", getAllCharacters()));
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL", getHarpy()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getHarpy().getPlayerSurrenderCount()>=3) { // Bitch content
				return DialogueManager.getDialogueFromId("innoxia_encounters_dominion_harpy_nest_start").getResponse(responseTab, index);
			}
			
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Сразиться", "Побори за себя и сразись с [npc.name]!", getHarpy()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(isStorm()) {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "охват")+")",
								"Из-за бушующего шторма Арканы, [npc.name] сейчас заинтересован только в сексе с тобой!",
								null);
						
					} else if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand1()) {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "охват")+")",
								"У тебя недостаточно денег, чтобы откупиться от [npc.name]. Тебе придется либо сразиться с [npc.herHim], либо предложить [npc.herHim] свое тело!",
								null);
					} else {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "охват")+")",
								"Предложить заплатить [npc.name] "+Util.intToString(Main.game.getDialogueFlags().getMuggerDemand1())+" Огни, чтобы он оставил тебя в покое.", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getMuggerDemand1());
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_ATTACK_PAY_OFF", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 3) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Предложить свое тело",
								"Предложить свое тело [npc.name], чтобы избежать жестокого столкновения.<br/>[style.italicsSex(Постоянная покорность перед [npc.name] в конечном итоге приведет к тому, что [npc.herHim] заставит тебя стать [npc.her] сучкой...)]",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
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
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_OFFER_BODY", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								getHarpy().incrementPlayerSurrenderCount(1);
							}
						};
						
					} else {
						return new Response("Предложить свое тело", "Ты понимаешь, что [npc.name] совсем не заинтересован в сексе с тобой. Тебе придется либо предложить [npc.herHim] немного денег, либо приготовиться к бою!", null);
					}
					
				} else if (index == 4 && getHarpy().isApplyingPostCombatTransformations()) {
					return new Response("Сдаться",
							"Полностью сдаться на милость [npc.name] и позволить [npc.herHim] делать с твоим телом всё, что пожелает [npc.she]...<br/>[style.italicsTfGeneric(Это приведет к тому, что [npc.name] попытается заставить тебя выпить зелье трансформации, прежде чем, возможно, решит трахнуть тебя!)].<br/>[style.italicsSex(Постоянная покорность перед [npc.name] в конечном итоге приведет к тому, что [npc.herHim] заставит тебя стать [npc.her] сучкой...)]",
							SURRENDER,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							applyPregnancyReactions();
							getHarpy().incrementPlayerSurrenderCount(1);
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc.name] совсем не горит желанием трахаться с тобой, так что секс втроем ей вряд ли интересен..."),
								null);
						
					} else if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc.name] совсем не горит желанием трахаться с [npc2.name], так что секс втроем ей вряд ли интересен..."),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc2.name] совсем не горит желанием трахаться с [npc.name], а заставить [npc2.herHim] ты всё равно не сможешь..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Предложить [npc.name] заняться сексом одновременно с тобой и [npc2.name], чтобы избежать кровавой драки."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_OFFER_THREESOME", getHarpy(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 7 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc.name] совсем не горит желанием трахаться с [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc2.name] совсем не горит желанием трахаться с [npc.name], а заставить [npc2.herHim] ты всё равно не сможешь..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(getHarpy(), companion, "Сказать [npc.name], что [npc.she] может использовать тело [npc2.namePos], чтобы избежать кровавой драки."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_OFFER_COMPANION", getHarpy(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getHarpy()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if (index == 1) {
					if(isStorm()) {
						return new Response("Поговорить",
								"Из-за бушующего шторма Арканы, [npc.name] сейчас заинтересован только в сексе с тобой!",
								null);
					}
					return new Response("Поговорить", "Поговорите с [npc.name] какое-то время, чтобы получше узнать [npc.herHim].", HARPY_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 10));
							
							if(getHarpy().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(isStorm()) {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "охват")+")",
								"Из-за бушующего шторма Арканы, [npc.name] сейчас заинтересован только в сексе с тобой!",
								null);
					}
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getMuggerDemand1()) {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "охват")+")",
								"У вас недостаточно денег, чтобы предложить что-то [npc.name].", null);
					} else {
						return new Response("Предложить деньги ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getMuggerDemand1(), "охват")+")",
								"Предложите [npc.name] немного денег, чтобы помочь [npc.herHim] купить еду и одежду.", HARPY_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getMuggerDemand1()));
								Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 10));

								if(getHarpy().isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Секс (доминант)", "Возьмите на себя доминирующую роль и займитесь сексом с [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getHarpy()),
										Main.game.getPlayer().getCompanions(),
										null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_SEX_AS_DOM", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Секс (доминант)", "Вы чувствуете, что [npc.name] не особо заинтересована в сексе с вами...", null);
					}
					
				} else if (index == 4) {
					if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Секс (сабмиссив)", "Предложите свое тело [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Main.game.getPlayer().getCompanions()),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_SEX_AS_SUB", getAllCharacters())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Секс (сабмиссив)", "Вы чувствуете, что [npc.name] не особо заинтересована в сексе с вами...", null);
					}
					
				} if (index == 5) {
					if(isStorm()) {
						return new Response("Предложить комнату",
								"Из-за бушующего шторма Арканы, [npc.name] сейчас заинтересован только в сексе с тобой!",
								null);
						
					} else if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !getHarpy().isAffectionHighEnoughToInviteHome()) {
						return new Response("Предложить комнату",
								"Вам кажется, что было бы лучше провести больше времени, узнавая [npc.name], прежде чем приглашать [npc.herHim] обратно в особняк Lilaya...<br/>[style.italics(Требуется, чтобы [npc.name] имела хотя бы "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+" немного привязанности к вам.)]",
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("Предложить комнату",
								"Вам нужно получить разрешение Lilaya, прежде чем приглашать [npc.name] обратно в её особняк...",
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("Предложить комнату",
								"У тебя нет подходящей комнаты, чтобы [npc.name] могла въехать. Сначала улучши одну из пустых комнат в доме Lilaya до «Гостевой комнаты».",
								null);
						
					}else {
						return new Response("Предложить комнату", "Спросить у [npc.name], хочет ли [npc.she] комнату в особняке Lilaya.", HARPY_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 25));
							}
						};
					}
					
				} else if(index==6) {
					if(getHarpy().getPlayerSurrenderCount()<3 && getHarpy().isApplyingPostCombatTransformations()) {
						if(transformationsApplied) {
							return new Response("Трансформироваться",
									"[npc.Name] уже отдала тебе все зелья трансформации, что были у [npc.she]!",
									null);
							
						} else {
							return new Response("Трансформироваться",
									"Сказать [npc.name], что ты хочешь выпить любые зелья трансформации, которые есть у [npc.she]...<br/>[style.italicsTfGeneric(Это приведет к тому, что [npc.name] заставит тебя выпить зелье трансформации!)]",
										HARPY_PEACEFUL_TRANSFORMED,
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_TRANSFORMATION_RECEIVING), Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(), null, null, null) {
								@Override
								public Colour getHighlightColour() {
									return PresetColour.TRANSFORMATION_GENERIC;
								}
								@Override
								public void effects() {
									applyPregnancyReactions();
									Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "PEACEFUL_TRANSFORMATIONS", getAllCharacters()));
									Main.game.appendToTextStartStringBuilder(getHarpy().applyPostCombatTransformation());
									transformationsApplied = true;
								}
							};
						}
					}
					
				} else if (index==10) {
					return new Response("Атаковать", "Предать доверие [npc.namePos] и напасть на [npc.herHim]!", HARPY_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), -50));
							getHarpy().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 11 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc.name] совсем не горит желанием трахаться с тобой, так что секс втроем ей вряд ли интересен..."),
								null);
						
					} else if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc.name] совсем не горит желанием трахаться с [npc2.name], так что секс втроем ей вряд ли интересен..."),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc2.name] совсем не горит желанием трахаться с [npc.name], а заставить [npc2.herHim] ты всё равно не сможешь..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(getHarpy(), companion, "Предложить [npc.name] заняться сексом одновременно с тобой и [npc2.name]."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer(), companion),
										null,
										null,
										ResponseTag.PREFER_DOGGY),
								AFTER_SEX_PEACEFUL_THREESOME,
								UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_OFFER_THREESOME", getHarpy())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 12 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(!getHarpy().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc.name] совсем не горит желанием трахаться с [npc2.name]..."),
								null);
						
					} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc2.name] совсем не горит желанием трахаться с [npc.name], а заставить [npc2.herHim] ты всё равно не сможешь..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(getHarpy(), companion, "Сказать [npc.name], что [npc.she] может использовать тело [npc2.namePos]."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(companion),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())),
								AFTER_SEX_PEACEFUL_OFFERED_COMPANION, UtilText.parseFromXMLFile("encounters/dominion/"+getFileLocation(), "HARPY_ATTACK_PEACEFUL_OFFER_COMPANION", getHarpy(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getHarpy()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else if (index == 0) {
					return new Response("Уйти",
							"Сказать [npc.name], что ты спешишь по делам, и отправиться дальше.",
							Main.game.getDefaultDialogue(false));
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_TALK = new DialogueNode("Поговорить", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_TALK", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getHarpy().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Отпустить [npc.name].", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_OFFER_MONEY = new DialogueNode("Предложить деньги", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_OFFER_MONEY", getAllCharacters()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(getHarpy().isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Отпустить [npc.name] и пойти купить еды.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_OFFER_ROOM = new DialogueNode("Предложить комнату", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_OFFER_ROOM", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Забрать к себе", "Перенести [npc.name] в новую комнату [npc.her].", HARPY_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getHarpy().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getHarpy());
						Main.game.getTextEndStringBuilder().append(getHarpy().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("Новая комната", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_OFFER_ROOM_BACK_HOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Дать [npc.name] освоиться.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode HARPY_PEACEFUL_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HARPY_ATTACK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HARPY_PEACEFUL_ATTACK = new DialogueNode("Атаковать", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "HARPY_PEACEFUL_ATTACK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "Начать бой с [npc.name]!", getHarpy());
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("Продолжить", "Отойти от [npc.name] и приготовиться продолжить свой путь.", true) {
		
		@Override
		public String getContent() {
			if(getHarpy().isSatisfiedFromLastSex()) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_NO_ORGASM", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_THREESOME = new DialogueNode("Продолжить", "Отойти от [npc.name] и приготовиться продолжить свой путь.", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL_OFFERED_COMPANION = new DialogueNode("Продолжить", "Отойти от [npc.name] и приготовиться продолжить свой путь.", true) {
		
		@Override
		public String getContent() {
			if(getMainCompanion().isAttractedTo(getHarpy())) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_PEACEFUL_OFFERED_COMPANION_RELUCTANT", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getHarpy().setPlayerSurrenderCount(0);
			getHarpy().clearPetName(Main.game.getPlayer());
		}
		@Override
		public String getDescription() {
			return "Вы победили [npc.name]!";
		}
		@Override
		public String getContent() {
			if(getHarpy().isAttractedTo(Main.game.getPlayer()) && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getHarpy());
				
			} else {
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BETRAYED", getHarpy());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getHarpy());
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean noSex = getHarpy().isPostCombatNoSex();
			boolean wantsSex = getHarpy().isPostCombatWantsSex();
			boolean rapePlay = getHarpy().isPostCombatRapePlay();
			
			if (index == 1) {
				return new Response("Продолжить",
						"Продолжить путь..."
							+ (getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)
								?UtilText.parse(getHarpy(), "<br/>[style.italicsBad([npc.Name] будет навсегда удален из игры.)]")
								:""),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
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
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE":"AFTER_COMBAT_VICTORY_SEX", getAllCharacters()));
				} else {
					return new ResponseSex(
							"Изнасиловать [npc.herHim]",
							"[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE", getAllCharacters()));
				}
				
			} else if (index == 3) {
				if(noSex) {
					return new Response("Нежный секс", "[npc.Name] не заинтересована в сексе с вами!", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"Игры в изнасилование (нежные)":"Нежный секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_GENTLE":"AFTER_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()));
					
				} else {
					return new ResponseSex("Изнасиловать [npc.herHim] (нежно)",
							"[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getAllCharacters()));
				}
				
			} else if (index == 4) {
				if(noSex) {
					return new Response("Грубый секс", "[npc.Name] не заинтересована в сексе с вами!", null);
					
				} else if(wantsSex) {
					return new ResponseSex(rapePlay?"Игры в изнасилование (грубо)":"Грубый секс",
							"Ну, [npc.she] <i>сам</i> напросился!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", rapePlay?"AFTER_COMBAT_VICTORY_RAPE_ROUGH":"AFTER_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()));
					
				} else {
					return new ResponseSex("Изнасиловать [npc.herHim] (грубо)",
							"[npc.She] нужно наказать за то, что он так на тебя напал...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getHarpy()),
									Main.game.getPlayer().getCompanions(),
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getAllCharacters()));
				}
				
			} else if (index == 5) {
				if(!getHarpy().isAttractedTo(Main.game.getPlayer()) || getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("Подчиниться",
							"Ты не можешь поддаться [npc.herHim], ведь [npc.sheHasFull] не заинтересована в сексе с тобой!",
							null);
				} else {
					return new ResponseSex("Подчиниться",
							"Ты не совсем понимаешь, что делать... Возможно, лучше позволить [npc.name] решить, что будет дальше?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 7) {
				if(isStorm()) {
					return new Response("Поговорить",
							"Из-за бушующего шторма Арканы, [npc.name] сейчас заинтересован только в сексе с тобой!",
							null);
				}
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response("Поговорить", "Предав доверие [npc.namePos], [npc.she] больше никогда не захочет с тобой разговаривать.", null);
					
				} else {
					return new Response("Поговорить", "Поговорить с [npc.name] и спросить [npc.herHim], почему [npc.she] напал(ла) на тебя.", AFTER_COMBAT_VICTORY_TALK){
						@Override
						public void effects() {
							getHarpy().setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(getHarpy().setAffection(Main.game.getPlayer(), 10));
						}
					};
				}
				
			} else if (index == 8 && getHarpy().isAbleToSelfTransform()) {
				return new Response("Трансформировать [npc.herHim]",
						"Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getHarpy());
					}
				};
				
			} else if (index == 9 && getHarpy().isAbleToSelfTransform()) {
				return new Response("Быстрые трансформации",
						"Так как [npc.she] может превращать [npc.herself], у тебя в голове рождается несколько идей...(Ты вернешься к этим вариантам, когда закончишь превращать [npc.herHim].)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getHarpy(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Удалить персонажа",
						UtilText.parse(getHarpy(), "Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
						Main.game.banishNPC(getHarpy());
					}
				};
				
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!Main.game.isNonConEnabled() && (!getHarpy().isAttractedTo(Main.game.getPlayer()) || !getHarpy().isAttractedTo(companion))) {
					return new Response("Трио", UtilText.parse(companion, getHarpy(), "[npc2.Name] не заинтересована в сексе ни с тобой, ни с [npc.name]!"), null);
					
				} else if(!companion.isAttractedTo(getHarpy())) {
					return new Response(UtilText.parse(companion, "Трио"), UtilText.parse(companion, getHarpy(), "[npc.Name] не испытывает влечения к [npc2.name], так что она не захочет заняться сексом с [npc2.herHim]!"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Трио"),
							UtilText.parse(getHarpy(), companion, "Занимайтесь доминирующим сексом с [npc.name], и тогда [npc2.name] присоединится к веселью."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getHarpy()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_THREESOME", getHarpy(), companion));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getHarpy().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Отдать [npc.name]"), UtilText.parse(companion, getHarpy(), "[npc2.Name] не испытывает влечения к [npc.name], так что она не захочет переспать с [npc.herHim]!"), null);
					
				} else if(!companion.isAttractedTo(getHarpy())) {
					return new Response(UtilText.parse(companion, "Отдать [npc.name]"), UtilText.parse(companion, getHarpy(), "[npc.Name] не испытывает влечения к [npc2.name], так что она не захочет заняться сексом с [npc2.herHim]!"), null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Отдать [npc.name]"),
							UtilText.parse(companion, getHarpy(), "Сказать [npc.name], что [npc.she] может немного развлечься с [npc2.name] под твоим присмотром."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getHarpy()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getHarpy(), companion));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getHarpy())) {
					return new Response(UtilText.parse(companion, "Предложить [npc.name]"), UtilText.parse(companion, getHarpy(), "[npc.Name] не заинтересована в сексе с [npc2.name]!"), null);
					
				} else if(!getHarpy().isAttractedTo(companion)) {
					return new Response(UtilText.parse(companion, "Предложить [npc.name]"), UtilText.parse(companion, getHarpy(), "[npc2.Name] не заинтересована в сексе с [npc.name]!"), null);
					
				} else if(!companion.isAttractedTo(getHarpy()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(getHarpy(), companion, "Сразу видно, что [npc2.name] совсем не горит желанием трахаться с [npc.name], а заставить [npc2.herHim] ты всё равно не сможешь..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(getHarpy(), companion, "Сказать [npc.name], что [npc.she] может использовать [npc2.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getHarpy()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getHarpy(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getHarpy()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_TALK = new DialogueNode("Поговорить", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_VICTORY_TALK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Отпустить [npc.name].",
						Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	private static String applyTransformation(GameCharacter target,
			TransformativePotion potion,
			boolean forcedTF,
			FetishPotion fetishPotion,
			boolean forcedFetish) {
		
		StringBuilder sb = new StringBuilder();
		
		if(potion!=null && forcedTF) {
			sb.append(UtilText.parse(getHarpy(), target,
					"<p>[npc.Name] отступает, ухмыляясь на [npc2.name], пока [npc2.she] послушно [npc2.verb(swallow)] странную жидкость. [npc.speech(Отличная [npc2.girl]! Я превращу тебя в мою идеальную "+getHarpy().getPreferredBodyDescription("b")+"!)]"
					+ "</p>"));
			sb.append(getHarpy().applyPotion(potion, target));
		}
		
		if(fetishPotion!=null && forcedFetish) {
			sb.append(UtilText.parse(getHarpy(),
					"<p>С дьявольским восторгом в [npc.her] [npc.eyes], [npc.name] азартно выкрикивает: [npc.speech(Именно так, глотай всё до капли! Все эти изменения только к лучшему!)]</p>"));
			sb.append(getHarpy().applyPotion(fetishPotion, target));
		}
		return sb.toString();
	}

	public static final DialogueNode SURRENDER = new DialogueNode("", "", true) {
		public void applyPreParsingEffects() {
			AFTER_COMBAT_DEFEAT.applyPreParsingEffects();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "SURRENDER", getHarpy());		
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Поражение", "", true) {

		TransformativePotion potion = null;
		TransformativePotion companionPotion = null;
		FetishPotion fetishPotion = null;
		FetishPotion companionFetishPotion = null;
		
		public void applyPreParsingEffects() {
			transformationsApplied = false;
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getHarpy().generateTransformativePotion(Main.game.getPlayer());
				fetishPotion = getHarpy().generateFetishPotion(Main.game.getPlayer(), true);
			} else {
				potion = null;
				fetishPotion = null;
			}
			if(isCompanionDialogue()) {
				if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					companionPotion = getHarpy().generateTransformativePotion(getMainCompanion());
					companionFetishPotion = getHarpy().generateFetishPotion(getMainCompanion(), true);
				} else {
					companionPotion = null;
					companionFetishPotion = null;
				}
			}
		}
		
		@Override
		public String getDescription() {
			return "Вы были повержены [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_BETRAYED", getAllCharacters());
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getAllCharacters()));
			
			boolean forcedTF = getHarpy().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getHarpy().isUsingForcedFetish(Main.game.getPlayer());
			boolean companionForcedTF = isCompanionDialogue() && getHarpy().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getHarpy().isUsingForcedFetish(getMainCompanion());
			if((forcedTF && potion!=null)
					|| (forcedFetish && fetishPotion!=null)
					|| (companionForcedTF && companionPotion!=null)
					|| (companionForcedFetish && companionFetishPotion!=null)) {
				if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) { // Both TF:
					
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF", getAllCharacters()));
					}
	
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(companionPotion!=null && companionForcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_TF", getAllCharacters()));
					}
					
					return sb.toString();
					
				} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) { // Player TF:
					if(fetishPotion!=null && forcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_TF", getAllCharacters()));
					}
					return sb.toString();
					
				} else if(isCompanionDialogue()) { // Companion TF:
					if(companionFetishPotion!=null && companionForcedFetish) {
						if(potion!=null && forcedTF) {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF_AND_FETISH", getAllCharacters()));
						} else {
							sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_FETISH", getAllCharacters()));
						}
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_COMBAT_DEFEAT_COMPANION_SOLO_TF", getAllCharacters()));
					}
					return sb.toString();
				}
			}
			
			// If no transformations are going to be applied, just return content (and responses) as though it's the AFTER_COMBAT_TRANSFORMATION node:
			sb.append(AFTER_COMBAT_TRANSFORMATION.getContent());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.banishNPC(getHarpy());
						}
					};
				}
				return null;
			}

			// Response variables:
			boolean forcedTF = getHarpy().isUsingForcedTransform(Main.game.getPlayer());
			boolean forcedFetish = getHarpy().isUsingForcedFetish(Main.game.getPlayer());
			List<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(
					forcedTF && potion!=null
						?Fetish.FETISH_TRANSFORMATION_RECEIVING
						:null,
					forcedFetish && fetishPotion!=null
						?Fetish.FETISH_KINK_RECEIVING
						:null);
			CorruptionLevel applicableCorruptionLevel = forcedFetish && fetishPotion!=null
					?Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel()
					:Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
			boolean multiplePotions = applicableFetishes.size()>1;
			
			// Companion's response variables:
			boolean companionForcedTF = isCompanionDialogue() && getHarpy().isUsingForcedTransform(getMainCompanion());
			boolean companionForcedFetish = isCompanionDialogue() && getHarpy().isUsingForcedFetish(getMainCompanion());
			boolean companionMultiplePotions = (companionForcedTF && companionPotion!=null) && (companionForcedFetish && companionFetishPotion!=null);
			
			// Swallow/spit responses:
			if((!forcedTF || potion==null)
					&& (!forcedFetish || fetishPotion==null)
					&& (!companionForcedTF || companionPotion==null)
					&& (!companionForcedFetish || companionFetishPotion==null)) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);
				
			} else if(((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null))
						&& ((companionForcedTF || companionPotion!=null) && (companionForcedFetish || companionFetishPotion!=null))) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(!Collections.disjoint(Main.game.getPlayer().getFetishes(true), applicableFetishes)) {
						return new Response("Сплюнуть",
									"Благодаря вашему [style.boldFetish("+applicableFetishes.get(0).getName(Main.game.getPlayer())+")] фетиш, который ты обожаешь "+applicableFetishes.get(0).getShortDescriptor(Main.game.getPlayer())+" настолько, что не можете заставить себя выплюнуть трансформирующую "+(multiplePotions?"зелья":"зелье")+"!",
								null);
					} else {
						return new Response("Сплюнуть", 
								UtilText.parse(getMainCompanion(),
										"Выплюнуть "+(multiplePotions?"зелья":"зелье")+". ([npc.Name], скорее всего, решит "
													+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()?"[style.boldTfGeneric(проглотить)]":"[style.boldMinorBad(выплюнуть)]")
													+" собственный [npc.her] "+(companionMultiplePotions?"зелья":"зелье")+"!)"),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_BOTH_SPIT", getAllCharacters()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					return new Response("Проглотить",
							UtilText.parse(getMainCompanion(),
								"Проглотить "+(multiplePotions?"зелья":"зелье")+". ([npc.Name], скорее всего, решит "
											+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()?"[style.boldTfGeneric(проглотить)]":"[style.boldMinorBad(выплюнуть)]")
											+" собственный [npc.her] "+(companionMultiplePotions?"зелья":"зелье")+"!)"),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
							
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Сплюнуть (оба)");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сплюнуть (оба)",
								UtilText.parse(getMainCompanion(),
									"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b> фетиш, тебе так нравится превращаться, что ты даже не можешь заставить себя выплюнуть трансформацию "+(multiplePotions?"зелья":"зелье")+", равно как и велеть [npc.name] сделать то же самое!"),
								null);
						
					} else {
						return new Response("Сплюнуть (оба)",
								UtilText.parse(getMainCompanion(),
										"Выплюнуть "+(multiplePotions?"зелья":"зелье")+", и велеть [npc.name] сделать то же самое."
										+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
												?" (Однако, раз у [npc.name] есть "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" фетиш, [npc.sheIsFull] вряд ли тебя послушает!)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								transformationsApplied = true;
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT_REFUSED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT", getAllCharacters()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					return new Response("Проглотить (оба)",
							UtilText.parse(getMainCompanion(),
									"Проглотить "+(multiplePotions?"зелья":"зелье")+", и велеть [npc.name] сделать то же самое."
									+ (getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?" (Однако, поскольку у [npc.name] отрицательное желание к "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" фетиш, [npc.sheIsFull] вряд ли тебя послушает!)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW_REFUSED", getAllCharacters()));
							}
						}
					};
				}
				
			} else if((forcedTF && potion!=null) || (forcedFetish && fetishPotion!=null)) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сплюнуть",
								"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> фетиш, тебе так нравится превращаться, что ты даже не можешь заставить себя выплюнуть трансформацию "+(multiplePotions?"зелья":"зелье")+"!",
								null);
					} else {
						return new Response("Сплюнуть", "Выплюнуть зелье.", AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects() {
								transformationsApplied = true;
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SPIT", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 2) {
					return new Response("Проглотить",
							"Проглотить "+(multiplePotions?"зелья":"зелье")+".",
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_SWALLOW", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(applyTransformation(Main.game.getPlayer(), potion, forcedTF, fetishPotion, forcedFetish));
						}
					};
					
				} else if (index == 6 && isCompanionDialogue()) {
					return new Response("Проглотить (оба)",
							UtilText.parse(getMainCompanion(),
									getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"[npc.Name] не заставляют пить никакие трансформирующие зелья!"
										:"Так как рот [npc.namePos] заблокирован, [npc.she] не может пить никакие трансформирующие зелья!"),
							null);
					
				}  else if (index == 7 && isCompanionDialogue()) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Сплюнуть (оба)");
					}
					return new Response("Сплюнуть (оба)",
							UtilText.parse(getMainCompanion(),
									getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"[npc.Name] не заставляют пить никакие трансформирующие зелья!"
										:"Так как рот [npc.namePos] заблокирован, [npc.she] не может пить никакие трансформирующие зелья!"),
							null);
				}
				
			} else if(isCompanionDialogue()) {
				if (index == 1) {
					return new Response("Сплюнуть",
							UtilText.parse(getMainCompanion(),
									Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"Тебя не заставляют пить никакие трансформирующие зелья!"
										:"Так как твой рот заблокирован, ты не можешь пить никакие трансформирующие зелья!"),
							null);
					
				} else if (index == 2) {
					return new Response("Проглотить",
							UtilText.parse(getMainCompanion(),
									Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
										?"Тебя не заставляют пить никакие трансформирующие зелья!"
										:"Так как твой рот заблокирован, ты не можешь пить никакие трансформирующие зелья!"),
							null);
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Приказать выплюнуть");
					}
					return new Response("Приказать выплюнуть",
							UtilText.parse(getMainCompanion(), "Приказать [npc.name] выплюнуть "+(companionMultiplePotions?"зелья":"зелье")+"!"
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (Однако, раз у [npc.name] есть "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" фетиш, [npc.sheIsFull] вряд ли тебя послушает!)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT_REFUSED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "TF_COMPANION_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					return new Response("Приказать проглотить",
							UtilText.parse(getMainCompanion(),
								"Приказать [npc.name] проглотить "+(companionMultiplePotions?"зелья":"зелье")+"."
								+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
										?" (Однако, так как [npc.she] не любит превращения, [npc.sheIsFull] вряд ли тебя послушает!)"
										:"")),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							transformationsApplied = true;
							if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(applyTransformation(getMainCompanion(), companionPotion, companionForcedTF, companionFetishPotion, companionForcedFetish));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "ORDER_SWALLOW_REFUSED", getAllCharacters()));
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("Превращен(а)", "", true) {
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					if(getHarpy().isAttractedTo(getMainCompanion())) {
						if(getHarpy().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_BOTH", getAllCharacters());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX_BOTH", getAllCharacters());
						}
						
					} else {
						if(getHarpy().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_PLAYER_SOLO", getAllCharacters());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX_SOLO", getAllCharacters());
						}
					}
					
				} else if(getHarpy().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					if(getHarpy().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_COMPANION", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX_COMPANION", getAllCharacters());
					}
				}
				
			} else {
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					if(getHarpy().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "RAPE_PLAYER", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "OFFER_SEX", getAllCharacters());
					}
				}
			}

			if(transformationsApplied) {
				return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "NO_SEX_POST_TRANSFORM", getAllCharacters());
			}
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "NO_SEX", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getHarpy()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getHarpy().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getHarpy().isWillingToRape());
				
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) {
					if(getHarpy().isAttractedTo(getMainCompanion())) { // Threesome sex:
						if (index == 1) {
							return new ResponseSex("Секс",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name] навязывает [npc.herself] тебе"+(companionSex?UtilText.parse(getMainCompanion(), " и [npc.name]"):"")+"..."
												:"Сказать [npc.name], что ты"+(companionSex?UtilText.parse(getMainCompanion(), " и [npc.name]"):"")+" хочет заняться сексом с [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
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
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_THREESOME", getAllCharacters()));
							
						} else if (index == 2) {
							return new ResponseSex("Жажда секса",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name] навязывает [npc.herself] тебе"+(companionSex?UtilText.parse(getMainCompanion(), " и [npc.name]"):"")+"..."
												:"Сказать [npc.name], что ты"+(companionSex?UtilText.parse(getMainCompanion(), " и [npc.name]"):"")+" с радостью займется сексом с [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
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
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_THREESOME", getAllCharacters()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Сопротивляться сексу",
									UtilText.parse(getHarpy(), "[npc.Name] навязывает [npc.herself] тебе"+(companionSex?UtilText.parse(getMainCompanion(), " и [npc.name]"):"")+"..."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
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
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getAllCharacters()));
							
						} else if (index == 4 && !getHarpy().isWillingToRape()) {
							return new Response("Отказаться",
									UtilText.parse(getHarpy(), "Отказаться от секса с [npc.name] и продолжить свой путь."),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_THREESOME", getAllCharacters()));
								}
							};
						}
						return null;
						
					} else { // Solo sex with player:
						if (index == 1) {
							return new ResponseSex("Секс",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name] навязывает вам [npc.herself]..."
												:"Сказать [npc.name], что вы хотите заняться сексом с [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO", getAllCharacters()));
							
						} else if (index == 2) {
							return new ResponseSex("Жажда секса",
									UtilText.parse(getHarpy(),
											getHarpy().isWillingToRape()
												?"[npc.Name] навязывает вам [npc.herself]..."
												:"Сказать [npc.name], что вы с радостью займетесь сексом с [npc.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO", getAllCharacters()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Сопротивляться сексу",
									UtilText.parse(getHarpy(), "[npc.Name] навязывает вам [npc.herself]..."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO_RESIST", getAllCharacters()));
							
						} else if (index == 4 && !getHarpy().isWillingToRape()) {
							return new Response("Отказаться",
									UtilText.parse(getHarpy(), "Отказаться от секса с [npc.name] и продолжить свой путь."),
									Main.game.getDefaultDialogue(false)) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_SEX_SOLO", getAllCharacters()));
								}
							};
						}
						return null;
					}
					
				} else if(getHarpy().isAttractedTo(getMainCompanion())
						&& Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) { // Solo sex with companion:
					if(getHarpy().isWillingToRape()) {
						if (index == 1) {
							return new ResponseSex("Смотреть на изнасилование",
									UtilText.parse(getHarpy(), getMainCompanion(),
											"Вам остается только наблюдать, как [npc.name] насилует [npc2.name], навязывая [npc.herself]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO_COMPANION_RAPE", getAllCharacters()));
						}
						
					} else if(companionHappyToHaveSex) {
						if (index == 1) {
							return new ResponseSex("Смотреть на секс",
									UtilText.parse(getHarpy(), getMainCompanion(),
											"Вам остается только наблюдать, как [npc2.name] с радостью соглашается позволить [npc.name] трахать [npc2.herHim]."),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(getHarpy()),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_SOLO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 1) {
						return new Response(
								UtilText.parse(getMainCompanion(), "[npc.Name] отказывается"),
								UtilText.parse(getHarpy(), getMainCompanion(), "Похоже, [npc2.name] собирается отказаться заниматься сексом с [npc.name]."),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_SEX_SOLO_COMPANION", getAllCharacters()));
							}
						};
					}
				}
				
			} else {
				if(getHarpy().isAttractedTo(Main.game.getPlayer())) { // Solo sex with player:
					if (index == 1) {
						return new ResponseSex("Секс",
								UtilText.parse(getHarpy(),
										getHarpy().isWillingToRape()
											?"[npc.Name] навязывает вам [npc.herself]..."
											:"Сказать [npc.name], что вы хотите заняться сексом с [npc.herHim]."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX", getAllCharacters()));
						
					} else if (index == 2) {
						return new ResponseSex("Жажда секса",
								UtilText.parse(getHarpy(),
										getHarpy().isWillingToRape()
											?"[npc.Name] навязывает вам [npc.herself]..."
											:"Сказать [npc.name], что вы с радостью займетесь сексом с [npc.herHim]."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_EAGER),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX", getAllCharacters()));
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("Сопротивляться сексу",
								UtilText.parse(getHarpy(), "[npc.Name] навязывает вам [npc.herself]..."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(getHarpy()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "START_DEFEATED_SEX_RESIST", getAllCharacters()));
						
					} else if (index == 4 && !getHarpy().isWillingToRape()) {
						return new Response("Отказаться",
								UtilText.parse(getHarpy(), "Отказаться от секса с [npc.name] и продолжить свой путь."),
								Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_REFUSE_SEX", getAllCharacters()));
							}
						};
					}
					return null;
				}
			}
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "DEFEATED_NO_SEX", getAllCharacters()));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь насладился, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}

		@Override
		public String getContent() {
			if((getHarpy().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Main.sex.getNumberOfOrgasms(getHarpy()) >= getHarpy().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getAllCharacters());
				}
				
			} else {
				if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_VICTORY_RAPE", getAllCharacters());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Ничто не мешает вам полакомиться одеждой и вещами [npc.namePos]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getHarpy(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Удалить персонажа",
						UtilText.parse(getHarpy(), "Напугать [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
						AFTER_COMBAT_VICTORY){
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
						Main.game.banishNPC(getHarpy());
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
			return UtilText.parseFromXMLFile("encounters/dominion/harpyAttack", "AFTER_SEX_DEFEAT", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", AFTER_SEX_VICTORY) {
					@Override
					public void effects() {
						if(getHarpy().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getHarpy());
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			return null;
		}
	};
}
