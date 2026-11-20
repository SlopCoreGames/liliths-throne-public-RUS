package com.lilithsthrone.game.combat.moves;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CMSpecialAttack {
	
	public static AbstractCombatMove HORSE_KICK = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "удар копытом",
            1,
            2,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            DamageVariance.NONE,
            "moves/hoof_kick",
            Util.newArrayListOfValues(
            		PresetColour.DAMAGE_TYPE_PHYSICAL,
            		PresetColour.BASE_RED),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 1)),
            Util.newHashMapOfValues()) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, (source.getUnarmedDamage() * 2 * (source.isLegMovementHindered()?0.1f:1)));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), false);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.HOOFS), "Доступно персонажам с копытами.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Наносит мощный удар копытом по " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (source.isLegMovementHindered()?" [style.italicsMinorBad(Урон снижен до 10% a, так как [npc.her] одежда сковывает движения ног!)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать свои сильные [npc.her] ноги, чтобы нанести мощный удар копытом по [npc.her], нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен на 10%, если [npc.her] одежда сковывает движения ног.)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean maxLust = isTargetAtMaximumLust(target);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            Value<String, Integer> critDamageValue = new Value<>("", 0);
            if(isCrit) {
            	critDamageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit)/2); // Second kick damage from the crit.
            }
            
            return formatAttackOutcome(source, target,
            		(source.isLegMovementHindered()
            				?"Так как [npc.her] одежда ограничивает движения [npc.her], [npc.name] [npc.verb(struggle)] вложить силу в [npc.her] удар, нанося минимальный урон [npc2.name]..."
            				:"[npc.Name] [npc.verb(turn)] в сторону, прежде чем выбросить ногу и мощно ударить [npc2.name] с помощью [npc.her] "+(source.getLegConfiguration()==LegConfiguration.QUADRUPEDAL?"hoofs":"hoof")+"!")
            			+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc.Name] тут же наносит повторный удар, пытаясь пробить [npc2.namePos] блок!"+critDamageValue.getKey()
            			:null),
                	"[npc2.Name] наносит дополнительные " + getFormattedDamage(damageType, critDamageValue.getValue(), target, true, maxLust) + " урон!");
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "Удар копытом не может пробить "+(target!=null?UtilText.parse(target,"[npc.namePos]"):"цели")+" защиту цели.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int damage = getDamage(turnIndex, source, target, false);
            int potentialDamage = getDamageType(turnIndex, source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage<=0) {// != damage) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove CAT_SCRATCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "царапина",
            1,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, ((source.getUnarmedDamage()*1.5f) * (source.isArmMovementHindered()?0.5f:1)));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.CAT_MORPH), "Доступно персонажам с кошачьими когтями.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести яростную царапину " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Урон снижен до 50% a, если [npc.her] одежда мешает движениям рук!)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] кошачьи когти, чтобы нанести яростную царапину [npc.her] цели, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен на 50%, если [npc.her] одежда мешает движениям рук.)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"Так как одежда [npc.her] сковывает движения [npc.her], [npc.name] [npc.verb(struggle)], чтобы нанести [npc.her], нанося лишь половину урона [npc2.name]..."
            				:"Выпустив когти на антропоморфных кошачьих лапах [npc.her], [npc.name] стремительно [npc.verb(dash)] вперед, пытаясь поцарапать [npc2.name]!")+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc.NamePos] особенно эффективен!"
            			:null),
                	"Длительность '"+StatusEffect.VULNERABLE.getName(target)+"' удваивается!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((target!=null?UtilText.parse(target,"[npc.NameHasFull]"):"У защитника есть")+" no "+getDamageType(0, source).getNameColoured()+" защиту цели.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return target.getShields(getDamageType(turnIndex, source))<=0;
        }
    };

	public static AbstractCombatMove TAIL_SWIPE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "удар хвостом",
            2,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/tail_swipe",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*3;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
            		(source.getTailType().isSuitableForAttack() && source.getTailLength(false)>=100)
            			|| source.getLegConfiguration()==LegConfiguration.TAIL_LONG,
            		"Доступно персонажам с подходящим хвостом длиной не менее [units.sizeShort(100)] или с '"+LegConfiguration.TAIL_LONG.getName()+"' нижней частью тела.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести громогласный удар хвостом по " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон.");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] [npc.tailRace]-хвост, чтобы нанести громоподобный удар по [npc.her] цели, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон.");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"[npc.Name] [npc.verb(turn)] в сторону, используя инерцию, чтобы впечатать [npc.her] огромный [npc.tailRace]-хвост прямо в [npc2.name]!"+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
        				?"Взмах хвостом [npc.NamePos] особенно эффективен!"
            			:null),
                    "Длительность '"+StatusEffect.DAZED.getName(target)+"' удваивается!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
            		(source!=null?UtilText.parse(source,"[npc.NamePos]"):"Атакующего")+" хвост составляет не менее '<span style='color:"+PenetrationGirth.FIVE_THICK.getColour().toWebHexString()+";'>"+PenetrationGirth.FIVE_THICK.getName()+"</span>'.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getTailGirth().getValue()>=PenetrationGirth.FIVE_THICK.getValue()) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove SQUIRREL_SCRATCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "беличья царапина",
            1,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch_double",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 1))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * (source.isArmMovementHindered()?0.5f:1));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.SQUIRREL_MORPH), "Доступно персонажам с беличьими когтями.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести яростную царапину " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Урон снижен до 50% a, если [npc.her] одежда мешает движениям рук!)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] острые когти, чтобы нанести жестокую царапину [npc.her] цели, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен на 50%, если [npc.her] одежда мешает движениям рук.)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            int dealtCritDamage2 = 0;
            int dealtCritDamage3 = 0;
            if(isCrit) {
            	dealtCritDamage2 = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, false)).getValue()/2;
            	dealtCritDamage3 = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, false)).getValue()/2;
            }
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"Так как одежда [npc.her] сковывает движения [npc.her], [npc.name] [npc.verb(struggle)], чтобы нанести [npc.her], нанося лишь половину урона [npc2.name]..."
            				:"Выпуская когти на [npc.her] антропоморфных беличьих лапах, [npc.name] стремительно [npc.verb(dash)] вперед, пытаясь полоснуть [npc2.name]!")+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc.Name] быстро [npc.verb(scratch)] [npc2.name] еще дважды!"
            			:null),
                	"[npc2.Name] наносит дополнительные "+getFormattedDamage(damageType, dealtCritDamage2, target, true, maxLust)+", а затем еще один "+getFormattedDamage(damageType, dealtCritDamage3, target, true, maxLust)+" урон!");
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((target!=null?UtilText.parse(target,"[npc.NameHasFull]"):"У защитника есть")+" no "+getDamageType(0, source).getNameColoured()+" защиту цели.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return target.getShields(getDamageType(turnIndex, source))<=0;
        }
    };

	public static AbstractCombatMove WOLF_SAVAGE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "свирепый удар",
            6,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/savage_attack",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 3))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
		@Override
        public int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * 4 * (source.isArmMovementHindered()?0.5f:1));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().getRace().equals(Race.WOLF_MORPH), "Доступно персонажам, обладающим руками волка-морфа.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести серию свирепых ударов по " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Урон снижен до 50% a, если [npc.her] одежда мешает движениям рук!)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] острые когти, чтобы нанести серию свирепых ударов по [npc.her], нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен на 50%, если [npc.her] одежда мешает движениям рук.)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"Так как одежда [npc.her] сковывает движения рук [npc.her], [npc.name] [npc.verb(struggle)] нанести лишь [npc.her] свирепых ударов, нанося лишь половину урона [npc2.name]..."
            				:"Свирепым воем [npc.name] [npc.verb(launch)] [npc.herself] на [npc2.name], умудряясь нанести значительный урон [npc2.herHim], раздирая [npc2.her] тело [npc.her] острыми когтями.")+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"Ярость атаки [npc.namePos] застает [npc2.name] врасплох!"
            			:null),
                    "Длительность '"+StatusEffect.CRIPPLE.getName(target)+"' удваивается!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "Используется в первом ходу боя.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Main.combat.getTurn()==0;
        }
    };

	public static AbstractCombatMove ANTLER_HEADBUTT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "удар рогами в голову",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/antlers",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getHornType().equals(HornType.REINDEER_RACK), "Доступно персонажам с рогами.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести мощный удар лбом по " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон.");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать свои [npc.her] рога, чтобы нанести мощный удар лбом по [npc.her], нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон.");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"С выбросом энергии [npc.name] [npc.verb(leap)] вперед, врезаясь [npc.her] лбом в [npc2.namePos] тело и хлестая [npc2.herHim] боками своих [npc.her] рогов."+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			? "Удар лбом [npc.NamePos] особенно эффективен!"
            			:null),
                    "Длительность '"+StatusEffect.DAZED.getName(target)+"' удваивается!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((source!=null?UtilText.parse(source,"[npc.NameIsFull]"):"Атакующий должен быть")+" как минимум на 50% t больше, чем "+(target!=null?UtilText.parse(target,"[npc.name]"):"цель")+".");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getHeightValue()>target.getHeightValue()*1.5f) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove COW_HEADBUTT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "удар рогами",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/horns",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasHorns() && !source.getHornType().equals(HornType.REINDEER_RACK), "Доступно персонажам, у которых есть рога.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести мощный удар лбом по " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон.");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] рога, чтобы нанести мощный удар лбом по [npc.her] цели, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон.");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"С выбросом энергии, [npc.name] [npc.verb(leap)] вперед, врезаясь [npc.her] лбом в [npc2.namePos] тело и хлестая [npc2.herHim] боками [npc.her] рогов."+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"Удар лбом [npc.NamePos] особенно эффективен!"
            			:null),
                    "Длительность '"+StatusEffect.DAZED.getName(target)+"' удваивается!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((source!=null?UtilText.parse(source,"[npc.NameIsFull]"):"Атакующий должен быть")+" как минимум на 50% t больше, чем "+(target!=null?UtilText.parse(target,"[npc.name]"):"цель")+".");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getHeightValue()>target.getHeightValue()*1.5f) {
                return true;
            }
            return false;
        }
    };
    
    public static AbstractCombatMove BITE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дикий укус",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/bite",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
    	@Override
        public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
    		Map<AbstractStatusEffect, Integer> effects = Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 2));

            if(caster.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)) {
            	effects.put(StatusEffect.POISONED, 6);
            }
            if(caster.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)) {
            	effects.put(StatusEffect.POISONED_LUST, 6);
            }
            
        	if(isCritical) {
        		return effects;
        	}
    		return effects;
    	}
    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(!source.isCoverableAreaExposed(CoverableArea.MOUTH)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}
    	@Override
        public int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage() * 2 * (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?0:1);
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
				!Collections.disjoint(source.getFaceTypeTags(), Util.newArrayListOfValues(
						BodyPartTag.FACE_MUZZLE,
						BodyPartTag.FACE_FANGS,
//						BodyPartTag.FACE_SHARK_TEETH,
						BodyPartTag.FACE_BEAK
				)),
				"Доступно персонажам с антропоморфным лицом.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Нанести дикий укус " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон"
            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
        						?" и накладывая эффект 'отравления' на 6 ходов."
        						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
                					?" и накладывая эффект 'похотливого отравления' на 6 ходов."
                					:"."))
            				+ (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?" [style.italicsBad(Урон снижается до 0% a, если [npc.her] одежда закрывает [npc.her] рот!)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать антропоморфную морду [npc.her], чтобы нанести дикий укус цели [npc.her], нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон"
            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
        						?" и накладывая эффект 'отравления' на 6 ходов."
        						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
                					?" и накладывая эффект 'похотливого отравления' на 6 ходов."
                					:"."))
            				+ " [style.italicsBad(Урон снижается до 0%, если одежда [npc.her] закрывает рот [npc.her].)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(!source.isCoverableAreaExposed(CoverableArea.MOUTH)
            				?"Поскольку одежда [npc.her] закрывает рот [npc.her], [npc.nameIsFull] не может нанести урон своим диким укусом [npc.her]..."
            				:"С приливом энергии [npc.name] [npc.verb(leap)] вперед, пытаясь укусить [npc2.name]! [npc.Her] [npc.mouth] впивается в [npc2.her] [npc2.arm], и [npc.she] [npc.verb(manage)], чтобы нанести серьезный урон с помощью [npc.her] "+(source.getFaceType()==FaceType.HARPY?"острого клюва":"звериных зубов")+" прежде чем [npc2.she] [npc2.verb(pull)] освободится."
	            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
	            						?" В процессе укуса ядовитыми клыками [npc.namePos], в [npc2.namehasFull] был впрыснут яд!"
	            						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
	                    					?" В процессе укуса ядовитыми клыками [npc.namePos], в [npc2.namehasFull] был впрыснут яд похоти!"
	                    					:"")))
            			+damageValue.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"Дикий укус [npc.NamePos] особенно эффективен!"
            			:null),
                    "Длительность '"+StatusEffect.CRIPPLE.getName(target)+"' удваивается!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((target!=null?UtilText.parse(target,"[npc.NameHasFull]"):"У защитника есть")+" менее 25% "+Attribute.HEALTH_MAXIMUM.getColouredName("span")+" осталось.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(target.getHealthPercentage()<0.25f) {
                return true;
            }
            return false;
        }
    };

    public static AbstractCombatMove TALON_SLASH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "удар когтями",
            1,
            1,
            10,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            DamageVariance.HIGH,
            "moves/talon_slash",
            Util.newArrayListOfValues(PresetColour.RACE_HARPY),
            false,
            true,
            false,
            Util.newHashMapOfValues(),
            Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {
        @Override
        public DamageType getDamageType(int turnIndex, GameCharacter source) {
        	return DamageType.UNARMED.getParentDamageType(source, null);
    	}
        @Override
        public int getBaseDamage(GameCharacter source) {
//            return (int) Math.max(1, 20 * (source.isLegMovementHindered()?0.1f:1)); // kerambit damage
            return (int) Math.max(1, source.getUnarmedDamage() * 1.2f * (source.isLegMovementHindered()?0.1f:1));
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), false);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.TALONS), "Доступно персонажам с когтями.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
                    "Наносит мощный рубящий удар [npc.her] когтями по " + (target==null?"[npc.her] цель":"[npc2.name]") + ", нанося "
                            + getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + " урон."
                            + (source.isLegMovementHindered()?" [style.italicsMinorBad(Урон снижен до 10% a, так как [npc.her] одежда сковывает движения ног!)]":""));
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source,
                    "[npc.Name] может использовать [npc.her] острые когти, чтобы нанести мощный рубящий удар по [npc.her], нанося базовый "
                            + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен на 10%, если [npc.her] одежда сковывает движения ног.)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean maxLust = isTargetAtMaximumLust(target);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            Value<String, Integer> critDamageValue = new Value<>("", 0);
            if(isCrit) {
                critDamageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit)/2); // Second slash damage from the crit.
            }

            return formatAttackOutcome(source, target,
                    (source.isLegMovementHindered()
                            ?"Так как [npc.her] одежда сковывает движения ног [npc.her], [npc.name] [npc.verb(struggle)], чтобы эффективно использовать [npc.her] когти, нанося минимальный урон [npc2.name]..."
                            :"[npc.Name] [npc.verb(vault)] в воздухе, а затем приземляется на вытянутых ногах, мощно полосуя [npc2.name] [npc.her] когтями!")
                            +damageValue.getKey(),
                    "[npc2.Name] получил " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " урон!",
                    (isCrit
                            ?"[npc.Name] немедленно [npc.verb(slash)] снова, пробив блок [npc2.namePos]!"+critDamageValue.getKey()
                            :null),
                    "[npc2.Name] наносит дополнительные " + getFormattedDamage(damageType, critDamageValue.getValue(), target, true, maxLust) + " урон!");
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
                    "Удары когтями ломают "+(target!=null?UtilText.parse(target,"[npc.namePos]"):"цели")+" защиту цели.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            int damage = getDamage(turnIndex, source, target, false);
            int potentialDamage = getDamageType(turnIndex, source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage>0) {
                return true;
            }
            return false;
        }
    };
}
