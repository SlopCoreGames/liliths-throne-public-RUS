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
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже уверенный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо более уверенным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] уже не уверенный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(чувствуя себя гораздо менее уверенным)]!")
						+ "</p>");
		}
	},
	
	SHY(false, PersonalityCategory.CORE, "shy", "[npc.NameIsFull] невероятно застенчивый с другими людьми, и [npc.verb(prefer)] избегать разговоров, где это возможно.", "", PresetColour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже застенчивый, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо скромнее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] и так не страдает от скромности, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] скромность)]!")
						+ "</p>");
		}
	},

	KIND(false, PersonalityCategory.CORE, "kind", "[npc.Name] всегда [npc.verb(try)] быть доброй и внимательной к окружающим, иногда даже в ущерб собственному счастью [npc.her].", "", PresetColour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже добрый, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо добрее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] и так не очень добрая, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] доброту)]!")
						+ "</p>");
		}
	},
	
	SELFISH(false, PersonalityCategory.CORE, "selfish", "[npc.Name] всегда [npc.verb(put)] [npc.herself] на первом месте, и [npc.is] крайне маловероятно, что она сделает что-то, что не принесет прямой выгоды [npc.herHim].", "", PresetColour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже эгоистичен, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствует себя куда более эгоистичной)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] и так не эгоистична, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] эгоизма)]!")
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
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже наивный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо более наивным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] @@T1@@ уже циничный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] наивность)]!")
						+ "</p>");
		}
	},
	
	CYNICAL(false, PersonalityCategory.CORE, "cynical", "[npc.NameIsFull] особенно недоверчивый к намерениям и мотивациям других людей.", "", PresetColour.BASE_RED_DARK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(NAIVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже циничный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя гораздо более циничным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] уже не циничный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] цинизма)]!")
						+ "</p>");
		}
	},
	
	// Combat traits:
	
	BRAVE(false, PersonalityCategory.COMBAT, "brave", "[npc.Name] всегда [npc.verb(act)] мужественно, и [npc.is] не боится драки.", "", PresetColour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже храбрый, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствует себя куда смелее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] уже не храбрый, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] смелости)]!")
						+ "</p>");
		}
	},
	
	COWARDLY(false, PersonalityCategory.COMBAT, "cowardly", "[npc.Name] [npc.verb(get)] испугается очень легко, и предпочтет убегать от конфликтов, а не пытаться разрешить их напрямую.", "", PresetColour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже трусливый, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя намного более трусливым)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] @@T1@@ уже трусливый, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] трусость)]!")
						+ "</p>");
		}
	},

	// Sex traits:
	
	LEWD(false,
			PersonalityCategory.SEX,
			"lewd",
			"[npc.NameHasFull] обширное знание обо всем, что связано с сексом, и [npc.is] всегда готов поговорить о непристойных вещах.",
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
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже непристойный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя намного более непристойным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] уже не непристойный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] непристойность)]!")
						+ "</p>");
		}
	},
	
	INNOCENT(false,
			PersonalityCategory.SEX,
			"innocent",
			"[npc.Name] всегда [npc.verb(act)] смущен и невинный, когда говорит о (или выполняет) сексуальные акты.",
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
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже не невинный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(чувствуя себя намного более невинным)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] уже не невинный, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряя [npc.her] невинность)]!")
						+ "</p>");
		}
	},
	
	PRUDE(false,
			PersonalityCategory.SEX,
			"prude",
			"[npc.Name] [npc.do] не любит говорить о сексуальных вопросах, и [npc.verb(отказаться)] даже признать, что [npc.she] [npc.verb(знать)] что-либо об таких вещах.",
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
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже пуританка, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(найти)] [npc.herself] [style.colourMinorGood(чувствуя себя намного более пуританкой)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] уже не пуританка, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(теряет [npc.her] целомудрия)]!")
						+ "</p>");
		}
	},

	// Speech traits:
	
	LISP(false,
			PersonalityCategory.SPEECH,
			"lisp",
			"[npc.Name] [npc.verb(говорить)] с картавостью, произнося 'с' и 'з' как 'th'.",
			"[style.italicsBad(Все речи [npc.namePos] в игре будут затронуты этой картавостью!)]", PresetColour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] уже [npc.verb(speak)] с картавостью, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(говорит с картавостью)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] уже [npc.do]н'т говорить с картавостью, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(умеет говорить без картавости)]!")
						+ "</p>");
		}
	},

	STUTTER(false,
			PersonalityCategory.SPEECH,
			"stutter",
			"[npc.NameHasFull] привычка заикаться и спотыкаться на [npc.her] словах, как [npc.she] [npc.verb(speak)].",
			"[style.italicsBad(Все [npc.namePos] внутриигровые диалоги будут подвержены этому заиканию!)]", PresetColour.BASE_PINK_SALMON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] уже [npc.verb(speak)] с заиканием, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(говорит с заиканием)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] уже [npc.do]н'т говорить с запиной, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(может говорить без запинки)]!")
						+ "</p>");
		}
	},

	MUTE(true,
			PersonalityCategory.SPEECH,
			"mute",
			"[npc.NameIsFull] немой, и хотя [npc.she] может издавать некоторые непристойные звуки, когда находится в плену страсти, [npc.sheIsFull] в остальном полностью не может говорить.",
			"[style.italicsBad(Все [npc.namePos] игровые диалоги будут удалены!)]", PresetColour.BASE_CRIMSON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LISP, STUTTER, SLOVENLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] уже немой, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(не может говорить)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] уже может говорить, так что ничего не происходит...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(теперь она может говорить)]!")
						+ "</p>");
		}
	},

	SLOVENLY(false,
			PersonalityCategory.SPEECH,
			"slovenly",
			"[npc.Name] [npc.verb(speak)] крайне небрежно; проглатывая слоги и коверкая слова, так что [npc.her] речь зачастую бывает очень трудно понять.",
			"[style.italicsBad(Вся игровая речь [npc.namePos] будет изменена этим чертой!)]", PresetColour.BASE_BROWN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] уже и так [npc.verb(speak)] небрежно, так что ничего не меняется...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(говоря небрежно)]!")
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
				sb.append("<br/>[style.italicsDisabled(Нет текущих игровых эффектов...)]");
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
