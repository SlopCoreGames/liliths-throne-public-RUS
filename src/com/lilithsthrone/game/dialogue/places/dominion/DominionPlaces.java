package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Callie;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.CultistDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.RentalMommyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanDateFinalRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanFirstDate;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanFirstDoubleDate;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanRepeatDate;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.2
 * @author Innoxia
 */
public class DominionPlaces {

	public static final int TRAVEL_TIME_STREET = 2*60;
	
	public static boolean isCloseToEnforcerHQ() {
		return Vector2i.getDistance(Main.game.getPlayerCell().getLocation(), Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_ENFORCER_HQ).getLocation())<4f;
	}
	
	private static String getExtraStreetFeatures() {
		StringBuilder mommySB = new StringBuilder();
		StringBuilder occupantSB = new StringBuilder();
		StringBuilder cultistSB = new StringBuilder();
		StringBuilder reindeerSB = new StringBuilder();
		
		Set<NPC> characters = new HashSet<>(Main.game.getNonCompanionCharactersPresent());
		characters.addAll(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));

		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_NYAN_APARTMENT) {
			mommySB.append("<p>[style.boldPinkLight(Квартира Nyan:)]<br/>"
							+ (Main.game.getNpc(Nyan.class).getWorldLocation()==WorldType.NYANS_APARTMENT
								?"Nyan живет в этом районе, так что, если захотите, вы могли бы заглянуть в ее многоквартирный дом и навестить ее..."
								:"Nyan живет в этом районе, хотя вы знаете, что в этот час она будет на работе, так что особого смысла идти к ее дому сейчас нет...")
						+ "</p>");
		}
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_CALLIE_BAKERY) {
			mommySB.append("<p>[style.boldBrown(Пекарня The Creamy Bakery:)]</p>");
			mommySB.append(UtilText.parseFromXMLFile("nnxx/callie_bakery", "EXTERIOR"));
		}
		
		for(NPC npc : characters) {
			if(npc instanceof RentalMommy) {
				mommySB.append(
						UtilText.parse(npc,
								"<p>"
									+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Дом Мамочки:</b><br/>"
									+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM
										?"Дом Мамочки находится в конце этой улицы, но из-за бушующего аркана-шторма ее привычная скамейка сейчас пустует. Если захотите пообщаться с ней, лучше вернуться после того, как шторм утихнет..."
										:"Дом Мамочки находится в конце этой улицы, и, глядя в ту сторону, вы видите, что она сидит на своей привычной скамейке снаружи. Все еще в своей футболке «Арендная мамочка», она явно готова к работе, как обычно...")
								+ "</p>"));
				break;
			}
			if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
				occupantSB.append("<p>");
				occupantSB.append(UtilText.parse(npc,
								"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>[npc.NamePos] Квартира:</b><br/>После переезда из дома Lilaya, [npc.name] поселилась в многоквартирном доме неподалеку от этого места."));

				if(npc.isAsleep()) {
					occupantSB.append(UtilText.parse(npc,
							" Если захотите, вы могли бы навестить [npc.race], но так как [npc.sheIs] сейчас [style.colourSleep(спит)], вам придется разбудить [npc.herHim]..."));
				} else {
					occupantSB.append(UtilText.parse(npc, " Если захотите, вы могли бы навестить [npc.race]..."));
				}
				occupantSB.append("<br/>");
				occupantSB.append(UtilText.parse(npc, "<i>[npc.Name] спит в промежутке между [style.time(\", \")"+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]</i>"));
				occupantSB.append("</p>");
				break;
			}
			
			if(npc instanceof Cultist) {
				cultistSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Часовня культистов:</b><br/>"
							+ UtilText.parse(npc, "Вы помните, что часовня [npc.namePos] находится где-то неподалеку, и если возникнет желание, вы легко сможете найти её снова...")
						+ "</p>");
				break;
			}
			
			if(npc instanceof ReindeerOverseer) {
				reindeerSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_REINDEER_MORPH.toWebHexString()+";'>Рабочие-олени:</b><br/>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM
								?UtilText.parse(npc, "Все морфы-олени укрылись от идущего арканального шторма. Если вы захотите поговорить с их надзирателем, вам нужно будет вернуться после того, как шторм утихнет.")
								:UtilText.parse(npc, "Большая группа морфов-оленей усердно работает, разгребая снег. Их лидер, [npc.a_race], выкрикивает приказы и мечется между рабочими, проверяя, чтобы работа выполнялась на [npc.her] удовлетворение. Хотя рабочие выглядят слишком занятыми, чтобы остановиться и поговорить, вы, вероятно, сможете перекинуться парой слов с надзирателем, если захотите."))
						+ "</p>");
				break;
			}
		}
		
		mommySB.append(cultistSB.toString()).append(occupantSB.toString()).append(reindeerSB.toString());
		
		AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
		if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) {
			mommySB.append("<p>");
				mommySB.append("[style.boldPinkLight(Чокер [style.Mule]:)]<br/>");
				mommySB.append("Надев свой чокер [style.mule], вы подаете сигнал любым проходящим мимо рабам-кентаврам из Dominion Express, что готовы удовлетворить их сексуальные потребности.");
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					mommySB.append(" Однако из-за идущего арканального шторма [style.colourMinorBad(шанс встретить кого-то из них равен нулю)]...");
				} else if(!Main.game.isExtendedWorkTime()) {
					mommySB.append(" Однако, поскольку в это время они не на работе, [style.colourMinorBad(шанс встретить кого-то из них равен нулю)]...");
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					mommySB.append(" Поскольку ты не можешь пошевелить ртом, [style.colourMinorBad(шансов почти нет)], что кто-то из них к тебе приблизится...");
				} else {
					mommySB.append(" Хотя Dominion — огромный город, в Dominion Express работает множество кентавров-рабов, а значит, есть хотя бы [style.colourMinorGood(небольшой шанс)], что ты наткнешься на одного из них...");
				}
			mommySB.append("</p>");
		}
		
		
		return mommySB.toString();
	}
	
	private static List<Response> getExtraStreetResponses() {
		List<Response> mommyResponses = new ArrayList<>();
		List<Response> occupantResponses = new ArrayList<>();
		List<Response> cultistResponses = new ArrayList<>();
		List<Response> reindeerResponses = new ArrayList<>();

		Set<NPC> characters = new HashSet<>(Main.game.getNonCompanionCharactersPresent());
		characters.addAll(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_NYAN_APARTMENT) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
				//TODO
//				if(Main.game.getNpc(Nyan.class).getWorldLocation()==WorldType.NYANS_APARTMENT) {
//					mommyResponses.add(new Response("Visit Nyan", "Head over to Nyan's apartment building and pay her a visit.", Main.game.getDefaultDialogue(false)));
//					
//				} else {
//					mommyResponses.add(new Response("Visit Nyan", "Nyan is out at work at this time of day, so you're unable to head over to her apartment building and pay her a visit...", null));
//				}
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend))) {
				int dateCost = 4000;
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWeekendDated)) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
						mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"На этих выходных ты уже водил Nyan на свидание. Придется ждать следующих выходных, чтобы выбраться на двойное свидание с ней и [nyanmum.name]...",
								null));
					} else {
						mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"На этих выходных ты уже водил Nyan и [nyanmum.name] на свидание. Придется ждать следующих выходных, чтобы выбраться с ними снова...",
								null));
					}
					
				} else if((Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY) && (Main.game.isHourBetween(20, 23))) {
					if(Main.game.getNpc(Nyan.class).getWorldLocation()!=WorldType.NYANS_APARTMENT) {
						mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"Nyan и [nyanmum.name] сейчас нет дома. Придется зайти после того, как закончится их рабочий день...",
								null));
						
					} else if(Main.game.getPlayer().getMoney()<dateCost) {
						mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"'Дубовая поляна' — очень дорогое место для свидания. Тебе понадобится как минимум "+Util.intToString(dateCost)+" огней, прежде чем пригласить Nyan и [nyanmum.name] на свидание туда.",
								null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"Ты не сможешь пойти на свидание в ресторан, если не сможешь ничего съесть!<br/>[style.italicsMinorBad(Чтобы пригласить Nyan и [nyanmum.name] на свидание, тебе нужно иметь доступ к своему рту...)]",
								null));
						
					} else {
						mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoney(dateCost, "охват")+")",
								"Забери Nyan и [nyanmum.name] из их жилого дома и отвези их на свидание в ресторан 'Дубовая поляна'.",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
									?NyanDateFinalRepeat.DOUBLE_DATE_START
									:NyanFirstDoubleDate.DATE_START));
					}
					
				} else {
					mommyResponses.add(new Response("Двойное свидание ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
							"Сейчас ты не можешь пригласить Nyan и [nyanmum.name] на свидание...<br/><i>Нужно, чтобы это был либо а "
								+ (Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
									?"[style.italicsMinorGood(Пятница)]"
									:"[style.italicsMinorBad(Пятница)]")
								+" или "
								+ (Main.game.getDayOfWeek()==DayOfWeek.SATURDAY
									?"[style.italicsMinorGood(Суббота)]"
									:"[style.italicsMinorBad(Суббота)]")
								+", в промежутке между "
								+ (Main.game.isHourBetween(20, 23)
									?"[style.italicsMinorGood([unit.time(20)]-[unit.time(23)])]"
									:"[style.italicsMinorBad([unit.time(20)]-[unit.time(23)])]")
								+" чтобы сводить Nyan и [nyanmum.name] на свидание!",
							null));
				}
				
			} else {
				int dateCost = 2500;
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWeekendDated)) {
					mommyResponses.add(new Response("Свидание с Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
							"Вы уже водили Nyan на свидание на этих выходных. Придется подождать до следующих выходных, прежде чем пойдете с ней снова...",
							null));
					
				} else if((Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY)
						&& (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23)) {
					if(Main.game.getNpc(Nyan.class).getWorldLocation()!=WorldType.NYANS_APARTMENT) {
						mommyResponses.add(new Response("Свидание с Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"Nyan сейчас на работе. Тебе придется зайти после того, как ее рабочий день закончится...",
								null));
						
					} else if(Main.game.getPlayer().getMoney()<dateCost) {
						mommyResponses.add(new Response("Свидание с Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"'Дубовая поляна' кажется очень дорогим местом для свидания. Тебе нужно как минимум "+Util.intToString(dateCost)+" огней, прежде чем пригласить Nyan на свидание туда.",
								null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						mommyResponses.add(new Response("Свидание с Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
								"Ты не сможешь пойти на свидание в ресторан, если тебе нечего будет есть!<br/>[style.italicsMinorBad(Чтобы пригласить Nyan на свидание, тебе нужно иметь доступ к своему рту...)]",
								null));
						
					} else {
						mommyResponses.add(new Response("Свидание с Nyan ("+UtilText.formatAsMoney(dateCost, "охват")+")",
								"Забери Nyan из ее жилого дома и отвези ее на свидание в ресторан 'Дубовая поляна'.",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)
									?NyanDateFinalRepeat.SOLO_DATE_START
									:(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)
										?NyanRepeatDate.DATE_START
										:NyanFirstDate.DATE_START)));
					}
					
				} else {
					mommyResponses.add(new Response("Свидание с Nyan ("+UtilText.formatAsMoneyUncoloured(dateCost, "охват")+")",
							"Ты не можешь пригласить Nyan на свидание в это время...<br/><i>Для свидания с Nyan требуется как минимум "
								+ (Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
									?"[style.italicsMinorGood(Пятница)]"
									:"[style.italicsMinorBad(Пятница)]")
								+" или "
								+ (Main.game.getDayOfWeek()==DayOfWeek.SATURDAY
									?"[style.italicsMinorGood(Суббота)]"
									:"[style.italicsMinorBad(Суббота)]")
								+", в промежутке между "
								+ (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23
									?"[style.italicsMinorGood([unit.time(18)]-[unit.time(23)])]"
									:"[style.italicsMinorBad([unit.time(18)]-[unit.time(23)])]")
								+" огней!",
							null));
				}
			}
		}
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_CALLIE_BAKERY) {
			int hourOpen = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("nnxx_callie_upgrade_2"))?7:9;
			int hourClose = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("nnxx_callie_upgrade_2"))?17:15;
			
			if(Main.game.isHourBetween(hourOpen, hourClose) && Main.game.getDayOfWeek()!=DayOfWeek.SUNDAY) {
				DialogueNode initNode = DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry_first_time");
				if(Main.game.getDialogueFlags().hasFlag("nnxx_callie_introduced")) {
					AbstractClothing playerNeckClothing = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
					if(Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_3")
							&& !Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_reaction_pending")
							&& Main.game.getPlayer().getSexCount(Main.game.getNpc(Callie.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))>0
							&& Main.game.getNpc(Callie.class).isAttractedTo(Main.game.getPlayer())
							&& Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)
							&& (playerNeckClothing!=null && playerNeckClothing.getClothingType().getId().equals("innoxia_neck_filly_choker"))
							&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getDialogueFlags().hasFlag("innoxia_callie_natalya_encountered")
									|| Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("callie_natalya_encounter_time") >= 60*60*24*3)) {
						initNode = DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry_natalya"); // Can be encountered every three days
						
					} else {
						initNode = DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry");
					}
				}
				
				mommyResponses.add(new Response("Сливочная пекарня",
						"Загляни в ближайшую пекарню 'Сливочная выпечка' и посмотри, что там внутри.<br/><i>Пекарня открыта с [style.italicsMinorGood([unit.time(\""+hourOpen+")]-[unit.time("+hourClose+")])].</i>",
								initNode) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("nnxx_callie_bakery"), PlaceType.getPlaceTypeFromId("nnxx_callie_bakery_counter"));
					}
				});
				
			} else {
				mommyResponses.add(new Response("Сливочная пекарня",
						"Ближайшая пекарня 'Сливочная выпечка' в это время суток закрыта.<br/><i>Тебе придется вернуться между"
								+ (Main.game.isHourBetween(hourOpen, hourClose)
										?" [style.italicsMinorGood([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])],"
										:" [style.italicsMinorBad([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])],")
								+ (Main.game.getDayOfWeek()!=DayOfWeek.SUNDAY
										?" [style.italicsMinorGood(понедельником и субботой)]"
										:" [style.italicsMinorBad(с понедельника по субботу)]")
								+ ".</i>",
						null));
			}
		}
		
		for(NPC npc : characters) {
			if(npc instanceof RentalMommy) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					mommyResponses.add(new Response("Мамочка", "«Мамочки» нет на её привычной скамье, и вы предполагаете, что она пережидает нынешнюю бурю в своём доме.", null));
				} else {
					mommyResponses.add(new Response("Мамочка", "Вы видите, что «Мамочка» сидит на деревянной скамье перед своим домом. Подойдите к ней и поздоровайтесь.", RentalMommyDialogue.ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.setActiveNPC(npc);	
						}
					});
				}
			}
			
			if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
//				if(!Main.game.getCharactersPresent().contains(npc)) {
//					occupantResponses.add(new Response(
//							UtilText.parse(npc, "[npc.Name]"),
//							UtilText.parse(npc, "[npc.Name] is out at work at the moment, and so you'll have to return at another time if you wanted to pay [npc.herHim] a visit..."),
//							null));
//				}
				occupantResponses.add(new Response(
						UtilText.parse(npc, "[npc.Name]"),
						UtilText.parse(npc,
								Main.game.getPlayer().getCompanions().contains(npc)
									?"Вернитесь в квартиру [npc.namePos]."
									:"Отправляйтесь к дому [npc.namePos] и навестите [npc.herHim]."),
						OccupantDialogue.OCCUPANT_APARTMENT) {
					@Override
					public void effects() {
						OccupantDialogue.initDialogue(npc, true, false);
					}
				});
			}
			
			if(npc instanceof Cultist) {
				cultistResponses.add(new Response("Часовня", UtilText.parse(npc, "Снова посетите часовню [npc.namePos]."), CultistDialogue.ENCOUNTER_CHAPEL_REPEAT) {
						@Override
						public void effects() {
							Main.game.setActiveNPC(npc);
						}
					});
			}
			
			if(npc instanceof ReindeerOverseer) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					reindeerResponses.add(new Response("Надзиратель",
							"Рабочие — морфы-олени — сейчас укрываются от идущего арканного шторма. Вам придётся вернуться позже, если вы хотите поговорить с надзирателем.",
							null));
				} else {
					reindeerResponses.add(new Response("Надзиратель", UtilText.parse(npc, "Подойди к [npc.name] и поздоровайся."), ReindeerOverseerDialogue.ENCOUNTER_START) {
							@Override
							public void effects() {
								Main.game.setActiveNPC(npc);
								npc.setPlayerKnowsName(true);
							}
						});
				}
			}
		}
		
		mommyResponses.addAll(cultistResponses);
		mommyResponses.addAll(occupantResponses);
		mommyResponses.addAll(reindeerResponses);
		
		return mommyResponses;
	}

	private static String getRandomStreetEvent() {
		int extraText = Util.random.nextInt(100) + 1;
		if (extraText <= 3) {
			return ("<p><i>Особо крупный и внушительный инкуб прокладывает себе путь сквозь толпу, держа на поводках трех высших кошкодевочек-рабынь. Каждая из них абсолютно нагая, и когда они проходят мимо, ты отчетливо видишь, как их киски сочатся смазкой от возбуждения.</i></p>");
		} else if (extraText <= 6) {
			return ("<p><i>В стороне ты видишь пару псов-Enforcer, допрашивающих подозрительного кошкопарня. Когда ты проходишь мимо, кошкопарень пытается дать деру, но его быстро валят на пол. Enforcer надевают на его запястья наручники и утаскивают в ближайший переулок.</i></p>");
		} else if (extraText <= 9) {
			return ("<p><i>Огромный билборд закрывает собой весь фасад одного из зданий на противоположной стороне улицы. На нем красуется реклама турнира «Рискни всем», обещающая великие награды любому, кто окажется достаточно силен, чтобы принять вызов. Снизу жирным красным шрифтом красуется надпись: «Скоро прием заявок!»</i></p>");
		} else if (extraText == 10) {
			return ("<p><i>A greater cat-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_cat_felines_fancy").getName(false)+ "'.</i></p>");
		} else if (extraText == 11) {
			return ("<p><i>A greater wolf-boy is handing out leaflets just in front of you, and as you pass, he shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_wolf_wolf_whiskey").getName(false)+ "'.</i></p>");
		} else if (extraText == 12) {
			return ("<p><i>A greater dog-girl is handing out leaflets just in front of you, and as you pass, she shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_dog_canine_crush").getName(false)+ "'.</i></p>");
		} else if (extraText == 13) {
			return ("<p><i>A greater horse-boy is handing out leaflets just in front of you, and as you pass, he shoves one into your hands."
					+ " You look down to see that it's just an advertisement for the drink '"+ ItemType.getItemTypeFromId("innoxia_race_horse_equine_cider").getName(false)+ "'.</i></p>");
		} else if (extraText == 14) {
			return ("<p><i>На одной стороне улицы собралась ликующая толпа, и когда ты бросаешь взгляд в их сторону, мимолетный просвет в людях позволяет тебе увидеть происходящее. Высшая кошкодевочка стоит на четвереньках, и ее подвергают двойному проникновению двумя массивными конскими членами высшего коня. Соки девушки стекают по ее ногам, а язык вываливается изо рта, пока гигантские члены входят и выходят из ее растянутых дырок.</i></p>");
		}
		return "";
	}
	
	private static String getEnforcersPresent() {
		StringBuilder sb = new StringBuilder();

		if(Main.game.getSavedEnforcers(WorldType.DOMINION).isEmpty()) {
			if(isCloseToEnforcerHQ()) {
				sb.append("<p style='text-align:center;'><i>");
					sb.append("Из-за близости [style.colourBlueDark(Enforcer HQ)] в Dominion, в этой области существует [style.italicsBad(высокий шанс)] встретить [style.colourBlueDark(Enforcer patrols)]!");
					if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
						sb.append("<br/>Однако из-за непрекращающегося шторма Аркана, в данный момент встретить патрули невозможно...");
					}
				sb.append("</i></p>");
			}
			
		} else {
			sb.append("<p style='text-align:center;'><i>");
			
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append("Из-за затянувшегося [style.italicsArcane(arcane storm)], [style.italicsGood(нет шансов)] встретить кого-либо из этих [style.colourBlueDark(Enforcer patrols)]:");
				} else if(isCloseToEnforcerHQ()) {
					sb.append("Из-за близости [style.colourBlueDark(Enforcer HQ)] в Dominion, существует [style.italicsBad(высокий шанс)] встретить одного из этих [style.colourBlueDark(Enforcer patrols)]:");
				} else {
					sb.append("Существует [style.italicsMinorBad(небольшой шанс)] наткнуться на одного из этих [style.colourBlueDark(Enforcer patrols)]:");
				}
				for(List<String> enforcerIds : Main.game.getSavedEnforcers(WorldType.DOMINION)) {
					sb.append("<br/>");
					List<String> names = new ArrayList<>();
					for(String id : enforcerIds) {
						try {
							GameCharacter enforcer = Main.game.getNPCById(id);
							names.add(UtilText.parse(enforcer, "<span style='color:"+enforcer.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					sb.append(Util.stringsToStringList(names, false));
				}
			
			sb.append("</i></p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode STREET = new DialogueNode("Улицы Dominion", "", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET"));
			if (Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				sb.append(getRandomStreetEvent());
			}

			sb.append(getExtraStreetFeatures());
			
			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_OCTOBER"));
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_SNOW"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index-1 < responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	


	public static final DialogueNode BACK_ALLEYS_SAFE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS_SAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BACK_ALLEYS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("переулки");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DARK_ALLEYS = new DialogueNode("Темные переулки", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DARK_ALLEYS", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(false));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("переулки");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode BACK_ALLEYS_CANAL = new DialogueNode("Переправа через канал", ".", false) {
		
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS_CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("переулки");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode BOULEVARD = new DialogueNode("Бульвар Dominion", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 90;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BOULEVARD"));
			
			sb.append(getExtraStreetFeatures());
			
			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_OCTOBER"));
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_SNOW"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index-1 < responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};

	private static Set<Integer> viewedNewsIndexes = new HashSet<>();
	private static boolean viewedAllNews = false;
	
	private static String getRandomNewsText() {
		List<AbstractSubspecies> possibleSubspecies = new ArrayList<>();
		for(AbstractSubspecies s : Subspecies.allSubspecies) {
			if(s.getMostCommonWorldRegions().contains(WorldRegion.DOMINION)) {
				possibleSubspecies.add(s);
			}
		}
		
		String randomFemalePerson = Util.randomItemFrom(possibleSubspecies).getSingularFemaleName(null);
		String randomMalePerson = Util.randomItemFrom(possibleSubspecies).getSingularMaleName(null);
		
		List<String> strings = Util.newArrayListOfValues(
				"грубоватый тип "+randomMalePerson+" разворачивает большой свиток, после чего откашливается и выкрикивает: [maleNPC.speech(Указом Lilith и в интересах безопасности Dominion, любой человек, замеченный на улицах в темное время суток, может быть законно подвергнут полному досмотру любым Enforcer!)]",
					Util.capitaliseSentence(UtilText.addDeterminer(randomFemalePerson))+" поднимает официальный лист бумаги с красной сургучной печатью и объявляет: [femaleNPC.speech(За любую информацию, ведущую к аресту лиц, ответственных за распространение нелегальных газет в районах под Гнездами Гарпий, назначена награда в размере двухсот тысяч Огней!)]",
					"Довольно дикая на вид суккуб в костюме ведьмы, очень напоминающем праздничный наряд на Хэллоуин, указывает на разных членов толпы и вопит: [femaleNPC.speech(Я насчитала не менее трех демонов в толпе без формы культистов! Что бы сказала Lilith, если бы увидела это сейчас?!)]",
					Util.capitaliseSentence(UtilText.addDeterminer(randomMalePerson))+" сообщает толпе несколько скучных, обыденных новостей. Ни одна из них тебя не интересует, и в конце концов ты отворачиваешься с чувством, будто только что зря потратил время.",
					Util.capitaliseSentence(UtilText.addDeterminer(randomFemalePerson))+" сообщает толпе несколько скучных, обыденных новостей. Ни одна из них тебя не интересует, и в конце концов ты отворачиваешься с чувством, будто только что зря потратил время.",
					Util.capitaliseSentence(UtilText.addDeterminer(randomMalePerson))+" вслух зачитывает список объявлений о местных лавках. Там действительно нет ничего интересного, и вскоре вы ловите себя на том, что отворачиваетесь и идете дальше.",
					Util.capitaliseSentence(UtilText.addDeterminer(randomFemalePerson))+" вслух зачитывает список объявлений о местных лавках. Там действительно нет ничего интересного, и вскоре вы ловите себя на том, что отворачиваетесь и идете дальше.");
		
		List<Integer> availableIndexes = new ArrayList<>();
		for(int i=0; i<strings.size(); i++) {
			availableIndexes.add(i);
		}
		for(Integer i : viewedNewsIndexes) {
			availableIndexes.remove(i);
		}
		if(availableIndexes.isEmpty()) {
			viewedAllNews = true;
			return "<p style='text-align:center;'>[style.italicsDisabled(Вы выслушали всё, что было сказано...)]</p>";
			
		} else {
			int index = Util.randomItemFrom(availableIndexes);
			viewedNewsIndexes.add(index);
			
			return "<p>"
						+ strings.get(index)
					+ "</p>";
		}
	}
	
	public static final DialogueNode DOMINION_PLAZA = new DialogueNode("Плаза Lilith", "", false) {
		@Override
		public void applyPreParsingEffects() {
			viewedNewsIndexes.clear();
			viewedAllNews = false;
		}
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DOMINION_PLAZA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return new Response("Новости", "Из-за непрекращающегося шторма Аркана здесь сейчас никого нет...", null);
					
				} else if(viewedAllNews) {
					return new Response("Новости", "Вы выслушали всё, что было сказано...", null);
					
				} else {
					return new Response(
							"Новости",
							"Решить остаться ненадолго и послушать одного из ораторов...", DOMINION_PLAZA_NEWS){
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append("<p>"
											+ getRandomNewsText()
											+"</p>");
								}
							};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DOMINION_PLAZA_NEWS = new DialogueNode("Плаза Lilith", ".", false, true) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Вы решаете остаться и послушать одного из многочисленных ораторов, обращающихся к толпе...</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DOMINION_PLAZA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_HOTEL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(STREET.getContent());
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_SHADED"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "HELENAS_HOTEL"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index==1) {
				return new Response("Гнездо Helena", "Воспользоваться лифтом в отеле, чтобы подняться прямиком к гнезду Helena.", HelenaHotel.HOTEL_TRAVEL_TO_NEST) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
					
			} else if(index-2 < responses.size()) {
				return responses.get(index-2);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STREET_SHADED = new DialogueNode("Улицы Dominion (в тени)", ".", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(STREET.getContent());
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_SHADED"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			 if(index!=0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CANAL = new DialogueNode("Каналы Dominion", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("каналы");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode CANAL_END = new DialogueNode("Каналы Dominion", ".", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL_END", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return AbstractEncounter.exploreArea("каналы");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};

	// Entrances and exits:

	public static final DialogueNode CITY_EXIT_SEWERS = new DialogueNode("Вход в Submission", "Войти в подземный город Submission.", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_SEWERS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Покорность", "Войти в подземный город Submission.", CITY_EXIT_SEWERS_ENTERING_SUBMISSION){
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.visitedSubmission, false);
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
						
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_SEWERS_ENTERING_SUBMISSION = new DialogueNode("Контрольно-пропускной пункт Enforcer", "Войти в подземный город Submission.", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.visitedSubmission);
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION"));
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_FIRST_TIME"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_REPEAT"));
			}
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.visitedSubmission)) {
				if (index == 1) {
					return new Response("Подтвердить", "Подтвердите кошкодевочке, что вы действительно впервые посещаете Submission.", CITY_EXIT_SEWERS_ENTERING_SUBMISSION_FIRST_TIME) {
						@Override
						public void effects() {
							Main.game.getNpc(Claire.class).setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLIME_QUEEN));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return SubmissionGenericPlaces.SEWER_ENTRANCE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_SEWERS_ENTERING_SUBMISSION_FIRST_TIME = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_FIRST_TIME_CONFIRMATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжайте свой путь через пост Enforcer.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.visitedSubmission, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_BAT_CAVERNS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_BAT_CAVERNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.getPlayer().isPartyAbleToFly()) {
						return new Response("Пещеры летучих мышей", "Так как ваши спутники не умеют летать, вы не можете воспользоваться шахтой, чтобы спуститься в Пещеры летучих мышей...", null);
						
					} else {
						return new Response("Пещеры летучих мышей", "Спуститесь по шахте, чтобы вернуться в Пещеры летучих мышей.", CITY_EXIT_BAT_CAVERNS_FLY_DOWN) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SHAFT, false);
							}
						};
					}
					
				} else {
					return new Response("Пещеры летучих мышей", "Так как вы не умеете летать, вы не можете воспользоваться шахтой, чтобы спуститься в Пещеры летучих мышей...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_EXIT_BAT_CAVERNS_FLY_DOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_BAT_CAVERNS_FLY_DOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BatCaverns.SHAFT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CITY_EXIT = new DialogueNode("Выход из Dominion", "", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isDiscoveredWorldMap()) {
				return "<p>Пара элитных демонов-Enforcer внимательно следят за каждым, кто входит в город или покидает его. Теперь, когда у вас есть карта и дела в мире за пределами Dominion, ничто не мешает вам отправиться в путь прямо сейчас.</p>";
				
			} else {
				return "<p>Пара элитных демонов-Enforcer внимательно следят за каждым, кто входит в город или покидает его. Хотя ничто не мешает вам отправиться в мир за пределами города, на данный момент у вас нет причин покидать Dominion, а без карты, как вы полагаете, будет очень легко заблудиться.</p><p>Ваши поиски способа вернуться в прежний мир, несомненно, со временем приведут вас в места за пределами Dominion, но пока что ваши дела сосредоточены внутри самого города.</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new ResponseEffectsOnly("Путешествие по миру", "Покинуть Dominion и отправиться в большой мир...") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.WORLD_MAP, Main.game.getPlayer().getGlobalLocation(), false);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else {
					return new Response("Путешествие по миру", "Вы не знаете, как выглядит остальной мир, и пока что ваши дела сосредоточены внутри города.", null);
				}

			} else {
				return null;
			}
		}
	};
}
