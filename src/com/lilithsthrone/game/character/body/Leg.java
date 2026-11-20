package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Leg implements BodyPartInterface {

	public static final float LENGTH_PERCENTAGE_MIN_FERAL = 0.05f;
	public static final float LENGTH_PERCENTAGE_MIN = 2f;
	public static final float LENGTH_PERCENTAGE_MAX = 10f;
	
	protected AbstractLegType type;
	protected FootStructure footStructure;
	protected LegConfiguration legConfiguration;
	protected int girth;
	protected float lengthAsPercentageOfHeight;

	public Leg(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.legConfiguration = legConfiguration;
		this.footStructure = type.getDefaultFootStructure(legConfiguration);
		this.girth = PenetrationGirth.THREE_AVERAGE.getValue();
		this.lengthAsPercentageOfHeight = LegConfiguration.getDefaultSerpentTailLengthMultiplier();
	}

	public Leg(Leg legToCopy) {
		this.type = legToCopy.type;
		this.legConfiguration = legToCopy.legConfiguration;
		this.footStructure = legToCopy.footStructure;
		this.girth = legToCopy.girth;
		this.lengthAsPercentageOfHeight = legToCopy.lengthAsPercentageOfHeight;
	}
	
	@Override
	public AbstractLegType getType() {
		return type;
	}

	public FootStructure getFootStructure() {
		return footStructure;
	}

	//TODO check
	/**
	 * @return A description of the change. Returns an empty String if !isCharacterInitialised(owner) or if footStructure==FootStructure.TENTACLED
	 */
	public String setFootStructure(GameCharacter owner, FootStructure footStructure) {
		if(!isCharacterInitialised(owner) || footStructure==FootStructure.TENTACLED) {
			this.footStructure = footStructure;
			return "";
		}
		
		if(this.getFootStructure()==footStructure) {
			return UtilText.parse(owner,
					"<p>[style.colourDisabled(Ничего не происходит, так как [npc.namePos] и [npc.feet] уже [npc.footStructure]...)]</p>");
		}
		
		if(!type.getFootType().getPermittedFootStructures(owner.getLegConfiguration()).contains(footStructure)) {
			return UtilText.parse(owner,
					"<p>[style.colourDisabled(Ничего не происходит, так как [npc.namePos] [npc.feet] не могут превратиться в "+footStructure.getName()+"...)]"
					+ "</p>");
		}

		
		this.footStructure = footStructure;
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>[npc.Name] [npc.verb(let)] от шокированного вздоха, когда [npc.she] внезапно [npc.verb(feel)] сильный жар, пронзающий [npc.her] [npc.legs] и [npc.feet]. Пошатываясь и покачиваясь, [npc.she] почти [npc.verb(lose)] [npc.her] равновесие, пока структура [npc.her] ног трансформируется и меняется.</br>");
		
		switch(footStructure) {
			case NONE:
				sb.append("Спустя мгновение, у [npc.sheIs] [style.boldTfGeneric(не осталось [npc.feet])]!");
				break;
			case DIGITIGRADE:
				sb.append("Спустя мгновение, [npc.sheIs] [style.boldTfGeneric(остались с пальцеходящими [npc.feet])], а это значит, что [npc.she] теперь [npc.verb(walk)] на [npc.her] пальцах, при этом [npc.her] пятка навсегда приподнята.");
				break;
			case PLANTIGRADE:
				sb.append("Спустя мгновение, [npc.sheIs] [style.boldTfGeneric(остались с пятипалыми [npc.feet])], а это значит, что [npc.she] теперь [npc.verb(walk)], при этом [npc.her] стопы плотно стоят на земле.");
				break;
			case UNGULIGRADE:
				sb.append("Спустя мгновение, [npc.sheIs] [style.boldTfGeneric(остались с копытообразными [npc.feet])], а это значит, что [npc.she] теперь [npc.verb(walk)] на [npc.her] [npc.toes], при этом остальная часть [npc.her] стопы навсегда приподнята.");
				break;
			case ARACHNOID:
				sb.append("Спустя мгновение, [npc.sheIs] [style.boldTfGeneric(остались с паукообразными [npc.feet])], а это значит, что [npc.she] теперь [npc.verb(walk)] на концах [npc.her] сегментированных паукообразных ног.");
				break;
			case TENTACLED:
				break;
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "</p>"
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
		
	}

	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	public void setLegConfigurationForced(AbstractLegType type, LegConfiguration legConfiguration) {
		this.type = type;
		this.footStructure = type.getDefaultFootStructure(legConfiguration);
		this.legConfiguration = legConfiguration;
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
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));

		return Util.randomItemFrom(descriptorList);
	}

	public String setType(GameCharacter owner, AbstractLegType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			this.footStructure = type.getDefaultFootStructure(this.getLegConfiguration());
			if(owner!=null) {
				if(Main.game.isStarted() && !type.isLegConfigurationAvailable(this.getLegConfiguration())) {
					this.getType().applyLegConfigurationTransformation(owner, RacialBody.valueOfRace(type.getRace()).getLegConfiguration(), true, true);
				}
				owner.postTransformationCalculation();
				
			} else {
				if(Main.game.isStarted() && !type.isLegConfigurationAvailable(this.getLegConfiguration())) {
					System.err.println("Leg.setType() was passed a null owner, and the type '"+type.getTransformName()+"' requires conversion of LegConfiguration from "
							+ "'"+this.getLegConfiguration().getName()+"' to '"+RacialBody.valueOfRace(type.getRace()).getLegConfiguration().getName()+"'.");
					this.setLegConfigurationForced(type, RacialBody.valueOfRace(type.getRace()).getLegConfiguration());
				}
			}
			return "";
		}
		
		if(type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.legs] [npc.a_legRace], так что ничего не происходит...)]</p>");
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>[npc.NamePos] [npc.legs] начинают дрожать и слабеть, а [npc.she] почти [npc.verb(lose)] [npc.her] равновесие по мере того, как начинается трансформация. ");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		this.footStructure = type.getDefaultFootStructure(this.getLegConfiguration());
		
		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		// Set tentacle variables:
		owner.setTentacleType(type.getTentacleType());
		owner.setTentacleCount(type.getTentacleCount());
		
		if(!type.isLegsReplacedByTentacles() && type.getDefaultFootStructure(this.getLegConfiguration())!=FootStructure.NONE) {
			sb.append(
					"<p>Трансформация оставила структуру [npc.her] [npc.feet] в виде [style.boldTFGeneric("+this.footStructure.getName()+")]! "+this.footStructure.getDescription()
					+ "</p>");
		}
		
		if(!type.isLegConfigurationAvailable(this.getLegConfiguration())) {
			sb.append(this.getType().applyLegConfigurationTransformation(owner, type.getAllowedLegConfigurations().get(0), true, true));
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Leg.class) && getType().getRace().isFeralPartsAvailable());
	}
	
	// These methods are just for if the Leg's LegConfiguration is of type TAIL_LONG:
	
	// Length:
	
	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}

	/**
	 * Sets the tails' length as a percentage of the owner's height. Value is bound to >=2f && <=10f
	 */
	public String setLengthAsPercentageOfHeight(GameCharacter owner, float lengthAsPercentageOfHeight) {
		if(!isCharacterInitialised(owner)) {
			// Allow for setting down to feral minimum, as this could be loading of a feral part:
			this.lengthAsPercentageOfHeight = Math.max(LENGTH_PERCENTAGE_MIN_FERAL, Math.min(lengthAsPercentageOfHeight, LENGTH_PERCENTAGE_MAX));
			return "";
		}
		
		float lengthChange = 0;
		
		float percentageMinimum = owner.isFeral()?LENGTH_PERCENTAGE_MIN_FERAL:LENGTH_PERCENTAGE_MIN;
		if (lengthAsPercentageOfHeight <= percentageMinimum) {
			if (this.lengthAsPercentageOfHeight != percentageMinimum) {
				lengthChange = percentageMinimum - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = percentageMinimum;
			}
		} else if (lengthAsPercentageOfHeight >= LENGTH_PERCENTAGE_MAX) {
			if (this.lengthAsPercentageOfHeight != LENGTH_PERCENTAGE_MAX) {
				lengthChange = LENGTH_PERCENTAGE_MAX - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = LENGTH_PERCENTAGE_MAX;
			}
			
		} else {
			if (this.lengthAsPercentageOfHeight != lengthAsPercentageOfHeight) {
				lengthChange = lengthAsPercentageOfHeight - this.lengthAsPercentageOfHeight;
				this.lengthAsPercentageOfHeight = lengthAsPercentageOfHeight;
			}
		}
		
		if(owner.getLegConfiguration()!=LegConfiguration.TAIL_LONG) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Так как у [npc.name] [npc.do] нет "+LegConfiguration.TAIL_LONG+" нижней части тела, кажется, ничего не происходит...)]</p>");
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Длина [npc.namePos] [npc.tail] не меняется...)]</p>");
		}
		
		String heightPercentageDescription = " (длина составляет "+((int)(owner.getLegTailLengthAsPercentageOfHeight()*100))+"% oф при росте [npc.namePos])";
		if(lengthChange>0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] [npc.a_moan+], когда [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее внутри [npc.her] [npc.tail+]. Без всякого предупреждения о том, что будет дальше, [npc.her] [npc.tail] внезапно [style.boldGrow(удлиняется)].<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] [npc.a_moan+], когда [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее внутри [npc.her] [npc.tail+]. Без всякого предупреждения о том, что будет дальше, [npc.her] [npc.tail] внезапно [style.boldShrink(укорачивается)].<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
					+ "</p>");
		}
	}
	
	public int getLength(GameCharacter owner) {
		return (int) (owner.getHeightValue() * getLengthAsPercentageOfHeight());
	}

	public int getLength(Body body) {
		return (int) (body.getHeightValue() * getLengthAsPercentageOfHeight());
	}
	
	
	// Diameter:
	
	public float getBaseDiameter(GameCharacter owner) {
		return getDiameter(owner, 0);
	}

	/**
	 * Uses basic equations to calculate a rough diameter dropoff, based on this tail type's TAPERING tag.
	 * 
	 * @param owner The character who this tail is attached to.
	 * @param atLength The length at which the diameter is to be found, measured from the base.
	 * @return The diameter.
	 */
	public float getDiameter(GameCharacter owner, float atLength) {
//		System.out.println(owner.getName());
		// Waist-to-height ratio is 0.5 for non-ferals, 0.25 for ferals
		// It is a measurement of waist circumference divided by height
		float waistToHeightRatio = owner.isFeral()?0.25f:0.5f;
		float waistCircumference = owner.getHeightValue()*waistToHeightRatio;
		float waistDiameter = (float) (waistCircumference / Math.PI);
//		System.out.println("WD: "+waistDiameter);
		
		// Modify base diameter (at waist) by hip size:
		float baseDiameter = waistDiameter * (1f + owner.getLegTailGirth().getDiameterPercentageModifier());
//		System.out.println("BD: "+baseDiameter);
		float lengthPercentage = Math.min(1, atLength / this.getLength(owner));
		
		 // Linear diameter tapering:
		// y = 1 - (0.95x)
		// At maximum length, diameter is 5% base diameter
		float diameter = (1 - (0.95f * lengthPercentage)) * baseDiameter;

//		System.out.println("FD: "+diameter);
		
		return diameter;
	}

}