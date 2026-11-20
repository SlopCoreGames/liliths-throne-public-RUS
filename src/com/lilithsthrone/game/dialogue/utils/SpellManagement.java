package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.1
 * @version 0.3.7.5
 * @author Innoxia
 */
public class SpellManagement {

    private static GameCharacter spellOwner;
    private static GameCharacter spellTarget;
    private static DialogueNode dialogueReturn;
    
    private static DialogueNode spellScreenAfterCasting;
    
    private static Spell spell;
    
    public static GameCharacter getSpellOwner() {
        if(spellOwner==null) {
            return Main.game.getPlayer();
        }
        return spellOwner;
    }

    public static GameCharacter getSpellTarget() {
        if(spellTarget==null) {
            return Main.game.getPlayer();
        }
        return spellTarget;
    }

    public static DialogueNode getDialogueReturn() {
        return dialogueReturn;
    }
    
    public static void setSpellOwner(GameCharacter spellOwner, DialogueNode dialogueReturn) {
        SpellManagement.spellOwner = spellOwner;
        SpellManagement.spellTarget = spellOwner;
        SpellManagement.dialogueReturn = dialogueReturn;
    }
    
    public static DialogueNode castSpell(Spell spell) {
    	SpellManagement.spellScreenAfterCasting = Main.game.getCurrentDialogueNode();
    	SpellManagement.spell = spell;
    	
    	spell.performOnSelection(0, getSpellOwner(), getSpellTarget(), null, getSpellOwner().getParty()); // Handles aura cost
    	
		Main.game.getTextStartStringBuilder().append(
				"<p style='text-align:center;'>"
					+ "<b>Casting '<span style='color:"+spell.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spell.getName())+"</span>':</b>"
					+ "<br/>"
					+spell.applyEffect(getSpellOwner(), getSpellTarget(), true, false)
				+"</p>");
		
    	return SPELL_CAST_DIALOGUE;
    }
    
    private static Response getResponses1To9(int index) {
    	if(index==1) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_EARTH) {
    			return new Response("Земля", UtilText.parse(getSpellOwner(), "Вы уже просматриваете заклинания школы [npc.namePos] Земля!"), null);
    		}
			return new Response("Земля", UtilText.parse(getSpellOwner(), "Просмотреть заклинания и улучшения школы [npc.namePos] Земля."), CHARACTER_SPELLS_EARTH);
			
		} else if(index==2) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_WATER) {
    			return new Response("Вода", UtilText.parse(getSpellOwner(), "Вы уже просматриваете заклинания школы [npc.namePos] Вода!"), null);
    		}
			return new Response("Вода", UtilText.parse(getSpellOwner(), "Просмотреть заклинания и улучшения школы [npc.namePos] Вода."), CHARACTER_SPELLS_WATER);
			
		} else if(index==3) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_FIRE) {
    			return new Response("Огненный", UtilText.parse(getSpellOwner(), "Вы уже просматриваете заклинания школы [npc.namePos] Огонь!"), null);
    		}
			return new Response("Огненный", UtilText.parse(getSpellOwner(), "Просмотреть заклинания и улучшения школы [npc.namePos] Огонь."), CHARACTER_SPELLS_FIRE);
			
		} else if(index==4) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_AIR) {
    			return new Response("Воздух", UtilText.parse(getSpellOwner(), "Вы уже просматриваете заклинания школы [npc.namePos] Воздух!"), null);
    		}
			return new Response("Воздух", UtilText.parse(getSpellOwner(), "Просмотреть [npc.namePos] заклинаний и улучшений в школе Воздуха."), CHARACTER_SPELLS_AIR);
			
		} else if(index==5) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_ARCANE) {
    			return new Response("Аркана", UtilText.parse(getSpellOwner(), "Вы уже просматриваете [npc.namePos] заклинаний Arcane!"), null);
    		}
			return new Response("Аркана", UtilText.parse(getSpellOwner(), "Просмотреть [npc.namePos] заклинаний и улучшений в школе Arcane."), CHARACTER_SPELLS_ARCANE);
			
		} else if(index==6) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_MISC) {
    			return new Response("Разное", UtilText.parse(getSpellOwner(), "Вы уже просматриваете [npc.namePos] прочих заклинаний!"), null);
    		}
			return new Response("Разное", UtilText.parse(getSpellOwner(), "Просмотреть [npc.namePos] прочих заклинаний, которые [npc.sheHas] получил из оружия или особых событий."), CHARACTER_SPELLS_MISC);
			
		} else if(index==9) {
			return new ResponseEffectsOnly(
					UtilText.parse(getSpellTarget(), "Цель: <b style='color:"+getSpellTarget().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
					"Переключить цель для наложения заклинаний.") {
				@Override
				public void effects() {
					List<GameCharacter> companions = Util.newArrayListOfValues(Main.game.getPlayer());
					companions.addAll(Main.game.getPlayer().getCompanions());
					if(!companions.isEmpty()) {
						for(int i=0; i<companions.size();i++) {
							if(companions.get(i).equals(getSpellTarget())) {
								if(i==companions.size()-1) {
									spellTarget = companions.get(0);
									break;
									
								} else {
									spellTarget = companions.get(i+1);
									break;
								}
							}
						}
					}
					Main.game.updateResponses();
				}
			};
		}
    	return null;
    }

    public static final DialogueNode CHARACTER_SPELLS_ARCANE = new DialogueNode("Заклинания Arcane", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.ARCANE.getDescription()
						+"</div><div class='container-full-width inner' style='text-align:center;'>[style.boldArcane(Способность школы Arcane:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)
									?"[style.colourDisabled("+SpellSchool.ARCANE.getPassiveBuff()+")]<br/>(Для разблокировки нужно знать как минимум <b>три</b> заклинания школы Arcane.)"
									:"[style.colourGood("+SpellSchool.ARCANE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if(index==10) {
				return new Response("Сбросить Arcane", UtilText.parse(getSpellOwner(), "Сбросить улучшения [npc.namePos] Arcane, вернув все потраченные очки. Заклинания [npc.Her] сброшены не будут."), CHARACTER_SPELLS_ARCANE) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.ARCANE);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_EARTH = new DialogueNode("Заклинания Земли", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.EARTH.getDescription()
						+"</div><div class='container-full-width inner' style='text-align:center;'>[style.boldEarth(Способность школы Земли:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
									?"[style.colourDisabled("+SpellSchool.EARTH.getPassiveBuff()+")]<br/>(Для разблокировки нужно знать как минимум <b>три</b> заклинания школы Земли.)"
									:"[style.colourGood("+SpellSchool.EARTH.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			}  else if(index==10) {
				return new Response("Сбросить Землю", UtilText.parse(getSpellOwner(), "Сбросить улучшения [npc.namePos] Земли, вернув все потраченные очки. Заклинания [npc.Her] сброшены не будут."), CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.EARTH);
					}
				};
				
			} else if(index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_WATER = new DialogueNode("Заклинания Воды", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.WATER.getDescription()
						+"</div><div class='container-full-width inner' style='text-align:center;'>[style.boldWater(Способность школы Воды:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
									?"[style.colourDisabled("+SpellSchool.WATER.getPassiveBuff()+")]<br/>(Для разблокировки нужно знать как минимум <b>три</b> заклинания школы Воды.)"
									:"[style.colourGood("+SpellSchool.WATER.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if(index==10) {
				return new Response("Сбросить Воду", UtilText.parse(getSpellOwner(), "Сбросить [npc.namePos] улучшения Воды, возвращая все потраченные очки. [npc.Her] заклинания сброшены не будут."), CHARACTER_SPELLS_WATER) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.WATER);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_AIR = new DialogueNode("Заклинания Воздуха", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.AIR.getDescription()
						+"</div><div class='container-full-width inner' style='text-align:center;'>[style.boldAir(Способность школы Воздуха:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)
									?"[style.colourDisabled("+SpellSchool.AIR.getPassiveBuff()+")]<br/>(Для разблокировки нужно знать как минимум <b>три</b> заклинания школы Воздуха.)"
									:"[style.colourGood("+SpellSchool.AIR.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			}  else if(index==10) {
				return new Response("Сбросить Воздух", UtilText.parse(getSpellOwner(), "Сбросить [npc.namePos] улучшения Воздуха, возвращая все потраченные очки. [npc.Her] заклинания сброшены не будут."), CHARACTER_SPELLS_AIR) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.AIR);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_FIRE = new DialogueNode("Заклинания Огня", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.FIRE.getDescription()
						+"</div><div class='container-full-width inner' style='text-align:center;'>[style.boldFire(Способность школы Огня:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)
									?"[style.colourDisabled("+SpellSchool.FIRE.getPassiveBuff()+")]<br/>(Требуется знание как минимум <b>трех</b> заклинаний школы Огня, чтобы разблокировать.)"
									:"[style.colourGood("+SpellSchool.FIRE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if(index==10) {
				return new Response("Сбросить Огонь", UtilText.parse(getSpellOwner(), "Сбросить [npc.namePos] улучшения Огня, возвращая все потраченные очки. [npc.Her] заклинания сброшены не будут."), CHARACTER_SPELLS_FIRE) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.FIRE);
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};

	public static final DialogueNode CHARACTER_SPELLS_MISC = new DialogueNode("Разные заклинания", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellMiscTreeDisplay(getSpellOwner(), getSpellTarget())
						+"</div><div class='container-full-width' style='width:50%; padding:8px; margin:0;'>Хотя многие заклинания подробно описаны в относительно легкодоступных книгах заклинаний, существуют и те, что ускользнули от каталогизации и записи в тексты. Хотя все эти неуловимые заклинания относятся к одной из пяти основных школ арканы, в тех немногих книгах, где они кратко упоминаются, их обычно классифицируют как «разные».<br/><br/>Хотя их нельзя выучить по книге, эти заклинания можно использовать, экипировав специально зачарованное оружие. Также существует возможность получить эти заклинания через уникальные события...</div><div class='container-full-width inner' style='text-align:center;'>[style.boldSpell(Разные заклинания)] добываются из особого оружия и уникальных событий.</div></div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=9) {
				return getResponses1To9(index);
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	

	public static final DialogueNode SPELL_CAST_DIALOGUE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(spell.getName());
		}
		@Override
		public String getContent(){
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Вернуться к экрану управления заклинаниями.", spellScreenAfterCasting);
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
}
