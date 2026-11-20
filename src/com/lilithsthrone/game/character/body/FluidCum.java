package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class FluidCum implements FluidInterface {

	protected AbstractFluidType type;
	protected FluidFlavour flavour;
	protected Set<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;

	public FluidCum(AbstractFluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new HashSet<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
	}

	public FluidCum(FluidCum cumToCopy) {
		this.type = cumToCopy.type;
		this.flavour = cumToCopy.flavour;
		this.fluidModifiers = new HashSet<>(cumToCopy.fluidModifiers);
		this.transformativeEffects = new ArrayList<>(cumToCopy.transformativeEffects);
	}
	
	public Element saveAsXML(String rootElementName, Element parentElement, Document doc) {
		Element element = doc.createElement(rootElementName);
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "type", FluidType.getIdFromFluidType(this.type));
		XMLUtil.addAttribute(doc, element, "flavour", this.flavour.toString());

		for(FluidModifier fm : this.getFluidModifiers()) {
			Element mod = doc.createElement("mod");
			mod.setTextContent(fm.toString());
			element.appendChild(mod);
		}
		
		return element;
	}

	public static FluidCum loadFromXML(String rootElementName, Element parentElement, Document doc) {
		return loadFromXML(rootElementName, parentElement, doc, null);
	}
	
	/**
	 * 
	 * @param parentElement
	 * @param doc
	 * @param baseType If you pass in a baseType, this method will ignore the saved type in parentElement.
	 */
	public static FluidCum loadFromXML(String rootElementName, Element parentElement, Document doc, AbstractFluidType baseType) {
		Element cum = (Element)parentElement.getElementsByTagName(rootElementName).item(0);

		AbstractFluidType fluidType = FluidType.CUM_HUMAN;
		
		if(baseType!=null) {
			fluidType = baseType;
			
		} else {
			try {
				fluidType = FluidType.getFluidTypeFromId(cum.getAttribute("type"));
			} catch(Exception ex) {
			}
		}
		
		FluidCum fluidCum = new FluidCum(fluidType);
		
		String flavourId = cum.getAttribute("flavour");
		if(flavourId.equalsIgnoreCase("SLIME")) {
			fluidCum.flavour = FluidFlavour.BUBBLEGUM;
		} else {
			fluidCum.flavour = FluidFlavour.valueOf(flavourId);
		}
		

		Element cumModifiers = (Element)cum.getElementsByTagName("cumModifiers").item(0);
		fluidCum.fluidModifiers.clear();
		if(cumModifiers!=null) {
			if(cumModifiers!=null) {
				Set<FluidModifier> fluidModifiers = fluidCum.fluidModifiers;
				Body.handleLoadingOfModifiers(FluidModifier.values(), null, cumModifiers, fluidModifiers);
			}
		} else {
			NodeList mods = cum.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				fluidCum.fluidModifiers.add(FluidModifier.valueOf(e.getTextContent()));
			}
		}
		
		return fluidCum;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidCum){
			if(((FluidCum)o).getType().equals(this.getType())
				&& ((FluidCum)o).getFlavour() == this.getFlavour()
				&& ((FluidCum)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidCum)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getType().hashCode();
		result = 31 * result + this.getFlavour().hashCode();
		result = 31 * result + this.getFluidModifiers().hashCode();
		result = 31 * result + this.getTransformativeEffects().hashCode();
		return result;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return this.getType().getName(false, gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return this.getType().getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return this.getType().getNamePlural(gc);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		String modifierDescriptor = "";
		if(!fluidModifiers.isEmpty()) {
			modifierDescriptor = new ArrayList<>(fluidModifiers).get(Util.random.nextInt(fluidModifiers.size())).getName();
		}
		
		return UtilText.returnStringAtRandom(
				(gc.getAttributeValue(Attribute.VIRILITY)>=20?"сильный":""),
				"горячая",
				modifierDescriptor,
				flavour.getRandomFlavourDescriptor(),
				type.getDescriptor(gc));
	}

	@Override
	public AbstractFluidType getType() {
		return type;
	}

	public void setType(AbstractFluidType type) {
		this.type = type;
	}

	public FluidFlavour getFlavour() {
		return flavour;
	}

	public String setFlavour(GameCharacter owner, FluidFlavour flavour) {
		if(!isCharacterInitialised(owner)) {
			this.flavour = flavour;
			return "";
		}
		
		if(this.flavour == flavour || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.flavour = flavour;
		
		return UtilText.parse(owner,
				"<p>Успокаивающее тепло разливается глубоко в [npc.namePos] [npc.balls+], заставляя [npc.herHim] невольно [npc.moan].<br/>[npc.NamePos] [npc.cum] "
					+ (flavour==FluidFlavour.FLAVOURLESS
						?"теперь <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>"
						:"теперь на вкус как <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>.")
				+ "</p>");
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!isCharacterInitialised(owner) && !fluidModifiers.contains(fluidModifier)) {
			fluidModifiers.add(fluidModifier);
			return "";
		}
		
		if(fluidModifiers.contains(fluidModifier) || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>Странный, пульсирующий жар укореняется глубоко внутри [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(вызывающий привыкание)]!</p>");
			case ALCOHOLIC:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC_WEAK);
				return UtilText.parse(owner,
						"<p>Странное, успокаивающее тепло укореняется глубоко внутри [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(крепко алкогольный)]!</p>");
			case ALCOHOLIC_WEAK:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC);
				return UtilText.parse(owner,
						"<p>Странное, успокаивающее тепло укореняется глубоко внутри [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(алкогольный)]!</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>Легкое, шипучее ощущение поднимается в [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(шипучий)]!</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>Серия странных пульсаций пронзает [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(психоактивный)]!</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>Успокаивающее тепло вливается в [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь пропитан [style.boldGrow(минеральным маслом)] и может растворять презервативы!</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>Медленное, ползучее тепло поднимается в [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(мускусный)]!</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>Странное, успокаивающее тепло разливается в [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(слизистый)]!</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>Густое, тошнотворное тепло разливается в [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(липкий)]!</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>Тяжелый жар медленно поднимается в [npc.namePos] [npc.balls], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.cum] теперь [style.boldGrow(вязкий)]!</p>");
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!isCharacterInitialised(owner)) {
			fluidModifiers.remove(fluidModifier);
			return "";
		}
		
		if(!fluidModifiers.contains(fluidModifier) || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>Мягкая прохлада разливается в [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не вызывает привыкания)]!</p>");
			case ALCOHOLIC:
			case ALCOHOLIC_WEAK:
				return UtilText.parse(owner,
						"<p>Мягкая прохлада разливается в [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не содержит алкоголя)]!</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>Спокойное, умиротворяющее чувство разливается в [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не шипучий)]!</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>Череда успокаивающих волн омывает [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не обладает психоактивным эффектом)]!</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>Легкая прохлада поднимается к [npc.namePos] [npc.balls], заставляя [npc.herHim] издавать мягкий вздох.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не мускусный)]!</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>Успокаивающая прохлада струится к [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не слизистый)]!</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>Мягкое тепло струится к [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не липкий)]!</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>Легкая прохлада поднимается к [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не вязкий)]!</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>Кратковременное облегчение разливается по [npc.namePos] [npc.balls], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.cum] [style.boldShrink(больше не напоминает минеральное масло)]!</p>");
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}
	
	public List<ItemEffect> getTransformativeEffects() {
		return transformativeEffects;
	}
	
	public void addTransformativeEffect(ItemEffect ie) {
		transformativeEffects.add(ie);
	}

	/**
	 * DO NOT MODIFY!
	 */
	public Set<FluidModifier> getFluidModifiers() {
		return fluidModifiers;
	}

	public void clearFluidModifiers() {
		fluidModifiers.clear();
	}

	public float getValuePerMl() {
		return 0.1f * type.getValueModifier();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidCum.class) && getType().getRace().isFeralPartsAvailable());
	}
}
