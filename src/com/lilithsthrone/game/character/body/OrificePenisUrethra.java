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
public class OrificePenisUrethra implements OrificeInterface {

	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificePenisUrethra(int wetness, int capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificePenisUrethra(OrificePenisUrethra orificePenisUrethraToCopy) {
		this.wetness = orificePenisUrethraToCopy.wetness;
		this.capacity = orificePenisUrethraToCopy.capacity;
		this.stretchedCapacity = orificePenisUrethraToCopy.stretchedCapacity;
		this.depth = orificePenisUrethraToCopy.depth;
		this.elasticity = orificePenisUrethraToCopy.elasticity;
		this.plasticity = orificePenisUrethraToCopy.plasticity;
		this.virgin = orificePenisUrethraToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificePenisUrethraToCopy.orificeModifiers);
	}
	
	@Override
	/** <b>This method is not used, as a penis's wetness is determined by its cum production.</b> */
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	/** <b>This method is not used, as a penis's wetness is determined by its cum production.</b> */
	public String setWetness(GameCharacter owner, int wetness) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] пенис, так что ничего не происходит...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(выработка предэякулята [npc.NamePos] не меняется...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.NamePos] [npc.eyes] расширяются, когда [npc.she] [npc.verb(feel)] [npc.her] [npc.cock+] внезапно твердеет, и [npc.she] [npc.verb(let)] [npc.a_moan+], пока скользкая струйка предэякулята сочится с кончика, а его выработка [style.boldGrow(увеличивается)].<br/>[npc.She] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(shift)] с дискомфортом и [npc.verb(let)] издает разочарованный стон, когда [npc.she] [npc.verb(feel)] [npc.her] выделение преэякулята [style.boldShrink(уменьшается)].<br/>[npc.She] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " уретра)]!</p>");
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
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] пенис, так что ничего не происходит...)]</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Емкость [npc.namePos] уретры не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>Непроизвольный, шокированный вздох вырывается из [npc.namePos] рта, когда [npc.she] [npc.verb(feel)] [npc.her] уретра пениса расслабляется и растягивается. Спустя мгновение тревожное чувство проходит, и [npc.she] очень быстро [npc.verb(realise)] понимает, что внутренняя [style.boldGrow(емкость уретры [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] вырывается крик, когда [npc.she] [npc.verb(feel)] уретра пениса [npc.her] неконтролируемо сжимается и пульсирует. Спустя мгновение тревожное чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутренность уретры [npc.her] [style.boldShrink(сократилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " уретра)]!</p>");
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
		return (int) (owner.getPenisRawSizeValue() * 1.5f * depth.getDepthPercentage());
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
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] пенис, так что ничего не происходит...)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", глубина [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" уретру нельзя изменить...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Глубина уретры [npc.namePos] не меняется...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может сдержать удивленного вздоха, когда [npc.she] [npc.verb(feel)] ощущает тревожное давление, пульсирующее от основания [npc.her] члена вверх к [npc.her] паху. Прежде чем вздох [npc.her] успевает превратиться в страдальческий крик, давление внезапно исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что уретра пениса [npc.her] [style.boldGrow(стала глубже)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное ощущение сжатия, спускающееся от [npc.her] нижней части живота к основанию [npc.her] члена. Прежде чем [npc.her] вздох успевает превратиться в мучительный крик, чувство внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что уретра пениса [npc.her] [style.boldShrink(стала мельче)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " уретра)]!</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] пенис, так что ничего не происходит...)]</p>");
		}
		
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		int elasticityChange = this.elasticity - oldElasticity;
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Эластичность уретры [npc.namePos] не меняется...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может сдержать удивленного вздоха, когда [npc.she] [npc.verb(feel)] странное ощущение расслабления, пульсирующее глубоко внутри [npc.her] члена. Так же быстро, как оно началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldGrow(эластичность [npc.her] уретры увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] странное пульсирующее сжатие глубоко внутри [npc.her] члена. Это ощущение проходит так же быстро, как и началось, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldShrink(эластичность [npc.her] уретры уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " уретра)]!</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] пенис, так что ничего не происходит...)]</p>");
		}
		
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		int plasticityChange = this.plasticity - oldPlasticity;
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Пластичность уретры [npc.namePos] не меняется...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздохнула от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко внутри [npc.her] члена. Прежде чем [npc.she] [npc.has] успела запаниковать, чувство быстро исчезло, оставив [npc.herHim] инстинктивное понимание того, что [style.boldGrow(пластичность уретры [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] изумленный вздох, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение размягчения, пульсирующее глубоко внутри [npc.her] члена. Прежде чем [npc.she] [npc.has] успеет запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldShrink(пластичность)] уретры [npc.her] уменьшилась.<br/>[npc.Name] теперь [npc.has] [style.boldSex(, " + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " уретра)]!</p>");
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
		if(owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] пенис, так что ничего не происходит...)]</p>");
		}
		
		orificeModifiers.add(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не выдохнуть [npc.a_moan+], когда сильное давление нарастает у основания [npc.her] [npc.cock], но прежде чем [npc.sheHasFull] успеет среагировать, чувство быстро рассеивается. С экспериментальным сжатием [npc.she] [npc.verb(discover)], что внутреннюю часть уретры [npc.her] теперь выстилают [style.boldGrow(мышцы)], которые [npc.she] может использовать, чтобы умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex(Уретра [npc.NamePos] члена теперь выстлана сложной сетью мышц!)]</p>");
				}
				break;
			case RIBBED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не издать [npc.a_moan+], когда у основания [npc.her] [npc.cock] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Слегка пошевелившись, [npc.she] [npc.verb(discover)], что стенки внутри [npc.her] уретры теперь покрыты [style.boldGrow(мясистыми, сверхчувствительными складками)], которые дарят невероятное наслаждение при стимуляции.<br/>[style.boldSex(Внутри пенисной уретры [npc.NamePos] теперь появились мясистые, вызывающие удовольствие складки!)]</p>");
				}
				break;
			case TENTACLED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не издать [npc.a_moan+], когда у основания [npc.her] [npc.cock] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. С экспериментальным сжатием [npc.she] [npc.verb(discover)], что внутреннюю часть уретры [npc.her] теперь выстилают [style.boldGrow(маленькие извивающиеся щупальца)], над которыми у [npc.sheHasFull] лишь ограниченный контроль.<br/>[style.boldSex(Внутри уретры [npc.namePos] теперь копошатся маленькие щупальца, которые живут своей жизнью, извиваясь и лаская любой входящий объект!)]</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по всей длине [npc.her] [npc.cock], прежде чем край [npc.her] уретры внезапно [style.boldGrow(раздувается)], превращаясь в кольцо, похожее на пончик.<br/>[style.boldSex(Край пенисной уретры [npc.namePos] теперь опухший и раздутый!)]</p>");
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
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не вскрикнуть от неожиданности, когда внутри [npc.her] [npc.cock] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутренняя часть [npc.her] уретры [style.boldShrink(потеряла свои дополнительные мышцы)].<br/>[style.boldSex(Пенисная уретра [npc.NamePos] больше не выстлана сложной серией мышц!)]</p>");
				}
				break;
			case RIBBED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не вскрикнуть от неожиданности, когда внутри [npc.her] [npc.cock] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Слегка пошевелив [npc.her] [npc.pussy], [npc.she] [npc.verb(discover)], что [style.boldShrink(мясистые, высокочувствительные складки)], которые когда-то выстилали [npc.her] уретру, [style.boldShrink(исчезли)].<br/>[style.boldSex(Пенисная уретра [npc.NamePos] больше не выстлана мясистыми, вызывающими удовольствие складками!)]</p>");
				}
				break;
			case TENTACLED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может сдержать испуганного вскрика, когда внутри [npc.her] [npc.cock] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Попробовав сжать мышцы, [npc.she] [npc.verb(discover)], что [style.boldShrink(извивающиеся щупальца)] внутри уретры [npc.her] [style.boldShrink(полностью исчезли)].<br/>[style.boldSex(Внутри уретры [npc.namePos] больше нет щупалец!)]</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может сдержать испуганного вскрика, когда [npc.she] ощущает покалывание, пробегающее по всей длине [npc.her] [npc.cock], после чего [style.boldShrink(припухший край)] уретры [npc.her] [style.boldShrink(сдувается)], принимая более нормальный вид.<br/>[style.boldSex(Край уретры [npc.namePos] больше не опухший и раздутый!)]</p>");
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
