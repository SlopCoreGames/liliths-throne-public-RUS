package com.lilithsthrone.game.dialogue.companions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.85
 * @version 0.3.9.2
 * @author Innoxia
 */
public class SlaveDialogue {

	private static NPC slave;
	private static NPC characterForSex;
	private static NPC characterForSexSecondary;
	
	private static List<NPC> charactersPresent;
	
	private static boolean initFromCharactersPresent;
	private static boolean dollStatueInterrupted;
	
	public static void initDialogue(NPC targetedSlave, boolean initFromCharactersPresent) {
		CompanionManagement.initManagement(SLAVE_START, 2, targetedSlave);
		slave = targetedSlave;
		characterForSex = targetedSlave;

		characterForSexSecondary = null;
		charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
		charactersPresent.removeIf((npc) -> !Main.game.getPlayer().getCompanions().contains(npc) && (!npc.isSlave() || !npc.getOwner().isPlayer()) && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()));
		if(charactersPresent.size()>1) {
			if(charactersPresent.contains(Main.game.getPlayer().getMainCompanion()) && !getSlave().equals(Main.game.getPlayer().getMainCompanion())) {
				characterForSexSecondary = (NPC) Main.game.getPlayer().getMainCompanion();
				
			} else {
				characterForSexSecondary = charactersPresent.stream().filter((npc) -> !npc.equals(getSlave())).findFirst().get();
			}
		}
		
		SlaveDialogue.initFromCharactersPresent = initFromCharactersPresent;
		dollStatueInterrupted = false;
	}
	
	private static DialogueNode getAfterSexDialogue() {
		if(initFromCharactersPresent) {
			return CharactersPresentDialogue.AFTER_SEX;
		} else {
			return AFTER_SEX;
		}
	}
	
	private static NPC getSlave() {
		return slave;
	}
	
	private static boolean isDoll() {
		return getSlave().isDoll();
	}
	
	private static boolean isDollStatue() {
		return getSlave().isDollStatue();
	}
	
	private static ResponseTag getDollSexTag() {
		if(isDollStatue()) {
			if(getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_ALL_FOURS)) {
				return ResponseTag.PREFER_DOGGY;
				
			} else if(getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_MISSIONARY) || getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_BRIDGE)) {
				return ResponseTag.PREFER_MISSIONARY;
				
			} else if(getSlave().hasSlaveJobSetting(SlaveJob.DOLL_STATUE, SlaveJobSetting.DOLL_STATUE_SQUATTING)) {
				return ResponseTag.PREFER_ORAL;
			}
		}
		return null;
	}
	
	public static boolean isDollStatueInterrupted() {
		return dollStatueInterrupted;
	}
	
	private static SlaveJob getCurrentJob() {
		return getSlave().getSlaveJob(Main.game.getHourOfDay());
	}

	public static String getTextFilePath() {
		if(getSlave().isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}

	private static String getThreesomeTextFilePath() {
		if(characterForSex.isRelatedTo(Main.game.getPlayer()) || (characterForSexSecondary!=null && characterForSexSecondary.isRelatedTo(Main.game.getPlayer()))) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}
	
	private static void applyReactionReset() {
		if(getSlave().isVisiblyPregnant()){
			getSlave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getSlave(), true);
		}
	}

	private static boolean isCompanionSexPublic() {
		return Main.game.getPlayer().getLocationPlace().isPopulated()
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS);
	}
	
	private static GameCharacter enslavementTarget;
	private static DialogueNode followupEnslavementDialogue;

	public static GameCharacter getEnslavementTarget() {
		return enslavementTarget;
	}

	public static void setEnslavementTarget(GameCharacter enslavementTarget) {
		SlaveDialogue.enslavementTarget = enslavementTarget;
	}
	
	public static DialogueNode getFollowupEnslavementDialogue() {
		return followupEnslavementDialogue;
	}

	public static void setFollowupEnslavementDialogue(DialogueNode followupEnslavementDialogue) {
		SlaveDialogue.followupEnslavementDialogue = followupEnslavementDialogue;
	}

	private static SMGeneric getGenericSlaveSexManager(
			List<GameCharacter> dominantParticipants,
			List<GameCharacter> submissiveParticipants,
			List<ResponseTag> tags,
			boolean forcePlayerFullControl) {
		return getGenericSlaveSexManager(dominantParticipants,
						submissiveParticipants,
						getDominantSpectators(),
						getSubmissiveSpectators(),
						tags,
						forcePlayerFullControl,
						new HashMap<>());
	}
	
	private static SMGeneric getGenericSlaveSexManager(
			List<GameCharacter> dominantParticipants,
			List<GameCharacter> submissiveParticipants,
			List<GameCharacter> dominantSpectators,
			List<GameCharacter> submissiveSpectators,
			List<ResponseTag> tags,
			boolean forcePlayerFullControl,
			Map<GameCharacter, SexPace> startingSexPaces) {
		return new SMGeneric(
				dominantParticipants,
				submissiveParticipants,
				dominantSpectators,
				submissiveSpectators,
				tags) {
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(startingSexPaces!=null && startingSexPaces.containsKey(character)) {
					return startingSexPaces.get(character);
				}
				return super.getStartingSexPaceModifier(character);
			}
			@Override
			public boolean isPublicSex() {
				return isCompanionSexPublic();
			}
			@Override
			public SexControl getSexControl(GameCharacter participant) {
				if(participant.isPlayer() && forcePlayerFullControl) {
					return SexControl.FULL;
				}
				return super.getSexControl(participant);
			}
			@Override
			public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
				ImmobilisationType immobilisationType = null;
				for(AbstractPlaceUpgrade upgrade : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
					immobilisationType = upgrade.getImmobilisationType();
					if(immobilisationType!=null) {
						break;
					}
				}
				
				Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
				map.put(immobilisationType, new HashMap<>());
				map.get(immobilisationType).put(dominantParticipants.get(0), new HashSet<>());
				if(immobilisationType!=null) {
					for(GameCharacter character : submissiveParticipants) {
						if(character.isSlave() && !character.isDollStatue()) {
							map.get(immobilisationType).get(dominantParticipants.get(0)).add(character);
						}
					}
				}
				
				map.put(ImmobilisationType.COMMAND, new HashMap<>());
				map.get(ImmobilisationType.COMMAND).put(dominantParticipants.get(0), new HashSet<>());
				for(GameCharacter character : submissiveParticipants) {
					if(character.isDollStatue()) {
						map.get(ImmobilisationType.COMMAND).get(dominantParticipants.get(0)).add(character);
					}
				}

				map.put(ImmobilisationType.SLEEP, new HashMap<>());
				map.get(ImmobilisationType.SLEEP).put(dominantParticipants.get(0), new HashSet<>());
				for(GameCharacter character : submissiveParticipants) {
					if(character.isAsleep()) {
						map.get(ImmobilisationType.SLEEP).get(dominantParticipants.get(0)).add(character);
					}
				}
				
				return map;
			}
		};
	}
	
	//TODO delete -> was moved into Sex init in 0.4.9.5
//	private static void applyImmobilisationText(List<GameCharacter> submissiveParticipants) {
//		// Immobilisation from room upgrades:
//		ImmobilisationType immobilisationType = null;
//		for(AbstractPlaceUpgrade upgrade : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
//			immobilisationType = upgrade.getImmobilisationType();
//			if(immobilisationType!=null) {
//				break;
//			}
//		}
//		if(immobilisationType!=null) {
//			List<GameCharacter> slaveSubs = new ArrayList<>(submissiveParticipants);
//			slaveSubs.removeIf(s->!s.isSlave() || s.isDollStatue());
//			if(!slaveSubs.isEmpty()) {
//				List<String> names = new ArrayList<>();
//				for(GameCharacter slave : slaveSubs) {
//					names.add(UtilText.parse(slave, "[npc.name]"));
//				}
//				StringBuilder sb = new StringBuilder();
//				sb.append("<p style='text-align:center;'>[style.italicsTerrible(");
//					switch(immobilisationType) {
//						case CHAINS:
//							sb.append("Thanks to the chains which have been added to this cell, ");
//							break;
//						case ROPE:
//							sb.append("Thanks to the ropes which have been added to this cell, ");
//							break;
//						case COCOON:
//						case TAIL_CONSTRICTION:
//						case TENTACLE_RESTRICTION:
//						case WITCH_SEAL:
//						case COMMAND:
//						case SLEEP:
//							break;
//					}
//					sb.append(Util.stringsToStringList(names, false));
//					if(names.size()>1) {
//						sb.append(" are");
//					} else {
//						sb.append(" is");
//					}
//					sb.append(" bound and unable to move!");
//				sb.append(")]</p>");
//				
//				Main.game.appendToTextEndStringBuilder(sb.toString());
//			}
//		}
//
//		// Doll:
//		List<GameCharacter> slaveSubs = new ArrayList<>(submissiveParticipants);
//		slaveSubs.removeIf(s->!s.isDollStatue());
//		if(!slaveSubs.isEmpty()) {
//			List<String> names = new ArrayList<>();
//			for(GameCharacter slave : slaveSubs) {
//				names.add(UtilText.parse(slave, "[npc.name]"));
//			}
//			StringBuilder sb = new StringBuilder();
//			sb.append("<p style='text-align:center;'>[style.italicsTerrible(");
//				sb.append("Having been ordered to do so, ");
//				sb.append(Util.stringsToStringList(names, false));
//				if(names.size()>1) {
//					sb.append(" are");
//				} else {
//					sb.append(" is");
//				}
//				sb.append(" remaining frozen in place!");
//			sb.append(")]</p>");
//			
//			Main.game.appendToTextEndStringBuilder(sb.toString());
//		}
//		
//		// Sleeping:
//		slaveSubs = new ArrayList<>(submissiveParticipants);
//		slaveSubs.removeIf(s->!s.isAsleep());
//		if(!slaveSubs.isEmpty()) {
//			List<String> names = new ArrayList<>();
//			for(GameCharacter slave : slaveSubs) {
//				names.add(UtilText.parse(slave, "[npc.name]"));
//			}
//			StringBuilder sb = new StringBuilder();
//			sb.append("<p style='text-align:center;'>[style.italicsTerrible(");
//				sb.append("As ");
//				if(names.size()>1) {
//					sb.append(" they are deep sleepers, ");
//				} else {
//					sb.append(UtilText.parse(slaveSubs.get(0), "[npc.sheIs] a deep sleeper, "));
//				}
//				sb.append(Util.stringsToStringList(names, false));
//				if(names.size()>1) {
//					sb.append(" remain");
//				} else {
//					sb.append(" remains");
//				}
//				sb.append(" asleep!");
//			sb.append(")]</p>");
//			
//			Main.game.appendToTextEndStringBuilder(sb.toString());
//		}
//	}
	
	public static List<GameCharacter> getDominantSpectators() {
		return Main.game.getPlayer().getCompanions();
	}

	public static List<GameCharacter> getSubmissiveSpectators() {
		// Removed in 0.4.1.5, as this was allowing sex with random characters who were on the tile that shouldn't have been involved in sex
//		List<NPC> characters = Main.game.getCharactersPresent();
//		characters.removeAll(Main.game.getPlayer().getCompanions());
//		return new ArrayList<>(characters);
		
//		List<NPC> characters = Main.game.getCharactersPresent();
//		characters.removeAll(Main.game.getPlayer().getCompanions());
//		characters.removeIf(c ->
//			(!c.isSlave() || !c.getOwner().isPlayer()) // Character is not a slave or is a slave that doesn't belong to the player
//			&& (!Main.game.getPlayer().getFriendlyOccupants().contains(c.getId()) || !c.isAttractedTo(Main.game.getPlayer()))); // AND is not a friend or is not attracted to the player
		
		List<NPC> characters = new ArrayList<>();
		for(NPC character : Main.game.getCharactersPresent()) {
			if(((character.isSlave() && character.getOwner().isPlayer()) // Add if character is player's slave
					|| (Main.game.getPlayer().getFriendlyOccupants().contains(character.getId()) && character.isAttractedTo(Main.game.getPlayer()))) // OR character is friend who is attracted to player
				&& !character.isAsleep()) { // AND not asleep
				characters.add(character);
			}
		}
		return new ArrayList<>(characters);
	}
	
	private static boolean enslavementWorked = false;
	public static final DialogueNode DEFAULT_ENSLAVEMENT_DIALOGUE = new DialogueNode("Новая рабыня", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!SlaveDialogue.getEnslavementTarget().isSlave() && SlaveDialogue.getEnslavementTarget().isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense()) {
				Main.game.getTextEndStringBuilder().append(enslavementTarget.applyEnslavementEffects(Main.game.getPlayer()));
				
			} else {
				Main.game.getTextEndStringBuilder().append(enslavementTarget.incrementAffection(Main.game.getPlayer(), -25));
			}
			
			// Generate content:
			GameCharacter target = enslavementTarget;
			AbstractClothing enslavementClothing = target.getEnslavementClothing();
			UtilText.addSpecialParsingString(enslavementClothing.getName(), true);
			UtilText.addSpecialParsingString(enslavementClothing.getClothingType().isPlural()?"them":"it", false);
			String path = "characters/enslavement";
			if(target instanceof NPCOffspring) {
				path = "characters/offspring/enslavement";
			}
			
			if(!target.isSlave() && target.isAbleToBeEnslaved() && Main.game.getPlayer().isHasSlaverLicense()) {
				if(enslavementClothing.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"))) {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_SUCCESS_COLLAR", target));
				} else {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_SUCCESS", target));
				}
				enslavementWorked = true;
				
				// Apply effects after content has been generated due to conditional checks:
				Main.game.getPlayer().addSlave((NPC) enslavementTarget);
				enslavementTarget.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
				
			} else {
				if(target.isSlave()) {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_ALREADY_SLAVE", target));
					
				} else if(!target.isAbleToBeEnslaved()) {
					if(target.getSubspecies()==Subspecies.DEMON) {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_NOT_WANTED_DEMON", target));
						
					} else {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_NOT_WANTED", target));
					}
					
				} else {
					Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile(path, "ENSLAVEMENT_FAIL_NO_LICENSE", target));
				}
				enslavementWorked = false;
			}
			
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(enslavementWorked) {
					return new Response("Продолжить",
							"Продолжить путь.",
							SlaveDialogue.getFollowupEnslavementDialogue()){
						@Override
						public void effects() {
//							Main.game.getPlayer().addSlave((NPC) enslavementTarget);
//							enslavementTarget.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
						}
						@Override
						public DialogueNode getNextDialogue(){
							return SlaveDialogue.getFollowupEnslavementDialogue();
						}
					};
					
				} else {
					return new Response("Продолжить",
							UtilText.parse(SlaveDialogue.getEnslavementTarget(), "Это не сработало, но это еще не значит, что ты закончил с [npc.name]!"),
							SlaveDialogue.getFollowupEnslavementDialogue());
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FREEDOM_DIALOG = new DialogueNode("Освобожденная рабыня", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(enslavementTarget.incrementAffection(Main.game.getPlayer(), -25));
		}
		@Override
		public String getContent() {
			GameCharacter target = enslavementTarget;
			AbstractClothing enslavementClothing = target.getEnslavementClothing();
			UtilText.addSpecialParsingString(enslavementClothing.getName(), true);
			UtilText.addSpecialParsingString(enslavementClothing.getClothingType().isPlural()?"them":"it", false);
			return UtilText.parseFromXMLFile("characters/enslavement", "ENSLAVEMENT_FAIL_FREEDOM_CERTIFICATION", target);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						UtilText.parse(SlaveDialogue.getEnslavementTarget(), "Это не сработало, но это еще не значит, что ты закончил с [npc.name]!"),
						SlaveDialogue.getFollowupEnslavementDialogue());
			}
			return null;
		}
	};
	
	private static String getSlaveStartCoreContent() {
		StringBuilder sb = new StringBuilder();
		
		if(!isDollStatue()) {
			if(getSlave().isVisiblyPregnant()) {
				// Pregnant encounters:
				if(!getSlave().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					sb.append("<p>");
						sb.append("Когда ты смотришь на [npc.race], невозможно не заметить тот факт, что [npc.sheIs] щеголяет округлившимся животом.");
						sb.append(" [npc.She] рассеянно #IF(npc.isTaur())жестикулирует в сторону#ELSEпоглаживает#ENDIF [npc.her] раздувшийся живот, пока [npc.she] отвечает тебе взглядом,");
					
					GameCharacter father = getSlave().getPregnantLitter().getFather();
					
					if(father!=null && father.isPlayer()) {
						switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
							case DISLIKE:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
									case DISOBEDIENT:
										sb.append(" даже не пытаясь скрыть ненависть в [npc.her] [npc.eyes], пока [npc.she] плюет, [npc.speech(Фу, это <i>ты</i>. Ты умудрился меня забеременеть, так что рассчитывай на отгулы. Чертов козел...)]");
										break;
									case NEUTRAL:
										sb.append(" пытаясь скрыть ненависть в [npc.her] [npc.eyes], пока [npc.she] замечает, [npc.speech(О, привет, [npc.pcName]. Ты меня забеременил, так что мне понадобится перерыв в работе.)]");
										break;
									case OBEDIENT:
										sb.append(" послушно делая [npc.her] всё возможное, чтобы скрыть ненависть во взгляде [npc.her] [npc.eyes], когда [npc.she] окликает её, [npc.speech(Привет, [npc.pcName]. Как ты наверняка видишь, ты сделала меня беременной...)]");
										break;
								}
								break;
							case NEUTRAL:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
									case DISOBEDIENT:
										sb.append(" вздыхая, [npc.speech(Привет, [npc.pcName]. Ты залетел мне, так что мне придется немного поберечь себя, ладно?)]");
										break;
									case NEUTRAL:
										sb.append(" вздыхая, [npc.speech(Привет, [npc.pcName]. Ты залетел мне...)]");
										break;
									case OBEDIENT:
										sb.append(" вздыхая, [npc.speech(Привет, [npc.pcName]. Ты залетел мне... Я позабочусь о наших детях!)]");
										break;
								}
								break;
							case LIKE:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
									case DISOBEDIENT:
										sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech([npc.PcName]! Смотри! Ты залетел мне, разве это не чудесно?! Мне нужно будет немного отдохнуть, чтобы как следует позаботиться о себе, хорошо?)]");
										break;
									case NEUTRAL:
										sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech([npc.PcName]! Ты залетел мне, разве это не чудесно?! Я позабочусь о наших детях!)]");
										break;
									case OBEDIENT:
										sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! Ты залетел мне! Я позабочусь о наших детях!)]");
										break;
								}
								break;
						}
						sb.append("</p>");
						sb.append(
								"<p>Вы [pc.step] к своей рабыне и проводите своей [pc.hands] по [npc.her] её беременному животу. Успокаивающе улыбаясь матери ваших детей, вы говорите [npc.herHim], что Lilaya сможет помочь [npc.herHim] принять роды, когда придет время.</p><p>[npc.Name] кивает в знак согласия, и после того как вы еще немного гладите и ласкаете [npc.her] её живот, вы [pc.step] назад и раздумываете, что делать дальше...</p>");
						
					} else {
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								sb.append(" вздыхая, [npc.speech(Привет, [npc.pcName]. "
											+(father==null || father.equals(getSlave())
												?"В итоге я забеременела"
												:father.getName("")+" заставил меня забеременеть")
											+", так что какое-то время буду потихоньку работать. Найди мне кого-нибудь из других рабынь на замену, ладно?)]");
								break;
							case NEUTRAL:
								sb.append(" вздыхая, [npc.speech(Привет, [npc.pcName]. "
											+(father==null || father.equals(getSlave())
												?"В итоге я забеременела"
												:father.getName("")+" заставил меня забеременеть")
											+", так что мне нужно будет немного сбавить темп, хорошо?)]");
								break;
							case OBEDIENT:
								sb.append(" покорно сообщает тебе о случившемся, [npc.speech(Привет, [npc.pcName]. "
											+(father==null || father.equals(getSlave())
												?"В итоге я забеременела"
												:father.getName("")+" заставил меня забеременеть")
											+", но я не позволю этому мешать моим обязанностям!)]");
								break;
						}
						sb.append("</p>");
						sb.append(
								"<p>Вы [pc.step] к своей рабыне и проводите [pc.hands] по её [npc.her] беременному животу. Ободряюще улыбнувшись [npc.race], вы говорите [npc.herHim], что Lilaya сможет помочь [npc.herHim] принять роды, когда придет время.</p><p>[npc.Name] подтверждает это кивком, и после того как вы еще какое-то время гладите и ласкаете [npc.her] живот, вы [pc.step] назад и гадаете, что делать дальше...</p>");
					}
				
				} else {
					sb.append(
							"<p>Глядя на [npc.race], вы видите, что [npc.sheIs] всё еще носит округлившийся живот, и [npc.she] рассеянно поглаживает [npc.her] беременный бугорок, когда [npc.she] встречается с вами взглядом,");
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append(" даже не пытаясь скрыть ненависть в глазах [npc.her] [npc.eyes], пока [npc.she] плюет: [npc.speech(Фу, это <i>ты</i>. Какого черта тебе теперь надо?!)]");
									break;
								case NEUTRAL:
									sb.append(" пытаясь скрыть ненависть в глазах [npc.her] [npc.eyes], когда [npc.she] замечает: [npc.speech(О, привет, [npc.pcName]. Чего ты хочешь?)]");
									break;
								case OBEDIENT:
									sb.append(" послушно делая [npc.her] всё возможное, чтобы скрыть ненависть в [npc.her] [npc.eyes], когда [npc.she] окликает её, [npc.speech(Привет, [npc.pcName]. Что я могу для тебя сделать?)]");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append(" вздыхая: [npc.speech(Привет, [npc.pcName]. Я тут потихоньку отдыхаю, ну, из-за беременности и всего такого, ладно?)]");
									break;
								case NEUTRAL:
									sb.append(" вздыхая: [npc.speech(Привет, [npc.pcName]. Что тебе нужно?)]");
									break;
								case OBEDIENT:
									sb.append(" вздыхая: [npc.speech(Привет, [npc.pcName]. Могу я чем-нибудь тебе помочь?)]");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! Как поживаешь?! Я сейчас немного отдыхаю, чтобы хорошенько о себе позаботиться, ладно?)]");
									break;
								case NEUTRAL:
									sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! Я отлично о себе забочусь! Как ты?)]");
									break;
								case OBEDIENT:
									sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! Могу я чем-нибудь тебе помочь?)]");
									break;
							}
							break;
					}
					sb.append("</p>");
					sb.append(
							"<p>Вы [pc.step] к своей рабыне и проводите [pc.hands] по её [npc.her] беременному животу. Ободряюще улыбнувшись [npc.race], вы говорите [npc.herHim], что Lilaya сможет помочь [npc.herHim] принять роды, когда придет время.</p><p>[npc.Name] подтверждает это кивком, и после того как вы еще какое-то время гладите и ласкаете [npc.her] живот, вы [pc.step] назад и гадаете, что делать дальше...</p>");
				}
				
			} else { // Standard repeat encounter:

				sb.append("<p>");
				
				if(isDoll()) {
					sb.append("[npc.speech(Добрый [style.morning], [pc.name],)] — говорит [npc.she] слегка роботизированным голосом [npc.her], [npc.speech(Как я могу служить тебе?)]");
					
				} else {
					sb.append("Когда ты смотришь на [npc.race], [npc.she] отвечает тебе взглядом,");
					
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append(" даже не пытаясь скрыть ненависть в [npc.her] [npc.eyes], [npc.she] сплевывает: [npc.speech(Фу, это <i>ты</i>. Какого черта тебе опять нужно?!)]");
									break;
								case NEUTRAL:
									sb.append(" пытаясь скрыть ненависть в глазах [npc.her] [npc.eyes], когда [npc.she] замечает: [npc.speech(О, привет, [npc.pcName]. Чего ты хочешь?)]");
									break;
								case OBEDIENT:
									sb.append(" послушно делая [npc.her] всё возможное, чтобы скрыть ненависть в [npc.her] [npc.eyes], когда [npc.she] окликает её, [npc.speech(Привет, [npc.pcName]. Что я могу для тебя сделать?)]");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append(" вздыхая, [npc.speech(Привет, [npc.pcName]. Чего ты хочешь?)]");
									break;
								case NEUTRAL:
									sb.append(" вздыхая: [npc.speech(Привет, [npc.pcName]. Что тебе нужно?)]");
									break;
								case OBEDIENT:
									sb.append(" вздыхая: [npc.speech(Привет, [npc.pcName]. Могу я чем-нибудь тебе помочь?)]");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! О, я так счастлива снова тебя видеть! Я вела себя очень хорошо!)]");
									break;
								case NEUTRAL:
									sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! Как дела? Могу я чем-нибудь тебе помочь?)]");
									break;
								case OBEDIENT:
									sb.append(" на лице [npc.her] расплывается широкая улыбка, когда [npc.she] радостно выкрикивает: [npc.speech(Привет, [npc.pcName]! Могу я чем-нибудь тебе помочь?)]");
									break;
							}
							break;
					}
				}
				
				sb.append("</p>");
				sb.append(
						"<p>Твоя рабыня ждет твоих приказов, и ты раздумываешь, что бы тебе сделать с [npc.herHim] сейчас...</p>");
			}
		}
		
		sb.append(getFooterText());
		
		return UtilText.parse(getSlave(), sb.toString());
	}
	
	public static final DialogueNode SLAVE_START_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static Response getWakeUpResponse() {
		return new Response(UtilText.parse(getSlave(), "Разбудить [npc.herHim]"),
				UtilText.parse(getSlave(),
						"Разбудить [npc.name], чтобы вы могли взаимодействовать с [npc.herHim].<br/>[style.italicsMinorBad([npc.She] будет немного раздражена тем, что вы разбудили [npc.herHim]...)]"),
				SLAVE_START_NO_CONTENT) {
			@Override
			public void effects() {
				getSlave().wakeUp();
				
				StringBuilder sb = new StringBuilder();
				sb.append("<p>");
					sb.append("Желая пообщаться с [npc.name], ты [pc.step] к месту, где [npc.sheIs] спит, и ");
					if(Main.game.getPlayer().isMute()) {
						sb.append(" слегка встряхиваешь [npc.herHim].");
					} else {
						sb.append(" окликаешь [npc.her] по имени.");
					}
					if(getSlave().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						sb.append(" Поскольку она спит очень крепко, этого недостаточно, чтобы разбудить [npc.herHim], и тебе приходится нанести [npc.herHim] несколько сильных толчков, прежде чем [npc.she] наконец сонно приоткрывает свои [npc.her] [npc.eyes].");
					}
					sb.append(" Проснувшись и увидев тебя над [npc.herHim], [npc.she] быстро вскакивает и поворачивается к тебе лицом.");
				sb.append("</p>");
				
				sb.append("<p style='text-align:center;'>[style.italicsMinorBad([npc.Name] не очень-то нравится, когда её будят...)]</p>");
				
				Main.game.appendToTextEndStringBuilder(UtilText.parse(getSlave(), sb.toString()));
				Main.game.appendToTextEndStringBuilder(getSlave().incrementAffection(Main.game.getPlayer(), -1));
				Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
			}
		};
	}
	
	public static final DialogueNode SLAVE_START = new DialogueNode("", ".", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				if(getSlave().isAsleep()) {
					UtilText.nodeContentSB.append("Так как в данный момент [npc.name] спит, тебе нужно сначала разбудить [npc.herHim], прежде чем взаимодействовать с [npc.herHim].");
					UtilText.nodeContentSB.append(" Такой поступок наверняка немного разозлит [npc.herHim]...");
				} else {
					if(isDoll()) {
						UtilText.nodeContentSB.append("Решив, что хочешь пообщаться с [npc.name], ты [pc.walk] к своей кукле и привлекаешь внимание [npc.her].");
						if(isDollStatue()) {
							UtilText.nodeContentSB.append(" Так как ты приказал [npc.herHim] изображать статую, [npc.she] послушно игнорирует твое приближение и остается неподвижной.");
						}
					} else {
						UtilText.nodeContentSB.append("Решив, что вы хотите пообщаться с [npc.name], вы [pc.walk] к [npc.herHim] и привлекаете [npc.her] внимание.");
					}
				}
			UtilText.nodeContentSB.append("</p>");
			
			if(!getSlave().isAsleep() && !isDollStatue()) {
				UtilText.nodeContentSB.append(getSlaveStartCoreContent());
			}
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Взаимодействие";
			} else if(index == 1) {
				return UtilText.parse("[style.colourSex(Секс)]");
			} else if(index == 2) {
				return UtilText.parse("[style.colourCompanion(Управление)]");
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if(index == 0) {
					return new Response("Уйти",
							UtilText.parse(getSlave(),
									isDollStatue()
										?"Оставить [npc.name] застывшей на месте и продолжить свой путь."
										:(getSlave().isAsleep()
											?"Оставить [npc.name] поспать..."
											:"Сказать [npc.name], что вы догоните [npc.herHim] в другой раз.")),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
				if(isDollStatue()) {
					if(index == 1) {
						return new Response("Остановить статую",
								UtilText.parse(getSlave(), "Попросить [npc.name] на время перестать вести себя как статуя, чтобы вы могли взаимодействовать с [npc.herHim]."),
								SLAVE_START_NO_CONTENT) {
							@Override
							public void effects() {
								dollStatueInterrupted = true;
								Main.game.appendToTextEndStringBuilder("<p>");
									Main.game.appendToTextEndStringBuilder("Желая пообщаться с [npc.name], пока [npc.herHim] продолжает притворяться бесчувственной статуей, вы разрешаете [npc.herHim] снова ненадолго начать двигаться свободно.");
								Main.game.appendToTextEndStringBuilder("</p>");
								Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
							}
						};
					}
					
				} else if(getSlave().isAsleep()) {
					if(index == 1) {
						return getWakeUpResponse();
					}

				} else {
					if(index == 1) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveBackground)) {
							return new Response("Прошлое",
									UtilText.parse(getSlave(),
											isDoll()
												?"Спросить у [npc.name], что [npc.she] помнит о своем создании."
												:"Спросить [npc.name] о прошлой жизни [npc.her]."),
									SLAVE_PROGRESSION) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveBackground);
									if(!isDoll()) {
										Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 3));
									}
								}
							};
						} else {
							return new Response("Прошлое", UtilText.parse(getSlave(), "Вы уже говорили об этом с [npc.name] сегодня..."), null);
						}
						
					} else if(index == 2) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveSmallTalk)) {
							return new Response("Светская беседа", UtilText.parse(getSlave(), "Поболтать с [npc.name] о том о сем."), SLAVE_MINOR) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveSmallTalk);
									if(!isDoll()) {
										switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
											case DISLIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -1f));
												break;
											case NEUTRAL:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2f));
												break;
											case LIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 4f));
												break;
										}
									}
								}
							};
						} else {
							return new Response("Светская беседа", UtilText.parse(getSlave(), "Вы уже провели время за разговорами с [npc.name] сегодня."), null);
						}
						
					} else if(index == 3
							&& getSlave().equals(Main.game.getNpc(Scarlett.class))
							&& !Main.getProperties().hasValue(PropertyValue.companionContent)
							&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
						if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
							return new Response("Helena",
									UtilText.parse(getSlave(), "Лавка Helena сейчас закрыта, поэтому вы не можете привести [npc.name] к ней в данный момент..."),
									null);
						}
						return new Response("Helena",
								UtilText.parse(getSlave(), "Сопроводить [npc.name] в лавку Helena в Переулке Рабов."),
								ScarlettsShop.ROMANCE_SHOP_CORE) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
							@Override
							public void effects() {
	//							Main.game.getPlayer().addCompanion(getSlave());
								applyReactionReset();
								Main.game.getDialogueFlags().setManagementCompanion(null);
								// Move them both here to make sure they haven't gone due to time ticking over into night time when player arrives:
								Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
								Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
								Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							}
						};
						
					} else if(index == 5 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
						if(!Main.game.getPlayer().hasCompanion(getSlave())) {
							if(!getSlave().isCompanionAvailable(Main.game.getPlayer())) {
								return new Response("Взять в отряд",
										UtilText.parse(getSlave(), "[npc.Name] нельзя добавить в ваш отряд!"),
										null);
									
							} else if(Main.game.getPlayer().canHaveMoreCompanions()) {
								return new Response("Взять в отряд",
										UtilText.parse(getSlave(), "Приказать [npc.name] следовать за вами."),
										SLAVE_START){
									@Override
									public void effects() {
										applyReactionReset();
										Main.game.getPlayer().addCompanion(getSlave());
									}
								};
							} else {
								return new Response("Взять в отряд",
										"Вы уже достигли лимита участников в отряде!",
										null);
							}
						} else {
							return new Response("Убрать из отряда",
									UtilText.parse(getSlave(), "Приказать [npc.name] покинуть ваш отряд."),
									SLAVE_START){
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().removeCompanion(getSlave());
								}
							};
						}
						
					} else if(index == 6) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveEncourage)) {
							if(getCurrentJob()==SlaveJob.IDLE) {
								return new Response("Работать",
										UtilText.parse(getSlave(),
												"[npc.Name] сейчас не работает...<br/>[style.italicsMinorBad(Вы не можете расспрашивать [npc.name] о работе [npc.her], пока [npc.sheIs] бездельничает!)]"),
										null);
								
							} else {
								return new Response("Работать", UtilText.parse(getSlave(), "Спросить [npc.name] о том, как продвигается работа [npc.her]."), SLAVE_ENCOURAGE) {
									@Override
									public void effects() {
										applyReactionReset();
										getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveEncourage);
										if(!isDoll()) {
											switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
												case DISLIKE:
													Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 0.5f));
													Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(0.1f));
													break;
												case NEUTRAL:
													Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2f));
													Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(1f));
													break;
												case LIKE:
													Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 4f));
													Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(2f));
													break;
											}
										}
									}
								};
							}
							
						} else {
							return new Response("Работать", UtilText.parse(getSlave(), "Вы уже говорили с [npc.name] о работе [npc.her] сегодня."), null);
						}
						
					} else if(index == 7) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveHug)) {
							return new Response("Обнять", UtilText.parse(getSlave(), "Обнять [npc.name]."), SLAVE_HUG) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveHug);
	
									if(!isDoll()) {
										switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
											case DISLIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1f));
												break;
											case NEUTRAL:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1));
												break;
											case LIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 5));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-2));
												break;
										}
									}
								}
							};
						} else {
							return new Response("Обнять", UtilText.parse(getSlave(), "Вы уже успели обнять [npc.name] сегодня."), null);
						}
						
					} else if(index == 8) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlavePettings)) {
							return new Response("Поглаживания", UtilText.parse(getSlave(), "Ласково погладить [npc.name]."), SLAVE_PETTINGS) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlavePettings);
									
									if(!isDoll()) {
										switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
											case DISLIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1f));
												break;
											case NEUTRAL:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 2));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-1));
												break;
											case LIKE:
												Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 5));
												Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-2));
												break;
										}
									}
								}
							};
						} else {
							return new Response("Поглаживания", UtilText.parse(getSlave(), "Вы уже успели погладить [npc.name] сегодня."), null);
						}
						
					} else if(index == 9) {
						if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
							if(isDoll()) {
								return new Response("Вручить подарок", UtilText.parse(getSlave(), "Как кукле, [npc.name] не нужны подарки, и она не умеет их ценить..."), null);
							}
							return new Response("Вручить подарок", UtilText.parse(getSlave(), "Вручить [npc.name] подарок, который у тебя с собой."), SLAVE_PRESENT) {
								@Override
								public void effects() {
									applyReactionReset();
									Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.PRESENT));
	
									if(!isDoll()) {
										Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(-2));
									}
								}
							};
							
						} else {
							return null;
						}
						
					} else if(index == 11) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveInspect)) {
							return new Response("Осмотреть", UtilText.parse(getSlave(), "Заставить [npc.name] раздеться и пройтись по [npc.her] комнате, чтобы ты мог её рассмотреть."), SLAVE_INSPECT) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveInspect);
		
									getSlave().setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.ASS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.MOUND, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.MOUTH, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.TESTICLES, Main.game.getPlayer(), true);
									getSlave().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
	
									if(!isDoll()) {
										if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() || getSlave().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
										} else {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -5));
										}
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(5));
									}
								}
							};
						} else {
							return new Response("Осмотреть", UtilText.parse(getSlave(), "Ты уже осматривал [npc.name] сегодня."), null);
						}
						
					} else if(index == 12) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveSpanking)) {
							return new Response("Порка", UtilText.parse(getSlave(), "Наклонить [npc.name] через свое колено и хорошенько отшлепать [npc.herHim]."), SLAVE_SPANKING) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveSpanking);
									if(!isDoll()) {
										if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
										} else {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -5));
										}
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(10));
									}
								}
							};
						} else {
							return new Response("Порка", UtilText.parse(getSlave(), "Ты уже отшлепал [npc.name] сегодня."), null);
						}
						
					} else if(index == 13) {
						if(!getSlave().NPCFlagValues.contains(NPCFlagValue.flagSlaveMolest)) {
							return new Response("Ласкать", UtilText.parse(getSlave(), "Заставить [npc.name] сидеть смирно, пока ты ощупываешь и ласкаешь тело [npc.her]."), SLAVE_MOLEST) {
								@Override
								public void effects() {
									applyReactionReset();
									getSlave().NPCFlagValues.add(NPCFlagValue.flagSlaveMolest);
	
									if(!isDoll()) {
										if(getSlave().isAttractedTo(Main.game.getPlayer())
												&& (getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) || getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB))) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), 10));
											
										} else if(!getSlave().isAttractedTo(Main.game.getPlayer()) && !getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) && !getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
											Main.game.getTextEndStringBuilder().append(getSlave().incrementAffection(Main.game.getPlayer(), -10));
										}
										Main.game.getTextEndStringBuilder().append(getSlave().incrementObedience(10));
									}
								}
							};
						} else {
							return new Response("Ласкать", UtilText.parse(getSlave(), "Ты уже ласкал [npc.name] сегодня."), null);
						}
						
					}
				}
				
				return null;
			
			} else if(responseTab == 1) {
				if(index == 0) {
					return new Response("Уйти", 
							UtilText.parse(getSlave(),
									isDollStatue()
										?"Оставить [npc.name] застывшей на месте и продолжить свой путь."
										:(getSlave().isAsleep()
											?"Оставить [npc.name] поспать..."
											:"Сказать [npc.name], что вы догоните [npc.herHim] в другой раз."))
							, Main.game.getDefaultDialogue(false)) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							applyReactionReset();
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
					if(index == 1) {
						if((!characterForSex.isSlave() || !characterForSex.getOwner().isPlayer())
								&& !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Секс", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, и вы не можете заставить [npc.herHim] переспать с вами..."), null);
							
						} else if(Main.game.isNonConEnabled() && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new ResponseSex("Изнасилование", UtilText.parse(characterForSex, "[npc.Name] явно не горит желанием переспать с тобой, но ведь у [npc.sheHasFull] нет выбора..."), 
									false, false,
									new SMMilkingStall(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
											Util.newHashMapOfValues(new Value<>(characterForSex, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))),
									getDominantSpectators(),
									getSubmissiveSpectators(),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START_MILKING_ROOM", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										if(characterForSex.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
										} else {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), -25));
										}
									}
								}
							};
							
						} else {
							return new ResponseSex("Секс", UtilText.parse(characterForSex, "Заняться сексом с [npc.name]."), 
									true, false,
									new SMMilkingStall(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
											Util.newHashMapOfValues(new Value<>(characterForSex, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))),
									getDominantSpectators(),
									getSubmissiveSpectators(),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START_MILKING_ROOM", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
									}
								}
							};
						}
					}
					
				} else if(getSlave().isAsleep()) {
					if(index == 1) {
						if((!characterForSex.isSlave() || !characterForSex.getOwner().isPlayer())
								&& !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Секс во сне", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, и вы не можете заставить [npc.herHim] переспать с вами..."), null);
							
						} else if(Main.game.isNonConEnabled() && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!getSlave().hasTrait(Perk.HEAVY_SLEEPER, true)) {
								return new Response("Секс во сне", UtilText.parse(characterForSex, "[npc.Name] спит не так уж крепко, так что вы не можете заняться сексом во сне с [npc.herHim]..."), null);
							}
							return new ResponseSex("Секс во сне",
									UtilText.parse(characterForSex,
											"[npc.Name] явно не горит желанием переспать с тобой, но ведь у [npc.sheHasFull] нет выбора. Поскольку [npc.sheIs] крепко спит, ты можешь осторожно трахать [npc.herHim], не разбудив [npc.herHim]..."), 
									false, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											new ArrayList<>(),
											new ArrayList<>(),
											Util.newArrayListOfValues(ResponseTag.PREFER_MISSIONARY),
											false,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPace.DOM_GENTLE))),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						
						} else {
							if(!getSlave().hasTrait(Perk.HEAVY_SLEEPER, true)) {
								return new Response("Секс во сне", UtilText.parse(characterForSex, "[npc.Name] спит не так уж крепко, так что вы не можете заняться сексом во сне с [npc.herHim]..."), null);
							}
							return new ResponseSex("Секс во сне",
									UtilText.parse(characterForSex, "Поскольку [npc.sheIs] крепко спит, ты можешь осторожно трахать [npc.name], не разбудив [npc.herHim]..."), 
									true, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											new ArrayList<>(),
											new ArrayList<>(),
											Util.newArrayListOfValues(ResponseTag.PREFER_MISSIONARY),
											false,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPace.DOM_GENTLE))),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						}
						
					}
					
				} else {
					if(index == 1) {
						if((!characterForSex.isSlave() || !characterForSex.getOwner().isPlayer())
								&& !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Секс", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, и вы не можете заставить [npc.herHim] переспать с вами..."), null);
							
						} else if(Main.game.isNonConEnabled() && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new ResponseSex("Изнасилование", UtilText.parse(characterForSex, "[npc.Name] явно не горит желанием переспать с тобой, но ведь у [npc.sheHasFull] нет выбора..."), 
									false, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											(characterForSex.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
												?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
												:new ArrayList<>()), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										if(characterForSex.getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
										} else {
											Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), -25));
										}
									}
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						
						} else {
							return new ResponseSex("Секс", UtilText.parse(characterForSex, "Заняться сексом с [npc.name]."), 
									true, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											isDollStatue()
												?Util.newArrayListOfValues(getDollSexTag())
												:(characterForSex.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
													?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
													:new ArrayList<>()),
											false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
									}
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						}
						
					} else if(index == 2) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Спитройст (спереди)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Спитройст (спереди)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary,
											"Поскольку [npc.sheIsFull] сейчас спит, [npc.name] не может участвовать в сексе..."),
									null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary, characterForSex,
											"[npc.Name] не испытывает влечения к [npc2.name], поэтому [npc.herHim] не получится занять доминирующую позицию, чтобы трахать [npc2.herHim]..."),
									null);
								
						} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSex,
											"[npc.Name] не испытывает к тебе влечения, поэтому она не захочет участвовать в тройничке, где [npc.she] взаимодействует с тобой..."),
									null);
							
						} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("Спитройст (спереди)",
									UtilText.parse(characterForSexSecondary, characterForSex,
											"[npc2.Name] не испытывает влечения к [npc.name], поэтому она не захочет участвовать в тройничке, где [npc2.she] взаимодействует с [npc.herHim]..."),
									null);
							
						} else {
							boolean isRape = !characterForSex.isAttractedTo(Main.game.getPlayer()) && !characterForSex.isAttractedTo(characterForSexSecondary);
							return new ResponseSex(
									isRape
										?"Изнасилование методом спитройста (спереди)"
										:"Спитройст (спереди)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Подвинься перед [npc.name] так, чтобы ты мог использовать рот [npc.her], пока [npc2.name] берет [npc.her] сзади."),
									null, null, null, null, null, null,
									!isRape, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(characterForSexSecondary, Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_FRONT_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
								}
							};
						}
						
					
					} else if(index == 3) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Вертел (сзади)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Вертел (сзади)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("Вертел (сзади)",
									UtilText.parse(characterForSexSecondary,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("Вертел (сзади)",
									UtilText.parse(characterForSexSecondary,
											"Поскольку [npc.sheIsFull] сейчас спит, [npc.name] не может участвовать в сексе..."),
									null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("Вертел (сзади)",
									UtilText.parse(characterForSexSecondary, characterForSex,
											"[npc.Name] не испытывает влечения к [npc2.name], поэтому [npc.herHim] не получится занять доминирующую позицию, чтобы трахать [npc2.herHim]..."),
									null);
								
						} else {
							 if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Вертел (сзади)",
										UtilText.parse(characterForSex,
												"[npc.Name] не испытывает к тебе влечения, поэтому она не захочет участвовать в тройничке, где [npc.she] взаимодействует с тобой..."),
										null);
								
							} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(characterForSexSecondary)) {
								return new Response("Вертел (сзади)",
										UtilText.parse(characterForSexSecondary, characterForSex,
												"[npc2.Name] не испытывает влечения к [npc.name], поэтому она не захочет участвовать в тройничке, где [npc2.she] взаимодействует с [npc.herHim]..."),
										null);
								
							} else {
								boolean isRape = !characterForSex.isAttractedTo(Main.game.getPlayer()) && !characterForSex.isAttractedTo(characterForSexSecondary);
								return new ResponseSex(
										isRape
											?"Изнасилование методом спитройста (сзади)"
											:"Вертел (сзади)",
										UtilText.parse(characterForSex, characterForSexSecondary, "Переместись за спину [npc.name], чтобы иметь доступ к заднице [npc.her], пока [npc2.name] занята ртом [npc.her]."),
										null, null, null, null, null, null,
										!isRape, false,
										getGenericSlaveSexManager(
												Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
												Util.newArrayListOfValues(characterForSex),
												Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
										getAfterSexDialogue(),
										UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_BEHIND_START", characterForSex, characterForSexSecondary)) {
									@Override
									public void effects() {
										applyReactionReset();
										//applyImmobilisationText(Util.newArrayListOfValues(characterForSex));
									}
								};
							}
						}
					
					} else if(index == 4) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Бок о бок (как доминант)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Бок о бок (как доминант)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("Бок о бок (как доминант)",
									UtilText.parse(characterForSexSecondary,
											"Поскольку [npc.sheIsFull] сейчас спит, [npc.name] не может участвовать в сексе..."),
									null);
							
						} else if((!Main.game.isNonConEnabled() || !characterForSexSecondary.isSlave()) && !characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Бок о бок (как доминант)",
									UtilText.parse(characterForSexSecondary,
											"[npc.Name] не испытывает к тебе влечения, поэтому она не захочет участвовать в тройничке, где [npc.she] взаимодействует с тобой..."),
									null);
								
						} else if((!Main.game.isNonConEnabled() || !characterForSex.isSlave()) && !characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Бок о бок (как доминант)",
									UtilText.parse(characterForSex,
											"[npc.Name] не испытывает к тебе влечения, поэтому она не захочет участвовать в тройничке, где [npc.she] взаимодействует с тобой..."),
									null);
							
						} else {
							boolean isRape = !characterForSex.isAttractedTo(Main.game.getPlayer()) || !characterForSexSecondary.isAttractedTo(Main.game.getPlayer());
							return new ResponseSex(
									isRape
										?"Групповой секс (как доминант)"
										:"Бок о бок (как доминант)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Прижми [npc.name] и [npc2.name] к полу на четвереньки, а затем встань на колени позади [npc.name], готовый трахнуть их обеих бок о бок."),
									null, null, null, null, null, null,
									!isRape, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(characterForSex, characterForSexSecondary));
								}
							};
						}
						
					} else if(index==5) {
						if(charactersPresent.size()<3) {
							return new Response("Гангбанг (как доминант)",
									"Для начала гангбанга должно присутствовать как минимум три персонажа.",
									null);
							
						} else if (charactersPresent.stream().anyMatch(c->c.isAsleep())) {
							boolean soloSleeper = charactersPresent.stream().filter(c->c.isAsleep()).count()==1;
							String names = Util.charactersToStringListOfNames(charactersPresent.stream().filter(c->c.isAsleep()).collect(Collectors.toList()));
							return new Response("Гангбанг (как доминант)",
											names+" "+(soloSleeper?"нужна":"нужна")+" чтобы участвовать в гангбанге!",
									null);
							
						} else {
							boolean isRape = charactersPresent.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()));
							// Handle attraction/non-con:
							if(isRape) {
								if(charactersPresent.stream().anyMatch(c->!c.isSlave())) {
									List<GameCharacter> nonSlaves = new ArrayList<>();
									for(GameCharacter character : charactersPresent) {
										if(!character.isSlave() && !character.isAttractedTo(Main.game.getPlayer())) {
											nonSlaves.add(character);
										}
									}
									String isAre = nonSlaves.size()==1?"is":"are";
									String slavePural = nonSlaves.size()==1?"slave":"slaves";
									String them = nonSlaves.size()==1?UtilText.parse(nonSlaves.get(0), "[npc.herHim]"):"them";
									return new Response("Гангбанг (как доминант)",
											"Как "+Util.charactersToStringListOfNames(nonSlaves)+" "+isAre+" не испытывает к тебе влечения, и "+isAre+" не твоя "+slavePural+", ты не можешь устроить гангбанг с "+them+".",
											null);
									
								} else if(!Main.game.isNonConEnabled()) {
									List<GameCharacter> nonAttracted = new ArrayList<>();
									for(GameCharacter character : charactersPresent) {
										if(!character.isAttractedTo(Main.game.getPlayer())) {
											nonAttracted.add(character);
										}
									}
									String isAre = nonAttracted.size()==1?"is":"are";
									String them = nonAttracted.size()==1?UtilText.parse(nonAttracted.get(0), "[npc.herHim]"):"them";
									return new Response("Гангбанг (как доминант)",
											"Как "+Util.charactersToStringListOfNames(nonAttracted)+" "+isAre+" не испытывает к тебе влечения, ты не сможешь устроить с ней групповой секс "+them+".",
											null);
								}
							}
							
							List<GameCharacter> nonAttracted = new ArrayList<>();
							for(GameCharacter character : charactersPresent) {
								if(!character.isAttractedTo(Main.game.getPlayer())) {
									nonAttracted.add(character);
								}
							}
							String isAre = nonAttracted.size()==1?"is":"are";
							String them = nonAttracted.size()==1?UtilText.parse(nonAttracted.get(0), "[npc.herHim]"):"them";
							List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
							return new ResponseSex(
									isRape
										?"Групповое изнасилование (в роли доминанта)"
										:"Гангбанг (как доминант)",
									"Заняться сексом с "+Util.charactersToStringListOfNames(sexParticipants)+"."
										+ (isRape
											?" Как "+Util.charactersToStringListOfNames(nonAttracted)+" "+isAre+" не испытывает к тебе влечения, тебе придется принудить её "+them+" к этому..."
											:""),//TODO sleeping
									null, null, null, null, null, null,
									!isRape, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											sexParticipants,
											Util.newArrayListOfValues(), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), sexParticipants.size()==3?"SEX_GANGBANG_3":"SEX_GANGBANG_4", sexParticipants)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(sexParticipants);
								}
							};
						}
						
					} else if(index == 6) {
						if (characterForSex.isDollStatue()) {
							return new Response("Субмиссивный секс",
									UtilText.parse(characterForSex,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Субмиссивный секс",
									UtilText.parse(characterForSex, 
										"[npc.Name] не испытывает к тебе влечения,"
										+ (Main.game.isNonConEnabled() && characterForSex.isSlave()
												?" так что если ты захочешь переспать с [npc.herHim], тебе придется изнасиловать [npc.herHim], выступая в роли доминанта."
												:" так что ты не сможешь заняться с [npc.herHim] подчиненным сексом.")),
									null);
							
						} else {
							return new ResponseSex("Субмиссивный секс",
									UtilText.parse(characterForSex, "Заниматься подчиненным сексом с [npc.name]."), 
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, true,
									getGenericSlaveSexManager(
										Util.newArrayListOfValues(characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										(characterForSex.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
												?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
												:new ArrayList<>()), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									if(!characterForSex.isDoll()) {
										Main.game.getTextEndStringBuilder().append(characterForSex.incrementAffection(Main.game.getPlayer(), 5));
									}
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer()));
								}
							};
						}
						
					} else if(index == 7) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Шпиготка (спереди)", "Тебе понадобится третий человек, чтобы устроить шпиготку...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Шпиготка (спереди)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if (characterForSex.isDollStatue()) {
							return new Response("Шпиготка (спереди)",
									UtilText.parse(characterForSex,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("Шпиготка (спереди)",
									UtilText.parse(characterForSexSecondary,
											"Поскольку [npc.sheIsFull] сейчас спит, [npc.name] не может участвовать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("Шпиготка (спереди)",
									UtilText.parse(characterForSexSecondary,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Шпиготка (спереди)",
										UtilText.parse(characterForSexSecondary, characterForSex,
												"Ни [npc.name], ни [npc2.name] не испытывают к тебе влечения,"
												+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
														?" так что если ты захочешь переспать с ними, тебе придется изнасиловать их, выступая в роли доминанта."
														:" так что с ними не получится заняться сексом в роли подчиненной.")),
										null);
							} else {
								return new Response("Шпиготка (спереди)",
										UtilText.parse(characterForSex,
												"[npc.Name] не испытывает к тебе влечения,"
												+ (Main.game.isNonConEnabled()
													?" так что если ты захочешь переспать с [npc.herHim], тебе придется изнасиловать [npc.herHim], выступая в роли доминанта."
													:" так что ты не сможешь заняться с [npc.herHim] подчиненным сексом.")),
										null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Шпиготка (спереди)",
									UtilText.parse(characterForSexSecondary,
										"[npc.Name] не испытывает к тебе влечения,"
										+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
											?" так что если ты захочешь переспать с [npc.herHim], тебе придется изнасиловать [npc.herHim], выступая в роли доминанта."
											:" так что ты не сможешь заняться с [npc.herHim] подчиненным сексом.")),
									null);
							
						} else {
							return new ResponseSex(
									"Шпиготка (спереди)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Встань на четвереньки лицом к [npc.name], чтобы [npc.she] мог использовать твой рот, пока [npc2.name] берет тебя сзади."),
									null, null, null, null, null, null,
									true, true,
									getGenericSlaveSexManager(
										Util.newArrayListOfValues(characterForSexSecondary, characterForSex),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer()));
								}
							};
						}
							
						
					} else if(index == 8) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Вертел (сзади)", "Чтобы устроить спитройст, понадобится еще один человек...", null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Шпиготка (сзади)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if (characterForSex.isDollStatue()) {
							return new Response("Шпиготка (сзади)",
									UtilText.parse(characterForSex,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("Шпиготка (сзади)",
									UtilText.parse(characterForSexSecondary,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("Шпиготка (сзади)",
									UtilText.parse(characterForSexSecondary,
											"Поскольку [npc.sheIsFull] сейчас спит, [npc.name] не может участвовать в сексе..."),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Шпиготка (сзади)",
										UtilText.parse(characterForSexSecondary, characterForSex,
												"Ни [npc.name], ни [npc2.name] не испытывают к тебе влечения,"
												+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
														?" так что если ты захочешь переспать с ними, тебе придется изнасиловать их, выступая в роли доминанта."
														:" так что с ними не получится заняться сексом в роли подчиненной.")),
										null);
							} else {
								return new Response("Шпиготка (сзади)",
										UtilText.parse(characterForSex,
												"[npc.Name] не испытывает к тебе влечения,"
												+ (Main.game.isNonConEnabled()
													?" так что если ты захочешь переспать с [npc.herHim], тебе придется изнасиловать [npc.herHim], выступая в роли доминанта."
													:" так что ты не сможешь заняться с [npc.herHim] подчиненным сексом.")),
										null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Шпиготка (сзади)",
									UtilText.parse(characterForSexSecondary,
										"[npc.Name] не испытывает к тебе влечения,"
										+ (Main.game.isNonConEnabled() && characterForSexSecondary.isSlave()
											?" так что если ты захочешь переспать с [npc.herHim], тебе придется изнасиловать [npc.herHim], выступая в роли доминанта."
											:" так что ты не сможешь заняться с [npc.herHim] подчиненным сексом.")),
									null);
							
						} else {
							return new ResponseSex(
									"Шпиготка (сзади)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Встань на четвереньки и подставь задницу [npc.name], чтобы [npc.she] трахал тебя, пока [npc2.name] использует твой рот."),
									null, null, null, null, null, null,
									true, true,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(characterForSex, characterForSexSecondary),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", characterForSexSecondary, characterForSex)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer()));
								}
							};
						}
					
					} else if(index == 9) {
						if(characterForSexSecondary==null || charactersPresent.size()<2) {
							return new Response("Рядом (как саб)", UtilText.parse(characterForSex, "Тебе понадобится третий человек, чтобы тебя трахали вместе с кем-то из них или с [npc.name]..."), null);
							
						} else if(characterForSex.isPlayer()) {
							return new Response("Рядом (как саб)", "Вы не можете выбрать самого себя для этого действия!", null);
							
						} else if (characterForSex.isDollStatue()) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSex,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isDollStatue()) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSexSecondary,
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (characterForSexSecondary.isAsleep()) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSexSecondary,
											"Поскольку [npc.sheIsFull] сейчас спит, [npc.name] не может участвовать в сексе..."),
									null);
							
						} else if(!characterForSex.isAttractedTo(Main.game.getPlayer())) {
							if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
								return new Response("Рядом (как саб)", UtilText.parse(characterForSexSecondary, characterForSex, "Ни [npc.name], ни [npc2.name] к вам не испытывают влечения..."), null);
							} else {
								return new Response("Рядом (как саб)", UtilText.parse(characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому она неохотно согласится на секс втроем..."), null);
							}
							
						} else if(!characterForSexSecondary.isAttractedTo(Main.game.getPlayer())) {
							return new Response("Рядом (как саб)", UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает к вам влечения, поэтому ни [npc.she], ни [npc2.name] не захотят заняться сексом втроем..."), null);
							
						} else if(!characterForSexSecondary.isAttractedTo(characterForSex)) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc.Name] не испытывает влечения к [npc2.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);

						} else if(!characterForSex.isAttractedTo(characterForSexSecondary)) {
							return new Response("Рядом (как саб)",
									UtilText.parse(characterForSexSecondary, characterForSex, "[npc2.Name] не испытывает влечения к [npc.name], поэтому ни один из них не захочет участвовать в групповом сексе, где предполагается взаимодействие друг с другом..."),
									null);
							
						} else {
							return new ResponseSex("Рядом (как саб)",
									UtilText.parse(characterForSex, characterForSexSecondary, "Встань на четвереньки рядом с [npc2.name], чтобы [npc.name] мог опуститься на колени позади вас обоих, готовый трахать вас обоих бок о бок."),
									null, null, null, null, null, null,
									true, false,
									getGenericSlaveSexManager(
											Util.newArrayListOfValues(characterForSex),
											Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary),
											Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY), false),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_AS_SUB_START", characterForSex, characterForSexSecondary)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(Util.newArrayListOfValues(Main.game.getPlayer(), characterForSexSecondary));
								}
							};
						}
					
					}else if(index==10) {
						if(charactersPresent.size()<3) {
							return new Response("Гангбанг (в роли сабмиссива)",
									"Для начала гангбанга должно присутствовать как минимум три персонажа.",
									null);
							
						} else if (charactersPresent.stream().anyMatch(c->c.isDollStatue())) {
							return new Response("Гангбанг (в роли сабмиссива)",
									UtilText.parse(charactersPresent.stream().filter(c->c.isDollStatue()).findFirst().get(),
											"Будучи неподвижной куклой, [npc.name] не может доминировать в сексе..."),
									null);
							
						} else if (charactersPresent.stream().anyMatch(c->c.isAsleep())) {
							boolean soloSleeper = charactersPresent.stream().filter(c->c.isAsleep()).count()==1;
							String names = Util.charactersToStringListOfNames(charactersPresent.stream().filter(c->c.isAsleep()).collect(Collectors.toList()));
							return new Response("Гангбанг (в роли сабмиссива)",
											names+" "+(soloSleeper?"нужна":"нужна")+" чтобы участвовать в гангбанге!",
									null);
							
						} else {
							boolean isRape = charactersPresent.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()));
							// Handle attraction/non-con:
							if(isRape) {
								List<GameCharacter> nonAttracted = new ArrayList<>();
								for(GameCharacter character : charactersPresent) {
									if(!character.isAttractedTo(Main.game.getPlayer())) {
										nonAttracted.add(character);
									}
								}
								String isAre = nonAttracted.size()==1?"is":"are";
								String them = nonAttracted.size()==1?UtilText.parse(nonAttracted.get(0), "[npc.herHim]"):"them";
								return new Response("Гангбанг (в роли сабмиссива)",
										"Как "+Util.charactersToStringListOfNames(nonAttracted)+" "+isAre+" не испытывает к тебе влечения, ты не сможешь "+them+" доминантно участвовать в гангбаге с тобой.",
										null);
							}
							
							List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
							return new ResponseSex(
									"Гангбанг (в роли сабмиссива)",
									"Иметь "+Util.charactersToStringListOfNames(sexParticipants)+" занять доминирующую роль и заставить их устроить тебе групповой секс.",
									null, null, null, null, null, null,
									true, false,
									getGenericSlaveSexManager(
											sexParticipants,
											Util.newArrayListOfValues(Main.game.getPlayer()),
											Util.newArrayListOfValues(),
											true),
									getAfterSexDialogue(),
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), sexParticipants.size()==3?"SEX_GANGBANG_AS_SUB_3":"SEX_GANGBANG_AS_SUB_4", sexParticipants)) {
								@Override
								public void effects() {
									applyReactionReset();
									//applyImmobilisationText(sexParticipants);
								}
							};
						}
						
					} else if(index==11) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSex, "Цель: <b style='color:"+characterForSex.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"Сменить целевого персонажа для группового секса.") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSex)) {
												if(i==charactersPresent.size()-1) {
													characterForSex = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get(1);
													}
												} else {
													characterForSex = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSexSecondary = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
													break;
												}
											}
										}
									}
									Main.game.updateResponses();
								}
							};
							
						} else {
							return new Response(
									UtilText.parse(characterForSex, "Цель: <b>[npc.Name]</b>"),
									"Сменить целевого персонажа для группового секса.<br/>[style.italicsBad(Чтобы разблокировать это действие, с вами должен быть спутник!)]",
									null); 
						}
						
					} else if(index==12) {
						if(characterForSexSecondary!=null) {
							return new ResponseEffectsOnly(
									UtilText.parse(characterForSexSecondary, "Второй: <b style='color:"+characterForSexSecondary.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
									"Сменить второго целевого персонажа для группового секса.") {
								@Override
								public void effects() {
									if(charactersPresent.size()>1) {
										for(int i=0; i<charactersPresent.size();i++) {
											if(charactersPresent.get(i).equals(characterForSexSecondary)) {
												if(i==charactersPresent.size()-1) {
													characterForSexSecondary = charactersPresent.get(0);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get(1);
													}
												} else {
													characterForSexSecondary = charactersPresent.get(i+1);
													if(characterForSexSecondary.equals(characterForSex)) {
														characterForSex = charactersPresent.get((i+2)<charactersPresent.size()?(i+2):0);
													}
												}
												break;
											}
										}
									}
									Main.game.updateResponses();
								}
							};
							
						} else {
							return new Response(
									UtilText.parse(characterForSex, "Второй: <b>[npc.Name]</b>"),
									"Сменить второго целевого персонажа для группового секса.<br/>[style.italicsBad(Чтобы разблокировать это действие, с вами должен быть спутник!)]",
									null);
						}
					}
				}
				return null;
				
			} else if(responseTab == 2) {
				if(isDollStatue()) {
					if(index == 1) {
						return new Response("Остановить статую",
								UtilText.parse(getSlave(), "Попросить [npc.name] на время перестать изображать статую, чтобы ты мог разобраться с [npc.herHim]."),
								SLAVE_START_NO_CONTENT) {
							@Override
							public void effects() {
								dollStatueInterrupted = true;
								Main.game.appendToTextEndStringBuilder("<p>");
									Main.game.appendToTextEndStringBuilder("Желая пообщаться с [npc.name], пока [npc.herHim] продолжает притворяться бесчувственной статуей, вы разрешаете [npc.herHim] снова ненадолго начать двигаться свободно.");
								Main.game.appendToTextEndStringBuilder("</p>");
								Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
							}
						};
						
					} else if(index==0) {
						return CompanionManagement.getManagementResponses(index);
					}
					return null;
					
				} else if(getSlave().isAsleep()) {
					if(index == 1) {
						return getWakeUpResponse();
						
					} else if(index==0) {
						return CompanionManagement.getManagementResponses(index);
					}
					return null;

				} else {
					return CompanionManagement.getManagementResponses(index);
				}
			
			} else {
				return null;
			}
		}
	};
	
	private static String getFooterText() {
		if(isDoll()) {
			return "";
		}
		return UtilText.parse(getSlave(),
				"<p><i>"
					+ (getSlave().isAttractedTo(Main.game.getPlayer())
						?"Судя по тому, как [npc.she] продолжает жадно поглядывать на твое тело, можно понять, что [npc.sheIs] тобой увлечена..."
						:"[npc.She] вроде как не испытывает к тебе влечения...")
				+ "</i></p>");
	}
	
	public static final DialogueNode SLAVE_PROGRESSION = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isDoll()) {
				UtilText.nodeContentSB.append(
						"<p>Желая услышать об этом с точки зрения [npc.her], ты спрашиваешь [npc.name], что [npc.she] может рассказать о процессе создания [npc.she].</p><p>[npc.speech(Прости, [pc.name], но я ничего об этом не помню,)] [npc.she] отвечает: [npc.speech(Мое первое воспоминание — это приказ занять свое место рядом с дюжиной других кукол и ждать там, пока меня не купят.)]</p><p>Похоже, [npc.name] не сможет рассказать тебе ничего такого, чего ты еще не узнал от Саэллатрикс, и ты решаешь не тратить время на дальнейшие расспросы на эту тему...</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p><i>Это еще не полностью реализовано!</i></p><p>Решив, что хочешь поговорить с [npc.name] о чем-то более серьезном, ты спрашиваешь [npc.herHim] о жизни [npc.her] до того, как она стала твоей рабыней: [pc.speech(Я бы хотел узнать побольше о твоем прошлом, [npc.name]. Какой была твоя жизнь до того, как ты оказалась здесь?)]</p><p>");
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("С выражением жгучей ненависти в [npc.her] [npc.eyes], [npc.she] быстро выплевывает дерзкий ответ: [npc.speech(Отвали! Будто я стану обсуждать с тобой такое дерьмо! Мудак!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("Хотя [npc.she] и пытается это скрыть, вы видите отчетливый взгляд ненависти в [npc.her] [npc.eyes], когда [npc.she] замечает: [npc.speech(Я не так уж много делала. Больше особо и сказать нечего, [npc.pcName].)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("[npc.She] послушно делает [npc.her] всё возможное, чтобы скрыть взгляд ненависти в [npc.her] [npc.eyes], когда [npc.she] отвечает: [npc.speech(Об этом не так уж много и расскажешь, [npc.pcName]. До того как стать твоей собственностью, моя жизнь была довольно скучной. Тебе нужно что-нибудь еще?)]");
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("Хотя [npc.she], кажется, и не ненавидит вас, [npc.name] явно чувствует себя не слишком уютно, обсуждая [npc.her] прошлое с вами, и вздыхает: [npc.speech(Не знаю, [npc.pcName], на самом деле рассказывать особо нечего. Давай лучше поговорим о чем-нибудь другом, ладно?)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("Хотя [npc.she], кажется, и не ненавидит вас, [npc.name] явно чувствует себя не слишком уютно, обсуждая [npc.her] прошлое с вами, и вздыхает: [npc.speech(Прости, [npc.pcName], о моем прошлом особо и сказать нечего. Может, я могу сделать для тебя что-нибудь другое?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("Хотя [npc.she], кажется, и не ненавидит тебя, [npc.name] явно не слишком уютно обсуждать с тобой [npc.her] прошлое, и просто отвечает: [npc.speech(Об этом особо и говорить нечего, [npc.pcName]. До того как стать твоей рабыней, моя жизнь была совершенно заурядной. Могу я сделать для тебя что-нибудь еще?)]");
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) { //TODO
							case DISOBEDIENT:
								UtilText.nodeContentSB.append("Едва сдерживая [npc.her] восторг от того, что ее спросили о [npc.her] прошлой жизни, [npc.name] быстро отвечает: [npc.speech(Спасибо, что спросил, [npc.pcName]! Ох, но, может, нам стоит поговорить об этом как-нибудь в другой раз...)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append("[npc.Name] улыбается, когда ты спрашиваешь [npc.herHim] о [npc.her] прошлой жизни, и отвечает: [npc.speech(Ах, [npc.pcName], может, нам стоит поговорить об этом как-нибудь в другой раз...)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append("Явно счастливая от того, что ее спросили о [npc.her] прошлой жизни, [npc.name] быстро отвечает: [npc.speech(Прости, [npc.pcName], но нам придется поговорить об этом как-нибудь в другой раз...)]");
								break;
						}
						break;
				}
				UtilText.nodeContentSB.append("</p>");
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_MINOR = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Разговор с [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>Решив воспользоваться тем, что [npc.name] — это не просто секс-кукла, вы заводите светскую беседу с [npc.herHim].</p><p>Поначалу кажется, будто вы говорите с обычным человеком, так как [npc.name] увлеченно отвечает на ваши вопросы и утверждения. Однако вскоре вы замечаете, что [npc.she] почти ничего не привносит в разговор, а просто реагирует на всё, что вы говорите.</p><p>Заметив этот предел способностей [npc.namePos], вы позволяете разговору подойти к концу, после чего задумываетесь, что делать дальше...</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>Вы решаете попытаться завязать непринужденную беседу с [npc.name] и задаете [npc.herHim] ряд вопросов: от того, как [npc.sheIs] находит жизнь в качестве вашей рабыни, до того, что [npc.she] думает о своеобразной арканической погоде здесь, в Dominion.");
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" Однако, как бы вы ни старались, ваши попытки завязать разговор натыкаются на невероятно грубые замечания.</p><p>Понимая, что так вы ни к чему не придете, вы бросаете попытки говорить с [npc.name]. Когда вы отворачиваетесь, [npc.she] хмурится. [npc.speech(Можешь ты уже просто отъебаться?!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" Однако, как бы вы ни старались, ваши попытки завязать разговор натыкаются на короткие, пренебрежительные реплики.</p><p>Понимая, что так вы ни к чему не придете, вы бросаете попытки говорить с [npc.name]. Когда вы отворачиваетесь, [npc.she] хмурится. [npc.speech(Ты закончил со мной, [npc.pcName]?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(" Как бы сильно вы ни старались, любые попытки завязать разговор натыкаются лишь на короткие, пренебрежительные реплики.</p><p>Понимая, что так вы ничего не добьетесь, вы решаете перестать навязываться [npc.name]. Когда вы уже собираетесь отвернуться, [npc.she] спрашивает: [npc.speech(Могу ли я сделать для вас что-нибудь еще, [npc.pcName]?)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> вы, ваши попытки светской беседы приносят больше вреда, чем пользы!</i></p>");
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											" Хотя поначалу [npc.she] кажется незаинтересованной в разговоре с вами, [npc.name] тем не менее отвечает на каждый ваш вопрос весьма дружелюбно. По медленно расцветающей улыбке на лице [npc.her] можно понять, что [npc.she] ценит ваши попытки помочь [npc.herHim] расслабиться.</p><p>Проведя за такой беседой с [npc.name] некоторое время, вы решаете ее закончить, и в этот момент ваша рабыня бормочет: [npc.speech(Спасибо, что поговорили со мной, [npc.pcName]...)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										" Хотя [npc.she] кажется немного нерешительной в разговоре с вами, [npc.name], тем не менее, отвечает на каждый ваш вопрос весьма дружелюбно. По медленно расцветающей улыбке на лице [npc.her] можно понять, что [npc.she] ценит ваши попытки разрядить обстановку и успокоить [npc.herHim].</p><p>Проведя некоторое время в такой беседе с [npc.name], вы решаете закончить разговор, и в этот момент ваша рабыня бормочет: [npc.speech(Спасибо, [npc.pcName]. Мне было приятно пообщаться с вами...)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										" [npc.She] послушно и дружелюбно отвечает на каждый ваш вопрос. По медленно расцветающей улыбке на лице [npc.her] можно понять, что [npc.she] ценит ваши попытки разрядить обстановку и успокоить [npc.herHim].</p><p>Проведя некоторое время в такой беседе с [npc.name], вы решаете закончить разговор, и в этот момент ваша рабыня улыбается. [npc.speech(Надеюсь, мои ответы вас удовлетворили, [npc.pcName].)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> ваши попытки завязать светскую беседу помогают [npc.herHim] привязаться к вам еще сильнее!</i></p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											" Сияя от счастья, [npc.name] с энтузиазмом отвечает на каждый твой вопрос, причем [npc.her] ведет себя скорее как близкий друг, чем как твоя рабыня. По [npc.her] улыбке и тому, как [npc.she] тоскливо заглядывает тебе в [pc.eyes], можно понять, что [npc.name] искренне ценит то, что ты находишь время поговорить с [npc.herHim].</p><p>Спустя некоторое время ты решаешь закончить разговор, и когда ты это делаешь, твоя рабыня расплывается в улыбке. [npc.speech(Спасибо, [npc.pcName]! Это так здорово — вот так время от времени болтать с тобой!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										" Сияя от счастья, [npc.name] с энтузиазмом отвечает на каждый твой вопрос, но [npc.she] старается не переигрывать, сохраняя некоторую дистанцию, пока [npc.she] пытается вести себя как послушная рабыня. По [npc.her] улыбке и тому, как [npc.she] тоскливо заглядывает тебе в [pc.eyes], можно понять, что [npc.name] искренне ценит то, что ты находишь время поговорить с [npc.herHim].</p><p>Спустя некоторое время ты решаешь закончить разговор, и когда ты это делаешь, твоя рабыня улыбается. [npc.speech(Благодарю вас, [npc.pcName]. Я ценю, что вы уделили мне время для разговора.)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										" Стараясь сдержать волнение в [npc.her] голосе, [npc.name] с энтузиазмом отвечает на каждый твой вопрос, хотя [npc.sheIs] и старается сохранять [npc.her] самообладание, делая [npc.her] всё возможное, чтобы вести себя как послушная рабыня. Однако [npc.She] не может полностью подавить [npc.her] улыбку и то, как [npc.she] тоскливо смотрит снизу вверх в твои [pc.eyes], давая тебе понять, что [npc.she] очень ценит то, что ты находишь время поговорить с [npc.herHim].</p><p>Спустя некоторое время ты решаешь закончить разговор, и когда ты это делаешь, твоя рабыня улыбается. [npc.speech(Надеюсь, мои ответы тебя удовлетворили, [npc.pcName].)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>Благодаря тебе твоя попытка завязать светскую беседу очень помогает [npc.herHim] симпатизировать тебе еще больше!</i></p>");
						break;
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_ENCOURAGE = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Подбодрить [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				String jobName = getCurrentJob().getName(getSlave());
				UtilText.nodeContentSB.append("<p>Желая услышать, что [npc.name] думает о [npc.her] работе, ты спрашиваешь [npc.herHim], что [npc.she] думает о работе в качестве "
							+ UtilText.generateSingularDeterminer(jobName)+" "+jobName+".</p><p>[npc.speech(Я ничего особенного об этом не думаю, [pc.name],)] — отвечает кукла, [npc.speech(Я здесь лишь для того, чтобы служить тебе, что бы ты ни велел мне делать.)]</p><p>Похоже, на эту тему тебе не удастся многого добиться от [npc.name]...</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>");
				
				switch(getCurrentJob()) {
					case CLEANING:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] старалась изо всех сил, работая твоей ");
						UtilText.nodeContentSB.append(Util.capitaliseSentence(SlaveJob.CLEANING.getName(getSlave())));
						UtilText.nodeContentSB.append(", ты спрашиваешь [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case IDLE:
						UtilText.nodeContentSB.append("Хотя [npc.name] еще не назначена на работу, вы спрашиваете [npc.her], как [npc.sheIs] находит жизнь в качестве вашей рабыни.");
						break;
					case GARDEN:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая садовницей, вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case SECURITY:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая охранницей, вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case KITCHEN:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая на кухне вашей поваром, вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case LAB_ASSISTANT:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая в лаборатории Lilaya в качестве помощницы вашей [lilaya.relation(pc)], вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case LIBRARY:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая библиотекарем в обширной библиотеке Lilaya, вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case OFFICE:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая в офисе, вы спрашиваете [npc.her], счастлива ли [npc.sheIs] заниматься всей этой бумажной волокитой.");
						break;
					case TEST_SUBJECT:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, будучи подопытной в исследованиях Lilaya по трансформации, вы спрашиваете [npc.her], как [npc.sheIs] находит это.");
						break;
					case PUBLIC_STOCKS:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, будучи выставленной на всеобщее обозрение в колодках в Переулке Рабов, вы спрашиваете [npc.her], как [npc.sheIs] находит это.");
						break;
					case PROSTITUTE:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая проституткой в Ангельском Поцелуе, вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case MILKING:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] выкладывалась на полную, работая в доильных стойлах, вы спрашиваете [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case BEDROOM:
						UtilText.nodeContentSB.append("Желая убедиться, что [npc.nameIsFull] счастлива, вы спрашиваете [npc.herHim], как [npc.she] относится к тому, что её назначили прислуживать вам в спальне.");
						break;
					case SPA:
						UtilText.nodeContentSB.append("Желая убедиться, что [npc.nameIsFull] счастлива, вы спрашиваете [npc.herHim], как [npc.she] относится к тому, что её назначили работать в спа.");
						break;
					case SPA_RECEPTIONIST:
						UtilText.nodeContentSB.append("Желая убедиться, что [npc.nameIsFull] счастлива, вы спрашиваете [npc.herHim], как [npc.she] относится к тому, что её назначили работать в спа.");
						break;
					case DINING_HALL:
						UtilText.nodeContentSB.append("Желая подбодрить [npc.name], чтобы [npc.her] старалась изо всех сил, работая твоей ");
						UtilText.nodeContentSB.append(Util.capitaliseSentence(SlaveJob.DINING_HALL.getName(getSlave())));
						UtilText.nodeContentSB.append(", ты спрашиваешь [npc.her], как [npc.sheIs] находит эту работу.");
						break;
					case DOLL_STATUE:
						break;
				}
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" Как только вы заканчиваете говорить, ваша непослушная рабыня выплевывает: [npc.speech(Да пошел ты! Ненавижу это всё, я даже не работаю, когда на дежурстве, так что иди нахуй!)]</p><p>Из [npc.her] грубой реакции совершенно ясно, что [npc.name] не только ненавидит вас, но и не особо горит желанием жить в качестве вашей рабыни. Прежде чем вы успеваете попытаться попросить [npc.herHim] дать вам внятный ответ, [npc.she] поворачивается к вам [npc.her] спиной и рычит: [npc.speech(Почему бы тебе просто не съебаться и не пойти пососать член Lilaya!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" Как только ты заканчиваешь говорить, твоя рабыня резко отвечает: [npc.speech(Я делаю то, что должна. Мне это не в радость, как и роль твоей рабыни, но я сделаю то, что необходимо.)]</p><p>Судя по [npc.her] резкой реакции, совершенно очевидно, что [npc.name] не слишком привыкла к [npc.her] жизни в рабстве. Прежде чем ты успеваешь что-то добавить, [npc.she] нетерпеливо спрашивает: [npc.speech(Есть еще что-то, [npc.pcName]? Или на сегодня ты со мной закончил?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(" Как только ты заканчиваешь говорить, твоя послушная рабыня быстро отвечает: [npc.speech(Я делаю всё, что мне велят, [npc.pcName]. Я буду исполнять свои обязанности настолько хорошо, насколько смогу, ведь именно этого от меня ждут.)]</p><p>Судя по [npc.her] резкой реакции, совершенно очевидно, что хотя [npc.name] и будет послушно исполнять [npc.her] обязанности рабыни, [npc.she] ты ей не нравишься. Прежде чем ты успеваешь что-то добавить, [npc.she] спрашивает: [npc.speech(Что еще тебе от меня нужно, [npc.pcName]?)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> Твоё проявление интереса к [npc.her] работе произвело лишь незначительный эффект!</i></p>");
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" Как только ты заканчиваешь говорить, твоя непослушная рабыня начинает ныть: [npc.speech(Ну, у меня ведь нет выбора! В смысле, спасибо, конечно, что спросил, но я же рабыня, так что мне приходится делать всё, что ты прикажешь, нравится мне это или нет...)]</p><p>Судя по [npc.her] предельно честной реакции, становится ясно, что [npc.name] еще не до конца привыкла к жизни в качестве твоей рабыни. Пока ты раздумываешь, что ответить, [npc.she] спрашивает: [npc.speech(Так чего ты хочешь, [npc.pcName]?)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" Как только ты заканчиваешь говорить, твоя рабыня отвечает: [npc.speech(Ну, не так уж и плохо, [npc.pcName]. Спасибо, что спросил, я буду делать всё, что ты прикажешь...)]</p><p>Судя по [npc.her] честной реакции, становится ясно, что у [npc.name] всё еще есть некоторые опасения по поводу жизни в качестве твоей рабыни. Пока ты раздумываешь, что ответить, [npc.she] спрашивает: [npc.speech(Могу я сделать для тебя что-нибудь еще, [npc.pcName]?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
											" Как только вы заканчиваете говорить, ваша рабыня отвечает: [npc.speech(Я всегда стараюсь изо всех сил, [npc.pcName]. Спасибо, что спросили, но будьте уверены, что я работаю на пределе своих возможностей.)]</p><p>Судя по реакции [npc.her], совершенно очевидно, что [npc.name] полностью приняла [npc.her] в качестве вашей рабыни. Однако ответ [npc.Her] показался немного холодным, и вы понимаете, что, хотя [npc.she] и не ненавидит вас, [npc.she] вас и не то чтобы любит. Прежде чем вы успеваете что-то сказать, [npc.she] продолжает: [npc.speech(Что еще я могу сделать для вас, [npc.pcName]?)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что вы проявили интерес к работе [npc.her], произвело заметный эффект!</i></p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											" Как только вы заканчиваете говорить, ваша непослушная рабыня смеется, [npc.speech(Ну, не то чтобы у меня был выбор, верно? Ха-ха! Но не волнуйся, я тебя люблю! В-в смысле! Мне нравится <i>работать</i> на тебя!)]</p><p>[npc.Her] щеки заливаются ярко-красным цветом, пока [npc.she] пытается поспешно скрыть [npc.her] оговорку, но даже без [npc.her] случайного признания вы уже знаете, что [npc.name] глубоко привязана к вам. По тому, как [npc.she] смотрит на вас, и по улыбке, расцветающей на [npc.her] лице каждый раз, когда вы уделяете [npc.herHim] хоть каплю внимания, всем ясно, насколько сильно [npc.she] вами очарована. Прежде чем вы успеваете что-либо сказать по этому поводу, ваша рабыня быстро пытается перевести тему на что-то менее смущающее для [npc.herHim], [npc.speech(Могу я сделать для тебя что-нибудь еще, [npc.pcName]? Что угодно, только попроси!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										" Как только вы заканчиваете говорить, ваша рабыня улыбается и отвечает: [npc.speech(Все идет замечательно, спасибо, что спросили, [npc.pcName]. Мне так нравится работать на вас...)]</p><p>[npc.Her] щеки заливаются ярко-красным цветом, когда [npc.she] признается в [npc.her] удовольствии быть вашей рабыней, но даже без [npc.her] застенчивого проявления вы и так знаете, что [npc.name] глубоко привязана к вам. По тому, как [npc.she] смотрит на вас, и по улыбке, расцветающей на [npc.her] лице каждый раз, когда вы уделяете [npc.herHim] хоть каплю внимания, всем ясно, насколько сильно [npc.she] вами очарована. Прежде чем вы успеваете что-либо сказать на этот счет, ваша рабыня поспешно пытается перевести тему на что-то менее смущающее для [npc.herHim]: [npc.speech(Могу ли я сделать для вас что-нибудь еще, [npc.pcName]?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										" Как только вы заканчиваете говорить, ваша рабыня улыбается и отвечает: [npc.speech(Все идет просто замечательно, [npc.pcName]. Мне так нравится работать на вас...)]</p><p>[npc.Her] щеки заливаются ярко-красным цветом, когда [npc.she] признается в [npc.her] удовольствии быть вашей рабыней, но даже без [npc.her] застенчивого проявления вы и так знаете, что [npc.name] глубоко привязана к вам. По тому, как [npc.she] смотрит на вас, и по улыбке, расцветающей на [npc.her] лице каждый раз, когда вы уделяете [npc.herHim] хоть каплю внимания, всем ясно, насколько сильно [npc.she] вами очарована. Прежде чем вы успеваете что-то сказать на этот счет, ваша рабыня поспешно пытается перевести тему на что-то менее смущающее для [npc.herHim]: [npc.speech(Могу ли я сделать для вас что-нибудь еще, [npc.pcName]?)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что вы проявили интерес к [npc.her] работе, оказало на нее огромное влияние!</i></p>");
						break;
				}
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_HUG = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Обнять [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>Желая проявить к [npc.name] немного физической привязанности, вы [pc.step] к [npc.herHim] и заключаете [npc.herHim] в крепкие объятия.</p><p>Ваша кукла тут же отвечает тем же, обвивая [npc.her] [npc.arms] вокруг вас и затягивая в [npc.herHim]. Хотя это именно та реакция, которой вы добивались, в [npc.namePos] объятиях нет ни тепла, ни близости, поэтому вскоре вы разжимаете руки и [pc.step] от [npc.herHim].</p><p>[npc.speech(Надеюсь, это было вам по вкусу, [pc.name],)] [npc.she] произносит, прежде чем замереть в ожидании того, чего вы пожелаете от [npc.herHim] дальше...</p>");
				
			} else {
				AffectionLevelBasic slaveAffection = AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()));
				
				boolean silly = Main.game.isSillyMode() && Math.random()<0.25f && slaveAffection==AffectionLevelBasic.NEUTRAL;
				
				UtilText.nodeContentSB.append("<p>");
				
				if(silly) {
					UtilText.nodeContentSB.append("Желая, чтобы [npc.name] проявила к вам физическую привязанность, вы просите [npc.herHim] обнять вас.");
				} else {
					UtilText.nodeContentSB.append("Вы решаете, что [npc.name] нуждается в утешении, и, сделав шаг вперед, хватаете [npc.herHim], после чего притягиваете [npc.herHim] в крепкие объятия.");
				}
				
				switch(slaveAffection) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											" [npc.She] мгновенно пытается вырваться из ваших рук, выкрикивая: [npc.speech(Отпусти меня! Да отъебись ты уже!)]</p><p>Вы игнорируете протесты своей непослушной рабыни, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] продолжает бороться с вами, ничуть не впечатленная вашим способом утешения [npc.herHim]. Спустя короткое время вы наконец отпускаете [npc.name], и [npc.she] отшатывается, крича: [npc.speech(Мне не нужна твоя жалость, ты "+(Main.game.getPlayer().isFeminine()?"сучка":"ублюдок!)}")+"!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										" [npc.She] инстинктивно пытается вырваться из твоих рук, скуля: [npc.speech([npc.PcName], пожалуйста! Я этого не хочу!)]</p><p>Ты игнорируешь протесты своей рабыни, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] пытается выскользнуть из твоей хватки, явно не впечатленная твоим способом утешения [npc.herHim], но ты крепко держишь её, не давая [npc.her] сбежать. Спустя некоторое время ты наконец отпускаешь [npc.name], и [npc.she] отшатывается назад, бормоча: [npc.speech(Да я вообще не люблю объятия...)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										" [npc.She] замирает, когда ты берешь [npc.herHim], и бормочет, без тени искренности в [npc.her] голосе: [npc.speech(Спасибо, [npc.pcName]...)]</p><p>Ты игнорируешь холодную реакцию своей рабыни, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] остается совершенно неподвижной, явно не впечатленной твоим способом утешения [npc.herHim], но ты игнорируешь нежелание [npc.her] реагировать и продолжаешь прижиматься к [npc.herHim]. Спустя некоторое время ты наконец отпускаешь [npc.name], и [npc.she] отступает назад, глядя в пол. [npc.speech(Спасибо, [npc.pcName]. Что еще тебе нужно?)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> ты, твоя попытка навязать физический контакт приносит больше вреда, чем пользы!</i></p>");
						break;
					case NEUTRAL:
						if(silly) {
							UtilText.nodeContentSB.append(
									"</p><p>[npc.speech(~Фух!~ Ладно...)] [npc.name] сдается, а затем, залившись румянцем, протягивает к тебе [npc.her] [npc.arms]. Маня тебя в свои [npc.her] объятия, она вздыхает: [npc.speech(Похоже, ты мой маленький погчамп... Иди сюда...)]</p><p>[pc.Stepping] вперед, ты игнорируешь [npc.namePos] смущение и позволяешь [npc.herHim] крепко тебя обнять. [npc.She] слегка похлопывает тебя по спине, и у тебя возникает четкое ощущение, что [npc.sheIs] отвечает на твой жест лишь потому, что от [npc.herHim] этого ждут. Спустя короткое время ты наконец отпускаешь [npc.name], позволяя [npc.herHim] [npc.step] назад и пробормотать: [npc.speech(Это было довольно поггерски...)]");
						} else {
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
											" [npc.She] неохотно отвечает на твои объятия, вздыхая: [npc.speech(Спасибо, [npc.pcName], пожалуй, обниматься время от времени не так уж и плохо...)]</p><p>Ты притягиваешь [npc.name] чуть ближе, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] слегка похлопывает тебя по спине, и у тебя возникает четкое ощущение, что [npc.sheIs] отвечает на твой жест лишь потому, что от [npc.herHim] этого ждут. Спустя короткое время ты наконец отпускаешь [npc.name], и [npc.she] отступает назад, улыбаясь. [npc.speech(Это было мило, пожалуй. Хочешь чего-нибудь еще?)]");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											" [npc.She] неохотно обнимает вас в ответ, вздыхая, [npc.speech(Спасибо, [npc.pcName]...)]</p><p>Вы притягиваете [npc.name] чуть ближе, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] слегка похлопывает вас по спине, и у вас складывается отчетливое впечатление, что [npc.sheIs] отвечает на ваш жест лишь потому, что этого ожидает [npc.herHim]. Спустя некоторое время вы, наконец, отпускаете [npc.name], и [npc.she] отступает назад с улыбкой. [npc.speech(Вам нужно что-нибудь еще, [npc.pcName]?)]");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											" [npc.She] неохотно обнимает вас в ответ, вздыхая, [npc.speech(Спасибо, [npc.pcName].)]</p><p>Вы притягиваете [npc.name] чуть ближе, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] слегка похлопывает вас по спине, и у вас складывается отчетливое впечатление, что [npc.sheIs] отвечает на ваш жест лишь потому, что этого ожидает [npc.herHim]. Спустя некоторое время вы, наконец, отпускаете [npc.name], и [npc.she] отступает назад с улыбкой. [npc.speech(Вам нужно что-нибудь еще, [npc.pcName]?)]");
									break;
							}
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что вы ободряюще обняли [npc.herHim], положительно сказалось на отношении [npc.she] к вам! Однако столь фамильярное обращение немного снизило [npc.her] Покорность...</i></p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											" [npc.She] с жадностью отвечает на твои объятия, издавая глубокий вздох, [npc.speech(Спасибо, [npc.pcName]!)]</p><p>Ты притягиваешь [npc.name] чуть ближе, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] прижимается к тебе, обвивая [npc.her] [npc.arms] вокруг твоей спины и довольно вздыхая. По [npc.her] реакции совершенно ясно, что [npc.she] искренне ценит твой жест, и, воодушевленный [npc.her] энтузиазмом, ты проводишь довольно много времени, обнимая свою рабыню.</p><p>Спустя некоторое время ты наконец отпускаешь [npc.name], и [npc.she] отступает назад с улыбкой. [npc.speech(Спасибо, [npc.pcName]! Мне это было очень нужно...)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										" [npc.She] с жадностью отвечает на твои объятия, издавая глубокий вздох, [npc.speech(Спасибо, [npc.pcName]...)]</p><p>Ты притягиваешь [npc.name] чуть ближе, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] прижимается к тебе, обвивая [npc.her] [npc.arms] вокруг твоей спины и довольно вздыхая. По [npc.her] реакции совершенно ясно, что [npc.she] искренне ценит твой жест, и, воодушевленный [npc.her] энтузиазмом, ты проводишь довольно много времени, обнимая свою рабыню.</p><p>Спустя некоторое время ты наконец отпускаешь [npc.name], и [npc.she] отступает назад с улыбкой. [npc.speech(Спасибо, [npc.pcName]. Мне это было очень нужно... Могу ли я что-нибудь сделать для тебя?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										" [npc.She] с нетерпением отвечает на твои объятия, издавая глубокий вздох, [npc.speech(Спасибо, [npc.pcName]...)]</p><p>Ты притягиваешь [npc.name] чуть ближе, прижимая [npc.herHim] к своему телу и вдыхая [npc.her] [npc.scent]. [npc.She] прижимается к тебе, обвивая [npc.her] [npc.arms] вокруг твоей спины и издавая удовлетворенный вздох. Судя по реакции [npc.her], совершенно очевидно, что [npc.she] действительно ценит твой жест, и, воодушевленный [npc.her] энтузиазмом, ты проводишь довольно много времени, обнимая свою рабыню.</p><p>Спустя некоторое время ты наконец отпускаешь [npc.name], и [npc.she] отступает на шаг, улыбаясь. [npc.speech(Спасибо, [npc.pcName]. Могу ли я что-нибудь для тебя сделать?)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что ты дал [npc.herHim] ободряющие объятия, оказало огромное положительное влияние на то, как [npc.she] видит тебя! Однако столь фамильярное обращение немного отрицательно сказалось на [npc.her] Покорности...</i></p>");
						break;
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_PETTINGS = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Погладить [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>Желая подбодрить [npc.name] лаской, вы протягиваете руку и кладете [pc.hand] на голову [npc.her]. Прежде чем [npc.she] успевает среагировать, вы начинаете поглаживать [npc.her] [npc.hair+], а затем плавно спускаетесь ниже, чтобы нежно почесать за одной из [npc.her] [npc.ears].</p><p>Ваша кукла подается навстречу вашим рукам, произнося, пока [npc.she] это делает: [npc.speech(Спасибо, [pc.name]. Надеюсь, я хорошо тебе служу.)]</p><p>Хотя вы продолжаете гладить [npc.name] еще несколько мгновений, холодный, роботизированный ответ [npc.her] — не совсем то, чего вы ожидали. Пустой, бесстрастный взгляд вашей куклы становится последней каплей, и вы убираете свою [pc.hand], гадая, что делать дальше...</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>Желая подбодрить [npc.name] лаской, вы протягиваете руку и кладете [pc.hand] на голову [npc.her]. Прежде чем [npc.she] успевает среагировать, вы начинаете поглаживать [npc.her] [npc.hair+], а затем плавно спускаетесь ниже, чтобы нежно почесать за одной из [npc.her] [npc.ears].</p><p>");
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"Потрясенная вашими действиями, [npc.name] требуется мгновение, чтобы ответить, и с гневным криком [npc.she] отшлепывает вашу [pc.hand] и отступает назад, выкрикивая: [npc.speech(Какого хера?! Слезь с меня! Оставь меня в покое, ублюдок!)]</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"Потрясенная вашими действиями, [npc.name] требуется мгновение, чтобы ответить, и с неловким скулением [npc.she] отступает назад, извиняясь: [npc.speech(Прости, [npc.pcName], но... не мог бы ты больше так не делать?)]</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"Потрясенная твоими действиями, [npc.name] требуется мгновение, чтобы ответить, и с неловким скулением [npc.she] замирает, морща [npc.her] [npc.eyes], пока [npc.she] заставляет [npc.herself] терпеть твое нежелательное внимание. Спустя мгновение ты прекращаешь то, что делаешь, и убираешь свою [pc.hand], заставляя [npc.name] облегченно вздохнуть, прежде чем она спрашивает: [npc.speech(Могу ли я сделать для тебя что-нибудь еще, [npc.pcName]?)]</p>");
								break;
						}
						UtilText.nodeContentSB.append(
								"<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> ты, твоя попытка навязать физический контакт приносит больше вреда, чем пользы!</i></p>");
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"Застигнутая врасплох твоим поступком, [npc.name] требуется мгновение, чтобы ответить, и с тихим вздохом [npc.she] наклоняет [npc.her] голову набок. [npc.speech(Это даже приятно... Продолжай!)]</p><p>Ты делаешь так, как просит [npc.she], и еще довольно долго гладишь и похлопываешь [npc.her] по голове. Однако в конце концов тебе кажется, что с [npc.namePos] на сегодня хватит, и ты убираешь свою [pc.hand], улыбаясь тому, как твоя рабыня издает очень довольный вздох.</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"Застигнутая врасплох твоим поступком, [npc.name] требуется мгновение, чтобы ответить, и с тихим вздохом [npc.she] наклоняет [npc.her] голову набок. [npc.speech(Это даже приятно... Спасибо, [npc.pcName]...)]</p><p>Воодушевленный реакцией [npc.her], ты продолжаешь гладить и похлопывать [npc.her] по голове еще довольно долго. Однако в конце концов тебе кажется, что с [npc.namePos] на сегодня хватит, и ты убираешь свою [pc.hand], улыбаясь тому, как твоя рабыня издает очень довольный вздох.</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"Застигнутый врасплох вашим действием, [npc.name] требуется мгновение, чтобы отреагировать, и с тихим вздохом [npc.she] наклоняет [npc.her] голову набок. [npc.speech(Спасибо, [npc.pcName]...)]</p><p>Воодушевленный реакцией [npc.her], вы продолжаете гладить и похлопывать [npc.her] по голове довольно долгое время. Однако в конце концов вам кажется, что с [npc.namePos] на сегодня хватит, и вы убираете свою [pc.hand], улыбаясь тому, как ваша рабыня издает очень довольный вздох.</p>");
								break;
						}
						UtilText.nodeContentSB.append(
								"<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что ты уделяешь [npc.herHim] столько интимного физического внимания, заставило [npc.her] полюбить тебя гораздо сильнее! Однако такое фамильярное обращение оказало слегка негативное влияние на [npc.her] Покорность...</i></p>");
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name] издает любящий вздох и делает шаг к вам, пока [npc.she] наклоняет [npc.her] голову набок. [npc.speech(Это так приятно... Продолжай!)]</p><p>Вы делаете так, как просит [npc.she], и продолжаете гладить и похлопывать [npc.her] по голове довольно долгое время. Однако в конце концов вам кажется, что с [npc.namePos] на сегодня хватит, и вы убираете свою [pc.hand], улыбаясь тому, как ваша рабыня издает очень довольный вздох.</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
											"[npc.Name] издает нежный вздох и делает шаг к тебе, пока [npc.she] склоняет [npc.her] голову набок. [npc.speech(Спасибо, [npc.pcName]! Это так приятно! Пожалуйста, не останавливайся!)]</p><p>Ты делаешь так, как просит [npc.she], и еще долго продолжаешь гладить и поглаживать [npc.her] по голове. Однако в конце концов тебе кажется, что с [npc.namePos] на сегодня хватит, и ты убираешь свою [pc.hand], улыбаясь тому, как твоя рабыня издает очень довольный вздох.</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
											"[npc.Name] издает нежный вздох и делает шаг к тебе, пока [npc.she] склоняет [npc.her] голову набок. [npc.speech(Спасибо, [npc.pcName]!)]</p><p>Воодушевленный реакцией [npc.her], ты продолжаешь гладить и поглаживать [npc.her] по голове еще какое-то время. Однако в конце концов тебе кажется, что с [npc.namePos] на сегодня хватит, и ты убираешь свою [pc.hand], улыбаясь тому, как твоя рабыня издает очень довольный вздох.</p>");
								break;
						}
						UtilText.nodeContentSB.append(
								"<p>"
									+ "<i>Due to the fact that [npc.name]"
										+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что ты уделяешь [npc.herHim] столько интимного физического внимания, заставило [npc.her] полюбить тебя гораздо сильнее! Однако такое фамильярное обращение оказало слегка негативное влияние на [npc.her] Покорность...</i></p>");
						break;
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_PRESENT = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Вручить [npc.Name] подарок");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Решив, что [npc.name] заслуживает подарка в эти праздничные дни, ты протягиваешь презент [npc.herHim]. [pc.speech(Это тебе, [npc.name]. Счастливых праздников!)]</p><p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Застигнутая совершенно врасплох, [npc.name], кажется, не знает, что ответить, и когда ты вкладываешь подарок в руки [npc.her] [npc.hands], [npc.she] бормочет: [npc.speech(Д-для меня? Ты же знаешь, что я все еще тебя ненавижу...)]</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Застигнутая врасплох, [npc.name], кажется, не знает, что ответить, и когда ты вкладываешь подарок в [npc.her] [npc.hands], [npc.she] бормочет: [npc.speech(Д-для меня? Что ж, тогда я возьму его...)]</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Застигнутая врасплох, [npc.name], кажется, не знает, что ответить, но когда ты вкладываешь подарок в [npc.her] [npc.hands], [npc.she] обретает [npc.her] самообладание и холодно заявляет: [npc.speech(Я приму это, если такова твоя воля, [npc.pcName].)]</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>Despite the fact that [npc.she]"
									+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> тебе, [npc.name] очень рада получить подарок!</i></p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Застигнутая врасплох, [npc.name] издает радостный возглас, и когда ты вкладываешь подарок в [npc.her] [npc.hands], [npc.she] улыбается тебе. [npc.speech(Д-для меня? Спасибо, [npc.pcName]!)]</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Застигнутая врасплох, [npc.name] издает радостный возглас, и когда ты вкладываешь подарок в [npc.her] [npc.hands], [npc.she] улыбается тебе. [npc.speech(Д-для меня? Спасибо, [npc.pcName]!)]</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Застигнутая врасплох, [npc.name] издает радостный возглас, и когда ты вкладываешь подарок в [npc.her] [npc.hands], [npc.she] улыбается тебе. [npc.speech(Спасибо, [npc.pcName], и тебя с праздниками!)]</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p><i>Хотя [npc.name] рада получить подарок, столь дружелюбное отношение немного снизило [npc.her] Покорность...</i></p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Застигнутая врасплох, [npc.name] издает восторженный крик, и когда ты вжимаешь подарок в [npc.her] [npc.hands], наружу вырывается [npc.she], [npc.speech([npc.PcName]! Спасибо тебе огромное! И тебя с праздником!)]</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Застигнутая врасплох, [npc.name] издает восторженный крик, и когда ты вжимаешь подарок в [npc.her] [npc.hands], наружу вырывается [npc.she], [npc.speech([npc.PcName]! Спасибо тебе огромное! И тебя с праздником!)]</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Застигнутая врасплох, [npc.name] издает восторженный крик, и когда ты вжимаешь подарок в [npc.her] [npc.hands], [npc.she] сияет, глядя на тебя. [npc.speech(Спасибо, [npc.pcName]! И тебя с праздником!)]</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p><i>Хотя [npc.name] рада получить подарок, столь дружелюбное отношение немного снизило [npc.her] Покорность...</i></p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static boolean isSlaveNaked() {
		return getSlave().isCoverableAreaVisible(CoverableArea.ANUS)
				&& getSlave().isCoverableAreaVisible(CoverableArea.NIPPLES)
				&& getSlave().isCoverableAreaVisible(CoverableArea.PENIS)
				&& getSlave().isCoverableAreaVisible(CoverableArea.VAGINA);
	}
	
	public static final DialogueNode SLAVE_INSPECT = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Осмотр [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
						"<p>"
							+ (isSlaveNaked()
									?"Решив, что хочешь осмотреть обнаженное тело [npc.namePos], ты приказываешь [npc.herHim] отступить на шаг, чтобы лучше рассмотреть [npc.herHim]."
									:"Решив, что хочешь осмотреть тело [npc.namePos], ты приказываешь [npc.herHim] раздеться догола, после чего делаешь шаг назад, чтобы лучше рассмотреть [npc.herHim].")
						+ "</p>"
						+ "<p>");
			String legsSpreading = getSlave().hasLegs()
					?"разводит [npc.her] [npc.legs] и демонстрирует [npc.her] "
					:"преподносит тебе [npc.her] ";
			
			if(isDoll()) {
				UtilText.nodeContentSB.append(
						(isSlaveNaked()
								?"[npc.She] тут же спешит исполнить твой приказ, послушно отступает на шаг и спрашивает,"
								:"[npc.She] тут же спешит исполнить твой приказ, послушно сбрасывая [npc.her] одежду на пол. Когда [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает,")
						+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
						+ " наконец, вы приказываете своей кукле показать вам порт в задней части шеи [npc.her] и проверяете, чтобы он был в идеальном состоянии.</p><p>Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и без единого слова жалобы [npc.she] вновь делает именно то, что вы говорите.</p>");
				
			} else {
				if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() || getSlave().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.She] сердито хмурится, когда [npc.she] слышит твой приказ, но, осознав, что у [npc.she] действительно нет выбора, неохотно начинает подчиняться."
												+ (isSlaveNaked()
														?" Бросая на вас яростный взгляд, [npc.she] рычит:"
														:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] рычит:")
												+ " [npc.speech(Чертов "+(Main.game.getPlayer().isFeminine()?"сучка":"ублюдок!)}")+"! Давай же! Рассматривай свою <i>собственность</i> получше, ты больной ублюдок!)]</p><p>Несмотря на гневные слова [npc.her], вы улавливаете нотки возбуждения в голосе [npc.namePos], и [npc.she] оказывает удивительно мало сопротивления, когда вы приказываете [npc.herHim] покрутиться перед вами. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
												+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
													+", и хотя это вызывает очередную гневную реплику из-за непослушных [npc.lips], вы понимаете, что [npc.she] втайне наслаждалась тем, что демонстрирует вам [npc.herself].</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.She] разочарованно вздыхает, когда [npc.she] слышит твой приказ, но, стараясь сдерживать [npc.her] эмоции, неохотно начинает подчиняться."
											+ (isSlaveNaked()
													?" Сверля вас взглядом, [npc.she] огрызается:"
													:" Пока [npc.she] снимает с [npc.her] одежду, [npc.she] резко бросает:")
											+ " [npc.speech(Очень хорошо, <i>[npc.pcName]</i>.)]</p><p>Несмотря на слегка бунтарский тон [npc.her], вы улавливаете в [npc.namePos] голосе нотки возбуждения, и [npc.she] оказывает удивительно мало сопротивления, когда вы приказываете [npc.herHim] вышагивать перед вами. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
												+", и по разочарованному вздоху [npc.her] вы понимаете, что [npc.she] понравилось демонстрировать вам [npc.herself].</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													? "[npc.She] тут же принимается выполнять ваш приказ, но вы видите отчетливый взгляд ненависти в [npc.her] [npc.eyes], когда [npc.she] послушно отступает назад и спрашивает:"
													: "[npc.She] тут же принимается выполнять ваш приказ, но вы видите отчетливый взгляд ненависти в [npc.her] [npc.eyes], когда [npc.she] послушно роняет [npc.her] одежду на пол. Пока [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает:")
											+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+ " Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
												+", и, не проронив ни слова жалобы, [npc.she] вновь делает именно то, что ты велел. По тому, как покраснели щеки [npc.her], можно понять, что [npc.she] доставило удовольствие демонстрировать [npc.herself] тебе.</p>");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.She] радостно вскрикивает, услышав твой приказ, и [npc.she] поспешно принимается его выполнять."
												+ (isSlaveNaked()
														?" Соблазнительно улыбаясь тебе, [npc.she] [npc.moansVerb],"
														:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] [npc.moansVerb],")
												+ " [npc.speech(~Ммм~ Надеюсь, тебе это понравится так же сильно, как и мне, [npc.pcName]...)]</p><p>Игнорируя слова [npc.her], ты приказываешь [npc.name] вышагивать перед тобой; приказ, который [npc.she] снова с готовностью исполняет. После этого ты заставляешь [npc.herHim] "+legsSpreading+partInspection()
												+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
												+", что вызывает разочарованный вздох из-за [npc.lips] твоей непослушной рабыни.</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.She] радостно вскрикивает, услышав приказ [npc.she], но, стараясь держать [npc.her] эмоции под контролем, поспешно принимается его выполнять."
											+ (isSlaveNaked()
													?" Соблазнительно улыбаясь тебе, [npc.she] [npc.moansVerb],"
													:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] [npc.moansVerb],")
											+ " [npc.speech(Надеюсь, это удовлетворит тебя, [npc.pcName].)]</p><p>После того как [npc.sheHas] раздета догола, ты приказываешь [npc.name] вышагивать перед тобой; приказ, который [npc.she] снова с готовностью исполняет. После этого ты заставляешь [npc.herHim] "+legsSpreading+partInspection()
											+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и с разочарованным вздохом [npc.she] делает то, что вы приказали.</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.She] немедленно принимается исполнять твой приказ, и ты видишь явный признак возбуждения в [npc.her] [npc.eyes], когда [npc.she] послушно отступает назад и спрашивает,"
													:"[npc.She] немедленно принимает твой приказ, и ты видишь явный признак возбуждения в [npc.her] [npc.eyes], когда [npc.she] послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последнюю из [npc.her] вещей, [npc.she] спрашивает,")
											+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+ " Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, не проронив ни слова жалобы, [npc.she] вновь делает именно то, что ты велел. По тому, как покраснели щеки [npc.her], можно понять, что [npc.she] доставило удовольствие демонстрировать [npc.herself] тебе.</p>");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.She] издает радостный тоненький возглас, услышав твой приказ, и поспешно принимается исполнять его, как и [npc.she]."
												+ (isSlaveNaked()
														?" Соблазнительно улыбаясь тебе, [npc.she] [npc.moansVerb],"
														:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] [npc.moansVerb],")
												+ " [npc.speech(~Ммм~ Это так унизительно — когда тебя заставляют это делать... Обожаю это...)]</p><p>Игнорируя слова [npc.her], ты приказываешь [npc.name] покрутиться перед тобой; приказ, который [npc.she] снова с радостью исполняет. После этого ты заставляешь [npc.herHim] "+legsSpreading+partInspection()
												+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
													+", что вызывает разочарованный вздох из-за [npc.lips] твоей непослушной рабыни.</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.She] издает радостный тоненький возглас, когда [npc.she] слышит твой приказ, но, стараясь держать [npc.her] эмоции под контролем, поспешно принимается исполнять."
											+ (isSlaveNaked()
													?" Соблазнительно улыбаясь тебе, [npc.she] [npc.moansVerb],"
													:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] [npc.moansVerb],")
											+ " [npc.speech(Надеюсь, это порадует тебя, [npc.pcName]...)]</p><p>Игнорируя любовный тон голоса [npc.her], ты приказываешь [npc.name] покрутиться перед тобой; приказ, который [npc.she] снова с радостью исполняет. После этого ты заставляешь [npc.herHim] "+legsSpreading+partInspection()
											+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и с разочарованным вздохом [npc.she] делает то, что вы приказали.</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.She] немедленно принимается исполнять твой приказ, и ты видишь явный признак возбуждения в [npc.her] [npc.eyes], когда [npc.she] послушно отступает назад и спрашивает,"
													:"[npc.She] немедленно принимает твой приказ, и ты видишь явный признак возбуждения в [npc.her] [npc.eyes], когда [npc.she] послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последнюю из [npc.her] вещей, [npc.she] спрашивает,")
											+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+ " Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, не проронив ни слова жалобы, [npc.she] вновь делает именно то, что ты велел. По тому, как покраснели щеки [npc.her], можно понять, что [npc.she] доставило удовольствие демонстрировать [npc.herself] тебе.</p>");
									break;
							}
							break;
					}
					UtilText.nodeContentSB.append(
							"<p><i>[npc.Name] обожает выставлять свою [npc.herself] напоказ, и это повышает как [npc.her] привязанность к тебе, так и [npc.her] покорность!</i></p>");
					
				} else {
					switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.She] сердито хмурится, когда [npc.she] слышит твой приказ, но, осознав, что у [npc.she] действительно нет выбора, неохотно начинает подчиняться."
												+ (isSlaveNaked()
														?" Бросая на вас яростный взгляд, [npc.she] рычит:"
														:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] рычит:")
												+ " [npc.speech(Чертов "+(Main.game.getPlayer().isFeminine()?"сучка":"ублюдок!)}")+"! Давай же! Взгляни хорошенько на свою <i>собственность</i>, больной ублюдок!)]</p><p>Игнорируя [npc.her] мятежные слова, ты приказываешь [npc.name] вышагивать перед тобой; приказ, который [npc.she] снова неохотно исполняет. После этого ты заставляешь [npc.herHim] "+legsSpreading+partInspection()
												+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
													+", вызывая очередное гневное замечание из уст твоей непокорной рабыни [npc.lips].</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.She] разочарованно вздыхает, когда [npc.she] слышит твой приказ, но, стараясь сдерживать [npc.her] эмоции, неохотно начинает подчиняться."
												+ (isSlaveNaked()
														?" Сверля вас взглядом, [npc.she] огрызается:"
														:" Пока [npc.she] снимает с [npc.her] одежду, [npc.she] резко бросает:")
											+ " [npc.speech(Очень хорошо, <i>[npc.pcName]</i>.)]</p><p>Игнорируя слегка дерзкий тон [npc.her], ты приказываешь [npc.name] вышагивать перед тобой; приказ, который [npc.she] снова неохотно исполняет. После этого ты заставляешь [npc.herHim] "+legsSpreading+partInspection()
											+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, с облегченным вздохом, [npc.she] делает так, как ты велел.</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													? "[npc.She] тут же принимается выполнять ваш приказ, но вы видите отчетливый взгляд ненависти в [npc.her] [npc.eyes], когда [npc.she] послушно отступает назад и спрашивает:"
													: "[npc.She] тут же принимается выполнять ваш приказ, но вы видите отчетливый взгляд ненависти в [npc.her] [npc.eyes], когда [npc.she] послушно роняет [npc.her] одежду на пол. Пока [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает:")
											+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+ " Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, без единого слова жалобы, [npc.she] снова делает именно то, что ты говоришь.</p>");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.She] смущенно вскрикивает, услышав приказ [npc.she], но, осознав, что у [npc.she] действительно нет выбора, неохотно подчиняется."
												+ (isSlaveNaked()
														?" Приняв [npc.her] самый умоляющий вид, [npc.she] скулит,"
														:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] скулит,")
												+ " [npc.speech(Неужели это действительно необходимо? Это как-то унизительно — когда тебя заставляют...)]</p><p>Игнорируя слова [npc.her], вы приказываете [npc.name] пройтись перед вами; приказ, который [npc.she] снова неохотно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
												+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
													+", что вызывает облегченный вздох из-под [npc.lips] вашей непокорной рабыни.</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.She] издает смущенный возглас, когда [npc.she] слышит ваш приказ, но, пытаясь сдержать [npc.her] эмоции, неохотно принимается повиноваться."
											+ (isSlaveNaked()
													?" Приняв [npc.her] самый умоляющий вид, [npc.she] скулит,"
													:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] скулит,")
											+ " [npc.speech(Просто дай мне минутку, [npc.pcName].)]</p><p>После того как [npc.sheIs] раздевается догола, вы приказываете [npc.name] пройтись перед вами; приказ, который [npc.she] снова неохотно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, с облегченным вздохом, [npc.she] делает так, как ты велел.</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.She] немедленно принимается исполнять ваш приказ, и вы видите явный след страдания в глазах [npc.her] [npc.eyes], когда [npc.she] послушно отступает назад и спрашивает,"
													:"[npc.She] немедленно принимается исполнять ваш приказ, и вы видите явный след страдания в глазах [npc.her] [npc.eyes], когда [npc.she] послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последнюю вещь с [npc.her], [npc.she] спрашивает,")
											+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+ " Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, без единого слова жалобы, [npc.she] снова делает именно то, что ты говоришь.</p>");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(
												"[npc.She] издает тихий печальный крик, когда [npc.she] слышит ваш приказ, но, понимая, что у [npc.she] действительно нет выбора, неохотно принимается повиноваться."
												+ (isSlaveNaked()
														?" Приняв [npc.her] самый умоляющий вид, [npc.she] скулит,"
														:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] скулит,")
												+ " [npc.speech(Знаешь, это как-то унизительно — когда тебя заставляют делать такое... А я думала, я тебе нравлюсь...)]</p><p>Игнорируя слова [npc.her], вы приказываете [npc.name] вышагивать перед вами; приказ, который [npc.she] снова неохотно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
												+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")
													+", что вызывает облегченный вздох из-под [npc.lips] вашей непокорной рабыни.</p>");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(
											"[npc.She] издает тихий печальный возглас, услышав твой приказ, но, стараясь сдерживать [npc.her] эмоции, медленно начинает подчиняться, пока [npc.she] наблюдает за ней."
											+ (isSlaveNaked()
													?" Приняв [npc.her] самый умоляющий вид, [npc.she] скулит,"
													:" Пока [npc.she] снимает одежду с [npc.her], [npc.she] скулит,")
											+ " [npc.speech(Я думала, я тебе нравлюсь, [npc.pcName]... Но если ты этого хочешь...)]</p><p>Игнорируя слегка бунтарский протест [npc.her], вы приказываете [npc.name] вышагивать перед вами; приказ, который [npc.she] снова неохотно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+" Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, с облегченным вздохом, [npc.she] делает так, как ты велел.</p>");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(
											(isSlaveNaked()
													?"[npc.She] немедленно принимается исполнять ваш приказ, и вы видите отчетливую печаль в [npc.eyes] [npc.her], когда [npc.she] послушно отступает назад и спрашивает,"
													:"[npc.She] немедленно принимает ваш приказ, и вы видите отчетливую печаль в [npc.eyes] [npc.her], когда [npc.she] послушно сбрасывает [npc.her] одежду на пол. Пока [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает,")
											+ " [npc.speech(Тебе нравится, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, а затем приказываете [npc.name] покрутиться перед вами; приказ, который [npc.she] снова послушно исполняет. После этого вы заставляете [npc.herHim] "+legsSpreading+partInspection()
											+ " Довольный внешним видом [npc.her], вы говорите [npc.name] "+(isSlaveNaked()?"сделать шаг вперед перед вами,":"снова одеться,")+", и, без единого слова жалобы, [npc.she] снова делает именно то, что ты говоришь.</p>");
									break;
							}
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>It makes no difference that [npc.name]"
									+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что вы заставляете её раздеваться и выставлять [npc.herself] перед вами словно кусок мяса, оказывает огромное негативное влияние на [npc.her] Привязанность к вам, одновременно повышая [npc.her] Покорность!</i></p>");
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static String partInspection() {
		if(getSlave().hasPenis()) {
			if(getSlave().hasVagina()) {
				return ("[npc.penis+] и [npc.vagina+] для вашего осмотра.");
			} else {
				return ("[npc.penis+] для вашего осмотра.");
			}
			
		} else if(getSlave().hasVagina()) {
			return ("[npc.vagina+] для вашего осмотра.");
			
		} else {
			return ("бесполое возвышение для вашего осмотра.");
		}
	}
	
	
	public static final DialogueNode SLAVE_SPANKING = new DialogueNode("", "", true) { //TODO
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Шлепанье [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>Вам кажется, что [npc.name] стоит напомнить о [npc.her] месте, и поэтому, устроившись поудобнее, вы приказываете [npc.herHim] раздеться и наклониться к вам на колени.</p><p>[npc.She] немедленно спешит исполнить ваш приказ и послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает: [npc.speech(Вам это по душе, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, прежде чем снова приказать [npc.name] нагнуться [npc.herself] через ваше колено; приказ, который [npc.she] исполняет так же быстро, как только может [npc.she]. Когда [npc.she] ложится вам на колени, подставляя вам [npc.her] [npc.ass+], вы стремительно опускаете свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Роботизированный визг сопровождает резкий звук удара по силикону, что побуждает вас нанести еще один удар по [npc.her] [npc.ass].</p><p>Вы продолжаете это какое-то время; ваша кукла послушно замерла у вас на коленях, пока вы обрушиваете удар за ударом на [npc.her] уязвимую задницу. Хотя [npc.she] визжит и тяжело дышит в ответ, вы не можете избавиться от ощущения, что [npc.sheIs] делает это ради вашего удовольствия и что наказание вовсе не причиняет [npc.herHim] боли. В итоге вы завершаете порку...</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>Тебе кажется, что [npc.name] не помешает напоминание о том, кто здесь главный, поэтому, усаживаясь, ты приказываешь [npc.herHim] раздеться и нагнуться тебе на колени.</p><p>");
	
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
											"Из рта [npc.her] вырывается сердитое рычание, когда [npc.she] слышит, что ты велишь [npc.herHim] делать, но, понимая, что у [npc.she] нет выбора, неохотно начинает подчиняться. Пока [npc.she] медленно снимает [npc.her] одежду, [npc.she] огрызается: [npc.speech(Ты, сука, "+(Main.game.getPlayer().isFeminine()?"сучка":"ублюдок!)}")+"! Я тебя так чертовски ненавижу!)]</p><p>Игнорируя дерзкие замечания [npc.her], ты снова приказываешь [npc.name] нагнуться [npc.herself] на твоё колено; приказ, который [npc.she] исполняет так медленно, как только может [npc.she]. Когда [npc.she] ложится тебе на колени, подставляя [npc.her] [npc.ass+], ты победно хохочешь, прежде чем резко опустить свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Резкий звук удара плоти сопровождается визгом, что побуждает тебя нанести еще один удар по [npc.her] [npc.ass].</p><p>Ты продолжаешь это какое-то время; твоя рабыня извивается и визжит у тебя на коленях, пока ты обрушиваешь удар за ударом на уязвимую задницу [npc.her]. Однако в конце концов тебе кажется, что [npc.sheIs] усвоила [npc.her] урок, и ты приказываешь [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.She] издает смущенный возглас, когда [npc.she] слышит, что именно ты приказываешь [npc.herHim] сделать, но, пытаясь сдержать [npc.her] эмоции, неохотно начинает подчиняться. Пока [npc.she] медленно снимает [npc.her] одежду, [npc.she] вздыхает, [npc.speech(Это займет всего мгновение, <i>[npc.pcName]</i>.)]</p><p>Игнорируя слегка бунтарский тон [npc.her], ты снова приказываешь [npc.name] нагнуться через [npc.herself] твое колено; приказ, который [npc.she] выполняет так медленно, как только может [npc.she]. Когда [npc.she] ложится тебе на колени, подставляя [npc.her] [npc.ass+], ты издаешь победный смех, прежде чем стремительно опустить свою [pc.hand], чтобы шлепнуть по открытым щекам [npc.her]. Вслед за резким звуком удара плоти раздается визг, что побуждает тебя нанести еще один удар по [npc.her] [npc.ass].</p><p>Ты продолжаешь это какое-то время; твоя рабыня ерзает и визжит у тебя на коленях, пока ты обрушиваешь удар за ударом на [npc.her] уязвимую задницу. Однако в конце концов тебе кажется, что [npc.sheIs] усвоила [npc.her] урок, и ты приказываешь [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.She] немедленно принимается исполнять ваш приказ, но вы видите отчетливый взгляд ненависти в [npc.her] [npc.eyes], пока [npc.she] послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает: [npc.speech(Это доставляет тебе удовольствие, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, прежде чем снова приказать [npc.name] нагнуться [npc.herself] на ваше колено; приказ, который [npc.she] исполняет так медленно, как только может [npc.she]. Когда [npc.she] ложится вам на колени, подставляя [npc.her] [npc.ass+], вы издаете победный смех, а затем стремительно опускаете свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Вслед за резким звуком удара плоти раздается визг, что побуждает вас нанести еще один удар по [npc.her] [npc.ass].</p><p>Вы продолжаете это некоторое время; ваша рабыня извивается и визжит у вас на коленях, пока вы обрушиваете удар за ударом на [npc.her] уязвимую задницу. Однако в конце концов вам кажется, что [npc.sheIs] усвоила [npc.her] урок, и вы приказываете [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.She] lets out a flustered cry as [npc.she] hears your order, but, realising that [npc.she] really doesn't have any choice in the matter, begrudgingly moves to obey. As [npc.she] takes [npc.her] clothes off, [npc.she] whines, [npc.speech(Do I really have to? It's kind of degrading being forced to do this...)]</p><p>Ignoring [npc.her] words, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can. As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks. A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass].</p><p>You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside. Eventually, however, you feel as though [npc.sheIs] been taught [npc.her] lesson, and you order [npc.herHim] to stand up and put [npc.her] clothes on once more.</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.She] lets out a flustered cry as [npc.she] hears your order, but, trying to keep [npc.her] emotions under control, begrudgingly moves to obey. As [npc.she] takes [npc.her] clothes off, [npc.she] whines, [npc.speech(Just give me a moment, [npc.pcName].)]</p><p>After [npc.sheIs] stripped naked, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can. As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks. A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass].</p><p>You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside. Eventually, however, you feel as though [npc.sheIs] been taught [npc.her] lesson, and you order [npc.herHim] to stand up and put [npc.her] clothes on once more.</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.She] немедленно принимается исполнять ваш приказ, но вы замечаете явный след тревоги в глазах [npc.her] [npc.eyes], когда [npc.she] послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает: [npc.speech(Вам это угодно, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, прежде чем снова приказать [npc.name] нагнуться [npc.herself] на ваше колено; приказ, который [npc.she] исполняет так быстро, как только [npc.she] может. Когда [npc.she] ложится вам на колени, подставляя [npc.her] [npc.ass+], вы издаете победный смех, а затем стремительно опускаете свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Вслед за резким звуком удара плоти раздается визг, что побуждает вас нанести еще один удар по [npc.her] [npc.ass].</p><p>Вы продолжаете это еще какое-то время; ваша рабыня извивается и визжит у вас на коленях, пока вы обрушиваете удар за ударом на [npc.her] уязвимую задницу. Однако в конце концов вам кажется, что [npc.sheIs] усвоила [npc.her] урок, и вы приказываете [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.She] издает печальный тихий крик, когда [npc.she] слышит твой приказ, но, осознав, что у [npc.she] действительно нет выбора, неохотно начинает подчиняться. Пока [npc.she] снимает с [npc.her] одежду, [npc.she] скулит: [npc.speech(Знаешь, это как-то унизительно — когда тебя заставляют... А я думала, я тебе нравлюсь...)]</p><p>Игнорируя слова [npc.her], ты снова приказываешь [npc.name] нагнуться через [npc.herself] на твое колено; приказ, который [npc.she] исполняет так медленно, как только может [npc.she]. Когда [npc.she] ложится тебе на колени, подставляя [npc.her] [npc.ass+], ты издаешь победный смех, прежде чем стремительно опустить свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Вслед за резким звуком удара плоти раздается визг, что побуждает тебя нанести еще один удар по [npc.her] [npc.ass].</p><p>Ты продолжаешь это какое-то время; твоя рабыня ерзает и визжит у тебя на коленях, пока ты обрушиваешь удар за ударом на уязвимую задницу [npc.her]. Однако в конце концов ты чувствуешь, что [npc.sheIs] усвоила [npc.her] урок, и приказываешь [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(
										"[npc.She] издает тихий печальный крик, когда [npc.she] слышит твой приказ, но, стараясь сдерживать [npc.her] эмоции, медленно начинает подчиняться. Пока [npc.she] снимает [npc.her] одежду, [npc.she] скулит, [npc.speech(А я думала, я тебе нравлюсь, [npc.pcName]... Но если ты этого хочешь...)]</p><p>Игнорируя слова [npc.her], ты снова приказываешь [npc.name] нагнуться через [npc.herself] твои колени; приказ, который [npc.she] исполняет так медленно, как только может [npc.she]. Когда [npc.she] ложится тебе на колени, подставляя [npc.her] [npc.ass+], ты победно хохочешь, прежде чем стремительно опустить свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Вслед за резким звуком удара плоти раздается визг, который побуждает тебя нанести еще один удар по [npc.her] [npc.ass].</p><p>Ты продолжаешь это какое-то время; твоя рабыня ерзает и визжит у тебя на коленях, пока ты обрушиваешь удар за ударом на уязвимую задницу [npc.her]. Однако в конце концов тебе кажется, что [npc.sheIs] усвоила [npc.her] урок, и ты приказываешь [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(
										"[npc.She] немедленно принимается исполнять ваш приказ, но вы замечаете отчетливую печаль в глазах [npc.her] [npc.eyes], когда [npc.she] послушно сбрасывает [npc.her] одежду на пол. Когда [npc.she] снимает последние предметы [npc.her] одежды, [npc.she] спрашивает: [npc.speech(Вам это угодно, [npc.pcName]?)]</p><p>Вы отвечаете утвердительно, прежде чем снова приказать [npc.name] нагнуться [npc.herself] на ваше колено; приказ, который [npc.she] исполняет так медленно, как только может [npc.she]. Когда [npc.she] ложится вам на колени, подставляя [npc.her] [npc.ass+], вы издаете победный смех, а затем стремительно опускаете свою [pc.hand], чтобы шлепнуть по обнаженным щекам [npc.her]. Резкий звук удара плоти сопровождается визгом, что побуждает вас нанести еще один удар по [npc.her] [npc.ass].</p><p>Вы продолжаете это какое-то время; ваша рабыня извивается и визжит у вас на коленях, пока вы обрушиваете удар за ударом на уязвимую задницу [npc.her]. Однако в конце концов вам кажется, что [npc.sheIs] усвоила [npc.her] урок, и вы приказываете [npc.herHim] встать и снова надеть [npc.her] одежду.</p>");
								break;
						}
						break;
				}
	
				if(getSlave().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
					UtilText.nodeContentSB.append(
							"<p><i>Так как [npc.name] — мазохистка, порка [npc.herHim] на самом деле заставила [npc.herHim] любить вас еще сильнее, а также повысила [npc.her] покорность!</i></p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>It makes no difference that [npc.name]"
									+ " <span style='"+getSlave().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+getSlave().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span> то, что </span> вынуждена представлять [npc.herself] для дисциплинарного шлепка, оказывает огромное негативное влияние на [npc.her] привязанность к тебе, одновременно повышая [npc.her] покорность </span>!</i></p>");
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SLAVE_MOLEST = new DialogueNode("", "", true) {
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel(){
			return UtilText.parse(getSlave(), "Домогаться [npc.Name]");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDoll()) {
				UtilText.nodeContentSB.append("<p>Не в силах устоять перед очарованием [npc.namePos] [npc.feminine] тела, ты обходишь [npc.her] со спины, после чего делаешь шаг вперед и обвиваешь [npc.herHim] своими [pc.arms]. Твоя кукла послушно замирает, пока ты хватаешь [npc.her] [npc.breasts+], и издает [npc.a_moan+], когда твои руки скользят между [npc.her] [npc.legs].</p><p>[npc.speech(Да, [pc.name], пожалуйста, используй меня как хочешь,)] [npc.she] тяжело дышит.</p><p>Делая именно это, ты продолжаешь ощупывать и ласкать [npc.name], останавливаясь лишь тогда, когда вдоволь на楽しみешься и исследуешь каждый дюйм силиконового тела [npc.her]. [pc.Stepping] отступив, ты гадаешь, что делать дальше...</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>Не в силах устоять перед очарованием [npc.namePos] [npc.feminine] тела, ты обходишь [npc.her] со спины, после чего делаешь шаг вперед и обвиваешь [npc.herHim] своими [pc.arms].");
	
				String firstCry = "ужаснутый крик";
				String firstReaction = "мгновенно начинает извиваться, пытаясь отстраниться от твоих прикосновений";
				String firstSpeech = "Отвали! Оставь меня в покое!";
				String firstPCReaction = "[npc.Her] слова ничуть не сдерживают твоего натиска на неохотное тело [npc.her]";
				
				String secondReaction = "[npc.She] продолжает кричать и сопротивляться";
				String thirdReaction = ", несмотря на протесты [npc.her],";
				String secondSpeech = "[npc.speech(Прекрати! Б-блять, уже отвали!)] [npc.she] кричит, но [npc.her] слова падают в пустоту, пока ты продолжаешь развлекаться.";
				
				String finalDescription = "В конце концов, тебе кажется, что ты уже достаточно нагулялся, и, отпустив [npc.name], чтобы [npc.herHim] могла метнуться на другой конец комнаты, ты с ухмылкой осматриваешь [npc.herHim] с ног до головы. [npc.She] продолжает изрыгать проклятия и требовать, чтобы ты оставил [npc.herHim] в покое, и ты гадаешь, стоит ли поступить так, как просит твоя рабыня, или сделать с [npc.herHim] что-нибудь еще...";
				
				switch(AffectionLevelBasic.getAffectionLevelFromValue(getSlave().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								break;
							case NEUTRAL:
								firstCry = "неловкий вскрик";
								firstReaction = "начинает извиваться, пытаясь увести тело [npc.her] от твоих прикосновений";
								firstSpeech = "М-можешь уже прекратить?!";
								firstPCReaction = "[npc.Her] слова ничуть не сдерживают твоего натиска на неохотное тело [npc.her]";
								
								secondReaction = "[npc.She] продолжает беспокойно ерзать";
								thirdReaction = ", несмотря на неохоту [npc.her],";
								secondSpeech = "[npc.speech(П-пожалуйста! П-прекрати сейчас же!)] — вскрикивает [npc.she], но [npc.her] слова падают в пустоту, пока ты продолжаешь развлекаться.";
								
								finalDescription = "В конце концов, тебе кажется, что ты уже достаточно нагулялся, и, отпустив [npc.name], чтобы [npc.herHim] могла метнуться на другой конец комнаты, ты с ухмылкой осматриваешь [npc.herHim] с ног до головы. [npc.She] избегает твоего взгляда, пока [npc.she] просит тебя оставить [npc.herHim] в покое, и ты гадаешь, стоит ли поступить так, как просит твоя рабыня, или сделать с [npc.herHim] что-нибудь еще...";
								break;
							case OBEDIENT:
								firstCry = "шокированный вскрик";
								firstReaction = "покорно замирает, подставляя [npc.her] тело под твои ласки";
								firstSpeech = "Я в твоем распоряжении, [npc.pcName].";
								firstPCReaction = "Холодные слова [npc.Her] ничуть не сдерживают твое натиск на [npc.her] тело";
								
								secondReaction = "[npc.She] продолжает покорно позволять использовать [npc.herself]";
								thirdReaction = ", несмотря на холодное отношение [npc.her],";
								secondSpeech = "[npc.speech(Так вам нравится, [npc.pcName]?)] — послушно спрашивает [npc.she], не проявляя никаких эмоций, пока ты продолжаешь развлекаться.";
								
								finalDescription = "В конце концов, ты чувствуешь, что насытился, и, отпустив [npc.name], чтобы [npc.herHim] могла отступить на шаг, ты с ухмылкой осматриваешь [npc.herHim] с ног до головы. [npc.She] покорно спрашивает, нужно ли от [npc.herHim] что-то еще, и ты невольно задумываешься, не стоит ли тебе сделать с [npc.herHim] что-нибудь еще...";
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								firstCry = "испуганный вскрик";
								firstReaction = "начинает извиваться, пытаясь увести тело [npc.her] от твоих прикосновений";
								firstSpeech = "Т-тебе обязательно это делать?";
								firstPCReaction = "Тревожный тон [npc.Her] ничуть не сдерживает твой натиск на [npc.her] тело";
								
								secondReaction = "[npc.She] продолжает слегка вырываться";
								thirdReaction = ", несмотря на совсем не восторженное отношение [npc.her],";
								secondSpeech = "[npc.speech(Сколько это еще будет продолжаться, [npc.pcName]?)] спрашивает [npc.she], но непослушный вопрос [npc.her] остается без ответа, пока вы продолжаете развлекаться.";
								
								finalDescription = "В конце концов, вы чувствуете, что насытились, и, отпустив [npc.name], чтобы [npc.herHim] могла сделать несколько шагов назад, вы с ухмылкой осматриваете [npc.herHim] с ног до головы. [npc.She] облегченно вздыхает, когда [npc.she] просит вас оставить [npc.herHim] в покое, и вы гадаете, стоит ли поступить так, как просит ваша рабыня, или заняться с [npc.herHim] чем-нибудь еще...";
								break;
							case NEUTRAL:
								firstCry = "полуподавленный крик";
								firstReaction = "начинает извиваться, пытаясь увести тело [npc.her] от твоих прикосновений";
								firstSpeech = "[npc.pcName], неужели тебе правда обязательно это делать?";
								firstPCReaction = "Тревожный тон [npc.Her] ничуть не сдерживает твой натиск на [npc.her] тело";
								
								secondReaction = "[npc.She] продолжает слегка вырываться";
								thirdReaction = ", несмотря на совсем не восторженное отношение [npc.her],";
								secondSpeech = "[npc.speech(Ты уже почти закончил, [npc.pcName]?)] спрашивает [npc.she], но непослушный вопрос [npc.her] остается без ответа, пока вы продолжаете развлекаться.";
								
								finalDescription = "В конце концов, вы чувствуете, что насытились, и, отпустив [npc.name], чтобы [npc.herHim] могла сделать несколько шагов назад, вы с ухмылкой осматриваете [npc.herHim] с ног до головы. [npc.She] издает легкий вздох облегчения, когда [npc.she] избегает вашего взгляда, и вы гадаете, стоит ли заняться с [npc.herHim] чем-нибудь еще...";
								break;
							case OBEDIENT:
								firstCry = "шокированный вскрик";
								firstReaction = "покорно замирает, подставляя [npc.her] тело под твои ласки";
								firstSpeech = "Я в твоем распоряжении, [npc.pcName].";
								firstPCReaction = "Холодные слова [npc.Her] ничуть не сдерживают твое натиск на [npc.her] тело";
								
								secondReaction = "[npc.She] продолжает покорно позволять использовать [npc.herself]";
								thirdReaction = ", несмотря на холодное отношение [npc.her],";
								secondSpeech = "[npc.speech(Так вам нравится, [npc.pcName]?)] — послушно спрашивает [npc.she], не проявляя никаких эмоций, пока ты продолжаешь развлекаться.";
								
								finalDescription = "В конце концов, ты чувствуешь, что насытился, и, отпустив [npc.name], чтобы [npc.herHim] могла отступить на шаг, ты с ухмылкой осматриваешь [npc.herHim] с ног до головы. [npc.She] покорно спрашивает, нужно ли от [npc.herHim] что-то еще, и ты невольно задумываешься, не стоит ли тебе сделать с [npc.herHim] что-нибудь еще...";
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(getSlave().getObedienceValue())) {
							case DISOBEDIENT:
								firstCry = "испуганный вскрик";
								firstReaction = "начинает извиваться, пытаясь увести тело [npc.her] от твоих прикосновений";
								firstSpeech = "[npc.PcName]! Я думала, я тебе нравлюсь?! П-почему ты так поступаешь?!";
								firstPCReaction = "расстроенный тон [npc.Her] ничуть не сдерживает твоего натиска на тело [npc.her]";
								
								secondReaction = "[npc.She] продолжает слегка вырываться";
								thirdReaction = ", несмотря на совсем не восторженное отношение [npc.her],";
								secondSpeech = "[npc.speech([npc.PcName], пожалуйста! Я... я всего лишь хотела обнимашек...)] [npc.she] вздыхает, [npc.her] дерзкое замечание остается без ответа, пока вы продолжаете развлекаться.";
								
								finalDescription = "В конце концов, ты чувствуешь, что насытился, и, отпустив [npc.name], чтобы дать [npc.herHim] сделать несколько шагов назад, ты с ухмылкой оглядываешь [npc.herHim] с ног до головы. [npc.She] облегченно вздыхает, а [npc.she] улыбается тебе снизу вверх, и ты задумываешься, не стоит ли тебе заняться с [npc.herHim] чем-нибудь еще...";
								break;
							case NEUTRAL:
								firstCry = "тихий плач";
								firstReaction = "замирает, подставляя [npc.her] тело";
								firstSpeech = "[npc.PcName], неужели тебе обязательно это делать?";
								firstPCReaction = "Тревожный тон [npc.Her] ничуть не сдерживает твой натиск на [npc.her] тело";
								
								secondReaction = "[npc.She] продолжает стоять неподвижно";
								thirdReaction = ", несмотря на слегка непокорное поведение [npc.her],";
								secondSpeech = "[npc.speech(Тебе нравится, [npc.pcName]?)] спрашивает [npc.she], и [npc.her] вопросительно замирает, не получив ответа, пока ты продолжаешь развлекаться.";
								
								finalDescription = "В конце концов, вы чувствуете, что насытились, и, отпустив [npc.name], чтобы [npc.herHim] могла сделать несколько шагов назад, вы с ухмылкой осматриваете [npc.herHim] с ног до головы. [npc.She] издает легкий вздох облегчения, когда [npc.she] избегает вашего взгляда, и вы гадаете, стоит ли заняться с [npc.herHim] чем-нибудь еще...";
								break;
							case OBEDIENT:
								firstCry = "легкий вздох";
								firstReaction = "покорно замирает, подставляя [npc.her] тело под твои ласки";
								firstSpeech = "Я в твоем распоряжении, [npc.pcName].";
								firstPCReaction = "Холодные слова [npc.Her] ничуть не сдерживают твое натиск на [npc.her] тело";
								
								secondReaction = "[npc.She] продолжает покорно позволять использовать [npc.herself]";
								thirdReaction = ", несмотря на холодное отношение [npc.her],";
								secondSpeech = "[npc.speech(Так вам нравится, [npc.pcName]?)] — послушно спрашивает [npc.she], не проявляя никаких эмоций, пока ты продолжаешь развлекаться.";
								
								finalDescription = "В конце концов, ты чувствуешь, что насытился, и, отпустив [npc.name], чтобы [npc.herHim] могла отступить на шаг, ты с ухмылкой осматриваешь [npc.herHim] с ног до головы. [npc.She] покорно спрашивает, нужно ли от [npc.herHim] что-то еще, и ты невольно задумываешься, не стоит ли тебе сделать с [npc.herHim] что-нибудь еще...";
								break;
						}
						break;
				}
				
				
				
				if(getSlave().hasBreasts()) {
					UtilText.nodeContentSB.append(
								" Твои [pc.hands] тянутся вверх, чтобы обхватить и сжать [npc.her] [npc.breasts+], вырывая "+firstCry+" из рта [npc.namePos], пока [npc.she] "+firstReaction+","
								+ " [npc.speech("+firstSpeech+")]"
							+ "</p>"
							+ "<p>"
								+ firstPCReaction+", и, пока одна [pc.hand] все еще сжимает [npc.her] [npc.breasts+], ты просовываешь другую #IF(npc.hasLegs())между [npc.her] [npc.legs]#ELSEк паху [npc.her]#ENDIF.");
				} else {
					UtilText.nodeContentSB.append(
							" Твои [pc.hands] тянутся вверх, чтобы погладить [npc.her] грудь, вырывая "+firstCry+" из рта [npc.namePos], пока [npc.she] "+firstReaction+","
							+ " [npc.speech("+firstSpeech+")]"
						+ "</p>"
						+ "<p>"
							+ firstPCReaction+", и, пока одна [pc.hand] все еще прижата к [npc.her] груди, ты просовываешь другую #IF(npc.hasLegs())между [npc.her] [npc.legs]#ELSEк паху [npc.her]#ENDIF.");
				}
	
				if(getSlave().hasVagina() && getSlave().hasPenis()) {
					UtilText.nodeContentSB.append(
								" "+secondReaction+" пока ты ощупываешь, гладишь и ласкаешь [npc.her] [npc.penis+], и ты не можешь удержаться от того, чтобы не [pc.moan] в [npc.ear] своей рабыни, когда"+thirdReaction+" ты чувствуешь, как [npc.her] [npc.cock+] твердеет под твоими пальцами. Спустив свои [pc.hand] еще ниже, ты переключаешь внимание на [npc.her] [npc.pussy+], снова ухмыляясь от осознания того, что твоя стимуляция [npc.her] [npc.clit+] и [npc.labia+] уже сделала [npc.her] влажной.</p>");
					
				} else if(getSlave().hasVagina()) {
					UtilText.nodeContentSB.append(
							" "+secondReaction+" пока ты ощупываешь, гладишь и исследуешь [npc.her] [npc.clit+] и [npc.labia+], и ты не можешь удержаться от того, чтобы не [pc.moan] в [npc.ear] своей рабыни, когда"+thirdReaction+" ты чувствуешь, что [npc.her] [npc.pussy+] уже намокла от твоих прикосновений.</p>");
					
				} else if(getSlave().hasPenis()) {
					UtilText.nodeContentSB.append(
							" "+secondReaction+" пока ты ощупываешь, гладишь и ласкаешь [npc.her] [npc.penis+], и ты не можешь удержаться от того, чтобы не [pc.moan] в [npc.ear] своей рабыни, когда"+thirdReaction+" ты чувствуешь, как [npc.her] [npc.cock+] твердеет под твоими пальцами.</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							" "+secondReaction+" пока ты ощупываешь, ласкаешь и исследуешь бесполое лоно [npc.her], ты не можешь удержаться от того, чтобы не [pc.moan] в [npc.ear] своей рабыни, когда"+thirdReaction+" ты чувствуешь, как [npc.herHim] [npc.her] [npc.hips] выпирает под твоими прикосновениями.</p>");
					
				}
				UtilText.nodeContentSB.append(
						"<p>"
							+ secondSpeech
						+ "</p>"
						+ "<p>"
							+ finalDescription
						+ "</p>");
				
				
				UtilText.nodeContentSB.append(
						"<p><i>Домогательства к [npc.name] помогли укрепить в уме [npc.her] мысль о том, что [npc.she] принадлежит тебе, повышая [npc.her] покорность!<p><i>");
				if(getSlave().isAttractedTo(Main.game.getPlayer())) {
					if(getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) || getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						UtilText.nodeContentSB.append(
									" Поскольку [npc.sheIs] одновременно и влечена к тебе, и"
									+ (getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE)
											?(getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)
													?" является и покорной, и возбуждается от принудительных встреч,"
													:" является покорной,")
											:" возбуждается от принудительных встреч,")
									+ " твое доминантное, недобровольное использование тела [npc.namePos] привело к тому, что [npc.herHim] стала любить тебя еще сильнее!</i></p>");
					} else {
						UtilText.nodeContentSB.append(
								" Поскольку [npc.sheIs] испытывает к тебе влечение, твое доминантное, принудительное использование тела [npc.namePos] привело к тому, что [npc.herHim] уровень привязанности к тебе не изменился!</i></p>");
					}
				} else {
					if(getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE) || getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						UtilText.nodeContentSB.append(
								" Несмотря на то, что [npc.sheIs] не испытывает к тебе влечения,"
								+ (getSlave().hasFetish(Fetish.FETISH_SUBMISSIVE)
										?(getSlave().hasFetish(Fetish.FETISH_NON_CON_SUB)
												?" будучи одновременно покорной и возбуждаясь от принудительных встреч,"
												:" будучи покорной,")
										:" возбуждаясь от принудительных встреч,")
								+ " твое доминантное, принудительное использование тела [npc.namePos] привело к тому, что [npc.herHim] уровень привязанности к тебе не изменился!</i></p>");
					} else {
						UtilText.nodeContentSB.append(
								" Поскольку [npc.sheIs] не испытывает к тебе влечения, твое доминантное, принудительное использование тела [npc.namePos] привело к тому, что [npc.herHim] сильно потеряла привязанность к тебе!</i></p>");
					}
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(getSlave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь на楽しみлся, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}
		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).size()==4) {
				List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_GANGBANG_3", sexParticipants);
				
			} else if(Main.sex.getAllParticipants(false).size()==5) {
				List<GameCharacter> sexParticipants = new ArrayList<GameCharacter>(charactersPresent);
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_GANGBANG_4", sexParticipants);
				
			} else {
				if(Main.sex.getNumberOfOrgasms(getSlave()) >= getSlave().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", getSlave());
				} else {
					return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", getSlave());
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Реши, что делать дальше.", SLAVE_START_NO_CONTENT) {
					@Override
					public void effects() {
						SlaveDialogue.initDialogue(getSlave(), false); // Need to re-init this dialogue as it gets cleared in Game turnUpdate during this scene.
						Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_CONTINUE", getSlave()));
						if(!getSlave().isAsleep() && !isDollStatue()) {
							Main.game.appendToTextEndStringBuilder(getFooterText());
						}
//						Main.game.appendToTextEndStringBuilder(getSlaveStartCoreContent());
					}
				};
			}
			return null;
		}
	};
	
}
