package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.8.5
 * @author Sightglass
 */
public class PenisPenisCloaca {
	private static boolean hasCloaca(GameCharacter gc) {
		return gc.getGenitalArrangement() == GenitalArrangement.CLOACA ||  gc.getGenitalArrangement() == GenitalArrangement.CLOACA_BEHIND;
	}

	// Innoxia: I removed the start/stop actions from this class as the PenisPenis class handles penis-to-penis actions as "frotting", and so by treating them as separate ongoing actions they clash with one another.
	/*
	public static final SexAction SLIT_PENETRATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
					    
		@Override
		public String getActionTitle() {
			return "Start slit penetration";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}

		@Override
		public String getActionDescription() {
			return "Start sliding [npc.her] [npc.cock] past [npc2.namePos] [npc2.cock] and into [npc2.her] genital slit.";
		}

		@Override
		public String getDescription() {
			
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							"Reaching down, [npc.name] [npc.verb(adjust)] [npc.her] [npc.cock] to line it up with [npc2.hers], before slipping past it, thrusting gently into [npc.her] genital slit.",
							"Carefully adjusting [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.cock] along the base of [npc2.her] [npc2.cock], slipping the tip into [npc2.her] slit.",
							"With [npc.a_moan+], [npc.name] slowly [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to tease [npc.her] [npc.cock] against the opening of [npc2.her] cloaca."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"With a rough buck of [npc.her] [npc.hips+], [npc.name] forcefully [npc.verb(grind)] [npc.her] [npc.cock] against [npc2.namePos] genital slit, .",
							"With a growl, [npc.name] roughly [npc.verb(pull)] [npc2.namePos] crotch into place around [npc.her] [npc.legs], lining up [npc.her] [npc.cock] against [npc2.hers] and starting to thrust them against each other.",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to forcefully grind [npc.her] [npc.cock] up and down over [npc2.hers]."));
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					sb.append(UtilText.returnStringAtRandom(
							" Happily bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and readily [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.her] body as [npc.name] [npc.verb(thrust)] into [npc2.her] slit.",
							" Responding with a happy buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to frantically drive [npc.her] [npc.cock] deeper into [npc2.her] cloaca."));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							" Bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.her] body as [npc.name] [npc.verb(thrust)] into [npc2.her] slit.",
							" Responding with a buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to drive [npc.her] [npc.cock] deeper into [npc2.her] cloaca."));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							" Desperately trying to pull away,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to get away from [npc2.herHim] and pull out.",
							" Responding by frantically recoiling from [npc.namePos] unwanted advance, [npc2.name] [npc2.verb(start)] pleading to be left alone, all the while trying to pull [npc2.her] [npc2.cock] away from [npc.nameHers]."));
					break;
			}
			
			return sb.toString();
		}
		
	};
	*/
	
	private static String getTargetedCharacterResponse(SexAction action) {
		StringBuilder sb = new StringBuilder();
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] с энтузиазмом [npc2.verb(respond)] в ответ, и [npc2.verb(let)] вытаскивает [npc2.a_moan+], чтобы [npc2.she] [npc2.verb(rub)] [npc2.her] собственный [npc2.cock] вверх и вниз по [npc.nameHers].",
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] против [npc.nameHers].",
						" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.hips+], используя [npc.namePos] движения, чтобы помогать втирать [npc2.her] собственный [npc2.cock] в [npc.nameHers]."));
				break;
			case SUB_RESISTING:
				sb.append(UtilText.returnStringAtRandom(
						" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] судорожно [npc2.verb(beg)], чтобы [npc.name] удалось вырваться из [npc2.him].",
						" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name] от паха [npc2.her].",
						" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрал(а) от [npc2.her] паха."));
				break;
			case SUB_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(respond)] входит в [npc2.a_moan+] как [npc2.she], а [npc2.verb(let)] выходит, пока [npc2.her] [npc2.verb(rub)] [npc2.cock] вверх и вниз по [npc.her].",
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, резко выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] против [npc.her].",
						" [npc2.Moaning] в наслаждении, [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips+], используя [npc.namePos] движения, чтобы помогать втирать [npc2.her] собственный [npc2.cock+] в [npc.her]."));
				break;
			case DOM_GENTLE:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] нежно [npc2.verb(respond)], а [npc2.verb(let)] из [npc2.a_moan+], словно [npc2.she], мягко [npc2.verb(rub)] [npc2.her] на [npc2.cock] вверх и вниз по [npc.nameHers].",
						" [npc2.A_moan+] медленно выскальзывает из между [npc2.namePos] [npc2.lips+], и, плавно выталкивая [npc2.her] [npc2.hips], [npc2.she] медленно [npc2.verb(grind)] [npc2.her] [npc2.cock] против [npc.nameHers].",
						" [npc2.Moaning] в восторге, [npc2.name] медленно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помочь прижать [npc2.her] собственный [npc2.cock] к [npc.nameHers]."));
				break;
			case DOM_ROUGH:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] жадно [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] собственный [npc2.cock] вверх и вниз по [npc.nameHers].",
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо выталкивая [npc2.her] [npc2.hips], [npc2.she] с силой [npc2.verb(grind)] [npc2.her] в [npc2.cock] против [npc.nameHers].",
						" [npc2.Moaning] в восторге, [npc2.name] с силой [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помочь грубо втирать [npc2.her] собственный [npc2.cock] в [npc.nameHers]."));
				break;
		}
		return sb.toString();
	}
	
	public static final SexAction SLIT_PENETRATION_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное проникновение в щель";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return hasCloaca(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}
		@Override
		public String getActionDescription() {
			return "Нежно введи свой [npc.cock] в половую щель [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.returnStringAtRandom(
					"Издавая легкие [npc.moan] при каждом движении [npc.her] [npc.hips], [npc.name] нежно [npc.verb(thrust)] [npc.her] [npc.cock+] в половую щель [npc2.namePos].",
					"[npc.Name] [npc.verb(let)] серию мягких [npc.moans], пока [npc.she] нежно [npc.verb(slip)] [npc.her] [npc.cock+] в клоаку [npc2.namePos].",
					"Скользя [npc.her] [npc.cock+] вдоль [npc2.namePos] [npc2.cock+] [npc.name], [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc2.namePos] клоаку."));

			sb.append(getTargetedCharacterResponse(this));
					
			return sb.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Проникновение в щель";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}
		@Override
		public String getActionDescription() {
			return "Втиснуть свой [npc.cock] в половую щель [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом нетерпеливом движении [npc.her] [npc.hips], [npc.name] страстно [npc.verb(thrust)] [npc.her] [npc.cock+] в половую щель [npc2.namePos].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] отчаянно [npc.verb(grind)] [npc.her] [npc.cock+] в клоаку [npc2.namePos].",
					"Нетерпеливо втирая [npc.her] [npc.cock+] вдоль [npc2.namePos], [npc2.cock+] [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] с силой [npc.verb(penetrate)] [npc2.namePos] тесную половую щель."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубое проникновение в щель";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}
		@Override
		public String getActionDescription() {
			return "Грубо втирай свой [npc.cock+] в щель, удерживая [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом мощном движении [npc.her] [npc.hips], [npc.name] грубо [npc.verb(thrust)] [npc.her] [npc.cock+] в половую щель [npc2.namePos].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.cock+] в клоаку [npc2.namePos].",
					"Яростно втирая [npc.her] [npc.cock+] по [npc2.namePos], [npc2.cock+] [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] с силой [npc.verb(penetrate)] [npc2.namePos] тесную половую щель."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	// Innoxia: I removed the start/stop actions from this class as the PenisPenis class handles penis-to-penis actions as "frotting", and so by treating them as separate ongoing actions they clash with one another.
	/*
	public static final SexAction SLIT_PENETRATION_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop slit penetration";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}
		
		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] genital slit.";
		}

		@Override
		public String getDescription() {
			
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"With one last rough thrust, [npc.name] [npc.verb(pull)] [npc.her] groin out of [npc2.namePos] genital slit.",
							"Roughly grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, pulling out of [npc2.namePos] cloaca."));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							"With one last buck of [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] groin out from [npc2.namePos] genital slit.",
							"Rubbing [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, pulling out of [npc2.namePos] cloaca."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							" Although happy to have [npc2.her] slit released, [npc2.name] [npc2.verb(continue)] crying and weakly struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] groin away from [npc.name]."));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] back, signalling [npc2.her] desire for more attention.",
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
					break;
			}
			
			return sb.toString();
		}
	};
	*/
	
	// Innoxia: I removed the orgasm from this class as the PenisPenis class handles the penis-to-penis orgasm action, and so by treating them as separate orgasm actions they clash with one another.
	/*
	public static final SexAction SLIT_PENETRATION_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterToBeCreampied() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return SexAreaPenetration.PENIS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			if(!performer.hasPenisIgnoreDildo()) {
				return false;
			}
			
			List<GameCharacter> ongoingPenis = Main.sex.getCharactersHavingOngoingActionWith(performer, SexAreaPenetration.PENIS);
			if(ongoingPenis.isEmpty()) {
				return false;
			}
			GameCharacter target = ongoingPenis.get(0);
			
			boolean dicksTouching = Main.sex.getOngoingSexAreas(performer, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);;
			if (!dicksTouching || !hasCloaca(target)) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)!=OrgasmBehaviour.CREAMPIE
					&& !performer.isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(performer) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(performer)>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			
			if(Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Slit creampie! (forced)";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked slit creampie!";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked  slit creampie!";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked  slit creampie!";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked  slit creampie!";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked slit creampie!";
				}
			}
			return "Slit creampie!";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to grind your penis into [npc.her] cloaca as your orgasm! As you're on the very brink of orgasm, you have no time to try and push [npc.herHim] away!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Cum inside [npc2.name], grinding your penis into [npc.her] cloaca.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {

				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).isEndsSex();
		}
	};
	*/
	
	public static final SexAction MUTUAL_SLIT_PENETRATION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Взаимное проникновение в щель";
		}

		@Override
		public String getActionDescription() {
			return "Просунь [npc2.namePos] [npc2.cock+] в её половую щель, пока трахаешь [npc2.hers].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			return hasCloaca(performer)
					&& hasCloaca(target)
					&& performer.hasPenisIgnoreDildo()
					&& target.hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			String start = UtilText.returnStringAtRandom(
					"Выпуская [npc.a_moan+], [npc.name] [npc.verb(slip)] кончик [npc2.namePos] [npc2.cock] в щель вокруг собственного [npc.cock+] [npc.her], описывая круги и дразня чувствительную кожу внутри.",
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(reach)] вниз к [npc2.namePos] [npc2.cock+]. Направляя его мимо собственного [npc.cock+] [npc.her], [npc.name] [npc.verb(grin)], а затем [npc.she] [npc.verb(slip)] его глубже в половую щель [npc.her], заставляя [npc2.name] издать [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] от восторга, когда [npc.she] [npc.verb(guide)] [npc2.namePos] [npc2.cock] у входа в клоаку [npc.her], прежде чем проскользнуть внутрь.",
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(slip)] [npc2.namePos] [npc2.cock] в половую щель [npc.her] и [npc.verb(tease)] её медленными круговыми движениями.");
			String middle1 = UtilText.returnStringAtRandom(
				"[npc.Name] медленно [npc.verb(work)] [npc2.namePos] [npc2.cock] глубже в щель [npc.her], пока [npc.she] [npc.verb(push)] собственный [npc.cock+] [npc.her] в [npc2.nameHers].",
				"Вместо того чтобы глубоко вонзить [npc.herself] в [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(push)] собственный [npc.cock+] [npc.her] в щель [npc2.namePos].",
				"Когда [npc2.namePos] [npc2.cock] вталкивается в клоаку [npc.her], [npc.name] [npc.verb(guide)] собственный член [npc.her] в щель [npc2.namePos]."
			);
			String middle2 = UtilText.returnStringAtRandom(
				"[npc2.NameIsFull] ошеломлена стимуляцией как от [npc2.her] [npc2.cock] в [npc.namePos] тесной щели, так и от [npc.namePos] [npc.cock+], входящего в [npc2.hers]. Все, что остается делать [npc2.she], — это содрогаться и [npc2.moan] от этого умопомрачительного удовольствия от взаимного проникновения.",
				"Внутренняя поверхность клоаки [npc.namePos] невероятно чувствительна, и [npc.name] [npc.verb(feel)] каждое содрогание и подергивание, когда [npc2.namePos] [npc2.cock] откликается на тугую хватку ее щели. Собственный [npc.cock] [npc.Her], прижатый к [npc2.namePos] [npc2.cock+] при вталкивании в [npc2.her] щель, пульсирует и дергается точно так же.",
				(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					?"С охватившей ее похотью [npc2.moan], [npc2.name] [npc2.verb(press)] ближе к [npc.name], мягко толкаясь, чтобы загнать как можно больше [npc2.her] [npc2.cock+] внутрь [npc.her] щели, тем самым заталкивая [npc.name] глубже в [npc2.hers]."
					:"")
			);
			String end = UtilText.returnStringAtRandom(
				"После нескольких последних толчков, [npc.name] осторожно [npc.verb(pull)] назад, и взаимное проникновение в клоаку [npc.verb(bring)]. После игривого [npc.verb(slap)] [npc2.namePos] [npc2.cock] об [npc.her]"
						+(performer.isVisiblyPregnant()
								?" беременный живот,"
								:" живот,")
						+" [npc.name] [npc.verb(return)] фроттинг с [npc2.herHim].",
				"Со вздохом и еще несколькими толчками [npc.name] [npc.verb(pull)] назад, так что [npc2.namePos] [npc2.cock+] вываливается из [npc.her] клоаки, после чего она продолжает фроттинг с [npc2.herHim]."
			);
			return String.join(" ",start, middle1, middle2,end);
		}
	};
}
