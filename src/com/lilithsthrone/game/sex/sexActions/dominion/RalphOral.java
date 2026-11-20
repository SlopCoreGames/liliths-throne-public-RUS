package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMRalphDiscountBig;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.64
 * @version 0.2.8
 * @author Innoxia
 */
public class RalphOral {

	// Player actions:

	public static final SexAction PLAYER_START_ORAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать сосать";
		}

		@Override
		public String getActionDescription() {
			return "Взять головку гигантского конского члена Ralph в рот.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyOngoingActionHappening()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Поскольку член Ralph настолько огромен, тебе даже не нужно наклоняться вперед, чтобы начать получать свою скидку. Просто открыв рот, ты опускаешь губы, чтобы поцеловать расширенную головку его лошадиного ствола.");
			
			if(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM))
				UtilText.nodeContentSB.append(" Когда кончик твоего языка скользит по его бугристой лошадиной уретре, в рот попадает капля соленой предэякулята. Ты издаешь удивленный стон, когда соленая жидкость стекает по твоим губам.");
			else
				UtilText.nodeContentSB.append(" Когда кончик твоего языка скользит по его бугристой лошадиной уретре, ты чувствуешь жар его звериного члена, исходящий прямо в лицо. Ты издаешь тихий стон в предвкушении того, как будешь сосать его восхитительный член.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append(", лишь слишком поздно вспомнив, что по ту сторону прилавка стоит покупатель!<br/>Стремясь искупить свою ошибку, ты быстро подаешься вперед, широко раскрывая челюсть и принимая горячий, пульсирующий член в рот. В спешке ты издаешь еще больше шума, и слышишь, как Ralph поспешно предлагает покупателю часть твоей скидки, чтобы сохранить это в секрете.");
			else
				UtilText.nodeContentSB.append(", который быстро приглушается, когда ты широко раскрываешь челюсть и берешь горячий, пульсирующий член в рот.");
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
			
	public static final SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Затаиться";
		}

		@Override
		public String getActionDescription() {
			return "Сиди совершенно неподвижно и не издавай ни звука. Это не даст покупателю за прилавком понять, что ты здесь.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.customerAtCounter;
		}

		@Override
		public String getDescription() {
			return "Ты сидишь совершенно неподвижно, изо всех сил стараясь не издавать ни звука, пока Ralph заканчивает возиться с клиентом над тобой. С твоих растянутых губ стекает склизкая струйка слюны и предэякулята, и ты бесшумно подаешься вперед чуть сильнее, пытаясь протолкнуть эту жидкость обратно в глотку с помощью члена Ralph.";
		}
	};
	
	public static final SexAction PLAYER_STAY_QUIET_TEASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Тихо дразнить";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить член Ralph, пытаясь заставить его потерять самообладание на глазах у клиента.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.customerAtCounter;
		}

		@Override
		public String getDescription() {
			return "Когда Ralph заканчивает возиться с клиентом над тобой, ты решаешь немного развлечься. Отстранившись, ты нежно проводишь языком вокруг расширенной головки его лошадиного члена, дразня кончик уретры. С твоих растянутых губ стекает склизкая струйка слюны и предэякулята, и ты тянешься вверх, чтобы размазать естественную смазку вверх и вниз по его стволу, мягко поглаживая яички, пока продолжаешь дразнить головку языком. Ты озорно ухмыляешься, слыша, как Ralph изо всех сил старается сохранить невозмутимость перед клиентом, и когда он тактично просит клиента посмотреть на другую сторону лавки, он с силой вгоняет свой член тебе в глотку, давая понять, что твоя маленькая игра его явно не забавляет...";
		}
	};
	
	public static final SexAction PLAYER_SUCK_COCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Сосать член";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать сосать член Ralph.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Ты продолжаешь ритмично кивать головой вперед-назад, заталкивая широкую, расширенную головку лошадиного члена Ralph глубоко себе в глотку. Его толстый черный ствол блестит и лоснится под светом лавки, пока твои губы размазывают скользкую смесь предэякулята и слюны по всей его длине.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append(" В твоей тесной каморке эхом отдаются похотливые хлюпающие звуки, которые в сочетании со скрипом половиц под твоими раскачивающимися движениями создают удивительно много шума. Ты так увлеклась сосанием этого восхитительного, жирного члена перед собой, что совершенно забыла о покупателе наверху, и слышишь, как Ralph отчаянно предлагает ему часть твоей скидки, пытаясь подкупить его, чтобы тот не шумел.");
			else
				UtilText.nodeContentSB.append(" Каждый раз, когда ты подаешься вперед, ты чувствуешь, как Ralph слегка подбрасывает бедрами, помогая вбивать свой гигантский ствол в твое отверстие для минета. В твоей тесной каморке эхом отдаются похотливые хлюпающие звуки, которые в сочетании со скрипом половиц под твоими раскачивающимися движениями создают удивительно много шума, и ты рада, что поблизости нет покупателей, которые могли бы тебя услышать.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
	
	public static final SexAction PLAYER_FONDLE_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Ласкать яички";
		}

		@Override
		public String getActionDescription() {
			return "Удели немного внимания яичкам Ralph.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Пока ты сосешь горячий черный конский член Ralph, ты вспоминаешь его недавние слова о том, что стоит уделить внимание его яйцам. Не желая его разочаровывать, ты — довольно неуклюже, учитывая почти полное отсутствие места для маневра — тянешься вверх к тяжелому мешочку, свисающему у основания его пульсирующего ствола. Ты нежно растираешь и сжимаешь его огромные, полные спермы яички, и ловишь себя на том, что невольно издаешь приглушенный стон, представляя, как они опустошаются прямо тебе в глотку.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>Сочетания твоих скованных, шаркающих движений и не самого тихого стона, который ты только что издала, оказывается достаточно, чтобы привлечь внимание клиента, стоящего прямо по ту сторону прилавка. Ты чувствуешь легкий укол разочарования, слыша, как Ralph отдает часть твоей скидки, лишь бы заставить их замолчать.");
			else
				UtilText.nodeContentSB.append(" Ты слышишь, как Ralph начинает ободряюще стонать над тобой, и продолжаешь ласкать и поглаживать его приятные тяжелые яйца, пока его соразмерный им член скользит в твоем рту и обратно.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
	
	public static final SexAction PLAYER_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Глубокий минет";
		}

		@Override
		public String getActionDescription() {
			return "Посмотри, как хорошо твоя глотка принимает конский член.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Собрав всю свою смелость, вы решаете попытаться заглотить как можно большую часть члена Ralph. Подавшись вперед, вы прижимаетесь к его паху, расслабляя горло и раскрывая пищевод, пытаясь проглотить толстую, горячую плоть его лошадиного достоинства. Обладая стальными нервами, вы толкаете голову вперед, с силой заталкивая всю длину его гигантского члена себе в глотку. Когда вы начинаете отстраняться, густая струйка вязкой слюны стекает по вашему подбородку, и, видя, что более половины его длины уже покидает ваш рот, вы решаете повторить всё заново и снова устремляетесь вперед, в пах Ralph. Благодаря обильному выделению слюны, его звериный, похожий на лошадиный ствол снова легко входит в ваш рот, и вы заглатываете его до самого основания, удерживая внутри так долго, как только можете, пока не начинаете задыхаться и не вынуждены отстраниться, чтобы лишь на мгновение перевести дух, прежде чем быстро вернуться к сосанию в обычном темпе.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>Громкие хлюпающие звуки в сочетании со скрипом половиц, когда вы раскачивались взад-вперед, глубоко заглатывая огромный лошадиный член, в итоге подняли немало шума. Вы слышите, как Ralph предлагает скидку вашему «дисконту» клиенту, который только что подслушал ваше пылкое действо, и издаете приглушенный вздох, осознавая, что совсем забыли об их присутствии.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};

	public static final SexAction PLAYER_BIG_DISCOUNT = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Большая скидка";
		}
		@Override
		public String getActionDescription() {
			return "Спросить у Ralph, можно ли получить еще большую скидку. <i style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Это предложение для Ralph оттрахать твою киску!</i>";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !SexFlags.askedForBigDiscount;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Пока ты сосала член Ralph, глубоко внизу живота у тебя начало разгораться отчаянное желание. Тебе хочется почувствовать его внутри себя. Его толстый, звериный член, вколачивающийся в твою киску, прежде чем наполнить тебя своим мощным семенем... Ты больше не можешь сдерживаться и, полностью выскользнув из твоего рта, его член, ты подаешься вперед, чтобы посмотреть на него своими большими невинными глазами.<br/>"
					+ UtilText.parseSpeechNoEffects("«Может быть, есть способ получить еще большую скидку?»", Main.game.getPlayer())+" — спрашиваешь ты своим самым убедительным соблазнительным тоном.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>Ты слышишь, как покупатель по другую сторону прилавка издает веселый возглас удивления, и Ralph поспешно предлагает ему часть твоей скидки, чтобы тот не шумел.");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
			
			SexFlags.askedForBigDiscount=true;

			Map<AbstractClothing, DisplacementType> clothingTouched = Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_PREGNANCY);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			}
		}
	};
	
	public static final SexAction PLAYER_ANAL_BIG_DISCOUNT = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Большая скидка";
		}
		@Override
		public String getActionDescription() {
			return "Спросить у Ralph, можно ли получить еще большую скидку. <i style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>Это предложение для Ralph оттрахать твою задницу!</i>";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !SexFlags.askedForBigDiscount;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Пока ты сосала член Ralph, глубоко внизу живота у тебя начало разгораться отчаянное желание. Тебе хочется почувствовать его внутри себя. Его толстый, звериный член, вколачивающийся в твою задницу, прежде чем наполнить тебя своим мощным семенем... Ты больше не можешь сдерживаться и, полностью выскользнув из твоего рта, его член, ты подаешься вперед, чтобы посмотреть на него своими большими невинными глазами.<br/>"
					+ UtilText.parseSpeechNoEffects("«Может быть, есть способ получить еще большую скидку?»", Main.game.getPlayer())+" — спрашиваешь ты своим самым убедительным соблазнительным тоном.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>Ты слышишь, как покупатель по другую сторону прилавка издает веселый возглас удивления, и Ralph поспешно предлагает ему часть твоей скидки, чтобы тот не шумел.");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter) {
				SexFlags.alertedCustomer=true;
			}
			
			SexFlags.askedForBigDiscount=true;
			
			Map<AbstractClothing, DisplacementType> clothingTouched = Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ANAL_RECEIVING);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};

	public static final SexAction PLAYER_TAKE_IT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Принимай";
		}

		@Override
		public String getActionDescription() {
			return "Ты не в том положении, чтобы делать что-то еще, кроме как лежать и позволять массивному конскому члену Ralph вколачиваться в твою киску.";
		}

		@Override
		public String getDescription() {
				return "Сильные руки Ralph крепко сжимают твои "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедра, пока он долбит в твою киску, и ты понимаешь, что тебе остается лишь лежать смирно и принимать это. Издавая похотливые стоны, ты подкладываешь руки под голову, используя их вместо подушки, пока твоя киска приносит тебе дополнительную скидку.";
		}
		
	};
	
	public static final SexAction PLAYER_TAKE_IT_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Принимай";
		}

		@Override
		public String getActionDescription() {
			return "Ты не в том положении, чтобы делать что-то еще, кроме как лежать и позволять массивному конскому члену Ralph вколачиваться в твою задницу.";
		}

		@Override
		public String getDescription() {
			return "Сильные руки Ralph крепко сжимают твои "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедра, пока он долбит в твою задницу, и ты понимаешь, что тебе остается лишь лежать смирно и принимать это. Издавая похотливые стоны, ты подкладываешь руки под голову, используя их вместо подушки, пока твоя задница приносит тебе дополнительную скидку.";
		}
		
	};

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "Теперь, когда член Ralph использует другое отверстие, ты можешь подбодрить его парой слов.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Поскольку доминирующая хватка Ralph не дает тебе возможности предпринять какие-либо смелые действия, тебе остается лишь подбодрить его словами. Повернув голову назад и демонстративно прикусив губу, ты умоляешь: ");
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ммм! Моя киска вся твоя!"));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ах, да! Оплодоверяй меня, как свою маленькую кобылку!"));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ах! Ralph! Т-твой член такой огромный!"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("А-ах! Да! Трахни меня!"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_DIRTY_TALK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "Теперь, когда член Ralph использует другое отверстие, ты можешь подбодрить его парой слов.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Поскольку доминирующая хватка Ralph не дает тебе возможности предпринять какие-либо смелые действия, тебе остается лишь подбодрить его словами. Повернув голову назад и демонстративно прикусив губу, ты умоляешь: ");
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ммм! Моя попка вся твоя!"));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ах, да! Я твоя маленькая кобылка!"));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ах! Ralph! Т-твой член такой огромный!"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("А-ах! Да! Трахни меня!"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	// Partner actions:

	public static final SexAction PARTNER_CUSTOMER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTurn() - SexFlags.customerTurnAppearance >= 3; // Have 2 turns of non-customer at minimum
		}
		@Override
		public String getActionTitle() {
			return "Покупатель!";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return "Ralph внезапно перестает плавно двигать бедрами, и прежде чем ты успеваешь понять, что случилось, ты слышишь, как он приветствует покупателя. Ты слышишь тяжелые шаги клиента, который подходит почти к самому краю прилавка, и понимаешь: если ты не будешь вести себя тихо, он обнаружит, что ты здесь прячешься!<br/><br/><b>Там</b> <b style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>покупатель</b> <b>на другой стороне прилавка!</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = true;
			SexFlags.customerTurnAppearance = Main.sex.getTurn();
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			
			UtilText.nodeContentSB.append("Ты чувствуешь, как член Ralph слегка отходит назад, когда он наклоняется к тебе, ");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Вот так, продолжай заслуживать свою скидку!", Main.game.getNpc(Ralph.class)));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ах... У тебя это отлично получается!", Main.game.getNpc(Ralph.class)));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Только не забывай вести себя потише!", Main.game.getNpc(Ralph.class)));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Продолжай, ты ~ммм~ просто великолепно справляешься!", Main.game.getNpc(Ralph.class)));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK_VAGINAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			
			UtilText.nodeContentSB.append("Ты чувствуешь, как рука Ralph спускается ниже, чтобы ухватить и сжать твою "+Main.game.getPlayer().getAssSize().getDescriptor()+" задницу, пока он стонет тебе в пах: ");
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Поверить не могу, что ты сама об этом попросила!", Main.game.getNpc(Ralph.class)));
						break;
					case 1:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Ах, да! Блядь...", Main.game.getNpc(Ralph.class)));
						break;
					case 2:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Ах, сука! Твоя киска просто охеренная!", Main.game.getNpc(Ralph.class)));
						break;
					default: 
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Бля! Ты реально заслужила эту скидку, а?", Main.game.getNpc(Ralph.class)));
						break;
				}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Грязные разговоры";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			
			UtilText.nodeContentSB.append("Ты чувствуешь, как рука Ralph спускается ниже, чтобы ухватить и сжать твою "+Main.game.getPlayer().getAssSize().getDescriptor()+" задницу, пока он стонет тебе в пах: ");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Поверить не могу, что ты меня на это уговорила!", Main.game.getNpc(Ralph.class)));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ах, да! Блядь...", Main.game.getNpc(Ralph.class)));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ах, бля! Это чертовски хорошо!", Main.game.getNpc(Ralph.class)));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ты реально заслуживаешь эту скидку, а?", Main.game.getNpc(Ralph.class)));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_COMMAND_START_ORAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Потребовать оральный секс";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyNonSelfOngoingActionHappening();
		}

		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Ralph.class))!=PARTNER_COMMAND_START_ORAL) {
				return SexActionPriority.HIGH;
			} else {
				return  SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			
			UtilText.nodeContentSB.append("Вы слышите нетерпеливое ворчание Ralph, когда он наклоняется к вам, "
					+ UtilText.parseSpeech("Что ты делаешь? Ты согласилась на это, так что не заставляй меня ждать!", Main.game.getNpc(Ralph.class)));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}

	};
	
	public static final SexAction PARTNER_START_ORAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Начать минет";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Ralph.class))==PARTNER_COMMAND_START_ORAL) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			
			UtilText.nodeContentSB.append("Вы слышите очень раздраженное ворчание Ralph, когда он наклоняется к вам, "
					+ UtilText.parseSpeech("Я покажу тебе, с чего нужно начинать!", Main.game.getNpc(Ralph.class))
					+"<br/>Прежде чем вы успеваете что-либо ответить, он внезапно подается бедрами вперед. Его твердый член быстро отбрасывает вашу голову назад, на столешницу позади вас, и прежде чем вы успеваете осознать происходящее, расширенная головка его лошачьего органа с силой втирается в ваше лицо. Быстро сменив положение и сделав еще один решительный толчок, вы чувствуете, как ваши губы размыкаются, когда Ralph вбивает свой жаждущий конский член вам в рот. Осознав, что это происходит на самом деле, вы наконец начинаете делать то, о чем договорились, и принимаетесь сосать член Ralph.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(SexFlags.customerAtCounter) {
				return "Ублажить клиента (Получить минет)";
			}
			return "Получить минет";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			}
			
			UtilText.nodeContentSB.append("Ralph остается почти неподвижным, приглядывая за лавкой, пока ты усердно сосешь его член. Время от времени он слегка подается бедрами вперед, помогая тебе заглатывать его массивный ствол, но в остальном тебе приходится справляться почти без помощи, стараясь доставить его члену удовольствие.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE_VAGINAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Вставить член";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Постоянные толчки между твоих ног начинают замедляться, и ты слегка поворачиваешь голову, оглядываясь, чтобы понять, что не так. Когда Ralph улыбается тебе в ответ, ты замечаешь, что его грудь тяжело вздымается, и понимаешь, что он просто на мгновение замедлился, чтобы перевести дух. Полностью погрузив свой член в твою жадную маленькую киску, он сжимает и ощупывает твою "+Main.game.getPlayer().getAssSize().getDescriptor()+" задницу, и ты откидываешь голову на руки, стоная и вздыхая, пока вы оба пытаетесь прийти в себя.<br/><br/>Не прошло и минуты, как ты чувствуешь, как широкая, раздутая головка его лошачьего члена выскальзывает из твоей щели, и когда ты издаешь жалкий скулящий звук от ощущения пустоты, Ralph крепче сжимает твои бедра и резко толкается вперед, превращая твой скулеж в высокий визг удовольствия, когда он снова начинает трахать тебя на полной скорости.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Вставить член";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Постоянные толчки в твой задний проход начинают замедляться, и ты слегка поворачиваешь голову, чтобы оглянуться и понять, в чем дело. Когда Ralph улыбается тебе в ответ, ты замечаешь, что его грудь тяжело вздымается, и понимаешь, что он просто на мгновение замедлился, чтобы перевести дух. Полностью погрузив свой член в твою жадную дырочку, он сжимает и ощупывает твою "+Main.game.getPlayer().getAssSize().getDescriptor()+" задницу, и ты откидываешь голову на руки, стоная и вздыхая, пока вы оба пытаетесь прийти в себя.<br/><br/>Не проходит и минуты, как ты чувствуешь, как широкая, раздутая головка его лошачьего члена выскальзывает из твоего заднего входа, и когда ты издаешь жалкий тонкий скулеж от ощущения пустоты, Ralph крепче сжимает твои бедра и делает мощный толчок вперед, превращая твой скулеж в отчаянный крик наслаждения, когда он снова начинает трахать тебя на полной скорости.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(SexFlags.customerAtCounter) {
				return "Обслужить клиента (Глубокий горловой)";
			}
			return "Глубокий минет";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
			
			UtilText.nodeContentSB.append("В ушах отдается легкий звон — единственный покупатель в лавке только что ушел, оставив вас наедине с Ralph. Последствия этого становятся очевидны мгновенно: вы внезапно чувствуете сильное давление по обе стороны головы. Подняв взгляд, вы видите, что Ralph обхватил вашу голову обеими руками, и прежде чем вы успеваете среагировать, он делает шаг вперед, прижимая вас к своему паху и с силой вонзая свой огромный конский член глубоко вам в глотку. В глазах все плывет, из глаз катятся слезы, и как раз в тот момент, когда вам кажется, что вы вот-вот потеряете сознание от нехватки кислорода, Ralph отпускает вас. Опустившись на колени, вы на мгновение выпускаете огромный ствол изо рта, кашляя и захлебываясь; густая струйка слюны и предэякулята стекает по вашему подбородку, пока вы пытаетесь прийти в себя. И именно в этот момент колокольчик звенит снова, и Ralph делает шаг вперед, скрывая нижнюю часть тела за прилавком и вновь втискивая свой член вам в рот.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_REDUCES_DISCOUNT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Уменьшить скидку";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.alertedCustomer;
		}

		@Override
		public String getDescription() {
			return "Ты чувствуешь, как член Ralph слегка отходит назад, когда он наклоняется к тебе, "
					+ UtilText.parseSpeech("Что я тебе говорил об этом приличном заведении?! Я ясно дал понять, что будет, если ты будешь слишком сильно шуметь! Скидка пять процентов, и никаких возражений!", Main.game.getNpc(Ralph.class))
					+"<br/>"
					+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
					+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
					+ "<br/><br/>"
					+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>скидка.</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.alertedCustomer=false;
			SexFlags.customerAtCounter = false;
			if(SexFlags.ralphDiscount>0)
				SexFlags.ralphDiscount-=5;
		}
	};
	
	public static final SexAction PARTNER_PENETRATES = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				return "Трахнуть [pc.herHim]";
			}
			return "Оплодотворить [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.askedForBigDiscount && Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.alertedCustomer){
				UtilText.nodeContentSB.append("Ralph наклоняется к вам, "
						+ UtilText.parseSpeech("Что я тебе говорил об этом приличном заведении?! Я ясно дал понять, что будет, если ты будешь слишком сильно шуметь! Скидка пять процентов, и никаких возражений!", Main.game.getNpc(Ralph.class))
						+"<br/>"
						+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
						+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
						+ "<br/><br/>"
						+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>скидка.</b><br/><br/>");
			}
				
			UtilText.nodeContentSB.append("Ralph отступает назад, сбрасывает штаны и идет к входу в лавку. Когда ты слышишь, как он запирает входную дверь и опускает жалюзи, до тебя доходит, что последний покупатель ушел уже довольно давно. Желая разузнать, что происходит, ты выбираешься из-под прилавка и пытаешься встать.<br/><br/>Почти мгновенно по ногам пронзает резкая судорога, и ты невольно вскрикиваешь, сгибаясь пополам и хватаясь за край прилавка. Внезапно ты чувствуешь, как сильные руки обхватывают твои бедра, и прежде чем ты успеваешь среагировать, "
					+(Main.game.getPlayer().isTaur()
						?"Ralph решительно толкает тебя вперед, заставляя взобраться на прилавок."
						:"Ralph прижимает тебя лицом вниз к поверхности прилавка.")
					+ " Ты издаешь тихий стон от его властного обращения, и пока он грубо ощупывает и сжимает твою "+Main.game.getPlayer().getAssSize().getDescriptor()+" задницу, он рычит тебе в самое ухо: "
					+ UtilText.parseSpeech(
						(Main.sex.getCharacterPerformingAction().isWearingCondom()
							?"Значит, хочешь скидку побольше, а?! Знаешь, жаль, конечно, что ты настояла на презервативе, но я все равно дам тебе еще двадцать процентов, если позволишь мне тебя оттрахать."
							:(Main.game.getPlayer().isVisiblyPregnant()
									?"Значит, хочешь скидку побольше, а?! Знаешь, жаль, конечно, что ты уже беременна, но я все равно дам тебе еще двадцать процентов, если позволишь мне тебя оттрахать."
									:"Значит, хочешь скидку побольше, а?! Ну так дай мне наполнить твой живот парой жеребят, и тогда я дам тебе еще двадцать пять процентов!")
						), Main.game.getNpc(Ralph.class))
					+"<br/><br/>К этому моменту тебя уже не волнует никакая скидка, всё, чего ты хочешь — это почувствовать, как этот восхитительный член скользит между твоих ног. Еще до того, как Ralph успевает закончить свое предложение, ты уже стонешь в знак согласия, и он издает короткий смешок, протягивая руку, чтобы сжать твою "
					+ (Main.game.getPlayer().getVaginaType()==VaginaType.HORSE_MORPH?"жаждущую лошадиную киску.":"горячую маленькую киску."));
			
			UtilText.nodeContentSB.append("<br/><br/>");
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				if(Main.game.getPlayer().isTaur()) {
					UtilText.nodeContentSB.append("Стараясь не задеть стойку своим беременным животом, Ralph прижимает твой торс к столешнице.");
				} else {
					UtilText.nodeContentSB.append("Стараясь не задеть стойку своим беременным животом, Ralph направляет тебя вперед, заставляя взобраться на столешницу.");
				}
				UtilText.nodeContentSB.append(" Схватив тебя за бедра, он не теряет времени и приставляет свой массивный член к твоей ждущей щели, и когда он начинает входить, ты издаешь отчаянный визг.");
				
			} else {
				if(Main.game.getPlayer().isTaur()) {
					UtilText.nodeContentSB.append("Направив тебя вперед и заставив взобраться на столешницу, Ralph не теряет времени и приставляет свой массивный член к твоей ждущей щели, и когда он начинает входить, ты издаешь отчаянный визг.");
				} else {
					UtilText.nodeContentSB.append("Прижав твою верхнюю часть тела к столешнице, Ralph не теряет времени и приставляет свой массивный член к твоей ждущей щели, и когда он начинает входить, ты издаешь отчаянный визг.");
				}
			}
			UtilText.nodeContentSB.append(
					" Его широкая, раздутая головка медленно проталкивается внутрь, и ты тяжело дышишь и извиваешься, пока твои складки похотливо обволакивают его животный лошадиный член. С резким шагом вперед Ralph внезапно вонзает свой нетерпеливый орган глубоко в твою голодную киску; ты ахаешь и стонешь, когда он начинает трахать тебя прямо на прилавке лавки.</p><p style='text-align:center;'><b>Теперь ты будешь получать</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
							+(SexFlags.ralphDiscount+(Main.sex.getCharacterPerformingAction().isWearingCondom()||Main.game.getPlayer().isVisiblyPregnant()?20:25)-(SexFlags.alertedCustomer?5:0))
					+"%</b> <b>скидка.</b>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String applyPreParsingEffects() {
			Main.sex.setSexManager(
					new SMRalphDiscountBig(
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM_SEX)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB_SEX))));
			return "";
		}
		@Override
		public void applyEffects() {
			Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA, new ArrayList<>());
			AbstractClothing trousers = Main.game.getNpc(Ralph.class).getClothingInSlot(InventorySlot.LEG);
			if(trousers!=null) {
				Main.game.getNpc(Ralph.class).unequipClothingOntoFloor(trousers, true, Main.game.getNpc(Ralph.class));
			}
			
			SexFlags.customerAtCounter = false;
			if(SexFlags.alertedCustomer) {
				if(SexFlags.ralphDiscount>0) {
					SexFlags.ralphDiscount-=5;
				}
			}
			
			SexFlags.alertedCustomer=false;
			if(Main.sex.getCharacterPerformingAction().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}
		}
		
	};
	
	public static final SexAction PARTNER_PENETRATES_ANUS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Трахать жопу [pc.her]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.askedForBigDiscount && !Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.alertedCustomer){
				UtilText.nodeContentSB.append("Ralph наклоняется к вам, "
						+ UtilText.parseSpeech("Что я тебе говорил об этом приличном заведении?! Я ясно дал понять, что будет, если ты будешь слишком сильно шуметь! Скидка пять процентов, и никаких возражений!", Main.game.getNpc(Ralph.class))
						+"<br/>"
						+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
						+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
						+ "<br/><br/>"
						+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>скидка.</b><br/><br/>");
			}
			
			UtilText.nodeContentSB.append("Ralph отступает назад и, скинув брюки, идет к входу в лавку. Когда ты слышишь, как он запирает входную дверь и опускает жалюзи, до тебя доходит, что последний покупатель ушел уже довольно давно. Однако прежде чем ты успеваешь выбраться из-под прилавка, внушительная фигура Ralph снова возникает перед тобой, преграждая путь.<br/><br/>"
					+ UtilText.parseSpeech("Значит, хочешь скидку побольше, а?! И как именно ты намерена её заслужить?", Main.game.getNpc(Ralph.class))
					+" Спрашивает он, скрестив руки на груди и хмуро глядя на тебя сверху вниз.<br/><br/>Ты понимаешь, что, хотя он был вполне доволен тем, что ты сосешь его член, тебе придется его убедить, если ты хочешь, чтобы он использовал твое другое отверстие. Отодвинув одежду, ты неловко переминаешься с ноги на ногу, наклоняясь, чтобы продемонстрировать свое "+Main.game.getPlayer().getAssCapacity().getDescriptor()+" анус. Ты слышишь, как Ralph изо всех сил пытается подавить предвкушающий стон, и пока ты трясешь своей "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедрами из стороны в сторону, он теряет самообладание.<br/><br/>Внезапно ты чувствуешь, как сильные руки хватают тебя за бедра, и прежде чем ты успеваешь среагировать, Ralph вытягивает тебя из-под прилавка и одним резким движением вжимает лицом в столешницу. Ты издаешь радостный вскрик от его властного обращения, и пока он грубо ощупывает и сжимает твою "+Main.game.getPlayer().getAssSize().getDescriptor()+" задницу, он наклоняется и рычит тебе на ухо: "
					+ UtilText.parseSpeech(
							"Знаешь, я обычно не занимаюсь подобным... Но черт возьми, какая у тебя шикарная задница! Дам тебе еще двадцать процентов, если позволишь мне ее немного поразмять.", Main.game.getNpc(Ralph.class))
					+"<br/><br/>"
					+ "By now, you don't really care about the discount, all you want is to feel that delicious cock ramming its way into your rear entrance."
					+ " Even before Ralph's finished giving you his offer, you're crying out in agreement, and he wastes no time in lining his massive cock up to your waiting asshole."
					+ " You feel the wide, flared head rubbing all over your slutty hole, and as he starts to slowly push his animalistic horse-cock into you, you begin to squirm and squeal."
					+ " With a quick step forwards, Ralph suddenly rams his impatient member deep into your hungry back door, and you let out an ear-splitting cry as he starts fucking you on the shop's counter-top."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
						+(SexFlags.ralphDiscount+20-(SexFlags.alertedCustomer?5:0))
						+"%</b> <b>скидка.</b>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setSexManager(
					new SMRalphDiscountBig(
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM_SEX)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB_SEX))));
			return "";
		}
		@Override
		public void applyEffects() {
			Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS, new ArrayList<>());
			AbstractClothing trousers = Main.game.getNpc(Ralph.class).getClothingInSlot(InventorySlot.LEG);
			if(trousers!=null) {
				Main.game.getNpc(Ralph.class).unequipClothingOntoFloor(trousers, true, Main.game.getNpc(Ralph.class));
			}
			
			SexFlags.customerAtCounter = false;
			if(SexFlags.alertedCustomer) {
				if(SexFlags.ralphDiscount>0) {
					SexFlags.ralphDiscount-=5;
				}
			}
			
			SexFlags.alertedCustomer=false;
			if(Main.sex.getCharacterPerformingAction().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}

		}
		
	};
	
	public static final SexAction PARTNER_ROUGH_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Трахнуть [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				if(Main.game.getPlayer().isTaur()) {
					sb.append("Схватив твои "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедра, Ralph слегка оттягивает тебя назад, и когда ты отступаешь на задних ногах, он внезапно с силой вгоняет свой массивный член в тебя целиком, отчего ты подаешься вперед с отчаянным воплем.");
				} else {
					sb.append("Схватив твои "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедра, Ralph слегка оттягивает тебя назад, уводя твой беременный живот подальше от края прилавка. Убедившись, что освободил достаточно места, он внезапно с силой вгоняет свой массивный член в тебя целиком, отчего ты подаешься вперед с отчаянным воплем.");
				}
				sb.append(" Воодушевленный твоей похотливой реакцией, Ralph начинает стремительно двигать бедрами вперед и назад, неустанно вколачиваясь в твою шлюшью киску. Расположив ноги по обе стороны от твоих, он концентрирует все движения только в бедрах, и твои экстатические крики вырываются короткими вспышками, пока он вбивает свой огромный конский член в твою щель, словно отбойный молоток.");
				
			} else {
				sb.append("Схватив твои "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедрах, Ralph сильно прижимает тебя к стойке, тяжело наваливаясь на тебя сверху и начиная неустанно вколачиваться в твою шлюшью киску. Используя твое тело как подушку, он концентрирует все движения только в бедрах, и твои экстатические крики вырываются короткими вспышками, пока он вбивает свой огромный конский член между твоих ног, словно отбойный молоток.");
			}
			
			sb.append((Main.sex.getCharacterPerformingAction().isWearingCondom()
						?" Каждый раз, когда он входит до самого основания, инерция его тяжелого мешка заставляет яички подпрыгивать и шлепаться о тебя, и ему плевать на то, что всё их содержимое окажется запертым в презервативе, который он заставил надеть хозяина."
						:" Каждый раз, когда он входит до самого основания, инерция его тяжелого мешка заставляет яички подпрыгивать и шлепаться о тебя, напоминая тебе о том, чем именно будет наполнена твоя грядущая конча внутри."));
			
			return sb.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ROUGH_FUCK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Трахнуть [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Схватив твои "+Main.game.getPlayer().getHipSize().getDescriptor()+" бедра — Ralph с силой прижимает тебя к столешнице, тяжело наваливаясь сверху и начиная безжалостно вколачивать свой член в твою шлюшью задницу. Используя твое тело как подушку, он концентрирует все движения только в бедрах, и твои восторженные крики вырываются короткими всплесками, пока он вбивает свой огромный конский член в твой задний проход, словно отбойный молоток."
				+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
					?" Каждый раз, когда он входит до самого основания, инерция его тяжелого мешка заставляет яички подпрыгивать и шлепаться о тебя, и ему плевать на то, что всё их содержимое окажется запертым в презервативе, который он заставил надеть хозяина."
					:" Каждый раз, когда он входит до самого основания, инерция его тяжелого мешка заставляет яички подпрыгивать и шлепаться о тебя, напоминая тебе о том, чем именно будет наполнена твоя грядущая конча внутри.");
		}
		
	};
	
	public static final SexAction PARTNER_NORMAL_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Трахнуть [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return (Main.game.getPlayer().isVisiblyPregnant()
						?"Ralph продолжает крепко сжимать твои бедра, раскачивая тебя вперед и назад, пока наполняет тебя своим огромным черным конским членом."
						:"Ralph продолжает крепко сжимать твои бедра, вжимая тебя в столешницу, пока наполняет тебя своим огромным черным конским членом.")
					+ " Его тяжелые яички раскачиваются взад-вперед, нетерпеливо желая извергнуть свой мощный заряд "
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"в презерватив, который ты подготовила."
						:(Main.game.getPlayer().isVisiblyPregnant()?"глубоко в твою голодную киску.":"глубоко в твою ждущую матку."));
		}
		
	};
	
	public static final SexAction PARTNER_NORMAL_FUCK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Трахнуть [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
				return "Ralph продолжает крепко сжимать твои бедра, вжимая тебя в столешницу, пока наполняет тебя своим огромным черным конским членом. Его тяжелые яички раскачиваются взад-вперед, нетерпеливо желая извергнуть свой мощный заряд "
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"в презерватив, который ты подготовила,"
						:"глубоко в твой ждущий зад.");
		}
		
	};
	

	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Приготовиться";
		}

		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Приготовьтесь к нему.";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "Ты издаешь мягкий [pc.moan] ободрения, готовясь к тому, чтобы [npc.name] достиг(ла) [npc.her] оргазма.";
				case DOM_NORMAL:
					return "Ты издаешь [pc.a_moan+] ободрения, готовясь к тому, чтобы [npc.name] достиг(ла) [npc.her] оргазма.";
				case DOM_ROUGH:
					return "Ты издаешь [pc.a_moan+] ободрения, готовясь к тому, чтобы [npc.name] достиг(ла) [npc.her] оргазма.";
				case SUB_EAGER:
					return "Ты издаешь [pc.a_moan+] ободрения, готовясь к тому, чтобы [npc.name] достиг(ла) [npc.her] оргазма.";
				case SUB_NORMAL:
					return "Ты издаешь [pc.a_moan] ободрения, готовясь к тому, чтобы [npc.name] достиг(ла) [npc.her] оргазма.";
				case SUB_RESISTING:
					return "Ты издаешь [pc.a_moan+], отчаянно пытаясь отстраниться от [npc.name] перед тем, как [npc.she] достигнет(ет) оргазма.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PLAYER_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Оргазм";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли климакса и больше не можете сдерживать оргазм.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("Когда огромный черный член Ralph глубоко врывается в твое горло, ты чувствуешь, как приближается твой пик, и прежде чем ты успеваешь что-то понять, из тебя вырывается отчаянный, приглушенный [pc.moan].");
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("Когда огромный черный член Ralph глубоко входит в твою [pc.pussy+], ты чувствуешь, как приближается твой пик, и прежде чем ты успеваешь что-то понять, ты издаешь [pc.a_moan+].");
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("Когда огромный черный член Ralph глубоко входит в твою [pc.asshole+], ты чувствуешь, как приближается твой пик, и прежде чем ты успеваешь что-то понять, ты издаешь [pc.a_moan+].");
				
			} else {
				UtilText.nodeContentSB.append("Ты чувствуешь, как в паху нарастает отчаянный жар, и с [pc.a_moan+] ты достигаешь своего пика.");
			}
			
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()){

				UtilText.nodeContentSB.append("<br/><br/>");
				
				if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					UtilText.nodeContentSB.append("Ты издаешь глубокий стон, чувствуя, как у основания твоего [pc.cock+] набухает узел, когда ты готовишься кончить.");
					
				} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
					UtilText.nodeContentSB.append("Ты издаешь глубокий стон, чувствуя, как широкая головка твоего [pc.cock+] набухает, когда ты готовишься кончить.");
					
				} else {
					UtilText.nodeContentSB.append("Ты издаешь глубокий стон, чувствуя, как твой [pc.cock+] дергается и пульсирует в предвкушении оргазма.");
					
				}
				
				// Describe cum amount:
				UtilText.nodeContentSB.append(" Так как ваше "+Main.game.getPlayer().getTesticleSize().getDescriptor()+" яички напрягаются");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", ты понимаешь, что не можешь выдавить из себя даже капли спермы.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как из твоего [pc.cum] вырывается тонкая струйка");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", ты чувствуешь, как из твоего [pc.cum] выплескивается небольшое количество смазки");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum] брызжет");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum] извергается");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum] хлещет");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum] хлещет");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", ты чувствуешь, как твой [pc.cum] хлещет");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE){
					if(Main.game.getPlayer().isWearingCondom()) {
						UtilText.nodeContentSB.append("наружу, прямо в твой презерватив.");
						
					} else if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
						UtilText.nodeContentSB.append(" в твой [pc.lowClothing(PENIS)].");
						
					} else {
						UtilText.nodeContentSB.append(" наружу, прямо на пол под тобой.");
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("<br/><br/>Отчаянный, дрожащий жар внезапно поднимается от твоей [pc.pussy+], и ты издаешь маниакальный визг, когда ослепительная волна чистого экстаза накрывает тебя.");
				
				if (!Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
					GameCharacter characterPenetrating = Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).get(0);
					switch(Main.sex.getFirstOngoingSexAreaPenetration(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
						case FINGER:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" Ты глубоко запускаешь пальцы в свою [pc.pussy+] и, отчаянно совершая манящие движения, издаешь серию высоких стонов, пока мышцы влагалища сжимают и обхватывают твои вошедшие пальцы.");
							} else {
								UtilText.nodeContentSB.append(" Пальцы [npc.NamePos] продолжают качать твою [pc.pussy+], и ты издаешь серию высоких стонов, пока мышцы влагалища сжимают и обхватывают вошедшие пальцы.");
							}
							break;
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" Ты продолжаешь трахать саму себя во время оргазма, издавая серию высоких стонов, пока мышцы влагалища сжимают и обхватывают твою [pc.cock+].");
							} else {
								UtilText.nodeContentSB.append(" Ralph продолжает трахать твою [pc.pussy+] на протяжении всего твоего оргазма, и ты издаешь серию высоких стонов, пока мышцы влагалища сжимают и обхватывают его мясистый лошадиный член. Воодушевленный сжимающим, доящим ощущением твоего влагалища, Ralph глубоко вгоняет свой толстый черный конский член в твою [pc.pussy+] и тяжело наваливается на тебя сверху. Ты чувствуешь жар его сильного мужского тела, прижимающегося к твоей спине, и издаешь побежденный стон, когда его лошачий член заявляет права на тебя как на свою очередную сексуальную добычу.");
							}
							break;
						case TAIL:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" Ты продолжаешь пользоваться хвостом, чтобы трахать саму себя во время оргазма, и издаешь серию пронзительных стонов, когда мышцы влагалища сжимаются и обхватывают инородный объект.");
							} else {
								UtilText.nodeContentSB.append(" [npc.NamePos] продолжает трахать твою [pc.pussy+] во время оргазма, и ты издаешь серию пронзительных стонов, когда мышцы влагалища сжимаются и обхватывают инородный объект.");
							}
							break;
						case TONGUE:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" Ты продолжаешь глубоко засовывать язык в свою [pc.pussy+] во время оргазма, и издаешь серию пронзительных стонов, когда мышцы влагалища сжимаются и обхватывают инородный мускул.");
							} else {
								UtilText.nodeContentSB.append(" [npc.Name] продолжает ласкать тебя, вылизывая и целуя твою [pc.pussy+], пока ты испытываешь оргазм, и ты издаешь серию пронзительных стонов, когда мышцы влагалища сжимаются и обхватывают инородный мускул.");
							}
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					UtilText.nodeContentSB.append(" Твоя [pc.pussy+] сильно сжимается, и волна разочарования от осознания собственной пустоты почти заглушает удовольствие, разливающееся по всей паховой области.");
				}

				UtilText.nodeContentSB.append(" С последним, оглушительным криком чистого возбуждения на тебя обрушивается кульминация, которая через несколько мгновений оставляет тебя тяжело дышащей, полностью удовлетворенной развалиной.");
			}
			
			// MOUND ORGASM:
			if (Main.game.getPlayer().getPenisType()==PenisType.NONE && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>Несмотря на отсутствие половых органов, ты чувствуешь, как отчаянное тепло стремительно разливается по нижней части живота, и прежде чем ты успеваешь что-то осознать, это ощущение концентрируется в паху, и тебя внезапно накрывает ослепительный оргазм. Твои ноги сжимаются, и ты издаешь отчаянный крик, когда кульминация глубоко внутри твоего возбужденного кукольного холмика застает тебя врасплох. Волна жаркого наслаждения расходится по всему телу, и ты содрогаешься и дрожишь, когда тебя захлестывает всепоглощающая волна экстаза.");
			}
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("<br/><br/>Когда ты начинаешь приходить в себя после оргазма, ты слышишь, как Ralph предлагает часть твоей скидки клиенту, который только что подслушал твой не самый тихий оргазм, и ты издаешь побежденный вздох, осознавая, что совсем забыла об их присутствии.");
			}
			
			return UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), UtilText.nodeContentSB.toString());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& Main.game.getPlayer().getPenisType()!=PenisType.NONE
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE)
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			
			if(SexFlags.customerAtCounter) {
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			}
			
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Приготовиться";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [pc.name] стремительно приближается к [pc.her] оргазму. Приготовься к этому.";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "[npc.Name] издает мягкий [npc.moan] ободрения, пока [npc.she] готовится к твоему оргазму.";
				case DOM_NORMAL:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к твоему оргазму.";
				case DOM_ROUGH:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к твоему оргазму.";
				case SUB_EAGER:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к твоему оргазму.";
				case SUB_NORMAL:
					return "[npc.Name] издает [npc.a_moan+] ободрения, пока [npc.she] готовится к твоему оргазму.";
				case SUB_RESISTING:
					return "[npc.Name] издает [npc.a_moan+], пока [npc.she] отчаянно пытается вырваться из твоих рук перед тем, как ты кончишь.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Не двигаться";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли климакса и больше не можете сдерживать оргазм.";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()){
				UtilText.nodeContentSB.append("Ralph начинает издавать серию низких, тяжелых стонов, и ты ахаешь, чувствуя, как его член начинает дергаться внутри тебя. С ревом он вонзает свой массивный лошадиный член глубоко в твою киску, и ты взвизгиваешь, чувствуя, как его мощный ствол поднимается внутри тебя, готовясь к оргазму своего хозяина.<br/> Растирая основание члена о твои внешние складки, его массивные яйца напрягаются, когда Ralph начинает кончать.");
				
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" Мощным толчком его гигантский член извергает мощную порцию семени прямо в презерватив, и когда ты чувствуешь, как горячее семя раздувает резину внутри тебя, ты удовлетворенно вздыхаешь. Его член продолжает качать еще несколько раз, и огромный черный конский ствол быстро и безболезненно оставляет свое семя в защитной оболочке, которую ты обеспечила.<br/><br/>Когда яички Ralph окончательно опустошаются, он отстраняется, вынимая свой стремительно размягчающийся член из твоей изрядно использованной киски. Ты тихо вздыхаешь от внезапного чувства пустоты, затем переворачиваешься на спину и игриво обвиваешь ногами Ralph, притягивая его к себе и потирая киску о его теперь уже вялый конский член, пока он завязывает использованный презерватив. Он кладет этот маленький, наполненный семенем сверток рядом с тобой, и ты дуешь на него губы, когда он берется за твои ноги и высвобождается, смеясь при виде твоей реакции, [ralph.speechNoEffects(Эй! Мне тут еще лавкой управлять, помнишь? В любом случае, думаю, ты более чем заслужила эту скидку...)]");
				} else {
					UtilText.nodeContentSB.append(" Мощным раскачивающимся движением его гигантский член извергает мощный заряд"
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?", и когда ты чувствуешь, как горячая сперма наполняет тебя, ты удовлетворенно стонешь."
								:" прямо в твою матку, и когда ты чувствуешь, как горячая сперма наполняет тебя, ты удовлетворенно стонешь.")
							+ " Его член делает еще несколько толчков, и этот огромный черный конь, уверенно вбивая свое семя глубоко в твою голодную киску."
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?" Когда твоя киска полностью пропитывается липкой белой лошадиной спермой, ты чувствуешь, как крепкая хватка Ralph на твоих бедрах ослабевает — он заканчивает свой оргазм."
								:" Когда твоя матка полностью пропитывается липкой белой лошадиной спермой, ты чувствуешь, как крепкая хватка Ralph на твоих бедрах ослабевает — он заканчивает свой оргазм.")
							+ "<br/><br/>Когда яйца Ralph окончательно опустошаются, он отстраняется и с влажным причмокивающим звуком вынимает свой стремительно обмякающий член из твоей раздолбанной щели. Ты издаешь тихий вздох, внезапно ощущая пугающую пустоту, и чувствуешь, как тонкая струйка его теплой влажной спермы вытекает из твоего входа."
							+(Main.game.getPlayer().isTaur()
								?" Оглянувшись через плечо, ты игриво подаешься назад навстречу Ralph, потирая свою наполненную спермой киску о его теперь уже вялый конский член. Однако, кажется, он не настроен на второй раунд, и ты обиженно дуешь губы, пока он смеется."
								:" Перевернувшись на спину, ты игриво обвиваешь ногами Ralph, притягивая его к себе и потирая свою наполненную спермой киску о его теперь уже вялый конский член. Однако, кажется, он не настроен на второй раунд, и ты обиженно дуешь губы, когда он берется за твои ноги и высвобождается, смеясь при виде твоей реакции.")
							+ " [ralph.speechNoEffects(Эй! У меня тут вообще-то магазин, помнишь? В любом случае, думаю, ты заслужила эту скидку...)]");
				}
				
			}else if(!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()){
				UtilText.nodeContentSB.append("Ralph начинает издавать серию низких, тяжелых стонов, и ты ахаешь, чувствуя, как его член начинает дергаться внутри тебя. С ревом он вбивает свой массивный конский член глубоко в твою задницу, и ты вскрикиваешь, ощущая, как его мощный ствол ходит внутри тебя, готовясь к оргазму своего хозяина.<br/> Растирая основание члена о твой анус, его огромные яйца напрягаются, когда Ralph начинает кончать.");
				 
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" Мощным толчком его гигантский член извергает мощную порцию семени прямо в презерватив, и когда ты чувствуешь, как горячее семя раздувает резину внутри тебя, ты удовлетворенно вздыхаешь. Его член продолжает качать еще несколько раз, и огромный черный конский ствол быстро и безболезненно оставляет свое семя в защитном слое, который ты обеспечила.<br/><br/>Когда яички Ralph окончательно опустошаются, он отстраняется, вынимая свой стремительно размягчающийся член из твоего изрядно использованного заднего прохода. Ты тихо вздыхаешь, внезапно почувствовав себя ужасно пустой, затем переворачиваешься на спину и игриво обвиваешь ногами Ralph, притягивая его к себе и потирая пах о его теперь уже вялый конский член, пока он завязывает использованный презерватив. Он кладет маленький наполненный семенем сверток рядом с тобой, и ты дуешь на него губы, когда он берется за твои ноги и высвобождается, смеясь при виде твоей реакции, [ralph.speechNoEffects(Эй! У меня тут магазин, помнишь? В любом случае, думаю, ты более чем заслужила эту скидку...)]");
				} else {
					UtilText.nodeContentSB.append(" Мощным толчком его гигантский член извергает порцию концентрированной спермы прямо тебе в задницу, и когда ты чувствуешь, как горячее семя наполняет тебя, ты издаешь удовлетворенный стон. Его член продолжает качать еще несколько раз, огромный черный конский ствол старательно заталкивает семя глубоко в твой голодный задний проход. Как только твое заднее отверстие полностью насыщается липкой белой лошадиной спермой, ты чувствуешь, как крепкая хватка Ralph на твоих бедрах начинает ослабевать — он заканчивает оргазм.<br/><br/>Когда яйца Ralph окончательно опустошаются, он отстраняется и с влажным причмокивающим звуком вынимает свой стремительно размягчающийся член из твоего изрядно использованного дырочного отверстия. Ты издаешь тихий вздох, внезапно ощущая сильную пустоту, и чувствуешь, как тонкая струйка его теплого влажного семени вытекает из твоего заднего прохода."
							+(Main.game.getPlayer().isTaur()
								?" Оглянувшись через плечо, ты игриво подаешься назад навстречу Ralph, потирая свой наполненный спермой анус о его теперь уже вялый конский член. Однако, кажется, он не настроен на второй раунд, и ты дуешь на него, пока он смеется,"
								:" Перевернувшись на спину, ты игриво обвиваешь ногами Ralph, притягивая его вперед и потирая свой наполненный спермой анус о его теперь уже вялый конский член. Однако, кажется, он не настроен на второй раунд, и ты дуешь на него, пока он берется за твои ноги и высвобождается, смеясь при виде твоей реакции,")
							+ " [ralph.speechNoEffects(Эй! У меня тут вообще-то магазин, помнишь? В любом случае, думаю, ты заслужила эту скидку...)]");
				}
				
			} else {
				if(SexFlags.customerAtCounter){
					if(SexFlags.alertedCustomer)
						UtilText.nodeContentSB.append("Ralph быстро заканчивает с клиентом, который всё еще посмеивается, зная, что происходит под прилавком. Кажется, Ralph просто хочет поскорее от него избавиться, и даже забывает вычесть ту часть твоей скидки, которую он только что раздал! Причина его оплошности вскоре проясняется...<br/><br/><b>Теперь здесь</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
					else
						UtilText.nodeContentSB.append("Ralph быстро заканчивает с покупателем, и когда ты слышишь, как тот начинает уходить, ты понимаешь, что благодаря твоей тишине они не заметили твоего присутствия!<br/><br/><b>Теперь рядом</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>нет покупателей</b> <b>у прилавка.</b><br/><br/>");
				}
				
				UtilText.nodeContentSB.append("Ralph начинает издавать серию низких, тяжелых стонов, и ты ахаешь, чувствуя, как его член начинает дергаться у тебя во рту. С подавленным рыком он вонзает свой массивный конский член глубоко тебе в глотку, и, извиваясь под прилавком, ты чувствуешь, как его мощный ствол пытается подняться выше, готовясь к оргазму своего хозяина.<br/><br/>Втирая основание своего члена в твои губы, он напрягает свои огромные яйца, когда Ralph начинает кончать.");
				
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" Мощным толчком его гигантский член извергает мощную порцию семени прямо в презерватив, и когда вы чувствуете, как горячее семя раздувает кончик защиты, которую вы обеспечили, вы издаете приглушенный стон. Огромный черный конский ствол продолжает пульсировать и дергаться еще несколько раз, безболезненно выгружая свое семя в подготовленную вами защиту.<br/><br/>Когда яички Ralph окончательно опустошаются, он отстраняется, вынимая свой стремительно размягчающийся член из вашего изнуренного горла. Вы тихо вздыхаете, внезапно почувствовав себя совершенно пустой, затем подаетесь вперед и игриво прикусываете губу, глядя на Ralph, пока он завязывает использованный презерватив. Он кладет маленький наполненный семенем сверток рядом с вами и, убедившись, что никто не смотрит, берет вас за одну из ваших [pc.arms+] и поднимает на ноги, жестом предлагая продолжить покупки, пока он выполняет свою часть сделки, [ralph.speechNoEffects(Ну, думаю, ты более чем заслужила эту скидку...)]");
				} else {
					UtilText.nodeContentSB.append(" Мощным толчком его гигантский член извергает густую порцию прямо тебе в желудок, и когда ты чувствуешь, как горячее семя наполняет тебя, из твоего горла вырывается приглушенный стон. Огромный черный конский ствол продолжает пульсировать и дергаться еще несколько раз, закидывая сперму глубоко в твое отверстие для орального секса. Только когда твой желудок полностью наполняется липкой белой лошадиной спермой, Ralph наконец заканчивает свой оргазм.<br/><br/>Когда его яички опустошаются, он отстраняется, вынимая свой стремительно размягчающийся член из твоего измученного горла. Густая, слизистая нить спермной слюны стекает тонкой струйкой из твоего рта, и ты подаешься вперед, вытираясь и игриво улыбаясь Ralph. Убедившись, что никто не смотрит, он берет тебя за одну из твоих [pc.arms+] и поднимает, жестом предлагая продолжить покупки, так как он выполнил свою часть сделки: [ralph.speechNoEffects(Ну, думаю, ты заслужила эту скидку сполна...)]");
				}
			}
			
			
			
			if(((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive() && (SexFlags.ralphDiscount<Main.game.getDialogueFlags().ralphDiscount)){
				UtilText.nodeContentSB.append(
						"<br/><br/>"
						+ "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Ты заслужила "+SexFlags.ralphDiscount+"% dдку на следующие три дня!</b><br/><br/><b>Поскольку Ralph и так давал тебе большую скидку в </b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
							+Main.game.getDialogueFlags().ralphDiscount+"%</b><b>, вместо этого он соглашается продлить её на следующие три дня!</b>");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/><b>You have earned a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+SexFlags.ralphDiscount+"%</b> <b>дку на следующие три дня!</b>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			// If your existing discount is bigger, just refresh the bigger discount:
			if(!((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive() || SexFlags.ralphDiscount>Main.game.getDialogueFlags().ralphDiscount){
				Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
			}
			
			Main.game.getDialogueFlags().setSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID, Main.game.getMinutesPassed());
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Main.sex.getTargetedPartner(cumProvider))) {
				if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.VAGINA).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.ANUS).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.MOUTH).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.NIPPLE).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.NIPPLE);
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};

}
