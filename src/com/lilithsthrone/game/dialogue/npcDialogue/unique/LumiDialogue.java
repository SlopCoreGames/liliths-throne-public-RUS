package com.lilithsthrone.game.dialogue.npcDialogue.unique;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.2.6
 * @author Nnxx, Innoxia
 */
public class LumiDialogue {
	
	public static final DialogueNode LUMI_APPEARS = new DialogueNode("Переулки", "", true) {
		
		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lumiMet)) {
					return new Response("Остаться", "Решить остаться и посмотреть, кто подойдет.", LUMI_APPEARS_REPEAT_ENCOUNTER);
				} else {
					return new Response("Остаться", "Решить остаться и посмотреть, кто подойдет.", LUMI_APPEARS_FIRST_ENCOUNTER);
				}
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Ускользнуть", "Быстро нырнуть в узкий проход.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_EVADE"));
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static long moneyStolen = 0;
	
	public static final DialogueNode LUMI_APPEARS_FIRST_ENCOUNTER = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_FIRST_ENCOUNTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ждать", "Продолжать прятаться за грудой хлама.", LUMI_APPEARS_FIRST_ENCOUNTER_WAITING) {
					@Override
					public void effects() {
						moneyStolen = Main.game.getPlayer().getMoney()>=700?700:Main.game.getPlayer().getMoney();
						Main.game.getPlayer().incrementMoney(-moneyStolen);
						
						if(moneyStolen==700) {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "MAX_MONEY_STOLEN"));
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "ALL_MONEY_STOLEN"));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_APPEARS_FIRST_ENCOUNTER_WAITING = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_FIRST_ENCOUNTER_WAITING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Погнаться за ними", "Ты не позволишь этому вору ускользнуть!", LUMI_CHASE);
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Отпустить их", "У тебя нет времени гоняться за мелкими воришками. Пусть уходят.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_LET_THEM_ESCAPE"));
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_CHASE = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить погоню", "Ты не позволишь этому вору ускользнуть!", LUMI_CHASE_CONTINUE) {
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_CHASE_CONTINUE = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CHASE_CONTINUE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Поговорить", "Решить попробовать поговорить с ней. В конце концов, насилие — это не всегда выход!", LUMI_CAUGHT_TALK);
				
			} else if (index == 2) {
				return new ResponseCombat("Сразиться",
						"Ты не позволишь этому вору ускользнуть!",
						Main.game.getNpc(Lumi.class),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_COMBAT_PC_OPENING")),
								new Value<>(Main.game.getNpc(Lumi.class), UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_COMBAT_LUMI_OPENING")))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lumiDisabled, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_PLAYER_LOSS = new DialogueNode("Переулки", "", true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_LOSS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_PLAYER_WIN = new DialogueNode("Переулки", "", true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Вернуть деньги", "Просто забрать свои деньги и уйти, не поднимая шума.", COMBAT_PLAYER_WIN_RECOVER_MONEY) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(moneyStolen);
					}
				};
				
			} else if (index == 2 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				return new ResponseSex("Воспользоваться случаем",
						"Теперь, когда она покорена, самое время повеселиться с этой беспомощной волчьей девчонкой!",
						false, false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lumi.class), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.DOM_ROUGH;
								}
								return SexPace.SUB_RESISTING;
							}
						},
						null,
						null, AFTER_SEX, UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN_TAKE_ADVANTAGE")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementKarma(-1000);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "AFTER_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_PLAYER_WIN_RECOVER_MONEY = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN_RECOVER_MONEY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_PLAYER_WIN_TAKE_ADVANTAGE = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "COMBAT_PLAYER_WIN_TAKE_ADVANTAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_CAUGHT_TALK = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Отдать", "Позволить Люми оставить украденные у тебя деньги и подсказать ей, как распорядиться ими с наибольшей пользой.", LUMI_CAUGHT_TALK_GIVE_MONEY) {
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setPlayerKnowsName(true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lumi.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index == 2) {
				return new Response("Спросить", "Попросить Люми вернуть деньги, которые она у тебя украла.", LUMI_CAUGHT_TALK_ASK_FOR_MONEY_BACK) {
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setPlayerKnowsName(true);
						Main.game.getPlayer().incrementMoney(moneyStolen);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lumiPromisedDinner, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lumi.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index == 3) {
				return new Response("Угрожать", "Проучить эту воровку! У тебя возникает предчувствие, что если ты сделаешь это, то больше её никогда не увидишь.", LUMI_CAUGHT_TALK_THREATEN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lumiDisabled, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_CAUGHT_TALK_GIVE_MONEY = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK_GIVE_MONEY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_CAUGHT_TALK_ASK_FOR_MONEY_BACK = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK_ASK_FOR_MONEY_BACK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LUMI_CAUGHT_TALK_THREATEN = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_CAUGHT_TALK_THREATEN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", null){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getNpc(Lumi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	
	public static final DialogueNode LUMI_APPEARS_REPEAT_ENCOUNTER = new DialogueNode("Переулки", "", true, true) {

		@Override
		public String getAuthor() {
			return "Ннхх";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/lumi", "LUMI_APPEARS_REPEAT_ENCOUNTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
