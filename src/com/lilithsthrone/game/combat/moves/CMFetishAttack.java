package com.lilithsthrone.game.combat.moves;

import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.4.2
 * @author Innoxia
 */
public class CMFetishAttack {
	
	private static int getFetishAttackBaseDamage(AbstractFetish associatedFetish, GameCharacter source, boolean isCrit) {
		int baseDamage = 3;
		FetishLevel fetishLevel = source.getFetishLevel(associatedFetish);
		baseDamage += fetishLevel.getBonusTeaseDamage();
        return baseDamage * (isCrit?3:1);
	}
	
	public static AbstractCombatMove TEASE_ANAL_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнилка для попатой",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_anal_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ANAL_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ANAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
            		+ "[npc.Name] [npc.verb(offer)] [npc2.name] использование [npc.her] задницы, нанося "+getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target))+" урон."
    				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
					(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(turn)] вокруг, демонстрируя [npc.her] [npc.ass+] перед [npc2.name], после чего отвешивает шлепок и [npc.moaning],  "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Хочешь подойти и сжать меня?)]"
									:"[npc.speech(Я чувствую такую пустоту! Моя задница буквально молит о твоем члене!)]"),
						"[npc.Name] [npc.verb(turn)] вокруг, сжимая и ощупывая [npc.her] [npc.ass+], пока [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Почему бы тебе не подойти и не попробовать?)]"
									:"[npc.speech(Моей заднице нужен член!)]"),
						"[npc.Name] [npc.verb(turn)] вокруг, обеими руками ощупывая и раздвигая [npc.her] ягодицы, пока [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Иди поиграй со мной!)]"
									:"[npc.speech(Пожалуйста, я хочу твой член в своей заднице!)]"),
						"[npc.Name] [npc.verb(turn)] вокруг, обеими руками раздвигая [npc.her] ягодицы, пока [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Ты же знаешь, что хочешь попробовать!)]"
									:"[npc.speech(Моей шлюшковатой попке <i>нужен</i> твой член!)]")))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
                	"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ANAL_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "анальная дразнилка",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_anal_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ANAL_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ANAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
            			+ "[npc.Name] [npc.verb(tell)] [npc2.name], которая [npc.sheIs] собирается использовать [npc2.her] задницу, нанося "+getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target))+" урон."
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], прежде чем [npc.her] опустит взгляд на [npc2.her] [npc2.ass+] и [npc.moaning], [npc.speech(Твоя задница так и просит хорошего траха!)]",
    						"[npc.Name] жадно [npc.verb(stare)] на [npc2.namePos] [npc2.ass+], [npc.moaning], [npc.speech(Я оттрахаю эту задницу так сильно!)]",
    						"Похотливо глядя на [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Я вдолблю эту сладкую попку прямо в землю!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
                	"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static AbstractCombatMove TEASE_VAGINAL_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "раззаживание киски",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_vaginal_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_VAGINAL_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_VAGINAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasVagina(), "Доступно персонажам, у которых есть влагалище и навык "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
            			+ "[npc.Name] [npc.verb(offer)] [npc2.name] использование [npc.her] киски, нанося "+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }
        
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый "
            				+ getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.hands] от [npc.her] [npc.legs] до [npc.moansVerb], [npc.speech(Моя киска ноет от желания почувствовать тебя!)]",
						"[npc.Name] [npc.verb(slip)] один [npc.hand] от [npc.her] [npc.legs] до [npc.moansVerb], [npc.speech(Иди трахни мою киску!)]",
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.hips+] немного и [npc.moansVerb], [npc.speech(Я уже вся промокла! Иди трахни мою маленькую киску!)]",
						"[npc.Name] [npc.verb(wink)] на [npc2.name] и [npc.moansVerb], [npc.speech(Моей шлюшьей киске <i>нужно</i> немного внимания!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
                	"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static AbstractCombatMove TEASE_VAGINAL_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "вагинальное дразнение",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_vaginal_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_VAGINAL_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_VAGINAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasVagina()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasVagina() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
		            		+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается использовать [npc2.her] киску, нанося "+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
	            			+ (!target.hasVagina()
            					?" [style.italicsMinorBad(Урон снижен до 1, так как у [npc2.name] нет влагалища!)]"
            					:(target.getFetishDesire(oppositeFetish).isNegative()
                    					?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
                            			:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен до 1, если у цели нет влагалища или она не любит "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasVagina() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(grin)] на [npc2.name], прежде чем опустить [npc.her] взгляд между [npc2.her] [npc2.legs] и [npc.moaning], [npc.speech(Твоя киска так и просит хорошего оттраха!)]",
						"[npc.Name] жадно [npc.verb(stare)] между [npc2.namePos] [npc2.legs], [npc.moaning], [npc.speech(Я оттрахаю эту киску так сильно!)]",
						"Похотливо глядя между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Я вдолблю эту сладкую киску прямо в землю!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static AbstractCombatMove TEASE_INCEST = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "инцестуозное дразнение",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_incest",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_INCEST;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_INCEST;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).isRelatedTo(source)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.isRelatedTo(source) || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            if(target.isRelatedTo(source)) {
	            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
	            			+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается заняться инцестом с [npc2.herHim], нанося "
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
	            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
            } else {
	            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
	            			+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.she] [npc.verb(love)] заниматься инцестом, нанося "
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон. [style.italicsMinorBad(Урон снижен до 1, так как [npc2.name] не является вашим родственником!)]");
            }
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен до 1, если цель не является вашим родственником или она не любит "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.isRelatedTo(source) || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            if(target.isRelatedTo(source)) {
    			String dialogue = "";
    			Set<Relationship> rel = source.getRelationshipsTo(target);
    			if(rel.contains(Relationship.Child)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"Позволь мне позаботиться о тебе, [npc.mommy]!",
    						"Давай же, [npc2.mommy]! Я просто хочу <i>по-настоящему</i> хорошо о тебе позаботиться!",
    						"[npc2.Mommy]! Я просто хочу показать тебе, как сильно я тебя люблю!");
    			} else if(rel.contains(Relationship.Parent)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"Пусть [npc.mommy] позаботится о тебе!",
    						"Не волнуйся, милая, [npc.mommy] позаботится о тебе как следует!",
    						"[npc.Mommy] просто хочет показать, как сильно [npc.she] тебя любит!");
    			} else if(rel.contains(Relationship.Sibling)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"Позволь своей [npc.sis] позаботиться о тебе!",
    						"Не бойся, [npc2.sis], я позабочусь о тебе как следует!",
    						"Ну же, [npc2.sis]! Я просто хочу показать, как сильно я тебя люблю!");
    			} else {
    				dialogue = UtilText.returnStringAtRandom(
    						"Пусть [npc.mommy] позаботится о тебе!",
    						"Не волнуйся, милая, [npc.mommy] позаботится о тебе как следует!",
    						"[npc.Mommy] просто хочет показать, как сильно [npc.she] тебя любит!");
    			}
            	
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc1.Name] [npc.verb(grin)] в [npc2.name], прежде чем мощно толкнуть [npc1.her] [npc1.hips] вперед и выкрикнуть, [npc1.speech("+dialogue+")]",
        						"Проводя [npc1.her] [npc1.hands] по [npc1.her] паху, [npc1.name] [npc.verb(call)] до [npc2.name], [npc1.speech("+dialogue+")]",
        						"[npc1.Name] [npc.verb(let)] с нетерпеливым стоном, пока [npc1.she] [npc.verb(thrust)] [npc1.her] [npc1.hips] на [npc2.name], [npc1.speech("+dialogue+")]"))+dealtDamage.getKey(),
                		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
                		(isCrit
                			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
                			:null),
                		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
            	
            	
            } else {
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc1.Name] [npc.verb(grin)] на [npc2.name], прежде чем резко толкнуть [npc1.her] [npc1.hips] вперед и выкрикнуть, [npc1.speech(Я такая грязная, что обожаю трахать своих родственников! Представь, что я сделаю с тобой!)]",
        						"Проводя [npc1.her] [npc1.hands] по [npc1.her] паху, [npc1.name] [npc.verb(call)] до [npc2.name], [npc1.speech(Жаль, что ты не мой [npc2.sister] или кто-то еще; нет ничего горячее, чем секс между братом и сестрой!)]",
        						"[npc1.Name] [npc.verb(let)] с нетерпеливым стоном, пока [npc1.she] [npc.verb(thrust)] [npc1.her] [npc1.hips] на [npc2.name], [npc1.speech(Жаль, что ты не мой родственник; я обожаю инцест!)]"))+dealtDamage.getKey(),
                		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
                		(isCrit
                			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
                			:null),
                		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
            }
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш и является твоим родственником.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish) && target.isRelatedTo(source);
        }
    };
    
    public static AbstractCombatMove TEASE_CUM_STUD = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнение кончиком-жеребцом",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cum",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_CUM_STUD;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_CUM_ADDICT;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] даст [npc2.herHim] попробовать [npc.her] сперму, нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], прежде чем резко толкнуть [npc.her] [npc.hips] и выкрикнуть, [npc.speech(Мне не терпится наполнить тебя моей [npc.cum]!)]",
    						"Проводя [npc.her] [npc.hands] по [npc.her] паху, [npc.name] [npc.verb(call)] до [npc2.name], [npc.speech(Спорим, тебе не терпится попробовать мою [npc.cum]!)]",
    						"[npc.Name] [npc.verb(let)] с нетерпеливым стоном, пока [npc.she] [npc.verb(thrust)] [npc.her] [npc.hips] на [npc2.name], [npc.speech(Не могу дождаться, когда наполню тебя своей [npc.cum]!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_CUM_ADDICT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "зависимая от спермы дразнилка",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cum_addict",
            Util.newArrayListOfValues(PresetColour.CLOTHING_WHITE),
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_CUM_ADDICT;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_CUM_STUD;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasPenis()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
    	            		+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.she] [npc.verb(want)] [npc2.her] спермы, нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (!target.hasPenisIgnoreDildo()
            						?" [style.italicsMinorBad(Урон снижен до 1, так как у [npc2.name] нет пениса!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен до 1, если у цели нет пениса или если она не любит "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(reveal)] [npc.her] похоть спермы, [npc.speech(Аах! Мне нужна сперма! Я целую вечность не ела!)]",
    						"Сначала издав голодный скулеж, [npc.name] [npc.verb(plead)], [npc.speech(Ммм! Мне нужна сперма!)]",
    						"[npc.Name] [npc.verb(let)] с жалким скулежом, пока [npc.she] [npc.verb(beg)], [npc.speech(Я так голодна! Мне нужна твоя сперма!)]",
    						"[npc.Name] [npc.verb(let)] с жалким скулежом, пока [npc.she] [npc.verb(beg)] в поисках трапезы, [npc.speech(Пожалуйста! Мне нужно немного спермы!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_PENIS_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "задрот-любитель членов",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cock_addict",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_PENIS_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_PENIS_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasPenis()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
    	            		+ "[npc.Name] [npc.verb(beg)] ради [npc2.namePos] члена, нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (!target.hasPenisIgnoreDildo()
            						?" [style.italicsMinorBad(Урон снижен до 1, так как у [npc2.name] нет пениса!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен до 1, если у цели нет пениса или если она не любит "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(stare)] между [npc2.namePos] [npc2.legs], пока [npc.she] [npc.moanVerb], [npc.speech(Скоро я попробую твой [npc2.cock] на вкус!)]",
						"[npc.Name] жадно [npc.verb(gaze)] между [npc2.namePos] [npc2.legs] и [npc.moanVerb], [npc.speech(Я хочу чувствовать, как твой член пульсирует внутри меня!)]",
						"[npc.Name] [npc.verb(grin)] на [npc2.name], облизывая [npc.her] [npc.lips+] и опуская [npc.her] взгляд на [npc2.namePos] пах, пока [npc.she] [npc.moanVerb], [npc.speech(Я позабочусь о твоем члене!)]"))
            			+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_PENIS_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнение кок-стада",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_dick_dealer",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_PENIS_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_PENIS_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasPenisIgnoreDildo(), "Доступно персонажам, у которых есть пенис и "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
	            			+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается дать [npc2.herHim] попробовать [npc.her] [npc.cock+], нанося "
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.a_hand] между [npc.her] [npc.legs], прежде чем схватить [npc.her] за пах и [npc.moaning] на [npc2.name], [npc.speech(Не могу дождаться, когда запихаю свой член в тебя!)]",
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.hips+] вперед и [npc.moanVerb] на [npc2.name], [npc.speech(Иди и попробуй мой член на вкус!)]",
						"[npc.Name] [npc.verb(grin)] на [npc2.name], пока [npc.she] [npc.moanVerb], [npc.speech(Тебе понравится ощущение моего члена!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LEG_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнилка щеголя",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_strutter",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_STRUTTER;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_LEG_LOVER;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasLegs(), "Доступно персонажам, у которых есть ноги и "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
            			+ "[npc.Name] [npc.verb(offer)] [npc2.name], используя [npc.her] ноги, нанося "+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }
        
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый "
            				+ getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.hands] между [npc.her] [npc.legs] и [npc.moansVerb], [npc.speech(Мои ножки так и просят твоих прикосновений!)]",
						"[npc.Name] [npc.verb(slip)] одну [npc.hand] вниз, чтобы ласкать [npc.her] [npc.legs] и [npc.moansVerb], [npc.speech(Иди и оттрахай мои бёдра!)]",
						"[npc.Name] [npc.verb(lift)] одну из [npc.her] [npc.legs+] и [npc.moansVerb], [npc.speech(Иди и оттрахай мои ноги и бёдра!)]",
						"[npc.Name] [npc.verb(wink)] на [npc2.name] и [npc.moansVerb], [npc.speech(Моим бёдрам <i>нужно</i> немного внимания!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
                	"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LEG_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнилка ногами",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_leg_lover",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_LEG_LOVER;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_STRUTTER;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasLegs() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
		            		+ "[npc.Name] [npc.verb(tell)] [npc2.name], которая [npc.sheIs] собирается использовать [npc2.her] [npc2.legs], нанося "+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
	            			+ (!target.hasLegs()
            					?" [style.italicsMinorBad(Урон снижен до 1, так как у [npc2.name] нет ног!)]"
            					:(target.getFetishDesire(oppositeFetish).isNegative()
                    					?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
                            			:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен до 1, если у цели нет ног или они не любят "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasLegs() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(grin)] на [npc2.name], прежде чем опустить [npc.her] взгляд на [npc2.her] [npc2.legs] и [npc.moaning], [npc.speech(Твои бёдра так и просятся, чтобы их хорошенько оттрахали!)]",
						"[npc.Name] жадно [npc.verb(stare)] на [npc2.namePos] [npc2.legs], [npc.moaning], [npc.speech(Я так сильно оттрахаю эти ножки!)]",
						"Похотливо глядя на [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Я впечатаю эти сладкие бёдра в землю!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
	public static AbstractCombatMove TEASE_FOOT_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "покорное дразнение ступнями",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_foot_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_FOOT_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_FOOT_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(beg)], чтобы [npc2.name] использовал(а) [npc2.her], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(gaze)] жадно вниз на [npc2.namePos] [npc2.feet], пока [npc.she] [npc.moanVerb], [npc.speech(Не могу дождаться, когда почувствую твои [npc2.feet] на себе!)]",
    						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip], пока [npc.she] [npc.verb(gaze)] вниз на [npc2.namePos] [npc2.feet]. [npc.speech(Позволь мне поклоняться твоим [npc2.feet]!)]",
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], облизывая [npc.her] [npc.lips+] и опуская [npc.her] взгляд на [npc2.namePos] [npc2.feet], пока [npc.she] [npc.moanVerb], [npc.speech(Я просто хочу поклоняться твоим [npc2.feet]!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_FOOT_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнить доминантной стопой",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_foot_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_FOOT_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_FOOT_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
	            			+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается использовать [npc.her] [npc.feet] на [npc2.herHim], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(lift)] одну из [npc.her] [npc.legs], прежде чем направить [npc.her] [npc.foot] на [npc2.name]. [npc.speech(Встань на колени и поцелуй мои [npc.feet]!)]",
    						"[npc.Name] [npc.verb(thrust)] одну из [npc.her] [npc.feet+] вперед и [npc.moanVerb] на [npc2.name], [npc.speech(Скоро ты будешь лизать мои пальцы ног!)]",
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], пока [npc.she] [npc.moanVerb], [npc.speech(Ползи сюда и поцелуй мои ноги!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
	public static AbstractCombatMove TEASE_ARMPIT_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнящая подмышечная шлюшка",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_armpit_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ARMPIT_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ARMPIT_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(beg)], чтобы [npc2.name] использовал(а) [npc2.her] подмышки, нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(lift)] [npc.her] [npc.arms] и [npc.moanVerb], [npc.speech(Не могу дождаться, когда ты начнешь использовать мои подмышки!)]",
    						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] как [npc.she] [npc.verb(lift)] [npc.her] [npc.arm] и [npc.verb(show)] с [npc.her] [npc.armpits+], [npc.speech(Тебе понравятся мои подмышки!)]",
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], облизывая [npc.her] [npc.lips+] и задирая [npc.her] [npc.arm], чтобы продемонстрировать [npc.her] [npc.armpit+] как [npc.she] [npc.moanVerb], [npc.speech(Я просто хочу, чтобы ты поклонялся моим подмышкам!)]")
            		)+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ARMPIT_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнящая любительница подмышек",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_armpit_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ARMPIT_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ARMPIT_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Критический)]: ":"")
	            			+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается использовать [npc.her] [npc.armpits] на [npc2.herHim], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], прежде чем опустить [npc.her] взгляд на [npc2.her] [npc2.arms] и [npc.moaning], [npc.speech(Твои подмышки так и просят, чтобы их хорошенько оттрахали!)]",
							"[npc.Name] жадно [npc.verb(stare)] на [npc2.namePos] [npc2.armpits], [npc.moaning], [npc.speech(Я собираюсь использовать твои подмышки!)]",
							"Похотливо глядя на [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Не могу дождаться, когда повеселюсь с твоими сексуальными подмышками!)]")
            		)+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ORAL_RECEIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "оральный дразнинг",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_oral_receiving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ORAL_RECEIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ORAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
                    		+ "[npc.Name] [npc.verb(beg)], чтобы [npc2.name] совершила оральный секс над [npc.herHim], нанося "
                    			+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] на [npc2.name], глядя на [npc2.her] [npc2.lips+] как на [npc.she] [npc.moansVerb], [npc.speech(Не терпится пустить твои [npc2.lips] в дело!)]",
    						"[npc.Name] жадно [npc.verb(stare)] в [npc2.namePos] [npc2.lips+], [npc.moaning], [npc.speech(Твой язык должен быть между моих [npc.legs]!)]",
    						"Похотливо глядя на [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Я собираюсь с пользой использовать твой [npc2.lips]!)]"))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_ORAL_GIVING = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнить любителя орального секса",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_oral_giving",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_ORAL_GIVING;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_ORAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
    	            		+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается отсосать [npc2.herHim], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(open)] [npc.her] рот, высунув [npc.her] [npc.tongue] и многозначительно поманив одним из [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Знаешь, я довольно искусно владею языком!)]"
    									:"[npc.speech(Хочешь узнать, как глубоко я смогу его принять?)]"),
    						"[npc.Name] [npc.verb(open)] [npc.her] рот, высунув [npc.her] [npc.tongue] и многозначительно поманив одним из [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Тебе бы очень понравилось ощущение моего языка!)]"
    									:"[npc.speech(Хочешь засунуть свой член мне в глотку?)]"),
    						"[npc.Name] [npc.verb(open)] [npc.her] рот, высунув [npc.her] [npc.tongue] и многозначительно поманив одним из [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Я лучше всех умею ласкать девчонок языком! Хочешь посмотреть?)]"
    									:"[npc.speech(Я лучшая в минете! Хочешь посмотреть?)]"),
    						"[npc.Name] [npc.verb(open)] [npc.her] рот, высунув [npc.her] [npc.tongue] и многозначительно поманив одним из [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Ты же знаешь, что хочешь почувствовать мой язык!)]"
    									:"[npc.speech(Ты же знаешь, что хочешь, чтобы я отсосала твой член!)]")))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_BREASTS_OTHERS = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнить любителя груди",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_breasts_others",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_BREASTS_OTHERS;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_BREASTS_SELF;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasBreasts()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasBreasts() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
                    		+ "[npc.Name] [npc.verb(beg)], чтобы прочувствовать [npc2.namePos] [npc2.breasts+], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (!target.hasBreasts()
            						?" [style.italicsMinorBad(Урон снижен до 1, так как у [npc2.name] нет груди!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урона. [style.italicsMinorBad(Урон снижен до 1, если у цели нет груди или если ей не нравится "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(!target.hasBreasts() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            String attackText = "";
            if(target.hasBreasts()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(grin)] на [npc2.name], пристально глядя на [npc2.her] [npc2.breasts+], пока [npc.she] [npc.moansVerb], [npc.speech(Мне не терпится добраться своими [npc.hands] до твоих [npc2.breasts]!)]",
						"[npc.Name] жадно [npc.verb(stare)] у [npc2.namePos] [npc2.breasts+], [npc.moaning], [npc.speech(Я повеселюсь, играя с ними!)]",
						"Похотливо глядя на [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] из [npc.a_moan+], [npc.speech(Я повеселюсь с твоими [npc2.breasts+]!)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"Глядя на грудь [npc2.namePos], [npc.name] [npc.verb(let)] раздраженно фыркнув, [npc.speech(Вот если бы у тебя была нормальная пара сисек, чтобы я могла ими заняться!)]",
							"[npc.Name] [npc.verb(pout)] на [npc2.namePos] [npc2.breasts+], после чего заныла: [npc.speech(О-о-о... вот если бы у тебя были классные сиськи, чтобы я могла с ними поиграть!)]")));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_BREASTS = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнить грудью",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_breasts_self",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_BREASTS_SELF;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_BREASTS_OTHERS;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasBreasts(), "Доступно персонажам, у которых есть грудь и навык "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(tease)] [npc2.name], обещая пощупать [npc.her] грудь, нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"Сжимая [npc.her] [npc.breasts+] вместе, [npc.name] [npc.verb(lean)] вперед и [npc.verb(wink)] на [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Давай поиграем вместе!)]"
    									:"[npc.speech(Ну же, я позволю тебе их сжать!)]"),
    						"Сжимая [npc.her] [npc.breasts+] вместе, [npc.name] [npc.verb(lean)] вперед и [npc.verb(wink)] на [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Хочешь повеселиться?)]"
    									:"[npc.speech(Да ладно тебе, ты же сама хочешь их пощупать!)]"),
    						"Кокетливо проводя [npc.her] [npc.hands] по [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] губы и капризно глядя на [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Разве ты не хочешь прийти и поиграть?)]"
    									:"[npc.speech(Бьюсь об заклад, ты просто жаждешь прикосновения!)]"),
    						"Кокетливо проводя [npc.her] [npc.hands] по [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] губы и капризно глядя на [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Да ладно тебе! Давай повеселимся!)]"
    									:"[npc.speech(~Аах!~ Мои соски такие твердые!)]")))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LACTATION_OTHERS = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнение любительницы молока",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_lactation_others",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_LACTATION_OTHERS;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_LACTATION_SELF;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || this.getPreferredTarget(source, enemies, allies).getBreastRawMilkStorageValue()==0) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if((target.getBreastRawMilkStorageValue()==0 && (!target.hasBreastsCrotch() || target.getBreastCrotchRawMilkStorageValue()==0))
            	|| target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(beg)], чтобы подоить [npc2.namePos] [npc2.breasts+], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getBreastRawMilkStorageValue()==0 && (!target.hasBreastsCrotch() || target.getBreastCrotchRawMilkStorageValue()==0)
            						?" [style.italicsMinorBad(Урон снижен до 1, так как [npc2.name] не кормит грудью!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
                            				:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижен до 1, если цель не кормит грудью или если ей не нравится "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));
            }
            
            String attackText = "";
            if(target.hasBreasts()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grin)] на [npc2.name], вожделенно глядя на [npc2.her] [npc2.breasts+], пока [npc.she] [npc.moansVerb], [npc.speech(Сейчас я с удовольствием подою твои сиськи!)]",
							"[npc.Name] жадно [npc.verb(stare)] на [npc2.namePos] [npc2.breasts+], [npc.moaning], [npc.speech(Не терпится хорошенько тебя подоить!)]",
							"Вожделенно глядя на [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Я с удовольствием подою твои [npc2.breasts+]!)]")));
				
            } else if(target.hasBreastsCrotch() && target.getBreastCrotchRawMilkStorageValue()>0) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grin)] на [npc2.name], вожделенно глядя на [npc2.her] [npc2.crotchBoobs+], пока [npc.she] [npc.moansVerb], [npc.speech(Сейчас я с удовольствием подою твои вымени!)]",
							"[npc.Name] жадно [npc.verb(stare)] на [npc2.namePos] [npc2.crotchBoobs+], [npc.moaning], [npc.speech(Не терпится хорошенько тебя подоить!)]",
							"Вожделенно глядя на [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], [npc.speech(Я с удовольствием подою твои [npc2.breasts+]!)]")));
            	
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"Глядя на грудь [npc2.namePos], [npc.name] [npc.verb(let)] раздраженно фыркнув, [npc.speech(Жаль, у тебя нет нормальной пары сисек, чтобы я могла их подоить!)]",
							"[npc.Name] [npc.verb(pout)] на [npc2.namePos] [npc2.breasts+], после чего заныв, [npc.speech(О-о-о... Жаль, у тебя нет классных сисек, чтобы я могла их подоить!)]")));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_LACTATION = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "лактационное дразнение",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_lactation_self",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_LACTATION_SELF;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_LACTATION_OTHERS;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if((source.getBreastRawMilkStorageValue()==0 && (!source.hasBreastsCrotch() || source.getBreastCrotchRawMilkStorageValue()==0))
            		|| target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && (source.hasBreasts() || source.hasBreastsCrotch()),
            		"Доступно персонажам, у которых есть грудь и у которых также есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(tell)] [npc2.name], так что [npc.sheIs] собирается заставить [npc2.herHim] доить [npc.her], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()
            						?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]"
            						:(source.getBreastRawMilkStorageValue()==0 && (!source.hasBreastsCrotch() || source.getBreastCrotchRawMilkStorageValue()==0)
            							?" [style.italicsMinorBad(Урон снижен до 1, так как [npc.nameIsFull] не кормит грудью!)]"
            							:"")));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш, или если [npc.nameIsFull] не кормит грудью.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            if(source.hasBreasts()) {
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"Сжимая [npc.her] [npc.breasts+] вместе, [npc.name] [npc.verb(pout)] на [npc2.name], [npc.speech(Мои груди так болят! Пожалуйста, мне нужно доение!)]",
        						"Сжимая [npc.her] [npc.breasts+] вместе, [npc.name] [npc.verb(let)] с тихим стоном, пока [npc.she] [npc.verb(bat)] [npc.her] веками на [npc2.name], [npc.speech(Я так полна молока! Мне нужна помощь!)]",
        						"Проводя [npc.her] [npc.hands] соблазнительно по [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] губу, прежде чем надуться на [npc2.name], [npc.speech(Пожалуйста, мне нужно доение!)]",
        						"Проводя [npc.her] [npc.hands] соблазнительно по [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] губу, прежде чем надуться на [npc2.name], [npc.speech(~Аах!~ Я так полна молока! Пожалуйста, мне нужна твоя помощь!)]"))+dealtDamage.getKey(),
                		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
                		(isCrit
                			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
                			:null),
                		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
            	
            } else { // Crotch boobs:
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"Представляя [npc.her] [npc.crotchBoobs+] для [npc2.name], [npc.name] [npc.verb(pout)] и [npc.verb(whine)], [npc.speech(Мои соски так болят! Пожалуйста, мне нужно доение!)]",
        						"Представляя [npc.her] [npc.crotchBoobs+] для [npc2.name], [npc.name] [npc.verb(bat)] [npc.her] веками и [npc.verb(whine)], [npc.speech(Я так полна молока! Мне нужна помощь!)]",
        						(source.isTaur()
        							?null
        							:"Проводя [npc.her] [npc.hands] соблазнительно по [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] губу, прежде чем надуться на [npc2.name], [npc.speech(Пожалуйста, мне нужно доение!)]"),
        						(source.isTaur()
        							?null
        							:"Проводя [npc.her] [npc.hands] соблазнительно по [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] губу, прежде чем надуться на [npc2.name], [npc.speech(~Аах!~ Я так полна молока! Пожалуйста, мне нужна твоя помощь!)]")))
                				+dealtDamage.getKey(),
                		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
                		(isCrit
                			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
                			:null),
                		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
            	
            }
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_FERTILITY = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнение фертильности",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_pregnancy",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_PREGNANCY;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_IMPREGNATION;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasVagina(), "Доступно персонажам, у которых есть вагина и "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
                    		+ "[npc.Name] [npc.verb(tell)] [npc2.name] насколько фертильна [npc.sheIsFull], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            String attackText = "";
            if(source.isVisiblyPregnant()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							(source.isTaur()
								?"Повернувшись на бок, чтобы продемонстрировать раздувшийся, беременный живот в теле [npc.her] [npc.legRace],"
								:"Проводя [npc.her] [npc.hands] по [npc.her] беременному животу,")
							+" [npc.name] [npc.verb(wink)] на [npc2.name] и [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Позволь мне рассказать тебе лучшие способы забеременеть!)]"
									:"[npc.speech(Всегда хотел трахнуть беременную [npc.girl]?)]"),
						"[npc.Name] [npc.verb(push)] по [npc.her] беременному животу и [npc.verb(giggle)] на [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Я расскажу тебе всё о том, как сама забеременела!)]"
									:"[npc.speech(Трахать беременную [npc.girls] — это лучшее, что может быть! Давай, я тебе покажу!)]"),
						(source.isTaur()
								?"Демонстрируя [npc.her] нижнюю часть тела [npc.legRace] перед [npc2.name], [npc.name] [npc.verb(show)] [npc.her] беременного живота и [npc.moansVerb], "
								:"Скользя [npc.her] по [npc.hands] поверх [npc.her] беременного живота, [npc.name] [npc.verb(pout)] на [npc2.name] и [npc.verb(giggle)], ")
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Подойди, потрогай!)]"
									:"[npc.speech(Беременные [npc.girls] — самые лучшие для траха!)]"),
						"Принимая позы, чтобы привлечь внимание к [npc.her] беременному животу, [npc.name] [npc.verb(bite)] [npc.her] губами к [npc2.name] и [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Ну же, почему бы нам не повеселиться?!)]"
									:"[npc.speech(Хочешь узнать, как охрененно трахать беременную [npc.girl]?)]"))));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						(source.isTaur()
							?"Выставляя напоказ, насколько плоский живот у [npc.her],"
							:"Потирая [npc.her] [npc.hands] по плоскому животу [npc.her],")
							+" [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] на [npc2.name] и [npc.verb(whine)], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Ох... Как же сильно я хочу забеременеть!)]"
									:"[npc.speech(Мне так нужно забеременеть! Давай, заполни меня своим спермой уже сейчас!)]"),
						(source.isTaur()
							?"Привлекая внимание к плоскому животу [npc.her],"
							:"Скользя [npc.her] [npc.hands] по плоскому животу [npc.her],")
							+ " [npc.name] [npc.verb(pout)] на [npc2.name] и [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Ох... Как же сильно я хочу забеременеть...)]"
									:"[npc.speech(Давай, засели мой живот детками уже сейчас!)]"),
						(source.isTaur()
							?"Выставляя напоказ, насколько плоский живот у [npc.her],"
							:"Скользя [npc.her] [npc.hands] по плоскому животу [npc.her],")
							+" [npc.name] [npc.verb(pout)] на [npc2.name] и [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Как же сильно я хочу забеременеть...)]"
									:"[npc.speech(Наполни мою утробу своей вкусной спермой! Я уже хочу забеременеть!)]"),
						(source.isTaur()
								?"Привлекая внимание к плоскому животу [npc.her],"
								:"Потирая [npc.her] [npc.hands] по плоскому животу [npc.her],")
							+" [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] на [npc2.name] и [npc.verb(whine)], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Как же сильно я хочу забеременеть...)]"
									:"[npc.speech(Ну же, оплодотвори меня! Моя утроба ждет твоего семени!)]"))));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_VIRILITY = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "дразнение мужественностью",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_impregnation",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_IMPREGNATION;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_PREGNANCY;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasPenisIgnoreDildo(), "Доступно персонажам, у которых есть пенис и у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
                    		+ "[npc.Name] [npc.verb(tell)] [npc2.name], когда [npc.sheIs] собирается оплодотворить [npc2.herHim], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            String attackText = "";
			if(target.isVisiblyPregnant()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"Протянув руку, чтобы схватить [npc.her] за пах, [npc.name] [npc.verb(wink)] на [npc2.name], [npc.speech(Жаль, что ты уже беременна... Но это не помешает мне наполнить твою киску моей [npc.cum+]!)]",
						"Проводя [npc.her] [npc.hands] по [npc.her] паху, [npc.name] [npc.verb(wink)] на [npc2.name], [npc.speech(Может, ты уже и беременна, но это не помешает мне устроить тебе отличный кремпи!)]",
						"Спуская [npc.her] [npc.hands], чтобы привлечь внимание к [npc.her] паху, [npc.name] [npc.verb(grin)] на [npc2.name], [npc.speech(Не думай, что беременность помешает мне залить твою щель моей [npc.cum+]!)]",
						"Протянув руку, чтобы схватить [npc.her] за пах, [npc.name] [npc.verb(grin)] на [npc2.name], [npc.speech(Жаль, что ты уже беременна... Но я все равно наполню тебя своей [npc.cum+]!)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"Протянув руку, чтобы схватить [npc.her] за пах, [npc.name] [npc.verb(wink)] на [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(Хочешь залететь? Моя сперма так и молит о том, чтобы наполнить твою утробу!)]"
									:"[npc.speech(Может, мне стоит дать тебе киску, а потом оплодотворить тебя!)]"),
						"Спуская [npc.her] [npc.hands] по [npc.her] паху, [npc.name] [npc.verb(wink)] на [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(Мое семя такое мощное, что ты забеременеешь с первого же траха!)]"
									:"[npc.speech(Если бы у тебя была киска, я бы тебя как следует осеменил!)]"),
						"Спуская [npc.her] [npc.hands] ниже, чтобы привлечь внимание к [npc.her] паху, [npc.name] [npc.verb(grin)] на [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(Мое семя настолько сильное, что ты залетаешь с первого же секса!)]"
									:"[npc.speech(Возможно, я подарю тебе тугую киску, а потом оплодотворю тебя с первого же траха!)]"),
						"Протягивая руку, чтобы схватить за [npc.her] пах, [npc.name] [npc.verb(grin)] на [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(Я трахну тебя так, что ты забеременеешь!)]"
									:"[npc.speech(Тебе нужна хорошая тугая киска, чтобы я мог трахнуть тебя до беременности!)]"))));
			}
            
            return formatAttackOutcome(source, target,
            		attackText+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_DOMINANT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "доминантный дразнилка",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_dominant",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_DOMINANT;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_SUBMISSIVE;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
            				+ "[npc.Name] [npc.verb(tell)] [npc2.name], что [npc.sheIs] собирается доминировать над [npc2.herHim], нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append(UtilText.returnStringAtRandom(
						"Похотливо ухмыляясь [npc2.name], [npc.name] [npc.verb(growl)], ",
						"Со зловещей ухмылкой на лице [npc.her], [npc.name] [npc.verb(growl)] на [npc2.name], ",
						"Внушительно глядя на [npc2.name], [npc.name] [npc.verb(growl)], ",
						"[npc.Name] [npc.verb(let)] угрожающее рычание, пока [npc.she] похотливо [npc.verb(stare)] на [npc2.name], "));
        	 sb.append(UtilText.returnStringAtRandom(
				"[npc.speech(Я сейчас так жестко тебя оттрахаю, сучка!)]",
				"[npc.speech(Ты познаешь лучший трах в своей жизни!)]",
				"[npc.speech(Ты будешь моей послушной маленькой шлюшкой!)]",
				"[npc.speech(Я сделаю тебя своей сучкой!)]",
				"[npc.speech(Скоро ты станешь моей сучкой!)]",
				"[npc.speech(К тому времени, как я с тобой закончу, ты превратишься в скулящую маленькую сучку!)]",
				"[npc.speech(Я оттрахаю тебя так сильно, что ты будешь визжать, как маленькая сучка!)]"));
            sb.append(dealtDamage.getKey());
            
            return formatAttackOutcome(source, target,
            		sb.toString(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static AbstractCombatMove TEASE_SUBMISSIVE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "покорная дразнилка",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_submissive",
            false,
            true,
            false,
			null) {

    	private AbstractFetish associatedFetish = Fetish.FETISH_SUBMISSIVE;
    	private AbstractFetish oppositeFetish = Fetish.FETISH_DOMINANT;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return getFetishAttackBaseDamage(associatedFetish, source, isCrit);
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Доступно персонажам, у которых есть "+associatedFetish.getName(source)+" фетиш.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            			(isCrit?"[style.colourExcellent(Критический)]: ":"")
                    		+ "[npc.Name] [npc.verb(tell)] [npc2.name], стараясь [npc.she] [npc.verb(love)] подчинение, нанося "
            					+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " урон."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Урон снижается до 1, если [npc2.name] [npc2.verb(dislike)] этот "+oppositeFetish.getName(source)+" фетиш!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] может использовать [npc.her] "+associatedFetish.getName(source)+" фетиш, чтобы дразнить [npc.her] цель, нанося базовый " + getFormattedDamage(damageType, getBaseDamage(source, false), null, false, false) + " урон. [style.italicsMinorBad(Урон снижается до 1, если цель не любит этот "+oppositeFetish.getName(null)+" фетиш.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> dealtDamage = new Value<>("", 0);
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = new Value<>("", 1);
            	isCrit = false;
            } else {
            	dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, isCrit));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(tilt)] [npc.her], склонив голову в знак покорности, а затем подняв на тебя большие невинные глаза, [npc.speech(Я буду хорошей [npc.girl]! Обещаю!)]",
    						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] и [npc.verb(shuffle)] [npc.her] [npc.feet], стараясь как [npc.she] [npc.do] [npc.her] выглядеть как можно слабее, [npc.speech(Я сделаю всё, что ты захочешь!)]",
    						"[npc.Name] [npc.verb(shuffle)] [npc.her] [npc.feet] и [npc.verb(make)] [npc.herself], стараясь казаться как можно меньше, прежде чем с похотью взглянуть на [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Пожалуйста! Обращайся со мной как со своей маленькой сучкой!)]"
    									:"[npc.speech(Пожалуйста! Сделай меня своей маленькой игрушкой для секса!)]"),
    						"[npc.Name] [npc.verb(put)] на [npc.her] с самым невинным видом, пока [npc.she] [npc.verb(gaze)] похотливо взирает на [npc2.namePos] [npc2.eyes], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Я буду твоей маленькой рабыней!)]"
    									:"[npc.speech(Я буду послушной маленькой залупой! Обещаю!)]")))+dealtDamage.getKey(),
            		"[npc2.Name] получил " + getFormattedDamage(damageType, dealtDamage.getValue(), target, true, maxLust) + " урон!",
            		(isCrit
            			?"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!"
            			:null),
            		"[npc2.NameIsFull] невероятно возбуждена, и [npc2.verb(take)] тройной урон!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"У цели есть")+""+oppositeFetish.getName(target)+" фетиш.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
}
