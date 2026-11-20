package com.lilithsthrone.game.character.attributes;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.97
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum LustLevel {

	ZERO_COLD("cold", 0, 10, 0.5f, PresetColour.LUST_STAGE_ZERO, SexPace.SUB_RESISTING, SexPace.DOM_GENTLE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_0;
		}
	},

	ONE_HORNY("horny", 10, 25, 0.75f, PresetColour.LUST_STAGE_ONE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_1;
		}
	},

	TWO_AMOROUS("sensual", 25, 50, 1f, PresetColour.LUST_STAGE_TWO, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_2;
		}
	},

	THREE_LUSTFUL("amorous", 50, 75, 1.25f, PresetColour.LUST_STAGE_THREE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_3;
		}
	},

	FOUR_IMPASSIONED("lustful", 75, 90, 1.5f, PresetColour.LUST_STAGE_FOUR, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_4;
		}
	},
	
	FIVE_BURNING("impassioned", 90, 100, 1.5f, PresetColour.LUST_STAGE_FIVE, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_5;
		}
	};
	
	
	private String name;
	private int minimumValue, maximumValue;
	private float arousalModifier;
	private Colour colour;
	private SexPace sexPaceSubmissive;
	private SexPace sexPaceDominant;

	private LustLevel(String name, int minimumValue, int maximumValue, float arousalModifier, Colour colour, SexPace sexPaceSubmissive, SexPace sexPaceDominant) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.arousalModifier = arousalModifier;
		this.colour = colour;
		this.sexPaceSubmissive = sexPaceSubmissive;
		this.sexPaceDominant = sexPaceDominant;
	}

	public abstract AbstractStatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}
	
	public float getArousalModifier() {
		return arousalModifier;
	}

	public Colour getColour() {
		return colour;
	}

	public static LustLevel getLustLevelFromValue(float value){
		if(value<0) {
			return ZERO_COLD;
		}
		for(LustLevel al : LustLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_BURNING;
	}


	public SexPace getSexPaceSubmissive() {
		return sexPaceSubmissive;
	}

	public SexPace getSexPaceDominant() {
		return sexPaceDominant;
	}
	
	public boolean isResistingFromRapePlay(GameCharacter character) {
		return !Main.sex.isDom(character)
				&& (character.hasFetish(Fetish.FETISH_NON_CON_SUB) && !Main.sex.isCharacterBannedFromRapePlay(character))
				&& !((character instanceof NPC) && ((NPC)character).hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer) && !character.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(character.getId()))
				&& getSexPaceSubmissive()!=SexPace.SUB_RESISTING;
	}
	
	public SexPace getSexPace(boolean consensual, GameCharacter character) {
		SexPace pace;
		if(Main.sex.isDom(character)) {
			pace = getSexPaceDominant();
			
			if((character.hasFetish(Fetish.FETISH_SUBMISSIVE) && !character.hasFetish(Fetish.FETISH_SADIST) && !character.hasFetish(Fetish.FETISH_DOMINANT))
					|| character.getFetishDesire(Fetish.FETISH_SADIST) == FetishDesire.ZERO_HATE) {
				pace = SexPace.DOM_GENTLE;
				
			} else if(character.getFetishDesire(Fetish.FETISH_SADIST).isNegative()) {
				pace = SexPace.DOM_NORMAL;
				
			} else if(character.hasFetish(Fetish.FETISH_SADIST)) {
				return SexPace.DOM_ROUGH;
				
			} else { // Hate sex:
				for(GameCharacter target : Main.sex.getAllParticipants()) {
					if(!Main.sex.isDom(target) && character.getAffection(target)<AffectionLevel.NEGATIVE_TWO_DISLIKE.getMaximumValue()) {
						return SexPace.DOM_ROUGH;
					}
				}
			}
			
		} else {
			pace = getSexPaceSubmissive();
			if((character.hasFetish(Fetish.FETISH_NON_CON_SUB) && !Main.sex.isCharacterBannedFromRapePlay(character))
					|| ((character instanceof NPC) && ((NPC)character).hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer) && !character.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(character.getId()))) {
				pace = SexPace.SUB_RESISTING;
			}
		}
		
		if(pace==SexPace.SUB_RESISTING && !Main.getProperties().hasValue(PropertyValue.nonConContent)) {
			pace = SexPace.SUB_NORMAL;
		}
		
		if(pace==SexPace.DOM_ROUGH
				&& ((!character.hasFetish(Fetish.FETISH_DOMINANT) && !character.hasFetish(Fetish.FETISH_SADIST) && !character.hasFetish(Fetish.FETISH_NON_CON_DOM))
						|| (character.getFetishDesire(Fetish.FETISH_SADIST).isNegative()))) {
			pace = SexPace.DOM_NORMAL;
		}
		
		return pace;
	}
	
	public List<String> getStatusEffectModifierDescription(boolean consensual, GameCharacter character) {
		List<String> modifiersList = new ArrayList<>();

		Colour levelColour = LustLevel.getLustLevelFromValue(character.getRestingLust()).getColour();
		modifiersList.add("Текущая похоть: <b style='color:"+levelColour.toWebHexString()+";'>"+character.getRestingLust()+"</b>");
		
		if(Main.game.isInSex()) {
			switch(this.getSexPace(consensual, character)) {
				case DOM_GENTLE:
					if(!character.isPlayer()) {
						modifiersList.add("Предпочитает <b style='color: " + SexPace.DOM_GENTLE.getColour().toWebHexString() + "'>нежный</b> темп");
					}
					break;
				case DOM_NORMAL:
					if(!character.isPlayer()) {
						modifiersList.add("Предпочитает <b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>обычный</b> темп");
					}
					break;
				case DOM_ROUGH:
					if(!character.isPlayer()) {
						if(!character.hasFetish(Fetish.FETISH_DOMINANT) && !character.hasFetish(Fetish.FETISH_SADIST)) {
							modifiersList.add("Предпочитает <b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>обычный</b> темп");
							modifiersList.add("(<b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>Грубый</b> темп требует "+Fetish.FETISH_DOMINANT.getName(character)
													+", "+Fetish.FETISH_NON_CON_DOM.getName(character)+", или "+Fetish.FETISH_SADIST.getName(character)+" фетиш)");
						} else {
							modifiersList.add("Предпочитает <b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>грубый</b> темп");
						}
					}
					break;
				case SUB_EAGER:
					if(!character.isPlayer()) {
						modifiersList.add("Предпочитает <b style='color: " + SexPace.SUB_EAGER.getColour().toWebHexString() + "'>жадный</b> темп");
					}
					break;
				case SUB_NORMAL:
					if(!character.isPlayer()) {
						modifiersList.add("Предпочитает <b style='color: " + SexPace.SUB_NORMAL.getColour().toWebHexString() + "'>обычный</b> темп");
					}
					break;
				case SUB_RESISTING:
					if(!character.isPlayer()) {
						if(character.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
							modifiersList.add("Всегда предпочитает <b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>сопротивляющийся</b> темп из-за "+Fetish.FETISH_NON_CON_SUB.getName(character)+" fetish");
						} else {
							modifiersList.add("Предпочитает <b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>сопротивляющийся</b> темп");
						}
					}
					break;
			}
		
			int gains = (int)(this.getArousalModifier()*100);
			modifiersList.add((gains>=100?"[style.boldArousal("+gains+"%)]":"[style.boldBad("+gains+"%)]")+" прироста возбуждения");
			
		}
		
		return modifiersList;
	}
	
	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
		StringBuilder sb = new StringBuilder();

		if(Main.game.isInSex()) {
			switch(this.getSexPace(consensual, character)) {
				case DOM_GENTLE:
					switch(this) {
						case ZERO_COLD:
							sb.append("[npc.NameIsFull] сейчас не особо хочет заниматься сексом, и в результате, [npc.she] [npc.verb(want)] предпочитает действовать медленно и нежно.");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull] сейчас довольно возбуждена, но [npc.is] все еще контролирует свою [npc.her] похоть, что позволяет [npc.herHim] сохранять хладнокровие и сосредоточиться на медленном и нежном темпе.");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull] сейчас чувствует нешуточное желание, но [npc.is] все еще способна сосредоточиться на медленном и нежном темпе.");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull] сейчас горит от похоти, но [npc.is] всё ещё в состоянии сосредоточиться на том, чтобы всё было медленно и нежно.");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull] буквально сгорает от похоти, но [npc.is] всё ещё в состоянии сосредоточиться на том, чтобы всё было медленно и нежно.");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull] полностью захлестнула похоть, но, каким-то образом, [npc.is] всё ещё в состоянии сосредоточиться на том, чтобы всё было медленно и нежно.");
							break;
					}
					break;
				case DOM_NORMAL:
				case SUB_NORMAL:
					switch(this) {
						case ZERO_COLD:
							sb.append("Хотя [npc.nameIsFull] сейчас не особо и хочется секса, [npc.she] [npc.is] всё ещё может заставить [npc.herself] вести себя так, будто [npc.sheIs] дико возбуждена.");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull] сейчас довольно возбуждена, и [npc.is] более чем не прочь заняться сексом в данный момент.");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull] сейчас чувствует нешуточное желание, и [npc.is] очень рада, что прямо сейчас занимается сексом.");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull] сейчас горит от похоти, и [npc.is] в экстазе от того, что прямо сейчас занимается сексом.");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull] буквально сгорает от похоти, но [npc.is] всё ещё в состоянии не дать [npc.herself] слишком сильно увлечься процессом.");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull] полностью захлестнула похоть, но, каким-то образом, [npc.is] всё ещё в состоянии не дать [npc.herself] слишком сильно увлечься процессом.");
							break;
					}
					break;
				case DOM_ROUGH:
				case SUB_EAGER:
					switch(this) {
						case ZERO_COLD:
							sb.append("Хотя [npc.nameIsFull] сейчас не особо и хочется секса, [npc.she] [npc.is] всё ещё может заставить [npc.herself] вести себя так, будто [npc.sheIs] чрезвычайно возбуждена.");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull] сейчас довольно возбуждена, и [npc.is] более чем не прочь заняться сексом в данный момент.");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull] сейчас чувствует нешуточное желание, и [npc.is] очень рада, что прямо сейчас занимается сексом.");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull] сейчас горит от похоти, и [npc.is] в экстазе от того, что прямо сейчас занимается сексом.");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull] полностью сгорает от похоти, и [npc.is] начинает по-настоящему терять голову.");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull] полностью захлестнута похотью, и [npc.has] окончательно теряет [npc.herself] в удовольствии от секса.");
							break;
					}
					break;
				case SUB_RESISTING:
					switch(this) {
						case ZERO_COLD:
							sb.append("[npc.NameIsFull] сейчас совсем не заинтересована в сексе и [npc.is] отчаянно пытается сопротивляться тому, что сейчас происходит с [npc.herHim].");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull] сейчас довольно возбуждена, но, несмотря на это, [npc.sheIs] совсем не довольна текущей ситуацией [npc.her] и [npc.is] отчаянно сопротивляется сексу.");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull] сейчас чувствует нешуточное желание, но, несмотря на это, [npc.sheIs] совсем не довольна текущей ситуацией [npc.her] и [npc.is] отчаянно сопротивляется сексу.");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull] сейчас горит от похоти, но, несмотря на это, [npc.sheIs] совсем не довольна текущей ситуацией [npc.her] и [npc.is] отчаянно сопротивляется сексу.");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull] полностью сгорает от похоти, но, несмотря на это, [npc.sheIs] совсем не довольна текущей ситуацией [npc.her] и [npc.is] отчаянно сопротивляется сексу.");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull] полностью захлестнута похотью, но, несмотря на это, [npc.sheIs] совсем не довольна текущей ситуацией [npc.her] и [npc.is] отчаянно сопротивляется сексу.");
							break;
					}
					break;
			}
			
		} else {
			switch(this) {
				case ZERO_COLD:
					sb.append("[npc.NameIsFull] сейчас не особо-то и хочет заниматься сексом.");
					break;
				case ONE_HORNY:
					sb.append("[npc.NameIsFull] сейчас довольно возбуждена, но [npc.is] всё ещё контролирует [npc.her] похоть.");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Осторожные нападающие</b><br>Похоже, ты начинаешь привлекать неприятности.");
					break;
				case TWO_AMOROUS:
					sb.append("[npc.NameIsFull] сейчас чувствует недюжинную похоть, и [npc.is] постоянно думает о сексе.");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Осторожные нападающие</b><br>Ты всё отчетливее ощущаешь на себе беспокойные взгляды.");
					break;
				case THREE_LUSTFUL:
					sb.append("[npc.NameIsFull] сейчас горит от похоти, и [npc.is] с трудом может думать о чем-то, кроме секса.");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Осторожные нападающие</b><br>Твою пропитанную похотью ауру уже невозможно игнорировать.");
					break;
				case FOUR_IMPASSIONED:
					sb.append("[npc.NameIsFull] полностью сгорает от похоти, и [npc.is] с трудом может думать о чем-то, кроме секса.");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Осторожные нападающие</b><br>Почти каждый прохожий оглядывается на тебя с вожделением.");
					break;
				case FIVE_BURNING:
					sb.append("[npc.NameIsFull] полностью захлестнута похотью, и [npc.is] не способна думать ни о чем, кроме секса.");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Осторожные нападающие</b><br>Всем видно, что ты буквально наполнена похотью. Некоторые наверняка попытаются этим воспользоваться.");
					break;
			}
		}
		
		return UtilText.parse(character, sb.toString());
	
	}
}
