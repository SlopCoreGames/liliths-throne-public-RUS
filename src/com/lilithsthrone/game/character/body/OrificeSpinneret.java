package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.4
 * @version 0.4.9.7
 * @author Innoxia
 */
public class OrificeSpinneret implements OrificeInterface {

	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeSpinneret() {
		this.wetness = Wetness.ONE_SLIGHTLY_MOIST.getValue();
		this.capacity = Capacity.ONE_EXTREMELY_TIGHT.getMedianValue();
		this.stretchedCapacity = this.capacity;
		this.depth = OrificeDepth.TWO_AVERAGE.getValue();
		this.elasticity = OrificeElasticity.FOUR_LIMBER.getValue();
		this.plasticity = OrificePlasticity.THREE_RESILIENT.getValue();
		this.virgin = true;

		this.orificeModifiers = new HashSet<>();
	}

	public OrificeSpinneret(OrificeSpinneret orificeSpinneretToCopy) {
		this.wetness = orificeSpinneretToCopy.wetness;
		this.capacity = orificeSpinneretToCopy.capacity;
		this.stretchedCapacity = orificeSpinneretToCopy.stretchedCapacity;
		this.depth = orificeSpinneretToCopy.depth;
		this.elasticity = orificeSpinneretToCopy.elasticity;
		this.plasticity = orificeSpinneretToCopy.plasticity;
		this.virgin = orificeSpinneretToCopy.virgin;

		this.orificeModifiers = new HashSet<>(orificeSpinneretToCopy.orificeModifiers);
	}
	
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : this.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = this.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.VAGINA)) {
				wetnessDescriptor = "wet";
			}
		}
		descriptorList.add(wetnessDescriptor);
		
		descriptorList.add(Capacity.getCapacityFromValue(this.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
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
		if(owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] паутинный аппарат, так что ничего не происходит...)]</p>");
		}
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", влажность [npc.namePos] "+Wetness.SEVEN_DROOLING.getDescriptor()+" паутинный аппарат нельзя изменить...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if(wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Влажность [npc.namePos] паутинного аппарата не меняется...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.NamePos] [npc.eyes] расширяются, пока [npc.she] [npc.verb(feel)] влага выступает вокруг [npc.her] паутинного аппарата, и [npc.she] [npc.verb(let)] наружу [npc.a_moan+], так как [npc.she] [npc.verb(realise)], что он смазывает сам себя и [style.boldGrow(становится всё мокрее)].<br/>Трансформация быстро проходит, оставляя [npc.herHim] с [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " паутинница)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(shift)] с дискомфортом и [npc.verb(let)] разочарованный стон, когда [npc.she] [npc.verb(feel)] [npc.her] паутинник [style.boldShrink(становится суше)].<br/>Трансформация быстро проходит, оставляя [npc.herHim] с [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " паутинница)]!</p>");
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
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] паутинный аппарат, так что ничего не происходит...)]</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Емкость [npc.namePos] паутинного аппарата не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>Непроизвольный, шокированный вздох срывается с [npc.namePos] рта, когда [npc.she] [npc.verb(feel)] [npc.her] паутинник неконтролируемо расширяется и растягивается. Спустя мгновение ощущение проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутреннее пространство [npc.her] паутинника [style.boldGrow(увеличилось)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " паутинница)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] вскрикивает, когда [npc.she] [npc.verb(feel)] [npc.her] паутинная сеть неконтролируемо сжимается и пульсирует. Спустя мгновение ощущение проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутренний [style.boldShrink(объем паутинной сети уменьшился)] [npc.her].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " паутинница)]!</p>");
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
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) { // Same formula as vagina depth
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
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] паутинный аппарат, так что ничего не происходит...)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", глубина [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" паутинный аппарат нельзя изменить...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Глубина [npc.namePos] паутинника не меняется...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно издает удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное давление, пульсирующее от [npc.her] паутины глубоко в [npc.her] нижней части живота. Прежде чем вздох [npc.her] успевает превратиться в мучительный крик, давление внезапно исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что [npc.her] паутина [style.boldGrow(стала глубже)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " паутинница)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно издает удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное ощущение сжатия, проходящее вниз по [npc.her] нижней части живота в [npc.her] паутинку. Прежде чем [npc.her] вздох успевает превратиться в страдальческий крик, чувство внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [npc.her] паутинка [style.boldShrink(стала мельче)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " паутинница)]!</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] паутинный аппарат, так что ничего не происходит...)]</p>");
		}
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Эластичность паутинного аппарата [npc.namePos] не меняется...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange slackening sensation pulsating deep within [npc.her] spinneret. Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] spinneret's [style.boldGrow(elasticity has increased)].<br/>[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " паутинница)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] странное сжимающее ощущение, пульсирующее глубоко внутри [npc.her] паутинного аппарата. Так же быстро, как оно началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldShrink(эластичность [npc.her] паутинного аппарата уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " паутинница)]!</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] паутинный аппарат, так что ничего не происходит...)]</p>");
		}
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Пластичность [npc.namePos] паутинника не меняется...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] вырывается удивленный вздох, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко внутри [npc.her] паутины. Прежде чем [npc.she] [npc.has] успеет запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldGrow(пластичность [npc.her] паутины увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " паутинница)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздохнула от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение размягчения, пульсирующее глубоко внутри [npc.her] паутины. Прежде чем [npc.she] успела [npc.has] запаниковать, чувство быстро исчезло, оставив [npc.herHim] инстинктивное понимание того, что [style.boldShrink(пластичность [npc.her] паутины уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " паутинница)]!</p>");
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
		if(!owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит, так как [npc.name] [npc.verb(lack)] паутинный аппарат...)]</p>");
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда сильное давление нарастает глубоко внутри веретена [npc.her], но прежде чем у [npc.sheHasFull] появляется возможность среагировать, ощущение быстро рассеивается. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутренние стенки веретена [npc.her] теперь выстланы [style.boldGrow(дополнительными мышцами)], которые [npc.she] может использовать, чтобы умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex(веретено [npc.NamePos] теперь выстлано сложной системой мышц!)]</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда сильное давление нарастает глубоко внутри веретена [npc.her], но прежде чем у [npc.sheHasFull] появляется возможность среагировать, ощущение быстро рассеивается. Слегка пошевелившись, [npc.she] [npc.verb(discover)], что внутри веретена [npc.her] теперь выстлано [style.boldGrow(мясистыми, высокочувствительными ребрами)], которые дарят невероятное удовольствие при стимуляции.<br/>[style.boldSex(веретено [npc.NamePos] теперь выстлано мясистыми, вызывающими наслаждение ребрами!)]</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда внутри [npc.her] паутинки нарастает сильное давление, но прежде чем у [npc.sheHasFull] появляется возможность среагировать, ощущение быстро исчезает. С экспериментальным сжатием [npc.she] [npc.verb(discover)], что стенки [npc.her] паутинки теперь усеяны [style.boldGrow(маленькими извивающимися щупальцами)], над которыми у [npc.sheHasFull] ограниченный контроль.<br/>[style.boldSex(Внутри [npc.namePos] паутинки теперь копошатся маленькие щупальца, которые живут своей жизнью, извиваясь и лаская любой входящий объект!)]</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] паутинке, прежде чем она внезапно [style.boldGrow(раздувается)].<br/>[style.boldSex([npc.NamePos] паутинка теперь чрезвычайно опухшая и раздутая!)]</p>");
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
						"<p>[npc.Name] не может не вскрикнуть от неожиданности, когда внутри [npc.her] паутинки нарастает сильное давление, но прежде чем у [npc.sheHasFull] появляется возможность среагировать, ощущение быстро исчезает. С экспериментальным сжатием [npc.she] [npc.verb(discover)], что внутренность [npc.her] паутинки [style.boldShrink(потеряла свои дополнительные мышцы)].<br/>[style.boldSex([npc.NamePos] паутинка больше не усеяна сложной сетью мышц!)]</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] веретена нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Слегка пошевелившись, [npc.she] [npc.verb(discover)], что [style.boldShrink(мясистые, сверхчувствительные ребра)], когда-то выстилавшие стенки [npc.her] веретена, [style.boldShrink(исчезли)].<br/>[style.boldSex(Веретено [npc.NamePos] больше не покрыто мясистыми, вызывающими наслаждение ребрами!)]</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] веретена нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Проверив это пробным сжатием, [npc.she] [npc.verb(discover)], что [style.boldShrink(извивающиеся щупальца)] внутри [npc.her] веретена [style.boldShrink(полностью исчезли)].<br/>[style.boldSex(Внутри веретена [npc.namePos] больше нет щупалец!)]</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает, когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] веретена, после чего оно внезапно [style.boldShrink(сжимается)], принимая более обычный размер.<br/>[style.boldSex(Веретено [npc.NamePos] больше не раздуто сверх меры!)]</p>");
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
