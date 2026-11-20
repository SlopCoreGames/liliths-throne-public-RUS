package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.fields.Angelixx;
import com.lilithsthrone.game.character.npc.fields.Sleip;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class BountyHunterLodge {

	private static final float BARMAN_BUY_MODIFIER = 0.75f;
	
	private static final Map<String, String> barConsumablesMap = Util.newHashMapOfValues(
			new Value<>("innoxia_race_squirrel_round_nuts", "BAR_NUTS"),
			new Value<>("innoxia_race_dog_canine_crush", "BAR_BEER"),
			new Value<>("innoxia_race_horse_equine_cider", "BAR_CIDER"),
			new Value<>("dsg_race_bear_vodka", "BAR_VODKA"),
			new Value<>("innoxia_race_wolf_wolf_whiskey", "BAR_WHISKEY"),
			new Value<>("innoxia_race_rat_black_rats_rum", "BAR_RUM"));

	private static Response getDobermannsUpstairsSexResponse(int startIndex, int index, String title) {
		if(index == startIndex) {
			title = title.replaceAll("sex_type_replacement", "oral");
			
			if(!KaysWarehouse.isPlayerMouthFree()) {
				return new Response(title,
						"Так как вы не можете разжать рот, вы не можете предложить доберман-мальчикам отсосать...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.SITTING,
						SexSlotSitting.SITTING, SexAreaOrifice.MOUTH,
						SexSlotSitting.SITTING, SexAreaPenetration.FINGER,
						SexSlotSitting.PERFORMING_ORAL,
						title,
						"Предложить отсосать доберман-мальчикам...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_ORAL"));
			}
			
		} else if(index == startIndex+1) {
			title = title.replaceAll("sex_type_replacement", "spit-roast");
			
			if(!KaysWarehouse.isPlayerMouthFree()) {
				return new Response(title,
						"Так как вы не можете разжать рот, вы не можете предложить доберман-мальчикам устроить вам спит-рост...",
						null);
				
			} else if(!KaysWarehouse.isPlayerAssFree() && !KaysWarehouse.isPlayerVaginaFree()) {
				return new Response(title,
						"Так как вы не можете открыть задницу"+(Main.game.getPlayer().hasVagina()?" или киску":"")+", вы не можете предложить доберман-мальчикам устроить вам спит-рост...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.ALL_FOURS,
						SexSlotAllFours.BEHIND, KaysWarehouse.isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotAllFours.IN_FRONT, SexAreaOrifice.MOUTH,
						SexSlotAllFours.ALL_FOURS,
						title,
						"Предложить двум доберман-мальчикам устроить вам спит-рост...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_SPITROAST"));
			}
			
		} else if(index == startIndex+2) {
			title = title.replaceAll("sex_type_replacement", "ride");
			
			if(!KaysWarehouse.isPlayerAssFree() && !KaysWarehouse.isPlayerVaginaFree()) {
				return new Response(title,
						"Так как вы не можете открыть задницу"+(Main.game.getPlayer().hasVagina()?" или киску":"")+", вы не можете предложить оседлать доберман-мальчиков...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.LYING_DOWN,
						SexSlotLyingDown.LYING_DOWN, KaysWarehouse.isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotLyingDown.MISSIONARY, KaysWarehouse.isPlayerAssFree()?SexAreaOrifice.ANUS:SexAreaOrifice.VAGINA,
						SexSlotLyingDown.COWGIRL,
						title,
						"Предложить двум мальчикам-доберманнам подвезти их...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_RIDE"));
			}
			
		} else if(index == startIndex+3 && Main.game.isNipplePenEnabled()) {
			title = title.replaceAll("sex_type_replacement", "nipple-fuck");
			
			if(!KaysWarehouse.isPlayerNippleFuckFree()) {
				return new Response(title,
						Main.game.getPlayer().isBreastFuckableNipplePenetration()
							?"Так как вы не можете добраться до своих сочных сосков, вы не можете предложить их мальчикам-доберманнам..."
							:"Так как у вас нет сочных сосков, вы не можете предложить их мальчикам-доберманнам...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT, SexAreaOrifice.NIPPLE,
						SexSlotStanding.STANDING_DOMINANT_TWO, SexAreaOrifice.NIPPLE,
						SexSlotStanding.PERFORMING_ORAL,
						title,
						"Предложить мальчикам-доберманнам оттрахать ваши соски...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_NIPPLES"));
			}
		}
		return null;
	}
	
	public static final DialogueNode ENTRANCE_INITITAL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE_INITITAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Выход", "Выйти обратно в Аллею Работорговцев.", PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FLOOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "FLOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && Main.game.getCharactersPresent().contains(Main.game.getNpc(SupplierLeader.class))) {
				return new Response("Доберманны", "Подойти и что-нибудь сказать Вольфгангу и Карлу...", DOBERMANNS);
			}
			return null;
		}
	};

	public static final DialogueNode DOBERMANNS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!KaysWarehouse.isSexAvailable()) {
					return new Response("Соблазнить", "Так как вы не можете добраться ни до одного из своих отверстий, вы не в состоянии соблазнить мальчиков-доберманнов...", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dobermannDefeatSeduced) || Main.game.getNpc(SupplierLeader.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
					return new Response("Соблазнить",
							"Предложить мальчикам-доберманнам снова оттрахать вас...<br/>"
								+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_DOMINANT)
										?"[style.italicsSexDom(Раз уж у вас)] [style.italicsMinorGood(есть) "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" фетиш)][style.italicsSexDom(, в следующей секс-сцене вы сможете выбрать роль либо доминанта, либо сабмиссива.)]"
										:"[style.italicsSex(Поскольку у вас)] [style.italicsMinorBad(нет "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" фетиша)][style.italicsSex(, в следующей секс-сцене вы сможете занять только роль сабмиссива.)]"),
							DOBERMANNS_UPSTAIRS) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEDUCE_REPEAT"));
						}
					};
					
				} else {
					return new Response("Соблазнить",
							"Соблазнить мальчиков-доберманов и переспать с ними...<br/>"
									+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_DOMINANT)
											?"[style.italicsSexDom(Раз уж у вас)] [style.italicsMinorGood(есть) "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" фетиша)][style.italicsSexDom(, в следующей секс-сцене вы сможете выбрать роль либо доминанта, либо сабмиссива.)]"
											:"[style.italicsSex(Поскольку у вас)] [style.italicsMinorBad(нет "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" фетиша)][style.italicsSex(, в следующей секс-сцене вы сможете занять только роль сабмиссива.)]"),
							DOBERMANNS_UPSTAIRS,
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
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEDUCE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatSeduced, true);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Уйти", "Отойти от стола и вернуться в таверну.", FLOOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DOBERMANNS_UPSTAIRS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS);
			Main.game.getNpc(SupplierLeader.class).setLocation(Main.game.getPlayer(), true);
			Main.game.getNpc(SupplierPartner.class).setLocation(Main.game.getPlayer(), true);
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
			if(index==1) {
				return new ResponseSex("Трахнуть их",
						"Повалить Вольфганга и Карла рядом друг с другом в позу догги-стайл, готовясь хорошенько развлечься с ними...",
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_UPSTAIRS_FUCK_THEM"));
			}
			return getDobermannsUpstairsSexResponse(2, index, "Подчиниться (sex_type_replacement)");
		}
	};

	public static final DialogueNode DOBERMANNS_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вниз", "Спуститься обратно на первый этаж таверны...", STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_AFTER_SEX_DOWNSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SEATING = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "SEATING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && Main.game.getCharactersPresent().contains(Main.game.getNpc(Silence.class))) {
				return new Response("Сайленс", "Подойти к Сайленс и что-нибудь ей сказать...<br/>[style.italicsBad(Вскоре будет добавлен мини-квест с участием Сайленс и Шэдоу!)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "STAIRS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Наверх", "Подняться по лестнице на второй этаж таверны.", UPSTAIRS_STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "STAIRS_UPSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_STAIRS);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BOUNTY_BOARD = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "BOUNTY_BOARD");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Награды", "Внимательнее изучить доступные награды.<br/>[style.italicsMinorBad(Будет добавлено в одном из будущих обновлений!)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode BAR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "BAR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Зарегистрироваться", "Сказать бармену, что вы хотите зарегистрироваться как официальный охотник за головами.<br/>[style.italicsMinorBad(Будет добавлено в одном из будущих обновлений!)]", null);
			}
			
			List<Response> responses = new ArrayList<>();
			
			for(Entry<String, String> entry : barConsumablesMap.entrySet()) {
				AbstractItem consumable = Main.game.getItemGen().generateItem(entry.getKey());
				int price = consumable.getPrice(BARMAN_BUY_MODIFIER);
				
				if(Main.game.getPlayer().getMoney()<price) {
					responses.add(new Response(consumable.getName()+" ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")",
							"У вас недостаточно "+Util.intToString(price)+" огней, чтобы сделать заказ "+consumable.getName(true, false)+"...",
							null));
					
				} else {
					responses.add(new Response(consumable.getName()+" ("+UtilText.formatAsMoney(price, "охват")+")",
							"Отдать "+Util.intToString(price)+" огни и порядок "+consumable.getName(true, false)+".",
							BAR_CONSUME) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(price), true);
							UtilText.addSpecialParsingString(consumable.getName(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", entry.getValue()));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().useItem(consumable, Main.game.getPlayer(), false, true));
						}
					});
				}
			}
			
			if(index>0 && index-2<responses.size()) {
				return responses.get(index-2);
			}
			
			return null;
		}
	};

	public static final DialogueNode BAR_CONSUME = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BAR.getResponse(responseTab, index);
		}
	};
	
	// First floor:
	
	public static final DialogueNode UPSTAIRS_CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_STAIRS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_STAIRS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вниз", "Спуститься обратно по лестнице на первый этаж таверны.", STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_STAIRS_DOWNSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	// Content for Angelixx & sons was moved out of here, but these locations should still work fine
	
	public static final DialogueNode UPSTAIRS_ROOM_ANGELIXX = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Angelixx.class))) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM_ANGELIXX");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isHourBetween(9, 18)) {
					return new Response("Ангеликс",
							"Постучать в дверь, чтобы встретиться с Анжеликсом.<br/>[style.italicsMinorBad(Этот контент скоро будет добавлен!)]",
							null);
					
				} else {
					return new Response("Ангеликс",
							"Анжеликса сейчас нет на месте...<br/><i>Приходите в период с [units.time(9)] по [units.time(18)], чтобы встретиться с Анжеликсом.</i>",
							null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_ROOM_SLEIP_NIR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Sleip.class))) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM_SLEIP_NIR");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isHourBetween(9, 18)) {
					return new Response("Слейп и Нир",
							"Постучать в дверь, чтобы встретиться со Слейпом и Ниром.<br/>[style.italicsMinorBad(Этот контент скоро будет добавлен!)]",
							null);
					
				} else {
					return new Response("Слейп и Нир",
							"Слейпа и Нира сейчас нет на месте...<br/><i>Приходите в период с [units.time(9)] по [units.time(18)], чтобы встретиться с Анжеликсом.</i>",
							null);
				}
			}
			return null;
		}
	};
	
	
}
