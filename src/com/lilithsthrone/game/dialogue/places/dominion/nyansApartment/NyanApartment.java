package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanApartment {

	private static GameCharacter activeSexPartner;
	
	public static GameCharacter getActiveSexPartner() {
		if(activeSexPartner==null) {
			return getNyan();
		}
		return activeSexPartner;
	}

	public static void setActiveSexPartner(GameCharacter activeSexPartner) { //TODO remember to call this...
		NyanApartment.activeSexPartner = activeSexPartner;
	}

	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}
	
	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode VISIT_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "VISIT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
			}
			return null;
		}
	};
	
	public static final DialogueNode HALLWAY = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "HALLWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "ENTRANCE_HALL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode NYAN_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "NYAN_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENSUITE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "ENSUITE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SPARE_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SPARE_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode BATHROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "BATHROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode KITCHEN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "KITCHEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DINING_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DINING_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SOLO_SEX_FOREPLAY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			responses.add(
				new ResponseSex("Куннилингус", "Дать похотливой кошкодевочке то, чего она хочет, отсосав ей.",
					true, true,
					new SMNyanSex(
							SexPosition.SITTING,
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL)),
							Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotSitting.SITTING))) {
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.isPlayer()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
							}
						}
						@Override
						public boolean isCharacterStartNaked(GameCharacter character) {
							return character.isPlayer();
						}
					},
					null,
					null,
					SOLO_SEX_MAIN,
					UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					return Util.newArrayListOfValues(
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
				}
			});
			
			responses.add(
				new ResponseSex("Фингеринг", UtilText.parse(getActiveSexPartner(), "Повалить [npc.name] на кровать и ласкать её пальцами, одновременно целуя."),
					true, true,
					new SMNyanSex(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO)),
							Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.isPlayer()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
							}
						}
						@Override
						public boolean isCharacterStartNaked(GameCharacter character) {
							return character.isPlayer();
						}
					},
					null,
					null,
					SOLO_SEX_MAIN,
					UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_FINGERING" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					return Util.newArrayListOfValues(
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueMouth.KISS_START, false, true),
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), FingerVagina.FINGERING_START, false, true));
				}
			});
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (минет)":""),
							UtilText.parse(getActiveSexPartner(), "Так как вы не можете дотянуться до своего члена, у вас не получится заняться сексом в позе шестьдесят девять с [npc.name]!"),
							null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (минет)":""), "Из-за строения нижней части тела [pc.legRace], вы не можете использовать эту позу!", null));
							
				} else {
					responses.add(
							new ResponseSex("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (минет)":""),
									UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] сесть верхом на ваше лицо и наклониться, чтобы она сосала ваш член, пока вы ласкаете её языком."),
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_SIXTY_NINE_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasPenis()?" (куннилингус)":""),
							UtilText.parse(getActiveSexPartner(), "Поскольку вы не можете дотянуться до своей киски, вы не можете заняться сексом в стиле шестьдесят девять с [npc.name]!"),
							null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (куннилингус)":""), "Из-за строения нижней части тела [pc.legRace], вы не можете использовать эту позу!", null));
							
				} else {
					responses.add(
							new ResponseSex("Шестьдесят девять"+(Main.game.getPlayer().hasPenis()?" (куннилингус)":""),
									UtilText.parse(getActiveSexPartner(), "Заставьте [npc.name] сесть вам на лицо и наклониться, чтобы она отсосала вам, пока вы будете ласкать её губами."),
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_SIXTY_NINE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Получить минет",
							UtilText.parse(getActiveSexPartner(), "Поскольку вы не можете дотянуться до своего члена, вы не можете заставить [npc.name] отсосать вам!"),
							null)); 
				} else {
					responses.add(
							new ResponseSex("Получить минет",
									UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] отсосать ваш член."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "[npc.Paizuri]"),
							UtilText.parse(getActiveSexPartner(), "Поскольку вы не можете дотянуться до своего члена, вы не можете заставить [npc.name] отсосать вам между грудей!"),
							null)); 
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "[npc.Paizuri]"),
									UtilText.parse(getActiveSexPartner(),
											getActiveSexPartner().isBreastFuckablePaizuri()
												?"Заставьте [npc.name] опуститься на колени и прижать её [npc.breasts+] к вашему члену, чтобы вы могли заняться тит-факом."
												:"Заставьте [npc.name] опуститься на колени и прижать её [npc.breasts+] к вашему члену, чтобы вы могли тереться об них."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.BREAST);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.NIPPLES)));
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_PAIZURI" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisBreasts.FUCKING_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Принять куннилингус",
							UtilText.parse(getActiveSexPartner(), "Поскольку вы не можете дотянуться до своей киски, вы не можете заставить [npc.name] отсосать вам!"),
							null)); 
				} else {
					responses.add(
							new ResponseSex("Принять куннилингус",
									UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] отсосать вам!"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_RECEIVE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			responses.add(
					new ResponseSex("Фейсситтинг",
							UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] сесть вам на лицо и ласкать её губами."), 
							true, true,
							new SMNyanSex(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.FACE_SITTING))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.isPlayer();
								}
							},
							null,
							null,
							SOLO_SEX_MAIN,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_FACE_SITTING" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});

			if(Main.game.isAnalContentEnabled()) {
				if((getActiveSexPartner() instanceof Nyan) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
						responses.add(new Response("Спросить об анальном сексе", "Спросить Nyan, будет ли ей комфортно заниматься анальным сексом с тобой.", SOLO_FOREPLAY_ANAL_TALK));
					
				} else if((getActiveSexPartner() instanceof NyanMum) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Спросить об анальном сексе", "Спросить [nyanmum.name], будет ли ей комфортно заниматься анальным сексом с тобой.", SOLO_FOREPLAY_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Заняться анилингусом",
								"Заставить возбужденную кошкодевочку подставить тебе свою задницу и начать её лизать.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return true;
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								SOLO_SEX_MAIN,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_PERFORM_ANILINGUS"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
				}
			}
			if(index > 0 && index - 1 < responses.size()) {
				return responses.get(index - 1);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_FOREPLAY_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(getActiveSexPartner() instanceof Nyan) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_ANAL_TALK"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SOLO_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SOLO_SEX_MAIN = new DialogueNode("Идти дальше", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getActiveSexPartner(), "[npc.Name] уже достаточно прелюдий...");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Миссионерская", UtilText.parse(getActiveSexPartner(), "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть [npc.name]!"), null)); 
					
				} else {
					responses.add(
							new ResponseSex("Миссионерская",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"Заставить Nyan лечь на спину и открыться тебе, прежде чем лишить её девственности."
										:UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] лечь на спину и открыться тебе, прежде чем трахнуть её."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_MISSIONARY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Догги-стайл", UtilText.parse(getActiveSexPartner(), "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть [npc.name]!"), null)); 
					
				} else {
					responses.add(
							new ResponseSex("Догги-стайл",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"Заставить Nyan встать на четвереньки и подставить себя вам, прежде чем лишить её девственности."
										:UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] встать на четвереньки и подставить себя вам, прежде чем трахнуть её."),
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_DOGGY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Наездница", UtilText.parse(getActiveSexPartner(), "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть [npc.name]!"), null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Наездница", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Наездница",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"Лишить Nyan девственности, заставив её оседлать ваш член."
										:UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] оседлать ваш член в позе наездницы."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.COWGIRL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_COWGIRL" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getActiveSexPartner(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Получить минет", "Поскольку у вас нет доступа к пенису, вы не можете сосать член!", null)); 
				} else {
					responses.add(
							new ResponseSex("Получить минет", UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] отсосать ваш член."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getActiveSexPartner(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Миссионерский секс ножницами ([npc.name] снизу)"),
							UtilText.parse(getActiveSexPartner(), "Поскольку у вас нет доступа к влагалищу, вы не можете заниматься сексом ножницами с [npc.name]!"),
							null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Миссионерский секс ножницами ([npc.name] снизу)"), "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null));
					
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "Миссионерский секс ножницами ([npc.name] снизу)"),
									UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] лечь на спину и заняться с ней ножницами."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_SCISSOR_TOP" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), ClitClit.TRIBBING_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Ножницы ([npc.name] сверху)"), UtilText.parse(getActiveSexPartner(), "Поскольку у вас нет доступа к влагалищу, вы не можете заниматься сексом ножницами с [npc.name]!"), null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Ножницы ([npc.name] сверху)"), "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null));
					
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "Ножницы ([npc.name] сверху)"),
									UtilText.parse(getActiveSexPartner(), "Лечь на спину и заставить [npc.name] заняться с вами ножницами."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.SCISSORING))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_SCISSOR_BOTTOM" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), ClitClit.TRIBBING_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Принять куннилингус", "Так как вы не можете получить доступ к своей вагине, вы не можете принимать куннилингус!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Принять куннилингус",
									UtilText.parse(getActiveSexPartner(), "Заставить [npc.name] отсосать вам!"),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_RECEIVE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				responses.add(
						new ResponseSex("Куннилингус",
							"Заставить похотливую кошкодевочку подставить вам свою киску и отсосать ей.",
							true, true,
							new SMNyanSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return true;
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.FULL;
									}
									return SexControl.ONGOING_ONLY;
								}
							},
							null,
							null,
							POST_SOLO_SEX,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_PERFORM_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if((getActiveSexPartner() instanceof Nyan) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
						responses.add(new Response("Спросить об анальном сексе", "Спросить Nyan, будет ли ей комфортно заниматься анальным сексом с тобой.", SOLO_SEX_ANAL_TALK));
					
				} else if((getActiveSexPartner() instanceof NyanMum) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Спросить об анальном сексе", "Спросить [nyanmum.name], будет ли ей комфортно заниматься анальным сексом с тобой.", SOLO_SEX_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Заняться анилингусом",
								"Заставить возбужденную кошкодевочку подставить тебе свою задницу и начать её лизать.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return true;
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								POST_SOLO_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_PERFORM_ANILINGUS"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
					if(Main.game.getPlayer().hasPenis()) {
						if(!penisAccess) {
							responses.add(new Response("Анал", UtilText.parse(getActiveSexPartner(), "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть [npc.name]!"), null)); 
							
						} else {
							responses.add(
									new ResponseSex("Анал",
											getActiveSexPartner() instanceof Nyan && getNyan().isAssVirgin()
												?"Заставить Nyan встать на четвереньки и подставиться перед тем, как лишить её девственности в анусе."
												:"Заставить [pc.name] встать на четвереньки и подставиться перед тем, как оттрахать ей задницу.",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
													} else {
														return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return true;
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_SOLO_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_ANAL" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisAnus.PENIS_FUCKING_START, false, true));
										}
									});
						}
					}
				}
			}
			
			if(index > 0 && index - 1 < responses.size()) {
				return responses.get(index - 1);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_SEX_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(getActiveSexPartner() instanceof Nyan) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_ANAL_TALK"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SOLO_SEX_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getActiveSexPartner(), "[npc.Name] удовлетворена и прекращает секс...");
		}
		@Override
		public void applyPreParsingEffects() {
			getActiveSexPartner().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, getNyan().hasStatusEffect(StatusEffect.PREGNANT_0));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Спать", UtilText.parse(getActiveSexPartner(), "Дать [npc.name] отдохнуть и лечь спать вместе с ней в её постель."), POST_SOLO_SEX_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
				getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			}
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_SLEEP" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Кухня", "Направиться на кухню...", POST_SOLO_SEX_KITCHEN);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX_KITCHEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().wearCasual();
			}
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyan(), getNyan(), null), true);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyanMum(), getNyanMum(), null), true);
			}
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_KITCHEN"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM")));
//			if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied) && getNyan().isPregnant())
//					|| (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied) && getNyanMum().isPregnant())) {
//				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "PREGNANCY_ADDITION"));
//			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Прощай",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
							?"Поцеловать Nyan и [nyanmum.name] на прощание и сказать, что скоро увидишься с ними."
							:"Поцеловать Nyan на прощание и сказать, что скоро увидишься с ней.",
						POST_SEX_MORNING_NO_CONTENT) {
					@Override
					public void effects() {
						getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_LEAVE"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM")));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SEX_MORNING_NO_CONTENT = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			}
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_SEX_FOREPLAY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumCreampied, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(index==1) {
				return new ResponseSex("Куннилингус", "Заставить похотливых кошкодевочек выставить тебе свои киски и отсосать обеим.",
						true, true,
						new SMNyanSex(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else if(character instanceof Nyan) {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								} else {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
						},
						null,
						null,
						DOUBLE_SEX_MAIN,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
								new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Фингеринг", "Повалить похотливых кошкодевочек на кровать [nyanmum.namePos] и начать ласкать их пальцами.",
						true, true,
						new SMNyanSex(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
						},
						null,
						null,
						DOUBLE_SEX_MAIN,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Получить минет", "Поскольку у вас нет доступа к пенису, вы не можете сосать член!", null)); 
				} else {
					responses.add(
							new ResponseSex("Получить минет", "Заставить Nyan и [nyanmum.name] отсосать твой член.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_TWO:SexSlotSitting.PERFORMING_ORAL_TWO))) {
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return partner instanceof NyanMum && Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									DOUBLE_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_RECEIVE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Принять куннилингус", "Так как вы не можете получить доступ к своей вагине, вы не можете принимать куннилингус!", null)); 
				} else {
					responses.add(
							new ResponseSex("Принять куннилингус", "Заставить Nyan и [nyanmum.name] отлизать тебя.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return partner instanceof NyanMum && Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									DOUBLE_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Спросить об анальном сексе", "Спросить Nyan и [nyanmum.name], не против ли они заняться с тобой анальным сексом.", DOUBLE_FOREPLAY_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Заняться анилингусом", "Заставить похотливых кошкодевочек выставить тебе свои задницы и начать их риммить.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
												new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else if(character instanceof Nyan) {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										} else {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										}
									}
									@Override
									public boolean isPartnerWantingToStopSex(GameCharacter partner) {
										return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return character.isPlayer();
									}
								},
								null,
								null,
								DOUBLE_SEX_MAIN,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true),
										new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
							}
						});
				}
			}
			
			if(index > 0 && index - 3 < responses.size()) {
				return responses.get(index - 3);
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_FOREPLAY_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOUBLE_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_SEX_MAIN = new DialogueNode("Идти дальше", "Похоже, Nyan и [nyanmum.name] на данный момент сыты по горло прелюдиями...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Миссионерская поза (Nyan)", "Поскольку у тебя нет доступа к члену, ты не можешь трахать Nyan или [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Миссионерская поза (Nyan)",
									getNyan().isVaginaVirgin()
										?"Заставь Nyan и [nyanmum.name] лечь на кровать и открыться тебе, прежде чем лишить Nyan девственности."
										:"Заставь Nyan и [nyanmum.name] лечь на кровать и открыться тебе, прежде чем проникнуть в киску Nyan.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_MISSIONARY_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyanMum(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Миссионерская поза ([nyanmum.name])", "Поскольку у тебя нет доступа к члену, ты не можешь трахать Nyan или [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Миссионерская поза ([nyanmum.name])", "Заставь Nyan и [nyanmum.name] лечь на кровать и открыться тебе, прежде чем проникнуть в [nyanmum.namePos] [nyanmum.pussy+].",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_MISSIONARY_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyan(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Догги-стайл (Nyan)", "Поскольку у тебя нет доступа к члену, ты не можешь трахать Nyan или [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Догги-стайл (Nyan)",
									getNyan().isVaginaVirgin()
										?"Заставить Nyan и [nyanmum.name] встать на четвереньки и подставиться под вас, прежде чем лишить Nyan девственности."
										:"Заставить Nyan и [nyanmum.name] встать на четвереньки и подставиться под вас, прежде чем войти в киску Nyan.",
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
													new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_DOGGY_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyanMum(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Догги-стайл ([nyanmum.name])", "Поскольку у тебя нет доступа к члену, ты не можешь трахать Nyan или [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Догги-стайл ([nyanmum.name])", "Заставить Nyan и [nyanmum.name] встать на четвереньки и подставиться под вас, прежде чем проникнуть в [nyanmum.namePos] [nyanmum.pussy+].",
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
													new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_DOGGY_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyan(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Наездница (Nyan)", "Поскольку у тебя нет доступа к члену, ты не можешь трахать Nyan или [nyanmum.name]!", null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Наездница (Nyan)", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Наездница (Nyan)",
									getNyan().isVaginaVirgin()
										?"Лиши Nyan девственности, позволив ей скакать на твоем члене, пока [nyanmum.name] сидит у тебя на лице."
										:"Заставь Nyan скакать на твоем члене, пока [nyanmum.name] сидит у тебя на лице.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.COWGIRL),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_COWGIRL_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Наездница ([nyanmum.name])", "Поскольку у тебя нет доступа к члену, ты не можешь трахать Nyan или [nyanmum.name]!", null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Наездница ([nyanmum.name])", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Наездница ([nyanmum.name])", "Заставь [nyanmum.name] скакать на твоем члене, пока Nyan сидит у тебя на лице.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.COWGIRL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_COWGIRL_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Получить минет", "Поскольку у вас нет доступа к пенису, вы не можете сосать член!", null)); 
				} else {
					responses.add(
							new ResponseSex("Получить минет", "Заставить Nyan и [nyanmum.name] отсосать твой член.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Ножницы (Nyan снизу)", "Так как ты не можешь дотянуться до своей вагины, ты не можешь заняться ножницами с Nyan!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Ножницы (Nyan снизу)", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null));
					
				} else {
					responses.add(
							new ResponseSex("Ножницы (Nyan снизу)", "Заставить Nyan лечь на спину и заняться с ней ножницами, пока [nyanmum.name] сидит у неё на лице.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSOR_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Ножницы ([nyanmum.name] снизу)", "Так как вы не можете получить доступ к своей вагине, вы не можете заняться ножницами с [nyanmum.name]!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Ножницы ([nyanmum.name] снизу)", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null));
					
				} else {
					responses.add(
							new ResponseSex("Ножницы ([nyanmum.name] снизу)", "Заставить [nyanmum.name] лечь на спину и заняться с ней ножницами, пока [nyan.name] сидит у неё на лице.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSOR_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyanMum(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Ножницы (Nyan сверху)", "Так как ты не можешь дотянуться до своей вагины, ты не можешь заняться ножницами с Nyan!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Ножницы (Nyan сверху)", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null));
					
				} else {
					responses.add(
							new ResponseSex("Ножницы (Nyan сверху)", "Лечь на спину и заставить Nyan заняться с вами ножницами, пока [nyanmum.name] сидит у вас на лице.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.SCISSORING),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSORED_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Ножницы ([nyanmum.name] сверху)", "Так как вы не можете получить доступ к своей вагине, вы не можете заняться ножницами с [nyanmum.name]!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Ножницы ([nyanmum.name] сверху)", "Из-за положения нижней части тела [pc.legRace] вы не можете использовать эту позу...", null));
					
				} else {
					responses.add(
							new ResponseSex("Ножницы ([nyanmum.name] сверху)", "Лечь на спину и заставить Nyan заняться с вами ножницами, пока [nyanmum.name] сидит у вас на лице.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.SCISSORING))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSORED_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Принять куннилингус", "Так как вы не можете получить доступ к своей вагине, вы не можете принимать куннилингус!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Принять куннилингус", "Заставить Nyan и [nyanmum.name] отлизать тебя.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE);
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				responses.add(
						new ResponseSex("Куннилингус", "Заставить похотливых кошкодевочек выставить тебе свои киски и отсосать обеим.",
							true, true,
							new SMNyanSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else if(character instanceof Nyan) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
										}
									} else {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
										}
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.FULL;
									}
									return SexControl.ONGOING_ONLY;
								}
							},
							null,
							null,
							POST_DOUBLE_SEX,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
						}
					});
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Спросить об анальном сексе", "Спросить Nyan и [nyanmum.name], не против ли они заняться с тобой анальным сексом.", DOUBLE_SEX_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Заняться анилингусом", "Заставить похотливых кошкодевочек выставить тебе свои задницы и начать их риммить.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
												new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else if(character instanceof Nyan) {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										} else {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								POST_DOUBLE_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true),
										new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
							}
						});
					
					if(Main.game.getPlayer().hasPenis()) {
						if(!penisAccess) {
							responses.add(new Response("Анал (Nyan)", "Поскольку вы не можете дотянуться до своего члена, вы не можете трахнуть Nyan в зад!", null)); 
							
						} else {
							responses.add(
									new ResponseSex("Анал (Nyan)",
											getNyan().isAssVirgin()
												?"Заставить Nyan и [nyanmum.name] встать на четвереньки и подставить вам свои зады, прежде чем лишить Nyan девственности в анусе."
												:"Заставить Nyan и [nyanmum.name] встать на четвереньки и подставить вам свои зады, прежде чем войти в анус Nyan.",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
															new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														if(targetedCharacter instanceof Nyan) {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														}
													} else if(character instanceof Nyan) {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													} else {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_DOUBLE_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANAL_NYAN")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisAnus.PENIS_FUCKING_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyanMum(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
										}
									});
						}
						if(!penisAccess) {
							responses.add(new Response("Анал ([nyanmum.name])", "Поскольку вы не можете дотянуться до своего члена, вы не можете трахнуть [nyanmum.namePos] в зад!", null)); 
							
						} else {
							responses.add(
									new ResponseSex("Анал ([nyanmum.name])",
											"Заставьте Nyan и [nyanmum.name] встать на четвереньки и подставить свои задницы, прежде чем войти в [nyanmum.namePos] анус.",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
															new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														if(targetedCharacter instanceof Nyan) {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														}
													} else if(character instanceof Nyan) {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													} else {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_DOUBLE_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANAL_NYANMUM")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisAnus.PENIS_FUCKING_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
													new InitialSexActionInformation(getNyan(), getNyan(), SelfFingerVagina.SELF_FINGER_VAGINA_PENETRATION, false, true));
										}
									});
						}
					}
				}
			}
			if(index==0 && responses.size()>14) {
				return responses.get(14);
			}
			if(index > 0) {
				if(index>14) {
					if(index < responses.size()) {
						return responses.get(index);
					}
				} else if(index - 1 < responses.size()) {
					return responses.get(index - 1);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_SEX_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOUBLE_SEX_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX = new DialogueNode("Завершено", "Nyan и [nyanmum.name] удовлетворены, и секс заканчивается...", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			getNyanMum().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, getNyan().hasStatusEffect(StatusEffect.PREGNANT_0));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumCreampied, getNyanMum().hasStatusEffect(StatusEffect.PREGNANT_0));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Спать", "Дайте кошкодевочкам отдохнуть и ложитесь спать вместе с ними.", POST_DOUBLE_SEX_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
				getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			}
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Кухня", "Направиться на кухню...", POST_DOUBLE_SEX_KITCHEN);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX_KITCHEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().wearCasual();
			}
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyanMum(), getNyanMum(), null), true);
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyan(), getNyan(), null), !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied));
			} 
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_KITCHEN"));
//			if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied) && getNyan().isPregnant())
//					|| (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied) && getNyanMum().isPregnant())) {
//				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "PREGNANCY_ADDITION"));
//			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Прощай", "Поцеловать Nyan и [nyanmum.name] на прощание и сказать, что скоро увидишься с ними.", POST_SEX_MORNING_NO_CONTENT) {
					@Override
					public void effects() {
						getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyanMum().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_LEAVE"));
					}
				};
				
			}
			return null;
		}
	};
}
