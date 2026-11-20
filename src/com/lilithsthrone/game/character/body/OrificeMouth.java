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
public class OrificeMouth implements OrificeInterface {
	
	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeMouth(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificeMouth(OrificeMouth orificeMouthToCopy) {
		this.wetness = orificeMouthToCopy.wetness;
		this.capacity = orificeMouthToCopy.capacity;
		this.stretchedCapacity = orificeMouthToCopy.stretchedCapacity;
		this.depth = orificeMouthToCopy.depth;
		this.elasticity = orificeMouthToCopy.elasticity;
		this.plasticity = orificeMouthToCopy.plasticity;
		this.virgin = orificeMouthToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeMouthToCopy.orificeModifiers);
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
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", влажность [npc.namePos] "+Wetness.SEVEN_DROOLING.getDescriptor()+" [npc.asshole] изменить нельзя...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Влажность горла [npc.namePos] не меняется...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] внезапно [npc.verb(start)] пустила слюни, и хотя [npc.she] быстро [npc.verb(swallow)] всё это, [npc.she] [npc.verb(feel)] что рот и горло [npc.her] навсегда [style.boldGrow(стали влажнее)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " горло)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] издает взволнованный вздох, пока рот и горло [npc.she] [npc.verb(feel)] [npc.her] [style.boldShrink(подсыхают)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " горло)]!</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Вместимость горла [npc.namePos] не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>Непроизвольный, шокированный вздох вырывается из рта [npc.namePos], когда [npc.she] [npc.verb(feel)] [npc.her] горло расслабляется и растягивается. Спустя мгновение тревожное чувство проходит, и [npc.she] очень быстро [npc.verb(realise)] что внутренняя [style.boldGrow(вместимость горла [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", " + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " горло)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] издает крик, пока [npc.she] [npc.verb(feel)] [npc.her] горло не сжимается и не судорожно сокращается. Спустя мгновение тревожное чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутренность [npc.her] горла [style.boldShrink(стала меньше)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " горло)]!</p>");
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
		return (int) (owner.getHeightValue() * 0.1f * depth.getDepthPercentage());
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
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", глубина [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" горло изменить нельзя...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Глубина горла [npc.namePos] не меняется...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное давление, пульсирующее из глубины горла [npc.her] вниз к торсу [npc.her]. Прежде чем вздох [npc.her] успевает превратиться в мучительный крик, давление внезапно исчезает, оставляя [npc.herHim] инстинктивное понимание того, что горло [npc.her] [style.boldGrow(стало глубже)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " горло)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное ощущение сжатия, поднимающееся от [npc.her] торса к задней части [npc.her] горла. Прежде чем [npc.her] вздох успевает превратиться в отчаянный крик, чувство внезапно исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что [npc.her] горло [style.boldShrink(стало более узким)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " горло)]!</p>");
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
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Эластичность горла [npc.namePos] не меняется...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не ахнуть от неожиданности, когда [npc.she] [npc.verb(feel)] странное ощущение расслабления, пульсирующее глубоко в горле [npc.her]. Так же быстро, как оно появилось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldGrow(эластичность горла [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", \"" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " горло)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не ахнуть от неожиданности, когда [npc.she] [npc.verb(feel)] странное ощущение сжатия, пульсирующее глубоко в горле [npc.her]. Так же быстро, как оно появилось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)], что [style.boldShrink(эластичность горла [npc.her] уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\", \"" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " горло)]!</p>");
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
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Пластичность горла [npc.namePos] не меняется...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] потрясенно ахает, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко в горле [npc.her]. Прежде чем [npc.she] [npc.has] успеет запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldGrow(пластичность горла [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\"}" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " горло)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] прерывистый вздох изумления, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение смягчения, пульсирующее глубоко в горле [npc.her]. Прежде чем [npc.she] успеет [npc.has] запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldShrink(пластичность горла [npc.her] уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " горло)]!</p>");
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
					return UtilText.parse(owner,
							"<p>[npc.Name] не может сдержать короткий вскрик, когда сильное давление нарастает глубоко в горле [npc.her], но прежде чем [npc.sheHasFull] успеет среагировать, чувство быстро рассеивается. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутреннюю часть горла [npc.her] теперь выстилают [style.boldGrow(дополнительные мышцы)], которые [npc.she] может использовать, чтобы умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex(горло [npc.NamePos] теперь выстлано сложной системой мышц!)]</p>");
			case RIBBED:
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает, когда глубоко в [npc.her] горле нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро рассеивается. Слегка пошевелив [npc.her] горлом, [npc.she] [npc.verb(discover)], что теперь стенки [npc.her] горла выстланы [style.boldGrow(мясистыми, сверхчувствительными ребрами)], которые дарят невероятное удовольствие при стимуляции.<br/>[style.boldSex(Горло [npc.NamePos] теперь выстлано мясистыми, вызывающими удовольствие ребрами!)]</p>");
			case TENTACLED:
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает, когда глубоко в горле [npc.her] нарастает сильное давление, но прежде чем у [npc.sheHasFull] появляется возможность среагировать, ощущение быстро исчезает. Сделав пробное сжатие, [npc.she] [npc.verb(discover)], что горло [npc.her] теперь заполнено [style.boldGrow(рядом маленьких извивающихся щупалец)], над которыми у [npc.sheHasFull] лишь ограниченный контроль.<br/>[style.boldSex(Горло [npc.namePos] теперь заполнено маленькими щупальцами, которые извиваются сами по себе!)]</p>");
			case PUFFY:
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает, когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] рту, после чего [npc.her] губы раздуваются и [style.boldGrow(набухают)].<br/>[style.boldSex(Губы [npc.NamePos] теперь невероятно пухлые!)]</p>");
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
							"<p>[npc.Name] невольно вскрикивает, когда глубоко в [npc.her] горле нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Сделав пробное сжатие, [npc.she] [npc.verb(discover)], что внутреннюю часть [npc.her] горла лишили [style.boldShrink(дополнительных мышц)].<br/>[style.boldSex(Горло [npc.NamePos] больше не выстлано сложной сетью мышц!)]</p>");
			case RIBBED:
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает, когда глубоко в [npc.her] горле нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Слегка шевеля [npc.her] горлом, [npc.she] [npc.verb(discover)], что [style.boldShrink(мясистые, сверхчувствительные ребра)], когда-то выстилавшие [npc.her] горло, исчезли.<br/>[style.boldSex(Горло [npc.NamePos] больше не выстлано мясистыми, вызывающими наслаждение ребрами!)]</p>");
			case TENTACLED:
					return UtilText.parse(owner,
							"<p>[npc.Name] невольно вскрикивает, когда глубоко в [npc.her] горле нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Сделав пробное сжатие, [npc.she] [npc.verb(discover)], что [style.boldShrink(цепочка маленьких извивающихся щупалец)] внутри [npc.her] горла полностью исчезла.<br/>[style.boldSex(Внутри горла [npc.namePos] больше нет маленьких щупалец!)]</p>");
			case PUFFY:
					return UtilText.parse(owner,
							"<p>[npc.Name] не может сдержать короткого вскрика, когда [npc.she] [npc.verb(feel)] покалывающее ощущение пробегает по [npc.her] губам, прежде чем они внезапно [style.boldShrink(сдуваются)] до более привычного размера.<br/>[style.boldSex(губы [npc.NamePos] больше не опухшие!)]</p>");
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
