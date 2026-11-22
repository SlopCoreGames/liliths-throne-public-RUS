package com.lilithsthrone.game.combat.spells;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.EffectBenefit;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public enum Spell {

	// FIRE:
	
	FIREBALL(false,
			SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			DamageType.FIRE,
			false,
			"Огненный шар",
			"fireball",
			"Призывает шар аркана, который можно запустить в цель.",
			30,
			DamageVariance.LOW,
			75,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.FIREBALL_1,
					SpellUpgrade.FIREBALL_2,
					SpellUpgrade.FIREBALL_3),
			null, null) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.LINGERING_FLAMES, 2));
			} else {
				return new HashMap<>();
			}
		}
		
		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2) && !caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_3)) {
				return 15;
			}
			return 30;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			
			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Силами вечности, я снимаю этот заклятие с маelstrom внутри! Выйди, огонь разрушения!",
													"За пределами огня, я слышу призыв аркана! Через меня, теперь ад освобожден!",
													"Пусть темная пустота разрушит древние печати, которые удерживают огонь ада на расстоянии! Иди, огонь ярости!"),
											"Призывая вихрь аркана вокруг твоего [pc.arm], ты сосредотачиваешь его силу в шар бурлящего огня, прежде чем запустить его в себя!",
											"Призывая вихрь аркана вокруг твоего [pc.arm], ты сосредотачиваешь его силу в шар бурлящего огня, прежде чем запустить его в [npc.name]!",
											"",
											"Призывая вихрь аркана вокруг [npc.her] [npc.arm], [npc.she] сосредотачивает его силу в шар бурлящего огня, прежде чем запустить его прямо в тебя!",
											"Призывая вихрь аркана вокруг [npc1.her] [npc1.arm], [npc1.name] сосредотачивает его силу в шар бурлящего огня, прежде чем запустить его прямо в [npc2.name]!")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
				descriptionSB.append("Огонь мгновенно разделяется на две части после того, как он был запущен!");
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(applyDamage(caster, target, damage));

				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
				
				// Second fireball:
				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
					damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
					GameCharacter secondaryTarget = Main.combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<br/>Второй огненный шар резко меняет траекторию, чтобы поразить "+UtilText.parse(target,"[npc.name]")+" цель во второй раз!");
						
						descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
						descriptionSB.append(applyDamage(caster, target, damage));
						
					} else {
						descriptionSB.append("<br/>Второй огненный шар резко меняет траекторию, чтобы поразить "+(secondaryTarget.isPlayer()?"вы":UtilText.parse(secondaryTarget,"[npc.name]"))+"!");
						
						descriptionSB.append(getDamageDescription(caster, secondaryTarget, damage, isHit, isCritical));
						descriptionSB.append(applyDamage(caster, secondaryTarget, damage));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	FLASH(false,
			SpellSchool.FIRE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.FIRE,
			false,
			"Вспышка",
			"flash",
			"Создает ослепительную вспышку света, которая дезориентирует цель.",
			0,
			DamageVariance.LOW,
			50,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH, 1)),
			Util.newArrayListOfValues(
					SpellUpgrade.FLASH_1,
					SpellUpgrade.FLASH_2,
					SpellUpgrade.FLASH_3),
			null,
			Util.newArrayListOfValues("[style.colourExcellent(Ослепляет)] цель на [style.colourTerrible(-1)] [style.colourActionPoints(очков действия)]!")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH_1, 1));
			} else {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH, 1));
			}
		}
		
		@Override
		public int getBaseCost(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_3)) {
				return 25;
			} else {
				return 50;
			}
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return "Ослепление на [style.colourTerrible(-2)] очка(ей) действия!";
			}
			return "Ослепление на [style.colourTerrible(-1)] очко(ей) действия!";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			
			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Закрытая на тысячу лет, сила, дремлющая во мне, пробудилась! Теперь, будьте свидетелями ослепительной ярости вселенной, рождающейся!",
													"Свет миллиона звезд - ничто по сравнению с силой, которую я теперь высвобождаю! Пусть мои аркана будут разрушены, и ослеплены все, кто осмеливается стоять передо мной!",
													"Силой солнца и взглядом луны, пусть небеса сами станут свидетелями силы, которую я теперь высвобождаю! Взирайте на конец миров, и отчаяние!"),
											"Одним движением руки, ты вызываешь ослепительный вспышку света прямо перед твоим собственным лицом!",
											"Одним движением руки, ты вызываешь ослепительный вспышку света прямо перед лицом [npc.namePos]!",
											"",
											"Одним движением руки [npc.her], [npc.name] вызываешь ослепительный вспышку света прямо перед твоим лицом!",
											"Одним движением руки [npc1.her], [npc1.name] вызываешь ослепительный вспышку света прямо перед лицом [npc2.namePos]!")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
				descriptionSB.append("Вторая вспышка света дугой уходит от первой, ища другую цель!");
			}

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				
				// Second flash:
				if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
					GameCharacter secondaryTarget = Main.combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<br/>"
								+"Вторая вспышка не находит другую цель, и быстро гаснет...");
						
					} else {
						descriptionSB.append("<br/>"
								+"Вторая вспышка бьет перед "+UtilText.parse(secondaryTarget,"[npc.namePos], ослепляя [npc.herHim]!"));

						descriptionSB.append(getDamageDescription(caster, secondaryTarget, 0, isHit, isCritical));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
				
			} else {
				descriptionSB.append("<p style='text-align:center;'>"
						+ "[style.italicsBad(Вспышка пролетает мимо!)]"
						+ "</p>");
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	CLOAK_OF_FLAMES(false,
			SpellSchool.FIRE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.FIRE,
			true,
			"Плащ Огня",
			"cloak_of_flames",
			"Окутывает цель в защитный плащ из аркана, даруя ей улучшенную устойчивость к огню и льду.",
			0,
			DamageVariance.LOW,
			50,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3)),
			Util.newArrayListOfValues(
					SpellUpgrade.CLOAK_OF_FLAMES_1,
					SpellUpgrade.CLOAK_OF_FLAMES_2,
					SpellUpgrade.CLOAK_OF_FLAMES_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_FIRE, 5),
					new Value<>(Attribute.RESISTANCE_ICE, 10)),
			Util.newArrayListOfValues("Действует [style.colourGood(3 хода)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_3, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_2, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_1, 3));
				
			} else {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3));
			}
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Окутывает цель в защитный плащ из огня.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Силой моей, пусть тают ледники, и пусть погаснут тысячи солнц! О, ярость звезд, я призываю тебя защитить [npc.master]!",
													"Знай теперь, что пламя ада само подчиняется каждому моему приказу, и из огненной бездны измерения огня я вызываю силу, чтобы защитить себя от всего вреда!",
													"Огнем и яростью, я вызываю адское пламя самого солнца! Приди теперь, аркана inferno, защити [npc.master] от всего, что осмеливается навредить [npc.herHim]!"),
											"Одним взмахом [pc.arm], ты вызываешь защитный плащ из аркана огня вокруг себя!",
											"Одним взмахом [pc.arm], ты вызываешь защитный плащ из аркана огня вокруг [npc.name]!",
											"Одним взмахом [npc.her] [npc.arm], [npc.name] вызываешь защитный плащ из аркана огня вокруг [npc.herself]!",
											"Одним взмахом [npc.her] [npc.arm], [npc.name] вызываешь защитный плащ из аркана огня вокруг тебя!",
											"Одним взмахом [npc1.her] [npc1.arm], [npc1.name] вызываешь защитный плащ из аркана огня вокруг [npc2.name]!"));
			
			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_FIRE(false,
			SpellSchool.FIRE,
			SpellType.SUMMON,
			DamageType.FIRE,
			true,
			"Стихийный огонь",
			"elemental_fire",
			"Вызови своего элементаля в физической форме, связав его со школой огня.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_FIRE_1,
					SpellUpgrade.ELEMENTAL_FIRE_2,
					SpellUpgrade.ELEMENTAL_FIRE_3A,
					SpellUpgrade.ELEMENTAL_FIRE_3B),
			null, Util.newArrayListOfValues("Вызываешь [style.colourArcane(Elemental)] в форме [style.colourSchoolFire(Fire)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знает этого заклинания, поэтому не может его использовать!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Заклинания не могут быть использованы, пока ты в плену!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "Во время боя заклинания могут быть использованы только как боевой ход!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_FIRE?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Заклинания могут быть использованы только в нейтральной сцене!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner) && !owner.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] как минимум <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] для того, чтобы использовать это заклинание!"));
				
			} else {
				String description = owner.isPlayer()?"Призови своего элемента, связав его со школой Огня!":"Заставь [npc.name] призвать [npc.her] элемента, связав его со школой Огня!";
				String cost = "Это будет стоить <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(аура)]!";
				if(owner.getMana()<this.getModifiedCost(owner)) {
					cost = "Это будет стоить <b>"+Math.round((owner.getMana()-this.getModifiedCost(owner))*-0.25f)+"</b> [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]!";
				}
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает твоего элементаля в форме огня.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}
			
			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.FIRE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
									?Util.randomItemFrom(
									Util.newArrayListOfValues(
										"[npc.speech(По древним обрядам огня, я призываю ад и воплощение ярости! Ответь на зов твоего [npc.master], [npc2.name], и, через уничтожение миллиона измерений, будь связан с моей волей!)] ",
										"[npc.speech(Пусть огонь поглотит, и ад внутри меня будет развязан! Огонь и ярость, твой [npc.master] зовёт! Внемли и явись, [npc2.name]!)] ",
										"[npc.speech(Пусть огонь поглотит, и ад внутри меня будет развязан! Огонь и ярость, твой [npc.master] зовет! Послушай и будь призвана, [npc2.name]!)] "))
									:"")
								+(caster.isPlayer()
									?"С вспышкой света и вспышкой огня, ты привязываешь твоего элементаля, [npc2.name], к школе Огня!"
									:"С вспышкой света и вспышкой огня, [npc1.name] привязывает элементаля [npc1.her], [npc2.name], к школе Огня!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древним обрядам огня, я призываю ад и воплощение ярости! Ответь на зов твоего [npc.master], [npc2.name], и, через уничтожение миллиона измерений, будь связан с моей волей!)] ",
											"[npc.speech(Пусть огонь поглотит, и ад внутри меня будет развязан! Огонь и ярость, твой [npc.master] зовёт! Внемли и явись, [npc2.name]!)] ",
											"[npc.speech(Пусть огонь поглотит, и ад внутри меня будет развязан! Огонь и ярость, твой [npc.master] зовет! Послушай и будь призвана, [npc2.name]!)] "))
										:"")
								+(caster.isPlayer()
									?"С вспышкой света и вспышкой огня, ты призываешь твоего элементаля, [npc2.name], привязывая [npc2.herHim] к школе Огня!"
									:"С вспышкой света и вспышкой огня, [npc1.name] вызывает [npc1.her] элементаль, [npc2.name], связывая [npc2.herHim] с школой Огня!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	// WATER:

	ICE_SHARD(false,
			SpellSchool.WATER,
			SpellType.OFFENSIVE,
			DamageType.ICE,
			false,
			"Ледяной осколок",
			"ice_shard",
			"Вызывает осколок льда, который можно запустить в цель.",
			25,
			DamageVariance.LOW,
			35,
			null, Util.newArrayListOfValues(
							SpellUpgrade.ICE_SHARD_1,
							SpellUpgrade.ICE_SHARD_2,
							SpellUpgrade.ICE_SHARD_3), null, null) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3) && isCritical) {
					return Util.newHashMapOfValues(
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3),
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FROZEN, 1));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)){
					return Util.newHashMapOfValues(
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Свидетельствую теперь, ужас беззвездной пустоты! Дождем льда и яростью метели, пусть моя аркана сила будет развязана!",
													"Печать внутри меня была разорвана, и моей силой теперь будет заморожена сама вселенная! Град, мокрый снег и лед, услышьте мой призыв и придите!",
													"Из-за замерзшей пустоты теперь моя сила развязана! Пересекая границу из замерзшего царства хаоса, пусть моя сила будет проявлена!"),
											"Призывая вихрь воды из влаги в воздухе, вы сосредотачиваете свою энергию на замораживании ее на месте, создавая осколок льда, который вы затем запускаете в себя!",
											"Призывая вихрь воды из влаги в воздухе, вы сосредотачиваете свою энергию на замораживании ее на месте, создавая осколок льда, который вы затем запускаете в [npc.name]!",
											"",
											"Призывая вихрь воды из влаги в воздухе, [npc1.name] сосредотачивает [npc1.her] энергию на замораживании ее на месте, создавая осколок льда, который [npc.she] затем запускает в вас!",
											"Призывая вихрь воды из влаги в воздухе, [npc1.name] сосредотачивает [npc1.her] энергию на замораживании ее на месте, создавая осколок льда, который [npc.she] затем запускает в [npc2.name]!")
								);
			
			if(isHit && isCritical && caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_2)) {
				descriptionSB.append(" Замерзший туман взрывается, когда осколок льда проходит через него");
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3)) {
					descriptionSB.append(", замуровывая всё в ближайшем окружении в тонкий слой льда!");
				} else {
					descriptionSB.append("!");
				}
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
		
		public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Цель находится под эффектом статуса «замораживающий туман».");
	    }
		
		//Differs from normal version; spells have special crit requirements.
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return target.hasStatusEffect(StatusEffect.FREEZING_FOG) || Main.combat.getStatusEffectsToApply().get(target).containsKey(StatusEffect.FREEZING_FOG);
		}
	},

	RAIN_CLOUD(false,
			SpellSchool.WATER,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.ICE,
			false,
			"Дождевая туча",
			"rain_cloud",
			"Призывает над головой цели небольшое облако аркана-напитанного дождя, который истощает способность цели творить заклинания.",
			0,
			DamageVariance.LOW,
			33,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.RAIN_CLOUD_1,
					SpellUpgrade.RAIN_CLOUD_2,
					SpellUpgrade.RAIN_CLOUD_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.SPELL_COST_MODIFIER, -25)), Util.newArrayListOfValues("Длится [style.colourGood(3 хода)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DEEP_CHILL, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает над целью дождевое облако.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(//TODO chuuni three from here
													"Пусть небеса разверзнутся, а потопы захлестнут Землю! Силой, что живет во мне, я разрываю небеса и дарую тебе водную могилу!"),
											"Резким взмахом своего [pc.arm] вы призываете дождевое облако над собственной головой!",
											"Резким взмахом своего [pc.arm] вы призываете дождевое облако над головой [npc.namePos]!",
											"",
											"Резким взмахом [npc.her] [npc.arm], [npc.name] призывает дождевое облако над вашей головой!",
											"Резким взмахом [npc1.her] [npc1.arm], [npc1.name] призывает дождевое облако над головой [npc2.namePos]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	SOOTHING_WATERS(false,
			SpellSchool.WATER,
			SpellType.DEFENSIVE_HEAL,
			DamageType.ICE,
			true,
			"Успокаивающие воды",
			"soothing_waters",
			"Призывает шар успокаивающей воды, пропитанной арканной энергией, которая восстанавливает "+Attribute.HEALTH_MAXIMUM.getName()+" каждого, кто её выпьет.",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.SOOTHING_WATERS_1_CLEAN,
					SpellUpgrade.SOOTHING_WATERS_2_CLEAN,
					SpellUpgrade.SOOTHING_WATERS_1,
					SpellUpgrade.SOOTHING_WATERS_2,
					SpellUpgrade.SOOTHING_WATERS_3),
			null, Util.newArrayListOfValues("Заклинания не могут быть использованы в плену!"+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.soothingWatersUpgradeTree;
		}
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знаю этого заклинания, поэтому не могу его использовать!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Заклинания могут быть использованы только в нейтральной сцене!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "Во время боя заклинания можно применять только как боевой приём!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_WATER?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Заклинания можно применять только в нейтральной сцене!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] как минимум <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] для того, чтобы использовать это заклинание!"));
				
			} else {
				String description = owner.isPlayer()?"Запустите заклинание \"Успокаивающие воды\"":"Заставить [npc.name] использовать Волшебную воду";
				if(owner==target) {
					description+=" на [npc.herself]!";
				} else {
					description+=" на [npc2.name]!";
				}
				String cost = "Это будет стоить <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(аура)]!";
				return new Value<>(true, UtilText.parse(owner, target, description+"<br/>"+cost));
			}
		}
		@Override
		public int getAPCost() {
			return 3;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Исцеляет цель.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Силой потока и ручья, реки и моря, я призываю вечные течения, которые не знают конца! Я передаю свою силу тебе и призываю источник вечной жизни!"),
											"С легким взмахом [pc.hand], ты вызываешь шар исцеляющей воды, который ты быстро пьешь.",
											"С легким взмахом [pc.hand], ты вызываешь шар исцеляющей воды, который ты посылаешь [npc.name] пить.",
											"С легким взмахом [npc.her] [npc.hand], [npc.name] вызываешь шар исцеляющей воды, который [npc.she] быстро пьет.",
											"С легким взмахом [npc.her] [npc.hand], [npc.name] вызывает шар с целебной водой, который [npc.she] посылает тебе, чтобы ты мог выпить.",
											"С легким взмахом [npc.her] [npc.hand], [npc.name] вызывает шар с целебной водой, который [npc.she] посылает [npc2.name], чтобы он мог выпить."));

			if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3) ) {
				descriptionSB.append("От основного шара отделились несколько меньших шаров с водой!");
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));

					descriptionSB.append("<br/>"
								+ "Шар с водой исцеляет "+UtilText.parse(target,"[npc.name]")+"на общую сумму "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" и "
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
//					descriptionSB.append("<br/>"
//											+ UtilText.parse(target, "One of the small orbs circles around to heal [npc.name] for a second time, restoring a total of "
//																		+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
//																		+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"));
//					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f));
//					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f);
					
					if(Main.game.isInCombat()) {
						List<GameCharacter> alliesPlusCaster = new ArrayList<>(Main.combat.getAllies(caster));
						alliesPlusCaster.add(caster);
						for(GameCharacter combatant : alliesPlusCaster) {
							descriptionSB.append("<br/>"
									+ UtilText.parse(combatant, "Один из маленьких шаров летит в сторону [npc.name], исцеляя [npc.herHim] на общую сумму "
																+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" и "
																+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"));
							descriptionSB.append(applyDamage(caster, combatant, -combatant.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f));
							combatant.incrementMana(combatant.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f);
							if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1_CLEAN)) {
								descriptionSB.append(UtilText.parse(combatant, "<br/>[npc.NamePos] тело и вся одежда [npc.her] [style.colourAqua(cleaned)] заклинанием!"));
								combatant.cleanAllClothing(false, false);
								combatant.cleanAllDirtySlots(true);
							}
							if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2_CLEAN)) {
								descriptionSB.append(UtilText.parse(combatant, "<br/>[npc.NamePos] тело [style.colourAqua(thoroughly washed)] заклинанием!"));
								combatant.drainTotalFluidsStored(SexAreaOrifice.ANUS, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.VAGINA, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, 250);
							}
						}
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "Амулет воды исцеляет "+UtilText.parse(target,"[npc.name]")+" на сумму "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" и "
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+"Амулет воды исцеляет "+UtilText.parse(target,"[npc.name]")+" на сумму "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" и "
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "Орбита воды исцеляет "+UtilText.parse(target,"[npc.name]")+" на сумму "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
				}
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1_CLEAN)) {
					descriptionSB.append(UtilText.parse(target, "<br/>[npc.NamePos] тело и вся [npc.her] одежда [style.colourAqua(чистая)] заклинанием!"));
					target.cleanAllClothing(false, false);
					target.cleanAllDirtySlots(true);
				}
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2_CLEAN)) {
					descriptionSB.append(UtilText.parse(target, "<br/>[npc.NamePos] тело [style.colourAqua(thoroughly washed)] заклинанием!"));
					target.drainTotalFluidsStored(SexAreaOrifice.ANUS, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, 250);
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_WATER(false,
			SpellSchool.WATER,
			SpellType.SUMMON,
			DamageType.ICE,
			true,
			"Стихийная вода",
			"elemental_water",
			"Призовите своего элемента в физической форме, связав его со школой воды.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_WATER_1,
					SpellUpgrade.ELEMENTAL_WATER_2,
					SpellUpgrade.ELEMENTAL_WATER_3A,
					SpellUpgrade.ELEMENTAL_WATER_3B),
			null, Util.newArrayListOfValues("Призывает [style.colourArcane(Элементал)] в форме [style.colourSchoolWater(Вода)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знает этого заклинания, поэтому не может его использовать!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Заклинания не могут быть использованы в плену!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "Во время боя заклинания могут быть использованы только как боевой ход!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_WATER?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Заклинания могут быть использованы только в нейтральной сцене!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] как минимум <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] для того, чтобы использовать это заклинание!"));
				
			} else {
				String description = owner.isPlayer()?"Призовите ваше элементальное существо, связав его со школой Воды!":"Заставьте [npc.name] призвать [npc.her] элементальное существо, связав его со школой Воды!";
				String cost = "Это будет стоить <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(аура)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает ваше элементальное существо в форме воды.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.WATER);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему обряду моря и неба, я призываю вечный поток! Ответь на зов твоего [npc.master], [npc2.name], и, утопив миллион измерений, будь связан с моей волей!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут развязаны! Пусть все воды будут под моим контролем, и, через наш вечный договор, я призываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть моря поднимутся, и цунами внутри меня будет освобождено! Наводнение и затопление, твой [npc.master] зовёт! Послушай, и будь призвана, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С огромным всплеском, ты привязываешь своего элементаля, [npc2.name], к школе Воды!"
									:"С огромным всплеском, [npc1.name] привязывает [npc1.her] элементаля, [npc2.name], к школе Воды!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему обряду моря и неба, я призываю вечный поток! Ответь на зов твоего [npc.master], [npc2.name], и, через утопление миллиона измерений, будь привязана к моей воле!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут освобождены! Пусть все воды будут под моим командованием, и, через наш вечный контракт, я призываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть моря поднимутся, и цунами внутри меня будет освобождено! Наводнение и затопление, твой [npc.master] зовёт! Послушай, и будь призвана, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С огромным всплеском, ты призываешь своего элементаля, [npc2.name], привязывая [npc2.herHim] к школе Воды!"
									:"С огромным всплеском, [npc1.name] призывает [npc1.her] элементаля, [npc2.name], привязывая [npc2.herHim] к школе Воды!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	// AIR:
	
	POISON_VAPOURS(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.POISON,
			false,
			"Ядовитые испарения",
			"poison_vapours",
			"Призывает облако ядовитого газа вокруг цели.",
			0,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.POISON_VAPOURS_1,
					SpellUpgrade.POISON_VAPOURS_2,
					SpellUpgrade.POISON_VAPOURS_3),
			null,
			Util.newArrayListOfValues("<b>25</b> [style.colourPoison(Poison Damage)] за ход за [style.colourGood(3 хода)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_CHOKING_HAZE, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает облако яда вокруг цели.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Пусть печать будет разорвана, и истинная сила будет освобождена! Теперь, через туман и смог, сам воздух подчиняется моему приказу!"),
											"С широким движением вашей [pc.arm], вы вызываете облако ядовитых паров вокруг себя!",
											"С широким движением вашей [pc.arm], вы вызываете облако ядовитых паров вокруг [npc.name]!",
											"",
											"С широким движением [npc.her] [npc.arm], [npc.name] вызываете облако ядовитых паров вокруг вас!",
											"С широким движением [npc1.her] [npc1.arm], [npc1.name] вызываете облако ядовитых паров вокруг [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_CHOKING_HAZE);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	VACUUM(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT_MINOR_DAMAGE,
			DamageType.PHYSICAL,
			false,
			"Вакуум",
			"vacuum",
			"Создает пустоту в воздухе, нанося небольшое начальное повреждение, когда оно всасывает цель, прежде чем оставаться рядом, чтобы продолжать нарушать ее движения.",
			5,
			DamageVariance.LOW,
			60,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.VACUUM_1,
					SpellUpgrade.VACUUM_2,
					SpellUpgrade.VACUUM_3),
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5)),
			Util.newArrayListOfValues("Продолжится [style.colourGood(4 хода)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_TOTAL_VOID, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_SUCTION, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_SECONDARY_VOIDS, 4));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM, 4));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает разрушительный вакуум рядом с целью.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Тьма, чернее черной! Я призываю вечную пустоту, и через разрыв во времени и пространстве, моя сила становится очевидной!"),
											"Сжатием кулака, ты вызываешь вакуум прямо рядом с собой!",
											"Сжатием кулака, ты вызываешь вакуум прямо рядом с [npc.name]!",
											"",
											"Сжатием кулака [npc.her], [npc.name] вызываешь вакуум прямо рядом с тобой!",
											"Сжатием кулака [npc.her], [npc.name] вызываешь вакуум прямо рядом с [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(applyDamage(caster, target, damage));

				target.removeStatusEffect(StatusEffect.VACUUM_TOTAL_VOID);
				target.removeStatusEffect(StatusEffect.VACUUM_SUCTION);
				target.removeStatusEffect(StatusEffect.VACUUM_SECONDARY_VOIDS);
				target.removeStatusEffect(StatusEffect.VACUUM);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	PROTECTIVE_GUSTS(false,
			SpellSchool.AIR,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"Защитные порывы",
			"protective_gusts",
			"Вызываешь благоприятный ветер, чтобы защитить цель, а также помочь направить её атаки.",
			0,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.PROTECTIVE_GUSTS_1,
					SpellUpgrade.PROTECTIVE_GUSTS_2,
					SpellUpgrade.PROTECTIVE_GUSTS_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 5),
					new Value<>(Attribute.ENERGY_SHIELDING, 1)),
			Util.newArrayListOfValues("Продолжительность: [style.colourGood(3 хода)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 5));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Вызываешь благоприятный ветер, чтобы защитить цель.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Сама атмосфера подчиняется моему приказу! Теперь, пусть моя истинная сила будет раскрепощена, и пусть ветер сам по себе будет подчинен моей воле!"),
											"Размахивая обоими [pc.arms] в воздухе, ты вызываешь благоприятный ветер, чтобы помочь защитить тебя!",
											"Размахивая обоими [pc.arms] в воздухе, ты вызываешь благоприятный ветер, чтобы помочь защитить [npc.name]!",
											"Махая обеими руками [npc.her] [npc.arms] вверх, [npc.name] призывает благосклонный ветер, чтобы защитить [npc.herHim]!",
											"Махая обеими руками [npc.her] [npc.arms] вверх, [npc.name] призывает благосклонный ветер, чтобы защитить тебя!",
											"Махая обеими руками [npc.her] [npc.arms] вверх, [npc.name] призывает благосклонный ветер, чтобы защитить [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_AIR(false,
			SpellSchool.AIR,
			SpellType.SUMMON,
			DamageType.PHYSICAL,
			true,
			"Стихийный воздух",
			"elemental_air",
			"Призываешь своего элемента в физической форме, привязывая его к школе Воздуха.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_AIR_1,
					SpellUpgrade.ELEMENTAL_AIR_2,
					SpellUpgrade.ELEMENTAL_AIR_3A,
					SpellUpgrade.ELEMENTAL_AIR_3B),
			null, Util.newArrayListOfValues("Призываешь [style.colourArcane(Elemental)] в форме [style.colourSchoolAir(Воздух)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знает этого заклинания, поэтому не может его использовать!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Заклинания не могут быть использованы в плену!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "Во время боя заклинания могут быть использованы только как боевой ход!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_AIR?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Заклинания могут быть использованы только в нейтральной сцене!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] как минимум <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] для того, чтобы использовать это заклинание!"));
				
			} else {
				String description = owner.isPlayer()?"Призовите ваше элементальное существо, связав его с школой Воздуха!":"Заставьте [npc.name] призвать [npc.her] элементальное существо, связав его с школой Воздуха!";
				String cost = "Это будет стоить <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(аура)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает ваше элементальное существо в форме воздуха.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.AIR);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему обряду ветра и бури, я призываю ураган! Ответь на призыв [npc.master], [npc2.name], и, благодаря жертве из миллиона измерений, будь связан с моей волей!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут развязаны! Пусть воздух сам будет под моим контролем, и, благодаря нашему вечному договору, я призываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть ветер поднимется, и ураган внутри меня будет развязан! Шторм и хаос, твой [npc.master] зовёт! Послушай и будь призван, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С помощью сильного порыва ветра ты связываешь твое элементальное существо, [npc2.name], с школой Воздуха!"
									:"С огромным порывом ветра, [npc1.name] связывает [npc1.her] элементаль, [npc2.name], со школой Воздуха!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему обряду ветра и бури, я вызываю ураган! Ответь на призыв твоего [npc.master], [npc2.name], и, благодаря жертве из миллиона измерений, будь привязан к моей воле!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут развязаны! Пусть воздух сам будет под моим контролем, и, благодаря нашему вечному договору, я вызываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть ветер поднимется, и ураган внутри меня будет развязан! Шторм и хаос, твой [npc.master] зовет! Послушай и будь вызван, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С огромным порывом ветра, ты вызываешь своего элементаля, [npc2.name], привязывая [npc2.herHim] к стихии Воздуха!"
									:"С огромным порывом ветра, [npc1.name] вызывается [npc1.her] элементаль, [npc2.name], привязывая [npc2.herHim] к стихии Воздуха!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	// EARTH:
	
	SLAM(false,
			SpellSchool.EARTH,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Удар",
			"slam",
			"Вызывает сокрушительную волну силы, которая обрушивается на цель.",
			40,
			DamageVariance.LOW,
			60,
			null, Util.newArrayListOfValues(
							SpellUpgrade.SLAM_1,
							SpellUpgrade.SLAM_2,
							SpellUpgrade.SLAM_3), null, null) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.SLAM_AFTER_SHOCK, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.SLAM_GROUND_SHAKE, 2));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Пусть горы трясутся, и земля раскалывается! Моя сила теперь будет развязана, и космические силы под моим контролем приведут к твоей гибели!"),
											"С движением руки вниз, ты вызываешь огромную волну чистой силы, которая обрушивается на тебя!",
											"С движением руки вниз, ты вызываешь огромную волну чистой силы, которая обрушивается на [npc.name]!",
											"",
											"С помощью движений вниз и вперед, [npc.name] вызывает огромную волну чистой силы, которая обрушивается на вас!",
											"С помощью движений вниз и вперед, [npc.name] вызывает огромную волну чистой силы, которая обрушивается на [npc2.name]!")
								);
			
			if(isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					descriptionSB.append("Сила затем ударяет по земле, вызывая огромный землетрясение!");
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					descriptionSB.append("Сила затем ударяет по земле, вызывая колебания!");
				}
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					for(GameCharacter combatant : Main.combat.getEnemies(caster)) {
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	TELEKENETIC_SHOWER(false,
			SpellSchool.EARTH,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			false,
			"Телекинетический душ",
			"telekinetic_shower",
			"Поднимает все мелкие предметы в окрестностях в воздух, прежде чем бросать их в цель.",
			0,
			DamageVariance.LOW,
			125,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEKENETIC_SHOWER_1,
					SpellUpgrade.TELEKENETIC_SHOWER_2,
					SpellUpgrade.TELEKENETIC_SHOWER_3),
			null, Util.newArrayListOfValues("<b>25</b> [style.colourPhysical(Физический Урон)] за ход за [style.colourGood(3 хода)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 6));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Запускает мелкие предметы в вашу цель, чтобы нанести урон и нарушить её.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Космические силы вселенной подчиняются каждому моему приказу! С помощью этого межпространственного вихря, я искажаю границы пространства и времени!"),
											"Поднимая ваше [pc.arms], вы поднимаете все мелкие предметы в непосредственной близости в воздух, прежде чем бросать их в себя!",
											"Поднимая ваше [pc.arms], вы поднимаете все мелкие предметы в непосредственной близости в воздух, прежде чем бросать их в [npc.name]!",
											"",
											"Поднимая [npc.her] [npc.arms], [npc.name] поднимает всевозможные мелкие предметы в непосредственной близости, прежде чем швырнуть их в вас!",
											"Поднимая [npc.her] [npc.arms], [npc.name] поднимает всевозможные мелкие предметы в непосредственной близости, прежде чем швырнуть их в [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	STONE_SHELL(false,
			SpellSchool.EARTH,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"Каменная оболочка",
			"stone_shell",
			"Призывает защитный слой камня вокруг цели.",
			0,
			DamageVariance.LOW,
			25,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.STONE_SHELL_1,
					SpellUpgrade.STONE_SHELL_2,
					SpellUpgrade.STONE_SHELL_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			Util.newArrayListOfValues("Действует в течение [style.colourGood(3 хода)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_HARDENED_CARAPACE, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_SHIFTING_SANDS, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает щит, чтобы защитить вашу цель.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Ограничение моих астральных сил подошло к концу! По моей силе, высвобожденной, сама Земля служит [npc.master]'s призыву!"),
											"Толкая ваше [pc.hand] вперед, вы призываете левитирующий каменный панцирь, чтобы защитить вас от входящих атак!",
											"Толкая ваше [pc.hand] вперед, вы призываете левитирующий каменный панцирь, чтобы защитить [npc.name] от входящих атак!",
											"Толкая [npc.her] [npc.hand] вперед, [npc.name] призывает левитирующий каменный панцирь, чтобы защитить [npc.herHim] от входящих атак!",
											"Толкая [npc.her] [npc.hand] вперед, [npc.name] призывает левитирующий каменный панцирь, чтобы защитить вас от входящих атак!",
											"Выбрасывая [npc1.her] [npc1.hand] вперёд, [npc1.name] призывает левитирующий каменный панцирь, чтобы защитить [npc2.name] от входящих атак!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.removeStatusEffectCombat(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH));
				target.removeStatusEffect(StatusEffect.STONE_SHELL_HARDENED_CARAPACE);
				target.removeStatusEffect(StatusEffect.STONE_SHELL_SHIFTING_SANDS);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_EARTH(false,
			SpellSchool.EARTH,
			SpellType.SUMMON,
			DamageType.PHYSICAL,
			false,
			"Стихийная земля",
			"elemental_earth",
			"Вызывайте ваше элементальное существо в физической форме, привязывая его к школе Земли.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_EARTH_1,
					SpellUpgrade.ELEMENTAL_EARTH_2,
					SpellUpgrade.ELEMENTAL_EARTH_3A,
					SpellUpgrade.ELEMENTAL_EARTH_3B),
			null, Util.newArrayListOfValues("Вызывайте [style.colourArcane(Elemental)] в форме [style.colourSchoolEarth(Earth)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знает этого заклинания, поэтому не может его использовать!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Заклинания не могут быть использованы во время заточения!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "Во время боя заклинания могут быть использованы только как боевой ход!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_EARTH?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Заклинания могут быть использованы только в нейтральной сцене!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] как минимум <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] для того, чтобы использовать это заклинание!"));
				
			} else {
				String description = owner.isPlayer()?"Вызывайте ваше элементальное существо, привязывая его к школе Земли!":"Пригласите [npc.name], чтобы он привел в действие [npc.her] элементаль, связав его с школой Земли!";
				String cost = "Это будет стоить <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(аура)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает вашего элементаля в форме земли.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
//				System.out.println(caster.getName());
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.EARTH);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему ритуалу песка и камня, я вызываю землетрясение! Ответьте на зов вашего [npc.master], [npc2.name], и, благодаря сокрушению миллиона измерений, будьте связаны с моей волей!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут развязаны! Пусть сама земля будет под моим контролем, и, благодаря нашему вечному контракту, я вызываю вас, [npc2.name]!)] ",
											"[npc.speech(Пусть земля дрожит, и сила внутри меня развязана! Камень и гора, ваш [npc.master] зовет! Послушайте и будьте вызваны, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С помощью взрыва камней и обломков вы связываете вашего элементаля, [npc2.name], с школой Земли!"
									:"С помощью взрыва камней и обломков, [npc1.name] связывает [npc1.her] элементаль, [npc2.name], с школой Земли!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему ритуалу песка и камня, я вызываю землетрясение! Ответьте на зов вашего [npc.master], [npc2.name], и, благодаря сокрушению миллиона измерений, будьте связаны с моей волей!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, будут освобождены! Пусть сама земля будет под моим контролем, и, благодаря нашему вечному договору, я призываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть земля дрожит, и сила во мне будет освобождена! Камень и гора, твой [npc.master] зовёт! Послушай, и будь призвана, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С помощью взрыва камней и обломков, ты призываешь своего элементаль, [npc2.name], связывая [npc2.herHim] с школой Земли!"
									:"С помощью взрыва камней и обломков, [npc1.name] призывает элементаль, [npc1.her], [npc2.name], связывая [npc2.herHim] с школой Земли!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	// ARCANE:
	
	ARCANE_AROUSAL(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Arcane Возбуждение",
			"arcane_arousal",
			"Вызывает у цели сильное возбуждающее арканическое видение.",
			15,
			DamageVariance.LOW,
			50,
			null, Util.newArrayListOfValues(
							SpellUpgrade.ARCANE_AROUSAL_1,
							SpellUpgrade.ARCANE_AROUSAL_2,
							SpellUpgrade.ARCANE_AROUSAL_3), null, null) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION, 2));
					
				}
			}
			return new HashMap<>();
		}

		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1)) {
				return 30;
			}
			return 15;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"С помощью истинной природы арканических сил, я передаю тебе пророчество самой Лилит! Посмотри, судьба, которая ждёт всех смертных, осмеливающихся встать против меня!"),
											"Ты сосредотачиваешь свою арканическую энергию на проецировании возбуждающего видения в свой собственный разум",
											"Ты сосредотачиваешь свою арканическую энергию на проецировании возбуждающего видения в разум [npc.namePos].",
											"",
											"[npc.Name] сосредотачивает [npc.her] арканическую энергию на проецировании возбуждающего видения в твой разум!",
											"[npc.Name] сосредотачивает [npc.her] арканическую энергию на проецировании возбуждающего видения в разум [npc2.namePos]!"));
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(target.incrementLust(damage, true));
				}
				
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES);
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION);
					
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	TELEPATHIC_COMMUNICATION(false,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"Телепатическая связь",
			"telepathic_communication",
			"Заговорщик проецирует соблазнительные голоса в сознание цели.",
			0,
			DamageVariance.LOW,
			75,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEPATHIC_COMMUNICATION_1,
					SpellUpgrade.TELEPATHIC_COMMUNICATION_2,
					SpellUpgrade.TELEPATHIC_COMMUNICATION_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15)), Util.newArrayListOfValues("Продолжительность: [style.colourGood(5 ходов)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 10));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 5));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Говорите напрямую в сознание цели.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Своей властью над всеми измерениями я теперь разрушаю границы пространства и времени! Сами небеса услышат мой голос, и отчаяние!"),
											"Вы сосредотачиваете свою арканическую энергию на том, чтобы ваши мысли могли быть проецированы в сознание других!",
											"Вы сосредотачиваете свою арканическую энергию на том, чтобы мысли [npc.namePos] могли быть проецированы в сознание других!",
											"[npc.Name] сосредотачивает [npc.her] арканическую энергию на том, чтобы мысли [npc.her] могли быть проецированы в сознание других!",
											"[npc.Name] сосредотачивает [npc.her] арканическую энергию на том, чтобы ваши мысли могли быть проецированы в сознание других!",
											"[npc1.Name] сосредотачивает [npc1.her] арканическую энергию на том, чтобы мысли [npc2.namePos] могли быть проецированы в сознание других!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ARCANE_CLOUD(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.LUST,
			false,
			"Облако Arcane",
			"arcane_cloud",
			"Призывает арканически заряженную грозовую тучу над головой цели.",
			0,
			DamageVariance.LOW,
			150,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ARCANE_CLOUD_1,
					SpellUpgrade.ARCANE_CLOUD_2,
					SpellUpgrade.ARCANE_CLOUD_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -25)), Util.newArrayListOfValues("Продолжается [style.colourGood(3 хода)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает аркана облако над целью.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"О, тёмные печати, содержащие мою высшую силу, теперь разрывайте на части! Пусть аркана проявления будут призваны, чтобы нанести моим врагам их окончательный приговор!"),
											"С поднятием [pc.arm], ты призываешь аркана облако над собственной головой!",
											"С поднятием [pc.arm], ты призываешь аркана облако над головой [npc.namePos]!",
											"",
											"С поднятием [npc.her] [npc.arm], [npc.name] призывает аркана облако над собственной головой!",
											"С поднятием [npc1.her] [npc1.arm], [npc1.name] призывает аркана облако над головой [npc2.namePos]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	CLEANSE(true,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT_CLEAR,
			DamageType.PHYSICAL,
			true,
			"Очищение",
			"cleanse",
			"Очищающая волна аркана энергии вырывается из цели, удаляя все её эффекты статуса.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.CLEANSE_1,
					SpellUpgrade.CLEANSE_2,
					SpellUpgrade.CLEANSE_3),
			null, Util.newArrayListOfValues(
					"[style.colourGood(Удаляет все)] эффекты статуса из",
					"как союзника, так и врага")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Убирает с полей боя все негативные эффекты как с союзников, так и с врагов.";
		}
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && Main.game.isInCombat()) {
				AbstractStatusEffect effect = StatusEffect.ARCANE_DUALITY_POSITIVE;
				
				if(Main.combat.getEnemies(caster).contains(target)) {
					effect = StatusEffect.ARCANE_DUALITY_NEGATIVE;
				}
				
				
				if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(effect, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(effect, 3));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Силой Лилит, я разрушаю иллюзии мира! Пусть время и пространство будут разорваны, и под моим арканическим господством, сама реальность будет извращена в мою пользу!"),
											"Выдвигая вперед свое [pc.hand], вы вызываете взрыв очищающей арканической энергии на себя!",
											"Выдвигая вперед свое [pc.hand], вы вызываете взрыв очищающей арканической энергии на [npc.name]!",
											"Выдвигая [npc.her] [npc.hand] вперед, [npc.name] вызываете взрыв очищающей арканической энергии на [npc.herself]!",
											"Выдвигая [npc.her] [npc.hand] вперед, [npc.name] вызываете взрыв очищающей арканической энергии на вас!",
											"Выдвигая [npc.her] [npc.hand] вперед, [npc.name] вызываете взрыв очищающей арканической энергии на [npc2.name]!")
								);

			descriptionSB.append(UtilText.parse(this.getPreferredTarget(caster, enemies, allies),
					"Энергия затем вылетает и взрывается вокруг [npc.name]!"));
			
			
			// If attack hits, apply damage and effects: TODO
			if (isHit) {
				List<AbstractStatusEffect> effectsToRemove = new ArrayList<>();
				// Remove status effects from ally:
				for(AbstractStatusEffect se : target.getStatusEffects()) {
					if(se.isCombatEffect() && ((se.getBeneficialStatus()==EffectBenefit.BENEFICIAL && !caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1)) || se.getBeneficialStatus()!=EffectBenefit.BENEFICIAL)) {
						effectsToRemove.add(se);
					}
				}
				for(AbstractStatusEffect se : effectsToRemove) {
					descriptionSB.append(target.removeStatusEffectCombat(se));
				}
				// Remove status effects from enemy:
				effectsToRemove.clear();
				for(AbstractStatusEffect se : this.getPreferredTarget(caster, enemies, allies).getStatusEffects()) {
					if(se.isCombatEffect() && (se.getBeneficialStatus()==EffectBenefit.BENEFICIAL || (se.getBeneficialStatus()!=EffectBenefit.BENEFICIAL && !caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1)))) {
						effectsToRemove.add(se);
					}
				}
				for(AbstractStatusEffect se : effectsToRemove) {
					descriptionSB.append(this.getPreferredTarget(caster, enemies, allies).removeStatusEffectCombat(se));
				}
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	STEAL(true,
			SpellSchool.ARCANE,
			SpellType.MISC,
			DamageType.PHYSICAL,
			false,
			"Кража",
			"steal",
			"Меньшая форма телепортации, это заклинание позволяет заклинателю украсть предмет из инвентаря цели.",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.STEAL_1,
					SpellUpgrade.STEAL_2,
					SpellUpgrade.STEAL_3A,
					SpellUpgrade.STEAL_3B),
			null, Util.newArrayListOfValues("[style.colourExcellent(Крадет)] случайный предмет из инвентаря цели")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Крадет предметы цели.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Ткань пространства и времени принадлежит мне! Космические измерения, подчиняйтесь моему приказу, и передайте мне имущество моих ненавистных врагов!"),
											"Вытянув [pc.hand] и сжав кулак, вы направляете свою арканическую силу, чтобы украсть один из ваших собственных предметов...", // ...
											"Вытянув [pc.hand] и сжав кулак, вы направляете свою арканическую силу, чтобы украсть один из предметов [npc.namePos]!",
											"",
											"Выталкивая [npc.her] [npc.hand] и сжимая [npc.her] кулак, [npc.name] направляет [npc.her] аркана, чтобы украсть один из ваших предметов!",
											"Выталкивая [npc.her] [npc.hand] и сжимая [npc.her] кулак, [npc.name] направляет [npc.her] аркана, чтобы украсть один из [npc2.namePos] предметов!"));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean stealItem = false;
				boolean mainWeaponSteal = false;
				boolean offhandWeaponSteal = false;
				AbstractClothing clothingToSteal = null;
				
				if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_3B)) {
					clothingToSteal = target.getClothingInSlot(InventorySlot.GROIN);
					if(clothingToSteal != null && !clothingToSteal.isSealed()) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<br/>"
								+getCastDescription(caster, target,
										null,
										"Вы украли свой собственный "+clothingToSteal.getName()+"...",
										"[npc.Name] издает смущенный крик, когда вы украли "+clothingToSteal.getName()+" что [npc.sheIs] в данный момент носит, [npc.speech(Y-Ты pervert!)]",
										"",
										"Ты не можешь не выпустить смущенный крик, когда [npc.name] крадет "+clothingToSteal.getName()+" то, что ты сейчас носишь, [pc.speech(Y-Ты pervert!)]",
										"[npc2.Name] выпустил смущенный крик, когда [npc1.name] украл "+clothingToSteal.getName()+" то, что [npc2.sheIs] в данный момент носит, [npc2.speech(Y-Ты pervert!)]")
								+ "<br/>");
						clothingToSteal.setName(target.getNameIgnoresPlayerKnowledge() + "'s " +clothingToSteal.getName());
						descriptionSB.append(caster.addClothing(clothingToSteal, true));
					}
				}
				
				if(clothingToSteal==null) {
					if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_3A)) {
						List<AbstractClothing> nonSealedClothing = new ArrayList<>();
						for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
							if(!c.isSealed()) {
								nonSealedClothing.add(c);
							}
						}
						if(!nonSealedClothing.isEmpty()) {
							clothingToSteal = nonSealedClothing.get(Util.random.nextInt(nonSealedClothing.size()));
						}
						
					} else if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_1)) {
						List<AbstractClothing> nonSealedOuterClothing = new ArrayList<>();
						for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
							if(!c.isSealed() && target.isAbleToUnequip(c, false, target)) {
								nonSealedOuterClothing.add(c);
							}
						}
						if(!nonSealedOuterClothing.isEmpty()) {
							clothingToSteal = nonSealedOuterClothing.get(Util.random.nextInt(nonSealedOuterClothing.size()));
						}
						
					}
					
					int mainWeaponIndex = 0;
					AbstractWeapon mainWeapon = null;
					int offhandWeaponIndex = 0;
					AbstractWeapon offhandWeapon = null;
					List<Integer> weaponIndexes = new ArrayList<>();
					for(int i=0;i<target.getMainWeaponArray().length; i++) {
						if(target.getMainWeapon(i)!=null) {
							weaponIndexes.add(i);
						}
					}
					if(!weaponIndexes.isEmpty()) {
						mainWeaponIndex = Util.randomItemFrom(weaponIndexes);
						mainWeapon = target.getMainWeapon(mainWeaponIndex);
					}
					weaponIndexes = new ArrayList<>();
					for(int i=0;i<target.getOffhandWeaponArray().length; i++) {
						if(target.getOffhandWeapon(i)!=null) {
							weaponIndexes.add(i);
						}
					}
					if(!weaponIndexes.isEmpty()) {
						offhandWeaponIndex = Util.randomItemFrom(weaponIndexes);
						offhandWeapon = target.getOffhandWeapon(offhandWeaponIndex);
					}
						
					if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_2)) {
						mainWeaponSteal = mainWeapon!=null;
						offhandWeaponSteal = offhandWeapon!=null;
					}
					
					stealItem = target.getInventorySlotsTaken()>0;
					
				
					double rnd = Math.random();
					
					if(mainWeaponSteal && (rnd<0.2 || (!offhandWeaponSteal && !stealItem && clothingToSteal==null))) {
						target.unequipMainWeapon(mainWeaponIndex, true, target.isPlayer());
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"Ты украл свою собственную "+mainWeapon.getName()+"...",
										"Ты украл [npc.namePos] "+mainWeapon.getName()+" из [npc.her] [npc.hands]!",
										"",
										"[npc.Name] украл твой "+mainWeapon.getName()+" из твоего [pc.hands]!",
										"[npc1.Name] украл [npc2.namePos] "+mainWeapon.getName()+" у [npc2.her] [npc2.hands]!")
								+ "<br/>"
								+ caster.addWeapon(mainWeapon, true));
						
					} else if(offhandWeaponSteal && (rnd<0.2 || (!stealItem && clothingToSteal==null))) {
						target.unequipOffhandWeapon(offhandWeaponIndex, true, target.isPlayer());
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"Ты украл свою "+offhandWeapon.getName()+"...",
										"Ты украл [npc.namePos] "+offhandWeapon.getName()+" у [npc.her] [npc.hands]!",
										"",
										"[npc.Name] украл твой "+offhandWeapon.getName()+" у твоего [pc.hands]!",
										"[npc1.Name] украл [npc2.namePos] "+offhandWeapon.getName()+" у [npc2.her] [npc2.hands]!")
								+ "<br/>"
								+ caster.addWeapon(offhandWeapon, true));
						
					} else if(stealItem && (rnd<0.5 || (clothingToSteal==null))) {
						AbstractItem item = null;
						if(!target.getAllItemsInInventory().isEmpty()) {
							item = Util.randomItemFrom(target.getAllItemsInInventory().keySet());
						}
						AbstractWeapon weapon = null;
						if(!target.getAllWeaponsInInventory().isEmpty()) {
							weapon = Util.randomItemFrom(target.getAllWeaponsInInventory().keySet());
						}
						AbstractClothing clothing = null;
						if(!target.getAllClothingInInventory().isEmpty()) {
							clothing = Util.randomItemFrom(target.getAllClothingInInventory().keySet());
						}
						double itemStealRnd = Math.random();
						if(item!=null && (itemStealRnd<0.33 || (weapon==null && clothing==null))) {
							target.removeItem(item);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"Ты украл(а) свой собственный "+item.getName()+"...",
											"Ты украл(а) [npc.namePos] "+item.getName()+" из [npc.her] инвентаря!",
											"",
											"[npc.Name] украл(а) твой "+item.getName()+" из твоего инвентаря!",
											"[npc1.Name] украл(а) [npc2.namePos] "+item.getName()+" из [npc2.her] инвентаря!")
									+ "<br/>"
									+ caster.addItem(item, false));
							
						} else if(weapon!=null && (itemStealRnd<0.66 || (clothing==null))) {
							target.removeWeapon(weapon);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"Ты украл(а) свой собственный "+weapon.getName()+"...",
											"Ты украл [npc.namePos] "+weapon.getName()+" из инвентаря [npc.her]!",
											"",
											"[npc.Name] украл твой "+weapon.getName()+" из твоего инвентаря!",
											"[npc1.Name] украл [npc2.namePos] "+weapon.getName()+" из инвентаря [npc2.her]!")
									+ "<br/>"
									+ caster.addWeapon(weapon, false));
							
						} else {
							target.removeClothing(clothing);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"Ты украл свой "+clothing.getName()+"...",
											"Ты украл [npc.namePos] "+clothing.getName()+" из инвентаря [npc.her]!",
											"",
											"[npc.Name] украл твой "+clothing.getName()+" из твоего инвентаря!",
											"[npc1.Name] украл [npc2.namePos] "+clothing.getName()+" из инвентаря [npc2.her]!")
									+ "<br/>"
									+ caster.addClothing(clothing, false));
						}
						
					} else if(clothingToSteal!=null) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"Ты украл свой собственный "+clothingToSteal.getName()+"...",
										"[npc.Name] издает смущенный крик, когда ты украл "+clothingToSteal.getName()+" который [npc.sheIs] в данный момент носит!",
										"",
										"Ты не можешь сдержать смущенный крик, когда [npc.name] украл "+clothingToSteal.getName()+" который ты в данный момент носишь!",
										"[npc2.Name] издает смущенный крик, когда [npc1.name] крадет "+clothingToSteal.getName()+" то, что [npc2.sheIs] в данный момент носит!")
								+ "<br/>");
						clothingToSteal.setName(target.getNameIgnoresPlayerKnowledge() + "'s " +clothingToSteal.getName());
						descriptionSB.append(caster.addClothing(clothingToSteal, true));
						
					} else {
						descriptionSB.append("<br/>[style.italicsDisabled(Там нечего красть...)]");
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
		
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.spellStealUpgradeTree;
		}
	},
	
	TELEPORT(true,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			true,
			"Телепортация",
			"teleport",
			"Целью является телепортация за спину своих врагов, что значительно повышает шанс уклонения.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEPORT_1,
					SpellUpgrade.TELEPORT_2,
					SpellUpgrade.TELEPORT_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ENERGY_SHIELDING, 100)), Util.newArrayListOfValues(
					"Действует [style.colourGood(1 ход)]",
					"[style.colourExcellent(Разблокирует)] телепорт на карте",
					"Телепорт на карте [style.colourTerrible(заблокирован)] компаньонами")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Телепортируйтесь за спину своей цели.";
		}

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 1));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT, 1));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2) && !allies.isEmpty()) {
				descriptionSB.append(getCastDescription(caster, target,
						Util.newArrayListOfValues(
								"Через тысячу измерений и через миллион миров, я бродю! Расстояние и время - это лишь незначительные атрибуты невежественных масс!"),
						"С быстрым, резким движением одной из ваших [pc.hands], вы телепортируете себя и своих союзников за спину своих врагов!",
						"С быстрым, резким движением одного из ваших [pc.hands], вы телепортируете себя и своих союзников за спину врагов!",
						"С быстрым, резким движением одного из [npc.her] [npc.hands], [npc.name] телепортирует обоих [npc.herself] и [npc.her] союзников за спину [npc.her] врагов!",
						"С быстрым, резким движением одного из [npc.her] [npc.hands], [npc.name] телепортирует обоих [npc.herself] и [npc.her] союзников за спину [npc.her] врагов!",
						"С быстрым, резким движением одного из [npc.her] [npc.hands], [npc.name] телепортирует обоих [npc.herself] и [npc.her] союзников за спину [npc.her] врагов!"));
				
			} else {
				descriptionSB.append(getCastDescription(caster, target,
						Util.newArrayListOfValues(
								"Через тысячу измерений и через миллион миров, я скитался! Расстояние и время - это не более чем незначительные атрибуты невежественных масс!"),
						"С быстрым, резким движением одного из ваших [pc.hands], вы телепортируете себя за спину врагов!",
						"С быстрым, резким движением одного из ваших [pc.hands], вы телепортируете [npc.name] за спину [npc.her] врагов!",
						"С быстрым, резким движением одного из [npc.her] [npc.hands], [npc.name] телепортирует за спину [npc.her] врагов!",
						"С быстрым, резким движением одного из [npc.her] [npc.hands], [npc.name] телепортирует вас за спину врагов!",
						"С быстрым, резким движением одного из [npc.her] [npc.hands], [npc.name] телепортирует [npc2.name] за спину [npc2.her] врагов!"));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEPORT_ARCANE_ARRIVAL);
				target.removeStatusEffect(StatusEffect.TELEPORT);
				
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2)) {
					applyStatusEffects(caster, caster, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, caster, isHit, isCritical));
					
					for(GameCharacter combatant : Main.combat.getAllies(caster)) {
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
					
				} else {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	LILITHS_COMMAND(true,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Команда Lilith",
			"liliths_command",
			"Наполняет слова заклинателя силой самой Лилит, заставляя цель мгновенно подчиниться.",
			0,
			DamageVariance.LOW,
			400,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.LILITHS_COMMAND_1,
					SpellUpgrade.LILITHS_COMMAND_2,
					SpellUpgrade.LILITHS_COMMAND_3),
			null,
			Util.newArrayListOfValues("[style.colourGood(25%)] шанс, что цель [style.colourExcellent(мгновенно подчинится)]")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Заставьте вашу цель подчиниться.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Через меня, Лилит становится явной! Через пропасть времени и пространства, её власть безгранична, и она приказывает вам подчиниться!"),
											"",
											"Зачерпывая огромную силу из вашей арканой ауры, вы проецируете слова самой Лилит в разум [npc.name], приказывая [npc.herHim] подчиниться.",
											"",
											"Зачерпывая огромную силу из арканой ауры [npc.her], [npc.name] проецирует слова самой Лилит в ваш разум, приказывая вам подчиниться!",
											"Зачерпывая огромную силу из арканой ауры [npc1.her], [npc1.name] проецирует слова самой Лилит в разум [npc2.namePos], приказывая [npc2.herHim] подчиниться!"));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean success = false;
				if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_3)) {
					success = true;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
					success = Math.random()<0.75f;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_1)) {
					success = Math.random()<0.5f && target.isVulnerableToArcaneStorm();
					
				} else {
					success = Math.random()<0.25f && target.isVulnerableToArcaneStorm();
				}
				
				if(success) {
					target.setHealthPercentage(0);
					target.setManaPercentage(0);
					target.setLustNoText(100);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
					if(target.isPlayer()) {
						descriptionSB.append(
								"<br/>"
								+ "Вы оказываетесь лицом к лицу с самой королевой всех демонов."
								+ "Щелкая пальцами, Лилит указывает на пол, и, делая это, вы находите себя на коленях перед ней."
								+ " Желая угодить ей, ты смотрю в глаза Лилит, когда ты [pc.moan], [pc.speech(Пожалуйста, Лилит... Делай со мной, что хочешь... Я твой верный раб...)]");
						
					} else {
						descriptionSB.append(UtilText.parse(target,
								"<br/>"
								+ "[npc.Name] опускается на [npc.her] колени, когда эффект заклинания Лилит полностью овладевает [npc.herHim]."
										+ " Выдавая [npc.a_moan+], [npc.she] начинает трогать [npc.herself], когда [npc.she] умоляет, [npc.speech(Пожалуйста, Лилит... Делай со мной, что хочешь... Я твой верный раб...)]"));
					}
					
				} else {
					if(target.isPlayer()) {
						descriptionSB.append(
								"<br/> Ты качаешь головой и отпрыгиваешь назад, когда ты сопротивляешься эффектам заклинания Лилит!");
						
					} else if(target.isVulnerableToArcaneStorm() || !caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
						descriptionSB.append(UtilText.parse(target, "<br/>[npc.Name] трясет [npc.her] головой, и он/она отпрыгивает назад, пока [npc.she] сопротивляется эффектам Команды Lilith!"));
					} else {
						descriptionSB.append(UtilText.parse(target, "<br/>[npc.Name] хихикает, когда [npc.she] дразнит, [npc.speech(Этот дешевый трюк не повлияет на [npc.a_race], как на меня!)]"));
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_ARCANE(false,
			SpellSchool.ARCANE,
			SpellType.SUMMON,
			DamageType.LUST,
			false,
			"Элементальная Arcane",
			"elemental_arcane",
			"Призови своего элемента в физической форме, связав его со школой Аркана.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_ARCANE_1,
					SpellUpgrade.ELEMENTAL_ARCANE_2,
					SpellUpgrade.ELEMENTAL_ARCANE_3A,
					SpellUpgrade.ELEMENTAL_ARCANE_3B),
			null, Util.newArrayListOfValues("Призывает [style.colourArcane(Элемент)] в форме [style.colourArcane(Аркана)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знает этого заклинания, поэтому не может его использовать!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Заклинания не могут быть использованы, пока вы в плену!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "Во время боя заклинания могут быть использованы только как боевой ход!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_ARCANE?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Заклинания могут быть использованы только в нейтральной сцене!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] как минимум <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] для того, чтобы использовать это заклинание!"));
				
			} else {
				String description = owner.isPlayer()?"Призовите ваше элементальное существо, связав его со школой Аркана!":"Заставьте [npc.name] призвать [npc.her] элементальное существо, связав его со школой Аркана!";
				String cost = "Это будет стоить <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(аура)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Призывает ваше элементальное существо в форме арканой энергии.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.ARCANE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему обряду дьявола и демона, я призываю Аркана! Ответь на зов твоего [npc.master], [npc2.name], и, покорив миллион измерений, будь подчинен моей воле!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут развязаны! Пусть Аркана сама будет в моем распоряжении, и, через наш вечный договор, я призываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть Аркана будет моей, и сила во мне будет развязана! Дух Аркана сам, твой [npc.master] зовет! Послушай и будь призвана, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С вспышкой фиолетовой арканической молнии, ты привязываешь твоего элементаля, [npc2.name], к школе Аркана!"
									:"С вспышкой фиолетовой арканической молнии, [npc1.name] привязывает твоего элементаля, [npc1.her], [npc2.name], к школе Аркана!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(По древнему обряду дьявола и демона, я призываю Аркана! Ответь на зов твоего [npc.master], [npc2.name], и, покорив миллион измерений, будь подчинен моей воле!)] ",
											"[npc.speech(Пусть силы, запечатанные во мне на тысячу лет, теперь будут развязаны! Пусть Аркана сама будет в моем распоряжении, и, через наш вечный договор, я призываю тебя, [npc2.name]!)] ",
											"[npc.speech(Пусть Аркана будет моей, и сила во мне будет развязана! Дух Аркана сам, твой [npc.master] зовет! Послушай и будь призвана, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"С вспышкой фиолетовой арканической молнии, ты призываешь своего элементаля, [npc2.name], привязывая [npc2.herHim] к школе Аркана!"
									:"С вспышкой фиолетовой арканой молнии, [npc1.name] призывает [npc1.her] элементаль, [npc2.name], связывая [npc2.herHim] со школой Аркана!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	// FROM WEAPONS:
	
	WITCH_SEAL(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.MISC,
			false,
			"Печать ведьмы",
			"spell_witch_seal",
			"Накладывает арканный знак на цель, полностью лишая её возможности двигаться.",
			0,
			DamageVariance.NONE,
			80,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.WITCH_SEAL, 1)),
			null,
			null,
			Util.newArrayListOfValues(
					"[style.colourExcellent(Seals)] цель на [style.colourTerrible(-3)] [style.colourActionPoints(action points)]!")) {
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Накладывает знаки на [style.colourTerrible(-3)] действия!";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Пусть знак, который когда-то содержал мою арканую силу, теперь подчиняется моей воле! Наложи на моего врага парализующую тьму абсолютного подчинения и привяжи его к его судьбе!"),
										"",
										"Сосредоточившись на арканой силе в твоей метле, ты призываешь мощный знак, который захватывает [npc.name] на месте!",
										"",
										"Сосредоточившись на арканой силе в [npc.her] метле, [npc.name] призывает мощный знак, который захватывает тебя на месте!",
										"Сосредоточившись на арканой силе в [npc1.her] метле, [npc1.name] [npc.verb(summon)] мощный знак, который захватывает [npc2.name] на месте!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	WITCH_CHARM(false,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.MISC,
			true,
			"Ведьмин шарм",
			"spell_witch_charm",
			"Накладывает арканный заклятие на цель, заставляя её казаться неотразимо красивой для всех, кто на неё смотрит.",
			0,
			DamageVariance.NONE,
			40,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.WITCH_CHARM, 5)),
			null,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25)), Util.newArrayListOfValues("Действует в течение [style.colourGood(5 ходов)]")) {
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Увеличивает урон от соблазнения.";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Пусть сама реальность будет искажена моей высшей силой! Все, кто смотрит на мое лицо, будут очарованы, и увидят истинное желание своего сердца!"),
										"Сосредоточившись на арканической силе в твоей метле, ты накладываешь на себя очаровывающий заклятье!",
										"Сосредоточившись на арканической силе в твоей метле, ты накладываешь на [npc.name] очаровывающий заклятье!",
										"Сосредоточившись на арканической силе в метле [npc.her], [npc.name] накладывает очаровывающий заклятье на [npc.herself]!",
										"Сосредоточившись на арканической силе в метле [npc.her], [npc.name] накладывает очаровывающий заклятье на тебя!",
										"Сосредоточившись на арканической силе в метле [npc1.her], [npc.name] накладывает очаровывающий заклятье на [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	

	
	DARK_SIREN_SIRENS_CALL(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT_MINOR_DAMAGE,
			DamageType.PHYSICAL,
			false,
			"Зов сирены",
			"dark_siren_sirens_call",
			"Освобождает реверберационный крик, сила которого заставляет землю расколоться. Из этой трещины поднимаются ядовитые пары, которые задыхают и подавляют всех близлежащих врагов.",
			10,
			DamageVariance.NONE,
			200,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.BANEFUL_FISSURE, 10)),
			null,
			null,
			Util.newArrayListOfValues(
					"<b>25</b> [style.colourPoison(Poison Damage)] за ход за [style.colourGood(10 ходов)]",
					"Поражает [style.colourExcellent(всех врагов)]")) {

		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Силы под землей, послушайтесь приказа [npc.master]! Разрывайте до конца времен пропасть тьмы, из которой может вырваться удушливый миазм токсичных измерений!"),
										"",
										"Сосредоточившись на огромной арканой силе в вашем серпе, вы ударяете вниз в землю под [npc.namePos] [npc.feet], разрывая землю и вызывая ядовитые испарения!",
										"",
										"Сосредоточившись на огромной арканой силе в [npc.her] серпе, [npc.name] ударяет вниз в землю под вашим [pc.feet], разрывая землю и вызывая ядовитые испарения!",
										"Сосредоточившись на огромной арканой силе в [npc.her] серпе, [npc.name] ударяет вниз в землю под [npc2.namePos] [npc2.feet], разрывая землю и вызывая ядовитые испарения!"));

			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}
			}
			
			for(GameCharacter combatant : Main.combat.getEnemies(caster)) {
				applyStatusEffects(caster, combatant, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
//			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	

	LIGHTNING_SPHERE_DISCHARGE(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Разряд молнии",
			"arcane_lightning_sphere_discharge",
			"Забирая небольшое количество ауры от своего владельца, арканный молниевой шар может выпустить вспышку возбуждающей арканной молнии, поражая всех, [style.colourBad(включая заклинателя)], в своей непосредственной близости.",
			10,
			DamageVariance.MEDIUM,
			50,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"Поражает [style.colourExcellent(всех врагов)]",
					"Поражает [style.colourTerrible(заклинателя)]",
					"Поражает [style.colourTerrible(всех союзников, включая заклинателя)]")) {

		@Override
		public int getAPCost() {
			return 1;
		}

		@Override
		public int getCooldown() {
			return 2;
		}
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Наносит [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")]"
					+ "ущерб [style.colourExcellent(всем врагам)] <i>и</i> [style.colourTerrible(всем союзникам, включая заклинателя)].";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Запечатанная на долгие тысячелетия, эта бесконечная космическая сила теперь будет развязана! Свидетельствуй о том, как я вызываю аркана, а затем подчиняюсь своей судьбе как творец, одержимый похотью!"),
										"Направляя часть твоей ауры в арканный шарик молнии, ты заставляешь его высвободить часть своей силы в виде разряда молнии!",
										"Направляя часть твоей ауры в арканный шарик молнии, ты заставляешь его высвободить часть своей силы в виде разряда молнии!",
										"Направляя часть ауры [npc.her] в арканный шарик молнии, [npc.name] [npc.verb(force)] заставляешь его высвободить часть своей силы в виде разряда молнии!",
										"Направляя часть ауры [npc.her] в арканный шарик молнии, [npc.name] [npc.verb(force)] заставляешь его высвободить часть своей силы в виде разряда молнии!",
										"Направляя часть ауры [npc.her] в арканный шарик молнии, [npc.name] [npc.verb(force)] заставляешь его высвободить часть своей силы в виде разряда молнии!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
					descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
					if(damage>0) {
						descriptionSB.append(applyDamage(caster, combatant, damage));
					}
					applyStatusEffects(caster, combatant, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Не может нанести крит.");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	LIGHTNING_SPHERE_OVERCHARGE(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Перегрузка молнией",
			"arcane_lightning_sphere_overcharge",
			"Забирая огромное количество ауры у своего владельца, арканный шарик молнии может высвободить огромный заряд возбуждающей арканной молнии, поражая всех, [style.colourBad(включая заклинателя)], в непосредственной близости.",
			30,
			DamageVariance.HIGH,
			250,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"Поражает [style.colourExcellent(всех врагов)]",
					"Поражает [style.colourTerrible(заклинателя)]",
					"Поражает [style.colourTerrible(всех союзников)]")) {

		@Override
		public int getAPCost() {
			return 3;
		}

		@Override
		public int getCooldown() {
			return 10;
		}
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Наносит [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")] урона [style.colourExcellent(всем врагам)] <i>и</i> [style.colourTerrible(всем союзникам, включая заклинателя)].";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Запечатанная на бесчисленные тысячелетия, эта бесконечная космическая мощь будет наконец высвобождена! Узрите Arcane-вихрь, который я призываю, а затем покоритесь своей судьбе в роли моих безумных от похоти марионеток!"),
										"Направляя свою ауру в шар Arcane-молнии, вы заставляете его выплеснуть мощь в виде сокрушительного разряда молнии!",
										"Направляя свою ауру в шар Arcane-молнии, вы заставляете его выплеснуть мощь в виде сокрушительного разряда молнии!",
										"Направляя [npc.her] ауру в шар арканной молнии, [npc.name] [npc.verb(force)] её, чтобы высвободить мощь в виде сокрушительного разряда молнии!",
										"Направляя [npc.her] ауру в шар арканной молнии, [npc.name] [npc.verb(force)] её, чтобы высвободить мощь в виде сокрушительного разряда молнии!",
										"Направляя [npc.her] ауру в шар арканной молнии, [npc.name] [npc.verb(force)] её, чтобы высвободить мощь в виде сокрушительного разряда молнии!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {

				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
					descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
					if(damage>0) {
						descriptionSB.append(applyDamage(caster, combatant, damage));
					}
					applyStatusEffects(caster, combatant, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Не может нанести крит.");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	ARCANE_CHAIN_LIGHTNING(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Цепная молния",
			"arcane_lightning_chain",
			"Заклинатель способен призвать трещащее воплощение арканной молнии, которая перескакивает от цели к цели, заставляя каждого пораженного человека терять контроль от возбуждения.",
			15,
			DamageVariance.MEDIUM,
			40,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"Поражает [style.colourExcellent(всех врагов)]")) {
		@Override
		public int getAPCost() {
			return 1;
		}
		@Override
		public int getCooldown() {
			return 2;
		}
		@Override
		public boolean isSpellBook() {
			return false;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Наносит [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")] урона [style.colourExcellent(всем врагам)].";
		}
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Запечатанная на бесчисленные тысячелетия, эта бесконечная космическая мощь будет наконец высвобождена! Узрите Arcane-вихрь, который я призываю, а затем покоритесь своей судьбе в роли моих безумных от похоти марионеток!"),
													"Сконцентрировав свою ауру на мгновение, вы призываете трещащее воплощение арканной молнии!",
													"Сконцентрировав свою ауру на мгновение, вы призываете трещащее воплощение арканной молнии!",
													"Сконцентрировав [npc.her] ауру на мгновение, [npc.name] [npc.verb(summon)] трещащее воплощение арканной молнии!",
													"Сконцентрировав [npc.her] ауру на мгновение, [npc.name] [npc.verb(summon)] трещащее воплощение арканной молнии!",
													"Сконцентрировав [npc.her] ауру на мгновение, [npc.name] [npc.verb(summon)] трещащее воплощение арканной молнии!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					if(Main.combat.isOpponent(caster, combatant)) {
						float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
						descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
						if(damage>0) {
							descriptionSB.append(applyDamage(caster, combatant, damage));
						}
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Не может нанести крит.");
	    }
		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	ARCANE_LIGHTNING_SUPERBOLT(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Молниеносный суперболт",
			"arcane_lightning_superbolt",
			"Заклинатель призывает могучий арканный суперболт молнии и выпускает его в цель, заставляя пораженного человека испытать невероятный прилив возбуждения.",
			50,
			DamageVariance.HIGH,
			200,
			null,
			null,
			null,
			Util.newArrayListOfValues()) {
		@Override
		public int getAPCost() {
			return 3;
		}
		@Override
		public int getCooldown() {
			return 10;
		}
		@Override
		public boolean isSpellBook() {
			return false;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return getFormattedSpellDamageRange(caster, target, enemies, allies);
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Запечатанная на бесчисленные тысячелетия, эта бесконечная космическая мощь будет высвобождена! Узри арканный апокалипсис, который я призываю, а затем покорись своей судьбе в роли моей одержимой похотью марионетки!"),
													"После того, как ты сосредоточишься на своей ауре, ты призываешь могущественное заклинание молнии, прежде чем запустить его прямо в себя!",
													"После того, как ты сосредоточишься на своей ауре, ты призываешь могущественное заклинание молнии, прежде чем запустить его прямо в [npc.name]!",
													"После того, как ты сосредоточишься на ауре [npc.her], [npc.name] [npc.verb(summon)] призывает могущественное заклинание молнии, прежде чем запустить его прямо в [npc.herself]!",
													"После того, как ты сосредоточишься на ауре [npc.her], [npc.name] [npc.verb(summon)] призывает могущественное заклинание молнии, прежде чем запустить его прямо в тебя!",
													"После того, как ты сосредоточишься на ауре [npc.her], [npc.name] [npc.verb(summon)] призывает могущественное заклинание молнии, прежде чем запустить его прямо в [npc2.name]!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
				descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Не может нанести крит.");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	};
	
	private static Map<SpellSchool, List<Spell>> spellsFromSchoolMap = new HashMap<>();
	
	static {
		for(SpellSchool school : SpellSchool.values()) {
			spellsFromSchoolMap.put(school, new ArrayList<>());
		}
		for(Spell s : Spell.values()) {
			spellsFromSchoolMap.get(s.getSpellSchool()).add(s);
		}
	}
	
	public static Map<SpellSchool, List<Spell>> getSpellsFromSchoolMap() {
		return spellsFromSchoolMap;
	}
	
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	protected static Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellStealUpgradeTree;
	protected static Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> soothingWatersUpgradeTree;
	static {
		spellStealUpgradeTree = new HashMap<>();

		spellStealUpgradeTree.put(0, new ArrayList<>());
		spellStealUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.ARCANE, 0, SpellUpgrade.STEAL_1));

		spellStealUpgradeTree.put(1, new ArrayList<>());
		spellStealUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.ARCANE, 1, SpellUpgrade.STEAL_2));
		spellStealUpgradeTree.get(1).get(0).addLink(spellStealUpgradeTree.get(0).get(0));

		spellStealUpgradeTree.put(2, new ArrayList<>());
		spellStealUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.ARCANE, 2, SpellUpgrade.STEAL_3A));
		spellStealUpgradeTree.get(2).get(0).addLink(spellStealUpgradeTree.get(1).get(0));

		spellStealUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.ARCANE, 2, SpellUpgrade.STEAL_3B));
		spellStealUpgradeTree.get(2).get(1).addLink(spellStealUpgradeTree.get(2).get(0));
		
		soothingWatersUpgradeTree = new HashMap<>();

		soothingWatersUpgradeTree.put(0, new ArrayList<>());
		soothingWatersUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.WATER, 0, SpellUpgrade.SOOTHING_WATERS_1));
		soothingWatersUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.WATER, 0, SpellUpgrade.SOOTHING_WATERS_1_CLEAN));

		soothingWatersUpgradeTree.put(1, new ArrayList<>());
		soothingWatersUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.WATER, 1, SpellUpgrade.SOOTHING_WATERS_2));
		soothingWatersUpgradeTree.get(1).get(0).addLink(soothingWatersUpgradeTree.get(0).get(0));
		soothingWatersUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.WATER, 1, SpellUpgrade.SOOTHING_WATERS_2_CLEAN));

		soothingWatersUpgradeTree.put(2, new ArrayList<>());
		soothingWatersUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.WATER, 2, SpellUpgrade.SOOTHING_WATERS_3));
		soothingWatersUpgradeTree.get(2).get(0).addLink(soothingWatersUpgradeTree.get(1).get(0));
	}
	
	
	private boolean forbiddenSpell;
	private SpellSchool spellSchool;
	private SpellType type;
	protected DamageType damageType;
	private boolean beneficial;
	
	private String name;
	private String description;
	
	protected int damage;
	protected int spellCost;
	protected DamageVariance damageVariance;
	private Map<AbstractStatusEffect, Integer> statusEffects;
	
	private List<SpellUpgrade> upgradeList;
	private Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellUpgradeTree;
	
	private HashMap<AbstractAttribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;

	private String pathName;
	private String SVGString;

	private Spell(boolean forbiddenSpell,
			SpellSchool spellSchool,
			SpellType type,
			DamageType damageType,
			boolean beneficial,
			String name,
			String pathName,
			String description,
			int damage,
			DamageVariance damageVariance,
			int spellCost,
			Map<AbstractStatusEffect, Integer> statusEffects,
			List<SpellUpgrade> upgradeList,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.forbiddenSpell = forbiddenSpell;
		
		this.spellSchool = spellSchool;
		this.type = type;
		this.damageType = damageType;
		this.beneficial = beneficial;
		
		this.name = name;
		this.description = description;

		this.damage = damage;
		this.damageVariance = damageVariance;
		
		this.spellCost = spellCost;
		
		if(statusEffects == null) {
			this.statusEffects = new HashMap<>();
		} else {
			this.statusEffects = statusEffects;
		}
		
		spellUpgradeTree = new HashMap<>();
		this.upgradeList = upgradeList;
		initialiseBasicSpellUpgradeTree(upgradeList);

		this.attributeModifiers = attributeModifiers;
		this.extraEffects = extraEffects;
		
		modifiersList = new ArrayList<>();
		
		if (attributeModifiers != null) {
			for (Entry<AbstractAttribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>"
						+ " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
		
		
		// SVG:
		this.pathName = pathName;
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Spell icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);
			
			SVGString = SvgUtil.colourReplacement(this.toString(), damageType.getMultiplierAttribute().getColour(), SVGString);
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    protected boolean isTargetAtMaximumLust(GameCharacter target) {
    	return target!=null && target.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX);
    }
	
	private void initialiseBasicSpellUpgradeTree(List<SpellUpgrade> upgradeList) {
		if(upgradeList!=null) {
			for(int i = 0; i<upgradeList.size(); i++) {
				spellUpgradeTree.put(i, new ArrayList<>());
				spellUpgradeTree.get(i).add(new TreeEntry<>(spellSchool, i, upgradeList.get(i)));

				if(i==upgradeList.size()-1 && upgradeList.size()==4) {
					spellUpgradeTree.get(i-1).add(new TreeEntry<>(spellSchool, i-1, upgradeList.get(i)));
					spellUpgradeTree.get(i-2).get(0).addLink(spellUpgradeTree.get(i-1).get(1));
					
				} else if(i!=0) {
					spellUpgradeTree.get(i).get(0).addLink(spellUpgradeTree.get(i-1).get(0));
				}
			}
		}
	}

	public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		return applyEffect(caster, target, null, null, isHit, isCritical);
	}

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical);

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}
	
	public boolean isSpellBook() {
		return true;
	}
	
	public boolean isForbiddenSpell() {
		return forbiddenSpell;
	}

	public SpellSchool getSpellSchool() {
		return spellSchool;
	}

	public SpellType getType() {
		return type;
	}

	public DamageType getDamageType() {
		return damageType;
	}
	
	public boolean isBeneficial() {
		return beneficial;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter source) {
		return description;
	}

	public int getDamage(GameCharacter caster) {
		return damage;
	}

	public DamageVariance getDamageVariance() {
		return damageVariance;
	}

	/**
	 * @return The basic spell cost, not taking into account the caster's spell efficiency.
	 */
	public int getBaseCost(GameCharacter caster) {
		return spellCost;
	}
	
	/**
	 * @param caster The person casting the spell.
	 * @return The cost of casting this spell as it relates to the caster. i.e. This spell's basic spell cost, reduced by the caster's spell efficiency.
	 */
	public float getModifiedCost(GameCharacter caster) {
		float calculatedCost = getBaseCost(caster);
		
		calculatedCost *= ((100 - caster.getAttributeValue(Attribute.SPELL_COST_MODIFIER)) / 100f);
		
		// Round float value to nearest 1 decimal place:
		return (Math.round(calculatedCost*10))/10f;
	}
	
	public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		return statusEffects;
	}

	/**
	 * @return A list of all available SpellUpgrades for this Spell. <b>You should most likely be checking getSpellUpgradeTree() instead!</b>
	 */
	public List<SpellUpgrade> getUpgradeList() {
		return upgradeList;
	}
	
	public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
		return spellUpgradeTree;
	}

	public HashMap<AbstractAttribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}
	
	public String getSVGString() {
		return SVGString;
	}

	public String getPathName() {
		return pathName;
	}
	
	protected void applyStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		for (Entry<AbstractStatusEffect, Integer> se : getStatusEffects(caster, target, isCritical).entrySet()) {
			Main.combat.addStatusEffectToApply(target, se.getKey(), se.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1));
		}
	}

	protected String getFormattedSpellDamageRange(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		return "Наносит <span style='color:"+getDamageType().getColour().toWebHexString()+";'>"
				+Math.round(Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance()))
				+"-"
				+Math.round(Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance()))
				+ " " +damageType.getName()
				+ "</span> урона";
	}
	
	protected String getDamageDescription(GameCharacter caster, GameCharacter target, float damage, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();
		
		boolean appliesEffects = !this.getStatusEffects(caster, target, isCritical).isEmpty();

		damageCostDescriptionSB.append("<br/>");
			if (caster.isPlayer()) {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(Ты промахнулся!)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"Ты [style.boldExcellent(критично)] поразишь [npc.name] на <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
						if(appliesEffects) {
							damageCostDescriptionSB.append(" Ты [style.boldExcellent(критично)] запустишь заклинание, удвоив его продолжительность!");
						}
					}
				} else {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(Вы промахнулись!)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"Вы поразили [npc.name] на <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
					}
				}
				
			} else {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append(UtilText.parse(caster, "[style.italicsBad([npc1.Name] промахнулся!)]"));
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(caster, target,
									"[npc1.Name] [style.boldExcellent(критически)] поразил " + (target.isPlayer()?"вы":"[npc2.name]")+" на <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
						if(appliesEffects) {
							damageCostDescriptionSB.append(UtilText.parse(caster, " [npc.Name] [style.boldExcellent(критически)] запустил заклинание, удвоив его продолжительность!"));
						}
					}
				} else {
					if(!isHit) {
						damageCostDescriptionSB.append(UtilText.parse(caster, "[style.italicsBad([npc1.Name] промахнулся!)]"));
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(caster, target,
									"[npc1.Name] поразил " + (target.isPlayer()?"вы":"[npc2.name]")+" на <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
					}
				}
			}
			if(damageCostDescriptionSB.toString().toString().equals("<br/>")) {
				return "";
			}
		
		return damageCostDescriptionSB.toString();
	}
	
	protected String applyDamage(GameCharacter caster, GameCharacter target, float damage) {
		return this.getDamageType().damageTarget(caster, target, (int)damage).getKey();
//		return target.incrementHealth(caster, -damage);
	}
	
	protected String getStatusEffectApplication(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		if (this.getStatusEffects(caster, target, isCritical) != null && !this.getStatusEffects(caster, target, isCritical).isEmpty() && isHit) {
			damageCostDescriptionSB.append(
					"<br/>"+UtilText.parse(target,
								(!target.isPlayer()
									? "[npc.Name] теперь "
									: "Вы теперь ")
								+(this.isBeneficial()
										?"получая эффект "
										:"получая дебафф ")));
			
			int i = 0;
			for (Entry<AbstractStatusEffect, Integer> seEntry : this.getStatusEffects(caster, target, isCritical).entrySet()) {
				if (i != 0) {
					if (i == statusEffects.size() - 1) {
						damageCostDescriptionSB.append(" и ");
					} else {
						damageCostDescriptionSB.append(", ");
					}
				}
				damageCostDescriptionSB.append("<b>" + seEntry.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1)
						+ "</b> ходов"
						+(caster.hasTrait(Perk.JOB_MUSICIAN, true)
								?" ([style.boldExcellent(удвоенный)] от <b style='color:"+Perk.JOB_MUSICIAN.getColour().toWebHexString()+";'>"+Perk.JOB_MUSICIAN.getName(caster)+"</b>)"
								:"")
						+ " из <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				i++;
			}
			damageCostDescriptionSB.append(".");
		}
		
		return damageCostDescriptionSB.toString();
	}
	
	public static String getBasicStatusEffectApplication(GameCharacter target, boolean beneficial, Map<AbstractStatusEffect, Integer> statusEffects) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		damageCostDescriptionSB.append(
				"<br/>"+UtilText.parse(target,
							(!target.isPlayer()
								? "[npc.She] теперь "
								: "Ты теперь ")
							+(beneficial
									?"получая эффект "
									:"получая дебафф ")));
		
		int i = 0;
		for (Entry<AbstractStatusEffect, Integer> seEntry : statusEffects.entrySet()) {
			if (i != 0) {
				if (i == statusEffects.size() - 1) {
					damageCostDescriptionSB.append(" и ");
				} else {
					damageCostDescriptionSB.append(", ");
				}
			}
			damageCostDescriptionSB.append("<b>" + seEntry.getValue() + "</b> раундов <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
			i++;
		}
		damageCostDescriptionSB.append(".");
		
		return damageCostDescriptionSB.toString();
	}

	protected String getCostDescription(GameCharacter caster, float cost) {
		if(cost<0) {
			return "<br/>Зачатие этого заклинания стоит "+(caster.isPlayer()?"you":UtilText.parse(caster, "[npc.name]"))+" <b>"
					+ -cost + "</b> <b style='color:" + Attribute.HEALTH_MAXIMUM.getColour().toWebHexString() + ";'>"+Attribute.HEALTH_MAXIMUM.getName()+"</b>!</b>";
		} else {
			return "<br/>Зачатие этого заклинания стоит "+(caster.isPlayer()?"you":UtilText.parse(caster, "[npc.name]"))+" <b>"
					+ cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>аура</b>!</b>";
		}
	}

	/**
	 * Utility method for returning appropriate cast description based on the identity of caster and target. Variable names should be self-explanatory.
	 */
	private static String getCastDescription(GameCharacter caster, GameCharacter target,
			List<String> chuuniDialogue,
			String playerSelfCast,
			String playerCastOnNPC,
			String NPCSelfCast,
			String NPCCastOnPlayer,
			String NPCCastOnNPC) {
		StringBuilder sb = new StringBuilder();
		
		if(caster.hasTraitActivated(Perk.CHUUNI) && chuuniDialogue!=null) {
			sb.append(UtilText.parse(caster, target, "[npc.speech("+Util.randomItemFrom(chuuniDialogue)+")]</br>"));
		}
		if(caster.isPlayer()) {
			if(target.isPlayer()) {
				sb.append(playerSelfCast);
			} else {
				sb.append(UtilText.parse(target, playerCastOnNPC));
			}
		} else {
			if(target.isPlayer()) {
				sb.append(UtilText.parse(caster, NPCCastOnPlayer));
			} else {
				if(target.equals(caster)) {
					sb.append(UtilText.parse(caster, NPCSelfCast));
				} else {
					sb.append(UtilText.parse(caster, target, NPCCastOnNPC));
				}
			}
		}
		return sb.toString();
	}
	
	// Rendering:
	
	private static final int ROWS = 3;
	
	private static StringBuilder treeSB = new StringBuilder();
	private static StringBuilder spellSB = new StringBuilder();
	private static StringBuilder lineSB = new StringBuilder();
	private static StringBuilder entrySB = new StringBuilder();
	

	public static String getSpellMiscTreeDisplay(GameCharacter character, GameCharacter target) {
		treeSB.setLength(0);

		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+PresetColour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Разное</h6></div>");
		
		for(Spell spell : Spell.values()) {
			if(!spell.isSpellBook()) { // Only append special spells obtained from weapons & other sources
				treeSB.append("<div class='container-full-width' style='border:1px solid "+(character.hasSpell(spell, true)?PresetColour.DAMAGE_TYPE_SPELL:PresetColour.BASE_GREY_DARK).toWebHexString()+"; width:25%; padding:0; margin:0;'>");
					treeSB.append(appendSpell(character, target, -1, spell, true));
				treeSB.append("</div>");
			}
		}
		
		treeSB.append("</div>");
		
		return treeSB.toString();
	}
	
	public static String getSpellTreesDisplay(SpellSchool school, GameCharacter character, GameCharacter target) {
		treeSB.setLength(0);
		appendSpellSchool(school, character, target);
		return treeSB.toString();
	}
	
	private static void appendSpellSchool(SpellSchool spellSchool, GameCharacter character, GameCharacter target) {
		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+spellSchool.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spellSchool.getName())+"</h6>"
						+"<b style='color:"+spellSchool.getColour().toWebHexString()+";'>"+character.getSpellUpgradePoints(spellSchool)+"</b> <b>Доступно очков улучшения</b></div>");
		
		for(Spell spell : Spell.getSpellsFromSchoolMap().get(spellSchool)) {
			if(spell.isSpellBook()) { // Do not append spells obtained from weapons & other sources
				boolean fullyUpgraded = character.isSpellFullyUpgraded(spell);
				
				if(!spell.getSpellUpgradeTree().isEmpty()) {
					treeSB.append("<div class='container-full-width' style='border:1px solid "+(fullyUpgraded?spell.getSpellSchool().getColour():PresetColour.BASE_GREY_DARK).toWebHexString()+"; width:25%; padding:0; margin:0;'>");
						for(int row=-1; row<ROWS; row++) {
							treeSB.append(appendSpell(character, target, row, spell, false));
						}
					treeSB.append("</div>");
				}
			}
		}
		
		treeSB.append("</div>");
	}
	
	private static String appendSpell(GameCharacter character, GameCharacter target, int row, Spell spell, boolean miscSpell) {
		spellSB.setLength(0);

		spellSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
			if(row==-1) {
				boolean hasSpell = character.hasSpell(spell, miscSpell);
				boolean forbidden = spell.isForbiddenSpell();
				
				spellSB.append("<div class='square-button "+(!hasSpell?" disabled":"")+"' style='width:50%; margin:8px 25% 4px 25%; cursor: default; "
										+(hasSpell
												?"border-color:"+spell.getSpellSchool().getColour().toWebHexString()+";"
												:"")+"' id='SPELL_TREE_"+spell+"'>"
									+ "<div class='square-button-content' style='cursor: default;'>"+spell.getSVGString()+"</div>"
									+ (!hasSpell
										?(forbidden
											?"<div class='overlay disabled-dark' style='cursor:default;'></div>"
											:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); '></div>")
										:"")
								+ "</div>");
				
				Value<Boolean, String> useDesc = spell.getSpellCastOutOfCombatDescription(character, target);
				spellSB.append("<div class='normal-button "+(useDesc.getKey()?"":"disabled")+"' id='SPELL_TREE_CAST_"+spell+"' style='width:50%; margin:8px 25% "+(miscSpell?"8px":"0")+" 25%; text-align:center;'>");
				spellSB.append("Применить");
				spellSB.append("</div>");
				
			} else {
				List<TreeEntry<SpellSchool, SpellUpgrade>> upgradesList = spell.getSpellUpgradeTree().get(row);
				int size = upgradesList.size();
				
				spellSB.append(getHorizontalLine(character, spell, row));
				for(TreeEntry<SpellSchool, SpellUpgrade> entry : upgradesList) {
					spellSB.append(getUpgradeEntry(character, spell, entry, size));
				}
			}
		spellSB.append("</div>");
		return spellSB.toString();
	}
	
	private static String getHorizontalLine(GameCharacter character, Spell spell, int row) {
		lineSB.setLength(0);
		
		for(TreeEntry<SpellSchool, SpellUpgrade> entry : spell.getSpellUpgradeTree().get(row)) {
			float entryX = getX(spell, entry.getRow(), entry);
			for(TreeEntry<SpellSchool, SpellUpgrade> siblingEntry : entry.getSiblingLinks()) {
				float siblingX = getX(spell, siblingEntry.getRow(), siblingEntry);
				lineSB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
						+ "<svg width='100%' height='100%'><line x1='"+siblingX+"%' y1='50%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineSiblingColour(character, spell, entry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
			}
			for(TreeEntry<SpellSchool, SpellUpgrade> parentEntry : entry.getParentLinks()) {
				float parentX = getX(spell, parentEntry.getRow(), parentEntry);
				String colour = getPerkLineParentColour(character, spell, entry).toWebHexString();
						
				if(Math.abs(entryX-parentX)>0.01f) {
					lineSB.append("<div style='width:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events: none;'>"
							+ "<svg style='padding:0; margin:0;' width='100%'><line x1='"+entryX+"%' y1='0' x2='"+parentX+"%' y2='0' stroke='"+colour+"' stroke-width='4px'/></svg></div>");
				}
			}
		}
		
		return lineSB.toString();
	}
	
	private static float getMargin(int size) {
		return (100-(size*40))/(size*2f);
	}
	
	private static float getX(Spell spell, int row, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		List<TreeEntry<SpellSchool, SpellUpgrade>> list = spell.getSpellUpgradeTree().get(row);
		int size = list.size();
		float marginSize = getMargin(size);
		int column = list.indexOf(entry);
		
		return ((marginSize*(column)*2)+(column*40)+20+marginSize);
	}
	
	private static String getUpgradeEntry(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> perkEntry, int size) {
		
		entrySB.setLength(0);

		boolean forbidden = spell.isForbiddenSpell();
		boolean hasUpgrade = character.hasSpellUpgrade(perkEntry.getEntry());
		boolean isUpgradeAvailable = isSpellUpgradeAvailable(character, spell, perkEntry);
		
		// Append up/down lines:
		float entryX = getX(spell, perkEntry.getRow(), perkEntry);
		if(!perkEntry.getParentLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='0%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineParentColour(character, spell, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		if(!perkEntry.getChildLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='100%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineChildColour(character, spell, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		
		entrySB.append("<div class='square-button round"+(!hasUpgrade && !isUpgradeAvailable?" disabled":"")+"' style='width:40%; margin:8px "+getMargin(size)+"%; "
										+ (character.hasSpellUpgrade(perkEntry.getEntry())
											?"cursor: default; border-color:"+perkEntry.getCategory().getColour().toWebHexString()+";"
											:(!perkEntry.getEntry().isAvailable(character) //|| character.getSpellUpgradePoints(perkEntry.getCategory()) < perkEntry.getEntry().getPointCost()
												?"cursor: default; border-color:"+PresetColour.GENERIC_BAD.toWebHexString()+";"
												:""))
										+"' id='SPELL_UPGRADE_"+perkEntry.getEntry()+"'>"
							+ "<div class='square-button-content'>"+perkEntry.getEntry().getSVGString()+"</div>"
							+ (!hasUpgrade && !isUpgradeAvailable
								?(forbidden
										?"<div class='overlay disabled-dark' style='border-radius:50%;'></div>"
										:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.8); border-radius:50%; cursor: default;'></div>")
								:(!hasUpgrade
									?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.6); border-radius:50%; cursor:pointer;'></div>"
									:""))
						+ "</div>");
		
		return entrySB.toString();
	}
	
	public static boolean isSpellUpgradeAvailable(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		if(character.hasSpell(spell) && entry.getEntry().isAlwaysAvailable()) {
			return true;
		}
		if(!entry.getEntry().isAvailable(character)) {
			return false;
		}
		if(!character.hasSpellUpgrade(entry.getEntry())) {
			for(TreeEntry<SpellSchool, SpellUpgrade> linkedEntry : entry.getLinks()) {
				if(character.hasSpellUpgrade(linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static Colour getPerkLineParentColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean parentOwned = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> parent : entry.getParentLinks()) {
			if(character.hasSpellUpgrade(parent.getEntry())) {
				parentOwned = true;
				break;
			}
		}
		
		return character.hasSpellUpgrade(entry.getEntry()) && parentOwned
				?entry.getCategory().getColour()
				:isSpellUpgradeAvailable(character, spell, entry)
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	private static Colour getPerkLineChildColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean childOwned = false;
		boolean childAvailable = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> child : entry.getChildLinks()) {
			if(character.hasSpellUpgrade(child.getEntry())) {
				childOwned = true;
			}
			if(isSpellUpgradeAvailable(character, spell, child)) {
				childAvailable = true;
			}
		}
		
		return character.hasSpellUpgrade(entry.getEntry()) && childOwned
				?entry.getCategory().getColour()
				:childAvailable
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}
	
	private static Colour getPerkLineSiblingColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean siblingOwned = false;
		boolean siblingAvailable = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> sibling : entry.getSiblingLinks()) {
			if(character.hasSpellUpgrade(sibling.getEntry())) {
				siblingOwned = true;
			}
			if((isSpellUpgradeAvailable(character, spell, sibling) && character.hasSpellUpgrade(entry.getEntry()))
					|| (isSpellUpgradeAvailable(character, spell, entry) && character.hasSpellUpgrade(sibling.getEntry()))) {
				siblingAvailable = true;
			}
		}
		
		return isSpellUpgradeAvailable(character, spell, entry) && siblingOwned
				?entry.getCategory().getColour()
				:siblingAvailable
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}

	// Combat maneuver compatibility
	// These functions are almost identical to the ones in CombatMove class,  with modifications to fit spells as necessary. Refer to CombatMove class for information.

	public int getAPCost() {
		return 1; // Normally just 1 AP.
	}

	public int getCooldown() {
		return 0; // Normally no CD.
	}

	public boolean isCanTargetEnemies() {
		return !isBeneficial();
	}

	public boolean isCanTargetAllies() {
		return isBeneficial();
	}

	public boolean isCanTargetSelf() {
		return isBeneficial();
	}

	public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
		if(!owner.hasSpell(this)) {
			return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do] не знает этого заклинания, поэтому не может его сотворить!"));
			
		} else if(Main.game.isInCombat()) {
			return new Value<>(false, UtilText.parse(owner, "В бою заклинания можно использовать только как боевой прием!"));
		}
		
		return new Value<>(false, "Это заклинание можно применить только во время боя!");
	}

	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(isCanTargetAllies() && allies.isEmpty()) {
			return 0.0f;
		}
		
		if(this.getType().isStatusEffectFocus()) { // If this spell is just for the application of a status effect, do not use it if all targets already have that status effect:
			boolean noEffect = true;
			if(isCanTargetEnemies()) { // Enemy status effect application:
				Set<GameCharacter> survivingEnemies = new HashSet<>(enemies);
				survivingEnemies.removeIf(enemy -> Main.combat.isCombatantDefeated(enemy));
//				System.out.println(survivingEnemies.size());
				enemyLoop:
				for(GameCharacter enemy : survivingEnemies) {
					List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, enemy, false).keySet());
					if(!statusEffects.isEmpty()) {
						for(AbstractStatusEffect se : statusEffects) {
							if(!enemy.hasStatusEffect(se)) {
								boolean alreadyTargetedWithThisSpell = false;
								for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
									if(move.getKey()==enemy && move.getValue().getAssociatedSpell()==this) {
										alreadyTargetedWithThisSpell = true;
										break;
									}
								}
								if(!alreadyTargetedWithThisSpell) {
									noEffect = false;
//									System.out.println(source.getName()+" | "+this.getName());
									break enemyLoop;
								}
							}
						}
					}
				}
				
			} else {
				Set<GameCharacter> survivingAllies = new HashSet<>(allies);
				survivingAllies.add(source);
				survivingAllies.removeIf(ally -> Main.combat.isCombatantDefeated(ally));
				allyLoop:
				for(GameCharacter ally : survivingAllies) {
					List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, ally, false).keySet());
					if(!statusEffects.isEmpty()) {
						for(AbstractStatusEffect se : statusEffects) {
							if(!ally.hasStatusEffect(se)) {
								boolean alreadyTargetedWithThisSpell = false;
								for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
									if(move.getKey()==ally && move.getValue().getAssociatedSpell()==this) {
										alreadyTargetedWithThisSpell = true;
										break;
									}
								}
								if(!alreadyTargetedWithThisSpell) {
									noEffect = false;
									break allyLoop;
								}
							}
						}
					}
				}
			}
			if(noEffect) {
				return 0;
			}
		}
		
		int behaviourMultiplier = 1;
		if(source.getCombatBehaviour()==CombatBehaviour.ATTACK && !this.isBeneficial()) {
			behaviourMultiplier = 2;
		}
		if(source.getCombatBehaviour()==CombatBehaviour.SUPPORT && this.isBeneficial()) {
			behaviourMultiplier = 10;
		}
		if(source.getCombatBehaviour()==CombatBehaviour.SPELLS) {
			behaviourMultiplier = 10;
		}
		return (0.2f*behaviourMultiplier) - 0.2f * source.getSelectedMovesByType(CombatMoveType.SPELL);
	}

	public GameCharacter getPreferredTarget(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(Main.game.isInCombat() && source.isPlayer()) {
			return Main.combat.getTargetedCombatant();
		}
		
		if(Main.game.isInCombat()) {
			GameCharacter preferredTarget = Main.combat.getPreferredTarget(source);
	    	if(preferredTarget!=null && !Main.combat.isCombatantDefeated(preferredTarget)) {
	    		return preferredTarget;
	    	}
		}
		
		if(isCanTargetEnemies()) {
			if(AbstractCombatMove.shouldBlunder()) {
				return enemies.get(Util.random.nextInt(enemies.size()));
				
			} else {
				float lowestHP = -1;
				GameCharacter potentialCharacter = null;
				
				if(this.getType().isStatusEffectFocus()) { // If this spell is primarily concerned with applying a status effect, only use it on targets who do not already have that status effect:
					Set<GameCharacter> survivingEnemies = new HashSet<>(enemies);
					survivingEnemies.removeIf(enemy -> Main.combat.isCombatantDefeated(enemy));
					enemyLoop:
					for(GameCharacter enemy : survivingEnemies) {
						List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, enemy, false).keySet());
						if(!statusEffects.isEmpty()) {
							for(AbstractStatusEffect se : statusEffects) {
								if(!enemy.hasStatusEffect(se)) {
									boolean alreadyTargetedWithThisSpell = false;
									for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
										if(move.getKey()==enemy && move.getValue().getAssociatedSpell()==this) {
											alreadyTargetedWithThisSpell = true;
											break;
										}
									}
									if(!alreadyTargetedWithThisSpell) {
										potentialCharacter = enemy;
										break enemyLoop;
									}
								}
							}
						}
					}
				}
				
				if(potentialCharacter==null) {
					for(GameCharacter character : enemies) {
						if(lowestHP == -1 || character.getHealth() < lowestHP) {
							potentialCharacter = character;
							lowestHP = character.getHealth();
						}
					}
				}
				return potentialCharacter;
			}
		}
		if(isCanTargetAllies() && !allies.isEmpty()) {
			if(AbstractCombatMove.shouldBlunder()) {
				return allies.get(Util.random.nextInt(allies.size()));
				
			} else {
				float lowestHP = -1;
				GameCharacter potentialCharacter = null;
				
				if(this.getType().isStatusEffectFocus()) { // If this spell is primarily concerned with applying a status effect, only use it on targets who do not already have that status effect:
					Set<GameCharacter> survivingAllies = new HashSet<>(allies);
					survivingAllies.add(source);
					survivingAllies.removeIf(ally -> Main.combat.isCombatantDefeated(ally));
					allyLoop:
					for(GameCharacter ally : survivingAllies) {
						List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, ally, false).keySet());
						if(!statusEffects.isEmpty()) {
							for(AbstractStatusEffect se : statusEffects) {
								if(!ally.hasStatusEffect(se)) {
									boolean alreadyTargetedWithThisSpell = false;
									for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
										if(move.getKey()==ally && move.getValue().getAssociatedSpell()==this) {
											alreadyTargetedWithThisSpell = true;
											break;
										}
									}
									if(!alreadyTargetedWithThisSpell) {
										potentialCharacter = ally;
										break allyLoop;
									}
								}
							}
						}
					}
				}
				
				if(potentialCharacter==null) {
					for(GameCharacter character : allies) {
						if(lowestHP == -1 || character.getHealth() < lowestHP) {
							potentialCharacter = character;
							lowestHP = character.getHealth();
						}
					}
				}
				return potentialCharacter;
			}
		}
		return source;
	}
	
	public abstract String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies);
	
	public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
        StringBuilder predictionSB = new StringBuilder();
        
        predictionSB.append(
				(isCrit?"[style.colourExcellent(Критический)]: ":"")
				+ "<span style='color:" + getSpellSchool().getColour().toWebHexString() + ";'>Применить заклинание '"+ getName() + "'</span> на [npc2.name].");

    	if(getSpellSchool()==SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN) && Main.combat.getManaBurnStack().get(source).size()>0 && Main.combat.getManaBurnStack().get(source).peek()<0) {
    		predictionSB.append("<br/>This will cost <b style='color:"+PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+";'>"+Units.round((-Main.combat.getManaBurnStack().get(source).peek()), 1)+" "+Attribute.HEALTH_MAXIMUM.getName()+"</b>"
    				+ " ([style.colourFire("+StatusEffect.FIRE_MANA_BURN.getName(source)+")]).");
    	} else {
    		predictionSB.append("<br/>Это будет стоить <b style='color:"+PresetColour.ATTRIBUTE_MANA.toWebHexString()+";'>"+this.getModifiedCost(source)+" аура</b>.");
    	}
    	
        predictionSB.append("<br/><i>"+getBasicEffectsString(source, target, enemies, allies)+"</i>");
        
        return UtilText.parse(source, target, predictionSB.toString());
	}

	public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.applyEffect(source, target, enemies, allies, true, isCrit));
		
		if(isCrit && !this.isBeneficial() && source.hasPerkAnywhereInTree(Perk.ARCANE_CRITICALS)) {
			sb.append(UtilText.parse(source, "<br/>[npc.NamePos] [style.boldExcellent(critical)] заклинание накладывает [style.boldArcane(arcane weakness)] на "+(target.isPlayer()?"вы":UtilText.parse(target, "[npc.name]"))+"!"));
			target.addStatusEffect(StatusEffect.ARCANE_WEAKNESS, 2);
			sb.append(
					UtilText.parse(target,
							"<br/>[npc.NameIsFull] теперь под действием <b style='color:"+StatusEffect.ARCANE_WEAKNESS.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(StatusEffect.ARCANE_WEAKNESS.getName(target))+"</b>"
									+ " на <b>две ходы</b>!"));
		}
		
		return sb.toString();
	}

	// Applies mana cost effects here. If overridden, don't forget to super call it unless it's a free spell.
	public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(getSpellSchool() == SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
			if(!Main.game.isInCombat()) {
				Main.combat.setupManaBurnStackForOutOfCombat(source);
			}
			Main.combat.getManaBurnStack().get(source).push(source.burnMana(getModifiedCost(source)));
			
		} else {
			source.incrementMana(-getModifiedCost(source));
		}
	}
	
    public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	if(getSpellSchool() == SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
    		float amount = Main.combat.getManaBurnStack().get(source).pop();
    		if(amount<0) {
        		source.incrementHealth(-amount);
    		} else {
    			source.incrementMana(amount);
    		}
		} else {
			source.incrementMana(getModifiedCost(source));
		}
    }

	public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		// Override. Note that disrupted spells don't disrupt their mana.
	}

	//TODO combine these two methods:
	
    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	List<String> critReqs = new ArrayList<>();

    	if(this.getSpellSchool() == SpellSchool.FIRE) {
    		critReqs.add(UtilText.parse(source, "[npc.NamePos] "+Attribute.HEALTH_MAXIMUM.getName()+" ниже 25%."));
    	} else {
        	return Util.newArrayListOfValues("Это единственное действие, используемое.");
    	}
    	
    	return critReqs;
    }
	
	//Differs from normal version; spells have special crit requirements.
	public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(this.getSpellSchool() == SpellSchool.FIRE) {
			return source.getHealthPercentage()<=0.25f; // Fire school spells crit when below 25% health.
		} else {
			return source.getSelectedMoves().size()<=1;
		}
	}
}
