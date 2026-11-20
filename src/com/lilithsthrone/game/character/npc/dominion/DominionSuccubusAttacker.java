package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayDemonDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.3.7.4
 * @author Innoxia
 */
public class DominionSuccubusAttacker extends NPC {
	
	public DominionSuccubusAttacker() {
		this(false);
	}
	
	public DominionSuccubusAttacker(boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(50)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5, Gender.getGenderFromUserPreferences(Femininity.FEMININE), Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(false, 10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
//			if(!Gender.getGenderFromUserPreferences(false, false).isFeminine()) {
//				this.setBody(Gender.M_P_MALE, Subspecies.DEMON, RaceStage.GREATER, true);
//				this.setGenderIdentity(Gender.M_P_MALE);
//			}
			
			Gender gender = Gender.getGenderFromUserPreferences(false, false);
			this.setBody(gender, Subspecies.DEMON, RaceStage.GREATER, true);
			this.setGenderIdentity(gender);
			
			
			Main.game.getCharacterUtils().randomiseBody(this, true);

			Main.game.getCharacterUtils().setHistoryAndPersonality(this, false);
			this.setHistory(Occupation.NPC_MUGGER); // All demon alleyway attackers are muggers
			
			addFetish(Fetish.FETISH_DEFLOWERING);
			addFetish(Fetish.FETISH_DOMINANT);
			Main.game.getCharacterUtils().addFetishes(this);

			this.removePersonalityTrait(PersonalityTrait.PRUDE);

			setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			setLevel(Util.random.nextInt(5) + 8);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(false);
			
			// Set random inventory & weapons:
			resetInventory(true);
			inventory.setMoney(50);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			// CLOTHING:
			
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);

			if(hasFetish(Fetish.FETISH_CUM_ADDICT) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);

			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();
			
			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
		}
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
		this.incrementMoney((long) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave() && this.isDoll()) {
			return super.getDescription();
		}
		if(isSlave()) {
			return UtilText.parse(this,
					"Потеряв [npc.herself] перед лицом [npc.her] мощного либидо, [npc.name] принялась рыскать по переулкам Dominion в поисках невинных горожан, чтобы навязать им [npc.herself]. Из-за невезения она попала в список разыскиваемых Enforcer, а затем столкнулась с вами, и вскоре [npc.she] оказалась в вашем рабстве и полной вашей собственности.");
		}
		return UtilText.parse(this,
				"Хотя у всех демонов чрезвычайно сильное либидо, некоторые страдают от него гораздо сильнее других. В то время как большинство способны контролировать свои сексуальные желания, другие, подобные этой [npc.race], изо всех сил пытаются думать лишь о том, как заполучить очередную сексуальную добычу.");
	}

	@Override
	public boolean isClothingStealable() {
		return true;
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
		return AlleywayDemonDialogue.DEMON_ATTACK;
	}

	@Override
	public boolean isAffectionHighEnoughToInviteHome() {
		if(this.isRelatedTo(Main.game.getPlayer())) {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue();
		} else {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
		}
	}

	// Combat:

	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		if(!this.isSlave() && this.getMainWeapon(0)==null) {
			if(this.isFeminine()) {
				return UtilText.parse(this, target,
						UtilText.returnStringAtRandom(
								"[npc.Name] выглядит раздраженной тем, что [npc2.nameIs] пытается сопротивляться, и бросается вперед, чтобы отвесить [npc2.her] звонкую пощечину.",
								"Сердито цокнув языком, [npc.Name] дает [npc2.name] пощечину.",
								"С разочарованным скулением [npc.Name] бьет [npc2.namePos] по голеням."));
			} else {
				return UtilText.parse(this, target,
						UtilText.returnStringAtRandom(
								"[npc.Name] выглядит раздраженной тем, что [npc2.nameIs] пытается сопротивляться, и бросается вперед, чтобы нанести мощный удар кулаком в [npc2.her] [npc2.arm].",
								"С яростным криком [npc.Name] бросается вперед и бьет [npc2.name] прямо в грудь!",
								"С разочарованным криком [npc.Name] бьет [npc2.namePos] по голеням."));
			}
		}
		return super.getMainAttackDescription(armRow, target, isHit, critical);
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AlleywayDemonDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", AlleywayDemonDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex() && !target.isAsleep()) {
			if((Main.sex.isDom(Main.game.getPlayer()) || Main.sex.isSubHasEqualControl()) && !target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return UtilText.parse(equipper, target,
						"<p>[npc2.name] [npc.name] [npc.verb(force)] [npc2.herHim], протягивая ей презерватив, чтобы она надела его. Быстро вырывая его из маленькой фольгированной упаковки, [npc2.she] [npc2.verb(roll)] по всей длине [npc2.her] [npc2.cock+], пока [npc2.she] [npc2.verb(whine)], [npc2.speech(Мне правда обязательно это делать? Без него куда приятнее...)]</p>");
				
			} else if (!target.isPlayer()) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name] [npc.verb(direct)] [npc.her] паутинка на [npc2.namePos] [npc2.cock], но, почуяв, что [npc.sheIs] собирается сделать, [npc2.name] [npc2.verb(slap)] её прочь и [npc2.verb(sneer)], [npc2.speech(Ха! Ну уж нет!)]");
				}
				return UtilText.parse(equipper, target,
						"<p>[npc.Name] [npc.verb(pull)] презерватив и [npc.verb(try)], чтобы отдать его [npc2.name], но [npc2.she] лишь смеется ей в [npc.her] лицо, прежде чем схватить маленький фольгированный пакетик и разорвать его пополам. Насмехаясь над попыткой [npc.namePos] заставить [npc2.herHim] надеть резину, [npc2.she] [npc2.verb(sneer)], [npc2.speech(Ха! Ну уж нет!)]</p>");
			}
		}
		return null;
	}
	
	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer() || penetrating!=SexAreaPenetration.PENIS || penetrated!=SexAreaOrifice.VAGINA || !penetratingCharacter.equals(this) || this.isSlave()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().hasHymen()) {
			sb.append("<p>Когда доминантная [npc.race] вонзает [npc.her] [npc.cock+] глубоко в твою [pc.pussy+], перед глазами взрываются искры, а с твоих губ срывается болезненный, пронзительный крик. Поскольку ты всё ещё девственница, это внезапное, яростное проникновение не похоже ни на что из того, что ты чувствовала прежде, и твой крик переходит в дрожащий стон, когда тебя захлестывает агония первого проникновения.</p>"
						+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)
								?"<p>Из-за своей запредельной мазохистки вы обнаруживаете, что ваши болезненные крики перемежаются с похотливыми стонами удовольствия. Агония между ног — это чистое блаженство, и вы сосредотачиваетесь на боли, визжа и стоная в восхитительном тумане всепоглощающего экстаза.</p>"
								:"")
						+"<p>Бросив взгляд вниз, ты видишь тонкую струйку крови, сочащуюся из твоей уже размягченной киски, и понимаешь, что твоя девственная плева полностью разорвана. Когда ты издаешь отчаянный вздох, ты слышишь, как [npc.race] хихикает над твоей реакцией.</p><p>Глядя на [npc.herHim] снизу вверх, ты видишь в глазах [npc.her] дикий, безумный взгляд. Очередная вспышка боли прошивает тебя между ног, когда [npc.she] вгоняет [npc.her] [npc.cock+] еще глубже в твою [pc.pussy+]. Наклонившись к тебе, [npc.she] втягивает тебя в небрежный, влажный поцелуй, и после последнего, яростного толчка тебе в пах ты чувствуешь, как основание [npc.her] члена вжимается в твою [pc.labia+].</p><p>Все еще удерживая [npc.her] [npc.cock] полностью вложенным в [pc.pussy], [npc.she] прерывает поцелуй и поднимает [npc.her] руку, чтобы закрыть твой рот, пока [npc.she] заглушает твои болезненные вскрики. Игриво прикусив [npc.her] губу, [npc.she] начинает дразнить тебя, явно приходя в невероятное возбуждение от мысли, что именно она лишит тебя девственности.</p><p>[npc.speechNoEffects(~Ммм!~ Разве не приятно?! Ох, интересно, ты что, берег себя для кого-то особенного?)] [npc.She] на мгновение замирает, чтобы издать насмешливый смешок, а затем продолжает: [npc.speechNoEffects(Ну, полагаю, в итоге ты всё же приберег себя для меня! Жаль, конечно, ведь для меня ты — всего лишь очередная легкая шлюха.)]</p><p>Ты издаешь протестующий стон, но [npc.race] лишь еще сильнее прижимает [npc.her] ладонь к твоему рту, пока [npc.she] продолжает, [npc.speechNoEffects(Что это? Хочешь, чтобы я сделала это снова?!)]</p><p>Издавая маниакальный смех, [npc.race] внезапно опускает [npc.her] бедра, позволяя [npc.her] [npc.cock+] полностью выскользнуть из твоей [pc.pussy+]. Снова пристроившись, [npc.she] не теряет ни секунды и яростно врывается в тебя во второй раз. Хотя это всё ещё достаточно дискомфортно, чтобы заставить тебя вскрикнуть, уткнувшись в ладонь [npc.her] руки, это совсем не похоже на ту агонию, что ты испытал при первом толчке; и когда ты чувствуешь, как [npc.her] пульсирующая плоть снова наполняет тебя, ты ловишь себя на том, что издаёшь отчаянный тихий стон.</p><p>[npc.race] выглядит в восторге от твоей реакции и наклоняется ближе, пока [npc.she] продолжает дразнить тебя, [npc.speechNoEffects(~Ооо!~ Значит, ты шлюшка, обожающая демонский член? Чувствуешь, как он входит <i>глубоко</i>, забирая твою драгоценную девственность?!)]</p><p>Слегка опустив [npc.her] бёдра, [npc.she] начинает стремительно входить и выходить из твоей [pc.pussy], убирая [npc.her] руку от твоего рта, пока ты не обнаруживаешь, что из тебя вырывается череда похотливых стонов. Спустив [npc.her] руки ниже, чтобы обхватить твою талию, [npc.she] продолжает дразнить тебя, повторяя, что ты всегда будешь помнить этот момент как время, когда ты осознала, что ты всего лишь шлюшка для огромного, толстого демонического члена. Пока боль между твоих ног сменяется тупой ноющей болью, ты ловишь себя на том, что издаёшь стон за стоном, и начинаешь беспокоиться, что [npc.race] может быть права...</p>");
			
		} else {
			sb.append(
					"<p>Когда доминантная [npc.race] вонзает [npc.her] [npc.cock+] глубоко в твою [pc.pussy+], ты не можешь сдержать отчаянный, пронзительный вопль, вырывающийся из твоего рта. Поскольку ты всё ещё девственница, это внезапное, резкое проникновение не похоже ни на что из того, что ты чувствовала раньше, и твой вопль переходит в дрожащий крик, когда ты оказываешься захлестнута этим новым ощущением.</p><p>Так как твоя девственная плева уже была разорвана ранее, проникновение не вызывает боли, но по твоей удивленной реакции [npc.name] все же понимает, что [npc.sheHas] только что лишила тебя девственности. С диким, безумным блеском в [npc.her] глазах, [npc.name] яростно врывается тебе в пах, вонзая [npc.her] [npc.cock+] так глубоко, как только [npc.she] может в твою [pc.pussy+]. Удерживая [npc.her] пульсирующий демонический член, засаженный как можно глубже в твою [pc.pussy+], [npc.name] игриво лижет [npc.her] [npc.lips], прежде чем начать дразнить тебя.</p><p>[npc.speechNoEffects(~Ммм!~ Ощущать, как огромный демонический член втиснут в твою киску — это ведь так приятно, а?! ~Ох!~ Интересно, ты берегла свой первый раз для кого-то особенного?] [npc.she] спрашивает, прежде чем замолчать и издать насмешливый смешок. Явно приходя в невероятное возбуждение от мысли, что именно она заберет твою девственность, [npc.she] нетерпеливо продолжает: [npc.speechNoEffects(Похоже, в итоге ты всё-таки приберегла себя для меня! Жаль, конечно, ведь для меня ты — всего лишь очередная легкая шлюшка.)]</p><p>Ты невольно издаешь [pc.a_moan+], чувствуя, как [npc.namePos] [npc.cock+] пульсирует глубоко внутри твоей киски, отчего [npc.herHim] снова издает насмешливый смешок и спрашивает: [npc.speechNoEffects(Что такое? Хочешь, чтобы я сделала это снова?!)]</p><p>Не дожидаясь твоего ответа, [npc.race] внезапно отводит [npc.her] бедра назад, позволяя [npc.her] [npc.cock+] полностью выскользнуть из твоей [pc.pussy+]. Снова пристроившись напротив [npc.herself], [npc.she] тут же входит в тебя во второй раз, и когда ты чувствуешь, как [npc.her] горячий член снова наполняет тебя, ты ловишь себя на том, что издаешь отчаянный [pc.moan]. [npc.Name] выглядит в восторге от твоей реакции и проталкивает [npc.her] свой демонический член чуть глубже, пока [npc.she] дразнит тебя, [npc.speechNoEffects(~Ооо!~ Значит, ты грязная шлюшка, обожающая демонский член, да? Тебе нравится это чувство? ~Ммм!~ Когда мой член входит <i>глубоко</i> в твою возбужденную киску, забирая твою драгоценную девственность?!)]</p><p>Слегка отстранившись, [npc.she] начинает входить и выходить из твоей [pc.pussy+], довольно ухмыляясь, когда [npc.she] слышит твои череду похотливых [pc.moans]. Пока [npc.her] член продолжает долбить твою киску, [npc.name] не унимается, насмехаясь над тобой и повторяя, что ты всегда будешь помнить этот момент как время, когда ты осознала, что ты всего лишь шлюшка для демонического члена. Пока [npc.she] говорит это, ты ловишь себя на том, что издаёшь стон за стоном, и начинаешь беспокоиться, что [npc.race] может быть права...</p>");
		}
		
		return UtilText.parse(this, sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
						+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Сломанная девственница</b></p><p>Пока [npc.race] продолжает долбить между твоих ног, внезапное осознание случившегося обрушивается на тебя, словно удар кувалдой.</p><p style='text-align:center;'>"
						+ UtilText.parsePlayerThought("Я... я лишилась девственности?!<br/>У... <b>неё</b>?!")
					+ "</p><p>Хуже всего даже не потеря девственности, которой ты так дорожила, а тот факт, что тебе это чертовски нравится. Пока твои половые губы бесстыдно обволакивают горячий, толстый демонский член, ты ловишь себя на мысли, что начинаешь соглашаться с тем, что говорит [npc.race].</p><p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("Если я больше не девственница, значит, я шлюха...<br/>Просто шлюха для демонского члена...<br/>Она права, я всего лишь очередная легкая сучка для такой, как она...")
					+ "</p>"
					+ "<p>"
						+ "You're vaguely aware of the [npc.race]'s taunts fading away as [npc.she] starts to focus [npc.her] concentration on fucking you."
						+ " With a desperate moan, you start bucking your hips back against [npc.herHim], resigning yourself to the fact that now you're nothing more than a"
						+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>сломанная девственница</b>...</p>";
	}
	
	// Dirty talk:
	
	@Override
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		if(this.isSlave()) {
			return super.getDirtyTalkNoPenetration(target, isPlayerDom);
		}
		
		List<String> speech = new ArrayList<>();
		
		if(isPlayerDom && Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING){
			speech.add("Давай же, трахни меня уже!");
			speech.add("Ну же! Чего ты так долго?!");
			speech.add("Трахни меня уже!");
			speech.add("Давай начнем! Ну же!");
		} else if(!isPlayerDom) {
			speech.add("Я превращу тебя в настоящую шлюху для демонского члена!");
			speech.add("Тебя когда-нибудь трахал демон?");
			speech.add("До конца этого будет ты умолять меня о моей сперме!");
			speech.add("Будешь послушной маленькой сучкой!");
		} else {
			return super.getDirtyTalkNoPenetration(target, isPlayerDom);
		}
		
		String returnedLine = speech.get(Util.random.nextInt(speech.size()));
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}
}
