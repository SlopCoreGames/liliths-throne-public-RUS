package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class ZaranixDialogue {

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {
		@Override
		public String getDescription() {
			return "[zaranix.Name] полностью повержен, и с усталым стоном [zaranix.she] отступает, сдаваясь тебе...";
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Покинуть дом Зараникса.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Использовать Зараникса", "Немного развлечься с этим инкубом.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Zaranix.class)),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY_USE_HIM"));
				
			} else if(index==3) {
				return new ResponseSex("Подчиниться",
						"Позволить Зараникса трахать тебя.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Zaranix.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY_SUBMIT"));
				
			} else if (index == 4) {
				return new Response("Трансформации",
						"Заставить Зараникса использовать демонические силы [zaranix.her], чтобы трансформировать [zaranix.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Zaranix.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Продолжить", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_SEX_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить свое путешествие.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloorRepeat.resetHouseAfterLeaving();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Повержена", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Тебя вышвыривают", "Поскольку он не может добраться до твоего рта, Зараникс не может использовать тебя и просто выбрасывает на улицу.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_NO_MOUTH"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							ZaranixHomeGroundFloor.resetHouseAfterLeaving();
						}
					};
				}
				
			} else if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Отказаться",
								"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> фетиш: тебе так нравится трансформация, что ты не можешь заставить себя отказаться от преобразующей жидкости!",
								null);
					} else {
						return new Response("Отказаться", "Отказаться пить преобразующее зелье. Это приведет к тому, что Зараникс выбросит тебя на улицу.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_REFUSE"));
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
								ZaranixHomeGroundFloor.resetHouseAfterLeaving();
							}
						};
					}
					
				} else if(index==2) {
					return new Response("Выпить", "Согласиться выпить трансформирующее зелье.", AFTER_COMBAT_DEFEAT_SWALLOW);
				}
				
			} else {
				if(index==1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Сплюнуть",
								"Из-за твоего <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> фетиш: ты так обожаешь превращения, что даже не можешь заставить себя выплюнуть трансформирующую жидкость!",
								null);
					} else {
						return new Response("Сплюнуть", "Выплюнуть зелье.", AFTER_COMBAT_DEFEAT_SPIT);
					}
					
				} else if(index==2) {
					return new Response("Проглотить", "Проглотить зелье.", AFTER_COMBAT_DEFEAT_SWALLOW);
				}
			}
			
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SPIT = new DialogueNode("Повержена", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixTransformedPlayer, false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Повиноваться", "Зараникс заставляет тебя отсосать ему.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("С готовностью подчиниться", "Зараникс заставляет тебя отсосать ему.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex("Сопротивляться", "Зараникс заставляет тебя отсосать ему.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START_RESIST")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Zaranix.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SWALLOW = new DialogueNode("Повержена", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixTransformedPlayer, true);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Нужен... член...", "Зелье Зараникса подействовало весьма сильно... Тебе определенно нужно отсосать его член, может, тогда ты снова сможешь соображать здраво?",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			if(!Main.game.getPlayer().getSexualOrientation().isAttractedToMasculine()) {
				if(index==2) {
					return new Response("Женская форма?",
							"Тебе хочется отсосать член Зараникса, но тебя не особо привлекают мужчины. Может, если ты попросишь его вежливо, он использует свои демонические силы, чтобы превратиться в суккуба ради тебя?",
							AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF = new DialogueNode("Использовано", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF_START"));
			((Zaranix)Main.game.getNpc(Zaranix.class)).transformFeminine();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF_END"));
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
				return new ResponseSex("Нужен... член...", "Получив желаемое, ты теперь можешь думать только об одном — как бы посильнее отсосать член Зараникса...",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Использовано", "Зараникса уже тошнит от твоих навыков минета...", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_SEX_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Тебя вышвыривают", "Зараникс вышвырнул тебя на улицу.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloor.resetHouseAfterLeaving();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
