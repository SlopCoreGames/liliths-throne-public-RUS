package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.7.7
 * @version 0.4.2.2
 * @author Innoxia
 */
public class SMDominionExpressEncounter extends SexManagerDefault {
	
	private Map<GameCharacter, SexType> preferences;
	private Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap;
	
	public SMDominionExpressEncounter(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			Map<GameCharacter, SexType> preferences,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		super(position,
				dominants,
				submissives);
		this.preferences = preferences;
		this.exposeAtStartOfSexMap = exposeAtStartOfSexMap;
	}
	
	@Override
	public boolean isPublicSex() {
		return true;
	}
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(!Main.sex.isDom(character)) {
			return SexControl.ONGOING_ONLY; // So the player can't start anything else.
		}
		return super.getSexControl(character);
	}
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
		return clothingToEquip.isCondom();
	}
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return true;
	}
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return exposeAtStartOfSexMap;
	}
	@Override
	public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		return new ArrayList<>();
	}
	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(preferences.containsKey(character)) {
			return preferences.get(character);
		}
		return super.getForeplayPreference(character, targetedCharacter);
	}
	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(!character.isPlayer()) {
			return getForeplayPreference(character, targetedCharacter);
		}
		return character.getMainSexPreference(targetedCharacter);
	}
	@Override
	public String getPublicSexStartingDescription() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION_EXPRESS) {
			return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>Проходящий мимо раб-кентавр оглядывается и ухмыляется, видя, как ты подчиняешься [npc.name]...</p>";
		}
		
		return super.getPublicSexStartingDescription();
	}
	@Override
	public String getRandomPublicSexDescription() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION_EXPRESS) {
			return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+ UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
							UtilText.returnStringAtRandom(
								"Пара кентавров поглядывают на тебя и хихикают, проходя мимо.",
								"Раб-кентавр торопливо пробегает мимо, слишком занятый, чтобы обращать на тебя внимание.",
								"Рабыня-кентавриха торопливо пробегает мимо, слишком занятая, чтобы обращать на тебя внимание.",
								"Группа рабов-кентавров на мгновение останавливается, чтобы прокомментировать твои старания, после чего они продолжают путь к конюшням.",
								"Где-то позади ты слышишь задорный смех кентаврихи.",
								"Где-то позади ты слышишь довольное хрюканье кентавра.",
								"Ты бросаешь взгляд в сторону и видишь, как [style.mule] бронзового ранга с любопытством наблюдает за тем, как ты обслуживаешь кентавров.",
								"Мускулистый раб-кентавр замирает, чтобы прокомментировать твое развратное зрелище, а затем у trots на работу.",
								"Пышногрудая рабыня-кентавриза на мгновение задерживается, чтобы прокомментировать твое развратное зрелище, а затем у trots в сторону своей работы."))
					+"</p>";
			
		}
		return super.getRandomPublicSexDescription();
	}
}
