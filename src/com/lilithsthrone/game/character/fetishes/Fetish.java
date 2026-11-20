package com.lilithsthrone.game.character.fetishes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.?
 * @version 0.4.2
 * @author Innoxia, Maxis
 */
public class Fetish {
	
	// FETISHES:

	// Sex types:
	
	public static AbstractFetish FETISH_ANAL_GIVING = new AbstractFetish(60,
			"анальный",
			"заниматься анальным сексом",
			"fetish_anal_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>анальное дразнение</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение задницей</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека совершать сексуальные действия с анусом своего партнера.";
				
			} else if(owner.isPlayer()) {
				return "Вы просто обожаете дарить анальный секс. Использование задницы партнера, присвоение его ануса как своей собственности — всё это сводит вас с ума от похоти!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на анальный секс.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "совершение действий во время анального секса");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "анальная дразнилка");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isAnalContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ANAL_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ANAL_RECEIVING = new AbstractFetish(60,
			"попатая",
			"принятие анального секса",
			"fetish_anal_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение задницей</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>анальное дразнение</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека быть принимающей стороной в анальном сексе.";
				
			} else if(owner.isPlayer()) {
				return "Ты просто обожаешь, когда в тебя трахаются в задницу. Одна мысль о том, что твою попку будут нещадно долбить, как послушной маленькой попатой, сводит тебя с ума от похоти!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] испытывает фетиш к принятию анального секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "любое анальное внимание");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнилка для попатой");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}

		@Override
		public boolean isContentEnabled() { return Main.game.isAnalContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ANAL_GIVING; }
	};
	
	public static AbstractFetish FETISH_VAGINAL_GIVING = new AbstractFetish(60,
			"вагинальный",
			"вагинальный секс",
			"fetish_vaginal_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнящее поклонение киске</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнящая кисочная шлюшка</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека совершать сексуальные действия с киской своего партнера.";
				
			} else if(owner.isPlayer()) {
				return "Хотя обычно это один из самых обыденных видов секса, ваша любовь к вагинальному сексу превратилась в настоящую одержимость.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] одержима вагинальным сексом.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "совершение вагинального секса");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнящее поклонение киске");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VAGINAL_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_VAGINAL_RECEIVING = new AbstractFetish(60,
			"кисочная шлюшка",
			"получение вагинального",
			"fetish_vaginal_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнящая кисочная шлюшка</span> (Требуется влагалище)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнящее поклонение киске</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы его киску использовали.";
				
			} else if(owner.isPlayer()) {
				return "Хотя обычно это один из самых обыденных видов сексуальной активности, ваша любовь к получению любого вида вагинального секса превратилась в настоящую одержимость.";
			} else {
				return UtilText.parse(owner, "[npc.Name] одержима получением вагинального секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "любое вагинальное ласкание");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "раззаживание киски");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VAGINAL_GIVING; }
	};
	
	public static AbstractFetish FETISH_ORAL_RECEIVING = new AbstractFetish(60,
			"оральный",
			"получение орального секса",
			"fetish_oral_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>оральное дразнение</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение оральным исполнением</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека быть принимающей стороной во время орального секса.";
				
			} else if(owner.isPlayer()) {
				return "Вы просто обожаете, когда вам делают минет. Направлять голову партнера себе между ног — ваше самое любимое занятие, и вы всегда жаждете почувствовать чьи-то губы и язык, которые доведут вас до оргазма.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает получать минет.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "получать минет");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "оральный дразнинг");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ORAL_GIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ORAL_GIVING = new AbstractFetish(60,
			"мастер орального секса",
			"делать минет",
			"fetish_oral_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение оральным исполнением</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>оральное дразнение</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека делать оральный секс своему партнеру.";
				
			} else if(owner.isPlayer()) {
				return "Вы просто обожаете делать минет. Опускаться головой между ног партнера — ваше самое любимое занятие, и вы всегда готовы и жаждете использовать свой рот, чтобы довести его или ее до оргазма!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает делать минет.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "делать оральный секс");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнить любителя орального секса");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ORAL_RECEIVING; }
	};
	
	public static AbstractFetish FETISH_BREASTS_OTHERS = new AbstractFetish(60,
			"любитель груди",
			"чужая грудь",
			"fetish_breasts_others",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить любителя груди</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить грудью</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека использовать грудь своего партнера.";
				
			} else if(owner.isPlayer()) {
				return "У вас одержимость грудью. Большая или маленькая — если у кого-то есть пара (или больше) сисек, вы обязательно найдете способ их использовать.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на чужую грудь.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "играть с чужой грудью");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнить любителя груди");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BREASTS_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BREASTS_SELF = new AbstractFetish(60,
			"груди",
			"самовоздействие на грудь",
			"fetish_breasts_self",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить грудью</span> (Требуется грудь)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить любителя груди</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы другие ласкали его грудь.";
				
			} else if(owner.isPlayer()) {
				return "Вы одержимы своей грудью. Вы не находите ничего лучше, чем доставлять удовольствие своим партнерам с её помощью или хвастаться ею перед многочисленными поклонниками.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование груди [npc.her].");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "прикосновения и ласки к груди [npc.breasts]");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнить грудью");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BREASTS_OTHERS; }
	};
	
	public static AbstractFetish FETISH_LACTATION_OTHERS = new AbstractFetish(60,
			"любитель молока",
			"кормление грудью",
			"fetish_lactation_others",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить молоком</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнилка лактацией</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы его партнерша кормила грудью.";
				
			} else if(owner.isPlayer()) {
				return "У вас одержимость кормлением из груди. Вы не любите ничего больше, чем сосать чьи-то налитые молоком соски.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на кормление из груди.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "кормление грудью");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнилка любителя молока");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isLactationContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LACTATION_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_LACTATION_SELF = new AbstractFetish(60,
			"лактация",
			"кормящая",
			"fetish_lactation_self",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнилка лактацией</span> (Требуются груди)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить молоком</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека иметь грудь, выделяющую молоко.";
				
			} else if(owner.isPlayer()) {
				return "У вас одержимость лактацией. Вы не любите ничего больше, чем полное, приносящее удовлетворение чувство, когда ваше молоко доят.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает лактацию.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "когда доят твою [npc.breasts]");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "лактационное дразнение");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isLactationContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LACTATION_OTHERS; }
		
	};
	
	public static AbstractFetish FETISH_LEG_LOVER = new AbstractFetish(60,
			"любитель ног",
			"ноги партнера",
			"fetish_leg_lover",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение любителя ног</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение походкой</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека использовать ноги во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Ты просто обожаешь ноги. Использование ног или бедер партнера в сексе — это твой главный возбудитель.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает использовать чужие ноги и бедра.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужие ноги");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнилка ногами");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_STRUTTER; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_STRUTTER = new AbstractFetish(60,
			"щеголь",
			"использование собственных ног",
			"fetish_strutter",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнилка щеголя</span> (Требуются ноги)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение любителя ног</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы его ноги использовали в сексуальном плане.";
				
			} else if(owner.isPlayer()) {
				return "Вы просто обожаете выставлять свои ноги напоказ. Использование ваших ног или бедер в сексе — это ваш главный возбудитель.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование [npc.her] ног или бедер в сексе.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "использование ваших бедер и ног в сексе");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнилка щеголя");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LEG_LOVER; }
	};
	
	public static AbstractFetish FETISH_FOOT_GIVING = new AbstractFetish(60,
			"доминантная стопа",
			"использование ног",
			"fetish_foot_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить доминантной стопой</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить подчиненной стопой</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека использовать свои стопы во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вы просто обожаете использовать ноги в сексе. Когда партнер боготворит ваши стопы и пальцы ног, совершая над ними сексуальные ласки, это возбуждает вас сильнее всего на свете.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование [npc.her] стоп в сексе.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "использование ног в сексе");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнить доминантной стопой");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isFootContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_FOOT_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_FOOT_RECEIVING = new AbstractFetish(60,
			"подчиненная стопа",
			"использование ступней партнера",
			"fetish_foot_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить подчиненной стопой</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнить доминантной стопой</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека использовать ступни своего партнера во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вы просто обожаете ступни. То, что партнер задействует свои стопы и пальцы в сексе, заводит вас сильнее всего.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] испытывает фетиш на использование чужих ступней.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужие ступни");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "покорное дразнение ступнями");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isFootContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_FOOT_GIVING; }
	};

	public static AbstractFetish FETISH_ARMPIT_GIVING = new AbstractFetish(60,
			"любитель подмышек",
			"демонстрация подмышек",
			"fetish_armpit_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение поклонением подмышкам</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение подмышечной шлюхой</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека совершать сексуальные действия с подмышками своего партнера.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] не хочет ничего, кроме как ласкать подмышки [npc.her], и даже предпочитает это классическому сексу.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "совершение сексуальных действий с подмышками");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнящее поклонение подмышкам");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isArmpitContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ARMPIT_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ARMPIT_RECEIVING = new AbstractFetish(60,
			"подмышечная шлюшка",
			"получение ласк подмышек",
			"fetish_armpit_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение подмышечной шлюхой</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение поклонением подмышкам</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы использовали его собственные подмышки.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] не хочет ничего, кроме как чтобы [npc.her] подмышки сексуально обслуживали [npc.her] партнеры, и даже предпочитает это классическому сексу.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "любое внимание к подмышкам");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнящая подмышечная шлюшка");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isArmpitContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ARMPIT_GIVING; }
	};
	
	public static AbstractFetish FETISH_PENIS_GIVING = new AbstractFetish(60,
			"кок-стад",
			"использование собственного члена",
			"fetish_dick_dealer",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение кок-стада</span> (Требуется член)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение члена зависимого</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека использовать свой член.";
				
			} else if(owner.isPlayer()) {
				return "Вы одержимы проникающим сексом. Вколачивание своего члена в любое доступное отверстие — это всё, о чем вы можете думать...";
			
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование члена [npc.her].");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "использование твоего члена");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение кок-стада");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PENIS_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PENIS_RECEIVING = new AbstractFetish(60,
			"зависимый от члена",
			"чужие члены",
			"fetish_cock_addict",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение члена зависимого</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнилка-задрот на членах</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека обслуживать мужские члены.";
				
			} else if(owner.isPlayer()) {
				return "Вы безнадежно зависимы от членов. Большие, маленькие, толстые или тонкие — вам плевать на внешний вид, лишь бы он входил и выходил из одного из ваших отверстий...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] безнадежно зависим(а) от членов.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужие члены");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "задрот-любитель членов");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PENIS_GIVING; }
	};
	
	public static AbstractFetish FETISH_CUM_STUD = new AbstractFetish(60,
			"задрот на конче",
			"кончая",
			"fetish_cum",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнилка-задрот на конче</span> (Требуется пенис)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение кончником-зависимым</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека кончать на партнера или внутрь него.";
				
			} else {
				return UtilText.parse(owner,
							"[npc.NameHasFull] особая одержимость эякуляцией. Больше всего [npc.she] [npc.verb(love)] наполнять спермой любые отверстия, но также вполне приемлемо забрызгать всё тело партнера.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "любая форма мастурбации с упором на эякуляцию");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение кончиком-жеребцом");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_CUM_ADDICT; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_CUM_ADDICT = new AbstractFetish(60,
			"кончник-зависимый",
			"игры со спермой",
			"fetish_cum_addict",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.CLOTHING_WHITE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение кончником-зависимым</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение кончиком-жеребцом</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека глотать сперму или быть ею покрытым.";
				
			} else if(owner.isPlayer()) {
				return "Ты безнадежно зависима от спермы. Тебе плевать, чья она; всё, чего ты хочешь — чтобы твой рот был полон вкусного, соленого семени... Позволить ему перекатываться по языку, смакуя каждое мгновение... Ммм... Сперма — это действительно лучшее...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает сперму.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужая сперма");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "зависимая от спермы дразнилка");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_CUM_STUD; }
	};
	
	public static AbstractFetish FETISH_DEFLOWERING = new AbstractFetish(60,
			"лишение девственности",
			"лишение девственности",
			"fetish_deflowering",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("Получить <span style='color:"+ PresetColour.GENERIC_EXPERIENCE.toWebHexString()+ ";'>xp</span> от <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>лишение девственности</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека лишать других девственности.";
				
			} else if(owner.isPlayer()) {
				return "Нет ничего слаще, чем лишать невинную девушку девственности. И хотя ваше любимое занятие — приручать будущую шлюху, вам всё равно нравится быть первым, кто трахает чью-то задницу, соски или глотку.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] одержим дефлорацией. [npc.She] обожает быть тем, кто разрывает девичью плеву, но также ему нравится первым трахать чью-то задницу, соски или глотку.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "лишение девственности");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PURE_VIRGIN; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PURE_VIRGIN = new AbstractFetish(60,
			"вагинальная девственность",
			"сохранение вагинальной девственности",
			"fetish_virginity",
			FetishExperience.BASE_VERY_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"эффект статуса [style.colourGood(Получение)] [style.colourExcellent(Чистая дева)]",
					"эффект статуса [style.colourBad(Страдание)] [style.colourTerrible(Потерянная дева)]"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека сохранять и ценить свою вагинальную девственность.";
				
			} else if(owner.hasVagina()) {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(prize)] ценит [npc.her] вагинальную девственность превыше всего на свете. Если [npc.she] [npc.was] когда-нибудь её потеряет, [npc.she] [npc.do] не будет знать, как [npc.she] справится с этим...");
				
			} else {
				if(owner.hasFetish(FETISH_PURE_VIRGIN)) {
					return UtilText.parse(owner, "Хотя у [npc.name] сейчас [npc.do] нет влагалища, [npc.she] [npc.verb(know)], что если бы у [npc.she] [npc.was] оно когда-нибудь появилось, [npc.she] ценил бы его девственность превыше всего на свете.");
					
				} else {
					return UtilText.parse(owner, "Так как у [npc.name] [npc.do]нет влагалища, [npc.she] не может фетишизировать сохранение девственности...");
				}
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "сохранение девственности");
		}
		
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.getVaginaType()!=VaginaType.NONE && character.isVaginaVirgin();
		}

		@Override
		public List<String> getPerkRequirements(GameCharacter character) {
			perkRequirementsList.clear();
			
			if(character.getVaginaType()==VaginaType.NONE) {
				perkRequirementsList.add("[style.colourBad(Требуется влагалище)]");
			} else {
				perkRequirementsList.add("[style.colourGood(Требуется влагалище)]");
			}
			
			if(!character.isVaginaVirgin()) {
				perkRequirementsList.add("[style.colourBad(Требуется девственность влагалища)]");
			} else {
				perkRequirementsList.add("[style.colourGood(Требуется девственность влагалища)]");
			}
			
			return perkRequirementsList;
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ZERO_PURE;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DEFLOWERING; }
	};
	
	public static AbstractFetish FETISH_MASTURBATION = new AbstractFetish(60,
			"мастурбация",
			"мастурбация",
			"fetish_masturbation",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.TEXT_GREY.toWebHexString()+ ";'>Нет особых способностей</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека мастурбировать.";
				
			} else {
				return UtilText.parse(owner, "Использование [npc.her] [npc.fingers], чтобы довести до оргазма либо [npc.herself], либо [npc.her] партнера — одна из [npc.namePos] любимых вещей во время секса.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "masturbating");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	};
	
	
	// FETISH_SPANKING("spanking", "You love the idea of spanking or being
	// spanked during sex."),

	// Effects:
	
	public static AbstractFetish FETISH_IMPREGNATION = new AbstractFetish(60,
			"оплодотворение",
			"оплодотворение",
			"fetish_impregnation",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
					+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение мужественностью</span> (Требуется пенис)",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Уязвим к</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>дразнение фертильностью</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека оплодотворять других людей.";
				
			} else if(owner.isPlayer()) {
				return "Вы часто ловите себя на фантазиях о том, как наполняете плодородные утробы своим семенем, а сама мысль о том, чтобы разводить своего сексуального партнера как животное, сводит вас с ума от Похоти.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на [npc.her] партнера во время секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "оплодотворение других");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение мужественностью");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PREGNANCY; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PREGNANCY = new AbstractFetish(60,
			"беременность",
			"беременность",
			"fetish_pregnancy",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
							+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение фертильности</span> (Требуется влагалище)",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Уязвим к</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>дразнение мужественности</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека забеременеть.";
				
			} else if(owner.isPlayer()) {
				return "Вы часто ловите себя на фантазиях о том, как вас оплодотворяют, а мысль о том, чтобы вас осеменяли как животное, сводит вас с ума от Похоти.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на оплодотворение.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "беременность");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение фертильности");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_IMPREGNATION; }
	};

	public static AbstractFetish FETISH_TRANSFORMATION_GIVING = new AbstractFetish(60,
			"трансформер",
			"превращение других",
			"fetish_transformation_giving",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Снижает стоимость приготовления всех зелий наполовину</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека изменять облик других людей.";
				
			} else if (owner.isPlayer()){
				return "Вы обожаете саму идею превращения других. Наблюдение за тем, как меняются их тела — по собственной воле или против неё — невероятно заводит вас.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает превращать других. Наблюдение за тем, как меняются их тела — по собственной воле или против неё — невероятно заводит [npc.herHim].");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "превращение других");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_TRANSFORMATION_RECEIVING = new AbstractFetish(60,
			"подопытный",
			"быть превращённым",
			"fetish_transformation_receiving",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.boldGood(Увеличивает эффективность)] <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>шанс получить принудительное превращение</span>",
					"[style.boldBad(Отключено)] способность выплевывать зелья трансформации"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека подвергаться трансформации под воздействием других.";
				
			} else if(owner.isPlayer()) {
				return "Вам безумно нравится идея превращения. Изменение частей вашего тела — по собственной воле или нет — невероятно вас возбуждает.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает трансформации. Изменение [npc.her] частей тела, по собственной воле или нет, невероятно возбуждает [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "идея трансформации");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_GIVING; }
	};
	
	public static AbstractFetish FETISH_KINK_GIVING = new AbstractFetish(60,
			"сторонник кинка",
			"навязывание фетишей другим",
//			"fetish_transformation_giving",
			"fetish_kink_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than triggering forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Вам нравится заставлять других пробовать что-то новенькое!</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека прививать новые фетиши другим людям.";
				
			} else if(owner.isPlayer()) {
				return "Идея даровать людям новые фетиши, по собственной воле или нет, невероятно вас возбуждает.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает дарить другим новые фетиши. Наблюдение за тем, как они наслаждаются новыми извращениями — добровольно или нет — невероятно возбуждает [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "дарить другим новые фетиши");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_KINK_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_KINK_RECEIVING = new AbstractFetish(60,
			"любопытство к кинкам",
			"обретение новых фетишей",
//			"fetish_transformation_receiving",
			"fetish_kink_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than not taking corruption from receiving forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Убирает прирост порочности, когда вам навязывают фетиш.</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека обретать новые фетиши.";
				
			} else if (owner.isPlayer()) {
				return "Вам нравится сама идея развития новых фетишей. Получение извращенного удовольствия от чего-то нового, будь то добровольно или нет, невероятно вас возбуждает.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает обретать новые фетиши. Получение извращенного удовольствия от чего-то нового, будь то добровольно или нет, невероятно возбуждает [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "идея того, что вам дарят новые фетиши");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_KINK_GIVING; }
	};
	
	// Behaviour (organised roughly in active/passive pairs):
	

	public static AbstractFetish FETISH_DENIAL = new AbstractFetish(60,
			"отрицание оргазма",
			"запрет оргазмов",
			"fetish_denial",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека лишать своего партнера оргазма.";
				
			} else if(owner.isPlayer()) {
				return "Будь то дразнение своим телом или прямое препятствование разрядке, вам нравится лишать партнеров оргазма во время секса.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на запрет оргазмов.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "запрет оргазмов");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_DENIAL_SELF = new AbstractFetish(60,
			"самозапрет",
			"запрет со стороны партнера",
			"fetish_denial_self",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы именно его оргазмы запрещали.";
				
			} else if(owner.isPlayer()) {
				return "Вы обожаете эджинг и то, когда вам запрещают достигать оргазма.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на то, чтобы [npc.her] запрещали оргазмы.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "запрет на оргазм");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL; }
	};
	
	public static AbstractFetish FETISH_DOMINANT = new AbstractFetish(60,
			"доминант",
			"вести себя доминантно",
			"fetish_dominant",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>доминантный дразнилка</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>сабмиссивный дразнилка</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека быть доминирующим партнером в сексе.";
				
			} else if(owner.isPlayer()) {
				return "Вам нравится быть доминирующим партнером во время секса, и вы точно знаете, как показать партнеру, кто здесь главный.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на доминирование в сексе.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "быть доминирующим партнером");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "доминантный дразнилка");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SUBMISSIVE; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_SUBMISSIVE = new AbstractFetish(60,
			"покорность",
			"играть роль подчиненной",
			"fetish_submissive",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>сабмиссивный дразнилка</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Уязвим к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>доминантный дразнилка</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека быть ведомым партнером во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вам нравится быть подчиненной во время секса. Вы готовы на все, чтобы продемонстрировать свою покорность, и с радостью позволите партнеру делать с вами всё, что ему вздумается.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] испытывает фетиш к роли подчиненной в сексе.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "быть ведомым партнером");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "покорная дразнилка");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DOMINANT; }
	};
	
	public static AbstractFetish FETISH_INCEST = new AbstractFetish(60,
			"инцест",
			"инцестуозный секс",
			"fetish_incest",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"Разблокирует <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>инцестуозное дразнение</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека заниматься сексом со своими родственниками.";
				
			} else if(owner.isPlayer()) {
				if(owner.getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
					return "Тебя всегда тянуло к твоему двоюродному брату...";
				} else {
					return "Тебя всегда тянуло к твоей тете Лили, а также к твоим двоюродным сестрам...";
				}
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на инцестуозный секс.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "секс с родственниками");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "инцестуозное дразнение");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean isContentEnabled() { return Main.game.isIncestEnabled(); }
	};
	
	public static AbstractFetish FETISH_SADIST = new AbstractFetish(60,
			"садист",
			"причинение боли",
			"fetish_sadist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] садистский [style.colourSex(секс-действия)]",
					"[style.boldExcellent(+5%)] ко всем [style.colourHealth(\", "+Attribute.HEALTH_MAXIMUM.getName()+" урон)]",
					"10% o урона всем пораженным",
					"[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+" урон)] наносится",
					"вам в виде <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'> урона похотью</span>",
					"[style.boldArcane(+1 essence)] при критическом",
					" ударе по врагам"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека издеваться над другими.";
				
			} else if(owner.isPlayer()) {
				return "Вы обожаете причинять боль и унижение, а вид страданий других сводит вас с ума от похоти.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает причинять боль и унижение.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "причинение боли и унижение другим");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_MASOCHIST; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_MASOCHIST = new AbstractFetish(60,
			"мазохист",
			"боль и унижение",
			"fetish_masochist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 2)),
			Util.newArrayListOfValues(
					"[style.boldSex(Наслаждается)] [style.boldTerrible(болезненными половыми актами)]",
					"25% o всего входящего",
					"<span style='color:"+ PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+ ";'>"+Attribute.HEALTH_MAXIMUM.getName()+" урона</span> преобразуется",
					" в <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'> урона похотью</span>",
					"[style.boldArcane(+1 эссенция)] когда вы",
					" получаете критический удар"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека подвергаться насилию.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(get)] невероятно возбуждается, когда подвергается болезненным или унизительным испытаниям. [npc.She] [npc.verb(find)] [npc.herself] испытывает возбуждение всякий раз, когда [npc.her] отверстия растягиваются или проникают слишком глубоко.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "боль и унижение");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SADIST; }
	};
	
	public static AbstractFetish FETISH_NON_CON_DOM = new AbstractFetish(60,
			"отсутствие согласия",
			"изнасилование",
			"fetish_noncon_dom",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Увеличивает</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>прирост возбуждения, когда партнер сопротивляется сексу</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека насиловать других.";
				
			} else if(owner.isPlayer()) {
				return "Вы не любите ничего больше, чем когда кого-то заставляют заниматься сексом против воли. Чем сильнее они сопротивляются, тем больше вы возбуждаетесь...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на встречи без согласия. Чем сильнее [npc.her] жертва сопротивляется, тем больше [npc.she] возбуждается...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "занятие сексом с неохотным партнером");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isNonConEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_NON_CON_SUB; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_NON_CON_SUB = new AbstractFetish(60,
			"невольная секс-игрушка",
			"изнасилование",
			"fetish_noncon_sub",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Увеличивает</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>прирост возбуждения при сопротивлении сексу</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека быть изнасилованным другими.";
				
			} else if(owner.isPlayer()) {
				return "Нет ничего слаще, чем когда тебя заставляют заниматься сексом против твоей воли. Борьба и мольбы о пощаде возбуждают тебя сильнее всего на свете...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на роль жертвы в ситуациях без согласия. Борьба и мольбы о пощаде возбуждают [npc.herHim] сильнее всего на свете...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "принуждение к сексу против воли");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isNonConEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_NON_CON_DOM; }
	};
	
	public static AbstractFetish FETISH_BONDAGE_APPLIER = new AbstractFetish(60,
			"мастер связывания",
			"наложение пут",
			"fetish_bondage_applier",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(Убирает затраты эссенции на чары запечатывания, служения и порабощения)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека запечатывать других в одежду.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] связывать людей в одежде, которую они не могут снять, а затем пользоваться их неподвижностью...");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "другие в запечатанной одежде");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_VICTIM; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BONDAGE_VICTIM = new AbstractFetish(60,
			"блэндаж-сучка",
			"быть связанной",
			"fetish_bondage_victim",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(стоимость 5x)] к [style.colourSeal(распечатыванию)] собственной одежды",
					"Бонус сета BDSM дает [style.colourGood(положительные эффекты)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека самому носить запечатанную одежду.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] быть затянутой в одежду, которую [npc.sheIs] не может снять, оставляя [npc.herHim] на милость других...");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "ношение запечатанной одежды");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_APPLIER; }
	};
	
	public static AbstractFetish FETISH_EXHIBITIONIST = new AbstractFetish(60,
			"эксгибиционизм",
			"обнажение себя",
			"fetish_exhibitionist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>заменяет</span> <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>эффекты обнажения</span> <span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>на полезные версии</span>"),
			null) {

		@Override
		public String getShortDescriptor(GameCharacter target) {
			if(target==null) {
				return "обнажение себя";
			}
			return UtilText.parse(target, "обнажение [npc.herself]");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека выставлять свое тело напоказ и тем, чтобы другие наблюдали за его сексом.";
				
			} else if(owner.isPlayer()) {
				return "Вы обожаете выставлять свое тело напоказ, и процесс демонстрации своей наготы в общественных местах возбуждает вас сильнее всего на свете.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на обнажение тела [npc.her].");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "обнажаться перед другими");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}

		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VOYEURIST; }
	};
	
	public static AbstractFetish FETISH_VOYEURIST = new AbstractFetish(60,
			"вуайерист",
			"наблюдение за другими",
			"fetish_voyeurist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Бонус к возбуждению</span> при просмотре секс-сцен"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека наблюдать за тем, как другие занимаются сексом.";
				
			} else if(owner.isPlayer()) {
				return "Вы обожаете наблюдать за другими... Особенно когда они вытворяют что-то непристойное...";
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на наблюдение за другими...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "наблюдение за другими");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_EXHIBITIONIST; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BIMBO = new AbstractFetish(60,
			"бимбо",
			"быть бимбо",
			null,
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Говорить как бимбо</span>"),
			null) {
		
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null ||owner.isFeminine()) {
				return "бимбо";
			} else {
				return "бро";
			}
		}
		
		@Override
		public String getShortDescriptor(GameCharacter target) {
			if (target==null ||target.isFeminine()) {
				return "быть бимбо";
			} else {
				return "быть бро";
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека вести себя как глупенькая бимбо.";
				
			} else if(owner.isFeminine()) {
				return UtilText.parse(owner,
						"[npc.NameIsFull] одержима идеей вести себя как полная бимбо. Дошло до того, что какой бы умной [npc.she] ни была на самом деле, [npc.she] не может представить [npc.herself] кем-то иным, кроме как пустой пустышкой.");
			} else {
				return UtilText.parse(owner,
						"[npc.NameIsFull] одержим идеей вести себя как туповатый серфер-бро. Дошло до того, что каким бы умным [npc.she] ни был на самом деле, [npc.she] не может представить [npc.herself] кем-то иным, кроме как безмозглой качком.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			if(target==null || target.isFeminine()) {
				return getGenericFetishDesireDescription(target, desire, "вести себя как бимбо");
			} else {
				return getGenericFetishDesireDescription(target, desire, "вести себя как бро");
			}
		}

		@Override
		public List<String> getExtraEffects(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Говорить как бимбо</span>");
			} else {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Говорить как бро</span>");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return bimboString;
			} else {
				return broString;
			}
		}

		@Override
		public FetishPreference getFetishPreferenceDefault() {
			return FetishPreference.TWO_DISLIKE;
		}
	};
	
	public static AbstractFetish FETISH_CROSS_DRESSER = new AbstractFetish(60,
			"кроссдрессинг",
			"кроссдрессинг",
			"fetish_cross_dresser",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к эффектам женственности одежды</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека носить одежду, которая кажется либо слишком женственной, либо слишком маскулинной для него.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] носить всевозможную одежду, и [npc.she] [npc.do]плевать, что другие сочтут её слишком мужской или женской для [npc.her] тела.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "ношение одежды, больше подходящей противоположному полу");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}

		@Override
		public FetishPreference getFetishPreferenceDefault() {
			return FetishPreference.TWO_DISLIKE;
		}
	};
	
	public static AbstractFetish FETISH_SIZE_QUEEN = new AbstractFetish(60,
			"размерная королева",
			"глубокое проникновение",
			"fetish_size_queen",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.BASE_YELLOW, PresetColour.BASE_PINK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			Util.newArrayListOfValues(
					"[style.colourGood(Любит)] [style.colourSex(когда его/её растягивают)]",
					"Считает [style.colourSex('неудобно глубокие')] проникновения [style.colourGood('комфортными') ]"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека, чтобы его партнеры обладали как можно более внушительными размерами.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(prefer)] [npc.her] партнеров с огромным достоинством, и [npc.verb(love)] чувствовать их внутри [npc.herHim] настолько глубоко, насколько это физически возможно.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "глубокое проникновение");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isPenetrationLimitationsEnabled(); }
	};
	
	// Derived fetishes:
	
	public static AbstractFetish FETISH_SWITCH = new AbstractFetish(60,
			"свитч",
			"быть свитчем",
			"fetish_switch",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_DOMINANT,
					Fetish.FETISH_SUBMISSIVE)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека быть либо доминирующим, либо подчиняющимся партнером во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вы вполне довольны сменой ролей доминанта и сабмиссива в зависимости от ситуации.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] с удовольствием играет как роль доминанта, так и сабмиссива во время секса.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "смена ролей доминанта и сабмиссива");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_BREEDER = new AbstractFetish(60,
			"бридер",
			"разведение",
			"fetish_breeder",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 25),
					new Value<>(Attribute.VIRILITY, 25)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_PREGNANCY,
					Fetish.FETISH_IMPREGNATION)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека как самой быть беременной, так и оплодотворять других.";
				
			} else if (owner.isPlayer()) {
				return "У вас есть мечта. Мечта о мире, в котором все беременны, включая вас саму!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] не хочет ничего больше, кроме как разделить любовь [npc.her] к беременности со всеми, кого [npc.she] встречает.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "всё, что связано с репродукцией");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_SADOMASOCHIST = new AbstractFetish(60,
			"садомазохист",
			"садомазохизм",
			"fetish_sadomasochist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 3),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_SADIST,
					Fetish.FETISH_MASOCHIST)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека причинять боль другим и получать её самому.";
				
			} else if (owner.isPlayer()) {
				return "Вам неважно, на чьей вы стороне — дающей или принимающей; если дело идет о боли и унижении, вы готовы на всё.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает боль и унижение во всех их проявлениях.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "все формы боли и унижения");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_LUSTY_MAIDEN = new AbstractFetish(60,
			"похотливая дева",
			"похотливая дева",
			"fetish_lusty_maiden",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>усиливает</span> <span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>'чистая девственница'</span>",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>усиливает</span> <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>'сломленная девственница'</span>"),
			null) {
		@Override
		public List<AbstractFetish> getFetishesForAutomaticUnlock() {
			return Util.newArrayListOfValues(
					Fetish.FETISH_PURE_VIRGIN,
					Main.game.isAnalContentEnabled()
						?Fetish.FETISH_ANAL_RECEIVING
						:null,
					Fetish.FETISH_ORAL_GIVING,
					Fetish.FETISH_BREASTS_SELF);
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш связан с желанием человека сохранить вагинальную девственность, используя при этом задницу, грудь или рот, чтобы довести партнера до оргазма.";
				
			} else if (owner.isPlayer()) {
				return "Вы — истинная искусительница, соблазняющая и доставляющая удовольствие другим своей задницей, ртом, грудью и даже обещанием своей киски, но вы никогда на самом деле не позволите никому проникнуть в ваше женское лоно и отнять вашу драгоценную девственность.";
			} else {
				return UtilText.parse(owner, "[npc.Name] обожает доставлять удовольствие другим, используя [npc.her] задницу, рот, грудь и даже обещание [npc.her] киски, но [npc.she] никогда на самом деле не позволит никому проникнуть в [npc.her] женское лоно и отнять [npc.her] драгоценную девственность.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "избегание вагинального секса");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	
	// Helper methods:
	
	private static String getAppliedFetishAttackLevelEffectDescription(GameCharacter character, AbstractFetish fetish, String fetishAttackName) {
		FetishLevel level = character.getFetishLevel(fetish);
		return "+"+level.getBonusTeaseDamage()+" базовый урон по "+fetishAttackName;
	}
	
	// Access methods:
	
	public static List<AbstractFetish> allFetishes;
	
	public static Map<AbstractFetish, String> fetishToIdMap = new HashMap<>();
	public static Map<String, AbstractFetish> idToFetishMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractFetish getFetishFromId(String id) {
		id = Util.getClosestStringMatch(id, idToFetishMap.keySet());
		
		return idToFetishMap.get(id);
	}
	
	public static String getIdFromFetish(AbstractFetish fetish) {
		return fetishToIdMap.get(fetish);
	}

	static {
		allFetishes = new ArrayList<>();
		
		// Hard-coded fetishes (all those up above):
		
		Field[] fields = Fetish.class.getFields();
		
		for(Field f : fields){
			if (AbstractFetish.class.isAssignableFrom(f.getType())) {
				
				AbstractFetish fetish;
				
				try {
					fetish = ((AbstractFetish) f.get(null));

					fetishToIdMap.put(fetish, f.getName());
					idToFetishMap.put(f.getName(), fetish);
					allFetishes.add(fetish);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractFetish> getAllFetishes() {
		return allFetishes;
	}
	
}
