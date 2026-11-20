package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FootMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.4
 * @author Innoxia
 */
public class KaysWarehouse {
	
	private static final int PAY_OFF_PRICE = 50_000;
	private static final int PAY_OFF_PRICE_WOLFGANG_SHARE = 30_000;
	private static final int PAY_OFF_PRICE_KARL_SHARE = 20_000;

	/**
	 * Sets quest progress to RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN and appends to the TextEndStringBuilder.
	 * <br/>Moves Wolfgang and Karl to the bounty hunter lodge.
	 */
	public static void applySuppliersBeatenEffects() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN));
		((SupplierLeader)Main.game.getNpc(SupplierLeader.class)).moveToBountyHunterLodge();
		((SupplierPartner)Main.game.getNpc(SupplierPartner.class)).moveToBountyHunterLodge();
	}
	
	/** The amount of flames the dobermann-boys give to you if you demonically intimidate them. */
	private static final int DEMONIC_PAYOFF = 6_000;
	
	public static boolean isPlayerMouthFree() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	public static boolean isPlayerAssFree() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true);
	}
	
	public static boolean isPlayerPenisFree() {
		return Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
	}
	
	public static boolean isPlayerVaginaFree() {
		return Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}
	
	public static boolean isPlayerNippleFuckFree() {
		return Main.game.getPlayer().hasBreasts() && Main.game.getPlayer().isBreastFuckableNipplePenetration() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true);
	}
	
	public static boolean isSexAvailable() {
		return isPlayerMouthFree() || isPlayerAssFree() || isPlayerVaginaFree();
	}
	
	public static ResponseSex getDobermannsSexResponse(AbstractSexPosition position,
			SexSlot slotWolfgang, SexAreaInterface sexAreaWolfgang,
			SexSlot slotKarl, SexAreaInterface sexAreaKarl,
			SexSlot slotPlayer,
			String title, String description, DialogueNode postSexDialogue, String startingText) {
		
		SexType sexTypeWolfgang = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, sexAreaWolfgang);
		SexType sexTypeKarl = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, sexAreaKarl);
		
		String fuckingText = "Вольфганг трахает твою " +sexTypeWolfgang.getTargetedSexArea().getName(Main.game.getPlayer())+" пока Карл трахает твою " +sexTypeKarl.getTargetedSexArea().getName(Main.game.getPlayer());
		if(sexTypeWolfgang.getTargetedSexArea()==sexTypeKarl.getTargetedSexArea()) {
			fuckingText = "и Вольфганг, и Карл трахают твою " + sexTypeWolfgang.getTargetedSexArea().getName(Main.game.getPlayer());
		}
		if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
			fuckingText = "Вольфганг трахает твое лицо, пока Карл заставляет тебя делать ему рукоблудие";
		}
		
		return new ResponseSex(title,
				description
					+"<br/>[style.italicsSex(Это приведет к тому, что "+fuckingText+"!)]",
				true, false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(SupplierLeader.class), slotWolfgang),
								new Value<>(Main.game.getNpc(SupplierPartner.class), slotKarl)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), slotPlayer))) {
						@Override
						public boolean isPositionChangingAllowed(GameCharacter character) {
							return false;
						}
						@Override
						public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
							return new ArrayList<>();
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(
									new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
									new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
									new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.NIPPLE
												?CoverableArea.NIPPLES
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.MOUTH
												?CoverableArea.MOUTH
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.ANUS
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.ANUS
												?CoverableArea.ANUS
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.VAGINA
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.VAGINA
												?CoverableArea.VAGINA
												:null)));
						}
						@Override
						public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(!character.isPlayer()) {
								return character.getMainSexPreference(targetedCharacter);
							}
							return super.getForeplayPreference(character, targetedCharacter);
						}
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.equals(Main.game.getNpc(SupplierLeader.class))) {
								return sexTypeWolfgang;
							}
							if(character.equals(Main.game.getNpc(SupplierPartner.class))) {
								return sexTypeKarl;
							}
							return super.getMainSexPreference(character, targetedCharacter);
						}
					},
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				startingText) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				List<InitialSexActionInformation> initialActions = new ArrayList<>();
				
				boolean sameTarget = sexTypeWolfgang.getTargetedSexArea()==sexTypeKarl.getTargetedSexArea();
				
				if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisNipple.PENIS_FUCKING_START, false, true));
				}
				
				if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), sameTarget?PenisVagina.PENIS_FUCKING_START_ADDITIONAL:PenisVagina.PENIS_FUCKING_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), sameTarget?PenisAnus.PENIS_FUCKING_START_ADDITIONAL:PenisAnus.PENIS_FUCKING_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaPenetration.FINGER) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), PenisNipple.PENIS_FUCKING_START, false, true));
				}
				
				return initialActions;
			}
		};
	}
	
	private static Response getDobermannsRewardSexResponse(int startIndex, int index, String title) {
		if(index == startIndex) {
			title = title.replaceAll("sex_type_replacement", "oral");
			
			if(!isPlayerMouthFree()) {
				return new Response(title,
						"Так как вы не можете разжать рот, вы не можете предложить доберман-мальчикам отсосать...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.SITTING,
						SexSlotSitting.SITTING, SexAreaOrifice.MOUTH,
						SexSlotSitting.SITTING, SexAreaPenetration.FINGER,
						SexSlotSitting.PERFORMING_ORAL,
						title,
						"Предложить отсосать доберман-мальчикам...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_ORAL"));
			}
			
		} else if(index == startIndex+1) {
			title = title.replaceAll("sex_type_replacement", "spit-roast");
			
			if(!isPlayerMouthFree()) {
				return new Response(title,
						"Так как вы не можете разжать рот, вы не можете предложить доберман-мальчикам устроить вам спит-рост...",
						null);
				
			} else if(!isPlayerAssFree() && !isPlayerVaginaFree()) {
				return new Response(title,
						"Так как вы не можете открыть задницу"+(Main.game.getPlayer().hasVagina()?" или киску":"")+", вы не можете предложить доберман-мальчикам устроить вам спит-рост...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.ALL_FOURS,
						SexSlotAllFours.BEHIND, isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotAllFours.IN_FRONT, SexAreaOrifice.MOUTH,
						SexSlotAllFours.ALL_FOURS,
						title,
						"Предложить двум доберман-мальчикам устроить вам спит-рост...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_SPITROAST"));
			}
			
		} else if(index == startIndex+2) {
			title = title.replaceAll("sex_type_replacement", "ride");
			
			if(!isPlayerAssFree() && !isPlayerVaginaFree()) {
				return new Response(title,
						"Так как вы не можете открыть задницу"+(Main.game.getPlayer().hasVagina()?" или киску":"")+", вы не можете предложить оседлать доберман-мальчиков...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.LYING_DOWN,
						SexSlotLyingDown.LYING_DOWN, isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotLyingDown.MISSIONARY, isPlayerAssFree()?SexAreaOrifice.ANUS:SexAreaOrifice.VAGINA,
						SexSlotLyingDown.COWGIRL,
						title,
						"Предложить двум мальчикам-доберманнам подвезти их...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_RIDE"));
			}
			
		} else if(index == startIndex+3 && Main.game.isNipplePenEnabled()) {
			title = title.replaceAll("sex_type_replacement", "nipple-fuck");
			
			if(!isPlayerNippleFuckFree()) {
				return new Response(title,
						Main.game.getPlayer().isBreastFuckableNipplePenetration()
							?"Так как вы не можете добраться до своих сочных сосков, вы не можете предложить их мальчикам-доберманнам..."
							:"Так как у вас нет сочных сосков, вы не можете предложить их мальчикам-доберманнам...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT, SexAreaOrifice.NIPPLE,
						SexSlotStanding.STANDING_DOMINANT_TWO, SexAreaOrifice.NIPPLE,
						SexSlotStanding.PERFORMING_ORAL,
						title,
						"Предложить мальчикам-доберманнам оттрахать ваши соски...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_NIPPLES"));
			}
		}
		return null;
	}
	
	private static ResponseSex getKaySexResponse(String title,
			String description,
			DialogueNode postSexDialogue,
			String startingText,
			AbstractSexPosition position,
			SexSlot slotKay, SexType sexTypeKay,
			SexSlot slotPlayer,
			List<CoverableArea> kayExposedParts,
			List<CoverableArea> playerExposedParts,
			List<InitialSexActionInformation> initialActions) {
		return getKaySexResponse(title,
				description,
				postSexDialogue,
				startingText,
				position,
				slotKay,
				sexTypeKay,
				slotPlayer,
				kayExposedParts,
				playerExposedParts,
				initialActions,
				null);
	}

	/** @param startingWetAreas Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	private static ResponseSex getKaySexResponse(String title,
			String description,
			DialogueNode postSexDialogue,
			String startingText,
			AbstractSexPosition position,
			SexSlot slotKay, SexType sexTypeKay,
			SexSlot slotPlayer,
			List<CoverableArea> kayExposedParts,
			List<CoverableArea> playerExposedParts,
			List<InitialSexActionInformation> initialActions,
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> startingWetAreas) {
		return new ResponseSex(title,
				description,
				true, false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), slotPlayer)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Kay.class), slotKay))) {
						@Override
						public SexPace getForcedSexPace(GameCharacter character) {
							if(character==Main.game.getNpc(Kay.class)) {
								return SexPace.SUB_EAGER; // Lock Kay into being eager, as if they were to fall into resisting, the scenes don't account for that
							}
							return null;
						}
						@Override
						public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
							return clothingToEquip.isCondom(); // Do not let sex clothing be equipped onto Kay, as they have special actions for this and it would otherwise break the flow of their scenes
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(
									new Value<>(Main.game.getNpc(Kay.class), kayExposedParts),
									new Value<>(Main.game.getPlayer(), playerExposedParts));
						}
						@Override
						public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(sexTypeKay!=null) {
								return character.getMainSexPreference(targetedCharacter);
							}
							return super.getForeplayPreference(character, targetedCharacter);
						}
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(sexTypeKay==null) {
								return super.getMainSexPreference(character, targetedCharacter);
							}
							if(character.equals(Main.game.getNpc(Kay.class))) {
								return sexTypeKay;
							} else {
								return sexTypeKay.getReversedSexType();
							}
						}
						@Override
						public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
							if(startingWetAreas==null) {
								return super.getStartingWetAreas();
							}
							return startingWetAreas;
						}
					},
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				startingText) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				return initialActions;
			}
		};
	}
	
	
	public static final DialogueNode INITIAL_ENTRY = new DialogueNode("Зона ресепшена", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "INITIAL_ENTRY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
					return new Response("Выход", "Решить пока покинуть склад.", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
						}
					};
					
				} else {
					return new Response("Регистратор", "Подойди к регистратору и скажи ей, что ты пришел к Кею.", RECEPTIONIST_UNLOCKING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode RECEPTIONIST_UNLOCKING = new DialogueNode("Зона ресепшена", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "RECEPTIONIST_UNLOCKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return INITIAL_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode RECEPTION = new DialogueNode("Зона ресепшена", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
				return INITIAL_ENTRY.getContent();
			}
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "RECEPTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return INITIAL_ENTRY.getResponse(responseTab, index);}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Коридор", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STORAGE_AREA = new DialogueNode("Складская зона", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
					return new Response("Излишки", "Ты не думаешь, что это хорошая идея — без разрешения брать вещи из ящика «Утилизация излишков»...", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)) {
					return new Response("Излишки", "Вы уже забрали сегодняшний излишек товара!", null);
					
				} else {
					return new Response("Излишки", "Возьмите сегодняшний излишек товара.", STORAGE_AREA_SEARCHING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayCratesSearched, true);
							
							List<AbstractClothingType> clothingToGenerate = new ArrayList<>(ClothingType.getAllClothing());
							clothingToGenerate.removeIf((clothing) -> clothing.getRarity()!=Rarity.COMMON || !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN) || clothing.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN));
							
							Main.game.getTextEndStringBuilder().append(
									Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), false), false)
									+ Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false)
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false):"")
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false):"")
							        + UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA_SEARCHED"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STORAGE_AREA_SEARCHING = new DialogueNode("Склад", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA_SEARCHING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STORAGE_AREA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WEAVING_MACHINES = new DialogueNode("Ткацкие станки", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "WEAVING_MACHINES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode OVERSEER_STATION = new DialogueNode("Пост надзирателя", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OVERSEER_STATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
					return new Response("офис [kay.NamePos]", "Поднимитесь по лестнице, пройдите через пост надзирателя и навестите [kay.name].", OFFICE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OFFICE, false);
							Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else {
					return new Response("Наверх", "Поднимитесь по лестнице и войдите на пост надзирателя.", DOBERMANNS) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP)==Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS));
							}
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Развернуться", "[pc.Step] от лестницы и спуститесь обратно по коридору.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS = new DialogueNode("Пост надзирателя", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(SupplierLeader.class).setPlayerKnowsName(true);
			Main.game.getNpc(SupplierPartner.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Уйти", "Теперь, когда эти доберманы-мальчишки приняли вас за Enforcer, лучше бы уладить этот конфликт, прежде чем пытаться уйти...", null);
				}
				if(Main.game.getPlayer().getRace()==Race.DEMON) {
					return new Response("Уйти", "Теперь, когда доберман-мальчики увидели тебя в твоей демонической форме, лучше всего разрешить этот конфликт, прежде чем пытаться уйти...", null);
				}
				return new Response("Уйти", "[pc.Step] отбиться от доберман-мальчиков и вернуться тем же путем, которым ты пришел.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_LEAVE"));
					}
				};
				
			} else if(index == 1) {
				if(Main.game.getPlayer().getMoney()<PAY_OFF_PRICE) {
					return new Response("Выплатить ("+UtilText.formatAsMoneyUncoloured(PAY_OFF_PRICE, "охват")+")",
							"У тебя недостаточно огней, чтобы предложить выплату по контракту доберман-мальчиков...",
							null);
					
				} else {
					return new Response("Выплатить ("+UtilText.formatAsMoney(PAY_OFF_PRICE, "охват")+")",
							"Предложить выплатить остаток контракта доберман-мальчиков, что вместе с колким напоминанием об их незаконности должно заставить их уйти.",
							DOBERMANNS_PAID_OFF) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatPaid, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-PAY_OFF_PRICE));
							Main.game.getNpc(SupplierLeader.class).incrementMoney(PAY_OFF_PRICE_WOLFGANG_SHARE);
							Main.game.getNpc(SupplierPartner.class).incrementMoney(PAY_OFF_PRICE_KARL_SHARE);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Блеф с Enforcer", "Использовать тот факт, что доберман-мальчики приняли тебя за Enforcer, чтобы убедить их уйти.", DOBERMANNS_ENFORCER_BLUFF) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatEnforcer, true);
						}
					};
					
				} else {
					return new Response("Блеф с Enforcer", "Чтобы попытаться это сделать, на тебе должна быть форма Enforcer!", null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().getRace()!=Race.DEMON
						&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
					if(!Main.game.getPlayer().isAbleToSelfTransform()) {
						return new Response("Демоническое разоблачение", "Поскольку твои способности к самотрансформации сейчас подавлены, ты не можешь превратиться в демона на глазах у парней-доберманов, чтобы запугать их и заставить уйти!", null);
					} else {
						return new Response("Демоническое разоблачение",
								"Превратиться в демона перед парнями-доберманами и запугать их, чтобы они ушли.",
								DOBERMANNS_DEMON_REVEAL) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatDemon, true);
							}
						};
					}
					
				} else {
					return new Response("Демоническое запугивание",
							"Использовать тот факт, что ты демон, чтобы запугать парней-доберманов и заставить их уйти.",
							DOBERMANNS_DEMONIC_INTIMIDATION,
							null,
							null,
							null,
							null,
							Util.newArrayListOfValues(Subspecies.DEMON)) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatDemon, true);
						}
					};
				}
				
			} else if(index==4) {
				if(!isSexAvailable()) {
					return new Response("Соблазнить", "Так как вы не можете добраться ни до одного из своих отверстий, вы не в состоянии соблазнить мальчиков-доберманнов...", null);
				}
				return new Response("Соблазнить",
						"Соблазнить парней-доберманов и предложить им свое тело в обмен на то, что они оставят Кей в покое.",
						DOBERMANNS_SEDUCE,
						null,
						null,
						Util.newArrayListOfValues(Perk.MALE_ATTRACTION, Perk.OBJECT_OF_DESIRE),
						null,
						null) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatSeduced, true);
					}
				};
				
			} else if(index==5) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Сразиться", "Было бы лучше попытаться сыграть на том, что парни-доберманы приняли тебя за Enforcer...", null);
					
				} else {
					return new ResponseCombat("Сразиться", "Немедленно вступить в бой!",
							Main.game.getNpc(SupplierLeader.class),
							Util.newArrayListOfValues(
									Main.game.getNpc(SupplierLeader.class),
									Main.game.getNpc(SupplierPartner.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Скоро я вышвырну вас обоих отсюда,)] — заявляешь ты, готовясь к драке, — [pc.speech(но я знаю, что такие, как вы, уважают только силу, так что у меня нет другого выбора, кроме как сделать это!)]"),
									new Value<>(Main.game.getNpc(SupplierLeader.class), "[wolfgang.speech(Ха!)] — кричит Wolfgang, — [wolfgang.speech(Если ты хочешь драки, мы ее тебе устроим!)]"),
									new Value<>(Main.game.getNpc(SupplierPartner.class), "[karl.speech(Ты за это заплатишь, сучка!)] — рычит Karl."))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS_PAID_OFF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(PAY_OFF_PRICE), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_PAID_OFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Отпустить их", "Отойди в сторону и дай двум доберманам уйти.", DOBERMANNS_BANISHED);
				
			} else {
				return getDobermannsRewardSexResponse(2, index, "\"Поблагодарить\" их (sex_type_replacement)");
			}
		}
	};
	
	public static final DialogueNode DOBERMANNS_ENFORCER_BLUFF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_ENFORCER_BLUFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DOBERMANNS_DEMON_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(DEMONIC_PAYOFF));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(DEMONIC_PAYOFF), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_DEMON_REVEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOBERMANNS_DEMONIC_INTIMIDATION = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(DEMONIC_PAYOFF));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(DEMONIC_PAYOFF), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_DEMONIC_INTIMIDATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOBERMANNS_SEDUCE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEDUCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDobermannsRewardSexResponse(1, index, "Предложить sex_type_replacement");
		}
	};
	
	public static final DialogueNode DOBERMANNS_COMBAT_PLAYER_VICTORY = new DialogueNode("Победа", "Два добермана повержены!", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatCombat, true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отпустить их", "Отойди в сторону и дай двум доберманам уйти.", DOBERMANNS_BANISHED);
				
			} else if (index == 2) {
				return new ResponseSex("Трахнуть их",
						"Повалить Вольфганга и Карла рядом друг с другом в позу догги-стайл, готовясь хорошенько развлечься с ними...",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_VICTORY_FUCK_THEM"));
				
			} else {
				return getDobermannsRewardSexResponse(3, index, "Подчиниться (sex_type_replacement)");
			}
		}
	};
	
	public static final DialogueNode DOBERMANNS_COMBAT_PLAYER_LOSS = new DialogueNode("Повержена", "Два добермана оказались тебе не по зубам...", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Трахнутый",
						"Совершенно измотанная и окончательно побежденная, ты не в силах оказать сопротивление, пока два добермана готовятся тебя оттрахать...",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
						},
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED"));
				
			} else if(index == 2) {
				return new ResponseSex("Жадно оттраханная",
						"Возбужденная тем, как властно тебя поставили на место, ты не можешь не умолять двух доберманов оттрахать тебя...",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY, ResponseTag.START_PACE_PLAYER_SUB_EAGER) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
						},
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED_EAGER"));
				
			} else if(index==3) {
				if(!Main.game.isNonConEnabled()) {
					return new Response("Отказаться", "Пусть ты и проиграла, но ты не позволишь этим двум громилам просто так взять свое!", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_THROWN_OUT"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
						}
					};
					
				} else {
					return new ResponseSex("Сопротивляться",
							"Несмотря на полное изнеможение и сокрушительное поражение, ты изо всех сил стараешься сопротивляться, пока два доберман-парня готовятся изнасиловать тебя...",
							null, null, null, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.PREFER_DOGGY, ResponseTag.START_PACE_PLAYER_SUB_RESISTING) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
							},
							AFTER_SEX_FUCKED,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED_RESIST"));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_WILLING = new DialogueNode("Завершено", "Двум доберман-парням уже всё надоело...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_WILLING_DOMMED_THEM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_WILLING");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отпустить их", "Отойди в сторону и дай двум доберманам уйти.", DOBERMANNS_BANISHED);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_FUCKED = new DialogueNode("Завершено", "Два доберман-парня вдоволь натешались тобой...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_FUCKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Тебя вышвыривают", "Карл бесцеремонно вышвыривает тебя из склада.", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_FUCKED_THROWN_OUT"));
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS_BANISHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applySuppliersBeatenEffects();
			Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).setAffection(Main.game.getPlayer(), 25));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_BANISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Следовать", "Последовать за возбужденным Кеем в его кабинет.", KAY_SAVED_OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OFFICE, false);
						Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_SAVED_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_SAVED_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE"));
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Kay.class), true);
			}
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
			if(index==0) {
				return new Response("Уйти", "Выйти из кабинета [kay.namePos] и вернуться в коридор склада.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_LEAVE"));
					}
				};
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayTalkedTo)) {
					return new Response("Светская беседа", "Ты уже успела поговорить с [kay.name] сегодня, так что тебе особо нечего сказать ему до завтра.", null);
					
				} else {
					return new Response("Светская беседа", "Немного поболтать с [kay.name].", KAY_OFFICE_UTIL_EMPTY) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK"));
							
							List<String> topics = Util.newArrayListOfValues(
									"KAY_MACHINES",
									"KAY_INTERCOM",
									"KAY_BUSINESS",
									"KAY_BOUNTY_HUNTERS");
							long lowestValue = 1_000_000;
							for(String topic : topics) {
								if(Main.game.getDialogueFlags().getSavedLong(topic)<lowestValue) {
									lowestValue = Main.game.getDialogueFlags().getSavedLong(topic);
								}
							}
							long thanksJava = lowestValue;
							topics.removeIf(s -> Main.game.getDialogueFlags().getSavedLong(s)>thanksJava);
							String topicSelected = Util.randomItemFrom(topics);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK_"+topicSelected));
							Main.game.getDialogueFlags().incrementSavedLong(topicSelected, 1);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK_END"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayTalkedTo, true);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFlirtedWith)) {
					return new Response("Флиртовать", "Ты уже успела пофлиртовать с [kay.name] сегодня. Если хочешь пофлиртовать с ним еще, стоит вернуться завтра.", null);
					
				} else {
					return new Response("Флиртовать", "Немного пофлиртовать с [kay.name].", KAY_OFFICE_UTIL_EMPTY) {
						@Override
						public int getSecondsPassed() {
							return 20*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_FLIRT"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFlirtedWith, true);
						}
					};
				}
				
			} else if(index==3) {
				if(Main.game.getNpc(Kay.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_FOUR_LOVE)) {
					return new Response("Доминировать",
							"Хотя он явно к тебе неравнодушен, заметно, что [kay.name] будет не по себе, если ты начнешь доминировать над ним в сексе.<br/>[style.italicsMinorBad(Требуется уровень привязанности у Кэя не ниже '"+AffectionLevel.POSITIVE_FOUR_LOVE.getName()+"' к тебе.)]",
							null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayDommed)) {
					return new Response("Доминировать", "Ты уже доминировала над [kay.name] сегодня, так что он будет слишком измотан, чтобы развлекать тебя, до завтрашнего дня.", null);
					
				} else {
					return new Response("Доминировать",
							"Доминируй над [kay.name] и заставь [kay.herHim] подчиниться тебе.",
							KAY_OFFICE_DOMINATE,
							Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
							Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(Main.game.getNpc(Kay.class).getAffection(Main.game.getPlayer())<100) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).setAffection(Main.game.getPlayer(), 100));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayDommed, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_UTIL_EMPTY = new DialogueNode("", "", true, true) {
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
			return OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kaySubmitted)) {
				if(index==1) {
					return new Response("[pc.Name]", "Не позволяй [kay.name] называть тебя каким-то особенным именем.<br/>[style.colourMinorGood(Это можно будет изменить в любое время после этой сцены.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NO_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==2) {
					return new Response("[pc.Mistress]", "Заставь [kay.name] называть тебя '[pc.Mistress]'.<br/>[style.colourMinorGood(Это можно будет изменить в любое время после этой сцены.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Mistress]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==3) {
					return new Response("[pc.Maam]", "Заставь [kay.name] называть тебя '[pc.Maam]'.<br/>[style.colourMinorGood(Это можно будет изменить в любое время после этой сцены.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Maam]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==4) {
					String surname = (Main.game.getPlayer().getSurname()!=null && !Main.game.getPlayer().getSurname().isEmpty()?"[pc.Surname]":"[pc.Name]");
					return new Response("[pc.Miss] "+surname,"Заставь [kay.name] называть тебя '[pc.Miss] "+surname+"'.<br/>[style.colourMinorGood(Это можно будет изменить в любое время после этой сцены.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Miss] "+surname);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==5) {
					return new Response("[pc.Mommy]", "Заставь [kay.name] называть тебя '[pc.Mommy]'.<br/>[style.colourMinorGood(Это можно будет изменить в любое время после этой сцены.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Mommy]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
				}
				
			} else {
				boolean buttplugActionAvailable = Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)!=null || Main.game.isAnalContentEnabled();
				
				if(index==0) {
					return new Response("Уйти",
							"Насладившись обществом [kay.name], пора покинуть его кабинет.<br/>[style.italicsMinorBad(Если ты уйдешь, тебе придется ждать до завтра, прежде чем ты снова сможешь доминировать над ним!)]",
							CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_LEAVE"));
						}
					};
				}
				// Standard actions:
				if(index==1) {
					return new Response("Погладить [kay.herHim]", "Заставить [kay.name] опуститься на колени рядом с тобой, чтобы можно было немного погладить мягкие ушки и волосы [kay.her].", KAY_OFFICE_DOMINATE_PETTING);
					
				} else if(index==2) {
					return new Response("Сделать массаж", "Попросить [kay.name] размять твои плечи и спину.", KAY_OFFICE_DOMINATE_MASSAGE);
					
				} else if(index==3 && Main.game.isFootContentEnabled()) {
					if(!Main.game.getPlayer().hasLegs()) {
						return new Response("Сделать массаж ног", "У тебя нет ног, чтобы [kay.name] могла их массировать...", null);
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.FEET, true)) {
						return new Response("Сделать массаж ног", "Поскольку ты не можешь подобраться к своим [pc.feet], [kay.name] не может сделать тебе массаж ног!", null);
					} else {
						return new Response("Сделать массаж ног", "Заставить [kay.name] сделать тебе массаж ног.", KAY_OFFICE_DOMINATE_MASSAGE_FEET);
					}
					
				} else if(Main.game.isFootContentEnabled()?index==4:index==3) {
					return getKaySexResponse("Трахать [kay.herHim]",
							"Притянуть [kay.name] к себе и сказать [kay.herHim], что ты собираешься трахать [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_FUCK"),
							SexPosition.STANDING,
							SexSlotStanding.STANDING_SUBMISSIVE,
							null,
							SexSlotStanding.STANDING_DOMINANT,
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues());
				}
				
				// Toys and behaviour changes:
				if(index==6) {
					if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) {
						return new Response("Клетка: [style.colourMinorBad(Не надета)]",
								"На [kay.Name] сейчас нет клетки целомудрия [kay.her]. Ты мог бы это исправить, если захочешь...<br/>[style.italicsMinorGood(Ты можешь в любое время надевать и снимать клетку [kay.namePos].)]",
								KAY_OFFICE_DOMINATE_CAGE);
					} else {
						return new Response("Клетка: [style.colourMinorGood(Надета)]",
								"На [kay.Name] сейчас надета клетка целомудрия [kay.her]. Ты мог бы снять её с [kay.herHim], если захочешь...<br/>[style.italicsMinorGood(Ты можешь в любое время надевать и снимать клетку [kay.namePos].)]",
								KAY_OFFICE_DOMINATE_CAGE);
					}
					
				} else if(index==7 && buttplugActionAvailable) {
					if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
						return new Response("Анальная пробка: [style.colourMinorBad(Извлечена)]", "В [kay.Name] сейчас не вставлена анальная пробка [kay.her]. Ты мог бы это исправить, если захочешь...", KAY_OFFICE_DOMINATE_BUTTPLUG);
					} else {
						return new Response("Анальная пробка: [style.colourMinorGood(Вставлена)]", "На [kay.Name] сейчас надета [kay.her] анальная пробка. Ты мог бы снять её, если захочешь...", KAY_OFFICE_DOMINATE_BUTTPLUG);
					}
					
				} else if(buttplugActionAvailable?index==8:index==7) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
						return new Response("Одежда: [style.colourFeminine(Женская)]",
								"Велеть [kay.name] переодеться обратно в старый мужской костюм [kay.her].<br/>[style.italicsMinorBad(В результате [kay.Name] вернется к своей старой прическе [kay.her], а макияж [kay.her] смоется.)]",
								KAY_OFFICE_DOMINATE_CLOTHING);
					} else {
						return new Response("Одежда: [style.colourMasculine(Мужская)]",
								"Сказать [kay.name], что [kay.she] стоит найти женский костюм.",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayPreviouslyFeminised)
									?KAY_OFFICE_DOMINATE_CLOTHING
									:KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME);
					}
					
				} else if(buttplugActionAvailable?index==9:index==8) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
						return new Response("Преображение", "[kay.Name] будет не по себе, если ты решишь устроить [kay.herHim] преображение, пока у неё нет [kay.herHim] для женской одежды!", null);
						
					} else if(!Main.game.getPlayer().hasItemType(ItemType.MAKEUP_SET)) {
						return new Response("Преображение", "Тебе требуется "+ItemType.MAKEUP_SET.getDeterminer()+" "+ItemType.MAKEUP_SET.getName(false)+" чтобы сделать это!", null);
						
					} else {
						return new Response("Преображение", "Заставь [kay.name] сесть в [kay.her] стул, чтобы сменить [kay.her] прическу и нанести макияж на [kay.herHim].", KAY_OFFICE_DOMINATE_MAKEUP);
					}
					
				} else if(index==10) {
					return new Response("Имя", "Выбери, как [kay.name] будет тебя называть.", KAY_OFFICE_DOMINATE_NAMING);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_UTIL_EMPTY = new DialogueNode("", "", true, true) {
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
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_POST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_PETTING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();

			responses.add(new Response("Перестать ласкать", "Наласкавшись с [kay.name] досыта, ты откидываешься на [kay.her] стул и гадаешь, чем заняться дальше.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_END"));
				}
			});
			
			if(isPlayerPenisFree()) {
				responses.add(getKaySexResponse("Получить минет",
						Main.game.getPlayer().isTaur()
							?"Встань и заставь [kay.name] опуститься на колени перед твоей нижней частью [pc.legRace], чтобы [kay.she] могла отсосать твой член."
							:"Притяни голову [kay.namePos] к себе на колени и заставь [kay.herHim] отсосать твой член.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_BLOWJOB"),
						Main.game.getPlayer().isTaur()
							?SexPosition.STANDING
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.PERFORMING_ORAL
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.STANDING_DOMINANT
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisMouth.BLOWJOB_START, false, true))));
				
			} else {
				responses.add(new Response("Получить минет", "Чтобы получить минет от [kay.name], тебе необходим пенис и доступ к нему.", null));
			}
			
			if(isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("Принять куннилингус",
						Main.game.getPlayer().isTaur()
							?"Встань и заставь [kay.name] опуститься на колени позади твоей нижней части [pc.legRace], чтобы [kay.she] могла отлизать тебя."
							:"Притяни голову [kay.namePos] к себе на колени и заставь [kay.herHim] отлизать тебя.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_CUNNILINGUS"),
						Main.game.getPlayer().isTaur()
							?SexPosition.STANDING
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.PERFORMING_ORAL_BEHIND
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.STANDING_DOMINANT
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true))));
				
				if(Main.game.getPlayer().isClitorisPseudoPenis()) {
					responses.add(getKaySexResponse("Клитор отсосан",
							Main.game.getPlayer().isTaur()
								?"Встань и заставь [kay.name] опуститься на колени позади твоего нижнего [pc.legRace], чтобы [kay.she] могла сосать твой [pc.clitSize] клитор."
								:"Притяни голову [kay.namePos] к себе на колени и заставь [kay.herHim] сосать твой [pc.clitSize] клитор.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_CLIT_SUCK"),
							Main.game.getPlayer().isTaur()
								?SexPosition.STANDING
								:SexPosition.SITTING,
							Main.game.getPlayer().isTaur()
								?SexSlotStanding.PERFORMING_ORAL
								:SexSlotSitting.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT),
							Main.game.getPlayer().isTaur()
								?SexSlotStanding.STANDING_DOMINANT
								:SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(CoverableArea.VAGINA),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), ClitMouth.CLIT_ORAL_START, false, true))));
				} else {
					responses.add(new Response("Клитор отсосан", "Тебе требуется клитор размером не меньше '"+ClitorisSize.getMinimumClitorisSizeForPseudoPenis()+"', прежде чем [kay.name] сможет его отсосать.", null));
				}
				
			} else {
				responses.add(new Response("Принять куннилингус", "Тебе требуется киска и доступ к ней, прежде чем ты сможешь получить куннилингус от [kay.name].", null));
				responses.add(new Response("Клитор отсосан", "Тебе требуется киска, клитор размером не меньше '"+ClitorisSize.getMinimumClitorisSizeForPseudoPenis()+"', и доступ к ней, прежде чем [kay.name] сможет его отсосать.", null));
			}
			
			for(int i=0;i<responses.size();i++) {
				if(i==index-1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+15)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();

			responses.add(new Response("Прекратить массаж", "Сказать [kay.name], что [kay.sheHas] проделала отличную работу, но на данный момент с [kay.her] тебе хватит.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_END"));
				}
			});
			
			if(isPlayerPenisFree() || isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("«Массаж» паха",
						"Сказать [kay.name], чтобы она переместила внимание [kay.her] на твой пах и "
						+(isPlayerPenisFree() && isPlayerVaginaFree()
							?"ласкала тебя пальцами, одновременно делая тебе минет/мастурбируя."
							:(isPlayerPenisFree()
								?"сделала тебе минет/мастурбировала."
								:"ласкала тебя пальцами.")),
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_GROIN"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.BEHIND
							:SexSlotDesk.BETWEEN_LEGS,
						isPlayerPenisFree()
							?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)
							:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(),
						Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA),
						Util.newArrayListOfValues(
								(isPlayerPenisFree()
									?new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true)
									:null),
								(isPlayerVaginaFree()
									?new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true)
									:null))));
				
			} else {
				responses.add(new Response("«Массаж» паха", "Чтобы получить «массаж» паха от [kay.name], тебе необходим пенис или влагалище, а также доступ к ним.", null));
			}

			if(isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("Куннилингус",
						"Заставить [kay.name] опуститься позади тебя и отсосать/полизать тебя.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_CUNNILINGUS"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.BEHIND_ORAL
							:SexSlotDesk.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true))));
				
			} else {
				responses.add(new Response("Куннилингус", "Чтобы получить куннилингус от [kay.name], вам необходима вагина и доступ к ней.", null));
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(isPlayerAssFree()) {
					responses.add(getKaySexResponse("Анилингус",
							"Заставьте [kay.name] опуститься позади вас и заняться анилингусом.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_ANILINGUS"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.OVER_DESK,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.BEHIND_ORAL
								:SexSlotDesk.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotDesk.OVER_DESK_ON_FRONT,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true))));
					
				} else {
					responses.add(new Response("Анилингус", "Чтобы получить куннилингус от [kay.name], вам необходим доступ к анусу.", null));
				}
			}
			
			if(!isPlayerVaginaFree()) {
				responses.add(new Response("Трахнутый", "Чтобы [kay.name] оттрахал вас, вам необходима вагина и доступ к ней.", null));
				
			} else if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				responses.add(new Response("Трахнутый", "Так как член [kay.namePos] заперт в девственной клетке [kay.her], [kay.she] не может трахать вас!", null));
				
			} else {
				responses.add(getKaySexResponse("Трахнутый",
						"Заставьте [kay.name] подняться позади вас и оттрахать вас.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_VAGINAL"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.HUMPING
							:SexSlotDesk.HUMPING,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)),
						Util.newHashMapOfValues(
								new Value<>(
										Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(
													SexAreaOrifice.VAGINA,
													Util.newHashMapOfValues(
														new Value<>(
															Main.game.getNpc(Kay.class),
															Util.newHashSetOfValues(LubricationType.PRECUM)))))))));
				
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!isPlayerAssFree()) {
					responses.add(new Response("Трахали (анально)", "Чтобы получить анальный секс от [kay.name], вам необходим доступ к анусу.", null));
					
				} else if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					responses.add(new Response("Трахали (анально)", "Так как член [kay.namePos] заперт в девственной клетке [kay.her], [kay.she] не может трахать ваш зад!", null));
					
				} else {
					responses.add(getKaySexResponse("Трахали (анально)",
							"Заставьте [kay.name] подняться позади вас и оттрахать ваш зад.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_ANAL"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.OVER_DESK,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.HUMPING
								:SexSlotDesk.HUMPING,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotDesk.OVER_DESK_ON_FRONT,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)),
							Util.newHashMapOfValues(
									new Value<>(
											Main.game.getPlayer(),
											Util.newHashMapOfValues(
													new Value<>(
														SexAreaOrifice.ANUS,
														Util.newHashMapOfValues(
															new Value<>(
																Main.game.getNpc(Kay.class),
																Util.newHashSetOfValues(LubricationType.PRECUM)))))))));
					
				}
			}
			
			for(int i=0;i<responses.size();i++) {
				if(i==index-1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_MASSAGE_FEET = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+15)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Прекратить массаж", "Сказать [kay.name], что [kay.sheHas] проделала отличную работу, но на данный момент с [kay.her] тебе хватит.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_END"));
					}
				};
				
			} else if(index==2) {
				return getKaySexResponse("[pc.Foot(true)] поклоняться",
						"Заставить [kay.name] восторженно воспевать твои [pc.feet+(true)] губами.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_FOOT_WORSHIP"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.USING_FEET
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.FEET),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), FootMouth.FOOT_ORAL_RECEIVING_START, false, true)));
				
			} else if(index==3) {
				if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("[pc.Footjob]", "Поскольку член [kay.namePos] заперт в [kay.her] девственной клетке, ты не можешь подарить [kay.herHim] [pc.footjob]!", null);
					
				} else {
					return getKaySexResponse("[pc.Footjob]",
							"Заставить [kay.name] выставить тебе [kay.her] член, чтобы ты могла наградить [kay.herHim] за [pc.footjob].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_FOOTJOB"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.SITTING,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.USING_FEET
								:SexSlotSitting.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisFeet.FOOT_JOB_DOUBLE_GIVING_START, false, true)));
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).equipClothingFromNowhere(
								Main.game.getItemGen().generateClothing("innoxia_bdsm_chastity_cage", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLUE_LIGHT, PresetColour.CLOTHING_STEEL, false), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_EQUIP"));
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_UNEQUIP"));
				Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS).setSealed(false);
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).unequipClothingIntoVoid(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_UNEQUIP_END"));
			}
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
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) { // Taken cage off:
				List<Response> responses = new ArrayList<>();

				responses.add(new Response("Откинуться назад", "Откинуться назад и позволить [kay.name] натянуть [kay.her] брюки обратно.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_SIT_BACK"));
					}
				});
				
				if(!Main.game.getPlayer().isTaur()) {
					responses.add(getKaySexResponse("Притянуть к себе на колени",
							"Притянуть [kay.name] к себе на колени и сказать [kay.herHim], что ты собираешься трахнуть [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_LAP"),
							SexPosition.SITTING,
							SexSlotSitting.SITTING_IN_LAP,
							null,
							SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				} else {
					responses.add(new Response("Притянуть к себе на колени", "Так как у тебя нижняя часть тела как у [pc.a_legRace], ты не можешь притянуть [kay.name] к себе на колени!", null));
				}

				if(Main.game.isAnalContentEnabled()) {
					responses.add(getKaySexResponse("Перевалить через стол",
							"Толкнуть [kay.name] на стол [kay.her] и сказать [kay.herHim], что ты собираешься трахнуть [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_DESK"),
							SexPosition.OVER_DESK,
							SexSlotDesk.OVER_DESK_ON_FRONT,
							null,
							SexSlotDesk.BETWEEN_LEGS,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				}

				responses.add(getKaySexResponse("Ручная стимуляция",
						"Вознаградить [kay.name], взявшись за член [kay.her] и отсосав [kay.herHim].",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_HANDJOB"),
						SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
						SexSlotStanding.PERFORMING_ORAL,
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), FingerPenis.COCK_MASTURBATING_START, false, true))));

				if(isPlayerMouthFree()) {
					responses.add(getKaySexResponse("Сосать член",
							"Вознаградить [kay.name], наклонившись вперед и посасывая член [kay.her].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_BLOWJOB"),
							SexPosition.STANDING,
							SexSlotStanding.STANDING_DOMINANT,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
							SexSlotStanding.PERFORMING_ORAL,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisMouth.GIVING_BLOWJOB_START, false, true))));
					
				} else {
					responses.add(new Response("Сосать член", "Ты не можешь дотянуться до своего рта, поэтому не можешь сосать член [kay.namePos].", null));
				}

				if(!isPlayerVaginaFree()) {
					responses.add(new Response("Трахнутая", "Чтобы [kay.name] оттрахал вас, вам необходима вагина и доступ к ней.", null));
					
				} else {
					responses.add(getKaySexResponse("Трахнутый",
							"Предстать перед [kay.name] и позволить [kay.herHim] трахать твою киску.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_VAGINAL"),
							SexPosition.ALL_FOURS,
							SexSlotAllFours.HUMPING,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
							SexSlotAllFours.ALL_FOURS,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.VAGINA),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true))));
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!isPlayerAssFree()) {
						responses.add(new Response("Трахнутая (анально)", "Чтобы получить анальный секс от [kay.name], вам необходим доступ к анусу.", null));
						
					} else {
						responses.add(getKaySexResponse("Трахнутый (анально)",
								"Предстать перед [kay.name] и позволить [kay.herHim] трахать твою задницу.",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_ANAL"),
								SexPosition.ALL_FOURS,
								SexSlotAllFours.HUMPING,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
								SexSlotAllFours.ALL_FOURS,
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true))));
					}
				}

				for(int i=0;i<responses.size();i++) {
					if(i==index-1) {
						return responses.get(i);
					}
				}
				
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_BUTTPLUG = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).equipClothingFromNowhere(
								Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_jewel", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_BLUE_LIGHT, null, false), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_EQUIP"));
				
			} else {
				Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS).setSealed(false); // Just in case
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).unequipClothingIntoVoid(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_UNEQUIP"));
			}
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
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
				List<Response> responses = new ArrayList<>();

				responses.add(new Response("Откинуться назад", "Откинуться назад и позволить [kay.name] натянуть [kay.her] брюки обратно.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_SIT_BACK"));
					}
				});
				
				if(!Main.game.getPlayer().isTaur()) {
					responses.add(getKaySexResponse("Притянуть к себе на колени",
							"Притянуть [kay.name] к себе на колени и сказать [kay.herHim], что ты собираешься трахнуть [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_LAP"),
							SexPosition.SITTING,
							SexSlotSitting.SITTING_IN_LAP,
							null,
							SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				} else {
					responses.add(new Response("Притянуть к себе на колени", "Так как у тебя нижняя часть тела как у [pc.a_legRace], ты не можешь притянуть [kay.name] к себе на колени!", null));
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(isPlayerMouthFree()) {
						responses.add(getKaySexResponse("Анилингус на столе",
								"Толкнуть [kay.name] на стол [kay.her] и начать сосать [kay.her] анус.",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_DESK_ANILINGUS"),
								SexPosition.OVER_DESK,
								SexSlotDesk.OVER_DESK_ON_FRONT,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
								SexSlotDesk.PERFORMING_ORAL,
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.MOUTH),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), TongueAnus.ANILINGUS_START, false, true))));
						
					} else {
						responses.add(new Response("Анилингус на столе", "Ваш рот чем-то занят, поэтому вы не можете заняться анилингусом с [kay.name].", null));
					}
					
					if(isPlayerPenisFree()) {
						responses.add(getKaySexResponse("Анальный секс на столе",
								"Толкнуть [kay.name] на стол [kay.her] и трахнуть [kay.her] в зад.",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_DESK_FUCK"),
								SexPosition.OVER_DESK,
								SexSlotDesk.OVER_DESK_ON_FRONT,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								SexSlotDesk.BETWEEN_LEGS,
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisAnus.PENIS_FUCKING_START, false, true))));
						
					} else {
						responses.add(new Response("Секс на столе", "Чтобы трахнуть [kay.name] в зад, вам необходим пенис и свободный доступ к нему.", null));
					}
				}

				for(int i=0;i<responses.size();i++) {
					if(i==index-1) {
						return responses.get(i);
					}
				}
				
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Попросить Кей переодеться в «её» новый комплект одежды...", KAY_OFFICE_DOMINATE_CLOTHING);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public boolean isContinuesDialogue() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayPreviouslyFeminised);
		}
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_MASCULINE"));
				Main.game.getNpc(Kay.class).removeAllMakeup();
				Main.game.getNpc(Kay.class).setName(new NameTriplet("Джек", "Джек", "Джеки"));
				Main.game.getNpc(Kay.class).setHairStyle(HairStyle.LOOSE);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFeminised, false);
				Main.game.getNpc(Kay.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_FEMININE"));
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFeminised, true);
				Main.game.getNpc(Kay.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getNpc(Kay.class).loadImages(true); // reload images to use correct artwork
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
				if(index==1) {
					return new Response("Кей", "Продолжать называть милую кошкодевочку по фамилии и говорить ей, что она выглядит очень прелестно.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Джек", "Джек", "Джеки"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==2) {
					return new Response("Джеки",
							"Скажи милой [kay.race], что она выглядит очень прелестно, и что имя «Джеки» больше подходит [kay.herHim], когда [kay.she] выглядит вот так.<br/>[style.colourFeminine(Кей будет зваться «Джеки», пока (если вообще) ты не скажешь [kay.herHim] снова начать носить мужскую одежду.)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Джеки", "Джеки", "Джеки"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_JACKIE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==3) {
					return new Response("Кейти",
							"Скажи милой [kay.race], что она выглядит очень прелестно, и что имя «Кейти» больше подходит [kay.herHim], когда [kay.she] выглядит вот так.<br/>[style.colourFeminine(Кей будет зваться «Кейти», пока (если вообще) ты не скажешь [kay.herHim] снова начать носить мужскую одежду.)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Кейти", "Кейти", "Кейти"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_KAYTIE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==4) {
					return new Response("Микайла",
							"Скажи милой [kay.race], что она выглядит очень прелестно, и что имя «Микаэла» больше подходит [kay.herHim], когда [kay.she] выглядит вот так.<br/>[style.colourFeminine(Кей будет зваться «Микаэла», пока (если вообще) ты не скажешь [kay.herHim] снова начать носить мужскую одежду.)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Микайла", "Микайла", "Микайла"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MIKAYLA"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				}
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_MAKEUP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget( Main.game.getNpc(Kay.class));
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MAKEUP"));
					
			sb.append(CharacterModificationUtils.getSelfDivHairStyles("Прическа", UtilText.parse(BodyChanging.getTarget(), "Изменить прическу [npc.namePos]."))
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые руж) используются для придания щекам цвета, чтобы придать лицу более юный вид и подчеркнуть скулы.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Помада", "Помада используется для придания губам цвета, текстуры и защиты.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Подводка наносится по контуру глаз, чтобы подчеркнуть их форму или выделить отдельные черты.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются для того, чтобы сделать глаза ярче или привлекательнее.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окрашивания и защиты ногтей на твоих [pc.hands].", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окрашивания и защиты ногтей на твоих [pc.feet].", true, true));
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Завершено", "Вы закончили преображение [kay.name].", KAY_OFFICE_DOMINATE_MAKEUP_END);
			}
			return null;
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_MAKEUP_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MAKEUP_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_NAMING = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			NPC kay = Main.game.getNpc(Kay.class);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NAMING"));
			
			UtilText.nodeContentSB.append(UtilText.parse(kay,
					"<p>В данный момент [npc.nameIsFull] называет тебя '[npc.pcName]', и ты гадаешь, стоит ли заставить [npc.herHim] называть тебя как-то иначе. Поскольку [npc.sheIs] не твоя рабыня, ты не можешь заставить [npc.herHim] изменить [npc.her] имя.</p>"));
			
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='padding:8px 16px;'><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Имя</div><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Фамилия</div><div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
						+ UtilText.parse(kay, "Как тебя называет [npc.she]")
					+ "</div>"
					
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(kay.getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput' value='"+ UtilText.parseForHTMLDisplay(kay.getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(kay.getPetName(Main.game.getPlayer()))
						+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+kay.getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div> <div class='normal-button disabled' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>Все рабыни</div>");
			
			UtilText.nodeContentSB.append(UtilText.parse(kay,
						"<p style='text-align:center; margin-top:4px;'><i>Если сказать [npc.name] называть вас «Мамой» или «Папой», «Мамочкой» или «Папочкой», «Госпожой» или «Господином», либо «Мадам» или «Сэр», то [npc.she] автоматически переключится на соответствующее парное имя в зависимости от пола вашего персонажа.</i></p></div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Завершено", "Вы закончили выбирать, как [kay.name] будет вас называть.", KAY_OFFICE_DOMINATE_NAMING_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_NAMING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NAMING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
}
