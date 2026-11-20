package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueMound {
	
	private static String getDescriptionFinisher(SexAction action, int variation) {
		if(!action.isTargetedCharacterInanimate()) {
			if(variation==1) {
				return " как [npc2.name] [npc2.moan+] под [npc.herHim].";
			}
			return " как [npc2.she] [npc2.moan+] под [npc.herHim].";
		}
		return ".";
	}
	
	public static final SexAction MOUND_SNOG = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубые поцелуи в лоно";
		}

		@Override
		public String getActionDescription() {
			return "Грубо целовать бесполое лоно [npc2.namePos].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] грубо [npc.verb(grind)] [npc.her] [npc.lips+] вниз, прижимаясь к кукольному лону [npc2.namePos], с силой целуя и облизывая чувствительную промежность [npc2.her]"+getDescriptionFinisher(this, 0),

						"Опустив голову [npc.her] в промежность [npc2.namePos], [npc.name] грубо [npc.verb(grind)] прижимает рот [npc.her] к бесполому лону [npc2.her], целуя и облизывая чувствительное лоно [npc2.her]"+getDescriptionFinisher(this, 1),

						"Вжимая лицо [npc.her] в пах [npc2.namePos], [npc.name] [npc.verb(start)] втирает [npc.her] [npc.lips+] в бесполое лоно [npc2.her], грубо целуя и облизывая чувствительную промежность [npc2.her]"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Целовать лоно";
		}

		@Override
		public String getActionDescription() {
			return "Страстно целовать и облизывать бесполое лоно [npc2.namePos].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] с жадностью [npc.verb(press)] [npc.her] [npc.lips+] вниз, прижимаясь к кукольному лону [npc2.namePos], страстно целуя и облизывая чувствительную промежность [npc2.her]"+getDescriptionFinisher(this, 0),

					"Опустив голову [npc.her] в пах [npc2.namePos], [npc.name] страстно [npc.verb(press)] [npc.her] губами к [npc2.her] бесполому паху, целуя и вылизывая [npc2.her] чувствительный холмик"+getDescriptionFinisher(this, 1),

					"Вжимаясь лицом [npc.her] в пах [npc2.namePos], [npc.name] [npc.verb(start)] прижимая [npc.her] [npc.lips+] к [npc2.her] бесполому холмику, жадно целуя и вылизывая [npc2.her] чувствительный пах"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction GENTLE_MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Целовать лоно";
		}

		@Override
		public String getActionDescription() {
			return "Нежно целовать и вылизывать бесполый холмик [npc2.namePos].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] нежно [npc.verb(press)] [npc.her] [npc.lips+] вниз к кукольному холмику [npc2.namePos], оставляя деликатные поцелуи на чувствительном паху [npc2.her]"+getDescriptionFinisher(this, 0),

					"Опустив голову [npc.her] в пах [npc2.namePos], [npc.name] нежно [npc.verb(press)] [npc.her] губами к [npc2.her] бесполому паху, целуя и вылизывая [npc2.her] чувствительный холмик"+getDescriptionFinisher(this, 1),

					"Вжимаясь лицом [npc.her] в пах [npc2.namePos], [npc.name] [npc.verb(start)] мягко прижимая [npc.her] [npc.lips+] к [npc2.her] бесполому холмику, нежно целуя и вылизывая [npc2.her] чувствительный пах"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_SNOG_SUB_EAGER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Трусситься о холмик";
		}

		@Override
		public String getActionDescription() {
			return "Страстно трусситься о бесполый пах [npc2.namePos].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] с жадностью [npc.verb(press)] [npc.her] [npc.lips+] вниз, прижимаясь к кукольному лону [npc2.namePos], страстно целуя и облизывая чувствительную промежность [npc2.her]"+getDescriptionFinisher(this, 0),

					"Опустив голову [npc.her] в пах [npc2.namePos], [npc.name] страстно [npc.verb(press)] [npc.her] губами к [npc2.her] бесполому паху, целуя и вылизывая [npc2.her] чувствительный холмик"+getDescriptionFinisher(this, 1),

					"Вжимаясь лицом [npc.her] в пах [npc2.namePos], [npc.name] [npc.verb(start)] прижимая [npc.her] [npc.lips+] к [npc2.her] бесполому холмику, жадно целуя и вылизывая [npc2.her] чувствительный пах"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_KISSING_SUB_NORMAL = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Целовать лоно";
		}
	
		@Override
		public String getActionDescription() {
			return "Нежно целовать и лизать кукольную промежность [npc.namePos].";
		}
	
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}
	
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(press)] [npc.her] [npc.lips+] вниз, прижимаясь к кукольному холмику [npc2.namePos], целуя и лизажа чувствительную промежность [npc2.her]"+getDescriptionFinisher(this, 0),

					"Опуская голову [npc.her] в промежность [npc2.namePos], [npc.name] [npc.verb(press)] прижимая рот [npc.her] к бесполому лону [npc2.her], целуя и лижа чувствительный холмик [npc2.her]"+getDescriptionFinisher(this, 1),

					"Вжимая лицо [npc.her] в пах [npc2.namePos], [npc.name] [npc.verb(start)] прижимая [npc.her] к бесполому холмику [npc.lips+], целуя и лижа чувствительную промежность [npc2.her], при этом [npc2.her]"+getDescriptionFinisher(this, 0));
		}
	};
}
