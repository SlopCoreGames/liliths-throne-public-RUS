package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class DominionPark {
	
	public static final DialogueNode PARK = new DialogueNode("Парк", ".", false) {
		@Override
		public String getAuthor() {
			return "Кумико";
		}
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "<p>Эта часть Dominion занята гигантским парком, утопающим в пышной зелени, благодаря чему воздух здесь кажется невероятно свежим по сравнению с остальной частью города. Парк состоит из чередующихся открытых лужаек и лесных массивов, соединенных сетью извилистых тропинок. В одном углу парка расположено небольшое озеро, а рядом с ним — поле диких цветов. В одной из зон обустроено несколько киосков с едой для тех, кто не взял с собой пикник.</p><p>Самая примечательная особенность находится в самом центре парка и представляет собой огромную статую самой Lilith. Ее томная улыбка, высеченная в белом мраморе, кажется почти насмешливой, и невольно возникает чувство, что здесь не стоит задерживаться надолго.</p><p>Пока что у вас не так много причин бродить по этому парку, но если бы вы были здесь не одни, это было бы прекрасное место, чтобы провести день; если, конечно, вы сможете игнорировать статую...</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Розарий", "Справа от вас раскинулся прекрасный розарий. Подойдите поближе и рассмотрите его получше.", PARK_ROSE_GARDEN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", false), false));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PARK_ROSE_GARDEN = new DialogueNode("Парк", ".", false, true) {
		@Override
		public String getAuthor() {
			return "Инноксия";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return "<p>Ваше внимание привлекает небольшой розарий, расположенный рядом с входом в парк. Подойдя ближе, вы замечаете небольшую табличку прямо перед оградой, на которой написано:</p><p style='text-align:center;'><i><b>Розарий Уильяма</b><br/>Пожалуйста, угощайтесь этими розами! Надеюсь, вы или ваш партнер получите от них столько же радости, сколько получаю я, выращивая их.<br/>— Уильям</i></p><p>Вы оглядываетесь по сторонам, но не видите никого поблизости, кто мог бы быть этим самым Уильямом. Снова сосредоточившись на его розарии, вы решаете последовать совету на табличке, и, пройдя вперед [pc.stepping], срываете одну розу с ближайшего куста.</p>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Розарий", "Вы уже сорвали розу из этого сада.", null);
			} else {
				return null;
			}
		}
	};
	
	
	// Natalya encounter:
//	#IF(pc.getClothingInSlot(IS_NECK)!=null && pc.getClothingInSlot(IS_NECK).getClothingType().getId().equals('innoxia_neck_filly_choker'))
	
	public static final DialogueNode NATALYA_ENCOUNTER_START = new DialogueNode("Внезапно кентавры", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaParkEncounter, true);
			Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
				} else {
					Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
				}
				
			} else {
				Main.game.getNpc(Natalya.class).setPlayerKnowsName(false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_FILLY_START");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
				Natalya natalya = (Natalya) Main.game.getNpc(Natalya.class);
				if(index==1) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex(
								"Анилингус",
								UtilText.parse(natalya, "Спрыгивают позади Натальи и начинают делать ей римджоб."),
								true,
								false,
								// This sex manager should be suitable
								new SMDominionExpress(SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(natalya, SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
										Util.newHashMapOfValues(new Value<>(natalya, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
										Util.newHashMapOfValues(
												new Value<>(natalya, Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								AFTER_FILLY_SEX_ANAL,
								UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_FILLY_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), natalya, TongueAnus.ANILINGUS_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex(
								"Ручная стимуляция",
								UtilText.parse(natalya, "Опускаются на колени рядом с Натальей и начинают делать ей хендджоб."),
								true,
								false,
								// This sex manager should be suitable
								new SMDominionExpress(SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(natalya, SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
										Util.newHashMapOfValues(new Value<>(natalya, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER))),
										Util.newHashMapOfValues(
												new Value<>(natalya, Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								AFTER_FILLY_SEX_HANDJOB,
								UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_FILLY_HANDJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), natalya, FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						};
					}
				}
				
			} else {
				if(index==2) {
					return new Response("Уйти",
							"Вам больше не хочется на это смотреть, поэтому вы разворачиваетесь и продолжаете свой путь.<br/><i>Возможно, вы еще встретите этого персонажа где-нибудь еще...</i>",
								NATALYA_ENCOUNTER_END_EARLY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_START_END_EARLY"));
						}
					};
					
				} else if(index==1) {
					if(!Main.game.isAnalContentEnabled()) {
						return new Response("Наблюдать за ней",
								"Вам больше не хочется на это смотреть...<br/>[style.italicsMinorBad(Сцены с этим персонажем включают анальный секс, поэтому они будут отключены до тех пор, пока в настройках не будет включен параметр «Анальный контент».)]",
								null);
					}
					return new Response("Наблюдать за ней", "Наблюдать за кентаврой, пока она втирается своей анусом в край телеги...", NATALYA_ENCOUNTER_CAUGHT);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_CAUGHT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_CAUGHT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Уйти",
						"Вы не считаете, что обязаны извиняться перед этой кентаврицей, поэтому решаете просто развернуться и идти дальше.<br/><i>Возможно, вы встретите её снова где-нибудь ещё...</i>",
							NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_CAUGHT_END_EARLY"));
					}
				};
				
			} else if(index==1) {
				return new Response("Извиниться", "Сказать ей, что вам жаль.", NATALYA_ENCOUNTER_APOLOGY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_APOLOGY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_APOLOGY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Уйти",
						"Вы уже извинялись перед этой кентаврицей и не хотите делать это снова, поэтому решаете просто развернуться и идти дальше.<br/><i>Возможно, вы встретите её снова где-нибудь ещё...</i>",
						NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_APOLOGY_END_EARLY"));
					}
				};
				
			} else if(index==1) {
				return new Response("Следовать", "Сделать так, как говорит кентаврица, и последовать за ней в кусты.<br/>[style.italicsSex(У вас возникает предчувствие, что вы можете увидеть нечто весьма непристойное...)]", NATALYA_ENCOUNTER_BUSHES) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
						Main.game.getNpc(Natalya.class).setPlayerKnowsName(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES = new DialogueNode("Требование Натальи", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Уйти", "Развернуться и оставить Наталию доводить себя до оргазма без вашей помощи.", NATALYA_ENCOUNTER_END_EARLY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_END_EARLY"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==1) {
				return new Response("Подчиниться",
						"Исполнить приказ доминантной [natalya.race], а после того как назовете её «Госпожой», опуститься на колени позади неё и втиснуть дилдо в её пухлую дырочку...",
						NATALYA_ENCOUNTER_BUSHES_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalyaInPark, true); //TODO text variation in interview & Helena quest encounter
						((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Начать ласкать", "Исполнить приказ Госпожи Натальи и начать ласкать её толстый лошадиный член.",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return getForeplayPreference(character, targetedCharacter);
								}
								return character.getMainSexPreference(targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaOrifice.NIPPLE, SexAreaOrifice.BREAST));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaPenetration.FOOT));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return super.getCharacterPullOutOrgasmCumTarget(character, target);
							}
						},
						null,
						null,
						NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX = new DialogueNode("Завершено", "На данный момент Госпожа Наталья закончила с вами.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Последовать за госпожой Натальей, чтобы проверить, закончили ли её кентавры свою работу.", NATALYA_ENCOUNTER_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_BUSHES_SUBMIT_POST_SEX_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_END_EARLY = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return PARK.getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PARK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode NATALYA_ENCOUNTER_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false));
		}
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
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда госпожа Наталья ушла, вам остается только продолжать свой путь через парк...", PARK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_END_PARK"));
					}
				};
			}
			return null;
		}
	};
	
	// Mule:

	public static final DialogueNode AFTER_FILLY_SEX_ANAL = new DialogueNode("Завершено", "На данный момент Госпожа Наталья закончила с вами.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "AFTER_FILLY_SEX_ANAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Последовать за госпожой Натальей, чтобы проверить, закончили ли её кентавры свою работу.", NATALYA_ENCOUNTER_FILLY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "NATALYA_ENCOUNTER_FILLY_END"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_FILLY_SEX_HANDJOB = new DialogueNode("Завершено", "На данный момент Госпожа Наталья закончила с вами.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/park/natalya_encounter", "AFTER_FILLY_SEX_HANDJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_FILLY_SEX_ANAL.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode NATALYA_ENCOUNTER_FILLY_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Natalya.class).returnToHome();
		}
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
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда госпожа Наталья ушла, вам остается только продолжать свой путь через парк...", PARK);
			}
			return null;
		}
	};
}
