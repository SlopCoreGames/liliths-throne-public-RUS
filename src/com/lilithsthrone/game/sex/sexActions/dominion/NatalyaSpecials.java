package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class NatalyaSpecials {

	public static final SexAction PARTNER_DEMAND_FACIAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDemandedFacial)
					&& Main.game.getNpc(Natalya.class).getArousal()>=ArousalLevel.FOUR_PASSIONATE.getMinimumValue();
		}
		@Override
		public String getActionTitle() {
			return "Требовать минет на лицо";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getDescription() {
			return "Дыхание Натальи становится всё чаще и тяжелее, и пока её [natalya.cockGirth], звериный член дико пульсирует в твоём [pc.hands], она отчаянно скребёт землю одной из передних копыт и вскрикивает: [natalya.speechNoEffects(Да! ~Ох!~ Я уже близко! ~Аах!~ В награду ты должен... ~Ох!~ ...ты должен принять мою загрузку прямо на своё лицо! ~Ммм!~)]";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDemandedFacial, true);
		}
	};
	
	public static final SexAction PLAYER_PREPARE_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Приготовиться ([style.colourMinorGood(минет на лицо)])";
		}
		@Override
		public String getActionDescription() {
			return "[style.colourMinorGood(Исполнить приказ Натальи)] и подставить свой [pc.face] под её лошадиный член, готовясь принять минет на лицо.";
		}
		@Override
		public String getDescription() {
			return "Издавая похотливый стон, Наталья вскрикивает: [natalya.speechNoEffects(Да! Я почти... ~Ох!~ ...почти кончаю! Делай, как я велю, и прими мою загрузку на лицо!)]<br/><br/>Пока [natalya.cockGirth] член [natalya.race] дико пульсирует в твоём [pc.hands], ты делаешь то, что тебе велели, и ерзаешь прямо перед ним. Продолжая растирать мускусистую предэякуляцию своей Госпожи вверх и вниз по всей длине её горячего ствола, ты направляешь раздутую головку прямо себе в лицо, готовясь получить свою награду...";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerReceivedNatalyaFacial, true);
		}
		@Override
		public int getActionRenderingPriority() {
			return 1;
		}
	};
	
	public static final SexAction PLAYER_PREPARE_NO_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
						|| (Main.game.getNpc(Natalya.class).getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Приготовиться ([style.colourMinorBad(пол)])";
		}
		@Override
		public String getActionDescription() {
			return "[style.colourMinorBad(Отказаться исполнять приказ Натальи)] и вместо того, чтобы принять минет на лицо, направить её член в сторону, заставляя её кончить прямо на пол.";
		}
		@Override
		public String getDescription() {
			return "Издавая похотливый стон, Наталья вскрикивает: [natalya.speechNoEffects(Рабыня! Я почти... ~Ох!~ ...почти кончаю! Делай, как я велю, и прими мою порцию на свое лицо!)]<br/><br/>Пока [natalya.cockGirth] член [natalya.race] дико пульсирует в твоей [pc.hands], ты решаешь, что не хочешь беспрекословно подчиняться, и вместо этого смещаешься в сторону. Продолжая растирать мускусистую предэякуляцию своей Госпожи вверх и вниз по всей длине ее горячего ствола, ты направляешь расширенную головку прочь от себя, готовясь к тому, что она выплеснет всю свою порцию прямо на пол...";
		}
		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerReceivedNatalyaFacial, false);
		}
	};
}
