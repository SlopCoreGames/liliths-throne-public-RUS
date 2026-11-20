package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Ass implements BodyPartInterface {

	protected AbstractAssType type;
	protected int assSize;
	protected int hipSize;
	
	protected Anus anus;

	public Ass(AbstractAssType type, int assSize, int hipSize, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.assSize = assSize;
		this.hipSize = hipSize;
		
		anus = new Anus(type.getAnusType(), wetness, capacity, depth, elasticity, plasticity, virgin);
	}

	public Ass(Ass assToCopy) {
		this.type = assToCopy.type;
		this.assSize = assToCopy.assSize;
		this.hipSize = assToCopy.hipSize;
		
		this.anus = new Anus(assToCopy.anus);
	}
	
	public Anus getAnus() {
		return anus;
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
		List<String> list = new ArrayList<>();
		
		list.add(type.getDescriptor(gc));
		list.add(this.getAssSize().getDescriptor());

		if(gc.getBodyMaterial().getPartDescriptors()!=null && !gc.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(gc.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(list);
	}

	@Override
	public AbstractAssType getType() {
		return type;
	}

	public String setType(GameCharacter owner, AbstractAssType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			anus.setType(type.getAnusType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.ANUS);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] задницу [npc.a_assRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>[npc.NamePos] [npc.verb(feel)] [npc.her] [npc.ass] внезапно смягчается и становится очень чувствительной, и [npc.she] [npc.verb(let)] [npc.a_moan+], когда интенсивное ощущение спускается вниз к [npc.her] [npc.asshole]. Тяжело дыша и вздыхая, [npc.she] [npc.verb(continue)] время от времени издает непроизвольные [npc.moan], пока [npc.she] [npc.verb(feel)] [npc.her] вся задница трансформируется.<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		anus.setType(type.getAnusType());
		owner.resetAreaKnownByCharacters(CoverableArea.ANUS);

		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}
	
	public AssSize getAssSize() {
		return AssSize.getAssSizeFromInt(assSize);
	}

	/**
	 * Sets assSize attribute.<br/>
	 * Value is bound to >=0 && <=AssSize.SEVEN_GIGANTIC.getValue()
	 * 
	 * @param owner The character to change.
	 * @param assSize Value to set assSize to.
	 * @return Description of the change.
	 */
	public String setAssSize(GameCharacter owner, int assSize) {
		int oldSize = this.assSize;
		this.assSize = Math.max(0, Math.min(assSize, AssSize.SEVEN_GIGANTIC.getValue()));
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int sizeChange = this.assSize - oldSize;
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер задницы [npc.namePos] не меняется...)]</p>");
		}
		
		String sizeDescriptor = getAssSize().getDescriptor();
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>Вы чувствуете легкую потерю равновесия, так как ваша [pc.ass] внезапно кажется тяжелее, а когда вы экспериментально встряхиваете ею, то обнаруживаете, что она определенно [style.boldGrow(стала больше)].<br/>Теперь у вас [style.boldSex(\", \"" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " задница)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] кажется немного неустойчивой, так как [npc.her] [npc.ass] внезапно кажется больше, и когда [npc.she] экспериментально встряхивает ею, [npc.she] обнаруживает, что она определенно [style.boldGrow(стала больше)].<br/>Теперь у [npc.She] [style.boldSex(\", \"" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " задница)]!</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>Вы внезапно чувствуете, будто с вашей задницы сняли тяжелый груз, и, экспериментально встряхнув своей [pc.ass], обнаруживаете, что она [style.boldShrink(уменьшилась)].<br/>Теперь у вас [style.boldSex(\", \"" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " задница)]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] кажется слегка неустойчивой, так как [npc.her] [npc.ass] внезапно словно уменьшаются, а когда [npc.she] экспериментально встряхивает их, [npc.she] обнаруживает, что они определенно [style.boldShrink(уменьшились)].<br/>[npc.She] теперь имеет [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " задница)]!</p>");
			}
		}
	}

	public HipSize getHipSize() {
		return HipSize.getHipSizeFromInt(hipSize);
	}
	
	/**
	 * Sets hipSize attribute.<br/>
	 * Value is bound to >=0 && <=HipSize.SEVEN_ABSURDLY_WIDE.getValue()
	 * 
	 * @param owner The character to change.
	 * @param hipSize Value to set hipSize to.
	 * @return Description of the change.
	 */
	public String setHipSize(GameCharacter owner, int hipSize) {
		int oldSize = this.hipSize;
		this.hipSize = Math.max(0, Math.min(hipSize, HipSize.SEVEN_ABSURDLY_WIDE.getValue()));
		int sizeChange = this.hipSize - oldSize;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер бедер [npc.namePos] не меняется...)]</p>");
		}
		
		String styledSizeDescriptor = "[style.boldSex("+ getHipSize().getDescriptor() + " бедра)]";
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(inhale)] резко вздрогнула от неожиданности, когда [npc.she] [npc.verb(feel)] [npc.her] бедра начали менять форму, выпячиваясь и [style.boldGrow(становясь шире)].<br/>[npc.She] теперь [npc.has] " + styledSizeDescriptor + "!"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(inhale)] резко вздрагивает от неожиданности, когда [npc.she] [npc.verb(feel)] [npc.her] бедра прогибаются внутрь и меняют форму, становясь [style.boldShrink(уже)]<br/>[npc.She] теперь [npc.has] " + styledSizeDescriptor + "!"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Ass.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}
