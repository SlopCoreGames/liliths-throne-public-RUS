package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.21
 * @version 0.3.21
 * @author DSG
 */
public class RebelBaseInsaneSurvivorDialogue {
	
	private static NPC getAttacker() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode INSANE_SURVIVOR_ATTACK = new DialogueNode("Живой призрак", "Странная фигура в углу.", true) {
		@Override
		public String getAuthor() {
				return "DSG";
		}
		@Override
		public int getSecondsPassed() {
				return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "ATTACK", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "[npc.She], похоже, настроен сразиться с тобой. Подыграй [npc.herHim].",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(Я тебя, демонская мразь, на куски порву!)]"))));
			}
			else if (index == 2) {
				return new Response("Поговорить", "У тебя такое чувство, что [npc.she] уже лишился рассудка, но попытка не пытка.", INSANE_SURVIVOR_TALK_ATTEMPT);
			}
			else if (index == 3 && Main.game.getPlayer().getMoney() <= 0) {
				return new Response("Предложить деньги", "Твои пустые карманы не дают возможности даже попробовать.", null);
			}
			else if (index == 3) {
				return new Response("Предложить деньги", "Хотя [npc.she] вряд ли гонится за деньгами, простое предложение не повредит... ведь так?", INSANE_SURVIVOR_BRIBE_ATTEMPT);
			}
			else if (index == 4) {
				return new Response("Сдаться", "Снаряжение [npc.Her] может и потрепанное, но выглядит серьезно. Возможно, тебе не удастся победить.", INSANE_SURVIVOR_SURRENDER_ATTEMPT);
			}
			else {
				return null;
			}
		}	 
	};
	
	public static final DialogueNode INSANE_SURVIVOR_TALK_ATTEMPT = new DialogueNode("Попытка разговора", "", true) {
		@Override
		public String getAuthor() {
				return "DSG";
		}
		@Override
		public int getSecondsPassed() {
				return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "TALK", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "С [npc.She] бесполезно договариваться. Защищайся!",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(Я тебя, демонская мразь, на куски порву!)]"))));
			}
			return null;
		}
	};
	
	public static final DialogueNode INSANE_SURVIVOR_BRIBE_ATTEMPT = new DialogueNode("Попытка подкупа", "", true) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "BRIBE", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", "[npc.She] не купишь. Защищайся!",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(Я тебя, демонская мразь, на куски порву!)]"))));
			}
			return null;
		}
	};
	
	public static final DialogueNode INSANE_SURVIVOR_SURRENDER_ATTEMPT = new DialogueNode("Сдался!", "Нужно знать, когда пора сбросить карты.", true) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "SURRENDER", getAttacker());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Защищаться", "[npc.She] не верит, что твоя сдача искренняя, так что у тебя почти не остается выбора.",
						getAttacker(), 
						Util.newHashMapOfValues(
						new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(Я тебя, демонская мразь, на куски порву!)]"))));
			}
			return null;
		}
	};
	
	public static final DialogueNode INSANE_SURVIVOR_VICTORY = new DialogueNode("Победа!", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "VICTORY", getAttacker()));
			Main.game.banishNPC(getAttacker());
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered);
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Незнакомца нигде не видно. Можно и дальше идти своим путем...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}	   
	};
	
	public static final DialogueNode INSANE_SURVIVOR_DEFEATED = new DialogueNode("Побежден!", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "DEFEATED", getAttacker()));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-5000));
			Main.game.banishNPC(getAttacker());
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered);
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
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
			if (index == 1) {
				return new Response("Продолжить", "Незнакомца нигде не видно. Можно и дальше идти своим путем...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}  
	};
	
}
