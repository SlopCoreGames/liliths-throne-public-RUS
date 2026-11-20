package com.lilithsthrone.game.character.race;

import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.78
 * @version 0.1.99
 * @author Innoxia
 */
public enum FurryPreference {

	/**No furry parts at all. (NPCs will spawn as regular humans.)*/
	HUMAN("Отключено", PresetColour.TRANSFORMATION_HUMAN, "X") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "Женственные "+subspecies.getNamePlural(null)+" будут полностью отключены в случайных встречах. Если все женственные предпочтения установлены на «Отключено», в случайных встречах по умолчанию будут появляться женщины-люди.";
		}
		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "Мужественные "+subspecies.getNamePlural(null)+" будут полностью отключены в случайных встречах. Если все мужественные предпочтения установлены на «Отключено», в случайных встречах по умолчанию будут появляться мужчины-люди.";
		}
		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleZeroDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleZero();
		}
	},
	
	/**NPCs will spawn with furry ears and eyes, and where applicable, furry tails, horns, antenna, and wings. They will <b>not</b> spawn with furry breasts or genitalia.*/
	MINIMUM("Минимум", PresetColour.TRANSFORMATION_PARTIAL, "--") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "Женственные "+subspecies.getNamePlural(null)+" будут появляться с пушистыми ушами и глазами, а также, где применимо, с пушистыми хвостами, рогами, антеннами и крыльями. У них <b>не</b> будет пушистой груди или гениталий.";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "Мужественные "+subspecies.getNamePlural(null)+" будут появляться с пушистыми ушами и глазами, а также, где применимо, с пушистыми хвостами, рогами, антеннами и крыльями. У них <b>не</b> будет пушистых гениталий.";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleOneDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleOne();
		}
	},
	
	/**NPCs will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings). They also have the chance to spawn with furry breasts, genitalia, arms, and legs.*/
	REDUCED("Меньший", PresetColour.TRANSFORMATION_PARTIAL_FULL, "-") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "Женственные "+subspecies.getNamePlural(null)+" будут появляться со всеми звериными чертами, которые включены в настройке «Минимум» (уши, глаза, хвосты, рога, антенны и крылья). Также есть шанс появления с пушистой грудью, гениталиями, руками и ногами.";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "Мужественные "+subspecies.getNamePlural(null)+" будут появляться со всеми звериными чертами, которые включены в настройке «Минимум» (уши, глаза, хвосты, рога, антенны и крылья). Также есть шанс появления с пушистой грудью, гениталиями, руками и ногами.";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleTwoDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleTwo();
		}
	},
	
	/**NPCs will spawn with all of the furry parts that the 'Minimum' setting enables (ears, eyes, tails, horns, antenna, and wings). They also have the chance to spawn with furry breasts, genitalia, arms, legs, skin/fur, and faces.*/
	NORMAL("Больший", PresetColour.TRANSFORMATION_LESSER, "+") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "Женственные "+subspecies.getNamePlural(null)+" будут появляться со всеми звериными чертами, которые включены в настройке «Минимум» (уши, глаза, хвосты, рога, антенны и крылья). Также есть шанс появления с пушистой грудью, гениталиями, руками, ногами, кожей/мехом и мордами.";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "Мужественные "+subspecies.getNamePlural(null)+" будут появляться со всеми звериными чертами, которые включены в настройке «Минимум» (уши, глаза, хвосты, рога, антенны и крылья). Также есть шанс появления с пушистой грудью, гениталиями, руками, ногами, кожей/мехом и мордами.";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleThreeDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleThree();
		}
	},
	
	/**NPCs will <b>always</b> spawn with as many furry parts as is possible (ears, eyes, tails, horns, antenna, wings, breasts, genitalia, arms, legs, skin/fur, and face).*/
	MAXIMUM("Максимальный", PresetColour.TRANSFORMATION_GREATER, "++") {
		@Override
		public String getDescriptionFeminine(AbstractSubspecies subspecies) {
			return "Женственные "+subspecies.getNamePlural(null)+" будут <b>всегда</b> появляться с максимально возможным количеством звериных черт (уши, глаза, хвосты, рога, антенны, крылья, грудь, гениталии, руки, ноги, кожа/мех и морда).";
		}

		@Override
		public String getDescriptionMasculine(AbstractSubspecies subspecies) {
			return "Мужественные "+subspecies.getNamePlural(null)+" будут <b>всегда</b> появляться с максимально возможным количеством звериных черт (уши, глаза, хвосты, рога, антенны, крылья, грудь, гениталии, руки, ноги, кожа/мех и морда).";
		}

		@Override
		public String getSVGImage(boolean disabled) {
			return disabled?SVGImages.SVG_IMAGE_PROVIDER.getScaleFourDisabled():SVGImages.SVG_IMAGE_PROVIDER.getScaleFour();
		}
	};
	
	private String name;
	private Colour colour;
	private String buttonText;
	
	private FurryPreference(String name, Colour colour, String buttonText) {
		this.name = name;
		this.colour = colour;
		this.buttonText = buttonText;
	}

	public abstract String getSVGImage(boolean disabled);
	
	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
	
	public String getButtonText() {
		return buttonText;
	}

	public abstract String getDescriptionFeminine(AbstractSubspecies subspecies);
	public abstract String getDescriptionMasculine(AbstractSubspecies subspecies);
}
