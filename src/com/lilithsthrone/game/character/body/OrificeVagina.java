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
public class OrificeVagina implements OrificeInterface {

	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	protected boolean hymen;
	protected boolean squirter;

	public OrificeVagina(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;

		this.orificeModifiers = new HashSet<>(orificeModifiers);
		
		hymen = virgin;
		squirter = wetness > Wetness.THREE_WET.getValue();
	}

	public OrificeVagina(OrificeVagina orificeVaginaToCopy) {
		this.wetness = orificeVaginaToCopy.wetness;
		this.capacity = orificeVaginaToCopy.capacity;
		this.stretchedCapacity = orificeVaginaToCopy.stretchedCapacity;
		this.depth = orificeVaginaToCopy.depth;
		this.elasticity = orificeVaginaToCopy.elasticity;
		this.plasticity = orificeVaginaToCopy.plasticity;
		this.virgin = orificeVaginaToCopy.virgin;

		this.orificeModifiers = new HashSet<>(orificeVaginaToCopy.orificeModifiers);
		
		this.hymen = orificeVaginaToCopy.hymen;
		this.squirter = orificeVaginaToCopy.squirter;
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return Wetness.SEVEN_DROOLING;
		}
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		if(owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] вагина, так что ничего не происходит...)]</p>");
		}
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", влажность [npc.namePos] "+Wetness.SEVEN_DROOLING.getDescriptor()+" [npc.pussy] нельзя изменить...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if(wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Влажность [npc.namePos] [npc.pussy] не меняется...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.NamePos] [npc.eyes] расширяются, пока [npc.she] [npc.verb(feel)] влага выступает вокруг [npc.her] [npc.pussy], а [npc.she] [npc.verb(let)] наружу [npc.a_moan+], так как [npc.she] [npc.verb(realise)], что она смазывает сама себя и [style.boldGrow(становится всё мокрее)].<br/>Трансформация быстро проходит, оставляя [npc.herHim] с [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " киской)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(shift)] с дискомфортом и [npc.verb(let)] издает разочарованный стон, когда [npc.she] [npc.verb(feel)] [npc.her] [npc.pussy] [style.boldShrink(становится суше)].<br/>Трансформация быстро проходит, оставляя [npc.herHim] с [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " киской)]!</p>");
		}
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
		if (owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] вагина, так что ничего не происходит...)]</p>");
		}
		
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		float capacityChange = this.capacity - oldCapacity;
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Вместимость [npc.namePos] [npc.pussy] не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>Непроизвольный, шокированный вздох срывается с губ [npc.namePos], когда [npc.she] [npc.verb(feel)] [npc.her] киска неконтролируемо расширяется и растягивается. Спустя мгновение ощущение проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутреннее пространство [npc.her] киски [style.boldGrow(увеличилось)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " киской)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] [npc.her] cunt uncontrollably tighten and clench. Within moments, the feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] pussy's internal [style.boldShrink(capacity has decreased)].<br/>[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " киской)]!</p>");
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
	public OrificeDepth getMinimumDepthForSizeComfortable(GameCharacter owner, int insertionSize) {
		OrificeDepth depth = OrificeDepth.ONE_SHALLOW;
		while((int) (owner.getHeightValue() * 0.1f * depth.getDepthPercentage())<insertionSize) {
			if(depth == OrificeDepth.SEVEN_FATHOMLESS) {
				return depth;
			}
			depth = OrificeDepth.getDepthFromInt(depth.getValue()+1);
		}
		return depth;
	}

	@Override
	public OrificeDepth getMinimumDepthForSizeUncomfortable(GameCharacter owner, int insertionSize) {
		OrificeDepth depth = OrificeDepth.ONE_SHALLOW;
		while((int) ((owner.getHeightValue() * 0.1f * depth.getDepthPercentage())*1.5f)<insertionSize) {
			if(depth == OrificeDepth.SEVEN_FATHOMLESS) {
				return depth;
			}
			depth = OrificeDepth.getDepthFromInt(depth.getValue()+1);
		}
		return depth;
	}
	
	@Override
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) { // 0.08 might be a little more realistic, but give it a little extra so that it's not annoying for people with large cocks
		return (int) (owner.getHeightValue() * 0.1f * depth.getDepthPercentage());
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth) {
		if(Main.game.isElasticityAffectDepthEnabled() && OrificeElasticity.getElasticityFromInt(elasticity).isExtendingUncomfortableDepth()) {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * (float)elasticity/1.8f);
		} else {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * 1.5f);
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
		if (owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] вагина, так что ничего не происходит...)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", глубина [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" [npc.pussy] нельзя изменить...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Глубина [npc.namePos] [npc.pussy] не меняется...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное давление, пульсирующее от [npc.her] киски глубоко в [npc.her] низ живота. Прежде чем вздох [npc.her] успевает превратиться в жалобный крик, давление внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [npc.her] щелка [style.boldGrow(стала глубже)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " киской)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное ощущение сжатия, спускающееся по [npc.her] нижней части живота прямо в [npc.her] киску. Прежде чем [npc.her] вздох успевает превратиться в жалобный крик, чувство внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [npc.her] щелка [style.boldShrink(стала мельче)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " киской)]!</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		if (owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] вагина, так что ничего не происходит...)]</p>");
		}
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Эластичность [npc.namePos] [npc.pussy] не меняется...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не ахнуть от неожиданности, когда [npc.she] [npc.verb(feel)] странное ощущение расслабления, пульсирующее глубоко внутри [npc.her] киски. Это чувство проходит так же быстро, как и появилось, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldGrow(эластичность [npc.her] щелки возросла)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " киской)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] странное пульсирующее сжатие глубоко внутри [npc.her] киски. Это ощущение проходит так же быстро, как и появилось, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldShrink(эластичность [npc.her] киски уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " киской)]!</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		if (owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] вагина, так что ничего не происходит...)]</p>");
		}
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Пластичность [npc.namePos] [npc.pussy] не меняется...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздохнула от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко внутри [npc.her] киски. Прежде чем [npc.she] успела [npc.has] запаниковать, чувство быстро исчезло, оставив [npc.herHim] инстинктивное понимание того, что пластичность [npc.her] щели [style.boldGrow(увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(, " + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " киской)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздохнула от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение размягчения, пульсирующее глубоко внутри [npc.her] киски. Прежде чем [npc.she] успела [npc.has] запаниковать, чувство быстро исчезло, оставив [npc.herHim] инстинктивное понимание того, что пластичность [npc.her] щели [style.boldShrink(уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(, " + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " киской)]!</p>");
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
		
		if(!isCharacterInitialised(owner) || owner.getBody()==null) {
			return "";
		}
		if(!owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит, так как [npc.name] [npc.verb(lack)] вагину...)]</p>");
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда глубоко внутри [npc.her] [npc.pussy] нарастает интенсивное давление, но прежде чем [npc.sheHasFull] успевает среагировать, это чувство быстро исчезает. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутренние стенки [npc.her] киски теперь выстланы [style.boldGrow(дополнительными мышцами)], которые [npc.she] может использовать, чтобы умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex([npc.NamePos] киска теперь выстлана сложной системой мышц!)]</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда глубоко внутри [npc.her] [npc.pussy] нарастает интенсивное давление, но прежде чем [npc.sheHasFull] успевает среагировать, это чувство быстро исчезает. Слегка пошевелившись, [npc.she] [npc.verb(discover)], что внутри [npc.her] киски теперь выстланы [style.boldGrow(мясистыми, сверхчувствительными ребрами)], которые дарят невероятное наслаждение при стимуляции.<br/>[style.boldSex([npc.NamePos] киска теперь выстлана мясистыми, вызывающими удовольствие ребрами!)]</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что стенки [npc.her] киски теперь покрыты [style.boldGrow(маленькими извивающимися щупальцами)], над которыми у [npc.sheHasFull] лишь ограниченный контроль.<br/>[style.boldSex(Внутри [npc.namePos] киски теперь копошатся маленькие щупальца, которые извиваются и ласкают любой входящий объект, словно живя своей собственной жизнью!)]</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] [npc.pussy], после чего губы [npc.her] [style.boldGrow(раздуваются)], превращаясь в огромные, опухшие половые губы.<br/>[style.boldSex(Губы [npc.NamePos] теперь чрезвычайно опухшие и раздутые!)]</p>");
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
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не вскрикнуть от неожиданности, когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутренность [npc.her] [npc.pussy] [style.boldShrink(потеряла свои дополнительные мышцы)].<br/>[style.boldSex(Киска [npc.NamePos] больше не покрыта сложной системой мышц!)]</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Слегка пошевелившись, [npc.she] [npc.verb(discover)], что [style.boldShrink(мясистые, сверхчувствительные складки)] на стенках киски [npc.her] [style.boldShrink(исчезли)].<br/>[style.boldSex(Киска [npc.NamePos] больше не покрыта мясистыми, вызывающими наслаждение складками!)]</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Проверив это пробным сжатием, [npc.she] [npc.verb(discover)], что [style.boldShrink(извивающиеся щупальца)] внутри киски [npc.her] [style.boldShrink(полностью исчезли)].<br/>[style.boldSex(Внутри киски [npc.namePos] больше нет щупалец!)]</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может сдержать испуганного вскрика, когда [npc.she] [npc.verb(feel)] покалывающее ощущение пробегает по [npc.her] [npc.pussy], после чего [npc.her] [style.boldShrink(сверхпухлые половые губы уменьшаются)], принимая более обычную форму.<br/>[style.boldSex([npc.NamePos] половые губы больше не сверхпухлые!)]</p>");
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

	public boolean isSquirter() {
		return squirter;
	}

	public String setSquirter(GameCharacter owner, boolean squirter) {
		if(owner == null) {
			this.squirter = squirter;
			return "";
		}
		if(this.squirter == squirter || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.squirter = squirter;
		
		if(squirter) {
			return UtilText.parse(owner,
					"<p>[npc.NameIsFull] теперь [style.boldGrow(сквиртер)]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.NameIsFull] больше не [style.boldShrink(сквиртер)]!</p>");
		}
	}

	public boolean hasHymen() {
		return hymen;
	}

	public String setHymen(GameCharacter owner, boolean hymen) {
		if(owner == null) {
			this.hymen = hymen;
			return "";
		}
		if(this.hymen == hymen || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.hymen = hymen;
		
		if(hymen) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [style.verb(feel)] пронзает резкое ощущение сжатия, уходящее глубоко в [npc.her] [npc.pussy+], и [npc.she] не может сдержать тонкий скулящий стон, когда [npc.her] [style.boldGrow(девственная плева восстанавливается)]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.NamePos] [npc.pussy+] внезапно начинает ныть, и [npc.she] не может сдержать тонкий скулящий стон, когда [npc.her] [style.boldShrink(девственная плева исчезает)]!</p>");
		}
	}

}
