package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Vagina implements BodyPartInterface {
	
	protected AbstractVaginaType type;
	protected Clitoris clitoris;
	protected int labiaSize;
	protected boolean pierced;
	protected boolean eggLayer;
	
	protected OrificeVagina orificeVagina;
	protected FluidGirlCum girlcum;
	protected OrificeVaginaUrethra orificeUrethra;

	public Vagina(AbstractVaginaType type, int labiaSize, int clitSize, int clitGirth, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.labiaSize = labiaSize;
		this.clitoris = new Clitoris(clitSize, clitGirth);
		this.pierced = false;
		this.eggLayer = type.isEggLayer();
		orificeVagina = new OrificeVagina(wetness, capacity, depth, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
		orificeUrethra = new OrificeVaginaUrethra(Wetness.TWO_MOIST.getValue(), 0, 2, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		girlcum = new FluidGirlCum(type.getFluidType());
	}

	public Vagina(Vagina vaginaToCopy) {
		this.type = vaginaToCopy.type;
		this.labiaSize = vaginaToCopy.labiaSize;
		this.clitoris = new Clitoris(vaginaToCopy.clitoris);
		this.pierced = vaginaToCopy.pierced;
		this.eggLayer = vaginaToCopy.eggLayer;
		this.orificeVagina = new OrificeVagina(vaginaToCopy.orificeVagina);
		this.orificeUrethra = new OrificeVaginaUrethra(vaginaToCopy.orificeUrethra);
		this.girlcum = new FluidGirlCum(vaginaToCopy.girlcum);
	}
	
	public OrificeVagina getOrificeVagina() {
		return orificeVagina;
	}

	public void setGirlcum(FluidGirlCum girlcum) {
		this.girlcum = girlcum;
	}

	public FluidGirlCum getGirlcum() {
		return girlcum;
	}
	
	public OrificeVaginaUrethra getOrificeUrethra() {
		return orificeUrethra;
	}

	@Override
	public AbstractVaginaType getType() {
		return type;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}
	
	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = orificeVagina.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.VAGINA)) {
				wetnessDescriptor = "wet";
			}
		}
		descriptorList.add(wetnessDescriptor);
		if(owner.getPubicHair().getValue()>=BodyHair.FOUR_NATURAL.getValue() && Main.game.isPubicHairEnabled()) {
			descriptorList.add("hairy");
		}
		
		// It doesn't make much sense to be referencing taste in a generic context
//		if(this.getGirlcum().getFlavour()!=FluidFlavour.GIRL_CUM && this.getGirlcum().getFlavour()!=FluidFlavour.FLAVOURLESS) {
//			descriptorList.add(this.getGirlcum().getFlavour().getName()+"-flavoured");
//		}
		
		if(owner.isVaginaFeral()) {
			descriptorList.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"feral",
					"bestial",
					"animalistic")));
		} else {
			descriptorList.add(type.getDescriptor(owner));
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptorList.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeVagina.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		descriptorList.removeIf(d->d==null || d.isEmpty());
		if(descriptorList.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(descriptorList);
	}
	
	public String getUrethraDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeUrethra.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		descriptorList.add(type.getDescriptor(owner));
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeUrethra.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
	}
	
	public String setType(GameCharacter owner, AbstractVaginaType type) {
		return setType(owner, type, false);
	}
	
	public String setType(GameCharacter owner, AbstractVaginaType type, boolean overridePregnancyPrevention) {
		if(this.type==VaginaType.NONE) {
			this.orificeVagina.setStretchedCapacity(this.orificeVagina.getRawCapacityValue());
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
			this.orificeVagina.hymen=true;
		}
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {// This always overrides pregnancy prevention, as the only times where this is true are for utility methods:
			this.type = type;
			this.girlcum.setType(type.getFluidType());
			this.eggLayer = type.isEggLayer();
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == owner.getVaginaType()) {
			if(type == VaginaType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.verb(lack)] вагину, так что ничего не происходит...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.a_vaginaRace] киска, так что ничего не происходит...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// Cannot transform if pregnant:
		if (!overridePregnancyPrevention
				&& type==VaginaType.NONE
				&& (owner.isPregnant() || owner.hasStatusEffect(StatusEffect.PREGNANT_0) || owner.getIncubationLitter(SexAreaOrifice.VAGINA)!=null)) {
			sb.append(UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] похотливый стон, когда [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy+] начинает становиться горячей и чувствительной, и когда волна покалывающего возбуждения проходит сквозь [npc.her] низ живота, [npc.her] стон превращается в отчаянный вздох. К огромному [npc.her] удивлению, чувство исчезает почти так же быстро, как и появилось, и со вздохом, [npc.she] [npc.verb(realise)] это "
						+ (owner.getIncubationLitter(SexAreaOrifice.VAGINA)!=null
							?"<b>яйца, инкубирующиеся в [npc.her] утробе, помешали удалению [npc.her] вагины</b>!"
							:(owner.hasStatusEffect(StatusEffect.PREGNANT_0)
								?"<b>возможность беременности помешала удалению [npc.her] вагины</b>!"
								:"<b>[npc.her] текущая беременность помешала удалению [npc.her] вагины</b>!"))
						+ "<br/>[npc.NamePos] киска остается [style.boldTfSex(неизменной)].</p>"));
			
			return sb.toString()
					+ "<p>"
						+owner.postTransformationCalculation()
					+"</p>";
		}
		
		// If have no vagina:
		if(owner.getVaginaType() == VaginaType.NONE) {
			sb.append(UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(blush)], когда [npc.she] [npc.verb(feel)] странный жар разливается по [npc.her] паху, и не может не издать низкий [npc.moan], когда [npc.skin] "
						+ (!owner.hasPenisIgnoreDildo()
							? "посреди [npc.her] паха"
							: (!owner.isTaur()
									?"под [npc.her] членом"
									:"над и позади [npc.her] члена"))
						+ " начинает прогибаться внутрь, образуя неглубокую борозду. Не подавая признаков остановки, это странное новое углубление продолжает углубляться, посылая очередной всплеск жара в нижнюю часть живота [npc.namePos]. Когда эта вторая волна жара утихает, резкое, пронзительное ощущение прошивает пах [npc.her], и хотя это не больно, [npc.she] не может не вскрикнуть от шока, когда эта борозда внезапно расщепляется и раскрывается, обнажая глубокий канал. Отверстие этого нового отверстия быстро принимает характерную форму влагалища, дополненную [npc.clitSize] клитором и [npc.labiaSize] половыми губами.</p><p>Как раз когда [npc.she] [npc.verb(start)] подумать, что трансформация завершена, последняя интенсивная волна жара пульсирует в нижней части живота [npc.namePos], и [npc.she] не может не издать отчаянный [npc.moan], когда [npc.she] инстинктивно [npc.verb(realise)], что внутри [npc.herHim] выросла полностью функционирующая женская репродуктивная система. Когда трансформация [npc.her] наконец подходит к концу, [npc.namePos] внезапно [npc.verb(become)] осознает, что новая киска [npc.her] уже насквозь промокла от возбуждения, заставляя [npc.herHim] издать последний чувственный [npc.moan].<br/>"));
			
			if(owner.isVaginaVirgin()) {
				sb.append(UtilText.parse(owner,
						"[npc.Name] теперь [npc.has] [style.colourExcellent(девственная)] [style.boldTfSex(вагина)], в комплекте с [style.colourExcellent(неповрежденной девственной плевой)]!"));
			} else {
				sb.append(UtilText.parse(owner,
						"[npc.Name] теперь [npc.has] [style.boldTfSex(вагина)], и хотя [npc.she] уже нельзя считать [npc.herself] девственницей, [npc.she] по крайней мере [npc.has] [style.colourExcellent(неповрежденную девственную плеву)]!"));
			}
					
			sb.append("</p>");
				
			if(owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				if(owner.isVaginaVirgin()) {
					sb.append(UtilText.parse(owner,
							"<p style='text-align:center;'>[style.boldExcellent(Чистая девственница)]<br/><i>Теперь, когда у [npc.name] [npc.has] вагина, [npc.she] наконец-то может считать [npc.herself] истинно чистой девственницей! С легкой восторженной улыбкой на [npc.her] [npc.face], [npc.she] не может не чувствовать невероятного восторга, ведь пока [npc.she] [npc.verb(retain)] [npc.her] девственность, [npc.she] будет воплощением идеального, гордого и добродетельного существа!</i><br/>[npc.NameIsFull] теперь [style.boldExcellent(Чистая девственница)]!</p>"));
				} else {
					sb.append(UtilText.parse(owner,
							"<p style='text-align:center;'>[style.boldGood(Чистая «Дева»)]<br/><i>Осознание того, что [npc.herself] снова обладает нетронутой киской, накрывает [npc.name] огромной волной эйфории. Убедив [npc.herself], что [npc.her] неповрежденная девственная плева означает, что [npc.sheIs] технически снова девственница, [npc.she] [npc.verb(feel)] в [npc.her] [npc.eyes] начинают подступать слезы радости. Однако, несмотря на это чувство ликования, где-то на задворках разума [npc.her] зудит навязчивый голос, напоминающий [npc.herHim], что [npc.she] больше никогда не будет «настоящей» девственницей.<br/>Встряхнув [npc.her] головой, чтобы прогнать эту незваную мысль, [npc.name] [npc.verb(focus)] на том, что пока [npc.her] плева остается целой, [npc.she] может хотя бы притворяться, будто [npc.sheHas] ее еще никто не трахал. Даже не желая допускать мысли, что [npc.her] киску когда-нибудь придется снова «разминать», [npc.she] [npc.verb(hold)] [npc.herself] [npc.her] высоко подняв голову и [npc.verb(tell)], что [npc.sheIs] снова является идеальным воплощением гордого и добродетельного существа!</i><br/>[npc.NameIsFull] теперь [style.boldGood(Чистая «Дева»)]!</p>"));
				}
			}
			
			this.type = VaginaType.HUMAN;
			owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
			
			if(type==VaginaType.HUMAN) {
				this.girlcum.setType(type.getFluidType());
				return sb.toString()
						+ "<p>"
							+owner.postTransformationCalculation()
						+"</p>";
			} else {
				return sb.toString()
						+ owner.setVaginaType(type);
			}
			
		} else {
			sb.append(UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странный жар пульсирует внутри [npc.her] киски, и [npc.she] не может не краснеть и тяжело дышать, пока [npc.her] киска начинает трансформироваться.<br/>"));
		}

		sb.append(this.type.applyAdditionalTransformationEffects(owner, false));
		this.type = type;
		this.girlcum.setType(type.getFluidType());
		this.eggLayer = type.isEggLayer();
		owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
		sb.append(this.type.getTransformationDescription(owner));
		sb.append(this.type.applyAdditionalTransformationEffects(owner, true));
		
		sb.append("</p>");

		if(this.type != VaginaType.NONE) {
			sb.append("<p style='text-align:center;'>");
				if(this.eggLayer) {
					sb.append(UtilText.parse(owner,"<i>Вместо того чтобы рожать живых детенышей, [npc.name] теперь [style.colourEgg([npc.verb(lay)] откладывает яйца)]!</i>"));
				} else {
					sb.append(UtilText.parse(owner,"<i>[npc.Name] теперь [style.colourSex([npc.verb(give)] рождает потомство)]!</i>"));
				}
			sb.append("</p>");
		}
		
		orificeVagina.getOrificeModifiers().clear();
		for(OrificeModifier om : type.getDefaultRacialOrificeModifiers()) {
			orificeVagina.addOrificeModifier(owner, om);
		}
		
		sb.append(UtilText.parse(owner,"<p>Все старые модификаторы, которые могла иметь киска [npc.her], [style.boldShrink(исчезли в процессе трансформации)]!"));
		
		if(orificeVagina.getOrificeModifiers().isEmpty()) {
			sb.append("</p>");
		} else {
			sb.append(UtilText.parse(owner,"<br/>"
					+ "Instead, [npc.her] new pussy is:"));
			
			for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
				sb.append("<br/>[style.boldGrow("+Util.capitaliseSentence(om.getName())+")]");
			}
			sb.append("</p>");
		}
		
		return sb.toString()
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	
	public LabiaSize getLabiaSize() {
		return LabiaSize.getLabiaSizeFromInt(labiaSize);
	}
	
	public int getRawLabiaSizeValue() {
		return labiaSize;
	}
	
	public String setLabiaSize(GameCharacter owner, int labiaSize) {
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		int oldSize = this.labiaSize;
		this.labiaSize = Math.max(0, Math.min(labiaSize, LabiaSize.FOUR_MASSIVE.getValue()));
		int sizeChange = this.labiaSize - oldSize;

		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Размер ваших половых губ не меняется...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер губ [npc.namePos] не меняется...)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>[pc.A_moan+] вырывается из между ваших [pc.lips+], и вы чувствуете, как теплое покалывание пробегает вверх по [pc.pussy]. Суетливо ерзая на месте, ваш возглас сменяется удивленным вздохом, когда вы чувствуете, как ваши половые губы набухают и [style.boldGrow(растут)].<br/>Теперь у вас [style.boldSex([pc.labiaSize] половые губы)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.A_moan+] вырывается из между [npc.name] [pc.lips+], когда [npc.she] чувствует, как теплое покалывание пробегает вверх по [npc.her] [npc.pussy]. Суетливо ерзая на месте, возглас [npc.her] сменяется удивленным вздохом, когда [npc.she] чувствует, как [npc.her] половые губы набухают и [style.boldGrow(растут)].<br/>Теперь у [npc.Name] [style.boldSex([npc.labiaSize] половые губы)]!</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>[pc.A_moan+] вырывается из между твоих [pc.lips+], и ты чувствуешь, как прохладное покалывание разливается вверх по [pc.pussy]. Ты начинаешь ерзать на месте, и твой возглас переходит в удивленный вздох, когда ты чувствуешь, как твои половые губы сжимаются и [style.boldShrink(уменьшаются)].<br/>Теперь у тебя [style.boldSex([pc.labiaSize] малые половые губы)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.A_moan+] вырывается из между [npc.name] [pc.lips+], когда [npc.she] ощущает, как прохладное покалывание разливается вверх по [npc.her] [npc.pussy]. Тщетно извиваясь на месте, [npc.her] восклицание превращается в удивленный вздох, когда [npc.she] чувствует, как [npc.her] половые губы сжимаются и [style.boldShrink(уменьшаются)].<br/>[npc.Name] теперь обладает [style.boldSex([npc.labiaSize] половыми губами)]!</p>");
			}
		}
	}

	public boolean isPierced() {
		return pierced;
	}

	public String setPierced(GameCharacter owner, boolean pierced) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.pierced = pierced;
			return "";
		}
		
		if(this.pierced == pierced || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos] [npc.pussy] теперь [style.boldGrow(проколот)]!</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_VAGINA);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>[npc.NamePos] [npc.pussy] [style.boldShrink(больше не проколот)]!</p>"
					+piercingUnequip);
		}
	}

	public boolean isEggLayer() {
		return eggLayer;
	}

	public String setEggLayer(GameCharacter owner, boolean eggLayer) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.eggLayer = eggLayer;
			return "";
		}
		
		if(this.eggLayer == eggLayer || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		if(owner.isPregnant()) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] изумленный вздох, когда неприятное покалывание внезапно разливается по нижней части [npc.her] живота. Однако почти сразу после появления это тревожное чувство исчезает, и [npc.name] [npc.verb(realise)], что [npc.her] текущая беременность мешает [npc.her] матке трансформироваться!</p>");
		}
		
		this.eggLayer = eggLayer;
		
		if(eggLayer) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] испускает потрясенный вздох, когда неприятное покалывание внезапно разливается по нижней части живота [npc.her]. Это чувство быстро сменяется сильной судорогой, отчего судорожный вздох [npc.namePos] превращается в мучительный стон.<br/>К счастью, эта дискомфортная трансформация быстро завершается, оставляя [npc.name] тяжело дышащей и инстинктивно понимающей, что [style.boldEgg([npc.she] теперь будет откладывать яйца вместо рождения живых детенышей)]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] издает потрясенный вздох, когда неприятное покалывание внезапно разливается по нижней части живота [npc.her]. Это чувство быстро сменяется интенсивной судорогой, отчего вздох [npc.namePos] превращается в мучительный стон.<br/>К счастью, эта дискомфортная трансформация быстро завершается, оставляя [npc.name] тяжело дышащей и инстинктивно понимающей, что [style.boldSex([npc.she] теперь будет рожать живых детенышей вместо того, чтобы откладывать яйца)]!</p>");
		}
	
	}

	public Clitoris getClitoris() {
		return clitoris;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Vagina.class) && getType().getRace().isFeralPartsAvailable());
	}
}
