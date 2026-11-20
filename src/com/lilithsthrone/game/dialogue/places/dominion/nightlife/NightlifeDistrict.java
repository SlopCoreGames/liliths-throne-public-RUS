package com.lilithsthrone.game.dialogue.places.dominion.nightlife;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionClubNPC;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMJulesCockSucking;
import com.lilithsthrone.game.sex.managers.dominion.SMKrugerChair;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.managers.dominion.toiletStall.SMStallSex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class NightlifeDistrict {
	
	private static boolean isSearchingForASub = true;
	private static Gender clubberGender;
	private static AbstractSubspecies clubberSubspecies;
	private static RaceStage clubberRaceStage;
	
	private static boolean isClubOpen(int minutesPassedForNextScene) {
		return !((Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) >= (60 * 5) && (Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) < (60 * 19));
	}
	
	public static boolean isSearchingForASub() {
		return isSearchingForASub;
	}

	public static List<GameCharacter> getClubbersPresent() {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		// Do not filter by checking for DominionClubNPC classes, as imported clubbers are not of this class
		clubbers.removeIf((npc) -> npc.isUnique() || npc.isSlave() || Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())); // So that when player takes clubber home, slaves/occupants in player's room are not added to this list
		return clubbers;
	}
	
	public static List<GameCharacter> getGloryHoleCharacters() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters;
	}

	
	public static List<GameCharacter> getSavedClubbers(boolean submissiveClubbers) {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL));
		
		clubbers.removeIf((npc) -> (submissiveClubbers
						?npc.hasPersonalityTrait(PersonalityTrait.CONFIDENT)
						:!npc.hasPersonalityTrait(PersonalityTrait.CONFIDENT)));
		
		return clubbers;
	}
	
	private static boolean hasPartner() {
		return !getClubbersPresent().isEmpty();
	}
	
	public static GameCharacter getPartner() {
		return getClubbersPresent().get(0);
	}
	
	public static boolean isPartnerSub() {
		return !getPartner().hasPersonalityTrait(PersonalityTrait.CONFIDENT);
	}

	private static void spawnClubbers(boolean submissiveClubbers) {
		NPC clubber = new DominionClubNPC(clubberGender, clubberSubspecies, clubberRaceStage, false);
				
		if(Math.random()<0.4f) {
			clubber.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				clubber.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				clubber.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		
		if(submissiveClubbers) {
			clubber.removePersonalityTrait(PersonalityTrait.SELFISH);
			clubber.removePersonalityTrait(PersonalityTrait.BRAVE);
			clubber.removePersonalityTrait(PersonalityTrait.CONFIDENT);
			if(Math.random()<0.5) {
				clubber.addPersonalityTrait(PersonalityTrait.SHY);
			}
			if(clubber.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isNegative()) {
				clubber.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
			}
			clubber.removeFetish(Fetish.FETISH_DOMINANT);
			if(clubber.getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()) {
				clubber.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			}
			
		} else {
			double rnd = Math.random();
			clubber.removePersonalityTrait(PersonalityTrait.SHY);
			clubber.addPersonalityTrait(PersonalityTrait.CONFIDENT);
			if(rnd<0.33f) {
				clubber.addPersonalityTrait(PersonalityTrait.KIND);
			} else if(rnd<0.66f) {
				clubber.addPersonalityTrait(PersonalityTrait.SELFISH);
			}
			if(clubber.getFetishDesire(Fetish.FETISH_DOMINANT).isNegative()) {
				clubber.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			}
			clubber.removeFetish(Fetish.FETISH_SUBMISSIVE);
			if(clubber.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()) {
				clubber.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
			}
		}
		
		try {
			Main.game.addNPC(clubber, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
//			// spawn 1 or 2
//		} else {
//			// spawn 2
//			// spawn second one with companion's preferences
//		}
	}
	
	public static void saveClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			clubber.setLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL);
		}
	}
	
	public static void removeClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			Main.game.banishNPC((NPC) clubber);
		}
	}
	
	private static String getClubberStatus(int secondsPassedForNextScene, boolean isDominantPartner) {
		StringBuilder sb = new StringBuilder();
		
		if(hasPartner()) {
			GameCharacter clubber = getClubbersPresent().get(0);
			
			AffectionLevel al = clubber.getAffectionLevel(Main.game.getPlayer());
			if(isDominantPartner) {
				al = AffectionLevel.getAffectionLevelFromValue(domPartnerNightlyAffection);
			}
			
			sb.append("<p style='text-align:center;'><i>");
			switch(al) {
				case NEGATIVE_FIVE_LOATHE:
				case NEGATIVE_FOUR_HATE:
				case NEGATIVE_THREE_STRONG_DISLIKE:
				case NEGATIVE_TWO_DISLIKE:
					sb.append("[npc.Name] выглядит <i style='color:"+al.getColour().toWebHexString()+";'>сердитым и раздосадованным</i>, и уйдет от тебя, если ты продолжишь плохо относиться к [npc.herHim].");
					break;
				case NEGATIVE_ONE_ANNOYED:
					sb.append("[npc.Name] выглядит <i style='color:"+al.getColour().toWebHexString()+";'>слегка раздраженным</i>, и ему начинает не нравиться происходящее.");
					break;
				case ZERO_NEUTRAL:
				case POSITIVE_ONE_FRIENDLY:
					sb.append("[npc.Name] выглядит <i style='color:"+al.getColour().toWebHexString()+";'>развлеченным</i>, и у него нет причин уходить от тебя.");
					break;
				case POSITIVE_TWO_LIKE:
				case POSITIVE_THREE_CARING:
					sb.append("[npc.Name] похож на [npc.sheIs], который <i style='color:"+al.getColour().toWebHexString()+";'>прекрасно проводит время</i>.");
					break;
				case POSITIVE_FOUR_LOVE:
				case POSITIVE_FIVE_WORSHIP:
					sb.append("[npc.NameIsFull] <i style='color:"+al.getColour().toWebHexString()+";'>отчаянно хочет заняться с тобой сексом</i>.");
					break;
			}
			
			if(clubber.getAlcoholLevelValue()>0) {
				sb.append("</br>");
					switch(clubber.getAlcoholLevel()) {
						case ZERO_SOBER:
							break;
						case ONE_TIPSY:
							sb.append("[npc.Name] сейчас <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>подшофе</i>.");
							break;
						case TWO_MERRY:
							sb.append("[npc.Name] сейчас <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>весел</i>.");
							break;
						case THREE_DRUNK:
							sb.append("[npc.Name] сейчас <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>пьян</i>.");
							break;
						case FOUR_HAMMERED:
							sb.append("[npc.Name] сейчас <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>в стельку</i>. Давать [npc.herHim] еще больше алкоголя было бы плохой идеей.");
							break;
						case FIVE_WASTED:
							sb.append("[npc.Name] сейчас <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>в хлам</i>."+(isPartnerSub()?" [npc.She] кажется, что [npc.sheIs] вот-вот отключится.":""));
							break;
					}
			}

			sb.append("</br>");
			if(likesSex(clubber, isDominantPartner)) {
				sb.append("Можно заметить, что [npc.she] хочет переспать с тобой...");
				
			} else if(likesGroping(clubber, isDominantPartner)) {
				if(isDominantPartner) {
					sb.append("Можно заметить, что [npc.sheIs] вот-вот попытается к тебе прикоснуться...");
				} else {
					sb.append("Можно заметить, что [npc.she] жаждет физического контакта...");
				}
				
			} else if(likesKiss(clubber, isDominantPartner)) {
				if(isDominantPartner) {
					sb.append("Можно заметить, что [npc.sheIs] может попытаться поцеловать тебя в любой момент...");
				} else {
					sb.append("Можно заметить, что [npc.she] не прочь получить поцелуй...");
				}
				
			} else {
				if(isDominantPartner) {
					sb.append("Можно заметить, что [npc.sheIs] еще не готова сделать первый шаг...");
				} else {
					sb.append("Было бы лучше немного поговорить с [npc.herHim], прежде чем переходить к делу...");
				}
			}
			
			sb.append("</i></p>");
			
		}
		
		int minutesPassedForNextScene = secondsPassedForNextScene/60;
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return UtilText.parse(getClubbersPresent(), sb.toString());
	}
	
	private static int getKalahariBreakTimeLeft() {
		return Math.max(0, (int) (35 - (Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID))));
	}

	private static boolean isPartnerLeaving(boolean isDominantPartner) {
		if(isDominantPartner) {
			return hasPartner() && domPartnerNightlyAffection<AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue();
		}
		return hasPartner() && getPartner().getAffection(Main.game.getPlayer())<AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue();
	}
	
	private static boolean isPartnerPassingOut() {
		return hasPartner() && isPartnerSub() && getPartner().getAlcoholLevelValue()>=AlcoholLevel.FIVE_WASTED.getMaximumValue()-0.05f;
	}
	
	private static String getEndingStatus(int minutesPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
		if(isPartnerLeaving(false)) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name] бросает тебя!)]</br>[npc.Name] сыта по горло, и пренебрежительным взмахом [npc.her] [npc.hand], [npc.she] разворачивается и уходит от тебя!"));
		
		} else if(isPartnerPassingOut()) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name] падает!)]</br>Перебрав с выпивкой, [npc.name] тяжело оседает на землю!"));
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_VIP_AREA)) {
				sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible(Время закрытия)]</br>Весь свет в клубе внезапно несколько раз мигает, и когда фоновая музыка смолкает, Кругер рычит: [kruger.speech(Похоже, пора закрываться на ночь. Давай, [pc.name]. Мы можем продолжить в другой раз.)]"));
				
			} else {
				sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible(Время закрытия)]</br>Весь свет в клубе внезапно несколько раз мигает, и когда фоновая музыка смолкает, вы слышите крик Жюль: [jules.speech(Время закрытия! Всем выйти!)]"));
			}
		}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static boolean isEndConditionMet(int minutesPassedForNextScene) {
		return isPartnerLeaving(false) || isPartnerPassingOut() || (!isClubOpen(minutesPassedForNextScene) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules));
	}
	
	private static Response getEndResponse(int index, int minutesPassedForNextScene) {
		if(isPartnerLeaving(false)) {
			if(index==1) {
				return new Response("Продолжить", UtilText.parse(getClubbersPresent(), "Возможно, тебе следовало относиться к [npc.name] немного лучше..."), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(isPartnerSub()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_LEAVES", getClubbersPresent()));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_DOM_LEAVES", getClubbersPresent()));
						}
						removeClubbers();
					}
				};
			}
			
		} else if(isPartnerPassingOut()) {
			if(index==1) {
				return new Response("Продолжить", UtilText.parse(getClubbersPresent(), "[npc.Name] падает без сил!"), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_TOO_WASTED", getClubbersPresent()));
						saveClubbers();
					}
				};
			}
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(hasPartner()) {
				if(index==1) {
					return new ResponseEffectsOnly("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Наступило время закрытия, так что тебе пора покинуть клуб. Попрощайся с [npc.name], прежде чем вернуться в район Dominion, известный как «Ночная жизнь».</br>[style.italicsGood(Сохраняет этого персонажа, чтобы его можно было снова встретить в клубе.)]")) {
						@Override
						public void effects() {
							saveClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner(), false)) {
						return new Response("Пригласить домой",
								UtilText.parse(getClubbersPresent(), "Наступило время закрытия, так что тебе пора покинуть клуб. Позови [npc.name] к себе домой."),
								RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
							}
						};
					} else {
						return new Response("Пригласить домой", UtilText.parse(getClubbersPresent(),
								"Наступило время закрытия, так что тебе пора покинуть клуб. [npc.Name] явно не горит желанием идти к тебе домой..."), null);
					}
					
				} else if(index==3) {
					return new ResponseEffectsOnly("Расстаться",
							UtilText.parse(getClubbersPresent(), "Время закрытия, так что тебе пора уходить из клуба. Скажи [npc.name], что тебе пора, прежде чем отправиться обратно в район Dominion, известный как «Nightlife».</br>[style.italicsBad(Удаляет этого персонажа из игры.)]")) {
						@Override
						public void effects() {
							removeClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("Уйти", "Время закрытия, так что тебе пора уходить из клуба.") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
						}
					};
				}
			}
			return null;
			
		}
		return null;
	}
	
	private static String getKalahariStatus(boolean withBreakTime, int secondsPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		sb.append("У Kalahari осталось "+(getKalahariBreakTimeLeft()-5)+" минут перерыва.");
		sb.append("</i></p>");
		
		int minutesPassedForNextScene = secondsPassedForNextScene/60;
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return sb.toString();
	}
	
	private static boolean likesKiss(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()
				|| clubber.getAlcoholLevelValue()>0;
	}
	
	private static boolean likesGroping(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.TWO_MERRY.getMinimumValue();
	}
	
	private static boolean likesSex(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_THREE_CARING.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.THREE_DRUNK.getMinimumValue();
	}
	
	private static final float KALAHARI_SELL_MODIFIER = 1.2f;

	public static final DialogueNode OUTSIDE = new DialogueNode("Ночная жизнь", "Ночная жизнь", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(!isClubOpen(0)) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_DAY_STORM"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_DAY"));
				}
				
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_NIGHT_STORM"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_NIGHT"));
				}
			}
			
			if(Main.game.getDialogueFlags().hasFlag("innoxia_hannah_training_complete")) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_LIGHTS_OUT"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!isClubOpen(0)) {
					return new Response("Место для выпивки", UtilText.parse("Ночной клуб «Watering Hole» сейчас закрыт. Табличка у входа сообщает, что он открыт каждую ночь с [unit.time(19)] до [unit.time(05)]."), null);
				} else {
					return new Response("Место для выпивки", "Ночной клуб «Watering Hole» сейчас открыт. Ты можешь войти, если захочешь.", WATERING_HOLE_ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, false);
							Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
						}
					};
				}
				
			} else if(index==2 && Main.game.getDialogueFlags().hasFlag("innoxia_hannah_training_complete")) {
				if(!Main.game.isHourBetween(18, 4)) {
					return new Response("Свет гаснет", UtilText.parse("Бар «Lights Out» сейчас закрыт. Табличка у входа сообщает, что он открыт каждую ночь с [unit.time(18)] до [unit.time(04)]."), null);
					
				} else {
					return new Response("Свет гаснет",
							"Бар «Lights Out» сейчас открыт. Если захочешь, можешь войти.",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_nightlife_lights_out_exit_initial_entry"));
				}
			}
			return null;
		}
	};

	public static final DialogueNode WATERING_HOLE_ENTRANCE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules) || isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.julesIntroduced)) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_REPEAT", getClubbersPresent());
				}
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_PASSED")
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!hasPartner()) {
				if(index==0) {
					return new ResponseEffectsOnly("Выход",
							"Покинуть «The Watering Hole» и вернуться в район Dominion, известный как «Nightlife».") {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
					if(index==1) {
						return new Response("Ждать", "Терпеливо ждать в очереди, чтобы попасть в клуб.", WATERING_HOLE_ENTRANCE_WAITING) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
							}
						};
						
					} else if(index==2) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Сосать член", "Ты не можешь дотянуться ртом до своего члена, так что не можешь отсосать Джулсу!", null);
						}
						return new ResponseSex("Сосать член", "Отсосать Джулсу на глазах у всех, чтобы пролезть без очереди.",
								true,
								false,
								new SMJulesCockSucking(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Jules.class), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
								null,
								null,
								AFTER_JULES_BLOWJOB,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suckedJulesCock, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Jules.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
							}
						};
						
					} else if(index==3 && Main.game.getPlayer().getRace()==Race.DEMON) {
						return new Response("Пролезть без очереди", "Использовать свой статус демона, чтобы пройти без очереди.", WATERING_HOLE_ENTRANCE_SKIP_QUEUE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
							}
						};
						
					}
					return null;
					
				} else { // Passed Jules:
					if(index==1 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suckedJulesCock)) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.fuckedJulesTonight)) {
							return new Response("Джулс", "Ты уже успела «хорошенько трахнуться» с Джулсом сегодня вечером, и у него нет времени на повторение...", null);
						}
						return new ResponseSex("Джулс", "Сказать Джулсу, что ты хочешь, чтобы он «хорошенько тебя трахнул»...",
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(Jules.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								AFTER_JULES_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_JULES_SEX", NightlifeDistrict.getClubbersPresent())){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.fuckedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.fuckedJulesTonight, true);
							}
						};
						
					}
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Попрощаться с [npc.name], прежде чем снова отправиться в район Dominion под названием «Ночная жизнь».</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]")) {
						@Override
						public void effects() {
							saveClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner(), false)) {
						return new Response("Пригласить домой", UtilText.parse(getClubbersPresent(), "Отвести [npc.name] обратно в свою комнату."), RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								Main.game.setRequestAutosave(false);
							}
						};
					} else {
						return new Response("Пригласить домой", UtilText.parse(getClubbersPresent(), "[npc.Name] явно не горит желанием идти к тебе. Стоит сначала еще немного пообщаться с [npc.herHim]..."), null);
					}
					
				} else if(index==3) {
					return new ResponseEffectsOnly("Расстаться",
							UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что тебе пора, прежде чем снова отправиться в район Dominion под названием «Ночная жизнь».</br>[style.italicsBad(Удаляет этого персонажа из игры.)]")) {
						@Override
						public void effects() {
							removeClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} 
				return null;
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_ENTRANCE_WAITING = new DialogueNode("Поилочка", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_WAITING")
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_ENTRANCE_SKIP_QUEUE = new DialogueNode("Поилочка", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_SKIP_QUEUE")
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_JULES_BLOWJOB = new DialogueNode("Завершено", "С Джулс хватит.", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AFTER_JULES_BLOWJOB")
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_JULES_SEX = new DialogueNode("Завершено", "С Джулс хватит.", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AFTER_JULES_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_MAIN = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Поговорить", UtilText.parse(getClubbersPresent(), "Поговорить с [npc.name] и получше узнать [npc.herHim]."), WATERING_HOLE_MAIN_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("Флиртовать", UtilText.parse(getClubbersPresent(), "Сделать комплимент внешности [npc.namePos] и начать флиртовать с [npc.herHim]."), WATERING_HOLE_MAIN_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==3) {
					return new Response("Поцеловать",
							UtilText.parse(getClubbersPresent(), "Сделать шаг вперед и поцеловать [npc.name]."+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_MAIN_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==4) {
					return new Response("Лапать",
							UtilText.parse(getClubbersPresent(), "Прижаться к [npc.name] и начать ощупывать [npc.herHim]."+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_MAIN_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getSecondsPassed(), false));
							}
						}
					};
					
				} if(index==9) {
					return new Response("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что тебе пора уходить, но ты надеешься снова увидеть [npc.herHim].</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]"),
							WATERING_HOLE_MAIN_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Расстаться",
							UtilText.parse(getClubbersPresent(), "Придумать предлог, чтобы отделаться от [npc.name].</br>[style.italicsBad(Удалить этого персонажа из игры.)]"),
							WATERING_HOLE_MAIN_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Искать (как доминант)",
							"Искать кого-нибудь, чтобы подойти и заговорить. Это даст тебе роль доминанта, где ты будешь вести своего партнера по клубу.",
							WATERING_HOLE_SEARCH_GENDER) {
						@Override
						public void effects() {
							isSearchingForASub = true;
						}
					};
					
				} else if(index==2) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("Повторить поиск (как доминант)", "Сначала нужно обыскать клуб!", null);
					} else {
						return new Response("Повторить поиск (как доминант)",
								"Повторить ваш последний поиск кого-нибудь, чтобы подойти и заговорить. ("+Util.capitaliseSentence(clubberGender.getName())+" "+clubberSubspecies.getName(null)+")",
								WATERING_HOLE_SEARCH_GENERATE) {
							@Override
							public void effects() {
								isSearchingForASub = true;
								spawnClubbers(true);
							}
						};
					}
					
				} else if(index==3) {
					if(getSavedClubbers(true).isEmpty()) {
						return new Response("Контакты (как доминант)", "Вы еще не встретили никого из тех, с кем обещали снова увидеться в клубе...", null);
						
					} else {
						return new Response("Контакты (как доминант)",
								"Поискать кого-то, с кем вы уже встречались в клубе раньше. Это даст вам роль доминанта, где именно вы будете вести своего партнера по клубу.",
								WATERING_HOLE_CONTACTS) {
							@Override
							public void effects() {
								isSearchingForASub = true;
							}
						};
					}
					
				} else if(index==4) {
					return new Response("Импорт (как доминант)",
							"Открыть экран импорта персонажей. Персонажи, импортированные на следующем экране, будут помечены как подчиненные и появятся в вашем списке «Контакты (как доминант)» после завершения импорта.",
							WATERING_HOLE_IMPORT) {
						@Override
						public void effects() {
							isSearchingForASub = true;
						}
					};
					
				} if(index==6) {
					return new Response("Поиск (как саб)",
							"Послоняться в главной зоне клуба и попытаться привлечь взгляд того, кто вам приглянется. Это даст вам роль подчиненного, где ваш партнер будет вести вас по клубу.",
							WATERING_HOLE_SEARCH_GENDER) {
						@Override
						public void effects() {
							isSearchingForASub = false;
						}
					};
					
				} else if(index==7) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("Повторить поиск (как саб)", "Сначала нужно обыскать клуб!", null);
					} else {
						return new Response("Повторить поиск (как саб)",
								"Повторить последний поиск того, кто мог бы привлечь ваше внимание. ("+Util.capitaliseSentence(clubberGender.getName())+" "+clubberSubspecies.getName(null)+")",
								WATERING_HOLE_SEARCH_GENERATE_DOM) {
							@Override
							public void effects() {
								isSearchingForASub = false;
								spawnClubbers(false);
								resetPreviousBehaviour(); 
							}
						};
					}
					
				} else if(index==8) {
					if(getSavedClubbers(false).isEmpty()) {
						return new Response("Контакты (подменю)", "Вы еще не встретили никого из тех, с кем обещали снова увидеться в клубе...", null);
						
					} else {
						return new Response("Контакты (подменю)",
								"Искать одного из ваших доминантных контактов, с которым вы уже встречались в клубе раньше. Это поставит вас в подчиненную роль, где ваш партнер будет вести вас по клубу.",
								WATERING_HOLE_CONTACTS_DOM) {
							@Override
							public void effects() {
								isSearchingForASub = false;
							}
						};
					}
					
				} else if(index==9) {
					return new Response("Импорт (подменю)",
							"Открыть экран импорта персонажей. Персонажи, импортированные на следующем экране, будут отмечены как доминантные и появятся в вашем списке «Контакты (подменю)» после завершения импорта.",
							WATERING_HOLE_IMPORT) {
						@Override
						public void effects() {
							isSearchingForASub = false;
						}
					};
				}
				
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_TALK = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_FLIRT = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_KISS = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_GROPE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_LOSE_COMPANY = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENDER = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENDER");
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "[style.colourFeminine(Женский)]";
				
			} else if(index==1) {
				return "[style.colourMasculine(Мужской)]";
				
			} else if(index==2) {
				return "[style.colourAndrogynous(Андрогинно)]";
				
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Решить не искать никого для сближения.", Main.game.getDefaultDialogue(false));
			}
			int count = 1;
			for(Gender gender : Gender.values()) {
				if((responseTab==0 && gender.getType()==PronounType.FEMININE)
						|| (responseTab==1 && gender.getType()==PronounType.MASCULINE)
						|| (responseTab==2 && gender.getType()==PronounType.NEUTRAL)) {
					if(count==index) {
						return new Response(Util.capitaliseSentence(gender.getName()),
								"Искать "+UtilText.generateSingularDeterminer(gender.getName())+" "+gender.getName()+" среди толпы гуляк. ("+(gender.getGenderName().isHasBreasts()?"[style.colourGood(Грудь)]":"[style.colourBad(Грудь)]")+", "
										+(gender.getGenderName().isHasPenis()?"[style.colourGood(Пенис)]":"[style.colourBad(Пенис)]")+", "
										+(gender.getGenderName().isHasVagina()?"[style.colourGood(Вагина)]":"[style.colourBad(Вагина)]")+")",
								WATERING_HOLE_SEARCH_RACE) {
							@Override
							public void effects() {
								clubberGender = gender;
							}
						};
					}
					count++;
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_RACE = new DialogueNode("Поилочка", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_RACE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(Частичная)]";
			} else if(index == 1) {
				return "[style.colourTfMinor(Незначительная)]";
			} else if(index == 2) {
				return "[style.colourTfLesser(Малая)]";
			} else if(index == 3) {
				return "[style.colourTfGreater(Сильная)]";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Решить поискать другой пол.", WATERING_HOLE_SEARCH_GENDER);
			}
			int count = 1;
			
			Set<AbstractSubspecies> subspeciesSet = new HashSet<>();
			for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
				subspeciesSet.addAll(pop.getSpecies().keySet());
			}
			if(!subspeciesSet.isEmpty()) {
				List<AbstractSubspecies> sortedSubspecies = new ArrayList<>(subspeciesSet);
				sortedSubspecies.sort((s1, s2) -> s1.getRace().getName(false).compareTo(s2.getRace().getName(false)));
				for(AbstractSubspecies subspecies : sortedSubspecies) {
					if(count==index) {
						return new Response(Util.capitaliseSentence(subspecies.getName(null)),
								"Искать "+UtilText.generateSingularDeterminer(subspecies.getName(null))+" "+subspecies.getName(null)+" среди толпы гуляк.",
								(isSearchingForASub
										?WATERING_HOLE_SEARCH_GENERATE
										:WATERING_HOLE_SEARCH_GENERATE_DOM)) {
							@Override
							public void effects() {
								switch(responseTab) {
									case 0:
										clubberRaceStage = RaceStage.PARTIAL;
										break;
									case 1:
										clubberRaceStage = RaceStage.PARTIAL_FULL;
										break;
									case 2:
										clubberRaceStage = RaceStage.LESSER;
										break;
									default:
										clubberRaceStage = RaceStage.GREATER;
										break;
								}
								clubberSubspecies = subspecies;
								spawnClubbers(isSearchingForASub);
							}
						};
					}
					count++;
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENERATE = new DialogueNode("Поилочка", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isPartnerSub()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE", getClubbersPresent())
						+getClubberStatus(this.getSecondsPassed(), false);
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_DOM", getClubbersPresent())
						+getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_CONTACTS = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_CONTACTS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Решить перестать искать того, кого вы уже встречали раньше.", WATERING_HOLE_MAIN);
			}
			int count = 1;
			for(GameCharacter character : getSavedClubbers(true)) {
				if(count==index) {
					if(!character.isAttractedTo(Main.game.getPlayer())) {
						return new Response(character.getName(true),
								UtilText.parse(character, "[npc.Name] [style.colourBad(больше не испытывает к вам влечения)] и вряд ли захочет проводить с вами время в клубе.<br/>([npc.She] — это [npc.a_fullRace(true)].)"),
								null);
					}
					if(Main.game.getMinutesPassed()-((NPC)character).getLastTimeEncountered()<12*60) {
						return new Response(character.getName(true),
								UtilText.parse(character, "Вы уже встречали [npc.name] в клубе сегодня, поэтому [style.colourBad(вы не сможете снова встретить [npc.herHim] до завтра)]."),
								null);
					}
					return new Response(character.getName(true),
							UtilText.parse(character, "Искать [npc.name] среди толпы гуляк.<br/>([npc.She] — это [npc.a_fullRace(true)].)"),
							WATERING_HOLE_FIND_CONTACT) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
							if(character.getTotalTimesHadSex(Main.game.getPlayer()) == 0) {
								character.setAffection(Main.game.getPlayer(), 5);
							}
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_FIND_CONTACT = new DialogueNode("Поилочка", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_IMPORT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			if(isSearchingForASub) {
				saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_IMPORT_DOM"));
			} else {
				saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_IMPORT_SUB"));
			}
			saveLoadSB.append(
					"<p>"
						+ "<table align='center'>");
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td><td><div class='saveLoadButton' id='IMPORT_CLUBBER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Импорт</div></td></tr>";
	}
	
	public static final DialogueNode WATERING_HOLE_LOITER_GENERATE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			List<GameCharacter> clubbers = getClubbersPresent();
			
			if(clubbers.size()==1) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE", clubbers);
				
			} else {
				if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE_TWO", clubbers);
					
				} else {
					clubbers.addAll(Main.game.getPlayer().getNonElementalCompanions());
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE_TWO_WITH_COMPANION", clubbers);
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_SEATING = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_WITH_PARTNER", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Поговорить", UtilText.parse(getClubbersPresent(), "Поговорить с [npc.name] и получше узнать [npc.herHim]."), WATERING_HOLE_SEATING_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("Флиртовать", UtilText.parse(getClubbersPresent(), "Сделать комплимент внешности [npc.namePos] и начать флиртовать с [npc.herHim]."), WATERING_HOLE_SEATING_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==3) {
					boolean bothBipeds = true;
					if(Main.game.getPlayer().isTaur() || getPartner().isTaur()) {
						bothBipeds = false;
					}
					return new Response( // If both partners are bipeds, play footsie. If not, feeling up occurs instead.
							bothBipeds
								?"Игривые прикосновения ногами"
								:"Лапать",
							UtilText.parse(getClubbersPresent(),
									(bothBipeds
										?"Слегка толкнуть [pc.foot] пахом в пах [npc.namePos]."
										:"Прижаться к [npc.name] и начать лапать [npc.herHim].")
									+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_SEATING_FOOTSIE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getSecondsPassed(), false));
								
							}
						}
					};

				} else if(index==4) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Секс (доминант)",
								UtilText.parse(getClubbersPresent(), "[npc.Name] [style.colourBad(не испытывает к тебе влечения)], и поэтому не желает заниматься с тобой сексом..."),
								null);
					}
					if(likesSex(getPartner(), false)) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};
						
						if(Main.game.getPlayer().isTaur()) { // Player is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						return new ResponseSex("Секс (доминант)",
								UtilText.parse(getClubbersPresent(), "Ты больше не можешь сопротивляться [npc.name]! Сделай шаг, чтобы начать доминировать над [npc.herHim]."),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM", getClubbersPresent()));
						
					} else {
						return new Response("Секс (доминант)",
								UtilText.parse(getClubbersPresent(), "Ты больше не можешь сопротивляться [npc.name]! Сделай шаг, чтобы начать доминировать над [npc.herHim].</br>[style.italicsBad([npc.She] может отреагировать на это не слишком хорошо!)]"),
								WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED) {
							@Override
							public void effects() {
								if(!getClubbersPresent().get(0).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
									Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								}
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED.getSecondsPassed(), false));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if(index==5) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Секс (сабмиссив)",
								UtilText.parse(getClubbersPresent(), "[npc.Name] [style.colourBad(не испытывает к тебе влечения)], и поэтому не желает заниматься с тобой сексом..."),
								null);
					}
					if(likesSex(getPartner(), false)) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};
						
						if(Main.game.getPlayer().isTaur()) {
							if(getPartner().isTaur()) { // Both taurs/arachnids:
								sm = new SexManagerDefault(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
									@Override
									public List<AbstractSexPosition> getAllowedSexPositions() {
										return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
									}
								};
							}
							
						} else if(getPartner().isTaur()) { // Partner is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), getPartner().hasPenis()?SexSlotStanding.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL_BEHIND))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						return new ResponseSex(
								"Секс (сабмиссив)",
								UtilText.parse(getClubbersPresent(), "Ты больше не можешь сопротивляться [npc.name]! Сделай шаг, чтобы начать подчиняться [npc.herHim]."),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB", getClubbersPresent()));
						
					} else {
						return new Response("Секс (сабмиссив)",
								UtilText.parse(getClubbersPresent(), "Ты больше не можешь сопротивляться [npc.name]! Сделай шаг, чтобы начать подчиняться [npc.herHim].</br>[style.italicsBad([npc.She] может отреагировать на это не слишком хорошо!)]"),
								WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED.getSecondsPassed(), false));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} if(index==9) {
					return new Response("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что тебе пора уходить, но ты надеешься снова увидеть [npc.herHim].</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]"),
							WATERING_HOLE_SEATING_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Расстаться",
							UtilText.parse(getClubbersPresent(), "Придумать предлог, чтобы отделаться от [npc.name].</br>[style.italicsBad(Удалить этого персонажа из игры.)]"),
							WATERING_HOLE_SEATING_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				}
				return null;
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_TALK = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_FLIRT = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_FOOTSIE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "С [npc.Name] на сегодня хватит...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getPartner())>=getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_NO_ORGASM", getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Увидеться снова",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что надеетесь снова увидеть [npc.herHim].</br>[style.italicsGood(Сохраняет этого персонажа, которого затем можно будет снова встретить в клубе.)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Надеяться, что нет (мягко)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Ответить уклончиво, втайне надеясь, что вы больше не увидите [npc.name].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Надеяться, что нет (грубо)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Грубо сказать [npc.name], что вас интересовал только трах [npc.herHim].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN = new DialogueNode("Поилочка", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN = new DialogueNode("Поилочка", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEATING_LOSE_COMPANY = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_BAR = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR", getClubbersPresent());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_REPEAT", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return getEndResponse(index, 0);
			}
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				if(index==1) {
					return new Response("Барменша", "Львица-барменша приветствует тебя.", WATERING_HOLE_BAR_KALAHARI_INTRO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariIntroduced, true);
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Kalahari", "Kalahari делает шаг вперед и приветствует тебя.", WATERING_HOLE_BAR_KALAHARI) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI", getClubbersPresent()));
							Main.game.getNpc(Kalahari.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	private static String getDrinkEffects(AbstractItem drink) {
		StringBuilder sb = new StringBuilder();
		
		for(ItemEffect ie : drink.getEffects()) {
			for(String desc : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
				sb.append("</br>"+desc);
			}
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_INTRO = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_INTRO", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(hasPartner()) {
				if(index==0) {
					return "Я";
					
				} else if(index==1) {
					return UtilText.parse(getClubbersPresent(), "[npc.Name]");
					
				}
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(!hasPartner() || responseTab==0) {
				if(index==1) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Вода ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает на бутылку "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Вода ("+UtilText.formatAsMoney(price, "охват")+")", "Попросить у Kalahari бутылку "+drink.getName(false, false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>[pc.speech(Можно мне бутылочку "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одного из холодильников за барной стойкой. Шагнув к тебе, она наклоняется вперед, демонстрируя глубокое декольте, и игриво подмигивает, ставя перед тобой холодный напиток. [kalahari.speech(Наслаждайся!)]</p><p>Выкрикнув благодарность сквозь фоновый шум клуба, ты откручиваешь крышку и подносишь пластиковую бутылку к своим [pc.lips]. Слабый сладкий аромат подсказывает, что это не просто вода, и когда ты запрокидываешь голову и начинаешь пить прохладную жидкость, вкус ванили ошеломляет твои чувства.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Пиво ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает на бутылку "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Пиво ("+UtilText.formatAsMoney(price, "охват")+")", "Попросить у Kalahari бутылку "+drink.getName(false, false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>[pc.speech(Можно мне бутылочку "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одного из холодильников за барной стойкой. Отступив на шаг, она наклоняется к тебе, демонстрируя декольте, и игриво подмигивает, ставя перед тобой холодное пиво. Схватив лежавшую рядом открывалку, Kalahari откупоривает бутылку и пододвигает её к тебе по столешнице. [kalahari.speech(Наслаждайся!)]</p><p>Перекрикивая шум клуба, ты выкрикиваешь слова благодарности, хватаешь холодную бутылку и подносишь её к своим [pc.lips]. Когда ты начинаешь жадно глотать напиток, понимаешь, что он совсем не похож на любое другое пиво, что ты пробовал раньше: он больше напоминает сладкий энергетический напиток, чем какой-либо алкогольный коктейль. Когда последние капли проскальзывают в горло, на языке остается странное мускусное послевкусие.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает денег на стакан "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "охват")+")",
								"Попросить у Kalahari стакан "+drink.getName(false, false)+"."+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>[pc.speech(Можно мне стакан "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одного из холодильников за барной стойкой. Отступив на шаг к тебе, она наклоняется вперед, демонстрируя глубокое декольте, и игриво подмигивает, доставая чистый бокал из-под стойки. Поставив бокал перед тобой, львица наливает порцию кремового алкогольного напитка, а затем пододвигает его по барной стойке к тебе. [kalahari.speech(Наслаждайся!)]</p><p>Выкрикнув слова благодарности сквозь фоновый шум клуба, ты берешь бокал и подносишь его к своим [pc.lips]. Напиток источает густой сливочный аромат, и когда ты жадно осушаешь прохладную жидкость, ты с delight обнаруживаешь, что вкус оказывается в точности таким же великолепным, как и его восхитительный запах.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает денег на стакан "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "охват")+")",
								"Попросить у Kalahari стакан "+drink.getName(false, false)+"."+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>[pc.speech(Можно мне стакан "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" с одной из полок за барной стойкой. Отступив на шаг к тебе, она наклоняется вперед, демонстрируя глубокое декольте, и игриво подмигивает, доставая чистый бокал из-под стойки. Поставив бокал перед тобой, львица наливает порцию крепкого алкогольного напитка, а затем пододвигает его по барной стойке к тебе. [kalahari.speech(Наслаждайся!)]</p><p>Выкрикнув слова благодарности сквозь фоновый шум клуба, ты берешь бокал и подносишь его к своим [pc.lips]. Виски источает густой мускусный аромат, и когда ты начинаешь глушить жидкость, ты обнаруживаешь, что вкус почти идентичен его резкому запаху.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает денег на стакан "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "охват")+")",
								"Попросить у Kalahari стакан "+drink.getName(false, false)+"."+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>[pc.speech(Можно мне стакан "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" с одной из полок за барной стойкой. Отступив на шаг назад, она наклоняется вперед, демонстрируя глубокое декольте, и игриво подмигивает тебе, доставая чистый бокал из-под стойки. Поставив бокал перед тобой, львица наливает порцию крепкого алкогольного напитка, а затем пододвигает его по барной стойке к тебе. [kalahari.speech(Наслаждайся!)]</p><p>Перекрикивая фоновый шум клуба, ты выкрикиваешь слова благодарности, берешь бокал и подносишь его к своим [pc.lips], после чего запрокидываешь голову и залпом осушаешь золотистую жидкость. Когда последние капли проскальзывают по твоему горлу, ты замечаешь слабый мускусный привкус, пропитывающий алкогольный напиток, который на некоторое время остается слегка неприятным послевкусием.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					if(!hasPartner()) {
						return new Response("Поговорить", "Наклониться вперед и немного поболтать с Kalahari.", WATERING_HOLE_BAR_KALAHARI_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_TALK.getSecondsPassed(), false));
							}
						};
					} else {
						return new Response("Поговорить", UtilText.parse(getClubbersPresent(), "Ты не можешь разговаривать с Kalahari, пока с тобой [npc.name]!"), null);
					}
					
				} else if(index==7) {
					if(!hasPartner()) {
						return new Response("Флиртовать", "Начать флиртовать с Kalahari.", WATERING_HOLE_BAR_KALAHARI_FLIRT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_FLIRT.getSecondsPassed(), false));
							}
						};
					} else {
						return new Response("Флиртовать", UtilText.parse(getClubbersPresent(), "Ты не можешь флиртовать с Kalahari, пока с тобой [npc.name]!"), null);
					}
					
				} else if(index==8) {
					if(hasPartner()) {
						return new Response("Перерыв", UtilText.parse(getClubbersPresent(), "Ты не можешь попросить Kalahari провести перерыв с тобой, пока с тобой [npc.name]!"), null);
						
					} else if(Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID) < 60 * 12) {
							return new Response("Перерыв", "Kalahari уже использовала свой перерыв сегодня вечером!", null);
							
					} else if(!likesKiss(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("Перерыв", "Ты еще недостаточно хорошо знаешь Kalahari, чтобы пригласить ее провести перерыв вместе с тобой. Сначала попробуй немного поболтать и пофлиртовать...", null);
						
					} else {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
							return new Response("Перерыв", "Спросить Kalahari, скоро ли у нее будет перерыв и не хочет ли она провести его с тобой.", WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO) {
								@Override
								public void effects() {
									Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.krugerIntroduced, true);
									Main.game.getDialogueFlags().setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Main.game.getMinutesPassed());
									Main.game.getNpc(Kruger.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()-7);
								}
							};
							
						} else {
							return new Response("Перерыв", "Спросить Kalahari, скоро ли у нее будет перерыв и не хочет ли она снова отдохнуть с тобой в VIP-зоне.", WATERING_HOLE_BAR_KALAHARI_BREAK) {
								@Override
								public void effects() {
									Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Main.game.getMinutesPassed());
								}
							};
						}
					}
					
				}
				
			} else {
				GameCharacter clubber = getClubbersPresent().get(0);
				
				if(index==1) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Вода ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает на бутылку "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Вода ("+UtilText.formatAsMoney(price, "охват")+")",
								UtilText.parse(getClubbersPresent(), "Попросить у Kalahari бутылку "+drink.getName(false, false)+" для [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>[pc.speech(Можно мне бутылочку "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одного из холодильников за барной стойкой. Отступая к тебе, она наклоняется вперед, демонстрируя декольте, и игриво подмигивает тебе, ставя перед тобой холодный напиток. [kalahari.speech(Наслаждайся!)]</p><p>Выкрикнув слова благодарности сквозь шум клуба, ты передаешь бутылку [npc.name]. [pc.speech(Похоже, тебе не помешало бы немного водички. Вот, держи.)]</p><p>[npc.speech(Спасибо, я уже изрядно притомилась, да,)] [npc.name] отвечает, прежде чем открутить крышку и жадно осушить бутылку.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Пиво ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает на бутылку "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Пиво ("+UtilText.formatAsMoney(price, "охват")+")",
								UtilText.parse(getClubbersPresent(), "Попросить у Kalahari бутылку "+drink.getName(false, false)+" для [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>[pc.speech(Можно мне бутылочку "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одного из холодильников за барной стойкой. Отступив к тебе, она наклоняется вперед, демонстрируя глубокое декольте и игриво подмигивая, пока ставит перед тобой холодное пиво. Схватив лежащий рядом открывашка, Kalahari откупоривает бутылку и пододвигает её к тебе через стойку. [kalahari.speech(Наслаждайся!)]</p><p>Прокричав слова благодарности сквозь фоновый шум клуба, ты передаешь бутылку [npc.name]. [pc.speech(Похоже, тебе не помешает глоток. Держи.)]</p><p>[npc.speech(Спасибо! Я уже изрядно притомилась, да,)] [npc.name] отвечает, прежде чем поднести бутылку к [npc.her] [npc.lips] и жадно осушить её.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 2)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает денег на стакан "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "охват")+")",
								UtilText.parse(getClubbersPresent(), "Попросить у Kalahari стакан "+drink.getName(false, false)+" для [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>[pc.speech(Можно мне стакан "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одного из холодильников за барной стойкой. Отступив к тебе, она наклоняется вперед, демонстрируя глубокое декольте и игриво подмигивая, пока достает чистый бокал из-под стойки. Поставив бокал перед тобой, львица наливает порцию кремового алкогольного напитка, а затем пододвигает его к тебе по барной стойке. [kalahari.speech(Наслаждайся!)]</p><p>Прокричав слова благодарности сквозь фоновый шум клуба, ты передаешь бокал [npc.name]. [pc.speech(Похоже, тебе не помешает глоток. Держи.)]</p><p>[npc.speech(О-о, спасибо! Обожаю «Кошачью изысканность»!)] [npc.name] радостно отвечает, прежде чем поднести бокал к [npc.her] [npc.lips] и жадно осушить этот сливочный алкогольный напиток.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 3)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает денег на стакан "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "охват")+")",
								UtilText.parse(getClubbersPresent(), "Попросить у Kalahari стакан "+drink.getName(false, false)+" для [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>[pc.speech(Можно мне стакан "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" из одной из полок за барной стойкой. Отступив на шаг назад, она подается вперед, демонстрируя глубокое декольте, и игриво подмигивает тебе, доставая чистый бокал из-под стойки. Поставив бокал перед тобой, львица наливает порцию крепкого алкогольного напитка, а затем пододвигает его по стойке к тебе. [kalahari.speech(Наслаждайся!)]</p><p>Выкрикнув слова благодарности сквозь фоновый шум клуба, ты передаешь бокал [npc.name]. [pc.speech(Похоже, тебе не помешало бы выпить. Держи.)]</p><p>[npc.speech(Эй, спасибо, [pc.name]! Волчье виски — просто лучший!)] [npc.name] счастливо отзывается, прежде чем поднести бокал к [npc.her] [npc.lips] и залпом осушить крепкий алкогольный напиток.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 4)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает денег на стакан "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "охват")+")",
								UtilText.parse(getClubbersPresent(), "Попросить у Kalahari стакан "+drink.getName(false, false)+" для [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>[pc.speech(Можно мне стакан "+drink.getName(false, false)+"?)] — выкрикиваешь ты сквозь шум клуба, обращаясь к Kalahari.</p><p>[kalahari.speech(Конечно, дорогуша!)] — отвечает львица. [kalahari.speech(С тебя "+Util.intToString(price)+" огней.)]</p><p>Отдав деньги Kalahari, ты наблюдаешь, как она разворачивается и достает бутылку "+drink.getName(false, false)+" ...с одной из полок за барной стойкой. Отступив на шаг назад к тебе, она наклоняется вперед, демонстрируя декольте, и игриво подмигивает тебе, доставая чистый бокал из-под стойки. Поставив бокал перед тобой, львица наливает порцию крепкого алкогольного напитка, а затем пододвигает его по барной стойке к тебе. [kalahari.speech(Наслаждайся!)]</p><p>Перекрикивая фоновый шум клуба, ты выкрикиваешь слова благодарности и передаешь бокал [npc.name]. [pc.speech(Похоже, тебе не помешает выпить. Держи.)]</p><p>[npc.speech(Спасибо, [pc.name]! Этот ром просто лучший!)] [npc.name] счастливо отвечает, прежде чем поднести бокал к [npc.her] [npc.lips] и залпом осушить крепкий алкогольный напиток.</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 5)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					return new Response("Поговорить", UtilText.parse(getClubbersPresent(), "Поговори с [npc.name], чтобы узнать [npc.herHim] получше."), WATERING_HOLE_BAR_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==7) {
					return new Response("Флиртовать", UtilText.parse(getClubbersPresent(), "Начать флиртовать с [npc.name]."), WATERING_HOLE_BAR_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==8) {
					return new Response("Поцеловать",
							UtilText.parse(getClubbersPresent(), "Сделать шаг вперед и поцеловать [npc.name]."+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_BAR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("Лапать",
							UtilText.parse(getClubbersPresent(), "Прижаться к [npc.name] и начать лапать [npc.herHim]."+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_BAR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==11) {
					return new Response("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что тебе пора уходить, но ты надеешься снова увидеть [npc.herHim].</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]"),
							WATERING_HOLE_BAR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==12) {
					return new Response("Расстаться",
							UtilText.parse(getClubbersPresent(), "Придумать предлог, чтобы отделаться от [npc.name].</br>[style.italicsBad(Удалить этого персонажа из игры.)]"),
							WATERING_HOLE_BAR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
				}
			}
				
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_TALK = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_FLIRT = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KISS = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_GROPE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_LOSE_COMPANY = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_DRINK = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_TALK = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_TALK");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_FLIRT = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT")
					+ "<p>"
						+ Main.game.getNpc(Kalahari.class).getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT_END");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO")
					+ getKalahariStatus(true, this.getSecondsPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(getKalahariBreakTimeLeft()<=0) {
				if(index==1) {
					return new Response("Прервать перерыв", "У Kalahari закончилось время перерыва. Дай ей вернуться к работе.", WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME) {
						@Override
						public void effects() {
							Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
							Main.game.getNpc(Kalahari.class).equipClothing();
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Поговорить", "Сейчас Kalahari интересует только секс!", null);
						
					} else {
						return new Response("Поговорить", "Поговори с Kalahari, чтобы узнать её получше.", WATERING_HOLE_KALAHARI_BREAK_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_TALK.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Флиртовать", "Сейчас Kalahari интересует только секс!", null);
						
					} else {
						return new Response("Флиртовать", "Начать флиртовать с Kalahari.", WATERING_HOLE_KALAHARI_BREAK_FLIRT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_FLIRT.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Поцеловать", "Сейчас Kalahari интересует только секс!", null);
						
					} else if(!likesKiss(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("Поцеловать", "Похоже, Kalahari сейчас не настроена на поцелуи. Было бы лучше сначала узнать её немного лучше.", null);
						
					} else {
						return new Response("Поцеловать", "Наклониться вперед и поцеловать Kalahari.", WATERING_HOLE_KALAHARI_BREAK_KISS) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_KISS.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Лапать", "Сейчас Kalahari интересует только секс!", null);
						
					} else if(!likesGroping(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("Лапать", "Ты понимаешь, что Kalahari плохо отреагирует на любые попытки доминировать и ласкать её. Лучше сначала немного пофлиртовать с ней.", null);
						
					} else {
						return new Response("Лапать", "Прижаться к Kalahari и начать ласкать её.", WATERING_HOLE_KALAHARI_BREAK_GROPE) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_GROPE.getSecondsPassed()));
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, true);
								Main.game.getNpc(Kalahari.class).displaceClothingForAccess(CoverableArea.BREASTS, null);
								Main.game.getNpc(Kalahari.class).displaceClothingForAccess(CoverableArea.VAGINA, null);
							}
						};
					}
					
				} else if(index==5) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Секс (доминант)", "Тебе нужно полностью настроить Kalahari на нужный лад, прежде чем заняться с ней сексом.", null);
					} else {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kalahari.class), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						};
						if(Main.game.getPlayer().isTaur()) { // Player is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kalahari.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							};
						}
						
						return new ResponseSex("Секс (доминант)",
								!Main.game.getPlayer().isTaur()
									?"Притянуть Kalahari к себе на колени и начать доминирующий секс."
									:"Встать, поднимая Kalahari вместе с собой, и начать доминирующий секс.",
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_DOM"));
					}
					
				}
				//TODO requires several improvements to sex AI and positioning first
//				else if(index==6) {
//					if(!Main.game.getPlayer().isFeminine()) {
//						return new Response("Kruger threesome", "Kruger is gynephilic, so wouldn't be interested in a threesome with you and Kalahari.", null);
//						
//					} else {
//						return new ResponseSex("Kruger threesome", "Have a threesome with Kruger as the dom, and you and Kalahari as the subs.",
//								true, true,
//								new SMKrugerThreesome(
//										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), SexPositionSlot.DOGGY_BEHIND)),
//										Util.newHashMapOfValues(
//												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
//												new Value<>(Main.game.getNpc(Kalahari.class), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
//								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
//								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_SUB"));
//					}
//					
//				}
				else if(index==0) {
					return new Response("Закончить", "Дать Kalahari вернуться к работе.", WATERING_HOLE_KALAHARI_BREAK_END) {
						@Override
						public void effects() {
							Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
							Main.game.getNpc(Kalahari.class).equipClothing();
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK")
					+ getKalahariStatus(true, this.getSecondsPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_TALK = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_FLIRT = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_FLIRT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_KISS = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_KISS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_GROPE = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE")
					+ "<p>"
						+ Main.game.getNpc(Kalahari.class).getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_BREAST_REVEAL")
						+ Main.game.getNpc(Kalahari.class).getVaginaDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_PUSSY_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX = new DialogueNode("Завершено", "Kalahari нужно вернуться к работе...", true) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kalahari.class))>=Main.game.getNpc(Kalahari.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Вернуться в основной зал клуба.", WATERING_HOLE_MAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_END = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode WATERING_HOLE_VIP = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_BLOCKED", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				if(index==1) {
					if(hasPartner()) {
						return new Response("Крюгер", UtilText.parse(getClubbersPresent(), "Ты не можешь разговаривать с Кругером, пока с тобой [npc.name]."), null);
						
					} else if(Main.game.getNpc(Kruger.class).getLastTimeHadSex() >= Main.game.getMinutesPassed()-(60*12)) {
						return new Response("Крюгер", "Сегодня ты уже занималась сексом с Крюгером. Хотя ты была бы не против еще немного развлечься с ним, ты понимаешь, что его терпение скоро лопнет, если ты будешь постоянно к нему возвращаться. Ты всегда сможешь прийти и оседлать его член завтра вечером.", null);
						
					} else {
						return new Response("Крюгер", "Подойти к Крюгеру и поздороваться.", WATERING_HOLE_VIP_KRUGER) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER"));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER.getSecondsPassed(), false));
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Kruger.class), true);
							}
						};
					}
				}
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("Поговорить", "Поговорить с Крюгером немного.", WATERING_HOLE_VIP_KRUGER_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_TALK.getSecondsPassed(), false));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isFeminine()) {
					return new Response("Флиртовать", "Пофлиртовать с Крюгером.", WATERING_HOLE_VIP_KRUGER_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FLIRT.getSecondsPassed(), false));
						}
					};
				} else {
					return new Response("Флиртовать", "Крюгер — гинефил, поэтому он не заинтересован в интимной близости с тобой.", null);
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Поцеловала", "Крюгер — гинефил, поэтому он не заинтересован в интимной близости с тобой.", null);
					
				} else if(!likesKiss(Main.game.getNpc(Kruger.class), false)) {
					return new Response("Поцеловала", "Похоже, Крюгер не особо горит желанием целоваться.", null);
					
				} else {
					return new Response("Поцеловала", "Сразу видно, что Крюгер хочет тебя поцеловать. Подайся вперед и позволь ему это сделать.", WATERING_HOLE_VIP_KRUGER_KISSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_KISSED.getSecondsPassed(), false));
						}
					};
				}
				
			} else if(index==4) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Ласки", "Крюгер — гинефил, поэтому он не заинтересован в интимной близости с тобой.", null);
					
				} else if(!likesGroping(Main.game.getNpc(Kruger.class), false)) {
					return new Response("Ласки", "Похоже, Крюгера сейчас не особо тянет пощупать тебя.", null);
					
				} else {
					return new Response("Ласки", "Ты чувствуешь, что Крюгер жаждет физического контакта. Придвинься к нему поближе и позволь ему ласкать тебя.", WATERING_HOLE_VIP_KRUGER_FELT_UP) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FELT_UP.getSecondsPassed(), false));
						}
					};
					
				}
				
			} else if(index==5) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Секс (сабмиссив)", "Крюгер — гинефил, поэтому он не заинтересован в интимной близости с тобой.", null);
					
				} else if(!likesSex(Main.game.getNpc(Kruger.class), false)) {
					return new Response("Секс (сабмиссив)", "Похоже, Крюгера сейчас не особо тянет на секс с тобой.", null);
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
						return new Response("Секс (сабмиссив)",
								"Крюгеру нужен доступ к"
									+ (Main.game.getPlayer().hasVagina()
										?(Main.game.isAnalContentEnabled()
											?" твоему рту, киске или анусу"
											:" либо к твоему рту, либо к киске")
										:(Main.game.isAnalContentEnabled()
											?" либо рот, либо задницу"
											:" твой рот"))
								+ " чтобы перетрахать тебя!",
								null);
					}
					return new ResponseSex("Секс (сабмиссив)", "Скажи Крюгеру, что хочешь, чтобы он оттрахал тебя...",
							true, true,
							new SMKrugerChair(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
							null,
							null,
							WATERING_HOLE_VIP_KRUGER_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_SEX_AS_SUB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
							} else if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
							}
						}
					};
				}
				
			} else if(index==0) {
				return new Response("Уйти", "Скажи Крюгеру, что тебе пора уходить.", WATERING_HOLE_VIP_KRUGER_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_TALK = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_FLIRT = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_FLIRT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_KISSED = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_KISSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_FELT_UP = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_FELT_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_AFTER_SEX = new DialogueNode("Завершено", "Крюгер закончил с тобой, и, измотанная сексом, ты охотно позволяешь ему столкнуть тебя с себя.", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_LEAVE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Танцевать", UtilText.parse(getClubbersPresent(), "Потанцуй немного с [npc.namePos]."), WATERING_HOLE_DANCE_FLOOR_DANCE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_DANCE_FLOOR_DANCE.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("Поцеловать",
							UtilText.parse(getClubbersPresent(), "Сделать шаг вперед и поцеловать [npc.name]."+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_DANCE_FLOOR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								
							}
						}
					};
					
				} else if(index==3) {
					return new Response("Лапать",
							UtilText.parse(getClubbersPresent(), "Прижаться к [npc.name] и начать ощупывать [npc.herHim]."+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] может отреагировать на это не лучшим образом!)]")),
							WATERING_HOLE_DANCE_FLOOR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что тебе пора уходить, но ты надеешься снова увидеть [npc.herHim].</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]"),
							WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Расстаться",
							UtilText.parse(getClubbersPresent(), "Придумать предлог, чтобы отделаться от [npc.name].</br>[style.italicsBad(Удалить этого персонажа из игры.)]"),
							WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Танцевать", "Потанцуй немного.", WATERING_HOLE_DANCE_FLOOR_DANCE);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_DANCE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_DANCE", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_DANCE_SOLO");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_KISS = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_GROPE = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY = new DialogueNode("Поилочка", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS = new DialogueNode("Туалеты", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				if(Main.game.getCurrentDialogueNode()==WATERING_HOLE_TOILETS_USE) {
					return new Response("Туалет", "Ты уже сидишь на унитазе...", null);
				}
				return new Response("Туалет", "Воспользоваться туалетом.", WATERING_HOLE_TOILETS_USE);
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==WATERING_HOLE_TOILETS_WASH) {
					return new Response("Умыться", "Ты уже умываешься...", null);
				}
				List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
				return new Response("Умыться",
						"Используйте раковины, чтобы умыться.<br/>[style.italicsGood(Это очистит вашу "+Util.inventorySlotsToParsedStringList(washSlots, Main.game.getPlayer())+", а также любую одежду в этих слотах.)]<br/>[style.italicsMinorBad(Это <b>не</b> очищает спутников.)]",
							WATERING_HOLE_TOILETS_WASH) {
					@Override
					public void effects() {
						for(InventorySlot slot : washSlots) {
							Main.game.getPlayer().removeDirtySlot(slot, true);
							AbstractClothing c = Main.game.getPlayer().getClothingInSlot(slot);
							if(c!=null) {
								c.setDirty(Main.game.getPlayer(), false);
							}
						}
					}
				};
				
			}
			
			if(hasPartner()) {
				if(index==3) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Секс в кабинке",
								UtilText.parse(getClubbersPresent(), "[npc.Name] [style.colourBad(не испытывает к тебе влечения)], и поэтому не желает заниматься с тобой сексом..."),
								null);
					}
					if(likesSex(getPartner(), false)) {
						return new ResponseSex("Секс в кабинке", UtilText.parse(getClubbersPresent(), "Попытаться заняться сексом с [npc.name] в одной из туалетных кабинок."),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								WATERING_HOLE_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX", getClubbersPresent()));
						
					} else {
						if(getClubbersPresent().get(0).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
							return new ResponseSex("«Изнасилование» в кабинке", UtilText.parse(getClubbersPresent(),
										"Хотя [npc.she] [npc.do] не кажется заинтересованной в сексе прямо сейчас, [npc.namePos] '"+Fetish.FETISH_NON_CON_SUB.getName(getClubbersPresent().get(0))+"' фетиш означает, что [npc.she] с удовольствием поиграет с тобой в «изнасилование» в одной из туалетных кабинок."),
									false, false,
									new SMStallSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public boolean isRapePlayBannedAtStart(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									WATERING_HOLE_TOILETS_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_RAPE", getClubbersPresent()));
							
						} else {
							return new Response("Секс в кабинке",
									UtilText.parse(getClubbersPresent(), "Попытаться заняться сексом с [npc.name] в одной из туалетных кабинок.</br>[style.italicsBad([npc.She] может отреагировать на это не слишком хорошо!)]"),
									WATERING_HOLE_TOILETS_SEX_REJECTED) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
									Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_TOILETS_SEX_REJECTED.getSecondsPassed(), false));
								}
								@Override
								public boolean isSexHighlight() {
									return true;
								}
							};
						}
					}
					
				} else if(index==4) {
					// If the partner wants normal sex, add the option for rape play to index 4 instead of 3:
					if(likesSex(getPartner(), false) && getClubbersPresent().get(0).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						return new ResponseSex("«Изнасилование» в кабинке", UtilText.parse(getClubbersPresent(),
								"Хотя [npc.she] была бы рада обычному сексу с тобой, ты мог бы воспользоваться [npc.namePos] '"+Fetish.FETISH_NON_CON_SUB.getName(getClubbersPresent().get(0))+"' фетиш, чтобы заставить [npc.herHim] заняться каким-нибудь насилием в одной из туалетных кабинок."),
							false, false,
							new SMStallSex(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public boolean isRapePlayBannedAtStart(GameCharacter character) {
									return false;
								}
							},
							null,
							null,
							WATERING_HOLE_TOILETS_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_RAPE_PLAY", getClubbersPresent()));
					}
				}
				
			} else {
				if(index==3) {
					boolean penisAvailable = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
					boolean vaginaAvailable = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
					
					if((penisAvailable && !Main.game.getPlayer().isTaur()) || vaginaAvailable) {
						return new Response("Глори-хоул (использовать)",
								"В паре туалетных кабинок есть «дыры славы». Подойди к одной из них и позволь человеку по ту сторону обслужить тебя.",
								WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY) {
							@Override
							public void effects() {
								spawnSubGloryHoleNPC("stranger");
							}
						};
						
					} else if(penisAvailable && Main.game.getPlayer().isTaur()) {
						return new Response("Глори-хоул (использовать)",
								"Из-за особенностей телосложения твоей [pc.legRace], ты не можешь принять удобную позу, чтобы воспользоваться глори-хоулом...",
								null);
						
					} else {
						return new Response("Глори-хоул (использовать)",
								"У вас нет доступа к своим гениталиям, поэтому вы не можете получить обслуживание в «дыре славы».",
								null);
					}
					
				} else if(index==4) {
					if((Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true))
							|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							|| (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
						return new Response("Глори-хоул (обслуживание)",
								"В паре туалетных кабинок есть «дыры славы». Опустись на колени и приготовься обслужить всё, что покажется в отверстии.",
								WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY) {
							@Override
							public void effects() {
								spawnDomGloryHoleNPC("stranger");
								spawnDomGloryHoleNPC("stranger");
							}
						};
					
					} else {
						return new Response("Глори-хоул (обслуживание)",
								"У вас нет доступа к вашему рту, гениталиям или анусу, поэтому вы не можете обслужить незнакомцев в «дырах славы».",
								null);
					}
					
				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==WATERING_HOLE_TOILETS_POSTERS) {
						return new Response("Плакаты", "Ты уже рассматриваешь плакаты...", null);
					}
					return new Response("Плакаты", "Рассмотреть плакаты.", WATERING_HOLE_TOILETS_POSTERS);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY = new DialogueNode("Туалеты", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Если подумать, тебе не особо хочется, чтобы какой-то незнакомец развлекался с твоими интимными местами...", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
				
			} else if(index==1) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("Использовать дыру славы", UtilText.parse(characters.get(0), "Сделайте так, как говорит [npc.name], и подойдите к «дыре славы»."),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_USING", characters));
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX = new DialogueNode("Туалеты", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Выйти из кабинки.", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY = new DialogueNode("Туалеты", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Уйти", "Если подумать, тебе не особо и хочется сосать члены незнакомцев...", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
				
			} else if(index==1) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("Начать (закрыть дверь)", "Закрой дверь, чтобы обеспечить себе хоть какую-то приватность, пока ты начинаешь обслуживать стоящие перед тобой члены.",
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(
										new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
										new Value<>(characters.get(1), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_SERVICING", characters));
				
			} else if(index==2) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("Начать (на виду у всех)", "Оставь дверь открытой, чтобы все в туалете могли наблюдать за тем, как ты обслуживаешь стоящие перед тобой члены.",
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(
										new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
										new Value<>(characters.get(1), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_SERVICING_PUBLIC", characters));
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX = new DialogueNode("Туалеты", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.sex.isPublicSex()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX_PUBLIC", getGloryHoleCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX", getGloryHoleCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Выйти из кабинки.", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
			}
			return null;
		}
	};
	
	private static String gloryholeNpcNameDescriptor ="";
	private static void spawnDomGloryHoleNPC(String genericName) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, true), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped()) {
			@Override
			public void turnUpdate() {
				if(this.getGenitalArrangement()==GenitalArrangement.NORMAL) { // Hide ass areas if normal genitals (not entirely sure why this was added...)
					this.setAreaKnownByCharacter(CoverableArea.ASS, Main.game.getPlayer(), false);
					this.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
				}
			}
		};
		
		npc.setRaceConcealed(true);
		
		double rnd = Math.random();
		if(rnd<0.1f && !gloryholeNpcNameDescriptor.equals("wasted")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			gloryholeNpcNameDescriptor ="wasted";
			npc.setGenericName("wasted "+genericName);
			
		} else if(Math.random()<0.3f && !gloryholeNpcNameDescriptor.equals("drunk")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			gloryholeNpcNameDescriptor ="drunk";
			npc.setGenericName("drunk "+genericName);
			
		} else if(Math.random()<0.4f && !gloryholeNpcNameDescriptor.equals("tipsy")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			gloryholeNpcNameDescriptor ="tipsy";
			npc.setGenericName("tipsy "+genericName);
			
		} else {
			gloryholeNpcNameDescriptor = Main.game.getCharacterUtils().setGenericName(npc, genericName, Util.newArrayListOfValues(gloryholeNpcNameDescriptor));
		}
		
		npc.setDescription("[npc.Name] — один из завсегдатаев «Водяной дыры», который, желая отдохнуть от танцпола, забрел в туалеты и застал тебя за обслуживанием славных дырок...");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(npc.hasPenis()) {
			npc.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
		}

		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		
		npc.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void spawnSubGloryHoleNPC(String genericName) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped());
		
		npc.setRaceConcealed(true);
		
		List<String> descriptors;
		double rnd = Math.random();
		if(rnd<0.1f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			descriptors = Util.newArrayListOfValues("wasted", "intoxicated");
			
		} else if(Math.random()<0.3f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			descriptors = Util.newArrayListOfValues("drunk");
			
		} else if(Math.random()<0.4f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			descriptors = Util.newArrayListOfValues("tipsy");
			
		} else {
			descriptors = Util.newArrayListOfValues("horny", "desperate", "horny");
		}
		npc.setGenericName(Util.randomItemFrom(descriptors)+" "+genericName);
		
		npc.setDescription("[npc.Name] — один из завсегдатаев «Водяной дыры», который, желая отдохнуть от танцпола, забрел в туалеты, чтобы заняться обслуживанием славных дырок...");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(Math.random()>0.75f) {
			npc.addFetish(Fetish.FETISH_ORAL_GIVING);
		}
		
		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		npc.setAssVirgin(false);
		npc.setFaceVirgin(false);
		
		npc.setAllAreasKnownByCharacter(Main.game.getPlayer(), false);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNode WATERING_HOLE_TOILETS_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "С [npc.Name] на сегодня хватит...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getPartner())>=getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_NO_ORGASM", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Увидеться снова",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что надеетесь снова увидеть [npc.herHim].</br>[style.italicsGood(Сохраняет этого персонажа, которого затем можно будет снова встретить в клубе.)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Надеяться, что нет (мягко)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Ответить уклончиво, втайне надеясь, что вы больше не увидите [npc.name].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Надеяться, что нет (грубо)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Грубо сказать [npc.name], что вас интересовал только трах [npc.herHim].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN = new DialogueNode("Поилочка", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_SEX_REJECTED = new DialogueNode("Туалеты", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX_REJECTED", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_USE = new DialogueNode("Туалеты", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_USE", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS_WASH = new DialogueNode("Туалеты", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_WASH", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS_POSTERS = new DialogueNode("Туалеты", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_POSTERS", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	// Dom partner:
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENERATE_DOM = new DialogueNode("Поилочка", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_DOM", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DOM_PARTNER_REACT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_CONTACTS_DOM = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_CONTACTS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Решить перестать искать того, кого вы уже встречали раньше.", WATERING_HOLE_MAIN);
			}
			int count = 1;
			for(GameCharacter character : getSavedClubbers(false)) {
				if(count==index) {
					if(!character.isAttractedTo(Main.game.getPlayer())) {
						return new Response(character.getName(true),
								UtilText.parse(character, "[npc.Name] [style.colourBad(больше не испытывает к вам влечения)] и вряд ли захочет проводить с вами время в клубе.<br/>([npc.She] — это [npc.a_fullRace(true)].)"),
								null);
					}
					if(Main.game.getMinutesPassed()-((NPC)character).getLastTimeEncountered()<12*60) {
						return new Response(character.getName(true),
								UtilText.parse(character, "Вы уже встречали [npc.name] в клубе сегодня, поэтому [style.colourBad(вы не сможете снова встретить [npc.herHim] до завтра)]."),
								null);
					}
					return new Response(character.getName(true),
							UtilText.parse(character, "Искать [npc.name] среди толпы гуляк.<br/>([npc.She] — это [npc.a_fullRace(true)].)"),
							WATERING_HOLE_FIND_CONTACT_DOM) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
//							character.setAffection(Main.game.getPlayer(), 5);
							domPartnerNightlyAffection = 5;
							resetPreviousBehaviour(); 
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_FIND_CONTACT_DOM = new DialogueNode("Поилочка", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT_DOM", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DOM_PARTNER_REACT.getResponse(responseTab, index);
		}
	};

	
	private static ClubberBehaviour currentBehaviour = ClubberBehaviour.INTRODUCTION;
	private static int turnsAtPlace = 0;
	private static boolean buyingDrinks = true;
	private static int domPartnerNightlyAffection = 0;
	
	private static String incrementDominantPartnerAffection(int increment) {
		domPartnerNightlyAffection += increment;
		domPartnerNightlyAffection = Math.max(-100, Math.min(100, domPartnerNightlyAffection));
		if(domPartnerNightlyAffection<=getPartner().getAffection(Main.game.getPlayer()) && domPartnerNightlyAffection<100) {
			return "";
		}
		return getPartner().incrementAffection(Main.game.getPlayer(), increment);
	}
	
	private static void resetPreviousBehaviour() {
		currentBehaviour = ClubberBehaviour.INTRODUCTION;
		turnsAtPlace = 0;
		buyingDrinks = true;
		domPartnerNightlyAffection = 0;
	}
	
	private static AbstractPlaceType getCurrentPlaceType() {
		return Main.game.getPlayer().getLocationPlace().getPlaceType();
	}
	
	private static boolean isWillingToMoveLocation() {
		return turnsAtPlace>=2 || currentBehaviour == ClubberBehaviour.INTRODUCTION;
	}
	
	private static boolean isPartnerOfferingDrinks() {
		if(buyingDrinks) {
			if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.FOUR_HAMMERED.getMinimumValue();
				
			} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.ONE_TIPSY.getMinimumValue();
				
			} else {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.THREE_DRUNK.getMinimumValue();
			}
		}
		return false;
	}
	
	/*
		Sleazy - bar, dance floor, toilets
			Wants you wasted
			Doesn't care about talking
			Likes feeling up/kissing
			Wants toilet sex
		Normal - bar, dance floor, seating area
			Wants you drunk
			Needs to talk -> flirt -> kiss -> grope
			Seating area sex
		Nice - bar, seating area, home
			Wants you tipsy
			Needs to talk -> flirt -> kiss
			Invites you home
	 */
	
	private static ClubberBehaviour getClubberBehaviour() {
		
		if(isPartnerLeaving(true)) {
			return ClubberBehaviour.LEAVES;
		}
		
		if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) { // Only goes to: Bar, dance floor, and toilets.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
				&& isPartnerOfferingDrinks()
				&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if(isWillingToMoveLocation() && likesSex(getPartner(), true)) {
				return ClubberBehaviour.TOILETS;
				
			}
			if(!isWillingToMoveLocation()) {
				if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
				} else {
					if(likesKiss(getPartner(), true)) {
						if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
							return ClubberBehaviour.BAR_GROPE;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					} else {
						if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
							return ClubberBehaviour.BAR_FLIRT;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					}
				}
				
			} else {
				if(getCurrentPlaceType()!=ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
				} else {
					if(likesKiss(getPartner(), true)) {
						if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
							return ClubberBehaviour.BAR_GROPE;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					} else {
						if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
							return ClubberBehaviour.BAR_FLIRT;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					}
				}
			}
			
		} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) { // Only goes to: Bar, seating area.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
					&& isPartnerOfferingDrinks()
					&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if(likesSex(getPartner(), true)) {
				if(getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_INVITE_HOME.getPlaceType()) {
					return ClubberBehaviour.SIT_DOWN_INVITE_HOME;
				} else {
					return ClubberBehaviour.BAR_INVITE_HOME;
				}
			}
			if(likesGroping(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_FOOTSIE.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FOOTSIE) {
						return ClubberBehaviour.SIT_DOWN_FOOTSIE;
					} else {
						return ClubberBehaviour.SIT_DOWN_KISS;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
						return ClubberBehaviour.BAR_GROPE;
					} else {
						return ClubberBehaviour.BAR_KISS;
					}
				}
			}
			if(likesKiss(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_KISS.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_KISS) {
						return ClubberBehaviour.SIT_DOWN_KISS;
					} else {
						return ClubberBehaviour.SIT_DOWN_FLIRT;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_KISS) {
						return ClubberBehaviour.BAR_KISS;
					} else {
						return ClubberBehaviour.BAR_FLIRT;
					}
				}
			}
			if((getCurrentPlaceType()==ClubberBehaviour.BAR_FLIRT.getPlaceType() || isWillingToMoveLocation()) && isPartnerOfferingDrinks()) {
				if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
					return ClubberBehaviour.BAR_FLIRT;
				} else {
					return ClubberBehaviour.BAR_TALK;
				}
				
			} else {
				if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FLIRT) {
					return ClubberBehaviour.SIT_DOWN_FLIRT;
				} else {
					return ClubberBehaviour.SIT_DOWN_TALK;
				}
			}
				
		} else { // Only goes to: Bar, dance floor, and seating area.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
					&& isPartnerOfferingDrinks()
					&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_SEX.getPlaceType()) && likesSex(getPartner(), true)) {
				return ClubberBehaviour.SIT_DOWN_SEX;
			}
			if(likesGroping(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_FOOTSIE.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FOOTSIE) {
						return ClubberBehaviour.SIT_DOWN_FOOTSIE;
					} else {
						return ClubberBehaviour.SIT_DOWN_KISS;
					}
					
				} else if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
						return ClubberBehaviour.BAR_GROPE;
					} else {
						return ClubberBehaviour.BAR_KISS;
					}
				}
			}
			if(likesKiss(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_KISS.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_KISS) {
						return ClubberBehaviour.SIT_DOWN_KISS;
					} else {
						return ClubberBehaviour.SIT_DOWN_FLIRT;
					}
					
				} else if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_KISS) {
						return ClubberBehaviour.DANCE_KISS;
					} else {
						return ClubberBehaviour.DANCE;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_KISS) {
						return ClubberBehaviour.BAR_KISS;
					} else {
						return ClubberBehaviour.BAR_FLIRT;
					}
				}
			}
			if(getCurrentPlaceType()==ClubberBehaviour.DANCE.getPlaceType() || !isWillingToMoveLocation()) { // If dancing, just move back to bar
				if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
					return ClubberBehaviour.BAR_FLIRT;
				} else {
					return ClubberBehaviour.BAR_TALK;
				}
				
			} else {
				return ClubberBehaviour.DANCE;
			}
		}
	}
	
	private static void applyBehaviourEffects() {
		ClubberBehaviour newBehaviour = getClubberBehaviour();
		
		turnsAtPlace++;

		if(newBehaviour==ClubberBehaviour.LEAVES) {
			currentBehaviour = newBehaviour;
			return;
		}
		
		if(currentBehaviour.getPlaceType()!=newBehaviour.getPlaceType()) {
			if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_BAR)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_BAR", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_DANCE_FLOOR)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_DANCE_FLOOR", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_SEATING_AREA", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_TOILETS", getClubbersPresent()));
			}

			turnsAtPlace=0;
		}
		
		currentBehaviour = newBehaviour;
		
		Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, currentBehaviour.getPlaceType());
	}
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER = new DialogueNode("Поилочка", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			ClubberBehaviour behaviour = currentBehaviour;

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_"+behaviour.toString(), getClubbersPresent()));

			UtilText.nodeContentSB.append(getClubberStatus(this.getSecondsPassed(), true));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
//			if(isEndConditionMet(0)) {
//				return getEndResponse(index, 0);
//			}
			
			ClubberBehaviour behaviour = currentBehaviour;
			
			switch(behaviour) {
				case LEAVES:
					if(index==9) {
						return new Response("Попрощаться",
								UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что, несмотря на то, как ты обошлась с [npc.herHim] этим вечером, ты надеешься снова увидеть [npc.herHim].</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LEAVES_SAVE_CLUBBER", getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==10) {
						return new Response("Расстаться",
								UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что ты не хочешь больше видеть [npc.herHim].</br>[style.italicsBad(Удалить этого персонажа из игры.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LEAVES_LOSE_COMPANY", getClubbersPresent()));
								removeClubbers();
							}
						};
					}
					break;
				case BAR_DRINK:
					if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) {
						if(index==1) {
							// Accept rum
							return new Response("Принять ром",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Принять бокал рома «Черная крыса», который тебе предложил [npc.name].<br/>"
											+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									getPartner().incrementAlcoholLevel(-0.05f); // TO stop them from drinking to collapse
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_RUM", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
							
						} else if(index==2) {
							// Refuse rum
							return new Response("Отклонить ром",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Отклонить бокал рома «Черная крыса», который тебе предложил [npc.name]."),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_RUM", getClubbersPresent()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
									buyingDrinks = false;
								}
							};
						}
						
					} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) {
						if(index==1) {
							// Accept Feline's fancy/beer
							return new Response((Main.game.getPlayer().isFeminine()
										?"Принять «Кошачью прихоть\""
										:"Принять «Собачью страсть»"),
									(Main.game.getPlayer().isFeminine()
										?UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Принять бокал «Кошачьего каприза», который вам предложила [npc.name].<br/>"
												+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy")))
										:UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Принять бутылку «Собачьей страсти», которую вам предложила [npc.name].<br/>"
												+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush")))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_FELINES_FANCY", getClubbersPresent()));
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_BEER", getClubbersPresent()));
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									}
									if(getPartner().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy").applyEffect(getPartner(), getPartner()));
									} else {
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush").applyEffect(getPartner(), getPartner()));
									}
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
						} else if(index==2) {
							// Refuse Feline's fancy/beer
							return new Response((Main.game.getPlayer().isFeminine()
										?"Отказаться от «Кошачьего каприза»"
										:"Отказаться от «Собачьей страсти»"),
									(Main.game.getPlayer().isFeminine()
											?UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Отказаться от бокала «Кошачьего каприза», который вам предложила [npc.name].")
											:UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Отказаться от бутылки «Собачьей страсти», которую вам предложила [npc.name].")),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_FELINES_FANCY", getClubbersPresent()));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_BEER", getClubbersPresent()));
									}
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
									buyingDrinks = false;
								}
							};
						}
						
					} else {
						if(index==1) {
							// Accept whiskey
							return new Response("Принять виски",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Принять бокал «Волчьего виски», который вам предложила [npc.name].<br/>"
										+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_WOLF_WHISKEY", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
							
						} else if(index==2) {
							// Refuse whiskey
							return new Response("Отказаться от виски",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Отказаться от стакана волчьего виски, который предложил [npc.name]."),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_WOLF_WHISKEY", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
									buyingDrinks = false;
								}
							};
						}
					}
					break;
				case BAR_FLIRT:
					if(index==1) {
						// Flirt back
						return new Response("Пофлиртовать в ответ",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Положительно отреагировать на кокетливые замечания [npc.namePos] и пофлиртовать с [npc.herHim] в ответ."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_FLIRT_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
						
					} else if(index==2) {
						// Shut down
						return new Response("Проявить пренебрежение",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Негативно отреагировать на кокетливые замечания [npc.namePos] и велеть [npc.herHim] остановиться."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_FLIRT_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case BAR_GROPE:
					if(index==1) {
						// Let them grope you
						return new Response("Подчиниться",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Прильнуть к [npc.name] и позволить [npc.herHim] ласкать тебя."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_GROPE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Оттолкнуть",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Велеть [npc.name] убраться от тебя подальше."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_GROPE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case BAR_INVITE_HOME:
					if(index==1) {
						return new Response("Пойти домой",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Последовать за [npc.name] к дому [npc.her], где [npc.sheIs] наверняка захочет переспать с тобой..."),
								WATERING_HOLE_DOM_PARTNER_TAKEN_HOME) {
							@Override
							public void effects() {
								
								for(GameCharacter clubber : getClubbersPresent()) {
									if(!clubber.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
										clubber.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, true);
									} else {
										clubber.returnToHome();
									}
									Main.game.getPlayer().setLocation(WorldType.DOMINION, clubber.getLocation(), false);
									Main.game.setRequestAutosave(false); // Autosaving when moving world here will cause the NPC to disappear when loaded
								}
								
							}
						};
						
					} else if(index==4) {
						return new Response("Отказать (мягко)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что тебе не очень хочется возвращаться к [npc.her], но ты надеешься увидеть [npc.herHim] в клубе как-нибудь в другой раз.</br>[style.italicsGood(Сохраняет этого персонажа, которого можно будет снова встретить в клубе.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BAR_INVITE_HOME_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Отказать (грубо)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Резко заявить [npc.name], что у тебя вообще нет никакого желания заниматься сексом с [npc.herHim].</br>[style.italicsBad(Убирает этого персонажа из игры.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BAR_INVITE_HOME_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case BAR_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("Поцеловать", 
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Прильнуть к [npc.name] и начать страстно целоваться с [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Оттолкнуть",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Оттолкнуть [npc.name] и сказать [npc.herHim], чтобы [npc.her] приберег(ла) свои [npc.lips] для [npc.herself]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case BAR_TALK:
					if(index==1) {
						// Continue conversation
						return new Response("Продолжить разговор",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "С радостью продолжить болтать с [npc.name]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_TALK_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(5));
							}
						};
					} else if(index==2) {
						// Show boredom
						return new Response("Молчать",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Неопределенно хмыкнуть, ожидая, пока [npc.name] продолжит."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_TALK_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
							}
						};
					}
					break;
				case DANCE:
					if(index==1) {
						// Dance
						return new Response("Танцевать",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Выйти на танцпол и начать танцевать с [npc.name]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
					} else if(index==2) {
						// Step back
						return new Response("Отказаться",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что тебе не интересно танцевать с [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case DANCE_GROPE:
					if(index==1) {
						// Let them grope you
						return new Response("Подчиниться",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Прильнуть к [npc.name] и позволить [npc.herHim] ласкать тебя."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_GROPE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Оттолкнуть",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Велеть [npc.name] убраться от тебя подальше."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_GROPE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case DANCE_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("Поцеловать", 
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Прильнуть к [npc.name] и начать страстно целоваться с [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Оттолкнуть",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Оттолкнуть [npc.name] и сказать [npc.herHim], чтобы [npc.her] приберег(ла) свои [npc.lips] для [npc.herself]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case INTRODUCTION:
					break;
				case SIT_DOWN_FLIRT:
					if(index==1) {
						// Flirt back
						return new Response("Пофлиртовать в ответ",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Положительно отреагировать на кокетливые замечания [npc.namePos] и пофлиртовать с [npc.herHim] в ответ."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FLIRT_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Проявить пренебрежение",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Негативно отреагировать на кокетливые замечания [npc.namePos] и велеть [npc.herHim] остановиться."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FLIRT_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case SIT_DOWN_FOOTSIE:
					boolean bothBipeds = true;
					if(Main.game.getPlayer().isTaur() || getPartner().isTaur()) {
						bothBipeds = false;
					}
					// If both partners are bipeds, play footsie. If not, feeling up occurs instead.
					if(index==1) {
						// Enjoy
						return new Response(
								bothBipeds
									?"Потереться ножками"
									:"Подчиниться",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(),
										bothBipeds
											?"Позволить [npc.namePos] [npc.foot] ласкать тебя вплоть до паха и начать отвечать на [npc.her] кокетливые движения."
											:"Прижаться задом к [npc.name] и позволить [npc.herHim] ласкать тебя."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FOOTSIE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Отстраниться",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(),
										bothBipeds
											?"Отстраниться от [npc.namePos] [npc.foot], а затем сердито велеть [npc.herHim] остановиться."
											:"Отстраниться от нежелательных прикосновений [npc.namePos], а затем сердито велеть [npc.herHim] остановиться."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FOOTSIE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case SIT_DOWN_INVITE_HOME:
					if(index==1) {
						return new Response("Пойти домой",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Последовать за [npc.name] к дому [npc.her], где [npc.sheIs] наверняка захочет переспать с тобой..."),
								WATERING_HOLE_DOM_PARTNER_TAKEN_HOME) {
							@Override
							public void effects() {
								
								for(GameCharacter clubber : getClubbersPresent()) {
									if(!clubber.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
										clubber.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, true);
									} else {
										clubber.returnToHome();
									}
									Main.game.getPlayer().setLocation(WorldType.DOMINION, clubber.getLocation(), false);
									Main.game.setRequestAutosave(false); // Autosaving when moving world here will cause the NPC to disappear when loaded
								}
							}
						};
						
					} else if(index==4) {
						return new Response("Отказать (мягко)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что тебе не очень хочется возвращаться к [npc.her], но ты надеешься увидеть [npc.herHim] в клубе как-нибудь в другой раз.</br>[style.italicsGood(Сохраняет этого персонажа, которого можно будет снова встретить в клубе.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "SIT_DOWN_INVITE_HOME_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Отказать (грубо)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Резко заявить [npc.name], что у тебя вообще нет никакого желания заниматься сексом с [npc.herHim].</br>[style.italicsBad(Убирает этого персонажа из игры.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "SIT_DOWN_INVITE_HOME_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case SIT_DOWN_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("Поцеловать",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Позволить [npc.name] начать целовать тебя."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Оттолкнуть",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Отклонить попытку [npc.namePos] поцеловать тебя и оттолкнуть [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case SIT_DOWN_SEX:
					if(index==1) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};

						if(getPartner().isTaur()) { // Partner is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						
						return new ResponseSex("Секс (сабмиссив)",
								UtilText.parse(getClubbersPresent(), "Сделать так, как говорит [npc.name], и начать подчиняться [npc.herHim] во время секса."),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_START", getClubbersPresent()));
						
					} else if(index==4) {
						return new Response("Отказать (мягко)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что тебе не очень-то хочется трахаться с [npc.herHim], но ты надеешься увидеть [npc.herHim] в клубе как-нибудь в другой раз.</br>[style.italicsGood(Сохраняет этого персонажа, которого можно будет снова встретить в клубе.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Отказать (грубо)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Резко заявить [npc.name], что у тебя вообще нет никакого желания заниматься сексом с [npc.herHim].</br>[style.italicsBad(Убирает этого персонажа из игры.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case SIT_DOWN_TALK:
					if(index==1) {
						// Continue conversation
						return new Response("Продолжить разговор",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "С радостью продолжить болтать с [npc.name]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_TALK_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(5));
							}
						};
					} else if(index==2) {
						// Show boredom
						return new Response("Молчать",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Неопределенно хмыкнуть, ожидая, пока [npc.name] продолжит."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_TALK_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
							}
						};
					}
					break;
				case TOILETS:
					if(index==1) {
						return new ResponseSex("Секс в кабинке", UtilText.parse(getClubbersPresent(), "Позволить [npc.name] оттрахать тебя в одной из туалетных кабинок."),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START", getClubbersPresent()));
						
					} else if(index==4) {
						return new Response("Отказать (мягко)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что тебе не очень-то хочется трахаться с [npc.herHim], но ты надеешься увидеть [npc.herHim] в клубе как-нибудь в другой раз.</br>[style.italicsGood(Сохраняет этого персонажа, которого можно будет снова встретить в клубе.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Отказать (грубо)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Резко заявить [npc.name], что у тебя вообще нет никакого желания заниматься сексом с [npc.herHim].</br>[style.italicsBad(Убирает этого персонажа из игры.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
			}
			
			if(behaviour!=ClubberBehaviour.LEAVES) {
				if(index==9) {
					return new Response("Попрощаться",
							UtilText.parse(getClubbersPresent(), "Сказать [npc.name], что тебе пора уходить, но ты надеешься снова увидеть [npc.herHim].</br>[style.italicsGood(Сохранить этого персонажа, чтобы его можно было снова встретить в клубе.)]"),
							UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Расстаться",
							UtilText.parse(getClubbersPresent(), "Придумать предлог, чтобы отделаться от [npc.name].</br>[style.italicsBad(Удалить этого персонажа из игры.)]"),
							UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_REACT = new DialogueNode("Поилочка", "", true, true) {
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("Продолжить", UtilText.parse(getClubbersPresent(), "Посмотреть, что [npc.name] захочет сделать дальше..."), WATERING_HOLE_DOM_PARTNER) {
					@Override
					public void effects() {
						applyBehaviourEffects();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TAKEN_HOME = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getLabel() {
			return UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Квартира [npc.NamePos]");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Секс", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Заниматься подчиненным сексом с [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_SEX", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("Отказаться",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что ты не особо хочешь возвращаться к [npc.her], чтобы заняться сексом...</br>[style.italicsGood(Сохраняет этого персонажа, которого можно будет снова встретить в клубе.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole",
								"WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==5) {
				return new Response("Сердито отказаться",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что это совсем не то, о чем ты думал(а), когда принимал(а) приглашение [npc.her] зайти к [npc.her]!</br>[style.italicsBad(Убирает этого персонажа из игры.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole",
								"WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getLabel() {
			return UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Квартира [npc.NamePos]");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Увидеться снова",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что надеетесь снова увидеть [npc.herHim].</br>[style.italicsGood(Сохраняет этого персонажа, которого затем можно будет снова встретить в клубе.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Надеяться, что нет (мягко)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Ответить уклончиво, втайне надеясь, что вы больше не увидите [npc.name].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==3) {
				return new Response("Надеяться, что нет (грубо)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Грубо сказать [npc.name], что вас интересовал только трах [npc.herHim].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "С [npc.Name] на сегодня хватит...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Увидеться снова",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что надеетесь снова увидеть [npc.herHim].</br>[style.italicsGood(Сохраняет этого персонажа, которого затем можно будет снова встретить в клубе.)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Надеяться, что нет (мягко)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Ответить уклончиво, втайне надеясь, что вы больше не увидите [npc.name].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Надеяться, что нет (грубо)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Грубо сказать [npc.name], что вас интересовал только трах [npc.herHim].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode UTIL_NEUTRAL_DIALOGUE_NO_TEXT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
}
