package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.4.4
 * @author Innoxia
 */
public enum FluidModifier {
	
	VISCOUS(PresetColour.BASE_PURPLE_DARK,
			false,
			"viscous",
			"Оно довольно вязкое и медленно стекает крупными каплями, напоминая густую патоку.",
			"Придает жидкости густую консистенцию, нечто среднее между жидким и твердым состоянием."),
	
	STICKY(PresetColour.BASE_YELLOW_LIGHT,
			false,
			"sticky",
			"Оно довольно липкое, и его трудно полностью смыть без мыла.",
			"Заставляет эту жидкость прилипать ко всему, чего она касается."),
	
	SLIMY(PresetColour.BASE_BLUE_LIGHT,
			false,
			"slimy",
			"Оно имеет склизкую, маслянистую текстуру.",
			"Придает этой жидкости ощущение скользкости и слизи."),
	
	BUBBLING(PresetColour.BASE_LILAC_LIGHT,
			false,
			"bubbling",
			"Оно шипит и пузырится, как газированный напиток.",
			"Заставляет эту жидкость пузыриться, словно газировку."),
	
	// SPECIAL EFFECTS:

	MUSKY(PresetColour.BASE_TAN,
			true,
			"musky",
			"У него сильный мускусный запах.",
			"Мускусная сперма и женская смазка накладывают эффект «отмечен мускусом» на любого, кто будет покрыт ими во время секса."),
	
	
	MINERAL_OIL(PresetColour.BASE_BLACK,
			true,
			"минеральное масло",
			"Оно богато минералами, полезными для кожи, но губительными для латекса.",
			"Жидкости с минеральным маслом быстро разрушают презервативы, из-за чего те могут порваться в самый момент оргазма."),
	
	ALCOHOLIC(PresetColour.BASE_ORANGE,
			true,
			"крепкоалкогольный",
			"В нем высокое содержание алкоголя, поэтому те, кто его употребит, быстро сильно опьянеют.",
			"Крепкоалкогольные жидкости значительно повышают уровень опьянения у любого, кто их употребит.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.001f); //TODO factor in body size
		}
	},

	ALCOHOLIC_WEAK(PresetColour.BASE_ORANGE_LIGHT,
			true,
			"alcoholic",
			"В нем низкое содержание алкоголя, поэтому те, кто его употребит, слегка опьянеют.",
			"Алкогольные жидкости повышают уровень опьянения у любого, кто их употребит.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.0001f); //TODO factor in body size
		}
	},
	
	ADDICTIVE(PresetColour.BASE_PINK,
			true,
			"addictive",
			"Вызывает сильное привыкание: любой, кто будет пить слишком много, быстро станет зависимым.",
			"Вызывающие привыкание жидкости заставляют любого, кто их употребит, подсесть на этот конкретный вид жидкости.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target==null || fluidProvider==null) {
				return ""; // catch for if one of the characters is null, which was the case in GameCharacter.calculateGenericSexEffects
			}
			if(target.isDoll()) {
				return "";
			}
			boolean curedWithdrawal = target.getAddiction(fluid.getType())!=null && Main.game.getMinutesPassed()-target.getAddiction(fluid.getType()).getLastTimeSatisfied()>=24*60;
			boolean appendAddiction = !Main.game.isInSex() || curedWithdrawal;
			if(target.addAddiction(new Addiction(fluid.getType(), Main.game.getMinutesPassed(), fluidProvider.getId()))) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>Благодаря аддиктивным свойствам "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(find)] [npc.herself] [style.colourArcane(жажда)] <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))+"</span> "+fluid.getName(fluidProvider)+"!"
						+ "</p>");
				
				
			} else {
				target.setLastTimeSatisfiedAddiction(fluid.getType(), Main.game.getMinutesPassed());
				if(appendAddiction) {
					return UtilText.parse(target, fluidProvider,
							"<p style='padding:0; margin:0; text-align:center;'>"
								+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"
									+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))
								+"</span> "+fluid.getName(fluidProvider)
									+" была удовлетворена!"
								+ (curedWithdrawal
									?" [npc.She] [npc.verb(feel)] глубоко благодарен "+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" за то, что предоставил [npc.herHim] то, в чем [npc.she] нуждался больше всего..."
											+ (target.isSlave()?target.incrementObedience(5):"")
									:" [npc.She] [npc.was] не страдает от ломки, но [npc.she] все еще [npc.verb(feel)] благодарен "
											+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" за то, что подпитывает зависимость [npc.her]...")
							+ "</p>");
				}
				return "";
			}
		}
	},
	
	HALLUCINOGENIC(PresetColour.BASE_PINK_DEEP,
			true,
			"psychoactive",
			"Любой, кто употребляет это, испытывает психоактивный эффект, который может проявляться в виде галлюцинаций, связанных с лактацией, или повышенной чувствительности к гипнотическому внушению.",
			"Психоактивные жидкости вызывают у любого употребившего их галлюциногенный трип, из-за чего восприятие половых органов искажается, а сам человек становится уязвим для гипнотического манипулирования.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_3)) {
				return "";
			}
			target.addPsychoactiveFluidIngested(fluid.getType());
			boolean appendPsychoactive = !target.hasStatusEffect(StatusEffect.PSYCHOACTIVE);
			target.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60*60);
			if(appendPsychoactive) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>Благодаря психоактивным свойствам "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(start)] <span style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>крышу сносит</span>!</p>");
			}
			return "";
		}
	};
	
	private Colour colour;
	private boolean specialEffects;
	private String name;
	private String description;
	private String briefDescription;
	
	private FluidModifier(Colour colour, boolean specialEffects, String name, String briefDescription, String description) {
		this.colour = colour;
		this.specialEffects = specialEffects;
		this.name = name;
		this.briefDescription = briefDescription;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isSpecialEffects() {
		return specialEffects;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBriefDescription() {
		return briefDescription;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isAppliesSpecialEffects() {
		return description!=null;
	}
	
	public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
		return "";
	}
}
