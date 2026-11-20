package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.3.4.5
 * @version 0.3.4.5
 * @author Innoxia
 */
public enum CombatBehaviour {
	
	BALANCED("balanced",
			"Прикажите [npc.name] использовать любые боевые приемы, которые [npc.she] сочтет наиболее подходящими.",
			"[npc.NameIsFull] использует любые боевые приемы, которые [npc.she] сочтет наиболее подходящими."),
	
	ATTACK("attack",
			"Прикажите [npc.name] действовать агрессивнее, отдавая предпочтение атакам основным оружием и наступательным заклинаниям перед любыми защитными способностями.",
			"Везде, где это возможно, [npc.name] будет переходить в наступление, предпочитая наносящие урон атаки защитным способностям."),
	
	DEFEND("defend",
			"Прикажите [npc.name] действовать более оборонительно, отдавая предпочтение способностям, предотвращающим входящий урон.",
			"Везде, где это возможно, [npc.name] будет переходить к обороне, отдавая предпочтение использованию способностей, предотвращающих входящий урон."),
	
	SEDUCE("seduce",
			"Прикажите [npc.name] вместо использования наносящих урон заклинаний или атак оружием начать соблазнять [npc.her] противников.",
			"Везде, где это возможно, [npc.name] будет сосредоточена на соблазнении [npc.her] противников вместо использования атакующих или защитных способностей."),
	
	SPELLS("spells",
			"Прикажите [npc.name] сосредоточиться на использовании всех заклинаний, которые [npc.sheHasFull] [npc.her] распоряжении.",
			"Везде, где это возможно, [npc.name] будет сосредоточена на использовании заклинаний, которые [npc.sheHasFull] [npc.her] распоряжении."),
	
	SUPPORT("support",
			"Прикажите [npc.name] сосредоточиться на использовании заклинаний или других способностей, поддерживающих [npc.her] союзников.",
			"По возможности, [npc.name] будет стараться использовать заклинания или другие способности, поддерживающие [npc.her] союзников.");

	String name;
	String orderDescription;
	String description;
	
	private CombatBehaviour(String name, String orderDescription, String description) {
		this.name = name;
		this.orderDescription = orderDescription;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getOrderDescription(GameCharacter character) {
		return UtilText.parse(character, orderDescription);
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

}
