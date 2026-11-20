package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * This NPC doesn't spawn with addictive fluids so as to prevent issues with randomly getting their partners addicted to fluids.
 * 
 * @since 0.2.2
 * @version 0.4.8.4
 * @author Innoxia
 */
public class GenericSexualPartner extends NPC {

	public GenericSexualPartner() {
		this(Gender.getGenderFromUserPreferences(false, false), WorldType.EMPTY, new Vector2i(0, 0), false);
	}
	
	public GenericSexualPartner(boolean isImported) {
		this(Gender.F_V_B_FEMALE, WorldType.EMPTY, new Vector2i(0, 0), isImported);
	}

	public GenericSexualPartner(Gender gender, AbstractWorldType worldLocation, Vector2i location, boolean isImported) {
		this(gender, worldLocation, location, isImported, null);
	}

	public GenericSexualPartner(Gender gender, AbstractWorldType worldLocation, AbstractPlaceType placeType, boolean isImported, Predicate<AbstractSubspecies> subspeciesRemovalFilter) {
		this(gender, worldLocation, Main.game.getWorlds().get(worldLocation).getCell(placeType).getLocation(), isImported, subspeciesRemovalFilter);
	}
	
	public GenericSexualPartner(Gender gender, AbstractWorldType worldLocation, Vector2i location, boolean isImported, Predicate<AbstractSubspecies> subspeciesRemovalFilter) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(false, 10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			this.setLocation(worldLocation, location, false);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>();
			availableSubspecies.addAll(Subspecies.getAllSubspecies());
			
			if(subspeciesRemovalFilter!=null) {
				availableSubspecies.removeIf(subspeciesRemovalFilter);
			}
			
			for(AbstractSubspecies s : availableSubspecies) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s==Subspecies.REINDEER_MORPH
						&& Main.game.getSeason()==Season.WINTER
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					AbstractSubspecies.addToSubspeciesMap(50, gender, s, availableRaces);
				}
				
				if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, subspeciesRemovalFilter==null);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this, "[npc.NameIsFull] [npc.a_race]."));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			// Do not allow addictive fluids:
			this.removeMilkCrotchModifier(FluidModifier.ADDICTIVE);
			this.removeMilkModifier(FluidModifier.ADDICTIVE);
			this.removeCumModifier(FluidModifier.ADDICTIVE);
			this.removeGirlcumModifier(FluidModifier.ADDICTIVE);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setName(new NameTriplet("неизвестный мужчина", "неизвестная женщина", "неизвестная женщина"));
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
		}
		
		if(setFetishes) {
		}
		
		if(setOrientation) {
		}

		if(setHistory) {
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	private boolean playerRequested = false;
	
	@Override
	public void generateSexChoices(boolean resetPositioningBan, GameCharacter target, List<SexType> request) {
		if(this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) && Main.sex.getTurn()>1) {
			playerRequested = true;
		}
		
		super.generateSexChoices(resetPositioningBan, target, request);
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.isHappyToBeInSlot(position, slot, target);
			
		} else {
			if(Main.sex.isInForeplay(this) || this.hasFetish(Fetish.FETISH_ORAL_GIVING) || !target.hasPenis()) {
				return slot==SexSlotUnique.GLORY_HOLE_KNEELING;
			} else {
				return slot==SexSlotUnique.GLORY_HOLE_FUCKED;
			}
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.getForeplayPreference(target);
		}
		
		if(target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
		} else if(target.hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
		} else {
			return super.getForeplayPreference(target);
		}
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.getMainSexPreference(target);
		}
		
		if(this.hasFetish(Fetish.FETISH_ORAL_GIVING)) {
			return getForeplayPreference(target);
		}
		
		if(this.hasVagina() && target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		} else if(target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
		}

		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer()
				|| penetrating != SexAreaPenetration.PENIS
				|| penetrated != SexAreaOrifice.VAGINA
				|| (!penetratingCharacter.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY) && !penetratingCharacter.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY))) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().hasHymen()) {
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("Когда [npc.namePos] [npc.cock+] [pc.pussy+], ты не можешь удержаться от отчаянного, дрожащего вопля. Будучи настолько очарованной идеей оставаться невинной девой, ты и сама не понимаешь, что за чертовщина заставила тебя ввязаться в эту «рулетку беременности», но пока [npc.namePos] [npc.cock+] забирает твою драгоценную девственность, у тебя нет времени размышлять о своем неверном выборе. Единственное, о чем ты можешь думать — это мучительная боль от того, что твоя девственная плева рвется человеком, которого ты никогда раньше даже не видела и с которым не разговаривала.");
				} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
					sb.append("Когда [npc.namePos] [npc.cock+] [pc.pussy+], чтобы разорвать твою плеву и забрать твою девственность, ты невольно издаешь похотливый, мазохистский крик. Мучительная боль от того, что твою плеву рвет человек, которого ты никогда раньше даже не видела и с которым не разговаривала, полностью овладевает тобой, и ты не можешь не визжать и стонать в восхитительном тумане всепоглощающего экстаза.");
				} else {
					sb.append("Когда [npc.namePos] [npc.cock+] [pc.pussy+], чтобы забрать твою девственность, ты не можешь удержаться от отчаянного, дрожащего вопля. Мучительная боль от того, что твою плеву рвет человек, которого ты никогда раньше даже не видела и с которым не разговаривала, полностью овладевает тобой, и ты ерзаешь на столе, пытаясь вытерпеть этот ужасный опыт.");
				}
			sb.append("</p>");
			
			sb.append("<p>Когда твой вой переходит в дрожащий крик, ты слышишь, как [npc.race] на другой стороне стены издает удивленный возглас: [npc.speechNoExtraEffects(Твою мать! Эта шлюшка оказалась девственницей!)]</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("Комната за стеной внезапно наполняется насмешливым смехом и похотливыми комментариями, и ты невольно пускаешь слезу, осознавая, что потеряла свою драгоценную, чистую девственность из-за какого-то незнакомца, который записался, чтобы попытаться зачать от тебя ребенка.");
				} else {
					sb.append("Комната за стеной внезапно наполняется насмешливым смехом и похотливыми комментариями, и ты невольно вздрагиваешь, осознавая, что потеряла девственность из-за какого-то незнакомца, который записался, чтобы попытаться зачать от тебя ребенка.");
				}
			sb.append("</p>");
			
			sb.append("<p>[pc.thought(Беременная... После моего первого раза... Уверена, что это невозможно...)] — думаешь ты про себя, пытаясь подавить всхлипы, когда [npc.name] отстраняется, а затем снова входит в тебя.</p><p>Пока [npc.she] наполняет твою только что раскрывшуюся киску [npc.her] [npc.cock+], ты слышишь, как [npc.herHim] насмехается над тобой с другой стороны стены: [npc.speechNoExtraEffects(Ну и гребаная шлюха! Решила лишиться девственности в игре в рулетку беременности! Ха! Рад, что мне никогда не придется рассказывать нашим детям, как они были зачаты!)]</p>");
			
		} else {
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("Когда [npc.namePos] [npc.cock+] вонзается в твою [pc.pussy+], ты невольно издаешь отчаянный вопль. Будучи настолько очарованной идеей оставаться невинной девой, ты и сама не понимаешь, что за чертовщина заставила тебя ввязаться в эту «рулетку беременности», но пока [npc.namePos] [npc.cock+] забирает твою драгоценную девственность, у тебя нет времени размышлять о своем неверном выборе. Единственное, о чем ты можешь думать — это то, что тебя «ломают» и приручают, причем человек, которого ты никогда раньше не видела и с которым даже не разговаривала, и ты не можешь не продолжать отчаянно кричать, пока [npc.she] проникает в тебя.");
				} else {
					sb.append("Когда [npc.namePos] [npc.cock+] вонзается в твою [pc.pussy+], чтобы забрать твою девственность, ты невольно издаешь отчаянный, похотливый [pc.moan]. Этот человек, которого ты никогда раньше не видела и с которым даже не разговаривала, дарит тебе ощущения, подобные которым ты еще не испытывала, и ты не можешь не продолжать кричать и [pc.moan] в восхитительном тумане всепоглощающего экстаза.");
				}
			sb.append("</p>");
			
			sb.append("<p>Благодаря тому, что ты ранее уже теряла девственную плеву, единственным признаком твоей невинности была твоя бурная реакция на трах, чего, судя по всему, оказалось достаточно для [npc.race] по ту сторону стены, когда [npc.she] издает удивленный возглас: [npc.speechNoExtraEffects(Твою мать! Кажется, эта шлюха была девственницей!)]</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("Комната за стеной внезапно наполняется насмешливым смехом и похотливыми комментариями, и ты невольно пускаешь слезу, осознавая, что потеряла свою драгоценную, чистую девственность из-за какого-то незнакомца, который записался, чтобы попытаться зачать от тебя ребенка.");
				} else {
					sb.append("Комната за стеной внезапно наполняется насмешливым смехом и похотливыми комментариями, и ты невольно морщишься, осознавая, что лишилась девственности с каким-то незнакомцем, который записался, чтобы попытаться зачать от тебя ребенка.");
				}
			sb.append("</p>");
			
			sb.append("<p>[pc.thought(Беременна... С самого первого раза... Уверена, что это невозможно...)] — думаешь ты про себя, непроизвольно издавая [pc.moan], когда [npc.name] отстраняется и снова врывается в тебя.</p><p>Пока [npc.she] наполняет твою только что раскрывшуюся киску [npc.her] [npc.cock+], ты слышишь, как [npc.herHim] издевается над тобой с другой стороны стены. [npc.speechNoExtraEffects(Ну и гребаная шлюха! Решила лишиться девственности в игре в рулетку беременности! Ха! Рад, что мне никогда не придется рассказывать нашим детям, как они были зачаты!)]</p>");
		}
		
		return sb.toString();
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return UtilText.parse(penetratingCharacter,
				"<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Потерянная девственница</b></p><p>Пока [npc.race] продолжает долбить твою киску, внезапное осознание произошедшего бьет тебя, словно кувалдой.</p><p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("Я... я лишилась девственности?!<br/>Вот так... Вот так, как <b>это</b>?!")
				+ "</p><p>Ты не знаешь, что хуже: потеря девственности, которой так дорожила, или тот факт, что тебе это на самом деле нравится. Пока твои [pc.labia+] похотливо раздвинуты вокруг "
					+ (penetrating==SexAreaPenetration.PENIS
						?"горячего, густого [npc.cock]"
						:penetrating.getName(penetratingCharacter))
					+ " который входит и выходит из тебя, ты начинаешь убеждать себя, что только на это ты и годна.</p><p style='text-align:center;'>"
				+ UtilText.parsePlayerThought("Если я уже не девственница, значит, я шлюха...<br/>Просто шлюха, которую будет трахать каждый, кто захочет меня использовать...<br/>Когда этот [npc.race] закончит со мной, бьюсь об заклад, не пройдет и много времени, как меня будет трахать кто-то другой...")
				+ "</p><p>Вы смутно осознаете, что [npc.name] [npc.moaning] в триумфальном наслаждении, когда [npc.she] понимает, что [npc.sheHas] только что лишил вас девственности. С отчаянным [pc.moan],"
					+ (Main.game.getPlayer().hasLegs()
						?" ты раздвигаешь ноги и"
						:" вы")
					+ " смиряешься с тем фактом, что теперь ты не более чем <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>сломанная девственница</b>...</p>");
	}
	
}