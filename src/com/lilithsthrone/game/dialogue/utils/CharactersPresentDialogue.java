package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.3
 * @version 0.3.5.1
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	public static String menuContent;
	public static String menuTitle;
	public static NPC characterViewed = null;
	
	private static NPC targetedCharacterForSex;
	private static NPC companionCharacter;
	
	public static void resetContent(GameCharacter characterViewed) {
		if(characterViewed==null) {
			CharactersPresentDialogue.characterViewed = Main.game.getCharactersPresent().get(0);
		} else {
			CharactersPresentDialogue.characterViewed = (NPC) characterViewed;
		}
		menuTitle = "Присутствующие персонажи ("+Util.capitaliseSentence(CharactersPresentDialogue.characterViewed.getName(true))+")";
		menuContent = ((NPC) CharactersPresentDialogue.characterViewed).getCharacterInformationScreen(true);

		if(Main.game.getPlayer().hasCompanion(CharactersPresentDialogue.characterViewed)) {
			if(CharactersPresentDialogue.characterViewed.isSlave() && CharactersPresentDialogue.characterViewed.getOwner().isPlayer()) {
				SlaveDialogue.initDialogue((NPC) CharactersPresentDialogue.characterViewed, true);
			} else {
				OccupantDialogue.initDialogue((NPC) CharactersPresentDialogue.characterViewed, false, true);
			}
			CompanionManagement.initManagement(MENU, 2, CharactersPresentDialogue.characterViewed);
		}
		
//		Main.game.setActiveNPC(characterViewed);
		targetedCharacterForSex = (NPC) CharactersPresentDialogue.characterViewed;

		if(Main.game.getPlayer().getCompanions().size()>1) {
			companionCharacter = (NPC) Main.game.getPlayer().getMainCompanion();
			if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
				companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
			}
		} else {
			companionCharacter = null;
		}
	}

	private static NPC getCharacterViewed() {
		return characterViewed;
	}
	
	private static NPC getTargetedCharacterForSex() {
		if(!Main.game.getCharactersPresent().contains(targetedCharacterForSex)) {
			targetedCharacterForSex = (NPC) CharactersPresentDialogue.characterViewed;
			if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
				if(Main.game.getPlayer().getCompanions().size()>1) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			}
		}
		return targetedCharacterForSex;
	}
	
	private static NPC getCompanionCharacter() {
		if(!Main.game.getCharactersPresent().contains(companionCharacter)) {
			if(Main.game.getPlayer().getCompanions().size()>1) {
				companionCharacter = (NPC) Main.game.getPlayer().getMainCompanion();
				if(Objects.equals(companionCharacter, targetedCharacterForSex)) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			} else {
				companionCharacter = null;
			}
		}
		return companionCharacter;
	}

	private static String getTextFilePath() {
		if(targetedCharacterForSex.isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}
	
	public static final DialogueNode MENU = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return menuTitle;
		}

		@Override
		public String getContent() {
			return menuContent;
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().hasCompanion(characterViewed)) {
				if(index == 0) {
					return "Персонажи";
				} else if(index == 1) {
					return UtilText.parse("[style.colourSex(Секс)]");
				} else if(index == 2) {
					return UtilText.parse("[style.colourCompanion(Управление)]");
				}
				
			} else {
				if(index == 0) {
					return "Персонажи";
				} else if(index == 1) {
					return "Секс";
				} else if(index == 2) {
					return "Управление";
				}
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if(responseTab==0) {
				if (index == 0) {
					return new ResponseEffectsOnly("Назад", "Перестать осматривать присутствующих персонажей и вернуться к основной игре."){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.mainController.openCharactersPresent();
						}
					};
					
				} else if (index <= charactersPresent.size()) {
					String title = "[npc.Name]";
					String description = "Внимательно осмотреть [npc.name].";
					
					if(charactersPresent.get(index - 1).equals(characterViewed)) {
						if(!charactersPresent.get(index - 1).isRaceConcealed() || charactersPresent.get(index - 1).isPlayerKnowsName()) {
							title = "[style.colourDisabled([npc.Name])]";
							description = "Вы уже смотрите на [npc.name]!";
						}else {
							title = "[style.colourDisabled(Неизвестный)]";
							description = "Вы не знаете, как выглядит этот человек!";
						}
							
						
					} else if(Main.game.getPlayer().hasCompanion(charactersPresent.get(index - 1))) {
						title = "[style.colourCompanion([npc.Name])]";
						description = "Внимательно осмотрите свою [style.colourCompanion(спутницу)], [npc.name].";
					}
					
					return new Response(
							UtilText.parse(charactersPresent.get(index - 1), title),
							UtilText.parse(charactersPresent.get(index - 1), description),
							charactersPresent.get(index - 1).equals(characterViewed)?null:MENU) {
						@Override
						public void effects() {
							characterViewed = charactersPresent.get(index-1);
							menuTitle = "Присутствующие персонажи ("+Util.capitaliseSentence(charactersPresent.get(index - 1).getName(true))+")";
							menuContent = ((NPC) charactersPresent.get(index - 1)).getCharacterInformationScreen(true);
						}
					};
					
				} else {
					return null;
				}
				
			} else if (responseTab==1 && Main.game.getPlayer().hasCompanion(characterViewed)) {
				if (index == 0) {
					return new ResponseEffectsOnly("Назад", "Перестать осматривать присутствующих персонажей и вернуться к основной игре."){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.mainController.openCharactersPresent();
						}
					};
				} 
				if(index>0 && index<5 && !characterViewed.isCompanionAvailableForSex(true)) {
					if(index==1) {
						return new Response("Секс", characterViewed.getCompanionSexRejectionReason(true), null);
					}
					return null;
				}
				if(index>5 && index<10 && !characterViewed.isCompanionAvailableForSex(false)) {
					if(index==6) {
						return new Response("Секс в подчиненной позиции", characterViewed.getCompanionSexRejectionReason(false), null);
					}
					return null;
				}
				if(characterViewed.isSlave() && characterViewed.getOwner().isPlayer()) {
					return SlaveDialogue.SLAVE_START.getResponse(responseTab, index);
				} else {
					return OccupantDialogue.OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab==2 && Main.game.getPlayer().hasCompanion(characterViewed)) {
				return CompanionManagement.getManagementResponses(index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь насладился, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				List<GameCharacter> parsingCharacters = new ArrayList<>(Main.sex.getAllParticipants());
				parsingCharacters.remove(Main.game.getPlayer());
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", parsingCharacters);
				
			} else if(Main.sex.getNumberOfOrgasms(getCharacterViewed()) >= getCharacterViewed().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", getTargetedCharacterForSex());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", getTargetedCharacterForSex());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Реши, что делать дальше.", AFTER_SEX) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.setActiveNPC(null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	

	public static final DialogueNode PERKS = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(getCharacterViewed(), "Древо навыков [npc.NamePos]");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parse(characterViewed,
					"<details><summary>[style.boldPerk(Информация о Перках и Чертах)]</summary>[style.colourPerk(Перки)] (круглые иконки) дают постоянные бонусы к атрибутам [npc.namePos].<br/>[style.colourPerk(Черты)] (квадратные иконки) дают уникальные эффекты для [npc.name]. В отличие от перков, <b>черты не будут влиять на [npc.name], пока их не вставят в панель [npc.her] «Активные черты»</b>.<br/>Для разблокировки перков требуются очки перков. [npc.Name] получает одно очко перка каждый раз, когда [npc.she] повышает уровень, и получает дополнительные два очка перка каждые пять уровней.<br/><br/>В дополнение к перкам, которые можно купить за очки перков, существуют также несколько специальных скрытых перков, которые открываются через особые события.</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterViewed, true));
			
			UtilText.nodeContentSB.append("</div>");
			
			if(!characterViewed.isElemental() && !characterViewed.isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'><i>Пожалуйста, учтите, что это древо перков находится в процессе разработки. Это не финальная версия, а лишь проверка концепции!</i></div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Древо навыков", UtilText.parse(characterViewed, "Вы уже распределяете очки навыков [npc.namePos]."), null);
				
			} else if(index==7) {
				return new Response("Сбросить перки", "Сбросить все перки и черты [npc.namePos], вернув все потраченные очки. (Это временная мера, так как древо перков всё еще находится в разработке.)", PERKS) {
					@Override
					public void effects() {
						characterViewed.resetPerksMap(false, false);
					}
				};
			}
			
			return MENU.getResponse(responseTab, index);
		}
	};
}
