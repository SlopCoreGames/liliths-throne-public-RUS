package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Ear implements BodyPartInterface {
	
	protected AbstractEarType type;
	protected boolean pierced;

	public Ear(AbstractEarType type) {
		this.type = type;
		pierced = false;
	}

	public Ear(Ear earToCopy) {
		this.type = earToCopy.type;
		this.pierced = earToCopy.pierced;
	}
	
	@Override
	public AbstractEarType getType() {
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
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}
	
	public String setType(GameCharacter owner, AbstractEarType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type.equals(getType())) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.ears] [npc.a_earRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		if(owner.isArmMovementHindered()) {
			sb.append("[npc.NamePos] [npc.ears] начинают непроизвольно дергаться и чесаться, и [npc.she] [npc.verb(let)] вздох от того, что [npc.she] [npc.verb(feel)] начинают трансформироваться. ");
		} else {
			sb.append("[npc.NamePos] [npc.ears] начинают непроизвольно дергаться и чесаться, и, издав вздох, [npc.she] [npc.verb(reach)] тянется ими потереть, пока [npc.she] [npc.verb(feel)] начинают трансформироваться. ");
		}
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;

		sb.append(type.getTransformationDescription(owner));

		sb.append("</p>");
		
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPierced() {
		return pierced;
	}
	
	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			if(owner.isPlayer()) {
				return "<p>Ваши [pc.ears] теперь [style.boldGrow(проколоты)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.ears] теперь [style.boldGrow(проколоты)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_EAR);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>Ваши [pc.ears] [style.boldShrink(больше не проколоты)]!</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.ears] [style.boldShrink(больше не проколоты)]!</p>"
						+piercingUnequip);
			}
		}
		
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Ear.class) && getType().getRace().isFeralPartsAvailable());
	}

}
