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
public class FluidMilk implements FluidInterface {

	
	protected AbstractFluidType type;
	protected FluidFlavour flavour;
	protected Set<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;
	
	protected boolean crotchMilk;

	public FluidMilk(AbstractFluidType type, boolean crotchMilk) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new HashSet<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
		
		this.crotchMilk = crotchMilk;
	}

	public FluidMilk(FluidMilk milkToCopy) {
		this.type = milkToCopy.type;
		this.flavour = milkToCopy.flavour;
		this.fluidModifiers = new HashSet<>(milkToCopy.fluidModifiers);
		this.transformativeEffects = new ArrayList<>(milkToCopy.transformativeEffects);
		this.crotchMilk = milkToCopy.crotchMilk;
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
	
	public static FluidMilk loadFromXML(String rootElementName, Element parentElement, Document doc) {
		return loadFromXML(rootElementName, parentElement, doc, null, false);
	}
	
	/**
	 * 
	 * @param parentElement
	 * @param doc
	 * @param baseType If you pass in a baseType, this method will ignore the saved type in parentElement.
	 */
	public static FluidMilk loadFromXML(String rootElementName, Element parentElement, Document doc, AbstractFluidType baseType, boolean crotchMilk) {
		
		Element milk = (Element)parentElement.getElementsByTagName(rootElementName).item(0);
		
		AbstractFluidType fluidType = FluidType.MILK_HUMAN;
		
		if(baseType!=null) {
			fluidType = baseType;
			
		} else {
			try {
				fluidType = FluidType.getFluidTypeFromId(milk.getAttribute("type"));
			} catch(Exception ex) {
			}
		}
		
		FluidMilk fluidMilk = new FluidMilk(fluidType, crotchMilk);
		
		String flavourId = milk.getAttribute("flavour");
		if(flavourId.equalsIgnoreCase("SLIME")) {
			fluidMilk.flavour = FluidFlavour.BUBBLEGUM;
		} else {
			fluidMilk.flavour = FluidFlavour.valueOf(flavourId);
		}
		
		Element milkModifiersElement = (Element)milk.getElementsByTagName("milkModifiers").item(0);
		fluidMilk.fluidModifiers.clear();
		if(milkModifiersElement!=null) {
			if(milkModifiersElement!=null) {
				Collection<FluidModifier> milkFluidModifiers = fluidMilk.fluidModifiers;
				Body.handleLoadingOfModifiers(FluidModifier.values(), null, milkModifiersElement, milkFluidModifiers);
			}
		} else {
			NodeList mods = milk.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				fluidMilk.fluidModifiers.add(FluidModifier.valueOf(e.getTextContent()));
			}
		}
		
		return fluidMilk;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidMilk){
			if(((FluidMilk)o).getType().equals(this.getType())
				&& ((FluidMilk)o).getFlavour() == this.getFlavour()
				&& ((FluidMilk)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidMilk)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
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
		if(!isCharacterInitialised(owner)) {
			this.flavour = flavour;
			return "";
		}
		
		if(this.flavour == flavour) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}

		this.flavour = flavour;
		
		if(this.isCrotchMilk()) {
			return UtilText.parse(owner,
					"<p>Успокаивающее тепло разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать довольный тихий вздох.<br/>[npc.NamePos] [npc.crotchMilk] "
						+ (flavour==FluidFlavour.FLAVOURLESS
							?"теперь <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>"
							:"теперь на вкус как <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>.")
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>Успокаивающее тепло разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать довольный тихий вздох.<br/>[npc.NamePos] [npc.milk] "
						+ (flavour==FluidFlavour.FLAVOURLESS
							?"теперь <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>"
							:"теперь на вкус как <b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>.")
					+ "</p>");
		}
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(fluidModifiers.contains(fluidModifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		if(owner == null) {
			return "";
		}
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Странный, пульсирующий жар разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(вызывает привыкание)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Странный, пульсирующий жар разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(вызывает привыкание)]!</p>");
				}
			case ALCOHOLIC:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC_WEAK);
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Странное, успокаивающее тепло разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(крепкий алкоголь)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Странное, успокаивающее тепло разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(крепкий алкоголь)]!</p>");
				}
			case ALCOHOLIC_WEAK:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC);
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Странное, успокаивающее тепло разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(алкоголь)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Странное, успокаивающее тепло разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(алкогольный)]!</p>");
				}
			case BUBBLING:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Легкое, пузырящееся ощущение разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(пузырящийся)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Легкое, пузырящееся ощущение разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(пузырящийся)]!</p>");
				}
			case HALLUCINOGENIC:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Серия странных пульсаций проносится сквозь [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(психоактивный)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Серия странных пульсаций проносится сквозь [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(психоактивный)]!</p>");
				}
			case MINERAL_OIL:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Успокаивающее тепло вливается в [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь пропитан [style.boldGrow(минеральным маслом)] и может плавить презервативы!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Успокаивающее тепло разливается внутри [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь пропитана [style.boldGrow(минеральным маслом)] и способна растворять презервативы!</p>");
				}
			case MUSKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Медленное, ползучее тепло поднимается к [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(мускусная)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Медленное, ползучее тепло поднимается к [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(мускусная)]!</p>");
				}
			case SLIMY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Странное, успокаивающее тепло поднимается к [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(скользкая)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Странное, успокаивающее тепло поднимается к [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(скользкая)]!</p>");
				}
			case STICKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Густое, тошнотворное тепло поднимается к [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(липкая)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Густое, тошнотворное тепло поднимается к [npc.namePos] [npc.breasts], заставляя [npc.herHim] издать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(липкая)]!</p>");
				}
			case VISCOUS:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Тяжелый жар медленно поднимается вверх по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] издавать [npc.a_moan+].<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldGrow(вязкий)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Тяжелый жар медленно поднимается вверх по [npc.namePos] [npc.breasts], заставляя [npc.herHim] издавать [npc.a_moan+].<br/>[npc.NamePos] [npc.milk] теперь [style.boldGrow(вязкий)]!</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!fluidModifiers.contains(fluidModifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		if(owner == null) {
			return "";
		}
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Легкая прохлада разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldShrink(больше не вызывает привыкания)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Легкая прохлада разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] теперь [style.boldShrink(больше не вызывает привыкания)]!</p>");
				}
			case ALCOHOLIC:
			case ALCOHOLIC_WEAK:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Легкая прохлада разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldShrink(больше не содержит алкоголя)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Легкая прохлада разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] теперь [style.boldShrink(больше не содержит алкоголя)]!</p>");
				}
			case BUBBLING:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Спокойное, умиротворяющее чувство разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] теперь [style.boldShrink(больше не шипучий)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Спокойное, умиротворяющее чувство разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не пузырится)]!</p>");
				}
			case HALLUCINOGENIC:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Волны успокоения накрывают [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] [style.boldShrink(больше не обладает психоактивным эффектом)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Волны успокоения накрывают [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не обладает психоактивным эффектом)]!</p>");
				}
			case MUSKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Легкая прохлада поднимается в [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] мягко вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] [style.boldShrink(больше не мускусный)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Легкая прохлада поднимается в [npc.namePos] [npc.breasts], заставляя [npc.herHim] мягко вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не мускусный)]!</p>");
				}
			case SLIMY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Успокаивающая прохлада течет вверх по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] [style.boldShrink(больше не слизистый)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Успокаивающая прохлада течет вверх по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не слизистый)]!</p>");
				}
			case STICKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Мягкое тепло разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] [style.boldShrink(больше не липкий)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Мягкое тепло разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не липкий)]!</p>");
				}
			case VISCOUS:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Легкая прохлада разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] [style.boldShrink(больше не вязкий)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Легкая прохлада разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не вязкий)]!</p>");
				}
			case MINERAL_OIL:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>Кратковременное облегчение разливается по [npc.namePos] [npc.crotchBoobs], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.crotchMilk] [style.boldShrink(больше не минеральное масло)]!</p>");
				} else {
					return UtilText.parse(owner,
							"<p>Кратковременное облегчение разливается по [npc.namePos] [npc.breasts], заставляя [npc.herHim] тихо вздохнуть.<br/>[npc.NamePos] [npc.milk] [style.boldShrink(больше не минеральное масло)]!</p>");
				}
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
		return 0.01f * type.getValueModifier();
	}

	public boolean isCrotchMilk() {
		return crotchMilk;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidMilk.class) && getType().getRace().isFeralPartsAvailable());
	}
}
