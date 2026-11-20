package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.5
 * @version 0.4.2.2
 * @author Innoxia
 */
public class RentalMommyDialogue {

	private static NPC getMommy() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ENCOUNTER = new DialogueNode("Арендованная мамочка", "", true) {
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)) {
				return UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "ENCOUNTER_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "ENCOUNTER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<50) {
					return new Response("Нанять "+UtilText.formatAsMoneyUncoloured(50, "охват"), "Тебе не хватает денег для этого!", null);
				}
				return new Response("Нанять "+UtilText.formatAsMoney(50, "охват"), "Нанять арендованную мамочку и провести время, отдыхая у неё на коленях.", MOMMYS_EXTRAS) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-50));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.mommyFound, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Отклонить", "Сказать арендованной мамочке, что ты пока не заинтересован в ней.", ENCOUNTER) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "DECLINE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.mommyFound, true);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode MOMMYS_EXTRAS = new DialogueNode("Арендованная мамочка", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS"));
			
			if(Main.game.isLactationContentEnabled()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_OFFER_BREASTFEEDING"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<1000) {
					return new Response("Секс в подчиненной позиции "+UtilText.formatAsMoneyUncoloured(1000, "охват"), "Тебе не хватает денег для этого!", null);
				}
				return new ResponseSex("Секс в подчиненной позиции "+UtilText.formatAsMoney(1000, "охват"), "Пойти за Мамочкой в её дом, чтобы позволить ей доминировать во время секса с тобой.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(getMommy()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						AFTER_SEX_MOMMY_AS_DOM,
						UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_SEX_SUB")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_SEX_SUB")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-1000);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<2000) {
					return new Response("Доминирующий секс "+UtilText.formatAsMoneyUncoloured(2000, "охват"), "Тебе не хватает денег для этого!", null);
				}
				return new ResponseSex("Доминирующий секс "+UtilText.formatAsMoney(2000, "охват"), "Пойти за Мамочкой в её дом, чтобы самому доминировать и заняться с ней сексом.",
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMommy()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						AFTER_SEX_MOMMY_AS_SUB,
						UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_SEX_DOM")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_SEX_DOM")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-2000);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getMoney()<500) {
					return new Response("Кормление грудью "+UtilText.formatAsMoneyUncoloured(500, "охват"), "Тебе не хватает денег для этого!", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Кормление грудью "+UtilText.formatAsMoneyUncoloured(500, "охват"), "Ты не можешь дотянуться ртом, так что Мамочка не сможет тебя покормить!", null);
				}
				return new Response("Кормление грудью "+UtilText.formatAsMoney(500, "охват"), "Пойти за Мамочкой в её дом, чтобы она тебя покормила грудью.", MOMMYS_EXTRAS_BREASTFEEDING) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-500);
						getMommy().setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
						getMommy().setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
						Main.game.getPlayer().ingestFluid(getMommy(), getMommy().getMilk(), SexAreaOrifice.MOUTH, 500);
					}
				};
				
			} else if(index==4) {
				if(Main.game.getPlayer().getMoney()<1000) {
					return new Response("Кормление грудью на людях "+UtilText.formatAsMoneyUncoloured(1000, "охват"), "Тебе не хватает денег для этого!", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Кормление грудью на людях "+UtilText.formatAsMoneyUncoloured(1000, "охват"), "Ты не можешь дотянуться ртом, так что Мамочка не сможет тебя покормить!", null);
				}
				return new Response("Кормление грудью на людях "+UtilText.formatAsMoney(1000, "охват"), "Остаться здесь, на скамейке, на виду у всех, чтобы мамочка тебя выкормила.", MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-1000);
						getMommy().setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
						getMommy().setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
						Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_LACTATION_OTHERS, 25);
						Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_EXHIBITIONIST, 10);
						Main.game.getPlayer().ingestFluid(getMommy(), getMommy().getMilk(), SexAreaOrifice.MOUTH, 500);
					}
				};
				
			} else if(index==5) {
				return new Response("Отклонить", "Отклонить дополнительные предложения мамочки и уйти.", ENCOUNTER) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_DECLINED"));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode MOMMYS_EXTRAS_BREASTFEEDING = new DialogueNode("Арендованная мамочка", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_BREASTFEEDING"));
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY"));
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_BREASTFEEDING"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Уйти", "Поблагодарить мамочку и уйти.", MOMMYS_EXTRAS_BREASTFEEDING) {
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
	
	public static final DialogueNode MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC = new DialogueNode("Арендованная мамочка", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Уйти", "Поблагодарить мамочку и уйти.", MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC) {
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
	
	public static final DialogueNode AFTER_SEX_MOMMY_AS_DOM = new DialogueNode("Завершено", "Арендованная мамочка отработала каждую копейку, и на этом секс подходит к концу...", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getNumberOfOrgasms(getMommy()) >= getMommy().getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_ORGASMED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_NO_ORGASM"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "AFTER_SEX_MOMMY_AS_DOM_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Уйти", "Поблагодарить мамочку и уйти.", AFTER_SEX_MOMMY_AS_DOM) {
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
	
	public static final DialogueNode AFTER_SEX_MOMMY_AS_SUB = new DialogueNode("Завершено", "Арендованная мамочка отработала каждую копейку, и на этом секс подходит к концу...", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getNumberOfOrgasms(getMommy()) >= getMommy().getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_ORGASMED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_NO_ORGASM"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "AFTER_SEX_MOMMY_AS_SUB_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Уйти", "Поблагодарить мамочку и уйти.", AFTER_SEX_MOMMY_AS_SUB) {
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
}
