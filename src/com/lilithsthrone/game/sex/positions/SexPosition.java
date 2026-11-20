package com.lilithsthrone.game.sex.positions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.dominion.MasturbationPanties;
import com.lilithsthrone.game.sex.sexActions.universal.ChairSex;
import com.lilithsthrone.game.sex.sexActions.universal.Cowgirl;
import com.lilithsthrone.game.sex.sexActions.universal.DoggyStyleAndProneBone;
import com.lilithsthrone.game.sex.sexActions.universal.FaceSitting;
import com.lilithsthrone.game.sex.sexActions.universal.GloryHole;
import com.lilithsthrone.game.sex.sexActions.universal.KneelingOral;
import com.lilithsthrone.game.sex.sexActions.universal.Masturbation;
import com.lilithsthrone.game.sex.sexActions.universal.MatingPress;
import com.lilithsthrone.game.sex.sexActions.universal.MilkingStall;
import com.lilithsthrone.game.sex.sexActions.universal.Missionary;
import com.lilithsthrone.game.sex.sexActions.universal.MissionaryDesk;
import com.lilithsthrone.game.sex.sexActions.universal.SixtyNine;
import com.lilithsthrone.game.sex.sexActions.universal.StocksSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * AbstractSexPositions for taurs, including taur-biped interactions.
 * 
 * @since 0.3.1
 * @version 0.3.4
 * @author Innoxia
 */
public class SexPosition {
	
	public static final AbstractSexPosition MASTURBATION = new AbstractSexPosition("",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(
					MasturbationPanties.class,
					Masturbation.class)) {
		@Override
		public Set<SexSlot> getAllAvailableSexPositions() {
			return Util.newHashSetOfValues(SexSlotMasturbation.KNEELING, SexSlotMasturbation.KNEELING_PANTIES, SexSlotMasturbation.SITTING, SexSlotMasturbation.STANDING);
		}
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(characterToTakeSlot.isTaur() && slot==SexSlotMasturbation.SITTING) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Из-за пропорций звериного низа [npc.her], [npc.nameIsFull] не может одновременно сидеть и мастурбировать."));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING), "[npc.NameIsFull] стоит на коленях на полу, готовая к мастурбации.");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.STANDING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.STANDING), "[npc.NameIsFull] стоит в полный рост, готовая к мастурбации.");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.SITTING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.SITTING), "[npc.NameIsFull] сидит, готовая к мастурбации.");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES), "[npc.NameIsFull] стоит на коленях на полу, готовая к мастурбации с помощью трусиков Lilaya.");
			}
			
			return UtilText.parse("Вы готовы к мастурбации.");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> masturbationSlots = Util.newArrayListOfValues(SexSlotMasturbation.KNEELING, SexSlotMasturbation.STANDING, SexSlotMasturbation.SITTING);
			
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES)!=null) {
				interactions.add(StandardSexActionInteractions.masturbation.getSexActionInteractions(SexSlotMasturbation.KNEELING_PANTIES, SexSlotMasturbation.KNEELING_PANTIES));
			} else {
				for(SexSlot slot : masturbationSlots) {
					interactions.add(StandardSexActionInteractions.masturbation.getSexActionInteractions(slot, slot));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return true;
		}
	};
	
	
	public static final AbstractSexPosition STANDING = new AbstractSexPosition("Стоя",
			8,
			true,
			SexActionPresets.positioningActionsNew, Util.newArrayListOfValues(KneelingOral.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.STANDING_SUBMISSIVE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.STANDING_SUBMISSIVE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.STANDING_SUBMISSIVE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_FOUR, SexSlotStanding.STANDING_SUBMISSIVE_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			// This position requires at least one character to be standing.
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotStanding.STANDING_DOMINANT
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_TWO
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_THREE
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_FOUR) {
					suitablePosition = true;
					break;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "Для этой позы хотя бы один персонаж должен находиться в положении стоя.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			List<String> subNames = new ArrayList<>();
			List<String> subStandingNames = new ArrayList<>();
			List<String> subStandingBehindNames = new ArrayList<>();
			List<String> subKneelingNames = new ArrayList<>();
			List<String> subKneelingBehindNames = new ArrayList<>();
			List<String> subSizeDiffKneelingNames = new ArrayList<>();
			List<String> subSizeDiffBehindNames = new ArrayList<>();
			List<String> domNames = new ArrayList<>();
			
//			List<String> sizeDifferenceAdditions = new ArrayList<>();
			Map<GameCharacter, SexSlot> doms = new HashMap<>();
			Map<GameCharacter, SexSlot> domTaurs = new HashMap<>();
			Map<GameCharacter, SexSlot> subs = new HashMap<>();
			List<GameCharacter> subsStanding = new ArrayList<>();
			List<GameCharacter> subsStandingBehind = new ArrayList<>();
			
			boolean playerInDoms = false;
			boolean playerInSubs = false;
			
			GameCharacter mainDom = null;
			GameCharacter mainSub = null;
			GameCharacter mainStandingSub = null;
			GameCharacter mainStandingBehindSub = null;
			GameCharacter mainKneelingSub = null;
			GameCharacter mainBehindSub = null;
			GameCharacter mainKneelingSizeDiffSub = null;
			GameCharacter mainBehindSizeDiffSub = null;
			
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotStanding.STANDING_DOMINANT
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_TWO
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_THREE
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_FOUR) {
						if(mainDom==null) {
							mainDom=e.getKey();
						}
						if(!e.getKey().isTaur()) {
							doms.put(e.getKey(), e.getValue());
						} else {
							domTaurs.put(e.getKey(), e.getValue());
						}
						if(e.getKey().isPlayer()) {
							playerInDoms = true;
						}
						
					} else {
						if(e.getKey().isPlayer()) {
							playerInSubs = true;
						}
						subs.put(e.getKey(), e.getValue());
					}
				}
			}
			
			for(Entry<GameCharacter, SexSlot> sub : subs.entrySet()) {
				if(mainSub==null) {
					mainSub = sub.getKey();
				}
				if(sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_TWO
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_THREE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_FOUR) {
					if(sub.getKey().isPlayer()) {
						subStandingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
					} else {
						subStandingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
					}
					if(mainStandingSub==null) {
						mainStandingSub=sub.getKey();
					}
					subsStanding.add(sub.getKey());
					
				} else if(sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR) {
					if(sub.getKey().isPlayer()) {
						subStandingBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
					} else {
						subStandingBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
					}
					if(mainStandingBehindSub==null) {
						mainStandingBehindSub=sub.getKey();
					}
					subsStandingBehind.add(sub.getKey());
					
				} else if(sub.getValue()==SexSlotStanding.PERFORMING_ORAL
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_TWO
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_THREE
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_FOUR) {
					if(sub.getKey().isSizeDifferenceShorterThan(mainDom)) {
						if(sub.getKey().isPlayer()) {
							subSizeDiffKneelingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subSizeDiffKneelingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainKneelingSizeDiffSub==null) {
							mainKneelingSizeDiffSub=sub.getKey();
						}
					} else {
						if(sub.getKey().isPlayer()) {
							subKneelingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subKneelingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainKneelingSub==null) {
							mainKneelingSub=sub.getKey();
						}
					}
					
				} else {
					if(sub.getKey().isSizeDifferenceShorterThan(mainDom)) {
						if(sub.getKey().isPlayer()) {
							subSizeDiffBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subSizeDiffBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainBehindSizeDiffSub==null) {
							mainBehindSizeDiffSub=sub.getKey();
						}
					} else {
						if(sub.getKey().isPlayer()) {
							subKneelingBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subKneelingBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainBehindSub==null) {
							mainBehindSub=sub.getKey();
						}
					}
				}
				subNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
			}
			
			for(GameCharacter c : doms.keySet()) {
				if(c.isPlayer()) {
					domNames.add(0, UtilText.parse(c, "[npc.name]"));
				} else {
					domNames.add(UtilText.parse(c, "[npc.name]"));
				}
			}
			for(GameCharacter c : domTaurs.keySet()) {
				if(c.isPlayer()) {
					domNames.add(0, UtilText.parse(c, "[npc.name]"));
				} else {
					domNames.add(UtilText.parse(c, "[npc.name]"));
				}
			}
			
			int totalDoms = doms.size()+domTaurs.size();
			if(totalDoms>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(domNames, false))+" стоят бок о бок перед ");
			} else {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(domNames, false))
						+UtilText.parse(mainDom, " [npc.is]")+" стоят перед ");
			}

			sb.append(Util.stringsToStringList(subNames, false)+", готовые повеселиться с "
					+(subNames.size()>1
							?(playerInSubs?(playerInSubs?""+Util.intToString(subs.size())+" из вас":"их"):"их")
							:UtilText.parse(subs.keySet().iterator().next(), " [npc.herHim]"))+". ");
			
			// Standing:
			if(subStandingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingNames, false))
						+" также стоят рядом друг с другом перед "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?""+Util.intToString(totalDoms)+" из вас":"их"))+". ");
				
			} else if(subStandingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingNames, false))
						+UtilText.parse(mainStandingSub, " [npc.is]")+" стоит перед "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?""+Util.intToString(totalDoms)+" из вас":"их"))+". ");
			}

			// Kneeling:
			if(subKneelingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingNames, false))
						+" стоят на коленях рядом друг с другом");
				if(domTaurs.isEmpty()) {
					sb.append(" перед "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" вы":""+Util.intToString(totalDoms)+" ними,"))+"готовые заняться оральным сексом." );
				} else if(doms.isEmpty()) {
					sb.append(", и они готовы подползти под "
								+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] звериное тело"):(playerInDoms?"одно из ваших звериных тел":"одно из их звериных тел"))+" чтобы заняться оральным сексом. ");
				} else {
					sb.append(", и они готовы подползти вперед и начать оральный секс. ");
				}
				
			} else if(subKneelingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingNames, false))
						+UtilText.parse(mainKneelingSub, " [npc.is]")+" опустившись на колени");
				if(domTaurs.isEmpty()) {
					sb.append(" перед "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" вы":""+Util.intToString(totalDoms)+" ними,"))+"готовые заняться оральным сексом. ");
				} else if(doms.isEmpty()) {
					sb.append(" и "+UtilText.parse(mainKneelingSub, " [npc.is]")+" готов(а) подползти вперед под "
								+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] звериное тело"):(playerInDoms?"одно из ваших звериных тел":"одно из их звериных тел"))+" чтобы заняться оральным сексом. ");
				} else {
					sb.append(" и "+UtilText.parse(mainKneelingSub, " [npc.is]")+" готов(а) подползти вперед и начать отыгрывать оральный секс. ");
				}
			}
			// Kneeling size difference:
			if(subSizeDiffKneelingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffKneelingNames, false))
						+" готовы заняться оральным сексом, но из-за "+(playerInSubs?"твоей":"их")+" невысокого роста, "+(playerInSubs?"вы":"они")+" им не нужно вставать на колени, вместо этого они стоят перед "
						+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" вы":""+Util.intToString(totalDoms)+" ними,"))+".");
				
			} else if(subSizeDiffKneelingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffKneelingNames, false))
						+UtilText.parse(mainKneelingSizeDiffSub, " [npc.is] готовы заняться оральным сексом, но из-за [npc.her] невысокого роста, [npc.she] [npc.does] не нужно вставать на колени, и [npc.is] вместо этого стоят перед ")
						+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" вы":""+Util.intToString(totalDoms)+" ними,"))+".");
			}

			// Standing behind:
			if(subStandingBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingBehindNames, false))
						+" стоят сзади, плечом к плечу.");
				
			} else if(subStandingBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingBehindNames, false))
						+UtilText.parse(mainStandingBehindSub, " [npc.is]")+" стоит сзади "+(totalDoms==1?UtilText.parse(mainDom, " [npc.name]"):(playerInDoms?" вы":" их"))+".");
			}
			
			// Kneeling behind:
			if(subKneelingBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingBehindNames, false))
						+" опустились на колени рядом друг с другом");
				if(domTaurs.isEmpty()) {
					sb.append(" сзади "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" вы":" их"))+", готовый заняться анилингусом.");
				} else if(doms.isEmpty()) {
					sb.append(" сзади "+
							(totalDoms==1
								?UtilText.parse(mainDom, " [npc.herHim], и готовы заняться оральным сексом с [npc.her] звериной задницей.")
								:(playerInDoms
									?" тебя, и готовы заняться оральным сексом с одной из твоих звериных задниц."
									:" их, и готовы заняться оральным сексом с одной из их звериных задниц.")));
				} else {
					sb.append(", и готовы заняться оральным сексом с "+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] задницей."):(playerInDoms?" твоими задницами.":" их задницами.")));
				}
				
			} else if(subKneelingBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingBehindNames, false))
						+UtilText.parse(mainBehindSub, " [npc.is]")+" опустившись на колени позади "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" вы":" их")));
				if(domTaurs.isEmpty()) {
					sb.append(", готовый заняться анилингусом.");
				} else if(doms.isEmpty()) {
					sb.append((totalDoms==1
								?UtilText.parse(mainDom, ", и "+UtilText.parse(mainBehindSub, " [npc.is]")+" готова отсосать [npc.her] звериной заднице.")
								:(playerInDoms
									?", и "+UtilText.parse(mainBehindSub, " [npc.is]")+" готова отсосать одну из твоих звериных задниц."
									:", и "+UtilText.parse(mainBehindSub, " [npc.is]")+" готова отсосать одну из их звериных задниц.")));
				} else {
					sb.append(", готовая отсосать "+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] задницей."):(playerInDoms?" твоими задницами.":" их задницами.")));
				}
			}
			// Kneeling behind size difference:
			if(subSizeDiffBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffBehindNames, false))
						+" расположены позади "+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"вы":""+Util.intToString(totalDoms)+" ними,"))
						+ ", готовая отсосать "+(totalDoms==1?UtilText.parse(mainDom, " звериная задница [npc.her]"):(playerInDoms?" ваши задницы":" их задницы"))
						+", но из-за "+(playerInSubs?"твоей":"их")+" невысокого роста, "+(playerInSubs?"вы":"они")+" не нужно вставать на колени, а стоят в полный рост.");
				
			} else if(subSizeDiffBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffBehindNames, false))
						+UtilText.parse(mainBehindSizeDiffSub, " [npc.is], стоящий(ая) сзади ")+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"вы":""+Util.intToString(totalDoms)+" ними,"))
						+ ", готовая отсосать "+(totalDoms==1?UtilText.parse(mainDom, " звериная задница [npc.her]"):(playerInDoms?" ваши задницы":" их задницы"))
						+UtilText.parse(mainBehindSizeDiffSub, ", но из-за низкого роста [npc.her], [npc.she] [npc.does] не нужно вставать на колени, а [npc.is] стоит в полный рост."));
			}
			

			// Size difference:
//			List<GameCharacter> allDoms = new ArrayList<>(doms.keySet());
//			allDoms.addAll(domTaurs.keySet());
//			playerInDoms = false;
//			playerInSubs = false;
//			for(GameCharacter sub : subsStanding) {
//				mainSub = sub;
//				List<String> names = new ArrayList<>();
//				for(GameCharacter dom : allDoms) {
//					if(sub.isSizeDifferenceShorterThan(dom)) {
//						names.add(UtilText.parse(dom, "[npc.name]"));
//						if(dom.isPlayer()) {
//							playerInDoms = true;
//						}
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(sub,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "+(names.size()>1?(playerInDoms?"you":"them"):UtilText.parse(mainDom, "[npc.herHim]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(GameCharacter sub : subsStandingBehind) {
//				mainSub = sub;
//				List<String> names = new ArrayList<>();
//				for(GameCharacter dom : allDoms) {
//					if(sub.isSizeDifferenceShorterThan(dom)) {
//						names.add(UtilText.parse(dom, "[npc.name]"));
//						if(dom.isPlayer()) {
//							playerInDoms = true;
//						}
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(sub,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "
//									+(names.size()>1?(playerInDoms?"your rear end":"their rear ends"):UtilText.parse(mainDom, "[npc.namePos] [npc.ass+]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(GameCharacter dom : allDoms) {
//				List<String> names = new ArrayList<>();
//				for(GameCharacter sub : subsStanding) {
//					if(dom.isSizeDifferenceShorterThan(sub)) {
//						names.add(UtilText.parse(sub, "[npc.name]"));
//					}
//					if(sub.isPlayer()) {
//						playerInSubs = true;
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(dom,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "+(names.size()>1?(playerInSubs?"you":"them"):UtilText.parse(mainSub, "[npc.herHim]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(String s : sizeDifferenceAdditions) {
//				sb.append("<br/>[style.italicsOrange("+s+")]");
//			}
			
			// Size difference:
			Map<GameCharacter, List<String>> shorterMap = new HashMap<>(); // Mapping short character to list of larger characters
			for(GameCharacter c1 : occupiedSlots.keySet()) {
				for(GameCharacter c2 : occupiedSlots.keySet()) {
					if(c1!=c2 && c1.isSizeDifferenceShorterThan(c2)) {
						shorterMap.putIfAbsent(c1, new ArrayList<>());
						shorterMap.get(c1).add(UtilText.parse(c2, "<span style='color:"+c2.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
					}
				}
			}
			if(!shorterMap.isEmpty()) {
				sb.append("<br/>[style.italicsOrange(Некоторые персонажи значительно ниже остальных, поэтому они могут делать минет своим более крупным партнерам, даже стоя:)]");
				for(Entry<GameCharacter, List<String>> entry : shorterMap.entrySet()) {
					sb.append(UtilText.parse(entry.getKey(), "<br/><span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span> [npc.is] ниже, чем "));
					sb.append("");
					sb.append(Util.stringsToStringList(entry.getValue(), false));
					sb.append(".");
				}
			}
						
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			
			List<SexSlot> domStanding = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR);
			List<SexSlot> subStanding = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO, SexSlotStanding.STANDING_SUBMISSIVE_THREE, SexSlotStanding.STANDING_SUBMISSIVE_FOUR);
			List<SexSlot> subStandingBehind = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(
					SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.PERFORMING_ORAL_FOUR);
			List<SexSlot> performingOralBehind = Util.newArrayListOfValues(
					SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE, SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR);
			
			// Adding faceToFace for every dominant to every submissive:
			for(SexSlot slotD : domStanding) {
				for(SexSlot slotD2 : subStanding) {
					if(slotD!=slotD2) {
						interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotD, slotD2));
					}
				}
				for(SexSlot slotS : subStanding) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(slotD, slotS));
					for(SexSlot slotS2 : subStanding) {
						if(slotS!=slotS2) {
							interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotS, slotS2));
						}
					}
				}
				for(SexSlot slotS : subStandingBehind) {
					interactions.add(StandardSexActionInteractions.standingBehind.getSexActionInteractions(slotS, slotD));
					for(SexSlot slotS2 : subStandingBehind) {
						if(slotS!=slotS2) {
							interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotS, slotS2));
						}
					}
				}
			}
			
			// Adding performingOral for to every dominant:
			for(SexSlot performingO : performingOral) {
				for(SexSlot slotD : domStanding) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(performingO, slotD));
				}
			}

			// Adding performingOralBehind for to every dominant:
			for(SexSlot performingOB : performingOralBehind) {
				for(SexSlot slotD : domStanding) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(performingOB, slotD));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return true;
		}
	};
	

	public static final AbstractSexPosition AGAINST_WALL = new AbstractSexPosition("Прижавшись к [pc.wall]",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.BACK_TO_WALL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.BACK_TO_WALL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.BACK_TO_WALL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_FOUR, SexSlotAgainstWall.BACK_TO_WALL_FOUR));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_FOUR, SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePositionStandingOrOral=false;
			boolean suitablePositionWall=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotAgainstWall.FACE_TO_WALL
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_FOUR
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_FOUR) {
					suitablePositionWall = true;
				}
				if(e.getValue()==SexSlotAgainstWall.STANDING_WALL
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_FOUR
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR) {
					suitablePositionStandingOrOral = true;
				}
			}
			if(!suitablePositionWall || !suitablePositionStandingOrOral) {
				return new Value<Boolean, String>(false, "Для этой позы как минимум один персонаж должен прижиматься к [pc.a_wall], а другой должен либо стоять, либо делать минет.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			List<String> standingNames = new ArrayList<>();
			List<String> facingWallNames = new ArrayList<>();
			List<String> backToWallNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			
			GameCharacter mainStanding = null;
			GameCharacter mainFacingWall = null;
			GameCharacter mainBackToWall = null;
			GameCharacter mainPerformingOral = null;
			
			boolean playerFacingWall = false;
			boolean playerBackToWall = false;
			boolean playerPerformingOral = false;
			
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotAgainstWall.STANDING_WALL
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotAgainstWall.FACE_TO_WALL
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_FOUR) {
						if(mainFacingWall==null) {
							mainFacingWall=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							facingWallNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							facingWallNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerFacingWall = true;
						}
						
					} else if(e.getValue()==SexSlotAgainstWall.BACK_TO_WALL
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_FOUR) {
						if(mainBackToWall==null) {
							mainBackToWall=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							backToWallNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							backToWallNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerBackToWall = true;
						}
						
					} else {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerPerformingOral = true;
						}
					}
				}
			}

			int facingWallCount = facingWallNames.size();
			if(facingWallCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(facingWallNames, false))+" стоят бок о бок лицом к ближайшему [pc.wall]");
				
			} else if(facingWallCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(facingWallNames, false))+UtilText.parse(mainFacingWall, " [npc.is] стоит лицом к ближайшему [pc.wall]"));
			}
			
			if(backToWallNames.size()>=2) {
				if(facingWallCount>0) {
					sb.append(", в то время как "+Util.stringsToStringList(backToWallNames, false)+" стоят рядом с "
								+(facingWallCount==1?UtilText.parse(mainFacingWall,"[npc.herHim]"):(playerBackToWall?"вы":"их"))+", с "+(playerBackToWall?"твоей":"их")+" вместо этого повернувшись спиной к [pc.wall]. ");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(backToWallNames, false))+" стоят бок о бок, при этом "+(playerBackToWall?"твоей":"их")+" их зады прижаты к [pc.wall] позади них. ");
				}
				
			} else if(backToWallNames.size()==1) {
				if(facingWallCount>0) {
					sb.append(", в то время как "+Util.stringsToStringList(backToWallNames, false)+UtilText.parse(mainBackToWall, " [npc.is] стоит рядом с ")
							+(facingWallCount==1?UtilText.parse(mainFacingWall,"[npc.herHim]"):(playerBackToWall?"вы":"их"))+", с "+UtilText.parse(mainBackToWall,"[npc.her]")+" вместо этого спиной к [pc.wall]. ");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(backToWallNames, false))+UtilText.parse(mainBackToWall, " [npc.is] стоит, прижав [npc.her] задницу к [pc.wall] позади [npc.herHim]. "));
				}
			} else {
				if(facingWallCount>1) {
					sb.append(", при этом каждый из "+(playerFacingWall?"вы":"их")+" время от времени оглядывается через "+(playerFacingWall?"твоей":"их")+" плечо. ");
				} else {
					sb.append(UtilText.parse(mainFacingWall, ", и время от времени [npc.verb(throw)] бросает взгляд назад через [npc.her] плечо. "));
				}
			}

			int totalAgainstWall = facingWallCount + backToWallNames.size();
			int standingCount = standingNames.size();
			GameCharacter mainWall = mainBackToWall==null?mainFacingWall:mainBackToWall;
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" расположены так, чтобы стоять вплотную к "
							+(totalAgainstWall>1?(playerFacingWall || playerBackToWall?"вы":"их"):UtilText.parse(mainWall, "[npc.name]")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.is] расположен(а) так, чтобы стоять вплотную к ")+(totalAgainstWall>1?(playerFacingWall || playerBackToWall?"вы":"их"):UtilText.parse(mainWall, "[npc.name]")));
			}
			
			if(performingOralNames.size()>=2) {
				if(standingCount>0) {
					sb.append(", в то время как "+Util.stringsToStringList(performingOralNames, false)+" стоит(ят) на "+(playerPerformingOral?"твоей":"их")+" коленях, готовый(ая) отсосать "
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"вы":"их"))+".");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" расположен(ы) совсем рядом сзади и стоят(ят) на "+(playerPerformingOral?"твоей":"их")+" коленях, готовый(ая) отсосать "
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"вы":"их"))+".");
				}
				
			} else if(performingOralNames.size()==1) {
				if(standingCount>0) {
					sb.append(", в то время как "+Util.stringsToStringList(performingOralNames, false)+UtilText.parse(mainPerformingOral, " [npc.is] на [npc.her] коленях, готовый(ая) отсосать ")
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"вы":"их"))+".");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral, " [npc.is] расположен(ы) совсем рядом сзади, а [npc.is] стоит(ят) на [npc.her] коленях, готовый(ая) отсосать ")
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"вы":"их"))+".");
				}
				
			} else {
				if(totalAgainstWall>1) {
					sb.append(", готовый(ая) немного развлечься с "+Util.intToString(totalAgainstWall)+" из "+(playerFacingWall?"вы":"их")+".");
				} else {
					sb.append(", готовые повеселиться с "+UtilText.parse(mainWall,"[npc.herHim]")+".");
				}
			}
			

			// Size difference:
			Map<GameCharacter, List<String>> shorterMap = new HashMap<>(); // Mapping short character to list of larger characters
			for(GameCharacter c1 : occupiedSlots.keySet()) {
				for(GameCharacter c2 : occupiedSlots.keySet()) {
					if(c1!=c2 && c1.isSizeDifferenceShorterThan(c2)) {
						shorterMap.putIfAbsent(c1, new ArrayList<>());
						shorterMap.get(c1).add(UtilText.parse(c2, "<span style='color:"+c2.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
					}
				}
			}
			if(!shorterMap.isEmpty()) {
				sb.append("<br/>[style.italicsOrange(Некоторые персонажи значительно ниже остальных, поэтому они могут делать минет своим более крупным партнерам, даже стоя:)]");
				for(Entry<GameCharacter, List<String>> entry : shorterMap.entrySet()) {
					sb.append(UtilText.parse(entry.getKey(), "<br/><span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span> [npc.is] ниже, чем "));
					sb.append("");
					sb.append(Util.stringsToStringList(entry.getValue(), false));
					sb.append(".");
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> facingWall = Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.FACE_TO_WALL_FOUR);
			List<SexSlot> backToWall = Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL, SexSlotAgainstWall.BACK_TO_WALL_TWO, SexSlotAgainstWall.BACK_TO_WALL_THREE, SexSlotAgainstWall.BACK_TO_WALL_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.STANDING_WALL_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotAgainstWall.PERFORMING_ORAL_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO, SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE, SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR);
			
			// Assign all actions to all recipients.
			// It might make more sense to limit actions to just the target "in front" of the standing or oral-performing character, but then it would require a bit of micro-management for the player to move into the correct slot.
			// I decided that giving the performing characters a wide range of targets was better than making them micro-manage their slots.
			
			for(SexSlot slotStanding : standing) {
				for(SexSlot slotFacing : facingWall) {
					interactions.add(StandardSexActionInteractions.standingBehindCharacterFacingWall.getSexActionInteractions(slotStanding, slotFacing));
				}
				for(SexSlot slotBack : backToWall) {
					interactions.add(StandardSexActionInteractions.standingBehindCharacterBackToWall.getSexActionInteractions(slotStanding, slotBack));
				}
			}
			for(SexSlot slotOral : performingOral) {
				for(SexSlot slotFacing : facingWall) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(slotOral, slotFacing));
				}
				for(SexSlot slotBack : backToWall) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(slotOral, slotBack));
				}
			}

			// Those standing beside one another can kiss:
			for(int i=0;i<3;i++) {
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(backToWall.get(i), facingWall.get(i)));
				
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(backToWall.get(i), backToWall.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(facingWall.get(i), facingWall.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(backToWall.get(i), facingWall.get(i+1)));
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_FOUR
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character pressing another against the wall can use body weight to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.FACE_TO_WALL)) {
				return false;
			}
			return true;
		}
	};
	

	public static final AbstractSexPosition OVER_DESK = new AbstractSexPosition("Поверх [pc.desk]",
			8,
			true,
			SexActionPresets.positioningActionsNew, Util.newArrayListOfValues(MissionaryDesk.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			Map<SexSlot, GameCharacter> reversedPositioningSlotsMap = new HashMap<>();
			for(Entry<GameCharacter, SexSlot> entry : positioningSlots.entrySet()) {
				reversedPositioningSlotsMap.put(entry.getValue(), entry.getKey());
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_FOUR, SexSlotDesk.PERFORMING_ORAL_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_BACK));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_BACK_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_BACK_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR, SexSlotDesk.OVER_DESK_ON_BACK_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}

			if((slot==SexSlotDesk.HUMPING && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK))
					|| (slot==SexSlotDesk.HUMPING_TWO && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_TWO) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_TWO))
					|| (slot==SexSlotDesk.HUMPING_THREE && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_THREE) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_THREE))
					|| (slot==SexSlotDesk.HUMPING_FOUR && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_FOUR))) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Нет ни одного свободного персонажа, лежащего на [pc.desk], чтобы его оттрахать!"));
			}

			// Cannot hump when the person standing behind the intended target is a taur:
			if((slot==SexSlotDesk.HUMPING && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS), "[npc.Name] не может никого оттрахать, пока тело [npc2.namePos] [npc2.legRace] мешает!"));
			} else if((slot==SexSlotDesk.HUMPING_TWO && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_TWO) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_TWO).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_TWO), "[npc.Name] не может никого оттрахать, пока тело [npc2.namePos] [npc2.legRace] мешает!"));
			} else if((slot==SexSlotDesk.HUMPING_THREE && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_THREE) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_THREE).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_THREE), "[npc.Name] не может никого оттрахать, пока тело [npc2.namePos] [npc2.legRace] мешает!"));
			} else if((slot==SexSlotDesk.HUMPING_FOUR && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_FOUR) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_FOUR).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_FOUR), "[npc.Name] не может никого оттрахать, пока тело [npc2.namePos] [npc2.legRace] мешает!"));
			}
			// And vice-versa:
			if((slot==SexSlotDesk.BETWEEN_LEGS && positioningSlots.containsValue(SexSlotDesk.HUMPING) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING), "Тело [npc.NamePos] [npc2.legRace] не поместится, пока [npc2.name] мешает!"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_TWO && positioningSlots.containsValue(SexSlotDesk.HUMPING_TWO) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_TWO), "Тело [npc.NamePos] [npc2.legRace] не поместится, пока [npc2.name] мешает!"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_THREE && positioningSlots.containsValue(SexSlotDesk.HUMPING_THREE) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_THREE), "Тело [npc.NamePos] [npc2.legRace] не поместится, пока [npc2.name] мешает!"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_FOUR && positioningSlots.containsValue(SexSlotDesk.HUMPING_FOUR) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_FOUR), "Тело [npc.NamePos] [npc2.legRace] не поместится, пока [npc2.name] мешает!"));
			}
			
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotDesk.OVER_DESK_ON_BACK
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Из-за пропорций звериного низа [npc.her], [npc.nameIsFull] не может лечь на [npc.her] над [pc.desk]."));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePositionStandingOrOral=false;
			boolean suitablePositionDesk=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_FOUR
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) {
					suitablePositionDesk = true;
				}
				if(e.getValue()==SexSlotDesk.BETWEEN_LEGS
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_TWO
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_THREE
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_FOUR
						|| e.getValue()==SexSlotDesk.HUMPING
						|| e.getValue()==SexSlotDesk.HUMPING_TWO
						|| e.getValue()==SexSlotDesk.HUMPING_THREE
						|| e.getValue()==SexSlotDesk.HUMPING_FOUR
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_TWO
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_THREE
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_FOUR
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_TWO
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_THREE
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_FOUR) {
					suitablePositionStandingOrOral = true;
				}
			}
			if(!suitablePositionDesk || !suitablePositionStandingOrOral) {
				return new Value<Boolean, String>(false, "Для этой позы как минимум один персонаж должен находиться сверху на [pc.desk], а другой должен либо стоять, либо двигаться навстречу, либо принимать оральный секс, либо делать его.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			List<String> deskBackNames = new ArrayList<>();
			List<String> deskFrontNames = new ArrayList<>();
			List<String> deskFrontTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainDeskFront = null;
			GameCharacter mainDeskFrontTaur = null;
			GameCharacter mainDeskBack = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerDeskFront = false;
			boolean playerDeskBack = false;

			// TODO Characters under 80cm tall need to be described as having climbed up onto the desk.
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) {
						if(mainDeskFront==null && !e.getKey().isTaur()) {
							mainDeskFront=e.getKey();
						}
						if(mainDeskFrontTaur==null && e.getKey().isTaur()) {
							mainDeskFrontTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerDeskFront = true;
							if(!e.getKey().isTaur()) {
								deskFrontNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								deskFrontTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								deskFrontNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								deskFrontTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_TWO
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_THREE
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_FOUR) {
						if(mainDeskBack==null) {
							mainDeskBack=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerDeskBack = true;
							deskBackNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							deskBackNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotDesk.BETWEEN_LEGS
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_TWO
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_THREE
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_FOUR) {
						if(mainStanding==null && !e.getKey().isTaur()) {
							mainStanding=e.getKey();
						}
						if(mainStandingTaur==null && e.getKey().isTaur()) {
							mainStandingTaur=e.getKey();
						}
						
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotDesk.HUMPING
							|| e.getValue()==SexSlotDesk.HUMPING_TWO
							|| e.getValue()==SexSlotDesk.HUMPING_THREE
							|| e.getValue()==SexSlotDesk.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotDesk.PERFORMING_ORAL
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			
			int deskBackCount = deskBackNames.size();
			if(deskBackCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskBackNames, false))+" лежат на спине над [pc.desk]");
				
			} else if(deskBackCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskBackNames, false))+UtilText.parse(mainDeskBack, " [npc.is] лежит на спине над [pc.desk]"));
			}
			
			if(deskFrontNames.size()>=2) {
				if(deskBackCount>0) {
					sb.append(", в то время как "+Util.stringsToStringList(deskFrontNames, false)+" лежат ниц, рядом с "
								+(deskBackCount==1?UtilText.parse(mainDeskBack,"[npc.herHim]"):(playerDeskBack?"вы":"их"))+". ");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontNames, false))+" лежат ниц, на [pc.desk]. "); 
				}
				
			} else if(deskFrontNames.size()==1) {
				if(deskBackCount>0) {
					sb.append(", в то время как "+Util.stringsToStringList(deskFrontNames, false)+UtilText.parse(mainDeskFront, " [npc.is] лежит ниц, на [pc.desk]. "));
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontNames, false))+UtilText.parse(mainDeskFront, " [npc.is] лежит ниц, на [pc.desk]. "));
				}
				
			} else if(deskBackCount>0) {
				if(deskBackCount>1) {
					sb.append(", бок о бок друг с другом. ");
				} else {
					sb.append(UtilText.parse(mainDeskBack, ", готовый к тому, чтобы его оттрахали. "));
				}
			}
			
			// Desk taurs:
			if(deskFrontTaurNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))+" запрыгнули передними лапами на [pc.desk], а затем улеглись, положив свои звериные низы тел на него. ");
				
			} else if(deskFrontTaurNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))+UtilText.parse(mainDeskFrontTaur,
						" [npc.has] запрыгнул(а) на [pc.desk], используя [npc.her] передние лапы, а затем улегся(лась), положив [npc.her] звериный низ тела на него. "));
			}
			

			// Standing between legs:
			
			int standingCount = standingNames.size();
			int totalDeskCount = deskBackCount + deskFrontNames.size() + deskFrontTaurNames.size();
			GameCharacter mainDesk = mainDeskBack==null
					?mainDeskFront==null
						?mainDeskFrontTaur
						:mainDeskFront
					:mainDeskBack;
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" подвинулись вперед, чтобы встать прямо между "
							+(totalDeskCount>1?(playerDeskBack||playerDeskFront?"твоими ногами":"их ногами"):UtilText.parse(mainDesk, "[npc.namePos] [npc.legs]")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.has] подвинулся(лась) вперед, чтобы встать прямо между ")
							+(totalDeskCount>1?(playerDeskBack||playerDeskFront?"твоими ногами":"их ногами"):UtilText.parse(mainDesk, "[npc.namePos] [npc.legs]")));
			}
			if(standingCount>0) {
				if(totalDeskCount>1) {
					sb.append(", готовый(ая) немного развлечься с "+Util.intToString(totalDeskCount)+" из "+(playerDeskBack||playerDeskFront?"вы":"их")+". ");
				} else {
					sb.append(", готовые повеселиться с "+UtilText.parse(mainDesk,"[npc.herHim]")+". ");
				}
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+" запрыгнули на [pc.desk] передними лапами и готовы использовать свои дикие низы тел");
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStandingTaur," [npc.has] запрыгнул(а) на [pc.desk], используя [npc.her] передние лапы, и [npc.is] готов(а) использовать [npc.her] дикий низ тела"));
			}
			if(standingCountTaurs>0) {
				if(totalDeskCount>1) {
					sb.append(" оседлать "+Util.intToString(totalDeskCount)+" из "+(playerDeskBack||playerDeskFront?"вы":"их")+". ");
				} else {
					sb.append(" оседлать "+UtilText.parse(mainDesk,"[npc.herHim]")+". ");
				}
			}
			

			// Humping:
			
			int humpingCount = humpingNames.size();
			if(humpingCount>0) {
				if(humpingCount>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+" с жадностью взобрались на край [pc.desk] и готовы начать трахать своих партнеров.");
					
				} else if(humpingCount==1) {
					sb.append(UtilText.parse(mainHumping, "[npc.Name] [npc.has] с жадностью взобрались на край [pc.desk], и [npc.is] готовы начать трахать"));
					if(totalDeskCount>1) {
						sb.append(UtilText.parse(mainHumping," [npc.her] партнера. "));
					} else {
						sb.append(UtilText.parse(mainDesk," [npc.name]")+". ");
					}
				}
			}
			
			
			// Performing oral:
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" расположены так, чтобы оказаться прямо между "
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"твоими ногами":"их ногами")+", готовая отсосать "+(playerDeskBack||playerDeskFront?"тобой. ":"ними. ")
									:UtilText.parse(mainDesk, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral," [npc.is] расположена так, чтобы оказаться прямо между ")
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"твоими ногами":"их ногами")+", готовая отсосать "+(playerDeskBack||playerDeskFront?"тобой. ":"ними. ")
									:UtilText.parse(mainDesk, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", тем временем,":"")+" располагаются с другой стороны [pc.desk], рядом с "
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"вашими лицами":"их лицами")+", чтобы получить минет от "+(playerDeskBack||playerDeskFront?"тобой.":"ними.")
									:UtilText.parse(mainDesk, "[npc.namePos] [npc.face], чтобы получить минет от [npc.herHim].")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", тем временем,":"")+UtilText.parse(mainReceivingOral, " [npc.is] располагаются с другой стороны [pc.desk], рядом с ")
						+(totalDeskCount>1
								?(playerDeskBack||playerDeskFront?"вашими лицами":"их лицами")+", чтобы получить минет от "+(playerDeskBack||playerDeskFront?"тобой.":"ними.")
								:UtilText.parse(mainDesk, "[npc.namePos] [npc.face], чтобы получить минет от [npc.herHim].")));
			}
			


			// Size difference:
			// Taurs cannot bend down far enough to perform oral.
			boolean playerTaurPerformingOral = playerDeskFront&&Main.game.getPlayer().isTaur();
			List<String> sizeDifferenceAdditions = new ArrayList<>();
			
			if(!deskFrontTaurNames.isEmpty() && !receivingOralNames.isEmpty()) {
				if(deskFrontTaurNames.size()>=2) {
					sizeDifferenceAdditions.add(
							Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))
								+" не могут заняться оральным сексом с "+Util.stringsToStringChoice(receivingOralNames, false)+", из-за того что "
									+(playerTaurPerformingOral
											?"вы не можете наклонить свое гуманоидное туловище достаточно низко"
											:"они не могут наклонить свои гуманоидные туловища достаточно низко."));
				} else {
					sizeDifferenceAdditions.add(
							Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))
								+UtilText.parse(mainDeskFrontTaur,
										" [npc.is] не может заняться оральным сексом с "+Util.stringsToStringChoice(receivingOralNames, false)+", из-за того что [npc.sheIsFull] не может наклонить [npc.her] гуманоидное туловище достаточно низко."));
				}
			}
			
			for(String s : sizeDifferenceAdditions) {
				sb.append("<br/>[style.italicsOrange("+s+")]");
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> onDeskBack = Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_BACK, SexSlotDesk.OVER_DESK_ON_BACK_TWO, SexSlotDesk.OVER_DESK_ON_BACK_THREE, SexSlotDesk.OVER_DESK_ON_BACK_FOUR);
			List<SexSlot> onDeskFront = Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_FRONT_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.BETWEEN_LEGS_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotDesk.HUMPING, SexSlotDesk.HUMPING_TWO, SexSlotDesk.HUMPING_THREE, SexSlotDesk.HUMPING_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotDesk.PERFORMING_ORAL, SexSlotDesk.PERFORMING_ORAL_TWO, SexSlotDesk.PERFORMING_ORAL_THREE, SexSlotDesk.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotDesk.RECEIVING_ORAL, SexSlotDesk.RECEIVING_ORAL_TWO, SexSlotDesk.RECEIVING_ORAL_THREE, SexSlotDesk.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskBack.getSexActionInteractions(slotStanding, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskFront.getSexActionInteractions(slotStanding, slotFront));
				}
				for(SexSlot receivingSlot : receivingOral) {
					interactions.add(StandardSexActionInteractions.standingDeskToReceivingOral.getSexActionInteractions(slotStanding, receivingSlot));
				}
			}

			for(SexSlot slotHumping : humping) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskBack.getSexActionInteractions(slotHumping, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskFront.getSexActionInteractions(slotHumping, slotFront));
				}
				for(SexSlot receivingSlot : receivingOral) {
					interactions.add(StandardSexActionInteractions.standingDeskToReceivingOral.getSexActionInteractions(slotHumping, receivingSlot));
				}
			}
			
			for(SexSlot slotOral : performingOral) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(slotOral, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(slotOral, slotFront));
				}
			}
			
			for(SexSlot slotBack : onDeskBack) {
				for(SexSlot slotOral : receivingOral) {
					interactions.add(StandardSexActionInteractions.lyingOnDeskPerformingOral.getSexActionInteractions(slotBack, slotOral));
				}
			}
			for(SexSlot slotFront : onDeskFront) {
				for(SexSlot slotOral : receivingOral) {
					interactions.add(StandardSexActionInteractions.lyingOnDeskPerformingOral.getSexActionInteractions(slotFront, slotOral));
				}
			}

			// Those on the desk can kiss the ones next to them:
			for(int i=0;i<3;i++) {
//				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskBack.get(i)));
//				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskFront.get(i), onDeskFront.get(i)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskFront.get(i)));
				
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskBack.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskFront.get(i), onDeskFront.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskFront.get(i+1)));
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character lying back can use their legs, tails, or tentacles to force a facial creampie on characters performing oral on them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericFaceForceCreampieAreas),
						new Value<>(Tail.class, genericFaceForceCreampieAreas),
						new Value<>(Tentacle.class, genericFaceForceCreampieAreas));
			}
			// The character lying back can use their legs, tails, or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character lying on their front can use their tails, or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character between legs can use their weight to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			// The character lying back or on front can use their arms to force a facial creampie on characters receiving oral from them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.OVER_DESK_FRONT)) {
				return false;
			}
			return true;
		}
	};

	
	public static final AbstractSexPosition STOCKS = new AbstractSexPosition("Стойла",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(StocksSex.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(slot!= positioningSlots.get(characterToTakeSlot)) {
				if(positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "[npc.NameIsFull] зажат в колодки, поэтому не может сменить слот!"));
					
				} else if(slot==SexSlotStocks.LOCKED_IN_STOCKS
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Персонажи не могут быть зажаты в колодки или освобождены из них во время этой секс-сцены!"));
				}
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS, SexSlotStocks.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_TWO, SexSlotStocks.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_THREE, SexSlotStocks.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_FOUR, SexSlotStocks.PERFORMING_ORAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean characterInStocks=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					characterInStocks = true;
				}
			}
			if(!characterInStocks) {
				return new Value<Boolean, String>(false, "Для этой позы как минимум один персонаж должен быть зажат в колодках.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> allStocksNames = new ArrayList<>();
			List<String> stocksNames = new ArrayList<>();
			List<String> stocksTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> beneathNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainStocks = null;
			GameCharacter mainStocksTaur = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainBeneath = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerStocks = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
						if(mainStocks==null && !e.getKey().isTaur()) {
							mainStocks=e.getKey();
						}
						if(mainStocksTaur==null && e.getKey().isTaur()) {
							mainStocksTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerStocks = true;
							allStocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allStocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotStocks.BEHIND_STOCKS
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotStocks.HUMPING
							|| e.getValue()==SexSlotStocks.HUMPING_TWO
							|| e.getValue()==SexSlotStocks.HUMPING_THREE
							|| e.getValue()==SexSlotStocks.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotStocks.BENEATH_STOCKS
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_FOUR) {
						if(mainBeneath==null) {
							mainBeneath=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							beneathNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							beneathNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotStocks.PERFORMING_ORAL
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			int stocksCount = stocksNames.size() + stocksTaurNames.size();
			GameCharacter soloStocks = mainStocks==null?mainStocksTaur:mainStocks;
			if(stocksNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))+" зажаты в "+Util.intToString(stocksNames.size())
						+" набор колодок, и они полностью во власти любого, кто захочет ими воспользоваться "+(playerStocks?"вы":"их")+". "); 
				
			} else if(stocksTaurNames.isEmpty()) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))
						+UtilText.parse(soloStocks,
								" [npc.is] заперт(а) в колодках, и [npc.is] полностью во власти любого, кто захочет использовать [npc.herHim]. ")); 
			}
			
			if(stocksTaurNames.size()>=2) {
				sb.append(" Обладая звериным низом, "+Util.stringsToStringList(stocksTaurNames, false)
						+" были заперты в специальные колодки, которые принудительно опускают их передние части в положение на коленях, в то время как их животные задние части удерживаются в вертикальном положении, позволяя любому подойти и использовать их рты или гениталии так, как ему заблагорассудится. "); 
				
			} else if(stocksTaurNames.size()==1) {
				sb.append(UtilText.parse(mainStocksTaur,
						" Обладая звериным низом [npc.a_legRace], "+Util.stringsToStringList(stocksTaurNames, false)
						+" [npc.has] заперт(а) в специальных колодках, которые принудительно опускают переднюю часть [npc.her] в положение на коленях, в то время как звериная задняя часть [npc.her] удерживается в вертикальном положении, позволяя любому подойти и использовать рот или гениталии [npc.her] так, как ему заблагорассудится. ")); 
			}
			
			// Using stocks target:
			
			int standingCount = standingNames.size();
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" подвинулись вперед так, чтобы встать прямо позади "
							+(stocksCount>1
									?(playerStocks?"тебя, готовые воспользоваться твоей неподвижностью. ":"них, готовых воспользоваться их неподвижностью. ")
									:UtilText.parse(soloStocks, "[npc.name], готового(ую) воспользоваться [npc.her] неподвижностью. ")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.has] двинулся вперед, чтобы встать прямо за ")
							+(stocksCount>1
									?(playerStocks?"тебя, готовые воспользоваться твоей неподвижностью. ":"них, готовых воспользоваться их неподвижностью. ")
									:UtilText.parse(soloStocks, "[npc.name], готового(ую) воспользоваться [npc.her] неподвижностью. ")));
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+" встали на задние лапы, прежде чем упереться передними "
							+UtilText.parse(mainStandingTaur, "[npc.feet]")+" на верхнюю часть колодок, позволяя им "
							+(stocksCount>1
									?(playerStocks?"эффективно оседлать "+(Util.intToString(stocksCount))+" тебя. ":"эффективно оседлать "+(Util.intToString(stocksCount))+" их. ")
									:UtilText.parse(soloStocks, "эффективно оседлать [npc.name]. ")));
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStanding," [npc.has] встал на [npc.her] задние лапы, прежде чем упереться [npc.her] передними [npc.feet] на верхнюю часть колодок, позволяя [npc.herHim] ")
							+(stocksCount>1
									?(playerStocks
											?UtilText.parse(mainStanding,"эффективно оседлать любого из "+(Util.intToString(stocksCount))+" [npc.she] [npc.verb(want)] тебя. ")
											:UtilText.parse(mainStanding,"эффективно оседлать любого из "+(Util.intToString(stocksCount))+" [npc.she] [npc.verb(want)] их. "))
									:UtilText.parse(soloStocks, "эффективно оседлать [npc.name]. ")));
			}
			
			if(humpingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+" забрались на "
						+(stocksCount>1
								?(playerStocks?"твою поясницу":"их поясницы")+", готовые начать трахать "+(playerStocks?"тобой. ":"ними. ")
								:UtilText.parse(soloStocks, "[npc.namePos] поясницу, готовую начать трахать [npc.herHim]. ")));
			
			
			} else if(humpingNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+UtilText.parse(mainHumping," [npc.has] забрались на ")
							+UtilText.parse(soloStocks, "[npc.namePos] поясницу, готовую начать трахать [npc.herHim]. "));
			}
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" расположены так, чтобы оказаться прямо между "
							+(stocksCount>1
									?(playerStocks?"твоими ногами":"их ногами")+", готовая отсосать "+(playerStocks?"тобой. ":"ними. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral," [npc.is] расположена так, чтобы оказаться прямо между ")
							+(stocksCount>1
									?(playerStocks?"твоими ногами":"их ногами")+", готовая отсосать "+(playerStocks?"тобой. ":"ними. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
			}
			

			if(beneathNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+" опустились под "
							+(stocksCount>1
									?(playerStocks?"вы":"их")+", готовая к траху. "
									:UtilText.parse(soloStocks, "[npc.name], готовая к тому, чтобы ее оттрахал [npc.herHim]. ")));
				
				
			} else if(beneathNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+UtilText.parse(mainBeneath," [npc.has] опустилась под ")
							+(stocksCount>1
									?(playerStocks?"вы":"их")+", готовая к траху. "
									:UtilText.parse(soloStocks, "[npc.name], готовая к тому, чтобы ее оттрахал [npc.herHim]. ")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty() || !beneathNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", тем временем,":"")+" расположены по другую сторону колодок, рядом с "
							+(stocksCount>1
									?(playerStocks?"вашими лицами":"их лицами")+", чтобы получить минет от "+(playerStocks?"тобой.":"ними.")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], чтобы получить минет от [npc.herHim].")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", тем временем,":"")+UtilText.parse(mainReceivingOral, " [npc.is] расположена по другую сторону колодок, рядом с ")
						+(stocksCount>1
								?(playerStocks?"вашими лицами":"их лицами")+", чтобы получить минет от "+(playerStocks?"тобой.":"ними.")
								:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], чтобы получить минет от [npc.herHim].")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> inStocks = Util.newArrayListOfValues(SexSlotStocks.LOCKED_IN_STOCKS, SexSlotStocks.LOCKED_IN_STOCKS_TWO, SexSlotStocks.LOCKED_IN_STOCKS_THREE, SexSlotStocks.LOCKED_IN_STOCKS_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotStocks.BEHIND_STOCKS, SexSlotStocks.BEHIND_STOCKS_TWO, SexSlotStocks.BEHIND_STOCKS_THREE, SexSlotStocks.BEHIND_STOCKS_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotStocks.HUMPING, SexSlotStocks.HUMPING_TWO, SexSlotStocks.HUMPING_THREE, SexSlotStocks.HUMPING_FOUR);
			List<SexSlot> beneath = Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS, SexSlotStocks.BENEATH_STOCKS_TWO, SexSlotStocks.BENEATH_STOCKS_THREE, SexSlotStocks.BENEATH_STOCKS_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotStocks.PERFORMING_ORAL, SexSlotStocks.PERFORMING_ORAL_TWO, SexSlotStocks.PERFORMING_ORAL_THREE, SexSlotStocks.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotStocks.RECEIVING_ORAL, SexSlotStocks.RECEIVING_ORAL_TWO, SexSlotStocks.RECEIVING_ORAL_THREE, SexSlotStocks.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckingCharacterInStocks.getSexActionInteractions(slotStanding, stockSlot));
				}
				for(SexSlot slotHumping : humping) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(slotStanding, slotHumping));
				}
			}

			for(SexSlot slotHumping : humping) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.humpingCharacterInStocks.getSexActionInteractions(slotHumping, stockSlot));
				}
			}
			
			for(SexSlot slotBeneath: beneath) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckedByCharacterInStocks.getSexActionInteractions(slotBeneath, stockSlot));
				}
			}
			
			for(SexSlot slotOral : performingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.performingOralOnStocks.getSexActionInteractions(slotOral, stockSlot));
				}
			}

			for(SexSlot slotOral : receivingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.stocksCharacterPerformingOral.getSexActionInteractions(stockSlot, slotOral));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)
					&& action.getParticipantType()==SexParticipantType.SELF)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in the stocks can use their tails or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.BEHIND_STOCKS)
					|| Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.HUMPING_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character performing oral can use their arms to force a facial creampie from those locked in stocks:
			if((Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS))
					&& (Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			// Characters locked in stocks cannot use fingers:
			if(Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(penetrator.getArmRows()*2)));
			}
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.LOCKED_IN_STOCKS)) {
				return false;
			}
			return true;
		}
	};
	
	public static final AbstractSexPosition MILKING_STALL = new AbstractSexPosition("Доильный станок",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(MilkingStall.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(slot!= positioningSlots.get(characterToTakeSlot)) {
				if(positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "[npc.NameIsFull] заблокирована в станке, поэтому не может сменить слот!"));
					
				} else if(slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Персонажи не могут быть заблокированы в станках или освобождены из них во время этой секс-сцены!"));
				}
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR, SexSlotMilkingStall.PERFORMING_ORAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean characterInStocks=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					characterInStocks = true;
				}
			}
			if(!characterInStocks) {
				return new Value<Boolean, String>(false, "Для этой позы как минимум один персонаж должен быть зафиксирован в доильном стойле.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> allStocksNames = new ArrayList<>();
			List<String> stocksNames = new ArrayList<>();
			List<String> stocksTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> beneathNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainStocks = null;
			GameCharacter mainStocksTaur = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainBeneath = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerStocks = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
						if(mainStocks==null && !e.getKey().isTaur()) {
							mainStocks=e.getKey();
						}
						if(mainStocksTaur==null && e.getKey().isTaur()) {
							mainStocksTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerStocks = true;
							allStocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allStocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.HUMPING
							|| e.getValue()==SexSlotMilkingStall.HUMPING_TWO
							|| e.getValue()==SexSlotMilkingStall.HUMPING_THREE
							|| e.getValue()==SexSlotMilkingStall.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR) {
						if(mainBeneath==null) {
							mainBeneath=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							beneathNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							beneathNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			int stocksCount = stocksNames.size() + stocksTaurNames.size();
			GameCharacter soloStocks = mainStocks==null?mainStocksTaur:mainStocks;
			if(stocksNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))+" зажаты в "+Util.intToString(stocksNames.size())
						+" наборы доильных стойл и полностью зависят от любого, кто захочет использовать "+(playerStocks?"вы":"их")+". "); 
				
			} else if(stocksTaurNames.isEmpty()) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))
						+UtilText.parse(soloStocks,
								" [npc.is] зафиксирован(а) в доильном стойле, а [npc.is] полностью зависит от любого, кто захочет использовать [npc.herHim]. ")); 
			}
			
			if(stocksTaurNames.size()>=2) {
				sb.append(" Обладая звериным низом, "+Util.stringsToStringList(stocksTaurNames, false)
						+" были зафиксированы в специально сконструированных доильных стойлах, которые принудительно опускают их передние половины в положение на коленях, в то время как их звериные задние половины удерживаются в вертикальном положении, позволяя любому подойти и использовать их рты или гениталии по своему желанию. "); 
				
			} else if(stocksTaurNames.size()==1) {
				sb.append(UtilText.parse(mainStocksTaur,
						" Обладая звериным низом [npc.a_legRace], "+Util.stringsToStringList(stocksTaurNames, false)
						+" [npc.has] зафиксирован(а) в специально сконструированном доильном стойле, которое принудительно опускает [npc.her] переднюю половину в положение на коленях, в то время как [npc.her] звериная задняя половина удерживается в вертикальном положении, позволяя любому подойти и использовать [npc.her] рот или гениталии по своему желанию. ")); 
			}
			
			// Standing behind:
			
			int standingCount = standingNames.size();
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" подвинулись вперед так, чтобы встать прямо позади "
							+(stocksCount>1
									?(playerStocks?"тебя, готовые воспользоваться твоей неподвижностью. ":"них, готовых воспользоваться их неподвижностью. ")
									:UtilText.parse(soloStocks, "[npc.name], готового(ую) воспользоваться [npc.her] неподвижностью. ")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.has] двинулся вперед, чтобы встать прямо за ")
							+(stocksCount>1
									?(playerStocks?"тебя, готовые воспользоваться твоей неподвижностью. ":"них, готовых воспользоваться их неподвижностью. ")
									:UtilText.parse(soloStocks, "[npc.name], готового(ую) воспользоваться [npc.her] неподвижностью. ")));
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+" встали на задние лапы, прежде чем упереться передними "
							+UtilText.parse(mainStandingTaur, "[npc.feet]")+" на вершине стойла, позволяя им "
							+(stocksCount>1
									?(playerStocks?"эффективно оседлать "+(Util.intToString(stocksCount))+" тебя. ":"эффективно оседлать "+(Util.intToString(stocksCount))+" их. ")
									:UtilText.parse(soloStocks, "эффективно оседлать [npc.name]. ")));
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStanding," [npc.has] поднялся(ась) на [npc.her] задние лапы, прежде чем упереть [npc.her] передние [npc.feet] на вершину стойла, позволяя [npc.herHim] ")
							+(stocksCount>1
									?(playerStocks
											?UtilText.parse(mainStanding,"эффективно оседлать любого из "+(Util.intToString(stocksCount))+" [npc.she] [npc.verb(want)] тебя. ")
											:UtilText.parse(mainStanding,"эффективно оседлать любого из "+(Util.intToString(stocksCount))+" [npc.she] [npc.verb(want)] их. "))
									:UtilText.parse(soloStocks, "эффективно оседлать [npc.name]. ")));
			}

			
			if(humpingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+" забрались на "
						+(stocksCount>1
								?(playerStocks?"твою поясницу":"их поясницы")+", готовые начать трахать "+(playerStocks?"тобой. ":"ними. ")
								:UtilText.parse(soloStocks, "[npc.namePos] поясницу, готовую начать трахать [npc.herHim]. ")));
			
			
			} else if(humpingNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+UtilText.parse(mainHumping," [npc.has] забрались на ")
							+UtilText.parse(soloStocks, "[npc.namePos] поясницу, готовую начать трахать [npc.herHim]. "));
			}
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" расположены так, чтобы оказаться прямо между "
							+(stocksCount>1
									?(playerStocks?"твоими ногами":"их ногами")+", готовая отсосать "+(playerStocks?"тобой. ":"ними. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral," [npc.is] расположена так, чтобы оказаться прямо между ")
							+(stocksCount>1
									?(playerStocks?"твоими ногами":"их ногами")+", готовая отсосать "+(playerStocks?"тобой. ":"ними. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
			}

			
			if(beneathNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+" опустились под "
							+(stocksCount>1
									?(playerStocks?"вы":"их")+", готовая к траху. "
									:UtilText.parse(soloStocks, "[npc.name], готовая к тому, чтобы ее оттрахал [npc.herHim]. ")));
				
				
			} else if(beneathNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+UtilText.parse(mainBeneath," [npc.has] опустилась под ")
							+(stocksCount>1
									?(playerStocks?"вы":"их")+", готовая к траху. "
									:UtilText.parse(soloStocks, "[npc.name], готовая к тому, чтобы ее оттрахал [npc.herHim]. ")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", тем временем,":"")+" расположены с другой стороны "+(stocksCount==1?"стойла":"стойл")+", рядом с "
							+(stocksCount>1
									?(playerStocks?"вашими лицами":"их лицами")+", чтобы получить минет от "+(playerStocks?"тобой.":"ними.")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], чтобы получить минет от [npc.herHim].")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", тем временем,":"")
						+UtilText.parse(mainReceivingOral, " [npc.is] располагается с другой стороны "+(stocksCount==1?"стойла":"стойл")+", рядом с ")
						+(stocksCount>1
								?(playerStocks?"вашими лицами":"их лицами")+", чтобы получить минет от "+(playerStocks?"тобой.":"ними.")
								:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], чтобы получить минет от [npc.herHim].")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> inStocks = Util.newArrayListOfValues(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotMilkingStall.HUMPING, SexSlotMilkingStall.HUMPING_TWO, SexSlotMilkingStall.HUMPING_THREE, SexSlotMilkingStall.HUMPING_FOUR);
			List<SexSlot> beneath = Util.newArrayListOfValues(SexSlotMilkingStall.BENEATH_MILKING_STALL, SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO, SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE, SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotMilkingStall.PERFORMING_ORAL, SexSlotMilkingStall.PERFORMING_ORAL_TWO, SexSlotMilkingStall.PERFORMING_ORAL_THREE, SexSlotMilkingStall.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotMilkingStall.RECEIVING_ORAL, SexSlotMilkingStall.RECEIVING_ORAL_TWO, SexSlotMilkingStall.RECEIVING_ORAL_THREE, SexSlotMilkingStall.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckingCharacterInStocks.getSexActionInteractions(slotStanding, stockSlot));
				}
				for(SexSlot slotHumping : humping) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(slotStanding, slotHumping));
				}
			}
			
			for(SexSlot slotHumping : humping) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.humpingCharacterInStocks.getSexActionInteractions(slotHumping, stockSlot));
				}
			}

			for(SexSlot slotBeneath: beneath) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckedByCharacterInStocks.getSexActionInteractions(slotBeneath, stockSlot));
				}
			}

			for(SexSlot slotOral : performingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.performingOralOnStocks.getSexActionInteractions(slotOral, stockSlot));
				}
			}

			for(SexSlot slotOral : receivingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.stocksCharacterPerformingOral.getSexActionInteractions(stockSlot, slotOral));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)
					&& action.getParticipantType()==SexParticipantType.SELF)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in the stocks can use their tails or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.BEHIND_STOCKS)
					|| Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.HUMPING_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character performing oral can use their arms to force a facial creampie from those locked in stocks:
			if((Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			// Characters locked in stocks cannot use fingers:
			if(Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(penetrator.getArmRows()*2)));
			}
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.LOCKED_IN_STOCKS)) {
				return false;
			}
			return true;
		}
	};
	
	
	public static final AbstractSexPosition ALL_FOURS = new AbstractSexPosition("На четвереньках",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(DoggyStyleAndProneBone.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_FOUR, SexSlotAllFours.BEHIND_ORAL_FOUR));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_ANAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_ANAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_ANAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_FOUR, SexSlotAllFours.IN_FRONT_ANAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			// Added to limit humping character to those of a small stature, but it's more fun to let anyone take that slot
//			List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
//			List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
//			for(int i=0;i<4;i++) {
//				if(slot==humpingList.get(i)) {
//					for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
//						if(e.getValue()==allFoursList.get(i)) {
//							if(!characterToTakeSlot.isSizeDifferenceShorterThan(e.getKey())) {
//								return new Value<Boolean, String>(
//										false,
//										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' can only be used if "+UtilText.parse(characterToTakeSlot, "[npc.nameIsFull]")
//											+" significantly shorter than the character in the '"+Util.capitaliseSentence(allFoursList.get(i).getDescription())+"' slot.");
//							}
//						}
//					}
//				}
//			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS)
					&& (slot==SexSlotAllFours.BEHIND
							|| slot==SexSlotAllFours.BEHIND_ORAL
							|| slot==SexSlotAllFours.HUMPING
							|| slot==SexSlotAllFours.IN_FRONT_ANAL
							|| slot==SexSlotAllFours.IN_FRONT
							|| slot==SexSlotAllFours.USING_FEET)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_TWO)
					&& (slot==SexSlotAllFours.BEHIND_TWO
							|| slot==SexSlotAllFours.BEHIND_ORAL_TWO
							|| slot==SexSlotAllFours.HUMPING_TWO
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_TWO
							|| slot==SexSlotAllFours.IN_FRONT_TWO
							|| slot==SexSlotAllFours.USING_FEET_TWO)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_TWO.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_THREE)
					&& (slot==SexSlotAllFours.BEHIND_THREE
							|| slot==SexSlotAllFours.BEHIND_ORAL_THREE
							|| slot==SexSlotAllFours.HUMPING_THREE
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_THREE
							|| slot==SexSlotAllFours.IN_FRONT_THREE
							|| slot==SexSlotAllFours.USING_FEET_THREE)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_THREE.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_FOUR)
					&& (slot==SexSlotAllFours.BEHIND_FOUR
							|| slot==SexSlotAllFours.BEHIND_ORAL_FOUR
							|| slot==SexSlotAllFours.HUMPING_FOUR
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_FOUR
							|| slot==SexSlotAllFours.IN_FRONT_FOUR
							|| slot==SexSlotAllFours.USING_FEET_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_FOUR.getDescription())+"' не назначен персонаж.");
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotAllFours.ALL_FOURS
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_TWO
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_THREE
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_FOUR) {
					suitablePosition = true;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "Для этой позы как минимум одному персонажу нужно встать на четвереньки.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			// For each character on all fours, describe them and those who are interacting with them:

			List<SexSlot> position1 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.USING_FEET, SexSlotAllFours.HUMPING, SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_ANAL);
			List<SexSlot> position2 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_ORAL_TWO, SexSlotAllFours.USING_FEET_TWO, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_ANAL_TWO);
			List<SexSlot> position3 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_ORAL_THREE, SexSlotAllFours.USING_FEET_THREE, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_ANAL_THREE);
			List<SexSlot> position4 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_FOUR, SexSlotAllFours.BEHIND_FOUR, SexSlotAllFours.BEHIND_ORAL_FOUR, SexSlotAllFours.USING_FEET_FOUR, SexSlotAllFours.HUMPING_FOUR, SexSlotAllFours.IN_FRONT_FOUR, SexSlotAllFours.IN_FRONT_ANAL_FOUR);
			
			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			int count=0;
			for(List<SexSlot> positions : positionLists) {
				GameCharacter allFours = null;
				GameCharacter behind = null;
				GameCharacter behindOral = null;
				GameCharacter usingFeet = null;
				GameCharacter humping = null;
				GameCharacter inFront = null;
				GameCharacter inFrontAnal = null;
				
				GameCharacter fallBackAllFours1 = null;
				GameCharacter fallBackAllFours2 = null;
				GameCharacter fallBackAllFours3 = null;
				
				for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
					if(e.getValue()==positions.get(0)) {
						allFours = e.getKey();
					}
					if(e.getValue()==positions.get(1)) {
						behind = e.getKey();
					}
					if(e.getValue()==positions.get(2)) {
						behindOral = e.getKey();
					}
					if(e.getValue()==positions.get(3)) {
						usingFeet = e.getKey();
					}
					if(e.getValue()==positions.get(4)) {
						humping = e.getKey();
					}
					if(e.getValue()==positions.get(5)) {
						inFront = e.getKey();
					}
					if(e.getValue()==positions.get(6)) {
						inFrontAnal = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS_THREE) {
						fallBackAllFours1 = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS_TWO) {
						fallBackAllFours2 = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS) {
						fallBackAllFours3 = e.getKey();
					}
				}
				
				boolean skipAllFours = false;
				if(allFours == null) {
					skipAllFours = true;
					allFours = fallBackAllFours1;
				}
				if(allFours == null) {
					allFours = fallBackAllFours2;
				}
				if(allFours == null) {
					allFours = fallBackAllFours3;
				}
				
				if(!skipAllFours) {
					switch(count) {
						case 0:
							sb.append(UtilText.parse(allFours,
									(!allFours.isTaur()
										?"[npc.NameIsFull] на четвереньках, готовая к траху в позе догги-стайл. "
										:"[npc.NameHasFull] опустил переднюю часть дикого тела [npc.her] ниже, заставив [npc.legRace] встать на колени, при этом высоко задрав [npc.her] задницу, готовую к траху. ")));
							break;
						case 1:
							sb.append(UtilText.parse(allFours, fallBackAllFours3,
									(!allFours.isTaur()
										?" Подобно [npc2.name], [npc.nameHasFull] тоже опустилась на четвереньки, подставляя [npc.herself] для того, чтобы её трахнули как животное. "
										:" Подобно [npc2.name], [npc.nameHasFull] опустилась на колени, прижимая переднюю часть тела [npc.her] [npc.legRace], при этом задрав [npc.her] [npc.ass+] вверх, чтобы ее трахали как животное. ")));
							break;
						case 2:
							sb.append(UtilText.parse(Util.newArrayListOfValues(allFours, fallBackAllFours3, fallBackAllFours2),
									(!allFours.isTaur()
										?" Как и [npc2.name] с [npc3.name], [npc.nameHasFull] опустилась на [npc.her] [npc.hands] и колени. "
										:" Как и [npc2.name] с [npc3.name], [npc.nameHasFull] опустилась на передние [npc.legs] [npc.her], приподняв [npc.her] [npc.ass+] и подставив нижнюю часть тела [npc.her] [npc.legRace] для жесткого траха. ")));
							break;
						case 3:
							sb.append(UtilText.parse(Util.newArrayListOfValues(allFours, fallBackAllFours3, fallBackAllFours2, fallBackAllFours1),
									(!allFours.isTaur()
										?" Завершая группу из четырех: [npc.nameIsFull] на четвереньках рядом с [npc2.name], [npc3.name] и [npc4.name]. "
										:" Завершая группу из четырех: [npc.nameIsFull] лежит рядом с [npc2.name], [npc3.name] и [npc4.name], а [npc.is] подставляет дикое тело [npc.her] [npc.legRace], словно похотливое животное. ")));
							break;
					}
				}
				
				boolean continuation = false;
				if(humping!=null) {
					if(humping.isSizeDifferenceShorterThan(allFours)) {
						sb.append(UtilText.parse(humping, allFours,
								" Не позволяя [npc.her] низкому росту помешать [npc.herHim] развлечься, [npc.NameHasFull] забралась на [npc2.namePos] поясницу, после чего обхватила [npc2.her] талию, готовясь начать трахать [npc2.herHim]."));
					} else {
						sb.append(UtilText.parse(humping, allFours,
								" [npc.NameHasFull] полностью нависла над спиной [npc2.namePos] и, прижавшись [npc.her] пахом к [npc2.her] [npc2.ass+], [npc.is] приготовилась начать трахать [npc2.herHim]."));
					}
					continuation = true;
				}
				if(behind!=null) {
					sb.append(UtilText.parse(behind, allFours,
							!behind.isTaur()
								?" [npc.NameIsFull] "+(positions.get(1).isStanding(behind)?"стоя":"на коленях")+" прямо за [npc2.herHim], в позиции, где [npc.she] может начать трахать [npc2.herHim] в любой момент."
								:" [npc.NameHasFull] полностью перешагнул через [npc2.herHim], нависнув над диким телом [npc.her] [npc.legRace] и фактически оседлав [npc2.herHim]."));
					continuation = true;
				}
				if(behindOral!=null) {
					sb.append(UtilText.parse(behindOral, allFours,
							" [npc.NameHasFull] "+(positions.get(2).isStanding(behindOral)?"поднялась, чтобы стоять":"опустилась на [npc.her] колени")+" позади [npc2.name], готовая отсосать [npc2.herHim]."));
					continuation = true;
				}
				if(usingFeet!=null) {
					sb.append(UtilText.parse(usingFeet, allFours,
							" Опустившись на [npc.her] колени позади [npc2.name], [npc.name] [npc.has] расположила [npc.herself] так, чтобы иметь возможность использовать [npc2.her] [npc2.feet+]."));
					continuation = true;
				}

				if(inFront!=null) {
					sb.append(UtilText.parse(inFront, allFours,
							continuation
							?" Тем временем, с другой стороны [npc2.name], [npc.nameHasFull] "+(positions.get(5).isStanding(inFront)?"шагающие":"опустился на колени")
									+(inFront.isTaur()
										?" поверх [npc2.herHim], после чего прижал звериное пах [npc.her] к [npc2.her] [npc2.face] так, чтобы [npc.she] могла получать оральный секс от [npc2.herHim]."
										:" прямо перед [npc2.her] [npc2.face], готовый принимать оральный секс от [npc2.herHim].")
							:" Вместо того чтобы заняться [npc2.herHim] сзади, [npc.nameHasFull] "+(positions.get(5).isStanding(inFront)?"подошел":"опустился на колени")
								+" прямо перед [npc2.her] [npc2.face], готовый принимать оральный секс от [npc2.herHim]."));
				}
				if(inFrontAnal!=null) {
					sb.append(UtilText.parse(inFrontAnal, allFours,
							continuation
							?" Тем временем, с другой стороны [npc2.name], [npc.nameHasFull] "+(positions.get(6).isStanding(inFrontAnal)?"подошел и развернулся":"опустилась на колени и отползла назад")
									+" к [npc2.her] [npc2.face], готовая использовать [npc2.her] рот на [npc.her] [npc.ass+]."
							:" Вместо того чтобы заняться [npc2.herHim] сзади, [npc.nameHasFull] "+(positions.get(6).isStanding(inFrontAnal)?"подошел и развернулся":"опустился на колени и отполз назад")
								+" к [npc2.her] [npc2.face], готовый использовать [npc2.her] рот на [npc.her] [npc.ass+]."));
				}

				if(!skipAllFours) {
					count++;
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
			List<SexSlot> behindList = Util.newArrayListOfValues(SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_FOUR);
			List<SexSlot> behindOralList = Util.newArrayListOfValues(SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.BEHIND_ORAL_TWO, SexSlotAllFours.BEHIND_ORAL_THREE, SexSlotAllFours.BEHIND_ORAL_FOUR);
			List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
			List<SexSlot> feetList = Util.newArrayListOfValues(SexSlotAllFours.USING_FEET, SexSlotAllFours.USING_FEET_TWO, SexSlotAllFours.USING_FEET_THREE, SexSlotAllFours.USING_FEET_FOUR);
			List<SexSlot> inFrontList = Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_FOUR);
			List<SexSlot> inFrontAnalList = Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_ANAL, SexSlotAllFours.IN_FRONT_ANAL_TWO, SexSlotAllFours.IN_FRONT_ANAL_THREE, SexSlotAllFours.IN_FRONT_ANAL_FOUR);
			
			// Those down on all fours can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO));
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE));
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR));
			
			// All those behind can interact with all those on all fours, those humping, and those in front:
			for(SexSlot behindSlot : behindList) {
				for(SexSlot allFoursSlot : allFoursList) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(behindSlot, allFoursSlot));
				}
				for(SexSlot humpingSlot : humpingList) {
					interactions.add(StandardSexActionInteractions.allFoursBehindToHumping.getSexActionInteractions(behindSlot, humpingSlot));
				}
			}
			for(int i=0; i<4; i++) {
				if(Main.sex.getCharacterInPosition(allFoursList.get(i))!=null && !Main.sex.getCharacterInPosition(allFoursList.get(i)).isTaur()) {
					for(SexSlot inFrontSlot : inFrontList) {
						interactions.add(StandardSexActionInteractions.allFoursCharacterBehindToCharactersFront.getSexActionInteractions(behindList.get(i), inFrontSlot));
					}
				}
			}
			
			// Those performing oral behind can oral the humpers:
			for(SexSlot behindOralSlot : behindOralList) {
				for(SexSlot humpingSlot : humpingList) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(behindOralSlot, humpingSlot));
				}
			}
			
			for(SexSlot allFoursSlot : allFoursList) {
				// All those performing oral behind can interact with all those on all fours:
				for(SexSlot behindOralSlot : behindOralList) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(behindOralSlot, allFoursSlot));
				}
				// All those behind wanting to use feet can interact with all those on all fours:
				for(SexSlot feetSlot : feetList) {
					interactions.add(StandardSexActionInteractions.allFoursFeet.getSexActionInteractions(feetSlot, allFoursSlot));
				}
				// All those receiving oral interact with all those on all fours:
				for(SexSlot inFrontSlot : inFrontList) {
					interactions.add(StandardSexActionInteractions.allFoursPerformingOral.getSexActionInteractions(allFoursSlot, inFrontSlot));
				}
				// All those receiving anal oral interact with all those on all fours:
				for(SexSlot inFrontAnalSlot : inFrontAnalList) {
					interactions.add(StandardSexActionInteractions.allFoursPerformingOralBehind.getSexActionInteractions(allFoursSlot, inFrontAnalSlot));
				}
			}
			
			// Humping characters can only interact with those they are on top of:
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING, SexSlotAllFours.ALL_FOURS));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.ALL_FOURS_TWO));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.ALL_FOURS_THREE));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_FOUR, SexSlotAllFours.ALL_FOURS_FOUR));

			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their tails or tentacles to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_FOUR)) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
			}
			// Non-bipedal characters performing oral can use their arm(s) to force a mouth creampie:
			if(cumTarget.isTaur()
					&& (Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_FOUR)) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			return null;
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			// Restrict anal actions if the one humping is in the way:
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.ALL_FOURS) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.BEHIND_ALL_FOURS)) {
				List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
				List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter humper = Main.sex.getCharacterInPosition(humpingList.get(i));
					GameCharacter allFours = Main.sex.getCharacterInPosition(allFoursList.get(i));
					if(humper!=null
							&& target.equals(allFours)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.ASS))
							&& Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaOrifice.VAGINA).contains(humper)) {
						return true;
					}
				}
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.ALL_FOURS)
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING_TWO
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING_THREE
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING_FOUR) {
				return false;
			}
			return true;
		}
	};
	
	
	//TODO cowgirl piazuri?
	/**
	 * Contains support for:<br/>
	 * <b>Face-sitting</b><br/>
	 * <b>Cow-girl</b><br/>
	 * <b>Missionary</b><br/>
	 * <b>Reverse cow-girl</b><br/>
	 * <b>Sixty-nine</b><br/>
	 * <b>Mating press</b><br/>
	 * <b>Scissoring</b>
	 */
	public static final AbstractSexPosition LYING_DOWN = new AbstractSexPosition("Лежа",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(Cowgirl.class, FaceSitting.class, MatingPress.class, Missionary.class, SixtyNine.class, DoggyStyleAndProneBone.class)) {
		
		private List<SexSlot> position1 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_FRONT, //0-1
				SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.SCISSORING, //2-6
				SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.SIXTY_NINE, SexSlotLyingDown.RECEIVING_ORAL, //7-10
				SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.BESIDE); //11-13
		private List<SexSlot> position2 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_FRONT_TWO, //0-1
				SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.SCISSORING_TWO, //2-6
				SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.SIXTY_NINE_TWO, SexSlotLyingDown.RECEIVING_ORAL_TWO, //7-10
				SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.BESIDE_TWO); //11-13
		private List<SexSlot> position3 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FRONT_THREE, //0-1
				SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.SCISSORING_THREE, //2-6
				SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.SIXTY_NINE_THREE, SexSlotLyingDown.RECEIVING_ORAL_THREE, //7-10
				SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.BESIDE_THREE); //11-13
		private List<SexSlot> position4 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_FOUR, SexSlotLyingDown.LYING_DOWN_FRONT_FOUR, //0-1
				SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.MISSIONARY_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.SCISSORING_FOUR, //2-6
				SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR, SexSlotLyingDown.RECEIVING_ORAL_FOUR, //7-10
				SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.MISSIONARY_ORAL_FOUR, SexSlotLyingDown.BESIDE_FOUR); //11-13
		
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_FRONT));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_FRONT_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FRONT_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FOUR, SexSlotLyingDown.LYING_DOWN_FRONT_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.SCISSORING));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.SCISSORING_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.SCISSORING_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.SCISSORING_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.SCISSORING));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.SCISSORING_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.SCISSORING_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_FOUR, SexSlotLyingDown.MISSIONARY_ORAL_FOUR, SexSlotLyingDown.SCISSORING_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}

			// Mating press is only compatible with missionary & oral:
			mutuallyExclusiveSlots.clear();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE,
					SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO,
					SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SCISSORING_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE,
					SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SCISSORING_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR,
					SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SCISSORING_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				SexSlot matingPressSlot = entry.get(0);
				for(SexSlot s : entry) {
					if(s==slot) {
						if(s==matingPressSlot) {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
								}
							}
							
						} else {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(e.getValue()==matingPressSlot && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
								}
							}
						}
					}
				}
			}

			// Frontal lying down positions are only compatible with "missionary" (between legs) and both oral positions:
			mutuallyExclusiveSlots.clear();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE,
					SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO,
					SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SCISSORING_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE,
					SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SCISSORING_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR,
					SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SCISSORING_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				SexSlot frontalLyingDownSlot = entry.get(0);
				for(SexSlot s : entry) {
					if(s==slot) {
						if(s==frontalLyingDownSlot) {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
								}
							}
							
						} else {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(e.getValue()==frontalLyingDownSlot && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
								}
							}
						}
					}
				}
			}
			
			
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotLyingDown.SCISSORING
						|| slot==SexSlotLyingDown.SCISSORING_TWO
						|| slot==SexSlotLyingDown.SCISSORING_THREE
						|| slot==SexSlotLyingDown.SCISSORING_FOUR)) {
				return new Value<Boolean, String>(false, "Слот '"+Util.capitaliseSentence(slot.getDescription())+"' можно использовать только персонажами с двуногой нижней частью тела.");
			}
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotLyingDown.SIXTY_NINE
						|| slot==SexSlotLyingDown.SIXTY_NINE_TWO
						|| slot==SexSlotLyingDown.SIXTY_NINE_THREE
						|| slot==SexSlotLyingDown.SIXTY_NINE_FOUR)) {
				return new Value<Boolean, String>(false, "Слот '"+Util.capitaliseSentence(slot.getDescription())+"' можно использовать только персонажами с двуногой нижней частью тела.");
			}
			
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN)
					&& (slot==SexSlotLyingDown.COWGIRL
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE
							|| slot==SexSlotLyingDown.FACE_SITTING
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE
							|| slot==SexSlotLyingDown.LAP_PILLOW
							|| slot==SexSlotLyingDown.MATING_PRESS
							|| slot==SexSlotLyingDown.SCISSORING
							|| slot==SexSlotLyingDown.SIXTY_NINE)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_TWO)
					&& (slot==SexSlotLyingDown.COWGIRL_TWO
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_TWO
							|| slot==SexSlotLyingDown.FACE_SITTING_TWO
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_TWO
							|| slot==SexSlotLyingDown.LAP_PILLOW_TWO
							|| slot==SexSlotLyingDown.MATING_PRESS_TWO
							|| slot==SexSlotLyingDown.SCISSORING_TWO
							|| slot==SexSlotLyingDown.SIXTY_NINE_TWO)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_TWO.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_THREE)
					&& (slot==SexSlotLyingDown.COWGIRL_THREE
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_THREE
							|| slot==SexSlotLyingDown.FACE_SITTING_THREE
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_THREE
							|| slot==SexSlotLyingDown.LAP_PILLOW_THREE
							|| slot==SexSlotLyingDown.MATING_PRESS_THREE
							|| slot==SexSlotLyingDown.SCISSORING_THREE
							|| slot==SexSlotLyingDown.SIXTY_NINE_THREE)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_THREE.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FOUR)
					&& (slot==SexSlotLyingDown.COWGIRL_FOUR
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_FOUR
							|| slot==SexSlotLyingDown.FACE_SITTING_FOUR
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR
							|| slot==SexSlotLyingDown.LAP_PILLOW_FOUR
							|| slot==SexSlotLyingDown.MATING_PRESS_FOUR
							|| slot==SexSlotLyingDown.SCISSORING_FOUR
							|| slot==SexSlotLyingDown.SIXTY_NINE_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FOUR.getDescription())+"' не назначен персонаж.");
			}
			

			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_TWO)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL_TWO)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_TWO.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_THREE)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL_THREE)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_THREE.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока в слот '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR.getDescription())+"' не назначен персонаж.");
			}
			
			
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT)
					&& (slot==SexSlotLyingDown.MISSIONARY
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, если к одному из ' не привязан персонаж"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN.getDescription())+"' либо '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_TWO)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_TWO)
					&& (slot==SexSlotLyingDown.MISSIONARY_TWO
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_TWO)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, если к одному из ' не привязан персонаж"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_TWO.getDescription())+"' либо '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_TWO.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_THREE)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_THREE)
					&& (slot==SexSlotLyingDown.MISSIONARY_THREE
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_THREE)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, если к одному из ' не привязан персонаж"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_THREE.getDescription())+"' либо '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_THREE.getDescription())+"' не назначен персонаж.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FOUR)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR)
					&& (slot==SexSlotLyingDown.MISSIONARY_FOUR
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, если к одному из ' не привязан персонаж"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FOUR.getDescription())+"' либо '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR.getDescription())+"' не назначен персонаж.");
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotLyingDown.LYING_DOWN
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_TWO
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_THREE
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FOUR
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT_TWO
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT_THREE
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) {
					suitablePosition = true;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "Для этой позы хотя бы один персонаж должен лежать.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			// For each character lying down, describe them and those who are interacting with them:

			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			List<String> besideNames = new ArrayList<>();
			GameCharacter mainBeside = null;

			List<GameCharacter> lyingDownCharacters = new ArrayList<>();
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue().hasTag(SexSlotTag.LYING_DOWN)
						|| e.getValue().hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
					lyingDownCharacters.add(e.getKey());
				}
			}
			
			int count=0;
			for(List<SexSlot> positions : positionLists) {
				GameCharacter lyingDown = null;
				GameCharacter lyingDownFront = null;
				GameCharacter cowgirl = null;
				GameCharacter cowgirlReverse = null;
				GameCharacter missionary = null;
				GameCharacter matingPress = null;
				GameCharacter scissoring = null;
				GameCharacter faceSitting = null;
				GameCharacter faceSittingReverse = null;
				GameCharacter sixtyNine = null;
				GameCharacter receivingOral = null;
				GameCharacter lapPillow = null;
				GameCharacter performingOral = null;
				
				for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
					if(e.getValue()==positions.get(0)) {
						lyingDown = e.getKey();
					}
					if(e.getValue()==positions.get(1)) {
						lyingDownFront = e.getKey();
					}
					if(e.getValue()==positions.get(2)) {
						cowgirl = e.getKey();
					}
					if(e.getValue()==positions.get(3)) {
						cowgirlReverse = e.getKey();
					}
					if(e.getValue()==positions.get(4)) {
						missionary = e.getKey();
					}
					if(e.getValue()==positions.get(5)) {
						matingPress = e.getKey();
					}
					if(e.getValue()==positions.get(6)) {
						scissoring = e.getKey();
					}
					if(e.getValue()==positions.get(7)) {
						faceSitting = e.getKey();
					}
					if(e.getValue()==positions.get(8)) {
						faceSittingReverse = e.getKey();
					}
					if(e.getValue()==positions.get(9)) {
						sixtyNine = e.getKey();
					}
					if(e.getValue()==positions.get(10)) {
						receivingOral = e.getKey();
					}
					if(e.getValue()==positions.get(11)) {
						lapPillow = e.getKey();
					}
					if(e.getValue()==positions.get(12)) {
						performingOral = e.getKey();
					}
					if(e.getValue()==positions.get(13)) {
						if(mainBeside==null) {
							mainBeside = e.getKey();
						}
						if(e.getKey().isPlayer()) {
							besideNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							besideNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
					
				}
				
				if(lyingDown==null) {
					// There always needs to be a character lying down, so if there are multiple characters in an oral position (or something similar), make the last encountered lyingDown character the focused one
					lyingDown = lyingDownCharacters.get(lyingDownCharacters.size()-1);
				}
				
				boolean skipLyingdown = count >= lyingDownCharacters.size();
				if(!skipLyingdown) {
					GameCharacter targetedCharacter1, targetedCharacter2, targetedCharacter3, targetedCharacter4;
					switch(count) {
						case 0:
							targetedCharacter1 = lyingDownCharacters.get(0);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(targetedCharacter1,
										(!targetedCharacter1.isTaur()
											?" [npc.NameIsFull] лежит на животе у [npc.her], "
											:" [npc.NameIsFull] лежит, растянувшись на диком теле [npc.her] [npc.legRace], ")
										+"покорно подставив [npc.her] спину, задницу и пах. "));
							} else {
								sb.append(UtilText.parse(lyingDownCharacters.get(0),
										(!lyingDownCharacters.get(0).isTaur()
											?" [npc.NameIsFull] лежит на спине у [npc.her], покорно подставив [npc.her] живот, [npc.face] и пах. "
											:" [npc.NameHasFull] лежит на диком теле [npc.her] [npc.legRace], а затем переворачивается на [npc.her] спину, чтобы покорно подставить [npc.her] живот. ")));
							}
							break;
						case 1:
							targetedCharacter1 = lyingDownCharacters.get(0);
							targetedCharacter2 = lyingDownCharacters.get(1);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(targetedCharacter2, targetedCharacter1,
										(!targetedCharacter2.isTaur()
											?" Присоединяясь к [npc2.name] на полу, [npc.nameIsFull] лежит на животе у [npc.her], "
											:" Присоединяясь к [npc2.name] на полу, [npc.nameIsFull] лежит на диком теле [npc.her] [npc.legRace], ")
										+"готовый к покорному траху. "));
							} else {
								sb.append(UtilText.parse(targetedCharacter2, targetedCharacter1,
										(!targetedCharacter2.isTaur()
											?" Присоединяясь к [npc2.name] на полу, [npc.nameHasFull] опускается на [npc.her] спину. "
											:" Присоединившись к [npc2.name] на полу, [npc.nameHasFull] опустилась на колено на дикое тело [npc.her] [npc.legRace], а затем перевернулась, подставляя [npc.her]. ")));
							}
							break;
						case 2:
							targetedCharacter1 = lyingDownCharacters.get(0);
							targetedCharacter2 = lyingDownCharacters.get(1);
							targetedCharacter3 = lyingDownCharacters.get(2);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter3.isTaur()
											?" Как и [npc2.name] с [npc3.name], [npc.nameHasFull] опустилась на пол, а [npc.is] легла на [npc.her] лицом, "
											:" Как и [npc2.name] с [npc3.name], [npc.nameHasFull] опустилась на пол и [npc.is], растянувшись на теле дикого [npc.legRace] [npc.her], ")
										+"готовый к покорному траху. "));
							} else {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter3.isTaur()
											?" Как и [npc2.name] с [npc3.name], [npc.nameHasFull] опустилась на пол, а затем легла на спину к [npc.her]. "
											:" Как и [npc2.name] с [npc3.name], [npc.nameHasFull] опустилась на дикое тело [npc.her] [npc.legRace], а затем перевернулась на спину к [npc.her], подставляя [npc.herself]. ")));
							}
							break;
						case 3:
							targetedCharacter1 = lyingDownCharacters.get(0);
							targetedCharacter2 = lyingDownCharacters.get(1);
							targetedCharacter3 = lyingDownCharacters.get(2);
							targetedCharacter4 = lyingDownCharacters.get(3);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter4, targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter4.isTaur()
											?" Завершая группу из четырех человек: [npc.nameIsFull] лежит на [npc.her] лицом рядом с [npc2.name], [npc3.name] и [npc4.name]. "
											:" Завершая группу из четырех человек: [npc.nameIsFull] лежит на [npc.her] лицом рядом с [npc2.name], [npc3.name] и [npc4.name]. ")));
							} else {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter4, targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter4.isTaur()
											?" Завершая группу из четырех человек: [npc.nameIsFull] лежит на [npc.her] спиной рядом с [npc2.name], [npc3.name] и [npc4.name]. "
											:" Завершая группу из четырех человек: [npc.nameIsFull] лежит рядом с [npc2.name], [npc3.name], [npc4.name], а [npc.has] перевернулась, подставляя низ дикого тела [npc.her] [npc.legRace]. ")));
							}
							break;
					}
					count++;
				}
				
				boolean continuation = false;
				
				if(lyingDownFront!=null) {
					if(missionary!=null) {
						if(!missionary.isTaur()) {
							sb.append(UtilText.parse(missionary, lyingDownFront,
									" [npc.NameHasFull] опустился позади [npc2.name] и расположил [npc.herself] между [npc2.her] [npc2.legs], готовый трахать [npc2.herHim] в позе «лежа на животе»."));
						} else {
							sb.append(UtilText.parse(missionary, lyingDownFront,
									" [npc.NameHasFull] прижал дикое тело [npc.legRace] [npc.her] сверху на [npc2.name], и [npc.is] был готов начать спариваться с [npc2.herHim] в позе «лежа на животе»."));
						}
						continuation = true;
					}
					if(performingOral!=null) {
						sb.append(UtilText.parse(performingOral, lyingDownFront,
								" Желая отсосать у [npc2.name], [npc.nameHasFull] лег(ла) между [npc2.her] [npc2.legs], прижав [npc.her] [npc.face] прямо к [npc2.her] паху."));
					}
					if(receivingOral!=null) {
						sb.append(UtilText.parse(receivingOral, lyingDownFront,
								" Желая получить минет от [npc2.name], [npc.nameHasFull] опустился(лась) перед [npc2.herHim], прижав пах [npc.her] прямо к [npc2.her] [npc2.face]."));
					}
					
					
				} else {
					// These four slots are mutually exclusive:
					if(cowgirl!=null) {
						if(!cowgirl.isTaur()) {
							sb.append(UtilText.parse(cowgirl, lyingDown,
									" [npc.NameHasFull] перешагнул(а) через [npc2.name], после чего опустил(а) [npc.herself] и сел(а) верхом на пах [npc2.her], готовый(ая) начать скакать на [npc2.herHim] в позе наездницы."));
						} else {
							sb.append(UtilText.parse(cowgirl, lyingDown,
									" [npc.NameHasFull] перешагнул(а) через [npc2.name], после чего опустил(а) заднюю часть дикого тела [npc.legRace] [npc.her], чтобы плотно прижать пах [npc.her] к [npc2.hers]."));
						}
						continuation = true;
					}
					if(cowgirlReverse!=null) {
						if(!cowgirlReverse.isTaur()) {
							sb.append(UtilText.parse(cowgirlReverse, lyingDown,
									" [npc.NameHasFull] перешагнул(а) через [npc2.name], после чего развернулся(лась), опустил(а) [npc.herself] и сел(а) верхом на пах [npc2.her], готовый(ая) начать скакать на [npc2.herHim] в позе обратной наездницы."));
						} else {
							sb.append(UtilText.parse(cowgirlReverse, lyingDown,
									" [npc.NameHasFull] перешагнула через [npc2.name], после чего развернулась и опустила заднюю часть дикого тела [npc.her], обладающего [npc.legRace], чтобы плотно прижать [npc.her] пах к [npc2.hers]."));
						}
						continuation = true;
					}
					if(matingPress!=null) {
						if(!matingPress.hasLegs()) {
							sb.append(UtilText.parse(matingPress, lyingDown,
									" [npc.NameHasFull] прижал [npc2.namePos] спиной к [npc2.her], после чего навис сверху над [npc2.herHim], удерживая [npc.her] в паху прямо над [npc2.hers]. [npc.SheIs] властно использовал(а) [npc.her] [npc.hands], чтобы прижать [npc2.namePos] запястья к полу по обе стороны от головы [npc2.her], тем самым полностью зафиксировав [npc2.herHim] под [npc.herHim] в позе, идеально подходящей для осеменения."));
							
						} else if(!matingPress.isTaur()) {
							sb.append(UtilText.parse(matingPress, lyingDown,
									" [npc.NameHasFull] раздвинул [npc2.namePos] [npc2.legs] и прижал их назад, к голове [npc2.her], а затем лег сверху на [npc2.herHim], удерживая [npc.her] прямо над [npc2.hers]. [npc.SheIs] властно использовал [npc.her] [npc.hands], чтобы прижать запястья [npc2.namePos] к полу по обе стороны от головы [npc2.her], тем самым полностью зафиксировав [npc2.herHim] под [npc.herHim] в позе, идеально подходящей для осеменения."));
							
						} else {
							sb.append(UtilText.parse(matingPress, lyingDown,
									" [npc.NameHasFull] раздвинул [npc2.namePos] [npc2.legs] и прижал [npc2.her] голову назад, прежде чем обрушить [npc.her] дикое [npc.legRace] тело на [npc2.herHim], зависнув [npc.her] звериным пахом прямо над [npc2.hers]. [npc.SheIs] доминантно использует [npc.her] передний [npc.legs], чтобы прижать [npc2.name] запястья к полу по обе стороны от [npc2.her] головы, тем самым полностью пригвождая [npc2.herHim] под [npc.herHim] в позе, идеально подходящей для осеменения."));
						}
						continuation = true;
					}
					if(scissoring!=null) {
						sb.append(UtilText.parse(scissoring, lyingDown,
								" [npc.NameHasFull] легла на спину [npc.her], и, подвигаясь [npc.spreadingHerLegs] и перебирая ногами вперед, [npc.has] прижала пах [npc.her] к [npc2.hers], готовясь начать ножницы с [npc2.herHim]."));
						continuation = true;
					}
	
					if(missionary!=null) {
						if(!missionary.isTaur()) {
							sb.append(UtilText.parse(missionary, lyingDown,
									" [npc.NameHasFull] опустился между [npc2.namePos] [npc2.legs], готовый трахать [npc2.herHim] в миссионерской позе."));
						} else {
							sb.append(UtilText.parse(missionary, lyingDown,
									" [npc.NameHasFull] опустил(а) тело дикого [npc.legRace] [npc.her] поверх [npc2.name], и [npc.is], готовый(ая) начать спариваться с [npc2.herHim] в миссионерской позе."));
						}
						continuation = true;
					}
					
					if(faceSitting!=null) {
						if(!faceSitting.isTaur()) {
							sb.append(UtilText.parse(faceSitting, lyingDown,
									continuation
									?" Тем временем, жаждая секса лицом, [npc.nameHasFull] перешагнул через [npc2.name], после чего опустил [npc.herself] и плотно прижал свой пах [npc.her] к [npc2.her] [npc2.face]."
									:" Жаждая секса лицом, [npc.nameHasFull] перешагнул через [npc2.name], после чего опустил [npc.herself] и плотно прижал свой пах [npc.her] к [npc2.her] [npc2.face]."));
						} else {
							sb.append(UtilText.parse(faceSitting, lyingDown,
									continuation
									?" Тем временем, жаждущая сесть [npc.nameHasFull] на лицо, перешагнула через [npc2.name], после чего опустила дикое тело [npc.legRace] [npc.her] и крепко прижала свою животную паховую область к [npc2.face] [npc2.her] [npc.her]."
									:" Жаждущая секса лицом, [npc.nameHasFull] перешагнула через [npc2.name], после чего опустила дикое тело [npc.her] [npc.legRace] и плотно прижала свою животную паховую область к [npc2.her] [npc2.face] [npc.her]."));
						}
					}
					if(faceSittingReverse!=null) {
						if(!faceSittingReverse.isTaur()) {
							sb.append(UtilText.parse(faceSittingReverse, lyingDown,
									continuation
									?" Тем временем [npc.nameHasFull] перешагнула через [npc2.name], затем развернулась лицом к нижней части тела [npc2.her] и опустила [npc.herself], чтобы занять позу обратного сидения на лице."
									:" [npc.NameHasFull] перешагнула через [npc2.name], затем развернулась лицом к нижней части тела [npc2.her] и опустила [npc.herself], чтобы занять позу обратного сидения на лице."));
						} else {
							sb.append(UtilText.parse(faceSittingReverse, lyingDown,
									continuation
									?" Тем временем [npc.nameHasFull] перешагнула через [npc2.name], затем развернулась лицом к нижней части тела [npc2.her] и опустила дикое тело [npc.her] [npc.legRace], чтобы занять позу обратного сидения на лице."
									:" [npc.NameHasFull] перешагнула через [npc2.name], затем развернулась лицом к нижней части тела [npc2.her] и опустила дикое тело [npc.her] [npc.legRace], чтобы занять позу обратного сидения на лице."));
						}
					}
					if(sixtyNine!=null) {
						sb.append(UtilText.parse(sixtyNine, lyingDown,
								continuation
								?" Тем временем, жаждая орального секса, [npc.nameHasFull] перекинула ногу через [npc2.name], после чего развернулась и опустила [npc.herself] на [npc2.herHim] в позе шестьдесят девять."
								:" Жаждая орального секса, [npc.nameHasFull] перекинула ногу через [npc2.name], после чего развернулась и опустила [npc.herself] на [npc2.herHim] в позе шестьдесят девять."));
					}
					if(lapPillow!=null) {
						sb.append(UtilText.parse(lapPillow, lyingDown,
								continuation
								?" Тем временем, желая уделить внимание [npc2.herHim], [npc.nameHasFull] опустилась на колени рядом с [npc2.namePos] [npc2.face], после чего приподняла [npc2.her] голову и положила её на [npc.her] колени."
								:" Желая уделить внимание [npc2.herHim], [npc.nameHasFull] опустилась на колени рядом с [npc2.namePos] [npc2.face], после чего приподняла [npc2.her] голову и положила её на [npc.her] колени."));
					}
	
					if(performingOral!=null) {
						sb.append(UtilText.parse(performingOral, lyingDown,
								" Желая отсосать у [npc2.name], [npc.nameHasFull] лег(ла) между [npc2.her] [npc2.legs], прижав [npc.her] [npc.face] прямо к [npc2.her] паху."));
					}
				}
			}
			
			if(besideNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(besideNames, false))
						+" расположены сбоку, готовые к оральному сексу или другим видам секса без проникновения. "); 
				
			} else if(besideNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(besideNames, false))
						+UtilText.parse(mainBeside,
								" [npc.is] расположена сбоку, готовая к оральному сексу или другим видам секса без проникновения. ")); 
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			// For each character lying down, describe them and those who are interacting with them:

			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			List<SexSlot> besideSlots = Util.newArrayListOfValues(SexSlotLyingDown.BESIDE, SexSlotLyingDown.BESIDE_TWO, SexSlotLyingDown.BESIDE_THREE, SexSlotLyingDown.BESIDE_FOUR);
			
			for(int i=0; i<positionLists.size(); i++) {
				List<SexSlot> positions = positionLists.get(i);
				
				SexSlot lyingDown = positions.get(0);
				SexSlot lyingDownFront = positions.get(1);
				SexSlot cowgirl = positions.get(2);
				SexSlot cowgirlReverse = positions.get(3);
				SexSlot missionary = positions.get(4);
				SexSlot matingPress = positions.get(5);
				SexSlot scissoring = positions.get(6);
				SexSlot faceSitting = positions.get(7);
				SexSlot faceSittingReverse = positions.get(8);
				SexSlot sixtyNine = positions.get(9);
				SexSlot receivingOral = positions.get(10);
				SexSlot lapPillow = positions.get(11);
				SexSlot performingOral = positions.get(12);
				
				// Lying on front:

				interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(missionary, lyingDownFront));
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, lyingDownFront));
				interactions.add(StandardSexActionInteractions.faceSittingRiding.getSexActionInteractions(receivingOral, lyingDownFront)); // Use same interactions as face sitting
				interactions.add(StandardSexActionInteractions.allFoursCharacterBehindToCharactersFront.getSexActionInteractions(receivingOral, missionary)); // Use same interaction as all fours in front/behind
				
				// Lying on back:
				
				interactions.add(StandardSexActionInteractions.cowgirlRiding.getSexActionInteractions(cowgirl, lyingDown));
				interactions.add(StandardSexActionInteractions.cowgirlReverseRiding.getSexActionInteractions(cowgirlReverse, lyingDown));
				interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, lyingDown));
				// Add missionary to lying down characters beside the main target:
				if(i>0) {
					interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, positionLists.get(i-1).get(0)));
				}
				if(i+1<positionLists.size()) {
					interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, positionLists.get(i+1).get(0)));
				}
				
				interactions.add(StandardSexActionInteractions.matingPress.getSexActionInteractions(matingPress, lyingDown));
				interactions.add(StandardSexActionInteractions.scissoring.getSexActionInteractions(scissoring, lyingDown));
				interactions.add(StandardSexActionInteractions.faceSittingRiding.getSexActionInteractions(faceSitting, lyingDown));
				interactions.add(StandardSexActionInteractions.faceSittingReverseRiding.getSexActionInteractions(faceSittingReverse, lyingDown));
				interactions.add(StandardSexActionInteractions.sixtyNine.getSexActionInteractions(sixtyNine, lyingDown));
				interactions.add(StandardSexActionInteractions.sixtyNine.getSexActionInteractions(lyingDown, sixtyNine));
				interactions.add(StandardSexActionInteractions.lapPillow.getSexActionInteractions(lapPillow, lyingDown));
				
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, lyingDown));
				// Add oral to lying down characters beside the main target:
				if(i>0) {
					interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, positionLists.get(i-1).get(0)));
				}
				if(i+1<positionLists.size()) {
					interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, positionLists.get(i+1).get(0)));
				}
				
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, matingPress));
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(performingOral, cowgirl));
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(performingOral, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.performingOralToSixtyNine.getSexActionInteractions(performingOral, sixtyNine));
				
				
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, scissoring));
				interactions.add(StandardSexActionInteractions.characterToCharactersBack.getSexActionInteractions(faceSittingReverse, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(lapPillow, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(lapPillow, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersBack.getSexActionInteractions(lapPillow, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(cowgirlReverse, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(cowgirlReverse, scissoring));

				interactions.add(StandardSexActionInteractions.characterToCharactersBackSex.getSexActionInteractions(missionary, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersBackSex.getSexActionInteractions(missionary, matingPress));

				// Those beside can interact with all lying down and kneeling:
				for(SexSlot beside : besideSlots) {
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, lyingDown));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, lyingDownFront));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, cowgirl));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, cowgirlReverse));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, missionary));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, scissoring));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, faceSitting));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, faceSittingReverse));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, lapPillow));
				}
			}
			
			// Those lying down can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR));

			// Those in missionary positions can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// Characters riding the one lying down can use their bodyweight to force a creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_FOUR
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_FOUR
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_FOUR) {
						return Util.newHashMapOfValues(
								new Value<>(Torso.class, genericGroinForceCreampieAreas));
					}
			}
			// Characters performing sixty-nine or lying down oral can use weight to force a facial creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_FOUR
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_FOUR) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericFaceForceCreampieAreas));
				}
			}
			// Characters lying down can use their legs to force a creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_FOUR
				|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Leg.class, genericGroinForceCreampieAreas),
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
			}
			// Characters performing oral on sixty-nine can use arms to force a facial creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			List<SexSlot> slotsTop = Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.MATING_PRESS_FOUR);
			List<SexSlot> slotsBottom = Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR);
			
			for(int i=0;i<4;i++) {
				GameCharacter top = Main.sex.getCharacterInPosition(slotsTop.get(i));
				GameCharacter bottom = Main.sex.getCharacterInPosition(slotsBottom.get(i));
				
				if(penetrator.equals(top) && bottom!=null) {
					return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(bottom.getArmRows()*2)));
				}
				if(penetrator.equals(bottom)&& top!=null) {
					return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(top.getArmRows()*2)));
				}
			}
			
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
//			// Restrict targeting lying down oral and missionary if there's another person in the same position focused on them:
//			if(performer!=target) {
//				List<SexSlot> slotsLyingDown = Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR);
//				List<SexSlot> slotsPerformingOral = Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.MISSIONARY_ORAL_FOUR);
//				List<SexSlot> slotsMissionary = Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR);
//				for(int i=0; i<4; i++) {
//					if(Main.sex.getSexPositionSlot(target)!=slotsLyingDown.get(i) && (Main.sex.getSexPositionSlot(performer)==slotsPerformingOral.get(i) || Main.sex.getSexPositionSlot(performer)==slotsMissionary.get(i))) {
//						int targetIndex = 0;
//						for(int j=0; j<4; j++) {
//							if(Main.sex.getSexPositionSlot(target)==slotsLyingDown.get(j)) {
//								targetIndex = j;
//								break;
//							}
//						}
//						if(Main.sex.getSexPositionSlot(performer)==slotsPerformingOral.get(i) && Main.sex.getAllOccupiedSlots(false).containsValue(slotsPerformingOral.get(targetIndex))) {
//							return true;
//						}
//						if(Main.sex.getSexPositionSlot(performer)==slotsMissionary.get(i) && Main.sex.getAllOccupiedSlots(false).containsValue(slotsMissionary.get(targetIndex))) {
//							return true;
//						}
//					}
//				}
//			}
			
			// Restrict fucking breasts if cowgirl character is riding cock.
			if(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.COWGIRL) && Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(performer.equals(cowgirl)
							&& action.getPerformingCharacterAreas().contains(SexAreaPenetration.PENIS)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.BREAST) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE))
							&& (Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.VAGINA, target).contains(SexAreaPenetration.PENIS)
									|| Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.ANUS, target).contains(SexAreaPenetration.PENIS)
									|| Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.ASS, target).contains(SexAreaPenetration.PENIS))) {
						return true;
					}
				}
			}
			
			// Restrict vaginal actions if cowgirl is riding cock anally.
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.COWGIRL) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(target.equals(cowgirl)
							&& action.getTargetedCharacterAreas().contains(SexAreaOrifice.VAGINA)
							&& (Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.ANUS) || Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.ASS))) {
						return true;
					}
				}
			}
			
			// Restrict anal actions if reverse cowgirl is riding cock.
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.COWGIRL_REVERSE) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.COWGIRL_REVERSE_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(target.equals(cowgirl)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.ASS))
							&& Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.VAGINA)) {
						return true;
					}
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.LAP_PILLOW)
					|| Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.MATING_PRESS)
					|| Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.SIXTY_NINE)
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.SIXTY_NINE)))
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN_TWO
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.SIXTY_NINE_TWO)))
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN_THREE
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.SIXTY_NINE_THREE)))
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN_FOUR
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR)))) {
				return false;
			}
			return true;
		}
	};
	
	public static final AbstractSexPosition SITTING = new AbstractSexPosition("Сидя",
			8,
			true,
			Util.mergeLists(
					SexActionPresets.positioningActionsNew,
					Util.newArrayListOfValues(ChairSex.class)),
			new ArrayList<>()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));

			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL, SexSlotSitting.SITTING_BETWEEN_LEGS));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"Слот '"+Util.capitaliseSentence(slot.getDescription())+"' нельзя использовать, пока слот '"+Util.capitaliseSentence(e.getValue().getDescription())+"' уже занят "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
//			if(characterToTakeSlot.isTaur()
//					&& (slot==SexSlotSitting.SITTING
//							|| slot==SexSlotSitting.SITTING_TWO
//							|| slot==SexSlotSitting.SITTING_THREE
//							|| slot==SexSlotSitting.SITTING_FOUR)) {
//				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Due to the proportions of [npc.her] animalistic lower body, [npc.nameIsFull] unable to use the '"+Util.capitaliseSentence(slot.getDescription())+"' slot."));
//			}
			if(!characterToTakeSlot.isTaur()
					&& (slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Только персонажи с нижней частью тела дикого животного могут использовать '"+Util.capitaliseSentence(slot.getDescription())+"' не назначен персонаж."));
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitableSitting = false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotSitting.SITTING
						|| e.getValue()==SexSlotSitting.SITTING_TWO
						|| e.getValue()==SexSlotSitting.SITTING_THREE
						|| e.getValue()==SexSlotSitting.SITTING_FOUR) {
					suitableSitting = true;
				}
			}
			if(!suitableSitting) {
				return new Value<Boolean, String>(false, "Для этой позы как минимум один персонаж должен сидеть.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> sittingNames = new ArrayList<>();
			List<String> allInLapNames = new ArrayList<>();
			List<String> inLapNames = new ArrayList<>();
			List<String> inLapNamesTaur = new ArrayList<>();
			List<String> allBetweenLegsNames = new ArrayList<>();
			List<String> betweenLegsNames = new ArrayList<>();
			List<String> betweenLegsNamesTaur = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> taurPresentingNames = new ArrayList<>();

			GameCharacter mainSitting = null;
			GameCharacter mainInLap = null;
			GameCharacter mainInLapTaur = null;
			GameCharacter mainBetweenLegs = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainTaurPresenting = null;
			
			boolean playerSitting = false;
			boolean playerBetweenLegs = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotSitting.SITTING
							|| e.getValue()==SexSlotSitting.SITTING_TWO
							|| e.getValue()==SexSlotSitting.SITTING_THREE
							|| e.getValue()==SexSlotSitting.SITTING_FOUR) {
						if(mainSitting==null) {
							mainSitting=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerSitting = true;
							sittingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							sittingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotSitting.SITTING_IN_LAP
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_TWO
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_THREE
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_FOUR) {
						if(mainInLap==null && !e.getKey().isTaur()) {
							mainInLap=e.getKey();
						}
						if(mainInLapTaur==null && e.getKey().isTaur()) {
							mainInLapTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							allInLapNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								inLapNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								inLapNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allInLapNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								inLapNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								inLapNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_TWO
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_THREE
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR) {
						if(mainBetweenLegs==null) {
							mainBetweenLegs=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerBetweenLegs = true;
							allBetweenLegsNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								betweenLegsNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								betweenLegsNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allBetweenLegsNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								betweenLegsNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								betweenLegsNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotSitting.PERFORMING_ORAL
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else {
						if(mainTaurPresenting==null) {
							mainTaurPresenting=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							taurPresentingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							taurPresentingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			
			if(sittingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(sittingNames, false))+" сидят рядом друг с другом, пока "); 
				
			} else {
				sb.append(UtilText.parse(mainSitting, "[npc.Name] [npc.is] садится на стул, в то время как ")); 
			}
			
			
			// Sitting in lap:
			int sittingLapCount = allInLapNames.size();
			boolean sentenceContinuationFound = true;
			if(sittingLapCount>=2) {
				if(inLapNames.isEmpty()) {
					sb.append(Util.stringsToStringList(inLapNamesTaur, false)+ " разворачивается и делает шаг назад, прежде чем опустить свое дикое тело на "
					+(sittingNames.size()>1
							?(playerSitting?"твои колени. ":"их колени. ")
							:UtilText.parse(mainSitting, "колени [npc.her]. ")));
					
				} else {
						sb.append(Util.stringsToStringList(allInLapNames, false)+" делает шаг вперед, чтобы сесть на "
									+(sittingNames.size()>1
											?(playerSitting?"твои колени. ":"их колени. ")
											:UtilText.parse(mainSitting, "колени [npc.her]. ")));
						if(!inLapNamesTaur.isEmpty()) {
							sb.append(" Чтобы сделать это, "+Util.capitaliseSentence(Util.stringsToStringList(inLapNamesTaur, false))
								+" развернулись и отступили назад, позволяя своим диким телам прижаться к "
								+(sittingNames.size()>1
										?(playerSitting?"твоим пахам. ":"их пахам. ")
										:UtilText.parse(mainSitting, "паху [npc.her]. ")));
						}
				}
				
			} else if(inLapNames.size()==1) {
				sb.append(Util.stringsToStringList(inLapNames, false)+UtilText.parse(mainInLap, " [npc.has] шагнул вперед, чтобы сесть к тебе на ")
						+(sittingNames.size()>1
								?(playerSitting?"колени. ":"их колени. ")
								:UtilText.parse(mainSitting, "колени [npc.her]. ")));
				
			} else if(inLapNamesTaur.size()==1) {
				sb.append(Util.stringsToStringList(inLapNamesTaur, false)+UtilText.parse(mainInLapTaur, " [npc.has] развернулся и отступил назад, прежде чем опустить дикое тело [npc.legRace] [npc.her] на ")
				+(sittingNames.size()>1
						?(playerSitting?"твои колени. ":"их колени. ")
						:UtilText.parse(mainSitting, "колени [npc.her]. ")));
			} else {
				sentenceContinuationFound=false;
			}
			
			// Between legs:
			int betweenLegsCount = allBetweenLegsNames.size();
			if(betweenLegsCount>=2) {
				if(betweenLegsNames.isEmpty()) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNamesTaur, false)):Util.stringsToStringList(betweenLegsNamesTaur, false))
						+ " сделали шаг вперед, нависая над "
					+(sittingNames.size()>1
							?(playerSitting ?"вы" :"их")
							:UtilText.parse(mainSitting, "[npc.name]"))
					+", прижимая пахи "+(playerBetweenLegs?"твоей":"их")+" зверических нижних тел вплотную к "
					+(sittingNames.size()>1
							?(playerSitting ?"твоим. " :"их. ")
							:UtilText.parse(mainSitting, "[npc.hers]. ")));
					
				} else {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNames, false)):Util.stringsToStringList(betweenLegsNames, false))
							+" сделали шаг вперед, чтобы встать между "
								+(sittingNames.size()>1
										?(playerSitting?"твоими ногами":"их ногами")
										:UtilText.parse(mainSitting, "[npc.namePos] [npc.legs]")));
					if(!betweenLegsNamesTaur.isEmpty()) {
						sb.append(", в то время как "+Util.stringsToStringList(betweenLegsNamesTaur, false)+ " "+(betweenLegsNamesTaur.size()==1?UtilText.parse(mainBetweenLegs, "[npc.has]"):"имеют")+" сделали шаг вперед, нависая над "
								+(sittingNames.size()>1
										?(playerSitting ?"вы" :"их")
										:UtilText.parse(mainSitting, "[npc.name]"))
								+(betweenLegsNamesTaur.size()==1
									?UtilText.parse(mainBetweenLegs, ", прижимая пах [npc.her] звериного [npc.legRace] вплотную к ")
									:", прижимая пахи "+(playerBetweenLegs?"твоей":"их")+" зверических нижних тел вплотную к")
								+(sittingNames.size()>1
										?(playerSitting ?"твоим. " :"их. ")
										:UtilText.parse(mainSitting, "[npc.hers]. ")));
					} else {
						sb.append(".");
					}
				}
				sentenceContinuationFound = true;
				
			} else if(betweenLegsNames.size()==1) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNames, false)):Util.stringsToStringList(betweenLegsNames, false))
						+UtilText.parse(mainBetweenLegs, " [npc.has] шагнул(ла) вперед, чтобы встать между ")
						+(sittingNames.size()>1
								?(playerSitting?"твоими ногами. ":"их ногами. ")
								:UtilText.parse(mainSitting, "[npc.her] [npc.legs]. ")));
				sentenceContinuationFound = true;
				
			} else if(betweenLegsNamesTaur.size()==1) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNamesTaur, false)):Util.stringsToStringList(betweenLegsNamesTaur, false))
						+UtilText.parse(mainBetweenLegs, " [npc.has] шагнул(ла) вперед, перешагнув через ")
						+(sittingNames.size()>1
								?(playerSitting ?"вы" :"их")
								:UtilText.parse(mainSitting, "[npc.name]"))
						+UtilText.parse(mainBetweenLegs, ", прижимая пах [npc.her] звериного [npc.legRace] вплотную к ")
						+(sittingNames.size()>1
								?(playerSitting ?"твоим. " :"их. ")
								:UtilText.parse(mainSitting, "[npc.hers]. ")));
				sentenceContinuationFound = true;
			}
			
			// Performing oral:
			if(performingOralNames.size()>=2) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false)):Util.stringsToStringList(performingOralNames, false))
							+" расположены так, чтобы находиться между "
							+(sittingNames.size()>1
									?(playerSitting?"твоими ногами":"их ногами")+", готовая отсосать "+(playerSitting?"тобой. ":"ними. ")
									:UtilText.parse(mainSitting, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
					sentenceContinuationFound = true;
				
			} else if(performingOralNames.size()==1) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false)):Util.stringsToStringList(performingOralNames, false))
							+UtilText.parse(mainPerformingOral," [npc.is] расположена так, чтобы оказаться прямо между ")
					+(sittingNames.size()>1
							?(playerSitting?"твоими ногами":"их ногами")+", готовая отсосать "+(playerSitting?"тобой. ":"ними. ")
							:UtilText.parse(mainSitting, "[npc.namePos] [npc.legs], готовый отсосать [npc.herHim]. ")));
					sentenceContinuationFound = true;
			}

			
			// Receiving oral:
			if(taurPresentingNames.size()>=2) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(taurPresentingNames, false)):Util.stringsToStringList(taurPresentingNames, false))
						+" развернулись и отступили назад, чтобы подставить свои звериные гениталии "
						+(sittingNames.size()>1
								?(playerSitting?""+Util.intToString(sittingNames.size())+" тебя.":""+Util.intToString(sittingNames.size())+" их.")
								:UtilText.parse(mainSitting, "[npc.name].")));
			
				
			} else if(taurPresentingNames.size()==1) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(taurPresentingNames, false)):Util.stringsToStringList(taurPresentingNames, false))
							+UtilText.parse(mainTaurPresenting," [npc.has] развернулся(лась) и отступил(а) назад, чтобы подставить [npc.her] дикий зад к ")
					+(sittingNames.size()>1
							?(playerSitting?""+Util.intToString(sittingNames.size())+" тебя.":""+Util.intToString(sittingNames.size())+" их.")
							:UtilText.parse(mainSitting, "[npc.name].")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			// All performing oral can interact with one another and the sitting characters who don't have a sitting in lap
			
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			// Only not free if using the standing fucking position
			boolean performerFree1 = true;
			boolean performerFree2 = true;
			boolean performerFree3 = true;
			boolean performerFree4 = true;
			
			// Commented-out the interactions within if statements in v0.4.2.2, as having them be unavailable without someone in that slot was preventing NPCs from moving into those slots during sex
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING_BETWEEN_LEGS));
					performerFree1 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_BETWEEN_LEGS));
				performerFree1 = false;
			}

			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TWO));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
					performerFree2 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
				performerFree2 = false;
			}
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_THREE));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
					performerFree3 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
				performerFree3 = false;
			}
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_FOUR));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
					performerFree4 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
				performerFree4 = false;
			}
			
			// v0.4.2.2 added these from the commented-out ones above
			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_BETWEEN_LEGS));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TWO));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_THREE));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_FOUR));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
			
			
			
			// Sitting characters can kiss/use appendages on ones sitting next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_FOUR));
			
			if(performerFree1) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING));
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_TWO));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree2) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_TWO));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree3) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_THREE));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree4) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_FOUR));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_THREE));
				}
			}

			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character riding cock can use their body weight to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			// The character sitting getting fucked can use their legs, tail, or tentacles to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.SITTING_IN_LAP)
					|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL)))
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING_TWO
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO))
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING_THREE
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE))
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING_FOUR
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR))) {
				return false;
			}
			return true;
		}
	};
	
	
	
	
	//--- Unique and one-off sex scenes ---//
	
	public static final AbstractSexPosition BREEDING_STALL = new AbstractSexPosition("Стойло для спаривания",
			2,
			true,
			null, Util.newArrayListOfValues()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(characterToTakeSlot.isTaur() && (slot==SexSlotBreedingStall.BREEDING_STALL_BACK)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Из-за пропорций звериного низа тела [npc.her], [npc.nameIsFull] не может(ет) лечь на [npc.her] спину, чтобы его(е) осеменили."));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			boolean front = true;
			GameCharacter lyingDown = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_FRONT);
			if(lyingDown==null) {
				front = false;
				lyingDown = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_BACK);
			}
			
			if(lyingDown.isPlayer()) {
				return "Ты лежишь на своей "+(front?"front":"back")+" на мягкой скамье, при этом твои ноги и низ живота торчат из отверстия в стене, подставляя твою киску заводчикам по ту сторону."
						+ (Main.game.getPlayer().hasTail()
								?" Когда ты принимаешь нужную позу, кто-то по другую сторону стены пристегивает твои "
									+(Main.game.getPlayer().getTailCount()>1?"[pc.tailCount] [pc.tails] к стене с помощью металлических зажимов":" [pc.tail] к стене с помощью металлического зажима")
									+ ", чтобы ты не могла использовать"+(Main.game.getPlayer().getTailCount()>1?"them":"it")+" чтобы заблокировать твою [pc.pussy+]."
								:"");
			} else {
				GameCharacter character = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_FRONT);
				if(character!=null) {
					return "[npc.Name] лежит на [npc.her] "+(front?"front":"back")+" на мягкой скамье, при этом ноги [npc.her] и её низ живота торчат из отверстия в стене. Киска [npc.Her] полностью открыта тебе, готовая к осеменению."
							+ (character.hasTail()
									?" Когда [npc.she] принимает позу, Эпона делает шаг вперед и пристегивает [npc.her] "
										+(character.getTailCount()>1?"[npc.tailCount] [npc.tails] к стене с помощью металлических зажимов":" [npc.tail] к стене с помощью металлического зажима")
										+ ", чтобы не дать [npc.herHim] использовать"+(character.getTailCount()>1?"them":"it")+" чтобы заблокировать [npc.her] [npc.pussy+]."
									:"");
				} else {
					return "";
				}
			}
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.breedingStallFucking.getSexActionInteractions(SexSlotBreedingStall.BREEDING_STALL_FUCKING, SexSlotBreedingStall.BREEDING_STALL_FRONT));
			interactions.add(StandardSexActionInteractions.breedingStallFucking.getSexActionInteractions(SexSlotBreedingStall.BREEDING_STALL_FUCKING, SexSlotBreedingStall.BREEDING_STALL_BACK));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			boolean performerIsMother = Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_FRONT || Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_BACK;
			if((performerIsMother
					&& (action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.TAIL)
							|| ((action.getSexAreaInteractions().values().contains(SexAreaOrifice.VAGINA) || action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)) && action.getParticipantType()==SexParticipantType.SELF)))
				|| (Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_FUCKING
						&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.TAIL)
						&& action.getParticipantType()!=SexParticipantType.SELF)) {
				return true;
			}
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					PlayerTalk.PLAYER_OFFER_ANAL,
					PlayerTalk.PLAYER_OFFER_NAIZURI,
					PlayerTalk.PLAYER_OFFER_NIPPLE,
					PlayerTalk.PLAYER_OFFER_ORAL,
					PlayerTalk.PLAYER_OFFER_PAIZURI,
					!Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_OFFER_VAGINAL,
					PlayerTalk.PLAYER_REQUEST_ANAL,
					PlayerTalk.PLAYER_REQUEST_ORAL,
					Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_REQUEST_VAGINAL);
			if(blockedActions.contains(action)) {
				return true;
			}
			
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotBreedingStall.BREEDING_STALL_BACK
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotBreedingStall.BREEDING_STALL_FUCKING) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return false;
		}
	};
	

	
	public static final AbstractSexPosition GLORY_HOLE = new AbstractSexPosition("Оральный глорихоул",
			3,
			true,
			null, Util.newArrayListOfValues(GloryHole.class)) {
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					GenericOrgasms.GENERIC_PREPARATION_DENIAL,
					GenericActions.GENERIC_DENY,
					PlayerTalk.PLAYER_OFFER_ANAL,
					PlayerTalk.PLAYER_OFFER_NAIZURI,
					PlayerTalk.PLAYER_OFFER_NIPPLE,
					!Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_OFFER_ORAL,
					PlayerTalk.PLAYER_OFFER_PAIZURI,
					PlayerTalk.PLAYER_OFFER_VAGINAL,
					PlayerTalk.PLAYER_REQUEST_ANAL,
					Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_REQUEST_ORAL,
					PlayerTalk.PLAYER_REQUEST_VAGINAL);
			if(blockedActions.contains(action)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			List<GameCharacter> characters = new ArrayList<>();
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_KNEELING));
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			StringBuilder sb = new StringBuilder();

			boolean c1Taur = characters.get(1).isTaur();
			if(Main.sex.getTotalParticipantCount(false)==3) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
				
				boolean c2Taur = characters.get(2).isTaur();
				
				if(c1Taur || c2Taur) {
					sb.append("[npc.NameIsFull] стоит на коленях на полу, готовая обслужить любые гениталии, которые [npc2.name] и [npc3.name] просунут в отверстия глорихоула по обе стороны от [npc.herHim].");
					for(int i=1; i<3; i++) {
						GameCharacter character = characters.get(i);
						if(character.hasPenis() && character.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							sb.append(UtilText.parse(characters.get(0), character,
									"<br/><i>Поскольку [npc2.namePos] [npc2.cock+] расположился ниже нижней части тела [npc2.her] [npc2.legRace], [npc2.name] быстро [npc2.verb(discover)], что [npc2.sheIs] не сможет принять такую позу, чтобы [npc2.she] мог просунуть [npc2.her] член сквозь слайтхол... Вместо этого, [npc2.she] [npc2.has] довольствоваться тем, что развернется и подставит [npc2.her] [npc2.pussy+] к [npc.name]...</i>"));
							
						} else {
							sb.append(UtilText.parse(characters.get(0), character,
									"<br/><i>Из-за того, что [npc2.a_legRace] обладает нижней частью тела [npc2.name], [npc2.verb(make)] куча шума, пока [npc2.she] неуклюже [npc2.verb(turn)] в тесной стойле и [npc2.verb(present)] [npc2.her] [npc2.pussy+] к [npc.name]...</i>"));
						}
					}
					
				} else {
					if(characters.get(1).hasPenis() && characters.get(1).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
							&& characters.get(2).hasPenis() && characters.get(2).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						sb.append("[npc.NameIsFull] стоит на коленях на полу, готовая обслужить [npc2.namePos] [npc2.cock+] с одной стороны и [npc3.namePos] [npc3.cock+] с другой.");
					} else {
						sb.append("[npc.NameIsFull] стоит на коленях на полу, готовая обслужить любые гениталии, которые [npc2.name] и [npc3.name] просунут в отверстия глорихоула по обе стороны от [npc.herHim].");
					}
				}
				
			} else {
				if(c1Taur) {
					sb.append("[npc.NameIsFull] стоит на коленях на полу, прижавшись [npc.her] ртом к отверстию в стене, готовая обслужить любые гениталии, которыми [npc2.name] будет [npc2.verb(present)] [npc.herHim].");
					GameCharacter character = characters.get(1);
					if(character.hasPenis() && character.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						sb.append(UtilText.parse(characters.get(0), character,
								"<br/><i>Поскольку [npc2.namePos] [npc2.cock+] расположился ниже нижней части тела [npc2.her] [npc2.legRace], [npc2.name] быстро [npc2.verb(discover)], что [npc2.sheIs] не сможет принять такую позу, чтобы [npc2.she] мог просунуть [npc2.her] член сквозь слайтхол... Вместо этого, [npc2.she] [npc2.has] довольствоваться тем, что развернется и подставит [npc2.her] [npc2.pussy+] к [npc.name]...</i>"));
						
					} else {
						sb.append(UtilText.parse(characters.get(0), character,
								"<br/><i>Из-за того, что [npc2.a_legRace] обладает нижней частью тела [npc2.name], [npc2.verb(make)] куча шума, пока [npc2.she] неуклюже [npc2.verb(turn)] в тесной стойле и [npc2.verb(present)] [npc2.her] [npc2.pussy+] к [npc.name]...</i>"));
					}
					
				} else {
					sb.append("[npc.NameIsFull] стоит на коленях на floor, прижавшись [npc.her] ртом к отверстию в стене, готовая обслужить любые гениталии, которыми [npc2.name] будет [npc2.verb(present)] [npc.herHim].");
				}
			}
			
			return UtilText.parse(characters, sb.toString());
		}
		
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_KNEELING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_KNEELING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return false;
		}
	};
	
	public static final AbstractSexPosition GLORY_HOLE_SEX = new AbstractSexPosition("Секс через отверстие в стене",
			3,
			true,
			null, Util.newArrayListOfValues(GloryHole.class)) {
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					GenericOrgasms.GENERIC_PREPARATION_DENIAL,
					GenericActions.GENERIC_DENY);
			if(blockedActions.contains(action)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			List<GameCharacter> characters = new ArrayList<>();
			
			boolean analFucking = false;
			if(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKED)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKED));
			} else {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED));
				analFucking = true;
			}
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKING));
			
			StringBuilder sb = new StringBuilder();
			
			if(analFucking) {
				sb.append("[npc.NameIsFull] прижимает [npc.her] [npc.asshole+] к отверстию в стене позади [npc.herHim],");
			} else {
				sb.append("[npc.NameIsFull] прижимает [npc.her] [npc.pussy+] к отверстию в стене позади [npc.herHim],");
			}
			
			if(characters.get(1).hasPenis() && characters.get(1).isAbleToAccessCoverableArea(CoverableArea.PENIS, true) && !characters.get(1).isTaur()) {
				sb.append(" готовая к тому, чтобы [npc2.name] толкнулся вперед и погрузил [npc2.her] [npc2.cock+] внутрь.");
			} else {
				sb.append(" готовая к тому, чтобы [npc2.name] начал использовать её.");
			}

			if(Main.sex.getTotalParticipantCount(false)==3) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
				sb.append(" По другую сторону кабинки [npc.name] [npc.is] наклоняется, чтобы продолжить ласкать [npc3.namePos] через отверстие в стене с помощью [npc.her] рта.");
			}
			
			return UtilText.parse(characters, sb.toString());
		}
		
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.gettingFuckedGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_FUCKED, SexSlotUnique.GLORY_HOLE_FUCKING));
			interactions.add(StandardSexActionInteractions.gettingAnallyFuckedGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED, SexSlotUnique.GLORY_HOLE_FUCKING));
			
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_FUCKED, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return false;
		}
	};
	
	

	public static List<AbstractSexPosition> allSexPositions;
	
	public static Map<AbstractSexPosition, String> sexPositionToIdMap = new HashMap<>();
	public static Map<String, AbstractSexPosition> idToSexPositionMap = new HashMap<>();
	
	public static AbstractSexPosition getSexPositionFromId(String id) {
		id = Util.getClosestStringMatch(id, idToSexPositionMap.keySet());
		
		return idToSexPositionMap.get(id);
	}
	
	public static String getIdFromSexPosition(AbstractSexPosition perk) {
		return sexPositionToIdMap.get(perk);
	}

	static {
		allSexPositions = new ArrayList<>();
		
		// Hard-coded status effects:
		// SexPosition:
		Field[] fields = SexPosition.class.getFields();
		for(Field f : fields){
			if (AbstractSexPosition.class.isAssignableFrom(f.getType())) {
				
				AbstractSexPosition sexPosition;
				
				try {
					sexPosition = ((AbstractSexPosition) f.get(null));

					sexPositionToIdMap.put(sexPosition, f.getName());
					idToSexPositionMap.put(f.getName(), sexPosition);
					allSexPositions.add(sexPosition);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		// SexPositionUnique:
		fields = SexPositionUnique.class.getFields();
		for(Field f : fields){
			if (AbstractSexPosition.class.isAssignableFrom(f.getType())) {
				
				AbstractSexPosition sexPosition;
				
				try {
					sexPosition = ((AbstractSexPosition) f.get(null));

					sexPositionToIdMap.put(sexPosition, f.getName());
					idToSexPositionMap.put(f.getName(), sexPosition);
					allSexPositions.add(sexPosition);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractSexPosition> getAllSexPositions() {
		return allSexPositions;
	}
}
