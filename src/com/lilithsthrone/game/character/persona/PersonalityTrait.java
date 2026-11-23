package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/***
 * @since 0.2.4
 * @version 0.4.9
 * @author Innoxia
 */
public enum PersonalityTrait {
	
	// Core traits:
	
	CONFIDENT(false, PersonalityCategory.CORE, "confident", "[npc.NameIsFull] очень напористая и уверенная в [npc.herself].", "", PresetColour.BASE_GREEN_LIME) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SHY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] и так уверена в себе, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствует себя гораздо увереннее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] и так не хватает уверенности, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(чувствует себя гораздо менее уверенно)]!")
						+ "</p>");
		}
	},
	
	SHY(false, PersonalityCategory.CORE, "shy", "[npc.NameIsFull] невероятно застенчива перед другими людьми и [npc.verb(prefer)] избегает разговоров, где это только возможно.", "", PresetColour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] и так застенчива, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(становится еще более застенчивой)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] и так не страдает от застенчивости, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] застенчивость)]!")
						+ "</p>");
		}
	},

	KIND(false, PersonalityCategory.CORE, "kind", "[npc.Name] всегда [npc.verb(try)] добрым и внимательным к окружающим, иногда даже в ущерб [npc.her] собственному счастью.", "", PresetColour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] [npc.isFull] и так добр, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо добрее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] и так не очень добр, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] доброту)]!")
						+ "</p>");
		}
	},
	
	SELFISH(false, PersonalityCategory.CORE, "selfish", "[npc.Name] всегда [npc.verb(put)] [npc.herself] на первом месте, и [npc.is] маловероятно, что он сделает что-то, что не принесет прямой выгоды [npc.herHim].", "", PresetColour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] [npc.isFull] и так эгоистичен, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо более эгоистичным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] и так не эгоистичен, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] эгоизм)]!")
						+ "</p>");
		}
	},

	NAIVE(false, PersonalityCategory.CORE, "naive", "Из-за нехватки жизненного опыта и мудрости, [npc.name] [npc.has] совершенно не понимает суровых реалий жизни.", "", PresetColour.BASE_PINK_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CYNICAL);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже достаточно наивен, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствует себя гораздо более наивным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] уже не наивен, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] наивность)]!")
						+ "</p>");
		}
	},
	
	CYNICAL(false, PersonalityCategory.CORE, "cynical", "[npc.NameIsFull] особенно не доверяет намерениям и мотивам других людей.", "", PresetColour.BASE_RED_DARK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(NAIVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже достаточно циничен, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствует себя гораздо более циничным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] уже не циничен, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] цинизм)]!")
						+ "</p>");
		}
	},
	
	// Combat traits:
	
	BRAVE(false, PersonalityCategory.COMBAT, "brave", "[npc.Name] всегда [npc.verb(act)] отважно, и [npc.is] не из тех, кто бежит от драки.", "", PresetColour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] и так уже храбр(а), так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо смелее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] уже и так не храбр(а), так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] храбрости)]!")
						+ "</p>");
		}
	},
	
	COWARDLY(false, PersonalityCategory.COMBAT, "cowardly", "[npc.Name] [npc.verb(get)] очень легко пугается и предпочитает убегать от конфликтов, а не пытаться решить их напрямую.", "", PresetColour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] и так уже труслив(а), так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо трусливее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] уже и так не труслив(а), так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] трусости)]!")
						+ "</p>");
		}
	},

	// Sex traits:
	
	LEWD(false,
			PersonalityCategory.SEX,
			"lewd",
			"[npc.NameHasFull] обширные познания во всем, что касается секса, и [npc.is] вечная готовность поболтать о пошлинках.",
			"", PresetColour.BASE_PINK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(PRUDE, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже и так развратная, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя куда более развратной)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] уже и так не развратная, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] развратности)]!")
						+ "</p>");
		}
	},
	
	INNOCENT(false,
			PersonalityCategory.SEX,
			"innocent",
			"[npc.Name] всегда [npc.verb(act)] смущается и ведет себя невинно, когда речь заходит о сексуальных актах (или когда она их совершает).",
			"", PresetColour.BASE_BLUE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, PRUDE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже и так невинная, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя куда более невинной)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] уже и так не невинная, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] невинности)]!")
						+ "</p>");
		}
	},
	
	PRUDE(false,
			PersonalityCategory.SEX,
			"prude",
			"[npc.Name] [npc.do] не любит говорить на сексуальные темы, и даже [npc.verb(refuse)] признать, что [npc.she] [npc.verb(know)] что-то подобное.",
			"", PresetColour.BASE_BLUE_STEEL) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] уже [npc.isFull] скромница, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствует себя гораздо более скромной)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] уже не является скромницей, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] скромные манеры)]!")
						+ "</p>");
		}
	},

	// Speech traits:
	
	LISP(false,
			PersonalityCategory.SPEECH,
			"lisp",
			"[npc.Name] [npc.verb(speak)] с шепелявостью, произнося «с» и «з» как «ф».",
			"[style.italicsBad(На всю [npc.namePos] внутриигровую речь будет влиять эта шепелявость!)]", PresetColour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] уже [npc.verb(speak)] с шепелявостью, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(говорит с шепелявостью)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] уже [npc.do] не говорит с шепелявостью, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(способна говорить без шепелявости)]!")
						+ "</p>");
		}
	},

	STUTTER(false,
			PersonalityCategory.SPEECH,
			"stutter",
			"[npc.NameHasFull] привычка заикаться и запинаться на [npc.her] словах, когда [npc.she] [npc.verb(speak)].",
			"[style.italicsBad(На всю речь [npc.namePos] в игре будет влиять это заикание!)]", PresetColour.BASE_PINK_SALMON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] уже [npc.verb(speak)] с заиканием, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(говорит с заиканием)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] уже [npc.do] не говорит с заиканием, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(способна говорить без заикания)]!")
						+ "</p>");
		}
	},

	MUTE(true,
			PersonalityCategory.SPEECH,
			"mute",
			"[npc.NameIsFull] — немая, и хотя [npc.she] может издавать всякие похотливые звуки в порыве страсти, в остальном [npc.sheIsFull] совершенно неспособна говорить.",
			"[style.italicsBad(Вся речь [npc.namePos] в игре будет удалена!)]", PresetColour.BASE_CRIMSON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LISP, STUTTER, SLOVENLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] уже немая, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(не может говорить)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] уже и так говорит, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(может говорить)]!")
						+ "</p>");
		}
	},

	SLOVENLY(false,
			PersonalityCategory.SPEECH,
			"slovenly",
			"[npc.Name] [npc.verb(speak)] очень небрежно; проглатывая слоги и с плохим произношением, [npc.her] речь часто бывает очень трудной для понимания.",
			"[style.italicsBad(На всю игровую речь [npc.namePos] будет влиять этот эффект!)]", PresetColour.BASE_BROWN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] уже [npc.verb(speak)] небрежно, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(говорит небрежно)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] уже [npc.do] не говорит небрежно, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(больше не говорит небрежно)]!")
						+ "</p>");
		}
	},;
	
	private boolean specialRequirements;
	private PersonalityCategory personalityCategory;
	private String name;
	private String description;
	private String gameplayInformation;
	private Colour colour;
	
	private PersonalityTrait(boolean specialRequirements, PersonalityCategory personalityCategory, String name, String description, String gameplayInformation, Colour colour) {
		this.specialRequirements = specialRequirements;
		this.personalityCategory = personalityCategory;
		this.name = name;
		this.description = description;
		this.gameplayInformation = gameplayInformation;
		this.colour = colour;
	}
	
	public PersonalityCategory getPersonalityCategory() {
		return personalityCategory;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter character, boolean withGameplayInformation, boolean withMutuallyExclusiveInformation) {
		StringBuilder sb = new StringBuilder();

		sb.append(UtilText.parse(character, description));
		
		if(withGameplayInformation) {
			if(gameplayInformation!=null && !gameplayInformation.isEmpty()) {
				sb.append("<br/>"+UtilText.parse(character, gameplayInformation));
			} else {
				sb.append("<br/>[style.italicsDisabled(Текущих игровых эффектов нет...)]");
			}
		}
		
		if(withMutuallyExclusiveInformation) {
			if(!this.getMutuallyExclusiveSettings().isEmpty()) {
				sb.append("<br/>[style.colourBad(Взаимоисключающие)] с ");
				List<String> names = new ArrayList<>();
				for(PersonalityTrait trait : this.getMutuallyExclusiveSettings()) {
					names.add("<span style='color:"+trait.getColour().toWebHexString()+";'>"+trait.getName()+"</span>");
				}
				sb.append(Util.stringsToStringList(names, false)+"!");
			}
		}
		
		return sb.toString();
	}
	
	public Colour getColour() {
		return colour;
	}

	public abstract List<PersonalityTrait> getMutuallyExclusiveSettings();

	public abstract String getAdditionDescription(GameCharacter target);
	
	public abstract String getRemovalDescription(GameCharacter target);
	
	public boolean isSpecialRequirements() {
		return specialRequirements;
	}
}
