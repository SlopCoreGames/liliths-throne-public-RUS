package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;
import java.util.Map;

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
 * @since 0.1.88
 * @version 0.3.7.8
 * @author Innoxia
 */
public class TongueNipple {
	
	public static final SexAction SUCKLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isFeral() || Main.sex.getCharacterTargetedForSexAction(this).getFeralAttributes().isBreastsPresent();
		}
		@Override
		public boolean isPhysicallyPossible() {
			return true; // Need this to override detection of whether nipples are penetrable or not.
		}
		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сосать соски";
					} else {
						return "Целовать соски";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Тянуть соски";
					} else {
						return "Целовать соски";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сосать соски-киски";
					} else {
						return "Целовать соски-киски";
					}
			}
			return "";
		}
		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Прижми свои [npc.lips] к [npc2.namePos] [npc2.breast+] и начни целовать [npc2.her] похожие на губы соски.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Прижми свои [npc.lips] к [npc2.namePos] [npc2.breast+] и начни сосать [npc2.her] [npc2.nipple+].");
					break;
				case VAGINA:
					sb.append("Прижми свои [npc.lips] к [npc2.namePos] [npc2.breast+] и начни целовать [npc2.her] похожие на киски соски.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
						?" Поскольку [npc2.sheIsFull] [style.colourMinorGood(лактирующая)], во время этого действия ты сможешь пить [npc2.her] [npc2.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			Map<SexPace, List<String>> descriptors = Util.newHashMapOfValues(
					new Value<>(SexPace.DOM_GENTLE, Util.newArrayListOfValues("slowly", "gently")),
					new Value<>(SexPace.SUB_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.SUB_EAGER, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_ROUGH, Util.newArrayListOfValues("forcefully", "roughly")));
			
			List<String> desList = descriptors.get(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()));
			int index = Util.random.nextInt(desList.size());
			String[] desc = new String[] {desList.get(index), desList.get((index+1)%desList.size())};
			
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] "+desc[0]+" [npc.verb(lean)] в сторону [npc2.namePos] [npc2.breasts+], прежде чем прижать [npc.her] [npc.lips+] к [npc2.her] соскам и "+desc[1]+" начинает ласкать их губами.",
							Util.capitaliseSentence(desc[0])+" прижимая [npc.her] [npc.lips+] к одной из [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] "+desc[1]+" ласкать [npc2.her] соски."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Подобно настоящему рту, [npc2.namePos] похожие на губы соски раздвигаются, обнажая отверстие, напоминающее глотку, что позволяет [npc.name] вонзать [npc.her] язык в [npc2.her] [npc2.breast(true)].");
					}
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] "+desc[0]+" [npc.verb(press)] [npc.her] [npc.lips+] к одной из [npc2.namePos] [npc2.breasts+], прежде чем начать "+desc[1]+" сосать и целовать [npc2.her] [npc2.nipple+].",
							Util.capitaliseSentence(desc[0])+" прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast(true)], [npc.name] [npc.verb(start)] "+desc[1]+" сосать и целовать [npc2.her] [npc2.nipple+]."));

					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" [npc.Name] [npc.verb(feel)], что центр [npc2.namePos] соска раскрывается, обнажая отверстие, позволяя [npc.herHim] вонзать [npc.her] язык в [npc2.her] [npc2.breast(true)].");
					}
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] "+desc[0]+" [npc.verb(lean)] в сторону [npc2.namePos] [npc2.breasts+], прежде чем прижать [npc.her] [npc.lips+] к одному из похожих на киску сосков [npc2.her] и "+desc[1]+" начинает целовать и лизать его.",
							Util.capitaliseSentence(desc[0])+" прижимая [npc.her] [npc.lips+] к одной из [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] "+desc[1]+" сосет и целует [npc2.her] похожий на киску сосок."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Как и у настоящей вагины, в центре соска [npc2.namePos], напоминающего киску, есть отверстие, позволяющее [npc.name] просовывать [npc.her] язык в [npc2.her] [npc2.breast(true)].");
					}
					break;
			}
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) { // Milk:
				switch(Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ZERO_NONE:
						break;
					case ONE_TRICKLE:
					case TWO_SMALL_AMOUNT:
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Проходит всего мгновение, и [npc2.her] [npc2.milk+] начинает стекать в [npc.her] рот, и",
								" [npc.Name] быстро [npc.verb(get)] вкус [npc2.her] [npc2.milk+], когда она брызжет в [npc.her] рот, и"));
						break;
					case FOUR_LARGE_AMOUNT:
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Проходит всего мгновение, и [npc2.her] [npc2.milk+] начинает течь в [npc.her] рот, и",
								" [npc.Name] быстро [npc.verb(get)] вкус [npc2.her] [npc2.milk+], когда она струится в [npc.her] рот, и"));
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Проходит всего мгновение, и [npc2.her] [npc2.milk+] начинает литься в [npc.her] рот, и",
								" [npc.Name] быстро [npc.verb(get)] вкус [npc2.her] [npc2.milk+], когда она хлещет в [npc.her] рот, и"));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] не может не издать тихий вздох в ответ на это глубокое чувство удовлетворения.",
									", нежно прижимая голову [npc.her] к [npc2.her] [npc2.breast+], [npc2.name] мягко [npc2.verb(encourage)] [npc.herHim], чтобы продолжать сосать [npc2.her] [npc2.nipple+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] не может не издать [npc2.a_moan+] в ответ на это глубокое чувство удовлетворения.",
									", радостно прижимая голову [npc.her] к [npc2.her] [npc2.breast+], [npc2.name] охотно [npc2.verb(encourage)] [npc.herHim], чтобы продолжать сосать [npc2.her] [npc2.nipple+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] не может не издать [npc2.a_moan+] в ответ на это глубокое чувство удовлетворения.",
									", грубо втискивая голову [npc.her] в [npc2.her] [npc2.breast+], [npc2.name] [npc2.verb(order)] [npc.herHim], чтобы продолжать сосать [npc2.her] [npc2.nipple+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] не может не издать [npc2.a_sob+], пока [npc2.she] лихорадочно [npc2.verb(try)], пытаясь оттолкнуть [npc.herHim] от [npc2.herHim].",
									", отчаянно пытаясь оттолкнуть голову [npc.namePos] от [npc2.her] [npc2.breast+], [npc2.name] [npc2.verb(plead)] с [npc.herHim], чтобы оставить [npc2.herHim] в покое."));
							break;
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.she] [npc.verb(let)] из [npc.a_moan], когда [npc.she] [npc.verb(gulp)] его.",
							" [npc.she] [npc.verb(let)] восторженный [npc.moan], когда [npc.she] [npc.verb(swallow)] его."));
				}
				
			} else { // No milk:
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Выпятив [npc2.her] грудь, [npc2.name] не может сдержать довольного вздоха, отвечая на восхитительное ощущение от того, как сосут [npc2.her] [npc2.nipple(true)].",
									" Мягко прижимая голову [npc.namePos] к [npc2.her] [npc2.breast+], [npc2.name] тихо [npc2.verb(encourage)] [npc.herHim], чтобы продолжать сосать [npc2.her] [npc2.nipple+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Выпятив [npc2.her] грудь, [npc2.name] не может не издать [npc2.a_moan+] в ответ на восхитительное ощущение от того, как сосут [npc2.her] [npc2.nipple(true)].",
									" Счастливо прижимая голову [npc.namePos] к [npc2.her] [npc2.breast+], [npc2.name] охотно [npc2.verb(encourage)] [npc.herHim], чтобы продолжать сосать [npc2.her] [npc2.nipple+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Выпятив [npc2.her] грудь, [npc2.name] не может не издать [npc2.a_moan+] в ответ на восхитительное ощущение от того, как сосут [npc2.her] [npc2.nipple(true)].",
									" Грубо втискивая голову [npc.namePos] в [npc2.her] [npc2.breast+], [npc2.name] [npc2.verb(order)] [npc.herHim], чтобы продолжать сосать [npc2.her] [npc2.nipple+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] не может сдержать [npc2.a_sob+], пока [npc2.she] лихорадочно [npc2.verb(try)], пытаясь оттолкнуть [npc.herHim] от [npc2.herHim].",
									" Отчаянно пытаясь оттолкнуть голову [npc.namePos] от [npc2.her] [npc2.breast+], [npc2.name] [npc2.verb(plead)] с [npc.herHim], чтобы оставить [npc2.herHim] в покое."));
							break;
					}
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
				float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5));
				
				if(suckleAmount>Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()) {
					suckleAmount = Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue();
				}
				
				String rs = Main.sex.getCharacterPerformingAction().ingestFluid(
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterTargetedForSexAction(this).getMilk(),
							SexAreaOrifice.MOUTH,
							suckleAmount);
				Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-suckleAmount);
				return rs;
			}
			return "";
		}
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			String actionDescription = "";
			switch(Main.sex.getCharacterTargetedForSexAction(action).getNippleShape()) {
				case LIPS:
					actionDescription = UtilText.returnStringAtRandom(
							"занимаясь оральным сексом с [npc2.her], словно губами",
							"целуя сосок [npc2.her]");
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(action).getBreastRawStoredMilkValue()>0) {
						actionDescription = UtilText.returnStringAtRandom(
								"сосет [npc2.her] [npc2.nipple+]",
								"сосет [npc2.milk] из соска [npc2.her]");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"сосет [npc2.her] [npc2.nipple+]",
								"сосет и лижет [npc2.her] [npc2.nipple(true)]");
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(action).isBreastFuckableNipplePenetration()) {
						actionDescription = UtilText.returnStringAtRandom(
								"вылизывает [npc2.her] похожий на киску сосок-пипку",
								"трахает языком [npc2.her] сосок-пипку");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"лижет [npc2.her] похожий на киску сосок-пипку",
								"целует и лижет [npc2.her] сосок-пипку");
					}
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(push)] [npc2.her] [npc2.breast+] на [npc.namePos] [npc.lips+], выпуская [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать "+actionDescription+".",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] [npc2.breast+] в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал(а) "+actionDescription+".",
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(grind)] [npc2.her] [npc2.breast+] на [npc.namePos] [npc.face], а затем с нетерпением умоляет [npc.herHim] продолжить "+actionDescription+"."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев отстранить [npc2.her] [npc2.breasts] от нежеланного [npc.namePos] [npc.tongue], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.she] [npc.verb(continue)] "+actionDescription+".",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от [npc.namePos] [npc.tongue], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)] "+actionDescription+"."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(push)] [npc2.her] [npc2.breast+] на [npc.namePos] [npc.lips+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать "+actionDescription+".",
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, мягко подбрасывая [npc2.her] [npc2.breast+] на [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал "+actionDescription+".",
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.breast+] на [npc.namePos] [npc.face], а затем с нетерпением умоляет [npc.herHim] продолжить "+actionDescription+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] неистово [npc2.verb(thrust)] [npc2.her] [npc2.breast+] на [npc.namePos] [npc.lips+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжать "+actionDescription+".",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо вжимая [npc2.her] [npc2.breast+] в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать "+actionDescription+".",
							" В восторге от [npc2.Moaning], [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.breast+] вниз на [npc.namePos] [npc.face], после чего агрессивно приказывает [npc.herHim] продолжать "+actionDescription+"."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.breast+] на [npc.namePos] [npc.lips+], издавая [npc2.a_moan], пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать "+actionDescription+".",
							" [npc2.A_moan] выскальзывает из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) "+actionDescription+".",
							" В восторге от [npc2.Moaning], [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.breast+] вниз на [npc.namePos] [npc.face], после чего умоляя [npc.herHim] продолжать "+actionDescription+"."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Нежное посасывание соска";
					} else {
						return "Нежный поцелуй соска";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Нежное посасывание соска";
					} else {
						return "Нежный поцелуй соска";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Нежное сосание соска-киски";
					} else {
						return "Нежные поцелуи соска-киски";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Нежно целует и ласкает ртом [npc2.namePos] похожие на рот складки.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Нежно целует и сосет [npc2.namePos] соски.");
					break;
				case VAGINA:
					sb.append("Нежно целует и лижет [npc2.namePos] похожие на киски соски.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
						?" Поскольку [npc2.sheIsFull] [style.colourMinorGood(лактирующая)], во время этого действия ты сможешь пить [npc2.her] [npc2.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нежно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожую на рот складку, издавая приглушенный [npc.moan], пока [npc.she] [npc.do] так.",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], нежно лаская губами [npc2.namePos] похожую на рот складку.",
								"Нежно проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] складку, прежде чем начать медленно целовать и ласкать её."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нежно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожую на рот складку, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по текущей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], нежно лаская губами [npc2.namePos] похожую на рот складку, жадно выпивая [npc2.milk], что вытекает в процессе.",
								"Мягко проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] на [npc2.her] соске, прежде чем начать ласкать его губами и слизывать вытекающую [npc2.milk]."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.do] так.",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] нежно целуя и облизывая [npc2.namePos] [npc2.nipple+].",
								"Мягко проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] на [npc2.her] [npc2.nipple+], прежде чем начать медленно лизать и целовать его."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] выпивает вытекающую [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] нежно целуя и облизывая [npc2.namePos] [npc2.nipple+], жадно слизывая [npc2.milk], которая вытекает в процессе.",
								"Мягко проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] на [npc2.her] [npc2.nipple+], прежде чем начать сосать её и пить [npc2.milk], что вытекает прямо в [npc.her] рот."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожую на киску сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] нежно "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")+" [npc2.namePos] похожий на киску сосок.",
								"Мягко проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] на [npc2.her] похожий на киску сосок, прежде чем начать медленно целовать и облизывать его."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожий на киску сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по [npc2.milk], что вытекает наружу.",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] нежно "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")
									+" [npc2.namePos] сосок-киска, жадно поглощающий вытекающую из него [npc2.milk].",
								"Мягко проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок-киску, прежде чем начать вылизывать и пить вытекающую из неё [npc2.milk]."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Липпл-сосание";
					} else {
						return "Липпл-поцелуй";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сосать сосок";
					} else {
						return "Целовать сосок";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сосать сосок-киску";
					} else {
						return "Целовать сосок-киску";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Целоваться и ласкаться с [npc2.namePos] ротоподобными липплами.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Целовать и сосать [npc2.namePos] соски.");
					break;
				case VAGINA:
					sb.append("Целовать и лизать [npc2.namePos] похожие на киски соски.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
						?" Поскольку [npc2.sheIsFull] [style.colourMinorGood(лактирующая)], во время этого действия ты сможешь пить [npc2.her] [npc2.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] ротоподобный сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно засасывая [npc2.namePos] ротоподобный сосок.",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок, прежде чем начать отчаянно целовать и ласкать его языком."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] ротоподобный сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струйке вытекающей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно засасывая [npc2.namePos] ротоподобный сосок и выпивая [npc2.milk], что вытекает в процессе.",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок, прежде чем начать ласкать его языком и выпивать вытекающую [npc2.milk]."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно целуя и облизывая [npc2.namePos] [npc2.nipple+].",
								"С энтузиазмом проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать отчаянно лизать и целовать её."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струящейся [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно целуя и облизывая [npc2.namePos] [npc2.nipple+], поглощая вытекающую при этом [npc2.milk].",
								"С энтузиазмом проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать сосать её и пить [npc2.milk], что течет прямо в [npc.her] рот."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соско-киску, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] с жадностью "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")+" [npc2.namePos] похожий на киску сосок.",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] соско-киску, прежде чем начать отчаянно целовать и лизать ее."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соско-киску, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по текущей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] с жадностью "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")
										+" [npc2.namePos] сосок-киска, жадно поглощающий вытекающую из него [npc2.milk].",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] соско-киску, прежде чем начать лизать ее и пить вытекающую [npc2.milk]."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Грубое посасывание соска";
					} else {
						return "Грубое поцелуй соска";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Грубое сосание соска";
					} else {
						return "Грубой поцелуй соска";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Грубое сосание соска-киски";
					} else {
						return "Грубые поцелуи в сосок-киску";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Грубо целует и ласкает ртом [npc2.namePos] соскоподобные складки.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Грубо целует и сосет соски [npc2.namePos].");
					break;
				case VAGINA:
					sb.append("Грубо целует и лижет соскоподобные киски-соски [npc2.namePos].");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
						?" Поскольку [npc2.sheIsFull] [style.colourMinorGood(лактирующая)], во время этого действия ты сможешь пить [npc2.her] [npc2.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соскоподобную складку, издавая приглушенный [npc.moan], пока [npc.she] [npc.do] так.",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] в грубых поцелуях с [npc2.namePos] соскоподобной складкой.",
								"Грубо водит кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] складку, прежде чем начать яростно целовать и ласкать её."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соскоподобную складку, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по текущей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] в грубых поцелуях с [npc2.namePos] соскоподобной складкой, жадно поглощая вытекающую [npc2.milk].",
								"Грубо водя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] на [npc2.her] соске, прежде чем начать жадно ласкать его и слизывать вытекающую [npc2.milk]."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.do] так.",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на грубых поцелуях и облизывании [npc2.namePos] [npc2.nipple+].",
								"Грубо водя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] на [npc2.her] [npc2.nipple+], прежде чем начать с силой лизать и целовать его."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] выпивает вытекающую [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на грубых поцелуях и облизывании [npc2.namePos] [npc2.nipple+], жадно слизывая [npc2.milk], которая выделяется в процессе.",
								"Грубо водя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать сосать её и выпивать [npc2.milk], что течёт в [npc.her] рот."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожий на киску сосок, издавая приглушённый [npc.moan], пока [npc.she] [npc.do].",
								"С приглушённым [npc.moan], [npc.name] [npc.verb(focus)] на грубо "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")+" [npc2.namePos] похожий на киску сосок.",
								"Грубо водя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] похожий на киску сосок, прежде чем начать яростно целовать и лизать его."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожий на киску сосок, издавая приглушённый [npc.moan], пока [npc.she] [npc.verb(drink)] по [npc2.milk], что из него течёт.",
								"С приглушённым [npc.moan], [npc.name] [npc.verb(focus)] на грубо "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")
										+" [npc2.namePos] сосок-киска, жадно поглощающий вытекающую из него [npc2.milk].",
								"Грубо водя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] похожий на киску сосок, прежде чем начать лизать его и выпивать [npc2.milk], что из него течёт."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сопротивляться сосанию соска";
					} else {
						return "Сопротивляться поцелуям соска";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сопротивляться сосанию соска";
					} else {
						return "Сопротивляться поцелуям в сосок";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сопротивляться сосанию соска-киски";
					} else {
						return "Сопротивляться поцелуям соска-киски";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Попытаться отстраниться от похожих на рот сосков [npc2.namePos].");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Попытаться отстраниться от сосков [npc2.namePos].");
					break;
				case VAGINA:
					sb.append("Попытаться отстраниться от сосков-кисок [npc2.namePos].");
					break;
			}
			return sb.toString();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] отстраниться от [npc.her] [npc.face], но [npc2.name] [npc2.verb(continue)], мягко прижимая [npc2.her] [npc2.nipple+] к [npc.her] [npc.lips+] и удерживая [npc.herHim] на месте, пока [npc2.she] [npc2.verb(force)] [npc2.herself] на [npc.herHim].",
							"Вместе с [npc.a_sob+], [npc.name] [npc.verb(try)] отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.lips] обратно к [npc2.her] [npc2.nipple+], нежно растирая [npc2.herself] о [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)] отстранить [npc.her] рот от [npc2.namePos] [npc2.breasts+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.nipple+] о [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] отстраниться от [npc.her] [npc.face], но [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.nipple+] вниз к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] яростно [npc2.verb(force)] [npc2.herself] на [npc.herHim].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь отстраниться от [npc2.name], но [npc2.she] яростно [npc2.verb(force)] [npc.her] [npc.lips] прямо обратно к [npc2.her] [npc2.nipple+], грубо втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается в попытках освободиться.",
							"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и когда [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь отстранить рот [npc.her] от [npc2.namePos] [npc2.breasts+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] [npc2.nipple+] против [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)], жадно прижимая [npc2.her] [npc2.nipple+] вниз к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] [npc2.verb(force)] [npc2.herself] на [npc.herHim].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.lips] прямо обратно к [npc2.her] [npc2.nipple+], жадно втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается в попытках освободиться.",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] отстранить [npc.her] рот от [npc2.namePos] [npc2.breasts+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.nipple+] против [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Липпл-сосание";
					} else {
						return "Липпл-поцелуй";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сосать сосок";
					} else {
						return "Целовать сосок";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Сосать сосок-киску";
					} else {
						return "Целовать сосок-киску";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Целоваться и ласкаться с [npc2.namePos] ротоподобными липплами.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Целовать и сосать [npc2.namePos] соски.");
					break;
				case VAGINA:
					sb.append("Целовать и лизать [npc2.namePos] похожие на киски соски.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
						?" Поскольку [npc2.sheIsFull] [style.colourMinorGood(лактирующая)], во время этого действия ты сможешь пить [npc2.her] [npc2.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожий на рот сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] в том, чтобы ласкать губами [npc2.namePos] похожий на рот сосок.",
								"Проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок, прежде чем начать отчаянно целовать и ласкать его."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] ротоподобный сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струйке вытекающей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] в том, чтобы ласкать губами [npc2.namePos] похожий на рот сосок, прихлебывая [npc2.milk], который вытекает в процессе.",
								"Проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок, прежде чем начать ласкать его губами и выпивать вытекающую [npc2.milk]."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на поцелуях и облизывании [npc2.namePos] [npc2.nipple+].",
								"Проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать отчаянно лизать и целовать её."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струящейся [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на поцелуях и облизывании [npc2.namePos] [npc2.nipple+], жадно поглощая [npc2.milk], которая вытекает в процессе.",
								"Проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать сосать её и пить [npc2.milk], что вытекает прямо в [npc.her] рот."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] похожий на киску сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")+" [npc2.namePos] похожий на киску сосок.",
								"Проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] соско-киске, прежде чем начать отчаянно целовать и лизать её."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соско-киске, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струйке [npc2.milk], что течет из неё.",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")
									+" [npc2.namePos] сосок-киска, жадно поглощающий вытекающую из него [npc2.milk].",
								"Проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] соско-киске, прежде чем начать лизать её и жадно пить вытекающую [npc2.milk]."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Жадно сосать сосок языком";
					} else {
						return "Жадно целовать сосок языком";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Жадно сосать сосок";
					} else {
						return "Жадно целовать сосок";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Жадно сосать соско-киску";
					} else {
						return "Жадкие поцелуи сосков-кисок";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Жадно целовать и ласкать ртом [npc2.namePos] соски-складки.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Жадно целовать и сосать [npc2.namePos] соски.");
					break;
				case VAGINA:
					sb.append("Жадно целовать и лизать [npc2.namePos] соски-киски.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
						?" Поскольку [npc2.sheIsFull] [style.colourMinorGood(лактирующая)], во время этого действия ты сможешь пить [npc2.her] [npc2.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] ротоподобный сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно засасывая [npc2.namePos] ротоподобный сосок.",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок, прежде чем начать отчаянно целовать и ласкать его языком."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] ротоподобный сосок, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струйке вытекающей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно засасывая [npc2.namePos] ротоподобный сосок и выпивая [npc2.milk], что вытекает в процессе.",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] сосок, прежде чем начать ласкать его языком и выпивать вытекающую [npc2.milk]."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно целуя и облизывая [npc2.namePos] [npc2.nipple+].",
								"С энтузиазмом проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать отчаянно лизать и целовать её."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(suck)] и [npc.verb(kiss)] [npc2.her] [npc2.nipple+], издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по струящейся [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)], жадно целуя и облизывая [npc2.namePos] [npc2.nipple+], поглощая вытекающую при этом [npc2.milk].",
								"С энтузиазмом проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] [npc2.nipple+], прежде чем начать сосать её и пить [npc2.milk], что течет прямо в [npc.her] рот."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соско-киску, издавая приглушенный [npc.moan], пока [npc.she] [npc.do].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] с жадностью "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")+" [npc2.namePos] похожий на киску сосок.",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] соско-киску, прежде чем начать отчаянно целовать и лизать ее."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(kiss)] [npc2.her] соско-киску, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(drink)] по текущей [npc2.milk].",
								"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] с жадностью "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()?"куннилингусом":"целуя и облизывая")
									+" [npc2.namePos] сосок-киска, жадно поглощающий вытекающую из него [npc2.milk].",
								"С жадностью проводя кончиком [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.breast+], [npc.name] [npc.verb(home)] в [npc2.her] соско-киску, прежде чем начать лизать ее и пить вытекающую [npc2.milk]."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Перестать сосать складки";
					} else {
						return "Перестать целовать складки";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Перестать сосать соски";
					} else {
						return "Перестать целовать соски";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0) {
						return "Перестать сосать соски-киски";
					} else {
						return "Перестать целовать соски-киски";
					}
			}
			return "";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					sb.append("Отстраниться от похожих на рот сосков [npc2.namePos].");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Отстраниться от сосков [npc2.namePos].");
					break;
				case VAGINA:
					sb.append("Отстраниться от похожих на киски сосков [npc2.namePos].");
					break;
			}
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С последним грубым лизком, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.namePos] [npc2.nipple+].",
							"Запечатлев на [npc2.namePos] [npc2.nipple+] последний грубый поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.her] [npc2.breasts+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С последним лизком, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.namePos] [npc2.nipple+].",
							"Запечатлев на [npc2.namePos] [npc2.nipple+] последний влажный поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.her] [npc2.breasts+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)], пытаясь сопротивляться [npc.name], [npc2.sobbing] и ерзая от дискомфорта, когда [npc2.she] понимает, что [npc.name] еще не совсем закончил с [npc2.herHim].",
								" Осознав, что [npc.sheHasFull] еще не совсем закончил с [npc2.herHim], [npc2.name] [npc2.verb(continue)] изо всех сил борется и [npc2.sobbing], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], ожидая, когда [npc.name] отпустит [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] уводит [npc.her] [npc.tongue+] от [npc2.her] [npc2.nipples+].",
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], пока [npc.name] [npc.verb(stop)] доставляет удовольствие [npc2.her] [npc2.nipples+], выдавая [npc2.her] желание получить еще больше [npc.her] внимания."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction BREASTFEED = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Кормить грудью";
			}
			return "Дать сосать соски";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Прижать лицо [npc2.namePos] к своей [npc.breasts] и заставить [npc2.herHim] начать пить твою [npc.milk].";
			}
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleShape()) {
				case LIPS:
					return "Прижать лицо [npc2.namePos] к своей [npc.breasts] и заставить [npc2.herHim] начать целовать твои соски.";
				case INVERTED:
				case NORMAL:
					return "Прижать лицо [npc2.namePos] к своей [npc.breasts] и заставить [npc2.herHim] начать целовать и сосать твои соски.";
				case VAGINA:
					return "Прижать лицо [npc2.namePos] к своей [npc.breasts] и заставить [npc2.herHim] начать целовать и лизать твои похожие на киски соски.";
			}
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			

			UtilText.nodeContentSB.setLength(0);
			Map<SexPace, List<String>> descriptors = Util.newHashMapOfValues(
					new Value<>(SexPace.DOM_GENTLE, Util.newArrayListOfValues("slowly", "gently")),
					new Value<>(SexPace.SUB_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.SUB_EAGER, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_ROUGH, Util.newArrayListOfValues("forcefully", "roughly")));
			
			List<String> desList = descriptors.get(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()));
			int index = Util.random.nextInt(desList.size());
			String[] desc = new String[] {desList.get(index), desList.get((index+1)%desList.size())};
			
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] за голову, [npc.name] "+desc[0]+" [npc.verb(guide)] [npc2.her] [npc2.lips+] к одному из [npc.her] [npc.breasts+], прежде чем "+desc[1]+" заставить [npc2.herHim] целовать и ласкать [npc.her] сосок.",
							"Схватив [npc2.namePos] за голову, [npc.name] "+desc[0]+" [npc.verb(pull)] [npc2.herHim] в [npc.her] [npc.breasts+], прежде чем "+desc[1]+" заставить [npc2.herHim] целовать [npc.her] похожий на рот сосок."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Как и настоящий рот, похожие на губы соски [npc.namePos] раздвигаются, открывая отверстие, напоминающее глотку, что позволяет [npc2.name] вонзить [npc2.her] язык в [npc.her] [npc.breast(true)].");
					}
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] за голову, [npc.name] "+desc[0]+" [npc.verb(guide)] [npc2.her] [npc2.lips+] к одному из [npc.her] [npc.breasts+], прежде чем "+desc[1]+" заставить [npc2.herHim] сосать и целовать [npc.nipple+] [npc.her].",
							"Схватив [npc2.namePos] за голову, [npc.name] "+desc[0]+" [npc.verb(pull)] [npc2.herHim] в [npc.her] [npc.breasts+], прежде чем "+desc[1]+" заставить [npc2.herHim] сосать и целовать [npc.nipple+] [npc.her]."));

					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" [npc2.Name] [npc2.verb(feel)], что центр соска [npc.namePos] раскрывается, обнажая отверстие, позволяя [npc2.herHim] вонзить [npc2.her] язык в [npc.her] [npc.breast(true)].");
					}
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] за голову, [npc.name] "+desc[0]+" [npc.verb(guide)] [npc2.her] [npc2.lips+] к одному из [npc.her] [npc.breasts+], прежде чем "+desc[1]+" заставить [npc2.herHim] лизать и целовать сосок-киску [npc.her].",
							"Схватив [npc2.namePos] за голову, [npc.name] "+desc[0]+" [npc.verb(pull)] [npc2.herHim] в [npc.her] [npc.breasts+], прежде чем "+desc[1]+" заставить [npc2.herHim] лизать и целовать сосок-киску [npc.her]."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Как и настоящая вагина, центр соска [npc.namePos], похожего на киску, скрывает отверстие, позволяя [npc2.name] вонзить [npc2.her] язык в [npc.her] [npc.breast(true)].");
					}
					break;
			}

			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) { // Milk:
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ZERO_NONE:
						break;
					case ONE_TRICKLE:
					case TWO_SMALL_AMOUNT:
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Проходит всего мгновение, и [npc.milk+] [npc.namePos] начинает стекать в рот [npc2.her],",
								" [npc2.Name] быстро [npc2.verb(get)] вкус [npc.her] [npc.milk+], когда она брызжет прямо в рот [npc2.her],"));
						break;
					case FOUR_LARGE_AMOUNT:
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Проходит всего мгновение, и [npc.namePos] [npc.milk+] начинает течь в рот [npc2.her],",
								" [npc2.Name] быстро [npc2.verb(get)] вкус [npc.her] [npc.milk+], пока она струится в рот [npc2.her],"));
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Проходит всего мгновение, и [npc.namePos] [npc.milk+] начинает литься в рот [npc2.her],",
								" [npc2.Name] быстро [npc2.verb(get)] вкус [npc.her] [npc.milk+], пока она хлещет в рот [npc2.her],"));
						break;
				}
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" и, издав тихий, приглушенный вздох, [npc2.she] [npc2.verb(bury)] лицо [npc2.her] в [npc.namePos] [npc.breasts+], и [npc2.verb(continue)] пьет [npc.her] [npc.milk].",
								" и, нежно прижимая [npc2.her] [npc2.face] к [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] мягко посасывает [npc.her] [npc.nipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" и, с приглушенным, [npc2.moan+], [npc2.she] грубо [npc2.verb(bury)] лицо [npc2.her] в [npc.namePos] [npc.breasts+], и [npc2.verb(continue)] жадно пьет [npc.her] [npc.milk].",
								" и, счастливо прижимая [npc2.her] [npc2.face] к [npc.namePos] [npc.breast+], [npc2.she] с жадностью [npc2.verb(continue)] [npc.her] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" и, с приглушенным, [npc2.moan+], [npc2.she] грубо [npc2.verb(bury)] лицо [npc2.her] в [npc.namePos] [npc.breasts+], и [npc2.verb(continue)] жадно пьет [npc.her] [npc.milk].",
								" и, с силой прижимая [npc2.her] [npc2.face] к [npc.namePos] [npc.breast+], [npc2.she] грубо [npc2.verb(continue)] [npc.her] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" и [npc2.she] не может [npc.verb(help)], но [npc.verb(let)] [npc2.a_moan+], пока [npc2.she] отчаянно [npc2.verb(try)], пытаясь оттолкнуть [npc.name] от [npc2.herHim].",
								" и, отчаянно пытаясь отстраниться от [npc.breasts+] [npc.namePos], [npc2.she] [npc2.verb(plead)] с помощью [npc.herHim], чтобы оставить [npc2.herHim] в покое."));
						break;
				}
			
			} else { // No milk:
				String suckleDesc = "";
				switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
					case LIPS:
						suckleDesc = "[npc2.verb(kiss)]";
						break;
					case INVERTED:
					case NORMAL:
						suckleDesc = "[npc2.verb(suck)] и [npc2.verb(kiss)]";
						break;
					case VAGINA:
						suckleDesc = "[npc2.verb(kiss)] и [npc2.verb(lick)]";
						break;
				}
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" С тихим, приглушенным вздохом, [npc2.she] [npc2.verb(bury)] лицом [npc2.her] в [npc.namePos] [npc.breasts+] и нежно "+suckleDesc+" [npc.her] [npc.nipple+].",
								" Мягко прижимая [npc2.her] [npc2.face] к [npc.namePos] [npc.breast+], [npc2.she] нежно "+suckleDesc+" [npc.her] [npc.nipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" С приглушенным, [npc2.moan+], [npc2.she] [npc2.verb(bury)] [npc2.her] лицом в [npc.namePos] [npc.breasts+] и жадно "+suckleDesc+" [npc.her] [npc.nipple+].",
								" Счастливо прижимая [npc2.her] [npc2.face] к [npc.namePos] [npc.breast+], [npc2.she] с нетерпением "+suckleDesc+" [npc.her] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Приглушенно, [npc2.moan+], [npc2.she] грубо [npc2.verb(bury)] [npc2.her] лицо в [npc.namePos] [npc.breasts+] и с силой "+suckleDesc+" [npc.her] [npc.nipple+].",
								" Напористо прижимая [npc2.her] [npc2.face] к [npc.namePos] [npc.breast+], [npc2.she] грубо "+suckleDesc+" [npc.her] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc.verb(help)], но [npc.verb(let)] [npc2.a_moan+], пока [npc2.she] отчаянно [npc2.verb(try)], пытаясь оттолкнуть [npc.name] от [npc2.herHim].",
								" Отчаянно пытаясь вырваться из [npc.breasts+] [npc.namePos], [npc2.she] [npc2.verb(plead)] с помощью [npc.herHim], чтобы [npc2.herHim] оставил(а) её в покое."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterPerformingAction().getBreastRawMilkStorageValue()/5));
				
				if(suckleAmount>Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()) {
					suckleAmount = Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue();
				}
				
				String rs = Main.sex.getCharacterTargetedForSexAction(this).ingestFluid(
						Main.sex.getCharacterPerformingAction(),
						Main.sex.getCharacterPerformingAction().getMilk(),
						SexAreaOrifice.MOUTH,
						suckleAmount);
				Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-suckleAmount);
				return rs;
			}
			return "";
		}
	};
	

	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			String actionDescription = "";
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					actionDescription = UtilText.returnStringAtRandom(
							"засасывает [npc.her] ртом, словно сосок",
							"целует сосок [npc.her]");
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(action).getBreastRawStoredMilkValue()>0) {
						actionDescription = UtilText.returnStringAtRandom(
								"сосет [npc.nipple+] [npc.her]",
								"сосет [npc.milk] из соска [npc.her]");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"сосет [npc.her] [npc.nipple+]",
								"сосет и целует [npc.her] [npc.nipple(true)]");
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(action).isBreastFuckableNipplePenetration()) {
						actionDescription = UtilText.returnStringAtRandom(
								"вылизывает [npc.her] сосок-киску",
								"трахает языком сосок-киску [npc.her]");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"облизывая похожий на киску сосок [npc.her]",
								"целуя и облизывая сосок-киску [npc.her]");
					}
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.breast+], издавая приглушенный [npc2.moan], пока [npc2.she] слабо [npc2.verb(continue)] "+actionDescription+".",
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], и, с жадностью прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" [npc2.Moaning] от восторга, [npc2.name] с жадностью [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.breast(true)], прежде чем жадно "+actionDescription+"."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.face] от [npc.namePos] [npc.breasts], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], когда [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.she] [npc.verb(continue)], чтобы прижать [npc.her] [npc.nipple+] к [npc2.her] [npc2.lips+].",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], чтобы отпрянуть от [npc.namePos] [npc.breasts], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)], чтобы прижать [npc.her] [npc.nipple+] к [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(press)] [npc2.her] [npc2.lips+] о [npc.namePos] [npc.breast+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" Из рта [npc2.namePos] вырывается приглушенный [npc2.moan], и, мягко прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" От восторга [npc2.Moaning], [npc2.name] мягко [npc2.verb(press)] [npc2.her] [npc2.lips+] о [npc.namePos] [npc.breast(true)], прежде чем нежно "+actionDescription+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] грубо [npc2.verb(press)] [npc2.her] [npc2.lips+] о [npc.namePos] [npc.breast+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" Из рта [npc2.namePos] вырывается приглушенный [npc2.moan], и, грубо прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" От восторга [npc2.Moaning], [npc2.name] грубо [npc2.verb(press)] [npc2.her] [npc2.lips+] о [npc.namePos] [npc.breast(true)], прежде чем с силой "+actionDescription+"."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(press)] [npc2.her] [npc2.lips+] о [npc.namePos] [npc.breast+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" Из рта [npc2.namePos] вырывается приглушенный [npc2.moan], и, прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breast+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.breast(true)], прежде чем "+actionDescription+"."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction BREASTFEED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Нежное кормление грудью";
			}
			return "Нежное сосание сосков";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					sb.append("Мягко прижми свои соски к рту [npc2.namePos] и заставь [npc2.herHim] целовать и ласкать их.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Мягко прижми свои соски к рту [npc2.namePos] и заставь [npc2.herHim] целовать и сосать их.");
					break;
				case VAGINA:
					sb.append("Мягко прижми свои соско-киски к рту [npc2.namePos] и заставь [npc2.herHim] лизать их.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
						?" Поскольку ты [style.colourMinorGood(лактирующая)], во время этого действия ты заставишь [npc2.herHim] пить твое [npc.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(push)] [npc.her] ртоподобный сосок против [npc2.namePos] [npc2.lips+], прежде чем использовать [npc.her] контроль над ним, чтобы нежно поцеловать [npc2.herHim].",
							"С [npc.a_moan+], [npc.name] мягко [npc.verb(press)] [npc.her] сосок против рта [npc2.namePos], прежде чем полностью поцеловать [npc2.herHim] в [npc2.lips+].",
							"Издавая тихий [npc.moan], [npc.name] [npc.verb(use)] [npc.her] контроль над [npc.her] соском, чтобы полностью поцеловать [npc2.name] в [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] тихо [npc.verb(let)] нежный [npc.moan], пока [npc.she] мягко [npc.verb(push)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] мягко [npc.verb(press)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"Издавая тихий [npc.moan], [npc.name] мягко [npc.verb(grind)] [npc.her] [npc.nipple+] вниз против [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] мягко [npc.verb(push)] [npc.her] сосок-киску, прижимая его к [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] нежно [npc.verb(press)] [npc.her] сосок-киску о [npc2.namePos] [npc2.lips+].",
							"Издавая тихий [npc.moan], [npc.name] нежно [npc.verb(grind)] [npc.her] сосок-киску о [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Кормить грудью";
			}
			return "Соски посасаны";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					sb.append("Прижми свои соски к рту [npc2.namePos] и заставь [npc2.herHim] целовать и ласкать их.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Прижми свои соски к рту [npc2.namePos] и заставь [npc2.herHim] целовать и посасывать их.");
					break;
				case VAGINA:
					sb.append("Прижми свои соски-киски к рту [npc2.namePos] и заставь [npc2.herHim] лизать их.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
						?" Поскольку ты [style.colourMinorGood(лактирующая)], во время этого действия ты заставишь [npc2.herHim] пить твое [npc.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(push)] [npc.her], подобный рту сосок, прижимаясь к [npc2.namePos] [npc2.lips+], а затем используя [npc.her] контроль над ним, чтобы страстно поцеловать [npc2.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] сосок к [npc2.namePos] рту, прежде чем страстно и полностью поцеловать [npc2.herHim] в [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] контроль над [npc.her] соском, чтобы страстно и полностью поцеловать [npc2.name] в [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] жадно [npc.verb(push)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] жадно [npc.verb(grind)] [npc.her] [npc.nipple+] вниз против [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] жадно [npc.verb(push)] [npc.her] похожий на киску сосок вниз против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] сосок-киску против [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] жадно [npc.verb(grind)] [npc.her] сосок-киску вниз против [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Грубое кормление грудью";
			}
			return "Грубо сосать соски";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					sb.append("Грубо прижать свои соски к рту [npc2.namePos], заставляя [npc2.herHim] целовать и ласкать их.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Грубо прижать свои соски к рту [npc2.namePos], заставляя [npc2.herHim] целовать и сосать их.");
					break;
				case VAGINA:
					sb.append("Грубо прижать свои соски-киски к рту [npc2.namePos], заставляя [npc2.herHim] лизать их.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
						?" Поскольку ты [style.colourMinorGood(лактирующая)], во время этого действия ты заставишь [npc2.herHim] пить твое [npc.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(push)] [npc.her] сосок-рот против [npc2.namePos] [npc2.lips+], прежде чем использовать [npc.her] контроль над ним, чтобы властно поцеловать [npc2.herHim].",
							"С [npc.a_moan+], [npc.name] грубо [npc.verb(press)] [npc.her] сосок против рта [npc2.namePos], прежде чем властно и полностью поцеловать [npc2.herHim] в [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] контроль над [npc.her] соском, чтобы властно и полностью поцеловать [npc2.name] в [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(push)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] грубо [npc.verb(press)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.nipple+] вниз против [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] высунув [npc.a_moan+] как [npc.she], грубо [npc.verb(push)] [npc.her] похожий на киску сосок о [npc2.namePos] [npc2.lips+].",
							"С помощью [npc.a_moan+], [npc.name] грубо [npc.verb(press)] [npc.her] сосок-киску о [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] грубо [npc.verb(grind)] [npc.her] сосок-киску, прижимая её к [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Сопротивляться кормлению грудью";
			}
			return "Сопротивляться сосанию сосков";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.nipples+] от рта [npc2.namePos].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.breasts] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tongue] на [npc.her] [npc.nipple+].",
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] мягкий поцелуй в [npc.her] [npc.nipple+].",
							"С помощью [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.lips+], но [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(continue)] целует [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.breasts] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] грубо [npc2.verb(slide)] [npc2.her] [npc2.tongue] по [npc.her] [npc.nipple+].",
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] силой [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] влажный поцелуй в [npc.her] [npc.nipple+].",
							"С [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)] оттащить [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.lips+], но [npc2.she] крепко удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(continue)] целует [npc.her] [npc.nipple+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.breasts] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue] по [npc.her] [npc.nipple+].",
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] страстный поцелуй в [npc.her] [npc.nipple+].",
							"С [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.lips+], но [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] с жадностью [npc2.verb(continue)] [npc.her] [npc.nipple+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BREASTFEED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Кормить грудью";
			}
			return "Соски посасаны";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					sb.append("Прижми свои соски к рту [npc2.namePos] и заставь [npc2.herHim] целовать и ласкать их.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Прижми свои соски к рту [npc2.namePos] и заставь [npc2.herHim] целовать и посасывать их.");
					break;
				case VAGINA:
					sb.append("Прижми свои соски-киски к рту [npc2.namePos] и заставь [npc2.herHim] лизать их.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
						?" Поскольку ты [style.colourMinorGood(лактирующая)], во время этого действия ты заставишь [npc2.herHim] пить твое [npc.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(push)] [npc.her], подобный рту сосок, прижимаясь к [npc2.namePos] [npc2.lips+], а затем используя [npc.her] контроль над ним, чтобы страстно поцеловать [npc2.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] сосок к [npc2.namePos] рту, прежде чем страстно и полностью поцеловать [npc2.herHim] в [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] контроль над [npc.her] соском, чтобы страстно и полностью поцеловать [npc2.name] в [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(push)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.nipple+] вниз против [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] высунув [npc.a_moan+] как [npc.she] [npc.verb(push)] [npc.her] похожий на киску сосок прямо на [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] сосок-киску о [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] сосок-киску об [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Жадно кормить грудью";
			}
			return "Жадно сосать соски";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					sb.append("Жадно прижать соски к рту [npc2.namePos], чтобы заставить [npc2.herHim] целовать и ласкать их.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("С жадностью прижать соски к рту [npc2.namePos], заставляя [npc2.herHim] целовать и сосать их.");
					break;
				case VAGINA:
					sb.append("С жадностью прижать свои соски-киски к рту [npc2.namePos], заставляя [npc2.herHim] лизать их.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
						?" Поскольку ты [style.colourMinorGood(лактирующая)], во время этого действия ты заставишь [npc2.herHim] пить твое [npc.milk]."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(push)] [npc.her], подобный рту сосок, прижимаясь к [npc2.namePos] [npc2.lips+], а затем используя [npc.her] контроль над ним, чтобы страстно поцеловать [npc2.herHim].",
							"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] сосок к [npc2.namePos] рту, прежде чем страстно и полностью поцеловать [npc2.herHim] в [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] контроль над [npc.her] соском, чтобы страстно и полностью поцеловать [npc2.name] в [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] жадно [npc.verb(push)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.nipple+] против [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] жадно [npc.verb(grind)] [npc.her] [npc.nipple+] вниз против [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] жадно [npc.verb(push)] [npc.her] похожий на киску сосок вниз против [npc2.namePos] [npc2.lips+].",
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] сосок-киску против [npc2.namePos] [npc2.lips+].",
							"Издавая [npc.a_moan+], [npc.name] жадно [npc.verb(grind)] [npc.her] сосок-киску вниз против [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0) {
				return "Прекратить кормление грудью";
			}
			return "Перестать сосать соски";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] отстраниться от твоих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо оттягивая голову [npc2.namePos] от [npc.her] [npc.breasts+], [npc.name] [npc.verb(order)] [npc2.herHim], чтобы прекратить поцелуи с [npc.her] [npc.nipple(true)].",
							"Грубо втирая [npc.her] [npc.nipple+] в [npc2.namePos] [npc2.face] в последний раз, [npc.name], а затем [npc.verb(push)] [npc.herHim] прочь."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Оттягивая голову [npc2.namePos] от [npc.her] [npc.breasts+], [npc.name] [npc.verb(tell)] [npc2.herHim], чтобы прекратить поцелуи с [npc.her] [npc.nipple(true)].",
							"Вжимая [npc.her] [npc.nipple+] в [npc2.namePos] [npc2.face] в последний раз, [npc.name], а затем [npc.verb(push)] [npc.herHim] прочь."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Пока слезы катились по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], так как [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc2.verb(continue)], сопротивляясь [npc.name] и умоляя [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.namePos] еще больше [npc.nipples+] [npc2.her].",
								" Все еще жаждая добавки, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(move)] от [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
