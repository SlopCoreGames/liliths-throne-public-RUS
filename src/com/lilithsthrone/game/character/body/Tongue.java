package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Tongue implements BodyPartInterface {

	
	protected AbstractTongueType type;
	protected Set<TongueModifier> tongueModifiers;
	protected int tongueLength;
	protected boolean pierced;

	public Tongue(AbstractTongueType type) {
		this.type = type;
		pierced = false;
		
		tongueLength = type.getDefaultLength();
		
		this.tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	public Tongue(Tongue tongueToCopy) {
		this.type = tongueToCopy.type;
		this.pierced = tongueToCopy.pierced;
		
		this.tongueLength = tongueToCopy.tongueLength;
		
		this.tongueModifiers = new HashSet<>(tongueToCopy.tongueModifiers);
	}
	
	@Override
	public AbstractTongueType getType() {
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
	
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
        
		for(TongueModifier tm : tongueModifiers) {
			list.add(tm.getName());
		}
		list.add(type.getDescriptor(owner));

		return Util.randomItemFrom(list);
	}
	
	/**
	 * Tongue type is set when FaceType changes.
	 */
	public void setType(AbstractTongueType type) {
		this.type = type;
		resetTongueModifiers();
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
				return "<p>Ваш [pc.tongue] теперь [style.boldGrow(проколот)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.tongue] теперь [style.boldGrow(проколот)]!</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_TONGUE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>Ваш [pc.tongue] [style.boldShrink(больше не проколот)]!</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.tongue] [style.boldShrink(больше не проколот)]!</p>"
						+piercingUnequip);
			}
		}
	}
	
	public TongueLength getTongueLength() {
		return TongueLength.getTongueLengthFromInt(tongueLength);
	}
	
	public int getTongueLengthValue() {
		return tongueLength;
	}

	public String setTongueLength(GameCharacter owner, int tongueLength) {
		int oldTongueLength = this.tongueLength;
		this.tongueLength = Math.max(0, Math.min(tongueLength, TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue()));
		int sizeChange = this.tongueLength - oldTongueLength;
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Длина вашего [pc.tongue] не меняется...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Длина [npc.namePos] [npc.tongue] не меняется...)]</p>");
			}
		}
		
		if(sizeChange < 0) {
			if(owner.isPlayer()) {
				return "<p>Приятная прохлада разливается по вашему [pc.tongue], заставляя вас удивленно ахнуть, когда вы чувствуете, как он [style.boldShrink(становится короче)].<br/>Теперь у вас "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [pc.tongue])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] издает тихий вскрик, когда [npc.she] чувствует, как приятная прохлада разливается по [npc.her] [npc.tongue], прежде чем он внезапно [style.boldShrink(становится короче)].<br/>Теперь у [npc.Name] "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [npc.tongue])]!</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>Пульсирующее тепло разливается по вашему [pc.tongue], заставляя вас удивленно ахнуть, когда вы чувствуете, как он [style.boldGrow(становится длиннее)].<br/>Теперь у вас "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [pc.tongue])]!</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name] издает тихий вскрик, когда [npc.she] чувствует, как пульсирующее тепло разливается по [npc.her] [npc.tongue], прежде чем он внезапно [style.boldGrow(становится длиннее)].<br/>Теперь у [npc.Name] "+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+" [style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+" [npc.tongue])]!</p>");
			}
		}
	}
	
	public boolean hasTongueModifier(TongueModifier modifier) {
		return tongueModifiers.contains(modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(hasTongueModifier(modifier)) {
			return !isCharacterInitialised(owner) ? "" : "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		tongueModifiers.add(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
			switch(modifier) {
				case RIBBED:
					sb.append("[npc.Name] [npc.verb(feel)] сильное давление нарастает в глубине [npc.her] горла, но прежде чем [npc.she] [npc.has] успевает запаниковать, оно внезапно исчезает, оставляя [npc.herHim] ряд [style.boldGrow(твердых, мясистых ребринок)], выстилающих [npc.her] [npc.tongue].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь ребристый!)]");
					break;
				case TENTACLED:
					sb.append("[npc.Name] [npc.verb(feel)] сильное давление нарастает в глубине [npc.her] горла, и [npc.she] [npc.verb(let)] непроизвольный вздох, пока [npc.she] [npc.verb(feel)] [style.boldGrow(ряд маленьких извивающихся щупалец)] разрастаются, покрывая [npc.her] [npc.tongue].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь покрыт маленькими щупальцами, которые извиваются сами по себе!)]");
					break;
				case BIFURCATED:
					sb.append("[npc.Name] [npc.verb(feel)] сильное давление нарастает в глубине [npc.her] горла, и [npc.she] [npc.verb(let)] непроизвольный крик, пока [npc.she] [npc.verb(feel)] [style.boldGrow(конец [npc.her] [npc.tongue] раздваивается)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь раздвоен, как у змеи!)]");
					break;
				case STRONG:
					sb.append("[npc.Name] [npc.verb(feel)] постоянное давление нарастает в глубине [npc.her] горла, но прежде чем [npc.she] успевает среагировать, ощущение пронзает [npc.her] [npc.tongue], превращая его в [style.boldGrow(особенно сильный орган)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь очень сильный!)]");
					break;
				case FLAT:
					sb.append("[npc.Name] [npc.verb(feel)] в глубине горла [npc.her] нарастает ровное давление, но прежде чем [npc.she] успевает среагировать, ощущение пронзает [npc.her] [npc.tongue], превращая его в [style.boldGrow(особенно плоский)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь особенно плоский!)]");
					break;
				case WIDE:
					sb.append("[npc.Name] [npc.verb(feel)] в глубине горла [npc.her] нарастает ровное давление, но прежде чем [npc.she] успевает среагировать, ощущение пронзает [npc.her] [npc.tongue], превращая его в [style.boldGrow(особенно широкий)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь особенно широкий!)]");
					break;
				case TAPERED:
					return "<p>[npc.Name] [npc.verb(feel)] сжимает [npc.her] [npc.tongue] со всех сторон, и [npc.she] [npc.verb(let)] судорожный вздох, пока [npc.she] [npc.verb(feel)] [style.boldGrow(он сужается к кончику)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] теперь сужается!)]</p>";
			}
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString());
	}

	public String removeTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(!hasTongueModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		tongueModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				return "<p>[npc.Name] [npc.verb(feel)] мягкая прохлада поднимается к [npc.her] [npc.tongue], но она внезапно исчезает прежде, чем [npc.she] [npc.has] успевает среагировать, [style.boldShrink(сглаживая)] [npc.her] твердые, мясистые ребристости в процессе.<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не ребриста!)]</p>";
			case TENTACLED:
				return "<p>[npc.Name] [npc.verb(feel)] мягкая прохлада поднимается к [npc.her] [npc.tongue], и [npc.she] [npc.verb(let)] судорожный вздох, когда [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(маленькие извивающиеся щупальца исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не покрыты маленькими щупальцами!)]</p>";
			case BIFURCATED:
				return "<p>[npc.Name] [npc.verb(feel)] мягкая прохлада поднимается к [npc.her] [npc.tongue], и [npc.she] [npc.verb(let)] судорожный вздох, когда [npc.she] [npc.verb(feel)] [style.boldShrink(оно сливается обратно в одну точку)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не раздвоено!)]</p>";
			case FLAT:
				return "<p>[npc.Name] [npc.verb(feel)] пульсирующее давление поднимается к [npc.her] [npc.tongue], и [npc.she] [npc.verb(let)] судорожный вздох, когда [npc.she] [npc.verb(feel)] [style.boldShrink(оно утолщается)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не кажется плоским!)]</p>";
			case STRONG:
				return "<p>[npc.Name] [npc.verb(feel)] пульсирующее давление поднимается к [npc.her] [npc.tongue], и [npc.she] [npc.verb(let)] судорожный вздох, когда [npc.she] [npc.verb(feel)] [style.boldShrink(оно размягчается и теряет силу)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не такое невероятно сильное!)]</p>";
			case WIDE:
				return "<p>[npc.Name] [npc.verb(feel)] пульсирующее давление, поднимающееся к [npc.her] [npc.tongue], и [npc.she] [npc.verb(let)] судорожный вздох, когда [npc.she] [npc.verb(feel)] [style.boldShrink(оно сужается)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не кажется особо широким!)]</p>";
			case TAPERED:
				return "<p>[npc.Name] [npc.verb(feel)] пульсирующее давление, нарастающее внутри [npc.her] [npc.tongue], и [npc.she] [npc.verb(let)] судорожный вздох, когда [npc.she] [npc.verb(feel)] [style.boldShrink(оно разбухает и теряет свою заостренность)].<br/>[style.boldSex([npc.NamePos] [npc.tongue] больше не заострен!)]</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}

	public void resetTongueModifiers() {
		tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Tongue.class) && getType().getRace().isFeralPartsAvailable());
	}
}
