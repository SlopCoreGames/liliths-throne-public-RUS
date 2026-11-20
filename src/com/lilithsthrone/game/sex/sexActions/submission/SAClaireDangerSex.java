package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class SAClaireDangerSex {

	public static final SexAction PARTNER_INTERRUPTED = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().getWorldLocation()==WorldType.SUBMISSION
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Claire.class))
					&& !SexFlags.claireSexInterrupted
					&& Main.sex.getTurn() - SexFlags.claireSexInterruptedTurn > 4
					&& !Main.sex.isInForeplay(Main.game.getNpc(Claire.class))
					&& Main.game.getPlayer().getArousal()<75
					&& Main.game.getNpc(Claire.class).getArousal()<75
					&& Math.random()<0.25f;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Дверь открывается!";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile("characters/submission/claire", "PARTNER_INTERRUPTED");
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = true;
			SexFlags.claireSexInterruptedTurn = Main.sex.getTurn();
		}
	};
	
	public static final SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& SexFlags.claireSexInterrupted;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Затаиться";
		}
		@Override
		public String getActionDescription() {
			return "Делай как говорит Клэр, и старайся не шуметь, пока Enforcer не уйдет.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("characters/submission/claire", "ENFORCER_ENTERING"));
			
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS)
					&& map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
				if(area.isOrifice()) {
					foundPenetration = true;
					sb.append("<br/><br/>");
					sb.append("Когда ты замираешь, не смея издать ни звука, пока Enforcer начинает обыскивать кладовую, ты внезапно чувствуешь, как твоя [pc.cock+] ");
					switch((SexAreaOrifice)area) {
						case ANUS:
							sb.append("пульсирует глубоко внутри [claire.asshole] Клэр. Очевидно, эта ситуация привела кошку в дикое возбуждение, и похотливая кошкодевушка медленно прижимает свою [claire.ass+] к тебе, заставляя твой член проникать еще глубже в ее [claire.asshole].");
							break;
						case ASS:
							sb.append("пульсирует между ягодицами [claire.assSize] Клэр. Очевидно, эта ситуация привела кошку в дикое возбуждение, и похотливая кошкодевушка медленно двигает своей [claire.hips+] тебе навстречу, заставляя твой член скользить вверх и вниз по ее [claire.asshole].");
							break;
						case BREAST:
							sb.append("пульсирует между грудей [claire.breastSize] Клэр. Очевидно, эта ситуация привела кошку в дикое возбуждение, и похотливая кошкодевушка медленно сжимает свои [claire.breasts+], заключая твой член в ложбинку.");
							break;
						case BREAST_CROTCH:
							sb.append("пульсирует между [claire.crotchBoobs] [claire.crotchBoobSize] Клэр. Очевидно, эта ситуация привела кошку в дикое возбуждение, и похотливая кошкодевушка медленно сжимает свои [claire.crotchBoobs+], заключая твой член в ложбинку.");
							break;
						case MOUTH:
							sb.append("пульсирует во рту у Клэр. Очевидно, она безумно возбуждена ситуацией, в которой вы оказались; похотливая кошкодевочка медленно подается вперед, заглатывая твой член глубоко в глотку.");
							break;
						case NIPPLE:
							sb.append("пульсирует глубоко внутри [claire.nipple(true)] Клэр. Очевидно, она безумно возбуждена ситуацией, в которой вы оказались; похотливая кошкодевочка медленно прижимается грудью к тебе, заставляя твой член проникать еще глубже в ее [claire.nipple+].");
							break;
						case NIPPLE_CROTCH:
							sb.append("пульсирует глубоко внутри [claire.crotchNipple] Клэр. Очевидно, она безумно возбуждена ситуацией, в которой вы оказались; похотливая кошкодевочка медленно выпячивает живот тебе навстречу, заставляя твой член проникать еще глубже в ее [claire.crotchNipple+].");
							break;
						case THIGHS:
							sb.append("пульсирует между толстыми бедрами Клэр. Очевидно, она безумно возбуждена ситуацией, в которой вы оказались; похотливая кошкодевочка медленно подается [claire.hips+] назад, насаживаясь на твой член между своих мягких ног.");
							break;
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							sb.append("пульсирует глубоко внутри [claire.urethraVagina] Клэр. Очевидно, она безумно возбуждена ситуацией, в которой вы оказались; похотливая кошкодевочка медленно выпячивает [claire.hips+] тебе навстречу, заставляя твой член проникать еще глубже в ее [claire.urethraVagina].");
							break;
						case VAGINA:
							sb.append("пульсирует глубоко внутри [claire.pussy+] Клэр. Очевидно, эта ситуация привела ее в безумное возбуждение, и похотливая кошкодевочка медленно выталкивает свою [claire.hips+] тебе навстречу, вонзая твой член еще глубже в ее [claire.pussy+].");
							break;
						case ARMPITS:
							sb.append("пульсирует у подмышки Клэр. Очевидно, эта ситуация привела ее в безумное возбуждение, и похотливая кошкодевочка медленно вжимается в тебя, двигаясь вверх и вниз и насаживая твой член на ее [claire.armpit+].");
							break;
						case SPINNERET:
							break;
					}
					sb.append("<br/><br/>Как раз в тот момент, когда тебе кажется, что ты уже на пределе, Enforcer находит то, что искал, и, не заметив вас, занятых совокуплением, уходит так же быстро, как и появился.");
				}
			}
			
			if(!foundPenetration) {
				sb.append("<br/><br/>После нервного тридцатисекундного ожидания Enforcer наконец находит то, что искал, и, не заметив вас, занятых совокуплением, уходит так же быстро, как и появился.");
			}
			sb.append(" С облегчением выдохнув, вы с Клэр возвращаетесь в прежнюю позу, чтобы продолжить с того места, на котором остановились...");

			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
	

	public static final SexAction PLAYER_KEEP_GOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS) && map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				foundPenetration = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next().isOrifice();
			}
			return foundPenetration
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& SexFlags.claireSexInterrupted;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Иди дальше";
		}
		@Override
		public String getActionDescription() {
			return "Игнорировать мольбы Клэр соблюдать тишину и продолжать трахать ее.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("characters/submission/claire", "ENFORCER_ENTERING"));
			
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
			if(area.isOrifice()) {
				sb.append("<br/><br/>");
				sb.append("Не видя причин слушаться Клэр, ты игнорируешь тот факт, что в кладовой рыщет Enforcer, и продолжаешь ");
				switch((SexAreaOrifice)area) {
					case ANUS:
						sb.append(" входишь своим [pc.cock+] в [claire.asshole+] Клэр и выходишь из неё. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её задницу.");
						break;
					case ASS:
						sb.append(" двигаешь своим [pc.cock+] вверх и вниз между ягодицами [claire.assSize] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать ложбинку её задницы.");
						break;
					case BREAST:
						sb.append(" двигаешь своим [pc.cock+] вверх и вниз между [claire.breastSize] грудями Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её пышную ложбинку.");
						break;
					case BREAST_CROTCH:
						sb.append("двигаешь своим [pc.cock+] вверх и вниз между [claire.crotchBoobSize] [claire.crotchBoobs] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать ложбинку между её грудями.");
						break;
					case MOUTH:
						sb.append(" засовываешь свой [pc.cock+] глубоко в глотку Клэр. Не желая быть обнаруженной, похотливая кошкодевочка изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её лицо.");
						break;
					case NIPPLE:
						sb.append(" входи и выходи своим [pc.cock+] в [claire.nipple(true)+] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её [claire.nipple(true)].");
						break;
					case NIPPLE_CROTCH:
						sb.append(" входи и выходи своим [pc.cock+] в [claire.crotchNipple(true)+] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её [claire.crotchNipple(true)].");
						break;
					case THIGHS:
						sb.append(" входи и выходи своим [pc.cock+] между толстых бёдер Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её бёдра.");
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						sb.append(" входи и выходи своим [pc.cock+] в [claire.urethraVagina] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её [claire.urethraVagina].");
						break;
					case VAGINA:
						sb.append(" входи и выходи своим [pc.cock+] в [claire.pussy+] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот обеими руками и изо всех сил старается не издавать ни звука, пока ты продолжаешь безжалостно трахать её [claire.pussy].");
						break;
					case ARMPITS:
						sb.append(" ты двигаешь своим [pc.cock+] вверх и вниз над [claire.armpit] Клэр. Не желая быть обнаруженной, похотливая кошкодевочка прикрывает рот свободной рукой и изо всех сил старается не шуметь, пока ты безжалостно трахаешь её [claire.armpit+].");
						break;
					case SPINNERET:
						break;
				}
				sb.append("<br/><br/>Как раз в тот момент, когда кажется, что пышногрудая кошкодевочка уже не может больше терпеть, Исполнитель находит то, что искал, и, не заметив вас двоих, занятых совокуплением, уходит так же быстро, как и вошел.");
			}
			
			sb.append(" Наконец сумев нарушить молчание, Клэр издает особенно эротичный стон и восклицает: [claire.speechNoEffects(~Аах!~ [pc.Name]! ~Ооох!~ Я... я же просила тебя остановиться!)]");
			sb.append("<br/><br/>");
			sb.append("Говоря своей возбужденной партнерше, что тебе слишком весело, чтобы слушаться её просьб, ты дерзко улыбаешься ей, прежде чем вернуться в прежнюю позу и быстро продолжить с того места, на котором остановился...");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
}
