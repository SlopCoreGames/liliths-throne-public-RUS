package com.lilithsthrone.game.character.body;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.?
 * @version 0.4.9.7
 * @author Innoxia
 */
public class OrificeNipples implements OrificeInterface {

	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	protected boolean crotchNipples;

	public OrificeNipples(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, boolean crotchNipples, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
		
		this.crotchNipples = crotchNipples;
	}

	public OrificeNipples(OrificeNipples orificeNipplesToCopy) {
		this.wetness = orificeNipplesToCopy.wetness;
		this.capacity = orificeNipplesToCopy.capacity;
		this.stretchedCapacity = orificeNipplesToCopy.stretchedCapacity;
		this.depth = orificeNipplesToCopy.depth;
		this.elasticity = orificeNipplesToCopy.elasticity;
		this.plasticity = orificeNipplesToCopy.plasticity;
		this.virgin = orificeNipplesToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeNipplesToCopy.orificeModifiers);
		
		this.crotchNipples = orificeNipplesToCopy.crotchNipples;
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return owner.getBreastMilkStorage().getAssociatedWetness();
	}
	
	@Override
	/**<b>DO NOT USE THIS. NIPPLE WETNESS IS DETERMINED BY BREAST LACTATION.</b>*/
	public String setWetness(GameCharacter owner, int wetness) {
		throw new IllegalAccessError("Попытка ручной установки влажности сосков!");
	}
	
	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue((int)capacity);
	}
	
	@Override
	public float getRawCapacityValue() {
		return capacity;
	}
	
	@Override
	public String setCapacity(GameCharacter owner, float capacity, boolean setStretchedValueToNewValue) {
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		float capacityChange = this.capacity - oldCapacity;
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Емкость [npc.namePos] "+nipplesString+" не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if(oldCapacity == 0) { // Getting fuckable nipples:
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(squirm)] о дискомфорте, когда [npc.her] "+nipplesString+" необычайно твердеют и становятся чувствительными. В торсе [npc.her] начинает нарастать странное давление, и [npc.she] [npc.verb(let)] глубокий вздох, пока внутри [npc.her] происходит радикальная трансформация "+breastsString+". Быстро ошеломленный растущей интенсивностью давления внутри [npc.her] "+breastsString+", вздох [npc.namePos] превращается в [npc.a_moan+], который вырывается из рта [npc.her] как [npc.her] "+nipplesString+" внезапно [style.boldGrow(раскрываются)], обнажая глубокие, готовые к траху проходы, образовавшиеся за ними.<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + capacityDescriptor + ", готовые к траху "+nipplesString+")]!"
						+ "</p>");
				
			} else { // Expanding fuckable nipples:
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] знакомое давление, нарастающее за готовыми к траху [npc.her] "+nipplesString+", прежде чем они внезапно [style.boldGrow(растянутся)] и станут шире и глубже.<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + capacityDescriptor + " "+nipplesString+")]!"
						+ "</p>");
			}
			
		} else {
			if(capacity == 0) { // Losing fuckable nipples:
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(squirm)] о дискомфорте, когда [npc.her] "+nipplesString+" становятся необычайно твердыми и чувствительными. Внутри торса [npc.her] начинает нарастать интенсивное давление, и [npc.she] [npc.verb(let)] глубокий вздох, когда [npc.her] "+nipplesString+" внезапно [style.boldShrink(сжимаются)], лишая возможности ими заниматься сексом.<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + capacityDescriptor + ", не поддающиеся сексу\", "+nipplesString+")]!"
						+ "</p>");
				
			} else { // Shrinking fuckable nipples:
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] знакомое давление, нарастающее за готовыми к траху [npc.her] "+nipplesString+", прежде чем они внезапно [style.boldShrink(сжимаются)] и становятся теснее.<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + capacityDescriptor + " "+nipplesString+")]!"
						+ "</p>");
			}
		}
	}
	
	@Override
	public float getStretchedCapacity() {
		return stretchedCapacity;
	}

	@Override
	public boolean setStretchedCapacity(float stretchedCapacity) {
		float oldStretchedCapacity = this.stretchedCapacity;
		this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		return oldStretchedCapacity != this.stretchedCapacity;
	}

	@Override
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) {
		// Calculate breast depth as simply owner.getBreastSize().getMeasurement()
		if(this.isCrotchNipples()) {
			return (int) ((owner.getBreastCrotchSize().getMeasurement()) * 0.5f * depth.getDepthPercentage());
		}
		return (int) ((owner.getBreastSize().getMeasurement()) * 0.5f * depth.getDepthPercentage());
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth) {
		if(Main.game.isElasticityAffectDepthEnabled() && OrificeElasticity.getElasticityFromInt(elasticity).isExtendingUncomfortableDepth()) {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * (float)elasticity/1.5f);
		} else {
			return getMaximumPenetrationDepthComfortable(owner, depth) * 2;
		}
	}
	
	@Override
	public OrificeDepth getDepth(GameCharacter owner) {
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return OrificeDepth.SEVEN_FATHOMLESS;
		}
		return OrificeDepth.getDepthFromInt(depth);
	}

	@Override
	public String setDepth(GameCharacter owner, int depth) {
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", глубина [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" "+nipplesString+" нельзя изменить...)]</p>\",");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Глубина [npc.namePos] "+nipplesString+" не меняется...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может сдержать удивленного вздоха, когда [npc.she] [npc.verb(feel)] тревожное давление, пульсирующее глубоко внутри [npc.her] "+breastsString+". Прежде чем вздох [npc.her] успевает превратиться в отчаянный крик, давление внезапно исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что [npc.her] "+nipplesString+" [style.boldGrow(увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\"" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " "+nipplesString+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] пугающее ощущение сжатия глубоко внутри [npc.her] "+breastsString+". Прежде чем ах [npc.her] превращается в жалобный крик, чувство внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [npc.her] "+nipplesString+" [style.boldShrink(стали менее эластичными)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " "+nipplesString+")]!"
					+ "</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Эластичность [npc.namePos] "+nipplesString+" не меняется...)]</p>");
		}
		
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] странное ощущение расслабления, пульсирующее глубоко внутри [npc.her] "+breastsString+". Так же быстро, как и началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [npc.her] "+nipplesString+" [style.boldGrow(стала более эластичной)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] странное ощущение сжатия, пульсирующее глубоко внутри [npc.her] "+breastsString+". Так же быстро, как и началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [npc.her] "+nipplesString+" [style.boldShrink(стала менее эластичной)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Пластичность [npc.namePos] "+nipplesString+" не меняется...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко внутри [npc.her] "+breastsString+". Прежде чем [npc.she] [npc.has] успеет запаниковать, чувство быстро исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что [npc.her] "+nipplesString+" [style.boldGrow(обрела некоторую пластичность)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение размягчения, пульсирующее глубоко внутри [npc.her] "+breastsString+". Прежде чем [npc.she] [npc.has] успеет запаниковать, чувство быстро исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что [npc.her] "+nipplesString+" [style.boldShrink(потеряла некоторую пластичность)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " "+nipplesString+")]!"
					+ "</p>");
		}
	}

	@Override
	public boolean isVirgin() {
		return virgin;
	}

	@Override
	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	public boolean isCrotchNipples() {
		return crotchNipples;
	}
	
	@Override
	public boolean hasOrificeModifier(OrificeModifier modifier) {
		return orificeModifiers.contains(modifier);
	}

	@Override
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		orificeModifiers.add(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}

		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>Внутри [npc.namePos] внезапно нарастает сильное давление "+breastsString+", и [npc.she] не может не издать [npc.a_moan+], когда [npc.she] чувствует, как [style.boldGrow(дополнительные мышцы)] разрастаются вглубь слизистой [npc.her] "+nipplesString+". [npc.sheIs] в шоке от того, что [npc.sheHasFull] обладает невероятным контролем над ними, что позволяет [npc.herHim] умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex(Внутри [npc.namePos] "+nipplesString+" теперь выстланы замысловатым рядом мышц!)]</p>");
				}
				break;
			case RIBBED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>Внутри [npc.namePos] внезапно нарастает сильное давление "+breastsString+", и [npc.she] не может не издать [npc.a_moan+], когда [npc.she] чувствует, как [style.boldGrow(мясистые, сверхчувствительные ребра)] прорастают в стенки [npc.her] "+nipplesString+". Слегка пошевелив [npc.her] "+breastsString+" , разряд удовольствия пронзает торс [npc.her], пока [npc.she] чувствует, как новые наросты [npc.her] трутся друг о друга, заставляя [npc.herHim] издать еще один [npc.moan+].<br/>[style.boldSex(Внутри [npc.namePos] "+nipplesString+" теперь выстланы мясистыми, вызывающими наслаждение ребрами!)]</p>");
				}
				break;
			case TENTACLED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>Внутри [npc.namePos] внезапно нарастает сильное давление "+breastsString+", и [npc.she] не может не издать [npc.a_moan+], когда [npc.she] чувствует странное покалывание глубоко внутри [npc.her] "+nipplesString+". Покалывание усиливается, и из уст [npc.her] вырывается удивленный крик, когда [npc.she] внезапно обнаруживает, что нутро [npc.her] "+nipplesString+" теперь заполнено [style.boldGrow(рядом маленьких извивающихся щупалец)], над которыми у [npc.sheHasFull] лишь ограниченный контроль.<br/>[style.boldSex(Внутри [npc.namePos] "+nipplesString+" теперь полно маленьких щупалец, которые извиваются сами по себе!)]</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] короткий вскрик, когда [npc.she] [npc.verb(feel)] покалывание, пробегающее по [npc.her] "+nipplesString+", прежде чем они внезапно раздуваются и [style.boldGrow(наливаются)].<br/>[style.boldSex([npc.NamePos] "+nipplesString+" теперь невероятно опухшие!)]</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}

	@Override
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(!hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		orificeModifiers.remove(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}

		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>Успокаивающее тепло медленно разливается по торсу [npc.namePos], и невольный [pc.moan] вырывается из между [npc.her] [npc.lips], пока [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(лишние мышцы)] впитываются обратно в плоть [npc.her] "+breastsString+".<br/>[style.boldSex(Внутренности [npc.namePos] "+nipplesString+" больше не выстланы сложной сетью мышц!)]</p>");
				}
				break;
			case RIBBED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>Успокаивающее тепло медленно разливается по торсу [npc.namePos], и невольный [pc.moan] вырывается из между [npc.her] [npc.lips], пока [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(мясистые, сверхчувствительные ребра)] впитываются обратно в плоть [npc.her] "+breastsString+".<br/>[style.boldSex(Внутренности [npc.namePos] "+nipplesString+" больше не ребристые!)]</p>");
				}
				break;
					
			case TENTACLED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>Успокаивающее тепло медленно разливается по торсу [npc.namePos], и невольный [npc.moan] вырывается из между [npc.her] [npc.lips], пока [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(маленькие извивающиеся щупальца)] впитываются обратно в плоть [npc.her] "+breastsString+".<br/>[style.boldSex(Внутренности [npc.namePos] "+nipplesString+" больше не наполнены маленькими щупальцами!)]</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] вздыхает, когда [npc.her] "+nipplesString+" [style.boldShrink(сжимаются)] и теряют свою припухлость.<br/>[style.boldSex([npc.NamePos] "+nipplesString+" больше не выглядят невероятно раздутыми!)]</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

	public void clearOrificeModifiers() {
		orificeModifiers.clear();
	}
}
