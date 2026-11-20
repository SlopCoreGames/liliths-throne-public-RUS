package com.lilithsthrone.game.sex.managers.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69.9
 * @version 0.4.2.2
 * @author Innoxia
 */
public class SMKrugerChair extends SexManagerDefault {

	public SMKrugerChair(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.SITTING,
				dominants,
				submissives);
	}
	
	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		return Util.newArrayListOfValues(
				SexPosition.SITTING);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return !character.isPlayer();
	}

	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
		
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
		} else if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
		} else {
			map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
		}
		
		return map;
	}
	
	@Override
	public boolean isPublicSex() {
		return true;
	}

	@Override
	public String getPublicSexStartingDescription() {
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>Львицы и девушки-зебры, сидящие вокруг кабинки, начинают хихикать и ласкать себя, наблюдая за тем, как тебя собираются трахнуть Крюгер...</p>";
	}

	@Override
	public String getRandomPublicSexDescription() {
		List<String> descriptions = Util.newArrayListOfValues(
				"Одна из львиц приподнимает юбку и, отодвинув стринги в сторону, начинает посасывать свой палец, не сводя глаз с тебя и Крюгера.",
				"Две девушки-зебры откидываются на спинку дивана и начинают страстно целоваться.",
				"Ты слышишь, как несколько наблюдающих за тобой львиц начинают отпускать комментарии по поводу твоих стараний.",
				"Ты слышишь эротический стон одной из девушек-зебр, когда она наблюдает за тем, как Крюгер развлекается с тобой.",
				"Девушки, наблюдающие за твоим выступлением, продолжают хихикать и оглашать всё вокруг непристойными комментариями.",
				"Ты бросаешь взгляд в сторону и видишь, как несколько девушек ласкают себя, наблюдая за тем, как ты и Крюгер вовсю трахаетесь.",
				"Одна из девушек-зебр подбадривает тебя, пока вы с Крюгером продолжаете заниматься сексом прямо перед ними.",
				"Одна из львиц хихикает и отпускает пошлую шутку, пока вы с Крюгером продолжаете трахаться перед ней.");
		
		if(Main.sex.getOrificesBeingPenetratedBy(Main.game.getNpc(Kruger.class), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
			descriptions.add("Львица опускается на колени рядом с тобой, а затем, хихикая про себя, прижимает твою голову к члену Крюгера, заставляя тебя глубоко заглотить его [kruger.cock+].");
			descriptions.add("Пара зебродевушек наклоняется к тебе с обеих сторон и, обхватив твою голову, помогает тебе ритмично двигаться вверх-вниз на [kruger.cock+].");
			descriptions.add("Одна из львиц наклоняется вперед и рычит: [genericFemale.speech(Вот так, сучка, знай своё место.)]");
			descriptions.add("Львица наклоняется и с рычанием вжимает твою голову в член Кругера: [genericFemale.speech(Давай, шлюха, ты можешь сосать член получше!)]");
			descriptions.add("Одна из зебродевушек откидывается на спинку дивана и подбадривает: [genericFemale.speech(Давай, Кругер, отымей эту сучку как следует!)]");
			descriptions.add("Зебродевушка прижимается к Кругеру и, ухмыляясь тебе сверху вниз, подзадоривает его: [genericFemale.speech(О да, Кругер, ты поставил эту шлюху на [pc.her] место!)]");
		}
		
		if(Main.sex.getOrificesBeingPenetratedBy(Main.game.getNpc(Kruger.class), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
			descriptions.add("Львица подходит к тебе сзади, берет тебя за плечи и начинает давить вниз, хихикая про себя и заставляя [kruger.cock+] Кругера входить глубоко в твою [pc.pussy+].");
			descriptions.add("Пара зебродевушек делает шаг вперед и, крепко обхватив тебя под [pc.arms], тянут тебя вверх и вниз, помогая [kruger.cock+] Кругера скользить в твою [pc.pussy+] и обратно.");
			descriptions.add("Одна из львиц наклоняется вперед и рычит: [genericFemale.speech(Ты всего лишь похотливая дырка для члена, верно, сучка?)]");
			descriptions.add("Львица наклоняется и, схватив тебя за [pc.hips+], насаживает тебя на член Кругера, рыча: [genericFemale.speech(Давай, шлюха, принимай его <i>глубоко</i>.)]");
			descriptions.add("Одна из зебродевушек откидывается на спинку дивана и подбадривает: [genericFemale.speech(Давай, Кругер, трахни эту сучку как следует!)]");
			descriptions.add("Зебродевушка прижимается к Кругеру и, ухмыляясь тебе, подначивает его: [genericFemale.speech(О да, Кругер, поставь эту шлюху на [pc.her] место!)]");
		}

		if(Main.sex.getOrificesBeingPenetratedBy(Main.game.getNpc(Kruger.class), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
			descriptions.add("Львица подходит к тебе сзади, берет тебя за плечи и сдавленно смеясь, надавливает вниз, заставляя [kruger.cock+] Кругера глубоко входить в твою [pc.asshole+].");
			descriptions.add("Пара зебродевушек выходит вперед и встает по обе стороны от тебя; крепко обхватив тебя под [pc.arms], они начинают двигать тобой вверх и вниз, помогая [kruger.cock+] Кругера скользить в твою [pc.asshole+].");
			descriptions.add("Одна из львиц наклоняется вперед и рычит: [genericFemale.speech(Ты просто грязная заднеприводная шлюха, верно, сучка?)]");
			descriptions.add("Львица наклоняется вперед, берет тебя за [pc.hips+] и с рычанием натягивает на член Кругера: [genericFemale.speech(Давай же, грязная заднеприводная шлюха, принимай его <i>глубоко</i>.)]");
			descriptions.add("Одна из зебродевушек откидывается на спинку дивана и подбадривает: [genericFemale.speech(Давай, Кругер, хорошенько оттрахай эту сучью задницу!)]");
			descriptions.add("Зебродевушка прижимается к Кругеру и, ухмыляясь тебе, подначивает его: [genericFemale.speech(О да, Кругер, поставь эту грязную заднеприводную шлюху на [pc.her] место!)]");
		}
		
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+Util.randomItemFrom(descriptions)
				+"</p>";
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kruger.class))>=2
				&& (Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=1 || Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kruger.class))>=5);
	}
}
