package com.lilithsthrone.game.dialogue.utils;

import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Units.ValueType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.7?
 * @version 0.3.9.1
 * @author Innoxia
 */
public class CharacterModificationUtils {

	private static StringBuilder contentSB = new StringBuilder();

	public static final int FLUID_INCREMENT_SMALL = 5;
	public static final int FLUID_INCREMENT_AVERAGE = 50;
	public static final int FLUID_INCREMENT_LARGE = 500;
	public static final int FLUID_INCREMENT_HUGE = 5000;

	public static final int FLUID_REGEN_INCREMENT_SMALL = 100;
	public static final int FLUID_REGEN_INCREMENT_AVERAGE = 1000;
	public static final int FLUID_REGEN_INCREMENT_LARGE = 10000;
	public static final int FLUID_REGEN_INCREMENT_HUGE = 100000;

	public static final int MAX_AGE_PLAYER = 50;
	public static final int MAX_AGE_NPC = 60;

	public static String getInformationDiv(String id, TooltipInformationEventListener information) {
		return getInformationDiv(id, information, false);
	}
	
	public static String getInformationDiv(String id, TooltipInformationEventListener information, boolean left) {
		Game.informationTooltips.put(id, information);
		return "<div class='title-button no-select' id='"+id+"' style='position:absolute; "+(left?"left:8px; right:auto;":"left:auto; right:8px;")+" top:8px; background:transparent; padding:0; margin:0;'>"
					+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
				+"</div>";
	}
	
	public static String getStartDateDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='cosmetics-inner-container full'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Дата начала</b></p>");
			contentSB.append("<p style='text-align:center;'>Выберите месяц, в котором начнется игра.</p>");
	
			for(Month month : Month.values()) {
				if(Main.game.getStartingDate().getMonth() == month) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH))+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='STARTING_MONTH_"+month+"' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_MINOR_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH))+"</span>"
							+ "</div>");
				}
			}

		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	// Basics:
	
	public static String getGenderChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-half-width' style='text-align:center;'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Пол</b></p>");
		
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>Ваш пол определяет, с какими гениталиями вы начнете игру.</p>");
			}
			
			if(BodyChanging.getTarget().getGender().getGenderName().isHasVagina()) {
				contentSB.append(
						"<div id='CHOOSE_GENDER_MALE' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.MASCULINE.getShades()[0]+";'>Мужчина</span></div><div class='cosmetics-button active'>[style.boldFeminine(Женственный)]</div>");
			} else {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourMasculine(Male)]"
						+ "</div>"
						+ "<div id='CHOOSE_GENDER_FEMALE' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.FEMININE.getShades()[0]+";'>Женщина</span></div>");
			}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getFemininityChoiceDiv() {
		contentSB.setLength(0);

		contentSB.append("<div class='container-half-width' style='text-align:center;'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Женственность</b></p>");
		
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>Женственность — это показатель того, насколько ваше лицо и тело мужские или женские.</p>");
			}
			
			if(BodyChanging.getTarget().getGender().getGenderName().isHasVagina()) {
				if(BodyChanging.getTarget().getFemininity()==Femininity.ANDROGYNOUS) {
					contentSB.append(
							"<div class='cosmetics-button active'>[style.colourAndrogynous(Андрогинный)]</div>");
				} else {
					contentSB.append(
							"<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.ANDROGYNOUS.getShades()[0]+";'>Андрогинный</span></div>");
				}
				if(BodyChanging.getTarget().getFemininity()==Femininity.FEMININE) {
					contentSB.append(
							"<div class='cosmetics-button active'>[style.colourFeminine(Женственный)]</div>");
				} else {
					contentSB.append(
							"<div id='CHOOSE_FEM_FEMININE' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.FEMININE.getShades()[0]+";'>Женственный</span></div>");
				}
				if(BodyChanging.getTarget().getFemininity()==Femininity.FEMININE_STRONG) {
					contentSB.append(
							"<div class='cosmetics-button active'>[style.colourFeminineStrong(Очень женственный)]</div>");
				} else {
					contentSB.append(
							"<div id='CHOOSE_FEM_FEMININE_STRONG' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.FEMININE_PLUS.getShades()[0]+";'>Очень женственный</span></div>");
				}
				
			} else {
				if(BodyChanging.getTarget().getFemininity()==Femininity.ANDROGYNOUS) {
					contentSB.append(
							"<div class='cosmetics-button active'>[style.colourAndrogynous(Андрогинный)]</div>");
				} else {
					contentSB.append(
							"<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.ANDROGYNOUS.getShades()[0]+";'>Андрогинный</span></div>");
				}
				if(BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE) {
					contentSB.append(
							"<div class='cosmetics-button active'>[style.colourMasculine(Мужественный)]</div>");
				} else {
					contentSB.append(
							"<div id='CHOOSE_FEM_MASCULINE' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.MASCULINE.getShades()[0]+";'>Мужественный</span></div>");
				}
				if(BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE_STRONG) {
					contentSB.append(
							"<div class='cosmetics-button active'>[style.colourMasculineStrong(Очень мужественный)]</div>");
				} else {
					contentSB.append(
							"<div id='CHOOSE_FEM_MASCULINE_STRONG' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.MASCULINE_PLUS.getShades()[0]+";'>Очень мужественный</span></div>");
				}
			}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getPersonalityChoiceDiv(boolean allowSpecials) {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width' style='text-align:center;'>");
		
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Характер</b></p>");
				
				if(BodyChanging.getTarget().isPlayer()) {
					contentSB.append("<p style='text-align:center;'>Ваш характер будет оказывать небольшое влияние в некоторых ситуациях. Он не будет ограничивать какие-либо настройки во время игры, а служит скорее для ролевого отыгрыша.</p>");
				}
				
				for(PersonalityTrait trait : PersonalityTrait.values()) {
					if(allowSpecials || !trait.isSpecialRequirements()) {
						if(BodyChanging.getTarget().hasPersonalityTrait(trait)) {
							contentSB.append(
									"<div id='PERSONALITY_TRAIT_"+trait+"' class='cosmetics-button active'><span style='color:"+trait.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(trait.getName())+"</span>"
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div id='PERSONALITY_TRAIT_"+trait+"' class='cosmetics-button'>[style.colourDisabled("+Util.capitaliseSentence(trait.getName())+")]"
									+ "</div>");
						}
					}
				}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getObedienceChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
		
			contentSB.append("<div class='cosmetics-inner-container full'>");
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Покорность</b></p>");
				
				for(ObedienceLevel obedience : ObedienceLevel.values()) {
					if(BodyChanging.getTarget().getObedience()==obedience) {
						contentSB.append(
								"<div id='OBEDIENCE_LEVEL_"+obedience+"' class='cosmetics-button active'><span style='color:"+obedience.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='OBEDIENCE_LEVEL_"+obedience+"' class='cosmetics-button'>[style.colourDisabled("+Util.capitaliseSentence(obedience.getName())+")]"
								+ "</div>");
					}
				}
			
			contentSB.append("</div>");
			
		contentSB.append("</div>");
		
		return contentSB.toString();
	}

	public static String getAffectionChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
		
			contentSB.append("<div class='cosmetics-inner-container full'>");
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Привязанность</b></p>");
				
				for(AffectionLevel affection : AffectionLevel.values()) {
					if(BodyChanging.getTarget().getAffectionLevel(Main.game.getPlayer())==affection) {
						contentSB.append(
								"<div id='AFFECTION_LEVEL_"+affection+"' class='cosmetics-button active'><span style='color:"+affection.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='AFFECTION_LEVEL_"+affection+"' class='cosmetics-button'>[style.colourDisabled("+Util.capitaliseSentence(affection.getName())+")]"
								+ "</div>");
					}
				}
			
			contentSB.append("</div>");
			
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	
	public static void performAgeCheck() {
		int age = (int) ChronoUnit.YEARS.between(BodyChanging.getTarget().getBirthday(), Main.game.getDateNow());
		
		if(BodyChanging.getTarget().isPlayer() && age>CharacterModificationUtils.MAX_AGE_PLAYER) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(age-CharacterModificationUtils.MAX_AGE_PLAYER));
			
		} else if(!BodyChanging.getTarget().isPlayer() && age>(CharacterModificationUtils.MAX_AGE_NPC-GameCharacter.MINIMUM_AGE)) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(age-(CharacterModificationUtils.MAX_AGE_NPC-GameCharacter.MINIMUM_AGE)));
		}
		
		if(BodyChanging.getTarget().isPlayer() && age<18) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusYears(18-age));
		}
		
		if(age<0) {
			BodyChanging.getTarget().setBirthday(Main.game.getDateNow());
		}
	}
	
	public static String getBirthdayChoiceDiv() {
		contentSB.setLength(0);

		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>День рождения</b></p>");
			
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>Вы родились "
										+Units.date(BodyChanging.getTarget().getBirthday(), Units.DateType.LONG)+", вам "+Util.intToString(BodyChanging.getTarget().getAgeValue())+" года.</p>");
			}
			
			contentSB.append("<div class='container-full-width' style='margin:0;padding;0;width:100%;'>");
			
				contentSB.append("<div class='container-full-width' style='width:calc(33.3% - 16px);'>");
					contentSB.append(applyDateWrapper("День", "BIRTH_DAY", "", "", String.valueOf(BodyChanging.getTarget().getBirthday().getDayOfMonth()), false, false));
				contentSB.append("</div>");

				contentSB.append("<div class='container-full-width' style='width:calc(33.3% - 16px);'>");
					contentSB.append(applyDateWrapper("Месяц", "BIRTH_MONTH", "", "", BodyChanging.getTarget().getBirthday().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false, false));
				contentSB.append("</div>");

				contentSB.append("<div class='container-full-width' style='width:calc(33.3% - 16px);'>");
					contentSB.append(applyDateWrapper("Возраст", "AGE", "", "",
							String.valueOf(BodyChanging.getTarget().getAgeValue()),
							BodyChanging.getTarget().getAgeValue()<=18,
							BodyChanging.getTarget().isPlayer()
								?BodyChanging.getTarget().getAgeValue()>=MAX_AGE_PLAYER
								:BodyChanging.getTarget().getAgeValue()>=MAX_AGE_NPC));
				contentSB.append("</div>");
				
			contentSB.append("</div>");
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String applyDateWrapper(String title, String id, String measurement, String measurementPlural, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return "<p style='width:100%;  margin:0; padding:0; text-align:center;'>"
						+ "<b>"+title+"</b>"
					+ "</p>"
					+ "<div class='container-half-width' style='width:30%; margin:0; padding:0; text-align:center;'>"
						+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (decreaseDisabled?"[style.boldDisabled(--"+measurementPlural+")]":"[style.boldBad(--)]")
						+ "</div>"
						+ "<div id='"+id+"_УМЕНЬШИТЬ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (decreaseDisabled?"[style.boldDisabled(-"+measurement+")]":"[style.boldMinorBad(-)]")
						+ "</div>"
					+ "</div>"
					+ "<div class='container-half-width' style='width:40%; margin:0; padding:0; text-align:center;'>"
						+ value
					+ "</div>"
					+ "<div class='container-half-width' style='width:30%; margin:0; padding:0; text-align:center;'>"
						+ "<div id='"+id+"_УВЕЛИЧИТЬ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (increaseDisabled?"[style.boldDisabled(+"+measurement+")]":"[style.boldMinorGood(+)]")
						+ "</div>"
						+ "<div id='"+id+"_УВЕЛИЧИТЬ_БОЛЬШОЙ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (increaseDisabled?"[style.boldDisabled(++"+measurementPlural+")]":"[style.boldGood(++)]")
						+ "</div>"
					+ "</div>";
	}
	
	public static String getAgeChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-half-width'>");
			contentSB.append(applyDateWrapper("Возраст", "AGE", "", "",
					String.valueOf(BodyChanging.getTarget().getAgeValue()),
					BodyChanging.getTarget().getAgeValue()<=18,
					BodyChanging.getTarget().isPlayer()
						?BodyChanging.getTarget().getAgeValue()>=MAX_AGE_PLAYER
						:BodyChanging.getTarget().getAgeValue()>=MAX_AGE_NPC));
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getOrientationChoiceDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isPlayer()) {
			contentSB.append("<div class='container-full-width' style='text-align:center;'>");
		} else {
			contentSB.append("<div class='container-half-width' style='text-align:center;'>");
		}
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Сексуальная ориентация</b></p>");
			
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>Сексуальная ориентация определяется вашим влечением к женственности или мужественности.<br/><i>Наведите курсор на иконку ориентации в панели эффектов вашего персонажа, чтобы увидеть их действие.</i></p>");
			}
			
			for(SexualOrientation orientation : SexualOrientation.values()) {
				if(BodyChanging.getTarget().getSexualOrientation() == orientation) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+orientation.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(orientation.getName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='SEXUAL_ORIENTATION_"+orientation+"' class='cosmetics-button'><span style='color:"+orientation.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(orientation.getName())+"</span>"
							+ "</div>");
				}
			}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getFetishChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<div class='container-full-width' style='padding:0;'>");
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>Фетиши</b></p>");
				
				int i=0;
				for(AbstractFetish fetish : Fetish.getAllFetishes()) {
					if((fetish.isAvailable(BodyChanging.getTarget()) || (fetish==Fetish.FETISH_PURE_VIRGIN && Main.game.getPlayer().hasVagina())) // Always allow virgin fetish so that players can start as broken virgin
							&& fetish.isContentEnabled()
							&& fetish.getFetishesForAutomaticUnlock().isEmpty()) {
						contentSB.append("<div class='container-full-width inner' style='width:100%; margin:0; padding:0; background:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
						
							contentSB.append("<div class='container-full-width inner' style='margin:0; padding:0 0 0 20px; width:25%; text-align:center;background:transparent;'>");
								contentSB.append("<span style='color:"+BodyChanging.getTarget().getFetishDesire(fetish).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fetish.getName(BodyChanging.getTarget()))+"</span>");
							contentSB.append("</div>");
	
							contentSB.append(
									getInformationDiv(
											"FETISH_INFO_"+Fetish.getIdFromFetish(fetish),
											new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(fetish.getName(BodyChanging.getTarget())), fetish.getDescription(null)),
											true));
							
							contentSB.append("<div class='container-full-width inner' style='margin:0; padding:0; width:75%; text-align:center; background:transparent;'>");
							for(FetishDesire desire : FetishDesire.values()) {
								if(BodyChanging.getTarget().getFetishDesire(fetish)==desire) {
									contentSB.append("<div class='cosmetics-button active' style='width:18%; margin:1%; min-width:0;'>"
											+ "<span style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getName())+"</span></div>");
								} else {
									contentSB.append("<div id='FETISH_DESIRE_"+Fetish.getIdFromFetish(fetish)+desire+"' class='cosmetics-button' style='width:18%; margin:1%; min-width:0;'><span style='color:"+desire.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(desire.getName())+"</span></div>");
								}
							}
							contentSB.append("</div>");
							
						contentSB.append("</div>");
					}
					i++;
				}
			
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	
	public static int maxSexExperience = 500;
	public static int[] normalSexExperienceValues = new int[] {0, 1, 25, 100, 250};
	public static float[] sexExperienceCorruption = new float[] {0, 0.5f, 1, 2.5f, 5};
	
	private static Colour[] sexColours = new Colour[] {PresetColour.GENERIC_EXCELLENT, PresetColour.BASE_PINK_LIGHT, PresetColour.BASE_PINK_SALMON, PresetColour.BASE_PINK, PresetColour.BASE_PINK_DEEP};
	public static String[] feminineNames = new String[] {"Девственница", "Неопытная", "Опытная", "Эксперт", "Шлюшка"};
	public static String[] masculineNames = new String[] {"Девственница", "Неопытная", "Опытная", "Эксперт", "Самец"};
	
//	public static String[] virginityLossesGynephilic = new String[] {"your girlfriend", "", "some girl in your apartment", "some girl in a club's restroom"};
//	public static String[] virginityLossesAmbiphilic = new String[] {"your girlfriend in her apartment", "your girlfriend in your apartment", "some girl in her apartment", "some girl in your apartment", "some girl in a club's restroom",
//			"your boyfriend in his apartment", "your boyfriend in your apartment", "some guy in his apartment", "some guy in your apartment", "some guy in a club's restroom"};
//	public static String[] virginityLossesAndrophilic = new String[] {"your boyfriend in his apartment", "your boyfriend in your apartment", "some guy in his apartment", "some guy in your apartment", "some guy in a club's restroom"};
//	
	
	
	public static String getSexualExperienceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'><div class='container-full-width' style='text-align:center;'><b>Секс-действия [style.colourSex(Совершенные)]</b></div>");
		
			contentSB.append(
							getSexExperienceEntry("HANDJOBS_GIVEN",
									"Дрочка",
									"вы доводили кого-то до оргазма рукой",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))
							
							+ getSexExperienceEntry("FINGERINGS_GIVEN",
									"Пальцевое проникновение",
									"вы ласкали кого-то пальцами",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))
							
							+ getSexExperienceEntry("BLOWJOBS_GIVEN",
									"Минет",
									"вы сосали чей-то член",
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))
							
							+ getSexExperienceEntry("CUNNILINGUS_GIVEN",
									"Куннилингус",
									"Кунилингус",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))

							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANILINGUS_GIVEN",
										"Анилингус",
										"вы ласкали чей-то анус языком",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))
									:"")
			
							+ getSexExperienceEntry("VAGINAL_GIVEN",
									"Вагинальный секс",
									"ты трахнул чью-то киску",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))
							
							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANAL_GIVEN",
										"Анальный секс",
										"ты трахнул чью-то жопу",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))
									:""));
		contentSB.append("</div>");

		contentSB.append("<div class='container-full-width'><div class='container-full-width' style='text-align:center;'><b>Секс-действия [style.colourSexDom(Полученные)]</b></div>");
			contentSB.append(
							(BodyChanging.getTarget().hasPenis()
									?getSexExperienceEntry("HANDJOBS_TAKEN",
										"Дрочка",
										"кто-то отсосал тебе рукой",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER))
									:"")
							
							+ (BodyChanging.getTarget().hasVagina()
									?getSexExperienceEntry("FINGERINGS_TAKEN",
										"Пальцевое проникновение",
										"тебе кто-то ласкал киску пальцами",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER))
									:"")
							
							+ (BodyChanging.getTarget().hasPenis()
									?getSexExperienceEntry("BLOWJOBS_TAKEN",
										"Минет",
										"кто-то отсосал твой член",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))
									:"")
							
							+ (BodyChanging.getTarget().hasVagina()
									?getSexExperienceEntry("CUNNILINGUS_TAKEN",
										"Куннилингус",
										"тебе кто-то отлизал",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE))
									:"")
							
							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANILINGUS_TAKEN",
										"Анилингус",
										"кто-то облизал твой анус",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))
									:"")
							
							+ (BodyChanging.getTarget().hasVagina()
									?getSexExperienceEntry("VAGINAL_TAKEN",
										"Вагинальный секс",
										"твою киску трахнули",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))
									:"")

							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANAL_TAKEN",
										"Анальный секс",
										"твой зад оттрахали",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS))
									:""));
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static void resetImpossibeSexExperience() {
		if(!BodyChanging.getTarget().hasVagina()) {
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), 0);
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER), 0);
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE), 0);
		}
		if(!BodyChanging.getTarget().hasPenis()) {
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 0);
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER), 0);
		}
	}

	public static void incrementSexExperience(SexType type, int increment) {
		int count = BodyChanging.getTarget().getSexCount(null, type) + increment;
		setSexExperience(type, count);
	}
	
	public static void setSexExperience(SexType type, int count) {
		count = Math.min(maxSexExperience, Math.max(0, count));
		int index = getSexExperienceIndex(type);
		BodyChanging.getTarget().incrementAttribute(Attribute.MAJOR_CORRUPTION, -sexExperienceCorruption[index]);
		BodyChanging.getTarget().setSexCount(null, type, count);
		index = getSexExperienceIndex(type);
		BodyChanging.getTarget().incrementAttribute(Attribute.MAJOR_CORRUPTION, sexExperienceCorruption[index]);
		
		
		if(count!=0) {
			if(BodyChanging.getTarget().getSexualOrientation()==SexualOrientation.GYNEPHILIC
					|| (BodyChanging.getTarget().getSexualOrientation()==SexualOrientation.AMBIPHILIC && !BodyChanging.getTarget().isFeminine())) {
				BodyChanging.getTarget().setVirginityLoss(type, "", BodyChanging.getTarget().isPlayer()?"твоя девушка":"незнакомец");
			} else {
				BodyChanging.getTarget().setVirginityLoss(type, "", BodyChanging.getTarget().isPlayer()?"твой парень":"незнакомка");
			}
		} else {
			BodyChanging.getTarget().resetVirginityLoss(type);
		}
		
//		System.out.println(BodyChanging.getTarget().getNameIgnoresPlayerKnowledge()+": "+type+" | "+BodyChanging.getTarget().getVirginityLossDescription(type));
		
		if(type.getPerformingSexArea()==SexAreaPenetration.PENIS) {
			if(count==0 || type.getTargetedSexArea().isPenetration() || (type.getTargetedSexArea().isOrifice() && !((SexAreaOrifice)type.getTargetedSexArea()).isInternalOrifice())) {
				BodyChanging.getTarget().setPenisVirgin(true);
			} else {
				BodyChanging.getTarget().setPenisVirgin(false);
			}
		}
		
		if(type.getTargetedSexArea()==SexAreaPenetration.PENIS) {
			if(type.getPerformingSexArea().isOrifice()) {
				switch((SexAreaOrifice)type.getPerformingSexArea()) {
					case ANUS:
						if(count==0) {
							BodyChanging.getTarget().setAssVirgin(true);
						} else {
							BodyChanging.getTarget().setAssVirgin(false);
						}
						break;
					case ARMPITS:
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(count==0) {
							BodyChanging.getTarget().setFaceVirgin(true);
						} else {
							BodyChanging.getTarget().setFaceVirgin(false);
						}
						break;
					case NIPPLE:
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(count==0) {
							BodyChanging.getTarget().setVaginaVirgin(true);
						} else {
							BodyChanging.getTarget().setVaginaVirgin(false);
						}
						break;
					case SPINNERET:
						if(count==0) {
							BodyChanging.getTarget().setSpinneretVirgin(true);
						} else {
							BodyChanging.getTarget().setSpinneretVirgin(false);
						}
						break;
				}
			}
		}
	}
	
	private static int getSexExperienceIndex(SexType associatedSexType) {
		int index = 0;
		for(int i=normalSexExperienceValues.length-1; i>=0; i--) {
			if(BodyChanging.getTarget().getTotalSexCount(associatedSexType) >= normalSexExperienceValues[i]) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	private static String getSexExperienceEntry(String id, String title, String description, SexType associatedSexType) {
		int index = getSexExperienceIndex(associatedSexType);
		
		String[] names = BodyChanging.getTarget().isFeminine()?feminineNames:masculineNames;
		
		int sexCount = BodyChanging.getTarget().getTotalSexCount(associatedSexType);
		boolean decreaseDisabled = sexCount<=0;
		boolean increaseDisabled = sexCount>=maxSexExperience;
		int singleStep = 1;
		int minorStep = 10;
		int majorStep = 100;
		
		return "<div class='container-full-width inner'>"
					+ "<div class='container-full-width inner' style='width:25%;margin:0;padding:0; font-weight:bold; text-align:center;'>"
						+ title
					+ "</div><div class='container-full-width inner' style='width:75%;margin:0;padding:0;text-align:center;'><i>Количество раз "+description+".</i></div><div class='container-full-width' style='width:100%;margin:0;padding:0; display:flex; align-items:baseline;'><div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'><div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+majorStep+")]":"[style.boldBad(-"+majorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+minorStep+")]":"[style.boldBad(-"+minorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ_МАЛ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+singleStep+")]":"[style.boldBadMinor(-"+singleStep+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:8%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+"<span style='color:"+sexColours[index].toWebHexString()+";'>"+sexCount+"</span>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ_МАЛ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+singleStep+")]":"[style.boldGoodMinor(+"+singleStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+minorStep+")]":"[style.boldGoodMinor(+"+minorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ_БОЛЬШОЙ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+majorStep+")]":"[style.boldGood(+"+majorStep+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width inner' style='width:40%; margin:0;padding:0;text-align:center;'>"
							+"<span style='color:"+sexColours[index].toWebHexString()+";'>"+names[index]+"</span>"
							+" ([style.colourCorruption(+"+sexExperienceCorruption[index]+" порочность)])</div></div></div>";
	}
	
	
	
	// Advanced:
	
	private static String applyWrapper(String title, String description, String id, String input, boolean halfWidth) {
		if(halfWidth) {
			return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:48%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
						+ "<p style='text-align:center; margin:0; padding:0;'>"
							+ "<b>"+title+"</b>"
						+"</p>"
						+ input
					+ "</div>";
			
		} else {
			return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:98%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
//						+"<div class='cosmetics-inner-container left'>"
//							+ "<p style='text-align:center; margin:0; padding:0;'>"
//								+ "<b>" +title+"</b>"
//							+"</p>"
//						+ "</div>"
//						+ "<div class='cosmetics-inner-container right'>"
//							+ input
//						+ "</div>"
						+ "<p style='text-align:center; margin:0; padding:0;'>"
							+ "<b>"+title+"</b>"
						+"</p>"
						+ input
					+ "</div>";
		}
	}

	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return applyFullVariableWrapper(title, description, id, minorStep, majorStep, value, decreaseDisabled, increaseDisabled, null, false);
	}
	
	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled, boolean fullWidth) {
		return applyFullVariableWrapper(title, description, id, minorStep, majorStep, value, decreaseDisabled, increaseDisabled, null, fullWidth);
	}
	

	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled, String additionalDescription) {
		return applyFullVariableWrapper(title, description, id, minorStep, majorStep, value, decreaseDisabled, increaseDisabled, additionalDescription, false);
	}
	
	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled, String additionalDescription, boolean fullWidth) {
			return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:"+(fullWidth?"98":"48")+"%; padding:1%; box-sizing:border-box; position:relative;'><p style='margin:0; padding:0;'>"
							+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
							+ "<b>"+title+"</b>"
						+"</p>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ_БОЛЬШ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+majorStep+")]":"[style.boldBad(-"+majorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+minorStep+")]":"[style.boldBadMinor(-"+minorStep+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:48%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ value
						+ "</div>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+minorStep+")]":"[style.boldGoodMinor(+"+minorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ_БОЛЬШОЙ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+majorStep+")]":"[style.boldGood(+"+majorStep+")]")
							+ "</div>"
						+ "</div>"
						+(additionalDescription!=null && !additionalDescription.isEmpty()
							?"<div class='container-full-width' style='width:98%; margin:1%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"+additionalDescription+"</div>"
							:"")
					+ "</div>";
	}


	private static String applyFullVariableWrapperSizes(String title, String description, String id, double value, boolean decreaseDisabled, boolean increaseDisabled, boolean fullWidth) {
		return applyFullVariableWrapper(title, description, id, Units.size(1), Units.size(5),
				Units.size(value, Units.ValueType.PRECISE, Units.UnitType.SHORT),decreaseDisabled, increaseDisabled, fullWidth);
	}
	
	private static String applyVariableWrapperFluids(String title, String description, String id, String value, boolean decreaseDisabled, boolean increaseDisabled, int incrementSmall, int incrementAverage, int incrementLarge, int incrementHuge) {
		return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:48%; padding:1%; box-sizing:border-box; position:relative'>"
					+ "<p style='margin:0; padding:0;'>"
						+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
						+ "<b>"+title+"</b>"
					+"</p>"
					+ "<div class='container-full-width'>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ_МАЛ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementSmall)+")]":"[style.boldBadMinor("+Units.fluid(-incrementSmall)+")]")
							+ "</div>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementAverage)+")]":"[style.boldBadMinor("+Units.fluid(-incrementAverage)+")]")
							+ "</div>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ_БОЛЬШ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementLarge)+")]":"[style.boldBad("+Units.fluid(-incrementLarge)+")]")
							+ "</div>"
							+ "<div id='"+id+"_УМЕНЬШИТЬ_ОГРОМНОЕ' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementHuge)+")]":"[style.boldBad("+Units.fluid(-incrementHuge)+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:48%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ value
						+ "</div>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ_МАЛ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementSmall)+")]":"[style.boldGoodMinor(+"+Units.fluid(incrementSmall)+")]")
							+ "</div>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementAverage)+")]":"[style.boldGoodMinor(+"+Units.fluid(incrementAverage)+")]")
							+ "</div>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ_БОЛЬШОЙ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementLarge)+")]":"[style.boldGood(+"+Units.fluid(incrementLarge)+")]")
							+ "</div>"
							+ "<div id='"+id+"_УВЕЛИЧИТЬ_ОГРОМНЫЙ' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementHuge)+")]":"[style.boldGood(+"+Units.fluid(incrementHuge)+")]")
							+ "</div>"
						+ "</div>"
					+ "</div>"
				+ "</div>";
	}
	

	public static String getAgeAppearanceChoiceDiv() {
		return applyFullVariableWrapper(
				"Внешний возраст",
				UtilText.parse(BodyChanging.getTarget(),
						"Изменяет то, насколько старым [npc.name] [npc.verb(appear)] кажется. [npc.She] [npc.is] может выглядеть не моложе 18 лет или на "
						+ Util.intToString(BodyChanging.getTarget().getAgeDifferenceUpperLimit())
						+ " лет старше, чем настоящий возраст [npc.her].<br/><i>Это чисто косметическое изменение, которое не влияет на игровой процесс.</i>"),
				"AGE_APPEARANCE",
				"1",
				"5",
				String.valueOf(BodyChanging.getTarget().getAppearsAsAgeValue()),
				BodyChanging.getTarget().getAppearsAsAgeValue()<=18,
				BodyChanging.getTarget().getAppearsAsAgeValue()>=(BodyChanging.getTarget().getAgeValue()+BodyChanging.getTarget().getAgeDifferenceUpperLimit()))
				
				+ applyWrapper("День рождения",
						UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] день рождения изменить нельзя, но, трансформируя тело [npc.her], [npc.she] может казаться моложе или старше, чем [npc.she] есть на самом [npc.is]."),
						"BIRTHDAY",
						"<p style='text-align:center; margin:0; padding:0;'>"
							+ BodyChanging.getTarget().getBirthdayString()
								+ UtilText.parse(BodyChanging.getTarget(),
									"</br>Making [npc.namePos] real age: <b style='color:"+BodyChanging.getTarget().getAge().getColour().toWebHexString()+"'>"+BodyChanging.getTarget().getAgeValue()+"</b>")
						+"</p>",
						true);
	}
	
	public static String getHeightChoiceDiv(boolean fullWidth) {
		return applyFullVariableWrapperSizes("Рост",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет рост [npc.name] [npc.is].<br/><i>Это влияет на некоторые описания, а также используется для определения того, относится ли секс-сцена к категории «разница в размерах».</i>"
						+ (!Main.game.isInNewWorld()
							?"<br/>[style.italicsMinorBad(Рост ограничен значением [units.sizes("+Height.getMaximumHeightForCharacterCreation()+")] при создании персонажа, но позже его можно увеличить до [units.sizes("+Height.SEVEN_COLOSSAL.getMaximumValue()+")].)]"
							:"")),
				"HEIGHT",
				BodyChanging.getTarget().getHeightValue(),
				BodyChanging.getTarget().getHeightValue()<=BodyChanging.getTarget().getMinimumHeight(),
				BodyChanging.getTarget().getHeightValue()
					>= (Main.game.isInNewWorld()
							?BodyChanging.getTarget().getMaximumHeight()
							:Height.getMaximumHeightForCharacterCreation()),
				fullWidth);
	}
	
	public static String getSelfTransformFemininityChoiceDiv() {
		return applyFullVariableWrapper(
				"Женственность",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет степень женственности или мужественности тела [npc.namePos].<br/><i>Это влияет на цвет текста речи, определяет, будет ли одежда слишком женственной или мужественной для [npc.herHim], а также используется для определения сексуального влечения (в зависимости от ориентации других).</i>"),
				"FEMININITY",
				"1",
				"5",
				BodyChanging.getTarget().getFemininityValue()
					+"<br/><i style='color:"+BodyChanging.getTarget().getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getFemininity().getName(false))+"</i>",
				BodyChanging.getTarget().getFemininityValue()<=0,
				BodyChanging.getTarget().getFemininityValue()>=100);
	}
	
	public static String getSelfTransformBodyMaterialChoiceDiv(GameCharacter target) {
		contentSB.setLength(0);
		
		List<BodyMaterial> materials = new ArrayList<>();
		switch(target.getBodyMaterial()) {
			case AIR:
				materials.add(BodyMaterial.AIR);
				break;
			case ARCANE:
				materials.add(BodyMaterial.ARCANE);
				break;
			case FIRE:
				materials.add(BodyMaterial.FIRE);
				break;
			case FLESH:
				materials.add(BodyMaterial.FLESH);
				break;
			case SILICONE:
				materials.add(BodyMaterial.SILICONE);
				break;
			case ICE:
			case WATER:
				materials.add(BodyMaterial.WATER);
				materials.add(BodyMaterial.ICE);
				break;
			case SLIME:
				materials.add(BodyMaterial.SLIME);
				break;
			case RUBBER:
			case STONE:
				materials.add(BodyMaterial.STONE);
				materials.add(BodyMaterial.RUBBER);
				break;
		}
		
		for(BodyMaterial mat : materials) {
			if(BodyChanging.getTarget().getBodyMaterial() == mat) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+mat.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(mat.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BODY_MATERIAL_"+mat+"' class='cosmetics-button'><span style='color:"+mat.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(mat.getName())+"</span>"
						+ "</div>");
			}
		}
		
		return applyWrapper("Материал тела",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет материал тела [npc.namePos].<br/><i>Разные типы материала тела дают различные пассивные бонусы. Наведите курсор на подсказку о расовом эффекте этого персонажа, чтобы увидеть их!</i>"),
				"BODY_MATERIAL",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformTailChoiceDiv(List<AbstractRace> availableRaces, boolean removeNone) {
		contentSB.setLength(0);
		
		for(AbstractTailType tail : TailType.getAllTailTypes()) {
			if((tail.getRace() !=null && availableRaces.contains(tail.getRace()))
					|| (!removeNone && tail==TailType.NONE)) {
				if(!BodyChanging.getTarget().getLegConfiguration().isAbleToGrowTail() && tail!=TailType.NONE) {
					continue;
				}
				if (!BodyChanging.getTarget().isYouko()) {
					if(BodyChanging.getTarget().getTailType()==TailType.FOX_MORPH_MAGIC && tail!=TailType.FOX_MORPH_MAGIC) {
						continue;
					}
					if(BodyChanging.getTarget().getTailType()!=TailType.FOX_MORPH_MAGIC && tail==TailType.FOX_MORPH_MAGIC) {
						continue;
					}
				}
				Colour c = PresetColour.TEXT_GREY;
				
				if(tail.getRace() != null) {
					c = tail.getRace().getColour();
				}

				boolean suitableForPenetration = (tail.getTags().contains(BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION) || Main.game.isFurryTailPenetrationContentEnabled())
						&& !tail.getTags().contains(BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION);
				
				if(BodyChanging.getTarget().getTailType() == tail) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"
									+Util.capitaliseSentence(tail.getTransformName())+(suitableForPenetration?"*":"")
									+(tail.isPrehensile()?"&#8314;":"")
									+(tail.isOvipositor()?"&deg;":"")
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TAIL_"+TailType.getIdFromTailType(tail)+"' class='cosmetics-button'><span style='color:"+c.getShades()[0]+";'>"
									+Util.capitaliseSentence(tail.getTransformName())+(suitableForPenetration?"*":"")
									+(tail.isPrehensile()?"&#8314;":"")
									+(tail.isOvipositor()?"&deg;":"")
								+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("Хвост",
				UtilText.parse(BodyChanging.getTarget(),
						"<i>Хвосты должны быть пригодны для проникновения (*), а также быть цепкими (&#8314;) или иметь высоту не менее 50% o футов [npc.namePos], чтобы их можно было использовать для проникновения в отверстия во время секса. Хвосты, оснащенные яйцекладом (&deg;), могут откладывать яйца в отверстия во время секса.</i>"),
				"TAIL_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTailLengthDiv() {
		if(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
			float percentageMinimum = BodyChanging.getTarget().isFeral()?Leg.LENGTH_PERCENTAGE_MIN_FERAL:Leg.LENGTH_PERCENTAGE_MIN;
			
			return applyFullVariableWrapper(Util.capitaliseSentence(LegConfiguration.TAIL_LONG.getName())+" Длина",
					UtilText.parse(BodyChanging.getTarget(),
						"Изменить длину [npc.namePos] [npc.tail]. Длина определяется как процент от [npc.namePos] роста и ограничена значениями от "+Math.round(percentageMinimum*100)+"% a до "+Math.round(Leg.LENGTH_PERCENTAGE_MAX*100)+"%."),
					"TAIL_LENGTH",
					"5%",
					"25%",
					Math.round(BodyChanging.getTarget().getLegTailLengthAsPercentageOfHeight()*100)+"%"
						+ "<br/>"
						+ Units.size(BodyChanging.getTarget().getLegTailLength(false)),
					BodyChanging.getTarget().getLegTailLengthAsPercentageOfHeight()<=percentageMinimum,
					BodyChanging.getTarget().getLegTailLengthAsPercentageOfHeight()>=Leg.LENGTH_PERCENTAGE_MAX);
			
		} else {
			return applyFullVariableWrapper("Длина хвоста",
					UtilText.parse(BodyChanging.getTarget(),
							BodyChanging.getTarget().hasTail()
								?"Изменить длину [npc.namePos] [npc.tail]. Длина определяется как процент от [npc.namePos] роста и ограничена значениями от "+Math.round(Tail.LENGTH_PERCENTAGE_MIN*100)+"% a до "+Math.round(Tail.LENGTH_PERCENTAGE_MAX*100)+"%."
								:"У [npc.Name] [npc.do] нет хвоста, поэтому длину изменить нельзя!"),
					"TAIL_LENGTH",
					"5%",
					"25%",
					Math.round(BodyChanging.getTarget().getTailLengthAsPercentageOfHeight()*100)+"%"
						+ "<br/>"
						+ Units.size(BodyChanging.getTarget().getTailLength(false))
						+ "<br/><i>Base Diameter: "+Units.size(BodyChanging.getTarget().getTailDiameter(0))+"</i>"
						+ "<br/><i>Tip Diameter: "+Units.size(BodyChanging.getTarget().getTailDiameter(BodyChanging.getTarget().getTailLength(false)))+"</i>",
					BodyChanging.getTarget().getTailLengthAsPercentageOfHeight()<=Tail.LENGTH_PERCENTAGE_MIN || !BodyChanging.getTarget().hasTail(),
					BodyChanging.getTarget().getTailLengthAsPercentageOfHeight()>=Tail.LENGTH_PERCENTAGE_MAX || !BodyChanging.getTarget().hasTail());
		}
	}
	
	public static String getSelfTransformTailCountDiv() {
		contentSB.setLength(0);

		if(!BodyChanging.getTarget().hasTail()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
						+ Util.capitaliseSentence("Ноль")
					+ "</div>");
			
		} else {
			for(int i=1; i <= Tail.MAXIMUM_COUNT; i++) {
				if(BodyChanging.getTarget().getTailCount() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
							+ "</div>");
					
				} else {
					if(!BodyChanging.isDebugMenu() && (BodyChanging.getTarget().isYouko() && i > BodyChanging.getTarget().getMaxTailCount())) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
									+ Util.capitaliseSentence(Util.intToString(i))
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='TAIL_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
								+ "</div>");
					}
				}
			}
		}
		
		return applyWrapper("Количество хвостов",
				UtilText.parse(BodyChanging.getTarget(),
						!BodyChanging.getTarget().hasTail()
							?"Так как у [npc.name] [npc.do] нет хвоста, [npc.she] не может изменить количество [npc.she] [npc.has]!"
							:((BodyChanging.getTarget().isYouko()
								?"Так как [npc.nameIsFull] — ёко, [npc.she] может изменить количество хвостов, которые [npc.she] [npc.verb(appear)]!"
								:"Изменить количество [npc.tails] у [npc.name] [npc.has].")
							+ "<br/><i>Количество хвостов учитывается при проверке возможности использования хвоста для проникновения во время секса.</i>")),
				"TAIL_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTailGirthDiv() {
		contentSB.setLength(0);

		if(!BodyChanging.getTarget().hasTail()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
						+ Util.capitaliseSentence("N/A")
					+ "</div>");
			
		} else {
			for(PenetrationGirth girth : PenetrationGirth.values()) {
				if(BodyChanging.getTarget().getTailGirth() == girth) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTailType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TAIL_GIRTH_"+girth+"' class='cosmetics-button'><span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTailType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("Толщина хвоста",
				UtilText.parse(BodyChanging.getTarget(),
						!BodyChanging.getTarget().hasTail()
							?("Так как у [npc.name] [npc.do] нет хвоста, [npc.she] не может изменить обхват хвоста [npc.her]!"
									+(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG
										?"<br/>(<i>Обхват змеиного хвоста [npc.her] зависит от размера бедер [npc.her].</i>)"
										:""))
							:("Изменить обхват [npc.namePos] [npc.tail].<br/><i>Обхват хвоста влияет на то, будет ли он слишком велик для отверстия, в которое он проникает.</i>")),
				"TAIL_GIRTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformTentacleLengthDiv() {
		return applyFullVariableWrapper("Длина щупальца",
				UtilText.parse(BodyChanging.getTarget(),
						BodyChanging.getTarget().hasTentacle()
							?"Изменить длину [npc.namePos] [npc.tentacles]. Она определяется как процент от роста [npc.namePos] и ограничена значениями от 100% a до 500%."
							:"У [npc.Name] [npc.do] нет щупалец, поэтому длину изменить нельзя!"),
				"TENTACLE_LENGTH",
				"5%",
				"25%",
				Math.round(BodyChanging.getTarget().getTentacleLengthAsPercentageOfHeight()*100)+"%"
					+ "<br/>"
					+ Units.size(BodyChanging.getTarget().getTentacleLength(false)),
				BodyChanging.getTarget().getTentacleLengthAsPercentageOfHeight()<=Tentacle.LENGTH_PERCENTAGE_MIN || !BodyChanging.getTarget().hasTentacle(),
				BodyChanging.getTarget().getTentacleLengthAsPercentageOfHeight()>=Tentacle.LENGTH_PERCENTAGE_MAX || !BodyChanging.getTarget().hasTentacle());
	}

	public static String getSelfTransformTentacleGirthDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasTentacle()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
						+ Util.capitaliseSentence("N/A")
					+ "</div>");
			
		} else {
			for(PenetrationGirth girth : PenetrationGirth.values()) {
				if(BodyChanging.getTarget().getTentacleGirth() == girth) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTentacleType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TENTACLE_GIRTH_"+girth+"' class='cosmetics-button'><span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTentacleType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("Обхват щупальца",
				UtilText.parse(BodyChanging.getTarget(),
						!BodyChanging.getTarget().hasTentacle()
							?"Так как у [npc.name] [npc.do] нет щупальца, [npc.she] не может изменить обхват щупальца [npc.her]!"
							:("Изменить обхват [npc.namePos] [npc.tentacle].<br/><i>Обхват щупальца влияет на то, будет ли оно слишком большим для отверстия, которое оно проникает.</i>")),
				"TENTACLE_GIRTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformWingSizeDiv() {
		GameCharacter target = BodyChanging.getTarget();
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasWings()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (WingSize wingSize : WingSize.values()) {
				if (target.getWingType().getMinimumSize().getValue()>wingSize.getValue()
						|| target.getWingType().getMaximumSize().getValue()<wingSize.getValue()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
									+Util.capitaliseSentence(wingSize.getName())
									+(wingSize.getValue()>=target.getLegConfiguration().getMinimumWingSizeForFlight(target.getBody()).getValue()?"*":"")
									+"</div>");
					
				} else {
					if (BodyChanging.getTarget().getWingSize() == wingSize) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
										+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(wingSize.getName())
										+(wingSize.getValue()>=target.getLegConfiguration().getMinimumWingSizeForFlight(target.getBody()).getValue()?"*":"")+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='WING_SIZE_"+wingSize+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(wingSize.getName())
										+(wingSize.getValue()>=target.getLegConfiguration().getMinimumWingSizeForFlight(target.getBody()).getValue()?"*":"")+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("Размер крыльев",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер крыльев [npc.namePos].<br/><i>Размер крыльев влияет на способность [npc.namePos] летать. Это зависит от конфигурации ног; подходящие размеры для тела [npc.namePos] '[npc.legConfiguration]' отмечены звездочкой. Некоторые типы крыльев не поддерживают все возможные размеры.</i>"),
				"WING_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformWingChoiceDiv(List<AbstractRace> availableRaces, boolean removeNone) {
		contentSB.setLength(0);
		
		List<AbstractWingType> sortedTypes = new ArrayList<>(WingType.getAllWingTypes());
		/*
		 * 'None' is first, then the rest of the 'no race' options.
		 * Then, the races are sorted alphabetically.
		 * Within a race (including 'no race'), options are sorted alphabetically.
		 */
		sortedTypes.sort(Comparator.comparingInt((AbstractWingType w) -> w == WingType.NONE ? 0 : 1)
				.thenComparingInt(w -> w.getRace() == Race.NONE ? 0 : 1)
				.thenComparing(w -> w.getRace().getName(false))
				.thenComparing(AbstractWingType::getTransformName));
		
		for(AbstractWingType wing : sortedTypes) {
			if((wing.getRace() !=null && availableRaces.contains(wing.getRace()))
					|| (!removeNone && wing==WingType.NONE)) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(wing.getRace() != null) {
					c = wing.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getWingType() == wing) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(wing.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='WING_"+WingType.getIdFromWingType(wing)+"' class='cosmetics-button'><span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(wing.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Крылья",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип крыльев [npc.namePos].<br/><i>Типы крыльев, позволяющие летать (если их размер также достаточно велик), отмечены звездочкой.</i>"),
				"WING_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformHornChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);

		Set<AbstractHornType> types = new HashSet<>();
		for(AbstractRace race : availableRaces) {
			types.addAll(RacialBody.valueOfRace(race).getHornTypes(false));
		}
		
		
		List<AbstractHornType> sortedTypes = new ArrayList<>(types);
		/*
		 * 'None' is first, then the rest of the 'no race' options.
		 * Then, the races are sorted alphabetically.
		 * Within a race (including 'no race'), options are sorted alphabetically.
		 */
		sortedTypes.sort(Comparator.comparingInt((AbstractHornType h) -> h == HornType.NONE ? 0 : 1)
				.thenComparingInt(h -> h.getRace() == Race.NONE ? 0 : 1)
				.thenComparing(h -> h.getRace().getName(false))
				.thenComparing(AbstractHornType::getTransformName));
		
		for(AbstractHornType horn : sortedTypes) {
			if((horn.getRace()!=null && availableRaces.contains(horn.getRace()))
					|| horn.equals(HornType.NONE)) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(horn.getRace() != null) {
					c = horn.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getHornType().equals(horn)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(horn.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_"+HornType.getIdFromHornType(horn)+"' class='cosmetics-button'><span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(horn.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Рога",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип рогов [npc.namePos].<br/><i>Рога достаточной длины можно использовать как опору для некоторых сексуальных актов, а также по ним определяют подвид.</i>"),
				"HORN_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHornSizeDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasHorns()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (HornLength hornLength : HornLength.values()) {
				if (HornLength.getLengthFromInt(BodyChanging.getTarget().getHornLengthValue()) == hornLength) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(hornLength.getDescriptor())+(hornLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_LENGTH_"+hornLength+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(hornLength.getDescriptor())+(hornLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Длина рогов",
				UtilText.parse(BodyChanging.getTarget(), "Изменить длину рогов [npc.namePos].<br/><i>Рога достаточной длины можно использовать как опору для некоторых сексуальных актов (отмеченных звездочкой), а также по ним определяют подвид.</i>"),
				"HORN_LENGTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHornCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasHorns()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Horn.MAXIMUM_ROWS; i++) {
				if (BodyChanging.getTarget().getHornRows() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Количество рядов рогов",
				UtilText.parse(BodyChanging.getTarget(), "Изменить количество пар рогов у [npc.horns] [npc.name].<br/><i>Это в основном косметическое изменение, но оно также учитывается при определении подвида (например, у единорогов всего один «ряд» из одного рога).</i>"),
				"HORN_COUNT",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHornsPerRowCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasHorns()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Horn.MAXIMUM_HORNS_PER_ROW; i++) {
				if (BodyChanging.getTarget().getHornsPerRow() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_COUNT_PER_ROW_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}
		
		return applyWrapper("Количество рогов в ряду",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество [npc.horns] в каждом ряду.<br/><i>Это в основном косметическое изменение, но оно также учитывается при определении подвида (например, у единорогов один «ряд» из одного рога).</i>"),
				"HORN_COUNT_ROWS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAntennaChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractAntennaType antenna : AntennaType.getAllAntennaTypes()) {
			if((antenna.getRace()!=null && availableRaces.contains(antenna.getRace())) || antenna==AntennaType.NONE) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(antenna.getRace() != null) {
					c = antenna.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getAntennaType() == antenna) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(antenna.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_"+AntennaType.getIdFromAntennaType(antenna)+"' class='cosmetics-button'><span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(antenna.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Усики",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип антенн [npc.namePos].<br/><i>Используется только для определения подвида.</i>"),
				"ANTENNAE_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAntennaSizeDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasAntennae()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (HornLength antennaLength : HornLength.values()) {
				if (HornLength.getLengthFromInt(BodyChanging.getTarget().getAntennaLength()) == antennaLength) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(antennaLength.getDescriptor())+(antennaLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_LENGTH_"+antennaLength+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(antennaLength.getDescriptor())+(antennaLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Длина антенн",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет длину антенн [npc.namePos].<br/><i>Достаточно длинные антенны могут использоваться как ручки для некоторых сексуальных действий (отмечены звездочкой), а также используются для определения подвида.</i>"),
				"ANTENNA_LENGTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAntennaCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasAntennae()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Antenna.MAXIMUM_ROWS; i++) {
				if (BodyChanging.getTarget().getAntennaRows() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Количество рядов антенн",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество пар [npc.name] у [npc.antennae].<br/><i>Это в основном косметическое изменение, но оно также учитывается при определении подвидов (например, у единорогов всего один «ряд» из одной антенны).</i>"),
				"ANTENNA_COUNT",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAntennaePerRowCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasAntennae()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Antenna.MAXIMUM_ANTENNAE_PER_ROW; i++) {
				if (BodyChanging.getTarget().getAntennaePerRow() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_COUNT_PER_ROW_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}
		
		return applyWrapper("Количество антенн в ряду",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество [npc.antennae] в каждом ряду.<br/><i>Это в основном косметическое изменение, но оно также учитывается при определении подвидов (например, у единорогов всего один «ряд» из одной антенны).</i>"),
				"ANTENNA_COUNT_ROWS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHairChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractHairType hair : HairType.getAllHairTypes()) {
			if((hair.getRace() !=null && availableRaces.contains(hair.getRace()))) {
				if(BodyChanging.getTarget().getHairType()==hair) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+hair.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hair.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='HAIR_"+HairType.getIdFromHairType(hair)+"' class='cosmetics-button'><span style='color:"+hair.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hair.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Волосы",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип волос [npc.namePos].<br/><i>Используется только для идентификации подвидов.</i>"),
				"HAIR_TYPE",
				contentSB.toString(),
				false);
	}

	public static String getSelfTransformHairLengthDiv() {
		contentSB.setLength(0);
		
		for(HairLength hairLength : HairLength.values()) {
			if(BodyChanging.getTarget().getHairLength()==hairLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HAIR_LENGTH_"+hairLength+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Длина волос",
				UtilText.parse(BodyChanging.getTarget(), "Изменить длину [npc.hair(true)] [npc.namePos].<br/><i>Волосы достаточной длины (отмеченные звездочкой) можно будет оттягивать во время некоторых сексуальных актов.</i>"),
				"HAIR_LENGTH",
				contentSB.toString(),
				true);
	}

	public static String getNeckFluffDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isNeckFluff()) {
			contentSB.append(
					"<div id='NECK_FLUFF_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.TRANSFORMATION_SHRINK.getShades()[0]+";'>Без шейной шерсти</span></div><div class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_GROW.toWebHexString()+";'>Шейная шерсть</span></div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SHRINK.toWebHexString()+";'>Без шейной шерсти</span></div><div id='NECK_FLUFF_ON' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GROW.getShades()[0]+";'>Шейная шерсть</span></div>");
		}
		

		return applyWrapper("Шейная шерсть",
				UtilText.parse(BodyChanging.getTarget(), "Установить, будет ли у [npc.name] [npc.has] много дополнительной шерсти вокруг [npc.her] на шее и верхней части груди.<br/><i>Это чисто косметическое изменение.</i>"),
				"NECK_FLUFF",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfDivHairStyles(String title, String description) {
		contentSB.setLength(0);

		for (HairStyle hairStyle : HairStyle.values()) {
			if (BodyChanging.getTarget().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
						+ "</div>");
			} else {
				if(BodyChanging.getTarget().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + ")]"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Прическа",
				description
					+ "<br/><i>'"+Util.capitaliseSentence(HairStyle.TWIN_TAILS.getName(BodyChanging.getTarget()))+"' и '"+Util.capitaliseSentence(HairStyle.TWIN_BRAIDS.getName(BodyChanging.getTarget()))+"' можно использовать как захваты в некоторых секс-сценах.</i>",
				"HAIR_STYLE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformAssChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractAssType ass : AssType.getAllAssTypes()) {
			if((ass.getRace() !=null && availableRaces.contains(ass.getRace()))) {
				
				if(BodyChanging.getTarget().getAssType().equals(ass)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+ass.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ass.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ASS_"+AssType.getIdFromAssType(ass)+"' class='cosmetics-button'><span style='color:"+ass.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ass.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Анус",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип ануса [npc.namePos].<br/><i>Тип ануса влияет на модификаторы ануса, с которыми появляются персонажи, а также на описания и идентификацию подвидов.</i>"),
				"ASSHOLE_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformBreastChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractBreastType breast : BreastType.getAllBreastTypes()) {
			if((breast.getRace() !=null && breast.getRace()!=Race.NONE && availableRaces.contains(breast.getRace()))) {
				if(BodyChanging.getTarget().getBreastType() == breast) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+breast.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_"+BreastType.getIdFromBreastType(breast)+"' class='cosmetics-button'><span style='color:"+breast.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Соски",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип сосков [npc.namePos].<br/><i>Тип сосков влияет на модификаторы сосков, с которыми появляются персонажи, а также на тип молока, которое вырабатывают [npc.her] груди. Это также используется для описаний персонажей и идентификации подвидов.</i>"),
				"NIPPLE_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformBreastCrotchChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractBreastType breast : BreastType.getAllBreastTypes()) {
			if((breast.getRace()==Race.NONE || availableRaces.contains(breast.getRace()))) {
				if(BodyChanging.getTarget().getBreastCrotchType() == breast) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+breast.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_CROTCH_"+BreastType.getIdFromBreastType(breast)+"' class='cosmetics-button'><span style='color:"+breast.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper(Util.capitaliseSentence(getCrotchBoobName(false))+" Тип",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип сосков у [npc.namePos] "+getCrotchBoobName(true)
						+ "<br/><i>Тип паховых сосков влияет на модификаторы сосков, с которыми появляются персонажи, а также на тип молока, которое производит [npc.her] "+getCrotchBoobName(true)+" </i>. Это также используется для описаний персонажей и идентификации подвидов.</i>"),
				"CROTCH_NIPPLE_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformArmChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractArmType arm : ArmType.getAllArmTypes()) {
			if(arm.getRace() !=null && availableRaces.contains(arm.getRace())) {
				if(BodyChanging.getTarget().getArmType().equals(arm)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+arm.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ARM_"+ArmType.getIdFromArmType(arm)+"' class='cosmetics-button'><span style='color:"+arm.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Руки",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип рук [npc.namePos].<br/><i>Тип рук используется только для описаний персонажей и идентификации подвидов.</i>"),
				"ARM_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformArmCountDiv() {
		contentSB.setLength(0);
		
		for(int i=1; i <= Arm.MAXIMUM_ROWS; i++) {
			if(BodyChanging.getTarget().getArmRows() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ARM_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Количество рядов рук",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество пар [npc.name] у [npc.arms].<br/><i>Количество рук используется в описаниях персонажей и для определения подвидов, а также определяет наличие свободных рук для секса.</i>"),
				"ARM_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLegChoiceDiv(List<AbstractRace> availableRaces, boolean bypassRestrictions) {
		contentSB.setLength(0);
		
		for(AbstractLegType leg : LegType.getAllLegTypes()) {
			if((leg.isAvailableForSelfTransformMenu(BodyChanging.getTarget()) || bypassRestrictions) && leg.getRace()!=null && availableRaces.contains(leg.getRace())) {
				if(BodyChanging.getTarget().getLegType().equals(leg)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+leg.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='LEG_"+LegType.getIdFromLegType(leg)+"' class='cosmetics-button'><span style='color:"+leg.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Ноги",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип ног [npc.namePos].<br/><i>Тип ног используется в описаниях персонажей и для определения подвидов. Разные типы ног имеют разные доступные конфигурации ног.</i>"),
				"LEG_TYPE",
				contentSB.toString(),
				true);
			
	}
	
	public static String getSelfTransformFootStructureChoiceDiv() {
		contentSB.setLength(0);
		
		for(FootStructure footStructure : FootStructure.values()) {
			if(BodyChanging.getTarget().getLegType().getFootType().getPermittedFootStructures(BodyChanging.getTarget().getLegConfiguration()).contains(footStructure)) {
				if(BodyChanging.getTarget().getFootStructure() == footStructure) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(footStructure.getName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='FOOT_STRUCTURE_"+footStructure+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(footStructure.getName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Строение стоп",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет строение [npc.feet] у [npc.namePos].<br/><i>Тип стоп используется как в описаниях персонажей, так и в описаниях секса, а также может ограничивать доступную обувь.</i>"),
				"FOOT_STRUCTURE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformFaceChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractFaceType face : FaceType.getAllFaceTypes()) {
			if(face.getRace() !=null && availableRaces.contains(face.getRace())) {
				if(BodyChanging.getTarget().getFaceType() == face) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+face.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(face.getTransformName())+(face.getTags().contains(BodyPartTag.THERMAL_VISION)?"*":"")+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='FACE_"+FaceType.getIdFromFaceType(face)+"' class='cosmetics-button'><span style='color:"+face.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(face.getTransformName())+(face.getTags().contains(BodyPartTag.THERMAL_VISION)?"*":"")+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Лицо",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип лица [npc.namePos].<br/><i>Тип лица определяет форму рта и языка. Он также определяет подвид и может даровать способность к тепловому зрению (отмечено звездочкой).</i>"),
				"FACE_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformBodyChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractTorsoType skin : TorsoType.getAllTorsoTypes()) {
			if(availableRaces.contains(skin.getRace())) {
				if(BodyChanging.getTarget().getTorsoType() == skin) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+skin.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(skin.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TORSO_"+TorsoType.getIdFromTorsoType(skin)+"' class='cosmetics-button'><span style='color:"+skin.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(skin.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Тело",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип тела [npc.namePos].<br/><i>Тип тела влияет на то, какое покрытие будет у тела [npc.namePos]: от кожи или меха до чешуи или перьев.</i>"),
				"BODY_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformGenitalArrangementChoiceDiv() {
		contentSB.setLength(0);
		
		for(GenitalArrangement arrangement : GenitalArrangement.values()) {
//			if(arrangement!=GenitalArrangement.CLOACA_BEHIND) {
				if(BodyChanging.getTarget().getLegConfiguration().getAvailableGenitalConfigurations().contains(arrangement) || BodyChanging.getTarget().getGenitalArrangement()==arrangement) {
					if(BodyChanging.getTarget().getGenitalArrangement() == arrangement) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>"+Util.capitaliseSentence(arrangement.getName())+"</span>"
								+ "</div>");
						
					} else if(!Main.getProperties().hasValue(PropertyValue.bipedalCloaca) && BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.BIPEDAL && arrangement!=GenitalArrangement.NORMAL) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
									+ "<span style='color:"+PresetColour.BASE_GREY.getShades()[0]+";'>"+Util.capitaliseSentence(arrangement.getName())+"</span>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='GENITAL_ARRANGEMENT_"+arrangement+"' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_SEX.getShades()[0]+";'>"+Util.capitaliseSentence(arrangement.getName())+"</span>"
								+ "</div>");
					}
				}
//			}
		}

		return applyWrapper("Расположение гениталий",
				UtilText.parse(BodyChanging.getTarget(),
						"Изменить конфигурацию гениталий [npc.namePos].<br/><i>Это определяет расположение гениталий и ануса [npc.namePos]. Доступно только '"+GenitalArrangement.NORMAL.getName()+"' для большинства конфигураций ног.</i>"
						+ (!Main.getProperties().hasValue(PropertyValue.bipedalCloaca)
							?" [style.italicsDisabled(Двустворчатые клоаки отключены в ваших настройках контента.)]"
							:"")),
				"GENITAL_ARRANGEMENT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLegConfigurationChoiceDiv() {
		contentSB.setLength(0);
		
		for(LegConfiguration legConfig : LegConfiguration.values()) {
			if(BodyChanging.getTarget().getLegType().isLegConfigurationAvailable(legConfig)) {
				if(BodyChanging.getTarget().getLegConfiguration() == legConfig) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.RACE_BESTIAL.toWebHexString()+";'>"+Util.capitaliseSentence(legConfig.getName())+(!legConfig.isAbleToGrowTail()?"*":"")+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='LEG_CONFIGURATION_"+legConfig+"' class='cosmetics-button'><span style='color:"+PresetColour.RACE_BESTIAL.getShades()[0]+";'>"+Util.capitaliseSentence(legConfig.getName())+(!legConfig.isAbleToGrowTail()?"*":"")+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("Конфигурация ног",
				UtilText.parse(BodyChanging.getTarget(), "Измените облик нижней части тела [npc.namePos].<br/><i>Применение этого изменения трансформирует все части тела ниже талии! Некоторые конфигурации ног (отмеченные звездочкой) не позволят [npc.namePos] отрастить хвост!</i>"),
				"LEG_CONFIGURATION",
				contentSB.toString(),
				true);
	}
	
	
	public static String getSelfTransformEarChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractEarType ear : EarType.getAllEarTypes()) {
			if(availableRaces.contains(ear.getRace())) {
				if(BodyChanging.getTarget().getEarType().equals(ear)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+ear.getRace().getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(ear.getTransformName())
									+(ear.isAbleToBeUsedAsHandlesInSex()?"*":"")
									+(ear.getTags().contains(BodyPartTag.ECHO_LOCATION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='EAR_"+EarType.getIdFromEarType(ear)+"' class='cosmetics-button'><span style='color:"+ear.getRace().getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(ear.getTransformName())
									+(ear.isAbleToBeUsedAsHandlesInSex()?"*":"")
									+(ear.getTags().contains(BodyPartTag.ECHO_LOCATION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Уши",
				UtilText.parse(BodyChanging.getTarget(), "Измените тип ушей [npc.namePos].<br/><i>Тип ушей помогает определить подвид. Некоторые из них достаточно длинные, чтобы их можно было тянуть во время секса (*), или они могут даровать эхолокацию (&#8314;).</i>"),
				"EAR_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformEyeChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractEyeType eye : EyeType.getAllEyeTypes()) {
			if(availableRaces.contains(eye.getRace())) {
				if(BodyChanging.getTarget().getEyeType() == eye) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+eye.getRace().getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(eye.getTransformName())
									+(eye.getTags().contains(BodyPartTag.NIGHT_VISION)?"*":"")
									+(eye.getTags().contains(BodyPartTag.EYE_PERFECT_VISION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='EYE_"+EyeType.getIdFromEyeType(eye)+"' class='cosmetics-button'><span style='color:"+eye.getRace().getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(eye.getTransformName())
									+(eye.getTags().contains(BodyPartTag.NIGHT_VISION)?"*":"")
									+(eye.getTags().contains(BodyPartTag.EYE_PERFECT_VISION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Глаза",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип глаз [npc.namePos].<br/><i>Тип глаз определяет форму радужки и зрачка у персонажей, используется для идентификации подвидов, а также может даровать ночное зрение (*) или идеальное зрение (&#8314;).</i>"),
				"EYE_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformIrisChoiceDiv() {
		contentSB.setLength(0);
		
		for(EyeShape eyeShape : EyeShape.values()) {
			if(BodyChanging.getTarget().getIrisShape() == eyeShape) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='IRIS_SHAPE_"+eyeShape+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Форма радужки",
				UtilText.parse(BodyChanging.getTarget(), "Изменить форму радужки [npc.namePos].<br/><i>Это чисто косметическое изменение.</i>"),
				"IRIS_SHAPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformPupilChoiceDiv() {
		contentSB.setLength(0);
		
		for(EyeShape eyeShape : EyeShape.values()) {
			if(BodyChanging.getTarget().getPupilShape() == eyeShape) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PUPIL_SHAPE_"+eyeShape+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Форма зрачка",
				UtilText.parse(BodyChanging.getTarget(), "Изменить форму зрачка [npc.namePos].<br/><i>Это чисто косметическое изменение.</i>"),
				"PUPIL_SHAPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformEyeCountDiv() {
		contentSB.setLength(0);
		
		for(int i=1; i <= Eye.MAXIMUM_PAIRS; i++) {
			if(BodyChanging.getTarget().getEyePairs() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='EYE_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Количество пар глаз",
				UtilText.parse(BodyChanging.getTarget(), "Измените количество пар [npc.eyes] [npc.name] [npc.has].<br/><i>Это чисто косметическое изменение.</i>"),
				"EYE_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLipSizeDiv() {
		contentSB.setLength(0);
		
		for(LipSize lipSize : LipSize.values()) {
			if(BodyChanging.getTarget().getLipSize() == lipSize) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(lipSize.getName())+(lipSize.isImpedesSpeech()&&Main.game.isLipLispEnabled()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LIP_SIZE_"+lipSize+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(lipSize.getName())+(lipSize.isImpedesSpeech()&&Main.game.isLipLispEnabled()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер губ",
				UtilText.parse(BodyChanging.getTarget(), "Измените размер [npc.namePos] губ."
						+ (Main.game.isLipLispEnabled()
								?"<br/><i>Хотя это в основном косметическое изменение, очень большой размер губ (отмеченный звездочкой) также заставит [npc.name] говорить с шепелявостью.</i>"
								:"<br/><i>Это чисто косметическое изменение, так как контент «Шепелявость губ» отключен.</i>")),
				"LIP_SIZE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformThroatModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div  id='THROAT_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы губ и горла",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы губ и горла [npc.namePos].<br/><i>Эти модификаторы влияют на описания во время орального секса.</i>"),
				"THROAT_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getFaceWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='THROAT_WETNESS_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Влажность горла",
				UtilText.parse(BodyChanging.getTarget(), "Изменить влажность горла [npc.namePos].<br/><i>Это влияет на удовольствие во время секса, так как недостаточно смазанные отверстия отрицательно сказываются на приросте возбуждения.</i>"
							+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
									?"<br/>[style.italicsWetness8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'!)]"
									:"")),
				"THROAT_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getFaceCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Вместимость горла",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость горла [npc.namePos].<br/><i>Это влияет на размер объектов для проникновения, которые может принять горло [npc.namePos]. Если вместимость слишком мала или слишком велика для объекта, это влияет на прирост возбуждения.</i>"),
				"THROAT_CAPACITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformThroatDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getFaceDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='THROAT_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Глубина горла",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину горла [npc.namePos].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно принять.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"THROAT_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getFaceElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Эластичность горла",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет эластичность горла [npc.namePos].<br/><i>Это определяет, насколько быстро горло [npc.namePos] растянется, если проникающий объект окажется слишком толстым для него."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину горла [npc.her].":""))
						+"</i>",
				"THROAT_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getFacePlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Пластичность горла",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет пластичность горла [npc.namePos].<br/><i>Пластичность отверстия определяет, насколько быстро и насколько сильно оно восстанавливает свою первоначальную тесноту после растяжения.</i>"),
				"THROAT_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTongueModifiersDiv() {
		contentSB.setLength(0);
		
		for(TongueModifier tongueMod : TongueModifier.values()) {
			if(BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
				contentSB.append(
						"<div  id='TONGUE_MOD_"+tongueMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(tongueMod.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TONGUE_MOD_"+tongueMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(tongueMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы языка",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы языка [npc.namePos].<br/><i>Модификаторы языка влияют на описания во время орального секса.</i>"),
				"TONGUE_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTongueSizeDiv() {
		contentSB.setLength(0);
		
		for(TongueLength tongueLength : TongueLength.values()) {
			if(BodyChanging.getTarget().getTongueLength() == tongueLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(tongueLength.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TONGUE_LENGTH_"+tongueLength+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(tongueLength.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Длина языка",
				UtilText.parse(BodyChanging.getTarget(), "Изменить длину языка [npc.namePos].<br/><i>Это влияет на описания во время орального секса.</i>"),
				"TONGUE_LENGTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAssSizeDiv() {
		contentSB.setLength(0);
		
		for(AssSize as : AssSize.values()) {
			if(BodyChanging.getTarget().getAssSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ASS_SIZE_"+as+"' class='cosmetics-button'><span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер задницы [npc.namePos].<br/><i>Это чисто косметическое изменение.</i>"),
				"ASS_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformHipSizeDiv() {
		contentSB.setLength(0);
		
		for(HipSize hs : HipSize.values()) {
			if(BodyChanging.getTarget().getHipSize() == hs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+hs.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HIP_SIZE_"+hs+"' class='cosmetics-button'><span style='color:"+hs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер бедер",
				UtilText.parse(BodyChanging.getTarget(),
						"Изменить размер бедер [npc.namePos]."
						+ (BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG
								?"<br/>Это влияет на обхват змеиного хвоста [npc.her]."
								:"<br/>Если у [npc.she] когда-нибудь появится нижняя часть тела в виде змеиного хвоста, это повлияет на его обхват.")),
				"HIP_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getAssWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ANUS_WETNESS_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Влажность ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить влажность ануса [npc.namePos].<br/><i>Это влияет на удовольствие во время секса, так как недостаточно смазанные отверстия отрицательно сказываются на приросте возбуждения.</i>"
						+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
							?"<br/>[style.italicsWetness8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'!)]"
							:"")),
				"ASSHOLE_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getAssCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Вместимость ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость ануса [npc.namePos].<br/><i>Это влияет на размер предметов для проникновения, которые может принять анус [npc.namePos]. Если вместимость слишком мала или слишком велика для предмета, это влияет на прирост возбуждения.</i>"),
				"ASSHOLE_CAPACITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAnusDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getAssDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ANUS_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Глубина ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину ануса [npc.namePos].<br/><i>Глубина отверстия определяет длину предмета для проникновения, который может комфортно вместить в себя анус.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"ANUS_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getAssElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Эластичность ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет эластичность ануса [npc.namePos].<br/><i>Этот параметр определяет, насколько быстро анус [npc.namePos] растянется, если проникающий объект окажется слишком толстым для него."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину ануса [npc.her].":""))
						+"</i>",
				"ASSHOLE_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getAssPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Пластичность ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет пластичность ануса [npc.namePos].<br/><i>Пластичность отверстия определяет, насколько быстро и насколько сильно оно восстанавливает свою первоначальную узкость после растяжения.</i>"),
				"ASSHOLE_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div  id='ANUS_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы ануса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы ануса [npc.namePos].<br/><i>Модификаторы отверстия влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"ANUS_MOD",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformBreastSizeDiv() {
		return applyFullVariableWrapper("Размер груди",
				BodyChanging.getTarget().isPlayer()
					?"Изменить размер вашей груди."
					:UtilText.parse(BodyChanging.getTarget(), "Изменить размер груди [npc.namePos]."),
				"BREAST_SIZE",
				"1",
				"5",
				BodyChanging.getTarget().getBreastRawSizeValue()
					+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastSize().getCupSizeName())+(BodyChanging.getTarget().getBreastSize()==CupSize.FLAT?"":"-cup")+"</i>",
				BodyChanging.getTarget().getBreastRawSizeValue()<=0
					|| (BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE)!=null),
				BodyChanging.getTarget().getBreastRawSizeValue()>=CupSize.XXX_N.getMeasurement());
	}
	
	// This is to stop variable-length names from being generated every time a button is clicked (i.e. stop "crotch-tits", "crotch-breasts")
	private static String getCrotchBoobName(boolean plural) {
		if(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS) {
			return plural?"udders":"udder";
		} else {
			return plural?"crotch-boobs":"crotch-boob";
		}
	}
	
	public static String getSelfTransformBreastCrotchSizeDiv() {
		return applyFullVariableWrapper(
				UtilText.parse(BodyChanging.getTarget(), Util.capitaliseSentence(getCrotchBoobName(false))+" Размер"),
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер [npc.namePos] "+getCrotchBoobName(true)+"."),
				"BREAST_CROTCH_SIZE",
				" cup",
				" cup",
				Util.capitaliseSentence(BodyChanging.getTarget().getBreastCrotchSize().getCupSizeName())
					+"<br/>("+BodyChanging.getTarget().getBreastCrotchRawSizeValue()+")",
				BodyChanging.getTarget().getBreastCrotchRawSizeValue()<=0
						|| (BodyChanging.getTarget().getBreastCrotchRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null),
				BodyChanging.getTarget().getBreastCrotchRawSizeValue()>=CupSize.XXX_N.getMeasurement());
	}
	
	public static String getSelfTransformBreastShapeDiv() {
		contentSB.setLength(0);
		
		for(BreastShape bs : BreastShape.values()) {
			if(!bs.isRestrictedToCrotchBoobs()) {
				if(BodyChanging.getTarget().getBreastShape() == bs) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_SHAPE_"+bs+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Форма груди",
				UtilText.parse(BodyChanging.getTarget(), "Изменить форму груди [npc.namePos].<br/><i>Это в основном косметическое изменение, но оно также влияет на некоторые описания во время секса.</i>"),
				"BREAST_SHAPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformBreastCrotchShapeDiv() {
		contentSB.setLength(0);
		
		for(BreastShape bs : BreastShape.values()) {
			if(BodyChanging.getTarget().getBreastCrotchShape() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_CROTCH_SHAPE_"+bs+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper(
				UtilText.parse(BodyChanging.getTarget(), Util.capitaliseSentence(getCrotchBoobName(false))+" Форма"),
				UtilText.parse(BodyChanging.getTarget(), "Изменить форму [npc.namePos] "+getCrotchBoobName(true)+".<br/><i>Это в основном косметическое изменение, но оно также влияет на некоторые описания во время секса.</i>"),
				"BREAST_CROTCH_SHAPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformBreastRowsDiv() {
		contentSB.setLength(0);
		
		if (Main.getProperties().multiBreasts == 0) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Один")
							+"</div>");
		} else {
			for (int i = 1; i<=Breast.MAXIMUM_BREAST_ROWS; i++) {
				if (BodyChanging.getTarget().getBreastRows() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Пары груди",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество пар груди, которые [npc.name] [npc.has].<br/><i>Это в основном косметическое изменение, но оно также учитывается при определении наличия свободных сосков для использования во время секса.</i>"
						+(Main.getProperties().multiBreasts == 0
							?"<br/>[style.italicsBad(Множественная грудь отключена в настройках контента!)]"
							:"")),
				"BREAST_ROWS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformBreastCrotchRowsDiv() {
		contentSB.setLength(0);
		
		int minimum = 1;
		if(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS) {
			minimum = 0;
		}
		
		for(int i=minimum; i <= BreastCrotch.MAXIMUM_BREAST_ROWS; i++) {
			String name = Util.capitaliseSentence(Util.intToString(i));
			if(i==0) {
				name = "(Одиночная)";
			}
			if(BodyChanging.getTarget().getBreastCrotchRows()==i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+name+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_CROTCH_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+name+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper(
				UtilText.parse(BodyChanging.getTarget(), Util.capitaliseSentence(getCrotchBoobName(false))+" Пары"),
				UtilText.parse(BodyChanging.getTarget(), "Изменить количество пар "+getCrotchBoobName(false)+" [npc.name] [npc.has].<br/><i>Это в основном косметическое изменение, но оно также учитывается при определении наличия свободных сосков в паху для использования во время секса.</i>"),
				"BREAST_CROTCH_ROWS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCountDiv() {
		contentSB.setLength(0);

		int minimum = 1;
		
		for(int i=minimum; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			if(BodyChanging.getTarget().getNippleCountPerBreast() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Количество сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество сосков, которое [npc.name] имеет на каждой груди.<br/><i>Это в первую очередь косметическое изменение, но оно также влияет на описания во время секса.</i>"),
				"NIPPLE_COUNT",
				contentSB.toString(),true);
	}

	public static String getSelfTransformNippleCrotchCountDiv() {
		contentSB.setLength(0);

		int minimum = 1;
		if(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS && BodyChanging.getTarget().getBreastCrotchRows()==0) {
			minimum = 2;
		}
		
		for(int i=minimum; i <= BreastCrotch.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			if(BodyChanging.getTarget().getNippleCrotchCountPerBreast() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_CROTCH_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Количество сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить количество сосков у [npc.name] [npc.has] на каждой "+getCrotchBoobName(false)+".<br/><i>Это в первую очередь косметическое изменение, но оно также влияет на описания во время секса.</i>"),
				"NIPPLE_CROTCH_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleSizeDiv() {
		contentSB.setLength(0);
		
		for(NippleSize ns : NippleSize.values()) {
			if(BodyChanging.getTarget().getNippleSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_SIZE_"+ns+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер [npc.namePos] сосков.<br/><i>Это в основном косметическое изменение, но оно также влияет на описания во время секса.</i>"),
				"NIPPLE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchSizeDiv() {
		contentSB.setLength(0);
		
		for(NippleSize ns : NippleSize.values()) {
			if(BodyChanging.getTarget().getNippleCrotchSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_CROTCH_SIZE_"+ns+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер [npc.namePos] [npc.crotchNipples].<br/><i>Это в основном косметическое изменение, но оно также влияет на описания во время секса.</i>"),
				"NIPPLE_CROTCH_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAreolaeSizeDiv() {
		contentSB.setLength(0);
		
		for(AreolaeSize as : AreolaeSize.values()) {
			if(BodyChanging.getTarget().getAreolaeSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_SIZE_"+as+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер ареол",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер [npc.namePos] ареол.<br/><i>Это в основном косметическое изменение, но оно также влияет на описания во время секса.</i>"),
				"AREOLAE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAreolaeCrotchSizeDiv() {
		contentSB.setLength(0);
		
		for(AreolaeSize as : AreolaeSize.values()) {
			if(BodyChanging.getTarget().getAreolaeCrotchSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_CROTCH_SIZE_"+as+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер ареол",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер [npc.namePos] ареол вокруг [npc.her] [npc.crotchNipples].<br/><i>Это в основном косметическое изменение, но оно также влияет на описания во время секса.</i>"),
				"CROTCH_AREOLAE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleShapeDiv() {
		contentSB.setLength(0);
		
		for(NippleShape ns : NippleShape.values()) {
			if(BodyChanging.isDebugMenu() || !ns.isAssociatedWithPenetrationContent() || Main.game.isNipplePenEnabled()) {
				if(BodyChanging.getTarget().getNippleShape() == ns) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");

				} else {
					contentSB.append(
							"<div id='NIPPLE_SHAPE_"+ns+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");
				}
			}
		}

		return applyWrapper("Форма сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить форму [npc.namePos] сосков.<br/><i>Это в основном косметическое изменение, но оно также влияет на описания и доступность некоторых действий во время секса.</i>"),
				"NIPPLE_SHAPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCrotchShapeDiv() {
		contentSB.setLength(0);
		
		for(NippleShape ns : NippleShape.values()) {
			if(BodyChanging.isDebugMenu() || !ns.isAssociatedWithPenetrationContent() || Main.game.isNipplePenEnabled()) {
				if(BodyChanging.getTarget().getNippleCrotchShape() == ns) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");

				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_SHAPE_"+ns+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");
				}
			}
		}

		return applyWrapper("Форма клитора",
				UtilText.parse(BodyChanging.getTarget(), "Изменить форму [npc.namePos] [npc.crotchNipples].<br/><i>Это в основном косметическое изменение, но оно также влияет на описания и доступность некоторых действий во время секса.</i>"),
				"NIPPLE_CROTCH_SHAPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getNippleCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Вместимость сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость [npc.namePos] сосков.<br/><i>Любое значение, отличное от нуля, позволяет [npc.namePos] соски проникать во время секса. (Зависит от ваших предпочтений в настройках контента для «проникновения в соски».)</i>"),
				"NIPPLE_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getNippleCrotchCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Вместимость сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость [npc.namePos] [npc.crotchNipples].<br/><i>Любое значение, отличное от нуля, позволяет [npc.namePos] соскам проникать во время секса. (Зависит от вашего предпочтения «проникновение в соски» в настройках контента.)</i>"),
				"NIPPLE_CROTCH_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static int getLactationUpperLimit() {
		if (Main.game.isInNewWorld()) {
			return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
		} else {
			return 150;
		}
	}
	
	public static String getSelfTransformLactationDiv() {
		String title = "Лактация";
		String description = UtilText.parse(BodyChanging.getTarget(), "Изменить максимальное количество молока, которое [npc.name] может хранить в [npc.her] [npc.breasts].<br/><i>После опорожнения, грудь [npc.namePos] наполняется молоком до этого значения со скоростью, определяемой значением регенерации молока [npc.her].</i>");
		String id = "MILK_PRODUCTION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastRawMilkStorageValue(), ValueType.PRECISE)
							+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastMilkStorage().getName())+"</i>",
					BodyChanging.getTarget().getBreastRawMilkStorageValue()<=0,
					BodyChanging.getTarget().getBreastRawMilkStorageValue()>=getLactationUpperLimit(),
					FLUID_INCREMENT_SMALL,
					FLUID_INCREMENT_AVERAGE,
					FLUID_INCREMENT_LARGE,
					FLUID_INCREMENT_HUGE);
		}
	}
	
	public static int getLactationCrotchUpperLimit() {
		return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
	}
	
	public static String getSelfTransformLactationCrotchDiv() {
		String title = "Лактация";
		String description = UtilText.parse(BodyChanging.getTarget(), "Изменить максимальное количество молока, которое [npc.name] может хранить в [npc.her] [npc.crotchBoobs].<br/><i>После опорожнения, [npc.namePos] [npc.crotchBoobs] наполняются молоком до этого значения со скоростью, определяемой значением регенерации пахового молока [npc.her].</i>");
		String id = "MILK_CROTCH_PRODUCTION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue(), ValueType.PRECISE)
							+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastCrotchMilkStorage().getName())+"</i>",
					BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()<=0,
					BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()>=getLactationCrotchUpperLimit(),
					FLUID_INCREMENT_SMALL,
					FLUID_INCREMENT_AVERAGE,
					FLUID_INCREMENT_LARGE,
					FLUID_INCREMENT_HUGE);
		}
	}
	
	public static String getSelfTransformLactationRegenerationDiv() {
		String title = "Регенерация молока на одну грудь";
		String description = UtilText.parse(BodyChanging.getTarget(), "Изменить скорость производства молока в <b>каждой</b> из [npc.namePos] грудей. Чем больше грудей у [npc.sheHasFull], тем больше молока [npc.she] производит в секунду.<br/><i>После опорожнения, грудь [npc.namePos] наполняется молоком до своего максимального значения хранения с этой скоростью.</i>");
		String id = "MILK_REGENERATION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastRawLactationRegenerationValue(), ValueType.PRECISE)+"/день<br/>("+Units.fluid(BodyChanging.getTarget().getLactationRegenerationPerSecond(false)*60, ValueType.PRECISE)+"/минуту)<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastLactationRegeneration().getName())+"</i>",
					BodyChanging.getTarget().getBreastRawLactationRegenerationValue()<=0,
					BodyChanging.getTarget().getBreastRawLactationRegenerationValue()>=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(),
					FLUID_REGEN_INCREMENT_SMALL,
					FLUID_REGEN_INCREMENT_AVERAGE,
					FLUID_REGEN_INCREMENT_LARGE,
					FLUID_REGEN_INCREMENT_HUGE);
		}
	}

	public static String getSelfTransformLactationCrotchRegenerationDiv() {
		String title = "Регенерация молока за грудь-яйцо";
		String description = UtilText.parse(BodyChanging.getTarget(), "Изменяет скорость производства молока в <b>каждые</b> [npc.namePos] [npc.crotchBoobs]. Чем больше [npc.crotchBoobs] [npc.sheHasFull], тем больше молока [npc.she] производит в секунду.<br/><i>После опорожнения, [npc.namePos] [npc.crotchBoobs] наполняются молоком до максимального значения хранения с этой скоростью.</i>");
		String id = "MILK_CROTCH_REGENERATION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastCrotchRawLactationRegenerationValue(), ValueType.PRECISE)+"/день<br/>("+Units.fluid(BodyChanging.getTarget().getCrotchLactationRegenerationPerSecond(false)*60, ValueType.PRECISE)+"/минуту)<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastCrotchLactationRegeneration().getName())+"</i>",
					BodyChanging.getTarget().getBreastCrotchRawLactationRegenerationValue()<=0,
					BodyChanging.getTarget().getBreastCrotchRawLactationRegenerationValue()>=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(),
					FLUID_REGEN_INCREMENT_SMALL,
					FLUID_REGEN_INCREMENT_AVERAGE,
					FLUID_REGEN_INCREMENT_LARGE,
					FLUID_REGEN_INCREMENT_HUGE);
		}
	}

	public static String getSelfTransformLactationFlavourDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>");
		} else {
			for (FluidFlavour flavour : FluidFlavour.values()) {
				if (BodyChanging.getTarget().getMilkFlavour().equals(flavour)) {
					contentSB.append(
							"<div id='MILK_FLAVOUR_"+flavour+"' class='cosmetics-button active'><span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_FLAVOUR_"+flavour+"' class='cosmetics-button'><span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Вкус молока",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вкус молока [npc.namePos].<br/><i>Это повлияет на описания как во время секса, так и вне его.</i>"),
				"MILK_FLAVOUR",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformLactationModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>");
		} else {
			for (FluidModifier modifier : FluidModifier.values()) {
				if (BodyChanging.getTarget().hasMilkModifier(modifier)) {
					contentSB.append(
							"<div id='MILK_MODIFIER_"+modifier+"' class='cosmetics-button active'><span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_MODIFIER_"+modifier+"' class='cosmetics-button'><span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Модификаторы молока",
				UtilText.parse(BodyChanging.getTarget(), "Добавить или убрать модификаторы для молока [npc.namePos].<br/><i>Это повлияет на описания как во время секса, так и вне его. Модификаторы со звездочкой рядом с названием имеют особые игровые эффекты, например, наложение статусных эффектов при употреблении.</i>"),
				"MILK_MODIFIER",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformLactationCrotchFlavourDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>");
		} else {
			for (FluidFlavour flavour : FluidFlavour.values()) {
				if (BodyChanging.getTarget().getMilkCrotchFlavour().equals(flavour)) {
					contentSB.append(
							"<div id='MILK_CROTCH_FLAVOUR_"+flavour+"' class='cosmetics-button active'><span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_CROTCH_FLAVOUR_"+flavour+"' class='cosmetics-button'><span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Вкус молока",
				UtilText.parse(BodyChanging.getTarget(), "Изменить характер [npc.namePos] [npc.crotchMilk].<br/><i>Это повлияет на описания как во время секса, так и вне его.</i>"),
				"MILK_CROTCH_FLAVOUR",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLactationCrotchModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Лактация отключена")
							+"</div>");
		} else {
			for (FluidModifier modifier : FluidModifier.values()) {
				if (BodyChanging.getTarget().hasMilkCrotchModifier(modifier)) {
					contentSB.append(
							"<div id='MILK_CROTCH_MODIFIER_"+modifier+"' class='cosmetics-button active'><span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_CROTCH_MODIFIER_"+modifier+"' class='cosmetics-button'><span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Модификаторы молока",
				UtilText.parse(BodyChanging.getTarget(), "Добавить или убрать модификаторы для [npc.namePos] [npc.crotchMilk].<br/><i>Это повлияет на описания как во время секса, так и вне его. Модификаторы со звездочкой рядом с названием имеют особые игровые эффекты, например, наложение статусных эффектов при употреблении.</i>"),
				"MILK_CROTCH_MODIFIER",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getNippleDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='NIPPLE_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("Глубина сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину [npc.namePos] [npc.nipples].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно вместить.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"NIPPLE_DEPTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCrotchDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getNippleCrotchDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='NIPPLE_CROTCH_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("Глубина сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину [npc.namePos] [npc.crotchNipples].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно вместить.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"NIPPLE_CROTCH_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getNippleElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Эластичность сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить эластичность [npc.namePos] сосков.<br/><i>Это определяет, насколько быстро [npc.namePos] соски растянутся, если проникающий объект окажется слишком толстым для них."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину [npc.her] сосков.":""))
						+"</i>",
				"NIPPLE_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getNippleCrotchElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Эластичность сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить эластичность [npc.namePos] [npc.crotchNipples].<br/><i>Это определяет, насколько быстро [npc.namePos] [npc.crotchNipples] растянутся, если проникающий объект окажется слишком толстым для них."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину [npc.her] [npc.crotchNipples].":""))
						+"</i>",
				"NIPPLE_CROTCH_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNipplePlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getNipplePlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Пластичность сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить пластичность [npc.namePos] сосков.<br/><i>Пластичность отверстия определяет, насколько быстро и насколько сильно оно восстанавливает свою первоначальную упругость после растяжения.</i>"),
				"NIPPLE_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchPlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getNippleCrotchPlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Пластичность сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить пластичность [npc.namePos] [npc.crotchNipples].<br/><i>Пластичность отверстия определяет, насколько быстро и насколько сильно оно восстанавливает свою первоначальную узость после растяжения.</i>"),
				"NIPPLE_CROTCH_PLASTICITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='NIPPLE_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Модификаторы сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы для сосков [npc.namePos].<br/><i>Модификаторы отверстий влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"NIPPLE_MODS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCrotchModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Перо для сосков отключено")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasNippleCrotchOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Модификаторы сосков",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы для [npc.namePos] [npc.crotchNipples].<br/><i>Модификаторы отверстий влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"NIPPLE_CROTCH_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractVaginaType vagina : VaginaType.getAllVaginaTypes()) {
			if(((vagina.getRace() !=null && availableRaces.contains(vagina.getRace()))
					|| vagina==VaginaType.NONE)
					&& vagina!=VaginaType.ONAHOLE) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(vagina.getRace() != null) {
					c = vagina.getRace().getColour();
				}
				
				
				if(BodyChanging.getTarget().getVaginaType() == vagina) {
					contentSB.append(
							"<div id='VAGINA_"+VaginaType.getIdFromVaginaType(vagina)+"' class='cosmetics-button active'><span style='color:"+c.toWebHexString()+";'>"
									+Util.capitaliseSentence(vagina.getTransformName())+(vagina.isEggLayer()?"*":"")
								+"</span>"
							+ "</div>");
					
				} else {
					boolean cannotChoose = vagina==VaginaType.NONE
							&& (BodyChanging.getTarget().isPregnant() || BodyChanging.getTarget().hasStatusEffect(StatusEffect.PREGNANT_0) || BodyChanging.getTarget().hasIncubationLitter(SexAreaOrifice.VAGINA));
					contentSB.append(
							"<div id='VAGINA_"+VaginaType.getIdFromVaginaType(vagina)+"' class='cosmetics-button"+(cannotChoose?" отключено":"")+"'><span style='color:"+(cannotChoose?PresetColour.TEXT_GREY.toWebHexString():c.getShades()[0])+";'>"
									+Util.capitaliseSentence(vagina.getTransformName())+(vagina.isEggLayer()?"*":"")
								+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Киска",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип влагалища [npc.namePos]. Типы, отмеченные звездочкой [style.colourEgg(откладывают яйца)], по умолчанию.<br/>[style.italicsMinorBad(Влагалище персонажа нельзя изменить, пока он высиживает яйца или имеет возможность забеременеть!)]"),
				"VAGINA_TYPE",
				contentSB.toString(),
				false);
	}

	public static String getSelfTransformGirlcumFlavourDiv() {
		contentSB.setLength(0);
		
		for(FluidFlavour flavour : FluidFlavour.values()) {
			if(BodyChanging.getTarget().getGirlcumFlavour().equals(flavour)) {
				contentSB.append(
						"<div id='GIRLCUM_FLAVOUR_"+flavour+"' class='cosmetics-button active'><span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='GIRLCUM_FLAVOUR_"+flavour+"' class='cosmetics-button'><span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Вкус девичьей спермы",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вкус девичьей спермы [npc.namePos].<br/><i>Это повлияет на описания как во время секса, так и вне его.</i>"),
				"GIRLCUM_FLAVOUR",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformGirlcumModifiersDiv() {
		contentSB.setLength(0);
		
		for(FluidModifier modifier : FluidModifier.values()) {
			if(BodyChanging.getTarget().hasGirlcumModifier(modifier)) {
				contentSB.append(
						"<div id='GIRLCUM_MODIFIER_"+modifier+"' class='cosmetics-button active'><span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='GIRLCUM_MODIFIER_"+modifier+"' class='cosmetics-button'><span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы девичьей спермы",
				UtilText.parse(BodyChanging.getTarget(), "Добавить или удалить модификаторы к женской смазке [npc.namePos].<br/><i>Это повлияет на описания как во время, так и вне секса. Модификаторы со звездочкой рядом с названием имеют особые игровые эффекты, такие как наложение статусных эффектов при проглатывании.</i>"),
				"GIRLCUM_MODIFIER",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformVaginaCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getVaginaCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Вместимость влагалища",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость влагалища [npc.namePos].<br/><i>Это влияет на размер проникающих объектов, которые может принять киска [npc.namePos]. Если вместимость слишком мала или слишком велика для объекта, это повлияет на прирост возбуждения.</i>"),
				"VAGINA_CAPACITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaSquirterDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isVaginaSquirter()) {
			contentSB.append(
					"<div id='VAGINA_SQUIRTER_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_WETNESS_ONE.getShades()[0]+";'>Не склонна к сквирту</span></div><div class='cosmetics-button active'><span style='color:"+PresetColour.GENERIC_WETNESS_FIVE.toWebHexString()+";'>Склонна к сквирту</span></div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_WETNESS_ONE.toWebHexString()+";'>Не склонна к сквирту</span></div><div id='VAGINA_SQUIRTER_ON' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_WETNESS_FIVE.getShades()[0]+";'>Склонна к сквирту</span></div>");
		}
		

		return applyWrapper("Сквиртер",
				UtilText.parse(BodyChanging.getTarget(), "Установить, будет ли влагалище [npc.namePos] выделять жидкость, когда [npc.she] [npc.verb(orgasm)].<br/><i>Если [npc.nameIsFull] склонна к сквирту, то когда [npc.she] [npc.verb(orgasm)], [npc.she] испачкает одежду [npc.she] [npc.verb(squirt)]. Также, если кто-то будет ласкать [npc.herHim] ртом, он будет проглатывать [npc.her] жидкость.</i>"),
				"VAGINA_SQUIRTER",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaHymenDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().hasHymen()) {
			contentSB.append(
					"<div id='VAGINA_HYMEN_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.getShades()[0]+";'>Утеряна</span></div><div class='cosmetics-button active'><span style='color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Цела</span></div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>Утеряна</span></div><div id='VAGINA_HYMEN_ON' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_MINOR_GOOD.getShades()[0]+";'>Цела</span></div>");
		}
		

		return applyWrapper("Девственная плева",
				UtilText.parse(BodyChanging.getTarget(), "Установить, является ли девственная плева [npc.namePos] целой или нет."),
				"VAGINA_HYMEN",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaEggLayerDiv() {
		contentSB.setLength(0);
		
		boolean disabled = BodyChanging.getTarget().isPregnant();
		
		if(BodyChanging.getTarget().isVaginaEggLayer()) {
			contentSB.append(
					"<div id='VAGINA_EGG_LAYER_OFF' class='cosmetics-button"+(disabled?" отключено":"")+"'><span style='color:"+PresetColour.GENERIC_SEX.getShades()[0]+";'>Естественные роды</span></div><div class='cosmetics-button"+(disabled?" отключено":" активно")+"'><span style='color:"+PresetColour.EGG.toWebHexString()+";'>Яйцеклад</span></div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button"+(disabled?" отключено":" активно")+"'><span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Живорождение</span></div><div id='VAGINA_EGG_LAYER_ON' class='cosmetics-button"+(disabled?" отключено":"")+"'><span style='color:"+PresetColour.EGG.getShades()[0]+";'>Яйцеклад</span></div>");
		}
		
		return applyWrapper("Тип рождения",
				UtilText.parse(BodyChanging.getTarget(), "Устанавливает, будет ли [npc.namePos] рожать живых детенышей или откладывать яйца. [style.italicsMinorBad(Нельзя изменить во время беременности.)]<br/><i>При каждом изменении типа влагалища это значение сбрасывается до типа рождения, установленного по умолчанию для этого типа влагалища.</i>"),
				"VAGINA_EGG_LAYER",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getVaginaWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_WETNESS_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Влажность влагалища",
				UtilText.parse(BodyChanging.getTarget(), "Изменить влажность влагалища [npc.namePos].<br/><i>Это влияет на удовольствие во время секса, так как недостаточно смазанные отверстия замедляют рост возбуждения.</i>"
							+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
									?"<br/>[style.italicsWetness8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'!)]"
									:"")),
				"VAGINA_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getVaginaDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Глубина влагалища",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину влагалища [npc.namePos].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно вместить.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"VAGINA_DEPTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getVaginaElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Эластичность влагалища",
				UtilText.parse(BodyChanging.getTarget(), "Изменить эластичность влагалища [npc.namePos].<br/><i>Это определяет, насколько быстро влагалище [npc.namePos] растянется, если проникающий объект окажется слишком толстым для него."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину влагалища [npc.her].":""))
						+"</i>",
				"VAGINA_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getVaginaPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Пластичность влагалища",
				UtilText.parse(BodyChanging.getTarget(), "Изменить пластичность влагалища [npc.namePos].<br/><i>Пластичность отверстия определяет, насколько быстро и в какой степени оно восстанавливает свою первоначальную узкость после растяжения.</i>"),
				"VAGINA_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformClitorisSizeDiv() {
		return applyFullVariableWrapper("Длина клитора",
				UtilText.parse(BodyChanging.getTarget(), "Изменить длину клитора [npc.namePos]. Все размеры больше '<i style='color:"+ClitorisSize.ONE_BIG.getColour().toWebHexString()+";'>"
							+ Util.capitaliseSentence(ClitorisSize.ONE_BIG.getDescriptor())+"</i>' позволяют использовать клитор [npc.her] для проникновения во время секса. (При достижении достаточного размера значение будет отмечено звездочкой, что позволит использовать его как псевдопенис.)"),
				"CLITORIS_SIZE",
				Units.size(1),
				Units.size(5),
				Units.size(BodyChanging.getTarget().getVaginaRawClitorisSizeValue(), Units.ValueType.PRECISE, Units.UnitType.SHORT)
					+"<br/><i style='color:"+BodyChanging.getTarget().getVaginaClitorisSize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getVaginaClitorisSize().getDescriptor())
						+ (BodyChanging.getTarget().getVaginaClitorisSize().isPseudoPenisSize()?"*":"")
					+"</i>"
					+ "<br/><i>Diameter: "+Units.size(BodyChanging.getTarget().getClitorisDiameter())+"</i>",
				BodyChanging.getTarget().getVaginaRawClitorisSizeValue()<=0,
				BodyChanging.getTarget().getVaginaRawClitorisSizeValue()>=ClitorisSize.SEVEN_STALLION.getMaximumValue());
	}

	public static String getSelfTransformClitorisGirthDiv() {
		contentSB.setLength(0);
		
		for(PenetrationGirth girth : PenetrationGirth.values()) {
			if(BodyChanging.getTarget().getClitorisGirth() == girth) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_GIRTH_"+girth+"' class='cosmetics-button'><span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Обхват клитора",
				UtilText.parse(BodyChanging.getTarget(), "Изменить обхват клитора [npc.namePos].<br/><i>Обхват клитора влияет на то, будет ли он слишком толстым для отверстия, которое он проникает.</i>"),
				"CLITORIS_GIRTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLabiaSizeDiv() {
		contentSB.setLength(0);
		
		for(LabiaSize size : LabiaSize.values()) {
			if(BodyChanging.getTarget().getVaginaLabiaSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LABIA_SIZE_"+size+"' class='cosmetics-button'><span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер половых губ",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер половых губ [npc.namePos].<br/><i>Это чисто косметическое изменение, влияющее на описания как во время секса, так и вне его.</i>"),
				"LABIA_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='VAGINA_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы влагалища",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы влагалища [npc.namePos].<br/><i>Модификаторы отверстий влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"VAGINA_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getVaginaUrethraCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Вместимость уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость уретры [npc.namePos].<br/><i>Любое значение, отличное от нуля, позволяет [npc.namePos] уретру влагалища проникать во время секса. (Зависит от ваших предпочтений в настройках контента для 'проникновения в уретру'.)</i>"),
				"VAGINA_URETHRA_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getVaginaUrethraDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='VAGINA_URETHRA_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("Глубина уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину уретры [npc.namePos].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно вместить.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"VAGINA_URETHRA_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getVaginaUrethraElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Эластичность уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить эластичность уретры [npc.namePos].<br/><i>Это определяет, насколько быстро уретра [npc.namePos] растянется, если проникающий объект окажется слишком толстым для нее."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину уретры [npc.her].":""))
						+"</i>",
				"VAGINA_URETHRA_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraPlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getVaginaUrethraPlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Пластичность уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить пластичность уретры [npc.namePos].<br/><i>Пластичность отверстия определяет, насколько быстро и насколько сильно оно восстанавливает свою первоначальную узкость после растяжения.</i>"),
				"VAGINA_URETHRA_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasVaginaUrethraOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='VAGINA_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Модификаторы уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы уретры [npc.namePos].<br/><i>Модификаторы отверстия влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"VAGINA_URETHRA_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformPenisChoiceDiv(List<AbstractRace> availableRaces, boolean halfWidth) {
		contentSB.setLength(0);
		
		for(AbstractPenisType penis : PenisType.getAllPenisTypes()) {
			if(((penis.getRace() !=null && availableRaces.contains(penis.getRace()))
					|| penis==PenisType.NONE)
					&& penis!=PenisType.DILDO) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(penis.getRace() != null) {
					c = penis.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getPenisType() == penis) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(penis.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='PENIS_"+PenisType.getIdFromPenisType(penis)+"' class='cosmetics-button'><span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(penis.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Пенис",
				UtilText.parse(BodyChanging.getTarget(), "Изменить тип пениса [npc.namePos].<br/><i>Тип пениса влияет на модификаторы и другие атрибуты, с которыми персонажи появляются в игре, а также на описания как во время, так и вне секса.</i>"),
				"PENIS_TYPE",
				contentSB.toString(),
				halfWidth);
	}
	
	public static String getSelfTransformPenisSizeDiv() {
		return applyFullVariableWrapper("Длина пениса",
				(BodyChanging.getTarget().isPlayer()
						?"Изменить длину вашего пениса."
						:UtilText.parse(BodyChanging.getTarget(), "Изменить размер пениса [npc.namePos].")),
				"PENIS_SIZE",
				Units.size(1),
				Units.size(5),
				Units.size(BodyChanging.getTarget().getPenisRawSizeValue(), Units.ValueType.PRECISE, Units.UnitType.SHORT)
					+"<br/><i style='color:"+BodyChanging.getTarget().getPenisSize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getPenisSize().getDescriptor())+"</i>"
					+ "<br/><i>Diameter: "+Units.size(BodyChanging.getTarget().getPenisDiameter())+"</i>",
				BodyChanging.getTarget().getPenisRawSizeValue()<=0,
				BodyChanging.getTarget().getPenisRawSizeValue()>=PenisLength.SEVEN_STALLION.getMaximumValue());
	}
	
	public static String getSelfTransformPenisGirthDiv() {
		contentSB.setLength(0);
		
		for(PenetrationGirth girth : PenetrationGirth.values()) {
			if(BodyChanging.getTarget().getPenisGirth() == girth) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_GIRTH_"+girth+"' class='cosmetics-button'><span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Обхват пениса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить обхват пениса [npc.namePos].<br/><i>Обхват пениса влияет на то, будет ли он слишком велик для отверстия, которое он проникает.</i>"),
				"PENIS_GIRTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTesticleSizeDiv() {
		contentSB.setLength(0);
		
		for(TesticleSize size : TesticleSize.values()) {
			if(BodyChanging.getTarget().getTesticleSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_SIZE_"+size+"' class='cosmetics-button'><span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер яичек",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер яичек [npc.namePos].<br/><i>Размер яичек — это косметическое изменение, которое влияет на описания как во время секса, так и вне его.</i>"),
				"TESTICLE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTesticleCountDiv() {
		contentSB.setLength(0);
		
		for(int i=Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i+=2) {
			if(BodyChanging.getTarget().getTesticleCount() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_COUNT_"+i+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Количество яичек",
				UtilText.parse(BodyChanging.getTarget(), "Изменить количество яичек у [npc.name].<br/><i>Количество яичек — это косметическое изменение, которое влияет на описания как во время секса, так и вне его.</i>"),
				"TESTICLE_COUNT",
				contentSB.toString(),
				true);
	}
	

	public static String getSelfTransformInternalTesticleDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isInternalTesticles()) {
			contentSB.append(
					"<div id='TESTICLES_INTERNAL_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>Внешние</span></div><div class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>Внутренние</span></div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>Внешние</span></div><div id='TESTICLES_INTERNAL_ON' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>Внутренние</span></div>");
		}
		

		return applyWrapper("Внутренние яички",
				UtilText.parse(BodyChanging.getTarget(), "Установить, являются ли [npc.namePos] яички внутренними или нет.<br/><i>Это влияет на описания и доступность некоторых действий во время секса. (Для фута-персонажей зависит от вашей настройки контента «яички футари».)</i>"),
				"INTERNAL_TESTICLES",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getPenisCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Вместимость уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость [npc.namePos] уретры.<br/><i>Любое значение, отличное от нуля, означает, что [npc.namePos] мочеиспускательный канал пениса можно проникать во время секса. (Зависит от ваших предпочтений в настройках контента «проникновение в уретру».)</i>"),
				"PENIS_URETHRA_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getUrethraDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='URETHRA_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("Глубина уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину уретры [npc.namePos].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно вместить.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"URETHRA_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static int getCumUpperLimit() {
		if(Main.game.isInNewWorld()) {
			return CumProduction.SEVEN_MONSTROUS.getMaximumValue();
		} else {
			return 30;
		}
	}
	
	public static String getSelfTransformCumProductionDiv() {
		return applyVariableWrapperFluids("Запас спермы",
				UtilText.parse(BodyChanging.getTarget(), "Изменить максимальный запас спермы в [npc.namePos].<br/><i>После опорожнения, [npc.namePos] яички наполняются спермой до этого значения со скоростью, определяемой значением регенерации спермы [npc.her].</i>"),
				"CUM_PRODUCTION",
				Util.capitaliseSentence(BodyChanging.getTarget().getPenisCumStorage().getName())
					+"<br/>("+Units.fluid(BodyChanging.getTarget().getPenisRawCumStorageValue(), ValueType.PRECISE)+")",
				BodyChanging.getTarget().getPenisRawCumStorageValue()<=0,
				BodyChanging.getTarget().getPenisRawCumStorageValue()>=getCumUpperLimit(),
				FLUID_INCREMENT_SMALL,
				FLUID_INCREMENT_AVERAGE,
				FLUID_INCREMENT_LARGE,
				FLUID_INCREMENT_HUGE);
	}
	
	public static String getSelfTransformCumRegenerationDiv() {
		return applyVariableWrapperFluids("Регенерация спермы",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет скорость, с которой [npc.name] [npc.verb(produce)] накапливается.<br/><i>После опустошения, [npc.namePos] шары будут наполняться спермой до максимального значения с этой скоростью.</i>"),
				"CUM_REGENERATION",
				Units.fluid(BodyChanging.getTarget().getPenisRawCumProductionRegenerationValue(), ValueType.PRECISE)+"/день<br/>("+Units.fluid(BodyChanging.getTarget().getCumRegenerationPerSecond()*60, ValueType.PRECISE)+"/минуту)<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getPenisCumProductionRegeneration().getName())+"</i>",
				BodyChanging.getTarget().getPenisRawCumProductionRegenerationValue()<=0,
				BodyChanging.getTarget().getPenisRawCumProductionRegenerationValue()>=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(),
				FLUID_REGEN_INCREMENT_SMALL,
				FLUID_REGEN_INCREMENT_AVERAGE,
				FLUID_REGEN_INCREMENT_LARGE,
				FLUID_REGEN_INCREMENT_HUGE);
	}

	public static String getSelfTransformCumExplusionDiv() {
		CumProduction expelledValue = CumProduction.getCumProductionFromInt(BodyChanging.getTarget().getPenisMaximumOrgasmCumQuantity());
		int extraSlotsDirtied = expelledValue.getAdditionalSlotsDirtiedUponOrgasm();
		return applyFullVariableWrapper("Извержение спермы",
				UtilText.parse(BodyChanging.getTarget(), "Изменяет количество накопленной [npc.namePos] спермы, извергаемой при каждом оргазме.<br/><i>Если количество накопленной спермы меньше или равно "+Units.fluid(Testicle.MINIMUM_VALUE_FOR_ALL_CUM_TO_BE_EXPELLED)+", 100% o, из [npc.namePos] шаров будет извержено % o спермы.</i>"),
				"CUM_EXPULSION",
				"1",
				"10",
				BodyChanging.getTarget().getPenisRawCumExpulsionValue()+"%"
						+"<br/><i>"+Units.fluid(BodyChanging.getTarget().getPenisMaximumOrgasmCumQuantity())+" - <span style='color:"+expelledValue.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(expelledValue.getName())+"</span></i>",
//					+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getPenisCumExpulsion().getDescriptor())+"</i>",
				BodyChanging.getTarget().getPenisRawCumExpulsionValue()<=5 || !Main.getProperties().hasValue(PropertyValue.cumRegenerationContent),
				BodyChanging.getTarget().getPenisRawCumExpulsionValue()>=100 || !Main.getProperties().hasValue(PropertyValue.cumRegenerationContent),
				"<i>Dirties <b style='color:"+expelledValue.getColour().toWebHexString()+";'>"+Util.intToString(extraSlotsDirtied)+"</b> дополнительных слотов инвентаря"+(extraSlotsDirtied==1?"":"s")+" во время направленного оргазма.</i>");
	}

	public static String getSelfTransformCumFlavourDiv() {
		contentSB.setLength(0);
		
		for(FluidFlavour flavour : FluidFlavour.values()) {
			if(BodyChanging.getTarget().getCumFlavour().equals(flavour)) {
				contentSB.append(
						"<div id='CUM_FLAVOUR_"+flavour+"' class='cosmetics-button active'><span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CUM_FLAVOUR_"+flavour+"' class='cosmetics-button'><span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Вкус спермы",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вкус спермы [npc.namePos].<br/><i>Это повлияет на описания как во время секса, так и вне его.</i>"),
				"CUM_FLAVOUR",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformCumModifiersDiv() {
		contentSB.setLength(0);
		
		for(FluidModifier modifier : FluidModifier.values()) {
			if(BodyChanging.getTarget().hasCumModifier(modifier)) {
				contentSB.append(
						"<div id='CUM_MODIFIER_"+modifier+"' class='cosmetics-button active'><span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CUM_MODIFIER_"+modifier+"' class='cosmetics-button'><span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы спермы",
				UtilText.parse(BodyChanging.getTarget(), "Добавить или убрать модификаторы спермы [npc.namePos].<br/><i>Это повлияет на описания как во время секса, так и вне его. Модификаторы со звездочкой рядом с названием имеют особые игровые эффекты, например, наложение статусных эффектов при проглатывании.</i>"),
				"CUM_MODIFIER",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getUrethraElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Эластичность уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить эластичность уретры [npc.namePos].<br/><i>Это определяет, насколько быстро уретра [npc.namePos] растянется, если проникающий объект окажется слишком толстым для нее."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину уретры [npc.her].":""))
						+"</i>",
				"PENIS_URETHRA_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraPlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getUrethraPlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Пластичность уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить пластичность уретры [npc.namePos].<br/><i>Пластичность отверстия определяет, насколько быстро и насколько сильно оно восстанавливает свою первоначальную узкость после растяжения.</i>"),
				"PENIS_URETHRA_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformPenisModifiersDiv() {
		contentSB.setLength(0);
		
		for(PenetrationModifier penMod : PenetrationModifier.values()) {
			if(BodyChanging.getTarget().hasPenisModifier(penMod)) {
				contentSB.append(
						"<div id='PENIS_MOD_"+penMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_MOD_"+penMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы пениса",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы пениса [npc.namePos].<br/><i>Модификаторы пениса влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"PENIS_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("Уретральный пенис отключен")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='URETHRA_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("Модификаторы уретры",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы уретры [npc.namePos].<br/><i>Модификаторы отверстия влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"PENIS_URETHRA_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getBodySizeChoiceDiv() {
		contentSB.setLength(0);
		
		for(BodySize bs : BodySize.values()) {
			if( BodyChanging.getTarget().getBodySize() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+ BodyChanging.getTarget().getBodySize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(bs.getName(false))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BODY_SIZE_"+bs+"' class='cosmetics-button'><span style='color:"+bs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(bs.getName(false))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Размер тела",
				UtilText.parse(BodyChanging.getTarget(), "Изменить размер тела [npc.namePos].<br/><i>Это определяет количество подкожного жира, который [npc.namePos] [npc.has], и является чисто косметическим изменением.</i>"),
				"BODY_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getMuscleChoiceDiv() {
		contentSB.setLength(0);
		
		for(Muscle muscle : Muscle.values()) {
			if( BodyChanging.getTarget().getMuscle() == muscle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+ BodyChanging.getTarget().getMuscle().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='MUSCLE_"+muscle+"' class='cosmetics-button'><span style='color:"+muscle.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Рельеф мышц",
				UtilText.parse(BodyChanging.getTarget(), "Изменить рельеф мышц [npc.namePos]."+(!Main.game.isInNewWorld()?" Это не влияет на атрибут телосложения вашего персонажа.":"")
							+ "<br/><i>Это определяет, насколько выражены мышцы [npc.namePos] [npc.has], и является чисто косметическим изменением.</i>"),
				"MUSCLE",
				contentSB.toString(),
				true);
	}
	
	public static String getLipSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер губ</h5><p style='text-align:center;'>Выберите объем ваших губ.</p></div><div class='cosmetics-inner-container right'>");
		
		for(LipSize ls : LipSize.values()) {
			if(!ls.isImpedesSpeech() || !Main.game.isLipLispEnabled()) {
				if(BodyChanging.getTarget().getLipSize() == ls) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+ls.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ls.getName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='LIP_SIZE_"+ls+"' class='cosmetics-button'><span style='color:"+ls.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ls.getName())+"</span>"
							+ "</div>");
				}
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getLipPuffynessDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Пухлые губы</h5><p style='text-align:center;'>Выберите, будут ли ваши губы дополнительно пухлыми.</p></div><div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
			contentSB.append(
					"<div id='LIP_PUFFY_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>Обычные</span></div><div class='cosmetics-button active'>[style.boldGood(Пухлые)]</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(Normal)]"
					+ "</div>"
					+ "<div id='LIP_PUFFY_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>Пухлые</span></div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static CupSize[] getBreastSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new CupSize[] {CupSize.FLAT, CupSize.TRAINING_AAA, CupSize.TRAINING_AA, CupSize.TRAINING_A};
		} else {
			return new CupSize[] {CupSize.AA, CupSize.A, CupSize.B, CupSize.C, CupSize.D, CupSize.DD, CupSize.E};
		}
	}
	
	public static String getBreastSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер груди</h5><p style='text-align:center;'>Выберите размер вашей груди (размер чашки).</p></div><div class='cosmetics-inner-container right'>");
		
		CupSize[] sizesAvailable = getBreastSizesAvailable();
		
		for(CupSize cs : sizesAvailable) {
			if(BodyChanging.getTarget().getBreastSize() == cs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(cs.getCupSizeName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_SIZE_"+cs+"' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(cs.getCupSizeName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBreastShapeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Форма груди</h5><p style='text-align:center;'>Выберите форму вашей груди.</p></div><div class='cosmetics-inner-container right'>");
		
		for(BreastShape bs : BreastShape.values()) {
			if(!bs.isRestrictedToCrotchBoobs()) {
				if(BodyChanging.getTarget().getBreastShape() == bs) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_SHAPE_"+bs+"' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static NippleSize[] getNippleSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new NippleSize[] {NippleSize.ZERO_TINY, NippleSize.ONE_SMALL, NippleSize.TWO_BIG};
		} else {
			return new NippleSize[] {NippleSize.ZERO_TINY, NippleSize.ONE_SMALL, NippleSize.TWO_BIG, NippleSize.THREE_LARGE, NippleSize.FOUR_MASSIVE};
		}
	}
	
	public static String getNippleSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер сосков</h5><p style='text-align:center;'>Выберите размер ваших сосков.</p></div><div class='cosmetics-inner-container right'>");
		
		NippleSize[] sizesAvailable = getNippleSizesAvailable();
		
		for(NippleSize ns : sizesAvailable) {
			if(BodyChanging.getTarget().getNippleSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+ns.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_SIZE_"+ns+"' class='cosmetics-button'><span style='color:"+ns.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static AreolaeSize[] getAreolaeSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new AreolaeSize[] {AreolaeSize.ZERO_TINY, AreolaeSize.ONE_SMALL, AreolaeSize.TWO_BIG};
		} else {
			return new AreolaeSize[] {AreolaeSize.ZERO_TINY, AreolaeSize.ONE_SMALL, AreolaeSize.TWO_BIG, AreolaeSize.THREE_LARGE, AreolaeSize.FOUR_MASSIVE};
		}
	}
	
	public static String getAreolaeSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер ареол</h5><p style='text-align:center;'>Выберите размер ваших ареол.</p></div><div class='cosmetics-inner-container right'>");
		
		AreolaeSize[] sizesAvailable = getAreolaeSizesAvailable();
		
		for(AreolaeSize as : sizesAvailable) {
			if(BodyChanging.getTarget().getAreolaeSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_SIZE_"+as+"' class='cosmetics-button'><span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getNipplePuffynessDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Пухлые соски</h5><p style='text-align:center;'>Выберите, будут ли ваши соски дополнительно пухлыми.</p></div><div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
			contentSB.append(
					"<div id='NIPPLE_PUFFY_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>Обычные</span></div><div class='cosmetics-button active'>[style.boldGood(Пухлые)]</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(Normal)]"
					+ "</div>"
					+ "<div id='NIPPLE_PUFFY_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>Пухлые</span></div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static AssSize[] getAssSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new AssSize[] {AssSize.ZERO_FLAT, AssSize.ONE_TINY, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, AssSize.FOUR_LARGE};
		} else {
			return AssSize.values();
		}
	}
	
	public static String getAssSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер задницы</h5><p style='text-align:center;'>Выберите размер вашей задницы.</p></div><div class='cosmetics-inner-container right'>");
		
		AssSize[] sizesAvailable = getAssSizesAvailable();
		
		for(AssSize as : sizesAvailable) {
			if(BodyChanging.getTarget().getAssSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ASS_SIZE_"+as+"' class='cosmetics-button'><span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static HipSize[] getHipSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new HipSize[] {HipSize.ZERO_NO_HIPS, HipSize.ONE_VERY_NARROW, HipSize.TWO_NARROW, HipSize.THREE_GIRLY};
		} else {
			return HipSize.values();
		}
	}
	
	public static String getHipSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер бедер</h5><p style='text-align:center;'>Выберите ширину ваших бедер.</p></div><div class='cosmetics-inner-container right'>");
		
		HipSize[] sizesAvailable = getHipSizesAvailable();
		
		for(HipSize hs : sizesAvailable) {
			if(BodyChanging.getTarget().getHipSize() == hs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+hs.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HIP_SIZE_"+hs+"' class='cosmetics-button'><span style='color:"+hs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBleachedAnusDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Осветленный анус</h5><p style='text-align:center;'>Выберите, будет ли ваш анус осветлен, чтобы он по цвету совпадал с остальным телом.</p></div><div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().isAssBleached()) {
			contentSB.append(
					"<div id='BLEACHING_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>Обычный</span></div><div class='cosmetics-button active'>[style.boldGood(Осветленный)]</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(Normal)]"
					+ "</div>"
					+ "<div id='BLEACHING_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>Осветленные</span></div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static int[] getPenisSizesAvailable() {
		return new int[] {3, 5, 8, 10, 12, 15, 18, 20};
	}
	
	public static String getPenisSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Длина члена</h5><p style='text-align:center;'>Выберите длину своего члена.</p></div><div class='cosmetics-inner-container right'>");
		
		int[] sizesAvailable = getPenisSizesAvailable();
		
		for(int size : sizesAvailable) {
			if(BodyChanging.getTarget().getPenisRawSizeValue() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Units.size(size)+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_SIZE_"+size+"' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Units.size(size)+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static TesticleSize[] getTesticleSizesAvailable() {
		return new TesticleSize[] {TesticleSize.ZERO_VESTIGIAL, TesticleSize.ONE_TINY, TesticleSize.TWO_AVERAGE, TesticleSize.THREE_LARGE};
	}
	
	public static String getTesticleSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер яичек</h5><p style='text-align:center;'>Выберите размер своих яичек.</p></div><div class='cosmetics-inner-container right'>");
		
		TesticleSize[] sizesAvailable = getTesticleSizesAvailable();
		
		for(TesticleSize size : sizesAvailable) {
			if(BodyChanging.getTarget().getTesticleSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_SIZE_"+size+"' class='cosmetics-button'><span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getVaginaCapacityDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Вместимость влагалища</h5><p style='text-align:center;'>Выберите вместимость своего влагалища. Высокая вместимость позволит легче принимать крупные члены, но если оно будет слишком свободным, партнерам с маленькими членами будет не очень приятно.</p></div><div class='cosmetics-inner-container right'>");
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getVaginaCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}

	public static String getSelfTransformClitorisModifiersDiv() {
		contentSB.setLength(0);
		
		for(PenetrationModifier penMod : PenetrationModifier.values()) {
			if(BodyChanging.getTarget().hasClitorisModifier(penMod)) {
				contentSB.append(
						"<div id='CLITORIS_MOD_"+penMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_MOD_"+penMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Модификаторы клитора",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы клитора [npc.namePos].<br/><i>Модификаторы клитора влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"CLITORIS_MODS",
				contentSB.toString(),
				true);
	}
	
	public static ClitorisSize[] getClitorisSizesAvailable() {
		return new ClitorisSize[] {ClitorisSize.ZERO_AVERAGE, ClitorisSize.ONE_BIG};
	}
	
	public static String getClitorisSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Длина клитора</h5><p style='text-align:center;'>Выберите длину вашего клитора.</p></div><div class='cosmetics-inner-container right'>");
		
		ClitorisSize[] sizesAvailable = getClitorisSizesAvailable();
		
		for(ClitorisSize size : sizesAvailable) {
			if(BodyChanging.getTarget().getVaginaClitorisSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_SIZE_"+size+"' class='cosmetics-button'><span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getLabiaSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'><div class='cosmetics-inner-container left'><h5 style='text-align:center;'>Размер половых губ</h5><p style='text-align:center;'>Выберите размер ваших половых губ.</p></div><div class='cosmetics-inner-container right'>");
		
		for(LabiaSize size : LabiaSize.values()) {
			if(BodyChanging.getTarget().getVaginaLabiaSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LABIA_SIZE_"+size+"' class='cosmetics-button'><span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getSelfTransformSpinneretModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasSpinneretOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='SPINNERET_MOD_"+orificeMod+"' class='cosmetics-button active'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_MOD_"+orificeMod+"' class='cosmetics-button'><span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}
		
		return applyWrapper("Модификаторы спинрет",
				UtilText.parse(BodyChanging.getTarget(), "Изменить модификаторы для спинрет [npc.namePos].<br/><i>Модификаторы отверстий влияют на описания и некоторые действия во время секса. Модификаторы, имеющие не только описательный эффект, отмечены звездочкой.</i>"),
				"SPINNERET_MODS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformSpinneretCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getSpinneretCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_CAPACITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Вместимость спинрет",
				UtilText.parse(BodyChanging.getTarget(), "Изменить вместимость спинрет [npc.namePos].<br/><i>Это влияет на размер проникающих объектов, которые может принять спинрет [npc.namePos]. Если вместимость слишком мала или слишком велика для объекта, это влияет на прирост возбуждения.</i>"),
				"SPINNERET_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformSpinneretWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getSpinneretWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='SPINNERET_WETNESS_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Влажность спинрет",
				UtilText.parse(BodyChanging.getTarget(), "Изменить влажность спинрет [npc.namePos].<br/><i>Это влияет на удовольствие во время секса, так как недостаточно смазанные отверстия отрицательно сказываются на приросте возбуждения.</i>"
							+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
									?"<br/>[style.italicsWetness8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'!)]"
									:"")),
				"SPINNERET_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformSpinneretDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getSpinneretDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='SPINNERET_DEPTH_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Глубина спинрет",
				UtilText.parse(BodyChanging.getTarget(), "Изменить глубину спинрет [npc.namePos].<br/><i>Глубина отверстия определяет длину проникающего объекта, который оно может комфортно принять.</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(As [npc.namePos] body is made out of [npc.bodyMaterial], [npc.her] orifices can never be less than '"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'!)]"
								:"")),
				"SPINNERET_DEPTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformSpinneretElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getSpinneretElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_ELASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Эластичность спинрет",
				UtilText.parse(BodyChanging.getTarget(), "Изменить эластичность спинрет [npc.namePos].<br/><i>Это определяет, насколько быстро спинрет [npc.namePos] растянется, если проникающий объект окажется слишком толстым для неё."
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" Значения, отмеченные звездочкой, увеличивают максимальную дискомфортную глубину спинрет [npc.her].":""))
						+"</i>",
				"SPINNERET_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformSpinneretPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getSpinneretPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_PLASTICITY_"+value+"' class='cosmetics-button'><span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Пластичность спинрет",
				UtilText.parse(BodyChanging.getTarget(), "Изменить пластичность спинрет [npc.namePos].<br/><i>Пластичность отверстия определяет, как быстро и насколько сильно оно восстанавливает свою первоначальную тесноту после растяжения.</i>"),
				"SPINNERET_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	
	
	// ---------------------- Kate's Shop: ---------------------- //
	
	public static String getKatesDivHairLengths(boolean withCost, String title, String description) {
		contentSB.setLength(0);
		
		boolean noCost = !withCost;

		
		for(HairLength hairLength : HairLength.values()) {
			if(BodyChanging.getTarget().getHairLength()==hairLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+hairLength.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HAIR_LENGTH_"+hairLength+"' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST || noCost
									? "<span style='color:"+hairLength.getColour().getShades()[0]+";'>" + Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"") + "</span>"
									: "[style.colourDisabled(" + Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"") + ")]")
						+ "</div>");
			}
		}

		return applyWrapper("Длина волос"
				+(noCost
					?""
					:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST
							? UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_LENGTH_COST, "b")
							: UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_LENGTH_COST, "b", PresetColour.GENERIC_BAD))),
				UtilText.parse(BodyChanging.getTarget(), "Изменить длину [npc.hair(true)] [npc.namePos].<br/><i>Волосы достаточной длины (отмеченные звездочкой) можно будет оттягивать во время некоторых сексуальных актов.</i>"),
				"HAIR_LENGTH",
				contentSB.toString(),
				false);
	}
	
	public static String getKatesDivHairStyles(boolean withCost, String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !withCost;
		for (HairStyle hairStyle : HairStyle.values()) {
			if (BodyChanging.getTarget().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
						+ "</div>");
			} else {
				if(BodyChanging.getTarget().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST || noCost
											? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
											: "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + ")]")
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + ")]"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Прическа"
				+(noCost
						?""
						:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST
								? UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_STYLE_COST, "b")
								: UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_STYLE_COST, "b", PresetColour.GENERIC_BAD))),
				UtilText.parse(BodyChanging.getTarget(), description)
					+ "<br/><i>'"+Util.capitaliseSentence(HairStyle.TWIN_TAILS.getName(BodyChanging.getTarget()))
						+"' и '"+Util.capitaliseSentence(HairStyle.TWIN_BRAIDS.getName(BodyChanging.getTarget()))+"' можно использовать как захваты в некоторых секс-сценах.</i>",
				"HAIR_STYLE",
				contentSB.toString(),
				false);
		
	}
	
	public static String getKatesDivAssHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().isAssHairAvailable()
						?""
						:"<br/><i>Из-за типа ануса [npc.namePos], у [npc.she] не растет волос на заднице!</i>"),
				BodyChanging.getTarget().getAssHair(),
				"ASS_HAIR_",
				!BodyChanging.getTarget().isAssHairAvailable());
	}
	
	public static String getKatesDivUnderarmHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().isUnderarmHairAvailable()
						?""
						:"<br/><i>Из-за типа рук [npc.namePos], у [npc.she] не растет волос под мышками!</i>"),
				BodyChanging.getTarget().getUnderarmHair(),
				"UNDERARM_HAIR_",
				!BodyChanging.getTarget().isUnderarmHairAvailable());
	}
	
	public static String getKatesDivFacialHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().isFeminine() && !Main.game.isFemaleFacialHairEnabled()
						?"<br/><i>Из-за того, что [npc.nameIsFull] женственная, у [npc.she] не растет борода!</i>"
						:(BodyChanging.getTarget().isUnderarmHairAvailable()
							?""
							:"<br/><i>Из-за типа лица [npc.namePos], у [npc.she] не растет борода!</i>")),
				BodyChanging.getTarget().getFacialHair(),
				"FACIAL_HAIR_",
				!BodyChanging.getTarget().isFacialHairAvailable() || (BodyChanging.getTarget().isFeminine() && !Main.game.isFemaleFacialHairEnabled()));
	}
	
	public static String getKatesDivPubicHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().hasPenisIgnoreDildo() && !BodyChanging.getTarget().getPenisType().isPubicHairAllowed()
						?"<br/><i>Из-за типа пениса [npc.namePos], у [npc.she] не растет лобковая растительность!</i>"
						:(BodyChanging.getTarget().hasVagina() && !BodyChanging.getTarget().getVaginaType().isPubicHairAllowed()
							?"<br/><i>Из-за типа влагалища [npc.namePos], у [npc.she] не растет лобковая растительность!</i>"
							:(BodyChanging.getTarget().hasPenisIgnoreDildo() || BodyChanging.getTarget().hasVagina()
							?""
							:"<br/><i>Из-за того, что у [npc.she] [npc.verb(lack)] гениталии, у [npc.name] не растет лобковая растительность!</i>"))),
				BodyChanging.getTarget().getPubicHair(),
				"PUBIC_HAIR_",
				!BodyChanging.getTarget().isPubicHairAvailable());
	}
	
	private static String getKatesDivGenericBodyHair(boolean withCost, String title, String description, BodyHair activeHair, String id, boolean blockAllButNoneOptions) {
		contentSB.setLength(0);

		boolean noCost = !withCost;
		for (BodyHair bodyHair : BodyHair.values()) {
			if (activeHair == bodyHair) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(bodyHair.getName()) + "</span>"
						+ "</div>");
			} else {
				if(blockAllButNoneOptions) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(bodyHair.getName()) + ")]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+id+bodyHair+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || noCost
										? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(bodyHair.getName()) + "</span>"
										: "[style.colourDisabled(" + Util.capitaliseSentence(bodyHair.getName()) + ")]")
							+ "</div>");
				}
			}
		}

		return applyWrapper(title
				+(noCost
						?""
						:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST
								? UtilText.formatAsMoney(SuccubisSecrets.BASE_BODY_HAIR_COST, "b")
								: UtilText.formatAsMoney(SuccubisSecrets.BASE_BODY_HAIR_COST, "b", PresetColour.GENERIC_BAD))),
				UtilText.parse(BodyChanging.getTarget(), description),
				id,
				contentSB.toString(),
				false);
	}
	
	public static String getKatesDivGenericBodyHairDisabled(String title, String description, String disabledDescription) {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>"
					+ "<p style='text-align:center; color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
						+ disabledDescription
					+ "</p>"
				+ "</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivAnalBleaching() {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();

		contentSB.append("<div class='container-full-width'>");

			contentSB.append(
						 "<div class='container-full-width' style='text-align:center; width:100%;padding:0;margin:0;'><b>Анальное отбеливание</b>"
							+ (noCost
								?""
								:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
									? UtilText.formatAsMoney(SuccubisSecrets.BASE_ANAL_BLEACHING_COST, "b")
									: UtilText.formatAsMoney(SuccubisSecrets.BASE_ANAL_BLEACHING_COST, "b", PresetColour.GENERIC_BAD)))
						+ "</div>");
						
			contentSB.append(
					getInformationDiv(
							"ANAL_BLEACHING",
							new TooltipInformationEventListener().setInformation("Анальное отбеливание", "Анальное отбеливание — это процесс осветления кожи вокруг ануса, чтобы она лучше гармонировала с окружающими участками тела."),
							false));
			
			contentSB.append("<div class='container-full-width' style='text-align:center; width:100%;padding:0;margin:0;'>");
			if(BodyChanging.getTarget().isAssBleached()) {
				contentSB.append(
						"<div id='BLEACHING_OFF' class='cosmetics-button'>[style.colourDisabled(Нормальный)]</div><div class='cosmetics-button active'>[style.boldArcane(Отбеленный)]</div>");
			} else {
				contentSB.append(
						"<div class='cosmetics-button active'>Нормальный</div><div id='BLEACHING_ON' class='cosmetics-button'>"
							+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
								?"<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>Отбеленный</span>"
								:"[style.colourDisabled(Отбеленный)]")
						+ "</div>");
			}
	
			contentSB.append("</div>");
			
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	//TODO reset on open
	private static Map<AbstractBodyCoveringType, Covering> coveringsToBeApplied = new HashMap<>();
	
	public static Map<AbstractBodyCoveringType, Covering> getCoveringsToBeApplied() {
		return coveringsToBeApplied;
	}
	
	public static void resetCoveringsToBeApplied() {
		coveringsToBeApplied = new HashMap<>();
	}

	public static String getKatesDivCoveringsNew(boolean withCost, AbstractRace race, AbstractBodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow) {
		return getKatesDivCoveringsNew(withCost, race, coveringType, title, description, withSecondary, withGlow, true);
	}
	
	public static String getKatesDivCoveringsNew(boolean withCost, AbstractRace race, AbstractBodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow, boolean withDyeAndExtraPatterns) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.coveringChangeListenersRequired, true);
		
		boolean disabledButton = !coveringsToBeApplied.containsKey(coveringType) || coveringsToBeApplied.get(coveringType).equals(BodyChanging.getTarget().getCovering(coveringType));
		
		StringBuilder sb = new StringBuilder();

		Covering activeCovering = !coveringsToBeApplied.containsKey(coveringType)
										?BodyChanging.getTarget().getCovering(coveringType)
										:coveringsToBeApplied.get(coveringType);
		
		List<CoveringPattern> availablePatterns = new ArrayList<>(withDyeAndExtraPatterns
																	?coveringType.getAllPatterns().keySet()
																	:coveringType.getNaturalPatterns().keySet());
		
		String rainbow = "";
		
		List<Colour> availablePrimaryColours = new ArrayList<>(withDyeAndExtraPatterns
				?coveringType.getAllPrimaryColours()
				:coveringType.getNaturalColoursPrimary());
		Collections.sort(availablePrimaryColours, (c1, c2)->c2.isMetallic()?(c1.isMetallic()?0:-1):(c1.isMetallic()?1:0));
		
		List<Colour> availableSecondaryColours = new ArrayList<>(withDyeAndExtraPatterns
													?coveringType.getAllSecondaryColours()
													:coveringType.getNaturalColoursSecondary());
		Collections.sort(availableSecondaryColours, (c1, c2)->c2.isMetallic()?(c1.isMetallic()?0:-1):(c1.isMetallic()?1:0));
		
		String border = "граница: 1px сплошная "+PresetColour.BACKGROUND.toWebHexString()+";";
		
		boolean secondaryDisabled = activeCovering.getPattern()==CoveringPattern.NONE
										|| activeCovering.getPattern()==CoveringPattern.EYE_IRISES
										|| activeCovering.getPattern()==CoveringPattern.EYE_PUPILS
										|| activeCovering.getPattern()==CoveringPattern.EYE_SCLERA
										|| !withSecondary;
		
		sb.append("<div class='container-full-width' style='text-align:center;'>");

			sb.append("<div class='container-full-width' style='padding:0; margin:0; text-align:center;'>");
				sb.append("<b>"+Util.capitaliseSentence(title)+"</b>");
				if(race!=Race.NONE) {
					sb.append(" - <b style='color:"+(race.getColour().toWebHexString())+"'>"+Util.capitaliseSentence(race.getName(BodyChanging.getTarget().getBody(), BodyChanging.getTarget().isFeral()))+"</b>");
				}
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='padding:0; margin:0; text-align:center;'>");
				sb.append(Util.capitaliseSentence(BodyChanging.getTarget().getCovering(coveringType).getFullDescription(BodyChanging.getTarget(), true)));
			sb.append("</div>");

			// Pattern and Modifiers section on left:
			sb.append("<div class='container-full-width' style='width:33.3%; padding:0; margin:0; text-align:center;'>");
			
				// Covering Pattern:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<p style='padding:0;margin:0;text-align:center;'>Шаблон:</p>");
					for (CoveringPattern pattern : availablePatterns) {
						if (activeCovering.getPattern() == pattern) {
							sb.append(
									"<div class='cosmetics-button active'>"
										+ "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
									+ "</div>");
						} else {
							sb.append(
									"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PATTERN_"+pattern+"' class='cosmetics-button'>"
											+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
												? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
												: "[style.colourDisabled(" + Util.capitaliseSentence(pattern.getName()) + ")]")
									+ "</div>");
						}
					}
				sb.append("</div>");
				
				// Covering Modifiers:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<p style='padding:0;margin:0;text-align:center;'>Модификаторы:</p>");
					if(activeCovering.getType().getNaturalModifiers().size() + activeCovering.getType().getExtraModifiers().size()>1) {
//						sb.append("<div class='container-full-width'>");
						for(CoveringModifier mod : activeCovering.getType().getNaturalModifiers()) {
							if (activeCovering.getModifier() == mod) {
								sb.append(
										"<div class='cosmetics-button active'>"
											+ "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
										+ "</div>");
							} else {
								sb.append(
										"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_MODIFIER_"+mod+"' class='cosmetics-button'>"
												+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
													? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
													: "[style.colourDisabled(" + Util.capitaliseSentence(mod.getName()) + ")]")
										+ "</div>");
							}
						}
						for(CoveringModifier mod : activeCovering.getType().getExtraModifiers()) {
							if (activeCovering.getModifier() == mod) {
								sb.append(
										"<div class='cosmetics-button active'>"
											+ "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
										+ "</div>");
							} else {
								sb.append(
										"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_MODIFIER_"+mod+"' class='cosmetics-button'>"
												+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
													? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
													: "[style.colourDisabled(" + Util.capitaliseSentence(mod.getName()) + ")]")
										+ "</div>");
							}
						}
//						sb.append("</div>");
						
					} else {
						sb.append("<p style='padding:0;margin:0;text-align:center;'>[style.italicsDisabled(Нет доступных)]</p>");
					}
				sb.append("</div>");
				
			sb.append("</div>");

			// Primary and secondary colours:
			sb.append("<div class='container-full-width' style='width:66.6%; padding:0; margin:0; text-align:center;'>");
			
				// Primary:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; background:transparent;'>");
						sb.append("Основной цвет");
						sb.append(" | <span style='color:"+activeCovering.getPrimaryColour().toWebHexString()+";"
										+(activeCovering.isPrimaryGlowing()
												?"text-shadow: 0px 0px 4px "+activeCovering.getPrimaryColour().getShades()[4]+";"
												:"")+"'>"
									+Util.capitaliseSentence(activeCovering.getPrimaryColour().getName())
								+"</span>");
					sb.append("</div>");
					for (Colour c : availablePrimaryColours) {
						if(c.getRainbowColours()!=null) {
							rainbow = c.getRainbowDiv(5);
						} else {
							rainbow = "";
						}
						sb.append("<div class='normal-button"+(activeCovering.getPrimaryColour()==c?" выбранный":"")+"' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PRIMARY_"+c.getId()+"' style='width:auto; margin-right:4px;"+(activeCovering.getPrimaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
											+ (c.isMetallic()
													?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:(c.getRainbowColours()!=null
														?"<div class='phone-item-colour' style='background: "+rainbow
														:"<div class='phone-item-colour' style='background"+(c==PresetColour.COVERING_CLEAR?"-изображение":"-цвет")+":" + (c.getCoveringIconColour()) + ";"))
												+(c==PresetColour.COVERING_NONE
													?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X"
													:"'>")
											+"</div>"
										+ "</div>");
					}
					
					if(activeCovering.getPrimaryColour()!=PresetColour.COVERING_NONE && withGlow) { // Glow:
						if(activeCovering.isPrimaryGlowing()) {
							sb.append(
									"<div class='normal-button active' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PRIMARY_GLOW_OFF' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>[style.boldArcane(Сияние Arcane)]</div>");
						} else {
							sb.append(
									"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PRIMARY_GLOW_ON' class='normal-button' style='width:50%; margin:1% 25%; padding:0; text-align:center;'><span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>Сияние Arcane</span></div>");
						}
					}
				sb.append("</div>");
				
				// Secondary:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; background:transparent;'>");
						sb.append("Дополнительный цвет");
						if(!secondaryDisabled) {
							sb.append(" | <span style='color:"+activeCovering.getSecondaryColour().toWebHexString()+";"
											+(activeCovering.isSecondaryGlowing()
													?"text-shadow: 0px 0px 4px "+activeCovering.getSecondaryColour().getShades()[4]+";"
													:"")+"'>"
										+Util.capitaliseSentence(activeCovering.getSecondaryColour().getName())
									+"</span>");
						}
					sb.append("</div>");
					if(secondaryDisabled) {
						sb.append("<p style='padding:0;margin:0;text-align:center;'>[style.italicsDisabled(Нет доступных)]</p>");
						
					} else {
						for (Colour c : availableSecondaryColours) {
							if(c.getRainbowColours()!=null) {
								rainbow = c.getRainbowDiv(5);
							} else {
								rainbow = "";
							}
							sb.append("<div class='normal-button"+(activeCovering.getSecondaryColour()==c?" выбранный":"")+"' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_SECONDARY_"+c.getId()+"' style='width:auto; margin-right:4px;"+(activeCovering.getSecondaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
												+ (c.isMetallic()
														?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
														:(c.getRainbowColours()!=null
															?"<div class='phone-item-colour' style='background: "+rainbow
															:"<div class='phone-item-colour' style='background"+(c==PresetColour.COVERING_CLEAR?"-изображение":"-цвет")+":" + (c.getCoveringIconColour()) + ";"))
													+(c==PresetColour.COVERING_NONE
														?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X"
														:"'>")
												+"</div>"
											+ "</div>");
						}
					}
					
					if(activeCovering.getSecondaryColour() != PresetColour.COVERING_NONE && withGlow && !secondaryDisabled) { // Glow:
						if(activeCovering.isSecondaryGlowing()) {
							sb.append(
									"<div class='normal-button active' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_SECONDARY_GLOW_OFF' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>[style.boldArcane(Сияние Arcane)]</div>");
						} else {
							sb.append(
									"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_SECONDARY_GLOW_ON' class='normal-button' style='width:50%; margin:1% 25%; padding:0; text-align:center;'><span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>Сияние Arcane</span></div>");
						}
					}
				sb.append("</div>");
			
			sb.append("</div>");

			if(coveringType==BodyCoveringType.MAKEUP_LIPSTICK && Main.getProperties().hasValue(PropertyValue.lipstickMarkingContent)) {
				boolean heavyLipstick = BodyChanging.getTarget().isHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<div class='container-full-width' style='width:60%; padding:0; margin:0; text-align:center;'>");
						sb.append(UtilText.parse(BodyChanging.getTarget(),
								"Если нанести губную помаду толстым слоем, [npc.name] будет оставлять следы на любой части [npc.she] [npc.verb(kiss)]!<br/>[style.italics(Толстый слой помады нужно обновлять после секс-сцен, в которых она использовалась.)]"));
					sb.append("</div>");
					sb.append("<div class='container-full-width' style='width:40%; padding:0; margin:0; text-align:center;'>");
						if(!heavyLipstick) {
							sb.append(
									"<div class='cosmetics-button active'>"
										+ "<span style='color:" + PresetColour.BASE_PINK_LIGHT.toWebHexString() + ";'>Обычная</span></div>");
						} else {
							sb.append(
									"<div id='MAKEUP_LIPSTICK_HEAVY_OFF' class='cosmetics-button'>"
										+ "<span style='color:"+PresetColour.BASE_PINK_LIGHT.getShades()[0]+";'>Обычная</span></div>");
						}
						if(heavyLipstick) {
							sb.append(
									"<div class='cosmetics-button active'>"
										+ "<span style='color:" + PresetColour.BASE_PINK_DEEP.toWebHexString() + ";'>Густая</span></div>");
						} else {
							sb.append(
									"<div id='MAKEUP_LIPSTICK_HEAVY_ON' class='cosmetics-button'>"
										+ "<span style='color:"+PresetColour.BASE_PINK_DEEP.getShades()[0]+";'>Густая</span></div>");
						}
					sb.append("</div>");
				sb.append("</div>");
			}
			
			// Reset/Apply changes buttons:
			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center;'>");
				sb.append("<div class='container-full-width' style='width:50%; padding:0; margin:0; text-align:center;'>");
					if(disabledButton) {
						sb.append("<div class='normal-button disabled' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;'>"
									+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Сбросить изменения</span></div>");
					} else {
						sb.append("<div class='normal-button' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;' id='RESET_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"'>[style.colourMinorBad(Сбросить изменения)]</div>");
					}
				sb.append("</div>");
				sb.append("<div class='container-full-width' style='width:50%; padding:0; margin:0; text-align:center;'>");
					if(disabledButton) {
						sb.append("<div class='normal-button disabled' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;'>"
									+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Применить изменения"
										+ (withCost
											?" ("+UtilText.formatAsMoneyUncoloured(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "охват")+")"
											:"")
									+"</span>"
								+ "</div>");
					} else {
						sb.append("<div class='normal-button' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;' id='APPLY_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"'>[style.colourMinorGood(Применить изменения)]"
									+ (withCost
										?" ("
											+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
												? UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "охват")
												: UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "охват", PresetColour.GENERIC_BAD))+")"
										:"")
									+ "</div>");
					}
				sb.append("</div>");
			sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String getKatesDivPiercings(boolean noCost) {
		contentSB.setLength(0);
		
		boolean isPierced = false;
		boolean canPierce = true;
		
		String title="";
		String  description="";
		int i=0;
		
		for(PiercingType piercingType : PiercingType.values()) {
			title = Util.capitaliseSentence(piercingType.getName())+" Прокол";
			description = piercingType.getDescription();
			
			switch(piercingType) {
				case EAR:
					isPierced = BodyChanging.getTarget().isPiercedEar();
					break;
				case LIP:
					isPierced = BodyChanging.getTarget().isPiercedLip();
					break;
				case NAVEL:
					isPierced = BodyChanging.getTarget().isPiercedNavel();
					break;
				case NOSE:
					isPierced = BodyChanging.getTarget().isPiercedNose();
					break;
				case TONGUE:
					isPierced = BodyChanging.getTarget().isPiercedTongue();
					break;
					
				case NIPPLE:
					isPierced = BodyChanging.getTarget().isPiercedNipple();
					break;
				case PENIS:
					canPierce = BodyChanging.getTarget().hasPenisIgnoreDildo();
					isPierced = BodyChanging.getTarget().isPiercedPenis();
					break;
				case VAGINA:
					canPierce = BodyChanging.getTarget().hasVagina();
					isPierced = BodyChanging.getTarget().isPiercedVagina();
					break;
			}

			contentSB.append("<div class='container-full-width' style='padding:4px;margin:0;width:100%;background:"+(i%2==0?PresetColour.BACKGROUND_ALT:PresetColour.BACKGROUND).toWebHexString()+";'>");
				
				if(noCost) {
					contentSB.append("<div class='container-half-width' style='padding:0;margin:0;width:50%;background:transparent;text-align:center;'>");
						if(canPierce) {
							contentSB.append(title);
						} else {
							contentSB.append("[style.colourDisabled("+title+")]");
						}
					contentSB.append("</div>");
					
				} else {
					contentSB.append("<div class='container-half-width' style='padding:0;margin:0 5% 0 0;width:35%;background:transparent;text-align:right;'>");
						if(canPierce) {
							contentSB.append(title);
						} else {
							contentSB.append("[style.colourDisabled("+title+")]");
						}
					contentSB.append("</div>");
					contentSB.append("<div class='container-half-width' style='padding:0;margin:0;width:10%;background:transparent;text-align:left;'>");
						if(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(piercingType)) {
							contentSB.append(UtilText.formatAsMoney(SuccubisSecrets.getPiercingCost(piercingType), "b"));
						} else {
							contentSB.append(UtilText.formatAsMoney(SuccubisSecrets.getPiercingCost(piercingType), "b", PresetColour.GENERIC_BAD));
						}
					contentSB.append("</div>");
				}
				
				contentSB.append(
						getInformationDiv(
								"PIERCING_INFO_"+piercingType,
								new TooltipInformationEventListener().setInformation(title, description),
								true));

				contentSB.append("<div class='container-half-width' style='padding:0;margin:0;width:50%;background:transparent;'>");
					
					if(isPierced) {
						contentSB.append(
								"<div id='"+piercingType+"_PIERCE_REMOVE' class='cosmetics-button'>[style.colourDisabled(Без проколов)]</div>");
						
						contentSB.append(
								"<div class='cosmetics-button active'>[style.boldArcane(С проколами)]</div>");
					} else {
						contentSB.append(
								"<div class='cosmetics-button active'>Без проколов</div>");
						
						if(canPierce) {
							contentSB.append(
									"<div id='"+piercingType+"_ПРОТЫКАНА' class='cosmetics-button'>"
										+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(piercingType) || noCost
											?"<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>С проколами</span>"
											:"[style.colourDisabled(Проколотый)]")
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div class='cosmetics-button disabled'>[style.colourDisabled(Проколотый)]</div>");
						}
					}
				contentSB.append("</div>");
			
			contentSB.append("</div>");
			
			i++;
		}
		
		
		return contentSB.toString();
	}

	public static String getKatesDivTattoos() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
//				+ "<h5 style='width:100%; text-align:center;'>Main Areas</h5>");
		
		for(InventorySlot invSlot : RenderingEngine.mainInventorySlots) {
			contentSB.append(getTattooDiv(invSlot));
		}
		
//		contentSB.append("</div>");
//		
//		contentSB.append("<div class='container-full-width'>"
//				+ "<h5 style='width:100%; text-align:center;'>Extra Areas</h5>");
		
		for(InventorySlot invSlot : RenderingEngine.secondaryInventorySlots) {
			contentSB.append(getTattooDiv(invSlot));
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getTattooDiv(InventorySlot invSlot) {
		Tattoo tattooInSlot = BodyChanging.getTarget().getTattooInSlot(invSlot);
		boolean disabled = false;
		
		switch(invSlot) {
			case HORNS:
				if(BodyChanging.getTarget().getHornType().equals(HornType.NONE)) {
					disabled = true;
				}
				break;
			case PENIS:
				if(!BodyChanging.getTarget().hasPenisIgnoreDildo()) {
					disabled = true;
				}
				break;
			case TAIL:
				if(BodyChanging.getTarget().getTailType()==TailType.NONE) {
					disabled = true;
				}
				break;
			case VAGINA:
				if(!BodyChanging.getTarget().hasVagina()) {
					disabled = true;
				}
				break;
			case WINGS:
				if(BodyChanging.getTarget().getWingType()==WingType.NONE) {
					disabled = true;
				}
				break;
			default:
				break;
		}
		
		return "<div class='container-half-width inner' style='width:23%;margin:1%;'>"
				+ "<div class='container-full-width inner' style='width:100%; margin:0; text-align:center;'>"+Util.capitaliseSentence(invSlot.getTattooSlotName())+"</div>"
				+(disabled
					?"<div class='inventory-item-slot disabled' style='width:48%;margin:0 1%'></div>"
					:"<div class='modifier-icon' style='width:48%;margin:0 1%'>"
						+ (tattooInSlot==null
							?"<div class='modifier-icon-content'></div>"
							:"<div class='modifier-icon-content' style='background-color:"+tattooInSlot.getRarity().getBackgroundColour().toWebHexString()+";'>"+tattooInSlot.getSVGImage(BodyChanging.getTarget())+"</div>")
						+ "<div class='overlay no-pointer' id='TATTOO_INFO_"+invSlot.toString()+"'></div>"
					+ "</div>")
				
				+ "<div class='container-half-width inner' style='width:48%;margin:0 1%;padding:0;'><div style='float:left; width:98%; margin:0 1%; padding:0;'><div class='normal-button"+(disabled?" disabled":"")+"' "+(!disabled?"id='TATTOO_ADD_REMOVE_"+invSlot.toString()+"'":"")+" style='width:100%;'>"
							+(tattooInSlot==null
								?"Добавить"
								:(SuccubisSecrets.invSlotTattooToRemove==invSlot || !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?"[style.colourBad(Убрать)]":"Убрать"))
						+"</div>"
					+ "</div>"
					+ (Main.game.isInNewWorld()
							?"<div style='float:left; width:98%; margin:0 1%; padding:0;'><div class='normal-button"+(disabled || tattooInSlot==null?" disabled":"")+"' "+(!disabled && tattooInSlot!=null?"id='TATTOO_MODIFY_"+invSlot.toString()+"'":"")+" style='width:100%;'>Изменить</div></div>"
							:"")
					+ (Main.game.isInNewWorld()
						?"<div style='float:left; width:98%; margin:0 1%; padding:0;'><div class='normal-button"+(disabled || tattooInSlot==null?" disabled":"")+"' "+(!disabled && tattooInSlot!=null?"id='TATTOO_ENCHANT_"+invSlot.toString()+"'":"")+" style='width:100%;'>Зачаровать</div></div>"
						:"")
				+ "</div>"
			+ "</div>";
	}
	
	public static InventorySlot tattooInventorySlot = null;
	public static Tattoo tattoo = null;
	public static boolean retroactiveApplicationPreferZeroStart = false;
	
	public static void resetTattooVariables(InventorySlot slot) {
		tattooInventorySlot = slot;
		
		retroactiveApplicationPreferZeroStart = false;
		
		tattoo = new Tattoo(
				"innoxia_symbol_tribal",
				PresetColour.CLOTHING_GREY,
				null,
				null,
				false,
				new TattooWriting(
						"",
						PresetColour.BASE_GREY,
						false),
				new TattooCounter(
						TattooCounterType.NONE,
						TattooCountType.NUMBERS,
						PresetColour.BASE_GREY,
						false,
						0));
	}
	
	public static void resetTattooColours() {
		if(!tattoo.getType().getAvailablePrimaryColours().contains(tattoo.getPrimaryColour())) {
			tattoo.setPrimaryColour(tattoo.getType().getAvailablePrimaryColours().get(0));
		}
		
		if(!tattoo.getType().getAvailableSecondaryColours().contains(tattoo.getSecondaryColour())) {
			if(tattoo.getType().getAvailableSecondaryColours().isEmpty()) {
				tattoo.setSecondaryColour(null);
			} else {
				tattoo.setSecondaryColour(tattoo.getType().getAvailableSecondaryColours().get(0));
			}
		}

		if(!tattoo.getType().getAvailableTertiaryColours().contains(tattoo.getTertiaryColour())) {
			if(tattoo.getType().getAvailableTertiaryColours().isEmpty()) {
				tattoo.setTertiaryColour(null);
			} else {
				tattoo.setTertiaryColour(tattoo.getType().getAvailableTertiaryColours().get(0));
			}
		}
		
		tattoo.setGlowing(false);
	}
	
	public static String getKatesDivTattoosAdd() {
		contentSB.setLength(0);
		
		// Type:
	
		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<div class='container-full-width' style='width:75%; margin:0; position:relative; text-align:center;'>");
				contentSB.append("<h5 style='width:100%; text-align:center;'>Выбрать тип</h5>");
		
				for(AbstractTattooType type : TattooType.getConditionalTattooTypes(BodyChanging.getTarget())) {
					if(type.getSlotAvailability().contains(tattooInventorySlot)) {
						contentSB.append("<div style='width:23%; margin:1%; padding:0; display:inline-block;'><div class='normal-button"+(tattoo.getType()==type?" выбранный":"")+"' id='TATTOO_TYPE_"+type.getId()+"' style='width:100%; margin:0; color:"+(tattoo.getType()==type?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</div>"
										+ "</div>");
						
					} else {
						contentSB.append("<div style='width:23%; margin:1%; padding:0; display:inline-block;'><div class='normal-button disabled' id='TATTOO_TYPE_"+type.getId()+"' style='width:100%; margin:0;'>"+Util.capitaliseSentence(type.getName())+"</div>"
							+ "</div>");
					}
				}
				contentSB.append("</div>"
						+ "<div class='container-full-width' style='width:25%; margin:0;'>");

//				background-color:"+Main.game.getPlayer().getCovering(Main.game.getPlayer().getTorsoCovering()).getPrimaryColour().toWebHexString()+";
				
				contentSB.append("<div class='modifier-icon' style='float:left; width:100%; margin:0; text-align:center;'>"
									+ "<div class='modifier-icon-content'>"+tattoo.getSVGImage(BodyChanging.getTarget())+"</div>"
									+ "<div class='overlay no-pointer' id='NEW_TATTOO_INFO'></div>"
								+ "</div>");
			
			contentSB.append("</div>");
		contentSB.append("</div>");
	
		// Colours:

		contentSB.append("<div class='container-full-width'><h5 style='width:100%; text-align:center;'>Выбрать цвета</h5>");
			
			// Primary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
				for (Colour c : tattoo.getType().getAvailablePrimaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getPrimaryColour()==c?" выбранный":"")+"' id='TATTOO_COLOUR_PRIMARY_"+c.getId()+"' style='width:auto; margin-right:4px;"+(tattoo.getPrimaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'><div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			contentSB.append("</div>");

			// Secondary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
			if(tattoo.getType().getAvailableSecondaryColours().isEmpty()) {
				contentSB.append(
						"<p style='text-align:center;'>[style.italicsDisabled(Дополнительные цвета отсутствуют...)]</p>");
				
			} else {
				for (Colour c : tattoo.getType().getAvailableSecondaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getSecondaryColour()==c?" выбранный":"")+"' id='TATTOO_COLOUR_SECONDARY_"+c.getId()+"' style='width:auto; margin-right:4px;"+(tattoo.getSecondaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'><div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("</div>");

			// Tertiary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
			if(tattoo.getType().getAvailableTertiaryColours().isEmpty()) {
				contentSB.append(
						"<p style='text-align:center;'>[style.italicsDisabled(Нет доступных дополнительных цветов...)]</p>");
				
			} else {
				for (Colour c : tattoo.getType().getAvailableTertiaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getTertiaryColour()==c?" выбранный":"")+"' id='TATTOO_COLOUR_TERTIARY_"+c.getId()+"' style='width:auto; margin-right:4px;"+(tattoo.getTertiaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'><div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("</div>");
			
			if(Main.game.isInNewWorld()) {
				contentSB.append("<div class='container-full-width'>");
					if(tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))) {
						contentSB.append(
								"<div class='normal-button disabled' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>Свечение</div>");
						
					} else if(tattoo.isGlowing()) {
						contentSB.append(
								"<div class='normal-button active' id='TATTOO_GLOW' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>[style.boldArcane(Свечение Arcane)]</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_GLOW' class='normal-button' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>Сияние Arcane</span></div>");
					}
				contentSB.append("</div>");
			}

		contentSB.append("</div>");
	
		// Writing:

		contentSB.append("<div class='container-full-width'><h5 style='width:100%; text-align:center;'>Выбрать стиль письма</h5>");
		
			contentSB.append("<div class='container-full-width' style='width:66.6%; margin:0; position:relative; text-align:center;'>"
					+"<form style='padding:0; margin:0 0 4px 0; text-align:center;'>"
						+ "<input type='text' id='tattoo_name' value='" +UtilText.parseForHTMLDisplay(tattoo.getWriting().getText())+"' style='padding:0;margin:0;width:80%;'></form><p style='width:100%; text-align:center;'>Стиль письма</p>");
			for(TattooWritingStyle style : TattooWritingStyle.values()) {
				contentSB.append("<div style='width:18%; margin:0 1%; padding:0; display:inline-block;'><div class='normal-button"+(tattoo.getWriting().getStyles().contains(style)?" выбранный":"")+"' id='TATTOO_WRITING_STYLE_"+style.toString()+"' style='width:100%; margin:0; color:"+(tattoo.getWriting().getStyles().contains(style)?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"+Util.capitaliseSentence(style.getName())+"</div>"
								+ "</div>");
			}
			contentSB.append("<div class='container-full-width'>"
					+ "Output: "+tattoo.getFormattedWritingOutput()
					+ "</div>");
			contentSB.append("</div>");
			
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
				for (Colour c : TattooWriting.getAvailableColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getWriting().getColour()==c?" выбранный":"")+"' id='TATTOO_WRITING_COLOUR_"+c.getId()+"' style='width:auto; margin-right:4px;"+(tattoo.getWriting().getColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'><div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
				if(Main.game.isInNewWorld()) {
					contentSB.append("<br/>");
					if(tattoo.getWriting().isGlow()) {
						contentSB.append(
								"<div class='normal-button selected' id='TATTOO_WRITING_GLOW' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>[style.boldArcane(Сияние Аркана)]</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_WRITING_GLOW' class='normal-button' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>Сияние Arcane</span></div>");
					}
				}
			contentSB.append("</div>");
		contentSB.append("</div>");

		// Counter:
		if(Main.game.isInNewWorld()) {
			contentSB.append("<div class='container-full-width'>");

			contentSB.append("<div class='container-full-width inner' style='margin:0; padding:0; width:100%; text-align:center; background:transparent;'>");
				contentSB.append("<h5 style='width:100%; text-align:center;'>Выбрать счетчик</h5>");
			contentSB.append("</div>");
			
			contentSB.append(getInformationDiv(
					"TATTOO_COUNTER_INFO",
					new TooltipInformationEventListener().setInformation(
							"Счетчик татуировок",
							"Счетчики татуировок зачарованы так, чтобы они обновлялись автоматически по мере увеличения типа счетчика. Большинство типов счетчиков могут либо начинаться с 0, либо показывать весь предыдущий опыт. Некоторые же могут показывать только текущие значения, и они отмечены звездочкой."),
					false));
			
				contentSB.append("<div class='container-full-width' style='width:66.6%; margin:0;'>");
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center; margin-top:0; padding-top:0;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>Тип счетчика</p>");
						for(TattooCounterType counterType : TattooCounterType.getTattooCounterTypesWithContentFiltersApplied()) {
							contentSB.append("<div style='width:48%; margin:1%; padding:0; display:inline-block;'><div class='normal-button"+(tattoo.getCounter().getType()==counterType?" выбранный":"")+"' id='TATTOO_COUNTER_TYPE_"+counterType.toString()+"' style='width:100%; margin:0; color:"+(tattoo.getCounter().getType()==counterType?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"
													+Util.capitaliseSentence(counterType.getName())
													+ (counterType.isRetroactiveApplicationAvailable()?"":" *")
												+"</div>"
											+ "</div>");
						}
					contentSB.append("</div>");
				contentSB.append("</div>");
				
				contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
					for (Colour c : TattooCounter.getAvailableColours()) {
						contentSB.append("<div class='normal-button"+(tattoo.getCounter().getColour()==c?" выбранный":"")+"' id='TATTOO_COUNTER_COLOUR_"+c.getId()+"' style='width:auto; margin-right:4px;"+(tattoo.getCounter().getColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'><div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" цвет:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
										+ "</div>");
					}
					contentSB.append("<br/>");
					if(tattoo.getCounter().isGlow()) {
						contentSB.append(
								"<div class='normal-button selected' id='TATTOO_COUNTER_GLOW' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>[style.boldArcane(Сияние Аркана)]</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_COUNTER_GLOW' class='normal-button' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>Сияние Arcane</span></div>");
					}
					
					boolean activeButtonZero = tattoo.getCounter().getType().getNonRetroactiveOffset(BodyChanging.getTarget())==0?retroactiveApplicationPreferZeroStart:tattoo.getCounter().isRetroactiveApplication();
					
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>Начальное количество</p>");

						if(tattoo.getCounter().getType().isRetroactiveApplicationAvailable()) {
							contentSB.append("<div style='width:98%; margin:1%; padding:0; display:inline-block;'>");
								contentSB.append("<div class='normal-button"+(activeButtonZero?" выбранный":"")+"' id='TATTOO_COUNT_RETROACTIVE_DISABLED' style='width:100%; margin:0; color:"+(activeButtonZero?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>");
									contentSB.append("Начать с 0");
								contentSB.append("</div>");
							contentSB.append("</div>");
							contentSB.append("<div style='width:98%; margin:1%; padding:0; display:inline-block;'>");
								contentSB.append("<div class='normal-button"+(!activeButtonZero?" выбранный":"")+"' id='TATTOO_COUNT_RETROACTIVE_ENABLED' style='width:100%; margin:0; color:"+(!activeButtonZero?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>");
									contentSB.append("Показать все");
								contentSB.append("</div>");
							contentSB.append("</div>");
							
						} else {
							contentSB.append("<div class='container-full-width' style='margin:0; padding:0;'>");
								contentSB.append("[style.colourDisabled(<i>Этот тип счетчика всегда отображает текущее значение, поэтому начальное количество не применимо.</i>)]");
							contentSB.append("</div>");
						}
							
					contentSB.append("</div>");
					
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>Стиль счетчика</p>");
						for(TattooCountType countType : TattooCountType.values()) {
							contentSB.append("<div style='width:98%; margin:1%; padding:0; display:inline-block;'><div class='normal-button"+(tattoo.getCounter().getCountType()==countType?" выбранный":"")+"' id='TATTOO_COUNT_TYPE_"+countType.toString()+"' style='width:100%; margin:0; color:"+(tattoo.getCounter().getCountType()==countType?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"
													+Util.capitaliseSentence(countType.getName())+"</div>"
											+ "</div>");
						}
					contentSB.append("</div>");
					
					contentSB.append("<div class='container-full-width'>");
						if(tattoo.getCounter().getType()==TattooCounterType.NONE) {
							contentSB.append("[style.colourDisabled(Результат: <i>(Тип счетчика — 'нет', так что у этой татуировки не будет счетчика.)</i>)]");
						} else {
							contentSB.append("Результат: "+tattoo.getFormattedCounterOutput(BodyChanging.getTarget()));
						}
					contentSB.append("</div>");
				contentSB.append("</div>");
				
			contentSB.append("</div>");
		}
		
		contentSB.append("<p id='hiddenPField' style='display:none;'></p>");
		
		return contentSB.toString();
	}
	
}
