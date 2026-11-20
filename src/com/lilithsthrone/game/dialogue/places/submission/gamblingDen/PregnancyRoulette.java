package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMBreedingStall;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.9.4
 * @author Innoxia
 */
public class PregnancyRoulette {

	private static List<GenericSexualPartner> breeders = new ArrayList<>();
	private static int breederIndex = 0;
	private static int roll = 1;
	private static GenericSexualPartner mother;
	private static GameCharacter selectedBreeder;
	private static SexType murkPreference = null;
	
	private static void initBreeder(NPC partner) {
		partner.deleteAllEquippedClothing(true);
		partner.clearFetishes();
		partner.clearFetishDesires();
		partner.addFetish(Fetish.FETISH_IMPREGNATION);
		partner.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		partner.setPlayerKnowsName(true);
		partner.setPenisVirgin(false);
		partner.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		partner.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		partner.setAttribute(Attribute.VIRILITY, (partner.getPenisRawSizeValue()*2)+(partner.getTesticleSize().getValue() * 5)+partner.getPenisRawCumStorageValue());
		
		try {
			Main.game.addNPC(partner, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initMother() {
		mother = new GenericSexualPartner(Gender.F_V_B_FEMALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		mother.clearFetishes();
		mother.clearFetishDesires();
		mother.addFetish(Fetish.FETISH_PREGNANCY);
		mother.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		mother.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		mother.deleteAllEquippedClothing(true);
		mother.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		mother.setPlayerKnowsName(true);
		mother.useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), mother, false);
		try {
			Main.game.addNPC(mother, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNode PREGNANCY_ROULETTE = new DialogueNode("Счётчик рулетки беременности", "", false) {
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)
					|| (Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer()));
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE"));
				
				boolean preg = Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY);
				boolean impreg = Main.game.getPlayer().hasFetish(Fetish.FETISH_IMPREGNATION);
				if(preg) {
					if(impreg) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BOTH_FETISH"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_PREGNANCY_FETISH"));
					}
					
				} else if(impreg) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_IMPREGNATION_FETISH"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_NO_FETISH"));
				}
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_END"));
				
			} else if(Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_EPONA_IMPREGNATED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_REPEAT"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
					UtilText.nodeContentSB.append(
							"<p>Ответив на её приветствие, вы подходите к стойке, где счастливая девочка-лошадка спрашивает: [epona.speech(Хочешь ещё разок сыграть в рулетку беременности?");
					
					if(Main.game.getDialogueFlags().eponaStamps>=6) {
						UtilText.nodeContentSB.append(" Или, может, ты хочешь обменять свои штампы и прокатиться на мне!)]</p>");
					} else {
						UtilText.nodeContentSB.append(" У вас осталось "+Util.intToString(6-Main.game.getDialogueFlags().eponaStamps)+" штампов, прежде чем вы сможете прокатиться на мне!)]</p>");
					}
				}  else {
					UtilText.nodeContentSB.append(
							"<p>Ответив на её приветствие, вы подходите к стойке, где счастливая девочка-лошадка спрашивает: [epona.speech(Хочешь сыграть в рулетку беременности? Пора начинать собирать штампы!)]</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				if(index==1) {
					return new Response("Продолжить", "Теперь, когда Эпона рассказала вам об игре в рулетку беременности, вы раздумываете, что делать дальше...", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_GREETING_UTIL_END"));
						}
					};
				} else {
					return null;
				}
				
			} else if(Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("Продолжить", "Вы рады видеть, в каком восторге Эпона от мысли стать матерью ваших детей.", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getNpc(Epona.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_GREETING_UTIL_PREG_END"));
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index == 1) {
					return new Response("Правила", "Спросить Эпону о правилах рулетки беременности.", PREGNANCY_ROULETTE_RULES);
					
				} else if(index==2) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("Осемененный самец ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты уже беременна, так что не можешь записаться в матери!", null);
						
					} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
						return new Response("Осемененный самец ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Твоя утроба полна яицеклеток, так что ты не можешь записаться в матери!", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("Осемененный самец ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты не можешь участвовать в рулетке беременности, если твоя киска уже полна спермы!", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("Осемененный самец ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя нет влагалища, так что ты не можешь записаться в матери!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Осемененный самец ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты не можешь получить доступ к своему влагалищу, так что не можешь записаться в матери!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Осемененный самец ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя недостаточно денег, чтобы записаться в матери!", null);
						
					} else {
						return new Response("Осемененный самец ("+UtilText.formatAsMoney(10000, "охват")+")", "Записаться в матери для рулетки беременности, прося самцов осеменить тебя.", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, ((s) -> s.getRace()==Race.HARPY));
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("Осеменение футы ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты уже беременна, так что не можешь записаться в матери!", null);
						
					} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
						return new Response("Осеменение футы ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Твоя утроба полна яицеклеток, так что ты не можешь записаться в матери!", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("Осеменение футы ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты не можешь участвовать в рулетке беременности, если твоя киска уже полна спермы!", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("Осеменение футы ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя нет влагалища, так что ты не можешь записаться в матери!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Осеменение футы ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты не можешь получить доступ к своему влагалищу, так что не можешь записаться в матери!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Осеменение футы ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя недостаточно денег, чтобы записаться в матери!", null);
						
					} else {
						return new Response("Осеменение футы ("+UtilText.formatAsMoney(10000, "охват")+")", "Записаться в матери для игры в рулетку беременности, прося футас оседлать тебя.", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==4) {
					if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
						return new Response("Заводчик ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя нет пениса, поэтому ты не можешь записаться в заводчики!", null);
						
					} else if(!Main.game.getPlayer().isAbleToOrgasm()) {
						return new Response("Заводчик ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "Ты не можешь достичь оргазма, поэтому не можешь записаться в заводчики!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Заводчик ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя нет доступа к своему пенису, поэтому ты не можешь записаться в заводчики!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Заводчик ("+UtilText.formatAsMoneyUncoloured(10000, "охват")+")", "У тебя недостаточно денег, чтобы записаться в заводчики!", null);
						
					} else {
						return new Response("Заводчик ("+UtilText.formatAsMoney(10000, "охват")+")", "Записаться в число заводчиц для игры в рулетку беременности.", PREGNANCY_ROULETTE_BREEDER) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								// Skew the dice roll in the player's favour (lowest number goes first):
								Dice d = new Dice(Util.newHashMapOfValues(
										new Value<>(DiceFace.ONE, 4f),
										new Value<>(DiceFace.TWO, 3f),
										new Value<>(DiceFace.THREE, 2f),
										new Value<>(DiceFace.FOUR, 1f),
										new Value<>(DiceFace.FIVE, 0.5f),
										new Value<>(DiceFace.SIX, 0.25f)));
								d.roll();
								roll = d.getFace().getValue();
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
									
								} else {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
								}
								
								initMother();
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("Оседлать Эпону", "У вас недостаточно штампов, чтобы прокатиться на Эпоне! У вас есть "+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+", а нужно как минимум шесть.", null);
						
					} else {
						return new ResponseSex("Оседлать Эпону", "Сказать Эпоне, что вы хотите обменять свои штампы на поездку. (Заняться сексом в роли доминанта.)",
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(Main.game.getNpc(Epona.class)),
										null,
										null) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								EPONA_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_START_SEX_AS_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==7) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("Эпона скачет", "У вас недостаточно штампов, чтобы заставить Эпону прокатить вас! У вас есть "+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+", а нужно как минимум шесть.", null);
						
					} else {
						return new ResponseSex("Эпона скачет", "Скажите Эпоне, что хотите обменять свои марки и чтобы она «прокатилась» на вас. (Заняться сексом в роли подчиненного партнера.)",
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(Epona.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								EPONA_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_START_SEX_AS_DOM")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==11 && Main.game.getNpc(Murk.class).isSlave()) {
					return new Response("[murk.Name]", "Спросите Эпону, можно ли вам взглянуть на [murk.name].", MURK);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode EPONA_POST_SEX = new DialogueNode("Завершено", "Эпоне требуется минутка, чтобы перевести дух, прежде чем она вернется к работе.", false) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Epona.class))>=Main.game.getNpc(Epona.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_POST_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_POST_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_GREETING_UTIL = new DialogueNode("Счётчик рулетки беременности", "", false, true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_RULES = new DialogueNode("Счётчик рулетки беременности", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	private static String getBreederPanel(NPC breeder) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-half-width'>[npc.Name] - [npc.FullRace(true)]<br/>[npc.CockGirth], [npc.penisValue], [npc.cockColour(true)] [npc.cockRace] [npc.cock], с [npc.ballSize] яичками.</div>");
		
		return UtilText.parse(breeder, sb.toString());
	}
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION"));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index==1) {
				return new Response("Ждать", "Подождите, пока Эпона заведет заводчиков в комнату.", PREGNANCY_ROULETTE_MOTHER_SELECTION) {
					@Override
					public void effects() {
						Main.game.getNpc(Epona.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_SELECTION = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_SELECTION"));
			}

			for(int i=0;i< breeders.size()/2;i++) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='width:100%; margin:0'>");
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2)));
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2 +1)));
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index<=breeders.size()) {
				NPC breeder = breeders.get(index-1);
				return new Response(breeder.getName(true), "Скажите Эпоне, что вы думаете, будто "+breeder.getName(true)+" забеременеет от этого.", PREGNANCY_ROULETTE_MOTHER_START) {
					@Override
					public void effects() {
						selectedBreeder=breeder;
						Collections.shuffle(breeders);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_START = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_START", Util.newArrayListOfValues(selectedBreeder));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				NPC breeder = breeders.get(breederIndex);
				
				if(!Main.game.getPlayer().isTaur() && !Main.game.getPlayer().hasLegs() && Main.game.getPlayer().getGenitalArrangement()!=GenitalArrangement.CLOACA_BEHIND) {
					return new Response("Спереди "+breeder.getName(true),
							"Из-за отсутствия ног и того, что ваша вагина расположена спереди, вас невозможно осеменить, лежа на животе. Вам придется лечь на спину...",
							null);
				}
				
				return new ResponseSex("Спереди "+breeder.getName(true), "Ложись на живот, там "+breeder.getName(true)+" первым подберется, чтобы оттрахать тебя.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLustNoText(80);
								}
							}
						},
						null,
						null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else if(index==2) {
				NPC breeder = breeders.get(breederIndex);

				if(Main.game.getPlayer().isTaur()) {
					return new Response("Назад "+breeder.getName(true),
							"Из-за того, что твоя нижняя часть тела принадлежит дикой [pc.legRace], ты не сможешь поместиться в стойку для разведения, лежа на спине. Тебе придется лечь на живот...",
							null);
				}
				
				if(!Main.game.getPlayer().hasLegs() && Main.game.getPlayer().getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return new Response("Назад "+breeder.getName(true),
							"Из-за отсутствия ног и того, что твоя вагина расположена сзади, ты не сможешь принимать семя, лежа на спине. Тебе придется лечь на живот...",
							null);
				}
				
				return new ResponseSex("Назад "+breeder.getName(true), "Ложись на спину, там "+breeder.getName(true)+" первым подберется, чтобы оттрахать тебя.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_BACK))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLustNoText(80);
								}
							}
						},
						null,
						null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROULETTE_SEX = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return breeders.get(breederIndex-1).getName(true)+" завершено";
		}
		@Override
		public String getDescription() {
			return UtilText.parse(breeders.get(breederIndex-1), "Теперь, когда [npc.name] отходил свою очередь [npc.her] и залил тебя спермой, [npc.sheHasFull] возвращается к [npc.step]...");
		}
		@Override
		public String getContent() {
			NPC breeder = breeders.get(breederIndex-1);
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_ROULETTE_SEX", Util.newArrayListOfValues(breeder));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(breederIndex<breeders.size()) {
				if(index==1) {
					NPC breeder = breeders.get(breederIndex);
					
					return new ResponseSex("Спереди "+breeder.getName(true), "Устройся так, чтобы лежать на животе, там "+breeder.getName(true)+" будет следующий осеменитель, который подберется, чтобы оттрахать тебя.",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStall(
									Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLustNoText(80);
									}
								}
							},
							null,
							null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else if(index==2) {
					NPC breeder = breeders.get(breederIndex);

					if(Main.game.getPlayer().isTaur()) {
						return new Response("Назад "+breeder.getName(true),
								"Из-за того, что твоя нижняя часть тела принадлежит дикой [pc.legRace], ты не сможешь поместиться в стойку для разведения, лежа на спине. Тебе придется лечь на живот...",
								null);
					}
					
					return new ResponseSex("Назад "+breeder.getName(true), "Ляг на спину так, чтобы "+breeder.getName(true)+" будет следующий осеменитель, который подберется, чтобы оттрахать тебя.",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStall(
									Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_BACK))) {
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLustNoText(80);
									}
								}
							},
							null,
							null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Завершено", "все шесть производителей излили свой сперму в твою [pc.pussy+].", PREGNANCY_ROULETTE_MOTHER_FINISHED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Epona.class).useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false));
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_FINISHED = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant()) {
					if(Objects.equals(Main.game.getPlayer().getPregnantLitter().getFather(), selectedBreeder)) {
						return new Response("Победитель!", "Ты правильно угадала, что "+selectedBreeder.getName(true)+" станет отцом!", PREGNANCY_ROULETTE_MOTHER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(50000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
						
					} else {
						return new Response("Проигравшая", "Ты неправильно угадала, что "+selectedBreeder.getName(true)+" станет отцом, и проиграла ставку...", PREGNANCY_ROULETTE_MOTHER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
					}
						
				} else {
					return new Response("Проигравшая", "Ты не забеременела, так что все проигрывают свои ставки...", PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_WINNER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_LOSER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isFeminine()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FUTA", Util.newArrayListOfValues(mother)));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER", Util.newArrayListOfValues(mother)));
			}
			
			UtilText.nodeContentSB.append(
					"<p>Эпона бросает кости, и вы наблюдаете, как они с грохотом останавливаются на числе "+Util.intToString(roll)+". [epona.speech(Alright, [pc.name], that means you're going "+Util.intToPosition(roll)+"! А теперь я брошу кубики за всех остальных!)]</p><p>Теперь вам всем приходится ждать, пока Эпона бросает кости для каждого из остальных заводчиков. Каждый раз, когда ей выпадает уже выпавшее число, ей приходится перебрасывать кубики, из-за чего вам всем приходится ждать гораздо дольше, чем если бы она просто использовала более эффективный способ определения очередности.</p><p>Однако в конце концов Эпона заканчивает, и вы продвигаетесь вперед, чтобы занять свое место в очереди...</p><p style='text-align:center;'><i>Ваш черед: <b>"+Util.intToPosition(roll)+"</b>!</i>"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1){
				return new ResponseSex(roll==1?"Первый":"Ваш ход",
						roll==1
							?"Так как вам выпала единица, вы будете первым, кто опробует на деле добровольницу."
							:"Когда заводчица отходит от добровольницы, вы делаете шаг вперед, чтобы заняться трахом ее залитой спермой киски.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(mother,
										mother.hasLegs() || mother.getGenitalArrangement()!=GenitalArrangement.CLOACA_BEHIND
											?SexSlotBreedingStall.BREEDING_STALL_BACK
											:SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								character.setArousal(50);
								character.setLustNoText(80);
							}
						},
						null,
						null,
						PREGNANCY_ROULETTE_BREEDER_POST_SEX,
						roll==1
							?UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FIRST", Util.newArrayListOfValues(mother))
							:UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_MIDDLE", Util.newArrayListOfValues(mother))){
					@Override
					public void effects() {
						for(int i=0; i<roll-1; i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCum(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity());
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_POST_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(mother, "Теперь, когда вы получили свою порцию и кончили, вам нужно отойти от [npc.name]...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Ждать возвращения Эпоны.", PREGNANCY_ROULETTE_BREEDER_FINISHED) {
					@Override
					public void effects() {
						for(int i=roll-1; i<breeders.size(); i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCum(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity());
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_FINISHED = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FINISHED")
					+mother.useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), mother, false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(mother.isPregnant()) {
					if(mother.getPregnantLitter().getFather()!=null && mother.getPregnantLitter().getFather().isPlayer()) {
						return new Response("Победитель!", "Вы получили "+mother.getName(true)+" беременна!", PREGNANCY_ROULETTE_BREEDER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
						
					} else {
						return new Response("Проигравшая", "Ты не "+mother.getName(true)+" забеременела, так что проиграла ставку...", PREGNANCY_ROULETTE_BREEDER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
					}
						
				} else {
					return new Response("Проигравшая", "Никто не "+mother.getName(true)+" забеременел, так что все проигрывают свои ставки...", PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
							Main.game.banishNPC(mother);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_WINNER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_LOSER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY = new DialogueNode("", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MURK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaMurkOwnerIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Войти", "Войти в комнату, где содержится [murk.name]...", MURK_ALONE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkOwnerIntroduced, true);
					}
				};
				
			} else if(index==0) {
				return new Response("Уйти", "Засомневаться в желании навестить [murk.name], развернуться и уйти...", MURK_BACK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkOwnerIntroduced, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MURK_BACK = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_BACK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	private static void applyMurkReactions() {
		if(Main.game.getNpc(Murk.class).isVisiblyPregnant()) {
			Main.game.getNpc(Murk.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Murk.class), true);
		}
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSeen, true);
	}
	
	public static final DialogueNode MURK_ALONE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Map<SexType, Integer> sexMap = new HashMap<>();
			if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				sexMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), 10);
			}
			if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				sexMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), 3);
			}
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				sexMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1);
			}
			if(sexMap.isEmpty()) {
				murkPreference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
			} else {
				murkPreference = Util.getRandomObjectFromWeightedMap(sexMap);
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_ALONE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.murkMaster)) {
				if(index==1) {
					return new Response("Повиноваться", "Исполнить приказ Господина и опуститься на колени, чтобы поклоняться его члену.", MURK_SUBMIT_ACCEPT) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							applyMurkReactions();
						}
					};
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaMurkSubmitted)) {
				if(index==1) {
					return new Response("Повиноваться", "Исполнить приказ [murk.name] и опуститься на колени, чтобы поклоняться члену [murk.her].", MURK_SUBMIT_ACCEPT) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							applyMurkReactions();
						}
					};
					
				} else if(index==2) {
					return new Response("Отказаться", "Сказать [murk.name], чтобы не задирала нос, и отказаться подчиняться [murk.herHim].", MURK_SUBMIT_REFUSE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, false);
							applyMurkReactions();
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.murkLectured)) {
						return new Response("Лекция", "Вы уже читали лекцию [murk.name] сегодня, и кажется, что нет смысла повторять это до завтра...", null);
					}
					return new Response("Лекция", "Потратить время на лекцию для [murk.name] о том, почему то, что сделала [murk.she], было неправильно.", MURK_LECTURING) {
						@Override
						public void effects() {
							applyMurkReactions();
							if(Main.game.getNpc(Murk.class).isFeminine() && Main.game.getNpc(Murk.class).getAffection(Main.game.getPlayer())<25) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), 5));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.murkSpanked)) {
						return new Response("Шлепать", "Вы уже шлепали [murk.name] сегодня, и кажется, что нет смысла делать это снова до завтра...", null);
					}
					return new Response("Шлепать", "Потратить время на то, чтобы шлепать [murk.name].", MURK_SPANKING) {
						@Override
						public void effects() {
							applyMurkReactions();
							if(Main.game.getNpc(Murk.class).isFeminine()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), 10));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), -10));
							}
						}
					};
					
				} else if(index==3) {
					return new ResponseSex(
							"Трахать",
							"Доминантно трахать [murk.name].",
							true,
							false,
							new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Murk.class), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							null,
							null,
							AFTER_MURK_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SEX_DOM")) {
						@Override
						public void effects() {
							applyMurkReactions();
						}
					};
					
				} else if(index==4 && !Main.game.getNpc(Murk.class).isFeminine()) {
					return new ResponseSex(
							"Подчиниться",
							"Скажи [murk.name], что хочешь, чтобы [murk.herHim] доминировала над тобой...",
							true,
							false,
							new SMAllFours(
									Util.newHashMapOfValues(
										new Value<>(
											Main.game.getNpc(Murk.class),
											murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA || murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
												?SexSlotAllFours.HUMPING
												:SexSlotAllFours.IN_FRONT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return murkPreference;
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return murkPreference;
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									
									if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
										
									} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
										
									} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
									}
									
									map.put(Main.game.getNpc(Murk.class), Util.newArrayListOfValues(CoverableArea.PENIS));
									
									return map;
								}
							},
							null,
							null,
							AFTER_MURK_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette",
									murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA
										?"MURK_SEX_SUB_VAGINA"
										:(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
											?"MURK_SEX_SUB_ANUS"
											:(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH
												?"MURK_SEX_SUB_ORAL"
												:"MURK_SEX_SUB_HANDJOB")))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, true);
							applyMurkReactions();
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS){
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
								
							} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
								
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
							}
						}
					};
					
				} else if(index==5 && !Main.game.getNpc(Murk.class).isFeminine()) {
					return new Response("Феминизация",
							"Используй зелье с верхней полки, чтобы превратить [murk.name] в покорную девку-крысу.<br/>[style.italicsGenericTf(Это постоянная трансформация, после которой ты больше не сможешь выбирать действие «Подчиниться» с ней.)]",
							MURK_FEMINISE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, false);
							applyMurkReactions();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_FEMINISE"));
							Main.game.getTextStartStringBuilder().append(((Murk)Main.game.getNpc(Murk.class)).applyFeminisation());
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_FEMINISE_END"));
						}
					};
					
				} else if(index==0) {
					return new Response("Уйти", "Выйди из кладовой и сообщи Эпоне, что ты закончил с [murk.name].", MURK_LEAVE) {
						@Override
						public void effects() {
							applyMurkReactions();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_ALONE_LEAVE"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_LECTURING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkLectured, true);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_LECTURING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_SPANKING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkSpanked, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SPANKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_MURK_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription() {
			return "С [murk.Name] на сегодня хватит...";
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_AS_DOM");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_AS_SUB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("Уйти", "Оставь [murk.name] тяжело дышать на полу и уходи", MURK_LEAVE){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_DOM_LEAVE"));
						}
					};
					
				} else {
						return new Response("Уйти", "Сделай как говорит [murk.name] и уходи", MURK_LEAVE){
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_SUB_LEAVE"));
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_FEMINISE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_LEAVE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_CORRIDOR, false);
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return GamblingDenDialogue.CORRIDOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MURK_SUBMIT_ACCEPT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Подчиниться",
						"Ты подчиняешься своему Господину и позволяешь ему доминировать над тобой...",
						true,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
									new Value<>(
										Main.game.getNpc(Murk.class),
										murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA || murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
											?SexSlotAllFours.HUMPING
											:SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return murkPreference;
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return murkPreference;
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								
								if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
									
								} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
									
								} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
								}
								
								map.put(Main.game.getNpc(Murk.class), Util.newArrayListOfValues(CoverableArea.PENIS));
								
								return map;
							}
						},
						null,
						null,
						AFTER_MURK_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette",
								murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA
									?"MURK_SEX_SUB_VAGINA"
									:(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
										?"MURK_SEX_SUB_ANUS"
										:(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH
											?"MURK_SEX_SUB_ORAL"
											:"MURK_SEX_SUB_HANDJOB")))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSeen, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, true);
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS){
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							
						} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MURK_SUBMIT_REFUSE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_REFUSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};
	
	
}
