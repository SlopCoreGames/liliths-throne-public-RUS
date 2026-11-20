package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;

/**
 * Used in Pathing.
 * 
 * @since 0.3.1
 * @version 0.3.5
 * @author Innoxia
 */
public enum MapTravelType {

	WALK_SAFE("Пешком (самый безопасный)",
			"Использовать самый безопасный путь до пункта назначения.",
			"Нажмите один раз на карту, чтобы отметить конечную точку, затем нажмите на неё еще раз, чтобы отправиться в путь. Вы можете ставить несколько точек маршрута, удерживая Shift при клике.",
			PresetColour.GENERIC_MINOR_GOOD) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					return !character.isCaptive();
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "Вы не можете использовать быстрое перемещение, пока вы в плену!";
					}
					return "";
				}
			},
	
	WALK_DANGEROUS("Пешком (самый быстрый)",
			"Использовать самый быстрый путь до пункта назначения.",
			"Нажмите один раз на карту, чтобы отметить конечную точку, затем нажмите на неё еще раз, чтобы отправиться в путь. Вы можете ставить несколько точек маршрута, удерживая Shift при клике.",
			PresetColour.GENERIC_MINOR_BAD) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					return !character.isCaptive();
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "Вы не можете использовать быстрое перемещение, пока вы в плену!";
					}
					return "";
				}
			},
	
	FLYING("Лететь",
			"Долететь до пункта назначения.",
			"Нажмите один раз на карту, чтобы отметить конечную точку, затем нажмите на неё еще раз, чтобы отправиться в путь.",
			PresetColour.SPELL_SCHOOL_AIR) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					return !character.isCaptive() && character.isPartyAbleToFly() && character.getWorldLocation().isFlightEnabled();
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "Вы не можете использовать быстрое перемещение, пока вы в плену!";
					}
					if(!character.getWorldLocation().isFlightEnabled()) {
						return "В этой области нельзя летать!";
					}
					if(!character.isAbleToFly()) {
						return "Вы не можете летать!";
					}
					return "Не все ваши спутники умеют летать!";
				}
			},
	
	TELEPORT("Телепортация",
			"Телепортироваться к месту назначения.",
			"Нажмите один раз на карту, чтобы отметить конечную точку, затем нажмите на неё еще раз, чтобы отправиться в путь.",
			PresetColour.SPELL_SCHOOL_ARCANE) {
				@Override
				public boolean isAvailable(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return false;
					}
					if(Main.game.isDebugMode()) {
						return true;
					}
					if(!character.getWorldLocation().getTeleportPermissions().isOutgoing()
							|| (c!=null && !c.getType().getTeleportPermissions().isIncoming())
							|| (c!=null && !c.getPlace().getPlaceType().getTeleportPermissions().isIncoming())) {
						return false;
					}
					return (character.isAbleToTeleport() && character.getMana()>=Spell.TELEPORT.getModifiedCost(character));
				}

				@Override
				public String getUnavailablilityDescription(Cell c, GameCharacter character) {
					if(character.isCaptive()) {
						return "Вы не можете использовать быстрое перемещение, пока вы в плену!";
					}
					if(!character.getWorldLocation().getTeleportPermissions().isOutgoing()) {
						return "Вы не можете телепортироваться из области '"+character.getWorldLocation().getName()+"'!";
					}
					if(c!=null && !c.getType().getTeleportPermissions().isIncoming()) {
						return "Вы не можете телепортироваться в область '"+c.getType().getName()+"'!";
					}
					if(c!=null && !c.getPlace().getPlaceType().getTeleportPermissions().isIncoming()) {
						return "Вы не можете телепортироваться на клетку '"+c.getPlace().getName()+"'!";
					}
					if(!character.isAbleToTeleport()) {
						return character.getUnableToTeleportDescription();
					}
					return "Вам требуется как минимум  "+Spell.TELEPORT.getModifiedCost(character)+" ауры, чтобы сотворить это заклинание!";
				}
			};
	
	private String name;
	private String description;
	private String useInstructions;
	private Colour colour;
	
	private MapTravelType(String name, String description, String useInstructions, Colour colour) {
		this.name = name;
		this.description = description;
		this.useInstructions = useInstructions;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getUseInstructions() {
		return useInstructions;
	}

	public Colour getColour() {
		return colour;
	}
	
	public abstract boolean isAvailable(Cell c, GameCharacter character);

	public abstract String getUnavailablilityDescription(Cell c, GameCharacter character);
}
