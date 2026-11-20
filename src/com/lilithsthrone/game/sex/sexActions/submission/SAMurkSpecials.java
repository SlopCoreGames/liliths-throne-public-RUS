package com.lilithsthrone.game.sex.sexActions.submission;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class SAMurkSpecials {

	/**
	 * Special action for Murk preparing for the player's orgasm.
	 */
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getCharacterOrgasming()!=null
					&& Main.sex.getCharacterOrgasming().isPlayer()
					&& Main.game.getPlayer().isCaptive();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getActionTitle() {
			return "Подбодрить доярку";
		}
		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Подбодри [npc2.herHim], чтобы она кончила для тебя.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.MOUTH)) {
				return UtilText.returnStringAtRandom(
						"Чувствуя, что ты вот-вот кончишь от того, как его толстый, пульсирующий член трахает тебе лицо, Мёрк грубо вонзает свой темный ствол глубоко тебе в глотку и хрипит,",
						"Понимая, что ты вот-вот кончишь только от того, что сосёшь ему член, Мёрк ускоряется и яростно вколачивает свой горячий член глубоко тебе в глотку, хрипя,",
						"Зная, что ты вот-вот достигнешь пика, Мёрк продолжает безжалостно трахать твою глотку, хрипя,")
						+ " [murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"Вот так, сучка! ~Ммм!~ Кончай от того, как мой толстый, вонючий член трахает твою глотку!",
								"Давай же, похотливая сучка! ~Аах!~ Пусть вкус моего толстого, вонючего члена доставит тебе оргазм!",
								"Ты что, собралась кончить просто от того, что сосёшь мой толстый, вонючий член?! ~Ммм!~ Ну же, сучка! Кончай для меня!")
						+")]";
				
			} else if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"Чувствуя, как твоя [pc.pussy+] начинает неконтролируемо сжиматься вокруг его толстого, пульсирующего члена, Мёрк грубо вонзает свой темный ствол целиком в твою текущую дырочку и хрипит,",
						"Понимая по диким спазмам твоей киски, что ты вот-вот кончишь, Мёрк ускоряется и яростно вколачивает свой горячий член глубоко в твою [pc.pussy+], хрипя,",
						"Когда твоя [pc.pussy+] начинает неистово сжимать жирный, [murk.cockColour] член Мёрка, пухлый крысомальчик понимает, что ты вот-вот достигнешь оргазма, и глубоко рычит:")
						+ " [murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"Вот так, сучка! ~Ммм!~ Кончай от того, как мой жирный, вонючий член трахает твою уродскую киску!",
								"Давай же, похотливая сучка! ~Аах!~ Пусть ощущение моего жирного, вонючего члена доставит твоей уродской киске оргазм!",
								"Твоя уродская киска сейчас кончит от того, что мой жирный, вонючий член долбит её?! ~Ммм!~ Ну же, сучка! Кончай для меня!")
						+")]";
				
			} else if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.ANUS)) {
				return UtilText.returnStringAtRandom(
						"Чувствуя, как твоя [pc.asshole+] начинает бесконтрольно сжиматься вокруг его жирного, пульсирующего члена, Мёрк грубо вбивает свой темный ствол до упора в твою мокрую дырочку и рычит:",
						"Понимая по диким спазмам твоего ануса, что ты вот-вот кончишь, Мёрк ускоряется и яростно вколачивает свой горячий член глубоко в твою [pc.asshole+], тяжело рыча:",
						"Когда твоя [pc.asshole+] начинает неистово сжимать жирный, [murk.cockColour] член Мёрка, пухлый крысомальчик понимает, что ты вот-вот достигнешь оргазма, и глубоко рычит:")
						+ " [murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"Вот так, сучка! ~Ммм!~ Кончай от того, как мой жирный, вонючий член трахает твою задницу!",
								"Давай же, похотливая заднице-сучка! ~Аах!~ Пусть ощущение моего жирного, вонючего члена доставит твоей попке оргазм!",
								"Ты что, сейчас кончишь от того, что мой жирный, вонючий член долбит твою задницу?! ~Ммм!~ Ну же, заднице-сучка! Кончай для меня!")
						+")]";
				
			} else {
				return "Когда Мёрк понимает, что ты вот-вот достигнешь оргазма, он грубо вжимается в тебя и скалится: [murk.speechNoEffects(Ну же, сучка! Кончай для меня, блядь!)]"; //This should never be reached.
			}
		}
	};
}
