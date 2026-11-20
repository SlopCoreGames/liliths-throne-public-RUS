package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.submission.SMAxel;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class GamblingDenDialogue {
	
	private static final int REWARD_AMOUNT = 50_000;
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Вход", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_END_VENGAR_QUEST");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "ENTRANCE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				if(index==1) {
					return new Response("Продолжить", "[axel.Name] глубоко вздыхает и поворачивается к тебе.", AXEL_VENGAR_RESOLUTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(REWARD_AMOUNT));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Axel.class).setAffection(Main.game.getPlayer(), 40));
						}
					};
				}
					
			} else {
				if(index==1) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
						return new Response("Продолжить", "Отправиться исследовать Игорный притон.", ENTRANCE){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
							}
						};
						
					} else {
						return new Response("Уйти", "Толкнуть двери и выйти обратно в Submission.", SubmissionGenericPlaces.GAMBLING_DEN){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
								Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN);
							}
						};
					}
					
				} else if(index==2) {
					return new Response("[axel.name]", "Подойти к [axel.name] и поздороваться.", AXEL){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
						}
					};
				
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_RESOLUTION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_RESOLUTION", Main.game.getPlayer().getMainCompanion());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("Кабинет [axel.name]", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_AXEL = new DialogueNode("Кабинет [axel.name]", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE);
			Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE);
			for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
				companion.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL", Main.game.getPlayer().getMainCompanion());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				boolean attracted = Main.game.getNpc(Axel.class).isAttractedTo(Main.game.getPlayer());
				if(index==1) {
					if(!attracted) {
						return new Response("Доминировать", "[axel.name] не хочет заниматься с тобой сексом, так как [axel.she] привлекают только маскулинные люди.", null);
					}
					return new ResponseSex(
							"Доминировать",
							"Сказать [axel.name], что ты собираешься доминировать над [axel.herHim]...",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_LEXA_DOMINATE"));
					
					
				} else if(index==2) {
					if(!attracted) {
						return new Response("Подчиниться", "[axel.name] не хочет заниматься с тобой сексом, так как [axel.she] привлекают только маскулинные люди.", null);
					}
					return new ResponseSex(
							"Подчиниться",
							"Сказать [axel.name], что [axel.sheIs] сделает [axel.her] всё возможное, чтобы подчинить тебя...",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_LEXA_SUBMIT"));
					
					
				} else if(index==3) {
					// Cage on/off
					if(Main.game.getNpc(Axel.class).getClothingInSlot(InventorySlot.PENIS)!=null) {
						return new Response("Клетка: [style.colourMinorGood(Надета)]",
								"На [axel.Name] сейчас надета [axel.her] девственная клетка. Ты мог бы снять её с [axel.herHim], если захочешь...<br/>[style.italicsMinorGood(Вы можете свободно надевать и снимать клетку [axel.namePos] в любое время.)]",
								OFFICE_WITH_LEXA_CAGE) {
							@Override
							public void effects() {
								((Axel)Main.game.getNpc(Axel.class)).applyCage(false, Main.game.getPlayer());
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CAGE_OFF"));
							}
						};
						
					} else {
						return new Response("Клетка: [style.colourMinorBad(Не надета)]",
								"На [axel.Name] сейчас нет [axel.her] девственной клетки. Ты мог бы это исправить, если захочешь...<br/>[style.italicsMinorGood(Вы можете свободно надевать и снимать клетку [axel.namePos] в любое время.)]",
								OFFICE_WITH_LEXA_CAGE) {
							@Override
							public void effects() {
								((Axel)Main.game.getNpc(Axel.class)).applyCage(true, Main.game.getPlayer());
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CAGE_ON"));
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
						return new Response("Одежда",
								"Сказать [axel.name], что [axel.she] стоит сменить стиль одежды, которую выбирает [axel.she].",
								OFFICE_WITH_LEXA_CLOTHING);
					}
					return new Response("Феминизация",
							"Сказать [axel.name], что ты собираешься помочь [axel.herHim] реализовать [axel.her] полный потенциал и полностью феминизировать [axel.herHim].<br/>[style.italicsArcane(Это постоянная, необратимая трансформация!)]",
							OFFICE_WITH_LEXA_FEMINISE);
					
				} else if(index==7
						&& Main.game.getPlayer().isFeminine()
						&& !Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine()) {
					if(!Main.game.getPlayer().hasItemType(ItemType.ORIENTATION_HYPNO_WATCH)) {
						return new Response("Гипно-часы", "У тебя нет гипно-часов, поэтому ты не можешь сделать [axel.name] амбифилом...", null);
					}
					return new Response("Гипно-часы",
							"Использовать гипно-часы, чтобы сделать [axel.name] амбифилом.<br/>[style.italicsArcane(Это постоянное, необратимое изменение!)]",
							OFFICE_WITH_LEXA_HYPNO) {
						@Override
						public void effects() {
							Main.game.getNpc(Axel.class).setSexualOrientation(SexualOrientation.AMBIPHILIC);
						}
					};
					
				} else if(index==0) {
					return new Response("Уйти", "Решить ничего не делать с [axel.name] и вернуться в игорный притон.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL_LEAVE", Main.game.getPlayer().getMainCompanion()));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"Доминировать",
							"Сказать [axel.name], что ты собираешься доминировать над [axel.herHim]...",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_DOMINATE"));
					
					
				} else if(index==2) {
					return new ResponseSex(
							"Подчиниться",
							"Сказать [axel.name], что [axel.sheIs] сделает [axel.her] всё возможное, чтобы подчинить тебя...",
							true,
							true,
							new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
							},
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_SUBMIT"));
					
					
				} else if(index==6) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Отсосать", "Вы не можете дотянуться до своего рта, поэтому не можете сосать член [axel.namePos]...", null);
					}
					return new ResponseSex(
							"Отсосать",
							"Начать заниматься сексом с [axel.name]: опуститься на колени и начать сосать его член...",
							true,
							true,
							new SMAxel(
									SexPosition.STANDING,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newArrayListOfValues(CoverableArea.PENIS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ORAL_PERFORMING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							return list;
						}
					};
					
				} else if(index==7) {
					boolean cockAccess = Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
					boolean pussyAccess = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
					
					if(!cockAccess && !pussyAccess) {
						return new Response("Принять минет", "У вас нет гениталий, к которым [axel.name] мог бы прикоснуться, чтобы отсосать вам...", null);
					}
					return new ResponseSex(
							"Принять минет",
							cockAccess
								?"Начать заниматься сексом с [axel.name]: заставить его опуститься на колени и сосать ваш член..."
								:"Начать заниматься сексом с [axel.name]: заставить его опуститься на колени и отсосать вам...",
							true,
							true,
							new SMAxel(
									SexPosition.STANDING,
									cockAccess
										?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)
										:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									cockAccess
										?Util.newArrayListOfValues(CoverableArea.PENIS)
										:Util.newArrayListOfValues(CoverableArea.VAGINA),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ORAL_RECEIVING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							if(cockAccess) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							} else {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
							return list;
						}
					};
					
				} else if(index==8 && Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Заняться анилингусом", "Вы не можете дотянуться до своего рта, поэтому не можете отлизать анус [axel.name]...", null);
					}
					return new ResponseSex( 
							"Заняться анилингусом",
							"Начать заниматься сексом с [axel.name]: заставить его наклониться над столом, чтобы вы могли отлизать ему анус...",
							true,
							true,
							new SMAxel(
									SexPosition.OVER_DESK,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
									Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotDesk.OVER_DESK_ON_FRONT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.PERFORMING_ORAL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ANILINGUS_PERFORMING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerPenis.COCK_MASTURBATING_START, false, true));
							return list;
						}
					};
					
				} else if(index==9 && Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("Получить анилингус", "Вы не можете получить доступ к своему анусу, поэтому [axel.name] не может отлизать вам...", null);
					}
					return new ResponseSex(
							"Получить анилингус",
							"Начать заниматься сексом с [axel.name], заставив его делать тебе анилингус...",
							true,
							true,
							new SMAxel(
									SexPosition.AGAINST_WALL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
									Util.newArrayListOfValues(CoverableArea.MOUTH),
									Main.game.getPlayer().hasPenis()
										?Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)
										:(Main.game.getPlayer().hasVagina()
											?Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.VAGINA)
											:Util.newArrayListOfValues(CoverableArea.ANUS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Axel.class), SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL))),
							null,
							null,
							AFTER_AXEL_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "START_AXEL_ANILINGUS_RECEIVING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getNpc(Axel.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
							if(Main.game.getPlayer().hasPenis()) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerPenis.COCK_MASTURBATED_START, false, true));
							} else if(Main.game.getPlayer().hasVagina()) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Axel.class), FingerVagina.FINGERED_START, false, true));
							}
							return list;
						}
					};
					
				} else if(index==0) {
					return new Response("Уйти", "Решить ничего не делать с [axel.name] и вернуться в игорный притон.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_AXEL_LEAVE", Main.game.getPlayer().getMainCompanion()));
						}
					};
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_AXEL_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_LEXA_SEX");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Выйти из кабинета [axel.namePos] и вернуться в игорный притон.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_CAGE = new DialogueNode("", "", true) {
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
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_HYPNO = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_HYPNO");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Выпить (Без добавки)",
						"Заставить [axel.name] выпить зелье без добавки, что феминизирует [axel.herHim] и заставит член [axel.her] уменьшиться до крошечного клиторо-члена.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_ZERO"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenetrationGirth.TWO_NARROW, PenisLength.ZERO_MICROSCOPIC, TesticleSize.ZERO_VESTIGIAL, CumProduction.ONE_TRICKLE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Выпить (Половина добавки)",
						"Заставить [axel.name] выпить зелье и половину добавки, что феминизирует [axel.herHim], но оставит член [axel.her] среднего размера.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_HALF"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenetrationGirth.THREE_AVERAGE, PenisLength.TWO_AVERAGE, TesticleSize.TWO_AVERAGE, CumProduction.ONE_TRICKLE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==3) {
				return new Response("Выпить (Полная добавка)",
						"Заставить [axel.name] выпить зелье вместе с полной добавкой, что феминизирует [axel.herHim], но вернет член [axel.her] к прежним размерам.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_FULL"));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisation(PenetrationGirth.FIVE_THICK, PenisLength.FOUR_HUGE, TesticleSize.FOUR_HUGE, CumProduction.FOUR_LARGE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelFeminised, true);
					}
				};
				
			} else if(index==0) {
				return new Response("Назад",
						"Решить не феминизировать [axel.name] и выбрать другое занятие в кабинете [axel.her].",
						OFFICE_WITH_LEXA_FEMINISE_BACK_OUT);
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_BACK_OUT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_BACK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_APPLY = new DialogueNode("", "", true) {
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
			if(index==1) {
				return new Response("Согласиться",
						"Вы не видите причин не помочь [axel.name] выбрать, какую одежду надеть.",
						OFFICE_WITH_LEXA_FEMINISE_APPLY_CLOTHING);
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_APPLY_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_FEMINISE_APPLY_CLOTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Женственные",
						"Велеть [axel.name] начать носить женственные платья.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_FEMININE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==2) {
				return new Response("Горничная",
						"Велеть [axel.name] начать носить платье горничной.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_MAID"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==3) {
				return new Response("Шалава",
						"Велеть [axel.name] начать носить вызывающе откровенную одежду, чтобы все знали, какая [axel.sheIs] шлюха.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_WHORE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_WITH_LEXA_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingFeminine)) {
					return new Response("Женственные", "[axel.Name] уже носит женственное платье.", null);
				}
				return new Response("Женственные",
						"Велеть [axel.name] надеть женственное платье.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_FEMININE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, false);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingMaid)) {
					return new Response("Горничная", "[axel.Name] уже одета в форму горничной.", null);
				}
				return new Response("Горничная",
						"Велеть [axel.name] начать носить платье горничной.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_MAID"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, false);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelClothingWhore)) {
					return new Response("Шалава", "[axel.Name] уже одета как настоящая шлюха.", null);
				}
				return new Response("Шалава",
						"Велеть [axel.name] начать носить вызывающе откровенную одежду, чтобы все знали, какая [axel.sheIs] шлюха.",
						OFFICE_WITH_LEXA_FEMINISE_CLOTHING_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_WHORE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingFeminine, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingMaid, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelClothingWhore, true);
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==0) {
				return new Response("Передумать",
						"Решить не менять наряд [axel.name] и заняться чем-то другим в кабинете [axel.her].",
						OFFICE_WITH_LEXA_CLOTHING_BACK_OUT);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_WITH_LEXA_CLOTHING_BACK_OUT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "OFFICE_WITH_LEXA_CLOTHING_BACK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_WITH_AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL = new DialogueNode("Разговор с [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Дела", "Поговорить с [axel.name] о его делах.", AXEL_BUSINESS);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("Рокси", "Спросить [axel.name] о Рокси.", AXEL_ROXY) {
						@Override
						public void effects() {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_ROXY_REPEAT"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_ROXY"));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelMentionedVengar, true);
						}
					};
					
				} else {
					return new Response("Рокси", "Тебе нужно сначала поговорить с Рокси, прежде чем расспрашивать [axel.name] о ней.", null);
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelMentionedVengar) && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)) { // Initial asking/quest start:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelExplainedVengar)) {
						return new Response("Предложить помощь",
								"Сказать [axel.name], что ты хочешь помочь [axel.herHim] разобраться с Венгаром.",
								AXEL_VENGAR) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_SIDE;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_OFFER_HELP"));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_VENGAR));
							}
						};
						
					} else {
						return new Response("Венгар", "Спросить [axel.name] о Венгаре.", AXEL_VENGAR) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR"));
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelExplainedVengar, true);
							}
						};
					}
					
				} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)
						&& Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_TWO_COOPERATION) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelToldSubmit)) {
						return new Response("Навестить Венгара", "Сказать [axel.name], что ты готов отправиться вместе с [axel.herHim] навестить Венгара прямо сейчас.", AXEL_VENGAR_VISIT) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_SIDE;
							}
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
								Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
							}
						};
						
					} else {
						return new Response("Требование Венгара", "Рассказать [axel.name] о требовании Венгара.", AXEL_VENGAR_SUBMIT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelToldSubmit, true);
							}
						};
					}
					
				} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)
							|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelFeminised)) {
						return new Response("Кабинет", "Сказать Лексе, что ты хочешь провести приватную «дискуссию» с [lexa.herHim] в кабинете [lexa.her].", OFFICE_WITH_AXEL);
						
					} else {
						if(Main.game.getPlayer().isFeminine()) {
							return new Response("Кабинет", "Можешь сказать, что Акселя не интересуют такие женственные особы, как ты, и поэтому он вряд ли захочет провести с тобой время в своем кабинете.", null);
						}
						return new Response("Кабинет", "Спросить Акселя, не могли бы вы вдвоем провести приватную «дискуссию» в его кабинете о том, как сильно он благодарен тебе за помощь.", OFFICE_WITH_AXEL) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
				}
				
			} else if(index==0) {
				return new Response("Назад", "Попрощаться и выйти обратно в вестибюль.", ENTRANCE);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_BUSINESS = new DialogueNode("Разговор с [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_BUSINESS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Дела", "Вы уже разговариваете с [axel.name].", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_ROXY = new DialogueNode("Разговор с [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Рокси", "Вы уже обсуждаете Рокси с [axel.name].", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR = new DialogueNode("Разговор с [axel.name]", "", true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_SUBMIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Навестить Венгара", "Сказать [axel.name], что ты готов отправиться вместе с [axel.herHim] навестить Венгара прямо сейчас.", AXEL_VENGAR_VISIT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.QUEST_SIDE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				return new Response("Позже", "Сказать [axel.name], что ты пока не готова отправиться с [axel.herHim] на встречу к Венгару, и что ты дашь знать [axel.herHim], когда будешь готова.", AXEL_VENGAR);
				
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Размышлять о предупреждении Шэдоу, следуя за [axel.name] и Сайленс в зал Венгара.", AXEL_VENGAR_VISIT_KNEEL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Axel.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 30));
						
						Main.game.getNpc(Axel.class).unequipAllClothingIntoVoid(true, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_KNEEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Остановить [axel.name]", "Остановить [axel.name], чтобы он не выпил ром с зельем.", AXEL_VENGAR_VISIT_STOPPED);
				
			} else if(index==2) {
				return new Response("Промолчать",
						"Ничего не сказать, чтобы помешать [axel.name] выпить ром с зельем, позволяя [axel.herHim] превратиться в изнеженную версию самого себя.",
						AXEL_VENGAR_VISIT_SISSIFIED) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.ANDROGYNOUS;
					}
					@Override
					public void effects() {
						((Axel)Main.game.getNpc(Axel.class)).applySissification();
						((Axel)Main.game.getNpc(Axel.class)).applyCage(true, Main.game.getPlayer());
						((Axel)Main.game.getNpc(Axel.class)).setName(new NameTriplet("Лекса", "Лекса", "Лекса"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelSissified, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_STOPPED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_STOPPED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Вернуться в игорный притон вместе с [axel.name].", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_STOPPED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_SISSIFIED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Достаточно", "Не дать Венгару зайти слишком далеко и увести [axel.name] обратно в игорный притон.", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
				
			} else if(index==2) {
				return new Response("Смотреть", "Позволить Венгару зайти чуть дальше...", AXEL_VENGAR_VISIT_SISSIFIED_FINISH);
			}
			return null;
		}
	};

	public static final DialogueNode AXEL_VENGAR_VISIT_SISSIFIED_FINISH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Достаточно", "Сказать Венгару, что хватит, и увести [axel.name] обратно в игорный притон.", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isFeminine()) {
					return new Response("Трахнуть Лексу", "Похоже, Лексу не особо интересно заниматься сексом с кем-то настолько женственным, как ты...", null);
				}
				return new ResponseSex(
						"Трахнуть Лексу",
						"Сделай, как советует Венгар: трахни Лексу прямо на глазах у всех в зале.",
						true,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Axel.class), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Vengar.class),
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						null,
						AFTER_AXEL_SISSIFIED_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_PLAYER_FUCK"));
				
			} else if(index==3) {
				return new ResponseSex(
						"Наблюдать за Венгаром",
						"Присоединиться к остальным в зале и смотреть, как Венгар трахает «Лексу».",
						true,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Axel.class), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getPlayer(),
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						null,
						AFTER_AXEL_SISSIFIED_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_SISSIFIED_FINISH_VENGAR_FUCK"));
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_AXEL_SISSIFIED_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SISSIFIED_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Сопроводить Лексу обратно в игорный притон.", AXEL_VENGAR_VISIT_RETURN) {
					@Override
					public void effects() {
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AFTER_AXEL_SISSIFIED_SEX_RETURN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return ""; // Appended by lead-in dialogues
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Продолжать путь через туннели...",
						AXEL_VENGAR_VISIT_RETURN_NEXT);
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_NEXT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
			Main.game.getNpc(Silence.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_NEXT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сопровождение",
						"Enforcer сопровождает тебя и [axel.name] к ближайшему посту Enforcer...",
						AXEL_VENGAR_VISIT_RETURN_ENFORCERS);
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_ENFORCERS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).returnToHome();
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
			Main.game.getNpc(Axel.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 3*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_ENFORCERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Игорный притон",
						"Сопроводить [axel.name] обратно в игорный притон...",
						AXEL_VENGAR_VISIT_RETURN_ENFORCERS_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_ENFORCERS_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			RatWarrensDialogue.applyRatWarrensRaid();
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
			Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_ENFORCERS_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Награда",
						"Ждать, пока [axel.name] заберет твою награду из кабинета [axel.her].",
						AXEL_VENGAR_VISIT_RETURN_FINISH) {
					@Override
					public void effects() {
						((Shadow)Main.game.getNpc(Shadow.class)).moveToBountyHunterLodge();
						((Silence)Main.game.getNpc(Silence.class)).moveToBountyHunterLodge();
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(REWARD_AMOUNT));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Axel.class).setAffection(Main.game.getPlayer(), 40));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.SIDE_UTIL_COMPLETE));
						((Axel)Main.game.getNpc(Axel.class)).applyFeminisationCosmetics();
						Main.game.getNpc(Axel.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AXEL_VENGAR_VISIT_RETURN_FINISH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(REWARD_AMOUNT), true);
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "AXEL_VENGAR_VISIT_RETURN_FINISH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Игорный притон", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10) {
					return new Response("Игровой автомат ("+UtilText.formatAsMoney(10, "охват")+")", "У вас недостаточно денег, чтобы играть в игровые автоматы!", null);
				} else {
					return new Response("Игровой автомат ("+UtilText.formatAsMoney(10, "охват")+")", "Положите 10 огней в ближайший игровой автомат и потяните за рычаг.", SLOT_MACHINE) {
						@Override
						public void effects() {
							
							Map<AbstractSubspecies, Integer> slotMachineValues = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 5),
									new Value<>(Subspecies.IMP, 10),
									new Value<>(Subspecies.DOG_MORPH, 25),
									new Value<>(Subspecies.CAT_MORPH, 25),
									new Value<>(Subspecies.COW_MORPH, 50),
									new Value<>(Subspecies.DEMON, 100),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 500));
							
							Map<AbstractSubspecies, Integer> slotMachineValueProbabilities = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 16),
									new Value<>(Subspecies.IMP, 8),
									new Value<>(Subspecies.DOG_MORPH, 2),
									new Value<>(Subspecies.CAT_MORPH, 2),
									new Value<>(Subspecies.COW_MORPH, 2),
									new Value<>(Subspecies.DEMON, 1),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 1));
							
							Main.game.getTextEndStringBuilder().append(
									"<p>Решив испытать удачу за одним из игровых автоматов, вы подходите к ближайшему и опускаете десять огней в прорезь для монет.</p>"
									+ Main.game.getPlayer().incrementMoney(-10)
									+"<p>Потянув за рычаг, вы наблюдаете, как три барабана на передней панели стремительно вращаются, прежде чем медленно остановиться на трех картинках...</p>");
							

							Main.game.getTextEndStringBuilder().append("<div class='container-full-width'>");
							
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'><p style='width:100%'><b>Результаты автомата:</b></p>");
								
								List<AbstractSubspecies> races = new ArrayList<>(slotMachineValues.keySet());
								
								List<AbstractSubspecies> results = new ArrayList<>();
	
								boolean winner = false;
								if(Math.random()<0.32f) {
									AbstractSubspecies s = Util.getRandomObjectFromWeightedMap(slotMachineValueProbabilities);
									for(int i=0; i<3; i++) {
										results.add(s);
									}
									winner=true;
									
								} else {
									for(int i=0; i<3; i++) {
										AbstractSubspecies s = races.get(Util.random.nextInt(races.size()));
										results.add(s);
										if(i==0) {
											races.remove(s);
										}
									}
									Collections.shuffle(results);
								}
								
								for(AbstractSubspecies r : results) {
									Main.game.getTextEndStringBuilder().append(
											"<div class='modifier-icon' style='width:31.3%; margin:0 1%; border:3px solid "+(winner?PresetColour.GENERIC_EXCELLENT.toWebHexString():"")+"; display:inline-block;'><div class='modifier-icon-content'>"+r.getSVGString(null)+"</div>"
											+ "</div>");
								}
								if(winner) {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>[style.colourExcellent(Вы выиграли!)]<br/>Три "+results.get(0).getNamePlural(null)+" выплата "+UtilText.formatAsMoney(slotMachineValues.get(results.get(0)), "охват")+"!"
											+ "</p>");
								} else {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>[style.colourTerrible(Вы проиграли!)]</p>");
								}
								
								Main.game.getTextEndStringBuilder().append("</div>");
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+"<p style='text-align:center;'>");
							
									for(Entry<AbstractSubspecies, Integer> entry : slotMachineValues.entrySet()) {
										Main.game.getTextEndStringBuilder().append("<span style='color:"+entry.getKey().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getNamePlural(null))+"</span>: "
												+UtilText.formatAsMoney(entry.getValue(), "охват")+"<br/>");
									}
												
									Main.game.getTextEndStringBuilder().append("</p>");
								Main.game.getTextEndStringBuilder().append("</div>");
							Main.game.getTextEndStringBuilder().append("</div>");
								
							if(winner) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(slotMachineValues.get(results.get(0))));
								Main.game.getTextEndStringBuilder().append(
										"<p>Выиграв "+Util.intToString(slotMachineValues.get(results.get(0)))+" огни, вы раздумываете, стоит ли забрать деньги и отправиться дальше, или же сделать еще одну ставку в попытке выиграть снова...</p>");
							} else {
								Main.game.getTextEndStringBuilder().append(
										"<p>Проиграв десять огней, вы раздумываете, стоит ли смириться с потерей и отправиться дальше, или же сделать еще одну ставку в попытке выиграть...</p>");
							}
						}
					};
				}
			}
			return null;
		}
	};
	

	public static final DialogueNode SLOT_MACHINE = new DialogueNode("Игорный притон", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
		
	};
	
	
	public static final DialogueNode GAMBLING = new DialogueNode("Столы для костей", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==gamblers.size()+1){
				return new Response("Правила", "Взгляните на стоящую неподалеку табличку с правилами игры в костяной покер.", GAMBLING_RULES);
				
			} else {
				try {
					gamblers.sort((g1, g2) -> ((GamblingDenPatron) g1).getTable().compareTo(((GamblingDenPatron) g2).getTable()));
				} catch(Exception ex) {
				}
				
				if(index-1<gamblers.size()) {
					NPC gambler = gamblers.get(index-1);
					DicePokerTable table = 
							(gambler instanceof GamblingDenPatron && ((GamblingDenPatron) gambler).getTable()!=null)
								?((GamblingDenPatron) gambler).getTable()
								:DicePokerTable.COPPER;
					int buyIn = table.getInitialBet()+table.getRaiseAmount();
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly(
								"<span style='color:"+table.getColour().toWebHexString()+";'>"+UtilText.parse(gambler, "[npc.Name]")+"</span> ("+UtilText.formatAsMoney(buyIn, "охват")+")",
								UtilText.parse(gambler,
										"Начать игру в костяной покер с [npc.name]. Взнос составляет "+UtilText.formatAsMoney(table.getInitialBet(), "охват")
										+", но вам также понадобятся "+UtilText.formatAsMoney(table.getRaiseAmount(), "охват")+" для любых повышений ставок.")) {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, GAMBLING, "misc/dicePoker")));
							}
						};
						
					} else {
						return new Response(gambler.getName(true)+" ("+UtilText.formatAsMoneyUncoloured(buyIn, "охват")+")",
								"Взнос составляет "+UtilText.formatAsMoney(table.getInitialBet(), "охват")
								+", но вам также понадобятся "+UtilText.formatAsMoney(table.getRaiseAmount(), "охват")+" на любые повышения ставок. В итоге у тебя не хватает денег, чтобы играть за этим столом!",
								null);
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode GAMBLING_RULES = new DialogueNode("Столы для костей", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Дочитав правила, вы отходите от знака.", GAMBLING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MALE_STALLS = new DialogueNode("Стойла для разведения самцов", "", false) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_MALE_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_FUTA_STALLS = new DialogueNode("Загоны для фута-разведения", "", false) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_FUTA_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
