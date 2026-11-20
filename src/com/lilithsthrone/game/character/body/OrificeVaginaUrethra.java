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
public class OrificeVaginaUrethra implements OrificeInterface {

	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeVaginaUrethra(int wetness, int capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		this.stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificeVaginaUrethra(OrificeVaginaUrethra orificeVaginaUrethraToCopy) {
		this.wetness = orificeVaginaUrethraToCopy.wetness;
		this.capacity = orificeVaginaUrethraToCopy.capacity;
		this.stretchedCapacity = orificeVaginaUrethraToCopy.stretchedCapacity;
		this.depth = orificeVaginaUrethraToCopy.depth;
		this.elasticity = orificeVaginaUrethraToCopy.elasticity;
		this.plasticity = orificeVaginaUrethraToCopy.plasticity;
		this.virgin = orificeVaginaUrethraToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeVaginaUrethraToCopy.orificeModifiers);
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		if (owner!=null && !owner.hasVagina()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] вагина, так что ничего не происходит...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		int wetnessChange = this.wetness - oldWetness;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos] влажность уретры не меняется...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.NamePos] [npc.eyes] расширяется, когда [npc.she] [npc.verb(feel)] дрожь возбуждения пронзает [npc.her] [npc.pussy+], и [npc.she] осознает, что уретра [npc.her] стала [style.boldGrow(влажнее)].<br/>[npc.She] теперь [npc.has] [style.boldSex(\"," + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(shift)] неловко и [npc.verb(let)] издает разочарованный стон, когда уретра [npc.she] [npc.verb(feel)] [npc.her] начинает [style.boldShrink(подсыхать)].<br/>[npc.She] теперь [npc.has] [style.boldSex(\"," + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " уретра)]!</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Емкость [npc.namePos] уретры не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>Непроизвольный, шокированный вздох вырывается из рта [npc.namePos], когда [npc.she] [npc.verb(feel)] чувствует, как уретра киски [npc.her] расслабляется и растягивается. Спустя мгновение тревожное чувство проходит, и [npc.she] очень быстро [npc.verb(realise)] понимает, что внутренняя [style.boldGrow(вместимость уретры [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\"," + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] срывается на крик, когда [npc.she] [npc.verb(feel)] [npc.her] киска непроизвольно сжимается и пульсирует. Спустя мгновение тревожное чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутренность [npc.her] уретры [style.boldShrink(сузилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " уретра)]!</p>");
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
		return (int) (owner.getHeightValue() * 0.05f * depth.getDepthPercentage());
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
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] тревожное пульсирующее давление, поднимающееся от [npc.her] киски вверх к [npc.her] нижней части живота. Прежде чем [npc.her] ах превращается в жалобный крик, давление внезапно исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что уретра [npc.her] киски [style.boldGrow(стала глубже)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] пугающее ощущение сжатия, спускающееся от [npc.her] нижней части живота в [npc.her] киску. Прежде чем [npc.her] вздох успевает превратиться в жалобный крик, чувство внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что уретра [npc.her] киски [style.boldShrink(стала мельче)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " уретра)]!</p>");
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
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] странное ощущение расслабления, пульсирующее глубоко внутри [npc.her] киски. Так же быстро, как оно началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldGrow(эластичность [npc.her] уретры увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может сдержать удивленного вздоха, когда [npc.she] [npc.verb(feel)] странное сжимающее ощущение, пульсирующее глубоко внутри [npc.her] киски. Так же быстро, как оно началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldShrink(эластичность [npc.her] уретры уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " уретра)]!</p>");
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
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздохнула от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко внутри [npc.her] киски. Прежде чем [npc.she] успела [npc.has] запаниковать, чувство быстро исчезло, оставив [npc.herHim] инстинктивное понимание того, что [style.boldGrow(пластичность уретры [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " уретра)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] прерывисто ахает от шока, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение размягчения, пульсирующее глубоко внутри [npc.her] киски. Прежде чем [npc.she] [npc.has] успевает запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldShrink(пластичность)] уретры [npc.her] снизилась.<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " уретра)]!</p>");
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
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isVaginaUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не выдать [npc.a_moan+], когда глубоко внутри [npc.her] [npc.pussy] нарастает интенсивное давление, но прежде чем [npc.sheHasFull] успевает среагировать, это чувство быстро рассеивается. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что стенки уретры [npc.her] теперь выстланы [style.boldGrow(мышцами)], которые [npc.she] может использовать, чтобы умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex(вагинальная уретра [npc.NamePos] теперь выстлана сложной сетью мышц!)]</p>");
				}
				break;
			case RIBBED:
				if(owner.isVaginaUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] не может не издать [npc.a_moan+], когда глубоко внутри [npc.her] [npc.pussy] нарастает интенсивное давление, но прежде чем [npc.sheHasFull] успевает среагировать, это чувство быстро рассеивается. Слегка пошевелив [npc.her] [npc.pussy], [npc.she] [npc.verb(discover)], что стенки [npc.her] уретры теперь покрыты [style.boldGrow(мясистыми, сверхчувствительными складками)], которые дарят невероятное наслаждение при стимуляции.<br/>[style.boldSex([npc.NamePos] вагинальная уретра теперь покрыта мясистыми, вызывающими удовольствие складками!)]</p>");
				}
				break;
			case TENTACLED:
				if(owner.isVaginaUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно издает [npc.a_moan+], когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Сделав пробное сжатие, [npc.she] [npc.verb(discover)], что стенки [npc.her] уретры теперь покрыты [style.boldGrow(маленькими извивающимися щупальцами)], над которыми у [npc.sheHasFull] лишь ограниченный контроль.<br/>[style.boldSex(Внутри вагинальной уретры [npc.namePos] теперь копошатся маленькие щупальца, которые извиваются и ласкают любой входящий объект, словно живя своей собственной жизнью!)]</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно издает [npc.a_moan+], когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] [npc.pussy], после чего край [npc.her] уретры внезапно [style.boldGrow(раздувается)], превращаясь в кольцо, похожее на пончик.<br/>[style.boldSex(Край уретры [npc.namePos] теперь опухший и раздутый!)]</p>");
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
				if(owner.isVaginaUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Сделав пробное сжатие, [npc.she] [npc.verb(discover)], что внутренние стенки [npc.her] уретры [style.boldShrink(потеряли свои дополнительные мышцы)].<br/>[style.boldSex(Уретра [npc.NamePos] больше не покрыта сложной сетью мышц!)]</p>");
				}
				break;
			case RIBBED:
				if(owner.isVaginaUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Слегка пошевелив [npc.her] [npc.pussy], [npc.she] [npc.verb(discover)], что [style.boldShrink(мясистые, сверхчувствительные складки)], когда-то выстилавшие уретру [npc.her], [style.boldShrink(исчезли)].<br/>[style.boldSex(Уретра [npc.NamePos] больше не покрыта мясистыми, вызывающими наслаждение складками!)]</p>");
				}
				break;
			case TENTACLED:
				if(owner.isVaginaUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] [npc.pussy] нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Проверив всё экспериментальным сжатием, [npc.she] [npc.verb(discover)], что [style.boldShrink(извивающиеся щупальца)] внутри уретры [npc.her] [style.boldShrink(полностью исчезли)].<br/>[style.boldSex(Внутри уретры [npc.namePos] больше нет щупалец!)]</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает, когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] [npc.pussy], прежде чем [style.boldShrink(припухший край)] уретры [npc.her] [style.boldShrink(сдувается)], принимая более нормальный вид.<br/>[style.boldSex(Край уретры [npc.namePos] больше не опухший и раздутый!)]</p>");
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
