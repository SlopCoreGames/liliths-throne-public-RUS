package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.7.8
 * @author Innoxia
 */
public class AlleywayProstituteDialogue {
	
	private static boolean inApartment = false;
	private static boolean hadSex = false;
	
	/**
	 * Generate an estimate for the remaining value of the prostitute's fine.
	 * DOES NOT use randomness, so that the value is repeatable without requiring
	 * storage (and may change as the player interacts with them).
	 */
	public static int getModifiedFineAmount(GameCharacter character) {
		/*
		 * Estimate the maximum number of times they've had sex as an illegal prostitute:
		 * - percentageNonProstitute = sexCount ^ -0.33
		 *          1 -> 1.00
		 *         10 -> 0.47
		 *        100 -> 0.22
		 *       1000 -> 0.10
		 * - percentageProstitute = 1 - percentageNonProstitute
		 *          1 -> 0.00
		 *         10 -> 0.53
		 *        100 -> 0.78
		 *       1000 -> 0.90
		 * - countProstitute = sexCount * percentageProstitute
		 *          1 ->   0
		 *         10 ->   5
		 *        100 ->  78
		 *       1000 -> 897
		 */
		int maxSexCountAsProstitute = (int)((1 - Math.pow(character.getTotalSexConsensualCount(), -0.33)) * character.getTotalSexConsensualCount());
		
		/*
		 * Assume the profits are split:
		 * - 60% for food, pills, clothing, rent
		 * - 20% for 'protection' money
		 * - 5% of customers don't pay (10% if physically weak, or naive)
		 */
		double savingsPercentage = character.hasPersonalityTrait(PersonalityTrait.NAIVE) ? 0.10 : 0.15;
		
		/*
		 * For simplicity, assume threesomes are already accounted-for within sex-count.
		 */
		int maxSavings = (int)(maxSexCountAsProstitute * prostitutePrice(false) * savingsPercentage);
		
		/*
		 * We don't actually know how long ago (in lore) they got into debt
		 * So adjust their savings, so that it doesn't exceed the debt amount
		 * (to prevent issues where the effective fine is negative)
		 *        0 ->    0
		 *     1000 -> 1500
		 *     5000 -> 4500
		 *    10000 -> 6000
		 *   100000 -> 8571
		 * 13212918 -> 8997 (this is the current theoretical maximum -- 100yr old, 500sex/yr, max price of 2215)
		 */
		int fineAmount = Main.game.getDialogueFlags().getProstituteFine();
		int actualSavings = (int)(maxSavings * (fineAmount * 0.9) / (maxSavings + (fineAmount / 2)));
		
		return fineAmount - actualSavings;
	}
	
	private static int prostitutePrice(boolean threesome) {
		return getProstitute().getProstitutePrice() * (threesome?2:1);
	}
	
	private static NPC getProstitute() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isStorm() {
		return getProstitute().isVulnerableToArcaneStorm()
				&& Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
	}
	
	public static final DialogueNode ALLEY_PROSTITUTE = new DialogueNode("Проститутка", "", true) {
		@Override
		public void applyPreParsingEffects() {
			inApartment = false;
			hadSex = false;
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(false)), true);
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(true)), false);
		}
		@Override
		public String getContent() {
			if(isStorm()) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_STORM", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = isStorm()?0:prostitutePrice(false);
			int threesomeCost = isStorm()?0:prostitutePrice(true);
			
			if (index == 1) {
				return new Response("Уйти", "Тебя совсем не интересует секс с проституткой. Прогуляйся по [npc.herHim] и продолжай свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(getProstitute().isVisiblyPregnant()){
							getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_LEAVE", getProstitute()));
					}
				};
				
			} else if (index == 2 && !hadSex) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Доминант ("+UtilText.formatAsMoney(cost, "охват")+")",
							UtilText.parse(getProstitute(), "У тебя нет "+cost+" огней, так что ты не можешь позволить себе секс с [npc.name]."),
							null);
					
				} else {
					return new ResponseSex("Доминант ("+UtilText.formatAsMoney(cost, "охват")+")",
							UtilText.parse(getProstitute(),
									cost==0
										?"[npc.Name] так возбуждена, что [npc.she] не возьмет с тебя ни единого огня за секс с [npc.herHim]!"
										:("Заплатить [npc.name] "+cost+" огней за доминантный секс с [npc.herHim].")),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getProstitute()),
							null,
							null) {
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.equals(getProstitute())) {
										return SexPace.SUB_NORMAL;
									}
									return super.getStartingSexPaceModifier(character);
								}
							},
							AFTER_SEX_PAID,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_DOM_SEX", getProstitute())) {
						@Override
						public void effects() {
							hadSex = true;
							inApartment = !isStorm();
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							if(cost>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
								getProstitute().incrementMoney(cost);
							}
						}
					};
				}
				
			} else if (index == 3 && !hadSex) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Сабмиссив ("+UtilText.formatAsMoney(cost, "охват")+")",
							UtilText.parse(getProstitute(), "У тебя нет "+cost+" огней, так что ты не можешь позволить себе секс с [npc.name]."),
							null);
					
				} else {
					return new ResponseSex("Сабмиссив ("+UtilText.formatAsMoney(cost, "охват")+")",
							UtilText.parse(getProstitute(),
									cost==0
										?"[npc.Name] так возбуждена, что [npc.she] не возьмет с тебя ни единого огня за секс с [npc.herHim]!"
										:("Заплатить [npc.name] "+cost+" огней, чтобы заняться подчиненным сексом с [npc.herHim].")),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null) {
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.equals(getProstitute())) {
										return SexPace.DOM_NORMAL;
									}
									return super.getStartingSexPaceModifier(character);
								}
							},
							AFTER_SEX_PAID,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_SUB_SEX", getProstitute())) {
						@Override
						public void effects() {
							hadSex = true;
							inApartment = !isStorm();
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							if(cost>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
								getProstitute().incrementMoney(cost);
							}
						}
					};
				}
				
			} else if(index == 4) {
				if(!getProstitute().hasFlag(NPCFlagValue.prostituteQuestioned)) {
					return new Response("Допрос",
							UtilText.parse(getProstitute(), "Спросить [npc.name], почему [npc.sheIs] работает в переулках. В конце концов, на главных улицах Dominion было бы куда безопаснее..."),
							ALLEY_PROSTITUTE_QUESTION) {
						@Override
						public void effects() {
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							getProstitute().addFlag(NPCFlagValue.prostituteQuestioned);
						}
					};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					if(RedLightDistrict.isSpaceForMoreProstitutes()) {
						return new Response("Поцелуй Ангела",
								UtilText.parse(getProstitute(), "Сказать [npc.name], что [npc.she] сможет встать на правильный путь, если [npc.she] согласится прийти и работать в Angel's Kiss."),
								ALLEY_PROSTITUTE_ANGELS_KISS) {
							@Override
							public void effects() {
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								Main.game.getPlayer().incrementKarma(25);
								getProstitute().setDescription(UtilText.parse(getProstitute(), "Сначала вы нашли [npc.name] в переулках Dominion, где [npc.she] незаконно продавала тело [npc.her]. Вы предложили [npc.herHim] шанс переехать и работать в Angel's Kiss; предложение, которое [npc.she] с радостью приняла."));
								Main.game.getTextEndStringBuilder().append(getProstitute().incrementAffection(Main.game.getPlayer(), 50));
								getProstitute().setRandomUnoccupiedLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_BEDROOM, true);
							}
						};
					} else {
						return new Response("Поцелуй Ангела", "В Angel's Kiss нет свободных мест для еще одной проститутки...", null);
					}
				}
			}
			
			if(Main.game.getPlayer().hasCompanions()) {
				if(index == 6 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("Тройничок ("+UtilText.formatAsMoney(threesomeCost, "охват")+")",
								UtilText.parse(getProstitute(), "Ваша [com.companion] не испытывает влечения к [npc.name], поэтому она неохотно вступит в секс с [npc.herHim]."),
								null);
						
					} else if(Main.game.getPlayer().getMoney()<threesomeCost) {
						return new Response("Тройничок ("+UtilText.formatAsMoney(threesomeCost, "охват")+")",
								UtilText.parse(getProstitute(), "У тебя нет "+threesomeCost+" огней, так что вы не можете позволить себе тройничок с [com.name] и [npc.name]."),
								null);
						
					} else {
						return new ResponseSex("Тройничок ("+UtilText.formatAsMoney(threesomeCost, "охват")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name] так возбуждена, что [npc.she] не возьмет с тебя ни единого огня за секс с [npc.herHim]!"
											:("Заплатить [npc.name] "+threesomeCost+" огней, чтобы заняться тройнишком с тобой и [com.name].")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(
												Main.game.getPlayer(),
												Main.game.getPlayer().getMainCompanion()),
										Util.newArrayListOfValues(getProstitute()),
								null,
								null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_THREESOME", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(threesomeCost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-threesomeCost));
									getProstitute().incrementMoney(threesomeCost);
								}
							}
						};
					}
					
				} else if (index == 7 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("Доминант(ка) ([com.Name]) ("+UtilText.formatAsMoney(cost, "охват")+")",
								UtilText.parse(getProstitute(), "Ваша [com.companion] не испытывает влечения к [npc.name], поэтому она неохотно вступит в секс с [npc.herHim]."),
								null);
						
					} else if(Main.game.getPlayer().getMoney()<cost) {
						return new Response("Доминант(ка) ([com.Name]) ("+UtilText.formatAsMoney(cost, "охват")+")",
								UtilText.parse(getProstitute(), "У тебя нет "+cost+" огней, так что тебе не по карману заплатить, чтобы [com.name] доминантно трахала [npc.name]."),
								null);
						
					} else {
						return new ResponseSex("Доминант(ка) ([com.Name]) ("+UtilText.formatAsMoney(cost, "охват")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name] так возбуждена, что [npc.she] не возьмет с тебя ни копейки за то, что ты позволишь [com.name] доминантно трахать [npc.herHim]!"
											:("Заплатить [npc.name] "+cost+" огней, чтобы позволить [com.name] доминантно трахать [npc.herHim].")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer().getMainCompanion()),
										Util.newArrayListOfValues(getProstitute()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return !character.isPlayer();
									}
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_COMPANION_DOM_SEX", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(cost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
									getProstitute().incrementMoney(cost);
								}
							}
						};
					}
					
				} else if (index == 8 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("Субмиссив(ка) ([com.Name]) ("+UtilText.formatAsMoney(cost, "охват")+")",
								"Твоя [com.companion] не испытывает влечения к [npc.name], поэтому [npc.name] будет неохотно сводить [com.herHim] для секса с [npc.herHim].",
								null);
						
					} if(Main.game.getPlayer().getMoney()<cost) {
						return new Response("Субмиссив(ка) ([com.Name]) ("+UtilText.formatAsMoney(cost, "охват")+")",
								"У тебя нет "+cost+" огней, так что ты не можешь позволить себе заплатить [npc.name], чтобы она доминантно оттрахала [com.name].",
								null);
						
					} else {
						return new ResponseSex("Субмиссив(ка) ([com.Name]) ("+UtilText.formatAsMoney(cost, "охват")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name] так возбуждена, что [npc.she] не возьмет с тебя ни копейки за то, чтобы доминантно оттрахать [com.name]!"
											:("Заплатить [npc.name] "+cost+" огней, чтобы доминантно оттрахать [com.name].")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getProstitute()),
										Util.newArrayListOfValues(Main.game.getPlayer().getMainCompanion()),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.DOM_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_COMPANION_SUB_SEX", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(cost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
									getProstitute().incrementMoney(cost);
								}
							}
						};
					}
				}
			}
		
			if(getProstitute().hasFlag(NPCFlagValue.prostituteQuestioned)) {
				if(index == 5) {
					return new Response("Атаковать",
							UtilText.parse(getProstitute(), "Если бы ты действительно захотел, ничто не мешает тебе напасть на [npc.name]. В конце концов, раз [npc.sheHas] нарушает закон, [npc.she] можно считать легкой добычей!<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
							PROSTITUTE_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else if (index == 9) {
					return new Response(
							"Уволить (пригрозить)",
							UtilText.parse(getProstitute(), "Сказать [npc.name], что если [npc.she] немедленно не покинет эту зону, ты расскажешь Enforcers, где находится [npc.she].<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
							PROSTITUTE_REMOVAL_THREATENED) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
					};
					
				} else if (index == 10) {
					int fineAmount = getModifiedFineAmount(getProstitute());
					if(Main.game.getPlayer().getMoney()<fineAmount) {
						return new Response("Убрать ("+UtilText.formatAsMoney(fineAmount, "охват")+")",
								UtilText.parse(getProstitute(), "У тебя нет "+Util.intToString(fineAmount)+" огней, так что ты не можешь позволить себе заплатить [npc.name], чтобы она покинула эту зону."),
								null);
					} else {
						return new Response(
								"Убрать ("+UtilText.formatAsMoney(fineAmount, "охват")+")",
								UtilText.parse(getProstitute(), "Дать [npc.name] достаточно денег, чтобы расплатиться с Enforcers, которые охотятся за [npc.herHim], что позволит [npc.herHim] больше не работать в этих опасных переулках.<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]"),
								PROSTITUTE_REMOVAL_PAID) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ALLEY_PROSTITUTE_QUESTION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(Util.intToString(getModifiedFineAmount(getProstitute())), true);
			if(inApartment) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_QUESTION_APARTMENT", getProstitute()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_QUESTION", getProstitute()));
			}
			// Reset special parsing so that ALLEY_PROSTITUTE.getResponse() parsing is correct:
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(false)), true);
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(true)), false);
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ALLEY_PROSTITUTE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ALLEY_PROSTITUTE_ANGELS_KISS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS", getProstitute()));
			if(inApartment) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS_END_APARTMENT", getProstitute()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS_END", getProstitute()));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Зная, что теперь [npc.name] можно найти в Angel's Kiss, ты снова отправляешься в путь...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode PROSTITUTE_REMOVAL_THREATENED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Util.intToString(getModifiedFineAmount(getProstitute())), true);
			if(inApartment) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_THREATENED_APARTMENT", getProstitute()));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_THREATENED", getProstitute()));
			}
			Main.game.banishNPC(getProstitute());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Теперь, когда ты навел порядок в этом районе города, ты можешь продолжить свой путь...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode PROSTITUTE_REMOVAL_PAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			double rnd = Math.random();
			AbstractWeapon weapon;
			if(rnd<0.60f) {
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_enbaton_enbaton"); // 60% chance of getting a baton
			} else if(rnd>0.70f){
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"); // 30% chance of getting a pistol
			} else {
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_taser_taser"); // 10% chance of getting a taser
			}
			UtilText.addSpecialParsingString(weapon.getName(true, true), true);
			UtilText.addSpecialParsingString(Util.intToString(getModifiedFineAmount(getProstitute())), false);
			if(inApartment) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_PAID_APARTMENT", getProstitute()));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_PAID", getProstitute()));
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getModifiedFineAmount(getProstitute())));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(weapon, false));
			Main.game.banishNPC(getProstitute());
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Чувствуя радость от того, что смог помочь одной из обездоленных жительниц Dominion, ты продолжаешь свой путь...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode PROSTITUTE_FIGHT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().incrementKarma(-25);
		}
		@Override
		public String getContent() {
			if(inApartment) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_FIGHT_APARTMENT", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_FIGHT", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "Проучить эту наглую шлюху!", getProstitute());
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getProstitute(), "Вы победили [npc.name]!");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY", getProstitute());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти",
						"Оставить [npc.name] и идти дальше.<br/>[style.italicsBad(Это навсегда уберет [npc.herHim] из игры!)]",
						Main.game.getDefaultDialogue(false)) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Немного развлечься",
						UtilText.parse(getProstitute(), "Очевидно, что [npc.name] хочет, чтобы ты трахнул [npc.herHim], так что, может, тебе стоит дать [npc.herHim] то, чего желает [npc.she]..."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX", getProstitute()));
				
			} else if (index == 3) {
				return new ResponseSex("Нежное развлечение",
						UtilText.parse(getProstitute(), "Очевидно, что [npc.name] хочет, чтобы ты трахнул [npc.herHim], так что, может, тебе стоит дать [npc.herHim] то, чего желает [npc.she]..."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
								null, null,
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getProstitute()));
				
			} else if (index == 4) {
				return new ResponseSex("Грубое развлечение",
						UtilText.parse(getProstitute(), "Очевидно, что [npc.name] хочет, чтобы ты трахнул [npc.herHim], так что, может, тебе стоит дать [npc.herHim] то, чего желает [npc.she]..."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
								null, null,
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getProstitute()));
				
			} else if (index == 5) {
				return new ResponseSex("Подчиниться",
						UtilText.parse(getProstitute(), "Ты чувствуешь вину за то, что избил [npc.name]. Возможно, если подчиниться [npc.herHim] и позволить [npc.herHim] доминантно трахать тебя, это заставит [npc.herHim] почувствовать себя лучше?"),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getProstitute()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SUBMIT", getProstitute()));
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь",
						UtilText.parse(getProstitute(), "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")){
					@Override
					public void effects() {
						Main.mainController.openInventory(getProstitute(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 8 && getProstitute().isAbleToSelfTransform()) {
				return new Response("Трансформировать [npc.herHim]",
						"Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getProstitute());
					}
				};
				
			} else if (index == 9 && getProstitute().isAbleToSelfTransform()) {
				return new Response("Быстрые трансформации",
						"Так как [npc.she] может превращать [npc.herself], у тебя в голове рождается несколько идей...(Ты вернешься к этим вариантам, когда закончишь превращать [npc.herHim].)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getProstitute(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 11 && Main.game.getPlayer().hasCompanions()) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();
				
				if(!companion.isAttractedTo(getProstitute())) {
					return new Response(UtilText.parse(companion, "Трио"),
							UtilText.parse(getProstitute(), "[com.Name] не испытывает влечения к [npc.name], так что она не захочет заниматься сексом с [npc.herHim]!"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Трио"),
							UtilText.parse(getProstitute(), "Заняться доминантным сексом с [npc.name], заставив [com.name] присоединиться к веселью."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getProstitute()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_THREESOME", getProstitute()));
				}
				
			} else if (index == 12 && Main.game.getPlayer().hasCompanions()) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();

				if(!companion.isAttractedTo(getProstitute())) {
					return new Response("Отдать [com.name]",
							UtilText.parse(getProstitute(), "[com.Name] не испытывает влечения к [npc.name], так что она не захочет заниматься сексом с [npc.herHim]!"),
							null);
					
				} else {
					return new ResponseSex("Отдать [com.name]",
							UtilText.parse(getProstitute(), "Сказать [com.name], что [com.she] может немного развлечься с [npc.name], пока ты наблюдаешь."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getProstitute()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getProstitute()));
				}
				
			} else if (index == 13 && Main.game.getPlayer().hasCompanions() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();

				if(!getProstitute().isAttractedTo(companion)) {
					return new Response("Предложить [com.name]",
							UtilText.parse(getProstitute(), "[com.Name] не испытывает влечения к [npc.name], так что она не захочет заниматься сексом с [npc.herHim]!"),
							null);
					
				} else if(!companion.isAttractedTo(getProstitute()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Предложить [com.name]",
							UtilText.parse(getProstitute(), "Можно заметить, что [com.name] совсем не заинтересована в сексе с [npc.name], а заставить [com.herHim] ты не можешь..."),
							null);
					
				} else {
					return new ResponseSex("Предложить [com.name]",
							UtilText.parse(getProstitute(), "Сказать [npc.name], что [npc.she] может воспользоваться [com.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getProstitute())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getProstitute()) && Main.game.isNonConEnabled()) {
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
			return UtilText.parse(getProstitute(), "Вы были повержены [npc.name]!");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT", getProstitute());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getProstitute().isAttractedTo(Main.game.getPlayer()) && getProstitute().isWillingToRape()) {
				if (index == 1) {
					return new ResponseSex("Секс",
							"[npc.Name] навязывает вам [npc.herself]...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX", getProstitute()));
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							UtilText.parse(getProstitute(), "[npc.Name] навязывает вам [npc.herself]..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getProstitute()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Сопротивляться сексу",
							UtilText.parse(getProstitute(), "[npc.Name] навязывает вам [npc.herself]..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getProstitute()));
				}
				
			} else {
				if (index == 1) {
					return new Response("Продолжить",
							"Идти дальше.<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]",
							AFTER_COMBAT_DEFEAT){
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
							Main.game.banishNPC(getProstitute());
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PAID = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь на楽しみлся, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}
		@Override
		public String getContent() {
			if(inApartment) {
				if(Main.game.getPlayer().hasCompanions()) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
						if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_THREESOME", getProstitute());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_COMPANION", getProstitute());
						}
					}
				}
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID", getProstitute());
				
			} else {
				if(Main.game.getPlayer().hasCompanions()) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
						if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_THREESOME", getProstitute());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_COMPANION", getProstitute());
						}
					}
				}
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						UtilText.parse(getProstitute(), "Оставить [npc.name] позади и продолжить свой путь."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(inApartment) {
							if(Main.game.getPlayer().hasCompanions() && Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_LEAVE_COMPANION", getProstitute()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_LEAVE", getProstitute()));
							}
							
						} else {
							if(Main.game.getPlayer().hasCompanions() && Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_LEAVE_COMPANION", getProstitute()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_LEAVE", getProstitute()));
							}
						}
					}
				};
				
			} else if(index >= 4) {
				return ALLEY_PROSTITUTE.getResponse(responseTab, index);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(getProstitute(), "Теперь, когда ты вдоволь на楽しみлся, ты можешь отступить и оставить [npc.name] приходить в себя.");
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_THREESOME", getProstitute());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_COMPANION", getProstitute());
					}
				}
			}
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY", getProstitute());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти",
						"Оставить [npc.name] и идти дальше.<br/>[style.italicsBad(Это навсегда уберет [npc.herHim] из игры!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_LEAVE", getProstitute()));
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Инвентарь", "Ничто не мешает вам полакомиться одеждой и вещами [npc.namePos]..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getProstitute(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else {
				return null;
			}
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
			if(inApartment) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT_APARTMENT", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT", getProstitute());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Идти дальше.<br/>[style.italicsBad(Это навсегда удалит [npc.herHim] из игры!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT_LEAVE", getProstitute()));
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
