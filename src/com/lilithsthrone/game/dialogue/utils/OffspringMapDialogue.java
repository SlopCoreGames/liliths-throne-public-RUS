package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;

/**
 * @since 0.3.2
 * @version 0.4.4
 * @author Innoxia
 */
public class OffspringMapDialogue {
	
	private static List<OffspringSeed> getOffspringList() {
		AbstractWorldType worldType = Main.game.getPlayer().getWorldLocation();
		AbstractPlaceType placeType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		
		AbstractEncounter enc = Main.game.getPlayer().getLocationPlace().getPlaceType().getEncounterType();
		return Main.game.getOffspringNotSpawned(os->
			os.getSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType)
				&& (worldType==WorldType.HARPY_NEST
						?(os.getHalfDemonSubspecies()==null || os.getHalfDemonSubspecies().getRace()==Race.HARPY)
						:(os.getHalfDemonSubspecies()==null || os.getHalfDemonSubspecies().getRace()!=Race.HARPY))
				// Allow youko in Elis alleyways:
				|| (enc==Encounter.getEncounterFromId("innoxia_elis_alleyway")
						&& (os.getSubspecies()==Subspecies.FOX_ASCENDANT
							|| os.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
							|| os.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC))
				// Allow Angels in Dominion:
				|| (enc==Encounter.DOMINION_ALLEY
						&& (os.getSubspecies()==Subspecies.ANGEL))
				// Allow alligators, slimes, and rats in Dominion canals:
				|| (enc==Encounter.DOMINION_CANAL
						&& (os.getSubspecies()==Subspecies.ALLIGATOR_MORPH
							|| os.getSubspecies()==Subspecies.SLIME
							|| os.getSubspecies()==Subspecies.RAT_MORPH))
				);
	}
	
	
	public static final DialogueNode OFFSPRING_CHOICE = new DialogueNode("Список потомства", "-", true) {
		List<OffspringSeed> offspringList;
		@Override
		public void applyPreParsingEffects() {
			offspringList = getOffspringList();
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean noOffspring = offspringList.isEmpty();
			
			UtilText.nodeContentSB.append(
					"<p>Аркана-чары на карте заставляют список имен всех ваших еще не встреченных отпрысков проецироваться в воздухе перед вами, а соответствующие точки показывают их точное местоположение на самой физической карте."
						+ (noOffspring
								? " Похоже, что на данный момент у вас нет еще не встреченных потомков..."
								: " К счастью, кажется, один из ваших отпрысков находится именно в этой области. Взглянув на ключ, вы увидите, кто именно это...")
					+ "</p>"
					+ "<p style='text-align:center;'>");
			
			if(Main.game.getOffspringNotSpawned(os->true).isEmpty()) {
				UtilText.nodeContentSB.append("[style.colourDisabled(Нет доступного потомства)]");
				
			} else {
				boolean foundAnyInArea = false;
				List<OffspringSeed> offspringToShow = Main.game.getOffspringNotSpawned(npc->offspringList.contains(npc));
				if(!offspringToShow.isEmpty()) {
					foundAnyInArea = true;
					UtilText.nodeContentSB.append("Потомство [style.colourMinorGood(возможно, в этой области)]:<br/>");
				}
				for(OffspringSeed os : offspringToShow) {
					UtilText.nodeContentSB.append("<span style='color:"+os.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(os.getName())+"</span>");
					
					String unknownMotherName = "Неизвестно";
					if(os.getMother()==null && !os.getMotherName().equals("???")) {
						unknownMotherName = os.getMotherName();
					}
					String unknownFatherName = "Неизвестно";
					if(os.getFather()==null && !os.getFatherName().equals("???")) {
						unknownFatherName = os.getFatherName();
					}

					UtilText.nodeContentSB.append(" ("+(os.isFeral()?"<span style='color:"+RaceStage.FERAL.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(RaceStage.FERAL.getName())+"</span> ":"")+"<span style='color:"+os.getSubspecies().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(os.getSubspecies().getName(os.getBody()))+"</span>)"
							+ " Mother: "+(os.getMother()==null?unknownMotherName:(os.getMother().isPlayer()?"[style.colourExcellent(Вы)]":os.getMother().getName(true)))
							+ " Отец: "+(os.getFather()==null?unknownFatherName:(os.getFather().isPlayer()?"[style.colourExcellent(Вы)]":os.getFather().getName(true)))
							+ "<br/>");
				}
				
				offspringToShow = Main.game.getOffspringNotSpawned(npc->!offspringList.contains(npc));
				if(!offspringToShow.isEmpty()) {
					if(foundAnyInArea) {
						UtilText.nodeContentSB.append("<br/>");
					}
					UtilText.nodeContentSB.append("Потомство [style.colourMinorBad(не в этой области)]:<br/>");
				}
				for(OffspringSeed os : offspringToShow) {
					UtilText.nodeContentSB.append("[style.colourDisabled("+Util.capitaliseSentence(os.getName())+")]");
					
					String unknownMotherName = "Неизвестно";
					if(os.getMother()==null && !os.getMotherName().equals("???")) {
						unknownMotherName = os.getMotherName();
					}
					String unknownFatherName = "Неизвестно";
					if(os.getFather()==null && !os.getFatherName().equals("???")) {
						unknownFatherName = os.getFatherName();
					}
					
					UtilText.nodeContentSB.append(" (<span style='color:"+os.getSubspecies().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(os.getSubspecies().getName(os.getBody()))+"</span>)"
							+ " Mother: "+(os.getMother()==null?unknownMotherName:(os.getMother().isPlayer()?"[style.colourExcellent(Вы)]":os.getMother().getName(true)))
							+ " Отец: "+(os.getFather()==null?unknownFatherName:(os.getFather().isPlayer()?"[style.colourExcellent(Вы)]":os.getFather().getName(true)))
							+ "<br/>");
				}
			}
				
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<OffspringSeed> offspringToShow = Main.game.getOffspringNotSpawned(npc->offspringList.contains(npc));
			offspringToShow.addAll(Main.game.getOffspringNotSpawned(npc->!offspringList.contains(npc)));
			
			if (index == 0) {
				return new Response("Назад", "В конце концов, вы решаете не искать здесь свое потомство.", Main.game.getDefaultDialogue(false));
				
			} else if(index-1 < offspringToShow.size()) {
				OffspringSeed offspring = offspringToShow.get(index-1);
				
				if(!offspringList.contains(offspring)) {
					return new Response(offspring.getName(),
							offspring.getName()+" не удалось найти в этой области из-за "+offspring.hisHer()+" подвида...",
							null);
				}

				return new Response(offspring.getName(),
						"С помощью зачарованной карты вы понимаете, что скоро сможете найти "+offspring.getName()+" в этой области...",
						offspring.getEncounterDialogue()) {
					@Override
					public Colour getHighlightColour() {
						return offspring.getFemininity().getColour();
					}
					@Override
					public void effects() {
						NPC npc = new NPCOffspring(offspring);

						npc.setLocation(Main.game.getPlayer(), true);

						npc.equipClothing(EquipClothingSetting.getAllClothingSettings());

						Main.game.setActiveNPC(npc);

						Main.game.getTextStartStringBuilder().append(
								"<p><i>"
									+ UtilText.parse(npc,
										"Изучив карту, вы вскоре обнаруживаете, что ваш [npc.daughter], [npc.name], живет именно в этом районе переулков. Убрав карту, вы начинаете поиски [npc.herHim]...")
								+ "</i></p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
