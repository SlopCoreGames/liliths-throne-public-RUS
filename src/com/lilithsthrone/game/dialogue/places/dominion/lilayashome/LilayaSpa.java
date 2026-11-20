package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMShower;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.3.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class LilayaSpa {
	
	public static final String SPA_CONSTRUCTTION_TIMER_ID = "spa_construction_timer";
	
	private static Cell cellInstallation = null;
	
    private static List<GameCharacter> slavesWashing;
    private static List<GameCharacter> slavesSex;
	private static List<GameCharacter> bathingStripped;
    private static List<GameCharacter> slavesForMassage;

	private static GameCharacter drinksCharacter = null;
	private static GameCharacter massageSlave = null;
	private static GameCharacter guest = null;
	private static boolean playerStripped = false;
	
	private static boolean massageSlaveSex = false;
	
    private static List<AbstractItemType> getDrinks() {
    	return Util.newArrayListOfValues(
    			ItemType.getItemTypeFromId("innoxia_race_human_vanilla_water"),
    			ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_juice_box"),
    			ItemType.getItemTypeFromId("innoxia_race_dog_canine_crush"),
    			ItemType.getItemTypeFromId("innoxia_race_horse_equine_cider"),
    			ItemType.getItemTypeFromId("innoxia_race_cat_felines_fancy"),
    			ItemType.getItemTypeFromId("innoxia_race_fox_vulpines_vineyard"),
    			ItemType.getItemTypeFromId("innoxia_race_rat_black_rats_rum"),
    			ItemType.getItemTypeFromId("innoxia_race_wolf_wolf_whiskey"));
    }
    
    public static void setCellInstallation(Cell cellInstallation) {
    	LilayaSpa.cellInstallation = cellInstallation;
    }
    
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> charactersPresent = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		charactersPresent.removeIf(character -> !character.isSlave());
		return charactersPresent;
	}
	
	public static void initGuestAtSpa(GameCharacter guest) {
		LilayaSpa.guest = guest;
		drinksCharacter = null;
	}
	
	public static boolean isGuestAbleToEquipSwimwear(GameCharacter guest) {
		if(guest.isFeminine()) {
			AbstractClothing swimsuit = Main.game.getItemGen().generateClothing("innoxia_chest_swimsuit", PresetColour.CLOTHING_PINK, false);
			AbstractClothing bikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_bikini", PresetColour.CLOTHING_PINK, false); // innoxia_chest_micro_bikini
			AbstractClothing bikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_bikini", PresetColour.CLOTHING_PINK, false); // innoxia_groin_micro_bikini
			
			return guest.isAbleToEquip(swimsuit, true, guest) || (guest.isAbleToEquip(bikiniTop, true, guest) && guest.isAbleToEquip(bikiniBottom, true, guest));
			
		} else {
			AbstractClothing swimShorts = Main.game.getItemGen().generateClothing("innoxia_groin_swim_shorts", PresetColour.CLOTHING_BLUE, false);
			return guest.isAbleToEquip(swimShorts, true, guest);
		}
	}
	
	private static boolean isGuestPresent() {
		return guest!=null;
	}
	
	// Reception slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> receptionGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] профессионально реверанснет, когда [npc.she] заметит ваше появление в зоне ресепшена, а затем спросит,#ELSE[npc.Name] профессионально поклонится, когда [npc.she] заметит ваше появление в зоне ресепшена, а затем спросит,#ENDIF",
						"Одарив тебя профессиональной улыбкой, [npc.name] спрашивает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] наклоняется над стойкой и принимает [npc.her] самый соблазнительный тон, когда [npc.she] спрашивает,#ELSE[npc.Name] пристально смотрит тебе в [pc.eyes] и принимает [npc.her] самый соблазнительный тон, когда [npc.she] спрашивает,#ENDIF",
						"Одарив тебя соблазнительной улыбкой, [npc.name] спрашивает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] прикусывает [npc.her] губу и издает неуместный, шлюшковатый стон, когда [npc.she] спрашивает,#ELSE[npc.Name] похотливо ухмыляется тебе при входе, а затем бесцеремонно спрашивает,#ENDIF",
						"Одарив тебя похотливой улыбкой, [npc.name] спрашивает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] делает реверанс, когда [npc.she] замечает твое появление у стойки регистрации, а затем спрашивает,#ELSE[npc.Name] улыбается, когда [npc.she] замечает твое появление у стойки регистрации, а затем спрашивает,#ENDIF",
						"Одарив тебя мимолетной улыбкой, [npc.name] спрашивает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] радостно вскрикивает, когда [npc.she] замечает твое появление у стойки регистрации, а затем с нетерпением спрашивает,#ELSE[npc.Name] счастливо улыбается, когда [npc.she] замечает твое появление у стойки регистрации, а затем с нетерпением спрашивает,#ENDIF",
						"Одарив тебя любящей улыбкой, [npc.name] с нетерпением спрашивает,")));

	private static Map<SlavePermissionSetting, List<String>> receptionGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] профессионально реверансирует, когда [npc.she] замечает ваше появление в зоне ресепшена, после чего послушно ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ELSE[npc.Name] профессионально кланяется, когда [npc.she] замечает ваше появление в зоне ресепшена, после чего послушно ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ENDIF",
						"Одарив вас профессиональной улыбкой, [npc.name] послушно ждет, сможет ли [npc.she] чем-нибудь вам помочь.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] наклоняется над прилавком и одаривает тебя [npc.her] самой соблазнительной улыбкой, [npc.she] ожидая, попросишь ли ты чего-нибудь у [npc.herHim].#ELSE[npc.Name] пристально смотрит тебе в [pc.eyes] и одаривает [npc.her] самой соблазнительной улыбкой, пока [npc.she] ждет, решишься ли ты попросить что-нибудь у [npc.herHim].#ENDIF",
						"Одарив вас соблазнительной улыбкой, [npc.name] ждет, сможет ли [npc.she] чем-нибудь вам помочь.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] прикусывает [npc.her] губу и издает неуместный, похотливый стон, пока [npc.she] ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ELSE[npc.Name] похотливо ухмыляется вам при входе, после чего ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ENDIF",
						"Одарив вас похотливой ухмылкой, [npc.name] ждет, сможет ли [npc.she] чем-нибудь вам помочь.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] делает реверанс, когда [npc.she] видит, как вы входите в зону ресепшена, после чего послушно ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ELSE[npc.Name] улыбается, когда [npc.she] видит, как вы входите в зону ресепшена, после чего послушно ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ENDIF",
						"Быстро улыбнувшись вам, [npc.name] ждет, сможет ли [npc.she] чем-нибудь вам помочь.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] издает радостный возглас, когда [npc.she] видит, как вы входите в зону ресепшена, после чего послушно ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ELSE[npc.Name] счастливо улыбается, когда [npc.she] видит, как вы входите в зону ресепшена, после чего послушно ждет, не попросите ли вы чего-нибудь у [npc.herHim].#ENDIF",
						"Одарив вас любящей улыбкой, [npc.name] счастливо ждет, сможет ли [npc.she] чем-нибудь вам помочь.")));

	private static Map<SlavePermissionSetting, List<String>> receptionSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Чем я могу вам помочь, [pc.name]?",
						"Могу ли я чем-нибудь вам помочь, [pc.name]?",
						"Не желаете ли вы принять душ перед тем, как войти в спа, [pc.name]?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"Не желаете ли вы получить некоторую <i>личную</i> помощь?",
						"Есть ли <i>что-нибудь</i>, с чем я могу вам помочь, [pc.name]?",
						"Как я могу <i>помочь</i> вам сегодня, [pc.name]?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))Как насчет того, чтобы нагнуть меня на этом столе и трахнуть до беспамятства?#ELSEТы ведь хочешь хорошенько трахнуться, верно?#ENDIF",
						"Давай уже, трахни меня!",
						"Давай трахнемся, [pc.name]!")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"[pc.name], я могу тебе чем-нибудь помочь?",
						"Что я могу сделать для тебя, [pc.name]?",
						"[pc.name], тебе чем-нибудь помочь?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"Привет, [pc.name]! Могу я чем-нибудь помочь?! Я так рада тебя снова видеть!",
						"Что я могу сделать для тебя, [pc.name]?!",
						"Привет, [pc.name]! Чем я могу помочь тебе сегодня?!")));
	
	private static List<String> receptionRudeGreetings = Util.newArrayListOfValues(
			"Сердито глядя на тебя, [npc.name] непослушно хмурится, ",
			"С [npc.her], [npc.eyes+] переполненной обидой, [npc.name] свирепо смотрит на тебя и рявкает:",
			"Явно не в восторге от того, что её заставили работать в спа, [npc.name] сердито рычит:");

	private static List<String> receptionRudeGreetingsMute = Util.newArrayListOfValues(
			"Гневно глядя на тебя, [npc.name] издает непослушную гримасу, ясно давая понять, что [npc.sheHasFull] совершенно не горит желанием тебе чем-либо помогать...",
			"С [npc.her], [npc.eyes+] переполненной обидой, [npc.name] свирепо смотрит на тебя и издает непослушное рычание.",
			"Явно не в восторге от того, что её заставили работать в спа, [npc.name] сердито рычит на тебя, после чего скрещивает [npc.her] [npc.arms] и отказывается оказывать тебе хоть какую-то помощь.");
	
	private static List<String> receptionRudeSpeech = Util.newArrayListOfValues(
			"Какого хера тебе еще надо, <i>[pc.name]</i>?",
			"Просто шевели жопой и вали отсюда.",
			"Как насчет того, чтобы развернуться и съебаться?");
	
	
	// Shower slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> showerGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Намыливая [npc.her] [npc.hands], [npc.name] принимается мыть твою спину, приговаривая:",
						"Стараясь вести себя профессионально, [npc.name] принимается намыливать твое тело, приговаривая:")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] наклоняется и дразняще прижимает [npc.her] [npc.breasts+] к твоей спине,#ELSE[npc.Name] наклоняется и дразняще прижимает [npc.herself] к твоей спине,#ENDIF",
						"#IF(npc.isFeminine())Соблазнительно поглаживая свои мыльные [npc.fingers] [npc.her] по твоему телу, [npc.name] дразнит,#ELSESоблачительно проводя своими мыльными [npc.hands] [npc.her] по твоему телу, [npc.name] дразнит,#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())Бесстыдно потирая [npc.her] мыльные [npc.breasts] по твоей спине, [npc.name] грубо спрашивает,#ELSESбесстыдно прижимая [npc.her] свое мыльное тело к твоей спине, [npc.name] грубо спрашивает,#ENDIF",
						"Основательно ощупывая твое тело, пока [npc.she] растирает мыльные [npc.hands] [npc.her] по твоим [pc.skin], [npc.name] многозначительно спрашивает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Намыливая [npc.her] [npc.hands], [npc.name] принимается мыть твою спину, приговаривая:",
						"Прилежно выполняя свою работу [npc.her], [npc.name] намыливает твое тело, приговаривая:")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] с любовью проводит [npc.her] мыльными руками по твоему телу, прежде чем прижаться к тебе и вздохнуть:#ELSE[npc.Name] с любовью проводит [npc.her] мыльными руками по твоему телу, а затем прижимается к тебе и говорит:#ENDIF",
						"Счастливо напевая [npc.herself], пока [npc.she] растирает мыло по твоему телу, [npc.name] говорит,")));

	private static Map<SlavePermissionSetting, List<String>> showerGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Вспенивая мыло на [npc.her] [npc.hands], [npc.name] профессионально принимается мыть твою спину.",
						"Действуя профессионально, [npc.name] принимается растирать мыло по твоему телу, помогая тебе очиститься.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] наклоняется и соблазнительно прижимается [npc.her] [npc.breasts+] к твоей спине, пока [npc.she] помогает тебе отмыться.#ELSE[npc.Name] наклоняется и соблазнительно прижимается [npc.herself] к твоей спине, пока [npc.she] помогает тебе отмыться.#ENDIF",
						"#IF(npc.isFeminine())Соблазнительно водя [npc.her] мыльной [npc.fingers] по твоему телу, [npc.name] ухмыляется [npc.herself], пока [npc.she] помогает тебе отмыться.#ELSESоблазнительно проводя [npc.her] мыльной [npc.hands] по твоему телу, [npc.name] ухмыляется [npc.herself], пока [npc.she] помогает тебе отмыться.#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())Бесстыдно растирая [npc.her] мыльную [npc.breasts] по твоей спине, [npc.name] тянется вниз, чтобы хорошенько ощупать твой пах, и издает голодный [npc.moan].#ELSESбесстыдно прижимая [npc.her] мыльное тело к твоей спине, [npc.name] тянется вниз, чтобы хорошенько ощупать твой пах, и издает голодный [npc.moan].#ENDIF",
						"Хорошенько ощупывая твое тело, пока [npc.she] растирает мыльную [npc.her] [npc.hands] по твоей [pc.skin], [npc.name] издает голодный [npc.moan], пока [npc.she] помогает тебе отмыться.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Намыливая [npc.her] [npc.hands], [npc.name] прилежно принимается за мытье твоей спины.",
						"С присущим ей усердием принимаясь за [npc.her] работу, [npc.name] растирает мыло по твоему телу, пока [npc.she] помогает тебе отмыться.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] с любовью проводит [npc.her] мыльными руками по твоему телу, а затем прижимается к тебе, издавая счастливый вздох.#ELSE[npc.Name] с любовью проводит [npc.her] мыльными руками по твоему телу, а затем прижимается к тебе, издавая счастливый вздох.#ENDIF",
						"Счастливо намылив всё твоё тело, [npc.name] прижимается к тебе и удовлетворенно вздыхает.")));

	private static Map<SlavePermissionSetting, List<String>> showerSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Я быстро отмою тебя до блеска, [pc.name].",
						"Это займет всего мгновение, [pc.name].")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"Знаешь, здесь можно заняться не только чисткой...",
						"Возможно, нам стоит провести здесь еще немного времени вместе...")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))Ну же, [pc.name], прижми меня к стене и хорошенько оттрахай!#ELSEНу же, [pc.name], давай трахаться!#ENDIF",
						"Давай уже, трахни меня!")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"После этого ты будешь сиять чистотой, [pc.name].",
						"Еще секунду, и ты будешь чистеньким, [pc.name].")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"Надеюсь, тебе нравится, [pc.name]...",
						"Тебе так нравится, [pc.name]?")));
	
	private static List<String> showerRudeGreetings = Util.newArrayListOfValues(
			"[npc.Name] сердито хмурится, пока [npc.she] неохотно помогает тебе отмыться,",
			"Яростно глядя на тебя, пока [npc.she] вяло помогает мыть твое тело, [npc.name] рычит,",
			"Испытывая негодование от того, что [npc.sheHasFull] приходится помогать тебе мыться, [npc.name] гневно усмехается,");

	private static List<String> showerRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.Name] сердито хмурится, пока [npc.she] неохотно помогает тебе отмыться...",
			"Весь процесс [npc.name] яростно сверлит тебя взглядом, вяло помогая мыть твое тело...",
			"Испытывая негодование от того, что [npc.sheHasFull] приходится помогать тебе мыться, [npc.name] гневно смотрит на тебя...");
	
	private static List<String> showerRudeSpeech = Util.newArrayListOfValues(
			"Просто, блять, поторапливайся, чтобы я могла свалить отсюда...",
			"Как же сильно я это ненавижу...",
			"Какого хера я вообще должна это делать?");
	
	
	// Bathing slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> bathingGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Погружаясь в теплую воду бассейна, [npc.name] вздыхает,",
						"Издавая глубокий вздох, когда [npc.she] входит в бассейн, [npc.name] замечает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] прикусывает [npc.her] [npc.lip] и бросает на тебя соблазнительный взгляд, пока [npc.she] дразнит тебя,#ELSE[npc.Name] бросает на тебя соблазнительную ухмылку и дразнит,#ENDIF",
						"#IF(npc.isFeminine())Соблазнительно дразня [npc.her], мокрую [npc.fingers] по всему [npc.her] телу, [npc.name] дразнит,#ELSESоблазнительно проводя [npc.her], мокрым [npc.hands] по всему [npc.her] телу, [npc.name] дразнит,#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"Разбрызгивая воду в бассейне, чтобы прижаться к тебе, [npc.name] грубо спрашивает,",
						"Подплывая вплотную, [npc.name] хорошенько прощупывает твое тело, пока [npc.she] многозначительно спрашивает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Погружаясь в теплую воду бассейна, [npc.name] вздыхает,",
						"Издавая глубокий вздох, когда [npc.she] входит в бассейн, [npc.name] замечает,")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"[npc.Name] слегка краснеет, когда [npc.she] погружается в теплую воду и вздыхает,",
						"Счастливо напевая [npc.herself], пока [npc.she] скользит в теплую воду, [npc.name] вздыхает,")));

	private static Map<SlavePermissionSetting, List<String>> bathingGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Погружаясь в теплую воду бассейна, [npc.name] тихо вздыхает, просто наслаждаясь возможностью расслабиться.",
						"Издавая едва слышный вздох, когда [npc.she] входит в бассейн, [npc.name] закрывает [npc.her] [npc.eyes] и расслабляется в теплой воде.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())[npc.Name] прикусывает [npc.her] [npc.lip] и бросает на тебя соблазнительный взгляд, когда [npc.she] подплывает вплотную к тому месту, где ты сидишь.#ELSE[npc.Name] одаривает тебя соблазнительной ухмылкой, пока [npc.she] подплывает вплотную к тому месту, где ты сидишь.#ENDIF",
						"[npc.Name] нарочито соблазнительно проводит своими мокрыми [npc.her] [npc.hands] по [npc.her] телу, а затем многозначительно подмигивает тебе.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"Разбрызгивая воду в бассейне, чтобы прижаться к тебе, [npc.name] грубо жестикулирует, призывая тебя трахнуть [npc.herHim]...",
						"Подплывая к тебе вплотную, [npc.name] хорошенько ощупывает твое тело, пока [npc.she] издает голодный скулящий звук.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
							"Погружаясь в теплую воду бассейна, [npc.name] тихо вздыхает, просто наслаждаясь возможностью расслабиться.",
							"Издавая едва слышный вздох, когда [npc.she] входит в бассейн, [npc.name] закрывает [npc.her] [npc.eyes] и расслабляется в теплой воде.")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"[npc.Name] слегка краснеет, когда [npc.she] погружается в теплую воду и улыбается тебе.",
						"[npc.Name] счастливо напевает [npc.herself], пока [npc.she] погружается в теплую воду и одаривает тебя любящей улыбкой.")));

	private static Map<SlavePermissionSetting, List<String>> bathingSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"Пожалуйста, дай знать, если я тебе чем-то понадоблюсь, [pc.name]...",
						"Это так приятно...")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"Знаешь, мы могли бы заняться чем-то помимо простого отдыха...",
						"Может, найдем способ сделать это поинтереснее?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))Давай же, [pc.name], трахни меня как следует!#ELSEДавай же, [pc.name], давай уже просто трахнемся!#ENDIF",
						"Эй, как насчет того, чтобы трахнуться в бассейне? Звучит неплохо, а?")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"Это так охеренно...",
						"Спасибо, что впустила меня к себе, [pc.name]...")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"Это просто супер... Спасибо тебе огромное, что позволила мне побыть с тобой, [pc.name]...",
						"Спасибо, что разделила это со мной, [pc.name]...")));
	
	private static List<String> bathingRudeGreetings = Util.newArrayListOfValues(
			"[npc.Name] сердито хмурится, когда [npc.she] неохотно присоединяется к тебе в бассейне,",
			"Яростно глядя на тебя, [npc.name] хмурится, когда [npc.she] с брызгами погружается в теплую воду бассейна,",
			"Испытывая негодование от того, что [npc.sheHasFull] приходится присоединяться к тебе в бассейне, [npc.name] гневно усмехается,");

	private static List<String> bathingRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.Name] сердито хмурится, пока [npc.she] неохотно заходит в бассейн...",
			"Яростно глядя на тебя, [npc.name] неохотно плюхается в теплую воду бассейна...",
			"Раздраженная тем, что [npc.sheHasFull] приходится присоединяться к тебе в бассейне, [npc.name] гневно сверлит тебя взглядом...");
	
	private static List<String> bathingRudeSpeech = Util.newArrayListOfValues(
			"Спа — это мило, но куда лучше, когда тебя здесь нет...",
			"Поторапливайся и уходи, чтобы я снова могла наслаждаться этим в одиночестве...",
			"Просто поскорее выметайся отсюда...");
	
	
	private static String getReceptionSlavesDescription(List<GameCharacter> slavesReception) {
		if(slavesReception.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesReception.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesReception) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append("Получив указание работать администратором в твоем личном спа, твоя рабыня, "+Util.stringsToStringList(names, false)+", стоит за стойкой регистрации.");
			} else {
				sb.append("Получив указание работать администраторами в вашем личном спа-салоне, ваши рабыни, "+Util.stringsToStringList(names, false)+", стоят за стойкой регистрации.");
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesReception.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(receptionGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(receptionGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(receptionSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesReception);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(receptionRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(receptionRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(receptionRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	private static String getShowerSlavesDescription(List<GameCharacter> slavesWashing) {
		if(slavesWashing.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing, "Получив указание помочь вам с мытьем, ваша рабыня, "+Util.stringsToStringList(names, false)+", [npc.steps] заходит в душевую и готовится начать мыть вас..."));
			} else {
				sb.append(UtilText.parse(slavesWashing, "Получив указание помочь вам с мытьем, ваши рабыни, "+Util.stringsToStringList(names, false)+", [npc.step] заходят в душевую и готовятся начать мыть вас..."));
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(showerGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(showerGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(showerSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesWashing);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(showerRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(showerRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(showerRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}

	private static String getSpaSlavesDescription(List<GameCharacter> slavesPresent) {
		if(slavesPresent.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		boolean soloSlave = slavesPresent.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesPresent) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append("[style.boldMinorGood(Присутствующие рабыни)]<b>:</b><br/>");
				sb.append(UtilText.parse(slavesPresent, "Сидя на одном из шезлонгов у бассейна, ваша рабыня, "+Util.stringsToStringList(names, false)+", ждет, когда вы отдадите [npc.herHim] приказ."));
				if(slavesPresent.get(0).hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)) {
					sb.append(UtilText.parse(slavesPresent,
							"</p><p>[style.boldAqua(Купание)]<b>:</b><br/>Раз уж ты велела "+Util.stringsToStringList(names, false)+" ей присоединяться к тебе каждый раз, когда ты принимаешь ванну в спа, можешь быть уверена, что [npc.she] составит тебе компанию, стоит тебе окунуться в один из бассейнов."));
				}
				if(slavesPresent.get(0).hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE)) {
					sb.append(UtilText.parse(slavesPresent,
							"</p><p>[style.boldTan(Массаж)]<b>:</b><br/>"+Util.stringsToStringList(names, false)+" [npc.has] прошла небольшое обучение искусству массажа, так что если ты захочешь, чтобы [npc.herHim] сделала тебе массаж, тебе нужно лишь лечь на один из шезлонгов и позвать [npc.herHim]."));
				}
				
			} else {
				sb.append("[style.boldMinorGood(Присутствующие рабыни)]<b>:</b><br/>");
				sb.append(UtilText.parse(slavesPresent, "Сидя на шезлонгах у бассейнов, твои рабыни "+Util.stringsToStringList(names, false)+"ждут, когда ты отдашь им приказ."));
				List<String> bathingNames = new ArrayList<>();
				GameCharacter bathingSlave = null;
				for(GameCharacter npc : slavesPresent) {
					if(npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)) {
						bathingSlave = npc;
						bathingNames.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
					}
				}
				if(!bathingNames.isEmpty()) {
					sb.append("</p><p>[style.boldAqua(Купание)]<b>:</b><br/>");
					if(bathingNames.size()==1) {
						sb.append(UtilText.parse(bathingSlave,
								"Раз уж ты велела [npc.herHim] присоединяться к тебе каждый раз, когда ты принимаешь ванну в спа, можешь быть уверена, что "+Util.stringsToStringList(bathingNames, false)+" составит тебе компанию, стоит тебе окунуться в один из бассейнов."));
					} else {
						sb.append("Раз уж ты велела им присоединяться к тебе каждый раз, когда ты принимаешь ванну в спа, можешь быть уверена, что "+Util.stringsToStringList(bathingNames, false)+" составит тебе компанию, стоит тебе окунуться в один из бассейнов.");
					}
				}
				List<String> massageNames = new ArrayList<>();
				GameCharacter massageSlave = null;
				for(GameCharacter npc : slavesPresent) {
					if(npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE)) {
						massageNames.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
					}
				}
				if(!massageNames.isEmpty()) {
					sb.append("</p><p>[style.boldTan(Массаж)]<b>:</b><br/>");
					if(massageNames.size()==1) {
						sb.append(UtilText.parse(massageSlave,
								Util.stringsToStringList(bathingNames, false)+" [npc.has] прошла небольшое обучение искусству массажа, так что если ты захочешь, чтобы [npc.herHim] сделала тебе массаж, тебе нужно лишь лечь на один из шезлонгов и позвать [npc.herHim]."));
					} else {
						sb.append(Util.stringsToStringList(bathingNames, false)+" их обучили искусству массажа, так что если вы захотите, чтобы одна из этих рабынь сделала вам массаж, вам нужно лишь лечь на один из шезлонгов и позвать одну из них.");
					}
				}
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static String getBathingSlavesDescription(List<GameCharacter> slavesWashing) {
		if(slavesWashing.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing, "Получив указание присоединяться к вам каждый раз, когда вы принимаете ванну, ваша рабыня, "+Util.stringsToStringList(names, false)+", [npc.steps] входит в бассейн и погружается в теплую воду рядом с вами..."));
			} else {
				sb.append(UtilText.parse(slavesWashing, "Получив указание присоединяться к вам каждый раз, когда вы принимаете ванну, ваши рабыни, "+Util.stringsToStringList(names, false)+", [npc.step] входят в бассейн и погружаются в теплую воду рядом с вами..."));
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(bathingGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(bathingGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(bathingSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesWashing);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(bathingRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(bathingRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(bathingRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+" [npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode SPA_INSTALLATION = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Решиться", "Решиться расширить эту комнату и превратить её в частный спа-салон.", SPA_INSTALLATION_COMMIT) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation;
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX(), cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Передумать", "Сказать Lilaya, что вы передумали...", SPA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA.getInstallCost());
						cellInstallation.addPlaceUpgrade(PlaceUpgrade.LILAYA_EMPTY_ROOM);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_INSTALLATION_COMMIT = new DialogueNode("Спа", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Последовать примеру Lilaya и выйти в коридор...", SPA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_COMMIT_LEAVE"));
						Main.game.getTextStartStringBuilder().append(PlaceType.LILAYA_HOME_CORRIDOR.getDialogue(false).getContent());
						if(Main.game.getActiveWorld().getWorldType()==WorldType.LILAYAS_HOUSE_GROUND_FLOOR) { // To cover for if the player is upgrading via Office's occupancy ledger
							Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_INSTALLATION_END = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SPA_CONSTRUCTION = new DialogueNode("Строительная площадка", "", false) {
		@Override
		public void applyPreParsingEffects() {
			// This is a backup check to finish construction if somehow the building site has not been converted to the spa:
			if(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID) > 7) {
				LilayaHomeGeneric.dailyUpdate();
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			int daysLeft = 7 - (int)(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID));
			UtilText.addSpecialParsingString(Util.intToString(daysLeft)+" "+(daysLeft==1?"day":"days"), true);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CONSTRUCTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_RECEPTION = new DialogueNode("Спа", "", false) {
		@Override
		public void applyPreParsingEffects() {
			drinksCharacter = null;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				int daysLeft = 7 - (int)(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID));
				UtilText.addSpecialParsingString(Util.intToString(daysLeft)+" "+(daysLeft==1?"day":"days"), true);
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CONSTRUCTION");
				
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION", getSlaves()));
				sb.append(getReceptionSlavesDescription(getSlaves()));
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_END"));
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
				return sb.toString();
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<GameCharacter> slavesAssignedToRoom = getSlaves();
			
			if(index==0) {
				return null;
				
			} else if(index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять комнатой", "Перейти к экрану управления этой конкретной комнатой.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Управлять комнатой", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			} else if(index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять людьми", "Перейти к экрану управления вашими рабынями и дружелюбными жильцами.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Управлять людьми", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			} else if(index==3) {
				if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)!=null) {
					return new Response("Душ",
							"Воспользуйтесь одним из душевых, чтобы быстро привести себя в порядок перед тем, как войти непосредственно в спа-зону.<br/>[style.italicsGood(Очищает <b> максимум "+Units.fluid(500)+"</b> жидкостей из всех отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
							SPA_RECEPTION_SHOWER){
						@Override
						public void effects() {
							slavesWashing = getSlaves().stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA_RECEPTIONIST, SlaveJobSetting.SPA_SHOWERING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, true, null, 240+30);
							}

							Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>Вы оставляете одежду в раздевалке, чтобы её почистили, пока вы моетесь...</i></p>");
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(false, false, null, 240+30));
						}
					};
				}
				return null;
			}
			//TODO Inviting slaves, occupants, Arthur, Lilaya (who is always accompanied by Rose)
			// Leads into them taking showers and into pools
			//TODO make spa core scene travel disabled
			// If get Lilaya drunk, Rose makes her perform cunnilingus (Rose sits on edge of pool while Lilaya is in it)
				// Rose asks if you'd like to join her bitch
				// Leads into Rose fucking you and Lilaya with strapon
			
			int indexPresentStart = 4;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = (NPC) slavesAssignedToRoom.get(index-indexPresentStart);
				return LilayaHomeGeneric.interactWithNPC(character);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SPA_RECEPTION_SHOWER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_SHOWER", getSlaves()));
			sb.append(getShowerSlavesDescription(slavesWashing));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_SHOWER_END", getSlaves()));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] showerSlots = new SexSlot[] {
					SexSlotStanding.STANDING_SUBMISSIVE,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND,
					SexSlotStanding.STANDING_SUBMISSIVE_TWO,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO};
			
			if(index==1) {
				return new Response("Закончить", "Закончите принимать душ и вернитесь в раздевалку.", AFTER_SHOWER_FINISHED);
				
			} else if(index==2) {
				if(slavesWashing.isEmpty()) {
					return new Response("Секс", "У вас нет рабов, назначенных на ресепшн спа с правом мытья, так что заняться сексом здесь не с кем...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size(); i++) {
					slaveSlots.put(slavesWashing.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Секс",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "Заняться доминантным сексом с [npc.name] в душе.")
							:"Заняться доминантным сексом со своими рабами в душе.",
						true, false,
						new SMShower(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								slaveSlots),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "START_SHOWER_SEX_AS_DOM", slavesWashing)) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
					}
				};
				
			} else if(index==3) {
				if(slavesWashing.isEmpty()) {
					return new Response("Субмиссивный секс", "У вас нет рабов, назначенных на ресепшн спа с правом мытья, так что заняться сабмиссивным сексом здесь не с кем...", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Субмиссивный секс",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "Так как [npc.name] не испытывает к вам влечения, [npc.she] не желает выступать в роли доминанта в сексе...")
								:UtilText.parse(slavesWashing, "Так как [npc2.name] не испытывает к вам влечения, [npc2.she] не желает выступать в роли доминанта в сексе..."),
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), showerSlots[i]);
				}
				return new ResponseSex("Субмиссивный секс",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Позволить [npc.name] доминантно трахать вас в душе.")
										:"Позволь своим рабыням доминантно трахать тебя в душе.",
						true, true,
						new SMShower(SexPosition.STANDING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT))),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "START_SHOWER_SEX_AS_SUB", attractedSlaves)) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SHOWER_SEX = new DialogueNode("Завершено", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getDescription() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			if(sexSlaves.size()==1) {
				return UtilText.parse(sexSlaves, "Насладившись [npc.her], [npc.name] напоминает тебе, что у тебя есть и другие дела, которыми нужно заняться...");
			} else {
				return "Насладившись, твои рабыни напоминают тебе, что у тебя есть и другие дела, которыми нужно заняться...";
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			UtilText.addSpecialParsingString(String.valueOf(sexSlaves.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_SHOWER_SEX", sexSlaves));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_RECEPTION.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_RECEPTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_SHOWER_FINISHED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_SHOWER_FINISHED", slavesWashing));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_RECEPTION.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPA_CORE = new DialogueNode("", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return isGuestPresent();
		}
		@Override
		public void applyPreParsingEffects() {
			try {
				Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).get(0).getInventory().cleanAllClothing(true);
			} catch(Exception ex) {
				System.err.println("Clothes cleaning in SPA_CORE failed!");
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			UtilText.addSpecialParsingString(String.valueOf(getSlaves().isEmpty()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE"));
			
			sb.append(getSpaSlavesDescription(getSlaves()));
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA_BAR).isEmpty()) {
				return null;
			}
			if(index==0) {
				return "Бассейны";
			} else if(index==1) {
				return "Бар";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isGuestPresent() && index==0) {
				return new Response("Уйти",
						UtilText.parse(guest, "Скажи [npc.name], что тебе пора уходить, и проводи [npc.herHim] из спа."),
						SPA_GUEST_END);
			}
			
			if(responseTab==1) {
				if(index==0) {
					return null;
				}
				GameCharacter target = 
						drinksCharacter==null || (!getSlaves().contains(drinksCharacter) && !isGuestPresent())
							?Main.game.getPlayer()
							:drinksCharacter;
							
				if(index==11) {
					return new ResponseEffectsOnly(
							UtilText.parse(target, "Цель: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Передать целевому персонажу напиток.") {
						@Override
						public void effects() {
							List<GameCharacter> characters = Util.newArrayListOfValues(Main.game.getPlayer());
							if(isGuestPresent()) {
								characters.add(guest);
							} else {
								characters.addAll(getSlaves());
							}
							for(int i=0; i<characters.size();i++) {
								if(characters.get(i).equals(target)) {
									if(i==characters.size()-1) {
										drinksCharacter = characters.get(0);
									} else {
										drinksCharacter = characters.get(i+1);
										break;
									}
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				if(index-1<getDrinks().size()) {
					AbstractItem drink = Main.game.getItemGen().generateItem(getDrinks().get(index-1));
					return new Response(drink.getName(false, false),
							target.isPlayer()
								?"Выпить бокал "+drink.getName(false, false)+" из бара."
								:UtilText.parse(target, "Дать [npc.name] бокал "+drink.getName(false, false)+" из бара."),
							SPA_CORE_BAR_DRINK) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(drink.getName(false, false), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BAR_DRINK", target));
							if(target instanceof Arthur || target instanceof Rose) { // Arthur and Rose do not get drunk (Lilaya does...)
								if(!drink.getItemTags().contains(ItemTag.ALCOHOLIC)) {
									Main.game.getTextStartStringBuilder().append(drink.applyEffect(Main.game.getPlayer(), target));
								}
								
							} else {
								Main.game.getTextStartStringBuilder().append(drink.applyEffect(Main.game.getPlayer(), target));
							}
						}
					};
				}
				
				return null;
			}
			
			if(isGuestPresent()) {
				if(index==1) {
					return new Response("Воспользоваться бассейном",
							UtilText.parse(guest, "Спуститься в один из бассейнов с [npc.name] и немного расслабиться...")
								+ "<br/>[style.italicsExcellent(Это вымоет <b>все</b> жидкости из всех ваших отверстий.)]",
							SPA_GUEST_CORE_BATHING){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(guest.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
				}
				if(index==2) {
					return new Response("Сделать массаж",
							UtilText.parse(guest, "Уложить [npc.name] на один из шезлонгов и сделать [npc.herHim] массаж."),
							SPA_GUEST_CORE_MASSAGE_GIVE);
				}
				if(index==3) {
					return new Response("Получить массаж",
							UtilText.parse(guest,
									"Прилечь на один из шезлонгов и позволить [npc.name] сделать вам массаж."),
							SPA_GUEST_CORE_MASSAGE_RECEIVE);
				}
				
			} else {
				List<GameCharacter> slaves = new ArrayList<>(getSlaves());
				if(index==1) {
					return new Response("Воспользоваться бассейном",
							"Спуститься в один из бассейнов и немного расслабиться...<br/>[style.italicsExcellent(Это вымоет <b>все</b> жидкости из всех ваших отверстий.)]"
								+ (Main.game.getPlayer().hasCompanions()
									?"<br/>[style.italicsMinorGood(Это <b>действительно</b> очищает спутников.)]"
									:""),
							SPA_CORE_BATHING){
						@Override
						public void effects() {
							bathingStripped = new ArrayList<>();
							slavesWashing = slaves.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60);
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
					
				} else if(index==2) {
					return new Response("Воспользоваться бассейном (раздеться)",
							"Разденься догола, затем окунись в один из бассейнов и позволь себе немного расслабиться...<br/>[style.italicsExcellent(Это вымоет все <b>все</b> жидкости из всех твоих отверстий.)]"
								+ (Main.game.getPlayer().hasCompanions()
									?"<br/>[style.italicsMinorGood(Это <b>действительно</b> очищает спутников.)]"
									:""),
							SPA_CORE_BATHING){
						@Override
						public void effects() {
							bathingStripped = new ArrayList<>();
							bathingStripped.add(Main.game.getPlayer());
							Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, false);
							slavesWashing = slaves.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60);
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
					
				} else if(index==3) {
					if(!slaves.stream().anyMatch(npc -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE))) {
						return new Response("Массаж",
								"Нет свободных рабынь, чтобы сделать тебе массаж!<br/><i>Тебе нужно назначить рабынь для работы в спа и дать им разрешение на '"+SlaveJobSetting.SPA_MASSAGE.getName()+"' массаж'.</i>",
								null);
					}
					return new Response("Массаж",
							"Попросить одну из своих рабынь сделать тебе массаж.<br/>[style.italics(Перейди к экрану выбора рабынь, где ты сможешь выбрать ту, которая будет делать тебе массаж.)]",
							SPA_MASSAGE_SELECTION);
					
				} else if(index==4) {
//					return new Response("Relax", "Lie down on one of the loungers and just relax for a while...", null); //TODO Slave use player
					return null;
					
				} else if(index==5) { //TODO All slimes turn the pool into slime and massage you. Requires unique sex scene with special actions as they fuck you from everywhere at once
//					if(!slaves.stream().anyMatch(npc -> npc.getRace()==Race.SLIME && npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING))) {
//						return new Response("Slime soak", "TODO", null);
//					}
//					return new Response("Slime soak", "TODO", null);
					return null;
				}
				int indexPresentStart = 6;
				if(index>0 && index-indexPresentStart<getSlaves().size()) {
					NPC character = (NPC) getSlaves().get(index-indexPresentStart);
					return LilayaHomeGeneric.interactWithNPC(character);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SPA_CORE_BAR_DRINK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Решить, что делать дальше...", SPA_CORE);
			}
			return null;
		}
	};

	public static final DialogueNode SPA_CORE_BATHING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene, and populate slaves for sex:
			slavesSex = new ArrayList<>();
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
				if(slave.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_STRIP_TO_BATHE)) {
					bathingStripped.add(slave);
					slave.unequipAllClothingIntoHoldingInventory(slave, false, false);
				}
				if(slavesSex.size()<3) {
					slavesSex.add(slave);
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(bathingStripped.contains(Main.game.getPlayer())), true);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING"));
			sb.append(getBathingSlavesDescription(slavesWashing));
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_END", slavesWashing));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] bathingSlots = new SexSlot[] {
					SexSlotSitting.SITTING_IN_LAP,
					SexSlotSitting.SITTING_TWO,
					SexSlotSitting.SITTING_THREE};
			
		    List<GameCharacter> slavesSexNoNulls = new ArrayList<>(slavesSex);
		    slavesSexNoNulls.removeIf(s->s==null);
			
			if(index==1) {
				return new Response("Закончить", "Закончить купание и выйти из бассейна.", BATHING_FINISHED);
				
			} else if(index==2) {
				if(slavesSex.isEmpty()) {
					return new Response("Секс", "У тебя нет рабынь, назначенных для работы в спа с правом купания, так что тебе не с кем заняться сексом...", null);
				}
				if(slavesSexNoNulls.isEmpty()) {
					return new Response("Секс", "Тебе нужно выбрать хотя бы одну рабыню для секса...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesSexNoNulls.size() && i<bathingSlots.length; i++) {
					slaveSlots.put(slavesSexNoNulls.get(i), bathingSlots[i]);
				}
//				UtilText.addSpecialParsingString(String.valueOf(slavesSexNoNulls.size()), true);
				List<GameCharacter> notAttractedList = new ArrayList<>();
				List<String> notAttractedNamesList = new ArrayList<>();
				
				for(GameCharacter slaveTarget : slavesSexNoNulls) {
					if(!slaveTarget.isAttractedTo(Main.game.getPlayer())) {
						notAttractedList.add(slaveTarget);
						notAttractedNamesList.add(UtilText.parse(slaveTarget, "[npc.name]"));
					}
				}
				
				if(!Main.game.isNonConEnabled() && !notAttractedList.isEmpty()) {
					return new Response("Секс",
							notAttractedList.size()==1
								?UtilText.parse(notAttractedList.get(0), "[npc.Name] не испытывает к тебе влечения, поэтому [npc.she] не хочет, чтобы ты трахал [npc.herHim]...")
								:Util.stringsToStringList(notAttractedNamesList, true)+" не испытывают к тебе влечения, поэтому не хотят заниматься с тобой сексом...",
							null);
				}
				return new ResponseSex(slavesSexNoNulls.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()))?"Изнасилование":"Секс",
						(slavesSexNoNulls.size()==1
							?UtilText.parse(slavesSexNoNulls, "Заняться доминантным сексом с [npc.name].")
							:(slavesSexNoNulls.size()==2
								?UtilText.parse(slavesSexNoNulls, "Заняться доминантным сексом с [npc.name], пока [npc2.name] сидит рядом.")
								:UtilText.parse(slavesSexNoNulls, "Заняться доминантным сексом с [npc.name], пока [npc2.name] и [npc3.name] сидят рядом.")))
						+(notAttractedList.isEmpty()
							?""
							:(notAttractedList.size()==1
								?UtilText.parse(notAttractedList.get(0), "<br/>[style.italicsBad([npc.Name] не испытывает к тебе влечения, поэтому сочтет это изнасилованием...)]")
								:"<br/>[style.italicsBad("+Util.stringsToStringList(notAttractedNamesList, true)+" не испытывают к тебе влечения, поэтому сочтут это изнасилованием...)]")),
						true, false,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								slaveSlots) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return bathingStripped.contains(character);
							}
						},
						null,
						null,
						BATHING_AFTER_SEX,
						"") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(slavesSexNoNulls.size()), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_SEX_DOM", slavesSexNoNulls));
					}
				};
				
			} else if(index==3) {
				if(slavesSex.isEmpty()) {
					return new Response("Субмиссивный секс", "У тебя нет рабов, назначенных для работы в спа с правом купания, так что тебе не с кем заняться сабмиссивным сексом...", null);
				}
				if(slavesSexNoNulls.isEmpty()) {
					return new Response("Субмиссивный секс", "Тебе нужно выбрать хотя бы одну рабыню для секса...", null);
				}
				
				if(!slavesSexNoNulls.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Субмиссивный секс",
							slavesSexNoNulls.size()==1
								?UtilText.parse(slavesSexNoNulls, "Так как [npc.name] не испытывает к вам влечения, [npc.she] не желает выступать в роли доминанта в сексе...")
								:"Так как твои рабы не испытывают к тебе влечения, они не хотят выступать в роли доминантных партнеров в сексе...",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesSexNoNulls.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size() && i<bathingSlots.length; i++) {
					slaveSlots.put(attractedSlaves.get(i), bathingSlots[i]);
				}
				return new ResponseSex("Субмиссивный секс",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Позволить [npc.name] доминантно трахать тебя в бассейне.")
										:"Позволить своим рабам доминантно трахать тебя в бассейне.",
						true, true,
						new SMBath(SexPosition.SITTING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return bathingStripped.contains(character);
							}
						},
						null,
						null,
						BATHING_AFTER_SEX,
						"") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_SEX_SUB", attractedSlaves));
					}
				};
			}
			if(slavesSex.size()>0) {
				if(index==11) {
					GameCharacter target = slavesSex.get(0);
					return new ResponseEffectsOnly(
							target==null
								?"Цель: [style.boldDisabled(Нет)]"
								:UtilText.parse(target, "Цель: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Переключить основного персонажа для секса.") {
						@Override
						public void effects() {
							setNextSexTarget(0);
							Main.game.updateResponses();
						}
					};
					
				} else if(index==12 && slavesSex.size()>1) {
					GameCharacter target = slavesSex.get(1);
					return new ResponseEffectsOnly(
							target==null
								?"Цель: [style.boldDisabled(Нет)]"
								:UtilText.parse(target, "Цель: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Переключить второстепенного персонажа для секса.") {
						@Override
						public void effects() {
							setNextSexTarget(1);
							Main.game.updateResponses();
						}
					};
					
				} else if(index==13 && slavesSex.size()>2) {
					GameCharacter target = slavesSex.get(2);
					return new ResponseEffectsOnly(
							target==null
								?"Цель: [style.boldDisabled(Нет)]"
								:UtilText.parse(target, "Цель: <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"Переключить третьего персонажа для секса.") {
						@Override
						public void effects() {
							setNextSexTarget(2);
							Main.game.updateResponses();
						}
					};
				}
			}
			return null;
		}
	};
	
	private static void setNextSexTarget(int index) {
		GameCharacter current = slavesSex.get(index);
		if(slavesSex.get(index)==null) {
			slavesSex.remove(index);
			slavesSex.add(index, slavesWashing.get(0));
		} else {
			for(int i=0; i<slavesWashing.size();i++) {
				if(slavesWashing.get(i).equals(current)) {
					slavesSex.remove(index);
					if(i==slavesWashing.size()-1) {
						slavesSex.add(index, null);
					} else {
						slavesSex.add(index, slavesWashing.get(i+1));
					}
					break;
				}
			}
		}
		
		Set<GameCharacter> excludedCharacters = new HashSet<>();
		GameCharacter newTarget = slavesSex.get(index);
		if(newTarget!=null) {
			excludedCharacters.add(newTarget);
		}
		
		for(int i=0; i<slavesSex.size(); i++) {
			if(i!=index) {
				if(excludedCharacters.contains(slavesSex.get(i))) {
					slavesSex.remove(i);
					slavesSex.add(i, current);
				}
			}
		}
	}

	public static final DialogueNode BATHING_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
//			for(GameCharacter character : bathingStripped) {
//				character.equipAllClothingFromHoldingInventory();
//			}
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getDescription() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			if(sexSlaves.size()==1) {
				return UtilText.parse(sexSlaves, "Насладившись [npc.her], [npc.name] напоминает тебе, что у тебя есть и другие дела, которыми нужно заняться...");
			} else {
				return "Насладившись, твои рабыни напоминают тебе, что у тебя есть и другие дела, которыми нужно заняться...";
			}
		}
		@Override
		public String getContent() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			UtilText.addSpecialParsingString(String.valueOf(sexSlaves.size()), true);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "BATHING_AFTER_SEX", sexSlaves);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE_BATHING.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE_BATHING.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BATHING_FINISHED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter character : bathingStripped) {
				character.equipAllClothingFromHoldingInventory();
			}
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "BATHING_FINISHED", slavesWashing));
			sb.append(SPA_CORE.getContent());
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SPA_MASSAGE_SELECTION = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			slavesForMassage = new ArrayList<>(getSlaves());
			slavesForMassage.removeIf(s -> !s.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE));
		}
		@Override
		public String getContent() {
			List<String> names = new ArrayList<>();
			for(GameCharacter npc : slavesForMassage) {
				names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
			}
			
			UtilText.addSpecialParsingString(String.valueOf(slavesForMassage.size()), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SELECTION", slavesForMassage);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "В итоге решить отказаться от массажа...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SELECTION_BACK"));
					}
				};
			}
			if(index-1<slavesForMassage.size()) {
				GameCharacter slave = slavesForMassage.get(index-1);
				return new Response(UtilText.parse(slave, "[npc.Name]"),
						UtilText.parse(slave,
								"Прилечь на один из шезлонгов и позволить [npc.name] сделать вам массаж."
								+ (slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())
									?(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)
										?"<br/>[style.italicsSex(Так как [npc.name] имеет разрешение использовать тебя для секса, а [npc.she] сейчас на взводе, [npc.she] наверняка начнет трахать тебя прямо во время массажа!)]"
										:"<br/>[style.italicsSex(Так как [npc.name] имеет разрешение использовать тебя для секса, [npc.she] может начать трахать тебя прямо во время массажа!)]")
									:"")),
						SPA_MASSAGE) {
					@Override
					public Colour getHighlightColour() {
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())) {
							return PresetColour.GENERIC_SEX;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						massageSlave = slave;
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())) {
							massageSlaveSex = slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE) || Math.random()<0.5f;
						} else {
							massageSlaveSex = false;
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(massageSlaveSex) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX", massageSlave);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE", massageSlave);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(massageSlaveSex) {
				if(index==1) {
					return new ResponseSex(
							"Трахнутый",
							UtilText.parse(massageSlave, "[npc.Name] прижимает тебя к шезлонгу, готовая начать трахать тебя..."),
							true,
							!(massageSlave.isWillingToRape(Main.game.getPlayer()) && massageSlave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)),
							new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(massageSlave, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_FUCKED", massageSlave));	
					
				} else if(index==2) {
					if(massageSlave.isWillingToRape(Main.game.getPlayer()) && massageSlave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)) {
						return new Response("Отказаться",
								UtilText.parse(massageSlave, "Раз уж ты дал(а) [npc.herHim] разрешение на изнасилование, [npc.nameIsFull] не примет отказ!"),
								null);
						
					} else {
						return new Response("Отказаться",
								UtilText.parse(massageSlave, "У тебя сейчас совсем нет настроения, так что решительно прикажи [npc.name] остановиться."),
								REFUSE_SLAVE_SEX);
						
					}
				}
				
			} else {
				if(index==1) {
					return new Response("Продолжить", "Теперь, когда ты получил приятный расслабляющий массаж, ты задумываешься, чем заняться дальше...", SPA_CORE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_FINISHED", massageSlave));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isNonConEnabled() && !massageSlave.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Секс", UtilText.parse(massageSlave, "[npc.Name] не испытывает к тебе влечения, поэтому [npc.she] не хочет, чтобы ты трахал [npc.herHim]..."), null);
					}
					return new ResponseSex(
							!massageSlave.isAttractedTo(Main.game.getPlayer())
								?"Изнасилование"
								:"Секс",
							!massageSlave.isAttractedTo(Main.game.getPlayer())
								?UtilText.parse(massageSlave, "Прижать [npc.name] к шезлонгу и изнасиловать [npc.herHim].")
								:UtilText.parse(massageSlave, "Прижать [npc.name] к шезлонгу и заняться с [npc.herHim] доминантным сексом."),
							true,
							false,
							new SexManagerDefault(massageSlave.isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), massageSlave.isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(massageSlave, massageSlave.isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX_AS_DOM", massageSlave));
					
				} else if(index==3) {
					if(!massageSlave.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Субмиссивный секс", UtilText.parse(massageSlave, "Поскольку [npc.name] не испытывает к тебе влечения, [npc.she] не желает брать на себя доминирующую роль в сексе с тобой..."), null);
					}
					return new ResponseSex(
							"Субмиссивный секс",
							UtilText.parse(massageSlave, "Приказать [npc.name] трахнуть тебя на шезлонге..."),
							true,
							false,
							new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(massageSlave, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX_AS_SUB", massageSlave));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_MASSAGE_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(massageSlave, "Насладившись [npc.her], [npc.name] напоминает тебе, что у тебя есть и другие дела, которыми нужно заняться...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_MASSAGE_SEX", massageSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда массаж оказался неожиданно веселым, ты задумываешься, чем заняться дальше...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_MASSAGE_SEX_FINISHED", massageSlave));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode REFUSE_SLAVE_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "REFUSE_SLAVE_SEX", massageSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда ты велел своей рабыне отступить, ты задумываешься, чем заняться дальше...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "REFUSE_SLAVE_SEX_FINISHED", massageSlave));
					}
				};
			}
			return null;
		}
	};
	
	
	// Guest content:
	
	public static final DialogueNode SPA_GUEST_INVITE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_INVITE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("В спа", UtilText.parse(guest, "Сопроводить [npc.name] в спа."), SPA_GUEST_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_SPA);
						guest.setLocation(Main.game.getPlayer());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_ARRIVE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			if(guest.isFeminine()) {
				AbstractClothing swimsuit = Main.game.getItemGen().generateClothing("innoxia_chest_swimsuit", PresetColour.CLOTHING_PINK, false);
				AbstractClothing bikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_bikini", PresetColour.CLOTHING_PINK_LIGHT, false);
				AbstractClothing bikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_bikini", PresetColour.CLOTHING_PINK_LIGHT, false);
				AbstractClothing microBikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_micro_bikini", PresetColour.CLOTHING_PINK_HOT, false);
				AbstractClothing microBikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_micro_bikini", PresetColour.CLOTHING_PINK_HOT, false);
				
				if(index==1) {
					if(guest.isAbleToEquip(swimsuit, true, guest)) {
						return new Response("Купальник", UtilText.parse(guest, "Попросить [npc.name] надеть скромный купальник."), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(swimsuit, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_SWIMSUIT", guest));
							}
						};
					} else {
						return new Response("Купальник", UtilText.parse(guest, "[npc.Name] не может надеть купальник, так как часть запечатанной одежды [npc.her] мешает [npc.herHim] сделать это..."), null);
					}
				}
				if(index==2) {
					if(guest.isAbleToEquip(bikiniTop, true, guest) && guest.isAbleToEquip(bikiniBottom, true, guest)) {
						return new Response("Бикини", UtilText.parse(guest, "Попросить [npc.name] надеть бикини."), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(bikiniTop, true, guest);
								guest.equipClothingFromNowhere(bikiniBottom, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_BIKINI", guest));
							}
						};
					} else {
						return new Response("Бикини", UtilText.parse(guest, "[npc.Name] не может надеть бикини, так как часть запечатанной одежды [npc.her] мешает [npc.herHim] сделать это..."), null);
					}
				}
				if(index==3) {
					if(guest.isAbleToEquip(microBikiniTop, true, guest) && guest.isAbleToEquip(microBikiniBottom, true, guest)) {
						return new Response(
								guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.colourBad(Микро-бикини)]"
									:"Микро-бикини",
								UtilText.parse(guest, "Попросить [npc.name] надеть вызывающее микро-бикини."
										+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
											?"[style.italicsBad(Так как у [npc.name] отрицательное желание к '"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"' фетиш, [npc.she] это не понравится!)]"
											:"")),
								SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(microBikiniTop, true, guest);
								guest.equipClothingFromNowhere(microBikiniBottom, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_MICRO_BIKINI", guest));
								if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
									Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
								}
							}
						};
					} else {
						return new Response("Микро-бикини", UtilText.parse(guest, "[npc.Name] не может надеть микро-бикини, так как часть [npc.her] облегающей одежды мешает [npc.herHim] сделать это..."), null);
					}
				}
				if(index==4) {
					return new Response(
							guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
								?"[style.colourBad(Обнаженная)]"
								:"Обнаженная",
							UtilText.parse(guest, "Приказать [npc.name] скинуть всю [npc.her] одежду и отправиться в спа обнаженной."
								+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.italicsBad(Так как у [npc.name] отрицательное желание к '"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"' фетиш, [npc.she] это не понравится!)]"
									:"")),
							SPA_GUEST_PLAYER_CLOTHING) {
						@Override
						public void effects() {
							guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_NAKED", guest));
							if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
				}
				
			} else {
				AbstractClothing swimShorts = Main.game.getItemGen().generateClothing("innoxia_groin_swim_shorts", PresetColour.CLOTHING_BLUE, false);
				if(index==1) {
					if(guest.isAbleToEquip(swimShorts, true, guest)) {
						return new Response("Плавки", UtilText.parse(guest, "Приказать [npc.name] надеть плавки."), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(swimShorts, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_SWIM_SHORTS", guest));
							}
						};
					} else {
						return new Response("Плавки", UtilText.parse(guest, "[npc.Name] не может надеть плавки, так как часть [npc.her] облегающей одежды мешает [npc.herHim] сделать это..."), null);
					}
				}
				if(index==2) {
					return new Response(
							guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
								?"[style.colourBad(Обнаженная)]"
								:"Обнаженная",
							UtilText.parse(guest, "Приказать [npc.name] скинуть всю [npc.her] одежду и отправиться в спа обнаженной."
								+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.italicsBad(Так как у [npc.name] отрицательное желание к '"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"' фетиш, [npc.she] это не понравится!)]"
									:"")),
							SPA_GUEST_PLAYER_CLOTHING) {
						@Override
						public void effects() {
							guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_NAKED", guest));
							if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_PLAYER_CLOTHING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Не раздеваться", "Решить не раздеваться и войти в спа в текущем виде.", SPA_GUEST_PLAYER_CLOTHING_REVEAL) {
					@Override
					public void effects() {
						playerStripped = false;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_CONTINUE", guest));
					}
				};
			}
			if(index==2) {
				return new Response("Раздеться",
						"Сбросить всю одежду и войти в спа нагишом.",
						SPA_GUEST_PLAYER_CLOTHING_REVEAL) {
					@Override
					public void effects() {
						playerStripped = true;
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_NAKED", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_PLAYER_CLOTHING_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_REVEAL", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Бассейны", UtilText.parse(guest, "Провести [npc.name] к бассейнам."), SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_REVEAL_POOLS", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_CORE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE", guest);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPA_GUEST_CORE_BATHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Закончить", "Закончить купание и выйти из бассейна.", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_END", guest));
					}
				};
				
			} else if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Секс",
							UtilText.parse(guest, "[npc.Name] не испытывает к тебе влечения, поэтому [npc.she] не хочет, чтобы ты трахал [npc.herHim]..."),
							null);
				}
				return new ResponseSex("Секс",
						UtilText.parse(guest, "Заняться доминантным сексом с [npc.name]."),
						true, false,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(guest, SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						SPA_GUEST_CORE_BATHING_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_SEX_START", guest));
				
			} else if(index==3) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Субмиссивный секс",
							UtilText.parse(guest, "[npc.Name] не испытывает к тебе влечения, поэтому [npc.she] не хочет, чтобы ты трахал [npc.herHim]..."),
							null);
				}
				return new ResponseSex("Субмиссивный секс",
						UtilText.parse(guest, "Позволить [npc.name] взять инициативу в свои руки и трахнуть вас прямо здесь, в бассейне."),
						true, true,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(guest, SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						SPA_GUEST_CORE_BATHING_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_SUBMISSIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_BATHING_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getDescription() {
			return UtilText.parse(guest, "Насладившись временем с [npc.her], [npc.name] спрашивает, не хотите ли вы заняться чем-нибудь еще...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_AFTER_SEX", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Закончить купание и выйти из бассейна.", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_AFTER_SEX_END", guest));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_GIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", UtilText.parse(guest, "После того как вы сделали [npc.name] приятный расслабляющий массаж, вы задумываетесь, чем заняться дальше..."), SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE_END", guest));
					}
				};
				
			} else if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response(UtilText.parse(guest, "Трахнуть [npc.herHim]"),
							UtilText.parse(guest, "Так как [npc.name] не испытывает к вам влечения, [npc.she] не желает заниматься с вами сексом..."),
							null);
				}
				return new ResponseSex(
						UtilText.parse(guest, "Трахнуть [npc.herHim]"),
						UtilText.parse(guest, "Дай [npc.name] то, чего [npc.she] так явно от тебя хочет..."),
						true,
						false,
						new SexManagerDefault(guest.isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), guest.isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(guest, guest.isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public String getDeskName() {
								return "массажный стол";
							}
						},
						null,
						null,
						SPA_GUEST_CORE_MASSAGE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_RECEIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда ты получил приятный расслабляющий массаж, ты задумываешься, чем заняться дальше...", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE_END", massageSlave));
					}
				};
				
			}
			if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Трахнутый",
							UtilText.parse(guest, "Так как [npc.name] не испытывает к вам влечения, [npc.she] не желает заниматься с вами сексом..."),
							null);
				}
				return new ResponseSex(
						"Трахнутый",
						UtilText.parse(guest, "Скажи [npc.name], что [npc.she] может трахать тебя..."),
						true,
						false,
						new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(guest, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public String getDeskName() {
								return "массажный стол";
							}
						},
						null,
						null,
						SPA_GUEST_CORE_MASSAGE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(guest, "Насладившись временем с [npc.her], [npc.name] спрашивает, не хотите ли вы заняться чем-нибудь еще...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_AFTER_SEX", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда массаж оказался неожиданно веселым, ты задумываешься, чем заняться дальше...", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_AFTER_SEX_FINISHED", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.equipAllClothingFromHoldingInventory();
			guest.returnToHome();
			if(playerStripped) {
				Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			}
			Main.game.getPlayer().setNearestLocation(PlaceType.LILAYA_HOME_CORRIDOR);

			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_END", guest));
			
			LilayaSpa.guest = null;
			drinksCharacter = null;
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда массаж оказался неожиданно веселым, ты задумываешься, чем заняться дальше...", PlaceType.LILAYA_HOME_CORRIDOR.getDialogue(false));
			}
			return null;
		}
	};
	
	
	
	
	//TODO
	// Expansion:
	

	public static final DialogueNode SPA_SAUNA_INSTALLATION = new DialogueNode("", "", true) { //TODO
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Решиться", "Решиться на расширение спа и пристройку сауны.", SPA_SAUNA_INSTALLATION_END) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation; //TODO
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Передумать", "Сказать Lilaya, что вы передумали...", SPA_SAUNA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA_SAUNA.getInstallCost());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_SAUNA_INSTALLATION_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_SAUNA = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сауна", "Провести немного времени в сауне.", null);
			}
			//TODO slave interaction
			
			return null;
		}
	};
	

	public static final DialogueNode SPA_POOL_INSTALLATION = new DialogueNode("", "", true) { //TODO
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Решиться", "Решиться на расширение спа и пристройку сауны.", SPA_POOL_INSTALLATION_END) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation; //TODO
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()-1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()-1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Передумать", "Сказать Lilaya, что вы передумали...", SPA_POOL_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA_POOL.getInstallCost());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_POOL_INSTALLATION_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_POOL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поплавать", "Поплавать.", null);
			}
			//TODO slave interaction
			
			return null;
		}
	};
	
}
