package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.5.5
 * @author Innoxia
 */
public class TunnelImpsDialogue {

	private static TransformativePotion potion = null;
	private static TransformativePotion companionPotion = null;
	
	public static List<GameCharacter> getImpGroup() {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof ImpAttacker));
		Collections.sort(guards, (a, b)->b.getLevel()-a.getLevel());
		return guards;
	}
	
	public static ImpAttacker getImpLeader() {
		return (ImpAttacker) getImpGroup().get(0);
	}

	public static void banishImpGroup() {
		for(GameCharacter imp : getImpGroup()) {
			if(!imp.isSlave()) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		if(isCompanionDialogue()) {
			List<GameCharacter> allCharacters = new ArrayList<>();
			allCharacters.add(getMainCompanion());
			allCharacters.addAll(getImpGroup());
			return allCharacters;
			
		} else {
			return getImpGroup();
		}
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static String getImpEncounterId() {
		StringBuilder idSB = new StringBuilder();
		if(isCompanionDialogue()) {
			idSB.append("Спутники");
		}
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
			// Alpha imp group encounter:
			idSB.append("Альфа");
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
			// Demon group encounter:
			idSB.append("Демон");
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
			// Female imps encounter:
			idSB.append("Женщины");
			
		} else {
			// Male imps encounter:
			idSB.append("Мужчины");
		}
		
		return idSB.toString();
	}
	
	public static final DialogueNode IMP_ATTACK = new DialogueNode("Банда бесов", "Группа бесов атакует!", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if (index == 1) {
					return new ResponseCombat("Сразиться", "Покрой себя и сразись с этой бандой бесов!", getImpLeader(), getImpGroup(), null);
					
				} else if (index == 2) {
					return new Response("Предложить деньги", "Этих бесов не интересуют твои деньги! Тебе придется либо сражаться, либо отдаться им на милость...", null);
					
				} else if (index == 3) {
					return new Response("Предложить свое тело",
							"Предложи свое тело бесам, чтобы избежать жестокого столкновения.",
							IMP_ATTACK_OFFER_BODY,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseCombat("Сразиться", UtilText.parse(getMainCompanion(), "Покрой себя и с помощью [npc.name] сразись с бесами!"), getImpLeader(), getImpGroup(), null);
					
				} else if (index == 2) {
					return new Response("Предложить деньги", "Этих бесов не интересуют твои деньги! Тебе придется либо сражаться, либо отдаться им на милость...", null);
					
				} else if (index == 3) {
					return new Response("Предложить свое тело (в одиночку)",
							UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и предложить свое тело бесам, чтобы избежать кровавой стычки."),
							IMP_ATTACK_OFFER_BODY,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null){
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
					
				} else if (index == 4) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Предложить секс втроем"),
								UtilText.parse(companion, "Вы видите, что [npc.name] совсем не горит желанием трахаться с бесами, и вы не можете заставить [npc.herHim] сделать это..."),
								null);
						
					} else {
						return new Response("Предложить секс втроем",
								UtilText.parse(companion, "Предложить бесам совокупление и с вами, и с [npc.name], чтобы избежать кровавой стычки."),
								IMP_ATTACK_OFFER_THREESOME,
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(companion, "Вы видите, что [npc.name] совсем не горит желанием трахаться с бесами, и вы не можете заставить [npc.herHim] сделать это..."),
								null);
						
					} else {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(companion, "Сказать бесам, что они могут использовать тело [npc.namePos], чтобы избежать кровавой стычки."),
								IMP_ATTACK_OFFER_COMPANION) {
							@Override
							public void effects() {
								if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else {
					return null;
				}
			
			}
		}
	};
	
	public static final DialogueNode IMP_ATTACK_OFFER_BODY = new DialogueNode("Банда бесов", "", true) {

		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_BODY", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сплюнуть",
								"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> фетиш: ты так обожаешь превращения, что даже не можешь заставить себя выплюнуть трансформирующую жидкость!",
								null);
					} else {
						return new Response("Сплюнуть", "Выплюнуть зелье.", AFTER_COMBAT_TRANSFORMATION_SOLO) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters())); // Re-use TF refuse dialogue
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

					return new Response("Проглотить",
							(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
								?"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище, а также делает его женственным и заставляет грудь вырасти..."
								:"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище...",
							AFTER_COMBAT_TRANSFORMATION_SOLO,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()) // Re-use TF refuse dialogue
									);
							Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));
						}
					};
				}
				
			} else {
				return AFTER_COMBAT_TRANSFORMATION_SOLO.getResponse(responseTab, index); // Sex responses
			}
			
			return null;
		}
	};

	public static final DialogueNode IMP_ATTACK_OFFER_COMPANION = new DialogueNode("Банда бесов", "", true) {

		public void applyPreParsingEffects() {
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(companionPotion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					return new Response("Приказать выплюнуть",
							UtilText.parse(getMainCompanion(), "Приказать [npc.name] выплюнуть зелье, которое бесы пытаются заставить выпить [npc.herHim]."
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (Однако, раз у [npc.name] есть "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" фетиш, [npc.sheIsFull] вряд ли тебя послушает!)"
											:"")),
							AFTER_OFFER_COMPANION_TRANSFORMATION) {
						@Override
						public void effects(){
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT_COMPANION_SWALLOWS", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
	
						return new Response("Приказать проглотить",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"Приказать [npc.name] проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище, а также делает его женственным и заставляет грудь вырасти."
										:"Приказать [npc.name] проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и вагину.")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?" (Однако, так как [npc.she] не любит превращения, [npc.sheIsFull] вряд ли тебя послушает!)"
											:"")),
								AFTER_OFFER_COMPANION_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
	
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW_COMPANION_SPITS", getAllCharacters()));
								}
							}
						};
				}
				
			} else {
				return AFTER_OFFER_COMPANION_TRANSFORMATION.getResponse(responseTab, index); // Sex responses
			}
			
			return null;
		}
	};
	
	
	public static final DialogueNode IMP_ATTACK_OFFER_THREESOME = new DialogueNode("Банда бесов", "", true) {

		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null && companionPotion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сплюнуть",
									"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b> фетиш: ты так обожаешь превращения, что даже не можешь заставить себя выплюнуть трансформирующую жидкость!",
								null);
					} else {
						return new Response("Сплюнуть", 
								UtilText.parse(getMainCompanion(),
										"Выплюнуть зелье. ([npc.Name] решит, выплюнуть или проглотить собственное зелье [npc.her] [npc.herself].)"), AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
									
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters()) // Re-use description
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT", getAllCharacters()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					return new Response("Проглотить",
							UtilText.parse(getMainCompanion(),
								((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
									?"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище, а также делает его женственным и заставляет грудь вырасти..."
									:"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище...")
								+ " ([npc.Name] решит, выплюнуть или проглотить собственное зелье [npc.her] [npc.herself].)"),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
							} else {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SPITS", getAllCharacters()));
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
										+"</b> фетиш! Ты так обожаешь трансформации, что не можешь заставить себя выплюнуть преображающую жидкость или велеть [npc.name] сделать то же самое!"),
								null);
						
					} else {
						return new Response("Сплюнуть (оба)",
								UtilText.parse(getMainCompanion(), "Выплюнуть зелье и велеть [npc.name] сделать то же самое."
										+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
												?" (Однако, раз у [npc.name] есть "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" фетиш, [npc.sheIsFull] вряд ли тебя послушает!)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
									
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SPIT", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT_WITH_ORDER", getAllCharacters()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

						return new Response("Проглотить (оба)",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и вагину, а также становится женственным и отращивает грудь."
										:"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и вагину.")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?"Проглотив, велеть [npc.name] сделать то же самое. (Однако, так как [npc.she] не любит трансформации, [npc.sheIsFull] вряд ли тебя послушает!)"
											:" Проглотив, велеть [npc.name] сделать то же самое...")),
								AFTER_COMBAT_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									}
								}
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								}
							}
						};
				}
				
			} else if(potion==null && companionPotion==null) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);  // Sex responses
				
			} else if(potion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сплюнуть",
								"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> фетиш: ты так обожаешь превращения, что даже не можешь заставить себя выплюнуть трансформирующую жидкость!",
								null);
					} else {
						return new Response("Сплюнуть", "Выплюнуть зелье.", AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					return new Response("Проглотить",
							(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
								?"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище, а также делает его женственным и заставляет грудь вырасти..."
								:"Проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище...",
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));
						}
					};
					
				} else if (index == 6) {
					return new Response("Проглотить (оба)",
							UtilText.parse(getMainCompanion(), "Поскольку бесы не могут добраться до рта [npc.namePos], они не пытаются заставить [npc.herHim] выпить их преображающее зелье."),
							null);
					
				}  else if (index == 7) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Сплюнуть (оба)");
					}
					return new Response("Сплюнуть (оба)",
							UtilText.parse(getMainCompanion(), "Поскольку бесы не могут добраться до рта [npc.namePos], они не пытаются заставить [npc.herHim] выпить их преображающее зелье."),
							null);
				}
				
			} else {
				if (index == 1) {
					return new Response("Сплюнуть", UtilText.parse(getMainCompanion(),"Поскольку бесам не удается пробраться тебе в рот, они игнорируют тебя и сосредотачиваются на превращении [npc.name]!"), null);
					
				} else if (index == 2) {
					return new Response("Проглотить", UtilText.parse(getMainCompanion(),"Поскольку бесам не удается пробраться тебе в рот, они игнорируют тебя и сосредотачиваются на превращении [npc.name]!"), null);
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("Приказать выплюнуть");
					}
					return new Response("Приказать выплюнуть",
							UtilText.parse(getMainCompanion(), "Приказать [npc.name] выплюнуть зелье, которое бесы пытаются заставить выпить [npc.herHim]."
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (Однако, раз у [npc.name] есть "+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+" фетиш, [npc.sheIsFull] вряд ли тебя послушает!)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_SPIT_WITH_ORDER", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
	
						return new Response("Приказать проглотить",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"Приказать [npc.name] проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и влагалище, а также делает его женственным и заставляет грудь вырасти."
										:"Приказать [npc.name] проглотить зелье, которое, если верить бесам, заставляет выпившего отрастить и пенис, и вагину.")
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
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
	
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
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
			return "Ты одолел бесов!";
		}

		@Override
		public String getContent() {
			if(getImpGroup().isEmpty()) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_ALL_ENSLAVED", getImpGroup());
				
			} else if(getImpGroup().size()==1) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED_ONE", getImpGroup());
				
			} else if(getImpGroup().size()<4) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED", getImpGroup());
			}
			return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGroup().isEmpty()) {
				if(index==0) {
					return "Взаимодействие";
					
				} else if(index==1) {
					return "Инвентарь";
					
				} else if(index==2) {
					return "Трансформации";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGroup().isEmpty()) {
				if(index==1) {
					return new Response("Продолжить", "Раз уж ты поработил всех бесов, остается только идти дальше своим путем...", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Продолжить", "Оставить бесов и продолжить путь...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_CONTINUE", getImpGroup()));
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Секс",
								"Ну, они сами <i>напрашиваются</i>!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("Нежный секс",
								"Ну, они сами <i>напрашиваются</i>!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Грубый секс",
								"Ну, они сами <i>напрашиваются</i>!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Подчиниться",
								"Ты не совсем понимаешь, что делать дальше... Возможно, лучше позволить бесам самим решить, что делать...",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
									Main.game.setResponseTab(0);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Продолжить", "Оставить бесов и продолжить путь...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Соло-секс",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы трахаетесь с бесами."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("Соло-секс (Нежный)",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы нежно трахаетесь с бесами."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Соло-секс (Грубый)",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы грубо трахаетесь с бесами."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Соло-сабмиссив",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы подчиняетесь бесам, позволяя им доминировать над вами во время секса."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Групповой секс",
									UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с бесами, а так как [npc.sheIs] не рабыня, вы не можете заставить [npc.herHim] сделать это..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Групповой секс"),
									UtilText.parse(companion, "Занимайтесь доминирующим сексом с бесами и заставьте [npc.name] присоединиться к веселью."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGroup(),
									null,
									null,
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX", getImpGroup()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Групповое подчинение",
									UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с бесами, а так как [npc.sheIs] не рабыня, вы не можете заставить [npc.herHim] сделать это..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Групповое подчинение"),
									UtilText.parse(companion, "Заставьте [npc.name] подчиниться бесам вместе с вами, позволяя им доминировать над вами обоими."),
									true,
									false,
									getImpGroup(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getImpGroup()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Отдать [npc.name]"),
									UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с бесами, а так как [npc.sheIs] не рабыня, вы не можете заставить [npc.herHim] сделать это..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Отдать [npc.name]"),
									UtilText.parse(companion, "Скажите [npc.name], что [npc.she] может немного повеселиться с бесами, пока вы наблюдаете."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGroup(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GIVE_TO_COMPANION", getImpGroup()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
									UtilText.parse(companion, "Вы видите, что [npc.name] совсем не заинтересована в сексе с бесами, и не можете заставить [npc.herHim] сделать это..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
									UtilText.parse(companion, "Передайте [npc.name] бесам и наблюдайте, как они трахают [npc.herHim]."),
									true,
									false,
									getImpGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_OFFER_COMPANION", getImpGroup())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
									Main.game.setResponseTab(0);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Поражение", "", true) {
		
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
			if(isCompanionDialogue() && getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getDescription() {
			return "Вы были повержены бесами!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				return IMP_ATTACK_OFFER_THREESOME.getResponse(responseTab, index);
			} else {
				return IMP_ATTACK_OFFER_BODY.getResponse(responseTab, index);
			}
		}
	};
	

	public static final DialogueNode AFTER_OFFER_COMPANION_TRANSFORMATION = new DialogueNode("Банда бесов", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Смотреть",
						UtilText.parse(getMainCompanion(), "Наблюдайте, как бесы трахают [npc.name]..."),
						true,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(getMainCompanion()),
						null,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						AFTER_SEX_WATCHING_COMPANION,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_START_SEX", getAllCharacters()));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION_SOLO = new DialogueNode("Банда бесов", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Секс",
						"Позвольте банде бесов уложить вас в нужную позу...",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Жажда секса",
						"С готовностью позволь толпе бесов усадить тебя в нужное положение...",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Сопротивляться сексу",
						"Попытайся сопротивляться, пока толпа бесов усаживает тебя в нужное положение...",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("Банда бесов", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Секс",
						"Позвольте банде бесов уложить вас в нужную позу...",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Жажда секса",
						"С готовностью позволь толпе бесов усадить тебя в нужное положение...",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Сопротивляться сексу",
						"Попытайся сопротивляться, пока толпа бесов усаживает тебя в нужное положение...",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь повеселился, можно отступить и оставить бесов приходить в себя и расходиться.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "AFTER_VICTORY_SEX", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImpGroup();
						}
					};
				}
				
			} else if(responseTab==1) {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
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
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGroup()) {
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_WATCHING_COMPANION = new DialogueNode("Завершено", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getMainCompanion(), "Бесы закончили с [npc.name]...");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_SEX_WATCHING_COMPANION", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGroup()) {
							if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && getMainCompanion().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								getMainCompanion().addDirtySlot(InventorySlot.HEAD);
							}
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
