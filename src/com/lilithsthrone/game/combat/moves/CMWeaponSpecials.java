package com.lilithsthrone.game.combat.moves;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.7.8
 * @version 0.3.7.8
 * @author Innoxia
 */
public class CMWeaponSpecials {
	
	public static AbstractCombatMove MKAR_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "очередь",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 21_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_mkar")), "Доступно персонажам, у которых экипирован MKAR.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Выпустить весь 30-зарядный магазин в режиме автоматического огня, нанося "+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+" урона за каждую попавшую пулю.<br/>[style.italicsGood(Бьет по всем врагам!)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может переключиться на автоматический режим и выпустить весь 30-зарядный магазин по [npc.her] врагам, нанося "+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+" урона за каждую попавшую пулю.<br/>[style.italicsGood(Бьет по всем врагам!)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 30 - 5 - Util.random.nextInt(6); // Hit with 20-25 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();
            
            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "rifle";
    		}
            
        	attackDesc.append(UtilText.parse(source,
        			"Переключив селектор огня на [npc.her] "+weaponName+" в режим автоматического огня, [npc.name] [npc.verb(take)] и [npc.verb(pull)] спусковой крючок, он обрушивает оглушительный, смертоносный град пуль на "
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her] врагов")+"!"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull], пораженных [style.boldTerrible(\""+finalBullets+")] пуль"+(finalBullets==1?"":"s")+"!"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name] получил "+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+" урон!"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
    

	public static AbstractCombatMove BR14_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "очередь",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 26_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_br14")), "Доступно персонажам с экипированным BR14.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Опустошает весь 20-зарядный магазин в режиме автоматического огня, нанося "+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+" урона за каждую попавшую пулю.<br/>[style.italicsGood(Бьет по всем врагам!)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может переключиться на автоматический режим и опустошить весь 20-зарядный магазин по [npc.her] врагам, нанося "+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+" урона за каждую попавшую пулю.<br/>[style.italicsGood(Бьет по всем врагам!)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 20 - 5 - Util.random.nextInt(11); // Hit with 5-15 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();

            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "rifle";
    		}
    		
        	attackDesc.append(UtilText.parse(source,
        			"Переключая селектор огня на [npc.her] "+weaponName+" в полностью автоматический режим, [npc.name] [npc.verb(take)] прицеливается и [npc.verb(pull)] спусковой крючок, обрушивая оглушительный, смертоносный град пуль по "
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her] врагов")+"!"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull], пораженных [style.boldTerrible(\""+finalBullets+")] пуль"+(finalBullets==1?"":"s")+"!"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name] получил "+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+" урон!"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
    
    public static AbstractCombatMove FAUXMAS_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "очередь",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 18_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_famase")), "Доступно персонажам с экипированным FAUXMAS.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Опустошает весь 25-зарядный магазин в режиме автоматического огня, нанося "+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+" урона за каждую попавшую пулю.<br/>[style.italicsGood(Бьет по всем врагам!)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может переключиться на автоматический режим и опустошить весь 25-зарядный магазин по [npc.her] врагам, нанося "+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+" урона за каждую попавшую пулю.<br/>[style.italicsGood(Бьет по всем врагам!)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 25 - 5 - Util.random.nextInt(6); // Hit with 15-20 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();
            
            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "rifle";
    		}
            
        	attackDesc.append(UtilText.parse(source,
        			"Переключив селектор огня на [npc.her] "+weaponName+" в полностью автоматический режим, [npc.name] [npc.verb(take)] прицеливается и [npc.verb(pull)] спусковой крючок, обрушивая оглушительный, смертоносный град пуль по "
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her] врагов")+"!"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull], пораженных [style.boldTerrible(\""+finalBullets+")] пуль"+(finalBullets==1?"":"s")+"!"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name] получил "+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+" урон!"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };

    public static AbstractCombatMove INKY_SUMMON = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "призвать Инки",
            100,
            1,
            CombatMoveType.ATTACK,
            DamageType.LUST,
            "statusEffects/inky_summon",
            Util.newArrayListOfValues(PresetColour.BASE_PURPLE),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.INKY_ATTACK, 6))) {
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_pen_inky")), "Доступно персонажам, у которых есть перо Инки.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return UtilText.parse(source, target,
            		"Выпустить Инки на [npc2.name]. Маленькая аркана-сущность будет с радостью наносить "+getFormattedDamage(DamageType.LUST, 15, null, false, false)+" урон каждый ход и уменьшать очки действия [npc2.her] на 1!");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            return UtilText.parse(source,
            		"Выпустить Инки на вашу цель. Маленькая аркана-сущность будет с радостью наносить "+getFormattedDamage(DamageType.LUST, 15, null, false, false)+" урон каждый ход и уменьшать их очки действия на 1!");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            
            return formatAttackOutcome(source, target,
            		"Трижды провернув колпачок [npc.her], [npc.name] [npc.verb(pull)] его, открывая нечто поистине особенное. С обнаженного пера капает сгусток фиолетовой жидкости, который стремительно разбухает и принимает форму маленького существа, похожего на осьминога. Это арканное создание, известное лишь как Инки, левитирует в воздух и, глядя на [npc2.name] глазами, полными обожания, издает радостный тихий стон.<br/><br/>Летит прямиком к [npc2.name], обвивает щупальцами [npc2.herHim] и под радостное хлюпанье начинает похотливо трахать и ласкать [npc2.herHim]. Очень быстро становится ясно, что любая попытка [npc2.namePos] оторвать это арканное создание от [npc2.herHim] обречена на провал. Каждый раз, когда [npc2.she] [npc2.verb(try)] схватить маленького осьминога, [npc2.her] [npc.hands] просто соскальзывают сквозь его жидкое тело, что вызывает милый булькающий смех у похотливого существа.<br/><br/>Хотя щупальца Инки одновременно и отвлекают, и возбуждают, [npc2.name] ничего не может с этим поделать, и поэтому [npc2.she] [npc2.verb(try)] игнорировать это, пока [npc2.she] [npc.verb(wait)] ждать, когда его арканная сила иссякнет...",
            		"Инки теперь уделяет [npc2.name] всё своё интимное внимание!",
            		null,
                	null);
        }
    };
}
