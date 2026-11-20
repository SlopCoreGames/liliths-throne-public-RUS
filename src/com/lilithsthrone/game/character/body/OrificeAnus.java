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
public class OrificeAnus implements OrificeInterface {
	
	protected int wetness;
	protected float capacity;
	protected float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeAnus(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificeAnus(OrificeAnus orificeAnusToCopy) {
		this.wetness = orificeAnusToCopy.wetness;
		this.capacity = orificeAnusToCopy.capacity;
		this.stretchedCapacity = orificeAnusToCopy.stretchedCapacity;
		this.depth = orificeAnusToCopy.depth;
		this.elasticity = orificeAnusToCopy.elasticity;
		this.plasticity = orificeAnusToCopy.plasticity;
		this.virgin = orificeAnusToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeAnusToCopy.orificeModifiers);
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Влажность [npc.namePos] [npc.asshole] не меняется...)]</p>");
		}

		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner,
						"<p>[npc.NamePos] [npc.eyes] расширяются, пока [npc.she] [npc.verb(feel)] влага покрывает [npc.her] анус, и [npc.she] не может сдержать глубокий [npc.moan], когда [npc.she] [npc.verb(realise)], что задний вход [npc.her] смазывается и [style.boldGrow(становится всё мокрее)].<br/>Трансформация быстро проходит, оставляя [npc.herHim] с [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " анусом)]!</p>");
			
		} else {
			return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(shift)] о дискомфорте и [npc.verb(let)] разочарованный стон, когда [npc.she] [npc.verb(feel)] задний вход [npc.her] [style.boldShrink(подсыхает)].<br/>Трансформация быстро проходит, оставляя [npc.herHim] с [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " анусом)]!</p>");
		}
	}

	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue(capacity);
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Емкость [npc.namePos] [npc.asshole] не меняется...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>Непроизвольный, шокированный вздох срывается с рта [npc.namePos], когда [npc.she] [npc.verb(feel)] анус [npc.her] расширяется и растягивается. Спустя мгновение ощущение проходит, и [npc.she] очень быстро [npc.verb(realise)], что внутренняя [style.boldGrow(емкость ануса [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(\"}}" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " анусом)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] вырывается крик, когда [npc.she] [npc.verb(feel)] анус [npc.her] неконтролируемо сжимается и судорожно пульсирует. Спустя мгновение ощущение проходит, и [npc.she] очень быстро [npc.verb(realise)] понимает, что внутренний проход ануса [npc.her] [style.boldShrink(стал меньше)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " анусом)]!</p>");
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
		return (int) (owner.getHeightValue() * 0.12f * depth.getDepthPercentage());
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
					"<p style='text-align:center;'>[style.colourSex(Из-за того, что она сделана из "+owner.getBodyMaterial().getName()+", глубина [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" [npc.asshole] изменить нельзя...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Глубина [npc.namePos] [npc.asshole] не меняется...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно ахает от неожиданности, когда [npc.she] [npc.verb(feel)] тревожное давление, пульсирующее глубоко внутри [npc.her] задницы. Прежде чем вдох [npc.her] успевает превратиться в жалобный стон, давление внезапно исчезает, и [npc.herHim] инстинктивно понимает, что [npc.her] анус [style.boldGrow(стал глубже)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " анусом)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] невольно издает удивленный вздох, когда [npc.she] [npc.verb(feel)] тревожное ощущение сжатия, поднимающееся из самых глубин [npc.her] задницы. Прежде чем вздох [npc.her] превращается в жалобный крик, чувство внезапно исчезает, оставляя [npc.herHim] с инстинктивным пониманием того, что [npc.her] анус [style.boldShrink(стал мельче)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " анусом)]!</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Эластичность [npc.namePos] [npc.asshole] не меняется...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может сдержать удивленного вздоха, когда [npc.she] [npc.verb(feel)] странное ощущение расслабления, пульсирующее глубоко внутри [npc.her] задницы. Так же быстро, как оно началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)] что анус [npc.her] [style.boldGrow(стал более эластичным)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " анусом)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] не может не издать удивленный вздох, когда [npc.she] [npc.verb(feel)] странное сжимающее ощущение, пульсирующее глубоко внутри [npc.her] задницы. Так же быстро, как оно началось, чувство проходит, и [npc.she] очень быстро [npc.verb(realise)] понимает, что [style.boldShrink(эластичность ануса [npc.her] уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " анусом)]!</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Пластичность [npc.namePos] [npc.asshole] не меняется...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] издает шокированный вздох, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение затвердевания, пульсирующее глубоко внутри [npc.her] задницы. Прежде чем [npc.she] [npc.has] успеет запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldGrow(пластичность ануса [npc.her] увеличилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " анусом)]!</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] сдавленный вздох, когда [npc.she] внезапно [npc.verb(feel)] странное ощущение размягчения, пульсирующее глубоко внутри [npc.her] задницы. Прежде чем [npc.she] [npc.has] время, чтобы запаниковать, чувство быстро исчезает, оставляя [npc.herHim] инстинктивное понимание того, что [style.boldShrink(пластичность ануала [npc.her] уменьшилась)].<br/>[npc.Name] теперь [npc.has] [style.boldSex(, " + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " анусом)]!</p>");
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
						"<p>[npc.Name] не может не выдохнуть [npc.a_moan+], когда интенсивное давление нарастает глубоко внутри [npc.her] задницы, но прежде чем [npc.sheHasFull] представится шанс среагировать, чувство быстро рассеивается. С экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутренние стенки [npc.her] ануала теперь выстланы [style.boldGrow(дополнительными мышцами)], которые [npc.she] может использовать, чтобы умело обхватывать и сжимать любой проникающий объект.<br/>[style.boldSex(ануал [npc.NamePos] теперь выстлан сложной системой мышц!)]</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда сильное давление нарастает глубоко внутри [npc.her] задницы, но прежде чем у [npc.sheHasFull] появляется возможность среагировать, чувство быстро исчезает. Слегка пошевелившись, [npc.she] [npc.verb(discover)], что внутренность [npc.her] ануса теперь выстлана [style.boldGrow(мясистыми, сверхчувствительными складками)], которые дарят невероятное удовольствие при стимуляции.<br/>[style.boldSex(Внутри [npc.NamePos] ануса теперь выстланы мясистые, вызывающие удовольствие складки!)]</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не издать [npc.a_moan+], когда сильное давление нарастает глубоко внутри [npc.her] задницы, но прежде чем у [npc.sheHasFull] появляется возможность среагировать, чувство быстро исчезает. Проверив это экспериментальным сжатием, [npc.she] [npc.verb(discover)], что внутренность [npc.her] ануса теперь выстлана [style.boldGrow(маленькими извивающимися щупальцами)], над которыми у [npc.sheHasFull] лишь ограниченный контроль.<br/>[style.boldSex(Внутри [npc.namePos] ануса теперь копошатся маленькие щупальца, которые живут своей жизнью, извиваясь и лаская любой входящий объект!)]</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может не вскрикнуть, когда [npc.she] [npc.verb(feel)] покалывающее ощущение, пробегающее по [npc.her] [npc.ass], прежде чем край [npc.her] [npc.asshole] [style.boldGrow(раздувается)], превращаясь в кольцо, похожее на пончик.<br/>[style.boldSex(Край [npc.namePos] ануса теперь опухший и раздутый!)]</p>");
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
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] задницы нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Попробовав сжать мышцы, [npc.she] [npc.verb(discover)], что внутри [npc.her] ануса [style.boldShrink(исчезли лишние мышцы)].<br/>[style.boldSex(Анус [npc.NamePos] больше не выстлан сложной сетью мышц!)]</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] задницы нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Немного пошевелившись, [npc.she] [npc.verb(discover)], что [style.boldShrink(мясистые, сверхчувствительные складки)], когда-то выстилавшие стенки [npc.her] ануса, [style.boldShrink(исчезли)].<br/>[style.boldSex(Анус [npc.NamePos] больше не выстлан мясистыми, вызывающими наслаждение складками!)]</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>[npc.Name] невольно вскрикивает от неожиданности, когда глубоко внутри [npc.her] задницы нарастает сильное давление, но прежде чем [npc.sheHasFull] успевает среагировать, ощущение быстро исчезает. Попробовав сжать мышцы, [npc.she] [npc.verb(discover)], что [style.boldShrink(извивающиеся щупальца)] внутри [npc.her] ануса [style.boldShrink(полностью исчезли)].<br/>[style.boldSex(Внутри ануса [npc.namePos] больше нет щупалец!)]</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>[npc.Name] не может сдержать крика, когда [npc.she] [npc.verb(feel)] покалывающее ощущение пробегает по [npc.her] [npc.ass], а затем припухлый край [npc.her] [npc.asshole] [style.boldShrink(сдувается)], принимая более привычную форму.<br/>[style.boldSex(Край ануса [npc.namePos] больше не опухший и раздутый!)]</p>");
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
