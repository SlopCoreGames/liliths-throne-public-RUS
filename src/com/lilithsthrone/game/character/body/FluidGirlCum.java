package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
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
public class FluidGirlCum implements FluidInterface {
	
	protected AbstractFluidType type;
	protected FluidFlavour flavour;
	protected Set<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;

	public FluidGirlCum(AbstractFluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new HashSet<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
	}

	public FluidGirlCum(FluidGirlCum fluidGirlCumToCopy) {
		this.type = fluidGirlCumToCopy.type;
		this.flavour = fluidGirlCumToCopy.flavour;
		this.fluidModifiers = new HashSet<>(fluidGirlCumToCopy.fluidModifiers);
		this.transformativeEffects = new ArrayList<>(fluidGirlCumToCopy.transformativeEffects);
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

	public static FluidGirlCum loadFromXML(String rootElementName, Element parentElement, Document doc) {
		return loadFromXML(rootElementName, parentElement, doc, null);
	}
	
	/**
	 * 
	 * @param parentElement
	 * @param doc
	 * @param baseType If you pass in a baseType, this method will ignore the saved type in parentElement.
	 */
	public static FluidGirlCum loadFromXML(String rootElementName, Element parentElement, Document doc, AbstractFluidType baseType) {
		
		Element girlcum = (Element)parentElement.getElementsByTagName(rootElementName).item(0);

		AbstractFluidType fluidType = FluidType.GIRL_CUM_HUMAN;
		
		if(baseType!=null) {
			fluidType = baseType;
			
		} else {
			try {
				fluidType = FluidType.getFluidTypeFromId(girlcum.getAttribute("type"));
			} catch(Exception ex) {
			}
		}
		
		FluidGirlCum fluidGirlcum = new FluidGirlCum(fluidType);
		
		String flavourId = girlcum.getAttribute("flavour");
		if(flavourId.equalsIgnoreCase("SLIME")) {
			fluidGirlcum.flavour = FluidFlavour.BUBBLEGUM;
		} else {
			fluidGirlcum.flavour = FluidFlavour.valueOf(flavourId);
		}
		

		Element girlcumModifiersElement = (Element)girlcum.getElementsByTagName("girlcumModifiers").item(0);
		fluidGirlcum.fluidModifiers.clear();
		if(girlcumModifiersElement!=null) {
			if(girlcumModifiersElement!=null) {
				Collection<FluidModifier> girlcumFluidModifiers = fluidGirlcum.fluidModifiers;
				Body.handleLoadingOfModifiers(FluidModifier.values(), null, girlcumModifiersElement, girlcumFluidModifiers);
			}
			
		} else {
			NodeList mods = girlcum.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				fluidGirlcum.fluidModifiers.add(FluidModifier.valueOf(e.getTextContent()));
			}
		}
		
		return fluidGirlcum;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidGirlCum){
			if(((FluidGirlCum)o).getType().equals(this.getType())
				&& ((FluidGirlCum)o).getFlavour() == this.getFlavour()
				&& ((FluidGirlCum)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidGirlCum)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
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
	public String getDescriptor(GameCharacter gc) {
		String modifierDescriptor = "";
		if(!fluidModifiers.isEmpty()) {
			modifierDescriptor = new ArrayList<>(fluidModifiers).get(Util.random.nextInt(fluidModifiers.size())).getName();
		}
		
		return UtilText.returnStringAtRandom(
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
		if (owner == null) {
			this.flavour = flavour;
			return "";
		}
		if(this.flavour == flavour || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}

		this.flavour = flavour;
		
		return UtilText.parse(owner,
				"<p>Успокаивающее тепло разливается по [npc.namePos] [npc.pussy], заставляя [npc.herHim] невольно издать [npc.moan].<br/>[npc.NamePos] [pc.girlcum] "
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
		if(fluidModifiers.contains(fluidModifier) || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);

		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>Странный, пульсирующий жар укореняется глубоко внутри [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(вызывает привыкание)]!</p>");
			case ALCOHOLIC:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC_WEAK);
				return UtilText.parse(owner,
						"<p>Странное, успокаивающее тепло укореняется глубоко внутри [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(сильно алкогольный)]!</p>");
			case ALCOHOLIC_WEAK:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC);
				return UtilText.parse(owner,
						"<p>Странное, успокаивающее тепло укореняется глубоко внутри [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(алкогольный)]!</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>Легкое, пузырящееся ощущение поднимается в [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(пузырящийся)]!</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>Серия странных пульсаций пронзает [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(психоактивный)]!</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>Успокаивающее тепло вливается в [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь пропитан [style.boldGrow(минеральным маслом)] и может растворять презервативы!</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>Медленное, ползучее тепло поднимается вверх к [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(мускусный)]!</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>Странное, успокаивающее тепло разливается по [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(слизистый)]!</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>Густое, тошнотворное тепло поднимается к [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(липкий)]!</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>Тяжелый жар медленно поднимается к [npc.namePos] [npc.pussy], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.girlcum] теперь [style.boldGrow(вязкий)]!</p>");
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!fluidModifiers.contains(fluidModifier) || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>Легкая прохлада разливается по [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не вызывает привыкания)]!</p>");
			case ALCOHOLIC:
			case ALCOHOLIC_WEAK:
				return UtilText.parse(owner,
						"<p>Легкая прохлада разливается по [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не алкогольный)]!</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>Спокойное, умиротворяющее чувство разливается по [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не шипучий)]!</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>Череда успокаивающих волн омывает [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не вызывает галлюцинаций)]!</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>Легкая прохлада поднимается к [npc.namePos] [npc.pussy], заставляя [npc.herHim] нежно вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не мускусный)]!</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>Успокаивающая прохлада струится в [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не склизкий)]!</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>Мягкое тепло разливается по [npc.namePos] [npc.pussy], заставляя [npc.herHim] нежно вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не липкий)]!</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>Легкая прохлада поднимается к [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не вязкий)]!</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>Кратковременное облегчение струится в [npc.namePos] [npc.pussy], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.girlcum] [style.boldShrink(больше не минеральное масло)]!</p>");
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
		return 1f * type.getValueModifier();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidGirlCum.class) && getType().getRace().isFeralPartsAvailable());
	}
}
