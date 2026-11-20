package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.96
 * @version 0.3.9
 * @author Innoxia
 */
public class ReindeerOverseerDialogue {
	
	private static NPC getReindeer() {
		return Main.game.getActiveNPC();
	}
	
	private static Response getDefaultResponses(int index) {
		if(index == 1) {
			return new ResponseTrade("Торговать",
					UtilText.parse(getReindeer(), "Спросить у [npc.name], какие рождественские подарки [npc.sheIs] продает."),
					getReindeer()) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
				}
			};
			
		} else if(index == 2) {
			if(Main.game.getDialogueFlags().hasWorkedForReindeer(getReindeer().getId())) {
				return new Response("Работать",
						UtilText.parse(getReindeer(), "Вы уже помогли [npc.name] закончить всю работу, которую [npc.she] должна была сделать сегодня, так что, если вы хотите снова поработать на [npc.herHim], вам придется вернуться завтра."),
						null);
				
			} else {
				return new Response("Работать", "Предложить поработать с оленеморфами.", ENCOUNTER_WORK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
						Main.game.getDialogueFlags().addReindeerDailyWorkedFor(getReindeer().getId());
					}
				};
			}
			
		} else if(index == 3) {
			if(!Main.game.getDialogueFlags().hasWorkedForReindeer(getReindeer().getId())) {
				return new Response("Снять стресс",
						UtilText.parse(getReindeer(), "[npc.Name] сейчас слишком занята, чтобы отвлекаться от работы. Возможно, если вы сначала поможете, у [npc.she] найдется время, чтобы трахнуть вас..."),
						null);
				
			} else if(!getReindeer().isAttractedTo(Main.game.getPlayer())) {
				return new Response("Снять стресс",
						UtilText.parse(getReindeer(), "[npc.Name] не испытывает к вам влечения, и поэтому [npc.sheIs] не готова заняться с вами сексом..."),
						null);
				
			} else {
				return new ResponseSex("Снять стресс",
							UtilText.parse(getReindeer(), "Спросить [npc.name], не хочет ли [npc.she] немного развлечься с вами."),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getReindeer()),
							null,
							null) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							AFTER_SEX,
							UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "SEX_START")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
						}
					};
			}
			
		} else if(index==0) {
			return new Response("Уйти",
					UtilText.parse(getReindeer(), "Сказать [npc.name], что вы, возможно, заглянете в другой раз, прежде чем уйти."),
					ENCOUNTER_START){
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
				}
				@Override
				public DialogueNode getNextDialogue(){
					return Main.game.getDefaultDialogue(false);
				}
			};
		}
		return null;
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("Надзиратель оленей", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_START", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_WORK = new DialogueNode("Надзиратель оленей", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Копать снег",
						UtilText.parse(getReindeer(), "Скажи [npc.name], что будешь чистить снег вместе с остальными рабочими.<br/>Твоя производительность и оплата будут зависеть от твоей [style.italicsStrength("+Attribute.MAJOR_PHYSIQUE.getName()+")]."),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_SHOVEL_SNOW", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
				
			} else if(index==2) {
				return new Response("Использовать тепловой шест",
						UtilText.parse(getReindeer(), "Скажи [npc.name], что ты хочешь использовать одну из нагретых палок, чтобы прочертить линии на снегу.<br/>Твои успехи и оплата будут зависеть от твоего [style.italicsIntelligence("+Attribute.MAJOR_ARCANE.getName()+")]."),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_HEAT_STAVE", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
				
			} else if(index==3) {
				return new Response("'Поощрение'",
						UtilText.parse(getReindeer(), "Скажи [npc.name], что ты лучше всего подойдешь для разноса напитков и «поощрения» рабочих.<br/>Твоя производительность и оплата будут зависеть от твоей [style.italicsCorruption("+Attribute.MAJOR_CORRUPTION.getName()+")]."),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_ENCOURAGEMENT", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_WORK_FINISHED = new DialogueNode("Надзиратель оленей", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60 * 4*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_FINISHED", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getReindeer(), "[npc.Name] берет свое, и секс заканчивается...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "AFTER_SEX", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
}
