package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia
 */
public class PenisType {
	
	public static AbstractPenisType NONE = new AbstractPenisType(null,
			Race.NONE,
			TesticleType.NONE,
			"[npc.She] [npc.verb(squirm)] и [npc.moansVerb], словно [npc.her] член и яички, стремительно уменьшаются, и через несколько секунд не остается ничего, что напоминало бы [npc.herHim] о [npc.her] мужественности.<br/>[npc.Name] теперь [npc.has] [style.boldSex(no penis)].",
			"", // Shouldn't need a description of no cock.
			null) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(!applicationAfterChangeApplied) {
				owner.setPiercedPenis(false);
			}
			return "";
		}
	};

	public static AbstractPenisType DILDO = new AbstractPenisType(BodyCoveringType.DILDO,
			Race.NONE,
			TesticleType.DILDO,
			Util.newArrayListOfValues("дилдо"),
			Util.newArrayListOfValues("дилдо"),
			Util.newArrayListOfValues("дилдо"),
			Util.newArrayListOfValues("дилдо"),
			Util.newArrayListOfValues("резиновый", "резиновый", "силикон", "искусственный"),
			"Вы каким-то образом превратили свой член в дилдо, что является багом. (Пожалуйста, сообщите об этом Innoxia!)", // Dildos are not a transformable option
			"[npc.She] [npc.is] сейчас носит [npc.a_cockGirth], [npc.cockLengthValue] дилдо, сделанное из [npc.cockFullDescription(true)].",
			null) {
	};
	
	public static AbstractPenisType HUMAN = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HUMAN,
			TesticleType.HUMAN,
			"[npc.She] теперь [npc.has] [style.boldHuman(человеческий член)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldHuman([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF человеческие яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldHuman(человеческую сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] человеческий член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			null) {
	};

	public static AbstractPenisType ANGEL = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.ANGEL,
			TesticleType.ANGEL,
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldAngel(ангельский член)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldAngel([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF ангельские яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldAngel(ангельскую сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] ангельский член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			null) {
	};

	public static AbstractPenisType DEMON_COMMON = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.DEMON,
			TesticleType.DEMON_COMMON,
			Util.newArrayListOfValues("суккуб-"),
			Util.newArrayListOfValues("суккуб-"),
			Util.newArrayListOfValues("инкуб-"),
			Util.newArrayListOfValues("инкуб-"),
			Util.newArrayListOfValues("демонический"),
			"[npc.She] [npc.verb(squirm)] и [npc.moansVerb], когда кожа, покрывающая [npc.her] член, превращается в гладкий, сверхчувствительный демонический орган. С кончика начинает сочиться липкая предэякуляционная смазка, и [npc.she] [npc.verb(let)] [npc.a_moan+], когда по всей его длине внезапно проступают толстые рельефные жилы. Как будто этого было мало, ряды маленьких бугорков начинают выпирать и превращаться в крошечные щупальца, которые затем начинают извиваться сами по себе.<br/>[npc.She] теперь [npc.has] #IF(npc.isShortStature()) [style.boldImp(бесовский член)]#ELSE [style.boldDemon(демонический член)]#ENDIF, [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF бесовские яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldImp(бесовскую сперму)].#ELSE [style.boldDemon([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF демонические яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldDemon(демоническую сперму)].#ENDIF",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] демонический член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.RIBBED,
				PenetrationModifier.TENTACLED,
				PenetrationModifier.PREHENSILE)) {
	};

	public static AbstractPenisType COW_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.COW_MORPH,
			TesticleType.BOVINE,
			Util.newArrayListOfValues("коровий-"),
			Util.newArrayListOfValues("коровий-"),
			Util.newArrayListOfValues("бычий-"),
			Util.newArrayListOfValues("бычий-"),
			Util.newArrayListOfValues(""),
			"Издавая непроизвольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна непреодолимого возбуждения, когда [npc.her] ствол становится шире, а головка сужается к кончику.<br/>[npc.She] теперь [npc.has] [style.boldCowMorph(бычий пенис)], [npc.materialDescriptor] [npc.penisFullDescription].<br/>[npc.She] [npc.has] [style.boldCowMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF бычьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldCowMorph(бычью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] #IF(npc.isFeminine())коровье#ELSEбычье#ENDIF член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.TAPERED,
				PenetrationModifier.VEINY,
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType DOG_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.DOG_MORPH,
			TesticleType.CANINE,
			Util.newArrayListOfValues("собачий-", "сучья-"),
			Util.newArrayListOfValues("собачий-", "сучья-"),
			Util.newArrayListOfValues("собачий-"),
			Util.newArrayListOfValues("собачий-"),
			Util.newArrayListOfValues("пёсьи"),
			"Издавая невольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна непреодолимого возбуждения, когда у основания [npc.her] ствола внезапно проступает толстое утолщение. Пока [npc.she] [npc.verb(pant)] и [npc.verb(gasp)], жадно хватая ртом воздух, головка [npc.her] члена сужается, переходя в свою новую форму.<br/>[npc.She] теперь [npc.has] [style.boldDogMorph(собачий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldDogMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF собачьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldDogMorph(собачью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] собачий член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};
	
	public static AbstractPenisType WOLF_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.WOLF_MORPH,
			TesticleType.LUPINE,
			Util.newArrayListOfValues("волчья-"),
			Util.newArrayListOfValues("волчья-"),
			Util.newArrayListOfValues("волчья-"),
			Util.newArrayListOfValues("волчья-"),
			Util.newArrayListOfValues("волчий"),
			"Издавая непроизвольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис, принимающий новую форму, и [npc.sheIs] накрывает волна непреодолимого возбуждения, когда у основания [npc.her] ствола внезапно проступает толстое утолщение. Пока [npc.she] [npc.verb(pant)] и [npc.verb(gasp)], жадно хватая ртом воздух, головка [npc.her] члена сужается, переходя в свою новую форму.<br/>[npc.She] теперь [npc.has] [style.boldWolfMorph(волчий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldWolfMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF волчьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldWolfMorph(волчью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] волчий член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};
	
	public static AbstractPenisType FOX_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.FOX_MORPH,
			TesticleType.FOX_MORPH,
			Util.newArrayListOfValues("лисья-", "лисичья-"),
			Util.newArrayListOfValues("лисья-", "лисичья-"),
			Util.newArrayListOfValues("лисья-"),
			Util.newArrayListOfValues("лисья-"),
			Util.newArrayListOfValues("лисьи"),
			"Издавая непроизвольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна непреодолимого возбуждения, когда у основания [npc.her] ствола внезапно проступает толстое утолщение. Пока [npc.she] [npc.verb(pant)] и [npc.verb(gasp)], жадно хватая ртом воздух, головка [npc.her] члена сужается, переходя в свою новую форму.<br/>[npc.She] теперь [npc.has] [style.boldFoxMorph(лисий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldFoxMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF лисьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldFoxMorph(лисью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] лисий член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};

	public static AbstractPenisType CAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.CAT_MORPH,
			TesticleType.FELINE,
			Util.newArrayListOfValues("кошачья-"),
			Util.newArrayListOfValues("кошачья-"),
			Util.newArrayListOfValues("кошачья-"),
			Util.newArrayListOfValues("кошачья-"),
			Util.newArrayListOfValues("кошачьими"),
			"Издавая непроизвольный стон, [npc.she] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна всепоглощающего возбуждения, когда ряды плотских маленьких загнутых назад шипов проступают по всей длине [npc.her] ствола.<br/>[npc.She] теперь [npc.has] [style.boldCatMorph(кошачий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldCatMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF кошачьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldCatMorph(кошачья сперма)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] кошачий член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.BARBED,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType ALLIGATOR_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.ALLIGATOR_MORPH,
			TesticleType.ALLIGATOR_MORPH,
			Util.newArrayListOfValues("аллигаторийский-"),
			Util.newArrayListOfValues("аллигаторийский-"),
			Util.newArrayListOfValues("аллигаторийский-"),
			Util.newArrayListOfValues("аллигаторийский-"),
			Util.newArrayListOfValues("рептилоидные"),
			"Издавая непроизвольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна всепоглощающего возбуждения, когда [npc.her] ствол становится эрегированным, а головка разглаживается.<br/>[npc.She] теперь [npc.has] [style.boldGatorMorph(аллигаторий пенис)], [npc.materialDescriptor] [npc.penisFullDescription].<br/>[npc.She] [npc.has] [style.boldGatorMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF рептильные яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldGatorMorph(аллигаторий сперма)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] аллигаторовый член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.BLUNT)) {
	};

	public static AbstractPenisType EQUINE = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HORSE_MORPH,
			TesticleType.EQUINE,
			Util.newArrayListOfValues("кобылий-", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF-", "конский-"),
			Util.newArrayListOfValues("кобылий-", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF-", "конский-"),
			Util.newArrayListOfValues("жеребцовый-", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF-", "конский-"),
			Util.newArrayListOfValues("жеребцовый-", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF-", "конский-"),
			Util.newArrayListOfValues(""),
			"Издавая непроизвольный стон, [npc.name] [npc.verb(feel)] [npc.her] член принимает новую форму, и [npc.sheIs] накрывает волна непреодолимого возбуждения, когда [npc.her] ствол становится шире, а головка уплощается.<br/>[npc.She] теперь [npc.has] [style.boldHorseMorph(лошадиный член)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldHorseMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF лошадиные яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldHorseMorph(лошадиную сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] лошадиный член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.FLARED,
				PenetrationModifier.VEINY,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType REINDEER_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.REINDEER_MORPH,
			TesticleType.REINDEER_MORPH,
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues(""),
			"Издавая невольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна непреодолимого Возбуждения, пока ствол [npc.her] становится шире, а головка уплощается.<br/>[npc.She] теперь [npc.has] [style.boldReindeerMorph(олений пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldReindeerMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF оленьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldReindeerMorph(оленью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] олений член, который является [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.TAPERED,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType HARPY = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HARPY,
			TesticleType.AVIAN,
			Util.newArrayListOfValues("гарпии-"),
			Util.newArrayListOfValues("гарпии-"),
			Util.newArrayListOfValues("гарпии-"),
			Util.newArrayListOfValues("гарпии-"),
			Util.newArrayListOfValues("птичий"),
			"Издавая невольный стон, [npc.name] [npc.verb(feel)] [npc.her] пенис принимает новую форму, и [npc.sheIs] накрывает волна непреодолимого Возбуждения, когда он втягивается в новую препуцию, сформировавшуюся у основания.<br/>[npc.She] теперь [npc.has] [style.boldHarpy(птичий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldHarpy([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF птичьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldHarpy(птичью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] птичий член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType SQUIRREL_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.SQUIRREL_MORPH,
			TesticleType.SQUIRREL,
			Util.newArrayListOfValues("беличий-"),
			Util.newArrayListOfValues("беличий-"),
			Util.newArrayListOfValues("беличий-"),
			Util.newArrayListOfValues("беличий-"),
			Util.newArrayListOfValues("грызуний"),
			"[npc.She] теперь [npc.has] [style.boldSquirrelMorph(пенис белки)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldSquirrelMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF беличьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldSquirrelMorph(беличью сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] беличий член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType RAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.RAT_MORPH,
			TesticleType.RAT_MORPH,
			Util.newArrayListOfValues("крысиный-"),
			Util.newArrayListOfValues("крысиный-"),
			Util.newArrayListOfValues("крысиный-"),
			Util.newArrayListOfValues("крысиный-"),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldRatMorph(пенис крысы)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldRatMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF крысиные яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldRatMorph(крысиную сперму)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] крысиный член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType RABBIT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.RABBIT_MORPH,
			TesticleType.RABBIT_MORPH,
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldRabbitMorph(кроличий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldRabbitMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF кроличьи яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldRabbitMorph(кроличий сперма)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] кроличий член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType BAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.BAT_MORPH,
			TesticleType.BAT_MORPH,
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldBatMorph(летучий пенис)], [npc.materialDescriptor] [npc.penisFullDescription(true)].<br/>[npc.She] [npc.has] [style.boldBatMorph([npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние,#ENDIF летучие яички)], [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] [style.boldBatMorph(летучая сперма)].",
			"[npc.She] [npc.has] [npc.a_cockGirth], [npc.cockLengthValue] летучий член, который [npc.materialCompositionDescriptor] [npc.cockFullDescription(true)].",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	
	private static List<AbstractPenisType> allPenisTypes;
	private static Map<AbstractPenisType, String> penisToIdMap = new HashMap<>();
	private static Map<String, AbstractPenisType> idToPenisMap = new HashMap<>();
	
	static {
		allPenisTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("penis")) {
					try {
						AbstractPenisType type = new AbstractPenisType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allPenisTypes.add(type);
						penisToIdMap.put(type, id);
						idToPenisMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("penis")) {
					try {
						AbstractPenisType type = new AbstractPenisType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allPenisTypes.add(type);
						penisToIdMap.put(type, id);
						idToPenisMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded penis types:
		
		Field[] fields = PenisType.class.getFields();
		
		for(Field f : fields){
			if (AbstractPenisType.class.isAssignableFrom(f.getType())) {
				
				AbstractPenisType ct;
				try {
					ct = ((AbstractPenisType) f.get(null));

					penisToIdMap.put(ct, f.getName());
					idToPenisMap.put(f.getName(), ct);
					
					allPenisTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allPenisTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractPenisType getPenisTypeFromId(String id) {
		if(id.equals("IMP")) {
			return PenisType.DEMON_COMMON;
		}
		if(id.equals("BOVINE")) {
			return PenisType.COW_MORPH;
		}
		if(id.equals("CANINE")) {
			return PenisType.DOG_MORPH;
		}
		if(id.equals("LUPINE")) {
			return PenisType.WOLF_MORPH;
		}
		if(id.equals("VULPINE")) {
			return PenisType.FOX_MORPH;
		}
		if(id.equals("FELINE")) {
			return PenisType.CAT_MORPH;
		}
		if(id.equals("AVIAN")) {
			return PenisType.HARPY;
		}
		if(id.equals("SQUIRREL")) {
			return PenisType.SQUIRREL_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToPenisMap.keySet());
		return idToPenisMap.get(id);
	}
	
	public static String getIdFromPenisType(AbstractPenisType penisType) {
		return penisToIdMap.get(penisType);
	}
	
	public static List<AbstractPenisType> getAllPenisTypes() {
		return allPenisTypes;
	}
	
	private static Map<AbstractRace, List<AbstractPenisType>> typesMap = new HashMap<>();
	
	public static List<AbstractPenisType> getPenisTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractPenisType> types = new ArrayList<>();
		for(AbstractPenisType type : PenisType.getAllPenisTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}