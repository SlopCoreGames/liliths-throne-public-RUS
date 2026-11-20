package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Clitoris implements BodyPartInterface {

	protected int clitSize;
	protected int girth;
	protected Set<PenetrationModifier> clitModifiers;
	
	public Clitoris(int clitSize, int girth) {
		this.clitSize = clitSize;
		this.girth = girth;
		
		clitModifiers = new HashSet<>();
	}

	public Clitoris(Clitoris clitorisToCopy) {
		this.clitSize = clitorisToCopy.clitSize;
		this.girth = clitorisToCopy.girth;
		
		this.clitModifiers = new HashSet<>(clitorisToCopy.clitModifiers);
	}
	
	@Override
	public BodyPartTypeInterface getType() {
		return null;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public String getName(GameCharacter gc) {
		return getNameSingular(gc);
	}
	
	@Override
	public String getName(GameCharacter gc, boolean withDescriptor) {
		String name = getName(gc);
		String descriptor = getDescriptor(gc);
		return (withDescriptor && descriptor!=null && !descriptor.isEmpty()?descriptor+" ":"")+name;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(this.getClitorisSize()==ClitorisSize.ZERO_AVERAGE) {
			return UtilText.returnStringAtRandom("клитор", "клитор", "клитор", "горошина", "бугорок");
		} else {
			return UtilText.returnStringAtRandom("клитор", "клитор", "клитор", "клитор-член");
		}
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		if(this.getClitorisSize()==ClitorisSize.ZERO_AVERAGE) {
			return UtilText.returnStringAtRandom("клиторы", "клиторы", "клиторы", "бугорки", "горошины");
		} else {
			return UtilText.returnStringAtRandom("клиторы", "клиторы", "клиторы", "клитор-член");
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(GameCharacter gc) {
		return gc.getVaginaType().getBodyCoveringType(gc);
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return body.getVagina().getBodyCoveringType(body);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		List<String> descriptors = new ArrayList<>();
		
		descriptors.add("sensitive");
		
		if(this.getGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(this.getGirth().getName());
		}
		
		if(this.getClitorisSize()!=ClitorisSize.ZERO_AVERAGE) {
			descriptors.add(this.getClitorisSize().getDescriptor());
		} else {
			descriptors.add("little");
		}
		
		if(!this.getClitorisModifiers().isEmpty()) {
			PenetrationModifier mod = Util.randomItemFrom(this.getClitorisModifiers());
			if(mod!=PenetrationModifier.OVIPOSITOR) {
				descriptors.add(mod.getName());
			}
		}

		if(gc.getBodyMaterial().getPartDescriptors()!=null && !gc.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptors.add(Util.randomItemFrom(gc.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(descriptors);
	}
	
	public String getClitTipNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("кончик", "кончик", "конец");
	}
	
	public String getClitTipNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("кончики", "кончики", "заканчивается");
	}
	
	public String getClitTipDescriptor(GameCharacter gc) {
		List<String> descriptors = new ArrayList<>();
		for(PenetrationModifier mod : this.getClitorisModifiers()) {
			switch(mod) {
				case BARBED:
				case KNOTTED:
				case OVIPOSITOR:
				case PREHENSILE:
				case RIBBED:
				case SHEATHED:
				case TENTACLED:
				case VEINY:
					break;
				case BLUNT:
					descriptors.add("blunt");
					break;
				case FLARED:
					descriptors.add("flared");
					break;
				case TAPERED:
					descriptors.add("tapered");
					break;
			}
		}
		if(descriptors.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(descriptors);
	}
	
	public ClitorisSize getClitorisSize() {
		return ClitorisSize.getClitorisSizeFromInt(clitSize);
	}

	public int getRawClitorisSizeValue() {
		return clitSize;
	}
	
	public String setClitorisSize(GameCharacter owner, int clitSize) {
		if(!isCharacterInitialised(owner)) {
			this.clitSize = Math.max(0, Math.min(clitSize, ClitorisSize.SEVEN_STALLION.getMaximumValue()));
			return "";
		}
		
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		int oldSize = this.clitSize;
		this.clitSize = Math.max(0, Math.min(clitSize, ClitorisSize.SEVEN_STALLION.getMaximumValue()));
		int sizeChange = this.clitSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Размер твоего клитора не меняется...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер клитора [npc.namePos] не меняется...)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>Ты издаешь [pc.a_moan], чувствуя, как внутри твоей [pc.pussy] нарастает глубокая пульсация. Твои щеки краснеют, когда это ощущение поднимается к самому клитору, и с легким вздохом ты чувствуешь, как он [style.boldGrow(увеличивается)].<br/>Теперь у тебя есть [style.boldSex([pc.a_clitSize] [pc.clit])]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] издает [npc.a_moan], когда [npc.she] чувствует, как внутри [npc.her] [npc.pussy] нарастает глубокая пульсация. Щеки [npc.Her] краснеют, когда это ощущение поднимается к [npc.her] клитору, и с легким вздохом [npc.she] чувствует, как он [style.boldGrow(увеличивается)].<br/>Теперь у [npc.She] есть [style.boldSex([npc.a_clitSize] [npc.clit])]!</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>Ты издаешь [pc.a_moan], чувствуя, как внутри твоей [pc.pussy] нарастает глубокая пульсация. Твои щеки краснеют, когда это ощущение поднимается к самому клитору, и с легким вздохом ты чувствуешь, как он [style.boldShrink(уменьшается)].<br/>Теперь у тебя есть [style.boldSex([pc.a_clitSize] [pc.clit])]!</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] издает [npc.a_moan], когда [npc.she] чувствует, как внутри [npc.her] нарастает глубокая пульсация. [npc.pussy] [npc.Her] щеки краснеют, когда это ощущение поднимается к [npc.her] клитору, и с легким вздохом [npc.she] чувствует, как он [style.boldShrink(уменьшается)].<br/>[npc.She] теперь обладает [style.boldSex([npc.a_clitSize] [npc.clit])]!</p>");
			}
		}
	}
	

	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenetrationGirth.FOUR_FAT.getValue()
	 */
	public String setGirth(GameCharacter owner, int girth) {
		if(!isCharacterInitialised(owner)) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Толщина [npc.namePos] [npc.clit] не меняется...)]</p>");
		}
		
		if (girthChange > 0) {
			return UtilText.parse(owner,
					"</p>[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] глубокую пульсацию, нарастающую внутри [npc.her] клитора. [npc.Her] щеки краснеют, когда ощущение распространяется по всему [npc.her] [npc.pussy+], и [npc.she] не может сдержать очередной [npc.moan+], когда [npc.her] клитор внезапно [style.boldGrow(становится толще)].<br/>[npc.She] теперь [npc.has] [style.boldSex([npc.a_clitGirth] [npc.clit])]!</p>");
		} else {
			return UtilText.parse(owner,
					"</p>[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] глубокую пульсацию, нарастающую внутри [npc.her] клитора. [npc.Her] щеки краснеют, когда ощущение поднимается вверх по [npc.her] стержню, и [npc.she] не может сдержать очередной [npc.moan+], когда [npc.her] клитор внезапно [style.boldShrink(сужается)].<br/>[npc.She] теперь [npc.has] [style.boldSex([npc.a_clitGirth] [npc.cock])]!</p>");
		}
	}

	// Diameter:

	public static float getGenericDiameter(int length, PenetrationGirth girth) {
		return getGenericDiameter(length, girth, new HashSet<>());
	}
	
	public static float getGenericDiameter(int length, PenetrationGirth girth, Set<PenetrationModifier> mods) {
		float baseDiameterModifier = 0.2f;
		baseDiameterModifier = Math.max(0.15f, baseDiameterModifier - (Math.max(length-15, 0) * 0.0025f)); // Every cm over 15 (6 inches) reduces the base diameter modifier by 0.25%
		
		return Units.round((length * baseDiameterModifier) * (1f + girth.getDiameterPercentageModifier() + (mods.contains(PenetrationModifier.FLARED)?0.05f:0) + (mods.contains(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
	}
	
	public float getDiameter() {
		return getGenericDiameter(clitSize, getGirth(), clitModifiers);
	}

	public Set<PenetrationModifier> getClitorisModifiers() {
		return clitModifiers;
	}
	
	public boolean hasClitorisModifier(PenetrationModifier modifier) {
		return clitModifiers.contains(modifier);
	}

	public String addClitorisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(hasClitorisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}

		clitModifiers.add(modifier);
		
		List<String> pmsRemoved = new ArrayList<>();
		
		for(PenetrationModifier pm : modifier.getMutuallyExclusivePenetrationModifiers()) {
			if(hasClitorisModifier(pm)) {
				pmsRemoved.add(pm.getName());
				clitModifiers.remove(pm);
			}
		}

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(!owner.hasVagina()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(У тебя нет клитора, поэтому ничего не происходит...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] не хватает клитора, так что ничего не происходит...)]</p>");
			}
		}
		
		
		String removedText = "";
		if(!pmsRemoved.isEmpty()) {
			removedText = "<br/>[style.italicsMinorBad(Due to being mutually exclusive with the '"+modifier.getName()+"' модификатор, [npc.namePos] клитор больше не "+Util.stringsToStringList(pmsRemoved, false)+".)]";
		}
		
		String returnText = "";
		
		switch(modifier) {
			case RIBBED:
				returnText = "Внутри [npc.namePos] [npc.clit] нарастает сильное давление, но прежде чем [npc.she] [npc.has] успеет среагировать, по всей его длине вырастает ряд [style.boldGrow(твердых, мясистых ребринок)].<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь ребристый!)]";
				break;
			case TENTACLED:
				returnText = "Внутри [npc.namePos] [npc.clit] нарастает пульсирующее тепло, но прежде чем [npc.she] [npc.has] успеет среагировать, по всей его длине вырастает ряд [style.boldGrow(маленьких извивающихся щупалец)].<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь покрыт маленькими щупальцами, которые извиваются сами по себе!)]";
				break;
			case BARBED:
				returnText = "Внутри [npc.namePos] [npc.clit] нарастает сильное тепло, но прежде чем [npc.she] [npc.has] успеет среагировать, по всей его длине вырастает ряд [style.boldGrow(маленьких мясистых шипов)].<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь усажен мясистыми шипами, направленными назад!)]";
				break;
			case FLARED:
				returnText = "На кончике [npc.namePos] [npc.clit] нарастает сильное тепло, и прежде чем [npc.she] [npc.has] успеет среагировать, [style.boldGrow(головка расширяется)], напоминая лошадиную.<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь имеет широкую, расширенную головку!)]";
				break;
			case BLUNT:
				returnText = "В кончике [npc.namePos] [npc.clit] нарастает сильный жар, и прежде чем [npc.she] [npc.has] успеет среагировать, [style.boldGrow(головка разглаживается)], становясь похожей на головку рептилии.<br/>[style.boldSex(у [npc.NamePos] [npc.clit] теперь гладкая, тупая головка!)]";
				break;
			case KNOTTED:
				returnText = "В основании [npc.namePos] [npc.clit] нарастает сильный жар, и прежде чем [npc.she] [npc.has] успеет среагировать, там быстро раздувается [style.boldGrow(жирный узел)].<br/>[style.boldSex(у [npc.NamePos] [npc.clit] теперь есть жирный узел у основания!)]";
				break;
			case PREHENSILE:
				returnText = "Странное покалывание пробегает по всей длине [npc.namePos] [npc.clit], и [npc.she] внезапно осознает, что она стала [style.boldGrow(цепкой)], что позволяет [npc.herHim] извиваться и двигаться, словно хвост примата.<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь цепкая!)]";
				break;
			case SHEATHED:
				returnText = "В основании [npc.namePos] [npc.clit] нарастает сильное давление, и прежде чем [npc.she] [npc.has] успеет среагировать, оно втягивается в совершенно новую [style.boldGrow(оболочку)], которая только что там выросла.<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь в оболочке!)]";
				break;
			case TAPERED:
				returnText = "Внутри [npc.namePos] [npc.clit] нарастает сильный жар, но прежде чем [npc.she] [npc.has] успеет среагировать, ствол внезапно [style.boldGrow(сужается к концу)].<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь сужается к концу!)]";
				break;
			case VEINY:
				returnText = "Внутри [npc.namePos] [npc.clit] нарастает сильный жар, но прежде чем [npc.she] [npc.has] успеет среагировать, по всей его длине вздувается [style.boldGrow(ряд заметных вен)].<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь в венах!)]";
				break;
			case OVIPOSITOR:
				returnText = "Сильное покалывание разливается по [npc.namePos] [npc.clit], и [npc.she] не может сдержать [npc.a_moan+], когда [npc.she] чувствует, как он превращается в [style.boldGrow(яйцеклад)].<br/>[style.boldSex([npc.NamePos] [npc.clit] теперь может откладывать яйца!)]<br/><i>(Чтобы стать полностью функциональным, [npc.name] [npc.verb(require)] [npc.her] яйца, которые должны быть оплодотворены перед откладкой. Яйца нельзя откладывать во влагалище уже беременной цели.)</i>";
				break;
		}
		
		if(returnText.isEmpty()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		return UtilText.parse(owner,
				"<p>"
					+returnText
					+removedText
				+"</p>");
	}

	public String removeClitorisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasClitorisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		clitModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>Вы чувствуете, как внутри вашей [pc.clit] разливается успокаивающая прохлада, и прежде чем вы успеваете среагировать, ваши твердые мясистые ребра внезапно [style.boldShrink(исчезают)].<br/>[style.boldSex(Ваша [pc.clit] больше не ребристая!)]</p>";
				} else {
					return "<p>Внутри [npc.namePos] [npc.clit] разливается успокаивающая прохлада, но прежде чем [npc.sheHasFull] успеет среагировать, [npc.her] твердые мясистые ребра внезапно [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не ребристая!)]</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как внутри твоей [pc.clit] разливается успокаивающая прохлада, и прежде чем ты успеваешь среагировать, твои маленькие извивающиеся щупальца [style.boldShrink(исчезают)].<br/>[style.boldSex(Твоя [pc.clit] больше не покрыта маленькими щупальцами!)]</p>";
				} else {
					return "<p>Внутри [npc.namePos] [npc.clit] разливается успокаивающая прохлада, и прежде чем у [npc.sheHasFull] появляется шанс среагировать, [npc.her] маленькие извивающиеся щупальца [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не покрыта маленькими щупальцами!)]</p>";
				}
			case BARBED:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как внутри твоей [pc.clit] разливается успокаивающая прохлада, и прежде чем ты успеваешь среагировать, твои маленькие плотские шипы [style.boldShrink(исчезают)].<br/>[style.boldSex(Твоя [pc.clit] больше не шипованная!)]</p>";
				} else {
					return "<p>Внутри [npc.namePos] [npc.clit] разливается успокаивающая прохлада, но прежде чем у [npc.sheHasFull] появляется шанс среагировать, [npc.her] маленькие плотские шипы [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не шипованная!)]</p>";
				}
			case FLARED:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как в кончике твоей [pc.clit] разливается успокаивающая прохлада, и прежде чем ты успеваешь среагировать, твоя расширенная головка [style.boldShrink(уменьшается)], из-за чего твой клитор становится похожим на обычный, человеческий.<br/>[style.boldSex(У твоей [pc.clit] больше нет расширенной головки!)]</p>";
				} else {
					return "<p>Приятная прохлада разливается по кончику [npc.namePos] [npc.clit], и прежде чем [npc.sheHasFull] успевает среагировать, расширенная головка [npc.her] [style.boldShrink(уменьшается)], из-за чего клитор [npc.her] становится похожим на обычный, человеческий.<br/>[style.boldSex(У [npc.NamePos] [npc.clit] больше нет расширенной головки!)]</p>";
				}
			case BLUNT:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как приятная прохлада разливается по кончику твоего [pc.clit], и прежде чем ты успеваешь среагировать, твоя тупая головка [style.boldShrink(уменьшается)], из-за чего твой клитор становится похожим на обычный, человеческий.<br/>[style.boldSex(Твой [pc.clit] больше не имеет тупой головки!)]</p>";
				} else {
					return "<p>Приятная прохлада разливается по кончику [npc.namePos] [npc.clit], и прежде чем [npc.sheHasFull] успевает среагировать, тупая головка [npc.her] [style.boldShrink(уменьшается)], из-за чего клитор [npc.her] становится похожим на обычный, человеческий.<br/>[style.boldSex(У [npc.NamePos] [npc.clit] больше нет тупой головки!)]</p>";
				}
			case KNOTTED:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как приятная прохлада разливается у основания твоего [pc.clit], и прежде чем ты успеваешь среагировать, твой жирный узел [style.boldShrink(уменьшается)] и исчезает.<br/>[style.boldSex(Твой [pc.clit] больше не узловатый!)]</p>";
				} else {
					return "<p>Приятная прохлада разливается у основания [npc.namePos] [npc.clit], и прежде чем [npc.sheHasFull] успевает среагировать, жирный узел [npc.her] [style.boldShrink(уменьшается)] и исчезает.<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не узловатый!)]</p>";
				}
			case PREHENSILE:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь странное покалывание, поднимающееся вверх по всей длине твоего [pc.clit], и внезапно осознаешь, что он [style.boldShrink(больше не хватательный)].<br/>[style.boldSex(Твой [pc.clit] больше не хватательный!)]</p>";
				} else {
					return "<p>Странное покалывание поднимается вверх по всей длине [npc.namePos] [npc.clit], и [npc.she] внезапно осознает, что он [style.boldShrink(больше не хватательный)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не хватательный!)]</p>";
				}
			case SHEATHED:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как у основания твоего [pc.clit] нарастает успокаивающая прохлада, и прежде чем ты успеваешь среагировать, твоя оболочка [style.boldShrink(исчезает)].<br/>[style.boldSex(Твой [pc.clit] больше не скрыт в оболочке!)]</p>";
				} else {
					return "<p>У основания [npc.namePos] [npc.clit] нарастает успокаивающая прохлада, и прежде чем у [npc.sheHasFull] появляется шанс среагировать, оболочка [npc.her] [style.boldShrink(исчезает)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не скрыт в оболочке!)]</p>";
				}
			case TAPERED:
				if(owner.isPlayer()) {
					return "<p>Ты чувствуешь, как внутри твоего [pc.clit] нарастает интенсивное тепло, но прежде чем ты успеваешь среагировать, ствол внезапно [style.boldShrink(расширяется)].<br/>[style.boldSex(Твой [pc.clit] больше не сужается к концу!)]</p>";
				} else {
					return "<p>Внутри [npc.namePos] [npc.clit] нарастает интенсивное тепло, но прежде чем у [npc.sheHasFull] появляется шанс среагировать, ствол внезапно [style.boldShrink(расширяется)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не сужается к концу!)]</p>";
				}
			case VEINY:
				if(owner.isPlayer()) {
					return "<p>Вы чувствуете, как внутри вашего [pc.clit] нарастает сильный жар, но прежде чем вы успеваете среагировать, ваши вздувшиеся вены [style.boldShrink(исчезают)].<br/>[style.boldSex(Ваш [pc.clit] больше не венозный!)]</p>";
				} else {
					return "<p>Сильный жар нарастает внутри [npc.namePos] [npc.clit], но прежде чем у [npc.sheHasFull] появляется шанс среагировать, [npc.her] вздувшиеся вены [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не венозный!)]</p>";
				}
			case OVIPOSITOR:
				return "<p>Странное покалывание пробегает по всей длине [npc.namePos] [npc.clit], и [npc.she] не может сдержать [npc.a_moan+], когда [npc.she] чувствует, как он превращается в нечто, что [style.boldShrink(больше не функционирует как яйцеклад)].<br/>[style.boldSex([npc.NamePos] [npc.clit] больше не яйцеклад!)]</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}

	public void resetClitorisModifiers() {
		clitModifiers = new HashSet<>();
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Clitoris.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}
